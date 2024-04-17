/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC010001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_HDRTMsg;
import business.db.RWS_CPLTTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_PUT_AWAYTMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_CONFTMsg;
import business.db.SHPG_ORD_CONF_DTLTMsg;
import business.db.SHPG_ORD_CUST_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.parts.NLZC001001PMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC010001PMsg;
import business.parts.NLZC205001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001;
import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NLX.NLXC025001.NLXC025001; // import
// com.canon.cusa.s21.common.NLX.NLXC100001.NLXC100001UpdateShippingStatus;
// 2013/05/13
// OCE WDS
// R-WH003
// SOL#094
// SO,
// Configuratio(SO
// Creation,Configuration)
// delete
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VIS_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NLZC0100:Sell Then Buy Direct Shipment
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         H.Haga          Create          N/A
 * 10/16/2009   Fujitsu         A.Akabane       Update          760
 * 11/18/2009   Fujitsu         T.Motoyama      Update          RQ0598
 * 02/03/2010   Fujistu         T.Motoyama      Update          N/A
 * 04/28/2010   Fujitsu         H.Nagashima     Update          4825
 * 06/01/2010   Fujitsu         R.Mori          Update          6741,6838
 * 04/05/2012   CSAI            M.Takahashi     Update          PROD(39018)
 * 11/14/2012   Fujitsu         S.Takami        Update          Oce WDS SOLOce WDS SOL#094 SO, Configuratio(SO Creation,Configuration)
 * 01/28/2013   Fujitsu         M.Takahashi     Update          WDS Defect#378
 * 05/03/2013   Hitachi         T.Kawazu        Update          QC1181
 * 2013/05/13   Fujitsu         S.Takami        Update          OCE WDS R-WH003 SOL#094 SO, Configuratio(SO Creation,Configuration)
 * 2013/07/10   Hitachi         T.Kawazu        Update          QC1365
 * 2013/08/30   Fujitsu         Y.Taoka         Update          Defect#1645
 * 2015/11/12   CITS            T.Kikuhara      Update          CSA V2.0
 * 2016/06/23   CSAI            K.Lee           Update          QC#10724
 * 2016/07/15   CITS            K.Ogino         Update          QC#11988
 * 2016/07/15   CITS            K.Ogino         Update          QC#11995
 * 2016/07/19   CITS            T.Kikuhara      Update          QC#11998
 * 2016/09/21   CSAI            T.Hakodate      Update          QC#14457
 * 2016/12/26   CITS            T.Wada          Update          QC#16667
 * 2018/01/17   CITS            T.Hakodate      UPDATE          QC#18460
 * 2018/07/09   CITS            K.Ogino         Update          QC#27063
 * 2018/12/25   Fujitsu         S.Ohki          Update          QC#29544
 * 2019/01/11   CITS            T.hakodate      Update          QC#29910
 * 2019/05/17   CITS            K.Ogino         Update          QC#50027
 * 2019/08/09   CITS            K.Ogino         Update          QC#52401
 *</pre>
 */
public class NLZC010001 extends S21ApiCommonBase implements TRX_RSN {

    /** */
    private static final String PROGRAM_ID = "NLZC010001:";

    /**  */
    private static final String NLZM1001E = "NLZM1001E";

    /**  */
    private static final String NLZM1004E = "NLZM1004E";

    /**  */
    private static final String NLZM1005E = "NLZM1005E";

    /**  */
    private static final String NLZM1030E = "NLZM1030E";

    /**  */
    private static final String NLAM1001E = "NLAM1001E";

    /**  */
    private static final String NLAM1002E = "NLAM1002E";

    /**  */
    private static final String NLAM1006E = "NLAM1006E";

    /**  */
    private static final String NLAM1013E = "NLAM1013E";

    /**  */
    private static final String NLAM1132E = "NLAM1132E";

    /**  */
    private static final String NLAM1133E = "NLAM1133E";

    /**  */
    private static final String NLAM1134E = "NLAM1134E";

    // DEL START 08/30/2013 Defect#1645
    /**  */
    // private static final String NLAM1160E = "NLAM1160E";
    // DEL END 08/30/2013 Defect#1645
    /**  */
    private static final String NLAM1161E = "NLAM1161E";

    /** */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** */
    private static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** */
    private static final String PO_RCV_NUM = "PO_RCV_NUM";

    /** */
    private static final String RWS_NUM = "RWS_NUM";

    /** */
    private static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** */
    private static final String INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /** */
    private static final String SO_NUM = "SO_NUM";

    /** */
    private static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** */
    private static final String INBD_VIS_PK = "INBD_VIS_PK";

    /** */
    private static final String SLS_DT = "SLS_DT";

    // DEL START 08/30/2013 Defect#1645
    /** */
    // private static final String TRIAL_LOAN_RSN_CD =
    // "TRIAL_LOAN_RSN_CD";
    // DEL END 08/30/2013 Defect#1645
    /** */
    private static final String BOL_NUM = "BOL_NUM";

    /** */
    private static final String PRO_NUM = "PRO_NUM";

    /** */
    private static final String VND_CD = "VND_CD";

    /** */
    private static final String SO_CUST_DATA_TP_CD = "SO_CUST_DATA_TP_CD";

    /** */
    private static final String INBD_OTBD_CD = "INBD_OTBD_CD";

    /** */
    private static final String WH_CD_PRH = "WH_CD_PRH";

    /** */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /** */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** */
    private static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** */
    private static final String PO_RCV_FROM_LOC_TP_CD = "PO_RCV_FROM_LOC_TP_CD";

    /** */
    private static final String PO_RCV_FROM_LOC_CD = "PO_RCV_FROM_LOC_CD";

    /** */
    private static final String PO_RCV_ETA_DT = "PO_RCV_ETA_DT";

    /** */
    private static final String SYS_SRC_CD_PRH = "SYS_SRC_CD_PRH";

    /** */
    private static final String PO_RCV_TRX_HDR_NUM = "PO_RCV_TRX_HDR_NUM";

    /** */
    private static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /** */
    private static final String STK_STS_CD = "STK_STS_CD";

    /** */
    private static final String PO_RCV_QTY = "PO_RCV_QTY";

    /** */
    private static final String PO_RCV_TRX_LINE_NUM = "PO_RCV_TRX_LINE_NUM";

    /** */
    private static final String MDSE_CD = "MDSE_CD";

    /** */
    private static final String CUST_PO_NUM = "CUST_PO_NUM";

    /** */
    private static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** */
    private static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** */
    private static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** */
    private static final String TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** */
    private static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /** */
    private static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** */
    private static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** */
    private static final String SYS_SRC_CD_SHPG = "SYS_SRC_CD_SHPG";

    /** */
    private static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    // 2018/12/25 QC#29544 Add Start
    /** */
    private static final String CUST_UOM_CD = "CUST_UOM_CD";

    /** */
    private static final String AVAL_SO_QTY = "AVAL_SO_QTY";
    // 2018/12/25 QC#29544 Add End

    // DEL START 08/30/2013 Defect#1645
    /** */
    // private static final String TRIAL_LOAN_RSN_CD_CPO =
    // "TRIAL_LOAN_RSN_CD_CPO";
    // DEL END 08/30/2013 Defect#1645
    /** */
    private static final String ADD_SHIP_TO_LOC_NM = "ADD_SHIP_TO_LOC_NM";

    /** */
    private static final String ADD_SHIP_TO_ADDL_LOC_NM = "ADD_SHIP_TO_ADDL_LOC_NM";

    /** */
    private static final String ADD_SHIP_TO_FIRST_LINE_ADDR = "ADD_SHIP_TO_FIRST_LINE_ADDR";

    /** */
    private static final String ADD_SHIP_TO_SCD_LINE_ADDR = "ADD_SHIP_TO_SCD_LINE_ADDR";

    /** */
    private static final String ADD_SHIP_TO_THIRD_LINE_ADDR = "ADD_SHIP_TO_THIRD_LINE_ADDR";

    /** */
    private static final String ADD_SHIP_TO_FRTH_LINE_ADDR = "ADD_SHIP_TO_FRTH_LINE_ADDR";

    /** */
    private static final String ADD_SHIP_TO_CTRY_CD = "ADD_SHIP_TO_CTRY_CD";

    /** */
    private static final String ADD_SHIP_TO_ST_CD = "ADD_SHIP_TO_ST_CD";

    /** */
    private static final String ADD_SHIP_TO_CTY_ADDR = "ADD_SHIP_TO_CTY_ADDR";

    /** */
    private static final String ADD_SHIP_TO_POST_CD = "ADD_SHIP_TO_POST_CD";

    // DEL START 08/30/2013 Defect#1645
    /** */
    // private static final String TRIAL_FINAL_PRPS_CD =
    // "TRIAL_FINAL_PRPS_CD";
    // DEL END 08/30/2013 Defect#1645
    /** */
    private static final String TRX_CD = "TRX_CD";

    /** */
    private static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** */
    private static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** */
    private static final String WH_CD = "WH_CD";

    /** */
    private static final String LOC_NM = "LOC_NM";

    /** */
    private static final String WH_PK = "WH_PK";

    /** */
    private static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** */
    private static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** */
    private static final String SCE_ORD_TP_CD_DB = "SCE_ORD_TP_CD_DB";

    /** */
    private static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";

    /** */
    private static final String SHIP_TO_CUST_PK = "SHIP_TO_CUST_PK";

    /** */
    private static final String SHIP_TP = "SHIP_TP";

    /** */
    private static final String GLBL_CMPY_CD_KEY = "glblCmpyCd01";

    /** */
    private static final String PO_RCV_NUM_KEY = "poRcvNum01";

    // ADD START 11/18/2015 CSA V2.0
    /** */
    private static final String CPO_ORD_NUM_KEY = "cpoOrdNum01";

    // ADD END 11/18/2015 CSA V2.0

    // TODO WH_SCHD logical remove is suspend by tsuda-san
    // /** */
    // private static final String PO_RCV_LINE_NUM_KEY =
    // "poRcvLineNum01";
    //
    // /** */
    // private static final String INVTY_LOC_CD_KEY = "invtyLocCd01";

    /** */
    private static final String SCE_INBD_KEY = "sceInbd01";

    /** */
    private static final String INBD_LTST_REC_FLG_KEY = "inbdLtstRecFlg01";

    /** */
    private static final String LF_PAD_CHAR = "0";

    /** */
    private static final int RWS_DTL_LINE_NUM_LENGTH = 3;

    /** */
    private static final int SO_SLP_NUM_LENGTH = 3;

    /** */
    private S21SsmLowLevelCodingClient ssmClient = null;

    /** */
    private static final String SELL_DIRECT_SSM_KEY = "getSellDirectDataRs";

    // ADD START 11/18/2015 CSA V2.0
    /** SQL NAME */
    private static final String SHIP_TP_SSM_KEY = "getShipTp";

    // ADD END 11/18/2015 CSA V2.0

    /** */
    private static final String RWS_STS_CD_PMSG_KEY = "rwsStsCd";

    /** */
    private static final String ACTL_RCV_QTY_PMSG_KEY = "actlRcvQty";

    /** */
    private static final String GLBL_CMPY_CD_PMSG_KEY = "glblCmpyCd";

    /** */
    private static final String PO_RCV_NUM_PMSG_KEY = "poRcvNum";

    /** */
    private static final String INBD_LTST_REC_FLG_PMSG_KEY = "inbdLtstRecFlg";

    /** */
    private String sceOrdTpCdOut;

    /** */
    private String sceTrxcdOut;

    /** */
    private String sceTrxRsncdOut;

    /** */
    private String systemDayTs;

    /** */
    private String sysDateTs;

    /** */
    private static final int CTAC_PTNR_LENGTH = 18;

    /** Locale: YYYYMMDDHHMMSSsss */
    private static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    // DEL START 11/06/2015 CSA V2.0
    /** DS SO Calling mode: New SO Creation */
    // private static final String DS_SO_API_MODE = "0";
    // DEL END 11/06/2015 CSA V2.0
    // ADD START 11/06/2015 CSA V2.0
    /** DS SO Calling mode: Drop Ship */
    private static final String DS_SO_API_MODE = "3";

    /** */
    private static final String SCE_ORD_TP_CD_DN = "DN";

    /** */
    private static final String SHIP_TP_LE = "LE";

    /** */
    private static final String SHIP_TP_LI = "LI";

    // ADD END 11/06/2015 CSA V2.0

    /** */
    private static final String RTL_WH_CD = "RTL_WH_CD";
    /** */
    private static final String RTL_SWH_CD = "RTL_SWH_CD";
    /** */
    private static final String SHIP_FROM_RTL_WH_CD = "SHIP_FROM_RTL_WH_CD";
    /** */
    private static final String SHIP_FROM_RTL_SWH_CD = "SHIP_FROM_RTL_SWH_CD";
    /** */
    private static final String SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";
    /** */
    private static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";
    /** */
    private static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";
    /** */
    private static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";
    /** */
    private static final String SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";
    /** */
    private static final String RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";
    /** */
    private static final String SER_APVL_REQ_FLG = "SER_APVL_REQ_FLG";
    /** */
    private static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** */
    private static final String SHIP_FROM_SO_NUM = "SHIP_FROM_SO_NUM";

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    // 2018/12/25 QC#29544 Add Start
    /** TOT_SHPG_WT Max Length */
    private static final int TOT_SHPG_WT_MAX_LG = 11;

    /** TOT_SHPG_WT Decimal Length */
    private static final int TOT_SHPG_WT_DECL_LG = 4;
    // 2018/12/25 QC#29544 Add End

    /**
     *
     */
    public NLZC010001() {
        super();
        this.ssmClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        sceOrdTpCdOut = "";
        sceTrxcdOut = "";
        sceTrxRsncdOut = "";
        systemDayTs = "";
        sysDateTs = "";
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.execute
     * @param params List<NLZC010001PMsg>
     * @param onBatchType onBatchType
     */
    public void execute(final List<NLZC010001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NLZC010001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.execute
     * @param param NLZC010001PMsg
     * @param onBatchType onBatchType
     */
    public void execute(final NLZC010001PMsg param, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] start", this);

        sceOrdTpCdOut = "";
        sceTrxcdOut = "";
        sceTrxRsncdOut = "";
        systemDayTs = "";
        sysDateTs = "";

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        updateSellThenBuyShip(msgMap, onBatchType);

        msgMap.flush();

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] end", this);
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.execute
     */
    protected void updateSellThenBuyShip(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        systemDayTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        sysDateTs = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);

        // ssmBatchClient
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        NLZC010001PMsg param = (NLZC010001PMsg) msgMap.getPmsg();

        if (checkInputParam(param, onBatchType) == true) {
            msgMap.addXxMsgId(NLZM1001E);
            return;
        }

        if (checkInputdb(param, onBatchType) == true) {
            msgMap.addXxMsgId(NLZM1005E);
            return;
        }

        /** ResultSet */
        ResultSet rsSellDirectShip = null;
        PreparedStatement stmtSellDirectShip = null;
        Map<String, String> queryParam = new HashMap<String, String>();

        // ADD START 11/18/2015 CSA V2.0
        /** ResultSet */
        ResultSet rsShipTp = null;
        PreparedStatement stmtShipTp = null;
        // ADD END 11/18/2015 CSA V2.0

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        try {

            queryParam.put(GLBL_CMPY_CD_KEY, param.glblCmpyCd.getValue());
            queryParam.put(PO_RCV_NUM_KEY, param.poRcvNum.getValue());
            queryParam.put(SCE_INBD_KEY, INBD_OTBD.INBOUND);

            stmtSellDirectShip = this.ssmClient.createPreparedStatement(SELL_DIRECT_SSM_KEY, queryParam, execParam);
            rsSellDirectShip = stmtSellDirectShip.executeQuery();

            if (rsSellDirectShip.next() == false) {
                msgMap.addXxMsgId(NLZM1004E);

                NLXC025001.outputLog(1, NLAM1132E, new String[] {SELL_DIRECT_SSM_KEY, NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), param.poRcvNum.getValue(), INBD_OTBD.INBOUND) }, onBatchType, this);

                return;
            }

            String errMsgID = checkDbParam(param, rsSellDirectShip, onBatchType);
            if (ZYPCommonFunc.hasValue(errMsgID) == true) {
                msgMap.addXxMsgId(errMsgID);
                return;
            }

            String nextRwsNum = "";
            nextRwsNum = "";
            if (ONBATCH_TYPE.BATCH.equals(onBatchType)) {
                nextRwsNum = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NLXSceConst.RWS_BATCH_KEY);
            } else if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                nextRwsNum = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NLXSceConst.RWS_ONLINE_KEY);
            }
            if (EZDDebugOutput.isDebug()) {
                EZDDebugOutput.println(1, PROGRAM_ID + "======== Next RwsNum Start =======>", this);
                EZDDebugOutput.println(1, PROGRAM_ID + "nextRwsNum   :" + nextRwsNum, this);
                EZDDebugOutput.println(1, PROGRAM_ID + "======== Next RwsNum End =======>", this);
            }

            String nextSoNum = "";
            if (ONBATCH_TYPE.BATCH.equals(onBatchType)) {
                nextSoNum = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NLXSceConst.SO_BATCH_KEY);
            } else if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                nextSoNum = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NLXSceConst.SO_ONLINE_KEY);
                // } else {
                // throw new S21AbendException(NLAM1131E, new String[]
                // {ONBATCHTYPE, ONBATCH_TYPE.OTHER.toString() });
            }
            if (EZDDebugOutput.isDebug()) {
                EZDDebugOutput.println(1, PROGRAM_ID + "======== Next SoNum Start =======>", this);
                EZDDebugOutput.println(1, PROGRAM_ID + "nextSoNum   :" + nextSoNum, this);
                EZDDebugOutput.println(1, PROGRAM_ID + "======== Next SoNum End =======>", this);
            }

            // ADD START 11/06/2015 CSA V2.0
            param.soNum.setValue(nextSoNum);
            // ADD END 11/06/2015 CSA V2.0

            INBD_VISTMsg inbdVisT = new INBD_VISTMsg();

            inbdVisT.setConditionValue(GLBL_CMPY_CD_KEY, param.glblCmpyCd.getValue());
            inbdVisT.setConditionValue(PO_RCV_NUM_KEY, param.poRcvNum.getValue());
            inbdVisT.setConditionValue(INBD_LTST_REC_FLG_KEY, ZYPConstant.FLG_ON_Y);
            inbdVisT.setSQLID("001");
            INBD_VISTMsgArray inbdVisArrayT = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inbdVisT);

            if (inbdVisArrayT.length() > 0) {

                INBD_VISTMsg inbdVisTKey = new INBD_VISTMsg();
                INBD_VISTMsg inbdVisTUp = new INBD_VISTMsg();

                inbdVisTKey.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                inbdVisTKey.poRcvNum.setValue(param.poRcvNum.getValue());
                inbdVisTKey.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);

                inbdVisTUp.inbdLtstRecFlg.setValue(ZYPConstant.FLG_OFF_N);

                int upCnt = S21ApiTBLAccessor.updateByPartialValue(inbdVisTKey, new String[] {GLBL_CMPY_CD_PMSG_KEY, PO_RCV_NUM_PMSG_KEY, INBD_LTST_REC_FLG_PMSG_KEY }, inbdVisTUp, new String[] {INBD_LTST_REC_FLG_PMSG_KEY });

                if (upCnt == 0) {
                    throw new S21AbendException(NLAM1134E, new String[] {inbdVisTKey.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM),
                            NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), param.poRcvNum.getValue()) });
                }
            }

            int cnt = 0;

            NLZC002001PMsg invtyUpdPmsg;
            List<NLZC002001PMsg> pmsgArray = new ArrayList<NLZC002001PMsg>();

            String jobDay = param.slsDt.getValue();

            boolean cur = rsSellDirectShip.first();
            boolean retIsWholesale = false;
            String trxSrcTpCd = null;
            String poRcvEtaDt = null;
            for (; cur == true;) {

                cnt = cnt + 1;

                // ADD START 11/18/2015 CSA V2.0
                queryParam.put(GLBL_CMPY_CD_KEY, param.glblCmpyCd.getValue());
                queryParam.put(CPO_ORD_NUM_KEY, rsSellDirectShip.getString(CPO_ORD_NUM));
                stmtShipTp = this.ssmClient.createPreparedStatement(SHIP_TP_SSM_KEY, queryParam, execParam);
                rsShipTp = stmtShipTp.executeQuery();
                // ADD END 11/18/2015 CSA V2.0

                insertRwsPutAway(rsSellDirectShip, jobDay, nextRwsNum, cnt);

                insertRwsDtl(rsSellDirectShip, nextRwsNum, cnt);

                updatePoRcvDtl(rsSellDirectShip, cnt);

                insertShpgOrdConfDtl(rsSellDirectShip, nextSoNum, cnt);

                insertShpgOrdDtl(rsSellDirectShip, nextSoNum, cnt);

                insertInbdVis(rsSellDirectShip, jobDay, nextRwsNum, cnt);

                // TODO WH_SCHD logical remove is suspend by tsuda-san
                // removeWhSchd(rsSellDirectShip);

                // MOD START 11/18/2015 CSA V2.0
                // pmsgArray = setPmsgArray(param, rsSellDirectShip,
                // nextRwsNum, nextSoNum, cnt, pmsgArray);
                pmsgArray = setPmsgArray(param, rsSellDirectShip, nextRwsNum, nextSoNum, cnt, pmsgArray, rsShipTp);
                // MOD END 11/18/2015 CSA V2.0

                if (cnt <= 1) {

                    String jobDayTs = jobDay + S21SystemDate.getCurrentSystemTime("HHmmss");
                    insertRwsCplt(rsSellDirectShip, jobDayTs, nextRwsNum);

                    insertRwsHdr(rsSellDirectShip, jobDayTs, nextRwsNum);
                    // QC#27063
                    insertShpgOrdConf(rsSellDirectShip, jobDayTs, nextSoNum);

                    insertShpgOrd(rsSellDirectShip, nextSoNum);

                    insertShpgOrdCustDtl(rsSellDirectShip, nextSoNum);

                    updatePoRcvHdr(rsSellDirectShip);

                    // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
                    // Creation,Configuration) add start
                    trxSrcTpCd = rsSellDirectShip.getString(TRX_SRC_TP_CD);
                    if (isWholesale(trxSrcTpCd)) {
                        retIsWholesale = true;
                        // UPD START 11/06/2015 CSA V2.0
                        // if (!callDsSOApi(msgMap, nextSoNum,
                        // onBatchType)) {
                        poRcvEtaDt = rsSellDirectShip.getString(PO_RCV_ETA_DT);
                        // if (!callDsSOApi(msgMap, nextSoNum,
                        // poRcvEtaDt, onBatchType)) {
                        // //UPD END 11/06/2015 CSA V2.0
                        // // error had been occurred at DS SO API,
                        // then return
                        // return;
                        // }
                        // 2013/05/13 OCE WDS R-WH003 SOL#094 SO,
                        // Configuratio(SO Creation,Configuration)
                        // delete start
                        // String resultErrMsgId =
                        // NLXC100001UpdateShippingStatus.updShpgStsAndInsEventForAPI(param.glblCmpyCd.getValue(),
                        // SHPG_STS.SHIPPED, nextSoNum, onBatchType);
                        // if (null != resultErrMsgId) {
                        // msgMap.addXxMsgId(resultErrMsgId);
                        // return;
                        // }
                        // 2013/05/13 OCE WDS R-WH003 SOL#094 SO,
                        // Configuratio(SO Creation,Configuration)
                        // delete end
                    } else {
                        retIsWholesale = false;
                    }
                    // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
                    // Creation,Configuration) add end
                }

                cur = rsSellDirectShip.next();

                // QC#29910 add start
                S21SsmLowLevelCodingClient.closeResource(stmtShipTp, rsShipTp);
                // QC#29910 add end
            }


            if (retIsWholesale) {
                if (!callSOApi(msgMap, nextSoNum, poRcvEtaDt, onBatchType)) {
                    return;
                }
            }

            // DEL START 11/06/2015 CSA V2.0
            /*
             * // START 2013/01/28 M.Takahashi [WDS Defect#378]
             * //List<NLZC002001PMsg> pmsgArrayForStockOut = new
             * ArrayList<NLZC002001PMsg>(); boolean retailFlg = false;
             * // END 2013/01/28 M.Takahashi [WDS Defect#378] //if
             * (EZDDebugOutput.isDebug()) { for (int i = 0; i <
             * pmsgArray.size(); i++) { invtyUpdPmsg =
             * pmsgArray.get(i);
             */
            // DEL END 11/06/2015 CSA V2.0
            /*
             * EZDDebugOutput.println(1, PROGRAM_ID + "PMsg cnt:" +
             * String.valueOf(i), this); EZDDebugOutput.println(1,
             * PROGRAM_ID + "xxRqstTpCd:" +
             * invtyUpdPmsg.xxRqstTpCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" +
             * invtyUpdPmsg.glblCmpyCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "trxCd:" +
             * invtyUpdPmsg.trxCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "trxRsnCd:" +
             * invtyUpdPmsg.trxRsnCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "xxTrxDtlCd:" +
             * invtyUpdPmsg.xxTrxDtlCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "mdseCd:" +
             * invtyUpdPmsg.mdseCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "invtyLocCd:" +
             * invtyUpdPmsg.invtyLocCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "locStsCd:" +
             * invtyUpdPmsg.locStsCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "stkStsCd:" +
             * invtyUpdPmsg.stkStsCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "xxRqstQty:" +
             * invtyUpdPmsg.xxRqstQty.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "invtyTrxDt:" +
             * invtyUpdPmsg.invtyTrxDt.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "xxSysTp:" +
             * invtyUpdPmsg.xxSysTp.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "sysSrcCd:" +
             * invtyUpdPmsg.sysSrcCd.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "soNum:" +
             * invtyUpdPmsg.soNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "soSlpNum:" +
             * invtyUpdPmsg.soSlpNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "cpoOrdNum:" +
             * invtyUpdPmsg.cpoOrdNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "cpoDtlLineNum:"
             * + invtyUpdPmsg.cpoDtlLineNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID +
             * "cpoDtlLineSubNum:" +
             * invtyUpdPmsg.cpoDtlLineSubNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "origCpoOrdNum:"
             * + invtyUpdPmsg.origCpoOrdNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID +
             * "origCpoDtlLineNum:" +
             * invtyUpdPmsg.origCpoDtlLineNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID +
             * "origCpoDtlLineSubNum:" +
             * invtyUpdPmsg.origCpoDtlLineSubNum.getValue(), this);
             * EZDDebugOutput.println(1, PROGRAM_ID + "uomCd:" +
             * invtyUpdPmsg.uomCd.getValue(), this);
             */

            // DEL START 11/06/2015 CSA V2.0
            /*
             * // split invtyUpdPmsg for allocation if
             * (TRX.MOVEMENT.equals(invtyUpdPmsg.trxCd.getValue()) &&
             * //
             * TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT.equals(invtyUpdPmsg
             * .trxRsnCd.getValue())) { // START 2013/01/28
             * M.Takahashi [WDS Defect#378]
             * //pmsgArrayForStockOut.add(invtyUpdPmsg);
             * //pmsgArray.remove(i); retailFlg = true; break; } //}
             * else if
             * (TRX.MOVEMENT.equals(invtyUpdPmsg.trxCd.getValue()) &&
             * // //
             * TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_IN.equals(invtyUpdPmsg
             * .trxRsnCd.getValue())) { //
             * pmsgArrayForStockOut.add(invtyUpdPmsg); //
             * pmsgArray.remove(i); //} // END 2013/01/28 M.Takahashi
             * [WDS Defect#378] } // } // START 2013/01/28 M.Takahashi
             * [WDS Defect#378] if (retailFlg) { List<NLZC002001PMsg>
             * pmsgArrayForPurchaseStockIn = new
             * ArrayList<NLZC002001PMsg>(); List<NLZC002001PMsg>
             * pmsgArrayForAllocation = new
             * ArrayList<NLZC002001PMsg>(); List<NLZC002001PMsg>
             * pmsgArrayStockInOut = new ArrayList<NLZC002001PMsg>();
             * for (int i = 0; i < pmsgArray.size(); i++) {
             * invtyUpdPmsg = pmsgArray.get(i); // split invtyUpdPmsg
             * for allocation if
             * (TRX.MOVEMENT.equals(invtyUpdPmsg.trxCd.getValue()) &&
             * //
             * TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT.equals(invtyUpdPmsg
             * .trxRsnCd.getValue())) {
             * pmsgArrayForAllocation.add(invtyUpdPmsg);
             * pmsgArrayStockInOut.add(invtyUpdPmsg); } else if
             * (TRX.PURCHASE_STOCK_IN
             * .equals(invtyUpdPmsg.trxCd.getValue())) {
             * pmsgArrayForPurchaseStockIn.add(invtyUpdPmsg); } else {
             * pmsgArrayStockInOut.add(invtyUpdPmsg); } } // Execute
             * ALL Purchase Stock-In NLZC002001 api = new
             * NLZC002001(); api.execute(pmsgArrayForPurchaseStockIn,
             * onBatchType); for (int i = 0; i <
             * pmsgArrayForPurchaseStockIn.size(); i++) { invtyUpdPmsg
             * = pmsgArrayForPurchaseStockIn.get(i); List<String>
             * errList = S21ApiUtil.getXxMsgIdList(invtyUpdPmsg); for
             * (String xxMsgId : errList) {
             * msgMap.addXxMsgId(xxMsgId); NLXC025001.outputLog(1,
             * xxMsgId, null, onBatchType, this); } } // Execute ALL
             * Allocation For DIRECT_SALE_SHIPMENT_STOCK_OUT if
             * (!pmsgArrayForAllocation.isEmpty()) { // Inventory
             * Allocation API NLZC001001 alzc0010 = new NLZC001001();
             * for (int i = 0; i < pmsgArrayForAllocation.size(); i++)
             * { invtyUpdPmsg = pmsgArrayForAllocation.get(i); //
             * Inventory Allocation NLZC001001PMsg invtyAllocPMsg =
             * new NLZC001001PMsg();
             * invtyAllocPMsg.xxProcTpCd.setValue
             * (NLZC001001.PROC_TP_ALLOC);
             * invtyAllocPMsg.glblCmpyCd.setValue
             * (invtyUpdPmsg.glblCmpyCd.getValue());
             * invtyAllocPMsg.mdseCd
             * .setValue(invtyUpdPmsg.mdseCd.getValue());
             * invtyAllocPMsg
             * .invtyLocCd.setValue(invtyUpdPmsg.invtyLocCd
             * .getValue());
             * invtyAllocPMsg.locStsCd.setValue(invtyUpdPmsg
             * .locStsCd.getValue());
             * invtyAllocPMsg.stkStsCd.setValue(
             * invtyUpdPmsg.stkStsCd.getValue());
             * invtyAllocPMsg.xxRqstQty
             * .setValue(invtyUpdPmsg.xxRqstQty.getValue());
             * invtyAllocPMsg
             * .xxAllocOpt.setValue(NLZC001001.OPT_CHK_FREE_OH); //
             * Inventory Allocation API is executed for
             * DIRECT_SALE_SHIPMENT_STOCK_OUT
             * alzc0010.execute(invtyAllocPMsg, onBatchType); } } //
             * Execute ALL remaining Stock-Out and Stock-In
             * api.execute(pmsgArrayStockInOut, onBatchType); for (int
             * i = 0; i < pmsgArrayStockInOut.size(); i++) {
             * invtyUpdPmsg = pmsgArrayStockInOut.get(i); List<String>
             * errList = S21ApiUtil.getXxMsgIdList(invtyUpdPmsg); for
             * (String xxMsgId : errList) {
             * msgMap.addXxMsgId(xxMsgId); NLXC025001.outputLog(1,
             * xxMsgId, null, onBatchType, this); } } } else {
             * NLZC002001 api = new NLZC002001();
             * api.execute(pmsgArray, onBatchType); for (int i = 0; i
             * < pmsgArray.size(); i++) { invtyUpdPmsg =
             * pmsgArray.get(i); List<String> errList =
             * S21ApiUtil.getXxMsgIdList(invtyUpdPmsg); for (String
             * xxMsgId : errList) { msgMap.addXxMsgId(xxMsgId);
             * NLXC025001.outputLog(1, xxMsgId, null, onBatchType,
             * this); } } } //NLZC002001 api = new NLZC002001();
             * //api.execute(pmsgArray, onBatchType); //for (int i =
             * 0; i < pmsgArray.size(); i++) { // invtyUpdPmsg =
             * pmsgArray.get(i); // List<String> errList =
             * S21ApiUtil.getXxMsgIdList(invtyUpdPmsg); // for (String
             * xxMsgId : errList) { // msgMap.addXxMsgId(xxMsgId); //
             * NLXC025001.outputLog(1, xxMsgId, null, onBatchType,
             * this); // } //} //if (!pmsgArrayForStockOut.isEmpty())
             * { // invtyUpdPmsg = pmsgArrayForStockOut.get(0); // //
             * Inventory Allocation // NLZC001001PMsg invtyAllocPMsg =
             * new NLZC001001PMsg(); //
             * invtyAllocPMsg.xxProcTpCd.setValue
             * (NLZC001001.PROC_TP_ALLOC); //
             * invtyAllocPMsg.glblCmpyCd
             * .setValue(invtyUpdPmsg.glblCmpyCd.getValue()); //
             * invtyAllocPMsg
             * .mdseCd.setValue(invtyUpdPmsg.mdseCd.getValue()); //
             * invtyAllocPMsg
             * .invtyLocCd.setValue(invtyUpdPmsg.invtyLocCd
             * .getValue()); //
             * invtyAllocPMsg.locStsCd.setValue(invtyUpdPmsg
             * .locStsCd.getValue()); //
             * invtyAllocPMsg.stkStsCd.setValue
             * (invtyUpdPmsg.stkStsCd.getValue()); //
             * invtyAllocPMsg.xxRqstQty
             * .setValue(invtyUpdPmsg.xxRqstQty.getValue()); //
             * invtyAllocPMsg
             * .xxAllocOpt.setValue(NLZC001001.OPT_CHK_FREE_OH); // //
             * Inventory Allocation API is executed for
             * DIRECT_SALE_SHIPMENT_STOCK_OUT // NLZC001001 alzc0010 =
             * new NLZC001001(); // alzc0010.execute(invtyAllocPMsg,
             * onBatchType); // // api = new NLZC002001(); //
             * api.execute(pmsgArrayForStockOut, onBatchType); // for
             * (int i = 0; i < pmsgArrayForStockOut.size(); i++) { //
             * invtyUpdPmsg = pmsgArrayForStockOut.get(i); //
             * List<String> errList =
             * S21ApiUtil.getXxMsgIdList(invtyUpdPmsg); // for (String
             * xxMsgId : errList) { // msgMap.addXxMsgId(xxMsgId); //
             * NLXC025001.outputLog(1, xxMsgId, null, onBatchType,
             * this); // } // } //} // END 2013/01/28 M.Takahashi [WDS
             * Defect#378]
             */
            // DEL END 11/06/2015 CSA V2.0
            // ADD START 11/06/2015 CSA V2.0
            List<NLZC002001PMsg> pmsgArrayForPurchaseStockIn = new ArrayList<NLZC002001PMsg>();
            List<NLZC002001PMsg> pmsgArrayForAllocation = new ArrayList<NLZC002001PMsg>();
            List<NLZC002001PMsg> pmsgArrayStockInOut = new ArrayList<NLZC002001PMsg>();

            for (int i = 0; i < pmsgArray.size(); i++) {
                invtyUpdPmsg = pmsgArray.get(i);

                // split Purchase Stock In and Allocation Data
                if (TRX.PURCHASE_STOCK_IN.equals(invtyUpdPmsg.trxCd.getValue()) || TRX_RSN.OFF_THE_BOOK_STOCK_IN.equals(invtyUpdPmsg.trxRsnCd.getValue())) {
                    pmsgArrayForPurchaseStockIn.add(invtyUpdPmsg);
                    pmsgArrayForAllocation.add(invtyUpdPmsg);
                } else {
                    pmsgArrayStockInOut.add(invtyUpdPmsg);
                }

                // split for Inventory Allocation
                // if
                // (TRX_RSN.OFF_THE_BOOK_STOCK_IN.equals(invtyUpdPmsg.trxRsnCd.getValue())
                // ||
                // TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_OUT.equals(invtyUpdPmsg.trxRsnCd.getValue())
                // ||
                // TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_IN.equals(invtyUpdPmsg.trxRsnCd.getValue())
                // ) {
                // pmsgArrayForAllocation.add(invtyUpdPmsg);
                // }
            }

            // Execute Inventory Update API(Purchase Stock In)
            NLZC002001 api = new NLZC002001();
            api.execute(pmsgArrayForPurchaseStockIn, onBatchType);

            for (int i = 0; i < pmsgArrayForPurchaseStockIn.size(); i++) {
                invtyUpdPmsg = pmsgArrayForPurchaseStockIn.get(i);
                List<String> errList = S21ApiUtil.getXxMsgIdList(invtyUpdPmsg);
                for (String xxMsgId : errList) {
                    msgMap.addXxMsgId(xxMsgId);
                    NLXC025001.outputLog(1, xxMsgId, null, onBatchType, this);
                }
            }

            // Execute Inventory Allocation API
            if (!pmsgArrayForAllocation.isEmpty()) {

                NLZC001001 alzc0010 = new NLZC001001();

                for (int i = 0; i < pmsgArrayForAllocation.size(); i++) {
                    invtyUpdPmsg = pmsgArrayForAllocation.get(i);

                    NLZC001001PMsg invtyAllocPMsg = new NLZC001001PMsg();
                    invtyAllocPMsg.xxProcTpCd.setValue(NLZC001001.PROC_TP_ALLOC);
                    invtyAllocPMsg.glblCmpyCd.setValue(invtyUpdPmsg.glblCmpyCd.getValue());
                    invtyAllocPMsg.mdseCd.setValue(invtyUpdPmsg.mdseCd.getValue());
                    invtyAllocPMsg.invtyLocCd.setValue(invtyUpdPmsg.invtyLocCd.getValue());
                    invtyAllocPMsg.locStsCd.setValue(invtyUpdPmsg.locStsCd.getValue());
                    invtyAllocPMsg.stkStsCd.setValue(invtyUpdPmsg.stkStsCd.getValue());
                    invtyAllocPMsg.xxRqstQty.setValue(invtyUpdPmsg.xxRqstQty.getValue());
                    invtyAllocPMsg.xxAllocOpt.setValue(NLZC001001.OPT_CHK_FREE_OH);

                    alzc0010.execute(invtyAllocPMsg, onBatchType);
                }
            }

            // Execute Inventory Update API remaining Stock-Out and
            // Stock-In
            api.execute(pmsgArrayStockInOut, onBatchType);

            for (int i = 0; i < pmsgArrayStockInOut.size(); i++) {
                invtyUpdPmsg = pmsgArrayStockInOut.get(i);
                List<String> errList = S21ApiUtil.getXxMsgIdList(invtyUpdPmsg);
                for (String xxMsgId : errList) {
                    msgMap.addXxMsgId(xxMsgId);
                    NLXC025001.outputLog(1, xxMsgId, null, onBatchType, this);
                }
            }
            // ADD END 11/06/2015 CSA V2.0

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSellDirectShip, rsSellDirectShip);
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.inputCheck
     */
    protected boolean checkInputParam(NLZC010001PMsg param, ONBATCH_TYPE onBatchType) {

        boolean errFlg = false;

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {GLBL_CMPY_CD }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.sysSrcCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {SYS_SRC_CD }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.poRcvNum)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_NUM }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {SLS_DT }, onBatchType, this);
            errFlg = true;
        }

        return errFlg;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.checkInputdb
     */
    protected boolean checkInputdb(NLZC010001PMsg param, ONBATCH_TYPE onBatchType) {

        boolean errFlg = false;

        if (!(ZYPCodeDataUtil.hasCodeValue(NLXSceConst.TBL_GLBL_CMPY, param.glblCmpyCd.getValue(), param.glblCmpyCd.getValue()))) {
            NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_GLBL_CMPY, GLBL_CMPY_CD, param.glblCmpyCd.getValue() }, onBatchType, this);
            errFlg = true;
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ sysSrcCd ]" + param.sysSrcCd.getValue(), this);
        if (!(ZYPCodeDataUtil.hasCodeValue(NLXSceConst.TBL_SYS_SRC, param.glblCmpyCd.getValue(), param.sysSrcCd.getValue()))) {
            NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_SYS_SRC, SYS_SRC_CD, param.sysSrcCd.getValue() }, onBatchType, this);
            errFlg = true;
        }

        return errFlg;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.checkDbParam
     */
    protected String checkDbParam(NLZC010001PMsg param, ResultSet rs, ONBATCH_TYPE onBatchType) throws SQLException {

        String errMsgID = "";
        boolean errFlg = false;

        boolean cur = rs.first();
        for (; cur == true;) {

            // if (!ZYPCommonFunc.hasValue(rs.getString(TRX_HDR_NUM)))
            // {
            // NLXC025001.outputLog(1, NLAM1002E, new String[]
            // {NLXSceConst.TBL_SHPG_PLN, SHPG_PLN_NUM,
            // rs.getString(SHPG_PLN_NUM), TRX_HDR_NUM }, onBatchType,
            // this);
            // errFlg = true;
            // }

            if (!ZYPCommonFunc.hasValue(rs.getString(TRX_LINE_NUM))) {
                NLXC025001.outputLog(1, NLAM1002E, new String[] {NLXSceConst.TBL_SHPG_PLN, SHPG_PLN_NUM, rs.getString(SHPG_PLN_NUM), TRX_LINE_NUM }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(rs.getString(TRX_LINE_SUB_NUM))) {
                NLXC025001.outputLog(1, NLAM1002E, new String[] {NLXSceConst.TBL_SHPG_PLN, SHPG_PLN_NUM, rs.getString(SHPG_PLN_NUM), TRX_LINE_SUB_NUM }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(rs.getString(SHIP_TO_CUST_CD))) {
                NLXC025001.outputLog(1, NLAM1002E, new String[] {NLXSceConst.TBL_SHPG_PLN, SHPG_PLN_NUM, rs.getString(SHPG_PLN_NUM), SHIP_TO_CUST_CD }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(rs.getString(SYS_SRC_CD_SHPG))) {
                NLXC025001.outputLog(1, NLAM1002E, new String[] {NLXSceConst.TBL_SHPG_PLN, SHPG_PLN_NUM, rs.getString(SHPG_PLN_NUM), SYS_SRC_CD }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(rs.getString(CPO_ORD_TP_CD))) {
                NLXC025001.outputLog(1, NLAM1002E, new String[] {NLXSceConst.TBL_CPO, CPO_ORD_NUM, rs.getString(CPO_ORD_NUM), CPO_ORD_TP_CD }, onBatchType, this);
                errFlg = true;
            }

            // DEL START 08/30/2013 Defect#1645
            // if
            // ((CPO_ORD_TP.TRIAL).equals(rs.getString(CPO_ORD_TP_CD)))
            // {
            // if
            // (!ZYPCommonFunc.hasValue(rs.getString(TRIAL_LOAN_RSN_CD_CPO)))
            // {
            // NLXC025001.outputLog(1, NLAM1002E, new String[]
            // {NLXSceConst.TBL_CPO, CPO_ORD_NUM,
            // rs.getString(CPO_ORD_NUM), TRIAL_LOAN_RSN_CD },
            // onBatchType, this);
            // errFlg = true;
            // }
            // }
            // DEL END 08/30/2013 Defect#1645

            if (errFlg == true) {
                errMsgID = NLZM1030E;
                return errMsgID;
            }

            // DEL START 08/30/2013 Defect#1645
            // if
            // ((CPO_ORD_TP.TRIAL).equals(rs.getString(CPO_ORD_TP_CD)))
            // {
            // if
            // (!ZYPCommonFunc.hasValue(rs.getString(TRIAL_FINAL_PRPS_CD)))
            // {
            // throw new S21AbendException(NLAM1001E, new String[]
            // {NLXSceConst.TBL_TRIAL_LOAN_RSN, TRIAL_LOAN_RSN_CD,
            // rs.getString(TRIAL_LOAN_RSN_CD_CPO) });
            // } else if
            // ((!TRIAL_FINAL_PRPS.TRIAL_USE.equals(rs.getString(TRIAL_FINAL_PRPS_CD)))
            // &&
            // (!TRIAL_FINAL_PRPS.TRIAL_SALE.equals(rs.getString(TRIAL_FINAL_PRPS_CD))))
            // {
            // throw new S21AbendException(NLAM1160E, new String[]
            // {rs.getString(TRIAL_LOAN_RSN_CD_CPO),
            // rs.getString(TRIAL_FINAL_PRPS_CD) });
            // }
            // }
            // DEL END 08/30/2013 Defect#1645

            if (!ZYPCommonFunc.hasValue(rs.getString(SCE_ORD_TP_CD_DB))) {
                throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(SCE_ORD_TP_CD, INBD_OTBD_CD), NLXCMsgHelper.toListedString(rs.getString(SCE_ORD_TP_CD), INBD_OTBD.INBOUND) });
            }

            if (!ZYPCommonFunc.hasValue(rs.getString(WH_PK))) {
                throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_WH, WH_CD, rs.getString(WH_CD_PRH) });
            }

            // if
            // (!ZYPCommonFunc.hasValue(rs.getString(SHIP_TO_CUST_PK)))
            // {
            // throw new S21AbendException(NLAM1001E, new String[]
            // {NLXSceConst.TBL_SHIP_TO_CUST,
            // NLXCMsgHelper.toListedString(GLBL_CMPY_CD,
            // SHIP_TO_CUST_CD),
            // NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(),
            // rs.getString(SHIP_TO_CUST_CD)) });
            // }

            if (!ZYPCommonFunc.hasValue(rs.getString(BILL_TO_CUST_CD))) {
                NLXC025001.outputLog(1, NLAM1161E, new String[] {NLXSceConst.TBL_SHIP_TO_CUST, BILL_TO_CUST_CD }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(rs.getString(SELL_TO_CUST_CD))) {
                NLXC025001.outputLog(1, NLAM1161E, new String[] {NLXSceConst.TBL_SHIP_TO_CUST, SELL_TO_CUST_CD }, onBatchType, this);
                errFlg = true;
            }

            if (errFlg == true) {
                errMsgID = NLZM1005E;
                return errMsgID;
            }

            if (!ZYPCommonFunc.hasValue(sceOrdTpCdOut)) {

                // DEL START 11/06/2015 CSA V2.0
                /*
                 * String cpoOrdTpCd = rs.getString(CPO_ORD_TP_CD); //
                 * 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
                 * Creation,Configuration) add start String trxSrcTpCd
                 * = rs.getString(TRX_SRC_TP_CD); if
                 * (isWholesale(trxSrcTpCd)) { sceOrdTpCdOut =
                 * NLXSceConst.SCE_ORD_TYPE_RS; // 11/14/2012 Oce WDS
                 * SOL#094 SO, Configuratio(SO Creation,Configuration)
                 * add end } else if
                 * (CPO_ORD_TP.SALES.equals(cpoOrdTpCd) ||
                 * CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd)) { //
                 * 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
                 * Creation,Configuration) update this "if" to
                 * "else if" sceOrdTpCdOut =
                 * NLXSceConst.SCE_ORD_TYPE_REGULAR; } else if
                 * (CPO_ORD_TP.LOAN.equals(cpoOrdTpCd)) {
                 * sceOrdTpCdOut = NLXSceConst.SCE_ORD_TYPE_LOAN; }
                 * else if (CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd)) {
                 * //MOD START 08/30/2013 Defect#1645 // if
                 * (TRIAL_FINAL_PRPS
                 * .TRIAL_USE.equals(rs.getString(TRIAL_FINAL_PRPS_CD
                 * ))) { // sceOrdTpCdOut =
                 * NLXSceConst.SCE_ORD_TYPE_TRAIL_USE; // } else { //
                 * sceOrdTpCdOut =
                 * NLXSceConst.SCE_ORD_TYPE_TRAIL_SALE; // }
                 * sceOrdTpCdOut = NLXSceConst.SCE_ORD_TYPE_TRAIL_USE;
                 * //MOD END 08/30/2013 Defect#1645 } else if
                 * (CPO_ORD_TP.PURCHASE.equals(cpoOrdTpCd) ||
                 * CPO_ORD_TP.RENTAL.equals(cpoOrdTpCd) ||
                 * CPO_ORD_TP.LEASE.equals(cpoOrdTpCd) ||
                 * CPO_ORD_TP.SERVICE.equals(cpoOrdTpCd) ||
                 * CPO_ORD_TP.RETAIL_TRIAL.equals(cpoOrdTpCd) ||
                 * CPO_ORD_TP.RETAIL_ORDER.equals(cpoOrdTpCd)) {
                 * sceOrdTpCdOut = NLXSceConst.SCE_ORD_TYPE_RS; }
                 */
                // DEL END 11/06/2015 CSA V2.0
                // ADD START 11/06/2015 CSA V2.0
                sceOrdTpCdOut = NLXSceConst.SCE_ORD_TYPE_RS;
                // ADD END 11/06/2015 CSA V2.0

                SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
                sceOrdTpT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                sceOrdTpT.sceOrdTpCd.setValue(sceOrdTpCdOut);
                sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.OUTBOUND);

                sceOrdTpT = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpT);
                if (sceOrdTpT == null) {
                    throw new S21AbendException(NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(SCE_ORD_TP_CD, INBD_OTBD_CD), NLXCMsgHelper.toListedString(sceOrdTpCdOut, INBD_OTBD.OUTBOUND) });
                }
                sceTrxcdOut = sceOrdTpT.trxCd.getValue();
                sceTrxRsncdOut = sceOrdTpT.trxRsnCd.getValue();

            }
            cur = rs.next();
        }

        return errMsgID;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgArray
     */
    // MOD START 11/18/2015 CSA V2.0
    // protected List<NLZC002001PMsg> setPmsgArray(NLZC010001PMsg
    // param, ResultSet rs, String rwsNum, String soNum, int cnt,
    // List<NLZC002001PMsg> pmsgArray) throws SQLException {
    protected List<NLZC002001PMsg> setPmsgArray(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray, ResultSet rsShipTp) throws SQLException {
        // MOD END 11/18/2015 CSA V2.0

        // DEL START 11/18/2015 CSA V2.0
        /*
         * // Domestic Purchase Stock-In NLZC002001PMsg pmsg = new
         * NLZC002001PMsg();
         * ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd,
         * NLZC002001.RQST_STOCK_IN);
         * ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd,
         * param.glblCmpyCd.getValue());
         * ZYPEZDItemValueSetter.setValue(pmsg.trxCd,
         * TRX.PURCHASE_STOCK_IN);
         * ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd,
         * PURCHASE_STOCK_IN);
         * ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd,
         * NLZC002001.TRX_DTL_SELL_THEN_BUY);
         * ZYPEZDItemValueSetter.setValue(pmsg.mdseCd,
         * rs.getString(MDSE_CD));
         * ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd,
         * rs.getString(WH_CD_PRH));
         * ZYPEZDItemValueSetter.setValue(pmsg.locStsCd,
         * LOC_STS.DC_STOCK);
         * ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd,
         * rs.getString(STK_STS_CD));
         * ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty,
         * rs.getBigDecimal(PO_RCV_QTY));
         * ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt,
         * param.slsDt.getValue());
         * ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp,
         * NLZC002001.SYS_TP_INBD);
         * ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd,
         * rs.getString(SYS_SRC_CD_PRH));
         * ZYPEZDItemValueSetter.setValue(pmsg.poRcvNum,
         * rs.getString(PO_RCV_NUM));
         * ZYPEZDItemValueSetter.setValue(pmsg.poRcvLineNum,
         * rs.getString(PO_RCV_LINE_NUM));
         * ZYPEZDItemValueSetter.setValue(pmsg.rwsNum, rwsNum);
         * ZYPEZDItemValueSetter.setValue(pmsg.rwsRefNum,
         * rs.getString(RWS_REF_NUM));
         * ZYPEZDItemValueSetter.setValue(pmsg.rwsDtlLineNum,
         * ZYPCommonFunc.leftPad(String.valueOf(cnt),
         * RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
         * ZYPEZDItemValueSetter.setValue(pmsg.poOrdNum,
         * rs.getString(PO_RCV_TRX_HDR_NUM));
         * ZYPEZDItemValueSetter.setValue(pmsg.poOrdDtlLineNum,
         * rs.getString(PO_RCV_TRX_LINE_NUM));
         * ZYPEZDItemValueSetter.setValue(pmsg.vndCd,
         * rs.getString(PO_RCV_FROM_LOC_CD));
         * ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
         * pmsgArray.add(pmsg); // Stock Out and Stock In String
         * cpoOrdTpCd = rs.getString(CPO_ORD_TP_CD); // 11/14/2012 Oce
         * WDS SOL#094 SO, Configuratio(SO Creation,Configuration) add
         * start String trxSrcTpCd = rs.getString(TRX_SRC_TP_CD); if
         * (isWholesale(trxSrcTpCd)) { pmsgArray = setPmsgRtl(param,
         * rs, rwsNum, soNum, cnt, pmsgArray); // 11/14/2012 Oce WDS
         * SOL#094 SO, Configuratio(SO Creation,Configuration) add end
         * // Add QC1365 Start } else if
         * (CPO_ORD_TP.PURCHASE.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.RENTAL.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.LEASE.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.SERVICE.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.RETAIL_TRIAL.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.RETAIL_ORDER.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.SALES.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.LOAN.equals(cpoOrdTpCd) ||
         * CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd)) { pmsgArray =
         * setPmsgDS(param, rs, rwsNum, soNum, cnt, pmsgArray); } //
         * Add QC1365 End //Delete QC1365 Start // } else if
         * (CPO_ORD_TP.SALES.equals(cpoOrdTpCd)) { // // 11/14/2012
         * Oce WDS SOL#094 SO, Configuratio(SO Creation,Configuration)
         * update this "if" to "else if" // pmsgArray =
         * setPmsgSales(param, rs, rwsNum, soNum, cnt, pmsgArray); //
         * // } else if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd)) { //
         * pmsgArray = setPmsgExpence(param, rs, rwsNum, soNum, cnt,
         * pmsgArray); // // } else if
         * (CPO_ORD_TP.LOAN.equals(cpoOrdTpCd)) { // pmsgArray =
         * setPmsgLoan(param, rs, rwsNum, soNum, cnt, pmsgArray); //
         * // } else if (CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd)) { // //
         * if(TRIAL_FINAL_PRPS.TRIAL_USE.equals(rs.getString(
         * TRIAL_FINAL_PRPS_CD))) { // pmsgArray =
         * setPmsgTrailUse(param, rs, rwsNum, soNum, cnt, pmsgArray);
         * // } else { // pmsgArray = setPmsgTrailSales(param, rs,
         * rwsNum, soNum, cnt, pmsgArray); // } // // } else if
         * (CPO_ORD_TP.PURCHASE.equals(cpoOrdTpCd) // ||
         * CPO_ORD_TP.RENTAL.equals(cpoOrdTpCd) // ||
         * CPO_ORD_TP.LEASE.equals(cpoOrdTpCd) // ||
         * CPO_ORD_TP.SERVICE.equals(cpoOrdTpCd) // ||
         * CPO_ORD_TP.RETAIL_TRIAL.equals(cpoOrdTpCd) // ||
         * CPO_ORD_TP.RETAIL_ORDER.equals(cpoOrdTpCd)) { // pmsgArray
         * = setPmsgRtl(param, rs, rwsNum, soNum, cnt, pmsgArray); //
         * } //Delete QC1365 End
         */
        // DEL END 11/18/2015 CSA V2.0
        // ADD START 11/18/2015 CSA V2.0
        if (SCE_ORD_TP_CD_DN.equals(rs.getString(SCE_ORD_TP_CD))) {
            pmsgArray = setPmsgDN(param, rs, rwsNum, soNum, cnt, pmsgArray);
        } else if (!rsShipTp.next()) {
            pmsgArray = setPmsgRS(param, rs, rwsNum, soNum, cnt, pmsgArray);
        } else if (SHIP_TP_LE.equals(rsShipTp.getString(SHIP_TP))) {
            pmsgArray = setPmsgLE(param, rs, rwsNum, soNum, cnt, pmsgArray);
        } else if (SHIP_TP_LI.equals(rsShipTp.getString(SHIP_TP))) {
            pmsgArray = setPmsgLI(param, rs, rwsNum, soNum, cnt, pmsgArray);
        }
        // ADD END 11/18/2015 CSA V2.0

        return pmsgArray;
    }

    // Add QC1365 Start
    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgSales
     */
    protected List<NLZC002001PMsg> setPmsgDS(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsg);

        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    // Add QC1365 End

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgSales
     */
    protected List<NLZC002001PMsg> setPmsgSales(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());

        // ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp,
        // NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsg);

        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());

        // ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp,
        // NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);

        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgExpence
     */
    protected List<NLZC002001PMsg> setPmsgExpence(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_STOCK_OUT);

        // ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd,
        // NLZC002001.TRX_DTL_EXP);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());

        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);

        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsg);

        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);

        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_STOCK_IN);

        // ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd,
        // NLZC002001.TRX_DTL_EXP);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());

        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgLoan
     */
    protected List<NLZC002001PMsg> setPmsgLoan(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        // Loan Stock Out
        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.LOAN_SHIPMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_LOAN_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));

        ZYPEZDItemValueSetter.setValue(pmsg.origCpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));

        pmsgArray.add(pmsg);

        // Loan Stock In
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.LOAN_SHIPMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_LOAN_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.LOAN);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));

        ZYPEZDItemValueSetter.setValue(pmsg.origCpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));

        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgTrailUse
     */
    protected List<NLZC002001PMsg> setPmsgTrailUse(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_TRIAL_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));

        ZYPEZDItemValueSetter.setValue(pmsg.origCpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));

        pmsgArray.add(pmsg);

        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_TRIAL_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.TRIAL_USE);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));

        ZYPEZDItemValueSetter.setValue(pmsg.origCpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));

        pmsgArray.add(pmsg);
        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgTrailSales
     */
    protected List<NLZC002001PMsg> setPmsgTrailSales(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_TRIAL_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));

        ZYPEZDItemValueSetter.setValue(pmsg.origCpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));

        pmsgArray.add(pmsg);

        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, DROP_SHIPMENT_TRIAL_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.TRIAL_SALE);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));

        // ZYPEZDItemValueSetter.setValue(pmsg.bolNum,
        // NLXSceConst.ALLSTR);

        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));

        ZYPEZDItemValueSetter.setValue(pmsg.origCpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.origCpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));

        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgSales
     */
    protected List<NLZC002001PMsg> setPmsgRtl(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        NLZC002001PMsg pmsgStkIn = new NLZC002001PMsg();
        NLZC002001PMsg pmsgStkOut = new NLZC002001PMsg();

        // Set common parameter
        pmsgStkOut.xxSysTp.setValue(NLZC002001.SYS_TP_OTBD);
        pmsgStkOut.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        pmsgStkOut.mdseCd.setValue(rs.getString(MDSE_CD));
        pmsgStkOut.stkStsCd.setValue(rs.getString(STK_STS_CD));
        pmsgStkOut.xxRqstQty.setValue(rs.getBigDecimal(PO_RCV_QTY));
        pmsgStkOut.invtyTrxDt.setValue(param.slsDt.getValue());
        pmsgStkOut.sysSrcCd.setValue(rs.getString(SYS_SRC_CD_SHPG));
        pmsgStkOut.soNum.setValue(soNum);
        pmsgStkOut.soSlpNum.setValue(ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        pmsgStkOut.uomCd.setValue(PKG_UOM.EACH);

        // Copy (Out -> In)
        EZDMsg.copy(pmsgStkOut, null, pmsgStkIn, null);

        // Stock Out
        pmsgStkOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        pmsgStkOut.trxCd.setValue(TRX.MOVEMENT);
        pmsgStkOut.trxRsnCd.setValue(TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT);
        pmsgStkOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_ROSS);
        pmsgStkOut.invtyLocCd.setValue(rs.getString(INVTY_LOC_CD));
        pmsgStkOut.locStsCd.setValue(LOC_STS.DC_STOCK);
        // 2013/05/13 OCE WDS R-WH003 SOL#094 SO, Configuratio(SO
        // Creation,Configuration) update start
        // pmsgStkOut.rossOrdNum.setValue(rs.getString(TRX_HDR_NUM));
        // pmsgStkOut.rossDtlLineNum.setValue(rs.getString(TRX_LINE_NUM));
        // pmsgStkOut.rossDtlLineSubNum.setValue(rs.getString(TRX_LINE_SUB_NUM));
        pmsgStkOut.cpoOrdNum.setValue(rs.getString(TRX_HDR_NUM));
        pmsgStkOut.cpoDtlLineNum.setValue(rs.getString(TRX_LINE_NUM));
        pmsgStkOut.cpoDtlLineSubNum.setValue(rs.getString(TRX_LINE_SUB_NUM));
        // 2013/05/13 OCE WDS R-WH003 SOL#094 SO, Configuratio(SO
        // Creation,Configuration) update end
        pmsgStkOut.shipFromLocCustCd.setValue(rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsgStkOut);

        // Stock In
        pmsgStkIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        pmsgStkIn.trxCd.setValue(TRX.MOVEMENT);
        pmsgStkIn.trxRsnCd.setValue(TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_IN);
        pmsgStkIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_ROSS);
        pmsgStkIn.invtyLocCd.setValue(rs.getString(SHIP_TO_CUST_CD));
        pmsgStkIn.locStsCd.setValue(LOC_STS.WAITING_FOR_INSTALLATION);
        // 2013/05/13 OCE WDS R-WH003 SOL#094 SO, Configuratio(SO
        // Creation,Configuration) update start
        // pmsgStkIn.rossOrdNum.setValue(rs.getString(TRX_HDR_NUM));
        // pmsgStkIn.rossDtlLineNum.setValue(rs.getString(TRX_LINE_NUM));
        // pmsgStkIn.rossDtlLineSubNum.setValue(rs.getString(TRX_LINE_SUB_NUM));
        pmsgStkIn.cpoOrdNum.setValue(rs.getString(TRX_HDR_NUM));
        pmsgStkIn.cpoDtlLineNum.setValue(rs.getString(TRX_LINE_NUM));
        pmsgStkIn.cpoDtlLineSubNum.setValue(rs.getString(TRX_LINE_SUB_NUM));
        // 2013/05/13 OCE WDS R-WH003 SOL#094 SO, Configuratio(SO
        // Creation,Configuration) update end
        pmsgStkIn.shipFromLocCustCd.setValue(rs.getString(WH_CD_PRH));
        pmsgArray.add(pmsgStkIn);

        return pmsgArray;
    }

    // ADD START 11/18/2015 CSA V2.0
    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgDN
     */
    protected List<NLZC002001PMsg> setPmsgDN(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        // ROSS Off-the-Book Stock-In
        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.OFF_THE_BOOK_STOCK_IN);
        // ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd,
        // NLZC002001.TRX_DTL_SELL_THEN_BUY);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(pmsg.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdNum, rs.getString(PO_RCV_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdDtlLineNum, rs.getString(PO_RCV_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.vndCd, rs.getString(PO_RCV_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        // ROSS Off the book Shipment Stock-Out
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_OUT);
        // ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd,
        // NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        // ROSS Off the book Shipment Stock-In
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_IN);
        // ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd,
        // NLZC002001.TRX_DTL_REG_SLS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgRS
     */
    protected List<NLZC002001PMsg> setPmsgRS(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        // Domestic Purchase Stock In
        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_SELL_THEN_BUY);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(pmsg.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdNum, rs.getString(PO_RCV_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdDtlLineNum, rs.getString(PO_RCV_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.vndCd, rs.getString(PO_RCV_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        // Regular Sales Domestic Shipment Stock Out
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_ROSS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        // Regular Sales Off the book Shipment Stock-In
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_ROSS);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgLE
     */
    protected List<NLZC002001PMsg> setPmsgLE(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        // Domestic Purchase Stock In
        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_SELL_THEN_BUY);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(pmsg.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdNum, rs.getString(PO_RCV_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdDtlLineNum, rs.getString(PO_RCV_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.vndCd, rs.getString(PO_RCV_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        // Loan Expense Domestic Shipment Stock Out
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.EXPENSE_SHIPMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.EXPENSE_LOAN_DROP_SHIPMENT_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.setPmsgLI
     */
    protected List<NLZC002001PMsg> setPmsgLI(NLZC010001PMsg param, ResultSet rs, String rwsNum, String soNum, int cnt, List<NLZC002001PMsg> pmsgArray) throws SQLException {

        // Domestic Purchase Stock In
        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_SELL_THEN_BUY);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poRcvLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(pmsg.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdNum, rs.getString(PO_RCV_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.poOrdDtlLineNum, rs.getString(PO_RCV_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.vndCd, rs.getString(PO_RCV_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        // Regular Sales Domestic Shipment Stock Out
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.DROP_SHIPMENT_TRIAL_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        // Regular Sales Domestic Shipment Stock In
        pmsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.DROP_SHIPMENT_TRIAL_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_TRIAL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, NLXSceConst.SCE_ORD_TYPE_LOAN);
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.TRIAL_USE);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipFromLocCustCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        pmsgArray.add(pmsg);

        return pmsgArray;
    }

    // ADD END 11/18/2015 CSA V2.0

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertRwsPutAway
     */
    protected void insertRwsPutAway(ResultSet rs, String timeStamp, String rwsNum, int cnt) throws SQLException {

        RWS_PUT_AWAYTMsg rwsPutAwayT = new RWS_PUT_AWAYTMsg();

        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.invtyStkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.whCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.sceOrdTpCd, rs.getString(SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.rwsStkQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.rwsStkDtTmTs, timeStamp);
        ZYPEZDItemValueSetter.setValue(rwsPutAwayT.mdseCd, rs.getString(MDSE_CD));

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsPutAwayT cnt:" + String.valueOf(cnt), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + rwsPutAwayT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsNum:" + rwsPutAwayT.rwsNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsDtlLineNum:" + rwsPutAwayT.rwsDtlLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "invtyStkStsCd:" + rwsPutAwayT.invtyStkStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whCd:" + rwsPutAwayT.whCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsRefNum:" + rwsPutAwayT.rwsRefNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sceOrdTpCd:" + rwsPutAwayT.sceOrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsStkQty:" + rwsPutAwayT.rwsStkQty.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsStkDtTmTs:" + rwsPutAwayT.rwsStkDtTmTs.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "mdseCd:" + rwsPutAwayT.mdseCd.getValue(), this);
        }

        S21ApiTBLAccessor.insert(rwsPutAwayT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {

            throw new S21AbendException(NLAM1133E, new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                    NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsNum, rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertRwsDtl
     */
    protected void insertRwsDtl(ResultSet rs, String rwsNum, int cnt) throws SQLException {

        RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rwsDtlT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.trxLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.invtyStkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsPutAwayQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.whInEtaDt, rs.getString(PO_RCV_ETA_DT));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsStsCd, RWS_STS.RECEIVED);
        // Add Start QC1181
        ZYPEZDItemValueSetter.setValue(rwsDtlT.poRcvTrxLineNum, rs.getString(PO_RCV_TRX_LINE_NUM));
        // Add End QC1181

        ZYPEZDItemValueSetter.setValue(rwsDtlT.rtlWhCd, rs.getString(RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.rtlSwhCd, rs.getString(RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlWhCd, rs.getString(SHIP_FROM_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromRtlSwhCd, rs.getString(SHIP_FROM_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.shipFromInvtyLocCd, rs.getString(SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqNum, rs.getString(PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqLineNum, rs.getString(PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(rwsDtlT.prchReqLineSubNum, rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
        if (ZYPConstant.FLG_ON_Y.equals(rs.getString(SER_NUM_TAKE_FLG)) && ZYPConstant.FLG_ON_Y.equals(rs.getString(RCV_SER_TAKE_FLG))) {
            ZYPEZDItemValueSetter.setValue(rwsDtlT.serNumTakeFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(rwsDtlT.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(rwsDtlT.serApvlReqFlg, rs.getString(SER_APVL_REQ_FLG));
        // QC#50027
        ZYPEZDItemValueSetter.setValue(rwsDtlT.techMeetTruckFlg, ZYPConstant.FLG_OFF_N);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsDtlT cnt:" + String.valueOf(cnt), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + rwsDtlT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsNum:" + rwsDtlT.rwsNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsDtlLineNum:" + rwsDtlT.rwsDtlLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxLineNum:" + rwsDtlT.trxLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "mdseCd:" + rwsDtlT.mdseCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "invtyStkStsCd:" + rwsDtlT.invtyStkStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsQty:" + rwsDtlT.rwsQty.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsPutAwayQty:" + rwsDtlT.rwsPutAwayQty.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whInEtaDt:" + rwsDtlT.whInEtaDt.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsStsCd:" + rwsDtlT.rwsStsCd.getValue(), this);
            // Add Start QC1181
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvTrxLineNum:" + rwsDtlT.poRcvTrxLineNum.getValue(), this);
            // Add End QC1181
            EZDDebugOutput.println(1, PROGRAM_ID + "rtlWhCd:" + rwsDtlT.rtlWhCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rtlSwhCd:" + rwsDtlT.rtlSwhCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "invtyLocCd:" + rwsDtlT.invtyLocCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipFromRtlWhCd:" + rwsDtlT.shipFromRtlWhCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipFromRtlSwhCd:" + rwsDtlT.shipFromRtlSwhCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipFromInvtyLocCd:" + rwsDtlT.shipFromInvtyLocCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "prchReqNum:" + rwsDtlT.prchReqNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "prchReqLineNum:" + rwsDtlT.prchReqLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "prchReqLineSubNum:" + rwsDtlT.prchReqLineSubNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "serNumTakeFlg:" + rwsDtlT.serNumTakeFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "serApvlReqFlg:" + rwsDtlT.serApvlReqFlg.getValue(), this);
        }

        S21ApiTBLAccessor.insert(rwsDtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {rwsDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                    NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd.getValue(), rwsNum, rwsDtlT.rwsDtlLineNum.getValue()) });
        }

    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.updatePoRcvDtl
     */
    protected void updatePoRcvDtl(ResultSet rs, int cnt) throws SQLException {

        PO_RCV_DTLTMsg poRcvDtlT = new PO_RCV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.rwsStsCd, RWS_STS.RECEIVED);
        ZYPEZDItemValueSetter.setValue(poRcvDtlT.actlRcvQty, rs.getBigDecimal(PO_RCV_QTY));

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvDtlT cnt:" + String.valueOf(cnt), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + poRcvDtlT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvNum:" + poRcvDtlT.poRcvNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvLineNum:" + poRcvDtlT.poRcvLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsStsCd:" + poRcvDtlT.rwsStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "actlRcvQty:" + poRcvDtlT.actlRcvQty.getValue(), this);
        }

        S21ApiTBLAccessor.updateSelectionField(poRcvDtlT, new String[] {RWS_STS_CD_PMSG_KEY, ACTL_RCV_QTY_PMSG_KEY });

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvDtlT.getReturnCode())) {
            throw new S21AbendException(NLAM1134E, new String[] {poRcvDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM),
                    NLXCMsgHelper.toListedString(rs.getString(GLBL_CMPY_CD), rs.getString(PO_RCV_NUM), rs.getString(PO_RCV_LINE_NUM)) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertShpgOrdConfDtl
     */
    protected void insertShpgOrdConfDtl(ResultSet rs, String soNum, int cnt) throws SQLException {

        SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlT = new SHPG_ORD_CONF_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.whCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.fromStkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.shipQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.soProcStsCd, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.mdseCd, rs.getString(MDSE_CD));
        // shpgOrdConfDtlT.frtInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        // QC#52401
        if (ZYPCommonFunc.hasValue(rs.getString("BOL_NUM"))) {
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.bolNum, rs.getString("BOL_NUM"));
        } else {
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.bolNum, NLXSceConst.ALLSTR);
        }
        if (ZYPCommonFunc.hasValue(rs.getString("PRO_NUM"))) {
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.proNum, rs.getString("PRO_NUM"));
        } else {
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.proNum, NLXSceConst.ALLSTR);
        }
        if (ZYPCommonFunc.hasValue(rs.getString("CARR_CD"))) {
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.vndCd, rs.getString("CARR_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.vndCd, NLXSceConst.ALLSTR);
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlT.frtRelnFlg, ZYPConstant.FLG_OFF_N);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "shpgOrdConfDtlT cnt:" + String.valueOf(cnt), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + shpgOrdConfDtlT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soNum:" + shpgOrdConfDtlT.soNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soSlpNum:" + shpgOrdConfDtlT.soSlpNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whCd:" + shpgOrdConfDtlT.whCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "stkStsCd:" + shpgOrdConfDtlT.fromStkStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipQty:" + shpgOrdConfDtlT.shipQty.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soProcStsCd:" + shpgOrdConfDtlT.soProcStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "mdseCd:" + shpgOrdConfDtlT.mdseCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "frtRelnFlg:" + shpgOrdConfDtlT.frtRelnFlg.getValue(), this);
        }

        S21ApiTBLAccessor.insert(shpgOrdConfDtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfDtlT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {
                    shpgOrdConfDtlT.getTableName(),
                    NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_SLP_NUM, WH_CD, BOL_NUM, PRO_NUM, VND_CD),
                    NLXCMsgHelper.toListedString(shpgOrdConfDtlT.glblCmpyCd.getValue(), soNum, shpgOrdConfDtlT.soSlpNum.getValue(), shpgOrdConfDtlT.whCd.getValue(), shpgOrdConfDtlT.bolNum.getValue(), shpgOrdConfDtlT.proNum.getValue(),
                            shpgOrdConfDtlT.vndCd.getValue()) });
        }
    }

    /**
     * getShpgPlnNum
     * @param ResultSet rs
     * @return shpgPlnNum String
     * @throws SQLException
     */
    protected String getShpgPlnNum(ResultSet rs) throws SQLException {

        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put("glblCmpyCd", rs.getString("GLBL_CMPY_CD"));
        paramMap.put("trxHdrNum", rs.getString("PD_CPO_ORD_NUM"));
        paramMap.put("trxLineNum", rs.getString("PD_CPO_DTL_LINE_NUM"));
        paramMap.put("trxLineSubNum", rs.getString("PD_CPO_DTL_LINE_SUB_NUM"));
        paramMap.put("poOrdNum", rs.getString("PD_PO_ORD_NUM"));
        paramMap.put("shpgStsCd", SHPG_STS.SHIPPED);

        String shpgPlnNum = (String) ssmBatchClient.queryObject("getShpgPlnNum", paramMap);

        return shpgPlnNum;

    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertShpgOrdDtl
     */
    protected void insertShpgOrdDtl(ResultSet rs, String soNum, int cnt) throws SQLException {

        SHPG_ORD_DTLTMsg shpgOrdDtlT = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.soSlpNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), SO_SLP_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.fromStkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shipQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgStsCd, SHPG_STS.SHIPPED);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.inEachQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxHdrNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));

        // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
        // Creation,Configuration) add start
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.configItemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
        // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);
        // Creation,Configuration) add end

        String cpoOrdTpCd = rs.getString(CPO_ORD_TP_CD);

        // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
        // Creation,Configuration) add start
        String trxSrcTpCd = rs.getString(TRX_SRC_TP_CD);
        if (isWholesale(trxSrcTpCd)) {
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPlnNum, getShpgPlnNum(rs));
            // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
            // Creation,Configuration) add end
        } else if (CPO_ORD_TP.PURCHASE.equals(cpoOrdTpCd) || CPO_ORD_TP.RENTAL.equals(cpoOrdTpCd) || CPO_ORD_TP.LEASE.equals(cpoOrdTpCd) || CPO_ORD_TP.SERVICE.equals(cpoOrdTpCd) || CPO_ORD_TP.RETAIL_TRIAL.equals(cpoOrdTpCd)
                || CPO_ORD_TP.RETAIL_ORDER.equals(cpoOrdTpCd)) {
            // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
            // Creation,Configuration) update this "if" to "else if"
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPlnNum, getShpgPlnNum(rs));
        }

        if ((CPO_ORD_TP.EXPENSE).equals(rs.getString(CPO_ORD_TP_CD))) {

            ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, ZYPConstant.FLG_ON_Y);

        } else {

            ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, ZYPConstant.FLG_OFF_N);

        }

        ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.sysSrcCd, rs.getString(SYS_SRC_CD_SHPG));

        // 2018/12/25 QC#29544 Add Start
        MDSE_STORE_PKGTMsg mspTMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mspTMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(mspTMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(mspTMsg.pkgUomCd, PKG_UOM.EACH);
        mspTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(mspTMsg);

        if (mspTMsg != null) {
            BigDecimal inPoundWt = mspTMsg.inPoundWt.getValue();
            BigDecimal shpgQty = rs.getBigDecimal(PO_RCV_QTY);
            BigDecimal totShpgWt = multiplyNullToNull(inPoundWt, shpgQty, TOT_SHPG_WT_DECL_LG);
            totShpgWt = overflowToBorder(totShpgWt, TOT_SHPG_WT_MAX_LG, TOT_SHPG_WT_DECL_LG);
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgWt, totShpgWt);
        }
        // 2018/12/25 QC#29544 Add End

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "shpgOrdDtlT cnt:" + String.valueOf(cnt), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + shpgOrdDtlT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soNum:" + shpgOrdDtlT.soNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soSlpNum:" + shpgOrdDtlT.soSlpNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "mdseCd:" + shpgOrdDtlT.mdseCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "fromStkStsCd:" + shpgOrdDtlT.fromStkStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shpgQty:" + shpgOrdDtlT.shpgQty.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipQty:" + shpgOrdDtlT.shipQty.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shpgStsCd:" + shpgOrdDtlT.shpgStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "serNumTakeFlg:" + shpgOrdDtlT.serNumTakeFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "inEachQty:" + shpgOrdDtlT.inEachQty.getValue(), this);
            // EZDDebugOutput.println(1, PROGRAM_ID + "lnVoidFlg:" +
            // shpgOrdDtlT.lnVoidFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxHdrNum:" + shpgOrdDtlT.trxHdrNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxLineNum:" + shpgOrdDtlT.trxLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxLineSubNum:" + shpgOrdDtlT.trxLineSubNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "expFlg:" + shpgOrdDtlT.expFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sysSrcCd:" + shpgOrdDtlT.sysSrcCd.getValue(), this);

            // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
            // Creation,Configuration) add start
            EZDDebugOutput.println(1, PROGRAM_ID + "configItemFlg:" + shpgOrdDtlT.configItemFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "batNumTakeFlg:" + shpgOrdDtlT.batNumTakeFlg.getValue(), this);
            // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
            // Creation,Configuration) add end
            // 2018/12/25 QC#29544 Add Start
            EZDDebugOutput.println(1, PROGRAM_ID + "totShpgWt:" + shpgOrdDtlT.totShpgWt.getValue(), this);
            // 2018/12/25 QC#29544 Add END
        }
        S21ApiTBLAccessor.insert(shpgOrdDtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDtlT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {shpgOrdDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_SLP_NUM),
                    NLXCMsgHelper.toListedString(shpgOrdDtlT.glblCmpyCd.getValue(), soNum, shpgOrdDtlT.soSlpNum.getValue()) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertInbdVis
     */
    protected void insertInbdVis(ResultSet rs, String jobDay, String rwsNum, int cnt) throws SQLException {

        INBD_VISTMsg inbdVisT = new INBD_VISTMsg();
        ZYPEZDItemValueSetter.setValue(inbdVisT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ));
        ZYPEZDItemValueSetter.setValue(inbdVisT.inbdLtstRecFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.PO_COMPLETE_RECEIVE);
        ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);
        ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisActlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inbdVisT.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(inbdVisT.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(inbdVisT.poRcvLineNum, rs.getString(PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(inbdVisT.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(inbdVisT.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(cnt), RWS_DTL_LINE_NUM_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(inbdVisT.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inbdVisT.visLocTpCd, VIS_LOC_TP.DC);
        ZYPEZDItemValueSetter.setValue(inbdVisT.visLocCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(inbdVisT.visLocNm, rs.getString(LOC_NM));
        ZYPEZDItemValueSetter.setValue(inbdVisT.visQty, rs.getBigDecimal(PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(inbdVisT.etaEtdDt, jobDay);
        ZYPEZDItemValueSetter.setValue(inbdVisT.calcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inbdVisT.cratTs, sysDateTs);
        ZYPEZDItemValueSetter.setValue(inbdVisT.regdTs, sysDateTs);
        ZYPEZDItemValueSetter.setValue(inbdVisT.invtyStkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(inbdVisT.imptCustPoNum, rs.getString(CUST_PO_NUM));

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "inbdVisT cnt:" + String.valueOf(cnt), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + inbdVisT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "inbdVisPk:" + inbdVisT.inbdVisPk.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "inbdLtstRecFlg:" + inbdVisT.inbdLtstRecFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "inbdVisEventCd:" + inbdVisT.inbdVisEventCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "inbdVisActlFlg:" + inbdVisT.inbdVisActlFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsNum:" + inbdVisT.rwsNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvNum:" + inbdVisT.poRcvNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvLineNum:" + inbdVisT.poRcvLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsRefNum:" + inbdVisT.rwsRefNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsDtlLineNum:" + inbdVisT.rwsDtlLineNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "mdseCd:" + inbdVisT.mdseCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "visLocTpCd:" + inbdVisT.visLocTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "visLocCd:" + inbdVisT.visLocCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "visLocNm:" + inbdVisT.visLocNm.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "visQty:" + inbdVisT.visQty.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "etaEtdDt:" + inbdVisT.etaEtdDt.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "calcFlg:" + inbdVisT.calcFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "cratDt:" + inbdVisT.cratTs.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "regdDt:" + inbdVisT.regdTs.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "invtyStkStsCd:" + inbdVisT.invtyStkStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "imptCustPoNum:" + inbdVisT.imptCustPoNum.getValue(), this);
        }
        S21ApiTBLAccessor.insert(inbdVisT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inbdVisT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E,
                    new String[] {inbdVisT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, INBD_VIS_PK), NLXCMsgHelper.toListedString(inbdVisT.glblCmpyCd.getValue(), inbdVisT.inbdVisPk.getValue()) });
        }

    }

    // TODO WH_SCHD logical remove is suspend by tsuda-san
    // /**
    // *
    // * @param rs ResultSet
    // * @throws SQLException
    // */
    // private void removeWhSchd(ResultSet rs) throws SQLException {
    //
    // WH_SCHDTMsg whSchdTMsg = new WH_SCHDTMsg();
    // whSchdTMsg.setConditionValue(GLBL_CMPY_CD_KEY,
    // rs.getString(GLBL_CMPY_CD));
    // whSchdTMsg.setConditionValue(PO_RCV_NUM_KEY,
    // rs.getString(PO_RCV_NUM));
    // whSchdTMsg.setConditionValue(PO_RCV_LINE_NUM_KEY,
    // rs.getString(PO_RCV_LINE_NUM));
    // whSchdTMsg.setConditionValue(INVTY_LOC_CD_KEY,
    // rs.getString(WH_CD_PRH));
    // whSchdTMsg.setSQLID("018");
    // WH_SCHDTMsgArray whSchdTMsgAry = (WH_SCHDTMsgArray)
    // S21ApiTBLAccessor.findByConditionForUpdate(whSchdTMsg);
    //
    // if (whSchdTMsgAry.getValidCount() == 0) {
    // return;
    // }
    //
    // WH_SCHDTMsg[] delWhSchdTMsgAry = new
    // WH_SCHDTMsg[whSchdTMsgAry.getValidCount()];
    // for (int i = 0; i < delWhSchdTMsgAry.length; i++) {
    // delWhSchdTMsgAry[i] = whSchdTMsgAry.no(i);
    // }
    // S21FastTBLAccessor.removeLogical(delWhSchdTMsgAry);
    // }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertRwsCplt
     */
    protected void insertRwsCplt(ResultSet rs, String timeStamp, String rwsNum) throws SQLException {

        RWS_CPLTTMsg rwsCpltT = new RWS_CPLTTMsg();

        ZYPEZDItemValueSetter.setValue(rwsCpltT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(rwsCpltT.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsCpltT.whCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(rwsCpltT.rwsRefNum, rs.getString(RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(rwsCpltT.sceOrdTpCd, rs.getString(SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(rwsCpltT.rwsCloDtTmTs, timeStamp);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + rwsCpltT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsNum:" + rwsCpltT.rwsNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whCd:" + rwsCpltT.whCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsRefNum:" + rwsCpltT.rwsRefNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sceOrdTpCd:" + rwsCpltT.sceOrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsCloDtTmTs:" + rwsCpltT.rwsCloDtTmTs.getValue(), this);
        }
        S21ApiTBLAccessor.insert(rwsCpltT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsCpltT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {rwsCpltT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsCpltT.glblCmpyCd.getValue(), rwsNum) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertRwsHdr
     */
    protected void insertRwsHdr(ResultSet rs, String timeStamp, String rwsNum) throws SQLException {

        RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(rwsHdrT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.inbdSrcTpCd, INBD_SRC_TP.DS);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.sceOrdTpCd, rs.getString(SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsRefNum, rs.getString(RWS_REF_NUM));
        // ZYPEZDItemValueSetter.setValue(rwsHdrT.trxOrdNum,
        // rs.getString(PO_RCV_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.trxOrdNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.whCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocTpCd, rs.getString(PO_RCV_FROM_LOC_TP_CD));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.fromLocCd, rs.getString(PO_RCV_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.trxCd, rs.getString(TRX_CD));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.trxRsnCd, rs.getString(TRX_RSN_CD));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.whInEtaDt, rs.getString(PO_RCV_ETA_DT));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.drctShipTpCd, DRCT_SHIP_TP.SELL_THEN_BUY);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.wmsDropStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsPrtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsStsCd, RWS_STS.RECEIVED);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.schdEtaChkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.finalEtaChkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.sysSrcCd, rs.getString(SYS_SRC_CD_PRH));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.dnldDtTmTs, systemDayTs);
        // Add Start QC1181
        ZYPEZDItemValueSetter.setValue(rwsHdrT.poRcvTrxHdrNum, rs.getString(PO_RCV_TRX_HDR_NUM));
        // Add End QC1181

        ZYPEZDItemValueSetter.setValue(rwsHdrT.trxSrcTpCd, TRX_SRC_TP.PURCHASE_ORDER);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.pickUpRqstFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rwsHdrT.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.shipFromSoNum, rs.getString(SHIP_FROM_SO_NUM));
        ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsCloDtTmTs, timeStamp);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + rwsHdrT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsNum:" + rwsHdrT.rwsNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "inbdSrcTpCd:" + rwsHdrT.inbdSrcTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sceOrdTpCd:" + rwsHdrT.sceOrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsRefNum:" + rwsHdrT.rwsRefNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxHdrNum:" + rwsHdrT.trxOrdNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whCd:" + rwsHdrT.whCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "fromLocTpCd:" + rwsHdrT.fromLocTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "fromLocCd:" + rwsHdrT.fromLocCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxCd:" + rwsHdrT.trxCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxRsnCd:" + rwsHdrT.trxRsnCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whInEtaDt:" + rwsHdrT.whInEtaDt.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "drctShipTpCd:" + rwsHdrT.drctShipTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "wmsDropStsCd:" + rwsHdrT.wmsDropStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsPrtFlg:" + rwsHdrT.rwsPrtFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsStsCd:" + rwsHdrT.rwsStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "schdEtaChkFlg:" + rwsHdrT.schdEtaChkFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "finalEtaChkFlg:" + rwsHdrT.finalEtaChkFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sysSrcCd:" + rwsHdrT.sysSrcCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "dnldDtTmTs:" + rwsHdrT.dnldDtTmTs.getValue(), this);
            // Add Start QC1181
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvTrxHdrNum:" + rwsHdrT.poRcvTrxHdrNum.getValue(), this);
            // Add End QC1181
            EZDDebugOutput.println(1, PROGRAM_ID + "trxSrcTpCd:" + rwsHdrT.trxSrcTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "pickUpRqstFlg:" + rwsHdrT.pickUpRqstFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "svcConfigMstrPk:" + rwsHdrT.svcConfigMstrPk.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipFromSoNum:" + rwsHdrT.shipFromSoNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsCloDtTmTs:" + rwsHdrT.rwsCloDtTmTs.getValue(), this);
        }
        S21ApiTBLAccessor.insert(rwsHdrT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {rwsHdrT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd.getValue(), rwsNum) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertShpgOrdConf
     */
    protected void insertShpgOrdConf(ResultSet rs, String timeStamp, String soNum) throws SQLException {

        SHPG_ORD_CONFTMsg shpgOrdConfT = new SHPG_ORD_CONFTMsg();

        ZYPEZDItemValueSetter.setValue(shpgOrdConfT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfT.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfT.whCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfT.sceOrdTpCd, sceOrdTpCdOut);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfT.soProcStsCd, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfT.shipDtTmTs, timeStamp);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + shpgOrdConfT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soNum:" + shpgOrdConfT.soNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whCd:" + shpgOrdConfT.whCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sceOrdTpCd:" + shpgOrdConfT.sceOrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soProcStsCd:" + shpgOrdConfT.soProcStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipDtTmTs:" + shpgOrdConfT.shipDtTmTs.getValue(), this);
        }
        S21ApiTBLAccessor.insert(shpgOrdConfT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {shpgOrdConfT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM), NLXCMsgHelper.toListedString(shpgOrdConfT.glblCmpyCd.getValue(), soNum) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertShpgOrd
     */
    protected void insertShpgOrd(ResultSet rs, String soNum) throws SQLException {

        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();

        ZYPEZDItemValueSetter.setValue(shpgOrdT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.soNum, soNum);

        ZYPEZDItemValueSetter.setValue(shpgOrdT.sellToCustCd, rs.getString(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.billToCustCd, rs.getString(BILL_TO_CUST_CD));

        ZYPEZDItemValueSetter.setValue(shpgOrdT.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.whCd, rs.getString(WH_CD_PRH));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.trxHdrNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.trxCd, sceTrxcdOut);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.trxRsnCd, sceTrxRsncdOut);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.shpgStsCd, SHPG_STS.SHIPPED);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.sceOrdTpCd, sceOrdTpCdOut);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.dnldTmTs, systemDayTs);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.pddDt, rs.getString(PO_RCV_ETA_DT));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.rsdDt, rs.getString(PO_RCV_ETA_DT));
        // ZYPEZDItemValueSetter.setValue(shpgOrdT.wmsDropFlg,
        // PROC_STS.COMPLEATED);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.wmsDropFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(shpgOrdT.drctShipTpCd, DRCT_SHIP_TP.SELL_THEN_BUY);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.soPrtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.shpgFrceFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(shpgOrdT.trxSrcTpCd, rs.getString(TRX_SRC_TP_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.asnReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.printSccLbFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.printUccLbFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.mixPltAllwFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.printPltUccLbFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.printNonAsnUccLbFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.dropShipFlg, ZYPConstant.FLG_OFF_N);

        // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
        // Creation,Configuration) add start
        ZYPEZDItemValueSetter.setValue(shpgOrdT.hazMatFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.soConfigFlg, ZYPConstant.FLG_OFF_N);

        shpgOrdT.wmsDropRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.wmsRtePathCd.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.preIstlShopRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.rtrnItemInclFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.delyReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.tempSchdFlg.setValue(ZYPConstant.FLG_OFF_N);
        // 2018/1/17 QC#18460 T.Hakodate ADD START
        String schdDelyTm = getRequestInstallTime(rs.getString(GLBL_CMPY_CD), rs.getString(TRX_HDR_NUM));
        if (ZYPCommonFunc.hasValue(schdDelyTm)) {
            shpgOrdT.schdDelyTm.setValue(schdDelyTm);
        }
        ZYPEZDItemValueSetter.setValue(shpgOrdT.techMeetTruckFlg, getTechMeetTruckFlg(rs.getString(GLBL_CMPY_CD), rs.getString(TRX_HDR_NUM)));
        // 2018/1/17 QC#18460 T.Hakodate ADD END

        // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
        // Creation,Configuration) add end
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + shpgOrdT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soNum:" + shpgOrdT.soNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shipToCustCd:" + shpgOrdT.shipToCustCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "whCd:" + shpgOrdT.whCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxHdrNum:" + shpgOrdT.trxHdrNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxCd:" + shpgOrdT.trxCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxRsnCd:" + shpgOrdT.trxRsnCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shpgStsCd:" + shpgOrdT.shpgStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sceOrdTpCd:" + shpgOrdT.sceOrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "dnldTmTs:" + shpgOrdT.dnldTmTs.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "pddDt:" + shpgOrdT.pddDt.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rsdDt:" + shpgOrdT.rsdDt.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "wmsDropFlg:" + shpgOrdT.wmsDropFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "drctShipTpCd:" + shpgOrdT.drctShipTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soPrtFlg:" + shpgOrdT.soPrtFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "shpgFrceFlg:" + shpgOrdT.shpgFrceFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "trxSrcTpCd:" + shpgOrdT.trxSrcTpCd.getValue(), this);

            // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
            // Creation,Configuration) add start
            EZDDebugOutput.println(1, PROGRAM_ID + "hazMatFlg:" + shpgOrdT.hazMatFlg.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soConfigFlg:" + shpgOrdT.soConfigFlg.getValue(), this);
            // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
            // Creation,Configuration) add end
        }
        S21ApiTBLAccessor.insert(shpgOrdT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {shpgOrdT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM), NLXCMsgHelper.toListedString(shpgOrdT.glblCmpyCd.getValue(), soNum) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.insertShpgOrdCustDtl
     */
    protected void insertShpgOrdCustDtl(ResultSet rs, String soNum) throws SQLException {

        SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlT = new SHPG_ORD_CUST_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustDataTpCd, SO_CUST_DATA_TP.SHIP_TO);

        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_01, rs.getString(ADD_SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_02, rs.getString(ADD_SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_01, rs.getString(ADD_SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_02, rs.getString(ADD_SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_03, rs.getString(ADD_SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_04, rs.getString(ADD_SHIP_TO_FRTH_LINE_ADDR));

        if (NLXC014001.nullToEmpty(rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT)).length() <= CTAC_PTNR_LENGTH) {
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnNm, rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT));
        } else {
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnNm, rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT).substring(0, CTAC_PTNR_LENGTH));
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctryCd, rs.getString(ADD_SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.stCd, rs.getString(ADD_SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctyAddr, rs.getString(ADD_SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.postCd, rs.getString(ADD_SHIP_TO_POST_CD));

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + shpgOrdCustDtlT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soNum:" + shpgOrdCustDtlT.soNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soCustDataTpCd:" + shpgOrdCustDtlT.soCustDataTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soCustLineLocNm_01:" + shpgOrdCustDtlT.soCustLineLocNm_01.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soCustLineLocNm_02:" + shpgOrdCustDtlT.soCustLineLocNm_02.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soCustLineAddr_01:" + shpgOrdCustDtlT.soCustLineAddr_01.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soCustLineAddr_02:" + shpgOrdCustDtlT.soCustLineAddr_02.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soCustLineAddr_03:" + shpgOrdCustDtlT.soCustLineAddr_03.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soCustLineAddr_04:" + shpgOrdCustDtlT.soCustLineAddr_04.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "ctacPtnrPsnNm:" + shpgOrdCustDtlT.ctacPtnrPsnNm.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "ctryCd:" + shpgOrdCustDtlT.ctryCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "stCd:" + shpgOrdCustDtlT.stCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "ctyAddr:" + shpgOrdCustDtlT.ctyAddr.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "postCd:" + shpgOrdCustDtlT.postCd.getValue(), this);
        }
        S21ApiTBLAccessor.insert(shpgOrdCustDtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdCustDtlT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {shpgOrdCustDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_CUST_DATA_TP_CD),
                    NLXCMsgHelper.toListedString(shpgOrdCustDtlT.glblCmpyCd.getValue(), soNum, shpgOrdCustDtlT.soCustDataTpCd.getValue()) });
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustDataTpCd, SO_CUST_DATA_TP.BILL_TO);
        S21ApiTBLAccessor.insert(shpgOrdCustDtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdCustDtlT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {shpgOrdCustDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_CUST_DATA_TP_CD),
                    NLXCMsgHelper.toListedString(shpgOrdCustDtlT.glblCmpyCd.getValue(), soNum, shpgOrdCustDtlT.soCustDataTpCd.getValue()) });
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustDataTpCd, SO_CUST_DATA_TP.SELL_TO);
        S21ApiTBLAccessor.insert(shpgOrdCustDtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdCustDtlT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {shpgOrdCustDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_CUST_DATA_TP_CD),
                    NLXCMsgHelper.toListedString(shpgOrdCustDtlT.glblCmpyCd.getValue(), soNum, shpgOrdCustDtlT.soCustDataTpCd.getValue()) });
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC010001.updatePoRcvHdr
     */
    protected void updatePoRcvHdr(ResultSet rs) throws SQLException {

        PO_RCV_HDRTMsg poRcvHdrT = new PO_RCV_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(poRcvHdrT.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvNum, rs.getString(PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.rwsStsCd, RWS_STS.RECEIVED);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd:" + poRcvHdrT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "poRcvNum:" + poRcvHdrT.poRcvNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rwsStsCd:" + poRcvHdrT.rwsStsCd.getValue(), this);
        }
        S21ApiTBLAccessor.updateSelectionField(poRcvHdrT, new String[] {RWS_STS_CD_PMSG_KEY });
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvHdrT.getReturnCode())) {
            throw new S21AbendException(NLAM1134E, new String[] {poRcvHdrT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM), NLXCMsgHelper.toListedString(rs.getString(GLBL_CMPY_CD), rs.getString(PO_RCV_NUM)) });
        }
    }

    /**
     * Call SO API
     * @param msgMap NLZC010001 message map
     * @param soNum Shipping Order Number
     * @param onBatchType online/batch type
     * @return boolean, TRUE: Normal End, FALSE: has error/warning
     */
    private boolean callSOApi(S21ApiMessageMap msgMap, String soNum, String poRcvEtaDt, ONBATCH_TYPE onBatchType) {
        boolean result = Boolean.TRUE;
        List<NLZC205001PMsg> soApiParamList = new ArrayList<NLZC205001PMsg>();
        NLZC010001PMsg param = (NLZC010001PMsg) msgMap.getPmsg();

        NLZC205001 soApi = new NLZC205001();
        NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();

        ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_DROP_SHIP);
        ZYPEZDItemValueSetter.setValue(soApiPMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(soApiPMsg.sceOrdTpCd, sceOrdTpCdOut);
        ZYPEZDItemValueSetter.setValue(soApiPMsg.etaDt, poRcvEtaDt);
        soApiPMsg.rsdDt.clear();

        soApiParamList.add(soApiPMsg);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "========SO API Parameters from NLZC010001 Start =======>", this);
            EZDDebugOutput.println(1, PROGRAM_ID + "glblCmpyCd   :" + soApiPMsg.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "xxModeCd     :" + soApiPMsg.xxModeCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "soNum        :" + soApiPMsg.soNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "sceOrdTpCd   :" + soApiPMsg.sceOrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "rsdDt        :" + soApiPMsg.rsdDt.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "======== SO API Parameters from NLZC010001 End =======<", this);
        }
        soApi.execute(soApiParamList, onBatchType);

        List<String> msgIds = S21ApiUtil.getXxMsgIdList(soApiPMsg);
        for (String msgId : msgIds) {
            msgMap.addXxMsgId(msgId);
            S21InfoLogOutput.println("SO API Error:" + S21MessageFunc.clspGetMessage(msgId));
            if (msgId.endsWith("E")) {
                result = Boolean.FALSE;
            }
        }
        msgMap.flush();
        return result;
    }

    private boolean isWholesale(String trxSrcTpCd) {
        return TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd);
    }
    // 11/14/2012 Oce WDS SOL#094 SO, Configuratio(SO
    // Creation,Configuration) add end

    // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START
    private String getRequestInstallTime(String glblCmpyCd, String trxHdrNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("trxHdrNum", trxHdrNum);

        return (String) ssmBatchClient.queryObject("getRequestInstallTime", queryParam);
    }

    // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD END

    // 2018/01/10 QC#18460(SOL#087) T,Hakodate ADD START
    private String getTechMeetTruckFlg(String glblCmpyCd, String trxHdrNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("trxHdrNum", trxHdrNum);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        String result = (String) ssmBatchClient.queryObject("getTechMeetTruckFlg", queryParam);

        if (ZYPCommonFunc.hasValue(result)) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }
    // 2018/0110 QC#18460(SOL#087) T,Hakodate END START

    // 2018/12/25 QC#29544 Add Start
    /**
     * <pre>
     * Multiply
     * If val=null then return null
     * Half up round
     * </pre>
     * @param arg0 Argument0
     * @param arg1 Argument1
     * @param scale DecimalLength
     * @return BigDecimal
     */
    private BigDecimal multiplyNullToNull(final BigDecimal arg0, final BigDecimal arg1, int scale) {

        if (arg0 == null || arg1 == null) {
            return null;
        } else {
            BigDecimal multiply = arg0.multiply(arg1);
            return multiply.setScale(scale, RoundingMode.HALF_UP);
        }
    }

    /**
     * Overflow Value To Border Value
     * @param val BigDecimalValue
     * @param maxLg MaxLength
     * @param declLg DecimalLength
     * @return BigDecimalValue (If val=null then return null)
     */
    private BigDecimal overflowToBorder(final BigDecimal val, final int maxLg, final int declLg) {

        BigDecimal rtrnVal = val;
        if (val != null) {
            BigDecimal maxVal = getMaxVal(maxLg, declLg);
            BigDecimal minVal = maxVal.negate();
            rtrnVal = rtrnVal.min(maxVal);
            rtrnVal = rtrnVal.max(minVal);
        }
        return rtrnVal;
    }

    /**
     * Get Max Value
     * @param maxLg MaxLength
     * @param declLg DecimalLength
     * @return MaxValue
     */
    private BigDecimal getMaxVal(final int maxLg, final int declLg) {

        BigDecimal overflowVal = BigDecimal.valueOf(1, declLg - maxLg);
        BigDecimal unitVal = BigDecimal.valueOf(1, declLg);
        return overflowVal.subtract(unitVal);
    }
    // 2018/12/25 QC#29544 Add End

}
