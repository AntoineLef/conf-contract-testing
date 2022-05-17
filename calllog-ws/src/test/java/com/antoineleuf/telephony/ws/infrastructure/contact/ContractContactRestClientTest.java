package com.antoineleuf.telephony.ws.infrastructure.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.mock.OpenAPIExpectation;

import com.antoineleuf.telephony.ws.domain.contact.ContactRepositoryTest;

@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = { 8082 })
public class ContractContactRestClientTest extends ContactRepositoryTest {

  @BeforeEach
  public void setup(MockServerClient client) throws InterruptedException {
    client.upsert(OpenAPIExpectation.openAPIExpectation("Contacts.json"));
    contactRepository = new ContactRestClient("http://127.0.0.1:8082");
  }
}
