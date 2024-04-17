/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1240.common;

import static business.servlet.NPAL1240.constant.NPAL1240Constant.BIZ_ID;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.DISPLAY_NM_ASL_QLFY_REF_CD;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.DISPLAY_NM_ASL_QLFY_TP;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.FUNC_CD_SEARCH;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import business.blap.NPAL1240.NPAL1240CMsg;
import business.servlet.NPAL1240.NPAL1240BMsg;
import business.servlet.NPAL1240.NPAL1240_RBMsgArray;
import business.servlet.NPAL1240.constant.NPAL1240Constant.BTN_APP;
import business.servlet.NPAL1240.constant.NPAL1240Constant.BTN_CMN;
import business.servlet.NPAL1240.constant.NPAL1240Constant.BTN_STATUS;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Business ID : NPAL1240 Qualifier Setup
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240CommonLogic {

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for Search.
     * </p>
     * @param scrnMsg (BMsg)
     * @return bizMsg (CMsg)
     */
    public static NPAL1240CMsg copyScrnMsgToBizMsgForSearch(NPAL1240BMsg scrnMsg) {
        NPAL1240CMsg bizMsg = new NPAL1240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    /**
     * <p>
     * Sets the display name.
     * </p>
     * @param scrnMsg
     */
    public static void setDisplayName(NPAL1240BMsg scrnMsg) {
        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.A.no(index).aslQlfyTpCd_A.setNameForMessage(DISPLAY_NM_ASL_QLFY_TP);
            scrnMsg.A.no(index).aslQlfyRefCd_A.setNameForMessage(DISPLAY_NM_ASL_QLFY_REF_CD);
        }
    }

    /**
     * <p>
     * Protects all input fields.
     * </p>
     * @param scrnMsg BMsg
     */
    public static void protectAllInputFields(NPAL1240BMsg scrnMsg) {
        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.A.no(index).xxChkBox_A.setInputProtected(true);
            scrnMsg.A.no(index).aslQlfyRelnPk_A.setInputProtected(true);
            scrnMsg.A.no(index).aslQlfyTpCd_A.setInputProtected(true);
            scrnMsg.A.no(index).aslQlfyRefCd_A.setInputProtected(true);
        }
    }

    /**
     * <p>
     * Initializes common buttons.
     * </p>
     * @param scrnMsg BMsg
     * @param handler S21CommonHandler
     */
    public static void initializeCommonButtons(NPAL1240BMsg scrnMsg, S21CommonHandler handler) {
        setButtonProperties(handler, BTN_CMN.CLEAR, BTN_STATUS.DISABLED);
        setButtonProperties(handler, BTN_CMN.CLOSE, BTN_STATUS.ENABLED);
    }

    /**
     * <p>
     * Deactivates application-specific buttons.
     * </p>
     * @param scrnMsg BMsg
     * @param handler S21CommonHandler
     */
    public static void deactivateApplicationButtons(NPAL1240BMsg scrnMsg, S21CommonHandler handler) {
        deactivateButton(BTN_APP.REF_NUM, handler);
        deactivateButton(BTN_APP.INSERT_ROW, handler);
        deactivateButton(BTN_APP.DELETE_ROW, handler);
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

    public static void setOutputParam(Object[] arg, NPAL1240_RBMsgArray resultArray) {

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < 3) {
            return;
        }

        if (arg[2] instanceof EZDMsgArray) {
            EZDMsg.copy(resultArray, "", (EZDMsgArray) arg[2], "Q");
        }
    }
}
