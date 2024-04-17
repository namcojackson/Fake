/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1250.common;

import static business.servlet.NPAL1250.constant.NPAL1250Constant.BIZ_ID;
import static business.servlet.NPAL1250.constant.NPAL1250Constant.FUCTION_CODE_UPDATE;
import static business.servlet.NPAL1250.constant.NPAL1250Constant.SCRN_ID;
import parts.common.EZDMsg;
import business.blap.NPAL1250.NPAL1250CMsg;
import business.servlet.NPAL1250.NPAL1250BMsg;
import business.servlet.NPAL1250.constant.NPAL1250Constant.BTN_CMN;
import business.servlet.NPAL1250.constant.NPAL1250Constant.BTN_STATUS;
import business.servlet.NPAL1250.constant.NPAL1250Constant.FUNC_CD;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Big Deal Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1250CommonLogic {

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for Search.
     * </p>
     * @param scrnMsg (BMsg)
     * @return bizMsg (CMsg)
     */
    public static NPAL1250CMsg copyScrnMsgToBizMsgForSearch(NPAL1250BMsg scrnMsg) {
        NPAL1250CMsg bizMsg = new NPAL1250CMsg();
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
    public static NPAL1250CMsg copyScrnMsgToBizMsgForUpdate(NPAL1250BMsg scrnMsg) {
        NPAL1250CMsg bizMsg = new NPAL1250CMsg();
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
    public static void setDisplayNameForMessage(NPAL1250BMsg scrnMsg) {

        scrnMsg.sellToCustCd.setNameForMessage("Account Code");
        scrnMsg.dsAcctNm.setNameForMessage("Customer Name");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To Code");
        scrnMsg.locNm.setNameForMessage("Location Name");
        scrnMsg.bigDealNum.setNameForMessage("Big Deal Customer#");

        for (int index = 0; index < scrnMsg.A.length(); index++) {
            scrnMsg.A.no(index).sellToCustCd_A.setNameForMessage("Account Code");
            scrnMsg.A.no(index).dsAcctNm_A.setNameForMessage("Customer Name");
            scrnMsg.A.no(index).shipToCustCd_A.setNameForMessage("Ship To Code");
            scrnMsg.A.no(index).locNm_A.setNameForMessage("Location Name");
            scrnMsg.A.no(index).bigDealNum_A.setNameForMessage("Big Deal Customer#");
        }
    }

    /**
     * <p>
     * Initializes the button properties.
     * </p>
     * @param handler S21CommonHandler
     */
    public static void initializeButtonProperties(S21CommonHandler handler) {

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
            // Common.
            activateButton(BTN_CMN.SUBMIT, handler);
        }
    }

    /**
     * <p>
     * Disable Edit button properties.
     * </p>
     * @param handler S21CommonHandler
     */
    public static void disableEditButtonProperties(S21CommonHandler handler) {

        // Common.
        deactivateButton(BTN_CMN.SUBMIT, handler);
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

    public static void setInputProtectedForDetail(NPAL1250BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.A.no(index).sellToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(index).dsAcctNm_A.setInputProtected(true);
            scrnMsg.A.no(index).shipToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(index).locNm_A.setInputProtected(true);

            if (!isEntryGranted()) {
                scrnMsg.A.no(index).bigDealNum_A.setInputProtected(true);
            }
        }
    }
}
