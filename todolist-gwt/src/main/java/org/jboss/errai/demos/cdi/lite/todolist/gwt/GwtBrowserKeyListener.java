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

import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import elemental2.dom.EventListener;
import elemental2.dom.KeyboardEvent;
import org.jboss.errai.demos.cdi.lite.todolist.model.KeyListener;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public class GwtBrowserKeyListener extends KeyListener {

  private EventListener eventListener = event -> {

    if (!(event instanceof KeyboardEvent)) {
      GWT.log("Not KeyboardEvent");
      return;
    }

    final KeyboardEvent keyboardEvent = (KeyboardEvent) event;

    for (int i = 0; i < getSubscribers().size(); i++) {
      getSubscribers().get(i).onKeyPressed(keyOf(keyboardEvent));
    }
  };

  private char keyOf(final KeyboardEvent keyboardEvent) {
    final String key = keyboardEvent.key;
    switch (key) {
    case "Escape":
      return '\u001B';
    case "Enter":
      return '\n';
    case "Backspace":
      return '\u007F';
    default:
      if (key.length() == 1) {
        return key.charAt(0);
      } else {
        return '\0';
      }
    }
  }

  @Override
  public void start() {
    GWT.log("Starting KeyListener");
    DomGlobal.document.addEventListener("keydown", eventListener);
  }

  @Override
  public void stop() {
    GWT.log("Stopping KeyListener");
    DomGlobal.document.removeEventListener("keydown", eventListener);
  }
}
