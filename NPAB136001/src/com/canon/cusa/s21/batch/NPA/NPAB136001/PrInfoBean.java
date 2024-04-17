/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB136001;

import business.db.MDSETMsg;
import business.db.INSRC_RULETMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.S21_PSNTMsg;

/**
 * <pre>
 * Business ID : NPAB1360 Insourcing Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class PrInfoBean {
    /** . */
    private PRCH_REQTMsg headerInfo = null;

    /** . */
    private PRCH_REQ_DTLTMsg detailInfo = null;

    /** . */
    private MDSETMsg mdseInfo = null;

    /** . */
    private MDSE_ITEM_STSTMsg mdseItemStsInfo = null;

    /** . */
    private RTL_WHTMsg rtlWhInfo = null;

    /** . */
    private S21_PSNTMsg s21PsnInfo = null;

    /** . */
    private INSRC_RULETMsg ruleInfo = null;

    /**
     * @param headerInfo PRCH_REQTMsg
     */
    public void setHeaderInfo(PRCH_REQTMsg headerInfo) {
        this.headerInfo = headerInfo;
    }

    /**
     * @return headerInfo
     */
    public PRCH_REQTMsg getHeaderInfo() {
        return headerInfo;
    }

    /**
     * @param detailInfo PRCH_REQ_DTLTMsg
     */
    public void setDetailInfo(PRCH_REQ_DTLTMsg detailInfo) {
        this.detailInfo = detailInfo;
    }

    /**
     * @return detailInfo
     */
    public PRCH_REQ_DTLTMsg getDetailInfo() {
        return detailInfo;
    }

    /**
     * @param mdseInfo MDSETMsg
     */
    public void setMdseInfo(MDSETMsg mdseInfo) {
        this.mdseInfo = mdseInfo;
    }

    /**
     * @return mdseInfo
     */
    public MDSETMsg getMdseInfo() {
        return mdseInfo;
    }

    /**
     * @param mdseItemStsInfo MDSE_ITEM_STSTMsg
     */
    public void setMdseItemStsInfo(MDSE_ITEM_STSTMsg mdseItemStsInfo) {
        this.mdseItemStsInfo = mdseItemStsInfo;
    }

    /**
     * @return mdseItemStsInfo
     */
    public MDSE_ITEM_STSTMsg getMdseItemStsInfo() {
        return mdseItemStsInfo;
    }

    /**
     * @param rtlWhInfo RTL_WHTMsg
     */
    public void setRtlWhInfo(RTL_WHTMsg rtlWhInfo) {
        this.rtlWhInfo = rtlWhInfo;
    }

    /**
     * @return rtlWhInfo
     */
    public RTL_WHTMsg getRtlWhInfo() {
        return rtlWhInfo;
    }

    /**
     * @param s21PsnInfo S21_PSNTMsg
     */
    public void setS21PsnInfo(S21_PSNTMsg s21PsnInfo) {
        this.s21PsnInfo = s21PsnInfo;
    }

    /**
     * @return s21PsnInfo
     */
    public S21_PSNTMsg getS21PsnInfo() {
        return s21PsnInfo;
    }

    /**
     * @param ruleInfo INSRC_RULETMsg
     */
    public void setRuleInfo(INSRC_RULETMsg ruleInfo) {
        this.ruleInfo = ruleInfo;
    }

    /**
     * @return ruleInfo
     */
    public INSRC_RULETMsg getRuleInfo() {
        return ruleInfo;
    }
}
