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
 * 10/09/2015   Hitachi         T.Tomita        Create          N/A
 * 01/04/2016   Hitachi         T.Kanasaka      Update          N/A
 * 03/18/2016   Hitachi         T.Kanasaka      Update          QC#5719
 * 03/25/2016   Hitachi         K.Kishimoto     Update          QC#5879
 * 09/27/2016   Hitachi         T.Kanasaka      Update          QC#9905
 * 02/28/2017   Hitachi         N.Arai          Update          QC#17748
 * 08/21/2017   Hitachi         M.Kidokoro      Update          QC#20073
 * 10/16/2017   Hitachi         U.Kim           Update          QC#21584
 * 12/26/2017   Hitachi         K.Kishimoto     Update          QC#22407
 * 2018/07/25   Hitachi         A.Kohinata      Update          QC#27329
 * 2018/09/10   Hitachi         K.Kojima        Update          QC#28107
 *</pre>
 */
public class NSXC003001CallTaxCalcAPIForUsage {

    /** Mode : Distribute Tax */
    private static final String PROC_MODE_DISTRIBUTE_TAX = "D";

    /**
     * Call Tax Calc API For Usage
     * @param apiBean CallTaxCalcAPIForUsageBean
     * @param onBatchType ONBATCH_TYPE
     * @return NWZC036101PMsg
     */
    public static NWZC036101PMsg callTaxCalcApi(CallTaxCalcAPIForUsageBean apiBean, final ONBATCH_TYPE onBatchType) {
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForUsage-callTaxCalcApi start");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        NWZC036101 taxApi = new NWZC036101();
        NWZC036101PMsg taxApiPmsg = setTaxCalcApiParams(apiBean);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForUsage-callTaxCalcApi-callNWZC0361 start");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        taxApi.execute(taxApiPmsg, onBatchType);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForUsage-callTaxCalcApi-callNWZC0361 end");
        s21InfoLogOutputPrintln(apiBean.getLogOutputFlg(), "NSXC003001CallTaxCalcAPIForUsage-callTaxCalcApi end");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        return taxApiPmsg;
    }

    private static NWZC036101PMsg setTaxCalcApiParams(CallTaxCalcAPIForUsageBean apiBean) {
        NWZC036101PMsg taxApiPMsg = new NWZC036101PMsg();

        // Header Parameter
        setValue(taxApiPMsg.glblCmpyCd, apiBean.getGlblCmpyCd());
        setValue(taxApiPMsg.slsDt, apiBean.getSlsDt());
        setValue(taxApiPMsg.xxModeCd, apiBean.getXxProcMd());
        // START 2016/03/18 T.Kanasaka [QC#5719, MOD]
        // NSXC003001CallTaxCalcAPICommon.setBillToInfoParams(taxApiPMsg,
        // apiBean.getDsAcctNum());
        // NSXC003001CallTaxCalcAPICommon.setShipToInfoParams(taxApiPMsg,
        // apiBean.getDsContrDtlTpCd(), apiBean.getSvcMachMstrPk(),
        // apiBean.getDsAcctNum());
        NSXC003001CallTaxCalcAPICommon.setBillToInfoParams(taxApiPMsg, apiBean.getBaseBillToCustCd());
        // START 2016/09/27 T.Kanasaka [QC#9905, MOD]
//        NSXC003001CallTaxCalcAPICommon.setShipToInfoParams(taxApiPMsg, apiBean.getDsContrDtlTpCd(), apiBean.getSvcMachMstrPk(), apiBean.getDsAcctNum(), apiBean.getBaseBillToCustCd());
        // START 2018/09/10 K.Kojima [QC#28107,MOD]
        // NSXC003001CallTaxCalcAPICommon.setShipToInfoParams(taxApiPMsg, apiBean.getDsContrDtlTpCd(), apiBean.getSvcMachMstrPk(), apiBean.getDsAcctNum(), apiBean.getBaseBillToCustCd(), apiBean.getDsContrPk());
        // START 2020/05/13 K.Sakurai [QC#56726, MOD]
        // NSXC003001CallTaxCalcAPICommon.setShipToInfoParams(taxApiPMsg, apiBean.getDsContrDtlTpCd(), apiBean.getSvcMachMstrPk(), apiBean.getDsAcctNum(), apiBean.getBaseBillToCustCd(), apiBean.getDsContrPk(), apiBean.getLogOutputFlg());
        NSXC003001CallTaxCalcAPICommon.setShipToInfoParams(taxApiPMsg, apiBean.getDsContrDtlTpCd(), apiBean.getSvcMachMstrPk(), apiBean.getDsAcctNum(), apiBean.getBaseBillToCustCd(), apiBean.getDsContrPk(), apiBean.getLogOutputFlg(), apiBean.getOrigSvcInvNum());
        // END 2020/05/13 K.Sakurai [QC#56726, MOD]
        // END 2018/09/10 K.Kojima [QC#28107,MOD]
        // END 2016/09/27 T.Kanasaka [QC#9905, MOD]
        // END 2016/03/18 T.Kanasaka [QC#5719, MOD]
        // START 2017/02/28 N.Arai [QC#17748, ADD]
        NSXC003001CallTaxCalcAPICommon.setSalesRepInfoParams(taxApiPMsg, apiBean.getDsContrPk(), apiBean.getNextBllgDt());
        // END 2017/02/28 N.Arai [QC#17748, ADD]
        NSXC003001CallTaxCalcAPICommon.setDsInvInfoParams(taxApiPMsg, apiBean.getDsContrPk());
        // START 2017/10/16 U.Kim [QC#21584, MOD]
        //setValue(taxApiPMsg.trxDt, apiBean.getNextBllgDt());
        NSXC003001CallTaxCalcAPICommon.setTrxDateInfoParams(taxApiPMsg, apiBean.getOrigSvcInvNum());
        if (!hasValue(taxApiPMsg.trxDt.getValue())) {
            setValue(taxApiPMsg.trxDt, apiBean.getNextBllgDt());
        }
        // END 2017/10/16 U.Kim [QC#21584, MOD]
        setValue(taxApiPMsg.xxTaxCalcHdrNum, apiBean.getDsContrBllgSchdPk().toString());
        // START 2017/08/21 M.Kidokoro [QC#20073, ADD]
        if (hasValue(apiBean.getSvcInvNum())) {
            setValue(taxApiPMsg.xxTaxCalcHdrNum, apiBean.getSvcInvNum());
        }
        // END 2017/08/21 M.Kidokoro [QC#20073, ADD]
        // Line Parameter
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).xxTaxCalcLineNum_A, apiBean.getDsContrBllgSchdPk().toString());
        // Mod Start 03/25/2016 <QC#5879>
        NSXC003001CallTaxCalcAPICommon.setDsMdseInfoParams(taxApiPMsg.taxCalculateInputLine.no(0), apiBean.getGlblCmpyCd(), apiBean.getSvcPgmMdseCd(), apiBean.getmdseCdForSvcAllocTp());
        // Mod End 03/25/2016 <QC#5879>
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).shipQty_A, BigDecimal.ONE);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).funcNetUnitPrcAmt_A, apiBean.getBaseFuncAmt());
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).slsAmt_A, apiBean.getBaseFuncAmt());
        if (hasValue(apiBean.getInvTp()) && INV_TP.CREDIT_MEMO.equals(apiBean.getInvTp())) {
            setValue(taxApiPMsg.taxCalculateInputLine.no(0).taxAmt_A, apiBean.getFuncTaxAmt());
        }
        // Add Start 12/26/2017 <QC#22407>
        setValue(taxApiPMsg.invTpCd, apiBean.getInvTp());
        // Add End   12/26/2017 <QC#22407>
        // add start 2018/07/25 QC#27329
        NSXC003001CallTaxCalcAPICommon.setOrigSvcInvParams(taxApiPMsg, apiBean.getInvTp(), apiBean.getOrigSvcInvNum(), apiBean.getDsContrBllgSchdPk());
        // add end 2018/07/25 QC#27329
        // Set the same parameter as the header parameter
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).trxDt_A, taxApiPMsg.trxDt);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).billToAcctNum_A, taxApiPMsg.billToAcctNum);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).billToLocNum_A, taxApiPMsg.billToLocNum);
        setValue(taxApiPMsg.taxCalculateInputLine.no(0).shipToLocNum_A, taxApiPMsg.shipToLocNum);
        // Set Account Number
        if (!PROC_MODE_DISTRIBUTE_TAX.equals(apiBean.getXxProcMd())) {
            setValue(taxApiPMsg.dsAcctNum_SE, apiBean.getDsAcctNum());
        }

        EZDMsg.copy(taxApiPMsg, "B", taxApiPMsg.taxCalculateInputLine.no(0), "AB");
        EZDMsg.copy(taxApiPMsg, "ST", taxApiPMsg.taxCalculateInputLine.no(0), "AT");
        EZDMsg.copy(taxApiPMsg, "SR", taxApiPMsg.taxCalculateInputLine.no(0), "AR");

        taxApiPMsg.taxCalculateInputLine.setValidCount(1);
        return taxApiPMsg;
    }

    // START 2018/09/10 K.Kojima [QC#28107,ADD]
    private static void s21InfoLogOutputPrintln(String logOutputFlg, String message) {
        if (logOutputFlg != null && logOutputFlg.equals(ZYPConstant.FLG_ON_Y)) {
            S21InfoLogOutput.println("[Billing Calculation Log]" + message);
        }
    }
    // END 2018/09/10 K.Kojima [QC#28107,ADD]
}
