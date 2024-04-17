/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB247001;

import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.VAR_CHAR_CONST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RPT_RQST_HDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RPT_RQST_COND;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DS_IMPT_ORD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_CD_C525;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORD_IMPT_RPT_WRK;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MSG_RPT_RQST_COND;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.CSA_ORD_HDR_VIEW_SNAP;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.CSA_ORD_ITEM_VIEW_SNAP;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MSG_ORD_SRC_REF_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MSG_RPT_RQST_HDR_PK;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MSG_RPT_RQST_HDR_REP_ID_NULL;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FMT_YYYYMMDDHHMMSS;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.COND_SQL_FORMAT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.OTPT_OP_CD_VAL;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.LINE_ONE_AREA;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.LINE_TWO_AREA;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.LINE_THREE_AREA;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.LINE_MSG_AREA;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.LINE_NOTE_AREA;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.CONTR_BIZ_TP_CDS;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NWAM0311E;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NWAM0729E;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NWAM0261E;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NWAM0546E;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_C525_MSG01;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_C525_MSG02;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_C525_MSG03;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_DEAL_APPR_FLAG_Y_MSG01;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_DEAL_APPR_FLAG_Y_MSG02;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_DEAL_APPR_FLAG_Y_MSG03;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_DEAL_APPR_FLAG_N_MSG01;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_ARE_FAX_NAD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_ARE_FAX_GMD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_DEAL_APPR_FLAG_N_MSG02;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_DEAL_APPR_FLAG_N_MSG03;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_DEAL_APPR_FLAG_N_MSG04;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_SUPPLY_INCL_FLAG_Y_MSG05;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_SUPPLY_INCL_FLAG_Y_NOT_ZERO_MSG06;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.NOTE_AREA_SUPPLY_INCL_FLAG_N_NOT_ZERO_MSG05;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_RPT_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_RPT_TITLE;
//import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_PRINT_JOB_NAME;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_RPT_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_RPT_PARAM_BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_RPT_PARAM_OTPT_OP_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_RPT_PARAM_RPT_RQST_HDR_PK;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EIP_RPT_PARAM_INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MAIL_TEMPLATE_BATCH_ID_KEY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MAIL_TEMPLATE_BATCH_NAME_KEY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MAIL_TEMPLATE_ERR_INFO_KEY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MAIL_FROM_ADDR_GRP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MAIL_ADDR_TO_GRP;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MAIL_TEMP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.BATCH_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ERR_MSG_HDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FRM_ERR_MSG_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_RPT_RQST_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_RPT_RQST_HDR_PK;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_RPT_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_COND_VAL_LIST;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_INSTL_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_ISTL_SUB_LOC_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_ROW_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_CONTR_BIZ_TP_CD_LIST;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RPT_RQST_HDR_PK;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RPT_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RPT_RQST_COL_VAL_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RPT_RQST_PHYS_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RPT_RQST_OP_TP_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORD_SRC_REF_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.CPO_ORD_TS;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RTL_DIV_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORD_COORD_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RTL_CUST_PO_DT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_FIRST_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_SCD_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_CTY_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_ST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_POST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_TEL_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_FAX_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_LOC_ATTN_TO_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_FIRST_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_SCD_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_CTY_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_ST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_POST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_TEL_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_FAX_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DLR_APP_FLG;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SPLY_INCL_FLG;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.WTY_MTH;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_FIRST_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_SCD_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_CTY_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_ST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_POST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_TEL_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_FAX_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_FIRST_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_SCD_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_CTY_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_ST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_POST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_TEL_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_FAX_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_FIRST_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_SCD_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_CTY_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_ST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_POST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_TEL_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_FAX_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_TO_LOC_ATTN_TO_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.LOC_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORD_QTY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SHIP_QTY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.BO_QTY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.PRNT_SER_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.WH_FIRST_CUST_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ACTL_SHIP_DT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MDL_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ISTL_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.SVC_DLR_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.EXT_WTY_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.FNDG_DLR_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORIG_DLR_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ORD_TAKE_DLR_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RMV_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MLY_BASE_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.MLY_ADMIN_COMP_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.XS_MTR_COPY_QTY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.XS_MTR_COMP_COPY_QTY;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.XS_MTR_COMP_AMT_RATE;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.RTL_MSG_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.ROSS_ORD_TP_MSG_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.LOC_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB247001.constant.NWAB247001Constant.DB_PARAM_CPO_SRC_TP_CD;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ORD_IMPT_RPT_WRKTMsg;
import business.db.RPT_RQST_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RPT_RQST_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_DIV;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
//import com.canon.cusa.s21.framework.printing.eip.S21PrinterOutputParameter;

/**
 *<pre>
 * NWAB2470:CUSA Retail Order import report Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/17/2016   CITS            K.Ogino         Create          N/A
 * 06/15/2016   SRAA            K.Aratani       Update          QC#9971
 * 06/21/2016   SRAA            K.Aratani       Update          QC#10554
 * 07/13/2017   CITS            T.Tokutomi      Update          QC#19919
 *</pre>
 */
public class NWAB247001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Branch No. */
    private String prBrNo;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** normal count */
    private int normalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** error message List */
    private List<String> errorInfoList = new ArrayList<String>();

    /** isExistsreport */
    private boolean isExistsreport = false;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NWAB247001().executeBatch(NWAB247001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        writeStartLogLn("initRoutine");

        try {
            // S21SsmBatchClient
            this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

            // Get GLBL_CMPY_CD
            this.glblCmpyCd = getGlobalCompanyCode();
            if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
                throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
            }

            // Sales Date
            this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

            // Branch No.
            this.prBrNo = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM, this.glblCmpyCd);
            if (!ZYPCommonFunc.hasValue(this.prBrNo)) {
                throw new S21AbendException(NWAM0261E, new String[] {VAR_CHAR_CONST_NM });
            }
        } finally {
            writeEndLogLn("initRoutine");
        }
    }

    @Override
    protected void mainRoutine() {
        writeStartLogLn("mainRoutine");

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // EIP Report Service
            S21EIPPrintingService service = new S21EIPPrintingService();

            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            // Search Target Report Request Header
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(DB_PARAM_RPT_RQST_PROC_STS_CD, RPT_RQST_PROC_STS.UNPROCESS);
            ps = ssmLlcClient.createPreparedStatement("findReportRequestHeader", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Report Request Condition
                BigDecimal rptRqstHdrPk = rs.getBigDecimal(RPT_RQST_HDR_PK);
                String rptId = rs.getString(RPT_ID);

                if (!ZYPCommonFunc.hasValue(rptId)) {
                    errorCount++;
                    String[] paramArray = new String[] {RPT_RQST_HDR, String.format(MSG_RPT_RQST_HDR_REP_ID_NULL, rptRqstHdrPk) };
                    String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, rptRqstHdrPk, errMsgText));
                    continue;
                }

                // Search Report Request Condition
                List<Map<String, Object>> conditionList = findReportRequestCondition(rptRqstHdrPk, rptId);
                if (!hasValueList(conditionList)) {
                    errorCount++;
                    String[] paramArray = new String[] {RPT_RQST_COND, String.format(MSG_RPT_RQST_HDR_PK, rptRqstHdrPk) };
                    String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, rptRqstHdrPk, errMsgText));
                    continue;
                }

                boolean result = mainProcess(service, rptRqstHdrPk, conditionList);
                if (result) {
                    normalCount++;
                    commit();
                } else {
                    errorCount++;
                    rollback();
                }
            }

            if (this.isExistsreport) {
                // If success, activate Report Processing
                long processPk = service.activateAsyncReportJob();
                writeLogLn("Process PK print : " + processPk);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
            writeEndLogLn("mainRoutine");
        }
    }

    @Override
    protected void termRoutine() {
        writeStartLogLn("termRoutine");

        try {

            if (hasValueList(this.errorInfoList)) {
                // send error mail
                postErrorMail();
            }

            if (0 < errorCount) {
                termCd = TERM_CD.WARNING_END;
            }
            setTermState(termCd, normalCount, errorCount);
        } finally {
            writeEndLogLn("termRoutine");
        }
    }

    // ****************************************************************
    // Main Process
    // ****************************************************************
    private boolean mainProcess(S21EIPPrintingService service, BigDecimal rptRqstHdrPk, List<Map<String, Object>> condList) {
        writeStartLogLn("mainProcess", String.format(MSG_RPT_RQST_HDR_PK, rptRqstHdrPk));

        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updResult = false;
        try {
            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            // Search Import Order
            List<String> condValList = new ArrayList<String>(condList.size());
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            //QC#9971
            ssmParam.put(DB_PARAM_CPO_SRC_TP_CD, CPO_SRC_TP.CUSA_NAD_OR_GMD);
            for (Map<String, Object> map : condList) {
                String rptRqstPhysColNm = (String) map.get(RPT_RQST_PHYS_NM);
                String rptRqstOpTpNm = (String) map.get(RPT_RQST_OP_TP_NM);
                String rptRqstColValTxt = (String) map.get(RPT_RQST_COL_VAL_TXT);
                condValList.add(String.format(COND_SQL_FORMAT, rptRqstPhysColNm, rptRqstOpTpNm, rptRqstColValTxt));
            }
            ssmParam.put(DB_PARAM_COND_VAL_LIST, condValList);
            ps = ssmLlcClient.createPreparedStatement("findDSImportOrder", ssmParam, execParam);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    // Insert bean
                    OrdImptRptWrkBean bean = new OrdImptRptWrkBean();
                    bean.setRptRqstHdrPk(rptRqstHdrPk);
                    bean.setCpoOrdNum(rs.getString(CPO_ORD_NUM));
                    bean.setCpoOrdTs(rs.getString(CPO_ORD_TS));
                    bean.setOrdSrcRefNum(rs.getString(ORD_SRC_REF_NUM));
                    bean.splitOrdSrcRefNum();

                    // Search Header Area
                    Map<String, Object> hdrMap = findCsaOrder(GLBL_CMPY.CANON_USA_INC, "findCsaOrdHeader", bean);
                    if (hdrMap == null) {
                        String[] paramArray = new String[] {CSA_ORD_HDR_VIEW_SNAP, String.format(MSG_ORD_SRC_REF_NUM, bean.getRossCpoOrdNum(), bean.getInstlCd(), bean.getIstlSubLocCd()) };
                        String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
                        writeLogLn(errMsgText);
                        this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, rptRqstHdrPk, errMsgText));
                        //QC#9971
                        //return false;
                        continue;
                    }
                    bean.setHdrMap(hdrMap);

                    // Creating Line1 area to ORD_IMPT_RPT_WRK
                    Map<String, Object> lineOneMap = findCsaOrder(GLBL_CMPY.CANON_USA_INC, "findCsaOrdLineOneArea", bean);
                    if (lineOneMap != null) {
                        bean.setAreaMap(lineOneMap);
                        bean.setAreaName(LINE_ONE_AREA);
                        boolean result = createOrdImptRptWrk(bean);
                        if (!result) {
                            return result;
                        }
                    }

                    // Creating Line2 area to ORD_IMPT_RPT_WRK
                    List<Map<String, Object>> lineTwoList = findCsaOrderList(GLBL_CMPY.CANON_USA_INC, "findCsaOrdLineTwoArea", bean);
                    if (hasValueList(lineTwoList)) {
                        bean.setAreaName(LINE_TWO_AREA);
                        for (Map<String, Object> areaMap : lineTwoList) {
                            bean.setAreaMap(areaMap);
                            boolean result = createOrdImptRptWrk(bean);
                            if (!result) {
                                return result;
                            }
                        }
                    } else {
                        String[] paramArray = new String[] {CSA_ORD_ITEM_VIEW_SNAP, String.format(MSG_ORD_SRC_REF_NUM, bean.getRossCpoOrdNum(), bean.getInstlCd(), bean.getIstlSubLocCd()) };
                        String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
                        writeLogLn(errMsgText);
                        this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, rptRqstHdrPk, errMsgText));
                        //QC#9971
                        //return false;
                        continue;
                    }

                    // Creating Line3 area to ORD_IMPT_RPT_WRK
                    List<Map<String, Object>> lineThreeList = findCsaOrderList(GLBL_CMPY.CANON_USA_INC, "findCsaOrdLineThreeArea", bean);
                    if (hasValueList(lineThreeList)) {
                        bean.setAreaName(LINE_THREE_AREA);
                        for (Map<String, Object> areaMap : lineThreeList) {
                            bean.setAreaMap(areaMap);
                            boolean result = createOrdImptRptWrk(bean);
                            if (!result) {
                                return result;
                            }
                            List<Map<String, Object>> lineThreeList2 = findCsaOrderUsgList(GLBL_CMPY.CANON_USA_INC, 
                            		(BigDecimal)areaMap.get("RCPO_DTL_SQ"), (String)areaMap.get("SO_NUM"), (String)areaMap.get("MDL_NM"));
                            if (hasValueList(lineThreeList2)) {
                                bean.setAreaName(LINE_THREE_AREA);
                                for (Map<String, Object> areaMap2 : lineThreeList2) {
                                    bean.setAreaMap(areaMap2);
                                    boolean result2 = createOrdImptRptWrk(bean);
                                    if (!result2) {
                                        return result2;
                                    }
                                }
                            }
                        }
                    }

                    // Creating Message area to ORD_IMPT_RPT_WRK
                    List<Map<String, Object>> lineMsgList = findCsaOrdMsgArea(GLBL_CMPY.CANON_USA_INC, bean);
                    if (hasValueList(lineMsgList)) {
                        bean.setAreaName(LINE_MSG_AREA);
                        for (Map<String, Object> areaMap : lineMsgList) {
                            bean.setAreaMap(areaMap);
                            boolean result = createOrdImptRptWrk(bean);
                            if (!result) {
                                return result;
                            }
                        }
                    }

                    // Creating Note area to ORD_IMPT_RPT_WRK
                    bean.setAreaName(LINE_NOTE_AREA);
                    boolean result = createOrdImptRptWrk(bean);
                    if (!result) {
                        return result;
                    }
                } while (rs.next());
            } else {
                String[] paramArray = new String[] {DS_IMPT_ORD, String.format(MSG_RPT_RQST_COND, rptRqstHdrPk) };
                String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
                writeLogLn(errMsgText);
                this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, rptRqstHdrPk, errMsgText));
                return false;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
            writeEndLogLn("mainProcess", String.format(MSG_RPT_RQST_HDR_PK, rptRqstHdrPk));
        }

        // EIP Service createReportByAsync
        BigDecimal eipRptRqstPk = createEIPReportRequest(service, rptRqstHdrPk);
        // status update
        updResult = updateRptRqstWrk(rptRqstHdrPk, eipRptRqstPk);

        return updResult;
    }

    // ****************************************************************
    // S21SsmBatchClient DB Access Method
    // ****************************************************************
    private List<Map<String, Object>> findReportRequestCondition(BigDecimal rptRqstHdrPk, String rptId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_RPT_RQST_HDR_PK, rptRqstHdrPk);
        ssmParam.put(DB_PARAM_RPT_ID, rptId);
        return queryObjectList("findReportRequestCondition", ssmParam);
    }

    private List<Map<String, Object>> findCsaOrderList(String gllblCmpyCd, String statementId, OrdImptRptWrkBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, gllblCmpyCd);
        ssmParam.put(DB_PARAM_CPO_ORD_NUM, bean.getRossCpoOrdNum());
        ssmParam.put(DB_PARAM_INSTL_CD, bean.getInstlCd());
        ssmParam.put(DB_PARAM_ISTL_SUB_LOC_CD, bean.getIstlSubLocCd());
        ssmParam.put(DB_PARAM_CONTR_BIZ_TP_CD_LIST, CONTR_BIZ_TP_CDS);
        return queryObjectList(statementId, ssmParam);
    }

    private List<Map<String, Object>> findCsaOrderUsgList(String gllblCmpyCd, BigDecimal rcpoDtlSq, String soNum, String mdlNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, gllblCmpyCd);
        ssmParam.put("rcpoDtlSq", rcpoDtlSq);
        ssmParam.put("soNum", soNum);
        ssmParam.put("mdlNm", mdlNm);
        return queryObjectList("findCsaOrdLineThreeArea2", ssmParam);
    }

    private Map<String, Object> findCsaOrder(String gllblCmpyCd, String statementId, OrdImptRptWrkBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, gllblCmpyCd);
        ssmParam.put(DB_PARAM_CPO_ORD_NUM, bean.getRossCpoOrdNum());
        ssmParam.put(DB_PARAM_INSTL_CD, bean.getInstlCd());
        ssmParam.put(DB_PARAM_ISTL_SUB_LOC_CD, bean.getIstlSubLocCd());
        ssmParam.put(DB_PARAM_CONTR_BIZ_TP_CD_LIST, CONTR_BIZ_TP_CDS);
        ssmParam.put(DB_PARAM_ROW_NUM, 1);
        return queryObject(statementId, ssmParam);
    }

    private List<Map<String, Object>> findCsaOrdMsgArea(String gllblCmpyCd, OrdImptRptWrkBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, gllblCmpyCd);
        ssmParam.put(DB_PARAM_TRX_HDR_NUM, bean.getRossCpoOrdNum());
        return queryObjectList("findCsaOrdMsgArea", ssmParam);
    }

    private List<Map<String, Object>> queryObjectList(String statementId, Map<String, Object> ssmParam) {
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
    }

    private Map<String, Object> queryObject(String statementId, Map<String, Object> ssmParam) {
        return (Map<String, Object>) ssmBatchClient.queryObject(statementId, ssmParam);
    }

    // ****************************************************************
    // Update RPT_RQST_HDR Method
    // ****************************************************************
    private boolean updateRptRqstWrk(BigDecimal rptRqstHdrPk, BigDecimal eipRptRqstPk) {
        RPT_RQST_HDRTMsg tMsg = new RPT_RQST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rptRqstHdrPk, rptRqstHdrPk);

        tMsg = (RPT_RQST_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        if (tMsg == null) {
            String[] paramArray = new String[] {String.format(MSG_RPT_RQST_HDR_PK, rptRqstHdrPk) };
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
            writeLogLn(errMsgText);
            this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, rptRqstHdrPk, errMsgText));
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.eipRptRqstPk, eipRptRqstPk);
        ZYPEZDItemValueSetter.setValue(tMsg.rptRqstProcStsCd, RPT_RQST_PROC_STS.PROCESSED);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            String[] paramArray = new String[] {String.format(MSG_RPT_RQST_HDR_PK, rptRqstHdrPk) };
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0729E, paramArray);
            writeLogLn(errMsgText);
            this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, rptRqstHdrPk, errMsgText));
            return false;
        }
        return true;
    }

    // ****************************************************************
    // Insert ORD_IMPT_RPT_WRK Method
    // ****************************************************************
    private boolean createOrdImptRptWrk(OrdImptRptWrkBean bean) {

        // Header Map
        Map<String, Object> hdrMap = bean.getHdrMap();
        // Area Map
        Map<String, Object> areaMap = bean.getAreaMap();
        // Area Name
        String areaName = bean.getAreaName();

        ORD_IMPT_RPT_WRKTMsg tMsg = new ORD_IMPT_RPT_WRKTMsg();

        // Get ORD_IMPT_RPT_WRK_SQ
        BigDecimal ordImptRptWrkSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_IMPT_RPT_WRK_SQ);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptWrkPk, ordImptRptWrkSq);
        ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.otptOpCd, OTPT_OP_CD_VAL);
        ZYPEZDItemValueSetter.setValue(tMsg.rptRqstHdrPk, bean.getRptRqstHdrPk());
        ZYPEZDItemValueSetter.setValue(tMsg.procDtTxt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, bean.getOrdSrcRefNum());
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, bean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(tMsg.istlFirstCustNm, (String) hdrMap.get(ISTL_LOC_FIRST_CUST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.istlScdCustNm, (String) hdrMap.get(ISTL_LOC_SCD_CUST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.istlFirstLineAddr, (String) hdrMap.get(ISTL_LOC_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.istlCtyAddr, (String) hdrMap.get(ISTL_LOC_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.istlStCd, (String) hdrMap.get(ISTL_LOC_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.istlPostCd, (String) hdrMap.get(ISTL_LOC_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.istlTelNum, (String) hdrMap.get(ISTL_LOC_TEL_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.istlFaxNum, (String) hdrMap.get(ISTL_LOC_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.istlAttnToNm, (String) hdrMap.get(ISTL_LOC_ATTN_TO_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.instlCd, bean.getInstlCd());
        ZYPEZDItemValueSetter.setValue(tMsg.istlSubLocCd, bean.getIstlSubLocCd());
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdDtTxt, bean.getCpoOrdTs());
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCd, (String) hdrMap.get(SVC_DLR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrFirstCustNm, (String) hdrMap.get(SVC_DLR_FIRST_CUST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrScdCustNm, (String) hdrMap.get(SVC_DLR_SCD_CUST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrFirstLineAddr, (String) hdrMap.get(SVC_DLR_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCtyAddr, (String) hdrMap.get(SVC_DLR_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrStCd, (String) hdrMap.get(SVC_DLR_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrPostCd, (String) hdrMap.get(SVC_DLR_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrTelNum, (String) hdrMap.get(SVC_DLR_TEL_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrFaxNum, (String) hdrMap.get(SVC_DLR_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlDivCd, (String) hdrMap.get(RTL_DIV_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ordCoordNm, (String) hdrMap.get(ORD_COORD_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, (String) hdrMap.get(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDtTxt, (String) hdrMap.get(RTL_CUST_PO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.rossOrdTpMsgTxt, (String) hdrMap.get(ROSS_ORD_TP_MSG_TXT));
        if (LINE_ONE_AREA.equals(areaName)) {
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrCd, (String) areaMap.get(FNDG_DLR_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrFirstCustNm, (String) areaMap.get(FNDG_DLR_FIRST_CUST_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrScdCustNm, (String) areaMap.get(FNDG_DLR_SCD_CUST_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrFirstLineAddr, (String) areaMap.get(FNDG_DLR_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrCtyAddr, (String) areaMap.get(FNDG_DLR_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrStCd, (String) areaMap.get(FNDG_DLR_ST_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrPostCd, (String) areaMap.get(FNDG_DLR_POST_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrTelNum, (String) areaMap.get(FNDG_DLR_TEL_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrFaxNum, (String) areaMap.get(FNDG_DLR_FAX_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrCd, (String) areaMap.get(ORIG_DLR_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrFirstCustNm, (String) areaMap.get(ORIG_DLR_FIRST_CUST_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrScdCustNm, (String) areaMap.get(ORIG_DLR_SCD_CUST_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrFirstLineAddr, (String) areaMap.get(ORIG_DLR_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrCtyAddr, (String) areaMap.get(ORIG_DLR_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrStCd, (String) areaMap.get(ORIG_DLR_ST_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrPostCd, (String) areaMap.get(ORIG_DLR_POST_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrTelNum, (String) areaMap.get(ORIG_DLR_TEL_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.origDlrFaxNum, (String) areaMap.get(ORIG_DLR_FAX_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstCustNm, (String) areaMap.get(SHIP_TO_LOC_FIRST_CUST_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToScdCustNm, (String) areaMap.get(SHIP_TO_LOC_SCD_CUST_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, (String) areaMap.get(SHIP_TO_LOC_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, (String) areaMap.get(SHIP_TO_LOC_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToStCd, (String) areaMap.get(SHIP_TO_LOC_ST_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, (String) areaMap.get(SHIP_TO_LOC_POST_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToTelNum, (String) areaMap.get(SHIP_TO_LOC_TEL_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToFaxNum, (String) areaMap.get(SHIP_TO_LOC_FAX_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToAttnTxt, (String) areaMap.get(SHIP_TO_LOC_ATTN_TO_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNum, (String) areaMap.get(LOC_NUM));
        } else if (LINE_TWO_AREA.equals(areaName)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd_01, (String) areaMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdlNm_01, (String) areaMap.get(MDL_NM));
            if (ZYPCommonFunc.hasValue((BigDecimal) areaMap.get(ORD_QTY))) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, ZYPCommonFunc.toCommaHensyu(String.valueOf(areaMap.get(ORD_QTY)), false));
            }
            if (ZYPCommonFunc.hasValue((BigDecimal) areaMap.get(SHIP_QTY))) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyShipTxt, ZYPCommonFunc.toCommaHensyu(String.valueOf(areaMap.get(SHIP_QTY)), false));
            }
            if (ZYPCommonFunc.hasValue((BigDecimal) areaMap.get(BO_QTY))) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyBoTxt, ZYPCommonFunc.toCommaHensyu(String.valueOf(areaMap.get(BO_QTY)), false));
            }
            ZYPEZDItemValueSetter.setValue(tMsg.serNum, (String) areaMap.get(SER_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.prntSerNum, (String) areaMap.get(PRNT_SER_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.soNum, (String) areaMap.get(SO_NUM));
            if (ZYPCommonFunc.hasValue((String) areaMap.get(ACTL_SHIP_DT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.actlShipDtTxt, ZYPDateUtil.formatEzd8ToDisp((String) areaMap.get(ACTL_SHIP_DT)));
            }
            ZYPEZDItemValueSetter.setValue(tMsg.whNm, (String) areaMap.get(LOC_NM));
        } else if (LINE_THREE_AREA.equals(areaName)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd_02, (String) areaMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdlNm_02, (String) areaMap.get(MDL_NM));
            if (ZYPCommonFunc.hasValue((String) areaMap.get(ISTL_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtIstlCompTxt, (String) areaMap.get(ISTL_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(SVC_DLR_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtSvcDlrCompTxt, (String)areaMap.get(SVC_DLR_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(EXT_WTY_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtExtWtyCompTxt, (String)areaMap.get(EXT_WTY_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(FNDG_DLR_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtFndgDlrCompTxt, (String)areaMap.get(FNDG_DLR_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(ORIG_DLR_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtOrigDlrCompTxt, (String)areaMap.get(ORIG_DLR_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(ORD_TAKE_DLR_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtOrdTakeDlrCompTxt, (String)areaMap.get(ORD_TAKE_DLR_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(RMV_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtRmvCompTxt, (String)areaMap.get(RMV_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(MLY_BASE_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtMlyBaseCompTxt, (String)areaMap.get(MLY_BASE_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(MLY_ADMIN_COMP_AMT))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtMlyAdminCompTxt, (String)areaMap.get(MLY_ADMIN_COMP_AMT));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(XS_MTR_COPY_QTY))) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyMlyCopyInclCompTxt, (String)areaMap.get(XS_MTR_COPY_QTY));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(XS_MTR_COMP_COPY_QTY))) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyXsMtrCompCopyTxt, (String)areaMap.get(XS_MTR_COMP_COPY_QTY));
            }
            if (ZYPCommonFunc.hasValue((String) areaMap.get(XS_MTR_COMP_AMT_RATE))) {
                ZYPEZDItemValueSetter.setValue(tMsg.amtRateXsMtrCompTxt, (String)areaMap.get(XS_MTR_COMP_AMT_RATE));
            }
        } else if (LINE_MSG_AREA.equals(areaName)) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptMsgTxt, (String) areaMap.get(RTL_MSG_TXT));
        } else if (LINE_NOTE_AREA.equals(areaName)) {
            setNoteArea(tMsg, hdrMap);
        }

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            String[] paramArray = new String[] {ORD_IMPT_RPT_WRK, String.format(MSG_ORD_SRC_REF_NUM, bean.getRossCpoOrdNum(), bean.getInstlCd(), bean.getIstlSubLocCd()) };
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0546E, paramArray);
            writeLogLn(errMsgText);
            writeLogLn(String.format(MSG_ORD_SRC_REF_NUM, bean.getRossCpoOrdNum(), bean.getInstlCd(), bean.getIstlSubLocCd()));
            this.errorInfoList.add(String.format(FRM_ERR_MSG_TXT, bean.getRptRqstHdrPk(), errMsgText));
            return false;
        }
        return true;
    }

    private void setNoteArea(ORD_IMPT_RPT_WRKTMsg tMsg, Map<String, Object> hdrMap) {

        String svcDlrCd = (String) hdrMap.get(SVC_DLR_CD);
        if (ZYPCommonFunc.hasValue(svcDlrCd) && SVC_DLR_CD_C525.equals(svcDlrCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_01, NOTE_AREA_C525_MSG01);
            ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_02, NOTE_AREA_C525_MSG02);
            ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_03, NOTE_AREA_C525_MSG03);
        } else {
            // other
            String dlrAppFlg = (String) hdrMap.get(DLR_APP_FLG);
            if (ZYPConstant.FLG_ON_Y.equals(dlrAppFlg)) {
                ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_01, NOTE_AREA_DEAL_APPR_FLAG_Y_MSG01);
                ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_02, NOTE_AREA_DEAL_APPR_FLAG_Y_MSG02);
                ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_03, NOTE_AREA_DEAL_APPR_FLAG_Y_MSG03);
            } else {
                String rtlDivCd = (String) hdrMap.get(RTL_DIV_CD);
                if (RTL_DIV.NATIONAL_ACCOUNTS.equals(rtlDivCd)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_01, String.format(NOTE_AREA_DEAL_APPR_FLAG_N_MSG01, NOTE_ARE_FAX_NAD));
                } else if (RTL_DIV.GOVERNMENT.equals(rtlDivCd)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_01, String.format(NOTE_AREA_DEAL_APPR_FLAG_N_MSG01, NOTE_ARE_FAX_GMD));
                } else {
                    tMsg.ordImptRptFtrMsgTxt_01.clear();
                }
                ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_02, String.format(NOTE_AREA_DEAL_APPR_FLAG_N_MSG02, (String) hdrMap.get(WH_FIRST_CUST_NM)));
                ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_03, NOTE_AREA_DEAL_APPR_FLAG_N_MSG03);
                ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_04, NOTE_AREA_DEAL_APPR_FLAG_N_MSG04);
                String splyInclFlg = (String) hdrMap.get(SPLY_INCL_FLG);
                String wtyMth = (String) hdrMap.get(WTY_MTH);
                if (ZYPConstant.FLG_ON_Y.equals(splyInclFlg)) {
                    if (ZYPCommonFunc.hasValue(wtyMth) && !ZYPConstant.FLG_OFF_0.equals(wtyMth)) {
                        ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_05, NOTE_AREA_SUPPLY_INCL_FLAG_Y_MSG05);
                        ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_06, NOTE_AREA_SUPPLY_INCL_FLAG_Y_NOT_ZERO_MSG06);
                    } else if (!ZYPCommonFunc.hasValue(wtyMth) || ZYPConstant.FLG_OFF_0.equals(wtyMth)) {
                        ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_05, NOTE_AREA_SUPPLY_INCL_FLAG_Y_MSG05);
                    } else {
                        tMsg.ordImptRptFtrMsgTxt_05.clear();
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(wtyMth) && !ZYPConstant.FLG_OFF_0.equals(wtyMth)) {
                        ZYPEZDItemValueSetter.setValue(tMsg.ordImptRptFtrMsgTxt_05, NOTE_AREA_SUPPLY_INCL_FLAG_N_NOT_ZERO_MSG05);
                    } else {
                        tMsg.ordImptRptFtrMsgTxt_05.clear();
                    }
                }
            }
        }

    }

    // ****************************************************************
    // Create EIP Report Method
    // ****************************************************************
    private BigDecimal createEIPReportRequest(S21EIPPrintingService service, BigDecimal rptRqstHdrPk) {
        writeStartLogLn("createEIPReportRequest");
        try {
            // ********************************
            // Set Report Basic Info
            // ********************************
            S21ReportRequestBean request = new S21ReportRequestBean(EIP_RPT_ID);
            request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
            request.setRptArcFlg(true);
            String rptTtlNm = String.format(EIP_RPT_TITLE, String.valueOf(rptRqstHdrPk), ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS));
            request.setRptTtlNm(rptTtlNm);

            // ********************************
            // Set Report Input Parameter
            // ********************************
            S21InputParameter inputParam = request.getInputParamBeanInstance();
            inputParam.addReportParameter(EIP_RPT_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            inputParam.addReportParameter(EIP_RPT_PARAM_BIZ_APP_ID, BIZ_APP_ID);
            inputParam.addReportParameter(EIP_RPT_PARAM_OTPT_OP_CD, OTPT_OP_CD_VAL);
            inputParam.addReportParameter(EIP_RPT_PARAM_RPT_RQST_HDR_PK, rptRqstHdrPk);
            inputParam.addReportParameter(EIP_RPT_PARAM_INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
            request.setInputParamBean(inputParam);

            // ********************************
            // Set Print out option
            // ********************************
            //S21PrinterOutputParameter printOutParam = request.getPrintOutParamBeanInstance();
            //printOutParam.setBranchNo(this.prBrNo);
            //printOutParam.setPrintJobName(String.format(EIP_PRINT_JOB_NAME, String.valueOf(rptRqstHdrPk), ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS)));
            //request.setPrintOutParamBean(printOutParam);

            long eipRptRqstPk = service.createReportByAsync(request);
            // set activate Report Processing Flag
            this.isExistsreport = true;
            writeLogLn("Request PK for print : " + eipRptRqstPk);
            return BigDecimal.valueOf(eipRptRqstPk);
        } finally {
            writeEndLogLn("createEIPReportRequest");
        }
    }

    // ****************************************************************
    // Post Error Mail Method
    // ****************************************************************
    private void postErrorMail() {
        writeStartLogLn("postErrorMail");

        try {
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_FROM_ADDR_GRP_ID);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (!hasValueList(addrFromList)) {
                return;
            }

            S21MailAddress from = addrFromList.get(0);

            // *****************************************************************
            // Deriving To Mail Address
            // *****************************************************************
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_ADDR_TO_GRP);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (!hasValueList(addrToList)) {
                return;
            }

            // *****************************************************************
            // Create Mail Body
            // *****************************************************************
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMP_ID);
            if (template == null) {
                return;
            }
            template.setTemplateParameter(MAIL_TEMPLATE_BATCH_ID_KEY, this.getClass().getSimpleName());
            template.setTemplateParameter(MAIL_TEMPLATE_BATCH_NAME_KEY, BATCH_NM);
            String errMsg = getMailBodyErrMsg();
            template.setTemplateParameter(MAIL_TEMPLATE_ERR_INFO_KEY, errMsg);

            // *****************************************************************
            // Post mail
            // *****************************************************************
            S21Mail mail;
            for (S21MailAddress addr : addrToList) {
                mail = new S21Mail(this.glblCmpyCd);
                mail.setFromAddress(from);
                mail.setToAddress(addr);
                mail.setMailTemplate(template);
                mail.postMail();
            }
        } finally {
            writeEndLogLn("postErrorMail");
        }
    }

    private String getMailBodyErrMsg() {
        StringBuilder sb = new StringBuilder();
        sb.append(ERR_MSG_HDR);
        sb.append("\n");

        for (String message : this.errorInfoList) {
            sb.append(message);
            sb.append("\n");
        }

        return sb.toString();
    }

    // ****************************************************************
    // Data Check Method
    // ****************************************************************
    private static <T extends List<?>> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    // ****************************************************************
    // Output Log Method
    // ****************************************************************
    private static void writeStartLogLn(String methodNm) {
        writeLogLn("[START] %s", methodNm);
    }

    private static void writeStartLogLn(String methodNm, String msg) {
        writeLogLn("[START] %s(%s)", methodNm, msg);
    }

    private static void writeEndLogLn(String methodNm) {
        writeLogLn("[END] %s\r\n", methodNm);
    }

    private static void writeEndLogLn(String methodNm, String msg) {
        writeLogLn("[END] %s(%s)\r\n", methodNm, msg);
    }

    private static void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", BIZ_APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }
}
