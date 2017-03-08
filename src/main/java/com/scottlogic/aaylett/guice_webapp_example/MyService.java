package com.scottlogic.aaylett.guice_webapp_example;

import akka.cluster.ClusterEvent.MemberUp;
import akka.cluster.Member;
import com.google.inject.Inject;
import com.scottlogic.aaylett.guice_webapp_example.Node.NodeFactory;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

/**
 * Created by hongkailiu on 2017-03-07.
 */
public class MyService {
  @Getter
  private Set<Node> nodes = new HashSet<>();

  @Inject
  NodeFactory nodeFactory;

  public void add(Node node) {
    nodes.add(node);
  }

  public void add(Member member) {
    System.out.println("==================");
    nodes.add(nodeFactory.create(member.address().toString()));
  }
}
