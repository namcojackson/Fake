/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NPA.NPAB125001;

import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ACTL_ARV_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ACTL_SHIP_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ASL_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.AUTO_LIC_ALLOC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_ASN_REQ_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_BILL_TO_CUST_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CPO_ORD_TP_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_EDI_CUST_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_EDI_TRD_PTNR_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_ETA_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_EXPT_BOL_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_EXPT_INV_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_LAK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_ORD_TAKE_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_PO_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_PO_REQ_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_PROC_PGM_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_CODE_CUST_SHIP_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_CODE_CUST_SOLD_TO_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_CODE_STAT_INV;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_CODE_STAT_SO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_CODE_TP_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_CODE_TRANSACT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_CODE_WH;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_NUM_CUST_PO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_NUM_SO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_PARTS_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_P_QTY_SHIP;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_REV_RECOG_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_ROWNUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SELL_TO_CUST_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SER_TAKE_DT_TM_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SET_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SHIP_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SHPG_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SHPG_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SO_CLOSE_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SO_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_RSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_SRC_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BOL_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CODE_TRANSACT_10;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CONF_DTL_BOL_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CONF_DTL_PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CONF_DTL_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CPO_DTL_HLD_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CPO_DTL_LINE_SUB_NUM_000;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CPO_HLD_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CPO_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CUST_ISS_PO_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DB_RETURN_CD_NORMAL_END;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DELIMITER;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DELIVERY_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DS_BIZ_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DS_BIZ_PROC_LOG_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DS_BIZ_PROC_LOG_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.EDI_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.EDI_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.EDI_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.EDI_SUB_NUM_001;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.FROM_STK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.FRT_CHRG_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.FRT_CHRG_TO_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.IF_ID_NPAA0051;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.IF_ID_NPAA0061;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ILMS_LIC_ACCS_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ILMS_LIC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.INVTY_CTRL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.LIC_ACCS_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.MDSE_CD_UPD_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.MODE_DAILY;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.MODE_NIGHT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.MODE_PARTS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.MODE_WS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1003E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1173E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1312E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1496E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1497E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1499E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1500E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1505E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NULL_VAL;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.PO_REQ_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.PO_SER_NUM_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_AMT_INVO_FRT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_CODE_CUST_CHRG_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_CODE_CUST_SHIP_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_CODE_CUST_SOLD_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_CODE_WH_SHIP;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_DATE_SHIP_PRSS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_NUM_CUST_PO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_NUM_FEDX_CONTROL;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_NUM_INVOICE;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_NUM_LINE;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_NUM_SO;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_NUM_TRUCKER;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_PARTS_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.P_QTY_SHIP;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.REV_RECOG_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ROW_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SELL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SER_EVENT_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SER_TAKE_DT_TM_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SET_ITEM_SHIP_CPLT_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SET_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHIP_CPLT_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHIP_DT_TM_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHIP_FROM_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHIP_PLN_HLD_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHIP_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHPG_MODE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHPG_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SO_CLOSE_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SO_SER_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.STAT_SO_INVOICED;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.STAT_SO_SHIPPED;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.THIRD_PTY_INV_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TIME_FORMAT_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TOT_FRT_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TOT_FRT_AMT_DTL;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TOT_SHIP_WT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TOT_SHIP_WT_DTL;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TRX_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TRX_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.TRX_LINE_SUB_NUM_000;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.VAR_CUSA_EDI_TRD_PTNR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.VAR_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.VAR_CUSA_PRT_EDI_TRD_PTNR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.VAR_DROP_SHIP_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.WH_CD;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.EDI_ASN_DTL_WRKTMsg;
import business.db.EDI_ASN_HDR_WRKTMsg;
import business.db.EDI_ASN_SER_NUM_WRKTMsg;

import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASN_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ELAN_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LAK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAB125001:Receiving ASN for CUSA Domestic
 * 
 * Date         Company      Name             Create/Update    Defect No
 * ---------------------------------------------------------------------
 * 2013/07/29   Hitachi      T.Kanasaka       Create           QC1388
 * 2014/03/18   Hitachi      T.Kanasaka       Update           CSA-028
 * 2016/02/06   CITS         T.Hakodate       All Update       CSA
 * 2016/10/16   CITS         T.Hakodate       Update           QC#14912
 * 2016/11/11   CITS         M.Naito          Update           CSA
 * 2016/12/15   CITS         T.Hakodate       Update           QC#7523
 * 2017/01/12   CITS         K.Kameoka        Update           QC#17077
 * 2017/02/10   CITS         T.Hakodate       Update           QC#17531
 * 2017/03/05   CITS         T.Hakodate       Update           QC#17960
 * 2017/07/06   CITS         K.Ogino          Update           QC#18182
 * 2017/08/21   CITS         K.Ogino          Update           QC#20681
 * 2017/09/04   CITS         K.Ogino          Update           QC#20878
 * 2018/05/17   CITS         K.Ogino          Update           QC#25508
 * 2018/07/25   CITS         T.hakodate       Update           QC#25246
 * 2019/02/08   Fujitsu      T.Ogura          Update           QC#30278
 * 2019/02/21   CITS         M.Naito          Update           QC#30311
 * 2019/02/22   CITS         M.Naito          Update           QC#30452
 * 2019/04/16   CITS         A.Kobayashi      Update           QC#31144
 * 2019/05/27   CITS         M.Naito          Update           QC#50282
 * 2019/08/13   CITS         K.Ogino          Update           QC#52012
 * 2019/08/15   CITS         K.Ogino          Update           QC#52677
 * 2019/08/26   CITS         R.Shimamoto      Update           QC#52580
 * 2021/04/16   CITS         K.Ogino          Update           QC#58256
 * <pre>
 */
public class NPAB125001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Cusa Global Company Code */
    private String cusaGlblCmpyCd = null;

    /** shpgStatusCode */
    private String shpgStatusCode = null;

    /** cpoOrdTpCd */
    private String cpoOrdTpCd = null;

    /** trdPtnrCdList */
    private ArrayList<String> trdPtnrCdList = new ArrayList<String>();

    /** Interface Id */
    private String interfaceId = null;

    /** userVariable1 */
    private String userVariable1 = null;

    /** processMode. */
    private String processMode = null;

    /** bizProcLogPk. */
    private BigDecimal dsBizProcLogPk = null;

    /** dsBizLastUpdTs. */
    private String dsBizLastUpdTs = null;

    /** dummyWhCd. */
    private String dummyWhCd = null;

    /** Shpg Ord Inf Cache */
    private Map<String, List> shippingOrderInfoCache = new HashMap<String, List>();

    /** Cache */
    private Map<String, List<String>> revenueRecognitionMethodCodeCache = new HashMap<String, List<String>>();

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Calc Delivery Date */
    private CalcDeliveryDate calcDeliveryDate = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** total correct count */
    private int totalCorrectCount = 0;

    /** total Execute count */
    private int totalExecuteCount = 0;

    /** total error count */
    private int totalErrorCount = 0;

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NPAB125001().executeBatch(NPAB125001.class.getSimpleName());

    }

    protected void initRoutine() {

        // term code
        termCd = TERM_CD.NORMAL_END;

        // Initialization
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // DeliveryDate
        calcDeliveryDate = new CalcDeliveryDate(profileService, S21SsmBatchClient.getClient(this.getClass()));

        // Get the Global Company Code.
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {GLBL_CMPY_CD });
        }

        // Get Interface Id
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NPAM1173E, new String[] {INTERFACE_ID });
        }

        // QC#7523 START
        // Get userVariable1
        // 1 : Daily mode
        // 2 : Night mode
        userVariable1 = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(userVariable1)) {
            userVariable1 = "1";
        }
        // QC#7523 END

        // Get salesDate
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAM1173E, new String[] {SALES_DATE });
        }

        // init S21SsmBatchClient
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    protected void mainRoutine() {

        // Set the fetch size.
        S21SsmExecutionParameter execParam = null;
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);

        if (IF_ID_NPAA0051.equals(interfaceId)) {

            processMode = "1";
            getLastUpdateTs();

            // QC#31144 inform the time for processing data from last task (this message can be changed)
            S21InfoLogOutput.println("TARGET DATA : " + dsBizLastUpdTs);


            receivingAsnFromWs(execParam);

        } else if (IF_ID_NPAA0061.equals(interfaceId)) {

            processMode = "2";
            getLastUpdateTs();

            // QC#31144 inform the time for processing data from last task (this message can be changed)
            S21InfoLogOutput.println("TARGET DATA : " + dsBizLastUpdTs);

            receivingAsnFromParts(execParam);

        }

        updateDsBizProcLog();

        // QC#31144 inform the time to finish and next time processing (this message can be changed)
        S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + dsBizLastUpdTs);
    }

    protected void termRoutine() {

        if (errMsgList.size() == 0) {

            termCd = TERM_CD.NORMAL_END;

        } else {

            sendEMailToAdmin();

            termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.totalCorrectCount, this.totalErrorCount, this.totalExecuteCount);
    }

    /**
     * getLastUpdateTs
     */
    private void getLastUpdateTs() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PROC_PGM_ID, BUSINESS_ID + processMode);

        List<Map<String, Object>> lastUpdateTsList = ssmBatchClient.queryObjectList("getLastUpdateTs", ssmParam);

        if (lastUpdateTsList == null || lastUpdateTsList.isEmpty()) {

            dsBizProcLogPk = null;
            dsBizLastUpdTs = "00000000";

        } else {

            Map<String, Object> lastUpdateTsMap = lastUpdateTsList.get(0);
            dsBizProcLogPk = (BigDecimal) lastUpdateTsMap.get(DS_BIZ_PROC_LOG_PK);
            dsBizLastUpdTs = (String) lastUpdateTsMap.get(DS_BIZ_LAST_UPD_TS);

        }

    }

    /**
     * updateDsBizProcLog
     */
    private void updateDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg outMsg = new DS_BIZ_PROC_LOGTMsg();

        if (!ZYPCommonFunc.hasValue(dsBizProcLogPk)) {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat(processMode));
            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, dsBizLastUpdTs);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(DS_BIZ_PROC_LOG_SQ));

            EZDTBLAccessor.insert(outMsg);

        } else {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, dsBizProcLogPk);
            outMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat(processMode));
            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, dsBizLastUpdTs);

            EZDTBLAccessor.update(outMsg);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append(outMsg.getTableName());

            rollback();

            termCd = TERM_CD.ABNORMAL_END;

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {sb.toString() }));

        } else {

            commit();
            // rollback();

        }

    }

    /**
     * receivingAsnFromWs
     * @param execParam
     */
    private void receivingAsnFromWs(S21SsmExecutionParameter execParam) {

        getVarCharConstValue();

        // get Bill To/Sell To
        // ********************************************************************
        Map<String, List> groupCompanyList = getGroupCompany(trdPtnrCdList);

        if (groupCompanyList.size() == 0) {
            termCd = TERM_CD.WARNING_END;
            return;
        }

        // Get ASN Record.
        // ********************************************************************
        List<Map> asnRecordList = getAsnRecordList(groupCompanyList, execParam);
        if (asnRecordList.size() == 0) {
            return;
        }

        List<String> poSerNumPkList = new ArrayList<String>();

        int lineNum = 1;

        String prevSoNum = null;

        // Insert ASN Record.
        while (asnRecordList.size() != 0) {

            List<Map> asnDataBySo = getAsnDataBySO(asnRecordList);
            String soNum = (String) asnDataBySo.get(0).get(SO_NUM);

            totalExecuteCount++;

            // if (checkShipingStatusCode(asnDataBySo)) {

            List<Map> serialInfoList = getSerialInformation(asnDataBySo, poSerNumPkList);

            boolean isSkip = true;

            String rtnmsg = calcDeliveryDate.calcDeliveryDate(asnDataBySo);

            if (ZYPCommonFunc.hasValue(rtnmsg)) {
                totalErrorCount++;
                prevSoNum = soNum;
                addMailMessageToAdminForImport(rtnmsg, soNum, S21MessageFunc.clspGetMessage(rtnmsg, new String[] {soNum }));
                continue;
            }

            // checkOriginalMdseCd(asnDataBySo);

            isSkip = checkVendorDropShipWs(asnDataBySo.get(0));

            if (!isSkip) {
                totalErrorCount++;
                prevSoNum = soNum;
                rollback();
                addMailMessageToAdminForImport(NPAM1312E, soNum, S21MessageFunc.clspGetMessage(NPAM1312E, new String[] {soNum }));
                continue;
            }

            isSkip = insertEdiAsnHdrWrk(asnDataBySo.get(0));

            if (!isSkip) {
                totalErrorCount++;
                prevSoNum = soNum;
                rollback();
                addMailMessageToAdminForImport(NPAM1312E, soNum, S21MessageFunc.clspGetMessage(NPAM1312E, new String[] {soNum }));
                continue;
            }

            if (ZYPCommonFunc.hasValue(prevSoNum)) {

                if (!soNum.equals(prevSoNum)) {

                    lineNum = 1;

                }

            }

            for (Map<String, String> asnRecord : asnDataBySo) {

                asnRecord = setMdseItemCheck(asnRecord);

                // START 2019/05/27 M.Naito [QC#50282,MOD]
//              isSkip = insertEdiAsnDtlWrk(asnRecord, lineNum);
                BigDecimal asnTotFrtAmt = null;
                if (ZYPCommonFunc.hasValue(asnRecord.get(TOT_FRT_AMT))) {
                    if (lineNum == 1) {
                        asnTotFrtAmt = new BigDecimal(asnRecord.get(TOT_FRT_AMT));
                    } else {
                        asnTotFrtAmt = BigDecimal.ZERO;
                    }
                }
                isSkip = insertEdiAsnDtlWrk(asnRecord, lineNum, asnTotFrtAmt);
                // END 2019/05/27 M.Naito [QC#50282,MOD]

                if (!isSkip) {

                    break;

                }

                lineNum++;
            }

            // intangble DELIVERY_DATE
            updateEdiAsnDtlWrk(asnDataBySo);

            if (!isSkip) {
                totalErrorCount++;
                prevSoNum = soNum;
                lineNum = 1;
                rollback();
                addMailMessageToAdminForImport(NPAM1312E, soNum, S21MessageFunc.clspGetMessage(NPAM1312E, new String[] {soNum }));
                continue;
            }

            for (Map<String, String> asnRecord : serialInfoList) {

                isSkip = insertEdiAsnSerNumWrk(asnRecord);

                if (!isSkip) {

                    break;

                }

            }

            if (!isSkip) {
                totalErrorCount++;
                prevSoNum = soNum;
                lineNum = 1;
                rollback();
                addMailMessageToAdminForImport(NPAM1312E, soNum, S21MessageFunc.clspGetMessage(NPAM1312E, new String[] {soNum }));
                continue;
            }

            totalCorrectCount++;
            commit();
            // rollback();

            prevSoNum = soNum;

        }

    }

    /**
     * @param asnRecord
     * @return
     */
    private boolean checkVendorDropShipWs(Map<String, String> asnRecord) {

        boolean returnFlag = true;

        if (ZYPConstant.FLG_ON_Y.equals(asnRecord.get(PO_REQ_FLG))) {

            if (ZYPCommonFunc.hasValue(asnRecord.get(THIRD_PTY_INV_NUM))) {

                asnRecord.put(SHIP_FROM_SO_NUM, asnRecord.get(THIRD_PTY_INV_NUM));

            } else {

                returnFlag = false;
            }

        } else {

            asnRecord.put(SHIP_FROM_SO_NUM, asnRecord.get(SO_NUM));
        }

        return returnFlag;

    }

    /**
     * insertEdiAsnHdrWrk
     * @param asnRecord
     * @return
     */
    private boolean insertEdiAsnHdrWrk(Map<String, String> asnRecord) {

        EDI_ASN_HDR_WRKTMsg insertTMsg = new EDI_ASN_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoNum, asnRecord.get(SO_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, asnRecord.get(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.sellToCustCd, asnRecord.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, asnRecord.get(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.vndInvtyLocCd, asnRecord.get(WH_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnShipDtTmTs, asnRecord.get(SHIP_DT_TM_TS));
        if (ZYPCommonFunc.hasValue(asnRecord.get(TOT_SHIP_WT))) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotShipWt, new BigDecimal(asnRecord.get(TOT_SHIP_WT)));
        }
        if (ZYPCommonFunc.hasValue(asnRecord.get(TOT_FRT_AMT))) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotFrtAmt, new BigDecimal(asnRecord.get(TOT_FRT_AMT)));
        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipFromSoNum, asnRecord.get(SHIP_FROM_SO_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.brFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertTMsg.procFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(insertTMsg.ediRcvTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
        if (ZYPCommonFunc.hasValue(interfaceId)) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.itrlIntfcId, this.interfaceId);
        } else {
            ZYPEZDItemValueSetter.setValue(insertTMsg.itrlIntfcId, IF_ID_NPAA0051);
        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.ediPoOrdNum, asnRecord.get(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.cmProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxHdrNum, asnRecord.get(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.vndInvNum, asnRecord.get(SHIP_FROM_SO_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.elanPrintStsCd, ELAN_PRINT_STS.INITIAL);

        boolean comFlag = false;

        if (insert(insertTMsg, asnRecord.get(SO_NUM))) {

            comFlag = true;

            this.dsBizLastUpdTs = insertTMsg.ezUpTime.getValue();

        }

        return comFlag;

    }

    /**
     * @param asnRecord
     * @return
     */
    private Map<String, String> setMdseItemCheck(Map<String, String> asnRecord) {

        String ediLineNum = null;
        String poOrdDtlLineNum = null;

        // init setting
        asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_OFF_N);

        // csa po data does not exists.
        if (!ZYPCommonFunc.hasValue((String) asnRecord.get(CUST_ISS_PO_NUM))) {

            asnRecord.put(EDI_PO_ORD_DTL_LINE_NUM, (String) asnRecord.get(CPO_DTL_LINE_NUM) + (String) asnRecord.get(CPO_DTL_LINE_SUB_NUM));
            asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_ON_Y);
            return asnRecord;

        }

        if (!ZYPCommonFunc.hasValue((String) asnRecord.get(SET_MDSE_CD))) {

            ediLineNum = (String) asnRecord.get(EDI_NUM);

            if (ZYPCommonFunc.hasValue(ediLineNum)) {

                itemChangeCheck(asnRecord);

            } else {

                asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_ON_Y);
            }

        } else {

            // step 2
            // search csa cpo dtl
            String cusaEdiNum = searchCsaCpoDtl((String) asnRecord.get(CPO_ORD_NUM), (String) asnRecord.get(CPO_DTL_LINE_NUM));

            if (ZYPCommonFunc.hasValue(cusaEdiNum)) {

                // step 3
                // search csa set parent
                poOrdDtlLineNum = searchPoOrdDtlLineNum((String) asnRecord.get(CUST_ISS_PO_NUM), cusaEdiNum);

            }

            List<Map<String, Object>> aslMdseList = null;

            if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {

                // step 4
                // search csa set comp list
                aslMdseList = searchAslMdseCd((String) asnRecord.get(CUST_ISS_PO_NUM), poOrdDtlLineNum);

            }

            if (aslMdseList != null) {

                asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_ON_Y);

                boolean mdseMatched = false;

                for (int j = 0; j < aslMdseList.size(); j++) {

                    Map<String, Object> aslMdseMap = aslMdseList.get(j);

                    // step 5 mdse code mapping
                    if (((String) asnRecord.get(MDSE_CD)).equals((String) aslMdseMap.get(ASL_MDSE_CD))) {

                        ediLineNum = (String) aslMdseMap.get(PO_ORD_DTL_LINE_NUM);

                        asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_OFF_N);

                        mdseMatched = true;

                        break;

                    }

                }

                if (!mdseMatched) {

                    for (int i = 0; i < aslMdseList.size(); i++) {

                        Map<String, Object> aslMdseMap = aslMdseList.get(i);

                        // step 5 8 digit mdse code
                        // mapping

                        if (mappingWithOrdTakeMdse(((String) asnRecord.get(MDSE_CD)), (String) aslMdseMap.get(ASL_MDSE_CD))) {

                            ediLineNum = (String) aslMdseMap.get(PO_ORD_DTL_LINE_NUM);

                            // QC#20681 Start
//                          asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_OFF_N);
                            // QC#20681 End

                            break;

                        }

                    }
                }

            }

            if (!ZYPCommonFunc.hasValue(ediLineNum)) {

                ediLineNum = (String) asnRecord.get(CPO_DTL_LINE_NUM) + (String) asnRecord.get(CPO_DTL_LINE_SUB_NUM);

                // QC#25246 MOD START
                //asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_ON_Y);
                asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_OFF_N);
                // QC#25246 MOD END

            }

        }

        asnRecord.put(EDI_PO_ORD_DTL_LINE_NUM, ediLineNum);

        return asnRecord;

    }

    /**
     * @param ordNum
     * @param lineNum
     * @return
     */
    private String searchCsaCpoDtl(String ordNum, String lineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_CPO_ORD_NUM, ordNum);
        ssmParam.put(BIND_CPO_DTL_LINE_NUM, lineNum);
        ssmParam.put(BIND_CPO_DTL_LINE_SUB_NUM, CPO_DTL_LINE_SUB_NUM_000);

        String ediNum = (String) ssmBatchClient.queryObject("searchCsaCpoDtl", ssmParam);

        return ediNum;
    }

    /**
     * @param poOrdNum
     * @param ediNum
     * @return
     */
    private String searchPoOrdDtlLineNum(String poOrdNum, String ediNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        // START 2019/02/22 M.Naito [QC#30452,MOD]
//        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);

        if (ZYPCommonFunc.hasValue(poOrdNum)) {
            if (poOrdNum.length() > 8) {
                ssmParam.put(BIND_PO_ORD_NUM, poOrdNum.substring(poOrdNum.length() - 8));
            } else {
                ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
            }
        }
        // END 2019/02/22 M.Naito [QC#30452,MOD]
        if (ZYPCommonFunc.hasValue(ediNum)) {
            if ((ediNum.length() > 3)) {
                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum.substring(ediNum.length() - 3));
            } else {
                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);
            }
        }

        String poOrdDtlLineNum = (String) ssmBatchClient.queryObject("searchPoOrdDtlLineNum", ssmParam);

        return poOrdDtlLineNum;
    }

    /**
     * searchAslMdseCd
     * @param poOrdNum
     * @param ediNum
     * @return aslMdseCdList
     */
    private List<Map<String, Object>> searchAslMdseCd(String poOrdNum, String poOrdDtlLineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        // START 2019/02/22 M.Naito [QC#30452,MOD]
//        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        if (ZYPCommonFunc.hasValue(poOrdNum)) {
            if (poOrdNum.length() > 8) {
                ssmParam.put(BIND_PO_ORD_NUM, poOrdNum.substring(poOrdNum.length() - 8));
            } else {
                ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
            }
        }
        // END 2019/02/22 M.Naito [QC#30452,MOD]
        ssmParam.put(BIND_SET_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);

        List<Map<String, Object>> aslMdseCdList = ssmBatchClient.queryObjectList("searchAslMdseCd", ssmParam);

        return aslMdseCdList;
    }

    /**
     * @param cusaMdseCd
     * @param csaMdseCd
     * @return
     */
    private boolean mappingWithOrdTakeMdse(String cusaMdseCd, String csaMdseCd) {

        boolean mapFlg = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        // QC#17077 Start
        if (csaMdseCd.length() > 8) {
            csaMdseCd = csaMdseCd.substring(0, 8);
        }
        if (cusaMdseCd.length() > 8) {
            cusaMdseCd = cusaMdseCd.substring(0, 8);
        }
        // QC#17077 End

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        // QC#17077 Start
        ssmParam.put(BIND_ORD_TAKE_MDSE_CD, cusaMdseCd);
        // ssmParam.put(BIND_ORD_TAKE_MDSE_CD, csaMdseCd.substring(0,
        // 8));
        // QC#17077 End

        String ordTakeMdseCd = (String) ssmBatchClient.queryObject("searcOrdTakeMdse", ssmParam);

        // QC#17077 Start
        if (ZYPCommonFunc.hasValue(ordTakeMdseCd) && cusaMdseCd.equals(csaMdseCd)) {
            // if (ZYPCommonFunc.hasValue(ordTakeMdseCd) &&
            // cusaMdseCd.substring(0,
            // 8).equals(csaMdseCd.substring(0, 8))) {
            // QC#17077 End

            mapFlg = true;

        }

        return mapFlg;

    }

    /**
     * updateEdiAsnDtlWrk
     * @param asnDataBySo
     * @return
     */
    private boolean updateEdiAsnDtlWrk(List<Map> asnDataBySo) {

        Map<String, Object> asnRecordCache = new HashMap<String, Object>();

        for (Map<String, String> asnRecord : asnDataBySo) {

            String asnSoNum = (String) asnRecord.get("SO_NUM");

            if (asnRecordCache.containsKey(asnSoNum)) {
                continue;
            }

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("asnSoNum", asnSoNum);

            List<Map<String, String>> IntangibleItemList = ssmBatchClient.queryObjectList("getIntangibleItem", ssmParam);

            String asnDelyDt = (String) ssmBatchClient.queryObject("getAsnDelyDt", ssmParam);

            for (Map<String, String> IntangibleItem : IntangibleItemList) {

                EDI_ASN_DTL_WRKTMsg outMsg = new EDI_ASN_DTL_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(outMsg.asnSoNum, asnSoNum);
                ZYPEZDItemValueSetter.setValue(outMsg.asnLineNum, (String) IntangibleItem.get("ASN_LINE_NUM"));

                outMsg = (EDI_ASN_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

                if (ZYPCommonFunc.hasValue(asnDelyDt)) {

                    if (asnDelyDt.length() > 8) {
                        asnDelyDt = asnDelyDt.substring(0, 8);
                    }

                    ZYPEZDItemValueSetter.setValue(outMsg.asnPlnDelyDt, asnDelyDt);

                } else {

                    ZYPEZDItemValueSetter.setValue(outMsg.asnPlnDelyDt, salesDate);
                }

                EZDTBLAccessor.update(outMsg);

                if (!DB_RETURN_CD_NORMAL_END.equals(outMsg.getReturnCode())) {
                    String[] errPrm = getErrParam(outMsg);
                    S21InfoLogOutput.println(NPAM1499E, errPrm);
                    addMailMessageToAdminForImport(NPAM1499E, asnSoNum, S21MessageFunc.clspGetMessage(NPAM1499E, errPrm));
                    return false;
                }
            }

            asnRecordCache.put(asnSoNum, "CASH");

        }

        return true;

    }

    /**
     * insertEdiAsnDtlWrk
     * @param asnRecord
     * @return
     */
    // START 2019/05/27 M.Naito [QC#50282,MOD]
//    private boolean insertEdiAsnDtlWrk(Map<String, String> asnRecord, int num) {
    private boolean insertEdiAsnDtlWrk(Map<String, String> asnRecord, int num, BigDecimal asnTotFrtAmt) {
    // END 2019/05/27 M.Naito [QC#50282,MOD]

        String lineNum = String.format("%03d", num);

        EDI_ASN_DTL_WRKTMsg insertTMsg = new EDI_ASN_DTL_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoNum, (String) asnRecord.get(SO_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnLineNum, lineNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoSlpNum, (String) asnRecord.get(SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.vndInvtyLocCd, (String) asnRecord.get(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnProNum, (String) asnRecord.get(PRO_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnCarrCd, (String) asnRecord.get(VND_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnBolNum, (String) asnRecord.get(BOL_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ediPoOrdDtlLineNum, (String) asnRecord.get(EDI_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ediSubNum, (String) asnRecord.get(EDI_SUB_NUM));
        if (ZYPCommonFunc.hasValue(asnRecord.get(TOT_SHIP_WT))) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnTtlWt, asnRecord.get(TOT_SHIP_WT));
        }
        // START 2019/05/27 M.Naito [QC#50282,MOD]
//        if (ZYPCommonFunc.hasValue(asnRecord.get(TOT_FRT_AMT))) {
//            ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotFrtAmt, new BigDecimal(asnRecord.get(TOT_FRT_AMT)));
//        }
        if (ZYPCommonFunc.hasValue(asnTotFrtAmt)) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotFrtAmt, asnTotFrtAmt);
        }
        // END 2019/05/27 M.Naito [QC#50282,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnPlnDelyDt, (String) asnRecord.get(DELIVERY_DATE));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnMdseCd, (String) asnRecord.get(MDSE_CD));
        if (ZYPCommonFunc.hasValue(asnRecord.get(SHIP_QTY))) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnQty, new BigDecimal(asnRecord.get(SHIP_QTY)));
        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnStkStsCd, (String) asnRecord.get(FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.mdseCdUpdFlg, (String) asnRecord.get(MDSE_CD_UPD_FLG));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxHdrNum, (String) asnRecord.get(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxLineNum, (String) asnRecord.get(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxLineSubNum, (String) asnRecord.get(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shpgPlnNum, (String) asnRecord.get(SHPG_PLN_NUM));

        boolean comFlag = false;

        if (insert(insertTMsg, (String) asnRecord.get(SO_NUM))) {

            comFlag = true;

            this.dsBizLastUpdTs = insertTMsg.ezUpTime.getValue();

        }

        return comFlag;
    }

    /**
     * insertEdiAsnSerNumWrk
     * @param asnRecord
     * @return
     */
    private boolean insertEdiAsnSerNumWrk(Map<String, String> asnRecord) {

        EDI_ASN_SER_NUM_WRKTMsg insertTMsg = new EDI_ASN_SER_NUM_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(insertTMsg.soSerId, asnRecord.get(SO_SER_ID));
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoNum, asnRecord.get(SO_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnMdseCd, asnRecord.get(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoSlpNum, asnRecord.get(SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.vndInvtyLocCd, asnRecord.get(WH_CD));
        ZYPEZDItemValueSetter.setValue(insertTMsg.serNum, asnRecord.get(SER_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.serTakeDtTmTs, asnRecord.get(SER_TAKE_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxHdrNum, asnRecord.get(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxLineNum, asnRecord.get(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxLineSubNum, asnRecord.get(TRX_LINE_SUB_NUM));

        return insert(insertTMsg, asnRecord.get(SO_NUM));
    }

    /**
     * insertEdiAsnHdrWrkForParts
     * @param rs
     */
    private boolean insertEdiAsnHdrWrkForParts(ResultSet rs, String prtVndInv) throws SQLException {

        EDI_ASN_HDR_WRKTMsg insertTMsg = new EDI_ASN_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoNum, rs.getString(P_NUM_SO));
        ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, rs.getString(P_CODE_CUST_CHRG_TO));
        ZYPEZDItemValueSetter.setValue(insertTMsg.sellToCustCd, rs.getString(P_CODE_CUST_SOLD_TO));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, rs.getString(P_CODE_CUST_SHIP_TO));
        ZYPEZDItemValueSetter.setValue(insertTMsg.vndInvtyLocCd, rs.getString(P_CODE_WH_SHIP));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnShipDtTmTs, rs.getString(P_DATE_SHIP_PRSS));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotShipWt, rs.getBigDecimal(TOT_SHIP_WT));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotFrtAmt, rs.getBigDecimal(P_AMT_INVO_FRT));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipFromSoNum, prtVndInv);
        ZYPEZDItemValueSetter.setValue(insertTMsg.brFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertTMsg.procFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(insertTMsg.ediRcvTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
        if (ZYPCommonFunc.hasValue(interfaceId)) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.itrlIntfcId, this.interfaceId);
        } else {
            ZYPEZDItemValueSetter.setValue(insertTMsg.itrlIntfcId, IF_ID_NPAA0061);
        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.ediPoOrdNum, rs.getString(P_NUM_CUST_PO));
        ZYPEZDItemValueSetter.setValue(insertTMsg.cmProcStsCd, ZYPConstant.FLG_OFF_N);
        if (!NULL_VAL.equals(rs.getString(P_NUM_INVOICE))) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.vndInvNum, rs.getString(P_NUM_INVOICE));
        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.elanPrintStsCd, ELAN_PRINT_STS.INITIAL);
        return insert(insertTMsg, rs.getString(P_NUM_SO));

    }

    /**
     * insertEdiAsnDtlWrkForParts
     * @param rs
     */
    // START 2019/02/21 M.Naito [QC#30311,MOD]
//    private boolean insertEdiAsnDtlWrkForParts(ResultSet rs, int num) throws SQLException {
    private boolean insertEdiAsnDtlWrkForParts(ResultSet rs, int num, String mdseCdUpdFlg) throws SQLException {
    // END 2019/02/21 M.Naito [QC#30311,MOD]


        String lineNum = String.format("%03d", num);

        EDI_ASN_DTL_WRKTMsg insertTMsg = new EDI_ASN_DTL_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoNum, rs.getString(P_NUM_SO));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnLineNum, lineNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnSoSlpNum, lineNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.vndInvtyLocCd, rs.getString(P_CODE_WH_SHIP));
        if (ZYPCommonFunc.hasValue(rs.getString(P_NUM_TRUCKER))) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnProNum, rs.getString(P_NUM_TRUCKER));
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnBolNum, rs.getString(P_NUM_TRUCKER));
        } else {
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnProNum, rs.getString(P_NUM_FEDX_CONTROL));
            ZYPEZDItemValueSetter.setValue(insertTMsg.asnBolNum, rs.getString(P_NUM_FEDX_CONTROL));
        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.ediSubNum, EDI_SUB_NUM_001);
        // START 2019/05/27 M.Naito [QC#50282,MOD]
//        ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotFrtAmt, rs.getBigDecimal(P_AMT_INVO_FRT));
        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(P_AMT_INVO_FRT))) {
            if (num == 1) {
                ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotFrtAmt, rs.getBigDecimal(P_AMT_INVO_FRT));
            } else {
                ZYPEZDItemValueSetter.setValue(insertTMsg.asnTotFrtAmt, BigDecimal.ZERO);
            }
        }
        // END 2019/05/27 M.Naito [QC#50282,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnPlnDelyDt, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT_DT));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnMdseCd, rs.getString(P_PARTS_NUM));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnQty, rs.getBigDecimal(P_QTY_SHIP));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnStkStsCd, STK_STS.GOOD);
        // START 2019/02/21 M.Naito [QC#30311,MOD]
//        ZYPEZDItemValueSetter.setValue(insertTMsg.mdseCdUpdFlg, rs.getString(P_MDSE_CD_UPD_FLG));
        ZYPEZDItemValueSetter.setValue(insertTMsg.mdseCdUpdFlg, mdseCdUpdFlg);
        // END 2019/02/21 M.Naito [QC#30311,MOD]
        // QC#58256
        ZYPEZDItemValueSetter.setValue(insertTMsg.asnCarrCd, rs.getString("P_CODE_SHIP_VIA"));


        boolean comFlag = false;

        if (insert(insertTMsg, rs.getString(P_NUM_SO))) {

            comFlag = true;

            this.dsBizLastUpdTs = insertTMsg.ezUpTime.getValue();

        }

        return comFlag;
    }

    /**
     * @param tMsg EZDTMsg
     * @param soNum String
     * @return boolean
     */
    public boolean insert(EZDTMsg tMsg, String soNum) {

        EZDTBLAccessor.insert(tMsg);

        if (!DB_RETURN_CD_NORMAL_END.equals(tMsg.getReturnCode())) {
            String[] errPrm = getErrParam(tMsg);
            S21InfoLogOutput.println(NPAM1499E, errPrm);
            addMailMessageToAdminForImport(NPAM1499E, soNum, S21MessageFunc.clspGetMessage(NPAM1499E, errPrm));

            return false;
        }

        return true;
    }

    /**
     * getErrParam
     * @param ezdTMsg
     * @return
     */
    private static String[] getErrParam(EZDTMsg ezdTMsg) {
        final StringBuilder pk = new StringBuilder();

        final List[] keyColumnList = ezdTMsg.getKeyColumnList();
        for (int i = 0; i < keyColumnList[1].size(); i++) {
            pk.append((String) keyColumnList[1].get(i));
            pk.append(DELIMITER);
            pk.append(ezdTMsg.getDBValue((String) keyColumnList[0].get(i)));
            pk.append(" ");
        }
        return new String[] {ezdTMsg.getTableName(), pk.toString() };
    }

    /**
     * itemChangeCheck
     * @param asnRecord
     */
    private void itemChangeCheck(Map<String, String> asnRecord) {

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            String ediNum = (String) asnRecord.get(EDI_NUM);
            ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
            // START 2019/02/22 M.Naito [QC#30452,ADD]
//            ssmParam.put(BIND_PO_ORD_NUM, (String) asnRecord.get(CUST_ISS_PO_NUM));
            String ediPoOrdNum = (String) asnRecord.get(CUST_ISS_PO_NUM);
            if (ZYPCommonFunc.hasValue(ediPoOrdNum)) {

                if (ediPoOrdNum.length() > 8) {
                    ssmParam.put(BIND_PO_ORD_NUM, ediPoOrdNum.substring(ediPoOrdNum.length() - 8));
                } else {
                    ssmParam.put(BIND_PO_ORD_NUM, ediPoOrdNum);
                }
            }
            // END 2019/02/22 M.Naito [QC#30452,ADD]

            if (ZYPCommonFunc.hasValue(ediNum)) {

                if (ediNum.length() > 3) {
                    ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum.substring(ediNum.length() - 3));
                } else {
                    ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);
                }
            }

            preparedStatement = ssmLlcClient.createPreparedStatement("itemChangeCheck", ssmParam);
            resultSet = preparedStatement.executeQuery();

            String cusaMdseCd = (String) asnRecord.get(MDSE_CD);
            asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_OFF_N);

            if (resultSet.next()) {

                String aslMdseCd = resultSet.getString(ASL_MDSE_CD);

                if (ZYPCommonFunc.hasValue(aslMdseCd)) {

                    if (cusaMdseCd.equals(aslMdseCd)) {

                        asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_OFF_N);

                    } else {

                        asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_ON_Y);

                    }

                } else {

                    // START 2019/02/22 M.Naito [QC#30452,MOD]
//                    asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_ON_Y);
                    asnRecord.put(MDSE_CD_UPD_FLG, ZYPConstant.FLG_OFF_N);
                    // END 2019/02/22 M.Naito [QC#30452,MOD]
                }

            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

        }

    }

// START 2019/02/21 M.Naito [QC#30311,ADD]
    /**
     * itemChangeCheckForParts
     * @param ResultSet
     */
    private String itemChangeCheckForParts(ResultSet rs) {

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String mdseCdUpdFlg = ZYPConstant.FLG_OFF_N;

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            String poOrdNum = (String) rs.getString(P_NUM_CUST_PO);
            String poOrdDtlLineNum = (String) rs.getString(P_NUM_LINE);
            if (ZYPCommonFunc.hasValue(poOrdNum)) {

                if (poOrdNum.length() > 8) {
                    ssmParam.put(BIND_PO_ORD_NUM, poOrdNum.substring(poOrdNum.length() - 8));
                } else {
                    ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
                }
            }
            if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {

                if (poOrdDtlLineNum.length() > 3) {
                    ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum.substring(poOrdDtlLineNum.length() - 3));
                } else {
                    ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
                }
            }
            ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

            preparedStatement = ssmLlcClient.createPreparedStatement("itemChangeCheck", ssmParam);
            resultSet = preparedStatement.executeQuery();

            String cusaMdseCd = (String) rs.getString(P_PARTS_NUM);

            if (resultSet.next()) {

                String aslMdseCd = resultSet.getString(ASL_MDSE_CD);

                if (ZYPCommonFunc.hasValue(cusaMdseCd) && ZYPCommonFunc.hasValue(aslMdseCd)) {

                    if (cusaMdseCd.equals(aslMdseCd)) {

                        mdseCdUpdFlg = ZYPConstant.FLG_OFF_N;

                    } else {

                        mdseCdUpdFlg = ZYPConstant.FLG_ON_Y;

                    }

                } else {

                    mdseCdUpdFlg = ZYPConstant.FLG_OFF_N;
                }

            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

        }
        return mdseCdUpdFlg;

    }
// END 2019/02/21 M.Naito [QC#30311,ADD]

    /**
     * getLakNumber
     * @param asnDataBySo
     * @return
     */
    private List<Map> getLakNumber(Map asnDataBySo) {

        List<Map> lakList = new ArrayList<Map>();

        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        paramMap.put(BIND_SO_NUM, (String) asnDataBySo.get(SO_NUM));
        paramMap.put(BIND_SO_SLP_NUM, (String) asnDataBySo.get(SO_SLP_NUM));
        paramMap.put(BIND_CPO_ORD_NUM, (String) asnDataBySo.get(CPO_ORD_NUM));
        paramMap.put(BIND_CPO_DTL_LINE_NUM, (String) asnDataBySo.get(CPO_DTL_LINE_NUM));
        paramMap.put(BIND_CPO_DTL_LINE_SUB_NUM, (String) asnDataBySo.get(CPO_DTL_LINE_SUB_NUM));
        paramMap.put(BIND_ROWNUM, (BigDecimal) asnDataBySo.get(ORD_QTY));
        paramMap.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = ssmLlcClient.createPreparedStatement("getLakNumber", paramMap);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, String> serialInfo = new HashMap<String, String>();

                serialInfo.put(GLBL_CMPY_CD, resultSet.getString(GLBL_CMPY_CD));
                serialInfo.put(MDSE_CD, resultSet.getString(MDSE_CD));
                serialInfo.put(SO_NUM, resultSet.getString(SO_NUM));
                serialInfo.put(SO_SER_ID, resultSet.getString(SO_SER_ID));
                serialInfo.put(WH_CD, resultSet.getString(WH_CD));

                // QC#17960 START
                if (ZYPConstant.FLG_OFF_N.equals(resultSet.getString(ILMS_LIC_FLG)) && !ZYPCommonFunc.hasValue(resultSet.getString(ILMS_LIC_ACCS_NUM))) {
                    serialInfo.put(SER_NUM, resultSet.getString(LIC_ACCS_NUM));
                } else {
                    serialInfo.put(SER_NUM, resultSet.getString(ILMS_LIC_ACCS_NUM));
                }
                // QC#17960 END

                serialInfo.put(SER_TAKE_DT_TM_TS, resultSet.getString(SER_TAKE_DT_TM_TS));
                serialInfo.put(SO_SLP_NUM, resultSet.getString(SO_SLP_NUM));
                serialInfo.put(TRX_HDR_NUM, (String) asnDataBySo.get(TRX_HDR_NUM));
                serialInfo.put(TRX_LINE_NUM, (String) asnDataBySo.get(TRX_LINE_NUM));
                serialInfo.put(TRX_LINE_SUB_NUM, (String) asnDataBySo.get(TRX_LINE_SUB_NUM));
                serialInfo.put(SHPG_PLN_NUM, (String) asnDataBySo.get(SHPG_PLN_NUM));

                lakList.add(serialInfo);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return lakList;

    }

    /**
     * getSerialInformationForWh
     * @param asnDataBySo
     * @return
     */
    private List<Map> getSerialInformationForWh(Map asnDataBySo) {

        List<Map> shipingStatusCodeList = new ArrayList<Map>();

        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        paramMap.put(BIND_SO_NUM, (String) asnDataBySo.get(SO_NUM));
        paramMap.put(BIND_SO_SLP_NUM, (String) asnDataBySo.get(SO_SLP_NUM));
        paramMap.put(BIND_CPO_ORD_NUM, (String) asnDataBySo.get(CPO_ORD_NUM));

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = ssmLlcClient.createPreparedStatement("getSerialInformation", paramMap);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, String> serialInfo = new HashMap<String, String>();

                serialInfo.put(GLBL_CMPY_CD, resultSet.getString(GLBL_CMPY_CD));
                serialInfo.put(MDSE_CD, resultSet.getString(MDSE_CD));
                serialInfo.put(SO_NUM, resultSet.getString(SO_NUM));
                serialInfo.put(SO_SER_ID, resultSet.getString(SO_SER_ID));
                serialInfo.put(WH_CD, resultSet.getString(WH_CD));
                serialInfo.put(SER_NUM, resultSet.getString(SER_NUM));
                serialInfo.put(SER_TAKE_DT_TM_TS, resultSet.getString(SER_TAKE_DT_TM_TS));
                serialInfo.put(SO_SLP_NUM, resultSet.getString(SO_SLP_NUM));
                serialInfo.put(TRX_HDR_NUM, (String) asnDataBySo.get(TRX_HDR_NUM));
                serialInfo.put(TRX_LINE_NUM, (String) asnDataBySo.get(TRX_LINE_NUM));
                serialInfo.put(TRX_LINE_SUB_NUM, (String) asnDataBySo.get(TRX_LINE_SUB_NUM));
                serialInfo.put(SHPG_PLN_NUM, (String) asnDataBySo.get(SHPG_PLN_NUM));

                shipingStatusCodeList.add(serialInfo);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return shipingStatusCodeList;
    }

    /**
     * getSerialInformationForVender
     * @param asnDataBySo
     * @param poSerNumPkList
     * @return
     */
    private List<Map> getSerialInformationForVender(Map asnDataBySo, List<String> poSerNumPkList) {
        List<Map> poStatusCodeList = new ArrayList<Map>();

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        paramMap.put(BIND_TRX_HDR_NUM, (String) asnDataBySo.get(TRX_HDR_NUM));
        paramMap.put(BIND_TRX_LINE_NUM, (String) asnDataBySo.get(TRX_LINE_NUM));
        paramMap.put(BIND_TRX_LINE_SUB_NUM, (String) asnDataBySo.get(TRX_LINE_SUB_NUM));
        paramMap.put(BIND_PO_ORD_NUM, (String) asnDataBySo.get(PO_ORD_NUM));
        paramMap.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        String shipQty = (String) asnDataBySo.get(SHIP_QTY);

        int i = 0;

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        EDI_ASN_SER_NUM_WRKTMsg refTMsg = new EDI_ASN_SER_NUM_WRKTMsg();

        try {

            preparedStatement = ssmLlcClient.createPreparedStatement("getSerialInformationForVendor", paramMap);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Map<String, String> serialInfo = new HashMap<String, String>();

                String serNumPk = resultSet.getString(PO_SER_NUM_PK);

                serialInfo.put(GLBL_CMPY_CD, resultSet.getString(GLBL_CMPY_CD));
                serialInfo.put(MDSE_CD, resultSet.getString(MDSE_CD));
                serialInfo.put(SO_NUM, (String) asnDataBySo.get(SO_NUM));
                serialInfo.put(SO_SER_ID, resultSet.getString(ROW_ID));
                serialInfo.put(WH_CD, this.dummyWhCd);
                serialInfo.put(SER_NUM, resultSet.getString(SER_NUM));

                if (ZYPCommonFunc.hasValue(resultSet.getString(SER_EVENT_TS))) {
                    if (resultSet.getString(SER_EVENT_TS).length() > refTMsg.getAttr(BIND_SER_TAKE_DT_TM_TS).getDigit()) {
                        serialInfo.put(SER_TAKE_DT_TM_TS, resultSet.getString(SER_EVENT_TS).substring(0, refTMsg.getAttr(BIND_SER_TAKE_DT_TM_TS).getDigit()));
                    } else {
                        serialInfo.put(SER_TAKE_DT_TM_TS, resultSet.getString(SER_EVENT_TS));
                    }
                }

                serialInfo.put(SO_SLP_NUM, (String) asnDataBySo.get(SO_SLP_NUM));
                serialInfo.put(TRX_HDR_NUM, (String) asnDataBySo.get(TRX_HDR_NUM));
                serialInfo.put(TRX_LINE_NUM, (String) asnDataBySo.get(TRX_LINE_NUM));
                serialInfo.put(TRX_LINE_SUB_NUM, (String) asnDataBySo.get(TRX_LINE_SUB_NUM));
                serialInfo.put(SHPG_PLN_NUM, (String) asnDataBySo.get(SHPG_PLN_NUM));

                if (!poSerNumPkList.contains(serNumPk)) {

                    if (i < Integer.parseInt(shipQty)) {

                        poSerNumPkList.add(serNumPk);
                        poStatusCodeList.add(serialInfo);
                        i++;

                    } else {

                        break;

                    }
                }

            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

        }

        return poStatusCodeList;
    }

    /**
     * getSerialInformation
     * @param asnDataBySo
     * @param poSerNumPkList
     * @return statusCodeList
     */
    private List<Map> getSerialInformation(List<Map> asnDataBySo, List<String> poSerNumPkList) {

        String poReqFlag = null;
        List<Map> statusCodeList = null;

        statusCodeList = new ArrayList<Map>();

        String curSoSlpNum = null;
        String prevSoSlpNum = null;

        for (Map map : asnDataBySo) {

            poReqFlag = (String) map.get(PO_REQ_FLG);
            curSoSlpNum = (String) map.get(SO_SLP_NUM);

            if (ZYPCommonFunc.hasValue(prevSoSlpNum) && curSoSlpNum.equals(prevSoSlpNum)) {

                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(poReqFlag)) {

                List<Map> fvList = getSerialInformationForVender(map, poSerNumPkList);

                for (Map<String, String> vdMap : fvList) {

                    statusCodeList.add(vdMap);

                }

            } else {

                List<Map> fwList = getSerialInformationForWh(map);

                for (Map<String, String> whMap : fwList) {

                    statusCodeList.add(whMap);

                }
            }

            prevSoSlpNum = (String) map.get(SO_SLP_NUM);
        }

        // get LAK Number
        Map<String, Object> asnRecordCache = new HashMap<String, Object>();

        // QC#25508
        String preMdseCd = "";
        String preSoNum = "";
        String preSoSlpNum = "";
        String preSoSerId = "";

        for (Map map : asnDataBySo) {

            if (ZYPConstant.FLG_ON_Y.equals((String) map.get("AUTO_LIC_ALLOC_FLG"))) {

                String cacheKey = (String) map.get("CPO_ORD_NUM") + (String) map.get("CPO_DTL_LINE_NUM") + (String) map.get("CPO_DTL_LINE_SUB_NUM");

                if (asnRecordCache.containsKey(cacheKey)) {

                    continue;

                }

                List<Map> lakList = getLakNumber(map);

                for (Map<String, String> lakMap : lakList) {

                    // QC#25508
                    if (ZYPCommonFunc.hasValue(preMdseCd) //
                            && ZYPCommonFunc.hasValue(preSoNum) //
                            && ZYPCommonFunc.hasValue(preSoSlpNum) //
                            && ZYPCommonFunc.hasValue(preSoSerId) //
                            && preMdseCd.equals(lakMap.get(MDSE_CD)) //
                            && preSoNum.equals(lakMap.get(SO_NUM)) //
                            && preSoSlpNum.equals(lakMap.get(SO_SLP_NUM)) //
                            && preSoSerId.equals(lakMap.get(SO_SER_ID))) {

                        lakMap.put(SO_SER_ID, String.valueOf(Integer.parseInt(lakMap.get(SO_SER_ID)) + 1));

                    }
                    statusCodeList.add(lakMap);

                    preMdseCd = lakMap.get(MDSE_CD);
                    preSoNum = lakMap.get(SO_NUM);
                    preSoSlpNum = lakMap.get(SO_SLP_NUM);
                    preSoSerId = lakMap.get(SO_SER_ID);
                }

                asnRecordCache.put(cacheKey, "CASH");

            }

        }

        return statusCodeList;
    }

    /**
     * @param asnDataBySo
     * @return
     */
    private boolean checkShipingStatusCode(List<Map> asnDataBySo) {

        for (Map map : asnDataBySo) {

            String revenueRecognitionMethodCode = (String) map.get(REV_RECOG_METH_CD);

            if (revenueRecognitionMethodCode.equals("040")) {

                continue;

            }

            // Get Revenue Recognition Method Code List
            List<String> revenueRecognitionMethodCodeList = null;

            String cacheKey = this.cusaGlblCmpyCd + revenueRecognitionMethodCode;

            if (revenueRecognitionMethodCodeCache.containsKey(cacheKey)) {

                revenueRecognitionMethodCodeList = revenueRecognitionMethodCodeCache.get(cacheKey);

            } else {

                revenueRecognitionMethodCodeList = getRevenueRecognitionMethodCodeList(revenueRecognitionMethodCode);
                revenueRecognitionMethodCodeCache.put(cacheKey, revenueRecognitionMethodCodeList);
            }

            // Check Shiping Status Code
            String shipingStatusCode = (String) map.get(SHPG_STS_CD);

            boolean isSkip = true;

            for (String code : revenueRecognitionMethodCodeList) {

                if (code.equals(shipingStatusCode)) {

                    isSkip = false;
                    break;
                }

            }

            if (isSkip) {
                S21InfoLogOutput.println(NPAM1500E);
                addMailMessageToAdminForImport(NPAM1500E, (String) asnDataBySo.get(0).get(SO_NUM), S21MessageFunc.clspGetMessage(NPAM1500E, new String[] {(String) asnDataBySo.get(0).get(SO_NUM) }));
                return false;
            }

        }

        return true;

    }

    /**
     * getRevenueRecognitionMethodCodeList
     * @param revenueRecognitionMethodCode
     * @return shipingStatusCodeList
     */
    private List<String> getRevenueRecognitionMethodCodeList(String revenueRecognitionMethodCode) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_REV_RECOG_METH_CD, revenueRecognitionMethodCode);

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<String> shipingStatusCodeList = new ArrayList<String>();

        try {
            preparedStatement = ssmLlcClient.createPreparedStatement("getRevenueRecognitionMethodCode", ssmParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String shipingStatusCode = resultSet.getString(SHPG_STS_CD);
                shipingStatusCodeList.add(shipingStatusCode);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return shipingStatusCodeList;
    }

    /**
     * getAsnDataBySO
     * @param asnDataList
     * @return
     */
    private List<Map> getAsnDataBySO(List<Map> asnDataList) {

        Map data = asnDataList.get(0);
        String defaultSoNum = (String) data.get("SO_NUM");

        List<Map> asnDataBySo = new ArrayList<Map>();

        for (Map<String, String> map : asnDataList) {

            String soNum = map.get(SO_NUM);

            if (defaultSoNum.equals(soNum)) {

                asnDataBySo.add(map);

            } else {

                continue;
            }
        }

        asnDataList.removeAll(asnDataBySo);

        return asnDataBySo;
    }

    /**
     * @param execParam
     */
    private void receivingAsnFromParts(S21SsmExecutionParameter execParam) {

        getVarCharConstValue();

        List<String> pCodeCustSoldToList = getSellToCustCd();

        PreparedStatement prdStmtPartsHdr = null;
        ResultSet resultSetHdr = null;

        PreparedStatement prdStmtParts = null;
        ResultSet resultSet = null;

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(BIND_P_CODE_STAT_SO, STAT_SO_SHIPPED);
            // QC#52677
            ssmParam.put(BIND_P_CODE_STAT_INV, STAT_SO_INVOICED);
            ssmParam.put(BIND_P_CODE_CUST_SOLD_TO_LIST, pCodeCustSoldToList);
            ssmParam.put(BIND_P_CODE_TRANSACT, CODE_TRANSACT_10);
            ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(BIND_EZUPTIME, this.dsBizLastUpdTs);
            ssmParam.put(BIND_P_QTY_SHIP, BigDecimal.ZERO);

            prdStmtPartsHdr = ssmLlcClient.createPreparedStatement("searchSendAsnInfoForPartsHdr", ssmParam, execParam);
            resultSetHdr = prdStmtPartsHdr.executeQuery();
            // QC#52012
            List<String> prtVndInvList = new ArrayList<String>();

            while (resultSetHdr.next()) {

                int lineNum = 1;
                String prevSoNum = null;
                String curSoNum = null;

                curSoNum = resultSetHdr.getString(P_NUM_SO);
                ssmParam.put(BIND_P_NUM_SO, curSoNum);

                try {    // 2019/02/08 T.Ogura [QC#30278,ADD]

                    prdStmtParts = ssmLlcClient.createPreparedStatement("searchSendAsnInfoForParts", ssmParam, execParam);
                    resultSet = prdStmtParts.executeQuery();

                    while (resultSet.next()) {

                        if (!ZYPCommonFunc.hasValue(prevSoNum) || !curSoNum.equals(prevSoNum)) {

                            lineNum = 1;

                        } else {

                            lineNum++;

                        }

                        // START 2019/02/21 M.Naito [QC#30311,ADD]
                        String itemChangeFlg = itemChangeCheckForParts(resultSet);
                        // END 2019/02/21 M.Naito [QC#30311,ADD]
                        boolean isSkip = true;
                        String prtVndInv = curSoNum;

                        // check vendor drop ship
                        if (checkVendorDropShip(resultSet.getString(P_CODE_WH_SHIP))) {

                            // QC#52012 Start
                            String mdseCd = resultSet.getString(P_PARTS_NUM);

                            // get vendor invoice
                            List<String> resultPrtVndInvList = getVendorInvoiceExists(resultSet.getString(P_NUM_CUST_PO), resultSet.getString(P_CODE_CUST_SHIP_TO), resultSet.getString(P_PARTS_NUM), resultSet.getBigDecimal(P_QTY_SHIP));

                            if (resultPrtVndInvList == null || resultPrtVndInvList.isEmpty()) {

                                prtVndInv = getVendorInvoice(resultSet.getString(P_NUM_CUST_PO), resultSet.getString(P_CODE_CUST_SHIP_TO), resultSet.getString(P_PARTS_NUM));

                                if (!ZYPCommonFunc.hasValue(prtVndInv)) {

                                    isSkip = false;
                                }

                            } else {

                                for (String retPrtVndInv : resultPrtVndInvList) {
                                    if (!prtVndInvList.contains(mdseCd + retPrtVndInv)) {
                                        prtVndInv = retPrtVndInv;
                                        prtVndInvList.add(mdseCd + retPrtVndInv);
                                        break;
                                    } else {
                                        prtVndInv = retPrtVndInv;
                                    }
                                }

                            }
                            // QC#52012 End

                        }

                        if (!isSkip) {
                            totalErrorCount++;
                            rollback();
                            prevSoNum = curSoNum;
                            addMailMessageToAdminForImport(NPAM1312E, curSoNum, S21MessageFunc.clspGetMessage(NPAM1312E, new String[] {curSoNum }));
                            continue;
                        }

                        if ((!ZYPCommonFunc.hasValue(prevSoNum) || !curSoNum.equals(prevSoNum))) {

                            isSkip = insertEdiAsnHdrWrkForParts(resultSet, prtVndInv);

                        }

                        if (!isSkip) {
                            totalErrorCount++;
                            rollback();
                            prevSoNum = curSoNum;
                            addMailMessageToAdminForImport(NPAM1312E, curSoNum, S21MessageFunc.clspGetMessage(NPAM1312E, new String[] {curSoNum }));
                            continue;
                        }

                        // START 2019/02/21 M.Naito [QC#30311,MOD]
//                        isSkip = insertEdiAsnDtlWrkForParts(resultSet, lineNum);
                        isSkip = insertEdiAsnDtlWrkForParts(resultSet, lineNum, itemChangeFlg);
                        // END 2019/02/21 M.Naito [QC#30311,MOD]

                        if (!isSkip) {
                            totalErrorCount++;
                            rollback();
                            prevSoNum = curSoNum;
                            addMailMessageToAdminForImport(NPAM1312E, curSoNum, S21MessageFunc.clspGetMessage(NPAM1312E, new String[] {curSoNum }));
                            continue;
                        }

                        totalCorrectCount++;

                        prevSoNum = curSoNum;

                        if (resultSet.isLast()) {

                            commit();
                            prtVndInvList = new ArrayList<String>();
                            // rollback();

                        }

                    }

                // START 2019/02/08 T.Ogura [QC#30278,ADD]
                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(prdStmtParts, resultSet);
                }
                // END   2019/02/08 T.Ogura [QC#30278,ADD]

            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(prdStmtPartsHdr, resultSetHdr);
//            S21SsmLowLevelCodingClient.closeResource(prdStmtParts, resultSet);    // 2019/02/08 T.Ogura [QC#30278,DEL]

        }

    }

    /**
     * getVendorInvoice
     * @param custPoNum
     * @param custShipToCd
     * @param partsNum
     * @return
     */
    private String getVendorInvoice(String custPoNum, String custShipToCd, String partsNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_P_NUM_CUST_PO, custPoNum);
        ssmParam.put(BIND_P_CODE_CUST_SHIP_TO, custShipToCd);
        ssmParam.put(BIND_P_PARTS_NUM, partsNum);

        String result = (String) ssmBatchClient.queryObject("getVendorInvoice", ssmParam);

        return result;
    }

    /**
     * getVendorInvoice. Mod QC#52012
     * @param custPoNum
     * @param custShipToCd
     * @param partsNum
     * @param asnQty
     * @return
     */
    private List<String> getVendorInvoiceExists(String custPoNum, String custShipToCd, String partsNum, BigDecimal asnQty) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_P_NUM_CUST_PO, custPoNum);
        ssmParam.put(BIND_P_CODE_CUST_SHIP_TO, custShipToCd);
        ssmParam.put(BIND_P_PARTS_NUM, partsNum);
        ssmParam.put(BIND_P_QTY_SHIP, asnQty);
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        List<String> result = (List<String>) ssmBatchClient.queryObjectList("getVendorInvoiceExists", ssmParam);

        return result;
    }

    /**
     * checkVendorDropShip
     * @param whCd
     * @return
     */
    private boolean checkVendorDropShip(String whCd) {

        boolean dropShipFlag = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_P_CODE_WH, whCd);

        String result = (String) ssmBatchClient.queryObject("checkVendorDropShip", ssmParam);

        if (ZYPCommonFunc.hasValue(result) && ZYPConstant.FLG_ON_Y.equals(result)) {

            dropShipFlag = true;

        }

        return dropShipFlag;
    }

    /**
     * @return
     */
    private List<String> getSellToCustCd() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_P_CODE_TP_LIST, this.trdPtnrCdList);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSellToCustCd", ssmParam);

        ArrayList<String> soldToList = new ArrayList<String>();

        for (int i = 0; i < resultList.size(); i++) {

            Map<String, Object> aslMdseMap = resultList.get(i);

            soldToList.add((String) aslMdseMap.get(P_CODE_CUST_SOLD_TO));

        }

        return soldToList;
    }

    /**
     * getVarCharConstValue
     */
    private void getVarCharConstValue() {

        // get VAR CONST VAL
        // ********************************************************************
        this.cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_GLBL_CMPY_CD, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(cusaGlblCmpyCd)) {

            throw new S21AbendException(NPAM1496E);

        }

        if (MODE_WS.equals(processMode)) {

            String str = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_TRD_PTNR_CD, this.glblCmpyCd);

            if (!ZYPCommonFunc.hasValue(str)) {

                throw new S21AbendException(NPAM1497E);

            } else {

                String[] trdPtnr = str.split(",");

                for (int i = 0; i < trdPtnr.length; i++) {

                    this.trdPtnrCdList.add(trdPtnr[i]);

                }
            }

            this.dummyWhCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_DROP_SHIP_WH_CD, this.glblCmpyCd);

            if (!ZYPCommonFunc.hasValue(dummyWhCd)) {

                throw new S21AbendException(NPAM1505E);

            }

        } else if (MODE_PARTS.equals(processMode)) {

            String str = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_EDI_TRD_PTNR_CD, this.glblCmpyCd);

            if (!ZYPCommonFunc.hasValue(str)) {

                throw new S21AbendException(NPAM1497E);

            } else {

                String[] trdPtnr = str.split(",");

                for (int i = 0; i < trdPtnr.length; i++) {

                    this.trdPtnrCdList.add(trdPtnr[i]);

                }
            }
        }
    }

    /**
     * getGroupCompany
     * @param trdPtnrCdList
     * @return
     */
    private Map<String, List> getGroupCompany(ArrayList<String> trdPtnrCdList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_EDI_TRD_PTNR_CD_LIST, trdPtnrCdList);
        ssmParam.put(BIND_EDI_CUST_TP_CD, "1");

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<String> billToCustCdList = new ArrayList<String>();
        List<String> sellToCustCdList = new ArrayList<String>();
        Map<String, List> groupCompanyList = new HashMap<String, List>();
        groupCompanyList.put(BILL_TO_CUST_CD, billToCustCdList);
        groupCompanyList.put(SELL_TO_CUST_CD, sellToCustCdList);

        try {
            preparedStatement = ssmLlcClient.createPreparedStatement("getBillToSellTo", ssmParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String billToCustomer = resultSet.getString(BILL_TO_CUST_CD);
                String sellToCustomer = resultSet.getString(SELL_TO_CUST_CD);

                if (ZYPCommonFunc.hasValue(billToCustomer)) {
                    billToCustCdList.add(billToCustomer);
                }
                if (ZYPCommonFunc.hasValue(sellToCustomer)) {
                    sellToCustCdList.add(sellToCustomer);
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

        }

        return groupCompanyList;
    }

    /**
     * @param groupCompanyList
     * @return
     */
    private List<Map> getAsnRecordList(Map<String, List> groupCompanyList, S21SsmExecutionParameter execParam) {

        // ship data from cusa WH
        List<Map> asnRecordListForWh = getAsnRecordListForWh(groupCompanyList, execParam);

        // QC#7523 START
        // intangible item ship data from cusa WH
        List<Map> intangibleAsnRecordListForWh = getIntangibleAsnRecordListForWh(asnRecordListForWh, groupCompanyList);
        asnRecordListForWh.addAll(intangibleAsnRecordListForWh);
        // QC#7523 END

        // ship data from vendor WH
        List<Map> asnRecordListForVendor = getAsnRecordListForVendor(groupCompanyList);

        asnRecordListForWh.addAll(asnRecordListForVendor);

        return asnRecordListForWh;
    }

    /**
     * getAsnRecordListForWh
     * @param groupCompanyList
     * @param execParam
     * @return
     */
    private List<Map> getAsnRecordListForWh(Map<String, List> groupCompanyList, S21SsmExecutionParameter execParam) {

        PreparedStatement prdStmtWs = null;
        ResultSet resultSet = null;

        List<Map> asnRecordList = new ArrayList<Map>();

        // *****************************************************************
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);

        List<String> shpgStsCdList = new ArrayList<String>();

        this.cpoOrdTpCd = ZYPCodeDataUtil.getVarCharConstValue("NPAB1250_CPO_ORD_TP_CD", this.glblCmpyCd);

        if (MODE_NIGHT.equals(userVariable1)) {

            ssmParam.put("modeNight", userVariable1);
            ssmParam.put("ezUptimeNight", salesDate);
            this.shpgStatusCode = ZYPCodeDataUtil.getVarCharConstValue("NPAB1250_SHPG_STS_NIGHT", this.glblCmpyCd);

        } else {

            ssmParam.put("modeDaily", userVariable1);
            this.shpgStatusCode = ZYPCodeDataUtil.getVarCharConstValue("NPAB1250_SHPG_STS_DAY", this.glblCmpyCd);

        }

        if (ZYPCommonFunc.hasValue(this.shpgStatusCode)) {

            String[] shpgStatusCodeTemp = this.shpgStatusCode.split(",");

            for (int i = 0; i < shpgStatusCodeTemp.length; i++) {

                shpgStsCdList.add(shpgStatusCodeTemp[i]);

            }

        }

        ssmParam.put("shipDtTmTs", salesDate);

        // QC#7523 END

        ssmParam.put(BIND_TRX_LINE_SUB_NUM, TRX_LINE_SUB_NUM_000);
        ssmParam.put(BIND_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);
        ssmParam.put(BIND_PO_REQ_FLG, ZYPConstant.FLG_OFF_N);
        ssmParam.put(BIND_SO_CLOSE_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(BIND_ASN_REQ_FLG, ZYPConstant.FLG_OFF_N);
        ssmParam.put(BIND_SO_PROC_STS_CD, SO_PROC_STS.SHIP);
        ssmParam.put(BIND_BILL_TO_CUST_CD_LIST, groupCompanyList.get(BILL_TO_CUST_CD));
        ssmParam.put(BIND_SELL_TO_CUST_CD_LIST, groupCompanyList.get(SELL_TO_CUST_CD));
        ssmParam.put(BIND_SHPG_STS_CD_LIST, shpgStsCdList);
        ssmParam.put(BIND_SHPG_STS_CD, SHPG_STS.N_INVOICE_READY);
        ssmParam.put(BIND_ETA_DT, salesDate);
        ssmParam.put(BIND_EXPT_INV_TP_CD, ZYPConstant.FLG_OFF_N);
        ssmParam.put(BIND_EXPT_BOL_DT, salesDate);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            prdStmtWs = ssmLlcClient.createPreparedStatement("searchSendAsnInfo", ssmParam, execParam);
            resultSet = prdStmtWs.executeQuery();

            while (resultSet.next()) {

                Map<String, Object> asnRecord = new HashMap<String, Object>();
                asnRecord.put(GLBL_CMPY_CD, resultSet.getString(GLBL_CMPY_CD));
                asnRecord.put(REV_RECOG_METH_CD, resultSet.getString(REV_RECOG_METH_CD));
                asnRecord.put(CUST_ISS_PO_NUM, resultSet.getString(CUST_ISS_PO_NUM));
                asnRecord.put(CPO_ORD_NUM, resultSet.getString(CPO_ORD_NUM));
                asnRecord.put(CUST_ISS_PO_DT, resultSet.getString(CUST_ISS_PO_DT));
                asnRecord.put(EDI_NUM, resultSet.getString(EDI_NUM));
                asnRecord.put(EDI_SUB_NUM, resultSet.getString(EDI_SUB_NUM));
                asnRecord.put(CPO_DTL_LINE_NUM, resultSet.getString(CPO_DTL_LINE_NUM));
                asnRecord.put(CPO_DTL_LINE_SUB_NUM, resultSet.getString(CPO_DTL_LINE_SUB_NUM));
                asnRecord.put(SHPG_STS_CD, resultSet.getString(SHPG_STS_CD));
                asnRecord.put(ACTL_ARV_DT, resultSet.getString(ACTL_ARV_DT));
                asnRecord.put(SHPG_PLN_NUM, resultSet.getString(SHPG_PLN_NUM));
                asnRecord.put(TRX_HDR_NUM, resultSet.getString(TRX_HDR_NUM));
                asnRecord.put(TRX_LINE_NUM, resultSet.getString(TRX_LINE_NUM));
                asnRecord.put(TRX_LINE_SUB_NUM, resultSet.getString(TRX_LINE_SUB_NUM));
                asnRecord.put(THIRD_PTY_INV_NUM, resultSet.getString(THIRD_PTY_INV_NUM));
                asnRecord.put(TOT_SHIP_WT_DTL, resultSet.getString(TOT_SHIP_WT_DTL));
                asnRecord.put(PO_REQ_FLG, resultSet.getString(PO_REQ_FLG));
                asnRecord.put(SO_CLOSE_FLG, resultSet.getString(SO_CLOSE_FLG));
                asnRecord.put(BILL_TO_CUST_CD, resultSet.getString(BILL_TO_CUST_CD));
                asnRecord.put(SELL_TO_CUST_CD, resultSet.getString(SELL_TO_CUST_CD));
                asnRecord.put(SHIP_TO_CUST_CD, resultSet.getString(SHIP_TO_CUST_CD));
                asnRecord.put(SHPG_MODE_CD, resultSet.getString(SHPG_MODE_CD));
                asnRecord.put(SHPG_SVC_LVL_CD, resultSet.getString(SHPG_SVC_LVL_CD));
                asnRecord.put(EZUPTIME, resultSet.getString(EZUPTIME));
                asnRecord.put(FRT_CHRG_TO_CD, resultSet.getString(FRT_CHRG_TO_CD));
                asnRecord.put(FRT_CHRG_METH_CD, resultSet.getString(FRT_CHRG_METH_CD));
                asnRecord.put(SO_NUM, resultSet.getString(SO_NUM));
                asnRecord.put(WH_CD, resultSet.getString(WH_CD));
                asnRecord.put(SHIP_DT_TM_TS, resultSet.getString(SHIP_DT_TM_TS));
                asnRecord.put(TOT_SHIP_WT, resultSet.getString(TOT_SHIP_WT));
                asnRecord.put(TOT_FRT_AMT, resultSet.getString(TOT_FRT_AMT));
                asnRecord.put(SO_SLP_NUM, resultSet.getString(SO_SLP_NUM));
                asnRecord.put(INVTY_LOC_CD, resultSet.getString(INVTY_LOC_CD));
                asnRecord.put(PRO_NUM, resultSet.getString(PRO_NUM));
                asnRecord.put(VND_CD, resultSet.getString(VND_CD));
                asnRecord.put(BOL_NUM, resultSet.getString(BOL_NUM));
                asnRecord.put(MDSE_CD, resultSet.getString(MDSE_CD));
                asnRecord.put(SHIP_QTY, resultSet.getString(SHIP_QTY));
                asnRecord.put(TOT_FRT_AMT_DTL, resultSet.getString(TOT_FRT_AMT_DTL));
                asnRecord.put(FROM_STK_STS_CD, resultSet.getString(FROM_STK_STS_CD));
                asnRecord.put(SET_MDSE_CD, resultSet.getString(SET_MDSE_CD));

                // QC#7523 START
                asnRecord.put(CPO_ORD_TP_CD, resultSet.getString(CPO_ORD_TP_CD));
                asnRecord.put(SET_ITEM_SHIP_CPLT_FLG, resultSet.getString(SET_ITEM_SHIP_CPLT_FLG));
                asnRecord.put(SHIP_CPLT_CD, resultSet.getString(SHIP_CPLT_CD));

                // QC#7523 END

                asnRecordList.add(asnRecord);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(prdStmtWs, resultSet);
        }

        return asnRecordList;

    }

    // QC#7523 START

    private List<Map> getIntangibleAsnRecordListForWh(List<Map> asnRecordListForWh, Map<String, List> groupCompanyList) {

        List<Map> asnRemoveRecordList = new ArrayList<Map>();
        List<Map> asnRecordList = new ArrayList<Map>();
        List<Map> asnRecordListReturn = new ArrayList<Map>();

        String cpoOrdNum = "";
        Map<String, String> cpoOrdNumCache = new HashMap<String, String>();

        // common ssm param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
        ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        List<String> shpgStsCdList = new ArrayList<String>();
        String shpgStatusCode = "";

        if (MODE_NIGHT.equals(userVariable1)) {

            ssmParam.put("modeNight", userVariable1);
            ssmParam.put("ezUptimeNight", salesDate);
            shpgStsCdList.add(SHPG_STS.SHIPPED);
            shpgStsCdList.add(SHPG_STS.ARRIVED);
            shpgStsCdList.add(SHPG_STS.INSTALLED);
            shpgStsCdList.add(SHPG_STS.INVOICED);
	        // QC#52580 Add Start
            shpgStsCdList.add(SHPG_STS.VALIDATED);
            // QC#52580 Add End

        } else {

            ssmParam.put("modeDaily", userVariable1);
            shpgStsCdList.add(SHPG_STS.SHIPPED);
            shpgStsCdList.add(SHPG_STS.ARRIVED);
            shpgStsCdList.add(SHPG_STS.INSTALLED);
            shpgStsCdList.add(SHPG_STS.VALIDATED);

        }

        ssmParam.put("shpgStsCdList", shpgStsCdList);

        for (int i = 0; i < asnRecordListForWh.size(); i++) {

            cpoOrdNum = (String) asnRecordListForWh.get(i).get("CPO_ORD_NUM");
            String cacheKey = "CPO_ORD_NUM" + cpoOrdNum;

            // get ShipCompletion data with intangible
            // ***********************************************

            ssmParam.put("shipDtTmTs", (String) asnRecordListForWh.get(i).get("SHIP_DT_TM_TS"));
            ssmParam.put("soNum", (String) asnRecordListForWh.get(i).get("SO_NUM"));
            ssmParam.put("soSlpNum", getMaxSoSlpNum((String) asnRecordListForWh.get(i).get("SO_NUM")));
            ssmParam.put("cpoOrdNum", cpoOrdNum);
            ssmParam.put("shipCpltCd", (String) asnRecordListForWh.get(i).get("SHIP_CPLT_CD"));
            ssmParam.put("asnSoNum", (String) asnRecordListForWh.get(i).get("SO_NUM"));

            List<Map<String, Object>> intangibleAsnRecordResultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getIntangibleAsnRecordListForWh", ssmParam);

            for (Map<String, Object> intangibleAsnRecordResultMap : intangibleAsnRecordResultList) {

                // check LAK Assign
                if (lakAssigned(intangibleAsnRecordResultMap)) {

                    Map<String, Object> asnRecord = new HashMap<String, Object>();

                    asnRecord = setAsnValue(intangibleAsnRecordResultMap);

                    asnRecordList.add(asnRecord);
                }

            }

            // setcomponents
            // ***********************************************
            if (ZYPConstant.FLG_ON_Y.equals((String) asnRecordListForWh.get(i).get("SET_ITEM_SHIP_CPLT_FLG"))
                    && (CPO_ORD_TP.SALES.equals((String) asnRecordListForWh.get(i).get("CPO_ORD_TP_CD")) || CPO_ORD_TP.EXPENSE.equals((String) asnRecordListForWh.get(i).get("CPO_ORD_TP_CD"))
                            || CPO_ORD_TP.TRIAL.equals((String) asnRecordListForWh.get(i).get("CPO_ORD_TP_CD")) || CPO_ORD_TP.LOAN.equals((String) asnRecordListForWh.get(i).get("CPO_ORD_TP_CD")))) {

                ssmParam.put("cpoDtlLineNum", (String) asnRecordListForWh.get(i).get("CPO_DTL_LINE_NUM"));
                ssmParam.put("cpoDtlLineSubNum", "000");
                ssmParam.put("shpgPlnNum", (String) asnRecordListForWh.get(i).get("SHPG_PLN_NUM"));

                List<Map<String, Object>> intangibleAsnRecordSetCompList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getIntangibleAsnRecordSetCompListForWh", ssmParam);

                for (Map<String, Object> intangibleAsnRecordSetCompMap : intangibleAsnRecordSetCompList) {

                    // check LAK Assign
                    if (lakAssigned(intangibleAsnRecordSetCompMap)) {

                        Map<String, Object> asnRecord = new HashMap<String, Object>();

                        asnRecord = setAsnValue(intangibleAsnRecordSetCompMap);

                        asnRecordList.add(asnRecord);
                    } else {
                        asnRemoveRecordList.add(asnRecordListForWh.get(i));
                    }

                }

            }

        }

        asnRecordListForWh.removeAll(asnRemoveRecordList);

        // only intangible
        // ***********************************************
        ssmParam.put("salesDate", salesDate);
        ssmParam.put("trxLineSubNum", "000");
        ssmParam.put("shpgStsCd_Can", SHPG_STS.CANCELLED);
        ssmParam.put(BIND_BILL_TO_CUST_CD_LIST, groupCompanyList.get(BILL_TO_CUST_CD));
        ssmParam.put(BIND_SELL_TO_CUST_CD_LIST, groupCompanyList.get(SELL_TO_CUST_CD));

        List<String> onlyInangibleShpgStsCdList = new ArrayList<String>();

        onlyInangibleShpgStsCdList.add(SHPG_STS.SHIPPED);
        onlyInangibleShpgStsCdList.add(SHPG_STS.ARRIVED);
        onlyInangibleShpgStsCdList.add(SHPG_STS.INSTALLED);
        onlyInangibleShpgStsCdList.add(SHPG_STS.VALIDATED);

        // QC#18182 Start
        List<String> cpoOrdTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(this.cpoOrdTpCd)) {

            String[] cpoOrdTpCdTmp = this.cpoOrdTpCd.split(",");

            for (int j = 0; j < cpoOrdTpCdTmp.length; j++) {

                cpoOrdTpCdList.add(cpoOrdTpCdTmp[j]);

            }

        }

        ssmParam.put("cpoOrdTpCdList", cpoOrdTpCdList);
        ssmParam.put("shpgStsCdList", onlyInangibleShpgStsCdList);
        ssmParam.put("shpgStsCdShip", SHPG_STS.SHIPPED);

        List<Map<String, Object>> intangibleOnlyAsnRecordList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getIntangibleOnlyAsnRecordList", ssmParam);

        for (Map<String, Object> intangibleOnlyAsnRecordMap : intangibleOnlyAsnRecordList) {

            BigDecimal minLakQty = (BigDecimal) intangibleOnlyAsnRecordMap.get("MIN_LAK_QTY");
            String setShipStsCd =  (String) intangibleOnlyAsnRecordMap.get("SET_SHIP_STS_CD");
            // QC#52580 Add
            BigDecimal lakQty = (BigDecimal) intangibleOnlyAsnRecordMap.get("LAK_QTY");
            String setFlg = (String) intangibleOnlyAsnRecordMap.get("SET_FLG");

            // QC#52580 Mod Start
            if (ZYPConstant.FLG_ON_Y.equals(setFlg)) {
            	// Set Item Process.
            	if (ZYPCommonFunc.hasValue(minLakQty) //
                        && BigDecimal.ZERO.compareTo(minLakQty) != 0 //
                        && this.shpgStatusCode.contains(setShipStsCd)) {

                   Map<String, Object> asnRecord = new HashMap<String, Object>();

                   asnRecord = setAsnValue(intangibleOnlyAsnRecordMap);

                   asnRecordList.add(asnRecord);
               }

            } else {
            	if (ZYPCommonFunc.hasValue(lakQty) //
                        && BigDecimal.ZERO.compareTo(lakQty) != 0
                        && this.shpgStatusCode.contains(setShipStsCd)) {

                   Map<String, Object> asnRecord = new HashMap<String, Object>();

                   asnRecord = setAsnValue(intangibleOnlyAsnRecordMap);

                   asnRecordList.add(asnRecord);
               }
            }
            // QC#52580 Mod End

        }
        // QC#18182 End

        // duplicate remove
        // ***********************************************
        Map<String, Object> asnRecordCache = new HashMap<String, Object>();

        for (Map<String, Object> asnRecordMap : asnRecordList) {

            String cacheKey = (String) asnRecordMap.get("CPO_ORD_NUM") + (String) asnRecordMap.get("CPO_DTL_LINE_NUM") + (String) asnRecordMap.get("SHPG_PLN_NUM") + (String) asnRecordMap.get("MDSE_CD");

            if (asnRecordCache.containsKey(cacheKey)) {
                continue;
            }

            asnRecordListReturn.add(asnRecordMap);

            asnRecordCache.put(cacheKey, "CASH");

        }

        // set Dummy SO#
        // ***********************************************
        for (Map<String, Object> asnRecordListReturnMap : asnRecordListReturn) {

            if (!ZYPCommonFunc.hasValue((String) asnRecordListReturnMap.get("SO_NUM"))) {

                asnRecordListReturnMap = setDummySoNumAndSoSlpNum(asnRecordListReturnMap);

            }

        }

        // set Qty adjust
        // ***********************************************
        for (Map<String, Object> asnRecordListReturnMap : asnRecordListReturn) {

            if (ZYPCommonFunc.hasValue((String) asnRecordListReturnMap.get("SET_MDSE_CD"))) {

                asnRecordListReturnMap = adjustSetQty(asnRecordListReturnMap);

            }
        }

        return asnRecordListReturn;

    }

    /**
     * setDummySoNumAndSoSlpNum
     * @param map
     * @return
     */
    private Map<String, Object> setDummySoNumAndSoSlpNum(Map<String, Object> map) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        String dummySoNum = (String) map.get("CPO_ORD_NUM");
        String dummysoSlpNum = (String) map.get("CPO_DTL_LINE_NUM") + (String) map.get("CPO_DTL_LINE_SUB_NUM");

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dummySoNum", dummySoNum);
        ssmParam.put("asnSoNum", dummySoNum + '%');

        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDummySoNumAndSoSlpNum", ssmParam);

        map.put("SO_NUM", (String) result.get("DUMMY_SO_NUM"));
        map.put("SO_SLP_NUM", dummysoSlpNum);

        return map;

    }

    private Map<String, Object> adjustSetQty(Map<String, Object> map) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
        ssmParam.put("prntMdseCd", (String) map.get("SET_MDSE_CD"));
        ssmParam.put("childMdseCd", (String) map.get("MDSE_CD"));
        ssmParam.put("trxHdrNum", (String) map.get("CPO_ORD_NUM"));
        ssmParam.put("trxLineNum", (String) map.get("CPO_DTL_LINE_NUM"));
        ssmParam.put("soNum", (String) map.get("SO_NUM"));
        // QC#20878
        ssmParam.put("soSlpNum", (String) map.get("SO_SLP_NUM"));

        Map<String, Object> childCmpsn = (Map<String, Object>) ssmBatchClient.queryObject("getCmpsn", ssmParam);

        BigDecimal zero = new BigDecimal("0");

        if (childCmpsn != null && !childCmpsn.isEmpty() && ((BigDecimal) childCmpsn.get("COUNT")).compareTo(zero) != 0) {
            map.put("SHIP_QTY", ((BigDecimal) childCmpsn.get("COUNT")).toString());
        }

        return map;

    }

    /**
     * setAsnValue
     * @param Map<String, Object> map
     * @return Map<String, String> asnRecord
     */
    private Map<String, Object> setAsnValue(Map<String, Object> map) {

        Map<String, Object> asnRecord = new HashMap<String, Object>();

        asnRecord.put("GLBL_CMPY_CD", (String) map.get("GLBL_CMPY_CD"));
        asnRecord.put("CUST_ISS_PO_NUM", (String) map.get("CUST_ISS_PO_NUM"));
        asnRecord.put("CPO_ORD_NUM", (String) map.get("CPO_ORD_NUM"));
        asnRecord.put("CUST_ISS_PO_DT", (String) map.get("CUST_ISS_PO_DT"));
        asnRecord.put("CPO_ORD_TP_CD", (String) map.get("CPO_ORD_TP_CD"));
        asnRecord.put("REV_RECOG_METH_CD", (String) map.get("REV_RECOG_METH_CD"));
        asnRecord.put("EDI_NUM", (String) map.get("EDI_NUM"));
        asnRecord.put("EDI_SUB_NUM", (String) map.get("EDI_SUB_NUM"));
        asnRecord.put("CPO_DTL_LINE_NUM", (String) map.get("CPO_DTL_LINE_NUM"));
        asnRecord.put("CPO_DTL_LINE_SUB_NUM", (String) map.get("CPO_DTL_LINE_SUB_NUM"));
        asnRecord.put("MDSE_CD", (String) map.get("MDSE_CD"));
        asnRecord.put("SHPG_STS_CD", (String) map.get("SHPG_STS_CD"));
        asnRecord.put("ACTL_ARV_DT", (String) map.get("ACTL_ARV_DT"));
        asnRecord.put("SHPG_PLN_NUM", (String) map.get("SHPG_PLN_NUM"));
        asnRecord.put("TRX_HDR_NUM", (String) map.get("TRX_HDR_NUM"));
        asnRecord.put("TRX_LINE_NUM", (String) map.get("TRX_LINE_NUM"));
        asnRecord.put("TRX_LINE_SUB_NUM", (String) map.get("TRX_LINE_SUB_NUM"));
        asnRecord.put("THIRD_PTY_INV_NUM", (String) map.get("THIRD_PTY_INV_NUM"));
        asnRecord.put("PO_REQ_FLG", (String) map.get("PO_REQ_FLG"));
        asnRecord.put("SO_CLOSE_FLG", (String) map.get("SO_CLOSE_FLG"));
        asnRecord.put("ORD_QTY", (BigDecimal) map.get("ORD_QTY"));
        asnRecord.put("SHIP_CPLT_CD", (String) map.get("SHIP_CPLT_CD"));
        asnRecord.put("SET_MDSE_CD", (String) map.get("SET_MDSE_CD"));
        asnRecord.put("SET_ITEM_SHIP_CPLT_FLG", (String) map.get("SET_ITEM_SHIP_CPLT_FLG"));
        asnRecord.put("BILL_TO_CUST_CD", (String) map.get("BILL_TO_CUST_CD"));
        asnRecord.put("SELL_TO_CUST_CD", (String) map.get("SELL_TO_CUST_CD"));
        asnRecord.put("SHIP_TO_CUST_CD", (String) map.get("SHIP_TO_CUST_CD"));
        asnRecord.put("SHPG_MODE_CD", (String) map.get("SHPG_MODE_CD"));
        asnRecord.put("SHPG_SVC_LVL_CD", (String) map.get("SHPG_SVC_LVL_CD"));
        asnRecord.put("FRT_CHRG_METH_CD", (String) map.get("FRT_CHRG_METH_CD"));
        asnRecord.put("FRT_CHRG_TO_CD", (String) map.get("FRT_CHRG_TO_CD"));
        asnRecord.put("EZUPTIME", (String) map.get("EZUPTIME"));
        asnRecord.put("WH_CD", (String) map.get("WH_CD"));
        asnRecord.put("SHIP_DT_TM_TS", (String) map.get("SHIP_DT_TM_TS"));
        asnRecord.put("TOT_SHIP_WT", (String) map.get("TOT_SHIP_WT"));
        asnRecord.put("TOT_FRT_AMT", (String) map.get("TOT_FRT_AMT"));
        asnRecord.put("SO_NUM", (String) map.get("SO_NUM"));
        asnRecord.put("SO_SLP_NUM", (String) map.get("SO_SLP_NUM"));
        asnRecord.put("PRO_NUM", (String) map.get("PRO_NUM"));
        asnRecord.put("VND_CD", (String) map.get("VND_CD"));
        asnRecord.put("BOL_NUM", (String) map.get("BOL_NUM"));
        asnRecord.put("SHIP_QTY", (map.get("SHIP_QTY")).toString());
        asnRecord.put("FROM_STK_STS_CD", (String) map.get("FROM_STK_STS_CD"));
        asnRecord.put("AUTO_LIC_ALLOC_FLG", (String) map.get("AUTO_LIC_ALLOC_FLG"));
        asnRecord.put("TOT_FRT_AMT_DTL", (String) map.get("TOT_FRT_AMT_DTL"));
        asnRecord.put("TOT_SHIP_WT_DTL", (String) map.get("TOT_SHIP_WT_DTL"));
        asnRecord.put("INVTY_LOC_CD", (String) map.get("INVTY_LOC_CD"));

        return asnRecord;

    }

    /**
     * getMaxSoSlpNum
     * @param soNum String
     * @return String
     */
    private String getMaxSoSlpNum(String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
        ssmParam.put("soNum", soNum);

        String result = (String) ssmBatchClient.queryObject("getMaxSoSlpNum", ssmParam);

        return result;

    }

    /**
     * @param map
     * @return
     */
    private boolean parentItemShipped(Map<String, Object> map) {

        if (ZYPCommonFunc.hasValue((String) map.get("SET_SHPG_PLN_NUM"))) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
            ssmParam.put("setShpgPlnNum", (String) map.get("SET_SHPG_PLN_NUM"));
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);

            String result = (String) ssmBatchClient.queryObject("getSetShpgPlnNUm", ssmParam);

            // set parent exists in asn.
            if (ZYPCommonFunc.hasValue(result)) {

                return true;
            }

            // set parent does not exists in asn.
            return false;

        } else {

            return true;
        }

    }

    /**
     * lakAssigned
     * @param map
     * @return
     */
    private boolean lakAssigned(Map<String, Object> map) {

        boolean lakAssigned = false;

        if (ZYPConstant.FLG_ON_Y.equals(map.get("AUTO_LIC_ALLOC_FLG"))) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
            ssmParam.put("cpoOrdNum", (String) map.get("CPO_ORD_NUM"));
            ssmParam.put("cpoDtlLineNum", (String) map.get("CPO_DTL_LINE_NUM"));
            ssmParam.put("cpoDtlLineSubNum", (String) map.get("CPO_DTL_LINE_SUB_NUM"));
            ssmParam.put("mdseCd", (String) map.get("MDSE_CD"));
            ssmParam.put("lakStsCd", LAK_STS.USED);

            List<String> cpoOrdTpCdList = new ArrayList<String>();

            if (ZYPCommonFunc.hasValue(this.cpoOrdTpCd)) {

                String[] cpoOrdTpCdTmp = this.cpoOrdTpCd.split(",");

                for (int j = 0; j < cpoOrdTpCdTmp.length; j++) {

                    cpoOrdTpCdList.add(cpoOrdTpCdTmp[j]);

                }

            }

            ssmParam.put("cpoOrdTpCdList", cpoOrdTpCdList);

            String lakAssigne = (String) ssmBatchClient.queryObject("checkLakAssingn", ssmParam);

            if (ZYPCommonFunc.hasValue(lakAssigne) && Integer.parseInt(lakAssigne) > 0) {

                lakAssigned = true;
            }

        } else {

            lakAssigned = true;
        }

        return lakAssigned;

    }

    // QC#7523 END

    /**
     * getAsnRecordListForVendor
     * @param groupCompanyList
     * @return
     */
    private List<Map> getAsnRecordListForVendor(Map<String, List> groupCompanyList) {

        // order info
        List<Map> orderInfo = getOrderInfoForVendor(groupCompanyList);

        // if order info Set Item Ship Complete Flag=N search shpg ord
        List<Map> asnRecordListForVendor = getShippingOrderInfoForVendor(groupCompanyList, orderInfo);

        return asnRecordListForVendor;
    }

    /**
     * getOrderInfoForVendor
     * @param groupCompanyList
     * @return
     */
    private List<Map> getOrderInfoForVendor(Map<String, List> groupCompanyList) {

        List<String> shpgStsCdList = new ArrayList<String>();
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        if (MODE_NIGHT.equals(userVariable1)) {

            ssmParam.put("modeNight", userVariable1);

        }

        shpgStsCdList.add(SHPG_STS.SHIPPED);
        shpgStsCdList.add(SHPG_STS.ARRIVED);
        shpgStsCdList.add(SHPG_STS.INSTALLED);

        ssmParam.put(BIND_BILL_TO_CUST_CD_LIST, groupCompanyList.get(BILL_TO_CUST_CD));
        ssmParam.put(BIND_SELL_TO_CUST_CD_LIST, groupCompanyList.get(SELL_TO_CUST_CD));
        ssmParam.put(BIND_SHPG_STS_CD_LIST, shpgStsCdList);
        ssmParam.put(BIND_ETA_DT, salesDate);
        ssmParam.put(BIND_TRX_LINE_SUB_NUM, TRX_LINE_SUB_NUM_000);
        ssmParam.put(BIND_PO_REQ_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(BIND_SO_CLOSE_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(BIND_TRX_CD, TRX_LINE_SUB_NUM_000);
        ssmParam.put(BIND_TRX_RSN_CD, "01");
        ssmParam.put(BIND_ASN_REQ_FLG, ZYPConstant.FLG_OFF_N);
        ssmParam.put(BIND_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);
        ssmParam.put(BIND_EZUPTIME, dsBizLastUpdTs);
        ssmParam.put("salesDate", salesDate);

        List<Map> asnRecordList = new ArrayList<Map>();

        try {
            preparedStatement = ssmLlcClient.createPreparedStatement("getOrderInfoForVendor", ssmParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> asnRecord = new HashMap<String, Object>();
                asnRecord.put(GLBL_CMPY_CD, resultSet.getString(GLBL_CMPY_CD));
                asnRecord.put(REV_RECOG_METH_CD, resultSet.getString(REV_RECOG_METH_CD));
                asnRecord.put(CUST_ISS_PO_NUM, resultSet.getString(CUST_ISS_PO_NUM));
                asnRecord.put(CPO_ORD_NUM, resultSet.getString(CPO_ORD_NUM));
                asnRecord.put(CUST_ISS_PO_DT, resultSet.getString(CUST_ISS_PO_DT));
                asnRecord.put(EDI_NUM, resultSet.getString(EDI_NUM));
                asnRecord.put(EDI_SUB_NUM, resultSet.getString(EDI_SUB_NUM));
                asnRecord.put(CPO_DTL_LINE_NUM, resultSet.getString(CPO_DTL_LINE_NUM));
                asnRecord.put(CPO_DTL_LINE_SUB_NUM, resultSet.getString(CPO_DTL_LINE_SUB_NUM));
                asnRecord.put(SHPG_STS_CD, resultSet.getString(SHPG_STS_CD));
                asnRecord.put(ACTL_ARV_DT, resultSet.getString(ACTL_ARV_DT));
                asnRecord.put(SHPG_SVC_LVL_CD, resultSet.getString(SHPG_SVC_LVL_CD));
                asnRecord.put(FRT_CHRG_TO_CD, resultSet.getString(FRT_CHRG_TO_CD));
                asnRecord.put(FRT_CHRG_METH_CD, resultSet.getString(FRT_CHRG_METH_CD));
                asnRecord.put(MDSE_CD, resultSet.getString(MDSE_CD));
                asnRecord.put(THIRD_PTY_INV_NUM, resultSet.getString(THIRD_PTY_INV_NUM));
                asnRecord.put(ORD_QTY, resultSet.getString(ORD_QTY));
                asnRecord.put(ACTL_SHIP_DT, resultSet.getString(ACTL_SHIP_DT));
                asnRecord.put(TRX_LINE_NUM, resultSet.getString(TRX_LINE_NUM));
                asnRecord.put(TRX_HDR_NUM, resultSet.getString(TRX_HDR_NUM));
                asnRecord.put(TRX_LINE_SUB_NUM, resultSet.getString(TRX_LINE_SUB_NUM));
                asnRecord.put(PO_ORD_NUM, resultSet.getString(PO_ORD_NUM));
                asnRecord.put(SHPG_PLN_NUM, resultSet.getString(SHPG_PLN_NUM));
                asnRecord.put(PO_REQ_FLG, ZYPConstant.FLG_ON_Y);
                asnRecord.put(PRO_NUM, resultSet.getString(PRO_NUM));
                asnRecord.put(VND_CD, resultSet.getString(VND_CD));
                asnRecord.put(BOL_NUM, resultSet.getString(BOL_NUM));
                asnRecord.put(SET_MDSE_CD, resultSet.getString(SET_MDSE_CD));
                asnRecord.put(BILL_TO_CUST_CD, resultSet.getString(BILL_TO_CUST_CD));
                asnRecord.put(SELL_TO_CUST_CD, resultSet.getString(SELL_TO_CUST_CD));
                asnRecord.put(SHIP_TO_CUST_CD, resultSet.getString(SHIP_TO_CUST_CD));
                asnRecord.put(FROM_STK_STS_CD, resultSet.getString(FROM_STK_STS_CD));
                asnRecord.put(INVTY_CTRL_FLG, resultSet.getString(INVTY_CTRL_FLG));

                if (ZYPConstant.FLG_ON_Y.equals(resultSet.getString(SET_ITEM_SHIP_CPLT_FLG))) {

                    if (Arrays.asList(CPO_ORD_TP.SALES, CPO_ORD_TP.EXPENSE, CPO_ORD_TP.TRIAL, CPO_ORD_TP.LOAN).contains(resultSet.getString(CPO_ORD_TP_CD))) {

                        if (!excludeForSetShipCplt(resultSet)) {

                            continue;

                        }

                    }
                }

                asnRecordList.add(asnRecord);

            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

        }

        return asnRecordList;

    }

    /**
     * getShippingOrderInfoForVendor
     * @param groupCompanyList
     * @param orderInfoList
     * @return
     */
    private List<Map> getShippingOrderInfoForVendor(Map<String, List> groupCompanyList, List<Map> orderInfoList) {

        List<Map> asnRecordList = new ArrayList<Map>();
        Map<String, String> dummySoList = new HashMap<String, String>();

        int soSlpNum = 0;

        for (Map<String, String> orderInfo : orderInfoList) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
            ssmParam.put(BIND_TRX_HDR_NUM, orderInfo.get(TRX_HDR_NUM));
            ssmParam.put(BIND_ASN_REQ_FLG, ZYPConstant.FLG_OFF_N);
            ssmParam.put(BIND_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);
            ssmParam.put(BIND_BILL_TO_CUST_CD_LIST, groupCompanyList.get(BILL_TO_CUST_CD));
            ssmParam.put(BIND_SELL_TO_CUST_CD_LIST, groupCompanyList.get(SELL_TO_CUST_CD));
            ssmParam.put(BIND_TRX_LINE_NUM, orderInfo.get(TRX_LINE_NUM));
            ssmParam.put(BIND_TRX_LINE_SUB_NUM, orderInfo.get(TRX_LINE_SUB_NUM));
            ssmParam.put(BIND_MDSE_CD, orderInfo.get(MDSE_CD));
            ssmParam.put(BIND_SHIP_QTY, orderInfo.get(ORD_QTY));
            ssmParam.put(BIND_PO_PROC_STS_CD, SO_PROC_STS.SHIP);
            ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {

                preparedStatement = ssmLlcClient.createPreparedStatement("getShippingOrderInfoForVendor", ssmParam);
                resultSet = preparedStatement.executeQuery();
                List<Map> shippingOrderInfo = new ArrayList<Map>();

                while (resultSet.next()) {

                    Map<String, Object> asnRecord = new HashMap<String, Object>();
                    asnRecord.put(BILL_TO_CUST_CD, resultSet.getString(BILL_TO_CUST_CD));
                    asnRecord.put(SELL_TO_CUST_CD, resultSet.getString(SELL_TO_CUST_CD));
                    asnRecord.put(SHIP_TO_CUST_CD, resultSet.getString(SHIP_TO_CUST_CD));
                    asnRecord.put(SHPG_MODE_CD, resultSet.getString(SHPG_MODE_CD));
                    asnRecord.put(SO_NUM, resultSet.getString(SO_NUM));
                    asnRecord.put(SO_SLP_NUM, resultSet.getString(SO_SLP_NUM));
                    asnRecord.put(WH_CD, resultSet.getString(WH_CD));
                    asnRecord.put(INVTY_LOC_CD, resultSet.getString(INVTY_LOC_CD));
                    asnRecord.put(SHIP_DT_TM_TS, resultSet.getString(SHIP_DT_TM_TS));
                    asnRecord.put(TOT_SHIP_WT, resultSet.getString(TOT_SHIP_WT));
                    asnRecord.put(TOT_FRT_AMT, resultSet.getString(TOT_FRT_AMT));
                    asnRecord.put(PRO_NUM, resultSet.getString(CONF_DTL_PRO_NUM));
                    asnRecord.put(VND_CD, resultSet.getString(CONF_DTL_VND_CD));
                    asnRecord.put(BOL_NUM, resultSet.getString(CONF_DTL_BOL_NUM));
                    asnRecord.put(SHIP_QTY, resultSet.getString(SHIP_QTY));
                    asnRecord.put(TOT_FRT_AMT_DTL, resultSet.getString(TOT_FRT_AMT_DTL));
                    asnRecord.put(FROM_STK_STS_CD, resultSet.getString(FROM_STK_STS_CD));
                    asnRecord.put(TOT_SHIP_WT_DTL, resultSet.getString(TOT_SHIP_WT_DTL));
                    asnRecord.put(SET_MDSE_CD, resultSet.getString(SET_MDSE_CD));

                    shippingOrderInfo.add(asnRecord);
                }

                // only intangible
                // Map<String, Object> asnRecord = null;

                Map<String, Object> asnRecord = new HashMap<String, Object>();

                if (shippingOrderInfo.size() == 0 && ZYPConstant.FLG_OFF_N.equals(orderInfo.get(INVTY_CTRL_FLG))) {

                    asnRecord.put(BILL_TO_CUST_CD, orderInfo.get(BILL_TO_CUST_CD));
                    asnRecord.put(SELL_TO_CUST_CD, orderInfo.get(SELL_TO_CUST_CD));
                    asnRecord.put(SHIP_TO_CUST_CD, orderInfo.get(SHIP_TO_CUST_CD));
                    asnRecord.put(SO_NUM, orderInfo.get(TRX_HDR_NUM));
                    asnRecord.put(SO_SLP_NUM, orderInfo.get(TRX_LINE_NUM));
                    asnRecord.put(SHIP_DT_TM_TS, orderInfo.get(ACTL_SHIP_DT));
                    asnRecord.put(VND_CD, orderInfo.get(VND_CD));
                    asnRecord.put(BOL_NUM, orderInfo.get(CONF_DTL_BOL_NUM));
                    asnRecord.put(SHIP_QTY, orderInfo.get(ORD_QTY));
                    asnRecord.put(FROM_STK_STS_CD, orderInfo.get(FROM_STK_STS_CD));
                    asnRecord.put(SET_MDSE_CD, orderInfo.get(SET_MDSE_CD));
                    asnRecord.put(GLBL_CMPY_CD, orderInfo.get(GLBL_CMPY_CD));
                    asnRecord.put(REV_RECOG_METH_CD, orderInfo.get(REV_RECOG_METH_CD));
                    asnRecord.put(CUST_ISS_PO_NUM, orderInfo.get(CUST_ISS_PO_NUM));
                    asnRecord.put(CPO_ORD_NUM, orderInfo.get(CPO_ORD_NUM));
                    asnRecord.put(CPO_DTL_LINE_NUM, orderInfo.get(CPO_DTL_LINE_NUM));
                    asnRecord.put(CPO_DTL_LINE_SUB_NUM, orderInfo.get(CPO_DTL_LINE_SUB_NUM));
                    asnRecord.put(EDI_NUM, orderInfo.get(EDI_NUM));
                    asnRecord.put(EDI_SUB_NUM, orderInfo.get(EDI_SUB_NUM));
                    asnRecord.put(SHPG_STS_CD, orderInfo.get(SHPG_STS_CD));
                    asnRecord.put(ACTL_ARV_DT, orderInfo.get(ACTL_ARV_DT));
                    asnRecord.put(SHPG_SVC_LVL_CD, orderInfo.get(SHPG_SVC_LVL_CD));
                    asnRecord.put(FRT_CHRG_TO_CD, orderInfo.get(FRT_CHRG_TO_CD));
                    asnRecord.put(FRT_CHRG_METH_CD, orderInfo.get(FRT_CHRG_METH_CD));
                    asnRecord.put(MDSE_CD, orderInfo.get(MDSE_CD));
                    asnRecord.put(THIRD_PTY_INV_NUM, orderInfo.get(THIRD_PTY_INV_NUM));
                    asnRecord.put(ORD_QTY, orderInfo.get(ORD_QTY));
                    asnRecord.put(ACTL_SHIP_DT, orderInfo.get(ACTL_SHIP_DT));
                    asnRecord.put(TRX_HDR_NUM, orderInfo.get(TRX_HDR_NUM));
                    asnRecord.put(TRX_LINE_NUM, orderInfo.get(TRX_LINE_NUM));
                    asnRecord.put(TRX_LINE_SUB_NUM, orderInfo.get(TRX_LINE_SUB_NUM));
                    asnRecord.put(PO_ORD_NUM, orderInfo.get(PO_ORD_NUM));
                    asnRecord.put(SHPG_PLN_NUM, orderInfo.get(SHPG_PLN_NUM));
                    asnRecord.put(PO_REQ_FLG, orderInfo.get(PO_REQ_FLG));

                    if (ZYPCommonFunc.hasValue(orderInfo.get(PRO_NUM))) {
                        asnRecord.put(PRO_NUM, orderInfo.get(PRO_NUM));
                    }

                    if (ZYPCommonFunc.hasValue(orderInfo.get(VND_CD))) {
                        asnRecord.put(VND_CD, orderInfo.get(VND_CD));
                    }

                    if (ZYPCommonFunc.hasValue(orderInfo.get(BOL_NUM))) {
                        asnRecord.put(BOL_NUM, orderInfo.get(BOL_NUM));
                    }

                    asnRecordList.add(asnRecord);

                } else if (shippingOrderInfo.size() == 0) {

                    continue;

                } else if (shippingOrderInfo.size() == 1) {

                    asnRecord = shippingOrderInfo.get(0);
                    asnRecord.put(GLBL_CMPY_CD, orderInfo.get(GLBL_CMPY_CD));
                    asnRecord.put(REV_RECOG_METH_CD, orderInfo.get(REV_RECOG_METH_CD));
                    asnRecord.put(CUST_ISS_PO_NUM, orderInfo.get(CUST_ISS_PO_NUM));
                    asnRecord.put(CPO_ORD_NUM, orderInfo.get(CPO_ORD_NUM));
                    asnRecord.put(CPO_DTL_LINE_NUM, orderInfo.get(CPO_DTL_LINE_NUM));
                    asnRecord.put(CPO_DTL_LINE_SUB_NUM, orderInfo.get(CPO_DTL_LINE_SUB_NUM));
                    asnRecord.put(EDI_NUM, orderInfo.get(EDI_NUM));
                    asnRecord.put(EDI_SUB_NUM, orderInfo.get(EDI_SUB_NUM));
                    asnRecord.put(SHPG_STS_CD, orderInfo.get(SHPG_STS_CD));
                    asnRecord.put(ACTL_ARV_DT, orderInfo.get(ACTL_ARV_DT));
                    asnRecord.put(SHPG_SVC_LVL_CD, orderInfo.get(SHPG_SVC_LVL_CD));
                    asnRecord.put(FRT_CHRG_TO_CD, orderInfo.get(FRT_CHRG_TO_CD));
                    asnRecord.put(FRT_CHRG_METH_CD, orderInfo.get(FRT_CHRG_METH_CD));
                    asnRecord.put(MDSE_CD, orderInfo.get(MDSE_CD));
                    asnRecord.put(THIRD_PTY_INV_NUM, orderInfo.get(THIRD_PTY_INV_NUM));
                    asnRecord.put(ORD_QTY, orderInfo.get(ORD_QTY));
                    asnRecord.put(ACTL_SHIP_DT, orderInfo.get(ACTL_SHIP_DT));
                    asnRecord.put(TRX_HDR_NUM, orderInfo.get(TRX_HDR_NUM));
                    asnRecord.put(TRX_LINE_NUM, orderInfo.get(TRX_LINE_NUM));
                    asnRecord.put(TRX_LINE_SUB_NUM, orderInfo.get(TRX_LINE_SUB_NUM));
                    asnRecord.put(PO_ORD_NUM, orderInfo.get(PO_ORD_NUM));
                    asnRecord.put(SHPG_PLN_NUM, orderInfo.get(SHPG_PLN_NUM));
                    asnRecord.put(PO_REQ_FLG, orderInfo.get(PO_REQ_FLG));

                    if (ZYPCommonFunc.hasValue(orderInfo.get(PRO_NUM))) {
                        asnRecord.put(PRO_NUM, orderInfo.get(PRO_NUM));
                    }

                    if (ZYPCommonFunc.hasValue(orderInfo.get(VND_CD))) {
                        asnRecord.put(VND_CD, orderInfo.get(VND_CD));
                    }

                    if (ZYPCommonFunc.hasValue(orderInfo.get(BOL_NUM))) {
                        asnRecord.put(BOL_NUM, orderInfo.get(BOL_NUM));
                    }

                    asnRecordList.add(asnRecord);

                } else {

                    String cacheKey = this.cusaGlblCmpyCd + orderInfo.get(MDSE_CD) + orderInfo.get(TRX_HDR_NUM) + orderInfo.get(TRX_LINE_NUM) + orderInfo.get(TRX_LINE_SUB_NUM) + orderInfo.get(ORD_QTY) + orderInfo.get(ACTL_SHIP_DT);

                    if (shippingOrderInfoCache.containsKey(cacheKey) && 1 <= shippingOrderInfoCache.get(cacheKey).size()) {

                        asnRecord = (Map<String, Object>) shippingOrderInfoCache.get(cacheKey).remove(0);
                        asnRecord.put(GLBL_CMPY_CD, orderInfo.get(GLBL_CMPY_CD));
                        asnRecord.put(REV_RECOG_METH_CD, orderInfo.get(REV_RECOG_METH_CD));
                        asnRecord.put(CUST_ISS_PO_NUM, orderInfo.get(CUST_ISS_PO_NUM));
                        asnRecord.put(CPO_ORD_NUM, orderInfo.get(CPO_ORD_NUM));
                        asnRecord.put(CPO_DTL_LINE_NUM, orderInfo.get(CPO_DTL_LINE_NUM));
                        asnRecord.put(CPO_DTL_LINE_SUB_NUM, orderInfo.get(CPO_DTL_LINE_SUB_NUM));
                        asnRecord.put(EDI_NUM, orderInfo.get(EDI_NUM));
                        asnRecord.put(EDI_SUB_NUM, orderInfo.get(EDI_SUB_NUM));
                        asnRecord.put(SHPG_STS_CD, orderInfo.get(SHPG_STS_CD));
                        asnRecord.put(ACTL_ARV_DT, orderInfo.get(ACTL_ARV_DT));
                        asnRecord.put(SHPG_SVC_LVL_CD, orderInfo.get(SHPG_SVC_LVL_CD));
                        asnRecord.put(FRT_CHRG_TO_CD, orderInfo.get(FRT_CHRG_TO_CD));
                        asnRecord.put(FRT_CHRG_METH_CD, orderInfo.get(FRT_CHRG_METH_CD));
                        asnRecord.put(MDSE_CD, orderInfo.get(MDSE_CD));
                        asnRecord.put(THIRD_PTY_INV_NUM, orderInfo.get(THIRD_PTY_INV_NUM));
                        asnRecord.put(ORD_QTY, orderInfo.get(ORD_QTY));
                        asnRecord.put(ACTL_SHIP_DT, orderInfo.get(ACTL_SHIP_DT));
                        asnRecord.put(TRX_HDR_NUM, orderInfo.get(TRX_HDR_NUM));
                        asnRecord.put(TRX_LINE_NUM, orderInfo.get(TRX_LINE_NUM));
                        asnRecord.put(TRX_LINE_SUB_NUM, orderInfo.get(TRX_LINE_SUB_NUM));
                        asnRecord.put(PO_ORD_NUM, orderInfo.get(PO_ORD_NUM));
                        asnRecord.put(SHPG_PLN_NUM, orderInfo.get(SHPG_PLN_NUM));
                        asnRecord.put(PO_REQ_FLG, orderInfo.get(PO_REQ_FLG));

                        if (ZYPCommonFunc.hasValue(orderInfo.get(PRO_NUM))) {
                            asnRecord.put(PRO_NUM, orderInfo.get(PRO_NUM));
                        }

                        if (ZYPCommonFunc.hasValue(orderInfo.get(VND_CD))) {
                            asnRecord.put(VND_CD, orderInfo.get(VND_CD));
                        }

                        if (ZYPCommonFunc.hasValue(orderInfo.get(BOL_NUM))) {
                            asnRecord.put(BOL_NUM, orderInfo.get(BOL_NUM));
                        }

                        asnRecordList.add(asnRecord);

                    } else {

                        shippingOrderInfoCache.put(cacheKey, shippingOrderInfo);

                        asnRecord = shippingOrderInfo.remove(0);
                        asnRecord.put(GLBL_CMPY_CD, orderInfo.get(GLBL_CMPY_CD));
                        asnRecord.put(REV_RECOG_METH_CD, orderInfo.get(REV_RECOG_METH_CD));
                        asnRecord.put(CUST_ISS_PO_NUM, orderInfo.get(CUST_ISS_PO_NUM));
                        asnRecord.put(CPO_ORD_NUM, orderInfo.get(CPO_ORD_NUM));
                        asnRecord.put(CPO_DTL_LINE_NUM, orderInfo.get(CPO_DTL_LINE_NUM));
                        asnRecord.put(CPO_DTL_LINE_SUB_NUM, orderInfo.get(CPO_DTL_LINE_SUB_NUM));
                        asnRecord.put(EDI_NUM, orderInfo.get(EDI_NUM));
                        asnRecord.put(EDI_SUB_NUM, orderInfo.get(EDI_SUB_NUM));
                        asnRecord.put(SHPG_STS_CD, orderInfo.get(SHPG_STS_CD));
                        asnRecord.put(ACTL_ARV_DT, orderInfo.get(ACTL_ARV_DT));
                        asnRecord.put(SHPG_SVC_LVL_CD, orderInfo.get(SHPG_SVC_LVL_CD));
                        asnRecord.put(FRT_CHRG_TO_CD, orderInfo.get(FRT_CHRG_TO_CD));
                        asnRecord.put(FRT_CHRG_METH_CD, orderInfo.get(FRT_CHRG_METH_CD));
                        asnRecord.put(MDSE_CD, orderInfo.get(MDSE_CD));
                        asnRecord.put(THIRD_PTY_INV_NUM, orderInfo.get(THIRD_PTY_INV_NUM));
                        asnRecord.put(ORD_QTY, orderInfo.get(ORD_QTY));
                        asnRecord.put(ACTL_SHIP_DT, orderInfo.get(ACTL_SHIP_DT));
                        asnRecord.put(TRX_HDR_NUM, orderInfo.get(TRX_HDR_NUM));
                        asnRecord.put(TRX_LINE_NUM, orderInfo.get(TRX_LINE_NUM));
                        asnRecord.put(TRX_LINE_SUB_NUM, orderInfo.get(TRX_LINE_SUB_NUM));
                        asnRecord.put(PO_ORD_NUM, orderInfo.get(PO_ORD_NUM));
                        asnRecord.put(SHPG_PLN_NUM, orderInfo.get(SHPG_PLN_NUM));
                        asnRecord.put(PO_REQ_FLG, orderInfo.get(PO_REQ_FLG));

                        if (ZYPCommonFunc.hasValue(orderInfo.get(PRO_NUM))) {
                            asnRecord.put(PRO_NUM, orderInfo.get(PRO_NUM));
                        }

                        if (ZYPCommonFunc.hasValue(orderInfo.get(VND_CD))) {
                            asnRecord.put(VND_CD, orderInfo.get(VND_CD));
                        }

                        if (ZYPCommonFunc.hasValue(orderInfo.get(BOL_NUM))) {
                            asnRecord.put(BOL_NUM, orderInfo.get(BOL_NUM));
                        }

                        asnRecordList.add(asnRecord);

                    }

                }

            } catch (SQLException e) {

                sqlExceptionHandler(e);

            } finally {

                S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

            }

        }

        return asnRecordList;

    }

    /**
     * @param asnRecordList
     * @param orderInfo
     * @param dummySoList
     * @param soSlpNum
     */
    private void setAsnRecordForSetVendor(List<Map> asnRecordList, Map<String, String> orderInfo, Map<String, String> dummySoList, int soSlpNum) {

        Map<String, Object> asnRecord = new HashMap<String, Object>();
        String soNum = dummySoList.get(orderInfo.get(TRX_HDR_NUM));
        String slpNum = null;

        if (soSlpNum < 10) {

            slpNum = "00" + String.valueOf(soSlpNum);

        } else if (soSlpNum > 9 && soSlpNum < 100) {

            slpNum = "0" + String.valueOf(soSlpNum);

        } else {

            slpNum = String.valueOf(soSlpNum);
        }

        asnRecord.put(GLBL_CMPY_CD, orderInfo.get(GLBL_CMPY_CD));
        asnRecord.put(REV_RECOG_METH_CD, orderInfo.get(REV_RECOG_METH_CD));
        asnRecord.put(CUST_ISS_PO_NUM, orderInfo.get(CUST_ISS_PO_NUM));
        asnRecord.put(CPO_ORD_NUM, orderInfo.get(TRX_HDR_NUM));
        asnRecord.put(CPO_DTL_LINE_NUM, orderInfo.get(CPO_DTL_LINE_NUM));
        asnRecord.put(CPO_DTL_LINE_SUB_NUM, orderInfo.get(CPO_DTL_LINE_SUB_NUM));
        asnRecord.put(TRX_HDR_NUM, orderInfo.get(TRX_HDR_NUM));
        asnRecord.put(TRX_LINE_NUM, orderInfo.get(TRX_LINE_NUM));
        asnRecord.put(TRX_LINE_SUB_NUM, orderInfo.get(TRX_LINE_SUB_NUM));
        asnRecord.put(BILL_TO_CUST_CD, orderInfo.get(BILL_TO_CUST_CD));
        asnRecord.put(SELL_TO_CUST_CD, orderInfo.get(SELL_TO_CUST_CD));
        asnRecord.put(SHIP_TO_CUST_CD, orderInfo.get(SHIP_TO_CUST_CD));
        asnRecord.put(SHPG_MODE_CD, orderInfo.get(SHPG_MODE_CD));
        asnRecord.put(SO_NUM, soNum);
        asnRecord.put(SO_SLP_NUM, slpNum);
        asnRecord.put(WH_CD, this.dummyWhCd);
        asnRecord.put(INVTY_LOC_CD, this.dummyWhCd);
        asnRecord.put(SHIP_DT_TM_TS, orderInfo.get(ACTL_SHIP_DT));

        if (!ZYPCommonFunc.hasValue(orderInfo.get(PRO_NUM))) {

            asnRecord.put(PRO_NUM, NULL_VAL);

        } else {

            asnRecord.put(PRO_NUM, orderInfo.get(PRO_NUM));

        }

        if (!ZYPCommonFunc.hasValue(orderInfo.get(VND_CD))) {

            asnRecord.put(VND_CD, NULL_VAL);

        } else {

            asnRecord.put(VND_CD, orderInfo.get(VND_CD));
        }

        if (!ZYPCommonFunc.hasValue(orderInfo.get(BOL_NUM))) {

            asnRecord.put(BOL_NUM, NULL_VAL);

        } else {

            asnRecord.put(BOL_NUM, orderInfo.get(BOL_NUM));
        }

        asnRecord.put(SHIP_QTY, orderInfo.get(ORD_QTY));
        asnRecord.put(TOT_FRT_AMT_DTL, orderInfo.get(TOT_FRT_AMT_DTL));
        asnRecord.put(FROM_STK_STS_CD, orderInfo.get(FROM_STK_STS_CD));
        asnRecord.put(TOT_SHIP_WT_DTL, orderInfo.get(TOT_SHIP_WT_DTL));
        asnRecord.put(EDI_NUM, orderInfo.get(EDI_NUM));
        asnRecord.put(EDI_SUB_NUM, orderInfo.get(EDI_SUB_NUM));
        asnRecord.put(SHPG_STS_CD, orderInfo.get(SHPG_STS_CD));
        asnRecord.put(ACTL_ARV_DT, orderInfo.get(ACTL_ARV_DT));
        asnRecord.put(SHPG_SVC_LVL_CD, orderInfo.get(SHPG_SVC_LVL_CD));
        asnRecord.put(FRT_CHRG_TO_CD, orderInfo.get(FRT_CHRG_TO_CD));
        asnRecord.put(FRT_CHRG_METH_CD, orderInfo.get(FRT_CHRG_METH_CD));
        asnRecord.put(MDSE_CD, orderInfo.get(MDSE_CD));
        asnRecord.put(THIRD_PTY_INV_NUM, orderInfo.get(THIRD_PTY_INV_NUM));
        asnRecord.put(ORD_QTY, orderInfo.get(ORD_QTY));
        asnRecord.put(ACTL_SHIP_DT, orderInfo.get(ACTL_SHIP_DT));
        asnRecord.put(PO_ORD_NUM, orderInfo.get(PO_ORD_NUM));
        asnRecord.put(SHPG_PLN_NUM, orderInfo.get(SHPG_PLN_NUM));
        asnRecord.put(PO_REQ_FLG, orderInfo.get(PO_REQ_FLG));
        asnRecord.put(FROM_STK_STS_CD, orderInfo.get(FROM_STK_STS_CD));
        asnRecord.put(SET_MDSE_CD, orderInfo.get(SET_MDSE_CD));

        asnRecordList.add(asnRecord);

    }

    /**
     * excludeForSetShipCplt
     * @param resultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean excludeForSetShipCplt(ResultSet resultSet) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_CPO_ORD_NUM, resultSet.getString(CPO_ORD_NUM));
        ssmParam.put(BIND_CPO_DTL_LINE_NUM, resultSet.getString(CPO_DTL_LINE_NUM));
        ssmParam.put(BIND_SHPG_PLN_NUM, resultSet.getString(SHPG_PLN_NUM));
        ssmParam.put(BIND_CPO_DTL_LINE_SUB_NUM, TRX_LINE_SUB_NUM_000);
        ssmParam.put(BIND_TRX_LINE_SUB_NUM, TRX_LINE_SUB_NUM_000);

        List<Map<String, String>> result = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getSetShipCpltDetail", ssmParam);

        final List<String> cpoOrdTpList = Arrays.asList(new String[] {CPO_ORD_TP.TRIAL_TO_SALES, CPO_ORD_TP.LOAN_TO_SALES });

        for (Map<String, String> resultMap : result) {

            String invtyCtrlFlg = resultMap.get(INVTY_CTRL_FLG);
            String autoLicAllocFlg = resultMap.get(AUTO_LIC_ALLOC_FLG);
            String soCloseFlg = resultMap.get(SO_CLOSE_FLG);
            String shpgStsCd = resultMap.get(SHPG_STS_CD);
            String poReqFlg = resultMap.get(PO_REQ_FLG);
            String cpoHldFlg = resultMap.get(CPO_HLD_FLG);
            String cpoDtlHldFlg = resultMap.get(CPO_DTL_HLD_FLG);
            String shipPlnHldFlg = resultMap.get(SHIP_PLN_HLD_FLG);

            if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {

                if (ZYPConstant.FLG_OFF_N.equals(soCloseFlg) || (MODE_DAILY.equals(userVariable1) && !Arrays.asList(SHPG_STS.SHIPPED, SHPG_STS.ARRIVED).contains(shpgStsCd))) {

                    return false;
                }

                if (ZYPConstant.FLG_OFF_N.equals(soCloseFlg) || MODE_NIGHT.equals(userVariable1) && !Arrays.asList(SHPG_STS.SHIPPED, SHPG_STS.ARRIVED, SHPG_STS.INVOICED).contains(shpgStsCd)) {

                    return false;
                }

            } else {

                // Vendor Drop
                if (ZYPConstant.FLG_ON_Y.equals(poReqFlg)) {

                    if (ZYPConstant.FLG_OFF_N.equals(soCloseFlg) || (MODE_DAILY.equals(userVariable1) && !Arrays.asList(SHPG_STS.SHIPPED, SHPG_STS.ARRIVED).contains(shpgStsCd))) {

                        return false;
                    }

                    if (ZYPConstant.FLG_OFF_N.equals(soCloseFlg) || MODE_NIGHT.equals(userVariable1) && !Arrays.asList(SHPG_STS.SHIPPED, SHPG_STS.ARRIVED, SHPG_STS.INVOICED).contains(shpgStsCd)) {

                        return false;
                    }

                } else {

                    if (!SHPG_STS.VALIDATED.equals(shpgStsCd)) {

                        return false;
                    }

                    if (Arrays.asList(cpoHldFlg, cpoDtlHldFlg, shipPlnHldFlg).contains(ZYPConstant.FLG_ON_Y)) {

                        return false;

                    }

                    if (ZYPConstant.FLG_ON_Y.equals(autoLicAllocFlg)) {
                        final Map<String, Object> ssmLakParam = new HashMap<String, Object>();
                        ssmLakParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
                        ssmLakParam.put(BIND_CPO_ORD_NUM, resultMap.get(CPO_ORD_NUM));
                        ssmLakParam.put(BIND_CPO_ORD_TP_LIST, cpoOrdTpList);
                        ssmLakParam.put(BIND_CPO_DTL_LINE_NUM, resultMap.get(CPO_DTL_LINE_NUM));
                        ssmLakParam.put(BIND_CPO_DTL_LINE_SUB_NUM, resultMap.get(CPO_DTL_LINE_SUB_NUM));
                        ssmLakParam.put(BIND_MDSE_CD, resultMap.get(MDSE_CD));
                        ssmLakParam.put(BIND_LAK_STS_CD, LAK_STS.USED);

                        if ((Integer) ssmBatchClient.queryObject("getLakAssignedNum", ssmLakParam) == 0) {

                            return false;

                        }
                    }
                }
            }
        }

        return true;

    }

    /**
     * @param soNum
     * @param trxId
     * @param errMsg
     */
    private void addMailMessageToAdminForImport(String msgId, String soNum, String errMsg) {

        Map<String, String> mailParam = new HashMap<String, String>();
        StringBuilder mailMessageForAdmin = new StringBuilder();

        mailMessageForAdmin.append("SO#:");
        mailMessageForAdmin.append(soNum);
        mailMessageForAdmin.append(" Reason:");
        mailMessageForAdmin.append(errMsg);
        mailMessageForAdmin.append("\r\n");
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, new String(mailMessageForAdmin));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(new String(mailMessageForAdmin));
    }

    /**
     * sendEMailToAdmin
     */
    private void sendEMailToAdmin() {

        NLXMailSend mail = new NLXMailSend(glblCmpyCd);
        mail.send(BUSINESS_ID, errMsgList);
        commit();
        // rollback();
    }

}
