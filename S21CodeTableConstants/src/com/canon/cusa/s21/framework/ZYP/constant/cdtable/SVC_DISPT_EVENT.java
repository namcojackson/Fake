/*
* Copyright (c) 2011 Canon USA Inc. All rights reserved.
* Original Author: AUTO generated File : S21WDS CodeTable Generator Tool
* Company: Canon USA Inc.
* Date Generated:2015/08/19 12:06:43
*/
package com.canon.cusa.s21.framework.ZYP.constant.cdtable;

/**
 * <p>
 * This interface provides the logical code names as constants for SVC_DISPT_EVENT table.
 * </p>
 *
 * @author $Author$
 * @version $Revision$
 */
public interface SVC_DISPT_EVENT {

String OPEN = "000";
String REOPEN = "001";
String OPEN_BY_TECH = "002";
String ADD_TASK = "100";
String CREATE_FOLLOW_UP_TASK = "110";
String CREATE_INTERRUPT_TASK = "120";
String TSS_HELP = "130";
String VERIFY_PO = "010";
String CREDIT_APPROVAL = "011";
String PENDING_INSTALL = "019";
String TBO = "020";
String SEND_TASK = "021";
String SCHEDULE = "030";
String UNSCHEDULE = "035";
String ASSIGN = "040";
String UNASSIGN = "045";
String IN_ROUTE = "050";
String UN_IN_ROUTE = "055";
String TECH_ARRIVING = "060";
String COMPLETE = "070";
String FORCE_COMPLETE = "075";
String CLOSE = "080";
String FORCE_CLOSE = "085";
String INVOICE = "090";
String DEBRIEF_ERROR = "E01";
String CHARGES_ERROR = "E02";
String AD_RESOLVE_CALL = "800";
String AD_NEED_TECHNICIAN = "810";
String AD_NEED_MORE_TIME = "820";
String CANCEL = "900";
String UPDATE_TASK = "200";
String UPDATE_FSR = "210";
String UPDATE_SCHEDULE = "211";
String UPDATE_ETA = "212";
String UPDATE_DISPATCH = "213";
String UPDATE_VISIT = "214";
String UPDATE_USAGE_AND_METER = "215";
String UPDATE_NOTES = "220";
String CHANGE_TASK_AMOUNT_MANUALLY = "230";
String ALTER_CROSS_SERVICE = "Z10";
String SENT_TO_MDS_CLOUD = "Z11";

//No use code but old source refers to them.
//String CREDIT_APPROVAL = "010";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PARTS_ENTRY = "020";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PRINT_QUOTE = "021";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String QUOTE_APPROVAL = "022";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String QUOTE_REJECT = "023";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String SCHEDULE_FSR = "030";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String ACCEPT_FSR = "031";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UNSCHEDULE_FSR = "035";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String DISPATCH_FSR = "040";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String POST_DISPATCH_FSR = "041";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UNDISPATCH_FSR = "045";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String COMPLETE_FSR = "060";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CONTINUOUS_FSR_CREATE = "061";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String INVOICE_FSR = "070";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String MINIMUN_FSR_INVOICE = "071";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CUSTOMER_CANCELLED = "950";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CANCELLED = "999";
//String UPDATE_TASK = "100";
//String UPDATE_SCHEDULE = "110";
//String UPDATE_ETA = "111";
//String UPDATE_DISPATCH = "120";
//String UPDATE_VISIT = "130";
//String UPDATE_USAGE_AND_METER = "140";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UPDATE_QUOTE = "150";
//String CHANGE_TASK_AMOUNT_MANUALLY = "200";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UPDATE_MEMO = "160";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String FSR_MAINTENANCE = "D41";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CREATE_1ST_FSR = "X10";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UPDATE_TEMP_CUST_NUM = "X20";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PRINT_QUOTE_2 = "X23";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PRINT_QUOTE_3 = "X25";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String REPAIR_ORDER_FORM = "X27";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String SCHEDULE_1ST_FSR = "X30";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UNSCHEDULE_1ST_FSR = "X35";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String ASSIGN_1ST_FSR = "X40";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UNASSIGN_1ST_FSR = "X45";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PARTS_QUOTE_ENTERED = "X50";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String INVOICE_1ST_FSR = "X60";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CLOSE_1ST_FSR = "X70";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String COMPLETE_1ST_FSR = "X80";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CUSTOMER_PICK_UP = "X90";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String FSR_POST_ENTRY = "X95";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String POST_ENTRY_INVOICING = "X97";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CREATE_2ND_FSR = "Y10";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PARTS_QUOTE_ENTERED_2 = "Y15";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PRINT_QUOTE_4 = "Y23";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PRINT_QUOTE_5 = "Y25";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CUSTOMER_ACCEPT = "Y28";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String SCHEDULE_2ND_FSR = "Y30";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UNSCHEDULE_2ND_FSR = "Y35";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String ASSIGN_2ND_FSR = "Y40";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String UNASSIG_2ND_FSR = "Y45";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String INVOICE_2ND_FSR = "Y50";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String PARTS_OK_OR_AVAIL_2ND = "Y60";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CLOSE_2ND_FSR = "Y70";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String COMPLETE_2ND_FSR = "Y80";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CUSTOMER_PICK_UP_2 = "Y90";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CUSTOMER_CANCELLED_2 = "Z50";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String INVOICE_MINIMUM = "Z60";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CUSTOMER_PICK_UP_3 = "Z70";
/** @deprecated FIXME This code is deleted but still exists to avoid build error. */
String CANCELLED_2 = "Z99";
//String ADD_TASK = "001";
}
