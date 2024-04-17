/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0320.common;


import static business.servlet.NSBL0320.constant.NSBL0320Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0320.NSBL0320BMsg;
import business.servlet.NSBL0320.NSBL0320Bean;

/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         Y.Osawa         Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          CSA QC#5765
 *</pre>
 */
public class NSBL0320CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0320BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0320BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSBL0420BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSBL0320BMsg scrnMsg) {
        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

        if (scrnMsg.A.getValidCount() <= 0) {
            handler.setButtonEnabled(BTN_SELECT_ALL, false);
            handler.setButtonEnabled(BTN_UNSELECT_ALL, false);
            handler.setButtonEnabled(BTN_SEARCH, false);
        } else {
            handler.setButtonEnabled(BTN_SELECT_ALL, true);
            handler.setButtonEnabled(BTN_UNSELECT_ALL, true);
            handler.setButtonEnabled(BTN_SEARCH, true);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0320BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSBL0320BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
            scrnMsg.A.no(i).xxGenlFldAreaTxt.setInputProtected(true);
        }
    }

    // add start 2016/03/22 CSA Defect#5765
    /**
     * To ON the check box of all lines.
     * @param scrnMsg
     */
    public static final void selectAll(NSBL0320BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            ZYPTableUtil.selectAll(scrnMsg.A, NSBL0320Bean.xxChkBox, ZYPConstant.FLG_ON_Y);
        }
    }
    // add end 2016/03/22 CSA Defect#5765
}
