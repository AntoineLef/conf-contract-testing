package com.antoineleuf.telephony.ws.domain;

import com.antoineleuf.telephony.ws.api.dto.CallLogDto;
import com.antoineleuf.telephony.ws.domain.contact.ContactAssembler;

public class CallLogAssembler {
  
  
  private ContactAssembler contactAssembler;
  
  public CallLogAssembler() {
    contactAssembler = new ContactAssembler();
  }
  public CallLogDto create(CallLog callLog) {
    CallLogDto callLogDto = new CallLogDto();
    
    callLogDto.id = callLog.getId();
    callLogDto.caller = contactAssembler.create(callLog.getCaller());
    callLogDto.date = callLog.getDate();
    callLogDto.durationInSeconds = callLog.getDurationInSeconds();
  
    return callLogDto;
  }
}
