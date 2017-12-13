Errai CDI-Lite Demo
--

This is a demo To-do list application that uses Errai as its dependency injection framework.

> **NOTE:** This is a very early stage development demo. Many of the IOC/CDI features might not work as expected.

Before running..
--

1. Clone the Errai fork at tiagobento and build the `errai-cdi-lite` experimental branch.

    `git clone https://github.com/tiagobento/errai`

    `git checkout errai-cdi-lite`

    `mvn clean install -Dgwt.compiler.skip`
       
1. Clone this repo and build it.
    
    `git clone https://github.com/tiagobento/errai-cdi-lite-demo`
    
    `mvn clean install -Dapt-generators -Dgwt.compiler.skip`

Running on the terminal
---
    
1. Run the Main class.

    `mvn exec:java -Dexec.mainClass="org.jboss.errai.demos.cdi.lite.Main" -pl todolist-java`
    
Running on the browser
---

1. Either with new Errai APT generators.

    `mvn clean gwt:run -pl todolist-gwt -Dapt-generators`
    
2. Or with classical GWT generators.

    `mvn clean gwt:run -pl todolist-gwt`    
    
    
    
