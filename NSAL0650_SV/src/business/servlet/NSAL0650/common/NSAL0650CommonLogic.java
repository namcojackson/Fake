/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0650.common;

import static business.servlet.NSAL0650.constant.NSAL0650Constant.SCREEN_ID;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0650.NSAL0650BMsg;
import business.servlet.NSAL0650.constant.NSAL0650Constant.BTN_LBL;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Update Auto Estimation Round
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 * 2016/12/02   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/13   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0650CommonLogic {

    /**
     * addCheckItem
     * @param scrnMsg NSAL0650BMsg
     */
    public static void addCheckItem(NSAL0650BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mtrEstTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrEstTpCd_AH);
        }
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0650BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0650BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0650BMsg
     */
    // mod start 2016/12/02 CSA QC#4210
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0650BMsg scrnMsg) {
    // mod end 2016/12/02 CSA QC#4210
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0650BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0650BMsg scrnMsg) {
        scrnMsg.mtrEstTpCd_H.setInputProtected(false);
        scrnMsg.svcMemoRsnCd_H.setInputProtected(false);
        scrnMsg.svcCmntTxt_H.setInputProtected(false);
        scrnMsg.xxChkBox_AL.setInputProtected(false);

    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0650BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0650BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // mod start 2016/12/02 CSA QC#4210
//            if (hasValue(scrnMsg.A.no(i).dsMsgTxt_A1)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
            // mod end 2016/12/02 CSA QC#4210
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).mtrEstTpCd_AH.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).mtrEstTpCd_AH.setInputProtected(false);
            }
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mtrEstTpNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsMsgTxt_A1.setInputProtected(true);
        }
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0650BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0650BMsg scrnMsg) {
        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
    // START 2017/02/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 1, null);
    // END   2017/02/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0650BMsg
     */
    private static void setRowColors(NSAL0650BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
