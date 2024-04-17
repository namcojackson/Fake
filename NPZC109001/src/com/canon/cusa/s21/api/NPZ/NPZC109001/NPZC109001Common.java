/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC109001;

import static com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import business.db.INBD_VISTMsg;
import business.parts.NPZC109001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACK_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VIS_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * POYO Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/12/2013   Hitachi         T.Tomita        Create          N/A
 * 05/06/2013   Hitachi         K.Kasai         Update          QC1187
 * 09/04/2017   CITS            T.Tokutomi      Update          QC#20433
 * </pre>
 */
public class NPZC109001Common {

    protected static void checkParam(NPZC109001PMsg pMsg, S21ApiMessageMap msgMap) {
        checkGlblCmpyCd(pMsg, msgMap);
        checkMode(pMsg, msgMap);
        String mode = pMsg.xxModeCd.getValue();

        if (pMsg.detailList != null && pMsg.detailList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.detailList.getValidCount(); i++) {
                checkPoOrdNum(pMsg.detailList.no(i).poOrdNum.getValue(), msgMap);
                checkPoOrdDtlLineNum(pMsg.detailList.no(i).poOrdDtlLineNum.getValue(), msgMap);
                if (isHistoryMode(mode)) {
                    checkQty(pMsg.detailList.no(i).xxQty10Num.getValue(), msgMap);
                }
            }
        } else {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0131E));
            msgMap.addXxMsgId(NPZM0131E);
        }
    }

    private static void checkGlblCmpyCd(NPZC109001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.glblCmpyCd)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0001E));
            msgMap.addXxMsgId(NPZM0001E);
        }
    }

    private static void checkMode(NPZC109001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.xxModeCd)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0093E));
            msgMap.addXxMsgId(NPZM0093E);
        }
    }

    private static void checkPoOrdNum(String poOrdNum, S21ApiMessageMap msgMap) {
        if (!hasValue(poOrdNum)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0069E));
            msgMap.addXxMsgId(NPZM0069E);
        }
    }

    private static void checkPoOrdDtlLineNum(String poOrdDtlLineNum, S21ApiMessageMap msgMap) {
        if (!hasValue(poOrdDtlLineNum)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0108E));
            msgMap.addXxMsgId(NPZM0108E);
        }
    }

    private static void checkQty(BigDecimal qty, S21ApiMessageMap msgMap) {
        if (!hasValue(qty)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0127E));
            msgMap.addXxMsgId(NPZM0127E);
        }
    }

    protected static boolean isInsertMode(String mode) {
        return POYO_INSERT_MODE.equals(mode);
    }

    protected static boolean isHistoryMode(String mode) {
        return POYO_HISTORY_MODE.equals(mode);
    }

    protected static boolean isDeleteMode(String mode) {
        return POYO_DELETE_MODE.equals(mode);
    }

    protected static boolean isPoStsValidated(String poStsCd) {
        return PO_STS.VALIDATED.equals(poStsCd);
    }

    protected static boolean isPoStsReceiving(String poStsCd) {
        return PO_STS.RECEIVING.equals(poStsCd);
    }

    protected static boolean isPoStsPoConfirmed(String poStsCd) {
        return PO_STS.PO_CONFIRMED.equals(poStsCd);
    }

    protected static boolean isPoStsPoError(String poStsCd) {
        return PO_STS.PO_ERROR.equals(poStsCd);
    }

    protected static String makeImptInvDoNum(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        String suffixNum = MIN_SUFFIX_NUM;
        INBD_VISTMsg inbdVisTMsg = NPZC109001DBAccess.selectInbdVisTMsgToImptInvDoNum(glblCmpyCd, poOrdNum, poOrdDtlLineNum);
        if (inbdVisTMsg != null) {
            String currentSuffixNum = inbdVisTMsg.imptInvDoNum.getValue().substring(SUFFIX_NUM_POSITION);
            if (!MAX_SUFFIX_NUM.equals(currentSuffixNum)) {
                int nextSuffixNum = Integer.parseInt(currentSuffixNum) + 1;
                suffixNum = ZYPCommonFunc.leftPad(String.valueOf(nextSuffixNum), LINE_NUM_LENGTH, STR_ZERO);
            }
        }

        StringBuilder imptInvDoNum = new StringBuilder();
        imptInvDoNum.append(poOrdNum);
        imptInvDoNum.append(poOrdDtlLineNum);
        imptInvDoNum.append(suffixNum);

        return imptInvDoNum.toString();
    }

    protected static void updateInbdLtstRecFlg(List<INBD_VISTMsg> inbdVisTMsgList) {
        for (INBD_VISTMsg inMsg : inbdVisTMsgList) {
            ZYPEZDItemValueSetter.setValue(inMsg.inbdLtstRecFlg, ZYPConstant.FLG_OFF_N);
            NPZC109001DBAccess.updateInbdVisTMsg(inMsg);
        }
    }

    protected static void updateVisQty(List<INBD_VISTMsg> inbdVisTMsgList, BigDecimal apiQty) {
        BigDecimal visQtyValue;
        for (INBD_VISTMsg inMsg : inbdVisTMsgList) {
            visQtyValue = inMsg.visQty.getValue().subtract(apiQty);
            ZYPEZDItemValueSetter.setValue(inMsg.visQty, visQtyValue);
            NPZC109001DBAccess.updateInbdVisTMsg(inMsg);
        }
    }

    // QC#20433 Update method. Add variable ltstRecFlg. : Update Date 09/01/2017
    protected static void createInbdVisForShippedPoAck(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String regdTs, BigDecimal diffQty, Map<String, Object> shippiedPoAckDtlMap, Map<String, Object> poMap, String ltstRecFlg) {
        // Create DC Stock-in
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        String imptInvDoNum = makeImptInvDoNum(glblCmpyCd, poOrdNum, poOrdDtlLineNum);
        setInbdVisCommonForInsertMode(inMsg, ltstRecFlg);
        setInbdVisPoAckForInsertMode(inMsg, shippiedPoAckDtlMap, poMap);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);
        ZYPEZDItemValueSetter.setValue(inMsg.imptInvDoNum, imptInvDoNum);
        ZYPEZDItemValueSetter.setValue(inMsg.visLocTpCd, VIS_LOC_TP.DC);
        ZYPEZDItemValueSetter.setValue(inMsg.visQty, diffQty);
        ZYPEZDItemValueSetter.setValue(inMsg.etaEtdDt, (String) shippiedPoAckDtlMap.get("ETA_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.regdTs, regdTs);
        NPZC109001DBAccess.insertInbdVisTMsg(inMsg);

        // Create Vendor Stock-out
        INBD_VISTMsg outMsg = new INBD_VISTMsg();
        EZDMsg.copy(inMsg, null, outMsg, null);
        setInbdVisCommonForInsertMode(outMsg, ltstRecFlg);
        ZYPEZDItemValueSetter.setValue(outMsg.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outMsg.visLocTpCd, VIS_LOC_TP.VENDOR);
        ZYPEZDItemValueSetter.setValue(outMsg.etaEtdDt, (String) shippiedPoAckDtlMap.get("ETD_DT"));
        NPZC109001DBAccess.insertInbdVisTMsg(outMsg);
    }

    // QC#20433 Update method. Add variable ltstRecFlg. : Update Date 09/01/2017
    protected static void createInbdVisForNotShipPoAck(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String regdTs, Map<String, Object> notShipPoAckDtlMap, Map<String, Object> poMap, String ltstRecFlg) {
        // Create DC Stock-in
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        String poAckLineStsCd = (String) notShipPoAckDtlMap.get("PO_ACK_LINE_STS_CD");
        String imptInvDoNum = makeImptInvDoNum(glblCmpyCd, poOrdNum, poOrdDtlLineNum);
        setInbdVisCommonForInsertMode(inMsg, ltstRecFlg);
        setInbdVisPoAckForInsertMode(inMsg, notShipPoAckDtlMap, poMap);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);
        ZYPEZDItemValueSetter.setValue(inMsg.imptInvDoNum, imptInvDoNum);
        ZYPEZDItemValueSetter.setValue(inMsg.visLocTpCd, VIS_LOC_TP.DC);
        ZYPEZDItemValueSetter.setValue(inMsg.visQty, (BigDecimal) notShipPoAckDtlMap.get("ORD_QTY"));
        //START 2013/05/06 Kasai[Mod Defect.#1187]
        if (PO_ACK_LINE_STS.BACK_ORDER.equals(poAckLineStsCd) || !ZYPCommonFunc.hasValue((String) notShipPoAckDtlMap.get("ETA_DT"))) {
            ZYPEZDItemValueSetter.setValue(inMsg.etaEtdDt, (String) poMap.get("ETA_DT"));
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.etaEtdDt, (String) notShipPoAckDtlMap.get("ETA_DT"));
        }
        //END 2013/05/06 Kasai[Mod Defect.#1187]
        ZYPEZDItemValueSetter.setValue(inMsg.regdTs, regdTs);
        NPZC109001DBAccess.insertInbdVisTMsg(inMsg);

        // Create Vendor Stock-out
        INBD_VISTMsg outMsg = new INBD_VISTMsg();
        EZDMsg.copy(inMsg, null, outMsg, null);
        setInbdVisCommonForInsertMode(outMsg, ltstRecFlg);
        ZYPEZDItemValueSetter.setValue(outMsg.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(outMsg.visLocTpCd, VIS_LOC_TP.VENDOR);
        //START 2013/05/06 Kasai[Mod Defect.#1187]
        if (PO_ACK_LINE_STS.BACK_ORDER.equals(poAckLineStsCd)) {
            outMsg.etaEtdDt.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(outMsg.etaEtdDt, (String) notShipPoAckDtlMap.get("ETD_DT"));
        }
        //END 2013/05/06 Kasai[Mod Defect.#1187]
        NPZC109001DBAccess.insertInbdVisTMsg(outMsg);
    }

    // QC#20433 Update method. Add variable ltstRecFlg. : Update Date 09/01/2017
    protected static void createInbdVisForPo(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String regdTs, BigDecimal diffPoQty, Map<String, Object> poMap, String ltstRecFlg) {
        // Create DC Stock-in
        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        String imptInvDoNum = makeImptInvDoNum(glblCmpyCd, poOrdNum, poOrdDtlLineNum);
        setInbdVisCommonForInsertMode(inMsg, ltstRecFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);
        ZYPEZDItemValueSetter.setValue(inMsg.imptInvDoNum, imptInvDoNum);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) poMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.visLocTpCd, VIS_LOC_TP.DC);
        ZYPEZDItemValueSetter.setValue(inMsg.visLocCd, (String) poMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.visLocNm, (String) poMap.get("INVTY_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(inMsg.visQty, diffPoQty);
        ZYPEZDItemValueSetter.setValue(inMsg.etaEtdDt, (String) poMap.get("ETA_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.cratTs, (String) poMap.get("PO_SUBMT_TS"));
        ZYPEZDItemValueSetter.setValue(inMsg.regdTs, regdTs);
        NPZC109001DBAccess.insertInbdVisTMsg(inMsg);
    }

    // QC#20433 Update method. Add variable ltstRecFlg. : Update Date 09/01/2017
    protected static void setInbdVisCommonForInsertMode(INBD_VISTMsg inMsg, String ltstRecFlg) {
        ZYPEZDItemValueSetter.setValue(inMsg.inbdVisPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ));
        ZYPEZDItemValueSetter.setValue(inMsg.inbdLtstRecFlg, ltstRecFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.inbdVisEventCd, INBD_VIS_EVENT.POYO_RECEIVE);
        ZYPEZDItemValueSetter.setValue(inMsg.inbdVisActlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.imptInvPoTpCd, PRE_DO_VAL);
        ZYPEZDItemValueSetter.setValue(inMsg.calcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.invtyStkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(inMsg.poyoIntfcId, NPZC1090);
    }

    protected static void setInbdVisPoAckForInsertMode(INBD_VISTMsg inMsg, Map<String, Object> poAckDtlMap, Map<String, Object> poMap) {
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) poAckDtlMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.visLocCd, (String) poMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.visLocNm, (String) poMap.get("INVTY_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(inMsg.cratTs, (String) poAckDtlMap.get("ORD_DTL_LAST_UPD_TS"));
    }

    protected static void createInbdVisForHistoryMode(List<INBD_VISTMsg> inbdVisTMsgList, String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, BigDecimal apiQty) {
        String imptInvDoNum = makeImptInvDoNum(glblCmpyCd, poOrdNum, poOrdDtlLineNum);

        for (INBD_VISTMsg inMsg : inbdVisTMsgList) {
            ZYPEZDItemValueSetter.setValue(inMsg.inbdVisPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.inbdLtstRecFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.imptInvDoNum, imptInvDoNum);
            ZYPEZDItemValueSetter.setValue(inMsg.visQty, apiQty);

            NPZC109001DBAccess.insertInbdVisTMsg(inMsg);
        }
    }

    protected static BigDecimal sumQty(List<Map<String, Object>> mapList, String key) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        BigDecimal qty;
        for (Map<String, Object> map : mapList) {
            qty = (BigDecimal) map.get(key);
            if (hasValue(qty)) {
                rtnVal = rtnVal.add(qty);
            }
        }

        return rtnVal;
    }

    protected static BigDecimal sumPoQty(Map<String, Object> poMap) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        BigDecimal poRcvQty = (BigDecimal) poMap.get("PO_RCV_QTY");
        BigDecimal poBalQty = (BigDecimal) poMap.get("PO_BAL_QTY");
        if (hasValue(poRcvQty)) {
            rtnVal = rtnVal.add(poRcvQty);
        }

        if (hasValue(poBalQty)) {
            rtnVal = rtnVal.add(poBalQty);
        }

        return rtnVal;
    }

    protected static BigDecimal calcVisQty(Map<String, Object> poMap, List<Map<String, Object>> rwsMapList) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        BigDecimal poBalQty = (BigDecimal) poMap.get("PO_BAL_QTY");
        if (poBalQty != null) {
            rtnVal = rtnVal.add(poBalQty);
        }

        BigDecimal printedRwsQty = BigDecimal.ZERO;
        BigDecimal otherRwsQty = BigDecimal.ZERO;
        BigDecimal otherRwsPutAwayQty = BigDecimal.ZERO;
        String rwsStsCd;
        BigDecimal rwsQty;
        BigDecimal rwsPutAwayQty;
        for (Map<String, Object> rwsMap : rwsMapList) {
            rwsStsCd = (String) rwsMap.get("RWS_STS_CD");
            rwsQty = (BigDecimal) rwsMap.get("RWS_QTY");
            rwsPutAwayQty = (BigDecimal) rwsMap.get("RWS_PUT_AWAY_QTY");
            if (RWS_STS.PRINTED.equals(rwsStsCd)) {
                if (hasValue(rwsQty)) {
                    printedRwsQty = printedRwsQty.add(rwsQty);
                }
                continue;
            }

            if (hasValue(rwsQty)) {
                otherRwsQty = otherRwsQty.add(rwsQty);
            }
            if (hasValue(rwsPutAwayQty)) {
                otherRwsPutAwayQty = otherRwsPutAwayQty.add(rwsPutAwayQty);
            }
        }

        BigDecimal otherQty = otherRwsQty.subtract(otherRwsPutAwayQty);
        BigDecimal calcRwsQty = printedRwsQty.add(otherQty);
        rtnVal = rtnVal.subtract(calcRwsQty);

        return rtnVal;
    }
}
