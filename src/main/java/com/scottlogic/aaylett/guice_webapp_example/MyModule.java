package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Named;

/**
 * Created by hongkailiu on 2017-03-08.
 */
public class MyModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(String.class).toInstance("Hello, World!");
    bind(MyService.class).in(Scopes.SINGLETON);
    System.out.println("1==========");
    bind(MyActorSystem.class).asEagerSingleton();
    System.out.println("3==========");
  }

  @Provides @Named("subscriber")
  ActorRef provideSubscriberActorRef(MyActorSystem myActorSystem) {
    return myActorSystem.getSubscriberActorRef();
  }

  @Provides @Named("publisher")
  ActorRef providePublisherActorRef(MyActorSystem myActorSystem) {
    return myActorSystem.getPublisherActorRef();
  }

  @Provides
  ActorSystem provideActorSystem(MyActorSystem myActorSystem) {
    return myActorSystem.getActorSystem();
  }
}
