/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/26   Fujitsu         T.Murai         Update          S21_NA#13067
 * 2017/03/01   Fujitsu         Y.Kanefusa      Update          S21_NA#17637
 * 2017/11/01   Fujitsu         H.Sugawara      Update          QC#18787(Sol#232)
 * 2017/12/20   Fujitsu         Mz.Takahashi    Update          S21_NA#20164(L3 Sol#356)/
 * 2018/02/09   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/03/19   Fujitsu         A.Kosai         Update          S21_NA#24810
 * 2018/04/16   Fujitsu         N.Sugiura       Update          S21_NA#22965
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/08/24   Fujitsu         T.Noguchi       Update          S21_NA#27202
 * 2019/07/09   Fujitsu         Mz.Takahashi    Update          QC#51252
 * 2019/12/20   Fujitsu         A.Kazuki        Update          QC#53055
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2023/11/29   CITS            L.Duy           Update          CSA-QC#62421
 * 2024/04/03   CITS            A.Shimada       Update          CSA-QC#63691
 * </pre>
 */
public class NWAL1770Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1770";

    /** Header ID (Items) */
    public static final String ITEMS_HDR_ID = "BHEAD";

    /** Header ID (Additional Data) */
    public static final String ADDL_HDR_ID = "CHEAD";

    /** Period */
    public static final String PERIOD = ".";

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** Hyphen */
    public static final String HYPHEN = "-";

    /** Under Line */
    public static final String UNDER_LINE = "_";

    /** New Line */
    public static final String NEW_LINE = "\r\n";

    //START 2024/04/03 [CSA-QC#63691,ADD] 
    /** Add items tab Initial blank lines*/
    public static final String NUM_CONST_KEY_CORE_INIT_BLANK_LINES = "INIT_BLANK_LINES";
    /**Set Default Initial blank lines*/
    public static final BigDecimal DEFAULT_NUM_INIT_BLANK_LINES = BigDecimal.valueOf(20);
    
    /*Set the target*/
    public static final String TARGET = "TARGET";
    //END 2024/04/03 [CSA-QC#63691,ADD]

    /** Parent Sub Line Number */
    public static final String PRNT_SUB_LINE_NUM = "000";

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 14 */
    public static final int IDX_14 = 14;

    /** Index Number 100 */
    public static final int IDX_100 = 100;

    /** Brunch Number 00 */
    public static final String BRUNCH_00 = "00";

    /** Brunch Number 01 */
    public static final String BRUNCH_03 = "03";

    // Add Start 2016/08/26 S21_NA#13067
    /** Default Sub WH Code (NEW) */
    public static final String SUB_WH_CD_NEW = "NEW";
    // Add End 2016/08/26 S21_NA#13067

    /** 100 Percentage */
    public static final BigDecimal PCT_100 = new BigDecimal(100);

    /** Billing Attribute Value Text */
    public static final String BLLG_ATTRB_VAL_TXT = "BLLG_ATTRB_VAL_TXT";

    /** Billing Attribute Name */
    public static final String BLLG_ATTRB_NM = "BLLG_ATTRB_NM";

    /** Order Category Context Type Code (SPLY_QUOTE) */
    public static final String SUPPLY_QUOTE_ELIGIBLE_ORDER_CATEGROY = "SPLY_QUOTE";

    /** Order Category Context Type Code (EQUIPMENT_ORDER) */
    public static final String EQUIPMENT_ORDER_VALUE_SET = "EQUIPMENT_ORDER";

    /** Order Category Context Type Code (SUPPLIES_ORDER) */
    public static final String SUPPLIES_ORDER_VALUE_SET = "SUPPLIES_ORDER";

    // 2018/03/19 S21_NA#24810 Del Start
//    /** Varchar Const Key (QUOTE_CANCELLED) */
//    public static final String KEY_QUOTE_CANCELLED = "QUOTE_CANCELLED";
    // 2018/03/19 S21_NA#24810 Del End

    /** Varchar Const Key (PKG_UOM_FOR_PRC) */
    public static final String KEY_PKG_UOM_FOR_PRC = "PKG_UOM_FOR_PRC";

    /** Varchar Const Key (OUTPUT_CONTACT_CD) */
    public static final String KEY_OUTPUT_CONTACT_CD = "OUTPUT_CONTACT_CD";

    // Add Start 2016/08/26 S21_NA#13067
    /** VARCHAR_CONST define: DROP_SHIP_RTL_WH_CD */
    public static final String DROP_SHIP_RTL_WH_CD = "DROP_SHIP_RTL_WH_CD";
    // Add End 2016/08/26 S21_NA#13067

    /** Salesrep Credit Percent Num Const Name */
    public static final String DEF_SLS_CR_PCT_WRITER = "DEF_SLS_CR_PCT_WRITER";     // QC#17637 2017/03/01 Add

    // Add Start 2017/12/20 QC#20164(L3 Sol#356)
    /** Value Set Key */
    public static final String CTAC_ROLE_MND_SET = "CTAC_ROLE_MND_SET";
    // Add End 2017/12/20 QC#20164(L3 Sol#356)

    //2019/07/23  QC#51252 Add Start
    /** Mail Group ID Default */
    public static final String ML_GRP_ID_DEF = "ORD_CONF";

    /** Mail Group ID LFS */
    public static final String ML_GRP_ID_LFS = "ORD_CONF_LFS_SPLY";

    /** Mail Group ID PPS */
    public static final String ML_GRP_ID_PPS = "ORD_CONF_PPS_PARTS";
    //2019/07/23  QC#51252 Add End

    //2019/07/23  QC#51252 Del Start
    /** Mail Group ID */
    // spublic static final String MAIL_GROUP_ID = "NWAL1770";
    //2019/07/23  QC#51252 Del End

    /** Mail Template ID (Quote Confirmation) */
    public static final String NWAL1770M001 = "NWAL1770M001";

    /** Mail Template ID (Order Confirmation) */
    public static final String NWAL1770M002 = "NWAL1770M002";

    /** Mail Template ID (Supply Tracking) */
    public static final String NWAL1770M003 = "NWAL1770M003";

    /** Mail Key From */
    public static final String MAIL_KEY_FROM = "From";

    /** Report ID (Order Conf LFS) */
    public static final String NWAF0060 = "NWAF0060";

    /** Report ID (Quote LFS) */
    public static final String NWAF0065 = "NWAF0065";

    /** Report ID (Order Conf Parts & PPS) */
    public static final String NWAF0070 = "NWAF0070";

    /** Report ID (Quote Parts & PPS) */
    public static final String NWAF0075 = "NWAF0075";

    /** Report ID (Supply Tracking) */
    public static final String NWAF1040 = "NWAF1040";

    /** Report Name (Order Confirmation) */
    public static final String REPORT_NM_ORD_CONF = "Order Confirmation(";

    /** Report Name (Quote) */
    public static final String REPORT_NM_QUOTE = "Quote(";

    /** Report Name (Supply Tracking) */
    public static final String REPORT_NM_SPLY_TRK = "Supply Tracking(";

    /** Parentheses (Close) */
    public static final String PARENTHESES_CLOSE = ")";

    /** Extension (.pdf) */
    public static final String PDF = ".pdf";

    /** Date Format (yyyyMMddHHmmssSSS) */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";
    
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";

    /** MODE_REGIST = "11" */
    public static final String MODE_REGIST = "11";

    // S21_NA#22965 Add Start
    /** PROCESS_LVL_HEADER = "01" */
    public static final String PROCESS_LVL_HEADER = "01";

    /** PROCESS_LVL_LINE = "02" */
    public static final String PROCESS_LVL_LINE = "02";

    /** TOT_WT_SCALE = 0 */
    public static final int TOT_WT_SCALE = 6;
    // S21_NA#22965 Add End

    // Add Start 2017/11/01 QC#18787(Sol#232)
    /** Key Value for searching ORD_CATG_BIZ_CTX */
    public static final String ORD_CATG_CTX_TP_CD_DAYS_VALID = "DEFAULT_DAYS_VALID";
    // Add End 2017/11/01 QC#18787(Sol#232)

    // 2018/02/09 S21_NA#20297(Sol#379) Add Start
    /** Shipping Comment Limit Size */
    public static final int SHPG_CMT_TXT_LIMIT_SIZE = 260;
    // 2018/02/09 S21_NA#20297(Sol#379) Add End

    /** Bill Event List */
    public static final List<String> BILL_EVENT_LIST;
    static {
        List<String> billEventList = new ArrayList<String>();
        billEventList.add("OpenWin_BillTo");
        billEventList.add("OnBlur_DeriveFromBillToName");
        billEventList.add("OnBlur_DeriveFromBillToAccount");
        billEventList.add("OnBlur_DeriveFromBillToLocation");
        BILL_EVENT_LIST = Collections.unmodifiableList(billEventList);
    }

    /** Ship Event List */
    public static final List<String> SHIP_EVENT_LIST;
    static {
        List<String> shipEventList = new ArrayList<String>();
        shipEventList.add("OpenWin_ShipTo");
        shipEventList.add("OnBlur_DeriveFromShipToName");
        shipEventList.add("OnBlur_DeriveFromShipToAccount");
        shipEventList.add("OnBlur_DeriveFromShipToLocation");
        SHIP_EVENT_LIST = Collections.unmodifiableList(shipEventList);
    }

    /** Sold Event List */
    public static final List<String> SOLD_EVENT_LIST;
    static {
        List<String> soldEventList = new ArrayList<String>();
        soldEventList.add("OpenWin_SoldTo");
        soldEventList.add("OnBlur_DeriveFromSoldToName");
        soldEventList.add("OnBlur_DeriveFromSoldToAccount");
        soldEventList.add("OnBlur_DeriveFromSoldToLocation");
        SOLD_EVENT_LIST = Collections.unmodifiableList(soldEventList);
    }

    // 2018/05/11 QC#22139 Add Start
    /** Print on Shipping check box ON */
    public static final String SHPG_CMNT_PRINT_CHK_BOX_ON = "1";
    // 2018/05/11 QC#22139 Add End

    // 2018/08/24 S21_NA#27202 Add Start
    /** DS_DROP_SHIP_WH_CD */
    public static final String DS_DROP_SHIP_WH_CD = "DS_DROP_SHIP_WH_CD";
    // 2018/08/24 S21_NA#27202 Add End
    
    // Add Start 2019/12/20 QC#53055
    public static final String TRTY_GRP_TP_IS = "IS";
    // Add End   2019/12/20 QC#53055

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /** Default value "ANY" */
    public static final String DEF_VAL_ANY = "*";
    /** Number of Order histories */
    public static final int NUM_OF_ORD_HIST = 10;
    /** Number of Line Histories */
    public static final int NUM_OF_LINE_HIST = 100;
    /** Attachment data type code : File */
    public static final String ATT_DATA_TP_CD_FILE = "FL";
    /** Attachment data type code: Therefore */
    public static final String ATT_DATA_TP_CD_THEREFORE = "TF";
    /** Order attachment business id */
    public static final String ATT_ORD_BIZ_ID = "NWAL1500";
    /** Order attachment business app name */
    public static final String ATT_ORD_BIZ_APP_NM = "Order Entry";
    /** Order attachment data key text */
    public static final String ATT_ORD_DATA_KEY_TXT = "Order Number";
    /** Tab: Items */
    public static final String TAB_ITEMS = "Items";
    /** ATT_DOC_TP_CD: Order for Order Entry */
    public static final String ATT_DOC_TP_CD_ORDER_FOR_ORDER = "10";
    /** ATT_DOC_TP_CD: Quote */
    public static final String ATT_DOC_TP_CD_QUOTE = "20";
    /** ATT_DOC_TP_CD: OM Comment */
    public static final String ATT_DOC_TP_CD_OM_CMNT = "30";
    /** ATT_DOC_TP_CD: OM Comment for Order Entry */
    public static final String ATT_DOC_TP_CD_OM_CMNT_FOR_ORDER = "40";
    /** Varchar Const Key (CR_APVL_WF_ID) */
    public static final String KEY_CR_APVL_WF_ID = "CR_APVL_WF_ID";
    /** Workflow Action Name: APPROVE */
    public static final String WF_ACTION_NM_APPROVE = "APPROVE";
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
	
	// 2023/11/29 CSA-QC#62421 Add Start
    /** Tab anchor Additional Data */
    public static final String TAB_ADDITIONAL = "Additional";
    // 2023/11/29 CSA-QC#62421 Add End
    
    //START 2024/04/03 [CSA-QC#63691,ADD]
    /**SPLY_QUOTE_STS_CD : Submit*/
    public static final String SPLY_QUOTE_STS_CD_SUBMITTED = "10";
    /**SPLY_QUOTE_STS_CD : Cancel*/
    public static final String SPLY_QUOTE_STS_CD_CANCELLED = "99";
    //END 2024/04/03 [CSA-QC#63691,ADD]
}
