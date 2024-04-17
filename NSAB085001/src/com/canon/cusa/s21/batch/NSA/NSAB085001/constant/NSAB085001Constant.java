package com.canon.cusa.s21.batch.NSA.NSAB085001.constant;

/**
 * <pre>
 *  Meter Reading Notification Batch Extract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/11/10   Hitachi         Y.Nagasawa      Create          QC#61756
 */
public class NSAB085001Constant {

    /** MAX_FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** DEF_WINDOW_BEF_DAYS */
    public static final int DEF_WINDOW_BEF_DAYS = 0;

    /** MTR_NTFY_STS_WAITING_FOR_SEND */
    public static final String MTR_NTFY_STS_WAITING_FOR_SEND = "01";

    /** MTR_NTFY_STS_ERROR */
    public static final String MTR_NTFY_STS_ERROR = "09";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Unable to resend email because Billing Schedule is not open status. */
    public static final String NSAM0783E = "NSAM0783E";

}
