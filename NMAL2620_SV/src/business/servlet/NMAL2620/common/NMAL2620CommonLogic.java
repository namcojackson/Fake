/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NMAL2620.common;

import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_APPLY;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_APPROVE;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_CLEAR;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_DELETE;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_DOWNROAD;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_REJECT;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_RESET;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_RETURN;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_SAVE;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.BTN_CMN_SUBMIT;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.SCREEN_ID;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.SERVICE_MODE_01;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.SERVICE_MODE_02;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2620.NMAL2620BMsg;
import business.servlet.NMAL2620.constant.NMAL2620Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Territory Mass Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/27   Fujitsu         C.Yokoi         Update          N/A
 *</pre>
 */
public class NMAL2620CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2620BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2620BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NMAL2620BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NMAL2620BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2620BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2620BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);
        scrnMsg.psnNum_D.setInputProtected(true);
        protectControlDetail(handler, scrnMsg);
    }

    /**
     * Protect control detail
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2620BMsg
     */
    public static final void protectControlDetail(EZDCommonHandler handler, NMAL2620BMsg scrnMsg) {
        // 2016/04/27 Add Start
        if (SERVICE_MODE_01.equals(scrnMsg.trtyUpdModeTpCd_V.getValue())) {
            handler.setButtonEnabled(NMAL2620Constant.BTN_GET_PSN_NM, true);
            scrnMsg.psnNum_LK.setInputProtected(false);
            scrnMsg.psnNum_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxPsnNm_D.setInputProtected(false);
            scrnMsg.xxFromDt.setInputProtected(false);
            scrnMsg.xxThruDt.setInputProtected(false);
            scrnMsg.endDt.setInputProtected(true);
            scrnMsg.endDt.clear();
        } else if (SERVICE_MODE_02.equals(scrnMsg.trtyUpdModeTpCd_V.getValue())) {
            handler.setButtonEnabled(NMAL2620Constant.BTN_GET_PSN_NM, false);
            scrnMsg.psnNum_LK.setInputProtected(true);
            scrnMsg.psnNum_LK.clear();
            scrnMsg.xxPsnNm_D.setInputProtected(true);
            scrnMsg.xxFromDt.setInputProtected(true);
            scrnMsg.xxThruDt.setInputProtected(true);
            scrnMsg.endDt.setInputProtected(false);
            scrnMsg.endDt.setInputProtected(false);
            scrnMsg.xxPsnNm_D.clear();
            scrnMsg.psnNum_D.clear();
            scrnMsg.xxFromDt.clear();
            scrnMsg.xxThruDt.clear();
        } else {
            handler.setButtonEnabled(NMAL2620Constant.BTN_GET_PSN_NM, true);
            scrnMsg.psnNum_LK.setInputProtected(false);
            scrnMsg.psnNum_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxPsnNm_D.setInputProtected(false);
            scrnMsg.xxFromDt.setInputProtected(false);
            scrnMsg.xxThruDt.setInputProtected(false);
            scrnMsg.endDt.setInputProtected(false);
        }
        // 2016/04/27 Add End

    }

    /**
     * set Row Colors
     * @param scrnMsg NMAL2620BMsg
     */
    public static void setRowColors(NMAL2620BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * add Check all
     * @param scrnMsg NMAL2620BMsg
     */
    public static void addCheckAll(NMAL2620BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * protectSearchResult
     * @param scrnMsg NMAL2620BMsg
     */
    public static void protectSearchResult(NMAL2620BMsg scrnMsg) {
        // 2016/04/27 Add Start
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).orgNm_A.setInputProtected(true);
            scrnMsg.A.no(i).orgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).bizAreaOrgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).lineBizTpCd_A.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).psnNum_A.setInputProtected(true);
            scrnMsg.A.no(i).orgFuncRoleTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).acctTeamRoleTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
        }
        // 2016/04/27 Add End
    }

}
