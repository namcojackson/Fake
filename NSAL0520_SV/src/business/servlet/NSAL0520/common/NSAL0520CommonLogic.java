package business.servlet.NSAL0520.common;

import business.servlet.NSAL0520.NSAL0520BMsg;
import business.servlet.NSAL0520.NSAL0520Bean;
import business.servlet.NSAL0520.constant.NSAL0520Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NSAL0520CommonLogic {

    private static void alternateTableRowColor(NSAL0520BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(NSAL0520Constant.SCR_ID_00, scrnMsg);
        control.clearRowsBG("A_Right", scrnMsg.A);
        control.clearRowsBG("A_Left", scrnMsg.A);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            int xxRowNum = scrnMsg.A.no(i).xxRowNum_A.getValueInt();
            if (xxRowNum % 2 == 0) {
                control.setRowStyle("A_Right", i, "pEvenNumberBGcolor");
                control.setRowStyle("A_Left", i, "pEvenNumberBGcolor");
            }
        }
    }

    private static void activateButtons(S21CommonHandler handler, NSAL0520BMsg scrnMsg) {
        handler.setButtonProperties(NSAL0520Constant.BTN_CMN_CLEAR[0], NSAL0520Constant.BTN_CMN_CLEAR[1], NSAL0520Constant.BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(NSAL0520Constant.BTN_CMN_CLOSE[0], NSAL0520Constant.BTN_CMN_CLOSE[1], NSAL0520Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    private static void activateScreenItems(NSAL0520BMsg scrnMsg) {
        scrnMsg.A.setInputProtected(true);
    }

    private static void setTabOrder(NSAL0520BMsg scrnMsg) {
        ZYPGUITableFocusRule tableRule = new ZYPGUITableFocusRule(NSAL0520Constant.SCR_ID_00, NSAL0520Bean.A);
        scrnMsg.addGUIAttribute(tableRule);
        ZYPGUIFocusRule rule = null;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            rule = new ZYPGUIFocusRule(NSAL0520Bean.svcConfigMstrPk_A + "#" + i);
            if (i > 0) {
                rule.setPrevId(NSAL0520Bean.xxGenlFldAreaTxt_A + "#" + (i - 1));
            }
            tableRule.addRule(rule);
            rule = new ZYPGUIFocusRule(NSAL0520Bean.mdlNm_A + "#" + i);
            // START 2018/09/13 K.fujimoto [QC#28240, MOD]
            rule.setNextId(NSAL0520Bean.billToDsAcctNm_A + "#" + i);
            tableRule.addRule(rule);
            rule = new ZYPGUIFocusRule(NSAL0520Bean.billToDsAcctNm_A + "#" + i);
            rule.setPrevId(NSAL0520Bean.mdlNm_A + "#" + i);
            tableRule.addRule(rule);
            // END 2018/09/13 K.fujimoto [QC#28240, MOD]
            rule = new ZYPGUIFocusRule(NSAL0520Bean.xxGenlFldAreaTxt_A + "#" + i);
            if ((i + 1) != scrnMsg.A.getValidCount()) {
                rule.setNextId(NSAL0520Bean.svcConfigMstrPk_A + "#" + (i + 1));
            }
            tableRule.addRule(rule);
        }
    }

    public static void setupScreenItems(S21CommonHandler handler, NSAL0520BMsg scrnMsg) {
        activateButtons(handler, scrnMsg);
        activateScreenItems(scrnMsg);
        setTabOrder(scrnMsg);
        alternateTableRowColor(scrnMsg);
    }

}
