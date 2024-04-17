/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1000.common;

import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL1000.NLCL1000BMsg;
import business.servlet.NLCL1000.constant.NLCL1000Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 * 2016/05/20   CSAI            K.Lee           Update          QC#7441
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 * 2018/07/12   Fujitsu         Hd.Sugawara     Update          QC#21076
 *</pre>
 */
public class NLCL1000CommonLogic implements NLCL1000Constant {

    /**
     * <pre>
     * Set F8:Clear Button and F9:Close Button
     * 
     * </pre>
     * 
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * <pre>
     * Enable Search Button
     * 
     * </pre>
     * 
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_SEARCH[0], true);
    }

    /**
     * <pre>
     * Set Screen display
     * Delete Sort icon
     * Set Detail Line color
     * Set Focus
     * 
     * </pre>
     * 
     * @param scrnMsg NMAL6050BMsg
     * @param baseContents String[][]
     */
    public static void initScrn(NLCL1000BMsg scrnMsg, String[][] baseContents) {

        // Delete Sort icon
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);

        // Set Detail Line color
        NLCL1000CommonLogic.clearGUIAttribute(scrnMsg);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
                if (scrnMsg.A.no(i).xxScrItem20Txt_A1.getValue().equals(scrnMsg.P.no(j).xxScrItem20Txt_P1.getValue())
                &&  scrnMsg.A.no(i).mdseCd_A1.getValue().equals(scrnMsg.P.no(j).mdseCd_P1.getValue())
                &&  scrnMsg.A.no(i).invtyLocCd_A1.getValue().equals(scrnMsg.P.no(j).invtyLocCd_P1.getValue())
                &&  scrnMsg.A.no(i).ordQty_A1.getValue().equals(scrnMsg.P.no(j).ordQty_P1.getValue())
                ) {
                    setHtmlColor(scrnMsg, SCREEN_ID, "A_TR_"+i, HTML_COLOR_YELLOW);
                }
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_AS.setInputProtected(true);
            scrnMsg.A.no(i).whNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).ordQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem10Txt_A1.setInputProtected(true);
        }
        // Set Focus
        // Mod Start 2018/07/12 QC#21076
        //scrnMsg.setFocusItem(scrnMsg.xxChkBox_AL);
        scrnMsg.setFocusItem(scrnMsg.xxChkBox_DS);
        // Mod End 2018/07/12 QC#21076
    }

    /**
     * @param scrnMsg
     * @param screenId
     * @param htmlId
     * @param color
     */
    public static void setHtmlColor(NLCL1000BMsg scrnMsg, String screenId, String htmlId, String color) {
        EZDGUIAttribute setColorAttribute = null;
        setColorAttribute = new EZDGUIAttribute(screenId, htmlId);
        setColorAttribute.setStyleAttribute("background-color", color);
        scrnMsg.addGUIAttribute(setColorAttribute);
    }
    
    /**
     * <pre>
     * Set Screen display
     * Delete Sort icon
     * Set Detail Line color
     * Set Focus
     * 
     * </pre>
     * 
     * @param scrnMsg NMAL6050BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NLCL1000BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.clearGUIAttribute(SCREEN_ID, "A_TR_"+i);
        }
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(MY_BUSINESS_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
