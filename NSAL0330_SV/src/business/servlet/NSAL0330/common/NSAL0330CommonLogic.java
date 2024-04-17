/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0330.common;

import static business.servlet.NSAL0330.constant.NSAL0330Constant.SCREEN_ID;

import java.util.List;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0330.NSAL0330BMsg;
import business.servlet.NSAL0330.NSAL0330_ABMsg;
import business.servlet.NSAL0330.constant.NSAL0330Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/21   Hitachi         T.Tomita        Update          N/A
 * 2016/04/14   Hitachi         T.Kanasaka      Update          QC#4960
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#1759
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/05   Hitachi         K.Kishimoto     Update          QC#12879
 * 2017/12/11   Hitachi         U.Kim           Update          QC#18779
 * 2018/05/11   Hitachi         K.Kim           Update          QC#25897
 * 2018/07/17   Hitachi         K.Kishimoto     Update          QC#25959
 *</pre>
 */
public class NSAL0330CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0330BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0330BMsg scrnMsg, String userId) {
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg, userId);
        // ADD start 2016/04/22 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/22 CSA Defect#1759
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0330BMsg
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0330BMsg scrnMsg) {
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_SAVE[0], NSAL0330Constant.BTN_CMN_SAVE[1], NSAL0330Constant.BTN_CMN_SAVE[2], 1, null);
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_SUBMIT[0], NSAL0330Constant.BTN_CMN_SUBMIT[1], NSAL0330Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_APPLY[0], NSAL0330Constant.BTN_CMN_APPLY[1], NSAL0330Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_APPROVE[0], NSAL0330Constant.BTN_CMN_APPROVE[1], NSAL0330Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_REJECT[0], NSAL0330Constant.BTN_CMN_REJECT[1], NSAL0330Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_BLANK6[0], NSAL0330Constant.BTN_CMN_BLANK6[1], NSAL0330Constant.BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_BLANK7[0], NSAL0330Constant.BTN_CMN_BLANK7[1], NSAL0330Constant.BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_CLEAR[0], NSAL0330Constant.BTN_CMN_CLEAR[1], NSAL0330Constant.BTN_CMN_CLEAR[2], 0, null);
        // START 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_RESET[0], NSAL0330Constant.BTN_CMN_RESET[1], NSAL0330Constant.BTN_CMN_RESET[2], 1, null);
        // END 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(NSAL0330Constant.BTN_CMN_RETURN[0], NSAL0330Constant.BTN_CMN_RETURN[1], NSAL0330Constant.BTN_CMN_RETURN[2], 1, null);

        if (!hasUpdate(userProfileService)) {
            handler.setButtonProperties(NSAL0330Constant.BTN_CMN_SAVE[0], NSAL0330Constant.BTN_CMN_SAVE[1], NSAL0330Constant.BTN_CMN_SAVE[2], 0, null);
        }
    }

    /**
     * Control screen fields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0330BMsg
     * @param userId String
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0330BMsg scrnMsg, String userId) {
        controlScreenHeaderFields(userProfileService, handler, scrnMsg, userId);
        controlScreenDetailFields(userProfileService, handler, scrnMsg, userId);
    }

    /**
     * controlScreenHeaderFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0330BMsg
     * @param userId String
     */
    private static final void controlScreenHeaderFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0330BMsg scrnMsg, String userId) {

        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
        // START 2015/10/21 T.Tomita [N/A, DEL]
//        scrnMsg.t_MdlNm_H1.setInputProtected(true);
//        scrnMsg.baseBillToCustCd_H1.setInputProtected(true);
        // END 2015/10/21 T.Tomita [N/A, DEL]

        scrnMsg.contrEffFromDt_H1.setInputProtected(true);
        scrnMsg.contrEffThruDt_H1.setInputProtected(true);
        // START 2017/12/11 U.Kim [QC#18779, MOD]
        //scrnMsg.baseBllgTmgCd_H1.setInputProtected(false);
        scrnMsg.baseBllgTmgCd_H1.setInputProtected(true);
        // END 2017/12/11 U.Kim [QC#18779, MOD]
        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.xxRadioBtn_H1.setInputProtected(false);
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        scrnMsg.baseDplyPerEndDay_H1.setInputProtected(false);
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

        scrnMsg.baseBllgLastBllgDt_H1.setInputProtected(true);
        scrnMsg.invTotAmt_H1.setInputProtected(true);
        scrnMsg.ccyCd_H1.setInputProtected(true);
        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.xxRadioBtn_H2.setInputProtected(false);
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
        scrnMsg.contrBllgDay_H1.setInputProtected(false);

        scrnMsg.svcMemoRsnCd_F3.setInputProtected(false);
        scrnMsg.svcCmntTxt_F1.setInputProtected(false);

        scrnMsg.invTotAmt_H1.setAppFracDigit(2);

        handler.setButtonEnabled(NSAL0330Constant.BTN_SELECT_ALL[0], true);
        handler.setButtonEnabled(NSAL0330Constant.BTN_UN_SELECT_ALL[0], true);
        handler.setButtonEnabled(NSAL0330Constant.BTN_INSERT_ROW[0], true);
        handler.setButtonEnabled(NSAL0330Constant.BTN_DELETE_ROW[0], true);
        handler.setButtonEnabled(NSAL0330Constant.BTN_SCHEDULES[0], true);
        handler.setButtonEnabled(NSAL0330Constant.BTN_SKIP_MONTH[0], true);

        // START 2015/10/21 T.Tomita [N/A, MOD]
        // START 2018/05/11 K.Kim [QC#25897, MOD]
        // START 2018/07/17 [QC#25959, MOD]
        if (!hasUpdate(userProfileService) || NSAL0330Constant.REF_MODE.equals(scrnMsg.xxModeCd_H1.getValue()) || !isAggMachine(scrnMsg)) {
        // END   2018/07/17 [QC#25959, MOD]
        // END 2018/05/11 K.Kim [QC#25897, MOD]
            handler.setButtonEnabled(NSAL0330Constant.BTN_SELECT_ALL[0], false);
            handler.setButtonEnabled(NSAL0330Constant.BTN_UN_SELECT_ALL[0], false);
            handler.setButtonEnabled(NSAL0330Constant.BTN_INSERT_ROW[0], false);
            handler.setButtonEnabled(NSAL0330Constant.BTN_DELETE_ROW[0], false);

            scrnMsg.baseBllgTmgCd_H1.setInputProtected(true);
            // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H1.setInputProtected(true);
            // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
            // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
            scrnMsg.baseDplyPerEndDay_H1.setInputProtected(true);
            // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
            // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H2.setInputProtected(true);
            // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
            scrnMsg.contrBllgDay_H1.setInputProtected(true);

            scrnMsg.svcMemoRsnCd_F3.setInputProtected(true);
            scrnMsg.svcCmntTxt_F1.setInputProtected(true);
        // START 2016/08/05 K.Kishimoto [QC#12879, ADD]
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.invFlg_H1.getValue())) {
            scrnMsg.baseBllgTmgCd_H1.setInputProtected(true);
            scrnMsg.baseDplyPerEndDay_H1.setInputProtected(true);
            scrnMsg.contrBllgDay_H1.setInputProtected(true);
        // END 2016/08/05 K.Kishimoto [QC#12879, ADD]
        // START 2016/04/14 T.Kanasaka [QC#4960, ADD]
        } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.invSeptBaseUsgFlg_H1.getValue())) {
            // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H1.setInputProtected(true);
            // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
            // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
            scrnMsg.baseDplyPerEndDay_H1.setInputProtected(true);
            // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
            // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H2.setInputProtected(true);
            // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
            scrnMsg.contrBllgDay_H1.setInputProtected(true);
        // END 2016/04/14 T.Kanasaka [QC#4960, ADD]
        // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
        } else if (ZYPCommonFunc.hasValue(scrnMsg.bllgCycleStartMth_H1)) {
            // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H1.setInputProtected(true);
            // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
            scrnMsg.baseDplyPerEndDay_H1.setInputProtected(true);
        // END 2016/05/18 T.Kanasaka [QC#2184, ADD]
        }
        // END 2015/10/21 T.Tomita [N/A, MOD]
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0330BMsg
     * @param userId String
     */
    private static final void controlScreenDetailFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0330BMsg scrnMsg, String userId) {
        // START 2015/10/21 T.Tomita [N/A, MOD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2018/05/11 K.Kim [QC#25897, MOD]
            // START 2018/07/17 [QC#25959, MOD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).invFlg_A1.getValue()) || !hasUpdate(userProfileService) || NSAL0330Constant.REF_MODE.equals(scrnMsg.xxModeCd_H1.getValue()) || !isAggMachine(scrnMsg)) {
            // END   2018/07/17 [QC#25959, MOD]
            // END 2018/05/11 K.Kim [QC#25897, MOD]
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsContrBllgSchdSqNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsContrPrcEffSqNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).perSchdNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).perBllgCycleCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).bllgSchdFromDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).bllgSchdThruDt_A1.setInputProtected(true);
//                scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(true);
                scrnMsg.A.no(i).bllgCycleDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAdjAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).baseSubTotPrcDealAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).ccyCd_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).dsContrBllgSchdSqNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsContrPrcEffSqNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).perSchdNum_A1.setInputProtected(false);
                scrnMsg.A.no(i).perBllgCycleCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).bllgSchdFromDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).bllgSchdThruDt_A1.setInputProtected(false);
//                scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(false);
                scrnMsg.A.no(i).bllgCycleDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAdjAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).baseSubTotPrcDealAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).ccyCd_A1.setInputProtected(true);
            }

            scrnMsg.A.no(i).basePrcDealAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).basePrcDealAdjAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).baseSubTotPrcDealAmt_A1.setAppFracDigit(2);
        }
        // END 2015/10/21 T.Tomita [N/A, MOD]
    }

    // ADD start 2016/04/22 CSA Defect#1759
    /**
     * protectedAllHeaderFields
     * @param NSAL0330BMsg scrnMsg
     */
    private static void setRowColors(NSAL0330BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // ADD end 2016/04/22 CSA Defect#1759

    // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//    /**
//     * protectedAllHeaderFields
//     * @param NSAL0330BMsg scrnMsg
//     */
//    private static final void protectedAllHeaderFields(NSAL0330BMsg scrnMsg) {
//
//        scrnMsg.dsContrNum_H1.setInputProtected(true);
//        scrnMsg.serNum_H1.setInputProtected(true);
//        // START 2015/10/21 T.Tomita [N/A, DEL]
////        scrnMsg.t_MdlNm_H1.setInputProtected(true);
////        scrnMsg.baseBillToCustCd_H1.setInputProtected(true);
//        // END 2015/10/21 T.Tomita [N/A, DEL]
//
//        scrnMsg.contrEffFromDt_H1.setInputProtected(true);
//        scrnMsg.contrEffThruDt_H1.setInputProtected(true);
//        scrnMsg.baseBllgTmgCd_H1.setInputProtected(true);
//        scrnMsg.xxRadioBtn_H1.setInputProtected(true);
//        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
//        scrnMsg.baseDplyPerEndDay_H1.setInputProtected(true);
//        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
//
//        scrnMsg.baseBllgLastBllgDt_H1.setInputProtected(true);
//        scrnMsg.invTotAmt_H1.setInputProtected(true);
//        scrnMsg.ccyCd_H1.setInputProtected(true);
//        scrnMsg.xxRadioBtn_H2.setInputProtected(true);
//        scrnMsg.contrBllgDay_H1.setInputProtected(true);
//
//        scrnMsg.svcMemoRsnCd_F3.setInputProtected(true);
//        scrnMsg.svcCmntTxt_F1.setInputProtected(true);
//    }
    // END 2016/08/05 K.Kishimoto [QC#12879, DEL]

    /**
     * <pre>
     * Check addCheckItem return UPDATE
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NSAL0330BMsg scrnMsg) {
        addHeaderCheckItem(scrnMsg);
        addDetailCheckItem(scrnMsg);
    }

    /**
     * addHeaderCheckItem
     * @param scrnMsg NSAL0330BMsg
     */
    public static void addHeaderCheckItem(NSAL0330BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.baseBllgTmgCd_H1);
        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_H1);
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        scrnMsg.addCheckItem(scrnMsg.baseDplyPerEndDay_H1);
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_H2);
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
        scrnMsg.addCheckItem(scrnMsg.contrBllgDay_H1);

        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_F3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_F1);
    }

    /**
     * addDetailCheckItem
     * @param scrnMsg NSAL0330BMsg
     */
    public static void addDetailCheckItem(NSAL0330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0330_ABMsg abMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(abMsg.perSchdNum_A1);
            scrnMsg.addCheckItem(abMsg.perBllgCycleCd_A1);
            scrnMsg.addCheckItem(abMsg.bllgSchdFromDt_A1);
            scrnMsg.addCheckItem(abMsg.bllgSchdThruDt_A1);
            // START 2015/10/21 T.Tomita [N/A, MOD]
//            scrnMsg.addCheckItem(abMsg.bllgCycleCd_A3);
            scrnMsg.addCheckItem(abMsg.bllgCycleDescTxt_A1);
            // END 2015/10/21 T.Tomita [N/A, MOD]
            scrnMsg.addCheckItem(abMsg.basePrcDealAmt_A1);
        }
    }

    /**
     * inputCheck
     * @param scrnMsg NSAL0330BMsg
     */
    public static final void inputCheck(NSAL0330BMsg scrnMsg) {

        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        checkClosingDay(scrnMsg);
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]

        checkSchdDt(scrnMsg);

        addCheckItem(scrnMsg);
    }

    /**
     * inputCheck
     * @param scrnMsg NSAL0330BMsg
     */
    public static void checkSchdDt(NSAL0330BMsg scrnMsg) {
        int size = scrnMsg.A.getValidCount();
        for (int i = 0; i < size; i++) {
            NSAL0330_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(abMsg.bllgSchdFromDt_A1) && ZYPCommonFunc.hasValue(abMsg.bllgSchdThruDt_A1)) {
                if (abMsg.bllgSchdThruDt_A1.getValue().compareTo(abMsg.bllgSchdFromDt_A1.getValue()) < 0) {
                    abMsg.bllgSchdThruDt_A1.setErrorInfo(1, NSAL0330Constant.NSAM0339E);
                }
            }
        }
    }

    // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//    // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
//    /**
//     * checkClosingDay
//     * @param scrnMsg NSAL0330BMsg
//     */
//    public static final void checkClosingDay(NSAL0330BMsg scrnMsg) {
//        if (NSAL0330Constant.RADIO_VALUE_CLOSING_DAY.compareTo(scrnMsg.xxRadioBtn_H1.getValue()) == 0) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.baseDplyPerEndDay_H1)) {
//                try {
//                    int cloDay = Integer.parseInt(scrnMsg.baseDplyPerEndDay_H1.getValue());
//                    if (cloDay < 0 || cloDay > 27) {
//                        scrnMsg.baseDplyPerEndDay_H1.setErrorInfo(1, NSAL0330Constant.NSAM0194E, new String[] {"0", "27" });
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
//    // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
    // END 2016/08/05 K.Kishimoto [QC#12879, DEL]

    private static boolean hasUpdate(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NSAL0330Constant.BUSINESS_ID);
        if (functionIds.contains(NSAL0330Constant.FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }
    // START 2018/05/11 K.Kim [QC#25897, ADD]
    /**
     * @param scrnMsg NSAL0330BMsg
     * @return boolean
     */
    public static boolean isAggMachine(NSAL0330BMsg scrnMsg) {

        if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd_H1.getValue())) {
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.dsContrDtlTpCd_H1.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END 2018/05/11 K.Kim [QC#25897, ADD]
}
