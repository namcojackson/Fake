/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDFBigDecimalItem;
import parts.common.EZDItem;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDSBigDecimalItem;

/**
 * <pre>
 * </pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2009   Fujitsu         H.Haga          Create          N/A
 *</pre>
 */
@SuppressWarnings("serial")
class NLXC015007 extends NLXC015006 {

    protected int compare(NLXC015004 rcd0, NLXC015004 rcd1) {
        int result = 0;

        EZDItem item0 = null;
        EZDItem item1 = null;
        String value0 = "";
        String value1 = "";
        String orderBy = NLXC015003.ASC;

        String[] keys = rcd0.getKeys();
        for (int i = 0; i < keys.length; i++) {
            item0 = rcd0.getEZDItem(keys[i]);
            item1 = rcd1.getEZDItem(keys[i]);
            orderBy = rcd0.getOrderBy(keys[i]);

            switch (rcd0.getItemType(keys[i])) {
                case EZDItemAttrDefines.TYPE_SEISU_SYOSU:
                    if (item0 instanceof EZDBBigDecimalItem) {
                        result = compare(((EZDBBigDecimalItem) item0), ((EZDBBigDecimalItem) item1));
                    } else if (item0 instanceof EZDCBigDecimalItem) {
                        result = compare(((EZDCBigDecimalItem) item0), ((EZDCBigDecimalItem) item1));
                    } else if (item0 instanceof EZDFBigDecimalItem) {
                        result = compare(((EZDFBigDecimalItem) item0), ((EZDFBigDecimalItem) item1));
                    } else if (item0 instanceof EZDSBigDecimalItem) {
                        result = compare(((EZDSBigDecimalItem) item0), ((EZDSBigDecimalItem) item1));
                    } else if (item0 instanceof EZDPBigDecimalItem) {
                        result = compare(((EZDPBigDecimalItem) item0), ((EZDPBigDecimalItem) item1));
                    }
                    break;

                case EZDItemAttrDefines.TYPE_HANKAKUSUJI:
                    value0 = getValueString(item0);
                    value1 = getValueString(item1);
                    try {
                        result = compare(Integer.parseInt(value0), Integer.parseInt(value1));
                    } catch (NumberFormatException ex) {
                        result = compare(value0, value1);
                    }
                    break;

                default:
                    value0 = getValueString(item0);
                    value1 = getValueString(item1);
                    result = compare(value0, value1);
                    break;
            }
        }

        if (item0.isClear() || item1.isClear()) {
            return result;
        }

        if (NLXC015003.DESC.equals(orderBy)) {
            result = -result;
        }

        return result;
    }

    protected int compare(EZDBBigDecimalItem bdItem0, EZDBBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return 1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return -1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDCBigDecimalItem bdItem0, EZDCBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return 1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return -1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDFBigDecimalItem bdItem0, EZDFBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return 1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return -1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDSBigDecimalItem bdItem0, EZDSBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return 1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return -1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDPBigDecimalItem bdItem0, EZDPBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return 1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return -1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(String str0, String str1) {
        if (str0.length() == 0 && str1.length() == 0) {
            return 0;
        }

        if (str0.length() == 0 && str1.length() > 0) {
            return 1;
        }

        if (str0.length() > 0 && str1.length() == 0) {
            return -1;
        }

        return str0.compareTo(str1);
    }

    protected int compare(Integer num0, Integer num1) {
        int ret;
        if (num0 != null && num1 != null) {
            ret = num0.compareTo(num1);
        } else if (num0 == null && num1 != null) {
            ret = 1;
        } else if (num0 != null && num1 == null) {
            ret = -1;
        } else {
            ret = 0;
        }
        return ret;
    }
}
