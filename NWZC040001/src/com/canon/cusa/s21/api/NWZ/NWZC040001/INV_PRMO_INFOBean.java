/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDTBigDecimalItem;
import business.db.INV_PRMO_INFOTMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040004PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * INV_PRMO_INFOBean
 * 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class INV_PRMO_INFOBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor for NWZC040004PMsg not exist
     * @param invPMsg NWZC040001PMsg
     */
    public INV_PRMO_INFOBean(NWZC040001PMsg invPMsg) {
        this.invPMsg = invPMsg;
        this.invPrmoInfoTMsg = new INV_PRMO_INFOTMsg();
        this.invPrmoInfoMsgMap = new S21ApiMessageMap(invPMsg);
    }

    /**
     * constructor for NWZC040004PMsg exist
     * @param invPMsg NWZC040001PMsg
     * @param invPrmoInfoPMsg NWZC040004PMsg
     */
    public INV_PRMO_INFOBean(NWZC040001PMsg invPMsg, NWZC040004PMsg invPrmoInfoPMsg) {
        this(invPMsg);
        this.invPrmoInfoPMsg = invPrmoInfoPMsg;
        EZDMsg.copy(invPrmoInfoPMsg, null, invPrmoInfoTMsg, null);
        defaultValueSetter(invPrmoInfoPMsg.prmoQty, this.invPrmoInfoTMsg.prmoQty);
        defaultValueSetter(invPrmoInfoPMsg.dealUnitPrcAmt, this.invPrmoInfoTMsg.dealUnitPrcAmt);
        defaultValueSetter(invPrmoInfoPMsg.dealLastNetUnitPrcAmt, this.invPrmoInfoTMsg.dealLastNetUnitPrcAmt);
        defaultValueSetter(invPrmoInfoPMsg.dealNetAmt, this.invPrmoInfoTMsg.dealNetAmt);
        defaultValueSetter(invPrmoInfoPMsg.dealDiscAmt, this.invPrmoInfoTMsg.dealDiscAmt);
        defaultValueSetter(invPrmoInfoPMsg.dealPrmoNetUnitPrcAmt, this.invPrmoInfoTMsg.dealPrmoNetUnitPrcAmt);
        defaultValueSetter(invPrmoInfoPMsg.dealPerUnitFixAmt, this.invPrmoInfoTMsg.dealPerUnitFixAmt);
        defaultValueSetter(invPrmoInfoPMsg.dealSlsPctNum, this.invPrmoInfoTMsg.dealSlsPctNum);
        defaultValueSetter(invPrmoInfoPMsg.funcPerUnitFixAmt, this.invPrmoInfoTMsg.funcPerUnitFixAmt);
        defaultValueSetter(invPrmoInfoPMsg.funcUnitPrcAmt, this.invPrmoInfoTMsg.funcUnitPrcAmt);
        defaultValueSetter(invPrmoInfoPMsg.funcLastNetUnitPrcAmt, this.invPrmoInfoTMsg.funcLastNetUnitPrcAmt);
        defaultValueSetter(invPrmoInfoPMsg.funcNetAmt, this.invPrmoInfoTMsg.funcNetAmt);
        defaultValueSetter(invPrmoInfoPMsg.funcDiscAmt, this.invPrmoInfoTMsg.funcDiscAmt);
        defaultValueSetter(invPrmoInfoPMsg.funcPrmoNetUnitPrcAmt, this.invPrmoInfoTMsg.funcPrmoNetUnitPrcAmt);
        this.invPrmoInfoMsgMap = new S21ApiMessageMap(invPrmoInfoPMsg);
    }

    /** NWZC040001PMsg */
    private NWZC040001PMsg invPMsg;

    /** NWZC040004PMsg */
    private NWZC040004PMsg invPrmoInfoPMsg;

    /** INV_PRMO_INFOTMsg */
    private INV_PRMO_INFOTMsg invPrmoInfoTMsg;

    /** S21ApiMessageMap */
    private S21ApiMessageMap invPrmoInfoMsgMap;

    /**
     * @return NWZC040001PMsg
     */
    public NWZC040001PMsg getNWZC040001PMsg() {
        return this.invPMsg;
    }

    /**
     * @return invPrmoInfoTMsg
     */
    public INV_PRMO_INFOTMsg getInvPrmoInfoTMsg() {
        return invPrmoInfoTMsg;
    }

    /**
     * addXxMsgId
     * @param xxMsgId MessageID
     */
    public void addXxMsgId(String xxMsgId) {
        invPrmoInfoMsgMap.addXxMsgId(xxMsgId);
    }

    /**
     * setInvNum
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invPrmoInfoTMsg.invNum.setValue(invNum);
    }

    /**
     * @return NWZC040004PMsg
     */
    public NWZC040004PMsg getNWZC040004PMsg() {
        return this.invPrmoInfoPMsg;
    }

    /**
     * flushAllMessageMaps
     */
    public void flushAllMessageMap() {
        if (this.invPrmoInfoMsgMap != null) {
            this.invPrmoInfoMsgMap.flush();
        }
    }

    private void defaultValueSetter(EZDPBigDecimalItem pBigDecimalItem, EZDTBigDecimalItem tBigDecimalItem) {
        if (!ZYPCommonFunc.hasValue(pBigDecimalItem)) {
            tBigDecimalItem.setValue(BigDecimal.ZERO);
        }
    }
}
