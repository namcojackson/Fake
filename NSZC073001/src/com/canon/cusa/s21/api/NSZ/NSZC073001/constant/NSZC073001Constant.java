/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC073001.constant;

/**
 * <pre>
 * TTS Escalation API Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2015   Hitachi         Y.Tsuchimoto    Create          NA#TTS Escalation API
 * 2015/12/24   Hitachi         K.Yamada        Update          CSA QC#2359
 * </pre>
 */
public final class NSZC073001Constant {
    /**
     * An input parameter, [@], has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /**
     * Failed to insert the @.
     */
    public static final String NSZM0626E = "NSZM0626E";

    /**
     * @ doesn't exist.
     */
    public static final String NSZM0627E = "NSZM0627E";

    /**
     * The @ does not exist in S21. Please check it.
     */
    public static final String NSZM0628E = "NSZM0628E";

    /**
     * The @ is incorrect. Please check it.
     */
    public static final String NSZM0629E = "NSZM0629E";

    /**
     * NSZM0627E Parameter : Global Company Code
     */
    public static final String NSZM0627E_PARAM_GLBL_CMPY_CD = "Global Company Code";

    /**
     * NSZM0628E Parameter : Serial
     */
    public static final String NSZM0628E_PARAM_SERIAL = "Serial#";

    /**
     * NSZM0628E Parameter : Problem
     */
    public static final String NSZM0628E_PARAM_PROBLEM = "Problem Code";

    /**
     * NSZM0628E Parameter : Location
     */
    public static final String NSZM0628E_PARAM_LOCATION = "Location Code";

    /**
     * NSZM0628E Parameter : Correction
     */
    public static final String NSZM0628E_PARAM_CORRECTION = "Correction Code";

    /**
     * NSZM0628E Parameter : Escalation Date
     */
    public static final String NSZM0629E_PARAM_ESCALATION = "Escalation Date";

    /**
     * NSZM0628E Parameter : Reason
     */
    public static final String NSZM0628E_PARAM_REASON = "Reason Code";

    /**
     * NSZM0628E Parameter : Task
     */
    public static final String NSZM0628E_PARAM_TASK = "Task#";

    /**
     * Parameter name : glblCmpyCd
     */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * Parameter name : slsDt
     */
    public static final String PARAM_SLS_DT = "slsDt";

    /**
     * Parameter name : svcTaskNum
     */
    public static final String PARAM_SVC_TASK_NUM = "svcTaskNum";

    /**
     * Parameter name : esclTpTxt
     */
    public static final String PARAM_ESCL_TP_TXT = "esclTpTxt";

    /**
     * Parameter name : dsAcctNm
     */
    public static final String PARAM_DS_ACCT_NM = "dsAcctNm";

    /**
     * Parameter name : firstLineAddr
     */
    public static final String PARAM_FIRST_LINE_ADDR = "firstLineAddr";

    /**
     * Parameter name : mdlNm
     */
    public static final String PARAM_MDL_NM = "mdlNm";

    /**
     * Parameter name : serNum
     */
    public static final String PARAM_SER_NUM = "serNum";

    /**
     * Parameter name : techNm
     */
    public static final String PARAM_TECH_NM = "techNm";

    /**
     * Parameter name : cellPhoNum
     */
    public static final String PARAM_CELL_PHO_NUM = "cellPhoNum";

    /**
     * Parameter name : svcPblmTpCd
     */
    public static final String PARAM_SVC_PBLM_TP_CD = "svcPblmTpCd";

    /**
     * Parameter name : svcPblmLocTpCd
     */
    public static final String PARAM_SVC_PBLM_LOC_TP_CD = "svcPblmLocTpCd";

    /**
     * Parameter name : svcPblmCrctTpCd
     */
    public static final String PARAM_SVC_PBLM_CRCT_TP_CD = "svcPblmCrctTpCd";

    /**
     * Parameter name : svcPblmRsnTpCd
     */
    public static final String PARAM_SVC_PBLM_RSN_TP_CD = "svcPblmRsnTpCd";

    /**
     * Parameter name : helpDeskTktNum
     */
    public static final String PARAM_HELP_DESK_TKT_NUM = "helpDeskTktNum";

    /**
     * Parameter name : svcCmntTxt
     */
    public static final String PARAM_SVC_CMNT_TXT = "svcCmntTxt";

    /**
     * Parameter name : attDataDescTxt
     */
    public static final String PARAM_ATT_DATA_DESC_TXT = "attDataDescTxt";

    /**
     * Parameter name : techSprtGrpEmlAddr
     */
    public static final String PARAM_TECH_SPRT_GRP_EML_ADDR = "techSprtGrpEmlAddr";

    /**
     * ReturnCode (Normal):
     */
    public static final String RETURN_CD_NORMAL = "0000";

    // add start 2015/12/24 CSA Defect#2359
    /** Default Global Company Code */
    public static final String DEFAULT_GLBL_CMPY_CD = "ADB";
    // add end 2015/12/24 CSA Defect#2359

}
