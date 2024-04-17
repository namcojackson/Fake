/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC050001.constant;

/**
 * <pre>
 * De-Register from UGW API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/03   Hitachi         Y.Zhang         Create          N/A
 * 2016/10/17   Hitachi         Y.Zhang         Create          QC#15162
 * 2016/11/24   Hitachi         N.Arai          Update          QC#15829
 *</pre>
 */
public class NSZC050001Constant {

    /**
     * Const Value:UGW_CSV_DBL_QUOT_FLG
     */
    public static final String CONST_UGW_CSV_DBL_QUOT_FLG = "UGW_CSV_DBL_QUOT_FLG";

    /**
     * Empty field
     */
    public static final String EMP = "";

   // START 2016/10/17 Y.Zhang [QC#15162, MOD]
    /**
     * CR+LF
     */
    public static final String CRLF_CODE = "\r\n";
    /**
     * Const Value:UGW_LINE_FEED_CD
     */
    public static final String CONST_UGW_LINE_FEED_CD = "UGW_LINE_FEED_CD";
    /**
     * LF_CODE
     */
    public static final String LF_CODE = "\n";
    /**
     * CRLF
     */ 
    public static final String CRLF = "CRLF";
    /**
     * LF
     */
    public static final String LF = "LF";
    // END 2016/10/17 Y.Zhang [QC#15162, MOD]

    /**
     *Column Value:VRSN_NM
     */
    public static final String COLUMN_VRSN_NM = "VRSN_NM";

    /**
     *Column Value:VRSN_NUM
     */
    public static final String COLUMN_VRSN_NUM = "VRSN_NUM";

    /**
     *Column Value:CUST_NM
     */
    public static final String COLUMN_CUST_NM = "CUST_NM";

    /**
     *Column Value:RDS_ID
     */
    public static final String COLUMN_RDS_ID = "RDS_ID";

    /**
     *Column Value:DEL_RDS_NM
     */
    public static final String COLUMN_DEL_RDS_NM = "DEL_RDS_NM";

    /**
     *Column Value:DEL_DVC_NM
     */
    public static final String COLUMN_DEL_DVC_NM = "DEL_DVC_NM";

    /**
     *Column Value:DEF_MTR_READ_METH_CD
     */
    public static final String CONST_DEF_MTR_READ_METH_CD = "DEF_MTR_READ_METH_CD";

    /**
     * Service machine master is not found.
     */
    public static final String NSZM0006E = "NSZM0006E";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /**
     * Failed to update IWR Registration Condition record.
     */
    public static final String NSZM1047E = "NSZM1047E";

    /**
     * Failed to update Ds Contract Detail record.
     */
    public static final String NSZM1048E = "NSZM1048E";

    /**
     * An error occurred in UGW API.
     */
    public static final String NSZM1049E = "NSZM1049E";

    /**
     * Failed to update a Service Machine Master record.
     */
    public static final String NSZM0036E = "NSZM0036E";

    /**
     * Failed to delete the machine information to UGW.
     */
    public static final String NSZM1050W = "NSZM1050W";

    /**
     * This machine cannot delete to UGW,because it is not existed in
     * UGW.
     */
    public static final String NSZM1051E = "NSZM1051E";

    /**
     * This registration Information is not existed in IWR
     * registration info.
     */
    public static final String NSZM1052E = "NSZM1052E";

    /**
     * Multiple registration Information exists in IWR registration
     * info.
     */
    public static final String NSZM1053E = "NSZM1053E";

    /**
     * Response message from UGW.Serial#:@, Message:@.
     */
    public static final String NSZM1068I = "NSZM1068I";

    /**
     * LENGTH:API_MSG
     */
    public static final int LENGTH_API_MSG = 1000;

    /** Message:Nomal End. */
    public static final String MSG_NML = "Done.";

    /** Message:Error Occurred. */
    public static final String MSG_ERR = "Error Occured.";

    /**
     * Const Value:NSZC0500_NORMAL_MSG
     */
    public static final String CONST_NORMAL_MSG = "NSZC0500_NORMAL_MSG";

// START 2016/11/24 N.Arai [QC#15829, MOD]
     /** column name:IWR_RGTN_STS_CD */
     public static final String IWR_RGTN_STS_CD = "IWR_RGTN_STS_CD";

     /** column name:IWR_COND_CD */
     public static final String IWR_COND_CD = "IWR_COND_CD";

     /** Message : Failed to update @ table.[@] */
     public static final String NSBM0120E = "NSBM0120E";

     /** Update User Id */
     public static final String UPDATE_USER_ID = "NSZC050001";
//END 2016/11/24 N.Arai [QC#15829, MOD]
}
