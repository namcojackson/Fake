/**
 * <pre>
 * Copyright (c) 2017 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB077001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Update Contract For Shell Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/24/2017   Hitachi         A.Kohinata      Create          N/A
 * 08/28/2017   Hitachi         Y.Takeno        Update          QC#20665
 * 09/22/2017   Hitachi         A.Kohinata      Update          QC#18534
 * 10/06/2017   Hitachi         A.Kohinata      Update          QC#21639
 * 12/04/2017   Hitachi         K.Ochiai        Update          QC#21698
 * 2018/02/23   Hitachi         K.Kojima        Update          QC#21685
 * 2018/05/09   Hitachi         K.Kitachi       Update          QC#25728
 * 2018/06/13   Fujitsu         M.Ohno          Update          QC#26495
 * 2019/01/21   Hitachi         K.Kim           Update          QC#29782
 *</pre>
 */
public class NSAB077001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NSAB077001";

    /** BATCH_MODE: DAILY */
    public static final String BATCH_MODE_DAILY = "0";

    /** BATCH_MODE: NIGHTLY */
    public static final String BATCH_MODE_NIGHTLY = "1";

    /** CONV_PROC_STS: IN_PROCESS */
    public static final String CONV_PROC_STS_IN_PROCESS = "1";

    /** CONV_PROC_STS: PROCESSED */
    public static final String CONV_PROC_STS_PROCESSED = "2";

    /** CONTR_ACT_MODE: NEW */
    public static final String CONTR_ACT_MODE_NEW = "NEW";

    /** CONTR_ACT_MODE: ADD */
    public static final String CONTR_ACT_MODE_ADD = "ADD";

    /** DATE FORMAT: yyyymmdd */
    public static final String DATE_FORMAT = "yyyymmdd";

    /** SPACE */
    public static final String SPACE = " ";

    // START 2018/05/09 K.Kitachi [QC#25728, ADD]
    /** COMMA */
    public static final String COMMA = ",";
    // END 2018/05/09 K.Kitachi [QC#25728, ADD]

    /** int: 2 */
    public static final int INT_2 = 2;

    /** int: 4 */
    public static final int INT_4 = 4;

    /** int: 6 */
    public static final int INT_6 = 6;

    /** int: 8 */
    public static final int INT_8 = 8;

    /** int: 28 */
    public static final int INT_28 = 28;

    /** INIT_DAY */
    public static final String INIT_DAY = "0";

    /** LAST_DAY_OF_A_MONTH */
    public static final String LAST_DAY_OF_A_MONTH = "99";

    // START 2017/08/28 [QC#20665, ADD]
    public static final String DELIMITER_SRC_REF_CMNT_TXT = "|";
    // END   2017/08/28 [QC#20665, ADD]

    /** Numbering UniqueID DS_CONTR_NUM */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_COMIT */
    public static final String TERM_COND_RSP_TM_COMIT = "TERM_COND_RSP_TM_COMIT";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_MEAS_PER */
    public static final String TERM_COND_RSP_TM_MEAS_PER = "TERM_COND_RSP_TM_MEAS_PER";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_RMD_1 */
    public static final String TERM_COND_RSP_TM_RMD_1 = "TERM_COND_RSP_TM_RMD_1";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_RMD_2 */
    public static final String TERM_COND_RSP_TM_RMD_2 = "TERM_COND_RSP_TM_RMD_2";

    /** VARCHAR CONST KEY: TERM_COND_MAX_CMBN_SLA_RMD */
    public static final String TERM_COND_MAX_CMBN_SLA_RMD = "TERM_COND_MAX_CMBN_SLA_RMD";

    /** SLA target VARCHAR CONST KEY */
    public static final String[] SLA_TERM_COND_VARCHAR_CONST_KEY = {TERM_COND_RSP_TM_COMIT, TERM_COND_RSP_TM_MEAS_PER, TERM_COND_RSP_TM_RMD_1, TERM_COND_RSP_TM_RMD_2, TERM_COND_MAX_CMBN_SLA_RMD };

    // START 2018/02/23 K.Kojima [QC#21685,ADD]
    /** VARCHAR CONST KEY: TERM_COND_STAPLES_INCL_NAME */
    public static final String TERM_COND_STAPLES_INCL_NAME = "TERM_COND_STAPLES_INCL_NAME";

    // END 2018/02/23 K.Kojima [QC#21685,ADD]

    // START 2018/05/09 K.Kitachi [QC#25728, ADD]
    /** VARCHAR CONST KEY: STUB_PREP_SVC_PGM_TP */
    public static final String STUB_PREP_SVC_PGM_TP = "STUB_PREP_SVC_PGM_TP";
    // END 2018/05/09 K.Kitachi [QC#25728, ADD]
    
    // START 2018/06/13 [QC#26495,ADD]
    /** VARCHAR CONST KEY: TERM_COND_CBS_STD_VRSN */
    public static final String TERM_COND_CBS_STD_VRSN = "TERM_COND_CBS_STD_VRSN";

    /** VARCHAR CONST KEY: TERM_COND_CONTR_PRC_TP */
    public static final String TERM_COND_CONTR_PRC_TP = "TERM_COND_CONTR_PRC_TP";

    /** Contract Level VARCHAR CONST KEY */
    public static final String[] CONTR_LVL_TERM_COND_VARCHAR_CONST_KEY = {TERM_COND_CBS_STD_VRSN, TERM_COND_CONTR_PRC_TP};

    // END 2018/06/13 [QC#26495,ADD] 

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    // START 2019/01/21 [QC#29782, ADD]
    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";
    // END 2019/01/21 [QC#29782, ADD]

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** Global Company Code is mandatory. */
    public static final String NSAM0177E = "NSAM0177E";

    /** Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** [@] cannot be obtained. */
    public static final String NSAM0179E = "NSAM0179E";

    /** The process cannot be executed because [@] is [@]. */
    public static final String NSAM0683E = "NSAM0683E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
    // START 2017/09/22 A.Kohinata [QC#18534,ADD]
    /** Process : New */
    public static final String PROC_NEW = "1";

    /** Process : Install Complete */
    public static final String PROC_INSTL_COMP = "2";
    // END 2017/09/22 A.Kohinata [QC#18534,ADD]

    // add start 2017/10/06 QC#21639
    /** DEF_FSR_NUM */
    public static final String DEF_FSR_NUM = "-1";
    // add end 2017/10/06 QC#21639

    // START 2017/12/04 K.Ochiai [QC#21698, ADD]
    public static final String ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP = "EMSD_CONTR_BR_REP";
    // END 2017/12/04 K.Ochiai [QC#21698, ADD]

    // START 2018/02/23 K.Kojima [QC#21685,ADD]
    public static final String STAPLES_INCL_VAL_NO = "01";

    public static final String STAPLES_INCL_VAL_YES = "02";
    // END 2018/02/23 K.Kojima [QC#21685,ADD]

    // START 2019/01/21 [QC#29782, ADD]
    /** DS_CONTR_BASE_USG_NM:BASE */
    public static final String DS_CONTR_BASE_USG_NM_B = "BASE";

    /** DS_CONTR_MACH_LVL_NUM:1 */
    public static final String DS_CONTR_MACH_LVL_NUM_1 = "1";

    /** DS_CONTR_MACH_LVL_NUM:2 */
    public static final String DS_CONTR_MACH_LVL_NUM_2 = "2";

    /** DS_CONTR_MACH_LVL_NUM:3 */
    public static final String DS_CONTR_MACH_LVL_NUM_3 = "3";

    /** ROW_NUM1 */
    public static final String ROW_NUM1 = "1";
    // END 2019/01/21 [QC#29782, ADD]
    // add start 2020/06/16 QC#54318-2
    public static final BigDecimal DEF_FIXED_MONTH = BigDecimal.valueOf(12);
    // add end 2020/06/16 QC#54318-2

}

