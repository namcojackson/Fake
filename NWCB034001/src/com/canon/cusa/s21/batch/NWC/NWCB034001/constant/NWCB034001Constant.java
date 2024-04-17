/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB034001.constant;

import java.math.BigDecimal;

import business.db.CFS_LEASE_PKG_PO_HDRTMsg;

/**
 *<pre>
 * NWCB0340:CFS Lease Package Extract Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/07/2016   CITS            T.Gotoda        Create          N/A
 * 07/26/2016   Fujitsu         W.Honda         Update          Therefore
 * 01/22/2018   Fujitsu         K.Ishizuka      Update          S21_NA#23439
 * 05/24/2018   Fujitsu         H.Kumagai       Update          QC#24431
 * 02/28/2019   Fujitsu         Y.Kanefusa      Update          S21_NA#30523
 * 09/30/2022   Hitachi         H.Watanabe      Update          QC#60253
 *</pre>
 */
public class NWCB034001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWCB0340";

    /** INV_THRHD */
    public static final String INV_THRHD = "INV_THRHD";

    // Therefore 2016/07/26 Add start
    /** 01 : Current Mode */
    public static final String MODE_CD_01 = "01";

    /** 02 : New Mode */
    public static final String MODE_CD_02 = "02";
    // Therefore 2016/07/26 Add end

    /** ONE_HUNDRED */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    /** RATE_FRAC_DIGIT */
    public static final int RATE_FRAC_DIGIT =  new CFS_LEASE_PKG_PO_HDRTMsg().getAttr("invCpltTotDealNetAmt").getFracDigit();

    // =================================================
    // DB Param
    // =================================================

    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;

    /** DB_PARAM_GLBL_CMPY_CD */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    // QC#24431 2018/06/08 Add Start
    /** DB_PARAM_ORD_HDR_STS_CANC */
    public static final String DB_PARAM_ORD_HDR_STS_CANC = "ordHdrStsCdCancel";
    // QC#24431 2018/06/08 Add End

    // Therefore 2016/07/26 Add start
    /** DB_PARAM_GLBL_CMPY_CD */
    public static final String DB_PARAM_MODE_CD = "modeCd";

    /** DB_PARAM_CFS_PO_NUM */
    public static final String DB_PARAM_CFS_PO_NUM = "cfsPoNum";

    /** DB_PARAM_CFS_APP_NUM */
    public static final String DB_PARAM_CFS_APP_NUM = "cfsAppNum";
    // Therefore 2016/07/26 Add end

    /** DB_PARAM_CPO_ORD_NUM */
    public static final String DB_PARAM_CPO_ORD_NUM = "cpoOrdNum";

    /** DB_PARAM_ATTRB_KEY_NM */
    public static final String DB_PARAM_ATTRB_KEY_NM = "attrbKeyNm";

    // 2022/09/30 QC#60253 Add Start
    /** DB_PARAM_ORD_HDR_STS_CD */
    public static final String DB_PARAM_ORD_HDR_STS_CD = "ordHdrStsCd";
    // 2022/09/30 QC#60253 Add End

    /** DB COLUMN : CPO_ORD_NUM */
    public static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB COLUMN : COL_BILL_TO_CUST_ACCT_CD */
    public static final String COL_BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";
    
    /** DB COLUMN : COL_SHIP_TO_CUST_ACCT_CD */
    public static final String COL_SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";

    /** DB COLUMN : COL_DS_ACCT_NM */
    public static final String COL_DS_ACCT_NM = "DS_ACCT_NM";

    /** DB COLUMN : COL_CPO_TOT_DEAL_NET_AMT */
    public static final String COL_CPO_TOT_DEAL_NET_AMT = "CPO_TOT_DEAL_NET_AMT";

    /** DB COLUMN : COL_SLS_REP_TOC_NM */
    public static final String COL_SLS_REP_TOC_NM = "SLS_REP_TOC_NM";

    /** DB COLUMN : COL_SLS_REP_TOC_CD */
    public static final String COL_SLS_REP_TOC_CD = "SLS_REP_TOC_CD";

    /** DB COLUMN : COL_INV_NUM */
    public static final String COL_INV_NUM = "INV_NUM";

    /** DB COLUMN : COL_INV_DT */
    public static final String COL_INV_DT = "INV_DT";

    /** DB COLUMN : COL_ACCT_DT */
    public static final String COL_ACCT_DT = "ACCT_DT";

    /** DB COLUMN : COL_INV_TP_CD */
    public static final String COL_INV_TP_CD = "INV_TP_CD";

    /** DB COLUMN : COL_BILL_TO_CUST_CD */
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    
    /** DB COLUMN : COL_SHIP_TO_CUST_CD */
    public static final String COL_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD"; // 2018/01/22 S21_NA#23439 Add

    /** DB COLUMN : COL_NEGO_DEAL_AMT */
    public static final String COL_NEGO_DEAL_AMT = "NEGO_DEAL_AMT"; 

    /** DB COLUMN : COL_INV_TOT_DEAL_NET_AMT */
    public static final String COL_INV_TOT_DEAL_NET_AMT = "INV_TOT_DEAL_NET_AMT";

    /** DB COLUMN : COL_INV_TOT_DEAL_SLS_AMT */
    public static final String COL_INV_TOT_DEAL_SLS_AMT = "INV_TOT_DEAL_SLS_AMT";

    /** DB COLUMN : COL_INV_TOT_DEAL_TAX_AMT */
    public static final String COL_INV_TOT_DEAL_TAX_AMT = "INV_TOT_DEAL_TAX_AMT";

    /** DB COLUMN : COL_INV_TOT_DEAL_DISC_AMT */
    public static final String COL_INV_TOT_DEAL_DISC_AMT = "INV_TOT_DEAL_DISC_AMT";

    /** DB COLUMN : COL_INV_TOT_DEAL_FRT_AMT */
    public static final String COL_INV_TOT_DEAL_FRT_AMT = "INV_TOT_DEAL_FRT_AMT";

    /** DB COLUMN : COL_COA_BR_CD */
    public static final String COL_COA_BR_CD = "COA_BR_CD";

    /** DB COLUMN : COL_PSN_FIRST_NM */
    public static final String COL_PSN_FIRST_NM = "PSN_FIRST_NM";

    /** DB COLUMN : COL_PSN_LAST_NM */
    public static final String COL_PSN_LAST_NM = "PSN_LAST_NM";

    /** DB COLUMN : COL_SLS_ADMIN_PSN_CD */
    public static final String COL_SLS_ADMIN_PSN_CD = "SLS_ADMIN_PSN_CD";

    // QC#30523 2019/02/28 Add Start
    /** DB COLUMN : COL_SLS_ADMIN_PSN_CD */
    public static final String COL_ORD_BOOK_REQ_USR_ID = "ORD_BOOK_REQ_USR_ID";
    // QC#30523 2019/02/28 Add End

    /** DB COLUMN : COL_AR_TRX_BAL_PK */
    public static final String COL_AR_TRX_BAL_PK = "AR_TRX_BAL_PK";

    /** DB COLUMN : COL_CR_REBIL_RSN_CATG_CD */
    public static final String COL_CR_REBIL_RSN_CATG_CD = "CR_REBIL_RSN_CATG_CD";

    // Therefore 2016/07/26 Add start
    /** DB COLUMN : COL_CFS_PO_NUM */
    public static final String COL_CFS_PO_NUM = "CFS_PO_NUM";

    /** DB COLUMN : COL_CFS_APP_NUM */
    public static final String COL_CFS_APP_NUM = "CFS_APP_NUM";

    /** DB COLUMN : COL_CFS_PO_AMT */
    public static final String COL_CFS_PO_AMT = "CFS_PO_AMT";

    /** DB COLUMN : COL_CFS_LEASE_PO_INFO_PK */
    public static final String COL_CFS_LEASE_PO_INFO_PK = "CFS_LEASE_PO_INFO_PK";

    /** DB COLUMN : COL_LEASE_CMPY_USR_ID */
    public static final String COL_LEASE_CMPY_USR_ID = "LEASE_CMPY_USR_ID";

    /** DB COLUMN : LEASE_CMPY_USR_NM */
    public static final String COL_LEASE_CMPY_USR_NM= "LEASE_CMPY_USR_NM";
    // Therefore 2016/07/26 Add end

    // QC#24431 2018/06/08 Add Start
    /** DB COLUMN : CFS_LEASE_PKG_PO_HDR_PK */
    public static final String COL_CFS_LEASE_PKG_PO_HDR_PK= "CFS_LEASE_PKG_PO_HDR_PK";
    // QC#24431 2018/05/24 Add Start

    // QC#24431 2018/05/24 Add Start
    /** DB COLUMN : ENT_CPO_TOT_DEAL_NET_AMT */
    public static final String COL_ENT_CPO_TOT_DEAL_NET_AMT= "ENT_CPO_TOT_DEAL_NET_AMT";
    // QC#24431 2018/05/24 Add Start

    // =================================================
    // Message Code
    // =================================================
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** It failed to register @ Table.[@] */
    public static final String NWCM0109E = "NWCM0109E";

    /** It failed to update @ Table.[@] */
    public static final String NWCM0110E = "NWCM0110E";

    /** It failed to delete @ Table.[@] */
    public static final String NWCM0111E = "NWCM0111E";

    /** It failed to get [@].(@) */
    public static final String NWCM0112E = "NWCM0112E";

    /** Parameter {@} is invalid value. */
    public static final String NWZM1400E = "NWZM1400E";
}
