/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6840.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTStringItem;
import business.blap.NMAL6840.NMAL6840CMsg;
import business.blap.NMAL6840.NMAL6840SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * <p>
 * NMAL6840 Sub Warehouse Setup.
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * </pre>
 */
public class NMAL6840CommonLogic {

    /**
     * <p>
     * Gets the string value from {@link EZDCStringItem}.<br>
     * If the value is null, returns the empty string.<br>
     * </p>
     * @param stringItem {@link EZDCStringItem}
     * @return the string value.
     */
    public static String getValueIfNullReturnEmpty(EZDCStringItem stringItem) {
        if (stringItem != null && ZYPCommonFunc.hasValue(stringItem)) {
            return stringItem.getValue();
        } else {
            return "";
        }
    }

    /**
     * <p>
     * Gets the string value from {@link EZDTStringItem}.<br>
     * If the value is null, returns the empty string.<br>
     * </p>
     * @param stringItem EZDTStringItem
     * @return the string value.
     */
    public static String getValueIfNullReturnEmpty(EZDTStringItem stringItem) {
        if (stringItem != null && ZYPCommonFunc.hasValue(stringItem)) {
            return stringItem.getValue();
        } else {
            return "";
        }
    }

    /**
     * <p>
     * Checks if the check box is checked.
     * </p>
     * @param checkBox the check box
     * @return if the check box is checked, returns true.
     */
    public static boolean isChecked(EZDCStringItem checkBox) {
        return (ZYPConstant.CHKBOX_ON_Y.equals(checkBox.getValue()));
    }

    /**
     * <p>
     * Checks if the value is changed after search.
     * </p>
     * @param itemOfSMsg the field item of SMsg (cache)
     * @param itemOfCMsg the field item of CMsg (screen)
     * @return if the value is changed, returns true.
     */
    public static boolean isChangedAfterSearch(EZDSStringItem itemOfSMsg, EZDCStringItem itemOfCMsg) {
        // if the value of SMsg is empty, has NOT searched yet.
        if (!ZYPCommonFunc.hasValue(itemOfSMsg)) {
            return false;
        }
        return !(itemOfSMsg.getValue().equals(itemOfCMsg.getValue()));
    }

    /**
     * <p>
     * Sets the error information to the field item.
     * </p>
     * @param item the field item.
     * @param messageCd the message code
     * @param messageParams the message parameters
     */
    public static void setErrorInfo(EZDCItem item, String messageCd, Object... messageParams) {
        item.setErrorInfo(1, messageCd, toStringArray(messageParams));
    }

    /**
     * <p>
     * Sets the message information to the CMsg.
     * </p>
     * @param cMsg bizMsg.
     * @param messageCd the message code
     * @param messageParams the message parameters
     */
    public static void setMessageInfo(EZDCMsg cMsg, String messageCd, Object... messageParams) {
        cMsg.setMessageInfo(messageCd, toStringArray(messageParams));
    }

    /**
     * <p>
     * Converts the array of {@link Object} to the array of
     * {@link String}.
     * </p>
     * @param objects the array of Object.
     * @return the array of String.
     */
    private static String[] toStringArray(Object[] objects) {
        if (objects == null) {
            return null;
        }
        String[] params = new String[objects.length];
        for (int index = 0; index < objects.length; index++) {
            if (objects[index] == null) {
                params[index] = "";
            } else {
                params[index] = objects[index].toString();
            }
        }
        return params;
    }

    /**
     * <p>
     * Cleans the table data.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    public static void clearTable(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
    }

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownListForSWH(NMAL6840CMsg cMsg, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_L1.no(i), (String) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_LD.no(i), (String) pullDownData.get(dbColumn[1]));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_L1.no(i), (String) pullDownData.get(dbColumn[2]));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhDescTxt_L1.no(i), (String) pullDownData.get(dbColumn[3]));
            ZYPEZDItemValueSetter.setValue(cMsg.mdseCostTpNm_L1.no(i), (String) pullDownData.get(dbColumn[4]));
            ZYPEZDItemValueSetter.setValue(cMsg.mdseInvtyCostPct_L1.no(i), (BigDecimal) pullDownData.get(dbColumn[5]));

            if (i >= cMsg.rtlSwhCd_L1.length()) {
                break;
            }
        }
    }

}
