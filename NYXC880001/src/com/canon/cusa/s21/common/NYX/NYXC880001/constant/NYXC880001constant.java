package com.canon.cusa.s21.common.NYX.NYXC880001.constant;

public class NYXC880001constant {
    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NYEM0002E  = "NYEM0002E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NYEM0003W = "NYEM0003W";

    /** The process has been successfully completed. */
    public static final String NYEM0004I = "NYEM0004I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** An input parameter "@" has not been set. */
    public static final String NYEM0005E = "NYEM0005E";

    /** No Tasks found for the selected Event. */
    public static final String NYEM0006I = "NYEM0006I";

    /** Failed to approve the following claim(s): @ */
    public static final String NYEM0007E = "NYEM0007E";

    /** Failed to reject the following claim(s): @ */
    public static final String NYEM0008E = "NYEM0008E";

    /** [@] is already registered. */
    public static final String NYEM0009E = "NYEM0009E";

    /** Please select at least 1 checkbox. */
    public static final String NYEM0010E = "NYEM0010E";

    /** Cannot add anymore details. */
    public static final String NYEM0011E = "NYEM0011E";

    /** @ record does not exist. */
    public static final String ZZSM4110E = "ZZSM4110E";

    /** The attached file data cannot be read. : [@] */
    public static final String ZZMM0004E = "ZZMM0004E";

    /** Unsupported error type was specified.[msgCode : @][msgParam : @] */
    public static final String NYEM0001E = "NYEM0001E";

    /** @@@@. */
    public static final String ZZSM4199E = "ZZSM4199E";

    /** Please check only 1 checkbox. */
    public static final String NYEM0012E = "NYEM0012E";

    /** Error happen during checking parameter. The parameter is @. */
    public static final String NYEM0013E = "NYEM0013E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NYEM0014E = "NYEM0014E";

    /** [@] is not selected. */
    public static final String NYEM0015E = "NYEM0015E";

    /** @ record does not exist. */
    public static final String NYEM0016E = "NYEM0016E";

    /** Effective Periods are overlapping.  Please correct. */
    public static final String NYEM0017E = "NYEM0017E";

// QC#23516 ADD START 2018/05/07
    /** Failed to comment the following claim(s): @ */
    public static final String NYEM0018E = "NYEM0018E";

    /** [@] don't have the required authorization. [@] */
    public static final String NYEM0019E = "NYEM0019E";
// QC#23516 ADD END 2018/05/07

    public static final String NYEM0020E = "NYEM0020E";
    public static final String NYEM0021I = "NYEM0021I";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // UI Constant
    // --------------------------------

    /** IMG Control Name */
    public static final String VIEW_IMG_PROCESSING = "imgProcessing";

    /** IMG Control Name */
    public static final String VIEW_IMG_COMPLETE = "imgCompleted";

    /** Button Control Name */
    public static final String VIEW_BTN_DETAIL = "Detail";

    /** Button Control Name */
    public static final String VIEW_BTN_DETAIL_HISTORY = "Detail_History";

    // --------------------------------
    // Biz Constant
    // --------------------------------

    /** Action User WF System, */
    public static final String ACT_USER_WFSYSTEM = "Workflow System";

    /** New Line */
    public static final String TEXTAREA_NEWLINE = "\r\n";

    /** Wf_Administrator */
    public static final String ADMIN_OPERATOR_ID = "S21NWF_ADMINISTRATOR";

    /** Administrator Name*/
    public static final String ADMIN_OPERATOR_NAME = "Administrator";

// 2018/09/21 ADD START
    /** Administrator Rights */
    public static final String ADMIN_RIGHTS = "*";
// 2019/09/21 ADD END

    /** Date format */
    public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm z";

    /** ONE DATE TIME */
    public static final int ONE_DATE_TIME = 1000 * 60 * 60 * 24;

    /** ONE HOUR TIME */
    public static final int ONE_HOUR_TIME = 1000 * 60 * 60;

    /** ONE MINUTE TIME */
    public static final int ONE_MINUTE_TIME = 1000 * 60;

    /** Process All Code */
    public static final String PROC_ID_ALL = "ALL";

    /** Task All Code */
    public static final String TASK_ID_ALL = "ALL";

    /** Tab Setting */
    public static final String CTX_ATTR_BIZ_PROC_INF = "businessProcessInfo";

    /** Tab Setting */
    public static final String CTX_ATTR_SEL_PROC_INF = "SelectedS21ProcessInfo";

    /** Process Name Length */
    public static final int PROCESS_NM_LENGTH = 40;

    // --------------------------------
    // Mail Template
    // --------------------------------

    /** Mail Default Template */
    public static final String DEFAULT_TEMPLATE = "NWFDEFAULT";

}
