/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240.common;

import static business.blap.NWAL2240.constant.NWAL2240Constant.DEFAULT_TIME;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.AM_CD;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_ADD;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_APPLY;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_APPROVE;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_CLEAR;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_DELETE;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_DOWNLOAD;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_REJECT;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_RESET;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_RETURN;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_SAVE;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_CMN_BTN_SUBMIT;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_DELETE;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_SEARCH_ORDER;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BTN_TECH;
//import static business.servlet.NWAL2240.constant.NWAL2240Constant.CHK_EMAIL_PATTERN;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.CHK_EXT_PATTERN;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.CHK_PHONE_PATTERN;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.CHK_TIME_PATTERN;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.CLN;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.DECIMAL_DIGITS_NUM_ZERO;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.DERY_DISP_FLG_OFF;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.DERY_DISP_FLG_ON;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.EMAIL_FORMAT;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.EXT_FORMAT;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.MAX_CNT_CONTACT_LIST;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NOON;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0030E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0664E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0665E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0746E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0749E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0827E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.PM_CD;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TAB_CONTACT;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TAB_ENABLE_FLG_OFF;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TAB_INSTALL;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TAB_SURVEY;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TECHNICIAN_LABEL_NAME_DE_INS;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TECHNICIAN_LABEL_NAME_INS;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TEL_FORMAT;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TIME_CHAR_LEN;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TIME_FORMAT;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.ZZM9000E;

import java.util.LinkedHashSet;
import java.util.Set;

import parts.common.EZDBDateItem;
import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NWAL2240.NWAL2240CMsg;
import business.servlet.NWAL2240.NWAL2240BMsg;
import business.servlet.NWAL2240.NWAL2240_CBMsg;
import business.servlet.NWAL2240.NWAL2240_PBMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;

/**
 *<pre>
 * NWAL2240CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#5503
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#5535
 * 2016/07/28   Fujitsu         H.Ikeda         Update          QC#5030
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 * 2017/08/25   Fujitsu         S.Iidaka        Update          QC#20740-1
 * 2018/01/09   Fujitsu         N.Sugiura       Update          QC#18460
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 * 2019/11/09   Fujitsu         Mz.Takahashi    Update          QC#53993
 *</pre>
 */
public class NWAL2240CommonLogic {

    /**
     * <pre>
     * Initial Common Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPLY[0], BTN_CMN_BTN_APPLY[1], BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPROVE[0], BTN_CMN_BTN_APPROVE[1], BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_REJECT[0], BTN_CMN_BTN_REJECT[1], BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DOWNLOAD[0], BTN_CMN_BTN_DOWNLOAD[1], BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DELETE[0], BTN_CMN_BTN_DELETE[1], BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_CLEAR[0], BTN_CMN_BTN_CLEAR[1], BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RESET[0], BTN_CMN_BTN_RESET[1], BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RETURN[0], BTN_CMN_BTN_RETURN[1], BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * Control Contact List Button
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NWAL2240BMsg
     */
    public static void controlContactListBtn(EZDCommonHandler handler, NWAL2240BMsg scrnMsg) {

        if (scrnMsg.C.getValidCount() >= MAX_CNT_CONTACT_LIST) {
            handler.setButtonEnabled(BTN_ADD[0], false);
        } else {
            handler.setButtonEnabled(BTN_ADD[0], true);
        }

        if (scrnMsg.C.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_DELETE[0], false);
        } else {
            handler.setButtonEnabled(BTN_DELETE[0], true);
        }
    }

    /**
     * <pre>
     * Init Control Fields
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NWAL2240BMsg
     */
    public static void initControlFields(EZDCommonHandler handler, NWAL2240BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd_H0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd_H0, scrnMsg.configCatgCd_L0.no(0));
        }

        scrnMsg.techNm_DI.setInputProtected(true);
    }

    /**
     * <pre>
     * Control Elevator Available Flag
     * </pre>
     * @param scrnMsg NWAL2240BMsg
     */
    public static void controlElevAvalFlg(NWAL2240BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_SS) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.elevAvalFlg_SS.getValue())) {

            scrnMsg.elevAvalFromHourMn_SS.clear();
            scrnMsg.xxSvcFromHourMnTxt_EA.clear();
            // 2016/07/29 QC#5030 Mod Start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.elevAvalFromHourMn_AP, scrnMsg.xxTpCd.no(0));
            scrnMsg.elevAvalFromHourMn_AP.clear();
            // 2016/07/29 QC#5030 Mod End
            scrnMsg.elevAvalToHourMn_SS.clear();
            scrnMsg.xxSvcToHourMnTxt_EA.clear();
            // 2016/07/29 QC#5030 Mod Start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.elevAvalToHourMn_AP, scrnMsg.xxTpCd.no(0));
            scrnMsg.elevAvalToHourMn_AP.clear();
            // 2016/07/29 QC#5030 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.elevApptReqFlg_SS, ZYPConstant.FLG_OFF_N);
            scrnMsg.elevWdt_SS.clear();
            scrnMsg.elevDepthNum_SS.clear();
            scrnMsg.elevCapWt_SS.clear();
            scrnMsg.elevDoorHgt_SS.clear();
            scrnMsg.elevDoorWdt_SS.clear();

            scrnMsg.xxSvcFromHourMnTxt_EA.setInputProtected(true);
            scrnMsg.elevAvalFromHourMn_AP.setInputProtected(true);
            scrnMsg.xxSvcToHourMnTxt_EA.setInputProtected(true);
            scrnMsg.elevAvalToHourMn_AP.setInputProtected(true);
            scrnMsg.elevApptReqFlg_SS.setInputProtected(true);
            scrnMsg.elevWdt_SS.setInputProtected(true);
            scrnMsg.elevDepthNum_SS.setInputProtected(true);
            scrnMsg.elevCapWt_SS.setInputProtected(true);
            scrnMsg.elevDoorHgt_SS.setInputProtected(true);
            scrnMsg.elevDoorWdt_SS.setInputProtected(true);

        } else {
            scrnMsg.xxSvcFromHourMnTxt_EA.setInputProtected(false);
            scrnMsg.elevAvalFromHourMn_AP.setInputProtected(false);
            scrnMsg.xxSvcToHourMnTxt_EA.setInputProtected(false);
            scrnMsg.elevAvalToHourMn_AP.setInputProtected(false);
            scrnMsg.elevApptReqFlg_SS.setInputProtected(false);
            scrnMsg.elevWdt_SS.setInputProtected(false);
            scrnMsg.elevDepthNum_SS.setInputProtected(false);
            scrnMsg.elevCapWt_SS.setInputProtected(false);
            scrnMsg.elevDoorHgt_SS.setInputProtected(false);
            scrnMsg.elevDoorWdt_SS.setInputProtected(false);
        }
    }

    /**
     * <pre>
     * Control Elevator Appointment Needed Flag
     * </pre>
     * @param scrnMsg NWAL2240BMsg
     */
    public static void controlElevApptReqFlg(NWAL2240BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.elevApptReqFlg_SS) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.elevApptReqFlg_SS.getValue())) {
            scrnMsg.elevCtacPsnNm_SS.clear();
            scrnMsg.elevCtacTelNum_SS.clear();

            scrnMsg.elevCtacPsnNm_SS.setInputProtected(true);
            scrnMsg.elevCtacTelNum_SS.setInputProtected(true);
        } else {
            scrnMsg.elevCtacPsnNm_SS.setInputProtected(false);
            scrnMsg.elevCtacTelNum_SS.setInputProtected(false);
        }
    }

    /**
     * <pre>
     * Control Loading Dock
     * </pre>
     * @param scrnMsg NWAL2240BMsg
     */
    public static void controlLoadingDock(NWAL2240BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_SS) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.loadDockAvalFlg_SS.getValue())) {

            scrnMsg.loadDockAvalFromHourMn_SS.clear();
            scrnMsg.loadDockAvalToHourMn_SS.clear();
            scrnMsg.xxSvcFromHourMnTxt_LD.clear();
            scrnMsg.xxSvcToHourMnTxt_LD.clear();
            // 2016/07/29 QC#5030 Mod Start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.loadDockAvalFromHourMn_AP, scrnMsg.xxTpCd.no(0));
            // ZYPEZDItemValueSetter.setValue(scrnMsg.loadDockAvalToHourMn_AP, scrnMsg.xxTpCd.no(0));
            scrnMsg.loadDockAvalFromHourMn_AP.clear();
            scrnMsg.loadDockAvalToHourMn_AP.clear();
            // 2016/07/29 QC#5030 Mod End
            scrnMsg.xxSvcFromHourMnTxt_LD.setInputProtected(true);
            scrnMsg.xxSvcToHourMnTxt_LD.setInputProtected(true);
            scrnMsg.loadDockAvalFromHourMn_AP.setInputProtected(true);
            scrnMsg.loadDockAvalToHourMn_AP.setInputProtected(true);
        } else {

            scrnMsg.xxSvcFromHourMnTxt_LD.setInputProtected(false);
            scrnMsg.xxSvcToHourMnTxt_LD.setInputProtected(false);
            scrnMsg.loadDockAvalFromHourMn_AP.setInputProtected(false);
            scrnMsg.loadDockAvalToHourMn_AP.setInputProtected(false);
        }
    }

    /**
     * <pre>
     * To Array Pop Up
     * </pre>
     * @param p NWAL2240_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popup(NWAL2240_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }

    /**
     * <pre>
     * Conv Time For Screen
     * </pre>
     * @param scrnMsg NWAL2240BMsg
     * @param cMsg NWAL2240CMsg
     */
    public static void convTimeforScreen(NWAL2240BMsg scrnMsg, NWAL2240CMsg cMsg) {
        convTimetoShort(scrnMsg.rqstIstlTm_DI, scrnMsg.rqstIstlTm_AP);
        convTimetoShort(scrnMsg.opsFromHourMn_DI, scrnMsg.opsFromHourMn_AP);
        convTimetoShort(scrnMsg.opsToHourMn_DI, scrnMsg.opsToHourMn_AP);
        convTimetoShort(scrnMsg.loadDockAvalFromHourMn_SS, scrnMsg.loadDockAvalFromHourMn_AP);
        convTimetoShort(scrnMsg.loadDockAvalToHourMn_SS, scrnMsg.loadDockAvalToHourMn_AP);
        convTimetoShort(scrnMsg.carrDelyTmHourMn_SS, scrnMsg.carrDelyTmHourMn_AP);
        convTimetoShort(scrnMsg.elevAvalFromHourMn_SS, scrnMsg.elevAvalFromHourMn_AP);
        convTimetoShort(scrnMsg.elevAvalToHourMn_SS, scrnMsg.elevAvalToHourMn_AP);
    }

    private static void convTimetoShort(EZDBStringItem time, EZDBStringItem amPm) {
        if (ZYPCommonFunc.hasValue(time)) {
            // START 2016/04/27 K.Kojima [QC#5535,ADD]
            if (time.getValue().length() > 4) {
                ZYPEZDItemValueSetter.setValue(time, time.getValue().substring(0, 4));
            }
            // END 2016/04/27 K.Kojima [QC#5535,ADD]
            Integer convTime = Integer.parseInt(time.getValue()) - NOON;
            if (convTime >= 0) {
                ZYPEZDItemValueSetter.setValue(time, String.format("%04d", convTime));
                ZYPEZDItemValueSetter.setValue(amPm, PM_CD);

            } else {
                ZYPEZDItemValueSetter.setValue(amPm, AM_CD);
            }
        }
    }

    /**
     * Checking Phone Number
     */
    private static void checkPhoneNum(NWAL2240BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.elevCtacTelNum_SS)) {
            if (!scrnMsg.elevCtacTelNum_SS.getValue().matches(CHK_PHONE_PATTERN)) {
                scrnMsg.elevCtacTelNum_SS.setErrorInfo(1, NWAM0664E, new String[] {TEL_FORMAT });
            }
        }

        for (int idx = 0; idx < scrnMsg.C.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnTelNum_C0)) {
                if (!scrnMsg.C.no(idx).ctacPsnTelNum_C0.getValue().matches(CHK_PHONE_PATTERN)) {
                    scrnMsg.C.no(idx).ctacPsnTelNum_C0.setErrorInfo(1, NWAM0664E, new String[] {TEL_FORMAT });
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnFaxNum_C0)) {
                if (!scrnMsg.C.no(idx).ctacPsnFaxNum_C0.getValue().matches(CHK_PHONE_PATTERN)) {
                    scrnMsg.C.no(idx).ctacPsnFaxNum_C0.setErrorInfo(1, NWAM0664E, new String[] {TEL_FORMAT });
                }
            }

            // Del Start 2018/12/14 QC#24022
            //if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnExtnNum_C0)) {
            //    if (!scrnMsg.C.no(idx).ctacPsnExtnNum_C0.getValue().matches(CHK_EXT_PATTERN)) {
            //        scrnMsg.C.no(idx).ctacPsnExtnNum_C0.setErrorInfo(1, NWAM0664E, new String[] {EXT_FORMAT });
            //    }
            //}
            // Del End 2018/12/14 QC#24022
        }
    }

    /**
     * Checking EMail
     */
    private static void checkEMail(NWAL2240BMsg scrnMsg) {

        for (int idx = 0; idx < scrnMsg.C.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnEmlAddr_C0)) {
                // 2016/10/06 QC#1443 Add Start --------------
                boolean ret = NMXC106001CommonCheckUtil.checkEmailFormat(scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.getValue());

                if (!ret) {
                    scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                }
                // 2016/10/06 QC#1443 Add End --------------
                //if (!scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.getValue().matches(CHK_EMAIL_PATTERN)) {
                //    scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                //}
            }
        }
    }

    /**
     * Check Name Mandatory
     * @param scrnMsg NWAL2240BMsg
     */
    public static void checkNameMandatory(NWAL2240BMsg scrnMsg) {

        for (int idx = 0; idx < scrnMsg.C.getValidCount(); idx++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnFirstNm_C0)) {
                scrnMsg.C.no(idx).ctacPsnFirstNm_C0.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.C.no(idx).ctacPsnFirstNm_C0.getNameForMessage() });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnLastNm_C0)) {
                scrnMsg.C.no(idx).ctacPsnLastNm_C0.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.C.no(idx).ctacPsnLastNm_C0.getNameForMessage() });
            }
        }
    }

    /**
     * Checkng Time Item
     * @param scrnMsg NWAL2240BMsg
     */
    private static void checkTimeItem(NWAL2240BMsg scrnMsg) {
        // Delivery &Install Tab
        isTime(scrnMsg.rqstIstlTm_DI, scrnMsg.opsFromHourMn_DI, scrnMsg.opsToHourMn_DI);

        // Survey Tab
        isTime(scrnMsg.loadDockAvalFromHourMn_SS, scrnMsg.loadDockAvalToHourMn_SS, scrnMsg.carrDelyTmHourMn_SS, scrnMsg.elevAvalFromHourMn_SS, scrnMsg.elevAvalToHourMn_SS);
    }

    private static void isTime(EZDBStringItem... timeItems) {
        for (EZDBStringItem timeItem : timeItems) {
            if (ZYPCommonFunc.hasValue(timeItem)) {
                if (timeItem.getValue().length() < TIME_CHAR_LEN) {
                    timeItem.setErrorInfo(1, NWAM0664E, new String[] {TIME_FORMAT });
                }

                if (!timeItem.getValue().matches(CHK_TIME_PATTERN)) {
                    timeItem.setErrorInfo(1, NWAM0665E);
                }
            }
        }
    }

    /**
     * Checking Number Item
     * @param scrnMsg NWAL2240BMsg
     */
    private static void checkNumber(NWAL2240BMsg scrnMsg) {
        isNumber(scrnMsg.otsdStepNum_SS, scrnMsg.insdStepNum_SS, scrnMsg.flgtStairNum_SS);
    }

    private static void isNumber(EZDBStringItem... numItems) {
        for (EZDBStringItem numItem : numItems) {
            if (ZYPCommonFunc.hasValue(numItem)) {
                if (!numItem.getValue().matches("\\d+")) {
                    numItem.setErrorInfo(1, "ZZM9004E", new String[] {numItem.getNameForMessage() });
                }
            }
        }
    }

// 2017/08/25 S21_NA#20740-1 Del Start
//    /**
//     * Checking Date Item
//     * @param scrnMsg NWAL2240BMsg
//     */
//    private static void checkDate(NWAL2240BMsg scrnMsg) {
//        isDate(scrnMsg.rddDt_DI, scrnMsg.rqstIstlDt_DI);
//    }
//
//    private static void isDate(EZDBDateItem... dateItems) {
//        for (EZDBDateItem dateItem : dateItems) {
//            if (ZYPCommonFunc.hasValue(dateItem)) {
//                if (!ZYPDateUtil.isValidDate(dateItem.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
//                    dateItem.setErrorInfo(1, NWAM0664E, new String[] {ZYPDateUtil.TYPE_MMDDYYYY });
//                }
//            }
//        }
//    }
//
// 2017/08/25 S21_NA#20740-1 Del End
    /**
     * Check Format Item
     * @param scrnMsg NWAL2240BMsg
     */
    public static void checkFormatItem(NWAL2240BMsg scrnMsg) {
//        checkDate(scrnMsg); // 2017/08/25 S21_NA#20740-1 Del
        checkTimeItem(scrnMsg);
        checkPhoneNum(scrnMsg);
        checkEMail(scrnMsg);
        checkNumber(scrnMsg);
    }

    /**
     * Set Tab Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2240BMsg
     */
    public static void setTabProtect(EZDCommonHandler handler, NWAL2240BMsg scrnMsg) {

        boolean diProtectFlg = false;
        boolean ssProtectFlg = false;
        boolean coProtectFlg = false;

        if (TAB_ENABLE_FLG_OFF.equals(scrnMsg.xxRsltFlg_DI.getValue())) {
            diProtectFlg = true;
        }

        if (TAB_ENABLE_FLG_OFF.equals(scrnMsg.xxRsltFlg_SS.getValue())) {
            ssProtectFlg = true;
        }

        if (TAB_ENABLE_FLG_OFF.equals(scrnMsg.xxRsltFlg_CO.getValue())) {
            coProtectFlg = true;
        }

        // Delivery & Install
//        scrnMsg.rddDt_DI.setInputProtected(diProtectFlg); // 2017/08/25 S21_NA#20740-1 Del
        scrnMsg.delyAddlCmntTxt_DI.setInputProtected(diProtectFlg);
        scrnMsg.svcIstlRuleNum_DI.setInputProtected(diProtectFlg);
        scrnMsg.istlDivCd_DI.setInputProtected(diProtectFlg);
        scrnMsg.istlTechCd_DI.setInputProtected(diProtectFlg);
        // scrnMsg.rqstIstlDt_DI.setInputProtected(diProtectFlg);  // 2018/01/09 QC#18460 Del
        scrnMsg.xxSvcFromHourMnTxt_RQ.setInputProtected(diProtectFlg);
        scrnMsg.istlAddlCmntTxt_DI.setInputProtected(diProtectFlg);
        scrnMsg.xxSvcFromHourMnTxt_OP.setInputProtected(diProtectFlg);
        scrnMsg.xxSvcToHourMnTxt_OP.setInputProtected(diProtectFlg);
        scrnMsg.loadDockAvalFlg_DI.setInputProtected(diProtectFlg);
        scrnMsg.stairCrawReqFlg_DI.setInputProtected(diProtectFlg);
        scrnMsg.stairCrawNum_DI.setInputProtected(diProtectFlg);
        scrnMsg.elevAvalFlg_DI.setInputProtected(diProtectFlg);
        scrnMsg.opsFromHourMn_AP.setInputProtected(diProtectFlg);
        scrnMsg.opsToHourMn_AP.setInputProtected(diProtectFlg);
        scrnMsg.rqstIstlTm_AP.setInputProtected(diProtectFlg);
        scrnMsg.techNm_LK.setInputProtected(diProtectFlg);
        if (diProtectFlg) {
            scrnMsg.techNm_LK.clear();
        }

        // 2019/11/09 QC#53993 Add Start
        scrnMsg.istlBySvcPrvdPtyCd_DI.setInputProtected(diProtectFlg);
        scrnMsg.svcBySvcPrvdPtyCd_DI.setInputProtected(diProtectFlg);
        // 2019/11/09 QC#53993 Add End

        handler.setButtonEnabled(BTN_TECH[0], !diProtectFlg);

        // Site Survey
        scrnMsg.cmpyInfoAptBldgNm_SS.setInputProtected(ssProtectFlg);
        scrnMsg.cmpyInfoFlNm_SS.setInputProtected(ssProtectFlg);
        scrnMsg.cmpyInfoDeptNm_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevProtReqFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.siteSrvyAddlCmntTxt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.otsdStepNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.insdStepNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.stairCrawReqFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.flgtStairNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.stairAndLdgWdt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockAvalFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockHgt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcFromHourMnTxt_LD.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockAvalFromHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcToHourMnTxt_LD.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockAvalToHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.trctrAndTrailAccsFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcFromHourMnTxt_CD.setInputProtected(ssProtectFlg);
        scrnMsg.carrDelyTmHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.delyTrnspOptCd_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevAvalFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcFromHourMnTxt_EA.setInputProtected(ssProtectFlg);
        scrnMsg.elevAvalFromHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcToHourMnTxt_EA.setInputProtected(ssProtectFlg);
        scrnMsg.elevAvalToHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.elevApptReqFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevCtacPsnNm_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevCtacTelNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.bldgEntDoorHgt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.bldgEntDoorWdt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.crdrWdt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.doorWdt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevWdt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevDepthNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevCapWt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevDoorHgt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevDoorWdt_SS.setInputProtected(ssProtectFlg);

        // Contact
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL2240_CBMsg cBMsg = scrnMsg.C.no(i);

            cBMsg.ctacPsnTpCd_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnFirstNm_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnLastNm_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnTelNum_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnExtnNum_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnEmlAddr_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnFaxNum_C0.setInputProtected(coProtectFlg);
        }
        handler.setButtonEnabled(BTN_ADD[0], !coProtectFlg);
        handler.setButtonEnabled(BTN_DELETE[0], !coProtectFlg);

        if (!ssProtectFlg) {
            controlElevAvalFlg(scrnMsg);
            controlElevApptReqFlg(scrnMsg);
            controlLoadingDock(scrnMsg);
        }

        if (!coProtectFlg) {
            controlContactListBtn(handler, scrnMsg);
        }

        if (diProtectFlg && ssProtectFlg && coProtectFlg) {
            handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 0, null);
        }
    }

    /**
     * Set Delivery Install Display Flag
     * @param scrnMsg NWAL2240BMsg
     */
    public static void setDelyIstlDispFlg(NWAL2240BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_H0) && CONFIG_CATG.INBOUND.equals(scrnMsg.configCatgCd_H0.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_DI, DERY_DISP_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_LK, TECHNICIAN_LABEL_NAME_DE_INS);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_DI, DERY_DISP_FLG_ON);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_LK, TECHNICIAN_LABEL_NAME_INS);
        }
    }

    /**
     * Set Name For Message Delivery Display
     * @param scrnMsg NWAL2240BMsg
     */
    public static void setNameForMessageDeryDisp(NWAL2240BMsg scrnMsg) {

        if (DERY_DISP_FLG_OFF.equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {
//            scrnMsg.rddDt_DI.setNameForMessage("Pickup Date"); // 2017/08/25 S21_NA#20740-1 Del
            scrnMsg.delyAddlCmntTxt_DI.setNameForMessage("Pickup Instructions");
            scrnMsg.svcIstlRuleNum_DI.setNameForMessage("De-install Type");
            scrnMsg.istlDivCd_DI.setNameForMessage("De-install Division");
            scrnMsg.istlTechCd_DI.setNameForMessage("De-install Technician");
            // scrnMsg.rqstIstlDt_DI.setNameForMessage("De-install Date");  // 2018/01/09 QC#18460 Del
            scrnMsg.rqstIstlTm_DI.setNameForMessage("De-install Time");
            scrnMsg.istlAddlCmntTxt_DI.setNameForMessage("De-install Instructions");
            // 2019/11/9 QC#53993 Add Start
            scrnMsg.istlBySvcPrvdPtyCd_DI.setNameForMessage("To Be De-Installed By");
            scrnMsg.svcBySvcPrvdPtyCd_DI.setNameForMessage("");
            // 2019/11/9 QC#53993 Add End
        } else {
//            scrnMsg.rddDt_DI.setNameForMessage("Delivery Date"); // 2017/08/25 S21_NA#20740-1 Del
            scrnMsg.delyAddlCmntTxt_DI.setNameForMessage("Delivery Instructions");
            scrnMsg.svcIstlRuleNum_DI.setNameForMessage("Install Type");
            scrnMsg.istlDivCd_DI.setNameForMessage("Install Division");
            scrnMsg.istlTechCd_DI.setNameForMessage("Install Technician");
            // scrnMsg.rqstIstlDt_DI.setNameForMessage("Install Date");  // 2018/01/09 QC#18460 Del
            scrnMsg.rqstIstlTm_DI.setNameForMessage("Install Time");
            scrnMsg.istlAddlCmntTxt_DI.setNameForMessage("Install Instructions");
            // 2019/11/9 QC#53993 Add Start
            scrnMsg.istlBySvcPrvdPtyCd_DI.setNameForMessage("To Be Installed By");
            scrnMsg.svcBySvcPrvdPtyCd_DI.setNameForMessage("Service Provided By");
            // 2019/11/9 QC#53993 Add End
        }
    }

// 2017/08/25 S21_NA#20740-1 Del Start
//    /**
//     * Date Compare Check
//     * @param scrnMsg NWAL2240BMsg
//     */
//    public static void dlvyIstlDateCompareCheck(NWAL2240BMsg scrnMsg) {
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_DI)) {
//            if (ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue()).compareTo(scrnMsg.rddDt_DI.getValue()) > 0) {
//                scrnMsg.rddDt_DI.setErrorInfo(1, NWAM0030E);
//                return;
//            }
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_DI) && ZYPCommonFunc.hasValue(scrnMsg.rqstIstlDt_DI)) {
//
//            if (scrnMsg.rddDt_DI.getValue().compareTo(scrnMsg.rqstIstlDt_DI.getValue()) > 0) {
//                scrnMsg.rddDt_DI.setErrorInfo(1, NWAM0746E);
//                scrnMsg.rqstIstlDt_DI.setErrorInfo(1, NWAM0746E);
//                return;
//
//            } else if (scrnMsg.rddDt_DI.getValue().compareTo(scrnMsg.rqstIstlDt_DI.getValue()) == 0) {
//
//                if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_OP) && ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_RQ)) {
//                    timeFromToCompare(scrnMsg.xxSvcToHourMnTxt_OP, scrnMsg.opsToHourMn_AP, scrnMsg.xxSvcFromHourMnTxt_RQ, scrnMsg.rqstIstlTm_AP);
//                }
//            }
//        }
//    }
// 2017/08/25 S21_NA#20740-1 Del End

    /**
     * Time Compare Check
     * @param scrnMsg NWAL2240BMsg
     */
    public static void timeCompareCheck(NWAL2240BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_OP) && ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_OP)) {
            timeFromToCompare(scrnMsg.xxSvcFromHourMnTxt_OP, scrnMsg.opsFromHourMn_AP, scrnMsg.xxSvcToHourMnTxt_OP, scrnMsg.opsToHourMn_AP);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_LD) && ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_LD)) {
            timeFromToCompare(scrnMsg.xxSvcFromHourMnTxt_LD, scrnMsg.loadDockAvalFromHourMn_AP, scrnMsg.xxSvcToHourMnTxt_LD, scrnMsg.loadDockAvalToHourMn_AP);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_EA) && ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_EA)) {
            timeFromToCompare(scrnMsg.xxSvcFromHourMnTxt_EA, scrnMsg.elevAvalFromHourMn_AP, scrnMsg.xxSvcToHourMnTxt_EA, scrnMsg.elevAvalToHourMn_AP);
        }
    }

    /**
     * Time From To Compare
     * @param fromTime EZDBStringItem
     * @param fromAp EZDBStringItem
     * @param toTime EZDBStringItem
     * @param toAp EZDBStringItem
     */
    public static void timeFromToCompare(EZDBStringItem fromTime, EZDBStringItem fromAp, EZDBStringItem toTime, EZDBStringItem toAp) {

        if (fromAp.getValue().equals(PM_CD) && toAp.getValue().equals(AM_CD)) {

            fromAp.setErrorInfo(1, NWAM0749E);
            toAp.setErrorInfo(1, NWAM0749E);
            return;

        } else if (fromAp.getValue().equals(AM_CD) && toAp.getValue().equals(PM_CD)) {
            return;
        }

        if (getTm(fromTime.getValue()).compareTo(getTm(toTime.getValue())) > 0) {
            fromTime.setErrorInfo(1, NWAM0749E);
            toTime.setErrorInfo(1, NWAM0749E);
            return;
        }
    }

    /**
     * <pre>
     * Set App Frac Digit
     * </pre>
     * @param scrnMsg NWAL2240BMsg
     */
    public static void setAppFracDigit(NWAL2240BMsg scrnMsg) {

        scrnMsg.loadDockHgt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.stairAndLdgWdt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.bldgEntDoorHgt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.bldgEntDoorWdt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.crdrWdt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.doorWdt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.elevWdt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.elevDepthNum_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.elevCapWt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.elevDoorHgt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        scrnMsg.elevDoorWdt_SS.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).inPoundWt_B0.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
            scrnMsg.B.no(i).inInchLg_B0.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
            scrnMsg.B.no(i).inInchWdt_B0.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
            scrnMsg.B.no(i).inInchHgt_B0.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
            scrnMsg.B.no(i).xxInInchDgnlNum_B0.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
        }
    }

    /**
     * <pre>
     * Add Check Item BizLayer Error
     * </pre>
     * @param scrnMsg NWAL2240BMsg
     */
    public static void addCheckItemBizLayerErr(NWAL2240BMsg scrnMsg) {

        // ----------------------------------------------
        // Header
        // ----------------------------------------------
        scrnMsg.addCheckItem(scrnMsg.ordSrcRefNum_H0);
        scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum_H0);
        scrnMsg.addCheckItem(scrnMsg.configCatgCd_H0);

        String origxxDplyTab = scrnMsg.xxDplyTab.getValue();
        scrnMsg.xxDplyTab.clear();
        Set<EZDBItem> itemList = new LinkedHashSet<EZDBItem>();

        // ----------------------------------------------
        // Delivery&Install Tab
        // ----------------------------------------------
        itemList.add(scrnMsg.dsOrdPosnNum_H0);
        itemList.add(scrnMsg.xxSvcFromHourMnTxt_RQ);
        itemList.add(scrnMsg.xxSvcFromHourMnTxt_OP);
        itemList.add(scrnMsg.xxSvcToHourMnTxt_OP);
        itemList.add(scrnMsg.opsFromHourMn_AP);
        itemList.add(scrnMsg.opsToHourMn_AP);
        // itemList.add(scrnMsg.rqstIstlDt_DI);  // 2018/01/09 QC#18460 Del
//        itemList.add(scrnMsg.rddDt_DI); // 2017/08/25 S21_NA#20740-1 Del
        itemList.add(scrnMsg.delyAddlCmntTxt_DI);
        itemList.add(scrnMsg.istlAddlCmntTxt_DI);
        itemList.add(scrnMsg.stairCrawNum_DI);
        itemList.add(scrnMsg.istlTechCd_DI);
        // S21_NA#5030 Add start
        itemList.add(scrnMsg.loadDockAvalFlg_DI);
        itemList.add(scrnMsg.stairCrawReqFlg_DI);
        itemList.add(scrnMsg.elevAvalFlg_DI);
        // S21_NA#5030 Add End

        for (EZDBItem item : itemList) {
            scrnMsg.addCheckItem(item);
            if (item.isError()) {
                scrnMsg.xxDplyTab.setValue(TAB_INSTALL);
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            // ----------------------------------------------
            // SiteServey Tab
            // ----------------------------------------------
            itemList.add(scrnMsg.xxSvcFromHourMnTxt_LD);
            itemList.add(scrnMsg.xxSvcToHourMnTxt_LD);
            itemList.add(scrnMsg.loadDockAvalFromHourMn_AP);
            itemList.add(scrnMsg.loadDockAvalToHourMn_AP);
            itemList.add(scrnMsg.xxSvcFromHourMnTxt_CD);
            itemList.add(scrnMsg.xxSvcFromHourMnTxt_EA);
            itemList.add(scrnMsg.xxSvcToHourMnTxt_EA);
            itemList.add(scrnMsg.elevAvalFromHourMn_AP);
            itemList.add(scrnMsg.elevAvalToHourMn_AP);
            itemList.add(scrnMsg.siteSrvyAddlCmntTxt_SS);
            itemList.add(scrnMsg.elevCtacTelNum_SS);
            itemList.add(scrnMsg.cmpyInfoAptBldgNm_SS);
            itemList.add(scrnMsg.cmpyInfoFlNm_SS);
            itemList.add(scrnMsg.cmpyInfoDeptNm_SS);
            itemList.add(scrnMsg.otsdStepNum_SS);
            itemList.add(scrnMsg.insdStepNum_SS);
            itemList.add(scrnMsg.flgtStairNum_SS);
            itemList.add(scrnMsg.loadDockHgt_SS);
            itemList.add(scrnMsg.stairAndLdgWdt_SS);
            itemList.add(scrnMsg.delyTrnspOptCd_SS);
            itemList.add(scrnMsg.elevCtacPsnNm_SS);
            itemList.add(scrnMsg.bldgEntDoorHgt_SS);
            itemList.add(scrnMsg.bldgEntDoorWdt_SS);
            itemList.add(scrnMsg.crdrWdt_SS);
            itemList.add(scrnMsg.doorWdt_SS);
            itemList.add(scrnMsg.elevWdt_SS);
            itemList.add(scrnMsg.elevDepthNum_SS);
            itemList.add(scrnMsg.elevCapWt_SS);
            itemList.add(scrnMsg.elevDoorHgt_SS);
            itemList.add(scrnMsg.elevDoorWdt_SS);
            // S21_NA#5030 Add start
            itemList.add(scrnMsg.elevProtReqFlg_SS);
            itemList.add(scrnMsg.stairCrawReqFlg_SS);
            itemList.add(scrnMsg.loadDockAvalFlg_SS);
            itemList.add(scrnMsg.trctrAndTrailAccsFlg_SS);
            itemList.add(scrnMsg.elevAvalFlg_SS);
            itemList.add(scrnMsg.elevApptReqFlg_SS);
            // S21_NA#5030 Add End

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_SURVEY);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            // ----------------------------------------------
            // Contact Tab
            // ----------------------------------------------
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                itemList.add(scrnMsg.C.no(i).ctacPsnFirstNm_C0);
                itemList.add(scrnMsg.C.no(i).ctacPsnLastNm_C0);
                itemList.add(scrnMsg.C.no(i).ctacPsnTelNum_C0);
                itemList.add(scrnMsg.C.no(i).ctacPsnFaxNum_C0);
                itemList.add(scrnMsg.C.no(i).ctacPsnExtnNum_C0);
                itemList.add(scrnMsg.C.no(i).ctacPsnEmlAddr_C0);
            }

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_CONTACT);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            scrnMsg.xxDplyTab.setValue(origxxDplyTab);
        }
    }

    /**
     * set Scrn Tm
     * @param scrnMsg NWAL2240BMsg
     */
    public static void setScrnTm(NWAL2240BMsg scrnMsg) {

        scrnMsg.xxSvcFromHourMnTxt_OP.setValue(setTm(scrnMsg.opsFromHourMn_DI.getValue()));
        scrnMsg.xxSvcToHourMnTxt_OP.setValue(setTm(scrnMsg.opsToHourMn_DI.getValue()));
        scrnMsg.xxSvcFromHourMnTxt_RQ.setValue(setTm(scrnMsg.rqstIstlTm_DI.getValue()));
        scrnMsg.xxSvcFromHourMnTxt_LD.setValue(setTm(scrnMsg.loadDockAvalFromHourMn_SS.getValue()));
        scrnMsg.xxSvcToHourMnTxt_LD.setValue(setTm(scrnMsg.loadDockAvalToHourMn_SS.getValue()));
        scrnMsg.xxSvcFromHourMnTxt_CD.setValue(setTm(scrnMsg.carrDelyTmHourMn_SS.getValue()));
        scrnMsg.xxSvcFromHourMnTxt_EA.setValue(setTm(scrnMsg.elevAvalFromHourMn_SS.getValue()));
        scrnMsg.xxSvcToHourMnTxt_EA.setValue(setTm(scrnMsg.elevAvalToHourMn_SS.getValue()));
    }

    /**
     * get Scrn Tm
     * @param scrnMsg NWAL2240BMsg
     */
    public static void getScrnTm(NWAL2240BMsg scrnMsg) {

        scrnMsg.opsFromHourMn_DI.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_OP.getValue()));
        scrnMsg.opsToHourMn_DI.setValue(getTm(scrnMsg.xxSvcToHourMnTxt_OP.getValue()));
        scrnMsg.rqstIstlTm_DI.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_RQ.getValue()));
        scrnMsg.loadDockAvalFromHourMn_SS.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_LD.getValue()));
        scrnMsg.loadDockAvalToHourMn_SS.setValue(getTm(scrnMsg.xxSvcToHourMnTxt_LD.getValue()));
        scrnMsg.carrDelyTmHourMn_SS.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_CD.getValue()));
        scrnMsg.elevAvalFromHourMn_SS.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_EA.getValue()));
        scrnMsg.elevAvalToHourMn_SS.setValue(getTm(scrnMsg.xxSvcToHourMnTxt_EA.getValue()));
    }

    /**
     * set Time
     * @param tm time
     * @return time
     */
    public static String setTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            // START 2016/04/26 K.Kojima [QC#5535,ADD]
            if (tm.length() > 4) {
                tm = tm.substring(0, 4);
            }
            // END 2016/04/26 K.Kojima [QC#5535,ADD]
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(tm);
            if (strBuf.length() > 2) {
                strBuf.insert(2, CLN);
            }
            return strBuf.toString();
        }
        return tm;
    }

    /**
     * get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(CLN, "");
        }
        return tm;
    }

    // START 2016/04/26 K.Kojima [QC#5503,ADD]
    /**
     * allDisabled
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2240BMsg
     */
    public static void searchOrderDisabled(EZDCommonHandler handler, NWAL2240BMsg scrnMsg) {
        scrnMsg.ordSrcRefNum_H0.setInputProtected(true);
        scrnMsg.dsOrdPosnNum_H0.setInputProtected(true);
        scrnMsg.configCatgCd_H0.setInputProtected(true);
        handler.setButtonEnabled(BTN_SEARCH_ORDER[0], false);
        handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_CLEAR[0], BTN_CMN_BTN_CLEAR[1], BTN_CMN_BTN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_RESET[0], BTN_CMN_BTN_RESET[1], BTN_CMN_BTN_RESET[2], 0, null);
    }
    // END 2016/04/26 K.Kojima [QC#5503,ADD]

    // S21_NA#5030 Add start
    /**
     * Checking RadioButton
     * @param scrnMsg NWAL2240BMsg
     */
    public static void checkRadioButton(NWAL2240BMsg scrnMsg) {
        String tabTp = scrnMsg.xxDplyTab.getValue();
        if (TAB_INSTALL.equals(tabTp)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_DI)) {
                scrnMsg.loadDockAvalFlg_DI.setErrorInfo(1, NWAM0827E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.stairCrawReqFlg_DI)) {
                scrnMsg.stairCrawReqFlg_DI.setErrorInfo(1, NWAM0827E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_DI)) {
            scrnMsg.elevAvalFlg_DI.setErrorInfo(1, NWAM0827E);
            }
        } else if (TAB_SURVEY.equals(tabTp)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevProtReqFlg_SS)) {
                scrnMsg.elevProtReqFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.stairCrawReqFlg_SS)) {
                scrnMsg.stairCrawReqFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_SS)) {
                scrnMsg.loadDockAvalFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.trctrAndTrailAccsFlg_SS)) {
                scrnMsg.trctrAndTrailAccsFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_SS)) {
                scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevApptReqFlg_SS)) {
                scrnMsg.elevApptReqFlg_SS.setErrorInfo(1, NWAM0827E);
            }
        }
    }
    // S21_NA#5030 Add End
}
