/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB011001.constant;

/**
 * <pre>
 * Previous Open Task Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         G.Gotou         Create          N/A
 * 2019/03/28   Hitachi         K.Kitamura      Update          CSA QC#30906
 * </pre>
 */
public class NSBB011001Constant {

    /** NASM0010E: Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** NASM0011E: Sales date cannot be obtained. */
    public static final String NASM0011E = "NASM0011E";

    /** Failed to delete the @ table. */
    public static final String NSZM0637E = "NSZM0637E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** @ is not set.[@] */
    public static final String NSZM0401E = "NSZM0401E";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmss";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0110";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** get Open Service Task */
    public static final String SEARCH_OPEN_SERVICE_TASK = "50";

    /** get Organization Code */
    public static final String SEARCH_ORG_CD = "60";

    /** cnt PREV_OPEN_TASK */
    public static final String CNT_PREV_OPEN_TASK = "70";

    /** cnt INPROC_TASK */
    public static final String CNT_INPROC_TASK = "71";

    /** cnt CLO_TASK */
    public static final String CNT_CLO_TASK = "72";

    /** cnt CANC_TASK */
    public static final String CNT_CANC_TASK = "73";

    /** cnt REJ_TASK */
    public static final String CNT_REJ_TASK = "74";

    /** cnt AVG_TM_RSP */
    public static final String CNT_AVG_TM_RSP = "75";

    /** num SVC_RSP_TM */
    public static final String NUM_SVC_RSP_TM = "76";

    /** cnt ASG_TASK */
    public static final String CNT_ASG_TASK = "77";

    /** cnt OPEN_TASK */
    public static final String CNT_OPEN_TASK = "78";

    /** cnt HLD_TASK */
    public static final String CNT_HLD_TASK = "79";

    /** cnt ESCL_TASK */
    public static final String CNT_ESCL_TASK = "80";

    /** cnt VIP_TASK */
    public static final String CNT_VIP_TASK = "81";

    /** cnt ATTN_TASK */
    public static final String CNT_ATTN_TASK = "82";
    // START 2019/03/28 K.Kitamura [QC#30233, ADD]
    /** cnt TBO_TASK */
    public static final String CNT_TBO_TASK = "83";

    /** cnt SCHD_TASK */
    public static final String CNT_SCHD_TASK = "84";
    // END 2019/03/28 K.Kitamura [QC#30233, ADD]
    /** Colon */
    public static final String STR_COLON = ":";

    /** hyphen */
    public static final String STR_HYPHEN = " - ";

    /** searchCondition */
    public static final String SEARCH_PARAM = "searchCondition";

    /** Substring Position 0 */
    public static final int SUB_STR_POS_0 = 0;

    /** Substring Position 2 */
    public static final int SUB_STR_POS_2 = 2;

    /** Substring Position 4 */
    public static final int SUB_STR_POS_4 = 4;

    /** Fisrt Org Code:SERVICE */
    public static final String ORG_CD_SERVICE = "2";

}
