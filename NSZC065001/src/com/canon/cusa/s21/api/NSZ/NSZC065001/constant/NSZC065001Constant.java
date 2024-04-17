/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC065001.constant;

/**
 * <pre>
 * Contract Status Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/09/2015   Hitachi         Y.Tsuchimoto    Create          NA#Contract Status Validation API
 * 09/28/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 08/16/2018   CITS            M.Naito         Update          QC#27249
 * </pre>
 */
public final class NSZC065001Constant {
    /**
     * "Data Global Company Code" is required.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "CPO.CPO_ORD_TP_CD" is not Supply Order.
     */
    public static final String NSZM0608E = "NSZM0608E";

    /**
     * Input parameter "CPO_DTL.SVC_MACH_MSTR_PK" is a mandatory field.
     */
    public static final String NSZM0615E = "NSZM0615E";

    /**
     * Input parameter "CPO.CPO_ORD_TS" is a mandatory field.
     */
    public static final String NSZM0616E = "NSZM0616E";

    /**
     * The target data does not exist in Contract.
     */
    public static final String NSZM0617E = "NSZM0617E";

    /**
     * The target data does not exist in DS_CONTR_PRC_EFF.
     */
    public static final String NSZM0618E = "NSZM0618E";

    /**
     * The target data does not exist in DS_CONTR_BLLG_MTR.
     */
    public static final String NSZM0619E = "NSZM0619E";

    /**
     * DB Column Item : DS_CONTR_PK
     * */
    public static final String CLM_DS_CONTR_PK = "DS_CONTR_PK";

    /**
     * DB Column Item : DS_CONTR_DTL_PK
     * */
    public static final String CLM_DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /**
     * DB Column Item : ETTL_AVAL_FLG
     * */
    public static final String CLM_ETTL_AVAL_FLG = "ETTL_AVAL_FLG";

    /**
     * DB Column Item : DS_CONTR_CTRL_STS_CD
     * */
    public static final String CLM_DS_CONTR_CTRL_STS_CD = "DS_CONTR_CTRL_STS_CD";

    /**
     * DB Column Item : BASE_CHRG_FLG
     * */
    public static final String CLM_BASE_CHRG_FLG = "BASE_CHRG_FLG";

    /**
     * DB Column Item : USG_CHRG_FLG
     * */
    public static final String CLM_USG_CHRG_FLG = "USG_CHRG_FLG";

    // START 2018/08/16 M.Naito [QC#27249, ADD]
    /**
     * DB Column Item : SVC_PGM_MDSE_CD
     * */
    public static final String CLM_SVC_PGM_MDSE_CD = "SVC_PGM_MDSE_CD";
    // END 2018/08/16 M.Naito [QC#27249, ADD]

    /**
     * "YYYYMMDD LENGTH
     */
    public static final int YYYYMMDD_LENGTH = 8;

    // START 2016/09/28 A.Kohinata [QC#12898, ADD]
    /**
     * Input parameter "DS Contract Number" is a mandatory field.
     */
    public static final String NSZM0271E = "NSZM0271E";
    // END 2016/09/28 A.Kohinata [QC#12898, ADD]
}
