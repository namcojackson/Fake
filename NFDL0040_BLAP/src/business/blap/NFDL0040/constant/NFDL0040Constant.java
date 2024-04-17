package business.blap.NFDL0040.constant;
/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 04/18/2018   Hiatchi         Y.Takeno        Update          QC#20940
 *</pre>
 */
public interface NFDL0040Constant {

    public static final String STR_ON_ACCOUNT = "On Account";

    public static final String TAB_PROMISE = "Promise";

    public static final String TAB_DISPUTE = "Dispute";

    public static final String TABLE_A = "A";

    public static final String TABLE_B = "B";

    public static final String ORACLE_SEQ_CLT_PRMS_DTL_SQ = "CLT_PRMS_DTL_SQ";

    public static final String ORACLE_SEQ_CLT_DSPT_TRX_SQ = "CLT_DSPT_TRX_SQ";

    // START 2018/04/18 [QC#20940, ADD]
    public static final String VAR_CHAR_CONST_NFDL0040_CLT_DTL_NOTE_TXT = "NFDL0040_CLT_DTL_NOTE_TXT";

    public static final int MAX_LENGTH_CLT_DTL_NOTE_TXT = 4000;
    // END   2018/04/18 [QC#20940, ADD]
}
