/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFDL0150.common;

import static business.servlet.NFDL0150.constant.NFDL0150Constant.*;

import java.util.List;

import business.servlet.NFDL0150.NFDL0150BMsg;
import business.servlet.NFDL0150.NFDL0150Bean;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/04   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#5358
 *</pre>
 */
public class NFDL0150CommonLogic {

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NFDL0150BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NFDL0150BMsg scrnMsg) {
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

        if (!funcIdList.contains(UPD_FUNC)) {
            handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                scrnMsg.A.no(num).cltStrgyCd_A.setInputProtected(true);
                scrnMsg.A.no(num).cltStrgyNm_A.setInputProtected(true);
            }
        } else {
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                if (XX_TP_CD_UPD.equals(scrnMsg.A.no(num).xxTpCd_A.getValue())) {
                    scrnMsg.A.no(num).cltStrgyCd_A.setInputProtected(true);
                } else {
                    scrnMsg.A.no(num).cltStrgyCd_A.setInputProtected(false);
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
            handler.setButtonEnabled(BTN_WORK_ITEM, false);
            handler.setButtonEnabled(BNT_CUSTOMER_TYPE, false);
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
    public static void commonAddCheckItem(NFDL0150BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NFDL0150Bean.cltStrgyCd_A, NFDL0150Bean.cltStrgyNm_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

}
