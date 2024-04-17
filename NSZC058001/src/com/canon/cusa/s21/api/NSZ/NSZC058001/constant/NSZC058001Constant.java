/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC058001.constant;

/**
 * <pre>
 * Supplemental Task Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/09   Hitachi         A.Kohinata      Create          N/A
 * 2016/05/11   Hitachi         T.Iwamoto       Update          QC#8109
 * 2018/06/15   Fujitsu         M.Ohno          Update          QC#25689
 *</pre>
 */
public class NSZC058001Constant {

    /** Process Mode: NEW */
    public static final String MODE_NEW = "1";

    /** Process Mode: UPDATE */
    public static final String MODE_UPDATE = "2";

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Time Format */
    public static final String TIME_FORMAT = "HHmmss";

    /** Date Time Format */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Process Mode is not valid. */
    public static final String NSZM0039E = "NSZM0039E";

    /** An error occurred when attempting to change date format. */
    public static final String NSZM0046E = "NSZM0046E";

    /** Failed to register to the @ table. */
    public static final String NSZM0398E = "NSZM0398E";

    /** Failed to update the @ table.[@] */
    public static final String NSZM0399E = "NSZM0399E";

    /** Input parameter "@" is a mandatory field. */
    public static final String NSZM0609E = "NSZM0609E";

    /** The target data does not exist in @. */
    public static final String NSZM0610E = "NSZM0610E";

    /** Supplemental task period is overlapping with other @ tasks. [@] - [@] */
    public static final String NSZM0611E = "NSZM0611E";

    /** Cannot proceed registration since another @ task [@] is active. */
    public static final String NSZM0612E = "NSZM0612E";

    /** Service Task Number for Interrupt is not active. [@] */
    public static final String NSZM0613E = "NSZM0613E";

    /**
     * Interrupt task period is not within service task period. [@] - [@] */
    public static final String NSZM0614E = "NSZM0614E";

    /** The chronological sequence between the [@] is wrong. */
    public static final String NSZM0620E = "NSZM0620E";

    /** MAX_DT */
    public static final String MAX_DT = "99991231";

    /** MAX_TM */
    public static final String MAX_TM = "235959";

    // 2018/06/15 QC#25689 add start
    /** MAX_TM_NUM(99:59) */
    public static final long MAX_TM_NUM = 5999;
    // 2018/06/15 QC#25689 add end
}
