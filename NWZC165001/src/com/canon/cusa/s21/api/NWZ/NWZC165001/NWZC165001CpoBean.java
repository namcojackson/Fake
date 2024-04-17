package com.canon.cusa.s21.api.NWZ.NWZC165001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * NWZC1650 Order Header Workflow Rejection And Approval API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWZC165001CpoBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** HOLD_MODE */
    private String hldMode;

    /** HLD_RSN_CD */
    private String hldRsnCd;

    /** CPO_DTL_LINE_NUM */
    private String cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM */
    private String cpoDtlLineSubNum;

    /** APRV_CMNT */
    private String aprvCmnt;

    /** HLD_PK */
    private BigDecimal hldPk;

    /** CPO_ORD_NUM (for Hold) */
    private String cpoOrdNum_H;

    /** CPO_DTL_LINE_NUM (for Hold) */
    private String cpoDtlLineNum_H;

    /** CPO_DTL_LINE_SUB_NUM (for Hold) */
    private String cpoDtlLineSubNum_H;

    /** SHPG_PLN_NUM (for Hold) */
    private String shpgPlnNum_H;

    /** HLD_RSN_NM (for Hold) */
    private String hldRsnNm_H;

    /** HLD_LVL_CD (for Hold) */
    private String hldLvlCd_H;

    /** AUTO_CANC_ORD_FLG */
    private String autoCancOrdFlg;

    /** SLS_DT */
    private String slsDt;

    /** USER_ID */
    private String userId;

    /** @return glblCmpyCd */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /** @return cpoOrdNum */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /** @return hldMode */
    public String getHldMode() {
        return hldMode;
    }

    /** @return hldRsnCd */
    public String getHldRsnCd() {
        return hldRsnCd;
    }

    /** @return hldRsnCd */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /** @return cpoDtlLineSubNum */
    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    /** @return aprvCmnt */
    public String getAprvCmnt() {
        return aprvCmnt;
    }

    /** @return hldPk */
    public BigDecimal getHldPk() {
        return hldPk;
    }

    /** @return cpoOrdNum_H */
    public String getCpoOrdNum_H() {
        return cpoOrdNum_H;
    }

    /** @return cpoDtlLineNum_H */
    public String getCpoDtlLineNum_H() {
        return cpoDtlLineNum_H;
    }

    /** @return cpoDtlLineSubNum_H */
    public String getCpoDtlLineSubNum_H() {
        return cpoDtlLineSubNum_H;
    }

    /** @return shpgPlnNum_H */
    public String getShpgPlnNum_H() {
        return shpgPlnNum_H;
    }

    /** @return hldRsnNm_H */
    public String getHldRsnNm_H() {
        return hldRsnNm_H;
    }

    /** @return hldLvlCd_H */
    public String getHldLvlCd_H() {
        return hldLvlCd_H;
    }

    /** @return autoCancOrdFlg */
    public String getAutoCancOrdFlg() {
        return autoCancOrdFlg;
    }

    /** @return slsDt */
    public String getSlsDt() {
        return slsDt;
    }

    /** @return userId */
    public String getUserId() {
        return userId;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    };

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    };

    public void setHldMode(String hldMode) {
        this.hldMode = hldMode;
    }

    public void setHldRsnCd(String hldRsnCd) {
        this.hldRsnCd = hldRsnCd;
    }

    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    public void setAprvCmnt(String aprvCmnt) {
        this.aprvCmnt = aprvCmnt;
    };

    public void setHldPk(BigDecimal hldPk) {
        this.hldPk = hldPk;
    }

    public void setCpoOrdNum_H(String cpoOrdNum_H) {
        this.cpoOrdNum_H = cpoOrdNum_H;
    }

    public void setCpoDtlLineNum_H(String cpoDtlLineNum_H) {
        this.cpoDtlLineNum_H = cpoDtlLineNum_H;
    }

    public void setCpoDtlLineSubNum_H(String cpoDtlLineSubNum_H) {
        this.cpoDtlLineSubNum_H = cpoDtlLineSubNum_H;
    }

    public void setAutoCancOrdFlg(String autoCancOrdFlg) {
        this.autoCancOrdFlg = autoCancOrdFlg;
    }

    public void setShpgPlnNum_H(String shpgPlnNum_H) {
        this.shpgPlnNum_H = shpgPlnNum_H;
    }

    public void setHldRsnNm_H(String hldRsnNm_H) {
        this.hldRsnNm_H = hldRsnNm_H;
    }

    public void setHldLvlCd_H(String hldLvlCd_H) {
        this.hldLvlCd_H = hldLvlCd_H;
    }

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
