package com.antoineleuf.telephony.ws.domain.contact;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

public class ContactRepositoryTest {

  private static final String AN_ID = UUID.randomUUID().toString();

  protected ContactRepository contactRepository;

  @Test
  public void whenGettingContact_thenAContactIsReturn() {

    // when
    Contact contact = contactRepository.getContact(AN_ID);

    // then
    Truth.assertThat(contact.getTelephoneNumber()).isNotNull();
  }
}
