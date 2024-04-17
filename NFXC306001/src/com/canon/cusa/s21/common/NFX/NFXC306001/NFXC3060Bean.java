/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * NFXC3060Bean.
 * 
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2009   Canon      M.Moriyama   Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC306001;

import java.math.BigDecimal;

/**
 * NFXC3060Bean.
 */
public class NFXC3060Bean {

    /** oraSqNo. */
    private BigDecimal oraSqNo = BigDecimal.ZERO;

    /** oraSqNoStr. */
    private String oraSqNoStr = null;

    /** rtrnNo. */
    private String rtrnNo = null;

    /**
     * setOraSqNo.
     * @param pOraSqNo
     */
    public void setOraSqNo(BigDecimal pOraSqNo) {
        oraSqNo = pOraSqNo;

    }

    /**
     * getOraSqNo.
     * @return oraSqNo
     */
    public BigDecimal getOraSqNo() {
        return oraSqNo;

    }

    /**
     * setOraSqNoStr.
     * @param pOraSqNoStr
     */
    public void setOraSqNoStr(String pOraSqNoStr) {
        oraSqNoStr = pOraSqNoStr;

    }

    /**
     * getOraSqNoStr.
     * @return oraSqNoStr
     */
    public String getOraSqNoStr() {
        return oraSqNoStr;

    }

    /**
     * setRtrnNo.
     * @param pRtrnNo
     */
    public void setRtrnNo(String pRtrnNo) {
        rtrnNo = pRtrnNo;

    }

    /**
     * getRtrnNo.
     * @return rtrnNo
     */
    public String getRtrnNo() {
        return rtrnNo;

    }

}
