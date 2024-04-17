/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC075001.constant;

/**
 * <pre>
 * Receive Field Request from Click Software API Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/06/2015   Hitachi         T.Iwamoto       Create          NA#Receive Field Request from Click Software API
 * 11/07/2016   Hitachi         K.Ochiai        Update          CSA QC#15819
 * 11/24/2016   Hitachi         N.Arai          Update          QC#15829
 * 2016/12/12   Hitachi         K.Kojima        Update          QC#16300
 * 2019/07/26   Hitachi         T.Aoyagi        Update          QC#52029
 * 2023/10/06   Hitachi         K.Watanabe      Update          QC#54186
 * </pre>
 */
public final class NSZC075001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "Serial#" is a mandatory field.
     */
    public static final String NSZM0653E = "NSZM0653E";

    /**
     * Input parameter "Service Call Type Code" is a mandatory field.
     */
    public static final String NSZM0418E = "NSZM0418E";

    /**
     * Input parameter "Technician Code" is a mandatory field.
     */
    public static final String NSZM0052E = "NSZM0052E";

    /**
     * Input parameter "Problem Code" is a mandatory field.
     */
    public static final String NSZM0702E = "NSZM0702E";

    /**
     * Serial# "@" is not found.
     */
    public static final String NSZM0703E = "NSZM0703E";

    /**
     * Serial# "@" is not valid status to create service call.
     */
    public static final String NSZM0704E = "NSZM0704E";

    /**
     * Serial# "@" is not valid status to create warehouse call.
     */
    public static final String NSZM0705E = "NSZM0705E";

    /**
     * System cannot derive default value of "@".
     */
    public static final String NSZM0706E = "NSZM0706E";

    /**
     * Input parameter "Request Program Code" is a mandatory field.
     */
    public static final String NSZM0707E = "NSZM0707E";

    // START 2019/07/26 T.Aoyagi [QC#52029,ADD]
    /**
     * Serial # cannot be uniquely specified.
     */
    public static final String NSAM0128E = "NSAM0128E";
    // END 2019/07/26 T.Aoyagi [QC#52029,ADD]

    /**
     * FLD_RQST_SRC_PGM_CD C:Click Software
     */
    public static final String FLD_RQST_SRC_PGM_CLICK = "C";

    /**
     * FLD_RQST_SRC_PGM_CD S:WMS Shop Call
     */
    public static final String FLD_RQST_SRC_PGM_WMS = "S";

    // START 2016/12/12 K.Kojima [QC#16300,ADD]
    /**
     * FLD_RQST_SRC_PGM_CD S:from Parts Order
     */
    public static final String FLD_RQST_SRC_PGM_PARTS = "P";
    // END 2016/12/12 K.Kojima [QC#16300,ADD]

    /**
     * XXM_MODE_CD 01:Create FSR
     */
    public static final String XX_MODE_CD = "01";

    /**
     * HH24MISS
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * HH24MISS
     */
    public static final int TIME_START_KEY = 8;

    /**
     * BYPS_PRF_TECH_FLG
     */
    public static final String BYPS_PRF_TECH_FLG = "BYPS_PRF_TECH_FLG";

    /**
     * MACH_DOWN_FLG
     */
    public static final String MACH_DOWN_FLG = "MACH_DOWN_FLG";

    /**
     * SVC_CALL_SRC_TP_CD
     */
    public static final String SVC_CALL_SRC_TP_CD = "SVC_CALL_SRC_TP_CD";

    /**
     * BYPS_PRF_TECH_FLG
     */
    public static final String SVC_MEMO_TP_CD = "SVC_MEMO_TP_CD";

    /**
     * FLD_RQST_DEF_RULE
     */
    public static final String RELN_BILL_TO_CUST_CD = "RELN_BILL_TO_CUST_CD";

    /** The corresponding data does not exist in "VAR_CHAR_CONST". */
    public static final String NSZM0102E = "NSZM0102E";

    /** The corresponding data does not exist in "VAR_CHAR_CONST". */
    public static final String NSZC0750_SUCCESS = "NSZC0750_SUCCESS";

    /** The corresponding data does not exist in "VAR_CHAR_CONST". */
    public static final String NSZC0750_FAILED = "NSZC0750_FAILED";

    /** The corresponding data does not exist in "VAR_CHAR_CONST". */
    public static final String NSZC0750_NOT_EXIST_EML = "NSZC0750_NOT_EXIST_EML";

    /** Failed to insert the CLICK_FLD_RQST. */
    public static final String NSZM0829E = "NSZM0829E";

    // add start 2016/11/07 QC#15819
    /** Input parameter "Service Call Type Code" or "External Call Type Reference Text" is a mandatory field. */
    public static final String NSZM1072E = "NSZM1072E";

    /** "Service Call Type Code" cannot be identified from Specified "External Call Type Reference Text" */
    public static final String NSZM1073E = "NSZM1073E";

    /** Input parameter "Problem Code" or "External Problem Type Reference Text" is a mandatory field. */
    public static final String NSZM1074E = "NSZM1074E";

    /** "Problem Code" cannot be identified from Specified "External Problem Type Reference Text" */
    public static final String NSZM1075E = "NSZM1075E";
    // add end 2016/11/07 QC#15819

// START 2016/11/24 N.Arai [QC#15829, MOD]
      /** column name:SVC_MACH_MSTR_PK */
      public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

      /** column name:SVC_BY_LINE_BIZ_TP_CD */
      public static final String SVC_BY_LINE_BIZ_TP_CD = "SVC_BY_LINE_BIZ_TP_CD";

      /** column name:FLD_SVC_BR_CD */
      public static final String FLD_SVC_BR_CD = "FLD_SVC_BR_CD";

      /** column name:FIN_BR_CD */
      public static final String FIN_BR_CD = "FIN_BR_CD";

      /** column name:CUR_LOC_ACCT_NUM */
      public static final String CUR_LOC_ACCT_NUM = "CUR_LOC_ACCT_NUM";

      /** Message : Failed to update @ table.[@] */
      public static final String NSBM0120E = "NSBM0120E";
//END 2016/11/24 N.Arai [QC#15829, MOD]

      // START 2023/10/06 K.Watanabe [QC#54186, ADD]
      /** column name:SVC_BY_LINE_BIZ_TP_CD */
      public static final String ISTL_BY_SVC_PRVD_PTY_CD = "ISTL_BY_SVC_PRVD_PTY_CD";

      /** column name:SVC_BY_LINE_BIZ_TP_CD */
      public static final String SVC_BY_SVC_PRVD_PTY_CD = "SVC_BY_SVC_PRVD_PTY_CD";
      // END 2023/10/06 K.Watanabe [QC#54186, ADD]
}
