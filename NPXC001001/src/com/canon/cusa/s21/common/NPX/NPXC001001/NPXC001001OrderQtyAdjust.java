/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 * <pre>
 * OrderQtyAdjust
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/05   Hitachi         T.Kanasaka      Create          QC1003
 * 2013/04/23   Hitachi         A.Kohinata      Update          QC1117
 * 2013/04/25   Hitachi         A.Kohinata      Update          QC1133
 *</pre>
 */
public class NPXC001001OrderQtyAdjust {

    /** BigDecimal.compareTo result:1 */
    private static final int LEFT_IS_LARGE = 1;

    /** BigDecimal.compareTo result:-1 */
    private static final int LEFT_IS_SMALL = -1;

    /**
     * Get Calculation Purchase Requisition Quantity
     * @param prchReqQty BigDecimal
     * @param ropQty BigDecimal
     * @param minOrdQty BigDecimal
     * @param incrOrdQty BigDecimal
     * @param maxInvtyQty BigDecimal
     * @return Calculation Purchase Requisition Quantity
     */
    public static BigDecimal getCalcPrchReqQty(BigDecimal prchReqQty, BigDecimal ropQty, BigDecimal minOrdQty, BigDecimal incrOrdQty, BigDecimal maxInvtyQty) {

        // START 2013/04/25 A.Kohinata [QC1133 ADD]
        // prchReqQty = 0
        if (ZYPCommonFunc.hasValue(prchReqQty)) {
            if (prchReqQty.compareTo(BigDecimal.ZERO) == 0) {
                return prchReqQty;
            }
        }
        // END   2013/04/25 A.Kohinata [QC1133 ADD]

        BigDecimal calcPrchReqQty = prchReqQty;

        // maxInvtyQty > 0
        if (ZYPCommonFunc.hasValue(maxInvtyQty) && ZYPCommonFunc.hasValue(ropQty)) {
            if (maxInvtyQty.compareTo(BigDecimal.ZERO) == LEFT_IS_LARGE) {
                // START 2013/04/23 A.Kohinata [QC1117 UPD]
                //calcPrchReqQty = maxInvtyQty.subtract(ropQty);
                calcPrchReqQty = maxInvtyQty.subtract(ropQty).add(calcPrchReqQty);
                // END   2013/04/23 A.Kohinata [QC1117 UPD]
            }
        }

        // minOrdQty > 0 and calcPrchReqQty < minOrdQty
        if (ZYPCommonFunc.hasValue(minOrdQty) && ZYPCommonFunc.hasValue(calcPrchReqQty)) {
            if (minOrdQty.compareTo(BigDecimal.ZERO) == LEFT_IS_LARGE && calcPrchReqQty.compareTo(minOrdQty) == LEFT_IS_SMALL) {
                calcPrchReqQty = minOrdQty;
            }
        }

        // minOrdQty > 0 and incrOrdQty > 0
        // and calcPrchReqQty > minOrdQty
        if (ZYPCommonFunc.hasValue(minOrdQty) && ZYPCommonFunc.hasValue(incrOrdQty) && ZYPCommonFunc.hasValue(calcPrchReqQty)) {
            if (minOrdQty.compareTo(BigDecimal.ZERO) == LEFT_IS_LARGE && incrOrdQty.compareTo(BigDecimal.ZERO) == LEFT_IS_LARGE && calcPrchReqQty.compareTo(minOrdQty) == LEFT_IS_LARGE) {
                calcPrchReqQty = minOrdQty.add(calcPrchReqQty.subtract(minOrdQty).divide(incrOrdQty, RoundingMode.UP).multiply(incrOrdQty));
            }
        }

        return calcPrchReqQty;
    }
}
