/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB270001.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2018/12/07   Fujitsu         T.Murai         Create          N/A
 * 2024/02/15   CITS            R.Tamura        Update          CSA-QC#63393
 *</pre>
 */
public class NWAB270001Constant {

    /** business_id : NWAB2700 */
    public static final String PROGRAM_ID = "NWAB270001";

    /** ZZZM9025E : [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** ZZZM9026E : [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /**
     * ZZOM0011E : Update failed because this record has been recently updated by another user.
     */
    public static final String ZZOM0011E = "ZZOM0011E";

    /**
     * ZZOM0011E : An error occurred in API. <APIID: [@], Error Code:[@], Data Key: [@]>
     */
    public static final String NWAM0323W = "NWAM0323W";

    /** NWZM1023E : It failed to insert the [@]. PK [@] */
    public static final String NWZM1023E = "NWZM1023E";

    /** */
    public static final int ERR_MSG_TXT_LEN = 1000;

    /** Insert Count */
    public static final int BULK_INSERT_CNT = 1000;

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Service Machine Transaction Type Code Length */
    public static final int SVC_MACH_TRX_TP_CD_LEN = 20;

    /** default EFF_THRU_DT */
    public static final String DEF_EFF_THRU_DT = "99991231";

    // START 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
    /** Main Machine data does not exist. [@] */
    public static final String NWAM0991E = "NWAM0991E";
    // END 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
}
