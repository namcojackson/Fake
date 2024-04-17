/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NWAL1510.common;

import static business.servlet.NWAL1510.constant.NWAL1510Constant.AM_CD;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_ADD;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_ADD_CTAC_EVENT_NM;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_APPLY;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_APPROVE;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_CLEAR;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_DELETE;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_DOWNLOAD;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_REJECT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_RESET;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_RETURN;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_SAVE;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_CMN_BTN_SUBMIT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_DELETE;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_DEL_CTAC_EVENT_NM;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BTN_TECH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.CHK_TIME_PATTERN;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.COLON;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.COMMA;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.DECIMAL_DIGITS_NUM_ZERO;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.DERY_DISP_FLG_OFF;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.DERY_DISP_FLG_ON;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.EMAIL_FORMAT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.EXT_MAX_LENGTH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.HYPHEN;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.MAX_CNT_CONTACT_LIST;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NMAM8190E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NOON;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0186E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0664E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0665E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0749E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0761E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0763E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0773E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0827E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0832E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0869E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0906E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0964E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.PERIOD;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.PM_CD;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_0;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_1;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_2;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_3;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_4;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_5;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_6;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_7;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.REF_AUTH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.SLASH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.SPACE;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_CONTACT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_ENABLE_FLG_OFF;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_INSTALL;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_SURVEY;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TECHNICIAN_LABEL_NAME_DE_INS;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TECHNICIAN_LABEL_NAME_INS;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TIME_FORMAT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.ZZBM0084E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDBDateItem;
import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCommonConst;
import parts.common.EZDFieldErrorException;
import parts.common.EZDItemAttribute;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NWAL1510.NWAL1510CMsg;
import business.db.DS_SITE_SRVYTMsg;
import business.servlet.NWAL1510.NWAL1510BMsg;
import business.servlet.NWAL1510.NWAL1510_CBMsg;
import business.servlet.NWAL1510.NWAL1510_PBMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         S.Ohki          Create          N/A
 * 2015/11/26   Fujitsu         T.Ishii         Update          S21_NA#1147
 * 2016/02/09   Fujitsu         S.Ohki          Update          S21_NA#1622
 * 2016/05/13   Fujitsu         M.suzuki        Update          S21_NA#7088
 * 2016/06/06   Fujitsu         S.Ohki          Update          S21_NA#7088
 * 2016/07/11   Fujitsu         H.Ikeda         Update          S21_NA#5030
 * 2016/09/20   Fujitsu         R.Nakamura      Update          QC#13738
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 * 2016/10/21   Fujitsu         S.Takami        Update          S21_NA#15475
 * 2016/11/04   Fujitsu         M.Ohno          Update          S21_NA#15686
 * 2016/11/07   Fujitsu         Y.Kanefusa      Update          S21_NA#14143
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2017/06/28   Fujitsu         M.Yamada        Update          S21_NA#19610
 * 2017/07/19   Fujitsu         S.Takami        Update          S21_NA#20014
 * 2017/08/09   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/1/18    CITS            T.Hakodate      Update          S21_NA#18460(Sol#087)
 * 2018/1/31    CITS            T.Hakodate      Update          S21_NA#18460(Sol#087)
 * 2018/07/18   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          S21_NA#24022
 * 2019/10/25   Fujitsu         Mz.Takahashi    Update          S21_NA#53993
 * 2019/12/20   Fujitsu         S.Kosaka        Update          QC#54725
 * 2020/02/10   Fujitsu         C.Hara          Update          QC#54725-1
 * 
 *</pre>
 */
public class NWAL1510CommonLogic {

    /**
     * <pre>
     * Initial Common Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPLY[0], BTN_CMN_BTN_APPLY[1], BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPROVE[0], BTN_CMN_BTN_APPROVE[1], BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_REJECT[0], BTN_CMN_BTN_REJECT[1], BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DOWNLOAD[0], BTN_CMN_BTN_DOWNLOAD[1], BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DELETE[0], BTN_CMN_BTN_DELETE[1], BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_CLEAR[0], BTN_CMN_BTN_CLEAR[1], BTN_CMN_BTN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_RESET[0], BTN_CMN_BTN_RESET[1], BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RETURN[0], BTN_CMN_BTN_RETURN[1], BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * Control Contact List Button
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NWAL1510BMsg
     */
    public static void controlContactListBtn(EZDCommonHandler handler, NWAL1510BMsg scrnMsg) {

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
     * @param scrnMsg NWAL1510BMsg
     */
    public static void initControlFields(EZDCommonHandler handler, NWAL1510BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd_H0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd_H0, scrnMsg.configCatgCd_L0.no(0));
        }

        scrnMsg.techNm_DI.setInputProtected(true);
    }

    /**
     * <pre>
     * Control Elevator Available Flag
     * </pre>
     * @param scrnMsg NWAL1510BMsg
     */
    public static void controlElevAvalFlg(NWAL1510BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_SS) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.elevAvalFlg_SS.getValue())) {

            scrnMsg.elevAvalFromHourMn_SS.clear();
            scrnMsg.xxSvcFromHourMnTxt_EA.clear();
            // 2016/07/11 S21_NA#5030 mod start
            ZYPEZDItemValueSetter.setValue(scrnMsg.elevAvalFromHourMn_AP, scrnMsg.xxTpCd.no(0));
            scrnMsg.elevAvalFromHourMn_AP.clear();
            // 2016/07/11 S21_NA#5030 mod end
            scrnMsg.elevAvalToHourMn_SS.clear();
            scrnMsg.xxSvcToHourMnTxt_EA.clear();
            // 2016/07/11 S21_NA#5030 mod start
            ZYPEZDItemValueSetter.setValue(scrnMsg.elevAvalToHourMn_AP, scrnMsg.xxTpCd.no(0));
            scrnMsg.elevAvalToHourMn_AP.clear();
            // 2016/07/11 S21_NA#5030 mod end
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
     * @param scrnMsg NWAL1510BMsg
     */
    public static void controlElevApptReqFlg(NWAL1510BMsg scrnMsg) {

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
     * @param scrnMsg NWAL1510BMsg
     */
    public static void controlLoadingDock(NWAL1510BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_SS) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.loadDockAvalFlg_SS.getValue())) {

            scrnMsg.loadDockAvalFromHourMn_SS.clear();
            scrnMsg.loadDockAvalToHourMn_SS.clear();
            scrnMsg.xxSvcFromHourMnTxt_LD.clear();
            scrnMsg.xxSvcToHourMnTxt_LD.clear();
            // 2016/07/11 S21_NA#5030 mod start
            // S21_NA#873 modify start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.loadDockAvalFromHourMn_SS,
            // scrnMsg.xxTpCd.no(0));
            //ZYPEZDItemValueSetter.setValue(scrnMsg.loadDockAvalFromHourMn_AP, scrnMsg.xxTpCd.no(0));
            scrnMsg.loadDockAvalFromHourMn_AP.clear();
            // S21_NA#873 modify end
            ZYPEZDItemValueSetter.setValue(scrnMsg.loadDockAvalToHourMn_AP, scrnMsg.xxTpCd.no(0));
            scrnMsg.loadDockAvalToHourMn_AP.clear();
            // 2016/07/11 S21_NA#5030 mod End
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
     * @param p NWAL1510_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popup(NWAL1510_PBMsgArray p, int size) {
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
     * @param scrnMsg NWAL1510BMsg
     * @param cMsg NWAL1510CMsg
     */
    public static void convTimeforScreen(NWAL1510BMsg scrnMsg, NWAL1510CMsg cMsg) {
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
            Integer convTime = Integer.parseInt(time.getValue()) - NOON;
            if (convTime >= 0) {
                ZYPEZDItemValueSetter.setValue(time, String.format("%04d", convTime));
                ZYPEZDItemValueSetter.setValue(amPm, PM_CD);

            } else {
                // 2017/07/19 S21_NA#20014 Mod Start
//                ZYPEZDItemValueSetter.setValue(amPm, AM_CD);
                if (!ZYPCommonFunc.hasValue(amPm)) {
                    ZYPEZDItemValueSetter.setValue(amPm, AM_CD);
                }
                // 2017/07/19 S21_NA#20014 Mod End
            }
        } else { // 2017/07/19 S21_NA#20014 Add Start
            amPm.setValue("");
        } // 2017/07/19 S21_NA#20014 Add End
    }

    /**
     * Checking Required
     * @param scrnMsg NWAL1510BMsg
     */
    public static void checkRequired(NWAL1510BMsg scrnMsg) {
        // 2018/07/18 S21_NA#26188 Add Tab Condition 
        String tabTp = scrnMsg.xxDplyTab.getValue();
        boolean massApplyMode = ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_MU.getValue()); // 2018/07/18 S21_NA#26188 Add
        if (TAB_INSTALL.equals(tabTp)) {
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.stairCrawReqFlg_DI.getValue())) { // 2018/07/18 S21_NA#26188 Mod Condition
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.stairCrawReqFlg_DI.getValue()) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_D2.getValue()) || !massApplyMode)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.stairCrawNum_DI)) {
                    scrnMsg.stairCrawReqFlg_DI.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_DI.getNameForMessage(), scrnMsg.stairCrawNum_DI.getNameForMessage() });
                    scrnMsg.stairCrawNum_DI.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_DI.getNameForMessage(), scrnMsg.stairCrawNum_DI.getNameForMessage() });
                }
            }
        } else if (TAB_SURVEY.equals(tabTp)) { // 2018/07/18 S21_NA#26188 Add Tab Condition 
            boolean allEdtMode = ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxEdtModeFlg_SS.getValue()); // 2018/07/19 S21_NA#26188 Add
            // /2016/05/13 S21_NA#7088 Add Start ------------
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.stairCrawReqFlg_SS.getValue())) { // 2018/07/19 S21_NA#26188 Mod Condition
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.stairCrawReqFlg_SS.getValue()) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_S4.getValue()) || !massApplyMode || allEdtMode)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.otsdStepNum_SS)) {
                    scrnMsg.stairCrawReqFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.otsdStepNum_SS.getNameForMessage() });
                    scrnMsg.otsdStepNum_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.otsdStepNum_SS.getNameForMessage() });
                }

                if (!ZYPCommonFunc.hasValue(scrnMsg.insdStepNum_SS)) {
                    scrnMsg.stairCrawReqFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.insdStepNum_SS.getNameForMessage() });
                    scrnMsg.insdStepNum_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.insdStepNum_SS.getNameForMessage() });
                }

                if (!ZYPCommonFunc.hasValue(scrnMsg.flgtStairNum_SS)) {
                    scrnMsg.stairCrawReqFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.flgtStairNum_SS.getNameForMessage() });
                    scrnMsg.flgtStairNum_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.flgtStairNum_SS.getNameForMessage() });
                }

                if (!ZYPCommonFunc.hasValue(scrnMsg.stairAndLdgWdt_SS)) {
                    scrnMsg.stairCrawReqFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.stairAndLdgWdt_SS.getNameForMessage() });
                    scrnMsg.stairAndLdgWdt_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.stairCrawReqFlg_SS.getNameForMessage(), scrnMsg.stairAndLdgWdt_SS.getNameForMessage() });
                }
            }
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.loadDockAvalFlg_SS.getValue())) { // 2018/07/19 S21_NA#26188 Mod Condition
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.loadDockAvalFlg_SS.getValue()) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_S5.getValue()) || !massApplyMode || allEdtMode)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.loadDockHgt_SS)) {
                    scrnMsg.loadDockAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.loadDockAvalFlg_SS.getNameForMessage(), scrnMsg.loadDockHgt_SS.getNameForMessage() });
                    scrnMsg.loadDockHgt_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.loadDockAvalFlg_SS.getNameForMessage(), scrnMsg.loadDockHgt_SS.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_LD)) {
                    scrnMsg.loadDockAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.loadDockAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcFromHourMnTxt_LD.getNameForMessage() });
                    scrnMsg.xxSvcFromHourMnTxt_LD.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.loadDockAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcFromHourMnTxt_LD.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_LD)) {
                    scrnMsg.loadDockAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.loadDockAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcToHourMnTxt_LD.getNameForMessage() });
                    scrnMsg.xxSvcToHourMnTxt_LD.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.loadDockAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcToHourMnTxt_LD.getNameForMessage() });
                }
            }
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.elevAvalFlg_SS.getValue())) { // 2018/07/19 S21_NA#26188 Mod Condition
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.elevAvalFlg_SS.getValue()) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SF.getValue()) || !massApplyMode || allEdtMode)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_EA)) {
                    scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcFromHourMnTxt_EA.getNameForMessage() });
                    scrnMsg.xxSvcFromHourMnTxt_EA.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcFromHourMnTxt_EA.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_EA)) {
                    scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcToHourMnTxt_EA.getNameForMessage() });
                    scrnMsg.xxSvcToHourMnTxt_EA.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.xxSvcToHourMnTxt_EA.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.elevWdt_SS)) {
                    scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevWdt_SS.getNameForMessage() });
                    scrnMsg.elevWdt_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevWdt_SS.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.elevDepthNum_SS)) {
                    scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevDepthNum_SS.getNameForMessage() });
                    scrnMsg.elevDepthNum_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevDepthNum_SS.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.elevCapWt_SS)) {
                    scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevCapWt_SS.getNameForMessage() });
                    scrnMsg.elevCapWt_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevCapWt_SS.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.elevDoorHgt_SS)) {
                    scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevDoorHgt_SS.getNameForMessage() });
                    scrnMsg.elevDoorHgt_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevDoorHgt_SS.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.elevDoorWdt_SS)) {
                    scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevDoorWdt_SS.getNameForMessage() });
                    scrnMsg.elevDoorWdt_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevAvalFlg_SS.getNameForMessage(), scrnMsg.elevDoorWdt_SS.getNameForMessage() });
                }
                // 2018/07/19 S21_NA#26188 Add Start
                if (massApplyMode && !allEdtMode) {
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SH.getValue())) {
                        scrnMsg.xxChkBox_SG.setErrorInfo(1, ZZBM0084E, new String[] {"Check Box"});
                    }
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SK.getValue())) {
                        scrnMsg.xxChkBox_SK.setErrorInfo(1, ZZBM0084E, new String[] {"Check Box"});
                    }
                }
                // 2018/07/19 S21_NA#26188 Add End
            }
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.elevApptReqFlg_SS.getValue())) { // 2018/07/19 S21_NA#26188 Mod Condition
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.elevApptReqFlg_SS.getValue()) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SH.getValue()) || !massApplyMode || allEdtMode)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.elevCtacPsnNm_SS)) {
                    scrnMsg.elevApptReqFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevApptReqFlg_SS.getNameForMessage(), scrnMsg.elevCtacPsnNm_SS.getNameForMessage() });
                    scrnMsg.elevCtacPsnNm_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevApptReqFlg_SS.getNameForMessage(), scrnMsg.elevCtacPsnNm_SS.getNameForMessage() });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.elevCtacTelNum_SS)) {
                    scrnMsg.elevApptReqFlg_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevApptReqFlg_SS.getNameForMessage(), scrnMsg.elevCtacTelNum_SS.getNameForMessage() });
                    scrnMsg.elevCtacTelNum_SS.setErrorInfo(1, NWAM0761E, new String[] {scrnMsg.elevApptReqFlg_SS.getNameForMessage(), scrnMsg.elevCtacTelNum_SS.getNameForMessage() });
                }
                // 2018/07/19 S21_NA#26188 Add Start
                if (massApplyMode && !allEdtMode) {
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SF.getValue())) {
                        scrnMsg.xxChkBox_SF.setErrorInfo(1, ZZBM0084E, new String[] {"Check Box"});
                    }
                }
                // 2018/07/19 S21_NA#26188 Add End
            }
            if (massApplyMode && !allEdtMode && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SK.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SF.getValue())) {
                    scrnMsg.xxChkBox_SF.setErrorInfo(1, ZZBM0084E, new String[] {"Check Box"});
                }
            }
        // 2016/05/13 S21_NA#7088 Add END --------------
        // 2018/07/19 S21_NA#26188 Add Start
        }  else if (TAB_CONTACT.equals(tabTp)) {
            if(massApplyMode && scrnMsg.C.getValidCount() == 0) {
                scrnMsg.setMessageInfo(NMAM8190E);
                throw new EZDFieldErrorException();
            }
        }
        // 2018/07/19 S21_NA#26188 Add End
    }

    /**
     * Checking Phone Number
     * @param scrnMsg NWAL1510BMsg
     */
    private static void checkPhoneNum(NWAL1510BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.elevCtacTelNum_SS)) {

            String telNum = scrnMsg.elevCtacTelNum_SS.getValue();

            if (telNum.length() < 7 || telNum.length() > 20) {
                scrnMsg.elevCtacTelNum_SS.setErrorInfo(1, NWAM0763E);
            } else {
                telNum = telNum.replaceAll(SLASH, HYPHEN);
                telNum = telNum.replaceAll(PERIOD, HYPHEN);
                ZYPEZDItemValueSetter.setValue(scrnMsg.elevCtacTelNum_SS, telNum);
            }
        }

        for (int idx = 0; idx < scrnMsg.C.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnTelNum_C0)) {

                String telNum = scrnMsg.C.no(idx).ctacPsnTelNum_C0.getValue();

                if (telNum.length() < 7 || telNum.length() > 20) {
                    scrnMsg.C.no(idx).ctacPsnTelNum_C0.setErrorInfo(1, NWAM0763E);
                } else {
                    telNum = telNum.replaceAll(SLASH, HYPHEN);
                    telNum = telNum.replaceAll(PERIOD, HYPHEN);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnTelNum_C0, telNum);
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnFaxNum_C0)) {

                String faxNum = scrnMsg.C.no(idx).ctacPsnFaxNum_C0.getValue();
                if (faxNum.length() < 7 || faxNum.length() > 20) {
                    scrnMsg.C.no(idx).ctacPsnFaxNum_C0.setErrorInfo(1, NWAM0763E);
                } else {
                    faxNum = faxNum.replaceAll(SLASH, HYPHEN);
                    faxNum = faxNum.replaceAll(PERIOD, HYPHEN);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnFaxNum_C0, faxNum);
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnExtnNum_C0)) {

                String extNum = scrnMsg.C.no(idx).ctacPsnExtnNum_C0.getValue();
                // Mod Start 2018/12/14 QC#24022
                //if (extNum.length() > 4) {
                //    scrnMsg.C.no(idx).ctacPsnExtnNum_C0.setErrorInfo(1, NWAM0764E);
                if (extNum.length() > EXT_MAX_LENGTH) {
                    scrnMsg.C.no(idx).ctacPsnExtnNum_C0.setErrorInfo(1, NWAM0964E, //
                            new String[] {scrnMsg.C.no(idx).ctacPsnExtnNum_C0.getNameForMessage(), String.valueOf(EXT_MAX_LENGTH) });
                    // Mod End 2018/12/14 QC#24022
                }
            }
        }
    }

    /**
     * Checking EMail
     */
    private static void checkEMail(NWAL1510BMsg scrnMsg) {

        for (int idx = 0; idx < scrnMsg.C.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnEmlAddr_C0)) {
                // 2016/05/13 QC#1443 Add Start --------------
                boolean ret =NMXC106001CommonCheckUtil.checkEmailFormat(scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.getValue());

                if (!ret) {
                    scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                }
                // 2016/05/13 QC#1443 Add End --------------

                //if (!scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.getValue().matches(CHK_EMAIL_PATTERN)) {
                //    scrnMsg.C.no(idx).ctacPsnEmlAddr_C0.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                //}
            }
        }
    }

    /**
     * Check Name Mandatory
     * @param scrnMsg NWAL1510BMsg
     */
    public static void checkNameMandatory(NWAL1510BMsg scrnMsg) {

        for (int idx = 0; idx < scrnMsg.C.getValidCount(); idx++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnFirstNm_C0)) {
                scrnMsg.C.no(idx).ctacPsnFirstNm_C0.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.C.no(idx).ctacPsnFirstNm_C0.getNameForMessage() });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(idx).ctacPsnLastNm_C0)) {
                scrnMsg.C.no(idx).ctacPsnLastNm_C0.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.C.no(idx).ctacPsnLastNm_C0.getNameForMessage() });
            }
        }
    }

    // /2016/05/13 S21_NA#7088 Add Start --------------
    /**
     * checkinstallMandatory
     * @param scrnMsg NWAL1510BMsg
     */
    public static void checkInstallMandatory(NWAL1510BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.svcIstlRuleNum_DI)) {
            scrnMsg.svcIstlRuleNum_DI.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.svcIstlRuleNum_DI.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.istlDivCd_DI)) {
            scrnMsg.istlDivCd_DI.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.istlDivCd_DI.getNameForMessage() });
        }
    }

    /**
     * checkTransMandatory
     * @param scrnMsg NWAL1510BMsg
     */
    public static void checkTransMandatory(NWAL1510BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.delyTrnspOptCd_SS)) {
            scrnMsg.delyTrnspOptCd_SS.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.delyTrnspOptCd_SS.getNameForMessage() });
        }

    }

    /**
     * checkBldgEnt
     * @param scrnMsg NWAL1510BMsg
     */
    public static void checkBldgEntMandatory(NWAL1510BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.bldgEntDoorHgt_SS)) {
            scrnMsg.bldgEntDoorHgt_SS.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.bldgEntDoorHgt_SS.getNameForMessage() });
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.bldgEntDoorWdt_SS)) {
            scrnMsg.bldgEntDoorWdt_SS.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.bldgEntDoorWdt_SS.getNameForMessage() });
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.crdrWdt_SS)) {
            scrnMsg.crdrWdt_SS.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.crdrWdt_SS.getNameForMessage() });
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.doorWdt_SS)) {
            scrnMsg.doorWdt_SS.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.doorWdt_SS.getNameForMessage() });
        }
    }

    // /2016/05/13 S21_NA#7088 End Start --------------

    /**
     * Checking Time Item
     * @param scrnMsg NWAL1510BMsg
     */
    private static void checkTimeItem(NWAL1510BMsg scrnMsg) {
        // Delivery &Install Tab
        isTime(scrnMsg.xxSvcFromHourMnTxt_RQ, scrnMsg.xxSvcFromHourMnTxt_OP, scrnMsg.xxSvcToHourMnTxt_OP);

        // Survey Tab
        isTime(scrnMsg.xxSvcFromHourMnTxt_LD, scrnMsg.xxSvcToHourMnTxt_LD, scrnMsg.xxSvcFromHourMnTxt_CD, scrnMsg.xxSvcFromHourMnTxt_EA, scrnMsg.xxSvcToHourMnTxt_EA);
    }

    private static void isTime(EZDBStringItem... timeItems) {
        for (EZDBStringItem timeItem : timeItems) {
            if (ZYPCommonFunc.hasValue(timeItem)) {
                if (timeItem.getValue().length() < 5) {
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
     * @param scrnMsg NWAL1510BMsg
     */
    private static void checkNumber(NWAL1510BMsg scrnMsg) {
        isNumber(scrnMsg.stairCrawNum_DI);
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

    /**
     * Checkng Date Item
     * @param scrnMsg NWAL1510BMsg
     */
    private static void checkDate(NWAL1510BMsg scrnMsg) {
        isDate(scrnMsg.rqstIstlDt_DI);
    }

    private static void isDate(EZDBDateItem... dateItems) {
        for (EZDBDateItem dateItem : dateItems) {
            if (ZYPCommonFunc.hasValue(dateItem)) {
                if (!ZYPDateUtil.isValidDate(dateItem.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                    dateItem.setErrorInfo(1, NWAM0664E, new String[] {ZYPDateUtil.TYPE_MMDDYYYY });
                }
            }
        }
    }

    /**
     * Check Format Item
     * @param scrnMsg NWAL1510BMsg
     */
    public static void checkFormatItem(NWAL1510BMsg scrnMsg) {
        checkDate(scrnMsg);
        checkTimeItem(scrnMsg);
        checkPhoneNum(scrnMsg);
        checkEMail(scrnMsg);
        checkNumber(scrnMsg);
    }

    /**
     * Set Tab Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1510BMsg
     */
    public static void setTabProtect(EZDCommonHandler handler, NWAL1510BMsg scrnMsg) {

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
        scrnMsg.delyAddlCmntTxt_DI.setInputProtected(diProtectFlg);
        scrnMsg.svcIstlRuleNum_DI.setInputProtected(diProtectFlg);
        scrnMsg.istlDivCd_DI.setInputProtected(diProtectFlg);
        scrnMsg.istlTechCd_DI.setInputProtected(diProtectFlg);
        scrnMsg.rqstIstlDt_DI.setInputProtected(diProtectFlg);
        scrnMsg.istlAddlCmntTxt_DI.setInputProtected(diProtectFlg);
        scrnMsg.loadDockAvalFlg_DI.setInputProtected(diProtectFlg);
        scrnMsg.stairCrawReqFlg_DI.setInputProtected(diProtectFlg);
        scrnMsg.stairCrawNum_DI.setInputProtected(diProtectFlg);
        scrnMsg.elevAvalFlg_DI.setInputProtected(diProtectFlg);
        scrnMsg.opsFromHourMn_AP.setInputProtected(diProtectFlg);
        scrnMsg.opsToHourMn_AP.setInputProtected(diProtectFlg);
        // 2020/02/10 QC#54725-1 Mod Start
        // 2019/12/26 QC#54725 Add Srart
        //scrnMsg.delySchdStsCd_DI.setInputProtected(diProtectFlg);
        //scrnMsg.xxAttDataCmntTxt_DI.setInputProtected(diProtectFlg);
        // 2019/12/26 QC#54725 Add End
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRsltFlg_DS.getValue())) {
            scrnMsg.delySchdStsCd_DI.setInputProtected(false);
            scrnMsg.xxAttDataCmntTxt_DI.setInputProtected(false);
        } else {
            scrnMsg.delySchdStsCd_DI.setInputProtected(true);
            scrnMsg.xxAttDataCmntTxt_DI.setInputProtected(true);
        }
        // 2020/02/10 QC#54725-1 Mod End
        // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD START
        // Install Type. EDTBL_FLG.
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRsltFlg_IT.getValue()) && !diProtectFlg ) {
            scrnMsg.rqstIstlTm_AP.setInputProtected(false);
            scrnMsg.xxSvcFromHourMnTxt_RQ.setInputProtected(false);
        } else{
            scrnMsg.rqstIstlTm_AP.setInputProtected(true);
            scrnMsg.xxSvcFromHourMnTxt_RQ.setInputProtected(true);
        }
        // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD END
        scrnMsg.xxSvcFromHourMnTxt_OP.setInputProtected(diProtectFlg);
        scrnMsg.xxSvcToHourMnTxt_OP.setInputProtected(diProtectFlg);

        // 2019/11/09 QC#53993 Add Start
        scrnMsg.istlBySvcPrvdPtyCd_DI.setInputProtected(diProtectFlg);
        scrnMsg.svcBySvcPrvdPtyCd_DI.setInputProtected(diProtectFlg);
        // 2019/11/09 QC#53993 Add Stop

        // 2020/02/10 QC#54725-1 Del End
        // 2019/12/20 QC#54725 Add Start
        //scrnMsg.delySchdStsCd_DI.setInputProtected(diProtectFlg);
        //scrnMsg.xxAttDataCmntTxt_DI.setInputProtected(diProtectFlg);
        // 2019/12/20 QC#54725 Add Stop
        // 2020/02/10 QC#54725-1 Del End

        scrnMsg.techNm_LK.setInputProtected(diProtectFlg);
        if (diProtectFlg) {
            scrnMsg.techNm_LK.clear();
        }
        handler.setButtonEnabled(BTN_TECH[0], !diProtectFlg);

        // Site Survey
        scrnMsg.cmpyInfoAptBldgNm_SS.setInputProtected(ssProtectFlg);
        scrnMsg.cmpyInfoFlNm_SS.setInputProtected(ssProtectFlg);
        scrnMsg.cmpyInfoDeptNm_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevProtReqFlg_SS.setInputProtected(ssProtectFlg);
        //  Mod Start 2016/09/20 QC#13738
//        scrnMsg.siteSrvyAddlCmntTxt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.xxFldValTxt_SS.setInputProtected(ssProtectFlg);
        // Mod End 2016/09/20 QC#13738
        scrnMsg.otsdStepNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.insdStepNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.stairCrawReqFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.flgtStairNum_SS.setInputProtected(ssProtectFlg);
        scrnMsg.stairAndLdgWdt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockAvalFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockHgt_SS.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockAvalFromHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.loadDockAvalToHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.trctrAndTrailAccsFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.carrDelyTmHourMn_AP.setInputProtected(ssProtectFlg);
        scrnMsg.delyTrnspOptCd_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevAvalFlg_SS.setInputProtected(ssProtectFlg);
        scrnMsg.elevAvalFromHourMn_AP.setInputProtected(ssProtectFlg);
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
        scrnMsg.xxSvcFromHourMnTxt_LD.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcToHourMnTxt_LD.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcFromHourMnTxt_CD.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcFromHourMnTxt_EA.setInputProtected(ssProtectFlg);
        scrnMsg.xxSvcToHourMnTxt_EA.setInputProtected(ssProtectFlg);

        // Contact
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL1510_CBMsg cBMsg = scrnMsg.C.no(i);

            cBMsg.ctacPsnTpCd_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnFirstNm_C0.setInputProtected(coProtectFlg);
            // QC16452 add Start
            if (coProtectFlg) {
                cBMsg.ctacPsnFirstNm_LK.clear();
            } else {
                cBMsg.ctacPsnFirstNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            }
            // QC16452 add End
            cBMsg.ctacPsnLastNm_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnTelNum_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnExtnNum_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnEmlAddr_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacPsnFaxNum_C0.setInputProtected(coProtectFlg);
            cBMsg.ctacCustRefTpCd_C0.setInputProtected(coProtectFlg);   // QC#16452 add
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
            setProtectByContact(scrnMsg);
        }

        if (diProtectFlg && ssProtectFlg && coProtectFlg) {
            handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 0, null);
        }
    }

    /**
     * Set Delivery Install Display Flag
     * @param scrnMsg NWAL1510BMsg
     */
    public static void setDelyIstlDispFlg(NWAL1510BMsg scrnMsg) {

        // 2019/10/26 QC#53993 Mod Start
        //if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_H0) && CONFIG_CATG.INBOUND.equals(scrnMsg.configCatgCd_H0.getValue())) {
        if (CONFIG_CATG.INBOUND.equals(scrnMsg.configCatgCd_H0.getValue())) {
        // 2019/10/26 QC#53993 Mod End    
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_DI, DERY_DISP_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_LK, TECHNICIAN_LABEL_NAME_DE_INS);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_DI, DERY_DISP_FLG_ON);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_LK, TECHNICIAN_LABEL_NAME_INS);
        }
    }

    /**
     * Set Name For Message Delivery Display
     * @param scrnMsg NWAL1510BMsg
     */
    public static void setNameForMessageDeryDisp(NWAL1510BMsg scrnMsg) {

        if (DERY_DISP_FLG_OFF.equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {
            scrnMsg.delyAddlCmntTxt_DI.setNameForMessage("Pickup Instructions");
            scrnMsg.svcIstlRuleNum_DI.setNameForMessage("De-install Type");
            scrnMsg.istlDivCd_DI.setNameForMessage("De-install Division");
            scrnMsg.istlTechCd_DI.setNameForMessage("De-install Technician");
            scrnMsg.rqstIstlDt_DI.setNameForMessage("De-install Date");
            // 2018/01/31 T.Hakodate S21_NA#18460(Sol#087) ADD START
            scrnMsg.rqstIstlTm_DI.setNameForMessage("Time Stop");
            // 2018/01/31 T.Hakodate S21_NA#18460(Sol#087) ADD END
            scrnMsg.istlAddlCmntTxt_DI.setNameForMessage("De-install Instructions");
        } else {
            scrnMsg.delyAddlCmntTxt_DI.setNameForMessage("Delivery Instructions");
            scrnMsg.svcIstlRuleNum_DI.setNameForMessage("Install Type");
            scrnMsg.istlDivCd_DI.setNameForMessage("Install Division");
            scrnMsg.istlTechCd_DI.setNameForMessage("Install Technician");
            scrnMsg.rqstIstlDt_DI.setNameForMessage("Install Date");
            // 2018/01/31 T.Hakodate S21_NA#18460(Sol#087) ADD START
            scrnMsg.rqstIstlTm_DI.setNameForMessage("Time Stop");
            // 2018/01/31 T.Hakodate S21_NA#18460(Sol#087) ADD END
            scrnMsg.istlAddlCmntTxt_DI.setNameForMessage("Install Instructions");
        }
    }

    /**
     * Date Compare Check
     * @param scrnMsg NWAL1510BMsg
     */
    public static void dlvyIstlDateCompareCheck(NWAL1510BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.rqstIstlDt_DI)) {
            if (ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue()).compareTo(scrnMsg.rqstIstlDt_DI.getValue()) > 0) {
                scrnMsg.rqstIstlDt_DI.setErrorInfo(1, NWAM0773E);
                return;
            }
        }
    }

    /**
     * Date Compare Check
     * @param scrnMsg NWAL1510BMsg
     */
    public static void timeCompareCheck(NWAL1510BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_OP) && ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_OP)) {
            timeFromToCompare(scrnMsg.xxSvcFromHourMnTxt_OP, scrnMsg.opsFromHourMn_AP, scrnMsg.xxSvcToHourMnTxt_OP, scrnMsg.opsToHourMn_AP);
        }
        // 2016/07/11 QC#5030 Mod Start
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_OP) && !(scrnMsg.opsFromHourMn_AP.getValue().equals(PM_CD) || scrnMsg.opsFromHourMn_AP.getValue().equals(AM_CD))) {
            scrnMsg.opsFromHourMn_AP.setErrorInfo(1, NWAM0869E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_OP) && !(scrnMsg.opsToHourMn_AP.getValue().equals(PM_CD) || scrnMsg.opsToHourMn_AP.getValue().equals(AM_CD))) {
            scrnMsg.opsToHourMn_AP.setErrorInfo(1, NWAM0869E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_RQ) && !(scrnMsg.rqstIstlTm_AP.getValue().equals(PM_CD) || scrnMsg.rqstIstlTm_AP.getValue().equals(AM_CD))) {
            scrnMsg.rqstIstlTm_AP.setErrorInfo(1, NWAM0869E);
        }
        // 2016/07/11 QC#5030 Mod End

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_LD) && ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_LD)) {
            timeFromToCompare(scrnMsg.xxSvcFromHourMnTxt_LD, scrnMsg.loadDockAvalFromHourMn_AP, scrnMsg.xxSvcToHourMnTxt_LD, scrnMsg.loadDockAvalToHourMn_AP);
        }

        // 2016/07/11 QC#5030 Mod Start
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_LD) && !(scrnMsg.loadDockAvalFromHourMn_AP.getValue().equals(PM_CD) || scrnMsg.loadDockAvalFromHourMn_AP.getValue().equals(AM_CD))) {
            scrnMsg.loadDockAvalFromHourMn_AP.setErrorInfo(1, NWAM0869E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_LD) && !(scrnMsg.loadDockAvalToHourMn_AP.getValue().equals(PM_CD) || scrnMsg.loadDockAvalToHourMn_AP.getValue().equals(AM_CD))) {
            scrnMsg.loadDockAvalToHourMn_AP.setErrorInfo(1, NWAM0869E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_CD) && !(scrnMsg.carrDelyTmHourMn_AP.getValue().equals(PM_CD) || scrnMsg.carrDelyTmHourMn_AP.getValue().equals(AM_CD))) {
            scrnMsg.carrDelyTmHourMn_AP.setErrorInfo(1, NWAM0869E);
        }
        // 2016/07/11 QC#5030 Mod End

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_EA) && ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_EA)) {
            timeFromToCompare(scrnMsg.xxSvcFromHourMnTxt_EA, scrnMsg.elevAvalFromHourMn_AP, scrnMsg.xxSvcToHourMnTxt_EA, scrnMsg.elevAvalToHourMn_AP);
        }
        // 2016/07/11 QC#5030 Mod Start
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_EA) && !(scrnMsg.elevAvalFromHourMn_AP.getValue().equals(PM_CD) || scrnMsg.elevAvalFromHourMn_AP.getValue().equals(AM_CD))) {
            scrnMsg.elevAvalFromHourMn_AP.setErrorInfo(1, NWAM0869E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_EA) && !(scrnMsg.elevAvalToHourMn_AP.getValue().equals(PM_CD) || scrnMsg.elevAvalToHourMn_AP.getValue().equals(AM_CD))) {
            scrnMsg.elevAvalToHourMn_AP.setErrorInfo(1, NWAM0869E);
        }
        // 2016/07/11 QC#5030 Mod End
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
     * @param scrnMsg NWAL1510BMsg
     */
    public static void setAppFracDigit(NWAL1510BMsg scrnMsg) {

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
     * @param scrnMsg NWAL1510BMsg
     */
    public static void addCheckItemBizLayerErr(NWAL1510BMsg scrnMsg) {
        
        // ----------------------------------------------
        // Header
        // ----------------------------------------------
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H0);
        scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum_H0);
        scrnMsg.addCheckItem(scrnMsg.configCatgCd_H0);

        // 2018/07/19 S21_NA#26188 Mod Start
        // String origxxDplyTab = scrnMsg.xxDplyTab.getValue();
        // scrnMsg.xxDplyTab.clear();
        String tabTp = scrnMsg.xxDplyTab.getValue();
        // 2018/07/19 S21_NA#26188 Mod End
        
        Set<EZDBItem> itemList = new LinkedHashSet<EZDBItem>();
        if (TAB_INSTALL.equals(tabTp)) { // 2018/07/19 S21_NA#26188 Add Condition
        // ----------------------------------------------
        // Delivery&Install Tab
        // ----------------------------------------------
        itemList.add(scrnMsg.dsOrdPosnNum_H0);
        itemList.add(scrnMsg.xxSvcFromHourMnTxt_RQ);
        itemList.add(scrnMsg.xxSvcFromHourMnTxt_OP);
        itemList.add(scrnMsg.xxSvcToHourMnTxt_OP);
        itemList.add(scrnMsg.opsFromHourMn_AP);
        itemList.add(scrnMsg.opsToHourMn_AP);
        itemList.add(scrnMsg.rqstIstlDt_DI);
        itemList.add(scrnMsg.delyAddlCmntTxt_DI);
        itemList.add(scrnMsg.istlAddlCmntTxt_DI);
        itemList.add(scrnMsg.stairCrawNum_DI);
        itemList.add(scrnMsg.istlTechCd_DI);
        // S21_NA#5030 Add start
        itemList.add(scrnMsg.loadDockAvalFlg_DI);
        itemList.add(scrnMsg.stairCrawReqFlg_DI);
        itemList.add(scrnMsg.elevAvalFlg_DI);
        // S21_NA#5030 Add End

        // /2016/05/13 S21_NA#7088 Add Start --------------
        itemList.add(scrnMsg.svcIstlRuleNum_DI);
        itemList.add(scrnMsg.istlDivCd_DI);
        // /2016/05/13 S21_NA#7088 End Start --------------
        
        // 2018/07/18 S21_NA#26188 Add Start
        itemList.add(scrnMsg.xxChkBox_D0);
        itemList.add(scrnMsg.xxChkBox_D1);
        itemList.add(scrnMsg.xxChkBox_D2);
        itemList.add(scrnMsg.xxChkBox_D3);
        itemList.add(scrnMsg.xxChkBox_D4);
        itemList.add(scrnMsg.xxChkBox_D5);
        itemList.add(scrnMsg.xxChkBox_D7);
        itemList.add(scrnMsg.xxChkBox_D9);
        // 2018/07/18 S21_NA#26188 Add End
        // 2019/10/30 QC#53993 Add Start
        itemList.add(scrnMsg.istlBySvcPrvdPtyCd_DI);
        itemList.add(scrnMsg.svcBySvcPrvdPtyCd_DI);
        itemList.add(scrnMsg.xxChkBox_DA);
        // 2019/10/30 QC#53993 Add End
        // 2019/12/20 QC#54725 Add Start
        itemList.add(scrnMsg.delySchdStsCd_DI);
        itemList.add(scrnMsg.xxAttDataCmntTxt_DI);
        itemList.add(scrnMsg.xxChkBox_DB);
        itemList.add(scrnMsg.xxChkBox_DC);
        // 2019/12/20 QC#54725 Add End

        for (EZDBItem item : itemList) {
            scrnMsg.addCheckItem(item);
            if (item.isError()) {
                scrnMsg.xxDplyTab.setValue(TAB_INSTALL);
            }
        }
        }

        // if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) { // 2018/07/19 S21_NA#26188 Mod Condition
        if (TAB_SURVEY.equals(tabTp)) {
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
            // Mod Start 2016/09/20 QC#13738
//            itemList.add(scrnMsg.siteSrvyAddlCmntTxt_SS);
            itemList.add(scrnMsg.xxFldValTxt_SS);
            // Mod End 2016/09/20 QC#13738
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
            // 2018/07/18 S21_NA#26188 Add Start 
            itemList.add(scrnMsg.xxEdtModeFlg_SS);
            itemList.add(scrnMsg.xxChkBox_S0);
            itemList.add(scrnMsg.xxChkBox_S1);
            itemList.add(scrnMsg.xxChkBox_S2);
            itemList.add(scrnMsg.xxChkBox_S3);
            itemList.add(scrnMsg.xxChkBox_S4);
            itemList.add(scrnMsg.xxChkBox_S5);
            itemList.add(scrnMsg.xxChkBox_SC);
            itemList.add(scrnMsg.xxChkBox_SD);
            itemList.add(scrnMsg.xxChkBox_SE);
            itemList.add(scrnMsg.xxChkBox_SF);
            itemList.add(scrnMsg.xxChkBox_SH);
            itemList.add(scrnMsg.xxChkBox_SJ);
            itemList.add(scrnMsg.xxChkBox_SK);
            // 2018/07/18 S21_NA#26188 Add End
            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_SURVEY);
                }
            }

            // Add Start 2016/09/20 QC#13738
            if (!scrnMsg.xxFldValTxt_SS.isError() && ZYPCommonFunc.hasValue(scrnMsg.xxFldValTxt_SS)) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("xxFldValTxt_SS");
                DS_SITE_SRVYTMsg dssTMsg = new DS_SITE_SRVYTMsg();
                EZDItemAttribute checkAttrb = dssTMsg.getAttr("siteSrvyAddlCmntTxt");

                if (checkValDigit(scrnMsg.xxFldValTxt_SS.getValue().length(), checkAttrb)) {
                    // Mod Start 2016/11/04 M.Ohno S21_NA#13738
                    // scrnMsg.xxFldValTxt_SS.setErrorInfo(1, NWAM0895E, new String[] {"Additional Comments", String.valueOf(checkAttrb.getDigit())});
                    scrnMsg.xxFldValTxt_SS.setErrorInfo(1, NWAM0906E, new String[] {String.valueOf(checkAttrb.getDigit())});
                    // Mod End   2016/11/04 M.Ohno S21_NA#13738
                }
            }
            // Add End 2016/09/20 QC#13738
        }

        // if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) { // 2018/07/19 S21_NA#26188 Mod Condition
        if (TAB_CONTACT.equals(tabTp)) {
            itemList = new LinkedHashSet<EZDBItem>();
            // ----------------------------------------------
            // Contact Tab
            // ----------------------------------------------
            itemList.add(scrnMsg.xxEdtModeFlg_CT); // 2018/07/19 S21_NA#26188 Add
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

        // 2018/07/19 S21_NA#26188 Del Start
        // if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
        //     scrnMsg.xxDplyTab.setValue(origxxDplyTab);
        // }
        // 2018/07/19 S21_NA#26188 Del End
    }

    /**
     * Set Screen Time
     * @param scrnMsg NWAL1510BMsg
     */
    public static void setScrnTm(NWAL1510BMsg scrnMsg) {

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
     * Get Screen Time
     * @param scrnMsg NWAL1510BMsg
     */
    public static void getScrnTm(NWAL1510BMsg scrnMsg) {

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
     * Set Time
     * @param tm time
     * @return time
     */
    public static String setTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(tm);
            if (strBuf.length() > 2) {
                strBuf.insert(2, COLON);
            }
            return strBuf.toString();
        }
        return tm;
    }

    /**
     * Get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(COLON, "");
        }
        return tm;
    }

    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL1510BMsg
     * @param glblCmpyCd String
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdConfig(NWAL1510BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[POP_PAR_7];
        params[POP_PAR_0] = "";
        params[POP_PAR_1] = "Order Configration Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("     CONF.DS_ORD_POSN_NUM");
        sb.append("    ,CONF.MDL_DESC_TXT");
        sb.append("    ,CONF.SHIP_TO_FIRST_LINE_ADDR || '").append(SPACE).append("' || ");
        sb.append("     CONF.SHIP_TO_SCD_LINE_ADDR   || '").append(SPACE).append("' || ");
        sb.append("     CONF.SHIP_TO_THIRD_LINE_ADDR || '").append(SPACE).append("' || ");
        sb.append("     CONF.SHIP_TO_FRTH_LINE_ADDR  || '").append(SPACE).append("' || ");
        sb.append("     CONF.SHIP_TO_CTY_ADDR        || '").append(COMMA).append("' || ");
        sb.append("     CONF.SHIP_TO_ST_CD           || '").append(SPACE).append("' || ");
        sb.append("     CONF.SHIP_TO_POST_CD").append(" AS ADDR");
        sb.append("    ,CONF.CPO_ORD_NUM");
        sb.append("    ,CONF.GLBL_CMPY_CD");
        sb.append("    ,CONF.EZCANCELFLAG ");
        sb.append("FROM");
        sb.append("    DS_CPO_CONFIG CONF ");
        sb.append("WHERE");
        sb.append("        CONF.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND CONF.CPO_ORD_NUM  = '").append(scrnMsg.cpoOrdNum_H0.getValue()).append("'");
        // 2016/10/21 S21_NA#15475 Add Start
        sb.append("    AND CONF.CONFIG_CATG_CD = '").append(scrnMsg.configCatgCd_H0.getValue()).append("'");
        // 2016/10/21 S21_NA#15475 Add End
        sb.append("    AND CONF.DS_ORD_POSN_NUM IN (");
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (i >= 1) {
                sb.append(",");
            }
            sb.append("'").append(scrnMsg.H.no(i).dsOrdPosnNum_H1.getValue()).append("'");
        }
        sb.append(")");
        sb.append("    AND CONF.EZCANCELFLAG = '0' ");

        params[POP_PAR_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        params[POP_PAR_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[POP_PAR_4];
        columnArray0[POP_PAR_0] = "Config#";
        columnArray0[POP_PAR_1] = "DS_ORD_POSN_NUM";
        columnArray0[POP_PAR_2] = BigDecimal.valueOf(8);
        columnArray0[POP_PAR_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[POP_PAR_4];
        columnArray1[POP_PAR_0] = "Model";
        columnArray1[POP_PAR_1] = "MDL_DESC_TXT";
        columnArray1[POP_PAR_2] = BigDecimal.valueOf(12);
        columnArray1[POP_PAR_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[POP_PAR_4];
        columnArray2[POP_PAR_0] = "Ship Address";
        columnArray2[POP_PAR_1] = "ADDR";
        columnArray2[POP_PAR_2] = BigDecimal.valueOf(70);
        columnArray2[POP_PAR_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[POP_PAR_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[POP_PAR_2];
        // 2016/10/21 S21_NA#15475 Add Start
//        sortConditionArray0[POP_PAR_0] = "DS_ORD_POSN_NUM";
        int paddingDigits = scrnMsg.getAttr("dsOrdPosnNum_H0").getDigit();
        String sortKey = "LPAD(DS_ORD_POSN_NUM, " + String.valueOf(paddingDigits) + ", '0')";
        sortConditionArray0[POP_PAR_0] = sortKey;
        // 2016/10/21 S21_NA#15475 Add End
        sortConditionArray0[POP_PAR_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[POP_PAR_5] = sortConditionList;

        return params;
    }

    // Add Start 2016/11/04 M.Ohno S21_NA#15686
    /**
     * Get Param NWAL1130 For Technician Code
     * @param scrnMsg NWAL1510BMsg
     * @param glblCmpyCd String
     * @return Param NWAL1130 For Technician Code
     */
    public static Object[] getParamNWAL1130ForTech(NWAL1510BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[POP_PAR_7];
        params[POP_PAR_0] = "";
        params[POP_PAR_1] = "Technician Code Search";

        // QC#19610
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT");
//        sb.append("    TM.TECH_TOC_CD");
//        sb.append("    ,TM.TECH_NM");
//        sb.append("    ,TM.GLBL_CMPY_CD");
//        sb.append("    ,TM.EZCANCELFLAG ");
//        sb.append("FROM");
//        sb.append("    TECH_MSTR TM");
//        sb.append("    ,ORG_FUNC_ASG OFA");
//        sb.append("    ,S21_ORG SO ");
//        sb.append("WHERE");
//        sb.append("        TM.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
//        sb.append("    AND TM.EZCANCELFLAG = '0'");
//        sb.append("");
//        sb.append("    AND TM.GLBL_CMPY_CD = OFA.GLBL_CMPY_CD");
//        sb.append("    AND TM.EZCANCELFLAG = OFA.EZCANCELFLAG");
//        sb.append("    AND TM.TECH_TOC_CD  = OFA.PSN_CD");
//        sb.append("");
//        sb.append("    AND OFA.GLBL_CMPY_CD = SO.GLBL_CMPY_CD");
//        sb.append("    AND OFA.EZCANCELFLAG = SO.EZCANCELFLAG");
//        sb.append("    AND OFA.TOC_CD       = SO.TOC_CD");
//        sb.append("    AND SO.RGTN_STS_CD   <> '").append(RGTN_STS.TERMINATED).append("'");
//
//        params[POP_PAR_2] = sb.toString();

        params[POP_PAR_2] = getTechSearchQuery(glblCmpyCd);

        List<Object[]> whereList = new ArrayList<Object[]>();
        params[POP_PAR_3] = whereList;

        Object[] whereArray0 = new Object[POP_PAR_4];
        whereArray0[POP_PAR_0] = "Technician Code";
        whereArray0[POP_PAR_1] = "TECH_TOC_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.istlTechCd_DI)) {
            whereArray0[POP_PAR_2] = scrnMsg.istlTechCd_DI.getValue();
        } else {
            whereArray0[POP_PAR_2] = "%";
        }
        whereArray0[POP_PAR_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[POP_PAR_4];
        whereArray1[POP_PAR_0] = "Technician Name";
        whereArray1[POP_PAR_1] = "TECH_NM";
        whereArray1[POP_PAR_2] = null;
        whereArray1[POP_PAR_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[POP_PAR_4];
        columnArray0[POP_PAR_0] = "Technician Code";
        columnArray0[POP_PAR_1] = "TECH_TOC_CD";
        columnArray0[POP_PAR_2] = BigDecimal.valueOf(12);
        columnArray0[POP_PAR_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[POP_PAR_4];
        columnArray1[POP_PAR_0] = "Technician Name";
        columnArray1[POP_PAR_1] = "TECH_NM";
        columnArray1[POP_PAR_2] = BigDecimal.valueOf(80);
        columnArray1[POP_PAR_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[POP_PAR_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[POP_PAR_2];
        sortConditionArray0[POP_PAR_0] = "TECH_TOC_CD";
        sortConditionArray0[POP_PAR_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[POP_PAR_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[POP_PAR_6] = scrnMsg.P;

        return params;
    }

    // QC#19610
    private static String getTechSearchQuery(String glblCmpyCd) {
        final String salesDate = ZYPDateUtil.getSalesDate();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    TM.TECH_TOC_CD");
        sb.append("    , TM.TECH_NM");
        sb.append("    , TM.GLBL_CMPY_CD");
        sb.append("    , TM.EZCANCELFLAG");
        sb.append("  FROM");
        sb.append("    TECH_MSTR TM");
        sb.append("  WHERE");
        sb.append("        TM.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND TM.EZCANCELFLAG = '0'");
        //
        sb.append("    AND EXISTS(");
        sb.append("         SELECT");
        sb.append("             1");
        sb.append("             FROM");
        sb.append("                 ORG_FUNC_ASG       OFA");
        sb.append("                 , TOC              T");
        sb.append("                 , ORG_FUNC_ROLE_TP OFRT");
        sb.append("             WHERE");
        sb.append("                     OFA.GLBL_CMPY_CD                  = TM.GLBL_CMPY_CD");
        sb.append("                 AND OFA.PSN_CD                        = TM.TECH_TOC_CD");
        sb.append("                 AND OFA.EFF_FROM_DT                  <= '").append(salesDate).append("'");
        sb.append("                 AND NVL(OFA.EFF_THRU_DT, '").append(salesDate).append("') >= '").append(salesDate).append("'");
        //
        sb.append("                 AND OFA.GLBL_CMPY_CD                  = T.GLBL_CMPY_CD");
        sb.append("                 AND OFA.TOC_CD                        = T.TOC_CD");
        //
        sb.append("                 AND T.GLBL_CMPY_CD                    = OFRT.GLBL_CMPY_CD");
        sb.append("                 AND T.ORG_FUNC_ROLE_TP_CD             = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("                 AND OFRT.TECH_MSTR_REQ_FLG            = 'Y'");
        sb.append("                 AND OFRT.ACTV_FLG                     = 'Y'");
        //
        sb.append("                 AND OFA.EZCANCELFLAG                  = '0'");
        sb.append("                 AND T.EZCANCELFLAG                    = '0'");
        sb.append("                 AND OFRT.EZCANCELFLAG                 = '0'");
        sb.append("     )");

        return sb.toString();
    }
    // Add End   2016/11/04 M.Ohno S21_NA#15686

    // 2016/06/06 S21_NA#7088 Add Start
    /**
     * <pre>
     * Item Required Rerease
     * </pre>
     * @param scrnMsg NWAL1510BMsg
     */
    public static void ItemRequiredRerease(NWAL1510BMsg scrnMsg) {

        if (scrnMsg.svcIstlRuleNum_DI.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("svcIstlRuleNum_DI");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.svcIstlRuleNum_DI.clearErrorInfo();
            }
        }

        // 2019/11/01 QC#53993 Add Start
        if (scrnMsg.istlBySvcPrvdPtyCd_DI.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("istlBySvcPrvdPtyCd_DI");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.istlBySvcPrvdPtyCd_DI.clearErrorInfo();
            }
        }
        // 2019/11/01 QC#53993 Add End

        if (scrnMsg.istlDivCd_DI.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("istlDivCd_DI");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.istlDivCd_DI.clearErrorInfo();
            }
        }

        if (scrnMsg.delyTrnspOptCd_SS.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("delyTrnspOptCd_SS");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.delyTrnspOptCd_SS.clearErrorInfo();
            }
        }

        if (scrnMsg.delyTrnspOptCd_SS.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("delyTrnspOptCd_SS");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.delyTrnspOptCd_SS.clearErrorInfo();
            }
        }

        if (scrnMsg.bldgEntDoorHgt_SS.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("bldgEntDoorHgt_SS");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.bldgEntDoorHgt_SS.clearErrorInfo();
            }
        }

        if (scrnMsg.bldgEntDoorWdt_SS.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("bldgEntDoorWdt_SS");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.bldgEntDoorWdt_SS.clearErrorInfo();
            }
        }

        if (scrnMsg.crdrWdt_SS.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("crdrWdt_SS");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.crdrWdt_SS.clearErrorInfo();
            }
        }

        if (scrnMsg.doorWdt_SS.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("doorWdt_SS");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.doorWdt_SS.clearErrorInfo();
            }
        }
        
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (scrnMsg.C.no(i).ctacPsnFirstNm_C0.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("ctacPsnFirstNm_C0");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).ctacPsnFirstNm_C0.clearErrorInfo();
                }
            }

            if (scrnMsg.C.no(i).ctacPsnLastNm_C0.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("ctacPsnLastNm_C0");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).ctacPsnLastNm_C0.clearErrorInfo();
                }
            }
            // QC#14143 2016/11/07 Add Start
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).ctacPsnTelNum_C0) 
                    && !ZYPCommonFunc.hasValue(scrnMsg.C.no(i).ctacPsnEmlAddr_C0) 
                    && !ZYPCommonFunc.hasValue(scrnMsg.C.no(i).ctacPsnFaxNum_C0)) {
                scrnMsg.C.no(i).ctacPsnTelNum_C0.setErrorInfo(1, NWAM0832E);
                scrnMsg.C.no(i).ctacPsnExtnNum_C0.setErrorInfo(1, NWAM0832E);
                scrnMsg.C.no(i).ctacPsnEmlAddr_C0.setErrorInfo(1, NWAM0832E);
                scrnMsg.C.no(i).ctacPsnFaxNum_C0.setErrorInfo(1, NWAM0832E);
            }
            // QC#14143 2016/11/07 Add End
        }
        
    }
    // 2016/06/06 S21_NA#7088 Add End

    // 2016/07/11 S21_NA#5030 Add start
    /**
     * Checking RadioButton
     * @param scrnMsg NWAL1510BMsg
     */
    public static void checkRadioButton(NWAL1510BMsg scrnMsg) {

        String tabTp = scrnMsg.xxDplyTab.getValue();
        boolean massApplyMode = ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_MU.getValue()); // 2018/07/18 S21_NA#26188 Add
        if (TAB_INSTALL.equals(tabTp)) {
            // 2018/07/18 S21_NA#26188 Mod Condition Start
            // if (!ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_DI)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_DI) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_D1.getValue()) || !massApplyMode)) {
                scrnMsg.loadDockAvalFlg_DI.setErrorInfo(1, NWAM0827E);
            }
            // if (!ZYPCommonFunc.hasValue(scrnMsg.stairCrawReqFlg_DI)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.stairCrawReqFlg_DI) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_D2.getValue()) || !massApplyMode)) {
                scrnMsg.stairCrawReqFlg_DI.setErrorInfo(1, NWAM0827E);
            }
            // if (!ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_DI)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_DI) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_D3.getValue()) || !massApplyMode)) {
                scrnMsg.elevAvalFlg_DI.setErrorInfo(1, NWAM0827E);
            }
        } else if (TAB_SURVEY.equals(tabTp)) {
            boolean allEdtMode = ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxEdtModeFlg_SS.getValue()); // 2018/07/19 S21_NA#26188 Add
            // if (!ZYPCommonFunc.hasValue(scrnMsg.elevProtReqFlg_SS)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevProtReqFlg_SS) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_S3.getValue()) || !massApplyMode || allEdtMode)) {
                scrnMsg.elevProtReqFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            // if (!ZYPCommonFunc.hasValue(scrnMsg.stairCrawReqFlg_SS)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.stairCrawReqFlg_SS) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_S4.getValue()) || !massApplyMode || allEdtMode)) {
                scrnMsg.stairCrawReqFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            // if (!ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_SS)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.loadDockAvalFlg_SS) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_S5.getValue()) || !massApplyMode || allEdtMode)) {
                scrnMsg.loadDockAvalFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            // if (!ZYPCommonFunc.hasValue(scrnMsg.trctrAndTrailAccsFlg_SS)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.trctrAndTrailAccsFlg_SS) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SC.getValue()) || !massApplyMode || allEdtMode)) {
                scrnMsg.trctrAndTrailAccsFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            // if (!ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_SS)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevAvalFlg_SS) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SF.getValue()) || !massApplyMode || allEdtMode)) {
                scrnMsg.elevAvalFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            // if (!ZYPCommonFunc.hasValue(scrnMsg.elevApptReqFlg_SS)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.elevApptReqFlg_SS) && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_SH.getValue()) || !massApplyMode || allEdtMode)) {
                scrnMsg.elevApptReqFlg_SS.setErrorInfo(1, NWAM0827E);
            }
            // 2018/07/18 S21_NA#26188 Mod Condition End
            // 2018/07/19 S21_NA#26188 Add Start
            if (massApplyMode && !ZYPCommonFunc.hasValue(scrnMsg.xxEdtModeFlg_SS)) {
                scrnMsg.xxEdtModeFlg_SS.setErrorInfo(1, NWAM0827E);
            }
        } else if (TAB_CONTACT.equals(tabTp)){
            if (massApplyMode && !ZYPCommonFunc.hasValue(scrnMsg.xxEdtModeFlg_CT)) {
                scrnMsg.xxEdtModeFlg_CT.setErrorInfo(1, NWAM0827E);
            }
        }
        // 2018/07/19 S21_NA#26188 Add End
    }
    // 2016/07/11 S21_NA#5030 Add End

    // Add Start 2016/09/20 QC#13738
    /**
     * <pre>
     * checkValDigit
     * </pre>
     * @param targetLength int
     * @param checkAttrb EZDItemAttribute
     * @return result False : NG True : OK
     */
    public static boolean checkValDigit(int targetLength, EZDItemAttribute checkAttrb) {

        if (checkAttrb == null) {
            return false;
        }

        if (targetLength > checkAttrb.getDigit()) {
            return true;
        }
        return false;
    }
    // Add End 2016/09/20 QC#13738

    // S21_NA#16035 Add Start
    /**
     * Set Screen Protect By Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1510BMsg
     */
    public static void setProtectByAuthority(EZDCommonHandler handler, NWAL1510BMsg scrnMsg) {

        // Only Reference Authority
        if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(scrnMsg.Z.no(0).xxFuncId.getValue())) {
            handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 0, null);
            handler.setButtonEnabled(BTN_ADD_CTAC_EVENT_NM, false);
            handler.setButtonEnabled(BTN_DEL_CTAC_EVENT_NM, false);
        }
    }
    // S21_NA#16035 Add End

    // QC#16452 add Start
    /**
     * Set Screen Protect By Contact
     * @param scrnMsg NWAL1840BMsg
     */
    public static void setProtectByContact(NWAL1510BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1510_CBMsg ctacMsg = scrnMsg.C.no(i);

            if (ZYPCommonFunc.hasValue(ctacMsg.dsCpoCtacPsnPk_C0)) {
                ctacMsg.ctacPsnTpCd_C0.setInputProtected(true);
                ctacMsg.ctacPsnFirstNm_C0.setInputProtected(true);
                ctacMsg.ctacPsnFirstNm_LK.clear();
                ctacMsg.ctacPsnLastNm_C0.setInputProtected(true);
                ctacMsg.ctacCustRefTpCd_C0.setInputProtected(true);

            } else if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnPk_C0)) {
                if (ZYPCommonFunc.hasValue(ctacMsg.ctacCustRefTpCd_CP)) {
                    ctacMsg.ctacPsnTpCd_C0.setInputProtected(true);
                    ctacMsg.ctacPsnFirstNm_C0.setInputProtected(true);
                    ctacMsg.ctacPsnFirstNm_LK.clear();
                    ctacMsg.ctacPsnLastNm_C0.setInputProtected(true);
                    ctacMsg.ctacCustRefTpCd_C0.setInputProtected(true);
                }
            }
        }
    }
    // QC#16452 add End
    
    // 2018/07/18 S21_NA#26188 Add Start
    public static void checkMassApplyCheckBox(NWAL1510BMsg scrnMsg){

        String tabTp = scrnMsg.xxDplyTab.getValue();
        boolean massApplyMode = ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_MU.getValue());
        if(!massApplyMode){
            return;
        }
        if (TAB_INSTALL.equals(tabTp)) {
            if(!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D0) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D1) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D2) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D3) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D4) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D5) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D7) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D9) && //
                    // 2019/12/20 QC#54725 Add Start
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_DB) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_DC) && //
                    // 2019/12/20 QC#54725 Add End
                    // 2019/10/30 QC#53993 Add Start
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_DA)){
                    // 2019/10/30 QC#53993 Add End
                scrnMsg.xxChkBox_D0.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D1.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D2.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D3.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D4.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D5.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D6.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D7.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D8.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_D9.setErrorInfo(1, NWAM0186E);
                // 2019/10/30 QC#53993 Add Start
                scrnMsg.xxChkBox_DA.setErrorInfo(1, NWAM0186E);
                // 2019/10/30 QC#53993 Add End
                // 2019/12/20 QC#54725 Add Start
                scrnMsg.xxChkBox_DB.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_DC.setErrorInfo(1, NWAM0186E);
                // 2019/12/20 QC#54725 Add End
            }
        } else if(TAB_SURVEY.equals(tabTp)){
            if(ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxEdtModeFlg_SS.getValue()) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_S0) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_S1) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_S2) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_S3) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_S4) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_S5) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SC) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SD) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SE) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SF) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SG) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SH) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SI) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SJ) && //
                    !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SK)) {
                scrnMsg.xxChkBox_S0.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_S1.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_S2.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_S3.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_S4.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_S5.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SC.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SD.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SE.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SF.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SG.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SH.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SI.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SJ.setErrorInfo(1, NWAM0186E);
                scrnMsg.xxChkBox_SK.setErrorInfo(1, NWAM0186E);
            }
        }
    }
    // 2018/07/18 S21_NA#26188 Add End
}
