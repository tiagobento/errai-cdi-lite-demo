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
import org.jboss.errai.common.client.api.annotations.IOCProducer;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public class DisplayDivProducer {

  private static final HTMLDivElement displayDiv = (HTMLDivElement) DomGlobal.document.createElement("div");

  @IOCProducer
  @Produces
  @Named("display")
  public static HTMLDivElement displayDiv() {
    return displayDiv;
  }
}
