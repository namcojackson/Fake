/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740.common;

import static business.servlet.NSAL0740.constant.NSAL0740Constant.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0740.NSAL0740BMsg;
import business.servlet.NSAL0740.NSAL0740_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         Y.Takeno        Create          N/A
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1736
 * 2015/12/11   Hitachi         T.Tsuchida      Update          QC#1611
 * 2016/02/08   Hitachi         T.Aoyagi        Update          QC#4089
 * 2016/03/07   Hitachi         T.Aoyagi        Update          QC#3945
 * 2016/03/16   Hitachi         M.Gotou         Update          QC#4090
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#4472
 * 2016/05/26   Hitachi         M.Gotou         Update          QC#4472
 * 2016/07/06   Hitachi         T.Tomita        Update          QC#11031
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#8608
 * 2016/12/05   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/08/22   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2017/09/05   CITS            M.Naito         Update          QC#18724
 * 2017/09/08   Hitachi         K.Kim           Update          QC#20880
 * 2017/10/27   Hitachi         M.Kidokoro      Update          QC#21672
 * 2017/11/02   Hitachi         K.Kojima        Update          QC#21672-1
 * 2017/11/06   Hitachi         M.Kidokoro      Update          QC#21932
 * 2017/11/08   Hitachi         M.Kidokoro      Update          QC#21928
 * 2017/11/15   Hitachi         M.Kidokoro      Update          QC#21928-1
 * 2018/05/30   CITS            M.Naito         Update          QC#22887
 * 2018/07/27   Hitachi         A.Kohinata      Update          QC#27445
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 * 2018/11/28   Hitachi         T.Tomita        Update          QC#28638
 * 2019/01/24   Fujitsu         R.Nakamura      Update          QC#29782
 * 2019/11/19   Hitachi         Y.Takeno        Update          QC#52179
 *</pre>
 */
public class NSAL0740CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0700BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0740BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * init Common Button Control.
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0740BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0740BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        // START 2019/11/19 [QC#52179, MOD]
        // handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        // END   2019/11/19 [QC#52179, MOD]
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
     // START 2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
     // END   2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * control screen fields.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0740BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0740BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0740BMsg scrnMsg) {

        // START 2019/11/19 [QC#52179, MOD]
        scrnMsg.contrUplftTpCd_H3.setInputProtected(true);
        scrnMsg.uplftPrcMethCd_H3.setInputProtected(true);
        scrnMsg.uplftBasePrcUpRatio_H1.setInputProtected(true);
        scrnMsg.uplftMtrPrcUpRatio_H1.setInputProtected(true);
        scrnMsg.svcMemoRsnCd_H3.setInputProtected(true);
        scrnMsg.svcCmntTxt_H1.setInputProtected(true);
        scrnMsg.befEndUplftDaysAot_H1.setInputProtected(true);
        scrnMsg.xxChkBox_X1.setInputProtected(true);
        scrnMsg.xxChkBox_X2.setInputProtected(true);

        handler.setButtonEnabled(BTN_APPLY_TO_ALL, false);

        if (isUpdatable()) {
            scrnMsg.contrUplftTpCd_H3.setInputProtected(false);
            scrnMsg.uplftPrcMethCd_H3.setInputProtected(false);
            scrnMsg.uplftBasePrcUpRatio_H1.setInputProtected(false);
            scrnMsg.uplftMtrPrcUpRatio_H1.setInputProtected(false);
            scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
            scrnMsg.svcCmntTxt_H1.setInputProtected(false);
            // QC#18799 ADD START
            scrnMsg.befEndUplftDaysAot_H1.setInputProtected(false);
            // QC#18799 ADD END
            scrnMsg.xxChkBox_X1.setInputProtected(false);
            scrnMsg.xxChkBox_X2.setInputProtected(false);

            handler.setButtonEnabled(BTN_APPLY_TO_ALL, true);
        }
        // END   2019/11/19 [QC#52179, MOD]

        // START 2018/05/30 M.Naito [QC#22887, MOD]
//        scrnMsg.uplftBasePrcUpRatio_H1.setAppFracDigit(0);
//        scrnMsg.uplftMtrPrcUpRatio_H1.setAppFracDigit(0);
        scrnMsg.uplftBasePrcUpRatio_H1.setAppFracDigit(2);
        scrnMsg.uplftMtrPrcUpRatio_H1.setAppFracDigit(2);
        // END 2018/05/30 M.Naito [QC#22887, MOD]

        // add start 2016/07/13 CSA Defect#8608
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg_H1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg_H1.getValue())) {
            scrnMsg.uplftPrcMethCd_H3.setInputProtected(true);
            scrnMsg.uplftBasePrcUpRatio_H1.setInputProtected(true);
            scrnMsg.uplftMtrPrcUpRatio_H1.setInputProtected(true);
            // QC#18799 ADD START
            scrnMsg.befEndUplftDaysAot_H1.setInputProtected(true);
            // QC#18799 ADD END
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd_H3) || UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.uplftPrcMethCd_H3.getValue())) {
            scrnMsg.uplftBasePrcUpRatio_H1.setInputProtected(true);
            scrnMsg.uplftMtrPrcUpRatio_H1.setInputProtected(true);
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg_H1.getValue())) {
            scrnMsg.uplftBasePrcUpRatio_H1.setInputProtected(true);
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg_H1.getValue())) {
            scrnMsg.uplftMtrPrcUpRatio_H1.setInputProtected(true);
        }
        // add end 2016/07/13 CSA Defect#8608
        // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_X1, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_X2, ZYPConstant.CHKBOX_ON_Y);
        // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
    }

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0740BMsg scrnMsg) {

        int idxDsContract = 0;
        // mod start 2016/12/05 CSA QC#4210
//        int idxDsContractDtl = 0;

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        // mod start 2016/07/06 CSA Defect#11031
        int durCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (LINE_LEVEL_CONTRACT.equals(scrnMsg.A.no(i).dsContrMachLvlNum_D1.getValue())) {
                idxDsContract = i;
                durCnt = 0;
                // START 2017/11/02 K.Kojima [QC#21672-1,ADD]
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxNum_Y)) {
                    durCnt = scrnMsg.A.no(i).xxNum_Y.getValueInt();
                }
                // END 2017/11/02 K.Kojima [QC#21672-1,ADD]
            }
            if (LINE_LEVEL_CONTRACT_DETAIL.equals(scrnMsg.A.no(i).dsContrMachLvlNum_D1.getValue())) {
//                idxDsContractDtl = i;
                durCnt = calcContrDuration(scrnMsg, i);
            }

            // START 2019/11/19 [QC#52179, MOD]
            // boolean isContractProtected = isProtectedCheckBoxContract(scrnMsg.A.no(idxDsContract));
            // boolean isSerialProtected = isProtectedCheckBoxSerial(scrnMsg.A.no(i), isContractProtected, durCnt);
            boolean isContractProtected = !isUpdatable() || isProtectedCheckBoxContract(scrnMsg.A.no(idxDsContract));
            boolean isSerialProtected = !isUpdatable() || isProtectedCheckBoxSerial(scrnMsg.A.no(i), isContractProtected, durCnt);
            // mod end 2016/12/05 CSA QC#4210
            // boolean isBaseOverageProtected = isProtectedCheckBoxBaseOverage(scrnMsg.A.no(i), isContractProtected, isSerialProtected);
            boolean isBaseOverageProtected = !isUpdatable() || isProtectedCheckBoxBaseOverage(scrnMsg.A.no(i), isContractProtected, isSerialProtected);
            // END   2019/11/19 [QC#52179, MOD]
            // START 2017/11/06 M.Kidokoro [QC#21932, MOD]
//          boolean isEscalationYearProtected = isProtectedCheckBoxEscalationYear(scrnMsg.A.no(i), isSerialProtected);
            // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//            boolean isEscalationYearProtected = isProtectedCheckBoxEscalationYear(scrnMsg.A.no(i), isContractProtected, isSerialProtected, isBaseOverageProtected);
            // END 2018/11/16 K.Kitachi [QC#28638, DEL]
            // END 2017/11/06 M.Kidokoro [QC#21932, MOD]

            scrnMsg.A.no(i).xxScrItem34Txt_D1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_S1.setInputProtected(isContractProtected);
            if (!isContractProtected && isSerialProtected) {
                scrnMsg.A.no(i).xxChkBox_S1.setInputProtected(isSerialProtected);
            }
            // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
            if (!isContractProtected && !isSerialProtected) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_S1, ZYPConstant.CHKBOX_ON_Y);
            }
            // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

            scrnMsg.A.no(i).serNum_D1.setInputProtected(true);
            // Add Start 2019/01/24 QC#29782
            scrnMsg.A.no(i).svcMachMstrPk_D1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_D1.setInputProtected(true);
            // Add End 2019/01/24 QC#29782

            scrnMsg.A.no(i).xxChkBox_S2.setInputProtected(isBaseOverageProtected);
            // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
            if (!isBaseOverageProtected) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_S2, ZYPConstant.CHKBOX_ON_Y);
            }
            // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

            scrnMsg.A.no(i).dsContrBaseUsgNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).contrEffFromDt_D1.setInputProtected(true);
            scrnMsg.A.no(i).contrEffThruDt_D1.setInputProtected(true);

            if (LINE_LEVEL_CONTRACT.equals(scrnMsg.A.no(i).dsContrMachLvlNum_D1.getValue())) {
                scrnMsg.A.no(i).contrUplftTpCd_D3.setInputProtected(isContractProtected);
                scrnMsg.A.no(i).uplftPrcMethCd_D3.setInputProtected(isContractProtected);
                scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1.setInputProtected(isContractProtected);
                scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(isContractProtected);
                scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(isContractProtected);
                // START 2018/11/16 K.Kitachi [QC#28638, ADD]
                // Mod Start 2018/11/28 QC#28638
                scrnMsg.A.no(i).maxPrcUpRatio_D1.setInputProtected(isContractProtected);
                // Mod End 2018/11/28 QC#28638
                scrnMsg.A.no(i).fixTermInMthAot_D1.setInputProtected(isContractProtected);
                scrnMsg.A.no(i).uplftFixedDt_D1.setInputProtected(isContractProtected);
                // END 2018/11/16 K.Kitachi [QC#28638, ADD]
            } else if (LINE_LEVEL_CONTRACT_DETAIL.equals(scrnMsg.A.no(i).dsContrMachLvlNum_D1.getValue())) {
                scrnMsg.A.no(i).contrUplftTpCd_D3.setInputProtected(isSerialProtected);
                scrnMsg.A.no(i).uplftPrcMethCd_D3.setInputProtected(isSerialProtected);
                scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1.setInputProtected(isSerialProtected);
                scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(isSerialProtected);
                scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(isSerialProtected);
                // START 2018/11/16 K.Kitachi [QC#28638, ADD]
                // Mod Start 2018/11/28 QC#28638
                scrnMsg.A.no(i).maxPrcUpRatio_D1.setInputProtected(isSerialProtected);
                // Mod End 2018/11/28 QC#28638
                scrnMsg.A.no(i).fixTermInMthAot_D1.setInputProtected(isSerialProtected);
                scrnMsg.A.no(i).uplftFixedDt_D1.setInputProtected(isSerialProtected);
                // END 2018/11/16 K.Kitachi [QC#28638, ADD]
                // Add Start 2018/11/28 QC#28638
                if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.A.no(i).dsContrCatgCd_D1.getValue())) {
                    if (DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.A.no(i).dsContrDtlTpCd_D1.getValue())) {
                        // Aggregate Line
                        scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(true);
                        scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(isSerialProtected);
                    } else {
                        scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(isSerialProtected);
                        scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(true);
                    }
                }
                // Add End 2018/11/28 QC#28638
            } else if (LINE_LEVEL_BASE_OVERAGE.equals(scrnMsg.A.no(i).dsContrMachLvlNum_D1.getValue())) {
                scrnMsg.A.no(i).contrUplftTpCd_D3.setInputProtected(isBaseOverageProtected);
                scrnMsg.A.no(i).uplftPrcMethCd_D3.setInputProtected(isBaseOverageProtected);
                scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1.clear();
                scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1.setInputProtected(true);
                // START 2018/11/16 K.Kitachi [QC#28638, ADD]
                scrnMsg.A.no(i).maxPrcUpRatio_D1.setInputProtected(isBaseOverageProtected);
                scrnMsg.A.no(i).fixTermInMthAot_D1.setInputProtected(isBaseOverageProtected);
                scrnMsg.A.no(i).uplftFixedDt_D1.setInputProtected(isBaseOverageProtected);
                // END 2018/11/16 K.Kitachi [QC#28638, ADD]
                if (BASE.equals(scrnMsg.A.no(i).dsContrBaseUsgNm_D1.getValue())) {
                    scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(isBaseOverageProtected);
                    scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(true);
                } else if (OVERAGE.equals(scrnMsg.A.no(i).dsContrBaseUsgNm_D1.getValue())) {
                    scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(true);
                    scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(isBaseOverageProtected);
                }
            }

            // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//            scrnMsg.A.no(i).xxChkBox_E1.setInputProtected(isEscalationYearProtected);
//            scrnMsg.A.no(i).xxChkBox_E2.setInputProtected(isEscalationYearProtected);
//            scrnMsg.A.no(i).xxChkBox_E3.setInputProtected(isEscalationYearProtected);
//            scrnMsg.A.no(i).xxChkBox_E4.setInputProtected(isEscalationYearProtected);
//            scrnMsg.A.no(i).xxChkBox_E5.setInputProtected(isEscalationYearProtected);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).uplftPrcMethCd_D3)) {
                scrnMsg.A.no(i).maxPrcUpRatio_D1.setInputProtected(true);
                scrnMsg.A.no(i).fixTermInMthAot_D1.setInputProtected(true);
                scrnMsg.A.no(i).uplftFixedDt_D1.setInputProtected(true);
            }
            scrnMsg.A.no(i).uplftPcyDt_D1.setInputProtected(true);
            // END 2018/11/16 K.Kitachi [QC#28638, MOD]

            scrnMsg.A.no(i).xxGenlFldAreaTxt_D1.setInputProtected(true);

            // add start 2016/07/13 CSA Defect#8608
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).uplftBaseFlg_D1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).uplftUsgFlg_D1.getValue())) {
                scrnMsg.A.no(i).uplftPrcMethCd_D3.setInputProtected(true);
                scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1.setInputProtected(true);
                scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(true);
                scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(true);
                // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//                scrnMsg.A.no(i).xxChkBox_E1.setInputProtected(true);
//                scrnMsg.A.no(i).xxChkBox_E2.setInputProtected(true);
//                scrnMsg.A.no(i).xxChkBox_E3.setInputProtected(true);
//                scrnMsg.A.no(i).xxChkBox_E4.setInputProtected(true);
//                scrnMsg.A.no(i).xxChkBox_E5.setInputProtected(true);
                // END 2018/11/16 K.Kitachi [QC#28638, DEL]
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).uplftPrcMethCd_D3) || UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.A.no(i).uplftPrcMethCd_D3.getValue())) {
                scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(true);
                scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(true);
            } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).uplftBaseFlg_D1.getValue())) {
                scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setInputProtected(true);
            } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).uplftUsgFlg_D1.getValue())) {
                scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setInputProtected(true);
            }
            // add end 2016/07/13 CSA Defect#8608

            // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
//            scrnMsg.A.no(i).basePrcUpRatio_D1.setAppFracDigit(0);
//            scrnMsg.A.no(i).mtrPrcUpRatio_D1.setAppFracDigit(0);
            // START 2018/05/30 M.Naito [QC#22887, MOD]
//            scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setAppFracDigit(0);
//            scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setAppFracDigit(0);
            scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setAppFracDigit(2);
            scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setAppFracDigit(2);
            // END 2018/05/30 M.Naito [QC#22887, MOD]
            // END   2016/02/08 T.Aoyagi [QC#4089, MOD]
            // START 2018/11/16 K.Kitachi [QC#28638, ADD]
            scrnMsg.A.no(i).maxPrcUpRatio_D1.setAppFracDigit(2);
            // END 2018/11/16 K.Kitachi [QC#28638, ADD]

            // START 2017/10/27 M.Kidokoro [QC#21672, MOD]
//            isVisibilityContract(scrnMsg, i);
            // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//            isVisibilityContract(scrnMsg, i, isEscalationYearProtected);
            isVisibilityContract(scrnMsg, i);
            // END 2018/11/16 K.Kitachi [QC#28638, MOD]
            // END 2017/10/27 M.Kidokoro [QC#21672, MOD]
            isVisibilitySerial(scrnMsg, i);
            // START 2017/09/08 K.Kim [QC#20880, MOD]
//            isVisibilityBaseOverage(scrnMsg, i);
            // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//            isVisibilityBaseOverage(scrnMsg, i, isEscalationYearProtected);
            isVisibilityBaseOverage(scrnMsg, i);
            // END 2018/11/16 K.Kitachi [QC#28638, MOD]
            // END 2017/09/08 K.Kim [QC#20880, MOD]

            // Del Start 2018/11/28 QC#28638
//            // add start 2016/03/16 CSA Defect#4090
//            setUpliftFields(scrnMsg, i, durCnt);
//            // add end 2016/03/16 CSA Defect#4090
            // Del Start 2018/11/28 QC#28638
        }
        // mod end 2016/07/06 CSA Defect#11031
    }

    // Del Start 2018/11/28 QC#28638
//    // add start 2016/03/16 CSA Defect#4090
//    // mod start 2016/07/06 CSA Defect#11031
//    private static void setUpliftFields(NSAL0740BMsg scrnMsg, int idx, int durCnt) {
//        // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//        if (durCnt == 1) {
//            setUnvisibilityItem(scrnMsg, "xxChkBox_S1#" + idx);
//            setUnvisibilityItem(scrnMsg, "xxChkBox_S2#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E1#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E2#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E3#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E4#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
//            scrnMsg.A.no(idx).xxChkBox_S1.setInputProtected(true);
//            scrnMsg.A.no(idx).xxChkBox_S2.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E1.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E2.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E3.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E4.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E5.setInputProtected(true);
////        } else if (durCnt == 2) {
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E2#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E3#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E4#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
////            scrnMsg.A.no(idx).xxChkBox_E2.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E3.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E4.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E5.setInputProtected(true);
////        } else if (durCnt == 3) {
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E3#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E4#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
////            scrnMsg.A.no(idx).xxChkBox_E3.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E4.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E5.setInputProtected(true);
////        } else if (durCnt == 4) {
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E4#" + idx);
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
////            scrnMsg.A.no(idx).xxChkBox_E4.setInputProtected(true);
////            scrnMsg.A.no(idx).xxChkBox_E5.setInputProtected(true);
////        } else if (durCnt == 5) {
////            setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
////            scrnMsg.A.no(idx).xxChkBox_E5.setInputProtected(true);
//        }
//        // END 2018/11/16 K.Kitachi [QC#28638, MOD]
//
//        // del start 2018/07/27 QC#27445
////        // START 2017/09/05 M.Naito [QC#18724, ADD]
////        BigDecimal dsContrPk = scrnMsg.A.no(idx).dsContrPk_D1.getValue();
////        for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
////            if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(i).contrFixedYearsAot_P1)) {
////                continue;
////            }
////            if (dsContrPk.compareTo(scrnMsg.P.no(i).dsContrPk_P1.getValue()) == 0) {
////                int contrFixedYearsAot = scrnMsg.P.no(i).contrFixedYearsAot_P1.getValue().intValue();
////
////                if (contrFixedYearsAot > 1) {
////                    scrnMsg.A.no(idx).xxChkBox_E1.setInputProtected(true);
////                }
////                if (contrFixedYearsAot > 2) {
////                    scrnMsg.A.no(idx).xxChkBox_E2.setInputProtected(true);
////                }
////                if (contrFixedYearsAot > 3) {
////                    scrnMsg.A.no(idx).xxChkBox_E3.setInputProtected(true);
////                }
////                if (contrFixedYearsAot > 4) {
////                    scrnMsg.A.no(idx).xxChkBox_E4.setInputProtected(true);
////                }
////                if (contrFixedYearsAot > 5) {
////                    scrnMsg.A.no(idx).xxChkBox_E5.setInputProtected(true);
////                }
////            }
////        }
////        // END 2017/09/05 M.Naito [QC#18724, ADD]
//        // del end 2018/07/27 QC#27445
//    }
//    // mod end 2016/07/06 CSA Defect#11031
//    // add end 2016/03/16 CSA Defect#4090
    // Del Start 2018/11/28 QC#28638

    /**
     * setRowColors
     * @param scrnMsg NSAL0740BMsg
     */
    private static void setRowColors(NSAL0740BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static boolean isProtectedCheckBoxContract(NSAL0740_ABMsg aBMsg) {
        if (LINE_LEVEL_CONTRACT.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
            if (DS_CONTR_STS.EXPIRED.equals(aBMsg.dsContrStsCd_D1.getValue()) || DS_CONTR_STS.CANCELLED.equals(aBMsg.dsContrStsCd_D1.getValue()) || DS_CONTR_STS.TERMINATED.equals(aBMsg.dsContrStsCd_D1.getValue())) {
                return true;
            }
        }
        return false;
    }

    // add start 2016/07/06 CSA Defect#11031
    private static boolean isProtectedCheckBoxSerial(NSAL0740_ABMsg aBMsg, boolean isContractProtected, int durCnt) {
        if (LINE_LEVEL_CONTRACT_DETAIL.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
            if (isContractProtected == true) {
                return true;
            }

            if (DS_CONTR_DTL_STS.EXPIRED.equals(aBMsg.dsContrDtlStsCd_D1.getValue()) || DS_CONTR_DTL_STS.CANCELLED.equals(aBMsg.dsContrDtlStsCd_D1.getValue()) || DS_CONTR_DTL_STS.TERMINATED.equals(aBMsg.dsContrDtlStsCd_D1.getValue())) {
                return true;
            }

            // Del Start 2018/11/28 QC#28638
            // if (durCnt == 1) {
            //     return true;
            // }
            // Del End 2018/11/28 QC#28638
        }
        return false;
    }
    // add end 2016/07/06 CSA Defect#11031

    private static boolean isProtectedCheckBoxBaseOverage(NSAL0740_ABMsg aBMsg, boolean isContractProtected, boolean isSerialProtected) {
        if (LINE_LEVEL_BASE_OVERAGE.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
            if (isContractProtected == true || isSerialProtected == true) {
                return true;
            }

            if (OVERAGE.equals(aBMsg.dsContrBaseUsgNm_D1.getValue()) && aBMsg.xxNum_D1.getValueInt() > 0 && aBMsg.xxNum_D1.getValueInt() == aBMsg.xxNum_D2.getValueInt()) {
                return true;
            }

            // START 2017/11/06 M.Kidokoro [QC#21932, ADD]
            if (DS_CONTR_DTL_STS.EXPIRED.equals(aBMsg.dsContrDtlStsCd_D1.getValue()) || DS_CONTR_DTL_STS.CANCELLED.equals(aBMsg.dsContrDtlStsCd_D1.getValue()) || DS_CONTR_DTL_STS.TERMINATED.equals(aBMsg.dsContrDtlStsCd_D1.getValue())) {
                return true;
            }
            // END 2017/11/06 M.Kidokoro [QC#21932, ADD]
        }
        return false;
    }

    // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//    // START 2017/11/06 M.Kidokoro [QC#21932, MOD]
////  private static boolean isProtectedCheckBoxEscalationYear(NSAL0740_ABMsg aBMsg, boolean isSerialProtected) {
//    private static boolean isProtectedCheckBoxEscalationYear(NSAL0740_ABMsg aBMsg, boolean isContractProtected, boolean isSerialProtected, boolean isBaseOverageProtected) {
//    // END 2017/11/06 M.Kidokoro [QC#21932, MOD]
//
//        // START 2017/11/06 M.Kidokoro [QC#21932, MOD]
////      // START 2017/10/27 M.Kidokoro [QC#21672, ADD]
////      if (LINE_LEVEL_CONTRACT.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
////          return isSerialProtected;
////      }
////      // END 2017/10/27 M.Kidokoro [QC#21672, ADD]
////      if (LINE_LEVEL_CONTRACT_DETAIL.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
////          return isSerialProtected;
////      }
////      // START 2017/09/08 K.Kim [QC#20880, MOD]
////      if (LINE_LEVEL_BASE_OVERAGE.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
////          return isSerialProtected;
////      }
////      // END 2017/09/08 K.Kim [QC#20880, MOD]
//        if (LINE_LEVEL_CONTRACT.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
//            return isContractProtected;
//        }
//        if (LINE_LEVEL_CONTRACT_DETAIL.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
//            return isSerialProtected;
//        }
//        if (LINE_LEVEL_BASE_OVERAGE.equals(aBMsg.dsContrMachLvlNum_D1.getValue())) {
//            return isBaseOverageProtected;
//        }
//        // END 2017/11/06 M.Kidokoro [QC#21932, MOD]
//
//        return true;
//    }
    // END 2018/11/16 K.Kitachi [QC#28638, DEL]

    private static void setUnvisibilityItem(NSAL0740BMsg scrnMsg, String itemId) {
        EZDGUIAttribute xxItem = new EZDGUIAttribute(SCREEN_ID, itemId);
        xxItem.setVisibility(false);
        scrnMsg.addGUIAttribute(xxItem);
    }

    // START 2017/10/27 M.Kidokoro [QC#21672, MOD]
//    private static void isVisibilityContract(NSAL0740BMsg scrnMsg, int idx) {
    // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//    private static void isVisibilityContract(NSAL0740BMsg scrnMsg, int idx, boolean isEscalationYearProtected) {
    private static void isVisibilityContract(NSAL0740BMsg scrnMsg, int idx) {
    // END 2018/11/16 K.Kitachi [QC#28638, MOD]
    // END 2017/10/27 M.Kidokoro [QC#21672, MOD]
        if (LINE_LEVEL_CONTRACT.equals(scrnMsg.A.no(idx).dsContrMachLvlNum_D1.getValue())) {
            setUnvisibilityItem(scrnMsg, "xxChkBox_S2#" + idx);

            // START 2017/10/27 M.Kidokoro [QC#21672, MOD]
//            setUnvisibilityItem(scrnMsg, "xxChkBox_E1#" + idx);
//            setUnvisibilityItem(scrnMsg, "xxChkBox_E2#" + idx);
//            setUnvisibilityItem(scrnMsg, "xxChkBox_E3#" + idx);
//            setUnvisibilityItem(scrnMsg, "xxChkBox_E4#" + idx);
//            setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
            // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//            if (isEscalationYearProtected) {
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E1#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E2#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E3#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E4#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
//            }
            // END 2018/11/16 K.Kitachi [QC#28638, DEL]
            // END 2017/10/27 M.Kidokoro [QC#21672, MOD]
        }
    }

    private static void isVisibilitySerial(NSAL0740BMsg scrnMsg, int idx) {
        if (LINE_LEVEL_CONTRACT_DETAIL.equals(scrnMsg.A.no(idx).dsContrMachLvlNum_D1.getValue())) {
            setUnvisibilityItem(scrnMsg, "xxChkBox_S2#" + idx);
        }
    }

    // START 2017/09/08 K.Kim [QC#20880, MOD]
//    private static void isVisibilityBaseOverage(NSAL0740BMsg scrnMsg, int idx) {
    // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//    private static void isVisibilityBaseOverage(NSAL0740BMsg scrnMsg, int idx, boolean isEscalationYearProtected) {
    private static void isVisibilityBaseOverage(NSAL0740BMsg scrnMsg, int idx) {
    // END 2018/11/16 K.Kitachi [QC#28638, MOD]
    // START 2017/09/08 K.Kim [QC#20880, MOD]
        if (LINE_LEVEL_BASE_OVERAGE.equals(scrnMsg.A.no(idx).dsContrMachLvlNum_D1.getValue())) {
            setUnvisibilityItem(scrnMsg, "xxChkBox_S1#" + idx);

            // START 2017/09/08 K.Kim [QC#20880, ADD]
            // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//            if (isEscalationYearProtected) {
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E1#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E2#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E3#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E4#" + idx);
//                setUnvisibilityItem(scrnMsg, "xxChkBox_E5#" + idx);
//            }
            // END 2018/11/16 K.Kitachi [QC#28638, DEL]
            // END 2017/09/08 K.Kim [QC#20880, ADD]
        }
    }

    // add start 2016/05/12 CSA Defect#4472
    /**
     * get LowerLines For Contract
     * @param scrnMsg NSAL0740_BMsg
     * @param currentCnt int
     * @return List<NSAL0740_ABMsg>
     */
    public static List<NSAL0740_ABMsg> getLowerLinesForContract(NSAL0740BMsg scrnMsg, int currentCnt) {

        NSAL0740_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0740_ABMsg> lowerList = new ArrayList<NSAL0740_ABMsg>();
        // START 2017/11/15 M.Kidokoro [QC#21928-1, ADD]
        if (LINE_LEVEL_BASE_OVERAGE.equals(abMsg.dsContrMachLvlNum_D1.getValue())) {
            return lowerList;
        }
        // END 2017/11/15 M.Kidokoro [QC#21928-1, ADD]
        for (int i = currentCnt + 1; i < scrnMsg.A.getValidCount(); i++) {
            if (abMsg.dsContrPk_D1.getValueInt() != scrnMsg.A.no(i).dsContrPk_D1.getValueInt()) {
                return lowerList;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_S1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_S2.getValue())) {
                lowerList.add(scrnMsg.A.no(i));
            }
        }
        return lowerList;
    }

    /**
     * get LowerLines For SerNum
     * @param scrnMsg NSAL0740_BMsg
     * @param currentCnt int
     * @return List<NSAL0740_ABMsg>
     */
    public static List<NSAL0740_ABMsg> getLowerLinesForSer(NSAL0740BMsg scrnMsg, int currentCnt) {

        NSAL0740_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0740_ABMsg> lowerList = new ArrayList<NSAL0740_ABMsg>();
        for (int i = currentCnt + 1; i < scrnMsg.A.getValidCount(); i++) {
            if (abMsg.dsContrDtlPk_D1.getValueInt() != scrnMsg.A.no(i).dsContrDtlPk_D1.getValueInt()) {
                return lowerList;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_S1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_S2.getValue())) {
                lowerList.add(scrnMsg.A.no(i));
            }
        }
        return lowerList;
    }
    // add end 2016/05/12 CSA Defect#4472
    // add start 2016/07/06 CSA Defect#11031
    private static int calcContrDuration(NSAL0740BMsg scrnMsg, int idx) {
        String contrEffFromDt = scrnMsg.A.no(idx).contrEffFromDt_D1.getValue();
        String contrEffThruDt = scrnMsg.A.no(idx).contrEffThruDt_D1.getValue();
        if (!ZYPCommonFunc.hasValue(contrEffFromDt) || !ZYPCommonFunc.hasValue(contrEffThruDt)) {
            return 0;
        }
        return getContrDuration(contrEffFromDt, contrEffThruDt);
    }

    private static int getContrDuration(String contrEffFromDt, String contrEffThruDt) {

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(contrEffFromDt);
        } catch (ParseException e) {
            throw new S21AbendException(e);
        }

        String endDate = contrEffThruDt;

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDt);
        String calcEndDate = "";
        int durCnt = 1;
        while (endDate.compareTo(calcEndDate) > 0) {
            cal.add(Calendar.YEAR, 1);
            calcEndDate = df.format(cal.getTime());
            Date extDt;
            try {
                extDt = df.parse(calcEndDate);
            } catch (ParseException e) {
                throw new S21AbendException(e);
            }
            cal.setTime(extDt);
            durCnt++;
        }
        return (durCnt - 1);
    }
    // add end 2016/07/06 CSA Defect#11031

    // START 2019/11/19 [QC#52179, ADD]
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCTION_ID_UPDATE);
    }
    // END   2019/11/19 [QC#52179, ADD]
}
