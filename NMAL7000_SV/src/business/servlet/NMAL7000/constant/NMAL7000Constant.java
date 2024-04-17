/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000.constant;

/**
 *<pre>
 * NMAL7000Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7000Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7000";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7000Scrn00";

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_COLUMN_NUM = 4;

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

    /** Upload CSV Id. Price List Equipment Work */
    public static final String UPLD_CSV_ID_EQUIP_WRK = "NMA7010001";

    /** Upload CSV Id. Price List Service Work */
    public static final String UPLD_CSV_ID_SVC_WRK = "NMA7010002";

    /** Upload CSV Id. Price List Service Tier Work */
    public static final String UPLD_CSV_ID_SVC_TIER_WRK = "NMA7010003";

    /** Upload CSV Id. Price List Additional Charge Work */
    public static final String UPLD_CSV_ID_ADDL_CHRG_WRK = "NMA7010004";

    /** Upload CSV Id. Price List Supply Program Work */
    public static final String UPLD_CSV_ID_SPLY_PGM_WRK = "NMA7010005";

    /** Upload CSV Id. Price List Lease Rate Work */
    public static final String UPLD_CSV_ID_LEASE_RATE_WRK = "NMA7010006";

    /** Upload CSV Id. Price List Lease Return Work */
    public static final String UPLD_CSV_ID_LEASE_RTRN_WRK = "NMA7010007";

    /** Upload CSV Id. Price List Trade In Work */
    public static final String UPLD_CSV_ID_TI_VAL_WRK = "NMA7010008";

    /** Upload CSV Id. Price Customer Audience Work */
    public static final String UPLD_CSV_ID_CUST_AUDC_WRK = "NMA7020001";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
