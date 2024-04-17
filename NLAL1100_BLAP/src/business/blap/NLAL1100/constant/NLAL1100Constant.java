/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL1100.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NLAL1100 Warehouse Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 * 05/16/2016   CSAI            Y.Imazu         Update          QC#7972
 * 11/22/2016   CITS            Y.Fujii         Update          R362
 * 12/27/2016   CITS            Y.Fujii         Update          QC#16547
 * 02/02/2017   CITS            M.Naito         Update          QC#12673
 * 11/17/2017   CITS            K.Ogino         Update          QC#22587
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 * 05/09/2019   Fujitsu         T.Ogura         Update          QC#50027
 * 02/05/2020   CITS            M.Furugoori     Update          QC#50121
 * 12/07/2023   Hitachi         K.Ishizuka      Update          QC#61300
 *</pre>
 */
public class NLAL1100Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NLAL1100";

    /** Screen ID */
    public static final String SCREEN_ID = "NLAL1100Scrn00";

    /** Line Break Code */
    public static final String LINE_BREAK_CD = "\r\n";

    /** Date format : HHmmssSSS modify QC#22587*/
    public static final String DT_FMT_HHMMSSSSS = "HHmmssSSS";

    /** MSG_TYPE_ERROR */
    public static final String MSG_TYPE_ERROR = "E";

    /** MSG_TYPE_WARNING */
    public static final String MSG_TYPE_WARNING = "W";

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /** Value : TIME_AM */
    public static final String TIME_AM = "AM";

    /** Value : TIME_PM */
    public static final String TIME_PM = "PM";

    /** Hour minute string length : HHMM */
    public static final int HOUR_MINUTE_STR_LENGTH = 4;

    /** half day hours */
    public static final int HALF_DAY_HOURS = 12;

    /** One hour minutes */
    public static final int ONE_HOUR_MINUTES = 60;
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    // START 2023/12/07 K.Ishizuka [QC#61300,ADD]
    /** ****************************************** */
    /** Mail */
    /** ****************************************** */
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** mail from group */
    public static final String MAIL_FROM_GRP = "NLA";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

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
    public static final String EMAIL_PARAM_DEINS_LOC = "deinsLoc";

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

    /** Hour minute string length : HHMMDD */
    public static final int HOUR_MINUTE_LEN = 4;

    /** Default HHmmss Value */
    public static final String DEF_HHMMSS = "000000";
    // END   2023/12/07 K.Ishizuka [QC#61300,ADD]
    
    /***************************************************
     * Message
     ***************************************************/

    /** Please execute again after checking the warning field. */
    public static final String NATM0001W = "NATM0001W";

    /** The entered [@] does not exist in master. */
    public static final String NLZM2278E = "NLZM2278E";

    /** The combination of [@] and [@] does not exist in master. */
    public static final String NLZM2279E = "NLZM2279E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NLZM2273E = "NLZM2273E";

    /** [@] is not selected. */
    public static final String NLZM2274E = "NLZM2274E";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** The target data for the update does not exist. */
    public static final String NLCM0123E = "NLCM0123E";

    /** It is being updated by another user.  Please start again from a search. */
    public static final String NLBM0009E = "NLBM0009E";

    // START 2023/12/07 K.Ishizuka [QC#61300,ADD]
    /** It failed to register an email. */
    public static final String NLBM1065E = "NLBM1065E";
    // END   2023/12/07 K.Ishizuka [QC#61300,ADD]

    /** If carrier has changed, please submit it in prior  to this process. */
    public static final String NLBM1286E = "NLBM1286E";

    /** It failed to register @. */
    public static final String NLBM1295E = "NLBM1295E";

    /** [@] and [@] are not consistent. */
    public static final String NLBM1308E = "NLBM1308E";

    /** [@] and [@] are not consistent. */
    public static final String NLBM1328W = "NLBM1328W";

    /** Already sent the request to the carrier. Do you need to update carrier code? */
    public static final String NLBM1327W = "NLBM1327W";

    /** Not allowed to proceed when data is for "Send Request". */
    public static final String NLBM1311E = "NLBM1311E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to print [@]. */
    public static final String NLZM2275E = "NLZM2275E";

    /** Multiple Details cannot be processed at the same time. */
    public static final String NLAM0046E = "NLAM0046E";

    /** The details of the process target have not been selected. */
    public static final String NPAM0049E = "NPAM0049E";

    /** @ ended abnormally. */
    public static final String NLBM0024E = "NLBM0024E";

    /** */
    public static final String NWAM0146E = "NWAM0146E";

    /** Details require more than 1 row.  Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /** Entered time is incorrect. */
    public static final String NWAM0665E = "NWAM0665E";    // 2019/05/09 T.Ogura [QC#50027,ADD]

    // START 2020/02/05 [QC#50121, ADD]
    /** If [@] is set, [@] must be entered. */
    public static final String NMAM8163W = "NMAM8163W";
    // END 2020/02/05 [QC#50121, ADD]
    
    // START 2023/12/07 K.Ishizuka [QC#61300,ADD]

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_ESS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_ESS = "MEET_TRUCK_ERL_START_TM_ESS";

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_LFS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_LFS = "MEET_TRUCK_ERL_START_TM_LFS";

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_PPS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_PPS = "MEET_TRUCK_ERL_START_TM_PPS";

    /** VAR_CHAR_CONST : MEET_TRUCK_RSP_TM_MN_AOT */
    public static final String NUM_CONST_MEET_TRUCK_RSP_TM_MN_AOT = "MEET_TRUCK_RSP_TM_MN_AOT";
    
    /** default Value :  MEET_TRUCK_RSP_TM_MN_AOT */
    public static final BigDecimal DEF_MEET_TRUCK_RSP_TM_MN_AOT = BigDecimal.valueOf(30);
    // END   2023/12/07 K.Ishizuka [QC#61300,ADD]
    
    /***************************************************
     * CSV Download
     ***************************************************/

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NLAL1100_ManageRMAOrders";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV_HDR */
    public static final String[] CSV_HDR = new String[] {
          "Summary Flag"
        , "Check Box"
        , "Config ID"
        , "RMA Order Number"
        , "RMA Order Line Number"
        , "RWS Number"
        , "RWS Line Number"
        , "Warehouse"
        , "Sub Warehouse"
        , "Customer Pickup From"
        , "Installed At"
        , "Customer Contact"
        , "Item Number"
        , "Item Description"
        , "Quantity"
        , "Serial Number"
        , "Booked Date"
        , "Aging Bucket"
        , "Assigned to Group"
        , "Scheduled Pickup Date"
        , "Tech Meet The Truck"    // 2019/05/09 T.Ogura [QC#50027,ADD]
        , "Carrier Reason"
        , "Actual Pickup Date"
        , "Inspection Completion Date"
        , "RMA Close Date"
        , "Out Of Territory"
        , "Carrier"
        , "Carrie Rqst"
        , "Order Category"
        , "Order Reason"
        , "Order Line Category"
        , "Order Line Status"
        , "Pickup Status"
        , "FSR Number"
        , "HDD Removal Date"
        , "HDD Hold Released"
        , "RWS Status"
        , "RWS Close Date"
        , "Request Pickup Date"
        , "Pickup Service Level"
        , "Carrier Account"
        , "Coordinator"
        , "Scheduling Status"
        , "Model"
        , "Customer Pickup Code"
        , "City"};


    /***************************************************
     * EVENT_NM
     ***************************************************/
    /** for BL09(Print) */
    public static final String EVENT_NM_NLAL1100_PRINT = "NLAL1100Scrn00_Print";

    /***************************************************
     * EIP Report
     ***************************************************/
    /** REPORT_ID. */
    public static final String REPORT_ID = "NLAF0020";

    /** GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** CSA_RMA_RPT_PRINT_RQST_SQ. */
    public static final String CSA_RMA_RPT_PRINT_RQST_SQ = "CSA_RMA_RPT_PRINT_RQST_SQ";

    /** RWS_REF_NUM. */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";

    /** INTL_LANG_VAL_COL_NM. */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** Report Title Name. */
    public static final String REPORT_TITLE = " RMA Return Order Report";

    /** Report File Extension. */
    public static final String REPORT_FILE_EXTENSION = ".pdf";

    /** CHAR_HYPHEN. */
    public static final char CHAR_HYPHEN = '-';

    /** CHAR_BLANK. */
    public static final char CHAR_BLANK = ' ';

    /** MULTIPLE */
    public static final String MULTIPLE = "Multiple";
}
