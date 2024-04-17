/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0320.common;

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0320DiffValueSetter {

    private boolean set = false;

    public void setValue(EZDTStringItem trgt, EZDCStringItem src) {
        if (!NSAL0320CommonLogic.isEqualStr(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
        }
    }

    public void setValue(EZDTStringItem trgt, String src) {
        if (!NSAL0320CommonLogic.isEqualStr(trgt.getValue(), src)) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
        }
    }

    public void setValue(EZDTBigDecimalItem trgt, EZDCBigDecimalItem src) {
        if (!NSAL0320CommonLogic.isEqualNum(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
        }
    }

    public void setValue(EZDTBigDecimalItem trgt, BigDecimal src) {
        if (!NSAL0320CommonLogic.isEqualNum(trgt.getValue(), src)) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
        }
    }

    public void setValue(EZDTBigDecimalItem trgt, int src) {
        if (!NSAL0320CommonLogic.isEqualNum(trgt.getValue(), BigDecimal.valueOf(src))) {
            ZYPEZDItemValueSetter.setValue(trgt, BigDecimal.valueOf(src));
            set = true;
        }
    }

    public void setValue(EZDTDateItem trgt, EZDCDateItem src) {
        if (!NSAL0320CommonLogic.isEqualStr(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
        }
    }

    public void setValue(EZDTDateItem trgt, String src) {
        if (!NSAL0320CommonLogic.isEqualStr(trgt.getValue(), src)) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
        }
    }

    public boolean set() {
        return set;
    }

    public void clear() {
        this.set = false;
    }
}
