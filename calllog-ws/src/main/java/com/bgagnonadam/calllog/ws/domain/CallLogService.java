package com.bgagnonadam.calllog.ws.domain;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.bgagnonadam.calllog.ws.api.dto.CallLogDto;
import com.bgagnonadam.calllog.ws.domain.contact.Contact;
import com.bgagnonadam.calllog.ws.domain.contact.ContactRepository;

public class CallLogService {

  private static final Logger logger = Logger.getLogger(CallLogService.class.getName());

  private CallLogRepository callLogRepository;

  private CallLogAssembler callLogAssembler;

  private ContactRepository contactRepository;

  public CallLogService(CallLogRepository callLogRepository, CallLogAssembler callLogAssembler, ContactRepository contactRepository) {
    this.callLogRepository = callLogRepository;
    this.callLogAssembler = callLogAssembler;
    this.contactRepository = contactRepository;
  }

  public List<CallLogDto> findAllCallLogs() {
    logger.info("Get all call logs");
    List<CallLog> callLogs = callLogRepository.findAll();
    
    callLogs.stream().forEach(callLog -> {
      String callerId = callLog.getCallerId();
      Contact foundContact = contactRepository.getContact(callerId);
      callLog.setCaller(foundContact);
      logger.info(String.format("Fetching caller's contact info with Id: %s for call log: %s", callerId, callLog.getId()));
    });
    
    return callLogs.stream().map(callLogAssembler::create).collect(Collectors.toList());
  }

  public void deleteCallLog(String id) {
    logger.info(String.format("Delete call log with id %s", id));
    callLogRepository.remove(id);
  }
}
