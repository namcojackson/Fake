/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1180.common;

import static business.servlet.NSAL1180.constant.NSAL1180Constant.*;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1180.NSAL1180BMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         K.Yamada        Create          N/A
 * 2016/02/24   Hitachi         K.Kasai         Update          QC#903
 *</pre>
 */
public class NSAL1180CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1180BMsg
     */
    public static void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL1180BMsg scrnMsg) {

        // mod start 2016/02/24 CSA Defect#903
        handler.setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
        // mod end 2016/02/24 CSA Defect#903
        handler.setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_CLOSE, BUTTON_LABEL_CLOSE, 1, null);

        // set protect
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxRecNmTxt_HI.setInputProtected(true);
        }

        // set BG color
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.A);
        tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.A, 1);
    }

}
