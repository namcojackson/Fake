package business.blap.NFCL3020.constant;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 09/05/2016   Fujitsu         C.Tanaka        Update          QC#5521
 * 2018/03/08   Fujitsu         H.Ikeda         Update          QC#24469
 * 2018/03/16   Fujitsu         H.Ikeda         Update          QC#21737
 * 2018/04/06   Fujitsu         H.Ikeda         Update          QC#25338
 * 2018/06/04   Fujitsu         Y.Matsui        Update          QC#25368
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public interface NFCL3020Constant {

    /**
     * Asterisk
     */
    String ASTERISK = "*";

    /** Business ID */
    public static final String BIZ_ID = "NFCL3020";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NFCM0865E = "NFCM0865E";

    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    // Add Start 2018/03/08 S21_NA#24469
    /** TYPE_INIT */
    static final String TYPE_INIT = "init";

    /** TYPE_ADD */
    static final String TYPE_ADD = "add";

    /** TYPE_DEL */
    static final String TYPE_DEL = "del";
    // Add END   2018/03/08 S21_NA#24469

    // START 2018/03/16 H.Ikeda [QC#21737,ADD]
    // --------------------------------
    // CSV File Download
    // --------------------------------
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NFCL3020_Batch_Entry";

    /** CSV Header For Download */
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] { "ChkBox"
                                                                        ,"Receipt Doc#"
                                                                        ,"Receipt#"
                                                                        ,"Type"
                                                                        ,"Date"
                                                                        ,"Amount"
                                                                        ,"Customer#"
                                                                        ,"Customer Name"
                                                                        ,"Location"
                                                                        ,"Status"
                                                                        ,"Apply To"
                                                                        ,"Inv type"
                                                                        ,"Amount Applied"
                                                                       };
    // END   2018/03/16 H.Ikeda [QC#21737,ADD]
    // START 2018/04/06 H.Ikeda [QC#25338, ADD]
    /**
     * Deal Sequence Number : 
     */
    public static final String DEAL_SQ_NUM = "00000001";

    /**
     * Deal Sequence Detail Number
     */
    public static final String DEAL_SQ_DTL_NUM = "0001";

    /**
     * Receipt Detail Number : 0001
     */
    public static final String RCPT_DTL_NUM = "0001";

    /**
     * Apply Return Code : UnProcess
     */
    public static final String APPLY_RTNCD_UN_PROCCES = "0";

    /**
     * Apply Return Code : Normal
     */
    public static final String APPLY_RTNCD_NORMAL = "1";

    /**
     * Apply Return Code : Cash Application Error
     */
    public static final String APPLY_RTNCD_CASHAPP_ERROR = "2";

    /**
     * Apply Return Code : Exclusion Error
     */
    public static final String APPLY_RTNCD_EXCLUSION_ERROR = "3";

    /**
     * Apply Return Code : Other Error
     */
    public static final String APPLY_RTNCD_OTHERS_ERROR = "4";

    // START 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Message Kind error
     */
    public static final String MESSAGE_KIND_ERROR = "E";

    // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * AR Receipt Type Dummy Code
     */
    public static final String AR_RCPT_TP_DUMMY = "99";

    /**
     * VarChar Constant Key : AR_RCPT_TOC_CD 
     */
    public static final String VAR_CHAR_CONST_AR_RCPT_TOC_CD_KEY = "AR_RCPT_TOC_CD";

    /**
     * VarChar Constant Key : AR_RCPT_PROD_CD
     */
    public static final String VAR_CHAR_CONST_AR_RCPT_PROD_CD_KEY = "AR_RCPT_PROD_CD";
    // END   2018/04/06 H.Ikeda [QC#25338, ADD]

    // START 2018/06/04 Y.Matsui [QC#25368,MOD]
    /**
     * VarChar Constant Key : AR_SUB_SYS_ID
     */
    public static final String VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY = "AR_SUB_SYS_ID";
    // END   2018/06/04 Y.Matsui [QC#25368,MOD]
}
