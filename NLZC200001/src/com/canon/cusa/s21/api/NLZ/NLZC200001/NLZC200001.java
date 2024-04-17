/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC200001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.IMPT_PACK_SLP_STSTMsg;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_HDRTMsg;
import business.db.PO_RCV_MSGTMsg;
import business.db.PO_RCV_MSGTMsgArray;
import business.db.RWS_DTLTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_MSGTMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SYS_SRCTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC200001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.constant.NLZC200001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NLX.NLXC025001.NLXC025001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_INV_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_INV_SHPG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_INV_SLP_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SSM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;


/**
 * <pre>
 * RWS API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/15/2009   Fujitsu         A.Akabane       Create            N/A
 * 11/04/2009   Fujitsu         T.Motoyama      Update            N/A
 * 11/11/2009   Fujitsu         T.Motoyama      Update            1736
 * 11/25/2009   Fujitsu         A.Akabane       Update            2093
 * 12/16/2009   Fujitsu         A.Akabane       Update            N/A
 * 12/28/2009   Fujitsu         A.Akabane       Update            N/A
 * 10/05/2010   Fujitsu         S.Yoshida       Update            527
 * 10/13/2010   Fujitsu         T.Ishii         Update            RWS Ref#(Return case)
 * 12/04/2010   CSAI            D.Fukaya        Update            N/A
 * 12/04/2010   CSAI            M.Takahashi     Update            1452
 * 03/25/2013   Hitachi         S.Tanabe        Update            QC821
 * 05/13/2013   Hitachi         Y.Igarashi      Update            QC1092
 * 07/29/2013   Hitachi         A.Kohinata      Update            QC913
 * 09/25/2015   Hitachi         Y.Tsuchimoto    Update            CSA(9.115, 9.11.12)
 * 04/04/2016   CSAI            K.Lee           Update            QC#4776
 * 06/30/2016   CSAI            K.Lee           Update            Configuration Change
 * 09/04/2016   CITS            T.Wada          Update            QC#5142
 * 03/13/2017   CITS            T.Tokutomi      Update            Merge DS. And Delete Comment out.
 * 09/11/2017   CITS            S.Endo          Update            Sol#069(QC#18270)
 * 02/05/2018   CITS            K.Ogino         Update            QC#23962
 * 02/22/2018   CITS            T.Gotoda        Update            QC#24066
 * 03/07/2018   CITS            T.hakodate      Update            QC#20399
 * 07/17/2018   CITS            T.hakodate      Update            QC#26863
 * 05/09/2019   Fujitsu         T.Ogura         Update            QC#50027
 * 06/13/2022   Hitachi         K.Kim           Update            QC#60022
 * 10/24/2022   Hitachi         T.NEMA          Update            QC#60022
 * 04/07/2023   Hitachi         M.Nakajima      Update            QC#61001
 *</pre>
 */

public class NLZC200001 extends S21ApiCommonBase {

    /** SSM Client */
    private S21SsmLowLevelCodingClient ssmClient = null;

    /** SQL ID: getInvInfo */
    private static final String GET_INV_INFO = "getInvInfo";

    /** SQL ID: getCntnrInfo */
    private static final String GET_CNTNR_INFO = "getCntnrInfo";

    /** SQL ID: getScpInvInfo */
    private static final String GET_SCP_INV_INFO = "getScpInvInfo";

    /** SQL ID: getScpCntnrInfo */
    private static final String GET_SCP_CNTNR_INFO = "getScpCntnrInfo";

    /** SQL ID: getScpAsnInfo */
    private static final String GET_SCP_ASN_INFO = "getScpAsnInfo";

    /** SQL ID: getPoRcvInfo */
    private static final String GET_PO_RCV_INFO = "getPoRcvInfo";

    /** SQL ID: getSoInfo */
    private static final String GET_SO_INFO = "getSoInfo";

    /** SQL ID: getSoInfo */
    private static final String GET_RTL_WH_BUYBACK = "getRtlWhForBuyBack";

    /** SQL ID: getSoInfo */
    private static final String GET_RTL_WH_BUYBACK_DTL = "getRtlWhForBuyBackDtl";

    /** SQL ID: getSoInfo */
    private static final String GET_DPLY_VND_NM = "getDplyVndNmOfPoVndView";

    /** SQL Bind Name for SSM : GLBL_CMPY_CD */
    private static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Bind Name for SSM : IMPT_INV_PK */
    private static final String BIND_IMPT_INV_PK = "imptInvPk";

    /** SQL Bind Name for SSM : IMPT_INV_CLS_CD */
    private static final String BIND_IMPT_INV_CLS_CD = "imptInvClsCd";

    /** SQL Bind Name for SSM : IMPT_INV_PRT_FLG */
    private static final String BIND_IMPT_INV_PRT_FLG = "imptInvPrtFlg";

    /** SQL Bind Name for SSM : IMPT_INV_ACTV_VRSN_FLG */
    private static final String BIND_IMPT_INV_ACTV_VRSN_FLG = "imptInvActvVrsnFlg";

    /** SQL Bind Name for SSM : IMPT_INV_SLP_CLS_CD */
    private static final String BIND_IMPT_INV_SLP_CLS_CD = "imptInvSlpClsCd";

    /** SQL Bind Name for SSM : ASN_RWS_STS_CD */
    private static final String BIND_ASN_RWS_STS_CD = "asnRwsStsCd";

    /** SQL Bind Name for SSM : IMPT_INV_PACK_SLP_STS_CD */
    private static final String BIND_IMPT_PACK_SLP_STS_CD = "imptPackSlpStsCd";

    /** SQL Bind Name for SSM : IN_PACK_TOT_QTY */
    private static final String BIND_IN_PACK_TOT_QTY = "inPackTotQty";

    /** SQL Bind Name for SSM : IMPT_CNTNR_LOT_NUM */
    private static final String BIND_IMPT_CNTNR_LOT_NUM = "imptCntnrLotNum";

    /** SQL Bind Name for SSM : IMPT_CNTNR_NUM */
    private static final String BIND_IMPT_CNTNR_NUM = "imptCntnrNum";

    /** SQL Bind Name for SSM : REQ_STK_IN_QTY */
    private static final String BIND_REQ_STK_IN_QTY = "reqStkInQty";

    /** SQL Bind Name for SSM : TRUCK_CNTNR_PK */
    private static final String BIND_TRUCK_CNTNR_PK = "truckCntnrPk";

    /** SQL Bind Name for SSM : ASN_STK_IN_QTY */
    private static final String BIND_ASN_STK_IN_QTY = "asnStkInQty";

    /** SQL Bind Name for SSM : SSM_STS_CD */
    private static final String BIND_SSM_STS_CD = "ssmStsCd";

    /** SQL Bind Name for SSM : PO_RCV_NUM */
    private static final String BIND_PO_RCV_NUM = "poRcvNum";

    /** SQL Bind Name for SSM : PO_RCV_NUM */
    private static final String BIND_PO_RCV_LINE_NUM = "poRcvLineNum";

    /** SQL Bind Name for SSM : PO_RCV_QTY */
    private static final String BIND_PO_RCV_QTY = "poRcvQty";

    /** SQL Bind Name for SSM : RWS_STS_CD */
    private static final String BIND_RWS_STS_CD = "rwsStsCd";

    /** SQL Bind Name for SSM : INBD_OTBD_CD */
    private static final String BIND_INBD_OTBD_CD = "inbdOtbdCd";

    /** SQL Bind Name for SSM : SO_NUM */
    private static final String BIND_SO_NUM = "soNum";

    /** SQL Bind Name for SSM : SHPG_QTY */
    private static final String BIND_SHPG_QTY = "shpgQty";

    /** SQL Bind Name for SSM : RMV_CONFIG_FLG */
    private static final String BIND_RMV_CONFIG_FLG = "rmvConfigFlg";

    /** SQL Bind Name for SSM : VND_CD */
    private static final String BIND_VND_CD = "vndCd";

    /** Message ID : NLZM1001E */
    private static final String NLZM1001E = "NLZM1001E";

    /** Message ID : NLZM1004E */
    private static final String NLZM1004E = "NLZM1004E";

    /** Message ID : NLZM1014E */
    private static final String NLZM1014E = "NLZM1014E";

    /** Message ID : NLZM1020E */
    private static final String NLZM1020E = "NLZM1020E";

    /** Message ID : NLZM1024E */
    private static final String NLZM1024E = "NLZM1024E";

    /** Message ID : NLZM1035E */
    private static final String NLZM1035E = "NLZM1035E";

    /** Message ID : NLZM2016E */
    private static final String NLZM2016E = "NLZM2016E";

    /** Message ID : NLZM2017E */
    private static final String NLZM2017E = "NLZM2017E";

    // 2013/07/29 QC913 A.Kohinata Add Start
    /** Message ID : NLZM2185E */
    private static final String NLZM2185E = "NLZM2185E";
    // 2013/07/29 QC913 A.Kohinata Add End

    /** Message ID : NLAM1001E */
    private static final String NLAM1001E = "NLAM1001E";

    /** Message ID : NLAM1006E */
    private static final String NLAM1006E = "NLAM1006E";

    /** Message ID : NLAM1013E */
    private static final String NLAM1013E = "NLAM1013E";

    /** Message ID : NLAM1131E */
    private static final String NLAM1131E = "NLAM1131E";

    /** Message ID : NLAM1132E */
    private static final String NLAM1132E = "NLAM1132E";

    /** Message ID : NLAM1133E */
    private static final String NLAM1133E = "NLAM1133E";

    /** Message ID : NLAM1134E */
    private static final String NLAM1134E = "NLAM1134E";

    /**  : ONBATCH_TYPE */
    private static final String ONBATCH_TYPE = "ONBATCH_TYPE";

    /**  : GLBL_CMPY_CD */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**  : SYS_SRC_CD */
    private static final String SYS_SRC_CD = "SYS_SRC_CD";

    /**  : ADDL_LOC_NM */
    private static final String ADDL_LOC_NM = "ADDL_LOC_NM";

    /**  : ASN_STK_IN_QTY */
    private static final String ASN_STK_IN_QTY = "ASN_STK_IN_QTY";

    /**  : ASN_NUM = */
    private static final String ASN_NUM = "ASN_NUM";

    /**  : CTRY_CD = */
    private static final String CTRY_CD = "CTRY_CD";

    /**  : CTY_ADDR  */
    private static final String CTY_ADDR = "CTY_ADDR";

    /**  : DEF_INVTY_LOC_CD */
    private static final String DEF_INVTY_LOC_CD = "DEF_INVTY_LOC_CD";

    /**  : DNLD_TM_TS */
    private static final String DNLD_TM_TS = "DNLD_TM_TS";

    /**  : FIRST_LINE_ADDR */
    private static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**  : FROM_STK_STS_CD */
    private static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /**  : FRTH_LINE_ADDR */
    private static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**  : INBD_SRC_TP_CD */
    private static final String INBD_SRC_TP_CD = "INBD_SRC_TP_CD";

    /**  : IMPT_CNTNR_LOT_NUM */
    private static final String IMPT_CNTNR_LOT_NUM = "IMPT_CNTNR_LOT_NUM";

    /**  : IMPT_CNTNR_SEAL_NUM_01 */
    private static final String IMPT_CNTNR_SEAL_NUM_01 = "IMPT_CNTNR_SEAL_NUM_01";

    /**  : IMPT_CNTNR_NUM */
    private static final String IMPT_CNTNR_NUM = "IMPT_CNTNR_NUM";

    /**  : IMPT_INV_BOL_NUM */
    private static final String IMPT_INV_BOL_NUM = "IMPT_INV_BOL_NUM";

    /**  : IMPT_INV_CARR_CD */
    private static final String IMPT_INV_CARR_CD = "IMPT_INV_CARR_CD";

    /**  : IMPT_DO_CLS_CD */
    private static final String IMPT_DO_CLS_CD = "IMPT_DO_CLS_CD";

    /**  : IMPT_INV_CLS_CD */
    private static final String IMPT_INV_CLS_CD = "IMPT_INV_CLS_CD";

    /**  : IMPT_INV_DO_NUM */
    private static final String IMPT_INV_DO_NUM = "IMPT_INV_DO_NUM";

    /**  : IMPT_INV_DO_PK */
    private static final String IMPT_INV_DO_PK = "IMPT_INV_DO_PK";

    /**  : IMPT_INV_FLGT_NUM */
    private static final String IMPT_INV_FLGT_NUM = "IMPT_INV_FLGT_NUM";

    /**  : IMPT_INV_NUM */
    private static final String IMPT_INV_NUM = "IMPT_INV_NUM";

    /**  : IMPT_INV_PK */
    private static final String IMPT_INV_PK = "IMPT_INV_PK";

    /**  : IMPT_INV_SHPG_METH_CD */
    private static final String IMPT_INV_SHPG_METH_CD = "IMPT_INV_SHPG_METH_CD";

    /**  : IMPT_INV_SLP_CLS_CD */
    private static final String IMPT_INV_SLP_CLS_CD = "IMPT_INV_SLP_CLS_CD";

    /**  : IMPT_INV_VER_NUM */
    private static final String IMPT_INV_VER_NUM = "IMPT_INV_VER_NUM";

    /**  : IMPT_INV_VESL_NM */
    private static final String IMPT_INV_VESL_NM = "IMPT_INV_VESL_NM";

    /**  : IMPT_PACK_SLP_NUM */
    private static final String IMPT_PACK_SLP_NUM = "IMPT_PACK_SLP_NUM";

    /**  : IMPT_PACK_SLP_PK */
    private static final String IMPT_PACK_SLP_PK = "IMPT_PACK_SLP_PK";

    /**  : IMPT_PACK_CSE_CLS_CD */
    private static final String IMPT_PACK_CSE_CLS_CD = "IMPT_PACK_CSE_CLS_CD";

    /**  : IMPT_PACK_SLP_LINE_NUM */
    private static final String IMPT_PACK_SLP_LINE_NUM = "IMPT_PACK_SLP_LINE_NUM";

    /**  : IMPT_PACK_SLP_DTL_PK */
    private static final String IMPT_PACK_SLP_DTL_PK = "IMPT_PACK_SLP_DTL_PK";

    /**  : IMPT_PACK_SLP_STS_PK */
    private static final String IMPT_PACK_SLP_STS_PK = "IMPT_PACK_SLP_STS_PK";

    /**  : IMPT_PACK_SLP_DTL_LINE_NUM */
    private static final String IMPT_PACK_SLP_DTL_LINE_NUM = "IMPT_PACK_SLP_DTL_LINE_NUM";

    /**  : IMPT_VND_CD */
    private static final String IMPT_VND_CD = "IMPT_VND_CD";

    /**  : IMPT_VND_SHPG_METH_CD */
    private static final String IMPT_VND_SHPG_METH_CD = "IMPT_VND_SHPG_METH_CD";

    /**  : IN_PACK_NET_WT */
    private static final String IN_PACK_NET_WT = "IN_PACK_NET_WT";

    /**  : IN_PACK_WH_IN_ETA_DT */
    private static final String IN_PACK_WH_IN_ETA_DT = "IN_PACK_WH_IN_ETA_DT";

    /**  : IN_PACK_CSE_QTY */
    private static final String IN_PACK_CSE_QTY = "IN_PACK_CSE_QTY";

    /**  : IN_PACK_UNIT_QTY */
    private static final String IN_PACK_UNIT_QTY = "IN_PACK_UNIT_QTY";

    /**  : IN_PACK_TOT_QTY */
    private static final String IN_PACK_TOT_QTY = "IN_PACK_TOT_QTY";

    /**  : INBD_OTBD_CD */
    private static final String INBD_OTBD_CD = "INBD_OTBD_CD";

    /**  : INLND_CARR_CD */
    private static final String INLND_CARR_CD = "INLND_CARR_CD";

    /**  : INLND_SHPG_METH_CD */
    private static final String INLND_SHPG_METH_CD = "INLND_SHPG_METH_CD";

    /**  : LGSC_SCP_ORD_NUM */
    private static final String LGSC_SCP_ORD_NUM = "LGSC_SCP_ORD_NUM";

    /**  : LOC_NM  */
    private static final String LOC_NM = "LOC_NM";

    /**  : MDSE_CD */
    private static final String MDSE_CD = "MDSE_CD";

    /**  : TRUCK_CNTNR_PK */
    private static final String TRUCK_CNTNR_PK = "TRUCK_CNTNR_PK";

    /**  : OUT_PACK_CSE_QTY */
    private static final String OUT_PACK_CSE_QTY = "OUT_PACK_CSE_QTY";

    /**  : OUT_PACK_FROM_CSE_NUM */
    private static final String OUT_PACK_FROM_CSE_NUM = "OUT_PACK_FROM_CSE_NUM";

    /**  : OUT_PACK_TO_CSE_NUM */
    private static final String OUT_PACK_TO_CSE_NUM = "OUT_PACK_TO_CSE_NUM";

    /**  : PO_RCV_NUM */
    private static final String PO_RCV_NUM = "PO_RCV_NUM";

    /**  : PO_RCV_FROM_LOC_CD */
    private static final String PO_RCV_FROM_LOC_CD = "PO_RCV_FROM_LOC_CD";

    /**  : PO_RCV_FROM_LOC_TP_CD */
    private static final String PO_RCV_FROM_LOC_TP_CD = "PO_RCV_FROM_LOC_TP_CD";

    /**  : PO_RCV_ETA_DT */
    private static final String PO_RCV_ETA_DT = "PO_RCV_ETA_DT";

    /**  : PO_RCV_LINE_NUM */
    private static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /**  : PO_RCV_QTY */
    private static final String PO_RCV_QTY = "PO_RCV_QTY";

    /**  : POST_CD */
    private static final String POST_CD = "POST_CD";

    /**  : PSD_DT  */
    private static final String PSD_DT = "PSD_DT";

    /**  : REQ_STK_IN_QTY */
    private static final String REQ_STK_IN_QTY = "REQ_STK_IN_QTY";

    /**  : REQ_STK_IN_WH_CD */
    private static final String REQ_STK_IN_WH_CD = "REQ_STK_IN_WH_CD";

    /**  : REQ_WH_ETA_DT */
    private static final String REQ_WH_ETA_DT = "REQ_WH_ETA_DT";

    /**  : RWS_DTL_LINE_NUM */
    private static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /**  : RWS_MSG_SQ_NUM */
    private static final String RWS_MSG_SQ_NUM = "RWS_MSG_SQ_NUM";

    /**  : RWS_NUM */
    private static final String RWS_NUM = "RWS_NUM";

    /**  : RWS_REF_NUM */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /**  : SCD_LINE_ADDR */
    private static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**  : SCE_ORD_TP_CD */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /**  : SO_NUM */
    private static final String SO_NUM = "SO_NUM";

    /**  : SO_SLP_NUM */
    private static final String SO_SLP_NUM = "SO_SLP_NUM";

    /**  : SCP_CNTNR_NUM */
    private static final String SCP_CNTNR_NUM = "SCP_CNTNR_NUM";

    /**  : SCP_SEAL_NUM */
    private static final String SCP_SEAL_NUM = "SCP_SEAL_NUM";

    /**  : SHIP_TO_CUST_CD */
    private static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**  : SHPG_QTY  */
    private static final String SHPG_QTY = "SHPG_QTY";

    /**  : ST_CD */
    private static final String ST_CD = "ST_CD";

    /**  : STK_IN_WH_CD */
    private static final String STK_IN_WH_CD = "STK_IN_WH_CD";

    /**  : STK_STS_CD */
    private static final String STK_STS_CD = "STK_STS_CD";

    /**  : TEL_NUM */
    private static final String TEL_NUM = "TEL_NUM";

    /**  : THIRD_LINE_ADDR */
    private static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**  : TO_STK_STS_CD */
    private static final String TO_STK_STS_CD = "TO_STK_STS_CD";

    /**  : TRX_CD */
    private static final String TRX_CD = "TRX_CD";

    /**  : TRX_RSN_CD */
    private static final String TRX_RSN_CD = "TRX_RSN_CD";

    /**  : VND_CD */
    private static final String VND_CD = "VND_CD";

    /**  : WH_CD */
    private static final String WH_CD = "WH_CD";

    /**  : WH_ETA_DT */
    private static final String WH_ETA_DT = "WH_ETA_DT";

    // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Add Start
    /** : RTL_WH_CD */
    private static final String RTL_WH_CD = "RTL_WH_CD";

    /** : RTL_SWH_CD */
    private static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** : SHIP_FROM_RTL_WH_CD */
    private static final String SHIP_FROM_RTL_WH_CD = "SHIP_FROM_RTL_WH_CD";

    /** : SHIP_FROM_RTL_SWH_CD */
    private static final String SHIP_FROM_RTL_SWH_CD = "SHIP_FROM_RTL_SWH_CD";

    /** : SHIP_FROM_INVTY_LOC_CD */
    private static final String SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";

    /** : PRCH_REQ_NUM */
    private static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** : PRCH_REQ_LINE_NUM */
    private static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** : PRCH_REQ_LINE_SUB_NUM */
    private static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** : SER_NUM_TAKE_FLG */
    private static final String SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /** : RCV_SER_TAKE_FLG */
    private static final String RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /** : SHPG_SER_TAKE_FLG */
    private static final String SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";

    /** : FROM_LOC_NM_01_RW */
    private static final String FROM_LOC_NM_01_RW = "FROM_LOC_NM_01_RW";

    /** : FROM_LOC_NM_01_VND */
    private static final String FROM_LOC_NM_01_VND = "FROM_LOC_NM_01_VND";

    /** : FROM_LOC_NM_02_RW */
    private static final String FROM_LOC_NM_02_RW = "FROM_LOC_NM_02_RW";

    /** : FROM_LOC_NM_02_VND */
    private static final String FROM_LOC_NM_02_VND = "FROM_LOC_NM_02_VND";

    /** : FROM_LOC_CTY_ADDR_RW */
    private static final String FROM_LOC_CTY_ADDR_RW = "FROM_LOC_CTY_ADDR_RW";

    /** : FROM_LOC_CTY_ADDR_VND */
    private static final String FROM_LOC_CTY_ADDR_VND = "FROM_LOC_CTY_ADDR_VND";

    /** : FROM_LOC_ST_CD_RW */
    private static final String FROM_LOC_ST_CD_RW = "FROM_LOC_ST_CD_RW";

    /** : FROM_LOC_ST_CD_VND */
    private static final String FROM_LOC_ST_CD_VND = "FROM_LOC_ST_CD_VND";

    /** : FROM_LOC_CTRY_CD_RW */
    private static final String FROM_LOC_CTRY_CD_RW = "FROM_LOC_CTRY_CD_RW";

    /** : FROM_LOC_CTRY_CD_VND */
    private static final String FROM_LOC_CTRY_CD_VND = "FROM_LOC_CTRY_CD_VND";

    /** : FROM_LOC_TEL_NUM_RW */
    private static final String FROM_LOC_TEL_NUM_RW = "FROM_LOC_TEL_NUM_RW";

    /** : FROM_LOC_TEL_NUM_VND */
    private static final String FROM_LOC_TEL_NUM_VND = "FROM_LOC_TEL_NUM_VND";

    /** : FROM_LOC_ADDR_01_RW */
    private static final String FROM_LOC_ADDR_01_RW = "FROM_LOC_ADDR_01_RW";

    /** : FROM_LOC_ADDR_01_VND */
    private static final String FROM_LOC_ADDR_01_VND = "FROM_LOC_ADDR_01_VND";

    /** : FROM_LOC_ADDR_02_RW */
    private static final String FROM_LOC_ADDR_02_RW = "FROM_LOC_ADDR_02_RW";

    /** : FROM_LOC_ADDR_02_VND */
    private static final String FROM_LOC_ADDR_02_VND = "FROM_LOC_ADDR_02_VND";

    /** : FROM_LOC_ADDR_03_RW */
    private static final String FROM_LOC_ADDR_03_RW = "FROM_LOC_ADDR_03_RW";

    /** : FROM_LOC_ADDR_03_VND */
    private static final String FROM_LOC_ADDR_03_VND = "FROM_LOC_ADDR_03_VND";

    /** : FROM_LOC_ADDR_04_RW */
    private static final String FROM_LOC_ADDR_04_RW = "FROM_LOC_ADDR_04_RW";

    /** : FROM_LOC_ADDR_04_VND */
    private static final String FROM_LOC_ADDR_04_VND = "FROM_LOC_ADDR_04_VND";

    /** : FROM_LOC_POST_CD_RW */
    private static final String FROM_LOC_POST_CD_RW = "FROM_LOC_POST_CD_RW";

    /** : FROM_LOC_POST_CD_VND */
    private static final String FROM_LOC_POST_CD_VND = "FROM_LOC_POST_CD_VND";

    /** : SVC_CONFIG_MSTR_PK */
    private static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** : SHIP_TO_RTL_WH_CD */
    private static final String SHIP_TO_RTL_WH_CD = "SHIP_TO_RTL_WH_CD";

    /** : SHIP_TO_RTL_SWH_CD */
    private static final String SHIP_TO_RTL_SWH_CD = "SHIP_TO_RTL_SWH_CD";

    /** : TO_INVTY_LOC_CD */
    private static final String TO_INVTY_LOC_CD = "TO_INVTY_LOC_CD";

    /** : TRX_SRC_TP_CD */
    private static final String TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** : LOC_TP_CD */
    private static final String LOC_TP_CD = "LOC_TP_CD";

    /** : RTL_WH_NM */
    private static final String RTL_WH_NM = "RTL_WH_NM";

    /** : SHIP_FROM_SO_NUM */
    private static final String SHIP_FROM_SO_NUM = "SHIP_FROM_SO_NUM";

    /**  : INVTY_LOC_CD */
    private static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /**  : DOM_INV_NUM */
    private static final String DOM_INV_NUM = "DOM_INV_NUM";

    // 2013/03/25 QC821 S.Tanabe Add Start
    /**  : PO_RCV_TRX_HDR_NUM */
    private static final String PO_RCV_TRX_HDR_NUM = "PO_RCV_TRX_HDR_NUM";

    /**  : PO_RCV_TRX_LINE_NUM */
    private static final String PO_RCV_TRX_LINE_NUM = "PO_RCV_TRX_LINE_NUM";

    // 2013/03/25 QC821 S.Tanabe Add End
    // 2013/05/13 QC1092 Y.Igarashi Add Start
    /**  : PRO_NUM */
    private static final String PRO_NUM = "PRO_NUM";
    // 2013/05/13 QC1092 Y.Igarashi Add End
    /**  : *GLBL_CMPY */
    private static final String GLBL_CMPY = "GLBL_CMPY";

    /**  : TRX_HDR_NUM */
    private static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /**  : SRC_RTL_WH_CD */
    private static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /**  : SRC_RTL_SWH_CD */
    private static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    // START 2022/06/13 [QC#60022,ADD]
    /**  : WH_OWNR_CD */
    private static final String WH_OWNR_CD = "WH_OWNR_CD";
    // END   2022/06/13 [QC#60022,ADD]

    /**  : RWS_DTL_LINE_NUM_LENGTH */
    private static final int RWS_DTL_LINE_NUM_LENGTH = 3;

    /**  : RWS_DTL_LINE_NUM_PUT_NUM */
    private static final String RWS_DTL_LINE_NUM_PUT_NUM = "0";

    /**  : RWS_PUT_AWAY_QTY_ZERO */
    private static final String RWS_PUT_AWAY_QTY_ZERO = "0";

    /**  : WMS_DROP_STS_CD_NOT_DRP */
    private static final String WMS_DROP_STS_CD_NOT_DRP = "0";

    /**  : WMS_DROP_STS_CD_DRP */
    private static final String WMS_DROP_STS_CD_DRP = "1";

    /** SQL ID : PO_RCV_MSQLID_PO_SG */
    private static final String SQLID_PO_RCV_MSG = "001";

    /**  : FETCH_SIZE_MAX */
    private static final int FETCH_SIZE_MAX = 999;

    /**  : RWS_QTY_ZERO */
    private static final int RWS_QTY_ZERO = 0;

    /**  : ONBATCH_NULL */
    private static final String ONBATCH_NULL = "NULL";

    /** System Date */
    private String sysDt = null;

    /** Sales Date */
    private String salesDt = null;

    /** Currency Code */
    private String stdCcyCd = "";

    /** SSM client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** : SER_APVL_REQ_FLG */
    private static final String SER_APVL_REQ_FLG = "SER_APVL_REQ_FLG";

    /**
     * <pre>
     * </pre>
     */
    public NLZC200001() {
        super();
        this.ssmClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * execute
     * </pre>
     * @param param NLZC200001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC200001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doRwsApi(msgMap, onBatchType);

        msgMap.flush();
    }

    /**
     * <pre>
     * execute
     * </pre>
     * @param params List<NLZC200001PMsg>
     * @param onBatchType ONBATCH_TYPE
     * @see com.canon.cusa.s21.api.NLZ.NLZC200001.execute
     */
    public void execute(final List<NLZC200001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NLZC200001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    protected void doRwsApi(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        boolean returnValue = checkParam(msgMap, onBatchType);

        if (!returnValue) {
            return;
        }

        String inbdSrcTpCd = param.inbdSrcTpCd.getValue();
        sysDt = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        salesDt = ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue());

        if (INBD_SRC_TP.INVOICE.equals(inbdSrcTpCd)) {

            doInvoice(msgMap, onBatchType);

        } else if (INBD_SRC_TP.CONTAINER.equals(inbdSrcTpCd)) {

            doContainer(msgMap, onBatchType);

        } else if (INBD_SRC_TP.SCP_ORDER.equals(inbdSrcTpCd)) {

            doScpOrder(msgMap, onBatchType);

        } else if (INBD_SRC_TP.SCP_ASN.equals(inbdSrcTpCd)) {

            doScpAsn(msgMap, onBatchType);

        } else if (INBD_SRC_TP.PO_RECEIVING.equals(inbdSrcTpCd)) {

            doPoReceiving(msgMap, onBatchType);

        } else if (INBD_SRC_TP.RETURN.equals(inbdSrcTpCd)) {

            ReturnRWS rRws = new ReturnRWS();
            rRws.execute(param, onBatchType);

        } else if (INBD_SRC_TP.SO.equals(inbdSrcTpCd)) {

            doSo(msgMap, onBatchType);
        }
    }

    /**
     * <pre>
     * <ul>
     * </ul>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    protected boolean checkParam(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        boolean returnValue = true;

        if (onBatchType == null) {
            throw new S21AbendException(NLAM1131E, new String[] {NLXCMsgHelper.toListedString(ONBATCH_TYPE), NLXCMsgHelper.toListedString(ONBATCH_NULL) });
        }

        String mndtryFlg = ZYPConstant.FLG_OFF_N;
        String cnfgrFlg = ZYPConstant.FLG_OFF_N;
        String mstFlg = ZYPConstant.FLG_OFF_N;

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {GLBL_CMPY_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;
        } else {
            GLBL_CMPYTMsg glblCmpyT = new GLBL_CMPYTMsg();
            glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(glblCmpyT.getTableName(), param.glblCmpyCd.getValue(), param.glblCmpyCd.getValue());

            if (glblCmpyT == null) {
                msgMap.addXxMsgId(NLZM1035E);
                NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_GLBL_CMPY, NLXCMsgHelper.toListedString(GLBL_CMPY_CD), NLXCMsgHelper.toListedString(param.glblCmpyCd) }, onBatchType, this);
                mstFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.sysSrcCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {SYS_SRC_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;
        } else if (ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            SYS_SRCTMsg sysSrcT = new SYS_SRCTMsg();
            sysSrcT = (SYS_SRCTMsg) ZYPCodeDataUtil.findByCode(sysSrcT.getTableName(), param.glblCmpyCd.getValue(), param.sysSrcCd.getValue());
            if (sysSrcT == null) {
                msgMap.addXxMsgId(NLZM1020E);
                NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_SYS_SRC, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SYS_SRC_CD), NLXCMsgHelper.toListedString(param.glblCmpyCd, param.sysSrcCd) }, onBatchType, this);
                mstFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        String inbdSrcTpCd = param.inbdSrcTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(param.inbdSrcTpCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {INBD_SRC_TP_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;
        } else if (INBD_SRC_TP.INVOICE.equals(inbdSrcTpCd)) {
            if (param.imptInvPk.getValue() == null) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {IMPT_INV_PK }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
        } else if (INBD_SRC_TP.CONTAINER.equals(inbdSrcTpCd)) {
            if (!ZYPCommonFunc.hasValue(param.imptCntnrLotNum)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {IMPT_CNTNR_LOT_NUM }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
            if (!ZYPCommonFunc.hasValue(param.imptCntnrNum)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {IMPT_CNTNR_NUM }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
        } else if (INBD_SRC_TP.SCP_ORDER.equals(inbdSrcTpCd)) {
            String imptInvPkFlg = null;
            String imptCntnrLotNumFlg = null;
            String imptCntnrNumFlg = null;

            if (!ZYPCommonFunc.hasValue(param.imptInvPk)) {
                imptInvPkFlg = ZYPConstant.FLG_OFF_N;
            } else {
                imptInvPkFlg = ZYPConstant.FLG_ON_Y;
            }
            if (!ZYPCommonFunc.hasValue(param.imptCntnrLotNum)) {
                imptCntnrLotNumFlg = ZYPConstant.FLG_OFF_N;
            } else {
                imptCntnrLotNumFlg = ZYPConstant.FLG_ON_Y;
            }
            if (!ZYPCommonFunc.hasValue(param.imptCntnrNum)) {
                imptCntnrNumFlg = ZYPConstant.FLG_OFF_N;
            } else {
                imptCntnrNumFlg = ZYPConstant.FLG_ON_Y;
            }

            if (ZYPConstant.FLG_ON_Y.equals(imptInvPkFlg)) {
                if (ZYPConstant.FLG_ON_Y.equals(imptCntnrLotNumFlg) && ZYPConstant.FLG_ON_Y.equals(imptCntnrNumFlg)) {
                    NLXC025001.outputLog(1, NLAM1131E, new String[] {NLXCMsgHelper.toListedString(IMPT_CNTNR_LOT_NUM, IMPT_CNTNR_NUM), NLXCMsgHelper.toListedString(param.imptCntnrLotNum, param.imptCntnrNum) }, onBatchType, this);
                    cnfgrFlg = ZYPConstant.FLG_ON_Y;
                } else if (ZYPConstant.FLG_ON_Y.equals(imptCntnrLotNumFlg) && ZYPConstant.FLG_OFF_N.equals(imptCntnrNumFlg)) {
                    NLXC025001.outputLog(1, NLAM1131E, new String[] {NLXCMsgHelper.toListedString(IMPT_CNTNR_LOT_NUM), NLXCMsgHelper.toListedString(param.imptCntnrLotNum) }, onBatchType, this);
                    cnfgrFlg = ZYPConstant.FLG_ON_Y;
                } else if (ZYPConstant.FLG_OFF_N.equals(imptCntnrLotNumFlg) && ZYPConstant.FLG_ON_Y.equals(imptCntnrNumFlg)) {
                    NLXC025001.outputLog(1, NLAM1131E, new String[] {NLXCMsgHelper.toListedString(IMPT_CNTNR_NUM), NLXCMsgHelper.toListedString(param.imptCntnrNum) }, onBatchType, this);
                    cnfgrFlg = ZYPConstant.FLG_ON_Y;
                }
            } else if (ZYPConstant.FLG_OFF_N.equals(imptInvPkFlg)) {
                if (ZYPConstant.FLG_ON_Y.equals(imptCntnrLotNumFlg) && ZYPConstant.FLG_OFF_N.equals(imptCntnrNumFlg)) {
                    NLXC025001.outputLog(1, NLAM1006E, new String[] {IMPT_CNTNR_NUM }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;
                } else if (ZYPConstant.FLG_OFF_N.equals(imptCntnrLotNumFlg) && ZYPConstant.FLG_ON_Y.equals(imptCntnrNumFlg)) {
                    NLXC025001.outputLog(1, NLAM1006E, new String[] {IMPT_CNTNR_LOT_NUM }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;
                } else if (ZYPConstant.FLG_OFF_N.equals(imptCntnrLotNumFlg) && ZYPConstant.FLG_OFF_N.equals(imptCntnrNumFlg)) {
                    NLXC025001.outputLog(1, NLAM1006E, new String[] {IMPT_CNTNR_LOT_NUM, IMPT_CNTNR_NUM }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;
                }
            }
        } else if (INBD_SRC_TP.SCP_ASN.equals(inbdSrcTpCd)) {
            if (!ZYPCommonFunc.hasValue(param.truckCntnrPk)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {TRUCK_CNTNR_PK }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
        } else if (INBD_SRC_TP.PO_RECEIVING.equals(inbdSrcTpCd)) {
            if (!ZYPCommonFunc.hasValue(param.poRcvNum)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_NUM }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
            stdCcyCd = getStdCcyCd(msgMap);

            
        } else if (INBD_SRC_TP.RETURN.equals(inbdSrcTpCd)) {
            /**
             * Merge DS : Return param check -> ReturnRWS class. 
             * Skip this Method.
             **/
            return true;
        } else if (INBD_SRC_TP.SO.equals(inbdSrcTpCd)) {
            if (!ZYPCommonFunc.hasValue(param.soNum)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {SO_NUM }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
        } else {
            NLXC025001.outputLog(1, NLAM1131E, new String[] {INBD_SRC_TP_CD, NLXCMsgHelper.toListedString(param.inbdSrcTpCd) }, onBatchType, this);
            cnfgrFlg = ZYPConstant.FLG_ON_Y;
        }
        if (ZYPConstant.FLG_ON_Y.equals(mndtryFlg) || ZYPConstant.FLG_ON_Y.equals(cnfgrFlg) || ZYPConstant.FLG_ON_Y.equals(mstFlg)) {
            if (ZYPConstant.FLG_ON_Y.equals(mndtryFlg)) {
                msgMap.addXxMsgId(NLZM1001E);
            }
            if (ZYPConstant.FLG_ON_Y.equals(cnfgrFlg)) {
                msgMap.addXxMsgId(NLZM1014E);
            }
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param param NLZC200001PMsg
     */
    protected void doInvoice(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        paramMap.put(BIND_IMPT_INV_PK, param.imptInvPk.getValue());
        paramMap.put(BIND_IMPT_INV_CLS_CD, IMPT_INV_CLS.N_INVOICE);
        paramMap.put(BIND_IMPT_INV_PRT_FLG, ZYPConstant.FLG_OFF_N);
        paramMap.put(BIND_IMPT_INV_ACTV_VRSN_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(BIND_IMPT_INV_SLP_CLS_CD, IMPT_INV_SLP_CLS.A1_INVOICE);
        paramMap.put(BIND_IMPT_PACK_SLP_STS_CD, IMPT_INV_STS.INV_REL);
        paramMap.put(BIND_IN_PACK_TOT_QTY, RWS_QTY_ZERO);
        String statementId = GET_INV_INFO;

        PreparedStatement updateState = null;
        ResultSet updateRs = null;

        try {
            updateState = this.ssmClient.createPreparedStatement(statementId, paramMap, execParam);
            updateRs = updateState.executeQuery();
            boolean cur = updateRs.next();
            if (!cur) {
                msgMap.addXxMsgId(NLZM1004E);
                NLXC025001.outputLog(1, NLAM1132E, new String[] {statementId
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.imptInvPk, IMPT_INV_CLS.N_INVOICE, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y, IMPT_INV_SLP_CLS.A1_INVOICE, IMPT_INV_STS.INV_REL)}, onBatchType, this);
                return;
            }
            SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
            sceOrdTpT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            sceOrdTpT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_IV);
            sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.INBOUND);
            sceOrdTpT = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpT);
            if (sceOrdTpT == null) {
                msgMap.addXxMsgId(NLZM2016E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, NLXSceConst.SCE_ORD_TP_CD_IV, INBD_OTBD.INBOUND) }, onBatchType, this);
                return;
            }
            if (!ZYPCommonFunc.hasValue(updateRs.getString(VND_CD))) {
                msgMap.addXxMsgId(NLZM1024E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_VND, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, VND_CD), NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(IMPT_VND_CD)) }, onBatchType, this);
                return;
            }
            String pastWhCd = null;
            String currentWhCd = null;
            int weight = 0;
            // RWS#
            String rwsNum = null;
            // RWS Line#
            int lineNum = 1;
            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            int i = 0;

            for (; cur;) {
                if (!NLXSceConst.DRCT_SHIP_WH_CD.equals(updateRs.getString(DEF_INVTY_LOC_CD))) {
                    currentWhCd = updateRs.getString(DEF_INVTY_LOC_CD);
                    if (!currentWhCd.equals(pastWhCd)) {
                        if (pastWhCd != null) {
                            rwsHdrT.totRcvWt.setValue(weight);
                            S21ApiTBLAccessor.insert(rwsHdrT);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                                throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd, rwsHdrT.rwsNum) });
                            }
                            param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                            param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
                            i++;
                            rwsHdrT.clear();
                            weight = 0;
                            lineNum = 1;
                        }
                        String glblCmpyCd = param.glblCmpyCd.getValue();
                        rwsNum = preChkToZYPNumbering(glblCmpyCd, onBatchType);

                        rwsHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                        rwsHdrT.rwsNum.setValue(rwsNum);
                        rwsHdrT.inbdSrcTpCd.setValue(INBD_SRC_TP.INVOICE);
                        rwsHdrT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_IV);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, updateRs.getString(IMPT_INV_NUM));
                        rwsHdrT.imptInvPk.setValue(updateRs.getBigDecimal(IMPT_INV_PK));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvNum, updateRs.getString(IMPT_INV_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvClsCd, updateRs.getString(IMPT_INV_CLS_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVerNum, updateRs.getString(IMPT_INV_VER_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvSlpClsCd, updateRs.getString(IMPT_INV_SLP_CLS_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvBolNum, updateRs.getString(IMPT_INV_BOL_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, updateRs.getString(DEF_INVTY_LOC_CD));
                        rwsHdrT.fromLocTpCd.setValue(LOC_TP.VENDOR);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, updateRs.getString(IMPT_VND_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(ADDL_LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FIRST_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(SCD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(THIRD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FRTH_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(CTY_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(ST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(POST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(CTRY_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(TEL_NUM));
                        rwsHdrT.trxCd.setValue(sceOrdTpT.trxCd.getValue());
                        rwsHdrT.trxRsnCd.setValue(sceOrdTpT.trxRsnCd.getValue());
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, updateRs.getString(IN_PACK_WH_IN_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.ltstWhInEtaDt, updateRs.getString(IN_PACK_WH_IN_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCarrCd, updateRs.getString(IMPT_INV_CARR_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvShpgMethCd, updateRs.getString(IMPT_INV_SHPG_METH_CD));
                        rwsHdrT.drctShipTpCd.setValue(DRCT_SHIP_TP.N_OR_A);
                        rwsHdrT.wmsDropStsCd.setValue(WMS_DROP_STS_CD_NOT_DRP);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, sysDt);
                        rwsHdrT.rwsPrtFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.rwsStsCd.setValue(RWS_STS.PRINTED);
                        rwsHdrT.schdEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.finalEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.sysSrcCd.setValue(param.sysSrcCd.getValue());
                        if (IMPT_INV_SHPG_METH.AIR.equals(rwsHdrT.imptInvShpgMethCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_FLGT_NUM));
                        } else if (IMPT_INV_SHPG_METH.OCEAN.equals(rwsHdrT.imptInvShpgMethCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_VESL_NM));
                        }
                    }
                    RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();

                    rwsDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    rwsDtlT.rwsNum.setValue(rwsNum);
                    rwsDtlT.rwsDtlLineNum.setValue(ZYPCommonFunc.leftPad(Integer.toString(lineNum), RWS_DTL_LINE_NUM_LENGTH, RWS_DTL_LINE_NUM_PUT_NUM));
                    rwsDtlT.imptInvPk.setValue(updateRs.getBigDecimal(IMPT_INV_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvNum, updateRs.getString(IMPT_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvClsCd, updateRs.getString(IMPT_INV_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvVerNum, updateRs.getString(IMPT_INV_VER_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvSlpClsCd, updateRs.getString(IMPT_INV_SLP_CLS_CD));
                    rwsDtlT.imptInvDoPk.setValue(updateRs.getBigDecimal(IMPT_INV_DO_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvDoNum, updateRs.getString(IMPT_INV_DO_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptDoClsCd, updateRs.getString(IMPT_DO_CLS_CD));
                    rwsDtlT.imptPackSlpPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpNum, updateRs.getString(IMPT_PACK_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpLineNum, updateRs.getString(IMPT_PACK_SLP_LINE_NUM));
                    rwsDtlT.imptPackSlpDtlPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_DTL_PK));
                    rwsDtlT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpDtlLineNum, updateRs.getString(IMPT_PACK_SLP_DTL_LINE_NUM));
                    rwsDtlT.mdseCd.setValue(updateRs.getString(MDSE_CD));
                    rwsDtlT.invtyStkStsCd.setValue(STK_STS.GOOD);
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, updateRs.getBigDecimal(IN_PACK_TOT_QTY));
                    rwsDtlT.rwsPutAwayQty.setValue(Integer.parseInt(RWS_PUT_AWAY_QTY_ZERO));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, updateRs.getString(IN_PACK_WH_IN_ETA_DT));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackCseClsCd, updateRs.getString(IMPT_PACK_CSE_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackFromCseNum, updateRs.getBigDecimal(OUT_PACK_FROM_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackToCseNum, updateRs.getBigDecimal(OUT_PACK_TO_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackCseQty, updateRs.getBigDecimal(OUT_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackCseQty, updateRs.getBigDecimal(IN_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackUnitQty, updateRs.getBigDecimal(IN_PACK_UNIT_QTY));
                    rwsDtlT.rwsStsCd.setValue(RWS_STS.PRINTED);
                    rwsDtlT.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]

                    S21ApiTBLAccessor.insert(rwsDtlT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {
                        throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                                NLXCMsgHelper.toListedString(param.glblCmpyCd, rwsDtlT.rwsNum, rwsDtlT.rwsDtlLineNum) });
                    }

                    IMPT_PACK_SLP_STSTMsg imptPackSlpStsT = new IMPT_PACK_SLP_STSTMsg();
                    imptPackSlpStsT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    imptPackSlpStsT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    imptPackSlpStsT = (IMPT_PACK_SLP_STSTMsg) S21ApiTBLAccessor.findByKeyForUpdate(imptPackSlpStsT);
                    if (imptPackSlpStsT == null) {
                        throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK)) });
                    }

                    imptPackSlpStsT.imptPackSlpStsCd.setValue(IMPT_INV_STS.RWS_CRTD);

                    S21ApiTBLAccessor.update(imptPackSlpStsT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(imptPackSlpStsT.getReturnCode())) {
                        throw new S21AbendException(NLAM1134E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(imptPackSlpStsT.glblCmpyCd, imptPackSlpStsT.imptPackSlpStsPk) });
                    }
                    weight += updateRs.getInt(IN_PACK_NET_WT);
                    lineNum++;
                }
                pastWhCd = currentWhCd;
                cur = updateRs.next();
            }
            if (ZYPCommonFunc.hasValue(rwsHdrT.glblCmpyCd)) {
                rwsHdrT.totRcvWt.setValue(weight);
                S21ApiTBLAccessor.insert(rwsHdrT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                }
                param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
            }
            rwsHdrT.clear();
            weight = 0;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param param NLZC200001PMsg
     */
    protected void doContainer(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        paramMap.put(BIND_IMPT_CNTNR_LOT_NUM, param.imptCntnrLotNum.getValue());
        paramMap.put(BIND_IMPT_CNTNR_NUM, param.imptCntnrNum.getValue());
        paramMap.put(BIND_IMPT_PACK_SLP_STS_CD, IMPT_INV_STS.INV_REL);
        paramMap.put(BIND_IMPT_INV_CLS_CD, IMPT_INV_CLS.N_INVOICE);
        paramMap.put(BIND_IMPT_INV_PRT_FLG, ZYPConstant.FLG_OFF_N);
        paramMap.put(BIND_IMPT_INV_ACTV_VRSN_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(BIND_IMPT_INV_SLP_CLS_CD, IMPT_INV_SLP_CLS.A1_INVOICE);
        paramMap.put(BIND_IN_PACK_TOT_QTY, RWS_QTY_ZERO);

        String statementId = GET_CNTNR_INFO;

        PreparedStatement updateState = null;
        ResultSet updateRs = null;
        try {
            updateState = this.ssmClient.createPreparedStatement(statementId, paramMap, execParam);
            updateRs = updateState.executeQuery();
            boolean cur = updateRs.next();
            if (!cur) {
                msgMap.addXxMsgId(NLZM1004E);
                NLXC025001.outputLog(1, NLAM1132E, new String[] {statementId,
                        NLXCMsgHelper.toListedString(param.glblCmpyCd, param.imptCntnrLotNum, param.imptCntnrNum, IMPT_INV_STS.INV_REL, IMPT_INV_CLS.N_INVOICE, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y, IMPT_INV_SLP_CLS.A1_INVOICE) }, onBatchType, this);
                return;
            }
            SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
            sceOrdTpT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            sceOrdTpT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_CT);
            sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.INBOUND);
            sceOrdTpT = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpT);
            if (sceOrdTpT == null) {
                msgMap.addXxMsgId(NLZM2016E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, NLXSceConst.SCE_ORD_TP_CD_CT, INBD_OTBD.INBOUND) }, onBatchType, this);
                return;
            }
            if (!ZYPCommonFunc.hasValue(updateRs.getString(VND_CD))) {
                msgMap.addXxMsgId(NLZM1024E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_VND, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, VND_CD), NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(IMPT_VND_CD)) }, onBatchType, this);
                return;
            }
            String pastWhCd = null;
            String currentWhCd = null;
            int weight = 0;
            // RWS#
            String rwsNum = null;
            // RWS Line#
            int lineNum = 1;
            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            int i = 0;

            for (; cur;) {
                currentWhCd = updateRs.getString(DEF_INVTY_LOC_CD);
                if (!NLXSceConst.DRCT_SHIP_WH_CD.equals(currentWhCd)) {

                    if (!currentWhCd.equals(pastWhCd)) {
                        if (pastWhCd != null) {
                            rwsHdrT.totRcvWt.setValue(weight);
                            S21ApiTBLAccessor.insert(rwsHdrT);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                                throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                            }
                            param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                            param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
                            i++;
                            rwsHdrT.clear();
                            weight = 0;
                            lineNum = 1;
                        }
                        String glblCmpyCd = param.glblCmpyCd.getValue();
                        rwsNum = preChkToZYPNumbering(glblCmpyCd, onBatchType);

                        rwsHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                        rwsHdrT.rwsNum.setValue(rwsNum);
                        rwsHdrT.inbdSrcTpCd.setValue(INBD_SRC_TP.CONTAINER);
                        rwsHdrT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_CT);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, updateRs.getString(IMPT_CNTNR_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptCntnrLotNum, updateRs.getString(IMPT_CNTNR_LOT_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptCntnrNum, updateRs.getString(IMPT_CNTNR_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.cntnrSealNum, updateRs.getString(IMPT_CNTNR_SEAL_NUM_01));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, updateRs.getString(DEF_INVTY_LOC_CD));
                        rwsHdrT.fromLocTpCd.setValue(LOC_TP.VENDOR);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, updateRs.getString(IMPT_VND_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(ADDL_LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FIRST_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(SCD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(THIRD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FRTH_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(CTY_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(ST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(POST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(CTRY_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(TEL_NUM));
                        rwsHdrT.trxCd.setValue(sceOrdTpT.trxCd.getValue());
                        rwsHdrT.trxRsnCd.setValue(sceOrdTpT.trxRsnCd.getValue());
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, updateRs.getString(IN_PACK_WH_IN_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.ltstWhInEtaDt, updateRs.getString(IN_PACK_WH_IN_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCarrCd, updateRs.getString(IMPT_INV_CARR_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvShpgMethCd, updateRs.getString(IMPT_INV_SHPG_METH_CD));
                        rwsHdrT.drctShipTpCd.setValue(DRCT_SHIP_TP.N_OR_A);
                        rwsHdrT.wmsDropStsCd.setValue(WMS_DROP_STS_CD_NOT_DRP);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, sysDt);
                        rwsHdrT.rwsPrtFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.rwsStsCd.setValue(RWS_STS.PRINTED);
                        rwsHdrT.schdEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.finalEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.sysSrcCd.setValue(param.sysSrcCd.getValue());
                        if (IMPT_INV_SHPG_METH.AIR.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_FLGT_NUM));
                        } else if (IMPT_INV_SHPG_METH.OCEAN.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_VESL_NM));
                        }
                    }
                    RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();

                    rwsDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    rwsDtlT.rwsNum.setValue(rwsNum);
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsDtlLineNum, ZYPCommonFunc.leftPad(Integer.toString(lineNum), RWS_DTL_LINE_NUM_LENGTH, RWS_DTL_LINE_NUM_PUT_NUM));
                    rwsDtlT.imptInvPk.setValue(updateRs.getBigDecimal(IMPT_INV_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvNum, updateRs.getString(IMPT_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvClsCd, updateRs.getString(IMPT_INV_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvVerNum, updateRs.getString(IMPT_INV_VER_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvSlpClsCd, updateRs.getString(IMPT_INV_SLP_CLS_CD));
                    rwsDtlT.imptInvDoPk.setValue(updateRs.getBigDecimal(IMPT_INV_DO_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvDoNum, updateRs.getString(IMPT_INV_DO_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptDoClsCd, updateRs.getString(IMPT_DO_CLS_CD));
                    rwsDtlT.imptPackSlpPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpNum, updateRs.getString(IMPT_PACK_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpLineNum, updateRs.getString(IMPT_PACK_SLP_LINE_NUM));
                    rwsDtlT.imptPackSlpDtlPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_DTL_PK));
                    rwsDtlT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpDtlLineNum, updateRs.getString(IMPT_PACK_SLP_DTL_LINE_NUM));
                    rwsDtlT.mdseCd.setValue(updateRs.getString(MDSE_CD));
                    rwsDtlT.invtyStkStsCd.setValue(STK_STS.GOOD);
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, updateRs.getBigDecimal(IN_PACK_TOT_QTY));
                    rwsDtlT.rwsPutAwayQty.setValue(Integer.parseInt(RWS_PUT_AWAY_QTY_ZERO));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, updateRs.getString(IN_PACK_WH_IN_ETA_DT));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackCseClsCd, updateRs.getString(IMPT_PACK_CSE_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackFromCseNum, updateRs.getBigDecimal(OUT_PACK_FROM_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackToCseNum, updateRs.getBigDecimal(OUT_PACK_TO_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackCseQty, updateRs.getBigDecimal(OUT_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackCseQty, updateRs.getBigDecimal(IN_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackUnitQty, updateRs.getBigDecimal(IN_PACK_UNIT_QTY));
                    rwsDtlT.rwsStsCd.setValue(RWS_STS.PRINTED);
                    rwsDtlT.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]

                    S21ApiTBLAccessor.insert(rwsDtlT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {
                        throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                                NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd, rwsDtlT.rwsNum, rwsDtlT.rwsDtlLineNum) });
                    }

                    IMPT_PACK_SLP_STSTMsg imptPackSlpStsT = new IMPT_PACK_SLP_STSTMsg();
                    imptPackSlpStsT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    imptPackSlpStsT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));

                    imptPackSlpStsT = (IMPT_PACK_SLP_STSTMsg) S21ApiTBLAccessor.findByKeyForUpdate(imptPackSlpStsT);
                    if (imptPackSlpStsT == null) {
                        throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK)) });
                    }
                    imptPackSlpStsT.imptPackSlpStsCd.setValue(IMPT_INV_STS.RWS_CRTD);

                    S21ApiTBLAccessor.update(imptPackSlpStsT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(imptPackSlpStsT.getReturnCode())) {
                        throw new S21AbendException(NLAM1134E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(imptPackSlpStsT.glblCmpyCd, imptPackSlpStsT.imptPackSlpStsPk) });
                    }
                    weight += updateRs.getInt(IN_PACK_NET_WT);
                    lineNum++;
                }
                pastWhCd = currentWhCd;
                cur = updateRs.next();
            }
            if (ZYPCommonFunc.hasValue(rwsHdrT.glblCmpyCd)) {
                rwsHdrT.totRcvWt.setValue(weight);
                S21ApiTBLAccessor.insert(rwsHdrT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                }
                param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
            }
            rwsHdrT.clear();
            weight = 0;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param param NLZC200001PMsg
     */
    protected void doScpOrder(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        if (ZYPCommonFunc.hasValue(param.imptInvPk)) {
            doScpOrdInv(msgMap, onBatchType);
        } else if (ZYPCommonFunc.hasValue(param.imptCntnrNum)) {
            doScpOrdCntnr(msgMap, onBatchType);
        }

    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void doScpOrdInv(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        paramMap.put(BIND_IMPT_INV_PK, param.imptInvPk.getValue());
        paramMap.put(BIND_REQ_STK_IN_QTY, RWS_QTY_ZERO);
        paramMap.put(BIND_IMPT_PACK_SLP_STS_CD, IMPT_INV_STS.INV_REL);
        paramMap.put(BIND_IMPT_INV_CLS_CD, IMPT_INV_CLS.N_INVOICE);
        paramMap.put(BIND_IMPT_INV_PRT_FLG, ZYPConstant.FLG_OFF_N);
        paramMap.put(BIND_IMPT_INV_ACTV_VRSN_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(BIND_IMPT_INV_SLP_CLS_CD, IMPT_INV_SLP_CLS.A1_INVOICE);


        String statementId = GET_SCP_INV_INFO;

        PreparedStatement updateState = null;
        ResultSet updateRs = null;

        try {
            updateState = this.ssmClient.createPreparedStatement(statementId, paramMap, execParam);
            updateRs = updateState.executeQuery();
            boolean cur = updateRs.next();
            if (!cur) {
                msgMap.addXxMsgId(NLZM1004E);
                NLXC025001.outputLog(1, NLAM1132E, new String[] {statementId,
                        NLXCMsgHelper.toListedString(param.glblCmpyCd, param.imptInvPk, IMPT_INV_STS.INV_REL, IMPT_INV_CLS.N_INVOICE, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y, IMPT_INV_SLP_CLS.A1_INVOICE) }, onBatchType, this);
                return;
            }
            SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
            sceOrdTpT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            sceOrdTpT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_IO);
            sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.INBOUND);
            sceOrdTpT = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpT);
            if (sceOrdTpT == null) {
                msgMap.addXxMsgId(NLZM2016E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, NLXSceConst.SCE_ORD_TP_CD_IO, INBD_OTBD.INBOUND) }, onBatchType, this);
                return;
            }
            if (!ZYPCommonFunc.hasValue(updateRs.getString(VND_CD))) {
                msgMap.addXxMsgId(NLZM1024E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_VND, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, VND_CD),
                        NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(IMPT_VND_CD)) }, onBatchType, this);
                return;
            }
            String pastWhCd = null;
            String currentWhCd = null;
            int weight = 0;
            // RWS#
            String rwsNum = null;
            // RWS Line#
            int lineNum = 1;
            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            int i = 0;

            for (; cur;) {
                if (!NLXSceConst.DRCT_SHIP_WH_CD.equals(updateRs.getString(REQ_STK_IN_WH_CD))) {
                    currentWhCd = updateRs.getString(REQ_STK_IN_WH_CD);
                    if (!currentWhCd.equals(pastWhCd)) {
                        if (pastWhCd != null) {
                            rwsHdrT.totRcvWt.setValue(weight);
                            S21ApiTBLAccessor.insert(rwsHdrT);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                                throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                            }
                            param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                            param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
                            i++;
                            rwsHdrT.clear();
                            weight = 0;
                            lineNum = 1;
                        }
                        String glblCmpyCd = param.glblCmpyCd.getValue();
                        rwsNum = preChkToZYPNumbering(glblCmpyCd, onBatchType);

                        rwsHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                        rwsHdrT.rwsNum.setValue(rwsNum);
                        rwsHdrT.inbdSrcTpCd.setValue(INBD_SRC_TP.SCP_ORDER);
                        rwsHdrT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_IO);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, updateRs.getString(IMPT_INV_NUM));
                        rwsHdrT.imptInvPk.setValue(updateRs.getBigDecimal(IMPT_INV_PK));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvNum, updateRs.getString(IMPT_INV_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvClsCd, updateRs.getString(IMPT_INV_CLS_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVerNum, updateRs.getString(IMPT_INV_VER_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvSlpClsCd, updateRs.getString(IMPT_INV_SLP_CLS_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvBolNum, updateRs.getString(IMPT_INV_BOL_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.trxOrdNum, updateRs.getString(LGSC_SCP_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, updateRs.getString(REQ_STK_IN_WH_CD));
                        rwsHdrT.fromLocTpCd.setValue(LOC_TP.VENDOR);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, updateRs.getString(IMPT_VND_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(ADDL_LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FIRST_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(SCD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(THIRD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FRTH_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(CTY_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(ST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(POST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(CTRY_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(TEL_NUM));
                        rwsHdrT.trxCd.setValue(sceOrdTpT.trxCd.getValue());
                        rwsHdrT.trxRsnCd.setValue(sceOrdTpT.trxRsnCd.getValue());
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, updateRs.getString(REQ_WH_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.ltstWhInEtaDt, updateRs.getString(REQ_WH_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCarrCd, updateRs.getString(INLND_CARR_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvShpgMethCd, updateRs.getString(IMPT_INV_SHPG_METH_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.inlndShpgMethCd, updateRs.getString(INLND_SHPG_METH_CD));
                        rwsHdrT.drctShipTpCd.setValue(DRCT_SHIP_TP.N_OR_A);
                        rwsHdrT.wmsDropStsCd.setValue(WMS_DROP_STS_CD_NOT_DRP);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, sysDt);
                        rwsHdrT.rwsPrtFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.rwsStsCd.setValue(RWS_STS.PRINTED);
                        rwsHdrT.schdEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.finalEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.sysSrcCd.setValue(param.sysSrcCd.getValue());
                        if (IMPT_INV_SHPG_METH.AIR.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_FLGT_NUM));
                        } else if (IMPT_INV_SHPG_METH.OCEAN.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_VESL_NM));
                        }
                    }
                    RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();
                    rwsDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    rwsDtlT.rwsNum.setValue(rwsNum);
                    rwsDtlT.rwsDtlLineNum.setValue(ZYPCommonFunc.leftPad(Integer.toString(lineNum), RWS_DTL_LINE_NUM_LENGTH, RWS_DTL_LINE_NUM_PUT_NUM));
                    rwsDtlT.imptInvPk.setValue(updateRs.getBigDecimal(IMPT_INV_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvNum, updateRs.getString(IMPT_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvClsCd, updateRs.getString(IMPT_INV_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvVerNum, updateRs.getString(IMPT_INV_VER_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvSlpClsCd, updateRs.getString(IMPT_INV_SLP_CLS_CD));
                    rwsDtlT.imptInvDoPk.setValue(updateRs.getBigDecimal(IMPT_INV_DO_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvDoNum, updateRs.getString(IMPT_INV_DO_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptDoClsCd, updateRs.getString(IMPT_DO_CLS_CD));
                    rwsDtlT.imptPackSlpPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpNum, updateRs.getString(IMPT_PACK_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpLineNum, updateRs.getString(IMPT_PACK_SLP_LINE_NUM));
                    rwsDtlT.imptPackSlpDtlPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_DTL_PK));
                    rwsDtlT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpDtlLineNum, updateRs.getString(IMPT_PACK_SLP_DTL_LINE_NUM));
                    rwsDtlT.mdseCd.setValue(updateRs.getString(MDSE_CD));
                    rwsDtlT.invtyStkStsCd.setValue(STK_STS.GOOD);
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, updateRs.getBigDecimal(REQ_STK_IN_QTY));
                    rwsDtlT.rwsPutAwayQty.setValue(Integer.parseInt(RWS_PUT_AWAY_QTY_ZERO));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, updateRs.getString(REQ_WH_ETA_DT));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackCseClsCd, updateRs.getString(IMPT_PACK_CSE_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackFromCseNum, updateRs.getBigDecimal(OUT_PACK_FROM_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackToCseNum, updateRs.getBigDecimal(OUT_PACK_TO_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackCseQty, updateRs.getBigDecimal(OUT_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackCseQty, updateRs.getBigDecimal(IN_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackUnitQty, updateRs.getBigDecimal(IN_PACK_UNIT_QTY));
                    rwsDtlT.rwsStsCd.setValue(RWS_STS.PRINTED);
                    rwsDtlT.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]

                    S21ApiTBLAccessor.insert(rwsDtlT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {
                        throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                                NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd, rwsDtlT.rwsNum, rwsDtlT.rwsDtlLineNum) });
                    }
                    IMPT_PACK_SLP_STSTMsg imptPackSlpStsT = new IMPT_PACK_SLP_STSTMsg();
                    imptPackSlpStsT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    imptPackSlpStsT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    imptPackSlpStsT = (IMPT_PACK_SLP_STSTMsg) S21ApiTBLAccessor.findByKeyForUpdate(imptPackSlpStsT);
                    if (imptPackSlpStsT == null) {
                        throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK)) });
                    }
                    imptPackSlpStsT.imptPackSlpStsCd.setValue(IMPT_INV_STS.RWS_CRTD);
                    S21ApiTBLAccessor.update(imptPackSlpStsT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(imptPackSlpStsT.getReturnCode())) {
                        throw new S21AbendException(NLAM1134E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(imptPackSlpStsT.glblCmpyCd, imptPackSlpStsT.imptPackSlpStsPk) });
                    }

                    weight += updateRs.getInt(IN_PACK_NET_WT);
                    lineNum++;
                }
                pastWhCd = currentWhCd;
                cur = updateRs.next();
            }
            if (ZYPCommonFunc.hasValue(rwsHdrT.glblCmpyCd)) {
                rwsHdrT.totRcvWt.setValue(weight);
                S21ApiTBLAccessor.insert(rwsHdrT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                }
                param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
            }
            rwsHdrT.clear();
            weight = 0;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void doScpOrdCntnr(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        paramMap.put(BIND_IMPT_CNTNR_LOT_NUM, param.imptCntnrLotNum.getValue());
        paramMap.put(BIND_IMPT_CNTNR_NUM, param.imptCntnrNum.getValue());
        paramMap.put(BIND_REQ_STK_IN_QTY, RWS_QTY_ZERO);
        paramMap.put(BIND_IMPT_PACK_SLP_STS_CD, IMPT_INV_STS.INV_REL);
        paramMap.put(BIND_IMPT_INV_CLS_CD, IMPT_INV_CLS.N_INVOICE);
        paramMap.put(BIND_IMPT_INV_PRT_FLG, ZYPConstant.FLG_OFF_N);
        paramMap.put(BIND_IMPT_INV_ACTV_VRSN_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(BIND_IMPT_INV_SLP_CLS_CD, IMPT_INV_SLP_CLS.A1_INVOICE);

        String statementId = GET_SCP_CNTNR_INFO;
        PreparedStatement updateState = null;
        ResultSet updateRs = null;

        try {
            updateState = this.ssmClient.createPreparedStatement(statementId, paramMap, execParam);
            updateRs = updateState.executeQuery();
            boolean cur = updateRs.next();
            if (!cur) {
                msgMap.addXxMsgId(NLZM1004E);
                NLXC025001.outputLog(1, NLAM1132E, new String[] {statementId,
                        NLXCMsgHelper.toListedString(param.glblCmpyCd, param.imptCntnrLotNum, param.imptCntnrNum, IMPT_INV_STS.INV_REL, IMPT_INV_CLS.N_INVOICE, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y, IMPT_INV_SLP_CLS.A1_INVOICE) }, onBatchType, this);
                return;
            }
            SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
            sceOrdTpT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            sceOrdTpT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_CO);
            sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.INBOUND);
            sceOrdTpT = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpT);
            if (sceOrdTpT == null) {
                msgMap.addXxMsgId(NLZM2016E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, NLXSceConst.SCE_ORD_TP_CD_CO, INBD_OTBD.INBOUND) }, onBatchType, this);
                return;
            }
            if (!ZYPCommonFunc.hasValue(updateRs.getString(VND_CD))) {
                msgMap.addXxMsgId(NLZM1024E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_VND, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, VND_CD),
                        NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(IMPT_VND_CD)) }, onBatchType, this);
                return;
            }
            String pastWhCd = null;
            String currentWhCd = null;
            int weight = 0;
            // RWS#
            String rwsNum = null;
            // RWS Line#
            int lineNum = 1;
            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            int i = 0;

            for (; cur;) {
                if (!NLXSceConst.DRCT_SHIP_WH_CD.equals(updateRs.getString(REQ_STK_IN_WH_CD))) {
                    currentWhCd = updateRs.getString(REQ_STK_IN_WH_CD);
                    if (!currentWhCd.equals(pastWhCd)) {
                        if (pastWhCd != null) {
                            rwsHdrT.totRcvWt.setValue(weight);
                            S21ApiTBLAccessor.insert(rwsHdrT);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                                throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                            }
                            param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                            param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
                            i++;
                            rwsHdrT.clear();
                            weight = 0;
                            lineNum = 1;
                        }
                        String glblCmpyCd = param.glblCmpyCd.getValue();
                        rwsNum = preChkToZYPNumbering(glblCmpyCd, onBatchType);

                        rwsHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                        rwsHdrT.rwsNum.setValue(rwsNum);
                        rwsHdrT.inbdSrcTpCd.setValue(INBD_SRC_TP.SCP_ORDER);
                        rwsHdrT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_CO);
                        rwsHdrT.rwsRefNum.setValue(updateRs.getString(IMPT_CNTNR_NUM));
                        rwsHdrT.imptCntnrLotNum.setValue(updateRs.getString(IMPT_CNTNR_LOT_NUM));
                        rwsHdrT.imptCntnrNum.setValue(updateRs.getString(IMPT_CNTNR_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.cntnrSealNum, updateRs.getString(IMPT_CNTNR_SEAL_NUM_01));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.trxOrdNum, updateRs.getString(LGSC_SCP_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, updateRs.getString(REQ_STK_IN_WH_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTpCd, LOC_TP.VENDOR);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, updateRs.getString(IMPT_VND_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(ADDL_LOC_NM));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FIRST_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(SCD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(THIRD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FRTH_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(CTY_ADDR));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(ST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(POST_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(CTRY_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(TEL_NUM));
                        rwsHdrT.trxCd.setValue(sceOrdTpT.trxCd.getValue());
                        rwsHdrT.trxRsnCd.setValue(sceOrdTpT.trxRsnCd.getValue());
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, updateRs.getString(REQ_WH_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.ltstWhInEtaDt, updateRs.getString(REQ_WH_ETA_DT));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCarrCd, updateRs.getString(INLND_CARR_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvShpgMethCd, updateRs.getString(IMPT_INV_SHPG_METH_CD));
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.inlndShpgMethCd, updateRs.getString(INLND_SHPG_METH_CD));
                        rwsHdrT.drctShipTpCd.setValue(DRCT_SHIP_TP.N_OR_A);
                        rwsHdrT.wmsDropStsCd.setValue(WMS_DROP_STS_CD_NOT_DRP);
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, sysDt);
                        rwsHdrT.rwsPrtFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.rwsStsCd.setValue(RWS_STS.PRINTED);
                        rwsHdrT.schdEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.finalEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                        rwsHdrT.sysSrcCd.setValue(param.sysSrcCd.getValue());
                        if (IMPT_INV_SHPG_METH.AIR.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_FLGT_NUM));
                        } else if (IMPT_INV_SHPG_METH.OCEAN.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                            ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_VESL_NM));
                        }
                    }
                    RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();

                    rwsDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    rwsDtlT.rwsNum.setValue(rwsNum);
                    rwsDtlT.rwsDtlLineNum.setValue(ZYPCommonFunc.leftPad(Integer.toString(lineNum), RWS_DTL_LINE_NUM_LENGTH, RWS_DTL_LINE_NUM_PUT_NUM));
                    rwsDtlT.imptInvPk.setValue(updateRs.getBigDecimal(IMPT_INV_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvNum, updateRs.getString(IMPT_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvClsCd, updateRs.getString(IMPT_INV_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvVerNum, updateRs.getString(IMPT_INV_VER_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvSlpClsCd, updateRs.getString(IMPT_INV_SLP_CLS_CD));
                    rwsDtlT.imptInvDoPk.setValue(updateRs.getBigDecimal(IMPT_INV_DO_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvDoNum, updateRs.getString(IMPT_INV_DO_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptDoClsCd, updateRs.getString(IMPT_DO_CLS_CD));
                    rwsDtlT.imptPackSlpPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpNum, updateRs.getString(IMPT_PACK_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpLineNum, updateRs.getString(IMPT_PACK_SLP_LINE_NUM));
                    rwsDtlT.imptPackSlpDtlPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_DTL_PK));
                    rwsDtlT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpDtlLineNum, updateRs.getString(IMPT_PACK_SLP_DTL_LINE_NUM));
                    rwsDtlT.mdseCd.setValue(updateRs.getString(MDSE_CD));
                    rwsDtlT.invtyStkStsCd.setValue(STK_STS.GOOD);
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, updateRs.getBigDecimal(REQ_STK_IN_QTY));
                    rwsDtlT.rwsPutAwayQty.setValue(Integer.parseInt(RWS_PUT_AWAY_QTY_ZERO));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, updateRs.getString(REQ_WH_ETA_DT));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackCseClsCd, updateRs.getString(IMPT_PACK_CSE_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackFromCseNum, updateRs.getBigDecimal(OUT_PACK_FROM_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackToCseNum, updateRs.getBigDecimal(OUT_PACK_TO_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackCseQty, updateRs.getBigDecimal(OUT_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackCseQty, updateRs.getBigDecimal(IN_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackUnitQty, updateRs.getBigDecimal(IN_PACK_UNIT_QTY));
                    rwsDtlT.rwsStsCd.setValue(RWS_STS.PRINTED);
                    rwsDtlT.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]

                    S21ApiTBLAccessor.insert(rwsDtlT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {
                        throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                                NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd, rwsDtlT.rwsNum, rwsDtlT.rwsDtlLineNum) });
                    }

                    IMPT_PACK_SLP_STSTMsg imptPackSlpStsT = new IMPT_PACK_SLP_STSTMsg();
                    imptPackSlpStsT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    imptPackSlpStsT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    imptPackSlpStsT = (IMPT_PACK_SLP_STSTMsg) S21ApiTBLAccessor.findByKeyForUpdate(imptPackSlpStsT);
                    if (imptPackSlpStsT == null) {
                        throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK)) });
                    }

                    imptPackSlpStsT.imptPackSlpStsCd.setValue(IMPT_INV_STS.RWS_CRTD);

                    S21ApiTBLAccessor.update(imptPackSlpStsT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(imptPackSlpStsT.getReturnCode())) {
                        throw new S21AbendException(NLAM1134E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(imptPackSlpStsT.glblCmpyCd, imptPackSlpStsT.imptPackSlpStsPk) });
                    }
                    weight += updateRs.getInt(IN_PACK_NET_WT);
                    lineNum++;
                }
                pastWhCd = currentWhCd;
                cur = updateRs.next();
            }
            if (ZYPCommonFunc.hasValue(rwsHdrT.glblCmpyCd)) {
                rwsHdrT.totRcvWt.setValue(weight);
                S21ApiTBLAccessor.insert(rwsHdrT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                }
                param.RWSNumList.no(i).rwsNum.setValue(rwsNum);
                param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
            }
            rwsHdrT.clear();
            weight = 0;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param param NLZC200001PMsg
     */
    protected void doScpAsn(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD,           param.glblCmpyCd.getValue());
        paramMap.put(BIND_TRUCK_CNTNR_PK,         param.truckCntnrPk.getValue());
        paramMap.put(BIND_ASN_STK_IN_QTY,         RWS_QTY_ZERO);
        paramMap.put(BIND_SSM_STS_CD,             SSM_STS.ASN);
        paramMap.put(BIND_IMPT_INV_CLS_CD,        IMPT_INV_CLS.N_INVOICE);
        paramMap.put(BIND_IMPT_INV_PRT_FLG,       ZYPConstant.FLG_OFF_N);
        paramMap.put(BIND_IMPT_INV_ACTV_VRSN_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(BIND_IMPT_INV_SLP_CLS_CD,    IMPT_INV_SLP_CLS.A1_INVOICE);
        paramMap.put(BIND_ASN_RWS_STS_CD,         NLXSceConst.ASN_CREATED);

        String statementId = GET_SCP_ASN_INFO;

        PreparedStatement updateState = null;
        ResultSet updateRs = null;

        try {

            updateState = this.ssmClient.createPreparedStatement(statementId, paramMap, execParam);
            updateRs = updateState.executeQuery();

            boolean cur = updateRs.next();

            if (!cur) {
                msgMap.addXxMsgId(NLZM1004E);
                NLXC025001.outputLog(1, NLAM1132E, new String[] {statementId
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.truckCntnrPk, RWS_QTY_ZERO, SSM_STS.ASN, IMPT_INV_STS.INV_REL
                                , IMPT_INV_CLS.N_INVOICE, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y, IMPT_INV_SLP_CLS.A1_INVOICE) }, onBatchType, this);
                return;
            }

            SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
            sceOrdTpT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            sceOrdTpT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_CA);
            sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.INBOUND);
            sceOrdTpT = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpT);

            if (sceOrdTpT == null) {
                msgMap.addXxMsgId(NLZM2016E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, NLXSceConst.SCE_ORD_TP_CD_CA, INBD_OTBD.INBOUND) }, onBatchType, this);
                return;
            }

            if (!ZYPCommonFunc.hasValue(updateRs.getString(VND_CD))) {
                msgMap.addXxMsgId(NLZM1024E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_VND, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, VND_CD),
                        NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(IMPT_VND_CD)) }, onBatchType, this);
                return;
            }

            int weight = 0;
            // RWS#
            String rwsNum = null;
            // RWS Line#
            int lineNum = 1;
            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();

            if (!NLXSceConst.DRCT_SHIP_WH_CD.equals(updateRs.getString(STK_IN_WH_CD))) {

                String glblCmpyCd = param.glblCmpyCd.getValue();
                rwsNum = preChkToZYPNumbering(glblCmpyCd, onBatchType);

                rwsHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                rwsHdrT.rwsNum.setValue(rwsNum);
                rwsHdrT.inbdSrcTpCd.setValue(INBD_SRC_TP.SCP_ASN);
                rwsHdrT.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_CA);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, updateRs.getString(SCP_CNTNR_NUM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.cntnrSealNum, updateRs.getString(SCP_SEAL_NUM));
                rwsHdrT.truckCntnrPk.setValue(updateRs.getBigDecimal(TRUCK_CNTNR_PK));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.trxOrdNum, updateRs.getString(ASN_NUM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, updateRs.getString(STK_IN_WH_CD));
                rwsHdrT.fromLocTpCd.setValue(LOC_TP.VENDOR);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, updateRs.getString(IMPT_VND_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(LOC_NM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(ST_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(POST_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(CTRY_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(TEL_NUM));
                rwsHdrT.trxCd.setValue(sceOrdTpT.trxCd.getValue());
                rwsHdrT.trxRsnCd.setValue(sceOrdTpT.trxRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, updateRs.getString(WH_ETA_DT));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.ltstWhInEtaDt, updateRs.getString(WH_ETA_DT));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCarrCd, updateRs.getString(INLND_CARR_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvShpgMethCd, updateRs.getString(IMPT_INV_SHPG_METH_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.inlndShpgMethCd, updateRs.getString(INLND_SHPG_METH_CD));
                rwsHdrT.drctShipTpCd.setValue(DRCT_SHIP_TP.N_OR_A);
                rwsHdrT.wmsDropStsCd.setValue(WMS_DROP_STS_CD_NOT_DRP);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, sysDt);
                rwsHdrT.rwsPrtFlg.setValue(ZYPConstant.FLG_OFF_N);
                rwsHdrT.rwsStsCd.setValue(RWS_STS.PRINTED);
                rwsHdrT.schdEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                rwsHdrT.finalEtaChkFlg.setValue(ZYPConstant.FLG_OFF_N);
                rwsHdrT.sysSrcCd.setValue(param.sysSrcCd.getValue());

                if (IMPT_INV_SHPG_METH.AIR.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                    ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_FLGT_NUM));
                } else if (IMPT_INV_SHPG_METH.OCEAN.equals(updateRs.getString(IMPT_VND_SHPG_METH_CD))) {
                    ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvVeslNm, updateRs.getString(IMPT_INV_VESL_NM));
                }

                for (; cur;) {

                    RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();

                    rwsDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    rwsDtlT.rwsNum.setValue(rwsNum);
                    rwsDtlT.rwsDtlLineNum.setValue(ZYPCommonFunc.leftPad(Integer.toString(lineNum), RWS_DTL_LINE_NUM_LENGTH, RWS_DTL_LINE_NUM_PUT_NUM));
                    rwsDtlT.imptInvPk.setValue(updateRs.getBigDecimal(IMPT_INV_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvNum, updateRs.getString(IMPT_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvClsCd, updateRs.getString(IMPT_INV_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvVerNum, updateRs.getString(IMPT_INV_VER_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvSlpClsCd, updateRs.getString(IMPT_INV_SLP_CLS_CD));
                    rwsDtlT.imptInvDoPk.setValue(updateRs.getBigDecimal(IMPT_INV_DO_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvDoNum, updateRs.getString(IMPT_INV_DO_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptDoClsCd, updateRs.getString(IMPT_DO_CLS_CD));
                    rwsDtlT.imptPackSlpPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpNum, updateRs.getString(IMPT_PACK_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpLineNum, updateRs.getString(IMPT_PACK_SLP_LINE_NUM));
                    rwsDtlT.imptPackSlpDtlPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_DTL_PK));
                    rwsDtlT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackSlpDtlLineNum, updateRs.getString(IMPT_PACK_SLP_DTL_LINE_NUM));
                    rwsDtlT.mdseCd.setValue(updateRs.getString(MDSE_CD));
                    rwsDtlT.invtyStkStsCd.setValue(STK_STS.GOOD);
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, updateRs.getBigDecimal(ASN_STK_IN_QTY));
                    rwsDtlT.rwsPutAwayQty.setValue(Integer.parseInt(RWS_PUT_AWAY_QTY_ZERO));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, updateRs.getString(WH_ETA_DT));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptPackCseClsCd, updateRs.getString(IMPT_PACK_CSE_CLS_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackFromCseNum, updateRs.getBigDecimal(OUT_PACK_FROM_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackToCseNum, updateRs.getBigDecimal(OUT_PACK_TO_CSE_NUM));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.outPackCseQty, updateRs.getBigDecimal(OUT_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackCseQty, updateRs.getBigDecimal(IN_PACK_CSE_QTY));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.inPackUnitQty, updateRs.getBigDecimal(IN_PACK_UNIT_QTY));
                    rwsDtlT.rwsStsCd.setValue(RWS_STS.PRINTED);
                    rwsDtlT.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]

                    S21ApiTBLAccessor.insert(rwsDtlT);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {
                        throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                                NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd, rwsDtlT.rwsNum, rwsDtlT.rwsDtlLineNum) });
                    }

                    IMPT_PACK_SLP_STSTMsg imptPackSlpStsT = new IMPT_PACK_SLP_STSTMsg();
                    imptPackSlpStsT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    imptPackSlpStsT.imptPackSlpStsPk.setValue(updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK));

                    imptPackSlpStsT = (IMPT_PACK_SLP_STSTMsg) S21ApiTBLAccessor.findByKeyForUpdate(imptPackSlpStsT);

                    if (imptPackSlpStsT == null) {
                        throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getBigDecimal(IMPT_PACK_SLP_STS_PK)) });
                    }

                    imptPackSlpStsT.imptPackSlpStsCd.setValue(IMPT_INV_STS.RWS_CRTD);

                    S21ApiTBLAccessor.update(imptPackSlpStsT);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(imptPackSlpStsT.getReturnCode())) {
                        throw new S21AbendException(NLAM1134E, new String[] {NLXSceConst.TBL_IMPT_PACK_SLP_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, IMPT_PACK_SLP_STS_PK),
                                NLXCMsgHelper.toListedString(imptPackSlpStsT.glblCmpyCd, imptPackSlpStsT.imptPackSlpStsPk) });
                    }
                    weight += updateRs.getInt(IN_PACK_NET_WT);

                    cur = updateRs.next();

                    lineNum++;
                }

                rwsHdrT.totRcvWt.setValue(weight);

                S21ApiTBLAccessor.insert(rwsHdrT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                }

                param.RWSNumList.no(0).rwsNum.setValue(rwsNum);
                param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
        }
    }

    /**
     * <pre>
     * doPoReceiving
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    protected void doPoReceiving(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        paramMap.put(BIND_PO_RCV_NUM, param.poRcvNum.getValue());
        paramMap.put(BIND_RWS_STS_CD, RWS_STS.SAVED);
        paramMap.put(BIND_PO_RCV_QTY, RWS_QTY_ZERO);
        paramMap.put(BIND_INBD_OTBD_CD, INBD_OTBD.INBOUND);
        String statementId = GET_PO_RCV_INFO;
        PreparedStatement updateState = null;
        ResultSet updateRs = null;

        try {

            updateState = this.ssmClient.createPreparedStatement(statementId, paramMap, execParam);
            updateRs = updateState.executeQuery();
            boolean cur = updateRs.next();

            if (!cur) {
                msgMap.addXxMsgId(NLZM1004E);
                NLXC025001.outputLog(1, NLAM1132E, new String[] {statementId, NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvNum, RWS_STS.SAVED, INBD_OTBD.INBOUND) }, onBatchType, this);
                return;
            }
            if (!ZYPCommonFunc.hasValue(updateRs.getString(TRX_CD)) || !ZYPCommonFunc.hasValue(updateRs.getString(TRX_RSN_CD))) {
                msgMap.addXxMsgId(NLZM2016E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD),
                        NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(SCE_ORD_TP_CD), INBD_OTBD.INBOUND) }, onBatchType, this);
                return;
            }

            if (!ZYPCommonFunc.hasValue(updateRs.getString(INVTY_LOC_CD))) {
                msgMap.addXxMsgId(NLZM2185E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_WH, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WH_CD), NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(PO_RCV_FROM_LOC_CD)) }, onBatchType, this);
                return;
            }

            String glblCmpyCd = param.glblCmpyCd.getValue();
            String rwsNum = preChkToZYPNumbering(glblCmpyCd, onBatchType);

            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(rwsHdrT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsNum, rwsNum);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.sceOrdTpCd, updateRs.getString(SCE_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, updateRs.getString(RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.trxOrdNum, updateRs.getString(PO_RCV_NUM));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, updateRs.getString(WH_CD));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTpCd, updateRs.getString(PO_RCV_FROM_LOC_TP_CD));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, updateRs.getString(PO_RCV_FROM_LOC_CD));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.trxCd, updateRs.getString(TRX_CD));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.trxRsnCd, updateRs.getString(TRX_RSN_CD));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, updateRs.getString(PO_RCV_ETA_DT));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.ltstWhInEtaDt, updateRs.getString(PO_RCV_ETA_DT));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCarrCd, updateRs.getString(INLND_CARR_CD));
            rwsHdrT.totRcvWt.clear();
            ZYPEZDItemValueSetter.setValue(rwsHdrT.drctShipTpCd, DRCT_SHIP_TP.N_OR_A);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.wmsDropStsCd, WMS_DROP_STS_CD_NOT_DRP);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, sysDt);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsPrtFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsStsCd, RWS_STS.PRINTED);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.schdEtaChkFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.finalEtaChkFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.sysSrcCd, param.sysSrcCd.getValue());
            // 2013/03/25 QC821 S.Tanabe Add Start
            ZYPEZDItemValueSetter.setValue(rwsHdrT.poRcvTrxHdrNum, updateRs.getString(PO_RCV_TRX_HDR_NUM));
            // 2013/03/25 QC821 S.Tanabe Add End

            /** Merge DS **/
            BigDecimal svcConfigMstrPk = updateRs.getBigDecimal(SVC_CONFIG_MSTR_PK);
            String shipFromSoNum = updateRs.getString(SHIP_FROM_SO_NUM);
            String trxSrcTpCd = updateRs.getString(TRX_SRC_TP_CD);

            // START 2022/06/13 [QC#60022,ADD]
            String[] sceOrdTpList = ZYPCodeDataUtil.getVarCharConstValue(NLZC200001Constant.VAR_CHAR_CONCT_NM_TG_SCE_ORD_TP, param.glblCmpyCd.getValue()).split(",");
            // END   2022/06/13 [QC#60022,ADD]

            ZYPEZDItemValueSetter.setValue(rwsHdrT.trxSrcTpCd, getTrxSrcTpCd(rwsHdrT.sceOrdTpCd.getValue(), trxSrcTpCd));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.pickUpRqstFlg, ZYPConstant.FLG_OFF_N);
            // START 2022/06/13 [QC#60022,ADD]
            if (Arrays.asList(sceOrdTpList).contains(rwsHdrT.sceOrdTpCd.getValue()) 
            // START 2022/10/24 T.NEMA [QC#60022, DEL]
                    && WH_OWNR.STI.equals(updateRs.getString(WH_OWNR_CD))) { 
            //        && !isCarrSti(glblCmpyCd, rwsHdrT.rwsCarrCd.getValue(), CARR_TP.STI)) {
            // END   2022/10/24 T.NEMA [QC#60022, DEL]
                ZYPEZDItemValueSetter.setValue(rwsHdrT.pickUpRqstFlg, ZYPConstant.FLG_ON_Y);
            }
            // END   2022/06/13 [QC#60022,ADD]
            // START 2023/04/07 [QC#61001,ADD]
            String[] agwSceOrdTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NLZC200001Constant.AGW_TARGET_SCE_ORD_TP_CD, param.glblCmpyCd.getValue()).split(",");
            if (Arrays.asList(agwSceOrdTpCdList).contains(rwsHdrT.sceOrdTpCd.getValue()) 
                    && WH_OWNR.AGW.equals(updateRs.getString(WH_OWNR_CD))) {
                ZYPEZDItemValueSetter.setValue(rwsHdrT.pickUpRqstFlg, ZYPConstant.FLG_ON_Y);
            }
            // END   2023/04/07 [QC#61001,ADD]
            ZYPEZDItemValueSetter.setValue(rwsHdrT.svcConfigMstrPk, getSvcConfigMstrPk(rwsHdrT.sceOrdTpCd.getValue(), svcConfigMstrPk));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.shipFromSoNum, shipFromSoNum);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.srcOrdNum, getSrcOrdNumForPoRcv(rwsHdrT.sceOrdTpCd.getValue(), updateRs));
            ZYPEZDItemValueSetter.setValue(rwsHdrT.serAllocTrxHdrNum, getSerAllocTrxHdrNumForPoRcv(rwsHdrT.sceOrdTpCd.getValue(), updateRs));

            // 2013/05/13 QC1092 Y.Igarashi Add Start
            String imptInvNum = updateRs.getString(RWS_REF_NUM);
            // 2018/03/07 QC#20399 START
            String imptInvBolNum = updateRs.getString(PRO_NUM);
            if (ZYPCommonFunc.hasValue(imptInvBolNum) && imptInvBolNum.length() > rwsHdrT.getAttr("imptInvBolNum").getDigit()) {
                imptInvBolNum = imptInvBolNum.substring(0, rwsHdrT.getAttr("imptInvBolNum").getDigit());
            } 
            // 2013/05/13 QC1092 Y.Igarashi Add End
            // 2018/03/07 QC#20399 END

            // 2013/07/29 QC913 A.Kohinata Upd Start
            if (LOC_TP.VENDOR.equals(updateRs.getString(PO_RCV_FROM_LOC_TP_CD))) {

                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(FROM_LOC_NM_01_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(FROM_LOC_NM_02_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(FROM_LOC_CTY_ADDR_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(FROM_LOC_ST_CD_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(FROM_LOC_CTRY_CD_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(FROM_LOC_TEL_NUM_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FROM_LOC_ADDR_01_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(FROM_LOC_ADDR_02_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(FROM_LOC_ADDR_03_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FROM_LOC_ADDR_04_VND));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(FROM_LOC_POST_CD_VND));

            } else if (LOC_TP.WAREHOUSE.equals(updateRs.getString(PO_RCV_FROM_LOC_TP_CD)) || LOC_TP.TECHNICIAN.equals(updateRs.getString(PO_RCV_FROM_LOC_TP_CD))) {

                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(FROM_LOC_NM_01_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(FROM_LOC_NM_02_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(FROM_LOC_CTY_ADDR_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(FROM_LOC_ST_CD_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(FROM_LOC_CTRY_CD_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(FROM_LOC_TEL_NUM_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FROM_LOC_ADDR_01_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(FROM_LOC_ADDR_02_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(FROM_LOC_ADDR_03_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FROM_LOC_ADDR_04_RW));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(FROM_LOC_POST_CD_RW));
            }
            // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Mod End
            // 2013/07/29 QC913 A.Kohinata Upd End

            /** Merge DS **/
            if (SCE_ORD_TP.BUY_BACK.equals(updateRs.getString(SCE_ORD_TP_CD))) {

                HashMap<String, Object> paramRtlWh = new HashMap<String, Object>();
                paramRtlWh.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                paramRtlWh.put(BIND_PO_RCV_NUM, param.poRcvNum.getValue());
                Map<String, Object> rtlWhMap = (Map<String, Object>) ssmBatchClient.queryObject(GET_RTL_WH_BUYBACK, paramRtlWh);

                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, (String) rtlWhMap.get(RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, (String) rtlWhMap.get(ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, (String) rtlWhMap.get(CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, (String) rtlWhMap.get(ST_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, (String) rtlWhMap.get(CTRY_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, (String) rtlWhMap.get(TEL_NUM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, (String) rtlWhMap.get(FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, (String) rtlWhMap.get(SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, (String) rtlWhMap.get(THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, (String) rtlWhMap.get(FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, (String) rtlWhMap.get(POST_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTpCd, (String) rtlWhMap.get(LOC_TP_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, (String) rtlWhMap.get(SRC_RTL_WH_CD));
            }

            if (LOC_TP.VENDOR.equals(rwsHdrT.fromLocTpCd.getValue())) {

                HashMap<String, Object> paramAcctNm = new HashMap<String, Object>();
                paramAcctNm.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                paramAcctNm.put(BIND_VND_CD, rwsHdrT.fromLocCd.getValue());
                String acctNm = (String) ssmBatchClient.queryObject(GET_DPLY_VND_NM, paramAcctNm);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.shipFromAcctNm, acctNm);

            } else if (LOC_TP.WAREHOUSE.equals(rwsHdrT.fromLocTpCd.getValue()) || LOC_TP.TECHNICIAN.equals(rwsHdrT.fromLocTpCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(rwsHdrT.shipFromAcctNm, rwsHdrT.fromLocNm_01.getValue());
            }

            // Line#
            // 2013/05/13 QC1092 Y.Igarashi Add Start
            List<RWS_DTLTMsg> rwsDtlList = new ArrayList<RWS_DTLTMsg>();
            boolean dgDataSetFlg = false;

            int lineNum = 1;
            for (; cur;) {

                RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rwsDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsDtlLineNum, ZYPCommonFunc.leftPad(Integer.toString(lineNum), RWS_DTL_LINE_NUM_LENGTH, RWS_DTL_LINE_NUM_PUT_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.trxLineNum, updateRs.getString(PO_RCV_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.mdseCd, updateRs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.invtyStkStsCd, updateRs.getString(STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, updateRs.getBigDecimal(PO_RCV_QTY));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsPutAwayQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, updateRs.getString(PO_RCV_ETA_DT));
                rwsDtlT.outPackFromCseNum.clear();
                rwsDtlT.outPackToCseNum.clear();
                ZYPEZDItemValueSetter.setValue( rwsDtlT.rwsStsCd, RWS_STS.PRINTED);
                ZYPEZDItemValueSetter.setValue(rwsDtlT.poRcvTrxLineNum, updateRs.getString(PO_RCV_TRX_LINE_NUM));

                if((NLXSceConst.SCE_ORD_TP_CD_DG).equals(rwsHdrT.sceOrdTpCd.getValue())){

                    this.getInv_Num(rwsHdrT,rwsDtlT);

                    if (lineNum == 1) {

                        // 2018/03/07 QC#20399 MOD START
                        String proNum = updateRs.getString(PRO_NUM);

                        if (ZYPCommonFunc.hasValue(proNum) && proNum.length() > rwsHdrT.getAttr("imptInvBolNum").getDigit()) {
                            proNum = proNum.substring(0, rwsHdrT.getAttr("imptInvBolNum").getDigit());
                        }

                        ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvBolNum, proNum);
                        // 2018/03/07 QC#20399 MOD END
                    }

                    if (ZYPCommonFunc.hasValue(rwsDtlT.imptInvNum) || ZYPCommonFunc.hasValue(rwsDtlT.imptInvDoNum)) {

                        dgDataSetFlg = true;
                    }
                }

                /** Merge DS **/
                ZYPEZDItemValueSetter.setValue(rwsDtlT.rtlWhCd, updateRs.getString(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.rtlSwhCd, updateRs.getString(RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.invtyLocCd, updateRs.getString(INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlWhCd, updateRs.getString(SHIP_FROM_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlSwhCd, updateRs.getString(SHIP_FROM_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromInvtyLocCd, updateRs.getString(SHIP_FROM_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqNum, updateRs.getString(PRCH_REQ_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqLineNum, updateRs.getString(PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqLineSubNum, updateRs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.serNumTakeFlg, getSerNumTakeFlg(rwsHdrT.sceOrdTpCd.getValue(), updateRs.getString(SER_NUM_TAKE_FLG), updateRs.getString(RCV_SER_TAKE_FLG), updateRs.getString(SHPG_SER_TAKE_FLG)));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.serApvlReqFlg,updateRs.getString(SER_APVL_REQ_FLG));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.carrCd,updateRs.getString(INLND_CARR_CD));

                if(SCE_ORD_TP.BUY_BACK.equals(updateRs.getString(SCE_ORD_TP_CD))){

                    HashMap<String, Object> paramRtlSwh = new HashMap<String, Object>();
                    paramRtlSwh.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    paramRtlSwh.put(BIND_PO_RCV_NUM, updateRs.getString(PO_RCV_NUM));
                    paramRtlSwh.put(BIND_PO_RCV_LINE_NUM, updateRs.getString(PO_RCV_LINE_NUM));
                    Map<String, Object> rtlSwhMap = (Map<String, Object>) ssmBatchClient.queryObject(GET_RTL_WH_BUYBACK_DTL, paramRtlSwh);

                    ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlWhCd, (String)rtlSwhMap.get(SRC_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlSwhCd, (String)rtlSwhMap.get(SRC_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromInvtyLocCd, (String)rtlSwhMap.get(INVTY_LOC_CD));
                }
                rwsDtlT.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]

                rwsDtlList.add(rwsDtlT);


                // 2013/05/13 QC1092 Y.Igarashi Mod Start
                PO_RCV_DTLTMsg poRcvDtlT = new PO_RCV_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(poRcvDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvNum, param.poRcvNum.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvLineNum, updateRs.getString(PO_RCV_LINE_NUM));
                poRcvDtlT = (PO_RCV_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poRcvDtlT);

                if (poRcvDtlT == null) {

                    throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_PO_RCV_DTL,
                            NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM),
                            NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvNum, updateRs.getString(PO_RCV_LINE_NUM)) });
                }

                ZYPEZDItemValueSetter.setValue(poRcvDtlT.rwsStsCd, RWS_STS.PRINTED);

                S21ApiTBLAccessor.update(poRcvDtlT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvDtlT.getReturnCode())) {

                    throw new S21AbendException(NLAM1134E, new String[] {NLXSceConst.TBL_PO_RCV_DTL,
                            NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM),
                            NLXCMsgHelper.toListedString(poRcvDtlT.glblCmpyCd, poRcvDtlT.poRcvNum, updateRs.getString(PO_RCV_LINE_NUM)) });
                }

                if (NLXSceConst.SCE_ORD_TP_CD_DG.equals(updateRs.getString(SCE_ORD_TP_CD))) {

                    callInventoryUpdateAPI(msgMap, updateRs, rwsDtlT, onBatchType);
                }

                lineNum++;

                cur = updateRs.next();
            }


            // 2013/05/13 QC1092 Y.Igarashi Add Start
            for (int i = 0; i < rwsDtlList.size(); i++) {

                RWS_DTLTMsg rwsDtlT = rwsDtlList.get(i);

                if (!dgDataSetFlg) {

                    ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvNum, imptInvNum);
                }

                S21ApiTBLAccessor.insert(rwsDtlT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {

                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                            NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd, rwsDtlT.rwsNum, rwsDtlT.rwsDtlLineNum) });
                }
            }


            if (!dgDataSetFlg) {

                ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvNum, imptInvNum);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.imptInvBolNum, imptInvBolNum);
            }

            // 2013/05/13 QC1092 Y.Igarashi Add End
            PO_RCV_MSGTMsg searchPoRcvMsgT = new PO_RCV_MSGTMsg();
            searchPoRcvMsgT.setSQLID(SQLID_PO_RCV_MSG);
            searchPoRcvMsgT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            searchPoRcvMsgT.setConditionValue("poRcvNum01", param.poRcvNum.getValue());

            PO_RCV_MSGTMsgArray poTArr = (PO_RCV_MSGTMsgArray) S21ApiTBLAccessor.findByCondition(searchPoRcvMsgT);

            for (int i = 0; i < poTArr.length(); i++) {

                RWS_MSGTMsg rwsMsgT = new RWS_MSGTMsg();
                PO_RCV_MSGTMsg resultPoRcvMsgT = (PO_RCV_MSGTMsg) poTArr.get(i);
                ZYPEZDItemValueSetter.setValue(rwsMsgT.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsMsgT.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(rwsMsgT.rwsMsgSqNum, resultPoRcvMsgT.poRcvMsgSqNum.getValue());
                ZYPEZDItemValueSetter.setValue(rwsMsgT.rwsMsgTxt, resultPoRcvMsgT.poRcvMsgTxt.getValue());

                S21ApiTBLAccessor.insert(rwsMsgT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsMsgT.getReturnCode())) {

                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_MSG, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_MSG_SQ_NUM), NLXCMsgHelper.toListedString(rwsMsgT.glblCmpyCd, rwsMsgT.rwsNum, rwsMsgT.rwsMsgSqNum) });
                }
            }

            S21ApiTBLAccessor.insert(rwsHdrT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {

                throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
            }

            param.RWSNumList.no(0).rwsNum.setValue(rwsNum);
            param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);

            PO_RCV_HDRTMsg poRcvHdrT = new PO_RCV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(poRcvHdrT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvNum, param.poRcvNum.getValue());
            poRcvHdrT = (PO_RCV_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poRcvHdrT);

            if (poRcvHdrT == null) {

                throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_PO_RCV_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvNum) });
            }

            ZYPEZDItemValueSetter.setValue(poRcvHdrT.rwsStsCd, RWS_STS.PRINTED);
            S21ApiTBLAccessor.update(poRcvHdrT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvHdrT.getReturnCode())) {

                throw new S21AbendException(NLAM1134E, new String[] {NLXSceConst.TBL_PO_RCV_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM), NLXCMsgHelper.toListedString(poRcvHdrT.glblCmpyCd, poRcvHdrT.poRcvNum) });
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
        }
    }

    // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Add Start
    /**
     * getSerNumTakeFlg
     * @param sceOrdTpCd String
     * @param serNumTakeFlg String
     * @param rcvSerTakeFlg String
     * @param shpgSerTakeFlg String
     * @return String
     */
    private String getSerNumTakeFlg(String sceOrdTpCd, String serNumTakeFlg, String rcvSerTakeFlg, String shpgSerTakeFlg) {

        if (!ZYPConstant.FLG_ON_Y.equals(serNumTakeFlg)) {

            return ZYPConstant.FLG_OFF_N;

        } else {

            if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_KC.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {

                if (ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {

                    return ZYPConstant.FLG_ON_Y;
                }

            } else {

                if (ZYPConstant.FLG_ON_Y.equals(rcvSerTakeFlg)) {

                    return ZYPConstant.FLG_ON_Y;
                }
            }
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * getTrxSrcTpCd
     * @param sceOrdTpCd String
     * @param trxSrcTpCd String
     * @return String
     */
    private String getTrxSrcTpCd(String sceOrdTpCd, String trxSrcTpCd) {

        String rtnTrxSrcTpCd = null;

        if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_DG.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_DN.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.PURCHASE_ORDER;

        } else if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.BUY_BACK;

        } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.WAREHOUSE_TRANSFER;

        } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.TECH_REQUEST;

        } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.INTERNAL_KITTING;

        } else if (NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.INTERNAL_UN_KITTING;

        } else if (NLXSceConst.SCE_ORD_TP_CD_KC.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = trxSrcTpCd;

        } else if (NLXSceConst.SCE_ORD_TP_CD_RF.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_RC.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.REFURBISH;
        } else if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.REMANUFACTURING;
        }  else if (NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

            rtnTrxSrcTpCd = TRX_SRC_TP.REMAN_PARTS_USAGE;
        } 

        return rtnTrxSrcTpCd;
    }

    /**
     * getSvcConfigMstrPk
     * @param sceOrdTpCd String
     * @param svcConfigMstrPk BigDecimal
     * @return BigDecimal
     */
    private BigDecimal getSvcConfigMstrPk(String sceOrdTpCd, BigDecimal svcConfigMstrPk) {

        BigDecimal rtnSvcConfigMstrPk = null;

        if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {

            rtnSvcConfigMstrPk = svcConfigMstrPk;
        }

        return rtnSvcConfigMstrPk;
    }

    /**
     * getSerNumTakeFlgForSo
     * @param serNumTakeFlg String
     * @param shpgSerTakeFlg String
     * @return String
     */
    private String getSerNumTakeFlgForSo(String serNumTakeFlg, String shpgSerTakeFlg) {

        if (!ZYPConstant.FLG_ON_Y.equals(serNumTakeFlg)) {

            return ZYPConstant.FLG_OFF_N;

        } else {

            if (ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {

                return ZYPConstant.FLG_ON_Y;
            }
        }

        return ZYPConstant.FLG_OFF_N;
    }
    // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Add End

    /**
     * Get Inv_Num
     * @param rwsHdrT RWS_HDRTMsg
     * @param rwsDtlT RWS_DTLTMsg
     * @return RWS_DTLTMsg
     */
    private Boolean getInv_Num(final RWS_HDRTMsg rwsHdrT,RWS_DTLTMsg rwsDtlT) {

        // SQL bind parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", rwsDtlT.glblCmpyCd.getValue());
        queryParam.put("rwsRefNum", rwsHdrT.rwsRefNum.getValue());
        queryParam.put("whCd", rwsHdrT.whCd.getValue());
        queryParam.put("mdseCd", rwsDtlT.mdseCd.getValue());
        queryParam.put("rwsQty", rwsDtlT.rwsQty.getValue().toString());

        this.ssmBatchClient.queryObjectList("getInv_Num", queryParam,new getInv_Num(rwsDtlT));

        return true;
    }

    /**
     * Get Inv_Num
     */
    private static class getInv_Num extends S21SsmBooleanResultSetHandlerSupport {

        /** RWS Detail */
        private RWS_DTLTMsg rwsDtlT;

        public getInv_Num(RWS_DTLTMsg rwsDtlT) {

            this.rwsDtlT = rwsDtlT;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            if (rs.next()) {
                ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvNum, rs.getString("INV_NUM"));
                ZYPEZDItemValueSetter.setValue(rwsDtlT.imptInvDoNum, rs.getString("CUST_PO_NUM"));                
            }

            return true;
        }

    }

    /**
     * <pre>
     * doSo
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    protected void doSo(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();
        List<String> rmvConfigFlgList = new ArrayList<String>();

        SHPG_ORDTMsg inMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.soNum, param.soNum);
        SHPG_ORDTMsg outMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {

            msgMap.addXxMsgId(NLZM1004E);
            NLXC025001.outputLog(1, NLAM1132E, new String[] {inMsg.getTableName(), NLXCMsgHelper.toListedString(param.glblCmpyCd, param.soNum, INBD_OTBD.INBOUND) }, onBatchType, this);
            return;
        }

        if (SCE_ORD_TP.CONFIG_CHANGE.equals(outMsg.sceOrdTpCd.getValue())) {

            rmvConfigFlgList.add(ZYPConstant.FLG_OFF_N);
            rmvConfigFlgList.add(ZYPConstant.FLG_ON_Y);

        } else {
            rmvConfigFlgList.add("");
        }

        rmvConfigFlgList:
        for (int i = 0; i < rmvConfigFlgList.size(); i++) {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE_MAX);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            paramMap.put(BIND_SO_NUM, param.soNum.getValue());
            paramMap.put(BIND_SHPG_QTY, RWS_QTY_ZERO);
            paramMap.put(BIND_INBD_OTBD_CD, INBD_OTBD.INBOUND);

            if (ZYPCommonFunc.hasValue(rmvConfigFlgList.get(i))) {

                paramMap.put(BIND_RMV_CONFIG_FLG, rmvConfigFlgList.get(i));
            }

            String statementId = GET_SO_INFO;

            PreparedStatement updateState = null;
            ResultSet updateRs = null;

            try {

                updateState = this.ssmClient.createPreparedStatement(statementId, paramMap, execParam);
                updateRs = updateState.executeQuery();

                boolean cur = updateRs.next();

                if (!cur) {
                    continue rmvConfigFlgList;
                }

                if (!ZYPCommonFunc.hasValue(updateRs.getString(TRX_CD)) || !ZYPCommonFunc.hasValue(updateRs.getString(TRX_RSN_CD))) {

                    msgMap.addXxMsgId(NLZM2016E);
                    NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD),
                            NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(SCE_ORD_TP_CD), INBD_OTBD.INBOUND) }, onBatchType, this);
                    return;
                }

                if (!ZYPCommonFunc.hasValue(updateRs.getString(WH_CD))) {

                    msgMap.addXxMsgId(NLZM2017E);
                    NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_WH, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WH_CD), NLXCMsgHelper.toListedString(param.glblCmpyCd, updateRs.getString(WH_CD)) }, onBatchType, this);
                    return;
                }

                checkShpgQtyWhenItemChange:
                if (NLXSceConst.SCE_ORD_TP_CD_IC.equals(updateRs.getString(SCE_ORD_TP_CD))) {

                    while (cur) {

                        if (updateRs.getInt(SHPG_QTY) < RWS_QTY_ZERO) {

                            cur = updateRs.first();
                            break checkShpgQtyWhenItemChange;
                        }

                        cur = updateRs.next();
                    }

                    msgMap.addXxMsgId(NLZM1004E);
                    NLXC025001.outputLog(1, NLAM1132E, new String[] {statementId, NLXCMsgHelper.toListedString(param.glblCmpyCd, param.soNum, INBD_OTBD.INBOUND) }, onBatchType, this);
                    return;
                }

                String glblCmpyCd = param.glblCmpyCd.getValue();
                String rwsNum = preChkToZYPNumbering(glblCmpyCd, onBatchType);

                RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rwsHdrT.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.inbdSrcTpCd, INBD_SRC_TP.SO);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.sceOrdTpCd, updateRs.getString(SCE_ORD_TP_CD));

                if (i > 0) {

                    ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, updateRs.getString(SO_NUM).concat("-").concat(ZYPCommonFunc.leftPad(String.valueOf(i), 2, "0")));

                } else {

                    ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, updateRs.getString(SO_NUM));
                }

                ZYPEZDItemValueSetter.setValue(rwsHdrT.trxOrdNum, updateRs.getString(SO_NUM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, updateRs.getString(SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTpCd, updateRs.getString(LOC_TP_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, updateRs.getString(WH_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_01, updateRs.getString(RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocNm_02, updateRs.getString(ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_01, updateRs.getString(FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_02, updateRs.getString(SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_03, updateRs.getString(THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocAddr_04, updateRs.getString(FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtyAddr, updateRs.getString(CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocStCd, updateRs.getString(ST_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocPostCd, updateRs.getString(POST_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCtryCd, updateRs.getString(CTRY_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTelNum, updateRs.getString(TEL_NUM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.trxCd, updateRs.getString(TRX_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.trxRsnCd, updateRs.getString(TRX_RSN_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, updateRs.getString(PSD_DT));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.ltstWhInEtaDt, updateRs.getString(PSD_DT));
                rwsHdrT.totRcvWt.clear();
                ZYPEZDItemValueSetter.setValue(rwsHdrT.drctShipTpCd, DRCT_SHIP_TP.N_OR_A);
                //QC#26863 MOD START
                //09/11/2017 CITS S.Endo MOD Sol#069(QC#18270) START
                //ZYPEZDItemValueSetter.setValue(rwsHdrT.wmsDropStsCd, WMS_DROP_STS_CD_DRP);
                if (SCE_ORD_TP.SUB_WH_CHANGE.equals(outMsg.sceOrdTpCd.getValue()) ||
                        SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(outMsg.sceOrdTpCd.getValue()) || 
                        //SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(outMsg.sceOrdTpCd.getValue()) ||
                        SCE_ORD_TP.ITEM_CHANGE.equals(outMsg.sceOrdTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(rwsHdrT.wmsDropStsCd, WMS_DROP_STS_CD_NOT_DRP);
                } else {
                    ZYPEZDItemValueSetter.setValue(rwsHdrT.wmsDropStsCd, WMS_DROP_STS_CD_DRP);
                }
                //09/11/2017 CITS S.Endo MOD Sol#069(QC#18270) END
              //QC#26863 MOD END
                ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, updateRs.getString(DNLD_TM_TS));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsPrtFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsStsCd, RWS_STS.PRINTED);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.schdEtaChkFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.finalEtaChkFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.sysSrcCd, param.sysSrcCd.getValue());


                /** Merge DS **/
                ZYPEZDItemValueSetter.setValue(rwsHdrT.trxSrcTpCd, updateRs.getString(TRX_SRC_TP_CD));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.pickUpRqstFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(rwsHdrT.shipFromAcctNm, rwsHdrT.fromLocNm_01.getValue());
                ZYPEZDItemValueSetter.setValue(rwsHdrT.srcOrdNum, updateRs.getString(TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(rwsHdrT.serAllocTrxHdrNum, updateRs.getString(TRX_HDR_NUM));

                // QC#23962, QC#24066
                if (!ZYPConstant.FLG_ON_Y.equals(rmvConfigFlgList.get(i))) {
                    ZYPEZDItemValueSetter.setValue(rwsHdrT.svcConfigMstrPk, updateRs.getBigDecimal(SVC_CONFIG_MSTR_PK));
                    if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(outMsg.sceOrdTpCd.getValue())
                            || SCE_ORD_TP.SUB_WH_CHANGE.equals(outMsg.sceOrdTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(rwsHdrT.svcConfigMstrPk, updateRs.getBigDecimal("PICK_SVC_CONFIG_MSTR_PK"));
                    }
                }

                S21ApiTBLAccessor.insert(rwsHdrT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {

                    throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM)
                            , NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd, rwsHdrT.rwsNum) });
                }


                // SO Line#
                int lineNum = 1;

                for (; cur;) {

                    if (!(NLXSceConst.SCE_ORD_TP_CD_IC.equals(updateRs.getString(SCE_ORD_TP_CD)) && updateRs.getInt(SHPG_QTY) > 0)) {

                        RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();

                        ZYPEZDItemValueSetter.setValue(rwsDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsNum, rwsNum);
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsDtlLineNum, ZYPCommonFunc.leftPad(Integer.toString(lineNum), RWS_DTL_LINE_NUM_LENGTH, RWS_DTL_LINE_NUM_PUT_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.trxLineNum, updateRs.getString(SO_SLP_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.mdseCd, updateRs.getString(MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, updateRs.getBigDecimal(SHPG_QTY).abs());
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsPutAwayQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, updateRs.getString(PSD_DT));
                        rwsDtlT.outPackFromCseNum.clear();
                        rwsDtlT.outPackToCseNum.clear();
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsStsCd, RWS_STS.PRINTED);


                        /** Merge DS **/
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.rtlWhCd, updateRs.getString(SHIP_TO_RTL_WH_CD));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.rtlSwhCd, updateRs.getString(SHIP_TO_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.invtyLocCd, updateRs.getString(TO_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlWhCd, updateRs.getString(SHIP_FROM_RTL_WH_CD));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlSwhCd, updateRs.getString(SHIP_FROM_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromInvtyLocCd, updateRs.getString(SHIP_FROM_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqNum, updateRs.getString(PRCH_REQ_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqLineNum, updateRs.getString(PRCH_REQ_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqLineSubNum, updateRs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.serNumTakeFlg, getSerNumTakeFlgForSo(updateRs.getString(SER_NUM_TAKE_FLG), updateRs.getString(SHPG_SER_TAKE_FLG)));
                        ZYPEZDItemValueSetter.setValue(rwsDtlT.serApvlReqFlg, ZYPConstant.FLG_OFF_N);


                        if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(updateRs.getString(SCE_ORD_TP_CD)) || NLXSceConst.SCE_ORD_TP_CD_DC.equals(updateRs.getString(SCE_ORD_TP_CD))) {

                            ZYPEZDItemValueSetter.setValue(rwsDtlT.invtyStkStsCd, updateRs.getString(TO_STK_STS_CD));

                        } else {

                            ZYPEZDItemValueSetter.setValue(rwsDtlT.invtyStkStsCd, updateRs.getString(FROM_STK_STS_CD));
                        }
                        rwsDtlT.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]

                        S21ApiTBLAccessor.insert(rwsDtlT);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {

                            throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_RWS_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                                    NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd, rwsDtlT.rwsNum, rwsDtlT.rwsDtlLineNum) });
                        }

                        lineNum++;
                    }

                    cur = updateRs.next();
                }

                param.RWSNumList.no(param.RWSNumList.getValidCount()).rwsNum.setValue(rwsNum);
                param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);

                rwsHdrT.clear();

            } catch (SQLException e) {

                sqlExceptionHandler(e);

            } finally {

                S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
            }
        }

        if (param.RWSNumList.getValidCount() == 0) {

            msgMap.addXxMsgId(NLZM1004E);
            NLXC025001.outputLog(1, NLAM1132E, new String[] {GET_SO_INFO, NLXCMsgHelper.toListedString(param.glblCmpyCd, param.soNum, INBD_OTBD.INBOUND) }, onBatchType, this);
        }
    }

    /**
     * <pre>
     * preChkToZYPNumbering
     * </pre>
     * @param glblCmpyCd String
     * @param onBatchType ONBATCH_TYPE
     * @return String
     */
    private String preChkToZYPNumbering(final String glblCmpyCd, final ONBATCH_TYPE onBatchType) {

        // RWS#
        String rwsNum = null;

        if (S21ApiInterface.ONBATCH_TYPE.ONLINE.equals(onBatchType)) {

            String seqKey = NLXSceConst.RWS_ONLINE_KEY;
            rwsNum = ZYPNumbering.getUniqueID(glblCmpyCd, seqKey);

        } else if (S21ApiInterface.ONBATCH_TYPE.BATCH.equals(onBatchType)) {

            String seqKey = NLXSceConst.RWS_BATCH_KEY;
            rwsNum = ZYPNumbering.getUniqueID(glblCmpyCd, seqKey);
        }

        return rwsNum;
    }

    /**
     * <pre>
     * callInventoryUpdateAPI
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param rs ResultSet
     * @param rwsDtlT RWS_DTLTMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException
     */
    private void callInventoryUpdateAPI(final S21ApiMessageMap msgMap, ResultSet rs, RWS_DTLTMsg rwsDtlT, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();
        NLZC002001PMsg inPMsg = getPmsgDg(param, rs, rwsDtlT);

        NLZC002001 api = new NLZC002001();
        api.execute(inPMsg, onBatchType);

        checkApiErr(msgMap, inPMsg);
    }

    /**
     * <pre>
     * getPmsgDg
     * </pre>
     * @param param NLZC200001PMsg
     * @param rs ResultSet
     * @param rwsDtlT RWS_DTLTMsg
     * @return NLZC002001PMsg API parameter
     * @throws SQLException
     */
    private NLZC002001PMsg getPmsgDg(final NLZC200001PMsg param, ResultSet rs, RWS_DTLTMsg rwsDtlT) throws SQLException {

        // Movement Stock in
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.INBOUND_IN_TRANSIT_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_DOM);
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, rs.getString(MDSE_CD));
        // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Mod Start
        //ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, rs.getString(WH_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Mod End
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, salesDt);
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, param.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, rwsDtlT.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, rwsDtlT.rwsDtlLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxSlpNum, rs.getString(DOM_INV_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.vndCd, rs.getString(PO_RCV_FROM_LOC_CD));
        // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdNum, rs.getString(PO_RCV_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdDtlLineNum, rs.getString(PO_RCV_TRX_LINE_NUM));
        // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Add End
        ZYPEZDItemValueSetter.setValue(inPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        return inPMsg;
    }

    /**
     * <pre>
     * checkApiErr
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param pMsg NLZC002001PMsg
     */
    private void checkApiErr(S21ApiMessageMap msgMap, NLZC002001PMsg pMsg) {

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (int i = 0; i < msgList.size(); i++) {

                String messageId = msgList.get(i);

                if (messageId.endsWith("E")) {

                    throw new S21AbendException(msgList.get(0));

                } else if (messageId.endsWith("W")) {

                    msgMap.addXxMsgId(messageId);
                }
            }
        }
    }

    /**
     * <pre>
     * getStdCcyCd
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @return String
     */
    private String getStdCcyCd(S21ApiMessageMap msgMap) {

        String value = null;

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();
        GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY, param.glblCmpyCd.getValue(), param.glblCmpyCd.getValue());

        if (glblCmpyT != null) {

            value = glblCmpyT.stdCcyCd.getValue();
        }

        return value;
    }

    /**
     * getSrcOrdNumForPoRcv
     * @param sceOrdTpCd
     * @param updateRs
     * @return
     * @throws SQLException
     */
    private String getSrcOrdNumForPoRcv(String sceOrdTpCd, ResultSet updateRs) throws SQLException {
        String srcOrdNum = null;

        if (SCE_ORD_TP.DOMESTIC_PO_EXISTS.equals(sceOrdTpCd) //
                || SCE_ORD_TP.DOMESTIC_CANON_GROUP.equals(sceOrdTpCd) //
                || SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) //
                || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REMAN_PARTS_USAGE.equals(sceOrdTpCd)) {

            srcOrdNum = updateRs.getString(PO_RCV_TRX_HDR_NUM);

        } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {
            srcOrdNum = updateRs.getString(PRCH_REQ_NUM);
        } else {
            // SCE_ORD_TP = (DT, KT, KU, KC, RF, RC)
            srcOrdNum = updateRs.getString(TRX_HDR_NUM);
        }

        return srcOrdNum;
    }

    /**
     * getSerAllocTrxHdrNumForPoRcv
     * @param sceOrdTpCd
     * @param updateRs
     * @return
     * @throws SQLException
     */
    private String getSerAllocTrxHdrNumForPoRcv(String sceOrdTpCd, ResultSet updateRs) throws SQLException {
        String serAllocTrxHdrNum = null;

        if (SCE_ORD_TP.DOMESTIC_PO_EXISTS.equals(sceOrdTpCd) //
                || SCE_ORD_TP.DOMESTIC_CANON_GROUP.equals(sceOrdTpCd) //
                || SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) //
                || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REMAN_PARTS_USAGE.equals(sceOrdTpCd)) {

            serAllocTrxHdrNum = updateRs.getString(PO_RCV_TRX_HDR_NUM);

        } else {
            // SCE_ORD_TP = (TR, DT, KT, KU, KC, RF, RC)
            serAllocTrxHdrNum = updateRs.getString(TRX_HDR_NUM);
        }

        return serAllocTrxHdrNum;
    }

    // START 2022/10/24 T.NEMA [QC#60022, DEL]
    // START 2022/06/13 [QC#60022,ADD]
//    /**
//     * isCarrSti
//     * @param glblCmpyCd String
//     * @param carrCd String
//     * @param carrTpCd String
//     * @return true: Carrier STI
//     */
//    private boolean isCarrSti(String glblCmpyCd, String carrCd, String carrTpCd) {
//
//        if (!ZYPCommonFunc.hasValue(carrCd)) {
//            return false;
//        }
//
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("carrCd", carrCd);
//        queryParam.put("carrTpCd", carrTpCd);
//
//        Integer cntCarrSti = (Integer) ssmBatchClient.queryObject("cntCarrSti", queryParam);
//
//        if (cntCarrSti > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    // END   2022/06/13 [QC#60022,ADD]
    // END   2022/10/24 T.NEMA [QC#60022, DEL]

}
