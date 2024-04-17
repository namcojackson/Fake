/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC067001.constant;

/**
 * <pre>
 * Modification Status Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2015   Hitachi         Y.Tsuchimoto    Create          NA#Modification Status Update API
 * 01/16/2017   Hitachi         T.Mizuki        Update          QC#17018
 * </pre>
 */
public final class NSZC067001Constant {
    /**
     * An input parameter, [@], has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /**
     * Service Modification cannot be found.
     */
    public static final String NSZM0622E = "NSZM0622E";

    /**
     * The Serial # is incorrect.  It is set out of the range of the service modification. 
     */
    public static final String NSZM0623E = "NSZM0623E";

    /**
     * Failed to update the SVC_MOD_STS.
     */
    public static final String NSZM0625E = "NSZM0625E";

    /**
     * Failed to insert the SVC_MOD_STS.
     */
    public static final String NSZM0626E = "NSZM0626E";

    /**
     * Parameter name : glblCmpyCd
     */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * Parameter name : svcModPlnId
     */
    public static final String PARAM_SVC_MOD_PLN_ID = "svcModPlnId";

    /**
     * Parameter name : svcTaskNum
     */
    public static final String PARAM_SVC_TASK_NUM = "svcTaskNum";

    /**
     * Parameter name : svcMachMstrPk
     */
    public static final String PARAM_SVC_MACH_MSTR_PK = "svcMachMstrPk";

    /**
     * Parameter name : mdseCd
     */
    public static final String PARAM_MDSE_CD = "mdseCd";

    /**
     * Parameter name : serNum
     */
    public static final String PARAM_SER_NUM = "serNum";

    /**
     * Parameter name : svcModProcStsCd
     */
    public static final String PARAM_SVC_MOD_PROC_STS_CD = "svcModProcStsCd";

    // mod start 2017/01/16 CSA QC#17018
    /**
     * MDSE Code Length
     */
    public static final int MDSE_CD_LENGTH = 8;
    // mod end 2017/01/16 CSA QC#17018
    /**
     * ReturnCode (Normal):
     */
    public static final String RETURN_CD_NORMAL = "0000";

    /**
     * DB Column Item : SVC_MOD_STS_PK
     */
    public static final String CLM_SVC_MOD_STS_PK = "SVC_MOD_STS_PK";

    /**
     * DB Column Item : SVC_MOD_PK
     */
    public static final String CLM_SVC_MOD_PK = "SVC_MOD_PK";

    /**
     * DB Column Item : SVC_MOD_DTL_PK
     */
    public static final String CLM_SVC_MOD_DTL_PK = "SVC_MOD_DTL_PK";

    /**
     * DB Column Item : SVC_MACH_MSTR_PK
     */
    public static final String CLM_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /**
     * DB Column Item : SVC_TASK_NUM
     */
    public static final String CLM_SVC_TASK_NUM = "SVC_TASK_NUM";

    /**
     * DB Column Item : SVC_MOD_OPT_CD
     */
    public static final String CLM_SVC_MOD_OPT_CD = "SVC_MOD_OPT_CD";

    /**
     * DB Column Item : SVC_MOD_OPT_DT
     */
    public static final String CLM_SVC_MOD_OPT_DT = "SVC_MOD_OPT_DT";
}
