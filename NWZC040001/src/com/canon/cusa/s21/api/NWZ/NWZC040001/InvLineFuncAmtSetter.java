/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import business.db.INVTMsg;

/**
 * <pre>
 * InvLineFuncAmtSetter
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public interface InvLineFuncAmtSetter {

    /**
     * copyInvLineDealAmtToFuncAmt
     * @param invLineBean INV_LINEBean
     */
    void copyInvLineDealAmtToFuncAmt(INV_LINEBean invLineBean);

    /**
     * getInvLineExchangeAmountData
     * @param invLineBean INV_LINEBean
     * @return InvLineExchangeAmountData
     */
    InvLineExchangeAmountData getInvLineExchangeAmountData(INV_LINEBean invLineBean);

    /**
     * setInvLineFuncAmt
     * @param invLineBean INV_LINEBean
     * @param invLineExchAmtData InvLineExchangeAmountData
     */
    void setInvLineFuncAmt(INVBean invBean, INVTMsg invTMsg, INV_LINEBean invLineBean, InvLineExchangeAmountData invLineExchAmtData);
}
