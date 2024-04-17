package com.canon.cusa.s21.batch.NFD.NFDB002001;

import java.math.BigDecimal;

/**
 * TargetData
 * @author h00180
 */
/**
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * @author h00180
 * 2016/04/28   Fujitsu         C.Naito         Update          QC#7310
 */
public class TargetData {

    /**
     * cltAcctCd
     */
    private String cltAcctCd;

    /**
     * billToCustCd
     */
    private String billToCustCd;

    /**
     * cltStrgyWrkItemTrxPk
     */
    private BigDecimal cltStrgyWrkItemTrxPk;

    /**
     * cltWrkTpCd
     */
    private String cltWrkTpCd;

    /**
     * cltDunLtrTpCd
     */
    private String cltDunLtrTpCd;

    /**
     * cmpyNm
     */
    private String cmpyNm;

    /**
     * remFirstLineAddr
     */
    private String remFirstLineAddr;

    /**
     * remScdLineAddr
     */
    private String remScdLineAddr;

    /**
     * remThirdLineAddr
     */
    private String remThirdLineAddr;

    /**
     * remFrthLineAddr
     */
    private String remFrthLineAddr;

    /**
     * remCtyAddr
     */
    private String remCtyAddr;

    /**
     * remProvNm
     */
    private String remProvNm;

    /**
     * remStCd
     */
    private String remStCd;

    /**
     * remPostCd
     */
    private String remPostCd;

    /**
     * locNm
     */
    private String locNm;

    /**
     * addlLocNm
     */
    private String addlLocNm;

    /**
     * btcFirstLineAddr
     */
    private String btcFirstLineAddr;

    /**
     * btcScdLineAddr
     */
    private String btcScdLineAddr;

    /**
     * btcThirdLineAddr
     */
    private String btcThirdLineAddr;

    /**
     * btcFrthLineAddr
     */
    private String btcFrthLineAddr;

    /**
     * btcCtyAddr
     */
    private String btcCtyAddr;

    /**
     * btcProvNm
     */
    private String btcProvNm;

    /**
     * btcStCd
     */
    private String btcStCd;

    /**
     * btcPostCd
     */
    private String btcPostCd;

    // [QC#7310] UPDATE start
//    /**
//     * cltPsnCdBill
//     */
//    private String cltPsnCdBill;
//
//    /**
//     * cltPsnNmBill
//     */
//    private String cltPsnNmBill;
//
//    /**
//     * cltPsnCdAcct
//     */
//    private String cltPsnCdAcct;
//
//    /**
//     * cltPsnNmAcct
//     */
//    private String cltPsnNmAcct;
    /**
     * cltPsnCd
     */
    private String cltPsnCd;

    /**
     * cltPsnNm
     */
    private String cltPsnNm;

    /**
     * cltStmtPhoNum
     */
    private String cltStmtPhoNum;

    /**
     * cltStmtFaxNum
     */
    private String cltStmtFaxNum;

    /**
     * cltPsnEmlAddr
     */
    private String cltPsnEmlAddr;
    // [QC#7310] UPDATE end

    /**
     * glblCmpyNm
     */
    private String glblCmpyNm;

////////////////////////////////////////////////////////////////////////

    /**
     * @return
     */
    public String getCltAcctCd() {
        return cltAcctCd;
    }

    /**
     * @param cltAcctCd
     */
    public void setCltAcctCd(String cltAcctCd) {
        this.cltAcctCd = cltAcctCd;
    }

    /**
     * @return
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * @param billToCustCd
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * @return
     */
    public BigDecimal getCltStrgyWrkItemTrxPk() {
        return cltStrgyWrkItemTrxPk;
    }

    /**
     * @param cltStrgyWrkItemTrxPk
     */
    public void setCltStrgyWrkItemTrxPk(BigDecimal cltStrgyWrkItemTrxPk) {
        this.cltStrgyWrkItemTrxPk = cltStrgyWrkItemTrxPk;
    }

    /**
     * @return
     */
    public String getCltWrkTpCd() {
        return cltWrkTpCd;
    }

    /**
     * @param cltWrkTpCd
     */
    public void setCltWrkTpCd(String cltWrkTpCd) {
        this.cltWrkTpCd = cltWrkTpCd;
    }

    /**
     * @return
     */
    public String getCltDunLtrTpCd() {
        return cltDunLtrTpCd;
    }

    /**
     * @param cltDunLtrTpCd
     */
    public void setCltDunLtrTpCd(String cltDunLtrTpCd) {
        this.cltDunLtrTpCd = cltDunLtrTpCd;
    }

    /**
     * @return
     */
    public String getCmpyNm() {
        return cmpyNm;
    }

    /**
     * @param cmpyNm
     */
    public void setCmpyNm(String cmpyNm) {
        this.cmpyNm = cmpyNm;
    }

    /**
     * @return
     */
    public String getRemFirstLineAddr() {
        return remFirstLineAddr;
    }

    /**
     * @param remFirstLineAddr
     */
    public void setRemFirstLineAddr(String remFirstLineAddr) {
        this.remFirstLineAddr = remFirstLineAddr;
    }

    /**
     * @return
     */
    public String getRemScdLineAddr() {
        return remScdLineAddr;
    }

    /**
     * @param remScdLineAddr
     */
    public void setRemScdLineAddr(String remScdLineAddr) {
        this.remScdLineAddr = remScdLineAddr;
    }

    /**
     * @return
     */
    public String getRemThirdLineAddr() {
        return remThirdLineAddr;
    }

    /**
     * @param remThirdLineAddr
     */
    public void setRemThirdLineAddr(String remThirdLineAddr) {
        this.remThirdLineAddr = remThirdLineAddr;
    }

    /**
     * @return
     */
    public String getRemFrthLineAddr() {
        return remFrthLineAddr;
    }

    /**
     * @param remFrthLineAddr
     */
    public void setRemFrthLineAddr(String remFrthLineAddr) {
        this.remFrthLineAddr = remFrthLineAddr;
    }

    /**
     * @return
     */
    public String getRemCtyAddr() {
        return remCtyAddr;
    }

    /**
     * @param remCtyAddr
     */
    public void setRemCtyAddr(String remCtyAddr) {
        this.remCtyAddr = remCtyAddr;
    }

    /**
     * @return
     */
    public String getRemProvNm() {
        return remProvNm;
    }

    /**
     * @param remProvNm
     */
    public void setRemProvNm(String remProvNm) {
        this.remProvNm = remProvNm;
    }

    /**
     * @return
     */
    public String getRemStCd() {
        return remStCd;
    }

    /**
     * @param remStCd
     */
    public void setRemStCd(String remStCd) {
        this.remStCd = remStCd;
    }

    /**
     * @return
     */
    public String getRemPostCd() {
        return remPostCd;
    }

    /**
     * @param remPostCd
     */
    public void setRemPostCd(String remPostCd) {
        this.remPostCd = remPostCd;
    }

    /**
     * @return
     */
    public String getLocNm() {
        return locNm;
    }

    /**
     * @param locNm
     */
    public void setLocNm(String locNm) {
        this.locNm = locNm;
    }

    /**
     * @return
     */
    public String getAddlLocNm() {
        return addlLocNm;
    }

    /**
     * @param addlLocNm
     */
    public void setAddlLocNm(String addlLocNm) {
        this.addlLocNm = addlLocNm;
    }

    /**
     * @return
     */
    public String getBtcFirstLineAddr() {
        return btcFirstLineAddr;
    }

    /**
     * @param btcFirstLineAddr
     */
    public void setBtcFirstLineAddr(String btcFirstLineAddr) {
        this.btcFirstLineAddr = btcFirstLineAddr;
    }

    /**
     * @return
     */
    public String getBtcScdLineAddr() {
        return btcScdLineAddr;
    }

    /**
     * @param btcScdLineAddr
     */
    public void setBtcScdLineAddr(String btcScdLineAddr) {
        this.btcScdLineAddr = btcScdLineAddr;
    }

    /**
     * @return
     */
    public String getBtcThirdLineAddr() {
        return btcThirdLineAddr;
    }

    /**
     * @param btcThirdLineAddr
     */
    public void setBtcThirdLineAddr(String btcThirdLineAddr) {
        this.btcThirdLineAddr = btcThirdLineAddr;
    }

    /**
     * @return
     */
    public String getBtcFrthLineAddr() {
        return btcFrthLineAddr;
    }

    /**
     * @param btcFrthLineAddr
     */
    public void setBtcFrthLineAddr(String btcFrthLineAddr) {
        this.btcFrthLineAddr = btcFrthLineAddr;
    }

    /**
     * @return
     */
    public String getBtcCtyAddr() {
        return btcCtyAddr;
    }

    /**
     * @param btcCtyAddr
     */
    public void setBtcCtyAddr(String btcCtyAddr) {
        this.btcCtyAddr = btcCtyAddr;
    }

    /**
     * @return
     */
    public String getBtcProvNm() {
        return btcProvNm;
    }

    /**
     * @param btcProvNm
     */
    public void setBtcProvNm(String btcProvNm) {
        this.btcProvNm = btcProvNm;
    }

    /**
     * @return
     */
    public String getBtcStCd() {
        return btcStCd;
    }

    /**
     * @param btcStCd
     */
    public void setBtcStCd(String btcStCd) {
        this.btcStCd = btcStCd;
    }

    /**
     * @return
     */
    public String getBtcPostCd() {
        return btcPostCd;
    }

    /**
     * @param btcPostCd
     */
    public void setBtcPostCd(String btcPostCd) {
        this.btcPostCd = btcPostCd;
    }
    // [QC#7310] UPDATE start
//    /**
//     * @return
//     */
//    public String getCltPsnCdBill() {
//        return cltPsnCdBill;
//    }
//
//    /**
//     * @param cltPsnCdBill
//     */
//    public void setCltPsnCdBill(String cltPsnCdBill) {
//        this.cltPsnCdBill = cltPsnCdBill;
//    }
//
//    /**
//     * @return
//     */
//    public String getCltPsnNmBill() {
//        return cltPsnNmBill;
//    }
//
//    /**
//     * @param cltPsnNmBill
//     */
//    public void setCltPsnNmBill(String cltPsnNmBill) {
//        this.cltPsnNmBill = cltPsnNmBill;
//    }
//
//    /**
//     * @return
//     */
//    public String getCltPsnCdAcct() {
//        return cltPsnCdAcct;
//    }
//
//    /**
//     * @param cltPsnCdAcct
//     */
//    public void setCltPsnCdAcct(String cltPsnCdAcct) {
//        this.cltPsnCdAcct = cltPsnCdAcct;
//    }
//
//    /**
//     * @return
//     */
//    public String getCltPsnNmAcct() {
//        return cltPsnNmAcct;
//    }
//
//    /**
//     * @param cltPsnNmAcct
//     */
//    public void setCltPsnNmAcct(String cltPsnNmAcct) {
//        this.cltPsnNmAcct = cltPsnNmAcct;
//    }

    /**
     * @return cltPsnCd
     */
    public String getCltPsnCd() {
        return cltPsnCd;
    }

    /**
     * @param cltPsnCd set cltPsnCd
     */
    public void setCltPsnCd(String cltPsnCd) {
        this.cltPsnCd = cltPsnCd;
    }

    /**
     * @return cltPsnNm
     */
    public String getCltPsnNm() {
        return cltPsnNm;
    }

    /**
     * @param cltPsnNm set cltPsnNm
     */
    public void setCltPsnNm(String cltPsnNm) {
        this.cltPsnNm = cltPsnNm;
    }

    /**
     * @return cltStmtPhoNum
     */
    public String getCltStmtPhoNum() {
        return cltStmtPhoNum;
    }

    /**
     * @param cltStmtPhoNum set cltStmtPhoNum
     */
    public void setCltStmtPhoNum(String cltStmtPhoNum) {
        this.cltStmtPhoNum = cltStmtPhoNum;
    }

    /**
     * @return cltStmtFaxNum
     */
    public String getCltStmtFaxNum() {
        return cltStmtFaxNum;
    }

    /**
     * @param cltStmtFaxNum set cltStmtFaxNum
     */
    public void setCltStmtFaxNum(String cltStmtFaxNum) {
        this.cltStmtFaxNum = cltStmtFaxNum;
    }

    /**
     * @return cltPsnEmlAddr
     */
    public String getCltPsnEmlAddr() {
        return cltPsnEmlAddr;
    }

    /**
     * @param cltPsnEmlAddr set cltPsnEmlAddr
     */
    public void setCltPsnEmlAddr(String cltPsnEmlAddr) {
        this.cltPsnEmlAddr = cltPsnEmlAddr;
    }
    // [QC#7310] UPDATE end

    /**
     * @return
     */
    public String getGlblCmpyNm() {
        return glblCmpyNm;
    }

    /**
     * @param glblCmpyNm
     */
    public void setGlblCmpyNm(String glblCmpyNm) {
        this.glblCmpyNm = glblCmpyNm;
    }

}
