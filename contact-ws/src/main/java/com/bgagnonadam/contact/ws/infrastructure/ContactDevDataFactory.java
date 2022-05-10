package com.bgagnonadam.contact.ws.infrastructure;

import java.util.List;

import com.bgagnonadam.contact.ws.domain.Contact;

import jersey.repackaged.com.google.common.collect.Lists;

public class ContactDevDataFactory {

  public List<Contact> createMockData() {
    List<Contact> contacts = Lists.newArrayList();
    Contact jobs = new Contact();
    jobs.setId("1");
    jobs.setName("Steve Jobs");
    jobs.setAddress("California");
    jobs.setTelephoneNumber("514-999-0000");
    jobs.setBirthday("February 24, 1955");
    contacts.add(jobs);

    Contact balmer = new Contact();
    balmer.setId("2");
    balmer.setName("Steve Balmer");
    balmer.setAddress("Manitoba");
    balmer.setTelephoneNumber("781-888-1111");
    balmer.setBirthday("March 24, 1956");
    contacts.add(balmer);

    Contact franklin = new Contact();
    franklin.setId("3");
    franklin.setName("Benjamin Franklin");
    franklin.setAddress("Washington");
    franklin.setTelephoneNumber("964-543-6475");
    franklin.setBirthday("January 17, 1706");
    contacts.add(franklin);

    return contacts;
  }

}
