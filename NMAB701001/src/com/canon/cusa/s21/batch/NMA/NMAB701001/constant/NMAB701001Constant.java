/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB701001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Mass Upload Price List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   Fujitsu         M.Ohno          Create
 * 2016/06/16   Hitachi         Y.Takeno        Update          QC#8156
 * 2017/08/28   Fujitsu         H.Sugawara      Update          QC#19873
 *</pre>
 */
public final class NMAB701001Constant {

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    // Del Start 2017/08/28 QC#19873
    ///** String Date High Value */
    //public static final String HIGH_VAL_DT = "99991231";
    // Del End 2017/08/28 QC#19873

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Error Msg */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** Amount is overflowed.(@:@ , @:@) */
    public static final String NMAM8424E = "NMAM8424E";

    /** Error Msg */
    public static final String NMAM8228E = "NMAM8228E";
    
    /** @ must have at least one row.  Please enter data. */
    public static final String NMAM8214E = "NMAM8214E";
    
    // Del Start 2017/08/28 QC#19873
    ///** Please confirm the error details. */
    //public static final String NMAM8457E = "NMAM8457E";
    // Del End 2017/08/28 QC#19873

    /** MAX_AMT VALUE */
    public static final BigDecimal MAX_AMT = new BigDecimal("1000000000000000");

    /** user Id NMA7010001 */
    public static final String CSV_ID_NMA7010001 = "NMA7010001";

    /** user Id NMA7010002 */
    public static final String CSV_ID_NMA7010002 = "NMA7010002";

    /** user Id NMA7010003 */
    public static final String CSV_ID_NMA7010003 = "NMA7010003";

    /** user Id NMA7010004 */
    public static final String CSV_ID_NMA7010004 = "NMA7010004";

    /** user Id NMA7010005 */
    public static final String CSV_ID_NMA7010005 = "NMA7010005";

    /** user Id NMA7010006 */
    public static final String CSV_ID_NMA7010006 = "NMA7010006";

    /** user Id NMA7010007 */
    public static final String CSV_ID_NMA7010007 = "NMA7010007";

    /** user Id NMA7010008 */
    public static final String CSV_ID_NMA7010008 = "NMA7010008";

    // START 2016/06/16 [QC#8156,ADD]
    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_UPD */
    public static final String RESULT_MSG_UPD = "%d record(s) successfully updated.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
    // END 2016/06/16 [QC#8156,ADD]

    /** Price List ID Extension Key */
    public static final String EXTN_KEY = "PRC_CTG_CD";

    /** MSG_UPLD_CSV_ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** BigDecimal MAX_SORT_NUM:999 */
    public static final BigDecimal MAX_SORT_NUM = new BigDecimal("999");
    
    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";
}
