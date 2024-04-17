/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB265003.Constant;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         K.Minamide      Create          N/A
 * 2016/07/05   Fujitsu         R.Nakamura      Update          QC#5909
 * 2017/05/09   Fujitsu         k.Fujita        Update          RS#8275
 *</pre>
 */
public class NMAB265003Constant {

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Mode :1 Territory to Location */
    public static final String MODE_TERRITORY_TO_LOCATION = "1";

    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    // Add Start 2016/07/06 QC#5909paramsATRAL
    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** Table Name : ACCT_TRTY_RESRC_LOG */
    public static final String TABLE_ACCT_TRTY_RESRC_LOG = "ACCT_TRTY_RESRC_LOG";
    // Add Start 2017/05/09 RS#8275
    /** Table Name : PROS_TRTY_RESRC_LOG */
    public static final String TABLE_PROS_TRTY_RESRC_LOG = "PROS_TRTY_RESRC_LOG";
    // Add End 2017/05/09 RS#8275
    /** Table Name : ACCT_TRTY_ROLE_ASG_LOG */
    public static final String TABLE_ACCT_TRTY_ROLE_ASG_LOG = "ACCT_TRTY_ROLE_ASG_LOG";

    /** Table Name : ACCT_TRTY_ROLE_ASG */
    public static final String TABLE_ACCT_TRTY_ROLE_ASG = "ACCT_TRTY_ROLE_ASG";

    /** Table Name : PROS_TRTY_ROLE_ASG_LOG */
    public static final String TABLE_PROS_TRTY_ROLE_ASG_LOG = "PROS_TRTY_ROLE_ASG_LOG";

    /** Table Name : PROS_TRTY_ROLE_ASG */
    public static final String TABLE_PROS_TRTY_ROLE_ASG = "PROS_TRTY_ROLE_ASG";
    // Add End 2016/07/06 QC#5909paramsATRAL

    // Add Start 2017/05/09 RS#8275
    /** Table Name : ACCT_TRTY_RESRC_ASG */
    public static final String TABLE_ACCT_TRTY_RESRC_ASG = "ACCT_TRTY_RESRC_ASG";

    /** Table Name : PROS_TRTY_RESRC_ASG */
    public static final String TABLE_PROS_TRTY_RESRC_ASG = "PROS_TRTY_RESRC_ASG";
    // Add End 2017/05/09 RS#8275
}
