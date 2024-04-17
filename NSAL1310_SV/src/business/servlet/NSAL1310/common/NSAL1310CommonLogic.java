/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310.common;

import static business.servlet.NSAL1310.constant.NSAL1310Constant.*;

import java.util.List;

import business.servlet.NSAL1310.NSAL1310BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 * 2018/07/31   Hitachi         A.Kohinata      Update          QC#26659
 *</pre>
 */
public class NSAL1310CommonLogic {

    /**
     * setupScreenItemsInit
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL1310BMsg
     */
    public static void setupScreenItemsInit(S21CommonHandler handler, NSAL1310BMsg scrnMsg) {
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Complete Contract. UserID is -> " + profile.getContextUserInfo().getUserId());
        }

        scrnMsg.setInputProtected(false);
        handler.setButtonEnabledAll();
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        // START 2017/01/20 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        // END 2017/01/20 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonEnabled(BTN_DELETE_LINE, false);

        if (funcIdList.contains(FUNC_UPDATE)) {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonEnabled(BTN_ADD_LINE, true);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonEnabled(BTN_ADD_LINE, false);
        }

        S21TableColorController lineTblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        lineTblColor.clearRowsBG("A", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * setupScreenItemsList
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL1310BMsg
     */
    public static void setupScreenItemsList(S21CommonHandler handler, NSAL1310BMsg scrnMsg) {
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Complete Contract. UserID is -> " + profile.getContextUserInfo().getUserId());
        }

        boolean funcUpdateFlag = false;
        if (funcIdList.contains(FUNC_UPDATE)) {
            funcUpdateFlag = true;
        }

        if (funcUpdateFlag) {
            if (scrnMsg.xxPageShowOfNum.getValueInt() == scrnMsg.xxListNum.getValueInt()) {
                handler.setButtonEnabled(BTN_ADD_LINE, false);
            } else {
                handler.setButtonEnabled(BTN_ADD_LINE, true);
            }
            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled(BTN_DELETE_LINE, false);
            } else {
                handler.setButtonEnabled(BTN_DELETE_LINE, true);
            }
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcTermAttrbDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcTermAttrbSortNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcTermCondCatgDescTxt_A1.setInputProtected(true);
            if (funcUpdateFlag) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).defCovTermCondPk_A1)) {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                    scrnMsg.A.no(i).svcCovTmplTpCd_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPENWIN_CONDATTRB, i, false);
                } else {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).svcCovTmplTpCd_A1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPENWIN_CONDATTRB, i, true);
                }
                // mod start 2018/06/25 QC#17369
                if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1.setInputProtected(true);
                }
                // mod end 2018/06/25 QC#17369
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).svcCovTmplTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).svcTermCondDataValCd_D1.setInputProtected(true);
                scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).condValNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxTrxDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).svcTermCondDataValCd_L1.setInputProtected(true);
                scrnMsg.A.no(i).attrbUpdAvalFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).asgContrLvlFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).asgMachLvlFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).covTermCondActvFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_CONDATTRB, i, false);
            }
            scrnMsg.A.no(i).condValNum_A1.setAppFracDigit(0);
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL1310BMsg
     */
    public static void addCheckItem(NSAL1310BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcCovTmplTpCd_A1.clearErrorInfo();
            scrnMsg.A.no(i).svcTermAttrbDescTxt_A1.clearErrorInfo();
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcCovTmplTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDescTxt_A1);
            if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermCondDataValCd_D1);
            } else if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1);
            } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).condValNum_A1);
            } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxTrxDt_A1);
            } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermCondDataValCd_L1);
            // add start 2018/06/25 QC#17369
            } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1)) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1);
                    scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Default Value" });
                }
            // add end 2018/06/25 QC#17369
            // add start 2018/07/31 QC#26659
            } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(scrnMsg.A.no(i).svcTermDataTpCd_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1);
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1) && !ZYPCommonFunc.isNumberCheck(scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1.getValue())) {
                    scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1.setErrorInfo(1, ZZM9004E, new String[] {"Default Value" });
                }
            // add end 2018/07/31 QC#26659
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).attrbUpdAvalFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asgContrLvlFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asgMachLvlFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).covTermCondActvFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcTermAttrbDescTxt_A1)) {
                scrnMsg.A.no(i).svcTermAttrbDescTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"T&C Attrb" });
            }
        }
    }

    /**
     * addCheckItemCheck
     * @param scrnMsg NSAL1310BMsg
     */
    public static void addCheckItemCheck(NSAL1310BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcCovTmplTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDescTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).attrbUpdAvalFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asgContrLvlFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asgMachLvlFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).covTermCondActvFlg_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
        }
    }
}
