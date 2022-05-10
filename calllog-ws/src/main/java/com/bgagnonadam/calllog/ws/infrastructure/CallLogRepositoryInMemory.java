package com.bgagnonadam.calllog.ws.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bgagnonadam.calllog.ws.domain.CallLog;
import com.bgagnonadam.calllog.ws.domain.CallLogRepository;

import jersey.repackaged.com.google.common.collect.Lists;

public class CallLogRepositoryInMemory implements CallLogRepository {

  private Map<String, CallLog> callLogs = new HashMap<>();

  @Override
  public List<CallLog> findAll() {
      return Lists.newArrayList(callLogs.values());
  }

  @Override
  public void save(CallLog callLog) {
    callLogs.put(callLog.getId(), callLog);
  }

  @Override
  public void remove(String id) {
    callLogs.remove(id);
  }
}
