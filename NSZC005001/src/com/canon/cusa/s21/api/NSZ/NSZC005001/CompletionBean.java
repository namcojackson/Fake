/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC005001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.db.FSR_VISITTMsg;
import business.db.SVC_BILL_TPTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC005001_xxFsrUsgListPMsg;
import business.parts.NSZC005001_xxPartSurveyListPMsg;
import business.parts.NSZC005001_xxTmEventListPMsg;
import business.parts.NSZC061001PMsg;

/**
 * <pre>
 * Service Dispatch Completion API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   SRA             E.Inada         Create          N/A
 * 2013/09/11   SRA             Y.Chen          Update          QC2208
 * 10/21/2015   Hitachi         A.Kohinata      Update          NA#Renewal
 * 2016/04/20   Hitachi         T.Iwamoto       Update          QC#4469
 * 2019/02/13   Hitachi         K.Fujimoto      Update          QC#30310
 * 2019/11/18   Hitachi         K.Fujimoto      Update          QC#54391
 *</pre>
 */
public class CompletionBean {

    /** acctArthTpCd */
    private String acctArthTpCd = null;

    /** aftDeclPntDigitNum */
    private BigDecimal aftDeclPntDigitNum = null;

    /** intgProdCatgCdDrum */
    private String intgProdCatgCdDrum = null;

    /** phoneFixFlg */
    private String phoneFixFlg = null;

    /** svcLborTmNum */
    private BigDecimal svcLborTmNum = null;

    /** fsrVisitTMsg */
    private FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();

    /** svcTaskTMsg */
    private SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();

    /** svcBillTpTMsg */
    private SVC_BILL_TPTMsg svcBillTpTMsg = new SVC_BILL_TPTMsg();

    /** dsSvcCallTpTMsg */
    private DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = new DS_SVC_CALL_TPTMsg();

    /** tmEventListLbor */
    private List<NSZC005001_xxTmEventListPMsg> tmEventListLbor = new ArrayList<NSZC005001_xxTmEventListPMsg>();

    /** tmEventListTrvl */
    private List<NSZC005001_xxTmEventListPMsg> tmEventListTrvl = new ArrayList<NSZC005001_xxTmEventListPMsg>();

    /** tmEventListOther */
    private List<NSZC005001_xxTmEventListPMsg> tmEventListOther = new ArrayList<NSZC005001_xxTmEventListPMsg>();

    /** fsrUsgListConf */
    private List<NSZC005001_xxFsrUsgListPMsg> fsrUsgListConf = new ArrayList<NSZC005001_xxFsrUsgListPMsg>();

    // add start 2016/04/18 CSA Defect#4469
    /** fsrUsgSrvyListConf */
    private List<NSZC005001_xxPartSurveyListPMsg> fsrUsgSrvyListConf = new ArrayList<NSZC005001_xxPartSurveyListPMsg>();
    // add end 2016/04/18 CSA Defect#4469

    /** fsrUsgListConfForInvty */
    private List<NSZC005001_xxFsrUsgListPMsg> fsrUsgListConfForInvty = new ArrayList<NSZC005001_xxFsrUsgListPMsg>();

    /** pricingApiPMsg */
    private NSZC061001PMsg svcPrcApiPMsg = new NSZC061001PMsg();

    /** svcTaskNumListForUsg */
    private List<String> svcTaskNumListForUsg = new ArrayList<String>();

    /** aftDeclPntDigitNum(standard currency) */
    private BigDecimal stdCcyAftDeclPntDigitNum = null;

    // Add Start 2019/02/13 K.Fujimoto QC#30310
    private String changePartsMsg = null;
    // Add End   2019/02/13 K.Fujimoto QC#30310

    // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
    private boolean isOnsSprtCall = false;
    // END   2019/11/18 K.Fujimoto [QC#54391, ADD]
    /**
     * getAcctArthTpCd
     * @return acctArthTpCd
     */
    public String getAcctArthTpCd() {
        return acctArthTpCd;
    }

    /**
     * setAcctArthTpCd
     * @param acctArthTpCd String
     */
    public void setAcctArthTpCd(String acctArthTpCd) {
        this.acctArthTpCd = acctArthTpCd;
    }

    /**
     * getAftDeclPntDigitNum
     * @return aftDeclPntDigitNum
     */
    public BigDecimal getAftDeclPntDigitNum() {
        return aftDeclPntDigitNum;
    }

    /**
     * setAftDeclPntDigitNum
     * @param aftDeclPntDigitNum BigDecimal
     */
    public void setAftDeclPntDigitNum(BigDecimal aftDeclPntDigitNum) {
        this.aftDeclPntDigitNum = aftDeclPntDigitNum;
    }

    /**
     * getIntgProdCatgCdDrum
     * @return intgProdCatgCdDrum
     */
    public String getIntgProdCatgCdDrum() {
        return intgProdCatgCdDrum;
    }

    /**
     * setIntgProdCatgCdDrum
     * @param intgProdCatgCdDrum String
     */
    public void setIntgProdCatgCdDrum(String intgProdCatgCdDrum) {
        this.intgProdCatgCdDrum = intgProdCatgCdDrum;
    }

    /**
     * getPhoneFixFlg
     * @return phoneFixFlg
     */
    public String getPhoneFixFlg() {
        return phoneFixFlg;
    }

    /**
     * setPhoneFixFlg
     * @param phoneFixFlg String
     */
    public void setPhoneFixFlg(String phoneFixFlg) {
        this.phoneFixFlg = phoneFixFlg;
    }

    /**
     * getSvcLborTmNum
     * @return svcLborTmNum
     */
    public BigDecimal getSvcLborTmNum() {
        return svcLborTmNum;
    }

    /**
     * setSvcLborTmNum
     * @param svcLborTmNum BigDecimal
     */
    public void setSvcLborTmNum(BigDecimal svcLborTmNum) {
        this.svcLborTmNum = svcLborTmNum;
    }

    /**
     * getFsrVisitTMsg
     * @return fsrVisitTMsg
     */
    public FSR_VISITTMsg getFsrVisitTMsg() {
        return fsrVisitTMsg;
    }

    /**
     * setFsrVisitTMsg
     * @param fsrVisitTMsg FSR_VISITTMsg
     */
    public void setFsrVisitTMsg(FSR_VISITTMsg fsrVisitTMsg) {
        this.fsrVisitTMsg = fsrVisitTMsg;
    }

    /**
     * getSvcTaskTMsg
     * @return svcTaskTMsg
     */
    public SVC_TASKTMsg getSvcTaskTMsg() {
        return svcTaskTMsg;
    }

    /**
     * setSvcTaskTMsg
     * @param svcTaskTMsg SVC_TASKTMsg
     */
    public void setSvcTaskTMsg(SVC_TASKTMsg svcTaskTMsg) {
        this.svcTaskTMsg = svcTaskTMsg;
    }

    /**
     * getSvcBillTpTMsg
     * @return svcBillTpTMsg
     */
    public SVC_BILL_TPTMsg getSvcBillTpTMsg() {
        return svcBillTpTMsg;
    }

    /**
     * setSvcBillTpTMsg
     * @param svcBillTpTMsg SVC_BILL_TPTMsg
     */
    public void setSvcBillTpTMsg(SVC_BILL_TPTMsg svcBillTpTMsg) {
        this.svcBillTpTMsg = svcBillTpTMsg;
    }

    /**
     * getDsSvcCallTpTMsg
     * @return dsSvcCallTpTMsg
     */
    public DS_SVC_CALL_TPTMsg getDsSvcCallTpTMsg() {
        return dsSvcCallTpTMsg;
    }

    /**
     * setDsSvcCallTpTMsg
     * @param dsSvcCallTpTMsg DS_SVC_CALL_TPTMsg
     */
    public void setDsSvcCallTpTMsg(DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg) {
        this.dsSvcCallTpTMsg = dsSvcCallTpTMsg;
    }

    /**
     * addTmEventLbor
     * @param tmEventLbor NSZC005001_xxTmEventListPMsg
     */
    public void addTmEventListLbor(NSZC005001_xxTmEventListPMsg tmEventLbor) {
        this.tmEventListLbor.add(tmEventLbor);
    }

    /**
     * getTmEventListLbor
     * @return tmEventListLbor
     */
    public List<NSZC005001_xxTmEventListPMsg> getTmEventListLbor() {
        return tmEventListLbor;
    }

    /**
     * addTmEventListTrvl
     * @param tmEventTrvl NSZC005001_xxTmEventListPMsg
     */
    public void addTmEventListTrvl(NSZC005001_xxTmEventListPMsg tmEventTrvl) {
        this.tmEventListTrvl.add(tmEventTrvl);
    }

    /**
     * getTmEventListTrvl
     * @return tmEventListTrvl
     */
    public List<NSZC005001_xxTmEventListPMsg> getTmEventListTrvl() {
        return tmEventListTrvl;
    }

    /**
     * addTmEventListTrvl
     * @param tmEventOther NSZC005001_xxTmEventListPMsg
     */
    public void addTmEventListOther(NSZC005001_xxTmEventListPMsg tmEventOther) {
        this.tmEventListOther.add(tmEventOther);
    }

    /**
     * getTmEventListOther
     * @return tmEventListOther
     */
    public List<NSZC005001_xxTmEventListPMsg> getTmEventListOther() {
        return tmEventListOther;
    }

    /**
     * addFsrUsgListConf
     * @param fsrUsgConf NSZC005001_xxFsrUsgListPMsg
     */
    public void addFsrUsgListConf(NSZC005001_xxFsrUsgListPMsg fsrUsgConf) {
        this.fsrUsgListConf.add(fsrUsgConf);
    }

    /**
     * getFsrUsgListConf
     * @return fsrUsgListConf
     */
    public List<NSZC005001_xxFsrUsgListPMsg> getFsrUsgListConf() {
        return fsrUsgListConf;
    }

    // add start 2016/04/18 CSA Defect#4469
    /**
     * addFsrUsgSrvyListConf
     * @param fsrUsgSrvyConf NSZC005001_xxPartSurveyListPMsg
     */
    public void addFsrUsgSrvyListConf(NSZC005001_xxPartSurveyListPMsg fsrUsgSrvyConf) {
        this.fsrUsgSrvyListConf.add(fsrUsgSrvyConf);
    }

    /**
     * getFsrUsgSrvyListConf
     * @return fsrUsgSrvyListConf
     */
    public List<NSZC005001_xxPartSurveyListPMsg> getFsrUsgSrvyListConf() {
        return fsrUsgSrvyListConf;
    }
    // add end 2016/04/18 CSA Defect#4469

    /**
     * addFsrUsgListConfForInvty
     * @param fsrUsgConfForInvty NSZC005001_xxFsrUsgListPMsg
     */
    public void addFsrUsgListConfForInvty(NSZC005001_xxFsrUsgListPMsg fsrUsgConfForInvty) {
        this.fsrUsgListConfForInvty.add(fsrUsgConfForInvty);
    }

    /**
     * getFsrUsgListConfForInvty
     * @return fsrUsgListConfForInvty
     */
    public List<NSZC005001_xxFsrUsgListPMsg> getFsrUsgListConfForInvty() {
        return fsrUsgListConfForInvty;
    }

    /**
     * getSvcPrcApiPMsg
     * @return svcPrcApiPMsg
     */
    public NSZC061001PMsg getSvcPrcApiPMsg() {
        return svcPrcApiPMsg;
    }

    /**
     * setSvcPrcApiPMsg
     * @param svcPrcApiPMsg NSZC061001PMsg
     */
    public void setSvcPrcApiPMsg(NSZC061001PMsg svcPrcApiPMsg) {
        this.svcPrcApiPMsg = svcPrcApiPMsg;
    }

    /**
     * addSvcTaskNumListForUsg
     * @param svcTaskNum String
     */
    public void addSvcTaskNumListForUsg(String svcTaskNum) {
        this.svcTaskNumListForUsg.add(svcTaskNum);
    }

    /**
     * getSvcTaskNumListForUsg
     * @return svcTaskNumListForUsg
     */
    public List<String> getSvcTaskNumListForUsg() {
        return svcTaskNumListForUsg;
    }

    /**
     * containsSvcTaskNumListForUsg
     * @param svcTaskNum String
     * @return boolean
     */
    public boolean containsSvcTaskNumListForUsg(String svcTaskNum) {
        if (this.svcTaskNumListForUsg.contains(svcTaskNum)) {
            return true;
        }
        return false;
    }

    /**
     * getStdCcyAftDeclPntDigitNum
     * @return stdCcyAftDeclPntDigitNum
     */
    public BigDecimal getStdCcyAftDeclPntDigitNum() {
        return stdCcyAftDeclPntDigitNum;
    }

    /**
     * setStdCcyAftDeclPntDigitNum
     * @param stdCcyAftDeclPntDigitNum BigDecimal
     */
    public void setStdCcyAftDeclPntDigitNum(BigDecimal stdCcyAftDeclPntDigitNum) {
        this.stdCcyAftDeclPntDigitNum = stdCcyAftDeclPntDigitNum;
    }

    // Add Start 2019/02/13 K.Fujimoto QC#30310
    /** 
     * setModPartsMsg
     * @param modPartsMsg
     */
    public void setChangePartsMsg(String modPartsMsg) {
        this.changePartsMsg = modPartsMsg;
    }

    /**
     * getModPartsMsg
     * @return modPartsMsg
     */
    public String getChangePartsMsg() {
        return changePartsMsg;
    }
    // Add End   2019/02/13 K.Fujimoto QC#30310

    // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
    /**
     * setOnsSprtCall
     * @param isOnsSprtCall
     */
    public void setOnsSprtCall(boolean isOnsSprtCall) {
        this.isOnsSprtCall = isOnsSprtCall;
    }
    
    /**
     * isOnsSprtCall
     * @return isOnsSprtCall
     */
    public boolean isOnsSprtCall() {
        return isOnsSprtCall;
    }
    // END   2019/11/18 K.Fujimoto [QC#54391, ADD]
}
