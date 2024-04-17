/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB268001.constant;

/**
 *<pre>
 * Contact Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/19   Fujitsu         C.Yokoi         Update          CSA-QC#7183
 * 2017/08/30   Fujitsu         H.Sugawara      Update          QC#20791
 * 2017/10/03   Fujitsu         M.Ohno          Update          QC#20972
 * 2018/03/06   Fujitsu         K.Ishizuka      Update          QC#23351
 * 2018/07/18   Fujitsu         Hd.Sugawara     Update          QC#26407
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 *</pre>
 */
public class NMAB268001Constant {

    /** Error Msg */
    public static final String NMAM8228E = "NMAM8228E";

    /** Error Msg */
    public static final String NMAM8416E = "NMAM8416E";

    /** Error Msg */
    public static final String NMAM8417E = "NMAM8417E";

    /** Error Msg */
    public static final String NMAM8418E = "NMAM8418E";

    /** Error Msg */
    public static final String NMAM8411E = "NMAM8411E";

    /** Error Msg */
    public static final String NMAM8419E = "NMAM8419E";

    // Del Start 2017/08/30 QC#20791
    ///** Error Msg */
    //public static final String NMAM8420E = "NMAM8420E";
    // Del End 2017/08/30 QC#20791

    /** Error Msg */
    public static final String NMAM8421E = "NMAM8421E";

    // Del Start 2017/08/30 QC#20791
    ///** Error Msg */
    //public static final String NMAM8431E = "NMAM8431E";
    // Del End 2017/08/30 QC#20791

    /** Error Msg */
    public static final String NMAM8368E = "NMAM8368E";

    /** Error Msg */
    public static final String NWBM0097E = "NWBM0097E";

    /** Error Msg */
    public static final String NMAM8414E = "NMAM8414E";

    /** Error Msg */
    public static final String NMAM8452E = "NMAM8452E";

    /** Error Msg */
    public static final String NMAM8453E = "NMAM8453E";

    /** Error Msg */
    public static final String NMAM8462E = "NMAM8462E";

    /** Please confirm the error details. */
    public static final String NMAM8457E = "NMAM8457E";

    /** Please confirm the warning details. */
    public static final String NMAM8463E = "NMAM8463E";

    /** Error Msg */
    public static final String ZYPM0181E = "ZYPM0181E";

    // 2016/06/30 CSA-QC#10918 Add Start
    /** Please specify [@]. */
    public static final String NMAM0041E = "NMAM0041E";
    // 2016/06/30 CSA-QC#10918 Add End

    // 2017/10/03 CSA-QC#20769 Add Start
    /** Email format is incorrect. */
    public static final String NMAM8347E = "NMAM8347E";

    /**
     * Phone number format is incorrect. Minimum length is 7digit,
     * Maximum length is 20 digit.
     */
    public static final String NMAM8348E = "NMAM8348E";

    // Del Start 2018/12/14 QC#24022
    ///** Extention length has been exceeded. Maximum length is 4digit. */
    //public static final String NMAM8349E = "NMAM8349E";
    // Del End 2018/12/14 QC#24022

    /** Primary Contact Typ doesn't exist in Contact Point.  */
    public static final String NMAM8671E = "NMAM8671E";
    // 2017/10/03 CSA-QC#20769 Add End

    // Add Start 2018/07/18 QC#26407
    /** Please set Y or N on "@" of the parameter. */
    public static final String NMAM8684E = "NMAM8684E";
    // Add End 2018/07/18 QC#26407

    // Add Start 2018/12/14 QC#24022
    /** Extention length has been exceeded. Maximum length is 10 digit. */
    public static final String NMAM8698E = "NMAM8698E";
    // Add End 2018/12/14 QC#24022

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

    /** HitDate */
    public static final String HIT_DATE = "99991231";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** NUM_3 */
    public static final int NUM_3 = 3;

    /** NUM_4 */
    public static final int NUM_4 = 4;

    /** Process Mode */
    public static final String PROCESS_MODE01 = "01";

    /** GLOBAL_COMPANY_CODE */
    public static final String GLOBAL_COMPANY_CODE = "Global Company Code";

    /** PARAM_GLBL_CMPY_CD */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** PARAM_UPLD_CSV_RQST_PK */
    public static final String PARAM_UPLD_CSV_RQST_PK = "upldCsvRqstPk";

    /** MSG_UPLD_CSV_ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** PARAM_DS_ACCT_NUM */
    public static final String PARAM_DS_ACCT_NUM = "dsAcctNum";

    /** PARAM_LOC_NUM */
    public static final String PARAM_LOC_NUM = "locNum";

    /** PARAM_LOC_PRIM_FLG */
    public static final String PARAM_CTAC_PRIM_FLG = "ctacPrimFlg";

    /** PARAM_RGTN_STS_CD */
    public static final String PARAM_RGTN_STS_CD = "rgtnStsCd";

    /** PARAM_PROC_DATE */
    public static final String PARAM_PROC_DATE = "procDate";

    // Del Start 2017/08/30 QC#20791
    ///** PARAM_FIRST_NM */
    //public static final String PARAM_FIRST_NM = "ctacPsnFirstNm";
    // Del End 2017/08/30 QC#20791

    // Del Start 2017/08/30 QC#20791
    ///** PARAM_LAST_NM */
    //public static final String PARAM_LAST_NM = "ctacPsnLastNm";
    // Del End 2017/08/30 QC#20791

    /** PARAM_LAST_NM */
    public static final String PARAM_SER_NUM = "serNum";

    /** PARAM_DATE */
    public static final String PARAM_DATE = "hitDate";

    /** PARAM_PROS_FLG */
    public static final String PARAM_PROS_FLG = "prospectFlg";

    /** PARAM_FLG_ON_Y */
    public static final String PARAM_FLG_ON_Y = "flgOnY";

    // 2017/10/03 CSA-QC#20769 Add Start
    /** PARAM_CTAC_TP_CD */
    public static final String PARAM_CTAC_TP_CD = "ctacTpCd";
    // 2017/10/03 CSA-QC#20769 Add End

    /** PARAM_SVC_MACHN_STS_W4I */
    public static final String PARAM_SVC_MACHN_STS_W4I = "svcMachMstrSts_W4I";

    /** PARAM_SVC_MACHN_STS_INSTL */
    public static final String PARAM_SVC_MACHN_STS_INSTL = "svcMachMstrSts_INSTL";

    /** PARAM_SVC_MACHN_STS_W4R */
    public static final String PARAM_SVC_MACHN_STS_W4R = "svcMachMstrSts_W4R";

    // Add Start 2018/07/18 QC#26407
    /** PARAM_DS_CTAC_PSN_SALT_DESC_TXT */
    public static final String PARAM_DS_CTAC_PSN_SALT_DESC_TXT = "dsCtacPsnSaltDescTxt";

    /** PARAM_SVC_CTAC_TP_DESC_TXT */
    public static final String PARAM_SVC_CTAC_TP_DESC_TXT = "svcCtacTpDescTxt";
    // Add End 2018/07/18 QC#26407

    /** DS_ACCT_NUM */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /** DS_ACCT_NM */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** LOC_NUM */
    public static final String LOC_NUM = "LOC_NUM";

    /** CTAC_PRIM_FLG */
    public static final String CTAC_PRIM_FLG = "CTAC_PRIM_FLG";

    /** LAST_NM */
    public static final String LAST_NM = "LAST_NM";

    /** FIRST_NM */
    public static final String FIRST_NM = "FIRST_NM";

    // 2016/04/19 CSA-QC#7183 Add Start
    /** MSG_DESC_DS_CTAC_PSN_SALT */
    public static final String MSG_DESC_DS_CTAC_PSN_SALT = "Salutation";

    /** MSG_DESC_DS_CTAC_PSN_DEPT */
    public static final String MSG_DESC_DS_CTAC_PSN_DEPT = "Job Function/Dept";

    /** MSG_DESC_CTAC_TP */
    public static final String MSG_DESC_CTAC_TP = "Relationship to CSA";

    /** MSG_DESC_DS_CTAC_PSN_TTL */
    public static final String MSG_DESC_DS_CTAC_PSN_TTL = "Title";

    /** MSG_DESC_DS_CTAC_PNT_TP */
    public static final String MSG_DESC_DS_CTAC_PNT_TP = "Prefered Contact Type";

    /** MSG_DESC_SVC_CTAC_TP */
    public static final String MSG_DESC_SVC_CTAC_TP = "Service Contact Type";

    /** MSG_DESC_CTAC_PRIM_FLG */
    public static final String MSG_DESC_CTAC_PRIM_FLG = "Contact Primary Flag";

    /** MSG_DESC_LAST_NM */
    public static final String MSG_DESC_LAST_NM = "Last Name";

    /** MSG_DESC_FIRST_NM */
    public static final String MSG_DESC_FIRST_NM = "First Name";

    // 2016/04/19 CSA-QC#7183 Add End

    /** DS_CTAC_PSN_SALT_DESC_TXT */
    public static final String DS_CTAC_PSN_SALT_DESC_TXT = "DS_CTAC_PSN_SALT_DESC_TXT";

    // 2018/03/06 S21_NA#23351 Mod Start
//    /** DS_CTAC_PSN_DEPT_DESC_TXT */
//    public static final String DS_CTAC_PSN_DEPT_DESC_TXT = "DS_CTAC_PSN_DEPT_DESC_TXT";
//
//    /** CTAC_TP_DESC_TXT */
//    public static final String CTAC_TP_DESC_TXT = "CTAC_TP_DESC_TXT";
//
//    /** DS_CTAC_PSN_TTL_DESC_TXT */
//    public static final String DS_CTAC_PSN_TTL_DESC_TXT = "DS_CTAC_PSN_TTL_DESC_TXT";
//
//    /** DS_CTAC_PNT_TP_DESC_TXT */
//    public static final String DS_CTAC_PNT_TP_DESC_TXT = "DS_CTAC_PNT_TP_DESC_TXT";
    
    /** DS_CTAC_PSN_DEPT_CD */
    public static final String DS_CTAC_PSN_DEPT_CD = "DS_CTAC_PSN_DEPT_CD";

    /** CTAC_TP_CD */
    public static final String CTAC_TP_CD = "CTAC_TP_CD";

    /** DS_CTAC_PSN_TTL_CD */
    public static final String DS_CTAC_PSN_TTL_CD = "DS_CTAC_PSN_TTL_CD";

    /** DS_CTAC_PNT_TP_CD */
    public static final String DS_CTAC_PNT_TP_CD = "DS_CTAC_PNT_TP_CD";
    // 2018/03/06 S21_NA#23351 Mod End

    /** TEL_DS_CTAC_PNT_VAL_TXT */
    public static final String TEL_DS_CTAC_PNT_VAL_TXT = "TEL_DS_CTAC_PNT_VAL_TXT";

    /** EXTN_DS_CTAC_PNT_VAL_TXT */
    public static final String EXTN_DS_CTAC_PNT_VAL_TXT = "EXTN_DS_CTAC_PNT_VAL_TXT";

    /** EML_DS_CTAC_PNT_VAL_TXT */
    public static final String EML_DS_CTAC_PNT_VAL_TXT = "EML_DS_CTAC_PNT_VAL_TXT";

    /** CELL_DS_CTAC_PNT_VAL_TXT */
    public static final String CELL_DS_CTAC_PNT_VAL_TXT = "CELL_DS_CTAC_PNT_VAL_TXT";

    /** FAX_DS_CTAC_PNT_VAL_TXT */
    public static final String FAX_DS_CTAC_PNT_VAL_TXT = "FAX_DS_CTAC_PNT_VAL_TXT";

    /** SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** UPLD_CSV_RQST_CMNT_TXT */
    public static final String UPLD_CSV_RQST_CMNT_TXT = "UPLD_CSV_RQST_CMNT_TXT";

    /** UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "UPLD_CSV_RQST_PK";

    /** UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** SVC_CTAC_TP_DESC_TXT */
    public static final String SVC_CTAC_TP_DESC_TXT = "SVC_CTAC_TP_DESC_TXT";

    /** COUNT */
    public static final String COUNT = "COUNT";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** SVC_MACH_MSTR_PK */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** GET_WRK */
    public static final String GET_WRK = "getContactInfoWrk";

    /** CHK_PRMRY */
    public static final String CNT_PRMRY = "countPrimary";

    // Del Start 2017/08/30 QC#20791
    ///** GET_PRSN_NM */
    //public static final String GET_PRSN_NM = "getPersonName";
    // Del End 2017/08/30 QC#20791

    /** GET_ACCNT_LCTN */
    public static final String CNT_ACCNT_LCTN = "countAccountLocation";

    // Del Start 2017/08/30 QC#20791
    ///** CNT_CNTCT_PRSN */
    //public static final String CNT_CNTCT_PRSN = "countContactPerson";
    // Del End 2017/08/30 QC#20791

    /** GET_SRVC_MCHN */
    public static final String GET_SRVC_MCHN = "getSrvcMchnCntctPrsn";

    /** GET_DS_CTAC_PSN_SALT */
    public static final String GET_DS_CTAC_PSN_SALT = "getDsCtacPsnSalt";

    /** GET_AVAILABLE_SRVC_MCHN */
    public static final String GET_AVAILABLE_SRVC_MCHN = "getAvailableServiceMachine";

    // 2017/10/03 CSA-QC#20769 Add Start
    /** GET_CTAC_TP_PNT_MDN_DFN */
    public static final String GET_CTAC_TP_PNT_MDN_DFN = "getCtacTpPntMndDfn";

    // Add Start 2018/07/18 QC#26407
    /** GET_SVC_CTAC_TP */
    public static final String GET_SVC_CTAC_TP = "getSvcCtacTp";
    // Add End 2018/07/18 QC#26407

    /** Slash */
    public static final String SLASH = "/";

    /** Period */
    public static final String PERIOD = "\\.";

    /** Hyphen */
    public static final String HYPHEN = "-";
    // 2017/10/03 CSA-QC#20769 Add End

    // Add Start 2018/12/14 QC#24022
    /** Max length of extension */
    public static final int EXT_MAX_LENGTH = 10;
    // Add End 2018/12/14 QC#24022

}
