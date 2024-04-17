/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC006001;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.db.SVC_INV_LINETMsg;
import business.parts.NSZC006001_xxInvLineListPMsg;
//import business.parts.NWZC036001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC006001.constant.NSZC006001Constant;

/**
 * <pre>
 * Service Invoice Line Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/06/2013   Fujitsu         S.Nakai         Create          N/A
 * 09/28/2015   Hitachi         A.Kohinata      Update          NA#
 *</pre>
 */
public class SvcInvLineBean implements NSZC006001Constant {

    /** NSZC006001_xxInvLineListPMsg */
    private NSZC006001_xxInvLineListPMsg invLinePMsg;

    /** SVC_INV_LINETMsg */
    private SVC_INV_LINETMsg svcInvLineTMsg;

//    /** Tax Calculate API PMsg */
//    private NWZC036001PMsg taxCalPMsg;

    /** exec User Cd */
    private String psnCd;

    // Add Start 09/28/2015
    /** svc lbor unit amt */
    private BigDecimal svcLborUnitAmt;

    /** svc trvl unit amt */
    private BigDecimal svcTrvlUnitAmt;
    // Add End 09/28/2015

    /**
     * constructor
     * @param invLinePMsg NSZC006001_xxInvLineListPMsg
     * @param glblCmpyCd String
     */
    public SvcInvLineBean(NSZC006001_xxInvLineListPMsg invLinePMsg, String glblCmpyCd) {
        this.invLinePMsg = invLinePMsg;
        this.svcInvLineTMsg = new SVC_INV_LINETMsg();

        EZDMsg.copy(invLinePMsg, null, this.svcInvLineTMsg, null);

        this.svcInvLineTMsg.glblCmpyCd.setValue(glblCmpyCd);
        this.psnCd = invLinePMsg.psnCd.getValue();
        // Add Start 09/28/2015
        this.svcLborUnitAmt = invLinePMsg.svcLborUnitAmt.getValue();
        this.svcTrvlUnitAmt = invLinePMsg.svcTrvlUnitAmt.getValue();
        // Add End 09/28/2015

        defaultTMsgSetting();
    }

    /**
     * defaultSetting
     */
    public void defaultTMsgSetting() {

        if (this.svcInvLineTMsg == null) {
            return;
        }
    }

    // ------------------------------------------------
    // Getter and Setter
    // ------------------------------------------------
    /**
     * getRtlInvLineTMsg
     * @return SVC_INV_LINETMsg
     */
    public SVC_INV_LINETMsg getSvcInvLineTMsg() {
        return this.svcInvLineTMsg;
    }

    /**
     * setSvcInvLineTMsg
     * @param svcInvLineTMsg SVC_INV_LINETMsg
     */
    public void setSvcInvLineTMsg(SVC_INV_LINETMsg svcInvLineTMsg) {
        this.svcInvLineTMsg = svcInvLineTMsg;
    }

    /**
     * getInvLinePMsg
     * @return NSZC006001_xxInvLineListPMsg
     */
    public NSZC006001_xxInvLineListPMsg getInvLinePMsg() {
        return this.invLinePMsg;
    }

//    /**
//     * getTaxCalPMsg
//     * @return NWZC036001PMsg
//     */
//    public NWZC036001PMsg getTaxCalPMsg() {
//        return taxCalPMsg;
//    }
//
//    /**
//     * setTaxCalPMsg
//     * @param taxCalPMsg NSZC005001PMsg
//     */
//    public void setTaxCalPMsg(NWZC036001PMsg taxCalPMsg) {
//        this.taxCalPMsg = taxCalPMsg;
//    }

    /**
     * getPsnCd
     * @return psnCd
     */
    public String getPsnCd() {
        return psnCd;
    }

    // Add Start 09/28/2015
    /**
     * getSvcLborUnitAmt
     * 
     * @return svcLborUnitAmt
     */
    public BigDecimal getSvcLborUnitAmt() {
        return this.svcLborUnitAmt;
    }

    /**
     * getSvcTrvlUnitAmt
     * 
     * @return svcTrvlUnitAmt
     */
    public BigDecimal getSvcTrvlUnitAmt() {
        return this.svcTrvlUnitAmt;
    }
    // Add End 09/28/2015
}
