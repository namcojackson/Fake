/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC164001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * NWZC164001Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         H.Nagashima     Create          N/A
 * 2017/04/13   Fujitsu         T.Aoi           Update          S21_NA#18207
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWZC164001Bean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    /** Workflow key */
    private String wfKey;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** SLS_DT */
    private String slsDt;

    /** HLD_PK */
    private BigDecimal hldPk;

    /** HLD_RSN_CD */
    private String hldRsnCd;

    // 2017/04/13 S21_NA#18207 Add Start
    /** HLD_RSN_NM */
    private String hldRsnNm;
    // 2017/04/13 S21_NA#18207 Add End

    /** HLD_DT */
    private String hldDt;

    /** HLD_QTY */
    private BigDecimal hldQty;

    /** CPO_DTL_LINE_NUM */
    private String cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM */
    private String cpoDtlLineSubNum;

    /** SHPG_PLN_NUM */
    private String shpgPlnNum;

    /** ORD_PROC_NODE_CD */
    private String ordProcNodeCd;

    /** ORD_PROC_NODE_NM */
    private String ordProcNodeNm;

    /** VLD_APVL_NODE_PRFL_CD */
    private String vldApvlNodePrflCd;

    /** CR_APVL_NODE_PRFL_CD */
    private String crApvlNodePrflCd;

    /** PRFT_APVL_NODE_PRFL_CD */
    private String prftdApvlNodePrflCd;

    /** SPLY_ABUSE_NODE_PRFL_CD */
    private String splyAbuseNodePrflCd;

    /** BYOT_NODE_PREL_CD */
    private String byotNodePrflCd;

    /** ORD_PROC_NODE_PRFL_NM_VA */
    private String ordProcNodePrflNmVa;

    /** ORD_PROC_NODE_PRFL_NM_CA */
    private String ordProcNodePrflNmCa;

    /** ORD_PROC_NODE_PRFL_NM_PA */
    private String ordProcNodePrflNmPa;

    /** ORD_PROC_NODE_PRFL_NM_SA */
    private String ordProcNodePrflNmSa;

    /** ORD_PROC_NODE_PRFL_NM_BA */
    private String ordProcNodePrflNmBa;

    // add start 2023/04/25 QC#61337
    /** MAN_PRC_NODE_PRFL_CD */
    private String manPrcNodePrflCd;

    /** ORD_PROC_NODE_PRFL_NM_MA */
    private String ordProcNodePrflNmMa;
   // add end 2023/04/25 QC#61337

    /**
     * @return wfKey
     */
    public String getWfKey() {
        return wfKey;
    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @return slsDt
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @return hldPk
     */
    public BigDecimal getHldPk() {
        return hldPk;
    }

    /**
     * @return hldRsnCd
     */
    public String getHldRsnCd() {
        return hldRsnCd;
    }

    // 2017/04/13 S21_NA#18207 Add Start
    /**
     * @return hldRsnNm
     */
    public String getHldRsnNm() {
        return hldRsnNm;
    }
    // 2017/04/13 S21_NA#18207 Add End

    /**
     * @return hldDt
     */
    public String getHldDt() {
        return hldDt;
    }

    /**
     * @return hldQty
     */
    public BigDecimal getHldQty() {
        return hldQty;
    }

    /**
     * @return cpoDtlLineNum
     */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /**
     * @return cpoDtlLineSubNum
     */
    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    /**
     * @return shpgPlnNum
     */
    public String getShpgPlnNum() {
        return shpgPlnNum;
    }

    /**
     * @return ordProcNodeCd
     */
    public String getOrdProcNodeCd() {
        return ordProcNodeCd;
    }

    /**
     * @return ordProcNodeNm
     */
    public String getOrdProcNodeNm() {
        return ordProcNodeNm;
    }

    /**
     * @return vldApvlNodePrflCd
     */
    public String getVldApvlNodePrflCd() {
        return vldApvlNodePrflCd;
    }

    /**
     * @return crApvlNodePrflCd
     */
    public String getCrApvlNodePrflCd() {
        return crApvlNodePrflCd;
    }

    /**
     * @return prftdApvlNodePrflCd
     */
    public String getPrftdApvlNodePrflCd() {
        return prftdApvlNodePrflCd;
    }

    /**
     * @return splyAbuseNodePrflCd
     */
    public String getSplyAbuseNodePrflCd() {
        return splyAbuseNodePrflCd;
    }

    /**
     * @return byotNodePrflCd
     */
    public String getByotNodePrflCd() {
        return byotNodePrflCd;
    }

    /**
     * @param wfKey String
     */
    public void setWfKey(String wfKey) {
        this.wfKey = wfKey;
    }

    /**
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @param cpoOrdNum String
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @param slsDt String
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @param hldPk String
     */
    public void setHldPk(BigDecimal hldPk) {
        this.hldPk = hldPk;
    }

    /**
     * @param hldRsnCd String
     */
    public void setHldRsnCd(String hldRsnCd) {
        this.hldRsnCd = hldRsnCd;
    }

    // 2017/04/13 S21_NA#18207 Add Start
    /**
     * @param hldRsnNm String
     */
    public void setHldRsnNm(String hldRsnNm) {
        this.hldRsnNm = hldRsnNm;
    }

    // 2017/04/13 S21_NA#18207 Add End

    /**
     * @param hldDt String
     */
    public void setHldDt(String hldDt) {
        this.hldDt = hldDt;
    }

    /**
     * @param hldQty String
     */
    public void setHldQty(BigDecimal hldQty) {
        this.hldQty = hldQty;
    }

    /**
     * @param cpoDtlLineNum String
     */
    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    /**
     * @param cpoDtlLineSubNum String
     */
    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    /**
     * @param shpgPlnNum String
     */
    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }

    /**
     * @param ordProcNodeCd String
     */
    public void setOrdProcNodeCd(String ordProcNodeCd) {
        this.ordProcNodeCd = ordProcNodeCd;
    }

    /**
     * @param ordProcNodeNm String
     */
    public void setOrdProcNodeNm(String ordProcNodeNm) {
        this.ordProcNodeNm = ordProcNodeNm;
    }

    /**
     * @param vldApvlNodePrflCd String
     */
    public void setVldApvlNodePrflCd(String vldApvlNodePrflCd) {
        this.vldApvlNodePrflCd = vldApvlNodePrflCd;
    }

    /**
     * @param crApvlNodePrflCd String
     */
    public void setCrApvlNodePrflCd(String crApvlNodePrflCd) {
        this.crApvlNodePrflCd = crApvlNodePrflCd;
    }

    /**
     * @param prftdApvlNodePrflCd String
     */
    public void setPrftdApvlNodePrflCd(String prftdApvlNodePrflCd) {
        this.prftdApvlNodePrflCd = prftdApvlNodePrflCd;
    }

    /**
     * @param splyAbuseNodePrflCd String
     */
    public void setSplyAbuseNodePrflCd(String splyAbuseNodePrflCd) {
        this.splyAbuseNodePrflCd = splyAbuseNodePrflCd;
    }

    /**
     * @param byotNodePrflCd String
     */
    public void setByotNodePrflCd(String byotNodePrflCd) {
        this.byotNodePrflCd = byotNodePrflCd;
    }

    /**
     * @return ordProcNodePrflNmVa
     */
    public String getOrdProcNodePrflNmVa() {
        return ordProcNodePrflNmVa;
    }

    /**
     * @return ordProcNodePrflNmCa
     */
    public String getOrdProcNodePrflNmCa() {
        return ordProcNodePrflNmCa;
    }

    /**
     * @return ordProcNodePrflNmPa
     */
    public String getOrdProcNodePrflNmPa() {
        return ordProcNodePrflNmPa;
    }

    /**
     * @return ordProcNodePrflNmSa
     */
    public String getOrdProcNodePrflNmSa() {
        return ordProcNodePrflNmSa;
    }

    /**
     * @return ordProcNodePrflNmBa
     */
    public String getOrdProcNodePrflNmBa() {
        return ordProcNodePrflNmBa;
    }

    /**
     * @param ordProcNodePrflNmVa String
     */
    public void setOrdProcNodePrflNmVa(String ordProcNodePrflNmVa) {
        this.ordProcNodePrflNmVa = ordProcNodePrflNmVa;
    }

    /**
     * @param ordProcNodePrflNmCa String
     */
    public void setOrdProcNodePrflNmCa(String ordProcNodePrflNmCa) {
        this.ordProcNodePrflNmCa = ordProcNodePrflNmCa;
    }

    /**
     * @param ordProcNodePrflNmPa String
     */
    public void setOrdProcNodePrflNmPa(String ordProcNodePrflNmPa) {
        this.ordProcNodePrflNmPa = ordProcNodePrflNmPa;
    }

    /**
     * @param ordProcNodePrflNmSa String
     */
    public void setOrdProcNodePrflNmSa(String ordProcNodePrflNmSa) {
        this.ordProcNodePrflNmSa = ordProcNodePrflNmSa;
    }

    /**
     * @param ordProcNodePrflNmBa String
     */
    public void setOrdProcNodePrflNmBa(String ordProcNodePrflNmBa) {
        this.ordProcNodePrflNmBa = ordProcNodePrflNmBa;
    }

    // add start 2023/04/25 QC#61337
    /**
     * @return manPrcNodePrflCd
     */
    public String getManPrcNodePrflCd() {
        return this.manPrcNodePrflCd;
    }

    /**
     * @param manPrcNodePrflCd String
     */
    public void setManPrcNodePrflCd(String manPrcNodePrflCd) {
        this.manPrcNodePrflCd = manPrcNodePrflCd;
    }

    /**
     * @return ordProcNodePrflNmMa
     */
    public String getOrdProcNodePrflNmMa() {
        return this.ordProcNodePrflNmMa;
    }

    /**
     * @param ordProcNodePrflNmMa String
     */
    public void setOrdProcNodePrflNmMa(String ordProcNodePrflNmMa) {
        this.ordProcNodePrflNmMa = ordProcNodePrflNmMa;
    }
   // add end 2023/04/25 QC#61337

}
