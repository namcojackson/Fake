/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import business.db.INV_BOLTMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * INV_BOLBean
 * 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * 
 * </pre>
 */
public class INV_BOLBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor
     * @param invPMsg NWZC040001PMsg
     * @param invBolPMsg NWZC040002PMsg
     */
    public INV_BOLBean(NWZC040001PMsg invPMsg, NWZC040002PMsg invBolPMsg) {
        this.invPMsg = invPMsg;
        this.invBolPMsg = invBolPMsg;
        this.invBolTMsg = new INV_BOLTMsg();
        
        EZDMsg.copy(invBolPMsg, null, this.invBolTMsg, null);

        defaultFlgSetter(invBolPMsg.dropShipFlg, this.invBolTMsg.dropShipFlg);
        defaultFlgSetter(invBolPMsg.exptFlg, this.invBolTMsg.exptFlg);

        defaultValueSetter(invBolPMsg.shipDealSlsAmt, this.invBolTMsg.shipDealSlsAmt);
        defaultValueSetter(invBolPMsg.shipDealNetAmt, this.invBolTMsg.shipDealNetAmt);
        defaultValueSetter(invBolPMsg.shipDealFrtAmt, this.invBolTMsg.shipDealFrtAmt);
        defaultValueSetter(invBolPMsg.shipDealDiscAmt, this.invBolTMsg.shipDealDiscAmt);
        defaultValueSetter(invBolPMsg.shipDealHdlgChrgAmt, this.invBolTMsg.shipDealHdlgChrgAmt);
        defaultValueSetter(invBolPMsg.totBolDealTaxAmt, this.invBolTMsg.totBolDealTaxAmt);
        defaultValueSetter(invBolPMsg.frtTaxPct, this.invBolTMsg.frtTaxPct);
        defaultValueSetter(invBolPMsg.frtDealTaxAmt, this.invBolTMsg.frtDealTaxAmt);

        defaultValueSetter(invBolPMsg.shipFuncSlsAmt, this.invBolTMsg.shipFuncSlsAmt);
        defaultValueSetter(invBolPMsg.shipFuncNetAmt, this.invBolTMsg.shipFuncNetAmt);
        defaultValueSetter(invBolPMsg.shipFuncFrtAmt, this.invBolTMsg.shipFuncFrtAmt);
        defaultValueSetter(invBolPMsg.shipFuncDiscAmt, this.invBolTMsg.shipFuncDiscAmt);
        defaultValueSetter(invBolPMsg.shipFuncHdlgChrgAmt, this.invBolTMsg.shipFuncHdlgChrgAmt);
        defaultValueSetter(invBolPMsg.totBolFuncTaxAmt, this.invBolTMsg.totBolFuncTaxAmt);
        defaultValueSetter(invBolPMsg.frtFuncTaxAmt, this.invBolTMsg.frtFuncTaxAmt);

        this.invBolMsgMap = new S21ApiMessageMap(invBolPMsg);
        this.invLineList = new ArrayList<INV_LINEBean>();
    }

    /** NWZC040001PMsg */
    private NWZC040001PMsg invPMsg;

    /** NWZC040002PMsg */
    private NWZC040002PMsg invBolPMsg;

    /** INV_BOLTMsg */
    private INV_BOLTMsg invBolTMsg;

    /** S21ApiMessageMap */
    private S21ApiMessageMap invBolMsgMap;

    /** List<INV_LINEBean> */
    private List<INV_LINEBean> invLineList;

    /**
     * @return NWZC040001PMsg
     */
    public NWZC040001PMsg getNWZC040001PMsg() {
        return this.invPMsg;
    }

    /**
     * @return invBolTMsg
     */
    public INV_BOLTMsg getInvBolTMsg() {
        return invBolTMsg;
    }

    /**
     * @return invLineList
     */
    public List<INV_LINEBean> getInvLineList() {
        return invLineList;
    }

    /**
     * addXxMsgId
     * @param xxMsgId MessageID
     */
    public void addXxMsgId(String xxMsgId) {
        this.invBolMsgMap.addXxMsgId(xxMsgId);
    }


    /**
     * addInvLine
     * @param invLineBean INV_LINEBean
     */
    public void addInvLine(INV_LINEBean invLineBean) {
        this.invLineList.add(invLineBean);
    }

    /** 
     * setInvNum
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invBolTMsg.invNum.setValue(invNum);
        for (INV_LINEBean invLineBean : invLineList) {
            invLineBean.setInvNum(invNum);
        }
    }

    /**
     * @return NWZC040002PMsg
     */
    public NWZC040002PMsg getNWZC040002PMsg() {
        return this.invBolPMsg;
    }

    /**
     * flushAllMessageMaps
     */
    public void flushAllMessageMap() {
        this.invBolMsgMap.flush();
        for (INV_LINEBean invLineBean : invLineList) {
            invLineBean.flushAllMessageMap();
        }
    }

    /**
     * addAllInvLineList
     * @param allInvLineList List<INV_LINEBean>
     */
    public void addAllInvLineList(List<INV_LINEBean> allInvLineList) {
        allInvLineList.addAll(this.invLineList);
    }

    private void defaultFlgSetter(EZDPStringItem pFlg, EZDTStringItem tFlg) {
        if (!ZYPCommonFunc.hasValue(pFlg)) {
            tFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
    }

    private void defaultValueSetter(EZDPBigDecimalItem pBigDecimalItem, EZDTBigDecimalItem tBigDecimalItem) {
        if (!ZYPCommonFunc.hasValue(pBigDecimalItem)) {
            tBigDecimalItem.setValue(BigDecimal.ZERO);
        }
    }
}
