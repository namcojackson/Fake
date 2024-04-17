/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/02/10   Hitachi         A.Kohinata      Update          CSA QC#1157
 * 2016/05/19   Hitachi         K.Kasai         Update          QC#447
 * 2016/09/20   Hitachi         Y.Zhang         Update          QC#12852
 * 2017/01/23   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/07/24   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 2017/12/22   Hitachi         U.Kim           Update          QC#22448
 * 2018/01/15   Hitachi         K.Kojima        Update          QC#23352
 * 2019/02/21   Hitachi         K.Fujimoto      Update          QC#30366
 * 2024/03/14   Hitachi         K.Watanabe      Update          QC#63542
 *</pre>
 */
public class NSAL0490Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0490";

    /** Screen ID */
    public static final String SCREEN_ID_00 = "NSAL0490Scrn00";

    /** Service Contract, Dispatch Manager */
    public static final String AUTH_CONTR_MGR = "NSAL0490T020";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Button (Search) */
    public static final String BTN_SEARCH = "Search";

    /** Button (Contract Uplifts) */
    public static final String BTN_CONTR_UPLFT = "ContractUplifts";

    /** Button (M) */
    public static final String BTN_OPEN_MDSE = "OpenWin_MdseCd";

    /** Button (>>) */
    public static final String BTN_SET_MDSE = "Setting_MdseInfo";

    /** Button (Insert Parent) */
    public static final String BTN_INS_PRNT = "InsertRow_Parent";

    /** Button (Insert Child) */
    public static final String BTN_INS_CLD = "InsertRow_Child";

    /** Button (Insert Supply Mapping) */
    public static final String BTN_INS_SUPPLY_MAP = "InsertRow_SupplyMap";

    /** Button (Delete Row) */
    public static final String BTN_DLT_ITEM_CONFIG = "DeleteRow_ItemConfig";

    /** Button (Delete Row Supply Mapping) */
    public static final String BTN_DLT_SUPPLY_MAP = "DeleteRow_SupplyMap";

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /** Button (Insert Criticality) */
    public static final String BTN_INS_CRITICALITY = "InsertRow_Criticality";

    /** Button (Delete Row Criticality) */
    public static final String BTN_DLT_CRITICALITY = "DeleteRow_Criticality";

    /** Button (M) */
    public static final String BTN_OPEN_MDSE_CRIT = "OpenWin_MdseCdCrit";
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /** Item Name (Model Name) */
    public static final String ITEM_NM_MDL_NM = "Model Name";

    /** Item Name (Model Description) */
    public static final String ITEM_NM_MDL_DESP = "Model Description";

    /** Item Name (Model Group) */
    public static final String ITEM_NM_MDL_GRP = "Model Group";

    // START 2016/09/20 Y.Zhang [QC#12582, modify]
    /** Item Name (Item Code) */
    public static final String ITEM_NM_MDSE_CD = "Item Code";

    /** Item Name (Parent Item Code) */
    public static final String ITEM_NM_PRNT_MDSE_CD = "Parent Item Code";

    /** Item Name (Child Item Code) */
    public static final String ITEM_NM_CHILD_MDSE_CD = "Child Item Code";
   // END 2016/09/20 Y.Zhang [QC#12582, modify]

    /** Item Name (Start Date) */
    public static final String ITEM_NM_START_DT = "Start Date";

    /** Item Name (End Date) */
    public static final String ITEM_NM_END_DT = "End Date";

    /** Item Name (Local Recall Rules (Days)) */
    public static final String ITEM_NM_RCLL_INTVL_DAYS_AOT = "Local Recall Rules (Days)";

    /** Item Name (Local Recall Rules (Copies)) */
    public static final String ITEM_NM_RCLL_COPY_VOL_CNT = "Local Recall Rules (Copies)";

    /** Item Name (Global Recall Rules (Days)) */
    public static final String ITEM_NM_RCLL_GLBL_INTVL_DAYS_AOT = "Global Recall Rules (Days)";

    /** Item Name (Global Recall Rules (Copies)) */
    public static final String ITEM_NM_RCLL_GLBL_COPY_VOL_CNT = "Global Recall Rules (Copies)";

    /** Item Name (Response Time Target (Hours)) */
    public static final String ITEM_NM_RSP_TM_UP_MN_AOT = "Response Time Target (Hours)";

    /** Item Name (Excessive Calls) */
    public static final String ITEM_NM_XS_VISIT_CNT = "Excessive Calls";

    /** Item Name (Phone Fix Period (Days)) */
    public static final String ITEM_NM_PHONE_FIX_INTVL_DAYS_AOT = "Phone Fix Period (Days)";

    /** Item Name (Average Daily Copy Volume (ADCV)) */
    public static final String ITEM_NM_COPY_VOL_DAYS_AOT = "Average Daily Copy Volume (ADCV)";

    /** Item Name (Max Copies Per Day - Total) */
    public static final String ITEM_NM_MAX_COPY_PER_DAY_TOT_CNT = "Max Copies Per Day - Total";

    /** Item Name (Max Copies Per Day - BW) */
    public static final String ITEM_NM_MAX_COPY_PER_DAY_BLACK_CNT = "Max Copies Per Day - BW";

    /** Item Name (Max Test Copies) */
    public static final String ITEM_NM_MAX_COPY_TEST_CNT = "Max Test Copies";

    /** Item Name (Speed - BW (Copies Per Minute)) */
    public static final String ITEM_NM_MDL_SPEED_BLACK_RATE = "Speed - BW (Copies Per Minute)";

    /** Item Name (Speed - Color (Copies Per Minute)) */
    public static final String ITEM_NM_MDL_SPEED_COLOR_RATE = "Speed - Color (Copies Per Minute)";

    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    /** Item Name (MIF Inactive(Months)) */
    public static final String ITEM_NM_MACH_IN_FLD_INAC_MTH_AOT = "MIF Inactive(Months)";

    /** Item Name (Model Duration(Minutes)) */
    public static final String ITEM_NM_MDL_DURN_TM_NUM = "Model Duration(Minutes)";
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    /** Item Name (Tolerance) */
    public static final String ITEM_NM_SPLY_TOI_PCT = "Tolerance";

    /** Item Name (Cust Stock) */
    public static final String ITEM_NM_CUST_STK_QTY = "Cust Stock";

    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    /** Item Name (Initial Quantity) */
    public static final String ITEM_NM_SPLY_INIT_QTY = "Initial Quantity";

    /** Item Name (Contract Quantity) */
    public static final String ITEM_NM_SPLY_CONTR_QTY = "Contract Quantity";
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    // START 2016/02/10 A.Kohinata [QC#1157, ADD]
    /** Item Name (Service Skills) */
    public static final String ITEM_NM_SVC_SKILL = "Service Skills";
    // END 2016/02/10 A.Kohinata [QC#1157, ADD]

    // add start 2016/05/19 CSA Defect#447
    /** Item Name (Status) */
    public static final String ITEM_NM_STATUS = "Status";

    /** Item Name (Date) */
    public static final String ITEM_NM_DATE = "Date";

    /** Item Name (Service Contracts Information) */
    public static final String ITEM_NM_SVC_CONTR_INFO = "Service Contracts Information";

    /** Item Name (Time and Material Information) */
    public static final String ITEM_NM_TM_INFO = "Time and Material Information";

    /** Item Name (Technical Support Information) */
    public static final String ITEM_NM_TECH_SUPT_INFO = "Technical Support Information";

    /** Item Name (Other Comments) */
    public static final String ITEM_NM_OTH_INFO = "Other Comments";
    // add end 2016/05/19 CSA Defect#447

    // START 2017/12/22 U.Kim [QC#22448, ADD]
    /** Item Name (Dispatch Comments) */
    public static final String ITEM_NM_DISPT_CMNT = "Dispatch Comments";
    // END 2017/12/22 U.Kim [QC#22448, ADD]

    // START 2018/01/15 K.Kojima [QC#23352,ADD]
    /** Item Name (Model Type) */
    public static final String ITEM_NM_MODEL_TYPE = "Model Type";
    // END 2018/01/15 K.Kojima [QC#23352,ADD]

    /** Tab Name (Item Configurations) */
    public static final String TAB_ITEM_CONFIG = "ItemConfig";

    /** Tab Name (Service Rules) */
    public static final String TAB_SVC_RULES = "SvcRules";

    /** Tab Name (Supply Mapping) */
    public static final String TAB_SUPPLY_MAP = "SupplyMap";

    // add start 2016/05/19 CSA Defect#447
    /** Tab Name (End Of Life) */
    public static final String TAB_END_OF_LIFE = "EndOfLife";
    // add end 2016/05/19 CSA Defect#447

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /** Tab Name (Criticality) */
    public static final String TAB_CRITICALITY = "Criticality";
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /** Detail Type (Parent) */
    public static final String DTL_TP_PRNT = "PRNT";

    /** Function Button 1 Button Name */
    public static final String BTN_CMN_SAVE_BTN_NM = "btn1";

    /** Function Button 1 Label */
    public static final String BTN_CMN_SAVE_LABEL = "Save";

    /** Function Button 2 Button Name */
    public static final String BTN_CMN_SUBMIT_BTN_NM = "btn2";

    /** Function Button 2 Event Name */
    public static final String BTN_CMN_SUBMIT_EVENT_NM = "CMN_Submit";

    /** Function Button 2 Label */
    public static final String BTN_CMN_SUBMIT_LABEL = "Submit";

    /** Function Button 3 Button Name */
    public static final String BTN_CMN_APPLY_BTN_NM = "btn3";

    /** Function Button 3 Label */
    public static final String BTN_CMN_APPLY_LABEL = "Apply";

    /** Function Button 4 Button Name */
    public static final String BTN_CMN_APPROVE_BTN_NM = "btn4";

    /** Function Button 4 Label */
    public static final String BTN_CMN_APPROVE_LABEL = "Approve";

    /** Function Button 5 Button Name */
    public static final String BTN_CMN_REJECT_BTN_NM = "btn5";

    /** Function Button 5 Label */
    public static final String BTN_CMN_REJECT_LABEL = "Reject";

    /** Function Button 6 Button Name */
    public static final String BTN_CMN_DOWNLOAD_BTN_NM = "btn6";

    /** Function Button 6 Label */
    public static final String BTN_CMN_DOWNLOAD_LABEL = "Download";

    /** Function Button 7 Button Name */
    public static final String BTN_CMN_DELETE_BTN_NM = "btn7";

    /** Function Button 7 Label */
    public static final String BTN_CMN_DELETE_LABEL = "Delete";

    /** Function Button 8 Button Name */
    public static final String BTN_CMN_CLEAR_BTN_NM = "btn8";

    /** Function Button 8 Event Name */
    public static final String BTN_CMN_CLEAR_EVENT_NM = "CMN_Clear";

    /** Function Button 8 Label */
    public static final String BTN_CMN_CLEAR_LABEL = "Clear";

    /** Function Button 9 Button Name */
    public static final String BTN_CMN_RESET_BTN_NM = "btn9";

    // START 2017/01/23 K.Ochiai [QC#16331,MOD]
    /** Function Button 9 Button Name */
    public static final String BTN_CMN_RESET_EVENT_NM = "CMN_Reset";
    // END 2017/01/23 K.Ochiai [QC#16331,MOD]

    /** Function Button 9 Label */
    public static final String BTN_CMN_RESET_LABEL = "Reset";

    /** Function Button 10 Button Name */
    public static final String BTN_CMN_RETURN_BTN_NM = "btn10";

    /** Function Button 10 Event Name */
    public static final String BTN_CMN_RETURN_EVENT_NM = "CMN_Return";

    /** Function Button 10 Label */
    public static final String BTN_CMN_RETURN_LABEL = "Return";

    /** Parameter 0 */
    public static final int PRMS_00 = 0;

    /** Parameter 1 */
    public static final int PRMS_01 = 1;

    /** Parameter 2 */
    public static final int PRMS_02 = 2;

    /** Parameter 3 */
    public static final int PRMS_03 = 3;

    /** Parameter 4 */
    public static final int PRMS_04 = 4;

    /** Parameter 5 */
    public static final int PRMS_05 = 5;

    /** Parameter 6 */
    public static final int PRMS_06 = 6;

    /** Parameter 7 */
    public static final int PRMS_07 = 7;

    /** Parameter 8 */
    public static final int PRMS_08 = 8;

    /** Parameter 9 */
    public static final int PRMS_09 = 9;

    /** Parameter 10 */
    public static final int PRMS_10 = 10;

    /** Parameter 11 */
    public static final int PRMS_11 = 11;

    // add start 2016/05/19 CSA Defect#447
    /** Status is duplicated. Can not enter same status. */
    public static final String NSAM0476E = "NSAM0476E";

    /** If you selected @, it is required to enter @. */
    public static final String NSAM0081E = "NSAM0081E";

    /** [@] field has too many decimal digits entered. */
    public static final String ZZM9015E = "ZZM9015E";
    // add end 2016/05/19 CSA Defect#447
    // Add Start 2018/02/21 K.Fujimoto QC#30366
    /** @ should be less than @. */
    public static final String NSAM0746E = "NSAM0746E";

    /** EOL date: No Service */
    public static final String NO_SERVICE_DATE = "No Service Date";

    /** EOL date: No Contract */
    public static final String NO_CONTRACT_DATE = "No Contract Date";

    /** EOL date: Inactive */
    public static final String INACTIVE = "Inactive Date";
    // Add End   2018/02/21 K.Fujimoto QC#30366

    // START 2024/03/14 K.Watanabe [QC#63542, ADD]
    /** Item Name (Copy Model Name) */
    public static final String ITEM_NM_COPY_MDL_NM = "Copy Model Name";
    // END   2024/03/14 K.Watanabe [QC#63542, ADD]

}
