/*
* Copyright (c) 2011 Canon USA Inc. All rights reserved.
* Original Author: AUTO generated File : S21WDS CodeTable Generator Tool
* Company: Canon USA Inc.
* Date Generated:2015/08/13 20:26:27
*/
package com.canon.cusa.s21.framework.ZYP.constant.cdtable;

/**
 * <p>
 * This interface provides the logical code names as constants for SVC_TASK_STS table.
 * </p>
 *
 * @author $Author$
 * @version $Revision$
 */
public interface SVC_TASK_STS {

String SAVED = "00";
String OPEN = "10";
String REOPEN = "12";
String WAITING_FOR_CUSTOMER_ACTION = "13";
String PENDING_INSTALL = "16";
String PENDING_PARTS = "17";
String PENDING_PO = "18";
String CREDIT_HOLD = "19";
String TBO = "20";
String SCHEDULED = "25";
String ASSIGNED = "30";
String IN_ROUTE = "40";
String ARRIVED = "45";
String IN_PROCESS = "80";
String COMPLETED = "90";
String PENDING_CHARGE = "91";
String CLOSED = "95";
String CANCELLED = "99";
String DEBRIEF_ERROR = "E1";
String NOTIFY_VENDOR = "21";
String VENDOR_ACKNOWLEDGED = "22";

//No use code but old source refers to them.
String CONTINUOUS_OPEN = "11";
String WAITING_FOR_CREDIT_APPROVAL = "20";
String APPROVED = "21";
String DISPATCHED = "40";
String CUSTOMER_APPROVED = "51";
String CUSTOMER_REJECTED = "52";
String IN_PLANNING = "22";
String AUTO_SCHEDULE = "23";
String WAITING_FOR_CUSTOMER_APPROVAL = "50";
String CUSTOMER_CANCELLED = "98";

}
