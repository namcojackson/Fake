/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0870.common;

import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_APPLY;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_APPROVE;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_CLEAR;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_DELETE;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_DOWNROAD;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_REJECT;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_RESET;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_SAVE;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.SCREEN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0870.NSAL0870BMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 * 2017/01/23   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0870CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0870BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0870BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0870BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0870BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        // START 2017/01/23 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        // END 2017/01/23 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0870BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0870BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);
    }

    /**
     * control Inactive Field
     * @param scrnMsg NSAL0870BMsg
     */
    public static final void protectFields(NSAL0870BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A.setInputProtected(true);
            scrnMsg.A.no(i).svcContrBrDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrReadSrcTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrReadDt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_A2.setInputProtected(true);
            scrnMsg.A.no(i).mtrCnt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsMsgTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsMtrProcStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsMtrProcTs_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsMtrProcTs_A2.setInputProtected(true);
            scrnMsg.A.no(i).rgtnUsrId_A.setInputProtected(true);
        }
    }

    /**
     * set Row Colors
     * @param scrnMsg NSAL0870BMsg
     */
    public static void setRowColors(NSAL0870BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }
}
