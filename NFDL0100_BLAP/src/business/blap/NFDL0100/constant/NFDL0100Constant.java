package business.blap.NFDL0100.constant;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public interface NFDL0100Constant {

    /**
     * VarCharConst : CL System Business ID
     */
    public static final String VAR_CHAR_CONST_KEY_CL_SYSTEM_BIZ_ID = "NFDL0100_CL_SYSTEM_BIZ_ID";

    /**
     * VarCharConst : CL System Parameter Name
     */
    public static final String VAR_CHAR_CONST_KEY_CL_SYSTEM_PARAM_NM = "NFDL0100_CL_SYSTEM_PARAM_NM";

    /**
     * Doc Type : BIL
     */
    public static final String DOC_TP_01 = "BIL";

    /**
     * Doc Type : INV
     */
    public static final String DOC_TP_02 = "INV";

    /**
     * Doc Type : DM
     */
    public static final String DOC_TP_03 = "DM";

    /**
     * Doc Type : CM
     */
    public static final String DOC_TP_04 = "CM";

    /**
     * CSV Header Name
     */
    public static final String[] HEADER_NAME = {"<Customer Account#>", "<Customer Account Name>", "<Bill To Cust Code>"
                                                , "<Bill To Cust Name>", "<Bill-To-Loc>", "<Contract/Order>", "<Invoice No>"
                                                , "<Bill No>", "<Doc Type>", "<Orig Amt>", "<Remain Amt>", "<Inv Date>"
                                                , "<Due Date>", "<Days Past Due>", "<PO Number>"};

    /** Number Constant Name */
    public static final String CONST_MAX_ROW_CNT = "AR_INV_PRNT_LIMIT";

}
