/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3110.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3110.NLBL3110BMsg;
import business.servlet.NLBL3110.constant.NLBL3110Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 *</pre>
 */
public class NLBL3110CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3110BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3110BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(NLBL3110Constant.SCREEN_ID);

        controlScreen(userProfileService, handler, scrnMsg);
    }

    /**
     * controlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3110BMsg
     */
    public static final void controlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3110BMsg scrnMsg) {

        initCommonButton(userProfileService, handler);
        controlScreenFields(userProfileService, handler, scrnMsg);
    }

    /**
     * initCommonButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {

        handler.setButtonProperties(NLBL3110Constant.BTN_CMN_CLEAR[0], NLBL3110Constant.BTN_CMN_CLEAR[1], NLBL3110Constant.BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(NLBL3110Constant.BTN_CMN_CLOSE[0], NLBL3110Constant.BTN_CMN_CLOSE[1], NLBL3110Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3110BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3110BMsg scrnMsg) {

        // Header
        scrnMsg.setInputProtected(true);

        // Detail
        scrnMsg.A.setInputProtected(true);

        if (0 < scrnMsg.A.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLBL3110Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }

        addCheckItemHeader(scrnMsg);
    }

    /**
     * AddCheckItemHeader
     * @param scrnMsg NLBL3110BMsg
     */
    public static void addCheckItemHeader(NLBL3110BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.cpoNum_H1);
        scrnMsg.addCheckItem(scrnMsg.trxHdrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.trxLineNum_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_H1);
    }
}