// START 2017/05/25 [RS#8316,ADD]
//package com.canon.cusa.s21.batch.NFA.NFAB104001;
package com.canon.cusa.s21.batch.NLC.NLCB801501;
// END 2017/05/25 [RS#8316,ADD]

import java.math.BigDecimal;

/**
 * <pre>
 * InvtySlsCrDist Class
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/24/2015   CSAI            K.Uramori       Create              
 * 09/07/2016   Hitachi         T.Tsuchida      Update          QC#14260
 * 05/25/2017   CITS            T.Ono           Update          RS#8316 
 *</pre>
 */
/**
 * @author H00180
 */
public class InvtySlsCrDist {

    /** Global Company Code */
    private String glblCmpyCd;

    /** AJE_INVTY_INTFC_PK */
    private BigDecimal ajeInvtyIntfcPk;

    /** GL Date */
    private String glDt;

    /** INVTY_TRX_PK */
    private BigDecimal invtyTrxPk;

    /** SYS_SRC_CD */
    private String sysSrcCd;

    /** TRX_CD */
    private String trxCd;

    /** TRX_RSN_CD */
    private String trxRsnCd;

    /** INV_INVTY_IND_TP_CD */
    private String invInvtyIndTpCd;

    /** BILL_TO_CUST_CD */
    private String billToCustCd;

    /** SELL_TO_CUST_CD */
    private String sellToCustCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** INV_NUM */
    private String invNum;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** SO_NUM */
    private String soNum;

    /** MDSE_CD */
    private String mdseCd;

    /** MDSE_OR_PRT_CD */
    private String mdseOrPrtCd;

    /** INVTY_LOC_CD */
    private String invtyLocCd;

    /** TOC_CD */
    private String tocCd;

    /** VND_CD */
    private String vndCd;

    /** CCY_CD */
    private String ccyCd;

    /** INVTY_TRX_QTY */
    private BigDecimal invtyTrxQty;

    /** UNIT_COST_AMT */
    private BigDecimal unitCostAmt;

    /** SHIP_COST_AMT */
    private BigDecimal shipCostAmt;

    /** DEPC_AMT */
    private BigDecimal depcAmt;

    /** COA_ACCT_CD */
    private String coaAcctCd;

    /** COA_PROD_CD */
    private String coaProdCd;

    /** EXP_PROJ_CD */
    private String expProjCd;

    /** COA_BR_CD */
    private String coaBrCd;

    /** COA_CC_CD */
    private String coaCcCd;

    /** COA_CH_CD */
    private String coaChCd;

    /** AJE_INTFC_CMNT_TXT */
    private String ajeIntfcCmntTxt;

    /** DS_ACCT_NUM */
    private String dsAcctNum;

    /** INVTY_TRX_SLP_NUM */
    private String invtyTrxSlpNum;

    /** INVTY_TRX_REF_NUM */
    private String invtyTrxRefNum;

    /** INVTY_ORD_NUM */
    private String invtyOrdNum;

    /** INVTY_ORD_LINE_NUM */
    private String invtyOrdLineNum;

    /** RWS_NUM */
    private String rwsNum;

    /** WRK_ORD_NUM */
    private String wrkOrdNum;

    /** CPO_DTL_LINE_NUM */
    private String cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM */
    private String cpoDtlLineSubNum;

    /** DS_CPO_SLS_CR_PK */
    private BigDecimal dsCpoSlsCrPk;

    /** SLS_REP_CR_PCT */
    private BigDecimal slsRepCrPct;

    /** DR_INVTY_ORD_CMPY_CD */
    private String drInvtyOrdCmpyCd;

    /** DR_INVTY_ORD_BR_CD */
    private String drInvtyOrdBrCd;

    /** DR_INVTY_ORD_CC_CD */
    private String drInvtyOrdCcCd;

    /** DR_INVTY_ORD_ACCT_CD */
    private String drInvtyOrdAcctCd;

    /** DR_INVTY_ORD_PROD_CD */
    private String drInvtyOrdProdCd;

    /** DR_INVTY_ORD_CH_CD */
    private String drInvtyOrdChCd;

    /** DR_INVTY_ORD_AFFL_CD */
    private String drInvtyOrdAfflCd;

    /** DR_INVTY_ORD_PROJ_CD */
    private String drInvtyOrdProjCd;

    /** DR_INVTY_ORD_EXTN_CD */
    private String drInvtyOrdExtnCd;

    /** CR_INVTY_ORD_CMPY_CD */
    private String crInvtyOrdCmpyCd;

    /** CR_INVTY_ORD_BR_CD */
    private String crInvtyOrdBrCd;

    /** CR_INVTY_ORD_CC_CD */
    private String crInvtyOrdCcCd;

    /** CR_INVTY_ORD_ACCT_CD */
    private String crInvtyOrdAcctCd;

    /** CR_INVTY_ORD_PROD_CD */
    private String crInvtyOrdProdCd;

    /** CR_INVTY_ORD_CH_CD */
    private String crInvtyOrdChCd;

    /** CR_INVTY_ORD_AFFL_CD */
    private String crInvtyOrdAfflCd;

    /** CR_INVTY_ORD_PROJ_CD */
    private String crInvtyOrdProjCd;

    /** CR_INVTY_ORD_EXTN_CD */
    private String crInvtyOrdExtnCd;

    /** PRCH_PRC_AMT */
    private BigDecimal prchPrcAmt;

    /** SHIP_FROM_UNIT_COST_AMT */
    private BigDecimal shipFromUnitCostAmt;

    /** SHIP_FROM_AMT */
    private BigDecimal shipFromAmt;

    /** RMNF_WIP_AMT */
    private BigDecimal rmnfWipAmt;

    /** SVC_MACH_MSTR_PK */
    private BigDecimal svcMachMstrPk;

    /** MACH_MDSE_CD */
    private String machMdseCd;

    /** FIN_BR_CD */
    private String finBrCd;

    /** SER_NUM */
    private String serNum;

    /** RTL_WH_CD */
    private String rtlWhCd;

    /** ORIG_RTL_WH_CD */
    private String origRtlWhCd;

    /** RMNF_WIP_CPLT_FLG */
    private String rmnfWipCpltFlg;

    /** PO_ORD_NUM */
    private String poOrdNum;

    /** PO_ORD_DTL_LINE_NUM */
    private String poOrdDtlLineNum;

    // START 2016/09/07 T.Tsuchida [QC#14260,ADD]
    /** RMA_NUM */
    private String rmaNum;

    /** RMA_LINE_NUM */
    private String rmaLineNum;

    /** RMA_LINE_SUB_NUM */
    private String rmaLineSubNum;

    // END 2016/09/07 T.Tsuchida [QC#14260,ADD]

    /** Divided Ship Cost Amount */
    private BigDecimal dividedShipCostAmt;

    /** Divided Ship Cost Amount For Ship From */
    private BigDecimal dividedShipFromCostAmt;

    /** SLS_CR_QUOT_FLG */
    private String slsCrQuotFlg;

    /**
     * getter
     * @return String
     */
    public String getSlsCrQuotFlg() {
        return slsCrQuotFlg;
    }

    /**
     * setter
     * @param slsCrQuotFlg String
     */
    public void setSlsCrQuotFlg(String slsCrQuotFlg) {
        this.slsCrQuotFlg = slsCrQuotFlg;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getDividedShipFromCostAmt() {
        return dividedShipFromCostAmt;
    }

    /**
     * setter
     * @param dividedShipFromCostAmt BigDecimal
     */
    public void setDividedShipFromCostAmt(BigDecimal dividedShipFromCostAmt) {
        this.dividedShipFromCostAmt = dividedShipFromCostAmt;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getDsCpoSlsCrPk() {
        return dsCpoSlsCrPk;
    }

    /**
     * setter
     * @param dsCpoSlsCrPk BigDecimal
     */
    public void setDsCpoSlsCrPk(BigDecimal dsCpoSlsCrPk) {
        this.dsCpoSlsCrPk = dsCpoSlsCrPk;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getSlsRepCrPct() {
        return slsRepCrPct;
    }

    /**
     * setter
     * @param slsRepCrPct BigDecimal
     */
    public void setSlsRepCrPct(BigDecimal slsRepCrPct) {
        this.slsRepCrPct = slsRepCrPct;
    }

    /**
     * getter
     * @return String
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * setter
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * getter
     * @return String
     */
    public String getGlDt() {
        return glDt;
    }

    /**
     * setter
     * @param glDt String
     */
    public void setGlDt(String glDt) {
        this.glDt = glDt;
    }

    /**
     * getter
     * @return BigDecimal
     */
    public BigDecimal getAjeInvtyIntfcPk() {
        return ajeInvtyIntfcPk;
    }

    /**
     * setter
     * @param ajeInvtyIntfcPk BigDecimal
     */
    public void setAjeInvtyIntfcPk(BigDecimal ajeInvtyIntfcPk) {
        this.ajeInvtyIntfcPk = ajeInvtyIntfcPk;
    }

    /**
     * getter
     * @return BigDecimal
     */
    public BigDecimal getInvtyTrxPk() {
        return invtyTrxPk;
    }

    /**
     * setter
     * @param invtyTrxPk BigDecimal
     */
    public void setInvtyTrxPk(BigDecimal invtyTrxPk) {
        this.invtyTrxPk = invtyTrxPk;
    }

    /**
     * getter
     * @return String
     */
    public String getSysSrcCd() {
        return sysSrcCd;
    }

    /**
     * setter
     * @param sysSrcCd String
     */
    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }

    /**
     * getter
     * @return String
     */
    public String getTrxCd() {
        return trxCd;
    }

    /**
     * setter
     * @param trxCd String
     */
    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }

    /**
     * getter
     * @return String
     */
    public String getTrxRsnCd() {
        return trxRsnCd;
    }

    /**
     * setter
     * @param trxRsnCd String
     */
    public void setTrxRsnCd(String trxRsnCd) {
        this.trxRsnCd = trxRsnCd;
    }

    /**
     * getter
     * @return String
     */
    public String getInvInvtyIndTpCd() {
        return invInvtyIndTpCd;
    }

    /**
     * setter
     * @param invInvtyIndTpCd String
     */
    public void setInvInvtyIndTpCd(String invInvtyIndTpCd) {
        this.invInvtyIndTpCd = invInvtyIndTpCd;
    }

    /**
     * getter
     * @return String
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * setter
     * @param billToCustCd String
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * getter
     * @return String
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * setter
     * @param sellToCustCd String
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * getter
     * @return String
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * setter
     * @param shipToCustCd String
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * getter
     * @return String
     */
    public String getInvNum() {
        return invNum;
    }

    /**
     * setter
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    /**
     * getter
     * @return String
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * setter
     * @param cpoOrdNum String
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * getter
     * @return String
     */
    public String getSoNum() {
        return soNum;
    }

    /**
     * setter
     * @param soNum String
     */
    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }

    /**
     * getter
     * @return String
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * setter
     * @param mdseCd String
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * getter
     * @return String
     */
    public String getMdseOrPrtCd() {
        return mdseOrPrtCd;
    }

    /**
     * setter
     * @param mdseOrPrtCd String
     */
    public void setMdseOrPrtCd(String mdseOrPrtCd) {
        this.mdseOrPrtCd = mdseOrPrtCd;
    }

    /**
     * getter
     * @return String
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * setter
     * @param invtyLocCd String
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * getter
     * @return String
     */
    public String getTocCd() {
        return tocCd;
    }

    /**
     * setter
     * @param tocCd String
     */
    public void setTocCd(String tocCd) {
        this.tocCd = tocCd;
    }

    /**
     * getter
     * @return String
     */
    public String getVndCd() {
        return vndCd;
    }

    /**
     * setter
     * @param vndCd String
     */
    public void setVndCd(String vndCd) {
        this.vndCd = vndCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCcyCd() {
        return ccyCd;
    }

    /**
     * setter
     * @param ccyCd String
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getInvtyTrxQty() {
        return invtyTrxQty;
    }

    /**
     * setter
     * @param invtyTrxQty BigDecimal
     */
    public void setInvtyTrxQty(BigDecimal invtyTrxQty) {
        this.invtyTrxQty = invtyTrxQty;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getUnitCostAmt() {
        return unitCostAmt;
    }

    /**
     * setter
     * @param unitCostAmt BigDecimal
     */
    public void setUnitCostAmt(BigDecimal unitCostAmt) {
        this.unitCostAmt = unitCostAmt;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getShipCostAmt() {
        return shipCostAmt;
    }

    /**
     * setter
     * @param shipCostAmt BigDecimal
     */
    public void setShipCostAmt(BigDecimal shipCostAmt) {
        this.shipCostAmt = shipCostAmt;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getDepcAmt() {
        return depcAmt;
    }

    /**
     * setter
     * @param depcAmt BigDecimal
     */
    public void setDepcAmt(BigDecimal depcAmt) {
        this.depcAmt = depcAmt;
    }

    /**
     * getter
     * @return String
     */
    public String getCoaAcctCd() {
        return coaAcctCd;
    }

    /**
     * setter
     * @param coaAcctCd String
     */
    public void setCoaAcctCd(String coaAcctCd) {
        this.coaAcctCd = coaAcctCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCoaProdCd() {
        return coaProdCd;
    }

    /**
     * setter
     * @param coaProdCd String
     */
    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    /**
     * getter
     * @return String
     */
    public String getExpProjCd() {
        return expProjCd;
    }

    /**
     * setter
     * @param expProjCd String
     */
    public void setExpProjCd(String expProjCd) {
        this.expProjCd = expProjCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCoaBrCd() {
        return coaBrCd;
    }

    /**
     * setter
     * @param coaBrCd String
     */
    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCoaCcCd() {
        return coaCcCd;
    }

    /**
     * setter
     * @param coaCcCd String
     */
    public void setCoaCcCd(String coaCcCd) {
        this.coaCcCd = coaCcCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCoaChCd() {
        return coaChCd;
    }

    /**
     * setter
     * @param coaChCd String
     */
    public void setCoaChCd(String coaChCd) {
        this.coaChCd = coaChCd;
    }

    /**
     * getter
     * @return String
     */
    public String getAjeIntfcCmntTxt() {
        return ajeIntfcCmntTxt;
    }

    /**
     * setter
     * @param ajeIntfcCmntTxt String
     */
    public void setAjeIntfcCmntTxt(String ajeIntfcCmntTxt) {
        this.ajeIntfcCmntTxt = ajeIntfcCmntTxt;
    }

    /**
     * getter
     * @return String
     */
    public String getDsAcctNum() {
        return dsAcctNum;
    }

    /**
     * setter
     * @param dsAcctNum String
     */
    public void setDsAcctNum(String dsAcctNum) {
        this.dsAcctNum = dsAcctNum;
    }

    /**
     * getter
     * @return String
     */
    public String getInvtyTrxSlpNum() {
        return invtyTrxSlpNum;
    }

    /**
     * setter
     * @param invtyTrxSlpNum String
     */
    public void setInvtyTrxSlpNum(String invtyTrxSlpNum) {
        this.invtyTrxSlpNum = invtyTrxSlpNum;
    }

    /**
     * getter
     * @return String
     */
    public String getInvtyTrxRefNum() {
        return invtyTrxRefNum;
    }

    /**
     * setter
     * @param invtyTrxRefNum String
     */
    public void setInvtyTrxRefNum(String invtyTrxRefNum) {
        this.invtyTrxRefNum = invtyTrxRefNum;
    }

    /**
     * getter
     * @return String
     */
    public String getInvtyOrdNum() {
        return invtyOrdNum;
    }

    /**
     * setter
     * @param invtyOrdNum String
     */
    public void setInvtyOrdNum(String invtyOrdNum) {
        this.invtyOrdNum = invtyOrdNum;
    }

    /**
     * getter
     * @return String
     */
    public String getInvtyOrdLineNum() {
        return invtyOrdLineNum;
    }

    /**
     * setter
     * @param invtyOrdLineNum String
     */
    public void setInvtyOrdLineNum(String invtyOrdLineNum) {
        this.invtyOrdLineNum = invtyOrdLineNum;
    }

    /**
     * getter
     * @return String
     */
    public String getRwsNum() {
        return rwsNum;
    }

    /**
     * setter
     * @param rwsNum String
     */
    public void setRwsNum(String rwsNum) {
        this.rwsNum = rwsNum;
    }

    /**
     * getter
     * @return String
     */
    public String getWrkOrdNum() {
        return wrkOrdNum;
    }

    /**
     * setter
     * @param wrkOrdNum String
     */
    public void setWrkOrdNum(String wrkOrdNum) {
        this.wrkOrdNum = wrkOrdNum;
    }

    /**
     * getter
     * @return String
     */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /**
     * setter
     * @param cpoDtlLineNum String
     */
    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    /**
     * getter
     * @return String
     */
    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    /**
     * setter
     * @param cpoDtlLineSubNum String
     */
    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdCmpyCd() {
        return drInvtyOrdCmpyCd;
    }

    /**
     * setter
     * @param drInvtyOrdCmpyCd String
     */
    public void setDrInvtyOrdCmpyCd(String drInvtyOrdCmpyCd) {
        this.drInvtyOrdCmpyCd = drInvtyOrdCmpyCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdBrCd() {
        return drInvtyOrdBrCd;
    }

    /**
     * setter
     * @param drInvtyOrdBrCd String
     */
    public void setDrInvtyOrdBrCd(String drInvtyOrdBrCd) {
        this.drInvtyOrdBrCd = drInvtyOrdBrCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdCcCd() {
        return drInvtyOrdCcCd;
    }

    /**
     * setter
     * @param drInvtyOrdCcCd String
     */
    public void setDrInvtyOrdCcCd(String drInvtyOrdCcCd) {
        this.drInvtyOrdCcCd = drInvtyOrdCcCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdAcctCd() {
        return drInvtyOrdAcctCd;
    }

    /**
     * setter
     * @param drInvtyOrdAcctCd String
     */
    public void setDrInvtyOrdAcctCd(String drInvtyOrdAcctCd) {
        this.drInvtyOrdAcctCd = drInvtyOrdAcctCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdProdCd() {
        return drInvtyOrdProdCd;
    }

    /**
     * setter
     * @param drInvtyOrdProdCd String
     */
    public void setDrInvtyOrdProdCd(String drInvtyOrdProdCd) {
        this.drInvtyOrdProdCd = drInvtyOrdProdCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdChCd() {
        return drInvtyOrdChCd;
    }

    /**
     * setter
     * @param drInvtyOrdChCd String
     */
    public void setDrInvtyOrdChCd(String drInvtyOrdChCd) {
        this.drInvtyOrdChCd = drInvtyOrdChCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdAfflCd() {
        return drInvtyOrdAfflCd;
    }

    /**
     * setter
     * @param drInvtyOrdAfflCd String
     */
    public void setDrInvtyOrdAfflCd(String drInvtyOrdAfflCd) {
        this.drInvtyOrdAfflCd = drInvtyOrdAfflCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdProjCd() {
        return drInvtyOrdProjCd;
    }

    /**
     * setter
     * @param drInvtyOrdProjCd String
     */
    public void setDrInvtyOrdProjCd(String drInvtyOrdProjCd) {
        this.drInvtyOrdProjCd = drInvtyOrdProjCd;
    }

    /**
     * getter
     * @return String
     */
    public String getDrInvtyOrdExtnCd() {
        return drInvtyOrdExtnCd;
    }

    /**
     * setter
     * @param drInvtyOrdExtnCd String
     */
    public void setDrInvtyOrdExtnCd(String drInvtyOrdExtnCd) {
        this.drInvtyOrdExtnCd = drInvtyOrdExtnCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdCmpyCd() {
        return crInvtyOrdCmpyCd;
    }

    /**
     * setter
     * @param crInvtyOrdCmpyCd String
     */
    public void setCrInvtyOrdCmpyCd(String crInvtyOrdCmpyCd) {
        this.crInvtyOrdCmpyCd = crInvtyOrdCmpyCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdBrCd() {
        return crInvtyOrdBrCd;
    }

    /**
     * setter
     * @param crInvtyOrdBrCd String
     */
    public void setCrInvtyOrdBrCd(String crInvtyOrdBrCd) {
        this.crInvtyOrdBrCd = crInvtyOrdBrCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdCcCd() {
        return crInvtyOrdCcCd;
    }

    /**
     * setter
     * @param crInvtyOrdCcCd String
     */
    public void setCrInvtyOrdCcCd(String crInvtyOrdCcCd) {
        this.crInvtyOrdCcCd = crInvtyOrdCcCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdAcctCd() {
        return crInvtyOrdAcctCd;
    }

    /**
     * setter
     * @param crInvtyOrdAcctCd String
     */
    public void setCrInvtyOrdAcctCd(String crInvtyOrdAcctCd) {
        this.crInvtyOrdAcctCd = crInvtyOrdAcctCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdProdCd() {
        return crInvtyOrdProdCd;
    }

    /**
     * setter
     * @param crInvtyOrdProdCd String
     */
    public void setCrInvtyOrdProdCd(String crInvtyOrdProdCd) {
        this.crInvtyOrdProdCd = crInvtyOrdProdCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdChCd() {
        return crInvtyOrdChCd;
    }

    /**
     * setter
     * @param crInvtyOrdChCd String
     */
    public void setCrInvtyOrdChCd(String crInvtyOrdChCd) {
        this.crInvtyOrdChCd = crInvtyOrdChCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdAfflCd() {
        return crInvtyOrdAfflCd;
    }

    /**
     * setter
     * @param crInvtyOrdAfflCd String
     */
    public void setCrInvtyOrdAfflCd(String crInvtyOrdAfflCd) {
        this.crInvtyOrdAfflCd = crInvtyOrdAfflCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdProjCd() {
        return crInvtyOrdProjCd;
    }

    /**
     * setter
     * @param crInvtyOrdProjCd String
     */
    public void setCrInvtyOrdProjCd(String crInvtyOrdProjCd) {
        this.crInvtyOrdProjCd = crInvtyOrdProjCd;
    }

    /**
     * getter
     * @return String
     */
    public String getCrInvtyOrdExtnCd() {
        return crInvtyOrdExtnCd;
    }

    /**
     * setter
     * @param crInvtyOrdExtnCd String
     */
    public void setCrInvtyOrdExtnCd(String crInvtyOrdExtnCd) {
        this.crInvtyOrdExtnCd = crInvtyOrdExtnCd;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getPrchPrcAmt() {
        return prchPrcAmt;
    }

    /**
     * setter
     * @param prchPrcAmt BigDecimal
     */
    public void setPrchPrcAmt(BigDecimal prchPrcAmt) {
        this.prchPrcAmt = prchPrcAmt;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getShipFromUnitCostAmt() {
        return shipFromUnitCostAmt;
    }

    /**
     * setter
     * @param shipFromUnitCostAmt BigDecimal
     */
    public void setShipFromUnitCostAmt(BigDecimal shipFromUnitCostAmt) {
        this.shipFromUnitCostAmt = shipFromUnitCostAmt;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getShipFromAmt() {
        return shipFromAmt;
    }

    /**
     * setter
     * @param shipFromAmt BigDecimal
     */
    public void setShipFromAmt(BigDecimal shipFromAmt) {
        this.shipFromAmt = shipFromAmt;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getRmnfWipAmt() {
        return rmnfWipAmt;
    }

    /**
     * setter
     * @param rmnfWipAmt BigDecimal
     */
    public void setRmnfWipAmt(BigDecimal rmnfWipAmt) {
        this.rmnfWipAmt = rmnfWipAmt;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * setter
     * @param svcMachMstrPk BigDecimal
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * getter
     * @return String
     */
    public String getMachMdseCd() {
        return machMdseCd;
    }

    /**
     * setter
     * @param machMdseCd String
     */
    public void setMachMdseCd(String machMdseCd) {
        this.machMdseCd = machMdseCd;
    }

    /**
     * getter
     * @return String
     */
    public String getFinBrCd() {
        return finBrCd;
    }

    /**
     * setter
     * @param finBrCd String
     */
    public void setFinBrCd(String finBrCd) {
        this.finBrCd = finBrCd;
    }

    /**
     * getter
     * @return String
     */
    public String getSerNum() {
        return serNum;
    }

    /**
     * setter
     * @param serNum String
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    /**
     * getter
     * @return String
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /**
     * setter
     * @param rtlWhCd String
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    /**
     * getter
     * @return String
     */
    public String getOrigRtlWhCd() {
        return origRtlWhCd;
    }

    /**
     * setter
     * @param origRtlWhCd String
     */
    public void setOrigRtlWhCd(String origRtlWhCd) {
        this.origRtlWhCd = origRtlWhCd;
    }

    /**
     * getter
     * @return String
     */
    public String getRmnfWipCpltFlg() {
        return rmnfWipCpltFlg;
    }

    /**
     * setter
     * @param rmnfWipCpltFlg String
     */
    public void setRmnfWipCpltFlg(String rmnfWipCpltFlg) {
        this.rmnfWipCpltFlg = rmnfWipCpltFlg;
    }

    /**
     * getter
     * @return String
     */
    public BigDecimal getDividedShipCostAmt() {
        return dividedShipCostAmt;
    }

    /**
     * setter
     * @param dividedShipCostAmt BigDecimal
     */
    public void setDividedShipCostAmt(BigDecimal dividedShipCostAmt) {
        this.dividedShipCostAmt = dividedShipCostAmt;
    }

    /**
     * @return
     */
    public String getPoOrdNum() {
        return poOrdNum;
    }

    /**
     * @param poOrdNum
     */
    public void setPoOrdNum(String poOrdNum) {
        this.poOrdNum = poOrdNum;
    }

    /**
     * @return
     */
    public String getPoOrdDtlLineNum() {
        return poOrdDtlLineNum;
    }

    /**
     * @param poOrdDtlLineNum
     */
    public void setPoOrdDtlLineNum(String poOrdDtlLineNum) {
        this.poOrdDtlLineNum = poOrdDtlLineNum;
    }

    // START 2016/09/07 T.Tsuchida [QC#14260,ADD]
    /**
     * @return
     */
    public String getRmaNum() {
        return rmaNum;
    }

    /**
     * @param rmaNum
     */
    public void setRmaNum(String rmaNum) {
        this.rmaNum = rmaNum;
    }

    /**
     * @return
     */
    public String getRmaLineNum() {
        return rmaLineNum;
    }

    /**
     * @param rmaLineNum
     */
    public void setRmaLineNum(String rmaLineNum) {
        this.rmaLineNum = rmaLineNum;
    }

    /**
     * @return
     */
    public String getRmaLineSubNum() {
        return rmaLineSubNum;
    }

    /**
     * @param rmaLineSubNum
     */
    public void setRmaLineSubNum(String rmaLineSubNum) {
        this.rmaLineSubNum = rmaLineSubNum;
    }

    // END 2016/09/07 T.Tsuchida [QC#14260,ADD]

}
