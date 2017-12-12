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

package org.jboss.errai.demos.cdi.lite;

import org.jboss.errai.common.configuration.ErraiApp;
import org.jboss.errai.common.configuration.ErraiModule;
import org.jboss.errai.demos.cdi.lite.container.CdiLiteContainer;
import org.jboss.errai.demos.cdi.lite.todolist.TodoListApp;

import static org.jboss.errai.common.configuration.Target.JAVA;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */

@ErraiModule
@ErraiApp(gwtModuleName = "", target = JAVA)
public class Main {

  public static void main(final String[] args) {

    final CdiLiteContainer container = new CdiLiteContainer();

    final TodoListApp todoListApp = container.getBeanManager().lookupBean(TodoListApp.class).getInstance();
    todoListApp.start();
  }

}
