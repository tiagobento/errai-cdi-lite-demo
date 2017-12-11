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

package org.jboss.errai.demos.cdi.lite.container;

import org.jboss.errai.ioc.client.Bootstrapper;
import org.jboss.errai.ioc.client.ErraiContainer;
import org.jboss.errai.ioc.client.QualifierEqualityFactory;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.ioc.client.container.SyncBeanManagerImpl;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public class CdiLiteContainer {

  // Because these classes are generated on the last round of the exporters annotation processors,
  // the compiler doesn't see it. So we have to load them dynamically.
  private static final String BOOTSTRAPPER_FQCN = "org.jboss.errai.ioc.client.BootstrapperImpl";
  private static final String QUALIFIER_EQUALITY_FACTORY_FQCN = "org.jboss.errai.ioc.client.QualifierEqualityFactoryImpl";

  /* This class will be moved to errai-cdi-lite */

  private final SyncBeanManagerImpl beanManager;

  public CdiLiteContainer() {
    try {
      beanManager = new SyncBeanManagerImpl();
      new ErraiContainer(beanManager, newBootstrapper(), newQualifierEqualityFactory()).bootstrap();
    } catch (final Exception e) {
      throw new RuntimeException("Failed to bootstrap CDI-Lite container", e);
    }
  }

  private Bootstrapper newBootstrapper() throws Exception {
    return (Bootstrapper) Class.forName(BOOTSTRAPPER_FQCN).newInstance();
  }

  private QualifierEqualityFactory newQualifierEqualityFactory() throws Exception {
    return (QualifierEqualityFactory) Class.forName(QUALIFIER_EQUALITY_FACTORY_FQCN).newInstance();
  }

  public SyncBeanManager getBeanManager() {
    return beanManager;
  }
}
