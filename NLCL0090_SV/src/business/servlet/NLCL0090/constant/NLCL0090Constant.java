/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLCL0090.constant;

/** 
 *<pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         FXS)KF.Qian     Create          N/A
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public interface NLCL0090Constant {

    /**
     * SCREEN_ID
     */
    String SCREEN_ID = "NLCL0090Scrn00";

    /**
     * BUSINESS_ID
     */
    String BUSINESS_ID = "NLCL0090";

    /**
     */
    String MAXLENGTH = "100";

    /**
     */
    String INVTYQTY_DF = "invtyQty_DF";

    /**
     */
    String INVTYQTY_DT = "invtyQty_DT";

    /**
     */
    int MAXLINE = 100;

    /**
     */
    int LENGTH_OF_INVTYAVALQTY = 9;

    /**
     */
    int NVTYAVALQTY_FROM = 1;

    /**
     */
    int NVTYAVALQTY_TO = 10;

    /**
     */
    String SELECT_TOC = "Select_Toc";

    /**
     */
    String OPENWIN_SUBWINMDSECODE_FROM = "Y";

    /**
     */
    String OPENWIN_SUBWINMDSECODE_TO = "N";

    /**
     */
    String CHKBOX_DF = "xxChkBox_DF";

    /**
     */
    String CHKBOX_DT = "xxChkBox_DT";

    /***************************************************
     * Function Button
     * [0]:Button Name [1]:Event Name [2]:Button Lavel
     ***************************************************/

    /** Function Button 1 */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Search */
    String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Search MDSE Information From */
    String[] BTN_SEARCH_MDSE_FROM = {"Search_MDSEInfo_From", "Search_MDSEInfo_From", ">>" };

    /** Search MDSE Information To */
    String[] BTN_SEARCH_MDSE_TO = {"Search_MDSEInfo_To", "Search_MDSEInfo_To", ">>" };

    /** Add Detail Line From */
    String[] BTN_ADD_DTL_FROM = {"Add_Detail_From", "Add_Detail_From", "Add" };

    /** Add Detail Line To */
    String[] BTN_ADD_DTL_TO = {"Add_Detail_To", "Add_Detail_To", "Add" };

    /** Delete Detail Line From */
    String[] BTN_DEL_DTL_FROM = {"Del_Detail_From", "Del_Detail_From", "Delete Row" };

    /** Delete Detail Line To */
    String[] BTN_DEL_DTL_TO = {"Del_Detail_To", "Del_Detail_To", "Delete Row" };

    /***************************************************
     * Event Name
     ***************************************************/

    /** Event Name: CMN_Close */
    String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: OpenWin_LocInfoRtlWh */
    String EVENT_NM_OPENWIN_LOCINFO_RTLWH = "OpenWin_RetailWH_Info";

    /** Event Name: OpenWin_LocInfoRtlSWh */
    String EVENT_NM_OPENWIN_LOCINFO_RTLSWH = "OpenWin_RetailSubWH_Info";

    /** Event Name: OpenWin_SubWinMDSECode_From */
    String  EVENT_NM_OPENWIN_MDSE_FROM = "OpenWin_SubWinMDSECode_From";

    /** Event Name: OpenWin_SubWinMDSECode_To */
    String  EVENT_NM_OPENWIN_MDSE_TO = "OpenWin_SubWinMDSECode_To";

    /***************************************************
     * Message
     ***************************************************/

    /** Details require more than 1 row.  Please enter. */
    String NLBM0002E = "NLBM0002E";

    /** Serial # is duplicated. Need to change or delete either of them. */
    String NLBM1266E ="NLBM1266E";

    /** Detail rows are entered incorrectly.  Please correct. */
    String NLCM0043E = "NLCM0043E";

    /** Some items cannot be displayed since it exceeds the maximum length. */
    String NLCM0085W = "NLCM0085W";

    /** Values remains in the detail line entry field.  Add detail lines. */
    String NLCM0015E = "NLCM0015E";

    /** Entered data is already registered. */
    String NLCM0019E = "NLCM0019E";

    /** The number of Detail rows reached to the maximum.  No more Details can be registered. */
    String NLCM0025E = "NLCM0025E";

    /** The value for [@] must be bigger than [@]. */
    String NLZM2277E = "NLZM2277E";

    /** Process ended normally. */
    String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    String ZZM9000E = "ZZM9000E";
}
