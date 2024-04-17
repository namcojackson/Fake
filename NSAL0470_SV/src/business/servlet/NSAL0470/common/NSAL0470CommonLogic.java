/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0470.common;

import java.util.List;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NSAL0470.NSAL0470BMsg;
import business.servlet.NSAL0470.NSAL0470_ABMsg;
import static business.servlet.NSAL0470.constant.NSAL0470Constant.*;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;

/**
 *<pre>
 * Complete Contract
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Yamada        Create          N/A
 * 2016/02/12   Hitachi         T.Aoyagi        Update          QC3274
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3050
 * 2016/04/20   Hitachi         K.Kishimoto     Update          QC5130
 * 2016/04/21   Hitachi         A.Kohinata      Update          QC1088
 *</pre>
 */
public class NSAL0470CommonLogic {

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0470BMsg
     */
    public static void initialize(EZDCommonHandler handler, NSAL0470BMsg scrnMsg) {

        // set button property
        // START 2016/02/15 T.Aoyagi [QC3050, MOD]
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        // Mod Start 04/20/2016 <QC#5130>
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // Mod End 04/20/2016 <QC#5130>
        handler.setButtonProperties(BTN_FIX_CONTRACT[0], BTN_FIX_CONTRACT[1], BTN_FIX_CONTRACT[2], 1, null);
        handler.setButtonProperties(BTN_SEND_SUPERVISOR[0], BTN_SEND_SUPERVISOR[1], BTN_SEND_SUPERVISOR[2], 1, null);
        handler.setButtonProperties(BTN_OVERRIDE_OUTCOME[0], BTN_OVERRIDE_OUTCOME[1], BTN_OVERRIDE_OUTCOME[2], 1, null);
        handler.setButtonProperties(BTN_ACTIVATE_CONTRACT[0], BTN_ACTIVATE_CONTRACT[1], BTN_ACTIVATE_CONTRACT[2], 1, null);
        // END 2016/02/15 T.Aoyagi [QC3050, MOD]

    }

    /**
     * screenItemControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0470BMsg
     */
    public static void screenItemControl(EZDCommonHandler handler, NSAL0470BMsg scrnMsg) {

        controlProcess(handler, scrnMsg);
        setTableBGColor(scrnMsg);
        setOutComeBGColor(scrnMsg);

    }

    private static void controlProcess(EZDCommonHandler handler, NSAL0470BMsg scrnMsg) {

        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Complete Contract. UserID is -> " + profile.getContextUserInfo().getUserId());
        }

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_SEND_SUPERVISOR[0], false);
            handler.setButtonEnabled(BTN_OVERRIDE_OUTCOME[0], false);
            handler.setButtonEnabled(BTN_CMN_DOWNLOAD[0], false);
            // START 2016/02/15 T.Aoyagi [QC3050, MOD]
            handler.setButtonEnabled(BTN_ACTIVATE_CONTRACT[0], false);
            // END 2016/02/15 T.Aoyagi [QC3050, MOD]
        } else {
            handler.setButtonEnabled(BTN_CMN_DOWNLOAD[0], true);
            // START 2016/04/21 [QC1088, MOD]
            if (FLG_ON_Y.equals(scrnMsg.xxErrFlg.getValue())) {
                handler.setButtonEnabled(BTN_FIX_CONTRACT[0], true);
                handler.setButtonEnabled(BTN_SEND_SUPERVISOR[0], true);
                handler.setButtonEnabled(BTN_OVERRIDE_OUTCOME[0], true);
                handler.setButtonEnabled(BTN_ACTIVATE_CONTRACT[0], false);
            } else {
                handler.setButtonEnabled(BTN_FIX_CONTRACT[0], false);
                handler.setButtonEnabled(BTN_SEND_SUPERVISOR[0], false);
                handler.setButtonEnabled(BTN_OVERRIDE_OUTCOME[0], false);
                handler.setButtonEnabled(BTN_ACTIVATE_CONTRACT[0], true);
            }
            // END 2016/04/21 [QC1088, MOD]
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrVldNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrVldStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrVldRsltMsgTxt_A.setInputProtected(true);
        }

        // START 2016/04/21 [QC1088, MOD]
        if (funcIdList.contains(SUPERVISOR_FUNC)) {
            handler.setButtonEnabled(BTN_SEND_SUPERVISOR[0], false);
        }

        if (!funcIdList.contains(SUPERVISOR_FUNC)) {
            handler.setButtonEnabled(BTN_OVERRIDE_OUTCOME[0], false);
        }

        if (!funcIdList.contains(SUPERVISOR_FUNC) && !funcIdList.contains(UPD_FUNC)) {
            handler.setButtonEnabled(BTN_SEND_SUPERVISOR[0], false);
            handler.setButtonEnabled(BTN_ACTIVATE_CONTRACT[0], false);
        }

        if (FLG_ON_Y.equals(scrnMsg.qltyAsrnHldPendApvlFlg.getValue())) {
            handler.setButtonEnabled(BTN_SEND_SUPERVISOR[0], false);
            handler.setButtonEnabled(BTN_OVERRIDE_OUTCOME[0], false);
            handler.setButtonEnabled(BTN_ACTIVATE_CONTRACT[0], false);
        }
        // END 2016/04/21 [QC1088, MOD]
    }

    private static void setTableBGColor(NSAL0470BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
            // START 2016/02/12 T.Aoyagi [QC3274, ADD]
            tblColor.clearRowsBG("A2", scrnMsg.A);
            tblColor.setAlternateRowsBG("A2", scrnMsg.A);
            // END 2016/02/12 T.Aoyagi [QC3274, ADD]
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
            // START 2016/02/12 T.Aoyagi [QC3274, ADD]
            tblColor.clearRowsBG("A2", scrnMsg.A);
            // END 2016/02/12 T.Aoyagi [QC3274, ADD]
        }
    }

    private static void setOutComeBGColor(NSAL0470BMsg scrnMsg) {

        // clear current gui attribute
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.clearGUIAttribute(SCREEN_ID, "dsContrVldStsDescTxt_A#" + i);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0470_ABMsg line = scrnMsg.A.no(i);
            EZDGUIAttribute attr = new EZDGUIAttribute(SCREEN_ID, "dsContrVldStsDescTxt_A#" + i);

            if (DS_CONTR_VLD_STS.ERROR.equals(line.dsContrVldStsCd_A.getValue())) {
                attr.setAttribute("className", "pErr");
            } else if (DS_CONTR_VLD_STS.WARNING.equals(line.dsContrVldStsCd_A.getValue())) {
                attr.setAttribute("className", "pWar");
            }
            scrnMsg.addGUIAttribute(attr);
        }

    }

}
