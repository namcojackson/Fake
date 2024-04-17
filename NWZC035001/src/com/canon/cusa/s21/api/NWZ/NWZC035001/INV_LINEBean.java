/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC035001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.db.DS_INV_LINE_TAX_DTLTMsg;
import business.db.INV_LINETMsg;
import business.db.INV_PRMO_INFOTMsg;
import business.parts.NWZC035001PMsg;
import business.parts.NWZC035002PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * INV_LINEBean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/11/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * 11/04/2009   Fujitsu         N.Mitsuishi     Update          1068
 * 02/11/2010   Fujitsu         K.Tajima        Update          3164
 * 06/24/2010   Fujitsu         H.Nagashima     Update          1219(E2E)
 * 09/28/2010   Fujitsu         R.Watanabe      Update          N/A(Adjust a fraction.)
 * 09/10/2012   Fujitsu         S.Tsunaki       Update          WDS
 * 08/06/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM10
 * 08/06/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM31
 * 08/06/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM50
 * 09/30/2015   Fujitsu         H.Nagashima     Update          CSA
 * 10/20/2016   Fujitsu         H.Nagashima     Update          QC#14448
 * 12/12/2016   Fujitsu         H.Nagashima     Update          QC#16237
 * 05/21/2018   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 06/14/2018   Fujitsu         H.Nagashima     Update          QC#26121
 * 06/03/2019   Fujitsu         K.Kato          Update          QC#50654
 * </pre>
 */
public class INV_LINEBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param msgMap S21ApiMessageMap
     * @param orderDataPMsg NWZC035002PMsg
     */
    public INV_LINEBean(S21ApiMessageMap msgMap, NWZC035002PMsg orderDataPMsg) {
        this.msgMap = msgMap;
        this.invLineTMsg = new INV_LINETMsg();
        EZDMsg.copy(msgMap.getPmsg(), null, invLineTMsg, null);
        EZDMsg.copy(orderDataPMsg, null, invLineTMsg, null);
        this.invLineTMsg.mercCntnFlg.setValue(ZYPConstant.FLG_OFF_N);
        this.invPrmoInfoList = new ArrayList<INV_PRMO_INFOTMsg>();
        this.pmtTermCd = orderDataPMsg.pmtTermCd.getValue();
        this.cashDiscTermCd = orderDataPMsg.cashDiscTermCd.getValue();
        this.dealCcyCd = orderDataPMsg.dealCcyCd.getValue();
        this.apvlNum = orderDataPMsg.apvlNum.getValue();
        this.soNum = orderDataPMsg.soNum.getValue();
        this.bolNum = orderDataPMsg.bolNum.getValue();
        this.proNum = orderDataPMsg.proNum.getValue();
        this.shipCpltCd = orderDataPMsg.shipCpltCd.getValue();
        this.setItemChecked = false;
        this.shipDealFrtAmt = orderDataPMsg.shipDealFrtAmt.getValue();
        this.shipFuncFrtAmt = orderDataPMsg.shipFuncFrtAmt.getValue();
        this.origInvLineDealTaxAmt = orderDataPMsg.origInvLineDealTaxAmt.getValue();
        this.origInvLineFuncTaxAmt = orderDataPMsg.origInvLineFuncTaxAmt.getValue();
        this.origInvLineTaxPct = orderDataPMsg.origInvLineTaxPct.getValue();
        this.setShpgPlnNum = orderDataPMsg.setShpgPlnNum.getValue();

// ********** [Def# 3164] K.Tajima - START
        // CR_DR_RSN_CD
        this.invLineTMsg.crDrRsnCd.setValue(orderDataPMsg.crDrRsnCd_LI.getValue());
        // CR_DR_RSN_SUB_CD
        this.invLineTMsg.crDrRsnSubCd.setValue(orderDataPMsg.crDrRsnSubCd_LI.getValue());
// ********** [Def# 3164] K.Tajima - E N D

// ----- add 20100624 Defect1219(E2E)
        this.invLineTMsg.histCratCpltFlg.setValue(ZYPConstant.FLG_OFF_N);

        // WDS Add Start
        this.mdseTpCd = orderDataPMsg.mdseTpCd.getValue();
        this.dsOrdPosnNum = orderDataPMsg.dsOrdPosnNum.getValue();
        this.svcConfigMstrPk = orderDataPMsg.svcConfigMstrPk.getValue();
        this.orgFuncRoleTpCd = orderDataPMsg.orgFuncRoleTpCd.getValue();
        this.tocCd = orderDataPMsg.tocCd.getValue();
        // WDS Add End
        // WDS Add Start R-OM010
        this.dsContrNum = orderDataPMsg.dsContrNum.getValue();
        this.dsContrSqNum = orderDataPMsg.dsContrSqNum.getValue();
        // WDS Add End R-OM010
        // WDS Add Start R-OM031
        this.shpgPlnTrxCd = orderDataPMsg.trxCd.getValue();
        this.shpgPlnTrxRsnCd = orderDataPMsg.trxRsnCd.getValue();
        this.shipFromInvtyLocCd = orderDataPMsg.shipFromInvtyLocCd.getValue();
        // WDS Add End R-OM031
        // WDS Add Start R-OM031
        NWZC035001PMsg pMsg = (NWZC035001PMsg) msgMap.getPmsg();
        this.cpoOrdTpCd = pMsg.cpoOrdTpCd.getValue();
        // WDS Add End R-OM031

        //CSA ADD Start
        this.soSlpNum             = orderDataPMsg.soSlpNum.getValue();
        this.crRebilCd            = orderDataPMsg.crRebilCd.getValue();
        this.billToCustAcctCd     = orderDataPMsg.billToCustAcctCd.getValue();
        if (hasValue(orderDataPMsg.ctacPsnPk_B)) {
            this.billToCtacPsnPk      = orderDataPMsg.ctacPsnPk_B.getValue().toString();
        }
        this.billToCtacPsnFirstNm = orderDataPMsg.billToCtacPsnFirstNm.getValue();
        this.billToCtacPsnMidNm   = orderDataPMsg.billToCtacPsnMidNm.getValue();
        this.billToCtacPsnLastNm  = orderDataPMsg.billToCtacPsnLastNm.getValue();
        this.origInvNum           = orderDataPMsg.origInvNum.getValue();
        this.billWithEquipFlg     = orderDataPMsg.billWithEquipFlg.getValue();

        if (hasValue(orderDataPMsg.shipToCustAcctCd_G)) {
            this.shipToCustAcctCd     = orderDataPMsg.shipToCustAcctCd_G.getValue();
        }
        if (hasValue(orderDataPMsg.shipToCustLocCd_G)) {
            this.shipToCustLocCd      = orderDataPMsg.shipToCustLocCd_G.getValue();
        }
        if (hasValue(orderDataPMsg.ctacPsnPk_G)) {
            this.shipToCtacPsnPk      = orderDataPMsg.ctacPsnPk_G.getValue().toString();
        }

        this.dsInvLineTaxDtlList = new ArrayList<DS_INV_LINE_TAX_DTLTMsg>();

        //CSA QC#2846 Start
        this.invtyCtrlFlg = orderDataPMsg.invtyCtrlFlg.getValue();
        //CSA QC#2846 End
        //QC#6832 2016/04/12 add Start
        this.poReqFlg = orderDataPMsg.poReqFlg.getValue();
        //QC#6832 2016/04/12 add End
        // QC#14448 add Start
        invLineTMsg.compProcStsCd.setValue("0");
        // QC#14448 add End
        // QC#21841 2018/05/21 Add Start
        invLineTMsg.invLineCatgCd.setValue(INV_LINE_CATG.ITEM);
        // QC#21841 2018/05/21 Add End
        //CSA ADD End
    }

    /**
     * Constructor for dummy Set Merchandise
     * @param msgMap S21ApiMessageMap
     * @param orgInvLineTMsg INV_LINETMsg
     */
    public INV_LINEBean(S21ApiMessageMap msgMap, INV_LINETMsg orgInvLineTMsg) {

        this.msgMap = msgMap;
        this.invLineTMsg = new INV_LINETMsg();
        EZDMsg.copy(orgInvLineTMsg, null, this.invLineTMsg, null);
        this.invPrmoInfoList = new ArrayList<INV_PRMO_INFOTMsg>();
        //CSA ADD Start
//        this.dsInvLineTMsg = new DS_INV_LINETMsg();
//        EZDMsg.copy(orgInvLineTMsg, null, this.dsInvLineTMsg, null);
        this.setItemDummyFlg = ZYPConstant.FLG_ON_Y;
        //CSA ADD End
    }

// CSA ADD Start
    /**
     * Constructor for Bill with Equipment
     * @param origInvLineBean INV_LINETMsg
     */
    public INV_LINEBean(INV_LINEBean origInvLineBean) {
        this.msgMap = origInvLineBean.msgMap;

        INV_LINETMsg origInvLineTMsg = origInvLineBean.getInvLineTMsg();
        this.invLineTMsg = new INV_LINETMsg();
        EZDMsg.copy(msgMap.getPmsg(), null, invLineTMsg, null);
        EZDMsg.copy(origInvLineTMsg, null, this.invLineTMsg, null);

        this.invPrmoInfoList = new ArrayList<INV_PRMO_INFOTMsg>();
        this.shipDealFrtAmt = BigDecimal.ZERO;
        this.shipFuncFrtAmt = BigDecimal.ZERO;

        this.billWithEquipFlg = ZYPConstant.FLG_ON_Y;
        this.dsInvLineTaxDtlList = new ArrayList<DS_INV_LINE_TAX_DTLTMsg>();
        //QC#15701 add Start
        this.invLineTMsg.cpoDtlLineSubNum.setValue("999");
        this.invLineTMsg.setMdseCd.clear();
        //QC#15701 add End
        //QC#16237 add Start
        this.svcConfigMstrPk = invLineTMsg.svcConfigMstrPk.getValue();
        this.dsContrNum = invLineTMsg.dsContrNum.getValue();
        this.dsContrSqNum = invLineTMsg.dsContrSqNum.getValue();
        //QC#16237 add End
    }
    // QC#21841 2018/05/21 Add Start
    public INV_LINEBean() {
        this.invPrmoInfoList = new ArrayList<INV_PRMO_INFOTMsg>();
    }
    // QC#21841 2018/05/21 Add End
// CSA ADD End

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** INV_LINETMsg */
    private INV_LINETMsg invLineTMsg;

    //CSA ADD Start
    /** pmtTermCd */
    private String pmtTermCd;

    /** cashDiscTermCd */
    private String cashDiscTermCd;

    /** dealCcyCd */
    private String dealCcyCd;

    /** apvlNum */
    private String apvlNum;

    /** soNum */
    private String soNum;

    /** bolNum */
    private String bolNum;

    /** proNum */
    private String proNum;

    /** List of INV_PRMO_INFOBean */
    private List<INV_PRMO_INFOTMsg> invPrmoInfoList;

    /** shipCpltCd */
    private String shipCpltCd;

    /** setItemChecked */
    private boolean setItemChecked;

    /** invtyCtrlFlg */
    private String invtyCtrlFlg;

    /** invtyValFlg */
    private String invtyValFlg;

    /** shipDealFrtAmt */
    private BigDecimal shipDealFrtAmt;

    /** shipFuncFrtAmt */
    private BigDecimal shipFuncFrtAmt;

    /** origInvLineDealTaxAmt */
    private BigDecimal origInvLineDealTaxAmt;

    /** origInvLineDealTaxAmt */
    private BigDecimal origInvLineFuncTaxAmt;

    /** origInvLineTaxPct */
    private BigDecimal origInvLineTaxPct;

    /** setShpgPlnNum */
    private String setShpgPlnNum;

    /** dealGrsTotPrcAmt */
    private BigDecimal dealGrsTotPrcAmt;

    /** funcGrsTotPrcAmt */
    private BigDecimal funcGrsTotPrcAmt;

    // WDS ADD Start
    /** mdseTpCd */
    private String mdseTpCd;

    /** bomLvlLineNum */
    private String dsOrdPosnNum;

    /** svcConfigMstrPk */
    private BigDecimal svcConfigMstrPk;

    /** orgFuncRoleTpCd */
    private String orgFuncRoleTpCd;

    /** tocCd */
    private String tocCd;

    /** invRcpntCustCd */
    private String invRcpntCustCd;
    // WDS ADD End
    // WDS Add Start R-OM010
    /** dsContrNum */
    private String dsContrNum;

    /** dsContrSqNum */
    private String dsContrSqNum;

    // WDS Add End R-OM010
    // WDS Add Start R-OM031
    /** shpgPlnTrxCd */
    private String shpgPlnTrxCd;

    /** shpgPlnTrxRsnCd */
    private String shpgPlnTrxRsnCd;

    /** intyLocCd */
    private String shipFromInvtyLocCd;
    // WDS Add End R-OM031
    // WDS Add Start R-OM050
    /** cpoOrdTpCd */
    private String cpoOrdTpCd;

    // WDS Add End R-OM050

    // CSA ADD Start
    /** soSlpNum */
    private String soSlpNum;

    /** dsInvLineTaxDtlTMsg */
    private List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlList;

    /** crRebilCd */
    private String crRebilCd;

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

    /** billWithEquipFlg  */
    private String billWithEquipFlg;

    /** setItemDummyFlg  */
    private String setItemDummyFlg;

    /** invBolBean */
    private INV_BOLBean invBolBean;

    //QC#6832 2016/04/12 add Start
    /** poReqFlg */
    private String poReqFlg;
    //QC#6832 2016/04/12 add End

    //QC#16237 add Start
    /** existContr */
    private boolean existContr = false;
    //QC#16237 add End
    // QC#21841 2018/05/21 Add Start
    private String invLineCatgCd;
    // QC#21841 2018/05/21 Add End
    // QC#26121 2018/06/08 add Start
    /** Invoice Type Code */
    private String invTpCd;
    // QC#26121 2018/06/08 add End
// CSA ADD End

    // 2019/06/03 QC#50654 Add Start
    /** Original Inventory Location Code */
    private String origInvtyLocCd;
    // 2019/06/03 QC#50654 Add End

    /**
     * @return apvlNum
     */
    public String getApvlNum() {
        return apvlNum;
    }

    /**
     * @return bolNum
     */
    public String getBolNum() {
        return bolNum;
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
     * @return invLineTMsg
     */
    public INV_LINETMsg getInvLineTMsg() {
        return invLineTMsg;
    }

    /**
     * @return msgMap
     */
    public S21ApiMessageMap getMsgMap() {
        return msgMap;
    }

    /**
     * @return pmtTermCd
     */
    public String getPmtTermCd() {
        return pmtTermCd;
    }

    /**
     * @return proNum
     */
    public String getProNum() {
        return proNum;
    }

    /**
     * @return soNum
     */
    public String getSoNum() {
        return soNum;
    }

    /**
     * @param soNum String
     */
    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }
// CSA ADD Start
    /**
     * @return soSlpNum
     */
    public String getSoSlpNum() {
        return soSlpNum;
    }

    /**
     * @return invPrmoInfoBeanList
     */
    public List<INV_PRMO_INFOTMsg> getInvPrmoInfoList() {
        return invPrmoInfoList;
    }

    /**
     * addInvPrmoInfo
     * @param arg0 List of INV_PRMO_INFOTMsg
     */
    public void addAllInvPrmoInfo(List<INV_PRMO_INFOTMsg> arg0) {
        this.invPrmoInfoList.addAll(arg0);
    }

    /**
     * addInvPrmoInfo
     * @param invPrmoInfoTMsg INV_PRMO_INFOTMsg
     */
    public void addInvPrmoInfo(INV_PRMO_INFOTMsg invPrmoInfoTMsg) {
        this.invPrmoInfoList.add(invPrmoInfoTMsg);
    }

    /**
     * @return setItemChecked
     */
    public boolean isSetItemChecked() {
        return setItemChecked;
    }

    /**
     *  return setItemChecked
     */
    public void setSetItemChecked() {
        this.setItemChecked = true;
    }

    /**
     * @return shipCpltCd
     */
    public String getShipCpltCd() {
        return shipCpltCd;
    }

    /**
     * @return invtyCtrlFlg
     */
    public String getInvtyCtrlFlg() {
        return invtyCtrlFlg;
    }

    /**
     * @param invtyCtrlFlg String
     */
    public void setInvtyCtrlFlg(String invtyCtrlFlg) {
        this.invtyCtrlFlg = invtyCtrlFlg;
    }

    /**
     * @return invtyValFlg
     */
    public String getInvtyValFlg() {
        return invtyValFlg;
    }

    /**
     * @param invtyValFlg String
     */
    public void setInvtyValFlg(String invtyValFlg) {
        this.invtyValFlg = invtyValFlg;
    }

    /**
     * @param bolNum String
     */
    public void setBolNum(String bolNum) {
        this.bolNum = bolNum;
    }

    /**
     * @param proNum String
     */
    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    /**
     * @return shipDealFrtAmt
     */
    public BigDecimal getShipDealFrtAmt() {
        return shipDealFrtAmt;
    }

    /**
     * @return shipFuncFrtAmt
     */
    public BigDecimal getShipFuncFrtAmt() {
        return shipFuncFrtAmt;
    }

    /**
     * @return origInvLineDealTaxAmt
     */
    public BigDecimal getOrigInvLineDealTaxAmt() {
        return origInvLineDealTaxAmt;
    }

    /**
     * @return origInvLineFuncTaxAmt
     */
    public BigDecimal getOrigInvLineFuncTaxAmt() {
        return origInvLineFuncTaxAmt;
    }

    /**
     * @return origInvLineTaxPct
     */
    public BigDecimal getOrigInvLineTaxPct() {
        return origInvLineTaxPct;
    }

    /**
     * @return setShpgPlnNum
     */
    public String getSetShpgPlnNum() {
        return setShpgPlnNum;
    }

    /**
     * @param dealGrsTotPrcAmt BigDecimal
     */
    public void setDealGrsTotPrcAmt(BigDecimal dealGrsTotPrcAmt) {
        this.dealGrsTotPrcAmt = dealGrsTotPrcAmt;
    }

    /**
     * @return dealGrsTotPrcAmt
     */
    public BigDecimal getDealGrsTotPrcAmt() {
        return dealGrsTotPrcAmt;
    }

    /**
     * @param funcGrsTotPrcAmt BigDecimal
     */
    public void setFuncGrsTotPrcAmt(BigDecimal funcGrsTotPrcAmt) {
        this.funcGrsTotPrcAmt = funcGrsTotPrcAmt;
    }

    /**
     * @return funcGrsTotPrcAmt
     */
    public BigDecimal getFuncGrsTotPrcAmt() {
        return funcGrsTotPrcAmt;
    }

    // WDS ADD Start
    /**
     * @return mdseTpCd
     */
    public String getMdseTpCd() {
        return mdseTpCd;
    }

    /**
     * @param mdseTpCd mdseTpCd
     */
    public void setMdseTpCd(String mdseTpCd) {
        this.mdseTpCd = mdseTpCd;
        ZYPEZDItemValueSetter.setValue(this.invLineTMsg.mdseTpCd, mdseTpCd);
    }

    /**
     * @return bomLvlLineNum
     */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    /**
     * @param dsOrdPosnNum String
     */
    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
        ZYPEZDItemValueSetter.setValue(this.invLineTMsg.dsOrdPosnNum, dsOrdPosnNum);
    }

    /**
     * @return svcConfigMstrPk
     */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * @param svcConfigMstrPk svcConfigMstrPk
     */
    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
        ZYPEZDItemValueSetter.setValue(this.invLineTMsg.svcConfigMstrPk, svcConfigMstrPk);
    }

    /**
     * @return orgFuncRoleTpCode
     */
    public String getOrgFuncRoleTpCd() {
        return orgFuncRoleTpCd;
    }

    /**
     * @param orgFuncRoleTpCode orgFuncRoleTpCode
     */
    public void setOrgFuncRoleTpCd(String orgFuncRoleTpCode) {
        this.orgFuncRoleTpCd = orgFuncRoleTpCode;
    }

    /**
     * @return tocCd
     */
    public String getTocCd() {
        return tocCd;
    }

    /**
     * @param tocCd tocCd
     */
    public void setTocCd(String tocCd) {
        this.tocCd = tocCd;
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
    // WDS Add Start R-OM010
    /**
     * @return dsContrNum
     */
    public String getDsContrNum() {
        return dsContrNum;
    }
    /**
     * @param dsContrNum dsContrNum
     */
    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
        ZYPEZDItemValueSetter.setValue(this.invLineTMsg.dsContrNum, dsContrNum);
    }
    /**
     * @return dsContrSqNum
     */
    public String getDsContrSqNum() {
        return dsContrSqNum;
    }
    /**
     * @param dsContrSqNum dsContrSqNum
     */
    public void setDsContrSqNum(String dsContrSqNum) {
        this.dsContrSqNum = dsContrSqNum;
        ZYPEZDItemValueSetter.setValue(this.invLineTMsg.dsContrSqNum, dsContrSqNum);
    }

    // WDS Add End R-OM010
    // WDS Add Start R-OM031
    /**
     * @return shpgPlnTrxCd
     */
    public String getShpgPlnTrxCd() {
        return shpgPlnTrxCd;
    }

    /**
     * @param shpgPlnTrxCd String
     */
    public void setShpgPlnTrxCd(String shpgPlnTrxCd) {
        this.shpgPlnTrxCd = shpgPlnTrxCd;
    }

    /**
     * @return shpgPlnTrxRsnCd
     */
    public String getShpgPlnTrxRsnCd() {
        return shpgPlnTrxRsnCd;
    }

    /**
     * @param shpgPlnTrxRsnCd String
     */
    public void setShpgPlnTrxRsnCd(String shpgPlnTrxRsnCd) {
        this.shpgPlnTrxRsnCd = shpgPlnTrxRsnCd;
    }

    /**
     * @return shipFromInvtyLocCd
     */
    public String getShipFromInvtyLocCd() {
        return shipFromInvtyLocCd;
    }

    /**
     * @param shipFromInvtyLocCd String
     */
    public void setShipFromInvtyLocCd(String shipFromInvtyLocCd) {
        this.shipFromInvtyLocCd = shipFromInvtyLocCd;
    }

    // WDS Add End R-OM031
    // WDS Add Start R-OM050
    /**
     * @return cpoOrdTpCd
     */
    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    /**
     * @param cpoOrdTpCd String
     */
    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    }

    // WDS Add End R-OM050

//CSA ADD Start
    /**
     * @return dsInvLineTaxDtlList
     */
    public List<DS_INV_LINE_TAX_DTLTMsg> getDsInvLineTaxDtlList() {
        return dsInvLineTaxDtlList;
    }

    /**
     * @return crRebilCd
     */
    public String crRebilCd() {
        return crRebilCd;
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

    /**
     * @return billWithEquipFlg
     */
    public String getBillWithEquipFlg() {
        return billWithEquipFlg;
    }
    /**
     * @return billWithEquipFlg
     */
    public String getSetItemDummyFlg() {
        return setItemDummyFlg;
    }

    /**
     * @return invBolBean
     */
    public INV_BOLBean getInvBolBean() {
        return invBolBean;
    }

    /**
     * @param invBolBean
     */
    public void setInvBolBean(INV_BOLBean invBolBean) {
        this.invBolBean = invBolBean;
    }

    //QC#6832 2016/04/12 add Start
    /**
     * @return poReqFlg
     */
    public String getPoReqFlg() {
        return poReqFlg;
    }
   //QC#6832 2016/04/12 add End
    //QC#16237 add Start
    /**
     * @return existContr
     */
    public boolean isExistContr() {
        return existContr;
    }

    /**
     * @param existContr
     */
    public void setExistContr(boolean existContr) {
        this.existContr = existContr;
    }
    //QC#16237 add End
    // QC#21841 2018/05/21 Add Start
    /**
     * @param invLineCatgCd
     */
    public String getInvLineCtgCd() {
        return invLineCatgCd;
    }

    /**
     * @return invBolBean
     */
    public void setInvLineCtgCd(String invLineCatgCd) {
        this.invLineCatgCd = invLineCatgCd;
    }
    /**
     * copyOrignInvLine
     * @param origInvLineBean String
     */
    public void copyOrignInvLine(INV_LINEBean origInvLineBean){
        this.msgMap = origInvLineBean.msgMap;

        INV_LINETMsg origInvLineTMsg = origInvLineBean.getInvLineTMsg();
        this.invLineTMsg = new INV_LINETMsg();
        EZDMsg.copy(msgMap.getPmsg(), null, invLineTMsg, null);
        EZDMsg.copy(origInvLineTMsg, null, this.invLineTMsg, null);
        this.pmtTermCd = origInvLineBean.pmtTermCd;
        this.cashDiscTermCd = origInvLineBean.cashDiscTermCd;
        this.dealCcyCd = origInvLineBean.dealCcyCd;
        this.soNum = origInvLineBean.soNum;
        this.bolNum = origInvLineBean.bolNum;
        this.proNum = origInvLineBean.proNum;
        this.invRcpntCustCd = origInvLineBean.invRcpntCustCd;
        this.billToCustAcctCd = origInvLineBean.billToCustAcctCd;
        this.billToCtacPsnFirstNm = origInvLineBean.billToCtacPsnFirstNm;
        this.billToCtacPsnMidNm = origInvLineBean.billToCtacPsnMidNm;
        this.billToCtacPsnLastNm = origInvLineBean.billToCtacPsnLastNm;
        this.origInvNum = origInvLineBean.origInvNum;
        this.shipToCustAcctCd = origInvLineBean.shipToCustAcctCd;
        this.shipToCustLocCd = origInvLineBean.shipToCustLocCd;
        this.shipToCtacPsnPk = origInvLineBean.shipToCtacPsnPk;
        this.shipFromInvtyLocCd = origInvLineBean.shipFromInvtyLocCd;
        // 2019/06/03 QC#50654 Add Start
        this.origInvtyLocCd = origInvLineBean.origInvtyLocCd;
        // 2019/06/03 QC#50654 Add End
        this.dsInvLineTaxDtlList = new ArrayList<DS_INV_LINE_TAX_DTLTMsg>();
    }
    // QC#21841 2018/05/21 Add End
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
//CSA ADD End
}
