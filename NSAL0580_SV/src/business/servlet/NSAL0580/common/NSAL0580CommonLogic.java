/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0580.common;

import static business.servlet.NSAL0580.constant.NSAL0580Constant.*;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;

import business.servlet.NSAL0580.NSAL0580BMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 *  2015/11/06   Hitachi        J.Kim           Create          N/A
 *  2016/02/26   Hitachi        K.Kishimoto     Update          QC#2011
 *  2017/01/16   Hitachi        K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0580CommonLogic {

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0580BMsg
     * @param functionList List<String>
     */
    public static void activateButtons(EZDCommonHandler handler, NSAL0580BMsg scrnMsg, List<String> functionList) {

        scrnMsg.dsContrNum.setInputProtected(true);
        scrnMsg.dsContrCtrlStsNm.setInputProtected(true);

        // set button property
        // common button
        if (functionList.contains("NSAL0580T020")) {

            handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
            handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_ACTIVE, null);
            handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
            handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
            handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
            handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
            handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
            // Mod Start 01/16/2017 <QC#16331>
            handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
            handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_ACTIVE, null);
            // Mod Start 01/16/2017 <QC#16331>
            handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);
        } else {

            handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
            handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_INACTIVE, null);
            handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
            handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
            handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
            handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
            handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
            handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
            handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_INACTIVE, null);
            handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);
        }
    }

    /**
     * addCheckItemForAllHeader
     * @param scrnMsg NSAL0580BMsg
     */
    public static void addCheckItemForAllHeader(NSAL0580BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsNm);
        //Mod Start 02/26/2016 <QC#2011>
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsCd_H);
        //Mod End   02/26/2016 <QC#2011>
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
    }
}
