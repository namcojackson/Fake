/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC209001;

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
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_HDRTMsg;
import business.db.RWS_CPLTTMsg;
import business.db.RWS_CPLT_WRKTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_PUT_AWAYTMsg;
import business.db.RWS_PUT_AWAY_SERTMsg;
import business.db.RWS_PUT_AWAY_SER_WRKTMsg;
import business.db.RWS_PUT_AWAY_SER_WRKTMsgArray;
import business.db.RWS_SCHD_DTL_TRKTMsg;
import business.db.SHIP_SER_NUMTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_CONFTMsg;
import business.db.SHPG_ORD_CONF_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.WH_SCHDTMsg;
import business.db.WRK_ORDTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC209001PMsg;
import business.parts.NPZC002001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC126001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC153001PMsg;
import business.parts.NWZC153002PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NPZ.NPZC002001.NPZC002001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC126001.NPZC126001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC153001.NWZC153001;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NLX.NLXC004001.NLXRWSClose;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NLX.NLXC025001.NLXC025001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VIS_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

/**
 * <pre>
 * Update RWS Completion API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/29/2015   CITS            Hisashi         Create
 * 05/06/2016   CSAI            Y.Imazu         Update          QC#7742
 * 05/10/2016   CSAI            Y.Imazu         Update          QC#8110
 * 06/01/2016   CSAI            Y.Imazu         Update          QC#8001
 * 06/15/2016   CSAI            Y.Imazu         Update          QC#10090
 * 06/21/2016   CSAI            Y.Imazu         Update          QC#10428
 * 06/30/2016   CSAI            K.Lee           Update          Configuration Change
 * 07/26/2016   CSAI            Y.Imazu         Update          QC#9103
 * 07/28/2016   CSAI            Y.Imazu         Update          QC#12545
 * 01/24/2017   CITS            K.Ogino         Update          QC#16556
 * 03/03/2017   CITS            T.Hakodate      Update          QC#13811
 * 05/18/2017   CITS            S.Endo          Update          RS#7266
 * 07/04/2017   CITS            T.Kikuhara      Update          QC#19381
 * 10/03/2017   CITS            T.Kikuhara      Update          QC#21574
 * 10/17/2017   CITS            K.Ogino         Update          QC#21774
 * 10/18/2017   CITS            T.Hakodate      UPDATE          QC#21670
 * 11/08/2017   CITS            K.Ogino         Update          QC#22374
 * 11/10/2017   CITS            K.Ogino         Update          QC#22485
 * 11/27/2017   CITS            K.Ogino         Update          QC#22374
 * 12/01/2017   CITS            K.Ogino         Update          QC#22481
 * 12/04/2017   CITS            K.Ogino         Update          QC#22834
 * 12/06/2017   CITS            T.Wada          Update          QC#22820
 * 01/29/2018   CITS            T.Wada          Update          QC#23072
 * 03/13/2018   CITS            K.Ogino         Update          QC#18794
 * 06/20/2018   CITS            K.Ogino         Update          QC#26132
 * 06/28/2018   CITS            Y.Iwasaki       Update          QC#25847
 * 07/04/2018   CITS            T.Hakodate      UPDATE          QC#27092
 * 07/04/2018   CITS            T.Hakodate      Update          QC#26863
 * 08/10/2018   CITS            T.Hakodate      Update          QC#26585
 * 08/15/2018   CITS            T.Hakodate      Update          QC#27400
 * 08/25/2018   CITS            K.Ogino         Update          QC#25867
 * 11/20/2018   CITS            T.Tokutomi      Update          QC#29030
 * 02/15/2019   CITS            K.Ogino         Update          QC#29680
 * 12/02/2019   CITS            K.Ogino         Update          QC#54841
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 07/10/2020   CITS            K.Ogino         Update          QC#57220
 * 09/22/2023   Hitachi         T.Kuroda        Update          QC#61265
 * 14/03/2024   CITS            J.Cho           Update          QC#63527
 *</pre>
 */
public class NLZC209001 extends S21ApiCommonBase {

    /** The parameter value is invalid. Field Name:  [@], Field Value:  [@] */
    public static final String NLAM1131E = "NLAM1131E";

    /** A value is not set in the required parameter field. */
    public static final String NLZM1001E = "NLZM1001E";

    /** A value is not set in the required parameter field. Field Name:  [@] */
    public static final String NLAM1006E = "NLAM1006E";

    /** No target data exists. */
    public static final String NLZM1004E = "NLZM1004E";

    /** The corresponding data does not exist. Table Name : [@], Key Field Name:  [@], Key Value:  [@] */
    public static final String NLAM1001E = "NLAM1001E";

    /** There is no data corresponding to the RWS Header. */
    public static final String NLZM1011E = "NLZM1011E";

    /** It does not exist in GLBL_CMPY. */
    public static final String NLZM1035E = "NLZM1035E";

    /** Already stocked in. */
    public static final String NLZM1007E = "NLZM1007E";

    /** Stock-in has been canceled. */
    public static final String NLZM1013E = "NLZM1013E";

    /** RWS Status is incorrect. */
    public static final String NLZM1016E = "NLZM1016E";

    /** Order type does not match. */
    public static final String NLZM1008E = "NLZM1008E";

    /** WH_CD does not match. */
    public static final String NLZM1017E = "NLZM1017E";

    /** RWS_REF_NUM does not match. */
    public static final String NLZM1018E = "NLZM1018E";

    /** A duplicate error occurred while registering the Table. TABLE ID : [@], Field Name:  [@], Key Information: [@] */
    public static final String NLAM1133E = "NLAM1133E";

    /** The Table Update process failed.  The data does not exist. TBLE ID : [@], Field Name:  [@], Key Information: [@] */
    public static final String NLAM1134E = "NLAM1134E";

    /** The target data does not exist. SQL ID : [@], Bind Variable:  [@] */
    public static final String NLAM1132E = "NLAM1132E";

    /** Incorrect order type. */
    public static final String NLZM1036E = "NLZM1036E";

    /** An error occurred in RWS Completion.  TABLE:[@] GLBL_CMPY_CD:[@] WRK_TRX_ID:[@] SQ_ID:[@] */
    public static final String NLAM1208E = "NLAM1208E";

    /** SO_PROC_STS_CD : Shipped */
    private static final String SO_PROC_STS_SHIPPED = "S";

    /** Asterisk */
    private static final String FREE = "*";

    /** Online Batch Type */
    private static final String ONBATCH_TYPE = "ONBATCH_TYPE";

    /** DB Column : GLBL_CMPY_CD */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Column : WRK_TRX_ID */
    private static final String WRK_TRX_ID = "WRK_TRX_ID";

    /** DB Column : SQ_ID */
    private static final String SQ_ID = "SQ_ID";

    /** DB Column : RWS_NUM */
    private static final String RWS_NUM = "RWS_NUM";

    /** DB Column : SO_NUM */
    private static final String SO_NUM = "SO_NUM";

    /** DB Column : SO_SLP_NUM */
    private static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** DB Column : RWS_REF_NUM */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /** DB Column : TRX_ORD_NUM */
    private static final String TRX_ORD_NUM = "TRX_ORD_NUM";

    /** DB Column : SYS_SRC_CD */
    private static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** DB Column : FROM_LOC_CD */
    private static final String FROM_LOC_CD = "FROM_LOC_CD";

    /** DB Column : RWS_DTL_LINE_NUM */
    private static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** DB Column : IMPT_INV_NUM */
    private static final String IMPT_INV_NUM = "IMPT_INV_NUM";

    /** DB Column : INVTY_STK_STS_CD */
    private static final String INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /** DB Column : RWS_STQ_QTY */
    private static final String RWS_STK_QTY = "RWS_STK_QTY";

    /** DB Column : CMPY_INVTY_FLG */
    private static final String CMPY_INVTY_FLG = "CMPY_INVTY_FLG";

    /** DB Column : INVTY_ACCT_CD */
    private static final String INVTY_ACCT_CD = "INVTY_ACCT_CD";

    /** DB Column : RWS_QTY */
    private static final String RWS_QTY = "RWS_QTY";

    /** DB Column : RWS_PUT_AWAY_QTY */
    private static final String RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";

    /** DB Column : MDSE_CD */
    private static final String MDSE_CD = "MDSE_CD";

    /** DB Column : TRX_LINE_NUM */
    private static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    // QC#22834
    /** DB Column : SHPG_ORD_DTL.TRX_LINE_NUM */
    private static final String TRX_LINE_NUM_SOD = "TRX_LINE_NUM_SOD";

    /** DB Column : RWS_STS_CD */
    private static final String RWS_STS_CD = "RWS_STS_CD";

    /** DB Column : INVTY_ORD_NUM */
    private static final String INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** DB Column : INVTY_ORD_LINE_NUM */
    private static final String INVTY_ORD_LINE_NUM = "INVTY_ORD_LINE_NUM";

    /** DB Column : PO_ORD_NUM */
    private static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** DB Column : PO_ORD_DTL_LINE_NUM */
    private static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB Column : WRK_ORD_NUM */
    private static final String WRK_ORD_NUM = "WRK_ORD_NUM";

    /** DB Column : SELL_TO_CUST_CD */
    private static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Column : BILL_TO_CUST_CD */
    private static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Column : ORIG_CPO_ORD_NUM */
    private static final String ORIG_CPO_ORD_NUM = "ORIG_CPO_ORD_NUM";

    /** DB Column : ORIG_CPO_DTL_LINE_NUM */
    private static final String ORIG_CPO_DTL_LINE_NUM = "ORIG_CPO_DTL_LINE_NUM";

    /** DB Column : ORIG_CPO_DTL_LINE_SUB_NUM */
    private static final String ORIG_CPO_DTL_LINE_SUB_NUM = "ORIG_CPO_DTL_LINE_SUB_NUM";

    /** DB Column : PO_RCV_NUM */
    private static final String PO_RCV_NUM = "PO_RCV_NUM";

    /** DB Column : PO_RCV_LINE_NUM */
    private static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /** DB Column : PRO_NUM */
    private static final String PRO_NUM = "PRO_NUM";

    /** DB Column : BOL_NUM */
    private static final String BOL_NUM = "BOL_NUM";

    /** DB Column : VND_CD */
    private static final String VND_CD = "VND_CD";

    /** DB Column : FROM_STK_STS_CD */
    private static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** DB Column : DOM_INV_NUM */
    private static final String DOM_INV_NUM = "DOM_INV_NUM";

    /** DB Column : WH_SCHD_PK */
    private static final String WH_SCHD_PK = "WH_SCHD_PK";

    /** DB Column : INBD_VIS_EVENT_CD */
    private static final String INBD_VIS_EVENT_CD = "INBD_VIS_EVENT_CD";

    /** DB Column : SER_NUM */
    private static final String SER_NUM = "SER_NUM";

    /** DB Column : SHIP_FROM_INVTY_LOC_CD */
    private static final String SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";

    /** DB Column : INVTY_LOC_CD */
    private static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Column : PRCH_REQ_NUM */
    private static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** DB Column : PRCH_REQ_LINE_NUM */
    private static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** DB Column : PRCH_REQ_LINE_SUB_NUM */
    private static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** DB Column : SHIP_QTY */
    private static final String SHIP_QTY = "SHIP_QTY";

    /** DB Column : TO_INVTY_LOC_CD */
    private static final String TO_INVTY_LOC_CD = "TO_INVTY_LOC_CD";

    /** DB Column : WRK_ORD_LINE_NUM */
    private static final String WRK_ORD_LINE_NUM = "WRK_ORD_LINE_NUM";

    /** DB Column : SHIP_MDSE_CD */
    private static final String SHIP_MDSE_CD = "SHIP_MDSE_CD";

    /** DB Column : INVTY_TRX_SLP_NUM */
    private static final String INVTY_TRX_SLP_NUM = "INVTY_TRX_SLP_NUM";

    /** DB Column : CPO_ORD_NUM */
    private static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB Column : CPO_DTL_LINE_NUM */
    private static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** DB Column : CPO_DTL_LINE_SUB_NUM */
    private static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** DB Column : LOC_TP_CD */
    private static final String LOC_TP_CD = "LOC_TP_CD";

    /** DB Column : TRX_CD */
    private static final String TRX_CD = "TRX_CD";

    /** DB Column : TRX_RSN_CD */
    private static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** DB Column : TRX_LINE_SUB_NUM */
    private static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** DB Column : RTRN_QTY */
    private static final String RTRN_QTY = "RTRN_QTY";

    /** DB Column : ORD_LINE_OPEN_FLG */
    private static final String ORD_LINE_OPEN_FLG = "ORD_LINE_OPEN_FLG";

    /** DB Column : ORD_LINE_STS_CD */
    private static final String ORD_LINE_STS_CD = "ORD_LINE_STS_CD";

    /** DB Column : DS_WRK_ORD_TP_CD */
    private static final String DS_WRK_ORD_TP_CD = "DS_WRK_ORD_TP_CD";

    /** Table Column : RWS_CPLT_WRK */
    private static final String RWS_CPLT_WRK = "RWS_CPLT_WRK";

    /** Table Column : RWS_HDR */
    private static final String RWS_HDR = "RWS_HDR";

    /** Table Column : SHPG_ORD */
    private static final String SHPG_ORD = "SHPG_ORD";

    /** Table Column : CPO */
    private static final String CPO = "CPO";

    /** Table Column : CPO_DTL */
    private static final String CPO_DTL = "CPO_DTL";

    /** Table Column : PO_RCV_DTL */
    private static final String PO_RCV_DTL = "PO_RCV_DTL";

    /** Table Column : PO_RCV_HDR */
    private static final String PO_RCV_HDR = "PO_RCV_HDR";

    /** Table Column : GLBL_CMPY */
    private static final String GLBL_CMPY = "GLBL_CMPY";

    /** WH_SCHD_REF_KEY_NUM_SQ */
    private static final String WH_SCHD_REF_KEY_NUM_SQ = "WH_SCHD_REF_KEY_NUM_SQ";

    /** WH_SCHD_REF_KEY_NUM_SQ_02 */
    private static final String WH_SCHD_REF_KEY_NUM_SQ_02 = "WH_SCHD_REF_KEY_NUM_SQ_02";

    /** SHPG_MODE_CD : Shipped */
    private static final String SHPG_MODE_SHIPPED = "12";

    /** SQLID : SHPG_ORD_DTL */
    private static final String SQLID_SHPG_ORD_DTL = "001";

    /** SQLID : SQLID_RWS_DTL */
    private static final String SQLID_RWS_DTL = "001";

    /** Date Length */
    private static final int DATE_END_DIGIT = 8;

    /** RWS Stock Status Cd */
    private static final String RWS_INVTY_STK_STS_CD = "RWS_INVTY_STK_STS_CD";

    /** Varchar Constant : Loan WH*/
    private static final String LOAN_DUMMY_WH_CD = "LOAN_DUMMY_WH_CD";

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmClient = null;

    /** S21SsmBatchClient */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLZC209001.class);

    /** System Date */
    private String sysDt = "";

    /** Currency Code */
    private String stdCcyCd = "";

    /** SCE_ORD_TP_CD List */
    private final List<String> sceOrdList = new ArrayList<String>();

    /** SHPG_STS_CD List */
    private final List<String> shpgStsList = new ArrayList<String>();

    /** Error Flag */
    private boolean dataErrFlg = false;

    /** Ship serial id */
    private static HashMap<String, Integer> shipSerId = new HashMap<String, Integer>();

    /**
     * <pre>
     * </pre>
     */
    public NLZC209001() {

        super();
        this.ssmClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // 10/10/2017 CITS T.Hakodate Update QC#21670 START
        /*
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_SC);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_IC);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_CC);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_SW);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_RM);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_RL);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_RX);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_RU);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_IT);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_KT);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_KU);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_KC);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_RP);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_BB);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_DP);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_DG);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_DN);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_DT);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_TR);
         * sceOrdList.add(NLXSceConst.SCE_ORD_TP_CD_RT);
         * shpgStsList.add(SHPG_STS.S_OR_O_PRINTED);
         * shpgStsList.add(SHPG_STS.PICKED);
         * shpgStsList.add(SHPG_STS.PACKED);
         * shpgStsList.add(SHPG_STS.STAGED);
         */
        // 10/10/2017 CITS T.Hakodate Update QC#21670 END
        

    }

    /**
     * <pre>
     * execute.
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC209001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateRwsCmpl(msgMap, onBatchType);

        msgMap.flush();
    }

    /**
     * <pre>
     * execute.
     * </pre>
     * 
     * @param params List<NLZC209001PMsg>
     * @param onBatchType ONBATCH_TYPE
     * @see com.canon.cusa.s21.api.NLZ.NLZC209001.execute
     */
    public void execute(final List<NLZC209001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NLZC209001PMsg msg : params) {

            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * <ul>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void updateRwsCmpl(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        dataErrFlg = false;

        boolean errFlg = checkParam(msgMap, onBatchType);

        if (errFlg) {
            return;
        }

        RWS_CPLT_WRKTMsg rwsCpltWrkT = new RWS_CPLT_WRKTMsg();
        rwsCpltWrkT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        rwsCpltWrkT.wrkTrxId.setValue(param.wrkTrxId.getValue());
        rwsCpltWrkT.sqId.setValue(param.sqId.getValue());
        rwsCpltWrkT = (RWS_CPLT_WRKTMsg) S21ApiTBLAccessor.findByKey(rwsCpltWrkT);

        if (rwsCpltWrkT == null) {

            String[] list = new String[] {RWS_CPLT_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID)
                    , NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), param.wrkTrxId.getValue(), param.sqId.getValue()) };
            addErrLogMsg(msgMap, NLZM1004E, NLAM1001E, list, onBatchType);
            return;
        }

        RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
        rwsHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        rwsHdrT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
        rwsHdrT = (RWS_HDRTMsg) S21ApiTBLAccessor.findByKey(rwsHdrT);

        if (rwsHdrT == null) {

            String[] list = new String[] {RWS_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), rwsCpltWrkT.rwsNum.getValue()) };
            addErrLogMsg(msgMap, NLZM1011E, NLAM1001E, list, onBatchType);
            return;
        }
        String hdrRwsSts = rwsHdrT.rwsStsCd.getValue();
        errFlg = checkInputData(msgMap, rwsCpltWrkT, rwsHdrT, onBatchType);

        if (errFlg) {

            return;
        }

        RWS_CPLTTMsg rwsCpltT = new RWS_CPLTTMsg();
        rwsCpltT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
        rwsCpltT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
        rwsCpltT.whCd.setValue(rwsCpltWrkT.whCd.getValue());
        rwsCpltT.rwsRefNum.setValue(rwsCpltWrkT.rwsRefNum.getValue());
        rwsCpltT.sceOrdTpCd.setValue(rwsCpltWrkT.sceOrdTpCd.getValue());
        rwsCpltT.rwsCloDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
        S21ApiTBLAccessor.insert(rwsCpltT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsCpltT.getReturnCode())) {

            String[] list = new String[] {rwsCpltT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM)
                    , NLXCMsgHelper.toListedString(rwsCpltT.glblCmpyCd.getValue(), rwsCpltT.rwsNum.getValue()) };
            throw new S21AbendException(NLAM1133E, list);
        }

        if (RWS_STS.RECEIVED.equals(rwsHdrT.rwsStsCd.getValue())) {

            deleteWhSchd(rwsHdrT);
            return;
        }

        // SCE_ORD_TP_CD
        String sceOrdTpCd = rwsCpltWrkT.sceOrdTpCd.getValue();
        sysDt = ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD);
//        sysDtTs = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);

        // QC#21574 UPD START
        //if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd)
        //        || NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd)
        //        || NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd)
        //        || NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)
        //        || NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)) {

        String rwsCloseSoCloseConst = ZYPCodeDataUtil.getVarCharConstValue("NLZC2090_RWS_CLOSE_SO_CLOSE", param.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(rwsCloseSoCloseConst)) {
            String[] rwsCloseSoClose = rwsCloseSoCloseConst.split(",");
            if (Arrays.asList(rwsCloseSoClose).contains(sceOrdTpCd)) {
                closeSo(msgMap, rwsCpltWrkT, rwsHdrT, onBatchType);
        // QC#21574 UPD END
            // QC#22820
            } else if (NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)) {
                // QC#29680
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", rwsCpltWrkT.glblCmpyCd.getValue());
                queryParam.put("soNum", rwsHdrT.trxOrdNum.getValue());
                queryParam.put("shipped", SHPG_STS.SHIPPED);

                Integer shipSoCnt = (Integer) ssmBatchClient.queryObject("checkShippingOrder", queryParam);

                if (shipSoCnt == 0) {

                    closeSo(msgMap, rwsCpltWrkT, rwsHdrT, onBatchType);
                }
            }
        }

        closeOrder(msgMap, rwsCpltWrkT, onBatchType);

        rwsHdrT.rwsStsCd.setValue(RWS_STS.RECEIVED);
        
        /** Merge DS **/
        if(!ZYPCommonFunc.hasValue(rwsHdrT.rwsCloDtTmTs)){
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCloDtTmTs, rwsCpltWrkT.rwsCloDtTmTs.getValue());
        }
        S21ApiTBLAccessor.update(rwsHdrT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {

            String[] list = new String[] {rwsHdrT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM)
                    , NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd.getValue(), rwsHdrT.rwsNum.getValue()) };
            throw new S21AbendException(NLAM1134E, list);
        }

        NLXRWSClose common = new NLXRWSClose();

        try {

            common.closeRws(param.glblCmpyCd.getValue(), rwsHdrT.rwsNum.getValue(), sceOrdTpCd, sysDt, onBatchType);

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        deleteWhSchd(rwsHdrT);

        if (!hdrRwsSts.equals(rwsHdrT.rwsStsCd.getValue()) && NLXSceConst.SCE_ORD_TP_CD_RT.equals(rwsHdrT.sceOrdTpCd.getValue())) {
            RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();
            rwsDtlT.setSQLID(SQLID_RWS_DTL);
            rwsDtlT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            rwsDtlT.setConditionValue("rwsNum01", rwsCpltWrkT.rwsNum.getValue());
            RWS_DTLTMsgArray msgArr = (RWS_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(rwsDtlT);

            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd,param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum,rwsHdrT.trxOrdNum.getValue());

          //Search RWS_HDR&RWS_DTL to DS_CPO_RTRN_DTL
            int rmaLineNo = 0;
            for (int i = 0; i < msgArr.length(); i++) {
                RWS_DTLTMsg rwsDtl = (RWS_DTLTMsg) msgArr.get(i);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd",param.glblCmpyCd.getValue());
                params.put("trxOrdNum", rwsHdrT.trxOrdNum.getValue());
                params.put("trxLineNum", rwsDtl.trxLineNum.getValue());
                params.put("trxLineSubNum", rwsDtl.trxLineSubNum.getValue());

                Map<String, Object> dsCpoDtlInfo= (Map<String, Object>)this.ssmBatchClient.queryObject("getDsCpoRtrnDtl", params);
                if (dsCpoDtlInfo == null|| dsCpoDtlInfo.size() ==0){
                    continue;
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.rmaLineList.no(rmaLineNo).dsCpoRtrnLineNum,dsCpoDtlInfo.get("DS_CPO_RTRN_LINE_NUM").toString());
                    ZYPEZDItemValueSetter.setValue(pMsg.rmaLineList.no(rmaLineNo).dsCpoRtrnLineSubNum,dsCpoDtlInfo.get("DS_CPO_RTRN_LINE_SUB_NUM").toString());
                    rmaLineNo++;                    
                }
                
            }
            pMsg.rmaLineList.setValidCount(rmaLineNo);

            if (rmaLineNo > 0) {
                NWZC188001 nwzc188001 = new NWZC188001();
                nwzc188001.execute(pMsg, onBatchType);
            }

        }

        // QC#19381 ADD START
        if (!hdrRwsSts.equals(rwsHdrT.rwsStsCd.getValue()) && NLXSceConst.SCE_ORD_TP_CD_DT.equals(rwsHdrT.sceOrdTpCd.getValue())) {
            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd,param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum,rwsHdrT.srcOrdNum.getValue());
            NWZC188001 nwzc188001 = new NWZC188001();
            nwzc188001.execute(pMsg, onBatchType);
        }
        // QC#19381 ADD END

    }

    /**
     * <pre>
     * <ul>
     * </ul>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkParam(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean errFlg = false;

        if (onBatchType == null) {

            throw new S21AbendException(NLAM1131E, new String[] {ONBATCH_TYPE, "null" });
        }

        GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY, param.glblCmpyCd.getValue(), param.glblCmpyCd.getValue());

        if (glblCmpyT == null) {

            addErrLogMsg(msgMap, NLZM1035E, NLZM1035E, null, onBatchType);
            errFlg = true;

        } else {

            stdCcyCd = glblCmpyT.stdCcyCd.getValue();
        }

        return errFlg;
    }

    /**
     * <pre>
     * checkInputData
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param rwsHdrT RWS_HDRTMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private boolean checkInputData(final S21ApiMessageMap msgMap, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final RWS_HDRTMsg rwsHdrT, final ONBATCH_TYPE onBatchType) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        boolean errFlg = false;

        String rwsStsCd = rwsHdrT.rwsStsCd.getValue();

        if (rwsStsCd.equals(RWS_STS.RECEIVED)) {

            NLXC025001.outputLog(1, NLZM1007E, null, onBatchType, this);

            RWS_CPLTTMsg rwsCpltT = new RWS_CPLTTMsg();
            rwsCpltT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            rwsCpltT.rwsNum.setValue(rwsHdrT.rwsNum.getValue());
            rwsCpltT = (RWS_CPLTTMsg) S21ApiTBLAccessor.findByKey(rwsCpltT);

            if (rwsCpltT != null) {

                errFlg = true;
            }

        } else if (rwsStsCd.equals(RWS_STS.CANCELED)) {

            addErrLogMsg(msgMap, NLZM1013E, NLZM1013E, null, onBatchType);
            errFlg = true;

        } else if (!rwsStsCd.equals(RWS_STS.PRINTED) && !rwsStsCd.equals(RWS_STS.RECEIVING)) {

            addErrLogMsg(msgMap, NLZM1016E, NLZM1016E, null, onBatchType);
            errFlg = true;
        }

        // 10/10/2017 CITS T.Hakodate Update QC#21670 START
        // if
        // (!sceOrdList.contains(rwsCpltWrkT.sceOrdTpCd.getValue())) {
        //
        // addErrLogMsg(msgMap, NLZM1036E, NLZM1036E, null,
        // onBatchType);
        // errFlg = true;
        // }
        String[] sceOrdtpList = ZYPCodeDataUtil.getVarCharConstValue("NLZC2090_SCE_ORD_TP_LIST", param.glblCmpyCd.getValue()).split(",");

        if (!(Arrays.asList(sceOrdtpList).contains(rwsCpltWrkT.sceOrdTpCd.getValue()))) {

            addErrLogMsg(msgMap, NLZM1036E, NLZM1036E, null, onBatchType);
            errFlg = true;
        }
        // 10/10/2017 CITS T.Hakodate Update QC#21670 END

        if (!rwsHdrT.sceOrdTpCd.getValue().equals(rwsCpltWrkT.sceOrdTpCd.getValue())) {

            addErrLogMsg(msgMap, NLZM1008E, NLZM1008E, null, onBatchType);
            errFlg = true;
        }

        if (!rwsHdrT.whCd.getValue().equals(rwsCpltWrkT.whCd.getValue())) {

            addErrLogMsg(msgMap, NLZM1017E, NLZM1017E, null, onBatchType);
            errFlg = true;
        }

        if (!rwsHdrT.rwsRefNum.getValue().equals(rwsCpltWrkT.rwsRefNum.getValue())) {

            addErrLogMsg(msgMap, NLZM1018E, NLZM1018E, null, onBatchType);
            errFlg = true;
        }

        return errFlg;
    }

    /**
     * <pre>
     * closeSo
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param rwsHdrT RWS_HDRTMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void closeSo(final S21ApiMessageMap msgMap, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final RWS_HDRTMsg rwsHdrT, final ONBATCH_TYPE onBatchType) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        String sceOrdTpCd = rwsCpltWrkT.sceOrdTpCd.getValue();

        String soNum = rwsHdrT.trxOrdNum.getValue();

        if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("rwsNum", rwsHdrT.rwsNum.getValue());
            queryParam.put("poNum", rwsHdrT.poRcvTrxHdrNum.getValue());
            soNum = (String) ssmBatchClient.queryObject("getSoNumForBb", queryParam);

            if (!ZYPCommonFunc.hasValue(soNum)) {

                String[] list = new String[] {SHPG_ORD, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), rwsHdrT.rwsNum.getValue()) };
                throw new S21AbendException(NLAM1001E, list);
            }
        }

        // QC#21574 ADD START
        if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
            soNum = rwsHdrT.imptInvNum.getValue();
        }
        // QC#21574 ADD END

        // get Shipping Order
        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();
        shpgOrdT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        shpgOrdT.soNum.setValue(soNum);
        shpgOrdT = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdT);

        if (shpgOrdT == null) {
            String[] list = new String[] {SHPG_ORD, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), soNum) };
            throw new S21AbendException(NLAM1001E, list);
        }

        // get Shipping Order Detail
        SHPG_ORD_DTLTMsg shpgOrdDtlT = new SHPG_ORD_DTLTMsg();
        shpgOrdDtlT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        shpgOrdDtlT.setConditionValue("soNum01", soNum);
        shpgOrdDtlT.setSQLID(SQLID_SHPG_ORD_DTL);
        SHPG_ORD_DTLTMsgArray msgArr = (SHPG_ORD_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(shpgOrdDtlT);

        if (msgArr.length() == 0) {

            String[] list = new String[] {shpgOrdDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), soNum) };
            throw new S21AbendException(NLAM1001E, list);
        }

        int outCnt = 0;
        // QC#18794
        String slsDt = ZYPDateUtil.getSalesDate(shpgOrdT.glblCmpyCd.getValue());

        for (int i = 0; i < msgArr.length(); i++) {

            shpgOrdDtlT = (SHPG_ORD_DTLTMsg) msgArr.get(i);
            String shpgStsCd = shpgOrdDtlT.shpgStsCd.getValue();

            
            // 10/10/2017 CITS T.Hakodate Update QC#21670 START
            // if (!shpgStsList.contains(shpgStsCd)) {
            //
            // continue;
            // }
            //            
            String[] shpgStsCdList = ZYPCodeDataUtil.getVarCharConstValue("NLZC2090_SHPG_STS_CD_LIST", param.glblCmpyCd.getValue()).split(",");

            if (!(Arrays.asList(shpgStsCdList).contains(shpgStsCd))) {
                continue;
            }

            // 10/10/2017 CITS T.Hakodate Update QC#21670 END

            if (NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd) && BigDecimal.ZERO.compareTo(shpgOrdDtlT.shpgQty.getValue()) > 0) {
                // QC#26132
                // Item Change Dummy Line
                shpgOrdDtlT.shpgStsCd.setValue(SHPG_STS.SHIPPED);
                shpgOrdDtlT.dsSoLineStsCd.setValue(DS_SO_LINE_STS.SHIPPED);
                S21ApiTBLAccessor.update(shpgOrdDtlT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDtlT.getReturnCode())) {

                    String[] list = new String[] {shpgOrdDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_SLP_NUM),
                            NLXCMsgHelper.toListedString(shpgOrdDtlT.glblCmpyCd.getValue(), shpgOrdDtlT.soNum.getValue(), shpgOrdDtlT.soSlpNum.getValue()) };
                    throw new S21AbendException(NLAM1134E, list);
                }
                continue;
            }

            outCnt++;

            // create Shipping Order Confirmation Detail
            SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlT = new SHPG_ORD_CONF_DTLTMsg();
            shpgOrdConfDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            shpgOrdConfDtlT.soNum.setValue(shpgOrdDtlT.soNum.getValue());
            shpgOrdConfDtlT.soSlpNum.setValue(shpgOrdDtlT.soSlpNum.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.whCd, shpgOrdDtlT.invtyLocCd);
            shpgOrdConfDtlT.fromStkStsCd.setValue(shpgOrdDtlT.fromStkStsCd.getValue());
            shpgOrdConfDtlT.shipQty.setValue(shpgOrdDtlT.shpgQty.getValue());
            shpgOrdConfDtlT.soProcStsCd.setValue(SO_PROC_STS_SHIPPED);
            shpgOrdConfDtlT.mdseCd.setValue(shpgOrdDtlT.mdseCd.getValue());
            shpgOrdConfDtlT.proNum.setValue(FREE);
            shpgOrdConfDtlT.bolNum.setValue(FREE);
            shpgOrdConfDtlT.vndCd.setValue(FREE);
            shpgOrdConfDtlT.frtRelnFlg.setValue(ZYPConstant.FLG_OFF_N);
            S21ApiTBLAccessor.insert(shpgOrdConfDtlT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfDtlT.getReturnCode())) {

                String[] list = new String[] {shpgOrdConfDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_SLP_NUM, PRO_NUM, BOL_NUM, VND_CD),
                        NLXCMsgHelper.toListedString(shpgOrdConfDtlT.glblCmpyCd.getValue(), shpgOrdConfDtlT.soNum.getValue(), shpgOrdConfDtlT.soSlpNum.getValue(), FREE, FREE, FREE) };
                throw new S21AbendException(NLAM1133E, list);
            }

            // QC#18794 Craete SHIP_SER_NUM for Item Change
            if (NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd)) {

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
                queryParam.put("soNum", shpgOrdConfDtlT.soNum.getValue());
                queryParam.put("soSlpNum", shpgOrdConfDtlT.soSlpNum.getValue());
                queryParam.put("mdseCd", shpgOrdConfDtlT.mdseCd.getValue());
                queryParam.put("whPickFlgY", ZYPConstant.FLG_ON_Y);
                List<Map<String, Object>> soSerMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSoSerForIc", queryParam);

                if (soSerMapList == null || soSerMapList.isEmpty()) {
                    queryParam = new HashMap<String, Object>();
                    queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
                    queryParam.put("soNum", shpgOrdConfDtlT.soNum.getValue());
                    queryParam.put("soSlpNum", shpgOrdConfDtlT.soSlpNum.getValue());
                    queryParam.put("mdseCd", shpgOrdConfDtlT.mdseCd.getValue());
                    queryParam.put("ordAsgFlgY", ZYPConstant.FLG_ON_Y);
                    soSerMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSoSerForIc", queryParam);
                }

                List<NSZC001001PMsg> machMstrPMsgList = new ArrayList<NSZC001001PMsg>();

                if (soSerMapList != null && !soSerMapList.isEmpty()) {

                    for (Map<String, Object> soSerMap : soSerMapList) {
                        // check ship serial id
                        String shipSerialIdKey = shpgOrdConfDtlT.soNum.getValue() + shpgOrdConfDtlT.mdseCd.getValue();

                        int count = 1;

                        if (shipSerId.containsKey(shipSerialIdKey)) {
                            count = shipSerId.get(shipSerialIdKey) + 1;
                            shipSerId.put(shipSerialIdKey, count);
                        } else {
                            shipSerId.put(shipSerialIdKey, count);
                        }

                        SHIP_SER_NUMTMsg shipSerNum = new SHIP_SER_NUMTMsg();
                        shipSerNum.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(shipSerNum.mdseCd, shpgOrdConfDtlT.mdseCd);
                        ZYPEZDItemValueSetter.setValue(shipSerNum.soNum, shpgOrdConfDtlT.soNum);
                        ZYPEZDItemValueSetter.setValue(shipSerNum.whCd, shpgOrdConfDtlT.whCd);
                        ZYPEZDItemValueSetter.setValue(shipSerNum.soSerId, String.valueOf(count));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.serNum, (String) soSerMap.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.serTakeDtTmTs, rwsCpltWrkT.rwsCloDtTmTs);
                        ZYPEZDItemValueSetter.setValue(shipSerNum.soSlpNum, shpgOrdConfDtlT.soSlpNum);
                        ZYPEZDItemValueSetter.setValue(shipSerNum.serIntfcProcStsCd, PROC_STS.IN_COMPLETED);

                        S21ApiTBLAccessor.insert(shipSerNum);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipSerNum.getReturnCode())) {

                            String[] list = new String[] {shipSerNum.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, "SO_NUM", "SO_SLP_NUM", SER_NUM, MDSE_CD),
                                    NLXCMsgHelper.toListedString(shipSerNum.glblCmpyCd.getValue(), shipSerNum.soNum.getValue(), shipSerNum.soSlpNum.getValue(), shipSerNum.serNum.getValue(), shipSerNum.mdseCd.getValue()) };
                            throw new S21AbendException(NLAM1133E, list);
                        }

                        // Machine Master Dispoasl
                        if (ZYPCommonFunc.hasValue((BigDecimal) soSerMap.get("SVC_MACH_MSTR_PK"))) {
                            // Serial IB
                            SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
                            ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, param.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, (BigDecimal) soSerMap.get("SVC_MACH_MSTR_PK"));
                            smmTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(smmTMsg);

                            if (smmTMsg == null) {
                                continue;
                            }

                            machMstrPMsgList.add(createMachMstrPMsg(param.glblCmpyCd.getValue(), slsDt, smmTMsg, ProcessMode.ALLOCATION_OFF.code));
                            machMstrPMsgList.add(createMachMstrPMsg(param.glblCmpyCd.getValue(), slsDt, smmTMsg, ProcessMode.DISPOSAL.code));

                        }
                    }
                }

                // Non Serial Check
                queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
                queryParam.put("trxHdrNum", shpgOrdDtlT.trxHdrNum.getValue());
                queryParam.put("trxLineNum", shpgOrdDtlT.trxLineNum.getValue());
                queryParam.put("rowNum", shpgOrdConfDtlT.shipQty.getValue());
                List<BigDecimal> smmPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getIbListNonSerItem", queryParam);

                for (BigDecimal svcMachMstrPk : smmPkList) {
                    // Non Serial IB
                    SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
                    ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, svcMachMstrPk);
                    smmTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(smmTMsg);

                    if (smmTMsg == null) {
                        continue;
                    }

                    machMstrPMsgList.add(createMachMstrPMsg(param.glblCmpyCd.getValue(), slsDt, smmTMsg, ProcessMode.ALLOCATION_OFF.code));
                    machMstrPMsgList.add(createMachMstrPMsg(param.glblCmpyCd.getValue(), slsDt, smmTMsg, ProcessMode.DISPOSAL.code));
                }

                // Execute Machine Mster
                if (!machMstrPMsgList.isEmpty()) {

                    NSZC001001 api = new NSZC001001();
                    api.execute(machMstrPMsgList, onBatchType);

                    for (NSZC001001PMsg machMstrPmsg : machMstrPMsgList) {
                        if (S21ApiUtil.isXxMsgId(machMstrPmsg)) {
                            List<String> msgList = S21ApiUtil.getXxMsgIdList(machMstrPmsg);
                            for (int ii = 0; i < msgList.size(); ii++) {
                                String messageId = msgList.get(ii);
                                if (messageId.endsWith("E")) {
                                    throw new S21AbendException(msgList.get(0));
                                } else if (messageId.endsWith("W")) {
                                    msgMap.addXxMsgId(messageId);
                                }
                            }
                        }
                    }
                }
            }

            // update Shipping Order Detail
            shpgOrdDtlT.shipQty.setValue(shpgOrdDtlT.shpgQty.getValue());
            shpgOrdDtlT.shpgStsCd.setValue(SHPG_STS.SHIPPED);
            shpgOrdDtlT.dsSoLineStsCd.setValue(DS_SO_LINE_STS.SHIPPED);
            S21ApiTBLAccessor.update(shpgOrdDtlT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDtlT.getReturnCode())) {

                String[] list = new String[] {shpgOrdDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_SLP_NUM),
                        NLXCMsgHelper.toListedString(shpgOrdDtlT.glblCmpyCd.getValue(), shpgOrdDtlT.soNum.getValue(), shpgOrdDtlT.soSlpNum.getValue()) };
                throw new S21AbendException(NLAM1134E, list);
            }
            // QC#23072 Upd Start
            if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
            	// Reman Order Update
                NPZC126001PMsg pMsg = new NPZC126001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, RMNF_ORD_STS.COMPLETED);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrdT.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(shpgOrdT.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(pMsg.rmnfOrdNum, shpgOrdT.trxHdrNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.soNum, shpgOrdT.soNum.getValue());

                NPZC126001 api = new NPZC126001();
                api.execute(pMsg, onBatchType);

                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
                    for (int ii = 0; i < msgList.size(); ii++) {
                        String messageId = msgList.get(ii);
                        if (messageId.endsWith("E")) {
                            throw new S21AbendException(msgList.get(0));
                        } else if (messageId.endsWith("W")) {
                            msgMap.addXxMsgId(messageId);
                        }
                    }
                }
            }
            // QC#23072 Upd End
            // call Inventory Update API
            NLZC002001 api = new NLZC002001();

            // QC#21574 UPD START
            if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_RX.equals(sceOrdTpCd)) {

                NLZC002001PMsg outPMsg = getPmsgCloseSoForReman(param, shpgOrdT, shpgOrdDtlT, rwsHdrT, sceOrdTpCd);
                // QC#21574 UPD END

                api.execute(outPMsg, onBatchType);

                checkApiErr(msgMap, outPMsg);

            } else {

                NLZC002001PMsg outPMsg = getPmsgCloseSo(param, shpgOrdT, shpgOrdDtlT, rwsHdrT, sceOrdTpCd);
                api.execute(outPMsg, onBatchType);

                checkApiErr(msgMap, outPMsg);
            }

            // call Shipping Plan Update API
            callNWZC003001(param.glblCmpyCd.getValue(), shpgOrdDtlT.shpgPlnNum.getValue(), rwsCpltWrkT.rwsCloDtTmTs.getValue(), shpgOrdDtlT.shipQty.getValue(), onBatchType);
        }

        if (outCnt == 0) {

            String[] list = new String[] {shpgOrdDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), soNum) };
            throw new S21AbendException(NLAM1001E, list);
        }

        // create Shipping Order Confirmation Header
        SHPG_ORD_CONFTMsg shpgOrdConfT = new SHPG_ORD_CONFTMsg();
        shpgOrdConfT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        shpgOrdConfT.soNum.setValue(shpgOrdT.soNum.getValue());
        shpgOrdConfT.whCd.setValue(shpgOrdT.whCd.getValue());
        shpgOrdConfT.sceOrdTpCd.setValue(shpgOrdT.sceOrdTpCd.getValue());
        shpgOrdConfT.soProcStsCd.setValue(SO_PROC_STS_SHIPPED);
        shpgOrdConfT.shipDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
        S21ApiTBLAccessor.insert(shpgOrdConfT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfT.getReturnCode())) {

            String[] list = new String[] {shpgOrdConfT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM)
                    , NLXCMsgHelper.toListedString(shpgOrdConfT.glblCmpyCd.getValue(), shpgOrdConfT.soNum.getValue()) };
            throw new S21AbendException(NLAM1133E, list);
        }

        // update Shipping Order
        shpgOrdT.shpgStsCd.setValue(SHPG_STS.SHIPPED);

        S21ApiTBLAccessor.update(shpgOrdT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdT.getReturnCode())) {

            String[] list = new String[] {shpgOrdT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM)
                    , NLXCMsgHelper.toListedString(shpgOrdDtlT.glblCmpyCd.getValue(), shpgOrdDtlT.soNum.getValue()) };
            throw new S21AbendException(NLAM1134E, list);
        }
    }

    /**
     * <pre>
     * Close Orders
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void closeOrder(final S21ApiMessageMap msgMap, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        String sceOrdTpCd = rwsCpltWrkT.sceOrdTpCd.getValue();

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", param.glblCmpyCd.getValue());
        paramMap.put("rwsNum", rwsCpltWrkT.rwsNum.getValue());

        String statementId = "";

        if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)) {

            statementId = "getScIcCcSwItInfo";

        // QC#21574 ADD START
        } else if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {

            statementId = "getRmInfoRm";

        } else if (NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

            paramMap.put("remanUsage", NLXSceConst.SCE_ORD_TP_CD_RU);
            statementId = "getRmInfoRu";

        } else if (NLXSceConst.SCE_ORD_TP_CD_RL.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_RX.equals(sceOrdTpCd)) {

            statementId = "getRmInfo";

        // QC#21574 ADD END
        } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd)) {

            statementId = "getKtKuInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_KC.equals(sceOrdTpCd)) {

            statementId = "getKcInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {

            statementId = "getRpInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {

            statementId = "getBbInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_DG.equals(sceOrdTpCd)
                || NLXSceConst.SCE_ORD_TP_CD_DN.equals(sceOrdTpCd)) {

            statementId = "getDpDgDnInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd)) {

            statementId = "getDtInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd)) {

            statementId = "getTrInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_RT.equals(sceOrdTpCd)) {

            statementId = "getRtInfo";
        }

        PreparedStatement closeState = null;
        ResultSet closeRs = null;

        try {

            closeState = this.ssmClient.createPreparedStatement(statementId, paramMap);
            closeRs = closeState.executeQuery();

            boolean cur = closeRs.next();

            if (!cur) {

                String[] list = new String[] {statementId, NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), rwsCpltWrkT.rwsNum.getValue()) };
                throw new S21AbendException(NLAM1132E, list);
            }

            if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)) {

                closeScIcCcSwIt(msgMap, closeRs, rwsCpltWrkT, onBatchType);

            } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_KC.equals(sceOrdTpCd)) {

                closeKtKuKc(msgMap, closeRs, rwsCpltWrkT, onBatchType);

            } else if (NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {

                closeRp(msgMap, closeRs, rwsCpltWrkT, onBatchType, sceOrdTpCd);

            } else if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {

                closeBb(msgMap, closeRs, rwsCpltWrkT, onBatchType, sceOrdTpCd);

            } else if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_DG.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_DN.equals(sceOrdTpCd)) {

                closeDpDgDn(msgMap, closeRs, rwsCpltWrkT, onBatchType, sceOrdTpCd);

            } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd)) {

                closeDt(msgMap, closeRs, onBatchType, sceOrdTpCd);

            } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd)) {

                closeTr(msgMap, closeRs, rwsCpltWrkT, onBatchType, sceOrdTpCd);

            } else if (NLXSceConst.SCE_ORD_TP_CD_RT.equals(sceOrdTpCd)) {

                closeRt(msgMap, closeRs, rwsCpltWrkT, onBatchType);

            } else if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_RL.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_RX.equals(sceOrdTpCd)
                    || NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

                closeReman(msgMap, closeRs, rwsCpltWrkT, onBatchType);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(closeState, closeRs);
        }
    }

    /**
     * <pre>
     * Close Order for SC, IC, SW and IT
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException SQLException
     */
    private void closeScIcCcSwIt(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;
        String sceOrdTpCd = rwsCpltWrkT.sceOrdTpCd.getValue();
        List<String> invtyOrdList = new ArrayList<String>();

        for (; cur;) {

            // mandatory check
            String invtyOrdNum = NLXC014001.nullToEmpty(closeRs.getString(INVTY_ORD_NUM));

            if (ZYPCommonFunc.hasValue(invtyOrdNum) && !invtyOrdList.contains(invtyOrdNum)) {

                if (NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)) {

                    Map<String, Object> queryParam = new HashMap<String, Object>();
                    queryParam.put("glblCmpyCd", rwsCpltWrkT.glblCmpyCd.getValue());
                    queryParam.put("rwsNum", rwsCpltWrkT.rwsNum.getValue());
                    queryParam.put("invtyOrdNum", invtyOrdNum);
                    queryParam.put("rwsStsReceived", RWS_STS.RECEIVED);

                    Integer openRwsCnt = (Integer) ssmBatchClient.queryObject("checkAllRelatedRwsClosed", queryParam);

                    if (openRwsCnt == 0) {

                        invtyOrdList.add(invtyOrdNum);
                    }

                } else {

                    invtyOrdList.add(invtyOrdNum);
                }
            }

            // RWS Status check
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // QC#22374
            // Create or Update RWS Put Away
            RWS_PUT_AWAYTMsg rwsPutAwayT = new RWS_PUT_AWAYTMsg();
            rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
            rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
            rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
            rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));

            rwsPutAwayT = (RWS_PUT_AWAYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsPutAwayT);

            if (rwsPutAwayT == null) {

                rwsPutAwayT = new RWS_PUT_AWAYTMsg();
                rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
                rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
                rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
                rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));
                rwsPutAwayT.whCd.setValue(closeRs.getString(INVTY_LOC_CD));
                rwsPutAwayT.rwsRefNum.setValue(rwsCpltWrkT.rwsRefNum.getValue());
                rwsPutAwayT.sceOrdTpCd.setValue(rwsCpltWrkT.sceOrdTpCd.getValue());
                rwsPutAwayT.rwsStkQty.setValue(NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)));
                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
                rwsPutAwayT.mdseCd.setValue(closeRs.getString(MDSE_CD));

                S21ApiTBLAccessor.insert(rwsPutAwayT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                    throw new S21AbendException(NLAM1133E, list);
                }

            } else {

                BigDecimal putAwayRwsStkQty = NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue());
                BigDecimal wrkRwsStkQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY));
                BigDecimal rwsStkQty = putAwayRwsStkQty.add(wrkRwsStkQty);
                rwsPutAwayT.rwsStkQty.setValue(rwsStkQty);
                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());

                S21ApiTBLAccessor.update(rwsPutAwayT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                    throw new S21AbendException(NLAM1134E, list);
                }
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, rwsCpltWrkT.rwsNum.getValue(), NLXC014001.nullToEmpty(closeRs.getString(RWS_DTL_LINE_NUM)), rwsPutAwayT.rwsStkQty.getValue());

            // Call Inventory Update API
            NLZC002001PMsg inPMsg = getPmsgScIcCcSwIt(param, closeRs, sceOrdTpCd);

            NLZC002001 api = new NLZC002001();
            api.execute(inPMsg, onBatchType);

            checkApiErr(msgMap, inPMsg);

            if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)) {

                // Call PR Update API to close request
                String prchReqNum = NLXC014001.nullToEmpty(closeRs.getString(PRCH_REQ_NUM));
                String prchReqLineNum = NLXC014001.nullToEmpty(closeRs.getString(PRCH_REQ_LINE_NUM));
                BigDecimal prchReqLineSubNum = closeRs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);

                BigDecimal rwsQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_QTY));
                BigDecimal rwsPutAwayQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));
                BigDecimal remainingQty = rwsQty.subtract(rwsPutAwayQty);
                BigDecimal rwsStkQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY));
                String rwsCloDtTmTs = null;
                if(remainingQty.compareTo(rwsStkQty)==0) {
                    rwsCloDtTmTs = rwsCpltWrkT.rwsCloDtTmTs.getValue();
                }
                callNPZC103001(param.glblCmpyCd.getValue(), prchReqNum, prchReqLineNum, prchReqLineSubNum,
                        rwsCpltWrkT.rwsNum.getValue(), rwsCloDtTmTs, rwsStkQty, sceOrdTpCd, onBatchType);

            }

            // Call Inventory Order Update API to close order detail
            callNLZC003001(param.glblCmpyCd.getValue(), invtyOrdNum, closeRs.getString(INVTY_ORD_LINE_NUM), null, onBatchType, false, NLZC003001.PROC_TP_CLO);

            insertRwsPutAwaySer(param, rwsCpltWrkT.rwsNum.getValue(), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getString(RWS_DTL_LINE_NUM), sceOrdTpCd, rwsCpltWrkT.rwsCloDtTmTs.getValue(), closeRs);

            cur = closeRs.next();
        }

        // Call Inventory Order Update API to close order header
        callNLZC003001(param.glblCmpyCd.getValue(), "", "", invtyOrdList, onBatchType, true, NLZC003001.PROC_TP_CLO);
    }

    /**
     * <pre>
     * Close Order for Reman(RM,RL,RU,RX)
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException SQLException
     */
    private void closeReman(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;
        String poRcvNum = "";
        String sceOrdTpCd = rwsCpltWrkT.sceOrdTpCd.getValue();

        for (; cur;) {

            poRcvNum = NLXC014001.nullToEmpty(closeRs.getString(TRX_ORD_NUM));

            // RWS Status check
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // QC#22374
            // Create or Update RWS Put Away
            RWS_PUT_AWAYTMsg rwsPutAwayT = new RWS_PUT_AWAYTMsg();
            rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
            rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
            rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
            rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));

            rwsPutAwayT = (RWS_PUT_AWAYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsPutAwayT);

            if (rwsPutAwayT == null) {

                rwsPutAwayT = new RWS_PUT_AWAYTMsg();
                rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
                rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
                rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
                rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));
                rwsPutAwayT.whCd.setValue(closeRs.getString(INVTY_LOC_CD));
                rwsPutAwayT.rwsRefNum.setValue(rwsCpltWrkT.rwsRefNum.getValue());
                rwsPutAwayT.sceOrdTpCd.setValue(rwsCpltWrkT.sceOrdTpCd.getValue());
                rwsPutAwayT.rwsStkQty.setValue(NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)));
                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
                rwsPutAwayT.mdseCd.setValue(closeRs.getString(MDSE_CD));

                S21ApiTBLAccessor.insert(rwsPutAwayT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                    throw new S21AbendException(NLAM1133E, list);
                }

            } else {

                BigDecimal putAwayRwsStkQty = NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue());
                BigDecimal wrkRwsStkQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY));
                BigDecimal rwsStkQty = putAwayRwsStkQty.add(wrkRwsStkQty);
                rwsPutAwayT.rwsStkQty.setValue(rwsStkQty);
                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());

                S21ApiTBLAccessor.update(rwsPutAwayT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                    throw new S21AbendException(NLAM1134E, list);
                }
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, rwsCpltWrkT.rwsNum.getValue(), NLXC014001.nullToEmpty(closeRs.getString(RWS_DTL_LINE_NUM)), rwsPutAwayT.rwsStkQty.getValue());

          // QC#26863 ADD START
            // QC#21574 ADD START
            // if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd) ||
            // NLXSceConst.SCE_ORD_TP_CD_RX.equals(sceOrdTpCd)) {
            if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
                // Call Inventory Update API (NLZC0020)
                List<NLZC002001PMsg> pMsgList = getPmsgRemanStockIn(param, closeRs);

                NLZC002001 api = new NLZC002001();
                api.execute(pMsgList, onBatchType);

                for (NLZC002001PMsg msg : pMsgList) {
                    checkApiErr(msgMap, msg);
                }
            }
            // QC#21574 ADD END
            // QC#26863 ADD START

            // Close PO Receiving Detail
            if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

                closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)));
            }

            insertRwsPutAwaySer(param, rwsCpltWrkT.rwsNum.getValue(), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getString(RWS_DTL_LINE_NUM), sceOrdTpCd, rwsCpltWrkT.rwsCloDtTmTs.getValue(), closeRs);

            // QC#13811 START
            if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
                updateInbdVisDo(msgMap, sceOrdTpCd, poRcvNum, closeRs ,  rwsPutAwayT.rwsStkQty.getValue());
            }
            // QC#13811 END
            
            cur = closeRs.next();
        }

        // Close PO Receiving Header
        if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

            closePoHeader(msgMap, poRcvNum);
        }
    }

    /**
     * <pre>
     * Close Order for Kt, KU and KC
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException SQLException
     */
    private void closeKtKuKc(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;
        boolean cancelWrkOrd = false;
        String sceOrdTpCd = rwsCpltWrkT.sceOrdTpCd.getValue();
        String poRcvNum = "";
        String wrkOrdNum = "";

        // Check Receive or Cancel
        if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd)) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", rwsCpltWrkT.glblCmpyCd.getValue());
            queryParam.put("rwsNum", rwsCpltWrkT.rwsNum.getValue());

            Integer rwsPutAwayWrkCnt = (Integer) ssmBatchClient.queryObject("checkRwsPutAwayWork", queryParam);

            if (rwsPutAwayWrkCnt == 0) {

                cancelWrkOrd = true;
            }
        }

        // Update Original Item Inventory for Receive
        if (!cancelWrkOrd) {

            // Get Original Item Info
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", rwsCpltWrkT.glblCmpyCd.getValue());
            queryParam.put("rwsNum", rwsCpltWrkT.rwsNum.getValue());
            queryParam.put("sceOrdTpCd", sceOrdTpCd);

            List<Map<String, Object>> shipMdseKtKuKcInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShipMdseKtKuKcInfoList", queryParam);

            // Call Inventory Update API (NLZC0020) for Original Item
            List<NLZC002001PMsg> pMsgStkOutList = getPmsgKtKuKcForStkOut(param, shipMdseKtKuKcInfoList, sceOrdTpCd);

            NLZC002001 stkOutApi = new NLZC002001();
            stkOutApi.execute(pMsgStkOutList, onBatchType);

            for (NLZC002001PMsg msg : pMsgStkOutList) {

                checkApiErr(msgMap, msg);
            }
        }

        for (; cur;) {

            poRcvNum = NLXC014001.nullToEmpty(closeRs.getString(TRX_ORD_NUM));
            wrkOrdNum = NLXC014001.nullToEmpty(closeRs.getString(WRK_ORD_NUM));

            // RWS Status Check
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // Create RWS Put Away for Receive
            RWS_PUT_AWAYTMsg rwsPutAwayT = null;
            if (!cancelWrkOrd) {

                // QC#22374 Update
                rwsPutAwayT = new RWS_PUT_AWAYTMsg();
                rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
                rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
                rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
                rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));

                rwsPutAwayT = (RWS_PUT_AWAYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsPutAwayT);

                if (rwsPutAwayT == null) {

                    rwsPutAwayT = new RWS_PUT_AWAYTMsg();
                    rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
                    rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
                    rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
                    rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));
                    rwsPutAwayT.whCd.setValue(closeRs.getString(INVTY_LOC_CD));
                    rwsPutAwayT.rwsRefNum.setValue(rwsCpltWrkT.rwsRefNum.getValue());
                    rwsPutAwayT.sceOrdTpCd.setValue(rwsCpltWrkT.sceOrdTpCd.getValue());
                    rwsPutAwayT.rwsStkQty.setValue(NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)));
                    rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
                    rwsPutAwayT.mdseCd.setValue(closeRs.getString(MDSE_CD));

                    S21ApiTBLAccessor.insert(rwsPutAwayT);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                        String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                                NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                        throw new S21AbendException(NLAM1133E, list);
                    }

                } else {

                    BigDecimal putAwayRwsStkQty = NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue());
                    BigDecimal wrkRwsStkQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY));
                    BigDecimal rwsStkQty = putAwayRwsStkQty.add(wrkRwsStkQty);
                    rwsPutAwayT.rwsStkQty.setValue(rwsStkQty);
                    rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());

                    S21ApiTBLAccessor.update(rwsPutAwayT);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                        String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                                NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                        throw new S21AbendException(NLAM1134E, list);
                    }
                }
            }

            BigDecimal rwsPutAwayQty = BigDecimal.ZERO;
            BigDecimal lossQty = BigDecimal.ZERO;

            if (cancelWrkOrd) {

                rwsPutAwayQty = BigDecimal.ZERO;
                lossQty = closeRs.getBigDecimal(RWS_QTY);
            } else {

                rwsPutAwayQty = rwsPutAwayT.rwsStkQty.getValue();
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, closeRs.getString(RWS_NUM), closeRs.getString(RWS_DTL_LINE_NUM), rwsPutAwayQty);

            // Call Inventory Update API (NLZC0020) for Receive
            if (!cancelWrkOrd) {

                List<NLZC002001PMsg> pMsgList = getPmsgKtKuKc(param, closeRs, sceOrdTpCd);

                NLZC002001 api = new NLZC002001();
                api.execute(pMsgList, onBatchType);

                for (NLZC002001PMsg msg : pMsgList) {
                    checkApiErr(msgMap, msg);
                }
            }

            // Close PO Receiving Detail
            if (cancelWrkOrd) {
                closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)), lossQty, sceOrdTpCd);
            } else {
                closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)), closeRs.getBigDecimal(RWS_QTY).subtract(rwsPutAwayT.rwsStkQty.getValue()), sceOrdTpCd);
            }

            // Create RWS Put Away Serial for Receive
            if (!cancelWrkOrd) {

                insertRwsPutAwaySer(param, rwsCpltWrkT.rwsNum.getValue(), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getString(RWS_DTL_LINE_NUM), sceOrdTpCd, rwsCpltWrkT.rwsCloDtTmTs.getValue(), closeRs);
           }

            // QC#13811 START
            updateInbdVisDo(msgMap, sceOrdTpCd, poRcvNum, closeRs , rwsPutAwayQty);
            // QC#13811 END

            
            cur = closeRs.next();
        }

        // Close PO Receiving Header
        closePoHeader(msgMap, poRcvNum);

        // Call Work Order Update API
        if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd)) {

            callNPZC002001(param.glblCmpyCd.getValue(), wrkOrdNum, onBatchType, cancelWrkOrd);
        }
    }

    /**
     * <pre>
     * Close Order for RP
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException SQLException
     */
    private void closeRp(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType, String sceOrdTpCd) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;

        // QC#22481 Start
        String poRcvNum = "";

        for (; cur;) {
            // QC#55313 Start
            // Get Original Item Info
//            Map<String, Object> queryParam = new HashMap<String, Object>();
//            queryParam.put("glblCmpyCd", rwsCpltWrkT.glblCmpyCd.getValue());
//            queryParam.put("rwsNum", rwsCpltWrkT.rwsNum.getValue());
//            queryParam.put("rwsDtlLineNum", closeRs.getString(RWS_DTL_LINE_NUM));
//            // QC#54841
//            queryParam.put("adjustment", TRX.ADJUSTMENT);
//            queryParam.put("trxRsnCd15", TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR);
//            queryParam.put("wipSubCon", LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
//
//            List<Map<String, Object>> shipMdseRpInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShipMdseRpInfoList", queryParam);
//
//            // QC#54841 Start
//            if (shipMdseRpInfoList != null && !shipMdseRpInfoList.isEmpty()) {
//                // Call Inventory Update API (NLZC0020) for Original
//                // Item
//                List<NLZC002001PMsg> pMsgStkOutList = getPmsgRpForStkOut(param, shipMdseRpInfoList);
//
//                NLZC002001 stkOutApi = new NLZC002001();
//                stkOutApi.execute(pMsgStkOutList, onBatchType);
//
//                for (NLZC002001PMsg msg : pMsgStkOutList) {
//
//                    checkApiErr(msgMap, msg);
//                }
//                // QC#22481 End
//            }
            // QC#54841 End
            // RWS Status check
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // QC#22374
            // Create RWS Put Away
//            RWS_PUT_AWAYTMsg rwsPutAwayT = new RWS_PUT_AWAYTMsg();
//            rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
//            rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
//            rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
//            rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));
//
//            rwsPutAwayT = (RWS_PUT_AWAYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsPutAwayT);
//
//            if (rwsPutAwayT == null) {
//
//                rwsPutAwayT = new RWS_PUT_AWAYTMsg();
//                rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
//                rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
//                rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
//                rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));
//                rwsPutAwayT.whCd.setValue(closeRs.getString(INVTY_LOC_CD));
//                rwsPutAwayT.rwsRefNum.setValue(rwsCpltWrkT.rwsRefNum.getValue());
//                rwsPutAwayT.sceOrdTpCd.setValue(rwsCpltWrkT.sceOrdTpCd.getValue());
//                rwsPutAwayT.rwsStkQty.setValue(NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)));
//                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
//                rwsPutAwayT.mdseCd.setValue(closeRs.getString(MDSE_CD));
//
//                S21ApiTBLAccessor.insert(rwsPutAwayT);
//
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
//                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
//                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
//                    throw new S21AbendException(NLAM1133E, list);
//                }
//
//            } else {
//
//                BigDecimal putAwayRwsStkQty = NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue());
//                BigDecimal wrkRwsStkQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY));
//                BigDecimal rwsStkQty = putAwayRwsStkQty.add(wrkRwsStkQty);
//                rwsPutAwayT.rwsStkQty.setValue(rwsStkQty);
//                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
//
//                S21ApiTBLAccessor.update(rwsPutAwayT);
//
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
//                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
//                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
//                    throw new S21AbendException(NLAM1134E, list);
//                }
//            }
            // Close RWS Detail
            closeRwsDetail(msgMap, rwsCpltWrkT.rwsNum.getValue(), NLXC014001.nullToEmpty(closeRs.getString(RWS_DTL_LINE_NUM)), NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_PUT_AWAY_QTY)));

            // Call Inventory Update API (NLZC0020) for New Item
            // QC#22491 Start
//            if (NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue()).compareTo(BigDecimal.ZERO) != 0) {
//                List<NLZC002001PMsg> pMsgList = getPmsgRpForStkIn(param, closeRs);
//
//                NLZC002001 api = new NLZC002001();
//                api.execute(pMsgList, onBatchType);
//
//                for (NLZC002001PMsg msg : pMsgList) {
//
//                    checkApiErr(msgMap, msg);
//                }
//            }
            // QC#22491 End

            // Close PO Receiving Detail
            poRcvNum = NLXC014001.nullToEmpty(closeRs.getString(TRX_ORD_NUM));
            // QC#22492
            closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)), closeRs.getBigDecimal(RWS_QTY).subtract(closeRs.getBigDecimal(RWS_PUT_AWAY_QTY)), sceOrdTpCd);

            // Call PO Status Update API (NPZC0040)
            String poOrdNum = closeRs.getString(PO_ORD_NUM);
            String poDtlLineNum = closeRs.getString(PO_ORD_DTL_LINE_NUM);

            // QC#22491 Start
            if (NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)).compareTo(BigDecimal.ZERO) != 0) {
                // QC#55313
                // For Receiving
                callNPZC004001ForReceiving(param.glblCmpyCd.getValue(), poOrdNum, poDtlLineNum, closeRs.getString(MDSE_CD), NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)), onBatchType, true);
            }
            // QC#22491 End

            // For Closing
            callNPZC004001(rwsCpltWrkT.glblCmpyCd.getValue(), poOrdNum, onBatchType);

            insertRwsPutAwaySer(param, rwsCpltWrkT.rwsNum.getValue(), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getString(RWS_DTL_LINE_NUM), sceOrdTpCd, rwsCpltWrkT.rwsCloDtTmTs.getValue(), closeRs);

            // QC#13811 START
            updateInbdVisDo(msgMap, sceOrdTpCd, poRcvNum, closeRs , NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)));
            // QC#13811 END

            cur = closeRs.next();
        }
        // QC#55313 End
        // Close PO Receiving Header
        closePoHeader(msgMap, poRcvNum);
    }

    /**
     * <pre>
     * Close Order for BB
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException SQLException
     */
    private void closeBb(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType, String sceOrdTpCd) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;

        String poRcvNum = "";

        for (; cur;) {

            // RWS Status check
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // QC#22374
            // Create or Update RWS Put Away
            RWS_PUT_AWAYTMsg rwsPutAwayT = new RWS_PUT_AWAYTMsg();
            rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
            rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
            rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
            rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));

            rwsPutAwayT = (RWS_PUT_AWAYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsPutAwayT);

            if (rwsPutAwayT == null) {

                rwsPutAwayT = new RWS_PUT_AWAYTMsg();
                rwsPutAwayT.glblCmpyCd.setValue(rwsCpltWrkT.glblCmpyCd.getValue());
                rwsPutAwayT.rwsNum.setValue(rwsCpltWrkT.rwsNum.getValue());
                rwsPutAwayT.rwsDtlLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
                rwsPutAwayT.invtyStkStsCd.setValue(closeRs.getString(INVTY_STK_STS_CD));
                rwsPutAwayT.whCd.setValue(closeRs.getString(INVTY_LOC_CD));
                rwsPutAwayT.rwsRefNum.setValue(rwsCpltWrkT.rwsRefNum.getValue());
                rwsPutAwayT.sceOrdTpCd.setValue(rwsCpltWrkT.sceOrdTpCd.getValue());
                rwsPutAwayT.rwsStkQty.setValue(NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)));
                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());
                rwsPutAwayT.mdseCd.setValue(closeRs.getString(MDSE_CD));

                S21ApiTBLAccessor.insert(rwsPutAwayT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                    throw new S21AbendException(NLAM1133E, list);
                }

            } else {

                BigDecimal putAwayRwsStkQty = NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue());
                BigDecimal wrkRwsStkQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY));
                BigDecimal rwsStkQty = putAwayRwsStkQty.add(wrkRwsStkQty);
                rwsPutAwayT.rwsStkQty.setValue(rwsStkQty);
                rwsPutAwayT.rwsStkDtTmTs.setValue(rwsCpltWrkT.rwsCloDtTmTs.getValue());

                S21ApiTBLAccessor.update(rwsPutAwayT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                    String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                            NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                    throw new S21AbendException(NLAM1134E, list);
                }
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, rwsCpltWrkT.rwsNum.getValue(), NLXC014001.nullToEmpty(closeRs.getString(RWS_DTL_LINE_NUM)), rwsPutAwayT.rwsStkQty.getValue());

            // Call Inventory Update API
            // QC#22491 Start
            if (NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue()).compareTo(BigDecimal.ZERO) != 0) {
                NLZC002001PMsg inPMsg = getPmsgBb(param, closeRs);

                NLZC002001 api = new NLZC002001();
                api.execute(inPMsg, onBatchType);

                checkApiErr(msgMap, inPMsg);
            }
            // QC#22491 End

            // Close PO Receiving Detail
            poRcvNum = NLXC014001.nullToEmpty(closeRs.getString(TRX_ORD_NUM));
            closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)), closeRs.getBigDecimal(RWS_QTY).subtract(rwsPutAwayT.rwsStkQty.getValue()), sceOrdTpCd);

            // Call PO Status Update API
            String poOrdNum = closeRs.getString(PO_ORD_NUM);
            String poDtlLineNum = closeRs.getString(PO_ORD_DTL_LINE_NUM);

            // for Receiving
            // QC#22491 Start
            if (NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue()).compareTo(BigDecimal.ZERO) != 0) {
                callNPZC004001ForReceiving(param.glblCmpyCd.getValue(), poOrdNum, poDtlLineNum, closeRs.getString(MDSE_CD), NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_STK_QTY)), onBatchType, false);
            }
            // QC#22491 End

            if (isAllRwsClosePO(param.glblCmpyCd.getValue(), poOrdNum)) {

                // for Closing
                callNPZC004001(rwsCpltWrkT.glblCmpyCd.getValue(), poOrdNum, onBatchType);
            }

            insertRwsPutAwaySer(param, rwsCpltWrkT.rwsNum.getValue(), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getString(RWS_DTL_LINE_NUM), sceOrdTpCd, rwsCpltWrkT.rwsCloDtTmTs.getValue(), closeRs);

            // QC#13811 START
            updateInbdVisDo(msgMap, sceOrdTpCd, poRcvNum, closeRs, rwsPutAwayT.rwsStkQty.getValue());
            // QC#13811 END

            cur = closeRs.next();
        }

        // Close PO Receiving Header
        closePoHeader(msgMap, poRcvNum);
    }

    /**
     * <pre>
     * Check All RWS closed or not for PO
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @return boolean
     */
    private boolean isAllRwsClosePO(String glblCmpyCd, String poOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poOrdNum", poOrdNum);
        queryParam.put("rwsOpenFlg", ZYPConstant.FLG_ON_Y);

        BigDecimal openRwsCnt = (BigDecimal) ssmBatchClient.queryObject("getOpenRwsCntPO", queryParam);

        if (BigDecimal.ZERO.compareTo(openRwsCnt) < 0) {

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Close Order for DP, DG and DN
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     * @param sceOrdTpCd String
     * @throws SQLException SQLException
     */
    private void closeDpDgDn(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType, String sceOrdTpCd) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;
        String poRcvNum = "";
        String poOrdNum = "";

        for (; cur;) {

            poRcvNum = NLXC014001.nullToEmpty(closeRs.getString(TRX_ORD_NUM));
            poOrdNum = NLXC014001.nullToEmpty(closeRs.getString(PO_ORD_NUM));

            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, closeRs.getString(RWS_NUM), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));

            // Calculate Loss Qty
            BigDecimal rwsQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_QTY));
            BigDecimal rwsPutAwayQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));
            BigDecimal lossQty = rwsQty.subtract(rwsPutAwayQty);

            // Call Inventory Update API
            if (NLXSceConst.SCE_ORD_TP_CD_DG.equals(sceOrdTpCd)) {

                NLZC002001PMsg pMsg = getPmsgDg(param, closeRs, lossQty);

                NLZC002001 api = new NLZC002001();
                api.execute(pMsg, onBatchType);

                checkApiErr(msgMap, pMsg);
                
                // QC#29030 Update INBD_VIS
                if(BigDecimal.ZERO.compareTo(lossQty) < 0 ){
                    insertInbdVisForLossQty(msgMap, closeRs, lossQty, poRcvNum);
                }
            }

            // Close PO Receiving Detail
            closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)), lossQty, sceOrdTpCd);

            cur = closeRs.next();
        }

        // Close PO Receiving Header
        closePoHeader(msgMap, poRcvNum);

        // Call PO Status Update API
        callNPZC004001(param.glblCmpyCd.getValue(), poOrdNum, onBatchType);
    }

    /**
    /**
     * <pre>
     * Close Order for DT
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param onBatchType ONBATCH_TYPE
     * @param sceOrdTpCd String
     * @throws SQLException SQLException
     */
    private void closeDt(final S21ApiMessageMap msgMap, final ResultSet closeRs, final ONBATCH_TYPE onBatchType, String sceOrdTpCd) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;
        String poRcvNum = "";
        String cpoOrdNum = "";
        // QC#27092 ADD START
        String prchReqNum = "";
        String prchReqLineNum = "";
        BigDecimal prchReqLineSubNum = BigDecimal.ZERO;
        String rwsNum = "";
        // QC#27092 ADD END
        

        for (; cur;) {

            poRcvNum = NLXC014001.nullToEmpty(closeRs.getString(TRX_ORD_NUM));
            cpoOrdNum = NLXC014001.nullToEmpty(closeRs.getString(CPO_ORD_NUM));

            // QC#27092 ADD START
            prchReqNum = NLXC014001.nullToEmpty(closeRs.getString(PRCH_REQ_NUM));
            prchReqLineNum = NLXC014001.nullToEmpty(closeRs.getString(PRCH_REQ_LINE_NUM));
            prchReqLineSubNum = NLXC014001.nullToZero(closeRs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
            rwsNum = NLXC014001.nullToEmpty(closeRs.getString(RWS_NUM));
            // QC#27092 ADD END
            
            // Check RWS Status
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, closeRs.getString(RWS_NUM), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));

            // Calculate Loss Qty
            BigDecimal rwsQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_QTY));
            BigDecimal rwsPutAwayQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));
            BigDecimal lossQty = rwsQty.subtract(rwsPutAwayQty);

            // Call Inventory Update API
            NLZC002001PMsg pMsg = getPmsgDt(param, closeRs, lossQty);

            NLZC002001 api = new NLZC002001();
            api.execute(pMsg, onBatchType);

            checkApiErr(msgMap, pMsg);

            // Close PO Receiving Detail
            closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)), lossQty, sceOrdTpCd);

            // Close CPO Detail
            if (ZYPConstant.FLG_ON_Y.equals(closeRs.getString(ORD_LINE_OPEN_FLG)) || isOpenOrdLineSts(closeRs.getString(ORD_LINE_STS_CD))) {

                closeCpoDtailForDt(msgMap, closeRs.getString(CPO_ORD_NUM), closeRs.getString(CPO_DTL_LINE_NUM), closeRs.getString(CPO_DTL_LINE_SUB_NUM));
            }

            
            // QC#27092 ADD START
            // call Purchase Requisition Update API (NPZC1030) Close
            // Inventory Request
            callNPZC103001(param.glblCmpyCd.getValue(), prchReqNum, prchReqLineNum, prchReqLineSubNum, rwsNum, null, lossQty, sceOrdTpCd, onBatchType);
            // QC#27092 ADD END
            
            cur = closeRs.next();
        }

        // Close PO Receiving Header
        closePoHeader(msgMap, poRcvNum);

        // Close CPO Header
        closeCpoHeaderForDt(msgMap, cpoOrdNum);

    }

    /**
     * isOpenOrdLineSts
     * @param ordLineStsCd String
     * @return boolean true : open, false : close
     */
    private boolean isOpenOrdLineSts(String ordLineStsCd) {

        if (ORD_LINE_STS.VALIDATED.equals(ordLineStsCd)) {

            return true;

        } else if (ORD_LINE_STS.PARTIALLY_SHIPPED.equals(ordLineStsCd)) {

            return true;

        } else if (ORD_LINE_STS.SHIPPED.equals(ordLineStsCd)) {

            return true;
        }

        return false;
    }

    /**
     * <pre>
     * Close Order for TR
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param onBatchType ONBATCH_TYPE
     * @param sceOrdTpCd String
     * @throws SQLException SQLException
     */
    private void closeTr(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType, String sceOrdTpCd) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;
        String poRcvNum = "";
        List<String> invtyOrdList = new ArrayList<String>();

        for (; cur;) {

            poRcvNum = NLXC014001.nullToEmpty(closeRs.getString(TRX_ORD_NUM));

            String invtyOrdNum = NLXC014001.nullToEmpty(closeRs.getString(INVTY_ORD_NUM));

            if (ZYPCommonFunc.hasValue(invtyOrdNum) && !invtyOrdList.contains(invtyOrdNum)) {

                invtyOrdList.add(invtyOrdNum);
            }

            // Check RWS Status
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, closeRs.getString(RWS_NUM), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));

            // Calculate Loss Qty
            BigDecimal rwsQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_QTY));
            BigDecimal rwsPutAwayQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));
            BigDecimal lossQty = rwsQty.subtract(rwsPutAwayQty);

            // Call Inventory Update API
            if (LOC_TP.TECHNICIAN.equals(closeRs.getString(LOC_TP_CD))) {

                NLZC002001PMsg pMsg = getPmsgTrToTech(param, closeRs, lossQty);

                NLZC002001 api = new NLZC002001();
                api.execute(pMsg, onBatchType);

                checkApiErr(msgMap, pMsg);

            } else {

                List<NLZC002001PMsg> pMsgList = getPmsgTr(param, closeRs, lossQty);

                NLZC002001 api = new NLZC002001();
                api.execute(pMsgList, onBatchType);

                for (NLZC002001PMsg msg : pMsgList) {

                    checkApiErr(msgMap, msg);
                }

                // Loss Qty is not a positive number.
                if (BigDecimal.ZERO.compareTo(lossQty) < 0) {

                    // Call PR Update API
                    String rwsNum = NLXC014001.nullToEmpty(closeRs.getString(RWS_NUM));
                    String prchReqNum = NLXC014001.nullToEmpty(closeRs.getString(PRCH_REQ_NUM));
                    String prchReqLineNum = NLXC014001.nullToEmpty(closeRs.getString(PRCH_REQ_LINE_NUM));
                    BigDecimal prchReqLineSubNum = closeRs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);
                    // QC#25867
                    callNPZC103001(param.glblCmpyCd.getValue(), prchReqNum, prchReqLineNum, prchReqLineSubNum, rwsNum, rwsCpltWrkT.rwsCloDtTmTs.getValue(), lossQty, sceOrdTpCd, onBatchType);
                }
            }

            // Close PO Receiving Detail
            closePoDetail(msgMap, poRcvNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)), lossQty, sceOrdTpCd);

            // Call Inventory Order Update API for Detail Line
            callNLZC003001(param.glblCmpyCd.getValue(), invtyOrdNum, closeRs.getString(INVTY_ORD_LINE_NUM), null, onBatchType, false, NLZC003001.PROC_TP_CLO);

            cur = closeRs.next();
        }

        // Close PO Receiving Header
        closePoHeader(msgMap, poRcvNum);

        // Call Inventory Order Update API for Header
        callNLZC003001(param.glblCmpyCd.getValue(), "", "", invtyOrdList, onBatchType, true, NLZC003001.PROC_TP_HDR_CLO);
    }

    /**
     * <pre>
     * Close Order for RT
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param rwsCpltWrkT RWS_CPLT_WRKTMsg
     * @throws SQLException SQLException
     */
    private void closeRt(final S21ApiMessageMap msgMap, final ResultSet closeRs, final RWS_CPLT_WRKTMsg rwsCpltWrkT, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();
        boolean cur = true;

        // QC#63527 2024/03/14 Start
        String loanInvtyLocCd = null;

        if (TRX.MOVEMENT.equals(closeRs.getString(TRX_CD))
                && TRX_RSN.TRIAL_TO_INVENTORY_STOCK_IN.equals(closeRs.getString(TRX_RSN_CD))) {
            loanInvtyLocCd = ZYPCodeDataUtil.getVarCharConstValue("LOAN_DUMMY_CSA_WH_CD", param.glblCmpyCd.getValue());
        } else if (TRX.MOVEMENT.equals(closeRs.getString(TRX_CD))
                && TRX_RSN.OFF_THE_BOOK_TRIAL_TO_INVENTORY_STOCK_IN.equals(closeRs.getString(TRX_RSN_CD))) {
            loanInvtyLocCd = ZYPCodeDataUtil.getVarCharConstValue("LOAN_DUMMY_GMD_WH_CD", param.glblCmpyCd.getValue());
        }
        // QC#63527 2024/03/14 end

        for (; cur;) {

            // Check RWS Status
            String rwsStsCd = NLXC014001.nullToEmpty(closeRs.getString(RWS_STS_CD));

            if (!RWS_STS.PRINTED.equals(rwsStsCd) && !RWS_STS.RECEIVING.equals(rwsStsCd)) {

                cur = closeRs.next();
                continue;
            }

            // Close RWS Detail
            closeRwsDetail(msgMap, closeRs.getString(RWS_NUM), closeRs.getString(RWS_DTL_LINE_NUM), closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));

            // Calculate Loss Qty
            BigDecimal rwsQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_QTY));
            BigDecimal rwsPutAwayQty = NLXC014001.nullToZero(closeRs.getBigDecimal(RWS_PUT_AWAY_QTY));
            BigDecimal lossQty = rwsQty.subtract(rwsPutAwayQty);

            // Close Return Order
            if (TRX.RENTAL_SHIPMENT.equals(closeRs.getString(TRX_CD))
                    // QC#63527 2024/03/14 Start
                    || (TRX.MOVEMENT.equals(closeRs.getString(TRX_CD)) && TRX_RSN.TRIAL_TO_INVENTORY_STOCK_IN.equals(closeRs.getString(TRX_RSN_CD)))
                    || (TRX.MOVEMENT.equals(closeRs.getString(TRX_CD)) && TRX_RSN.OFF_THE_BOOK_TRIAL_TO_INVENTORY_STOCK_IN.equals(closeRs.getString(TRX_RSN_CD)))) {
                    // QC#63527 2024/03/14 End

                if (BigDecimal.ZERO.compareTo(lossQty) < 0) {

                    // QC#16556
                    if (!TRX.RENTAL_SHIPMENT.equals(closeRs.getString(TRX_CD))) {

                        // Call Inventory Update API
                        NLZC002001PMsg pMsg = getPmsgRt(param, closeRs, lossQty, loanInvtyLocCd);

                        NLZC002001 api = new NLZC002001();
                        api.execute(pMsg, onBatchType);

                        checkApiErr(msgMap, pMsg);
                    }

                    // Call CPO Return Update API (NWZC1530) for shortage
                    callNWZC153001(param, closeRs.getString(TRX_ORD_NUM), closeRs.getString(TRX_LINE_NUM), closeRs.getString(TRX_LINE_SUB_NUM), onBatchType, NWZC153001Constant.RQST_DTL_SHORTAGE_CLOSED);
                }

            } else {

                if (BigDecimal.ZERO.equals(closeRs.getBigDecimal(RTRN_QTY))) {

                    // Call CPO Return Update API (NWZC1530)
                    callNWZC153001(param, closeRs.getString(TRX_ORD_NUM), closeRs.getString(TRX_LINE_NUM), closeRs.getString(TRX_LINE_SUB_NUM), onBatchType, NWZC153001Constant.RQST_DTL_RWC_CANCEL);
                }
            }

            cur = closeRs.next();
        }
    }

    /**
     * <pre>
     * Close RWS Detail
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @param rwsPutAwayQty BigDecimal
     */
    private void closeRwsDetail(final S21ApiMessageMap msgMap, final String rwsNum, final String rwsDtlLineNum, final BigDecimal rwsPutAwayQty) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();
        rwsDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        rwsDtlT.rwsNum.setValue(rwsNum);
        rwsDtlT.rwsDtlLineNum.setValue(rwsDtlLineNum);

        rwsDtlT = (RWS_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsDtlT);

        if (rwsDtlT != null) {
            ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsStsCd, RWS_STS.RECEIVED);
            ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsPutAwayQty, rwsPutAwayQty);
            ZYPEZDItemValueSetter.setValue(rwsDtlT.schdCoordStsCd, SCHD_COORD_STS.CLOSED);

            S21ApiTBLAccessor.update(rwsDtlT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {

                String[] list = new String[] {rwsDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                        NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd.getValue(), rwsDtlT.rwsNum.getValue(), rwsDtlT.rwsDtlLineNum.getValue()) };
                throw new S21AbendException(NLAM1134E, list);
            }

            RWS_SCHD_DTL_TRKTMsg rwsSchdDtlTrkT = new RWS_SCHD_DTL_TRKTMsg();
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.rwsSchdDtlTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RWS_SCHD_DTL_TRK_SQ));
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.updUsrId, rwsDtlT.ezUpUserID.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.updTs, rwsDtlT.ezUpTime.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.rwsNum, rwsDtlT.rwsNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.rwsDtlLineNum, rwsDtlT.rwsDtlLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.schdCoordStsCd, rwsDtlT.schdCoordStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.schdCoordPsnCd, rwsDtlT.schdCoordPsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.schdPickUpDt, rwsDtlT.schdPickUpDt.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.carrCd, rwsDtlT.carrCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.shpgSvcLvlCd, rwsDtlT.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.rtrnTrkStsCd, rwsDtlT.rtrnTrkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkT.rtrnTrkNtfyGrpCd, rwsDtlT.rtrnTrkNtfyGrpCd.getValue());
            S21ApiTBLAccessor.insert(rwsSchdDtlTrkT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsSchdDtlTrkT.getReturnCode())) {

                String[] list = new String[] {rwsSchdDtlTrkT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                        NLXCMsgHelper.toListedString(rwsSchdDtlTrkT.glblCmpyCd.getValue(), rwsSchdDtlTrkT.rwsNum.getValue(), rwsSchdDtlTrkT.rwsDtlLineNum.getValue()) };
                throw new S21AbendException(NLAM1133E, list);
            }
        }
    }

    /**
     * closeCpoDtailForDt
     * @param msgMap S21ApiMessageMap
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     */
    private void closeCpoDtailForDt(final S21ApiMessageMap msgMap, final String cpoOrdNum, final String cpoDtlLineNum, final String cpoDtlLineSubNum) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("cpoDtlLineNum", cpoDtlLineNum);
        queryParam.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        queryParam.put("shipPlnCancFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("shipFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("rwsOpenFlgY", ZYPConstant.FLG_ON_Y);

        Integer openShpgPlanAndRwsDtlCnt = (Integer) ssmBatchClient.queryObject("queryCpoDtlUpdateJudgement", queryParam);

        if (openShpgPlanAndRwsDtlCnt == 0) {

            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
            cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);

            if (cpoDtlTMsg == null) {

                String[] list = new String[] {CPO_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM, CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum) };
                throw new S21AbendException(NLAM1001E, list);
            }

            // Open Flag
            if (!ZYPConstant.FLG_OFF_N.equals(cpoDtlTMsg.openFlg.getValue())) {

                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.openFlg, ZYPConstant.FLG_OFF_N);
            }

            // Line Status
            if (!ORD_LINE_STS.CLOSED.equals(cpoDtlTMsg.ordLineStsCd.getValue()) && !ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg.ordLineStsCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ordLineStsCd, ORD_LINE_STS.CLOSED);
            }

            S21ApiTBLAccessor.update(cpoDtlTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {

                String[] list = new String[] {CPO_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM, CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum) };
                throw new S21AbendException(NLAM1134E, list);
            }

            // Process Log Output
            final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
            bizProcLogMsg.setSubSysId("NLZ");
            bizProcLogMsg.setProcId("OM");
            bizProcLogMsg.setEventId("Close");
            bizProcLogMsg.setDocTpCd("OM");
            bizProcLogMsg.setDocId(cpoDtlLineNum + "." + cpoDtlLineSubNum);
            bizProcLogMsg.setPrntDocId(cpoOrdNum);
            S21BusinessProcessLog.print(bizProcLogMsg);
        }
    }

    /**
     * closeCpoDtailForDt
     * @param msgMap S21ApiMessageMap
     * @param cpoOrdNum String
     */
    private void closeCpoHeaderForDt(final S21ApiMessageMap msgMap, final String cpoOrdNum) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("ordLineStsCdClosed", ORD_LINE_STS.CLOSED);
        queryParam.put("ordLineStsCdCancelled", ORD_LINE_STS.CANCELLED);

        Integer openOrdLineCnt = (Integer) ssmBatchClient.queryObject("queryCpoUpdateJudgement", queryParam);

        if (openOrdLineCnt == 0) {

            CPOTMsg cpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
            cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsg);

            if (cpoTMsg == null) {

                String[] list = new String[] {CPO, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), cpoOrdNum) };
                throw new S21AbendException(NLAM1001E, list);
            }

            ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, ORD_HDR_STS.CLOSED);
            S21ApiTBLAccessor.update(cpoTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {

                String[] list = new String[] {CPO, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), cpoOrdNum) };
                throw new S21AbendException(NLAM1134E, list);
            }

            // Process Log Output
            final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
            bizProcLogMsg.setSubSysId("NLZ");
            bizProcLogMsg.setProcId("OM");
            bizProcLogMsg.setEventId("Close");
            bizProcLogMsg.setDocTpCd("OM");
            bizProcLogMsg.setPrntDocId(cpoOrdNum);
            S21BusinessProcessLog.print(bizProcLogMsg);
        }
    }

    /**
     * <pre>
     * closePoDetail
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param poRcvNum String
     * @param poRcvLineNum String
     * @param lossQty BigDecimal
     * @param sceOrdTpCd String
     */
    private void closePoDetail(final S21ApiMessageMap msgMap, final String poRcvNum, final String poRcvLineNum, BigDecimal lossQty, String sceOrdTpCd) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        PO_RCV_DTLTMsg poRcvDtlT = new PO_RCV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvNum, poRcvNum);
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvLineNum, poRcvLineNum);
        poRcvDtlT = (PO_RCV_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poRcvDtlT);

        if (poRcvDtlT == null) {

            String[] list = new String[] {PO_RCV_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM)
                    , NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), poRcvNum, poRcvLineNum) };
            throw new S21AbendException(NLAM1001E, list);
        }

        if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_KC.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {

            ZYPEZDItemValueSetter.setValue(poRcvDtlT.lossQty, lossQty);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.rwsStsCd, RWS_STS.RECEIVED);

            BigDecimal poRcvQty = (poRcvDtlT.poRcvQty.getValue()).subtract(lossQty);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.actlRcvQty, poRcvQty);

        } else {

            BigDecimal lossQtySum = lossQty.add(poRcvDtlT.lossQty.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.lossQty, lossQtySum);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.rwsStsCd, RWS_STS.RECEIVED);

            // QC# 13811 START
            BigDecimal poRcvQty = (poRcvDtlT.poRcvQty.getValue()).subtract(lossQtySum);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.actlRcvQty, poRcvQty);
            // QC# 13811 END
        }


        S21ApiTBLAccessor.update(poRcvDtlT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvDtlT.getReturnCode())) {

            String[] list = new String[] {PO_RCV_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), poRcvNum, poRcvLineNum) };
            throw new S21AbendException(NLAM1134E, list);
        }
 
    }

    /**
     * <pre>
     * closePoDetail
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param poRcvNum String
     * @param poRcvLineNum String
     */
    private void closePoDetail(final S21ApiMessageMap msgMap, final String poRcvNum, final String poRcvLineNum) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        PO_RCV_DTLTMsg poRcvDtlT = new PO_RCV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvNum, poRcvNum);
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvLineNum, poRcvLineNum);
        poRcvDtlT = (PO_RCV_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poRcvDtlT);

        if (poRcvDtlT == null) {

            String[] list = new String[] {PO_RCV_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), poRcvNum, poRcvLineNum) };
            throw new S21AbendException(NLAM1001E, list);
        }

        ZYPEZDItemValueSetter.setValue(poRcvDtlT.rwsStsCd, RWS_STS.RECEIVED);

        // QC# 13811 START
        BigDecimal poRcvQty = (poRcvDtlT.poRcvQty.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.actlRcvQty, poRcvQty);
        // QC# 13811 END

        S21ApiTBLAccessor.update(poRcvDtlT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvDtlT.getReturnCode())) {

            String[] list = new String[] {PO_RCV_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), poRcvNum, poRcvLineNum) };
            throw new S21AbendException(NLAM1134E, list);
        }
    }

    /**
     * <pre>
     * closePoHeader
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param poRcvNum String
     */
    private void closePoHeader(final S21ApiMessageMap msgMap, final String poRcvNum) {

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        PO_RCV_HDRTMsg poRcvHdrT = new PO_RCV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvNum, poRcvNum);
        poRcvHdrT = (PO_RCV_HDRTMsg) S21ApiTBLAccessor.findByKey(poRcvHdrT);

        if (poRcvHdrT == null) {

            String[] list = new String[] {PO_RCV_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), poRcvNum) };
            throw new S21AbendException(NLAM1001E, list);
        }

        ZYPEZDItemValueSetter.setValue(poRcvHdrT.rwsStsCd, RWS_STS.RECEIVED);
        S21ApiTBLAccessor.update(poRcvHdrT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvHdrT.getReturnCode())) {

            String[] list = new String[] {PO_RCV_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), poRcvNum) };
            throw new S21AbendException(NLAM1134E, list);
        }
    }

    /**
     * <pre>
     * <ul>
     * Set Inventory Update API parameter to stock out by SO
     * </ul>
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param shpgOrdT SHPG_ORDTMsg
     * @param shpgOrdDtlT SHPG_ORD_DTLTMsg
     * @param rwsHdrT RWS_HDRTMsg
     * @param sceOrdTpCd String
     * @return NLZC002001PMsg
     */
    private NLZC002001PMsg getPmsgCloseSo(final NLZC209001PMsg param, final SHPG_ORDTMsg shpgOrdT, final SHPG_ORD_DTLTMsg shpgOrdDtlT,
            final RWS_HDRTMsg rwsHdrT, final String sceOrdTpCd) {

        NLZC002001PMsg outPMsg = new NLZC002001PMsg();
        outPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        outPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        outPMsg.mdseCd.setValue(shpgOrdDtlT.mdseCd.getValue());
        outPMsg.invtyLocCd.setValue(shpgOrdDtlT.invtyLocCd.getValue());
        outPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        outPMsg.stkStsCd.setValue(shpgOrdDtlT.fromStkStsCd.getValue());
        outPMsg.xxRqstQty.setValue(shpgOrdDtlT.shpgQty.getValue());
        outPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        outPMsg.sysSrcCd.setValue(rwsHdrT.sysSrcCd.getValue());
        outPMsg.soNum.setValue(shpgOrdDtlT.soNum.getValue());
        outPMsg.soSlpNum.setValue(shpgOrdDtlT.soSlpNum.getValue());
        outPMsg.ccyCd.setValue(stdCcyCd);
        outPMsg.uomCd.setValue(PKG_UOM.EACH);

        if (NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd)) {

            // Item Change Stock Out
            outPMsg.trxCd.setValue(TRX.ADJUSTMENT);
            outPMsg.trxRsnCd.setValue(TRX_RSN.ITEM_CHANGE_STOCK_OUT);
            outPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_ITM_CHNG_SCR);
            outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            outPMsg.invtyOrdNum.setValue(shpgOrdDtlT.trxHdrNum.getValue());
            outPMsg.invtyOrdLineNum.setValue(shpgOrdDtlT.trxLineNum.getValue());

        } else if (NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)) {

            // Configuration Change Stock Out
            outPMsg.trxCd.setValue(TRX.MOVEMENT);
            outPMsg.trxRsnCd.setValue(TRX_RSN.CONFIG_CHANGE_STOCK_OUT);
            outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            outPMsg.invtyOrdNum.setValue(shpgOrdDtlT.trxHdrNum.getValue());
            outPMsg.invtyOrdLineNum.setValue(shpgOrdDtlT.trxLineNum.getValue());

        } else if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd)) {

            // Status Change Stock Out
            outPMsg.trxCd.setValue(TRX.MOVEMENT);
            outPMsg.trxRsnCd.setValue(TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT);
            outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            outPMsg.invtyOrdNum.setValue(shpgOrdDtlT.trxHdrNum.getValue());
            outPMsg.invtyOrdLineNum.setValue(shpgOrdDtlT.trxLineNum.getValue());

        } else if (NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd)) {

            // Sub Warehouse Change Stock Out
            outPMsg.trxCd.setValue(TRX.INVENTORY_VALUATION);
            outPMsg.trxRsnCd.setValue(TRX_RSN.SWH_TRANSFER_STOCK_OUT);
            outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            outPMsg.invtyOrdNum.setValue(shpgOrdDtlT.trxHdrNum.getValue());
            outPMsg.invtyOrdLineNum.setValue(shpgOrdDtlT.trxLineNum.getValue());
            outPMsg.shipToCustCd.setValue(shpgOrdDtlT.toInvtyLocCd.getValue());

        } else if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {

            // Buy Back Stock Out
            outPMsg.trxCd.setValue(TRX.ADJUSTMENT);
            outPMsg.trxRsnCd.setValue(TRX_RSN.BUY_BACK_STOCK_OUT);
            outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            outPMsg.poOrdNum.setValue(shpgOrdDtlT.trxHdrNum.getValue());
            outPMsg.poOrdDtlLineNum.setValue(shpgOrdDtlT.trxLineNum.getValue());
            outPMsg.shipToCustCd.setValue(shpgOrdDtlT.toInvtyLocCd.getValue());

        } else if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)) {

            // Internal Warehouse Transfer Stock Out
            outPMsg.trxCd.setValue(TRX.MOVEMENT);
            outPMsg.trxRsnCd.setValue(TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT);
            outPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OTBD);
            outPMsg.invtyOrdNum.setValue(shpgOrdDtlT.trxHdrNum.getValue());
            outPMsg.invtyOrdLineNum.setValue(shpgOrdDtlT.trxLineNum.getValue());
            outPMsg.invtyTrxSlpNum.setValue(shpgOrdDtlT.trxHdrNum.getValue());
            outPMsg.shipToCustCd.setValue(shpgOrdDtlT.toInvtyLocCd.getValue());
            outPMsg.shipFromLocCustCd.setValue(shpgOrdDtlT.invtyLocCd.getValue());
        }

        // Set Serial number
        List<String> serNumList = null;

        if (NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)) {

            serNumList = getShipSerNumListIcCcScSwIT(param, shpgOrdDtlT);

        } else if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {

            serNumList = getShipSerNumListBb(param, shpgOrdDtlT);
        }

        if (serNumList != null) {

            for (int i = 0; i < serNumList.size(); i++) {

                ZYPEZDItemValueSetter.setValue(outPMsg.serNumList.no(i).serNum, serNumList.get(i));
            }

            outPMsg.serNumList.setValidCount(serNumList.size());
        }

        return outPMsg;
    }

    /**
     * <pre>
     * <ul>
     * Set Inventory Update API parameter to stock out for RM by SO
     * </ul>
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param shpgOrdT SHPG_ORDTMsg
     * @param shpgOrdDtlT SHPG_ORD_DTLTMsg
     * @param rwsHdrT RWS_HDRTMsg
     * @return NLZC002001PMsg
     */
    // QC#21574 UPD START
    private NLZC002001PMsg getPmsgCloseSoForReman(final NLZC209001PMsg param, final SHPG_ORDTMsg shpgOrdT, final SHPG_ORD_DTLTMsg shpgOrdDtlT, final RWS_HDRTMsg rwsHdrT, String sceOrdTpCd) {

        // Reman Item Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();
        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.REMANUFACTURING);
            // QC#22834 Start
            if (isRemanMainUnit(param, shpgOrdDtlT.trxHdrNum.getValue(), shpgOrdDtlT.trxLineNum.getValue())) {
                ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT);
            } else {
                ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT_ACSRY);
            }
            // QC#22834 End
            ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.DC_STOCK);
        } else {
            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_REMAN);
        }
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, shpgOrdDtlT.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, shpgOrdDtlT.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, shpgOrdDtlT.fromStkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, shpgOrdDtlT.shpgQty.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, rwsHdrT.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.soNum, shpgOrdDtlT.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.soSlpNum, shpgOrdDtlT.soSlpNum.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdNum, shpgOrdDtlT.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdLineNum, shpgOrdDtlT.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxSlpNum, shpgOrdDtlT.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);
        // QC#21574 UPD END

        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
            // Set Serial number
            List<String> serNumList = getShipSerNumListRm(param, shpgOrdDtlT);

            if (serNumList != null) {

                for (int i = 0; i < serNumList.size(); i++) {

                    ZYPEZDItemValueSetter.setValue(outPMsg.serNumList.no(i).serNum, serNumList.get(i));
                }

                outPMsg.serNumList.setValidCount(serNumList.size());
            }
        }

        return outPMsg;
    }

    /**
     * <pre>
     * set Inventory Update API PMsg for SC, IC, SW and IT
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param sceOrdTpCd SCE_ORD_TP_CD
     * @return NLZC002001PMsg
     * @throws SQLException SQLException
     */
    private NLZC002001PMsg getPmsgScIcCcSwIt(final NLZC209001PMsg param, final ResultSet closeRs, final String sceOrdTpCd) throws SQLException {

        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, closeRs.getBigDecimal(RWS_STK_QTY));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.soNum, closeRs.getString(SO_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.soSlpNum, closeRs.getString(SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyOrdNum, closeRs.getString(INVTY_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyOrdLineNum, closeRs.getString(INVTY_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        // Set Serial number
        RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
        rwsPutAwaySerWrkT.setSQLID("001");
        rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", closeRs.getString(WRK_TRX_ID));
        rwsPutAwaySerWrkT.setConditionValue("sqId01", closeRs.getString(SQ_ID));
        rwsPutAwaySerWrkT.setConditionValue("rwsNum01", closeRs.getString(RWS_NUM));
        rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", closeRs.getString(RWS_DTL_LINE_NUM));

        RWS_PUT_AWAY_SER_WRKTMsgArray msgArr = (RWS_PUT_AWAY_SER_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(rwsPutAwaySerWrkT);

        if (msgArr != null) {

            for (int i = 0; i < msgArr.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, msgArr.no(i).serNum);
            }

            inPMsg.serNumList.setValidCount(msgArr.getValidCount());
        }

        if (NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd)) {

            // Item Change Stock In
            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.ITEM_CHANGE_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_ITM_CHNG_SCR);

        } else if (NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)) {

            // Item Change Stock In
            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.CONFIG_CHANGE_STOCK_IN);
        
        } else if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd)) {

            // Status Change Stock In
            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.STOCK_STATUS_CHANGE_STOCK_IN);

        } else if (NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd)) {

            // Sub Warehouse Change Stock In
            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.INVENTORY_VALUATION);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.SWH_TRANSFER_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.shipFromLocCustCd, closeRs.getString(SHIP_FROM_INVTY_LOC_CD));

        } else if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)) {

            // Internal Warehouse Transfer Stock In
            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxSlpNum, closeRs.getString(INVTY_TRX_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(inPMsg.shipFromLocCustCd, closeRs.getString(SHIP_FROM_INVTY_LOC_CD));
        }

        return inPMsg;
    }

    /**
     * <pre>
     * <li>Refurbish Vendor Transfer Stock-Out from Vendor</li>
     * </pre>
     * @param param NLZC002001PMsg
     * @param shipMdseRpInfoList List<Map<String, Object>>
     * @return List<NLZC002001PMsg>
     * @throws SQLException SQLException
     */
    private List<NLZC002001PMsg> getPmsgRpForStkOut(final NLZC209001PMsg param, final List<Map<String, Object>> shipMdseRpInfoList) throws SQLException {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        for (Map<String, Object> shipMdseRpInfo : shipMdseRpInfoList) {

            // Refurb Vendor Transfer Stock-Out from Vendor
            NLZC002001PMsg outPMsg = new NLZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR);
            ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, (String) shipMdseRpInfo.get(SHIP_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, (String) shipMdseRpInfo.get(TO_INVTY_LOC_CD));
            // QC#26585 MOD START
            //ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.IN_TRANSIT);
            ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
            // QC#26585 MOD START
            ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, (String) shipMdseRpInfo.get(FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, (BigDecimal) shipMdseRpInfo.get(SHIP_QTY));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, (String) shipMdseRpInfo.get(SYS_SRC_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, (String) shipMdseRpInfo.get(TRX_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, (String) shipMdseRpInfo.get(TRX_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, (String) shipMdseRpInfo.get(RWS_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, (String) shipMdseRpInfo.get(RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, (String) shipMdseRpInfo.get(RWS_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.soNum, (String) shipMdseRpInfo.get(SO_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.soSlpNum, (String) shipMdseRpInfo.get(SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.poOrdNum, (String) shipMdseRpInfo.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.poOrdDtlLineNum, (String) shipMdseRpInfo.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdNum, (String) shipMdseRpInfo.get(INVTY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdLineNum, (String) shipMdseRpInfo.get(INVTY_ORD_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.shipToCustCd, (String) shipMdseRpInfo.get(TO_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.vndCd, (String) shipMdseRpInfo.get(VND_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
            ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

            pMsgList.add(outPMsg);
        }

        return pMsgList;
    }

    /**
     * <pre>
     * <li>Purchase Stock In</li>
     * </pre>
     * 
     * @param param NLZC002001PMsg
     * @param closeRs ResultSet
     * @return List<NLZC002001PMsg>
     * @throws SQLException SQLException
     */
    private List<NLZC002001PMsg> getPmsgRpForStkIn(final NLZC209001PMsg param, final ResultSet closeRs) throws SQLException {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        // Purchase Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, closeRs.getBigDecimal(RWS_STK_QTY));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_PROCR);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdNum, closeRs.getString(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdDtlLineNum, closeRs.getString(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.vndCd, closeRs.getString(VND_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        // Set Serial number
        RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
        rwsPutAwaySerWrkT.setSQLID("001");
        rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", closeRs.getString(WRK_TRX_ID));
        rwsPutAwaySerWrkT.setConditionValue("sqId01", closeRs.getString(SQ_ID));
        rwsPutAwaySerWrkT.setConditionValue("rwsNum01", closeRs.getString(RWS_NUM));
        rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", closeRs.getString(RWS_DTL_LINE_NUM));

        RWS_PUT_AWAY_SER_WRKTMsgArray msgArr = (RWS_PUT_AWAY_SER_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(rwsPutAwaySerWrkT);

        if (msgArr != null) {

            for (int i = 0; i < msgArr.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, msgArr.no(i).serNum);
            }

            inPMsg.serNumList.setValidCount(msgArr.getValidCount());
        }

        pMsgList.add(inPMsg);

        return pMsgList;
    }

    /**
     * <pre>
     * <ul>
     * <li>Inbound In-Transit Stock Out</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC002001PMsg
     * @param closeRs ResultSet
     * @param lossQty LOSS_QTY
     * @return NLZC002001PMsg
     * @throws SQLException SQLException
     */
    private NLZC002001PMsg getPmsgDg(final NLZC209001PMsg param, final ResultSet closeRs, final BigDecimal lossQty) throws SQLException {

        // Inbound In-Transit Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.INBOUND_IN_TRANSIT_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_DOM);
        ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.IN_TRANSIT);
        // QC#22485 Add
        ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(RWS_INVTY_STK_STS_CD));
        // QC#22485 End
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, lossQty);
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.poOrdNum, closeRs.getString(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.poOrdDtlLineNum, closeRs.getString(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxSlpNum, closeRs.getString(DOM_INV_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.vndCd, closeRs.getString(FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

        return outPMsg;
    }

    /**
     * getPmsgKtKuKcForStkOut
     * @param param NLZC209001PMsg
     * @param shipMdseKtKuInfoList List<Map<String, Object>>
     * @param sceOrdTpCd String
     * @return List<NLZC002001PMsg>
     * @throws SQLException SQLException
     */
    private List<NLZC002001PMsg> getPmsgKtKuKcForStkOut(final NLZC209001PMsg param, final List<Map<String, Object>> shipMdseKtKuInfoList, String sceOrdTpCd) throws SQLException {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        for (Map<String, Object> shipMdseKtKuInfo : shipMdseKtKuInfoList) {

            NLZC002001PMsg outPMsg = new NLZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, (String) shipMdseKtKuInfo.get(SHIP_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, (String) shipMdseKtKuInfo.get(TO_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, (String) shipMdseKtKuInfo.get(FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, (BigDecimal) shipMdseKtKuInfo.get(SHIP_QTY));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, (String) shipMdseKtKuInfo.get(SYS_SRC_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, (String) shipMdseKtKuInfo.get(TRX_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, (String) shipMdseKtKuInfo.get(RWS_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, (String) shipMdseKtKuInfo.get(RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.wrkOrdNum, (String) shipMdseKtKuInfo.get(WRK_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.wrkOrdLineNum, (String) shipMdseKtKuInfo.get(WRK_ORD_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

            if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.KITTING_ITEM_CHANGE_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_KIT_AND_RFRB);
                ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_COMPONENT);
                ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
                ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, (String) shipMdseKtKuInfo.get(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, (String) shipMdseKtKuInfo.get(RWS_DTL_LINE_NUM));

            } else if (NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.KITTING_ITEM_CHANGE_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_KIT_AND_RFRB);
                ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_KIT);
                ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);

            } else if (NLXSceConst.SCE_ORD_TP_CD_KC.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.MOVEMENT);
                ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.LOCATION_STATUS_CHANGE_CANCEL_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_CONF);
                ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_PROCR);
                ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, (String) shipMdseKtKuInfo.get(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, (String) shipMdseKtKuInfo.get(RWS_DTL_LINE_NUM));

                if (DS_WRK_ORD_TP.KIT.equals((String) shipMdseKtKuInfo.get(DS_WRK_ORD_TP_CD))) {

                    ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_COMPONENT);

                } else if (DS_WRK_ORD_TP.UN_KIT.equals((String) shipMdseKtKuInfo.get(DS_WRK_ORD_TP_CD))) {

                    ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_KIT);
                }
            }

            pMsgList.add(outPMsg);
        }

        return pMsgList;
    }

    /**
     * getPmsgKtKuKc
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param sceOrdTpCd String
     * @return List<NLZC002001PMsg>
     * @throws SQLException SQLException
     */
    private List<NLZC002001PMsg> getPmsgKtKuKc(final NLZC209001PMsg param, final ResultSet closeRs, String sceOrdTpCd) throws SQLException {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, closeRs.getBigDecimal(RWS_STK_QTY));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.wrkOrdNum, closeRs.getString(WRK_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        // Set Serial number
        RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
        rwsPutAwaySerWrkT.setSQLID("001");
        rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", closeRs.getString(WRK_TRX_ID));
        rwsPutAwaySerWrkT.setConditionValue("sqId01", closeRs.getString(SQ_ID));
        rwsPutAwaySerWrkT.setConditionValue("rwsNum01", closeRs.getString(RWS_NUM));
        rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", closeRs.getString(RWS_DTL_LINE_NUM));

        RWS_PUT_AWAY_SER_WRKTMsgArray msgArr = (RWS_PUT_AWAY_SER_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(rwsPutAwaySerWrkT);

        if (msgArr != null) {

            for (int i = 0; i < msgArr.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, msgArr.no(i).serNum);
            }

            inPMsg.serNumList.setValidCount(msgArr.getValidCount());
        }

        if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(sceOrdTpCd)) {

            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.KITTING_ITEM_CHANGE_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_KIT_AND_RFRB);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);

        } else if (NLXSceConst.SCE_ORD_TP_CD_KU.equals(sceOrdTpCd)) {

            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.KITTING_ITEM_CHANGE_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_KIT_AND_RFRB);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);

        } else if (NLXSceConst.SCE_ORD_TP_CD_KC.equals(sceOrdTpCd)) {

            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.LOCATION_STATUS_CHANGE_CANCEL_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_CONF);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_PROCR);
            ZYPEZDItemValueSetter.setValue(inPMsg.wrkOrdLineNum, closeRs.getString(WRK_ORD_LINE_NUM));
        }

        pMsgList.add(inPMsg);

        return pMsgList;
    }

    /**
     * <pre>
     * set Inventory Update API PMsg for BB
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @return NLZC002001PMsg
     * @throws SQLException SQLException
     */
    private NLZC002001PMsg getPmsgBb(final NLZC209001PMsg param, final ResultSet closeRs) throws SQLException {

        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, closeRs.getBigDecimal(RWS_STK_QTY));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_PROCR);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.soNum, closeRs.getString(SO_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.soSlpNum, closeRs.getString(SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdNum, closeRs.getString(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdDtlLineNum, closeRs.getString(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.vndCd, closeRs.getString(VND_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        // Set Serial number
        // Set Serial number
        RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
        rwsPutAwaySerWrkT.setSQLID("001");
        rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", closeRs.getString(WRK_TRX_ID));
        rwsPutAwaySerWrkT.setConditionValue("sqId01", closeRs.getString(SQ_ID));
        rwsPutAwaySerWrkT.setConditionValue("rwsNum01", closeRs.getString(RWS_NUM));
        rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", closeRs.getString(RWS_DTL_LINE_NUM));

        RWS_PUT_AWAY_SER_WRKTMsgArray msgArr = (RWS_PUT_AWAY_SER_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(rwsPutAwaySerWrkT);

        if (msgArr != null) {

            for (int i = 0; i < msgArr.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, msgArr.no(i).serNum);
            }

            inPMsg.serNumList.setValidCount(msgArr.getValidCount());
        }

        return inPMsg;
    }

    /**
     * <pre>
     * <ul>
     * <li>W/H Transfer Stock Out</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param lossQty BigDecimal
     * @return NLZC002001PMsg
     * @throws SQLException SQLException
     */
    private NLZC002001PMsg getPmsgDt(final NLZC209001PMsg param, final ResultSet closeRs, final BigDecimal lossQty) throws SQLException {

        // W/H Transfer Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_CONF);
        ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.IN_TRANSIT_WH);
        //QC#27092 MOD START
        //ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(RWS_INVTY_STK_STS_CD));
        //QC#27092 MOD END
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, lossQty);
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_OM);
        ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.cpoOrdNum, closeRs.getString(CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.cpoDtlLineNum, closeRs.getString(CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.cpoDtlLineSubNum, closeRs.getString(CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.shipFromLocCustCd, closeRs.getString(SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

        if (!ZYPConstant.FLG_ON_Y.equals(closeRs.getString(CMPY_INVTY_FLG))) {

            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT);

        } else if (INVTY_ACCT.ASSET.equals(closeRs.getString(INVTY_ACCT_CD))) {

            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT);

        } else {

            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT);
        }

        return outPMsg;
    }

    /**
     * <pre>
     * <ul>
     * <li>WH Transfer In-Transit Stock Out</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param lossQty BigDecimal
     * @return NLZC002001PMsg
     * @throws SQLException SQLException
     */
    private NLZC002001PMsg getPmsgTrToTech(final NLZC209001PMsg param, final ResultSet closeRs, final BigDecimal lossQty) throws SQLException {

        // WH Transfer In-Transit Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_CONF);
        ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.IN_TRANSIT_WH);
        // QC#57220 Mod Start
        //ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(RWS_INVTY_STK_STS_CD));
        // QC#57220 End
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, lossQty);
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdNum, closeRs.getString(INVTY_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdLineNum, closeRs.getString(INVTY_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.shipFromLocCustCd, closeRs.getString(SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

        return outPMsg;
    }

    /**
     * <pre>
     * <ul>
     * <li>WH Transfer In-Transit Stock Out</li>
     * <li>WH Transfer Stock In</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param lossQty BigDecimal
     * @return List<NLZC002001PMsg>
     * @throws SQLException SQLException
     */
    private List<NLZC002001PMsg> getPmsgTr(final NLZC209001PMsg param, final ResultSet closeRs, final BigDecimal lossQty) throws SQLException {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        // WH Transfer In-Transit Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_CONF);
        ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.IN_TRANSIT_WH);
        // QC#27400 MOD START
        //ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(RWS_INVTY_STK_STS_CD));
        // QC#27400 MOD START
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, lossQty);
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdNum, closeRs.getString(INVTY_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdLineNum, closeRs.getString(INVTY_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.shipFromLocCustCd, closeRs.getString(SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

        pMsgList.add(outPMsg);

        // WH Transfer Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.WAREHOUSE_TRANSFER_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_CONF);
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, closeRs.getString(SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, closeRs.getString(FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, lossQty);
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyOrdNum, closeRs.getString(INVTY_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyOrdLineNum, closeRs.getString(INVTY_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.shipFromLocCustCd, closeRs.getString(SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        // Set Serial number
        List<String> serNumList = getRwsPutAwaySerNumList(param, inPMsg.rwsNum.getValue(), inPMsg.rwsDtlLineNum.getValue());

        if (serNumList != null) {

            for (int i = 0; i < serNumList.size(); i++) {

                ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, serNumList.get(i));
            }

            inPMsg.serNumList.setValidCount(serNumList.size());
        }

        pMsgList.add(inPMsg);

        return pMsgList;
    }

    /**
     * <pre>
     * <ul>
     * <li>Loan to Inventory Shortage Stock Out</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param lossQty BigDecimal
     * @param invtyLocCd String
     * @return NLZC002001PMsg
     * @throws SQLException SQLException
     */
    private NLZC002001PMsg getPmsgRt(final NLZC209001PMsg param, final ResultSet closeRs, final BigDecimal lossQty, final String invtyLocCd) throws SQLException {

        // Loan to Inventory Shortage Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.EXPENSE_SHIPMENT);
        // QC#63527 2024/03/14 Start
        if (invtyLocCd.equals(ZYPCodeDataUtil.getVarCharConstValue("LOAN_DUMMY_CSA_WH_CD", param.glblCmpyCd.getValue()))) {
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT);
        } else {
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.OFF_THE_BOOK_EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT);
        }
        // QC#63527 2024/03/14 End
        ZYPEZDItemValueSetter.setValue(outPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_TO_INVTY);
        ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.TRIAL_USE);
        ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, lossQty);
        ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.cpoOrdNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.cpoDtlLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.cpoDtlLineSubNum, closeRs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rmaNum, closeRs.getString(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rmaLineNum, closeRs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.rmaLineSubNum, closeRs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.origCpoOrdNum, closeRs.getString(ORIG_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.origCpoDtlLineNum, closeRs.getString(ORIG_CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.origCpoDtlLineSubNum, closeRs.getString(ORIG_CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(outPMsg.sellToCustCd, closeRs.getString(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.billToCustCd, closeRs.getString(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.shipToCustCd, closeRs.getString(FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.shipToLocCustCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(outPMsg.shipCostAmt, getShipCostAmt(param, closeRs, lossQty));

        // Set Serial number
        List<String> serNumList = getRwsSerNumListLoanRt(param, outPMsg.rwsNum.getValue(), outPMsg.rwsDtlLineNum.getValue());

        if (serNumList != null) {

            for (int i = 0; i < serNumList.size(); i++) {

                ZYPEZDItemValueSetter.setValue(outPMsg.serNumList.no(i).serNum, serNumList.get(i));
            }

            outPMsg.serNumList.setValidCount(serNumList.size());
        }

        return outPMsg;
    }

    /**
     * <pre>
     * Call Inventory Order Update API
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param invtyOrdNum String
     * @param invtyOrdLineNum String
     * @param invtyOrdList List<String>
     * @param onBatchType ONBATCH_TYPE
     * @param hdrFlg boolean (true:HeaderClose false:DetailClose)
     * @param mode String
     */
    private void callNLZC003001(String glblCmpyCd, String invtyOrdNum, String invtyOrdLineNum, List<String> invtyOrdList, ONBATCH_TYPE onBatchType, boolean hdrFlg, String mode) {

        NLZC003001 invntOrdApi = new NLZC003001();
        NLZC003001PMsg pMsg;
        List<NLZC003001PMsg> pMsgList = new ArrayList<NLZC003001PMsg>();

        if (hdrFlg) {

            for (int i = 0; i < invtyOrdList.size(); i++) {

                pMsg = new NLZC003001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, mode);
                ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdNum, invtyOrdList.get(i));
                pMsgList.add(pMsg);
            }

        } else {

            pMsg = new NLZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, mode);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdNum, invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdLineNum, invtyOrdLineNum);
            pMsgList.add(pMsg);
        }

        invntOrdApi.execute(pMsgList, onBatchType);

        for (NLZC003001PMsg msg : pMsgList) {

            if (S21ApiUtil.isXxMsgId(msg)) {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
                throw new S21AbendException(msgList.get(0));
            }
        }
    }

    /**
     * <pre>
     * Close Purchase Requisition
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param prchReqNum String
     * @param prchReqLineNum String
     * @param prchReqLineSubNum BigDecimal
     * @param rwsNum String
     * @param reqQty BigDecimal
     * @param sceOrdTp String
     * @param onBatchType ONBATCH_TYPE
     */
    private void callNPZC103001(final String glblCmpyCd, final String prchReqNum, final String prchReqLineNum, 
            final BigDecimal prchReqLineSubNum, final String rwsNum, final String rwsCloDtTmTs, final BigDecimal reqQty, final String sceOrdTp, final ONBATCH_TYPE onBatchType) {

        NPZC103001 prchReqApi = new NPZC103001();
        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        List<NPZC103001PMsg> pMsgList = new ArrayList<NPZC103001PMsg>();

        // Set param
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_RECEIVED);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, prchReqNum);

        // Detail info
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineNum, prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineSubNum, prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsCloDtTmTs, rwsCloDtTmTs);

        if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTp)) {

            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsPutAwayQty, reqQty);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).backToTechQty, BigDecimal.ZERO);

        } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTp)) {

            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsPutAwayQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).backToTechQty, reqQty);

            // QC#27092 ADD START
        } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTp)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsPutAwayQty, BigDecimal.ZERO);
        }
        // QC#27092 ADD END
        
        pMsg.prchReqInfo.setValidCount(1);

        prchReqApi.execute(pMsg, onBatchType);

        for (NPZC103001PMsg msg : pMsgList) {

            if (S21ApiUtil.isXxMsgId(msg)) {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
                throw new S21AbendException(msgList.get(0));
            }
        }
    }

    /**
     * <pre>
     * Call Work Order Update API
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param wrkOrdNum String
     * @param onBatchType ONBATCH_TYPE
     * @param cancelWrkOrd boolean
     */
    private void callNPZC002001(String glblCmpyCd, String wrkOrdNum, ONBATCH_TYPE onBatchType, boolean cancelWrkOrd) {

        NPZC002001 api = new NPZC002001();
        NPZC002001PMsg pMsg = new NPZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.wrkOrdNum, wrkOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.wrkOrdStsCd, WRK_ORD_STS.RECEIVING_COMPLETION);

        if (cancelWrkOrd) {

            ZYPEZDItemValueSetter.setValue(pMsg.fnshGoodsRcvQty, BigDecimal.ZERO);

        } else {

            WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();
            wrkOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            wrkOrdTMsg.wrkOrdNum.setValue(wrkOrdNum);

            wrkOrdTMsg = (WRK_ORDTMsg) EZDTBLAccessor.findByKey(wrkOrdTMsg);

            ZYPEZDItemValueSetter.setValue(pMsg.fnshGoodsRcvQty, wrkOrdTMsg.fnshGoodsBalQty);
        }

        api.execute(pMsg, onBatchType);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            throw new S21AbendException(msgList.get(0));
        }
    }

    /**
     * <pre>
     * Close Purchase Order
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param mdseCd String
     * @param rwsStkQty BigDecimal
     * @param onBatchType ONBATCH_TYPE
     * @param hdrFlg boolean (true:HeaderClose false:DetailClose)
     */
    private void callNPZC004001ForReceiving(final String glblCmpyCd, final String poOrdNum, final String poOrdDtlLineNum
            , final String mdseCd, final BigDecimal rwsStkQty, final ONBATCH_TYPE onBatchType, final boolean hdrFlg) {

        NPZC004001 api = new NPZC004001();
        NPZC004001PMsg pMsg = new NPZC004001PMsg();

        pMsg.glblCmpyCd.setValue(glblCmpyCd);
        pMsg.poOrdNum.setValue(poOrdNum);

        if (hdrFlg) {

            pMsg.poStsCd.setValue(PO_STS.RECEIVING_COMPLETION);

        } else {

            pMsg.mdseCd.setValue(mdseCd);

            if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {

                pMsg.poOrdDtlLineNum.setValue(poOrdDtlLineNum);
            }

            pMsg.poRcvQty.setValue(rwsStkQty);
            pMsg.poStsCd.setValue(PO_STS.RECEIVING);
        }

        if (ZYPCommonFunc.hasValue(poOrdNum)) {

            api.execute(pMsg, onBatchType);

            if (S21ApiUtil.isXxMsgId(pMsg)) {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
                throw new S21AbendException(msgList.get(0));
            }
        }
    }

    /**
     * <pre>
     * Call PO Status Update API
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param onBatchType ONBATCH_TYPE
     */
    private void callNPZC004001(String glblCmpyCd, String poOrdNum, ONBATCH_TYPE onBatchType) {

        NPZC004001 api = new NPZC004001();
        NPZC004001PMsg pMsg = new NPZC004001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
        pMsg.poStsCd.setValue(PO_STS.RECEIVING_COMPLETION);

        api.execute(pMsg, onBatchType);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            throw new S21AbendException(msgList.get(0));
        }
    }

    /**
     * <pre>
     * Call Shipping Plan Update API
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @param actlShipDt String
     * @param ordQty BigDecimal
     * @param onBatchType ONBATCH_TYPE
     */
    private void callNWZC003001(String glblCmpyCd, String shpgPlnNum, String actlShipDt, BigDecimal ordQty, ONBATCH_TYPE onBatchType) {

        NWZC003001 api = new NWZC003001();
        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();

        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, SHPG_MODE_SHIPPED);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(pMsg.spTotFuncFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.actlShipDt, S21StringUtil.subStringByLength(actlShipDt, 0, DATE_END_DIGIT));
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, ordQty);
        pMsgList.add(pMsg);

        api.execute(pMsgList, onBatchType);

        for (NWZC003001PMsg msg : pMsgList) {

            if (S21ApiUtil.isXxMsgId(msg)) {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
                throw new S21AbendException(msgList.get(0));
            }
        }
    }

    /**
     * <pre>
     * Close Return CPO
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param trxOrdNum String
     * @param trxLineNum String
     * @param trxLineSubNum String
     * @param onBatchType ONBATCH_TYPE
     * @param dtlRqstTpCd String
     */
    private void callNWZC153001(NLZC209001PMsg param, final String trxOrdNum, final String trxLineNum, final String trxLineSubNum, final ONBATCH_TYPE onBatchType, final String dtlRqstTpCd) {

        NWZC153001 cpoRtrnDtlApi= new NWZC153001();

        NWZC153001PMsg pMsg = new NWZC153001PMsg();
        List<NWZC153001PMsg> pMsgList = new ArrayList<NWZC153001PMsg>();
        List<NWZC153002PMsg> linePMsgList = new ArrayList<NWZC153002PMsg>();

        // Header
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_UPD_STS);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, trxOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt.getValue());

        // Detail
        ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(0).xxRqstTpCd, dtlRqstTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(0).dsCpoRtrnLineNum, trxLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(0).dsCpoRtrnLineSubNum, trxLineSubNum);
        pMsg.ordRtrnDtlList.setValidCount(1);

        cpoRtrnDtlApi.execute(pMsg, linePMsgList, onBatchType);

        for (NWZC153001PMsg msg : pMsgList) {

            if (S21ApiUtil.isXxMsgId(msg)) {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
                throw new S21AbendException(msgList.get(0));
            }
        }
    }

    /**
     * <pre>
     * Get Shipped Cost Amount for Loan to Inventory
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param lossQty BigDecimal
     * @return BigDecimal
     * @throws SQLException
     */
    private BigDecimal getShipCostAmt(final NLZC209001PMsg param, final ResultSet closeRs, final BigDecimal lossQty) throws SQLException {

        // find location Code from CPO_DTL
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, closeRs.getString(ORIG_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, closeRs.getString(ORIG_CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, closeRs.getString(ORIG_CPO_DTL_LINE_SUB_NUM));

        cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);

        // Calculate by Inventory Valuation
        NLXC001001GetInventoryItemCostBean getInventoryItemCostBean = new NLXC001001GetInventoryItemCostBean();

        getInventoryItemCostBean.setGlblCmpyCd(param.glblCmpyCd.getValue());
        getInventoryItemCostBean.setMdseCd(closeRs.getString(MDSE_CD));
        getInventoryItemCostBean.setInvtyLocCd(cpoDtlTMsg.invtyLocCd.getValue());
        getInventoryItemCostBean.setQty(lossQty);

        NLXC001001GetInventoryItemCost.getInventoryItemCost(getInventoryItemCostBean);

        // Set Parameter.
        BigDecimal shipCostAmt = NLXC014001.nullToZero(getInventoryItemCostBean.getTotPrcAmt());

        return shipCostAmt;
    }

    /**
     * <pre>
     * checkApiErr
     * </pre>
     * 
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
     * addErrLogMsg
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param errMsgId String
     * @param logMsgId String
     * @param param String[]
     * @param onBatchType ONBATCH_TYPE
     */
    private void addErrLogMsg(final S21ApiMessageMap msgMap, final String errMsgId, final String logMsgId, final String[] param, final ONBATCH_TYPE onBatchType) {

        msgMap.addXxMsgId(errMsgId);

        if (!dataErrFlg) {

            NLZC209001PMsg pMsg = (NLZC209001PMsg) msgMap.getPmsg();
            String[] params = new String[] {RWS_CPLT_WRK, pMsg.glblCmpyCd.getValue(), pMsg.wrkTrxId.getValue(), pMsg.sqId.getValue() };
            NLXC025001.outputLog(1, NLAM1208E, params, onBatchType, this);
            dataErrFlg = true;
        }

        NLXC025001.outputLog(1, logMsgId, param, onBatchType, this);
    }

    /**
     * <pre>
     * deleteWhSchd
     * </pre>
     * 
     * @param inMsg RWS_HDRTMsg
     */
    private void deleteWhSchd(RWS_HDRTMsg inMsg) {

        List<WH_SCHDTMsg> deleteList = new ArrayList<WH_SCHDTMsg>();

        String sceOrdTpCd = inMsg.sceOrdTpCd.getValue();
        String inbdSrcTpCd = inMsg.inbdSrcTpCd.getValue();
        String statementId01 = "";
        String statementId02 = "";
        String statementId03 = "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        paramMap.put("rwsNum", inMsg.rwsNum.getValue());
        paramMap.put("inbdVisEventCd01", INBD_VIS_EVENT.INVOICE_NL_RECEIVE);
        paramMap.put("inbdVisEventCd02", INBD_VIS_EVENT.INVOICE_PLANNING_DI_NL_RECEIVE);
        paramMap.put("inbdVisEventCd03", INBD_VIS_EVENT.WH_CHANGE_REQUEST);
        paramMap.put("inbdVisEventCd04", INBD_VIS_EVENT.WH_CHANGE_DI_OR_TL);
        paramMap.put("inbdVisEventCd05", INBD_VIS_EVENT.WH_CHANGE_CR);
        paramMap.put("inbdVisEventCd06", INBD_VIS_EVENT.ASN);
        paramMap.put("inbdVisEventCd07", INBD_VIS_EVENT.WH_SCHEDULE_ENTRY);
        paramMap.put("inbdVisEventCd08", INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY);
        paramMap.put("imptInvNum", "");
        paramMap.put("whSchdRefKeyNumSq01", "");
        paramMap.put("whSchdRefKeyNumSq02", "");

        if (NLXSceConst.SCE_ORD_TP_CD_IV.equals(sceOrdTpCd)) {

            statementId01 = "getIVInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_CT.equals(sceOrdTpCd)) {

            statementId01 = "getCTInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_IO.equals(sceOrdTpCd)) {

            statementId01 = "getIOInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_CO.equals(sceOrdTpCd)) {

            statementId01 = "getCOInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTpCd)) {

            statementId01 = "getCAInfo";

        } else if (INBD_SRC_TP.PO_RECEIVING.equals(inbdSrcTpCd)) {

            statementId01 = "getPOInfo";

        } else {

            return;
        }

        PreparedStatement stmtFirst = null;
        ResultSet rsFirst = null;
        PreparedStatement stmtSecond = null;
        ResultSet rsSecond = null;
        PreparedStatement stmtThird = null;
        ResultSet rsThird = null;

        String curImptInvNum = "";
        String curWhSchdRefKeyNumSq = "";
        String inbdVisEventCd = null;
        String whSchdRefKeyNumSq = null;
        List<Map<String, Object>> invNumList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> whSchdRefKeyNumSqList = new ArrayList<String>();

        try {

            stmtFirst = this.ssmClient.createPreparedStatement(statementId01, paramMap);
            rsFirst = stmtFirst.executeQuery();

            while (rsFirst.next()) {

                WH_SCHDTMsg deleteMsg = new WH_SCHDTMsg();
                deleteMsg.glblCmpyCd.setValue(rsFirst.getString(GLBL_CMPY_CD));
                deleteMsg.whSchdPk.setValue(rsFirst.getBigDecimal(WH_SCHD_PK));

                if (NLXSceConst.SCE_ORD_TP_CD_IO.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_CO.equals(sceOrdTpCd)) {

                    paramMap.put("whSchdRefKeyNumSq01", rsFirst.getString(WH_SCHD_REF_KEY_NUM_SQ));

                } else if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTpCd)) {

                    if (!ZYPCommonFunc.hasValue(curImptInvNum)) {

                        curImptInvNum = rsFirst.getString(IMPT_INV_NUM);
                        map.put(IMPT_INV_NUM, rsFirst.getString(IMPT_INV_NUM));

                    } else if (!curImptInvNum.equals(rsFirst.getString(IMPT_INV_NUM))) {

                        Object[] whSchdRefKeyNumSqArr = (Object[]) whSchdRefKeyNumSqList.toArray();
                        map.put(WH_SCHD_REF_KEY_NUM_SQ_02, (Object[]) whSchdRefKeyNumSqArr);
                        invNumList.add(map);

                        map = new HashMap<String, Object>();
                        curImptInvNum = rsFirst.getString(IMPT_INV_NUM);
                        map.put(IMPT_INV_NUM, rsFirst.getString(IMPT_INV_NUM));

                        curWhSchdRefKeyNumSq = "";
                        whSchdRefKeyNumSqList.clear();
                    }

                    whSchdRefKeyNumSq = rsFirst.getString(WH_SCHD_REF_KEY_NUM_SQ);
                    inbdVisEventCd = rsFirst.getString(INBD_VIS_EVENT_CD);

                    if (!INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(inbdVisEventCd)
                            && !INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(inbdVisEventCd)
                            && !INBD_VIS_EVENT.ASN.equals(inbdVisEventCd)
                            && !curWhSchdRefKeyNumSq.equals(whSchdRefKeyNumSq)) {

                        whSchdRefKeyNumSqList.add(whSchdRefKeyNumSq);
                        curWhSchdRefKeyNumSq = whSchdRefKeyNumSq;
                    }
                }

                deleteList.add(deleteMsg);
            }

            Object[] whSchdRefKeyNumSqArr = (Object[]) whSchdRefKeyNumSqList.toArray();
            map.put(WH_SCHD_REF_KEY_NUM_SQ_02, (Object[]) whSchdRefKeyNumSqArr);
            invNumList.add(map);

            if (NLXSceConst.SCE_ORD_TP_CD_IO.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_CO.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTpCd)) {

                if (NLXSceConst.SCE_ORD_TP_CD_IO.equals(sceOrdTpCd)) {

                    statementId02 = "getSameCntnrDifferentWhInfoForIO";

                } else if (NLXSceConst.SCE_ORD_TP_CD_CO.equals(sceOrdTpCd)) {

                    statementId02 = "getSameCntnrDifferentWhInfoForCO";

                } else if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTpCd)) {

                    statementId02 = "getSameInvDifferentWhInfoForCA";
                }

                for (Map<String, Object> invNumMap : invNumList) {

                    // Find different WH in same Invoice.
                    if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTpCd)) {

                        if (ZYPCommonFunc.hasValue((String) invNumMap.get(IMPT_INV_NUM))) {

                            paramMap.put("imptInvNum", invNumMap.get(IMPT_INV_NUM));
                        }

                        if (invNumMap.get(WH_SCHD_REF_KEY_NUM_SQ_02) != null) {

                            paramMap.put("whSchdRefKeyNumSq02", invNumMap.get(WH_SCHD_REF_KEY_NUM_SQ_02));
                        }
                    }

                    stmtSecond = this.ssmClient.createPreparedStatement(statementId02, paramMap);
                    rsSecond = stmtSecond.executeQuery();

                    // Not exists different WH in same Invoice.
                    if (!rsSecond.next()) {

                        if (NLXSceConst.SCE_ORD_TP_CD_IO.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_CO.equals(sceOrdTpCd)) {

                            statementId03 = "getNLRcvSCPInfo";

                        } else if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTpCd)) {

                            statementId03 = "getNLRcvCAInfo";
                        }

                        stmtThird = this.ssmClient.createPreparedStatement(statementId03, paramMap);
                        rsThird = stmtThird.executeQuery();

                        while (rsThird.next()) {

                            WH_SCHDTMsg deleteMsg = new WH_SCHDTMsg();
                            deleteMsg.glblCmpyCd.setValue(rsThird.getString(GLBL_CMPY_CD));
                            deleteMsg.whSchdPk.setValue(rsThird.getBigDecimal(WH_SCHD_PK));

                            deleteList.add(deleteMsg);
                        }

                    } else {
                        // do nothing
                    }
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmtFirst, rsFirst);
            S21SsmLowLevelCodingClient.closeResource(stmtSecond, rsSecond);
            S21SsmLowLevelCodingClient.closeResource(stmtThird, rsThird);
        }

        int cnt = deleteList.size();

        if (cnt > 0) {

            WH_SCHDTMsg[] msgs = new WH_SCHDTMsg[cnt];

            for (int i = 0; i < cnt; i++) {

                msgs[i] = deleteList.get(i);
            }

            S21FastTBLAccessor.removeLogical(msgs);
        }
    }

    /**
     * insertRwsPutAwaySer
     * @param param NLZC209001PMsg
     * @param rwsNum String
     * @param srchRwsDtlLineNum String
     * @param insRwsDtlLineNum String
     * @param sceOrdTpCd String
     * @param rwsCloDtTmTs String
     * @param closeRs ResultSet
     */
    private void insertRwsPutAwaySer(NLZC209001PMsg param//
            , String rwsNum//
            , String srchRwsDtlLineNum//
            , String insRwsDtlLineNum//
            , String sceOrdTpCd//
            , String rwsCloDtTmTs
            , ResultSet closeRs) throws SQLException {

        // create RWS Put Away Serial
        // select from RWS_PUT_AWAY_SER_WRK
        RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
        rwsPutAwaySerWrkT.setSQLID("001");
        rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", closeRs.getString(WRK_TRX_ID));
        rwsPutAwaySerWrkT.setConditionValue("sqId01", closeRs.getString(SQ_ID));
        rwsPutAwaySerWrkT.setConditionValue("rwsNum01", closeRs.getString(RWS_NUM));
        rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", closeRs.getString(RWS_DTL_LINE_NUM));

        RWS_PUT_AWAY_SER_WRKTMsgArray msgArr = (RWS_PUT_AWAY_SER_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(rwsPutAwaySerWrkT);

        // insert into RWS_PUT_AWAY_SER
        for (int i = 0; i < msgArr.getValidCount(); i++) {
            RWS_PUT_AWAY_SER_WRKTMsg msg = (RWS_PUT_AWAY_SER_WRKTMsg) msgArr.get(i);

            RWS_PUT_AWAY_SERTMsg rwsPutAwaySerT = new RWS_PUT_AWAY_SERTMsg();
            rwsPutAwaySerT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            rwsPutAwaySerT.rwsNum.setValue(closeRs.getString(RWS_NUM));
            rwsPutAwaySerT.rwsLineNum.setValue(closeRs.getString(RWS_DTL_LINE_NUM));
            rwsPutAwaySerT.invtyStkStsCd.setValue(msg.invtyStkStsCd.getValue());
            rwsPutAwaySerT.serNum.setValue(msg.serNum.getValue());
            rwsPutAwaySerT.mdseCd.setValue(msg.mdseCd.getValue());
            rwsPutAwaySerT.serNumSendFlg.setValue(ZYPConstant.FLG_OFF_N);

            S21ApiTBLAccessor.insert(rwsPutAwaySerT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwaySerT.getReturnCode())) {
                String[] list = new String[] {
                        rwsPutAwaySerT.getTableName(),
                        NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD, SER_NUM, MDSE_CD),
                        NLXCMsgHelper.toListedString(rwsPutAwaySerT.glblCmpyCd.getValue(), rwsPutAwaySerT.rwsNum.getValue(), rwsPutAwaySerT.rwsLineNum.getValue(), rwsPutAwaySerT.invtyStkStsCd.getValue(), rwsPutAwaySerT.serNum
                                .getValue(), rwsPutAwaySerT.mdseCd.getValue()) };
                throw new S21AbendException(NLAM1133E, list);
            }
        }

        PreparedStatement state = null;
        ResultSet rs = null;

        try {

            Map<String, String> paramMap = new HashMap<String, String>();

            paramMap.put("glblCmpyCd01", param.glblCmpyCd.getValue());
            paramMap.put("rwsNum01", closeRs.getString(RWS_NUM));
            paramMap.put("rwsDtlLineNum01", closeRs.getString(RWS_DTL_LINE_NUM));
            paramMap.put("wrkTrxId01", closeRs.getString(WRK_TRX_ID));
            paramMap.put("sqId01", closeRs.getString(SQ_ID));

            // 10/10/2017 CITS T.Hakodate Update QC#21670 START
            String createShipSerTp = ZYPCodeDataUtil.getVarCharConstValue("NLZC2090_CREATE_SHIP_SER", param.glblCmpyCd.getValue());
            String[] createShipSerTpList = createShipSerTp.split(",");

            if (ZYPCommonFunc.hasValue(createShipSerTp)) {

                if (Arrays.asList(createShipSerTpList).contains(sceOrdTpCd)) {

                    // CREATE SHIP_SER_NUM
                    // BB : Buy Back SC : Stock Status Change
                    // SW : Sub Warehouse Change CC : Configuration
                    // Change
                    // if(NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)
                    // ||
                    // NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd)
                    // ||
                    // NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd)
                    // ||
                    // NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)
                    // ||
                    // NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)
                    // ||
                    // NLXSceConst.SCE_ORD_TP_CD_RX.equals(sceOrdTpCd)){

                    // 10/10/2017 CITS T.Hakodate Update QC#21670 END

                    if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {
                        // BB : Buy Back SC : Stock Status Change CC :
                        // Configuration Change
                        state = this.ssmClient.createPreparedStatement("getRwsPutAwaySerWrkForBb", paramMap);

                    } else if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_RX.equals(sceOrdTpCd)) {

                        state = this.ssmClient.createPreparedStatement("getRwsPutAwaySerWrkForRmRx", paramMap);

                    } else {

                        // SW :Sub Warehouse Change
                        state = this.ssmClient.createPreparedStatement("getRwsPutAwaySerWrkForSwScCc", paramMap);
                    }

                    rs = state.executeQuery();

                    // insert into SHIP_SER_NUM
                    while (rs.next()) {

                        // check ship serial id
                        String shipSerialIdKey = rs.getString("SO_NUM") + rs.getString("MDSE_CD");
                        
                        int count = 1;
                        
                        if (shipSerId.containsKey(shipSerialIdKey)) {
                            count = shipSerId.get(shipSerialIdKey) + 1;
                            shipSerId.put(shipSerialIdKey, count);
                        } else {
                            shipSerId.put(shipSerialIdKey, count);
                        }

                        SHIP_SER_NUMTMsg shipSerNum = new SHIP_SER_NUMTMsg();
                        shipSerNum.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(shipSerNum.mdseCd, rs.getString("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.soNum, rs.getString("SO_NUM"));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.whCd, rs.getString("INVTY_LOC_CD"));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.soSerId, String.valueOf(count));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.serNum, rs.getString("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.serTakeDtTmTs, rwsCloDtTmTs);
                        ZYPEZDItemValueSetter.setValue(shipSerNum.soSlpNum, rs.getString("SO_SLP_NUM"));
                        ZYPEZDItemValueSetter.setValue(shipSerNum.serIntfcProcStsCd, PROC_STS.IN_COMPLETED);

                        S21ApiTBLAccessor.insert(shipSerNum);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipSerNum.getReturnCode())) {

                            String[] list = new String[] {shipSerNum.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, "SO_NUM", "SO_SLP_NUM", SER_NUM, MDSE_CD),
                                    NLXCMsgHelper.toListedString(shipSerNum.glblCmpyCd.getValue(), shipSerNum.soNum.getValue(), shipSerNum.soSlpNum.getValue(), shipSerNum.serNum.getValue(), shipSerNum.mdseCd.getValue()) };
                            throw new S21AbendException(NLAM1133E, list);
                        }
                    }
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(state, rs);
        }
    }

    // QC# 13811 START
    private void updateInbdVisDo(final S21ApiMessageMap msgMap, String sceOrdTpCd, final String poRcvNum, final ResultSet closeRs , BigDecimal visQty) throws SQLException {

        INBD_VISTMsg inVisT = new INBD_VISTMsg();

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        String sysDtTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        BigDecimal inbdVisPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);

        ZYPEZDItemValueSetter.setValue(inVisT.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisPk, inbdVisPk);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdLtstRecFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisEventCd, INBD_VIS_EVENT.RWS_PUTAWAY_RECEIVE);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisActlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inVisT.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inVisT.poRcvNum, poRcvNum);
        ZYPEZDItemValueSetter.setValue(inVisT.poRcvLineNum, NLXC014001.nullToEmpty(closeRs.getString(TRX_LINE_NUM)));
        ZYPEZDItemValueSetter.setValue(inVisT.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inVisT.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inVisT.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inVisT.visLocTpCd, VIS_LOC_TP.DC);
        ZYPEZDItemValueSetter.setValue(inVisT.visLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inVisT.visLocNm, getInvtyLocNm(param.glblCmpyCd.getValue(), closeRs.getString(INVTY_LOC_CD)));
        ZYPEZDItemValueSetter.setValue(inVisT.invtyStkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inVisT.calcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inVisT.cratTs, sysDtTs);
        ZYPEZDItemValueSetter.setValue(inVisT.regdTs, sysDtTs);

        if (NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(inVisT.imptCustPoNum, NLXC014001.nullToEmpty(closeRs.getString(PO_ORD_NUM)));
        }

        ZYPEZDItemValueSetter.setValue(inVisT.visQty, visQty);

        ZYPEZDItemValueSetter.setValue(inVisT.etaEtdDt, S21StringUtil.subStringByLength(getpoRcvEtaDt(param.glblCmpyCd.getValue(), poRcvNum), 0, DATE_END_DIGIT));

        S21ApiTBLAccessor.insert(inVisT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inVisT.getReturnCode())) {
            String[] list = new String[] {inVisT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, inbdVisPk), NLXCMsgHelper.toListedString(inVisT.glblCmpyCd.getValue(), inVisT.inbdVisPk.getValue()) };
            throw new S21AbendException(NLAM1133E, list);
        }

        // QC#22493
        if (NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {
            INBD_VISTMsg inMsg = new INBD_VISTMsg();
            inMsg.setSQLID("049");
            inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            inMsg.setConditionValue("inbdLtstRecFlg01", ZYPConstant.FLG_ON_Y);
            inMsg.setConditionValue("inbdVisEventCd01", INBD_VIS_EVENT.POYO_RECEIVE);
            inMsg.setConditionValue("inbdVisDataTpCd01A", INBD_VIS_DATA_TP.STOCK_OUT);
            inMsg.setConditionValue("inbdVisDataTpCd01B", INBD_VIS_DATA_TP.STOCK_IN_DC);
            StringBuilder imptInvDoNum = new StringBuilder();
            imptInvDoNum.append(NLXC014001.nullToEmpty(closeRs.getString(PO_ORD_NUM)));
            imptInvDoNum.append(NLXC014001.nullToEmpty(closeRs.getString(PO_ORD_DTL_LINE_NUM)));
            imptInvDoNum.append("%");
            inMsg.setConditionValue("imptInvDoNum01", imptInvDoNum.toString());

            INBD_VISTMsgArray resultArray = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

            if (resultArray == null) {
                resultArray = new INBD_VISTMsgArray();
            }
            if (resultArray.getValidCount() > 0) {
                // Logical Remove Inbound Visibility
                INBD_VISTMsg[] updTMsg = new INBD_VISTMsg[resultArray.getValidCount()];
                for (int i = 0; i < resultArray.getValidCount(); i++) {
                    updTMsg[i] = resultArray.no(i);
                    ZYPEZDItemValueSetter.setValue(updTMsg[i].inbdLtstRecFlg, ZYPConstant.FLG_OFF_N);
                    S21ApiTBLAccessor.update(updTMsg);
                }
                S21ApiTBLAccessor.logicalRemove(updTMsg);
            }
        }

    }

    private String getInvtyLocNm(String glblCmpyCd, String invtyLocCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);

        String invtyLocNm = (String) ssmBatchClient.queryObject("getInvtyLocNm", queryParam);

        return invtyLocNm;

    }

    private String getpoRcvEtaDt(String glblCmpyCd, String poRcvNum) {

        PO_RCV_HDRTMsg poRcvHdrT = new PO_RCV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvNum, poRcvNum);

        poRcvHdrT = (PO_RCV_HDRTMsg) S21ApiTBLAccessor.findByKey(poRcvHdrT);

        return poRcvHdrT.poRcvEtaDt.getValue();

    }

    // QC# 13811 END


    /**
     * getTrxRefNum
     * @param glblCmpyCd String
     * @param trxHdrNum String
     * @param trxLineNum String
     * @return trxRefNum String
     */
    private String getTrxRefNum(String glblCmpyCd, String trxHdrNum, String trxLineNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyOrdNum", trxHdrNum);
        queryParam.put("invtyOrdLineNum", trxLineNum);

        String trxRefNum = (String) ssmBatchClient.queryObject("getTrxRefNum", queryParam);

        return trxRefNum;
    }

    /**
     * Get Shipping Serial number List(SCE_ORD_TP_CD=IC,CC,SC,SW,IT)
     * @param param NLZC209001PMsg
     * @param SHPG_ORD_DTLTMsg tMsg
     * @return List<String>
     */
    private List<String> getShipSerNumListIcCcScSwIT(NLZC209001PMsg param, SHPG_ORD_DTLTMsg tMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("invtyOrdNum", tMsg.trxHdrNum.getValue());
        queryParam.put("invtyOrdLineNum", tMsg.trxLineNum.getValue());

        List<String> shipSerNumList = (List<String>) ssmBatchClient.queryObjectList("getShipSerNumListIcCcScSwIT", queryParam);

        if (shipSerNumList == null || shipSerNumList.isEmpty()) {

            return null;
        }

        return shipSerNumList;
    }

    /**
     * Get Shipping Serial number List(SCE_ORD_TP_CD=BB)
     * @param param NLZC209001PMsg
     * @param SHPG_ORD_DTLTMsg tMsg
     * @return List<String>
     */
    private List<String> getShipSerNumListBb(NLZC209001PMsg param, SHPG_ORD_DTLTMsg tMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("poOrdNum", tMsg.trxHdrNum.getValue());
        queryParam.put("poOrdDtlLineNum", tMsg.trxLineNum.getValue());

        List<String> shipSerNumList = (List<String>) ssmBatchClient.queryObjectList("getShipSerNumListBb", queryParam);

        if (shipSerNumList == null || shipSerNumList.isEmpty()) {

            return null;
        }

        return shipSerNumList;
    }

    /**
     * Get Shipping Serial number List(SCE_ORD_TP_CD=RM)
     * @param param NLZC209001PMsg
     * @param SHPG_ORD_DTLTMsg tMsg
     * @return List<String>
     */
    private List<String> getShipSerNumListRm(NLZC209001PMsg param, SHPG_ORD_DTLTMsg tMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("rmnfOrdNum", tMsg.trxHdrNum.getValue());
        queryParam.put("rmnfOrdDtlLineNum", tMsg.trxLineNum.getValue());

        List<String> shipSerNumList = (List<String>) ssmBatchClient.queryObjectList("getShipSerNumListRm", queryParam);

        if (shipSerNumList == null || shipSerNumList.isEmpty()) {

            return null;
        }

        return shipSerNumList;
    }

    /**
     * Get RWS Serial number List
     * @param param NLZC209001PMsg
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return List<String>
     */
    private List<String> getRwsPutAwaySerNumList(NLZC209001PMsg param, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("rwsDtlLineNum", rwsDtlLineNum);

        List<String> rwsSerNumList = (List<String>) ssmBatchClient.queryObjectList("getRwsPutAwaySerNumList", queryParam);

        if (rwsSerNumList == null || rwsSerNumList.isEmpty()) {

            return null;
        }

        return rwsSerNumList;
    }

    /**
     * Get RWS Serial number List
     * @param param NLZC209001PMsg
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return List<String>
     */
    private List<String> getRwsSerNumListLoanRt(NLZC209001PMsg param, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("rwsDtlLineNum", rwsDtlLineNum);

        List<String> rwsSerNumList = (List<String>) ssmBatchClient.queryObjectList("getRwsSerNumListLoanRt", queryParam);

        if (rwsSerNumList == null || rwsSerNumList.isEmpty()) {

            return null;
        }

        return rwsSerNumList;
    }

    // QC#21574 ADD START
    /**
     * <pre>
     * set Inventory Update API PMsg for Reman Stock In
     * </pre>
     * 
     * @param param NLZC209001PMsg
     * @param closeRs ResultSet
     * @param sceOrdTpCd SCE_ORD_TP_CD
     * @return NLZC002001PMsg
     * @throws SQLException SQLException
     */
    private List<NLZC002001PMsg> getPmsgRemanStockIn(final NLZC209001PMsg param, final ResultSet closeRs) throws SQLException {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        // Reman Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());

        String sceOrdTpCd = closeRs.getString("SCE_ORD_TP_CD");
        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.REMANUFACTURING);
            // QC#22834 Start
            if (isRemanMainUnit(param, closeRs.getString(INVTY_TRX_SLP_NUM), closeRs.getString(TRX_LINE_NUM_SOD))) {
                ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN);
            } else {
                ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN_ACSRY);
            }
            // QC#22834 End
            ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, closeRs.getString(TRX_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, closeRs.getString(TRX_LINE_NUM));
        } else {
            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_IN);
        }
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, closeRs.getString(INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, closeRs.getBigDecimal(RWS_STK_QTY));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, closeRs.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, closeRs.getString(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, closeRs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, closeRs.getString(RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.soNum, closeRs.getString(SO_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.soSlpNum, closeRs.getString(SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxSlpNum, closeRs.getString(INVTY_TRX_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && NLXSceConst.SCE_ORD_TP_CD_RM.equals(sceOrdTpCd)) {
            // Set Serial numbe
            RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
            rwsPutAwaySerWrkT.setSQLID("001");
            rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", closeRs.getString(WRK_TRX_ID));
            rwsPutAwaySerWrkT.setConditionValue("sqId01", closeRs.getString(SQ_ID));
            rwsPutAwaySerWrkT.setConditionValue("rwsNum01", closeRs.getString(RWS_NUM));
            rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", closeRs.getString(RWS_DTL_LINE_NUM));

            RWS_PUT_AWAY_SER_WRKTMsgArray msgArr = (RWS_PUT_AWAY_SER_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(rwsPutAwaySerWrkT);

            if (msgArr != null) {

                for (int i = 0; i < msgArr.getValidCount(); i++) {

                    ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, msgArr.no(i).serNum);
                }

                inPMsg.serNumList.setValidCount(msgArr.getValidCount());
            }
        }

        pMsgList.add(inPMsg);

        return pMsgList;
    }
    // QC#21574 ADD END

    /**
     * Check Reman(RM) Main Unit
     * @param param NLZC209001PMsg
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param trxHdrNum String
     * @param trxLineNum String
     * @return boolean
     */
    private boolean isRemanMainUnit(NLZC209001PMsg param,String trxHdrNum, String trxLineNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("trxOrdNum", trxHdrNum);
        queryParam.put("trxLineNum", trxLineNum);

        Map<String, Object> svcMachMstrMap = (Map<String, Object>) ssmBatchClient.queryObject("getRmMachMstrPk", queryParam);

        if (svcMachMstrMap == null) {

            return false;
        }

        SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
        smmTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(smmTMsg);

        if (smmTMsg == null) {
            return false;
        }

        BigDecimal svcConfigMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK");
        String convSerNum = (String) svcMachMstrMap.get("RMNF_TO_CMPT_SER_NUM");

        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return true;
        }

        SVC_CONFIG_MSTRTMsg prmTMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcConfigMstrPk, svcConfigMstrPk);
        prmTMsg = (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(prmTMsg);

        if (prmTMsg == null) {
            return true;
        }

        smmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, prmTMsg.svcMachMstrPk);
        smmTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(smmTMsg);

        if (smmTMsg != null) {
            if (ZYPCommonFunc.hasValue(convSerNum) && ZYPCommonFunc.hasValue(smmTMsg.serNum) && convSerNum.equals(smmTMsg.serNum.getValue())) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * createMachMstrPMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param mode String
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg createMachMstrPMsg(String glblCmpyCd, String slsDt, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String mode) {

        NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, mode);

        if (svcMachMstrTMsg != null) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, svcMachMstrTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, svcMachMstrTMsg.serNum.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, svcMachMstrTMsg.stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd.getValue());
        }

        if (ProcessMode.DISPOSAL.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.effThruDt, slsDt);
            // START 2023/09/22 T.Kuroda [QC#61265, ADD]
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
            // END   2022/09/22 T.Kuroda [QC#61265, ADD]
        }

        return machMstrPMsg;
    }
    
    /**
     * insertInbdVisForLossQty
     *  QC#29030 Add method.
     * @param msgMap S21ApiMessageMap
     * @param closeRs ResultSet
     * @param lossQty BigDecimal
     * @param poRcvNum String
     * @throws SQLException
     */
    private void insertInbdVisForLossQty(final S21ApiMessageMap msgMap, final ResultSet closeRs, BigDecimal lossQty, String poRcvNum) throws SQLException{
        
        INBD_VISTMsg inVisT = new INBD_VISTMsg();

        NLZC209001PMsg param = (NLZC209001PMsg) msgMap.getPmsg();

        String sysDtTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        String poOrdNum = NLXC014001.nullToEmpty(closeRs.getString(PO_ORD_NUM));
        String poOrdLineNum = NLXC014001.nullToEmpty(closeRs.getString(PO_ORD_DTL_LINE_NUM));
        String imptInvDoNum = getNextImptInvDoNum(param.glblCmpyCd.getValue(), poOrdNum, poOrdLineNum);
        
        BigDecimal inbdVisPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);

        ZYPEZDItemValueSetter.setValue(inVisT.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisPk, inbdVisPk);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdLtstRecFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisEventCd, INBD_VIS_EVENT.POYO_RECEIVE);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);
        ZYPEZDItemValueSetter.setValue(inVisT.inbdVisActlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inVisT.imptInvDoNum, imptInvDoNum);
        ZYPEZDItemValueSetter.setValue(inVisT.imptInvPoTpCd, "P"); // PRE_DO_VAL :NPZC109001
        ZYPEZDItemValueSetter.setValue(inVisT.mdseCd, closeRs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inVisT.visLocTpCd, VIS_LOC_TP.DC);
        ZYPEZDItemValueSetter.setValue(inVisT.visLocCd, closeRs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(inVisT.visLocNm, getInvtyLocNm(param.glblCmpyCd.getValue(), closeRs.getString(INVTY_LOC_CD)));
        ZYPEZDItemValueSetter.setValue(inVisT.invtyStkStsCd, closeRs.getString(RWS_INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inVisT.calcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inVisT.cratTs, sysDtTs);
        ZYPEZDItemValueSetter.setValue(inVisT.regdTs, sysDtTs);

        ZYPEZDItemValueSetter.setValue(inVisT.visQty, lossQty);

        ZYPEZDItemValueSetter.setValue(inVisT.etaEtdDt, S21StringUtil.subStringByLength(getpoRcvEtaDt(param.glblCmpyCd.getValue(), poRcvNum), 0, DATE_END_DIGIT));

        ZYPEZDItemValueSetter.setValue(inVisT.poyoIntfcId, "NLZC2090");

        S21ApiTBLAccessor.insert(inVisT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inVisT.getReturnCode())) {
            String[] list = new String[] {inVisT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, inbdVisPk), NLXCMsgHelper.toListedString(inVisT.glblCmpyCd.getValue(), inVisT.inbdVisPk.getValue()) };
            throw new S21AbendException(NLAM1133E, list);
        }
    }

    /**
     * getNextImptInvDoNum
     *  QC#29030 Add method.
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return nextImptInvDoNum String
     */
    private String getNextImptInvDoNum(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {

        /** Minimum Suffix Number */
        final String MIN_SUFFIX_NUM = "001";

        /** Maximum Suffix Number */
        final String MAX_SUFFIX_NUM = "999";
        /** Suffix Number Position */
        final int SUFFIX_NUM_POSITION = 11;

        /** Line Number Length */
        final int LINE_NUM_LENGTH = 3;

        String suffixNum = MIN_SUFFIX_NUM;

        INBD_VISTMsg inbdVisTMsg = selectInbdVisTMsgToImptInvDoNum(glblCmpyCd, poOrdNum, poOrdDtlLineNum);
        if (inbdVisTMsg != null) {
            String currentSuffixNum = inbdVisTMsg.imptInvDoNum.getValue().substring(SUFFIX_NUM_POSITION);
            if (!MAX_SUFFIX_NUM.equals(currentSuffixNum)) {
                int nextSuffixNum = Integer.parseInt(currentSuffixNum) + 1;
                suffixNum = ZYPCommonFunc.leftPad(String.valueOf(nextSuffixNum), LINE_NUM_LENGTH, "0");
            }
        }

        StringBuilder imptInvDoNum = new StringBuilder();
        imptInvDoNum.append(poOrdNum);
        imptInvDoNum.append(poOrdDtlLineNum);
        imptInvDoNum.append(suffixNum);

        return imptInvDoNum.toString();
    }

    /**
     * selectInbdVisTMsgToImptInvDoNum
     *  QC#29030 Add method.
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return INBD_VISTMsg
     */
    private static INBD_VISTMsg selectInbdVisTMsgToImptInvDoNum(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setSQLID("048");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inbdVisEventCd01", INBD_VIS_EVENT.POYO_RECEIVE);
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_IN_DC);
        StringBuilder imptInvDoNum = new StringBuilder();
        imptInvDoNum.append(poOrdNum);
        imptInvDoNum.append(poOrdDtlLineNum);
        imptInvDoNum.append("%");
        inMsg.setConditionValue("imptInvDoNum01", imptInvDoNum.toString());

        INBD_VISTMsgArray resultArray = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        INBD_VISTMsg result = null;
        if (resultArray != null && resultArray.getValidCount() > 0) {
            result = resultArray.no(0);
        }

        return result;
    }
}
