package com.scottlogic.aaylett.guice_webapp_example;

import com.google.gson.Gson;
import com.scottlogic.aaylett.guice_webapp_example.Node.NodeFactory;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class TestServlet extends HttpServlet {

  private static final long serialVersionUID = 7528373021106530918L;

  private String greeting;

  @Inject
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  @Inject
  MyService myService;

  @Inject
  NodeFactory nodeFactory;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (myService.getNodes().isEmpty()) {
      myService.add(nodeFactory.create("aaa"));
    }
    String body = new Gson().toJson(myService.getNodes());
    resp.getOutputStream().print(body);
  }
}
