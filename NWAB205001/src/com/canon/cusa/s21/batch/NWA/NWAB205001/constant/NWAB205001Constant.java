/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB205001.constant;

/**
 * <pre>
 * Cycle Expiration Check.
 * This class defines the constant used in the batch application
 * program of BusinessID NWAB205001. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   Fujitsu         S.Ohki          Create          N/A
 * 
 *</pre>
 */
public class NWAB205001Constant {

    /**
     * Message ID
     */
    public static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** It failed to update the [@] table. Pkey [@] */
        NWZM1024E,
        /** The specified Constant [@] has not been set. */
        NWAM0261E,
        /** An error occurred in API. <APIID: [@], Error Code: [@], Data Key: [@] */
        NWAM0323W,
        /** The mail template cannot be acquired. <Template ID: [@]> */
        NWAM0319E,
        /** The target record @ is locked by other user. */
        NWAM0196E,
        /** It failed to register Mail. */
        NWAM0268E,
        /** In the table [@], The data cannot be updated since it is being updated by others. Pkey[@] */
        NWAM0515E,
        /** Could not get MailGroupAddress. MAIL_GROUP_ID_TO : [@] MAIL_KEY1 : [@] */
        NWAM0516E,
        /** No search results found. (Target table:[@],  Search key:[@]) */
        NWAM0373E,
        /** Failed to purge process with the credit card Transaction. Pkey [@] */
        NWAM0836E,
        /** @ ended abnormally.   @ */
        NWAM0447E
    }

    /** Program ID */
    public static final String PROGRAM_ID = "NWAB205001";

    /** Program Name */
    public static final String PROGRAM_NM = "Cycle Expiration Check Batch";

    /** VAR_CHAR_CONST Key (NWAB2050_PURGE_MT) */
    public static final String VAR_CHAR_CONST_NM_NWAB2050_PURGE_MTH = "NWAB2050_PURGE_MTH";

    /** FSR_STS Code (Closed) */
    public static final String FSR_STS_CD_CLOSED = "95";

    /** Date Format (YYYYMMDD) */
    public static final String DATE_FORMAT_YYYYMMDD = "YYYYMMDD";

    /** Fatch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

}
