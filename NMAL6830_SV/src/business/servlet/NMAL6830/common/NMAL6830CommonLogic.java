package business.servlet.NMAL6830.common;

import static business.servlet.NMAL6830.constant.NMAL6830Constant.BTN_CMN_CLEAR;
import static business.servlet.NMAL6830.constant.NMAL6830Constant.BTN_CMN_CLOSE;
import static business.servlet.NMAL6830.constant.NMAL6830Constant.SCREEN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6830.NMAL6830BMsg;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Cost Update Group Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6830CommonLogic {

    /**
     * initCommonButton
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * initScrn
     * @param scrnMsg NMAL6830BMsg
     * @param baseContents String[][]
     */
    public static void initScrn(NMAL6830BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.mdseCstUpdGrpTxt_H1);
    }

    /**
     * pageNationScrn
     * @param scrnMsg NMAL6830BMsg
     */
    public static void pageNationScrn(NMAL6830BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.mdseCstUpdGrpTxt_H1);
    }
}
