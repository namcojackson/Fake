/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2670.common;

import static business.servlet.NMAL2670.constant.NMAL2670Constant.BTN_DOWNLOAD;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2670.NMAL2670BMsg;
import business.servlet.NMAL2670.constant.NMAL2670Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2670CommonLogic {

    /**
     * Initialize Common Button
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2670Constant.CMN_BTN.CLEAR.getHtmlNm(), NMAL2670Constant.CMN_BTN.CLEAR.getEventNm(), NMAL2670Constant.CMN_BTN.CLEAR.getLabel(), 1, null);
        handler.setButtonProperties(NMAL2670Constant.CMN_BTN.CLOSE.getHtmlNm(), NMAL2670Constant.CMN_BTN.CLOSE.getEventNm(), NMAL2670Constant.CMN_BTN.CLOSE.getLabel(), 1, null);
    }

    /**
     * Clear condition
     * 
     * <pre>
     * clear screen
     * </pre>
     * @param scrnMsg NMAL2670BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NMAL2670BMsg scrnMsg) {

        scrnMsg.orgCd_H1.setInputProtected(true);
        scrnMsg.orgNm_H1.setInputProtected(true);

        handler.setButtonEnabled(BTN_DOWNLOAD, true);

        S21TableColorController tblColor = new S21TableColorController(NMAL2670Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Initialize Screen
     * @param scrnMsg NMAL2670BMsg
     * @param baseContents String[][]
     */
    public static void initScrn(NMAL2670BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(NMAL2670Constant.SCREEN_ID, scrnMsg, baseContents);

        S21TableColorController tblColor = new S21TableColorController(NMAL2670Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }
}
