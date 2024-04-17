/**
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NZZ.NZZB004001.constant;

/**
 * <pre>
 * S21 IVP Outbound Batch
 *
 * Date       Company  Name          Create/Update   Defect No
 * -----------------------------------------------------------------------
 * 26/04/2019 CITS     T.Sakata      Create         QC#31335
 *</pre>
 */
public class NZZB004001Constant {

    /** Mail Group ID To*/
    public static final String MAIL_GROUP_ID_TO = "NZZB0040";

    /** Mail Group ID From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Bind Key for TODAY */
    public static final String TODAY = "TODAY";

    /** Bind Key for TIME */
    public static final String TIME = "TIME";

    // It failed to register Mail(s) into DB.
    public static final String ALXM1004E = "ALXM1004E";

    // Could not get MailGroupAddress.  MAIL_GROUP_ID_TO : [@] MAIL_KEY1 : [@]
    public static final String APAM0063E = "APAM0063E";
    
}