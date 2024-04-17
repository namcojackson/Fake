package com.canon.cusa.s21.batch.NLB.NLBB206001;

import java.math.BigDecimal;

/**
 * <pre>
 * Intangible Inventory Transaction Creation Batch Bean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2012   Fujitsu         S.Takami        Create          N/A
 * 02/19/2013   Fujitsu         K.Fujita        Update          N/A
 * 2013/07/29   Fujitsu         S.Takami        Update          R-OM028
 * 2016/09/09   Hitachi         Y.Takeno        Update          QC#12003
 * 2022/06/14   CITS            K.Ogino         Update          QC#59704
 *</pre>
 */
public class NLBB206001Bean {
    private String ezuptime;
    private String ezuptimezone;
    private String shpgPlnNum;
    private String trxHdrNum;
    private String trxLineNum;
    private String trxLineSubNum;
    private String mdseCd;
    private String stkStsCd;
    private BigDecimal ordQty;
    private String soNum;
    private String soSlpNum;
    private String bolNum;
    private String carrCd;
    private String proNum;
    private String sellToCustCd;
    private String billToCustCd;
    private String shipToCustCd;
    private String shipToLocNm;
    private String slsRepTocCd;
    private String vndCd;
    private String invtyCtrlFlg;    // 2013/07/29 R-OM028 Add
    private String invtyValFlg;
    private String mdseTpCd;        // 2013/07/29 R-OM028 Add
    private String cpoDtlEzuptime;
    private String cpoDtlEzuptimezone;
    private String setMdseCd;
    private String setShpgPlnNum;
    private String shipCpltCd;
    private String poReqFlg;
    private String sysSrcCd;        // 2013/07/29 R-OM028 Add
    private String srtNum;          // 2013/07/29 R-OM028 Add
    private String dtPtn;
    private BigDecimal dsCpoConfigPk;   // 2015/09/18 CSA
    private String invtyLocCd;   // 2015/09/18 CSA
    private String actlShipDt;      // 2016/09/09 QC#12003
    private String updShpgStsCd; //2017/01/26 add
    // QC#59704 Add Start
    private String shpgStsCd;       
    // QC#59704 Add End

    public String getEzuptime() {
        return ezuptime;
    }
    public void setEzuptime(String ezuptime) {
        this.ezuptime = ezuptime;
    }
    public String getEzuptimezone() {
        return ezuptimezone;
    }
    public void setEzuptimezone(String ezuptimezone) {
        this.ezuptimezone = ezuptimezone;
    }
    public String getShpgPlnNum() {
        return shpgPlnNum;
    }
    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }
    public String getTrxHdrNum() {
        return trxHdrNum;
    }
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }
    public String getTrxLineNum() {
        return trxLineNum;
    }
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }
    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }
    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }
    public String getMdseCd() {
        return mdseCd;
    }
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }
    public String getStkStsCd() {
        return stkStsCd;
    }
    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }
    public BigDecimal getOrdQty() {
        return ordQty;
    }
    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }
    public String getSoNum() {
        return soNum;
    }
    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }
    public String getSoSlpNum() {
        return soSlpNum;
    }
    public void setSoSlpNum(String soSlpNum) {
        this.soSlpNum = soSlpNum;
    }
    public String getBolNum() {
        return bolNum;
    }
    public void setBolNum(String bolNum) {
        this.bolNum = bolNum;
    }
    public String getCarrCd() {
        return carrCd;
    }
    public void setCarrCd(String carrCd) {
        this.carrCd = carrCd;
    }
    public String getProNum() {
        return proNum;
    }
    public void setProNum(String proNum) {
        this.proNum = proNum;
    }
    public String getSellToCustCd() {
        return sellToCustCd;
    }
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }
    public String getBillToCustCd() {
        return billToCustCd;
    }
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }
    public String getShipToCustCd() {
        return shipToCustCd;
    }
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }
    public String getShipToLocNm() {
        return shipToLocNm;
    }
    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }
    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }
    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }
    public String getVndCd() {
        return vndCd;
    }
    public void setVndCd(String vndCd) {
        this.vndCd = vndCd;
    }
    public String getInvtyValFlg() {
        return invtyValFlg;
    }
    public void setInvtyValFlg(String invtyValFlg) {
        this.invtyValFlg = invtyValFlg;
    }
    public String getDtPtn() {
        return dtPtn;
    }
    public void setDtPtn(String dtPtn) {
        this.dtPtn = dtPtn;
    }
    public String getSetMdseCd() {
        return setMdseCd;
    }
    public void setSetMdseCd(String setMdseCd) {
        this.setMdseCd = setMdseCd;
    }
    public String getSetShpgPlnNum() {
        return setShpgPlnNum;
    }
    public void setSetShpgPlnNum(String setShpgPlnNum) {
        this.setShpgPlnNum = setShpgPlnNum;
    }
    public String getShipCpltCd() {
        return shipCpltCd;
    }
    public void setShipCpltCd(String shipCpltCd) {
        this.shipCpltCd = shipCpltCd;
    }
    public void setPoReqFlg(String poReqFlg) {
        this.poReqFlg = poReqFlg;
    }
    public String getPoReqFlg() {
        return poReqFlg;
    }
    public String getCpoDtlEzuptime() {
        return cpoDtlEzuptime;
    }
    public void setCpoDtlEzuptime(String cpoDtlEzuptime) {
        this.cpoDtlEzuptime = cpoDtlEzuptime;
    }
    public String getCpoDtlEzuptimezone() {
        return cpoDtlEzuptimezone;
    }
    public void setCpoDtlEzuptimezone(String cpoDtlEzuptimezone) {
        this.cpoDtlEzuptimezone = cpoDtlEzuptimezone;
    }
    // 2013/07/29 R-OM028 Add Start
    public String getInvtyCtrlFlg() {
        return invtyCtrlFlg;
    }
    public void setInvtyCtrlFlg(String invtyCtrlFlg) {
        this.invtyCtrlFlg = invtyCtrlFlg;
    }
    public String getMdseTpCd() {
        return mdseTpCd;
    }
    public void setMdseTpCd(String mdseTpCd) {
        this.mdseTpCd = mdseTpCd;
    }
    public String getSrtNum() {
        return srtNum;
    }
    public void setSrtNum(String srtNum) {
        this.srtNum = srtNum;
    }
    public String getSysSrcCd() {
        return sysSrcCd;
    }
    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }
    // 2013/07/29 R-OM028 Add Start

    // 2015/09/18 CSA add start
    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }
    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }
    public String getInvtyLocCd() {
        return invtyLocCd;
    }
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }
    // 2015/09/18 CSA add end

    // 2016/09/09 QC#12003 add Start
    public String getActlShipDt() {
        return actlShipDt;
    }
    public void setActlShipDt(String actlShipDt) {
        this.actlShipDt = actlShipDt;
    }
    // 2016/09/09 QC#12003 add End

    public String getUpdShpgStsCd() {
        return updShpgStsCd;
    }
    public void setUpdShpgStsCd(String updShpgStsCd) {
        this.updShpgStsCd = updShpgStsCd;
    }

    // QC#597043 Add Start
    public String getShpgStsCd() {
        return shpgStsCd;
    }
    public void setShpgStsCd(String shpgStsCd) {
        this.shpgStsCd = shpgStsCd;
    }
    // QC#59704 Add End

    public String toString() {
        StringBuffer objString = new StringBuffer("Record ===>\r\n");

        objString.append("EZUPTIME         => [" + nullToString(this.ezuptime) + "]\r\n");
        objString.append("EZUPTIMEZONE     => [" + nullToString(this.ezuptimezone) + "]\r\n");
        objString.append("SHPG_PLN_NUM     => [" + nullToString(this.shpgPlnNum) + "]\r\n");
        objString.append("TRX_HDR_NUM      => [" + nullToString(this.trxHdrNum) + "]\r\n");
        objString.append("TRX_LINE_NUM     => [" + nullToString(this.trxLineNum) + "]\r\n");
        objString.append("TRX_LINE_SUB_NUM => [" + nullToString(this.trxLineSubNum) + "]\r\n");
        objString.append("MDSE_CD          => [" + nullToString(this.mdseCd) + "]\r\n");
        objString.append("STK_STS_CD       => [" + nullToString(this.stkStsCd) + "]\r\n");
        objString.append("ORD_QTY          => [" + nullToString(this.ordQty) + "]\r\n");
        objString.append("SO_NUM           => [" + nullToString(this.soNum) + "]\r\n");
        objString.append("SO_SLP_NUM       => [" + nullToString(this.soSlpNum) + "]\r\n");
        objString.append("BOL_NUM          => [" + nullToString(this.bolNum) + "]\r\n");
        objString.append("CARR_CD          => [" + nullToString(this.carrCd) + "]\r\n");
        objString.append("PRO_NUM          => [" + nullToString(this.proNum) + "]\r\n");
        objString.append("SELL_TO_CUST_CD  => [" + nullToString(this.sellToCustCd) + "]\r\n");
        objString.append("BILL_TO_CUST_CD  => [" + nullToString(this.billToCustCd) + "]\r\n");
        objString.append("SHIP_TO_CUST_CD  => [" + nullToString(this.shipToCustCd) + "]\r\n");
        objString.append("SHIP_TO_LOC_NM   => [" + nullToString(this.shipToLocNm) + "]\r\n");
        objString.append("SLS_REP_TOC_CD   => [" + nullToString(this.slsRepTocCd) + "]\r\n");
        objString.append("VND_CD           => [" + nullToString(this.vndCd) + "]\r\n");
        objString.append("INVTY_CTRL_FLG   => [" + nullToString(this.invtyCtrlFlg) + "]\r\n");  // 2013/07/29 R-OM028 Add
        objString.append("INVTY_VAL_FLG    => [" + nullToString(this.invtyValFlg) + "]\r\n");
        objString.append("MDSE_TP_CD       => [" + nullToString(this.mdseTpCd) + "]\r\n");      // 2013/07/29 R-OM028 Add
        objString.append("SET_SHPG_PLN_NUM => [" + nullToString(this.setShpgPlnNum) + "]\r\n");
        objString.append("SET_MDSE_CD      => [" + nullToString(this.setMdseCd) + "]\r\n");
        objString.append("SHIP_CPLT_CD     => [" + nullToString(this.shipCpltCd) + "]\r\n");
        objString.append("PO_REQ_FLG       => [" + nullToString(this.poReqFlg) + "]\r\n");
        objString.append("CPO_DTL_EZUPTIME     => [" + nullToString(this.cpoDtlEzuptime) + "]\r\n");
        objString.append("CPO_DTL_EZUPTIMEZONE => [" + nullToString(this.cpoDtlEzuptimezone) + "]\r\n");
        objString.append("SYS_SRC_CD       => [" + nullToString(this.sysSrcCd) + "]\r\n");        // 2013/07/29 R-OM028 Add
        objString.append("SRT_NUM          => [" + nullToString(this.srtNum) + "]\r\n");        // 2013/07/29 R-OM028 Add
        objString.append("DT_PTN           => [" + nullToString(this.dtPtn) + "]\r\n");
        objString.append("DS_CPO_CONFIG_PK => [" + nullToString(this.dsCpoConfigPk) + "]\r\n"); // 2015/09/18 CSA add
        objString.append("ACTL_SHIP_DT     => [" + nullToString(this.actlShipDt) + "]\r\n"); // 2016/09/09 QC#12003
        // QC#59704 Add Start
        objString.append("SHPG_STS_CD      => [" + nullToString(this.shpgStsCd) + "]\r\n");
        // QC#59704 Add End

        objString.append("<=== Record");

        return objString.toString();
    }

    private String nullToString(Object obj) {
        String rtnVal = "(null)";
        if (null != obj) {
            rtnVal = obj.toString();
        }
        return rtnVal;
    }
}
