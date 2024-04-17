/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290.common;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.ADD_BTN;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BIZ_ID;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_01_SAV_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_01_SAV_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_01_SAV_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_02_SUB_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_02_SUB_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_02_SUB_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_03_APL_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_03_APL_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_03_APL_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_04_APR_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_04_APR_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_04_APR_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_05_REJ_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_05_REJ_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_05_REJ_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_06_DWL_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_06_DWL_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_06_DWL_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_07_DEL_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_07_DEL_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_07_DEL_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_09_RST_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_09_RST_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_09_RST_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_10_RTR_GUARD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_10_RTR_LABEL;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.BTN_10_RTR_NAME;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.PRIV_UPD;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.RMV_BTN;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.SCRN_ID_00;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.TBL_A;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.TBL_B1;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.TBL_B2;

import java.util.List;

import business.servlet.NMAL7290.NMAL7290BMsg;
import business.servlet.NMAL7290.NMAL7290_ABMsg;
import business.servlet.NMAL7290.NMAL7290_BBMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRCD_ACT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7290CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL7290CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NMAL7290BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NMAL7290BMsg scrnMsg) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);

    }

    /**
     * Control Common Button Properties.
     * @param handler S21CommonHandler
     * @param userProfileService S21UserProfileService
     * @param scrnMsg NMAL7290BMsg
     */
    public static void ctrlCmnBtnProp(S21CommonHandler handler, S21UserProfileService userProfileService, NMAL7290BMsg scrnMsg) {

        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);// QC#9694 2016/07/08 Add

        if (scrnMsg.B.getValidCount() > 0) {
            List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
            if (functionIds.contains(PRIV_UPD)) {
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
            handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
            handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 1, null);// QC#9694 2016/07/08 Add
        }
    }

    /**
     * Control Button properties.
     * @param handler Event Handler
     * @param scrnMsg NMAL7290BMsg
     */
    public static void ctrlBtnProp(S21CommonHandler handler, NMAL7290BMsg scrnMsg) {

        handler.setButtonEnabled(ADD_BTN, false);
        handler.setButtonEnabled(RMV_BTN, false);

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(ADD_BTN, true);
        }
        if (scrnMsg.B.getValidCount() > 0) {
            handler.setButtonEnabled(RMV_BTN, true);
        }
    }

    /**
     * Control Input properties.
     * @param scrnMsg NMAL7290BMsg
     */
    public static void ctrlInputProp(NMAL7290BMsg scrnMsg) {

        scrnMsg.xxFullNm_H1.setInputProtected(true);// QC#9694 2016/07/08 Add
        scrnMsg.xxFullNm_H2.setInputProtected(true);// QC#9694 2016/07/08 Add
        scrnMsg.cratDt.setInputProtected(true);// QC#9694 2016/07/08 Add
        scrnMsg.lastUpdDt.setInputProtected(true);// QC#9694 2016/07/08 Add
        NMAL7290_ABMsg aBMsg = null;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            aBMsg = scrnMsg.A.no(i);
            aBMsg.prcRuleNm_A.setInputProtected(true);
            aBMsg.prcRuleCondTpDescTxt_A.setInputProtected(true);
            aBMsg.prcRuleCatgDescTxt_A.setInputProtected(true);
            aBMsg.defRulePrcdNum_A.setInputProtected(true); // QC#9694 2016/07/08 Add
        }

        NMAL7290_BBMsg bBMsg = null;
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            bBMsg = scrnMsg.B.no(i);
            // QC#9694 2016/07/08 Add Start
            if (PRC_PRCD_ACT_TP.ALL.equals(scrnMsg.prcPrcdActTpCd.getValue())) {
                bBMsg.prcRulePrcdSqNum_B.setInputProtected(false);
            } else {
                bBMsg.prcRulePrcdSqNum_B.clear();
                bBMsg.prcRulePrcdSqNum_B.setInputProtected(true);
            }
            // QC#9694 2016/07/08 Add End
            bBMsg.prcRuleHdrPk_B.setInputProtected(true);
            bBMsg.prcRuleNm_B.setInputProtected(true);
            bBMsg.prcRuleCondTpDescTxt_B.setInputProtected(true);
            bBMsg.lineBizTpDescTxt_B.setInputProtected(true);
            bBMsg.prcRuleCatgDescTxt_B.setInputProtected(true);
            bBMsg.applyAutoFlg_B.setInputProtected(true);
            bBMsg.ovrdFlg_B.setInputProtected(true);
            bBMsg.xxScrItem8Txt_B.setInputProtected(true);
            bBMsg.defRulePrcdNum_B.setInputProtected(true);
            bBMsg.effFromDt_B.setInputProtected(true);
            bBMsg.effThruDt_B.setInputProtected(true);
        }
    }

    /**
     * Set Table Background Color
     * @param scrnMsg NMAL7290BMsg
     * @param tblId String
     */
    public static void setTblColor(NMAL7290BMsg scrnMsg, String tblId) {

        if (TBL_A.equals(tblId) && scrnMsg.A.getValidCount() > 0) {
            S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
            tblColor.clearRowsBG(TBL_A, scrnMsg.A);
            tblColor.setAlternateRowsBG(TBL_A, scrnMsg.A, 1);
        } else if (!TBL_A.equals(tblId) && scrnMsg.B.getValidCount() > 0) {
            S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
            tblColor.clearRowsBG(TBL_B1, scrnMsg.B);
            tblColor.clearRowsBG(TBL_B2, scrnMsg.B);
            tblColor.setAlternateRowsBG(TBL_B1, scrnMsg.B, 1);
            tblColor.setAlternateRowsBG(TBL_B2, scrnMsg.B, 1);
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NMAL7290BMsg scrnMsg) {

        //scrnMsg.addCheckItem(scrnMsg.prcRulePrcdGrpNum);
        scrnMsg.addCheckItem(scrnMsg.prcRulePrcdPk); // QC#9694 2016/07/08 Add
        scrnMsg.addCheckItem(scrnMsg.prcRulePrcdGrpNm);
        scrnMsg.addCheckItem(scrnMsg.prcPrcdActTpCd);     // QC#9694 2016/07/08 Add
        scrnMsg.addCheckItem(scrnMsg.effFromDt);     // QC#9694 2016/07/08 Add

        NMAL7290_BBMsg scrnLineMsg = null;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnLineMsg = scrnMsg.B.no(i);
        // QC#9694 2016/07/08 Mod Start
        //     scrnMsg.addCheckItem(scrnLineMsg.prcRulePrcdSqNum_B);
        //     scrnMsg.addCheckItem(scrnLineMsg.prcRuleOpTpCd_B);
        //     scrnMsg.addCheckItem(scrnLineMsg.prcRuleEvtrTpCd_B);
            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_B);
            if (PRC_PRCD_ACT_TP.ALL.equals(scrnMsg.prcPrcdActTpCd.getValue())) {
                scrnMsg.addCheckItem(scrnLineMsg.prcRulePrcdSqNum_B);
            }
        // QC#9694 2016/07/08 Mod End
        }
    }
}
