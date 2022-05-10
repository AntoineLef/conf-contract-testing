package com.bgagnonadam.calllog.ws.domain;

import com.bgagnonadam.calllog.ws.domain.contact.Contact;

public class CallLog {
  private String id;
  private String callerId;
  private Contact caller;
  private String date;
  private int durationInSeconds;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Contact getCaller() {
    return caller;
  }

  public void setCaller(Contact caller) {
    this.caller= caller;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getDurationInSeconds() {
    return durationInSeconds;
  }

  public void setDurationInSeconds(int durationInSeconds) {
    this.durationInSeconds = durationInSeconds;
  }

  public String getCallerId() {
    return callerId;
  }

  public void setCallerId(String callerId) {
    this.callerId = callerId;
  }
}
