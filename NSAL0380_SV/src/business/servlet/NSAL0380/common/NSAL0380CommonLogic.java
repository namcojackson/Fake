/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380.common;

import java.util.ArrayList;
import java.util.List;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;

import business.servlet.NSAL0380.NSAL0380BMsg;
import business.servlet.NSAL0380.NSAL0380Bean;
import business.servlet.NSAL0380.NSAL0380_ABMsg;
import business.servlet.NSAL0380.NSAL0380_ABMsgArray;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/02/25   Hitachi         O.Okuma         Update          QC3024
 * 2016/04/06   Hitachi         T.Tomita        Update          QC#3049
 * 2016/07/15   Hitachi         A.Kohinata      Update          QC#8608
 * 2017/01/12   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/09/29   Hitachi         K.Kojima        Update          QC#18378
 * 2017/11/08   Hitachi         M.Kidokoro      Update          QC#21928
 * 2017/11/15   Hitachi         M.Kidokoro      Update          QC#21928-1
 * 2018/05/30   CITS            M.Naito         Update          QC#22887
 * 2018/12/20   Hitachi         K.Kim           Update          QC#29636
 * 2019/01/23   Fujitsu         R.Nakamura      Update          QC#29782
 * 2019/06/26   Hitachi         A.Kohinata      Update          QC#50931
 *</pre>
 */
public class NSAL0380CommonLogic {

    /**
     * get Input Parameter
     * @param scrnMsg NSAL0380BMsg
     * @param params Object[]
     */
    public static void getInputParam(NSAL0380BMsg scrnMsg, Object[] params) {

        ZYPTableUtil.clear(scrnMsg.P);

        if (params instanceof Object[]) {
            Object[] prm = (Object[]) params;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBMsgArray) {
                    EZDMsg.copy(((EZDBMsgArray) prm[0]), null, scrnMsg.P, null);
                }
            }
        }

    }

    /**
     * init Control CommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_SAVE_BTN_NM, "", NSAL0380Constant.BTN_CMN_SAVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_SUBMIT_BTN_NM, NSAL0380Constant.BTN_CMN_SUBMIT_EVENT_NM, NSAL0380Constant.BTN_CMN_SUBMIT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_APPLY_BTN_NM, "", NSAL0380Constant.BTN_CMN_APPLY_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_APPROVE_BTN_NM, "", NSAL0380Constant.BTN_CMN_APPROVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_REJECT_BTN_NM, "", NSAL0380Constant.BTN_CMN_REJECT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_DOWNLOAD_BTN_NM, "", NSAL0380Constant.BTN_CMN_DOWNLOAD_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_DELETE_BTN_NM, "", NSAL0380Constant.BTN_CMN_DELETE_LABEL, 0, null);
        // START 2017/01/12 K.Ochiai [QC#16331, MOD]
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_CLEAR_BTN_NM, "", NSAL0380Constant.BTN_CMN_CLEAR_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_RESET_BTN_NM, NSAL0380Constant.BTN_CMN_RESET_EVENT_NM, NSAL0380Constant.BTN_CMN_RESET_LABEL, 0, null);
        // END 2017/01/12 K.Ochiai [QC#16331, MOD]
        scrnAppli.setButtonProperties(NSAL0380Constant.BTN_CMN_RETURN_BTN_NM, NSAL0380Constant.BTN_CMN_RETURN_EVENT_NM, NSAL0380Constant.BTN_CMN_RETURN_LABEL, 0, null);
    }

    /**
     * control CommonButton
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0380BMsg
     * @param isActive boolean
     */
    public static void controlCommonButton(EZDCommonHandler scrnAppli, NSAL0380BMsg scrnMsg, boolean isActive) {

        scrnAppli.setButtonEnabled(NSAL0380Constant.BTN_CMN_SUBMIT_BTN_NM, isActive);
        // START 2017/01/12 K.Ochiai [QC#16331, MOD]
        scrnAppli.setButtonEnabled(NSAL0380Constant.BTN_CMN_RESET_BTN_NM, true);
        // END 2017/01/12 K.Ochiai [QC#16331, MOD]
        scrnAppli.setButtonEnabled(NSAL0380Constant.BTN_CMN_RETURN_BTN_NM, true);
    }

    /**
     * control Detail
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0380BMsg
     */
    public static void controlScreenFields(EZDCommonHandler scrnAppli, NSAL0380BMsg scrnMsg) {

        // START 2018/05/30 M.Naito [QC#22887, MOD]
//        scrnMsg.basePrcUpRatio_H.setAppFracDigit(0);
//        scrnMsg.mtrPrcUpRatio_H.setAppFracDigit(0);
//        scrnMsg.maxPrcUpRatio_H.setAppFracDigit(0);
        scrnMsg.basePrcUpRatio_H.setAppFracDigit(2);
        scrnMsg.mtrPrcUpRatio_H.setAppFracDigit(2);
        scrnMsg.maxPrcUpRatio_H.setAppFracDigit(2);
        // END 2018/05/30 M.Naito [QC#22887, MOD]
    }

    /**
     * control Detail
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0380BMsg
     */
    public static void controlDtl(EZDCommonHandler scrnAppli, NSAL0380BMsg scrnMsg) {

        // add start 2016/07/15 CSA Defect#8608
        controlScreenHeaderFields(scrnAppli, scrnMsg);
        // add end 2016/07/15 CSA Defect#8608

        if (scrnMsg.A.getValidCount() > 0) {
            if (hasUpdateFuncId()) {
                controlCommonButton(scrnAppli, scrnMsg, true);
                controlScreenDetailFields(scrnMsg);
            } else {
                controlCommonButton(scrnAppli, scrnMsg, false);
                inActiveDtlField(scrnAppli, scrnMsg);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(0).contrAutoRnwTpCd_A);

        } else {
            controlCommonButton(scrnAppli, scrnMsg, false);
        }

        setTblBackColor(scrnMsg);
    }

    private static void initField(NSAL0380_ABMsg abMsg) {

        cotrolInputField(abMsg, false);
        abMsg.xxDsMsgEntryTxt_A.setInputProtected(true);

        // START 2018/05/30 M.Naito [QC#22887, MOD]
//        abMsg.maxPrcUpRatio_A.setAppFracDigit(0);
//        abMsg.basePrcUpRatio_A.setAppFracDigit(0);
//        abMsg.mtrPrcUpRatio_A.setAppFracDigit(0);
        abMsg.maxPrcUpRatio_A.setAppFracDigit(2);
        abMsg.basePrcUpRatio_A.setAppFracDigit(2);
        abMsg.mtrPrcUpRatio_A.setAppFracDigit(2);
        // END 2018/05/30 M.Naito [QC#22887, MOD]
    }

    private static void cotrolFieldForFleet(NSAL0380_ABMsg abMsg) {

        // mod start 2016/03/04 CSA Defect#3024
//        cotrolInputField(abMsg, true);
        cotrolInputField(abMsg, false);
        // mod end 2016/03/04 CSA Defect#3024
    }

    private static void cotrolInputField(NSAL0380_ABMsg abMsg, boolean flg) {

        abMsg.xxChkBox_A1.setInputProtected(flg);
        abMsg.xxChkBox_A2.setInputProtected(flg);
        abMsg.contrAutoRnwTpCd_A.setInputProtected(flg);
        abMsg.rnwPrcMethCd_A.setInputProtected(flg);
        abMsg.befEndRnwDaysAot_A.setInputProtected(flg);
        abMsg.maxContrRnwCnt_A.setInputProtected(flg);
        abMsg.maxPrcUpRatio_A.setInputProtected(flg);
        abMsg.basePrcUpRatio_A.setInputProtected(flg);
        abMsg.mtrPrcUpRatio_A.setInputProtected(flg);
        // Add Start 2019/01/23 QC#29782
        abMsg.mdseDescShortTxt_A.setInputProtected(true);
        // Add End 2019/01/23 QC#29782
    }

    // add start 2016/07/15 CSA Defect#8608
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0380BMsg scrnMsg) {

        scrnMsg.contrAutoRnwTpCd_H.setInputProtected(false);
        scrnMsg.rnwPrcMethCd_H.setInputProtected(false);
        scrnMsg.befEndRnwDaysAot_H.setInputProtected(false);
        scrnMsg.maxPrcUpRatio_H.setInputProtected(false);
        scrnMsg.maxContrRnwCnt_H.setInputProtected(false);
        scrnMsg.basePrcUpRatio_H.setInputProtected(false);
        scrnMsg.mtrPrcUpRatio_H.setInputProtected(false);

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwBaseFlg_H.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwUsgFlg_H.getValue())) {
            scrnMsg.rnwPrcMethCd_H.setInputProtected(true);
            scrnMsg.befEndRnwDaysAot_H.setInputProtected(true);
            scrnMsg.maxPrcUpRatio_H.setInputProtected(true);
            scrnMsg.maxContrRnwCnt_H.setInputProtected(true);
            scrnMsg.basePrcUpRatio_H.setInputProtected(true);
            scrnMsg.mtrPrcUpRatio_H.setInputProtected(true);
            scrnMsg.rnwPrcMethCd_H.clear();
            scrnMsg.befEndRnwDaysAot_H.clear();
            scrnMsg.maxPrcUpRatio_H.clear();
            scrnMsg.maxContrRnwCnt_H.clear();
            scrnMsg.basePrcUpRatio_H.clear();
            scrnMsg.mtrPrcUpRatio_H.clear();
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.rnwPrcMethCd_H) || RNW_PRC_METH.MODEL_PERCENT.equals(scrnMsg.rnwPrcMethCd_H.getValue())) {
            scrnMsg.basePrcUpRatio_H.setInputProtected(true);
            scrnMsg.mtrPrcUpRatio_H.setInputProtected(true);
            scrnMsg.basePrcUpRatio_H.clear();
            scrnMsg.mtrPrcUpRatio_H.clear();
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwBaseFlg_H.getValue())) {
            scrnMsg.basePrcUpRatio_H.setInputProtected(true);
            scrnMsg.basePrcUpRatio_H.clear();
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwUsgFlg_H.getValue())) {
            scrnMsg.mtrPrcUpRatio_H.setInputProtected(true);
            scrnMsg.mtrPrcUpRatio_H.clear();
        }

        // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H2, ZYPConstant.CHKBOX_ON_Y);
        // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
    }
    // add end 2016/07/15 CSA Defect#8608

    /**
     * control Field for Contract Category Code
     * @param scrnMsg NSAL0380BMsg
     */
    public static void controlScreenDetailFields(NSAL0380BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0380_ABMsg abMsg = scrnMsg.A.no(i);
            String xxDplyByCtrlAncrLvlCd = abMsg.xxDplyByCtrlAncrLvlCd_A.getValue();
            String dsContrStsCdHdr = abMsg.dsContrCtrlStsCd_A1.getValue();
            String dsContrStsCdDtl = abMsg.dsContrCtrlStsCd_A2.getValue();
            String dsContrCatgCd = abMsg.dsContrCatgCd_A.getValue();
            String dsContrDtlTpCd = abMsg.dsContrDtlTpCd_A.getValue();

            // reset
            initField(abMsg);

            // mod start 2016/04/06 CSA Defect#3049
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(xxDplyByCtrlAncrLvlCd)) {

                if (DS_CONTR_STS.TERMINATED.equals(dsContrStsCdHdr) || DS_CONTR_STS.EXPIRED.equals(dsContrStsCdHdr) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCdHdr)) {
                    cotrolInputField(abMsg, true);
                } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                    cotrolFieldForFleet(abMsg);
                }

                abMsg.xxChkBox_A2.setInputProtected(true);

                // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
                if (!DS_CONTR_STS.TERMINATED.equals(dsContrStsCdHdr) && !DS_CONTR_STS.EXPIRED.equals(dsContrStsCdHdr) && !DS_CONTR_STS.CANCELLED.equals(dsContrStsCdHdr)) {
                    ZYPEZDItemValueSetter.setValue(abMsg.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
                }
                // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(xxDplyByCtrlAncrLvlCd) || NSAL0380Constant.MACH_LVL_NUM_3.equals(xxDplyByCtrlAncrLvlCd) || NSAL0380Constant.MACH_LVL_NUM_4.equals(xxDplyByCtrlAncrLvlCd)) {
                if (DS_CONTR_DTL_STS.TERMINATED.equals(dsContrStsCdDtl) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrStsCdDtl) || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrStsCdDtl)) {
                    cotrolInputField(abMsg, true);
                } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
                    cotrolFieldForFleet(abMsg);
                } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                    controlFieldForAggLine(abMsg, dsContrDtlTpCd);
                }

                if (ZYPCommonFunc.hasValue(abMsg.dsContrBaseUsgNm_A)) {
                    controlFieldForThirdLine(abMsg);
                    abMsg.xxChkBox_A1.setInputProtected(true);
                    // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
                    if (!DS_CONTR_DTL_STS.TERMINATED.equals(dsContrStsCdDtl) && !DS_CONTR_DTL_STS.EXPIRED.equals(dsContrStsCdDtl) && !DS_CONTR_DTL_STS.CANCELLED.equals(dsContrStsCdDtl)) {
                        ZYPEZDItemValueSetter.setValue(abMsg.xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
                    }
                    // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
                } else {
                    abMsg.xxChkBox_A2.setInputProtected(true);
                    // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
                    if (!DS_CONTR_DTL_STS.TERMINATED.equals(dsContrStsCdDtl) && !DS_CONTR_DTL_STS.EXPIRED.equals(dsContrStsCdDtl) && !DS_CONTR_DTL_STS.CANCELLED.equals(dsContrStsCdDtl)) {
                        ZYPEZDItemValueSetter.setValue(abMsg.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
                    }
                    // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
                }
            }
            // mod end 2016/04/06 CSA Defect#3049
            // mod start 2016/07/15 CSA Defect#8608
            controlFieldForTypeAndMethod(abMsg);
            // mod end 2016/07/15 CSA Defect#8608
        }
    }

    /**
     * control Field for Aggregate Line
     * @param scrnMsg NSAL0380BMsg
     * @param dsContrDtlTpCd String
     */
    private static void controlFieldForAggLine(NSAL0380_ABMsg abMsg, String dsContrDtlTpCd) {

        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            abMsg.basePrcUpRatio_A.setInputProtected(true);
        } else {
            abMsg.mtrPrcUpRatio_A.setInputProtected(true);
        }
    }

    /**
     * control Field for Third Line
     * @param scrnMsg NSAL0380BMsg
     */
    private static void controlFieldForThirdLine(NSAL0380_ABMsg abMsg) {

        abMsg.befEndRnwDaysAot_A.setInputProtected(true);
        abMsg.befEndRnwDaysAot_A.clear();
        abMsg.maxContrRnwCnt_A.setInputProtected(true);
        // START 2018/12/20 [QC#29636, DEL]
        // abMsg.maxPrcUpRatio_A.setInputProtected(true);
        // END 2018/12/20 [QC#29636, DEL]

        if (NSAL0380Constant.BASE_NM.equals(abMsg.dsContrBaseUsgNm_A.getValue())) {
            abMsg.mtrPrcUpRatio_A.setInputProtected(true);
            abMsg.mtrPrcUpRatio_A.clear();
        } else {
            abMsg.basePrcUpRatio_A.setInputProtected(true);
            abMsg.basePrcUpRatio_A.clear();
        }
    }

    // mod start 2016/07/15 CSA Defect#8608
    /**
     * control Field For Type And Method
     * @param abMsg NSAL0380_ABMsg
     */
    private static void controlFieldForTypeAndMethod(NSAL0380_ABMsg abMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(abMsg.rnwBaseFlg_A.getValue()) && !ZYPConstant.FLG_ON_Y.equals(abMsg.rnwUsgFlg_A.getValue())) {
            abMsg.rnwPrcMethCd_A.setInputProtected(true);
            abMsg.befEndRnwDaysAot_A.setInputProtected(true);
            abMsg.maxContrRnwCnt_A.setInputProtected(true);
            abMsg.maxPrcUpRatio_A.setInputProtected(true);
            abMsg.basePrcUpRatio_A.setInputProtected(true);
            abMsg.mtrPrcUpRatio_A.setInputProtected(true);
            abMsg.rnwPrcMethCd_A.clear();
            abMsg.befEndRnwDaysAot_A.clear();
            abMsg.maxContrRnwCnt_A.clear();
            abMsg.maxPrcUpRatio_A.clear();
            abMsg.basePrcUpRatio_A.clear();
            abMsg.mtrPrcUpRatio_A.clear();
        } else if (!ZYPCommonFunc.hasValue(abMsg.rnwPrcMethCd_A) || RNW_PRC_METH.MODEL_PERCENT.equals(abMsg.rnwPrcMethCd_A.getValue())) {
            abMsg.basePrcUpRatio_A.setInputProtected(true);
            abMsg.mtrPrcUpRatio_A.setInputProtected(true);
            abMsg.basePrcUpRatio_A.clear();
            abMsg.mtrPrcUpRatio_A.clear();
        } else if (!ZYPConstant.FLG_ON_Y.equals(abMsg.rnwBaseFlg_A.getValue())) {
            abMsg.basePrcUpRatio_A.setInputProtected(true);
            abMsg.basePrcUpRatio_A.clear();
        } else if (!ZYPConstant.FLG_ON_Y.equals(abMsg.rnwUsgFlg_A.getValue())) {
            abMsg.mtrPrcUpRatio_A.setInputProtected(true);
            abMsg.mtrPrcUpRatio_A.clear();
        }
    }
    // mod end 2016/07/15 CSA Defect#8608

    /**
     * control Inactive Detail Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0380BMsg
     */
    public static void inActiveDtlField(EZDCommonHandler scrnAppli, NSAL0380BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0380_ABMsg abMsg = scrnMsg.A.no(i);
            cotrolInputField(abMsg, true);
            abMsg.xxDsMsgEntryTxt_A.setInputProtected(true);
        }
    }

    /**
     * check Item
     * @param scrnMsg NSAL0380BMsg
     */
    public static final void checkItem(NSAL0380BMsg scrnMsg) {

        checkInputForDetail(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrAutoRnwTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rnwPrcMethCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).befEndRnwDaysAot_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).maxContrRnwCnt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).maxPrcUpRatio_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).basePrcUpRatio_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrPrcUpRatio_A);
        }
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
    }

    /**
     * check Item For Submit
     * @param scrnMsg NSAL0380BMsg
     */
    public static final void checkItemForSubmit(NSAL0380BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H);

        checkItem(scrnMsg);
    }

    /**
     * set Table's Back Color
     * @param scrnMsg NSAL0380BMsg
     */
    public static final void setTblBackColor(NSAL0380BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NSAL0380Constant.SCREEN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NSAL0380Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NSAL0380Bean.A, scrnMsg.A);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NSAL0380Constant.BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Renewal Rules contract/machine level(" + NSAL0380Constant.BUSINESS_ID + "). UserID is -> "
                    + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (NSAL0380Constant.AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * setTargetItem
     * @param scrnMsg NSAL0380BMsg
     * @param itemNm String
     */
    public static void setTargetItem(NSAL0380BMsg scrnMsg, String itemNm) {
        setValue(scrnMsg.xxScrEventNm_O, itemNm);
    }

    /**
     * setConfigSearchPopUpInputParam
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSAL0380BMsg
     * @return Object[]
     */
    public static Object[] setConfigSearchPopUpInputParam(EZDApplicationContext ctx, NSAL0380BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.setValidCount(0);

        Object[] params = new Object[NSAL0380Constant.PARAM_LENGTH_NSAL1240];

        setValue(scrnMsg.xxModeCd_O1, NSAL0380Constant.PARAM_CONFIG_EXST_MODE_CD_01);
        setValue(scrnMsg.xxEdtModeFlg_O, NSAL0380Constant.PARAM_SVC_MACH_MSTR_STS_EDIT_FLG_Y);
        setValue(scrnMsg.xxModeCd_O2, NSAL0380Constant.PARAM_MACH_ALLOC_MODE_CODE_01);
        setValue(scrnMsg.mainMachFlg_O, NSAL0380Constant.PARAM_MAIN_UNIT_FLG_Y);

        params[NSAL0380Constant.PARAM_INDEX_0]  = scrnMsg.xxModeCd_O1;
        params[NSAL0380Constant.PARAM_INDEX_10] = scrnMsg.xxEdtModeFlg_O;
        params[NSAL0380Constant.PARAM_INDEX_11] = scrnMsg.xxModeCd_O2;
        params[NSAL0380Constant.PARAM_INDEX_12] = scrnMsg.mainMachFlg_O;
        params[NSAL0380Constant.PARAM_INDEX_30] = scrnMsg.O;
        return params;
    }

    /**
     * setConfigSearchPopUpOutputParam
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSAL0380BMsg
     */
    public static void setConfigSearchPopUpOutputParam(EZDApplicationContext ctx, NSAL0380BMsg scrnMsg) {

        String scrEventNm = scrnMsg.xxScrEventNm_O.getValue();

        if (NSAL0380Constant.EVENT_NM_SER_NUM_FROM.equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).serNum_O)) {
                setValue(scrnMsg.serNum_HF, scrnMsg.O.no(0).serNum_O);
            }
        } else if (NSAL0380Constant.EVENT_NM_SER_NUM_THRU.equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).serNum_O)) {
                setValue(scrnMsg.serNum_HT, scrnMsg.O.no(0).serNum_O);
            }
        } else if (NSAL0380Constant.EVENT_NM_IB_ID_FROM.equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).svcMachMstrPk_O)) {
                setValue(scrnMsg.svcMachMstrPk_HF, scrnMsg.O.no(0).svcMachMstrPk_O);
            }
        } else if (NSAL0380Constant.EVENT_NM_IB_ID_THRU.equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).svcMachMstrPk_O)) {
                setValue(scrnMsg.svcMachMstrPk_HT, scrnMsg.O.no(0).svcMachMstrPk_O);
            }
        }
    }

    /**
     * check Input for Details
     * @param scrnMsg NSAL0380BMsg
     */
    public static void checkInputForDetail(NSAL0380BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0380_ABMsg acMsg = scrnMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(acMsg.xxChkBox_A1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(acMsg.xxChkBox_A2.getValue())) {

                checkRenewalMethodRequired(acMsg);
                checkDays(acMsg);
                checkMaxRenew(acMsg);

                if (NSAL0380Constant.MACH_LVL_NUM_1.equals(acMsg.xxDplyByCtrlAncrLvlCd_A.getValue())
                        || NSAL0380Constant.MACH_LVL_NUM_2.equals(acMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {
                    checkDaysRequired(acMsg);
                // del start 2019/06/26 QC#50931
                //} else {
                //    checkRDaysRequiredBaseUsage(scrnMsg.A, i);
                // del end 2019/06/26 QC#50931
                }
            }
        }
    }

    /**
     * check Input for Apply To All
     * @param scrnMsg NSAL0380BMsg
     */
    public static void checkInputForAppToAll(NSAL0380BMsg scrnMsg) {

        if (!checkNum(scrnMsg.befEndRnwDaysAot_H)) {
            scrnMsg.befEndRnwDaysAot_H.setErrorInfo(1, NSAL0380Constant.NSAM0193E, new String[] {NSAL0380Constant.ITEM_NM_BEF_END_RNW_DAYS_AOT_NEW });
        }

        if (!checkNum(scrnMsg.maxContrRnwCnt_H)) {
            scrnMsg.befEndRnwDaysAot_H.setErrorInfo(1, NSAL0380Constant.NSAM0193E, new String[] {NSAL0380Constant.ITEM_NM_MAX_CONTR_RNW_CNT_NEW });
        }
    }

    /**
     * check Required RenewalMethod
     * @param abMsg NSAL0380_ABMsg
     */
    private static void checkRenewalMethodRequired(NSAL0380_ABMsg abMsg) {

        if (!ZYPCommonFunc.hasValue(abMsg.contrAutoRnwTpCd_A)) {
            return;
        }
        if (!CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(abMsg.contrAutoRnwTpCd_A.getValue())
                && !ZYPCommonFunc.hasValue(abMsg.rnwPrcMethCd_A))  {
            abMsg.rnwPrcMethCd_A.setErrorInfo(1, NSAL0380Constant.NSAM0329E);
        }
    }

    /**
     * check Numeric Days#
     * @param abMsg NSAL0380_ABMsg
     */
    private static void checkDays(NSAL0380_ABMsg abMsg) {

        if (!checkNum(abMsg.befEndRnwDaysAot_A)) {
            abMsg.befEndRnwDaysAot_A.setErrorInfo(1, NSAL0380Constant.NSAM0193E, new String[] {NSAL0380Constant.ITEM_NM_BEF_END_RNW_DAYS_AOT });
        }
    }

    /**
     * check Negative Number
     * @param numItem EZDBBigDecimalItem
     */
    private static boolean checkNum(EZDBBigDecimalItem bigDecimalItem) {

        if (!ZYPCommonFunc.hasValue(bigDecimalItem)) {
            return true;
        }
        if (bigDecimalItem.getValueInt() < 0)  {
            return false;
        }
        return true;
    }

    /**
     * check Required Days#
     * @param abMsg NSAL0380_ABMsg
     */
    private static void checkDaysRequired(NSAL0380_ABMsg abMsg) {

        if (!ZYPCommonFunc.hasValue(abMsg.contrAutoRnwTpCd_A)) {
            return;
        }
        // START 2017/09/29 K.Kojima [QC#18378,MOD]
        // if ((CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(abMsg.contrAutoRnwTpCd_A.getValue())
        //         || CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_ONLY.equals(abMsg.contrAutoRnwTpCd_A.getValue()))
        //         && !ZYPCommonFunc.hasValue(abMsg.befEndRnwDaysAot_A))  {
        if (ZYPConstant.FLG_ON_Y.equals(abMsg.autoRnwFlg_A.getValue())
                && !ZYPCommonFunc.hasValue(abMsg.befEndRnwDaysAot_A))  {
            // END 2017/09/29 K.Kojima [QC#18378,MOD]
            abMsg.befEndRnwDaysAot_A.setErrorInfo(1, NSAL0380Constant.NSAM0330E);
        }
    }

    /**
     * check Required Days# (BASE/OVERAGE)
     * @param abMsgArry NSAL0380_ABMsgArray
     * @param index int
     */
    private static void checkRDaysRequiredBaseUsage(NSAL0380_ABMsgArray abMsgArry, int index) {

        NSAL0380_ABMsg acMsg = abMsgArry.no(index);

        // START 2017/09/29 K.Kojima [QC#18378,DEL]
        // if (!ZYPCommonFunc.hasValue(acMsg.contrAutoRnwTpCd_A)) {
        //     return;
        // }
        // 
        // String contrAutoRnwTpCd = acMsg.contrAutoRnwTpCd_A.getValue();
        // 
        // if (!CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(contrAutoRnwTpCd)
        //         && !CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_ONLY.equals(contrAutoRnwTpCd))  {
        //     return;
        // }
        // END 2017/09/29 K.Kojima [QC#18378,DEL]

        // START 2017/09/29 K.Kojima [QC#18378,ADD]
        if (!ZYPCommonFunc.hasValue(acMsg.autoRnwFlg_A.getValue())) {
            return;
        }
        if (ZYPConstant.FLG_OFF_N.equals(acMsg.autoRnwFlg_A.getValue()))  {
            return;
        }
        // END 2017/09/29 K.Kojima [QC#18378,ADD]

        boolean errFlg = false;
        int dsContrPk = acMsg.dsContrPk_A.getValueInt();
        int dsContrDtlPk = acMsg.dsContrDtlPk_A.getValueInt();
        int errIndex = -1;
        for (int i = 0; i < abMsgArry.getValidCount(); i++) {
            NSAL0380_ABMsg parrentAcMsg = abMsgArry.no(i);
            if (parrentAcMsg.dsContrPk_A.getValueInt() == dsContrPk && NSAL0380Constant.MACH_LVL_NUM_1.equals(parrentAcMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {

                errIndex = i;

                if (ZYPCommonFunc.hasValue(parrentAcMsg.befEndRnwDaysAot_A)) {
                    errFlg = true;
                    break;
                }
            } else if (parrentAcMsg.dsContrPk_A.getValueInt() == dsContrPk && NSAL0380Constant.MACH_LVL_NUM_2.equals(parrentAcMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {

                if (parrentAcMsg.dsContrDtlPk_A.getValueInt() != dsContrDtlPk) {
                    continue;
                }

                errIndex = i;

                if (ZYPCommonFunc.hasValue(parrentAcMsg.befEndRnwDaysAot_A)) {
                    errFlg = true;
                    break;
                }
            }
        }

        if (!errFlg) {
            acMsg.contrAutoRnwTpCd_A.setErrorInfo(1, NSAL0380Constant.NSAM0330E);
            if (errIndex != -1) {
                abMsgArry.no(errIndex).befEndRnwDaysAot_A.setErrorInfo(1, NSAL0380Constant.NSAM0330E);
            }
        }
    }

    /**
     * check Correlation Max# For Times Renewed
     * @param abMsg NSAL0380_ABMsg
     */
    private static void checkMaxRenew(NSAL0380_ABMsg abMsg) {

        if (!ZYPCommonFunc.hasValue(abMsg.maxContrRnwCnt_A)
                || !ZYPCommonFunc.hasValue(abMsg.contrRnwTotCnt_A)) {
            return;
        }
        if (abMsg.maxContrRnwCnt_A.getValueInt() < abMsg.contrRnwTotCnt_A.getValueInt())  {
            abMsg.befEndRnwDaysAot_A.setErrorInfo(1, NSAL0380Constant.NSAM0339E, new String[]{NSAL0380Constant.ITEM_NM_MAX_CONTR_RNW_CNT, NSAL0380Constant.ITEM_NM_CONTR_RNW_TOT_CNT });
        }
    }

    /**
     * clear All Select Header
     * @param scrnMsg NSAL0380_BMsg
     */
    public static void clearAllSelectHeader(NSAL0380BMsg scrnMsg) {

        scrnMsg.xxChkBox_H1.clear();
        scrnMsg.xxChkBox_H2.clear();
    }

    /**
     * get LowerLines
     * @param scrnMsg NSAL0380_BMsg
     * @param currentCnt int
     * @return List<NSAL0380_ABMsg>
     */
    public static List<NSAL0380_ABMsg> getLowerLines(NSAL0380BMsg scrnMsg, int currentCnt) {

        NSAL0380_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0380_ABMsg> lowerList = new ArrayList<NSAL0380_ABMsg>();
        for (int i = currentCnt + 1; i < scrnMsg.A.getValidCount(); i++) {
            if (abMsg.dsContrPk_A.getValueInt() != scrnMsg.A.no(i).dsContrPk_A.getValueInt()) {
                return lowerList;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A2.getValue())) {
                lowerList.add(scrnMsg.A.no(i));
            }
        }
        return lowerList;
    }

    /**
     * get LowerLines For Contract
     * @param scrnMsg NSAL0380_BMsg
     * @param currentCnt int
     * @return List<NSAL0380_ABMsg>
     */
    public static List<NSAL0380_ABMsg> getLowerLinesForContract(NSAL0380BMsg scrnMsg, int currentCnt) {

        NSAL0380_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0380_ABMsg> lowerList = new ArrayList<NSAL0380_ABMsg>();
        // START 2017/11/15 M.Kidokoro [QC#21928-1, ADD]
        if (NSAL0380Constant.MACH_LVL_NUM_3.equals(abMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {
            return lowerList;
        }
        // END 2017/11/15 M.Kidokoro [QC#21928-1, ADD]
        int i = currentCnt + 1;
        for (; i < scrnMsg.A.getValidCount(); i++) {
            if (abMsg.dsContrPk_A.getValueInt() != scrnMsg.A.no(i).dsContrPk_A.getValueInt()) {
                return lowerList;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A2.getValue())) {
                lowerList.add(scrnMsg.A.no(i));
            }
        }
        return lowerList;
    }

    /**
     * get LowerLines For SerNum
     * @param scrnMsg NSAL0380_BMsg
     * @param currentCnt int
     * @return List<NSAL0380_ABMsg>
     */
    public static List<NSAL0380_ABMsg> getLowerLinesForSer(NSAL0380BMsg scrnMsg, int currentCnt) {

        NSAL0380_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0380_ABMsg> lowerList = new ArrayList<NSAL0380_ABMsg>();
        for (int i = currentCnt + 1; i < scrnMsg.A.getValidCount(); i++) {
            if (abMsg.dsContrDtlPk_A.getValueInt() != scrnMsg.A.no(i).dsContrDtlPk_A.getValueInt()) {
                return lowerList;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A2.getValue())) {
                lowerList.add(scrnMsg.A.no(i));
            }
        }
        return lowerList;
    }

    /**
     * get LowerLines
     * @param scrnMsg NSAL0380_BMsg
     * @param currentCnt int
     * @return List<NSAL0380_ABMsg>
     */
    public static List<NSAL0380_ABMsg> getLowerLinesForAgg(NSAL0380BMsg scrnMsg, int currentCnt) {

        NSAL0380_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0380_ABMsg> lowerList = new ArrayList<NSAL0380_ABMsg>();
        for (int i = currentCnt + 1; i < scrnMsg.A.getValidCount(); i++) {
            if (abMsg.prntDsContrDtlPk_A.getValueInt() != scrnMsg.A.no(i).prntDsContrDtlPk_A.getValueInt()) {
                return lowerList;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A2.getValue())) {
                lowerList.add(scrnMsg.A.no(i));
            }
        }
        return lowerList;
    }
}