/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0640.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/10/2016   CITS         Makoto Okigami     Create          N/A
 * 08/03/2018   CITS            Y.Iwasaki       Update          QC#26948
 * 10/17/2018   CITS            M.Naito         Update          QC#28770
 * 10/26/2018   CITS            T.Wada          Update          QC#26948-2
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 * 05/08/2019   CITS            T.Tokutomi      Update          QC#50029
 * 01/07/2020   Fujitsu         T.Ogura         Update          QC#50672
 * 03/06/2020   CITS            K.Ogino         Update          QC#56164
 * 01/12/2021   CITS            H.Dimay         Update          QC#58201
 * 01/13/2021   CITS            H.Dimay         Update          QC#58201-1
 * 05/15/2023   Hitachi         TZ.Win          Update          QC#61427
 *</pre>
 */
public class NLCL0640Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL0640";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL0640Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NLCL0640_INIT = "NLCL0640_INIT";

    /**
     * Event Name : Complete Count
     */
    public static final String EVENT_NM_NLCL0640_SEARCH_MDSE_NAME = "NLCL0640Scrn00_Search_MdseName";

    /**
     * Event Name : Add counted item
     */
    public static final String EVENT_NM_NLCL0640_ADD_COUNT_ITEM = "NLCL0640Scrn00_Add_CountItem";

    /**
     * Event Name : Import counted items
     */
    public static final String EVENT_NM_NLCL0640_IMPORT_COUNT_ITEMS = "NLCL0640Scrn00_Import_CountItems";

    /**
     * Event Name : TemplateFileForUpload
     */
    public static final String EVENT_NM_NLCL0640_TEMPLATE_FILE_FOR_UPLOAD = "NLCL0640Scrn00_TemplateFileForUpload";

    /**
     * Event Name : NLCL0650
     */
    public static final String EVENT_NM_NLCL0640_NLCL0650 = "NLCL0640_NLCL0650";

    /**
     * Event Name : Submit
     */
    public static final String EVENT_NM_NLCL0640_CMN_SUBMIT = "NLCL0640Scrn00_CMN_Submit";

    /**
     * Event Name : Reset
     */
    public static final String EVENT_NM_NLCL0640_CMN_RESET = "NLCL0640Scrn00_CMN_Reset";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_PAGE_PREV = "NLCL0640Scrn00_PagePrev";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_PAGE_NEXT = "NLCL0640Scrn00_PageNext";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: PHYS_INVTY_NUM
     */
    public static final String DB_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /**
     * DB Param Name: TECH_TOC_CD
     */
    public static final String DB_PARAM_TECH_TOC_CD = "techTocCd";

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * DB Param Name: RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    /**
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name: MDSE_CD
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * DB Param Name: SER_NUM
     */
    public static final String DB_PARAM_SER_NUM = "serNum";

    /**
     * DB Param Name: TECH_LOC_CD
     */
    public static final String DB_PARAM_TECH_LOC_CD = "techLocCd";

    /**
     * DB Param Name: PHYS_INVTY_CTRL_PK
     */
    public static final String DB_PARAM_PHYS_INVTY_CTRL_PK = "physInvtyCtrlPk";

    /**
     * DB Param Name: ADJ_VAR_FLG
     */
    public static final String DB_PARAM_ADJ_VAR_FLG = "adjVarFlg";

    /**
     * DB Param Name: LOC_STS_CD
     */
    public static final String DB_PARAM_LOC_STS_CD = "locStsCd";

    /**
     * DB Param Name: STK_STS_CD
     */
    public static final String DB_PARAM_STK_STS_CD = "stkStsCd";

    /**
     * DB Param Name: INVTY_QTY
     */
    public static final String DB_PARAM_INVTY_QTY = "invtyQty";

    /**
     * DB Param Name: PHYS_INVTY_CTRL_NM
     */
    public static final String DB_PARAM_PHYS_INVTY_CTRL_NM = "physInvtyCtrlNm";

    /**
     * DB Param Name: PHYS_INVTY_DT
     */
    public static final String DB_PARAM_PHYS_INVTY_DT = "physInvtyDt";

    /**
     * DB Param Name: PHYS_INVTY_STS_CD
     */
    public static final String DB_PARAM_PHYS_INVTY_STS_CD = "physInvtyStsCd";

    /**
     * DB Param Name: ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rownum";

    /**
     * DB Param Name: LOC_TP_CD
     */
    public static final String DB_PARAM_LOC_TP_CD = "locTpCd";

    /**
     * DB Param Name: RCV_SER_TAKE_FLG
     */
    public static final String DB_PARAM_RCV_SER_TAKE_FLG = "rcvSerTakeFlg";

    /**
     * DB Param Name: SVC_MACH_MSTR_STS_CD
     */
    public static final String DB_PARAM_SVC_MACH_MSTR_STS_CD = "svcMachMstrStsCd";

    // =================================================
    // DB Param Value
    // =================================================
    /**
     * DB Param Value: ROWNUM 1
     */
    public static final BigDecimal DB_PARAM_VALUE_ROWNUM_1 = BigDecimal.ONE;

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: PHYS_INVTY_CNT_STS_CD
     */
    public static final String DB_COLUMN_PHYS_INVTY_CNT_STS_CD = "PHYS_INVTY_CNT_STS_CD";

    /**
     * DB Column Name: PHYS_INVTY_CTRL_PK
     */
    public static final String DB_COLUMN_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";

    /**
     * DB Column Name: TECH_LOC_CD
     */
    public static final String DB_COLUMN_TECH_LOC_CD = "TECH_LOC_CD";

    /**
     * DB Column Name: LOC_NM
     */
    public static final String DB_COLUMN_LOC_NM = "LOC_NM";

    /**
     * DB Column Name: RTL_WH_CD
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name: RTL_SWH_CD
     */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * DB Column Name: CNT_SERIAL
     */
    public static final String DB_COLUMN_CNT_SERIAL = "CNT_SERIAL";

    /**
     * DB Column Name: CNT_ITEM
     */
    public static final String DB_COLUMN_CNT_ITEM = "CNT_ITEM";

    /**
     * DB Column Name: MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name: PHYS_INVTY_CNT_HDR_PK
     */
    public static final String DB_COLUMN_PHYS_INVTY_CNT_HDR_PK = "PHYS_INVTY_CNT_HDR_PK";

    /**
     * DB Column Name: SUM_CNT_INP_QTY
     */
    public static final String DB_COLUMN_SUM_CNT_INP_QTY = "SUM_CNT_INP_QTY";

    /**
     * DB Column Name: SUM_INVTY_QTY
     */
    public static final String DB_COLUMN_SUM_INVTY_QTY = "SUM_INVTY_QTY";

    /**
     * DB Column Name: MDSE_CD
     */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /**
     * DB Column Name: INVTY_QTY
     */
    public static final String DB_COLUMN_INVTY_QTY = "INVTY_QTY";

    /**
     * DB Column Name: INVTY_LOC_CD
     */
    public static final String DB_COLUMN_INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     * DB Column Name: STK_STS_CD
     */
    public static final String DB_COLUMN_STK_STS_CD = "STK_STS_CD";

    /**
     * DB Column Name: CNT_ADJ_VAR
     */
    public static final String DB_COLUMN_CNT_ADJ_VAR = "CNT_ADJ_VAR";

    /**
     * DB Column Name: WH_CD
     */
    public static final String DB_COLUMN_WH_CD = "WH_CD";

    /**
     * DB Column Name: PHYS_INVTY_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PHYS_INVTY_STS_DESC_TXT = "PHYS_INVTY_STS_DESC_TXT";

    /**
     * DB Column Name: RCV_SER_TAKE_FLG
     */
    public static final String DB_COLUMN_RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /**
     * DB Column Name: FROM_SER_NUM
     */
    public static final String DB_COLUMN_FROM_SER_NUM = "FROM_SER_NUM";

    /**
     * DB Column Name: THRU_SER_NUM
     */
    public static final String DB_COLUMN_THRU_SER_NUM = "THRU_SER_NUM";

    /**
     * DB Column Name: PHYS_INVTY_CNT_DTL_PK
     */
    public static final String DB_COLUMN_PHYS_INVTY_CNT_DTL_PK = "PHYS_INVTY_CNT_DTL_PK";

    /**
     * DB Column Name: QTY
     */
    public static final String DB_COLUMN_QTY = "QTY";

    /**
     * DB Column Name: CUR_LOC_NUM
     */
    public static final String DB_COLUMN_CUR_LOC_NUM = "CUR_LOC_NUM";

    /**
     * DB Column Name: SER_NUM
     */
    public static final String DB_COLUMN_SER_NUM = "SER_NUM";

    /**
     * DB Column Name: SUM_FIRST_CNT_INP_QTY
     */
    public static final String DB_COLUMN_SUM_FIRST_CNT_INP_QTY = "SUM_FIRST_CNT_INP_QTY";

    /**
     * DB Column Name: MAX_FIRST_CNT_INP_TS
     */
    public static final String DB_COLUMN_MAX_FIRST_CNT_INP_TS = "MAX_FIRST_CNT_INP_TS";

    /**
     * DB Column Name: SUM_SCD_CNT_INP_QTY
     */
    public static final String DB_COLUMN_SUM_SCD_CNT_INP_QTY = "SUM_SCD_CNT_INP_QTY";

    /**
     * DB Column Name: MAX_SCD_CNT_INP_TS
     */
    public static final String DB_COLUMN_MAX_SCD_CNT_INP_TS = "MAX_SCD_CNT_INP_TS";

    /**
     * DB Column Name: SUM_INVTY_AVAL_QTY
     */
    public static final String DB_COLUMN_SUM_INVTY_AVAL_QTY = "SUM_INVTY_AVAL_QTY";

    /**
     * DB Column Name: MAX_INVTY_REGD_TS
     */
    public static final String DB_COLUMN_MAX_INVTY_REGD_TS = "MAX_INVTY_REGD_TS";

    /**
     * DB Column Name: SUM_ADJ_VAR_QTY
     */
    public static final String DB_COLUMN_SUM_ADJ_VAR_QTY = "SUM_ADJ_VAR_QTY";

    /**
     * DB Column Name: CNT_ADJ_VAR_FLG_Y
     */
    public static final String DB_COLUMN_CNT_ADJ_VAR_FLG_Y = "CNT_ADJ_VAR_FLG_Y";

    /**
     * DB Column Name: SUM_ADJ_VAR_AMT
     */
    public static final String DB_COLUMN_SUM_ADJ_VAR_AMT = "SUM_ADJ_VAR_AMT";

    /**
     * DB Column Name: LG_SER_NUM
     */
    public static final String DB_COLUMN_LG_SER_NUM = "LG_SER_NUM";

    // =================================================
    // API Param
    // =================================================
    /**
     * API Param: Timestamp Format
     */
    public static final String API_PARAM_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * API Param: NLZC061001
     */
    public static final String CREATE_MODE = "01";

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZYEM0004E: The Upload File is empty or only has a header line, therefore it could not be processed. Please confirm the content of the  Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * ZZM9000E: [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * ZZM9014E: Upload file specified for [@] field does not exist or is empty.
     */
    public static final String ZZM9014E = "ZZM9014E";

    /**
     * NLCM0025E: The number of Detail rows reached to the maximum.  No more Details can be registered.
     */
    public static final String NLCM0025E = "NLCM0025E";

    /**
     * NLCM0145E: You cannot execute this function. Please contact an operator. Status:＠
     */
    public static final String NLCM0145E = "NLCM0145E";

    /**
     * NLCM0146E: The item @ and Ser @ is already registered in Tech PI.
     */
    public static final String NLCM0146E = "NLCM0146E";

    /**
     * NLCM0147E: The quantity @ for @ is not invalid.
     */
    public static final String NLCM0147E = "NLCM0147E";

    /**
     * NLCM0148E: The item @ does not exist in Master.
     */
    public static final String NLCM0148E = "NLCM0148E";

    /**
     * NLCM0149W: The PI is waiting for a manager approval. Please contact your manager.
     */
    public static final String NLCM0149W = "NLCM0149W";

    /**
     * NLCM0180E: The item[@] is already registered in Retal SWH.
     */
    public static final String NLCM0180E = "NLCM0180E";

    /**
     * NLCM0181E: The Serial# is mandatory for a serialized item[@].
     */
    public static final String NLCM0181E = "NLCM0181E";

    /**
     * NLCM0224E: Specified value is invalid. Field name: [@] Input value: [@].
     */
    public static final String NLCM0224E = "NLCM0224E";

    /**
     * NLZM2292E: The Serial # is incorrect. It is set out of the range.
     */
    public static final String NLZM2292E = "NLZM2292E";

    /**
     * NMAM0176E: It failed to register [@].
     */
    public static final String NMAM0176E = "NMAM0176E";

    /**
     * NMAM0177E: It failed to update [@].
     */
    public static final String NMAM0177E = "NMAM0177E";

    // QC#26948-2 Add
    /**
     * NLCM0227E: The discrepancy exceeds the limit value (100 items). Please check the contents of the upload file again
     */
    // START 2023/05/16 TZ.Win [QC#61427, DEL]
//    public static final String NLCM0227E = "NLCM0227E";
    // END 2023/05/16 TZ.Win [QC#61427, DEL]

    // START 2023/05/15 TZ.Win [QC#61427, ADD]
    /**
     * NLCM0227W: The discrepancy exceeds the limit value (200 items). If OK, please click the "Submit" button again.
     */
    public static final String NLCM0245W = "NLCM0245W";
    // END 2023/05/15 TZ.Win [QC#61427, ADD]

    // QC#50029 Add.
    /**
     * Failed to update "PHYS_INVTY_CTRL".
     */
    public static final String NLCM0163E = "NLCM0163E";

    // QC#50029 Add.
    /**
     * Failed to register PHYS_INVTY_ADJ.
     */
    public static final String NLZM2453E = "NLZM2453E";

    // QC#50029 Add.
    /**
     * Failed to update PHYS_INVTY_CNT_HDR.
     */
    public static final String NLZM2454E = "NLZM2454E";

    /**
     * NLCM0239E: The uploaded file has a row of incorrect data. (@) Line Number: [@]
     */
    public static final String NLCM0239E = "NLCM0239E";    // 01/07/2020 T.Ogura [QC#50672,ADD]

    // =================================================
    // Message Value
    // =================================================
    /**
     * PHYS_INVTY_CTRL
     */
    public static final String MSG_VALUE_PHYS_INVTY_CTRL = "Physical Inventory Control";

    /**
     * PHYS_INVTY_CNT_HDR
     */
    public static final String MSG_VALUE_PHYS_INVTY_CNT_HDR = "PI Count Header";

    /**
     * PHYS_INVTY_CNT_DTL
     */
    public static final String MSG_VALUE_PHYS_INVTY_CNT_DTL = "PI Count Detail";

    // =================================================
    // Other Value
    // =================================================
    /** PAGING_SIZE */
    // START 2018/12/03 T.Ogura [QC#27978,MOD]
//    public static final int CONST_PAGE_SIZE = 20;
    public static final int CONST_PAGE_SIZE = 30;
    // END   2018/12/03 T.Ogura [QC#27978,MOD]

    /** */
    public static final String TEMP_FILE_NAME = "TechPITemp";

    /** */
    public static final String TEMP_FILE_EXT = ".csv";

    /** */
    public static final String[] TEMPLATE_CSV_HEADER = {"Tech/Cust WH Code", "Sub WH Code", "Item Number", "Quantity", "Serial" };

    // START 2018/10/17 M.Naito [QC#28770, ADD]
    /** */
    public static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";
    // END 2018/10/17 M.Naito [QC#28770, ADD]

    // QC#26948-2 Add Start。Mod QC#56164
    // START 2021/01/12 [QC#58201, MOD]
    //public static final int MAX_UPLOAD_COUNT = 999;
    /** Maximum Detail entries */
    public static final int MAX_UPLOAD_COUNT = 2000;
    // END 2021/01/12 [QC#58201, MOD]
    // START 2021/01/13 [QC#58201-1, MOD]
    //public static final int MAX_VARIANCE_ITEM_COUNT = 100;
    /** Maximum Variance Count of data entries */
    public static final int MAX_VARIANCE_ITEM_COUNT = 200;
    // END 2021/01/13 [QC#58201-1, MOD]
    // QC#26948-2 Add End

    // QC#50029 Add.
    /** Column: MAX_NM_LENGHT */
    public static final int MAX_NM_LENGTH = 64;
}
