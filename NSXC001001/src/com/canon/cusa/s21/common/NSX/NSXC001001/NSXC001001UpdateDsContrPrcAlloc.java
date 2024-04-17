/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.parts.NSZC080001PMsg;
import business.parts.NSZC080001_segmentsListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC080001.NSZC080001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Update Direct Sales Contract Price Allocation
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/10   Hitachi         U.Kim           Create          N/A
 */

public class NSXC001001UpdateDsContrPrcAlloc {

    /**
     * @param glblCmpyCd
     * @param slsDt
     * @param dsContrPk
     * @param dsContrDtlPk
     * @param basePrcDealAmt
     * @param svcInvChrgTpCd
     * @param resrcObjNm
     * @param onBatchType
     * @return boolean
     */
    public static boolean setDsContrPrcAlloc(String glblCmpyCd, String slsDt, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal basePrcDealAmt, String svcInvChrgTpCd, String resrcObjNm, final ONBATCH_TYPE onBatchType) {
        // Parameter Check
        boolean checkResult = mandatoryCheck(glblCmpyCd, slsDt, dsContrPk, dsContrDtlPk, basePrcDealAmt, svcInvChrgTpCd, resrcObjNm, onBatchType);
        if (checkResult == false) {
            return false;
        }

        // Get digitNum
        BigDecimal digitNum = getDigitNum(glblCmpyCd, dsContrPk);
        if (digitNum == null) {
            return false;
        }

        // Get Up Target Price Allocation
        DS_CONTR_SEG_ALLOCTMsgArray dsContrPrcAllocTMsgList = getDsContrPrcAlloc(glblCmpyCd, dsContrPk, dsContrDtlPk, svcInvChrgTpCd);
        if (dsContrPrcAllocTMsgList.getValidCount() == 0) {
            return true;
        }

        // Calculate total Price Allocation Amount and Price Allocation Rate
        BigDecimal totalPrcAllocAmt = BigDecimal.ZERO;
        for(int i = 0; i < dsContrPrcAllocTMsgList.getValidCount(); i++){
            totalPrcAllocAmt = totalPrcAllocAmt.add(dsContrPrcAllocTMsgList.no(i).prcAllocAmt.getValue());
        }
        // Set Create Price Allocation
        boolean amtDiffFlg = false;
        if (totalPrcAllocAmt.compareTo(basePrcDealAmt) != 0) {
            amtDiffFlg = true;
        }
        if (amtDiffFlg) {
            List<DS_CONTR_SEG_ALLOCTMsg> dsContrPrcAllocTMsgListForCreate = new ArrayList<DS_CONTR_SEG_ALLOCTMsg>(dsContrPrcAllocTMsgList.getValidCount());
            for (int i = 0; i < dsContrPrcAllocTMsgList.getValidCount(); i++) {
                DS_CONTR_SEG_ALLOCTMsg prcMsg = dsContrPrcAllocTMsgList.no(i);
                DS_CONTR_SEG_ALLOCTMsg createPrcMsg = new DS_CONTR_SEG_ALLOCTMsg();
                EZDMsg.copy(prcMsg, null, createPrcMsg, null);
                ZYPEZDItemValueSetter.setValue(createPrcMsg.prcAllocAmt, calcNewPrcAllocAmt(basePrcDealAmt, totalPrcAllocAmt, dsContrPrcAllocTMsgList.no(i).prcAllocAmt.getValue(), digitNum));
                dsContrPrcAllocTMsgListForCreate.add(createPrcMsg);
            }
            adjustPrcAllocAmt(dsContrPrcAllocTMsgListForCreate, basePrcDealAmt);
            // Call API(NSZC0800)
            return callApi(glblCmpyCd, slsDt, resrcObjNm, dsContrPrcAllocTMsgListForCreate, onBatchType);
        }
        return true;
    }

    /**
     * @param glblCmpyCd
     * @param slsDt
     * @param dsContrPk
     * @param svcInvChrgTpCd
     * @param resrcObjNm
     * @param rate
     * @param befDsContrEffPk
     * @param aftDsContrEffPk
     * @param onBatchType
     * @return
     */
    private static boolean mandatoryCheck(String glblCmpyCd, String slsDt, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal basePrcDealAmt, String svcInvChrgTpCd, String resrcObjNm, final ONBATCH_TYPE onBatchType) {
        if (glblCmpyCd == null) {
            return false;
        }
        if (slsDt == null) {
            return false;
        }
        if (dsContrPk == null) {
            return false;
        }
        if (dsContrDtlPk == null) {
            return false;
        }
        if (basePrcDealAmt == null) {
            return false;
        }
        if (resrcObjNm == null) {
            return false;
        }
        if (onBatchType == null) {
            return false;
        }
        return true;
    }

    /**
     * @param glblCmpyCd
     * @param dsContrPk
     * @return DS_CONTRTMsg
     */
    private static BigDecimal getDigitNum(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = getDsContr(glblCmpyCd, dsContrPk);
        if (tMsg == null) {
            return null;
        }
        CCYTMsg ccyTMsg = getCcy(glblCmpyCd, tMsg.ccyCd.getValue());
        if (ccyTMsg == null) {
            return null;
        }
        return ccyTMsg.aftDeclPntDigitNum.getValue();
    }

    /**
     * @param glblCmpyCd
     * @param dsContrPk
     * @param dsContrDtlPk
     * @param svcInvChrgTpCd
     * @return List<DS_CONTR_SEG_ALLOCTMsg>
     */
    private static DS_CONTR_SEG_ALLOCTMsgArray getDsContrPrcAlloc(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String svcInvChrgTpCd) {

        DS_CONTR_SEG_ALLOCTMsg inMsg = new DS_CONTR_SEG_ALLOCTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPk01", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk) && ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            inMsg.setSQLID("001");
            inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            inMsg.setConditionValue("svcInvChrgTpCd01", svcInvChrgTpCd);
        } else if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        } else {
            inMsg.setSQLID("003");
        }

        DS_CONTR_SEG_ALLOCTMsgArray tMsgArray = (DS_CONTR_SEG_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray;

    }

    /**
     * @param glblCmpyCd
     * @param slsDt
     * @param resrcObjNm
     * @param tMsgList
     * @param onBatchType
     */
    private static boolean callApi(String glblCmpyCd, String slsDt, String resrcObjNm, List<DS_CONTR_SEG_ALLOCTMsg> tMsgList, final ONBATCH_TYPE onBatchType) {
        NSZC080001PMsg pMsg = new NSZC080001PMsg();
        NSZC080001 api = new NSZC080001();

        for (int i = 0; i < tMsgList.size(); i++) {
            DS_CONTR_SEG_ALLOCTMsg prcMsg = tMsgList.get(i);
            NSZC080001_segmentsListPMsg price = pMsg.segmentsList.no(i);
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, prcMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, prcMsg.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(pMsg.svcInvChrgTpCd, prcMsg.svcInvChrgTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.resrcObjNm, resrcObjNm);
            ZYPEZDItemValueSetter.setValue(price.coaCmpyCd, prcMsg.coaCmpyCd);
            ZYPEZDItemValueSetter.setValue(price.coaAfflCd, prcMsg.coaAfflCd);
            ZYPEZDItemValueSetter.setValue(price.coaBrCd, prcMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(price.coaChCd, prcMsg.coaChCd);
            ZYPEZDItemValueSetter.setValue(price.coaCcCd, prcMsg.coaCcCd);
            ZYPEZDItemValueSetter.setValue(price.coaAcctCd, prcMsg.coaAcctCd);
            ZYPEZDItemValueSetter.setValue(price.coaProdCd, prcMsg.coaProdCd);
            ZYPEZDItemValueSetter.setValue(price.coaProjCd, prcMsg.coaProjCd);
            ZYPEZDItemValueSetter.setValue(price.coaExtnCd, prcMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(price.prcAllocRate, prcMsg.prcAllocRate);
            ZYPEZDItemValueSetter.setValue(price.prcAllocAmt, prcMsg.prcAllocAmt);
        }
        pMsg.segmentsList.setValidCount(tMsgList.size());
        api.execute(pMsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    /**
     * @param prcAllocAmt
     * @param amtUpRate
     * @param digitNum
     * @return BigDecimal
     */
    private static BigDecimal calcNewPrcAllocAmt(BigDecimal basePrcDealAmt, BigDecimal totalPrcAllocAmt, BigDecimal prcAllocAmt, BigDecimal digitNum) {
        return basePrcDealAmt.multiply(prcAllocAmt).divide(totalPrcAllocAmt,digitNum.intValue(), BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @param dsContrPrcAllocTMsgListForCreate
     * @param basePrcDealAmt
     */
    private static void adjustPrcAllocAmt(List<DS_CONTR_SEG_ALLOCTMsg> dsContrPrcAllocTMsgListForCreate, BigDecimal basePrcDealAmt) {
        BigDecimal sumPrcAllocAmt = BigDecimal.ZERO;
        BigDecimal maxPrcAllocAmt = BigDecimal.ZERO;
        int maxIndex = 0;
        for (int i = 0; i < dsContrPrcAllocTMsgListForCreate.size(); i++) {
            DS_CONTR_SEG_ALLOCTMsg tMsg = dsContrPrcAllocTMsgListForCreate.get(i);
            sumPrcAllocAmt = sumPrcAllocAmt.add(tMsg.prcAllocAmt.getValue());
            if (maxPrcAllocAmt.compareTo(tMsg.prcAllocAmt.getValue()) < 0) {
                maxIndex = i;
                maxPrcAllocAmt = tMsg.prcAllocAmt.getValue();
            }
        }
        if (basePrcDealAmt.compareTo(sumPrcAllocAmt) == 0) {
            return;
        }
        BigDecimal adjustAmount = basePrcDealAmt.subtract(sumPrcAllocAmt);
        ZYPEZDItemValueSetter.setValue(dsContrPrcAllocTMsgListForCreate.get(maxIndex).prcAllocAmt, maxPrcAllocAmt.add(adjustAmount));
    }

    /**
     * @param glblCmpyCd
     * @param dsContrPk
     * @return DS_CONTRTMsg
     */
    private static DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * @param glblCmpyCd
     * @param ccyCd
     * @return DS_CONTRTMsg
     */
    private static CCYTMsg getCcy(String glblCmpyCd, String ccyCd) {
        CCYTMsg tMsg = new CCYTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.ccyCd, ccyCd);
        return (CCYTMsg) EZDTBLAccessor.findByKey(tMsg);
    }
}
