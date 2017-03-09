package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;

/**
 * Created by hongkailiu on 2017-03-07.
 */
public class MyActorSystem {

  private MyService myService;
  @Getter
  private ActorRef subscriberActorRef;
  @Getter
  private ActorRef publisherActorRef;
  @Getter
  private ActorSystem actorSystem;

  @Inject
  public MyActorSystem(MyService myService) {
    this.myService = myService;
    init();
  }


  private void init() {
    // Override the configuration of the port
    Config config = ConfigFactory.parseString(
        "akka.remote.netty.tcp.port=" + 2551).withFallback(
        ConfigFactory.load());

    // Create an Akka system
    actorSystem = ActorSystem.create("ClusterSystem", config);

    actorSystem.actorOf(
        Props.create(SimpleClusterListener.class, myService), "clusterListener");
    //workerActorRef = actorSystem.actorOf(Props.create(Worker.class), "worker");
    publisherActorRef = actorSystem.actorOf(Props.create(Publisher.class), "publisher");
    subscriberActorRef = actorSystem.actorOf(Props.create(Subscriber.class, publisherActorRef), "subscriber");

    System.out.println("=========" + subscriberActorRef.path().toString());
    System.out.println("=========" + subscriberActorRef.path().address().toString());
  }

}
