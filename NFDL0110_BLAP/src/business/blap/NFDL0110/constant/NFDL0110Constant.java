/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0110.constant;

/**
 *<pre>
 * Customer Care
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2016   CSAI            K.Lee           Create          Initial
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 *</pre>
 */
public interface NFDL0110Constant {

    /**
     * VarCharConst : CL System Business ID
     */
    public static final String VAR_CHAR_CONST_KEY_CL_SYSTEM_BIZ_ID = "NFDL0110_CL_SYSTEM_BIZ_ID";

    /**
     * VarCharConst : CL System Parameter Name
     */
    public static final String VAR_CHAR_CONST_KEY_CL_SYSTEM_PARAM_NM = "NFDL0110_CL_SYSTEM_PARAM_NM";

    /**
     * Line Type : Service
     */
    public static final String LINE_TP_01 = "Service";

    /**
     * Line Type : CONS
     */
    public static final String LINE_TP_02 = "CONS";

    /**
     * Line Type : Usage
     */
    public static final String LINE_TP_03 = "Usage";

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * CSV Header Name
     */
    public static final String[] HEADER_NAME = {"Customer Account#", "Customer Account Name", "Bill To#", "Bill To Name"
                          , "Bill-To-Loc", "Contract/Order", "Invoice No", "Line Type", "Bill From", "Bill To"
                          , "Orig Amt", "Remain Amt", "Due Date", "Days Past Due", "CI Number"
                          , "Create Date", "Status" };
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    // START 2018/10/11 J.Kim [QC#28570,ADD]
    /** Tickets Status (Customer Care Invoice) */
    public static final String CI_TICKET_STS_CLOSE = "CLOSE";
    // END 2018/10/11 J.Kim [QC#28570,ADD]
}
