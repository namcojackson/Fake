/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC271001.Constant;

/**
 *<pre>
 * Territory Rule Validation API Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Fujitsu         K.Minamide      Create          N/A
 * 2016/06/24   SRAA            Y.Chen          Update          QC#10858
 * 2016/09/07   SRAA            Y.Chen          Update          QC#12258
 *</pre>
 */
public class NMZC271001Constant {

    // QC#10858
    @Deprecated
    /** Mode :1 Territory to Location */
    public static final String MODE_TERRITORY_TO_LOCATION = "1";

    /** Mode :2 Prospect to Territory */
    public static final String MODE_PROSPECT_TO_TERRITORY = "2";

    // QC#12258
    /** Mode :3 Prospect to Territory Batch Mode */
    public static final String MODE_PROSPECT_TO_TERRITORY_BATCH = "3";

    /** Global Company Code is not set. */
    public static final String NMZM0092E = "NMZM0092E";

    /** Input parameter "xxModeInd" is mandatary field. */
    public static final String NMZM0090E = "NMZM0090E";

    /** Input parameter "slsDt" is mandatary field. */
    public static final String NMZM0082E = "NMZM0082E";

    /** Input parameter "orgCd" is mandatary field. */
    public static final String NMZM0071E = "NMZM0071E";

    /**
     * Input parameter "stCd", "postCd", "dsAcctNm" or "dsCustSicCd"
     * must be entered.
     */
    public static final String NMZM0091E = "NMZM0091E";
    
    // QC#12258
    /** Search Condition Key : Row Number */
    public static final String SRCH_COND_KEY_XX_ROW_NUM = "xxRowNum";

    /** Search Condition Key : State Code */
    public static final String SRCH_COND_KEY_ST_CD = "stCd";

    /** Search Condition Key : Post Code */
    public static final String SRCH_COND_KEY_POST_CD = "postCd";

    /** Search Condition Key : Account Name */
    public static final String SRCH_COND_KEY_DS_ACCT_NM = "dsAcctNm";

    /** Search Condition Key : SIC Code */
    public static final String SRCH_COND_KEY_DS_CUST_SIC_CD = "dsCustSicCd";

}
