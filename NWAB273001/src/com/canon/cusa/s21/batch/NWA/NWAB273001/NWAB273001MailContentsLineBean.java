/**
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NWA.NWAB273001;

import java.math.BigDecimal;

/**
 * <pre>
 * eCheck Capture Batch.
 * This class defines the available used in the batch application
 * program of BusinessID NWAB273001. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/06/2023   Hitachi         M.Hashino       Create          QC#55645
 * 
 *</pre>
 */

public class NWAB273001MailContentsLineBean {

    /** invoice number */
    private String invNum;

    /** invoice date */
    private String invDt;

    /** invoice total deal net amount */
    private BigDecimal invTotDealNetAmt;


    /**
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    /**
     * @return invNum
     */
    public String getInvNum() {
        return invNum;
    }

    /**
     * @param invDt String
     */
    public void setInvDt(String invDt) {
        this.invDt = invDt;
    }

    /**
     * @return invDt
     */
    public String getInvDt() {
        return invDt;
    }

    /**
     * @param invTotDealNetAmt invTotDealNetAmt
     */
    public void setInvTotDealNetAmt(BigDecimal invTotDealNetAmt) {
        this.invTotDealNetAmt = invTotDealNetAmt;
    }

    /**
     * @return invTotDealNetAmt
     */
    public BigDecimal getInvTotDealNetAmt() {
        return invTotDealNetAmt;
    }
}
