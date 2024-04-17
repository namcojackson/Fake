/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC189001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/31   Fujitsu         K.Ishizuka      Create          QC#18426(Sol#123)
 * </pre>
 */
public class ParamHdrBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String mdseCd_M;

    private String serNum;

    private BigDecimal dsContrPk;

    private boolean listFlg;

    public String getMdseCd_M() {
        return mdseCd_M;
    }

    public void setMdseCd_M(String mdseCd_M) {
        this.mdseCd_M = mdseCd_M;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    public boolean isListFlg() {
        return listFlg;
    }

    public void setListFlg(boolean listFlg) {
        this.listFlg = listFlg;
    }

}
