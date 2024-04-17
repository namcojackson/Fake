/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1260.common;

import static business.servlet.NSAL1260.constant.NSAL1260Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1260.NSAL1260BMsg;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1260CommonLogic {

    /**
     * initialize
     * @param handler EZDCommonHandler
     */
    public static void initialize(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * screenItemControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1260BMsg
     */
    public static void screenItemControl(EZDCommonHandler handler, NSAL1260BMsg scrnMsg) {

        controlProcess(handler, scrnMsg);
        setAppFracDigit(scrnMsg);
        setTableBGColor(scrnMsg);
    }

    /**
     * addCheckItemDetail
     * @param scrnMsg NSAL1260BMsg
     */
    public static void addCheckItemDetail(NSAL1260BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcMachMstrPk_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgCycleCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).basePrcDealAmt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrReadMethCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).billToAcctNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrVrsnEffFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrVrsnEffThruDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AB);
        }
    }

    /**
     * addCheckItemDetailForUpdate
     * @param scrnMsg NSAL1260BMsg
     */
    public static void addCheckItemDetailForUpdate(NSAL1260BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxErrFlg_A.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).basePrcDealAmt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrVrsnEffFromDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrVrsnEffThruDt_A);
            }
        }
    }

    private static final void controlProcess(EZDCommonHandler handler, NSAL1260BMsg scrnMsg) {

        scrnMsg.dsAcctNum_H.setInputProtected(true);
        scrnMsg.dsAcctNm_H.setInputProtected(true);
        scrnMsg.contrVrsnEffFromDt_H.setInputProtected(true);
        scrnMsg.contrVrsnEffThruDt_H.setInputProtected(true);
        scrnMsg.bllgCycleDescTxt_H.setInputProtected(true);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxErrFlg.getValue())) {
            handler.setButtonEnabled(BTN_UPLOAD, false);
            handler.setButtonEnabled(BTN_ADD_MACHINE, false);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).billToAcctNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A.setInputProtected(true);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxErrFlg_A.getValue())) {
                scrnMsg.A.no(i).xxChkBox_AA.setInputProtected(true);
                scrnMsg.A.no(i).bllgCycleCd_A.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAmt_A.setInputProtected(true);
                scrnMsg.A.no(i).mtrReadMethCd_A.setInputProtected(true);
                scrnMsg.A.no(i).contrVrsnEffFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).contrVrsnEffThruDt_A.setInputProtected(true);
                scrnMsg.A.no(i).xxChkBox_AB.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_AA.setInputProtected(false);
                scrnMsg.A.no(i).bllgCycleCd_A.setInputProtected(false);
                scrnMsg.A.no(i).basePrcDealAmt_A.setInputProtected(false);
                scrnMsg.A.no(i).mtrReadMethCd_A.setInputProtected(false);
                scrnMsg.A.no(i).contrVrsnEffFromDt_A.setInputProtected(false);
                scrnMsg.A.no(i).contrVrsnEffThruDt_A.setInputProtected(false);
                scrnMsg.A.no(i).xxChkBox_AB.setInputProtected(false);
            }
        }
    }

    private static void setAppFracDigit(NSAL1260BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).basePrcDealAmt_A.setAppFracDigit(DEF_FRAC_DIGIT_NUM);
        }
    }

    private static void setTableBGColor(NSAL1260BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
        }
    }
}
