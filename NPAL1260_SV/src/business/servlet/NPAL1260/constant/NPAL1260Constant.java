package business.servlet.NPAL1260.constant;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura        Create          N/A
 * 2019/01/10   Fujitsu         S.Takami        Update          QC#29890
 *</pre>
 */
public class NPAL1260Constant {

    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1260";

    /**
     * DB Like search char
     */
    public static final String LIKE_SEARCH_CHAR = "%";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /**
     * Screen ID
     */
    public static final String SCREEN_ID = "NPAL1260Scrn00";

    /**
     * for SupplierPopup Param (NWAL1130)
     */
    public static final String OPEN_WIN_SUPPLIER_SQL = "select V.EZCANCELFLAG,V.GLBL_CMPY_CD,V.VND_CD,V.LOC_NM,V.ARCS_SPLY_SITE_CD,V.INAC_DT,PV.PRNT_VND_CD,PV.PRNT_VND_NM,PV.SPLY_TP_CD "
            + " from PRNT_VND PV,VND V,VND_TP_RELN VTR "
            + " where V.VND_CD = VTR.VND_CD AND V.GLBL_CMPY_CD = VTR.GLBL_CMPY_CD AND V.EZCANCELFLAG = VTR.EZCANCELFLAG AND VTR.VND_TP_CD = '01' AND V.RGTN_STS_CD = 'P20' AND V.EZCANCELFLAG = '0'"
            + " AND V.EFF_THRU_DT >= TO_CHAR (SYSDATE, 'YYYYMMDD') AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD"
            + " AND V.EZCANCELFLAG = PV.EZCANCELFLAG AND V.PRNT_VND_PK = PV.PRNT_VND_PK AND (PV.INAC_DT IS NULL OR PV.INAC_DT > TO_CHAR (SYSDATE, 'YYYYMMDD'))";

    /**
     * for SupplierPopup Param (NWAL1130)
     */
    // 2019/01/10 QC#29890 Mod Start
//    public static final String OPEN_WIN_SUPPLIER_SQL_TECH = "select V.EZCANCELFLAG,V.GLBL_CMPY_CD,V.PSN_CD, V.FULL_PSN_NM from TECH_MSTR TM ,S21_PSN_V V "
//            + " where 1 = 1 and TM.GLBL_CMPY_CD = V.GLBL_CMPY_CD and TM.FLD_SVC_MGR_CD = V.PSN_CD and TM.EZCANCELFLAG = '0' and V.EZCANCELFLAG = '0' group by  V.EZCANCELFLAG,V.GLBL_CMPY_CD,V.PSN_CD, V.FULL_PSN_NM";
    public static final String OPEN_WIN_SUPPLIER_SQL_TECH = "select TM.EZCANCELFLAG,TM.GLBL_CMPY_CD,TM.TECH_TOC_CD, TM.TECH_NM from TECH_MSTR TM ,S21_PSN PSN "
        + " where 1 = 1 and TM.GLBL_CMPY_CD = PSN.GLBL_CMPY_CD and TM.TECH_TOC_CD = PSN.PSN_CD and TM.EZCANCELFLAG = '0' and PSN.EZCANCELFLAG = '0'";
    // 2019/01/10 QC#29890 Mod End

    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0288E:Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /**
     * ZZM9010E:[@] should be smaller than [@].
     */
    public static final String ZZM9010E = "ZZM9010E";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

}
