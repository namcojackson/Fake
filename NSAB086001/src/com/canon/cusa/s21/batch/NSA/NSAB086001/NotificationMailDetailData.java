package com.canon.cusa.s21.batch.NSA.NSAB086001;

import java.math.BigDecimal;

/**
 * <pre>
 * Meter Reading Notification for Email
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/11/10   Hitachi         Y.Nagasawa      Create          QC#61756
 */
public class NotificationMailDetailData {

    /** DS_CONTR_PK*/
    private  BigDecimal dsContrPk;

    /** DS_CONTR_NUM*/
    private  String dsContrNum;

    /** SER_NUM*/
    private  String serNum;

    /** T_MDL_NM*/
    private  String t_MdlNm;

    /** MTR_LB_DESC_TXT*/
    private  String mtrLbDescTxt;

    /** READ_MTR_CNT*/
    private  BigDecimal readMtrCnt;

    /** BLLG_SCHD_THRU_DT*/
    private  String bllgSchdThruDt;

    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    public String getDsContrNum() {
        return dsContrNum;
    }

    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getT_MdlNm() {
        return t_MdlNm;
    }

    public void setT_MdlNm(String mdlNm) {
        t_MdlNm = mdlNm;
    }

    public String getMtrLbDescTxt() {
        return mtrLbDescTxt;
    }

    public void setMtrLbDescTxt(String mtrLbDescTxt) {
        this.mtrLbDescTxt = mtrLbDescTxt;
    }

    public BigDecimal getReadMtrCnt() {
        return readMtrCnt;
    }

    public void setReadMtrCnt(BigDecimal readMtrCnt) {
        this.readMtrCnt = readMtrCnt;
    }

    public String getBllgSchdThruDt() {
        return bllgSchdThruDt;
    }

    public void setBllgSchdThruDt(String bllgSchdThruDt) {
        this.bllgSchdThruDt = bllgSchdThruDt;
    }

}
