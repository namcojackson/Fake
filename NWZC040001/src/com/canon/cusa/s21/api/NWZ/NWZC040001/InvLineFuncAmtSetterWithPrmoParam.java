/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.db.INVTMsg;
import business.db.INV_LINETMsg;
import business.db.INV_PRMO_INFOTMsg;

/**
 * <pre>
 * InvLineFuncAmtSetterWithPrmoParam
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class InvLineFuncAmtSetterWithPrmoParam implements InvLineFuncAmtSetter {

    /**
     * copyInvLineDealAmtToFuncAmt
     * @param invLineBean INV_LINEBean
     */
    public void copyInvLineDealAmtToFuncAmt(INV_LINEBean invLineBean) {

        copyInvPrmoInfoToInvLine(invLineBean);

        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        invLineTMsg.invLineFuncTaxAmt.setValue(invLineTMsg.invLineDealTaxAmt.getValue());
    }

    /**
     * getInvLineExchangeAmountData
     * @param invLineBean INV_LINEBean
     * @return InvLineExchangeAmountData
     */
    public InvLineExchangeAmountData getInvLineExchangeAmountData(INV_LINEBean invLineBean) {

        copyInvPrmoInfoToInvLine(invLineBean);

        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        InvLineExchangeAmountData invLineExchAmtData = new InvLineExchangeAmountData();
        invLineExchAmtData.setInvLineDealTaxAmt(invLineTMsg.invLineDealTaxAmt.getValue());

        return invLineExchAmtData;
    }

    /**
     * setInvLineFuncAmt
     * @param invLineBean INV_LINEBean
     * @param invLineExchAmtData InvLineExchangeAmountData
     */
    public void setInvLineFuncAmt(INVBean invBean, INVTMsg invTMsg, INV_LINEBean invLineBean, InvLineExchangeAmountData invLineExchAmtData) {
        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        if (BigDecimal.ZERO.compareTo(invLineTMsg.invLineFuncTaxAmt.getValue())==0) {
            invLineTMsg.invLineFuncTaxAmt.setValue(invLineExchAmtData.getInvLineFuncTaxAmt());

        } else if (BigDecimal.ZERO.compareTo(invLineTMsg.invLineDealTaxAmt.getValue())==0) {
            ZYPEZDItemValueSetter.setValue(invLineTMsg.invLineDealTaxAmt, NWZC040001.getDealAmount(invBean, invTMsg, invLineTMsg.invLineFuncTaxAmt.getValue()));
        }
    }

    private void copyInvPrmoInfoToInvLine(INV_LINEBean invLineBean) {
        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
        INV_PRMO_INFOTMsg firstInvPrmoInfoTmsg = invLineBean.getInvPrmoInfoList().get(0).getInvPrmoInfoTMsg();

        invLineTMsg.funcNetUnitPrcAmt.setValue(firstInvPrmoInfoTmsg.funcLastNetUnitPrcAmt.getValue());
        invLineTMsg.invLineFuncNetAmt.setValue(firstInvPrmoInfoTmsg.funcNetAmt.getValue());
        invLineTMsg.funcGrsUnitPrcAmt.setValue(firstInvPrmoInfoTmsg.funcUnitPrcAmt.getValue());
        invLineTMsg.funcGrsTotPrcAmt.setValue(firstInvPrmoInfoTmsg.funcUnitPrcAmt.getValue().multiply(firstInvPrmoInfoTmsg.prmoQty.getValue()));
//        invLineTMsg.funcGrsTotPrcAmt.setValue(firstInvPrmoInfoTmsg.funcNetAmt.getValue());

        List<INV_PRMO_INFOBean> invPrmoInfoList = invLineBean.getInvPrmoInfoList();
        BigDecimal funcDiscAmt = BigDecimal.ZERO;
        for (INV_PRMO_INFOBean invPrmoInfoBean : invPrmoInfoList) {
            INV_PRMO_INFOTMsg invPrmoInfoTmsg = invPrmoInfoBean.getInvPrmoInfoTMsg();
            funcDiscAmt = funcDiscAmt.add(invPrmoInfoTmsg.funcDiscAmt.getValue());
        }
        invLineTMsg.funcDiscUnitPrcAmt.setValue(funcDiscAmt);
    }
}
