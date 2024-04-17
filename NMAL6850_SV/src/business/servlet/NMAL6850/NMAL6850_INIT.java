/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850;

import static business.servlet.NMAL6850.common.NMAL6850CommonLogic.activateButton;
import static business.servlet.NMAL6850.common.NMAL6850CommonLogic.deactivateButton;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.BIZ_ID;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.FUCTION_CODE_UPDATE;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6850.NMAL6850CMsg;
import business.servlet.NMAL6850.common.NMAL6850CommonLogic;
import business.servlet.NMAL6850.constant.NMAL6850Constant.BTN_APP;
import business.servlet.NMAL6850.constant.NMAL6850Constant.BTN_CMN;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6850 Supplier Search
 * Function Name : The business process for Initialization.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/23   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NMAL6850_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;

        // resets the paging view.
        NMAL6850CommonLogic.resetPegingView(scrnMsg);

        // copies scrnMsg to bizMsg.
        NMAL6850CMsg bixMsg = NMAL6850CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);

        return bixMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;
        EZDMsg.copy((NMAL6850CMsg) cMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        // set button properties.
        initializeButtonProperties();

        // focus on Supplier Number.
        scrnMsg.setFocusItem(scrnMsg.prntVndCd_H);
    }

    /**
     * Initializes the button properties.
     */
    private void initializeButtonProperties() {

        // Application specific.
        activateButton(BTN_APP.SEARCH, this);

        // if If entry is granted, "New" button is enabled.
        if (isEntryGranted()) {
            activateButton(BTN_APP.NEW, this);
        } else {
            deactivateButton(BTN_APP.NEW, this);
        }

        // Common.
        deactivateButton(BTN_CMN.SAVE, this);
        deactivateButton(BTN_CMN.SUBMIT, this);
        deactivateButton(BTN_CMN.APPLY, this);
        deactivateButton(BTN_CMN.APPROVE, this);
        deactivateButton(BTN_CMN.REJECT, this);
        deactivateButton(BTN_CMN.DOWNLOAD, this);
        deactivateButton(BTN_CMN.DELETE, this);
        deactivateButton(BTN_CMN.CLEAR, this);
        activateButton(BTN_CMN.RESET, this);
        activateButton(BTN_CMN.RETURN, this);
    }

    /***
     * <p>
     * Checks if entry is granted.
     * </p>
     * @return If entry is granted, return true.
     */
    private boolean isEntryGranted() {
        return getUserProfileService().isFunctionGranted(getContextUserInfo().getUserId(), FUCTION_CODE_UPDATE);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL6850CommonLogic.setNameForMessage((NMAL6850BMsg) bMsg);
    }
}
