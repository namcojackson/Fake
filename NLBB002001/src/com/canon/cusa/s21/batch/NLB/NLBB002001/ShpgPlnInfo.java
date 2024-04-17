/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLB.NLBB002001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *<pre>
 * Routing Wave
 * Shipping Plan Related Information
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/30/2009   Fujitsu         K.Ozasa         Create          N/A
 * 27/10/2015   CITS            M.ITO           Update          N/A
 * 02/10/2017   CITS            K.Kameoka       Update          QC#17395
 * 03/08/2017   CITS            R.Shimamoto     Update          QC#17395-1
 *</pre>
 */
public class ShpgPlnInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** INVTY_LOC_CD */
    private String invtyLocCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** TRX_SRC_TP_CD */
    private String trxSrcTpCd;

    /** RDD_DT */
    private String rddDt;

    /** RSD_DT */
    private String rsdDt;

    /** DROP_SHIP_FLG */
    private String dropShipFlg;

    /** REQ_FRT_CHRG_TO_CD */
    private String reqFrtChrgToCd;

    /** REQ_FRT_CHRG_METH_CD */
    private String reqFrtChrgMethCd;

    /** RQST_CARR_CD */
    private String rqstCarrCd;

    /** CARR_ACCT_NUM */
    private String carrAcctNum;

    /** SHIP_TO_POST_CD */
    private String shipToPostCd;

    /** SHIP_TO_FIRST_LINE_ADDR */
    private String shipToFirstLineAddr;

    /** SHIP_TO_SCD_LINE_ADDR */
    private String shipToScdLineAddr;

    /** SHIP_TO_THIRD_LINE_ADDR */
    private String shipToThirdLineAddr;

    /** SHIP_TO_FRTH_LINE_ADDR */
    private String shipToFrthLineAddr;

    /** ORIG_SHPG_SVC_LVL_CD */
    private String origShpgSvcLvlCd;

    /** TRX_HDR_NUM */
    private String trxHdrNum;

    /** MDSE_CD */
    private String mdseCd;

    /** CUST_UOM_CD */
    private String custUomCd;

    /** SHPG_PLN_NUM */
    private String shpgPlnNum;

    /** AVAL_SO_QTY */
    private BigDecimal avalSoQty;

    /** TRX_LINE_NUM */
    private String trxLineNum;

    /** TRX_LINE_SUB_NUM */
    private String trxLineSubNum;

    /** SHPG_STS_CD */
    private String shpgStsCd;

    /** RTE_STS_CD */
    private String rteStsCd;

    /** SELL_TO_CUST_CD */
    private String sellToCustCd;

    /** SHIP_TO_ST_CD */
    private String shipToStCd;

    /** SHPG_ORD_CRAT_TP_CD */
    private String shpgOrdCratTpCd;

    /** CPO_EXST_FLG */
    private String cpoExstFlg;

    /** IN_POUND_WT */
    private BigDecimal inPoundWt;

    /** CPO_ORD_TP_CD */
    private String cpoOrdTpCd;

    /** SHPG_PLN_WT */
    private BigDecimal shpgPlnWt;

    /** SHPG_MODE_CD */
    private String shpgModeCd;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd;

    /** SDD_DT */
    private String sddDt;

    /** PSD_DT */
    private String psdDt;

    /** PDD_DT */
    private String pddDt;

    /** EXPT_FLG */
    private String exptFlg;

    /** DS_ORD_POSN_NUM */
    private String dsOrdPosnNum;

    /** CONFIG_ITEM_FLG */
    private String configItemFlg;

    /** CUST_EX_FOR_LT_CALC_FLG */
    private String custExForLtCalcFlg;

    /** HAZ_MAT_FLG */
    private String hazMatFlg;

    /** CONFIG_LT_DAY_NUM */
    private BigDecimal configLtDayNum;

    /** CONFIG_LT_DAY_NUM */
    private String invtyLocTpCd;

    /** RTL_WH_CD */
    private String rtlWhCd;

    //QC#17935 Start
    /** WH_OWNR_CD */
    private String whOwnrCd;

    //QC#17935 End

    //QC#17935-1 Start
    /** SVC_CONFIG_MSTR_PK */
    private String svcConfigMstrPk;

    /** SVC_MACH_MSTR_PK */
    private String svcMachMstrPk;

    //QC#17935-1 End

    /** DS_ORD_CATG_CD */
    private String dsOrdCatgCd;

    /** DS_ORD_TP_CD */
    private String dsOrdTpCd;

    /** componentList */
    private List<ShipCpltComponentBean> componentList = new ArrayList<ShipCpltComponentBean>();

    /**
     * Constructor
     */
    public ShpgPlnInfo() {

    }

    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    /**
     * Get GLBL_CMPY_CD
     * @return GLBL_CMPY_CD
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * Set GLBL_CMPY_CD
     * @param glblCmpyCd GLBL_CMPY_CD
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * Get INVTY_LOC_CD
     * @return invtyLocCd
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * Set INVTY_LOC_CD
     * @param invtyLocCd INVTY_LOC_CD
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * Get SHIP_TO_CUST_CD
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * Set SHIP_TO_CUST_CD
     * @param shipToCustCd SHIP_TO_CUST_CD
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * Get TRX_SRC_TP_CD
     * @return trxSrcTpCd
     */
    public String getTrxSrcTpCd() {
        return trxSrcTpCd;
    }

    /**
     * Set TRX_SRC_TP_CD
     * @param trxSrcTpCd TRX_SRC_TP_CD
     */
    public void setTrxSrcTpCd(String trxSrcTpCd) {
        this.trxSrcTpCd = trxSrcTpCd;
    }

    /**
     * Get RDD_DT
     * @return rddDt
     */
    public String getRddDt() {
        return rddDt;
    }

    /**
     * Set RDD_DT
     * @param rddDt RDD_DT
     */
    public void setRddDt(String rddDt) {
        this.rddDt = rddDt;
    }

    /**
     * Get RSD_DT
     * @return rsdDt
     */
    public String getRsdDt() {
        return rsdDt;
    }

    /**
     * Set RSD_DT
     * @param rsdDt RSD_DT
     */
    public void setRsdDt(String rsdDt) {
        this.rsdDt = rsdDt;
    }

    /**
     * Get DROP_SHIP_FLG
     * @return dropShipFlg
     */
    public String getDropShipFlg() {
        return dropShipFlg;
    }

    /**
     * Set DROP_SHIP_FLG
     * @param dropShipFlg DROP_SHIP_FLG
     */
    public void setDropShipFlg(String dropShipFlg) {
        this.dropShipFlg = dropShipFlg;
    }

    /**
     * Get REQ_FRT_CHRG_TO_CD
     * @return reqFrtChrgToCd
     */
    public String getReqFrtChrgToCd() {
        return reqFrtChrgToCd;
    }

    /**
     * Set REQ_FRT_CHRG_TO_CD
     * @param reqFrtChrgToCd REQ_FRT_CHRG_TO_CD
     */
    public void setReqFrtChrgToCd(String reqFrtChrgToCd) {
        this.reqFrtChrgToCd = reqFrtChrgToCd;
    }

    /**
     * Get REQ_FRT_CHRG_METH_CD
     * @return reqFrtChrgMethCd
     */
    public String getReqFrtChrgMethCd() {
        return reqFrtChrgMethCd;
    }

    /**
     * Set REQ_FRT_CHRG_METH_CD
     * @param reqFrtChrgMethCd REQ_FRT_CHRG_METH_CD
     */
    public void setReqFrtChrgMethCd(String reqFrtChrgMethCd) {
        this.reqFrtChrgMethCd = reqFrtChrgMethCd;
    }

    /**
     * Get RQST_CARR_CD
     * @return rqstCarrCd
     */
    public String getRqstCarrCd() {
        return rqstCarrCd;
    }

    /**
     * Set RQST_CARR_CD
     * @param rqstCarrCd RQST_CARR_CD
     */
    public void setRqstCarrCd(String rqstCarrCd) {
        this.rqstCarrCd = rqstCarrCd;
    }

    /**
     * Get CARR_ACCT_NUM
     * @return carrAcctNum
     */
    public String getCarrAcctNum() {
        return carrAcctNum;
    }

    /**
     * Set CARR_ACCT_NUM
     * @param carrAcctNum CARR_ACCT_NUM
     */
    public void setCarrAcctNum(String carrAcctNum) {
        this.carrAcctNum = carrAcctNum;
    }

    /**
     * Get SHIP_TO_POST_CD
     * @return shipToPostCd
     */
    public String getShipToPostCd() {
        return shipToPostCd;
    }

    /**
     * Set SHIP_TO_POST_CD
     * @param shipToPostCd SHIP_TO_POST_CD
     */
    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    /**
     * Get SHIP_TO_FIRST_LINE_ADDR
     * @return shipToFirstLineAddr
     */
    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    /**
     * Set SHIP_TO_FIRST_LINE_ADDR
     * @param shipToFirstLineAddr SHIP_TO_FIRST_LINE_ADDR
     */
    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    /**
     * Get SHIP_TO_SCD_LINE_ADDR
     * @return shipToScdLineAddr
     */
    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    /**
     * Set SHIP_TO_SCD_LINE_ADDR
     * @param shipToScdLineAddr SHIP_TO_SCD_LINE_ADDR
     */
    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    /**
     * Get SHIP_TO_THIRD_LINE_ADDR
     * @return shipToThirdLineAddr
     */
    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    /**
     * Set SHIP_TO_THIRD_LINE_ADDR
     * @param shipToThirdLineAddr SHIP_TO_THIRD_LINE_ADDR
     */
    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    /**
     * Get SHIP_TO_FRTH_LINE_ADDR
     * @return shipToFrthLineAddr
     */
    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    /**
     * Set SHIP_TO_FRTH_LINE_ADDR
     * @param shipToFrthLineAddr SHIP_TO_FRTH_LINE_ADDR
     */
    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    /**
     * Get ORIG_SHPG_SVC_LVL_CD
     * @return origShpgSvcLvlCd
     */
    public String getOrigShpgSvcLvlCd() {
        return origShpgSvcLvlCd;
    }

    /**
     * Set ORIG_SHPG_SVC_LVL_CD
     * @param origShpgSvcLvlCd ORIG_SHPG_SVC_LVL_CD
     */
    public void setOrigShpgSvcLvlCd(String origShpgSvcLvlCd) {
        this.origShpgSvcLvlCd = origShpgSvcLvlCd;
    }

    /**
     * Get TRX_HDR_NUM
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    /**
     * Set TRX_HDR_NUM
     * @param trxHdrNum TRX_HDR_NUM
     */
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }

    /**
     * Get MDSE_CD
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * Set MDSE_CD
     * @param mdseCd MDSE_CD
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * Get CUST_UOM_CD
     * @return custUomCd
     */
    public String getCustUomCd() {
        return custUomCd;
    }

    /**
     * Set CUST_UOM_CD
     * @param custUomCd CUST_UOM_CD
     */
    public void setCustUomCd(String custUomCd) {
        this.custUomCd = custUomCd;
    }

    /**
     * Get SHPG_PLN_NUM
     * @return shpgPlnNum
     */
    public String getShpgPlnNum() {
        return shpgPlnNum;
    }

    /**
     * Set SHPG_PLN_NUM
     * @param shpgPlnNum SHPG_PLN_NUM
     */
    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }

    /**
     * Get AVAL_SO_QTY
     * @return avalSoQty
     */
    public BigDecimal getAvalSoQty() {
        return avalSoQty;
    }

    /**
     * Set AVAL_SO_QTY
     * @param avalSoQty AVAL_SO_QTY
     */
    public void setAvalSoQty(BigDecimal avalSoQty) {
        this.avalSoQty = avalSoQty;
    }

    /**
     * Get TRX_LINE_NUM
     * @return trxLineNum
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    /**
     * Set TRX_LINE_NUM
     * @param trxLineNum TRX_LINE_NUM
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    /**
     * Get SHPG_STS_CD
     * @return shpgStsCd
     */
    public String getShpgStsCd() {
        return shpgStsCd;
    }

    /**
     * Set SHPG_STS_CD
     * @param shpgStsCd SHPG_STS_CD
     */
    public void setShpgStsCd(String shpgStsCd) {
        this.shpgStsCd = shpgStsCd;
    }

    /**
     * Get RTE_STS_CD
     * @return rteStsCd
     */
    public String getRteStsCd() {
        return rteStsCd;
    }

    /**
     * Set RTE_STS_CD
     * @param rteStsCd RTE_STS_CD
     */
    public void setRteStsCd(String rteStsCd) {
        this.rteStsCd = rteStsCd;
    }

    /**
     * Get SELL_TO_CUST_CD
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * Set SELL_TO_CUST_CD
     * @param sellToCustCd SELL_TO_CUST_CD
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * Get SHIP_TO_ST_CD
     * @return shipToStCd
     */
    public String getShipToStCd() {
        return shipToStCd;
    }

    /**
     * Set SHIP_TO_ST_CD
     * @param shipToStCd SHIP_TO_ST_CD
     */
    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    /**
     * Get SHPG_ORD_CRAT_TP_CD
     * @return shpgOrdCratTpCd
     */
    public String getShpgOrdCratTpCd() {
        return shpgOrdCratTpCd;
    }

    /**
     * Set SHPG_ORD_CRAT_TP_CD
     * @param shpgOrdCratTpCd SHPG_ORD_CRAT_TP_CD
     */
    public void setShpgOrdCratTpCd(String shpgOrdCratTpCd) {
        this.shpgOrdCratTpCd = shpgOrdCratTpCd;
    }

    /**
     * Get CPO_EXST_FLG
     * @return cpoExstFlg
     */
    public String getCpoExstFlg() {
        return cpoExstFlg;
    }

    /**
     * Set CPO_EXST_FLG
     * @param cpoExstFlg CPO_EXST_FLG
     */
    public void setCpoExstFlg(String cpoExstFlg) {
        this.cpoExstFlg = cpoExstFlg;
    }

    /**
     * Get IN_POUND_WT
     * @return inPoundWt
     */
    public BigDecimal getInPoundWt() {
        return inPoundWt;
    }

    /**
     * Set IN_POUND_WT
     * @param inPoundWt IN_POUND_WT
     */
    public void setInPoundWt(BigDecimal inPoundWt) {
        this.inPoundWt = inPoundWt;
    }

    /**
     * Get CPO_ORD_TP_CD
     * @return cpoOrdTpCd
     */
    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    /**
     * Set CPO_ORD_TP_CD
     * @param cpoOrdTpCd CPO_ORD_TP_CD
     */
    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    }

    /**
     * Get SHPG_PLN_WT
     * @return shpgPlnWt
     */
    public BigDecimal getShpgPlnWt() {
        return shpgPlnWt;
    }

    /**
     * Set SHPG_PLN_WT
     * @param shpgPlnWt SHPG_PLN_WT
     */
    public void setShpgPlnWt(BigDecimal shpgPlnWt) {
        this.shpgPlnWt = shpgPlnWt;
    }

    /**
     * Get SHPG_MODE_CD
     * @return shpgModeCd
     */
    public String getShpgModeCd() {
        return shpgModeCd;
    }

    /**
     * Set SHPG_MODE_CD
     * @param shpgModeCd SHPG_MODE_CD
     */
    public void setShpgModeCd(String shpgModeCd) {
        this.shpgModeCd = shpgModeCd;
    }

    /**
     * Get SHPG_SVC_LVL_CD
     * @return shpgSvcLvlCd
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * Set SHPG_SVC_LVL_CD
     * @param shpgSvcLvlCd SHPG_SVC_LVL_CD
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    /**
     * Get SDD_DT
     * @return sddDt
     */
    public String getSddDt() {
        return sddDt;
    }

    /**
     * Set SDD_DT
     * @param sddDt SDD_DT
     */
    public void setSddDt(String sddDt) {
        this.sddDt = sddDt;
    }

    /**
     * Get PSD_DT
     * @return psdDt
     */
    public String getPsdDt() {
        return psdDt;
    }

    /**
     * Set PSD_DT
     * @param psdDt PSD_DT
     */
    public void setPsdDt(String psdDt) {
        this.psdDt = psdDt;
    }

    /**
     * Get PDD_DT
     * @return pddDt
     */
    public String getPddDt() {
        return pddDt;
    }

    /**
     * Set PDD_DT
     * @param pddDt PDD_DT
     */
    public void setPddDt(String pddDt) {
        this.pddDt = pddDt;
    }

    /**
     * Get EXPT_FLG
     * @return exptFlg
     */
    public String getExptFlg() {
        return exptFlg;
    }

    /**
     * Set EXPT_FLG
     * @param exptFlg EXPT_FLG
     */
    public void setExptFlg(String exptFlg) {
        this.exptFlg = exptFlg;
    }

    /**
     * Get DS_ORD_POSN_NUM
     * @return dsOrdPosnNum
     */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    /**
     * Set DS_ORD_POSN_NUM
     * @param dsOrdPosnNum DS_ORD_POSN_NUM
     */
    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }

    /**
     * Get RTL_WH_CD
     * @return RTL_WH_CD
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /**
     * Set RTL_WH_CD
     * @param whCd RTL_WH_CD
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }
    
    // QC#17395  Start
    /**
     * Get WH_OWNR_CD
     * @return WH_OWNR_CD
     */
    public String getWhOwnrCd() {
        return whOwnrCd;
    }

    /**
     * Set WH_OWNR_CD
     * @param whOwnrCd WH_OWNR_CD
     */
    public void setWhOwnrCd(String whOwnrCd) {
        this.whOwnrCd = whOwnrCd;
    }

    /**
     * Get SVC_CONFIG_MSTR_PK
     * @return SVC_CONFIG_MSTR_PK
     */
    public String getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * Set SVC_CONFIG_MSTR_PK
     * @param svcConfigMstrPk SVC_CONFIG_MSTR_PK
     */
    public void setSvcConfigMstrPk(String svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }
    // QC#17395  End
    
    /**
     * Get SVC_MACH_MSTR_PK
     * @return SVC_MACH_MSTR_PK
     */
    public String getSvcMachgMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * Set SVC_MACH_MSTR_PK
     * @param svcMachMstrPk SVC_MACH_MSTR_PK
     */
    public void setSvcMachMstrPk(String svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * Get DS_ORD_CATG_CD
     * @return DS_ORD_CATG_CD
     */
    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    /**
     * Set DS_ORD_CATG_CD
     * @param dsOrdCatgCd DS_ORD_CATG_CD
     */
    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    /**
     * Get componentList
     * @return componentList
     */
    public List<ShipCpltComponentBean> getComponentList() {
        return componentList;
    }

    /**
     * Set componentList
     * @param componentList ShipCpltComponentBean
     */
    public void setComponentList(List<ShipCpltComponentBean> componentList) {
        this.componentList = componentList;
    }

    /**
     * Convert Properties To String
     * @return String of Properties
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + glblCmpyCd          = " + glblCmpyCd);
        append(sb, " + invtyLocCd          = " + invtyLocCd);
        append(sb, " + shipToCustCd        = " + shipToCustCd);
        append(sb, " + trxSrcTpCd          = " + trxSrcTpCd);
        append(sb, " + rddDt               = " + rddDt);
        append(sb, " + rsdDt               = " + rsdDt);
        append(sb, " + dropShipFlg         = " + dropShipFlg);
        append(sb, " + reqFrtChrgToCd      = " + reqFrtChrgToCd);
        append(sb, " + reqFrtChrgMethCd    = " + reqFrtChrgMethCd);
        append(sb, " + rqstCarrCd          = " + rqstCarrCd);
        append(sb, " + carrAcctNum         = " + carrAcctNum);
        append(sb, " + shipToPostCd        = " + shipToPostCd);
        append(sb, " + shipToFirstLineAddr = " + shipToFirstLineAddr);
        append(sb, " + shipToScdLineAddr   = " + shipToScdLineAddr);
        append(sb, " + shipToThirdLineAddr = " + shipToThirdLineAddr);
        append(sb, " + shipToFrthLineAddr  = " + shipToFrthLineAddr);
        append(sb, " + origShpgSvcLvlCd    = " + origShpgSvcLvlCd);
        append(sb, " + trxHdrNum           = " + trxHdrNum);
        append(sb, " + mdseCd              = " + mdseCd);
        append(sb, " + custUomCd           = " + custUomCd);
        append(sb, " + shpgPlnNum          = " + shpgPlnNum);
        append(sb, " + avalSoQty           = " + avalSoQty);
        append(sb, " + trxLineNum          = " + trxLineNum);
        append(sb, " + shpgStsCd           = " + shpgStsCd);
        append(sb, " + rteStsCd            = " + rteStsCd);
        append(sb, " + sellToCustCd        = " + sellToCustCd);
        append(sb, " + shipToStCd          = " + shipToStCd);
        append(sb, " + shpgOrdCratTpCd     = " + shpgOrdCratTpCd);
        append(sb, " + cpoExstFlg          = " + cpoExstFlg);
        append(sb, " + inPoundWt           = " + inPoundWt);
        append(sb, " + cpoOrdTpCd          = " + cpoOrdTpCd);
        append(sb, " + shpgPlnWt           = " + shpgPlnWt);
        append(sb, " + shpgModeCd          = " + shpgModeCd);
        append(sb, " + shpgSvcLvlCd        = " + shpgSvcLvlCd);
        append(sb, " + sddDt               = " + sddDt);
        append(sb, " + psdDt               = " + psdDt);
        append(sb, " + pddDt               = " + pddDt);
        append(sb, " + exptFlg             = " + exptFlg);
        append(sb, " + dsOrdPosnNum        = " + dsOrdPosnNum);
        append(sb, " + configItemFlg       = " + configItemFlg);
        append(sb, " + custExForLtCalcFlg  = " + custExForLtCalcFlg);
        append(sb, " + hazMatFlg           = " + hazMatFlg);
        append(sb, " + configLtDayNum      = " + configLtDayNum);
        append(sb, " + invtyLocTpCd        = " + invtyLocTpCd);
        append(sb, " + rtlWhCd             = " + rtlWhCd);
        // QC#17395  Start
        append(sb, " + whOwnrCd            = " + whOwnrCd);
        append(sb, " + svcMachMstrPk       = " + svcMachMstrPk);
        append(sb, " + svcConfigMstrPk     = " + svcConfigMstrPk);
        // QC#17395  End
        append(sb, " + dsOrdCatgCd         = " + dsOrdCatgCd);
        return sb.toString();
    }

    /**
     * Append String and Line Feed Code to StringBuilder
     * @param sb StringBuilder
     * @param str String
     */
    private void append(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\r\n");
    }

    /**
     * @return configItemFlg
     */
    public String getConfigItemFlg() {
        return configItemFlg;
    }

    /**
     * @param configItemFlg configItemFlg
     */
    public void setConfigItemFlg(String configItemFlg) {
        this.configItemFlg = configItemFlg;
    }

    /**
     * @return custExForLtCalcFlg
     */
    public String getCustExForLtCalcFlg() {
        return custExForLtCalcFlg;
    }

    /**
     * @param custExForLtCalcFlg custExForLtCalcFlg
     */
    public void setCustExForLtCalcFlg(String custExForLtCalcFlg) {
        this.custExForLtCalcFlg = custExForLtCalcFlg;
    }

    /**
     * @return hazMatFlg
     */
    public String getHazMatFlg() {
        return hazMatFlg;
    }

    /**
     * @param hazMatFlg hazMatFlg
     */
    public void setHazMatFlg(String hazMatFlg) {
        this.hazMatFlg = hazMatFlg;
    }

    /**
     * @return configLtDayNum
     */
    public BigDecimal getConfigLtDayNum() {
        return configLtDayNum;
    }

    /**
     * @param configLtDayNum configLtDayNum
     */
    public void setConfigLtDayNum(BigDecimal configLtDayNum) {
        this.configLtDayNum = configLtDayNum;
    }

    /**
     * Get INVTY_LOC_TP_CD
     * @return INVTY_LOC_TP_CD
     */
    public String getInvtyLocTpCd() {
        return invtyLocTpCd;
    }

    /**
     * Set INVTY_LOC_TP_CD
     * @param invtyLocTpCd INVTY_LOC_TP_CD
     */
    public void setInvtyLocTpCd(String invtyLocTpCd) {
        this.invtyLocTpCd = invtyLocTpCd;
    }

    /**
     * @param dsOrdTpCd Set dsOrdTpCd
     */
    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    /**
     * @return dsOrdTpCd
     */
    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }
}
