/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.NSAM0031E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;

import business.parts.NSZC047001PMsg;
import business.parts.NSZC047002PMsg;
import business.parts.NSZC047003PMsg;
import business.parts.NSZC047004PMsg;
import business.parts.NSZC047005PMsg;
import business.parts.NSZC047006PMsg;
import business.parts.NSZC047007PMsg;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047009PMsg;
import business.parts.NSZC047010PMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC047021PMsg;
import business.parts.NSZC047022PMsg;
import business.parts.NSZC047099PMsg;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 * 08/02/2016   Hitachi         K.Kishimoto     Update          QC#4961
 * 10/11/2016   Hitachi         K.Kishimoto     Update          QC#14400
 * 01/04/2017   Hitachi         T.Mizuki        Update          QC#16399
 * 02/28/2017   Hitachi         K.Kishimoto     Update          QC#17809
 * 08/23/2017   Hitachi         K.Kasai         Update          QC#18639
 * 2017/10/02   Hitachi         A.Kohinata      Update          QC#21567
 * 2019/06/13   Hitachi         K.Kitachi       Update          QC#50811
 *</pre>
 */
public class NSZC047001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC047001() {
        super();
    }

    /**
     * @param params List<NSZC047001PMsg>
     * @param onBatchType final
     */
    public void executeMode01(List<NSZC047001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NSZC047001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * execute Process Mode:01(Contract)
     * @param pMsg NSZC047001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        BigDecimal dsContrPrcEffPk = null;
        if (pMsg.xxBaseLineList.getValidCount() > 0) {
            dsContrPrcEffPk = pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL.getValue();
        } else if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrPrcEffPk = pMsg.xxMtrLineList.no(0).dsContrPrcEffPk_ML.getValue();
        }

        if (!hasValue(dsContrPrcEffPk)) {
            M01CreateContractProcess createContractProcess = new M01CreateContractProcess(msgMap);
            createContractProcess.doProcess(msgMap, onBatchType);
        } else {
            M01UpdateContractProcess updateContractProcess = new M01UpdateContractProcess();
            updateContractProcess.doProcess(msgMap, onBatchType);
        }

        if (pMsg.xxBaseLineList.getValidCount() > 0) {
            if (!hasValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL)) {
                NSZC047001CommonLogic.updateTermAmtForDsContrDtl(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue());
            }
        }

        //START 2017/08/22 K.Kasai [QC#18639,ADD]
        // Adjust exess Copy Qty
        for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
            if (!NSZC047001CommonLogic.adjustXsCopyQty(pMsg.glblCmpyCd.getValue(), pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML.getValue(), pMsg.usgChrgFlg.getValue())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD_MTR" });
            }
        }
        //END 2017/08/22 K.Kasai [QC#18639,ADD]

        // add start 2017/10/02 QC#21567
        NSZC047001CommonLogic.updateNextBllgDt(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), pMsg.baseChrgFlg.getValue(), pMsg.usgChrgFlg.getValue());
        // add end 2017/10/02 QC#21567
        msgMap.flush();
    }

    /**
     * execute Process Mode:02(Top Schedule)
     * @param pMsg NSZC047002PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047002PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M02TopScheduleProcess topScheduleProcess = new M02TopScheduleProcess();
        topScheduleProcess.doProcess(msgMap, onBatchType);
        // add start 2017/10/02 QC#21567
        NSZC047001CommonLogic.updateNextBllgDt(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), pMsg.baseChrgFlg.getValue(), pMsg.usgChrgFlg.getValue());
        // add end 2017/10/02 QC#21567
        msgMap.flush();
    }

    /**
     * execute Process Mode:03(Skip Month)
     * @param pMsg NSZC047003PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047003PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M03SkipMonthProcess skipMonthProcess = new M03SkipMonthProcess();
        skipMonthProcess.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }

    /**
     * execute Process Mode:04(Renewal)
     * @param pMsg NSZC047004PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047004PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M04RenewalProcess renewalProcess = new M04RenewalProcess();
        renewalProcess.doProcess(msgMap, onBatchType);

        //START 2017/08/22 K.Kasai [QC#18639,ADD]
        // Adjust exess Copy Qty
        for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
            if (!NSZC047001CommonLogic.adjustXsCopyQty(pMsg.glblCmpyCd.getValue(), pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML.getValue(), pMsg.usgChrgFlg.getValue())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD_MTR" });
            }
        }
        //END 2017/08/22 K.Kasai [QC#18639,ADD]
        // add start 2017/10/02 QC#21567
        NSZC047001CommonLogic.updateNextBllgDt(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), pMsg.baseChrgFlg.getValue(), pMsg.usgChrgFlg.getValue());
        // add end 2017/10/02 QC#21567
        msgMap.flush();
    }

    /**
     * execute Process Mode:05(Escalation)
     * @param pMsg NSZC047005PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047005PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M05AnnualEscalationProcess annualEscalationProcess = new M05AnnualEscalationProcess();
        annualEscalationProcess.doProcess(msgMap, onBatchType);

        //START 2017/08/22 K.Kasai [QC#18639,ADD]
        // Adjust exess Copy Qty
        for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
            if (!NSZC047001CommonLogic.adjustXsCopyQty(pMsg.glblCmpyCd.getValue(), pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML.getValue(), pMsg.usgChrgFlg.getValue())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD_MTR" });
            }
        }
        //END 2017/08/22 K.Kasai [QC#18639,ADD]
        // add start 2017/10/02 QC#21567
        NSZC047001CommonLogic.updateNextBllgDt(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), pMsg.baseChrgFlg.getValue(), pMsg.usgChrgFlg.getValue());
        // add end 2017/10/02 QC#21567
        msgMap.flush();
    }

    /**
     * execute Process Mode:06(Termination)
     * @param pMsg NSZC047006PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047006PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M06TerminationProcess terminationProcess = new M06TerminationProcess();
        terminationProcess.doProcess(msgMap, onBatchType);
        // add start 2017/10/02 QC#21567
        NSZC047001CommonLogic.updateNextBllgDt(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), null, null);
        // add end 2017/10/02 QC#21567
        msgMap.flush();
    }

    /**
     * execute Process Mode:07(Credit&Rebill)
     * @param pMsg NSZC047007PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047007PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M07CreditAndRebillProcess creditAndRebillProcess = new M07CreditAndRebillProcess();
        creditAndRebillProcess.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }

    /**
     * @param params List<NSZC047001PMsg>
     * @param onBatchType final
     */
    public void executeMode08(List<NSZC047008PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NSZC047008PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * execute Process Mode:08(Price Effectivity)
     * @param pMsg NSZC047008PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047008PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M08PriceEffectivityProcess priceEffectivityProcess = new M08PriceEffectivityProcess();
        priceEffectivityProcess.doProcess(msgMap, onBatchType);

        //START 2017/08/22 K.Kasai [QC#18639,ADD]
        // Adjust exess Copy Qty
        for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
            if (!NSZC047001CommonLogic.adjustXsCopyQty(pMsg.glblCmpyCd.getValue(), pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML.getValue(), pMsg.usgChrgFlg.getValue())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD_MTR" });
            }
        }
        //END 2017/08/22 K.Kasai [QC#18639,ADD]
        // add start 2017/10/02 QC#21567
        NSZC047001CommonLogic.updateNextBllgDt(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), pMsg.baseChrgFlg.getValue(), pMsg.usgChrgFlg.getValue());
        // add end 2017/10/02 QC#21567
        msgMap.flush();
    }

    /**
     * execute Process Mode:09(Cancel)
     * @param pMsg NSZC047009PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047009PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M09CancelProcess cancelProcess = new M09CancelProcess();
        cancelProcess.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }

	// Add Start 08/02/2016 <QC#4961>
    /**
     * execute Process Mode:21(calc Term Amount Rate)
     * @param pMsg NSZC047021PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047021PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M21CalcTermAmtRateProcess CalcTermAmtRatelProcess = new M21CalcTermAmtRateProcess();
        CalcTermAmtRatelProcess.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }
	// Add End   08/02/2016 <QC#4961>

    // Add Start 10/11/2016 <QC#14400>
    /**
     * execute Process Mode:22(Reset Service Contract Billing)
     * @param pMsg NSZC047009PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047022PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M22ResetSvcContrBllgProcess resetSvcContrBllgProc = new M22ResetSvcContrBllgProcess();
        resetSvcContrBllgProc.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }
    // Add End   10/11/2016 <QC#14400>

    // add start 2017/01/04 CSA QC#16399
    /**
     * execute Process Mode:10(Delete)
     * @param pMsg NSZC047010PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047010PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M10DeleteProcess cancelProcess = new M10DeleteProcess();
        cancelProcess.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }
    // add end 2017/01/04 CSA QC#16399
    // Add Start 02/28/2017 <QC#17809>
    /**
     * execute Process Mode:11(Sum Aggregate Term Amount)
     * @param pMsg NSZC047010PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047011PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M11SumAggregateProcess sumAggregateProcess = new M11SumAggregateProcess();
        sumAggregateProcess.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }
    // Add End   02/28/2017 <QC#17809>

    // START 2019/06/13 K.Kitachi [QC#50811, ADD]
    /**
     * execute Process Mode:99(Recovery Accessory Schedule Relation)
     * @param pMsg NSZC047099PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC047099PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        M99RecovAccSchdRelnProcess recovAccSchdRelnProcess = new M99RecovAccSchdRelnProcess();
        recovAccSchdRelnProcess.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }
    // END 2019/06/13 K.Kitachi [QC#50811, ADD]
}
