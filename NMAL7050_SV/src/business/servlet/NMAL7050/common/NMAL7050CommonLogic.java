/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050.common;

import static business.servlet.NMAL7050.constant.NMAL7050Constant.NMAM0185E;
import static business.servlet.NMAL7050.constant.NMAL7050Constant.NMAM0192E;
import static business.servlet.NMAL7050.constant.NMAL7050Constant.ZZM9037E;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7050.NMAL7050BMsg;
import business.servlet.NMAL7050.constant.NMAL7050Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         W.Honda         Create          N/A
 * 2016/09/09   Hitachi         T.Mizuki        Update          QC#13816
 * 2017/02/15   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7050CommonLogic {
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * 
     * @param handler EZDCommonHandler
     * @param scrnMsg DSAL0040BMsg
     * @param userId String
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL7050BMsg scrnMsg, String userId) {
        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(NMAL7050Constant.SCREEN_ID);

        controlScreenFields(handler, scrnMsg);
        // Mod Start 2017/02/15 QC#17529
//        initCommonButton(handler);
        initCommonButton(handler, scrnMsg);
        // Mod End 2017/02/15 QC#17529
        initButton(handler, scrnMsg);
    }

    /**
     * Control screen fields
     * 
     * @param handler EZDCommonHandler
     * @param scrnMsg DSAL0040BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL7050BMsg scrnMsg) {
        scrnMsg.xxLinkAncr_ML.setInputProtected(false);
        scrnMsg.xxDsMultMsgDplyTxt.setInputProtected(false);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlNm_A1)) {
                scrnMsg.A.no(i).xxLinkAncr_AM.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).xxLinkAncr_AM.setInputProtected(true);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcMtrPkgNm_A1)) {
                scrnMsg.A.no(i).xxLinkAncr_AM.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).xxLinkAncr_AM.setInputProtected(true);
            }
            scrnMsg.A.no(i).prcMtrPkgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
        }
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init Common
     * Button Control. <dd>Remarks:
     * 
     * @param handler EZ Common Handler
     * @param scrnMsg NMAL7050BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NMAL7050BMsg scrnMsg) {
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_SAVE[0], NMAL7050Constant.BTN_CMN_SAVE[1], NMAL7050Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_SUBMIT[0], NMAL7050Constant.BTN_CMN_SUBMIT[1], NMAL7050Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_APPLY[0], NMAL7050Constant.BTN_CMN_APPLY[1], NMAL7050Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_APPROVE[0], NMAL7050Constant.BTN_CMN_APPROVE[1], NMAL7050Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_REJECT[0], NMAL7050Constant.BTN_CMN_REJECT[1], NMAL7050Constant.BTN_CMN_REJECT[2], 0, null);
        // Mod Start 2017/02/15 QC#17529
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(NMAL7050Constant.BTN_CMN_DOWNLOAD[0], NMAL7050Constant.BTN_CMN_DOWNLOAD[1], NMAL7050Constant.BTN_CMN_DOWNLOAD[2], 1, null);
        } else {
            handler.setButtonProperties(NMAL7050Constant.BTN_CMN_DOWNLOAD[0], NMAL7050Constant.BTN_CMN_DOWNLOAD[1], NMAL7050Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        }
        // Mod End 2017/02/15 QC#17529
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_DETELE[0], NMAL7050Constant.BTN_CMN_DETELE[1], NMAL7050Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_CLEAR[0], NMAL7050Constant.BTN_CMN_CLEAR[1], NMAL7050Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_RESET[0], NMAL7050Constant.BTN_CMN_RESET[1], NMAL7050Constant.BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CMN_RETURN[0], NMAL7050Constant.BTN_CMN_RETURN[1], NMAL7050Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Init Buttons
     * 
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7050BMsg
     */
    public static final void initButton(EZDCommonHandler handler, NMAL7050BMsg scrnMsg) {
        handler.setButtonProperties(NMAL7050Constant.BTN_SEARCH[0], NMAL7050Constant.BTN_SEARCH[1], NMAL7050Constant.BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(NMAL7050Constant.BTN_CREATE_NEW[0], NMAL7050Constant.BTN_CREATE_NEW[1], NMAL7050Constant.BTN_CREATE_NEW[2], 1, null);
    }

    /**
     * Check Input Item For Search
     * @param scrnMsg DSAL0040BMsg
     */
    public static void checkInputSearch(NMAL7050BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxDsMultMsgDplyTxt)
                && !ZYPCommonFunc.hasValue(scrnMsg.mtrLbDescTxt_BG)
                && !ZYPCommonFunc.hasValue(scrnMsg.mtrLbDescTxt_PH)
                && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {
            scrnMsg.prcMtrPkgNm.setErrorInfo(1, NMAM0192E);
            scrnMsg.xxDsMultMsgDplyTxt.setErrorInfo(1, NMAM0192E);
            scrnMsg.mtrLbDescTxt_BG.setErrorInfo(1, NMAM0192E);
            scrnMsg.mtrLbDescTxt_PH.setErrorInfo(1, NMAM0192E);
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.effThruDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.setMessageInfo(ZZM9037E);
        }

        addCheckItemHeader(scrnMsg);
        // mod start 2016/09/09 CSA QC#13816
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {
            if (ZYPDateUtil.compare(scrnMsg.effFromDt.getValue(), scrnMsg.effThruDt
                    .getValue()) > 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NMAM0185E);
                scrnMsg.effThruDt.setErrorInfo(1, NMAM0185E);
            }
        }
        // mod end 2016/09/09 CSA QC#13816
        scrnMsg.putErrorScreen();
    }

    /**
     * Check Input Item Header
     * @param scrnMsg NMAL7050BMsg
     */
    public static void addCheckItemHeader(NMAL7050BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt);
        scrnMsg.addCheckItem(scrnMsg.mtrLbDescTxt_BG);
        scrnMsg.addCheckItem(scrnMsg.mtrLbDescTxt_PH);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
    }
}
