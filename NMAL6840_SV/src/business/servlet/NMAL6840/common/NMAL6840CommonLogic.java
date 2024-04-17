/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6840.common;

import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_MDSE_COST_TP_CD;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_MDSE_INVTY_COST_PCT;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_RTL_SWH_DESC_TXT;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_RTL_SWH_DSBL_FLG;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_RTL_SWH_MND_FLG;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_RTL_SWH_NM;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.DISPLAY_NM_RTL_SWH_SORT_NUM;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM0072E;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM8093E;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM8148W;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.MESSAGE_PARAM_NOT_WH;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.MESSAGE_PARAM_SRC_WH_SWH;

import java.util.Set;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import business.blap.NMAL6840.NMAL6840CMsg;
import business.servlet.NMAL6840.NMAL6840BMsg;
import business.servlet.NMAL6840.NMAL6840_ABMsg;
import business.servlet.NMAL6840.constant.NMAL6840Constant;
import business.servlet.NMAL6840.constant.NMAL6840Constant.BTN_APP;
import business.servlet.NMAL6840.constant.NMAL6840Constant.BTN_CMN;
import business.servlet.NMAL6840.constant.NMAL6840Constant.BTN_STATUS;
import business.servlet.NMAL6840.constant.NMAL6840Constant.FUNC_CD;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

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
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for SEARCH.
     * </p>
     * @param scrnMsg scrnMsg(BMsg)
     * @return bizMsg(CMsg)
     */
    public static NMAL6840CMsg copyScrnMsgToBizMsgForSearch(NMAL6840BMsg scrnMsg) {
        NMAL6840CMsg bizMsg = createBizMsg(FUNC_CD.SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for SEARCH.
     * </p>
     * @param scrnMsg scrnMsg(BMsg)
     * @return bizMsg(CMsg)
     */
    public static NMAL6840CMsg copyScrnMsgToBizMsgForUpdate(NMAL6840BMsg scrnMsg) {
        NMAL6840CMsg bizMsg = createBizMsg(FUNC_CD.UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    /**
     * <p>
     * Creates the business message.
     * </p>
     * @param funcCd the enumeration of function code
     * @return NMAL6840CMsg
     */
    private static NMAL6840CMsg createBizMsg(FUNC_CD funcCd) {
        NMAL6840CMsg bizMsg = new NMAL6840CMsg();
        bizMsg.setBusinessID(NMAL6840Constant.BIZ_ID);
        bizMsg.setFunctionCode(funcCd.getCode());
        return bizMsg;
    }

    /**
     * <p>
     * The method is doProcess() of NMAL6840_INIT.
     * </p>
     * @param scrnMsg scrnMsg
     * @param handler S21CommonHandler
     */
    public static void doProcessOfInit(NMAL6840BMsg scrnMsg, S21CommonHandler handler) {

        scrnMsg.clearAllGUIAttribute(NMAL6840Constant.SCRN_ID);

        // focus on WH Category.
        scrnMsg.setFocusItem(scrnMsg.rtlWhCatgCd_H1);

        // Sets up the buttons.
        initializeButtonProperties(scrnMsg, handler);

        // digit.
        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            setFractionDigit(scrnMsg.A.no(rowIndex));
        }
    }

    /**
     * <p>
     * Initializes the button properties.
     * </p>
     * 
     * <pre>
     * <b>Application-specific</b>
     * +--------------+-------------------+
     * | Button Name  | enabled/disabled  |
     * +==============+===================+
     * | Search       | enabled           |
     * +--------------+-------------------+
     * | Insert Row   | disabled          |
     * +--------------+-------------------+
     * | Delete Row   | disabled          |
     * +--------------+-------------------+
     * 
     * <b>Common</b>
     * +--------------+-------------------+
     * | Button Name  | enabled/disabled  |
     * +==============+===================+
     * | SAVE         | disabled          |
     * +--------------+-------------------+
     * | SUBMIT       | disabled          |
     * +--------------+-------------------+
     * | APPLY        | disabled          |
     * +--------------+-------------------+
     * | APPROVE      | disabled          |
     * +--------------+-------------------+
     * | REJECT       | disabled          |
     * +--------------+-------------------+
     * | DOWNLOAD     | disabled          |
     * +--------------+-------------------+
     * | DELETE       | disabled          |
     * +--------------+-------------------+
     * | CLEAR        | enabled           |
     * +--------------+-------------------+
     * | RESET        | enabled           |
     * +--------------+-------------------+
     * | RETURN       | enabled           |
     * +--------------+-------------------+
     * </pre>
     * @param scrnMsg scrnMsg
     * @param handler S21CommonHandler
     */
    private static void initializeButtonProperties(NMAL6840BMsg scrnMsg, S21CommonHandler handler) {

        // Application-specific.
        activateButton(BTN_APP.SEARCH, handler);

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCatgCd_H1)) {
            activateButton(BTN_APP.INSERT_ROW, handler);
            activateButton(BTN_APP.DELETE_ROW, handler);
        } else {
            deactivateButton(BTN_APP.INSERT_ROW, handler);
            deactivateButton(BTN_APP.DELETE_ROW, handler);
        }

        // Common.
        deactivateButton(BTN_CMN.SAVE, handler);

        if (scrnMsg.A.getValidCount() > 0) {
            activateButton(BTN_CMN.SUBMIT, handler);
        } else {
            deactivateButton(BTN_CMN.SUBMIT, handler);
        }

        deactivateButton(BTN_CMN.APPLY, handler);
        deactivateButton(BTN_CMN.APPROVE, handler);
        deactivateButton(BTN_CMN.REJECT, handler);
        deactivateButton(BTN_CMN.DOWNLOAD, handler);
        deactivateButton(BTN_CMN.DELETE, handler);
        activateButton(BTN_CMN.CLEAR, handler);
        activateButton(BTN_CMN.RESET, handler);
        activateButton(BTN_CMN.RETURN, handler);
    }

    /**
     * <p>
     * The method is doProcess() of NMAL6840Scrn00_OnClick_Search.
     * </p>
     * @param scrnMsg scrnMsg
     * @param handler S21CommonHandler
     */
    public static void doProcessOfSearch(NMAL6840BMsg scrnMsg, S21CommonHandler handler) {

        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            // enable/disable
            disableInputFields(scrnMsg.A.no(rowIndex));
            // display name
            setDisplayName(scrnMsg.A.no(rowIndex));
            // digit
            setFractionDigit(scrnMsg.A.no(rowIndex));
        }

        if (scrnMsg.A.getValidCount() > 0) {
            // focus on Sub WH Name.
            scrnMsg.setFocusItem(scrnMsg.A.no(0).rtlSwhNm_A1);

            activateButton(BTN_CMN.SUBMIT, handler);
        } else {
            // focus on WH Category.
            scrnMsg.setFocusItem(scrnMsg.rtlWhCatgCd_H1);

            deactivateButton(BTN_CMN.SUBMIT, handler);
        }

        activateButton(BTN_APP.INSERT_ROW, handler);
        activateButton(BTN_APP.DELETE_ROW, handler);
    }

    /**
     * <p>
     * Activates the common button.
     * </p>
     * @param button the enumeration of common button.
     * @param handler S21CommonHandler
     */
    public static void activateButton(BTN_CMN button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.ENABLED);
    }

    /**
     * <p>
     * Deactivates the common button.
     * </p>
     * @param button the enumeration of common button.
     * @param handler S21CommonHandler
     */
    public static void deactivateButton(BTN_CMN button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.DISABLED);
    }

    /**
     * <p>
     * Sets the properties of common button.
     * </p>
     * @param handler S21CommonHandler
     * @param button the enumeration of common button.
     * @param status the button status.
     */
    private static void setButtonProperties(S21CommonHandler handler, BTN_CMN button, BTN_STATUS status) {
        handler.setButtonProperties(button.getButtonName(), button.getEventName(), button.getLabel(), status.getStatus(), null);
    }

    /**
     * <p>
     * Activates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     */
    public static void activateButton(BTN_APP button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.ENABLED);
    }

    /**
     * <p>
     * Deactivates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     */
    public static void deactivateButton(BTN_APP button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.DISABLED);
    }

    /**
     * <p>
     * Sets the properties of common button.
     * </p>
     * @param handler S21CommonHandler
     * @param button the enumeration of common button.
     * @param status the button status.
     */
    private static void setButtonProperties(S21CommonHandler handler, BTN_APP button, BTN_STATUS status) {
        handler.setButtonEnabled(button.getName(), status.isEnabled());
    }

    /**
     * <p>
     * Activates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     * @param id the button id.
     */
    public static void activateTblButton(BTN_APP button, S21CommonHandler handler, Integer id) {
        setTblButtonProperties(handler, button, BTN_STATUS.ENABLED, id);
    }

    /**
     * <p>
     * Deactivates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     * @param id the button id.
     */
    public static void deactivateTblButton(BTN_APP button, S21CommonHandler handler, Integer id) {
        setTblButtonProperties(handler, button, BTN_STATUS.DISABLED, id);
    }

    /**
     * <p>
     * Sets the properties of common button.
     * </p>
     * @param handler S21CommonHandler
     * @param button the enumeration of common button.
     * @param status the button status.
     * @param id the button id.
     */
    private static void setTblButtonProperties(S21CommonHandler handler, BTN_APP button, BTN_STATUS status, Integer id) {
        handler.setButtonEnabled(button.getName(), id, status.isEnabled());
    }

    /**
     * <p>
     * Sets the display name for message.
     * </p>
     * @param scrnRow the row of screen.
     */
    public static void setDisplayName(NMAL6840_ABMsg scrnRow) {
        scrnRow.rtlSwhCd_A1.setNameForMessage(DISPLAY_NM_RTL_SWH_CD);
        scrnRow.rtlSwhNm_A1.setNameForMessage(DISPLAY_NM_RTL_SWH_NM);
        scrnRow.rtlSwhDescTxt_A1.setNameForMessage(DISPLAY_NM_RTL_SWH_DESC_TXT);
        scrnRow.mdseCostTpNm_A1.setNameForMessage(DISPLAY_NM_MDSE_COST_TP_CD);
        scrnRow.mdseInvtyCostPct_A1.setNameForMessage(DISPLAY_NM_MDSE_INVTY_COST_PCT);
        scrnRow.rtlSwhSortNum_A1.setNameForMessage(DISPLAY_NM_RTL_SWH_SORT_NUM);
        scrnRow.rtlSwhDsblFlg_A1.setNameForMessage(DISPLAY_NM_RTL_SWH_DSBL_FLG);
        scrnRow.rtlSwhMndFlg_A1.setNameForMessage(DISPLAY_NM_RTL_SWH_MND_FLG);
    }

    /**
     * <p>
     * Sets the Fraction Digit for display.
     * </p>
     * @param scrnRow the row of screen.
     */
    public static void setFractionDigit(NMAL6840_ABMsg scrnRow) {
        scrnRow.mdseInvtyCostPct_A1.setAppFracDigit(0);
    }

    /**
     * <p>
     * Enables the input fields.
     * </p>
     * @param scrnRow the row of screen
     */
    public static void enableInputFields(NMAL6840_ABMsg scrnRow) {
        setInputFields(scrnRow, false);
    }

    /**
     * <p>
     * Disables the input fields.
     * </p>
     * @param scrnRow the row of screen
     */
    public static void disableInputFields(NMAL6840_ABMsg scrnRow) {
        setInputFields(scrnRow, true);
    }

    /**
     * <p>
     * Sets whether the input fields is editable.
     * </p>
     * @param scrnRow the row of screen
     * @param isProtected if true, protected.
     */
    private static void setInputFields(NMAL6840_ABMsg scrnRow, boolean isProtected) {
        scrnRow.xxChkBox_A1.setInputProtected(isProtected);
        scrnRow.mdseCostTpNm_A1.setInputProtected(isProtected);
        scrnRow.mdseInvtyCostPct_A1.setInputProtected(isProtected);
    }

    /**
     * <p>
     * Checks that the value is duplicated.<br>
     * If the value is duplicated, set error info to item.<br>
     * </p>
     * @param item the field item
     * @param values the values for checking duplication
     */
    public static void checkIsDuplicated(EZDBStringItem item, Set<String> values) {
        if (!ZYPCommonFunc.hasValue(item)) {
            return;
        }
        if (values.contains(item.getValue())) {
            setErrorInfo(item, MESSAGE_CD_NMAM0072E, item.getNameForMessage());
        }
        values.add(item.getValue());
    }

    /**
     * <p>
     * Checks that the value is within the range.<br>
     * If the value is not within the range, set error info to item.<br>
     * </p>
     * @param item the field item
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public static void checkIsWithinRange(EZDBBigDecimalItem item, long minValue, long maxValue) {
        if (!ZYPCommonFunc.hasValue(item) || !ZYPCommonFunc.isNumeric(item.getValue().toPlainString())) {
            return;
        }

        if (!ZYPCommonFunc.isCheckSuutiHanni(item.getValue().longValue(), minValue, maxValue)) {
            setErrorInfo(item, MESSAGE_CD_NMAM8093E, item.getNameForMessage(), minValue, maxValue);
        }
    }

    /**
     * <p>
     * Checks that the value is "2:Warehouse".<br>
     * If the value is not correct, set error info to item.<br>
     * </p>
     * @param value the field item
     */
    public static void checkIsSrcTpWh(EZDBStringItem value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return;
        }

        if (!PROCR_TP.WAREHOUSE.equals(value.getValue())) {
            setWarningInfo(value, MESSAGE_CD_NMAM8148W, value.getNameForMessage(), MESSAGE_PARAM_NOT_WH, MESSAGE_PARAM_SRC_WH_SWH);
        }
    }

    /**
     * <p>
     * Sets the error information to the field item.
     * </p>
     * @param item the field item.
     * @param messageCd the message code
     * @param messageParams the message parameters
     */
    private static void setErrorInfo(EZDBItem item, String messageCd, Object... messageParams) {
        item.setErrorInfo(1, messageCd, toStringArray(messageParams));
    }

    /**
     * <p>
     * Sets the warning information to the field item.
     * </p>
     * @param item the field item.
     * @param messageCd the message code
     * @param messageParams the message parameters
     */
    private static void setWarningInfo(EZDBItem item, String messageCd, Object... messageParams) {
        item.setErrorInfo(2, messageCd, toStringArray(messageParams));
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
     * The method explanation: set SWH Information.
     * @param scrnMsg NMAL6840BMsg
     * @param eventRowIndex Integer
     */
    public static void setSWHInfo(NMAL6840BMsg scrnMsg, Integer eventRowIndex) {

        for (int i = 0; i < scrnMsg.rtlSwhCd_L1.length(); i++) {
            if (scrnMsg.A.no(eventRowIndex).rtlSwhCd_A1.getValue().equals(scrnMsg.rtlSwhCd_L1.no(i).getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlSwhNm_A1, scrnMsg.rtlSwhNm_L1.no(i));
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlSwhDescTxt_A1, scrnMsg.rtlSwhDescTxt_L1.no(i));
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).mdseCostTpNm_A1, scrnMsg.mdseCostTpNm_L1.no(i));
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).mdseInvtyCostPct_A1, scrnMsg.mdseInvtyCostPct_L1.no(i));
                break;
            }
        }
    }

}
