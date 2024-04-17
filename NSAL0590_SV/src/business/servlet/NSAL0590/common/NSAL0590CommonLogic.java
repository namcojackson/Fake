/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0590.common;

import static business.servlet.NSAL0590.constant.NSAL0590Constant.*;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;

import business.blap.NSAL0590.NSAL0590CMsg;
import business.blap.NSAL0590.NSAL0590SMsg;
import business.servlet.NSAL0590.NSAL0590BMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         J.Kim           Create          N/A
 * 2015/12/01   Hitachi         T.Kanasaka      Update          QC#1264
 * 2016/04/26   Hitachi         T.Iwamoto       Update          QC#1759
 *</pre>
 */
public class NSAL0590CommonLogic {

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0590BMsg
     */
    public static void initialize(EZDCommonHandler handler, NSAL0590BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);
        scrnMsg.A.setInputProtected(false);

        // set button property
        // common button
        handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_ACTIVE, null);
        handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
        handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_ACTIVE, null);
        handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);

        // optional button
        // START 2015/12/01 T.Kanasaka [QC#1264, MOD]
//        if (scrnMsg.A.getValidCount() == 0) {
//            handler.setButtonEnabled(INSERTROW[0], false);
//        } else {
//            handler.setButtonEnabled(INSERTROW[0], true);
//        }
        if (scrnMsg.xxPageShowOfNum.getValueInt() >= MAX_COUNT) {
            handler.setButtonEnabled(INSERTROW[0], false);
        } else {
            handler.setButtonEnabled(INSERTROW[0], true);
        }
        // END 2015/12/01 T.Kanasaka [QC#1264, MOD]
        handler.setButtonEnabled(SEARCH[0], true);

        // ADD start 2016/04/26 CSA Defect#1759
        setBgColor(scrnMsg);
        // ADD end 2016/04/26 CSA Defect#1759
    }

    /**
     * checkAuth
     * @param scrnMsg NSAL0590BMsg
     * @param handler EZDCommonHandler
     */
    public static void checkAuth(EZDCommonHandler handler, NSAL0590BMsg scrnMsg) {

        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (!functionIds.contains("NSAL0590T020")) {
            handler.setButtonEnabled(INSERTROW[0], false);
            handler.setButtonEnabled(SUBMIT[0], false);
        }
    }

    /**
     * setBgColor
     * @param scrnMsg NSAL0590BMsg
     */
    public static void setBgColor(NSAL0590BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.A);
        }
    }

    /**
     * setProtected
     * @param scrnMsg NSAL0590BMsg
     * @param handler EZDCommonHandler
     */
    public static void setProtected(NSAL0590BMsg scrnMsg, EZDCommonHandler handler) {

        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        scrnMsg.setInputProtected(false);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (hasValue(scrnMsg.A.no(i).dsContrRptGrpDescTxt)) {
                scrnMsg.A.no(i).dsContrRptGrpDescTxt.setInputProtected(false);
            }
            if (!functionIds.contains("NSAL0590T020")) {
                scrnMsg.A.no(i).dsContrRptGrpDescTxt.setInputProtected(false);
            }
        }
    }

    /**
     * addCheckItemForAllHeader
     * @param scrnMsg NSAL0590BMsg
     */
    public static void addCheckItemForAllHeader(NSAL0590BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpNum_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpDescTxt_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpStartDt_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpEndDt_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpStartDt_HE);
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpEndDt_HE);
    }

    /**
     * clear Search Result
     * @param bizMsg NSAL0590CMsg
     * @param globalMsg NSAL0590SMsg
     */
    public static void clearTable(NSAL0590CMsg bizMsg, NSAL0590SMsg globalMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
    }

    /**
     * clearPageNum
     * @param cMsg NSAL0590CMsg
     */
    public static void clearPageNum(NSAL0590CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }
}
