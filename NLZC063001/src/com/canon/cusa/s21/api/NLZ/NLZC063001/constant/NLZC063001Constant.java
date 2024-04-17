/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC063001.constant;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 *<pre>
 * PI Close API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2016   CITS            K.Ogino           Create          Initial
 *</pre>
 */
public class NLZC063001Constant extends S21ApiCommonBase {

    /**
     * TIMESTAMP_PATTERN
     */
    public static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * Physical Inventory Control record is not found.
     */
    public static final String NLCM0154E = "NLCM0154E";

    /**
     * Failed to update "PHYS_INVTY_CTRL".
     */
    public static final String NLCM0163E = "NLCM0163E";

    /** Global Company Code is mandatory. */
    public static final String NDAM0013E = "NDAM0013E";

    /** Input Parameter Sales Date is mandatory field. */
    public static final String NMZM0011E = "NMZM0011E";

    /** [Physical Inventory Number] parameter is required. */
    public static final String NLCM0155E = "NLCM0155E";

    /** [PI Count Status] parameter is required. */
    public static final String NLCM0164E = "NLCM0164E";

    /** message param */
    public static final String MSG_PARAM_GLBL_CMPY_CD = "Global Company Code";

    /** message param */
    public static final String MSG_PARAM_SALES_DATE = "Sales Date";

    /** message param */
    public static final String MSG_PARAM_PHYS_INVTY_CNT_STS_CD = "PHYS_INVTY_CNT_STS_CD";

    /** message param */
    public static final String MSG_PARAM_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";

    /** BIND_PARAM_GLBL_CMPY_CD */
    public static final String BIND_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** BIND_PARAM_PHYS_INVTY_NUM */
    public static final String BIND_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /** DB_RESULT_COL_PHYS_INVTY_CTRL_PK */
    public static final String DB_RESULT_COL_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";
}
