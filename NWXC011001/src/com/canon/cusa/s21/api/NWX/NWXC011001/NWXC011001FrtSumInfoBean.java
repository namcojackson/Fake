/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC011001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/31   Fujitsu         Y.Kanefusa      Create          N/A
 * </pre>
 */
public class NWXC011001FrtSumInfoBean implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** PRC_BASE_DT */
    private String prcBaseDt;

    private String configCatgCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** DS_ACCT_NUM */
    private String dsAcctNum;

    /** DS_ACCT_NM */
    private String dsAcctNm;

    /** DS_CUST_SIC_CD */
    private String dsCustSicCd;

    /** LOC_NUM */
    private String locNum;

    /** DS_ACCT_CLS_CD */
    private String dsAcctClsCd;

    /** DS_ACCT_TP_CD */
    private String dsAcctTpCd;

    /** DS_ACCT_DLR_CD */
    private String dsAcctDlrCd;

    /** DS_ACCT_GRP_CD */
    private String dsAcctGrpCd;

    /** COA_CH_CD */
    private String coaChCd;

    /** CSMP_NUM */
    private String csmpNum;

    /** DLR_REF_NUM */
    private String dlrRefNum;

    /** PRC_CONTR_NUM */
    private String prcContrNum;

    /** ST_CD */
    private String stCd;

    /** CTRY_CD */
    private String ctryCd;

    /** POST_CD */
    private String postCd;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd;

    /** CCY_CD */
    private String ccyCd;

    /** ORD_QTY */
    private BigDecimal ordQty;

    /** Total Weight */
    private BigDecimal ttlWeight;

    /** active Weight */
    private BigDecimal activWeight;

    /** frozen Freight Amount */
    private BigDecimal frozenFrtAmt;

    /** Constractor **/
    NWXC011001FrtSumInfoBean() {
        ordQty = BigDecimal.ZERO;
        ttlWeight = BigDecimal.ZERO;
        activWeight = BigDecimal.ZERO;
        frozenFrtAmt = BigDecimal.ZERO;
    }

    public String getPrcBaseDt() {
        return prcBaseDt;
    }

    public void setPrcBaseDt(String prcBaseDt) {
        this.prcBaseDt = prcBaseDt;
    }

    public String getConfigCatgCd() {
        return configCatgCd;
    }

    public void setConfigCatgCd(String configCatgCd) {
        this.configCatgCd = configCatgCd;
    }

    public String getShipToCustCd() {
        return shipToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public String getDsAcctNum() {
        return dsAcctNum;
    }

    public void setDsAcctNum(String dsAcctNum) {
        this.dsAcctNum = dsAcctNum;
    }

    public String getDsAcctNm() {
        return dsAcctNm;
    }

    public void setDsAcctNm(String dsAcctNm) {
        this.dsAcctNm = dsAcctNm;
    }

    public String getDsCustSicCd() {
        return dsCustSicCd;
    }

    public void setDsCustSicCd(String dsCustSicCd) {
        this.dsCustSicCd = dsCustSicCd;
    }

    public String getLocNum() {
        return locNum;
    }

    public void setLocNum(String locNum) {
        this.locNum = locNum;
    }

    public String getDsAcctClsCd() {
        return dsAcctClsCd;
    }

    public void setDsAcctClsCd(String dsAcctClsCd) {
        this.dsAcctClsCd = dsAcctClsCd;
    }

    public String getDsAcctTpCd() {
        return dsAcctTpCd;
    }

    public void setDsAcctTpCd(String dsAcctTpCd) {
        this.dsAcctTpCd = dsAcctTpCd;
    }

    public String getDsAcctDlrCd() {
        return dsAcctDlrCd;
    }

    public void setDsAcctDlrCd(String dsAcctDlrCd) {
        this.dsAcctDlrCd = dsAcctDlrCd;
    }

    public String getDsAcctGrpCd() {
        return dsAcctGrpCd;
    }

    public void setDsAcctGrpCd(String dsAcctGrpCd) {
        this.dsAcctGrpCd = dsAcctGrpCd;
    }

    public String getCoaChCd() {
        return coaChCd;
    }

    public void setCoaChCd(String coaChCd) {
        this.coaChCd = coaChCd;
    }

    public String getCsmpNum() {
        return csmpNum;
    }

    public void setCsmpNum(String csmpNum) {
        this.csmpNum = csmpNum;
    }

    public String getDlrRefNum() {
        return dlrRefNum;
    }

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    public String getPrcContrNum() {
        return prcContrNum;
    }

    public void setPrcContrNum(String prcContrNum) {
        this.prcContrNum = prcContrNum;
    }

    public String getStCd() {
        return stCd;
    }

    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    public String getCtryCd() {
        return ctryCd;
    }

    public void setCtryCd(String ctryCd) {
        this.ctryCd = ctryCd;
    }

    public String getPostCd() {
        return postCd;
    }

    public void setPostCd(String postCd) {
        this.postCd = postCd;
    }

    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    public String getCcyCd() {
        return ccyCd;
    }

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    public BigDecimal getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    public BigDecimal getTtlWeight() {
        return ttlWeight;
    }

    public void setTtlWeight(BigDecimal ttlWeight) {
        this.ttlWeight = ttlWeight;
    }

    public BigDecimal getActivWeight() {
        return activWeight;
    }

    public void setActivWeight(BigDecimal activWeight) {
        this.activWeight = activWeight;
    }

    public BigDecimal getFrozenFrtAmt() {
        return frozenFrtAmt;
    }

    public void setFrozenFrtAmt(BigDecimal frozenFrtAmt) {
        this.frozenFrtAmt = frozenFrtAmt;
    }

    /* add method */
    public void addOrdQty(BigDecimal ordQty) {
        this.ordQty = this.ordQty.add(ordQty);
    }

    public void addTtlWeight(BigDecimal ttlWeight) {
        this.ttlWeight = this.ttlWeight.add(ttlWeight);
    }

    public void addActivWeight(BigDecimal activWeight) {
        this.activWeight = this.activWeight.add(activWeight);
    }

    public void addFrozenFrtAmt(BigDecimal frozenFrtAmt) {
        this.frozenFrtAmt = this.frozenFrtAmt.add(frozenFrtAmt);
    }

}
