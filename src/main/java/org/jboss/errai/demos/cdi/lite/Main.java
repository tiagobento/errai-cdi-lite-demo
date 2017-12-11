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
import org.jboss.errai.demos.cdi.lite.beans.WelcomeService;
import org.jboss.errai.ioc.client.Bootstrapper;
import org.jboss.errai.ioc.client.Container;
import org.jboss.errai.ioc.client.container.BeanManagerSetup;
import org.jboss.errai.ioc.client.container.IOCEnvironment;
import org.jboss.errai.ioc.client.container.SyncBeanManager;

import static org.jboss.errai.common.configuration.Target.JAVA;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */

@ErraiApp(gwtModuleName = "", target = JAVA)
@ErraiModule
public class Main {

  public static void main(final String[] args) throws Exception {
    //Creates the BeanManager from the generated IOCEnvironmentImpl
    final SyncBeanManager beanManager = getNewBeanManager();

    //Bootstraps the Container using the generated BootstrapperImpl
    bootstrapContainer(beanManager);

    //Asks the Container for an instance of WelcomeService
    final WelcomeService welcomeService = beanManager.lookupBean(WelcomeService.class).getInstance();

    //Prints the publicMessage injected in the WelcomeService
    welcomeService.printWelcomeMessages();
  }

  private static void bootstrapContainer(final SyncBeanManager beanManager) throws Exception {
    new Container().bootstrapContainer((BeanManagerSetup) beanManager, newBootstrapper());
  }

  private static SyncBeanManager getNewBeanManager() throws Exception {
    return (SyncBeanManager) newIocEnvironment().getNewBeanManager();
  }

  private static IOCEnvironment newIocEnvironment() throws Exception {
    return (IOCEnvironment) Class.forName("org.jboss.errai.ioc.client.container.IOCEnvironmentImpl").newInstance();
  }

  private static Bootstrapper newBootstrapper() throws Exception {
    return (Bootstrapper) Class.forName("org.jboss.errai.ioc.client.BootstrapperImpl").newInstance();
  }
}
