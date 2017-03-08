package com.scottlogic.aaylett.guice_webapp_example;

import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.MemberRemoved;
import akka.cluster.ClusterEvent.MemberUp;
import akka.cluster.ClusterEvent.UnreachableMember;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SimpleClusterListener extends UntypedActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  Cluster cluster = Cluster.get(getContext().system());

  private MyService myService;

  public SimpleClusterListener(MyService myService){
    this.myService = myService;
  }

  //subscribe to cluster changes
  @Override
  public void preStart() {
    //#subscribe
    cluster.subscribe(getSelf(), ClusterEvent.initialStateAsEvents(), 
        MemberEvent.class, UnreachableMember.class);
    //#subscribe
  }

  //re-subscribe when restart
  @Override
  public void postStop() {
    cluster.unsubscribe(getSelf());
  }

  @Override
  public void onReceive(Object message) {
    if (message instanceof MemberUp) {
      MemberUp mUp = (MemberUp) message;
      log.info("Member is Up: {}", mUp.member());
      myService.add(mUp.member());
    } else if (message instanceof UnreachableMember) {
      UnreachableMember mUnreachable = (UnreachableMember) message;
      log.info("Member detected as unreachable: {}", mUnreachable.member());
      myService.remove(mUnreachable.member());
    } else if (message instanceof MemberRemoved) {
      MemberRemoved mRemoved = (MemberRemoved) message;
      log.info("Member is Removed: {}", mRemoved.member());
      myService.remove(mRemoved.member());
    } else if (message instanceof MemberEvent) {
      // ignore

    } else {
      unhandled(message);
    }

  }
}
