/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC400001.constant;

/**
 * <pre>
 * NWZC4000 : Return Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         S.Yoshida       Create          N/A
 * 2018/11/05   Fujitsu         K.Kato          Update          S21_NA#29015
 *</pre>
 */
public class NWZC400001Constant {

    // -- Process Mode ---------------------
    /** Request Type : Order Validation */
    public static final String REQ_TYPE_ORD_VLD = "1";

    /** Request Type : ReCalculate */
    public static final String REQ_TYPE_RECAL = "6";

    // -- Message Code --------------------
    /** Data Company Code is not entered. */
    public static final String NWZM0001E = "NWZM0001E";
    /** Sales Date is not entered. */
    public static final String NWZM0445E = "NWZM0445E";
    /** ValidationType is not valid. */
    public static final String NWZM0245E = "NWZM0245E";
    /** Order Number is not entered. */
    public static final String NWZM0002E = "NWZM0002E";
    /** Order Line Number is not entered. */
    public static final String NWZM0003E = "NWZM0003E";
    /** Order Line Sub Number is not entered. */
    public static final String NWZM0004E = "NWZM0004E";
    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";
    /** It failed to update the HLD. */
    public static final String NWZM0080E = "NWZM0080E";
    /** "PMT_TERM_CR_CARD" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NWZM1277E = "NWZM1277E";

    // -- Other ---------------------------
    /** Check Point : Order Validation */
    public static final String CHK_PNT_OV = "1";

    /** SET Item Header */
    public static final String SET_ITEM_PARENT_NUM = "000";

    // 2018/11/05 S21_NA#29015 Add Start
    public static final String SUB_SYS_ID = "NWZ";

    public static final String PROCESS_ID = "OM";

    public static final String DOCUMENT_TYPE = "OM";

    public static final String EVENT_ID = "Booked";
    // 2018/11/05 S21_NA#29015 Add End

}
