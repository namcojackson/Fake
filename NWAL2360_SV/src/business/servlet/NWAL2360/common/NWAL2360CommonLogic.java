/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2360.common;

import static business.servlet.NWAL2360.constant.NWAL2360Constant.BTN_CMN_BTN_10;
import static business.servlet.NWAL2360.constant.NWAL2360Constant.BTN_CMN_BTN_8;
import static business.servlet.NWAL2360.constant.NWAL2360Constant.DISPLAY_NM_DS_IMPT_ORD_PK;
import static business.servlet.NWAL2360.constant.NWAL2360Constant.SCRN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2360.NWAL2360BMsg;

/**
 *<pre>
 * SOM Approval Detail Common Logic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */

public class NWAL2360CommonLogic {

    /**
     * cntrlScrnItemDispInit
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2360BMsg
     */
    public static void cntrlScrnItemDispInit(EZDCommonHandler handler, NWAL2360BMsg scrnMsg) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).somStsTxt.setInputProtected(true);
            scrnMsg.A.no(i).somApvlTpTxt.setInputProtected(true);
            scrnMsg.A.no(i).somApvlDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).somVrsnId.setInputProtected(true);
            scrnMsg.A.no(i).somApvrNm.setInputProtected(true);
            scrnMsg.A.no(i).wfStartTs.setInputProtected(true);
            scrnMsg.A.no(i).wfEndTs.setInputProtected(true);
            scrnMsg.A.no(i).apvlNoteTxt.setInputProtected(true);
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

    }

    /**
     * setNameForMessage
     * @param arg0 NWAL2360BMsg
     */
    public static void setNameForMessage(NWAL2360BMsg arg0) {

        NWAL2360BMsg scrnMsg = (NWAL2360BMsg) arg0;

        scrnMsg.dsImptOrdPk.setNameForMessage(DISPLAY_NM_DS_IMPT_ORD_PK);
    }
}
