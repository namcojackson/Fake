/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0360.common;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0360.NSAL0360BMsg;
import business.servlet.NSAL0360.constant.NSAL0360Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2015/10/23   Hitach          T.Tomita        Update          N/A
 * 2015/12/09   Hitachi         K.Yamada        Update          CSA QC#1662
 * 2016/03/16   Hitachi         T.Tomita        Update          CSA QC#5247
 * 2016/04/14   Hitachi         T.Kanasaka      Update          QC#4960
 * 2016/04/27   Hitachi         T.Iwamoto       Update          QC#1759
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/04   Hitach          K.Kishimoto     Update          QC#12878
 * 2016/08/08   Hitachi         K.Kishimoto     Update          QC#12879
 *</pre>
 */
public class NSAL0360CommonLogic {

    /**
     * <pre>
	 * The initial state of the screen item is set.
	 * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0360BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0360BMsg scrnMsg, String userId) {
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg, userId);
        // ADD start 2016/04/27 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/27 CSA Defect#1759
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0360BMsg
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0360BMsg scrnMsg) {
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_SAVE[0], NSAL0360Constant.BTN_CMN_SAVE[1], NSAL0360Constant.BTN_CMN_SAVE[2], 1, null);
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_SUBMIT[0], NSAL0360Constant.BTN_CMN_SUBMIT[1], NSAL0360Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_APPLY[0], NSAL0360Constant.BTN_CMN_APPLY[1], NSAL0360Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_APPROVE[0], NSAL0360Constant.BTN_CMN_APPROVE[1], NSAL0360Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_REJECT[0], NSAL0360Constant.BTN_CMN_REJECT[1], NSAL0360Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_BLANK6[0], NSAL0360Constant.BTN_CMN_BLANK6[1], NSAL0360Constant.BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_BLANK7[0], NSAL0360Constant.BTN_CMN_BLANK7[1], NSAL0360Constant.BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_CLEAR[0], NSAL0360Constant.BTN_CMN_CLEAR[1], NSAL0360Constant.BTN_CMN_CLEAR[2], 0, null);
        // START 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_RESET[0], NSAL0360Constant.BTN_CMN_RESET[1], NSAL0360Constant.BTN_CMN_RESET[2], 1, null);
        // END 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(NSAL0360Constant.BTN_CMN_RETURN[0], NSAL0360Constant.BTN_CMN_RETURN[1], NSAL0360Constant.BTN_CMN_RETURN[2], 1, null);

        // START 2015/10/23 T.Tomita [N/A, MOD]
        if (!hasUpdate(userProfileService) || NSAL0360Constant.REF_MODE.equals(scrnMsg.xxModeCd_HD.getValue())) {
            handler.setButtonProperties(NSAL0360Constant.BTN_CMN_SAVE[0], NSAL0360Constant.BTN_CMN_SAVE[1], NSAL0360Constant.BTN_CMN_SAVE[2], 0, null);
        }
        // END 2015/10/23 T.Tomita [N/A, MOD]
    }

    /**
     * Control screen fields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0360BMsg
     * @param userId String
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0360BMsg scrnMsg, String userId) {
        controlScreenHeaderFields(userProfileService, handler, scrnMsg, userId);
        controlScreenDetailFields(userProfileService, handler, scrnMsg, userId);
    }

    /**
     * controlScreenHeaderFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0360BMsg
     * @param userId String
     */
    private static final void controlScreenHeaderFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0360BMsg scrnMsg, String userId) {
        // START 2015/10/23 T.Tomita [N/A, MOD]
        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
//        scrnMsg.t_MdlNm_H1.setInputProtected(true);

        scrnMsg.contrEffFromDt_H1.setInputProtected(true);
        scrnMsg.contrEffThruDt_H1.setInputProtected(true);
        // START 2016/08/04 K.Kishimoto [QC#12878, MOD]
//        scrnMsg.mtrBllgTmgCd_H1.setInputProtected(false);
        scrnMsg.mtrBllgTmgCd_H1.setInputProtected(true);
        // END 2016/08/04 K.Kishimoto [QC#12878, MOD]
        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.xxRadioBtn_H1.setInputProtected(false);
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        scrnMsg.mtrDplyPerEndDay_H1.setInputProtected(false);
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

        scrnMsg.mtrBllgLastBllgDt_H1.setInputProtected(true);
//        scrnMsg.xsChrgTpCd_H3.setInputProtected(false);
        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.xxRadioBtn_H2.setInputProtected(false);
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
        scrnMsg.mtrBllgDay_H1.setInputProtected(false);

        scrnMsg.svcMemoRsnCd_F3.setInputProtected(false);
        scrnMsg.svcCmntTxt_F1.setInputProtected(false);

        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            NSAL0360CommonLogic.getStringValueFromEZDMsg(scrnMsg, tblNm, "mtrLbNm_A").setInputProtected(true);
//            NSAL0360CommonLogic.getStringValueFromEZDMsg(scrnMsg, tblNm, "bllgMtrBillToCustCd_A").setInputProtected(true);
        }

        handler.setButtonEnabled(NSAL0360Constant.BTN_SELECT_ALL[0], true);
        handler.setButtonEnabled(NSAL0360Constant.BTN_UN_SELECT_ALL[0], true);
        handler.setButtonEnabled(NSAL0360Constant.BTN_INSERT_ROW[0], true);
        handler.setButtonEnabled(NSAL0360Constant.BTN_DELETE_ROW[0], true);
        handler.setButtonEnabled(NSAL0360Constant.BTN_SCHEDULES[0], true);
        handler.setButtonEnabled(NSAL0360Constant.BTN_SKIP_MONTH[0], true);

        if (!hasUpdate(userProfileService) || NSAL0360Constant.REF_MODE.equals(scrnMsg.xxModeCd_HD.getValue())) {
            handler.setButtonEnabled(NSAL0360Constant.BTN_SELECT_ALL[0], false);
            handler.setButtonEnabled(NSAL0360Constant.BTN_UN_SELECT_ALL[0], false);
            handler.setButtonEnabled(NSAL0360Constant.BTN_INSERT_ROW[0], false);
            handler.setButtonEnabled(NSAL0360Constant.BTN_DELETE_ROW[0], false);
//            handler.setButtonEnabled(NSAL0360Constant.BTN_SCHEDULES[0], false);

            // START 2016/08/04 K.Kishimoto [QC#12878, DEL]
//            scrnMsg.mtrBllgTmgCd_H1.setInputProtected(true);
            // END 2016/08/04 K.Kishimoto [QC#12878, DEL]
//            scrnMsg.xsChrgTpCd_H3.setInputProtected(true);
            // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H1.setInputProtected(true);
            // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
            // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
            scrnMsg.mtrDplyPerEndDay_H1.setInputProtected(true);
            // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
            // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H2.setInputProtected(true);
            // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
            scrnMsg.mtrBllgDay_H1.setInputProtected(true);

            scrnMsg.svcMemoRsnCd_F3.setInputProtected(true);
            scrnMsg.svcCmntTxt_F1.setInputProtected(true);
        // START 2016/08/08 K.Kishimoto [QC#12879, ADD]
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.invFlg_HH.getValue())) {
            scrnMsg.mtrDplyPerEndDay_H1.setInputProtected(true);
            scrnMsg.mtrBllgDay_H1.setInputProtected(true);
        // END 2016/08/08 K.Kishimoto [QC#12879, ADD]
        // START 2016/04/14 T.Kanasaka [QC#4960, ADD]
        } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.invSeptBaseUsgFlg_H1.getValue())) {
            // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H1.setInputProtected(true);
            // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
            // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
            scrnMsg.mtrDplyPerEndDay_H1.setInputProtected(true);
            // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
            // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//            scrnMsg.xxRadioBtn_H2.setInputProtected(true);
            // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
            scrnMsg.mtrBllgDay_H1.setInputProtected(true);
        // END 2016/04/14 T.Kanasaka [QC#4960, ADD]
        // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
        } else {
            boolean endDayProtected = false;
            outfor:
            for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
                EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);

                for (int i = 0; i < tblArray.getValidCount(); i++) {
                    EZDMsg ezdMsg = tblArray.get(i);

                    if (ZYPCommonFunc.hasValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleStartMth_A1"))) {
                        endDayProtected = true;
                        break outfor;
                    }
                }
            }

            if (endDayProtected) {
                // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//                scrnMsg.xxRadioBtn_H1.setInputProtected(true);
                // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
                scrnMsg.mtrDplyPerEndDay_H1.setInputProtected(true);
            }
        // END 2016/05/18 T.Kanasaka [QC#2184, ADD]
        }
        // END 2015/10/23 T.Tomita [N/A, MOD]
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0360BMsg
     * @param userId String
     */
    private static final void controlScreenDetailFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0360BMsg scrnMsg, String userId) {
        // START 2015/10/23 T.Tomita [N/A, MOD]
        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);

            for (int i = 0; i < tblArray.getValidCount(); i++) {
                EZDMsg ezdMsg = tblArray.get(i);
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "mtrLbNm_A1").setInputProtected(true);
//                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgMtrBillToCustCd_A1").setInputProtected(true);
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1").setInputProtected(true);
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1").setInputProtected(true);
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleDescTxt_A1").setInputProtected(true);
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1").setInputProtected(true);
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_AS").setInputProtected(true);
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1").setInputProtected(true);
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ccyCd_A1").setInputProtected(true);

                String invFlg = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "invFlg_A1").getValue();
                if (ZYPConstant.FLG_ON_Y.equals(invFlg) || !hasUpdate(userProfileService) || NSAL0360Constant.REF_MODE.equals(scrnMsg.xxModeCd_HD.getValue())) {
                    NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1").setInputProtected(true);
                    NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1").setInputProtected(true);
                    NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1").setInputProtected(true);
                    NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1").setInputProtected(true);
                    NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1").setInputProtected(true);
                } else {
                    NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1").setInputProtected(false);
                    NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1").setInputProtected(false);
                    NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1").setInputProtected(false);
                    NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1").setInputProtected(false);
                    NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1").setInputProtected(false);
                }
                // mod start 2015/12/09 CSA Defect#1662
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1").setAppFracDigit(6);
                // mod end 2015/12/09 CSA Defect#1662
            }
        }
        // END 2015/10/23 T.Tomita [N/A, MOD]
    }

    // ADD start 2016/04/27 CSA Defect#1759
    /**
     * set Row Colors
     * @param NSAL0360BMsg scrnMsg
     */
    private static void setRowColors(NSAL0360BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(NSAL0360Constant.SCREEN_ID, scrnMsg);
        try {
            for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
                EZDBMsgArray tblArray = (EZDBMsgArray) NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
                if (tblArray.getValidCount() > 0) {
                    tblColor.setAlternateRowsBG(tblNm, tblArray);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // ADD end 2016/04/27 CSA Defect#1759

    /**
     * <pre>
	 * Check addCheckItem return UPDATE
	 * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NSAL0360BMsg scrnMsg) {
        addHeaderCheckItem(scrnMsg);
        addDetailCheckItem(scrnMsg);
    }

    /**
     * addHeaderCheckItem
     * @param scrnMsg NSAL0360BMsg
     */
    public static void addHeaderCheckItem(NSAL0360BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mtrBllgTmgCd_H1);
        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_H1);
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        scrnMsg.addCheckItem(scrnMsg.mtrDplyPerEndDay_H1);
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_H2);
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
        scrnMsg.addCheckItem(scrnMsg.mtrBllgDay_H1);

        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_F3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_F1);
    }

    /**
     * addDetailCheckItem
     * @param scrnMsg NSAL0360BMsg
     */
    public static void addDetailCheckItem(NSAL0360BMsg scrnMsg) {
        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
            for (int i = 0; i < tblArray.getValidCount(); i++) {
                EZDMsg ezdMsg = tblArray.get(i);
                scrnMsg.addCheckItem(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"));
                scrnMsg.addCheckItem(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"));
                scrnMsg.addCheckItem(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"));
                scrnMsg.addCheckItem(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"));
                // START 2015/10/23 T.Tomita [N/A, DELL]
//                scrnMsg.addCheckItem(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleDescTxt_A1"));
//                scrnMsg.addCheckItem(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1"));
//                scrnMsg.addCheckItem(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1"));
                // END 2015/10/23 T.Tomita [N/A, DELL]
            }
        }
    }

    /**
     * inputCheck
     * @param scrnMsg NSAL0360BMsg
     */
    public static final void inputCheck(NSAL0360BMsg scrnMsg) {

        setListInputItem(scrnMsg);

        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        checkClosingDay(scrnMsg);
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]

        checkSchdDt(scrnMsg);

        addCheckItem(scrnMsg);
    }

    /**
     * inputCheck
     * @param scrnMsg NSAL0360BMsg
     */
    public static void checkSchdDt(NSAL0360BMsg scrnMsg) {

        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);

            for (int i = 0; i < tblArray.getValidCount(); i++) {
                EZDMsg ezdMsg = tblArray.get(i);

                EZDBDateItem bllgSchdFromDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1");
                EZDBDateItem bllgSchdThruDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1");

                if (ZYPCommonFunc.hasValue(bllgSchdFromDt) && ZYPCommonFunc.hasValue(bllgSchdThruDt)) {
                    if (bllgSchdThruDt.getValue().compareTo(bllgSchdFromDt.getValue()) < 0) {
                        bllgSchdThruDt.setErrorInfo(1, NSAL0360Constant.NSAM0339E);
                    }
                }
            }

        }
    }

    // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//    // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
//    /**
//     * checkClosingDay
//     * @param scrnMsg NSAL0360BMsg
//     */
//    public static final void checkClosingDay(NSAL0360BMsg scrnMsg) {
//        if (NSAL0360Constant.RADIO_VALUE_CLOSING_DAY.compareTo(scrnMsg.xxRadioBtn_H1.getValue()) == 0) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.mtrDplyPerEndDay_H1)) {
//                try {
//                    int cloDay = Integer.parseInt(scrnMsg.mtrDplyPerEndDay_H1.getValue());
//                    if (cloDay < 0 || cloDay > 27) {
//                        scrnMsg.mtrDplyPerEndDay_H1.setErrorInfo(1, NSAL0360Constant.NSAM0194E, new String[] {"0", "27" });
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
//    // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
    // END 2016/08/08 K.Kishimoto [QC#12879, DEL]

    private static boolean hasUpdate(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NSAL0360Constant.BUSINESS_ID);
        if (functionIds.contains(NSAL0360Constant.FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }

    public static EZDBStringItem getStringValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDBStringItem result = null;
        try {
            result = (EZDBStringItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDBBigDecimalItem getBigDecimalValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDBBigDecimalItem result = null;
        try {
            result = (EZDBBigDecimalItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDBDateItem getDateValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDBDateItem result = null;
        try {
            result = (EZDBDateItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDMsgArray getTableArrayFromEZDMsg(NSAL0360BMsg scrnMsg, String tblNm) {
        EZDMsgArray result = null;
        try {
            result = (EZDMsgArray) scrnMsg.getClass().getField(tblNm).get(scrnMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String replSfx(String str, String tblNm) {
        return str.replaceAll("_.", "_" + tblNm);
    }

    // START 2015/10/23 T.Tomita [N/A, ADD]
    private static void setListInputItem(NSAL0360BMsg scrnMsg) {
        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
            setSameSqInputItem(tblArray, tblNm);
        }
    }

    // START 2016/03/16 T.Tomita [QC#5247, MOD]
    private static void setSameSqInputItem(EZDMsgArray tblArray, String tblNm) {
        String preSchdSqNum = "";
        String schdSqNum = "";
        BigDecimal prePeSqNum = null;
        BigDecimal peSqNum = null;
        // input Item
        BigDecimal perSchdNum = null;
        String perBllgCycleCd = null;
        String bllgSchdFromDt = null;
        String bllgSchdThruDt = null;
        for (int i = 0; i < tblArray.getValidCount(); i++) {
            schdSqNum = getStringValueFromEZDMsg(tblArray.get(i), tblNm, "dsContrBllgSchdSqNum_A1").getValue();
            peSqNum = getBigDecimalValueFromEZDMsg(tblArray.get(i), tblNm, "dsContrPrcEffSqNum_A1").getValue();
            if (preSchdSqNum.equals(schdSqNum) && ZYPCommonFunc.hasValue(prePeSqNum) && prePeSqNum.compareTo(peSqNum) == 0) {
                // equals Schedule Sequence
                ZYPEZDItemValueSetter.setValue(getBigDecimalValueFromEZDMsg(tblArray.get(i), tblNm, "perSchdNum_A1"), perSchdNum);
                ZYPEZDItemValueSetter.setValue(getStringValueFromEZDMsg(tblArray.get(i), tblNm, "perBllgCycleCd_A1"), perBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdFromDt_A1"), bllgSchdFromDt);
                ZYPEZDItemValueSetter.setValue(getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdThruDt_A1"), bllgSchdThruDt);
            } else {
                preSchdSqNum = schdSqNum;
                prePeSqNum = peSqNum;
                perSchdNum = getBigDecimalValueFromEZDMsg(tblArray.get(i), tblNm, "perSchdNum_A1").getValue();
                perBllgCycleCd = getStringValueFromEZDMsg(tblArray.get(i), tblNm, "perBllgCycleCd_A1").getValue();
                bllgSchdFromDt = getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdFromDt_A1").getValue();
                bllgSchdThruDt = getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdThruDt_A1").getValue();
            }
        }
    }
    // END 2016/03/16 T.Tomita [QC#5247, MOD]
    // END 2015/10/23 T.Tomita [N/A, ADD]
}
