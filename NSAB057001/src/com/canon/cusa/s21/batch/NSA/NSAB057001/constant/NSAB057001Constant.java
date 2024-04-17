/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB057001.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ROSS_BAT_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ROSS_SEND_TRGT;

/**
 *<pre>
 * Update Meter Read Before Send To Sys85 Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/04/10   Hitachi         T.Tomita        Update          QC#18239
 *</pre>
 */
public class NSAB057001Constant {

    /** Global Company Code is mandatory. */
    public static final String NSAM0177E = "NSAM0177E";

    /** Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;
    // del start 2017/04/10 CSA Defect#18239
//    /** ROSS_SEND_TRGT_CD='3'  ROSS_BAT_PROC_STS_CD= '0' */
//    public static final String CASE_1 = ROSS_SEND_TRGT.NOT_APPLICABLE_BY_EDI_CODE + ROSS_BAT_PROC_STS.NOT_PROCESSED;
    // del end 2017/04/10 CSA Defect#18239
    /** ROSS_SEND_TRGT_CD='1'  ROSS_BAT_PROC_STS_CD= '0' */
    public static final String CASE_2 = ROSS_SEND_TRGT.APPLICABLE + ROSS_BAT_PROC_STS.NOT_PROCESSED;

    /** ROSS_SEND_TRGT_CD='2'  ROSS_BAT_PROC_STS_CD= '0' */
    public static final String CASE_3 = ROSS_SEND_TRGT.NOT_APPLICABLE_BY_TRANSACTION_STATUS + ROSS_BAT_PROC_STS.NOT_PROCESSED;
    // del start 2017/04/10 CSA Defect#18239
//    /** Column name : GLBL_CMPY_CD */
//    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";
    // del end 2017/04/10 CSA Defect#18239
    /** Column name : SVC_PHYS_MTR_READ_PK */
    public static final String COL_SVC_PHYS_MTR_READ_PK = "SVC_PHYS_MTR_READ_PK";
    // del start 2017/04/10 CSA Defect#18239
//    /** Column name : DS_CONTR_EDI_CD */
//    public static final String COL_DS_CONTR_EDI_CD = "DS_CONTR_EDI_CD";
    // del end 2017/04/10 CSA Defect#18239
    /** Column name : EST_FLG */
    public static final String COL_EST_FLG = "EST_FLG";
    // del start 2017/04/10 CSA Defect#18239
//    /** Column name : VLD_MTR_FLG */
//    public static final String COL_VLD_MTR_FLG = "VLD_MTR_FLG";
//
//    /** Column name : EFF_FROM_DT */
//    public static final String COL_EFF_FROM_DT = "EFF_FROM_DT";
//
//    /** Column name : SVC_MACH_MSTR_STS_CD */
//    public static final String COL_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";
    // del end 2017/04/10 CSA Defect#18239
    /** Column name : MTR_CNTR_ID */
    public static final String COL_MTR_CNTR_ID = "MTR_CNTR_ID";

    /** Column name : ACTV_FLG */
    public static final String COL_ACTV_FLG = "ACTV_FLG";
    // del start 2017/04/10 CSA Defect#18239
//    /** Column name : DS_CONTR_PK */
//    public static final String COL_DS_CONTR_PK = "DS_CONTR_PK";
//
//    /** Column name : DS_CONTR_CTRL_STS_CD */
//    public static final String COL_HEADER_DS_CONTR_CTRL_STS_CD = "HEADER_DS_CONTR_CTRL_STS_CD";
//
//    /** Column name : DS_CONTR_CTRL_STS_CD */
//    public static final String COL_DETAIL_DS_CONTR_CTRL_STS_CD = "DETAIL_DS_CONTR_CTRL_STS_CD";
    // del end 2017/04/10 CSA Defect#18239
    /** CASE2 Header DS_CONTR_CTRL_STS NG list */
    public static final List<String> CASE_2_DS_CONTR_CTRL_STS_NG_LIST = Collections.unmodifiableList(Arrays.asList(DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.ENTERED, DS_CONTR_CTRL_STS.SINGED, DS_CONTR_CTRL_STS.CANCELLED));

    /** MTR_CNTR_ID : Total(Hard Read) */
    public static final String MTR_CNTR_ID_TOTAL_HARD_READ = "101";

    /** MTR_CNTR_ID : BW(Hard Read) */
    public static final String MTR_CNTR_ID_BW_HARD_READ = "108";
}
