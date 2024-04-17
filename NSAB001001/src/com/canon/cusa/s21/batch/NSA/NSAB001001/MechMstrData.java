/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB001001;

import java.math.BigDecimal;

/**
 * <pre>
 * Machine Master Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 05/05/2013   Fujitsu         Y.Kamide        Create          N/A
 * 10/01/2015   Hitachi         T.Tomita        Update          CSA
 * 2015/12/10   Hitachi         T.Tomita        Update          CSA QC#1795
 *</pre>
 */
public final class MechMstrData {
    // START 2015/12/10 T.Tomita [QC#1795, ADD]
    /** DS_CPO_CONFIG_PK */
    private BigDecimal dsCpoConfigPk;
    // END 2015/12/10 T.Tomita [QC#1795, ADD]
    /** SVC_CONFIG_MSTR_PK */
    private BigDecimal svcConfigMstrPk;

    /** SVC_MACH_MSTR_PK */
    private BigDecimal svcMachMstrPk;

    /** SER_NUM */
    private String serNum;

    /** MDSE_CD */
    private String mdseCd;

    /** CUST_MACH_CTRL_NUM */
    private String custMachCtrlNum;

    /** SVC_MACH_SQ_NUM */
    private BigDecimal svcMachSqNum;

    /** SVC_MACH_TP_CD */
    private String svcMachTpCd;

    /** SVC_MACH_MSTR_STS_CD */
    private String svcMachMstrStsCd;

    /** LOC_NM */
    private String locNm;

    /** ADDL_LOC_NM */
    private String addlLocNm;

    /** FIRST_LINE_ADDR */
    private String firstLineAddr;

    /** SCD_LINE_ADDR */
    private String scdLineAddr;

    /** THIRD_LINE_ADDR */
    private String thirdLineAddr;

    /** FRTH_LINE_ADDR */
    private String frthLineAddr;

    /** CTY_ADDR */
    private String ctyAddr;

    /** ST_CD */
    private String stCd;

    /** PROV_NM */
    private String provNm;

    /** CNTY_NM */
    private String cntyNm;

    /** POST_CD */
    private String postCd;

    /** CTRY_CD */
    private String ctryCd;

    /** SVC_MACH_FL_NM */
    private String svcMachFlNm;

    /** PRF_TECH_CD */
    private String prfTechCd;

    /** STK_STS_CD */
    private String stkStsCd;

    /** SHIP_FROM_WH_CD */
    private String shipFromWhCd;

    /** SHIP_DT */
    private String shipDt;

    /** ISTL_DT */
    private String istlDt;

    /** SVC_MACH_RMV_DT */
    private String svcMachRmvDt;

    /** WH_CD */
    private String whCd;

    /** DRUM_EXCH_DT */
    private String drumExchDt;

    /** LAST_SVC_CALL_VISIT_DT_A */
    private String lastSvcCallVisitDt_A;

    /** TOT_SVC_VISIT_CNT */
    private BigDecimal totSvcVisitCnt;

    /** LAST_TECH_VISIT_DT */
    private String lastTechVisitDt;

    /** LAST_PRVNT_MAINT_DT */
    private String lastPrvntMaintDt;

    /** LAST_SVC_CALL_VISIT_DT_B */
    private String lastSvcCallVisitDt_B;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** CPO_DTL_LINE_NUM */
    private String cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM */
    private String cpoDtlLineSubNum;

    /** CUST_ISS_PO_NUM */
    private String custIssPoNum;

    /** SHPG_PLN_NUM */
    private String shpgPlnNum;

    /** SO_NUM */
    private String soNum;

    /** SO_SLP_NUM */
    private String soSlpNum;

    /** RMA_NUM */
    private String rmaNum;

    /** RMA_LINE_NUM */
    private String rmaLineNum;

    /** RMA_LINE_SUB_NUM */
    private String rmaLineSubNum;

    /** RMA_LINE_SUB_TRX_NUM */
    private String rmaLineSubTrxNum;

    /** AUTO_CRAT_FLG */
    private String autoCratFlg;

    /** OLD_SER_NUM */
    private String oldSerNum;

    /** DS_ORD_TP_CD */
    private String dsOrdTpCd;

    /** DS_ORD_RSN_CD */
    private String dsOrdRsnCd;

    /** SVC_CONFIG_MSTR_DTL_PK */
    private BigDecimal svcConfigMstrDtlPk;

    /** PRNT_SVC_MACH_MSTR_PK */
    private BigDecimal prntSvcMachMstrPk;

    /** EFF_FROM_DT */
    private String effFromDt;

    /** EFF_THRU_DT */
    private String effThruDt;

    /** SVC_MACH_USG_STS_CD */
    private String svcMachUsgStsCd;

    /** PRNT_SER_NUM */
    private String prntSerNum;

    /** SVC_MACH_TRX_TP_CD */
    private String svcMachTrxTpCd;

    /** CTRL_FLD_TXT_01 */
    private String ctrlFldTxt_01;

    /** CTRL_FLD_TXT_02 */
    private String ctrlFldTxt_02;

    /** CTRL_FLD_TXT_03 */
    private String ctrlFldTxt_03;

    /** CTRL_FLD_TXT_04 */
    private String ctrlFldTxt_04;

    /** CTRL_FLD_TXT_05 */
    private String ctrlFldTxt_05;

    /** CTRL_FLD_TXT_06 */
    private String ctrlFldTxt_06;

    /** PRC_CONTR_NUM */
    private String prcContrNum;

    /** CORP_ADVTG_STS_CD */
    private String corpAdvtgStsCd;

    /** DS_PO_EXPR_DT */
    private String dsPoExprDt;

    /** HARD_DRIVE_RMV_INCL_FLG */
    private String hardDriveRmvInclFlg;

    /** HARD_DRIVE_ERASE_INCL_FLG */
    private String hardDriveEraseInclFlg;

    /** LEASE_RTRN_FEE_INCL_FLG */
    private String leaseRtrnFeeInclFlg;

    /** DS_KEY_ACCT_FLG */
    private String dsKeyAcctFlg;

    /** SVC_NTWK_CONN_STS_CD */
    private String svcNtwkConnStsCd;

    /** SLD_BY_LINE_BIZ_TP_CD */
    private String sldByLineBizTpCd;

    /** SVC_LIC_CNT */
    private BigDecimal svcLicCnt;

    /** RQST_TECH_TOC_CD */
    private String rqstTechTocCd;

    /** SVC_MACH_QTY */
    private BigDecimal svcMachQty;

    /** TRX_HDR_NUM */
    private String trxHdrNum;

    /** TRX_LINE_NUM */
    private String trxLineNum;

    /** TRX_LINE_SUB_NUM */
    private String trxLineSubNum;

    /** SVC_MACH_MSTR_LOC_STS_CD */
    private String svcMachMstrLocStsCd;

    /** OWNR_ACCT_NUM */
    private String ownrAcctNum;

    /** OWNR_LOC_NUM */
    private String ownrLocNum;

    /** BILL_TO_ACCT_NUM */
    private String billToAcctNum;

    /** BILL_TO_LOC_NUM */
    private String billToLocNum;

    /** CUR_LOC_ACCT_NUM */
    private String curLocAcctNum;

    /** CUR_LOC_NUM */
    private String curLocNum;

    /** FLD_SVC_BR_CD */
    private String fldSvcBrCd;

    /** SVC_BY_LINE_BIZ_TP_CD */
    private String svcByLineBizTpCd;

    /** FIN_BR_CD */
    private String finBrCd;

    /** SHR_DLR_FLG */
    private String shrDlrFlg;

    /** IWR_RGTN_STS_CD */
    private String iwrRgtnStsCd;

    /** BIZ_HRS_SUN_FROM_TM */
    private String bizHrsSunFromTm;

    /** BIZ_HRS_SUN_TO_TM */
    private String bizHrsSunToTm;

    /** BIZ_HRS_MON_FRI_FROM_TM */
    private String bizHrsMonFriFromTm;

    /** BIZ_HRS_MON_FRI_TO_TM */
    private String bizHrsMonFriToTm;

    /** BIZ_HRS_SAT_FROM_TM */
    private String bizHrsSatFromTm;

    /** BIZ_HRS_SAT_TO_TM */
    private String bizHrsSatToTm;

    /** ENET_PLOT_FLG */
    private String enetPlotFlg;

    /** ENET_CMNT_TXT_01 */
    private String enetCmntTxt_01;

    /** ENET_CMNT_TXT_02 */
    private String enetCmntTxt_02;

    /** PICK_SVC_CONFIG_MSTR_PK */
    private BigDecimal pickSvcConfigMstrPk;

    /** SCE_ORD_TP_CD */
    private String sceOrdTpCd;

    // START 2015/12/10 T.Tomita [QC#1795, ADD]
    /**
     * @return dsCpoConfigPk
     */
    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    /**
     * @param dsCpoConfigPk dsCpoConfigPk
     */
    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }
    // END 2015/12/10 T.Tomita [QC#1795, ADD]

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
    }

    /**
     * @return svcMachMstrPk
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * @param svcMachMstrPk svcMachMstrPk
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * @return serNum
     */
    public String getSerNum() {
        return serNum;
    }

    /**
     * @param serNum serNum
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return custMachCtrlNum
     */
    public String getCustMachCtrlNum() {
        return custMachCtrlNum;
    }

    /**
     * @param custMachCtrlNum custMachCtrlNum
     */
    public void setCustMachCtrlNum(String custMachCtrlNum) {
        this.custMachCtrlNum = custMachCtrlNum;
    }

    /**
     * @return svcMachSqNum
     */
    public BigDecimal getSvcMachSqNum() {
        return svcMachSqNum;
    }

    /**
     * @param svcMachSqNum svcMachSqNum
     */
    public void setSvcMachSqNum(BigDecimal svcMachSqNum) {
        this.svcMachSqNum = svcMachSqNum;
    }

    /**
     * @return svcMachTpCd
     */
    public String getSvcMachTpCd() {
        return svcMachTpCd;
    }

    /**
     * @param svcMachTpCd svcMachTpCd
     */
    public void setSvcMachTpCd(String svcMachTpCd) {
        this.svcMachTpCd = svcMachTpCd;
    }

    /**
     * @return svcMachMstrStsCd
     */
    public String getSvcMachMstrStsCd() {
        return svcMachMstrStsCd;
    }

    /**
     * @param svcMachMstrStsCd svcMachMstrStsCd
     */
    public void setSvcMachMstrStsCd(String svcMachMstrStsCd) {
        this.svcMachMstrStsCd = svcMachMstrStsCd;
    }

    /**
     * @return locNm
     */
    public String getLocNm() {
        return locNm;
    }

    /**
     * @param locNm locNm
     */
    public void setLocNm(String locNm) {
        this.locNm = locNm;
    }

    /**
     * @return addlLocNm
     */
    public String getAddlLocNm() {
        return addlLocNm;
    }

    /**
     * @param addlLocNm addlLocNm
     */
    public void setAddlLocNm(String addlLocNm) {
        this.addlLocNm = addlLocNm;
    }

    /**
     * @return firstLineAddr
     */
    public String getFirstLineAddr() {
        return firstLineAddr;
    }

    /**
     * @param firstLineAddr firstLineAddr
     */
    public void setFirstLineAddr(String firstLineAddr) {
        this.firstLineAddr = firstLineAddr;
    }

    /**
     * @return scdLineAddr
     */
    public String getScdLineAddr() {
        return scdLineAddr;
    }

    /**
     * @param scdLineAddr scdLineAddr
     */
    public void setScdLineAddr(String scdLineAddr) {
        this.scdLineAddr = scdLineAddr;
    }

    /**
     * @return thirdLineAddr
     */
    public String getThirdLineAddr() {
        return thirdLineAddr;
    }

    /**
     * @param thirdLineAddr thirdLineAddr
     */
    public void setThirdLineAddr(String thirdLineAddr) {
        this.thirdLineAddr = thirdLineAddr;
    }

    /**
     * @return frthLineAddr
     */
    public String getFrthLineAddr() {
        return frthLineAddr;
    }

    /**
     * @param frthLineAddr frthLineAddr
     */
    public void setFrthLineAddr(String frthLineAddr) {
        this.frthLineAddr = frthLineAddr;
    }

    /**
     * @return ctyAddr
     */
    public String getCtyAddr() {
        return ctyAddr;
    }

    /**
     * @param ctyAddr ctyAddr
     */
    public void setCtyAddr(String ctyAddr) {
        this.ctyAddr = ctyAddr;
    }

    /**
     * @return stCd
     */
    public String getStCd() {
        return stCd;
    }

    /**
     * @param stCd stCd
     */
    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    /**
     * @return provNm
     */
    public String getProvNm() {
        return provNm;
    }

    /**
     * @param provNm provNm
     */
    public void setProvNm(String provNm) {
        this.provNm = provNm;
    }

    /**
     * @return cntyNm
     */
    public String getCntyNm() {
        return cntyNm;
    }

    /**
     * @param cntyNm cntyNm
     */
    public void setCntyNm(String cntyNm) {
        this.cntyNm = cntyNm;
    }

    /**
     * @return postCd
     */
    public String getPostCd() {
        return postCd;
    }

    /**
     * @param postCd postCd
     */
    public void setPostCd(String postCd) {
        this.postCd = postCd;
    }

    /**
     * @return ctryCd
     */
    public String getCtryCd() {
        return ctryCd;
    }

    /**
     * @param ctryCd ctryCd
     */
    public void setCtryCd(String ctryCd) {
        this.ctryCd = ctryCd;
    }

    /**
     * @return svcMachFlNm
     */
    public String getSvcMachFlNm() {
        return svcMachFlNm;
    }

    /**
     * @param svcMachFlNm svcMachFlNm
     */
    public void setSvcMachFlNm(String svcMachFlNm) {
        this.svcMachFlNm = svcMachFlNm;
    }

    /**
     * @return prfTechCd
     */
    public String getPrfTechCd() {
        return prfTechCd;
    }

    /**
     * @param prfTechCd prfTechCd
     */
    public void setPrfTechCd(String prfTechCd) {
        this.prfTechCd = prfTechCd;
    }

    /**
     * @return stkStsCd
     */
    public String getStkStsCd() {
        return stkStsCd;
    }

    /**
     * @param stkStsCd stkStsCd
     */
    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }

    /**
     * @return shipFromWhCd
     */
    public String getShipFromWhCd() {
        return shipFromWhCd;
    }

    /**
     * @param shipFromWhCd shipFromWhCd
     */
    public void setShipFromWhCd(String shipFromWhCd) {
        this.shipFromWhCd = shipFromWhCd;
    }

    /**
     * @return shipDt
     */
    public String getShipDt() {
        return shipDt;
    }

    /**
     * @param shipDt shipDt
     */
    public void setShipDt(String shipDt) {
        this.shipDt = shipDt;
    }

    /**
     * @return istlDt
     */
    public String getIstlDt() {
        return istlDt;
    }

    /**
     * @param istlDt istlDt
     */
    public void setIstlDt(String istlDt) {
        this.istlDt = istlDt;
    }

    /**
     * @return svcMachRmvDt
     */
    public String getSvcMachRmvDt() {
        return svcMachRmvDt;
    }

    /**
     * @param svcMachRmvDt svcMachRmvDt
     */
    public void setSvcMachRmvDt(String svcMachRmvDt) {
        this.svcMachRmvDt = svcMachRmvDt;
    }

    /**
     * @return whCd
     */
    public String getWhCd() {
        return whCd;
    }

    /**
     * @param whCd whCd
     */
    public void setWhCd(String whCd) {
        this.whCd = whCd;
    }

    /**
     * @return drumExchDt
     */
    public String getDrumExchDt() {
        return drumExchDt;
    }

    /**
     * @param drumExchDt drumExchDt
     */
    public void setDrumExchDt(String drumExchDt) {
        this.drumExchDt = drumExchDt;
    }

    /**
     * @return lastSvcCallVisitDt_A
     */
    public String getLastSvcCallVisitDt_A() {
        return lastSvcCallVisitDt_A;
    }

    /**
     * @param lastSvcCallVisitDt_A lastSvcCallVisitDt_A
     */
    public void setLastSvcCallVisitDt_A(String lastSvcCallVisitDt_A) {
        this.lastSvcCallVisitDt_A = lastSvcCallVisitDt_A;
    }

    /**
     * @return totSvcVisitCnt
     */
    public BigDecimal getTotSvcVisitCnt() {
        return totSvcVisitCnt;
    }

    /**
     * @param totSvcVisitCnt totSvcVisitCnt
     */
    public void setTotSvcVisitCnt(BigDecimal totSvcVisitCnt) {
        this.totSvcVisitCnt = totSvcVisitCnt;
    }

    /**
     * @return lastTechVisitDt
     */
    public String getLastTechVisitDt() {
        return lastTechVisitDt;
    }

    /**
     * @param lastTechVisitDt lastTechVisitDt
     */
    public void setLastTechVisitDt(String lastTechVisitDt) {
        this.lastTechVisitDt = lastTechVisitDt;
    }

    /**
     * @return lastPrvntMaintDt
     */
    public String getLastPrvntMaintDt() {
        return lastPrvntMaintDt;
    }

    /**
     * @param lastPrvntMaintDt lastPrvntMaintDt
     */
    public void setLastPrvntMaintDt(String lastPrvntMaintDt) {
        this.lastPrvntMaintDt = lastPrvntMaintDt;
    }

    /**
     * @return lastSvcCallVisitDt_B
     */
    public String getLastSvcCallVisitDt_B() {
        return lastSvcCallVisitDt_B;
    }

    /**
     * @param lastSvcCallVisitDt_B lastSvcCallVisitDt_B
     */
    public void setLastSvcCallVisitDt_B(String lastSvcCallVisitDt_B) {
        this.lastSvcCallVisitDt_B = lastSvcCallVisitDt_B;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @param cpoOrdNum cpoOrdNum
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @return cpoDtlLineNum
     */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /**
     * @param cpoDtlLineNum cpoDtlLineNum
     */
    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    /**
     * @return cpoDtlLineSubNum
     */
    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    /**
     * @param cpoDtlLineSubNum cpoDtlLineSubNum
     */
    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    /**
     * @return custIssPoNum
     */
    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    /**
     * @param custIssPoNum custIssPoNum
     */
    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    /**
     * @return shpgPlnNum
     */
    public String getShpgPlnNum() {
        return shpgPlnNum;
    }

    /**
     * @param shpgPlnNum shpgPlnNum
     */
    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }

    /**
     * @return soNum
     */
    public String getSoNum() {
        return soNum;
    }

    /**
     * @param soNum soNum
     */
    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }

    /**
     * @return soSlpNum
     */
    public String getSoSlpNum() {
        return soSlpNum;
    }

    /**
     * @param soSlpNum soSlpNum
     */
    public void setSoSlpNum(String soSlpNum) {
        this.soSlpNum = soSlpNum;
    }

    /**
     * @return rmaNum
     */
    public String getRmaNum() {
        return rmaNum;
    }

    /**
     * @param rmaNum rmaNum
     */
    public void setRmaNum(String rmaNum) {
        this.rmaNum = rmaNum;
    }

    /**
     * @return rmaLineNum
     */
    public String getRmaLineNum() {
        return rmaLineNum;
    }

    /**
     * @param rmaLineNum rmaLineNum
     */
    public void setRmaLineNum(String rmaLineNum) {
        this.rmaLineNum = rmaLineNum;
    }

    /**
     * @return rmaLineSubNum
     */
    public String getRmaLineSubNum() {
        return rmaLineSubNum;
    }

    /**
     * @param rmaLineSubNum rmaLineSubNum
     */
    public void setRmaLineSubNum(String rmaLineSubNum) {
        this.rmaLineSubNum = rmaLineSubNum;
    }

    /**
     * @return rmaLineSubTrxNum
     */
    public String getRmaLineSubTrxNum() {
        return rmaLineSubTrxNum;
    }

    /**
     * @param rmaLineSubTrxNum rmaLineSubTrxNum
     */
    public void setRmaLineSubTrxNum(String rmaLineSubTrxNum) {
        this.rmaLineSubTrxNum = rmaLineSubTrxNum;
    }

    /**
     * @return autoCratFlg
     */
    public String getAutoCratFlg() {
        return autoCratFlg;
    }

    /**
     * @param autoCratFlg autoCratFlg
     */
    public void setAutoCratFlg(String autoCratFlg) {
        this.autoCratFlg = autoCratFlg;
    }

    /**
     * @return oldSerNum
     */
    public String getOldSerNum() {
        return oldSerNum;
    }

    /**
     * @param oldSerNum oldSerNum
     */
    public void setOldSerNum(String oldSerNum) {
        this.oldSerNum = oldSerNum;
    }

    /**
     * @return dsOrdTpCd
     */
    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    /**
     * @param dsOrdTpCd dsOrdTpCd
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
     * @param dsOrdRsnCd dsOrdRsnCd
     */
    public void setDsOrdRsnCd(String dsOrdRsnCd) {
        this.dsOrdRsnCd = dsOrdRsnCd;
    }

    /**
     * @return svcConfigMstrDtlPk
     */
    public BigDecimal getSvcConfigMstrDtlPk() {
        return svcConfigMstrDtlPk;
    }

    /**
     * @param svcConfigMstrDtlPk svcConfigMstrDtlPk
     */
    public void setSvcConfigMstrDtlPk(BigDecimal svcConfigMstrDtlPk) {
        this.svcConfigMstrDtlPk = svcConfigMstrDtlPk;
    }

    /**
     * @return prntSvcMachMstrPk
     */
    public BigDecimal getPrntSvcMachMstrPk() {
        return prntSvcMachMstrPk;
    }

    /**
     * @param prntSvcMachMstrPk prntSvcMachMstrPk
     */
    public void setPrntSvcMachMstrPk(BigDecimal prntSvcMachMstrPk) {
        this.prntSvcMachMstrPk = prntSvcMachMstrPk;
    }

    /**
     * @return effFromDt
     */
    public String getEffFromDt() {
        return effFromDt;
    }

    /**
     * @param effFromDt effFromDt
     */
    public void setEffFromDt(String effFromDt) {
        this.effFromDt = effFromDt;
    }

    /**
     * @return effThruDt
     */
    public String getEffThruDt() {
        return effThruDt;
    }

    /**
     * @param effThruDt effThruDt
     */
    public void setEffThruDt(String effThruDt) {
        this.effThruDt = effThruDt;
    }

    /**
     * @return svcMachUsgStsCd
     */
    public String getSvcMachUsgStsCd() {
        return svcMachUsgStsCd;
    }

    /**
     * @param svcMachUsgStsCd svcMachUsgStsCd
     */
    public void setSvcMachUsgStsCd(String svcMachUsgStsCd) {
        this.svcMachUsgStsCd = svcMachUsgStsCd;
    }

    /**
     * @return prntSerNum
     */
    public String getPrntSerNum() {
        return prntSerNum;
    }

    /**
     * @param prntSerNum prntSerNum
     */
    public void setPrntSerNum(String prntSerNum) {
        this.prntSerNum = prntSerNum;
    }

    /**
     * @return svcMachTrxTpCd
     */
    public String getSvcMachTrxTpCd() {
        return svcMachTrxTpCd;
    }

    /**
     * @param svcMachTrxTpCd svcMachTrxTpCd
     */
    public void setSvcMachTrxTpCd(String svcMachTrxTpCd) {
        this.svcMachTrxTpCd = svcMachTrxTpCd;
    }

    /**
     * @return ctrlFldTxt_01
     */
    public String getCtrlFldTxt_01() {
        return ctrlFldTxt_01;
    }

    /**
     * @param ctrlFldTxt_01 ctrlFldTxt_01
     */
    public void setCtrlFldTxt_01(String ctrlFldTxt_01) {
        this.ctrlFldTxt_01 = ctrlFldTxt_01;
    }

    /**
     * @return ctrlFldTxt_02
     */
    public String getCtrlFldTxt_02() {
        return ctrlFldTxt_02;
    }

    /**
     * @param ctrlFldTxt_02 ctrlFldTxt_02
     */
    public void setCtrlFldTxt_02(String ctrlFldTxt_02) {
        this.ctrlFldTxt_02 = ctrlFldTxt_02;
    }

    /**
     * @return ctrlFldTxt_03
     */
    public String getCtrlFldTxt_03() {
        return ctrlFldTxt_03;
    }

    /**
     * @param ctrlFldTxt_03 ctrlFldTxt_03
     */
    public void setCtrlFldTxt_03(String ctrlFldTxt_03) {
        this.ctrlFldTxt_03 = ctrlFldTxt_03;
    }

    /**
     * @return ctrlFldTxt_04
     */
    public String getCtrlFldTxt_04() {
        return ctrlFldTxt_04;
    }

    /**
     * @param ctrlFldTxt_04 ctrlFldTxt_04
     */
    public void setCtrlFldTxt_04(String ctrlFldTxt_04) {
        this.ctrlFldTxt_04 = ctrlFldTxt_04;
    }

    /**
     * @return ctrlFldTxt_05
     */
    public String getCtrlFldTxt_05() {
        return ctrlFldTxt_05;
    }

    /**
     * @param ctrlFldTxt_05 ctrlFldTxt_05
     */
    public void setCtrlFldTxt_05(String ctrlFldTxt_05) {
        this.ctrlFldTxt_05 = ctrlFldTxt_05;
    }

    /**
     * @return ctrlFldTxt_06
     */
    public String getCtrlFldTxt_06() {
        return ctrlFldTxt_06;
    }

    /**
     * @param ctrlFldTxt_06 ctrlFldTxt_06
     */
    public void setCtrlFldTxt_06(String ctrlFldTxt_06) {
        this.ctrlFldTxt_06 = ctrlFldTxt_06;
    }

    /**
     * @return prcContrNum
     */
    public String getPrcContrNum() {
        return prcContrNum;
    }

    /**
     * @param prcContrNum prcContrNum
     */
    public void setPrcContrNum(String prcContrNum) {
        this.prcContrNum = prcContrNum;
    }

    /**
     * @return corpAdvtgStsCd
     */
    public String getCorpAdvtgStsCd() {
        return corpAdvtgStsCd;
    }

    /**
     * @param corpAdvtgStsCd corpAdvtgStsCd
     */
    public void setCorpAdvtgStsCd(String corpAdvtgStsCd) {
        this.corpAdvtgStsCd = corpAdvtgStsCd;
    }

    /**
     * @return dsPoExprDt
     */
    public String getDsPoExprDt() {
        return dsPoExprDt;
    }

    /**
     * @param dsPoExprDt dsPoExprDt
     */
    public void setDsPoExprDt(String dsPoExprDt) {
        this.dsPoExprDt = dsPoExprDt;
    }

    /**
     * @return hardDriveRmvInclFlg
     */
    public String getHardDriveRmvInclFlg() {
        return hardDriveRmvInclFlg;
    }

    /**
     * @param hardDriveRmvInclFlg hardDriveRmvInclFlg
     */
    public void setHardDriveRmvInclFlg(String hardDriveRmvInclFlg) {
        this.hardDriveRmvInclFlg = hardDriveRmvInclFlg;
    }

    /**
     * @return hardDriveEraseInclFlg
     */
    public String getHardDriveEraseInclFlg() {
        return hardDriveEraseInclFlg;
    }

    /**
     * @param hardDriveEraseInclFlg hardDriveEraseInclFlg
     */
    public void setHardDriveEraseInclFlg(String hardDriveEraseInclFlg) {
        this.hardDriveEraseInclFlg = hardDriveEraseInclFlg;
    }

    /**
     * @return leaseRtrnFeeInclFlg
     */
    public String getLeaseRtrnFeeInclFlg() {
        return leaseRtrnFeeInclFlg;
    }

    /**
     * @param leaseRtrnFeeInclFlg leaseRtrnFeeInclFlg
     */
    public void setLeaseRtrnFeeInclFlg(String leaseRtrnFeeInclFlg) {
        this.leaseRtrnFeeInclFlg = leaseRtrnFeeInclFlg;
    }

    /**
     * @return dsKeyAcctFlg
     */
    public String getDsKeyAcctFlg() {
        return dsKeyAcctFlg;
    }

    /**
     * @param dsKeyAcctFlg dsKeyAcctFlg
     */
    public void setDsKeyAcctFlg(String dsKeyAcctFlg) {
        this.dsKeyAcctFlg = dsKeyAcctFlg;
    }

    /**
     * @return svcNtwkConnStsCd
     */
    public String getSvcNtwkConnStsCd() {
        return svcNtwkConnStsCd;
    }

    /**
     * @param svcNtwkConnStsCd svcNtwkConnStsCd
     */
    public void setSvcNtwkConnStsCd(String svcNtwkConnStsCd) {
        this.svcNtwkConnStsCd = svcNtwkConnStsCd;
    }

    /**
     * @return sldByLineBizTpCd
     */
    public String getSldByLineBizTpCd() {
        return sldByLineBizTpCd;
    }

    /**
     * @param sldByLineBizTpCd sldByLineBizTpCd
     */
    public void setSldByLineBizTpCd(String sldByLineBizTpCd) {
        this.sldByLineBizTpCd = sldByLineBizTpCd;
    }

    /**
     * @return svcLicCnt
     */
    public BigDecimal getSvcLicCnt() {
        return svcLicCnt;
    }

    /**
     * @param svcLicCnt svcLicCnt
     */
    public void setSvcLicCnt(BigDecimal svcLicCnt) {
        this.svcLicCnt = svcLicCnt;
    }

    /**
     * @return rqstTechTocCd
     */
    public String getRqstTechTocCd() {
        return rqstTechTocCd;
    }

    /**
     * @param rqstTechTocCd rqstTechTocCd
     */
    public void setRqstTechTocCd(String rqstTechTocCd) {
        this.rqstTechTocCd = rqstTechTocCd;
    }

    /**
     * @return svcMachQty
     */
    public BigDecimal getSvcMachQty() {
        return svcMachQty;
    }

    /**
     * @param svcMachQty svcMachQty
     */
    public void setSvcMachQty(BigDecimal svcMachQty) {
        this.svcMachQty = svcMachQty;
    }

    /**
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    /**
     * @param trxHdrNum trxHdrNum
     */
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }

    /**
     * @return trxLineNum
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    /**
     * @param trxLineNum trxLineNum
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    /**
     * @return trxLineSubNum
     */
    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    /**
     * @param trxLineSubNum trxLineSubNum
     */
    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    /**
     * @return svcMachMstrLocStsCd
     */
    public String getSvcMachMstrLocStsCd() {
        return svcMachMstrLocStsCd;
    }

    /**
     * @param svcMachMstrLocStsCd svcMachMstrLocStsCd
     */
    public void setSvcMachMstrLocStsCd(String svcMachMstrLocStsCd) {
        this.svcMachMstrLocStsCd = svcMachMstrLocStsCd;
    }

    /**
     * @return ownrAcctNum
     */
    public String getOwnrAcctNum() {
        return ownrAcctNum;
    }

    /**
     * @param ownrAcctNum ownrAcctNum
     */
    public void setOwnrAcctNum(String ownrAcctNum) {
        this.ownrAcctNum = ownrAcctNum;
    }

    /**
     * @return ownrLocNum
     */
    public String getOwnrLocNum() {
        return ownrLocNum;
    }

    /**
     * @param ownrLocNum ownrLocNum
     */
    public void setOwnrLocNum(String ownrLocNum) {
        this.ownrLocNum = ownrLocNum;
    }

    /**
     * @return billToAcctNum
     */
    public String getBillToAcctNum() {
        return billToAcctNum;
    }

    /**
     * @param billToAcctNum billToAcctNum
     */
    public void setBillToAcctNum(String billToAcctNum) {
        this.billToAcctNum = billToAcctNum;
    }

    /**
     * @return billToLocNum
     */
    public String getBillToLocNum() {
        return billToLocNum;
    }

    /**
     * @param billToLocNum billToLocNum
     */
    public void setBillToLocNum(String billToLocNum) {
        this.billToLocNum = billToLocNum;
    }

    /**
     * @return curLocAcctNum
     */
    public String getCurLocAcctNum() {
        return curLocAcctNum;
    }

    /**
     * @param curLocAcctNum curLocAcctNum
     */
    public void setCurLocAcctNum(String curLocAcctNum) {
        this.curLocAcctNum = curLocAcctNum;
    }

    /**
     * @return curLocNum
     */
    public String getCurLocNum() {
        return curLocNum;
    }

    /**
     * @param curLocNum curLocNum
     */
    public void setCurLocNum(String curLocNum) {
        this.curLocNum = curLocNum;
    }

    /**
     * @return fldSvcBrCd
     */
    public String getFldSvcBrCd() {
        return fldSvcBrCd;
    }

    /**
     * @param fldSvcBrCd fldSvcBrCd
     */
    public void setFldSvcBrCd(String fldSvcBrCd) {
        this.fldSvcBrCd = fldSvcBrCd;
    }

    /**
     * @return svcByLineBizTpCd
     */
    public String getSvcByLineBizTpCd() {
        return svcByLineBizTpCd;
    }

    /**
     * @param svcByLineBizTpCd svcByLineBizTpCd
     */
    public void setSvcByLineBizTpCd(String svcByLineBizTpCd) {
        this.svcByLineBizTpCd = svcByLineBizTpCd;
    }

    /**
     * @return finBrCd
     */
    public String getFinBrCd() {
        return finBrCd;
    }

    /**
     * @param finBrCd finBrCd
     */
    public void setFinBrCd(String finBrCd) {
        this.finBrCd = finBrCd;
    }

    /**
     * @return shrDlrFlg
     */
    public String getShrDlrFlg() {
        return shrDlrFlg;
    }

    /**
     * @param shrDlrFlg shrDlrFlg
     */
    public void setShrDlrFlg(String shrDlrFlg) {
        this.shrDlrFlg = shrDlrFlg;
    }

    /**
     * @return iwrRgtnStsCd
     */
    public String getIwrRgtnStsCd() {
        return iwrRgtnStsCd;
    }

    /**
     * @param iwrRgtnStsCd iwrRgtnStsCd
     */
    public void setIwrRgtnStsCd(String iwrRgtnStsCd) {
        this.iwrRgtnStsCd = iwrRgtnStsCd;
    }

    /**
     * @return bizHrsSunFromTm
     */
    public String getBizHrsSunFromTm() {
        return bizHrsSunFromTm;
    }

    /**
     * @param bizHrsSunFromTm bizHrsSunFromTm
     */
    public void setBizHrsSunFromTm(String bizHrsSunFromTm) {
        this.bizHrsSunFromTm = bizHrsSunFromTm;
    }

    /**
     * @return bizHrsSunToTm
     */
    public String getBizHrsSunToTm() {
        return bizHrsSunToTm;
    }

    /**
     * @param bizHrsSunToTm bizHrsSunToTm
     */
    public void setBizHrsSunToTm(String bizHrsSunToTm) {
        this.bizHrsSunToTm = bizHrsSunToTm;
    }

    /**
     * @return bizHrsMonFriFromTm
     */
    public String getBizHrsMonFriFromTm() {
        return bizHrsMonFriFromTm;
    }

    /**
     * @param bizHrsMonFriFromTm bizHrsMonFriFromTm
     */
    public void setBizHrsMonFriFromTm(String bizHrsMonFriFromTm) {
        this.bizHrsMonFriFromTm = bizHrsMonFriFromTm;
    }

    /**
     * @return bizHrsMonFriToTm
     */
    public String getBizHrsMonFriToTm() {
        return bizHrsMonFriToTm;
    }

    /**
     * @param bizHrsMonFriToTm bizHrsMonFriToTm
     */
    public void setBizHrsMonFriToTm(String bizHrsMonFriToTm) {
        this.bizHrsMonFriToTm = bizHrsMonFriToTm;
    }

    /**
     * @return bizHrsSatFromTm
     */
    public String getBizHrsSatFromTm() {
        return bizHrsSatFromTm;
    }

    /**
     * @param bizHrsSatFromTm bizHrsSatFromTm
     */
    public void setBizHrsSatFromTm(String bizHrsSatFromTm) {
        this.bizHrsSatFromTm = bizHrsSatFromTm;
    }

    /**
     * @return bizHrsSatToTm
     */
    public String getBizHrsSatToTm() {
        return bizHrsSatToTm;
    }

    /**
     * @param bizHrsSatToTm bizHrsSatToTm
     */
    public void setBizHrsSatToTm(String bizHrsSatToTm) {
        this.bizHrsSatToTm = bizHrsSatToTm;
    }

    /**
     * @return enetPlotFlg
     */
    public String getEnetPlotFlg() {
        return enetPlotFlg;
    }

    /**
     * @param enetPlotFlg enetPlotFlg
     */
    public void setEnetPlotFlg(String enetPlotFlg) {
        this.enetPlotFlg = enetPlotFlg;
    }

    /**
     * @return enetCmntTxt_01
     */
    public String getEnetCmntTxt_01() {
        return enetCmntTxt_01;
    }

    /**
     * @param enetCmntTxt_01 enetCmntTxt_01
     */
    public void setEnetCmntTxt_01(String enetCmntTxt_01) {
        this.enetCmntTxt_01 = enetCmntTxt_01;
    }

    /**
     * @return enetCmntTxt_02
     */
    public String getEnetCmntTxt_02() {
        return enetCmntTxt_02;
    }

    /**
     * @param enetCmntTxt_02 enetCmntTxt_02
     */
    public void setEnetCmntTxt_02(String enetCmntTxt_02) {
        this.enetCmntTxt_02 = enetCmntTxt_02;
    }

    /**
     * @return pickSvcConfigMstrPk
     */
    public BigDecimal getPickSvcConfigMstrPk() {
        return pickSvcConfigMstrPk;
    }

    /**
     * @param pickSvcConfigMstrPk pickSvcConfigMstrPk
     */
    public void setPickSvcConfigMstrPk(BigDecimal pickSvcConfigMstrPk) {
        this.pickSvcConfigMstrPk = pickSvcConfigMstrPk;
    }

    /**
     * @return sceOrdTpCd
     */
    public String getSceOrdTpCd() {
        return sceOrdTpCd;
    }

    /**
     * @param sceOrdTpCd sceOrdTpCd
     */
    public void setSceOrdTpCd(String sceOrdTpCd) {
        this.sceOrdTpCd = sceOrdTpCd;
    }
}
