/*
 * Copyright (C) 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.demos.cdi.lite.todolist.todolist;

import org.jboss.errai.demos.cdi.lite.todolist.util.CircularHoverableListView;
import org.jboss.errai.demos.cdi.lite.todolist.util.ListItems;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import static org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListItem.Status.COMPLETED;
import static org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListItem.Status.TODO;
import static org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListView.Mode.ADDING;
import static org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListView.Mode.EDITING;
import static org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListView.Mode.NAVIGATING;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@Dependent
public class TodoListView extends CircularHoverableListView<TodoListItem> {

  private Mode mode;

  public TodoListView() {
    super(new ListItems<>());
  }

  @PostConstruct
  public void init() {
    mode = Mode.NAVIGATING;
  }

  @Override
  public String render() {
    if (isEmpty()) {
      return "Your to-do list is empty.\n\nPress [a] to add an element.";
    } else {
      return super.render() + "\n\n" + menu();
    }
  }

  @Override
  protected String menu() {
    return mode == NAVIGATING ? super.menu() + mode.menu() : mode.menu();
  }

  @Override
  public void onKeyPressed(final char key) {
    switch (mode) {
    case NAVIGATING:
      navigatingModeOnKeyPressed(key);
      break;
    case ADDING:
      addingModeOnKeyPressed(key);
      break;
    case EDITING:
      editingModeOnKeyPressed(key);
      break;
    }
  }

  private void editingModeOnKeyPressed(final char key) {
    final String label = getHoveredItem().getLabel();
    switch (key) {
    case '\n':
      mode = NAVIGATING;
      break;
    case '\u007F': //Backspace
      updateHovered(new TodoListItem(label.substring(0, label.length() - 1), getHoveredItem().getState()));
      break;
    default:
      updateHovered(new TodoListItem(label + key, getHoveredItem().getState()));
      break;
    }
  }

  private void addingModeOnKeyPressed(char key) {
    final String label = getHoveredItem().getLabel();
    switch (key) {
    case '\u001B':
      removeHovered();
      mode = NAVIGATING;
      break;
    case '\n':
      mode = NAVIGATING;
      break;
    case '\u007F': //Backspace
      updateHovered(new TodoListItem(label.substring(0, label.length() - 1), getHoveredItem().getState()));
      break;
    default:
      updateHovered(new TodoListItem(label + key, getHoveredItem().getState()));
      break;
    }
  }

  private void navigatingModeOnKeyPressed(final char key) {
    super.onKeyPressed(key);
    switch (key) {
    case 'd': onDeletePressed();
      break;
    case 'a': onAddPressed();
      break;
    case 'e': onEditPressed();
      break;
    case 'c': onCheckPressed();
      break;
    case '<': onMoveUpPressed();
      break;
    case '>': onMoveDownPressed();
      break;
    }
  }

  private void onMoveUpPressed() {
    moveHoveredItemUp();
  }

  private void onMoveDownPressed() {
    moveHoveredItemDown();
  }

  private void onCheckPressed() {
    switch (getHoveredItem().getState()) {
    case TODO:
      updateHovered(new TodoListItem(getHoveredItem().getLabel(), COMPLETED));
      break;
    case COMPLETED:
      updateHovered(new TodoListItem(getHoveredItem().getLabel(), TODO));
      break;
    }
  }

  private void onEditPressed() {
    mode = EDITING;
  }

  private void onAddPressed() {
    add(new TodoListItem("", TODO));
    mode = ADDING;
  }

  private void onDeletePressed() {
    removeHovered();
  }

  public enum Mode {
    NAVIGATING {
      @Override
      String menu() {
        return "Press [c] to mark an item as completed.\n"
                + "Press [a] to add.\n"
                + "Press [e] to edit.\n"
                + "Press [d] to delete.\n"
                + "Press [<] to move item up.\n"
                + "Press [>] to move item down.\n";
      }
    }, EDITING {
      @Override
      String menu() {
        return "You are editing an element.\nPress [Enter] to finish.";
      }
    }, ADDING {
      @Override
      String menu() {
        return "You are adding an element.\nPress [Enter] to finish or [Esc] to cancel.";
      }
    };

    abstract String menu();
  }
}
