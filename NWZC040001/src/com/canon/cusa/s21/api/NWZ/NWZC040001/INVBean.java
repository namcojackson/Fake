/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDTBigDecimalItem;
import business.db.INVTMsg;
import business.parts.NWZC040001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_CLM_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * INVBean
 * 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * 2017/08/18   Hitachi         J.Kim           Update          QC#20426
 * 2018/03/14   Hitachi         E.Kameishi      Update          QC#23689
 * </pre>
 */
public class INVBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor
     * @param invPMsg NWZC040001PMsg
     */
    public INVBean(NWZC040001PMsg invPMsg) {
        this.invPMsg = invPMsg;
        this.invTmsg = new INVTMsg();
        EZDMsg.copy(invPMsg, null, this.invTmsg, null);
        // START 2018/03/13 E.Kameishi [QC#23689,MOD]
        this.invTmsg.fnlzInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        // END 2018/03/13 E.Kameishi [QC#23689,MOD]

        if (!ZYPCommonFunc.hasValue(invPMsg.histCratCpltFlg)) {
            this.invTmsg.histCratCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        defaultValueSetter(invPMsg.invTotDealNetAmt, this.invTmsg.invTotDealNetAmt);
        defaultValueSetter(invPMsg.invTotDealSlsAmt, this.invTmsg.invTotDealSlsAmt);
        defaultValueSetter(invPMsg.invTotDealFrtAmt, this.invTmsg.invTotDealFrtAmt);
        defaultValueSetter(invPMsg.invTotDealTaxAmt, this.invTmsg.invTotDealTaxAmt);
        defaultValueSetter(invPMsg.invTotDealDiscAmt, this.invTmsg.invTotDealDiscAmt);
        defaultValueSetter(invPMsg.invTotDealDiscAmt, this.invTmsg.invTotDealDiscAmt);
        defaultValueSetter(invPMsg.invTotDealInsAmt, this.invTmsg.invTotDealInsAmt);
        defaultValueSetter(invPMsg.invTotFuncInsAmt, this.invTmsg.invTotFuncInsAmt);

        // START 2017/08/18 J.Kim [QC#20426,ADD]
        ZYPEZDItemValueSetter.setValue(invTmsg.acctDt, ZYPDateUtil.getSalesDate(invTmsg.glblCmpyCd.getValue()));
        // END 2017/08/18 J.Kim [QC#20426,ADD]

        this.invMsgMap = new S21ApiMessageMap(invPMsg);
        this.invBolList = new ArrayList<INV_BOLBean>();
        this.invCashDiscTermList = new ArrayList<INV_CASH_DISC_TERMBean>();

        if (!hasValue(invPMsg.dplyMdseDtlFlg)) {
            setValue(invTmsg.dplyMdseDtlFlg, ZYPConstant.FLG_ON_Y);
        }

        if (!hasValue(invPMsg.csmpInvClmStsCd)) {
            setValue(invTmsg.csmpInvClmStsCd, CSMP_INV_CLM_STS.NONE);
        }
        invTmsg.csmpInvProcStsCd.setValue(CSMP_INV_PROC_STS.NONE);
        invTmsg.itrlInvErrFlg.setValue(ZYPConstant.FLG_OFF_N);
        invTmsg.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);
    }

    /** NWZC040001PMsg */
    private NWZC040001PMsg invPMsg;

    /** INVTMsg */
    private INVTMsg invTmsg;

    /** S21ApiMessageMap */
    private S21ApiMessageMap invMsgMap;

    /** List<INV_BOLBean> */
    private List<INV_BOLBean> invBolList;

    /** List<INV_BOLBean> */
    private List<INV_CASH_DISC_TERMBean> invCashDiscTermList;

    /** funcCcyCd */
    private String funcCcyCd;

    /** acctArthTpCd */
    private String acctArthTpCd;

    /** beforeAdjustNETDueDt */
    private String beforeAdjustNETDueDt;

    /**
     * @return NWZC040001PMsg
     */
    public NWZC040001PMsg getNWZC040001PMsg() {
        return this.invPMsg;
    }

    /**
     * @return invBolList
     */
    public List<INV_BOLBean> getInvBolList() {
        return invBolList;
    }

    /**
     * @return invCashDiscTermList
     */
    public List<INV_CASH_DISC_TERMBean> getInvCashDiscTermList() {
        return invCashDiscTermList;
    }

    /**
     * @return invTmsg
     */
    public INVTMsg getInvTmsg() {
        return invTmsg;
    }

    /**
     * addXxMsgId
     * @param xxMsgId MessageID
     */
    public void addXxMsgId(String xxMsgId) {
        this.invMsgMap.addXxMsgId(xxMsgId);
    }

    /**
     * addInvBol
     * @param invBolBean INV_BOLBean
     */
    public void addInvBol(INV_BOLBean invBolBean) {
        this.invBolList.add(invBolBean);
    }

    /**
     * addInvCashDiscTerm
     * @param invCashDiscTermBean INV_CASH_DISC_TERMBean
     */
    public void addInvCashDiscTerm(INV_CASH_DISC_TERMBean invCashDiscTermBean) {
        this.invCashDiscTermList.add(invCashDiscTermBean);
    }

    /**
     * setInvNum
     * @param invNum String
     */
    public void setInvNum(String invNum) {

        this.invTmsg.invNum.setValue(invNum);
        for (INV_BOLBean invBolBean : invBolList) {
            invBolBean.setInvNum(invNum);
        }

        for (INV_CASH_DISC_TERMBean invCashDiscTermBean : invCashDiscTermList) {
            invCashDiscTermBean.setInvNum(invNum);
        }

        getNWZC040001PMsg().invNum.setValue(invNum);
    }

    /**
     * @return boolean
     */
    public boolean hasInvNum() {

        if (!ZYPCommonFunc.hasValue(getNWZC040001PMsg().invNum)) {
            return false;
        }
        return true;
    }

    /**
     * flushAllMessageMap
     */
    public void flushAllMessageMap() {
        this.invMsgMap.flush();
        for (INV_BOLBean invBolBean : invBolList) {
            invBolBean.flushAllMessageMap();
        }
    }

    /**
     * @return List<INV_LINEBean>
     */
    public List<INV_LINEBean> getInvLineList() {

        List<INV_LINEBean> allInvLineList = new ArrayList<INV_LINEBean>();

        for (INV_BOLBean invBolBean : invBolList) {
            invBolBean.addAllInvLineList(allInvLineList);
        }

        return allInvLineList;
    }

    /**
     * @return List<INV_PRMO_INFOBean>
     */
    public List<INV_PRMO_INFOBean> getInvPrmoInfoList() {

        List<INV_PRMO_INFOBean> allInvPrmoInfoList = new ArrayList<INV_PRMO_INFOBean>();

        for (INV_BOLBean invBolBean : invBolList) {
            List<INV_LINEBean> invLineList = invBolBean.getInvLineList();

            for (INV_LINEBean invLineBean : invLineList) {
                invLineBean.addAllInvPrmoInfoList(allInvPrmoInfoList);
            }
        }
        return allInvPrmoInfoList;
    }

    /**
     * @return acctArthTpCd
     */
    public String getAcctArthTpCd() {
        return acctArthTpCd;
    }

    /**
     * @param acctArthTpCd String
     */
    public void setAcctArthTpCd(String acctArthTpCd) {
        this.acctArthTpCd = acctArthTpCd;
    }

    /**
     * @return funcCcyCd
     */
    public String getFuncCcyCd() {
        return funcCcyCd;
    }

    /**
     * @param funcCcyCd String
     */
    public void setFuncCcyCd(String funcCcyCd) {
        this.funcCcyCd = funcCcyCd;
    }

    /**
     * @return beforeAdjustNETDueDt
     */
    public String getBeforeAdjustNETDueDt() {
        return beforeAdjustNETDueDt;
    }

    /**
     * @param beforeAdjustNETDueDt String
     */
    public void setBeforeAdjustNETDueDt(String beforeAdjustNETDueDt) {
        this.beforeAdjustNETDueDt = beforeAdjustNETDueDt;
    }

    private void defaultValueSetter(EZDPBigDecimalItem pBigDecimalItem, EZDTBigDecimalItem tBigDecimalItem) {
        if (!ZYPCommonFunc.hasValue(pBigDecimalItem)) {
            tBigDecimalItem.setValue(BigDecimal.ZERO);
        }
    }

    public S21ApiMessageMap getInvMsgMap() {
        return invMsgMap;
    }
}
