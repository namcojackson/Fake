/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC109001;

import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.NPZC1090;
import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.PERCENT;
import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.STR_FIVE;
import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.STR_FOUR;
import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.STR_ONE;
import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.STR_SIX;
import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.STR_THREE;
import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.STR_TWO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.RCV_ASN_VNDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACK_LINE_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * POYO Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/12/2013   Hitachi         T.Tomita        Create          N/A
 * 04/26/2013   Hitachi         T.Tomita        Update          QC1137
 * 05/06/2013   Hitachi         K.Kasai         Update          QC1187
 * 05/10/2013   Hitachi         T.Tomita        Update          QC1200
 * 12/12/2016   CITS            S.Endo          Update          QC14453
 * 08/24/2017   CITS            T.Tokutomi      Update          QC#20433
 * </pre>
 */
public class NPZC109001DBAccess {
    // 2013/04/26 QC1137 T.Tomita Update Start
    protected static INBD_VISTMsgArray selectInbdVisListForInsertMode(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setSQLID("050");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inbdLtstRecFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("inbdVisEventCd01", INBD_VIS_EVENT.POYO_RECEIVE);
        StringBuilder imptInvDoNum = new StringBuilder();
        imptInvDoNum.append(poOrdNum);
        imptInvDoNum.append(poOrdDtlLineNum);
        imptInvDoNum.append(PERCENT);
        inMsg.setConditionValue("imptInvDoNum01", imptInvDoNum.toString());
        inMsg.setConditionValue("inbdVisDataTpCd01A", INBD_VIS_DATA_TP.STOCK_OUT);
        inMsg.setConditionValue("inbdVisDataTpCd01B", INBD_VIS_DATA_TP.STOCK_IN_DC);
        inMsg.setConditionValue("poyoIntfcId01", NPZC1090);

        INBD_VISTMsgArray resultArray = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

        if (resultArray == null) {
            resultArray = new INBD_VISTMsgArray();
        }

        return resultArray;
    }
    // 2013/04/26 QC1137 T.Tomita Update End

    protected static INBD_VISTMsgArray selectInbdVisStkInForHistoryMode(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setSQLID("046");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inbdLtstRecFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("inbdVisEventCd01", INBD_VIS_EVENT.POYO_RECEIVE);
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_IN_DC);
        StringBuilder imptInvDoNum = new StringBuilder();
        imptInvDoNum.append(poOrdNum);
        imptInvDoNum.append(poOrdDtlLineNum);
        imptInvDoNum.append(PERCENT);
        inMsg.setConditionValue("imptInvDoNum01", imptInvDoNum.toString());

        INBD_VISTMsgArray resultArray = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (resultArray == null) {
            resultArray = new INBD_VISTMsgArray();
        }

        return resultArray;
    }

    protected static INBD_VISTMsg selectInbdVisStkOutForHistoryMode(String glblCmpyCd, String imptInvDoNum) {
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setSQLID("047");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inbdLtstRecFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("inbdVisEventCd01", INBD_VIS_EVENT.POYO_RECEIVE);
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_OUT);
        inMsg.setConditionValue("imptInvDoNum01", imptInvDoNum);

        INBD_VISTMsgArray resultArray = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        INBD_VISTMsg result = null;
        if (resultArray != null && resultArray.getValidCount() > 0) {
            result = resultArray.no(0);
        }

        return result;
    }

    protected static INBD_VISTMsg selectInbdVisTMsgToImptInvDoNum(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setSQLID("048");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inbdVisEventCd01", INBD_VIS_EVENT.POYO_RECEIVE);
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_IN_DC);
        StringBuilder imptInvDoNum = new StringBuilder();
        imptInvDoNum.append(poOrdNum);
        imptInvDoNum.append(poOrdDtlLineNum);
        imptInvDoNum.append(PERCENT);
        inMsg.setConditionValue("imptInvDoNum01", imptInvDoNum.toString());

        INBD_VISTMsgArray resultArray = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        INBD_VISTMsg result = null;
        if (resultArray != null && resultArray.getValidCount() > 0) {
            result = resultArray.no(0);
        }

        return result;
    }

    protected static INBD_VISTMsgArray selectInbdVisTMsgForDeleteMode(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setSQLID("049");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inbdLtstRecFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("inbdVisEventCd01", INBD_VIS_EVENT.POYO_RECEIVE);
        inMsg.setConditionValue("inbdVisDataTpCd01A", INBD_VIS_DATA_TP.STOCK_OUT);
        inMsg.setConditionValue("inbdVisDataTpCd01B", INBD_VIS_DATA_TP.STOCK_IN_DC);
        StringBuilder imptInvDoNum = new StringBuilder();
        imptInvDoNum.append(poOrdNum);
        imptInvDoNum.append(poOrdDtlLineNum);
        imptInvDoNum.append(PERCENT);
        inMsg.setConditionValue("imptInvDoNum01", imptInvDoNum.toString());

        INBD_VISTMsgArray resultArray = (INBD_VISTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

        if (resultArray == null) {
            resultArray = new INBD_VISTMsgArray();
        }

        return resultArray;
    }

    protected static POTMsg selectPoTMsgFindByKey(String glblCmpyCd, String poOrdNum) {
        POTMsg inMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);

        return (POTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static PO_DTLTMsg selectPoDtlTMsgFindByKey(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        PO_DTLTMsg inMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poOrdDtlLineNum);

        return (PO_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static Map<String, Object> selectPoMap(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("poOrdNum", poOrdNum);
        param.put("poOrdDtlLineNum", poOrdDtlLineNum);

        @SuppressWarnings("unchecked")
        Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getPo", param);

        return resultMap;
    }

    protected static List<Map<String, Object>> selectNotShipPoAckDtlMapList(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("strOne", STR_ONE);
        param.put("strTwo", STR_TWO);
        param.put("strThree", STR_THREE);
        param.put("strFour", STR_FOUR);
        param.put("strFive", STR_FIVE);
        //START 2013/05/06 Kasai[Mod Defect.#1187]
        param.put("strSix", STR_SIX);
        //END 2013/05/06 Kasai[Mod Defect.#1187]
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("poOrdNum", poOrdNum);
        param.put("poOrdDtlLineNum", poOrdDtlLineNum);
        param.put("poAckLineStsCdA", PO_ACK_LINE_STS.ALLOCATION);
        param.put("poAckLineStsCdB", PO_ACK_LINE_STS.BACK_ORDER);
        param.put("poAckLineStsCdSp", PO_ACK_LINE_STS.SO_PRINTED);
        param.put("poAckLineStsCdE", PO_ACK_LINE_STS.ERROR);
        //START 2013/05/06 Kasai[Mod Defect.#1187]
        param.put("poAckLineStsCdH", PO_ACK_LINE_STS.HOLD);
        //END 2013/05/06 Kasai[Mod Defect.#1187]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getNotShipPoAckDtl", param);
        if (resultMapList == null) {
            resultMapList = new ArrayList<Map<String, Object>>();
        }

        return resultMapList;
    }

    protected static List<Map<String, Object>> selectShippedPoAckDtlMapList(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("poOrdNum", poOrdNum);
        param.put("poOrdDtlLineNum", poOrdDtlLineNum);
        param.put("poAckLineStsCd", PO_ACK_LINE_STS.SHIPPED);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShippedPoAckDtl", param);
        if (resultMapList == null) {
            resultMapList = new ArrayList<Map<String, Object>>();
        }

        return resultMapList;
    }

    protected static List<Map<String, Object>> selectRwsMapList(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String[] rwsStsCdList, S21SsmBatchClient ssmBatchClient) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("poOrdNum", poOrdNum);
        param.put("poOrdDtlLineNum", poOrdDtlLineNum);
        param.put("rwsStsCdList", rwsStsCdList);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRws", param);
        if (resultMapList == null) {
            resultMapList = new ArrayList<Map<String, Object>>();
        }

        return resultMapList;
    }

    protected static List<Map<String, Object>> selectRwsMapList(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String[] rwsStsCdList, List<String> vndSoNumList, S21SsmBatchClient ssmBatchClient) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("poOrdNum", poOrdNum);
        param.put("poOrdDtlLineNum", poOrdDtlLineNum);
        param.put("rwsStsCdList", rwsStsCdList);
        param.put("rwsRefNum", vndSoNumList);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRws", param);
        if (resultMapList == null) {
            resultMapList = new ArrayList<Map<String, Object>>();
        }

        return resultMapList;
    }

    protected static int countRcvAsnVndFindByKey(String glblCmpyCd, String vndCd) {
        RCV_ASN_VNDTMsg inMsg = new RCV_ASN_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rcvAsnVndCd, vndCd);

        RCV_ASN_VNDTMsg result = (RCV_ASN_VNDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        int resultCount = 0;
        if (result != null) {
            resultCount = 1;
        }
        return resultCount;
    }

    protected static void insertInbdVisTMsg(INBD_VISTMsg inMsg) {
        // 2013/05/10 QC1200 T.Tomita Update Start
        if (BigDecimal.ZERO.compareTo(inMsg.visQty.getValue()) < 0) {
            S21ApiTBLAccessor.insert(inMsg);
        }
        // 2013/05/10 QC1200 T.Tomita Update Start
    }

    protected static void updateInbdVisTMsg(INBD_VISTMsg inMsg) {
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void removeInbdVisList(INBD_VISTMsgArray inMsgArray) {
        INBD_VISTMsg[] inMsg = new INBD_VISTMsg[inMsgArray.getValidCount()];
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            inMsg[i] = inMsgArray.no(i);
            ZYPEZDItemValueSetter.setValue(inMsg[i].inbdLtstRecFlg, ZYPConstant.FLG_OFF_N);

            updateInbdVisTMsg(inMsg[i]);

        }
        S21ApiTBLAccessor.logicalRemove(inMsg);
    }
}
