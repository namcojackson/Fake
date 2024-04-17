/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC008001;

/**
 * <pre>
 * Open RMA Master Maintenance<br>
 *
 * Date         Company         Name            Create/Update   Defect No * 
 * ----------------------------------------------------------------------
 * 02/23/2012   CSAI            N.Sasaki        Update          ITG#382732
 * </pre>
 */
public interface NLXC008001Constant {

    // public ---------------------------------------------
    /**     */
    public static final String CNT_ALL = "cntAll";

    /**     */
    public static final String CNT_ERR_100 = "cntErr100";

    /**     */
    public static final String CNT_ERR_200 = "cntErr200";
    
    /**
     * SQLID:SER_EVENT_STS_WRK02
     */
    public static final String SQLID_SES_01 = "001";

    /**
     * SQLID:SER_EVENT_STS_WRK03
     */
    public static final String SQLID_SES_02 = "002";

    /**
     * SQLID:SER_EVENT_STS_WRK04
     */
    public static final String SQLID_SES_03 = "003";

    /**     */
    public static final String GLBL_CMPY_CD_KEY = "glblCmpyCd01";

    /**     */
    public static final String MDSE_CD_KEY = "mdseCd01";

    /**     */
    public static final String SER_NUM_KEY = "serNum01";

    /**     */
    public static final String SER_OWNER_ID_KEY = "serOwnrId01";

    /** No record found in the table. Table Name:  "@", Key Item Name:  "@", Key Value:  "@"     */
    public static final String NLBM1039E = "NLBM1039E";

    /** DB Error occurred while  processing @ in @ table.  (Return CD: @)     */
    public static final String NLBM1019E = "NLBM1019E";

    /** Serial# is duplicated.     */
    public static final String NLZM2041W = "NLZM2041W";

    /** This Serial# is out of length. */
    public static final String NLZM2045W = "NLZM2045W";

    /** This Serial# is out of range. */
    public static final String NLZM2042W = "NLZM2042W";

    /**
     * SER_EVENT_STS
     */
    public static final String SER_EVENT_STS = "SER_EVENT_STS";

    /**     */
    public static final String KEY_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /**     */
    public static final String KEY_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /**
     * LOGICAL_REMOVE
     */
    public static final String LOGICAL_REMOVE = "logicalRemove";

    /**
     * UPDATE_SELECTION_FIELD
     */
    public static final String UPDATE_SELECTION_FIELD = "updateSelectionField";

    /**
     * PROC_STS_CD
     */
    public static final String PROC_STS_CD = "procStsCd";

    /**  */
    public static final String PROGRAM_ID = " ## NLXSerInfoInsert ## ";

    /** Change Serial# (SER_EVENT)     */
    public static final String CHNGE_SER_281 = "281";

    /** Third Party Order Released (SER_EVENT)     */
    public static final String THIRD_PARTY_SER_280 = "280";    

}
