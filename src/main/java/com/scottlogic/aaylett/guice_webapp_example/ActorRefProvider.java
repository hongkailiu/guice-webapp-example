package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by hongkailiu on 2017-03-07.
 */
public class ActorRefProvider implements Provider<ActorRef> {

  ActorSystem system;
  MyService myService;

  @Inject
  public ActorRefProvider(ActorSystem system, MyService myService) {
    this.system = system;
    this.myService = myService;
  }

  @Override
  public ActorRef get() {
    return system.actorOf(
    Props.create(SimpleClusterListener.class, myService),"clusterListener");
    /*return system.actorOf(Props.create(new Creator<Actor>() {
      @Override
      public Actor create() throws Exception {
        return new SimpleClusterListener(myService);
      }
    }),"clusterListener");*/
  }

}