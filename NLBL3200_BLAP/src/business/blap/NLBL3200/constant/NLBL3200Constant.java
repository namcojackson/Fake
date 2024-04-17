/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3200.constant;

import java.lang.reflect.Field;
import java.util.Comparator;

import parts.common.EZDTStringItem;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 2016/03/10   CITS      Takeshi Tokutomi      Update          QC#5242
 * 07/08/2016   CSAI            Y.Imazu         Update          QC#10232
 * 12/07/2016   CITS            Y.Fujii         Update          R360
 * 02/19/2018   CITS            T.Tokutomi      Update          QC#18367
 * 03/06/2018   CITS            T.Tokutomi      Update          QC#21913
 * 07/02/2018   CITS            T.Tokutomi      Update          QC#23726
 * 07/24/2019   Fujitsu         T.Ogura         Update          QC#51444
 *</pre>
 */
public class NLBL3200Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NLBL3200";

    /**
     * DS_COND_CONST_GRP_ID : NLZC2110_SHIP_CTRL
     */
    public static final String DCC_GRP_ID_NLZC2110_SHIP_CTRL = "NLZC2110_SHIP_CTRL";

    /**
     * VAR_CHAR_CONST_NM : CPO_DS_ORD_TP_FOR_WH_TRNSF
     */
    public static final String VAR_CONST_CPO_DS_ORD_TP_FOR_WH_TRNSF = "CPO_DS_ORD_TP_FOR_WH_TRNSF";

    /**
     * VAR_CHAR_CONST_NM : US_DOMESTIC_ST_CD
     */
    public static final String VAR_CONST_US_DOMESTIC_ST_CD = "US_DOMESTIC_ST_CD";    // 2019/07/24 T.Ogura [QC#51444,ADD]

    /**
     * TAB_SO_LIST
     */
    public static final String TAB_SO_LIST = "SoList";

    /**
     * TAB_PICK_LIST
     */
    public static final String TAB_PICK_LIST = "PickList";

    /** REPORT_ID. */
    public static final String REPORT_ID = "NLBF0010";

    /** REPORT_TITLE. */
    public static final String REPORT_TITLE = "S/O Report";

    /** REPORT_TITLE. */
    public static final String REPORT_EXPORT_CA_TITLE = "Export Document for CANADA";

    /** REPORT_TITLE. */
    public static final String REPORT_EXPORT_NON_CA_TITLE = "Export Document for Non-CANADA";

    /** GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** INTL_LANG_VAL_COL_NM. */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** SO_NUM. */
    public static final String SO_NUM = "SO_NUM";

    /** SHPG_ORD_RPT_PRINT_RQST_SQ. */
    public static final String SHPG_ORD_RPT_PRINT_RQST_SQ = "SHPG_ORD_RPT_PRINT_RQST_SQ";

    /** REPORT_TITLE_SEPARATOR. */
    public static final String REPORT_TITLE_SEPARATOR = " ";

    /** REPORT_ID_EXPORT_NON_CA. */
    public static final String REPORT_ID_EXPORT_NON_CA = "NLBF0020";

    /** REPORT_ID_EXPORT_CA. */
    public static final String REPORT_ID_EXPORT_CA = "NLBF0030";

    // QC#18367 Add.
    /** EXPT_DOC_PRINT_RQST_SQ. */
    public static final String EXPT_DOC_PRINT_RQST_SQ = "EXPT_DOC_PRINT_RQST_SQ";

    // QC#18367 Add.
    /** EXPT_DOC_CA_PRINT_RQST_SQ. */
    public static final String EXPT_DOC_CA_PRINT_RQST_SQ = "EXPT_DOC_CA_PRINT_RQST_SQ";

    /**
     * MAX_RECORD
     */
    public static final int MAX_RECORD = 1000;

    /**
     * MESSAGE_CODE NORMAL = 0
     */
    public static final int MESSAGE_CODE_NORMAL = 0;

    /**
     * MESSAGE_CODE ERROR = 1
     */
    public static final int MESSAGE_CODE_ERROR = 1;

    /**
     * MESSAGE_CODE WARNING = 2
     */
    public static final int MESSAGE_CODE_WARNING = 2;

    /**
     * FORMAT_TIMESTAMP
     */
    public static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmss";

    /** timestamp format (time part) */
    public static final String FORMAT_TIMESTAMP_TIME = "HHmmss";

    /**
     * ROLE_INQUIRY
     */
    public static final String ROLE_INQUIRY = "NLBL3200T010";

    /**
     * ROLE_UPDATE
     */
    public static final String ROLE_UPDATE = "NLBL3200T020";

    /**
     * ROLE_ALL_WH_PERMISSION
     */
    public static final String ROLE_ALL_WH_PERMISSION = "NLBL3200T030";

    /**
     * MDSE_DIGIT_8
     */
    public static final int MDSE_DIGIT_8 = 8;

    /** Value : TIME_AM */
    public static final String TIME_AM = "AM";

    /** Value : TIME_PM */
    public static final String TIME_PM = "PM";

    /**
     * Hour minute string length : HHMM
     */
    public static final int HOUR_MINUTE_STR_LENGTH = 4;

    /**
     * half day hours
     */
    public static final int HALF_DAY_HOURS = 12;

    /**
     * One day hours
     */
    public static final int ONE_DAY_HOURS = 24;

    /**
     * One hour munutes
     */
    public static final int ONE_HOUR_MINUTES = 60;

    /**
     * TBL_DS_SO_LINE_STS
     */
    public static final String TBL_DS_SO_LINE_STS = "DS_SO_LINE_STS";

    /**
     * TBL_SHPG_SVC_LVL
     */
    public static final String TBL_SHPG_SVC_LVL = "SHPG_SVC_LVL";

    // =================================================
    // Message Code
    // =================================================

    /** Details require more than 1 row. Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /**
     * Not open status line(s) are included in selected Shipping
     * Order.
     */
    public static final String NLBM1307E = "NLBM1307E";

    /**
     * Selected Shipping Order is not a target for Cancel in this
     * screen because the ship from location is @.
     */
    public static final String NLBM1351E = "NLBM1351E";

    /**
     * The Information for checking the cancel availability in this
     * screen cannot be found. Please contact system administrators.
     */
    public static final String NLBM1352E = "NLBM1352E";

    /**
     * NLZM2273E : You can not [@] this record Because of [@] already
     * exists.
     */
    public static final String NLZM2273E = "NLZM2273E";

    /**
     * NLZM2278E: The entered [@] does not exist in master.
     */
    public static final String NLZM2278E = "NLZM2278E";

    /**
     * NLZM2279E : The combination of [@] and [@] does not exist in
     * master.
     */
    public static final String NLZM2279E = "NLZM2279E";

    /**
     * NLZM2313E : You don't have the permission for the specified
     * Warehouse.
     */
    public static final String NLZM2313E = "NLZM2313E";

    /**
     * NZZM0000E : No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * NZZM0001W : There are too many search results, there is data
     * that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * ZZM9000E : [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * ZZZM9003I : The process [@] has been successfully completed.
     * exists.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * ZZM9037E : Please execute again after correcting the error
     * field.
     */
    public static final String ZZM9037E = "ZZM9037E";

    /**
     * NLZM2283E : Your request cannot be processed under this status.
     */
    public static final String NLZM2283E = "NLZM2283E";

    /**
     * NLBM0045E : Selected SO Information cannot be cancelled. Please
     * select a SO which can be cancelled.
     */
    public static final String NLBM0045E = "NLBM0045E";

    /**
     * NLZM2315E : This shipping order is not allowed to @ partially.
     * Please select all lines of this order.
     */
    public static final String NLZM2315E = "NLZM2315E";

    /**
     * NLBM1320E : Please select all components of the set item.
     */
    public static final String NLBM1320E = "NLBM1320E";

    /**
     * NLZM2284E : Select all details with the same Config Number. [@]
     */
    public static final String NLZM2284E = "NLZM2284E";

    /**
     * NLZM2284E : Cancel process ended abnormally.
     */
    public static final String NLBM0049E = "NLBM0049E";

    /**
     * NLBM0009E : It is being updated by another user. Please start
     * again from a search.
     */
    public static final String NLBM0009E = "NLBM0009E";

    /**
     * NLBM1314E : Not shipped line(s) doesn't exist in the shipping
     * order.
     */
    public static final String NLBM1314E = "NLBM1314E";

    /**
     * NLBM0024E : @ ended abnormally.
     */
    public static final String NLBM0024E = "NLBM0024E";

    /**
     * NLZM2277E : The value for [@] must be bigger than [@].
     */
    public static final String NLZM2277E = "NLZM2277E";

    /**
     * NLZM2316E : The value for [@] must be [@] or less.
     */
    public static final String NLZM2316E = "NLZM2316E";

    /**
     * NLZM2330E : This is different from UOM's Unit of Qty.
     */
    public static final String NLZM2330E = "NLZM2330E";

    /**
     * NLBM1313E : The entered "Serial Number" is duplicated in lines
     * you selected.
     */
    public static final String NLBM1313E = "NLBM1313E";

    /**
     * NLZM2317E : The entered Serial Number is already allocated by
     * other order.
     */
    public static final String NLZM2317E = "NLZM2317E";

    /**
     * NLZM2318E : The entered Serial Number is located at customer
     * site.
     */
    public static final String NLZM2318E = "NLZM2318E";

    /**
     * NLZM2319E : The entered Serial Number does not include in the
     * specified configuration.
     */
    public static final String NLZM2319E = "NLZM2319E";

    /**
     * NLZM2324E : The specified serial number is the component of the
     * other configuration.
     */
    public static final String NLZM2324E = "NLZM2324E";

    /**
     * NLBM1290E : Effective Asset does not exist.
     */
    public static final String NLBM1290E = "NLBM1290E";

    /**
     * NLBM1298W : The entered @ has already existed.
     */
    public static final String NLBM1298W = "NLBM1298W";

    /**
     * NLBM1273W : Request to Carrier is not done yet. Is this OK?
     */
    public static final String NLBM1273W = "NLBM1273W";

    /**
     * NLBM1300W : @ has not specified. Is this OK?
     */
    public static final String NLBM1300W = "NLBM1300W";

    /**
     * NLBM1301W : @ is different from the one previously @.
     */
    public static final String NLBM1301W = "NLBM1301W";

    /**
     * NLBM1299W : The Serial # specified does not exist in this WH.
     */
    public static final String NLBM1299W = "NLBM1299W";

    /**
     * NLZM2282W : To ignore the warning hit the [@] button again. To
     * correct the data hit the Apply button.
     */
    public static final String NLZM2282W = "NLZM2282W";

    /**
     * NLCM0125E : Data insert failure.@
     */
    public static final String NLCM0125E = "NLCM0125E";

    /**
     * NLCM0123E : The target data for the update does not exist.
     */
    public static final String NLCM0123E = "NLCM0123E";

    /** There are multiple @. Please select only one by Pop up. */
    public static final String NLBM1341E = "NLBM1341E";

    /** [@] and [@] are not consistent. */
    public static final String NLBM1308E = "NLBM1308E";

    /** Since physical inventory is in progress, cannot be processed. */
    public static final String NLBM1347E = "NLBM1347E";

    /** Please execute again after checking the warning field. */
    public static final String NATM0001W = "NATM0001W";

    /**  */
    public static final String ZZZM9005W = "ZZZM9005W";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** There is an error in @.  @, @ */
    public static final String NLBM1018E = "NLBM1018E";

    /** Shipping Qty and the Serial Qty does not match. */
    public static final String NLBM1355E = "NLBM1355E";


    /** abnormal end message : failed get report. */
    public static final String ABEND_MSG_FAILED_GET_REPORT = "get report bytes failure";

    /** Failed to PDF download. [@]. */
    public static final String NLBM1356E = "NLBM1356E";

    /** Multiple Shipping Order have been selected. Please select a single Shipping Order. */
    public static final String NLBM1357E = "NLBM1357E";

    /** The selected SO has already been sent to WMS. Manual cancel is needed from within WMS. Press button again to continue. */
    public static final String NLAM1342W = "NLAM1342W";

    /** Please select a Shipping Order. */
    public static final String NLBM1360W = "NLBM1360W";

    /** An error occurred at @. */
    public static final String NLBM1257E = "NLBM1257E";

    // QC#23726 Add.
    /** NLBM1369W : The relationship between '@' and '@' is incorrect. */
    public static final String NLBM1369W = "NLBM1369W";
    

    /**
     * Override Comparator Class
     * QC#21913 Add class.
     */
    public static class Comp implements Comparator<Object> {

        /** sortKey */
        private String sortKey;

        /**
         * Constructor
         * @param sortKey String
         */
        public Comp(String sortKey) {
            this.sortKey = sortKey;
        }

        /**
         * comparator
         * @param oPMsg1 Object
         * @param oPMsg2 Object
         * @return result int
         */
        @Override
        public int compare(Object oPMsg1, Object oPMsg2) {
            String ret1 = getValue(sortKey, oPMsg1);
            String ret2 = getValue(sortKey, oPMsg2);
            return ret1.compareTo(ret2);
        }

        private String getValue(String key, Object oPMsg) {
            try {
                Field field = oPMsg.getClass().getField(key);
                EZDTStringItem item = (EZDTStringItem) field.get(oPMsg);
                return item.getValue();
            } catch (Exception e) {
                return "";
            }
        }
    }
}
