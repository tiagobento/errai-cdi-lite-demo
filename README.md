Errai CDI-Lite Demo
--

This is a demo application that uses Errai as its dependency injection framework.

> **NOTE:** This is a very early stage development demo. Many of the IOC/CDI features might not work as expected.


Trying it out
---

1. Clone the Errai fork at tiagobento and build the `errai-cdi-lite` experimental branch

    `git clone https://github.com/tiagobento/errai`

    `git checkout errai-cdi-lite`

    `mvn clean install -Dgwt.compiler.skip=true`
    
2. Clone this repo and build it using the `-Dapt-generators` flag.
    
    `git clone https://github.com/tiagobento/errai-cdi-lite-demo`
    
    `mvn clean install -Dapt-generators`
    
3. Run the Main class

    `mvn exec:java -Dexec.mainClass="org.jboss.errai.demos.cdi.lite.Main"`
    
    
