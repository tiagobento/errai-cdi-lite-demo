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

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@Dependent
public class MenuItems {

  private final List<MenuItem> items;

  @Inject
  public MenuItems(final TodoListView todoListView) {
    this.items = Arrays.asList(

            new MenuItem("Menu item 1", todoListView),

            new MenuItem("Menu item 2", todoListView),

            new MenuItem("Exit", todoListView));
  }

  public List<MenuItem> getItems() {
    return items;
  }
}
