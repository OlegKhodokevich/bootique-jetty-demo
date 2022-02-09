# jetty-websocket-demo

A simple example that explains how to run websocket on the Jetty server and Bootique.

## Prerequisites

* Java 17 or newer
* Apache Maven

## Build the Demo

```bash
git clone git@github.com:bootique-examples/bootique-jetty-demo.git
cd bootique-jetty-demo/jetty-websocket-demo
mvn clean package
```

## Run the Demo

Check the options available in your app:

```bash
java -jar target/jetty-servlets-filters-demo-X.XX.jar
    
NAME
      jetty-servlets-filters-demo-X.XX.jar

OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.

      -s, --server
           Starts Jetty server.
```

Run the server with the provided `config.yml` file:

```bash    
java -jar target/jetty-websocket-demo-X.XX.jar --server --config=config.yml
```

Try opening the following URL and watch the logs:

* [http://127.0.0.1:9999/myapp](http://127.0.0.1:9999/myapp) should have a message and 5 packages.




    
