/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001.constant;

/**
 * <pre>
 * DS_CONTR_CTRL_CLS constant class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2015   Hitachi         K.Yamada        Create          N/A
 * 12/14/2015   Hitachi         T.Tsuchida      Update          QC#1577
 * 04/08/2016   Hitachi         A.Kohinata      Update          QC#6622
 * 04/25/2016   Hitachi         A.Kohinata      Create          QC#1088
 * 04/25/2017   Hitachi         Y.Takeno        Update          RS#7237
 * 2017/11/14   Hitachi         K.Kojima        Update          QC#21886
 * 2019/01/08   Hitachi         S.Kitamura      Update          QC#29646
 * 2019/01/17   Hitachi         S.Kitamura      Update          QC#29952
 * </pre>
 */
public interface DS_CONTR_CTRL_STS {

String SUBMITED = "SBMT";
String ACTIVE = "ACTV";
String BILLING_HOLD = "BILH";
String CANCELLED = "CANC";
String HOLD = "CTRH";
String DRAFT = "DRFT";
String ENTERED = "ENTR";
String EXPIRED = "EXPD";
String EXPIRED_HOLD = "EXPH";
String QA_HOLD = "QAHD";
String RENEWAL_HOLD = "RNWH";
String SINGED = "SIGD";
String SYSTEM_HOLD = "SYSH";
String TERMINATED = "TRMD";
String TERMINATED_HOLD = "TRMH";
String RENEWAL_HOLD_FOR_PO = "RNPO";
String QA_HOLD_PENDING_APPROVAL = "QAHP";
String ORDER = "ORDR";
String RENEWAL_HOLD_SYSTEM_HOLD = "RNWS";
String RENEWAL_HOLD_FOR_PO_SYSTEM_HOLD = "RNPS";
String LINK_ERROR_BILLING_HOLD = "LEBH";
String LINK_ERROR_HOLD = "LEHD";
String LINK_ERROR_SYSTEM_HOLD = "LESH";
String LINK_ERROR_QA_HOLD = "LEQH";
}
