/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230.common;

import static business.servlet.NMAL0230.constant.NMAL0230Constant.BIZ_ID;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_01_SAV_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_01_SAV_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_01_SAV_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_02_SUB_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_02_SUB_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_02_SUB_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_03_APL_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_03_APL_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_03_APL_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_04_APR_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_04_APR_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_04_APR_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_05_REJ_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_05_REJ_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_05_REJ_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_06_DWL_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_06_DWL_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_06_DWL_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_07_DEL_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_07_DEL_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_07_DEL_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_09_RST_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_09_RST_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_09_RST_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_10_RTR_GUARD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_10_RTR_LABEL;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BTN_10_RTR_NAME;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.PREV_UPD;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.SCRN_ID_00;

import java.util.List;

import business.servlet.NMAL0230.NMAL0230BMsg;
import business.servlet.NMAL0230.NMAL0230_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL0230CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0230CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL0230BMsg
     * @param scrnAMsgAry NMAL0230_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL0230BMsg scrnMsg, NMAL0230_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL0230BMsg
     * @param scrnAMsgAry NMAL0230_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL0230BMsg scrnMsg, NMAL0230_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL0230BMsg
     * @param scrnAMsgAry NMAL0230_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL0230BMsg scrnMsg, NMAL0230_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param userProfileService S21UserProfileService
     * @param scrnMsg NMAL0230BMsg
     */
    public static void controlScreen(S21CommonHandler handler, S21UserProfileService userProfileService, NMAL0230BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        if (!functionIds.contains(PREV_UPD)) {
            handler.setButtonEnabled("Create", true);
        }

        scrnMsg.coaProdNm_CO.setInputProtected(true);
        scrnMsg.coaProdNm_BO.setInputProtected(true);

        scrnMsg.setFocusItem(scrnMsg.mdseItemTpCd_BO);
    }
}
