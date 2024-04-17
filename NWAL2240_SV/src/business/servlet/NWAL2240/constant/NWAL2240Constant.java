/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240.constant;

/**
 *<pre>
 * NWAL2240Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#5503
 * 2016/07/29   Fujitsu         H.Ikeda         Update          QC#5030
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 *</pre>
 */
public class NWAL2240Constant {

    /**
     * Business ID -- NWAL2240
     */
    public static final String BUSINESS_ID = "NWAL2240";

    /**
     * Screen ID.
     */
    public static final String SCREEN_ID = "NWAL2240Scrn00";

    /** Common button 1 */
    public static final String[] BTN_CMN_BTN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Common button 2 */
    public static final String[] BTN_CMN_BTN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Common button 3 */
    public static final String[] BTN_CMN_BTN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Common button 4 */
    public static final String[] BTN_CMN_BTN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Common button 5 */
    public static final String[] BTN_CMN_BTN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Common button 6 */
    public static final String[] BTN_CMN_BTN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Common button 7 */
    public static final String[] BTN_CMN_BTN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Common button 8 */
    public static final String[] BTN_CMN_BTN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 9 */
    public static final String[] BTN_CMN_BTN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Common button 10 */
    public static final String[] BTN_CMN_BTN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Add Button */
    public static final String[] BTN_ADD = {"Add_Row", "Add_Row", "Add" };

    /** Add Button */
    public static final String[] BTN_DELETE = {"Delete_Row", "Delete_Row", "Delete" };

    /** Install Technician (>>) Button */
    public static final String[] BTN_TECH = {"Search_TechNm", "Search_TechNm", ">>" };

    // START 2016/04/26 K.Kojima [QC#5503,ADD]
    /** Search Button */
    public static final String[] BTN_SEARCH_ORDER = {"Search_Order", "Search_Order", "Search" };

    // END 2016/04/26 K.Kojima [QC#5503,ADD]

    /**
     * ERROR_CODE_E -- E
     */
    public static final String ERROR_CODE_E = "E";

    /**
     * FUNC_CD_SRCH -- 20
     */
    public static final String FUNC_CD_SRCH = "20";

    /** FUNC_CD_UPDT -- 40 */
    public static final String FUNC_CD_UPDT = "40";

    /**
     * CMN_CLOSE -- CMN_Close
     */
    public static final String CMN_CLOSE = "CMN_Close";

    // //////// Indexes of Popup parameter //////////
    /** Popup parameter */
    public static final int POP_PAR_0 = 0;

    /** Popup parameter */
    public static final int POP_PAR_1 = 1;

    /** Popup parameter */
    public static final int POP_PAR_2 = 2;

    /** Popup parameter */
    public static final int POP_PAR_3 = 3;

    /** Popup parameter */
    public static final int POP_PAR_4 = 4;

    /** Popup parameter */
    public static final int POP_PAR_5 = 5;

    /** Popup parameter */
    public static final int POP_PAR_6 = 6;

    /** Popup parameter */
    public static final int POP_PAR_7 = 7;

    /** Popup parameter */
    public static final int POP_PAR_8 = 8;

    /** Popup parameter */
    public static final int POP_PAR_9 = 9;

    /** Popup parameter */
    public static final int POP_PAR_10 = 10;

    /** Popup parameter */
    public static final int POP_PAR_11 = 11;

    /** Popup parameter */
    public static final int POP_PAR_12 = 12;

    /** Popup parameter */
    public static final int POP_PAR_13 = 13;

    /** Popup parameter */
    public static final int POP_PAR_14 = 14;

    /** Popup parameter */
    public static final int POP_PAR_15 = 15;

    /** Popup parameter */
    public static final int POP_PAR_16 = 16;

    /** Popup parameter */
    public static final int POP_PAR_17 = 17;

    /** Popup parameter */
    public static final int POP_PAR_18 = 18;

    /** Popup parameter */
    public static final int POP_PAR_19 = 19;

    /** Popup parameter */
    public static final int POP_PAR_20 = 20;

    /** Popup parameter */
    public static final int POP_PAR_25 = 25;

    /** Contact List Max Count */
    public static final int MAX_CNT_CONTACT_LIST = 200;

    /**
     * Error code that when we call BMsg#getMessageKind on error
     * occurred
     */
    public static final String MESSAGE_KIND_ERR = "E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** [@] should be smaller than [@]. */
    public static final String ZZZM9010E = "ZZZM9010E";

    /** Order Number has not been entered */
    public static final String NWAM0014E = "NWAM0014E";

    /** The details of the process target have not been selected. */
    public static final String NWAM0504E = "NWAM0504E";

    /** Configuration Category Type has not been entered. */
    public static final String NWAM0662E = "NWAM0662E";

    /** Technician Code has not been entered. */
    public static final String NWAM0663E = "NWAM0663E";

    /** The specified format is incorrect. It must be @ */
    public static final String NWAM0664E = "NWAM0664E";

    /** Entered time is incorrect. */
    public static final String NWAM0665E = "NWAM0665E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Time (To) must be greater than Time (From) */
    public static final String NWAM0749E = "NWAM0749E";

    /** It is incorrect magnitude relation of Date. */
    public static final String NWAM0746E = "NWAM0746E";

    /** A past date is already set for RDD. */
    public static final String NWAM0030E = "NWAM0030E";

    /** Mandatory Error. */
    public static final String ZZM9000E = "ZZM9000E";

    // 2016/07/29 QC#5030 Add Start
    /** Please select the AM or PM */
    public static final String NWAM0869E = "NWAM0869E";

    /** Please select for Radio button. */
    public static final String NWAM0827E = "NWAM0827E";
    // 2016/07/29 QC#5030 Add End


    /** Comma */
    public static final String COMMA = ",";

    /** Tab anchor Install */
    public static final String TAB_INSTALL = "Install";

    /** Tab anchor Survey */
    public static final String TAB_SURVEY = "Survey";

    /** Tab anchor Contact */
    public static final String TAB_CONTACT = "Contact";

    /** AM */
    public static final String AM_CD = "0";

    /** PM */
    public static final String PM_CD = "1";

    /** noon */
    public static final int NOON = 1200;

    /** Time check Parttern */
    public static final String CHK_TIME_PATTERN = "(0[0-9]|1[0-1])[0-5][0-9]";

    /** Phone check Pattern */
    public static final String CHK_PHONE_PATTERN = "(\\d{10}|\\d{3}-\\d{3}-\\d{4})";

    /** EMail check Pattern */
    // 2016/10/06 QC#1443 Del --------------
    //public static final String CHK_EMAIL_PATTERN = "^[a-zA-Z0-9]+([\\w\\.\\-]*[\\w\\-])*@([\\w\\-]+\\.)+[\\w\\-]+$";

    /** EXT check Pattern */
    public static final String CHK_EXT_PATTERN = "\\d{1,4}";

    /** Time Format */
    public static final String TIME_FORMAT = "9999";

    /** Tel Format */
    public static final String TEL_FORMAT = "9999999999 or 999-999-9999";

    /** E-Mail Format */
    public static final String EMAIL_FORMAT = "aaa@bbb";

    /** EXT FORMAT */
    public static final String EXT_FORMAT = "9999";

    /** Tab EnableFlg Off */
    public static final String TAB_ENABLE_FLG_OFF = "N";

    /** Tab EnableFlg On */
    public static final String TAB_ENABLE_FLG_ON = "Y";

    /** DI Tab Delivery Display Flag Off(Install) */
    public static final String DERY_DISP_FLG_OFF = "N";

    /** DI Tab Delivery Display Flag Off(Delivery) */
    public static final String DERY_DISP_FLG_ON = "Y";

    /** Button Display Flag */
    public static final String BUTTON_DISP_FLG_ON = "Y";

    /** Decimal digits Number 0. */
    public static final int DECIMAL_DIGITS_NUM_ZERO = 0;

    /** Time Character Length 4. */
    public static final int TIME_CHAR_LEN = 4;

    /** Technician Label Name Install Technician */
    public static final String TECHNICIAN_LABEL_NAME_INS = "Install Technician";

    /** Technician Label Name De-install Technician */
    public static final String TECHNICIAN_LABEL_NAME_DE_INS = "De-install Technician";

    /** colon */
    public static final String CLN = ":";

    // START 2016/04/26 K.Kojima [QC#5503,ADD]
    /** XX_EDT_MODE_CD : No Edit */
    public static final String XX_EDT_MODE_CD_NO_EDIT = "0";
    // END 2016/04/26 K.Kojima [QC#5503,ADD]

}
