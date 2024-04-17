package com.canon.cusa.s21.batch.NSA.NSAB086001.constant;

/**
 * <pre>
 * Meter Reading Notification for Email
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/11/10   Hitachi         Y.Nagasawa      Create          QC#61756
 */
public class NSAB086001Constant {

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** MTR_NTFY_STS_WAITING_FOR_SEND */
    public static final String MTR_NTFY_STS_WAITING_FOR_SEND = "01";

    /** MTR_NTFY_STS_COMPLETED */
    public static final String MTR_NTFY_STS_COMPLETED = "02";

    /** MTR_NTFY_STS_ERROR */
    public static final String MTR_NTFY_STS_ERROR = "09";

    /** SQL_DATE_FORMAT(YYYYMMDDHH24MISS) */
    public static final String SQL_DATE_FORMAT = "YYYYMMDDHH24MISS";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NSAB0860M001";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is invalid value. */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Max Commit Count */
    public static final int MAX_COMMIT_COUNT = 1000;

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

}
