/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC502001;

import java.io.Serializable;

/**
 * <pre>
 * AP Invoice Workflow Status Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */
public class NFZC502001TokenObjectLine implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** AP_INV_NUM */
    private String apInvNum;

    /** AP_VND_CD */
    private String apVndCd;

    /**
     * get AP_INV_NUM
     * @return String
     */
    public String getApInvNum() {
        return apInvNum;
    }

    /**
     * set AP_INV_NUM
     * @param pk BigDecimal
     */
    public void setApInvNum(String apInvNum) {
        this.apInvNum = apInvNum;
    }

    /**
     * get AP_VND_CD
     * @return String
     */
    public String getApVndCd() {
        return apVndCd;
    }

    /**
     * set AP_VND_CD
     * @param pk BigDecimal
     */
    public void setApVndCd(String apVndCd) {
        this.apVndCd = apVndCd;
    }

}
