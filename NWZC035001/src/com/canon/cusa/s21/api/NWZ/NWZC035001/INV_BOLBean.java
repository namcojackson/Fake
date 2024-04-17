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
import business.db.INV_BOLTMsg;
import business.parts.NWZC035002PMsg;

import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * INV_BOLBean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/11/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * 03/03/2010   Fujitsu         N.Mitsuishi     Update          N/A (Freight Amount can set even Order Entry Screen.)
 * 04/01/2011   Fujitsu         R.Watanabe      Update          1956(PRD)
 * 09/10/2012   Fujitsu         S.Tsunaki       Update          WDS
 * 09/30/2015   Fujitsu         H.Nagashima     Update          CSA
 * 06/14/2018   Fujitsu         H.Nagashima     Update          QC#26121
 * 06/03/2019   Fujitsu         K.Kato          Update          QC#50654
 * </pre>
 */
public class INV_BOLBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param msgMap S21ApiMessageMap
     * @param orderDataPMsg NWZC035002PMsg
     */
    public INV_BOLBean(S21ApiMessageMap msgMap, NWZC035002PMsg orderDataPMsg) {
        this.msgMap = msgMap;
        this.invBolTMsg = new INV_BOLTMsg();
        EZDMsg.copy(msgMap.getPmsg(), null, invBolTMsg, null);
        EZDMsg.copy(orderDataPMsg, null, invBolTMsg, null);
        this.pmtTermCd = orderDataPMsg.pmtTermCd.getValue();
        this.cashDiscTermCd = orderDataPMsg.cashDiscTermCd.getValue();
        this.dealCcyCd = orderDataPMsg.dealCcyCd.getValue();
        this.apvlNum = orderDataPMsg.apvlNum.getValue();
        this.invLineBeanList = new ArrayList<INV_LINEBean>();
        this.shpgChrgAmt = orderDataPMsg.shpgChrgAmt.getValue();

        // WDS ADD Start
        invRcpntCustCd = orderDataPMsg.invRcpntCustCd.getValue();
        // WDS ADD End

        // CSA ADD Start
        this.dsCpoConfigPk        = orderDataPMsg.dsCpoConfigPk.getValue();
        this.cpoOrdNum            = orderDataPMsg.cpoOrdNum.getValue();
        this.billToCustAcctCd     = orderDataPMsg.billToCustAcctCd.getValue();
        if (hasValue(orderDataPMsg.ctacPsnPk_B)) {
            this.billToCtacPsnPk  = orderDataPMsg.ctacPsnPk_B.getValue().toString();
        }
        this.billToCtacPsnFirstNm = orderDataPMsg.billToCtacPsnFirstNm.getValue();
        this.billToCtacPsnMidNm   = orderDataPMsg.billToCtacPsnMidNm.getValue();
        this.billToCtacPsnLastNm  = orderDataPMsg.billToCtacPsnLastNm.getValue();
            this.origInvNum       = orderDataPMsg.origInvNum.getValue();

        if (hasValue(orderDataPMsg.shipToCustAcctCd_G)) {
            this.shipToCustAcctCd     = orderDataPMsg.shipToCustAcctCd_G.getValue();
        }
        if (hasValue(orderDataPMsg.shipToCustLocCd_G)) {
            this.shipToCustLocCd      = orderDataPMsg.shipToCustLocCd_G.getValue();
        }
        if (hasValue(orderDataPMsg.ctacPsnPk_G)) {
            this.shipToCtacPsnPk      = orderDataPMsg.ctacPsnPk_G.getValue().toString();
        }
        setValue(invBolTMsg.ctacPsnPk, orderDataPMsg.ctacPsnPk_S.getValue());
        // CSA ADD End

    }

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** INVBOLTMsg */
    private INV_BOLTMsg invBolTMsg;

    /** pmtTermCd */
    private String pmtTermCd;

    /** cashDiscTermCd */
    private String cashDiscTermCd;

    /** dealCcyCd */
    private String dealCcyCd;

    /** apvlNum */
    private String apvlNum;

    /** shpgchrgAmt */
    private BigDecimal shpgChrgAmt;

    // WDS ADD Start
    /** invRcpntCustCd */
    private String invRcpntCustCd;
    // WDS ADD End

    /** List of INV_LINEBean */
    private List<INV_LINEBean> invLineBeanList;

    // CSA ADD Start
    /** dsCpoConfigPk */
    private BigDecimal dsCpoConfigPk;

    /** cpoOrdNum */
    private String cpoOrdNum;

    /** billToCustAcctCd */
    private String billToCustAcctCd;

    /** billToCtacPsnPk */
    private String billToCtacPsnPk;

    /** billToCtacPsnFirstNm */
    private String billToCtacPsnFirstNm;

    /** billToCtacPsnMidNm */
    private String billToCtacPsnMidNm;

    /** billToCtacPsnLastNm */
    private String billToCtacPsnLastNm;

    /** origInvNum */
    private String origInvNum;

    /** shipToCustAcctCd */
    private String shipToCustAcctCd;

    /** shipToCustLocCd */
    private String shipToCustLocCd;

    /** shipToCtacPsnPk */
    private String shipToCtacPsnPk;
    // QC#26121 2018/06/14 add Start
    /** Invoice Type Code */
    private String invTpCd;
    // QC#26121 2018/06/14 add End

    // 2019/06/03 QC#50654 Add Start
    /** Original Inventory Location Code */
    private String origInvtyLocCd;
    // 2019/06/03 QC#50654 Add Start
    // CSA ADD End

    /**
     * @return invBolTMsg
     */
    public INV_BOLTMsg getInvBolTMsg() {
        return invBolTMsg;
    }

    /**
     * @return msgMap
     */
    public S21ApiMessageMap getMsgMap() {
        return msgMap;
    }

    /**
     * @return apvlNum
     */
    public String getApvlNum() {
        return apvlNum;
    }

    /**
     * @return cashDiscTermCd
     */
    public String getCashDiscTermCd() {
        return cashDiscTermCd;
    }

    /**
     * @return dealCcyCd
     */
    public String getDealCcyCd() {
        return dealCcyCd;
    }

    /**
     * @return pmtTermCd
     */
    public String getPmtTermCd() {
        return pmtTermCd;
    }

    /**
     * @return invLineBeanList
     */
    public List<INV_LINEBean> getInvLineBeanList() {
        return invLineBeanList;
    }

    /**
     * addInvLine
     * @param invLineBean INV_LINEBean
     */
    public void addInvLine(INV_LINEBean invLineBean) {
        this.invLineBeanList.add(invLineBean);
    }

    /**
     * @return shpgChrgAmt
     */
    public BigDecimal getShpgChrgAmt() {
        return shpgChrgAmt;
    }

    // WDS ADD Start


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

// CSA ADD Start
    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @return dsCpoConfigPk
     */
    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @return billToCtacPsnPk
     */
    public String getBillToCtacPsnPk() {
        return billToCtacPsnPk;
    }

    /**
     * @return billToCtacPsnFirstNm
     */
    public String getBillToCtacPsnFirstNm() {
        return billToCtacPsnFirstNm;
    }

    /**
     * @return billToCtacPsnMidNm
     */
    public String getBillToCtacPsnMidNm() {
        return billToCtacPsnMidNm;
    }

    /**
     * @return billToCtacPsnLastNm
     */
    public String getBillToCtacPsnLastNm() {
        return billToCtacPsnLastNm;
    }

    /**
     * @return origInvNum
     */
    public String getOrigInvNum() {
        return origInvNum;
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
// CSA ADD End
//QC#16519 add Start
    /**
     * @return invoice key
     */
    public String getInvKey() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.pmtTermCd)
            .append(this.cashDiscTermCd)
            .append(this.dealCcyCd)
            .append(this.billToCustAcctCd)
            .append(this.billToCtacPsnPk)
            .append(this.billToCtacPsnFirstNm)
            .append(this.billToCtacPsnMidNm)
            .append(this.billToCtacPsnLastNm)
            .append(this.origInvNum)
            .append(this.shipToCustAcctCd)
            .append(this.shipToCustLocCd)
            .append(this.shipToCtacPsnPk)
            ;
        return sb.toString();
    }
    /**
     * @return invoice bol key
     */
    public String getInvBolKey() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.pmtTermCd)
            .append(this.cashDiscTermCd)
            .append(this.dealCcyCd)
            .append(this.billToCustAcctCd)
            .append(this.billToCtacPsnPk)
            .append(this.billToCtacPsnFirstNm)
            .append(this.billToCtacPsnMidNm)
            .append(this.billToCtacPsnLastNm)
            .append(this.origInvNum)
            .append(this.shipToCustAcctCd)
            .append(this.shipToCustLocCd)
            .append(this.shipToCtacPsnPk)
            .append(this.invBolTMsg.soNum.getValue())
            .append(this.invBolTMsg.bolNum.getValue())
            .append(this.invBolTMsg.proNum.getValue())
            ;
        return sb.toString();
    }
//QC#16519 add End

    // QC#26121 2018/06/14 add Start
    public String getInvTpCd() {
        return invTpCd;
    }

    public void setInvTpCd(String invTpCd) {
        this.invTpCd = invTpCd;
    }
    // QC#26121 2018/06/14 add End

    // 2019/06/03 QC#50654 Add Start
    public String getOrigInvtyLocCd() {
        return origInvtyLocCd;
    }

    public void setOrigInvtyLocCd(String origInvtyLocCd) {
        this.origInvtyLocCd = origInvtyLocCd;
    }
    // 2019/06/03 QC#50654 Add End

}
