package com.scottlogic.aaylett.guice_webapp_example;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by hongkailiu on 2017-03-07.
 */
@Data
public class Node {
  String hostname;

  @Inject
  public Node(@Assisted @NonNull String hostname) {
    this.hostname = hostname;
  }

  public interface NodeFactory {

    Node create(String hostname);
  }
}
