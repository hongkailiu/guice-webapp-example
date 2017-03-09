package com.scottlogic.aaylett.guice_webapp_example;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class TestServlet extends HttpServlet {

  private static final long serialVersionUID = 7528373021106530918L;

  private String greeting;

  @Inject
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  @Inject
  private MyService myService;

  @Inject
  private WorkerService workerService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("111");
    workerService.send();
    System.out.println("222");
    String body = new Gson().toJson(myService.getMembers());
    resp.getOutputStream().print(body);
  }
}
