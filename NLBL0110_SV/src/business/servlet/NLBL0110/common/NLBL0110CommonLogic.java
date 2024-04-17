package business.servlet.NLBL0110.common;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL0110.NLBL0110BMsg;
import business.servlet.NLBL0110.constant.NLBL0110Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * This common logic used in the screen application
 *  program of BusinessID NLBL0110. 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/02/04   Fujitsu         S.Uehara        Create          N/A
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0110CommonLogic implements NLBL0110Constant {

    /**
     * The display control of the screen item.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0110BMsg
     */
    public static void cntrlDispScrnItem(EZDCommonHandler handler, NLBL0110BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        boolean referenceFunctionExistFlg = false;
        boolean updateFunctionExistFlg = false;

        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {

            if (FUNCTION_REFERENCE.equals((String) functionList.get(i))) {
                referenceFunctionExistFlg = true;
            }

            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {
                updateFunctionExistFlg = true;
            }
        }

        if (!referenceFunctionExistFlg && !updateFunctionExistFlg) {

            throw new S21AbendException("The user doesn't have functionID for NLBL0110.");
        }

        if (updateFunctionExistFlg) {

            cntrlDispScrnItemForUpdateFunction(handler, scrnMsg);

        } else {

            cntrlDispScrnItemForReferenceFunction(handler, scrnMsg);
        }

    }

    /**
     * The display control of the screen item when the user has Function[Update].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0110BMsg
     */
    private static void cntrlDispScrnItemForUpdateFunction(EZDCommonHandler handler, NLBL0110BMsg scrnMsg) {

        // 2013/05/23 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_H2.setInputProtected(true);
        scrnMsg.whCd_HL.setInputProtected(false);
        // 2013/05/23 R-OM025 Inventory Transaction Add Start

        if (scrnMsg.A.getValidCount() == 0) {

            handler.setButtonEnabled(HTML_NAME_VALUE_BTN_VIEW, false);

            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);

        } else {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.A.no(i).endMthBizDaysAot_A1.setInputProtected(false);
            }

            if (scrnMsg.A.getValidCount() == 0) {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_VIEW, false);

            } else {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_VIEW, true);
            }

            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        }

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * The display control of the screen item when the user has Function[Refernce].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0110BMsg
     */
    private static void cntrlDispScrnItemForReferenceFunction(EZDCommonHandler handler, NLBL0110BMsg scrnMsg) {

        // 2013/05/23 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_H2.setInputProtected(true);
        scrnMsg.whCd_HL.setInputProtected(false);
        // 2013/05/23 R-OM025 Inventory Transaction Add Start

        if (scrnMsg.A.getValidCount() == 0) {

            handler.setButtonEnabled(HTML_NAME_VALUE_BTN_VIEW, false);

            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);

        } else {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.A.no(i).endMthBizDaysAot_A1.setInputProtected(true);
            }

            if (scrnMsg.A.getValidCount() == 0) {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_VIEW, false);

            } else {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_VIEW, true);
            }
        }

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    public static final void setTblBackColor(NLBL0110BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        tblColor.setAlternateRowsBG(TBL_ID_LEFT, scrnMsg.A);
        tblColor.setAlternateRowsBG(TBL_ID_RIGHT, scrnMsg.B);
        
    }
}
