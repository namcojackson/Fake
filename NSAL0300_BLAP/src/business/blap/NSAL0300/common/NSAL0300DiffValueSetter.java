/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0300.common;

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItem;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2016/03/04   Hitachi         T.Kanasaka      Update          QC2208
 * 2016/08/29   Hitachi         T.Kanasaka      Update          QC#4806
 *</pre>
 */
public class NSAL0300DiffValueSetter {

    private boolean set = false;

    // START 2016/03/04 T.Kanasaka [QC2208, MOD]
    public boolean setValue(EZDTStringItem trgt, EZDCStringItem src) {
        if (!NSAL0300CommonLogic.isEqualStr(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTStringItem trgt, EZDSStringItem src) {
        if (!NSAL0300CommonLogic.isEqualStr(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTStringItem trgt, String src) {
        if (!NSAL0300CommonLogic.isEqualStr(trgt.getValue(), src)) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTBigDecimalItem trgt, EZDTBigDecimalItem src) {
        if (!NSAL0300CommonLogic.isEqualNum(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTBigDecimalItem trgt, EZDCBigDecimalItem src) {
        if (!NSAL0300CommonLogic.isEqualNum(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTBigDecimalItem trgt, EZDSBigDecimalItem src) {
        if (!NSAL0300CommonLogic.isEqualNum(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTBigDecimalItem trgt, BigDecimal src) {
        if (!NSAL0300CommonLogic.isEqualNum(trgt.getValue(), src)) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTBigDecimalItem trgt, int src) {
        if (!NSAL0300CommonLogic.isEqualNum(trgt.getValue(), BigDecimal.valueOf(src))) {
            ZYPEZDItemValueSetter.setValue(trgt, BigDecimal.valueOf(src));
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTDateItem trgt, EZDCDateItem src) {
        if (!NSAL0300CommonLogic.isEqualStr(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTDateItem trgt, EZDSDateItem src) {
        if (!NSAL0300CommonLogic.isEqualStr(trgt.getValue(), src.getValue())) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }

    public boolean setValue(EZDTDateItem trgt, String src) {
        if (!NSAL0300CommonLogic.isEqualStr(trgt.getValue(), src)) {
            ZYPEZDItemValueSetter.setValue(trgt, src);
            set = true;
            return true;
        }
        return false;
    }
    // END 2016/03/04 T.Kanasaka [QC2208, MOD]

    public boolean set() {
        return set;
    }

    public void clear() {
        this.set = false;
    }
}
