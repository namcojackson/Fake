/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0610.common;

import static business.servlet.NLCL0610.constant.NLCL0610Constant.BIZ_ID;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.COLUMN_DISP_NM_FOR_WH_CODE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.COLUMN_DISP_NM_FOR_WH_NAME;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.COLUMN_SQL_NM_FOR_WH_CODE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.COLUMN_SQL_NM_FOR_WH_NAME;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.COLUMN_WIDTH_FOR_WH_CODE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.COLUMN_WIDTH_FOR_WH_NAME;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.DISPLAY_NM_PHYS_INVTY_CTRL_NM;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.DISPLAY_NM_PHYS_INVTY_DT_FROM;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.DISPLAY_NM_PHYS_INVTY_DT_THRU;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.DISPLAY_NM_RTL_WH_NM;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.FUCTION_CODE_UPDATE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.SCRN_ID;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.SORT_COLUMN_NAME_FOR_WH_CODE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.SORT_CONDITION_FOR_WH_CODE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.WHERE_DISP_NM_FOR_WH_CODE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.WHERE_DISP_NM_FOR_WH_NAME;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.WHERE_SQL_NM_FOR_WH_CODE;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.WHERE_SQL_NM_FOR_WH_NAME;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.WINDOW_TITLE_WH_SEARCH;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import business.blap.NLCL0610.NLCL0610CMsg;
import business.servlet.NLCL0610.NLCL0610BMsg;
import business.servlet.NLCL0610.constant.NLCL0610Constant.BTN_APP;
import business.servlet.NLCL0610.constant.NLCL0610Constant.BTN_CMN;
import business.servlet.NLCL0610.constant.NLCL0610Constant.BTN_STATUS;
import business.servlet.NLCL0610.constant.NLCL0610Constant.FUNC_CD;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610CommonLogic {

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for Search.
     * </p>
     * @param scrnMsg (BMsg)
     * @return bizMsg (CMsg)
     */
    public static NLCL0610CMsg copyScrnMsgToBizMsgForSearch(NLCL0610BMsg scrnMsg) {
        NLCL0610CMsg bizMsg = new NLCL0610CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD.SEARCH.getCode());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for Update.
     * </p>
     * @param scrnMsg (BMsg)
     * @return bizMsg (CMsg)
     */
    public static NLCL0610CMsg copyScrnMsgToBizMsgForUpdate(NLCL0610BMsg scrnMsg) {
        NLCL0610CMsg bizMsg = new NLCL0610CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD.UPDATE.getCode());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setDisplayNameForMessage(NLCL0610BMsg scrnMsg) {

        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlWhNm.setNameForMessage(DISPLAY_NM_RTL_WH_NM);
        scrnMsg.physInvtyDt_F.setNameForMessage(DISPLAY_NM_PHYS_INVTY_DT_FROM);
        scrnMsg.physInvtyDt_T.setNameForMessage(DISPLAY_NM_PHYS_INVTY_DT_THRU);
        scrnMsg.physInvtyCtrlNm.setNameForMessage(DISPLAY_NM_PHYS_INVTY_CTRL_NM);

    }

    /**
     * <p>
     * Set Radio Button Attribute.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setRadioButtonAttribute(NLCL0610BMsg scrnMsg) {

        // Disable radio button of child lines
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, "radio#" + i);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rtlWhCd_A)) {
                guiAttr.setVisibility(false);
                scrnMsg.addGUIAttribute(guiAttr);
            }
        }
    }

    /**
     * <p>
     * Initializes the button properties.
     * </p>
     * @param handler S21CommonHandler
     */
    public static void initializeButtonProperties(S21CommonHandler handler) {

        // Application-specific.
        deactivateButton(BTN_APP.CANCEL, handler);
        deactivateButton(BTN_APP.EDIT, handler);

        // Common.
        deactivateButton(BTN_CMN.SAVE, handler);
        deactivateButton(BTN_CMN.SUBMIT, handler);
        deactivateButton(BTN_CMN.APPLY, handler);
        deactivateButton(BTN_CMN.APPROVE, handler);
        deactivateButton(BTN_CMN.REJECT, handler);
        deactivateButton(BTN_CMN.DOWNLOAD, handler);
        deactivateButton(BTN_CMN.DELETE, handler);
        deactivateButton(BTN_CMN.CLEAR, handler);
        activateButton(BTN_CMN.RESET, handler);
        activateButton(BTN_CMN.RETURN, handler);
    }

    /**
     * <p>
     * Edit button properties.
     * </p>
     * @param handler S21CommonHandler
     */
    public static void editButtonProperties(S21CommonHandler handler) {

        if (isEntryGranted()) {
            // Application-specific.
            activateButton(BTN_APP.CANCEL, handler);
            activateButton(BTN_APP.EDIT, handler);
        }
    }

    /**
     * <p>
     * Disable Edit button properties.
     * </p>
     * @param handler S21CommonHandler
     */
    public static void disableEditButtonProperties(S21CommonHandler handler) {

        // Application-specific.
        deactivateButton(BTN_APP.CANCEL, handler);
        deactivateButton(BTN_APP.EDIT, handler);
    }

    /***
     * <p>
     * Checks if entry is granted.
     * </p>
     * @return If entry is granted, return true.
     */
    public static final boolean isEntryGranted() {
        S21UserProfileService service = S21UserProfileServiceFactory.getInstance().getService();
        return service.isFunctionGranted(service.getContextUserInfo().getUserId(), FUCTION_CODE_UPDATE);
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
     * Get Param NWAL1130 For Warehouse Information
     * @param scrnMsg NLCL0610BMsg
     * @return Param For Warehouse Information
     */
    public static Object[] getParamNWAL1130ForWarehouseInformation(NLCL0610BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = WINDOW_TITLE_WH_SEARCH;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    RW.GLBL_CMPY_CD ");
        sb.append("   ,RW.EZCANCELFLAG ");
        sb.append("   ,RW.RTL_WH_CD ");
        sb.append("   ,RW.RTL_WH_NM ");
        sb.append("FROM ");
        sb.append("    RTL_WH RW ");
        sb.append("WHERE ");
        sb.append("        RW.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        if (ZYPCommonFunc.hasValue(scrnMsg.varCharConstVal_LT)) {

            String[] whPilocTpCdArray = scrnMsg.varCharConstVal_LT.getValue().split(",");
            int count = whPilocTpCdArray.length;

            sb.append("    AND RW.LOC_TP_CD IN (");
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("'").append(whPilocTpCdArray[i].trim()).append("'");
            }

            sb.append(")");
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.varCharConstVal_WO)) {

            String[] whPiWhOwnrCdArray = scrnMsg.varCharConstVal_WO.getValue().split(",");
            int count = whPiWhOwnrCdArray.length;

            sb.append("    AND RW.WH_OWNR_CD IN (");
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("'").append(whPiWhOwnrCdArray[i].trim()).append("'");
            }

            sb.append(")");
        }
        sb.append("    AND RW.EZCANCELFLAG = '0'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = WHERE_DISP_NM_FOR_WH_CODE;
        whereArray0[1] = WHERE_SQL_NM_FOR_WH_CODE;
        whereArray0[2] = scrnMsg.rtlWhCd.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = WHERE_DISP_NM_FOR_WH_NAME;
        whereArray1[1] = WHERE_SQL_NM_FOR_WH_NAME;
        whereArray1[2] = scrnMsg.rtlWhNm.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = COLUMN_DISP_NM_FOR_WH_CODE;
        columnArray0[1] = COLUMN_SQL_NM_FOR_WH_CODE;
        columnArray0[2] = COLUMN_WIDTH_FOR_WH_CODE;
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = COLUMN_DISP_NM_FOR_WH_NAME;
        columnArray1[1] = COLUMN_SQL_NM_FOR_WH_NAME;
        columnArray1[2] = COLUMN_WIDTH_FOR_WH_NAME;
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = SORT_COLUMN_NAME_FOR_WH_CODE;
        sortConditionArray0[1] = SORT_CONDITION_FOR_WH_CODE;
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * Table Column Protect
     * @param scrnMsg NLCL0610BMsg
     */
    public static void setTableScreen(NLCL0610BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A_Left", scrnMsg.A);
        tblColor.setAlternateRowsBG("A_Right", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).physInvtyCtrlNm_A.setInputProtected(true);
            scrnMsg.A.no(i).physInvtyCtrlDescNm_A.setInputProtected(true);
        }
    }

}
