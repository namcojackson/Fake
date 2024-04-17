/**
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NWAL1510.constant;

/**
 * <pre>
 * Resource Search  NWAL1510 Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/09   Fujitsu         S.Ohki          Update          S21_NA#1622
 * 2016/07/11   Fujitsu         H.Ikeda         Update          QC#5030
 * 2016/09/20   Fujitsu         R.Nakamura      Update          QC#13738
 * 2016/11/04   Fujitsu         M.Ohno          Update          QC#13738-2
 * 2016/11/07   Fujitsu         Y.Kanefusa      Update          S21_NA#14143
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2017/08/10   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/07/18   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          S21_NA#24022
 * 2023/07/20   Hitachi         T.Fukuta        Create          CSA-QC#61467
 * </pre>
 */
public class NWAL1510Constant {

    /**
     * Business ID -- NWAL1510
     */
    public static final String BUSINESS_ID = "NWAL1510";

    /**
     * Screen ID.
     */
    public static final String SCREEN_ID = "NWAL1510Scrn00";

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
    public static final Integer POP_PAR_0 = 0;

    /** Popup parameter */
    public static final Integer POP_PAR_1 = 1;

    /** Popup parameter */
    public static final Integer POP_PAR_2 = 2;

    /** Popup parameter */
    public static final Integer POP_PAR_3 = 3;

    /** Popup parameter */
    public static final Integer POP_PAR_4 = 4;

    /** Popup parameter */
    public static final Integer POP_PAR_5 = 5;

    /** Popup parameter */
    public static final Integer POP_PAR_6 = 6;

    /** Popup parameter */
    public static final Integer POP_PAR_7 = 7;

    /** Popup parameter */
    public static final Integer POP_PAR_8 = 8;

    /** Popup parameter */
    public static final Integer POP_PAR_9 = 9;

    /** Popup parameter */
    public static final Integer POP_PAR_10 = 10;

    /** Popup parameter */
    public static final Integer POP_PAR_11 = 11;

    /** Popup parameter */
    public static final Integer POP_PAR_12 = 12;

    /** Popup parameter */
    public static final Integer POP_PAR_13 = 13;

    /** Popup parameter */
    public static final Integer POP_PAR_14 = 14;

    /** Popup parameter */
    public static final Integer POP_PAR_15 = 15;

    /** Popup parameter */
    public static final Integer POP_PAR_16 = 16;

    /** Popup parameter */
    public static final Integer POP_PAR_17 = 17;

    /** Popup parameter */
    public static final Integer POP_PAR_18 = 18;

    /** Popup parameter */
    public static final Integer POP_PAR_19 = 19;

    /** Popup parameter */
    public static final Integer POP_PAR_20 = 20;

    /** Popup parameter */
    public static final Integer POP_PAR_21 = 21;

    /** Popup parameter */
    public static final Integer POP_PAR_22 = 22;

//    /** Popup parameter */
//    public static final Integer POP_PAR_25 = 25;

    /** Popup parameter */
    public static final Integer POP_PAR_31 = 31;

    /** Contact List Max Count */
    public static final Integer MAX_CNT_CONTACT_LIST = 200;

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

    /** Install date is a past date. */
    public static final String NWAM0773E = "NWAM0773E";

    /** If [@] is "Yes", please specify [@]. */
    public static final String NWAM0761E = "NWAM0761E";

    /** Email  format is incorrect. */
    public static final String NWAM0762E = "NWAM0762E";

    /** Phone number format is incorrect. Minimum length is 7digit, Maximum length is 20 digit. */
    public static final String NWAM0763E = "NWAM0763E";

    // Del Start 2018/12/14 QC#24022
    ///** Extention length has been exceeded. Maximum length is 4digit. */
    //public static final String NWAM0764E = "NWAM0764E";
    // Del End 2018/12/14 QC#24022

    /** Mandatory Error. */
    public static final String ZZM9000E = "ZZM9000E";

    // 2016/07/11 QC#5030 Add Start
    /** Please select the AM or PM */
    public static final String NWAM0869E = "NWAM0869E";

    /** Please select for Radio button. */
    public static final String NWAM0827E = "NWAM0827E";
    // 2016/07/11 QC#5030 Add End

    // Add Start 2016/09/20 QC#13738
    /** Please be [@] is set in less than @ digits. */
    public static final String NWAM0895E = "NWAM0895E";
    // Add End 2016/09/20 QC#13738

    // Add Start 2016/11/04 M.Ohno S21_NA#13738
    /** Text length can't exceed @ characters. */
    public static final String NWAM0906E = "NWAM0906E";
    // Add End 2016/11/04 M.Ohno S21_NA#13738
    
    /** At least one contract point has to be entered. */
    public static final String NWAM0832E = "NWAM0832E"; // QC#14143 2016/11/07 Add 
 
    // 2018/07/18 S21_NA#26188 Add Start
    /** Please check at least 1 checkbox. */ 
    public static final String NWAM0186E = "NWAM0186E";
    
    /** [@] is mandatory. */
    public static final String ZZBM0084E = "ZZBM0084E";
    
    /** Detail requires at least one line.  Please enter. */
    public static final String NMAM8190E = "NMAM8190E";
    // 2018/07/18 S21_NA#26188 Add End

    // Add Start 2018/12/14 QC#24022
    /** @ length has been exceeded. Maximum length is @ digit. */
    public static final String NWAM0964E = "NWAM0964E";
    // Add End 2018/12/14 QC#24022

    /** Comma */
    public static final String COMMA = ",";

    /** Space */
    public static final String SPACE = " ";

    /** Colon */
    public static final String COLON = ":";

    /** Slash */
    public static final String SLASH = "/";

    /** Period */
    public static final String PERIOD = "\\.";

    /** Hyphen */
    public static final String HYPHEN = "-";

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
    public static final String CHK_TIME_PATTERN = "(0[0-9]|1[0-1]):[0-5][0-9]";

    /** Phone check Pattern */
    public static final String CHK_PHONE_PATTERN = "(\\d{10}|\\d{3}-\\d{3}-\\d{4})";

    /** EMail check Pattern */
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

    /** Technician Label Name Install Technician */
    public static final String TECHNICIAN_LABEL_NAME_INS = "Install Technician";

    /** Technician Label Name De-install Technician */
    public static final String TECHNICIAN_LABEL_NAME_DE_INS = "De-install Technician";

    /** Order position Number Name View Select */
    public static final String ORD_POSN_NUM_VIEW_SELECT = "View Selected Configration";

    // S21_NA#16035 Add Start
    /** Reference Authority */
    public static final String REF_AUTH = "NWAL1510T020";

    /** Add Contact Button Event Name */
    public static final String BTN_ADD_CTAC_EVENT_NM = "Add_Row";

    /** Delete Contact Button Event Name */
    public static final String BTN_DEL_CTAC_EVENT_NM = "Delete_Row";
    // S21_NA#16035 Add End

    // Add Start 2018/12/14 QC#24022
    /** Max length of extension */
    public static final int EXT_MAX_LENGTH = 10;
    // Add End 2018/12/14 QC#24022

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    public static final int POP_PAR_LENGTH_4 = 4;
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
