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

import org.jboss.errai.demos.cdi.lite.todolist.textual.KeyListener;
import org.jboss.errai.demos.cdi.lite.todolist.textual.KeyPressSensitive;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public class MenuView implements View, KeyPressSensitive {

  private final List<MenuItem> items;
  private final Display display;
  private int hoveredIndex;

  @Inject
  public MenuView(final Display display, final MenuItems items, final KeyListener keyListener) {
    this.subscribeTo(keyListener);
    this.display = display;
    this.items = items.getItems();
    this.hoveredIndex = 0;
  }

  @Override
  public String render() {
    return items.stream().map(this::render).collect(joining("\n"));
  }

  private String render(final MenuItem item) {
    return item.getLabel() + (items.indexOf(item) == hoveredIndex ? " <-" : "");
  }

  @Override
  public void onKeyPressed(char key) {
    if (key == 'w') {
      hoveredIndex = hoveredIndex <= 0 ? items.size() - 1 : hoveredIndex - 1;
    } else if (key == 's') {
      hoveredIndex = hoveredIndex >= items.size() - 1 ? 0 : hoveredIndex + 1;
    } else if (key == '\n') {
      display.setActiveView(items.get(hoveredIndex).getView());
    }
  }
}
