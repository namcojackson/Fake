package business.servlet.NWAL2040.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2016/03/24   Hitachi         K.Kojima        Update          S21 NA Unit Test #56  Add Upload Function
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public interface NWAL2040Constant {
	
    /**
     * Business ID -- NWAL2040
     */
    String BUSINESS_ID = "NWAL2040";

    /**
     * Screen ID.
     */
    String SCREEN_ID = "NWAL2040Scrn00";
    
    /**
     * FUNC_CD_SRCH -- 20
     */
    String FUNC_CD_SRCH = "20";

    /**
     * FUNC_CD_SBMT -- 40
     */
    String FUNC_CD_SBMT = "40";

    /**
     * CMN_CLOSE -- CMN_Close
     */
    String CMN_CLOSE = "CMN_Close";

    /**
     * ERROR_CODE_E -- E
     */
    String ERROR_CODE_E = "E";

    /**
     * CMN_BTN1 -- Save
     */
    String[] CMN_BTN1 = {"btn1", "", "Save" };

    /**
     * CMN_BTN2 -- Submit
     */
    String[] CMN_BTN2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * CMN_BTN3 -- Apply
     */
    String[] CMN_BTN3 = {"btn3", "", "Apply" };

    /**
     * CMN_BTN4 -- Approve
     */
    String[] CMN_BTN4 = {"btn4", "", "Approve" };

    /**
     * CMN_BTN5 -- Reject
     */
    String[] CMN_BTN5 = {"btn5", "", "Reject" };

    /**
     * CMN_BTN6 -- Download
     */
    String[] CMN_BTN6 = {"btn6", "CMN_Download", "Download" };

    /**
     * CMN_BTN7 -- Delete
     */
    String[] CMN_BTN7 = {"btn7", "", "Delete" };

    /**
     * CMN_BTN8 -- Clear
     */
    String[] CMN_BTN8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * CMN_BTN9 -- Reset
     */
    String[] CMN_BTN9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * CMN_BTN10 -- Return
     */
    String[] CMN_BTN10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Search Button
     */
    String[] SEARCH_BTN = {"btn11", "Search", "Search" };

    /**
     * Insert Source Category Button
     */
    String[] INS_SRC_CATG_BTN = {"btn12", "InsertRow_SRC_CATG", "Insert Row" };

    /**
     * Delete Source Category Button
     */
    String[] DEL_SRC_CATG_BTN = {"btn13", "DeleteRow_SRC_CATG", "Delete Row" };
    
    /**
     * Insert Mapping Template Button
     */
    String[] INS_MAP_TMPL_BTN = {"btn12", "InsertRow_MAP_TMPL_QLFY", "Insert Row" };

    /**
     * Delete Mapping Template Button
     */
    String[] DEL_MAP_TMPL_BTN = {"btn13", "DeleteRow_MAP_TMPL_QLFY", "Delete Row" };
    
    /**
     * filterResult Button
     */
    String[] FILTER_RESULT_BTN = {"btn13", "filter_Result", "Filter Result" };

    // START 2016/03/23 K.Kojima [UT#56,ADD]
    /**
     * Mass Upload Button
     */
    String[] MASS_UPLOAD_BTN = {"pBtn6", "MoveToUpload", "Mass Upload" };

    // END 2016/03/23 K.Kojima [UT#56,ADD]

    // 2017/09/12 QC#19805 Add Start
    /**
     * Insert Mapping Template RMA Button
     */
    String[] INS_MAP_TMPL_RMA_BTN = {"btn12", "InsertRow_MAP_TMPL_QLFY_RMA", "Insert Row" };

    /**
     * Delete Mapping Template RMA Button
     */
    String[] DEL_MAP_TMPL_RMA_BTN = {"btn13", "DeleteRow_MAP_TMPL_QLFY_RMA", "Delete Row" };
    // 2017/09/12 QC#19805 Add End

    String[] OTBD_PRIM_ON_HND_CHK_WH_BTN = {"btn13", "LineItem_OTBD_PRIM_ON_HND_CHK_WH", "..." };
    String[] OTBD_PRIM_ON_HND_CHK_SWH_BTN = {"btn13", "LineItem_OTBD_PRIM_ON_HND_CHK_SWH", "..." };
    String[] OTBD_SCD_ON_HND_CHK_WH_BTN = {"btn13", "LineItem_OTBD_SCD_ON_HND_CHK_WH", "..." };
    String[] OTBD_SCD_ON_HND_CHK_SWH_BTN = {"btn13", "LineItem_OTBD_SCD_ON_HND_CHK_SWH", "..." };
    String[] OTBD_DEF_WH_BTN = {"btn13", "LineItem_OTBD_DEF_WH", "..." };
    String[] OTBD_DEF_SWH_BTN = {"btn13", "LineItem_OTBD_DEF_SWH", "..." };
    String[] RMA_DEF_WH_BTN = {"btn13", "LineItem_RMA_DEF_WH", "..." };
    String[] RMA_DEF_SWH_BTN = {"btn13", "LineItem_RMA_DEF_SWH", "..." };
    
	String AUTH_READ = "NWAL2040T010";

	String AUTH_EDIT = "NWAL2040T020";

	String TAB_SRC_CATG = "SrcCatg";
	
	String TAB_MAP_TMPL_QLFY = "MapTmpQlfy";
    
    // 2017/09/12 QC#19805 Add Start
    String TAB_MAP_TMPL_QLFY_RMA = "MapTmpQlfyRMA";
    // 2017/09/12 QC#19805 Add End

}
