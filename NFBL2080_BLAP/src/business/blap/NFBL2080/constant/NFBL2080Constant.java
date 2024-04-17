/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2080.constant;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2016/07/28   Fujitsu         S.Yoshida       Update          QC#12555,12575
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12760
 * 2016/11/17   Hitachi         T.Tsuchida      Update          QC#16106
 * 2019/03/25   Hitachi         Y.Takeno        Update          QC#30850
 * 2019/05/20   Hitachi         H.Umeda         Update          QC#50204,50205
 * 2020/05/26   Fujitsu         H.Mizukami      Update          QC#56007
 * 2023/10/04   CITS            M.Kobayashi     Update          QC#61880
 *</pre>
 */
public interface NFBL2080Constant {

    /**
     * DB Item Name : Global Company Code
     */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    public static final String GLBL_CMPY_CD_J = "glblCmpyCd";
    /**
     * DB Item Name : Vendor Code
     */
    public static final String VND_CD  = "VND_CD";
    /**
     * DB Item Name : Location Name
     */
    public static final String LOC_NM = "LOC_NM";
    /**
     * DB Item Name : Vendor Invoice Number
     */
    public static final String VND_INV_NUM = "VND_INV_NUM";
    public static final String VND_INV_NUM_J = "vndInvNum";
    /**
     * DB Item Name : Vendor Invoice Number
     */
    public static final String VND_INV_BOL_LINE_NUM = "VND_INV_BOL_LINE_NUM";
    /**
     * DB Item Name : Vendor Invoice Number
     */
    public static final String VND_INV_LINE_NUM = "VND_INV_LINE_NUM"; 
    /**
     * DB Item Name : Vendor Invoice Number
     */
    public static final String VND_INV_LINE_SUB_NUM = "VND_INV_LINE_SUB_NUM";
    /**
     * DB Item Name : Vendor Invoice Number
     */
    public static final String VND_INV_LINE_SUB_TRX_NUM = "VND_INV_LINE_SUB_TRX_NUM";
    /**
     * DB Item Name : Vendor Invoice Process Status Code
     */
    public static final String VND_INV_PROC_STS_CD = "VND_INV_PROC_STS_CD";
    /**
     * DB Item Name : CSMP Process Status Code
     */
    public static final String INV_AR_PROC_STS_CD = "INV_AR_PROC_STS_CD";
    /**
     * DB Item Name : CSMP Process Status Description Text
     */
    public static final String INV_AR_PROC_STS_DESC_TXT = "INV_AR_PROC_STS_DESC_TXT";
    /**
     * DB Item Name : purchase order number
     */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";
    public static final String PO_ORD_NUM_J = "poOrdNum";
    /**
     * DB Item Name : EDI purchase order number
     */
    public static final String EDI_PO_ORD_NUM = "EDI_PO_ORD_NUM";
    /**
     * DB Item Name : ASN shipping order number
     */
    public static final String ASN_SO_NUM = "ASN_SO_NUM";
    /**
     * DB Item Name : shipping order number
     */
    public static final String SO_NUM = "SO_NUM";
    /**
     * DB Item Name : Invoice Date
     */
    public static final String INV_DT = "INV_DT";
    /**
     * DB Item Name : Invoice Total Deal Net Amount
     */
    public static final String INV_TOT_DEAL_NET_AMT = "INV_TOT_DEAL_NET_AMT";
    /**
     * DB Item Name : Batch Error Message Text
     */
    public static final String BAT_ERR_MSG_TXT = "BAT_ERR_MSG_TXT";
    /**
     * DB Item Name : EDI purchase order detail line number
     */
    public static final String EDI_PO_ORD_DTL_LINE_NUM = "EDI_PO_ORD_DTL_LINE_NUM";
    /**
     * DB Item Name : Merchandise Code
     */
    public static final String MDSE_CD = "MDSE_CD";
    /**
     * DB Item Name : Merchandise Name
     */
    public static final String MDSE_NM = "MDSE_NM";
    /**
     * DB Item Name : shipping Qty
     */
    public static final String SHIP_QTY = "SHIP_QTY";
    /**
     * DB Item Name : vendor invoice line deal net amount
     */
    public static final String VND_INV_LINE_DEAL_NET_AMT = "VND_INV_LINE_DEAL_NET_AMT";
    /**
     * DB Item Name : Customer Issue PO Number
     */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
//Add Start QC#12555
    /**
     * DB Item Name : Create Date
     */
    public static final String CRAT_DT = "CRAT_DT";
//Add End   QC#12555
// START 2019/05/17 H.Umeda [QC#50205,ADD]
    /**
     * DB Item Name : Invoice Type Code
     */
    public static final String INV_TP_CD = "INV_TP_CD";
    /**
     * DB Item Name : Internal Interface ID
     */
    public static final String ITRL_INTFC_ID = "ITRL_INTFC_ID";
    /**
     * DB Item Name : CR DR Reason Code
     */
    public static final String CR_DR_RSN_CD = "CR_DR_RSN_CD";
// END   2019/05/17 H.Umeda [QC#50205,ADD]

    /**
     * DB Item Name : Vnd Inv Err Msg ID
     */
    public static final String VND_INV_ERR_MSG_ID = "VND_INV_ERR_MSG_ID";

// START 2020/05/26 [QC#56007,ADD]
    /**
     * DB Item Name : purchase order detail line number
     */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
// END   2020/05/26 [QC#56007,ADD]

    /** Boolean True */
    public static final boolean TRUE = true;
    /** Boolean False */
    public static final boolean FALSE = false;
    /** Return Error Code */
    public static final int RTRN_ERR_CD = -99;
    /** MAX ROWS */
    public static final int MAX_ROWS = 1000;

//Add Start QC#12760
    /** INV_AR_PROC_STS_CD: Blank */
    public static final String INV_AR_PROC_STS_CD_BLANK = "9";

    /** INV_AR_PROC_STS_DESC_TXT: Blank */
    public static final String INV_AR_PROC_STS_DESC_TXT_BLANK = "(blank)";
//Add End QC#12760

    // START 2016/11/17 T.Tsuchida [QC#16106,ADD]
    /** FREIGHT */
    public static final String FREIGHT = "FREIGHT";
    /** TAX */
    public static final String TAX = "TAX";
    // END 2016/11/17 T.Tsuchida [QC#16106,ADD]

    // START 2023/10/04 M.Kobayashi [QC#61880,ADD]
    /** MISC */
    public static final String MISC = "MISC";
    // END 2023/10/04 M.Kobayashi [QC#61880,ADD]

    // START 2019/03/25 [QC#30850, ADD]
    /** VND_INV_LINE_SUB_NUM : 000 */
    public static final String VND_INV_LINE_SUB_NUM_000 = "000";

    /** Message Kind */
    static final String MESSAGE_KIND_E = "E";
    // END  2019/03/25 [QC#30850, ADD]
    
    // START 2019/05/20 H.Umeda [QC#50204,50205,ADD]
    /** Vender Invoice Process Status Code [Save] **/
    public static final String PROC_STS_TP_CD_SAVE = "S";
    
    /** Vender Invoice Process Status Code [Error] **/
    public static final String PROC_STS_TP_CD_ERR = "E";
    
    /** Invoice Type Code [Creadit Meno] **/
    public static final String TP_CD_CR_MEMO = "2";
    
    /** ITRL Interface ID [AWCA0010(CUSA WS)] **/
    public static final String INTF_ID_CUSA_WS = "AWCA0010";
    
    /** ITRL Interface ID [USDA0300(CUSA Parts)] **/
    public static final String INTF_ID_CUSA_PARTS = "USDA0300";
    
    public static final String INV_FLG_ON = "1";
    
    // END   2019/05/20 H.Umeda [QC#50204,50205,ADD]

    // START 2018/10/12 J.Kim [QC#28723,ADD]
    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_NAME = "AP Invoice IF Correction";

    /** CSV extension */
    public static final String CSV = ".csv";

    /** Download size */
    public static final int CSV_LIMIT_COUNT = 10000;

    /** New Line */
    public static final String NEW_LINE = "\r\n";

    /** CSV Header */
    public static final String[] CSV_HEADER = {
        "Supplier Site Name",
        "Supplier INV#",
        "Proc Status",
        "CSMP Proc Status",
        "PO#",
        "EDI PO#",
        "EDI Rcv Date",
        "Amount",
        "Message"
    };
    // END 2018/10/12 J.Kim [QC#28723,ADD]

    /**
     * vendor invoice process status code
     * @author q06799
     */
    public static enum VND_INV_PROC_STS_CD_LIST {
        /** Save */
        S,
        /** Cancel */
        C
    }
    /** FLAG */
    public enum FLG {
        /** Yes */
        Y,
        /** No */
        N
    }
    /**
     * Massage ID MSG_ID
     * @author
     *
     */
    public static enum MSG_ID {
        NZZM0000E,
        NZZM0001W,
        NFBM0225E,
        NFBM1242E,
        NFBM1173E,
        NFBM1281W,
        NFBM1297E,
        NFBM1285E,
        NFBM1287E,
        NFBM1303W,
        NFBM1304E,
        ZZZM9013E,
        NFBM0005I,
        NFBM0023E,
        ZZM8002I,
        ZZM8100I
    }

    /**
     * Screen ID for 02
     * @author
     *
     */
    public static enum SCRN_MSG_02 {
        NFBL2080_INIT,
        NFBL2080Scrn00_Click_Search,
        NFBL2080Scrn00_Click_Reprocess,
        NFBL2080Scrn00_Click_Cancel,
        NFBL2080Scrn00_Check_All,
        NFBL2080Scrn00_Un_Check_All,
        NFBL2080Scrn00_Click_vndInvNum,
        NFBL2080Scrn00_CMN_Return,
        NFBL2080Scrn00_CMN_Submit,
        NFBL2080Scrn00_OpenWin_Mdse,
        NFBL2080Scrn00_NMAL6800,
        NFBL2080Scrn00_PageJump,
        NFBL2080Scrn00_PageNext,
        NFBL2080Scrn00_PagePrev,
        NFBL2080Scrn00_Header,
        NFBL2080Scrn00_Detail,
        NFBL2080Scrn00_CMN_Reset
    }

    /**
     * Screen ID for 06
     * @author
     *
     */
    public static enum SCRN_MSG_06 {
        NFBL2080Scrn00_Click_Reprocess,
        NFBL2080Scrn00_Click_Cancel,
        NFBL2080Scrn00_CMN_Submit
    }
}