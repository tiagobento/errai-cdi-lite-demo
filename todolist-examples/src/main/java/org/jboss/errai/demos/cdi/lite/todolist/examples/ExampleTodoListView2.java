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

import org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListItem;
import org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListView;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import static org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListItem.Status.COMPLETED;
import static org.jboss.errai.demos.cdi.lite.todolist.todolist.TodoListItem.Status.TODO;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */

@Dependent
@Named("todoListViewExample2")
public class ExampleTodoListView2 extends TodoListView {

  @PostConstruct
  public void initExample1() {
    add(new TodoListItem("Create an Errai CDI-Lite demo app", COMPLETED));
    add(new TodoListItem("Finish Errai CDI-Lite stuff", TODO));
    add(new TodoListItem("Remove GWT dependency from errai-ioc", TODO));
    add(new TodoListItem("Write blog post about Errai APT generators", TODO));
  }
}
