/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0150.constant;

/**
 * <pre>
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 * 04/18/2022   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWCL0150Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NWCL0150";

    /**
     * Eligible Account and Bill To area Table
     */
    public static final String TABLE_A = "A";

    /**
     * Edit function code
     */
    public static final String FUNCTION_UPDATE = "NWCL0150T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NWCL0150Scrn00";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NWCL0150_CMN_SUBMIT = "NWCL0150Scrn00_CMN_Submit";

    // =================================================
    // Business Button Name
    // =================================================
    /**
     * Common button 1 id
     */
    public static final String BTN_CMN_BTN_1_ID = "btn1";

    /**
     * Common button 1 name
     */
    public static final String BTN_CMN_BTN_1_NAME = "CMN_Save";

    /**
     * Common button 1 value
     */
    public static final String BTN_CMN_BTN_1_VAL = "";

    /**
     * Common button 2 id
     */
    public static final String BTN_CMN_BTN_2_ID = "btn2";

    /**
     * Common button 2 name
     */
    public static final String BTN_CMN_BTN_2_NAME = "CMN_Submit";

    /**
     * Common button 2 value
     */
    public static final String BTN_CMN_BTN_2_VAL = "Submit";

    /**
     * Common button 3 id
     */
    public static final String BTN_CMN_BTN_3_ID = "btn3";

    /**
     * Common button 3 name
     */
    public static final String BTN_CMN_BTN_3_NAME = "Add_NewLine";

    /**
     * Common button 3 value
     */
    public static final String BTN_CMN_BTN_3_VAL = "";

    /**
     * Common button 4 id
     */
    public static final String BTN_CMN_BTN_4_ID = "btn4";

    /**
     * Common button 4 name
     */
    public static final String BTN_CMN_BTN_4_NAME = "CMN_Approve";

    /**
     * Common button 4 value
     */
    public static final String BTN_CMN_BTN_4_VAL = "";

    /**
     * Common button 5 id
     */
    public static final String BTN_CMN_BTN_5_ID = "btn5";

    /**
     * Common button 5 name
     */
    public static final String BTN_CMN_BTN_5_NAME = "CMN_Reject";

    /**
     * Common button 5 value
     */
    public static final String BTN_CMN_BTN_5_VAL = "";

    /**
     * Common button 6 id
     */
    public static final String BTN_CMN_BTN_6_ID = "btn6";

    /**
     * Common button 6 name
     */
    public static final String BTN_CMN_BTN_6_NAME = "CMN_Download";

    /**
     * Common button 6 value
     */
    public static final String BTN_CMN_BTN_6_VAL = "";

    /**
     * Common button 7 id
     */
    public static final String BTN_CMN_BTN_7_ID = "btn7";

    /**
     * Common button 7 name
     */
    public static final String BTN_CMN_BTN_7_NAME = "CMN_Delete";

    /**
     * Common button 7 value
     */
    public static final String BTN_CMN_BTN_7_VAL = "";

    /**
     * Common button 8 id
     */
    public static final String BTN_CMN_BTN_8_ID = "btn8";

    /**
     * Common button 8 name
     */
    public static final String BTN_CMN_BTN_8_NAME = "CMN_Clear";

    /**
     * Common button 8 value
     */
    // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//    public static final String BTN_CMN_BTN_8_VAL = "";
    public static final String BTN_CMN_BTN_8_VAL = "Clear";
    // END 2022/04/18 J.Evangelista [QC#59934,MOD]

    /**
     * Common button 9 id
     */
    public static final String BTN_CMN_BTN_9_ID = "btn9";

    /**
     * Common button 9 name
     */
    public static final String BTN_CMN_BTN_9_NAME = "CMN_Reset";

    /**
     * Common button 9 value
     */
    public static final String BTN_CMN_BTN_9_VAL = "Reset";

    /**
     * Common button 10 id
     */
    public static final String BTN_CMN_BTN_10_ID = "btn10";

    /**
     * Common button 10 name
     */
    public static final String BTN_CMN_BTN_10_NAME = "CMN_Return";

    /**
     * Common button 10 value
     */
    public static final String BTN_CMN_BTN_10_VAL = "Return";
    // =================================================
    // setNameForMessage
    // ================================================
    /**
     * CPO_ORD_NUM
     */
    public static final String CPO_ORD_NUM = "Order Number";

    /**
     * INVOICE_NUMBER
     */
    public static final String INVOICE_NUMBER = "Invoice Number";

    /**
     * CFS_LEASE_PKG_HLD_FLG
     */
    public static final String CFS_LEASE_PKG_HLD_FLG = "Hold Status";

    /**
     * LEASE_PKG_CRAT_FLG
     */
    public static final String LEASE_PKG_CRAT_FLG = "Package Created";
}
