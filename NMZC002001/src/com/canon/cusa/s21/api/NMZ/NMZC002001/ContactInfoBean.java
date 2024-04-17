package com.canon.cusa.s21.api.NMZ.NMZC002001;

import java.math.BigDecimal;

/**
 * NMZC0020 is data bean class.
 */
public class ContactInfoBean {

    /** dsCtacPsnRelnPk */
    private BigDecimal dsCtacPsnRelnPk;

    /** ctacPsnPk */
    private BigDecimal ctacPsnPk;

    /** dsCtacPntPk */
    private BigDecimal dsCtacPntPk;

    /** dsCtacPntTpCd */
    private String dsCtacPntTpCd;

    /** dsCtacPntActvFlg */
    private String dsCtacPntActvFlg;

    public void setDsCtacPsnRelnPk(BigDecimal dsCtacPsnRelnPk) {
        this.dsCtacPsnRelnPk = dsCtacPsnRelnPk;
    }

    public BigDecimal getDsCtacPsnRelnPk() {
        return dsCtacPsnRelnPk;
    }

    public void setCtacPsnPk(BigDecimal ctacPsnPk) {
        this.ctacPsnPk = ctacPsnPk;
    }

    public BigDecimal getCtacPsnPk() {
        return ctacPsnPk;
    }

    public void setDsCtacPntPk(BigDecimal dsCtacPntPk) {
        this.dsCtacPntPk = dsCtacPntPk;
    }

    public BigDecimal getDsCtacPntPk() {
        return dsCtacPntPk;
    }

    public void setDsCtacPntTpCd(String dsCtacPntTpCd) {
        this.dsCtacPntTpCd = dsCtacPntTpCd;
    }

    public String getDsCtacPntTpCd() {
        return dsCtacPntTpCd;
    }

    public void setDsCtacPntActvFlg(String dsCtacPntActvFlg) {
        this.dsCtacPntActvFlg = dsCtacPntActvFlg;
    }

    public String getDsCtacPntActvFlg() {
        return dsCtacPntActvFlg;
    }
 
}
