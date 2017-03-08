package com.scottlogic.aaylett.guice_webapp_example;

import com.google.inject.servlet.GuiceFilter;
import java.util.EnumSet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by hongkailiu on 2017-03-07.
 */
public class MyApp {

  public static void main(final String[] args) throws Exception {

    Server jettyServer = new Server(8080);

    // tell jetty to ask Guice about what servlet should handle the requests
    ServletContextHandler context = new ServletContextHandler(
        jettyServer, "/rest", ServletContextHandler.SESSIONS);
    // attach listener who instantiate our injector
    context.addEventListener(new ServletConfig());
    context.addFilter(GuiceFilter.class, "/*",
        EnumSet.of(javax.servlet.DispatcherType.REQUEST));
    // DefaultServlet is at the end of the pipeline to handle what Guice didn't
    //context.addServlet(DefaultServlet.class, "/");

    try {
      jettyServer.start();
      jettyServer.join();
    } catch (Exception e){
      e.printStackTrace();
    }
    finally {
      jettyServer.destroy();
    }

  }
}
