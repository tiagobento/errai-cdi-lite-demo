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

package org.jboss.errai.demos.cdi.lite.todolist.model;

import javax.annotation.PostConstruct;
import java.util.Stack;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public abstract class Display implements KeyPressSensitive {

  private final Stack<View> viewStack;
  private final KeyListener keyListener;

  public Display(final KeyListener keyListener) {
    this.viewStack = new Stack<>();
    this.keyListener = keyListener;
  }

  @PostConstruct
  public void init() {
    this.subscribeTo(keyListener);
  }

  public abstract void refresh();

  public void setActiveView(final View activeView) {
    viewStack.push(activeView);
  }

  protected boolean userCanGoBack() {
    return viewStack.size() > 1;
  }

  @Override
  public void onKeyPressed(final char key) {

    if (key == 'b' && userCanGoBack()) {
      viewStack.pop().onKeyPressed(key);
    }

    viewStack.peek().onKeyPressed(key);
    refresh();
  }

  protected View getCurrentView() {
    return viewStack.peek();
  }
}
