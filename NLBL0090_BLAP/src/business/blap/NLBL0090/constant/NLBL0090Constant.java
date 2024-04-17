/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0090.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         Mori            Create          N/A
 * 10/28/2010   CSAI            D.Fukaya        Update          N/A
 * 2016/04/07   CITS            Y.Nomura        Update          for CSA
 * 02/02/2017   CITS            M.Naito         Update          QC#12673
 *</pre>
 */
public class NLBL0090Constant {

    // **************** Screen Item Name *****************
    /**
     * Search Result Table Name:A
     */
    public static final String TBL_NAME_A = "A";

    /**
     * Search Result Table Name:B
     */
    public static final String TBL_NAME_B = "B";

    // **************** Data Character *****************
    /**
     * static Value
     */
    public static final String ASTERISK = "*";

    /**
     * static Value
     */
    public static final String COLON = ":";

    /**
     * Replace Charactor at CARR_URL.CARR_TRK_URL. (Regexp)
     */
    public static final String REPLACE_CHAR = "\\$\\$";

    /** . */
    public static final int DEFAULT_ADD_DAYS = -30;

    // **************** Pulldown List *****************
    /**
     * DB column For create Pulldown List : POD Status Type
     */
    public static final String[] POD_STS_TP_DBCOLUMN = {"POD_STS_TP_FOR_SCR_CD", "POD_STS_TP_FOR_SCR_NM" };

    /**
     * DB column For create Pulldown List : POD Status
     */
    public static final String[] POD_STS_DBCOLUMN = {"POD_STS_CD", "POD_STS_NM" };

    // **************** Display Search Result Table *****************
    /** . */
    public static final int SEARCH_MAX_CNT = 30000;

    // **************** CSV File *****************
    /**
     * CSV File Name
     */
    public static final String CSV_FILE_NM = "NLBL0090_BOLTracking";

    /** csv file ext. */
    public static final String CSV_FILE_EXT = ".csv";

    /**
     * CSV File Max Row
     */
    public static final int CSV_MAX_ROWS = 65000;

    // CSV File Header item
    /** . */
    public static final String BOL_NUM = "B/L#";

    /** . */
    public static final String PRO_NUM = "Tracking/Pro#";

    /** . */
    public static final String LOC_NM = "Carrier";

    /** . */
    public static final String WH_CD = "WH";

    /** . */
    public static final String SELL_TO_CUST_CD = "Sell To";

    /** . */
    public static final String SHIP_TO_CUST_CD = "Ship To";

    /** . */
    public static final String SO_NUM = "SO#";

    /** . */
    public static final String POD_STS_DT = "Status Date";

    /** . */
    public static final String POD_STS_CD = "POD Status Cd";

    /** . */
    public static final String POD_STS_NM = "POD Status Nm";

    /** . */
    public static final String POD_STS_RSN_CD = "Status Reason Cd";

    /** . */
    public static final String POD_STS_RSN_NM = "Status Reason Nm";

    /** . */
    public static final String SO_SLP_NUM = "SO Line#";

    /** . */
    public static final String ITEM_NUMBER = "Item Number";

    /** . */
    public static final String ITEM_NM = "Item Nm";

    /** . */
    public static final String STK_STS_CD = "SS";

    /** . */
    public static final String ORD_QTY = "Quantity";

    /** . */
    public static final String TRX_HDR_NUM = "CPO#";

    /** . */
    public static final String SCE_ORD_TP_CD = "SO Type Cd";

    /** . */
    public static final String SCE_ORD_TP_NM = "SO Type Nm";

    /** . */
    public static final String DT_TP_SHIP = "1";

    /** . */
    public static final String DT_TP_POD = "2";

    /** . */
    public static final String[][] DT_TP_PULDW_LIST = {{DT_TP_SHIP, "Shipped Date" }, {DT_TP_POD, "POD Status Date" } };

    /** . */
    public static final String DB_PARAM_VALUE_FOR_HHMMSS_MIN = "000000";

    /** . */
    public static final String DB_PARAM_VALUE_FOR_HHMMSS_MAX = "235959";

    // =================================================
    // Message Code
    // =================================================
    /** . */
    public static final String NZZM0000E = "NZZM0000E";

    /** . */
    public static final String NZZM0001W = "NZZM0001W";

    /** . */
    public static final String NLBM1096E = "NLBM1096E";

    /** . */
    public static final String NLBM0012E = "NLBM0012E";

    /** . */
    public static final String NLBM0014E = "NLBM0014E";

    /** . */
    public static final String NZZM0007E = "NZZM0007E";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** . */
    public static final String DB_PARAM_SMSG = "sMsg";

    /** . */
    public static final String DB_PARAM_ROWNUM = "rownum";

    /** . */
    public static final String DB_PARAM_DATA_SECURITY_LIST = "DATA_SECURITY_LIST";

    /** . */
    public static final String DB_PARAM_POD_STS_TP_FOR_SCR_CD = "podStsTpForScrCd";

    /** . */
    public static final String DB_PARAM_OUTBOUND_CARRIER = "OUTBOUND_CARRIER";

    /** . */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /** . */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /** . */
    public static final String DB_PARAM_POD_STS_CD_LIST = "POD_STS_CD_LIST";

    /** . */
    public static final String DB_PARAM_SHIPPED_DATE_FLG = "SHIPPED_DATE_FLG";

    /** . */
    public static final String DB_PARAM_POD_STS_DATE_FLG = "POD_STS_DATE_FLG";

    /** . */
    public static final String DB_PARAM_POD_INNER_JOIN = "POD_INNER_JOIN";

    /** . */
    public static final String DB_PARAM_OTBD = "OTBD";

    /** . */
    public static final String DB_PARAM_TIME_MIN = "hhmmssMin";

    /** . */
    public static final String DB_PARAM_TIME_MAX = "hhmmssMax";

    // =================================================
    // DB Columns
    // =================================================
    /** . */
    public static final String DB_COLUMN_BOL_NUM = "BOL_NUM";

    /** . */
    public static final String DB_COLUMN_PRO_NUM = "PRO_NUM";

    /** . */
    public static final String DB_COLUMN_LOC_NM = "LOC_NM";

    /** . */
    public static final String DB_COLUMN_WH_CD = "WH_CD";

    /** . */
    public static final String DB_COLUMN_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** . */
    public static final String DB_COLUMN_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** . */
    public static final String DB_COLUMN_SO_NUM = "SO_NUM";

    /** . */
    public static final String DB_COLUMN_XX_DT_TXT = "XX_DT_TXT";

    /** . */
    public static final String DB_COLUMN_POD_STS_CD = "POD_STS_CD";

    /** . */
    public static final String DB_COLUMN_POD_STS_NM = "POD_STS_NM";

    /** . */
    public static final String DB_COLUMN_POD_STS_RSN_CD = "POD_STS_RSN_CD";

    /** . */
    public static final String DB_COLUMN_POD_STS_RSN_NM = "POD_STS_RSN_NM";

    /** . */
    public static final String DB_COLUMN_SO_SLP_NUM = "SO_SLP_NUM";

    /** . */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /** . */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String DB_COLUMN_STK_STS_CD = "STK_STS_CD";

    /** . */
    public static final String DB_COLUMN_ORD_QTY = "ORD_QTY";

    /** . */
    /** . */
    public static final String DB_COLUMN_TRX_HDR_NUM = "TRX_HDR_NUM";

    /** . */
    public static final String DB_COLUMN_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** . */
    public static final String DB_COLUMN_SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /** . */
    public static final String DB_COLUMN_FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** . */
    public static final String DB_COLUMN_POD_STS_DT = "POD_STS_DT";
}
