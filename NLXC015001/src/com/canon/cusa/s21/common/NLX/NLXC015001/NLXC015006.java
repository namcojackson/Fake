/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItem;
import parts.common.EZDFBigDecimalItem;
import parts.common.EZDFDateItem;
import parts.common.EZDFStringItem;
import parts.common.EZDItem;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;

/**
 * <pre>
 * </pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2009   Fujitsu         H.Haga          Create          N/A
 *</pre>
 */
@SuppressWarnings("serial")
class NLXC015006 implements Comparator, Serializable {

    public int compare(Object arg0, Object arg1) {
        NLXC015004 rcd0 = (NLXC015004) arg0;
        NLXC015004 rcd1 = (NLXC015004) arg1;
        return compare(rcd0, rcd1);
    }

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
                // 10:I/O item type: Numerical value with decimal
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

                // 34:I/O item type: Normal-width figure
                case EZDItemAttrDefines.TYPE_HANKAKUSUJI:
                    value0 = getValueString(item0);
                    value1 = getValueString(item1);
                    try {
                        result = compare(new Integer(value0), new Integer(value1));
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

            if (result != 0) {
                break;
            }

        }

        if (NLXC015003.DESC.equals(orderBy)) {
            result = -result;
        }
        return result;
    }

    protected String getValueString(EZDItem ezdItem) {
        String rtnValue = "";
        // String
        if (ezdItem instanceof EZDBStringItem) {
            rtnValue = ((EZDBStringItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDCStringItem) {
            rtnValue = ((EZDCStringItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDFStringItem) {
            rtnValue = ((EZDFStringItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDSStringItem) {
            rtnValue = ((EZDSStringItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDPStringItem) {
            rtnValue = ((EZDPStringItem) ezdItem).getValue().trim();
            // Date
        } else if (ezdItem instanceof EZDBDateItem) {
            rtnValue = ((EZDBDateItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDCDateItem) {
            rtnValue = ((EZDCDateItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDFDateItem) {
            rtnValue = ((EZDFDateItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDSDateItem) {
            rtnValue = ((EZDSDateItem) ezdItem).getValue().trim();
        } else if (ezdItem instanceof EZDPDateItem) {
            rtnValue = ((EZDPDateItem) ezdItem).getValue().trim();

        }
        return rtnValue;
    }

    protected int compare(EZDBBigDecimalItem bdItem0, EZDBBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return -1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return 1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDCBigDecimalItem bdItem0, EZDCBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return -1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return 1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDFBigDecimalItem bdItem0, EZDFBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return -1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return 1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDSBigDecimalItem bdItem0, EZDSBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return -1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return 1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(EZDPBigDecimalItem bdItem0, EZDPBigDecimalItem bdItem1) {
        if (bdItem0.isClear() && bdItem1.isClear()) {
            return 0;
        }

        if (bdItem0.isClear() && !bdItem1.isClear()) {
            return -1;
        }

        if (!bdItem0.isClear() && bdItem1.isClear()) {
            return 1;
        }

        return compare(bdItem0.getValue(), bdItem1.getValue());
    }

    protected int compare(String str0, String str1) {
        return str0.compareTo(str1);
    }

    protected int compare(BigDecimal num0, BigDecimal num1) {
        return num0.compareTo(num1);
    }

    protected int compare(Integer num0, Integer num1) {
        return num0.compareTo(num1);
    }

}
