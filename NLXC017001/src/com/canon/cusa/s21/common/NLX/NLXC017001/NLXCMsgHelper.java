/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC017001;

import java.math.BigDecimal;

import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPItem;
import parts.common.EZDPStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTItem;
import parts.common.EZDTStringItem;

/**
 * Convert to concatenated string delimited by comma from any arguments.
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/29/2009   Fujitsu         K.Okamoto       Create          N/A
 *</pre>
 */
public final class NLXCMsgHelper {

    /** null-string */
    private static final String STR_NULL = "null";

    /**
     * Private Constractor.
     */
    private NLXCMsgHelper() {
    }

    /**
     * The value specified for a changeable argument is returned
     * by the string representation of the comma district switching off. 
     * <br>
     * The item to which it is assumed to be specified is only 
     * the following types.
     * <br>
     * Other types can be specified even in case of the value of
     * what type in case of the type with the toString method. 
     * <pre>
     * String
     * BigDecimal
     * EZDTStringItem      (TMsg: String value item)
     * EZDTBigDecimalItem  (TMsg: Number value item)
     * EZDTDateItem        (TMsg: Date value item)
     * EZDPStringItem      (PMsg: String value item)
     * EZDPBigDecimalItem  (PMsg: Number value item)
     * EZDPDateItem        (PMsg: Date value item)
     * </pre>
     * 
     * @param items  any dynamic arguments
     * @return       concateneted string
     */
    public static String toListedString(Object... items) {
        StringBuilder sb = new StringBuilder();
        Object itm = null;

        if (items == null) {
            return STR_NULL;
        }

        // looping by arguments
        for (int i = 0; i < items.length; i++) {
            itm = items[i];
            if (i > 0) {
                sb.append(", ");
            }

            // validate null value
            if (itm == null) {
                sb.append(STR_NULL);
                continue;
            }

            // validate value type
            if (itm instanceof String) {
                // is string type
                sb.append((String) itm);

            } else if (itm instanceof EZDTItem) {
                // is TMsg type
                EZDTItem tItm = (EZDTItem) itm;

                if (tItm.isClear()) {
                    // is cleared value
                    sb.append(STR_NULL);

                } else if (tItm instanceof EZDTStringItem) {
                    // is string value item
                    sb.append(((EZDTStringItem) tItm).getValue());

                } else if (tItm instanceof EZDTBigDecimalItem) {
                    // is number value item
                    sb.append(((EZDTBigDecimalItem) tItm).getValue());

                } else if (tItm instanceof EZDTDateItem) {
                    // is date value item
                    sb.append(((EZDTDateItem) tItm).getValue());

                }

            } else if (itm instanceof EZDPItem) {
                // is PMsg type
                EZDPItem pItm = (EZDPItem) itm;

                if (pItm.isClear()) {
                    // is cleared value
                    sb.append(STR_NULL);

                } else if (pItm instanceof EZDPStringItem) {
                    // is string value item
                    sb.append(((EZDPStringItem) pItm).getValue());

                } else if (pItm instanceof EZDPBigDecimalItem) {
                    // is number value item
                    sb.append(((EZDPBigDecimalItem) pItm).getValue());

                } else if (pItm instanceof EZDPDateItem) {
                    // is date value item
                    sb.append(((EZDPDateItem) pItm).getValue());

                }

            } else if (itm instanceof BigDecimal) {
                // is BigDecimal type
                sb.append(((BigDecimal) itm).toPlainString());

            } else {
                // is other type
                sb.append(itm.toString());
            }
        }

        return sb.toString();
    }
}
