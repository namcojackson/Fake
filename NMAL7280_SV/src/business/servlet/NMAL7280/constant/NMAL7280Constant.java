/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280.constant;

/**
 *<pre>
 * NMAL7280Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 * 2016/07/11   Fujitsu         W.Honda         Update          QC#8477
 * 2017/02/27   Fujitsu         W.Honda         Update          QC#16011
 *</pre>
 */
public class NMAL7280Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7280";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7280Scrn00";

    // Add Start 2017/02/27 QC#16011
    /** Update Authority */
    public static final String UPDATE_AUTHORITY = "NMAL7280T020";
    // Add End 2017/02/27 QC#16011

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // Add Start 2017/02/27 QC#16011
    /** InsertRow_CondGrp */
    public static final String BTN_ADD_COND_GRP= "InsertRow_CondGrp";

    /** DeleteRow_CondGrp */
    public static final String BTN_DEL_COND_GRP= "DeleteRow_CondGrp";
    // Add End 2017/02/27 QC#16011

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** [@] of the entered parameter is not set. */
    public static final String NMAM8133E = "NMAM8133E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** The same Condition# can't be used. */
    public static final String NMAM8294E = "NMAM8294E";

    /** The chronological sequence is wrong. */
    public static final String NMAM8115E = "NMAM8115E";

    /** [@] is not registered. */
    public static final String NMAM0011E = "NMAM0011E";

    /**  @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** Invalid combination.  @ & @  */
    public static final String NMAM0070E = "NMAM0070E";

    /** Error Message */
    public static final String NMAM0050E = "NMAM0050E";

    // --------------------------------
    // button
    // --------------------------------
    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /**  INSERT_Row   */
    public static final String[] BTN_INSERT_ROW = {"Insert_Row", "Insert_Row", "Insert Row" };

    /**  Delete_Row   */
    public static final String[] BTN_DELETE_ROW = {"Delete_Row", "Delete_Row", "Delete Row" };

    /** Condition # */
    public static final int CONDITION_NO_01 = 1;

    /** Condition # */
    public static final int CONDITION_NO_02 = 2;

    /** Condition # */
    public static final int CONDITION_NO_03 = 3;

    /** Condition # */
    public static final int CONDITION_NO_04 = 4;

    /** Condition # */
    public static final int CONDITION_NO_05 = 5;

    /** Condition # */
    public static final int CONDITION_NO_06 = 6;

    /** Condition # */
    public static final int CONDITION_NO_07 = 7;

    /** Condition # */
    public static final int CONDITION_NO_08 = 8;

    /** Condition # */
    public static final int CONDITION_NO_09 = 9;

    /** Condition # */
    public static final int CONDITION_NO_10 = 10;

    /** Condition # */
    public static final int CONDITION_NO_11 = 11;

    /** Condition # */
    public static final int CONDITION_NO_12 = 12;

    /** Condition Size */
    public static final int CONDITION_SIZE = 12;

    // QC#8477 2016/07/11 Add start
    /** PRC_RULE_COND_GRP_CD Max Length */
    public static final int COND_GRP_MAX_LENGTH = 2;
    // QC#8477 2016/07/11 Add end

    /** Detail List Size */
    public static final int DETAIL_LIST_SIZE = 1000;

    /** Opr Or */
    public static final String OPR_AND = "01";

    /** First Set Value */
    public static final String FIRST_SET_VALUE = "";


}
