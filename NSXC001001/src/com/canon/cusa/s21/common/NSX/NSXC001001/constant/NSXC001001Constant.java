/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * get Contract Information
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/15/2013   Fujitsu         S.Nakai         Create          N/A
 *</pre>
 */
public interface NSXC001001Constant {
    /** glblCmpyCd is mandatory. */
    String NSXM0001E = "NSXM0001E";
    /** glblCmpyCd is mandatory. */
    String NSXM0002E = "NSXM0002E";

    /**
     * SSM Select Column Name List.
     */
    enum SELECT {
        /** */
        DS_CONTR_PK
         /** */
        , DS_CONTR_NUM
        /** */
        , DS_CONTR_SQ_NUM
        /** */
        , DS_CONTR_NM
        /** */
        , DS_CONTR_DTL_PK
        /** */
        , CONTR_VRSN_EFF_FROM_DT
        /** */
        , CONTR_VRSN_EFF_THRU_DT
        /** */
        , CUST_PO_NUM
        /** */
        , PO_DT
        /** */
        , SVC_MACH_MSTR_PK
        /** */
        , PMT_TERM_CASH_DISC_CD
        /** */
        , DS_COV_SVC_TP_CD
        /** */
        , DS_COV_SVC_RATE
        /** */
        , SVC_CONFIG_MSTR_PK
        /** */
        , SVC_LBOR_CHRG_TP_CD
        /** */
        , CONTR_EFF_FROM_DT
        /** */
        , CONTR_EFF_THRU_DT
        /** */
        , RSP_TM_UP_MN_AOT
        /** */
        , RSP_TM_DOWN_MN_AOT
        /** */
        , CCY_CD
        /** */
        , PRNT_DS_CONTR_DTL_PK
        /** */
        , DS_CONTR_TP_NM
        /** */
        , SVC_COV_FROM_HOUR_MN
        /** */
        , SVC_COV_TO_HOUR_MN
        /** */
        , SVC_COV_SUN_FLG
        /** */
        , SVC_COV_MON_FLG
        /** */
        , SVC_COV_TUE_FLG
        /** */
        , SVC_COV_WED_FLG
        /** */
        , SVC_COV_THU_FLG
        /** */
        , SVC_COV_FRI_FLG
        /** */
        , SVC_COV_SAT_FLG
    }
    /** Warranty */
    String WARRANTY = "WARRANTY";
    /** Warranty */
    String WARRANTY_FLEET = "WARRANTY_FLEET";
    /** Fleet */
    String FLEET = "FLEET";
    /** Machine */
    String MACHINE = "MACHINE";
    /** Coverage */
    String COVERAGE = "COVERAGE";
    /** 100% Covered */
    BigDecimal FULL_COV = new BigDecimal("100");
}
