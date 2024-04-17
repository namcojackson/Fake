/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC035001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_INV_TPTMsg;
import business.db.INVTMsg;
import business.db.INV_CASH_DISC_TERMTMsg;
import business.parts.NWZC035001PMsg;
import business.parts.NWZC035002PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 * <pre>
 * INVBean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/07/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * 02/11/2010   Fujitsu         K.Tajima        Update          3164
 * 09/10/2012   Fujitsu         S.Tsunaki       Update          WDS
 * 09/30/2015   Fujitsu         H.Nagashima     Update          CSA
 * 06/16/2016   Fujitsu         H.Nagashima     Update          QC#19189
 * 06/14/2018   Fujitsu         H.Nagashima     Update          QC#26121
 * </pre>
 */
public class INVBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param msgMap S21ApiMessageMap
     * @param orderDataPMsg NWZC035002PMsg
     * @param invTMsg INVTMsg
     * @param needUpdate boolean
     */
    public INVBean(S21ApiMessageMap msgMap, NWZC035002PMsg orderDataPMsg, INVTMsg invTMsg, boolean needUpdate) {
        this.msgMap = msgMap;
        this.invTMsg = invTMsg;
        NWZC035001PMsg paramPMsg = (NWZC035001PMsg) msgMap.getPmsg();
        this.invTMsg.cpoOrdNum.setValue(paramPMsg.cpoOrdNum.getValue());
        this.invTMsg.cpoOrdTpCd.setValue(paramPMsg.cpoOrdTpCd.getValue());
        EZDMsg.copy(orderDataPMsg, null, this.invTMsg, null);
        this.cpoSrcTpCd = orderDataPMsg.cpoSrcTpCd.getValue();
        this.invCashDiscTermList = new ArrayList<INV_CASH_DISC_TERMTMsg>();
        this.invBolBeanList = new ArrayList<INV_BOLBean>();
        this.needUpdate = needUpdate;

// ********** [Def# 3164] K.Tajima - START
         // CR_DR_RSN_CD
         this.invTMsg.crDrRsnCd.setValue(orderDataPMsg.crDrRsnCd.getValue());
         // CR_DR_RSN_SUB_CD
         this.invTMsg.crDrRsnSubCd.setValue(orderDataPMsg.crDrRsnSubCd.getValue());
// ********** [Def# 3164] K.Tajima - E N D

         // WDS ADD Start
         this.invRcpntCustCd = orderDataPMsg.invRcpntCustCd.getValue();
         // WDS ADD End
         // WDS R-OM004 Add Start
         this.dsOrdTpCd = orderDataPMsg.dsOrdTpCd.getValue();
         this.dsOrdRsnCd = orderDataPMsg.dsOrdRsnCd.getValue();
         // WDS R-OM004 Add End

//CSA ADD Start
//         this.dsInvTMsg = new DS_INVTMsg();
         if (needUpdate) {
             INVTMsg itmsg = new INVTMsg();
             setValue(itmsg.glblCmpyCd, invTMsg.glblCmpyCd.getValue());
             setValue(itmsg.invNum, invTMsg.invNum.getValue());
//             itmsg = (INVTMsg) S21CacheTBLAccessor.findByKey(itmsg);
             itmsg = (INVTMsg) EZDTBLAccessor.findByKey(itmsg);     //QC#19189
             if (itmsg != null) {
                 EZDMsg.copy(itmsg, null, this.invTMsg, null);
             }
         }
         EZDMsg.copy(orderDataPMsg, null, this.invTMsg, null);

         setValue(this.invTMsg.srcSysDocNum, paramPMsg.cpoOrdNum.getValue());
         setValue(this.invTMsg.itrlInvErrFlg, ZYPConstant.FLG_OFF_N);
         setValue(this.invTMsg.slsRepTocCd, orderDataPMsg.slsRepTocCd_H.getValue());
         setValue(this.invTMsg.ctacPsnPk, orderDataPMsg.ctacPsnPk_B.getValue());

         if (hasValue(orderDataPMsg.origInvNum)) {
             //get original invoice
             INVTMsg origTmsg = new INVTMsg();
             origTmsg.glblCmpyCd.setValue(paramPMsg.glblCmpyCd.getValue());
             origTmsg.invNum.setValue(orderDataPMsg.origInvNum.getValue());
             this.origInvTMsg = (INVTMsg) S21CacheTBLAccessor.findByKey(origTmsg);
         }
         // get DS_INV_TP
         if (hasValue(invTMsg.dsInvTpCd)) {
             DS_INV_TPTMsg tmsg = new DS_INV_TPTMsg();
             setValue(tmsg.glblCmpyCd, paramPMsg.glblCmpyCd.getValue());
             setValue(tmsg.dsInvTpCd, invTMsg.dsInvTpCd.getValue());
             this.dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(tmsg);
         }

         if (hasValue(orderDataPMsg.shipToCustAcctCd_G)) {
             this.shipToCustAcctCd     = orderDataPMsg.shipToCustAcctCd_G.getValue();
         }
         if (hasValue(orderDataPMsg.shipToCustLocCd_G)) {
             this.shipToCustLocCd      = orderDataPMsg.shipToCustLocCd_G.getValue();
         }
         if (hasValue(orderDataPMsg.ctacPsnPk_G)) {
             this.shipToCtacPsnPk      = orderDataPMsg.ctacPsnPk_G.getValue().toString();
         }

//CSA ADD End
    }

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** INVTMsg */
    private INVTMsg invTMsg;

    /** beforeAdjustNETDueDt */
    private String beforeAdjustNETDueDt;

    /** cpoSrcTpCd */
    private String cpoSrcTpCd;

    /** acctArthTpCd */
    private String acctArthTpCd;

    /** List of INV_CASH_DISC_TERMTMsg */
    private List<INV_CASH_DISC_TERMTMsg> invCashDiscTermList;

    /** List of INV_BOLBean */
    private List<INV_BOLBean> invBolBeanList;

    /** thisTimeInvTotFuncNetAmt */
    private BigDecimal thisTimeInvTotFuncNetAmt;

    /** needUpdate */
    private boolean needUpdate;
    // WDS R-OM004 Add Start
    /** dsOrdTpCd */
    private String dsOrdTpCd;

    /** dsOrdRsnCd */
    private String dsOrdRsnCd;
    // WDS R-OM004 Add End

    /** invRcpntCustCd */
    private String invRcpntCustCd;

    /** crCardCustRefNum */
    private String crCardCustRefNum;

    /** crCardAuthRefNum */
    private String crCardAuthRefNum;

    /** crCardAuthDt */
    private String crCardAuthDt;

    /** crCardTpCd */
    private String crCardTpCd;

    /** crCardExprYrMth */
    private String crCardExprYrMth;
    // WDS ADD End

//CSA ADD Start
    /** origInvTMsg */
    private INVTMsg origInvTMsg;

    /** dsInvTpTMsg */
    private DS_INV_TPTMsg dsInvTpTMsg;

    /** shipToCustAcctCd */
    private String shipToCustAcctCd;

    /** shipToCustLocCd */
    private String shipToCustLocCd;

    /** shipToCtacPsnPk */
    private String shipToCtacPsnPk;
//CSA ADD End
    // QC#26121 add Start
    /** Last Invoice Type Code */
    private String lastInvTpCd;
    // QC#26121 add End

    /**
     * @return invTMsg
     */
    public INVTMsg getInvTMsg() {
        return invTMsg;
    }

    /**
     * @return msgMap
     */
    public S21ApiMessageMap getMsgMap() {
        return msgMap;
    }

    /**
     * @return beforeAdjustNETDueDt
     */
    public String getBeforeAdjustNETDueDt() {
        return beforeAdjustNETDueDt;
    }

    /**
     * @param beforeAdjustNETDueDt beforeAdjustNETDueDt
     */
    public void setBeforeAdjustNETDueDt(String beforeAdjustNETDueDt) {
        this.beforeAdjustNETDueDt = beforeAdjustNETDueDt;
    }

    /**
     * @return invCashDiscTermList
     */
    public List<INV_CASH_DISC_TERMTMsg> getInvCashDiscTermList() {
        return invCashDiscTermList;
    }

    /**
     * addInvCashDiscTerm
     * @param invCashDiscTermTMsg INV_CASH_DISC_TERMTMsg
     */
    public void addInvCashDiscTerm(INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg) {
        this.invCashDiscTermList.add(invCashDiscTermTMsg);
    }

    /**
     * @return invBolBeanList
     */
    public List<INV_BOLBean> getInvBolBeanList() {
        return invBolBeanList;
    }

    /**
     * addInvBol
     * @param invBolBean INV_BOLBean
     */
    public void addInvBol(INV_BOLBean invBolBean) {
        this.invBolBeanList.add(invBolBean);
    }

    /**
     * @return cpoSrcTpCd
     */
    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    /**
     * @return thisTimeInvTotFuncNetAmt
     */
    public BigDecimal getThisTimeInvTotFuncNetAmt() {
        return thisTimeInvTotFuncNetAmt;
    }

    /**
     * @param thisTimeInvTotFuncNetAmt BigDecimal
     */
    public void setThisTimeInvTotFuncNetAmt(BigDecimal thisTimeInvTotFuncNetAmt) {
        this.thisTimeInvTotFuncNetAmt = thisTimeInvTotFuncNetAmt;
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
     * @return needUpdate
     */
    public boolean isNeedUpdate() {
        return needUpdate;
    }

    // WDS ADD Start

    /**
     * @return crCardCustRefNum
     */
    public String getCrCardCustRefNum() {
        return crCardCustRefNum;
    }

    /**
     * @param crCardCustRefNum crCardCustRefNum
     */
    public void setCrCardCustRefNum(String crCardCustRefNum) {
        this.crCardCustRefNum = crCardCustRefNum;
        setValue(this.invTMsg.crCardCustRefNum, crCardCustRefNum);
    }

    /**
     * @return crCardAuthRefNum
     */
    public String getCrCardAuthRefNum() {
        return crCardAuthRefNum;
    }

    /**
     * @param crCardAuthRefNum crCardAuthRefNum
     */
    public void setCrCardAuthRefNum(String crCardAuthRefNum) {
        this.crCardAuthRefNum = crCardAuthRefNum;
        setValue(this.invTMsg.crCardAuthRefNum, crCardAuthRefNum);
    }

    /**
     * @return crCardAuthDt
     */
    public String getCrCardAuthDt() {
        return crCardAuthDt;
    }

    /**
     * @param crCardAuthDt crCardAuthDt
     */
    public void setCrCardAuthDt(String crCardAuthDt) {
        this.crCardAuthDt = crCardAuthDt;
        setValue(this.invTMsg.crCardAuthDt, crCardAuthDt);
    }

    /**
     * @return crCardTpCd
     */
    public String getCrCardTpCd() {
        return crCardTpCd;
    }

    /**
     * @param crCardTpCd crCardTpCd
     */
    public void setCrCardTpCd(String crCardTpCd) {
        this.crCardTpCd = crCardTpCd;
        setValue(this.invTMsg.crCardTpCd, crCardTpCd);
    }

    /**
     * @return crCardExprYrMth
     */
    public String getCrCardExprYrMth() {
        return crCardExprYrMth;
    }

    /**
     * @param crCardExprYrMth crCardExprYrMth
     */
    public void setCrCardExprYrMth(String crCardExprYrMth) {
        this.crCardExprYrMth = crCardExprYrMth;
        setValue(this.invTMsg.dsInvExprDt, crCardExprYrMth + "01");
    }

    /**
     * @return invRcpntCustCd
     */
    public String getInvRcpntCustCd() {
        return invRcpntCustCd;
    }

    /**
     * @param invRcpntCustCd invRcpntCustCd
     */
    public void setInvRcpntCustCd(String invRcpntCustCd) {
        this.invRcpntCustCd = invRcpntCustCd;
    }
    // WDS ADD End
    // WDS R-OM004 add Start
    /**
     * @return dsOrdTpCd
     */
    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    /**
     * @param dsOrdTpCd String
     */
    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    /**
     * @return dsOrdRsnCd
     */
    public String getDsOrdRsnCd() {
        return dsOrdRsnCd;
    }

    /**
     * @param dsOrdRsnCd String
     */
    public void setDsOrdRsnCd(String dsOrdRsnCd) {
        this.dsOrdRsnCd = dsOrdRsnCd;
    }
    // WDS R-OM004 add end

//CSA ADD Start
    /**
     * @return origInvTMsg
     */
    public INVTMsg getOrigInvTMsg() {
        return origInvTMsg;
    }

    /**
     * @return dsInvTpTMsg
     */
    public DS_INV_TPTMsg getDsInvTpTMsg() {
        return dsInvTpTMsg;
    }

    /**
     * @return shipToCustAcctCd
     */
    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    /**
     * @return shipToCustLocCd
     */
    public String getShipToCustLocCd() {
        return shipToCustLocCd;
    }

    /**
     * @return shipToCtacPsnPk
     */
    public String getShipToCtacPsnPk() {
        return shipToCtacPsnPk;
    }

    // QC#26121 add Start
    public String getLastInvTpCd() {
        return lastInvTpCd;
    }

    public void setLastInvTpCd(String lastInvTpCd) {
        this.lastInvTpCd = lastInvTpCd;
    }

    public void setInvTpCd(String invTpCd) {

        setValue(this.invTMsg.invTpCd, invTpCd);

        for (INV_BOLBean bolBean : invBolBeanList) {
            bolBean.setInvTpCd(invTpCd);
            for (INV_LINEBean lineBean : bolBean.getInvLineBeanList()) {
                lineBean.setInvTpCd(invTpCd);
            }
        }
    }
    // QC#26121 add End

//CSA ADD End
}
