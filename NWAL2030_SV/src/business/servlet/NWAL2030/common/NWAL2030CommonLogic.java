/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2030.common;

import static business.servlet.NWAL2030.constant.NWAL2030Constant.*;

import java.util.List;

import business.servlet.NWAL2030.NWAL2030BMsg;
import business.servlet.NWAL2030.NWAL2030Bean;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Hold Set Up Screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         K.Kojima        Create          N/A
 * 2016/04/07   Fujitsu         M.suzuki        Update          QC#6369
 *</pre>
 */
public class NWAL2030CommonLogic {

    /**
     * addCheckItemSearch
     * @param scrnMsg NWAL2030BMsg
     */
    public static void addCheckItemSearch(NWAL2030BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.hldRsnCd);
        scrnMsg.addCheckItem(scrnMsg.hldRsnNm);
    }

    /**
     * addCheckItemSubmit
     * @param scrnMsg NWAL2030BMsg
     */
    public static void addCheckItemSubmit(NWAL2030BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(
                new String[] {NWAL2030Bean.hldRsnCd_A, NWAL2030Bean.hldRsnNm_A, NWAL2030Bean.hldRsnDescTxt_A, NWAL2030Bean.hldLvlCd_SV, NWAL2030Bean.ordProcNodeCd_SV, NWAL2030Bean.hldEffFromDt_A, NWAL2030Bean.hldEffToDt_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NFBL2100BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NWAL2030BMsg scrnMsg) {
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Complete Contract. UserID is -> " + profile.getContextUserInfo().getUserId());
        }

        scrnMsg.setInputProtected(false);
        handler.setButtonEnabledAll();
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        //2016/04/04 S21_NA#6369 Start
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        //2016/04/04 S21_NA#6369
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        if (!funcIdList.contains(UPD_FUNC)) {
            handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
            handler.setButtonEnabled(BTN_ADD_LINE, false);
            handler.setButtonEnabled(BTN_DELETE_LINE, false);
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                scrnMsg.A.no(num).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(num).hldRsnCd_A.setInputProtected(true);
                scrnMsg.A.no(num).hldRsnNm_A.setInputProtected(true);
                scrnMsg.A.no(num).hldRsnDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(num).hldLvlCd_SV.setInputProtected(true);
                scrnMsg.A.no(num).ordProcNodeCd_SV.setInputProtected(true);
                scrnMsg.A.no(num).hldEffFromDt_A.setInputProtected(true);
                scrnMsg.A.no(num).hldEffToDt_A.setInputProtected(true);
            }
        } else {
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(num).xxExstFlg_A.getValue())) {
                    scrnMsg.A.no(num).xxChkBox_A.setInputProtected(true);
                    scrnMsg.A.no(num).hldRsnCd_A.setInputProtected(true);
                    scrnMsg.A.no(num).hldLvlCd_SV.setInputProtected(true);
                    scrnMsg.A.no(num).ordProcNodeCd_SV.setInputProtected(true);
                } else {
                    scrnMsg.A.no(num).xxChkBox_A.setInputProtected(false);
                    scrnMsg.A.no(num).hldRsnCd_A.setInputProtected(false);
                    scrnMsg.A.no(num).hldLvlCd_SV.setInputProtected(false);
                    scrnMsg.A.no(num).ordProcNodeCd_SV.setInputProtected(false);
                }
            }
        }

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_DELETE_LINE, false);
        }

        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_ADD_LINE, false);
        }

        S21TableColorController lineTblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        lineTblColor.clearRowsBG("A", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

}
