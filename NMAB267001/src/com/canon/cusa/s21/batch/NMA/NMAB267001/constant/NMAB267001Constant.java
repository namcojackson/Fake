/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB267001.constant;

/**
 *<pre>
 * Location Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/18   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/28   SRAA            Y.Chen          Update          QC#2674
 * 2016/05/26   Fujitsu         M.Suzuki        Update          QC#9073
 * 2016/06/16   Hitachi         Y.Osawa         Update          QC#8156
 * 2016/10/31   Fujitsu         C.Yokoi         Update          QC#15035
 * 2018/07/02   Fujitsu         T.Noguchi       Update          QC#26423
 * 2018/07/18   Fujitsu         Hd.Sugawara     Update          QC#26407
 *</pre>
 */
public class NMAB267001Constant {

    /** BATCH_PROGRAM_ID(NMAB267001) */
    public static final String PROGRAM_ID = "NMAB267001";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** Multiple Primary Location can't be set up in One Account. */
    public static final String NMAM8403E = "NMAM8403E";

    /** Duplicate Location# in One Account. */
    public static final String NMAM8404E = "NMAM8404E";

    /** @ information should be inputed 30 characters. */
    public static final String NMAM8405E = "NMAM8405E";

    /** @ is not correct date format. Please input MM/DD/YYYY */
    public static final String NMAM8406E = "NMAM8406E";

    // Del Start 2018/07/18 QC#26407
    ///** Please input @. */
    //public static final String NMAM8368E = "NMAM8368E";
    // Del End 2018/07/18 QC#26407

    /** Account# @ is not exist in Master. */
    public static final String NMAM8411E = "NMAM8411E";

    /** Location# related @(Account#) is not exist in Master. */
    public static final String NMAM8412E = "NMAM8412E";

    /** Please input "Y" either in *Bill To(Y or N) or *Ship To(Y or N). */
    public static final String NMAM8413E = "NMAM8413E";

    /** @ is not registered Customer Master data. */
    public static final String NMAM8414E = "NMAM8414E";

    /** We found a different address from the one you entered. */
    public static final String NMZM0179W = "NMZM0179W";

    /** [@] is invalid. */
    public static final String NWBM0097E = "NWBM0097E";

    /** Since the record corresponding to the Upload CSV Request Table
     * (UPLD_CSV_RQST) does not exist, it will be abended.*/
    public static final String ZYPM0181E = "ZYPM0181E";

    /** Please confirm the error details. */
    public static final String NMAM8457E = "NMAM8457E";

    /** Please confirm the warning details. */
    public static final String NMAM8463E = "NMAM8463E";

    // 2016/10/31 CSA-QC#15035 Add Start
    /** Error occured in other request for location related to same account. To register, please confirm other request. */
    public static final String NMAM8566E = "NMAM8566E";
    // 2016/10/31 CSA-QC#15035 Add End

    // Add Start 2018/07/18 QC#26407
    /** Please set Y or N on "@" of the parameter. */
    public static final String NMAM8684E = "NMAM8684E";
    // Add End 2018/07/18 QC#26407

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    // START 2016/06/16 [QC#8156,ADD]
    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_UPD */
    public static final String RESULT_MSG_UPD = "%d record(s) successfully updated.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
    // END 2016/06/16 [QC#8156,ADD]

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NMAB2670";

    /** blank */
    public static final String BLANK = " ";

    /** Process Mode */
    public static final String PROCESS_MODE06 = "06";

    /** Process Mode */
    public static final String PROCESS_MODE04 = "04";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** NUM_8 */
    public static final int NUM_8 = 8;

    /** NUM_3 */
    public static final int NUM_3 = 3;

// QC#2674
//    /** SIZE_30 */
//    public static final int SIZE_30 = 30;

    /** DS_ACCT_NUM */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /** CTRY_CD */
    public static final String CTRY_CD = "CTRY_CD";

    /** FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** CTY_ADDR */
    public static final String CTY_ADDR = "CTY_ADDR";

    /** ST_CD */
    public static final String ST_CD = "ST_CD";

    /** POST_CD */
    public static final String POST_CD = "POST_CD";

    /** PTY_LOC_PK */
    public static final String PTY_LOC_PK = "PTY_LOC_PK";

    /** BILL_TO_AVAL_FLG */
    public static final String BILL_TO_AVAL_FLG = "BILL_TO_AVAL_FLG";

    /** SHIP_TO_AVAL_FLG */
    public static final String SHIP_TO_AVAL_FLG = "SHIP_TO_AVAL_FLG";

    /** LINE_BIZ_TP_CD */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** LOC_PRIMARY_FLG */
    public static final String LOC_PRIM_FLG = "LOC_PRIM_FLG";

    /** LOC_ACTV_FLG */
    public static final String LOC_ACTV_FLG = "LOC_ACTV_FLG";

    /** DS_ACCT_NM */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** SCD_LINE_ADDR */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** THIRD_LINE_ADDR */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** FRTH_LINE_ADDR */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** CNTY_NM */
    public static final String CNTY_NM = "CNTY_NM";

    /** PROV_NM */
    public static final String PROV_NM = "PROV_NM";

    /** ACCT_LOC_PK */
    public static final String ACCT_LOC_PK = "ACCT_LOC_PK";

    /** BILL_TO_EFF_FROM_DT_TXT */
    public static final String BILL_TO_EFF_FROM_DT_TXT = "BILL_TO_EFF_FROM_DT_TXT";

    /** BILL_TO_EFF_THRU_DT_TXT */
    public static final String BILL_TO_EFF_THRU_DT_TXT = "BILL_TO_EFF_THRU_DT_TXT";

    /** SHIP_TO_EFF_FROM_DT_TXT */
    public static final String SHIP_TO_EFF_FROM_DT_TXT = "SHIP_TO_EFF_FROM_DT_TXT";

    /** SHIP_TO_EFF_THRU_DT_TXT */
    public static final String SHIP_TO_EFF_THRU_DT_TXT = "SHIP_TO_EFF_THRU_DT_TXT";

    /** DS_LOC_NM */
    public static final String DS_LOC_NM = "DS_LOC_NM";

    /** LOC_NUM */
    public static final String LOC_NUM = "LOC_NUM";

    /** LOC_NM */
    public static final String LOC_NM = "LOC_NM";

    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";

    /** BTC_EFF_FROM_DT */
    public static final String BTC_EFF_FROM_DT = "BTC_EFF_FROM_DT";

    /** BTC_EFF_THRU_DT */
    public static final String BTC_EFF_THRU_DT = "BTC_EFF_THRU_DT";

    /** STC_EFF_FROM_DT */
    public static final String STC_EFF_FROM_DT = "STC_EFF_FROM_DT";

    /** STC_EFF_THRU_DT */
    public static final String STC_EFF_THRU_DT = "STC_EFF_THRU_DT";

    /** DS_ACCT_ITRL_FLG */
    public static final String DS_ACCT_ITRL_FLG = "DS_ACCT_ITRL_FLG";

    /** DS_ACCT_CLS_CD */
    public static final String DS_ACCT_CLS_CD = "DS_ACCT_CLS_CD";

    /** COA_CH_CD */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** COA_AFFL_CD */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** DS_ACCT_DLR_CD */
    public static final String DS_ACCT_DLR_CD = "DS_ACCT_DLR_CD";

    /** DS_ACCT_LEGAL_NM */
    public static final String DS_ACCT_LEGAL_NM = "DS_ACCT_LEGAL_NM";

    /** DBA_NM */
    public static final String DBA_NM = "DBA_NM";

    /** RGTN_STS_CD */
    public static final String RGTN_STS_CD = "RGTN_STS_CD";

    /** DS_ACCT_URL */
    public static final String DS_ACCT_URL = "DS_ACCT_URL";

    /** UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "UPLD_CSV_RQST_PK";

    /** UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** UPLD_CSV_RQST_CMNT_TXT */
    public static final String UPLD_CSV_RQST_CMNT_TXT = "UPLD_CSV_RQST_CMNT_TXT";

    /** COUNT */
    public static final String COUNT = "COUNT";

    /** ADDL_LOC_NM */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";

    /** GLN_NUM */
    public static final String GLN_NUM = "GLN_NUM";

    /** TEL_NUM */
    public static final String TEL_NUM = "TEL_NUM";

    /** FAX_NUM */
    public static final String FAX_NUM = "FAX_NUM";

    /** RELN_BILL_TO_CUST_CD */
    public static final String RELN_BILL_TO_CUST_CD = "RELN_BILL_TO_CUST_CD";

    /** PRIM_USG_FLG */
    public static final String PRIM_USG_FLG = "PRIM_USG_FLG";

    /** BIG_DEAL_NUM */
    public static final String BIG_DEAL_NUM = "BIG_DEAL_NUM";

    /** DPL_STS_CD */
    public static final String DPL_STS_CD = "DPL_STS_CD";

    /** Bill To Start Date */
    public static final String LABEL_BILL_TO_FROM_DT = "Bill To Start Date";

    /** Ship To Start Date */
    public static final String LABEL_SHIP_TO_FROM_DT = "Ship To Start Date";

    /** Related Bill To Code */
    public static final String LABEL_RELN_BILL_TO = "Related Bill To Code";

    /** Related Account */
    public static final String LABEL_ACCT_RELN= "Related Account";

    // Add Start 2018/07/18 QC#26407
    /** Bill To Available Flag Parameter Name */
    public static final String LABEL_BILL_TO_AVAL_FLG = "Bill To(Y or N)";

    /** Ship To Available Flag Parameter Name */
    public static final String LABEL_SHIP_TO_AVAL_FLG = "Ship To(Y or N)";

    /** Location Primary Flag Parameter Name */
    public static final String LABEL_LOC_PRIM_FLG = "Primary(Y or N)";

    /** Location Active Flag Parameter Name */
    public static final String LABEL_LOC_ACTV_FLG = "Active(Y or N)";
    // Add End 2018/07/18 QC#26407

    /** GLOBAL_COMPANY_CODE */
    public static final String GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MSG_UPLD_CSV_ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** PARAM_GLBL_CMPY_CD */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** PARAM_UPLD_CSV_RQST_PK */
    public static final String PARAM_UPLD_CSV_RQST_PK = "upldCsvRqstPk";

    /** PARAM_DS_ACCT_NUM */
    public static final String PARAM_DS_ACCT_NUM = "dsAcctNum";

    /** PARAM_LOC_NUM */
    public static final String PARAM_LOC_NUM = "locNum";

    /** PARAM_FRTH_LINE_ADDR */
    public static final String PARAM_FRTH_LINE_ADDR = "firstLineAddr";

    /** PARAM_SCD_LINE_ADDR */
    public static final String PARAM_SCD_LINE_ADDR = "scdLineAddr";

    /** PARAM_CTY_ADDR */
    public static final String PARAM_CTY_ADDR = "ctyAddr";

    /** PARAM_ST_CD */
    public static final String PARAM_ST_CD = "stCd";

    /** PARAM_POST_CD */
    public static final String PARAM_POST_CD = "postCd";

    /** PARAM_CNTY_NM */
    public static final String PARAM_CNTY_NM = "cntyNm";

    /** PARAM_LOC_PRIM_FLG */
    public static final String PARAM_LOC_PRIM_FLG = "locPrimFlg";

    /** GET_WRK */
    public static final String GET_WRK = "getLocInfoWrk";

    /** CON_WRK */
    public static final String CON_WRK = "getCountLocInfoWrk";

    /** GET_ACC */
    public static final String GET_ACC = "getAccount";

    /** GET_LOC */
    public static final String GET_LOC = "getLocation";

    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    //-- Add Start QC#9073 2016/05/26 --
    /** MAX_DATE */
    public static final String MAX_DATE = "maxDate";

    /** MAX_DATE_VALUE */
    public static final String MAX_DATE_VALUE = "99991231";
    //----------------------------------

    // 2018/07/02 QC#26423 Add Start
    /** Single FETCH_SIZE */
    public static final int SINGLE_FETCH_SIZE = 1;
    // 2018/07/02 QC#26423 Add End
}
