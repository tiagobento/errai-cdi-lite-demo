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

package org.jboss.errai.demos.cdi.lite.todolist;

import org.jboss.errai.demos.cdi.lite.todolist.util.CircularHoverableListView;
import org.jboss.errai.demos.cdi.lite.todolist.util.ListItems;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.util.List;

import static org.jboss.errai.demos.cdi.lite.todolist.TodoListItem.Status.COMPLETED;
import static org.jboss.errai.demos.cdi.lite.todolist.TodoListItem.Status.TODO;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@Dependent
public class TodoListView extends CircularHoverableListView<TodoListItem> {

  private final List<TodoListItem> items;

  private State state;

  public TodoListView() {
    this(new ListItems<>());
  }

  private TodoListView(final ListItems<TodoListItem> items) {
    super(items);
    this.items = items.getItems();
  }

  @PostConstruct
  public void init() {

    items.add(new TodoListItem("Go shopping 1", TODO));
    items.add(new TodoListItem("Go shopping 2", TODO));
    items.add(new TodoListItem("Go shopping 3", TODO));

    state = State.NAVIGATING;
  }

  @Override
  public String render() {
    return "Press [a] to add.\n"
            + "Press [e] to edit.\n"
            + "Press [d] to delete.\n"
            + "Press [<] to move item up.\n"
            + "Press [>] to move item down.\n"
            + "\n"
            + super.render();
  }

  @Override
  public void onKeyPressed(final char key) {
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
  }

  private void onAddPressed() {
    add(new TodoListItem("Testing addition " + System.currentTimeMillis(), TODO));
  }

  private void onDeletePressed() {
    removeHovered();
  }

  public enum State {
    NAVIGATING, ADDING;
  }
}
