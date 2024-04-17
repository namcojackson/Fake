/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0120.constant;

/**
 * Class name: Screen Component ID : NFAL0120Constant
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public interface NFAL0120Constant {

    /** 0:E, 1:W, 2:OK */
    static final int ERROR = 0;

    /** 0:E, 1:W, 2:OK */
    static final int WARNING = 1;

    /** 0:E, 1:W, 2:OK */
    static final int OK = 2;

    /** Error sign for String */
    static final String ERROR_STR = "E";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    static final String[] BTN_CMN_BLANK1 = {"btn1", "", "" };

    /** Function Button 2 */
    static final String[] BTN_CMN_SUBMIT = {"btn2", "", "" };

    /** Function Button 3 */
    static final String[] BTN_CMN_BLANK3 = {"btn3", "", "" };

    /** Function Button 4 */
    static final String[] BTN_CMN_BLANK4 = {"btn4", "", "" };

    /** Function Button 5 */
    static final String[] BTN_CMN_BLANK5 = {"btn5", "", "" };

    /** Function Button 6 */
    static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    static final String[] BTN_CMN_BLANK7 = {"btn7", "", "" };

    /** Function Button 8 */
    static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    static final String[] BTN_CMN_RESET = {"btn9", "", "" };

    /** Function Button 10 */
    static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };
    // START 2016/12/22 E.Kameishi [QC#16730,ADD]
    /** Function Button 10 */
    static final String[] BTN_INQUIRY = {"InquiryBtn", "", "" };
    // END 2016/12/22 E.Kameishi [QC#16730,ADD]  
    /** Screen ID */
    static final String SCRN_ID = "NFAL0120Scrn00";

    /**
     * Business ID
     */
    static final String BIZ_ID = "NFAL0120";

    /** Blank */
    static final String BLANK = new String("");

    /** System Source Code for A/R reclass */
    static final String SYS_SRC_CD_AR_RECLASS = "NFC";

    /** Transaction Code for A/R reclass */
    static final String TRX_CD_AR_RECLASS = "170";

    /** Transaction Reason Code for A/R reclass */
    static final String TRX_RSN_CD_AR_RECLASS = "01";

    /** GL Date */
    static final String GL_DT = "Accounting Date";
    // START 2016/10/26 E.Kameishi [QC#14177,ADD]
    /** Search Option "INVTY_TRX_PK" */
    static final String INVTY_TRX_PK = "INVTY_TRX_PK";

    /** Search Option "RCPT_NUM"*/
    static final String RCPT_NUM = "RCPT_NUM";
    // END 2016/10/26 E.Kameishi [QC#14177,ADD]

    // START 2017/11/30 [QC#12525, ADD]
    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_ACCT = "COA_ACCT";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_ACCT = "COA_ACCT_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_ACCT = "COA_ACCT_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_ACCT = "COA_ACCT_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_ACCT = "Natural Account Search Popup";

    /** Header Code of Label Name for NMAL6050 : Natural Account Code */
    public static final String HDR_CD_LB_NM_FOR_ACCT = "Natural Account Code";

    /** Header Name of Label Name for NMAL6050 : Natural Account Name */
    public static final String HDR_NM_LB_NM_FOR_ACCT = "Natural Account Name";

    /** Detail Code of Label Name for NMAL6050 : Natural Account Code */
    public static final String DTL_CD_LB_NM_FOR_ACCT = "Natural Account Code";

    /** Detail Name of Label Name for NMAL6050 : Natural Account Name */
    public static final String DTL_NM_LB_NM_FOR_ACCT = "Natural Account Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_AFFL = "COA_AFFL";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_AFFL = "COA_AFFL_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_AFFL = "COA_AFFL_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_AFFL = "COA_AFFL_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_AFFL = "Intercompany Code Search Popup";

    /** Header Code of Label Name for NMAL6050 : Intercompany Code Code */
    public static final String HDR_CD_LB_NM_FOR_AFFL = "Intercompany Code";

    /** Header Name of Label Name for NMAL6050 : Intercompany Code Name */
    public static final String HDR_NM_LB_NM_FOR_AFFL = "Intercompany Name";

    /** Detail Code of Label Name for NMAL6050 : Intercompany Code Code */
    public static final String DTL_CD_LB_NM_FOR_AFFL = "Intercompany Code";

    /** Detail Name of Label Name for NMAL6050 : Intercompany Code Name */
    public static final String DTL_NM_LB_NM_FOR_AFFL = "Intercompany Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_BR = "COA_BR";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_BR = "COA_BR_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_BR = "COA_BR_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_BR = "COA_BR_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_BR = "Branch Search Popup";

    /** Header Code of Label Name for NMAL6050 : Branch Code */
    public static final String HDR_CD_LB_NM_FOR_BR = "Branch Code";

    /** Header Name of Label Name for NMAL6050 : Branch Name */
    public static final String HDR_NM_LB_NM_FOR_BR = "Branch Name";

    /** Detail Code of Label Name for NMAL6050 : Branch Code */
    public static final String DTL_CD_LB_NM_FOR_BR = "Branch Code";

    /** Detail Name of Label Name for NMAL6050 : Branch Name */
    public static final String DTL_NM_LB_NM_FOR_BR = "Branch Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_CC = "COA_CC";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_CC = "COA_CC_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_CC = "COA_CC_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_CC = "COA_CC_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_CC = "Cost Center Search Popup";

    /** Header Code of Label Name for NMAL6050 : Cost Center Code */
    public static final String HDR_CD_LB_NM_FOR_CC = "Cost Center Code";

    /** Header Name of Label Name for NMAL6050 : Cost Center Name */
    public static final String HDR_NM_LB_NM_FOR_CC = "Cost Center Name";

    /** Detail Code of Label Name for NMAL6050 : Cost Center Code */
    public static final String DTL_CD_LB_NM_FOR_CC = "Cost Center Code";

    /** Detail Name of Label Name for NMAL6050 : Cost Center Name */
    public static final String DTL_NM_LB_NM_FOR_CC = "Cost Center Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_CH = "COA_CH";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_CH = "COA_CH_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_CH = "COA_CH_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_CH = "COA_CH_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_CH = "Sales Channel Search Popup";

    /** Header Code of Label Name for NMAL6050 : Sales Channel Code */
    public static final String HDR_CD_LB_NM_FOR_CH = "Sales Channel Code";

    /** Header Name of Label Name for NMAL6050 : Sales Channel Name */
    public static final String HDR_NM_LB_NM_FOR_CH = "Sales Channel Name";

    /** Detail Code of Label Name for NMAL6050 : Sales Channel Code */
    public static final String DTL_CD_LB_NM_FOR_CH = "Sales Channel Code";

    /** Detail Name of Label Name for NMAL6050 : Sales Channel Name */
    public static final String DTL_NM_LB_NM_FOR_CH = "Sales Channel Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_EXTN = "COA_EXTN";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_EXTN = "COA_EXTN_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_EXTN = "COA_EXTN_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_EXTN = "COA_EXTN_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_EXTN = "Business Unit Search Popup";

    /** Header Code of Label Name for NMAL6050 : Business Unit Code */
    public static final String HDR_CD_LB_NM_FOR_EXTN = "Business Unit Code";

    /** Header Name of Label Name for NMAL6050 : Business Unit Name */
    public static final String HDR_NM_LB_NM_FOR_EXTN = "Business Unit Name";

    /** Detail Code of Label Name for NMAL6050 : Business Unit Code */
    public static final String DTL_CD_LB_NM_FOR_EXTN = "Business Unit Code";

    /** Detail Name of Label Name for NMAL6050 : Business Unit Name */
    public static final String DTL_NM_LB_NM_FOR_EXTN = "Business Unit Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_PROD = "COA_PROD";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_PROD = "COA_PROD_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_PROD = "COA_PROD_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_PROD = "COA_PROD_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_PROD = "Product Code Search Popup";

    /** Header Code of Label Name for NMAL6050 : Product Code Code */
    public static final String HDR_CD_LB_NM_FOR_PROD= "Product Code";

    /** Header Name of Label Name for NMAL6050 : Product Code Name */
    public static final String HDR_NM_LB_NM_FOR_PROD = "Product Name";

    /** Detail Code of Label Name for NMAL6050 : Product Code Code */
    public static final String DTL_CD_LB_NM_FOR_PROD = "Product Code";

    /** Detail Name of Label Name for NMAL6050 : Product Code Name */
    public static final String DTL_NM_LB_NM_FOR_PROD = "Product Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_PROJ = "COA_PROJ";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_PROJ = "COA_PROJ_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_PROJ = "COA_PROJ_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_PROJ = "COA_PROJ_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_PROJ = "Merchandise Type Search Popup";

    /** Header Code of Label Name for NMAL6050 : Merchandise Type Code */
    public static final String HDR_CD_LB_NM_FOR_PROJ = "Merchandise Type Code";

    /** Header Name of Label Name for NMAL6050 : Merchandise Type Name */
    public static final String HDR_NM_LB_NM_FOR_PROJ = "Merchandise Type Name";

    /** Detail Code of Label Name for NMAL6050 : Merchandise Type Code */
    public static final String DTL_CD_LB_NM_FOR_PROJ = "Merchandise Type Code";

    /** Detail Name of Label Name for NMAL6050 : Merchandise Type Name */
    public static final String DTL_NM_LB_NM_FOR_PROJ = "Merchandise Type Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_JRNL_CATG = "JRNL_CATG";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_JRNL_CATG = "JRNL_CATG_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_JRNL_CATG = "JRNL_CATG_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_JRNL_CATG = "JRNL_CATG_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_JRNL_CATG = "Journal Category Search Popup";

    /** Header Code of Label Name for NMAL6050 : Journal Category Code */
    public static final String HDR_CD_LB_NM_FOR_JRNL_CATG = "Journal Category Code";

    /** Header Name of Label Name for NMAL6050 : Journal Category Name */
    public static final String HDR_NM_LB_NM_FOR_JRNL_CATG = "Journal Category Name";

    /** Detail Code of Label Name for NMAL6050 : Journal Category Code */
    public static final String DTL_CD_LB_NM_FOR_JRNL_CATG = "Journal Category Code";

    /** Detail Name of Label Name for NMAL6050 : Journal Category Name */
    public static final String DTL_NM_LB_NM_FOR_JRNL_CATG = "Journal Category Name";
    // END 2017/11/30 [QC#12525, ADD]
}
