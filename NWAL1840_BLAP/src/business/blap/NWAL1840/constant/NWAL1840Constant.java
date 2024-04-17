/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         Y.Murai         Create          N/A
 * 2016/07/15   Fujitsu         H.Ikeda         Update          QC#11578
 * 2017/03/01   Fujitsu         Y.Kanefusa      Update          S21_NA#17637
 * 2017/12/21   Fujitsu         K.Ishizuka      Update          S21_NA#20164(Sol#356)
 * 2018/02/13   Fujitsu         K.Ishizuka      Update          S21_NA#20297(Sol#379)
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/10/05   Fujitsu         Hd.Sugawara     Update          S21_NA#28510
 * 2019/12/20   Fujitsu         A.Kazuki        Update          QC#53055
 * 2023/03/14   CITS            R.Azucena       Update          QC#61286
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * </pre>
 */
public class NWAL1840Constant {
    /** Business ID */
    public static final String BIZ_ID = "NWAL1840";

    /** Header ID (Items) */
    public static final String ITEMS_HDR_ID = "BHEAD";

    /** Header ID (Additional Data) */
    public static final String ADDL_HDR_ID = "CHEAD";

    /** Schedule Agreement Sub Line Number(001) */
    public static final String SCHD_SUB_LINE_NUM_001 = "001";

    /** Period */
    public static final String PERIOD = ".";

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    // Del Start 2018/10/05 QC#28510
    ///** Index Number 8 */
    //public static final int IDX_8 = 8;
    // Del End 2018/10/05 QC#28510

    /** Index Number 100 */
    public static final int IDX_100 = 100;

    /** 100 Percentage */
    public static final BigDecimal PCT_100 = new BigDecimal(100);

    /** Varchar Const Key (ORD_LINE_STS_TO_BE_CANCELLED) */
    public static final String KEY_ORD_LINE_STS_TO_BE_CANCELLED = "ORD_LINE_STS_TO_BE_CANCELLED";

    /** Salesrep Credit Percent Num Const Name */
    public static final String DEF_SLS_CR_PCT_WRITER = "DEF_SLS_CR_PCT_WRITER";     // QC#17637 2017/03/01 Add

    /** Billing Attribute Value Text */
    public static final String BLLG_ATTRB_VAL_TXT = "BLLG_ATTRB_VAL_TXT";

    /** Billing Attribute Name */
    public static final String BLLG_ATTRB_NM = "BLLG_ATTRB_NM";

    /** Order Category Context Type Code (EQUIPMENT_ORDER) */
    public static final String EQUIPMENT_ORDER_VALUE_SET = "EQUIPMENT_ORDER";

    /** Order Category Context Type Code (SUPPLIES_ORDER) */
    public static final String SUPPLIES_ORDER_VALUE_SET = "SUPPLIES_ORDER";

    /** MapKey - Allowed Qty */
    public static final String ALLOWED_QTY = "Allowed Qty";

    /** MapKey - Ordered Qty */
    public static final String ORDERED_QTY = "Ordered Qty";

    /** MapKey - Delivered Qty */
    public static final String DELIVERED_QTY = "Delivered Qty";
    /** Tab anchor Customer / Contact */
    public static final String TAB_CUSTOMER = "Customer";

    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";

    /** Tab anchor Lines */
    public static final String TAB_LINES = "Lines";

    /** Tab anchor Comments */
    public static final String TAB_COMMENTS = "Comments";

    /** Tab anchor Additional Data */
    public static final String TAB_ADDITIONAL = "Additional";

    /** New Line */
    public static final String NEW_LINE = "\r\n";
    
    /** Variable Character Constant for Default Contact */ // 2017/12/21 S21_NA#20164(Sol#356) ADD
    public static final String CONST_NWAL1840_DEF_CTAC_FOR_PULLDOWN = "NWAL1840_DEF_CTAC_FOR_PULLDOWN";
    
    /** Shipping Comment Limit Size*/ // 2018/02/13 S21_NA#20297(Sol#379) ADD
    public static final int SHPG_CMT_TXT_LIMIT_SIZE = 260;

    // QC#11578 2016/07/15 Add Start
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
    // QC#11578 2016/07/15 Add End
    // QC#22965 2018/04/11 Add Start
    public static final int TOT_WT_SCALE = 6;
    // QC#22965 2018/04/11 Add End

    // Add Start 2019/12/20 QC#53055
    /** Check Order type is IS. */
    public static final String TRTY_GRP_TP_IS = "IS";
    // Add End   2019/12/20 QC#53055

    // START 2023/03/14 R.Azucena [QC#61286 ADD]
    /** Order Submitted Date List Max Length */
    public static final int ORDR_SUBMT_DT_LIST_MAX_LEN = 20;
    // END 2023/03/14 R.Azucena [QC#61286 ADD]

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /** Tab anchor SA History */
    public static final String TAB_SA_HISTORY = "SaHistory";

    /** Number of SA Histories */
    public static final int NUM_OF_SA_HISTORY = 100;

    /** Varchar Const Key (NWAL1840_DUP_VALID) */
    public static final String KEY_NWAL1840_DUP_VALID = "NWAL1840_DUP_VALID";

    /** NWAL1840_DUP_VALID value : Error */
    public static final String DUP_VALID_ERROR = "ERROR";

    /** Date format pattern: INIT/SEARCH start timestamp */
    public static final String DATE_PATTERN_START_TS = "yyyyMMddHHmmssSSS";
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
}
