package com.antoineleuf.telephony.ws.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.antoineleuf.telephony.ws.ContactWsMain;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class ContactResourceImplTest {
  @BeforeAll
  public static void globalSetUp() {
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    RestAssured.port = 8082;
  }

  @BeforeEach
  public void setUp() {
    Thread t = new Thread(() -> {
      try {
        ContactWsMain.main(new String[] {});
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    t.setDaemon(true);
    t.start();
  }

  @Test
  public void givenContacts_whenGetAllContacts_thenContactsReturned() {
    RestAssured.get("/api/contacts/1").then().body("name", Matchers.is("Steve Jobs"));
  }
}
