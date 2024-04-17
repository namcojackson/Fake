/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB201001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Actual Invoice Interface Import
 * Insert records from interface table(NFBI0110_01) into CM_IF_MSTR_INV table.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/31/2013   Fujitsu         K.Kimura        Update          WDS#2990
 * 03/29/2016   CSAI            K.Uramori       Update          Discontinue set id
 * </pre>
 */

public interface NMAB201001Constant {

    /** INTERFACE ID */
    static final String INTFC_ID = "NMAI0020";

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "CoA Segments Value Update";

    /** Fixed Value : Blank for null value */
    static final String BLANK = "";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    /** Yes/No */
    static final String YES = "Y";
    
    // COA Company
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Company */
    static final String COA_SEG_NM_COA_CMPY = "SEGMENT1";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Compamy */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_CMPY = 3;
    
    /** Fixed Value : Max length of MAX_DESC_COA_CMPY_NM for COA Branch */
    static final int MAX_LENGTH_COA_CMPY_NM = 240;    
    static final int MAX_LENGTH_COA_CMPY_DESC_TXT = 50;

    // COA Branch
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Branch */
    static final String COA_SEG_NM_COA_BR = "SEGMENT2";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Branch */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_BR = 3;
    
    /** Fixed Value : Max length of MAX_DESC_COA_BR_NM for COA Branch */
    static final int MAX_LENGTH_COA_BR_NM = 240;
    static final int MAX_LENGTH_COA_BR_DESC_TXT = 50;
    
    // COA Cost Center
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Cost Center */
    static final String COA_SEG_NM_COA_CC = "SEGMENT3";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Cost Center */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_CC = 6;
    
    /** Fixed Value : Max length of MAX_DESC_COA_CC_NM for COA Branch */
    static final int MAX_LENGTH_COA_CC_NM = 240;    
    static final int MAX_LENGTH_COA_CC_DESC_TXT = 240;
    
    // COA Account
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Account */
    static final String COA_SEG_NM_COA_ACCT = "SEGMENT4";
    static final int MAX_LENGTH_COA_ACCT_NM = 240;    
    static final int MAX_LENGTH_COA_ACCT_DESC_TXT = 50;
    
    // COA Product
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Product */
    static final String COA_SEG_NM_COA_PROD = "SEGMENT5";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Product */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_PROD = 8;
    
    /** Fixed Value : Max length of MAX_DESC_COA_PROD_NM for COA Branch */
    static final int MAX_LENGTH_COA_PROD_NM = 50;
    static final int MAX_LENGTH_COA_PROD_DESC_TXT = 50;
    
    // COA Channel
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Channel */
    static final String COA_SEG_NM_COA_CH = "SEGMENT6";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Channel */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_CH = 3;
    
    /** Fixed Value : Max length of MAX_DESC_COA_CH_NM for COA Branch */
    static final int MAX_LENGTH_COA_CH_NM = 30;
    static final int MAX_LENGTH_COA_CH_DESC_TXT = 50;
    
    // COA Affiliate
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Affiliate */
    static final String COA_SEG_NM_COA_AFFL = "SEGMENT7";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Affiliate */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_AFFL = 3;
    
    /** Fixed Value : Max length of MAX_DESC_COA_AFFL_NM for COA Branch */
    static final int MAX_LENGTH_COA_AFFL_NM = 50;
    static final int MAX_LENGTH_COA_AFFL_DESC_TXT = 50;
    
    // COA Project
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Project */
    static final String COA_SEG_NM_COA_PROJ = "SEGMENT8";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Project */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_PROJ = 4;
    
    /** Fixed Value : Max length of MAX_DESC_COA_PROJ_NM for COA Branch */
    static final int MAX_LENGTH_COA_PROJ_NM = 240;
    static final int MAX_LENGTH_COA_PROJ_DESC_TXT = 50;
    
    // COA Extension
    /** Fixed Value : COA_FLEX_VAL_SET_ID - COA Extension */
    static final String COA_SEG_NM_COA_EXTN = "SEGMENT9";

    /** Fixed Value : Max length of COA_FLEX_VAL_CD for COA Extension */
    static final int COA_FLEX_VAL_CD_LENGTH_COA_EXTN = 5;
    
    /** Fixed Value : Max length of MAX_DESC_COA_EXTN_NM for COA Branch */
    static final int MAX_LENGTH_COA_EXTN_NM = 18;    
    static final int MAX_LENGTH_COA_EXTN_DESC_TXT = 50;

    // ** Message ID ** //
    /** Information Message() */
    static final String ZZIM0009I = "ZZIM0009I";

    /** Information Message() */
    static final String ZZBM0009I = "ZZBM0009I";

    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";

    /** Error Message */
    static final String ZZBM0074E = "ZZBM0074E";
    
    /** Error Message */
    static final String NFAM0035E = "NFAM0035E";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String INTERFACE_ID = "INTERFACE_ID";

    /** DB Item Column Name */
    static final String TRANSACTION_ID = "TRANSACTION_ID";

    /** DB Item Column Name */
    static final String SEGMENT_ID = "SEGMENT_ID";

    /** DB Item Column Name */
    static final String UNIT_ID = "UNIT_ID";

    /** DB Item Column Name */
    static final String SEQ_NUMBER = "SEQ_NUMBER";

    /** DB Item Column Name */
    static final String COA_SEG_NM = "COA_SEG_NM";

    /** DB Item Column Name */
    static final String COA_FLEX_VAL_CD = "COA_FLEX_VAL_CD";

    /** DB Item Column Name */
    static final String COA_DESC_TXT = "COA_DESC_TXT";

    /** DB Item Column Name */
    static final String COA_ENBL_FLG = "COA_ENBL_FLG";

    /** DB Item Column Name */
    static final String COA_SMRY_FLG = "COA_SMRY_FLG";

    /** DB Item Column Name */
    static final String COA_END_ACTV_TS = "COA_END_ACTV_TS";

    /** DB Item Column Name */
    static final String COA_CRAT_TS = "COA_CRAT_TS";

    /** DB Item Column Name */
    static final String COA_LAST_UPD_TS = "COA_LAST_UPD_TS";

    /** DB Item Column Name */
    static final String COA_FLEX_VAL_SET_ID = "COA_FLEX_VAL_SET_ID";

    /** DB Item Column Name */
    static final String COA_FLEX_VAL_ID = "COA_FLEX_VAL_ID";

}
