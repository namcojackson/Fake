/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC006001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsg;
import business.parts.NSZC006001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC006001.constant.NSZC006001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * Service Invoice Bean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/06/2013   Fujitsu         S.Nakai         Create          N/A
 * 09/28/2015   Hitachi         A.Kohinata      Update          NA#
 * 04/25/2016   Hitachi         T.Iwamoto       Update          QC#7450
 *</pre>
 */
public class SvcInvBean implements NSZC006001Constant {

    /** S21ApiMessageMap */
    private S21ApiMessageMap invMsgMap;

    /** SVC_INVTMsg */
    private SVC_INVTMsg svcInvTMsg;

    /** List<RtlInvLineBean> */
    private List<SvcInvLineBean> invLineBeanList;

    /** List<RtlInvLineBean> */
    private List<SVC_INV_LINE_ALLOCTMsg> invLineAllocList;

    // -- Values --
    /** glblCmpyCd */
    private String glblCmpyCd;

    /** pmtTermSendFaxFlg */
    private boolean pmtTermSendFaxFlg;

    /** ACCT_ARTH_TP_CD : '*' or '/' */
    private String acctArthTpCd;

    /** AFT_DECL_PNT_DIGIT_NUM */
    private int aftDeclPntDigitNum;

    /** fl Pln Flg */
    private boolean flPlnFlg;

    /** coa affl cd */
    private String coaAfflCd;

    //mod Start 2016/04/25 CSA Defect#7450
    /** coa Ch cd */
    private String coaChCd;
    //mod End 2016/04/25 CSA Defect#7450

    /** mode Cd */
    private String modeCd;

    // Add Start 09/28/2015
    /** cust po num */
    private String custPoNum;

    /** cust po dt */
    private String custPoDt;
    // Add End 09/28/2015

    /**
     * constructor
     * 
     * @param invPMsg
     *            NSZC006001PMsg
     */
    public SvcInvBean(NSZC006001PMsg invPMsg) {
        this.svcInvTMsg = new SVC_INVTMsg();
        this.modeCd = invPMsg.xxModeCd.getValue();
        this.glblCmpyCd = invPMsg.glblCmpyCd.getValue();
        this.invMsgMap = new S21ApiMessageMap(invPMsg);
        this.pmtTermSendFaxFlg = false;
        // Add Start 09/28/2015
        this.custPoNum = invPMsg.custPoNum.getValue();
        this.custPoDt = invPMsg.custPoDt.getValue();
        // Add End 09/28/2015

        EZDMsg.copy(invPMsg, null, this.svcInvTMsg, null);

        defaultTMsgSetting();

        this.invLineBeanList = new ArrayList<SvcInvLineBean>();
        this.invLineAllocList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>();

        // RTL_INV_LINE
        for (int i = 0; i < invPMsg.xxInvLineList.getValidCount(); i++) {
            SvcInvLineBean invLineBean = new SvcInvLineBean(invPMsg.xxInvLineList.no(i), invPMsg.glblCmpyCd.getValue());
            this.invLineBeanList.add(invLineBean);
        }
    }

    /**
     * default Setting
     */
    public void defaultTMsgSetting() {

        if (this.svcInvTMsg == null) {
            return;
        }

        this.svcInvTMsg.invTotDealSlsAmt.setValue(BigDecimal.ZERO);
        this.svcInvTMsg.invTotDealNetAmt.setValue(BigDecimal.ZERO);
        this.svcInvTMsg.invTotDealDiscAmt.setValue(BigDecimal.ZERO);
        this.svcInvTMsg.invTotDealTaxAmt.setValue(BigDecimal.ZERO);
        this.svcInvTMsg.invTotFuncSlsAmt.setValue(BigDecimal.ZERO);
        this.svcInvTMsg.invTotFuncNetAmt.setValue(BigDecimal.ZERO);
        this.svcInvTMsg.invTotFuncDiscAmt.setValue(BigDecimal.ZERO);
        this.svcInvTMsg.invTotFuncTaxAmt.setValue(BigDecimal.ZERO);

        this.svcInvTMsg.svcInvOmLinkFlg.setValue(ZYPConstant.FLG_OFF_N);
        this.svcInvTMsg.manCratInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        this.svcInvTMsg.slsSmryCratCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        // Add Start 09/28/2015
        this.svcInvTMsg.prcAllocByMachQtyFlg.setValue(ZYPConstant.FLG_OFF_N);
        // Add End 09/28/2015
}

    /**
     * addXxMsgId
     * 
     * @param xxMsgId
     *            MessageID
     */
    public void addXxMsgId(String xxMsgId) {
        this.invMsgMap.addXxMsgId(xxMsgId);
    }

    /**
     * flushAllMessageMap
     */
    public void flushMessageMap() {
        this.invMsgMap.flush();
    }

    /**
     * clear default flag
     */
    public void clearFlPlnFlg() {
        this.flPlnFlg = false;
    }

    /**
     * set default flag
     * 
     * @param flPlnFlg boolean
     */
    public void setFlPlnFlg(boolean flPlnFlg) {
        this.flPlnFlg = flPlnFlg;
    }

    /**
     * check exist default flag
     * 
     * @return boolean
     */
    public boolean isFlPlnFlg() {
        return this.flPlnFlg;
    }

    /**
     * setChengedSellToFlg
     * 
     * @param flg
     *            boolean
     */
    public void setPmtTermSendFaxFlg(boolean flg) {
        this.pmtTermSendFaxFlg = flg;
    }

    /**
     * check payment term send fax flag
     * 
     * @return boolean
     */
    public boolean isPmtTermSendFaxFlg() {
        return this.pmtTermSendFaxFlg;
    }

    // ------------------------------------------------
    // Getter and Setter
    // ------------------------------------------------
    /**
     * getRtlInvPMsg
     * 
     * @return NSZC006001PMsg
     */
    public NSZC006001PMsg getRtlInvPMsg() {
        return (NSZC006001PMsg) this.invMsgMap.getPmsg();

    }

    /**
     * getGlblCmpyCd
     * 
     * @return String
     */
    public String getGlblCmpyCd() {
        return this.glblCmpyCd;
    }

    /**
     * getSvcInvTMsg
     * 
     * @return SVC_INVTMsg
     */
    public SVC_INVTMsg getSvcInvTMsg() {
        return this.svcInvTMsg;
    }

    /**
     * setSvcInvTMsg
     * 
     * @param svcInvTMsg
     *            SVC_INVTMsg
     */
    public void setSvcInvTMsg(SVC_INVTMsg svcInvTMsg) {
        this.svcInvTMsg = svcInvTMsg;
    }

    /**
     * setAcctArthTpCd
     * 
     * @param acctArthTpCd
     *            String
     */
    public void setAcctArthTpCd(String acctArthTpCd) {
        this.acctArthTpCd = acctArthTpCd;
    }

    /**
     * getAcctArthTpCd
     * 
     * @return String
     */
    public String getAcctArthTpCd() {
        return this.acctArthTpCd;
    }

    /**
     * setCoaAfflCd
     * 
     * @param coaAfflCd
     *            String
     */
    public void setCoaAfflCd(String coaAfflCd) {
        this.coaAfflCd = coaAfflCd;
    }
    
    //mod Start 2016/04/25 CSA Defect#7450
    /**
     * setCoaChCd
     * 
     * @param coaChCd
     *            String
     */
    public void setCoaChCd(String coaChCd) {
        this.coaChCd = coaChCd;
    }
    //mod End 2016/04/25 CSA Defect#7450

    /**
     * getModeCd
     * 
     * @return String
     */
    public String getModeCd() {
        return modeCd;
    }

    /**
     * getCoaAfflCd
     * 
     * @return int
     */
    public String getCoaAfflCd() {
        return this.coaAfflCd;
    }

    //mod Start 2016/04/25 CSA Defect#7450
    /**
     * getCoaChCd
     * 
     * @return int
     */
    public String getCoaChCd() {
        return this.coaChCd;
    }
    //mod End 2016/04/25 CSA Defect#7450

    /**
     * setAftDeclPntDigitNum
     * 
     * @param aftDeclPntDigitNum
     *            BigDecimal
     */
    public void setAftDeclPntDigitNum(BigDecimal aftDeclPntDigitNum) {
        this.aftDeclPntDigitNum = aftDeclPntDigitNum.intValue();
    }

    /**
     * getAftDeclPntDigitNum
     * 
     * @return int
     */
    public int getAftDeclPntDigitNum() {
        return this.aftDeclPntDigitNum;
    }

    /**
     * getInvLineBeanList
     * 
     * @return List<SvcInvLineBean>
     */
    public List<SvcInvLineBean> getInvLineBeanList() {
        return this.invLineBeanList;
    }

    /**
     * setInvLineList
     * 
     * @param invLineBean
     *            List<SvcInvLineBean>
     */
    public void setInvLineList(List<SvcInvLineBean> invLineBean) {
        this.invLineBeanList = invLineBean;
    }

    /**
     * getInvLineBeanList
     * 
     * @return List<RtlInvLineBean>
     */
    public List<SVC_INV_LINE_ALLOCTMsg> getInvLineAllocList() {
        return this.invLineAllocList;
    }

    /**
     * setInvLineAllocList
     * 
     * @param invLineAllocList
     *            List<SVC_INV_LINE_ALLOCTMsg>
     */
    public void setInvLineAllocList(List<SVC_INV_LINE_ALLOCTMsg> invLineAllocList) {
        this.invLineAllocList = invLineAllocList;
    }

    // Add Start 09/28/2015
    /**
     * getCustPoNum
     * 
     * @return String
     */
    public String getCustPoNum() {
        return this.custPoNum;
    }

    /**
     * getCustPoDt
     * 
     * @return String
     */
    public String getCustPoDt() {
        return this.custPoDt;
    }
    // Add End 09/28/2015
}
