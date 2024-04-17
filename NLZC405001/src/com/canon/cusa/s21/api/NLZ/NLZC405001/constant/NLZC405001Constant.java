/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC405001.constant;

/**
 * <pre>
 * Update Delivery/Pickup from eCarriers API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/14/2016   CSAI            Y.Imazu         Create          CSA
 * </pre>
 */
public class NLZC405001Constant {

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Global Company Code is not set. */
    public static final String NLZM2259E = "NLZM2259E";

    /** Sales Date is not set. */
    public static final String NLZM2079E = "NLZM2079E";

    /** Inbound Outbound Code is not set. */
    public static final String NLZM2387E = "NLZM2387E";

    /** Incorrect Inbound Outbound Code. */
    public static final String NLZM2388E = "NLZM2388E";

    /** CPO Number and Trx Header Number and Pro Number are not set. */
    public static final String NLZM2405E = "NLZM2405E";

    /** CPO Number and Trx Header Number are not set. */
    public static final String NLZM2406E = "NLZM2406E";

    /** Carrier Reason Code is not set. */
    public static final String NLZM2389E = "NLZM2389E";

    /** Data does not exist in Carrier Reason. */
    public static final String NLZM2390E = "NLZM2390E";

    /** Failed to retrieve Default Carrier Code. */
    public static final String NLZM2407E = "NLZM2407E";

    /** The target "Shipping Order" cannot be found. */
    public static final String NLZM2391E = "NLZM2391E";

    /** It failed to update Shipping Order Schedule. */
    public static final String NLZM2393E = "NLZM2393E";

    /** Data does not exist in Shipping Order Schedule. */
    public static final String NLZM2392E = "NLZM2392E";

    /** It failed to register Shipping Order Schedule Tracking. */
    public static final String NLZM2394E = "NLZM2394E";

    /** It failed to update DS Shipping Order Detail. */
    public static final String NLZM2396E = "NLZM2396E";

    /** Data does not exist in DS Shipping Order Detail. */
    public static final String NLZM2395E = "NLZM2395E";

    /** The target "RWS" cannot be found. */
    public static final String NLZM2397E = "NLZM2397E";

    /** It failed to update RWS Schedule Detail. */
    public static final String NLZM2399E = "NLZM2399E";

    /** Data does not exist in RWS Schedule Detail. */
    public static final String NLZM2398E = "NLZM2398E";

    /**
     * The data could not be registered to the Receiving Work Sheet
     * Schedule Detail Tracking.
     */
    public static final String NLZM2307E = "NLZM2307E";

    /** Data does not exist in Return Tracking Status. */
    public static final String NLZM2400E = "NLZM2400E";

    /** It failed to register Return Tracking Notification Work. */
    public static final String NLZM2401E = "NLZM2401E";

    /** It failed to update DS RWS Detail. */
    public static final String NLZM2403E = "NLZM2403E";

    /** Data does not exist in DS RWS Detail. */
    public static final String NLZM2402E = "NLZM2402E";

    /** Retail Warehouse Code is not set in RWS. */
    public static final String NLZM2404E = "NLZM2404E";

    /** Input parameter[@], Value[@] */
    public static final String NAZM0280E = "NAZM0280E";

    /*****************************************************************
     * Parameter Field Name
     ****************************************************************/

    /** Global Company Code */
    public static final String PARAM_GLBL_CMPY_CD = "Global Company Code";

    /** Sales Date */
    public static final String PARAM_SLS_DT = "Sales Date";

    /** Inbound Outbound Code */
    public static final String PARAM_INBD_OTBD_CD = "Inbound Outbound Code";

    /** CPO Number */
    public static final String PARAM_CPO_ORD_NUM = "CPO Number";

    /** CPO Line Number */
    public static final String PARAM_CPO_DTL_LINE_NUM = "CPO Line Number";

    /** CPO Line Sub Number */
    public static final String PARAM_CPO_DTL_LINE_SUB_NUM = "CPO Line Sub Number";

    /** Transaction Header Number */
    public static final String PARAM_TRX_HDR_NUM = "Transaction Header Number (SO# or RWS#)";

    /** Transaction Line Number */
    public static final String PARAM_TRX_LINE_NUM = "Transaction Line Number (SO Line# or RWS Line#)";

    /** Pro Number */
    public static final String PARAM_PRO_NUM = "Pro Number";

    /** Carreir Reason Code */
    public static final String PARAM_CARR_RSN_CD = "Carreir Reason Code";

    /** Carrier Comment Text */
    public static final String PARAM_CARR_CMNT_TXT = "Carrier Comment Text";

    /** Update User ID */
    public static final String PARAM_UPD_USR_ID = "Update User ID";

    /** Update Timestamp */
    public static final String PARAM_UPD_TS = "Update Timestamp";

    /** Request Date */
    public static final String PARAM_XX_RQST_DT = "Request Date";

    /*****************************************************************
     * Constant
     ****************************************************************/

    /** Varchar Constant : Default Carrier Code */
    public static final String NLZC4050_DEF_CARR_CD = "NLZC4050_DEF_CARR_CD";

    /** Massage SubString : 10 */
    public static final int MSG_SUBSTRING_LENGTH = 10;

    /** EDI Time Code : Local Time */
    public static final String EDI_TM_CD_LT = "LT";
}
