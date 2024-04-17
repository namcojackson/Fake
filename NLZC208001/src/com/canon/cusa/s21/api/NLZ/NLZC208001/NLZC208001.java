/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC208001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INBD_VISTMsg;
import business.db.INVTY_TRXTMsg;
import business.db.MDSETMsg;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_DTLTMsgArray;
import business.db.PO_RCV_HDRTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_PUT_AWAYTMsg;
import business.db.RWS_PUT_AWAY_SERTMsg;
import business.db.RWS_PUT_AWAY_SER_WRKTMsg;
import business.db.RWS_PUT_AWAY_SER_WRKTMsgArray;
import business.db.RWS_PUT_AWAY_WRKTMsg;
import business.db.RWS_SCHD_DTL_TRKTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.STK_STSTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC208001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NWZC153001PMsg;
import business.parts.NWZC153002PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VIS_LOC_TP;
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
 * Update Put Away Confirmation API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/21/2015   CITS            Hisashi         Create          N/A
 * 12/30/2015   CSAI            K.Lee           Update          QC2318
 * 04/21/2016   Fujistu         Y.Taoka         Update          QC7355
 * 04/27/2016   CSAI            Y.Imazu         Update          QC#7715
 * 05/13/2016   Fujitsu         Y.Taoka         Update          QC#7606
 * 05/19/2016   CSAI            Y.Imazu         Update          QC#8571
 * 06/01/2016   CSAI            Y.Imazu         Update          QC#8001
 * 06/30/2016   CSAI            K.Lee           Update          Configuration Change
 * 07/18/2016   CSAI            Y.Imazu         Update          QC#6663
 * 12/01/2016   CITS            T.Hakodate      Update          QC#16303
 * 05/18/2017   CITS            S.Endo          Update          RS#7265
 * 07/04/2017   CITS            T.Kikuhara      Update          QC#19381
 * 10/13/2017   CITS            T.Hakodate      UPDATE          QC#21857
 * 10/18/2017   CITS            T.Hakodate      UPDATE          QC#21670
 * 06/28/2018   CITS            Y.Iwasaki       Update          QC#25847
 * 11/20/2019   CITS            K.Ogino         Update          QC#54627
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 05/28/2020   CITS            K.Ogino         Update          QC#54864
 * 08/27/2020   CITS            K.Ogino         Update          QC#57462
 * 14/03/2024   CITS            J.Cho           Update          QC#63527
 *</pre>
 */
public class NLZC208001 extends S21ApiCommonBase {

    // ----Message----
    /** */
    public static final String NLZM1004E = "NLZM1004E";

    /** */
    public static final String NLAM1001E = "NLAM1001E";

    /** */
    public static final String NLZM1015E = "NLZM1015E";

    /** */
    public static final String NLAM1132E = "NLAM1132E";

    /** */
    public static final String NLAM1131E = "NLAM1131E";

    /** */
    public static final String NLZM1007E = "NLZM1007E";

    /** */
    public static final String NLZM1013E = "NLZM1013E";

    /** */
    public static final String NLZM1014E = "NLZM1014E";

    /** */
    public static final String NLZM1016E = "NLZM1016E";

    /** */
    public static final String NLZM1008E = "NLZM1008E";

    /** */
    public static final String NLZM1009E = "NLZM1009E";

    /** */
    public static final String NLZM1010E = "NLZM1010E";

    /** */
    public static final String NLZM1017E = "NLZM1017E";

    /** */
    public static final String NLZM1018E = "NLZM1018E";

    /** */
    public static final String NLZM2022E = "NLZM2022E";

    /** */
    public static final String NLZM1035E = "NLZM1035E";

    /** */
    public static final String NLAM1133E = "NLAM1133E";

    /** */
    public static final String NLAM1134E = "NLAM1134E";

    /** */
    public static final String NLZM1036E = "NLZM1036E";

    /** */
    public static final String NLZM1037E = "NLZM1037E";

    /** */
    public static final String NLZM1038E = "NLZM1038E";

    /** */
    public static final String NLAM1161E = "NLAM1161E";

    /** */
    public static final String NLAM1162E = "NLAM1162E";

    /** */
    public static final String NLAM1207E = "NLAM1207E";

    /** */
    public static final String NLZM1049E = "NLZM1049E";

    /** */
    public static final String NLZM1050E = "NLZM1050E";

    /** */
    public static final String NLZM2176E = "NLZM2176E";

    /** */
    private static final String ONBATCH_TYPE = "ONBATCH_TYPE";

    /** */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** */
    private static final String RWS_NUM = "RWS_NUM";

    /** */
    private static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** */
    private static final String IMPT_INV_PK = "IMPT_INV_PK";

    /** */
    private static final String IMPT_PACK_SLP_STS_PK = "IMPT_PACK_SLP_STS_PK";

    /** */
    private static final String IMPT_INV_NUM = "IMPT_INV_NUM";

    /** */
    private static final String IMPT_INV_CLS_CD = "IMPT_INV_CLS_CD";

    /** */
    private static final String IMPT_INV_VER_NUM = "IMPT_INV_VER_NUM";

    /** */
    private static final String IMPT_INV_SLP_CLS_CD = "IMPT_INV_SLP_CLS_CD";

    /** */
    private static final String IMPT_INV_DO_NUM = "IMPT_INV_DO_NUM";

    /** */
    private static final String IMPT_DO_CLS_CD = "IMPT_DO_CLS_CD";

    /** */
    private static final String IMPT_PACK_SLP_NUM = "IMPT_PACK_SLP_NUM";

    /** */
    private static final String IMPT_PACK_SLP_LINE_NUM = "IMPT_PACK_SLP_LINE_NUM";

    /** */
    private static final String IMPT_PACK_SLP_DTL_LINE_NUM = "IMPT_PACK_SLP_DTL_LINE_NUM";

    /** */
    private static final String INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /** */
    private static final String RWS_QTY = "RWS_QTY";

    /** */
    private static final String RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";

    /** */
    private static final String MDSE_CD = "MDSE_CD";

    /** */
    private static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** */
    private static final String DTL_RWS_STS_CD = "DTL_RWS_STS_CD";

    /** */
    private static final String HDR_RWS_STS_CD = "HDR_RWS_STS_CD";

    /** */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /** */
    private static final String TRX_ORD_NUM = "TRX_ORD_NUM";

    /** */
    private static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** */
    private static final String IMPT_CNTNR_LOT_NUM = "IMPT_CNTNR_LOT_NUM";

    /** */
    private static final String IMPT_CNTNR_NUM = "IMPT_CNTNR_NUM";

    /** */
    private static final String FROM_LOC_CD = "FROM_LOC_CD";

    /** */
    private static final String LOC_NM = "LOC_NM";

    /** */
    private static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** */
    private static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** */
    private static final String INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** */
    private static final String INVTY_ORD_LINE_NUM = "INVTY_ORD_LINE_NUM";

    /** */
    private static final String SO_NUM = "SO_NUM";

    /** */
    private static final String INBD_VIS_PK = "INBD_VIS_PK";

    /** */
    private static final String PO_RCV_NUM = "PO_RCV_NUM";

    /** */
    private static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /** */
    private static final String CUST_PO_NUM = "CUST_PO_NUM";

    /** */
    private static final String RWS_PUT_AWAY_WRK = "RWS_PUT_AWAY_WRK";

    /** */
    private static final String RWS_DTL = "RWS_DTL";

    /** */
    private static final String PO_RCV_DTL = "PO_RCV_DTL";

    /** */
    private static final String PO_RCV_HDR = "PO_RCV_HDR";

    /** */
    private static final String CPO_DTL = "CPO_DTL";

    /** */
    private static final String CPO = "CPO";

    /** */
    private static final String GLBL_CMPY = "GLBL_CMPY";

    /** */
    private static final String STK_STS = "STK_STS";

    /** */
    private static final String DOM_INV_NUM = "DOM_INV_NUM";

    /** */
    private static final String RWS_LINE_NUM = "RWS_LINE_NUM";

    /** */
    private static final String SER_NUM = "SER_NUM";

    /** */
    private static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** */
    private static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** */
    private static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** */
    private static final String DS_CPO_RTRN_LINE_NUM = "DS_CPO_RTRN_LINE_NUM";

    /** */
    private static final String DS_CPO_RTRN_LINE_SUB_NUM = "DS_CPO_RTRN_LINE_SUB_NUM";

    /** */
    private static final String TRX_CD = "TRX_CD";

    /** */
    private static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** */
    private static final String ORIG_CPO_ORD_NUM = "ORIG_CPO_ORD_NUM";

    /** */
    private static final String ORIG_CPO_DTL_LINE_NUM = "ORIG_CPO_DTL_LINE_NUM";

    /** */
    private static final String ORIG_CPO_DTL_LINE_SUB_NUM = "ORIG_CPO_DTL_LINE_SUB_NUM";

    /** */
    private static final String REF_CPO_DTL_LINE_NUM = "REF_CPO_DTL_LINE_NUM";

    /** */
    private static final String REF_CPO_DTL_LINE_SUB_NUM = "REF_CPO_DTL_LINE_SUB_NUM";

    /** */
    private static final String ORD_SRC_REF_LINE_NUM = "ORD_SRC_REF_LINE_NUM";

    /** */
    private static final String ORD_SRC_REF_LINE_SUB_NUM = "ORD_SRC_REF_LINE_SUB_NUM";

    /** */
    private static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** */
    private static final String BILL_TO_CUST_LOC_CD = "BILL_TO_CUST_LOC_CD";

    /** */
    private static final String SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";

    /** */
    private static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** */
    private static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** */
    private static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** */
    private static final String FROM_INVTY_ACCT_CD = "FROM_INVTY_ACCT_CD";

    /** */
    private static final String FROM_RTL_WH_CATG_CD = "FROM_RTL_WH_CATG_CD";

    /** */
    private static final String FROM_CMPY_INVTY_FLG = "FROM_CMPY_INVTY_FLG";

    /** */
    private static final String TO_INVTY_ACCT_CD = "TO_INVTY_ACCT_CD";

    /** */
    private static final String TO_RTL_WH_CATG_CD = "TO_RTL_WH_CATG_CD";

    /** */
    private static final String TO_CMPY_INVTY_FLG = "TO_CMPY_INVTY_FLG";

    /** */
    private static final String LOAN_DUMMY_WH_CD = "LOAN_DUMMY_WH_CD";

    /** */
    private static final String PO_RCV_TRX_HDR_NUM = "PO_RCV_TRX_HDR_NUM";

    /** Add QC#55313 */
    private static final String VND_CD = "VND_CD";

    /** SQLID:PO_RCV_HDR */
    private static final String SQLID_PO_HDR = "001";

    /** SQLID:INBD_VIS (Domestic) */
    private static final String SQLID_INBD_VIS_DO = "003";

    /** SQLID:RWS_DTL */
    private static final String SQLID_RWS_DTL = "001";

    /** SQLID:SHPG_ORD_DTL */
    private static final String SQLID_SHPG_DTL = "001";

    /** SQLID:SQLID_RWS_PUT_AWAY_SER_WRK */
    private static final String SQLID_RWS_PUT_AWAY_SER_WRK = "001";

    /** */
    private static final int DATE_END_DIGIT = 8;

    /** */
    private S21SsmLowLevelCodingClient ssmClient = null;

    /** */
    private S21SsmBatchClient ssmBatchClient = null;

    /** */
    private String sysDt = "";

    /** */
    private String sysDtTs = "";

    /** Locale: YYYYMMDDHHMMSSsss */
    private static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    /** Currency Code */
    private String stdCcyCd = "";

    /** */
    private final List<String> onSceTpList = new ArrayList<String>();

    /** */
    private final List<String> offSceTpList = new ArrayList<String>();

    /** */
    private boolean dataErrFlg = false;

    /**
     * <pre>
     * </pre>
     */
    public NLZC208001() {

        super();
        this.ssmClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IV);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_CT);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IO);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_CO);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_CA);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_DO);
        
        // 10/10/2017 CITS T.Hakodate Update QC#21857 START
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_DP);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_DG);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_DN);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_DT);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_TR);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RT);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RU);
        // 10/10/2017 CITS T.Hakodate Update QC#21857 END
        
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RF);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RC);
        // onSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_LT);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_TI);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RB);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RA);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_DE);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_DC);

        // 10/10/2017 CITS T.Hakodate Update QC#21857 START
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_KT);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_KC);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_KU);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RP);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RM);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_BB);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IT);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_SC);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IC);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_SW);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_CC);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RM);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RL);
        // offSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RX);
        // 10/10/2017 CITS T.Hakodate Update QC#21857 END
        
        // imptSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IV);
        // imptSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_CT);
        // imptSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IO);
        // imptSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_CO);
        // imptSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_CA);
    }

    /**
     * <pre>
     * execute.
     * </pre>
     * 
     * @param param NLZC208001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC208001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkRws(msgMap, onBatchType)) {
            updatePutAway(msgMap, onBatchType);
        }

        msgMap.flush();
    }

    /**
     * Check RWS
     * 
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private boolean checkRws(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        PreparedStatement state = null;
        ResultSet rs = null;

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("wrkTrxId", param.wrkTrxId.getValue());
        paramMap.put("sqId", param.sqId.getValue());
        paramMap.put("glblCmpyCd", param.glblCmpyCd.getValue());
        paramMap.put("rwsStsPrnt", RWS_STS.PRINTED);

        try {

            state = this.ssmClient.createPreparedStatement("checkRws", paramMap);
            rs    = state.executeQuery();

            if (rs.next()) {

                String[] logParam = new String[2];
                logParam[0] = rs.getString("RWS_NUM_01");
                logParam[1] = rs.getString("RWS_NUM_02");

                dataErrFlg = false;
                addErrLogMsg(msgMap, NLZM1049E, NLZM1050E, logParam, onBatchType);
                return false;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(state, rs);
        }

        return true;
    }

    /**
     * <pre>
     * execute.
     * </pre>
     * 
     * @param params List<NLZC208001PMsg>
     * @param onBatchType ONBATCH_TYPE
     * @see com.canon.cusa.s21.api.NLZ.NLZC208001.execute
     */
    public void execute(final List<NLZC208001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NLZC208001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * <ul>
     * <li>RWS Header Close</li>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void updatePutAway(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();
        dataErrFlg = false;

        boolean paramErrFlg = checkParam(msgMap, onBatchType);

        if (paramErrFlg) {
            return;
        }
        
        // 10/10/2017 CITS T.Hakodate Update QC#21857 START
        String[] onSceTpListConst = null;
        String[] offSceTpListConst = null;
        offSceTpListConst = ZYPCodeDataUtil.getVarCharConstValue("NLZC2080_OFF_SCE_TP", param.glblCmpyCd.getValue()).split(",");
        onSceTpListConst = ZYPCodeDataUtil.getVarCharConstValue("NLZC2080_ON_SCE_TP", param.glblCmpyCd.getValue()).split(",");

        for (String onSceTp : onSceTpListConst) {

            onSceTpList.add(onSceTp);
        }

        for (String offSceTp : offSceTpListConst) {

            offSceTpList.add(offSceTp);
        }

        // 10/10/2017 CITS T.Hakodate Update QC#21857 END

        sysDt = ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD);
        sysDtTs = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);

        RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT = new RWS_PUT_AWAY_WRKTMsg();
        rwsPutAwayWrkT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        rwsPutAwayWrkT.wrkTrxId.setValue(param.wrkTrxId.getValue());
        rwsPutAwayWrkT.sqId.setValue(param.sqId.getValue());

        rwsPutAwayWrkT = (RWS_PUT_AWAY_WRKTMsg) S21ApiTBLAccessor.findByKey(rwsPutAwayWrkT);

        if (rwsPutAwayWrkT == null) {
            addErrLogMsg(msgMap, NLZM1004E, NLZM1004E, null, onBatchType);
            return;
        }

        boolean wrkErrFlg = checkWrkData(msgMap, rwsPutAwayWrkT, onBatchType);

        if (wrkErrFlg) {
            return;
        }

        String sceOrdTpCd = rwsPutAwayWrkT.sceOrdTpCd.getValue();

        if (offSceTpList.contains(sceOrdTpCd)) {
            return;
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", param.glblCmpyCd.getValue());
        paramMap.put("rwsNum", rwsPutAwayWrkT.rwsNum.getValue());
        paramMap.put("rwsDtlLineNum", rwsPutAwayWrkT.rwsDtlLineNum.getValue());
        paramMap.put("prchReqRecTpCd", PRCH_REQ_REC_TP.TECH_REQUEST);

        String statementId = "";

        if (NLXSceConst.SCE_ORD_TP_CD_DG.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_DP.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_DN.equals(sceOrdTpCd)) {

            statementId = "getDgDpDnInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd)) {

            statementId = "getDtInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd)) {

            statementId = "getTrInfo";

        } else if (NLXSceConst.SCE_ORD_TP_CD_RT.equals(sceOrdTpCd)) {

            statementId = "getRtInfo";
        } else if (NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

            statementId = "getRuInfo";
        // QC#55313
        } else if (NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {
            statementId = "getRpInfo";
        }

        PreparedStatement updateState = null;
        ResultSet updateRs = null;

        try {

            updateState = this.ssmClient.createPreparedStatement(statementId, paramMap);
            updateRs = updateState.executeQuery();

            boolean cur = updateRs.next();

            if (!cur) {
                String[] list = new String[] {statementId, NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), rwsPutAwayWrkT.rwsNum.getValue(), rwsPutAwayWrkT.rwsDtlLineNum.getValue()) };
                addErrLogMsg(msgMap, NLZM1015E, NLAM1132E, list, onBatchType);
                return;
            }

            NLZC208001Bean01 bean = setBean(updateRs, sceOrdTpCd);

            boolean errFlg = checkInputData(msgMap, rwsPutAwayWrkT, bean, onBatchType);

            if (errFlg) {
                return;
            }

            updateRwsResult(msgMap, rwsPutAwayWrkT, bean, onBatchType);

            boolean closeFlg = closeRwsHeader(msgMap, bean, onBatchType);

            if (closeFlg) {

                NLXRWSClose common = new NLXRWSClose();
                common.closeRws(param.glblCmpyCd.getValue(), bean.getRwsNum(), sceOrdTpCd, sysDt, onBatchType);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(updateState, updateRs);
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
    private boolean checkParam(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();
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
     * <ul>
     * </ul>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     */
    private boolean checkWrkData(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        boolean errFlg = false;

        if (!onSceTpList.contains(rwsPutAwayWrkT.sceOrdTpCd.getValue())
                && !offSceTpList.contains(rwsPutAwayWrkT.sceOrdTpCd.getValue())) {
            addErrLogMsg(msgMap, NLZM1036E, NLZM1036E, null, onBatchType);
            errFlg = true;
        }

        STK_STSTMsg stkStsT = (STK_STSTMsg) ZYPCodeDataUtil.findByCode(STK_STS, param.glblCmpyCd.getValue(), rwsPutAwayWrkT.invtyStkStsCd.getValue());

        if (stkStsT == null) {
            addErrLogMsg(msgMap, NLZM2022E, NLZM2022E, null, onBatchType);
            errFlg = true;
        }

        BigDecimal rwsQty = rwsPutAwayWrkT.rwsStkQty.getValue();

        if (BigDecimal.ZERO.compareTo(rwsQty) == 0) {
            errFlg = true;

            // Set first stock in date.
            setFirstStkInDt(param.glblCmpyCd.getValue(), rwsPutAwayWrkT.rwsNum.getValue());

        } else if (BigDecimal.ZERO.compareTo(rwsQty) > 0) {
            addErrLogMsg(msgMap, NLZM1014E, NLZM1014E, null, onBatchType);
            errFlg = true;
        }

        return errFlg;
    }

    /**
     * <pre>
     * <ul>
     * </ul>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     */
    private boolean checkInputData(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) {

        boolean errFlg = false;

        String dtlRwsStsCd = bean.getDtlRwsStsCd();
        String hdrRwsStsCd = bean.getHdrRwsStsCd();

        // DTL
        if (RWS_STS.RECEIVED.equals(dtlRwsStsCd)) {
            addErrLogMsg(msgMap, NLZM1007E, NLZM1007E, null, onBatchType);
            errFlg = true;
        } else if (RWS_STS.CANCELED.equals(dtlRwsStsCd)) {
            addErrLogMsg(msgMap, NLZM1013E, NLZM1013E, null, onBatchType);
            errFlg = true;
        } else if (!RWS_STS.PRINTED.equals(dtlRwsStsCd) && !RWS_STS.RECEIVING.equals(dtlRwsStsCd)) {
            addErrLogMsg(msgMap, NLZM1016E, NLZM1016E, null, onBatchType);
            errFlg = true;
        }

        // HDR
        if (RWS_STS.RECEIVED.equals(hdrRwsStsCd)) {
            addErrLogMsg(msgMap, NLZM1007E, NLZM1007E, null, onBatchType);
            errFlg = true;
        } else if (RWS_STS.CANCELED.equals(hdrRwsStsCd)) {
            addErrLogMsg(msgMap, NLZM1013E, NLZM1013E, null, onBatchType);
            errFlg = true;
        } else if (!RWS_STS.PRINTED.equals(hdrRwsStsCd) && !RWS_STS.RECEIVING.equals(hdrRwsStsCd)) {
            addErrLogMsg(msgMap, NLZM1016E, NLZM1016E, null, onBatchType);
            errFlg = true;
        }

        if (!bean.getSceOrdTpCd().equals(rwsPutAwayWrkT.sceOrdTpCd.getValue())) {
            addErrLogMsg(msgMap, NLZM1008E, NLZM1008E, null, onBatchType);
            errFlg = true;
        }

        if (!bean.getMdseCd().trim().equals(rwsPutAwayWrkT.mdseCd.getValue().trim())) {
            addErrLogMsg(msgMap, NLZM1009E, NLZM1009E, null, onBatchType);
            errFlg = true;
        } else {
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.mdseCd, bean.getMdseCd());
        }

        if (bean.getRwsQty().compareTo(bean.getRwsPutAwayQty().add(rwsPutAwayWrkT.rwsStkQty.getValue())) < 0) {
            addErrLogMsg(msgMap, NLZM1010E, NLZM1010E, null, onBatchType);
            errFlg = true;
        }

        if (!bean.getRwsRefNum().equals(rwsPutAwayWrkT.rwsRefNum.getValue())) {
            addErrLogMsg(msgMap, NLZM1018E, NLZM1018E, null, onBatchType);
            errFlg = true;
        }

        return errFlg;
    }

    /**
     * <pre>
     * updateRwsResult
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException
     */
    private void updateRwsResult(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        RWS_PUT_AWAYTMsg rwsPutAwayT = new RWS_PUT_AWAYTMsg();
        rwsPutAwayT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        rwsPutAwayT.rwsNum.setValue(rwsPutAwayWrkT.rwsNum.getValue());
        rwsPutAwayT.rwsDtlLineNum.setValue(rwsPutAwayWrkT.rwsDtlLineNum.getValue());
        rwsPutAwayT.invtyStkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());

        rwsPutAwayT = (RWS_PUT_AWAYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsPutAwayT);

        if (rwsPutAwayT == null) {

            rwsPutAwayT = new RWS_PUT_AWAYTMsg();
            rwsPutAwayT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            rwsPutAwayT.rwsNum.setValue(rwsPutAwayWrkT.rwsNum.getValue());
            rwsPutAwayT.rwsDtlLineNum.setValue(rwsPutAwayWrkT.rwsDtlLineNum.getValue());
            rwsPutAwayT.invtyStkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
            rwsPutAwayT.whCd.setValue(rwsPutAwayWrkT.whCd.getValue());
            rwsPutAwayT.rwsRefNum.setValue(rwsPutAwayWrkT.rwsRefNum.getValue());
            rwsPutAwayT.sceOrdTpCd.setValue(rwsPutAwayWrkT.sceOrdTpCd.getValue());
            rwsPutAwayT.rwsStkQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
            rwsPutAwayT.rwsStkDtTmTs.setValue(rwsPutAwayWrkT.rwsStkDtTmTs.getValue());
            rwsPutAwayT.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());

            S21ApiTBLAccessor.insert(rwsPutAwayT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                        NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                throw new S21AbendException(NLAM1133E, list);
            }

        } else {

            BigDecimal putAwayRwsStkQty = NLXC014001.nullToZero(rwsPutAwayT.rwsStkQty.getValue());
            BigDecimal wrkRwsStkQty = NLXC014001.nullToZero(rwsPutAwayWrkT.rwsStkQty.getValue());
            BigDecimal rwsStkQty = putAwayRwsStkQty.add(wrkRwsStkQty);
            rwsPutAwayT.rwsStkQty.setValue(rwsStkQty);
            rwsPutAwayT.rwsStkDtTmTs.setValue(rwsPutAwayWrkT.rwsStkDtTmTs.getValue());

            S21ApiTBLAccessor.update(rwsPutAwayT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayT.getReturnCode())) {
                String[] list = new String[] {rwsPutAwayT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM, INVTY_STK_STS_CD),
                        NLXCMsgHelper.toListedString(rwsPutAwayT.glblCmpyCd.getValue(), rwsPutAwayT.rwsNum.getValue(), rwsPutAwayT.rwsDtlLineNum.getValue(), rwsPutAwayT.invtyStkStsCd.getValue()) };
                throw new S21AbendException(NLAM1134E, list);
            }
        }

        // Set first stock in date.
        setFirstStkInDt(param.glblCmpyCd.getValue(), bean.getRwsNum());

        RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();
        rwsDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        rwsDtlT.rwsNum.setValue(bean.getRwsNum());
        rwsDtlT.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());

        BigDecimal putAwayQty = NLXC014001.nullToZero(bean.getRwsPutAwayQty());
        BigDecimal wrkRwsStkQty = NLXC014001.nullToZero(rwsPutAwayWrkT.rwsStkQty.getValue());
        BigDecimal rwsPutAwayQty = putAwayQty.add(wrkRwsStkQty);
        rwsDtlT.rwsPutAwayQty.setValue(rwsPutAwayQty);
        rwsDtlT.rwsStsCd.setValue(RWS_STS.RECEIVING);
        String[] updtList = new String[] {"rwsPutAwayQty", "rwsStsCd" };

        if (bean.getRwsQty().equals(rwsPutAwayQty)) {
            ZYPEZDItemValueSetter.setValue(rwsDtlT.rwsStsCd, RWS_STS.RECEIVED);
            ZYPEZDItemValueSetter.setValue(rwsDtlT.schdCoordStsCd, SCHD_COORD_STS.CLOSED);
            updtList = new String[] {"rwsPutAwayQty", "rwsStsCd", "schdCoordStsCd" };

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

        S21ApiTBLAccessor.updateSelectionField(rwsDtlT, updtList);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlT.getReturnCode())) {
            String[] list = new String[] {rwsDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_DTL_LINE_NUM),
                    NLXCMsgHelper.toListedString(rwsDtlT.glblCmpyCd.getValue(), rwsDtlT.rwsNum.getValue(), rwsDtlT.rwsDtlLineNum.getValue()) };
            throw new S21AbendException(NLAM1134E, list);
        }

        // select from RWS_PUT_AWAY_SER_WRK
        RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
        rwsPutAwaySerWrkT.setSQLID(SQLID_RWS_PUT_AWAY_SER_WRK);
        rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", param.wrkTrxId.getValue());
        rwsPutAwaySerWrkT.setConditionValue("sqId01", param.sqId.getValue());
        rwsPutAwaySerWrkT.setConditionValue("rwsNum01", bean.getRwsNum());
        rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", bean.getRwsDtlLineNum());

        RWS_PUT_AWAY_SER_WRKTMsgArray msgArr = (RWS_PUT_AWAY_SER_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(rwsPutAwaySerWrkT);

        // insert into RWS_PUT_AWAY_SER
        for (int i = 0; i < msgArr.getValidCount(); i++) {
            RWS_PUT_AWAY_SER_WRKTMsg msg = (RWS_PUT_AWAY_SER_WRKTMsg) msgArr.get(i);

            RWS_PUT_AWAY_SERTMsg rwsPutAwaySerT = new RWS_PUT_AWAY_SERTMsg();
            rwsPutAwaySerT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            rwsPutAwaySerT.rwsNum.setValue(bean.getRwsNum());
            rwsPutAwaySerT.rwsLineNum.setValue(bean.getRwsDtlLineNum());
            rwsPutAwaySerT.invtyStkStsCd.setValue(msg.invtyStkStsCd.getValue());
            rwsPutAwaySerT.serNum.setValue(msg.serNum.getValue());
            rwsPutAwaySerT.mdseCd.setValue(msg.mdseCd.getValue());
            rwsPutAwaySerT.serNumSendFlg.setValue(ZYPConstant.FLG_OFF_N);

            S21ApiTBLAccessor.insert(rwsPutAwaySerT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwaySerT.getReturnCode())) {
                String[] list = new String[] {
                        rwsPutAwaySerT.getTableName(),
                        NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_LINE_NUM, INVTY_STK_STS_CD, SER_NUM, MDSE_CD),
                        NLXCMsgHelper.toListedString(rwsPutAwaySerT.glblCmpyCd.getValue(), rwsPutAwaySerT.rwsNum.getValue(), rwsPutAwaySerT.rwsLineNum.getValue(), rwsPutAwaySerT.invtyStkStsCd.getValue(), rwsPutAwaySerT.serNum.getValue(),
                                rwsPutAwaySerT.mdseCd.getValue()) };
                throw new S21AbendException(NLAM1133E, list);
            }
        }

        // close each order
        String sceOrdTpCd = rwsPutAwayWrkT.sceOrdTpCd.getValue();

        if (NLXSceConst.SCE_ORD_TP_CD_DN.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_DP.equals(sceOrdTpCd)) {
            closeDnDp(msgMap, rwsPutAwayWrkT, bean, onBatchType, sceOrdTpCd);

        } else if (NLXSceConst.SCE_ORD_TP_CD_DG.equals(sceOrdTpCd)) {
            closeDg(msgMap, rwsPutAwayWrkT, bean, onBatchType);

        } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd)) {
            closeDt(msgMap, rwsPutAwayWrkT, bean, onBatchType);

        } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd)) {
            closeTr(msgMap, rwsPutAwayWrkT, bean, onBatchType);

        } else if (NLXSceConst.SCE_ORD_TP_CD_RT.equals(sceOrdTpCd)) {
            closeRt(msgMap, rwsPutAwayWrkT, bean, onBatchType);

        } else if (NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {
            closeRu(msgMap, rwsPutAwayWrkT, bean, onBatchType);
        // QC#55313
        } else if (NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {
            closeRp(msgMap, rwsPutAwayWrkT, bean, onBatchType);
        }
    }

    /**
     * <pre>
     * Close Order for DN and DP
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @param onBatchType ONBATCH_TYPE
     * @param sceOrdTpCd String
     * @throws SQLException
     */
    private void closeDnDp(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType, String sceOrdTpCd) throws SQLException {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        // call Inventory Update API (NLZC0020)
        NLZC002001PMsg inPMsg = getPmsgDoDp(param, rwsPutAwayWrkT, bean, sceOrdTpCd);

        NLZC002001 api = new NLZC002001();
        api.execute(inPMsg, onBatchType);

        checkApiErr(msgMap, inPMsg);

        // close PO_RCV_DTL
        closePoDetail(msgMap, rwsPutAwayWrkT, bean);

        // call PO Status Update API (NPZC0040)
        callNPZC004001(msgMap , param.glblCmpyCd.getValue(), bean.getPoOrdNum(), bean.getPoOrdDtlLineNum(),
                rwsPutAwayWrkT.mdseCd.getValue(), rwsPutAwayWrkT.rwsStkQty.getValue(), onBatchType, false);

        BigDecimal remainingQty=bean.getRwsQty().subtract(bean.getRwsPutAwayQty());
        BigDecimal rwsStkQty=rwsPutAwayWrkT.rwsStkQty.getValue();
        String rwsCloDtTmTs=null;
        if(remainingQty.compareTo(rwsStkQty)==0) {
            rwsCloDtTmTs=rwsPutAwayWrkT.rwsStkDtTmTs.getValue();
        }
        // call Purchase Requisition Update API (NPZC1030)
        callNPZC103001(param.glblCmpyCd.getValue(), bean, rwsCloDtTmTs, rwsStkQty, onBatchType);

        // update Inbound Visibility
        updateInbdVisDo(msgMap, rwsPutAwayWrkT, bean);

        // close PO_RCV_HDR
        boolean closeFlg = closePoHeader(msgMap, bean);

        if (closeFlg) {
            callNPZC004001(msgMap , param.glblCmpyCd.getValue(), bean.getPoOrdNum(), null, null, null, onBatchType, true);
        }
    }

    /**
     * <pre>
     * Close Order for DG
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException
     */
    private void closeDg(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        // call Inventory Update API (NLZC0020)
        List<NLZC002001PMsg> pMsgList = getPmsgDg(param, rwsPutAwayWrkT, bean);

        NLZC002001 api = new NLZC002001();
        api.execute(pMsgList, onBatchType);

        for (NLZC002001PMsg msg : pMsgList) {
            checkApiErr(msgMap, msg);
        }

        // close PO_RCV_DTL
        closePoDetail(msgMap, rwsPutAwayWrkT, bean);

        // call PO Status Update API (NPZC0040)
        callNPZC004001(msgMap , param.glblCmpyCd.getValue(), bean.getPoOrdNum(), bean.getPoOrdDtlLineNum(),
                rwsPutAwayWrkT.mdseCd.getValue(), rwsPutAwayWrkT.rwsStkQty.getValue(), onBatchType, false);

        BigDecimal remainingQty=bean.getRwsQty().subtract(bean.getRwsPutAwayQty());
        BigDecimal rwsStkQty=rwsPutAwayWrkT.rwsStkQty.getValue();
        String rwsCloDtTmTs=null;
        if(remainingQty.compareTo(rwsStkQty)==0) {
            rwsCloDtTmTs=rwsPutAwayWrkT.rwsStkDtTmTs.getValue();
        }
        // call Purchase Requisition Update API (NPZC1030)
        callNPZC103001(param.glblCmpyCd.getValue(), bean, rwsCloDtTmTs, rwsStkQty, onBatchType);

        // update Inbound Visibility
        updateInbdVisDo(msgMap, rwsPutAwayWrkT, bean);

        // close PO_RCV_HDR
        boolean closeFlg = closePoHeader(msgMap, bean);

        if (closeFlg) {
            callNPZC004001(msgMap , param.glblCmpyCd.getValue(), bean.getPoOrdNum(), null, null, null, onBatchType, true);
        }
    }

    /**
     * <pre>
     * Close Order for DT
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     */
    private void closeDt(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        // call Inventory Update API (NLZC0020)
        List<NLZC002001PMsg> pMsgList = getPmsgDt(param, rwsPutAwayWrkT, bean);

        NLZC002001 api = new NLZC002001();
        api.execute(pMsgList, onBatchType);

        for (NLZC002001PMsg msg : pMsgList) {
            checkApiErr(msgMap, msg);
        }

        // close PO_RCV_DTL
        closePoDetail(msgMap, rwsPutAwayWrkT, bean);

        BigDecimal remainingQty=bean.getRwsQty().subtract(bean.getRwsPutAwayQty());
        BigDecimal rwsStkQty=rwsPutAwayWrkT.rwsStkQty.getValue();
        String rwsCloDtTmTs=null;
        if(remainingQty.compareTo(rwsStkQty)==0) {
            rwsCloDtTmTs=rwsPutAwayWrkT.rwsStkDtTmTs.getValue();
        }
        // call Purchase Requisition Update API (NPZC1030)
        callNPZC103001(param.glblCmpyCd.getValue(), bean, rwsCloDtTmTs, rwsStkQty, onBatchType);

        // update Inbound Visibility
        updateInbdVisDo(msgMap, rwsPutAwayWrkT, bean);

        // close PO_RCV_HDR
        closePoHeader(msgMap, bean);

        // Close CPO
        closeCpoDtailForDt(msgMap, bean);

        // Close CPO Header
        closeCpoHeaderForDt(msgMap, bean);
    }

    /**
     * <pre>
     * Close Order for TR
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     */
    private void closeTr(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        // call Inventory Update API (NLZC0020)
        List<NLZC002001PMsg> pMsgList = getPmsgTr(param, rwsPutAwayWrkT, bean);

        NLZC002001 api = new NLZC002001();
        api.execute(pMsgList, onBatchType);

        for (NLZC002001PMsg msg : pMsgList) {
            checkApiErr(msgMap, msg);
        }

        // close PO_RCV_DTL
        boolean closeFlg = closePoDetail(msgMap, rwsPutAwayWrkT, bean);

        BigDecimal remainingQty=bean.getRwsQty().subtract(bean.getRwsPutAwayQty());
        BigDecimal rwsStkQty=rwsPutAwayWrkT.rwsStkQty.getValue();
        String rwsCloDtTmTs=null;
        if(remainingQty.compareTo(rwsStkQty)==0) {
            rwsCloDtTmTs=rwsPutAwayWrkT.rwsStkDtTmTs.getValue();
        }
        // call Purchase Requisition Update API (NPZC1030)
        callNPZC103001(param.glblCmpyCd.getValue(), bean, rwsCloDtTmTs, rwsStkQty, onBatchType);

        // call Inventory Order API (NLZC0030)
        if (closeFlg) {
            callNLZC003001(param.glblCmpyCd.getValue(), bean.getInvtyOrdNum(), bean.getInvtyOrdLineNum(), "", onBatchType, false);
        }

        // update Inbound Visibility
        updateInbdVisDo(msgMap, rwsPutAwayWrkT, bean);

        // close PO_RCV_HDR
        closeFlg = closePoHeader(msgMap, bean);

        // call Inventory Order API (NLZC0030)
        if (closeFlg) {
            callNLZC003001(param.glblCmpyCd.getValue(), "", "", bean.getSoNum(), onBatchType, true);
        }
    }

    /**
     * <pre>
     * Close Order for RT
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     */
    private void closeRt(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        // call Inventory Update API (NLZC0020)
        // Mod QC#54864
        List<NLZC002001PMsg> pMsgList = getPmsgRt(param, rwsPutAwayWrkT, bean, msgMap, onBatchType);

        NLZC002001 api = new NLZC002001();
        api.execute(pMsgList, onBatchType);

        for (NLZC002001PMsg msg : pMsgList) {
            checkApiErr(msgMap, msg);
        }

        // call CPO Return Update API (NWZC1530)
        callNWZC153001(msgMap, bean, rwsPutAwayWrkT, onBatchType);
    }

    /**
     * <pre>
     * Close Order for RU
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     */
    private void closeRu(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        // call Inventory Update API (NLZC0020)
        List<NLZC002001PMsg> pMsgList = getPmsgRu(param, rwsPutAwayWrkT, bean);

        NLZC002001 api = new NLZC002001();
        api.execute(pMsgList, onBatchType);

        for (NLZC002001PMsg msg : pMsgList) {
            checkApiErr(msgMap, msg);
        }

        // update Inbound Visibility
        updateInbdVisDo(msgMap, rwsPutAwayWrkT, bean);

        // close PO_RCV_DTL
        boolean closeFlg = closePoDetail(msgMap, rwsPutAwayWrkT, bean);

        // close PO_RCV_HDR
        if(closeFlg){
            closePoHeader(msgMap, bean);
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param bean NLZC208001Bean01
     * @return boolean true:Close
     */
    private boolean closeRwsHeader(final S21ApiMessageMap msgMap, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) {

        boolean closeFlg = false;

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        RWS_DTLTMsg rwsDtlT = new RWS_DTLTMsg();

        rwsDtlT.setSQLID(SQLID_RWS_DTL);

        rwsDtlT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsDtlT.setConditionValue("rwsNum01", bean.getRwsNum());

        RWS_DTLTMsgArray msgArr = (RWS_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(rwsDtlT);

        boolean receivingFlg = false;

        for (int i = 0; i < msgArr.length(); i++) {

            RWS_DTLTMsg rwsDtlList = (RWS_DTLTMsg) msgArr.get(i);
            String rwsStsCd = rwsDtlList.rwsStsCd.getValue();

            if (!RWS_STS.CANCELED.equals(rwsStsCd) && !RWS_STS.RECEIVED.equals(rwsStsCd)) {
                receivingFlg = true;
                break;
            }
        }

        String rwsStsCd = "";

        if (receivingFlg) {
            rwsStsCd = RWS_STS.RECEIVING;
        } else {
            rwsStsCd = RWS_STS.RECEIVED;
            closeFlg = true;
        }

        if (!rwsStsCd.equals(bean.getHdrRwsStsCd())) {

            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            rwsHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            rwsHdrT.rwsNum.setValue(bean.getRwsNum());
            rwsHdrT.rwsStsCd.setValue(rwsStsCd);
            String[] updtList = new String[] {"rwsStsCd" };

            if (closeFlg) {
                String salesDateTm = getSalesDateTm();
                rwsHdrT.rwsCloDtTmTs.setValue(salesDateTm);
                updtList = new String[] {"rwsStsCd", "rwsCloDtTmTs" };
            }

            S21ApiTBLAccessor.updateSelectionField(rwsHdrT, updtList);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                String[] list = new String[] {rwsHdrT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM), NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd.getValue(), rwsHdrT.rwsNum.getValue()) };
                throw new S21AbendException(NLAM1134E, list);
            }

            rwsHdrT = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(rwsHdrT);
            if (NLXSceConst.SCE_ORD_TP_CD_RT.equals(rwsHdrT.sceOrdTpCd.getValue())) {

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
                if (rmaLineNo > 0){
                    NWZC188001 nwzc188001 = new NWZC188001();
                    nwzc188001.execute(pMsg, onBatchType);
                }
            }

            // QC#19381 ADD START
            if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(rwsHdrT.sceOrdTpCd.getValue())) {
                NWZC188001PMsg pMsg = new NWZC188001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd,param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum,rwsHdrT.srcOrdNum.getValue());
                NWZC188001 nwzc188001 = new NWZC188001();
                nwzc188001.execute(pMsg, onBatchType);
            }
            // QC#19381 ADD END

        }

        return closeFlg;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @return boolean true:Close
     */
    private boolean closePoDetail(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {

        boolean closeFlg = false;

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        checkSelectKeyValue(bean.getTrxLineNum(), RWS_DTL, TRX_LINE_NUM);

        PO_RCV_DTLTMsg poRcvDtlT = new PO_RCV_DTLTMsg();
        poRcvDtlT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        poRcvDtlT.poRcvNum.setValue(bean.getTrxOrdNum());
        poRcvDtlT.poRcvLineNum.setValue(bean.getTrxLineNum());

        poRcvDtlT = (PO_RCV_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poRcvDtlT);

        if (poRcvDtlT == null) {
            String[] list = new String[] {PO_RCV_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getTrxOrdNum(), bean.getTrxLineNum()) };
            throw new S21AbendException(NLAM1001E, list);
        }

        BigDecimal actlQty = NLXC014001.nullToZero(poRcvDtlT.actlRcvQty.getValue());
        BigDecimal rwsStkQty = NLXC014001.nullToZero(rwsPutAwayWrkT.rwsStkQty.getValue());
        BigDecimal actlRcvQty = actlQty.add(rwsStkQty);
        poRcvDtlT.actlRcvQty.setValue(actlRcvQty);

        BigDecimal poRcvQty = NLXC014001.nullToZero(poRcvDtlT.poRcvQty.getValue());
        BigDecimal lossQty = NLXC014001.nullToZero(poRcvDtlT.lossQty.getValue());
        if (poRcvQty.compareTo(actlRcvQty.add(lossQty)) == 0) {
            poRcvDtlT.rwsStsCd.setValue(RWS_STS.RECEIVED);
            closeFlg = true;
        } else if (poRcvQty.compareTo(actlRcvQty.add(lossQty)) > 0) {
            poRcvDtlT.rwsStsCd.setValue(RWS_STS.RECEIVING);
        } else {
            throw new S21AbendException(NLZM1038E);
        }

        S21ApiTBLAccessor.update(poRcvDtlT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvDtlT.getReturnCode())) {
            String[] list = new String[] {poRcvDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM),
                    NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getTrxOrdNum(), bean.getTrxLineNum()) };
            throw new S21AbendException(NLAM1134E, list);
        }

        return closeFlg;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     */
    private void updateInbdVisDo(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        INBD_VISTMsg inbdVisT = new INBD_VISTMsg();

        inbdVisT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inbdVisT.poRcvNum.setValue(bean.getTrxOrdNum());
        inbdVisT.poRcvLineNum.setValue(bean.getTrxLineNum());
        inbdVisT.visLocTpCd.setValue(VIS_LOC_TP.DC);
        inbdVisT.visLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        inbdVisT.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);
        inbdVisT.inbdVisActlFlg.setValue(ZYPConstant.FLG_OFF_N);

        INBD_VISTMsg planInVisT = (INBD_VISTMsg) this.ssmBatchClient.queryObject("getPlanInvdVisInfoDo", inbdVisT);

        // QC#54627
//        if (planInVisT == null) {
//            String list = NLXCMsgHelper.toListedString(
//                      param.glblCmpyCd.getValue()
//                    , bean.getTrxOrdNum()
//                    , bean.getTrxLineNum()
//                    , VIS_LOC_TP.DC
//                    , rwsPutAwayWrkT.whCd.getValue()
//                    , ZYPConstant.FLG_ON_Y
//                    , ZYPConstant.FLG_OFF_N
//            );
//            throw new S21AbendException(NLAM1132E, new String[] {SQLID_INBD_VIS_DO, list});
//        } 
        if (planInVisT != null) {
            planInVisT.inbdLtstRecFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        INBD_VISTMsg inVisT = new INBD_VISTMsg();

        BigDecimal inbdVisPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);

        inVisT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inVisT.inbdVisPk.setValue(inbdVisPk);
        inVisT.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);
        inVisT.inbdVisEventCd.setValue(INBD_VIS_EVENT.RWS_PUTAWAY_RECEIVE);
        inVisT.inbdVisDataTpCd.setValue(INBD_VIS_DATA_TP.STOCK_IN_DC);
        inVisT.inbdVisActlFlg.setValue(ZYPConstant.FLG_ON_Y);
        inVisT.rwsNum.setValue(bean.getRwsNum());
        inVisT.poRcvNum.setValue(bean.getTrxOrdNum());
        inVisT.poRcvLineNum.setValue(bean.getTrxLineNum());
        inVisT.rwsRefNum.setValue(bean.getRwsRefNum());
        inVisT.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        inVisT.mdseCd.setValue(bean.getMdseCd());
        inVisT.visLocTpCd.setValue(VIS_LOC_TP.DC);
        inVisT.visLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        inVisT.visLocNm.setValue(bean.getLocNm());
        inVisT.visQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        inVisT.etaEtdDt.setValue(S21StringUtil.subStringByLength(rwsPutAwayWrkT.rwsStkDtTmTs.getValue(), 0, DATE_END_DIGIT));
        inVisT.calcFlg.setValue(ZYPConstant.FLG_OFF_N);
        inVisT.cratTs.setValue(sysDtTs);
        inVisT.regdTs.setValue(sysDtTs);
        inVisT.invtyStkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
        inVisT.imptCustPoNum.setValue(bean.getCustPoNum());

        S21ApiTBLAccessor.insert(inVisT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inVisT.getReturnCode())) {
            String[] list = new String[] {inVisT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, INBD_VIS_PK), NLXCMsgHelper.toListedString(inVisT.glblCmpyCd.getValue(), inVisT.inbdVisPk.getValue()) };
            throw new S21AbendException(NLAM1133E, list);
        }

        // QC#54627 Start
        if (planInVisT == null) {
            return;
        }

        if (planInVisT.visQty.getValue().compareTo(inVisT.visQty.getValue()) != 0) {
            // Partially Received
            planInVisT.visQty.setValue(planInVisT.visQty.getValue().subtract(inVisT.visQty.getValue()));

            //Insert adjustment recode
            INBD_VISTMsg adjustInVisT = new INBD_VISTMsg();
            EZDMsg.copy(planInVisT, null, adjustInVisT, null);

            planInVisT.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);

            BigDecimal inbdVisForAdjustPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);
            BigDecimal adjustQty = inVisT.visQty.getValue().multiply(new BigDecimal(-1));

            adjustInVisT.inbdVisPk.setValue(inbdVisForAdjustPk);
            adjustInVisT.visQty.setValue(adjustQty);

            S21ApiTBLAccessor.insert(adjustInVisT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(adjustInVisT.getReturnCode())) {
                String[] list = new String[] {adjustInVisT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, INBD_VIS_PK), NLXCMsgHelper.toListedString(adjustInVisT.glblCmpyCd.getValue(), adjustInVisT.inbdVisPk.getValue()) };
                throw new S21AbendException(NLAM1133E, list);
            }

        } else {
            BigDecimal adjustQty = inVisT.visQty.getValue().multiply(new BigDecimal(-1));
            planInVisT.visQty.setValue(adjustQty);
        }

        S21ApiTBLAccessor.update(planInVisT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(planInVisT.getReturnCode())) {
            String[] list = new String[] {planInVisT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, INBD_VIS_PK), NLXCMsgHelper.toListedString(planInVisT.glblCmpyCd.getValue(), planInVisT.inbdVisPk.getValue()) };
            throw new S21AbendException(NLAM1133E, list);
        }
        // QC#54627 End
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param bean NLZC208001Bean01
     * @return boolean true:Close
     */
    private boolean closePoHeader(final S21ApiMessageMap msgMap, final NLZC208001Bean01 bean) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();
        boolean closeFlg = false;

        PO_RCV_DTLTMsg poRcvDtlT = new PO_RCV_DTLTMsg();

        poRcvDtlT.setSQLID(SQLID_PO_HDR);

        poRcvDtlT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        poRcvDtlT.setConditionValue("poRcvNum01", bean.getTrxOrdNum());

        PO_RCV_DTLTMsgArray msgArr = (PO_RCV_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(poRcvDtlT);

        PO_RCV_HDRTMsg poRcvHdrT = new PO_RCV_HDRTMsg();
        poRcvHdrT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        poRcvHdrT.poRcvNum.setValue(bean.getTrxOrdNum());

        poRcvHdrT = (PO_RCV_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poRcvHdrT);

        if (poRcvHdrT == null) {
            String[] list = new String[] {PO_RCV_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getTrxOrdNum()) };
            throw new S21AbendException(NLAM1001E, list);
        }

        boolean receivingFlg = false;

        for (int i = 0; i < msgArr.length(); i++) {

            PO_RCV_DTLTMsg poRcv = (PO_RCV_DTLTMsg) msgArr.get(i);
            String rwsStsCd = poRcv.rwsStsCd.getValue();

            if (!RWS_STS.CANCELED.equals(rwsStsCd) && !RWS_STS.RECEIVED.equals(rwsStsCd)) {
                receivingFlg = true;
                break;
            }
        }

        String rwsStsCd = "";

        if (receivingFlg) {
            rwsStsCd = RWS_STS.RECEIVING;
        } else {
            rwsStsCd = RWS_STS.RECEIVED;
            closeFlg = true;
        }

        if (!rwsStsCd.equals(poRcvHdrT.rwsStsCd.getValue())) {

            poRcvHdrT.rwsStsCd.setValue(rwsStsCd);

            S21ApiTBLAccessor.update(poRcvHdrT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvHdrT.getReturnCode())) {
                String[] list = new String[] {poRcvHdrT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getTrxOrdNum()) };
                throw new S21AbendException(NLAM1134E, list);
            }
        }

        return closeFlg;
    }

    /**
     * closeCpoDtailForDt
     * @param msgMap S21ApiMessageMap
     * @param bean NLZC208001Bean01
     */
    private void closeCpoDtailForDt(final S21ApiMessageMap msgMap, final NLZC208001Bean01 bean) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bean.getCpoOrdNum());
        queryParam.put("cpoDtlLineNum", bean.getCpoDtlLineNum());
        queryParam.put("cpoDtlLineSubNum", bean.getCpoDtlLineSubNum());
        queryParam.put("shipPlnCancFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("shipFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("rwsOpenFlgY", ZYPConstant.FLG_ON_Y);

        Integer openShpgPlanAndRwsDtlCnt = (Integer) ssmBatchClient.queryObject("queryCpoDtlUpdateJudgement", queryParam);

        if (openShpgPlanAndRwsDtlCnt.intValue() == 0) {

            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, bean.getCpoOrdNum());
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, bean.getCpoDtlLineNum());
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, bean.getCpoDtlLineSubNum());

            cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);

            if (cpoDtlTMsg == null) {

                String[] list = new String[] {CPO_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM, CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getCpoOrdNum(), bean.getCpoDtlLineNum(), bean.getCpoDtlLineSubNum()) };
                throw new S21AbendException(NLAM1001E, list);
            }

            boolean cpoDtlUpd = false;

            // Open Flag
            if (!ZYPConstant.FLG_OFF_N.equals(cpoDtlTMsg.openFlg.getValue())) {

                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.openFlg, ZYPConstant.FLG_OFF_N);
                cpoDtlUpd = true;
            }

            // Line Status
            if (!ORD_LINE_STS.CLOSED.equals(cpoDtlTMsg.ordLineStsCd.getValue()) && !ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg.ordLineStsCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ordLineStsCd, ORD_LINE_STS.CLOSED);
                cpoDtlUpd = true;
            }

            if (cpoDtlUpd) {

                S21ApiTBLAccessor.update(cpoDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {

                    String[] list = new String[] {CPO_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM, CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM)
                            , NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getCpoOrdNum(), bean.getCpoDtlLineNum(), bean.getCpoDtlLineSubNum()) };
                    throw new S21AbendException(NLAM1134E, list);
                }

                // Process Log Output
                final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
                bizProcLogMsg.setSubSysId("NLZ");
                bizProcLogMsg.setProcId("OM");
                bizProcLogMsg.setEventId("Close");
                bizProcLogMsg.setDocTpCd("OM");
                bizProcLogMsg.setDocId(bean.getCpoDtlLineNum() + "." + bean.getCpoDtlLineSubNum());
                bizProcLogMsg.setPrntDocId(bean.getCpoOrdNum());
                S21BusinessProcessLog.print(bizProcLogMsg);
            }
        }
    }

    /**
     * closeCpoHeaderForDt
     * @param msgMap S21ApiMessageMap
     * @param bean NLZC208001Bean01
     */
    private void closeCpoHeaderForDt(final S21ApiMessageMap msgMap, final NLZC208001Bean01 bean) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bean.getCpoOrdNum());
        queryParam.put("ordLineStsCdClosed", ORD_LINE_STS.CLOSED);
        queryParam.put("ordLineStsCdCancelled", ORD_LINE_STS.CANCELLED);

        Integer openOrdLineCnt = (Integer) ssmBatchClient.queryObject("queryCpoUpdateJudgement", queryParam);

        if (openOrdLineCnt == 0) {

            CPOTMsg cpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bean.getCpoOrdNum());

            cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsg);

            if (cpoTMsg == null) {

                String[] list = new String[] {CPO, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getCpoOrdNum()) };
                throw new S21AbendException(NLAM1001E, list);
            }

            ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, ORD_HDR_STS.CLOSED);

            S21ApiTBLAccessor.update(cpoTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {

                String[] list = new String[] {CPO, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, CPO_ORD_NUM), NLXCMsgHelper.toListedString(param.glblCmpyCd.getValue(), bean.getCpoOrdNum()) };
                throw new S21AbendException(NLAM1134E, list);
            }

            // Process Log Output
            final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
            bizProcLogMsg.setSubSysId("NLZ");
            bizProcLogMsg.setProcId("OM");
            bizProcLogMsg.setEventId("Close");
            bizProcLogMsg.setDocTpCd("OM");
            bizProcLogMsg.setPrntDocId(bean.getCpoOrdNum());
            S21BusinessProcessLog.print(bizProcLogMsg);
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param soNum SO_NUM
     * @return SHPG_ORD_DTLTMsgArray
     */
    private SHPG_ORD_DTLTMsgArray getShpgOrdDtl(final String glblCmpyCd, final String soNum) {

        SHPG_ORD_DTLTMsg shpgOrdDtlT = new SHPG_ORD_DTLTMsg();
        shpgOrdDtlT.setSQLID(SQLID_SHPG_DTL);

        shpgOrdDtlT.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shpgOrdDtlT.setConditionValue("soNum01", soNum);

        SHPG_ORD_DTLTMsgArray msgArr = (SHPG_ORD_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(shpgOrdDtlT);

        if (msgArr == null || msgArr.length() == 0) {
            String[] list = new String[] {shpgOrdDtlT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM), NLXCMsgHelper.toListedString(glblCmpyCd, soNum) };
            throw new S21AbendException(NLAM1001E, list);
        }

        return msgArr;

    }

    /**
     * <pre>
     * Close Inventory Order
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param invtyOrdNum INVTY_ORD_NUM
     * @param invtyOrdLineNum INVTY_ORD_LINE_NUM
     * @param soNum SO_NUM
     * @param hdrFlg true:HeaderClose false:DetailClose
     */
    private void callNLZC003001(final String glblCmpyCd, final String invtyOrdNum, final String invtyOrdLineNum, final String soNum, final ONBATCH_TYPE onBatchType, final boolean hdrFlg) {

        NLZC003001 invntOrdApi = new NLZC003001();
        NLZC003001PMsg pMsg;
        List<NLZC003001PMsg> pMsgList = new ArrayList<NLZC003001PMsg>();

        if (hdrFlg) {
            SHPG_ORD_DTLTMsgArray shpgOrdDtlArr = getShpgOrdDtl(glblCmpyCd, soNum);
            List<String> invtyOrdList = new ArrayList<String>();
            for (int i = 0; i < shpgOrdDtlArr.length(); i++) {
                SHPG_ORD_DTLTMsg shpgOrdDtl = (SHPG_ORD_DTLTMsg) shpgOrdDtlArr.get(i);
                String invtyOrd = shpgOrdDtl.trxHdrNum.getValue();
                if (ZYPCommonFunc.hasValue(invtyOrd) && !invtyOrdList.contains(invtyOrd)) {
                    invtyOrdList.add(invtyOrd);
                }
            }

            for (int i = 0; i < invtyOrdList.size(); i++) {
                pMsg = new NLZC003001PMsg();
                pMsg.xxProcTpCd.setValue(NLZC003001.PROC_TP_HDR_CLO);
                pMsg.xxDtTpCd.setValue(NLZC003001.DT_TP_HDR);
                pMsg.glblCmpyCd.setValue(glblCmpyCd);
                pMsg.invtyOrdNum.setValue(invtyOrdList.get(i));
                pMsgList.add(pMsg);
            }
        } else {
            pMsg = new NLZC003001PMsg();
            pMsg.xxProcTpCd.setValue(NLZC003001.PROC_TP_CLO);
            pMsg.xxDtTpCd.setValue(NLZC003001.DT_TP_DTL);
            pMsg.glblCmpyCd.setValue(glblCmpyCd);
            pMsg.invtyOrdNum.setValue(invtyOrdNum);
            pMsg.invtyOrdLineNum.setValue(invtyOrdLineNum);
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
     * Close Purchase Order
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param poOrdNum PO_ORD_NUM
     * @param poOrdDtlLineNum PO_ORD_DTL_LINE_NUM
     * @param mdseCd MDSE_CD
     * @param rwsStkQty RWS_STK_QTY
     * @param hdrFlg true:HeaderClose false:DetailClose
     */
    private void callNPZC004001(final S21ApiMessageMap msgMap , final String glblCmpyCd, final String poOrdNum, final String poOrdDtlLineNum,
            final String mdseCd, final BigDecimal rwsStkQty, final ONBATCH_TYPE onBatchType, final boolean hdrFlg) {

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

                // QC#16303 START
                // throw new S21AbendException(msgList.get(0));
                for (String messageId : msgList) {

                    if (ZYPCommonFunc.hasValue(messageId)) {

                        msgMap.addXxMsgId(messageId);

                    }
                }

                // QC#16303 END

            }
        }
    }

    /**
     * <pre>
     * Close Purchase Requisition
     * </pre>
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param bean NLZC208001Bean01
     * @param rwsPutAwayQty BigDecimal
     * @param onBatchType ONBATCH_TYPE
     */
    private void callNPZC103001(final String glblCmpyCd, NLZC208001Bean01 bean, final String rwsCloDtTmTs, final BigDecimal rwsPutAwayQty, final ONBATCH_TYPE onBatchType) {

        if (ZYPCommonFunc.hasValue(bean.getPrchReqNum())) {

            NPZC103001 prchReqApi = new NPZC103001();
            NPZC103001PMsg pMsg = new NPZC103001PMsg();

            List<NPZC103001PMsg> pMsgList = new ArrayList<NPZC103001PMsg>();

            // Set param
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
            ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_RECEIVED);
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, bean.getPrchReqNum());

            // Detail info
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineNum, bean.getPrchReqLineNum());
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineSubNum, bean.getPrchReqLineSubNum());
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsNum, bean.getRwsNum());
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsCloDtTmTs, rwsCloDtTmTs);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).rwsPutAwayQty, rwsPutAwayQty);
            pMsg.prchReqInfo.setValidCount(1);

            prchReqApi.execute(pMsg, onBatchType);

            for (NPZC103001PMsg msg : pMsgList) {

                if (S21ApiUtil.isXxMsgId(msg)) {

                    List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
                    throw new S21AbendException(msgList.get(0));
                }
            }
        }
    }

    /**
     * <pre>
     * Close Return CPO
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param bean NLZC208001Bean01
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void callNWZC153001(final S21ApiMessageMap msgMap, final NLZC208001Bean01 bean, RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final ONBATCH_TYPE onBatchType) {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        NWZC153001PMsg pMsg = new NWZC153001PMsg();
        List<NWZC153002PMsg> linePMsgList = new ArrayList<NWZC153002PMsg>();

        // Set Header Param
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_UPD_STS);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt.getValue());

        // Set Detail Param
        ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(0).xxRqstTpCd, NWZC153001Constant.RQST_DTL_RECEIVE);
        ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(0).dsCpoRtrnLineNum, bean.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(0).dsCpoRtrnLineSubNum, bean.getDsCpoRtrnLineSubNum());
        ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(0).rtrnQty, rwsPutAwayWrkT.rwsStkQty.getValue().multiply(BigDecimal.ONE.negate()));
        pMsg.ordRtrnDtlList.setValidCount(1);

        NWZC153001 cpoRtrnDtlApi = new NWZC153001();
        cpoRtrnDtlApi.execute(pMsg, linePMsgList, onBatchType);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);

            if (!msgList.isEmpty()) {

                for (String messageId : msgList) {

                    if (ZYPCommonFunc.hasValue(messageId)) {

                        if (messageId.endsWith("E")) {

                            throw new S21AbendException(messageId);

                        } else if (messageId.endsWith("W")) {

                            msgMap.addXxMsgId(messageId);
                        }
                    }
                }
            }
        }

        for (NWZC153002PMsg linePMsg : linePMsgList) {

            if (S21ApiUtil.isXxMsgId(linePMsg)) {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(linePMsg);

                if (!msgList.isEmpty()) {

                    for (String messageId : msgList) {

                        if (ZYPCommonFunc.hasValue(messageId)) {

                            if (messageId.endsWith("E")) {

                                throw new S21AbendException(messageId);

                            } else if (messageId.endsWith("W")) {

                                msgMap.addXxMsgId(messageId);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * <ul>
     * <li>Purchase Stock In</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC208001PMsg
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @return NLZC002001PMsg API parameter
     */
    private NLZC002001PMsg getPmsgDoDp(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, String sceOrdTpCd) {

        // Purchase Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());

        if (NLXSceConst.SCE_ORD_TP_CD_DN.equals(sceOrdTpCd)) {

            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.OFF_THE_BOOK_STOCK_IN);

        } else if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(sceOrdTpCd)) {

            ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.PURCHASE_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_DOM);
        }

        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, rwsPutAwayWrkT.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, rwsPutAwayWrkT.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, rwsPutAwayWrkT.invtyStkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, rwsPutAwayWrkT.rwsStkQty.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, bean.getSysSrcCd());
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, bean.getTrxOrdNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, bean.getTrxLineNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, bean.getRwsNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, bean.getRwsRefNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, bean.getRwsDtlLineNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdNum, bean.getPoOrdNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdDtlLineNum, bean.getPoOrdDtlLineNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.vndCd, bean.getFromLocCd());
        ZYPEZDItemValueSetter.setValue(inPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        // get Serial Number
        List<String> serNumList = getRwsSerNumList(param, bean);

        if (serNumList != null) {

            for (int i = 0; i < serNumList.size(); i++) {

                ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, serNumList.get(i));
            }

            inPMsg.serNumList.setValidCount(serNumList.size());
        }

        return inPMsg;
    }

    /**
     * <pre>
     * <ul>
     * <li>Movement Stock Out</li>
     * <li>Purchase Stock In</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC208001PMsg
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @return NLZC002001PMsg API parameter
     */
    private List<NLZC002001PMsg> getPmsgDg(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        // Movement Stock out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();

        outPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        outPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        outPMsg.trxCd.setValue(TRX.MOVEMENT);
        outPMsg.trxRsnCd.setValue(TRX_RSN.INBOUND_IN_TRANSIT_STOCK_OUT);
        outPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_DOM);
        outPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
        outPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        outPMsg.locStsCd.setValue(LOC_STS.IN_TRANSIT);
        outPMsg.stkStsCd.setValue(bean.getInvtyStkStsCd());
        outPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        outPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
        outPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
        outPMsg.poRcvNum.setValue(bean.getTrxOrdNum());
        outPMsg.poRcvLineNum.setValue(bean.getTrxLineNum());
        outPMsg.rwsNum.setValue(bean.getRwsNum());
        outPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
        outPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        outPMsg.poOrdNum.setValue(bean.getPoOrdNum());
        outPMsg.poOrdDtlLineNum.setValue(bean.getPoOrdDtlLineNum());
        outPMsg.invtyTrxSlpNum.setValue(bean.getDomInvNum());
        outPMsg.vndCd.setValue(bean.getFromLocCd());
        outPMsg.ccyCd.setValue(stdCcyCd);
        outPMsg.uomCd.setValue(PKG_UOM.EACH);

        pMsgList.add(outPMsg);

        // Purchase Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();

        inPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        inPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inPMsg.trxCd.setValue(TRX.PURCHASE_STOCK_IN);
        inPMsg.trxRsnCd.setValue(TRX_RSN.PURCHASE_STOCK_IN);
        inPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_DOM);
        inPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
        inPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        inPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        inPMsg.stkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
        inPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        inPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        inPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
        inPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
        inPMsg.poRcvNum.setValue(bean.getTrxOrdNum());
        inPMsg.poRcvLineNum.setValue(bean.getTrxLineNum());
        inPMsg.rwsNum.setValue(bean.getRwsNum());
        inPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
        inPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        inPMsg.poOrdNum.setValue(bean.getPoOrdNum());
        inPMsg.poOrdDtlLineNum.setValue(bean.getPoOrdDtlLineNum());
        inPMsg.vndCd.setValue(bean.getFromLocCd());
        inPMsg.ccyCd.setValue(stdCcyCd);
        inPMsg.uomCd.setValue(PKG_UOM.EACH);

        // get Serial Number
        List<String> serNumList = getRwsSerNumList(param, bean);

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
     * <li>W/H Transfer Stock Out</li>
     * <li>W/H Transfer Stock In</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC208001PMsg
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @return List<NLZC002001PMsg> API parameter
     */
    private List<NLZC002001PMsg> getPmsgDt(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        String outTrxRsnCd = TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT;
        String inTrxRsnCd = TRX_RSN.WAREHOUSE_TRANSFER_STOCK_IN;

        // Off Book
        if (!ZYPConstant.FLG_ON_Y.equals(bean.getToCmpyInvtyFlg())) {

            outTrxRsnCd = TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT;
            inTrxRsnCd = TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_STOCK_IN;

        // Asset
        } else if (INVTY_ACCT.ASSET.equals(bean.getToInvtyAcctCd())) {

            outTrxRsnCd = TRX_RSN.ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT;
            inTrxRsnCd = TRX_RSN.ASSET_WAREHOUSE_TRANSFER_STOCK_IN;

        // WH to Showroom
        } else if (!RTL_WH_CATG.SHOWROOM.equals(bean.getFromRtlWhCatgCd()) && RTL_WH_CATG.SHOWROOM.equals(bean.getToRtlWhCatgCd())) {

            outTrxRsnCd = TRX_RSN.SHOWROOM_TRANSFER_IN_TRANSIT_STOCK_OUT;
            inTrxRsnCd = TRX_RSN.SHOWROOM_TRANSFER_STOCK_IN;

        // Showroom to WH
        } else if (RTL_WH_CATG.SHOWROOM.equals(bean.getFromRtlWhCatgCd()) && !RTL_WH_CATG.SHOWROOM.equals(bean.getToRtlWhCatgCd())) {

            outTrxRsnCd = TRX_RSN.SHOWROOM_TRANSFER_IN_TRANSIT_STOCK_OUT;
            inTrxRsnCd = TRX_RSN.SHOWROOM_TRANSFER_STOCK_IN;
        }

        // W/H Transfer Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();

        outPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        outPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        outPMsg.trxCd.setValue(TRX.MOVEMENT);
        outPMsg.trxRsnCd.setValue(outTrxRsnCd);
        outPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_RCV_CONF);
        outPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
        outPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        outPMsg.locStsCd.setValue(LOC_STS.IN_TRANSIT_WH);
        outPMsg.stkStsCd.setValue(bean.getInvtyStkStsCd());
        outPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        outPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        outPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
        outPMsg.poRcvNum.setValue(bean.getTrxOrdNum());
        outPMsg.poRcvLineNum.setValue(bean.getTrxLineNum());
        outPMsg.rwsNum.setValue(bean.getRwsNum());
        outPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
        outPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        outPMsg.cpoOrdNum.setValue(bean.getCpoOrdNum());
        outPMsg.cpoDtlLineNum.setValue(bean.getCpoDtlLineNum());
        outPMsg.cpoDtlLineSubNum.setValue(bean.getCpoDtlLineSubNum());
        outPMsg.ccyCd.setValue(stdCcyCd);
        outPMsg.uomCd.setValue(PKG_UOM.EACH);
        outPMsg.shipFromLocCustCd.setValue(bean.getShipFromInvtyLocCd());

        pMsgList.add(outPMsg);

        // W/H Transfer Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();

        inPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        inPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inPMsg.trxCd.setValue(TRX.MOVEMENT);
        inPMsg.trxRsnCd.setValue(inTrxRsnCd);
        inPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_RCV_CONF);
        inPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
        inPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        inPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        inPMsg.stkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
        inPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        inPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        inPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        inPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
        inPMsg.poRcvNum.setValue(bean.getTrxOrdNum());
        inPMsg.poRcvLineNum.setValue(bean.getTrxLineNum());
        inPMsg.rwsNum.setValue(bean.getRwsNum());
        inPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
        inPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        inPMsg.cpoOrdNum.setValue(bean.getCpoOrdNum());
        inPMsg.cpoDtlLineNum.setValue(bean.getCpoDtlLineNum());
        inPMsg.cpoDtlLineSubNum.setValue(bean.getCpoDtlLineSubNum());
        inPMsg.ccyCd.setValue(stdCcyCd);
        inPMsg.uomCd.setValue(PKG_UOM.EACH);
        inPMsg.shipFromLocCustCd.setValue(bean.getShipFromInvtyLocCd());

        // get Serial Number
        List<String> serNumList = getRwsSerNumList(param, bean);

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
     * <li>W/H Transfer Stock Out</li>
     * <li>W/H Transfer Stock In</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC208001PMsg
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @return List<NLZC002001PMsg> API parameter
     */
    private List<NLZC002001PMsg> getPmsgTr(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        // W/H Transfer Stock Out
        NLZC002001PMsg outPMsg = new NLZC002001PMsg();

        outPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        outPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        outPMsg.trxCd.setValue(TRX.MOVEMENT);
        outPMsg.trxRsnCd.setValue(TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT);
        outPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_RCV_CONF);
        outPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
        outPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        outPMsg.locStsCd.setValue(LOC_STS.IN_TRANSIT_WH);
        outPMsg.stkStsCd.setValue(bean.getInvtyStkStsCd());
        outPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        outPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
        outPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
        outPMsg.poRcvNum.setValue(bean.getTrxOrdNum());
        outPMsg.poRcvLineNum.setValue(bean.getTrxLineNum());
        outPMsg.rwsNum.setValue(bean.getRwsNum());
        outPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
        outPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        outPMsg.invtyOrdNum.setValue(bean.getInvtyOrdNum());
        outPMsg.invtyOrdLineNum.setValue(bean.getInvtyOrdLineNum());
        outPMsg.ccyCd.setValue(stdCcyCd);
        outPMsg.uomCd.setValue(PKG_UOM.EACH);
        outPMsg.shipFromLocCustCd.setValue(bean.getShipFromInvtyLocCd());

        pMsgList.add(outPMsg);

        // W/H Transfer Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();

        inPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        inPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inPMsg.trxCd.setValue(TRX.MOVEMENT);
        inPMsg.trxRsnCd.setValue(TRX_RSN.WAREHOUSE_TRANSFER_STOCK_IN);
        inPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_RCV_CONF);
        inPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
        inPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        inPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        inPMsg.stkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
        inPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        inPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        inPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
        inPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
        inPMsg.poRcvNum.setValue(bean.getTrxOrdNum());
        inPMsg.poRcvLineNum.setValue(bean.getTrxLineNum());
        inPMsg.rwsNum.setValue(bean.getRwsNum());
        inPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
        inPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        inPMsg.invtyOrdNum.setValue(bean.getInvtyOrdNum());
        inPMsg.invtyOrdLineNum.setValue(bean.getInvtyOrdLineNum());
        inPMsg.ccyCd.setValue(stdCcyCd);
        inPMsg.uomCd.setValue(PKG_UOM.EACH);
        inPMsg.shipFromLocCustCd.setValue(bean.getShipFromInvtyLocCd());

        // get Serial Number
        List<String> serNumList = getRwsSerNumList(param, bean);

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
     * <li>Return Stock In. Mod QC#54864</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC208001PMsg
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @return List<NLZC002001PMsg> API parameter
     */
    private List<NLZC002001PMsg> getPmsgRt(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        String trxCd = bean.getTrxCd();
        String trxRsnCd = bean.getTrxRsnCd();

        // QC#63527 2024/03/14 Start
        if ((TRX.MOVEMENT.equals(trxCd) && TRX_RSN.LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd))
                || (TRX.MOVEMENT.equals(trxCd) && TRX_RSN.OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd))) {
            String loanWH = null;
            if (TRX_RSN.LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd)) {
                loanWH = ZYPCodeDataUtil.getVarCharConstValue("LOAN_DUMMY_CSA_WH_CD", param.glblCmpyCd.getValue());
            } else {
                loanWH = ZYPCodeDataUtil.getVarCharConstValue("LOAN_DUMMY_GMD_WH_CD", param.glblCmpyCd.getValue());
            }

            String fromLoanInvtyLocCd = null;
            // QC#63527 2024/03/14 End

            // QC#54864 Start
            // QC#63527 2024/03/14 Start
            if (TRX_RSN.LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd)) {
                fromLoanInvtyLocCd = loanWH + getLoanWh(param.glblCmpyCd.getValue(), trxCd, TRX_RSN.LOAN_TO_INVENTORY_STOCK_OUT, loanWH, bean.getOrigCpoOrdNum(), bean.getOrigCpoDtlLineNum(), bean.getOrigCpoDtlLineSubNum());
            } else {
                fromLoanInvtyLocCd = loanWH + getLoanWh(param.glblCmpyCd.getValue(), trxCd, TRX_RSN.OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_OUT, loanWH, bean.getOrigCpoOrdNum(), bean.getOrigCpoDtlLineNum(), bean.getOrigCpoDtlLineSubNum());
            }
            // QC#63527 2024/03/14 End

            String fromSWH = getSWH(param.glblCmpyCd.getValue(), fromLoanInvtyLocCd);
            String toWH = getWH(param.glblCmpyCd.getValue(), rwsPutAwayWrkT.whCd.getValue());
            String toSWH = getSWH(param.glblCmpyCd.getValue(), rwsPutAwayWrkT.whCd.getValue());

            boolean isSWHChange = false;
            if (ZYPCommonFunc.hasValue(fromSWH) && ZYPCommonFunc.hasValue(toSWH) && !fromSWH.equals(toSWH)) {
                isSWHChange = true;
            }
            // QC#54864 End
            // Loan to Inventory Stock Out
            NLZC002001PMsg outPMsg = new NLZC002001PMsg();

            outPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            outPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            outPMsg.trxCd.setValue(trxCd);
            // QC#63527 2024/03/14 Start
            if (TRX_RSN.LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd)) {
                outPMsg.trxRsnCd.setValue(TRX_RSN.LOAN_TO_INVENTORY_STOCK_OUT);
            } else if (TRX_RSN.OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd)) {
                outPMsg.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_OUT);
            }
            // QC#63527 2024/03/14 End
            outPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_TO_INVTY);
            outPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
            // QC#54864
//            outPMsg.invtyLocCd.setValue(ZYPCodeDataUtil.getVarCharConstValue(LOAN_DUMMY_WH_CD, param.glblCmpyCd.getValue()));
            outPMsg.invtyLocCd.setValue(loanWH);
            outPMsg.locStsCd.setValue(LOC_STS.TRIAL_USE);
            outPMsg.stkStsCd.setValue(bean.getInvtyStkStsCd());
            outPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
            outPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
            outPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            outPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
            outPMsg.rwsNum.setValue(bean.getRwsNum());
            outPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
            outPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
            outPMsg.cpoOrdNum.setValue(bean.getCpoOrdNum());
            outPMsg.cpoDtlLineNum.setValue(bean.getCpoDtlLineNum());
            outPMsg.cpoDtlLineSubNum.setValue(bean.getCpoDtlLineSubNum());
            outPMsg.rmaNum.setValue(bean.getCpoOrdNum());
            outPMsg.rmaLineNum.setValue(bean.getCpoDtlLineNum());
            outPMsg.rmaLineSubNum.setValue(bean.getCpoDtlLineSubNum());
            outPMsg.origCpoOrdNum.setValue(bean.getOrigCpoOrdNum());
            outPMsg.origCpoDtlLineNum.setValue(bean.getOrigCpoDtlLineNum()); // QC7355 // QC#7606
            outPMsg.origCpoDtlLineSubNum.setValue(bean.getOrigCpoDtlLineSubNum()); // QC7355 // QC#7606
            outPMsg.sellToCustCd.setValue(bean.getSellToCustCd());
            outPMsg.billToCustCd.setValue(bean.getBillToCustLocCd());
            outPMsg.shipToCustCd.setValue(bean.getFromLocCd());
            // QC#54864
            if (isSWHChange) {
                outPMsg.shipToLocCustCd.setValue(toWH + fromSWH);
            } else {
                outPMsg.shipToLocCustCd.setValue(rwsPutAwayWrkT.whCd.getValue());
            }
            outPMsg.ccyCd.setValue(stdCcyCd);
            outPMsg.uomCd.setValue(PKG_UOM.EACH);

            // calculate ship cost amount from original loan order
            BigDecimal shipCostAmt = getShipCostAmt(param, rwsPutAwayWrkT, bean);
            outPMsg.shipCostAmt.setValue(shipCostAmt);

            List<String> serNumList = getRwsSerNumList(param, bean);

            if (serNumList != null) {

                for (int i = 0; i < serNumList.size(); i++) {

                    ZYPEZDItemValueSetter.setValue(outPMsg.serNumList.no(i).serNum, serNumList.get(i));
                }

                outPMsg.serNumList.setValidCount(serNumList.size());
            }

            pMsgList.add(outPMsg);

            // Loan to Inventory Stock In
            NLZC002001PMsg inPMsg = new NLZC002001PMsg();

            inPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            inPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            inPMsg.trxCd.setValue(trxCd);
            inPMsg.trxRsnCd.setValue(trxRsnCd);
            inPMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_TO_INVTY);
            inPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
            // QC#54864
//            inPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
            inPMsg.invtyLocCd.setValue(toWH + fromSWH);
            inPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
            inPMsg.stkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
            inPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
            inPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
            inPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            inPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
            inPMsg.rwsNum.setValue(bean.getRwsNum());
            inPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
            inPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
            inPMsg.cpoOrdNum.setValue(bean.getCpoOrdNum());
            inPMsg.cpoDtlLineNum.setValue(bean.getCpoDtlLineNum());
            inPMsg.cpoDtlLineSubNum.setValue(bean.getCpoDtlLineSubNum());
            inPMsg.rmaNum.setValue(bean.getCpoOrdNum());
            inPMsg.rmaLineNum.setValue(bean.getCpoDtlLineNum());
            inPMsg.rmaLineSubNum.setValue(bean.getCpoDtlLineSubNum());
            inPMsg.origCpoOrdNum.setValue(bean.getOrigCpoOrdNum());
            inPMsg.origCpoDtlLineNum.setValue(bean.getOrigCpoDtlLineNum()); // QC7355 // QC#7606
            inPMsg.origCpoDtlLineSubNum.setValue(bean.getOrigCpoDtlLineSubNum()); // QC7355 // QC#7606
            inPMsg.sellToCustCd.setValue(bean.getSellToCustCd());
            inPMsg.billToCustCd.setValue(bean.getBillToCustLocCd());
            inPMsg.shipToCustCd.setValue(bean.getFromLocCd());
            // QC#54864
            if (isSWHChange) {
                inPMsg.shipToLocCustCd.setValue(toWH + fromSWH);
            } else {
                inPMsg.shipToLocCustCd.setValue(rwsPutAwayWrkT.whCd.getValue());
            }
            inPMsg.ccyCd.setValue(stdCcyCd);
            inPMsg.uomCd.setValue(PKG_UOM.EACH);

            if (serNumList != null) {

                for (int i = 0; i < serNumList.size(); i++) {

                    ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, serNumList.get(i));
                }

                inPMsg.serNumList.setValidCount(serNumList.size());
            }

            pMsgList.add(inPMsg);
            // QC#54864 Start
            if (isSWHChange) {
                BigDecimal configPK = setSvcConfigMstrPkWithRWS(param.glblCmpyCd.getValue(), inPMsg.mdseCd.getValue(), inPMsg.rwsNum.getValue());
                // SWH Change
                NLZC002001PMsg swcOutPMsg = new NLZC002001PMsg();
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.mdseCd, bean.getMdseCd());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.invtyLocCd, toWH + fromSWH);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.locStsCd, LOC_STS.DC_STOCK);
                // QC#57462 Mod Start
//                ZYPEZDItemValueSetter.setValue(swcOutPMsg.stkStsCd, bean.getInvtyStkStsCd());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.stkStsCd, rwsPutAwayWrkT.invtyStkStsCd.getValue());
                // QC#57462 Mod End
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.xxRqstQty, rwsPutAwayWrkT.rwsStkQty.getValue());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.invtyTrxDt, param.slsDt);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.sysSrcCd, bean.getSysSrcCd());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.ccyCd, stdCcyCd);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.uomCd, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.trxCd, TRX.INVENTORY_VALUATION);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.trxRsnCd, TRX_RSN.SWH_TRANSFER_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.rwsNum, bean.getRwsNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.rwsRefNum, bean.getRwsRefNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.rwsDtlLineNum, bean.getRwsDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.cpoOrdNum, bean.getCpoOrdNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.cpoDtlLineNum, bean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.cpoDtlLineSubNum, bean.getCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.rmaNum, bean.getCpoOrdNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.rmaLineNum, bean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.rmaLineSubNum, bean.getCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.origCpoOrdNum, bean.getOrigCpoOrdNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.origCpoDtlLineNum, bean.getOrigCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.origCpoDtlLineSubNum, bean.getOrigCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.sellToCustCd, bean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.billToCustCd, bean.getBillToCustLocCd());;
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.shipToCustCd, rwsPutAwayWrkT.whCd.getValue());
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.trialLoanMstrPk, configPK);
                ZYPEZDItemValueSetter.setValue(swcOutPMsg.shipToLocCustCd, rwsPutAwayWrkT.whCd.getValue());
                
                if (serNumList != null) {

                    for (int i = 0; i < serNumList.size(); i++) {

                        ZYPEZDItemValueSetter.setValue(swcOutPMsg.serNumList.no(i).serNum, serNumList.get(i));
                    }

                    swcOutPMsg.serNumList.setValidCount(serNumList.size());
                }

                pMsgList.add(swcOutPMsg);

                NLZC002001PMsg swcInPMsg = new NLZC002001PMsg();
                ZYPEZDItemValueSetter.setValue(swcInPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.mdseCd, bean.getMdseCd());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.invtyLocCd, rwsPutAwayWrkT.whCd.getValue());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.locStsCd, LOC_STS.DC_STOCK);
                // QC#57462 Mod Start
                // ZYPEZDItemValueSetter.setValue(swcOutPMsg.stkStsCd,bean.getInvtyStkStsCd());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.stkStsCd, rwsPutAwayWrkT.invtyStkStsCd.getValue());
                // QC#57462 Mod End
                ZYPEZDItemValueSetter.setValue(swcInPMsg.xxRqstQty, rwsPutAwayWrkT.rwsStkQty.getValue());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.invtyTrxDt, param.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.sysSrcCd, bean.getSysSrcCd());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.ccyCd, stdCcyCd);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.uomCd, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.trxCd, TRX.INVENTORY_VALUATION);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.trxRsnCd, TRX_RSN.SWH_TRANSFER_STOCK_IN);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.rwsNum, bean.getRwsNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.rwsRefNum, bean.getRwsRefNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.rwsDtlLineNum, bean.getRwsDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.cpoOrdNum, bean.getCpoOrdNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.cpoDtlLineNum, bean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.cpoDtlLineSubNum, bean.getCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.rmaNum, bean.getCpoOrdNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.rmaLineNum, bean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.rmaLineSubNum, bean.getCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.origCpoOrdNum, bean.getOrigCpoOrdNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.origCpoDtlLineNum, bean.getOrigCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.origCpoDtlLineSubNum, bean.getOrigCpoDtlLineSubNum());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.sellToCustCd, bean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.billToCustCd, bean.getBillToCustLocCd());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.shipToCustCd, rwsPutAwayWrkT.whCd.getValue());
                ZYPEZDItemValueSetter.setValue(swcInPMsg.shipFromLocCustCd, toWH + fromSWH);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.trialLoanMstrPk, configPK);
                ZYPEZDItemValueSetter.setValue(swcInPMsg.shipToLocCustCd, rwsPutAwayWrkT.whCd.getValue());

                if (serNumList != null) {

                    for (int i = 0; i < serNumList.size(); i++) {

                        ZYPEZDItemValueSetter.setValue(swcInPMsg.serNumList.no(i).serNum, serNumList.get(i));
                    }

                    swcInPMsg.serNumList.setValidCount(serNumList.size());
                }

                pMsgList.add(swcInPMsg);
            }
            // QC#54864 End

        } else {

            // Return Stock In
            NLZC002001PMsg inPMsg = new NLZC002001PMsg();

            inPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            inPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            inPMsg.trxCd.setValue(trxCd);
            inPMsg.trxRsnCd.setValue(trxRsnCd);
            inPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
            inPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
            inPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
            inPMsg.stkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
            inPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
            inPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
            inPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            inPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
            inPMsg.rwsNum.setValue(bean.getRwsNum());
            inPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
            inPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
            inPMsg.cpoOrdNum.setValue(bean.getCpoOrdNum());
            inPMsg.cpoDtlLineNum.setValue(bean.getCpoDtlLineNum());
            inPMsg.cpoDtlLineSubNum.setValue(bean.getCpoDtlLineSubNum());
            inPMsg.rmaNum.setValue(bean.getCpoOrdNum());
            inPMsg.rmaLineNum.setValue(bean.getCpoDtlLineNum());
            inPMsg.rmaLineSubNum.setValue(bean.getCpoDtlLineSubNum());
            inPMsg.sellToCustCd.setValue(bean.getSellToCustCd());
            inPMsg.billToCustCd.setValue(bean.getBillToCustLocCd());
            inPMsg.shipToCustCd.setValue(bean.getFromLocCd());
            inPMsg.ccyCd.setValue(stdCcyCd);
            inPMsg.uomCd.setValue(PKG_UOM.EACH);

            // get Serial Number
            List<String> serNumList = getRwsSerNumList(param, bean);

            if (serNumList != null) {

                for (int i = 0; i < serNumList.size(); i++) {

                    ZYPEZDItemValueSetter.setValue(inPMsg.serNumList.no(i).serNum, serNumList.get(i));
                }

                inPMsg.serNumList.setValidCount(serNumList.size());

            }

            pMsgList.add(inPMsg);

        }

        return pMsgList;
    }

    /**
     * <pre>
     * <ul>
     * <li>Parts Usage Return for Reman Stock In</li>
     * </ul>
     * </pre>
     * 
     * @param param NLZC208001PMsg
     * @param rwsPutAwayWrkT RWS_PUT_AWAY_WRKTMsg
     * @param bean NLZC208001Bean01
     * @return List<NLZC002001PMsg> API parameter
     */
    private List<NLZC002001PMsg> getPmsgRu(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        // Parts Usage Return for Reman Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();

        inPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        inPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inPMsg.trxCd.setValue(TRX.REMANUFACTURING);
        inPMsg.trxRsnCd.setValue(TRX_RSN.PARTS_USAGE_RETURN_FOR_REMAN);
        inPMsg.mdseCd.setValue(rwsPutAwayWrkT.mdseCd.getValue());
        inPMsg.invtyLocCd.setValue(rwsPutAwayWrkT.whCd.getValue());
        inPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        inPMsg.stkStsCd.setValue(rwsPutAwayWrkT.invtyStkStsCd.getValue());
        inPMsg.xxRqstQty.setValue(rwsPutAwayWrkT.rwsStkQty.getValue());
        inPMsg.invtyTrxDt.setValue(param.slsDt.getValue());
        inPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OTBD);
        inPMsg.sysSrcCd.setValue(bean.getSysSrcCd());
        inPMsg.poRcvNum.setValue(bean.getTrxOrdNum());
        inPMsg.poRcvLineNum.setValue(bean.getTrxLineNum());
        inPMsg.rwsNum.setValue(bean.getRwsNum());
        inPMsg.rwsRefNum.setValue(bean.getRwsRefNum());
        inPMsg.rwsDtlLineNum.setValue(bean.getRwsDtlLineNum());
        inPMsg.invtyTrxSlpNum.setValue(bean.getPoRcvTrxHdrNum());
        inPMsg.shipFromLocCustCd.setValue(bean.getShipFromInvtyLocCd());
        inPMsg.ccyCd.setValue(stdCcyCd);
        inPMsg.uomCd.setValue(PKG_UOM.EACH);

        // get Serial Number
        List<String> serNumList = getRwsSerNumList(param, bean);

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

                    throw new S21AbendException(messageId);

                } else if (messageId.endsWith("W")) {

                    msgMap.addXxMsgId(messageId);
                }
            }
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param sceOrdTpCd SCE_ORD_TP_CD
     * @return NLZC208001Bean01 Bean
     * @throws SQLException SQLException
     */
    private NLZC208001Bean01 setBean(ResultSet rs, String sceOrdTpCd) throws SQLException {

        NLZC208001Bean01 bean = new NLZC208001Bean01();

        bean.setRwsNum(NLXC014001.nullToEmpty(rs.getString(RWS_NUM)));
        bean.setRwsDtlLineNum(NLXC014001.nullToEmpty(rs.getString(RWS_DTL_LINE_NUM)));
        bean.setImptInvPk(rs.getBigDecimal(IMPT_INV_PK));
        bean.setImptPackSlpStsPk(rs.getBigDecimal(IMPT_PACK_SLP_STS_PK));
        bean.setImptInvNum(NLXC014001.nullToEmpty(rs.getString(IMPT_INV_NUM)));
        bean.setImptInvClsCd(NLXC014001.nullToEmpty(rs.getString(IMPT_INV_CLS_CD)));
        bean.setImptInvVerNum(NLXC014001.nullToEmpty(rs.getString(IMPT_INV_VER_NUM)));
        bean.setImptInvSlpClsCd(NLXC014001.nullToEmpty(rs.getString(IMPT_INV_SLP_CLS_CD)));
        bean.setImptInvDoNum(NLXC014001.nullToEmpty(rs.getString(IMPT_INV_DO_NUM)));
        bean.setImptDoClsCd(NLXC014001.nullToEmpty(rs.getString(IMPT_DO_CLS_CD)));
        bean.setImptPackSlpNum(NLXC014001.nullToEmpty(rs.getString(IMPT_PACK_SLP_NUM)));
        bean.setImptPackSlpLineNum(NLXC014001.nullToEmpty(rs.getString(IMPT_PACK_SLP_LINE_NUM)));
        bean.setImptPackSlpDtlLineNum(NLXC014001.nullToEmpty(rs.getString(IMPT_PACK_SLP_DTL_LINE_NUM)));
        bean.setInvtyStkStsCd(NLXC014001.nullToEmpty(rs.getString(INVTY_STK_STS_CD)));
        bean.setRwsQty(NLXC014001.nullToZero(rs.getBigDecimal(RWS_QTY)));
        bean.setRwsPutAwayQty(NLXC014001.nullToZero(rs.getBigDecimal(RWS_PUT_AWAY_QTY)));
        bean.setMdseCd(NLXC014001.nullToEmpty(rs.getString(MDSE_CD)));
        bean.setTrxLineNum(NLXC014001.nullToEmpty(rs.getString(TRX_LINE_NUM)));
        bean.setDtlRwsStsCd(NLXC014001.nullToEmpty(rs.getString(DTL_RWS_STS_CD)));
        bean.setHdrRwsStsCd(NLXC014001.nullToEmpty(rs.getString(HDR_RWS_STS_CD)));
        bean.setSceOrdTpCd(NLXC014001.nullToEmpty(rs.getString(SCE_ORD_TP_CD)));
        bean.setRwsRefNum(NLXC014001.nullToEmpty(rs.getString(RWS_REF_NUM)));
        bean.setTrxOrdNum(NLXC014001.nullToEmpty(rs.getString(TRX_ORD_NUM)));
        bean.setSysSrcCd(NLXC014001.nullToEmpty(rs.getString(SYS_SRC_CD)));
        bean.setImptCntnrLotNum(NLXC014001.nullToEmpty(rs.getString(IMPT_CNTNR_LOT_NUM)));
        bean.setImptCntnrNum(NLXC014001.nullToEmpty(rs.getString(IMPT_CNTNR_NUM)));
        bean.setFromLocCd(NLXC014001.nullToEmpty(rs.getString(FROM_LOC_CD)));
        bean.setLocNm(NLXC014001.nullToEmpty(rs.getString(LOC_NM)));

        if (NLXSceConst.SCE_ORD_TP_CD_DN.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_DP.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_DG.equals(sceOrdTpCd)) {

            bean.setPoOrdNum(NLXC014001.nullToEmpty(rs.getString(PO_ORD_NUM)));
            bean.setPoOrdDtlLineNum(NLXC014001.nullToEmpty(rs.getString(PO_ORD_DTL_LINE_NUM)));
            bean.setCustPoNum(NLXC014001.nullToEmpty(rs.getString(CUST_PO_NUM)));
            bean.setDomInvNum(NLXC014001.nullToEmpty(rs.getString(DOM_INV_NUM)));
            bean.setPrchReqNum(NLXC014001.nullToEmpty(rs.getString(PRCH_REQ_NUM)));
            bean.setPrchReqLineNum(NLXC014001.nullToEmpty(rs.getString(PRCH_REQ_LINE_NUM)));
            bean.setPrchReqLineSubNum(rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));

        } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(sceOrdTpCd)) {

            bean.setCpoOrdNum(NLXC014001.nullToEmpty(rs.getString(CPO_ORD_NUM)));
            bean.setCpoDtlLineNum(NLXC014001.nullToEmpty(rs.getString(CPO_DTL_LINE_NUM)));
            bean.setCpoDtlLineSubNum(NLXC014001.nullToEmpty(rs.getString(CPO_DTL_LINE_SUB_NUM)));
            bean.setCustPoNum(NLXC014001.nullToEmpty(rs.getString(CUST_PO_NUM)));
            bean.setSoNum(NLXC014001.nullToEmpty(rs.getString(SO_NUM)));
            bean.setTrxCd(NLXC014001.nullToEmpty(rs.getString(TRX_CD)));
            bean.setTrxRsnCd(NLXC014001.nullToEmpty(rs.getString(TRX_RSN_CD)));
            bean.setShipFromInvtyLocCd(NLXC014001.nullToEmpty(rs.getString(SHIP_FROM_INVTY_LOC_CD)));
            bean.setPrchReqNum(NLXC014001.nullToEmpty(rs.getString(PRCH_REQ_NUM)));
            bean.setPrchReqLineNum(NLXC014001.nullToEmpty(rs.getString(PRCH_REQ_LINE_NUM)));
            bean.setPrchReqLineSubNum(rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
            bean.setFromInvtyAcctCd(NLXC014001.nullToEmpty(rs.getString(FROM_INVTY_ACCT_CD)));
            bean.setFromRtlWhCatgCd(NLXC014001.nullToEmpty(rs.getString(FROM_RTL_WH_CATG_CD)));
            bean.setFromCmpyInvtyFlg(NLXC014001.nullToEmpty(rs.getString(FROM_CMPY_INVTY_FLG)));
            bean.setToInvtyAcctCd(NLXC014001.nullToEmpty(rs.getString(TO_INVTY_ACCT_CD)));
            bean.setToRtlWhCatgCd(NLXC014001.nullToEmpty(rs.getString(TO_RTL_WH_CATG_CD)));
            bean.setToCmpyInvtyFlg(NLXC014001.nullToEmpty(rs.getString(TO_CMPY_INVTY_FLG)));

        } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(sceOrdTpCd)) {

            bean.setInvtyOrdNum(NLXC014001.nullToEmpty(rs.getString(INVTY_ORD_NUM)));
            bean.setInvtyOrdLineNum(NLXC014001.nullToEmpty(rs.getString(INVTY_ORD_LINE_NUM)));
            bean.setCustPoNum(NLXC014001.nullToEmpty(rs.getString(CUST_PO_NUM)));
            bean.setSoNum(NLXC014001.nullToEmpty(rs.getString(SO_NUM)));
            bean.setShipFromInvtyLocCd(NLXC014001.nullToEmpty(rs.getString(SHIP_FROM_INVTY_LOC_CD)));
            bean.setPrchReqNum(NLXC014001.nullToEmpty(rs.getString(PRCH_REQ_NUM)));
            bean.setPrchReqLineNum(NLXC014001.nullToEmpty(rs.getString(PRCH_REQ_LINE_NUM)));
            bean.setPrchReqLineSubNum(rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));

        } else if (NLXSceConst.SCE_ORD_TP_CD_RT.equals(sceOrdTpCd)) {

            bean.setCpoOrdNum(NLXC014001.nullToEmpty(rs.getString(CPO_ORD_NUM)));
            bean.setCpoDtlLineNum(NLXC014001.nullToEmpty(rs.getString(DS_CPO_RTRN_LINE_NUM)));
            bean.setCpoDtlLineSubNum(NLXC014001.nullToEmpty(rs.getString(DS_CPO_RTRN_LINE_SUB_NUM)));
            bean.setDsCpoRtrnLineNum(NLXC014001.nullToEmpty(rs.getString(DS_CPO_RTRN_LINE_NUM)));
            bean.setDsCpoRtrnLineSubNum(NLXC014001.nullToEmpty(rs.getString(DS_CPO_RTRN_LINE_SUB_NUM)));
            bean.setTrxCd(NLXC014001.nullToEmpty(rs.getString(TRX_CD)));
            bean.setTrxRsnCd(NLXC014001.nullToEmpty(rs.getString(TRX_RSN_CD)));
            bean.setOrigCpoOrdNum(NLXC014001.nullToEmpty(rs.getString(ORIG_CPO_ORD_NUM)));
            bean.setRefCpoDtlLineNum(NLXC014001.nullToEmpty(rs.getString(REF_CPO_DTL_LINE_NUM)));
            bean.setRefCpoDtlLineSubNum(NLXC014001.nullToEmpty(rs.getString(REF_CPO_DTL_LINE_SUB_NUM)));
            // QC#7355
            bean.setOrdSrcRefLineNum(NLXC014001.nullToEmpty(rs.getString(ORD_SRC_REF_LINE_NUM)));
            bean.setOrdSrcRefLineSubNum(NLXC014001.nullToEmpty(rs.getString(ORD_SRC_REF_LINE_SUB_NUM)));
            bean.setSellToCustCd(NLXC014001.nullToEmpty(rs.getString(SELL_TO_CUST_CD)));
            bean.setBillToCustLocCd(NLXC014001.nullToEmpty(rs.getString(BILL_TO_CUST_LOC_CD)));
            // QC#7606
            bean.setOrigCpoDtlLineNum(NLXC014001.nullToEmpty(rs.getString(ORIG_CPO_DTL_LINE_NUM)));
            bean.setOrigCpoDtlLineSubNum(NLXC014001.nullToEmpty(rs.getString(ORIG_CPO_DTL_LINE_SUB_NUM)));

        } else if (NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

            bean.setCustPoNum(NLXC014001.nullToEmpty(rs.getString(CUST_PO_NUM)));
            bean.setPoRcvTrxHdrNum(NLXC014001.nullToEmpty(rs.getString(PO_RCV_TRX_HDR_NUM)));
            bean.setShipFromInvtyLocCd(NLXC014001.nullToEmpty(rs.getString(SHIP_FROM_INVTY_LOC_CD)));
        // QC#55313
        } else if (NLXSceConst.SCE_ORD_TP_CD_RP.equals(sceOrdTpCd)) {
            bean.setVndCd(NLXC014001.nullToEmpty(rs.getString(VND_CD)));
            bean.setPoOrdNum(NLXC014001.nullToEmpty(rs.getString(PO_ORD_NUM)));
            bean.setPoOrdDtlLineNum(NLXC014001.nullToEmpty(rs.getString(PO_ORD_DTL_LINE_NUM)));
        }

        return bean;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void addErrLogMsg(final S21ApiMessageMap msgMap, final String errMsgId, final String logMsgId, final String[] param, final ONBATCH_TYPE onBatchType) {

        msgMap.addXxMsgId(errMsgId);
        if (!dataErrFlg) {
            NLZC208001PMsg pMsg = (NLZC208001PMsg) msgMap.getPmsg();
            String[] params = new String[] {RWS_PUT_AWAY_WRK, pMsg.glblCmpyCd.getValue(), pMsg.wrkTrxId.getValue(), pMsg.sqId.getValue() };
            NLXC025001.outputLog(1, NLAM1207E, params, onBatchType, this);
            dataErrFlg = true;
        }

        NLXC025001.outputLog(1, logMsgId, param, onBatchType, this);
    }

    /**
     * <pre>
     * </pre>
     * 
     */
    private void checkSelectKeyValue(String value, String tableName, String keyName) {

        if (!ZYPCommonFunc.hasValue(value)) {
            throw new S21AbendException(NLAM1161E, new String[] {tableName, keyName });
        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param rwsNum
     */
    private void setFirstStkInDt(String glblCmpyCd, String rwsNum) {

        RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
        rwsHdrT.glblCmpyCd.setValue(glblCmpyCd);
        rwsHdrT.rwsNum.setValue(rwsNum);
        rwsHdrT = (RWS_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(rwsHdrT);

        if (ZYPCommonFunc.hasValue(rwsHdrT.firstStkInDt)) {
            return;
        }

        rwsHdrT.firstStkInDt.setValue(sysDt);
        S21FastTBLAccessor.update(rwsHdrT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
            String[] list = new String[] {rwsHdrT.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM),
                    NLXCMsgHelper.toListedString(rwsHdrT.glblCmpyCd.getValue(), rwsHdrT.rwsNum.getValue()) };
            throw new S21AbendException(NLAM1134E, list);
        }
    }

    /**
     * <pre>
     * Get Shipped Cost Amount for Loan to Inventory
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private BigDecimal getShipCostAmt(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {

        // find location Code from CPO_DTL
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();

        cpoDtlTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        cpoDtlTMsg.cpoOrdNum.setValue(bean.getOrigCpoOrdNum());
        cpoDtlTMsg.cpoDtlLineNum.setValue(bean.getOrigCpoDtlLineNum()); // QC#7606
        cpoDtlTMsg.cpoDtlLineSubNum.setValue(bean.getOrigCpoDtlLineSubNum()); // QC#7606

        cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);

        // Calculate by Inventory Valuation
        NLXC001001GetInventoryItemCostBean getInventoryItemCostBean = new NLXC001001GetInventoryItemCostBean();

        getInventoryItemCostBean.setGlblCmpyCd(param.glblCmpyCd.getValue());
        getInventoryItemCostBean.setMdseCd(rwsPutAwayWrkT.mdseCd.getValue());
        getInventoryItemCostBean.setInvtyLocCd(cpoDtlTMsg.invtyLocCd.getValue());
        getInventoryItemCostBean.setQty(rwsPutAwayWrkT.rwsStkQty.getValue());

        NLXC001001GetInventoryItemCost.getInventoryItemCost(getInventoryItemCostBean);

        // Set Parameter.
        BigDecimal shipCostAmt = NLXC014001.nullToZero(getInventoryItemCostBean.getTotPrcAmt());

        return shipCostAmt;
    }

    //Start QC2318
    /**
     * Get Sales Date Time stamp
     * @return  yyyymmdd + hhmmss
     */
    private String getSalesDateTm() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }
    //End QC2318

    /**
     * Get RWS Serial number List
     * @param param NLZC208001PMsg
     * @param bean NLZC208001Bean01
     * @return List<String>
     */
    private List<String> getRwsSerNumList(NLZC208001PMsg param, NLZC208001Bean01 bean) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("wrkTrxId", param.wrkTrxId.getValue());
        queryParam.put("sqId", param.sqId.getValue());
        queryParam.put("rwsNum", bean.getRwsNum());
        queryParam.put("rwsDtlLineNum", bean.getRwsDtlLineNum());

        List<String> rwsSerNumList = (List<String>) ssmBatchClient.queryObjectList("getRwsSerNumList", queryParam);

        if (rwsSerNumList == null || rwsSerNumList.isEmpty()) {

            return null;
        }

        return rwsSerNumList;
    }

    // QC#55313 Add
    private void closeRp(final S21ApiMessageMap msgMap, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean, final ONBATCH_TYPE onBatchType) throws SQLException {

        NLZC208001PMsg param = (NLZC208001PMsg) msgMap.getPmsg();

        // Get Original Item Info
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", rwsPutAwayWrkT.glblCmpyCd.getValue());
        queryParam.put("rwsNum", rwsPutAwayWrkT.rwsNum.getValue());
        queryParam.put("rwsDtlLineNum", rwsPutAwayWrkT.rwsDtlLineNum.getValue());

        List<Map<String, Object>> shipMdseRpInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShipMdseRpInfoList", queryParam);

        if (shipMdseRpInfoList != null && !shipMdseRpInfoList.isEmpty()) {
            // Call Inventory Update API (NLZC0020) for Original Item
            List<NLZC002001PMsg> pMsgStkOutList = getPmsgRpForStkOut(param, shipMdseRpInfoList, bean, rwsPutAwayWrkT.rwsStkQty.getValue());

            NLZC002001 stkOutApi = new NLZC002001();
            stkOutApi.execute(pMsgStkOutList, onBatchType);

            for (NLZC002001PMsg msg : pMsgStkOutList) {

                checkApiErr(msgMap, msg);
            }
        }

        // call Inventory Update API (NLZC0020)
        List<NLZC002001PMsg> pMsgList = getPmsgRp(param, rwsPutAwayWrkT, bean);

        NLZC002001 api = new NLZC002001();
        api.execute(pMsgList, onBatchType);

        for (NLZC002001PMsg msg : pMsgList) {
            checkApiErr(msgMap, msg);
        }

        // close PO_RCV_DTL
        closePoDetail(msgMap, rwsPutAwayWrkT, bean);

        // call PO Status Update API (NPZC0040)
        callNPZC004001(msgMap , param.glblCmpyCd.getValue(), bean.getPoOrdNum(), bean.getPoOrdDtlLineNum(),
                rwsPutAwayWrkT.mdseCd.getValue(), rwsPutAwayWrkT.rwsStkQty.getValue(), onBatchType, false);

        BigDecimal remainingQty=bean.getRwsQty().subtract(bean.getRwsPutAwayQty());
        BigDecimal rwsStkQty=rwsPutAwayWrkT.rwsStkQty.getValue();
        String rwsCloDtTmTs=null;
        if(remainingQty.compareTo(rwsStkQty)==0) {
            rwsCloDtTmTs=rwsPutAwayWrkT.rwsStkDtTmTs.getValue();
        }
        // call Purchase Requisition Update API (NPZC1030)
        callNPZC103001(param.glblCmpyCd.getValue(), bean, rwsCloDtTmTs, rwsStkQty, onBatchType);

        // update Inbound Visibility
        updateInbdVisDo(msgMap, rwsPutAwayWrkT, bean);

        // close PO_RCV_HDR
        boolean closeFlg = closePoHeader(msgMap, bean);

        if (closeFlg) {
            callNPZC004001(msgMap , param.glblCmpyCd.getValue(), bean.getPoOrdNum(), null, null, null, onBatchType, true);
        }
    }

    private List<NLZC002001PMsg> getPmsgRp(final NLZC208001PMsg param, final RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, final NLZC208001Bean01 bean) {
        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        // Purchase Stock In
        NLZC002001PMsg inPMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.trxCd, TRX.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(inPMsg.mdseCd, rwsPutAwayWrkT.mdseCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyLocCd, rwsPutAwayWrkT.whCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inPMsg.stkStsCd, rwsPutAwayWrkT.invtyStkStsCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.xxRqstQty, rwsPutAwayWrkT.rwsStkQty);
        ZYPEZDItemValueSetter.setValue(inPMsg.invtyTrxDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(inPMsg.xxSysTp, NLZC002001.SYS_TP_PROCR);
        ZYPEZDItemValueSetter.setValue(inPMsg.sysSrcCd, bean.getSysSrcCd());
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvNum, bean.getTrxOrdNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.poRcvLineNum, bean.getTrxLineNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsNum, bean.getRwsNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsRefNum, bean.getRwsRefNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.rwsDtlLineNum, bean.getRwsDtlLineNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdNum, bean.getPoOrdNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.poOrdDtlLineNum, bean.getPoOrdDtlLineNum());
        ZYPEZDItemValueSetter.setValue(inPMsg.vndCd, bean.getVndCd());
        ZYPEZDItemValueSetter.setValue(inPMsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.uomCd, PKG_UOM.EACH);

        // Set Serial number
        RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
        rwsPutAwaySerWrkT.setSQLID("001");
        rwsPutAwaySerWrkT.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        rwsPutAwaySerWrkT.setConditionValue("wrkTrxId01", rwsPutAwayWrkT.wrkTrxId.getValue());
        rwsPutAwaySerWrkT.setConditionValue("sqId01", rwsPutAwayWrkT.sqId.getValue());
        rwsPutAwaySerWrkT.setConditionValue("rwsNum01", rwsPutAwayWrkT.rwsNum.getValue());
        rwsPutAwaySerWrkT.setConditionValue("rwsDtlLineNum01", rwsPutAwayWrkT.rwsDtlLineNum.getValue());

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

    private List<NLZC002001PMsg> getPmsgRpForStkOut(final NLZC208001PMsg param, final List<Map<String, Object>> shipMdseRpInfoList, NLZC208001Bean01 bean, BigDecimal rwsStkQty) throws SQLException {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        for (Map<String, Object> shipMdseRpInfo : shipMdseRpInfoList) {

            // Refurb Vendor Transfer Stock-Out from Vendor
            NLZC002001PMsg outPMsg = new NLZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR);
            ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, (String) shipMdseRpInfo.get("SHIP_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, (String) shipMdseRpInfo.get("TO_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
            ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, (String) shipMdseRpInfo.get("FROM_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, rwsStkQty);
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, param.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, (String) shipMdseRpInfo.get(SYS_SRC_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, (String) shipMdseRpInfo.get(TRX_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, (String) shipMdseRpInfo.get(TRX_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, (String) shipMdseRpInfo.get(RWS_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, (String) shipMdseRpInfo.get(RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, (String) shipMdseRpInfo.get(RWS_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.soNum, (String) shipMdseRpInfo.get(SO_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.soSlpNum, (String) shipMdseRpInfo.get("SO_SLP_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.poOrdNum, (String) shipMdseRpInfo.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.poOrdDtlLineNum, (String) shipMdseRpInfo.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdNum, (String) shipMdseRpInfo.get(INVTY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdLineNum, (String) shipMdseRpInfo.get(INVTY_ORD_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(outPMsg.shipToCustCd, (String) shipMdseRpInfo.get("TO_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.vndCd, (String) shipMdseRpInfo.get(VND_CD));
            ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, stdCcyCd);
            ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

            pMsgList.add(outPMsg);
        }

        return pMsgList;
    }
    // QC#55313 End

    // QC#54864 Start
    private String getLoanWh(String glblCmpyCd, String trxCd, String trxRsnCd, String invtyLocCd, String origCpoOrdNum, String origCpoDtlLineNum, String origCpoDtlLineSubNum) {

        // Get Outbound Inboud Code & Sub WH Code from DS_COND_CONST
        Map<String, Object> dsCondConstParam = new HashMap<String, Object>();
        dsCondConstParam.put("glblCmpyCd", glblCmpyCd);
        dsCondConstParam.put("condConstGrpId", "NLZC0020_LOAN_SWH");
        dsCondConstParam.put("trxCd", trxCd);
        dsCondConstParam.put("trxRsnCd", trxRsnCd);

        Map<String, Object> dsCondConstMap = (Map<String, Object>) ssmBatchClient.queryObject("getDsCondConst", dsCondConstParam);

        if (dsCondConstMap != null) {

            if ((String) dsCondConstMap.get("SWH_CD") != null) {

                RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, invtyLocCd);
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, (String) dsCondConstMap.get("SWH_CD"));
                rtlSwhTMsg = (RTL_SWHTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rtlSwhTMsg);

                if (rtlSwhTMsg == null) {

                    return null;
                }

                return rtlSwhTMsg.rtlSwhCd.getValue();

            } else if ((String) dsCondConstMap.get("INBD_OTBD_CD") != null) {

                if (INBD_OTBD.OUTBOUND.equals((String) dsCondConstMap.get("INBD_OTBD_CD"))) {

                    Map<String, Object> whParam = new HashMap<String, Object>();
                    whParam.put("glblCmpyCd", glblCmpyCd);
                    whParam.put("invtyLocCd", invtyLocCd);
                    whParam.put("cpoOrdNum", origCpoOrdNum);
                    whParam.put("cpoDtlLineNum", origCpoDtlLineNum);
                    whParam.put("cpoDtlLineSubNum", origCpoDtlLineSubNum);

                    return (String) ssmBatchClient.queryObject("getLoanWhFromOrgOrd", whParam);
                }
            }
        }

        return null;
    }

    private String getSWH(String glblCmpyCd, String invtyLocCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);
        RTL_SWHTMsg rtlSwhTMsg = (RTL_SWHTMsg) ssmBatchClient.queryObject("getRtlSwhRecByInvtyLocCd", queryParam);
        if (rtlSwhTMsg == null) {
            return null;
        } else {
            return rtlSwhTMsg.rtlSwhCd.getValue();
        }
    }

    private String getWH(String glblCmpyCd, String invtyLocCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);
        RTL_SWHTMsg rtlSwhTMsg = (RTL_SWHTMsg) ssmBatchClient.queryObject("getRtlSwhRecByInvtyLocCd", queryParam);
        if (rtlSwhTMsg == null) {
            return null;
        } else {
            return rtlSwhTMsg.rtlWhCd.getValue();
        }
    }

    private BigDecimal setSvcConfigMstrPkWithRWS(String glblCmpyCd, String mdseCd, String rwsNum) {

        BigDecimal ret = null;

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(glblCmpyCd);
        mdseTMsg.mdseCd.setValue(mdseCd);

        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return ret;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

            RWS_HDRTMsg rwsTMsg = new RWS_HDRTMsg();
            rwsTMsg.glblCmpyCd.setValue(glblCmpyCd);
            rwsTMsg.rwsNum.setValue(rwsNum);

            rwsTMsg = (RWS_HDRTMsg) S21ApiTBLAccessor.findByKey(rwsTMsg);

            if (rwsTMsg == null) {
                return ret;
            }

            ret = rwsTMsg.svcConfigMstrPk.getValue();
        }

        return ret;
    }
    // QC#54864 End
}