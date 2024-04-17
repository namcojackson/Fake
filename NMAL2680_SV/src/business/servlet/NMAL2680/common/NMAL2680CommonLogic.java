/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2680.common;

import static business.servlet.NMAL2680.constant.NMAL2680Constant.BTN_DOWNLOAD;
import static business.servlet.NMAL2680.constant.NMAL2680Constant.BTN_SEARCH_CUST;
import static business.servlet.NMAL2680.constant.NMAL2680Constant.BTN_SEARCH_PROS;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2680.NMAL2680BMsg;
import business.servlet.NMAL2680.constant.NMAL2680Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2680CommonLogic {

    /**
     * Initialize Common Button
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2680Constant.CMN_BTN.CLEAR.getHtmlNm(), NMAL2680Constant.CMN_BTN.CLEAR.getEventNm(), NMAL2680Constant.CMN_BTN.CLEAR.getLabel(), 1, null);
        handler.setButtonProperties(NMAL2680Constant.CMN_BTN.CLOSE.getHtmlNm(), NMAL2680Constant.CMN_BTN.CLOSE.getEventNm(), NMAL2680Constant.CMN_BTN.CLOSE.getLabel(), 1, null);
    }

    /**
     * Clear condition
     * 
     * <pre>
     * clear screen
     * </pre>
     * @param scrnMsg NMAL2680BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NMAL2680BMsg scrnMsg) {

        scrnMsg.orgCd_H1.setInputProtected(true);
        scrnMsg.orgNm_H1.setInputProtected(true);

        handler.setButtonEnabled(BTN_DOWNLOAD, true);
        if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            handler.setButtonEnabled(BTN_SEARCH_CUST, false);
            handler.setButtonEnabled(BTN_SEARCH_PROS, true);
        } else {
            handler.setButtonEnabled(BTN_SEARCH_CUST, true);
            handler.setButtonEnabled(BTN_SEARCH_PROS, false);
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).trtyRulePk_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctTpNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A1.setInputProtected(true);
        }

        S21TableColorController tblColor = new S21TableColorController(NMAL2680Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Initialize Screen
     * @param scrnMsg NMAL2680BMsg
     * @param baseContents String[][]
     */
    public static void initScrn(NMAL2680BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(NMAL2680Constant.SCREEN_ID, scrnMsg, baseContents);

        S21TableColorController tblColor = new S21TableColorController(NMAL2680Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }
}
