package com.canon.cusa.s21.api.NMZ.NMZC003001;

import java.math.BigDecimal;

/**
 * NMZC0030 is data bean class.
 */
public class AddrValidationBean {

    /** cntyPk */
    private BigDecimal cntyPk;
    /** cntyNm */
    private String cntyNm;

    /** @param cntyPk BigDecimal */
    public void setCntyPk(BigDecimal cntyPk) {
        this.cntyPk = cntyPk;
    }
    /** @return cntyPk */
    public BigDecimal getCntyPk() {
        return cntyPk;
    }
    /** @param cntyNm String */
    public void setCntyNm(String cntyNm) {
        this.cntyNm = cntyNm;
    }
    /** @return cntyNm */
    public String getCntyNm() {
        return cntyNm;
    }
}
