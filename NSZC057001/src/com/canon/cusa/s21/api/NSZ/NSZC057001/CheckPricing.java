/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2015/12/01   Hitachi         A.Kohinata      Update          QC1312
 * 2015/12/07   Hitachi         K.Yamada        Update          CSA QC#1417
 * </pre>
 */
public class CheckPricing {

    /**
     * checkNegativeInvoiceAmount
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkNegativeInvoiceAmount(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // add start 2015/12/07 CSA Defect#1417
            if (isUnderFleet(dsContrTMsg, dsContrDtlTMsg)) {
                continue;
            }
            // add end 2015/12/07 CSA Defect#1417

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            DS_CONTR_BLLG_SCHDTMsgArray dsContrBllgSchdTMsgArray = getDsContrBllgSchd(mainClass, param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());

            for (int j = 0; j < dsContrBllgSchdTMsgArray.getValidCount(); j++) {
                DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = dsContrBllgSchdTMsgArray.no(j);
                if (ZYPCommonFunc.hasValue(dsContrBllgSchdTMsg.baseActlPrcDealAmt) && dsContrBllgSchdTMsg.baseActlPrcDealAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0770E, new String[] {"Base Actual Price Deal Amount" }), DS_CONTR_BLLG_SCHD_PK, dsContrBllgSchdTMsg.dsContrBllgSchdPk.getValue());
                    break;
                }
                if (ZYPCommonFunc.hasValue(dsContrBllgSchdTMsg.mtrChrgDealAmt) && dsContrBllgSchdTMsg.mtrChrgDealAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0770E, new String[] {"Meter Charge Deal Amount" }), DS_CONTR_BLLG_SCHD_PK, dsContrBllgSchdTMsg.dsContrBllgSchdPk.getValue());
                    break;
                }
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
}
