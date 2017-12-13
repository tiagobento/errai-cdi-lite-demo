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

import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demos.cdi.lite.todolist.model.Display;
import org.jboss.errai.demos.cdi.lite.todolist.model.KeyListener;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@ApplicationScoped
public class GwtBrowserDisplay extends Display {

  private final HTMLDivElement displayDiv;

  @Inject
  public GwtBrowserDisplay(final KeyListener keyListener, final @Named("display") HTMLDivElement displayDiv) {
    super(keyListener);
    this.displayDiv = displayDiv;
  }

  @Override
  public void refresh() {
    displayDiv.innerHTML = "";

    if (userCanGoBack()) {
      displayDiv.innerHTML += "Press [b] to go back.<br/><br/>";
    }

    displayDiv.innerHTML += getCurrentView().render().replace("\n", "<br/>");
  }
}
