package com.canon.cusa.s21.batch.NWC.NWCB001001;

import java.io.Serializable;
import java.math.BigDecimal;

public class SoDivideKey implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** line number */
    private String cpoDtlLineNum;
    /** so number */
    private String soNum;
    /** bol number */
    private String bolNum;
    /** pro number */
    private String proNum;
    /** ds cpo config pk */
    private BigDecimal dsCpoConfigPk;
    public SoDivideKey() {
    }

    public String getBolNum() {
        return bolNum;
    }

    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    public String getProNum() {
        return proNum;
    }

    public String getSoNum() {
        return soNum;
    }

    public void setBolNum(String bolNum) {
        this.bolNum = bolNum;
    }

    public void setCpoDtlLineNum(String lineNum) {
        this.cpoDtlLineNum = lineNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }

    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }

}
