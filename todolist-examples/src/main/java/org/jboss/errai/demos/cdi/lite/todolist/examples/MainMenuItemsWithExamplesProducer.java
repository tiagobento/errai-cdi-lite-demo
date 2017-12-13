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

package org.jboss.errai.demos.cdi.lite.todolist.examples;

import org.jboss.errai.common.client.api.annotations.IOCProducer;
import org.jboss.errai.demos.cdi.lite.todolist.exit.ByeByeView;
import org.jboss.errai.demos.cdi.lite.todolist.menu.MainMenu;
import org.jboss.errai.demos.cdi.lite.todolist.menu.MenuItem;
import org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListView;
import org.jboss.errai.demos.cdi.lite.todolist.util.ListItems;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@Alternative
public class MainMenuItemsWithExamplesProducer {

  @Produces
  @IOCProducer
  @MainMenu
  public static ListItems mainMenuItems(final @Named("todoListViewExample1") TodoListView todoListView1,
          final @Named("todoListViewExample2") TodoListView todoListView2,
          final ByeByeView byeByeView) {

    return new ListItems<>(

            new MenuItem("To-do list slot 1 [example]", todoListView1),

            new MenuItem("To-do list slot 2 [example]", todoListView2),

            new MenuItem("Exit", byeByeView));
  }
}
