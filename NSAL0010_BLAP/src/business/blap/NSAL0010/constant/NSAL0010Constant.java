/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0010.constant;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/20   CUSA            Fujitsu         Create          N/A
 * 03/25/2014   Fujitsu         S.Nakai         Update          SR#510517
 * 11/11/2015   Hitachi         T.Tomita        Update          QC#569
 * 11/25/2015   Hitachi         T.Tomita        Update          QC#970
 * 2015/12/09   Hitachi         T.Tomita        Update          QC#951
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#2255
 * 2016/02/17   Hitachi         A.Kohinata      Update          QC#1986
 * 2016/02/26   Hitachi         T.Tomita        Update          QC#942
 * 2016/03/31   Hitachi         A.Kohinata      Update          QC#4891
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6218
 * 2016/04/25   Hitachi         T.Tomita        Update          QC#6672
 * 2016/04/28   Hitachi         T.Tomita        Update          QC#5398
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#8215
 * 2016/05/13   Hitachi         T.Tomita        Update          QC#7794
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/07/22   Hitachi         T.Tomita        Update          QC#11161
 * 2016/07/29   Hitachi         T.Tomita        Update          QC#12638
 * 08/10/2016   CSAI            Y.Imazu         Update          QC#12496
 * 2016/09/06   Hitachi         T.Tomita        Update          QC#13532
 * 2016/10/13   Hitachi         T.Tomita        Update          CSA QC#14734
 * 2016/11/14   Hitachi         N.Arai          Update          QC#15829
 * 2017/01/27   Hitachi         K.Kojima        Update          QC#16600
 * 2017/10/16   Hitachi         T.Tomita        Update          QC#21563
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#19932
 * 2019/08/07   Hitachi         A.Kohinata      Update          QC#52198
 * 2020/04/15   Hitachi         K.Sakurai       Update          QC#56528
 * 2020/04/24   Hitachi         K.Sakurai       Update          QC#56672
 * 2021/08/17   CITS            R.Cabral        Update          QC#59010
 * 2023/09/22   Hitachi         T.Kuroda        Update          QC#61265
 *</pre>
 */
public class NSAL0010Constant {

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Failed to update @ [@]. */
    public static final String NSAM0001E = "NSAM0001E";

    /**
     * The corresponding data does not exist.
     * <TABLE: [@], Data Key: [@]>
     */
    public static final String NSAM0004E = "NSAM0004E";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** When Machine Status is "@", "@" is required. */
    public static final String NSAM0008E = "NSAM0008E";

    /** Serial# will be changed. Is this OK? */
    public static final String NSAM0009W = "NSAM0009W";

    /** When Auto Create is "Y", you cannot change Business Type. */
    public static final String NSAM0010E = "NSAM0010E";

    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    /** Failed to insert @ table. [@] */
    public static final String NSAM0012E = "NSAM0012E";

    /** No search results found. */
    public static final String NSAM0013E = "NSAM0013E";

    /**
     * [@] has been updated. Do you want to proceed? If OK, please
     * click the "Submit" button.
     */
    public static final String NSAM0014W = "NSAM0014W";

    /**
     * Dummy Serial# will be generated because Serial# is not entered.
     * Do you want to proceed? If OK, please click the "Submit"
     * button.
     */
    public static final String NSAM0053W = "NSAM0053W";

    /** When Machine Status is "@", "@" cannot be entered. */
    public static final String NSAM0054E = "NSAM0054E";

    /** Machine Status cannot be changed to "@" from "@". */
    public static final String NSAM0055E = "NSAM0055E";

    /** You don't have the authorization to install. */
    public static final String NSAM0056E = "NSAM0056E";

    /**
     * Machine Status will be changed to "@" from "@". Do you want to
     * proceed? If OK, please click the "Submit" button.
     */
    public static final String NSAM0057W = "NSAM0057W";

    /** @ does not exist in @. */
    public static final String NSAM0063E = "NSAM0063E";

    /** When Machine Status is not "@", "@" cannot be entered. */
    public static final String NSAM0068E = "NSAM0068E";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Service Machine Type cannot be changed from "@" to "@". */
    public static final String NSAM0073E = "NSAM0073E";

    /**
     * Service Machine Type can be changed from "@" to "@" only if
     * there is no related accessories.
     */
    public static final String NSAM0074E = "NSAM0074E";

    /** Invalid combination.  @ & @  */
    public static final String NSAM0127E = "NSAM0127E";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /** Serial# cannot be changed when it is shipped from asset warehouse. */
    public static final String NSAM0297E = "NSAM0297E";

    /** Entered Serial# has already been registered in Direct sales asset master. */
    public static final String NSAM0204E = "NSAM0204E";

    /** The chronological sequence between the dates is wrong. */
    public static final String NSAM0062E = "NSAM0062E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";

    /** The selected record has already been registered and cannot be deleted. */
    public static final String NSAM0321E = "NSAM0321E";

    /** @ cannot be added because it is exceeding the maximum number of row [@]. */
    public static final String NSAM0320E = "NSAM0320E";
    
    // START 2020/04/15 K.Sakurai [QC#56528, ADD]
    /** You can not enter [@] earlier than [@]. */
    public static final String NSAM0346E = "NSAM0346E";
    // END 2020/04/15 K.Sakurai [QC#56528, ADD]

    /** Please enter Remove Date of old machine if new machine will be inserted. */
    public static final String NSAM0377E = "NSAM0377E";

    /** Duplicate machines exist. */
    public static final String NSAM0378E = "NSAM0378E";

    /** [@] cannot be the future date. Please re-examine the [@]. */
    public static final String NSAM0379E = "NSAM0379E";

    /** Added machine is already used by other configuration. [@] */
    public static final String NSAM0380E = "NSAM0380E";

    /** Machine master status cannot be updated because active contract exists.[@] */
    public static final String NSAM0381E = "NSAM0381E";

    /** Machine master status cannot be updated because active order exists.[@] */
    public static final String NSAM0382E = "NSAM0382E";

    // START 2017/01/27 K.Kojima [QC#16600,DEL]
    // // START 2015/11/25 T.Tomita [QC#970, ADD]
    // /** Specified "@" already exists. */
    // public static final String NSAM0059E = "NSAM0059E";
    // // END 2015/11/25 T.Tomita [QC#970, ADD]
    // END 2017/01/27 K.Kojima [QC#16600,DEL]
    
    // START 2017/01/27 K.Kojima [QC#16600,ADD]
    /** The combination of the specified @ and Item Number already exists. */
    public static final String NSAM0622E = "NSAM0622E";
    // END 2017/01/27 K.Kojima [QC#16600,ADD]

    // START 2015/12/09 T.Tomita [QC#951, ADD]
    /** Entered Item Number is not install base trackable. */
    public static final String NSAM0399E = "NSAM0399E";
    // END 2015/12/09 T.Tomita [QC#951, ADD]

    // START 2015/11/11 T.Tomita [QC#569, ADD]
    /** This record already exists. */
    public static final String ZZSM4163E = "ZZSM4163E";
    // END 2015/11/11 T.Tomita [QC#569, ADD]

    // START 2016/02/16 [QC#2255, ADD]
    /** Relationship between Machine Master Status and Machine Usage Status are invalid. */
    public static final String NSZM0869E = "NSZM0869E";
    // END 2016/02/16 [QC#2255, ADD]

    // START 2016/02/17 [QC#1986, ADD]
    /** Relation between "@" and "@" is not correct. */
    public static final String NSAM0138E = "NSAM0138E";
    // END 2016/02/17 [QC#1986, ADD]

    // START 2016/03/31 [QC#4891, ADD]
    /** Only a contact can be a primary contact. */
    public static final String NSAM0445E = "NSAM0445E";
    // END 2016/03/31 [QC#4891, ADD]

    /** Serial# cannot be entered because the specified item is not serial trackable. */
    public static final String NSAM0451E = "NSAM0451E";

    // add start 2016/04/19 CSA Defect#6218
    /** [@] cannot be entered when [@] is "@". */
    public static final String NSAM0448E = "NSAM0448E";
    // add end 2016/04/19 CSA Defect#6218

    // START 2016/05/12 T.Tomita [QC#8215, ADD]
    /** Specified item is not Inventory Trackable therefore Serial # cannot be entered. */
    public static final String NSAM0473E = "NSAM0473E";
    // END 2016/05/12 T.Tomita [QC#8215, ADD]

    // START 2016/05/13 T.Tomita [QC#7794, ADD]
    /** Multiple @ exist in @. */
    public static final String NSAM0474E = "NSAM0474E";
    // END 2016/05/13 T.Tomita [QC#7794, ADD]

    // add start 2016/04/25 CSA Defect#6672
    /** [@] is earlier than [@]. */
    public static final String NSAM0284E = "NSAM0284E";

    /** [@] is later than [@]. */
    public static final String NSAM0285E = "NSAM0285E";
    // add end 2016/04/25 CSA Defect#6672

    // START 2016/06/10 [QC#9591, ADD]
    /** Registered machine can not change the serial number. */
    public static final String NSAM0521E = "NSAM0521E";
    // END   2016/06/10 [QC#9591, ADD]

    /* 08/10/2016 CSAI Y.Imazu Add QC#12496 START */
    /** Machine Master Status can be allowed to change [@] because this machine requires the installation for active order [@]. */
    public static final String NSAM0602E = "NSAM0602E";
    /* 08/10/2016 CSAI Y.Imazu Add QC#12496 END */
    
    // START 2020/4/24 K.Sakurai [QC#56672, ADD]
    /** Value is missing in the parameter's required field.[@] */
    public static final String NSAM0681E = "NSAM0681E";
    // END 2020/4/24 K.Sakurai [QC#56672, ADD]

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Machine Type */
    public static final String MACH_TYPE_STR = "Machine Type";

    /** MdseCode */
    public static final String MDSE_CD_STR = "MdseCode";

    /** Machine Status */
    public static final String MACH_STATUS_STR = "Machine Status";

    /** Bill To */
    public static final String BILL_TO_STR = "Bill To";

    /** Sell To */
    public static final String SELL_TO_STR = "Sell To";

    /** Ship To */
    public static final String SHIP_TO_STR = "Ship To";

    /** Location Name */
    public static final String LOC_NAME_STR = "Location Name";

    /** First Line Address */
    public static final String FIRST_ADDR_STR = "First Line Address";

    /** City */
    public static final String CITY_STR = "City";

    /** Zip */
    public static final String ZIP_STR = "Zip";

    /** Country */
    public static final String COUNTRY_STR = "Country";

    /** Business Type */
    public static final String BUSINESS_TYPE_STR = "Business Type";

    /** Auto Create */
    public static final String AUTO_CREATE_STR = "Auto Create";

    /** Serial# */
    public static final String SERIAL_NUM_STR = "Serial#";

    /** Service Org Cd */
    public static final String SERVICE_ORG_CD_STR = "Service Org Cd";

    /** Service Layer Num */
    public static final String SERVICE_ORG_LAYER_NUM = "Service Layer Num";

    /** Configuration# */
    public static final String CONFIGURATION_NUM_STR = "Configuration#";

    /** Installation Date */
    public static final String INS_DT_STR = "Installation Date";

    /** Removed Date */
    public static final String RMV_DT_STR = "Removed Date";

    /** New Serial# */
    public static final String NEW_SERIAL_STR = "New Serial#";

    /** State */
    public static final String STATE_STR = "State";

    /** Tech Cd */
    public static final String TECH_CD_STR = "Tech Cd";

    /** DS_SER_NUM */
    public static final String GET_SEQ_KEY_SER_NUM = "DS_SER_NUM";

    /** SELL_TO_CUST */
    public static final String SELL_TO_CUST = "SELL_TO_CUST";

    /** SELL_TO_CUST */
    public static final String USR_DLR_TP_CD = "USR_DLR_TP_CD";

    /** INSTL_FUNC_ID */
    public static final String INSTL_FUNC_ID = "NSAL0010T030";

    /** Default zip code */
    public static final String DEFAULT_ZIP_CODE = "00000";

    /** VAR_CHAR_KEY:DEF_DS_ORD_TP_CD */
    public static final String DEF_DS_ORD_TP_CD = "DEF_DS_ORD_TP_CD";

    /** VAR_CHAR_KEY:DEF_DS_ORD_RSN_CD */
    public static final String DEF_DS_ORD_RSN_CD = "DEF_DS_ORD_RSN_CD";


    //add for new service machine master.
    /** TAB_MachConfig */
    public static final String TAB_MACH_CONFIG = "MachConfig";

    /** TAB_Solution */
    public static final String TAB_SOLUTION = "Solution";

    /** TAB_Contacts */
    public static final String TAB_CONTACTS = "Contacts";

    /** TAB_AddlAttrb */
    public static final String TAB_ADDL_ATTRB = "AddlAttrb";

    /** TAB_CurrentLoc */
    public static final String TAB_CURRENT_LOC = "CurrentLoc";

    /** TAB_SlsOrdHist */
    public static final String TAB_SLS_ORD_HIST = "SlsOrdHist";

    /** TAB_IBHistory */
    public static final String TAB_IB_HISTORY = "IBHistory";

    /** TAB_ContrSmry */
    public static final String TAB_CONTR_SMRY = "ContrSmry";

    /** TAB_SvcCallHist */
    public static final String TAB_SVC_CALL_HIST = "SvcCallHist";

    /** TAB_InvoiceHist */
    public static final String TAB_INVOICE_HIST = "InvoiceHist";

    /** fixed Value:OWNER */
    public static final String DPLY_OWNER = "OWNER";

    /** fixed Value:BILL TO */
    public static final String DPLY_BILL_TO = "BILL TO";

    /** fixed Value:CURRENT LOCATION */
    public static final String DPLY_CUR_LOC = "CURRENT LOCATION";

    /** Search Limit */
    public static final int SEARCH_LIMIT_CNT = 200;

    /** Download Limit */
    public static final int DOWNLOAD_LIMIT_CNT = 10000;

    /** Fetch size for Download */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0010";

    /** Search Event */
    public static final String SEARCH = "Search";

    /** Open Machine Master popup event(config tab) */
    public static final String OPENWIN_MACHIDA = "OpenWin_MachIdA";
    // START 2015/11/11 T.Tomita [QC#569, ADD]
    /** OpenWin_LinkNewConfig */
    public static final String OPENWIN_LINKNEWCONFIG = "OpenWin_LinkNewConfig";
    // END 2015/11/11 T.Tomita [QC#569, ADD]
    // START 2016/02/04 T.Tomita [QC#3312, ADD]
    /** OpenWin_SerialNum */
    public static final String OPENWIN_SERIALNUM = "OpenWin_SerialNum";
    // END 2016/02/04 T.Tomita [QC#3312, ADD]
    // START 2016/02/22 T.Tomita [QC#969 ADD]
    /** MIN_DT_VAL */
    public static final String MIN_DT_VAL = "19000101";
    /** MAX_DT_VAL */
    public static final String MAX_DT_VAL = "29991231";
    // END 2016/02/22 T.Tomita [QC#969 MOD]
    // START 2016/02/26 T.Tomita [QC#942 ADD]
    /** LGSC_USER */
    public static final String FUNC_ID_LGSC = BUSINESS_ID + "T040";
    // END 2016/02/26 T.Tomita [QC#942 ADD]
    // Add Start 2017/10/16 QC#21563
    /** Customer Master User */
    public static final String FUNC_ID_CUST_MSTR = BUSINESS_ID + "T060";
    // Add End 2017/10/16 QC#21563

    // START 2016/06/10 [QC#9591, ADD]
    /** IWR_ENBL_FLG : Yes */
    public static final String IWR_ENBL_FLG_YES = "Yes";

    /** IWR_ENBL_FLG : No */
    public static final String IWR_ENBL_FLG_NO = "No";

    /** YYYYMMDD Start Index */
    public static final int YYYYMMDD_START_INDEX = 1;

    /** YYYYMMDD Length */
    public static final int YYYYMMDD_LENGTH = 8;
    // END   2016/06/10 [QC#9591, ADD]

    // START 2016/07/22 T.Tomita [QC#11161, ADD]
    /** @ is mismatched with @. */
    public static final String NSAM0433E = "NSAM0433E";

    // START 2016/07/29 T.Tomita [QC#12638, DEL]
//    /** POST_CD Length */
//    public static final int POST_CD_LENGTH = 5;
    // END 2016/07/29 T.Tomita [QC#12638, DEL]
    // END 2016/07/22 T.Tomita [QC#11161, ADD]

    // START 2016/09/06 T.Tomita [QC#13532, ADD]
    /** Constant Key : NSAL0010_MACH_STS_CUST_GRP */
    public static final String CONST_KEY_MACH_STS_CUST_GRP = "NSAL0010_MACH_STS_CUST_GRP";

    /** Constant Key : NSAL0010_MACH_STS_WH_GRP */
    public static final String CONST_KEY_MACH_STS_WH_GRP = "NSAL0010_MACH_STS_WH_GRP";

    // Add Start 2017/10/16 QC#21563
    /** Constant Key : NSAL0010_MACH_STS_CUST_MSTR */
    public static final String CONST_KEY_MACH_STS_CUST_MSTR = "NSAL0010_MACH_STS_CUST_MSTR";

    /** Constant Key : NSAL0010_USG_STS_CUST_MSTR */
    public static final String CONST_KEY_USG_STS_CUST_MSTR = "NSAL0010_USG_STS_CUST_MSTR";
    // Add End 2017/10/16 QC#21563

    /** Delimiter : , */
    public static final String DELIMITER = ",";
    // END 2016/09/06 T.Tomita [QC#13532, ADD]

    // START 2016/10/13 T.Tomita [QC#14734, ADD]
    /** When Machine Status is "@", cannot "@". */
    public static final String NSAM0612E = "NSAM0612E";
    // END 2016/10/13 T.Tomita [QC#14734, ADD]

// START 2016/11/14 N.Arai [QC#15829, MOD]
    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** column name:LOC_NM */
    public static final String LOC_NM = "LOC_NM";

    /** column name:ADDL_LOC_NM */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";

    /** column name:CTRY_CD */
    public static final String CTRY_CD = "CTRY_CD";

    /** column name:FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** column name:SCD_LINE_ADDR */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** column name:THIRD_LINE_ADDR */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** column name:FRTH_LINE_ADDR */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** column name:SVC_MACH_FL_NM */
    public static final String SVC_MACH_FL_NM = "SVC_MACH_FL_NM";

    /** column name:POST_CD */
    public static final String POST_CD = "POST_CD";

    /** column name:CTY_ADDR */
    public static final String CTY_ADDR = "CTY_ADDR";

    /** column name:ST_CD */
    public static final String ST_CD = "ST_CD";

    /** column name:CNTY_NM */
    public static final String CNTY_NM = "CNTY_NM";

    /** column name:PROV_NM */
    public static final String PROV_NM = "PROV_NM";
//END 2016/11/14 N.Arai [QC#15829, MOD]

    // START 2018/05/28 K.Kitachi [QC#19932, ADD]
    /** Because inconsistency with inventory will occur, it cannot be changed. */
    public static final String NSAM0722E = "NSAM0722E";
    // END 2018/05/28 K.Kitachi [QC#19932, ADD]

    // add start 2019/08/07 QC#52198
    /** VARCHAR CONST KEY: OWNR_RELN_CHECK_EXCL_TRX_TP_CD */
    public static final String OWNR_RELN_CHECK_EXCL_TRX_TP_CD = "OWNR_RELN_CHECK_EXCL_TRX_TP_CD";

    /** VARCHAR CONST KEY: OWNR_RELN_CHECK_EXCL_ACCT_NUM */
    public static final String OWNR_RELN_CHECK_EXCL_ACCT_NUM = "OWNR_RELN_CHECK_EXCL_ACCT_NUM";
    // add end 2019/08/07 QC#52198

    // START 2021/08/17 R. Cabral [QC#59010, ADD]
    /** Constant Value : Blank Text */
    public static final String BLANK_TEXT = "BLANK";

    /** Constant Value : All Text */
    public static final String ALL_TEXT = "*";

    /** Constant Value : TRX_TP_UPDATE_MAP */
    public static final String TRX_TP_UPDATE_MAP = "TRX_TP_UPDATE_MAP";

    /** Changes to this Transaction Type cannot be made. */
    public static final String NSZM1379E = "NSZM1379E";
    // END   2021/08/17 R. Cabral [QC#59010, ADD]

// START 2023/09/22 T.Kuroda [QC#61265, ADD]
    /** Constant Value : Loc Sts Blank */
    public static final String LOC_STS_BLANK = "";
// END   2022/09/22 T.Kuroda [QC#61265, ADD]
}
