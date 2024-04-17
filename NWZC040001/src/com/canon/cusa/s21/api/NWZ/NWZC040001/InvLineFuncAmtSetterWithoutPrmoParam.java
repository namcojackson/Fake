/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.db.INVTMsg;
import business.db.INV_LINETMsg;
import business.db.INV_PRMO_INFOTMsg;

/**
 * <pre>
 * InvLineFuncAmtSetterWithoutPrmoParam
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class InvLineFuncAmtSetterWithoutPrmoParam implements InvLineFuncAmtSetter {

    /**
     * copyInvLineDealAmtToFuncAmt
     * @param invLineBean INV_LINEBean
     */
    public void copyInvLineDealAmtToFuncAmt(INV_LINEBean invLineBean) {
        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        invLineTMsg.funcNetUnitPrcAmt.setValue(invLineTMsg.dealNetUnitPrcAmt.getValue());
        invLineTMsg.invLineFuncTaxAmt.setValue(invLineTMsg.invLineDealTaxAmt.getValue());
        invLineTMsg.invLineFuncNetAmt.setValue(invLineTMsg.invLineDealNetAmt.getValue());
        invLineTMsg.funcDiscUnitPrcAmt.setValue(invLineTMsg.dealDiscUnitPrcAmt.getValue());
        invLineTMsg.funcGrsUnitPrcAmt.setValue(invLineTMsg.dealGrsUnitPrcAmt.getValue());
        invLineTMsg.funcGrsTotPrcAmt.setValue(invLineTMsg.dealGrsTotPrcAmt.getValue());

        INV_PRMO_INFOBean invPrmoInfoBean = invLineBean.getInvPrmoInfoList().get(0);
        INV_PRMO_INFOTMsg invPrmoInfoTmsg = invPrmoInfoBean.getInvPrmoInfoTMsg();
        invPrmoInfoTmsg.funcUnitPrcAmt.setValue(invLineTMsg.funcNetUnitPrcAmt.getValue());
        invPrmoInfoTmsg.funcLastNetUnitPrcAmt.setValue(invLineTMsg.funcNetUnitPrcAmt.getValue());
        invPrmoInfoTmsg.funcNetAmt.setValue(invLineTMsg.invLineFuncNetAmt.getValue());
    }

    /**
     * getInvLineExchangeAmountData
     * @param invLineBean INV_LINEBean
     * @return InvLineExchangeAmountData
     */
    public InvLineExchangeAmountData getInvLineExchangeAmountData(INV_LINEBean invLineBean) {
        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        InvLineExchangeAmountData invLineExchAmtData = new InvLineExchangeAmountData();

        invLineExchAmtData.setDealNetUnitPrcAmt(invLineTMsg.dealNetUnitPrcAmt.getValue());
        invLineExchAmtData.setInvLineDealTaxAmt(invLineTMsg.invLineDealTaxAmt.getValue());
        invLineExchAmtData.setInvLineDealNetAmt(invLineTMsg.invLineDealNetAmt.getValue());
        invLineExchAmtData.setDealDiscUnitPrcAmt(invLineTMsg.dealDiscUnitPrcAmt.getValue());
        invLineExchAmtData.setDealGrsUnitPrcAmt(invLineTMsg.dealGrsUnitPrcAmt.getValue());
        invLineExchAmtData.setDealGrsTotPrcAmt(invLineTMsg.dealGrsTotPrcAmt.getValue());

        return invLineExchAmtData;
    }

    /**
     * setInvLineFuncAmt
     * @param invLineBean INV_LINEBean
     * @param invLineExchAmtData InvLineExchangeAmountData
     */
    public void setInvLineFuncAmt(INVBean invBean, INVTMsg invTMsg, INV_LINEBean invLineBean, InvLineExchangeAmountData invLineExchAmtData) {
        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        NWZC040001.zeroThenOverWrite(invLineTMsg.funcNetUnitPrcAmt, invLineExchAmtData.getFuncNetUnitPrcAmt());
        NWZC040001.zeroThenOverWrite(invLineTMsg.invLineFuncNetAmt, invLineExchAmtData.getInvLineFuncNetAmt());
        NWZC040001.zeroThenOverWrite(invLineTMsg.funcDiscUnitPrcAmt, invLineExchAmtData.getFuncDiscUnitPrcAmt());
        NWZC040001.zeroThenOverWrite(invLineTMsg.funcGrsUnitPrcAmt, invLineExchAmtData.getFuncGrsUnitPrcAmt());
        NWZC040001.zeroThenOverWrite(invLineTMsg.funcGrsTotPrcAmt, invLineExchAmtData.getFuncGrsTotPrcAmt());

        if (BigDecimal.ZERO.compareTo(invLineTMsg.invLineFuncTaxAmt.getValue())==0) {
            invLineTMsg.invLineFuncTaxAmt.setValue(invLineExchAmtData.getInvLineFuncTaxAmt());

        } else if (BigDecimal.ZERO.compareTo(invLineTMsg.invLineDealTaxAmt.getValue())==0) {
            ZYPEZDItemValueSetter.setValue(invLineTMsg.invLineDealTaxAmt, NWZC040001.getDealAmount(invBean, invTMsg, invLineTMsg.invLineFuncTaxAmt.getValue()));
        }

        INV_PRMO_INFOBean invPrmoInfoBean = invLineBean.getInvPrmoInfoList().get(0);
        INV_PRMO_INFOTMsg invPrmoInfoTmsg = invPrmoInfoBean.getInvPrmoInfoTMsg();
        invPrmoInfoTmsg.funcUnitPrcAmt.setValue(invLineTMsg.funcNetUnitPrcAmt.getValue());
        invPrmoInfoTmsg.funcLastNetUnitPrcAmt.setValue(invLineTMsg.funcNetUnitPrcAmt.getValue());
        invPrmoInfoTmsg.funcNetAmt.setValue(invLineTMsg.invLineFuncNetAmt.getValue());
    }

}
