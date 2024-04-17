/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB255001.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * NWCB2540:Therefore Order Indexed Attributes Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/24   Fujitsu         W.Honda         Create          N/A
 * 2018/04/23   Fujitsu         W.Honda         Update          QC#24244
 *</pre>
 */

public class NWAB255001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWAB2550";

    /** Order Entry Business ID : NWAL1500 */
    public static final String ORD_ENTRY_BIZ_ID = "NWAL1500";

    /** Therefore Attach API Business ID : NWZC224001 */
    public static final String THEREFORE_ATT_BIZ_ID = "NWZC224001";

    /** Order Entry Business Name */
    public static final String ORD_ENTRY_BIZ_NM = "Order Entry";

    /** DOC_MGT_ATT_RQST_PK */
    public static final String COL_DOC_MGT_ATT_RQST_PK = "DOC_MGT_ATT_RQST_PK";

    /** DOC_MGT_DOC_ID */
    public static final String COL_DOC_MGT_DOC_ID = "DOC_MGT_DOC_ID";

    /** DOC_MGT_BIZ_DOC_NUM */
    public static final String COL_DOC_MGT_BIZ_DOC_NUM = "DOC_MGT_BIZ_DOC_NUM";

    /** DOC_MGT_PRNT_DOC_NUM */
    public static final String COL_DOC_MGT_PRNT_DOC_NUM = "DOC_MGT_PRNT_DOC_NUM";

    /** DOC_MGT_CATG_NUM */
    public static final String COL_DOC_MGT_CATG_NUM = "DOC_MGT_CATG_NUM";

    /** DOC_MGT_CATG_CD */
    public static final String COL_DOC_MGT_CATG_CD = "DOC_MGT_CATG_CD";

    /** ATT_DOC_TP_CD */
    public static final String COL_ATT_DOC_TP_CD = "ATT_DOC_TP_CD";

    /** CPO_ORD_NUM */
    public static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    //QC#24244 ADD START
    /** DOC_MGT_ATT_RTRY_RQST_FLG */
    public static final String COL_DOC_MGT_ATT_RTRY_RQST_FLG = "DOC_MGT_ATT_RTRY_RQST_FLG";

    /** ATTACH_MODE_CD : 01 */
    public static final String ATTACH_MODE_CD = "01";

    /** AQU_NUM */
    public static final String COL_AQU_NUM = "AQU_NUM";

    /** CONST_NM : THEREFORE_COND_FLD */
    public static final String CONST_NM_THEREFORE_COND_FLD = "THEREFORE_COND_FLD";
    //QC#24244 ADD END
    

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** It failed to update @. Table Name: [@] */
    public static final String NWAM0741E = "NWAM0741E";

    /** It failed to get [@].(@) */
    public static final String NWAM0877E = "NWAM0877E";

    /** The document category [@] is not defined. */
    public static final String NWAM0878E = "NWAM0878E";

    /** The document category [@] is not supported for document attach. */
    public static final String NWAM0879E = "NWAM0879E";

    /** Specified SO Number is invalid or does not exist. */
    public static final String NWAM0885E = "NWAM0885E";

    /** Specified CFS Lease Package Number is invalid or does not exist. */
    public static final String NWAM0886E = "NWAM0886E";

    /** Cannot register Therefore data to S21. Please contact IT Support. */
    public static final String NWAM0887E = "NWAM0887E";

}
