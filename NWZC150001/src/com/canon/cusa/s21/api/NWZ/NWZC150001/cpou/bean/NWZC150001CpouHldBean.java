package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 *CPO Update API Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/03   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * </pre>
 */
public class NWZC150001CpouHldBean implements Serializable {

    // Serial Version UID
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** XX_RQST_TP_CD*/
    private String              xxRqstTpCd;

    /** CPO_DTL_LINE_NUM*/
    private String              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
    private String              cpoDtlLineSubNum;

    /** HLD_PK*/
    private BigDecimal              hldPk;

    /** HLD_RSN_CD*/
    private String              hldRsnCd;

    /** HLD_DTL_TXT*/
    private String              hldDtlTxt;

    /**
     * <pre>
     * Get xxRqstTpCd
     * </pre>
     * @return xxRqstTpCd
     */
    public String getXxRqstTpCd() {
        return xxRqstTpCd;
    }

    /**
     * <pre>
     * Set xxRqstTpCd
     * </pre>
     * @param xxRqstTpCd Request Type Code
     */
    public void setXxRqstTpCd(String xxRqstTpCd) {
        this.xxRqstTpCd = xxRqstTpCd;
    }

    /**
     * <pre>
     * 
     * </pre>
     * @return cpoDtlLineNum
     */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /**
     * @param cpoDtlLineNum セットする cpoDtlLineNum
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
     * @param cpoDtlLineSubNum セットする cpoDtlLineSubNum
     */
    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    /**
     * @return hldPk
     */
    public BigDecimal getHldPk() {
        return hldPk;
    }

    /**
     * @param hldPk セットする hldPk
     */
    public void setHldPk(BigDecimal hldPk) {
        this.hldPk = hldPk;
    }

    /**
     * @return hldRsnCd
     */
    public String getHldRsnCd() {
        return hldRsnCd;
    }

    /**
     * @param hldRsnCd セットする hldRsnCd
     */
    public void setHldRsnCd(String hldRsnCd) {
        this.hldRsnCd = hldRsnCd;
    }

    /**
     * @return hldDtlTxt
     */
    public String getHldDtlTxt() {
        return hldDtlTxt;
    }

    /**
     * @param hldDtlTxt セットする hldDtlTxt
     */
    public void setHldDtlTxt(String hldDtlTxt) {
        this.hldDtlTxt = hldDtlTxt;
    }
}
