package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by hongkailiu on 2017-03-08.
 */
public class WorkerService {

  private final ActorRef subscriber;
  private final ActorRef publisher;

  @Inject
  public WorkerService(@Named("subscriber") ActorRef subscriber,@Named("publisher") ActorRef publisher) {
    this.subscriber = subscriber;
    this.publisher = publisher;
  }

  public void send(){
    publisher.tell("aaa", publisher);
  }
}
