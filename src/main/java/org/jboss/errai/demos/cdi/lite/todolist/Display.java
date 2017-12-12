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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Stack;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@ApplicationScoped
public class Display implements KeyPressSensitive {

  private Stack<View> view;

  protected Display() {
    view = new Stack<>();
  }

  @Inject
  public Display(final KeyListener keyListener) {
    this();
    this.subscribeTo(keyListener);
  }

  public void setActiveView(final View view) {
    this.view.push(view);
  }

  @Override
  public void onKeyPressed(char key) {

    if (key == 'b' && view.size() > 1) {
      view.pop();
    }

    refresh();
  }

  public void refresh() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println(view.peek().render());
  }
}
