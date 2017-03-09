package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by hongkailiu on 2017-03-08.
 */
public class Subscriber extends UntypedActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  private final ActorRef publisherActorRef;

  public Subscriber(ActorRef publisherActorRef) {
    this.publisherActorRef = publisherActorRef;
    ActorRef mediator =
        DistributedPubSub.get(getContext().system()).mediator();
    // subscribe to the topic named "content"
    mediator.tell(new DistributedPubSubMediator.Subscribe("content", getSelf()),
        getSelf());
  }

  public void onReceive(Object msg) {
    if (getSender()==publisherActorRef) {
      log.info("myself");
    }
    if (msg instanceof String)
      log.info("Got: {}", msg);
    else if (msg instanceof DistributedPubSubMediator.SubscribeAck)
      log.info("subscribing");
    else
      unhandled(msg);
  }


}
