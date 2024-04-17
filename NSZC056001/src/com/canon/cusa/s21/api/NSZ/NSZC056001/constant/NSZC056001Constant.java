/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC056001.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Billing Calculation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/30/2015   Hitachi         K.Kishimoto     Create
 * 03/11/2016   Hitachi         K.Kishimoto     Update          QC#5347
 * 2017/09/15   Hitachi         K.Kojima        Update          QC#21125
 * 2019/09/04   Hitachi         T.Aoyagi        Update          QC#53124
 * </pre>
 */
public class NSZC056001Constant {
    /** Process Mode 01:Billing Stage */
    public static final String MODE_BILLING_STAGE = "01";

    /** Process Mode 02:Preview Billing API */
    public static final String MODE_PREVIEW_BILLING_API = "02";

    /** Process Mode 03:Credit & Rebill */
    public static final String MODE_CREDIT_REBILL = "03";

    // add start 2019/09/04 QC#53124
    /**
     * CONTR_LOB_EXCL_TEST_COPY
     */
    public static final String CONTR_LOB_EXCL_TEST_COPY = "CONTR_LOB_EXCL_TEST_COPY";
    // add end 2019/09/04 QC#53124

    /** Process Mode is not valid. */
    public static final String NSZM0039E = "NSZM0039E";

    /** @ is not set.[@] */
    public static final String NSZM0401E = "NSZM0401E";

    /** @ is not found.[@] */
    public static final String NSZM0396E = "NSZM0396E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    // START 2017/09/15 K.Kojima [QC#21125,ADD]
    /** Since no previous meter read found, meter charge calculation cannot be processed. */
    public static final String NSZM1299E = "NSZM1299E";
    // END 2017/09/15 K.Kojima [QC#21125,ADD]

    /** GLBL_CMPY_CD */
    public static final String MSG_GLBL_CMPY_CD = "GlobalCompany Code";

    /** SLS_DT */
    public static final String MSG_SLS_DT = "Salse Date";

    /** DS_CONTR_DTL_PK */
    public static final String MSG_DS_CONTR_DTL_PK = "DS Contract Detail PK";

    /** BASE_CHRG_FLG */
    public static final String MSG_BASE_CHRG_FLG = "Base Charge Flag";

    /** USG_CHRG_FLG */
    public static final String MSG_USG_CHRG_FLG = "Usage Charge Flag";

    /** SVC_CR_REBIL_PK */
    public static final String MSG_SVC_CR_REBIL_PK = "Service Credit Rebill PK";

    /** EST_FLG */
    public static final String MSG_EST_FLG = "Estimate Flag";

    /** SCHD_DT */
    public static final String MSG_SCHD_DT = "Schedule Date";

    /** EFF_FROM_DT */
    public static final String MSG_EFF_FROM_DT = "Effective From Date";

    /** EFF_THRU_DT */
    public static final String MSG_EFF_THRU_DT = "Effective Thru Date";

    /** Input Parameters */
    public static final String MSG_INFO_IN_PRM = "Input Parameters";

    /** BigDecimal 100 */
    public static final BigDecimal BIGDECIMAL_100 = BigDecimal.valueOf(100);

    /** Days of Year */
    public static final BigDecimal DAYS_OF_YEAR = BigDecimal.valueOf(365);

    /** Date Format YYYYMMDD */
    public static final String DATE_FORMAT = S21CalendarUtilConstants.TYPE_YYYYMMDD;

	// Add Start 03/11/2016 <QC#5347>
    /**
     * Process Mode Invoice/Credit
     */
    public static final String PROC_MODE_INVOICE = "Q";
	// Add End   03/11/2016 <QC#5347>
}
