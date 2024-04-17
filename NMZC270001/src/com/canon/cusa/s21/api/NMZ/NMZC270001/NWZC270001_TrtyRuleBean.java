/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC270001;


import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;

import business.parts.NMZC270001_trtyRuleListPMsg;

/**
 *<pre>
 * NMZC2700 Territory Rule Validation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         C.Yokoi         Create          N/A
 * 2016/03/22   Fujitsu         C.Yokoi         Update          CSA-QC#5364
 * 2016/08/31   SRAA            Y.Chen          Update          QC#11728
 *</pre>
 */
public class NWZC270001_TrtyRuleBean {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param trtyRuleList String[]
     */
    public NWZC270001_TrtyRuleBean(String[] trtyRuleList) {
        // 2016/03/22 CSA-QC#5364 Add Start
        this.trtyRuleTpCd = TRTY_RULE_TP.POSTAL_CODE;
        this.trtyRuleOprdTpCd = TRTY_RULE_OPRD_TP.BETWEEN;
        this.trtyRuleLogicTpCd = TRTY_RULE_LOGIC_TP.AND;
        this.trtyRuleFromValTxt = trtyRuleList[0];
        this.trtyRuleThruValTxt = trtyRuleList[1];
        // 2016/03/22 CSA-QC#5364 Add End
    }

    /**
     * Constructor
     * @param trtyRuleList NMZC270001_trtyRuleListPMsg
     */
    public NWZC270001_TrtyRuleBean(NMZC270001_trtyRuleListPMsg trtyRuleList) {
        this.trtyRuleTpCd = trtyRuleList.trtyRuleTpCd.getValue();
        this.trtyRuleOprdTpCd = trtyRuleList.trtyRuleOprdTpCd.getValue();
        this.trtyRuleFromValTxt = trtyRuleList.trtyRuleFromValTxt.getValue();
        this.trtyRuleThruValTxt = trtyRuleList.trtyRuleThruValTxt.getValue();
        this.trtyRuleLogicTpCd = trtyRuleList.trtyRuleLogicTpCd.getValue();
        this.effFromDt = trtyRuleList.effFromDt_R.getValue();
        this.effThruDt = trtyRuleList.effThruDt_R.getValue();
    }

    // Data Definition
    /** TRTY_RULE_TP_CD*/
    private String              trtyRuleTpCd;

    /** TRTY_RULE_OPRD_TP_CD*/
    private String              trtyRuleOprdTpCd;

    /** TRTY_RULE_FROM_VAL_TXT*/
    private String              trtyRuleFromValTxt;

    /** TRTY_RULE_THRU_VAL_TXT*/
    private String              trtyRuleThruValTxt;

    /** TRTY_RULE_LOGIC_TP_CD*/
    private String              trtyRuleLogicTpCd;

    /** EFF_FROM_DT*/
    private String              effFromDt;

    /** EFF_THRU_DT*/
    private String              effThruDt;
    
    // QC#11728
    /** XX_MSG_ID */
    private String              xxMsgId;
    
    /** XX_MSG_PRM_TXT */
    private String              xxMsgPrmTxt;

    /** XX_ROW_NUM */
    private int              xxRowNum = -1;
    
    /**
     * Get the trtyRuleTpCd
     * @return trtyRuleTpCd
     */
    public String getTrtyRuleTpCd() {
        return trtyRuleTpCd;
    }

    /**
     * Set the trtyRuleTpCd
     * @param trtyRuleTpCd String
     */
    public void setTrtyRuleTpCd(String trtyRuleTpCd) {
        this.trtyRuleTpCd = trtyRuleTpCd;
    }

    /**
     * Get the trtyRuleOprdTpCd
     * @return trtyRuleOprdTpCd
     */
    public String getTrtyRuleOprdTpCd() {
        return trtyRuleOprdTpCd;
    }

    /**
     * Set the trtyRuleOprdTpCd
     * @param trtyRuleOprdTpCd String
     */
    public void setTrtyRuleOprdTpCd(String trtyRuleOprdTpCd) {
        this.trtyRuleOprdTpCd = trtyRuleOprdTpCd;
    }

    /**
     * Get the trtyRuleFromValTxt
     * @return trtyRuleFromValTxt
     */
    public String getTrtyRuleFromValTxt() {
        return trtyRuleFromValTxt;
    }

    /**
     * Set the trtyRuleFromValTxt
     * @param trtyRuleFromValTxt String
     */
    public void setTrtyRuleFromValTxt(String trtyRuleFromValTxt) {
        this.trtyRuleFromValTxt = trtyRuleFromValTxt;
    }

    /**
     * Get the trtyRuleThruValTxt
     * @return trtyRuleThruValTxt
     */
    public String getTrtyRuleThruValTxt() {
        return trtyRuleThruValTxt;
    }

    /**
     * Set the trtyRuleThruValTxt
     * @param trtyRuleThruValTxt String
     */
    public void setTrtyRuleThruValTxt(String trtyRuleThruValTxt) {
        this.trtyRuleThruValTxt = trtyRuleThruValTxt;
    }

    /**
     * Get the trtyRuleLogicTpCd
     * @return trtyRuleLogicTpCd
     */
    public String getTrtyRuleLogicTpCd() {
        return trtyRuleLogicTpCd;
    }

    /**
     * Set the trtyRuleLogicTpCd
     * @param trtyRuleLogicTpCd String
     */
    public void setTrtyRuleLogicTpCd(String trtyRuleLogicTpCd) {
        this.trtyRuleLogicTpCd = trtyRuleLogicTpCd;
    }

    /**
     * Get the effFromDt
     * @return effFromDt
     */
    public String getEffFromDt() {
        return effFromDt;
    }

    /**
     * Set the effFromDt
     * @param effFromDt String
     */
    public void setEffFromDt(String effFromDt) {
        this.effFromDt = effFromDt;
    }

    /**
     * Get the effThruDt
     * @return effThruDt
     */
    public String getEffThruDt() {
        return effThruDt;
    }

    /**
     * Set the effThruDt
     * @param effThruDt String
     */
    public void setEffThruDt(String effThruDt) {
        this.effThruDt = effThruDt;
    }

    /**
     * Get the xxMsgId
     * @return xxMsgId
     */
    public String getXxMsgId() {
        return xxMsgId;
    }

    /**
     * Set the xxMsgId
     * @param xxMsgId String
     */
    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }

    /**
     * Get the xxMsgPrmTxt
     * @return xxMsgPrmTxt
     */
    public String getXxMsgPrmTxt() {
        return xxMsgPrmTxt;
    }

    /**
     * Set the xxMsgPrmTxt
     * @param xxMsgPrmTxt String
     */
    public void setXxMsgPrmTxt(String xxMsgPrmTxt) {
        this.xxMsgPrmTxt = xxMsgPrmTxt;
    }

    /**
     * Get the xxRowNum
     * @return xxRowNum
     */
    public int getXxRowNum() {
        return xxRowNum;
    }

    /**
     * Set the xxRowNum
     * @param xxRowNum int
     */
    public void setXxRowNum(int xxRowNum) {
        this.xxRowNum = xxRowNum;
    }
}

