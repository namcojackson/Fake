/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8810.constant;

/**
 *<pre>
 * NYEL8810Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         Q09079          Create          N/A
 * 2018/04/24   Fujitsu         Q10814          Update          QC#23516
 * 2018/08/23   Fujitsu         Q10814          Update          QC#21387
 * 2018/12/18   Fujitsu         Q10627          Update          QC#29682
 *</pre>
 */
public class NYEL8810Constant {

    /** Business ID */
    public static final String BIZ_ID = "NYEL8810";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NYEL8810Scrn00";
    
    // --------------------------------
    // Message ID
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** REASSIGN Mode */
    public static final String REASSIGN_MODE = "2";

    /** Process Status Active */
    public static final String PROC_STS_ACTIVE = "1";

    /** Process Status Complete */
    public static final String PROC_STS_COMPLETE = "9";

    /** Task Status Active */
    public static final String TASK_STS_ACTIVE = "1";

    /** Task Status Complete */
    public static final String TASK_STS_COMPLETE = "9";

    /** Display Mode */
    public static final String DISP_MODE_GROUP = "1";

    /** Display Mode */
    public static final String DISP_MODE_GROUP_PROCESS = "2";

    /** Display Mode */
    public static final String DISP_MODE_GROUP_PROCESS_TASK = "3";

    /** View Image */
    public static final String VIEW_IMG_OFF = "9";

    /** View Image */
    public static final String VIEW_IMG_PLUS = "1";

    /** View Image */
    public static final String VIEW_IMG_MINUS = "2";

    /** Row Indent */
    public static final String ROW_INDENT = "  ";
    
    /** New Line */
    public static final String TEXTAREA_NEWLINE ="&#13;";
    
    /** Possible Action */
    public static final String PSBL_ACT_APPROVE ="Approve";

    /** Possible Action */
    public static final String PSBL_ACT_FYI ="FYI";

    /** Possible Action */
    public static final String PSBL_ACT_CANCEL ="Cancel";

    /** Min MAX_HHMMssSSSS */
    public static final String MIN_HHMMssSSSS = "000000000";

    /** Max MAX_HHMMssSSSS */
    public static final String MAX_HHMMssSSSS = "235959999";
    
    /** Administrator Group */
    public static final String ADMIN_GRP = "ADMIN";
    
    /** Initial start timestamp range */
    public static final int INIT_START_TS_RANGE = 60;

// QC#23516 ADD START 2018/04/24
    /** omit */
    public static final String TEXTAREA_OMIT = " ...";

    /** Comment Max Length */
    public static final int MAX_CMNT_L = 400;
// QC#23516 ADD END 2018/04/24

// QC#21387 ADD START 2018/08/23
    // --------------------------------
    // Search Parameter
    // --------------------------------
    /**
     * LIMIT_DL_ROWNUM : 65000
     */
    public static final int LIMIT_DL_ROWNUM = 65000;

    /**
     * Max Fetch Size : 1000
     */
    public static final int MAX_FETCH_SIZE = 1000;
// QC#21387 ADD END 2018/08/23

// QC#29682 ADD START 2018/12/18
    /**
     * Submission Task Name
     */
    public static final String TASK_ID_SUBMISSION = "Workflow Submission";

    /**
     * Approval Task Name
     */
    public static final String TASK_ID_APPROVAL = "Workflow Approval";
// QC#29682 ADD END   2018/12/18

}
