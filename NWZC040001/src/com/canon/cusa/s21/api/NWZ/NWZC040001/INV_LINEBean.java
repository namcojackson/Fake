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
import business.db.DS_INV_LINE_SER_NUMTMsg;
import business.db.DS_INV_LINE_TAX_DTLTMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.INVTMsg;
import business.db.INV_LINETMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040003PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_PROC_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * INV_LINEBean
 * 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * 2017/10/23   Fujitsu         T.Aoi           Update          QC#20719
 * 2018/08/21   Fujitsu         T.Aoi           Update          QC#27443
 * </pre>
 */
public class INV_LINEBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor
     * @param invPMsg NWZC040001PMsg
     * @param invLinePMsg NWZC040003PMsg
     */
    public INV_LINEBean(NWZC040001PMsg invPMsg, NWZC040003PMsg invLinePMsg) {
        this.invPMsg = invPMsg;
        this.invLinePMsg = invLinePMsg;
        this.invLineTMsg = new INV_LINETMsg();
        EZDMsg.copy(invLinePMsg, null, invLineTMsg, null);
        this.invLineTMsg.mercCntnFlg.setValue(ZYPConstant.FLG_OFF_N);

        this.invLineTMsg.origInvNum.setValue(invPMsg.origInvNum.getValue());

        defaultFlgSetter(invLinePMsg.manPrcFlg, this.invLineTMsg.manPrcFlg);
        defaultFlgSetter(invLinePMsg.taxFlg, this.invLineTMsg.taxFlg);
        defaultFlgSetter(invLinePMsg.setRatioKeepFlg, this.invLineTMsg.setRatioKeepFlg);

        defaultValueSetter(invLinePMsg.ordQty, this.invLineTMsg.ordQty);
        defaultValueSetter(invLinePMsg.shipQty, this.invLineTMsg.shipQty);
        defaultValueSetter(invLinePMsg.boQty, this.invLineTMsg.boQty);
        defaultValueSetter(invLinePMsg.dealDiscUnitPrcAmt, this.invLineTMsg.dealDiscUnitPrcAmt);
        defaultValueSetter(invLinePMsg.dealNetUnitPrcAmt, this.invLineTMsg.dealNetUnitPrcAmt);
        defaultValueSetter(invLinePMsg.invLineDealTaxAmt, this.invLineTMsg.invLineDealTaxAmt);
        defaultValueSetter(invLinePMsg.invLineDealNetAmt, this.invLineTMsg.invLineDealNetAmt);
        defaultValueSetter(invLinePMsg.taxPct, this.invLineTMsg.taxPct);
        //defaultValueSetter(invLinePMsg.shipCmplCostAmt, this.invLineTMsg.shipCmplCostAmt); // 2017/10/23 QC#20719 Del
        defaultValueSetter(invLinePMsg.dealGrsUnitPrcAmt, this.invLineTMsg.dealGrsUnitPrcAmt);
        defaultValueSetter(invLinePMsg.dealGrsTotPrcAmt, this.invLineTMsg.dealGrsTotPrcAmt);

        defaultValueSetter(invLinePMsg.funcDiscUnitPrcAmt, this.invLineTMsg.funcDiscUnitPrcAmt);
        defaultValueSetter(invLinePMsg.funcNetUnitPrcAmt, this.invLineTMsg.funcNetUnitPrcAmt);
        defaultValueSetter(invLinePMsg.invLineFuncTaxAmt, this.invLineTMsg.invLineFuncTaxAmt);
        defaultValueSetter(invLinePMsg.invLineFuncNetAmt, this.invLineTMsg.invLineFuncNetAmt);
        defaultValueSetter(invLinePMsg.funcGrsUnitPrcAmt, this.invLineTMsg.funcGrsUnitPrcAmt);
        defaultValueSetter(invLinePMsg.funcGrsTotPrcAmt, this.invLineTMsg.funcGrsTotPrcAmt);

        // CR_DR_RSN_CD
        this.invLineTMsg.crDrRsnCd.setValue( invPMsg.crDrRsnCd.getValue() );
        // CR_DR_RSN_SUB_CD
        this.invLineTMsg.crDrRsnSubCd.setValue( invLinePMsg.crDrRsnSubCd.getValue() );

        this.invLineTMsg.histCratCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        this.invLineTMsg.cpoOrdNum.setValue(invPMsg.cpoOrdNum.getValue());

        this.invLineMsgMap = new S21ApiMessageMap(invLinePMsg);
        this.invPrmoInfoList = new ArrayList<INV_PRMO_INFOBean>();

        this.dsInvLineTaxDtlTMsgList = new ArrayList<DS_INV_LINE_TAX_DTLTMsg>();
        this.dsInvLineSerNumTMsgList = new ArrayList<DS_INV_LINE_SER_NUMTMsg>();
        this.dsInvSlsCrTMsgList = new ArrayList<DS_INV_SLS_CRTMsg>();
        if (ZYPCommonFunc.hasValue(invLinePMsg.baseCmptFlg)) {
            invLineTMsg.baseCmptFlg.setValue(invLinePMsg.baseCmptFlg.getValue());
        } else {
        	invLineTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(invLinePMsg.csmpInvProcStsCd)) {
        	invLineTMsg.csmpInvProcStsCd.setValue(invLinePMsg.csmpInvProcStsCd.getValue());
        } else {
        	invLineTMsg.csmpInvProcStsCd.setValue(CSMP_INV_PROC_STS.NONE);
        }

        if (ZYPCommonFunc.hasValue(invLinePMsg.csmpContrNum)) {
        	invLineTMsg.csmpContrNum.setValue(invLinePMsg.csmpContrNum.getValue());
        }
        invLineTMsg.compProcStsCd.setValue("0");

        // 2018/08/21 QC#27443 Add Start
        defaultValueSetter(invLinePMsg.splyPgmShipQty, this.invLineTMsg.splyPgmShipQty);
        defaultValueSetter(invLinePMsg.splyPgmUnitAmtRate, this.invLineTMsg.splyPgmUnitAmtRate);
        // 2018/08/21 QC#27443 Add End
    }

    /** NWZC040001PMsg */
    private NWZC040001PMsg invPMsg;

    /** NWZC040003PMsg */
    private NWZC040003PMsg invLinePMsg;

    /** INV_LINETMsg */
    private INV_LINETMsg invLineTMsg;

    /** S21ApiMessageMap */
    private S21ApiMessageMap invLineMsgMap;

    /** List<INV_PRMO_INFOBean> */
    private List<INV_PRMO_INFOBean> invPrmoInfoList;

    /** InvLineFuncAmtSetter */
    private InvLineFuncAmtSetter invLineFuncAmtSetter;

    /** List<DS_INV_LINE_TAX_DTLTMsg> */
    private List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList;
    /** List<DS_INV_LINE_SER_NUMTMsg> */
    private List<DS_INV_LINE_SER_NUMTMsg> dsInvLineSerNumTMsgList;
    /** List<DS_INV_SLS_CRTMsg> */
    private List<DS_INV_SLS_CRTMsg> dsInvSlsCrTMsgList;

    /**
     * @return NWZC040001PMsg
     */
    public NWZC040001PMsg getNWZC040001PMsg() {
        return this.invPMsg;
    }

    /**
     * @return invLineTMsg
     */
    public INV_LINETMsg getInvLineTMsg() {
        return invLineTMsg;
    }

    /**
     * @return invPrmoInfoList
     */
    public List<INV_PRMO_INFOBean> getInvPrmoInfoList() {
        return invPrmoInfoList;
    }

    /**
     * addXxMsgId
     * @param xxMsgId MessageID
     */
    public void addXxMsgId(String xxMsgId) {
        this.invLineMsgMap.addXxMsgId(xxMsgId);
    }

    /**
     * addInvPrmoInfo
     * @param invPrmoInfoBean INV_PRMO_INFOBean
     */
    public void addInvPrmoInfo(INV_PRMO_INFOBean invPrmoInfoBean) {
        this.invPrmoInfoList.add(invPrmoInfoBean);
    }

    /**
     * setInvNum
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invLineTMsg.invNum.setValue(invNum);
        for (INV_PRMO_INFOBean invPrmoInfoBean : invPrmoInfoList) {
            invPrmoInfoBean.setInvNum(invNum);
        }

        for (DS_INV_LINE_TAX_DTLTMsg taxDtlTMsg : this.dsInvLineTaxDtlTMsgList) {
            taxDtlTMsg.invNum.setValue(invNum);
        }
        for (DS_INV_LINE_SER_NUMTMsg serNumTMsg : this.dsInvLineSerNumTMsgList) {
            serNumTMsg.invNum.setValue(invNum);
        }
        for (DS_INV_SLS_CRTMsg invSlsCrTMsg : this.dsInvSlsCrTMsgList) {
            invSlsCrTMsg.invNum.setValue(invNum);
        }
    }

    /**
     * @return NWZC040003PMsg
     */
    public NWZC040003PMsg getNWZC040003PMsg() {
        return this.invLinePMsg;
    }

    /**
     * flushAllMessageMaps
     */
    public void flushAllMessageMap() {
        this.invLineMsgMap.flush();
        for (INV_PRMO_INFOBean invPrmoInfoBean : invPrmoInfoList) {
            invPrmoInfoBean.flushAllMessageMap();
        }
    }

    /**
     * addAllInvPrmoInfoList
     * @param allInvPrmoInfoList INV_PRMO_INFOBean
     */
    public void addAllInvPrmoInfoList(List<INV_PRMO_INFOBean> allInvPrmoInfoList) {
        allInvPrmoInfoList.addAll(this.invPrmoInfoList);
    }

    /**
     * @param invLineFuncAmtSetter invLineFuncAmtSetter
     */
    public void setInvLineFuncAmtSetter(InvLineFuncAmtSetter invLineFuncAmtSetter) {
        this.invLineFuncAmtSetter = invLineFuncAmtSetter;
    }

    /**
     * copyInvLineDealAmtToFuncAmt
     */
    public void copyInvLineDealAmtToFuncAmt() {
        this.invLineFuncAmtSetter.copyInvLineDealAmtToFuncAmt(this);
    }

    /**
     * getInvLineExchangeAmountData
     * @return InvLineExchangeAmountData
     */
    public InvLineExchangeAmountData getInvLineExchangeAmountData() {
        return this.invLineFuncAmtSetter.getInvLineExchangeAmountData(this);
    }

    /**
     * setInvLineFuncAmt
     * @param invLineExchAmtData InvLineExchangeAmountData
     */
    public void setInvLineFuncAmt(INVBean invBean, INVTMsg invTMsg, InvLineExchangeAmountData invLineExchAmtData) {
        this.invLineFuncAmtSetter.setInvLineFuncAmt(invBean, invTMsg, this, invLineExchAmtData);
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

    public List<DS_INV_LINE_TAX_DTLTMsg> getDsInvLineTaxDtlTMsgList() {
        return dsInvLineTaxDtlTMsgList;
    }

    public List<DS_INV_LINE_SER_NUMTMsg> getDsInvLineSerNumTMsgList() {
        return dsInvLineSerNumTMsgList;
    }

    public List<DS_INV_SLS_CRTMsg> getDsInvSlsCrTMsgList() {
        return dsInvSlsCrTMsgList;
    }
}
