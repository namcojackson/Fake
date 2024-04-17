/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0150.constant;

/**
 * <pre>
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 * 09/21/2017   Fujitsu         H.Ikeda         Update          QC#20381
 * 09/21/2017   Fujitsu         H.Ikeda         Update          QC#20382
 * 01/04/2018   Fujitsu         W.Honda         Update          QC#21706
 * 04/18/2022   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWCL0150Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NWCL0150";

    /**
     * Eligible Account and Bill To area Table
     */
    public static final String TABLE_A = "A";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NWCL0150_INIT = "NWCL0150_INIT";

    // START 2022/04/18 J.Evangelista [QC#59934,ADD]
    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NWCL0150_CMN_CLEAR = "NWCL0150Scrn00_CMN_Clear";
    // END 2022/04/18 J.Evangelista [QC#59934,ADD]

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NWCL0150_CMN_RESET = "NWCL0150Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NWCL0150_CMN_SUBMIT = "NWCL0150Scrn00_CMN_Submit";

    /**
     * Event Name : PageJump
     */
    public static final String EVENT_NM_NWCL0150_PAGE_JUMP = "NWCL0150Scrn00_PageJump";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NWCL0150_PAGE_NEXT = "NWCL0150Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NWCL0150_PAGE_PREV = "NWCL0150Scrn00_PagePrev";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NWCL0150_SEARCH = "NWCL0150Scrn00_Search";

    /**
     * Event Name : EVENT_NM_NWCL0150_TBL_COL_SORT
     */
    public static final String EVENT_NM_NWCL0150_TBL_COL_SORT = "NWCL0150Scrn00_TBLColumnSort";

    // =================================================
    // Message Code
    // =================================================
    /**
     * No search results found.
     */
    public static final String NWCM0003I = "NWCM0003I";

    /**
     * Search ended normally but not all data is displayed since the
     * result exceeded the maximum.
     */
    public static final String NWCM0135I = "NWCM0135I";

    /**
     * It is being updated by another user. Please start over from the
     * search.
     */
    public static final String NWCM0137E = "NWCM0137E";

    /**
     * The data does not exist in [@]. [@]
     */
    public static final String NWCM0142E = "NWCM0142E";

    /**
     * It failed to update @ Table.[@]
     */
    public static final String NWCM0110E = "NWCM0110E";

    // QC#20382 2017/09/21 Add Start
    /** It failed to get [@].(@) */
    public static final String NWCM0112E = "NWCM0112E";
    // QC#20382 2017/09/21 Add End

    /**
     * ZZZM9003I:The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    // =================================================
    // DB Query Parameters
    // =================================================
    /**
     * DB_PARAM_GLBL_CMPY_CD
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB_PARAM_CPO_ORD_NUM
     */
    public static final String DB_PARAM_CPO_ORD_NUM = "cpoOrdNum";

    /**
     * DB_PARAM_CFS_LEASE_PKG_HLD_FLG
     */
    public static final String DB_PARAM_CFS_LEASE_PKG_HLD_FLG = "cfsLeasePkgHldFlg";

    /**
     * DB_PARAM_INV_NUM
     */
    public static final String DB_PARAM_INV_NUM = "invNum";

    /**
     * DB_PARAM_LEASE_PKG_CRAT_FLG
     */
    public static final String DB_PARAM_LEASE_PKG_CRAT_FLG = "leasePkgCratFlg";

    /**
     * DB_PARAM_ATTRB_KEY_NM
     */
    public static final String DB_PARAM_ATTRB_KEY_NM = "attrbKeyNm";

    /**
     * DB_PRAM_ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    // =================================================
    // DB Result Columns
    // =================================================
    // QC#20381 2017/09/21 Add Start
    /**
     * CFS_PO_NUM
     */
    public static final String CFS_PO_NUM = "CFS_PO_NUM";
    // QC#20381 2017/09/21 Add Start
    /**
     * CPO_ORD_NUM
     */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /**
     * INV_NUM
     */
    public static final String INV_NUM = "INV_NUM";

    /**
     * CFS_LEASE_PKG_HLD_FLG
     */
    public static final String CFS_LEASE_PKG_HLD_FLG = "CFS_LEASE_PKG_HLD_FLG";

    /**
     * ATTRB_VAL_NUM
     */
    public static final String ATTRB_VAL_NUM = "ATTRB_VAL_NUM";

    /**
     * DS_ACCT_NUM
     */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /**
     * DS_ACCT_NM
     */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /**
     * CFS_PO_AMT
     */
    public static final String CFS_PO_AMT = "CFS_PO_AMT";

    /**
     * INV_CPLT_AMT_RATE
     */
    public static final String INV_CPLT_AMT_RATE = "INV_CPLT_AMT_RATE";

    /**
     * CR_REBIL_HLD_FLG
     */
    public static final String CR_REBIL_HLD_FLG = "CR_REBIL_HLD_FLG";

    /**
     * LEASE_PKG_CRAT_FLG
     */
    public static final String LEASE_PKG_CRAT_FLG = "LEASE_PKG_CRAT_FLG";

    /**
     * CFS_MAN_HLD_ACT_TP_CD
     */
    public static final String CFS_MAN_HLD_ACT_TP_CD = "CFS_MAN_HLD_ACT_TP_CD";

    /**
     * CFS_LEASE_PKG_PO_HDR_PK
     */
    public static final String CFS_LEASE_PKG_PO_HDR_PK = "CFS_LEASE_PKG_PO_HDR_PK";

    // QC#21706 2018/01/04 Add Start
    /**
     * PO_INFO_PROC_FLG
     */
    public static final String PO_INFO_PROC_FLG = "PO_INFO_PROC_FLG";
    // QC#21706 2018/01/04 Add End

    /**
     * EZUPTIME
     */
    public static final String EZUPTIME = "EZUPTIME";

    /**
     * EZUPTIMEZONE
     */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    // =================================================
    // Other
    // =================================================

    /**
     * SUBMIT
     */
    public static final String SUBMIT = "Submit";

    /**
     * INV_THRHD
     */
    public static final String INV_THRHD = "INV_THRHD";

    /**
     * CFS_LEASE_ATTRB
     */
    public static final String CFS_LEASE_ATTRB = "CFS_LEASE_ATTRB";

    /**
     * CFS_LEASE_PKG_PO_HDR
     */
    public static final String CFS_LEASE_PKG_PO_HDR = "CFS_LEASE_PKG_PO_HDR";

    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;
}
