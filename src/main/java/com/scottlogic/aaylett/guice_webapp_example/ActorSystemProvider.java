package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.ActorSystem;
import com.google.inject.Provider;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by hongkailiu on 2017-03-07.
 */
public class ActorSystemProvider implements Provider<ActorSystem> {

  @Override
  public ActorSystem get() {
    // Override the configuration of the port
    Config config = ConfigFactory.parseString(
        "akka.remote.netty.tcp.port=" + 2551).withFallback(
        ConfigFactory.load());

    // Create an Akka system
    return ActorSystem.create("ClusterSystem", config);
  }

}
