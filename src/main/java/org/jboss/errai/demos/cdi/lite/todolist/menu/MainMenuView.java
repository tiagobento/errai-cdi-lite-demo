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

package org.jboss.errai.demos.cdi.lite.todolist.menu;

import org.jboss.errai.demos.cdi.lite.todolist.model.Display;
import org.jboss.errai.demos.cdi.lite.todolist.textual.TextualDisplay;
import org.jboss.errai.demos.cdi.lite.todolist.util.CircularHoverableListView;
import org.jboss.errai.demos.cdi.lite.todolist.util.ListItems;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public class MainMenuView extends CircularHoverableListView<MenuItem> {

  private final List<MenuItem> items;
  private final Display display;

  @Inject
  public MainMenuView(final Display display, final @MainMenu ListItems<MenuItem> items) {
    super(items);
    this.display = display;
    this.items = items.getItems();
  }

  @Override
  public void onKeyPressed(final char key) {
    super.onKeyPressed(key);

    switch (key) {
    case '\n': onEnterPressed(); break;
    }
  }

  private void onEnterPressed() {
    display.setActiveView(items.get(getHoveredItemIndex()).getObject());
  }
}
