/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490.common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6800.constant.NMAL6800Constant;
import business.servlet.NSAL0490.NSAL0490BMsg;
import business.servlet.NSAL0490.NSAL0490Bean;
import business.servlet.NSAL0490.NSAL0490_ABMsg;
import business.servlet.NSAL0490.NSAL0490_BBMsg;
import business.servlet.NSAL0490.NSAL0490_DBMsg;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MDL_EOL_STS;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2015/11/30   Hitachi         K.Yamada        Update          CSA QC#1158
 * 2016/02/04   Hitachi         T.Nishimura     Update          QC#4121
 * 2016/02/10   Hitachi         A.Kohinata      Update          CSA QC#1157
 * 2016/03/01   Hitachi         K.Kasai         Update          QC#3586
 * 2016/05/19   Hitachi         K.Kasai         Update          QC#447
 * 2016/05/30   Hitachi         K.Kasai         Update          QC#6675
 * 2017/01/23   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/07/25   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 2017/08/14   Fujitsu         T.Murai         Update          QC#20618
 * 2017/11/02   CITS            S.Katsuma       Update          SOL#170(QC#18624)
 * 2017/12/22   Hitachi         U.Kim           Update          QC#22448
 * 2018/01/15   Hitachi         K.Kojima        Update          QC#23352
 * 2019/02/21   Hitachi         K.Fujimoto      Update          QC#30366
 *</pre>
 */
public class NSAL0490CommonLogic {

    /**
     * init Control CommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_SAVE_BTN_NM, "", NSAL0490Constant.BTN_CMN_SAVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_SUBMIT_BTN_NM, NSAL0490Constant.BTN_CMN_SUBMIT_EVENT_NM, NSAL0490Constant.BTN_CMN_SUBMIT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_APPLY_BTN_NM, "", NSAL0490Constant.BTN_CMN_APPLY_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_APPROVE_BTN_NM, "", NSAL0490Constant.BTN_CMN_APPROVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_REJECT_BTN_NM, "", NSAL0490Constant.BTN_CMN_REJECT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_DOWNLOAD_BTN_NM, "", NSAL0490Constant.BTN_CMN_DOWNLOAD_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_DELETE_BTN_NM, "", NSAL0490Constant.BTN_CMN_DELETE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_CLEAR_BTN_NM, NSAL0490Constant.BTN_CMN_CLEAR_EVENT_NM, NSAL0490Constant.BTN_CMN_CLEAR_LABEL, 0, null);
        // START 2017/01/23 K.Ochiai [QC#16331,MOD]
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_RESET_BTN_NM, NSAL0490Constant.BTN_CMN_RESET_EVENT_NM, NSAL0490Constant.BTN_CMN_RESET_LABEL, 0, null);
        // END 2017/01/23 K.Ochiai [QC#16331,MOD]
        scrnAppli.setButtonProperties(NSAL0490Constant.BTN_CMN_RETURN_BTN_NM, NSAL0490Constant.BTN_CMN_RETURN_EVENT_NM, NSAL0490Constant.BTN_CMN_RETURN_LABEL, 0, null);
    }

    /**
     * control CommonButton
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlCommonButton(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg, boolean isActive) {

        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_CMN_SUBMIT_BTN_NM, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_CMN_CLEAR_BTN_NM, true);
        // START 2017/01/23 K.Ochiai [QC#16331,MOD]
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_CMN_RESET_BTN_NM, true);
        // END 2017/01/23 K.Ochiai [QC#16331,MOD]
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_CMN_RETURN_BTN_NM, true);
    }

    /**
     * control All Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlInitField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg, boolean isActive) {

        // button
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_CONTR_UPLFT, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_OPEN_MDSE, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SET_MDSE, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_PRNT, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_CLD, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_SUPPLY_MAP, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_ITEM_CONFIG, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_SUPPLY_MAP, isActive);

        // field
        NSAL0490CommonLogic.controlSearchField(scrnAppli, scrnMsg, isActive);
        scrnMsg.mdlDescTxt.setInputProtected(!isActive);
        scrnMsg.mdlGrpNm_LK.setInputProtected(!isActive);
        scrnMsg.mdlGrpNm.setInputProtected(!isActive);
        scrnMsg.svcSegCd.setInputProtected(!isActive);
        scrnMsg.mdlActvFlg.setInputProtected(!isActive);
        scrnMsg.ezInTime_CD.setInputProtected(true);
        scrnMsg.mtrGrpPk.setInputProtected(!isActive);
        scrnMsg.svcSkillNum.setInputProtected(!isActive);
        // START 2016/02/10 A.Kohinata [QC#1157, ADD]
        scrnMsg.svcSkillDescTxt_LK.setInputProtected(!isActive);
        scrnMsg.svcSkillDescTxt.setInputProtected(true);
        // END 2016/02/10 A.Kohinata [QC#1157, ADD]
        scrnMsg.svcIstlRuleNum.setInputProtected(!isActive);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        scrnMsg.custIstlFlg.setInputProtected(!isActive);
        scrnMsg.siteSrvyReqFlg.setInputProtected(!isActive);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        scrnMsg.svcIstlReqFlg.setInputProtected(!isActive);
        scrnMsg.svcDeinsRuleNum.setInputProtected(!isActive);
        // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        scrnMsg.svcLbGrpCd.setInputProtected(!isActive);
        // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        // START 2018/01/15 K.Kojima [QC#23352,ADD]
        scrnMsg.svcMdlTpCd.setInputProtected(!isActive);
        // END 2018/01/15 K.Kojima [QC#23352,ADD]

        if (isActive) {
            scrnMsg.mdlGrpNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            // START 2016/02/10 A.Kohinata [QC#1157, ADD]
            scrnMsg.svcSkillDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            // END 2016/02/10 A.Kohinata [QC#1157, ADD]
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0490_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.prntMdseCd_A.setInputProtected(!isActive);
            abMsg.childMdseCd_A.setInputProtected(!isActive);
            abMsg.effFromDt_A.setInputProtected(!isActive);
            abMsg.effThruDt_A.setInputProtected(!isActive);
        }
    }

    /**
     * control Item Configurations Detail Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlItemConfigDtlField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg, boolean isActive) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0490_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.prntMdseCd_A.setInputProtected(!isActive);
            abMsg.childMdseCd_A.setInputProtected(!isActive);
            abMsg.effFromDt_A.setInputProtected(!isActive);
            abMsg.effThruDt_A.setInputProtected(!isActive);
        }

        setPageForItemConfig(scrnMsg);
    }

    /**
     * control Item Configurations Detail Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlSupplyMapDtlField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg, boolean isActive) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL0490_BBMsg bbMsg = scrnMsg.B.no(i);
            bbMsg.mdseCd_B.setInputProtected(!isActive);
            bbMsg.splyTolPct_B.setInputProtected(!isActive);
            bbMsg.custStkQty_B.setInputProtected(!isActive);
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            bbMsg.splyInitQty_B.setInputProtected(!isActive);
            bbMsg.splyContrQty_B.setInputProtected(!isActive);
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            bbMsg.effFromDt_B.setInputProtected(!isActive);
            bbMsg.effThruDt_B.setInputProtected(!isActive);
        }

        setPageForSupplyMap(scrnMsg);
    }

    /**
     * control Search Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlSearchField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg, boolean isActive) {

        if (isActive) {
            scrnMsg.mdlNm_LK.setInputProtected(!isActive);
            scrnMsg.mdlNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.mdlNm_LK.setInputProtected(!isActive);
        }

        scrnMsg.mdlNm.setInputProtected(!isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SEARCH, isActive);
        // mod start 2016/02/04 CSA Defect#4121
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_CONTR_UPLFT, !isActive);
        // mod end 2016/02/04 CSA Defect#4121
    }

    /**
     * control Header Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlHdrField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg, boolean isActive) {

        // button
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SEARCH, isActive);
        scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_CONTR_UPLFT, isActive);

        // link
        scrnMsg.mdlNm_LK.setInputProtected(!isActive);
        scrnMsg.mdlGrpNm_LK.setInputProtected(!isActive);
        // START 2017/01/23 K.Ochiai [QC#16331,MOD]
        scrnMsg.svcSkillDescTxt_LK.setInputProtected(!isActive);
        // END 2017/01/23 K.Ochiai [QC#16331,MOD]

        // field
        scrnMsg.mdlNm.setInputProtected(!isActive);
        scrnMsg.mdlDescTxt.setInputProtected(!isActive);
        scrnMsg.mdlGrpNm.setInputProtected(!isActive);
        scrnMsg.svcSegCd.setInputProtected(!isActive);
        scrnMsg.mdlActvFlg.setInputProtected(!isActive);
        scrnMsg.ezInTime_CD.setInputProtected(true);
        scrnMsg.mtrGrpPk.setInputProtected(!isActive);
        scrnMsg.svcSkillNum.setInputProtected(!isActive);
        scrnMsg.svcIstlRuleNum.setInputProtected(!isActive);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        scrnMsg.custIstlFlg.setInputProtected(!isActive);
        scrnMsg.siteSrvyReqFlg.setInputProtected(!isActive);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        scrnMsg.svcIstlReqFlg.setInputProtected(!isActive);
        scrnMsg.svcDeinsRuleNum.setInputProtected(!isActive);
        // add start 2015/11/30 CSA Defect#1158
        scrnMsg.svcIstlCallGrpNum_IN.setInputProtected(!isActive);
        scrnMsg.svcIstlCallGrpNum_DE.setInputProtected(!isActive);
        // add end 2015/11/30 CSA Defect#1158
        // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        scrnMsg.svcLbGrpCd.setInputProtected(!isActive);
        // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        // START 2018/01/15 K.Kojima [QC#23352,ADD]
        scrnMsg.svcMdlTpCd.setInputProtected(!isActive);
        // END 2018/01/15 K.Kojima [QC#23352,ADD]
    }

    /**
     * control Item Configurations Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     */
    public static void controlItemConfigField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0490_ABMsg abMsg = scrnMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(abMsg.dsMdlConfigPk_A)) {
                abMsg.prntMdseCd_A.setInputProtected(true);
                abMsg.childMdseCd_A.setInputProtected(true);
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_OPEN_MDSE, i, false);
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SET_MDSE, i, false);
            } else {
                if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
                    abMsg.prntMdseCd_A.setInputProtected(false);
                    abMsg.childMdseCd_A.setInputProtected(true);
                } else {
                    abMsg.prntMdseCd_A.setInputProtected(true);
                    abMsg.childMdseCd_A.setInputProtected(false);
                }
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_OPEN_MDSE, i, true);
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SET_MDSE, i, true);
            }
        }

        // set Page
        setPageForItemConfig(scrnMsg);

        if (scrnMsg.xxPageShowOfNum.getValueInt() == 1) {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_ITEM_CONFIG, false);
        } else {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_ITEM_CONFIG, true);
        }

        if (scrnMsg.xxPageShowOfNum.getValueInt() == scrnMsg.xxMaxSrchCnt.getValueInt()) {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_PRNT, false);
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_CLD, false);
        } else {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_PRNT, true);
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_CLD, true);
        }
    }

    /**
     * control Service Rules Field
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlSvcRulesField(NSAL0490BMsg scrnMsg, boolean isActive) {

        scrnMsg.rcllIntvlDaysAot.setInputProtected(!isActive);
        scrnMsg.rcllCopyVolCnt.setInputProtected(!isActive);
        scrnMsg.rcllGlblIntvlDaysAot.setInputProtected(!isActive);
        scrnMsg.rcllGlblCopyVolCnt.setInputProtected(!isActive);
        // mod start 2016/05/30 CSA Defect#6675
        scrnMsg.xxRtoTaskTmNum.setInputProtected(!isActive);
        // mod start 2016/05/30 CSA Defect#6675
        scrnMsg.xsVisitCnt.setInputProtected(!isActive);
        scrnMsg.phoneFixFlg.setInputProtected(!isActive);
        scrnMsg.phoneFixIntvlDaysAot.setInputProtected(!isActive);
        scrnMsg.afterHourAllwFlg.setInputProtected(!isActive);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        scrnMsg.machInFldInacMthAot.setInputProtected(!isActive);
        scrnMsg.mdlDurnTmNum.setInputProtected(!isActive);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        scrnMsg.copyVolDaysAot.setInputProtected(!isActive);
        scrnMsg.maxCopyPerDayTotCnt.setInputProtected(!isActive);
        scrnMsg.maxCopyPerDayBlackCnt.setInputProtected(!isActive);
        scrnMsg.maxCopyTestCnt.setInputProtected(!isActive);
        scrnMsg.mdlSpeedBlackRate.setInputProtected(!isActive);
        scrnMsg.mdlSpeedColorRate.setInputProtected(!isActive);
        scrnMsg.dsMdlPaperSizeCd.setInputProtected(!isActive);
        // mod start 2016/05/30 CSA Defect#6675
        scrnMsg.xxRtoTaskTmNum.setAppFracDigit(1);
        // mod start 2016/05/30 CSA Defect#6675

        // remove Decimal
        if (ZYPCommonFunc.hasValue(scrnMsg.mdlSpeedBlackRate)) {
            scrnMsg.mdlSpeedBlackRate.setAppFracDigit(0);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.mdlSpeedColorRate)) {
            scrnMsg.mdlSpeedColorRate.setAppFracDigit(0);
        }
    }

    /**
     * control Supply Mapping Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     */
    public static void controlSupplyMapField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL0490_BBMsg bbMsg = scrnMsg.B.no(i);

            if (ZYPCommonFunc.hasValue(bbMsg.dsMdlSplyRelnPk_B)) {
                bbMsg.mdseCd_B.setInputProtected(true);
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_OPEN_MDSE, i, false);
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SET_MDSE, i, false);
            } else {
                bbMsg.mdseCd_B.setInputProtected(false);
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_OPEN_MDSE, i, true);
                scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SET_MDSE, i, true);
            }
        }

        // set Page
        setPageForSupplyMap(scrnMsg);

        if (scrnMsg.xxPageShowOfNum.getValueInt() == 1) {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_SUPPLY_MAP, false);
        } else {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_SUPPLY_MAP, true);
        }

        if (scrnMsg.xxPageShowOfNum.getValueInt() == scrnMsg.xxMaxSrchCnt.getValueInt()) {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_SUPPLY_MAP, false);
        } else {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_SUPPLY_MAP, true);
        }
    }

    // add start 2016/05/19 CSA Defect#447
    /**
     * control Item EndOfLife Field
     * @param scrnMsg scrnMsg
     * @param isActive boolean
     */
    public static void controlEndOfLifeField(NSAL0490BMsg scrnMsg, boolean isActive) {

        scrnMsg.dsMdlEolStsCd_D1.setInputProtected(!isActive);
        scrnMsg.dsMdlEolStsCd_D2.setInputProtected(!isActive);
        scrnMsg.dsMdlEolStsCd_D3.setInputProtected(!isActive);
        scrnMsg.dsMdlEolDt_D1.setInputProtected(!isActive);
        scrnMsg.dsMdlEolDt_D2.setInputProtected(!isActive);
        scrnMsg.dsMdlEolDt_D3.setInputProtected(!isActive);
        scrnMsg.eolSvcContrCmntTxt.setInputProtected(!isActive);
        scrnMsg.eolTmMatCmntTxt.setInputProtected(!isActive);
        scrnMsg.eolTechSprtCmntTxt.setInputProtected(!isActive);
        scrnMsg.eolOthCmntTxt.setInputProtected(!isActive);
        // START 2017/12/22 U.Kim [QC#22448, ADD]
        scrnMsg.eolDisptCmntTxt.setInputProtected(!isActive);
        // END 2017/12/22 U.Kim [QC#22448, ADD]
    }
    // add end 2016/05/19 CSA Defect#447

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * control Item Criticality Field
     * @param scrnMsg scrnMsg
     * @param isActive boolean
     */
    public static void controlCriticalityField(EZDCommonHandler scrnAppli, NSAL0490BMsg scrnMsg, boolean isActive) {

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NSAL0490_DBMsg dbMsg = scrnMsg.D.no(i);
            dbMsg.xxChkBox_D.setInputProtected(!isActive);
            dbMsg.mdseCd_D.setInputProtected(!isActive);
            dbMsg.backOrdImpctTpCd_D2.setInputProtected(!isActive);
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_OPEN_MDSE, i, true);
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_SET_MDSE, i, true);
        }
        if (scrnMsg.D.getValidCount() == 0) {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_CRITICALITY, false);
        } else {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_DLT_CRITICALITY, true);
        }
        if (scrnMsg.D.getValidCount() == scrnMsg.D.length()) {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_CRITICALITY, false);
        } else {
            scrnAppli.setButtonEnabled(NSAL0490Constant.BTN_INS_CRITICALITY, true);
        }
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
    
    /**
     * control Init Tab Button
     * @param scrnMsg NSAL0490BMsg
     */
    public static void controlInitTabBtn(NSAL0490BMsg scrnMsg) {

        scrnMsg.xxTabProt_IC.setInputProtected(true);
        scrnMsg.xxTabProt_SR.setInputProtected(true);
        scrnMsg.xxTabProt_SM.setInputProtected(true);
        //add start 2016/05/19 CSA Defect#447
        scrnMsg.xxTabProt_EL.setInputProtected(true);
        //add end 2016/05/19 CSA Defect#447
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        scrnMsg.xxTabProt_CR.setInputProtected(true);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

        if (ZYPCommonFunc.hasValue(scrnMsg.mdlId)) {
            scrnMsg.xxTabProt_SR.setInputProtected(false);
            scrnMsg.xxTabProt_SM.setInputProtected(false);
            //add start 2016/05/19 CSA Defect#447
            scrnMsg.xxTabProt_EL.setInputProtected(false);
            //add end 2016/05/19 CSA Defect#447
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
            scrnMsg.xxTabProt_CR.setInputProtected(false);
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        }
    }

    /**
     * control common Tab Button
     * @param scrnMsg NSAL0490BMsg
     * @param isItemConfActive boolean
     * @param isSvcRulesActive boolean
     * @param isSuppleMapActive boolean
     * @param isEndOfLifeActive boolean
     * @param isCriticality boolean  08/03/2017 CITS S.Endo Add Sol#072(QC#18386)
     */
    public static void controlCommonTabBtn(NSAL0490BMsg scrnMsg, boolean isItemConfActive, boolean isSvcRulesActive, boolean isSupplyMapActive, boolean isEndOfLifeActive, boolean isCriticality) {

        scrnMsg.xxTabProt_IC.setInputProtected(!isItemConfActive);
        scrnMsg.xxTabProt_SR.setInputProtected(!isSvcRulesActive);
        scrnMsg.xxTabProt_SM.setInputProtected(!isSupplyMapActive);
        // add start 2016/05/19 CSA Defect#447
        scrnMsg.xxTabProt_EL.setInputProtected(!isEndOfLifeActive);
        // add end 2016/05/19 CSA Defect#447
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        scrnMsg.xxTabProt_CR.setInputProtected(!isCriticality);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
    }

    /**
     * set Table's Back Color(Item Configurations Tab)
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void setTblBackColorItemConfig(NSAL0490BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NSAL0490Constant.SCREEN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NSAL0490Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NSAL0490Bean.A, scrnMsg.A);
    }

    /**
     * set Table's Back Color(Supply Mapping Tab)
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void setTblBackColorSupplyMap(NSAL0490BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NSAL0490Constant.SCREEN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NSAL0490Bean.B, scrnMsg.B);
        tblColor.setAlternateRowsBG(NSAL0490Bean.B, scrnMsg.B);
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * set Table's Back Color(Supply Mapping Tab)
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void setTblBackColorCriticality(NSAL0490BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NSAL0490Constant.SCREEN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NSAL0490Bean.D, scrnMsg.D);
        tblColor.setAlternateRowsBG(NSAL0490Bean.D, scrnMsg.D);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * has Register FuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NSAL0490Constant.BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Model Maintenance(" + NSAL0490Constant.BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (NSAL0490Constant.AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * set Parameter for Model Name(NMAL6050)
     * @param scrnMsg NSAL0490BMsg
     */
    public static void setParamMdlNm(NSAL0490BMsg scrnMsg) {

        scrnMsg.xxTblNm.setValue("MDL_NM_V");
        scrnMsg.xxTblCdColNm.setValue("T_MDL_ID");
        scrnMsg.xxTblNmColNm.setValue("T_MDL_NM");
        scrnMsg.xxTblSortColNm.setValue("T_MDL_ID");
        scrnMsg.xxScrNm.setValue("Model Name Popup");
        scrnMsg.xxHdrCdLbNm.setValue("Model ID");
        scrnMsg.xxHdrNmLbNm.setValue("Model Name");
        scrnMsg.xxDtlCdLbNm.setValue("Model ID");
        scrnMsg.xxDtlNmLbNm.setValue("Model Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.mdlNm)) {
            scrnMsg.xxCondNm.setValue(scrnMsg.mdlNm.getValue());
        } else {
            scrnMsg.xxCondNm.clear();
        }
        scrnMsg.xxCondCd.clear();
    }

    /**
     * set Parameter for Model Group(NMAL6050)
     * @param scrnMsg NSAL0490BMsg
     */
    public static void setParamMdlGrp(NSAL0490BMsg scrnMsg) {

        scrnMsg.xxTblNm.setValue("DS_MDL_GRP");
        scrnMsg.xxTblCdColNm.setValue("MDL_GRP_ID");
        scrnMsg.xxTblNmColNm.setValue("MDL_GRP_NM");
        scrnMsg.xxTblSortColNm.setValue("MDL_GRP_ID");
        scrnMsg.xxScrNm.setValue("Model Group Popup");
        scrnMsg.xxHdrCdLbNm.setValue("Model Group ID");
        scrnMsg.xxHdrNmLbNm.setValue("Model Group Name");
        scrnMsg.xxDtlCdLbNm.setValue("Model Group ID");
        scrnMsg.xxDtlNmLbNm.setValue("Model Group Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.mdlGrpNm)) {
            scrnMsg.xxCondNm.setValue(scrnMsg.mdlGrpNm.getValue());
        } else {
            scrnMsg.xxCondNm.clear();
        }
        scrnMsg.xxCondCd.clear();
    }

    /**
     * set Parameter for Mdse(NMAL6050)
     * @param scrnMsg NSAL0490BMsg
     */
    public static void setParamMdse(NSAL0490BMsg scrnMsg) {

//        scrnMsg.xxTblNm.setValue("MDSE");
//        scrnMsg.xxTblCdColNm.setValue("MDSE_CD");
//        scrnMsg.xxTblNmColNm.setValue("MDSE_NM");
//        scrnMsg.xxTblSortColNm.setValue("MDSE_CD");
//        scrnMsg.xxScrNm.setValue("MDSE Popup");
//        scrnMsg.xxHdrCdLbNm.setValue("MDSE Code");
//        scrnMsg.xxHdrNmLbNm.setValue("MDSE Name");
//        scrnMsg.xxDtlCdLbNm.setValue("MDSE Code");
//        scrnMsg.xxDtlNmLbNm.setValue("MDSE Name");
//        scrnMsg.xxCondCd.clear();
//        scrnMsg.xxCondNm.clear();
    }

    // START 2016/02/10 A.Kohinata [QC#1157, ADD]
    /**
     * set Parameter for Service Skills(NMAL6050)
     * @param scrnMsg NSAL0490BMsg
     */
    public static void setParamSvcSkill(NSAL0490BMsg scrnMsg) {

        setValue(scrnMsg.xxTblNm, "SVC_SKILL");
        setValue(scrnMsg.xxTblCdColNm, "SVC_SKILL_NUM");
        setValue(scrnMsg.xxTblNmColNm, "SVC_SKILL_DESC_TXT");
        setValue(scrnMsg.xxTblSortColNm, "SVC_SKILL_NUM");
        setValue(scrnMsg.xxScrNm, "Service Skills Popup");
        setValue(scrnMsg.xxHdrCdLbNm, "Skill Number");
        setValue(scrnMsg.xxHdrNmLbNm, "Skill Name");
        setValue(scrnMsg.xxDtlCdLbNm, "Skill Number");
        setValue(scrnMsg.xxDtlNmLbNm, "Skill Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.svcSkillNum)) {
            setValue(scrnMsg.xxCondCd, scrnMsg.svcSkillNum);
        } else {
            scrnMsg.xxCondCd.clear();
        }
        scrnMsg.xxCondNm.clear();
    }
    // END 2016/02/10 A.Kohinata [QC#1157, ADD]

    // mod start 2016/03/01 CSA Defect#3586
    // 2015/10/07 CSA Y.Tsuchimoto Add Start
//    /**
//     * get Parameter for NMAL6030 (for ItemConfig Tab)
//     * @param scrnMsg NSAL0490BMsg
//     * @param selectIndex Select Index
//     * @return Parameter for NMAL6030
//     */
//    public static Object[] getParamNMAL6030ForItemConfig(NSAL0490BMsg scrnMsg, int selectIndex) {
//        Object[] params = new Object[NSAL0490Constant.PRMS_02];
//
//        if (NSAL0490Constant.DTL_TP_PRNT.equals(scrnMsg.A.no(selectIndex).xxFlgNm_A.getValue())) {
//            params[NSAL0490Constant.PRMS_00] = scrnMsg.A.no(selectIndex).prntMdseCd_A;
//        } else {
//            params[NSAL0490Constant.PRMS_00] = scrnMsg.A.no(selectIndex).childMdseCd_A;
//        }
//        params[NSAL0490Constant.PRMS_01] = scrnMsg.A.no(selectIndex).mdseNm_A;
//        return params;
//    }
//
//    /**
//     * get Parameter for NMAL6030 (for SupplyMap Tab)
//     * @param scrnMsg NSAL0490BMsg
//     * @param selectIndex Select Index
//     * @return Parameter for NMAL6030
//     */
//    public static Object[] getParamNMAL6030ForSupplyMap(NSAL0490BMsg scrnMsg, int selectIndex) {
//        Object[] params = new Object[NSAL0490Constant.PRMS_02];
//        params[NSAL0490Constant.PRMS_00] = scrnMsg.B.no(selectIndex).mdseCd_B;
//        params[NSAL0490Constant.PRMS_01] = scrnMsg.B.no(selectIndex).mdseNm_B;
//        return params;
//    }
    // 2015/10/07 CSA Y.Tsuchimoto Add End
    /**
     * clear Parameter for NWAL6800 (for SupplyMap Tab)
     * @param scrnMsg NSAL0490BMsg
     */
    public static void clearParamNWAL6800(NSAL0490BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
    }
    /**
     * get Parameter for NWAL6800 (for ItemConfig Tab)
     * @param scrnMsg NSAL0490BMsg
     * @param selectIndex Select Index
     * @return Parameter for NMAL6030
     */
    public static Object[] getParamNWAL6800ForItemConfig(NSAL0490BMsg scrnMsg, int selectIndex) {

        //08/14/2017 Fujitsu T.Murai  Mod QC#20618 START
        //Object[] params = new Object[NSAL0490Constant.PRMS_07];
        Object[] params = new Object[NSAL0490Constant.PRMS_10];

        scrnMsg.xxPopPrm_00.setValue(NMAL6800Constant.XX_MODE_CD_08);
        //08/14/2017 Fujitsu T.Murai  Mod QC#20618 END

        if (NSAL0490Constant.DTL_TP_PRNT.equals(scrnMsg.A.no(selectIndex).xxFlgNm_A.getValue())) {
            params[NSAL0490Constant.PRMS_00] = scrnMsg.A.no(selectIndex).prntMdseCd_A;
        } else {
            params[NSAL0490Constant.PRMS_00] = scrnMsg.A.no(selectIndex).childMdseCd_A;
        }
        params[NSAL0490Constant.PRMS_01] = scrnMsg.xxPopPrm_01;
        params[NSAL0490Constant.PRMS_02] = scrnMsg.xxPopPrm_02;
        params[NSAL0490Constant.PRMS_03] = scrnMsg.xxPopPrm_03;
        params[NSAL0490Constant.PRMS_04] = scrnMsg.xxPopPrm_04;
        params[NSAL0490Constant.PRMS_05] = scrnMsg.xxPopPrm_05;
        params[NSAL0490Constant.PRMS_06] = scrnMsg.xxPopPrm_06;
        //08/14/2017 Fujitsu T.Murai  Add QC#20618 START
        params[NSAL0490Constant.PRMS_07] = scrnMsg.xxPopPrm_06;
        params[NSAL0490Constant.PRMS_08] = scrnMsg.xxPopPrm_06;
        params[NSAL0490Constant.PRMS_09] = scrnMsg.xxPopPrm_00;
        //08/14/2017 Fujitsu T.Murai  Add QC#20618 END
        return params;
    }

    /**
     * get Parameter for NWAL6800 (for SupplyMap Tab)
     * @param scrnMsg NSAL0490BMsg
     * @param selectIndex Select Index
     * @return Parameter for NMAL6030
     */
    public static Object[] getParamNWAL6800ForSupplyMap(NSAL0490BMsg scrnMsg, int selectIndex) {

        //08/14/2017 Fujitsu T.Murai  Mod QC#20618 START
        //Object[] params = new Object[NSAL0490Constant.PRMS_07];
        Object[] params = new Object[NSAL0490Constant.PRMS_10];

        scrnMsg.xxPopPrm_00.setValue(NMAL6800Constant.XX_MODE_CD_08);
        //08/14/2017 Fujitsu T.Murai  Mod QC#20618 END

        params[NSAL0490Constant.PRMS_00] = scrnMsg.B.no(selectIndex).mdseCd_B;
        params[NSAL0490Constant.PRMS_01] = scrnMsg.xxPopPrm_01;
        params[NSAL0490Constant.PRMS_02] = scrnMsg.xxPopPrm_02;
        params[NSAL0490Constant.PRMS_03] = scrnMsg.xxPopPrm_03;
        params[NSAL0490Constant.PRMS_04] = scrnMsg.xxPopPrm_04;
        params[NSAL0490Constant.PRMS_05] = scrnMsg.xxPopPrm_05;
        params[NSAL0490Constant.PRMS_06] = scrnMsg.xxPopPrm_06;
        //08/14/2017 Fujitsu T.Murai  Add QC#20618 START
        params[NSAL0490Constant.PRMS_07] = scrnMsg.xxPopPrm_06;
        params[NSAL0490Constant.PRMS_08] = scrnMsg.xxPopPrm_06;
        params[NSAL0490Constant.PRMS_09] = scrnMsg.xxPopPrm_00;
        //08/14/2017 Fujitsu T.Murai  Add QC#20618 END
        return params;
    }
    // mod end 2016/03/01 CSA Defect#3586

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * get Parameter for NWAL6800 (for SupplyMap Tab)
     * @param scrnMsg NSAL0490BMsg
     * @param selectIndex Select Index
     * @return Parameter for NMAL6030
     */
    public static Object[] getParamNWAL6800ForCriticality(NSAL0490BMsg scrnMsg, int selectIndex) {

        //08/14/2017 Fujitsu T.Murai  Mod QC#20618 START
        //Object[] params = new Object[NSAL0490Constant.PRMS_07];
        Object[] params = new Object[NSAL0490Constant.PRMS_10];

        scrnMsg.xxPopPrm_00.setValue(NMAL6800Constant.XX_MODE_CD_08);
        //08/14/2017 Fujitsu T.Murai  Mod QC#20618 END

        params[NSAL0490Constant.PRMS_00] = scrnMsg.D.no(selectIndex).mdseCd_D;
        params[NSAL0490Constant.PRMS_01] = scrnMsg.xxPopPrm_01;
        params[NSAL0490Constant.PRMS_02] = scrnMsg.xxPopPrm_02;
        params[NSAL0490Constant.PRMS_03] = scrnMsg.xxPopPrm_03;
        params[NSAL0490Constant.PRMS_04] = scrnMsg.xxPopPrm_04;
        params[NSAL0490Constant.PRMS_05] = scrnMsg.xxPopPrm_05;
        params[NSAL0490Constant.PRMS_06] = scrnMsg.xxPopPrm_06;
        //08/14/2017 Fujitsu T.Murai  Add QC#20618 START
        params[NSAL0490Constant.PRMS_07] = scrnMsg.xxPopPrm_06;
        params[NSAL0490Constant.PRMS_08] = scrnMsg.xxPopPrm_06;
        params[NSAL0490Constant.PRMS_09] = scrnMsg.xxPopPrm_00;
        //08/14/2017 Fujitsu T.Murai  Add QC#20618 END
        return params;
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * get Parameter for NMAL6050
     * @param scrnMsg NSAL0490BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] getParamNMAL6050(NSAL0490BMsg scrnMsg) {
        Object[] params = new Object[NSAL0490Constant.PRMS_11];
        params[NSAL0490Constant.PRMS_00] = scrnMsg.xxTblNm;
        params[NSAL0490Constant.PRMS_01] = scrnMsg.xxTblCdColNm;
        params[NSAL0490Constant.PRMS_02] = scrnMsg.xxTblNmColNm;
        params[NSAL0490Constant.PRMS_03] = scrnMsg.xxTblSortColNm;
        params[NSAL0490Constant.PRMS_04] = scrnMsg.xxScrNm;
        params[NSAL0490Constant.PRMS_05] = scrnMsg.xxHdrCdLbNm;
        params[NSAL0490Constant.PRMS_06] = scrnMsg.xxHdrNmLbNm;
        params[NSAL0490Constant.PRMS_07] = scrnMsg.xxDtlCdLbNm;
        params[NSAL0490Constant.PRMS_08] = scrnMsg.xxDtlNmLbNm;
        params[NSAL0490Constant.PRMS_09] = scrnMsg.xxCondCd;
        params[NSAL0490Constant.PRMS_10] = scrnMsg.xxCondNm;
        return params;
    }

    /**
     * get Add Child Index
     * @param scrnMsg NSAL0490BMsg
     * @return Add Child Index
     */
    public static final int getAddChildIndex(NSAL0490BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0490_ABMsg abMsg = scrnMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(abMsg.prntMdseCd_A) && !ZYPCommonFunc.hasValue(abMsg.childMdseCd_A)) {
                return i;
            }
        }

        return 0;
    }

    /**
     * clear Start Date
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void clearStartDateOfEmptyLine(NSAL0490BMsg scrnMsg) {

        // clear Start Date of Empty Line
        int lineCnt = scrnMsg.A.getValidCount();
        if (lineCnt == 1) {
            NSAL0490_ABMsg abMsg = scrnMsg.A.no(0);
            if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(abMsg.prntMdseCd_A) && ZYPCommonFunc.hasValue(abMsg.effFromDt_A)) {
                    abMsg.effFromDt_A.clear();
                    return;
                }
            }
        }
    }

    /**
     * check Header
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkHeader(NSAL0490BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        scrnMsg.addCheckItem(scrnMsg.mdlDescTxt);
        scrnMsg.addCheckItem(scrnMsg.mdlGrpNm);
        scrnMsg.putErrorScreen();
    }

    /**
     * check Item Configurations
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkItemConfig(NSAL0490BMsg scrnMsg) {

        checkItemConfigDetailSubmit(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    /**
     * check Item Configurations Detail
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkItemConfigDetail(NSAL0490BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[] {NSAL0490Bean.prntMdseCd_A, NSAL0490Bean.childMdseCd_A, NSAL0490Bean.effFromDt_A, NSAL0490Bean.effThruDt_A }, 1);
        scrnMsg.putErrorScreen();
    }

    /**
     * check Item Configurations Detail (Submit)
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkItemConfigDetailSubmit(NSAL0490BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0490_ABMsg abMsg = scrnMsg.A.no(i);

            if (i != 0) {
                if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
                    scrnMsg.addCheckItem(abMsg.prntMdseCd_A);
                } else {
                    scrnMsg.addCheckItem(abMsg.childMdseCd_A);
                }
                scrnMsg.addCheckItem(abMsg.effFromDt_A);
                scrnMsg.addCheckItem(abMsg.effThruDt_A);

                continue;
            }

            if (ZYPCommonFunc.hasValue(abMsg.effFromDt_A)) {
                if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
                    scrnMsg.addCheckItem(abMsg.prntMdseCd_A);
                } else {
                    scrnMsg.addCheckItem(abMsg.childMdseCd_A);
                }
            }

            String targetMdseCd = null;
            if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
                targetMdseCd = abMsg.prntMdseCd_A.getValue();
            } else {
                scrnMsg.addCheckItem(abMsg.childMdseCd_A);
                targetMdseCd = abMsg.childMdseCd_A.getValue();
            }

            if (ZYPCommonFunc.hasValue(targetMdseCd)) {
                scrnMsg.addCheckItem(abMsg.effFromDt_A);
                scrnMsg.addCheckItem(abMsg.effThruDt_A);
            }
        }
    }

    /**
     * check Service Rules
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkSvcRules(NSAL0490BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.rcllIntvlDaysAot);
        scrnMsg.addCheckItem(scrnMsg.rcllCopyVolCnt);
        scrnMsg.addCheckItem(scrnMsg.rcllGlblIntvlDaysAot);
        scrnMsg.addCheckItem(scrnMsg.rcllGlblCopyVolCnt);
        // mod start 2016/05/30 CSA Defect#6675
        scrnMsg.addCheckItem(scrnMsg.xxRtoTaskTmNum);
        checkItemFormat(scrnMsg.xxRtoTaskTmNum);
        // mod end 2016/05/30 CSA Defect#6675
        scrnMsg.addCheckItem(scrnMsg.xsVisitCnt);
        scrnMsg.addCheckItem(scrnMsg.phoneFixIntvlDaysAot);
        scrnMsg.addCheckItem(scrnMsg.copyVolDaysAot);
        scrnMsg.addCheckItem(scrnMsg.maxCopyPerDayTotCnt);
        scrnMsg.addCheckItem(scrnMsg.maxCopyPerDayBlackCnt);
        scrnMsg.addCheckItem(scrnMsg.maxCopyTestCnt);
        scrnMsg.addCheckItem(scrnMsg.mdlSpeedBlackRate);
        scrnMsg.addCheckItem(scrnMsg.mdlSpeedColorRate);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        scrnMsg.addCheckItem(scrnMsg.machInFldInacMthAot);
        scrnMsg.addCheckItem(scrnMsg.mdlDurnTmNum);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        scrnMsg.putErrorScreen();
    }

    // add start 2016/05/30 CSA Defect#6675
    private static void checkItemFormat(EZDBBigDecimalItem item) {
        String regex = "\\d{0,3}(\\.\\d)";
        Pattern p = Pattern.compile(regex);
        if (hasValue(item)) {
            Matcher m = p.matcher(item.getValue().toString());
            if (m.matches()) {
            } else {
                item.setErrorInfo(1, NSAL0490Constant.ZZM9015E, new String[] {"Response Time Target (Hours)"});
            }
        }
        return;
    }
    // add end 2016/05/30 CSA Defect#6675

    /**
     * check Supply Map
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkSupplyMap(NSAL0490BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.B);
        // 2015/10/07 CSA Y.Tsuchimoto Mod Start
        //scrnMsg.B.setCheckParam(new String[] {NSAL0490Bean.mdseCd_B, NSAL0490Bean.splyTolPct_B, NSAL0490Bean.custStkQty_B, NSAL0490Bean.effFromDt_B, NSAL0490Bean.effThruDt_B }, 1);
        scrnMsg.B.setCheckParam(new String[] {NSAL0490Bean.mdseCd_B, NSAL0490Bean.splyTolPct_B, NSAL0490Bean.custStkQty_B, NSAL0490Bean.effFromDt_B, NSAL0490Bean.effThruDt_B, NSAL0490Bean.splyInitQty_B, NSAL0490Bean.splyContrQty_B }, 1);
        // 2015/10/07 CSA Y.Tsuchimoto Mod End
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * check Supply Map
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkCriticality(NSAL0490BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.D);
        scrnMsg.D.setCheckParam(new String[] {NSAL0490Bean.mdseCd_D},1);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
    
    // add start 2016/05/19 CSA Defect#447
    /**
     * check End Of Life
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void checkEndOfLife(NSAL0490BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.dsMdlEolStsCd_D1);
        scrnMsg.addCheckItem(scrnMsg.dsMdlEolStsCd_D2);
        scrnMsg.addCheckItem(scrnMsg.dsMdlEolStsCd_D3);
        scrnMsg.addCheckItem(scrnMsg.dsMdlEolDt_D1);
        scrnMsg.addCheckItem(scrnMsg.dsMdlEolDt_D2);
        scrnMsg.addCheckItem(scrnMsg.dsMdlEolDt_D3);
        scrnMsg.addCheckItem(scrnMsg.eolSvcContrCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.eolTmMatCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.eolTechSprtCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.eolOthCmntTxt);
        // START 2017/12/22 U.Kim [QC#22448, ADD]
        scrnMsg.addCheckItem(scrnMsg.eolDisptCmntTxt);
        // END 2017/12/22 U.Kim [QC#22448, ADD]
        if (isEqualEzdStringItem(scrnMsg.dsMdlEolStsCd_D1, scrnMsg.dsMdlEolStsCd_D2)) {
            scrnMsg.dsMdlEolStsCd_D1.setErrorInfo(1, NSAL0490Constant.NSAM0476E);
            scrnMsg.dsMdlEolStsCd_D2.setErrorInfo(1, NSAL0490Constant.NSAM0476E);
        }
        if (isEqualEzdStringItem(scrnMsg.dsMdlEolStsCd_D2, scrnMsg.dsMdlEolStsCd_D3)) {
            scrnMsg.dsMdlEolStsCd_D2.setErrorInfo(1, NSAL0490Constant.NSAM0476E);
            scrnMsg.dsMdlEolStsCd_D3.setErrorInfo(1, NSAL0490Constant.NSAM0476E);
        }
        if (isEqualEzdStringItem(scrnMsg.dsMdlEolStsCd_D3, scrnMsg.dsMdlEolStsCd_D1)) {
            scrnMsg.dsMdlEolStsCd_D3.setErrorInfo(1, NSAL0490Constant.NSAM0476E);
            scrnMsg.dsMdlEolStsCd_D1.setErrorInfo(1, NSAL0490Constant.NSAM0476E);
        }
        if (hasPartMandatoryErr(scrnMsg.dsMdlEolStsCd_D1, scrnMsg.dsMdlEolDt_D1)) {
            scrnMsg.dsMdlEolStsCd_D1.setErrorInfo(1, NSAL0490Constant.NSAM0081E, new String []{"Status", "Date"});
            scrnMsg.dsMdlEolDt_D1.setErrorInfo(1, NSAL0490Constant.NSAM0081E, new String []{"Status", "Date"});
        }
        if (hasPartMandatoryErr(scrnMsg.dsMdlEolStsCd_D2, scrnMsg.dsMdlEolDt_D2)) {
            scrnMsg.dsMdlEolStsCd_D2.setErrorInfo(1, NSAL0490Constant.NSAM0081E, new String []{"Status", "Date"});
            scrnMsg.dsMdlEolDt_D2.setErrorInfo(1, NSAL0490Constant.NSAM0081E, new String []{"Status", "Date"});
        }
        if (hasPartMandatoryErr(scrnMsg.dsMdlEolStsCd_D3, scrnMsg.dsMdlEolDt_D3)) {
            scrnMsg.dsMdlEolStsCd_D3.setErrorInfo(1, NSAL0490Constant.NSAM0081E, new String []{"Status", "Date"});
            scrnMsg.dsMdlEolDt_D3.setErrorInfo(1, NSAL0490Constant.NSAM0081E, new String []{"Status", "Date"});
        }
        // Add Start 2018/02/21 K.Fujimoto QC#30366
        checkDsMdlEolDate(scrnMsg);
        // Add End   2018/02/21 K.Fujimoto QC#30366
        scrnMsg.putErrorScreen();
    }

    // Add Start 2018/02/21 K.Fujimoto QC#30366
    private static boolean checkDsMdlEolDate(NSAL0490BMsg scrnMsg) {
        boolean result = true;
        if (ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolStsCd_D1) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolStsCd_D2) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolDt_D1) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolDt_D2)) {
            String[] msgStr = checkDsMdlEolDate(scrnMsg.dsMdlEolDt_D1.getValue(), scrnMsg.dsMdlEolStsCd_D1.getValue(), scrnMsg.dsMdlEolDt_D2.getValue(), scrnMsg.dsMdlEolStsCd_D2.getValue());
            if (msgStr != null) {
                scrnMsg.dsMdlEolDt_D1.setErrorInfo(1, NSAL0490Constant.NSAM0746E, msgStr);
                scrnMsg.dsMdlEolDt_D2.setErrorInfo(1, NSAL0490Constant.NSAM0746E, msgStr);
                result = false;
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolStsCd_D2) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolStsCd_D3) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolDt_D2) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolDt_D3)) {
            String[] msgStr = checkDsMdlEolDate(scrnMsg.dsMdlEolDt_D2.getValue(), scrnMsg.dsMdlEolStsCd_D2.getValue(), scrnMsg.dsMdlEolDt_D3.getValue(), scrnMsg.dsMdlEolStsCd_D3.getValue());
            if (msgStr != null) {
                scrnMsg.dsMdlEolDt_D2.setErrorInfo(1, NSAL0490Constant.NSAM0746E, msgStr);
                scrnMsg.dsMdlEolDt_D3.setErrorInfo(1, NSAL0490Constant.NSAM0746E, msgStr);
                result = false;
            }

        }

        if (ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolStsCd_D1) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolStsCd_D3) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolDt_D1) && ZYPCommonFunc.hasValue(scrnMsg.dsMdlEolDt_D3)) {
            String[] msgStr = checkDsMdlEolDate(scrnMsg.dsMdlEolDt_D1.getValue(), scrnMsg.dsMdlEolStsCd_D1.getValue(), scrnMsg.dsMdlEolDt_D3.getValue(), scrnMsg.dsMdlEolStsCd_D3.getValue());
            if (msgStr != null) {
                scrnMsg.dsMdlEolDt_D1.setErrorInfo(1, NSAL0490Constant.NSAM0746E, msgStr);
                scrnMsg.dsMdlEolDt_D3.setErrorInfo(1, NSAL0490Constant.NSAM0746E, msgStr);
                result = false;
            }
        }
        return result;
    }

    private static String[] checkDsMdlEolDate(String dsMdlEolDt, String dsMdlEolStsCd, String dsMdlEolDt2, String dsMdlEolStsCd2) {
        String[] result = null;
        // Compare Date : No Contract < No Service < Inactive
        if (dsMdlEolStsCd.equals(DS_MDL_EOL_STS.EOL_NO_CONTRACT)) {
            if (dsMdlEolStsCd2.equals(DS_MDL_EOL_STS.EOL_NO_SERVICE)) {
                if (ZYPDateUtil.compare(dsMdlEolDt, dsMdlEolDt2) > 0) {
                    result = new String[] {NSAL0490Constant.NO_CONTRACT_DATE, NSAL0490Constant.NO_SERVICE_DATE};
                }
            } else if (dsMdlEolStsCd2.equals(DS_MDL_EOL_STS.EOL_INACTIVE)) {
                if (ZYPDateUtil.compare(dsMdlEolDt, dsMdlEolDt2) > 0) {
                    result = new String[] {NSAL0490Constant.NO_CONTRACT_DATE, NSAL0490Constant.INACTIVE};
                }
            }
        } else if (dsMdlEolStsCd.equals(DS_MDL_EOL_STS.EOL_NO_SERVICE)) {
            if (dsMdlEolStsCd2.equals(DS_MDL_EOL_STS.EOL_NO_CONTRACT)) {
                if (ZYPDateUtil.compare(dsMdlEolDt, dsMdlEolDt2) < 0) {
                    result = new String[] {NSAL0490Constant.NO_CONTRACT_DATE, NSAL0490Constant.NO_SERVICE_DATE};
                }
            } else if (dsMdlEolStsCd2.equals(DS_MDL_EOL_STS.EOL_INACTIVE)) {
                if (ZYPDateUtil.compare(dsMdlEolDt, dsMdlEolDt2) > 0) {
                    result = new String[] {NSAL0490Constant.NO_SERVICE_DATE, NSAL0490Constant.INACTIVE};
                }
            }
        } else if (dsMdlEolStsCd.equals(DS_MDL_EOL_STS.EOL_INACTIVE)) {
            if (dsMdlEolStsCd2.equals(DS_MDL_EOL_STS.EOL_NO_CONTRACT)) {
                if (ZYPDateUtil.compare(dsMdlEolDt, dsMdlEolDt2) < 0) {
                    result = new String[] {NSAL0490Constant.NO_CONTRACT_DATE, NSAL0490Constant.INACTIVE};
                }
            } else if (dsMdlEolStsCd2.equals(DS_MDL_EOL_STS.EOL_NO_SERVICE)) {
                if (ZYPDateUtil.compare(dsMdlEolDt, dsMdlEolDt2) < 0) {
                    result = new String[] {NSAL0490Constant.NO_SERVICE_DATE, NSAL0490Constant.INACTIVE};
                }
            }
        }
        return result;
    }
    // Add End   2018/02/21 K.Fujimoto QC#30366

    private static boolean isEqualEzdStringItem(EZDBStringItem str1, EZDBStringItem str2) {
        if (!ZYPCommonFunc.hasValue(str1)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(str2)) {
            return false;
        }
        if (str1.getValue().equals(str2.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean hasPartMandatoryErr(EZDBStringItem str1, EZDBDateItem dt1) {
        if (ZYPCommonFunc.hasValue(str1) && !ZYPCommonFunc.hasValue(dt1)) {
            return true;
        } else if (!ZYPCommonFunc.hasValue(str1) && ZYPCommonFunc.hasValue(dt1)) {
            return true;
        }
        return false;
    }
    // add end 2016/05/19 CSA Defect#447

    /**
     * set Page(Item Configurations Tab)
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void setPageForItemConfig(NSAL0490BMsg scrnMsg) {

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum_A.getValue());
        scrnMsg.xxPageShowToNum.setValue(scrnMsg.xxPageShowToNum_A.getValue());
        scrnMsg.xxPageShowOfNum.setValue(scrnMsg.xxPageShowOfNum_A.getValue());
    }

    /**
     * set Page(Supply Mapping Tab)
     * @param scrnMsg NSAL0490BMsg
     */
    public static final void setPageForSupplyMap(NSAL0490BMsg scrnMsg) {

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum_B.getValue());
        scrnMsg.xxPageShowToNum.setValue(scrnMsg.xxPageShowToNum_B.getValue());
        scrnMsg.xxPageShowOfNum.setValue(scrnMsg.xxPageShowOfNum_B.getValue());
    }
}
