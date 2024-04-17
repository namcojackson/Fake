/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0320.common;

import java.util.List;

import business.servlet.NSAL0320.NSAL0320BMsg;
import business.servlet.NSAL0320.NSAL0320Bean;
import business.servlet.NSAL0320.constant.NSAL0320Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2015/10/13   Hitachi         T.Tomita        Update          N/A
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC#1538
 * 2016/05/23   Hitachi         T.Tomita        Update          QC#4958
 * 2016/08/25   Hitachi         T.Tomita        Update          QC#12914
 * 2016/09/16   Hitachi         N.Arai          Update          QC#11616
 * 2016/12/15   Hitachi         T.Mizuki        Update          QC#16399
 * 2017/07/13   Hitachi         K.Kim           Update          QC#17276
 *</pre>
 */
public class NSAL0320CommonLogic {

    public static void setupScreenItems(NSAL0320BMsg scrnMsg, List<String> functionList) {
        alternateTableRowColor(scrnMsg);
        activateScreenItems(scrnMsg, functionList);
    }

    private static void alternateTableRowColor(NSAL0320BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(NSAL0320Constant.SCR_ID_00, scrnMsg);
        control.setAlternateRowsBG(NSAL0320Bean.A, scrnMsg.A);
    }

    private static void activateScreenItems(NSAL0320BMsg scrnMsg, List<String> functionList) {
        scrnMsg.dsContrNum.setInputProtected(true);
        scrnMsg.serNum.setInputProtected(true);
        scrnMsg.mdseCd.setInputProtected(true);
        // START 2016/09/16 N.Arai [QC#11616, MOD]
        // scrnMsg.mdseNm.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        // END 2016/09/16 N.Arai [QC#11616, MOD]
        scrnMsg.mdlNm.setInputProtected(true);

        String modeCd = getScreenModeCode(scrnMsg, functionList);
        // mod start 2016/12/15 CSA QC#16399
        if (NSAL0320Constant.SCR_MODE_CD_INQ.equals(modeCd) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.actvFlg.getValue())) {
            // mod end 2016/12/15 CSA QC#16399
            // START 2015/10/13 T.Tomita [N/A, ADD]
            scrnMsg.prcMtrPkgPk_B.setInputProtected(true);
            scrnMsg.bllgMtrMapLvlNum_C.setInputProtected(true);
            // END 2015/10/13 T.Tomita [N/A, ADD]
        } else if (NSAL0320Constant.SCR_MODE_CD_UPD.equals(modeCd)) {
            // START 2015/10/13 T.Tomita [N/A, ADD]
            scrnMsg.prcMtrPkgPk_B.setInputProtected(false);
            scrnMsg.bllgMtrMapLvlNum_C.setInputProtected(false);
            // END 2015/10/13 T.Tomita [N/A, ADD]
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2015/12/11 T.Kanasaka [QC#1538, MOD]
            scrnMsg.A.no(i).mtrLbDescTxt_AP.setInputProtected(true);
            scrnMsg.A.no(i).contrMtrMultRate.setAppFracDigit(2);

            if (NSAL0320Constant.SCR_MODE_CD_INQ.equals(modeCd)) {
                scrnMsg.A.no(i).bllblFlg.setInputProtected(true);
                scrnMsg.A.no(i).contrMtrMultRate.setInputProtected(true);
                scrnMsg.A.no(i).bllgMtrLbCd.setInputProtected(true);
                continue;
            }

            scrnMsg.A.no(i).bllblFlg.setInputProtected(false);
            scrnMsg.A.no(i).contrMtrMultRate.setInputProtected(false);
            scrnMsg.A.no(i).bllgMtrLbCd.setInputProtected(false);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).mtrEntryMndFlg.getValue())) {
                // START 2017/07/13 K.Kim [QC#17276, MOD]
                // scrnMsg.A.no(i).bllblFlg.setInputProtected(true);
                scrnMsg.A.no(i).bllblFlg.setInputProtected(false);
                // END 2017/07/13 K.Kim [QC#17276, MOD]
            }

            // START 2015/10/13 T.Tomita [N/A, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).bllblFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.actvFlg.getValue())) {
                    scrnMsg.A.no(i).bllgMtrLbCd.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).bllgMtrLbCd.setInputProtected(true);
                }
            } else {
                scrnMsg.A.no(i).contrMtrMultRate.setInputProtected(true);
                scrnMsg.A.no(i).bllgMtrLbCd.setInputProtected(true);
            }
            // END 2015/10/13 T.Tomita [N/A, ADD]
            // END 2015/12/11 T.Kanasaka [QC#1538, MOD]
            // add start 2016/05/23 CSA Defect#4958
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).bllgMtrLbCd_AB.no(0))) {
                // START 2016/08/25 T.Tomita [QC#12914, DEL]
                // scrnMsg.A.no(i).bllblFlg.clear();
                // END 2016/08/25 T.Tomita [QC#12914, DEL]
                scrnMsg.A.no(i).bllblFlg.setInputProtected(true);
            }
            // add end 2016/05/23 CSA Defect#4958
            // mod start 2016/12/15 CSA QC#16399
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.actvFlg.getValue())) {
                scrnMsg.A.no(i).bllblFlg.setInputProtected(true);
            }
            // mod end 2016/12/15 CSA QC#16399
        }
    }

    public static void addCheckItem(NSAL0320BMsg scrnMsg) {
        // TODO service program
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bllblFlg);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrMtrMultRate);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgMtrLbCd);
        }
    }

    public static void activateButtons(S21CommonHandler handler, NSAL0320BMsg scrnMsg, List<String> functionList) {
        String modeCd = getScreenModeCode(scrnMsg, functionList);
        if (NSAL0320Constant.SCR_MODE_CD_INQ.equals(modeCd)) {
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_1[0], NSAL0320Constant.BTN_CMN_BTN_1[1], NSAL0320Constant.BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_SUBMIT[0], NSAL0320Constant.BTN_CMN_SUBMIT[1], NSAL0320Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_3[0], NSAL0320Constant.BTN_CMN_BTN_3[1], NSAL0320Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_4[0], NSAL0320Constant.BTN_CMN_BTN_4[1], NSAL0320Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_5[0], NSAL0320Constant.BTN_CMN_BTN_5[1], NSAL0320Constant.BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_6[0], NSAL0320Constant.BTN_CMN_BTN_6[1], NSAL0320Constant.BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_7[0], NSAL0320Constant.BTN_CMN_BTN_7[1], NSAL0320Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_8[0], NSAL0320Constant.BTN_CMN_BTN_8[1], NSAL0320Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_RESET[0], NSAL0320Constant.BTN_CMN_RESET[1], NSAL0320Constant.BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_RETURN[0], NSAL0320Constant.BTN_CMN_RETURN[1], NSAL0320Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(NSAL0320Constant.BTN_ONCHANGE_SERVICEPROGRAM, false);
        } else if (NSAL0320Constant.SCR_MODE_CD_UPD.equals(modeCd)) {
            // Update
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_1[0], NSAL0320Constant.BTN_CMN_BTN_1[1], NSAL0320Constant.BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_SUBMIT[0], NSAL0320Constant.BTN_CMN_SUBMIT[1], NSAL0320Constant.BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_3[0], NSAL0320Constant.BTN_CMN_BTN_3[1], NSAL0320Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_4[0], NSAL0320Constant.BTN_CMN_BTN_4[1], NSAL0320Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_5[0], NSAL0320Constant.BTN_CMN_BTN_5[1], NSAL0320Constant.BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_6[0], NSAL0320Constant.BTN_CMN_BTN_6[1], NSAL0320Constant.BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_7[0], NSAL0320Constant.BTN_CMN_BTN_7[1], NSAL0320Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_8[0], NSAL0320Constant.BTN_CMN_BTN_8[1], NSAL0320Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_RESET[0], NSAL0320Constant.BTN_CMN_RESET[1], NSAL0320Constant.BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(NSAL0320Constant.BTN_CMN_RETURN[0], NSAL0320Constant.BTN_CMN_RETURN[1], NSAL0320Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(NSAL0320Constant.BTN_ONCHANGE_SERVICEPROGRAM, true);
        }
    }

    public static String getScreenModeCode(NSAL0320BMsg scrnMsg, List<String> functionList) {
        String modeCd = NSAL0320Constant.SCR_MODE_CD_INQ;
        // START 2015/10/13 T.Tomita [N/A, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.xxModeCd) && NSAL0320Constant.XX_MODE_CD_INQ.equals(scrnMsg.xxModeCd.getValue())) {
            modeCd = NSAL0320Constant.SCR_MODE_CD_INQ;
            return modeCd;
        }
        // END 2015/10/13 T.Tomita [N/A, ADD]
        if (functionList == null || functionList.isEmpty()) {
            modeCd = NSAL0320Constant.SCR_MODE_CD_INQ;
        } else if (functionList.contains(NSAL0320Constant.FUNC_ID_UPD)) {
            String dsContrStsCd = scrnMsg.dsContrDtlStsCd.getValue();
            String dsContrDtlStsCd = scrnMsg.dsContrDtlStsCd.getValue();
            modeCd = NSAL0320Constant.SCR_MODE_CD_UPD;
            if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd) || DS_CONTR_DTL_STS.INACTIVE.equals(dsContrDtlStsCd)) {
                modeCd = NSAL0320Constant.SCR_MODE_CD_INQ;
            }
        } else if (functionList.contains(NSAL0320Constant.FUNC_ID_INQ)) {
            modeCd = NSAL0320Constant.SCR_MODE_CD_INQ;
        }
        return modeCd;
    }
}
