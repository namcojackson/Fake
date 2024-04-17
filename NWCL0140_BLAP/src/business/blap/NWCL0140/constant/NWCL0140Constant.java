/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0140.constant;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NWCL0140Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NWCL0140";

    /**
     * Eligible Account and Bill To area Table
     */
    public static final String TABLE_A = "A";

    /**
     * Exclusion/Inclusion Order Category area Table
     */
    public static final String TABLE_B = "B";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NWCL0140_INIT = "NWCL0140_INIT";

    /**
     * Event Name : DeleteLineEligAcct
     */
    public static final String EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ACCT = "NWCL0140Scrn00_DeleteLineEligAcct";

    /**
     * Event Name : DeleteLineEligOrdCatg
     */
    public static final String EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ORD_CATG = "NWCL0140Scrn00_DeleteLineEligOrdCatg";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NWCL0140_CMN_RESET = "NWCL0140Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NWCL0140_CMN_SUBMIT = "NWCL0140Scrn00_CMN_Submit";

    /**
     * Event Name : AddLineEligAcct
     */
    public static final String EVENT_NM_NWCL0140_ADD_LINE_ELIG_ACCT = "NWCL0140Scrn00_AddLineEligAcct";

    /**
     * Event Name : AddLineEligOrdCatg
     */
    public static final String EVENT_NM_NWCL0140_ADD_LINE_ELIG_ORD_CATG = "NWCL0140Scrn00_AddLineEligOrdCatg";

    /**
     * Event Name : PageJump
     */
    public static final String EVENT_NM_NWCL0140_PAGE_JUMP = "NWCL0140Scrn00_PageJump";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NWCL0140_PAGE_NEXT = "NWCL0140Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NWCL0140_PAGE_PREV = "NWCL0140Scrn00_PagePrev";

    /**
     * Event Name : PageJumpEL
     */
    public static final String EVENT_NM_NWCL0140_PAGE_JUMP_EL = "NWCL0140Scrn00_PageJumpEL";

    /**
     * Event Name : PageNextEL
     */
    public static final String EVENT_NM_NWCL0140_PAGE_NEXT_EL = "NWCL0140Scrn00_PageNextEL";

    /**
     * Event Name : PagePrevEL
     */
    public static final String EVENT_NM_NWCL0140_PAGE_PREV_EL = "NWCL0140Scrn00_PagePrevEL";

    /**
     * Event Name : SearchEligAcct
     */
    public static final String EVENT_NM_NWCL0140_SEARCH_ELIG_ACCT = "NWCL0140Scrn00_SearchEligAcct";

    /**
     * Event Name : PageJumpEX
     */
    public static final String EVENT_NM_NWCL0140_PAGE_JUMP_EX = "NWCL0140Scrn00_PageJumpEX";

    /**
     * Event Name : PageNextEX
     */
    public static final String EVENT_NM_NWCL0140_PAGE_NEXT_EX = "NWCL0140Scrn00_PageNextEX";

    /**
     * Event Name : PagePrevEX
     */
    public static final String EVENT_NM_NWCL0140_PAGE_PREV_EX = "NWCL0140Scrn00_PagePrevEX";

    /**
     * Event Name : SearchEligOrdCatg
     */
    public static final String EVENT_NM_NWCL0140_SEARCH_ELIG_ORD_CATG = "NWCL0140Scrn00_SearchEligOrdCatg";

    /**
     * Event Name : OpenWin_ReasonH
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_REASON_H = "NWCL0140Scrn00_OpenWin_ReasonH";

    /**
     * Event Name : OpenWin_ReasonD
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_REASON_D = "NWCL0140Scrn00_OpenWin_ReasonD";

    /**
     * Event Name : EVENT_NM_NWCL0140_TBL_COL_SORT
     */
    public static final String EVENT_NM_NWCL0140_TBL_COL_SORT = "NWCL0140Scrn00_TBLColumnSort";

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
     * Detail rows exceeds [@]. No more rows can be added.
     */
    public static final String NWCM0136E = "NWCM0136E";

    /**
     * It is being updated by another user. Please start over from the
     * search.
     */
    public static final String NWCM0137E = "NWCM0137E";

    /**
     * It failed to register @ Table.[@]
     */
    public static final String NWCM0109E = "NWCM0109E";

    /**
     * It failed to update @ Table.[@]
     */
    public static final String NWCM0110E = "NWCM0110E";

    /**
     * The data you entered has already been registered.
     */
    public static final String NWCM0138E = "NWCM0138E";

    /**
     * [@] field is mandatory.
     */
    public static final String NWCM0139E = "NWCM0139E";

    /**
     * @ is duplicated.
     */
    public static final String NWCM0140E = "NWCM0140E";

    /**
     * [@] is already registered.
     */
    public static final String NWCM0141E = "NWCM0141E";

    /**
     * No Details are selected.
     */
    public static final String NWCM0127E = "NWCM0127E";

    /**
     * It failed to get [@].(@)
     */
    public static final String NWCM0112E = "NWCM0112E";

    /**
     * The data does not exist in [@]. [@]
     */
    public static final String NWCM0142E = "NWCM0142E";

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
     * DB_PARAM_BILL_TO_CUST_ACCT_CD
     */
    public static final String DB_PARAM_BILL_TO_CUST_ACCT_CD = "billToCustAcctCd";

    /**
     * DB_PARAM_BILL_TO_CUST_CD
     */
    public static final String DB_PARAM_BILL_TO_CUST_CD = "billToCustCd";

    /**
     * DB_PARAM_DS_ORD_CATG_CD
     */
    public static final String DB_PARAM_DS_ORD_CATG_CD = "dsOrdCatgCd";

    /**
     * DB_PARAM_DS_ORD_CATG_DESC_TXT
     */
    public static final String DB_PARAM_DS_ORD_CATG_DESC_TXT = "dsOrdCatgDescTxt";

    /**
     * DB_PARAM_DS_ORD_TP_CD
     */
    public static final String DB_PARAM_DS_ORD_TP_CD = "dsOrdTpCd";

    /**
     * DB_PARAM_SELL_TO_CUST_CD
     */
    public static final String DB_PARAM_SELL_TO_CUST_CD = "sellToCustCd";

    /**
     * DB_PARAM_ATTRB_KEY_NM
     */
    public static final String DB_PARAM_ATTRB_KEY_NM = "attrbKeyNm";

    /**
     * DB_PARAM_CFS_ELIG_ACCT_PK_LIST
     */
    public static final String DB_PARAM_CFS_LEASE_ELIG_ACCT_PK_LIST = "cfsLeaseEligAcctPkList";

    /**
     * DB_PARAM_CFS_LEASE_ELIG_ORD_CATG_PK_LIST
     */
    public static final String DB_PARAM_CFS_LEASE_ELIG_ORD_CATG_PK_LIST = "cfsLeaseEligOrdCatgPkList";

    /**
     * DB_PRAM_ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    /**
     * DB_PARAM_DS_ORD_TP_DESC_TXT
     */
    public static final String DB_PARAM_DS_ORD_TP_DESC_TXT = "dsOrdTpDescTxt";

    /**
     * DB_PARAM_RGTN_STS_CD
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    // =================================================
    // DB Result Columns
    // =================================================
    /**
     * CFS_ELIG_ACCT_PK
     */
    public static final String CFS_LEASE_ELIG_ACCT_PK = "CFS_LEASE_ELIG_ACCT_PK";

    /**
     * BILL_TO_CUST_ACCT_CD
     */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /**
     * BILL_TO_CUST_CD
     */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /**
     * DS_ACCT_NM
     */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /**
     * ATTRB_VAL_NUM
     */
    public static final String ATTRB_VAL_NUM = "ATTRB_VAL_NUM";

    /**
     * CFS_LEASE_ELIG_ORD_CATG_PK
     */
    public static final String CFS_LEASE_ELIG_ORD_CATG_PK = "CFS_LEASE_ELIG_ORD_CATG_PK";

    /**
     * DS_ORD_CATG_CD
     */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /**
     * DS_ORD_TP_CD
     */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /**
     * ORD_CATG_INCL_FLG
     */
    public static final String ORD_CATG_INCL_FLG = "ORD_CATG_INCL_FLG";

    /**
     * DS_ORD_CATG_DESC_TXT
     */
    public static final String DS_ORD_CATG_DESC_TXT = "DS_ORD_CATG_DESC_TXT";

    /**
     * DS_ORD_TP_DESC_TXT
     */
    public static final String DS_ORD_TP_DESC_TXT = "DS_ORD_TP_DESC_TXT";

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
     * Delete Line
     */
    public static final String DELETE = "Delete Line";

    /**
     * INV_THRHD
     */
    public static final String INV_THRHD = "INV_THRHD";

    /**
     * XX_CHK_BOX_A1
     */
    public static final String XX_CHK_BOX_EL = "xxChkBox_EL";

    /**
     * XX_CHK_BOX_EX
     */
    public static final String XX_CHK_BOX_EX = "xxChkBox_EX";

    /** Category */
    public static final String MSG_PARAM_CATG = "Category";

    /** Order Category */
    public static final String MSG_PARAM_ORD_CATG = "Order Category";

    /** Reason */
    public static final String MSG_PARAM_REASON = "Reason";

    /** Account Number */
    public static final String MSG_PARAM_ACCT_NUM = "Account Number";

    /** Location Number */
    public static final String MSG_PARAM_LOC_NUM = "Location Number";

    /** BILL_TO_CUST */
    public static final String BILL_TO_CUST = "BILL_TO_CUST";

    /**
     * CFS_LEASE_ELIG_ACCT
     */
    public static final String CFS_LEASE_ELIG_ACCT = "CFS_LEASE_ELIG_ACCT";

    /**
     * CFS_LEASE_ATTRB
     */
    public static final String CFS_LEASE_ATTRB = "CFS_LEASE_ATTRB";

    /**
     * CFS_LEASE_ELIG_ORD_CATG
     */
    public static final String CFS_LEASE_ELIG_ORD_CATG = "CFS_LEASE_ELIG_ORD_CATG";

    /**
     * DS_ORD_CATG
     */
    public static final String DS_ORD_CATG = "CFS_LEASE_EDS_ORD_CATGLIG_ORD_CATG";

    /**
     * DS_ORD_TP
     */
    public static final String DS_ORD_TP = "DS_ORD_TP";
}
