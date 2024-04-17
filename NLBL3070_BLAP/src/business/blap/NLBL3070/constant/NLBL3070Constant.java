/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3070.constant;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Comparator;

import parts.common.EZDTStringItem;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 * 07/19/2016   CSAI            Y.Imazu         Update          QC#10232
 * 08/31/2016   CSAI            Y.Imazu         Update          QC#9845
 * 02/10/2017   CITS            M.Naito         Update          QC#12673
 * 06/15/2017   CITS            R.Shimamoto     Update          QC#18272
 * 2017/12/28   CITS            T.Hakodate      Update          QC#18460(SOL#087)
 * 11/30/2017   CITS            M.Naito         Update          QC#18889
 * 01/19/2018   Hitachi         K.Ochiai        Update          QC#18889
 * 02/21/2018   CITS            T.Tokutomi      Update          QC#18725
 * 03/23/2018   CITS            S.Katsuma       Update          QC#24697
 * 07/02/2018   CITS            T.Tokutomi      Update          QC#23726
 * 10/03/2018   CITS            M.Naito         Update          QC#28539
 * 10/25/2018   CITS            K.Kameoka       Update          QC#28923
 * 02/04/2020   CITS            M.Furugoori     Update          QC#50121
 * 04/04/2022   CITS            R.Azucena       Update          QC#59802
 * 08/08/2022   CITS            R.Azucena       Update          QC#60416
 * 12/12/2022   Hitachi         T.Kuroda        Update          QC#60810
 *</pre>
 */

public class NLBL3070Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLBL3070";

    /** Screen ID */
    public static final String SCREEN_ID = "NLBL3070Scrn00";

    /** Role ID : All Warehouse Permission */
    public static final String ROLE_ALL_WH_PERMISSION = "NLBL3070T030";

    /** TAB ID:Scheduling */
    public static final String TAB_ID_SCHD = "Scheduling";

    /**  TAB ID:Deliveries */
    public static final String TAB_ID_DELV = "Deliveries";

    /** Search Type: 1(by Search Condition) */
    public static final String SEARCH_TP_COND = "1";

    /** Search Type: 2(Re-Search by So#) */
    public static final String SEARCH_TP_RESEARCH = "2";

    /** Search Type: 3(by Change Tab) */
    public static final String SEARCH_TP_TAB = "3";

    /** Search Type: 4(by Search Condition for Schedule Tab) */
    public static final String SEARCH_TP_COND_SCHD = "4";

    /** Value : TIME_AM */
    public static final String TIME_AM = "AM";

    /** Value : TIME_PM */
    public static final String TIME_PM = "PM";

    /** Hour minute string length : HHMM */
    public static final int HOUR_MINUTE_STR_LENGTH = 4;

    /** half day hours */
    public static final int HALF_DAY_HOURS = 12;

    /** One day hours */
    public static final int ONE_DAY_HOURS = 24;

    /** One hour minutes */
    public static final int ONE_HOUR_MINUTES = 60;

    /** VAR_CHAR_CONST_NM : CPO_DS_ORD_TP_FOR_WH_TRNSF */
    public static final String VAR_CONST_CPO_DS_ORD_TP_FOR_WH_TRNSF = "CPO_DS_ORD_TP_FOR_WH_TRNSF";

    /** EDI Time Code : Local Time */
    public static final String EDI_TM_CD_LT = "LT";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME_SCHD = "NLBL3070_DeliveryScheduling";

    /** CSV file name */
    public static final String CSV_FILE_NAME_DELV = "NLBL3070_ManageDeliveries";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** First SO Line (001) */
    public static final String SO_LINE_001 = "001";

    // START 2017/11/30 M.Naito [QC#18889,ADD]
    /** VAR_CHAR_CONST_NM : OUT_TRTY_SVC_BR_CD */
    public static final String VAR_CONST_OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";

    /** ****************************************** */
    /** Mail */
    /** ****************************************** */
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** mail from group */
    public static final String MAIL_FROM_GRP = "NLB";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String EMAIL_PARAM_BEFORE_DATE_TIME = "beforeDateTime";

    /** . */
    public static final String EMAIL_PARAM_AFTER_DATE_TIME = "afterDateTime";

    /** . */
    public static final String EMAIL_PARAM_TASK_NUM = "svcTaskNum";

    /** . */
    public static final String EMAIL_PARAM_FSR_NUM = "fsrNum";

    /** . */
    public static final String EMAIL_PARAM_CUST_NM = "custNm";

    /** . */
    public static final String EMAIL_PARAM_ISTL_LOC = "istlLoc";

    /** . */
    public static final String EMAIL_PARAM_SER_NUM = "serNum";

    /** . */
    public static final String EMAIL_PARAM_TECH_NM = "techNm";

    /** . */
    public static final String EMAIL_PARAM_TECH_EML_ADDR = "techEmlAddr";

    /** . */
    public static final String EMAIL_PARAM_TECH_PHONE_NUM = "techPhoneNum";

    /** DATE_FORMAT_DDMMMYYYY : dd-MMM-yyyy */
    public static final String DATE_FORMAT_DDMMMYYYY = "dd-MMM-yyyy";

    /** ORG_FUNC_ROLE_TP : Branch Service Manager */
    public static final String ORG_FUNC_ROLE_TP_BR_SVC_MGR = "FS010";
    // END 2017/11/30 M.Naito [QC#18889,ADD]

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR_SCHD = new String[] {
          "CheckBox"
        , "Source Order"
        , "Config Id"
        , "Back Order Status"
        , "Shipping Order"
        , "Model"
        , "Requested Delivery Date"
        , "Scheduled Carrier Pickup Date"
        , "Scheduled Delivery Date Time"
        // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
        , "Tech Meet The Truck"
        // END   2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
        // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
        , "Next Action Date"
        // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
        , "Carrier"
        //QC#28923 Del Start
//        , "Time Stop"
        //QC#28923 Del End
        , "Call Duration"
        , "FSR#"
        , "FSR Schedule Date"
        , "Technician"
        , "Scheduling Status"
        , "Customer Contact"
        , "Sales Rep"
        , "Telephone"
        , "Customer Name"
        , "Customer Address1"
        , "Customer Address2"
        , "City"
        , "State"
        , "Zip"
        , "bottom1"
        , "Source Warehouse"
        , "WMS Dropped"
        , "Drop Ready"
        , "Carrier Request"
        , "Service Level"
        , "Carrier Account Number"
        , "Coordinator"
        , "Schedule Notes"
        , "Schedule Notes Comment"
        , "Order Category"
        , "Order Reason"
        , "bottom2"};

    /** CSV_HDR_DELV */
    public static final String[] CSV_HDR_DELV = new String[] {
          "xxSmryLineFlg"
        , "checkBox1"
        , "checkBox2"
        , "bottom"
        , "Ship Config Id"
        , "Sales Order"
        , "Sales Order Line"
        , "Shipping Order"
        , "Shipping Order Line"
        , "Schdedule Coordinator"
        , "Source Warehouse"
        , "Source Sub Warehouse"
        , "Customer Ship To"
        , "Customer Contact"
        , "Item"
        , "Item Description"
        , "Essential/Critical"
        , "Quantity"
        , "Serial Number"
        , "Request Date"
        , "Scheduled Delivery Date Time"
        // START 2018/03/23 S.Katsuma [QC#24697,MOD]
        // QC#22613 Add.
        , "Order Released"
        // END 2018/03/23 S.Katsuma [QC#24697,MOD]
        , "Pick Confirm Date"
        , "Delivered to Shop"
        , "Tech Assigned Date"
        , "Delivered to Distribution Stage"
        , "Ship Confirm Date"
        , "Carrier Reason"
        , "Actual Delivery Date Time"
        , "Installation Date"
        , "Install Call Status"
        , "Shipping Order Status"
        , "Carrier"
        , "Tracking Number"
        , "Service Level"
        , "Scheduling Status"
        , "Send Request Date"
        , "Ship Cost"
        , "Ship To Name"
        , "Ship To City"
        , "Pick Config ID"
        , "Stock Status"
        , "Order Category"};

    /***************************************************
     * Message
     ***************************************************/

    /** Please execute again after checking the warning field. */
    public static final String NATM0001W = "NATM0001W";

    /** Entered date is later (future date) than today. */
    public static final String NLAM1087E = "NLAM1087E";

    /** Details require more than 1 row.  Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /** It is being updated by another user.  Please start again from a search. */
    public static final String NLBM0009E = "NLBM0009E";

    /** @ ended abnormally. */
    public static final String NLBM0024E = "NLBM0024E";

    /** Selected SO Information cannot be cancelled.  Please select a SO which can be cancelled. */
    public static final String NLBM0045E = "NLBM0045E";

    /** Cancel process ended abnormally. */
    public static final String NLBM0049E = "NLBM0049E";

    /** It failed to register @. */
    public static final String NLBM1295E = "NLBM1295E";

    /** [@] has not been changed. */
    public static final String NLBM1303E = "NLBM1303E";

    /** Not open status line(s) are included in selected Shipping Order. */
    public static final String NLBM1307E = "NLBM1307E";

    /** [@] and [@] are not consistent. */
    public static final String NLBM1308E = "NLBM1308E";

    /** [@] and [@] are not consistent. */
    public static final String NLBM1328W = "NLBM1328W";

    /** Please enter today or future date on [@]. */
    public static final String NLBM1231E = "NLBM1231E";

    /** Request to Carrier is not done yet. Is this OK? */
    public static final String NLBM1273W = "NLBM1273W";

    /** Effective Asset does not exist. */
    public static final String NLBM1290E = "NLBM1290E";

    /** The entered @ has already existed. */
    public static final String NLBM1298W = "NLBM1298W";

    /** The Serial # specified does not exist in this WH. */
    public static final String NLBM1299W = "NLBM1299W";

    /** @ has not specified. Is this OK? */
    public static final String NLBM1300W = "NLBM1300W";

    /** @ is different from the one previously @. */
    public static final String NLBM1301W = "NLBM1301W";

    /** @ is duplicated. */
    public static final String NLBM1304W = "NLBM1304W";

    /** Time verification error occurred in [@] field (hhmmss). */
    public static final String NLBM1305E = "NLBM1305E";

    /** Cannot partial shipment cancel. Select all within the same Shipping Order#. */
    public static final String NLBM1309E = "NLBM1309E";

    /** Not allowed to proceed when data is for "Send Request". */
    public static final String NLBM1311E = "NLBM1311E";

    /** It's a Component Item. Please select all within the same Component Item. */
    public static final String NLBM1312E = "NLBM1312E";

    /** Not shipped line(s) doesn't exist in the shipping order. */
    public static final String NLBM1314E = "NLBM1314E";

    /** There are lines with WH and Tracking Number. Please enter same Actual Delivery Date as the line. */
    public static final String NLBM1315E = "NLBM1315E";

    /** The entered "Serial Number" is duplicated in lines you selected. */
    public static final String NLBM1313E = "NLBM1313E";

    /** Please enter the date equal to or greater than the ship confirm date. */
    public static final String NLBM1316E = "NLBM1316E";

    /** To enter fewer serial numbers than registered, must set the all existing data. */
    public static final String NLBM1317E = "NLBM1317E";

    /** Already sent the request to the carrier. Do you need to update carrier code? */
    public static final String NLBM1327W = "NLBM1327W";

    /** There are multiple @. Please select only one by Pop up. */
    public static final String NLBM1341E = "NLBM1341E";

    /** If "@" is selected, cannot input "@". */
    public static final String NLBM1342E = "NLBM1342E";

    /** Since physical inventory is in progress, cannot be processed. */
    public static final String NLBM1347E = "NLBM1347E";

    /** If Carrier Reason is [@], please enter Actual Delivery Date. */
    public static final String NLBM1349E = "NLBM1349E";

    /** Cannot change Actual Delivery Date to blank because this line has already been arrived. */
    public static final String NLBM1350E = "NLBM1350E";

    /** Selected Shipping Order is not a target for Cancel in this screen because the ship from location is @. */
    public static final String NLBM1351E = "NLBM1351E";

    /** The Information for checking the cancel availability in this screen cannot be found. Please contact system administrators. */
    public static final String NLBM1352E = "NLBM1352E";

    /** Dates other than business days are set. */
    public static final String NLCM0092W = "NLCM0092W";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NLZM2273E = "NLZM2273E";

    /** [@] is not selected. */
    public static final String NLZM2274E = "NLZM2274E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NLZM2277E = "NLZM2277E";

    /** The entered [@] does not exist in master. */
    public static final String NLZM2278E = "NLZM2278E";

    /** The combination of [@] and [@] does not exist in master. */
    public static final String NLZM2279E = "NLZM2279E";

    /** The value for [@] must be the same as [@]. */
    public static final String NLZM2280E = "NLZM2280E";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** Select all details with the same Config Number. [@] */
    public static final String NLZM2284E = "NLZM2284E";

    /** NLZM2313E : You don't have the permission for the specified Warehouse. */
    public static final String NLZM2313E = "NLZM2313E";

    /** This shipping order is not allowed to @ partially. Please select all lines of this order. */
    public static final String NLZM2315E = "NLZM2315E";

    /** The value for [@] must be [@] or less. */
    public static final String NLZM2316E = "NLZM2316E";

    /** The entered Serial Number is already allocated by other order. */
    public static final String NLZM2317E = "NLZM2317E";

    /** The entered Serial Number is located at customer site. */
    public static final String NLZM2318E = "NLZM2318E";

    /** The entered Serial Number does not include in the specified configuration. */
    public static final String NLZM2319E = "NLZM2319E";

    /** This is different from UOM's Unit of Qty. */
    public static final String NLZM2330E = "NLZM2330E";

    /** Data does not exist in Carrier Reason. */
    public static final String NLZM2390E = "NLZM2390E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** If [@] is entered, please enter [@], too. */
    public static final String NPAM1190E = "NPAM1190E";

    /** The input data is not yet saved . If you want to save your changes, please click the submit button. */
    public static final String NSBM0029W = "NSBM0029W";

    /** Entered time is incorrect. */
    public static final String NWAM0665E = "NWAM0665E";

    /** The value for @ must be greater than or equal to @. */
    public static final String NFCM0780E = "NFCM0780E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Data update failure.  [ TableName = @ , key = @, value = @ ] */
    public static final String ZZMM0015E = "ZZMM0015E";

    /** No search results found. */
    public static final String NLBM1232I = "NLBM1232I";

    // START 2017/11/30 M.Naito [QC#18889,ADD]
    /** It failed to register an email. */
    public static final String NLBM1065E = "NLBM1065E";
    // END 2017/11/30 M.Naito [QC#18889,ADD]

    // QC#18725 Add.
    /** NLBM1297E : If choose @ at @ ,@ is required. */
    public static final String NLBM1297E = "NLBM1297E";

    // QC#18725 Add.
    /** NLBM1018E : There is an error in @.  @, @ */
    public static final String NLBM1018E = "NLBM1018E";

    // QC#23726 Add.
    /** NLBM1369W : The relationship between '@' and '@' is incorrect. */
    public static final String NLBM1369W = "NLBM1369W";

    // START 2020/02/04 [QC#50121, ADD]
    /** NMAM8163W : If [@] is set, [@] must be entered. */
    public static final String NMAM8163W = "NMAM8163W";
    // END 2020/02/04 [QC#50121, ADD]

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /** It is necessary to remove the config from Config Change Screen and increase the available single item. */
    // public static final String NLBM1376E = "NLBM1376E";
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]

    // START 2018/01/19 K.Ochiai [QC#18889,ADD]
    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_ESS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_ESS = "MEET_TRUCK_ERL_START_TM_ESS";

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_LFS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_LFS = "MEET_TRUCK_ERL_START_TM_LFS";

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_PPS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_PPS = "MEET_TRUCK_ERL_START_TM_PPS";
    // END 2018/01/19 K.Ochiai [QC#18889,ADD]

    // START 2018/10/03 M.Naito [QC#28539,ADD]
    /** VAR_CHAR_CONST : MEET_TRUCK_RSP_TM_MN_AOT */
    public static final String NUM_CONST_MEET_TRUCK_RSP_TM_MN_AOT = "MEET_TRUCK_RSP_TM_MN_AOT";

    /** default Value :  MEET_TRUCK_RSP_TM_MN_AOT */
    public static final BigDecimal DEF_MEET_TRUCK_RSP_TM_MN_AOT = BigDecimal.valueOf(30);

    /** Hour minute string length : HHMMDD */
    public static final int HOUR_MINUTE_LEN = 4;

    /** Default HHmmss Value */
    public static final String DEF_HHMMSS = "000000";
    // END 2018/10/03 M.Naito [QC#28539,ADD]

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /** SSM_GLBL_CMPY_CD */
    // public static final String SSM_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM_MDSE_CD */
    // public static final String SSM_MDSE_CD = "mdseCd";

    /** SSM_STK_STS_CD */
    // public static final String SSM_STK_STS_CD = "stkStsCd";

    /** SSM_CUR_LOC_NUM */
    // public static final String SSM_CUR_LOC_NUM = "curLocNum";

    /** SSM_SVC_MACH_MAINT_AVAL_FLG */
    // public static final String SSM_SVC_MACH_MAINT_AVAL_FLG = "svcMachMaintAvalFlg";

    /** SSM_PICK_SVC_CONFIG_MSTR_PK */
    // public static final String SSM_PICK_SVC_CONFIG_MSTR_PK = "pickSvcConfigMstrPk";

    /** SSM_SVC_MACH_MSTR_STS_CD_LIST */
    // public static final String SSM_SVC_MACH_MSTR_STS_CD_LIST = "svcMachMstrStsCdList";
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]

    // START 2022/12/12 T.Kuroda [QC#60810, ADD]
    /** This is a WMS SO and should be processed in the WMS. If you still want to proceed, please click "Ship Confirm" button again. */
    public static final String NLBM1378W = "NLBM1378W";
    // END 2022/12/12 T.Kuroda [QC#60810, ADD]

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
