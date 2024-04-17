/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import java.math.BigDecimal;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/31/2015   Fujitsu         T.Nakamura      Create          NA#ASCC CLICK
 *</pre>
 */
public class SvcPgmBean {

    /** Default Bill Type Code */
    private String defBillTpCd;

    /** AHS Available Flag */
    private String isAHSAvalFlg;

    /** Price Rate */
    private BigDecimal svcLborUnitAmt;

    /** 
     * Default Bill Type Code
     * @return String
     */
    public String getDefBillTpCd() {
        return defBillTpCd;
    }
    /** 
     * Default Bill Type Code
     * @param defBillTpCd String
     */
    public void setDefBillTpCd(String defBillTpCd) {
        this.defBillTpCd = defBillTpCd;
    }

    /** 
     * AHS Available Flag
     * @return String
     */
    public String getIsAHSAvalFlg() {
        return isAHSAvalFlg;
    }

    /** 
     * AHS Available Flag
     * @param isAHSAvalFlg String
     */
    public void setIsAHSAvalFlg(String isAHSAvalFlg) {
        this.isAHSAvalFlg = isAHSAvalFlg;
    }

    /** 
     * Price Rate
     * @return BigDecimal
     */
    public BigDecimal getSvcLborUnitAmt() {
        return svcLborUnitAmt;
    }

    /** 
     * Price Rate
     * @param svcLborUnitAmt BigDecimal
     */
    public void setSvcLborUnitAmt(BigDecimal svcLborUnitAmt) {
        this.svcLborUnitAmt = svcLborUnitAmt;
    }

}
