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

package org.jboss.errai.demos.cdi.lite.todolist.gwt;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.config.apt.api.ErraiApp;
import org.jboss.errai.config.apt.api.ErraiModule;
import org.jboss.errai.config.apt.api.Target;
import org.jboss.errai.demos.cdi.lite.todolist.app.TodoListApp;
import org.jboss.errai.demos.cdi.lite.todolist.menu.MainMenuItemsProducer;
import org.jboss.errai.ioc.client.api.EntryPoint;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@EntryPoint
@ErraiModule(iocAlternatives = MainMenuItemsProducer.class)
@ErraiApp(gwtModuleName = "org.jboss.errai.demos.cdi.lite.todolist.GwtBrowserTodoListApp", target = Target.GWT)
public class GwtBrowserErraiApp {

  @Inject
  @Named("display")
  private HTMLDivElement displayDiv;

  @Inject
  public TodoListApp todoListApp;

  @PostConstruct
  public void onModuleLoad() {
    todoListApp.start();
    DomGlobal.document.body.appendChild(displayDiv);
  }
}
