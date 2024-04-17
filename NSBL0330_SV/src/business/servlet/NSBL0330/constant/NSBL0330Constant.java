/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0330.constant;

/**
 *<pre>
 * Service Request By Summary Criteria
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 *</pre>
 */
public class NSBL0330Constant {

    /** Application ID */
    public static final String BUSINESS_APPLICATION_ID = "NSBL0330";

    /** Screen ID */
    public static final String SCREEN_ID = BUSINESS_APPLICATION_ID + "Scrn00";

    /** Function Code: Inquiry */
    public static final String EZD_FUNC_CD_INQ = "20";

    // [0]:Button Name [1]:Event Name [2]:Button Label
    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Function Button Page Preview */
    public static final String[] BTN_PAGE_PREV = {"PagePrev", "PagePrev", "Prev" };

    /** Function Button Page Next */
    public static final String[] BTN_PAGE_NEXT = {"PageNext", "PageNext", "Next" };

    /** Function Button Manager Preview */
    public static final String[] BTN_PREV_MGR = {"PrevMgr", "PrevMgr", "Prev Mgr" };

    /** Function Button Manager Next */
    public static final String[] BTN_NEXT_MGR = {"NextMgr", "NextMgr", "Next Mgr" };

    /** UNDER_BAR: _ */
    public static final String UNDER_BAR = "_";

    /** Parameter Length NSBL0340: 10 */
    public static final int PARAM_LENGTH_NSBL0340 = 10;

    /** Parameter Index DS Service Call Type Code: 6 */
    public static final int PARAM_IDX_DS_SVC_CALL_TP_CD = 6;

    /** Parameter Index FSR Service Task Status Relation PK: 7 */
    public static final int PARAM_IDX_FSR_SVC_TASK_STS_RELN_PK = 7;

    /** Parameter Index Technician Code: 8 */
    public static final int PARAM_IDX_TECH_CD = 8;

    /** Parameter Index Service Call Source Type Code: 9 */
    public static final int PARAM_IDX_SVC_CALL_SRC_TP_CD = 9;

    /** dsSvcCallTpCd */
    public static final String DS_SVC_CALL_TP_CD = "dsSvcCallTpCd";

    /** fsrSvcTaskStsRelnPk */
    public static final String FSR_SVC_TASK_STS_RELN_PK = "fsrSvcTaskStsRelnPk";

    /** techCd */
    public static final String TECH_CD = "techCd";

    /** svcCallSrcTpCd */
    public static final String SVC_CALL_SRC_TP_CD = "svcCallSrcTpCd";

    // START 2016/03/22 K.Yamada [QC#5735, ADD]
    /** Tab Name : Task Smry */
    public static final String TAB_NAME_NSBL0340 = "Task Smry";
    // END 2016/03/22 K.Yamada [QC#5735, ADD]
}
