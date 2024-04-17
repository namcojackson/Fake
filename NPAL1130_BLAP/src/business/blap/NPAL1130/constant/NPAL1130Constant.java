/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1130.constant;

import static  parts.common.EZDSchemaItemDefines.TYPE_HANKAKUEISU;
import static  parts.common.EZDSchemaItemDefines.TYPE_SEISU_SYOSU;
import static  parts.common.EZDSchemaItemDefines.TYPE_NENTSUKIHI;

/** 
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/08/2017   CITS            S.Katsuma       Update          QC#19656
 * 11/07/2017   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 * 2018/04/11   CITS            K.Ogino         Update          QC#21229
 * 2018/08/21   CITS            T.Hakodate      Update          QC#27636
 * 05/08/2023   CSA             G.Quan          Update          QC#61439
 *</pre>
 */
public class NPAL1130Constant {

    // =================================================
    // DB Parameter
    // =================================================
    /**
     * DB Parameter Name : MRP_INFO_RGTN_STS_CD
     */

    public static final String DB_PARAM_MRP_INFO_RGTN_STS_CD = "mrpInfoRgtnStsCd";

    /**
     * DB Parameter Name : cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Parameter Name : XX_MDSE_CD
     */
    public static final String DB_PARAM_XX_MDSE_CD = "xxMdseCd";

    /**
     * DB Parameter Name : etaEtdDt
     */
    public static final String DB_PARAM_ETA_ETD_DT = "etaEtdDt";

    // START 2017/08/01 S.Katsuma [QC#19656,ADD]
    /**
     * DB Parameter Name : invtyLocCd
     */
    public static final String DB_PARAM_INVTY_LOC_CD = "xxInvtyLocCd";
    // END 2017/08/01 S.Katsuma [QC#19656,ADD]

    /**
     * Sort By Parameter Name : sort by ASC
     */
    public static final String SORT_BY_ASC = "ASC";

    /**
     * Add QC#21229
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * Add QC#21229
     * DB Param Name: MDSE code
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    // =================================================
    // Constant Value
    // =================================================
    /**
     * Constant Value Name : NPZC1010_INVTY_STK
     */
    public static final String NPZC1010_INVTY_STK = "NPZC1010_INVTY_STK";

    /**
     * Constant Value Name : NPZC1010_INVTY_IN
     */
    public static final String NPZC1010_INVTY_IN = "NPZC1010_INVTY_IN";

    /**
     * Constant Value Name : NPZC1010_INVTY_OUT
     */
    public static final String NPZC1010_INVTY_OUT = "NPZC1010_INVTY_OUT";

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /**
     * Constant Value Name : NPZC1010_ORDER_PO
     */
    public static final String NPZC1010_ORDER_PO = "NPZC1010_ORDER_PO";

    /**
     * Constant Value Name : NPZC1010_ORDER_POREQ
     */
    public static final String NPZC1010_ORDER_POREQ = "NPZC1010_ORDER_POREQ";

    /**
     * Constant Value Name : NPZC1010_ORDER_INVREQ
     */
    public static final String NPZC1010_ORDER_INVREQ = "NPZC1010_ORDER_INVREQ";

    /**
     * Constant Value Name : NPZC1010_ORDER_WO
     */
    public static final String NPZC1010_ORDER_WO = "NPZC1010_ORDER_WO";

    /**
     * Constant Value Name : NPZC1010_ORDER_CPO
     */
    public static final String NPZC1010_ORDER_CPO = "NPZC1010_ORDER_CPO";

    /**
     * Constant Value Name : NPZC1010_ORDER_TECHREQ
     */
    public static final String NPZC1010_ORDER_TECHREQ = "NPZC1010_ORDER_TECHREQ";

    /**
     * Constant Value Name : NPZC1010_ORDER_REMAN
     */
    public static final String NPZC1010_ORDER_REMAN = "NPZC1010_ORDER_REMAN";
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

    // =================================================
    // Event Name
    // =================================================
    /** NPAL1130_INIT */
    public static final String NPAL1130_INIT = "NPAL1130_INIT";

    /** NPAL1130Scrn00_CMN_Reset */
    public static final String NPAL1130_CMN_RESET = "NPAL1130Scrn00_CMN_Reset";

    /** NPAL1130Scrn00_Search */
    public static final String NPAL1130_SEARCH = "NPAL1130Scrn00_Search";

    /** NPAL1130Scrn00_PagePrev */
    public static final String NPAL1130_PAGEPREV = "NPAL1130Scrn00_PagePrev";

    /** NPAL1130Scrn00_PageNext */
    public static final String NPAL1130_PAGENEXT = "NPAL1130Scrn00_PageNext";

    /** NPAL1130Scrn00_SearchMdseName */
    public static final String NPAL1130_SEARCH_MDSE_NAME = "NPAL1130Scrn00_SearchMdseName";

    /** NPAL1130Scrn00_SearchLocationName */
    public static final String NPAL1130_SEARCH_LOCATION_NAME = "NPAL1130Scrn00_SearchLocationName";

    /** NPAL11130Scrn00_CMN_Download */
    public static final String NPAL11130_CMN_DOWNLOAD = "NPAL1130Scrn00_CMN_Download";

    /** NPAL1130Scrn00_CMN_ColClear */
    public static final String NPAL1130_CMN_COL_CLEAR = "NPAL1130Scrn00_CMN_ColClear";

    /** NPAL1130Scrn00_CMN_ColSave */
    public static final String NPAL1130_CMN_COL_SAVE = "NPAL1130Scrn00_CMN_ColSave";

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /** NPAL1130Scrn00_OnChange_DplyConfOrdOnly */
    public static final String NPAL1130_ONCHANGE_DPLY_CONF_ORD_ONLY = "NPAL1130Scrn00_OnChange_DplyConfOrdOnly";
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

    /** QC#21229 NPAL1130Scrn00_OpenWin_Attachments */
    public static final String NPAL1130_OPEN_WIN_ATTACHMENTS = "NPAL1130Scrn00_OpenWin_Attachments";
    // =================================================
    // CSV Download
    // =================================================
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NPAL1130_SupplyDemand(Detail)";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 500;

    /** CSV_HDR */
    public static final String[] CSV_HDR = new String[] {
          "Item Number"
        , "Location"
        , "ETA or ETD Date"
        , "Inbd Outbd Stock"
        , "Trx Type"
        , "Order Number"
        , "Order Line Number"
        , "Order Type"
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        , "Order Header Status"
        , "Allocation Status"
        , "Approval Status"
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        , "Order Qty"
        , "Available Qty"
        , "Supersede"
        // START 2023/05/08 G.Quan [QC#61439, MOD]
        //, "Request Date"
        , "Ord Crt Date"
        // END 2023/05/08 G.Quan [QC#61439, MOD]
        , "Need By Date"};

    /** NPZC101001_BASE_CONTENTS */
    public static final String[][] NPZC101001_BASE_CONTENTS = {

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
//    {"curInvtyQty", "curInvtyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
//    {"schdInbdQty", "schdInbdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
//    {"schdOtbdQty", "schdOtbdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
//    {"curInvtyAvalQty", "curInvtyAvalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
//    {"prchReqQty", "prchReqQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
//    {"xxMdseCd", "xxMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
//    {"xxInvtyLocCd", "xxInvtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
//    {"etaEtdDt", "etaEtdDt", null, null, TYPE_NENTSUKIHI, "8", null},
//    {"inbdOtbdNm", "inbdOtbdNm", null, null, TYPE_HANKAKUEISU, "30", null},
//    {"arTrxTpNm", "arTrxTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
//    {"origOrdNum", "origOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
//    {"ordLineNum", "ordLineNum", null, null, TYPE_HANKAKUEISU, "6", null},
//    {"ordTpNm", "ordTpNm", null, null, TYPE_HANKAKUEISU, "25", null},
//    {"ordDt", "ordDt", null, null, TYPE_NENTSUKIHI, "8", null},
//    {"rqstRcvDt", "rqstRcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
    
    {"curInvtyQty", "curInvtyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
    {"schdInbdQty", "schdInbdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
    {"schdOtbdQty", "schdOtbdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
    {"curInvtyAvalQty", "curInvtyAvalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
    {"prchReqQty", "prchReqQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
    {"xxMdseCd", "xxMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
    {"xxInvtyLocCd", "xxInvtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
    {"etaEtdDt", "etaEtdDt", null, null, TYPE_NENTSUKIHI, "8", null},
    {"inbdOtbdNm", "inbdOtbdNm", null, null, TYPE_HANKAKUEISU, "30", null},
    {"ordDt", "ordDt", null, null, TYPE_NENTSUKIHI, "8", null},
    {"arTrxTpNm", "arTrxTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
    {"origOrdNum", "origOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
    {"ordLineNum", "ordLineNum", null, null, TYPE_HANKAKUEISU, "6", null},
    {"ordTpDescTxt", "ordTpDescTxt_A0", "A0", null, TYPE_HANKAKUEISU, "50", null},
    {"rqstRcvDt", "rqstRcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
    {"xxDplyOrdLineNum", "xxDplyOrdLineNum", null, null, TYPE_HANKAKUEISU, "30", null},
    {"ordHdrDplyStsCd", "ordHdrDplyStsCd", null, null, TYPE_HANKAKUEISU, "3", null},
    {"ordHdrDplyStsDescTxt", "ordHdrDplyStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
    {"shpgStsCd", "shpgStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
    {"shpgStsNm", "shpgStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
    {"apvlStsCd", "apvlStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
    {"apvlStsNm", "apvlStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    };
    
    // QC#27636 add start
    /** The process [@] has been successfully completed. exists. */
    public static final String ZZZM9003I = "ZZZM9003I";
    // QC#27636 add end
    
 }
