Hello world
==

This is a very simple app you can build using Errai CDI-Lite.

To run it, simply build it then run the Main class:

1. `mvn clean install -Dapt-generators`

2. `mvn exec:java -Dexec.mainClass="org.jboss.errai.demos.cdi.lite.Main"`

You should see the following output:
```text
Hello from CDI-Lite managed bean!
Public hello from CDI-Lite regular dependent bean!
Private hello from CDI-Lite regular dependent bean!
Hello from CDI-Lite qualified bean 1!
Hello from CDI-Lite qualified bean 2!
Hello from CDI-Lite custom-qualified string!
```
