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

package org.jboss.errai.demos.cdi.lite.beans;

import com.google.inject.Inject;
import org.jboss.errai.demos.cdi.lite.beans.qualifiers.QualifiedWelcomeMessage;
import org.jboss.errai.demos.cdi.lite.beans.qualifiers.WelcomeStringMessage;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import static java.beans.Introspector.decapitalize;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@Dependent
public class WelcomeService {

  @Inject
  @Named("welcomeMessage1")
  public QualifiedWelcomeMessage messageWithQualifier1;

  @Inject
  @Named("welcomeMessage2")
  public WelcomeMessage messageWithQualifier2;

  @Inject
  public DependentWelcomeMessage publicMessage;

  private final WelcomeMessage privateMessage;
  private final String stringMessage;

  @Inject
  public WelcomeService(final DependentWelcomeMessage privateDependentWelcomeMessage,
                        final @WelcomeStringMessage String stringMessage) {

    this.privateMessage = privateDependentWelcomeMessage;
    this.stringMessage = stringMessage;
  }

  public void printWelcomeMessages() {
    System.out.println("Hello from CDI-Lite managed bean!");
    System.out.println("Public " + decapitalize(publicMessage.text()));
    System.out.println("Private " + decapitalize(privateMessage.text()));
    System.out.println(messageWithQualifier1.text());
    System.out.println(messageWithQualifier2.text());
    System.out.println(stringMessage);
  }

}
