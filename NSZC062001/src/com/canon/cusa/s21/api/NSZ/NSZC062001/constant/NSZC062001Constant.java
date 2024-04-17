/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC062001.constant;

/**
 * <pre>
 * Service Call Creation for ITSM API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Hitachi         T.Mizuki        Create          N/A
 * </pre>
 */
public final class NSZC062001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

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
     * System cannot derive default value of "@".
     */
    public static final String NSZM0706E = "NSZM0706E";

    /**
     * Input parameter "Request Program Code" is a mandatory field.
     */
    public static final String NSZM0707E = "NSZM0707E";

    /** Failed to insert the XTRNL_SYS_CRAT_LOG. */
    public static final String NSZM0923E = "NSZM0923E";

    /** Failed to insert the XTRNL_SYS_CRAT_ERR_LOG. */
    public static final String NSZM0924E = "NSZM0924E";

    /** yyyyMMddHHmmss  */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** 8 */
    public static final int TIME_START_KEY = 8;

    /** BYPS_PRF_TECH_FLG */
    public static final String BYPS_PRF_TECH_FLG = "BYPS_PRF_TECH_FLG";

    /** MACH_DOWN_FLG */
    public static final String MACH_DOWN_FLG = "MACH_DOWN_FLG";

    /** SVC_CALL_SRC_TP_CD */
    public static final String SVC_CALL_SRC_TP_CD = "SVC_CALL_SRC_TP_CD";

    /** BYPS_PRF_TECH_FLG */
    public static final String SVC_MEMO_TP_CD = "SVC_MEMO_TP_CD";

    /** ITSM */
    public static final String ITSM = "I";

    /** PERCENT */
    public static final String PERCENT = "%";

    /** LENGTH_4 */
    public static final int LENGTH_4 = 4;

    /** PRAM bypsPrfTechFlg */
    public static final int PRAM_0 = 0;

    /** PRAM machDownFlg */
    public static final int PRAM_1 = 1;

    /** PRAM svcCallSrcTpCd */
    public static final int PRAM_2 = 2;

    /** PRAM svcMemoTpCd */
    public static final int PRAM_3 = 3;

}
