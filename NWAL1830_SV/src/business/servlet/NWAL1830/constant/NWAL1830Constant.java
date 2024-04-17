/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NWAL1830Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1830Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1830";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1830Scrn00";

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

    // --------------------------------
    // button
    // --------------------------------
    /** button: Mass Apply */
    public static final String BTN_MASS_APPLY = "MassApply";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Details require more than 1 row.  Please enter. */
    public static final String NWAM0812E = "NWAM0812E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Function
    // --------------------------------
    /** Read Only User*/
    public static final String NWAL1830T010 = "NWAL1830T010";

    /** Entry User */
    public static final String NWAL1830T020 = "NWAL1830T020";

    // --------------------------------
    // Radio Button Value
    // --------------------------------
    /** New Order */
    public static final BigDecimal RADIO_VAL_NEW_ORD = BigDecimal.ZERO;

    /** Existing Order */
    public static final BigDecimal RADIO_VAL_EXST_ORD = BigDecimal.ONE;

    //--------------------------------
    // ORD_LINE_VAL_SET Key
    //--------------------------------
    /** ORD_LINE_VAL_SET Key: Loan Conversion Line */
    public static final String ORD_LINE_VAL_SET_KEY = "LOAN_CONV_LINE";

    //--------------------------------
    // Order Header Status Name 
    //--------------------------------
    /** ENTERED */
    public static final String HEADER_STS_NM_ENTERED = "ENTERED";

    //--------------------------------
    // Order Line Status Name 
    //--------------------------------
    /** ON LOAN */
    public static final String LINE_STS_NM_ON_LOAN = "ON LOAN";

    // ----------- Common ** OnChange Event-----------
    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 12 */
    public static final int IDX_12 = 12;

    /** Index Number 13 */
    public static final int IDX_13 = 13;

    /** Index Number 13 */
    public static final int IDX_15 = 15;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Index Number 33 */
    public static final int IDX_33 = 33;

    /** Index Number 50 */
    public static final int IDX_50 = 50;

    /** Percent */
    public static final String PERCENT = "%"; // S21_NA#8393 Add

}
