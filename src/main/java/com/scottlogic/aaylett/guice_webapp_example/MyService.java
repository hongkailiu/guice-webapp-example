package com.scottlogic.aaylett.guice_webapp_example;

import java.util.HashSet;
import java.util.Set;

import akka.cluster.Member;
import lombok.Getter;

/**
 * Created by hongkailiu on 2017-03-07.
 */
public class MyService {
  @Getter
  private Set<Member> members = new HashSet<>();

  public void add(Member member) {
    System.out.println("==================");
    members.add(member);
  }

  public void remove(Member member) {
    System.out.println("==================");
    members.remove(member);
  }
}
