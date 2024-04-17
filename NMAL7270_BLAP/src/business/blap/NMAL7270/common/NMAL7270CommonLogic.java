/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7270.common;

import java.math.BigDecimal;

import parts.common.EZDCStringItem;
import parts.common.EZDFStringItem;
import business.blap.NMAL7270.NMAL7270CMsg;
import business.blap.NMAL7270.NMAL7270Query;
import business.blap.NMAL7270.constant.NMAL7270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * NMAL7270CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/09/27   Fujitsu         R.Nakamura      Update          QC#6931
 * 2018/06/07   Fujitsu         T.Noguchi       Update          QC#26099
 *</pre>
 */
public class NMAL7270CommonLogic {

    /**
     * toStr.
     * @param val BigDecimal
     * @return String
     */
    public static String toStr(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.toString();
        } else {
            return "";
        }
    }
    // QC#9694 2016/07/08 Add Start
    /**
     * checkPrcPrcdGrp
     * @param bizMsg NMAL7260CMsg
     * @return boolean
     */
    public static boolean checkPrcPrcdGrp(NMAL7270CMsg bizMsg) {
        boolean rtnValue = false;
        if (!ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk_H1)) {
            int count = NMAL7270Query.getInstance().getCountDefPrcd(bizMsg);
            if (count > 1) {
                rtnValue = true;
            }
        }
        return rtnValue;
    }
    // QC#9694 2016/07/08 Add End

    // Add Start 2016/09/27 QC#6931
    public static String convertDateFormat(String dt) {
        return dt == null ? null : dt.replaceAll(NMAL7270Constant.DT_CONV_FORMAT[0], NMAL7270Constant.DT_CONV_FORMAT[1]);
    }

    public static void setStringItem(EZDFStringItem strItem, String setValue) {
        if (ZYPCommonFunc.hasValue(setValue)) {
            ZYPEZDItemValueSetter.setValue(strItem, setValue);
        } else {
            strItem.clear();
        }
    }
    // Add End 2016/09/27 QC#6931

    // 2018/06/07 QC#26099 Add Start
    public static boolean checkDecimalDigit(EZDCStringItem item, int scale) {

        if (!ZYPCommonFunc.hasValue(item)) {
            return true;
        }

        try {
            BigDecimal dec = new BigDecimal(item.getValue());
            int curScale = dec.scale();
            if (curScale > scale) {
                return false;
            }
            dec = dec.setScale(scale);
            item.setValue(dec.toString());
        }
        catch (NumberFormatException e) {
            // do nothing
        }
        return true;
    }

    public static String formatAmount(EZDCStringItem item) {

        String retValue = item.getValue();
        
        try {
            BigDecimal dec = new BigDecimal(item.getValue());

            retValue = NMAL7270Constant.DF_AMT.format(dec);
        }
        catch (NumberFormatException e) {
            // do nothing
        }
        catch (IllegalArgumentException e) {
            // do nothing
        }
        return retValue;
    }
    // 2018/06/07 QC#26099 Add End
}
