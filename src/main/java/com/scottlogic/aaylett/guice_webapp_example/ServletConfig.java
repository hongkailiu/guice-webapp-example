package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.scottlogic.aaylett.guice_webapp_example.Node.NodeFactory;

public class ServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {
        super.configureServlets();

        serve("/").with(TestServlet.class);

        bind(String.class).toInstance("Hello, World!");
        bind(MyService.class).in(Scopes.SINGLETON);
        //bind(SimpleClusterApp.class).in(Scopes.SINGLETON);
        install(new FactoryModuleBuilder()
            .build(NodeFactory.class));
        System.out.println("1==========");
        bind(ActorSystem.class).toProvider(ActorSystemProvider.class).asEagerSingleton();
        System.out.println("2==========");
        bind(ActorRef.class).toProvider(ActorRefProvider.class).asEagerSingleton();
        System.out.println("3==========");

      }
    });
  }
}
