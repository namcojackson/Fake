/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/10/14   Fujitsu         I.Kondo         Create          N/A
 *</pre>
 */
package business.blap.NPAL0100.constant;

public interface NPAL0100Constant {

    /**
     * For Apl ID
     */
    enum SCRN_APL_ID {
        /** INIT */
        NPAL0100_INIT,
        /** Apply */
        NPAL0100Scrn00_Apply,
        /** Cancel */
        NPAL0100Scrn00_Cancel,
        /** CMN_Clear */
        NPAL0100Scrn00_CMN_Clear,
        /** CMN_Close */
        NPAL0100Scrn00_CMN_Close,
        /** Edit */
        NPAL0100Scrn00_Edit,
        /** PagePrev */
        NPAL0100Scrn00_PagePrev,
        /** PageNext */
        NPAL0100Scrn00_PageNext,
        /** Submit */
        NPAL0100Scrn00_CMN_Submit
    }

    /**
     * For Message Code
     */
    enum MESSAGE_CD {
        /** NPAM0014E */
        NPAM0014E
        /** NPAM0039W */
        , NPAM0039E
    }

    /** MDSE Type Code for Kit */
    String MDSE_TP_CD_KIT = "3";

    /**
     * Type of View for MDSE_TP_CD_ITEM
     */
    enum MDSE_TP_CD_VIEW {
        /** Yes */
        Yes,
        /** No */
        No
    }

    // SQLID
    /** SQLID for BILL_TO_CUST_CD */
    String SQL_ID_BILL_TO_CUST_CD = "052";

    /** SQLID for MDSE_TP_CD */
    String SQL_ID_MDSE_TP_CD = "026";

    /** SQLID for PO_SER_NUM */
    String SQL_ID_PO_SER_NUM = "001";

    /** SQLID for PO_SER_NUM(Not Inventory Control) */
    String SQL_ID_PO_SER_NUM_NO_INV_CTR = "002";

    /** SER_OWNR_ID */
    String SER_OWNR_ID = "08";
    
    /** SUBMIT */
    String SUBMIT = "SUBMIT";
    
    /** INIT */
    String INIT = "INIT";
    
    /** APPLY */
    String APPLY = "APPLY";
    
    /** CLEAR */
    String CLEAR = "CLEAR";
    
    /** DateFormat : yyMMddHHmmssSSS */
    String YYYYMMDD_HHMMSS_SSS = "yyyyMMddHHmmssSSS";
    
    /** SORT_BY_KEYS */
    String SORT_BY_KEYS  = "SORT_BY_KEYS";
    
    /** SORT_BY_LATEST_TS */
    String SORT_BY_LATEST_TS  = "SORT_BY_LATEST_TS";

    // SQL Parameter
    /**
     * SQL Parameter for BILL_TO_CUST
     */
    enum SQL_PARAM_BILL_TO_CUST {
        /** glblCmpyCd01 */
        glblCmpyCd01,
        /** billToCustCd01 */
        billToCustCd01
    }

    /**
     * SQL Parameter for BILL_TO_CUST
     */
    enum SQL_PARAM_MDSE_TP_CD {
        /** glblCmpyCd01 */
        glblCmpyCd01,
        /** mdseCd01 */
        mdseCd01
    }

    /**
     * SQL Parameter for PO_SER_NUM
     */
    enum SQL_PARAM_PO_SER_NUM {
        /** glblCmpyCd01 */
        glblCmpyCd01,
        /** serOwnrId01 */
        serOwnrId01,
        /** mdseCd01 */
        mdseCd01,
        /** keyInfoCd_0101 */
        keyInfoCd_0101,
        /** keyInfoCd_0201 */
        keyInfoCd_0201,
        /** keyInfoCd_0301 */
        keyInfoCd_0301,
        /** keyInfoCd_0401 */
        keyInfoCd_0401,
        /** keyInfoCd_0501 */
        keyInfoCd_0501,
        /** keyInfoCd_0601 */
        keyInfoCd_0601,
        /** keyInfoCd_0701 */
        keyInfoCd_0701,
        /** keyInfoCd_0801 */
        keyInfoCd_0801,
        /** keyInfoCd_0901 */
        keyInfoCd_0901
    }

    // Message Parameter
    /** Message Parameter for BILL_TO_CUST_CD */
    String MESSAGE_PARAM_BILL_TO_CUST_CD = "Bill To Customer Name";

    /** Message Parameter for MESSAGE_PARAM_MDSE_TP_CD */
    String MESSAGE_PARAM_MDSE_TP_CD = "Merchandise Type Code";

    // Proc FLG at Detail
    /** Record Status is NEW */
    String PROC_FLG_NEW = "1";

    /** Record Status is UPDATE */
    String PROC_FLG_UPDATE = "2";

    /** Record Status is not New, not UPDATE */
    String PROC_FLG_OTHER = "3";

    /** Error Flg Yes */
    String ERROR_FLG_ON = "y";

    /** Error Flg No */
    String ERROR_FLG_OFF = "n";

    /**
     * PO_SER_NUM Sequence number
     */
    String PO_SER_NUM_SQ = "PO_SER_NUM_SQ";

    /**
     * CMsg table modifier.
     */
    enum CMSG_TABLE_MDF {
        /** A1 */
        A1
        /** A2 */
        , A2
        /** B1 */
        , B1
        /** B2 */
        , B2
    }

    /**
     * SMsg table modifier.
     */
    enum SMSG_TABLE_MDF {
        /** B1 */
        B1
        /** B2 */
        , B2
        /** O1 */
        , O1
        /** O2 */
        , O2
    }

    /** TABLE_INIT_SORT_KEY */
    String TABLE_INIT_SORT_KEY = "xxRowNum_B2";

    enum PAGING_TYPE {
        /** PREV */
        PREV
        /** NEXT */
        , NEXT
    }
}
