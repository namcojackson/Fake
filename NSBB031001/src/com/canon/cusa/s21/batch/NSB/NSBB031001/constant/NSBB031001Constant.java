/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB031001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Create Task Daily Report Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Hitachi         G.Gotou         Create          N/A
 * 2016/07/05   Hitachi         O.Okuma         Update          CSA QC#10949
 * 2017/04/04   Hitachi         K.Kitachi       Update          CSA QC#18204
 * 2018/07/04   Hitachi         K.Kim           Update          CSA QC#26894
 * </pre>
 */
public class NSBB031001Constant {

    /** NASM0010E: Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** NASM0011E: Sales date cannot be obtained. */
    public static final String NASM0011E = "NASM0011E";

    /** Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** @ is not set.[@] */
    public static final String NSZM0401E = "NSZM0401E";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmss";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0310";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** get Organization Code */
    public static final String SEARCH_ORG_CD = "20";

    /** cnt TTL_IN_PROC_TASK */
    public static final String CNT_TTL_IN_PROC_TASK = "30";

    /** cnt PRT_WAIT_TASK */
    public static final String CNT_PRT_WAIT_TASK = "31";

    /** cnt SPCL_WAIT_TASK */
    public static final String CNT_SPCL_WAIT_TASK = "32";

    /** cnt CUST_TASK */
    public static final String CNT_CUST_TASK = "33";

    /** cnt CRAT_TASK */
    public static final String CNT_CRAT_TASK = "34";

    /** cnt AVAL_TECH */
    public static final String CNT_AVAL_TECH = "35";

    /** cnt AFT_HOUR_TASK */
    public static final String CNT_AFT_HOUR_TASK = "36";

    /** cnt AFT_HOUR_AVAL_TECH */
    public static final String CNT_AFT_HOUR_AVAL_TECH = "37";

    /** cnt CLO_TASK */
    public static final String CNT_CLO_TASK = "38";

    /** cnt PRT_FAIL */
    public static final String CNT_PRT_FAIL = "39";

    /** cnt POST_ENTRY_TASK */
    public static final String CNT_POST_ENTRY_TASK = "40";

    /** rate RSP_TM_CUST_TASK */
    public static final String RATE_RSP_TM_CUST_TASK = "41";

    /** rate RSP_TM_ALL_TASK */
    public static final String RATE_RSP_TM_ALL_TASK = "42";

    /** aot CUST_TASK_TTL_RSP */
    public static final String AOT_CUST_TASK_TTL_RSP = "43";

    /** cnt CLO_CUST_TASK */
    public static final String CNT_CLO_CUST_TASK = "44";

    /** aot ALL_TASK_TTL_RSP */
    public static final String AOT_ALL_TASK_TTL_RSP = "45";

    /** cnt CLO_ALL_TASK */
    public static final String CNT_CLO_ALL_TASK = "46";

    // START 2018/07/04 K.Kim [QC#26894, ADD]
    /** cnt ESCL_TASK */
    public static final String CNT_ESCL_TASK = "47";
    // END 2018/07/04 K.Kim [QC#26894, ADD]

    /** Colon */
    public static final String STR_COLON = ":";

    /** Fisrt Org Code:SERVICE */
    public static final String ORG_CD_SERVICE = "2";

    /** Structure Defenition:CSA */
    public static final String STRU_DFN_CSA = "CSA";

    /** Structure Defenition:LOB */
    public static final String STRU_DFN_LOB = "LOB";

    /** Structure Defenition:ZONE */
    public static final String STRU_DFN_ZONE = "ZONE";

    /** Structure Defenition:RG */
    public static final String STRU_DFN_RG = "RG";

    /** Structure Defenition:BR Rollup */
    public static final String STRU_DFN_BRROLLUP = "BRROLLUP";

    /** Structure Defenition:BR */
    public static final String STRU_DFN_BR = "BR";

    /** Structure Defenition:TEAM */
    public static final String STRU_DFN_TEAM = "TEAM";

   /** Compare S21_ORG.ORG_CD */
    public static final String[] CMP_S21_ORG_ORG_CD = {"", "",
        "AND SO.SCD_ORG_CD    = 'org_Cd'",
        "AND SO.THIRD_ORG_CD  = 'org_Cd'",
        "AND SO.FRTH_ORG_CD   = 'org_Cd'",
        "AND SO.FIFTH_ORG_CD  = 'org_Cd'",
        "AND SO.SIXTH_ORG_CD  = 'org_Cd'",
        "AND SO.SVNTH_ORG_CD  = 'org_Cd'",
        "AND SO.EIGHTH_ORG_CD = 'org_Cd'",
        "AND SO.NINTH_ORG_CD  = 'org_Cd'",
        "AND SO.TENTH_ORG_CD  = 'org_Cd'",
        "AND SO.ELVTH_ORG_CD  = 'org_Cd'",
        };

    // del start 2016/07/05 CSA Defect#1094
    // /** Compare ORG_STRU.ORG_CD */
    // public static final String[] CMP_ORG_STRU_ORG_CD = {"", "",
    //    "AND OS.SCD_ORG_CD    = 'org_Cd'",
    //    "AND OS.THIRD_ORG_CD  = 'org_Cd'",
    //    "AND OS.FRTH_ORG_CD   = 'org_Cd'",
    //    "AND OS.FIFTH_ORG_CD  = 'org_Cd'",
    //    "AND OS.SIXTH_ORG_CD  = 'org_Cd'",
    //    "AND OS.SVNTH_ORG_CD  = 'org_Cd'",
    //    "AND OS.EIGHTH_ORG_CD = 'org_Cd'",
    //    "AND OS.NINTH_ORG_CD  = 'org_Cd'",
    //    "AND OS.TENTH_ORG_CD  = 'org_Cd'",
    //    "AND OS.ELVTH_ORG_CD  = 'org_Cd'",
    //    };
    // del end 2016/07/05 CSA Defect#1094

    /** LAYER NUMBER */
    public static final int SCD_LAYERNUM = 2;
    public static final int THIRD_LAYERNUM = 3;
    public static final int FRTH_LAYERNUM = 4;
    public static final int FIFTH_LAYERNUM = 5;
    public static final int SIXTH_LAYERNUM = 6;
    public static final int SVNTH_LAYERNUM = 7;
    public static final int EIGHTH_LAYERNUM = 8;
    public static final int NINTH_LAYERNUM = 9;
    public static final int TENTH_LAYERNUM = 10;

    // START 2017/04/04 K.Kitachi [QC#18204, ADD]
    /** Max Rate Value */
    public static final BigDecimal MAX_RATE_VAL = new BigDecimal("9999.99999");

    /** Max Amount of Time Value */
    public static final BigDecimal MAX_AOT_VAL = new BigDecimal("9999999999");
    // END 2017/04/04 K.Kitachi [QC#18204, ADD]
}
