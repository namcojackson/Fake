/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC691001.constant;

/**
 * <pre>
 * MyCSA Customer Relation Create API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/07   Fujitsu         N.Sugawara      Create          N/A
 * </pre>
 */
public final class NMZC691001Constant {

    /**
     * Please set the correct Global Company Code.
     */
    public static final String NMZM0001E = "NMZM0001E";

    /**
     * Input parameter "Related Account From" is a mandatory field.
     */
    public static final String NMZM0353E = "NMZM0353E";

    /**
     * Input parameter "Related Account To" is a mandatory field.
     */
    public static final String NMZM0354E = "NMZM0354E";

    /**
     * Input parameter "Relation Type" is a mandatory field.
     */
    public static final String NMZM0355E = "NMZM0355E";

    /**
     * The entered [Related Account From] does not exist in [SELL_TO_CUST].
     */
    public static final String NMZM0356E = "NMZM0356E";

    /**
     * The entered [Related Account To] does not exist in [SELL_TO_CUST].
     */
    public static final String NMZM0357E = "NMZM0357E";

    /**
     * Either "Bill to Cust Flag" or "Ship to Cust Flag" needs to be set "Y".
     */
    public static final String NMZM0358E = "NMZM0358E";

    /**
     * Max Message Length
     */
    public static final int MAX_MSG_LEN = 300;

}
