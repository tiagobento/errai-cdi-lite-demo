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

package org.jboss.errai.demos.cdi.lite.todolist.util;

import org.jboss.errai.demos.cdi.lite.todolist.model.View;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public abstract class CircularHoverableListView<T extends ListItem<?>> implements View {

  private final List<T> items;

  private int i;

  public CircularHoverableListView(final ListItems<T> items) {
    this.items = items.getItems();
  }

  @PostConstruct
  public void initCircularHoverableListView() {
    i = 0;
  }

  @Override
  public String render() {
    return items.stream().map(this::render).collect(joining("\n"));
  }

  private String render(final T item) {
    if (isHovered(item)) {
      return "- " + item.render() + " <-";
    } else {
      return "- " + item.render();
    }
  }

  private boolean isHovered(final T item) {
    return items.indexOf(item) == i;
  }

  @Override
  public void onKeyPressed(final char key) {
    switch (key) {
    case 'w': onUpArrowPressed();
      break;
    case 's': onDownArrowPressed();
      break;
    }
  }

  private int lastIndex() {
    return items.size() - 1;
  }

  private void onDownArrowPressed() {
    i = i >= lastIndex() ? 0 : i + 1;
  }

  private void onUpArrowPressed() {
    i = i <= 0 ? lastIndex() : i - 1;
  }

  protected void removeHovered() {
    if (items.isEmpty()) {
      return;
    }

    items.remove(i);

    if (i != 0 && i >= items.size()) {
      i--;
    }
  }

  protected void moveHoveredItemUp() {
    if (i == 0) {
      return;
    }

    final T aux = getHoveredItem();
    items.remove(i);
    items.add(--i, aux);
  }

  protected void moveHoveredItemDown() {
    if (i == lastIndex()) {
      return;
    }

    final T aux = getHoveredItem();
    items.remove(i);
    items.add(++i, aux);
  }

  protected void updateHovered(final T item) {
    items.remove(i);
    items.add(i, item);
  }

  protected void add(final T item) {
    items.add(item);
    i = lastIndex();
  }

  protected T getHoveredItem() {
    return items.get(i);
  }
}
