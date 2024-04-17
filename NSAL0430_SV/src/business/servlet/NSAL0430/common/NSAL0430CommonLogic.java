package business.servlet.NSAL0430.common;

import java.math.BigDecimal;

import parts.common.EZDGUIAttribute;
import business.servlet.NSAL0430.NSAL0430BMsg;
import business.servlet.NSAL0430.NSAL0430Bean;
import business.servlet.NSAL0430.constant.NSAL0430Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NSAL0430CommonLogic {

    public static void addCheckItem(NSAL0430BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_D);
        }
    }

    public static void setupScreenItems(S21CommonHandler handler, NSAL0430BMsg scrnMsg) {
        activateButtons(handler, scrnMsg);
        activateScreenItems(scrnMsg);
        alternateTableRowColor(scrnMsg);
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    private static void activateButtons(S21CommonHandler handler, NSAL0430BMsg scrnMsg) {
        handler.setButtonProperties(NSAL0430Constant.BTN_CMN_CLEAR[0], NSAL0430Constant.BTN_CMN_CLEAR[1], NSAL0430Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NSAL0430Constant.BTN_CMN_CLOSE[0], NSAL0430Constant.BTN_CMN_CLOSE[1], NSAL0430Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    private static void activateScreenItems(NSAL0430BMsg scrnMsg) {
        scrnMsg.serNum.setInputProtected(true);
        scrnMsg.mdseCd.setInputProtected(true);
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // scrnMsg.mdseNm.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        scrnMsg.mdlNm.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).xxGenlFldAreaTxt_B.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).dsMtrReadTpDescTxt_D.setInputProtected(true);
        }
    }

    private static void addSelectedRowAttribute(NSAL0430BMsg scrnMsg, BigDecimal svcPhysMtrReadGrpSq) {
        addRowGrpBgColor(scrnMsg, svcPhysMtrReadGrpSq, "yellow");
    }

    private static void addEvenRowAttribute(NSAL0430BMsg scrnMsg, BigDecimal svcPhysMtrReadGrpSq) {
        addRowGrpBgColor(scrnMsg, svcPhysMtrReadGrpSq, "#E8E8E8");
    }

    private static void addRowGrpBgColor(NSAL0430BMsg scrnMsg, BigDecimal svcPhysMtrReadGrpSq, String clrCd) {
        EZDGUIAttribute attr = new EZDGUIAttribute(NSAL0430Constant.SCR_ID_00, "dl#" + svcPhysMtrReadGrpSq.toPlainString());
        attr.setStyleAttribute("background-color", clrCd);
        scrnMsg.addGUIAttribute(attr);
        attr = new EZDGUIAttribute(NSAL0430Constant.SCR_ID_00, "dr#" + svcPhysMtrReadGrpSq.toPlainString());
        attr.setStyleAttribute("background-color", clrCd);
        scrnMsg.addGUIAttribute(attr);
    }

    private static void clearRowGrpBgColor(NSAL0430BMsg scrnMsg, BigDecimal svcPhysMtrReadGrpSq) {
        scrnMsg.clearGUIAttribute(NSAL0430Constant.SCR_ID_00, "dl#" + svcPhysMtrReadGrpSq.toPlainString());
        scrnMsg.clearGUIAttribute(NSAL0430Constant.SCR_ID_00, "dr#" + svcPhysMtrReadGrpSq.toPlainString());
    }

    private static void alternateTableRowColor(NSAL0430BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(NSAL0430Constant.SCR_ID_00, scrnMsg);
        control.setAlternateRowsBG(NSAL0430Bean.A, scrnMsg.A);
        control.setAlternateRowsBG(NSAL0430Bean.B, scrnMsg.B);

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            BigDecimal curSq = scrnMsg.D.no(i).svcPhysMtrReadGrpSq_D.getValue();
            clearRowGrpBgColor(scrnMsg, curSq);
        }

        BigDecimal grpSq = NSAL0430Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ;
        int rowGrp = 0;
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            BigDecimal curSq = scrnMsg.D.no(i).svcPhysMtrReadGrpSq_D.getValue();
            if (!isEqualNum(grpSq, curSq)) {
                grpSq = curSq;
                rowGrp = rowGrp + 1;
                if (rowGrp % 2 == 0) {
                    addEvenRowAttribute(scrnMsg, grpSq);
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.D.no(i).xxChkBox_D.getValue())) {
                addSelectedRowAttribute(scrnMsg, curSq);
            }
        }
    }

}
