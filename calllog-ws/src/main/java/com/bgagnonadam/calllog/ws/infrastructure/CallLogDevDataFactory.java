package com.bgagnonadam.calllog.ws.infrastructure;

import java.util.List;

import com.bgagnonadam.calllog.ws.domain.CallLog;

import jersey.repackaged.com.google.common.collect.Lists;

public class CallLogDevDataFactory {

  public List<CallLog> createMockData() {
    List<CallLog> callLogs = Lists.newArrayList();
    
    CallLog callLog1 = new CallLog();
    callLog1.setId("1");
    callLog1.setCallerId("3");
    callLog1.setDate("2016-07-31T16:45:00Z");
    callLog1.setDurationInSeconds(65);
    callLogs.add(callLog1);

    CallLog callLog2 = new CallLog();
    callLog2.setId("2");
    callLog2.setCallerId("2");
    callLog2.setDate("2016-06-31T15:29:00Z");
    callLog2.setDurationInSeconds(99);
    callLogs.add(callLog2);

    CallLog callLog3 = new CallLog();
    callLog3.setId("3");
    callLog3.setCallerId("2");
    callLog3.setDate("2016-07-30T08:32:33Z");
    callLog3.setDurationInSeconds(22);
    callLogs.add(callLog3);

    return callLogs;
  }
}
