/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import java.io.Serializable;

import parts.common.EZDMsg;

import business.db.INV_CASH_DISC_TERMTMsg;
import business.parts.NWZC040001PMsg;

/**
 * <pre>
 * INVBean
 * 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class INV_CASH_DISC_TERMBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor
     * @param invPMsg NWZC040001PMsg
     */
    public INV_CASH_DISC_TERMBean(NWZC040001PMsg invPMsg) {
        this.invCashDiscTermTMsg = new INV_CASH_DISC_TERMTMsg();
        EZDMsg.copy(invPMsg, null, this.invCashDiscTermTMsg, null);
    }

    /** INV_CASH_DISC_TERMTMsg */
    private INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg;

    /**
     * @return invCashDiscTermTMsg
     */
    public INV_CASH_DISC_TERMTMsg getInvCashDiscTermTMsg() {
        return invCashDiscTermTMsg;
    }

    /**
     * setInvNum
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invCashDiscTermTMsg.invNum.setValue(invNum);
    }

}
