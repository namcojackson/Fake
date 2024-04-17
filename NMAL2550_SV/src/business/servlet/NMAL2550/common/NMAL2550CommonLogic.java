/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2550.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2550.NMAL2550BMsg;
import business.servlet.NMAL2550.constant.NMAL2550Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2550CommonLogic {

    /**
     * Initialize Common Button
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(NMAL2550Constant.CMN_BTN.CLEAR.getHtmlNm(), NMAL2550Constant.CMN_BTN.CLEAR.getEventNm(), NMAL2550Constant.CMN_BTN.CLEAR.getLabel(), 1, null);
        handler.setButtonProperties(NMAL2550Constant.CMN_BTN.CLOSE.getHtmlNm(), NMAL2550Constant.CMN_BTN.CLOSE.getEventNm(), NMAL2550Constant.CMN_BTN.CLOSE.getLabel(), 1, null);
    }

    /**
     * Clear condition
     * 
     * <pre>
     * clear screen
     * </pre>
     * @param scrnMsg NMAL2550BMsg
     */
    public static void clearCondition(NMAL2550BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(NMAL2550Constant.SCREEN_ID);
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxDtlCdLbNm.clear();
        scrnMsg.xxDtlNmLbNm.clear();
    }

    /**
     * Initialize Screen
     * @param scrnMsg NMAL2550BMsg
     * @param baseContents String[][]
     */
    public static void initScrn(NMAL2550BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(NMAL2550Constant.SCREEN_ID, scrnMsg, baseContents);

        S21TableColorController tblColor = new S21TableColorController(NMAL2550Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }

    /**
     * clear screen
     * @param scrnMsg NMAL2550BMsg
     */
    public static void setFocusForAPIError(NMAL2550BMsg scrnMsg) {

        if (NMAL2550Constant.COA_CMPY_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaCmpyCd_H1);
        }
        if (NMAL2550Constant.COA_AFFL_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaAfflCd_H1);
        }
        if (NMAL2550Constant.COA_BR_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaBrCd_H1);
        }
        if (NMAL2550Constant.COA_CC_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaCcCd_H1);
        }
        if (NMAL2550Constant.COA_ACCT_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaAcctCd_H1);
        }
        if (NMAL2550Constant.COA_PROD_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaProdCd_H1);
        }
        if (NMAL2550Constant.COA_CH_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaChCd_H1);
        }
        if (NMAL2550Constant.COA_PROJ_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaProjCd_H1);
        }
        if (NMAL2550Constant.COA_EXTN_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.coaExtnCd_H1);
        }
    }
}
