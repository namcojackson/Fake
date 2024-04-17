/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB265004.Constant;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         K.Minamide      Create          N/A
 * 2016/07/06   Fujitsu         R.Nakamura      Update          QC#5909
 * 2017/04/19   Fujitsu         k.Fujita        Update          RS#8275
 *</pre>
 */
public class NMAB265004Constant {

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Mode :1 Territory to Location */
    public static final String MODE_TERRITORY_TO_LOCATION = "1";

    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    // Add Start 2016/07/06 QC#5909paramsATRAL
    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;
    // Add End 2016/07/06 QC#5909paramsATRAL

    /** Org Rank Number Max */
    public static final String ORG_RANK_NUM_MAX = "999999999999";
}
