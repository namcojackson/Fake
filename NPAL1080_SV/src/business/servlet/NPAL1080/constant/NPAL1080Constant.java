package business.servlet.NPAL1080.constant;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/19/2016   CITS            K.Ogino         Update          QC#15820
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 * 03/16/2023   Hitachi         T.Kuroda        Update          QC#61204
 *</pre>
 */
public class NPAL1080Constant {

    /**
     * DB Like search char
     */
    public static final String LIKE_SEARCH_CHAR = "%";

    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1080";

    /**
     * Screen ID
     */
    public static final String SCREEN_ID = "NPAL1080Scrn00";

    /** Function Code : Search */
    public static final String FUNCTION_CD_SEARCH = "20";

    /** Function Code : Update */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Tab Header
     */
    public static final String TAB_HEADER = "Header";

    /**
     * Tab Detail
     */
    public static final String TAB_DETAIL = "Detail";

    /** . */
    public static final String BTN_DETAIL_ITEM = "OpenWin_Item";

    /** . */
    public static final String BTN_DETAIL_GET_ITEM_NAME = "GetItemName";

    /** . */
    public static final String BTN_DETAIL_WH = "OpenWin_Wh_Supplier";

    /** . */
    public static final String BTN_DETAIL_GET_WH_SUPPLER = "GetWhOrSupplierName";

    // QC#21913 Add.
    /** . */
    public static final String BTN_DETAIL_GET_TRACKING_NUMBER = "OpenWin_Tracking";

    /**
     * FUNC_ID_SEARCH
     */
    public static final String FUNC_ID_SEARCH = "NPAL1080T010";

    /**
     * FUNC_ID_SUBMIT
     */
    public static final String FUNC_ID_SUBMIT = "NPAL1080T020";

    /**
     * FUNC_ID_SPEC_UPDATE
     */
    public static final String FUNC_ID_SPEC_UPDATE = "NPAL1080T030";

    /**
     * NPAL1210 Popup Param 1:Tech Reques
     */
    public static final String NPAL1210_POP_PARAM_1 = "1";

    // =================================================
    // Label text
    // =================================================
    /** . */
    public static final String LABEL_TXT_PRCH_REQ_NUM = "Tech Request #";

    /** . */
    public static final String LABEL_TXT_PRCH_REQ_TP = "Tech Request Type";

    /** . */
    public static final String LABEL_TXT_RQST_RCV_DT = "Date & Time Needed";

    /** . */
    public static final String LABEL_TXT_FSR_NUM = "Service Request #";

    /** . */
    public static final String LABEL_TXT_SVC_TASK_NUM = "Service Request Task #";

    /** . */
    public static final String LABEL_TXT_SVC_MACH_SER_NUM = "Service Request Serial #";

    /** . */
    public static final String LABEL_TXT_CTAC_PSN_NM = "Attention To";

    /** . */
    public static final String LABEL_TXT_RQST_TECH_TOC_CD = "Technician Code";

    /** . */
    public static final String LABEL_TXT_TECH_NM = "Technician Name";

    /**
     * for SupplierPopup Param (NWAL1130)
     */
    public static final String OPEN_WIN_SUPPLIER_SQL = "(select V.EZCANCELFLAG,V.GLBL_CMPY_CD,V.VND_CD,V.LOC_NM,V.ARCS_SPLY_SITE_CD,V.INAC_DT,PV.PRNT_VND_CD,PV.PRNT_VND_NM,PV.SPLY_TP_CD"
            + " from PRNT_VND PV,VND V,VND_TP_RELN VTR"
            + " where V.VND_CD = VTR.VND_CD AND V.GLBL_CMPY_CD = VTR.GLBL_CMPY_CD AND V.EZCANCELFLAG = VTR.EZCANCELFLAG AND VTR.VND_TP_CD = '01' AND V.RGTN_STS_CD = 'P20' AND V.EZCANCELFLAG = '0' AND V.EFF_THRU_DT >= TO_CHAR (SYSDATE, 'YYYYMMDD') AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD AND V.EZCANCELFLAG = PV.EZCANCELFLAG AND V.PRNT_VND_PK = PV.PRNT_VND_PK AND (PV.INAC_DT IS NULL OR PV.INAC_DT > TO_CHAR (SYSDATE, 'YYYYMMDD')))";

    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    /** . */
    public static final String RUN_MODE_NEW = "1";

    /** . */
    public static final String RUN_MODE_COPIED = "2";

    /** . */
    public static final String RUN_MODE_SAVED = "3";

    /** . */
    public static final String RUN_MODE_SUBMITTED = "4";

    /** . */
    public static final String RUN_MODE_CLOSED = "5";

    /**
     * Time format charactor
     */
    public static final String COLON = ":";

    /**
     * Time format check pattern
     */
    public static final String CHK_TIME_PATTERN = "(0[0-9]|1[0-1]):[0-5][0-9]";

    /**
     * Time is invalid. Please enter in the "00:00" - "11:59" format
     * time.
     */
    public static final String NPAM1531E = "NPAM1531E";

    /**
     * [@] is not selected.
     */
    public static final String NLZM2274E = "NLZM2274E";

    /**
     * Either [@] or [@] must be specified.
     */
    public static final String NPAM1517E = "NPAM1517E";

    /**
     * [@] is mandatory value.@
     */
    public static final String NPAM1329E = "NPAM1329E";

    /**
     * AM/PM Time check val
     */
    public static final int TIME_CHECK_VAL = 1200;

    /**
     * index 0
     */
    public static final int IDX_0 = 0;

    /**
     * index 1
     */
    public static final int IDX_1 = 1;

    /**
     * index 2
     */
    public static final int IDX_2 = 2;

    /**
     * index 3
     */
    public static final int IDX_3 = 3;

    /**
     * index 4
     */
    public static final int IDX_4 = 4;

    /**
     * index 5
     */
    public static final int IDX_5 = 5;

    /**
     * index 6
     */
    public static final int IDX_6 = 6;

    /**
     * index 7
     */
    public static final int IDX_7 = 7;

    /**
     * index 20
     */
    public static final int IDX_20 = 20;

    /**
     * index 62
     */
    public static final int IDX_62 = 62;

    /**
     * // ================================================= // Event
     * Name // ================================================= /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

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

    /** Function Button 11 */
    public static final String[] BTN_ADD_LINE = {"btn11", "CMN_AddLine", "AddLine" };

    /** Protected Control Mode */
    public static final int MODE_BTN_INIT = 0;

    /** Protected Control Mode */
    public static final int MODE_BTN_ADDLINE = 1;

    /** Protected Control Mode */
    public static final int MODE_BTN_COPY = 2;

    /** Protected Control Mode */
    public static final int MODE_BTN_OTHER = 3;

    /** Tech SWH Defalut */
    public static final String TECH_SWH_DEFALUT = "G";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NPAM0076E: [@] does not exist in Master.
     */
    public static final String NPAM0076E = "NPAM0076E";

    /**
     * NMAM0288E:Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /**
     * NAMM0027E
     */
    public static final String NAMM0027E = "NAMM0027E";

    /**
     * NWZM0189E
     */
    public static final String NWZM0189E = "NWZM0189E";

    /**
     * NLZM2055E
     */
    public static final String NLZM2055E = "NLZM2055E";

    /**
     * NPAM0049E
     */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * NPAM1230E
     */
    public static final String NPAM1230E = "NPAM1230E";

    /**
     * NMAM0836E
     */
    public static final String NMAM0836E = "NMAM0836E";

    /**
     * NPAM1234E
     */
    public static final String NPAM1234E = "NPAM1234E";

    /**
     * NMAM8098E
     */
    public static final String NMAM8098E = "NMAM8098E";

    /**
     * NPAM1359I
     */
    public static final String NPAM1359I = "NPAM1359I";

    /**
     * NPAM1598I
     */
    public static final String NPAM1598I = "NPAM1598I";

    /**
     * NLAM0173E
     */
    public static final String NLAM0173E = "NLAM0173E";

    /**
     * NPAM1649E
     */
    public static final String NPAM1649E = "NPAM1649E";

    // START 2023/03/16 T.Kuroda QC#61204 ADD
    /**
     * ZZM9000E:
     */
    public static final String ZZM9000E = "ZZM9000E";
    // END   2023/03/16 T.Kuroda QC#61204 ADD

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Code Column for NPAL1010
     */
    public static final String LOC_ROLE_TP_CDLIST = "TECHNICIAN";

    // for Carrier
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_CARR = "OTBD_CARR_V";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_CARR = "CARR_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_CARR = "CARR_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_CARR = "CARR_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_CARR = "Carrier Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_CARR = "Carrier Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_CARR = "Carrier Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_CARR = "Carrier Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_CARR = "Carrier Name";

    // for Technician
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_TECH = "TECH_MSTR";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_TECH = "TECH_TOC_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_TECH = "TECH_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_TECH = "TECH_TOC_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_TECH = "Technician Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_TECH = "Technician Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_TECH = "Technician Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_TECH = "Technician Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_TECH = "Technician Name";

    /**
     * Left Table Id 
     */
    public static final String TBL_ID_L = "A1";

    /**
     * Right Table Id
     */
    public static final String TBL_ID_R = "A2";

    // START 2017/10/25 S.Katsuma QC#21896 ADD
    /** . */
    public static final String TECH_SWH_CD_R = "R";
    // END 2017/10/25 S.Katsuma QC#21896 ADD

    // START 2023/03/16 T.Kuroda QC#61204 ADD
    /**
     * Display Name : Upload File
     */
    public static final String DISPLAY_NM_UPLOAD_FILE = "Upload File";

    /**
     * Upload Template File Name
     */
    public static final String CSV_FILE_NAME = "TechRequestEntry_UploadTemplate";

    /**
     * Param Value for Upload
     */
    public static final String PRAM_UPLOAD_FILE_EXTENSION_CSV = ".csv";

    /**
     * Upload Template File Extesion
     */
    public static final String TMPL_FILE_EXTENSION = ".csv";
    // END   2023/03/16 T.Kuroda QC#61204 ADD

}
