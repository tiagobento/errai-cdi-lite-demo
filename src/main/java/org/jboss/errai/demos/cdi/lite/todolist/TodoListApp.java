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

import org.jboss.errai.demos.cdi.lite.todolist.home.HomeView;
import org.jboss.errai.demos.cdi.lite.todolist.model.Display;
import org.jboss.errai.demos.cdi.lite.todolist.model.KeyListener;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@Dependent
public class TodoListApp {

  private final Display display;
  private final KeyListener keyListener;
  private final HomeView homeView;

  @Inject
  public TodoListApp(final Display display, final KeyListener keyListener, final HomeView homeView) {
    this.display = display;
    this.keyListener = keyListener;
    this.homeView = homeView;
  }

  @PostConstruct
  public void init() {
    keyListener.start();
    display.setActiveView(homeView);
  }

  public void start() {
    display.refresh();
  }
}
