/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.parts.NWZC036101PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/10/10   Hitachi         K.Kishimoto     Create          QC#61468
 *</pre>
 */
public class NSXC003001CallTaxCalcAPIForFreight {

    /** Mode : Distribute Tax */
    private static final String PROC_MODE_DISTRIBUTE_TAX = "D";

    /**
     * Call Tax Calc API For Base
     * @param apiBean CallTaxCalcAPIForFreightBean
     * @param onBatchType ONBATCH_TYPE
     * @return NWZC036101PMsg
     */
    public static NWZC036101PMsg callTaxCalcApi(CallTaxCalcAPIForFreightBean apiBean, final ONBATCH_TYPE onBatchType) {
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForFreight-callTaxCalcApi start");
        NWZC036101 taxApi = new NWZC036101();
        NWZC036101PMsg taxApiPmsg = setTaxCalcApiParams(apiBean);
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForFreight-callTaxCalcApi-callNWZC0361 start");
        taxApi.execute(taxApiPmsg, onBatchType);
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForFreight-callTaxCalcApi-callNWZC0361 end");
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForFreight-callTaxCalcApi end");
        return taxApiPmsg;
    }

    private static NWZC036101PMsg setTaxCalcApiParams(CallTaxCalcAPIForFreightBean apiBean) {
        NWZC036101PMsg taxApiPMsg = new NWZC036101PMsg();

        // Header Parameter
        setValue(taxApiPMsg.glblCmpyCd, apiBean.getGlblCmpyCd());
        setValue(taxApiPMsg.slsDt, apiBean.getSlsDt());
        setValue(taxApiPMsg.xxModeCd, apiBean.getXxProcMd());
        NSXC003001CallTaxCalcAPICommon.setBillToInfoParams(taxApiPMsg, apiBean.getBaseBillToCustCd());
        NSXC003001CallTaxCalcAPICommon.setShipToInfoParams(taxApiPMsg, apiBean.getDsContrDtlTpCd(), apiBean.getSvcMachMstrPk(), apiBean.getDsAcctNum(), apiBean.getBaseBillToCustCd(), apiBean.getDsContrPk(), apiBean.getLogOutputFlg(), apiBean.getOrigSvcInvNum());
        NSXC003001CallTaxCalcAPICommon.setSalesRepInfoParams(taxApiPMsg, apiBean.getDsContrPk(), apiBean.getNextBllgDt());
        NSXC003001CallTaxCalcAPICommon.setDsInvInfoParams(taxApiPMsg, apiBean.getDsContrPk());
        NSXC003001CallTaxCalcAPICommon.setTrxDateInfoParams(taxApiPMsg, apiBean.getOrigSvcInvNum());
        if (!hasValue(taxApiPMsg.trxDt.getValue())) {
            setValue(taxApiPMsg.trxDt, apiBean.getNextBllgDt());
        }
        setValue(taxApiPMsg.xxTaxCalcHdrNum, apiBean.getDsContrBllgSchdPk().toString());
        if (hasValue(apiBean.getSvcInvNum())) {
            setValue(taxApiPMsg.xxTaxCalcHdrNum, apiBean.getSvcInvNum());
        }
        // Line Parameter
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).xxTaxCalcLineNum_A, apiBean.getDsContrBllgSchdPk().toString());
        NSXC003001CallTaxCalcAPICommon.setDsMdseInfoParams(taxApiPMsg.taxCalculateInputLine.no(0), apiBean.getGlblCmpyCd(), apiBean.getMdseCd());
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).shipQty_A, BigDecimal.ONE);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).funcNetUnitPrcAmt_A, apiBean.getBaseFuncAmt());
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).slsAmt_A, apiBean.getBaseFuncAmt());
        if (hasValue(apiBean.getInvTp()) && INV_TP.CREDIT_MEMO.equals(apiBean.getInvTp())) {
            setValue(taxApiPMsg.taxCalculateInputLine.no(0).taxAmt_A, apiBean.getFuncTaxAmt());
        }
        setValue(taxApiPMsg.invTpCd, apiBean.getInvTp());
        NSXC003001CallTaxCalcAPICommon.setOrigSvcInvParams(taxApiPMsg, apiBean.getInvTp(), apiBean.getOrigSvcInvNum(), apiBean.getDsContrBllgSchdPk());
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).trxDt_A, taxApiPMsg.trxDt);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).billToAcctNum_A, taxApiPMsg.billToAcctNum);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).billToLocNum_A, taxApiPMsg.billToLocNum);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).shipToLocNum_A, taxApiPMsg.shipToLocNum);
        if (!PROC_MODE_DISTRIBUTE_TAX.equals(apiBean.getXxProcMd())) {
            setValue(taxApiPMsg.dsAcctNum_SE, apiBean.getDsAcctNum());
        }

        EZDMsg.copy(taxApiPMsg, "B", taxApiPMsg.taxCalculateInputLine.no(0), "AB");
        EZDMsg.copy(taxApiPMsg, "ST", taxApiPMsg.taxCalculateInputLine.no(0), "AT");
        EZDMsg.copy(taxApiPMsg, "SR", taxApiPMsg.taxCalculateInputLine.no(0), "AR");

        taxApiPMsg.taxCalculateInputLine.setValidCount(1);
        return taxApiPMsg;
    }

    private static void s21InfoLogOutputPrintln(String logOutputFlg, String message) {
        if (logOutputFlg != null && logOutputFlg.equals(ZYPConstant.FLG_ON_Y)) {
            S21InfoLogOutput.println("[Billing Calculation Log]" + message);
        }
    }
}
