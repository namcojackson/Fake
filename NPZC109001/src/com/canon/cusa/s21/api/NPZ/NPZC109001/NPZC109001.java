/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC109001;

import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.parts.NPZC109001PMsg;
import business.parts.NPZC109001_detailListPMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * POYO Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/12/2013   Hitachi         T.Tomita        Create          N/A
 * 04/25/2013   Hitachi         T.Tomita        Update          QC1030
 * 04/26/2013   Hitachi         T.Tomita        Update          QC1137
 * 05/09/2013   Hitachi         T.Tomita        Update          QC1187
 * 05/10/2013   Hitachi         T.Tomita        Update          QC1200
 * 12/12/2016   CITS            S.Endo          Update          QC#14453
 * 09/04/2017   CITS            T.Tokutomi      Update          QC#20433
 * </pre>
 */
public class NPZC109001 extends S21ApiCommonBase {

    /**
     * S21ApiMessageMap
     */
    private S21ApiMessageMap msgMap = null;

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NPZC109001() {
        super();
    }

    /**
     * PR Update API
     * @param param NPZC109001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC109001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        NPZC109001PMsg pMsg = (NPZC109001PMsg) this.msgMap.getPmsg();
        doProcess(pMsg);
        this.msgMap.flush();
    }

    private void doProcess(NPZC109001PMsg pMsg) {
        // Check Parameter
        NPZC109001Common.checkParam(pMsg, msgMap);
        if (this.msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (pMsg.detailList == null || pMsg.detailList.getValidCount() == 0) {
            // No Detail Data
            return;
        }

        String mode = pMsg.xxModeCd.getValue();
        if (NPZC109001Common.isInsertMode(mode)) {
            // Insert Process
            insertProcess(pMsg);
        } else if (NPZC109001Common.isHistoryMode(mode)) {
            // History Process
            historyProcess(pMsg);
        } else if (NPZC109001Common.isDeleteMode(mode)) {
            // Delete Process
            deleteProcess(pMsg);
        }
    }

    private void insertProcess(NPZC109001PMsg pMsg) {
        // Logical Remove Inbound Visibility
        removeInbdVisForInsertMode(pMsg);

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String regdTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        NPZC109001_detailListPMsg detail;
        POTMsg poTMsg;
        String poStsCd;
        for (int i = 0; i < pMsg.detailList.getValidCount(); i++) {
            detail = pMsg.detailList.no(i);
            poTMsg = NPZC109001DBAccess.selectPoTMsgFindByKey(glblCmpyCd, detail.poOrdNum.getValue());
            if (poTMsg == null) {
                // Not PO Data
                continue;
            }

            poStsCd = poTMsg.poStsCd.getValue();
            if (NPZC109001Common.isPoStsValidated(poStsCd) || NPZC109001Common.isPoStsReceiving(poStsCd) || NPZC109001Common.isPoStsPoConfirmed(poStsCd) || NPZC109001Common.isPoStsPoError(poStsCd)) {
                if (isRcvAsnVndPo(glblCmpyCd, poTMsg)) {
                    executeRcvAsnVndInbdVis(glblCmpyCd, regdTs, detail);
                } else {
                    exec3rdPtyVndInbdVis(glblCmpyCd, regdTs, detail);
                }
            }
        }
    }

    private void historyProcess(NPZC109001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        NPZC109001_detailListPMsg detail;
        for (int i = 0; i < pMsg.detailList.getValidCount(); i++) {
            detail = pMsg.detailList.no(i);
            // 2013/05/10 QC1200 T.Tomita Update Start
            if (BigDecimal.ZERO.compareTo(detail.xxQty10Num.getValue()) < 0) {
                executeInbdVisForHistoryMode(glblCmpyCd, detail.poOrdNum.getValue(), detail.poOrdDtlLineNum.getValue(), detail.xxQty10Num.getValue());
            }
            // 2013/05/10 QC1200 T.Tomita Update End
        }
    }

    private void deleteProcess(NPZC109001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        NPZC109001_detailListPMsg detail;
        for (int i = 0; i < pMsg.detailList.getValidCount(); i++) {
            detail = pMsg.detailList.no(i);
            executeInbdVisForDeleteMode(glblCmpyCd, detail.poOrdNum.getValue(), detail.poOrdDtlLineNum.getValue());
        }
    }

    private void removeInbdVisForInsertMode(NPZC109001PMsg pMsg) {
        // 2013/04/26 QC1137 T.Tomita Update Start
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        NPZC109001_detailListPMsg detail;
        for (int i = 0; i < pMsg.detailList.getValidCount(); i++) {
            detail = pMsg.detailList.no(i);
            // Select Inbound Visibility
            INBD_VISTMsgArray inMsgArray = NPZC109001DBAccess.selectInbdVisListForInsertMode(glblCmpyCd, detail.poOrdNum.getValue(), detail.poOrdDtlLineNum.getValue());
            if (inMsgArray.getValidCount() > 0) {
                // Logical Remove Inbound Visibility
                NPZC109001DBAccess.removeInbdVisList(inMsgArray);
            }
        }
        // 2013/04/26 QC1137 T.Tomita Update End
    }

    private boolean isRcvAsnVndPo(String glblCmpyCd, POTMsg poTMsg) {
        // Count RcvAsnVndPo
        int count = NPZC109001DBAccess.countRcvAsnVndFindByKey(glblCmpyCd, poTMsg.vndCd.getValue());
        boolean result = false;
        if (count > 0) {
            result = true;
        }
        return result;
    }

    private void executeRcvAsnVndInbdVis(String glblCmpyCd, String regdTs, NPZC109001_detailListPMsg detail) {
        // Select PO Detail
        PO_DTLTMsg poDtlTMsg = NPZC109001DBAccess.selectPoDtlTMsgFindByKey(glblCmpyCd, detail.poOrdNum.getValue(), detail.poOrdDtlLineNum.getValue());
        if (poDtlTMsg == null) {
            return;
        }

        String poStsCd = poDtlTMsg.poStsCd.getValue();
        if (NPZC109001Common.isPoStsReceiving(poStsCd) || NPZC109001Common.isPoStsPoConfirmed(poStsCd) || NPZC109001Common.isPoStsPoError(poStsCd)) {
            createRcvAsnVndInbdVis(glblCmpyCd, regdTs, detail);
        } else if (NPZC109001Common.isPoStsValidated(poStsCd)) {
            execPoAckNoReciveInbdVis(glblCmpyCd, regdTs, detail);
        }
    }

    // QC#20433 Update method. Update by 09/04/2017
    private void createRcvAsnVndInbdVis(String glblCmpyCd, String regdTs, NPZC109001_detailListPMsg detail) {
        String poOrdNum = detail.poOrdNum.getValue();
        String poOrdDtlLineNum = detail.poOrdDtlLineNum.getValue();

        // Get PO Data
        Map<String, Object> poMap = NPZC109001DBAccess.selectPoMap(glblCmpyCd, poOrdNum, poOrdDtlLineNum, ssmBatchClient);
        if (poMap == null || poMap.isEmpty()) {
            this.msgMap.addXxMsgId(NPZM0231E);
            return;
        }

        // Get Not Ship PO_ACK_DTL Data
        List<Map<String, Object>> notShipPoAckDtlMapList = NPZC109001DBAccess.selectNotShipPoAckDtlMapList(glblCmpyCd, poOrdNum, poOrdDtlLineNum, ssmBatchClient);
        // Get Shipped PO_ACK_DTL Data
        List<Map<String, Object>> shippiedPoAckDtlMapList = NPZC109001DBAccess.selectShippedPoAckDtlMapList(glblCmpyCd, poOrdNum, poOrdDtlLineNum, ssmBatchClient);
        // 2013/05/09 QC1187 T.Tomita Add Start
        // PO_ACK Count Check
        int poAckCount = notShipPoAckDtlMapList.size() + shippiedPoAckDtlMapList.size();
        if (poAckCount == 0) {
            execPoAckNoReciveInbdVis(glblCmpyCd, regdTs, detail);
            return;
        }

        List<String> vndSoNumList = getVndSoNumList(notShipPoAckDtlMapList, shippiedPoAckDtlMapList);

        if (vndSoNumList.isEmpty()) {
            vndSoNumList.add(" ");
        }

        // 2013/05/09 QC1187 T.Tomita Add End
        // Get RWS Data
        String[] rwsStsCdList = new String[] {RWS_STS.PRINTED, RWS_STS.RECEIVING, RWS_STS.RECEIVED };
        List<Map<String, Object>> rwsMapList = NPZC109001DBAccess.selectRwsMapList(glblCmpyCd, poOrdNum, poOrdDtlLineNum, rwsStsCdList, vndSoNumList, ssmBatchClient);

        // Sum Shipped ORD_QTY
        BigDecimal sumShippedOrdQty = NPZC109001Common.sumQty(shippiedPoAckDtlMapList, "ORD_QTY");
        // Sum RWS_QTY
        BigDecimal sumRwsQty = NPZC109001Common.sumQty(rwsMapList, "RWS_QTY");

        // Sum PO QTY
        BigDecimal sumPoQty = NPZC109001Common.sumPoQty(poMap);

        // get relation rws
        List<Map<String, Object>> otherRwsList = NPZC109001DBAccess.selectRwsMapList(glblCmpyCd, poOrdNum, poOrdDtlLineNum, rwsStsCdList, ssmBatchClient);
        // Sum relation rws_qty
        BigDecimal sumRelationRwsQty = NPZC109001Common.sumQty(otherRwsList, "RWS_QTY");

        String ltstRecFlg = ZYPConstant.FLG_OFF_N;
        if(sumPoQty.compareTo(sumRelationRwsQty) > 0){
            ltstRecFlg = ZYPConstant.FLG_ON_Y;
        } 


        List<Map<String, Object>> execNotShipPoAckList = notShipPoAckDtlMapList;
        BigDecimal diffQty = BigDecimal.ZERO;
        if (sumShippedOrdQty.compareTo(sumRwsQty) > 0) {
            diffQty = sumShippedOrdQty.subtract(sumRwsQty);
            // Create INBD_VIS For Shipped PO_ACK
            execShippedPoAckInbdVis(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, diffQty, shippiedPoAckDtlMapList, poMap, ltstRecFlg);
        } else if (sumShippedOrdQty.compareTo(sumRwsQty) < 0) {
            diffQty = sumRwsQty.subtract(sumShippedOrdQty);
            execNotShipPoAckList = getNotShipPoAck(diffQty, notShipPoAckDtlMapList);
        }

        // Sum Not Ship ORD_QTY
        BigDecimal sumNotShipOrdQty = NPZC109001Common.sumQty(notShipPoAckDtlMapList, "ORD_QTY");

        // Create INBD_VIS For Not Ship PO_ACK
        // QC#20433 not ship - shipped
        if(sumNotShipOrdQty.compareTo(sumShippedOrdQty) != 0){
            execNotShipPoAckInbdVis(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, execNotShipPoAckList, poMap, ltstRecFlg);
        }

        // Sum PO_ACK QTY
        BigDecimal sumPoAckQty = sumNotShipOrdQty.add(sumShippedOrdQty).add(sumRelationRwsQty);
        if (sumPoQty.compareTo(sumPoAckQty) > 0) {
            BigDecimal diffPoQty = sumPoQty.subtract(sumPoAckQty);
            NPZC109001Common.createInbdVisForPo(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, diffPoQty, poMap, ZYPConstant.FLG_ON_Y);
        }
    }

    // QC#20433 Update method. Add variable ltstRecFlg. : Update Date 09/01/2017
    private void execShippedPoAckInbdVis(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String regdTs, BigDecimal diffQty, List<Map<String, Object>> shippiedPoAckDtlMapList, Map<String, Object> poMap, String ltstRecFlg) {
        BigDecimal qty = diffQty;
        BigDecimal ordQty;
        for (Map<String, Object> map : shippiedPoAckDtlMapList) {
            ordQty = (BigDecimal) map.get("ORD_QTY");
            if (qty.compareTo(ordQty) <= 0) {
                NPZC109001Common.createInbdVisForShippedPoAck(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, qty, map, poMap, ltstRecFlg);
                break;
            }

            NPZC109001Common.createInbdVisForShippedPoAck(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, ordQty, map, poMap, ltstRecFlg);
            qty = qty.subtract(ordQty);
        }
    }

    private List<Map<String, Object>> getNotShipPoAck(BigDecimal diffQty, List<Map<String, Object>> notShipPoAckDtlMapList) {
        List<Map<String, Object>> rtnMapList = new ArrayList<Map<String, Object>>();
        BigDecimal qty = diffQty;
        BigDecimal ordQty;
        Map<String, Object> copyMap;
        // 2013/04/25 QC1030 T.Tomita Add Start
        int lineNo = 0;
        // 2013/04/25 QC1030 T.Tomita Add End
        for (Map<String, Object> map : notShipPoAckDtlMapList) {
            ordQty = (BigDecimal) map.get("ORD_QTY");
            // 2013/04/25 QC1030 T.Tomita Add Start
            lineNo++;
            // 2013/04/25 QC1030 T.Tomita Add End
            if (qty.compareTo(ordQty) <= 0) {
                copyMap = new HashMap<String, Object>(map);
                ordQty = ordQty.subtract(qty);

                copyMap.put("ORD_QTY", ordQty);
                rtnMapList.add(copyMap);
                // 2013/04/25 QC1030 T.Tomita Add Start
                setTargetNotShipPoAckList(notShipPoAckDtlMapList, lineNo, rtnMapList);
                // 2013/04/25 QC1030 T.Tomita Add End
                break;
            }

            // 2013/04/25 QC1030 T.Tomita Delete Start
            // rtnMapList.add(map);
            // 2013/04/25 QC1030 T.Tomita Delete End
            qty = qty.subtract(ordQty);
        }
        return rtnMapList;
    }

    // QC#20433 Update method. Add variable ltstRecFlg. : Update Date 09/01/2017
    private void execNotShipPoAckInbdVis(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String regdTs, List<Map<String, Object>> execNotShipPoAckList, Map<String, Object> poMap, String ltstRecFlg) {
        for (Map<String, Object> notShipPoAckMap : execNotShipPoAckList) {
            NPZC109001Common.createInbdVisForNotShipPoAck(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, notShipPoAckMap, poMap, ltstRecFlg);
        }
    }

    private void exec3rdPtyVndInbdVis(String glblCmpyCd, String regdTs, NPZC109001_detailListPMsg detail) {
        String poOrdNum = detail.poOrdNum.getValue();
        String poOrdDtlLineNum = detail.poOrdDtlLineNum.getValue();

        // Get PO Data
        Map<String, Object> poMap = NPZC109001DBAccess.selectPoMap(glblCmpyCd, poOrdNum, poOrdDtlLineNum, ssmBatchClient);
        if (poMap == null || poMap.isEmpty()) {
            this.msgMap.addXxMsgId(NPZM0231E);
            return;
        }
        // Get RWS Data
        String[] rwsStsCdList = new String[] {RWS_STS.PRINTED, RWS_STS.RECEIVING };
        List<Map<String, Object>> rwsMapList = NPZC109001DBAccess.selectRwsMapList(glblCmpyCd, poOrdNum, poOrdDtlLineNum, rwsStsCdList, ssmBatchClient);

        BigDecimal visQty = NPZC109001Common.calcVisQty(poMap, rwsMapList);
        NPZC109001Common.createInbdVisForPo(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, visQty, poMap, ZYPConstant.FLG_ON_Y);
    }

    private void execPoAckNoReciveInbdVis(String glblCmpyCd, String regdTs, NPZC109001_detailListPMsg detail) {
        String poOrdNum = detail.poOrdNum.getValue();
        String poOrdDtlLineNum = detail.poOrdDtlLineNum.getValue();

        // Get PO Data
        Map<String, Object> poMap = NPZC109001DBAccess.selectPoMap(glblCmpyCd, poOrdNum, poOrdDtlLineNum, ssmBatchClient);
        if (poMap == null || poMap.isEmpty()) {
            this.msgMap.addXxMsgId(NPZM0231E);
            return;
        }
        // Get RWS Data
        String[] rwsStsCdList = new String[] {RWS_STS.PRINTED, RWS_STS.RECEIVING, RWS_STS.RECEIVED };
        List<Map<String, Object>> rwsMapList = NPZC109001DBAccess.selectRwsMapList(glblCmpyCd, poOrdNum, poOrdDtlLineNum, rwsStsCdList, ssmBatchClient);

        BigDecimal visQty = NPZC109001Common.calcVisQty(poMap, rwsMapList);
        NPZC109001Common.createInbdVisForPo(glblCmpyCd, poOrdNum, poOrdDtlLineNum, regdTs, visQty, poMap, ZYPConstant.FLG_ON_Y);
    }

    private void executeInbdVisForHistoryMode(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, BigDecimal qty) {
        BigDecimal apiQty = qty;

        // Get Stock-In Inbound Visibility List
        INBD_VISTMsgArray inbdVisTMsgArray = NPZC109001DBAccess.selectInbdVisStkInForHistoryMode(glblCmpyCd, poOrdNum, poOrdDtlLineNum);

        List<INBD_VISTMsg> historyTMsgList;
        INBD_VISTMsg inInbdVisTMsg;
        INBD_VISTMsg outInbdVisTMsg;
        BigDecimal inVisQty;
        for (int i = 0; i < inbdVisTMsgArray.getValidCount(); i++) {
            historyTMsgList = new ArrayList<INBD_VISTMsg>();
            inInbdVisTMsg = inbdVisTMsgArray.no(i);
            historyTMsgList.add(inInbdVisTMsg);

            // Get Stock-Out Inbound Visibility
            outInbdVisTMsg = NPZC109001DBAccess.selectInbdVisStkOutForHistoryMode(glblCmpyCd, inInbdVisTMsg.imptInvDoNum.getValue());
            if (outInbdVisTMsg != null) {
                historyTMsgList.add(outInbdVisTMsg);
            }

            inVisQty = inInbdVisTMsg.visQty.getValue();

            if (apiQty.compareTo(inVisQty) > 0) {
                // API Quantity > Stock-In Visibility Quantity
                apiQty = apiQty.subtract(inVisQty);
                // Update INBD_VIS.INBD_LTST_REC_FLG
                NPZC109001Common.updateInbdLtstRecFlg(historyTMsgList);
                continue;
            }

            if (apiQty.compareTo(inVisQty) == 0) {
                // API Quantity = Stock-In Visibility Quantity
                // Update INBD_VIS.INBD_LTST_REC_FLG
                NPZC109001Common.updateInbdLtstRecFlg(historyTMsgList);
            } else {
                // API Quantity < Stock-In Visibility Quantity
                // Update INBD_VIS.VIS_QTY
                NPZC109001Common.updateVisQty(historyTMsgList, apiQty);
                // Create INBD_VIS
                NPZC109001Common.createInbdVisForHistoryMode(historyTMsgList, glblCmpyCd, poOrdNum, poOrdDtlLineNum, apiQty);
            }

            break;
        }
    }

    private void executeInbdVisForDeleteMode(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        // Get Inbound Visibility List
        INBD_VISTMsgArray inMsgArray = NPZC109001DBAccess.selectInbdVisTMsgForDeleteMode(glblCmpyCd, poOrdNum, poOrdDtlLineNum);
        if (inMsgArray.getValidCount() > 0) {
            // Logical Remove Inbound Visibility
            NPZC109001DBAccess.removeInbdVisList(inMsgArray);
        }
    }

    // 2013/04/25 QC1030 T.Tomita Add Start
    private void setTargetNotShipPoAckList(List<Map<String, Object>> notShipPoAckDtlMapList, int lineNo, List<Map<String, Object>> execNotShipPoAckList) {
        for (int i = lineNo; i < notShipPoAckDtlMapList.size(); i++) {
            execNotShipPoAckList.add(notShipPoAckDtlMapList.get(i));
        }
    }
    // 2013/04/25 QC1030 T.Tomita Add End

    // QC#20433 Add mothod
    private List<String> getVndSoNumList(List<Map<String, Object>> notShipPoAckDtlMapList, List<Map<String, Object>> shippiedPoAckDtlMapList) {
        HashSet<String> set = new HashSet<String>();

        for (Map<String, Object> map : notShipPoAckDtlMapList) {
            String vndSoNum = (String) map.get("VND_SO_NUM");
            if (ZYPCommonFunc.hasValue(vndSoNum)) {
                set.add(vndSoNum);
            }
        }
        for (Map<String, Object> map : shippiedPoAckDtlMapList) {
            String vndSoNum = (String) map.get("VND_SO_NUM");
            if (ZYPCommonFunc.hasValue(vndSoNum)) {
                set.add(vndSoNum);
            }
        }

        return new ArrayList<String>(set);
    }
}
