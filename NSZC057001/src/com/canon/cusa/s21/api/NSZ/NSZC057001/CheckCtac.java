/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.getDsContrBllgMtr;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.setValueTMsg;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/29   Hitachi         K.Kojima        Create          QC#17068
 * </pre>
 */
public class CheckCtac {

    /**
     * checkBillToCtacDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkBillToCtacDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!ZYPConstant.FLG_OFF_N.equals(dsContrTMsg.invSeptBaseUsgFlg.getValue())) {
            return rtrnTMsgArray;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);
            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }
            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                continue;
            }

            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            // base bill to contact
            BigDecimal baseBillToCtacPsnPk = null;
            if (ZYPCommonFunc.hasValue(dsContrDtlTMsg.ctacPsnPk)) {
                baseBillToCtacPsnPk = dsContrDtlTMsg.ctacPsnPk.getValue();
            } else if (ZYPCommonFunc.hasValue(dsContrTMsg.ctacPsnPk) && dsContrDtlTMsg.baseBillToCustCd.getValue().equals(dsContrTMsg.altPayerCustCd.getValue())) {
                baseBillToCtacPsnPk = dsContrTMsg.ctacPsnPk.getValue();
            }

            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                if (!dsContrDtlTMsg.baseBillToCustCd.getValue().equals(dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                    continue;
                }
                BigDecimal mtrBillToCtacPsnPk = null;
                if (ZYPCommonFunc.hasValue(dsContrBllgMtrTMsg.ctacPsnPk)) {
                    mtrBillToCtacPsnPk = dsContrBllgMtrTMsg.ctacPsnPk.getValue();
                } else if (ZYPCommonFunc.hasValue(dsContrDtlTMsg.ctacPsnPk) && dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue().equals(dsContrDtlTMsg.baseBillToCustCd.getValue())) {
                    mtrBillToCtacPsnPk = dsContrDtlTMsg.ctacPsnPk.getValue();
                } else if (ZYPCommonFunc.hasValue(dsContrTMsg.ctacPsnPk) && dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue().equals(dsContrTMsg.altPayerCustCd.getValue())) {
                    mtrBillToCtacPsnPk = dsContrTMsg.ctacPsnPk.getValue();
                }
                if (!ZYPCommonFunc.hasValue(baseBillToCtacPsnPk) && !ZYPCommonFunc.hasValue(mtrBillToCtacPsnPk)) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(baseBillToCtacPsnPk) && ZYPCommonFunc.hasValue(mtrBillToCtacPsnPk) && baseBillToCtacPsnPk.compareTo(mtrBillToCtacPsnPk) == 0) {
                    continue;
                }
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1342E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                break;
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
}
