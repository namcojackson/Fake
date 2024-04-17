/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.parts;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 * <pre>
 * Set value to EZDItem.
 * </pre>
 * 
 * @author Y.Aikawa
 */
public class DFBEZDItemValueSetter {

    /*************************************************
     * setValue
     * Set String value to EZDTStringItem. 
     * @author Y.Aikawa 
     * @param ezdTStringItem EZDTStringItem
     * @param str String
     *************************************************/
    public static void setValue (EZDTStringItem ezdTStringItem, String str) {
    	if (ZYPCommonFunc.hasValue(str)) {
            ZYPEZDItemValueSetter.setValue(ezdTStringItem, str);
    	} else {
    		ezdTStringItem.clear();
    	}
    }
    
    /*************************************************
     * setValue
     * Set BigDecimal value to EZDTBigDecimalItem. 
     * @author Y.Aikawa 
     * @param ezdTBigDecimalItem EZDTBigDecimalItem
     * @param bd BigDecimal
     *************************************************/
    public static void setValue (EZDTBigDecimalItem ezdTBigDecimalItem, BigDecimal bd) {
    	if (ZYPCommonFunc.hasValue(bd)) {
            ZYPEZDItemValueSetter.setValue(ezdTBigDecimalItem, bd);
    	} else {
    		ezdTBigDecimalItem.clear();
    	}
    }
    
    /*************************************************
     * setValueZero
     * Set BigDecimal value to EZDTBigDecimalItem.
     * If BigDecimal value is null, set 0.
     * @author Y.Aikawa 
     * @param ezdTBigDecimalItem EZDTBigDecimalItem
     * @param bd BigDecimal
     *************************************************/
    public static void setValueZero (EZDTBigDecimalItem ezdTBigDecimalItem, BigDecimal bd) {
    	if (ZYPCommonFunc.hasValue(bd)) {
            ZYPEZDItemValueSetter.setValue(ezdTBigDecimalItem, bd);
    	} else {
            ZYPEZDItemValueSetter.setValue(ezdTBigDecimalItem, BigDecimal.ZERO);
    	}
    }
    
    /*************************************************
     * setValue
     * Set String(Date format) value to EZDTDateItem. 
     * @author Y.Aikawa 
     * @param ezdTDateItem EZDTDateItem
     * @param strDt String
     *************************************************/
    public static void setValue (EZDTDateItem ezdTDateItem, String strDt) {
    	if (ZYPCommonFunc.hasValue(strDt)) {
            ZYPEZDItemValueSetter.setValue(ezdTDateItem, strDt);
    	} else {
    		ezdTDateItem.clear();
    	}
    }
    
    /*************************************************
     * setValue
     * Set String value to EZCTStringItem. 
     * @author Y.Aikawa 
     * @param ezdCStringItem EZCTStringItem
     * @param str String
     *************************************************/
    public static void setValue (EZDCStringItem ezdCStringItem, String str) {
    	if (ZYPCommonFunc.hasValue(str)) {
            ZYPEZDItemValueSetter.setValue(ezdCStringItem, str);
    	} else {
    		ezdCStringItem.clear();
    	}
    }
    
    /*************************************************
     * setValue
     * Set BigDecimal value to EZDCBigDecimalItem.
     * @author Y.Aikawa 
     * @param ezdCBigDecimalItem EZDCBigDecimalItem
     * @param bd BigDecimal
     *************************************************/
    public static void setValue (EZDCBigDecimalItem ezdCBigDecimalItem, BigDecimal bd) {
    	if (ZYPCommonFunc.hasValue(bd)) {
            ZYPEZDItemValueSetter.setValue(ezdCBigDecimalItem, bd);
    	} else {
    		ezdCBigDecimalItem.clear();
    	}
    }    

    /*************************************************
     * setValueZero
     * Set BigDecimal value to EZDCBigDecimalItem.
     * If BigDecimal value is null, set 0.
     * @author Y.Aikawa 
     * @param ezdCBigDecimalItem EZDCBigDecimalItem
     * @param bd BigDecimal
     *************************************************/
    public static void setValueZero (EZDCBigDecimalItem ezdCBigDecimalItem, BigDecimal bd) {
    	if (ZYPCommonFunc.hasValue(bd)) {
            ZYPEZDItemValueSetter.setValue(ezdCBigDecimalItem, bd);
    	} else {
            ZYPEZDItemValueSetter.setValue(ezdCBigDecimalItem, BigDecimal.ZERO);
    	}
    }

    /*************************************************
     * setValue
     * Set String value to EZDBStringItem. 
     * @author Y.Aikawa 
     * @param ezdBStringItem EZDBStringItem
     * @param str String
     *************************************************/
    public static void setValue (EZDBStringItem ezdBStringItem, String str) {
    	if (ZYPCommonFunc.hasValue(str)) {
            ZYPEZDItemValueSetter.setValue(ezdBStringItem, str);
    	} else {
    		ezdBStringItem.clear();
    	}
    }
    
    /*************************************************
     * setValue
     * Set BigDecimal value to EZDBBigDecimalItem.
     * @author Y.Aikawa 
     * @param ezdBBigDecimalItem EZDBBigDecimalItem
     * @param bd BigDecimal
     *************************************************/
    public static void setValue (EZDBBigDecimalItem ezdBBigDecimalItem, BigDecimal bd) {
    	if (ZYPCommonFunc.hasValue(bd)) {
            ZYPEZDItemValueSetter.setValue(ezdBBigDecimalItem, bd);
    	} else {
    		ezdBBigDecimalItem.clear();
    	}
    }

    /*************************************************
     * setValueZero
     * Set BigDecimal value to EZDBBigDecimalItem.
     * If BigDecimal value is null, set 0.
     * @author Y.Aikawa 
     * @param ezdBBigDecimalItem EZDBBigDecimalItem
     * @param bd BigDecimal
     *************************************************/
    public static void setValueZero (EZDBBigDecimalItem ezdBBigDecimalItem, BigDecimal bd) {
    	if (ZYPCommonFunc.hasValue(bd)) {
            ZYPEZDItemValueSetter.setValue(ezdBBigDecimalItem, bd);
    	} else {
            ZYPEZDItemValueSetter.setValue(ezdBBigDecimalItem, BigDecimal.ZERO);
    	}
    }    
}
