/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190.constant;

/**
 *<pre>
 * NMAL7190Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2017/08/14   Fujitsu         M.Ohno          Update          QC#18789(L3)
 * 2018/12/05   Fujitsu         T.Noguchi       Update          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190Constant {

    /** SMsg Max Length */
    public static final int MAXROWOVERCOUNT = 10000;

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Column */
    public static final int CMN_PRM_COLUMN_NUM = 4;

    /** Common Pop-up Parameter/Sort */
    public static final int CMN_PRM_SORT_NUM = 2;

    /** NWAL1130 Parameter */
    public static final int NWAL1130_PRM_NUM = 7;

    /** NMAL6800 Parameter */
    public static final int NMAL6800_PRM_NUM = 10;

    // QC#18789 2017/08/14 add start
    /** NMAL2550 Parameter */
    public static final int NMAL2550_PRM_NUM = 10;
    // QC#18789 2017/08/14 add end

    /** NAML6760 Parameter */
    public static final int NAML6760_PRM_NUM = 15;

    /** NAML6050 Parameter */
    public static final int NAML6050_PRM_NUM = 11;

    /** NAML6050 Parameter : Code */
    public static final int NAML6050_PRM_CODE_NUM = 9;

    /** NAML6050 Parameter : Name */
    public static final int NAML6050_PRM_NAME_NUM = 10;

    // 2023/04/20 QC#61200 Mod Start
//    // 2018/12/05 QC#29324 Add Start
//    /** NMAL7440 Parameter */
//    public static final int NMAL7440_PRM_NUM = 12;
//    // 2018/12/05 QC#29324 Add End
    public static final int NMAL7440_PRM_NUM = 13;
    // 2023/04/20 QC#61200 Mod End

    /** Popup Name : NMAL6800 */
    public static final String POPUP_NAME_NMAL6800 = "NMAL6800";

    /** Popup Name : NMAL6760 */
    public static final String POPUP_NAME_NMAL6760 = "NMAL6760";

    /** Popup Name : NWAL1130 */
    public static final String POPUP_NAME_NMAL6050 = "NMAL6050";

    /** Popup Name : NWAL1130 */
    public static final String POPUP_NAME_NWAL1130 = "NWAL1130";

    // QC#18789 2017/08/14 add start
    /** Popup Name : NMAL2550 */
    public static final String POPUP_NAME_NMAL2550 = "NMAL2550";
    // QC#18789 2017/08/14 add end

    // 2018/12/05 QC#29324 Add Start
    /** Popup Name : NMAL7440 */
    public static final String POPUP_NAME_NMAL7440 = "NMAL7440";
    // 2018/12/05 QC#29324 Add End

    /** Popup Button Flag : Target From Text  */
    public static final String POPUP_BTN_FLG_FROM = "0";

    /** Popup Button Flag : Target To Text  */
    public static final String POPUP_BTN_FLG_TO = "1";

    /** Business ID */
    public static final String BIZ_ID = "NMAL7190";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7190Scrn00";

    /** Screen Name : Price Group Usage */
    public static final String SCRN_NM_PRC_GRP_USAGE = "PrcGrpUsage";

    /** Template File Name */
    public static final String TMPL_FILE_NAME = "UploadTemplate_(NAML7190001).xlsx";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // 2023/04/20 QC#61200 Add Start
    /**  Check Box Name */
    public static final String XX_CHKBOX_NAME = "xxChkBox_A1";

    public static final String XX_MODE_CD_08 ="08";
    // 2023/04/20 QC#61200 Add End

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
    // Business button
    // --------------------------------
    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Button View Group */
    public static final String[] BTN_VIEW_USAGE = {"OpenWin_PrcGrpUsage", "OpenWin_PrcGrpUsage", "View Usage" };

    /** Button Upload */
    public static final String[] BTN_UPLOAD = {"Upload", "Upload", "Upload" };

    /** Button Insert Row */
    public static final String[] BTN_INSERT_ROW = {"InsertRow", "InsertRow", "Insert Row" };

    /** Button Delete Row */
    public static final String[] BTN_DELETE_ROW = {"DeleteRow", "DeleteRow", "Delete Row" };

    /** Button Target From */
    public static final String[] BTN_TARGET_FROM = {"OpenWin_TargetFrom", "OpenWin_TargetFrom", "Target" };

    /** Button Target To */
    public static final String[] BTN_TARGET_TO = {"OpenWin_TargetTo", "OpenWin_TargetTo", "Target" };

    // 2018/12/05 QC#29324 Add Start
    /** Button Filter */
    public static final String[] BTN_FILTER = {"OpenWin_Filter", "OpenWin_Filter", "Filter" };
    // 2018/12/05 QC#29324 Add End

    // --------------------------------
    // Message ID
    // --------------------------------

    /**  [@] field has too many digits entered.  */
    public static final String NMAM8090W = "NMAM8090W";

    /**  Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /**  The value for [@] must be smaller than [@].  */
    public static final String NMAM0043E = "NMAM0043E";

    /**  The value for [@] must be over [@].  */
    public static final String NMAM0042E = "NMAM0042E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**  Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Only the files with the following extensions can be attached. [@] */
    public static final String ZYPM0188E = "ZYPM0188E";

    /**  The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /**  Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    // 2018/12/05 QC#29324 Add Start
    /** Please search for Price Group first. */
    public static final String NMAM8697E = "NMAM8697E";
    // 2018/12/05 QC#29324 Add End

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    //--------------------------------
    // String For Message
    //--------------------------------

    /**  When Operator is Between, Target To */
    public static final String OPERATOR_TARGETTO_MANDATORY = "When Operator is Between, Target To";

}
