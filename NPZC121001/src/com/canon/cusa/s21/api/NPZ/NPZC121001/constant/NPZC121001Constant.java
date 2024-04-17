/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC121001.constant;

/**
 * <pre>
 * Tech to Tech Transfer API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/07/2015   Hitachi         K.Kasai         Create
 * 02/12/2016   Hitachi         T.Iwamoto       Update          QC#3727
 *</pre>
 */
public class NPZC121001Constant {

    /** DS_COND_CONST GroupId : CLICK_COMMON */
    public static final String CONST_GRP_ID_CLICK_COMMON = "CLICK_COMMON";

    /** DS_COND_CONST GroupId : NPZC1210 */
    public static final String CONST_GRP_ID_NPZC1210 = "NPZC1210";

    /** DS_COND_CONST Code : DATE_FORMAT */
    public static final String CONST_GRP_ID_DATE_FORMAT = "DATE_FORMAT";

    /** DS_COND_CONST Code : TRANSFER_STATUS */
    public static final String CONST_CD_TRANSFER_STATUS = "TRANSFER_STATUS";

    /** DS_COND_CONST Code : REQUEST_STATUS */
    public static final String CONST_CD_REQUEST_STATUS = "REQUEST_STATUS";

    /** Message Parameter : GLBL_CMPY_CD */
    public static final String MSG_PRM_GLBL_CMPY_CD = "Global Company Code";

    /** Message Parameter : TO_INVTY_LOC_CD */
    public static final String MSG_PRM_TO_INVTY_LOC_CD = "Transfer To";

    /** Message Parameter : SLS_DT */
    public static final String MSG_PRM_SLS_DT = "Sales Date";

    /** Message Parameter : MDSE_CD */
    public static final String MSG_PRM_MDSE_CD = "Part Number";

    /** Message Parameter : SHIP_QTY */
    public static final String MSG_PRM_SHIP_QTY = "Quantity";

    /** Message Parameter : FROM_INVTY_LOC_CD */
    public static final String MSG_PRM_FROM_INVTY_LOC_CD = "Transfer From";

    /** Message Parameter : INVTY_AVAL_QTY */
    public static final String MSG_PRM_INVTY_AVAL_QTY = "Inventory Available Quantity";

    // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
    /** Message Parameter : REQUEST DATE */
    public static final String MSG_PRM_REQUEST_DATE = "Request Date";
    // END 02/12/2016 T.Iwamoto [QC#3727, ADD]

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires single byte numeric character. */
    public static final String ZZM9012E = "ZZM9012E";

    /** [@] field exceeded maximum value. */
    public static final String ZZM9002E = "ZZM9002E";

    /** [@] is a negative value. */
    public static final String NPAM0070E = "NPAM0070E";

    /** fromInvtyLocCd is not allocated for This shipped quantity. */
    public static final String NPZM0176E = "NPZM0176E";

    /**
     * The same value cannot be entered in toInvtyLocCd and
     * fromInvtyLocCd.
     */
    public static final String NPZM0177E = "NPZM0177E";

    /** Inventory with the entered Key does not exist. */
    public static final String NPZM0178E = "NPZM0178E";

    /** Failed to insert the CLICK_TECH_TRNSF_TO. */
    public static final String NPZM0209E = "NPZM0209E";

    // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";
    // END 02/12/2016 T.Iwamoto [QC#3727, ADD]

    /** Parameter Process Type : 1 */
    public static final String PRM_PROC_TP_CRAT = "1";

    /** Parameter Date Type : H */
    public static final String PRM_DATE_TP_HDR = "H";

    /** Parameter Date Type : D */
    public static final String PRM_DATE_TP_DTL = "D";

    /** Parameter Inventory Order Line Number : 001 */
    public static final String PRM_INVTY_ORD_LINE_NUM = "001";

    /** Parameter Request Stock In : I */
    public static final String PRM_RQST_STOCK_IN = "I";

    /** Parameter Request Stock Out : O */
    public static final String PRM_RQST_STOCK_OUT = "O";

    /** System Type Code : Out-Bound */
    public static final String SYS_TP_OTBD = "01";

    /** System Type Code : In-Bound */
    public static final String SYS_TP_INBD = "02";

    /** Transaction Detail Code : Ship Confirmation */
    public static final String TRX_DTL_SHIP_CONF = "12";

    /** Transaction Detail Code : Receive Confirmation */
    public static final String TRX_DTL_RCV_CONF = "13";

    /** Array List Size : 2 */
    public static final int ARRAY_LIST_SIZE = 2;

    /** Date Format : yyyyMMddHHmmss */
    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    /** Max Shipping Quantity Digit : 10 */
    public static final int MAX_SHIP_QTY_DIGIT = 10;

    /** Position Length : 8 */
    public static final int POS_LEN_8 = 8;
}
