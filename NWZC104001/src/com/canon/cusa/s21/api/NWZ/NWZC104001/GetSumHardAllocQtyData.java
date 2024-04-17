/**
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/

package com.canon.cusa.s21.api.NWZ.NWZC104001;

import java.math.BigDecimal;

/**
 * <pre>
 * AllocationItemData
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/07   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class GetSumHardAllocQtyData {

    /** SOFT_ALLOC_PK */
    private BigDecimal softAllocPK;

    /** SUM_HARD_ALLOC_QTY */
    private BigDecimal sumHardAllocQty;

    /**
     * @return softAllocPK
     */
    public BigDecimal getSoftAllocPK() {
        return softAllocPK;
    }

    /**
     * @param softAllocPK value of softAllocPK
     */
    public void setSoftAllocPK(BigDecimal softAllocPK) {
        this.softAllocPK = softAllocPK;
    }

    /**
     * @return sumHardAllocQty
     */
    public BigDecimal getSumHardAllocQty() {
        return sumHardAllocQty;
    }

    /**
     * @param sumHardAllocQty value of sumHardAllocQty
     */
    public void setSumHardAllocQty(BigDecimal sumHardAllocQty) {
        this.sumHardAllocQty = sumHardAllocQty;
    }

}
