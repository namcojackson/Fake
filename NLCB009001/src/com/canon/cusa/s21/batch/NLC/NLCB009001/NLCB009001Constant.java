/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB009001;
/**
 * <pre>
 * Custom Status
 *</pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/11/2011   CSAI            M.Takahashi     Create          N/A      
 * 10/23/2013   Fujitsu         A.Wada          Update          Def#349  
 * 12/04/2013   Hitachi         T.Aoyagi        Update          QC2852
 * 06/12/2014   Fujitsu         Y.Taoka         Update          WDS-NA#401
 *</pre>
 */
public interface NLCB009001Constant {

    /** Program ID */
   String NLCB009001 = "NLCB009001";

    /** Table ID : INVTY_DISCR_RPT_WRK_SQ */
   String TBL_ID_INVTY_DISCR_RPT_WRK = "INVTY_DISCR_RPT_WRK";

    /** Table ID : NLCI0050_01 */
//   String TBL_ID_NLCI0050_01 = "NLCI0050_01";

    /** Table ID : NLCI0110_01 */
 //  String TBL_ID_NLCI0110_01 = "NLCI0110_01";

    /** Column Name : GLBL_CMPY_CD */
   String COL_NM_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column Name : INTERFACE_ID */
//   String COL_NM_INTERFACE_ID = "INTERFACE_ID";

    /** Column Name : TRANSACTION_ID */
//   String COL_NM_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column Name : FIRST_PROD_CTRL_CD */
   String COL_NM_PROD_LINE = "PROD_LINE";

    /** Column Name : MDSE_CD */
   String COL_NM_MDSE_CD = "MDSE_CD";

    /** Column Name : INVTY_QTY */
   String COL_NM_INVTY_QTY = "INVTY_QTY";

    /** Column Name : INVTY_LOC_CD */
   String COL_NM_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Column Name : SEQ_NUM */
//   String COL_NM_SEQ_NUM = "SEQ_NUM";

    /** Column Name : SEGMENT_ID */
//   String COL_NM_SEG_ID = "SEGMENT_ID";

    /** Column Name : UNIT_ID */
//   String COL_NM_UNIT_ID = "UNIT_ID";

    /** Column Name : STK_STS_CD */
   String COL_NM_STK_STS_CD = "STK_STS_CD";

    /** Column Name : DISCR_RPT_WRK_PK */
   String COL_NM_INVTY_DISCR_RPT_WRK_PK = "INVTY_DISCR_RPT_WRK_PK";

    /** Column Name : DISCR_RPT_WRK_PK */
   String COL_NM_INVTY_DISCR_RPT_CRAT_DT = "INVTY_DISCR_RPT_CRAT_DT";

    /** Column Name : DISCR_RPT_WRK_PK */
   String COL_NM_INVTY_DISCR_RPT_CRAT_TM = "INVTY_DISCR_RPT_CRAT_TM";

    /** Column Name : LOC_STS_CD */
   String COL_NM_LOC_STS_CD = "LOC_STS_CD";

    /** Column Name : INVTY_ORD_NUM */
   String COL_NM_INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** Column Name : INVTY_ORD_LINE_NUM */
   String COL_NM_INVTY_ORD_LINE_NUM = "INVTY_ORD_LINE_NUM";

    /** Column Name : ORD_QTY */
   String COL_NM_ORD_QTY = "ORD_QTY";

    /** Column Name : RELN_QTY */
   String COL_NM_RELN_QTY = "RELN_QTY";

    /** Column Name : DIFF_QTY */
   String COL_NM_DIFF_QTY = "DIFF_QTY";

    /** Column Name : MDSE_NM */
   String COL_NM_MDSE_NM = "MDSE_NM";

   // START 2014/06/12 [WDS-NA#401, ADD]
   //
   /** Column Name for Report : WH_CD */
   String COL_NM_RPT_WH_CD = "WH";

   /** Column Name for Report : FIRST_PROD_CTRL_CD */
   String COL_NM_RPT_PROD_CTRL_CD = "PR";

   /** Column Name for Report : MDSE_CD */
   String COL_NM_RPT_MDSE_CD = "ITEM";

   /** Column Name for Report : MDSE_NM */
   String COL_NM_RPT_MDSE_NM = "DESCRIPTION";

   /** Column Name for Report : STK_STS_CD */
   String COL_NM_RPT_STK_STS_CD = "STK";

   /** Column Name for Report : S21_INVTY_QTY */
   String COL_NM_RPT_INVTY_QTY = "INVTY_QTY";

   /** Column Name for Report : RELN_SYS_INVTY_QTY */
   String COL_NM_RPT_RELN_SYS_INVTY_QTY = "RELATED_QTY";

   /** Column Name for Report : DIFF_INVTY_QTY */
   String COL_NM_RPT_DIFF_INVTY_QTY = "DIFF_QTY";

   /** Column Name for Report : ADJ_INVTY_ORD_NUM */
   String COL_NM_RPT_ADJ_INVTY_ORD_NUM = "ADJ";

   /** Column Name for Report : ADJ_ORD_REF_INVTY_QTY */
   String COL_NM_RPT_ADJ_ORD_REF_INVTY_QTY = "ADJ_QTY";
   // END   2014/06/12 [WDS-NA#401, ADD]

    /** Message ID : The  (@)  was (@) . ResultCount = (@)  */
   String ZZBM0009I = "ZZBM0009I";

    /** Message ID : [@] is not found in the query. */
//   String MSG_ID_ZZBM0034E = "ZZBM0034E";

    /** Message ID :[@] field is mandatory. */
   String ZZM9000E = "ZZM9000E";

    /** Message ID : [@] field requires numeric input only. */
   String ZZM9004E = "ZZM9004E";

    /** Message ID : Key Duplication Error: Table @, Key @ */
   String NLCM0034E = "NLCM0034E";

    /** Message ID : An error occurred in @. */
   String NLCM0047E = "NLCM0047E";

    /** Message ID : DB Access Error. Table[@], Command[@], Key[@] */
   String NLCM0050E = "NLCM0050E";

    /** Message ID : Entered @ is not a target for this process. */
   String NLCM0063E = "NLCM0063E";

    /** Message ID : It cannot be processed due to invalid data.  */
   String NLCM0079E = "NLCM0079E";

    /** Message ID : There is no data.  @: [@] */
   String NLCM0095E = "NLCM0095E";

    /** Message string : Global Company Code */
   String MSG_STR_GLBL_CMPY_CODE = "Global Company Code";

    /** Message string : Discrepancy Report Preservation period */
   String MSG_STR_DISCR_RPT_PRESER_PERIOD = "Preservation period of Discrepancy Report";

    /** Parameter Name : Interface ID */
   String MSG_STR_INTERFACE_ID = "INTERFACE ID";

    /** Parameter Name : Interface ID */
   String MSG_STR_REPORT_ID = "REPORT ID";

    /** Parameter Name : Transaction ID */
   String MSG_STR_TRANSACTION_ID = "TRANSACTION ID";

    /** Sequence Name : INVTY_DISCR_RPT_WRK_SQ */
   String SEQ_NM_INVTY_DISCR_RPT_WRK_SQ = "INVTY_DISCR_RPT_WRK_SQ";

    /** Report SQL bind key : glblCmpyCd */
   String RPT_SQL_KEY_GLBL_CMPY_CODE = "glblCmpyCd";

    /** Report SQL bind key : procDate */
   String RPT_SQL_KEY_INVTY_DISCR_RPT_CRT_DATE = "procDate";

    /** Report SQL bind key : procTime */
   String RPT_SQL_KEY_INVTY_DISCR_RPT_CRT_TM = "procTime";

    /** Report SQL bind key : invtyLocCd */
   String RPT_SQL_KEY_INVTY_LOC_CD = "invtyLocCd";

    /** Report Branch No */
   String RPT_BRANCH_NO = "01";

    /** Check for Interface ID : NLCI0050 */
   String CHK_NLCI0050 = "NLCI0050";

    /** Check for Interface ID : NLCI0110 */
   String CHK_NLCI0110 = "NLCI0110";

    /** Time pattern : HHmmss */
   String TM_PTRN_HHMMSS = "HHmmss";

    /** Fetch size for purge */
   int FETCH_SIZE = 1000;

    /** Zero */
   int NUM_ZERO = 0;

    /** Mail Template ID */
   String MAIL_TEMPLATE_ID = "NLCB0090M001";

    /** Mail Group ID (FROM) */
   String MAIL_FROM_GROUP_ID = "FROM0003";

    /** Mail Key 1 (FROM) */
   // START 2013/10/23 A.Wada [Def#349, MOD]
   //String MAIL_FROM_KEY_1 = "NLC";
   String MAIL_FROM_KEY_1 = "NLC";
   // END   2013/10/23 A.Wada [Def#349, MOD]

    /** Mail Group ID (TO) */
   String MAIL_TO_GROUP_ID = "NLCB0090";

    /** Mail template parameter : WAREHOUSE_CD */
   String MAIL_TMPL_PRM_WH_CD = "WAREHOUSE_CD";

    /** Mail template parameter : REPORT_ID */
   String MAIL_TMPL_PRM_RPT_ID = "REPORT_ID";

    /** Mail template parameter : DATE */
   String MAIL_TMPL_PRM_DATE = "DATE";

    /** Mail template parameter : TIME */
   String MAIL_TMPL_PRM_TIME = "TIME";

    /** Mail template parameter : TITLE */
   String MAIL_TMPL_PRM_TITLE = "TITLE";

  // START 2014/06/12 [WDS-NA#401, ADD]
   /** Mail template parameter : BODY */
  String MAIL_TMPL_PRM_BODY = "BODY";
  // END   2014/06/12 [WDS-NA#401, ADD]

    /** Report Header parameter : $WH$ */
   String RPT_HEAD_PRM_WH_CD = "$WH$";
   // START 2013/12/04 T.Aoyagi [QC2852,MOD]
//    /** Report Header : Discrepancy */
//   String RPT_HEADER_DISCREPANCY = "W/H     PR  Item            Description                 SS         S21 Qty        WMS Qty       Diff Qty  Adjustment       Reference";
//
//    /** Report Header : No Discrepancy */
//   String RPT_HEADER_NO_DISCREPANCY = "                                            W/H:" + RPT_HEAD_PRM_WH_CD + "  No Discrepancy";
   /** Half space 2 **/
   String HALF_SPACE_2 = "  ";
   /** Half space 44 **/
   String HALF_SPACE_44 = "                                            ";

   // START 2014/06/12 [WDS-NA#401, MOD]
//   /** VAR_CHAR_CONST parameter : NLCB0090_ML_BODY_TXT_ES */
//   String NLCB0090_ML_BODY_TXT_ES = "NLCB0090_ML_BODY_TXT_ES";
   /** VAR_CHAR_CONST parameter : NLCB0090_ML_BODY_TXT_EN */
   String NLCB0090_ML_BODY_TXT_EN = "NLCB0090_ML_BODY_TXT_EN";
   // END   2014/06/12 [WDS-NA#401, MOD]
   // END 2013/12/04 T.Aoyagi [QC2852,MOD]

    /** Digit of Report time */
   int DIGIT_REPORT_TIME = 6;

    /** Digit of Report time for display */
   int DIGIT_REPORT_TIME_DISP = 4;

    /** VAR_CHAR_CONST DELIMITER*/
   String VAL_DELIMITER = ",";

    /** VAR_CHAR_CONST KEY*/
   String NLCI0110_WH_CD = "NLCB0090_NLCI0110_WH_CD";

    /** VAR_CHAR_CONST KEY*/
   String NLCI0050_WH_CD = "NLCB0090_NLCI0050_WH_CD";
}
