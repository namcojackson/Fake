/*
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC013001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * Qty Based Fee Summary Information Bean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/10/26   Hitachi         T.Fukuta        Create          CSA-QC#61619
 * </pre>
 */
public class NWXC013001SumInfoBean implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** Order Price */
    private BigDecimal orderPrice;

    /** Total Fee */
    private BigDecimal totalFee;

    /** Constructor */
    NWXC013001SumInfoBean() {
        this.orderPrice = BigDecimal.ZERO;
        this.totalFee = BigDecimal.ZERO;
    }

    /**
     * Add Order Price
     * @param augend Price to be added
     */
    public void addOrderPrice(BigDecimal augend) {
        this.orderPrice = this.orderPrice.add(augend);
    }

    /**
     * Add Total Fee
     * @param augend Fee to be Added
     */
    public void addTotalFee(BigDecimal augend) {
        this.totalFee = this.totalFee.add(augend);
    }

    /**
     * Get Order Price
     * @return Order Price
     */
    public BigDecimal getOrderPrice() {
        return this.orderPrice;
    }

    /**
     * Get Total Fee
     * @return Total Fee
     */
    public BigDecimal getTotalFee() {
        return this.totalFee;
    }
}
