/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NMAL6710.constant;


/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 05/05/2016   SRAA            Y.Chen          Update          QC#7711
 * 06/21/2016   SRAA            Y.Chen          Update          QC#6189
 * 09/21/2016   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 02/21/2017   Fujitsu         K.Ishizuka      Update          QC#17610
 * 07/05/2018   Fujitsu         T.Aoi           Update          QC#26939
 * 07/15/2020   Fujitsu         M.Ohno          Update          QC#57315
 *</pre>
 */
public interface NMAL6710Constant {

    /** Please select item(s). **/
    public static final String NMAM8054E = "NMAM8054E";
    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";
    // QC#7711
    /** The search results must be refreshed by hitting the Search button. */
    public static final String NMAM8494E = "NMAM8494E";
    // QC#6189
    /** Please set at least one search criteria. */
    public static final String NMAM0288E = "NMAM0288E";

    // QC#11068
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    
    // ADD START S21_NA QC#17610
    /** You can enter wild card only with keyword. */
    public static final String NMAM8667E = "NMAM8667E";
    
    /** wild card */
    public static final String WILDCARD = "%";
    // ADD END S21_NA QC#17610
    
    /**
     * Function Button 1
     */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Function Button 2
     */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Function Button 3
     */
    String[] BTN_CMN_APPLY = {"btn3", "", "Apply" };

    /**
     * Function Button 4
     */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Function Button 5
     */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Function Button 6
     */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "", "Download" };

    /**
     * Function Button 7
     */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Function Button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Function Button 9
     */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Function Button 10
     */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };


    /**
     * Search Button
     */
    String[] BTN_SEARCH = {"btn11", "Search", "Search" };

    /**
     * Acct Info Button
     */
    String[] BTN_ACCT_INFO = {"btn12", "ShowAccountInformation", "Account info" };

    /**
     * Loc Info Button
     */
    String[] BTN_LOC_INFO = {"btn13", "ShowLocation", "Location info" };

    /**
     * New Loc Button
     */
    String[] BTN_NEW_LOC_INFO = {"btn14", "ShowNewLocation", "Add Location" };

    /**
     * New Loc Button
     */
    String[] BTN_NOTE = {"btn15", "ShowNote", "Note" };

    /**
     *Contacts Info Button
     */
    String[] BTN_CTAC = {"btn16", "OpenWin_CtacSrch", "Contacts" };
    
    // 2020/07/14 QC#57315 Add Start
    /**
     *Contacts Info Button
     */
    String[] BTN_DUNS_UPLD = {"btn17", "MoveWin_UploadDuns", "Duns Upload" };
    // 2020/07/14 QC#57315 Add End

    /**
     * Cmn_Return Button
     */
    String CMN_RETURN = "Cmn_Return";

    /**
     * Screen ID
     */
    String SCREEN_ID = "NMAL6710Scrn00";

    /**
     * Business ID
     */
    String BUSINESS_ID = "NMAL6710";

    /**
     * ReadOnly Function Code
     */
    String AUTH_READONLY = "NMAL6710T010";

    /**
     * Edit Function Code
     */
    String AUTH_EDIT = "NMAL6710T020";

    /**
     * Download Function Code
     */
    String AUTH_DOWNLOAD = "NMAL6710T025";

    // 2020/07/14 QC#57315 Add Start
    /**
     * Download Function Code
     */
    String AUTH_DUNS_UPLD = "NMAL6720T060";
    // 2020/07/14 QC#57315 Add End

    /**
     * SEARCH Function Code 20
     */
    String FUNCTION_CODE_SEARCH = "20";

    /**
     * UPDATE Function Code 40
     */
    String FUNCTION_CODE_UPDATE = "40";

    /**
     * TREEVIEW
     */
    String TREEVIEW = "treeView";

    /**
     * CLEAR FLG STATUS(ALL)
     */
    String ALL = "ALL";

    // Search Mode
    /**
     * SEARCH_MODE_CD_HRCH : 01
     */
    String SEARCH_MODE_CD_HRCH = "01";
    /**
     * SEARCH_MODE_CD_QUICK : 02
     */
    String SEARCH_MODE_CD_QUICK = "02";

    /** Search Result Mode : Hierarchy */
    String TAB_NM_HRCH = "Hierarchy";

    // QC#6189
    /** Search Result Mode : Hierarchy's Quick Lookup mode */
    String TAB_NM_HRCH_QUICK = "HierarchyQuickLookup";

    /** Search Result Mode : QuickLookup */
    String TAB_NM_QUICK = "QuickLookup";

    /** Radio button in Tree view **/
    int COLUMN_INDEX_RADIO_BUTTON = 1;
// QC#4506
//    /** Account# in Tree view **/
//    int COLUMN_INDEX_DS_ACCT_NUM = 10;
//    /** Account Name in Tree view **/
//    int COLUMN_INDEX_DS_ACCT_NM = 3;
//    /** Location# in Tree view **/
//    int COLUMN_INDEX_LOC_NUM = 11;
//    /** Location# in Tree view **/
//    int COLUMN_INDEX_BILL_TO = 8;
//    /** Location# in Tree view **/
//    int COLUMN_INDEX_SHIP_TO = 9;
      /** Tree view column index **/
      int COLUMN_INDEX_DS_ACCT_NUM = 11;
      /** Tree view column index **/
      int COLUMN_INDEX_LOC_NUM = 4;
      /** Tree view column index **/
      int COLUMN_INDEX_BILL_TO = 9;
      /** Tree view column index **/
      int COLUMN_INDEX_SHIP_TO = 10;
      
      // QC#6189
      /** Display Related Accounts Code: 01 */
      public static final String DISP_RELN_ACCT_CD_ACCT = "01";

      // 2018/07/05 QC#26939 Add Start
      /** Status (ACTIVE ONLY/ ACTIVE & INACTIVE) */
      public static final String STATUS_CD_ACTIVE = "01";
      public static final String STATUS_CD_ALL = "02";
      public static final String STATUS_NM_ACTIVE = "Active Only";
      public static final String STATUS_NM_ALL = "Active & Inactive";
      // 2018/07/05 QC#26939 Add End
      
      // 2020/07/14 QC#57315 Add Start
      public static final String UPLD_CSV_ID = "NMA2730001";
      // 2020/07/14 QC#57315 Add End
}
