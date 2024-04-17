/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFDL0120.common;

import static business.servlet.NFDL0120.constant.NFDL0120Constant.*;

import java.util.List;

import business.servlet.NFDL0120.NFDL0120BMsg;
import business.servlet.NFDL0120.NFDL0120Bean;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#5358
 * 2016/07/19   Hitachi         K.Kojima        Update          QC#10188
 *</pre>
 */
public class NFDL0120CommonLogic {

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NFDL0120BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NFDL0120BMsg scrnMsg) {
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
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        scrnMsg.cltStrgyCd.setInputProtected(true);
        scrnMsg.cltStrgyNm.setInputProtected(true);

        if (!funcIdList.contains(UPD_FUNC)) {
            handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                scrnMsg.A.no(num).cltWrkItemCd_SV.setInputProtected(true);
            }
        } else {
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                if (XX_TP_CD_UPD.equals(scrnMsg.A.no(num).xxTpCd_A.getValue())) {
                    scrnMsg.A.no(num).cltWrkItemCd_SV.setInputProtected(true);
                } else {
                    scrnMsg.A.no(num).cltWrkItemCd_SV.setInputProtected(false);
                }
            }
        }

        // START 2016/03/11 K.Kojima [QC#5358,ADD]
        if (scrnMsg.A.getValidCount() == 0 && scrnMsg.D.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
        }
        // END 2016/03/11 K.Kojima [QC#5358,ADD]

        if (scrnMsg.A.getValidCount() == 0) {
            // START 2016/03/11 K.Kojima [QC#5358,DEL]
            // handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
            // END 2016/03/11 K.Kojima [QC#5358,DEL]
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
        }

        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
        }

        S21TableColorController lineTblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        lineTblColor.clearRowsBG("A", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1170BMsg
     */
    public static void commonAddCheckItem(NFDL0120BMsg scrnMsg) {
        // START 2016/07/19 K.Kojima [QC#10188,MOD]
        // scrnMsg.A.setCheckParam(new String[]
        // {NFDL0120Bean.cltWrkItemCd_SV }, 1);
        scrnMsg.A.setCheckParam(new String[] {NFDL0120Bean.cltWrkItemCd_SV, NFDL0120Bean.cltWrkItemSortNum_A }, 1);
        // END 2016/07/19 K.Kojima [QC#10188,MOD]
        scrnMsg.addCheckItem(scrnMsg.A);
    }

}
