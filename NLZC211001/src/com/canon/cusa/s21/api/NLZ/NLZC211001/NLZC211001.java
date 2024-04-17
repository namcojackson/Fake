/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC211001;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AREA_LEAD_TMTMsg;
import business.db.BOLTMsg;
import business.db.BOL_RELNTMsg;
import business.db.CAL_RELNTMsg;
import business.db.CPOTMsg;
import business.db.EDI_ASNTMsg;
import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.INVTY_OWNRTMsg;
import business.db.MDSETMsg;
import business.db.POTMsg;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_HDRTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTE_GRPTMsg;
import business.db.RTE_GRP_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.SCE_EDI_TRXTMsg;
import business.db.SER_NUM_ERRTMsg;
import business.db.SHIP_SER_NUMTMsg;
import business.db.SHIP_SER_NUM_WRKTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_CONFTMsg;
import business.db.SHPG_ORD_CONF_DTLTMsg;
import business.db.SHPG_ORD_CONF_DTL_WRKTMsg;
import business.db.SHPG_ORD_CONF_WRKTMsg;
import business.db.SHPG_ORD_CUST_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_MSGTMsg;
import business.db.SHPG_ORD_SCHD_TRKTMsg;
import business.db.TRNSP_LTTMsg;
import business.db.VND_TP_RELNTMsg;
import business.db.WH_SCHDTMsg;
import business.db.WH_SCHDTMsgArray;
import business.db.WRK_ORDTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC211001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NPZC002001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC109001PMsg;
import business.parts.NPZC109001_detailListPMsg;
import business.parts.NPZC126001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;
import business.parts.NWZC403001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NPZ.NPZC002001.NPZC002001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC126001.NPZC126001;
import com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.api.NWZ.NWZC403001.NWZC403001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_RECOG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRIAL_FINAL_PRPS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmIntegerResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Update SO Confirmation
 * Get key of SO conf work tables from the parameter and do the followings.
 * (1)Update shipped quantity/status of SO
 * (2)Update inventory
 * (3)Close shipping plan,
 * (4)Close entry order
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/05/27   Fujitsu         K.Ozasa         Create          N/A
 * 2009/10/22   Fujitsu         K.Ozasa         Update          815
 * 2009/10/23   Fujitsu         K.Ozasa         Update          1008
 * 2010/01/12   Fujitsu         S.Uehara        Update          3041
 * 2010/01/20   Fujitsu         T.Motoyama      Update          3074
 * 2010/04/06   Fujitsu         R.Mori          Update          3208
 * 2010/04/19   Fujitsu         R.Mori          Update          3206
 * 2010/04/22   Fujitsu         R.Mori          Update          RQ1400(Procure)
 * 2010/05/24   CSAI            D.Fukaya        Update          6166
 * 2010/06/14   CSAI            D.Fukaya        Update          7019
 * 2010/06/16   CSAI            D.Fukaya        Update          7191
 * 2010/06/22   CSAI            D.Fukaya        Update          5015
 * 06/25/2010   Fujitsu         M.Yamada        Update          7150
 * 08/12/2010   CSAI            D.Fukaya        Update          238
 * 09/21/2010   CSAI            D.Fukaya        Update          N/A
 * 10/01/2010   CSAI            D.Fukaya        Update          N/A
 * 10/06/2010   CSAI            D.Fukaya        Update          N/A
 * 12/08/2010   CSAI            D.Fukaya        Update          743
 * 12/28/2010   CSAI            D.Fukaya        Update
 * 01/06/2011   CSAI            D.Fukaya        Update          1071
 * 01/24/2011   CSAI            M.Takahashi     Update
 * 02/07/2011   CSAI            D.Fukaya        Update          1345
 * 02/16/2011   CSAI            M.Takahashi     Update          1599
 * 03/01/2011   CSAI            M.Takahashi     Update          N/A
 * 04/05/2011   CSAI            D.Fukaya        Update          2013
 * 04/14/2011   CSAI            D.Fukaya        Update          2073
 * 08/22/2011   CSAI            M.Takahashi     Update          SR#357968
 * 06/06/2013   Fujitsu         Y.Taoka         Update          DS-Solution#R-OM025
 * 10/28/2015   CITS            T.Tokutomi      Update          CSA
 * 12/07/2015   CITS            T.Tokutomi      Update          QC#1492
 * 12/23/2015   CSAI            Y.Imazu         Update          QC#2287
 * 03/23/2016   CITS            T.Tokutomi      Update          QC#5905
 * 03/31/2016   CSAI            Y.Imazu         Update          QC#6379
 * 04/25/2016   CSAI            Y.Imazu         Update          QC#7584
 * 04/26/2016   CSAI            Y.Imazu         Update          QC#7598
 * 04/28/2016   CSAI            Y.Imazu         Update          QC#7714
 * 05/05/2016   CSAI            Y.Imazu         Update          QC#6663
 * 05/06/2016   CSAI            Y.Imazu         Update          QC#8042
 * 05/27/2016   CSAI            Y.Imazu         Update          QC#9196
 * 06/10/2016   CSAI            Y.Imazu         Update          QC#9772
 * 06/14/2016   CSAI            Y.Imazu         Update          QC#9873
 * 07/12/2016   CSAI            Y.Imazu         Update          QC#9677
 * 07/27/2016   CSAI            Y.Imazu         Update          QC#594
 * 07/28/2016   CSAI            Y.Imazu         Update          QC#12545
 * 08/17/2016   CSAI            Y.Imazu         Update          QC#6663
 * 08/23/2016   CITS            T.Hakodate      Update          QC#13719
 * 08/26/2016   CITS            T.Tokutomi      Update          QC#10302
 * 10/07/2016   CITS            T.Wada          Update          QC#13807
 * 10/28/2016   CITS            T.Wada          Update          QC#15562
 * 12/19/2016   CITS            K.Ogino         Update          QC#6562
 * 01/12/2017   CITS            R.Shimamoto     Update          QC#17029
 * 01/27/2017   CITS            T.Wada          Update          QC#17338
 * 02/06/2017   CITS            K.Ogino         Update          QC#17394
 * 02/20/2017   CITS            T.Wada          Update          QC#17340
 * 04/14/2014   CITS            R.Shimamoto     Update          QC#18302
 * 05/31/2017   CITS            T.Wada          Update          QC#18628
 * 07/12/2017   CITS            K.Ogino         Update          QC#19831
 * 08/15/2017   CITS            K.Ogino         Update          QC#20554
 * 09/12/2017   CITS            T.Hakodate      Update          Sol#069(QC#18270)
 * 09/21/2017   CITS            S.Endo          Update          Sol#069(QC#18270)
 * 11/13/2017   CITS            M.Naito         Update          QC#22503
 * 01/17/2018   CITS            K.Ogino         Update          QC#23438
 * 06/22/2018   CITS            K.Ogino         Update          QC#26866
 * 07/12/2018   CITS            T.hakodate      Update          QC#26863
 * 08/10/2018   CITS            T.hakodate      Update          QC#26585
 * 09/03/2019   CITS            K.Ogino         Update          QC#53009
 * 01/15/2019   CITS            A.Marte         Update          QC#58256
 * 10/06/2021   CITS            A.Marte         Update          QC#59155
 * 04/11/2022   CITS            K.Iwamoto       Update          QC#59904
 * 08/11/2022   CITS            R.Azucena       Update          QC#60359
 * 12/07/2022   CITS            F.Fadriquela    Update          QC#60889
 * 14/03/2024   CITS            J.Cho           Update          QC#63527
 * 03/22/2024   CITS            T.Omori         Update          QC#63431
 *</pre>
 */
public class NLZC211001 extends S21ApiCommonBase implements NLZC211001Constant {

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** Online/Batch Type */
    private ONBATCH_TYPE onBatch;

    /** SCE Order Type CD */
    private String sceOrdTpCd = null;

    /** SO Conf Work Count */
    private int cntConfWrk = 0;

    /** SO Conf Detail Work Count */
    private int cntConfDtlWrk = 0;

    /** Serial# Work Count */
    private int cntSerNumWrk = 0;

    /** Detail Shipping Status */
    private DetailShippingStatus detailShippingStatus = null;

    /**
     * Initialize
     */
    public NLZC211001() {
        super();
        // Initialize SSM
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }
    /**
     * execute
     * @param param NLZC211001PMsg
     * @param onBatchType Online/Batch Type
     */
    public void execute(final NLZC211001PMsg param, final ONBATCH_TYPE onBatchType) {

        try {
            msgMap = new S21ApiMessageMap(param);
            this.onBatch = onBatchType;

            updateSoConfirmation(param);
            outputProcessCnt();
        } finally {
            // Copy all messages to the parameter
            msgMap.flush();
        }
    }

    /**
     * execute (not to be used)
     * @see com.canon.cusa.s21.api.NLZ.NLZC211001.execute
     * @param params List of NLZC211001PMsg
     * @param onBatchType Online/Batch Type
     */
    public void execute(final List<NLZC211001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NLZC211001PMsg msg : params) {

            execute(msg, onBatchType);
        }
    }

    /**
     * Output Process Count
     */
    private void outputProcessCnt() {

        String[] str1 = {SHPG_ORD_CONF_WRK, DATA_PROCESSED, String.valueOf(this.cntConfWrk) };
        String[] str2 = {SHPG_ORD_CONF_DTL_WRK, DATA_PROCESSED, String.valueOf(this.cntConfDtlWrk) };
        String[] str3 = {SHIP_SER_NUM_WRK, DATA_PROCESSED, String.valueOf(this.cntSerNumWrk) };

        S21InfoLogOutput.println(ZZBM0009I, str1);
        S21InfoLogOutput.println(ZZBM0009I, str2);
        S21InfoLogOutput.println(ZZBM0009I, str3);
    }

    /**
     * Update SO Confirmation
     * @param param NLZC211001PMsg
     */
    private void updateSoConfirmation(final NLZC211001PMsg param) {

        boolean errFlg = false;

        // Check input parameter
        if (checkInParameter(param)) {

            return;
        }

        // Get SO data, lock it
        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, param.soNum.getValue());
        shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdTMsg);

        if (shpgOrdTMsg == null) {

            doErrorProcess(NLZM2300E, " This SO# does not exist.");
            return;
        }

        sceOrdTpCd = shpgOrdTMsg.sceOrdTpCd.getValue();

        // Get SO conf work data, check it
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        queryParam.put("wrkTrxId", param.wrkTrxId.getValue());
        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
        SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg = new SHPG_ORD_CONF_WRKTMsg();

        errFlg = (Boolean) this.ssmBatchClient.queryObject("getShpgOrdConfWrk", queryParam, new SoConfWrkProcess(shpgOrdConfWrkTMsg, shpgOrdTMsg));

        if (errFlg) {

            return;
        }

        if (shpgOrdConfWrkTMsg != null && !ZYPCommonFunc.hasValue(shpgOrdConfWrkTMsg.glblCmpyCd)) {

            shpgOrdConfWrkTMsg = null;
        }

        // Process by order status
        SoConfDtlWrkProcess confDtlProcess = new SoConfDtlWrkProcess(param, shpgOrdConfWrkTMsg, shpgOrdTMsg);

        if (shpgOrdConfWrkTMsg == null || (shpgOrdConfWrkTMsg != null && (SO_PROC_STS.SHIP.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue()) || SO_PROC_STS.LINE_VOID.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())))) {

            // Get SO conf detail work data, check them, import them to SO conf detail
            queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("soNum", param.soNum.getValue());
            queryParam.put("wrkTrxId", param.wrkTrxId.getValue());
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);

            errFlg = (Boolean) this.ssmBatchClient.queryObject("getShpgOrdConfDtlWrk", queryParam, confDtlProcess);

            if (errFlg) {

                return;
            }

        } else if (SO_PROC_STS.ORDER_VOID.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

            // Order cancel process
            queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("soNum", param.soNum.getValue());
            List<String> list = createConditionListForOrderCancel();
            queryParam.put("list", list);
            this.ssmBatchClient.queryObject("getShpgOrdDtlByShpgStsCd", queryParam, new SoDtlCancelProcess(shpgOrdTMsg));
        }

        // Get serial work data, import them to serial table (Not break off even if an error occurs)
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        queryParam.put("wrkTrxId", param.wrkTrxId.getValue());
        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);

        // Not break off when serial error
        errFlg = (Boolean) ssmBatchClient.queryObject("getShipSerNumWrk", queryParam, new ShipSerNumWrkProcess(shpgOrdTMsg));

        // Check if all details are closed or not
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        CheckAllDetailClose cadc = new CheckAllDetailClose();
        boolean allDetailCloseFlg = (Boolean) this.ssmBatchClient.queryObject("getShpgOrdDtl", queryParam, cadc);

        // Import SO conf work data to SO conf
        if (shpgOrdConfWrkTMsg != null) {

            if (!SO_PROC_STS.LINE_VOID.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue()) || detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                importSoConf(shpgOrdConfWrkTMsg);
            }
        }

        boolean asnProcessFlg = false;

        if (allDetailCloseFlg) {

            SHPG_ORD_CONFTMsg shpgOrdConfTMsg = createShipOrdConf(shpgOrdTMsg, cadc.isProcessShip());

            if (shpgOrdConfTMsg == null) {
                return;
            }

            processSoClose(shpgOrdConfWrkTMsg, shpgOrdConfTMsg, shpgOrdTMsg);

            if (shpgOrdConfWrkTMsg != null && SO_PROC_STS.SHIP.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

                asnProcessFlg = true;
            }

        } else {

            // Re Close.
            if (shpgOrdConfWrkTMsg != null && SO_PROC_STS.SHIP.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

                errFlg = confDtlProcess.reClose(param);

                if (!errFlg) {

                    // Critical Error. Process end.
                    return;
                }

                if ((Boolean) this.ssmBatchClient.queryObject("getShpgOrdDtl", queryParam, cadc)) {

                    processSoClose(shpgOrdConfWrkTMsg, null, shpgOrdTMsg);
                    asnProcessFlg = true;
                }

            } else {

                if (cadc.isProcessShip()) {

                    asnProcessFlg = true;
                }
            }
        }

        // Create ASN Process
        if (asnProcessFlg) {

            // check if ASN need to be created
            boolean isAsnRequiredFlag = isAsnRequired(param.glblCmpyCd.getValue(), param.soNum.getValue());

            if (isAsnRequiredFlag) {

                // get ASN data
                queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
                queryParam.put("soNum", param.soNum.getValue());

                errFlg = (Boolean) this.ssmBatchClient.queryObject("getAsnData", queryParam, new ASNProcess(shpgOrdTMsg));

                if (errFlg) {

                    return;
                }
            }
        }
        
        // QC#18302 Add.
        // Allocation Cancel To Partially Shipped
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        queryParam.put("shipped", DS_SO_LINE_STS.SHIPPED);
        queryParam.put("soCancelled", SHPG_STS.S_OR_O_CANCELLED);
        queryParam.put("cpoExstFlgN", ZYPConstant.FLG_OFF_N);

        errFlg = (Boolean) this.ssmBatchClient.queryObject("checkPartiallyShippedFromNonCpo", queryParam, new allocationCancelToPartiallyShipped(shpgOrdTMsg));

    }

    /**
     * createShipOrdConf
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param boolean isProcShip
     * @return SHPG_ORD_CONFTMsg
     */
    private SHPG_ORD_CONFTMsg createShipOrdConf(SHPG_ORDTMsg shpgOrdTMsg, boolean isProcShip) {

        SHPG_ORD_CONFTMsg shpgOrdConfTMsg = new SHPG_ORD_CONFTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soNum, shpgOrdTMsg.soNum);
        shpgOrdConfTMsg = (SHPG_ORD_CONFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdConfTMsg);

        if (shpgOrdConfTMsg == null) {

            String soProcStsCd = SO_PROC_STS.LINE_VOID;

            if (isProcShip) {

                soProcStsCd = SO_PROC_STS.SHIP;
            }

            List<SHPG_ORD_CONF_DTLTMsg> shpgOrdConfDtlTMsgList = getShpgOrdConfDtlList(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.soNum.getValue(), null, null, soProcStsCd);
            SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg = shpgOrdConfDtlTMsgList.get(shpgOrdConfDtlTMsgList.size() - 1);

            // QC#17029 Add.
            String rtlWhCd = getRtlWhCdFromRtlSwh(shpgOrdConfDtlTMsg.glblCmpyCd.getValue(), shpgOrdConfDtlTMsg.whCd.getValue());
            if (!ZYPCommonFunc.hasValue(rtlWhCd)) {

                doErrorProcess(NLZM2503E, shpgOrdConfDtlTMsg.whCd.getValue());
                return null;
            }

            // Create
            shpgOrdConfTMsg = new SHPG_ORD_CONFTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.glblCmpyCd, shpgOrdConfDtlTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soNum, shpgOrdConfDtlTMsg.soNum);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.whCd, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.sceOrdTpCd, shpgOrdTMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soProcStsCd, shpgOrdConfDtlTMsg.soProcStsCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soDataActTpCd, shpgOrdConfDtlTMsg.soDataActTpCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.shipDtTmTs, shpgOrdConfDtlTMsg.shipDtTmTs);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.totShipWt, shpgOrdConfDtlTMsg.totShipWt);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.totFrtAmt, shpgOrdConfDtlTMsg.totFrtAmt);
            S21ApiTBLAccessor.insert(shpgOrdConfTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdConfTMsg.getReturnCode())) {

                doErrorProcess(NLBM1064E, "DB error occurred.");
                return null;
            }
        }

        return shpgOrdConfTMsg;
    }

    /**
     * Get SHPG_ORD_DTL
     * @param glblCmpyCd String
     * @param soNum String
     * @param soSlpNum String
     * @return SHPG_ORD_DTLTMsg
     */
    private SHPG_ORD_DTLTMsg getShpgOrdDtl(String glblCmpyCd, String soNum, String soSlpNum) {

        SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soSlpNum, soSlpNum);

        return (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDtlTMsg);
    }

    /**
     * getShpgOrdConfDtlList
     * @param glblCmpyCd String
     * @param soNum String
     * @param soSlpNum String
     * @param mdseCd String
     * @param soProcStsCd String
     * @return List<SHPG_ORD_CONF_DTLTMsg>
     */
    private List<SHPG_ORD_CONF_DTLTMsg> getShpgOrdConfDtlList(String glblCmpyCd, String soNum, String soSlpNum, String mdseCd, String soProcStsCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("soNum", soNum);
        queryParam.put("soSlpNum", soSlpNum);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("soProcStsCd", soProcStsCd);

        return (List<SHPG_ORD_CONF_DTLTMsg>) ssmBatchClient.queryObjectList("getShpgOrdConfDtl", queryParam);
    }

    /**
     * getRtlWhCdFromRtlSwh
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return rtl_wh_cd String
     */
    private String getRtlWhCdFromRtlSwh(String glblCmpyCd, String invtyLocCd) {

        // QC#17029 Mod.
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);

        String rtlWhCd = (String) ssmBatchClient.queryObject("getRtlWhCdFromRtlSwh", queryParam);

        if (ZYPCommonFunc.hasValue(rtlWhCd)) {

            return rtlWhCd;

        } else {

            return "";

        }
    }

    /**
     * Check Input Parameter
     * @param param NLZC211001PMsg
     * @return boolean true : NG, false : OK
     */
    private boolean checkInParameter(NLZC211001PMsg param) {

        boolean errFlg = false;

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {

            doErrorProcess(NLZM2001E, "glblCmpyCd");
            errFlg = true;
        }

        // SO_NUM
        if (!ZYPCommonFunc.hasValue(param.soNum)) {

            doErrorProcess(NLZM2001E, "soNum");
            errFlg = true;
        }

        // WRK_TRX_ID
        if (!ZYPCommonFunc.hasValue(param.wrkTrxId)) {

            doErrorProcess(NLZM2001E, "wrkTrxId");
            errFlg = true;
        }

        return errFlg;
    }

    /**
     * SO Conf Work Process
     */
    private class SoConfWrkProcess extends S21SsmBooleanResultSetHandlerSupport {

        /** SHPG_ORD_CONF_WRKTMsg */
        private SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg;

        /** SHPG_ORDTMsg */
        private SHPG_ORDTMsg shpgOrdTMsg;

        public SoConfWrkProcess(SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg, SHPG_ORDTMsg shpgOrdTMsg) {

            this.shpgOrdConfWrkTMsg = shpgOrdConfWrkTMsg;
            this.shpgOrdTMsg = shpgOrdTMsg;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean errFlg = false;

            // Process 1st record only
            if (rs.next()) {

                // Copy to TMsg
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.soNum, rs.getString(SO_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.wrkTrxId, rs.getString(WRK_TRX_ID));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.sqId, rs.getString(SQ_ID));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.procStsCd, rs.getString(PROC_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.errMsgCd, rs.getString(ERR_MSG_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.whCd, rs.getString(WH_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.sceOrdTpCd, rs.getString(SCE_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.s80OrdTpCd, rs.getString(S80_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.soProcStsCd, rs.getString(SO_PROC_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.soDataActTpCd, rs.getString(SO_DATA_ACT_TP_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.shipDtTmTs, rs.getString(SHIP_DT_TM_TS));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.totShipWt, nullToZero(rs.getBigDecimal(TOT_SHIP_WT)));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.totFrtAmt, nullToZero(rs.getBigDecimal(TOT_FRT_AMT)));
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.srcTpCd, rs.getString(SRC_TP_CD));

                // Check SO conf work data
                if (checkConfWrk()) {

                    errFlg = true;
                }

            } else {

                this.shpgOrdConfWrkTMsg = null;
            }

            return errFlg;
        }

        /**
         * Check SO Conf Work Data
         * @return Check Result
         */
        private boolean checkConfWrk() {

            boolean errFlg = false;

            // SO# not in SO
            if (!shpgOrdTMsg.soNum.getValue().equals(shpgOrdConfWrkTMsg.soNum.getValue())) {

                doErrorProcess(NLZM2005E, "SO# not in SO");
                errFlg = true;

                // Return since the following checks cannot be done
                return errFlg;
            }

            // Get SCE order type of SO
            sceOrdTpCd = shpgOrdTMsg.sceOrdTpCd.getValue();

            // Shipping status of SO is not 'Printed', 'Picked', 'Packed', 'Staged'
            if (!(SHPG_STS.S_OR_O_PRINTED.equals(shpgOrdTMsg.shpgStsCd.getValue()) || SHPG_STS.PICKED.equals(shpgOrdTMsg.shpgStsCd.getValue()) || SHPG_STS.PACKED.equals(shpgOrdTMsg.shpgStsCd.getValue()) || SHPG_STS.STAGED
                    .equals(shpgOrdTMsg.shpgStsCd.getValue()))) {

                doErrorProcess(NLZM2006E, shpgOrdTMsg.toString());
                errFlg = true;
            }

            // Not same W/H as SO
            if (!shpgOrdConfWrkTMsg.whCd.getValue().equals(shpgOrdTMsg.whCd.getValue())) {

                doErrorProcess(NLZM2007E, shpgOrdTMsg.toString());
                errFlg = true;
            }

            // SO_PROC_STS_CD is not in code table (not 'Ship', 'Line Void', 'Order Void')
            if (!ZYPCodeDataUtil.hasCodeValue(TBL_SO_PROC_STS, shpgOrdConfWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

                doErrorProcess(NLZM2008E, "- 1" + shpgOrdTMsg.toString());
                errFlg = true;
            }

            // Ship Conf Available Flag : 'Order Void'
            if (doesExistDsCondConst(shpgOrdConfWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfWrkTMsg.sceOrdTpCd.getValue(), ZYPConstant.FLG_OFF_N, null, null, null, null)) {

                if (!SO_PROC_STS.ORDER_VOID.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

                    doErrorProcess(NLZM2008E, "- 3" + shpgOrdTMsg.toString());
                    errFlg = true;
                }
            }

            // Line Ship Available Flag : 'Line Void'
            if (doesExistDsCondConst(shpgOrdConfWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfWrkTMsg.sceOrdTpCd.getValue(), null, null, null, ZYPConstant.FLG_OFF_N, null)) {

                if (SO_PROC_STS.LINE_VOID.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

                    doErrorProcess(NLZM2008E, "- 3" + shpgOrdTMsg.toString());
                    errFlg = true;
                }
            }

            // SO_PROC_STS_CD is 'Order Void' and SHIP_QTY is more than 0 in at least one SO detail
            if (SO_PROC_STS.ORDER_VOID.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

                // Get SO detail count in which Ship Qty is more than 0
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", shpgOrdConfWrkTMsg.glblCmpyCd.getValue());
                queryParam.put("soNum", shpgOrdConfWrkTMsg.soNum.getValue());
                int cnt = (Integer) ssmBatchClient.queryObject("getShpgOrdDtlCnt", queryParam, new GetShpgOrdDtlCnt());

                if (cnt > 0) {

                    doErrorProcess(NLZM2008E, "- 4" + shpgOrdTMsg.toString());
                    errFlg = true;
                }
            }

            return errFlg;
        }
    }

    /**
     * SO Conf Detail Work Process
     */
    private class SoConfDtlWrkProcess extends S21SsmBooleanResultSetHandlerSupport {

        /** SHPG_ORD_CONF_WRKTMsg */
        private SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg;

        /** SHPG_ORDTMsg */
        private SHPG_ORDTMsg shpgOrdTMsg;

        /** Map<String, SHPG_ORD_DTLTMsg> */
        private Map<String, SHPG_ORD_DTLTMsg> soDtlMap;

        /** NLZC211001PMsg */
        private NLZC211001PMsg param;

        public SoConfDtlWrkProcess(NLZC211001PMsg param, SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg, SHPG_ORDTMsg shpgOrdTMsg) {

            this.shpgOrdConfWrkTMsg = shpgOrdConfWrkTMsg;
            this.shpgOrdTMsg = shpgOrdTMsg;
            this.soDtlMap = new HashMap<String, SHPG_ORD_DTLTMsg>();
            this.param = param;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            int cnt = 0;
            boolean errFlg = false;
            SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg = new SHPG_ORD_CONF_DTL_WRKTMsg();

            // Check all at first
            String chkSoProcStsCd = null;
            while (rs.next()) {
                cnt++;

                // Copy to TMsg
                shpgOrdConfDtlWrkTMsg = copyConfDtlWrkToTMsg(rs, shpgOrdConfDtlWrkTMsg);

                // Check SO conf detail work data
                if (checkConfDtlWrk(shpgOrdConfDtlWrkTMsg)) {

                    errFlg = true;
                    break;
                }

                // check soProcStsCd mixed.
                if (doesExistDsCondConst(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), sceOrdTpCd, null, null, null, ZYPConstant.FLG_OFF_N, null)) {

                    if (chkSoProcStsCd == null) {

                        chkSoProcStsCd = shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue();

                    } else {

                        if (!chkSoProcStsCd.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue())) {

                            doErrorProcess(NLZM2008E, "- 3" + shpgOrdConfDtlWrkTMsg.toString());
                            return true;
                        }
                    }
                }
            }

            // No target data
            if (cnt == 0) {

                // Skip detail update process. next serial process.
                if (shpgOrdConfWrkTMsg != null && SO_PROC_STS.SHIP.equals(shpgOrdConfWrkTMsg.soProcStsCd.getValue())) {

                    return false;
                }

                if (doesExistDsCondConst(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.sceOrdTpCd.getValue(), null, null, null, ZYPConstant.FLG_OFF_N, null)) {

                    doErrorProcess(NLZM2325E, "All lines of shipping order are not shipped.[SO#" + shpgOrdTMsg.soNum.getValue() + "]");

                } else {

                    doErrorProcess(NLZM2004E, "No target data confDtlWrk");
                }

                errFlg = true;
            }

            // check Qty
            if (doesExistDsCondConst(shpgOrdTMsg.glblCmpyCd.getValue(), sceOrdTpCd, null, null, null, null, ZYPConstant.FLG_OFF_N)) {

                if (!checkConfDtlWrkQty()) {

                    errFlg = true;
                }
            }

            // Business error
            if (errFlg) {

                return errFlg;
            }

            CPOTMsg cpoTMsg = null;

            if (SCE_ORD_TP.EXPORT.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {

                List<CPOTMsg> cpoList = (List<CPOTMsg>) ssmBatchClient.queryObjectList("getCpoInfo", shpgOrdTMsg);

                if (!cpoList.isEmpty()) {

                    cpoTMsg = cpoList.get(0);
                }
            }

            // Back the cursor
            rs.first();

            do {

                // Copy to TMsg
                shpgOrdConfDtlWrkTMsg = copyConfDtlWrkToTMsg(rs, shpgOrdConfDtlWrkTMsg);
                setMdseCdFromShpgOrdDtl(shpgOrdConfDtlWrkTMsg);

                if (SO_PROC_STS.SHIP.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue())) {

                    importSoConfDtl(shpgOrdConfDtlWrkTMsg, cpoTMsg);

                } else if (SO_PROC_STS.LINE_VOID.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue())) {

                    // Process 'Line Void' detail
                    importLineCancelDtl(shpgOrdConfDtlWrkTMsg, cpoTMsg);
                }

                cntConfDtlWrk++;

            } while (rs.next());

            return errFlg;
        }

        /**
         * Get SHPG_ORD_DTL
         * @param soSlpNum String
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         * @return SHPG_ORD_DTLTMsg
         */
        private SHPG_ORD_DTLTMsg getSoDetail(String soSlpNum, SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) {

            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();

            if (soDtlMap.containsKey(shpgOrdConfDtlWrkTMsg.soSlpNum.getValue())) {

                shpgOrdDtlTMsg = soDtlMap.get(shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());

            } else {

                shpgOrdDtlTMsg = getShpgOrdDtl(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfDtlWrkTMsg.soNum.getValue(), shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());
            }

            return shpgOrdDtlTMsg;
        }

        /**
         * checkConfDtlWrkQty
         * @return boolean
         */
        private boolean checkConfDtlWrkQty() {

            for (Iterator it = soDtlMap.entrySet().iterator(); it.hasNext();) {

                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();

                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = soDtlMap.get(key);

                // Partial Ship is not available
                if (shpgOrdDtlTMsg.shpgQty.getValue().compareTo(shpgOrdDtlTMsg.shipQty.getValue()) != 0) {

                    doErrorProcess(NLZM2048E, "SO#:" + shpgOrdDtlTMsg.soSlpNum.getValue() + "Line#:" + shpgOrdDtlTMsg.soSlpNum.getValue());
                    return false;
                }
            }

            return true;
        }

        /**
         * setMdseCdFromShpgOrdDtl
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         */
        private void setMdseCdFromShpgOrdDtl(SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) {

            // Get SO detail
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getSoDetail(shpgOrdConfDtlWrkTMsg.soSlpNum.getValue(), shpgOrdConfDtlWrkTMsg);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.mdseCd, shpgOrdDtlTMsg.mdseCd.getValue());
        }

        /**
         * Copy SO Conf Detail Work to TMsg
         * @param rs ResultSet
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         * @return SHPG_ORD_CONF_DTL_WRKTMsg
         * @throws SQLException
         */
        private SHPG_ORD_CONF_DTL_WRKTMsg copyConfDtlWrkToTMsg(ResultSet rs, SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) throws SQLException {

            // Copy to TMsg
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.soNum, rs.getString(SO_NUM));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.soSlpNum, rs.getString(SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.wrkTrxId, rs.getString(WRK_TRX_ID));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.sqId, rs.getString(SQ_ID));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.proNum, rs.getString(PRO_NUM));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.vndCd, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.procStsCd, rs.getString(PROC_STS_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.errMsgCd, rs.getString(ERR_MSG_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.mdseCd, rs.getString(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.whCd, rs.getString(WH_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.fromStkStsCd, rs.getString(FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.s80StkStsCd, rs.getString(S80_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.bolNum, rs.getString(BOL_NUM));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.totShipWt, nullToZero(rs.getBigDecimal(TOT_SHIP_WT)));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.totFrtAmt, nullToZero(rs.getBigDecimal(TOT_FRT_AMT)));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.shipQty, nullToZero(rs.getBigDecimal(SHIP_QTY)));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.soDataActTpCd, rs.getString(SO_DATA_ACT_TP_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.soProcStsCd, rs.getString(SO_PROC_STS_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.srcTpCd, rs.getString(SRC_TP_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.ucc128Cd, rs.getString(UCC128_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.shipDtTmTs, rs.getString(SHIP_DT_TM_TS));
            /**
             * QC#10302
             */
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.shpgSvcLvlCd, rs.getString(SHPG_SVC_LVL_CD));

            return shpgOrdConfDtlWrkTMsg;
        }

        /**
         * Check SO Conf Detail Work Data
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         * @return Check Result
         */
        private boolean checkConfDtlWrk(SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) {

            boolean errFlg = false;

            // Check so#
            if (!shpgOrdTMsg.soNum.getValue().equals(shpgOrdConfDtlWrkTMsg.soNum.getValue())) {

                doErrorProcess(NLZM2005E, "SO# not in SO");
                errFlg = true;

                // Return since the following checks cannot be done
                return errFlg;
            }

            // Shipping status of SO is not 'Printed', 'Picked', 'Packed', 'Staged'
            if (SHPG_STS.P_OR_O_PRINTED.equals(shpgOrdTMsg.shpgStsCd.getValue()) || SHPG_STS.PACKED.equals(shpgOrdTMsg.shpgStsCd.getValue()) || SHPG_STS.PICKED.equals(shpgOrdTMsg.shpgStsCd.getValue())
                    || SHPG_STS.STAGED.equals(shpgOrdTMsg.shpgStsCd.getValue())) {

                doErrorProcess(NLZM2006E, shpgOrdTMsg.toString());
                errFlg = true;
            }

            // Get SO detail
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getSoDetail(shpgOrdConfDtlWrkTMsg.soSlpNum.getValue(), shpgOrdConfDtlWrkTMsg);

            // SO#, Line# not in SO detail
            if (shpgOrdDtlTMsg == null) {

                doErrorProcess(NLZM2005E, "SO#, Line# not in SO detail");
                errFlg = true;

                // Return since the following checks cannot be done
                return errFlg;

            } else {

                // Set SHPG_ORD_DTLTMsg
                soDtlMap.put(shpgOrdConfDtlWrkTMsg.soSlpNum.getValue(), shpgOrdDtlTMsg);
            }

            // SHIP_QTY > (SHPG_QTY - SHIP_QTY) of SO detail
            if (shpgOrdConfDtlWrkTMsg.shipQty.getValue().compareTo(nullToZero(shpgOrdDtlTMsg.shpgQty.getValue()).subtract(nullToZero(shpgOrdDtlTMsg.shipQty.getValue()))) == 1) {

                doErrorProcess(NLZM2009E, shpgOrdConfDtlWrkTMsg);
                errFlg = true;

            } else {

                // shpg_ord_dtl.shipQty + shpg_ord_conf_wrk.shipQty.
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shipQty, nullToZero(shpgOrdDtlTMsg.shipQty.getValue()).add(nullToZero(shpgOrdConfDtlWrkTMsg.shipQty.getValue())));
                soDtlMap.put(shpgOrdConfDtlWrkTMsg.soSlpNum.getValue(), shpgOrdDtlTMsg);
            }

            // check from Stock status
            if (!shpgOrdDtlTMsg.fromStkStsCd.getValue().equals(shpgOrdConfDtlWrkTMsg.fromStkStsCd.getValue())) {

                doErrorProcess(NLZM2312E, shpgOrdConfDtlWrkTMsg);
                errFlg = true;
            }

            // Not same W/H as SO
            if (!shpgOrdConfDtlWrkTMsg.whCd.getValue().equals(shpgOrdDtlTMsg.invtyLocCd.getValue())) {

                doErrorProcess(NLZM2007E, shpgOrdConfDtlWrkTMsg);
                errFlg = true;
            }

            // Not same MDSE as SO
            if (!shpgOrdConfDtlWrkTMsg.mdseCd.getValue().trim().equals(shpgOrdDtlTMsg.mdseCd.getValue().trim())) {

                doErrorProcess(NLZM2010E, shpgOrdConfDtlWrkTMsg);
                errFlg = true;
            }

            // SO_PROC_STS is 'Ship', SCE_ORD_TP of SO is 'Regular', 'Trial For Sales', 'Trial For Use', 'Loan', 'ROSS', and neither BOL# nor (PRO# and VND_CD) is set
            if (SO_PROC_STS.SHIP.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue())) {

                if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd) || SCE_ORD_TP.LOAN.equals(sceOrdTpCd) || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

                    if (!ZYPCommonFunc.hasValue(shpgOrdConfDtlWrkTMsg.bolNum) && (!ZYPCommonFunc.hasValue(shpgOrdConfDtlWrkTMsg.proNum) || !ZYPCommonFunc.hasValue(shpgOrdConfDtlWrkTMsg.vndCd))) {

                        doErrorProcess(NLZM2011E, shpgOrdConfDtlWrkTMsg);
                        errFlg = true;
                    }
                }
            }

            // SO_PROC_STS_CD is not 'Ship', 'Line Void'
            if (!(SO_PROC_STS.SHIP.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue()) || SO_PROC_STS.LINE_VOID.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue()))) {

                doErrorProcess(NLZM2008E, "- 1" + shpgOrdConfDtlWrkTMsg.toString());
                errFlg = true;
            }

            // SO_PROC_STS_CD is 'Line Void', and SHIP_QTY of SO detail > 0
            if (doesExistDsCondConst(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), sceOrdTpCd, null, null, null, null, ZYPConstant.FLG_OFF_N)
                    || doesExistDsCondConst(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), sceOrdTpCd, null, null, null, ZYPConstant.FLG_OFF_N, null)) {

                if (SO_PROC_STS.LINE_VOID.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue())) {

                    if (nullToZero(shpgOrdDtlTMsg.shipQty.getValue()).compareTo(new BigDecimal(0)) == 1) {

                        doErrorProcess(NLZM2008E, "- 2" + shpgOrdConfDtlWrkTMsg.toString());
                        errFlg = true;
                    }
                }
            }

            // SO_PROC_STS same line Config Item, Set Item
            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk) || ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.setMdseCd)) {

                for (SHPG_ORD_DTLTMsg sod : soDtlMap.values()) {

                    // Config Item
                    if (ZYPCommonFunc.hasValue(sod.pickSvcConfigMstrPk)) {

                        // Line Void ShipQty check
                        if (SO_PROC_STS.LINE_VOID.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue()) && nullToZero(sod.shipQty.getValue()).compareTo(new BigDecimal(0)) == 1) {

                            doErrorProcess(NLZM2008E, "- 2" + shpgOrdConfDtlWrkTMsg.toString());
                            errFlg = true;
                        }

                        // Line Ship. status mixed check.
                        if (SO_PROC_STS.SHIP.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue()) && SHPG_STS.CANCELLED.equals(sod.shpgStsCd.getValue())) {

                            doErrorProcess(NLZM2008E, "- 2" + shpgOrdConfDtlWrkTMsg.toString());
                            errFlg = true;
                        }
                    }

                    // Set Item
                    if (ZYPCommonFunc.hasValue(sod.setMdseCd)) {

                        // Line Void ShipQty check
                        if (SO_PROC_STS.LINE_VOID.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue()) && nullToZero(sod.shipQty.getValue()).compareTo(new BigDecimal(0)) == 1) {

                            doErrorProcess(NLZM2008E, "- 2" + shpgOrdConfDtlWrkTMsg.toString());
                            errFlg = true;
                        }

                        // Line Ship. status mixed check.
                        if (SO_PROC_STS.SHIP.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue()) && SHPG_STS.CANCELLED.equals(sod.shpgStsCd.getValue())) {

                            doErrorProcess(NLZM2008E, "- 2" + shpgOrdConfDtlWrkTMsg.toString());
                            errFlg = true;
                        }
                    }
                }
            }

            // Set Item shipQty check
            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.setMdseCd)) {

                if (SO_PROC_STS.LINE_VOID.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue()) || nullToZero(shpgOrdDtlTMsg.shipQty.getValue()).compareTo(shpgOrdConfDtlWrkTMsg.shipQty.getValue()) != 0) {

                    doErrorProcess(NLZM2008E, "- 2" + shpgOrdConfDtlWrkTMsg.toString());
                    errFlg = true;
                }
            }

            // SO_PROC_STS is 'Ship', SCE_ORD_TP of SO is 'Regular', 'Trial For Sales', 'Trial For Use', 'Loan', 'ROSS', and Vendor Code not in Master
            if (SO_PROC_STS.SHIP.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue())) {

                if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd) || SCE_ORD_TP.LOAN.equals(sceOrdTpCd) || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

                    // Check only when Vendor Code is set
                    if (ZYPCommonFunc.hasValue(shpgOrdConfDtlWrkTMsg.vndCd.getValue())) {

                        VND_TP_RELNTMsg vndTpReln = new VND_TP_RELNTMsg();
                        ZYPEZDItemValueSetter.setValue(vndTpReln.glblCmpyCd, shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(vndTpReln.vndCd, shpgOrdConfDtlWrkTMsg.vndCd.getValue());
                        ZYPEZDItemValueSetter.setValue(vndTpReln.vndTpCd, VND_TP_CD_SCAC);
                        vndTpReln = (VND_TP_RELNTMsg) S21ApiTBLAccessor.findByKey(vndTpReln);

                        if (vndTpReln == null) {

                            doErrorProcess(NLZM2044W, shpgOrdConfDtlWrkTMsg);
                        }
                    }
                }
            }

            // When Serial# is taken
            if (ZYPConstant.FLG_ON_Y.equals(shpgOrdDtlTMsg.serNumTakeFlg.getValue()) //
                    && SO_PROC_STS.SHIP.equals(shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue())) {

                // Get sum of SHIP_QTY by SO#, Line#
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
                queryParam.put("soNum", shpgOrdConfDtlWrkTMsg.soNum.getValue());
                queryParam.put("soSlpNum", shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());
                queryParam.put("wrkTrxId", shpgOrdConfDtlWrkTMsg.wrkTrxId.getValue());
                int cntDtl = (Integer) ssmBatchClient.queryObject("getShpgOrdConfDtlWrkShipQty", queryParam, new GetShpgOrdConfDtlWrkShipQty());

                // Get data count of SHIP_SER_NUM_WRK by SO#, Line#
                queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
                queryParam.put("soNum", shpgOrdConfDtlWrkTMsg.soNum.getValue());
                queryParam.put("soSlpNum", shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());
                queryParam.put("wrkTrxId", shpgOrdConfDtlWrkTMsg.wrkTrxId.getValue());
                int cntSer = (Integer) ssmBatchClient.queryObject("getShipSerNumWrkCnt", queryParam, new GetShipSerNumWrkCnt());

                // SHIP_QTY <> Serial number count
                if (cntSer != cntDtl) {

                    doErrorProcess(NLZM2032W, shpgOrdConfDtlWrkTMsg);
                }
            }

            return errFlg;
        }

        /**
         * Import SO Conf Detail
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         */
        private void importSoConfDtl(SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg, CPOTMsg cpoTMsg) {

            boolean allShipFlg = false;
            boolean frtRelnFlg = false;

            // ** Set BOL# (BOL# is not set, set PRO# to BOL#)
            if (!ZYPCommonFunc.hasValue(shpgOrdConfDtlWrkTMsg.bolNum)) {

                ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.bolNum, shpgOrdConfDtlWrkTMsg.proNum.getValue());
            }

            // ** Import BOL
            if (ZYPCommonFunc.hasValue(shpgOrdConfDtlWrkTMsg.bolNum)) {

                frtRelnFlg = importBol(shpgOrdConfDtlWrkTMsg);
            }

            // ** Import BOL_RELN
            if (ZYPCommonFunc.hasValue(shpgOrdConfDtlWrkTMsg.bolNum)) {

                importBolReln(shpgOrdConfDtlWrkTMsg);
            }

            // ** Import SO Conf Detail

            // Check if SO Conf Detail is created or not
            SHPG_ORD_CONF_DTLTMsg inShpgOrdConfDtlTMsg = new SHPG_ORD_CONF_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.glblCmpyCd, shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.soNum, shpgOrdConfDtlWrkTMsg.soNum.getValue());
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.soSlpNum, shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.whCd, shpgOrdConfDtlWrkTMsg.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.bolNum, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.bolNum.getValue()));
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.proNum, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.proNum.getValue()));
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.vndCd, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.vndCd.getValue()));
            ZYPEZDItemValueSetter.setValue(inShpgOrdConfDtlTMsg.shipDtTmTs, shpgOrdConfDtlWrkTMsg.shipDtTmTs);
            SHPG_ORD_CONF_DTLTMsg outShpgOrdConfDtlTMsg = (SHPG_ORD_CONF_DTLTMsg) S21ApiTBLAccessor.findByKey(inShpgOrdConfDtlTMsg);

            // Not created
            if (outShpgOrdConfDtlTMsg == null) {

                // Create SO conf detail data
                outShpgOrdConfDtlTMsg = new SHPG_ORD_CONF_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.glblCmpyCd, shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.soNum, shpgOrdConfDtlWrkTMsg.soNum.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.soSlpNum, shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.whCd, shpgOrdConfDtlWrkTMsg.whCd.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.bolNum, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.bolNum.getValue()));
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.proNum, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.proNum.getValue()));
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.vndCd, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.vndCd.getValue()));
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.mdseCd, shpgOrdConfDtlWrkTMsg.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.shipQty, shpgOrdConfDtlWrkTMsg.shipQty.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.totFrtAmt, shpgOrdConfDtlWrkTMsg.totFrtAmt.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.fromStkStsCd, shpgOrdConfDtlWrkTMsg.fromStkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.totShipWt, shpgOrdConfDtlWrkTMsg.totShipWt.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.soDataActTpCd, shpgOrdConfDtlWrkTMsg.soDataActTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.soProcStsCd, shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue());
                /**
                 * QC#10302
                 */
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.shpgSvcLvlCd, shpgOrdConfDtlWrkTMsg.shpgSvcLvlCd);

                if (frtRelnFlg) {

                    ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.frtRelnFlg, ZYPConstant.FLG_ON_Y);

                } else {

                    ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.frtRelnFlg, ZYPConstant.FLG_OFF_N);
                }

                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.shipDtTmTs, shpgOrdConfDtlWrkTMsg.shipDtTmTs.getValue());
                S21ApiTBLAccessor.create(outShpgOrdConfDtlTMsg);

                if (RETURN_CD_PRIMARY_KEY_DUPLICATE.equals(outShpgOrdConfDtlTMsg.getReturnCode())) {

                    // Search again since numeric value must be added
                    outShpgOrdConfDtlTMsg = (SHPG_ORD_CONF_DTLTMsg) S21ApiTBLAccessor.findByKey(inShpgOrdConfDtlTMsg);
                    ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.shipQty, nullToZero(outShpgOrdConfDtlTMsg.shipQty.getValue()).add(shpgOrdConfDtlWrkTMsg.shipQty.getValue()));
                    BigDecimal totShipWt = nullToZero(outShpgOrdConfDtlTMsg.totShipWt.getValue()).add(shpgOrdConfDtlWrkTMsg.totShipWt.getValue());
                    ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.totShipWt, checkMaxLimit(totShipWt, MAX_VALUE_TOT_SHIP_WT));
                    S21ApiTBLAccessor.update(outShpgOrdConfDtlTMsg);
                }

            } else {

                // Update SO conf detail data
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.shipQty, nullToZero(outShpgOrdConfDtlTMsg.shipQty.getValue()).add(shpgOrdConfDtlWrkTMsg.shipQty.getValue()));
                BigDecimal totShipWt = nullToZero(outShpgOrdConfDtlTMsg.totShipWt.getValue()).add(shpgOrdConfDtlWrkTMsg.totShipWt.getValue());
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.totShipWt, checkMaxLimit(totShipWt, MAX_VALUE_TOT_SHIP_WT));
                /**
                 * QC#10302
                 */
                ZYPEZDItemValueSetter.setValue(outShpgOrdConfDtlTMsg.shpgSvcLvlCd, shpgOrdConfDtlWrkTMsg.shpgSvcLvlCd);
                S21ApiTBLAccessor.update(outShpgOrdConfDtlTMsg);

                if (!RETURN_CD_NORMAL_END.equals(outShpgOrdConfDtlTMsg.getReturnCode())) {

                    // DB error
                    doErrorProcess(NLZM2030E, shpgOrdConfDtlWrkTMsg);
                    throw new S21AbendException(NLZM2030E);
                }
            }

            // Update Non-Serialized
            if (!TRX_SRC_TP.WHOLE_SALES.equals(shpgOrdTMsg.trxSrcTpCd.getValue()) && !SCE_ORD_TP.CONFIG_CHANGE.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {

                // Get SO detail
                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getSoDetail(shpgOrdConfDtlWrkTMsg.soSlpNum.getValue(), shpgOrdConfDtlWrkTMsg);

                if (ZYPConstant.FLG_OFF_N.equals(shpgOrdDtlTMsg.serNumTakeFlg.getValue())) {

                    // Get Mdse
                    MDSETMsg mdseTMsg = new MDSETMsg();
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, shpgOrdDtlTMsg.mdseCd);
                    mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

                    if (mdseTMsg != null && ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

                        RTL_WHTMsg shipRtlWhTMsg = getRtlWh(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.whCd.getValue());
                        boolean isAssetWh = isAsset(shipRtlWhTMsg);

                        // Get Machine Master info
                        // START 2021/10/06 A.Marte [QC#59155, MOD]
                        //ArrayList<MachineMasterResultBean> errMsg = MachMstrApiEvent.callMachineMasterUpdateForNonSerial(shpgOrdTMsg, shpgOrdDtlTMsg, outShpgOrdConfDtlTMsg, shpgOrdConfDtlWrkTMsg.shipQty.getValue(),
                        //        isAssetWh, onBatch, ssmBatchClient);
                        ArrayList<MachineMasterResultBean> errMsg = MachMstrApiEvent.callMachineMasterUpdateForNonSerial(shpgOrdTMsg, shpgOrdDtlTMsg, outShpgOrdConfDtlTMsg, shpgOrdConfDtlWrkTMsg.shipQty.getValue(),
                                isAssetWh, onBatch, ssmBatchClient);
                        // END 2021/10/06 A.Marte [QC#59155, MOD]

                        if (!errMsg.isEmpty()) {

                            for (MachineMasterResultBean msgBean : errMsg) {

                                if (ZYPCommonFunc.hasValue(msgBean.getMsgId())) {

                                    doErrorProcess(msgBean.getMsgId(), "Machine Master API Error");
                                }

                                if (ZYPCommonFunc.hasValue(msgBean.getAssetErrMsgId())) {

                                    doErrorProcess(msgBean.getAssetErrMsgId(), "Asset Staging API Error");
                                }
                            }
                        }
                    }
                }
            }

            // ** Update SO Detail

            // Add SHIP_QTY to that of SO Detail
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getShpgOrdDtl(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfDtlWrkTMsg.soNum.getValue(), shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());

            // No data (impossible)
            if (shpgOrdDtlTMsg == null) {

                // DB error
                doErrorProcess(NLZM2030E, shpgOrdConfDtlWrkTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shipQty, nullToZero(shpgOrdDtlTMsg.shipQty.getValue()).add(shpgOrdConfDtlWrkTMsg.shipQty.getValue()));
            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                // DB error
                doErrorProcess(NLZM2030E, shpgOrdConfDtlWrkTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            // SHPG_QTY <= SHIP_QTY
            if (shpgOrdDtlTMsg.shpgQty.getValue().compareTo(shpgOrdDtlTMsg.shipQty.getValue()) <= 0) {

                // All items are shipped
                allShipFlg = true;
            }

            // All items are shipped
            if (allShipFlg) {

                // Set Component or Config Component
                if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.setMdseCd) || ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk)) {

                    List<SHPG_ORD_DTLTMsg> shpgOrdDtlTMsgList = new ArrayList<SHPG_ORD_DTLTMsg>();

                    shpgOrdDtlTMsgList.add(shpgOrdDtlTMsg);

                    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

                    HashMap<String, Object> queryParam = new HashMap<String, Object>();
                    queryParam.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
                    queryParam.put("soNum", shpgOrdDtlTMsg.soNum.getValue());
                    queryParam.put("soSlpNum", shpgOrdDtlTMsg.soSlpNum.getValue());

                    if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.setMdseCd)) {

                        queryParam.put("trxHdrNum", shpgOrdDtlTMsg.trxHdrNum.getValue());
                        queryParam.put("trxLineNum", shpgOrdDtlTMsg.trxLineNum.getValue());
                        queryParam.put("setMdseCd", shpgOrdDtlTMsg.setMdseCd.getValue());

                        result = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getShpgOrdDtlSetLine", queryParam);

                    } else if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk)) {

                        queryParam.put("svcConfigMstrPk", shpgOrdDtlTMsg.pickSvcConfigMstrPk.getValue());

                        result = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getShpgOrdDtlConfigLine", queryParam);
                    }

                    for (Map<String, Object> rs : result) {

                        // Skip SO Line already shipped
                        if (ZYPConstant.FLG_ON_Y.equals((String) rs.get("SHIP_FLG"))) {

                            continue;
                        }

                        SHPG_ORD_DTLTMsg compShpgOrdDtlTMsg = getShpgOrdDtl(shpgOrdDtlTMsg.glblCmpyCd.getValue(), (String) rs.get("SO_NUM"), (String) rs.get("SO_SLP_NUM"));

                        // Set Component Ship Qty Check
                        if (chkSetItemShip(compShpgOrdDtlTMsg, null)) {

                            allShipFlg = false;
                            break;
                        }

                        shpgOrdDtlTMsgList.add(compShpgOrdDtlTMsg);

                        // Config Component Ship Qty Check
                        if (chkConfigItemShip(compShpgOrdDtlTMsg, null)) {

                            allShipFlg = false;
                            break;
                        }

                    }

                    if (allShipFlg) {

                        for (SHPG_ORD_DTLTMsg compShpgOrdDtlTMsg : shpgOrdDtlTMsgList) {

                            // Line Close
                            executeLineClose(compShpgOrdDtlTMsg, cpoTMsg);
                        }
                    }

                } else {

                    // Line Close
                    executeLineClose(shpgOrdDtlTMsg, cpoTMsg);
                }

            } else {

                // Update SHPG_ORD_DTL
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.PARTIALLY_SHIPPED);
                S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

                if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                    // DB error
                    doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                    throw new S21AbendException(NLZM2030E);
                }

                // PR Update API
                if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineSubNum)) {

                    callPRUpdateAPI(PRUA_UPDATE, shpgOrdDtlTMsg, outShpgOrdConfDtlTMsg, null);
                }
            }
        }

        /**
         * executeLineClose
         * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
         * @param cpoTMsg CPOTMsg
         */
        private void executeLineClose(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, CPOTMsg cpoTMsg) {

            // Update SHPG_ORD_DTL
            if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.INSHED);

            } else {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.SHIPPED);
            }

            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                // DB error
                doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            // Update SHPG_ORD_DTL
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.SHIPPED);
            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                // DB error
                doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            // Get SHPG_ORD_CONF_DTL
            List<SHPG_ORD_CONF_DTLTMsg> shpgOrdConfDtlList = getShpgOrdConfDtlList(shpgOrdDtlTMsg.glblCmpyCd.getValue(), shpgOrdDtlTMsg.soNum.getValue(), shpgOrdDtlTMsg.soSlpNum.getValue(), null, null);

            if (isShpgModeInshed(shpgOrdDtlTMsg)) {

                // Call by BOL#, among all SO conf detail
                for (SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg : shpgOrdConfDtlList) {

                    callShippingPlanUpdateAPI(SHPG_MODE_INSHED, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, this.shpgOrdTMsg);
                    callInventoryUpdateAPI(this.param, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg, cpoTMsg);
                }

            } else {

                // Call by BOL#, among all SO conf detail
                for (SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg : shpgOrdConfDtlList) {

                    // Call Shipping Plan Update API
                    callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, this.shpgOrdTMsg);
                    callInventoryUpdateAPI(this.param, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg, cpoTMsg);
                }
            }

            // Inventory Order API
            if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue()) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())
                    || SCE_ORD_TP.DISPOSAL.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())) {

                callInventoryOrder(IOA_CLOSED, shpgOrdDtlTMsg);
            }

            // PR Update API
            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineSubNum)) {

                for (SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg : shpgOrdConfDtlList) {

                    callPRUpdateAPI(PRUA_UPDATE, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
                }
            }
        }

        /**
         * chkConfigOrSetItemShip
         * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         * @return true: not All Ship / false: all ship
         */
        private boolean chkSetItemShip(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) {

            // Set Item
            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.setMdseCd)) {

                BigDecimal shipQty = shpgOrdDtlTMsg.shipQty.getValue();

                if (shpgOrdConfDtlWrkTMsg != null) {

                    shipQty = shipQty.add(shpgOrdConfDtlWrkTMsg.shipQty.getValue());
                }

                if (shpgOrdDtlTMsg.shpgQty.getValue().compareTo(shipQty) != 0) {

                    return true;
                }
            }

            return false;
        }

        /**
         * chkConfigItemShip
         * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         * @return true: not All Ship / false: all ship
         */
        private boolean chkConfigItemShip(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) {

            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk)) {

                BigDecimal shipQty = shpgOrdDtlTMsg.shipQty.getValue();

                if (shpgOrdConfDtlWrkTMsg != null) {

                    shipQty = shipQty.add(shpgOrdConfDtlWrkTMsg.shipQty.getValue());
                }

                if (shpgOrdDtlTMsg.shpgQty.getValue().compareTo(shipQty) != 0) {

                    return true;
                }
            }

            return false;
        }
        
        /**
         * chkConfigQty
         * @param Map<String, Object> configMap
         * @return true: not All Ship / false: all ship
         */
        private boolean chkConfigQty(Map<String, Object> configMap) {

            BigDecimal totShpgQty = (BigDecimal) configMap.get("TOT_SHPG_QTY");
            BigDecimal totShipQty = (BigDecimal) configMap.get("TOT_SHIP_QTY");
            
            if (BigDecimal.ZERO.compareTo(totShipQty) != 0 && totShpgQty.compareTo(totShipQty) != 0) {

                return true;
            }

            return false;
        }

        /**
         * inEachQty
         * @param soDtl SHPG_ORD_DTLTMsg
         * @return true: UOM ship OK false: NG
         */
        private boolean checkUOMItemShip(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg) {

            HashMap<String, String> confParam = new HashMap<String, String>();
            confParam.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
            confParam.put("shpgPlnNum", shpgOrdDtlTMsg.shpgPlnNum.getValue());

            BigDecimal inEachQty = (BigDecimal) ssmBatchClient.queryObject("getInEachQty", confParam);

            if (ZYPCommonFunc.hasValue(inEachQty) && !BigDecimal.ZERO.equals(shpgOrdDtlTMsg.shipQty.getValue())) {

                if (!shpgOrdDtlTMsg.shipQty.getValue().remainder(inEachQty).equals(BigDecimal.ZERO)) {

                    return true;
                }
            }

            return false;
        }

        /**
         * Import BOL
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         * @return boolean
         */
        private boolean importBol(SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) {

            boolean frtRelnFlg = false;

            // Get Retail WH CD
            String rtlWhCd = getRtlWhCdFromRtlSwh(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfDtlWrkTMsg.whCd.getValue());

            // QC#17029 Add.
            if (!ZYPCommonFunc.hasValue(rtlWhCd)) {

                doErrorProcess(NLZM2503E, shpgOrdConfDtlWrkTMsg.whCd.getValue());
                return false;
            }

            // Check if same BOL# data is created or not
            BOLTMsg bolTMsg = new BOLTMsg();
            ZYPEZDItemValueSetter.setValue(bolTMsg.glblCmpyCd, shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(bolTMsg.whCd, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(bolTMsg.bolNum, shpgOrdConfDtlWrkTMsg.bolNum.getValue());
            bolTMsg = (BOLTMsg) S21ApiTBLAccessor.findByKey(bolTMsg);

            // Process only when data is not created
            if (bolTMsg == null) {

                // Create BOL data
                bolTMsg = new BOLTMsg();
                ZYPEZDItemValueSetter.setValue(bolTMsg.glblCmpyCd, shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(bolTMsg.whCd, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(bolTMsg.bolNum, shpgOrdConfDtlWrkTMsg.bolNum.getValue());

                if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

                    ZYPEZDItemValueSetter.setValue(bolTMsg.shpgStsCd, SHPG_STS.INSHED);

                } else {

                    ZYPEZDItemValueSetter.setValue(bolTMsg.shpgStsCd, SHPG_STS.SHIPPED);
                }

                S21ApiTBLAccessor.insert(bolTMsg);

                if (RETURN_CD_PRIMARY_KEY_DUPLICATE.equals(bolTMsg.getReturnCode())) {

                    // Continue when duplicate primary key error

                } else if (FRT_CHRG_TO.CUSTOMER.equals(shpgOrdTMsg.frtChrgToCd.getValue())) {

                    frtRelnFlg = true;
                }
            }

            return frtRelnFlg;
        }

        /**
         * Import BOL_RELN
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         */
        private void importBolReln(SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg) {

            // Get Retail WH CD
            String rtlWhCd = getRtlWhCdFromRtlSwh(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfDtlWrkTMsg.whCd.getValue());

            // QC#17029 Add.
            if (!ZYPCommonFunc.hasValue(rtlWhCd)) {

                doErrorProcess(NLZM2503E, shpgOrdConfDtlWrkTMsg.whCd.getValue());
                return;
            }

            // Check if same BOL#, PRO# data is created or not
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
            queryParam.put("whCd", rtlWhCd);
            queryParam.put("bolNum", shpgOrdConfDtlWrkTMsg.bolNum.getValue());
            queryParam.put("proNum", shpgOrdConfDtlWrkTMsg.proNum.getValue());
            queryParam.put("carrCd", shpgOrdConfDtlWrkTMsg.vndCd.getValue());
            Integer cnt = (Integer) ssmBatchClient.queryObject("getBolRelenCnt", queryParam, new GetBolRelenCnt());

            // Process only when data is not created
            if (cnt.intValue() == 0) {

                // Get max value of BOL_SQ_NUM
                queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
                queryParam.put("whCd", rtlWhCd);
                queryParam.put("bolNum", shpgOrdConfDtlWrkTMsg.bolNum.getValue());
                Integer maxBolSqNum = (Integer) ssmBatchClient.queryObject("getBolRelenMaxSqNum", queryParam, new GetBolRelenMaxSqNum());

                // Create BOL_RELN data
                BOL_RELNTMsg bolReln = new BOL_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(bolReln.glblCmpyCd, shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(bolReln.whCd, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(bolReln.bolNum, shpgOrdConfDtlWrkTMsg.bolNum.getValue());
                ZYPEZDItemValueSetter.setValue(bolReln.bolSqNum, String.valueOf(maxBolSqNum.intValue() + 1));
                ZYPEZDItemValueSetter.setValue(bolReln.carrCd, shpgOrdConfDtlWrkTMsg.vndCd.getValue());
                ZYPEZDItemValueSetter.setValue(bolReln.proNum, shpgOrdConfDtlWrkTMsg.proNum.getValue());
                S21ApiTBLAccessor.create(bolReln);

                if (RETURN_CD_PRIMARY_KEY_DUPLICATE.equals(bolReln.getReturnCode())) {

                    // Continue when duplicate primary key error
                }
            }
        }

        /**
         * Import Line Cancel Conf Detail
         * @param shpgOrdConfDtlWrkTMsg SHPG_ORD_CONF_DTL_WRKTMsg
         * @param cpoTMsg CPOTMsg
         */
        private void importLineCancelDtl(SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg, CPOTMsg cpoTMsg) {

            // Create Cancel SO conf detail data
            SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg = new SHPG_ORD_CONF_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.glblCmpyCd, shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.soNum, shpgOrdConfDtlWrkTMsg.soNum.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.soSlpNum, shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.whCd, shpgOrdConfDtlWrkTMsg.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.bolNum, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.bolNum.getValue()));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.proNum, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.proNum.getValue()));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.vndCd, emptyToAsterisk(shpgOrdConfDtlWrkTMsg.vndCd.getValue()));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.mdseCd, shpgOrdConfDtlWrkTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.shipQty, shpgOrdConfDtlWrkTMsg.shipQty.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.totFrtAmt, shpgOrdConfDtlWrkTMsg.totFrtAmt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.fromStkStsCd, shpgOrdConfDtlWrkTMsg.fromStkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.totShipWt, shpgOrdConfDtlWrkTMsg.totShipWt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.soDataActTpCd, shpgOrdConfDtlWrkTMsg.soDataActTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.soProcStsCd, shpgOrdConfDtlWrkTMsg.soProcStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.shipDtTmTs, shpgOrdConfDtlWrkTMsg.shipDtTmTs.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.frtRelnFlg, ZYPConstant.FLG_OFF_N);
            /**
             * QC#10302
             */
            ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.shpgSvcLvlCd, shpgOrdConfDtlWrkTMsg.shpgSvcLvlCd);

            S21ApiTBLAccessor.create(shpgOrdConfDtlTMsg);

            if (RETURN_CD_PRIMARY_KEY_DUPLICATE.equals(shpgOrdConfDtlTMsg.getReturnCode())) {

                SHPG_ORD_CONF_DTLTMsg updTMsg = (SHPG_ORD_CONF_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdConfDtlTMsg);

                if (updTMsg != null && !SO_PROC_STS.SHIP.equals(updTMsg.soProcStsCd.getValue())) {
                    S21ApiTBLAccessor.update(shpgOrdConfDtlTMsg);
                }
            }

            // Get SHPG_ORD_DTL
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getShpgOrdDtl(shpgOrdConfDtlWrkTMsg.glblCmpyCd.getValue(), shpgOrdConfDtlWrkTMsg.soNum.getValue(), shpgOrdConfDtlWrkTMsg.soSlpNum.getValue());

            // No data (impossible)
            if (shpgOrdDtlTMsg == null) {

                doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            String glblCmpyCd = shpgOrdDtlTMsg.glblCmpyCd.getValue();
            String soNum = shpgOrdDtlTMsg.soNum.getValue();
            String soSlpNum = shpgOrdDtlTMsg.soSlpNum.getValue();

            boolean isAllCancel = true;

            // Line Partial Ship
            if (shpgOrdDtlTMsg.shipQty.getValue().compareTo(BigDecimal.ZERO) > 0) {

                isAllCancel = false;

                if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.INSHED);

                } else {

                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.SHIPPED);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.S_OR_O_CANCELLED);
            }

            // Update SHPG_ORD_DTL
            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            if (isAllCancel) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.CANCELLED);

            } else {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.SHIPPED);
            }

            // Update SHPG_ORD_DTL
            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            List<SHPG_ORD_CONF_DTLTMsg> shipShpgOrdConfDtlTMsgList = new ArrayList<SHPG_ORD_CONF_DTLTMsg>();

            // Call Shipping Plan Update API
            if (isAllCancel) {

                callShippingPlanUpdateAPI(SHPG_MODE_HARDALLOCATED_SOCANCELLED, shpgOrdDtlTMsg, null, null);

            } else {

                shipShpgOrdConfDtlTMsgList = getShpgOrdConfDtlList(glblCmpyCd, soNum, soSlpNum, null, SO_PROC_STS.SHIP);
                callShippingPlanUpdateAPI(SHPG_MODE_PARTIAL_SHIP, shpgOrdDtlTMsg, shipShpgOrdConfDtlTMsgList.get(0), this.shpgOrdTMsg);
            }

//            // Call DO API(Line Cancel)
//            if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {
//
//                callDoAPI(shpgOrdTMsg, shpgOrdDtlTMsg, DO_MODIFY);
//            }

            // ** PR Update API
            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineSubNum)) {

                for (SHPG_ORD_CONF_DTLTMsg shipShpgOrdConfDtlTMsg : shipShpgOrdConfDtlTMsgList) {

                    callPRUpdateAPI(PRUA_UPDATE, shpgOrdDtlTMsg, shipShpgOrdConfDtlTMsg, null);
                }

                BigDecimal cancelQty = shpgOrdDtlTMsg.shpgQty.getValue().subtract(shpgOrdDtlTMsg.shipQty.getValue());

                if (cancelQty.intValue() > 0) {
                    // QC#14190
                    if (!sceOrdTpCd.equals(SCE_ORD_TP.TRIAL_FOR_USE) //
                            && !sceOrdTpCd.equals(SCE_ORD_TP.DIRECT_SALES) //
                            && !sceOrdTpCd.equals(SCE_ORD_TP.DC_TRANSFER)) {
                        callPRUpdateAPI(PRUA_CANCEL, shpgOrdDtlTMsg, null, cancelQty);
                    }
                }
            }

            // ** Inventory Order Update
            if (SCE_ORD_TP.TECH_REQUEST.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue()) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())
                    || SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue()) || SCE_ORD_TP.DISPOSAL.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())) {

                if (isAllCancel) {

                    if (chkShipQty(shpgOrdDtlTMsg)) {

                        callInventoryOrder(IOA_CLOSED, shpgOrdDtlTMsg);

                    } else {

                        callInventoryOrder(IOA_CANCEL, shpgOrdDtlTMsg);
                    }

                } else {

                    callInventoryOrder(IOA_CLOSED, shpgOrdDtlTMsg);
                }
            }

            // ** Machine Master Update API
            ArrayList<MachineMasterResultBean> errMsg = MachMstrApiEvent.callMachineMasterUpdateForCancel(shpgOrdDtlTMsg, false, onBatch, ssmBatchClient, sceOrdTpCd);

            if (!errMsg.isEmpty()) {

                for (MachineMasterResultBean msgBean : errMsg) {

                    doErrorProcess(msgBean.getMsgId(), "Machine Master API Error");
                }
            }

            // ** Inventory Update API
            for (SHPG_ORD_CONF_DTLTMsg shipShpgOrdConfDtlTMsg : shipShpgOrdConfDtlTMsgList) {

                callInventoryUpdateAPI(this.param, shpgOrdDtlTMsg, shipShpgOrdConfDtlTMsg, shpgOrdTMsg, cpoTMsg);
            }
        }

        /**
         * Import Line Cancel Conf Detail
         * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
         * @param shipDtTmTs String
         */
        private void importLineCancelDtl(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, String shipDtTmTs) {

            // ** Update SO detail (Line Cancel)
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.S_OR_O_CANCELLED);
            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                // DB error
                doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            // Call Shipping Plan Update API
            callShippingPlanUpdateAPI(SHPG_MODE_HARDALLOCATED_SOCANCELLED, shpgOrdDtlTMsg, null, null);

//            if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {
//
//                // Call DO API(Line Cancel)
//                callDoAPI(shpgOrdTMsg, shpgOrdDtlTMsg, DO_MODIFY);
//            }

            // ** update SHPG_ORD_DTL (Line Cancel)
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.CANCELLED);
            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                // DB error
                doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            // ** PR Update API
            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineSubNum)) {
                // QC#14190
                if (!sceOrdTpCd.equals(SCE_ORD_TP.TRIAL_FOR_USE) && !sceOrdTpCd.equals(SCE_ORD_TP.DIRECT_SALES) && !sceOrdTpCd.equals(SCE_ORD_TP.DC_TRANSFER)) {

                    callPRUpdateAPI(PRUA_CANCEL, shpgOrdDtlTMsg, null, shpgOrdDtlTMsg.shpgQty.getValue());
                }
            }

            // ** Inventory Order Update
            if (SCE_ORD_TP.TECH_REQUEST.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue()) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())
                    || SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue()) || SCE_ORD_TP.DISPOSAL.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())) {

                if (chkShipQty(shpgOrdDtlTMsg)) {

                    callInventoryOrder(IOA_CLOSED, shpgOrdDtlTMsg);

                } else {

                    callInventoryOrder(IOA_CANCEL, shpgOrdDtlTMsg);
                }
            }

            // ** Machine Master Update API
            ArrayList<MachineMasterResultBean> errMsg = MachMstrApiEvent.callMachineMasterUpdateForCancel(shpgOrdDtlTMsg, true, onBatch, ssmBatchClient, sceOrdTpCd);

            if (!errMsg.isEmpty()) {

                for (MachineMasterResultBean msgBean : errMsg) {

                    doErrorProcess(msgBean.getMsgId(), "Machine Master API Error");
                }
            }
        }

        /**
         * chkShipQty
         * @param sod SHPG_ORD_DTLTMsg
         * @return true:Ship QTY > 0 false: Ship QTY <= 0
         */
        private boolean chkShipQty(SHPG_ORD_DTLTMsg sod) {

            SHPG_ORD_DTLTMsg searchConfig = new SHPG_ORD_DTLTMsg();

            searchConfig.setSQLID("005");
            searchConfig.setConditionValue("glblCmpyCd01", sod.glblCmpyCd.getValue());
            searchConfig.setConditionValue("trxHdrNum01", sod.trxHdrNum.getValue());
            searchConfig.setConditionValue("trxLineNum01", sod.trxLineNum.getValue());
            searchConfig.setConditionValue("shipQty01", "0");

            if (S21ApiTBLAccessor.count(searchConfig) > 0) {

                return true;
            }

            return false;
        }

        /**
         * reClose
         * @param param
         * @param ssmBatchClient
         * @return true:Success false:Critical Error
         */
        public boolean reClose(NLZC211001PMsg param) {
            
            // QC#63431 MOD START
            HashMap<String, Object> queryParamConfigQty = new HashMap<String, Object>();
            queryParamConfigQty.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParamConfigQty.put("soNum", param.soNum.getValue());
            
            List<Map<String, Object>> configQtyList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getConfigQty", queryParamConfigQty);
            
            if (configQtyList != null && configQtyList.size() != 0) {
                for (Map<String, Object> configMap : configQtyList) {
                    
                    // check config qty
                    if (chkConfigQty(configMap)) {
                        doErrorProcess(NLZM2326E, "All components of the configuration are not shipped.[SO#" + shpgOrdTMsg.soNum.getValue() + "]");
                        return false;
                    }
                }
            }
            // QC#63431 MOD END

            HashMap<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("soNum", param.soNum.getValue());
            queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
            queryParam.put("flgN", ZYPConstant.FLG_OFF_N);

            List<Map<String, Object>> openShpgOrdDtlList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getShpgOrdDtlReClose", queryParam);

            for (Map<String, Object> openShpgOrdDtlMap : openShpgOrdDtlList) {

                String glblCmpyCd = param.glblCmpyCd.getValue();
                String soNum = (String) openShpgOrdDtlMap.get("SO_NUM");
                String soSlpNum = (String) openShpgOrdDtlMap.get("SO_SLP_NUM");

                // Get SHPG_ORD_DTL
                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getShpgOrdDtl(glblCmpyCd, soNum, soSlpNum);

                if (shpgOrdDtlTMsg == null) {

                    doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                    throw new S21AbendException(NLZM2030E);
                }

// QC#63431 DEL START
//                // Check Config Item
//                if (chkConfigItemShip(shpgOrdDtlTMsg, null)) {
//
//                    doErrorProcess(NLZM2326E, "All components of the configuration are not shipped.[SO#" + shpgOrdTMsg.soNum.getValue() + "]");
//                    return false;
//                }
// QC#63431 DEL END

                // Check Set Item
                if (chkSetItemShip(shpgOrdDtlTMsg, null)) {

                    doErrorProcess(NLZM2327E, "All components of the set item are not shipped.[SO#" + shpgOrdTMsg.soNum.getValue() + "]");
                    return false;
                }

                // Check UOM Item
                if (checkUOMItemShip(shpgOrdDtlTMsg)) {

                    doErrorProcess(NLZM2328E, "Shipped quantity does not match each quantity of UOM specified by order.[SO#" + shpgOrdTMsg.soNum.getValue() + "]");
                    return false;
                }

                BigDecimal cancelQty = shpgOrdDtlTMsg.shpgQty.getValue().subtract(shpgOrdDtlTMsg.shipQty.getValue());

                // Create SO conf detail data
                if (shpgOrdDtlTMsg.shipQty.getValue().intValue() == 0) {

                    SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg = new SHPG_ORD_CONF_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.soNum, soNum);
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.soSlpNum, soSlpNum);
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.whCd, shpgOrdDtlTMsg.invtyLocCd.getValue());
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.bolNum, "*");
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.proNum, "*");
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.vndCd, "*");
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.mdseCd, shpgOrdDtlTMsg.mdseCd.getValue());
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.shipQty, cancelQty);
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.totFrtAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.fromStkStsCd, shpgOrdDtlTMsg.fromStkStsCd.getValue());
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.totShipWt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.soProcStsCd, SO_PROC_STS.LINE_VOID);
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.shipDtTmTs, (String) openShpgOrdDtlMap.get("SHIP_DT_TM_TS"));
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlTMsg.frtRelnFlg, ZYPConstant.FLG_OFF_N);
                    S21ApiTBLAccessor.create(shpgOrdConfDtlTMsg);

                    if (RETURN_CD_PRIMARY_KEY_DUPLICATE.equals(shpgOrdConfDtlTMsg.getReturnCode())) {

                        S21ApiTBLAccessor.update(shpgOrdConfDtlTMsg);
                    }
                }

                // Line Partial Ship
                if (cancelQty.intValue() > 0 && shpgOrdDtlTMsg.shipQty.getValue().intValue() > 0) {

                    // Line Partial Check
                    if (doesExistDsCondConst(glblCmpyCd, this.shpgOrdTMsg.sceOrdTpCd.getValue(), null, null, null, null, ZYPConstant.FLG_OFF_N)) {

                        doErrorProcess(NLZM2048E, "ShipQty and OrdQty does not match.[SO#" + shpgOrdTMsg.soNum.getValue() + "]");
                        return false;
                    }

                    // ** Line Ship Method
                    CPOTMsg cpoTMsg = null;

                    if (SCE_ORD_TP.EXPORT.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {

                        List<CPOTMsg> cpoList = (List<CPOTMsg>) ssmBatchClient.queryObjectList("getCpoInfo", shpgOrdTMsg);

                        if (!cpoList.isEmpty()) {

                            cpoTMsg = cpoList.get(0);
                        }
                    }

                    // Update status for SHPG_ORD_DTL
                    if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.INSHED);

                    } else {

                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.SHIPPED);
                    }

                    S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

                    if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                        doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                        throw new S21AbendException(NLZM2030E);
                    }

                    // Update status for SHPG_ORD_DTL
                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.SHIPPED);
                    S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

                    if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                        doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                        throw new S21AbendException(NLZM2030E);
                    }

                    // Get Shipping Conf Detail
                    List<SHPG_ORD_CONF_DTLTMsg> shipShpgOrdConfDtlTMsgList = getShpgOrdConfDtlList(glblCmpyCd, soNum, soSlpNum, null, SO_PROC_STS.SHIP);

                    // Shipping Plan Update API
                    callShippingPlanUpdateAPI(SHPG_MODE_PARTIAL_SHIP, shpgOrdDtlTMsg, shipShpgOrdConfDtlTMsgList.get(0), this.shpgOrdTMsg);

                    // Inventory Update API
                    for (SHPG_ORD_CONF_DTLTMsg shipShpgOrdConfDtlTMsg : shipShpgOrdConfDtlTMsgList) {

                        callInventoryUpdateAPI(this.param, shpgOrdDtlTMsg, shipShpgOrdConfDtlTMsg, shpgOrdTMsg, cpoTMsg);
                    }

                    // Inventory Order API
                    if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue()) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())
                            || SCE_ORD_TP.DISPOSAL.equals(this.shpgOrdTMsg.sceOrdTpCd.getValue())) {

                        callInventoryOrder(IOA_CLOSED, shpgOrdDtlTMsg);
                    }

                    // PR Update API
                    if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineSubNum)) {

                        for (SHPG_ORD_CONF_DTLTMsg shipShpgOrdConfDtlTMsg : shipShpgOrdConfDtlTMsgList) {

                            callPRUpdateAPI(PRUA_UPDATE, shpgOrdDtlTMsg, shipShpgOrdConfDtlTMsg, null);
                        }

                        if (cancelQty.intValue() > 0) {

                            callPRUpdateAPI(PRUA_CANCEL, shpgOrdDtlTMsg, null, cancelQty);
                        }
                    }

                } else {

                    // Line Cancel Method
                    // Line Ship Check
                    if (doesExistDsCondConst(glblCmpyCd, this.shpgOrdTMsg.sceOrdTpCd.getValue(), null, null, null, ZYPConstant.FLG_OFF_N, null)) {

                        doErrorProcess(NLZM2329E, "Line ship needs all item shipped.");
                        return false;
                    }

                    importLineCancelDtl(shpgOrdDtlTMsg, (String) openShpgOrdDtlMap.get("SHIP_DT_TM_TS"));
                }
            }

            return true;
        }
    }

    /**
     * Serial# Work Process
     */
    private class ShipSerNumWrkProcess extends S21SsmBooleanResultSetHandlerSupport {

        /** SHPG_ORDTMsg */
        private SHPG_ORDTMsg shpgOrdTMsg;

        // Store key:MDSE_CD value:SER_NUM_ERR Table 1 Record
        /** */
        Map<String, SER_NUM_ERRTMsg> serErrRec = new HashMap<String, SER_NUM_ERRTMsg>();

        public ShipSerNumWrkProcess(SHPG_ORDTMsg shpgOrdTMsg) {

            this.shpgOrdTMsg = shpgOrdTMsg;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean errFlg = false;
            SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg = new SHIP_SER_NUM_WRKTMsg();

            while (rs.next()) {

                // Copy to TMsg
                shipSerNumWrkTMsg = copySerNumWrkToTMsg(rs, shipSerNumWrkTMsg);

                // get Same MDSE record in the store map.
                SER_NUM_ERRTMsg serNumErrTMsg = getStoredSerNumTMsgByKey(shipSerNumWrkTMsg);

                // Check Serial# work data (When check result is NG, set Warning and continue as normal end)
                errFlg = checkSerNumWrk(shipSerNumWrkTMsg, serNumErrTMsg);

                String glblCmpyCd = shipSerNumWrkTMsg.glblCmpyCd.getValue();
                String soNum = shipSerNumWrkTMsg.soNum.getValue();
                String soSlpNum = shipSerNumWrkTMsg.soSlpNum.getValue();
                String mdseCd = shipSerNumWrkTMsg.mdseCd.getValue();

                // Import Serial# work data
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("soNum", soNum);
                queryParam.put("mdseCd", mdseCd);
                ImportShipSerNum imShipSerNum = new ImportShipSerNum(shipSerNumWrkTMsg);
                ssmBatchClient.queryObject("getMaxSoSerId", queryParam, imShipSerNum);

                ArrayList<SHIP_SER_NUMTMsg> shipSerNumList = imShipSerNum.getShipSerNumList();

                if (!shipSerNumList.isEmpty()) {

                    // ** call API
                    // Get SO detail
                    SHPG_ORD_DTLTMsg soDtl = getShpgOrdDtl(glblCmpyCd, soNum, soSlpNum);

                    // Get SHPG_ORD_CONF_DTL
                    List<SHPG_ORD_CONF_DTLTMsg> socdList = getShpgOrdConfDtlList(glblCmpyCd, soNum, soSlpNum, mdseCd, null);

                    // Serial Transaction Error code && Machine Master status
                    String mmStatus = MachMstrApiEvent.checkMachineMasterAPIForSerial(soDtl, socdList, shipSerNumWrkTMsg);
                    String[] serTrxErr = MachMstrApiEvent.checkSerialTrxError(mmStatus);

                    // Serial Transaction API
// 2017/01/27 QC#17338 
                    //if (SHPG_STS.SHIPPED.equals(soDtl.shpgStsCd.getValue())) {

                        if (!callSerialTransactionAPI(shipSerNumWrkTMsg, soDtl, serTrxErr)) {

                            setOthErrCnt(serNumErrTMsg);
                        }
                    //}

                    // Machine Master API
// 2017/01/27 QC#17338 
                    //if (SHPG_STS.SHIPPED.equals(soDtl.shpgStsCd.getValue())) {

                        RTL_WHTMsg shipRtlWhTMsg = getRtlWh(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.whCd.getValue());
                        boolean isAssetWh = isAsset(shipRtlWhTMsg);

                        ArrayList<MachineMasterResultBean> errMsg = MachMstrApiEvent.callMachineMasterUpdateForSerial(shpgOrdTMsg, soDtl, socdList.get(0), shipSerNumWrkTMsg, mmStatus, shipSerNumList.get(0).serNum.getValue(),
                                isAssetWh, onBatch);

                        if (!errMsg.isEmpty()) {

                            for (MachineMasterResultBean msgBean : errMsg) {

                                if (ZYPCommonFunc.hasValue(msgBean.getMsgId())) {

                                    doErrorProcess(msgBean.getMsgId(), "Machine Master API Error");
                                }

                                if (ZYPCommonFunc.hasValue(msgBean.getAssetErrMsgId())) {

                                    doErrorProcess(msgBean.getAssetErrMsgId(), "Asset Staging API Error");
                                }
                            }

                            setOthErrCnt(serNumErrTMsg);
                        }
                    //}
                }

                serErrRec.put(shipSerNumWrkTMsg.mdseCd.getValue(), serNumErrTMsg);

                cntSerNumWrk++;
            }

            // Update Serial# Error Table
            updateSerialError();
            return errFlg;
        }

        /**
         * callSerialTransactionAPI
         * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
         * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
         * @param serTrxErr String return true:Success false:error
         */
        private boolean callSerialTransactionAPI(SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, String[] serTrxErr) {

            // Set Param
            NLZC302001PMsg pMsg = new NLZC302001PMsg();

            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shipSerNumWrkTMsg.glblCmpyCd.getValue());

            // 1 record Only
            pMsg.UpdateDetailList.setValidCount(1);
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serNum, shipSerNumWrkTMsg.serNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).mdseCd, shipSerNumWrkTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxTs, shipSerNumWrkTMsg.serTakeDtTmTs.getValue());

            if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

                if (isNonCSAAsset(getRtlWh(shipSerNumWrkTMsg.glblCmpyCd.getValue(), shipSerNumWrkTMsg.whCd.getValue()))) {

                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.OFF_THE_BOOK_STOCK_OUT);

                } else {

                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.SHIPMENT);
                }

            } else if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.WH_TRANSFER_STOCK_OUT);

            } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.VENDOR_RETURN);

            } else if (SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.DISPOSAL);

            } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.KITTING_STOCK_OUT);

            } else if (SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.REFURBISH_STOCK_OUT);

            } else if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.ITEM_CHANGE_STOCK_OUT);
            }

            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxSrcTpCd, SER_TRX_SRC_TP.SO);
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxSrcHdrNum, shpgOrdDtlTMsg.soNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxSrcLineNum, shpgOrdDtlTMsg.soSlpNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxRefNum, shpgOrdDtlTMsg.trxHdrNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).fromLocCd, shipSerNumWrkTMsg.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).toLocCd, shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).manCratFlg, ZYPConstant.FLG_OFF_N);

            if (serTrxErr != null) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serErrStsCd, serTrxErr[0]);
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).serTrxCmntTxt, serTrxErr[1]);
            }

            ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).toStkStsCd, shpgOrdDtlTMsg.fromStkStsCd);

            if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

                String loanType = judgeLoan(shpgOrdDtlTMsg);

                if (!ORD_TP_RS.equals(loanType)) {

                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.TRIAL_USE);
                }

            } else if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.IN_TRANSIT_WH);

            } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.WORK_IN_PROCESS_COMPONENT);

            } else if (SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.WORK_IN_PROCESS_KIT);

            } else if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)) {

                // QC#26585 MOD START
                //ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.IN_TRANSIT);
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
                // QC#26585 MOD END
            }

            if (!ZYPCommonFunc.hasValue(pMsg.UpdateDetailList.no(0).serTrxEventCd)) {

                return true;
            }

            // Call API
            NLZC302001 api = new NLZC302001();
            api.execute(pMsg, onBatch);

            if (S21ApiUtil.isXxMsgId(pMsg)) {

                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                    doErrorProcess(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), "Serial Transaction API Error.");
                }

                return false;
            }

            return true;
        }

        /**
         * Copy Serial# Work to TMsg
         * @param rs ResultSet
         * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
         * @return SHIP_SER_NUM_WRKTMsg
         * @throws SQLException
         */
        private SHIP_SER_NUM_WRKTMsg copySerNumWrkToTMsg(ResultSet rs, SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg) throws SQLException {

            // Copy to TMsg
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.soNum, rs.getString(SO_NUM));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.wrkTrxId, rs.getString(WRK_TRX_ID));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.sqId, rs.getString(SQ_ID));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.procStsCd, rs.getString(PROC_STS_CD));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.errMsgCd, rs.getString(ERR_MSG_CD));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.mdseCd, rs.getString(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.whCd, rs.getString(WH_CD));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.soSerId, rs.getString(SO_SER_ID));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.serNum, rs.getString(SER_NUM));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.serTakeDtTmTs, rs.getString(SER_TAKE_DT_TM_TS));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.soSlpNum, rs.getString(SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.serIntfcProcStsCd, rs.getString(SER_INTFC_PROC_STS_CD));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.serIntfcErrCd, rs.getString(SER_INTFC_ERR_CD));
            ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.srcTpCd, rs.getString(SRC_TP_CD));

            return shipSerNumWrkTMsg;
        }

        /**
         * Check Serial# Work Data (If check result is NG, set Warning
         * and continue as normal end)
         * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
         * @param serNumErrTMsg SER_NUM_ERRTMsg
         */
        private boolean checkSerNumWrk(SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg, SER_NUM_ERRTMsg serNumErrTMsg) {

            boolean errFlg = false;
            boolean serOtherErrFlg = false;

            String glblCmpyCd = shipSerNumWrkTMsg.glblCmpyCd.getValue();
            String soNum = shipSerNumWrkTMsg.soNum.getValue();
            String soSlpNum = shipSerNumWrkTMsg.soSlpNum.getValue();

            // Get SO detail
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getShpgOrdDtl(glblCmpyCd, soNum, soSlpNum);

            // SO#, Line# not in SO detail
            if (shpgOrdDtlTMsg == null) {

                doErrorProcess(NLZM2033W, "SO#, Line# not in SO detail (Serial)");
                serOtherErrFlg = true;
                setOthErrCnt(serNumErrTMsg);

                // Return since the following checks cannot be done
                return errFlg;
            }

            // Not same W/H as SO
            if (!shipSerNumWrkTMsg.whCd.getValue().equals(shpgOrdDtlTMsg.invtyLocCd.getValue())) {

                doErrorProcess(NLZM2012W, shpgOrdDtlTMsg);
                serOtherErrFlg = true;
            }

            // Not same MDSE as SO detail
            if (!shipSerNumWrkTMsg.mdseCd.getValue().equals(shpgOrdDtlTMsg.mdseCd.getValue())) {

                doErrorProcess(NLZM2013W, shpgOrdDtlTMsg);
                serOtherErrFlg = true;
            }

            // Serial# is not required on this MDSE
            if (ZYPConstant.FLG_OFF_N.equals(shpgOrdDtlTMsg.serNumTakeFlg.getValue())) {

                doErrorProcess(NLZM2014W, shpgOrdDtlTMsg);
                serOtherErrFlg = true;
            }

            if (serOtherErrFlg) {

                setOthErrCnt(serNumErrTMsg);
            }

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("soNum", soNum);
            queryParam.put("soSlpNum", soSlpNum);

            int cnt = (Integer) ssmBatchClient.queryObject("getShipSerNumCntBySoNum", queryParam, new GetShipSerNumCntBySoNum());

            if (shpgOrdDtlTMsg.shpgQty.getValue().compareTo(new BigDecimal(cnt)) != 1) {

                doErrorProcess(NLZM2043W, shpgOrdDtlTMsg);
                // It isn't taked Error in the Seriar Error Report.
            }

            return errFlg;
        }

        /**
         * setOthErrCnt
         * @param serNumErrTMsg SER_NUM_ERRTMsg
         */
        private void setOthErrCnt(SER_NUM_ERRTMsg serNumErrTMsg) {

            BigDecimal othErrQty = serNumErrTMsg.othErrQty.getValue();
            serNumErrTMsg.othErrQty.setValue(othErrQty.add(BigDecimal.ONE));
        }

        /**
         * getStoredSerNumTMsgByKey
         * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
         */
        private SER_NUM_ERRTMsg getStoredSerNumTMsgByKey(SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg) {

            if (serErrRec.containsKey(shipSerNumWrkTMsg.mdseCd.getValue())) {

                // When same PK record exists in the store map already.
                SER_NUM_ERRTMsg serErrTMsg = (SER_NUM_ERRTMsg) serErrRec.get(shipSerNumWrkTMsg.mdseCd.getValue());
                return serErrTMsg;

            } else {

                SER_NUM_ERRTMsg serErrTMsg = new SER_NUM_ERRTMsg();

                // Set key data for TMsg
                ZYPEZDItemValueSetter.setValue(serErrTMsg.glblCmpyCd, shipSerNumWrkTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(serErrTMsg.shipDt, shipSerNumWrkTMsg.serTakeDtTmTs.getValue().substring(0, 8));
                ZYPEZDItemValueSetter.setValue(serErrTMsg.mdseCd, shipSerNumWrkTMsg.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(serErrTMsg.whCd, shipSerNumWrkTMsg.whCd);

                // Set initial value
                ZYPEZDItemValueSetter.setValue(serErrTMsg.lgErrQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(serErrTMsg.rngErrQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(serErrTMsg.dupErrQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(serErrTMsg.othErrQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(serErrTMsg.totErrQty, BigDecimal.ZERO);

                return serErrTMsg;
            }
        }

        /**
         * Update Serial# Error Table
         */
        private void updateSerialError() {

            Collection<SER_NUM_ERRTMsg> values = serErrRec.values();

            for (SER_NUM_ERRTMsg tempSerErr : values) {

                SER_NUM_ERRTMsg serNumErrTMsg = getSameKeySerNumErr(tempSerErr);

                if (serNumErrTMsg == null) {

                    // When this Key value record is not exist, Insert.
                    serNumErrTMsg = tempSerErr;

                    // Set value for Insert/Update
                    setValToSerNumErrTMsg(tempSerErr, serNumErrTMsg);

                    S21ApiTBLAccessor.create(serNumErrTMsg);

                } else {

                    // When this Key value record is already exist, Update.
                    BigDecimal lgErrQty = tempSerErr.lgErrQty.getValue();
                    ZYPEZDItemValueSetter.setValue(serNumErrTMsg.lgErrQty, serNumErrTMsg.lgErrQty.getValue().add(lgErrQty));

                    BigDecimal rngErrQty = tempSerErr.rngErrQty.getValue();
                    ZYPEZDItemValueSetter.setValue(serNumErrTMsg.rngErrQty, serNumErrTMsg.rngErrQty.getValue().add(rngErrQty));

                    BigDecimal dupErrQty = tempSerErr.dupErrQty.getValue();
                    ZYPEZDItemValueSetter.setValue(serNumErrTMsg.dupErrQty, serNumErrTMsg.dupErrQty.getValue().add(dupErrQty));

                    BigDecimal othErrQty = tempSerErr.othErrQty.getValue();
                    ZYPEZDItemValueSetter.setValue(serNumErrTMsg.othErrQty, serNumErrTMsg.othErrQty.getValue().add(othErrQty));

                    // Set value for Insert/Update
                    setValToSerNumErrTMsg(tempSerErr, serNumErrTMsg);

                    S21ApiTBLAccessor.update(serNumErrTMsg);
                }

                if (!RETURN_CD_NORMAL_END.equals(serNumErrTMsg.getReturnCode())) {

                    // DB error
                    doErrorProcess(NLZM2030E, serNumErrTMsg);
                    throw new S21AbendException(NLZM2030E);
                }
            }
        }

        /**
         * setValToSerNumErrTMsg
         * @param tempSerErr SER_NUM_ERRTMsg
         * @param serNumErrTMsg SER_NUM_ERRTMsg
         */
        private void setValToSerNumErrTMsg(SER_NUM_ERRTMsg tempSerErr, SER_NUM_ERRTMsg serNumErrTMsg) {

            BigDecimal totalQty = serNumErrTMsg.lgErrQty.getValue().add(serNumErrTMsg.rngErrQty.getValue()).add(serNumErrTMsg.dupErrQty.getValue()).add(serNumErrTMsg.othErrQty.getValue());

            ZYPEZDItemValueSetter.setValue(serNumErrTMsg.shipYrMth, tempSerErr.shipDt.getValue().substring(0, 6));
            ZYPEZDItemValueSetter.setValue(serNumErrTMsg.totErrQty, totalQty);
        }
    }

    /**
     * Import Serial# Process
     */
    private class ImportShipSerNum extends S21SsmVoidResultSetHandlerSupport {

        /** SHIP_SER_NUM_WRKTMsg */
        private SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg;

        /** ArrayList<SHIP_SER_NUMTMsg> */
        private ArrayList<SHIP_SER_NUMTMsg> shipSerNumTMsgList;

        /**
         * getShipSerNumList
         * @return ArrayList<SHIP_SER_NUMTMsg>
         */
        public ArrayList<SHIP_SER_NUMTMsg> getShipSerNumList() {
            return this.shipSerNumTMsgList;
        }

        public ImportShipSerNum(SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg) {
            this.shipSerNumWrkTMsg = shipSerNumWrkTMsg;
            this.shipSerNumTMsgList = new ArrayList<SHIP_SER_NUMTMsg>();
        }

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            if (rs.next()) {

                BigDecimal soSerId = nullToZero(rs.getBigDecimal(SO_SER_ID));

                // ** Import Serial#
                SHIP_SER_NUMTMsg shipSerNumTMsg = new SHIP_SER_NUMTMsg();
                shipSerNumTMsg.glblCmpyCd.setValue(shipSerNumWrkTMsg.glblCmpyCd.getValue());
                shipSerNumTMsg.mdseCd.setValue(shipSerNumWrkTMsg.mdseCd.getValue());
                shipSerNumTMsg.soNum.setValue(shipSerNumWrkTMsg.soNum.getValue());

                // Set current value + 1 so that this data to be unique by each SO_NUM, MDSE_CD
                shipSerNumTMsg.soSerId.setValue(String.valueOf(soSerId.add(new BigDecimal(1))));
                shipSerNumTMsg.whCd.setValue(shipSerNumWrkTMsg.whCd.getValue());
                shipSerNumTMsg.serNum.setValue(shipSerNumWrkTMsg.serNum.getValue());
                shipSerNumTMsg.serTakeDtTmTs.setValue(shipSerNumWrkTMsg.serTakeDtTmTs.getValue());
                shipSerNumTMsg.soSlpNum.setValue(shipSerNumWrkTMsg.soSlpNum.getValue());
                shipSerNumTMsg.serIntfcProcStsCd.setValue(shipSerNumWrkTMsg.serIntfcProcStsCd.getValue());
                shipSerNumTMsg.serIntfcErrCd.setValue(shipSerNumWrkTMsg.serIntfcErrCd.getValue());
                S21ApiTBLAccessor.create(shipSerNumTMsg);

                if (RETURN_CD_PRIMARY_KEY_DUPLICATE.equals(shipSerNumTMsg.getReturnCode())) {

                    // Continue when duplicate primary key error

                } else {

                    shipSerNumTMsgList.add(shipSerNumTMsg);
                }
            }
        }
    }

    /**
     * SO Detail Cancel Process
     */
    private class SoDtlCancelProcess extends S21SsmVoidResultSetHandlerSupport {

        /** SHPG_ORDTMsg */
        private SHPG_ORDTMsg shpgOrdTMsg;

        public SoDtlCancelProcess(SHPG_ORDTMsg shpgOrdTMsg) {
            this.shpgOrdTMsg = shpgOrdTMsg;
        }

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            String glblCmpyCd = null;
            String soNum = null;
            String soSlpNum = null;
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();

            while (rs.next()) {

                glblCmpyCd = nullToEmpty(rs.getString(GLBL_CMPY_CD));
                soNum = nullToEmpty(rs.getString(SO_NUM));
                soSlpNum = nullToEmpty(rs.getString(SO_SLP_NUM));

                // Get primary key
                shpgOrdDtlTMsg = getShpgOrdDtl(glblCmpyCd, soNum, soSlpNum);

                // No data (impossible)
                if (shpgOrdDtlTMsg == null) {

                    doErrorProcess(NLZM2030E, soNum);
                    throw new S21AbendException(NLZM2030E);
                }

                // Update status
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shpgStsCd, SHPG_STS.S_OR_O_CANCELLED);
                S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

                if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                    doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                    throw new S21AbendException(NLZM2030E);
                }

                // Call Shipping Plan Update API
                callShippingPlanUpdateAPI(SHPG_MODE_HARDALLOCATED_SOCANCELLED, shpgOrdDtlTMsg, null, null);

                // Call DO API(Cancel)
//                if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {
//
//                    callDoAPI(shpgOrdTMsg, shpgOrdDtlTMsg, DO_CANCEL);
//                }

                if (shpgOrdDtlTMsg != null) {

                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.CANCELLED);
                    S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

                    if (!RETURN_CD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {

                        doErrorProcess(NLZM2030E, shpgOrdDtlTMsg);
                        throw new S21AbendException(NLZM2030E);
                    }
                }

                // ** Inventory Order Update
                if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd) || SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)) {

                    callInventoryOrder(IOA_CANCEL, shpgOrdDtlTMsg);
                }

                // ** PR Update API
                if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineNum) && ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.prchReqLineSubNum)) {

                    if (!sceOrdTpCd.equals(SCE_ORD_TP.TRIAL_FOR_USE) //
                            && !sceOrdTpCd.equals(SCE_ORD_TP.DIRECT_SALES) //
                            && !sceOrdTpCd.equals(SCE_ORD_TP.DC_TRANSFER)) {
                        callPRUpdateAPI(PRUA_CANCEL, shpgOrdDtlTMsg, null, shpgOrdDtlTMsg.shpgQty.getValue());
                    }
                }

                // ** Machine Master Update API
                ArrayList<MachineMasterResultBean> errMsg = MachMstrApiEvent.callMachineMasterUpdateForCancel(shpgOrdDtlTMsg, false, onBatch, ssmBatchClient, sceOrdTpCd);

                if (!errMsg.isEmpty()) {

                    for (MachineMasterResultBean msgBean : errMsg) {

                        doErrorProcess(msgBean.getMsgId(), "Machine Master Update API Error.");
                    }
                }
            }

            // ** Reman Order Update API QC#13807
            if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(shpgOrdTMsg.sceOrdTpCd.getValue()) || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {

                callRemanUpdateAPI(shpgOrdTMsg);
            }
        }
    }

    /**
     * Import SO Conf
     * @param shpgOrdConfWrkTMsg SHPG_ORD_CONF_WRKTMsg
     */
    private void importSoConf(SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg) {

        // Check if SO Conf is created or not
        SHPG_ORD_CONFTMsg shpgOrdConfTMsg = new SHPG_ORD_CONFTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.glblCmpyCd, shpgOrdConfWrkTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soNum, shpgOrdConfWrkTMsg.soNum.getValue());
        shpgOrdConfTMsg = (SHPG_ORD_CONFTMsg) S21ApiTBLAccessor.findByKey(shpgOrdConfTMsg);

        // Not created
        if (shpgOrdConfTMsg == null) {

            // Create SO conf data
            shpgOrdConfTMsg = new SHPG_ORD_CONFTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.glblCmpyCd, shpgOrdConfWrkTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soNum, shpgOrdConfWrkTMsg.soNum.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.whCd, shpgOrdConfWrkTMsg.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.sceOrdTpCd, shpgOrdConfWrkTMsg.sceOrdTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soProcStsCd, shpgOrdConfWrkTMsg.soProcStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.soDataActTpCd, shpgOrdConfWrkTMsg.soDataActTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.shipDtTmTs, shpgOrdConfWrkTMsg.shipDtTmTs.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.totShipWt, shpgOrdConfWrkTMsg.totShipWt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.totFrtAmt, shpgOrdConfWrkTMsg.totFrtAmt.getValue());

            S21ApiTBLAccessor.create(shpgOrdConfTMsg);

            if (RETURN_CD_PRIMARY_KEY_DUPLICATE.equals(shpgOrdConfTMsg.getReturnCode())) {

                // Continue when duplicate primary key error
            }

        } else {

            // Update only ship date
            ZYPEZDItemValueSetter.setValue(shpgOrdConfTMsg.shipDtTmTs, shpgOrdConfWrkTMsg.shipDtTmTs.getValue());
            S21ApiTBLAccessor.update(shpgOrdConfTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdConfTMsg.getReturnCode())) {

                // DB error
                doErrorProcess(NLZM2030E, shpgOrdConfWrkTMsg);
                throw new S21AbendException(NLZM2030E);
            }
        }

        cntConfWrk++;
    }

    /**
     * Check If All Details Are Closed
     */
    private class CheckAllDetailClose extends S21SsmBooleanResultSetHandlerSupport {

        /** process shipped Flag */
        private boolean procShipFlg = false;

        /**
         * isProcessShip
         * @return true: ship false: not ship
         */
        public boolean isProcessShip() {
            return procShipFlg;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean allDetailsClosedFlg = false;
            int cnt = 0;
            int cntShipped = 0;
            int cntInshed = 0;
            int cntCancelled = 0;
            String shpgStsCd = null;

            while (rs.next()) {

                // Get Shipping Status
                shpgStsCd = nullToEmpty(rs.getString(SHPG_STS_CD));

                // Count up by the status
                if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {

                    cntShipped++;
                    procShipFlg = true;

                } else if (SHPG_STS.INSHED.equals(shpgStsCd)) {

                    cntInshed++;

                } else if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

                    cntCancelled++;
                }

                cnt++;
            }

            // Check Shipping Status of the details
            if (cnt == cntShipped) {

                detailShippingStatus = DetailShippingStatus.ALL_SHIPPED;
                allDetailsClosedFlg = true;

            } else if (cnt == cntInshed) {

                detailShippingStatus = DetailShippingStatus.ALL_INSHED;
                allDetailsClosedFlg = true;

            } else if (cnt == cntCancelled) {

                detailShippingStatus = DetailShippingStatus.ALL_CANCELLED;
                allDetailsClosedFlg = true;

            } else if ((cntShipped > 0) && (cntCancelled > 0) && (cntShipped + cntCancelled == cnt)) {

                detailShippingStatus = DetailShippingStatus.SHIPPED_CANCELLED_MIX;
                allDetailsClosedFlg = true;

            } else if ((cntInshed > 0) && (cntCancelled > 0) && (cntInshed + cntCancelled == cnt)) {

                detailShippingStatus = DetailShippingStatus.INSHED_CANCELLED_MIX;
                allDetailsClosedFlg = true;

            } else {

                detailShippingStatus = DetailShippingStatus.NOT_ALL_CLOSE;
            }

            return allDetailsClosedFlg;
        }
    }

    /**
     * SO Close Process
     * @param shpgOrdConfWrkTMsg SHPG_ORD_CONF_WRKTMsg
     * @param shpgOrdConfTMsg SHPG_ORD_CONFTMsg
     * @param shpgOrdTMsg SHPG_ORDTMsg
     */
    private void processSoClose(SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg, SHPG_ORD_CONFTMsg shpgOrdConfTMsg, SHPG_ORDTMsg shpgOrdTMsg) {

        String shpgStsCd = null;
        String schdCoordStsCd = null;
        String poRcvNum = null;

        // ** Update status of SO
        if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

            shpgStsCd = SHPG_STS.S_OR_O_CANCELLED;
            schdCoordStsCd = SCHD_COORD_STS.CANCELED;

            /* 11/15/2016 CITS T.Tokutomi CLOSE -> NONE QC#15868 START */
        } else if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED || detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

            shpgStsCd = SHPG_STS.SHIPPED;

        } else if (detailShippingStatus == DetailShippingStatus.ALL_INSHED || detailShippingStatus == DetailShippingStatus.INSHED_CANCELLED_MIX) {

            shpgStsCd = SHPG_STS.INSHED;

        }
        /* 11/15/2016 CITS T.Tokutomi CLOSE -> NONE QC#15868 END */

        // Update the record which has been locked
        shpgOrdTMsg.shpgStsCd.setValue(shpgStsCd);
        S21ApiTBLAccessor.update(shpgOrdTMsg);

        if (!RETURN_CD_NORMAL_END.equals(shpgOrdTMsg.getReturnCode())) {

            // DB error
            doErrorProcess(NLZM2030E, shpgOrdTMsg);
            throw new S21AbendException(NLZM2030E);
        }

        /* 11/15/2016 CITS T.Tokutomi CLOSE -> NONE QC#15868 START */
        if (ZYPCommonFunc.hasValue(schdCoordStsCd)) {
            // Update SHPG_ORD
            updateShpgOrd(shpgOrdTMsg, schdCoordStsCd);
        }
        /* 11/15/2016 CITS T.Tokutomi CLOSE -> NONE QC#15868 END */

        // ** Process by SCE_ORD_TP
        if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // No process
            }

        } else if (SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // No process
            }

        } else if (SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // No process
            }

        } else if (SCE_ORD_TP.LOAN.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // No process
            }

        } else if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // No process
            }

        } else if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)) {

//            if (detailShippingStatus == DetailShippingStatus.ALL_INSHED) {
//
//                // Call Shipping Plan Update API (SO Close)
//                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);
//
//                // Call DO API(SO Confimation)
//                callDoAPI(shpgOrdTMsg, null, DO_STS_SO_CONF);
//
//            } else if (detailShippingStatus == DetailShippingStatus.INSHED_CANCELLED_MIX) {
//
//                // Call Shipping Plan Update API (SO Close)
//                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);
//
//                // Call DO API(SO Confimation)
//                callDoAPI(shpgOrdTMsg, null, DO_STS_SO_CONF);
//
//            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {
//
//                // No process
//            }

        } else if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

                if (SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)) {
                    // Call Inventory Order Update API
                    callInventoryOrderUpdateAPI(shpgOrdTMsg);
                }

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED //
                    && SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);
            }

        } else if (SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);
            }

        } else if (SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);

            }

        } else if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);

            }

        } else if (SCE_ORD_TP.DAMAGED_CLAIM_ENTRY.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);

            }

        } else if (SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // No process (impossible pattern)

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);

            }

        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

                // Call Work Order Update API
                callWorkOrderUpdateAPI(shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // No process

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Work Order Update API
                callWorkOrderUpdateAPI(shpgOrdTMsg);
            }

        } else if (SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

                // Call Work Order Update API
                callWorkOrderUpdateAPI(shpgOrdTMsg);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {

                // No process

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Work Order Update API
                callWorkOrderUpdateAPI(shpgOrdTMsg);
            }

        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);

                // Call Vendor Return Update API (Close)
                callVendorReturnUpdateAPI(shpgOrdTMsg, VND_RTRN_MODE_CLOSED);

            } else if (detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX) {
                // QC#17340
                // Call Shipping Plan Update API (SO Close)
                callShippingPlanUpdateAPI(SHPG_MODE_SHIPPED_SOCLOSE, null, null, shpgOrdTMsg);
                // Call Vendor Return Update API (Close)
                callVendorReturnUpdateAPI(shpgOrdTMsg, VND_RTRN_MODE_CLOSED);
            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Vendor Return Update API (Cancel)
                callVendorReturnUpdateAPI(shpgOrdTMsg, VND_RTRN_MODE_CANCELLED);
            }

        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            if (detailShippingStatus == DetailShippingStatus.ALL_INSHED) {

                // Call DO API(SO Confimation)
//                callDoAPI(shpgOrdTMsg, null, DO_STS_SO_CONF);

            } else if (detailShippingStatus == DetailShippingStatus.INSHED_CANCELLED_MIX) {

                // Call DO API(SO Confimation)
//                callDoAPI(shpgOrdTMsg, null, DO_STS_SO_CONF);

            } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

                // Call Vendor Return Update API (Cancel)
                callVendorReturnUpdateAPI(shpgOrdTMsg, VND_RTRN_MODE_CANCELLED);
            }

        } else if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

            if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);

            } else if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);
            }

        } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);
            }

        } else if (SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)) {

            if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);
            }

        } else if (SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd)) {

            if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

                // Call Inventory Order Update API
                callInventoryOrderUpdateAPI(shpgOrdTMsg);
            }
        // QC#26866
        } else if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {
            // PO Cancel Process
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
            queryParam.put("soNum", shpgOrdTMsg.soNum.getValue());
            this.ssmBatchClient.queryObject("getShpgOrdDtl", queryParam, new PoCancelProcess());
        }

        // PO Receiving API & RWS API
        if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {

            if (doesExistDsCondConst(shpgOrdTMsg.glblCmpyCd.getValue(), sceOrdTpCd, null, ZYPConstant.FLG_ON_Y, null, null, null)) {

                // Call PO Receiving API
                poRcvNum = callPoReceivingAPI(shpgOrdTMsg);

                // Call RWS API
                ArrayList<String> rwsNumList = callRwsAPI(shpgOrdTMsg, poRcvNum);

                // RWS Serial API
                ArrayList<String> errMsg = callRwsSerialAPI(rwsNumList, shpgOrdTMsg);

                if (!errMsg.isEmpty()) {

                    for (String msg : errMsg) {

                        doErrorProcess(msg, "RWS Serial API Error.");
                    }
                }

                // QC#6562 PR Update API Update/Update Mode
                callPrUpdateAPIUpdateMode(shpgOrdTMsg.glblCmpyCd.getValue(), rwsNumList);
            }
        }

        // RWS Cancel
        if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

            if (doesExistDsCondConst(shpgOrdTMsg.glblCmpyCd.getValue(), sceOrdTpCd, null, null, ZYPConstant.FLG_ON_Y, null, null)) {

                // Call RWS cancel process
                cancelRWS(shpgOrdTMsg);
            }
        }

        // ** Clear Routing Information

        // There are cancel (Order Void, Line Void) data in SO detail
        if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED || detailShippingStatus == DetailShippingStatus.SHIPPED_CANCELLED_MIX || detailShippingStatus == DetailShippingStatus.INSHED_CANCELLED_MIX) {

            clearRoutingInfo(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.rteGrpNum.getValue());
        }

        // QC#26863 MOD START
        // 08/30/2017 CITS T.Hakodate Mod Sol#069(QC#18270) START
        // Reman RWS Close
        //if (SHPG_STS.SHIPPED.equals(shpgOrdTMsg.shpgStsCd.getValue()) && (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd))) {
        //if (SHPG_STS.SHIPPED.equals(shpgOrdTMsg.shpgStsCd.getValue()) && (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd))) {
        if (SHPG_STS.SHIPPED.equals(shpgOrdTMsg.shpgStsCd.getValue()) && (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd))) {
            closeRemanRws(shpgOrdTMsg);
        }

        // 08/30/2017 CITS T.Hakodate Mod Sol#069(QC#18270) END
        // QC#26863 MOD END
        
        // Remaining Machine Master Allocation Cancel
        ArrayList<String> machMstrErrMsgList = callMachMstrAPIForAllocOff(shpgOrdTMsg);

        if (!machMstrErrMsgList.isEmpty()) {

            for (String machMstrErrMsg : machMstrErrMsgList) {

                doErrorProcess(machMstrErrMsg, "Machine Master Update API Error.");
            }
        }

        // Release Pending Parts
        if (SHPG_STS.SHIPPED.equals(shpgOrdTMsg.shpgStsCd.getValue())) {

            ArrayList<String> fsrUpdErrMsgList = callFsrUpdateAPI(shpgOrdTMsg);

            if (!fsrUpdErrMsgList.isEmpty()) {

                for (String fsrUpdErrMsg : fsrUpdErrMsgList) {

                    doErrorProcess(fsrUpdErrMsg, "FSR Update API Error.");
                }
            }
        }
    }

    /**
     * updateShpgOrd if SHPG_ORD does not exist, this method
     * skip.
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param schdCoordStsCd String
     */
    private void updateShpgOrd(SHPG_ORDTMsg shpgOrdTMsg, String schdCoordStsCd) {

        if (shpgOrdTMsg != null) {

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordStsCd, schdCoordStsCd);
            S21ApiTBLAccessor.update(shpgOrdTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdTMsg.getReturnCode())) {

                // SHPG_ORD Table Update Error
                doErrorProcess(NLZM2030E, shpgOrdTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            // Create SHPG_ORD_SCHD_TRK
            SHPG_ORD_SCHD_TRKTMsg shpgOrdSchdTrkTMsg = new SHPG_ORD_SCHD_TRKTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgOrdSchdTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SHPG_ORD_SCHD_TRK_SQ"));
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updUsrId, shpgOrdTMsg.ezUpUserID.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updTs, shpgOrdTMsg.ezUpTime.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.soNum, shpgOrdTMsg.soNum.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordStsCd, shpgOrdTMsg.schdCoordStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordPsnCd, shpgOrdTMsg.schdCoordPsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDelyDt, shpgOrdTMsg.schdDelyDt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrCd, shpgOrdTMsg.carrCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgSvcLvlCd, shpgOrdTMsg.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdFlg, shpgOrdTMsg.tempSchdFlg.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdRsnCd, shpgOrdTMsg.tempSchdRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdCmntTxt, shpgOrdTMsg.tempSchdCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDurnTmNum, shpgOrdTMsg.schdDurnTmNum.getValue());

            S21ApiTBLAccessor.create(shpgOrdSchdTrkTMsg);

            if (!RETURN_CD_NORMAL_END.equals(shpgOrdSchdTrkTMsg.getReturnCode())) {

                // SHPG_ORD_SCHD_TRK Table Update Error
                doErrorProcess(NLZM2030E, shpgOrdTMsg);
                throw new S21AbendException(NLZM2030E);
            }
        }
    }

    /**
     * Clear Routing Information
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param rteGrpNum RTE_GRP_NUM
     */
    private void clearRoutingInfo(String glblCmpyCd, String rteGrpNum) {

        if (ZYPCommonFunc.hasValue(rteGrpNum)) {

            // Delete RTE_GRP logically
            RTE_GRPTMsg rte = new RTE_GRPTMsg();
            rte.glblCmpyCd.setValue(glblCmpyCd);
            rte.rteGrpNum.setValue(rteGrpNum);
            S21ApiTBLAccessor.logicalRemove(rte);

            if (RETURN_CD_NO_DATA.equals(rte.getReturnCode())) {

                // Continue even if there are no target data
            }

            // Delete RTE_GRP_DTL logically
            RTE_GRP_DTLTMsg rteDtl = new RTE_GRP_DTLTMsg();
            rteDtl.glblCmpyCd.setValue(glblCmpyCd);
            rteDtl.rteGrpNum.setValue(rteGrpNum);
            S21ApiTBLAccessor.logicalRemoveByPartialKey(rteDtl);

            if (RETURN_CD_NO_DATA.equals(rteDtl.getReturnCode())) {

                // Continue even if there are no target data
            }
        }
    }

    /**
     * Cancel RWS
     * @param so SHPG_ORDTMsg
     */
    private void cancelRWS(SHPG_ORDTMsg so) {

		// Get RWS Reference#
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("glblCmpyCd", so.glblCmpyCd.getValue());
	    queryParam.put("rwsRefNum", so.soNum.getValue());
		queryParam.put("list", createConditionListForRwsCancel());
		queryParam.put("sceOrdTpCd", sceOrdTpCd);
		queryParam.put("whCd", so.whCd.getValue());

		//2017/09/21 S.Endo Mod Sol#069(QC#18270) START
		if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {
            //2022/04/11 K.Iwamoto Mod QC#59904 START
            queryParam.put("rwsRefNum", "");
            queryParam.put("srcOrdNum", so.srcOrdNum.getValue());
            queryParam.put("svcConfigMstrPk", so.svcConfigMstrPk.getValue());
            //2022/04/11 K.Iwamoto Mod QC#59904 END
            queryParam.put("whCd", so.shipToCustCd.getValue());
        }
		//2017/09/21 S.Endo Mod Sol#069(QC#18270) END

		if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {

			queryParam.put("poRcvTrxHdrNum", so.trxHdrNum.getValue());
			

		} else if (SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)
				|| SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd)
				|| SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd)) {

			queryParam.put("trxOrdNum", so.soNum.getValue());

		// QC#18628
		} else if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)) {
			queryParam.put("soNum", so.soNum.getValue());
        // START 2022/08/11 R.Azucena [QC#60359 ADD]
        } else if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {
            queryParam.put("svcConfigMstrPk", so.svcConfigMstrPk.getValue());
        // END 2022/08/11 R.Azucena [QC#60359 ADD]
		}

		this.ssmBatchClient.queryObject("getRwsHdr", queryParam, new CancelRWSProcess());
	}

    /**
     * RWS Cancel Process
     */
    private class CancelRWSProcess extends S21SsmVoidResultSetHandlerSupport {

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            String glblCmpyCd = null;
            String rwsNum = null;

            if (rs.next()) {

                glblCmpyCd = nullToEmpty(rs.getString(GLBL_CMPY_CD));
                rwsNum = nullToEmpty(rs.getString(RWS_NUM));

                // Update detail data
                RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsStsCd, RWS_STS.CANCELED);
                int cnt = S21ApiTBLAccessor.updateByPartialKey(rwsDtlTMsg, new String[] {"rwsStsCd" });

                if (!RETURN_CD_NORMAL_END.equals(rwsDtlTMsg.getReturnCode())) {

                    // DB error
                    doErrorProcess(NLZM2030E, rwsDtlTMsg);
                    throw new S21AbendException(NLZM2030E);
                }

                // Get header data
                RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, rwsNum);
                rwsHdrTMsg = (RWS_HDRTMsg) S21ApiTBLAccessor.findByKey(rwsHdrTMsg);

                // No data (Impossible)
                if (rwsHdrTMsg == null) {

                    // DB error
                    doErrorProcess(NLZM2030E, rwsNum);
                    throw new S21AbendException(NLZM2030E);
                }

                // Update header data
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsStsCd, RWS_STS.CANCELED);
                S21ApiTBLAccessor.update(rwsHdrTMsg);

                if (!RETURN_CD_NORMAL_END.equals(rwsHdrTMsg.getReturnCode())) {

                    // DB error
                    doErrorProcess(NLZM2030E, rwsHdrTMsg);
                    throw new S21AbendException(NLZM2030E);
                }

                // Po Receiving API
                if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {

                    RWS_DTLTMsg srchCond = new RWS_DTLTMsg();
                    srchCond.setSQLID("001");
                    srchCond.setConditionValue("glblCmpyCd01", rwsHdrTMsg.glblCmpyCd.getValue());
                    srchCond.setConditionValue("rwsNum01", rwsHdrTMsg.rwsNum.getValue());

                    RWS_DTLTMsgArray rwsDtlArray = (RWS_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(srchCond);

                    for (int i = 0; i < rwsDtlArray.getValidCount(); i++) {

                        // QC#19831 Start
                        updatePORcvLine(rwsHdrTMsg.glblCmpyCd.getValue(), rwsHdrTMsg.poRcvTrxHdrNum.getValue(), rwsDtlArray.no(i).poRcvTrxLineNum.getValue());
                    }

                    // Update PO_RCV_HDR
                    updatePORcvHdr(rwsHdrTMsg.glblCmpyCd.getValue(), rwsHdrTMsg.poRcvTrxHdrNum.getValue());

                    // Get PO_RCV Info
                    Map<String, Object> queryParam = new HashMap<String, Object>();
                    queryParam.put("glblCmpyCd", glblCmpyCd);
                    queryParam.put("poRcvTrxHdrNum",  rwsHdrTMsg.poRcvTrxHdrNum.getValue());

                    Map<String, Object> poRcvMap = (Map<String, Object>) ssmBatchClient.queryObject("getPoRcvHdr", queryParam);

                    if (poRcvMap != null && !poRcvMap.isEmpty()) {
                        // Update /Delete INBD_VIS
                        updateDeleteInbdVis(rwsHdrTMsg.glblCmpyCd.getValue(), (String) poRcvMap.get(PO_RCV_NUM));
    
                        // Delete WH_SCHD
                        deleteWhSchd(rwsHdrTMsg.glblCmpyCd.getValue(), (String) poRcvMap.get(PO_RCV_NUM));
                    }

                    for (int i = 0; i < rwsDtlArray.getValidCount(); i++) {
                        
                        // Call POYO Update API (NPZC1090) [Create Inbounds Visibility]
                        NPZC109001PMsg poyoVisPMsg = new NPZC109001PMsg();
                        ZYPEZDItemValueSetter.setValue(poyoVisPMsg.glblCmpyCd, rwsHdrTMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(poyoVisPMsg.xxModeCd, NPZC109001Constant.POYO_INSERT_MODE);
    
                        NPZC109001_detailListPMsg detail = poyoVisPMsg.detailList.no(0);
                        ZYPEZDItemValueSetter.setValue(detail.poOrdNum, rwsHdrTMsg.poRcvTrxHdrNum);
                        ZYPEZDItemValueSetter.setValue(detail.poOrdDtlLineNum, rwsDtlArray.no(i).poRcvTrxLineNum);
                        ZYPEZDItemValueSetter.setValue(detail.xxQty10Num, rwsDtlArray.no(i).rwsQty.getValue().subtract(rwsDtlArray.no(i).rwsPutAwayQty.getValue()));
                        poyoVisPMsg.detailList.setValidCount(1);
    
                        NPZC109001 poyoVisAPI = new NPZC109001();
                        poyoVisAPI.execute(poyoVisPMsg, ONBATCH_TYPE.ONLINE);
                    }
                    // QC#19831 End
                }
            }
        }

        /**
         * updatePORcv
         * @param glblCmpyCd String
         * @param trxHdrNum String
         * @param trxLineNum String
         * @return true:Success false:NG
         */
        private boolean updatePORcvLine(String glblCmpyCd, String trxHdrNum, String trxLineNum) {

            // QC#19831 Start
            // Get PO_RCV Info
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("poRcvTrxHdrNum", trxHdrNum);
            queryParam.put("poRcvTrxLineNum", trxLineNum);

            Map<String, Object> poRcvMap = (Map<String, Object>) ssmBatchClient.queryObject("getPoRcvDtl", queryParam);

            if (poRcvMap == null || poRcvMap.isEmpty()) {
                S21InfoLogOutput.println(ZZBM0009I, new String[] {"PO_RCV_DTL", trxHdrNum + " " + trxLineNum, "0" });
                return false;
            }

            // Update Condition
            PO_RCV_DTLTMsg poRcvDtlTMsg = new PO_RCV_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(poRcvDtlTMsg.glblCmpyCd, (String) poRcvMap.get(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(poRcvDtlTMsg.poRcvNum, (String) poRcvMap.get(PO_RCV_NUM));
            ZYPEZDItemValueSetter.setValue(poRcvDtlTMsg.poRcvLineNum, (String) poRcvMap.get(PO_RCV_LINE_NUM));
            poRcvDtlTMsg = (PO_RCV_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poRcvDtlTMsg);

            if (poRcvDtlTMsg == null) {
                S21InfoLogOutput.println(ZZBM0009I, new String[] {"PO_RCV_DTL", trxHdrNum + " " + trxLineNum, "0" });
                return false;
            }

            ZYPEZDItemValueSetter.setValue(poRcvDtlTMsg.rwsStsCd, RWS_STS.CANCELED);

            // Update
            S21ApiTBLAccessor.update(poRcvDtlTMsg);

            if (!RETURN_CD_NORMAL_END.equals(poRcvDtlTMsg.getReturnCode())) {

                doErrorProcess(NLBM1064E, "DB error occurred.");
                return false;
            }
            // QC#19831 End

            return true;
        }

        /**
         * updatePORcvHdr
         * @param glblCmpyCd String
         * @param trxHdrNum String
         * @return true:Success false:NG
         */
        private boolean updatePORcvHdr(String glblCmpyCd, String trxHdrNum) {

            // Update PO Rcv Hdr
            PO_RCV_HDRTMsg prhUpdCond = new PO_RCV_HDRTMsg();
            PO_RCV_HDRTMsg prhUpd = new PO_RCV_HDRTMsg();

            // Search Condition
            ZYPEZDItemValueSetter.setValue(prhUpdCond.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prhUpdCond.poRcvTrxHdrNum, trxHdrNum);

            // Update Condition
            ZYPEZDItemValueSetter.setValue(prhUpd.rwsStsCd, RWS_STS.CANCELED);

            // Update
            S21ApiTBLAccessor.updateByPartialValue(prhUpdCond, new String[] {"glblCmpyCd", "poRcvTrxHdrNum" }, prhUpd, new String[] {"rwsStsCd" });

            if (!RETURN_CD_NORMAL_END.equals(prhUpd.getReturnCode())) {

                doErrorProcess(NLBM1064E, "DB error occurred.");
                return false;
            }

            return true;
        }
    }


    /**
     * updateDeleteInbdVis
     * @param glblCmpyCd String
     * @param poRcvNum String
     * @return boolean
     */
    private boolean updateDeleteInbdVis(String glblCmpyCd, String poRcvNum) {

        if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {

            return true;
        }

        // Search INBD_VIS
        INBD_VISTMsg inbdVisTMsg = new INBD_VISTMsg();
        inbdVisTMsg.setSQLID("011");
        inbdVisTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inbdVisTMsg.setConditionValue("poRcvNum01", poRcvNum);
        inbdVisTMsg.setConditionValue("inbdLtstRecFlg01", ZYPConstant.FLG_ON_Y);
        INBD_VISTMsgArray inbdVisTMsgList = (INBD_VISTMsgArray) EZDTBLAccessor.findByCondition(inbdVisTMsg);

        if (inbdVisTMsgList == null || inbdVisTMsgList.getValidCount() < 1) {
            doErrorProcess(NFCM0576E, "Inbound Visibility");
            return false;
        }

        for (int i = 0; i < inbdVisTMsgList.getValidCount(); i++) {

            // Update INBD_VIS
            inbdVisTMsg = inbdVisTMsgList.no(i);
            ZYPEZDItemValueSetter.setValue(inbdVisTMsg.inbdLtstRecFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(inbdVisTMsg);

            if (!RETURN_CD_NORMAL_END.equals(inbdVisTMsg.getReturnCode())) {
                doErrorProcess(NFCM0576E, "Inbound Visibility");
                return false;
            }

            // Delete INBD_VIS
            EZDTBLAccessor.logicalRemove(inbdVisTMsg);

            if (!RETURN_CD_NORMAL_END.equals(inbdVisTMsg.getReturnCode())) {
                doErrorProcess(NFCM0576E, "Inbound Visibility");
                return false;
            }
        }

        return true;
    }

    /**
     * deleteWhSchd
     * @param glblCmpyCd String
     * @param poRcvNum String
     * @return boolean
     */
    private boolean deleteWhSchd(String glblCmpyCd, String poRcvNum) {

        if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {

            return true;
        }

        // Search WH_SCHD
        WH_SCHDTMsg whSchdTMsg = new WH_SCHDTMsg();
        whSchdTMsg.setSQLID("011");
        whSchdTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        whSchdTMsg.setConditionValue("poRcvNum01", poRcvNum);
        WH_SCHDTMsgArray whSchdTMsgList = (WH_SCHDTMsgArray) EZDTBLAccessor.findByCondition(whSchdTMsg);

        if (whSchdTMsgList == null || whSchdTMsgList.getValidCount() < 1) {
            doErrorProcess(NFCM0576E, "Inbound Visibility");
            return false;
        }

        // Delete WH_SCHD
        for (int i = 0; i < whSchdTMsgList.getValidCount(); i++) {

            whSchdTMsg = whSchdTMsgList.no(i);
            EZDTBLAccessor.logicalRemove(whSchdTMsg);

            if (!RETURN_CD_NORMAL_END.equals(whSchdTMsg.getReturnCode())) {
                doErrorProcess(NFCM0576E, "Inbound Visibility");
                return false;
            }
        }

        return true;
    }

    /**
     * Call Shipping Plan Update API
     * @param shpgModeCd Update Mode
     * @param soDtl SHPG_ORD_DTLTMsg
     * @param confDtl SHPG_ORD_CONF_DTLTMsg
     * @param so SHPG_ORDTMsg
     */
    private void callShippingPlanUpdateAPI(String shpgModeCd, SHPG_ORD_DTLTMsg soDtl, SHPG_ORD_CONF_DTLTMsg confDtl, SHPG_ORDTMsg so) {

        if (SHPG_MODE_SHIPPED.equals(shpgModeCd) || SHPG_MODE_PARTIAL_SHIP.equals(shpgModeCd)) {

            NWZC003001PMsg msg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(msg.shpgModeCd, shpgModeCd);
            ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, soDtl.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(msg.shpgPlnNum, soDtl.shpgPlnNum.getValue());

            // Set blank if '*'
            ZYPEZDItemValueSetter.setValue(msg.carrCd, asteriskToEmpty(confDtl.vndCd.getValue()));
            ZYPEZDItemValueSetter.setValue(msg.bolNum, asteriskToEmpty(confDtl.bolNum.getValue()));
            ZYPEZDItemValueSetter.setValue(msg.proNum, asteriskToEmpty(confDtl.proNum.getValue()));

            // Invoice in and first data of the BOL
            ZYPEZDItemValueSetter.setValue(msg.spTotFuncFrtAmt, BigDecimal.ZERO);

            if (ZYPConstant.FLG_ON_Y.equals(confDtl.frtRelnFlg.getValue())) {

                if (FRT_CHRG_TO.CUSTOMER.equals(so.frtChrgToCd.getValue())) {

                    ZYPEZDItemValueSetter.setValue(msg.spTotFuncFrtAmt, confDtl.totFrtAmt.getValue());
                }
            }

            ZYPEZDItemValueSetter.setValue(msg.actlShipDt, confDtl.shipDtTmTs.getValue().substring(0, 8));
            ZYPEZDItemValueSetter.setValue(msg.ordQty, confDtl.shipQty.getValue());

            if (SHPG_MODE_PARTIAL_SHIP.equals(shpgModeCd)) {

                ZYPEZDItemValueSetter.setValue(msg.slsDt, ZYPDateUtil.getSalesDate(soDtl.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(msg.ordQty, soDtl.shipQty.getValue());
            }

            // Execute API
            executeShippingPlanUpdateAPI(msg);

        } else if (SHPG_MODE_INSHED.equals(shpgModeCd)) {

            NWZC003001PMsg msg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(msg.shpgModeCd, shpgModeCd);
            ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, soDtl.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(msg.shpgPlnNum, soDtl.shpgPlnNum.getValue());

            // Execute API
            executeShippingPlanUpdateAPI(msg);

        } else if (SHPG_MODE_HARDALLOCATED_SOCANCELLED.equals(shpgModeCd)) {

            // Not call when SHPG_QTY < 0 (Considering Item Change)
            if (soDtl.shpgQty.getValue().compareTo(BigDecimal.ZERO) >= 0) {

                NWZC003001PMsg msg = new NWZC003001PMsg();
                ZYPEZDItemValueSetter.setValue(msg.shpgModeCd, shpgModeCd);
                ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, soDtl.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(msg.shpgPlnNum, soDtl.shpgPlnNum.getValue());
                ZYPEZDItemValueSetter.setValue(msg.slsDt, ZYPDateUtil.getSalesDate(soDtl.glblCmpyCd.getValue()));

                // Execute API
                executeShippingPlanUpdateAPI(msg);
            }

        } else if (SHPG_MODE_SHIPPED_SOCLOSE.equals(shpgModeCd)) {

            NWZC003001PMsg msg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(msg.shpgModeCd, shpgModeCd);
            ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, so.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(msg.soNum, so.soNum.getValue());

            // Execute API
            executeShippingPlanUpdateAPI(msg);

        } else {

            // Other (No process)
        }
    }

    /**
     * Execute Shipping Plan Update API
     * @param msg NWZC003001PMsg
     */
    private void executeShippingPlanUpdateAPI(NWZC003001PMsg msg) {

        if (msg != null) {

            // Create parameter list
            List<NWZC003001PMsg> list = new ArrayList<NWZC003001PMsg>();
            list.add(msg);

            // Execute API
            NWZC003001 api = new NWZC003001();
            api.execute(list, onBatch);

            // Get message list
            List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

            if (!msgList.isEmpty()) {

                for (String xxMsgId : msgList) {

                    if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                        S21InfoLogOutput.println(NDMM0012E, new String[] {"NWZC003001" });
                        doErrorProcess(xxMsgId, msg);

                        // DB error
                        throw new S21AbendException(xxMsgId);

                    } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                        // Continue when Warning
                        doErrorProcess(xxMsgId, msg);
                    }
                }
            }
        }
    }

    /**
     * Call Inventory Update API
     * @param apiParam NLZC211001PMsg
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param cpoTMsg CPOTMsg
     */
    private void callInventoryUpdateAPI(NLZC211001PMsg apiParam, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg, SHPG_ORDTMsg shpgOrdTMsg, CPOTMsg cpoTMsg) {

        // Create parameter
        NLZC002001PMsg msgOut = new NLZC002001PMsg();
        NLZC002001PMsg msgIn = new NLZC002001PMsg();
        List<NLZC002001PMsg> list = new ArrayList<NLZC002001PMsg>();

        // Set common parameter
        msgOut.xxSysTp.setValue(NLZC002001.SYS_TP_OTBD);
        msgOut.glblCmpyCd.setValue(shpgOrdConfDtlTMsg.glblCmpyCd.getValue());
        msgOut.mdseCd.setValue(shpgOrdConfDtlTMsg.mdseCd.getValue());
        msgOut.stkStsCd.setValue(shpgOrdConfDtlTMsg.fromStkStsCd.getValue());
        msgOut.xxRqstQty.setValue(shpgOrdConfDtlTMsg.shipQty.getValue());
        msgOut.invtyTrxDt.setValue(ZYPDateUtil.getSalesDate(shpgOrdDtlTMsg.glblCmpyCd.getValue()));
        msgOut.sysSrcCd.setValue(shpgOrdDtlTMsg.sysSrcCd.getValue());
        msgOut.soNum.setValue(shpgOrdConfDtlTMsg.soNum.getValue());
        msgOut.soSlpNum.setValue(shpgOrdConfDtlTMsg.soSlpNum.getValue());
        msgOut.uomCd.setValue(PKG_UOM.EACH);

        // Set Serial Number
        List<String> shipSerNumList = getShipSerNumList(apiParam, shpgOrdConfDtlTMsg);

        if (shipSerNumList != null) {

            for (int i = 0; i < shipSerNumList.size(); i++) {

                ZYPEZDItemValueSetter.setValue(msgOut.serNumList.no(i).serNum, shipSerNumList.get(i));
            }

            msgOut.serNumList.setValidCount(shipSerNumList.size());

        }

        // Set blank if '*'
        msgOut.bolNum.setValue(asteriskToEmpty(shpgOrdConfDtlTMsg.bolNum.getValue()));
        msgOut.proNum.setValue(asteriskToEmpty(shpgOrdConfDtlTMsg.proNum.getValue()));
        msgOut.carrCd.setValue(asteriskToEmpty(shpgOrdConfDtlTMsg.vndCd.getValue()));

        // Copy (Out -> In)
        EZDMsg.copy(msgOut, null, msgIn, null);
        ZYPEZDItemValueSetter.setValue(msgIn.invtyLocCd, shpgOrdDtlTMsg.toInvtyLocCd);

        // Set parameter by each order type
        if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)) {

            // When 'RG', need to check if Expense (EP) or not
            if (ZYPConstant.FLG_ON_Y.equals(shpgOrdDtlTMsg.expFlg.getValue())) {

                // Stock Out
                msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
                msgOut.trxCd.setValue(TRX.MOVEMENT);
                msgOut.trxRsnCd.setValue(TRX_RSN.DOMESTIC_SHIPMENT_STOCK_OUT);
                msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_EXP);
                msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
                msgOut.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgOut.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgOut.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgOut);

                // Stock In
                msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
                msgIn.trxCd.setValue(TRX.MOVEMENT);
                msgIn.trxRsnCd.setValue(TRX_RSN.DOMESTIC_SHIPMENT_STOCK_IN);
                msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_EXP);
                msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
                msgIn.locStsCd.setValue(LOC_STS.IN_TRANSIT);
                msgIn.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgIn.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgIn.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgIn);

            } else {

                // Regular
                // Stock Out
                msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
                msgOut.trxCd.setValue(TRX.MOVEMENT);
                msgOut.trxRsnCd.setValue(TRX_RSN.DOMESTIC_SHIPMENT_STOCK_OUT);
                msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_REG_SLS);
                msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
                msgOut.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgOut.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgOut.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgOut);

                // Stock In
                msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
                msgIn.trxCd.setValue(TRX.MOVEMENT);
                msgIn.trxRsnCd.setValue(TRX_RSN.DOMESTIC_SHIPMENT_STOCK_IN);
                msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_REG_SLS);
                msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
                msgIn.locStsCd.setValue(LOC_STS.IN_TRANSIT);
                msgIn.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgIn.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgIn.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgIn);
            }

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)) {

            boolean isShpgModeInshed = isShpgModeInshed(shpgOrdDtlTMsg);

            String cpoOrdTpCd = "";

            if (cpoTMsg != null) {

                cpoOrdTpCd = cpoTMsg.cpoOrdTpCd.getValue();
            }

            if (isShpgModeInshed == false && CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd)) {

                String trialFinalPrpsCd = "";

                if (TRIAL_FINAL_PRPS.TRIAL_SALE.equals(trialFinalPrpsCd)) {

                    // TRIAL_FOR_SALE
                    executeInventoryUpdateAPIForTS(msgOut, msgIn, list, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg);

                } else if (TRIAL_FINAL_PRPS.TRIAL_USE.equals(trialFinalPrpsCd)) {

                    // TRIAL_FOR_USE;
                    executeInventoryUpdateAPIForTU(msgOut, msgIn, list, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg);
                }

            } else if (isShpgModeInshed == false && CPO_ORD_TP.LOAN.equals(cpoOrdTpCd)) {

                // LOAN;
                executeInventoryUpdateAPIForLO(msgOut, msgIn, list, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg);

            } else {

                // Stock Out
                msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
                msgOut.trxCd.setValue(TRX.MOVEMENT);

                if (isShpgModeInshed) {

                    msgOut.trxRsnCd.setValue(TRX_RSN.EXPORT_SALE_SHIPMENT_STOCK_OUT);

                } else {

                    msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_REG_SLS);
                    msgOut.trxRsnCd.setValue(TRX_RSN.DOMESTIC_SHIPMENT_STOCK_OUT);
                }

                msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
                msgOut.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgOut.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgOut.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgOut.shipToCustCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
                msgOut.sellToCustCd.setValue(shpgOrdTMsg.sellToCustCd.getValue());
                msgOut.billToCustCd.setValue(shpgOrdTMsg.billToCustCd.getValue());
                list.add(msgOut);

                // Stock In
                msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
                msgIn.trxCd.setValue(TRX.MOVEMENT);

                if (isShpgModeInshed) {

                    msgIn.trxRsnCd.setValue(TRX_RSN.EXPORT_SALE_SHIPMENT_STOCK_IN);
                    msgIn.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                    msgIn.locStsCd.setValue(LOC_STS.IN_SHED);

                } else {

                    msgIn.trxRsnCd.setValue(TRX_RSN.DOMESTIC_SHIPMENT_STOCK_IN);
                    msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_REG_SLS);
                    msgIn.invtyLocCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
                    msgIn.locStsCd.setValue(LOC_STS.IN_TRANSIT);
                }

                msgIn.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgIn.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgIn.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgIn.shipToCustCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
                msgIn.sellToCustCd.setValue(shpgOrdTMsg.sellToCustCd.getValue());
                msgIn.billToCustCd.setValue(shpgOrdTMsg.billToCustCd.getValue());
                list.add(msgIn);

                // Execute API
                executeInventoryUpdateAPI(list);
            }

        } else if (SCE_ORD_TP.LOAN.equals(sceOrdTpCd)) {

            executeInventoryUpdateAPIForLO(msgOut, msgIn, list, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg);

        } else if (SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd)) {

            executeInventoryUpdateAPIForTU(msgOut, msgIn, list, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg);

        } else if (SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd)) {

            executeInventoryUpdateAPIForTS(msgOut, msgIn, list, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shpgOrdTMsg);

        } else if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

            // Check Loan
            String type = judgeLoan(shpgOrdDtlTMsg);
            // QC#63527 2024/03/14 Start
            String whOrderCd = null;
            // QC#63527 2024/03/14 End

            // Loan
            if (ORD_TP_RS_LOAN.equals(type)) {

                // QC#63527 2024/03/14 Start
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", shpgOrdConfDtlTMsg.glblCmpyCd.getValue());
                queryParam.put("whCd", shpgOrdConfDtlTMsg.whCd.getValue()); 
                whOrderCd = (String) ssmBatchClient.queryObject("getInvtyOwnrCd", queryParam);
                // QC#63527 2024/03/14 End

                // Stock Out
                msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
                msgOut.trxCd.setValue(TRX.MOVEMENT);
                // QC#63527 2024/03/14 Start
                if (INVTY_OWNR.GMD.equals(whOrderCd)) {
                    msgOut.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_LOAN_SHIPMENT_STOCK_OUT);
                } else {
                    msgOut.trxRsnCd.setValue(TRX_RSN.LOAN_SHIPMENT_STOCK_OUT);
                }
                // QC#63527 2024/03/14 End
                msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_SHIP);
                msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
                msgOut.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgOut.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgOut.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgOut);

                // Stock In
                msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
                msgIn.trxCd.setValue(TRX.MOVEMENT);
                // QC#63527 2024/03/14 Start
                if (INVTY_OWNR.GMD.equals(whOrderCd)) {
                    msgIn.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_LOAN_SHIPMENT_STOCK_IN);
                } else {
                    msgIn.trxRsnCd.setValue(TRX_RSN.LOAN_SHIPMENT_STOCK_IN);
                }
                // QC#63527 2024/03/14 End
                msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_SHIP);
                // QC#63527 2024/03/14 Start
                if (INVTY_OWNR.GMD.equals(whOrderCd)) {
                    msgIn.invtyLocCd.setValue(ZYPCodeDataUtil.getVarCharConstValue("LOAN_DUMMY_GMD_WH_CD", shpgOrdConfDtlTMsg.glblCmpyCd.getValue()));
                } else {
                    msgIn.invtyLocCd.setValue(ZYPCodeDataUtil.getVarCharConstValue("LOAN_DUMMY_CSA_WH_CD", shpgOrdConfDtlTMsg.glblCmpyCd.getValue()));
                }
                // QC#63527 2024/03/14 End
                msgIn.locStsCd.setValue(LOC_STS.TRIAL_USE);
                msgIn.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgIn.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgIn.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgIn);

                // Loan (Expense)
            } else if (ORD_TP_RS_LOAN_EXP.equals(type)) {

                // QC#63527 2024/03/14 Start
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", shpgOrdConfDtlTMsg.glblCmpyCd.getValue());
                queryParam.put("whCd", shpgOrdConfDtlTMsg.whCd.getValue());
                whOrderCd = (String) ssmBatchClient.queryObject("getInvtyOwnrCd", queryParam);
                // QC#63527 2024/03/14 End
                // Stock Out
                msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
                msgOut.trxCd.setValue(TRX.EXPENSE_SHIPMENT);
                // QC#63527 2024/03/14 Start
                if (INVTY_OWNR.GMD.equals(whOrderCd)) {
                    msgOut.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_EXPENSE_LOAN_SHIPMENT_STOCK_OUT);
                } else {
                    msgOut.trxRsnCd.setValue(TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT);
                }
                // QC#63527 2024/03/14 End
                msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_SHIP);
                msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
                msgOut.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgOut.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgOut.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgOut);

                // Regular
            } else {

                // Get Retail WH
                RTL_WHTMsg fromRtlWhTMsg = getRtlWh(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.whCd.getValue());

                if (isNonCSAAsset(fromRtlWhTMsg)) {

                    // Non CSA Asset
                    msgOut.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_OUT);
                    msgIn.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_IN);

                } else if (isAsset(fromRtlWhTMsg)) {

                    // Asset
                    msgOut.trxRsnCd.setValue(TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT_ASSET);
                    msgIn.trxRsnCd.setValue(TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_IN_ASSET);

                } else {

                    msgOut.trxRsnCd.setValue(TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT);
                    msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_ROSS);
                    msgIn.trxRsnCd.setValue(TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_IN);
                    msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_ROSS);
                }

                // Stock Out
                msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
                msgOut.trxCd.setValue(TRX.MOVEMENT);
                msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
                msgOut.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgOut.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgOut.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgOut);

                // Stock In
                msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
                msgIn.trxCd.setValue(TRX.MOVEMENT);
                msgIn.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                msgIn.locStsCd.setValue(LOC_STS.WAITING_FOR_INSTALLATION);
                msgIn.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
                msgIn.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
                msgIn.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
                msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
                list.add(msgIn);
            }

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)) {

            // Get Retail WH
            RTL_WHTMsg fromRtlWhTMsg = getRtlWh(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.whCd.getValue());
            RTL_WHTMsg toRtlWhTMsg = getRtlWh(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdDtlTMsg.shipToRtlWhCd.getValue());

            // Trx Reason
            if (isNonCSAAsset(fromRtlWhTMsg)) {

                // Non CSA Asset
                msgOut.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_STOCK_OUT);
                msgIn.trxRsnCd.setValue(TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN);

            } else if (isAsset(fromRtlWhTMsg)) {

                // Asset
                msgOut.trxRsnCd.setValue(TRX_RSN.ASSET_WAREHOUSE_TRANSFER_STOCK_OUT);
                msgIn.trxRsnCd.setValue(TRX_RSN.ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN);

            } else if (isShowroomTransfer(fromRtlWhTMsg, toRtlWhTMsg)) {

                // Showroom Transfer
                msgOut.trxRsnCd.setValue(TRX_RSN.SHOWROOM_TRANSFER_STOCK_OUT);
                msgIn.trxRsnCd.setValue(TRX_RSN.SHOWROOM_TRANSFER_IN_TRANSIT_STOCK_IN);

            } else {

                msgOut.trxRsnCd.setValue(TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT);
                msgIn.trxRsnCd.setValue(TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN);
            }

            // Stock Out
            msgOut.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
            msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            list.add(msgOut);

            // Stock In
            msgIn.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            msgIn.locStsCd.setValue(LOC_STS.IN_TRANSIT_WH);
            msgIn.cpoOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgIn.cpoDtlLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgIn.cpoDtlLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
            msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)) {

            // Get Retail WH
            RTL_WHTMsg fromRtlWhTMsg = getRtlWh(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.whCd.getValue());

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.ADJUSTMENT);

            if (isAsset(fromRtlWhTMsg)) {

                // Asset
                msgOut.trxRsnCd.setValue(TRX_RSN.ASSET_DISPOSAL);
            } else {

                msgOut.trxRsnCd.setValue(TRX_RSN.DISPOSAL);

            }

            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.invtyOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.invtyOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            list.add(msgOut);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.LOCATION_STATUS_CHANGE_STOCK_OUT);
            msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.wrkOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.wrkOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            list.add(msgOut);

            // Stock In
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.trxRsnCd.setValue(TRX_RSN.LOCATION_STATUS_CHANGE_STOCK_IN);
            msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            msgIn.wrkOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgIn.wrkOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());

            if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)) {

                msgIn.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_COMPONENT);

            } else {

                msgIn.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_KIT);
            }

            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)) {

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.VENDOR_TRANSFER_STOCK_OUT);
            msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.vndCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.wrkOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.wrkOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            list.add(msgOut);

            // Stock In
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.trxRsnCd.setValue(TRX_RSN.VENDOR_TRANSFER_STOCK_IN);
            msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            msgIn.vndCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            msgIn.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_ORIGINAL);
            msgIn.wrkOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgIn.wrkOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd)) {

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.PURCHASE_STOCK_IN);
            msgOut.trxRsnCd.setValue(TRX_RSN.DOMESTIC_VENDOR_RETURN);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.vndRtrnNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.trxLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
            msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.vndCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            list.add(msgOut);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.EXPORT_VENDOR_RETURN_STOCK_OUT);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.vndRtrnNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.trxLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
            msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.vndCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            msgOut.shipToCustCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            msgOut.sellToCustCd.setValue(shpgOrdTMsg.sellToCustCd.getValue());
            msgOut.billToCustCd.setValue(shpgOrdTMsg.billToCustCd.getValue());
            list.add(msgOut);

            // Stock In
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.trxRsnCd.setValue(TRX_RSN.EXPORT_VENDOR_RETURN_STOCK_IN);
            msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            msgIn.locStsCd.setValue(LOC_STS.IN_SHED);
            msgIn.vndRtrnNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgIn.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgIn.trxLineSubNum.setValue(shpgOrdDtlTMsg.trxLineSubNum.getValue());
            msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgIn.vndCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            msgIn.shipToCustCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            msgIn.sellToCustCd.setValue(shpgOrdTMsg.sellToCustCd.getValue());
            msgIn.billToCustCd.setValue(shpgOrdTMsg.billToCustCd.getValue());
            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, shpgOrdDtlTMsg.prchReqNum);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, shpgOrdDtlTMsg.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, shpgOrdDtlTMsg.prchReqLineSubNum);
            prchReqDtlTMsg = (PRCH_REQ_DTLTMsg) S21ApiTBLAccessor.findByKey(prchReqDtlTMsg);

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT);
            msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.invtyOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.invtyOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            list.add(msgOut);

            // Stock In
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.trxRsnCd.setValue(TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN);
            msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            msgIn.locStsCd.setValue(LOC_STS.IN_TRANSIT_WH);
            msgIn.stkStsCd.setValue(prchReqDtlTMsg.toStkStsCd.getValue());
            msgIn.invtyOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgIn.invtyOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)) {

            PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, shpgOrdDtlTMsg.prchReqNum);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, shpgOrdDtlTMsg.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, shpgOrdDtlTMsg.prchReqLineSubNum);
            prchReqDtlTMsg = (PRCH_REQ_DTLTMsg) S21ApiTBLAccessor.findByKey(prchReqDtlTMsg);

            POTMsg poTMsg = new POTMsg();
            ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, prchReqDtlTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, prchReqDtlTMsg.poOrdNum);
            poTMsg = (POTMsg) S21ApiTBLAccessor.findByKey(poTMsg);

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT);
            msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.poOrdNum.setValue(prchReqDtlTMsg.poOrdNum.getValue());
            msgOut.poOrdDtlLineNum.setValue(prchReqDtlTMsg.poOrdDtlLineNum.getValue());
            msgOut.invtyOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.invtyOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.shipToCustCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            msgOut.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.vndCd.setValue(poTMsg.vndCd.getValue());
            list.add(msgOut);

            // Stock In
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.trxRsnCd.setValue(TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_IN);
            msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            msgIn.invtyLocCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            // QC#26585 MOD START
            //msgIn.locStsCd.setValue(LOC_STS.IN_TRANSIT);
            msgIn.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
            // QC#26585 MOD END
            msgIn.poOrdNum.setValue(prchReqDtlTMsg.poOrdNum.getValue());
            msgIn.poOrdDtlLineNum.setValue(prchReqDtlTMsg.poOrdDtlLineNum.getValue());
            msgIn.invtyOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgIn.invtyOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgIn.shipToCustCd.setValue(shpgOrdDtlTMsg.toInvtyLocCd.getValue());
            msgIn.shipFromLocCustCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgIn.vndCd.setValue(poTMsg.vndCd.getValue());
            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)) {

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.ADJUSTMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.REFURB_EXPENSE_SHIP_OUT);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.invtyOrdNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());
            msgOut.invtyOrdLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.vndCd.setValue(shpgOrdTMsg.shipToCustCd.getValue());
            list.add(msgOut);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {

            // Main Machine check
            boolean prntConfigMach = false;

            Map<String, String> paramConfigMstr = new HashMap<String, String>();
            paramConfigMstr.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
            paramConfigMstr.put("trxHdrNum", shpgOrdDtlTMsg.trxHdrNum.getValue());
            paramConfigMstr.put("trxLineNum", shpgOrdDtlTMsg.trxLineNum.getValue());
            // QC#13837
            String mainUnitSerial = (String) ssmBatchClient.queryObject("getRmnfMainMachineSerial", paramConfigMstr);

            if (mainUnitSerial != null) {
                prntConfigMach = true;
            }

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.REMANUFACTURING);

            if (prntConfigMach) {

                msgOut.trxRsnCd.setValue(TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT);

            } else {

                msgOut.trxRsnCd.setValue(TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT_ACSRY);
            }

            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.invtyTrxSlpNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());

            list.add(msgOut);

            // Stock In
            Map<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
            param.put("trxHdrNum", shpgOrdDtlTMsg.trxHdrNum.getValue());
            param.put("trxLineNum", shpgOrdDtlTMsg.trxLineNum.getValue());
            param.put("sceOrdTpCd", sceOrdTpCd);
            Map<String, Object> rwsInfo = (Map<String, Object>) ssmBatchClient.queryObject("getRwsInfoForRemanConfChg", param);

            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.REMANUFACTURING);

            if (prntConfigMach) {

                msgIn.trxRsnCd.setValue(TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN);

            } else {

                msgIn.trxRsnCd.setValue(TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN_ACSRY);
            }

            ZYPEZDItemValueSetter.setValue(msgIn.mdseCd, (String) rwsInfo.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(msgIn.stkStsCd, (String) rwsInfo.get("INVTY_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(msgIn.xxRqstQty, (BigDecimal) rwsInfo.get("RWS_QTY"));
            ZYPEZDItemValueSetter.setValue(msgIn.invtyLocCd, (String) rwsInfo.get("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(msgIn.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(msgIn.poRcvNum, (String) rwsInfo.get("PO_RCV_NUM"));
            ZYPEZDItemValueSetter.setValue(msgIn.poRcvLineNum, (String) rwsInfo.get("PO_RCV_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(msgIn.rwsNum, (String) rwsInfo.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(msgIn.rwsRefNum, (String) rwsInfo.get("RWS_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(msgIn.rwsDtlLineNum, (String) rwsInfo.get("RWS_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(msgIn.trxLineNum, shpgOrdDtlTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(msgIn.invtyTrxSlpNum, shpgOrdDtlTMsg.trxHdrNum.getValue());

            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd)) {

            Map<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
            param.put("soNum", shpgOrdDtlTMsg.soNum.getValue());
            param.put("soSlpNum", shpgOrdDtlTMsg.soSlpNum.getValue());
            Map<String, Object> rwsInfo = (Map<String, Object>) ssmBatchClient.queryObject("getRwsInfoForRemanTrsf", param);

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.REMAN_PARTS_TRANSFER_STOCK_OUT);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgOut.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.invtyTrxSlpNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());

            list.add(msgOut);

            // Stock In
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.trxRsnCd.setValue(TRX_RSN.REMAN_PARTS_TRANSFER_STOCK_IN);
            msgIn.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgIn.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_REMAN);
            msgIn.rwsNum.setValue((String) rwsInfo.get("RWS_NUM"));
            msgIn.rwsRefNum.setValue((String) rwsInfo.get("RWS_REF_NUM"));
            msgIn.rwsDtlLineNum.setValue((String) rwsInfo.get("RWS_DTL_LINE_NUM"));
            msgIn.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgIn.invtyTrxSlpNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());

            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd)) {

            Map<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
            param.put("soNum", shpgOrdDtlTMsg.soNum.getValue());
            param.put("soSlpNum", shpgOrdDtlTMsg.soSlpNum.getValue());
            Map<String, Object> rwsInfo = (Map<String, Object>) ssmBatchClient.queryObject("getRwsInfoForRemanTrsf", param);

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.MOVEMENT);
            msgOut.trxRsnCd.setValue(TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_OUT);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_REMAN);
            msgOut.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.invtyTrxSlpNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());

            list.add(msgOut);

            // Stock In
            msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            msgIn.trxCd.setValue(TRX.MOVEMENT);
            msgIn.trxRsnCd.setValue(TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_IN);
            msgIn.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgIn.locStsCd.setValue(LOC_STS.DC_STOCK);
            msgIn.rwsNum.setValue((String) rwsInfo.get("RWS_NUM"));
            msgIn.rwsRefNum.setValue((String) rwsInfo.get("RWS_REF_NUM"));
            msgIn.rwsDtlLineNum.setValue((String) rwsInfo.get("RWS_DTL_LINE_NUM"));
            msgIn.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgIn.invtyTrxSlpNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());

            list.add(msgIn);

            // Execute API
            executeInventoryUpdateAPI(list);

        } else if (SCE_ORD_TP.REMAN_PARTS_USAGE.equals(sceOrdTpCd)) {

            // Stock Out
            msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            msgOut.trxCd.setValue(TRX.REMANUFACTURING);
            msgOut.trxRsnCd.setValue(TRX_RSN.PARTS_USAGE_FOR_REMAN);
            msgOut.invtyLocCd.setValue(shpgOrdConfDtlTMsg.whCd.getValue());
            msgOut.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_REMAN);
            msgOut.trxLineNum.setValue(shpgOrdDtlTMsg.trxLineNum.getValue());
            msgOut.invtyTrxSlpNum.setValue(shpgOrdDtlTMsg.trxHdrNum.getValue());

            list.add(msgOut);

            // Execute API
            executeInventoryUpdateAPI(list);
        }
    }

    /**
     * executeInventoryUpdateAPIForTS
     * @param msgOut NLZC002001PMsg
     * @param msgIn NLZC002001PMsg
     * @param list List<NLZC002001PMsg>
     * @param soDtl SHPG_ORD_DTLTMsg
     * @param confDtl SHPG_ORD_CONF_DTLTMsg
     * @param so SHPG_ORDTMsg
     */
    private void executeInventoryUpdateAPIForTS(NLZC002001PMsg msgOut, NLZC002001PMsg msgIn, List<NLZC002001PMsg> list, SHPG_ORD_DTLTMsg soDtl, SHPG_ORD_CONF_DTLTMsg confDtl, SHPG_ORDTMsg so) {

        // Stock Out
        msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        msgOut.trxCd.setValue(TRX.MOVEMENT);
        msgOut.trxRsnCd.setValue(TRX_RSN.TRIAL_SHIPMENT_STOCK_OUT);
        msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_SHIP);
        msgOut.invtyLocCd.setValue(confDtl.whCd.getValue());
        msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
        msgOut.cpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgOut.cpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgOut.cpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgOut.origCpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgOut.origCpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgOut.origCpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgOut.shipFromLocCustCd.setValue(confDtl.whCd.getValue());
        list.add(msgOut);

        // Stock In
        msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        msgIn.trxCd.setValue(TRX.MOVEMENT);
        msgIn.trxRsnCd.setValue(TRX_RSN.TRIAL_SHIPMENT_STOCK_IN);
        msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_SHIP);
        msgIn.invtyLocCd.setValue(so.shipToCustCd.getValue());
        msgIn.locStsCd.setValue(LOC_STS.TRIAL_SALE);
        msgIn.cpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgIn.cpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgIn.cpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgIn.origCpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgIn.origCpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgIn.origCpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgIn.shipFromLocCustCd.setValue(confDtl.whCd.getValue());
        list.add(msgIn);

        // Execute API
        executeInventoryUpdateAPI(list);
    }

    /**
     * executeInventoryUpdateAPIForTU
     * @param msgOut NLZC002001PMsg
     * @param msgIn NLZC002001PMsg
     * @param list List<NLZC002001PMsg>
     * @param soDtl SHPG_ORD_DTLTMsg
     * @param confDtl SHPG_ORD_CONF_DTLTMsg
     * @param so SHPG_ORDTMsg
     */
    private void executeInventoryUpdateAPIForTU(NLZC002001PMsg msgOut, NLZC002001PMsg msgIn, List<NLZC002001PMsg> list, SHPG_ORD_DTLTMsg soDtl, SHPG_ORD_CONF_DTLTMsg confDtl, SHPG_ORDTMsg so) {

        // Stock Out
        msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        msgOut.trxCd.setValue(TRX.MOVEMENT);
        msgOut.trxRsnCd.setValue(TRX_RSN.TRIAL_SHIPMENT_STOCK_OUT);
        msgOut.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_SHIP);
        msgOut.invtyLocCd.setValue(confDtl.whCd.getValue());
        msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
        msgOut.cpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgOut.cpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgOut.cpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgOut.origCpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgOut.origCpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgOut.origCpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgOut.shipFromLocCustCd.setValue(confDtl.whCd.getValue());
        list.add(msgOut);

        // Stock In
        msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        msgIn.trxCd.setValue(TRX.MOVEMENT);
        msgIn.trxRsnCd.setValue(TRX_RSN.TRIAL_SHIPMENT_STOCK_IN);
        msgIn.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_TRIAL_SHIP);
        msgIn.invtyLocCd.setValue(so.shipToCustCd.getValue());
        msgIn.locStsCd.setValue(LOC_STS.TRIAL_USE);
        msgIn.cpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgIn.cpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgIn.cpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgIn.origCpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgIn.origCpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgIn.origCpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgIn.shipFromLocCustCd.setValue(confDtl.whCd.getValue());
        list.add(msgIn);

        // Execute API
        executeInventoryUpdateAPI(list);
    }

    /**
     * executeInventoryUpdateAPIForLO
     * @param msgOut NLZC002001PMsg
     * @param msgIn NLZC002001PMsg
     * @param list List<NLZC002001PMsg>
     * @param soDtl SHPG_ORD_DTLTMsg
     * @param confDtl SHPG_ORD_CONF_DTLTMsg
     * @param so SHPG_ORDTMsg
     */
    private void executeInventoryUpdateAPIForLO(NLZC002001PMsg msgOut, NLZC002001PMsg msgIn, List<NLZC002001PMsg> list, SHPG_ORD_DTLTMsg soDtl, SHPG_ORD_CONF_DTLTMsg confDtl, SHPG_ORDTMsg so) {

        // Stock Out
        msgOut.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        msgOut.trxCd.setValue(TRX.LOAN_SHIPMENT);
        msgOut.trxRsnCd.setValue(TRX_RSN.LOAN_SHIPMENT_STOCK_OUT);
        msgOut.invtyLocCd.setValue(confDtl.whCd.getValue());
        msgOut.locStsCd.setValue(LOC_STS.DC_STOCK);
        msgOut.cpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgOut.cpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgOut.cpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgOut.origCpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgOut.origCpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgOut.origCpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgOut.shipFromLocCustCd.setValue(confDtl.whCd.getValue());
        list.add(msgOut);

        // Stock In
        msgIn.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        msgIn.trxCd.setValue(TRX.LOAN_SHIPMENT);
        msgIn.trxRsnCd.setValue(TRX_RSN.LOAN_SHIPMENT_STOCK_IN);
        msgIn.invtyLocCd.setValue(so.shipToCustCd.getValue());
        msgIn.locStsCd.setValue(LOC_STS.LOAN);
        msgIn.cpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgIn.cpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgIn.cpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgIn.origCpoOrdNum.setValue(soDtl.trxHdrNum.getValue());
        msgIn.origCpoDtlLineNum.setValue(soDtl.trxLineNum.getValue());
        msgIn.origCpoDtlLineSubNum.setValue(soDtl.trxLineSubNum.getValue());
        msgIn.shipFromLocCustCd.setValue(confDtl.whCd.getValue());
        list.add(msgIn);

        // Execute API
        executeInventoryUpdateAPI(list);
    }

    /**
     * getCpo
     * @param cpoNum String
     * @param glblCmpyCd String
     * @return CPOTMsg
     */
    private CPOTMsg getCpo(String cpoNum, String glblCmpyCd) {

        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoNum);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
        cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);

        // No data (impossible)
        if (cpoTMsg == null) {

            doErrorProcess(NLZM2030E, cpoNum);
            throw new S21AbendException(NLZM2030E);
        }

        return cpoTMsg;
    }

    /**
     * isShpgModeInshed
     * @param SHPG_ORD_DTLTMsg soDtl
     * @return boolean
     */
    private boolean isShpgModeInshed(SHPG_ORD_DTLTMsg soDtl) {

        if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)) {

            String revRecogMethCd = getCpo(soDtl.trxHdrNum.getValue(), soDtl.glblCmpyCd.getValue()).revRecogMethCd.getValue();

            if (REV_RECOG_METH.A_INVOICE_FINAL.equals(revRecogMethCd)) {

                return true; // In-Shed
            }

        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            return true; // In-Shed
        }

        return false; // In-Transit
    }

    /**
     * Execute Inventory Update API
     * @param list List<NLZC002001PMsg>
     */
    private void executeInventoryUpdateAPI(List<NLZC002001PMsg> list) {

        if (!list.isEmpty()) {

            NLZC002001 api = new NLZC002001();
            api.execute(list, onBatch);

            // Get message list
            for (int i = 0; i < list.size(); i++) {

                NLZC002001PMsg msg = (NLZC002001PMsg) list.get(i);
                List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

                if (!msgList.isEmpty()) {

                    for (String xxMsgId : msgList) {

                        if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                            S21InfoLogOutput.println(NDMM0012E, new String[] {"NLZC002001" });
                            doErrorProcess(xxMsgId, msg);

                            // DB error
                            throw new S21AbendException(NLZM2030E);

                        } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                            // QC#53009
                            if (NLZM0040W.equals(xxMsgId)) {
                                xxMsgId = NLZM2521E;
                            }
                            // Continue when Warning
                            doErrorProcess(xxMsgId, msg);
                        }
                    }
                }
            }
        }
    }

    /**
     * Call Inventory Order Update API
     * @param shpgOrdTMsg SHPG_ORDTMsg
     */
    private void callInventoryOrderUpdateAPI(SHPG_ORDTMsg shpgOrdTMsg) {

        // Get target inventory order number and execute API
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
        queryParam.put("soNum", shpgOrdTMsg.soNum.getValue());
        this.ssmBatchClient.queryObject("getShpgOrdDtl", queryParam, new InventoryOrderCloseProcess());
    }

    /**
     * Inventory Order Close Process
     */
    private class InventoryOrderCloseProcess extends S21SsmVoidResultSetHandlerSupport {

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            String glblCmpyCd = null;
            NLZC003001PMsg msg = null;
            List<String> trxHdrNumList = new ArrayList<String>();

            // Detail Close
            while (rs.next()) {

                String shpgStsCd = nullToEmpty(rs.getString(SHPG_STS_CD));

                boolean execInvtyOrdApiFlg = false;

                glblCmpyCd = nullToEmpty(rs.getString(GLBL_CMPY_CD));
                String trxHdrNum = nullToEmpty(rs.getString(TRX_HDR_NUM));
                String trxLineNum = nullToEmpty(rs.getString(TRX_LINE_NUM));

                if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {

                    // Not call when DC Transfer (Called by other program later)
                    if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

                        continue;

                    } else if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd) || SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)) {

                        // Header Close Only. Skip Detail process.

                    } else {

                        // Close by Line (Ship)
                        msg = new NLZC003001PMsg();
                        ZYPEZDItemValueSetter.setValue(msg.xxProcTpCd, NLZC003001.PROC_TP_CLO);
                        ZYPEZDItemValueSetter.setValue(msg.xxDtTpCd, NLZC003001.DT_TP_DTL);
                        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(msg.invtyOrdNum, trxHdrNum);
                        ZYPEZDItemValueSetter.setValue(msg.invtyOrdLineNum, trxLineNum);

                        execInvtyOrdApiFlg = true;
                    }

                } else if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

                    if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd) || SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)) {

                        // Header Close Only. Skip Detail process.

                    } else {

                        // Close by Line (Cancel)
                        msg = new NLZC003001PMsg();
                        ZYPEZDItemValueSetter.setValue(msg.xxProcTpCd, NLZC003001.PROC_TP_CANC);
                        ZYPEZDItemValueSetter.setValue(msg.xxDtTpCd, NLZC003001.DT_TP_DTL);
                        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(msg.invtyOrdNum, trxHdrNum);
                        ZYPEZDItemValueSetter.setValue(msg.invtyOrdLineNum, trxLineNum);

                        execInvtyOrdApiFlg = true;
                    }
                }

                // Execute API
                if (execInvtyOrdApiFlg) {

                    executeInventoryOrderUpdateAPI(msg);
                }

                if (trxHdrNumList.isEmpty() || !trxHdrNumList.contains(trxHdrNum)) {

                    trxHdrNumList.add(trxHdrNum);
                }
            }

            // Header Close
            for (String invtyOrdNum : trxHdrNumList) {

                // Close by Header
                msg = new NLZC003001PMsg();
                ZYPEZDItemValueSetter.setValue(msg.xxProcTpCd, NLZC003001.PROC_TP_HDR_CLO);
                ZYPEZDItemValueSetter.setValue(msg.xxDtTpCd, NLZC003001.DT_TP_HDR);
                ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(msg.invtyOrdNum, invtyOrdNum);

                // Execute API
                executeInventoryOrderUpdateAPI(msg);
            }
        }

        /**
         * Execute Inventory Order Update API
         * @param msg NLZC003001PMsg
         */
        private void executeInventoryOrderUpdateAPI(NLZC003001PMsg msg) {

            if (msg != null) {

                NLZC003001 api = new NLZC003001();
                api.execute(msg, onBatch);

                // Get message list
                List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
                if (!msgList.isEmpty()) {
                    for (String xxMsgId : msgList) {

                        if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                            S21InfoLogOutput.println(NDMM0012E, new String[] {"NLZC003001" });
                            doErrorProcess(xxMsgId, msg);
                            throw new S21AbendException(NLZM2030E);

                        } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                            // Continue when Warning
                            doErrorProcess(xxMsgId, msg);
                        }
                    }
                }
            }
        }
    }

    /**
     * Call Work Order Update API
     * @param so SHPG_ORDTMsg
     */
    private void callWorkOrderUpdateAPI(SHPG_ORDTMsg so) {

        NPZC002001PMsg pMsg = null;

        if (detailShippingStatus == DetailShippingStatus.ALL_SHIPPED) {

            // Close by SO (Ship)
            pMsg = new NPZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.wrkOrdStsCd, WRK_ORD_STS.SHIPPED);
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, so.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.wrkOrdNum, so.trxHdrNum.getValue());

        } else if (detailShippingStatus == DetailShippingStatus.ALL_CANCELLED) {

            // Close by SO (Cancel)
            pMsg = new NPZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.wrkOrdStsCd, WRK_ORD_STS.SO_CANCELLED);
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, so.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.wrkOrdNum, so.trxHdrNum.getValue());
        }

        if (pMsg != null) {

            NPZC002001 api = new NPZC002001();
            api.execute(pMsg, onBatch);

            // Get message list
            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);

            if (!msgList.isEmpty()) {

                for (String xxMsgId : msgList) {

                    if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                        S21InfoLogOutput.println(NDMM0012E, new String[] {"NPZC002001" });
                        doErrorProcess(xxMsgId, pMsg);
                        throw new S21AbendException(NLZM2030E);

                    } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                        // Continue when Warning
                        doErrorProcess(xxMsgId, pMsg);
                    }
                }
            }
        }
    }

    /**
     * Call PO Receiving API
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @return PO Receiving#
     */
    private String callPoReceivingAPI(SHPG_ORDTMsg shpgOrdTMsg) {

        String poRcvFromLocTpCd = null;
        String poRcvFromLocCd = null;
        String whCd = null;
        String poRcvEtaDt = null;
        String poRcvTrxHdrNum = null;
        String poRcvNum = null;
        String rwsRefNum = null;
        String sysSrcCd = null;

        // Get SO detail
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
        queryParam.put("soNum", shpgOrdTMsg.soNum.getValue());
        List<String> list = new ArrayList<String>();
        list.add(SHPG_STS.SHIPPED);
        queryParam.put("list", list);
        List<SHPG_ORD_DTLTMsg> shpgOrdDtlTMsgList = (List<SHPG_ORD_DTLTMsg>) this.ssmBatchClient.queryObjectList("getShpgOrdDtlByShpgStsCd", queryParam);

        if (shpgOrdDtlTMsgList.isEmpty()) {

            doErrorProcess(NLZM2030E, shpgOrdTMsg);
            throw new S21AbendException(NLZM2030E);
        }

        NLZC201001PMsg msg = new NLZC201001PMsg();

        // Set parameter
        if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd) || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            // Set list parameter
            String inlineCarrCd = "";

            for (int i = 0; i < shpgOrdDtlTMsgList.size(); i++) {

                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) shpgOrdDtlTMsgList.get(i);

                // Get shpg ord conf
                List<SHPG_ORD_CONF_DTLTMsg> confDtlList = getShpgOrdConfDtlList(shpgOrdDtlTMsg.glblCmpyCd.getValue(), shpgOrdDtlTMsg.soNum.getValue(), shpgOrdDtlTMsg.soSlpNum.getValue(), null, null);

                // Get PR Detail
                PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, shpgOrdDtlTMsg.prchReqNum);
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, shpgOrdDtlTMsg.prchReqLineNum);
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, shpgOrdDtlTMsg.prchReqLineSubNum);
                prchReqDtlTMsg = (PRCH_REQ_DTLTMsg) S21ApiTBLAccessor.findByKey(prchReqDtlTMsg);

                if (msg.OrdInfoLIst.length() <= i) {

                    break;
                }

                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).mdseCd, shpgOrdDtlTMsg.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).stkStsCd, prchReqDtlTMsg.toStkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).poRcvQty, shpgOrdDtlTMsg.shipQty.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).poRcvTrxLineNum, shpgOrdDtlTMsg.soSlpNum.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).carrCd, emptyToAsterisk(confDtlList.get(0).vndCd.getValue()));
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).bolNum, emptyToAsterisk(confDtlList.get(0).bolNum.getValue()));
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).proNum, emptyToAsterisk(confDtlList.get(0).proNum.getValue()));

                // START 2021/01/18 A.Marte [QC#58256, MOD]
                if (inlineCarrCd.isEmpty()) {
                // if (inlineCarrCd == null) {
                // END 2021/01/18 A.Marte [QC#58256, MOD]
                    inlineCarrCd = emptyToAsterisk(confDtlList.get(0).vndCd.getValue());
                }

                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).invtyLocCd, shpgOrdDtlTMsg.toInvtyLocCd);
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).shipFromInvtyLocCd, shpgOrdDtlTMsg.invtyLocCd);
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).prchReqNum, shpgOrdDtlTMsg.prchReqNum);
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).prchReqLineNum, shpgOrdDtlTMsg.prchReqLineNum);
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).prchReqLineSubNum, shpgOrdDtlTMsg.prchReqLineSubNum);
                msg.OrdInfoLIst.setValidCount(i + 1);

                sysSrcCd = shpgOrdDtlTMsg.sysSrcCd.getValue();
            }

            // Move from W/H to W/H
            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, shpgOrdTMsg.whCd);
            rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);

            poRcvFromLocTpCd = rtlWhTMsg.locTpCd.getValue();

            if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

                poRcvFromLocCd = rtlWhTMsg.rtlWhCd.getValue();

            } else {

                poRcvFromLocCd = shpgOrdTMsg.whCd.getValue();
            }

            whCd = shpgOrdTMsg.shipToCustCd.getValue();

            if (!ZYPCommonFunc.hasValue(shpgOrdTMsg.pddDt)) {

                poRcvEtaDt = ZYPDateUtil.getSalesDate(shpgOrdTMsg.glblCmpyCd.getValue());

            } else {

                poRcvEtaDt = shpgOrdTMsg.pddDt.getValue();
            }

            poRcvTrxHdrNum = shpgOrdTMsg.soNum.getValue();
            rwsRefNum = getRwsReferenceNumber(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.soNum.getValue(), shpgOrdTMsg.whCd.getValue());

            if (rwsRefNum.length() > RWS_REF_NUM_LEN) {

                doErrorProcess(NPAM1320E, "Maximum number of digits exceeded.[ RWS_REF_NUM ]");
                return null;
            }

            // SHPG_ORD_CONF_DTL.vndCd
            ZYPEZDItemValueSetter.setValue(msg.inlndCarrCd, inlineCarrCd);

            // Get so message
            Map<String, Object> queryParam2 = new HashMap<String, Object>();
            queryParam2.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
            queryParam2.put("soNum", shpgOrdTMsg.soNum.getValue());
            queryParam2.put("soMsgTpCd", SHPG_ORD_MSG_TP.CPO_INFORMATION);
            List<SHPG_ORD_MSGTMsg> shpgOrdMsgList = (List<SHPG_ORD_MSGTMsg>) this.ssmBatchClient.queryObjectList("getShpgOrdMsgList", queryParam2);

            for (int i = 0; i < shpgOrdMsgList.size(); i++) {

                SHPG_ORD_MSGTMsg shpgOrdMsg = (SHPG_ORD_MSGTMsg) shpgOrdMsgList.get(i);

                if (msg.MsgInfoLIst.length() <= i) {

                    break;
                }

                msg.MsgInfoLIst.no(i).poRcvMsgTxt.setValue(shpgOrdMsg.soMsgDescTxt.getValue());
                msg.MsgInfoLIst.setValidCount(i + 1);
            }

        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) shpgOrdDtlTMsgList.get(0);

            // Get WRK_ORD data
            WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(wrkOrdTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(wrkOrdTMsg.wrkOrdNum, shpgOrdTMsg.trxHdrNum.getValue());
            wrkOrdTMsg = (WRK_ORDTMsg) S21ApiTBLAccessor.findByKey(wrkOrdTMsg);

            if (wrkOrdTMsg == null) {

                doErrorProcess(NLZM2030E, shpgOrdTMsg);
                throw new S21AbendException(NLZM2030E);
            }

            if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)) {

                // Set list parameter
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).mdseCd, wrkOrdTMsg.fnshGoodsMdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).stkStsCd, wrkOrdTMsg.stkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).poRcvQty, wrkOrdTMsg.fnshGoodsOrdQty.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).poRcvTrxLineNum, "001");
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).invtyLocCd, wrkOrdTMsg.rcvInvtyLocCd.getValue());
                ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).shipFromInvtyLocCd, shpgOrdDtlTMsg.toInvtyLocCd.getValue());
                msg.OrdInfoLIst.setValidCount(1);

                whCd = wrkOrdTMsg.rtlWhCd.getValue();

            } else if (SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

                // Get Work Order Detail
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", wrkOrdTMsg.glblCmpyCd.getValue());
                params.put("wrkOrdNum", wrkOrdTMsg.wrkOrdNum.getValue());
                params.put("flgY", ZYPConstant.FLG_ON_Y);

                List<Map<String, Object>> wrkOrdDtlMapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWrkOrdDtl", params);

                // Set list parameter
                for (int i = 0; i < wrkOrdDtlMapList.size(); i++) {

                    Map<String, Object> wrkOrdDtlMap = wrkOrdDtlMapList.get(i);

                    if (msg.OrdInfoLIst.length() <= i) {

                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).mdseCd, (String) wrkOrdDtlMap.get("ORIG_GOODS_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).stkStsCd, (String) wrkOrdDtlMap.get("STK_STS_CD"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).poRcvQty, (BigDecimal) wrkOrdDtlMap.get("ORIG_GOODS_ORD_QTY"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).poRcvTrxLineNum, (String) wrkOrdDtlMap.get("WRK_ORD_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).invtyLocCd, (String) wrkOrdDtlMap.get("RCV_INVTY_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).shipFromInvtyLocCd, shpgOrdDtlTMsg.toInvtyLocCd.getValue());
                    msg.OrdInfoLIst.setValidCount(i + 1);
                }

                whCd = wrkOrdTMsg.invtyLocCd.getValue();
            }

            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, shpgOrdTMsg.shipToCustCd.getValue());
            rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);

            poRcvFromLocTpCd = rtlWhTMsg.locTpCd.getValue();
            poRcvFromLocCd = shpgOrdTMsg.shipToCustCd.getValue();
            poRcvTrxHdrNum = shpgOrdTMsg.soNum.getValue();
            sysSrcCd = shpgOrdDtlTMsg.sysSrcCd.getValue();
            poRcvEtaDt = wrkOrdTMsg.rqstRcvDt.getValue();
            rwsRefNum = wrkOrdTMsg.wrkOrdNum.getValue();
        }

        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(msg.sceOrdTpCd, shpgOrdTMsg.sceOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(msg.poRcvFromLocTpCd, poRcvFromLocTpCd);
        ZYPEZDItemValueSetter.setValue(msg.poRcvFromLocCd, poRcvFromLocCd);
        ZYPEZDItemValueSetter.setValue(msg.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(msg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(msg.poRcvEtaDt, poRcvEtaDt);
        ZYPEZDItemValueSetter.setValue(msg.poRcvTrxHdrNum, poRcvTrxHdrNum);
        ZYPEZDItemValueSetter.setValue(msg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(msg.sysSrcCd, sysSrcCd);
        ZYPEZDItemValueSetter.setValue(msg.svcConfigMstrPk,shpgOrdTMsg.svcConfigMstrPk);

        NLZC201001 api = new NLZC201001();
        api.execute(msg, onBatch);

        // Get message list
        List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

        if (!msgList.isEmpty()) {

            for (String xxMsgId : msgList) {

                if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                    S21InfoLogOutput.println(NDMM0012E, new String[] {"NLZC201001" });
                    doErrorProcess(xxMsgId, msg);
                    throw new S21AbendException(NLZM2030E);

                } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                    // Continue when Warning
                    doErrorProcess(xxMsgId, msg);
                }
            }
        }

        // Get PO receiving#
        poRcvNum = msg.poRcvNum.getValue();
        return poRcvNum;
    }

    /**
     * get RWS Reference Number
     * @param glblCmpyCd String
     * @param trxRefNum String
     * @param whCd String
     * @return rws reference Number (String)
     */
    private String getRwsReferenceNumber(String glblCmpyCd, String trxRefNum, String whCd) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("whCd", whCd);
        paramMap.put("rwsRefNum", trxRefNum);

        BigDecimal rwsCnt = (BigDecimal) ssmBatchClient.queryObject("getRwsCnt", paramMap);

        if (rwsCnt == null || rwsCnt.intValue() == 0) {

            return trxRefNum;
        }

        paramMap.put("rwsRefNum", "^" + trxRefNum + "-" + "[0-9]{1,2}" + "$");

        String maxRwsRefNum = (String) ssmBatchClient.queryObject("getMaxRwsRefNum", paramMap);
        String rwsRefNum = "";

        if (maxRwsRefNum == null) {

            if (trxRefNum.length() < RWS_REF_NUM_LEN - 2) {

                rwsRefNum = trxRefNum + "-" + "01";

            } else {

                rwsRefNum = trxRefNum + "-" + "1";
            }

        } else {

            int index = maxRwsRefNum.lastIndexOf("-");
            String strRevision = String.valueOf(Integer.parseInt(maxRwsRefNum.substring(index + 1)) + 1);

            if (trxRefNum.length() < RWS_REF_NUM_LEN - 2) {

                rwsRefNum = trxRefNum + "-" + ZYPCommonFunc.leftPad(strRevision, 2, LF_PAD_CHAR);

            } else {

                rwsRefNum = trxRefNum + "-" + strRevision;
            }
        }

        return rwsRefNum;
    }

    /**
     * Get SO Detail List
     */
    private class GetShpgOrdDetail extends S21SsmVoidResultSetHandlerSupport {

        /** So Detail List */
        private List<SHPG_ORD_DTLTMsg> soDetailList;

        public GetShpgOrdDetail(List<SHPG_ORD_DTLTMsg> soDetailList) {

            this.soDetailList = soDetailList;
        }

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            while (rs.next()) {

                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.fromStkStsCd, rs.getString(FROM_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shipQty, nullToZero(rs.getBigDecimal(SHIP_QTY)));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.sysSrcCd, rs.getString(SYS_SRC_CD));
                soDetailList.add(shpgOrdDtlTMsg);
            }
        }
    }

    /**
     * Get RWS_DTL List
     */
    private class GetRwsDetail extends S21SsmVoidResultSetHandlerSupport {

        /** So Detail List */
        private List<RWS_DTLTMsg> rwsDetailList;

        public GetRwsDetail(List<RWS_DTLTMsg> rwsDetailList) {

            this.rwsDetailList = rwsDetailList;
        }

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            while (rs.next()) {

                RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.prchReqNum, rs.getString(PRCH_REQ_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.prchReqLineNum, rs.getString(PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.prchReqLineSubNum, rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsNum, rs.getString(RWS_NUM));
                rwsDetailList.add(rwsDtlTMsg);
            }
        }
    }

    /**
     * Call RWS API
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param poRcvNum PO Receiving#
     * @return ArrayList<String>
     */
    private ArrayList<String> callRwsAPI(SHPG_ORDTMsg shpgOrdTMsg, String poRcvNum) {

        String sysSrcCd = null;
        SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = null;

        // Get SO detail list
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
        queryParam.put("soNum", shpgOrdTMsg.soNum.getValue());
        List<SHPG_ORD_DTLTMsg> shpgOrdDtlTMsgList = new ArrayList<SHPG_ORD_DTLTMsg>();
        this.ssmBatchClient.queryObject("getShpgOrdDtl", queryParam, new GetShpgOrdDetail(shpgOrdDtlTMsgList));

        if (!shpgOrdDtlTMsgList.isEmpty()) {

            // Get from 1st record (Same value is set in all details)
            shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) shpgOrdDtlTMsgList.get(0);
            sysSrcCd = shpgOrdDtlTMsg.sysSrcCd.getValue();

        } else {

            doErrorProcess(NLZM2030E, shpgOrdTMsg);
            throw new S21AbendException(NLZM2030E);
        }

        NLZC200001PMsg msg = new NLZC200001PMsg();

        msg.glblCmpyCd.setValue(shpgOrdTMsg.glblCmpyCd.getValue());
        msg.sysSrcCd.setValue(sysSrcCd);
        msg.inbdSrcTpCd.setValue(INBD_SRC_TP.PO_RECEIVING);
        msg.poRcvNum.setValue(poRcvNum);

        NLZC200001 api = new NLZC200001();
        api.execute(msg, onBatch);

        // Get message list
        List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

        if (!msgList.isEmpty()) {

            for (String xxMsgId : msgList) {

                if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                    S21InfoLogOutput.println(NDMM0012E, new String[] {"NLZC200001" });
                    doErrorProcess(xxMsgId, msg);

                    // DB error
                    throw new S21AbendException(NLZM2030E);

                } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                    // Continue when Warning
                    doErrorProcess(xxMsgId, msg);
                }
            }
        }

        ArrayList<String> rwsNumList = new ArrayList<String>();

        for (int i = 0; i < msg.RWSNumList.getValidCount(); i++) {

            rwsNumList.add(msg.RWSNumList.no(i).rwsNum.getValue());
        }

        return rwsNumList;
    }

    /**
     * callRwsSerialAPI
     * @param rwsNumList rwsNumList
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @return ArrayList<String> errMsgList
     */
    private ArrayList<String> callRwsSerialAPI(ArrayList<String> rwsNumList, SHPG_ORDTMsg shpgOrdTMsg) {

        ArrayList<String> msgIdList = new ArrayList<String>();

        for (String rwsNum : rwsNumList) {

            HashMap<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
            param.put("rwsNum", rwsNum);

            String serSrchSmmId = null;

            if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd) || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

                serSrchSmmId = "getRwsSerial";

            } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)) {

                param.put("rwsDtlLineNum", "001");
                param.put("wrkOrdDtlLineNum", "000");

                serSrchSmmId = "getWrkRwsSerKitAndRf";

            } else if (SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

                serSrchSmmId = "getWrkRwsSerUnKit";
            }

            List<Map<String, Object>> rwsSerMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList(serSrchSmmId, param);

            if (rwsSerMapList != null && !rwsSerMapList.isEmpty()) {

                NLZC304001PMsg pMsg = new NLZC304001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);

                int cnt = 0;

                for (Map<String, Object> rwsSerMap : rwsSerMapList) {

                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).rwsDtlLineNum, (String) rwsSerMap.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).serNum, (String) rwsSerMap.get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).mdseCd, (String) rwsSerMap.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                    cnt++;
                }

                pMsg.SerialList.setValidCount(cnt);

                NLZC304001 api = new NLZC304001();
                api.execute(pMsg, onBatch);

                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                    msgIdList.add(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
            }
        }

        return msgIdList;
    }

    /**
     * Call Mach Master Update API for Allocation Off
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @return ArrayList<String>
     */
    private ArrayList<String> callMachMstrAPIForAllocOff(SHPG_ORDTMsg shpgOrdTMsg) {

        ArrayList<String> msgIdList = new ArrayList<String>();

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
        param.put("soNum", shpgOrdTMsg.soNum.getValue());
        //  QC#23438
        param.put("svcMachMstrStsCdW4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);

        List<BigDecimal> machMstrAllocOffList = (ArrayList<BigDecimal>) ssmBatchClient.queryObjectList("getMachMstrAllocOffList", param);

        if (machMstrAllocOffList == null || machMstrAllocOffList.isEmpty()) {

            return msgIdList;
        }

        for (BigDecimal machMstrAllocOffPk : machMstrAllocOffList) {

            NSZC001001PMsg pMsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(shpgOrdTMsg.glblCmpyCd.getValue()));
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, machMstrAllocOffPk);

            NSZC001001 api = new NSZC001001();
            api.execute(pMsg, onBatch);

            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                msgIdList.add(xxMsgId);
            }
        }

        return msgIdList;
    }

    /**
     * Call FSR Update API
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @return ArrayList<String>
     */
    private ArrayList<String> callFsrUpdateAPI(SHPG_ORDTMsg shpgOrdTMsg) {

        ArrayList<String> msgIdList = new ArrayList<String>();

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
        param.put("soNum", shpgOrdTMsg.soNum.getValue());
        param.put("svcTaskStsPendPrt", SVC_TASK_STS.PENDING_PARTS);

        List<Map<String, Object>> fsrTaskMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrTaskList", param);

        if (fsrTaskMapList == null || fsrTaskMapList.isEmpty()) {

            return msgIdList;
        }

        for (Map<String, Object> fsrTaskMap : fsrTaskMapList) {

            NSZC043001PMsg pMsg = new NSZC043001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
            ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.userId, shpgOrdTMsg.ezUpUserID.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).erlStartTs, ZYPCommonFunc.rightPad((String) fsrTaskMap.get("LTST_WH_IN_ETA_DT"), 17, "0"));
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).techCd, (String) fsrTaskMap.get("RQST_TECH_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_ON_Y);
            pMsg.taskList.setValidCount(1);

            NSZC043001 api = new NSZC043001();
            api.execute(pMsg, onBatch);

            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                msgIdList.add(xxMsgId);
            }
        }

        return msgIdList;
    }

    /**
     * Call DO API
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     */
//    private void callDoAPI(SHPG_ORDTMsg shpgOrdTMsg, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, String mode) {
//
//        String soNum = shpgOrdTMsg.soNum.getValue();
//        String trxHdrNum = shpgOrdTMsg.trxHdrNum.getValue();
//        String salesDt = ZYPDateUtil.getSalesDate(shpgOrdTMsg.glblCmpyCd.getValue());
//
//        NTZC001001PMsg msg = new NTZC001001PMsg();
//
//        msg.glblCmpyCd.setValue(shpgOrdTMsg.glblCmpyCd.getValue());
//        msg.xxProcDoStsCd.setValue(mode);
//        msg.doRqstSrcNum.setValue(soNum);
//        msg.trxHdrNum.setValue(trxHdrNum);
//        msg.procDt.setValue(salesDt);
//
//        List<NTZC001001_DtlListPMsg> dtlList = null;
//
//        if (DO_MODIFY.equals(mode)) {
//
//            NTZC001001_DtlListPMsg dtlMsg = new NTZC001001_DtlListPMsg();
//            ZYPEZDItemValueSetter.setValue(msg.trxSrcTpCd, shpgOrdTMsg.trxSrcTpCd.getValue());
//            ZYPEZDItemValueSetter.setValue(dtlMsg.shpgPlnNum, shpgOrdDtlTMsg.shpgPlnNum.getValue());
//            ZYPEZDItemValueSetter.setValue(dtlMsg.shipPlnCancDt, salesDt);
//            ZYPEZDItemValueSetter.setValue(dtlMsg.trxLineNum, shpgOrdDtlTMsg.trxLineNum.getValue());
//            ZYPEZDItemValueSetter.setValue(dtlMsg.trxLineSubNum, shpgOrdDtlTMsg.trxLineSubNum.getValue());
//            ZYPEZDItemValueSetter.setValue(dtlMsg.mdseCd, shpgOrdDtlTMsg.mdseCd.getValue());
//
//            dtlList = new ArrayList<NTZC001001_DtlListPMsg>();
//            dtlList.add(dtlMsg);
//        }
//
//        NTZC001001 api = new NTZC001001();
//        api.execute(msg, dtlList, onBatch);
//
//        // Get message list
//        List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
//
//        if (!msgList.isEmpty()) {
//
//            for (String xxMsgId : msgList) {
//
//                if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {
//
//                    S21InfoLogOutput.println(NDMM0012E, new String[] {"NTZC001001" });
//                    doErrorProcess(xxMsgId, msg);
//
//                    // DB error
//                    throw new S21AbendException(NLZM2030E);
//
//                } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {
//
//                    // Continue when Warning
//                    doErrorProcess(xxMsgId, msg);
//                }
//            }
//        }
//    }

    /**
     * Call Vendor Return Update API
     * @param shpgOrdTMsg SHPG_ORDTMsg
     */
    private void callVendorReturnUpdateAPI(SHPG_ORDTMsg shpgOrdTMsg, String mode) {

        String trxHdrNum = shpgOrdTMsg.trxHdrNum.getValue();
        String procDt = ZYPDateUtil.getSalesDate(shpgOrdTMsg.glblCmpyCd.getValue());
        String trxLineNum = null;
        String trxLineSubNum = null;

        // Get line number, line sub number
        SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soNum, shpgOrdTMsg.soNum.getValue());
        List<SHPG_ORD_DTLTMsg> soDtlList = (List<SHPG_ORD_DTLTMsg>) this.ssmBatchClient.queryObjectList("getShpgOrdDtlLineNum", shpgOrdDtlTMsg);

        if (soDtlList.isEmpty()) {

            doErrorProcess(NLZM2030E, shpgOrdTMsg);
            throw new S21AbendException(NLZM2030E);
        }

        NWZC403001PMsg msg = new NWZC403001PMsg();

        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(msg.xxModeCd, mode);
        ZYPEZDItemValueSetter.setValue(msg.vndRtrnNum, trxHdrNum);
        msg.List.setValidCount(soDtlList.size());

        if (VND_RTRN_MODE_CLOSED.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(msg.vndRtrnCloDt, procDt);

            for (int i = 0; i < soDtlList.size(); i++) {

                SHPG_ORD_DTLTMsg tMsg = (SHPG_ORD_DTLTMsg) soDtlList.get(i);

                trxLineNum = tMsg.trxLineNum.getValue();
                trxLineSubNum = tMsg.trxLineSubNum.getValue();

                ZYPEZDItemValueSetter.setValue(msg.List.no(i).trxLineNum, trxLineNum);
                ZYPEZDItemValueSetter.setValue(msg.List.no(i).trxLineSubNum, trxLineSubNum);
                ZYPEZDItemValueSetter.setValue(msg.List.no(i).vndRtrnDtlCloDt, procDt);
            }
        } else if (VND_RTRN_MODE_CANCELLED.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(msg.vndRtrnCancDt, procDt);

            for (int i = 0; i < soDtlList.size(); i++) {

                SHPG_ORD_DTLTMsg tMsg = (SHPG_ORD_DTLTMsg) soDtlList.get(i);

                trxLineNum = tMsg.trxLineNum.getValue();
                trxLineSubNum = tMsg.trxLineSubNum.getValue();

                ZYPEZDItemValueSetter.setValue(msg.List.no(i).trxLineNum, trxLineNum);
                ZYPEZDItemValueSetter.setValue(msg.List.no(i).trxLineSubNum, trxLineSubNum);
                ZYPEZDItemValueSetter.setValue(msg.List.no(i).vndRtrnDtlCancDt, procDt);
            }
        }
        executeVendorReturnUpdateAPI(msg);
    }

    /**
     * Execute Vendor Return Update API
     * @param msg NWZC403001PMsg
     */
    private void executeVendorReturnUpdateAPI(NWZC403001PMsg msg) {

        if (msg != null) {

            // Execute API
            NWZC403001 api = new NWZC403001();
            api.execute(msg, onBatch);

            // Get message list
            List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

            if (!msgList.isEmpty()) {

                for (String xxMsgId : msgList) {

                    if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                        S21InfoLogOutput.println(NDMM0012E, new String[] {"NWZC403001" });
                        doErrorProcess(xxMsgId, msg);
                        throw new S21AbendException(NLZM2030E);

                    } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                        // Continue when Warning
                        doErrorProcess(xxMsgId, msg);
                    }
                }
            }
        }
    }

    /**
     * Get SO_DTL Count (For SO Conf Work Check)
     */
    private class GetShpgOrdDtlCnt extends S21SsmIntegerResultSetHandlerSupport {

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            Integer cnt = 0;

            if (rs.next()) {

                cnt = rs.getInt(CNT);
            }

            return cnt;
        }
    }

    /**
     * Check if ASN Creation Process is required.
     */
    private boolean isAsnRequired(String glblCmpyCd, String soNum) {

        HashMap<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("ediCustTpShipTo", EDI_CUST_TP_SHIP_TO);
        queryParam.put("ediCustTpSellTo", EDI_CUST_TP_SELL_TO);
        queryParam.put("ansiTrxNumEDI856", ANSI_TRX_NUM_EDI_856);
        queryParam.put("trdPtnrAsnStruLvlNum3", TRD_PTNR_ASN_STRU_LVL_NUM_3);
        queryParam.put("cpoSrcTpEDI", CPO_SRC_TP.EDI);
        queryParam.put("cpoSrcTpOrderEntryScreen", CPO_SRC_TP.ORDER_ENTRY_SCREEN);
        queryParam.put("cpoSrcTpCPNet", CPO_SRC_TP.CP_NET);
        queryParam.put("cpoSrcTpCNA", CPO_SRC_TP.C_N_A);
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("soNum", soNum);

        int cnt = (Integer) ssmBatchClient.queryObject("getCntOfTrdPtnrForThreeLevelStruFile", queryParam, new Get3LvlCnt());

        if (cnt == 0) {

            return false;
        }

        return true;
    }

    /**
     * Get SO_DTL Count (For SO Conf Work Check)
     */
    private class Get3LvlCnt extends S21SsmIntegerResultSetHandlerSupport {

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            Integer cnt = 0;

            if (rs.next()) {

                cnt = rs.getInt(CNT);
            }

            return cnt;
        }
    }

    /**
     * Get SHIP_SER_NUM Count (For Serial# Work Check)
     */
    private class GetShipSerNumCntBySoNum extends S21SsmIntegerResultSetHandlerSupport {

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            Integer cnt = 0;

            if (rs.next()) {

                cnt = rs.getInt(CNT);
            }

            return cnt;
        }
    }

    /**
     * Get BOL_RELEN Count
     */
    private class GetBolRelenCnt extends S21SsmIntegerResultSetHandlerSupport {

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            Integer cnt = 0;

            if (rs.next()) {

                cnt = rs.getInt(CNT);
            }

            return cnt;
        }
    }

    /**
     * Get Max Value of BOL_RELEN.BOL_SQ_NUM
     */
    private class GetBolRelenMaxSqNum extends S21SsmIntegerResultSetHandlerSupport {

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            BigDecimal maxBolSqNum = new BigDecimal(0);

            if (rs.next()) {

                maxBolSqNum = nullToZero(rs.getBigDecimal(BOL_SQ_NUM));
            }

            return maxBolSqNum.intValue();
        }
    }

    /**
     * Get SHIP_SER_NUM_WRK Count (For SO Conf Work Check)
     */
    private class GetShipSerNumWrkCnt extends S21SsmIntegerResultSetHandlerSupport {

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            Integer cnt = 0;

            if (rs.next()) {

                cnt = rs.getInt(CNT);
            }

            return cnt;
        }
    }

    /**
     * Get Sum Value of SHPG_ORD_CONF_DTL_WRK.SHIP_QTY (For SO Conf
     * Detail Work Check)
     */
    private class GetShpgOrdConfDtlWrkShipQty extends S21SsmIntegerResultSetHandlerSupport {

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            BigDecimal shipQty = new BigDecimal(0);

            if (rs.next()) {

                shipQty = nullToZero(rs.getBigDecimal(SHIP_QTY));
            }

            return shipQty.intValue();
        }
    }

    /**
     * getSameKeySerNumErr
     * @param serNumErrTMsg SER_NUM_ERRTMsg
     * @return SER_NUM_ERRTMsg
     */
    private SER_NUM_ERRTMsg getSameKeySerNumErr(SER_NUM_ERRTMsg serNumErrTMsg) {

        SER_NUM_ERRTMsg selectSerErrTMsg = (SER_NUM_ERRTMsg) EZDTBLAccessor.findByKeyForUpdate(serNumErrTMsg);

        if (selectSerErrTMsg == null || !RETURN_CD_NORMAL_END.equals(selectSerErrTMsg.getReturnCode())) {

            // No data
            return null;

        } else {

            return selectSerErrTMsg;
        }
    }

    /**
     * Create Search Condition For Order Cancel
     * @return List of SHPG_STS_CD
     */
    private List<String> createConditionListForOrderCancel() {

        List<String> list = new ArrayList<String>();

        // Create list of status which is not 'Cancel'
        list.add(SHPG_STS.S_OR_O_PRINTED);
        list.add(SHPG_STS.PICKED);
        list.add(SHPG_STS.PACKED);
        list.add(SHPG_STS.STAGED);

        return list;
    }

    /**
     * Create Search Condition For RWS Cancel
     * @return List of RWS_STS_CD
     */
    private List<String> createConditionListForRwsCancel() {

        List<String> list = new ArrayList<String>();

        // Create list of status which is target of Cancel
        list.add(RWS_STS.SAVED);
        list.add(RWS_STS.PRINTED);

        return list;
    }

    /**
     * Convert Null to Zero
     * @param val BigDecimal
     * @return BigDecimal (If value is null, convert it to '0')
     */
    private BigDecimal nullToZero(BigDecimal val) {

        if (val == null) {

            return new BigDecimal(0);

        } else {

            return val;
        }
    }

    /**
     * Convert Null to Empty
     * @param str String
     * @return String (If String is null, convert it to "")
     */
    private String nullToEmpty(String str) {

        if (str == null) {

            return "";

        } else {

            return str;
        }
    }

    /**
     * Convert Empty to Asterisk
     * @param str String
     * @return String (If String is "", convert it to "*")
     */
    private String emptyToAsterisk(String str) {

        if ("".equals(str)) {

            return "*";

        } else {

            return str;
        }
    }

    /**
     * Convert Asterisk to Empty
     * @param str String
     * @return String (If String is "*", convert it to "")
     */
    private String asteriskToEmpty(String str) {

        if ("*".equals(str)) {

            return "";

        } else {

            return str;
        }
    }

    /**
     * Check Max Limit
     * @param val BigDecimal
     * @param maxValString
     * @return BigDecimal (If val is over max value, return max value)
     */
    private BigDecimal checkMaxLimit(BigDecimal val, String maxValString) {

        BigDecimal maxValue = new BigDecimal(maxValString);

        if (val.compareTo(maxValue) == 1) {

            return maxValue;

        } else {

            return val;
        }
    }

    /**
     * Do Error Process Add xxMsgId to msgMap and Output Log
     * @param xxMsgId String
     */
    private void doErrorProcess(String xxMsgId, String key) {

        int i = msgMap.getMsgMgr().getXxMsgIdListSize();

        if (i >= MSG_ID_LIST_SIZE) {

            // No Process

        } else {

            msgMap.addXxMsgId(xxMsgId);
        }

        S21InfoLogOutput.println(xxMsgId);
        S21InfoLogOutput.println(NFCM0576E, new String[] {key });
    }

    /**
     * Do Error Process Add xxMsgId to msgMap and Output Log
     * @param xxMsgId String
     */
    private void doErrorProcess(String xxMsgId, EZDMsg key) {

        if (null == key) {

            doErrorProcess(xxMsgId, "Key Data is NULL");

        } else {

            doErrorProcess(xxMsgId, key.toString());
        }
    }

    /**
     * SO Conf Detail Work Process
     */
    private class ASNProcess extends S21SsmBooleanResultSetHandlerSupport {

        /** SHPG_ORDTMsg */
        private SHPG_ORDTMsg shpgOrdTMsg;

        public ASNProcess(SHPG_ORDTMsg shpgOrdTMsg) {
            this.shpgOrdTMsg = shpgOrdTMsg;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            int cnt = 0;
            boolean errFlg = false;

            BigInteger ediTrxId = null;

            BigInteger ediSqId = BigInteger.ZERO;
            String asnPlnDelyDt = "";

            while (rs.next()) {
                cnt++;

                if (SO_PROC_STS.SHIP.equals(rs.getString(SO_PROC_STS_CD))) {

                    if (ediTrxId == null) {
                        ediTrxId = getNewEdiTrxId(shpgOrdTMsg.glblCmpyCd.getValue());
                    }

                    ediSqId = ediSqId.add(BigInteger.ONE);

                    if (!SCE_ORD_TP.EXPORT.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {

                        if (!ZYPCommonFunc.hasValue(asnPlnDelyDt)) {
                            asnPlnDelyDt = calcPlnDelyDt(rs);
                            if (!ZYPCommonFunc.hasValue(asnPlnDelyDt)) {
                                errFlg = true;
                            }
                        }
                    }

                    if (errFlg) {

                        return errFlg;
                    }

                    errFlg = createEdiAsn(rs, ediTrxId, ediSqId, asnPlnDelyDt);

                    // Business error
                    if (errFlg) {

                        return errFlg;
                    }
                } else {

                    // do nothing
                }
            }

            // No target data
            if (cnt == 0) {

                doErrorProcess(NLZM2004E, "No target data ASNProcess");
                errFlg = true;
            }

            if (ediSqId.compareTo(BigInteger.ZERO) > 0) {
                setEdiTrx(shpgOrdTMsg.glblCmpyCd.getValue(), String.format("%0" + Integer.toString(EDI_TRX_ID_MAX_LG) + "d", ediTrxId));
            }

            return errFlg;
        }

        /**
         * Calculate Planed Delivery Date
         * @param rs ResultSet
         * @return CalculatedRecord
         */
        private String calcPlnDelyDt(final ResultSet rs) throws SQLException {

            SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlTMsg = getShpgOrdCustDtl(rs);

            if (shpgOrdCustDtlTMsg == null) {

                doErrorProcess(NLBM1134E, "No target data Calculate Planed Delivery Date");
                return null;
            }

            String shpgModeCd = shpgOrdTMsg.shpgModeCd.getValue();

            if (!ZYPCommonFunc.hasValue(shpgModeCd)) {

                shpgModeCd = getShpgModeCd(shpgOrdTMsg);

                if (!ZYPCommonFunc.hasValue(shpgModeCd)) {

                    shpgModeCd = getShpgModeCdFromShpgWt(shpgOrdTMsg);
                }

                if (!ZYPCommonFunc.hasValue(shpgModeCd)) {

                    doErrorProcess(NLBM1129E, shpgOrdTMsg.toString());
                    return null;
                }
            }

            String asnPlnDelyDt = getAsnPlnDelyDt(rs, shpgOrdTMsg, shpgOrdCustDtlTMsg, shpgModeCd);

            if (!ZYPCommonFunc.hasValue(asnPlnDelyDt)) {

                doErrorProcess(NLBM1064E, shpgOrdTMsg.toString());
                return null;
            }

            return asnPlnDelyDt;
        }

        private boolean createEdiAsn(ResultSet rs, BigInteger ediTrxId, BigInteger ediSqId, String asnPlnDelyDt) throws SQLException {

            setEdiAsn(rs, String.format("%0" + Integer.toString(EDI_TRX_ID_MAX_LG) + "d", ediTrxId), String.format("%0" + Integer.toString(EDI_SQ_ID_MAX_LG) + "d", ediSqId), asnPlnDelyDt);

            return false;
        }

        /**
         * Set EDI Transaction Table
         * @param glblCmpyCd GlobalCompanyCode
         * @param ediTrxId EDITransactionID
         */
        private void setEdiTrx(final String glblCmpyCd, final String ediTrxId) {

            SCE_EDI_TRXTMsg outMsg = new SCE_EDI_TRXTMsg();

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.ediIntfcId, EDI_INTFC_ID_ASN);
            ZYPEZDItemValueSetter.setValue(outMsg.ediTrxId, ediTrxId);
            ZYPEZDItemValueSetter.setValue(outMsg.ediProcFlg, ZYPConstant.FLG_OFF_N);

            EZDTBLAccessor.insert(outMsg);

            if (!DB_RETURN_CD_NORMAL_END.equals(outMsg.getReturnCode())) {

                doErrorProcess(NLZM2030E, outMsg.toString());
                throw new S21AbendException(NLBM1064E);
            }
        }

        /**
         * Get New EDI Transaction ID
         * @param glblCmpyCd GlobalCompanyCode
         * @return NewEDITransactionID
         */
        private BigInteger getNewEdiTrxId(final String glblCmpyCd) {

            BigInteger newEdiTrxId = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_ASN_SQ").toBigInteger();

            return newEdiTrxId;
        }

        /**
         * Get Shipping Mode Code
         * @param shpgOrdTMsg ShippingOrderTable
         * @return MaxEDITransactionID
         */
        private String getShpgModeCd(final SHPG_ORDTMsg shpgOrdTMsg) {

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
            queryParam.put("shpgSvcLvlCd", shpgOrdTMsg.shpgSvcLvlCd.getValue());
            queryParam.put("frtChrgToCd", shpgOrdTMsg.frtChrgToCd.getValue());
            queryParam.put("frtChrgMethCd", shpgOrdTMsg.frtChrgMethCd.getValue());

            return (String) ssmBatchClient.queryObject("getMinShpgModeCd", queryParam);
        }

        /**
         * Get Shipping Mode Code
         * @param shpgOrdTMsg ShippingOrderTable
         * @return MaxEDITransactionID
         */
        private String getShpgModeCdFromShpgWt(final SHPG_ORDTMsg shpgOrdTMsg) {

            String shpgModeCd = null;

            // SQL bind parameter
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
            queryParam.put("frtChrgToCd", shpgOrdTMsg.frtChrgToCd.getValue());
            queryParam.put("frtChrgMethCd", shpgOrdTMsg.frtChrgMethCd.getValue());
            queryParam.put("effDt", ZYPDateUtil.getSalesDate(shpgOrdTMsg.glblCmpyCd.getValue()));

            List<String> shpgModeCdList = (List<String>) ssmBatchClient.queryObjectList("getShpgModeCdListFromShpgWt", queryParam);

            if (!shpgModeCdList.isEmpty()) {

                shpgModeCd = (String) shpgModeCdList.get(0);
            }
            return shpgModeCd;
        }

        /**
         * Get Advanced Shipping Notice Planned Delivery Date
         * @param rs ResultSet
         * @param shpgOrdTMsg ShippingOrderTable
         * @param shpgOrdCustDtlTMsg ShippingOrderCustomerDetailTable
         * @param shpgModeCd ShippingModeCode
         * @return ShippingOrderCustomerDetailTable
         */
        private String getAsnPlnDelyDt(final ResultSet rs, final SHPG_ORDTMsg shpgOrdTMsg, final SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlTMsg, final String shpgModeCd) throws SQLException {

            String asnShipDt = nullToEmpty(rs.getString(SHIP_DT_TM_TS));
            if (asnShipDt.length() > 8) {
                asnShipDt = asnShipDt.substring(0, 8);
            }

            String asnPlnDelyDt = calculatePdd(nullToEmpty(rs.getString(GLBL_CMPY_CD)), asnShipDt, nullToEmpty(rs.getString(WH_CD)), shpgOrdCustDtlTMsg.stCd.getValue(), shpgOrdCustDtlTMsg.postCd.getValue(), shpgModeCd,
                    shpgOrdTMsg.shpgSvcLvlCd.getValue(), asnShipDt);

            return asnPlnDelyDt;
        }

        /**
         * Calculate PDD
         */
        private String calculatePdd(String glblCmpyCd, String psdDt, String whCd, String shipToStCd, String shipToPostCd, String shpgModeCd, String shpgSvcLvlCd, String salesDate) {

            String pddDt = null;

            // Get Carrier calender code
            String carrCalCd;
            if (shpgModeCd == null) {
                carrCalCd = glblCmpyCd;
            } else {
                CAL_RELNTMsg calReln = getCalReln(glblCmpyCd, CAL_SUB_TP.CARRIER_CALENDAR, shpgModeCd);
                if (calReln == null) {
                    carrCalCd = glblCmpyCd;
                } else {
                    carrCalCd = calReln.calTpCd.getValue();
                }
            }

            // Get Transportation Lead Time
            BigDecimal transportationLt = getTransportationLT(glblCmpyCd, whCd, shipToStCd, shipToPostCd, shpgModeCd, shpgSvcLvlCd, salesDate);
            if (!ZYPCommonFunc.hasValue(transportationLt)) {
                return null;
            }

            // PSD is Carrier business day
            if (ZYPDateUtil.isBusinessDay(carrCalCd, psdDt)) {
                // PDD: PSD + Transportation Lead Time (Carrier business day)
                pddDt = ZYPDateUtil.addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue());

            } else {

                // PDD: PSD + Transportation Lead Time (Carrier business day) + 1 (Carrier business day)
                pddDt = ZYPDateUtil.addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue() + 1);
            }

            return pddDt;
        }

        /**
         * Get CAL_RELN
         */
        private CAL_RELNTMsg getCalReln(String glblCmpyCd, String calSubTpCd, String calMultCd) {

            CAL_RELNTMsg calReln = new CAL_RELNTMsg();
            calReln.glblCmpyCd.setValue(glblCmpyCd);
            calReln.calSubTpCd.setValue(calSubTpCd);
            calReln.calMultCd.setValue(calMultCd);
            calReln = (CAL_RELNTMsg) EZDTBLAccessor.findByKey(calReln);

            return calReln;
        }

        /**
         * Get Transportation LT
         */
        private BigDecimal getTransportationLT(String glblCmpyCd, String whCd, String shipToStCd, String shipToPostCd, String shpgModeCd, String shpgSvcLvlCd, String salesDate) {

            BigDecimal transportationLt;

            // Get lead time of Guaranteed Days Delivery
            ShpgSvcInfo shpgSvcInfo = getShpgSvcLeadTm(glblCmpyCd, shpgSvcLvlCd);
            if (shpgSvcInfo == null) {
                return null;
            }

            if (SHPG_SVC_TP.GUARANTEED_DAYS_DELIVERY.equals(shpgSvcInfo.getShpgSvcTpCd())) {

                transportationLt = shpgSvcInfo.getDelyLeadAot();

                // Get lead time of Ground Standard Delivery / Pick Up
            } else {
                TRNSP_LTTMsg trnspLt = getTrnspLt(glblCmpyCd, whCd, shipToStCd, shipToPostCd, shpgModeCd, salesDate);
                if (trnspLt == null) {

                    AREA_LEAD_TMTMsg areaLeadTm = getAreaLeadTm(glblCmpyCd, whCd, shipToStCd, shpgModeCd, salesDate);
                    if (areaLeadTm == null) {
                        return null;
                    }
                    transportationLt = areaLeadTm.delyLeadAot.getValue();

                } else {
                    transportationLt = trnspLt.trnspLtAot.getValue();
                }
            }

            return transportationLt;
        }

        /**
         * Get SHPG_SVC_LEAD_TM
         */
        private ShpgSvcInfo getShpgSvcLeadTm(String glblCmpyCd, String shpgSvcLvlCd) {

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
            List<ShpgSvcInfo> shpgSvcInfoList = (List<ShpgSvcInfo>) ssmBatchClient.queryObjectList("getShpgSvcLeadTm", queryParam);

            ShpgSvcInfo shpgSvcInfo;
            if (shpgSvcInfoList.isEmpty()) {
                shpgSvcInfo = null;
            } else {
                shpgSvcInfo = shpgSvcInfoList.get(0);
            }

            return shpgSvcInfo;
        }

        /**
         * Get TRNSP_LT
         */
        private TRNSP_LTTMsg getTrnspLt(String glblCmpyCd, String whCd, String shipToStCd, String shipToPostCd, String shpgModeCd, String salesDate) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("whCd", whCd);
            queryParam.put("shpgModeCd", shpgModeCd);
            queryParam.put("shipToStCd", shipToStCd);
            queryParam.put("shipToPostCd", shipToPostCd);
            queryParam.put("salesDate", salesDate);
            List<TRNSP_LTTMsg> trnspLtList = (List<TRNSP_LTTMsg>) ssmBatchClient.queryObjectList("getTrnspLt", queryParam);

            TRNSP_LTTMsg trnspLt;
            if (trnspLtList.isEmpty()) {
                trnspLt = null;
            } else {
                trnspLt = trnspLtList.get(0);
            }

            return trnspLt;
        }

        /**
         * Get AREA_LEAD_TM
         */
        private AREA_LEAD_TMTMsg getAreaLeadTm(String glblCmpyCd, String whCd, String shipToStCd, String shpgModeCd, String salesDate) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("whCd", whCd);
            queryParam.put("shpgModeCd", shpgModeCd);
            queryParam.put("shipToStCd", shipToStCd);
            queryParam.put("salesDate", salesDate);
            List<AREA_LEAD_TMTMsg> areaLeadTmList = (List<AREA_LEAD_TMTMsg>) ssmBatchClient.queryObjectList("getAreaLeadTm", queryParam);

            AREA_LEAD_TMTMsg areaLeadTm;
            if (areaLeadTmList.isEmpty()) {
                areaLeadTm = null;
            } else {
                areaLeadTm = areaLeadTmList.get(0);
            }

            return areaLeadTm;
        }

        /**
         * Convert Hour Value to Day Value
         * @param hourValue Hour Value
         * @return Day Value (If not divided, round up)
         */
        private BigDecimal hourToDay(BigDecimal hourValue) {

            BigDecimal dayValue = hourValue.divide(new BigDecimal(24), 0, BigDecimal.ROUND_UP);
            return dayValue;
        }

        /**
         * Get Shipping Order Customer Detail Table
         * @param rs ResultSet
         * @return ShippingOrderCustomerDetailTable
         */
        private SHPG_ORD_CUST_DTLTMsg getShpgOrdCustDtl(final ResultSet rs) throws SQLException {

            SHPG_ORD_CUST_DTLTMsg inMsg = new SHPG_ORD_CUST_DTLTMsg();

            inMsg.glblCmpyCd.setValue(nullToEmpty(rs.getString(GLBL_CMPY_CD)));
            inMsg.soNum.setValue(nullToEmpty(rs.getString(SO_NUM)));
            inMsg.soCustDataTpCd.setValue(SO_CUST_DATA_TP.SHIP_TO);

            inMsg = (SHPG_ORD_CUST_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
            if (inMsg == null) {
                return inMsg;
            }
            if (!DB_RETURN_CD_NORMAL_END.equals(inMsg.getReturnCode())) {
                doErrorProcess(NLZM2030E, inMsg.toString());
                throw new S21AbendException(NLBM1064E);
            }
            return inMsg;
        }

        /**
         * Set EDI Advanced Shipping Notice Table
         * @param rs ResultSet
         * @param ediTrxId EDI Transaction ID
         * @param ediSqId EDI Sequence ID
         * @param asnPlnDelyDt Planed Delivery Date
         */
        private void setEdiAsn(final ResultSet rs, final String ediTrxId, final String ediSqId, final String asnPlnDelyDt) throws SQLException {

            BigDecimal asnQty = null;
            String asnQtyTxt = rs.getString(SHIP_QTY);
            if (ZYPCommonFunc.hasValue(asnQtyTxt)) {
                asnQty = new BigDecimal(asnQtyTxt);
            }

            EDI_ASNTMsg outMsg = new EDI_ASNTMsg();

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.ediTrxId, ediTrxId);
            ZYPEZDItemValueSetter.setValue(outMsg.ediSqId, ediSqId);
            ZYPEZDItemValueSetter.setValue(outMsg.asnBolNum, rs.getString(BOL_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.asnCarrScacCd, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.asnCratDtTmTs, getSystemDtTmTs());
            ZYPEZDItemValueSetter.setValue(outMsg.asnProcCd, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.asnMdseCd, rs.getString(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.asnLoadId, rs.getString(BOL_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.asnShipId, rs.getString(BOL_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.asnQty, asnQty);
            ZYPEZDItemValueSetter.setValue(outMsg.asnSoNum, rs.getString(SO_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.asnSoSlpNum, rs.getString(SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.asnShpgPlnNum, rs.getString(SHPG_PLN_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.asnShipDtTmTs, rs.getString(SHIP_DT_TM_TS));
            ZYPEZDItemValueSetter.setValue(outMsg.asnInvtyLocCd, rs.getString(WH_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.asnRteDescTxt, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.asnProNum, rs.getString(PRO_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.asnTrnspTpCd, ASN_TRNSP_TP_LEVEL3);
            ZYPEZDItemValueSetter.setValue(outMsg.asnPareUccCd, PARENT_UCC_CD_LEVEL3);

            if (!SCE_ORD_TP.EXPORT.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(outMsg.asnPlnDelyDt, asnPlnDelyDt);
            }

            ZYPEZDItemValueSetter.setValue(outMsg.asnTtlWt, rs.getString(TOT_SHIP_WT));

            EZDTBLAccessor.insert(outMsg);
            if (!DB_RETURN_CD_NORMAL_END.equals(outMsg.getReturnCode())) {
                doErrorProcess(NLZM2030E, outMsg.toString());
                throw new S21AbendException(NLBM1064E);
            }
        }

        private String getSystemDtTmTs() {

            String yyyymmdd = ZYPDateUtil.getSalesDate();
            String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

            return yyyymmdd + hhmmss;
        }
    }

    /**
     * doesExistDsCondConst
     * @param glblCmpyCd String
     * @param sceOrdTp String
     * @param valTxt1 String
     * @param valTxt2 String
     * @param valTxt3 String
     * @param valTxt4 String
     * @param valTxt5 String
     * @return true:Exist false: not Exist
     */
    private boolean doesExistDsCondConst(String glblCmpyCd, String sceOrdTp, String valTxt1, String valTxt2, String valTxt3, String valTxt4, String valTxt5) {

        HashMap<String, String> searchConfig = new HashMap<String, String>();
        searchConfig.put("glblCmpyCd", glblCmpyCd);
        searchConfig.put("dsCondConstGrpId", DS_CONST_GRP_ID);
        searchConfig.put("dsCondConstCd", sceOrdTp);

        if (valTxt1 != null) {

            searchConfig.put("constValTxt01", valTxt1);
        }

        if (valTxt2 != null) {

            searchConfig.put("constValTxt02", valTxt2);
        }

        if (valTxt3 != null) {

            searchConfig.put("constValTxt03", valTxt3);
        }

        if (valTxt4 != null) {

            searchConfig.put("constValTxt04", valTxt4);
        }

        if (valTxt5 != null) {

            searchConfig.put("constValTxt05", valTxt5);
        }

        Integer count = Integer.valueOf(0);
        count = (Integer) this.ssmBatchClient.queryObject("countDsCondConst", searchConfig);

        if (count.compareTo(0) > 0) {

            return true;
        }

        return false;
    }

    /**
     * callInventoryOrder
     * @param procTpCd String
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @return true:Success false:Error
     */
    private boolean callInventoryOrder(String procTpCd, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg) {

        NLZC003001PMsg pMsg = new NLZC003001PMsg();

        // Set Param
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, procTpCd);

        if (IOA_HEADER_CLOSED.equals(procTpCd)) {

            ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, IOA_DATA_TYPE_HEADER);

        } else {

            ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, IOA_DATA_TYPE_DETAIL);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdNum, shpgOrdDtlTMsg.trxHdrNum);

        if (IOA_CLOSED.equals(procTpCd) || IOA_CANCEL.equals(procTpCd)) {

            ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdLineNum, shpgOrdDtlTMsg.trxLineNum);
        }

        NLZC003001 api = new NLZC003001();

        // execute
        api.execute(pMsg, this.onBatch);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                doErrorProcess(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), "Error Inventory Order API");
            }

            return false;
        }

        return true;
    }

    /**
     * callPRUpdateAPI
     * @param modeCd String
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @param cancelQty BigDecimal
     * @return
     */
    private boolean callPRUpdateAPI(String modeCd, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg, BigDecimal cancelQty) {

        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        // Set Parameter
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, modeCd);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, PRUA_EVENT_SHIPPED);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, shpgOrdDtlTMsg.prchReqNum);

        // Set one Line
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineNum, shpgOrdDtlTMsg.prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineSubNum, shpgOrdDtlTMsg.prchReqLineSubNum);

        // 2017/11/13 M.Naito Mod QC#22503 START
        // Get SHPG_ORD_CONF_DTL data
        String proNum = "";
        BigDecimal shipQty = new BigDecimal(0);

        if (PRUA_UPDATE.equals(modeCd)) {
            // concatenate proNum
            List<SHPG_ORD_CONF_DTLTMsg> shpgOrdConfDtlTMsgList = getShpgOrdConfDtlList(shpgOrdConfDtlTMsg.glblCmpyCd.getValue(), shpgOrdConfDtlTMsg.soNum.getValue(), shpgOrdConfDtlTMsg.soSlpNum.getValue(), null, SO_PROC_STS.SHIP);
            for (SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtl : shpgOrdConfDtlTMsgList) {
                if (ZYPCommonFunc.hasValue(proNum)) {
                    proNum = proNum + COMMA + shpgOrdConfDtl.proNum.getValue();
                } else {
                    proNum = shpgOrdConfDtl.proNum.getValue();
                }
                shipQty = shipQty.add(shpgOrdConfDtl.shipQty.getValue());
            }
            if (proNum.length() > PRO_NUM_LENGTH) {
                proNum =  proNum.substring(0, PRO_NUM_LENGTH);
            }
            // Update
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).soNum, shpgOrdDtlTMsg.soNum);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).proNum, proNum);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).shipDtTmTs, shpgOrdConfDtlTMsg.shipDtTmTs);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).shipQty, shipQty);

            // START 2022/12/07 F.Fadriquela [QC#60889, ADD]
            SHPG_ORDTMsg soInfo = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(soInfo.soNum, shpgOrdDtlTMsg.soNum);
            ZYPEZDItemValueSetter.setValue(soInfo.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
            soInfo = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(soInfo);

            if (soInfo != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).shpgSvcLvlCd, soInfo.shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).carrCd, soInfo.carrCd);
            }
            // END 2022/12/07 F.Fadriquela [QC#60889, ADD]

        } else {

            // Cancel
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).shipQty, cancelQty);
        }
        // 2017/11/13 M.Naito Mod QC#22503 END

        pMsg.prchReqInfo.setValidCount(1);

        NPZC103001 api = new NPZC103001();

        // execute
        api.execute(pMsg, this.onBatch);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                doErrorProcess(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), "Error PR Update API");
            }

            return false;
        }

        return true;
    }

    /**
     * Get Retail Warehouse
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return RTL_WHTMsg
     */
    private RTL_WHTMsg getRtlWh(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);

        return (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);
    }

    /**
     * isNonCSAAsset
     * @param rtlWhTMsg RTL_WHTMsg
     * @return true: non-CSA Asset false: CSA
     */
    private boolean isNonCSAAsset(RTL_WHTMsg rtlWhTMsg) {

        if (rtlWhTMsg != null) {

            INVTY_OWNRTMsg invtyOwnrTMsg = new INVTY_OWNRTMsg();
            ZYPEZDItemValueSetter.setValue(invtyOwnrTMsg.glblCmpyCd, rtlWhTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOwnrTMsg.invtyOwnrCd, rtlWhTMsg.invtyOwnrCd.getValue());
            invtyOwnrTMsg = (INVTY_OWNRTMsg) S21ApiTBLAccessor.findByKey(invtyOwnrTMsg);

            if (invtyOwnrTMsg != null) {

                if (!ZYPConstant.FLG_ON_Y.equals(invtyOwnrTMsg.cmpyInvtyFlg.getValue())) {

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * isAsset
     * @param rtlWhTMsg RTL_WHTMsg
     * @return boolean true: Asset false: Inventory
     */
    private boolean isAsset(RTL_WHTMsg rtlWhTMsg) {

        if (rtlWhTMsg != null) {

            if (INVTY_ACCT.ASSET.equals(rtlWhTMsg.invtyAcctCd.getValue())) {

                return true;
            }
        }

        return false;
    }

    /**
     * isShowroomTransfer
     * @param fromRtlWhTMsg RTL_WHTMsg
     * @param toRtlWhTMsg RTL_WHTMsg
     * @return boolean true: Showroom Transfer false: Regular Transfer
     */
    private boolean isShowroomTransfer(RTL_WHTMsg fromRtlWhTMsg, RTL_WHTMsg toRtlWhTMsg) {

        if (fromRtlWhTMsg != null && toRtlWhTMsg != null) {

            if (!RTL_WH_CATG.SHOWROOM.equals(fromRtlWhTMsg.rtlWhCatgCd.getValue()) && RTL_WH_CATG.SHOWROOM.equals(toRtlWhTMsg.rtlWhCatgCd.getValue())) {

                return true;

            } else if (RTL_WH_CATG.SHOWROOM.equals(fromRtlWhTMsg.rtlWhCatgCd.getValue()) && !RTL_WH_CATG.SHOWROOM.equals(toRtlWhTMsg.rtlWhCatgCd.getValue())) {

                return true;
            }

        } else if (fromRtlWhTMsg != null && toRtlWhTMsg == null) {

            if (RTL_WH_CATG.SHOWROOM.equals(fromRtlWhTMsg.rtlWhCatgCd.getValue())) {

                return true;
            }

        } else if (fromRtlWhTMsg == null && toRtlWhTMsg != null) {

            if (RTL_WH_CATG.SHOWROOM.equals(toRtlWhTMsg.rtlWhCatgCd.getValue())) {

                return true;
            }
        }

        return false;
    }

    /**
     * judgeLoan
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @return each ORD_TP_RS,ORD_TP_RS_LOAN,ORD_TP_RS_LOAN_EXP
     */
    private String judgeLoan(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
        param.put("cpoOrdNum", shpgOrdDtlTMsg.trxHdrNum.getValue());
        param.put("cpoDtlLineNum", shpgOrdDtlTMsg.trxLineNum.getValue());
        param.put("cpoDtlLineSubNum", shpgOrdDtlTMsg.trxLineSubNum.getValue());

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("judgeLoan", param);

        if (map != null) {

            String outOfNodeUsgFlg = (String) map.get("OUT_OF_WH_NODE_USG_FLG");
            String outOfWhInvtyProcFlg = (String) map.get("OUT_OF_WH_INVTY_PROC_FLG");

            if (ZYPConstant.FLG_ON_Y.equals(outOfNodeUsgFlg)) {

                // Get Mdse
                MDSETMsg mdseTMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, shpgOrdDtlTMsg.mdseCd);
                mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

                if (ZYPConstant.FLG_ON_Y.equals(outOfWhInvtyProcFlg) && ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

                    return ORD_TP_RS_LOAN;

                } else {

                    return ORD_TP_RS_LOAN_EXP;
                }
            }
        }

        return ORD_TP_RS;
    }

    /**
     * callRemanUpdateAPI
     * @param shpgOrd shpgOrd
     * @return true:OK false:Error
     */
    private boolean callRemanUpdateAPI(SHPG_ORDTMsg shpgOrd) {

        // Set Parameter
        NPZC126001PMsg pMsg = new NPZC126001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC126001Constant.MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrd.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(shpgOrd.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfOrdNum, shpgOrd.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, shpgOrd.soNum.getValue());

        // execute
        NPZC126001 api = new NPZC126001();
        api.execute(pMsg, this.onBatch);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                doErrorProcess(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), "Error Reman Update API");
            }

            return false;
        }

        return true;
    }

    /**
     * closeRemanRws
     * @param shpgOrd SHPG_ORDTMsg
     */
    private void closeRemanRws(SHPG_ORDTMsg shpgOrd) {

        // Init Data
        String glblCmpyCd = shpgOrd.glblCmpyCd.getValue();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Get RWS Info
        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("sceOrdTpCd", sceOrdTpCd);
        ssmParam.put("shipToCustCd", shpgOrd.shipToCustCd.getValue());

        // QC#15562
        if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {
            // RM
            ssmParam.put("trxHdrNum", shpgOrd.trxHdrNum.getValue());
            ssmParam.put("rwsRefNum", shpgOrd.soNum.getValue());

        } else {

            // RX, RL
            ssmParam.put("soNum", shpgOrd.soNum.getValue());
        }
        ssmParam.put("rwsOpenFlg", ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRwsInfo", ssmParam);

        // Create Parameter
        ArrayList<NLZC206001PMsg> putAwayList = new ArrayList<NLZC206001PMsg>();
        ArrayList<NLZC207001PMsg> rwsCompList = new ArrayList<NLZC207001PMsg>();

        // Serial Transaction
        NLZC302001PMsg pMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        int serTrxIndex = 0;

        String prevRwsNum = "";

        for (Map<String, Object> record : rs) {

            String rwsNum = (String) record.get("RWS_NUM");

            if (prevRwsNum.equals(rwsNum)) {

                // Update putAway
                NLZC206001PMsg putAway = putAwayList.get(putAwayList.size() - 1);

                int putAwayCount = putAway.RWSPutAwayList.getValidCount();
                int rcvSerCount = putAway.RcvSerNumList.getValidCount();

                // Put Away Info
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).rwsDtlLineNum, (String) record.get("RWS_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).invtyStkStsCd, (String) record.get("INVTY_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).sceOrdTpCd, (String) record.get("SCE_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).rwsStkDtTmTs, slsDt);
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).rwsStkQty, (BigDecimal) record.get("RWS_QTY"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).whCd, (String) record.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).mdseCd, (String) record.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(putAwayCount).rwsRefNum, (String) record.get("RWS_REF_NUM"));
                putAway.RWSPutAwayList.setValidCount(putAwayCount + 1);

                // Put Away Serial
                if (ZYPCommonFunc.hasValue((String) record.get("SER_NUM"))) {

                    ZYPEZDItemValueSetter.setValue(putAway.RcvSerNumList.no(rcvSerCount).rwsDtlLineNum, (String) record.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(putAway.RcvSerNumList.no(rcvSerCount).serNum, (String) record.get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(putAway.RcvSerNumList.no(rcvSerCount).mdseCd, (String) record.get("MDSE_CD"));
                    putAway.RcvSerNumList.setValidCount(rcvSerCount + 1);

                    // Serial Transaction
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serNum, (String) record.get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).mdseCd, (String) record.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxEventCd, SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxSrcTpCd, SER_TRX_SRC_TP.RWS);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxSrcHdrNum, rwsNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxSrcLineNum, (String) record.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxRefNum, (String) record.get("RWS_REF_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).fromLocCd, (String) record.get("SHIP_FROM_INVTY_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).toLocCd, (String) record.get("INVTY_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).manCratFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).toStkStsCd, (String) record.get("INVTY_STK_STS_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).locStsCd, LOC_STS.DC_STOCK);

                    serTrxIndex++;
                    pMsg.UpdateDetailList.setValidCount(serTrxIndex);
                }

            } else {

                // New putAway
                NLZC206001PMsg putAway = new NLZC206001PMsg();
                ZYPEZDItemValueSetter.setValue(putAway.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(putAway.slsDt, slsDt);

                // Put Away Info
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).rwsDtlLineNum, (String) record.get("RWS_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).invtyStkStsCd, (String) record.get("INVTY_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).sceOrdTpCd, (String) record.get("SCE_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).rwsStkDtTmTs, slsDt);
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).rwsStkQty, (BigDecimal) record.get("RWS_QTY"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).whCd, (String) record.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).mdseCd, (String) record.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).rwsRefNum, (String) record.get("RWS_REF_NUM"));

                ZYPEZDItemValueSetter.setValue(putAway.RWSPutAwayList.no(0).destInvtyLocCd, (String) record.get("INVTY_LOC_CD"));
                putAway.RWSPutAwayList.setValidCount(1);

                // Put Away Serial
                if (ZYPCommonFunc.hasValue((String) record.get("SER_NUM"))) {

                    ZYPEZDItemValueSetter.setValue(putAway.RcvSerNumList.no(0).rwsDtlLineNum, (String) record.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(putAway.RcvSerNumList.no(0).serNum, (String) record.get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(putAway.RcvSerNumList.no(0).mdseCd, (String) record.get("MDSE_CD"));
                    putAway.RcvSerNumList.setValidCount(1);

                    // Serial Transaction
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serNum, (String) record.get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).mdseCd, (String) record.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxEventCd, SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxSrcTpCd, SER_TRX_SRC_TP.RWS);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxSrcHdrNum, rwsNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxSrcLineNum, (String) record.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).serTrxRefNum, (String) record.get("RWS_REF_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).fromLocCd, (String) record.get("SHIP_FROM_INVTY_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).toLocCd, (String) record.get("INVTY_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).manCratFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).toStkStsCd, (String) record.get("INVTY_STK_STS_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(serTrxIndex).locStsCd, LOC_STS.DC_STOCK);

                    serTrxIndex++;
                    pMsg.UpdateDetailList.setValidCount(serTrxIndex);
                }

                putAwayList.add(putAway);

                // New RwsComp
                NLZC207001PMsg rwsComp = new NLZC207001PMsg();
                ZYPEZDItemValueSetter.setValue(rwsComp.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsComp.slsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(rwsComp.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(rwsComp.sceOrdTpCd, (String) record.get("SCE_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(rwsComp.rwsCloDtTmTs, slsDt);
                ZYPEZDItemValueSetter.setValue(rwsComp.whCd, (String) record.get("WH_CD"));
                ZYPEZDItemValueSetter.setValue(rwsComp.rwsRefNum, (String) record.get("RWS_REF_NUM"));

                rwsCompList.add(rwsComp);
            }
        }

        // Execute put Away Confirmation
        if (!putAwayList.isEmpty()) {

            NLZC206001 putAwayApi = new NLZC206001();
            putAwayApi.execute(putAwayList, onBatch);

            for (NLZC206001PMsg putAway : putAwayList) {

                if (S21ApiUtil.isXxMsgId(putAway)) {

                    for (int i = 0; i < putAway.xxMsgIdList.getValidCount(); i++) {

                        doErrorProcess(putAway.xxMsgIdList.no(i).xxMsgId.getValue(), "Error Put Away Confirmation from S21 DC API");
                    }
                }
            }
        }

        // Execute RWS Complete
        if (!rwsCompList.isEmpty()) {

            NLZC207001 rwsCompApi = new NLZC207001();
            rwsCompApi.execute(rwsCompList, onBatch);

            for (NLZC207001PMsg rwsComp : rwsCompList) {

                if (S21ApiUtil.isXxMsgId(rwsComp)) {

                    for (int i = 0; i < rwsComp.xxMsgIdList.getValidCount(); i++) {

                        doErrorProcess(rwsComp.xxMsgIdList.no(i).xxMsgId.getValue(), "Error RWS Complete from S21 DC API");
                    }
                }
            }
        }

        // RM
        if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {

            // Serial Transaction
            if (serTrxIndex > 0) {

                // Call API
                NLZC302001 api = new NLZC302001();
                api.execute(pMsg, onBatch);

                if (S21ApiUtil.isXxMsgId(pMsg)) {

                    for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                        doErrorProcess(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), "Serial Transaction API Error.");
                    }

                    return;
                }
            }

            // Get Machine Master
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("trxHdrNum", shpgOrd.trxHdrNum.getValue());

            ArrayList<Map<String, Object>> configCompMapList = (ArrayList<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRmnfConfigCompList", param);

            Map<String, Object> mainMachMap = new HashMap<String, Object>();
            ArrayList<Map<String, Object>> asryMachMapList = new ArrayList<Map<String, Object>>();
            ArrayList<BigDecimal> svcMachMstrList = new ArrayList<BigDecimal>();

            for (Map<String, Object> configCompMap : configCompMapList) {

                svcMachMstrList.add((BigDecimal) configCompMap.get("SVC_MACH_MSTR_PK"));

                if (ZYPConstant.FLG_ON_Y.equals((String) configCompMap.get("MAIN_MACH_FLG"))) {

                    mainMachMap = configCompMap;

                } else {

                    asryMachMapList.add(configCompMap);
                }
            }

            // Alloc off
            ArrayList<MachineMasterResultBean> errMsg = MachMstrApiEvent.callAllocOffMachMstr(glblCmpyCd, slsDt, svcMachMstrList, onBatch);

            if (!errMsg.isEmpty()) {

                for (MachineMasterResultBean msgBean : errMsg) {

                    if (ZYPCommonFunc.hasValue(msgBean.getMsgId())) {

                        doErrorProcess(msgBean.getMsgId(), "Machine Master API Error");
                    }

                    if (ZYPCommonFunc.hasValue(msgBean.getAssetErrMsgId())) {

                        doErrorProcess(msgBean.getAssetErrMsgId(), "Reman Alloc-off Staging API Error");
                    }
                }

                return;
            }

            // Release comp
            errMsg = MachMstrApiEvent.callRelCompMachMstr(glblCmpyCd, slsDt, asryMachMapList, onBatch);

            if (!errMsg.isEmpty()) {

                for (MachineMasterResultBean msgBean : errMsg) {

                    if (ZYPCommonFunc.hasValue(msgBean.getMsgId())) {

                        doErrorProcess(msgBean.getMsgId(), "Machine Master API Error");
                    }

                    if (ZYPCommonFunc.hasValue(msgBean.getAssetErrMsgId())) {

                        doErrorProcess(msgBean.getAssetErrMsgId(), "Reman Release Configuration Staging API Error");
                    }
                }

                return;
            }

            // QC#13719 : if SVC_CONFIG_MSTR_PK != null
            if (!mainMachMap.isEmpty()) {

                // create new comp (item change)
                errMsg = MachMstrApiEvent.callNewCompMachMstr(glblCmpyCd, slsDt, mainMachMap, asryMachMapList, onBatch);

                if (!errMsg.isEmpty()) {

                    for (MachineMasterResultBean msgBean : errMsg) {

                        if (ZYPCommonFunc.hasValue(msgBean.getMsgId())) {

                            doErrorProcess(msgBean.getMsgId(), "Machine Master API Error");
                        }

                        if (ZYPCommonFunc.hasValue(msgBean.getAssetErrMsgId())) {

                            doErrorProcess(msgBean.getAssetErrMsgId(), "Reman Create New Configuration Staging API Error");
                        }
                    }

                    return;
                }

            }
        }
    }

    /**
     * Call PR Update API Update/Update Mode
     * @param glblCmpycd String
     * @param rwsNumList ArrayList<String>
     */
    private void callPrUpdateAPIUpdateMode(String glblCmpycd, ArrayList<String> rwsNumList) {

        // Get PR detail list
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpycd);
        queryParam.put("rwsNumList", rwsNumList);
        List<RWS_DTLTMsg> rwsDtlTMsgList = new ArrayList<RWS_DTLTMsg>();
        this.ssmBatchClient.queryObject("getPrDetailRwsNumUpdateList", queryParam, new GetRwsDetail(rwsDtlTMsgList));

        if (rwsDtlTMsgList.isEmpty()) {

            return;

        }

        String prePrchReqNum = null;
        int prCnt = 0;
        NPZC103001PMsg msg = null;

        for (RWS_DTLTMsg tMsg : rwsDtlTMsgList) {

            if (!ZYPCommonFunc.hasValue(tMsg.prchReqNum)) {
                continue;
            }

            String prchReqNum = tMsg.prchReqNum.getValue();

            if (prCnt != 0 && !prchReqNum.equals(prePrchReqNum)) {

                if (msg != null) {
                    NPZC103001 api = new NPZC103001();
                    api.execute(msg, onBatch);

                    // Get message list
                    List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

                    if (!msgList.isEmpty()) {

                        for (String xxMsgId : msgList) {

                            if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                                S21InfoLogOutput.println(NDMM0012E, new String[] {"NPZC103001" });
                                doErrorProcess(xxMsgId, msg);

                                // DB error
                                throw new S21AbendException(NLZM2030E);

                            } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                                // Continue when Warning
                                doErrorProcess(xxMsgId, msg);
                            }
                        }
                    }
                }

                prCnt = 0;

                msg = new NPZC103001PMsg();
                msg.eventId.setValue(NPZC103001Constant.EVENT_UPDATE);
                msg.xxModeCd.setValue(NPZC103001Constant.MODE_UPDATE);
                msg.glblCmpyCd.setValue(glblCmpycd);
                msg.prchReqNum.setValue(tMsg.prchReqNum.getValue());
                msg.prchReqInfo.no(prCnt).prchReqLineNum.setValue(tMsg.prchReqLineNum.getValue());
                msg.prchReqInfo.no(prCnt).prchReqLineSubNum.setValue(tMsg.prchReqLineSubNum.getValue());
                msg.prchReqInfo.no(prCnt).rwsNum.setValue(tMsg.rwsNum.getValue());
                msg.prchReqInfo.setValidCount(msg.prchReqInfo.getValidCount() + 1);
                prCnt++;

            } else {

                if (msg == null) {
                    msg = new NPZC103001PMsg();
                    msg.eventId.setValue(NPZC103001Constant.EVENT_UPDATE);
                    msg.xxModeCd.setValue(NPZC103001Constant.MODE_UPDATE);
                    msg.glblCmpyCd.setValue(glblCmpycd);
                    msg.prchReqNum.setValue(tMsg.prchReqNum.getValue());
                }

                msg.prchReqInfo.no(prCnt).prchReqLineNum.setValue(tMsg.prchReqLineNum.getValue());
                msg.prchReqInfo.no(prCnt).prchReqLineSubNum.setValue(tMsg.prchReqLineSubNum.getValue());
                msg.prchReqInfo.no(prCnt).rwsNum.setValue(tMsg.rwsNum.getValue());
                msg.prchReqInfo.setValidCount(msg.prchReqInfo.getValidCount() + 1);
                prCnt++;
            }

            prePrchReqNum = prchReqNum;
        }

        if (msg != null) {
            NPZC103001 api = new NPZC103001();
            api.execute(msg, onBatch);

            // Get message list
            List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

            if (!msgList.isEmpty()) {

                for (String xxMsgId : msgList) {

                    if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                        S21InfoLogOutput.println(NDMM0012E, new String[] {"NPZC103001" });
                        doErrorProcess(xxMsgId, msg);

                        // DB error
                        throw new S21AbendException(NLZM2030E);

                    } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                        // Continue when Warning
                        doErrorProcess(xxMsgId, msg);
                    }
                }
            }
        }
    }

    /**
     * get Shipping serial number List
     * @param param NLZC211101PMsg
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @return List<String>
     */
    private List<String> getShipSerNumList(NLZC211001PMsg param, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("wrkTrxId", param.wrkTrxId.getValue());
        queryParam.put("soNum", shpgOrdConfDtlTMsg.soNum.getValue());
        queryParam.put("soSlpNum", shpgOrdConfDtlTMsg.soSlpNum.getValue());

        List<String> shipSerNumList = (List<String>) this.ssmBatchClient.queryObjectList("getShipSerNumLsit", queryParam);

        if (shipSerNumList == null || shipSerNumList.isEmpty()) {

            return null;

        }

        return shipSerNumList;
    }

    /** QC#18302 Add.
     * Allocation Cancel To Partially Shipped
     */
    private class allocationCancelToPartiallyShipped extends S21SsmBooleanResultSetHandlerSupport {

        /** SHPG_ORDTMsg */
        private SHPG_ORDTMsg shpgOrdTMsg;

        public allocationCancelToPartiallyShipped(SHPG_ORDTMsg shpgOrdTMsg) {
            this.shpgOrdTMsg = shpgOrdTMsg;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            List<NWZC107001PMsg> pMsgList = new ArrayList<NWZC107001PMsg>();
            while (rs.next()) {

                // Set parameter for Allocation for non CPO API.
                NWZC107001PMsg inPMsg = new NWZC107001PMsg();
                inPMsg.glblCmpyCd.setValue(shpgOrdTMsg.glblCmpyCd.getValue());
                inPMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
                inPMsg.xxRqstTpCd.setValue(NWZC107001.REQ_TP_CANCEL);
                inPMsg.trxSrcTpCd.setValue(rs.getString("TRX_SRC_TP_CD"));
                inPMsg.trxHdrNum.setValue(rs.getString("TRX_HDR_NUM"));
                inPMsg.trxLineNum.setValue(rs.getString("TRX_LINE_NUM"));
                inPMsg.trxLineSubNum.setValue(rs.getString("TRX_LINE_SUB_NUM"));

                pMsgList.add(inPMsg);
            }

            if (pMsgList.size() > 0) {
                // Execute Allocation for non CPO API.
                NWZC107001  nWZC107001 = new NWZC107001();
                nWZC107001.execute(pMsgList, ONBATCH_TYPE.BATCH);

                for (NWZC107001PMsg outPMsg : pMsgList) {
                    if (outPMsg.xxMsgIdList.getValidCount() > 0) {
                        NLZC211001PMsg inpPrmPMsg = (NLZC211001PMsg) msgMap.getPmsg();
                        EZDMsg.copy(outPMsg.xxMsgIdList, null, inpPrmPMsg.xxMsgIdList, null);
                        inpPrmPMsg.xxMsgIdList.setValidCount(outPMsg.xxMsgIdList.getValidCount());
                        return false;
                    }
                }

            }

            return true;
        }
    }

    /**
     * Add QC#26866 PO Cancel Process
     */
    private class PoCancelProcess extends S21SsmVoidResultSetHandlerSupport {

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            List<NWZC107001PMsg> alloPmsgList = new ArrayList<NWZC107001PMsg>();
            List<NPZC004001PMsg> poStsPmsgList = new ArrayList<NPZC004001PMsg>();

            while (rs.next()) {

                String shpgStsCd = nullToEmpty(rs.getString(SHPG_STS_CD));
                String glblCmpyCd = nullToEmpty(rs.getString(GLBL_CMPY_CD));
                String trxHdrNum = nullToEmpty(rs.getString(TRX_HDR_NUM));
                String trxLineNum = nullToEmpty(rs.getString(TRX_LINE_NUM));
                String mdseCd = nullToEmpty(rs.getString(MDSE_CD));

                if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {
                    // Allocation Off
                    // Set parameter for Allocation for non CPO API.
                    NWZC107001PMsg allocApiPMsg = new NWZC107001PMsg();
                    ZYPEZDItemValueSetter.setValue(allocApiPMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(allocApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                    ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstTpCd, NWZC107001.REQ_TP_CANCEL);
                    ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxSrcTpCd, TRX_SRC_TP.BUY_BACK);
                    ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxHdrNum, trxHdrNum);
                    ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineNum, trxLineNum);
                    ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineSubNum, "001");
                    alloPmsgList.add(allocApiPMsg);

                    // PO Cancel
                    NPZC004001PMsg pMsg = new NPZC004001PMsg();

                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, trxHdrNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.CANCELLED);
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, trxLineNum);
                    poStsPmsgList.add(pMsg);
                }
            }

            if (alloPmsgList.size() > 0) {
                // Execute Allocation for non CPO API.
                NWZC107001 allocApi = new NWZC107001();
                allocApi.execute(alloPmsgList, onBatch);

                for (NWZC107001PMsg outPMsg : alloPmsgList) {
                    // Get message list
                    List<String> msgList = S21ApiUtil.getXxMsgIdList(outPMsg);
                    if (!msgList.isEmpty()) {
                        for (String xxMsgId : msgList) {

                            if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                                S21InfoLogOutput.println(NDMM0012E, new String[] {"NWZC107001" });
                                doErrorProcess(xxMsgId, outPMsg);
                                throw new S21AbendException(NLZM2030E);

                            } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                                // Continue when Warning
                                doErrorProcess(xxMsgId, outPMsg);
                            }
                        }
                    }
                }
            }

            if (poStsPmsgList.size() > 0) {
                // Execute PO Status Update API
                NPZC004001 poStsUpdApi = new NPZC004001();
                poStsUpdApi.execute(poStsPmsgList, onBatch);

                for (NPZC004001PMsg outPMsg : poStsPmsgList) {
                    // Get message list
                    List<String> msgList = S21ApiUtil.getXxMsgIdList(outPMsg);
                    if (!msgList.isEmpty()) {
                        for (String xxMsgId : msgList) {

                            if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                                S21InfoLogOutput.println(NDMM0012E, new String[] {"NPZC004001" });
                                doErrorProcess(xxMsgId, outPMsg);
                                throw new S21AbendException(NLZM2030E);

                            } else if (xxMsgId.endsWith(MSG_TYPE_WARNING)) {

                                // Continue when Warning
                                doErrorProcess(xxMsgId, outPMsg);
                            }
                        }
                    }
                }
            }
        }
    }
}
