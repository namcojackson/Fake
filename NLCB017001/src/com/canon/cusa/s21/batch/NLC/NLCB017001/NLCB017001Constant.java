/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB017001;

import java.math.BigDecimal;
/**
 * <pre>
 * SCM/DB Daily Inventory Result to Canon,Inc. (Interface Work)
 *
 * Date         Company         Name            Create/Update   Defect No
 * -----------------------------------------------------------------------
 * 09/27/2012   CSAI            M.Takahashi     Create          S21_PRD#410759
 * 10/29/2013   Fujitsu         S.Yoshida       Update          Def.2928
 * 11/25/2013   Fujitsu         S.Yoshida       Update          Def.3149
 * 03/14/2014   CSAI            Y.Imazu         UPdate          ITG#508447
 * 12/19/2014   Hitachi         T.Kanasaka      UPdate          CCH-QC200
 * 07/06/2016   CITS            T.Kikuhara      UPdate          Ver2.0
 *</pre>
 */
public interface NLCB017001Constant {

    /** Debug level for Debug */
    int CST_DEBUG_MSG_LVL = 1;

    // Message ID
    /** Message ID : ZZM9000E The field of [@] is not input. */
    String MSG_ID_ZZM9000E = "ZZM9000E";

    /** Message ID : ZZM9001E [@] field has too many digits entered. */
    String MSG_ID_ZZM9001E = "ZZM9001E";

    /** The value which is not numerical was input to the field of [@]. */
    String MSG_ID_ZZM9004E = "ZZM9004E";

    /** Message ID : ZZBM0009I The  (@)  was (@) . ResultCount = (@) */
    String MSG_ID_ZZBM0009I = "ZZBM0009I";

    /** Message ID : NLCM0053E The process abended. */
    String MSG_ID_NLCM0053E = "NLCM0053E";

    /** Message ID : NLCM0063E The value you entered is incorrect. */
    String MSG_ID_NLCM0063E = "NLCM0063E";

//START ADD S.Yoshida [Def.3149]
    /** [@] is mandatory. */
    String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Entered @ is invalid. */
    String MSG_ID_NLCM0065E = "NLCM0065E";
//END   ADD S.Yoshida [Def.3149]

    // Process Code
    /** Process Code : WE125 */
    String PROC_CD_WE125 = "WE125";

    /** Process Code : WE125M */
    String PROC_CD_WE125M = "WE125M";

//START DELETE S.Yoshida [CMEX localize]
//    /** Process Code : CAS125 */
//    String PROC_CD_CAS125 = "CAS125";
//
//    /** Process Code : CAS125M */
//    String PROC_CD_CAS125M = "CAS125M";
//END   DELETE S.Yoshida [CMEX localize]

    // Interface ID
    /** Interface ID : NLCI0160 */
    String INTERFACE_ID_NLCI0160 = "NLCI0160";

//START DELETE S.Yoshida [CMEX localize]
//    /** Interface ID : NLCI0090 */
//    String INTERFACE_ID_NLCI0090 = "NLCI0090";
//END   DELETE S.Yoshida [CMEX localize]

    /** Interface ID : NLCI0170 */
    String INTERFACE_ID_NLCI0170 = "NLCI0170";

//START DELETE S.Yoshida [CMEX localize]
//    /** Interface ID : NLCI0100 */
//    String INTERFACE_ID_NLCI0100 = "NLCI0100";
//
//    /** Interface ID : NLCI0060 */
//    String INTERFACE_ID_NLCI0060 = "NLCI0060";
//
//    /** Interface ID : NLCI0430 */
//    String INTERFACE_ID_NLCI0430 = "NLCI0430";
//
//    /** Interface ID : NLCI0070 */
//    String INTERFACE_ID_NLCI0070 = "NLCI0070";
//END   DELETE S.Yoshida [CMEX localize]

//START ADD S.Yoshida [Def.3149]
    /** Message string : Commit Count */
    String KEY_INCL_TECH_INVTY_FLG = "MDSE_INCL_TECH_INVTY_CINC_FLG";
//END   ADD S.Yoshida [Def.3149]

    // Table ID
    /** Table ID : INVTY_SNAP_SHOT */
    String TABLE_ID_INVTY_SNAP_SHOT = "INVTY_SNAP_SHOT";

    /** Table ID : NLCI0160_01 */
    String TABLE_ID_NLCI0160_01 = "NLCI0160_01";

//START DELETE S.Yoshida [CMEX localize]
//    /** Table ID : NLCI0090_01 */
//    String TABLE_ID_NLCI0090_01 = "NLCI0090_01";
//END   DELETE S.Yoshida [CMEX localize]

    /** Table ID : NLCI0170_01 */
    String TABLE_ID_NLCI0170_01 = "NLCI0170_01";

//START DELETE S.Yoshida [CMEX localize]
//    /** Table ID : NLCI0100_01 */
//    String TABLE_ID_NLCI0100_01 = "NLCI0100_01";
//
//    /** Table ID : NLCI0060_01 */
//    String TABLE_ID_NLCI0060_01 = "NLCI0060_01";
//
//    /** Table ID : NLCI0430_01 */
//    String TABLE_ID_NLCI0430_01 = "NLCI0430_01";
//
//    /** Table ID : NLCI0070_01 */
//    String TABLE_ID_NLCI0070_01 = "NLCI0070_01";
//END   DELETE S.Yoshida [CMEX localize]

    // Colmn Name
    /** Colmn Name : GLBL_CMPY_CD */
    String COL_NM_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Colmn Name : MDSE_CD */
    String COL_NM_MDSE_CD = "MDSE_CD";

    /** Colmn Name : INVTY_LOC_CD */
    String COL_NM_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Colmn Name : LOC_STS_CD */
    String COL_NM_LOC_STS_CD = "LOC_STS_CD";

    /** Colmn Name : STK_STS_CD */
    String COL_NM_STK_STS_CD = "STK_STS_CD";

    /** Colmn Name : LOC_TP_CD */
    String COL_NM_LOC_TP_CD = "LOC_TP_CD";

    /** Colmn Name : INVTY_QTY */
    String COL_NM_INVTY_QTY = "INVTY_QTY";

    /** Colmn Name : INVTY_AVAL_QTY */
    String COL_NM_INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /** Colmn Name : IMPT_VND_CD */
    String COL_NM_IMPT_VND_CD = "IMPT_VND_CD";

    // START 2016/07/01 T.Kikuhara V2.0
    /** Colmn Name : RTL_WH_CATG_CD */
    String COL_NM_RTL_WH_CATG_CD = "RTL_WH_CATG_CD";

    /** Colmn Name : MDSE_INVTY_COST_P */
    String COL_NM_MDSE_INVTY_COST_PCT = "MDSE_INVTY_COST_PCT";
    // END 2016/07/01 T.Kikuhara V2.0

//START ADD S.Yoshida [Def.2928]
    /** Colmn Name : LOC_ROLE_TP_CD */
    String COL_LOC_ROLE_TP_CD = "LOC_ROLE_TP_CD";
//END   ADD S.Yoshida [Def.2928]

    // Dummy Location Code
    /** Dummy Location Code : BA20 */
    String DUMMY_LOC_CD_BA20 = "BA20";

    /** Dummy Location Code : BA21 */
    String DUMMY_LOC_CD_BA21 = "BA21";

    /** Dummy Location Code : BA22 */
    String DUMMY_LOC_CD_BA22 = "BA22";

    /** Dummy Location Code : BA24 */
    String DUMMY_LOC_CD_BA24 = "BA24";

    /** Dummy Location Code : BB20 */
    String DUMMY_LOC_CD_BB20 = "BB20";

    // START 2016/07/01 T.Kikuhara V2.0
    /** Dummy Location Code : BB21 */
    String DUMMY_LOC_CD_BB21 = "BB21";

    /** Dummy Location Code : ZA20 */
    String DUMMY_LOC_CD_ZA20 = "ZA20";
    // END 2016/07/01 T.Kikuhara V2.0

    /** Dummy Location Code : ZA21 */
    String DUMMY_LOC_CD_ZA21 = "ZA21";

    /** Dummy Location Code : ZZ */
    String DUMMY_LOC_CD_ZZ = "ZZ";

    // Length of Item
    /** Length of Item : GLBL_CMPY_3_CD */
    int LENGTH_GLBL_CMPY_3_CD = 3;

    /** Length of Item : MDSE_12_CD */
    int LENGTH_MDSE_12_CD = 12;

    // Message string
    /** Message string : Global Company Code */
    String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Process Code */
    String MSG_STR_PROC_CODE = "Process Code(VAR_USER1)";

    /** Message string : Interface ID */
    String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Message string : Commit Count */
    String MSG_STR_COMMIT_COUNT = "Commit Count(VAR_USER3)";

//START ADD S.Yoshida [Def.3149]
    /** Message string : Commit Count */
    String MSG_INCL_TECH_INVTY_FLG = "Technician Inventory included Flag in SCMDB IF(VAR_CHAR_CONST)";
//END   ADD S.Yoshida [Def.3149]

    /** Delimiter of List String : COMMA */
    String DELIMITER = ",";

    /** Fetch Size */
    int FETCH_SIZE = 1000;

    /** Max Value of Work DB Qty(999,999,999) */
    BigDecimal MAX_WRK_DB_QTY = new BigDecimal(999999999);

    /** Minimam Value of Work DB Qty(-999,999,999) */
    BigDecimal MIN_WRK_DB_QTY = new BigDecimal(-999999999);

    // START 2016/07/01 T.Kikuhara V2.0
    /** MDSE_INVTY_COST_PCT 100% */
    BigDecimal COST_PCT_100 = new BigDecimal(100);
    // END 2016/07/01 T.Kikuhara V2.0

    // START 2014/12/19 T.Kanasaka [CCH-QC200 DEL]
//    /** VND_CD_CANON_INC */
//    String VND_CD_CANON_INC = DLXSceConst.CANON_INC;
    // END 2014/12/19 T.Kanasaka [CCH-QC200 DEL]

    /** VND_CD_OTHER */
    String VND_CD_OTHER = "ZZZZZ";

    // START ADD Y.Imazu [ITG.508447]
    /** Direct Ship Warehouse Code */
    String DRCT_SHIP_WH_CD = "DRCT_SHIP_WH_CD";

    /** Non-ship Warehouse Code */
    String NON_SHIP_WH_CD = "NON_SHIP_WH_CD";
    // END   ADD Y.Imazu [ITG.508447]

    // START 2014/12/19 T.Kanasaka [CCH-QC200 ADD]
    /** VAR_CHAR_CONST_NM: SCM_DB_IF_CINC_VND_CD */
    String SCM_DB_IF_CINC_VND_CD = "SCM_DB_IF_CINC_VND_CD";
    // END 2014/12/19 T.Kanasaka [CCH-QC200 ADD]
}
