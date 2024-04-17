/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0110.common;

import static business.servlet.NSAL0110.constant.NSAL0110Constant.DISPLAY_MODE_SUMMARY;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.FOCUS_GROUP_01;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.FOCUS_GROUP_02;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.FOCUS_GROUP_03;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.FOCUS_GROUP_04;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.SCREEN_ID;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.TBL_ID_LEFT;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.TBL_ID_RIGHT;
import business.servlet.NSAL0110.NSAL0110BMsg;
import business.servlet.NSAL0110.NSAL0110Bean;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi      Create          N/A
 * 2015/11/02   Hitachi         K.Kasai         Update          N/A
 * 2016/06/27   Hitachi         O.Okuma         Update          QC#10886
 * 2017/01/30   Hitachi         K.Kitachi       Update          QC#17308
 *</pre>
 */
public class NSAL0110CommonLogic {

    /**
     * 
     * setBgColor
     * 
     * @param scrnMsg NSAL0110BMsg
     */
    public static void setBgColor(NSAL0110BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(TBL_ID_LEFT, scrnMsg.A);
        tblColor.clearRowsBG(TBL_ID_RIGHT, scrnMsg.A);

        // Color on
        tblColor.setAlternateRowsBG(TBL_ID_LEFT, scrnMsg.A, 1);
        tblColor.setAlternateRowsBG(TBL_ID_RIGHT, scrnMsg.A, 1);

    }

    /**
     * 
     * setFocusGrp
     * 
     * @param scrnMsg NSAL0110BMsg
     */
    public static void setFocusGrp(NSAL0110BMsg scrnMsg) {
        if (isSummary(scrnMsg.xxScrItem10Txt_SV.getValue())) {
            setFocusGrpForSummary(scrnMsg);
        } else {
            setFocusGrpForDetail(scrnMsg);
        }
    }

    private static void setFocusGrpForSummary(NSAL0110BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, NSAL0110Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            ZYPGUIFocusRule fRule1 = new ZYPGUIFocusRule(FOCUS_GROUP_01 + i);
            fRule1.setNextId(FOCUS_GROUP_02 + i);
            if (i > 0) {
                fRule1.setPrevId(FOCUS_GROUP_03 + (i - 1));
            }
            tblFocusRule.addRule(fRule1);

            ZYPGUIFocusRule fRule2 = new ZYPGUIFocusRule(FOCUS_GROUP_02 + i);
            fRule2.setNextId(FOCUS_GROUP_03 + i);
            tblFocusRule.addRule(fRule2);

            ZYPGUIFocusRule fRule3 = new ZYPGUIFocusRule(FOCUS_GROUP_03 + i);
            if (i != (scrnMsg.A.getValidCount() - 1)) {
                fRule3.setNextId(FOCUS_GROUP_01 + (i + 1));
            }
            fRule3.setPrevId(FOCUS_GROUP_02 + i);
            tblFocusRule.addRule(fRule3);
        }
    }

    private static void setFocusGrpForDetail(NSAL0110BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, NSAL0110Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            ZYPGUIFocusRule fRule1 = new ZYPGUIFocusRule(FOCUS_GROUP_01 + i);
            fRule1.setNextId(FOCUS_GROUP_02 + i);
            if (i > 0) {
                fRule1.setPrevId(FOCUS_GROUP_04 + (i - 1));
            }
            tblFocusRule.addRule(fRule1);

            ZYPGUIFocusRule fRule2 = new ZYPGUIFocusRule(FOCUS_GROUP_02 + i);
            fRule2.setNextId(FOCUS_GROUP_03 + i);
            fRule2.setPrevId(FOCUS_GROUP_01 + i);
            tblFocusRule.addRule(fRule2);

            ZYPGUIFocusRule fRule3 = new ZYPGUIFocusRule(FOCUS_GROUP_03 + i);
            fRule3.setPrevId(FOCUS_GROUP_02 + i);
            tblFocusRule.addRule(fRule3);

            ZYPGUIFocusRule fRule4 = new ZYPGUIFocusRule(FOCUS_GROUP_04 + i);
            if (i != (scrnMsg.A.getValidCount() - 1)) {
                fRule4.setNextId(FOCUS_GROUP_01 + (i + 1));
            }
            tblFocusRule.addRule(fRule4);
        }
    }

    /**
     * 
     * isSummary
     * 
     * @param dispMode Display Mode
     * @return true : Summary / false : Detail
     */
    public static boolean isSummary(String dispMode) {
        if (DISPLAY_MODE_SUMMARY.equals(dispMode)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * setProtected
     * 
     * @param scrnMsg NSAL0110BMsg
     */
    public static void setProtected(NSAL0110BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2017/01/30 K.Kitachi [QC#17308, MOD]
            scrnMsg.A.no(i).dsContrNum_RS.setInputProtected(true);
            // END 2017/01/30 K.Kitachi [QC#17308, MOD]
            scrnMsg.A.no(i).dsContrNm_RS.setInputProtected(true);
            scrnMsg.A.no(i).dsContrCtrlStsNm_HS.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNum_RS.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_RS.setInputProtected(true);
            scrnMsg.A.no(i).dsContrCatgDescTxt_RS.setInputProtected(true);

            scrnMsg.A.no(i).dsContrDtlTpDescTxt_RS.setInputProtected(true);
            scrnMsg.A.no(i).dsContrCtrlStsNm_DS.setInputProtected(true);
            scrnMsg.A.no(i).serNum_RS.setInputProtected(true);
            scrnMsg.A.no(i).mdlNm_RS.setInputProtected(true);
        }
    }
}

