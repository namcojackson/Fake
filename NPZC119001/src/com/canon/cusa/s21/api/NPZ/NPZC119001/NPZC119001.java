/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC119001;

import static com.canon.cusa.s21.api.NPZ.NPZC119001.constant.NPZC119001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDConnectionMgr;
import business.db.CLICK_PRT_RTRN_RQSTTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.MDSETMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.PRCH_REQ_TPTMsg;
import business.db.RTL_SWHTMsg;
import business.db.SHPG_ORD_PRO_NUM_LISTTMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;
import business.parts.NPZC119001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Tech Parts Return API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/22/2015   Hitachi         T.Harada        Create          
 * 01/04/2016   Fujitsu         S.Nakai         Update          QC#2425
 * 03/30/2016   Hitachi         T.Iwamoto       Update          QC#4287
 * 04/05/2016   Hitachi         T.Iwamoto       Update          QC#6390
 * 09/23/2016   CSAI            K.Lee           Update          QC#13310
 * 11/02/2016   Hitachi         T.Tomita        Update          QC#15740
 * 11/02/2016   Hitachi         T.Iwamoto       Update          QC#15490
 * 11/04/2016   Hitachi         T.Iwamoto       Update          QC#15807
 * 11/08/2016   Hitachi         K.Yamada        Update          QC#15490
 * 2016/11/10   Hitachi         K.Kojima        Update          QC#15490
 * 2016/11/18   Hitachi         A.Kohinata      Update          QC#16029
 * 2016/11/18   Hitachi         T.Tomita        Update          QC#16079
 * 2016/11/22   Hitachi         T.Tomita        Update          QC#16079
 * 2016/11/25   Hitachi         T.Tomita        Update          QC#16165
 * 2016/11/30   Hitachi         T.Tomita        Update          QC#16079
 * 2016/12/01   Hitachi         K.Kojima        Update          QC#16033
 * 2016/12/05   Hitachi         T.Tomita        Update          QC#16079
 * 2017/01/04   Hitachi         T.Tomita        Update          QC#16638
 * 2017/02/14   Hitachi         K.Kojima        Update          QC#16301
 * 2017/02/21   Hitachi         A.Kohinata      Update          QC#16123
 * 2017/10/20   CITS            M.Naito         Update          QC#17246
 * 2018/03/09   CITS            T.Tokutomi      Update          QC#21913
 * 2018/04/12   Hitachi         K.Kojima        Update          QC#23639
 * 2018/05/25   Hitachi         K.Kojima        Update          QC#26331
 * 2018/10/26   CITS            M.Naito         Update          QC#28965
 * 2019/12/13   CITS            M.Naito         Update          QC#54698
 * 2021/03/08   CITS            T.Wada          Update          QC#58518
 * 2022/03/14   Hitachi         A.Kohinata      Update          QC#59780
 * 2022/04/20   Hitachi         K.Kitachi       Update          QC#59897
 * </pre>
 */
public class NPZC119001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** Parts Return Request Status Map */
    private Map<String, String> prtRtrnReqStsMap = new HashMap<String, String>();

    /** Process Mode */
    private String processMode = null;

    /** DefaultWarehouse */
    private String defSrcRtlWhCd = null;
    /** DefaultSubWarehouse */
    private String defSrcRtlSwhCd = null;
    /** DefaultReturnWarehouse */
    private String defRtrnToRtlWhCd = null;
    /** DefaultReturnSubWarehouse */
    private String defRtrnToRtlSwhCd = null;
    // START 2018/04/12 K.Kojima [QC#23639,ADD]
    /** DefaultReturnWarehouse From SubWarehourse */
    private String defRtrnToRtlWhCdFromSWH = null;
    /** DefaultReturnSubWarehouse From SubWarehourse */
    private String defRtrnToRtlSwhCdFromSWH = null;
    // END 2018/04/12 K.Kojima [QC#23639,ADD]

    // START 2016/12/01 K.Kojima [QC#16033,ADD]
    /** prtRtrnRqstSq */
    private BigDecimal prtRtrnRqstSq = null;
    // END 2016/12/01 K.Kojima [QC#16033,ADD]

    // add start 2022/03/14 QC#59780
    /** prchReqLineSubNum */
    private BigDecimal prchReqLineSubNum = null;
    // add end 2022/03/14 QC#59780

    /**
     * Constructor
     */
    public NPZC119001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute API.
     * @param pMsg NPZC119001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(NPZC119001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        this.onBatchType = onBatTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        if (checkParameter(msgMap)) {
            getProcessMode(pMsg);
            if (checkParameter4EachMode(msgMap, pMsg)) {
                doProcess(msgMap, pMsg);
            }
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {

        NPZC119001PMsg pMsg = (NPZC119001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NPZM0179E);
        // mandatoryCheck(msgMap, pMsg.slsDt, NPZM0180E);
        // del start 2016/11/02 CSA Defect#15740
        // mandatoryCheck(msgMap, pMsg.prchReqStsNm, NPZM0191E);
        // del end 2016/11/02 CSA Defect#15740
        mandatoryCheck(msgMap, pMsg.clickKeyTxt, NPZM0260E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }

        return true;
    }

    private void getProcessMode(NPZC119001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(glblCmpyCd, CONST_GRP_ID_NPZC1190, CONST_PRT_RTRN_REQ_STS_KEY);
        if (dsCondConstTMsg != null) {
            this.prtRtrnReqStsMap.put(CONST_STS_AWAITINGSHIPMENT, dsCondConstTMsg.dsCondConstValTxt_01.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_SHIPMENTSHORT, dsCondConstTMsg.dsCondConstValTxt_02.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_SHIPPED, dsCondConstTMsg.dsCondConstValTxt_03.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_CLOSE, dsCondConstTMsg.dsCondConstValTxt_04.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_SUCCESS, dsCondConstTMsg.dsCondConstValTxt_05.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_ERROR, dsCondConstTMsg.dsCondConstValTxt_06.getValue());
//            this.prtRtrnReqStsMap.put(CONST_STS_ERROR, dsCondConstTMsg.dsCondConstValTxt_07.getValue());

//            if (pMsg.prchReqStsNm.getValue().equals(this.prtRtrnReqStsMap.get(CONST_STS_NEW))) {
//                this.processMode = CONST_STS_NEW;
//            } else if (pMsg.prchReqStsNm.getValue().equals(this.prtRtrnReqStsMap.get(CONST_STS_SHIPPED))) {
//                this.processMode = CONST_STS_SHIPPED;
//            } else if (pMsg.prchReqStsNm.getValue().equals(this.prtRtrnReqStsMap.get(CONST_STS_SHIPMENTSHORT))) {
//                this.processMode = CONST_STS_SHIPMENTSHORT;
//            }
            // mod start 2016/11/18 CSA Defect#16079
            if (!hasValue(pMsg.prchReqNum)) {
                this.processMode = CONST_STS_AWAITINGSHIPMENT;
            }
            // mod end 2016/11/18 CSA Defect#16079
        }
    }

    // mod start 2016/11/18 CSA Defect#16079
    private boolean checkParameter4EachMode(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg) {
        // add start 2016/11/02 CSA Defect#15740
        if (hasValue(this.processMode) && this.processMode.equals(CONST_STS_AWAITINGSHIPMENT)) {
            mandatoryCheck(msgMap, pMsg.prchReqStsNm, NPZM0191E);
        }
        // add end 2016/11/02 CSA Defect#15740
        // mod start 2016/11/22 CSA Defect#16079
        if (!hasValue(this.processMode) || !this.processMode.equals(CONST_STS_AWAITINGSHIPMENT)) {
            mandatoryCheck(msgMap, pMsg.prchReqNum, NPZM0060E);
            if (pMsg.ShipInfoList.getValidCount() != 0) {
                for (int i = 0; i < pMsg.ShipInfoList.getValidCount(); i++) {
                    // mod start 2022/03/14 QC#59780
                    //mandatoryCheck(msgMap, pMsg.ShipInfoList.no(i).proNum, NPZM0192E);
                    mandatoryCheck(msgMap, pMsg.ShipInfoList.no(i).origClickTrkNum, NPZM0192E);
                    // mod end 2022/03/14 QC#59780
                }
            } else {
                msgMap.addXxMsgId(NPZM0192E);
            }
        }
        // mod end 2016/11/22 CSA Defect#16079

        if (hasValue(this.processMode) && this.processMode.equals(CONST_STS_AWAITINGSHIPMENT)) {
//            mandatoryCheck(msgMap, pMsg.invtyLocCd, NPZM0193E);
            mandatoryCheck(msgMap, pMsg.techTocCd, NPZM0181E);
            mandatoryCheck(msgMap, pMsg.rtlWhCd, NPZM0261E);
            //mandatoryCheck(msgMap, pMsg.rtlSwhCd, NPZM0262E);
            if (pMsg.PartsList.getValidCount() != 0) {
                for (int i = 0; i < pMsg.PartsList.getValidCount(); i++) {
                    mandatoryCheck(msgMap, pMsg.PartsList.no(i).prchReqSrcTpNm, NPZM0194E);
                    mandatoryCheck(msgMap, pMsg.PartsList.no(i).mdseCd, NPZM0044E);
                    mandatoryCheck(msgMap, pMsg.PartsList.no(i).prchReqQty, NPZM0064E);
                    // add start 2016/11/02 CSA Defect#15490
                    mandatoryCheck(msgMap, pMsg.PartsList.no(i).clickKeyDtlTxt, NPZM0295E);
                    // add end 2016/11/02 CSA Defect#15490
                }
            } else {
                msgMap.addXxMsgId(NPZM0194E);
                msgMap.addXxMsgId(NPZM0044E);
                msgMap.addXxMsgId(NPZM0064E);
                // add start 2016/11/02 CSA Defect#15490
                msgMap.addXxMsgId(NPZM0295E);
                // add end 2016/11/02 CSA Defect#15490
            }
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }
    // mod end 2016/11/18 CSA Defect#16079

    // mod start 2017/01/04 CSA Defect#16638
    private void doProcess(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg) {
        NPZC103001PMsg retPrUpdApiPMsg = null;

        if (hasValue(this.processMode) && this.processMode.equals(CONST_STS_AWAITINGSHIPMENT)) {
            /** Parts Return Request Mode */
            retPrUpdApiPMsg = executeReturnRequest(msgMap, pMsg, null, null);
            if (!msgMap.getMsgMgr().isXxMsgId() && retPrUpdApiPMsg != null) {
                pMsg.prchReqNum.setValue(retPrUpdApiPMsg.prchReqNum.getValue());
            }
            insertClickTechRcvPrtForAwatingShip(msgMap, pMsg, this.prtRtrnReqStsMap.get(CONST_STS_SUCCESS), retPrUpdApiPMsg);
        } else {
            List<Map<String, Object>> parentList = getParentPrchReqDtl(pMsg);
            // mod start 2022/03/14 QC#59780
            //String strProNum = getStrProNum(pMsg);
            String origClickTrkNum = getTargetTrkNum(pMsg);
            // mod end 2022/03/14 QC#59780
            List<Map<String, Object>> shipList = new ArrayList<Map<String, Object>>();
            boolean regProNumFlg = false;
            String parentStsCd;
            for (Map<String, Object> parent : parentList) {
                this.processMode = null;
                parentStsCd = (String) parent.get("PRCH_REQ_LINE_STS_CD");
                if (PRCH_REQ_LINE_STS.AWAITING_SHIPPING.equals(parentStsCd)) {
                    // Shipment Mode
                    shipList.add(parent);
                    regProNumFlg = true;
                } else if (PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED.equals(parentStsCd) || PRCH_REQ_LINE_STS.SHIPPED.equals(parentStsCd)) {
                    regProNumFlg = true;
                } else if (PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED.equals(parentStsCd) || PRCH_REQ_LINE_STS.RECEIVED.equals(parentStsCd)) {
                    // mod start 2022/03/14 QC#59780
                    //regProNumFlg = shortShipProcess(msgMap, pMsg, parent, strProNum);
                    regProNumFlg = shortShipProcess(msgMap, pMsg, parent, origClickTrkNum);
                    // mod end 2022/03/14 QC#59780
                }
            }

            if (shipList.size() > 0) {
                // Shipment Mode : Call NLZC2100 API
                this.processMode = CONST_STS_SHIPPED;
                // mod start 2022/03/14 QC#59780
                //executeShippingRequest(msgMap, pMsg, shipList, strProNum);
                executeShippingRequest(msgMap, pMsg, shipList, origClickTrkNum);
                // mod end 2022/03/14 QC#59780
            }

            if (regProNumFlg) {
                // mod start 2022/03/14 QC#59780
                //insertRtrnProNumListTMsg(pMsg, strProNum);
                insertShpgOrdProNumList(pMsg, origClickTrkNum);
                // mod end 2022/03/14 QC#59780
            }
        }
    }
    // mod end 2017/01/04 CSA Defect#16638

    // add start 2017/01/04 CSA Defect#16638
    // mod start 2022/03/14 QC#59780
    //private boolean shortShipProcess(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, Map<String, Object> parentPrchReqDtlMap, String strProNum) {
    private boolean shortShipProcess(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, Map<String, Object> parentPrchReqDtlMap, String origClickTrkNum) {
    // mod end 2022/03/14 QC#59780
        String childStsCd;
        String prchReqNum = (String) parentPrchReqDtlMap.get("PRCH_REQ_NUM");
        String prchReqLineNum = (String) parentPrchReqDtlMap.get("PRCH_REQ_LINE_NUM");
        Map<String, Object> childPrchReqDtlMap = getChildPrchReqDtl(pMsg.glblCmpyCd.getValue(), prchReqNum, prchReqLineNum);
        if (childPrchReqDtlMap == null) {
            // Short Ship Mode
            // mod start 2022/03/14 QC#59780
            //executeShortShip(msgMap, pMsg, parentPrchReqDtlMap, strProNum);
            executeShortShip(msgMap, pMsg, parentPrchReqDtlMap, origClickTrkNum);
            // mod end 2022/03/14 QC#59780
            return true;
        }

        childStsCd = (String) childPrchReqDtlMap.get("PRCH_REQ_LINE_STS_CD");
        // add start 2022/03/14 QC#59780
        this.prchReqLineSubNum = (BigDecimal) childPrchReqDtlMap.get("PRCH_REQ_LINE_SUB_NUM");
        // add end 2022/03/14 QC#59780
        if (PRCH_REQ_LINE_STS.AWAITING_SHIPPING.equals(childStsCd)) {
            // Only Insert Tracking#
            return true;
        } else if (PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED.equals(childStsCd)) {
            // Nothing
            return false;
        } else if (PRCH_REQ_LINE_STS.SHIPPED.equals(childStsCd)) {
            // Only Insert Tracking#
            return true;
        } else if (PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED.equals(childStsCd) || PRCH_REQ_LINE_STS.RECEIVED.equals(childStsCd)) {
            // Short Ship Mode
            // mod start 2022/03/14 QC#59780
            //executeShortShip(msgMap, pMsg, childPrchReqDtlMap, strProNum);
            executeShortShip(msgMap, pMsg, childPrchReqDtlMap, origClickTrkNum);
            // mod end 2022/03/14 QC#59780
            return true;
        }
        // Nothing
        return false;
    }

    // mod start 2022/03/14 QC#59780
    //private void executeShortShip(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, Map<String, Object> prchReqDtlMap, String strProNum) {
    private void executeShortShip(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, Map<String, Object> prchReqDtlMap, String origClickTrkNum) {
    // mod end 2022/03/14 QC#59780
        BigDecimal shipQty = (BigDecimal) prchReqDtlMap.get("SHIP_QTY");
        if (!hasValue(shipQty) || BigDecimal.ZERO.compareTo(shipQty) >= 0) {
            // Not Short Ship
            return;
        }
        // START 2019/12/13 M.Naito [QC#54698,ADD]
        BigDecimal backToTechQty = (BigDecimal) prchReqDtlMap.get("BACK_TO_TECH_QTY");
        if (!hasValue(backToTechQty) || BigDecimal.ZERO.compareTo(backToTechQty) >= 0) {
            // Not Short Ship
            return;
        }
        // END 2019/12/13 M.Naito [QC#54698,ADD]
        boolean apiErrFlg = false;
        this.processMode = CONST_STS_SHIPMENTSHORT;
        // mod start 2022/03/14 QC#59780
        //NPZC103001PMsg retPrUpdApiPMsg = executeReturnRequest(msgMap, pMsg, prchReqDtlMap, strProNum);
        NPZC103001PMsg retPrUpdApiPMsg = executeReturnRequest(msgMap, pMsg, prchReqDtlMap, getTrkNumForS21(origClickTrkNum));
        // mod end 2022/03/14 QC#59780
        if (retPrUpdApiPMsg == null || retPrUpdApiPMsg.xxMsgIdList.getValidCount() > 0) {
            apiErrFlg = true;
        }
        // mod start 2022/03/14 QC#59780
        //insertClickTechRcvPrtForShip(msgMap, pMsg, this.prtRtrnReqStsMap.get(CONST_STS_SHIPPED), retPrUpdApiPMsg, prchReqDtlMap, apiErrFlg);
        insertClickTechRcvPrtForShip(msgMap, pMsg, this.prtRtrnReqStsMap.get(CONST_STS_SHIPPED), retPrUpdApiPMsg, prchReqDtlMap, apiErrFlg, origClickTrkNum);
        // mod end 2022/03/14 QC#59780
        // add start 2022/03/14 QC#59780
        if (retPrUpdApiPMsg != null && retPrUpdApiPMsg.prchReqInfo.getValidCount() > 0) {
            this.prchReqLineSubNum = retPrUpdApiPMsg.prchReqInfo.no(0).prchReqLineSubNum.getValue();
        }
        // add end 2022/03/14 QC#59780
    }
    // add end 2017/01/04 CSA Defect#16638

    private void insertClickTechRcvPrtForAwatingShip(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, String prchReqStsNm, NPZC103001PMsg retPrUpdApiPMsg) {
        if (!msgMap.getMsgMgr().isXxMsgId()) {
            /** success */
            // mod start 2022/03/14 QC#59780
            //insertClickTechRcvPrt(pMsg, prchReqStsNm, retPrUpdApiPMsg, null);
            insertClickTechRcvPrt(pMsg, prchReqStsNm, retPrUpdApiPMsg, null, null);
            // mod end 2022/03/14 QC#59780
            pMsg.prchReqStsNm.setValue(prchReqStsNm);
        } else {
            /** error */
            // START 2017/02/14 K.Kojima [QC#16301,ADD]
            // This API is called directly from Clicksoft that will not control any transactions in S21.
            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
            EZDConnectionMgr.getInstance().rollback();
            // END 2017/02/14 K.Kojima [QC#16301,ADD]
            // mod start 2022/03/14 QC#59780
            //insertClickTechRcvPrt(pMsg, this.prtRtrnReqStsMap.get(CONST_STS_ERROR), retPrUpdApiPMsg, null);
            insertClickTechRcvPrt(pMsg, this.prtRtrnReqStsMap.get(CONST_STS_ERROR), retPrUpdApiPMsg, null, null);
            // mod end 2022/03/14 QC#59780
            pMsg.prchReqStsNm.setValue(this.prtRtrnReqStsMap.get(CONST_STS_ERROR));
        }
    }

    // mod start 2022/03/14 QC#59780
    //private void insertClickTechRcvPrtForShip(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, String prchReqStsNm, NPZC103001PMsg retPrUpdApiPMsg, Map<String, Object> prchReqDtlMap, boolean apiErrFlg) {
    private void insertClickTechRcvPrtForShip(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, String prchReqStsNm, NPZC103001PMsg retPrUpdApiPMsg, Map<String, Object> prchReqDtlMap, boolean apiErrFlg, String origClickTrkNum) {
    // mod end 2022/03/14 QC#59780
        if (!apiErrFlg) {
            /** success */
            // mod start 2022/03/14 QC#59780
            //insertClickTechRcvPrt(pMsg, prchReqStsNm, retPrUpdApiPMsg, prchReqDtlMap);
            insertClickTechRcvPrt(pMsg, prchReqStsNm, retPrUpdApiPMsg, prchReqDtlMap, origClickTrkNum);
            // mod end 2022/03/14 QC#59780
            pMsg.prchReqStsNm.setValue(prchReqStsNm);
        } else {
            /** error */
            // START 2017/02/14 K.Kojima [QC#16301,ADD]
            // This API is called directly from Clicksoft that will not control any transactions in S21.
            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
            EZDConnectionMgr.getInstance().rollback();
            // END 2017/02/14 K.Kojima [QC#16301,ADD]
            // mod start 2022/03/14 QC#59780
            //insertClickTechRcvPrt(pMsg, this.prtRtrnReqStsMap.get(CONST_STS_ERROR), retPrUpdApiPMsg, prchReqDtlMap);
            insertClickTechRcvPrt(pMsg, this.prtRtrnReqStsMap.get(CONST_STS_ERROR), retPrUpdApiPMsg, prchReqDtlMap, origClickTrkNum);
            // mod end 2022/03/14 QC#59780
            pMsg.prchReqStsNm.setValue(this.prtRtrnReqStsMap.get(CONST_STS_ERROR));
        }
    }
    // mod end 2016/11/22 CSA Defect#16079

    // mod start 2016/11/22 CSA Defect#16079
    // mod start 2016/11/08 CSA Defect#15490
    // START 2016/11/25 T.Tomita [QC#16165, MOD]
    private NPZC103001PMsg executeReturnRequest(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, Map<String, Object> prchReqDtlMap, String strProNum) {

        setDefSrcRtlWh(pMsg);

        // mod start 2016/11/18 CSA Defect#16079
        /** Call NPZC1030 PR Update API. */
        NPZC103001PMsg retPrUpdApiPMsg = null;
        if (this.processMode.equals(CONST_STS_AWAITINGSHIPMENT)) {
            retPrUpdApiPMsg = callPRUpdateAPI(msgMap, pMsg);
        } else if (this.processMode.equals(CONST_STS_SHIPMENTSHORT)) {
            retPrUpdApiPMsg = callPRUpdateAPIForShortShip(msgMap, pMsg, prchReqDtlMap, strProNum);
        }
        // mod end 2016/11/18 CSA Defect#16079
        if (retPrUpdApiPMsg == null) {
            return null;
        }
//        if (msgMap.getMsgMgr().isXxMsgId()) {
//            return null;
//        }
        for (int i = 0; i < retPrUpdApiPMsg.xxMsgIdList.getValidCount(); i++) {
            msgMap.addXxMsgId(retPrUpdApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
        }
        return retPrUpdApiPMsg;
    }
    // END 2016/11/25 T.Tomita [QC#16165, MOD]
    // mod end 2016/11/08 CSA Defect#15490
    // mod end 2016/11/22 CSA Defect#16079

    private void setDefSrcRtlWh(NPZC119001PMsg pMsg) {

        List<Map<String, Object>> retRtlWh = getRtlWh(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.rtlWhCd.getValue());
        if (retRtlWh == null || retRtlWh.size() == 0) {
            return;
        }
        defSrcRtlWhCd = (String) retRtlWh.get(0).get("DEF_SRC_RTL_WH_CD");
        defSrcRtlSwhCd = (String) retRtlWh.get(0).get("DEF_SRC_RTL_SWH_CD");
        defRtrnToRtlWhCd = (String) retRtlWh.get(0).get("DEF_RTRN_TO_RTL_WH_CD");
        defRtrnToRtlSwhCd = (String) retRtlWh.get(0).get("DEF_RTRN_TO_RTL_SWH_CD");

        // START 2018/04/12 K.Kojima [QC#23639,ADD]
        RTL_SWHTMsg tMsg = getRtlSwhTMsg(pMsg.glblCmpyCd.getValue(), pMsg.rtlWhCd.getValue(), pMsg.rtlSwhCd.getValue());
        if (tMsg == null) {
            return;
        }
        defRtrnToRtlWhCdFromSWH = tMsg.defRtrnToRtlWhCd.getValue();
        defRtrnToRtlSwhCdFromSWH = tMsg.defRtrnToRtlSwhCd.getValue();
        // END 2018/04/12 K.Kojima [QC#23639,ADD]
    }

    // mod start 2016/11/18 CSA Defect#16079
    private NPZC103001PMsg callPRUpdateAPI(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg) {

        NPZC103001PMsg setPMsg = new NPZC103001PMsg();

        /** Header */
        setValue(setPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(setPMsg.procDt, pMsg.slsDt);

        setValue(setPMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        setValue(setPMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        setValue(setPMsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        setValue(setPMsg.prchReqCratByPsnCd, pMsg.techTocCd);
        setValue(setPMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.PHONE);
        setValue(setPMsg.prchReqApvlStsCd, getPrchReqTpCd(pMsg.glblCmpyCd.getValue()));
        setValue(setPMsg.prchReqApvlByPsnCd, pMsg.techTocCd);
        setValue(setPMsg.rqstTechTocCd, pMsg.techTocCd);

        setValue(setPMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.TECH_RETURN);
        setValue(setPMsg.prchReqTpCd, PRCH_REQ_TP.TECH_RETURN);

        /** Detail */
        String strProNum = null;
        if (pMsg.ShipInfoList.getValidCount() > 0) {
            // mod start 2022/03/14 QC#59780
            //strProNum = pMsg.ShipInfoList.no(0).proNum.getValue();
            strProNum = getTrkNumForS21(pMsg.ShipInfoList.no(0).origClickTrkNum.getValue());
            // mod end 2022/03/14 QC#59780
        }

        int reqPartsListCount = pMsg.PartsList.getValidCount();
        setPMsg.prchReqInfo.setValidCount(reqPartsListCount);

        for (int i = 0; i < reqPartsListCount; i++) {

            String prchReqLineTpCd = null;
            String rtlSwhCd = null;
            String toStkStsCd = null;

            DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(pMsg.glblCmpyCd.getValue(), CONST_GRP_ID_NPZC1190_RTRN_TP, pMsg.PartsList.no(i).prchReqSrcTpNm.getValue());
            if (dsCondConstTMsg != null) {
                prchReqLineTpCd = dsCondConstTMsg.dsCondConstValTxt_01.getValue();
                rtlSwhCd = dsCondConstTMsg.dsCondConstValTxt_02.getValue();
                toStkStsCd = dsCondConstTMsg.dsCondConstValTxt_03.getValue();
            } else {
                msgMap.addXxMsgId(NPZM0292E);
                return null;
            }

            setValue(setPMsg.prchReqInfo.no(i).prchReqLineTpCd, prchReqLineTpCd);
            setValue(setPMsg.prchReqInfo.no(i).procrTpCd, PROCR_TP.WAREHOUSE);

            setValue(setPMsg.prchReqInfo.no(i).srcInvtyLocCd, pMsg.rtlWhCd.getValue().concat(rtlSwhCd));
            setValue(setPMsg.prchReqInfo.no(i).mdseCd, pMsg.PartsList.no(i).mdseCd);
            setValue(setPMsg.prchReqInfo.no(i).prchReqQty, pMsg.PartsList.no(i).prchReqQty);
            setValue(setPMsg.prchReqInfo.no(i).proNum, strProNum);
            setValue(setPMsg.prchReqInfo.no(i).prchReqLineCmntTxt, pMsg.PartsList.no(i).prchReqLineCmntTxt);

            setValue(setPMsg.prchReqInfo.no(i).prchReqLineStsCd, PRCH_REQ_LINE_STS.OPEN);
            setValue(setPMsg.prchReqInfo.no(i).fromStkStsCd, STK_STS.GOOD);
            setValue(setPMsg.prchReqInfo.no(i).toStkStsCd, toStkStsCd);
            // START 2018/10/29 M.Naito [QC#28965,ADD]
            if (!hasValue(defRtrnToRtlWhCdFromSWH) && !hasValue(defRtrnToRtlSwhCdFromSWH)) {
                RTL_SWHTMsg tMsg = getRtlSwhTMsg(pMsg.glblCmpyCd.getValue(), pMsg.rtlWhCd.getValue(), rtlSwhCd);
                if (tMsg != null) {
                    defRtrnToRtlWhCdFromSWH = tMsg.defRtrnToRtlWhCd.getValue();
                    defRtrnToRtlSwhCdFromSWH = tMsg.defRtrnToRtlSwhCd.getValue();
                }
            }
            // END 2018/10/29 M.Naito [QC#28965,ADD]
            setValue(setPMsg.prchReqInfo.no(i).destInvtyLocCd, getDestInvtyLocCd(pMsg, prchReqLineTpCd, i));
        }

        NPZC103001 prUpdApi = new NPZC103001();
        prUpdApi.execute(setPMsg, this.onBatchType);
        return setPMsg;
    }
    // mod end 2016/11/18 CSA Defect#16079

    private String getPrchReqTpCd(String glblCmpyCd) {
        PRCH_REQ_TPTMsg prchReqTpTMsg = getPrchReqTpTMsg(glblCmpyCd, PRCH_REQ_TP.TECH_RETURN);
        if (prchReqTpTMsg != null) {
            return prchReqTpTMsg.prchReqApvlStsCd.getValue();
        }
        return null;
    }

    private String getDestInvtyLocCd(NPZC119001PMsg pMsg, String prchReqLineTpCd, int idx) {
        if (PRCH_REQ_LINE_TP.USED_LOCAL_RETURN.equals(prchReqLineTpCd)) {
            MDSETMsg mdseTMsg = getMdseTMsg(pMsg.glblCmpyCd.getValue(), pMsg.PartsList.no(idx).mdseCd.getValue());
            // START 2018/05/25 K.Kojima [QC#26331,MOD]
            // // START 2018/04/12 K.Kojima [QC#23639,ADD]
            // if (mdseTMsg != null && hasValue(mdseTMsg.rtrnDsplTpCd.getValue()) && hasValue(defRtrnToRtlWhCdFromSWH)) {
            //     return defRtrnToRtlWhCdFromSWH.concat(mdseTMsg.rtrnDsplTpCd.getValue());
            // }
            // // END 2018/04/12 K.Kojima [QC#23639,ADD]
            // if (mdseTMsg != null && hasValue(mdseTMsg.rtrnDsplTpCd.getValue()) && hasValue(defRtrnToRtlWhCd)) {
            //     return defRtrnToRtlWhCd.concat(mdseTMsg.rtrnDsplTpCd.getValue());
            // }
            if (mdseTMsg != null && hasValue(mdseTMsg.rtrnWhCd.getValue()) && hasValue(mdseTMsg.rtrnDsplTpCd.getValue())) {
                return mdseTMsg.rtrnWhCd.getValue().concat(mdseTMsg.rtrnDsplTpCd.getValue());
            }
            if (mdseTMsg != null && hasValue(mdseTMsg.rtrnDsplTpCd.getValue()) && hasValue(defRtrnToRtlWhCdFromSWH)) {
                return defRtrnToRtlWhCdFromSWH.concat(mdseTMsg.rtrnDsplTpCd.getValue());
            }
            if (mdseTMsg != null && hasValue(mdseTMsg.rtrnDsplTpCd.getValue()) && hasValue(defRtrnToRtlWhCd)) {
                return defRtrnToRtlWhCd.concat(mdseTMsg.rtrnDsplTpCd.getValue());
            }
            // END 2018/05/25 K.Kojima [QC#26331,MOD]
            // START 2018/04/12 K.Kojima [QC#23639,ADD]
            if (hasValue(defRtrnToRtlWhCdFromSWH) && hasValue(defRtrnToRtlSwhCdFromSWH)) {
                return defRtrnToRtlWhCdFromSWH.concat(defRtrnToRtlSwhCdFromSWH);
            }
            // END 2018/04/12 K.Kojima [QC#23639,ADD]
            if (hasValue(defRtrnToRtlWhCd) && hasValue(defRtrnToRtlSwhCd)) {
                return defRtrnToRtlWhCd.concat(defRtrnToRtlSwhCd);
            }
        } else {
            // START 2018/04/12 K.Kojima [QC#23639,ADD]
            // START 2018/10/29 M.Naito [QC#28965,MOD]
//            if (hasValue(defRtrnToRtlWhCdFromSWH) && hasValue(defRtrnToRtlSwhCdFromSWH)) {
            if (PRCH_REQ_LINE_TP.LOCAL_RETURN.equals(prchReqLineTpCd) && hasValue(defRtrnToRtlWhCdFromSWH) && hasValue(defRtrnToRtlSwhCdFromSWH)) {
            // END 2018/10/29 M.Naito [QC#28965,MOD]
                return defRtrnToRtlWhCdFromSWH.concat(defRtrnToRtlSwhCdFromSWH);
            }
            // END 2018/04/12 K.Kojima [QC#23639,ADD]
            // START 2017/10/20 M.Naito [QC#17246, MOD]
            if (hasValue(defRtrnToRtlWhCd) && hasValue(defRtrnToRtlSwhCd)) {
                // START 2018/10/29 M.Naito [QC#28965,ADD]
                if (PRCH_REQ_LINE_TP.LOCAL_RETURN.equals(prchReqLineTpCd)) {
                    return defRtrnToRtlWhCd.concat(defSrcRtlSwhCd);
                }
                // END 2018/10/29 M.Naito [QC#28965,ADD]
                return defRtrnToRtlWhCd.concat(defRtrnToRtlSwhCd);
            }
//            if (hasValue(defSrcRtlWhCd) && hasValue(defSrcRtlSwhCd)) {
//                return defSrcRtlWhCd.concat(defSrcRtlSwhCd);
//            }
            // END 2017/10/20 M.Naito [QC#17246, MOD]
        }
        return null;
    }

    // mod start 2016/11/30 CSA Defect#16079
    // mod start 2022/03/14 QC#59780
    //private void executeShippingRequest(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, List<Map<String, Object>> prchReqDtlMapList, String strTrakingNum) {
    private void executeShippingRequest(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, List<Map<String, Object>> prchReqDtlMapList, String origClickTrkNum) {
    // mod end 2022/03/14 QC#59780
        List<NLZC210001PMsg> setPMsg1 = new ArrayList<NLZC210001PMsg>();
        boolean errFlg = false;
        String soNum = null;
        String preSoNum = null;
        for (Map<String, Object> prchReqDtlMap : prchReqDtlMapList) {
            String prchReqLineNum = (String) prchReqDtlMap.get("PRCH_REQ_LINE_NUM");
            BigDecimal prchReqLineSubNum = (BigDecimal) prchReqDtlMap.get("PRCH_REQ_LINE_SUB_NUM");
            Map<String, Object> retSO = getSO(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.prchReqNum.getValue(), prchReqLineNum, prchReqLineSubNum);
            if (retSO == null) {
                continue;
            }

            soNum = (String) retSO.get("SO_NUM");
            if (hasValue(preSoNum) && !preSoNum.equals(soNum)) {
                /** Call NLZC2100 SO Confirmation from S21 DC API. */
                List<NLZC210001PMsg> retPMsgList = callSOConfAPI(pMsg, setPMsg1);

                int retPMsgListNum = retPMsgList.size();
                for (int msgCnt = 0; msgCnt < retPMsgListNum; msgCnt++) {
                    int retXxMsgIDNum = retPMsgList.get(msgCnt).xxMsgIdList.getValidCount();
                    for (int idCnt = 0; idCnt < retXxMsgIDNum; idCnt++) {
                        msgMap.addXxMsgId(retPMsgList.get(msgCnt).xxMsgIdList.no(idCnt).xxMsgId.getValue());
                        errFlg = true;
                    }
                }
                setPMsg1 = new ArrayList<NLZC210001PMsg>();
            }

            NLZC210001PMsg pMsgLine = new NLZC210001PMsg();
            setValue(pMsgLine.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(pMsgLine.whCd, (String) retSO.get("INVTY_LOC_CD"));
            setValue(pMsgLine.soNum, soNum);
            setValue(pMsgLine.sceOrdTpCd, SCE_ORD_TP.TECH_REQUEST);
            setValue(pMsgLine.soProcStsCd, SO_PROC_STS.SHIP);
            setValue(pMsgLine.shipDtTmTs, pMsg.slsDt.getValue() + ZYPDateUtil.getCurrentSystemTime(NLZC2100_TIME_FORMAT));
            setValue(pMsgLine.soSlpNum, (String) retSO.get("SO_SLP_NUM"));
            setValue(pMsgLine.soProcStsCd_DT, SO_PROC_STS.SHIP);
            setValue(pMsgLine.mdseCd, (String) retSO.get("MDSE_CD"));
            setValue(pMsgLine.fromStkStsCd, (String) retSO.get("FROM_STK_STS_CD"));
            setValue(pMsgLine.shipQty, (BigDecimal) retSO.get("RQST_ORD_QTY"));
            // mod start 2022/03/14 QC#59780
            //setValue(pMsgLine.bolNum, strTrakingNum);
            //setValue(pMsgLine.proNum, strTrakingNum);
            setValue(pMsgLine.bolNum, getTrkNumForS21(origClickTrkNum));
            setValue(pMsgLine.proNum, getTrkNumForS21(origClickTrkNum));
            // mod end 2022/03/14 QC#59780
            setValue(pMsgLine.totFrtAmt, BigDecimal.ZERO);
            setPMsg1.add(pMsgLine);

            preSoNum = soNum;
        }

        if (setPMsg1.size() == 0) {
            return;
        }

        /** Call NLZC2100 SO Confirmation from S21 DC API. */
        List<NLZC210001PMsg> retPMsgList = callSOConfAPI(pMsg, setPMsg1);

        int retPMsgListNum = retPMsgList.size();
        for (int msgCnt = 0; msgCnt < retPMsgListNum; msgCnt++) {
            int retXxMsgIDNum = retPMsgList.get(msgCnt).xxMsgIdList.getValidCount();
            for (int idCnt = 0; idCnt < retXxMsgIDNum; idCnt++) {
                msgMap.addXxMsgId(retPMsgList.get(msgCnt).xxMsgIdList.no(idCnt).xxMsgId.getValue());
                errFlg = true;
            }
        }

        // insert CLICK_PRT_RTRN_RQST
        for (Map<String, Object> prchReqDtlMap : prchReqDtlMapList) {
            // mod start 2022/03/14 QC#59780
            //insertClickTechRcvPrtForShip(msgMap, pMsg, this.prtRtrnReqStsMap.get(CONST_STS_SHIPPED), null, prchReqDtlMap, errFlg);
            insertClickTechRcvPrtForShip(msgMap, pMsg, this.prtRtrnReqStsMap.get(CONST_STS_SHIPPED), null, prchReqDtlMap, errFlg, origClickTrkNum);
            // mod end 2022/03/14 QC#59780
        }

        // add start 2017/02/21 CSA Defect#16123
        // Call Send My Inventory to Click API
        // START 2022/04/20 K.Kitachi [QC#59897, DEL]
//        if (!msgMap.getMsgMgr().isXxMsgId() && !errFlg) {
//            callMyInvtyAPI(pMsg, prchReqDtlMapList);
//        }
        // END 2022/04/20 K.Kitachi [QC#59897, DEL]
        // add end 2017/02/21 CSA Defect#16123
    }
    // mod end 2016/11/30 CSA Defect#16079

    private List<NLZC210001PMsg> callSOConfAPI(NPZC119001PMsg pMsg, List<NLZC210001PMsg> setPMsg1) {
        List<NLZC210002PMsg> setPMsg2 = new ArrayList<NLZC210002PMsg>();
        NLZC210001 soConfApi = new NLZC210001();
        soConfApi.execute(setPMsg1, setPMsg2, this.onBatchType);
        return setPMsg1;
    }

    // add start 2017/02/21 CSA Defect#16123
    // START 2022/04/20 K.Kitachi [QC#59897, DEL]
//    private void callMyInvtyAPI(NPZC119001PMsg pMsg, List<Map<String, Object>> prchReqDtlMapList) {
//        Map<String, List<String>> prchReqMap = new HashMap<String, List<String>>();
//
//        for (Map<String, Object> prchReqDtlMap : prchReqDtlMapList) {
//            String prchReqLineNum = (String) prchReqDtlMap.get("PRCH_REQ_LINE_NUM");
//            BigDecimal prchReqLineSubNum = (BigDecimal) prchReqDtlMap.get("PRCH_REQ_LINE_SUB_NUM");
//            Map<String, Object> retSO = getSO(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.prchReqNum.getValue(), prchReqLineNum, prchReqLineSubNum);
//            if (retSO != null) {
//                String invtyLocCd = (String) retSO.get("INVTY_LOC_CD");
//                String mdseCd = (String) retSO.get("MDSE_CD");
//                if (prchReqMap.containsKey(invtyLocCd)) {
//                    List<String> mdseCdList = prchReqMap.get(invtyLocCd);
//                    if (!mdseCdList.contains(mdseCd)) {
//                        mdseCdList.add(mdseCd);
//                        prchReqMap.put(invtyLocCd, mdseCdList);
//                    }
//                } else {
//                    List<String> mdseCdList = new ArrayList<String>();
//                    mdseCdList.add(mdseCd);
//                    prchReqMap.put(invtyLocCd, mdseCdList);
//                }
//            }
//        }
//
//        for (Map.Entry<String, List<String>> prchReq : prchReqMap.entrySet()) {
//            String invtyLocCd = prchReq.getKey();
//            List<String> mdseCdList = prchReq.getValue();
//            for (String mdseCd : mdseCdList) {
//                NLZC410001PMsg apiPMsg = new NLZC410001PMsg();
//                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//                setValue(apiPMsg.procDt, pMsg.slsDt);
//                setValue(apiPMsg.xxModeCd, NLZC410001Constant.MODE_DAILIY);
//                setValue(apiPMsg.mdseCd, mdseCd);
//                setValue(apiPMsg.invtyLocCd, invtyLocCd);
//                NLZC410001 api = new NLZC410001();
//                api.execute(apiPMsg, this.onBatchType);
//                if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
//                    EZDConnectionMgr.getInstance().rollback();
//                    return;
//                }
//            }
//        }
//        EZDConnectionMgr.getInstance().commit();
//    }
    // END 2022/04/20 K.Kitachi [QC#59897, DEL]
    // add end 2017/02/21 CSA Defect#16123

    private DS_COND_CONSTTMsg getDsCondConstTMsg(String gcc, String grpId, String constCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstGrpId, grpId);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstCd, constCd);
        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(dsCondConstTMsg);
    }

    private MDSETMsg getMdseTMsg(String gcc, String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
    }

    private PRCH_REQ_TPTMsg getPrchReqTpTMsg(String gcc, String prTpCd) {
        PRCH_REQ_TPTMsg prchReqTpTMsg = new PRCH_REQ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prchReqTpTMsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(prchReqTpTMsg.prchReqTpCd, prTpCd);
        return (PRCH_REQ_TPTMsg) S21ApiTBLAccessor.findByKey(prchReqTpTMsg);
    }

    private List<Map<String, Object>> getRtlWh(String gcc, String slsDt, String rtlWhCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("slsDt", slsDt);
        param.put("rtlWhCd", rtlWhCd);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRtlWh", param);
    }

    // mod start 2016/11/30 CSA Defect#16079
    private Map<String, Object> getSO(String gcc, String slsDt, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("slsDt", slsDt);
        param.put("prchReqNum", prchReqNum);
        param.put("prchReqLineNum", prchReqLineNum);
        param.put("prchReqLineSubNum", prchReqLineSubNum);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSO", param);
    }
    // mod end 2016/11/30 CSA Defect#16079

    // mod start 2018/03/09 CSA Defect#21913
    private List<String> getSoList(String gcc, String prchReqNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("prchReqNum", prchReqNum);
        String[] prchReqLineTp_Return = new String[] {PRCH_REQ_LINE_TP.LOCAL_RETURN, PRCH_REQ_LINE_TP.DEFECTIVE_RETURN, PRCH_REQ_LINE_TP.USED_LOCAL_RETURN };
        param.put("prchReqLineTpList", prchReqLineTp_Return);
        return (List<String>) this.ssmBatchClient.queryObjectList("getSoList", param);
    }
    // mod end 2018/03/09 CSA Defect#21913

    private Map<String, Object> getPrchReqLineNum(String glblCmpyCd, String prchReqNum, String mdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("prchReqNum", prchReqNum);
        param.put("mdseCd", mdseCd);
        param.put("stsCancelled", PRCH_REQ_LINE_STS.CANCELLED);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getPrchReqLineNum", param);
    }

    // START 2016/11/10 K.Kojima [QC#15490,ADD]
    // mod start 2016/11/22 CSA Defect#16079
    /**
     * getClickPrtRtrnRqstList
     * @param glblCmpyCd String
     * @param prchReqNum String
     */
    private List<Map<String, Object>> getClickPrtRtrnRqstList(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("prchReqNum", prchReqNum);
        param.put("prchReqLineNum", prchReqLineNum);
        param.put("prchReqLineSubNum", prchReqLineSubNum);
        param.put("prchReqStsTxt", PRCH_REQ_STS_AWAITING_SHIPMENT);
        // QC#58518 Mod Start
//        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getClickPrtRtrnRqstList", param);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getClickPrtRtrnRqstListForAwatingShipment", param);
        // QC#58518 Mod End
    }
    // mod end 2016/11/22 CSA Defect#16079
    // END 2016/11/10 K.Kojima [QC#15490,ADD]

    // add start 2016/12/05 CSA Defect#16079
    private List<Map<String, Object>> getClickPrtRtrnRqstListForShip(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("prchReqNum", prchReqNum);
        param.put("prchReqLineNum", prchReqLineNum);
        param.put("prchReqLineSubNum", prchReqLineSubNum);
        param.put("prchReqStsTxt", PRCH_REQ_STS_SHIPPED);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getClickPrtRtrnRqstList", param);
    }

    private PRCH_REQTMsg getPrchReqTMsg(String glblCmpyCd, String prchReqNum) {
        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.prchReqNum, prchReqNum);
        return (PRCH_REQTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private PRCH_REQ_DTLTMsg getPrchReqDtlTMsg(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        PRCH_REQ_DTLTMsg inMsg = new PRCH_REQ_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.prchReqNum, prchReqNum);
        setValue(inMsg.prchReqLineNum, prchReqLineNum);
        setValue(inMsg.prchReqLineSubNum, prchReqLineSubNum);
        return (PRCH_REQ_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // add end 2016/12/05 CSA Defect#16079

    // pivate Map<String, Object> getPRNum(String gcc, String soNum) {
    // Map<String, Object> param = new HashMap<String, Object>();
    // param.put("glblCmpyCd", gcc);
    // param.put("soNum", soNum);
    // return (Map<String, Object>)
    // this.ssmBatchClient.queryObjectList("getPRNum", param);
    // }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }
    // mod start 2022/03/14 QC#59780
    //private boolean insertClickTechRcvPrt(NPZC119001PMsg pmsg, String prchReqStsTxt, NPZC103001PMsg retPrUpdApiPMsg, Map<String, Object> prchReqDtlMap) {
    private boolean insertClickTechRcvPrt(NPZC119001PMsg pmsg, String prchReqStsTxt, NPZC103001PMsg retPrUpdApiPMsg, Map<String, Object> prchReqDtlMap, String origClickTrkNum) {
    // mod end 2022/03/14 QC#59780
        // START 2016/12/01 K.Kojima [QC#16033,MOD]
        // BigDecimal prtRtrnRqstSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRT_RTRN_RQST_SQ);
        BigDecimal prtRtrnRqstSq = getPrtRtrnRqstSq();
        // END 2016/12/01 K.Kojima [QC#16033,MOD]
        // mod start 2016/11/18 CSA Defect#16079
        if (this.processMode.equals(CONST_STS_AWAITINGSHIPMENT)) {
            for (int i = 0; i < pmsg.PartsList.getValidCount(); i++) {
                CLICK_PRT_RTRN_RQSTTMsg clickPrtRtrnRqstTMsg = new CLICK_PRT_RTRN_RQSTTMsg();
                BigDecimal clickPrtRtrnRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLICK_PRT_RTRN_RQST_SQ);
                setValue(clickPrtRtrnRqstTMsg.glblCmpyCd, pmsg.glblCmpyCd);
                setValue(clickPrtRtrnRqstTMsg.clickPrtRtrnRqstPk, clickPrtRtrnRqstPk);
                setValue(clickPrtRtrnRqstTMsg.prtRtrnRqstSq, prtRtrnRqstSq);
                setValue(clickPrtRtrnRqstTMsg.prchReqNum, pmsg.prchReqNum);
                setValue(clickPrtRtrnRqstTMsg.prchReqRqstStsTxt , pmsg.prchReqStsNm);
                setValue(clickPrtRtrnRqstTMsg.prchReqStsTxt, prchReqStsTxt);
                setValue(clickPrtRtrnRqstTMsg.rqstTechTocCd, pmsg.techTocCd);
                setValue(clickPrtRtrnRqstTMsg.srcRtlWhCd, pmsg.rtlWhCd);
                setValue(clickPrtRtrnRqstTMsg.srcRtlSwhCd, pmsg.rtlSwhCd);
                if (hasValue(pmsg.rtlWhCd) && hasValue(pmsg.rtlSwhCd)) {
                    setValue(clickPrtRtrnRqstTMsg.invtyLocCd, pmsg.rtlWhCd.getValue().concat(pmsg.rtlSwhCd.getValue()));
                }
                setValue(clickPrtRtrnRqstTMsg.prchReqInvtyTpTxt, pmsg.PartsList.no(i).prchReqSrcTpNm);
                setValue(clickPrtRtrnRqstTMsg.mdseCd, pmsg.PartsList.no(i).mdseCd);
                setValue(clickPrtRtrnRqstTMsg.prchReqQty, pmsg.PartsList.no(i).prchReqQty);
                setValue(clickPrtRtrnRqstTMsg.prchReqLineCmntTxt, pmsg.PartsList.no(i).prchReqLineCmntTxt);
                setValue(clickPrtRtrnRqstTMsg.procStsCd, PROC_STS.IN_COMPLETED);
                setValue(clickPrtRtrnRqstTMsg.clickKeyTxt, pmsg.clickKeyTxt);
                // add start 2016/11/02 CSA Defect#15490
                setValue(clickPrtRtrnRqstTMsg.clickKeyDtlTxt, pmsg.PartsList.no(i).clickKeyDtlTxt);
                // add end 2016/11/02 CSA Defect#15490
                // add start 2016/11/08 CSA Defect#15490
                if (retPrUpdApiPMsg != null) {
                    NPZC103001_prchReqInfoPMsg prReqInfo = retPrUpdApiPMsg.prchReqInfo.no(i);
                    setValue(clickPrtRtrnRqstTMsg.prchReqLineNum, prReqInfo.prchReqLineNum);
                    setValue(clickPrtRtrnRqstTMsg.prchReqLineSubNum, prReqInfo.prchReqLineSubNum);
                }
                // add end 2016/11/08 CSA Defect#15490

                S21FastTBLAccessor.insert(clickPrtRtrnRqstTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickPrtRtrnRqstTMsg.getReturnCode())) {
                    S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);
                    msgMap.addXxMsgId(NPZM0207E);
                    msgMap.flush();
                    return false;
                }
            }
            // START 2017/02/14 K.Kojima [QC#16301,ADD]
            // This API is called directly from Clicksoft that will not control any transactions in S21.
            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
            EZDConnectionMgr.getInstance().commit();
            // END 2017/02/14 K.Kojima [QC#16301,ADD]
        }

        if (this.processMode.equals(CONST_STS_SHIPMENTSHORT)) {
            // mod start 2022/03/14 QC#59780
            //if (!insertClickTechRcvPrtForShortShip(pmsg, prchReqStsTxt, prtRtrnRqstSq, retPrUpdApiPMsg)) {
            if (!insertClickTechRcvPrtForShortShip(pmsg, prchReqStsTxt, prtRtrnRqstSq, retPrUpdApiPMsg, origClickTrkNum)) {
            // mod end 2022/03/14 QC#59780
                return false;
            }
        }

        if (this.processMode.equals(CONST_STS_SHIPPED)) {
            // START 2016/11/10 K.Kojima [QC#15490,DEL]
            // for (int i = 0; i < pmsg.ShipInfoList.getValidCount(); i++) {
            //     CLICK_PRT_RTRN_RQSTTMsg clickPrtRtrnRqstTMsg = new CLICK_PRT_RTRN_RQSTTMsg();
            //     BigDecimal clickPrtRtrnRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLICK_PRT_RTRN_RQST_SQ);
            //     setValue(clickPrtRtrnRqstTMsg.glblCmpyCd, pmsg.glblCmpyCd);
            //     setValue(clickPrtRtrnRqstTMsg.clickPrtRtrnRqstPk, clickPrtRtrnRqstPk);
            //     setValue(clickPrtRtrnRqstTMsg.prtRtrnRqstSq, prtRtrnRqstSq);
            //     setValue(clickPrtRtrnRqstTMsg.prchReqNum, pmsg.prchReqNum);
            //     setValue(clickPrtRtrnRqstTMsg.prchReqRqstStsTxt , pmsg.prchReqStsNm);
            //     setValue(clickPrtRtrnRqstTMsg.prchReqStsTxt, prchReqStsTxt);
            //     setValue(clickPrtRtrnRqstTMsg.rqstTechTocCd, pmsg.techTocCd);
            //     setValue(clickPrtRtrnRqstTMsg.srcRtlWhCd, pmsg.rtlWhCd);
            //     setValue(clickPrtRtrnRqstTMsg.srcRtlSwhCd, pmsg.rtlSwhCd);
            //     if (hasValue(pmsg.rtlWhCd) && hasValue(pmsg.rtlSwhCd)) {
            //         setValue(clickPrtRtrnRqstTMsg.invtyLocCd, pmsg.rtlWhCd.getValue().concat(pmsg.rtlSwhCd.getValue()));
            //     }
            //     setValue(clickPrtRtrnRqstTMsg.prchReqInvtyTpTxt, pmsg.PartsList.no(i).prchReqSrcTpNm);
            //     setValue(clickPrtRtrnRqstTMsg.clickTrkNum, pmsg.ShipInfoList.no(i).proNum);
            //     setValue(clickPrtRtrnRqstTMsg.procStsCd, PROC_STS.IN_COMPLETED);
            //     setValue(clickPrtRtrnRqstTMsg.clickKeyTxt, pmsg.clickKeyTxt);
            //     // add start 2016/11/02 CSA Defect#15490
            //     setValue(clickPrtRtrnRqstTMsg.clickKeyDtlTxt, pmsg.PartsList.no(i).clickKeyDtlTxt);
            //     // add end 2016/11/02 CSA Defect#15490
            // 
            //     S21FastTBLAccessor.insert(clickPrtRtrnRqstTMsg);
            //     if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickPrtRtrnRqstTMsg.getReturnCode())) {
            //         S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);
            //         msgMap.addXxMsgId(NPZM0207E);
            //         msgMap.flush();
            //         return false;
            //     }
            // }
            // END 2016/11/10 K.Kojima [QC#15490,DEL]
            // START 2016/11/10 K.Kojima [QC#15136,ADD]
            String prchReqLineNum = (String) prchReqDtlMap.get("PRCH_REQ_LINE_NUM");
            BigDecimal prchReqLineSubNum = (BigDecimal) prchReqDtlMap.get("PRCH_REQ_LINE_SUB_NUM");
            List<Map<String, Object>> rqstList = getClickPrtRtrnRqstList(pmsg.glblCmpyCd.getValue(), pmsg.prchReqNum.getValue(), prchReqLineNum, prchReqLineSubNum);
            for (Map<String, Object> rqst : rqstList) {
                CLICK_PRT_RTRN_RQSTTMsg clickPrtRtrnRqstTMsg = new CLICK_PRT_RTRN_RQSTTMsg();
                BigDecimal clickPrtRtrnRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLICK_PRT_RTRN_RQST_SQ);
                setValue(clickPrtRtrnRqstTMsg.glblCmpyCd, pmsg.glblCmpyCd);
                setValue(clickPrtRtrnRqstTMsg.clickPrtRtrnRqstPk, clickPrtRtrnRqstPk);
                setValue(clickPrtRtrnRqstTMsg.prtRtrnRqstSq, prtRtrnRqstSq);
                setValue(clickPrtRtrnRqstTMsg.prchReqNum, pmsg.prchReqNum);
                setValue(clickPrtRtrnRqstTMsg.prchReqRqstStsTxt, pmsg.prchReqStsNm);
                setValue(clickPrtRtrnRqstTMsg.prchReqStsTxt, prchReqStsTxt);
                setValue(clickPrtRtrnRqstTMsg.rqstTechTocCd, (String) rqst.get(RQST_TECH_TOC_CD));
                setValue(clickPrtRtrnRqstTMsg.srcRtlWhCd, (String) rqst.get(SRC_RTL_WH_CD));
                setValue(clickPrtRtrnRqstTMsg.srcRtlSwhCd, (String) rqst.get(SRC_RTL_SWH_CD));
                setValue(clickPrtRtrnRqstTMsg.invtyLocCd, (String) rqst.get(INVTY_LOC_CD));
                setValue(clickPrtRtrnRqstTMsg.prchReqInvtyTpTxt, (String) rqst.get(PRCH_REQ_INVTY_TP_TXT));
                setValue(clickPrtRtrnRqstTMsg.mdseCd, (String) rqst.get(MDSE_CD));
                setValue(clickPrtRtrnRqstTMsg.prchReqQty, (BigDecimal) rqst.get(PRCH_REQ_QTY));
                setValue(clickPrtRtrnRqstTMsg.prchReqShortQty, (BigDecimal) rqst.get(PRCH_REQ_SHORT_QTY));
                setValue(clickPrtRtrnRqstTMsg.prchReqLineCmntTxt, (String) rqst.get(PRCH_REQ_LINE_CMNT_TXT));
                // mod start 2022/03/14 QC#59780
                //setValue(clickPrtRtrnRqstTMsg.clickTrkNum, pmsg.ShipInfoList.no(0).proNum);
                setValue(clickPrtRtrnRqstTMsg.clickTrkNum, getTrkNumForS21(origClickTrkNum));
                // mod end 2022/03/14 QC#59780
                setValue(clickPrtRtrnRqstTMsg.procStsCd, PROC_STS.IN_COMPLETED);
                setValue(clickPrtRtrnRqstTMsg.prtRtrnRqstSendTs, ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSSSS));
                setValue(clickPrtRtrnRqstTMsg.clickKeyTxt, pmsg.clickKeyTxt);
                setValue(clickPrtRtrnRqstTMsg.clickKeyDtlTxt, (String) rqst.get(CLICK_KEY_DTL_TXT));
                setValue(clickPrtRtrnRqstTMsg.prchReqLineNum, (String) rqst.get(PRCH_REQ_LINE_NUM));
                setValue(clickPrtRtrnRqstTMsg.prchReqLineSubNum, (BigDecimal) rqst.get(PRCH_REQ_LINE_SUB_NUM));
                // add start 2022/03/14 QC#59780
                setValue(clickPrtRtrnRqstTMsg.origClickTrkNum, origClickTrkNum);
                setValue(clickPrtRtrnRqstTMsg.clickToWmsTrkNum, getTrkNumForWms(origClickTrkNum));
                // add end 2022/03/14 QC#59780

                S21FastTBLAccessor.insert(clickPrtRtrnRqstTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickPrtRtrnRqstTMsg.getReturnCode())) {
                    S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);
                    msgMap.addXxMsgId(NPZM0207E);
                    msgMap.flush();
                    return false;
                }
            }
            // START 2017/02/14 K.Kojima [QC#16301,ADD]
            // This API is called directly from Clicksoft that will not control any transactions in S21.
            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
            EZDConnectionMgr.getInstance().commit();
            // END 2017/02/14 K.Kojima [QC#16301,ADD]
            // END 2016/11/10 K.Kojima [QC#15490,ADD]
        }

        // mod end 2016/11/18 CSA Defect#16079
        return true;
    }

    // add start 2016/11/18 CSA Defect#16079
    // mod start 2016/11/22 CSA Defect#16079
    // mod start 2017/01/04 CSA Defect#16638
    private List<Map<String, Object>> getParentPrchReqDtl(NPZC119001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("prchReqNum", pMsg.prchReqNum.getValue());
        param.put("prchReqLineTpCd", PRCH_REQ_LINE_TP.MISSING_PARTS);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getParentPrchReqDtl", param);
    }

    private Map<String, Object> getChildPrchReqDtl(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("prchReqNum", prchReqNum);
        param.put("prchReqLineNum", prchReqLineNum);
        param.put("prchReqLineTpCd", PRCH_REQ_LINE_TP.MISSING_PARTS);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getChildPrchReqDtl", param);
    }
    // mod end 2017/01/04 CSA Defect#16638

    // del start 2017/01/04 CSA Defect#16638
//    private boolean isShipment(Map<String, Object> prchReqDtlMap) {
//        String prchReqLineStsCd = (String) prchReqDtlMap.get("PRCH_REQ_LINE_STS_CD");
//        if (hasValue(prchReqLineStsCd)) {
//            // mod start 2016/12/05 CSA Defect#16079
//            if (PRCH_REQ_LINE_STS.SHIPPED.equals(prchReqLineStsCd) || PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED.equals(prchReqLineStsCd) || PRCH_REQ_LINE_STS.RECEIVED.equals(prchReqLineStsCd)) {
//                // Short Ship Mode
//                return false;
//            }
//            // mod end 2016/12/05 CSA Defect#16079
//        }
//
//        BigDecimal shipQty = (BigDecimal) prchReqDtlMap.get("SHIP_QTY");
//        if (hasValue(shipQty) && BigDecimal.ZERO.compareTo(shipQty) < 0) {
//            // Short Ship Mode
//            return false;
//        }
//
//        String prchReqTpCd = (String) prchReqDtlMap.get("PRCH_REQ_LINE_TP_CD");
//        if (hasValue(prchReqTpCd) && PRCH_REQ_LINE_TP.MISSING_PARTS.equals(prchReqTpCd)) {
//            // Short Ship Mode
//            return false;
//        }
//        return true;
//    }
    // del end 2017/01/04 CSA Defect#16638

    // START 2016/11/25 T.Tomita [QC#16165, MOD]
    private NPZC103001PMsg callPRUpdateAPIForShortShip(S21ApiMessageMap msgMap, NPZC119001PMsg pMsg, Map<String, Object> prchReqDtlMap, String strProNum) {
        NPZC103001PMsg setPMsg = new NPZC103001PMsg();

        // Header
        setValue(setPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(setPMsg.procDt, pMsg.slsDt);
        setValue(setPMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        setValue(setPMsg.eventId, NPZC103001Constant.EVENT_INSOURCING);
        setValue(setPMsg.prchReqNum, pMsg.prchReqNum);
        setValue(setPMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.TECH_RETURN);
        setValue(setPMsg.prchReqTpCd, PRCH_REQ_TP.TECH_RETURN);

        //Detail
        setValue(setPMsg.prchReqInfo.no(0).origPrchReqLineNum, (String) prchReqDtlMap.get("PRCH_REQ_LINE_NUM"));
        setValue(setPMsg.prchReqInfo.no(0).origPrchReqLineSubNum, (BigDecimal) prchReqDtlMap.get("PRCH_REQ_LINE_SUB_NUM"));
        setValue(setPMsg.prchReqInfo.no(0).prchReqLineTpCd, PRCH_REQ_LINE_TP.MISSING_PARTS);
        setValue(setPMsg.prchReqInfo.no(0).procrTpCd, PROCR_TP.WAREHOUSE);
        setValue(setPMsg.prchReqInfo.no(0).srcInvtyLocCd, (String) prchReqDtlMap.get("SRC_INVTY_LOC_CD"));
        setValue(setPMsg.prchReqInfo.no(0).mdseCd, (String) prchReqDtlMap.get("MDSE_CD"));
        setValue(setPMsg.prchReqInfo.no(0).prchReqQty, (BigDecimal) prchReqDtlMap.get("BACK_TO_TECH_QTY"));
        setValue(setPMsg.prchReqInfo.no(0).proNum, strProNum);
        setValue(setPMsg.prchReqInfo.no(0).prchReqLineCmntTxt, (String) prchReqDtlMap.get("PRCH_REQ_LINE_CMNT_TXT"));
        setValue(setPMsg.prchReqInfo.no(0).prchReqLineNum, (String) prchReqDtlMap.get("PRCH_REQ_LINE_NUM"));
        setPMsg.prchReqInfo.setValidCount(1);

        NPZC103001 prUpdApi = new NPZC103001();
        prUpdApi.execute(setPMsg, this.onBatchType);
        return setPMsg;
    }
    // END 2016/11/25 T.Tomita [QC#16165, MOD]
    // mod end 2016/11/22 CSA Defect#16079

    // del end 2022/03/14 QC#59780
//    private String getStrProNum(NPZC119001PMsg pMsg) {
//        String strProNum = null;
//        if (pMsg.ShipInfoList.getValidCount() == 0) {
//            return strProNum;
//        }
//        strProNum = pMsg.ShipInfoList.no(0).proNum.getValue();
//
//        List<String> strProNumList = new ArrayList<String>();
//        for (int i = 0; i < pMsg.ShipInfoList.getValidCount(); i++) {
//            if (hasValue(pMsg.ShipInfoList.no(i).proNum)) {
//                strProNumList.add(pMsg.ShipInfoList.no(i).proNum.getValue());
//            }
//        }
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("prchReqNum", pMsg.prchReqNum.getValue());
//        param.put("strProNumList", strProNumList);
//        List<String> existsProNumList = (List<String>) this.ssmBatchClient.queryObjectList("getStrProNum", param);
//
//        boolean existsFlg;
//        for (String tergetNum : strProNumList) {
//            existsFlg = false;
//            for (String existsNum : existsProNumList) {
//                if (tergetNum.equals(existsNum)) {
//                    existsFlg = true;
//                    break;
//                }
//            }
//
//            if (!existsFlg) {
//                strProNum = tergetNum;
//                break;
//            }
//        }
//        return strProNum;
//    }
    // del end 2022/03/14 QC#59780

    // add start 2022/03/14 QC#59780
    private String getTargetTrkNum(NPZC119001PMsg pMsg) {
        String origClickTrkNum = null;
        if (pMsg.ShipInfoList.getValidCount() == 0) {
            return origClickTrkNum;
        }
        origClickTrkNum = pMsg.ShipInfoList.no(0).origClickTrkNum.getValue();

        for (int i = 0; i < pMsg.ShipInfoList.getValidCount(); i++) {
            if (hasValue(pMsg.ShipInfoList.no(i).origClickTrkNum)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                param.put("prchReqNum", pMsg.prchReqNum.getValue());
                param.put("strProNum", getTrkNumForS21(pMsg.ShipInfoList.no(i).origClickTrkNum.getValue()));
                String proNum = (String) this.ssmBatchClient.queryObject("getStrProNum", param);
                if (!hasValue(proNum)) {
                    origClickTrkNum = pMsg.ShipInfoList.no(i).origClickTrkNum.getValue();
                    break;
                }
            }
        }
        return origClickTrkNum;
    }
    // add end 2022/03/14 QC#59780

    // mod start 2022/03/14 QC#59780
    //private boolean insertClickTechRcvPrtForShortShip(NPZC119001PMsg pmsg, String prchReqStsTxt, BigDecimal prtRtrnRqstSq, NPZC103001PMsg retPrUpdApiPMsg) {
    private boolean insertClickTechRcvPrtForShortShip(NPZC119001PMsg pmsg, String prchReqStsTxt, BigDecimal prtRtrnRqstSq, NPZC103001PMsg retPrUpdApiPMsg, String origClickTrkNum) {
    // mod end 2022/03/14 QC#59780
        if (retPrUpdApiPMsg == null) {
            return true;
        }

        // mod start 2016/12/05 CSA Defect#16079
        CLICK_PRT_RTRN_RQSTTMsg clickPrtRtrnRqstTMsg;
        List<Map<String, Object>> clickPrtRtrnRqstList;
        PRCH_REQTMsg prchReqTMsg = getPrchReqTMsg(pmsg.glblCmpyCd.getValue(), pmsg.prchReqNum.getValue());
        PRCH_REQ_DTLTMsg prchReqDtlTMsg;
        for (int i = 0; i < retPrUpdApiPMsg.prchReqInfo.getValidCount(); i++) {
            clickPrtRtrnRqstTMsg = new CLICK_PRT_RTRN_RQSTTMsg();
            BigDecimal clickPrtRtrnRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLICK_PRT_RTRN_RQST_SQ);
            prchReqDtlTMsg = getPrchReqDtlTMsg(pmsg.glblCmpyCd.getValue(), pmsg.prchReqNum.getValue(), retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineNum.getValue(), retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineSubNum.getValue());
            setValue(clickPrtRtrnRqstTMsg.glblCmpyCd, pmsg.glblCmpyCd);
            setValue(clickPrtRtrnRqstTMsg.clickPrtRtrnRqstPk, clickPrtRtrnRqstPk);
            setValue(clickPrtRtrnRqstTMsg.prtRtrnRqstSq, prtRtrnRqstSq);
            setValue(clickPrtRtrnRqstTMsg.prchReqNum, pmsg.prchReqNum);
            setValue(clickPrtRtrnRqstTMsg.prchReqRqstStsTxt , pmsg.prchReqStsNm);
            setValue(clickPrtRtrnRqstTMsg.prchReqStsTxt, prchReqStsTxt);
//            setValue(clickPrtRtrnRqstTMsg.rqstTechTocCd, pmsg.techTocCd);
            setValue(clickPrtRtrnRqstTMsg.rqstTechTocCd, prchReqTMsg.rqstTechTocCd);
//            setValue(clickPrtRtrnRqstTMsg.srcRtlWhCd, pmsg.rtlWhCd);
            setValue(clickPrtRtrnRqstTMsg.srcRtlWhCd, prchReqDtlTMsg.srcRtlWhCd);
//            setValue(clickPrtRtrnRqstTMsg.srcRtlSwhCd, pmsg.rtlSwhCd);
            setValue(clickPrtRtrnRqstTMsg.invtyLocCd, retPrUpdApiPMsg.prchReqInfo.no(i).srcInvtyLocCd);
            setValue(clickPrtRtrnRqstTMsg.mdseCd, retPrUpdApiPMsg.prchReqInfo.no(i).mdseCd);
            setValue(clickPrtRtrnRqstTMsg.prchReqQty, retPrUpdApiPMsg.prchReqInfo.no(i).prchReqQty);
            setValue(clickPrtRtrnRqstTMsg.prchReqLineCmntTxt, retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineCmntTxt);
            setValue(clickPrtRtrnRqstTMsg.clickTrkNum, prchReqDtlTMsg.proNum);
            setValue(clickPrtRtrnRqstTMsg.procStsCd, PROC_STS.IN_COMPLETED);
            setValue(clickPrtRtrnRqstTMsg.clickKeyTxt, pmsg.clickKeyTxt);
            setValue(clickPrtRtrnRqstTMsg.prchReqLineNum, retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineNum);
            setValue(clickPrtRtrnRqstTMsg.prchReqLineSubNum, retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineSubNum);
            if (hasValue(retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineSubNum)) {
                clickPrtRtrnRqstList = getClickPrtRtrnRqstListForShip(pmsg.glblCmpyCd.getValue(), pmsg.prchReqNum.getValue(), retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineNum.getValue(), retPrUpdApiPMsg.prchReqInfo.no(i).prchReqLineSubNum.getValue().subtract(BigDecimal.ONE));
                if (clickPrtRtrnRqstList.size() > 0) {
                    setValue(clickPrtRtrnRqstTMsg.prchReqInvtyTpTxt, (String) clickPrtRtrnRqstList.get(0).get("PRCH_REQ_INVTY_TP_TXT"));
                    setValue(clickPrtRtrnRqstTMsg.clickKeyDtlTxt, (String) clickPrtRtrnRqstList.get(0).get("CLICK_KEY_DTL_TXT"));
                }
            }
            // add start 2022/03/14 QC#59780
            setValue(clickPrtRtrnRqstTMsg.origClickTrkNum, origClickTrkNum);
            setValue(clickPrtRtrnRqstTMsg.clickToWmsTrkNum, getTrkNumForWms(origClickTrkNum));
            // add end 2022/03/14 QC#59780

            S21FastTBLAccessor.insert(clickPrtRtrnRqstTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickPrtRtrnRqstTMsg.getReturnCode())) {
                S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);
                msgMap.addXxMsgId(NPZM0207E);
                msgMap.flush();
                return false;
            }
        }
        // START 2017/02/14 K.Kojima [QC#16301,ADD]
        // This API is called directly from Clicksoft that will not control any transactions in S21.
        // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
        EZDConnectionMgr.getInstance().commit();
        // END 2017/02/14 K.Kojima [QC#16301,ADD]
        // mod end 2016/12/05 CSA Defect#16079
        return true;
    }
    // add end 2016/11/18 CSA Defect#16079

    // START 2016/11/25 T.Tomita [QC#16165, ADD]
    // QC#21913 Modify.  RTRN_PRO_NUM_LIST => SHPG_ORD_PRO_NUM_LIST
    // mod start 2022/03/14 QC#59780
    //private boolean insertRtrnProNumListTMsg(NPZC119001PMsg pMsg, String strProNum) {
    private boolean insertShpgOrdProNumList(NPZC119001PMsg pMsg, String origClickTrkNum) {
    // mod end 2022/03/14 QC#59780

        List<String> soList = getSoList(pMsg.glblCmpyCd.getValue(), pMsg.prchReqNum.getValue());

        for (String soNum : soList) {
            SHPG_ORD_PRO_NUM_LISTTMsg shpgProNumListTMsg = new SHPG_ORD_PRO_NUM_LISTTMsg();
            setValue(shpgProNumListTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(shpgProNumListTMsg.shpgOrdProNumListPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_PRO_NUM_LIST_SQ));
            setValue(shpgProNumListTMsg.trxHdrNum, soNum);
            // mod start 2022/03/14 QC#59780
            //setValue(shpgProNumListTMsg.proNum, strProNum);
            setValue(shpgProNumListTMsg.proNum, getTrkNumForS21(origClickTrkNum));
            // mod end 2022/03/14 QC#59780
            String curSysTm = ZYPDateUtil.getCurrentSystemTime(HHMMSS);
            setValue(shpgProNumListTMsg.proCratDtTmTs, pMsg.slsDt.getValue() + curSysTm);
            setValue(shpgProNumListTMsg.proSendFlg, ZYPConstant.FLG_OFF_N);
            // add start 2022/03/14 QC#59780
            setValue(shpgProNumListTMsg.clickToWmsTrkNum, getTrkNumForWms(origClickTrkNum));
            if (hasValue(this.prchReqLineSubNum)) {
                setValue(shpgProNumListTMsg.prchReqNum, pMsg.prchReqNum.getValue());
                setValue(shpgProNumListTMsg.prchReqLineSubNum, this.prchReqLineSubNum);
            }
            // add end 2022/03/14 QC#59780

            S21FastTBLAccessor.insert(shpgProNumListTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(shpgProNumListTMsg.getReturnCode())) {
                S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
                msgMap.addXxMsgId(NPZM0302E);
                msgMap.flush();
                return false;
            }
        }

        return true;
    }
    // END 2016/11/25 T.Tomita [QC#16165, ADD]

    // START 2016/12/01 K.Kojima [QC#16033,ADD]
    /**
     * getPrtRtrnRqstSq
     * @return BigDecimal prtRtrnRqstSq
     */
    private BigDecimal getPrtRtrnRqstSq() {
        if (this.prtRtrnRqstSq == null) {
            this.prtRtrnRqstSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRT_RTRN_RQST_SQ);
        }
        return this.prtRtrnRqstSq;
    }
    // END 2016/12/01 K.Kojima [QC#16033,ADD]

    // START 2018/04/12 K.Kojima [QC#23639,ADD]
    private RTL_SWHTMsg getRtlSwhTMsg(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {
        RTL_SWHTMsg inMsg = new RTL_SWHTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.rtlWhCd, rtlWhCd);
        setValue(inMsg.rtlSwhCd, rtlSwhCd);
        return (RTL_SWHTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // END 2018/04/12 K.Kojima [QC#23639,ADD]

    // add start 2022/03/14 QC#59780
    private String getTrkNumForS21(String origClickTrkNum) {
        if (!ZYPCommonFunc.hasValue(origClickTrkNum)) {
            return origClickTrkNum;
        }
        if (origClickTrkNum.length() == 34) {
            return origClickTrkNum.substring(34 - 12);
        }
        if (origClickTrkNum.length() > 30) {
            return origClickTrkNum.substring(0, 30);
        }
        return origClickTrkNum;
    }

    private String getTrkNumForWms(String origClickTrkNum) {
        if (ZYPCommonFunc.hasValue(origClickTrkNum) && origClickTrkNum.length() == 34) {
            return origClickTrkNum;
        }
        return null;
    }
    // add end 2022/03/14 QC#59780
}
