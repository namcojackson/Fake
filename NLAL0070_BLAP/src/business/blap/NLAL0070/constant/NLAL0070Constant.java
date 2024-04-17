/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/28/2009   Fujitsu         FAP)H.Aoki      Create          N/A
 * 12/23/2009   Fujitsu         A.Akabane       Update          N/A
 * 02/25/2010   Fujitsu         A.Akabane       Update          Workshop RQ No.233-237
 * 05/19/2010   Fujitsu         T.Ishii         Update          4524
 * 06/03/2013   Hitachi         T.Tomita        Update          QC1209
 *</pre>
 */
package business.blap.NLAL0070.constant;

public interface NLAL0070Constant {

    // 2013/06/03 QC1209 T.Tomita Add Start
    /**
     * BusinessID
     */
    String BUSINESS_ID = "NLAL0070";

    /**
     * Comma
     */
    String COMMA = ",";

    /**
     * Warehouse
     */
    String WH = "WH";
    // 2013/06/03 QC1209 T.Tomita Add End
    // 2013/06/03 QC1209 T.Tomita Delete Start
//    /**
//     */
//    String WH_CD_ALL = "00";
    // 2013/06/03 QC1209 T.Tomita Delete End

    /**
     */
    String PROFILE_ALL = "*";

    /**
     */
    int CSV_DOWNLOAD_MAX_COUNT = 65000;

    /**
     */
    int WEEK = 7;

    /**
     */
    int AFTER_WEEK = 7;

    /**
     */
    int BEFORE_WEEK = -7;

    /**
     */
    int CHECK_SCHEDULE_WETA = 0;

    /**
     */
    int CHECK_WETA_MEMO = 1;

    /**
     */
    int CHECK_FINAL_SCHEDULE_WETA = 2;
    
    /**
     */
    int CHECK_DIFF_TERMINAL_WETA_FINAL_WETA = 3;
    
    /**
     */
    int CHECK_DIFF_TERMINAL_WETA_SCHEDULE_WETA = 4;
    
    /**
     */
    int CHECK_TERMINAL_TERMINAL_ETA_MEMO = 5;
    
    /**
     */
    int CHECK_RAIL_AVAILABLE_DATE = 6;

    /** For Pulldown List of codetable */
    String COLON = ":";
    
    /** TAB NAME: Schedule */
    String TAB_NAME_SCHEDULE = "Schedule";
    
    /** TAB NAME: Schedule */
    String TAB_NAME_SUMMARY = "Summary";
    
    /** Command Name */
    String CMD_SEARCH = "Search";
    
    /** Command Name */
    String CMD_DOWNLOAD = "Download";
    
    /** For Date Culiculate */
    int AFTER_1DAY = 1;
    
    /** For Date Culiculate */
    int AFTER_2DAYS = 2;
    
    /** For Date Culiculate */
    int AFTER_3DAYS = 3;
    
    /** For Date Culiculate */
    int AFTER_4DAYS = 4;
    
    /** For Date Culiculate */
    int AFTER_5DAYS = 5;
    
    /** For Date Culiculate */
    int AFTER_6DAYS = 6;
    
    /** Max Count for a page */
    int PAGE_MAX_COUNT = 40;
    
    /** Start Index for Date And Day */
    int START_INDEX_FOR_DATE_AND_DAY = 1;
    
    /** Start Index for Date And Day */
    int END_INDEX_FOR_DATE_AND_DAY = 15;

    /** For Search */
    int EVENT_TYPE_SEARCH = 0;
    
    /** For Submit */
    int EVENT_TYPE_SUBMIT = 1;
    
    /** For Next Week */
    int EVENT_TYPE_NEXTWEEK = 7;
    
    /** For Previous Week */
    int EVENT_TYPE_PREVWEEK = -7;
    
    /** File Name For Download of Schedule List */
    String CSV_FILE_NAME_SCHEDULE_FOR_MDSE = "NLAL0070WHScheduleListForMDSE";
    
    /** File Name For Download of Schedule List */
    String CSV_FILE_NAME_SCHEDULE_FOR_CNTNR_INV = "NLAL0070WHScheduleListForCNTNR_INV";
   
    /** File Name For Download of Summary List */
    String CSV_FILE_NAME_SUMMARY = "NLAL0070WHSummaryList";  
    
    /** File Extension For Download */
    String DOWNLOAD_FILE_EXTENSION = ".csv";
    
    /** Header Name For Download: Schedule */
    String[] DOWNLOAD_HEADER_NAME_FOR_SCHEDULE_OF_MDSE = {"WH","Schedule#", "Delivery Type", "Shipping Method", "Priority", "Event Name", "Terminal Name", "Terminal ETA", "WH ETA", "Terminal ETA Memo"
    		, "Schedule(HQ) WETA", "Inbound Visibility", "Schedule(Revised) WETA", "Inbound Visibility", "Rail Available Date", "Appointment Time", "Appointment Drayage Carrier"
    		, "Item Number", "Item Name", "Item Q'ty", "PKG Master", "MultiWH WH Code", "Carrier", "Vessel", "B/L#", "Consignee Code", "Invoice#", "Container#", "RWS#", "RWS Type", "Schedule Type"
    		, "Comment"};

    /** Header Name For Download: Schedule */
    String[] DOWNLOAD_HEADER_NAME_FOR_SCHEDULE_OF_CNTNR_INV = {"WH","Schedule#", "Delivery Type", "Shipping Method", "Priority", "Event Name", "Terminal Name", "Terminal ETA", "WH ETA", "Terminal ETA Memo"
    		, "Schedule(HQ) WETA", "Inbound Visibility", "Schedule(Revised) WETA", "Inbound Visibility", "Rail Available Date", "Appointment Time", "Appointment Drayage Carrier"
    		, "Item Number", "Item Name", "Item Q'ty", "PKG Master", "MultiWH WH Code", "Carrier", "Vessel", "B/L#", "Consignee Code", "Invoice#", "Container#", "RWS#", "RWS Type", "Schedule Type"
    		, "Comment"};
    
    /** Header Name For Download: Summary */
    String[] DOWNLOAD_HEADER_NAME_FOR_SUMMARY = {"WH", "Schedule Type", "Latest WH ETA", "Num", "Pallet", "Case"};
    
    /** Stock Status Code : Good */
    String STOCK_STS_CD_GOOD = "1";
    
    /** LGSC_DELY_TP: 0 */
    String LGSC_DELY_TP_DEF = "NEF";
    
    /** LGSC_DELY_TP: 1 */
    String LGSC_DELY_TP_CR = "CR";
    
    /** LGSC_DELY_TP: 2 */
    String LGSC_DELY_TP_TL_OR_DI = "DI/TL";
    
    /** Radio Button: MDSE */
    int RADIO_MDSE = 1;
    
    /** Radio Button: CNTNR/INV */
    int RADIO_CNTNR_INV = 2;
    
    /** Maximum number of RWS QTY */
    int RWS_QTY_MAX = 9999;
    
    /** Maximum number of Pallet QTY */
    int PALLET_QTY_MAX = 99999;
    
    /** Maximum number of Case QTY */
    int CASE_QTY_MAX = 99999;
    
    /** Locale: YYYYMMDDHHMMSSsss*/
    String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";
}
