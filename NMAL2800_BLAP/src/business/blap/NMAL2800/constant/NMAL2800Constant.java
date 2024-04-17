/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2800.constant;

/**
 *<pre>
 * NMAL2800Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/10/03   Hitachi         T.Mizuki        Update          QC#12418 
 * 2016/11/21   Fujitsu         R.Nakamura      Update          QC#16082 
 * 2017/06/15   Hitachi         E.Kameishi      Update          QC#18184
 * 2017/10/16   Fujitsu         M.Ohno          Update          QC#21786
 * 2017/10/20   Fujitsu         M.Ohno          Update          QC#21866
 * 2018/01/16   Fujitsu         Hd.Sugawara     Update          QC#23148
 * 2018/03/29   Fujitsu         R.Nakamura      Update          QC#23141
 *</pre>
 */
public class NMAL2800Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2800";

    /** Mode: Search */
    public static final String MODE_CD_SRCH = "1";

    /** Mode: CSV Update */
    public static final String MODE_CD_UPL = "2";

    /** Mode: Search */
    public static final String MODE_SRCH = "Search";

    /** Mode: CSV Update */
    // Mod Start 2016/11/21 QC#16082
//    public static final String MODE_UPL = "CSV Upload";
    public static final String MODE_UPL = "FileUpload";
    // Mod End 2016/11/21 QC#16082

    /** Length of RESRC_TRTY_ORG_NM */
    public static final int RESRC_TRTY_ORG_NM_LEN = 2000;

    /** Length of MATCH_ACCT_LOC_NUM */
    public static final int MATCH_ACCT_LOC_NUM_LEN = 4000;

    /** Date length */
    public static final int DT_LEN = 8;

    /** String length */
    public static final int STR_LEN_20 = 20;

    /** String length */
    public static final int STR_LEN_15 = 15;

    /** String length */
    public static final int STR_LEN_18 = 18;

    /** PROS_RVW_STS_CD: All */
    public static final String PROS_RVW_STS_CD_ALL = "0";

    /** PROS_RVW_STS_DESC_TXT: All */
    public static final String PROS_RVW_STS_DESC_TXT_ALL = "All";

    /** PROS_RVW_STS_CD: Blank */
    public static final String PROS_RVW_STS_CD_BLANK = "9";

    /** PROS_RVW_STS_DESC_TXT: Blank */
    public static final String PROS_RVW_STS_DESC_TXT_BLANK = " : Not Processed yet";

    /** Application Function ID */
    public static final String APP_FUNC_ID = "NMAL6700";

    /** NMAL2800_DEF_DS_ACCT_ITRL_FLG */
    public static final String DEF_DS_ACCT_ITRL_FLG = "NMAL2800_DEF_DS_ACCT_ITRL_FLG";

    /** NMAL2800_DEF_DS_ACCT_CLS_CD */
    public static final String DEF_DS_ACCT_CLS_CD = "NMAL2800_DEF_DS_ACCT_CLS_CD";

    // --------------------------------
    // Popup
    // --------------------------------
    /** Mode Name:Duplicate Location */
    public static final String MODE_NM_LOC = "Duplicate Location";

    /** Mode Name:Candidate Territory */
    public static final String MODE_NM_TRTY = "Candidate Territory";

    /** Function ID:NMAL6760 */
    public static final String FUNC_ID_LOC = "NMAL6760";

    /** Function ID:NMAL2630 */
    public static final String FUNC_ID_TRTY = "NMAL2630";

    /** Header:Location Number , Account Name */
    public static final String HDR_LOC = "Location Number , Account Name";

    /**
     * Header:Territory Name , Territory Description , Territory Group
     */
    public static final String HDR_TRTY = "Territory Name , Territory Description , Territory Group";

    /** Key Field: LOC_NUM */
    public static final String KEY_FIELD_LOC = "LOC_NUM";

    /** Key Field: ORG_CD */
    public static final String KEY_FIELD_TRTY = "ORG_CD";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** No search results were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** Data delete failure.(ReturnCode = [@]) */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** It failed to send an email. */
    public static final String NMAM0166E = "NMAM0166E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and cannot proceed.
     */
    public static final String NMAM8114E = "NMAM8114E";

    /** Prospect Number doesn't exist on Prospect Review table. */
    public static final String NMAM8554E = "NMAM8554E";

    /** Prospect Review status is already set either D or U or A. */
    public static final String NMAM8555E = "NMAM8555E";

    /** Territory[@] is not Active Territory on S21. */
    public static final String NMAM8572E = "NMAM8572E";

    /** Sales Rep is not assigned to Territory[@]. */
    public static final String NMAM8558W = "NMAM8558W";

    /** Please select [@]. */
    public static final String NMAM8461E = "NMAM8461E";

    /** Please select [@]. */
    public static final String NMAM8545E = "NMAM8545E";

    // 2017/10/16 QC#21786 Add Start
    /**
     * More than 2 county names found. Please select county name and try again.
     */
    public static final String NMAM8501E = "NMAM8501E";
    // 2017/10/16 QC#21786 Add End

    // Add Start 2018/03/29 QC#23141
    /** More than one Territory Candidates (Rule Base). Please input to "Assign To Territory". */
    public static final String NMAM8677E = "NMAM8677E";
    
    /** Split Value : Comma */
    public static final String SPLIT_VAL_COMMA = ",";
    // Add End 2018/03/29 QC#23141

    /** DS_ACCT_RVW_PROS */
    public static final String TBL_DS_ACCT_RVW_PROS = "DS_ACCT_RVW_PROS";

    /** Account */
    public static final String PROS_ACCT = "Prospect Account";

    /** Prospect# */
    public static final String PROS_NUM = "Prospect#";

    /** Location ID */
    public static final String LOC_NUM = "Location ID";

    /** Territory Name*/
    public static final String TRTY_NM = "Territory Name";

    /** territory which is assigned Sales Rep */
    public static final String TRTY_ASG_SLS_REP = "territory which is assigned Sales Rep";

    /** territory which is assigned Resource(s) */
    public static final String TRTY_ASG_RESRC = "territory which is assigned Resource(s)";

    // --------------------------------
    // Mail
    // --------------------------------
    /** Mail Template Id */
    public static final String MAIL_TEMPLETE_ID = "NMAL2800M001";

    /** psnNum */
    public static final String ML_PSN_NUM = "psnNum";

    /** psnNm */
    public static final String ML_PSN_NM = "psnNm";

    /** dsAcctNum */
    public static final String ML_DS_ACCT_NUM = "dsAcctNum";

    /** locNum */
    public static final String ML_LOC_NUM = "locNum";

    /** rvwProsNum */
    public static final String ML_RVW_PROS_NUM = "rvwProsNum";

    /** befDsAcctNm */
    public static final String ML_BEF_DS_ACCT_NM = "befDsAcctNm";

    /** befPsnNum */
    public static final String ML_BEF_PSN_NUM = "befPsnNum";

    /** emlAddr */
    public static final String ML_EML_ADDR = "emlAddr";

    /** xxScrItem61Txt */
    public static final String ML_SLS_REP_NM = "xxScrItem61Txt";

    /** row */
    public static final String ML_ROW = "row";

    /** Message Header */
    public static final String ML_HDR = "Current Owner Name    S21 Account Number  S21 Location ID  Prospect Number       Prospect Name       \n" //
            + "-----------------------------------------------------------------------------------------------------\n";

    // --------------------------------
    // File
    // --------------------------------
    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** File size */
    public static final int CSV_LIMIT_COUNT = 65536;

    // Del Start 2018/01/16 QC#23148
    ///** CSV File Name Download */
    //public static final String CSV_FILE_NAME_DOWNLOAD = "Prospect Review";
    // Del End 2018/01/16 QC#23148

    /** CSV File Name Upload */
    public static final String CSV_FILE_NAME_UPLOAD = "Prospect Review Upload";

    /** File extension */
    public static final String EXT = ".csv";

    /** CSV File */
    public static final String CSV_FILE = "CSV File";

    // START 2017/06/15 E.Kameishi [QC#18184,ADD]
    /** Timestamp From */
    public static final String TM_TS_FROM = "000000";

    /** Timestamp To */
    public static final String TM_TS_TO = "235959";
    // END 2017/06/15 E.Kameishi [QC#18184,ADD]
    // Del Start 2018/01/16 QC#23148
//    /** Download file header */
//    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {//
//    "Process Flag" //
//            , "Prospect Number" //
//            , "Prospect Name" //
//            , "Address1" //
//            , "Address2" //
//            , "Address3" //
//            , "Address4" //
//            , "City" //
//            , "State Name" //
//            , "Postal Code" //
//            , "County" //
//            , "Current Owner Name" //
//            , "Line of business type" //
//            , "Territory Candidates (Resource Base)" //
//            , "Territory Candidates (Rule Base)" //
//            , "Creation Date" //
//            , "Potential Duplicate Location ID" //
//            , "Assign To Territory" //
//            , "Assign To Sales Rep" //
//            , "Merge To Location ID" //
//            , "Merge To Prospect#" //
//            , "New Address1" //
//            , "New Address2" //
//            , "New Address3" //
//            , "New Address4" //
//            , "New City" //
//            , "New State Code" //
//            , "New Postal Code" //
//            , "New County" //
//            , "New Country" //
//            , "Phone" //
//            , "New Phone" //
//            , "Fax" //
//            // mod start 2016/10/04 CSA QC#12418
//            , "New Fax" //
//            // mod end 2016/10/04 CSA QC#12418
//            , "Last Update Date" //
//            , "DUNS Number" //
//            , "Sic Code" //
//            , "Ultimate DUNS Number" //
//            , "New DNB Name" //
//            , "New Annual Us Sales" //
//            , "New Duns Number" //
//            , "New Line Of Business Name" //
//            , "New Employees Total" //
//            , "New Sic Code" //
//            , "New Ult Duns Number" //
//            , "New GLN" //
//            , "New Parent DUNS Number" //
//            , "New HQ DUNS Number" //
//            , "New Company SIC" //
//            , "New Company SIC Description" //
//            , "S21 Account Number" //
//            , "S21 Location ID" //
//    };
    // Del End 2018/01/16 QC#23148

    /** Upload file header */
    public static final String[] CSV_UPLOAD_HEADER = new String[] {//
        // Mod Start 2018/01/16 QC#23148
    //"Process Flag" // 2017/10/20 QC#21866 add
            //, "Prospect Number" //
        "Prospect Number" //
            // Mod End 2018/01/16 QC#23148
            , "Party Name" //
            , "Bill To Street 1" //
            , "Bill To Street 2" //
            , "Bill To Street 3" //
            , "Bill To Street 4" //
            , "Bill To City" //
            , "Bill To State" //
            // Mod Start 2018/01/16 QC#23148
            //, "Bill To Postal Code" //
            //, "Bill To Country" //
            , "Bill To Country" //
            , "Bill To Postal Code" //
            // Mod End 2018/01/16 QC#23148
            , "Ship To Street 1" //
            , "Ship To Street 2" //
            , "Ship To Street 3" //
            , "Ship To Street 4" //
            , "Ship To City" //
            // Add Start 2018/01/16 QC#23148
            , "Ship To County" //
            // Add End 2018/01/16 QC#23148
            , "Ship To State" //
            // Del Start 2018/01/16 QC#23148
            //, "Ship To Postal Code" //
            //, "Ship To County" //
            // Del End 2018/01/16 QC#23148
            , "Ship To Country" //
            // Add Start 2018/01/16 QC#23148
            , "Ship To Postal Code" //
            // Add End 2018/01/16 QC#23148
            , "Duns Number" //
            // Del Start 2018/01/16 QC#23148
            //, "Employees Total" //
            // Del End 2018/01/16 QC#23148
            , "Sic Code" //
            , "Ult Duns Number" //
            , "Phone" //
            , "Fax" //
            // Add Start 2018/01/16 QC#23148
            , "Owner Employee Number" //
            , "Process Flag" //
            , "Source LOB" //
            // Add End 2018/01/16 QC#23148
            // 2017/10/20 QC#21866 mod start
//            , "Process Flag" //
            // Del Start 2018/01/16 QC#23148
            //, "Account ID Address Revue Process Flag" //
            // Del End 2018/01/16 QC#23148
            // 2017/10/20 QC#21866 mod end
            // Mod Start 2018/01/16 QC#23148
            //, "Referencing Party Site Number" //
            , "Merge To Location ID" //
            // Mod End 2018/01/16 QC#23148
            , "To Be Sfdc#" //
            // Mod Start 2018/01/16 QC#23148
            //, "Territory Name" //
            , "Assign To Territory" //
            // Mod End 2018/01/16 QC#23148
            , "Email Address" //
            // Del Start 2018/01/16 QC#23148
            //, "Owner Employee Number" //
            // Del End 2018/01/16 QC#23148
            , "Owner Name" //
            // Mod Start 2018/01/16 QC#23148
            //, "Merge To Location ID" //
            //, "Candidate Territory Names" //
            , "Territory Candidates (Resource Base)" //
            , "Territory Candidates (Rule Base)" //
            // Mod End 2018/01/16 QC#23148
            , "SF Creation Date" //
            , "SF Last Update Date" //
            , "TA Prospect Name" //
            , "TA Ship To Address 1" //
            , "TA Ship To Address 2" //
            , "TA Ship To Address 3" //
            , "TA Ship To Address 4" //
            , "TA Ship To City" //
            , "TA Ship To State" //
            , "TA Ship To Zip" //
            , "TA Ship To County" //
            , "TA Ship To Country" //
            // Del Start 2018/01/16 QC#23148
            //, "Assign To Territory" //
            // Del End 2018/01/16 QC#23148
            , "TA Phone" //
            , "TA Fax"
            , "TA DNB Name" //
            , "TA Updated Annual Us Sales" //
            , "TA Updated Duns Number" //
            , "TA Updated Line Of Business" //
            , "TA Updated Employees Total" //
            , "TA Updated Sic Code" //
            , "Ta Updated Ult Duns Number" //
            // Del Start 2018/01/16 QC#23148
            //, "TA GLN" //
            // Del End 2018/01/16 QC#23148
            , "TA Parent Duns Number" //
            , "TA HQ Duns Number" //
            , "TA Company SIC" //
            , "TA Company SIC Description" //
            // Del Start 2018/01/16 QC#23148
            //, "Potential Duplicate Location ID" //
            //, "Retain Fields" //
            // Del End 2018/01/16 QC#23148
    };
}
