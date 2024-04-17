/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270.common;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.BIZ_ID;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_APL;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_APR;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_RST;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.COMMA;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.HIGH_VAL_DT;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_0;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_1;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_10;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_11;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_2;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_20;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_3;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_30;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_4;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_5;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_6;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_7;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_8;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.NMAM0043E;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.NMAM8113E;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.NMAM8258E;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_ACCT;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_CMN;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_GEN;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_ITEM;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_MDL;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_NONE;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_NONE_DT;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_NONE_PCT;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.POPUP_TYPE_NONE_VAL;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.SCRN_ID_00;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.UPDATE_AUTHORITY;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.ZZM9000E;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.ZZM9004E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import business.servlet.NMAL7270.NMAL7270BMsg;
import business.servlet.NMAL7270.NMAL7270_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7270CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2015/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2015/05/19   Fujitsu         Y.Kanefusa      Update          QC#6530
 * 2015/05/19   Fujitsu         Y.Kanefusa      Update          QC#6939
 * 2016/06/28   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2017/02/28   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/08/03   Fujitsu         H.Sugawara      Update          QC#18240
 * 2017/08/09   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2018/06/07   Fujitsu         T.Noguchi       Update          QC#26099
 * 2018/09/12   Fujitsu         M.Ohno          Update          QC#9700
 * 2018/11/22   Fujitsu         R.Nakamura      Update          QC#26339
 *</pre>
 */
public class NMAL7270CommonLogic {

    // Add Start 2017/02/28 QC#16011
    /**
     * Update Authority Check
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean updateAuthority(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(UPDATE_AUTHORITY);
    }
    // Add End 2017/02/28 QC#16011

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * initScrnProtect.
     * @param scrnMsg NMAL7270BMsg
     */
    public static void initScrnProtect(NMAL7270BMsg scrnMsg) {

        scrnMsg.xxFullNm_H1.setInputProtected(true);
        scrnMsg.cratDt_H1.setInputProtected(true);
        scrnMsg.xxFullNm_H2.setInputProtected(true);
        scrnMsg.lastUpdDt_H1.setInputProtected(true);
        scrnMsg.prcRulePrcdGrpNm_H1.setInputProtected(true); // QC#9694 2016/06/28 Add

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxRecNmTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A1.setInputProtected(true);
        }
    }

    /**
     * Set Button properties.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7270BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setBtnProp(S21CommonHandler handler, NMAL7270BMsg scrnMsg, S21UserProfileService userProfileService) {

        // Add Start 2017/02/28 QC#16011
        boolean uploadAuthFlg = updateAuthority(userProfileService);
        // Add Start 2017/02/28 QC#16011

        scrnMsg.xxLinkAncr_DP.setValue(ZYPConstant.FLG_ON_Y); // QC#9694 2016/06/28 Add

        // Add Start 2017/03/01 QC#16011
        if (uploadAuthFlg && scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        // Add End 2017/03/01 QC#16011

        if (scrnMsg.A.getValidCount() > 0) {
            // Del Start 2017/03/01 QC#16011
//            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            // Del End 2017/03/01 QC#16011

            handler.setButtonEnabled("Download_TrxCond", true);
        } else {
            handler.setButtonEnabled("Download_TrxCond", false);
        }

        if (scrnMsg.B.getValidCount() > 0) {
            handler.setButtonEnabled("Download_PrcAdjDtl", true);
        } else {
            handler.setButtonEnabled("Download_PrcAdjDtl", false);
        }

        // Add Start 2017/03/01 QC#16011
        handler.setButtonEnabled("Add_TrxCond", uploadAuthFlg);
        handler.setButtonEnabled("Del_TrxCond", uploadAuthFlg);
        handler.setButtonEnabled("Add_PrcAdjDtl", uploadAuthFlg);
        handler.setButtonEnabled("Del_PrcAdjDtl", uploadAuthFlg);
        // Add End 2017/03/01 QC#16011
    }

    /**
     * Set Screen Control.
     * @param scrnMsg NMAL7270BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setScrnCtrl(NMAL7270BMsg scrnMsg, S21UserProfileService userProfileService) {

        // Add Start 2017/02/28 QC#16011
        boolean updateAuthFlg = updateAuthority(userProfileService);
        boolean protectFldFlg = !updateAuthFlg;

        scrnMsg.prcRuleNm_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleDescTxt_H1.setInputProtected(protectFldFlg);
        scrnMsg.lineBizTpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcRuleCatgCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.effThruDt_H1.setInputProtected(protectFldFlg);
        scrnMsg.actvFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.applyAutoFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.ovrdFlg_H1.setInputProtected(protectFldFlg);
        scrnMsg.defRulePrcdNum_H1.setInputProtected(protectFldFlg);
        scrnMsg.xxLinkAncr_DP.setInputProtected(protectFldFlg);
        // Add End 2017/02/28 QC#16011
        // 2018/09/12 QC#9700 add start
        scrnMsg.prcGrpTrxTpCd_H1.setInputProtected(protectFldFlg);
        // 2018/09/12 QC#9700 add end

//        if (ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_BK)
//                && ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)
//                && ZYPDateUtil.compare(scrnMsg.effFromDt_H1.getValue(), scrnMsg.slsDt.getValue()) < 0) {
//            scrnMsg.effFromDt_H1.setInputProtected(true);
//        } else {
//            scrnMsg.effFromDt_H1.setInputProtected(false);
//        }
        // Mod Start 2017/02/28 QC#16011
//        setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.effFromDt_H1, scrnMsg.slsDt.getValue());
        setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.effFromDt_H1, scrnMsg.slsDt.getValue(), updateAuthFlg);
        // Mod End 2017/02/28 QC#16011
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleTrxCondPk_A1)
//                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)
//                    && (ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), scrnMsg.slsDt.getValue())) < 0) {
//                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
//            } else {
//                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
//            }
            // Mod Start 2017/02/28 QC#16011
//            setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.A.no(i).effFromDt_A1, scrnMsg.slsDt.getValue());
            setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.A.no(i).effFromDt_A1, scrnMsg.slsDt.getValue(), updateAuthFlg);
            // Mod End 2017/02/28 QC#16011

            // Add Start 2017/02/28 QC#16011
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleTrxCatgCd_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleAttrbCd_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleCondFromTxt_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).xxFromDt_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).xxThruDt_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(protectFldFlg);
            // Add End 2017/02/28 QC#16011
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcRuleDtlPk_B1)
//                    && ZYPCommonFunc.hasValue(scrnMsg.B.no(i).effFromDt_B1)
//                    && (ZYPDateUtil.compare(scrnMsg.B.no(i).effFromDt_B1.getValue(), scrnMsg.slsDt.getValue())) < 0) {
//                scrnMsg.B.no(i).effFromDt_B1.setInputProtected(true);
//            } else {
//                scrnMsg.B.no(i).effFromDt_B1.setInputProtected(false);
//            }
            // Mod Start 2017/02/28 QC#16011
//            setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.B.no(i).effFromDt_B1, scrnMsg.slsDt.getValue());
            setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.B.no(i).effFromDt_B1, scrnMsg.slsDt.getValue(), updateAuthFlg);
            // Mod End 2017/02/28 QC#16011
            // QC#6530 2016/05/19 Add Start
            scrnMsg.B.no(i).prcFmlaNm_B1.setInputProtected(true);
            // QC#6530 2016/05/19 Add End
            // Add Start 2017/02/28 QC#16011
            scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleCondGrpCd_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleDtlChrgNm_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleModTpCd_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleAdjTpCd_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleAttrbCd_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleAdjLvlCd_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcFmlaPk_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleDtlRate_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).prcRuleDtlTxt_B1.setInputProtected(protectFldFlg);
            scrnMsg.B.no(i).effThruDt_B1.setInputProtected(protectFldFlg);
            // Add End 2017/02/28 QC#16011
        }
    }

    /**
     * setEffFromDtProtect.
     * @param pkItem EZDBBigDecimalItem
     * @param effFromDtItem EZDBDateItem
     * @param slsDt String
     * @param updateAuthFlg boolean
     */
    public static void setEffFromDtProtect(EZDBBigDecimalItem pkItem, EZDBDateItem effFromDtItem, String slsDt, boolean updateAuthFlg) {

        // Mod Start 2018/11/22 QC#26339
        // Mod Start 2017/02/28 QC#16011
        if (!updateAuthFlg) {
            effFromDtItem.setInputProtected(true);
//        } else if (ZYPCommonFunc.hasValue(pkItem)
//                && ZYPCommonFunc.hasValue(effFromDtItem)
//                && (ZYPDateUtil.compare(effFromDtItem.getValue(), slsDt)) < 0) {
//        // Mod End 2017/02/28 QC#16011
//            effFromDtItem.setInputProtected(true);
        } else {
            effFromDtItem.setInputProtected(false);
        }
        // Mod End 2018/11/22 QC#26339
    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7270BMsg
     * @param scrnAMsgAry NMAL7270_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7270BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7270BMsg
     * @param scrnAMsgAry NMAL7270_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7270BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7270BMsg
     * @param scrnAMsgAry NMAL7270_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7270BMsg scrnMsg, NMAL7270_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * allAttrbCtrl.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7270BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void allAttrbCtrl(S21CommonHandler handler, NMAL7270BMsg scrnMsg, S21UserProfileService userProfileService) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        // Add Start 2017/03/01 QC#16011
        boolean updateAuthFlg = updateAuthority(userProfileService);
        // Add End 2017/03/01 QC#16011

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Mod Start 2017/03/01 QC#16011
//            condAttrbCtrl(handler, i, scrnMsg);
            condAttrbCtrl(handler, i, scrnMsg, updateAuthFlg);
            // Mod End 2017/03/01 QC#16011
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // Mod Start 2017/03/01 QC#16011
//            dtlAttrbCtrl(handler, i, scrnMsg);
            dtlAttrbCtrl(handler, i, scrnMsg, updateAuthFlg);
            // Mod End 2017/03/01 QC#16011
        }
    }

    /**
     * condAttrbCtrl.
     * @param handler S21CommonHandler
     * @param idx int
     * @param scrnMsg NMAL7270BMsg
     * @param updateAuthFlg boolean
     */
    public static void condAttrbCtrl(S21CommonHandler handler, int idx, NMAL7270BMsg scrnMsg, boolean updateAuthFlg) {
        // Input Field Change
        String popupType = getPopupType(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue());

        String cmnDisplay = "none";
        String genDisplay = "none";
        String itemDisplay = "none";
        String acctDisplay = "none";
        String mdlDisplay = "none";
        String fromValDisplay = "none";
        String fromDtCalDisplay = "none";
        String thruValDisplay = "none";
        String thruDtCalDisplay = "none";
        boolean btnEnable = true;
        boolean fldProtect = false;
        if (POPUP_TYPE_CMN.equals(popupType)) {
            cmnDisplay = "";
            fromValDisplay = "";
            thruValDisplay = "";
            fldProtect = true;
        } else if (POPUP_TYPE_GEN.equals(popupType)) {
            genDisplay = "";
            fromValDisplay = "";
            thruValDisplay = "";
            fldProtect = true;
        } else if (POPUP_TYPE_ITEM.equals(popupType)) {
            itemDisplay = "";
            fromValDisplay = "";
            thruValDisplay = "";
            fldProtect = true;
        } else if (POPUP_TYPE_ACCT.equals(popupType)) {
            acctDisplay = "";
            fromValDisplay = "";
            thruValDisplay = "";
            fldProtect = true;
        } else if (POPUP_TYPE_MDL.equals(popupType)) {
            mdlDisplay = "";
            fromValDisplay = "";
            thruValDisplay = "";
            fldProtect = true;
        } else if (POPUP_TYPE_NONE.equals(popupType)) {
            cmnDisplay = "";
            fromValDisplay = "";
            thruValDisplay = "";
            btnEnable = false;
        } else if (POPUP_TYPE_NONE_DT.equals(popupType)) {
            cmnDisplay = "";
            fromDtCalDisplay = "";
            thruDtCalDisplay = "";
            btnEnable = false;
        } else {
            cmnDisplay = "";
            fromValDisplay = "";
            thruValDisplay = "";
            fldProtect = true;
            btnEnable = false;
        }

        EZDGUIAttribute valBtnCmn = new EZDGUIAttribute(SCRN_ID_00, "OpenWin_PrcRuleCondVal_Cmn#" + idx);
        valBtnCmn.setStyleAttribute("display", cmnDisplay);
        scrnMsg.addGUIAttribute(valBtnCmn);

        EZDGUIAttribute valBtnGen = new EZDGUIAttribute(SCRN_ID_00, "OpenWin_PrcRuleCondVal_Gen#" + idx);
        valBtnGen.setStyleAttribute("display", genDisplay);
        scrnMsg.addGUIAttribute(valBtnGen);

        EZDGUIAttribute valBtnItem = new EZDGUIAttribute(SCRN_ID_00, "OpenWin_PrcRuleCondVal_Item#" + idx);
        valBtnItem.setStyleAttribute("display", itemDisplay);
        scrnMsg.addGUIAttribute(valBtnItem);

        EZDGUIAttribute valBtnAcct = new EZDGUIAttribute(SCRN_ID_00, "OpenWin_PrcRuleCondVal_Acct#" + idx);
        valBtnAcct.setStyleAttribute("display", acctDisplay);
        scrnMsg.addGUIAttribute(valBtnAcct);

        EZDGUIAttribute valBtnMdl = new EZDGUIAttribute(SCRN_ID_00, "OpenWin_PrcRuleCondVal_Mdl#" + idx);
        valBtnMdl.setStyleAttribute("display", mdlDisplay);
        scrnMsg.addGUIAttribute(valBtnMdl);

        EZDGUIAttribute fromVal = new EZDGUIAttribute(SCRN_ID_00, "prcRuleCondFromTxt#" + idx);
        fromVal.setStyleAttribute("display", fromValDisplay);
        scrnMsg.addGUIAttribute(fromVal);

        EZDGUIAttribute fromDt = new EZDGUIAttribute(SCRN_ID_00, "condFromDate#" + idx);
        fromDt.setStyleAttribute("display", fromDtCalDisplay);
        scrnMsg.addGUIAttribute(fromDt);

        EZDGUIAttribute fromCal = new EZDGUIAttribute(SCRN_ID_00, "condFromCalendar#" + idx);
        fromCal.setStyleAttribute("display", fromDtCalDisplay);
        scrnMsg.addGUIAttribute(fromCal);

        EZDGUIAttribute thruVal = new EZDGUIAttribute(SCRN_ID_00, "prcRuleCondThruTxt#" + idx);
        thruVal.setStyleAttribute("display", thruValDisplay);
        scrnMsg.addGUIAttribute(thruVal);

        EZDGUIAttribute thruDt = new EZDGUIAttribute(SCRN_ID_00, "condThruDate#" + idx);
        thruDt.setStyleAttribute("display", thruDtCalDisplay);
        scrnMsg.addGUIAttribute(thruDt);

        EZDGUIAttribute thruCal = new EZDGUIAttribute(SCRN_ID_00, "condThruCalendar#" + idx);
        thruCal.setStyleAttribute("display", thruDtCalDisplay);
        scrnMsg.addGUIAttribute(thruCal);

        handler.setButtonEnabled("Setting_Name", idx, btnEnable);
        handler.setButtonEnabled("OpenWin_PrcRuleCondVal_Cmn", idx, btnEnable);

        scrnMsg.A.no(idx).prcRuleCondThruTxt_A1.setInputProtected(fldProtect);

        // Add Start 2017/03/01 QC#16011
        if (!updateAuthFlg) {
            scrnMsg.A.no(idx).prcRuleCondThruTxt_A1.setInputProtected(true);

            handler.setButtonEnabled("OpenWin_PrcRuleCondVal_Cmn", false);
            handler.setButtonEnabled("OpenWin_PrcRuleCondVal_Gen", false);
            handler.setButtonEnabled("OpenWin_PrcRuleCondVal_Item", false);
            handler.setButtonEnabled("OpenWin_PrcRuleCondVal_Acct", false);
            handler.setButtonEnabled("OpenWin_PrcRuleCondVal_Mdl", false);
        }
        // Add End 2017/03/01 QC#16011

    }

    /**
     * dtlAttrbCtrl.
     * @param handler S21CommonHandler
     * @param idx int
     * @param scrnMsg NMAL7270BMsg
     * @param updateAuthFlg boolean
     */
    public static void dtlAttrbCtrl(S21CommonHandler handler, int idx, NMAL7270BMsg scrnMsg, boolean updateAuthFlg) {
        // Input Field Change
        String popupType = getPopupType(scrnMsg.B.no(idx).prcRuleAttrbCd_B1.getValue());

        String genDisplay = "none";
        String pctDisplay = "none";
        String valDisplay = "none";
        if (POPUP_TYPE_GEN.equals(popupType)) {
            genDisplay = "";
        } else if (POPUP_TYPE_NONE_PCT.equals(popupType)) {
            pctDisplay = "";
        } else if (POPUP_TYPE_NONE_VAL.equals(popupType)) {
            valDisplay = "";
        }

        EZDGUIAttribute fmlaPk = new EZDGUIAttribute(SCRN_ID_00, "prcFmlaPk#" + idx);
        fmlaPk.setStyleAttribute("display", genDisplay);
        scrnMsg.addGUIAttribute(fmlaPk);

        // QC#6530 2016/05/19 Add Start
        EZDGUIAttribute fmlaNm = new EZDGUIAttribute(SCRN_ID_00, "prcFmlaNm#" + idx);
        fmlaNm.setStyleAttribute("display", genDisplay);
        scrnMsg.addGUIAttribute(fmlaNm);
        // QC#6530 2016/05/19 Add End

        EZDGUIAttribute fmlaBtn = new EZDGUIAttribute(SCRN_ID_00, "OpenWin_PrcFmla#" + idx);
        fmlaBtn.setStyleAttribute("display", genDisplay);
        scrnMsg.addGUIAttribute(fmlaBtn);

        EZDGUIAttribute rate = new EZDGUIAttribute(SCRN_ID_00, "prcRuleDtlRate#" + idx);
        rate.setStyleAttribute("display", pctDisplay);
        scrnMsg.addGUIAttribute(rate);

        EZDGUIAttribute valTxt = new EZDGUIAttribute(SCRN_ID_00, "prcRuleDtlTxt#" + idx);
        valTxt.setStyleAttribute("display", valDisplay);
        scrnMsg.addGUIAttribute(valTxt);

        // Add Start 2017/03/01 QC#16011
        if (!updateAuthFlg) {
            handler.setButtonEnabled("OpenWin_PrcFmla", false);
        }
    }

    private static String getPopupType(String attrbCd) {
        String rtnType = "";
        if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(attrbCd)) {
            rtnType = POPUP_TYPE_ACCT;
        } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_ITEM;
        } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE;
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(attrbCd)) {
            rtnType = POPUP_TYPE_ACCT;
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.BRANCH.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.CALL_DATE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE_DT;
        } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE;
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE;
        } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.LINE_QTY.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE;
        } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE;
        } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE_DT;
        } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE_DT;
        } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(attrbCd)) {
            rtnType = POPUP_TYPE_MDL;
        } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE;
        } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        // QC#6173 2016/04/14 Add Start
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(attrbCd)) {
            rtnType = POPUP_TYPE_ACCT;
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(attrbCd)) {
            rtnType = POPUP_TYPE_CMN;
        // QC#6173 2016/04/14 Add End
        // QC#20249 2017/08/09 Add Start
        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE;
        // QC#20249 2017/08/09 Add End
        } else if (PRC_RULE_ATTRB.FORMULA.equals(attrbCd)) {
            rtnType = POPUP_TYPE_GEN;
        } else if (PRC_RULE_ATTRB.PERCENT.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE_PCT;
        } else if (PRC_RULE_ATTRB.VALUE.equals(attrbCd)) {
            rtnType = POPUP_TYPE_NONE_VAL;
        }

        return rtnType;
    }

    /**
     * checkMandatoryCmn.
     * @param scrnMsg NMAL7270BMsg
     */
    public static void checkMandatoryCmn(NMAL7270BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcRuleNm_H1)) {
            scrnMsg.prcRuleNm_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcRuleNm_H1.getNameForMessage()});
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
            scrnMsg.effFromDt_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.effFromDt_H1.getNameForMessage()});
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.defRulePrcdNum_H1)) {
            scrnMsg.defRulePrcdNum_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.defRulePrcdNum_H1.getNameForMessage()});
        }

        scrnMsg.addCheckItem(scrnMsg.prcRuleNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcRuleDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.defRulePrcdNum_H1);
        // 2018/09/12 QC#9700 add start
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrxTpCd_H1);
        // 2018/09/12 QC#9700 add end

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!POPUP_TYPE_NONE_DT.equals(getPopupType(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue()))) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A1)) {
                    scrnMsg.A.no(i).prcRuleCondFromTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).prcRuleCondFromTxt_A1.getNameForMessage()});
                }
            } else {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxFromDt_A1)) {
                    scrnMsg.A.no(i).xxFromDt_A1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).xxFromDt_A1.getNameForMessage()});
                }
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondFromTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondThruTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxThruDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcRuleCondGrpCd_B1)) {
                scrnMsg.B.no(i).prcRuleCondGrpCd_B1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(i).prcRuleCondGrpCd_B1.getNameForMessage()});
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcRuleDtlChrgNm_B1)) {
                scrnMsg.B.no(i).prcRuleDtlChrgNm_B1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(i).prcRuleDtlChrgNm_B1.getNameForMessage()});
            }

            if (PRC_RULE_ATTRB.FORMULA.equals(scrnMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcFmlaPk_B1)) {
                    scrnMsg.B.no(i).prcFmlaPk_B1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(i).prcFmlaPk_B1.getNameForMessage()});
                }
            } else if (PRC_RULE_ATTRB.PERCENT.equals(scrnMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcRuleDtlRate_B1)) {
                    scrnMsg.B.no(i).prcRuleDtlRate_B1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(i).prcRuleDtlRate_B1.getNameForMessage()});
                }
            } else if (PRC_RULE_ATTRB.VALUE.equals(scrnMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcRuleDtlTxt_B1)) {
                    scrnMsg.B.no(i).prcRuleDtlTxt_B1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(i).prcRuleDtlTxt_B1.getNameForMessage()});
                }
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).effFromDt_B1)) {
                scrnMsg.B.no(i).effFromDt_B1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(i).effFromDt_B1.getNameForMessage()});
            }

            // 2018/06/07 QC#26099 Add Start
            checkNumeric(scrnMsg.B.no(i).prcRuleDtlTxt_B1);
            // 2018/06/07 QC#26099 Add End

            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondGrpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlChrgNm_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcFmlaPk_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlRate_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlTxt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * checkBusinessCmn.
     * @param scrnMsg NMAL7270BMsg
     */
    public static void checkBusinessCmn(NMAL7270BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1)) {
            if (ZYPDateUtil.compare(scrnMsg.effFromDt_H1.getValue(), scrnMsg.effThruDt_H1.getValue()) > 0) {
                scrnMsg.effFromDt_H1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effFromDt_H1.getNameForMessage(), scrnMsg.effThruDt_H1.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
            }
        }

        StringBuilder sb = new StringBuilder();
        // Del Start 2017/08/03 QC#18240
        //Map<String, List<String[]>> trxCondEffMap = new HashMap<String, List<String[]>>();
        // Del End 2017/08/03 QC#18240
        Map<String, List<String[]>> adjDtlEffMap = new HashMap<String, List<String[]>>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)) {
                if (ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), scrnMsg.A.no(i).effThruDt_A1.getValue()) > 0) {
                    scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).effFromDt_A1.getNameForMessage(), scrnMsg.A.no(i).effThruDt_A1.getNameForMessage() });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                }
            }

            if (!scrnMsg.A.no(0).prcRuleTrxCatgCd_A1.getValue().equals(scrnMsg.A.no(i).prcRuleTrxCatgCd_A1.getValue())) {
                scrnMsg.A.no(i).prcRuleTrxCatgCd_A1.setErrorInfo(1, NMAM8258E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleTrxCatgCd_A1);
            }

            // QC#6939 2016/05/19 Del Start
//            if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
//                    || PRC_RULE_ATTRB.CUSTOMER_PO.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
//                    || PRC_RULE_ATTRB.LINE_AMOUNT.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
//                    || PRC_RULE_ATTRB.LINE_QTY.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
//                    || PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
//                    || PRC_RULE_ATTRB.TOTAL_QRY.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
//                if (scrnMsg.A.no(i).prcRuleCondFromTxt_A1.getValue().compareTo(scrnMsg.A.no(i).prcRuleCondThruTxt_A1.getValue()) > 0) {
//                    scrnMsg.A.no(i).prcRuleCondFromTxt_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).prcRuleCondFromTxt_A1.getNameForMessage(), scrnMsg.A.no(i).prcRuleCondThruTxt_A1.getNameForMessage() });
//                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondFromTxt_A1);
//                }
//            }
//
//            if (PRC_RULE_ATTRB.CALL_DATE.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
//                    || PRC_RULE_ATTRB.PRICING_DATE.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
//                    || PRC_RULE_ATTRB.REQUEST_DATE.equals(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
//                if (ZYPDateUtil.compare(scrnMsg.A.no(i).xxFromDt_A1.getValue(), scrnMsg.A.no(i).xxThruDt_A1.getValue()) > 0) {
//                    scrnMsg.A.no(i).xxFromDt_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).xxFromDt_A1.getNameForMessage(), scrnMsg.A.no(i).xxThruDt_A1.getNameForMessage() });
//                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxFromDt_A1);
//                }
//            }
            // QC#6939 2016/05/19 Del End

            // Del Start 2017/08/03 QC#18240
            //sb.setLength(0);
            //sb = sb.append(scrnMsg.A.no(i).prcRuleTrxCatgCd_A1.getValue());
            //sb = sb.append(COMMA).append(scrnMsg.A.no(i).prcRuleAttrbCd_A1.getValue());
            //sb = sb.append(COMMA).append(scrnMsg.A.no(i).prcRuleCondFromTxt_A1.getValue());
            //String effThruDt = nullToHighValue(scrnMsg.A.no(i).effThruDt_A1.getValue());
            //if (trxCondEffMap.containsKey(sb.toString())) {
            //    List<String[]> extList =  trxCondEffMap.get(sb.toString());
            //    for (String[] extStr : extList) {
            //        if (ZYPDateUtil.compare(extStr[0], effThruDt) <= 0
            //                && ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), extStr[1]) <= 0) {
            //            scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM8113E);
            //            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            //            break;
            //        }
            //    }
            //    String[] newStr = new String[] {scrnMsg.A.no(i).effFromDt_A1.getValue(), effThruDt};
            //    extList.add(newStr);
            //    trxCondEffMap.put(sb.toString(), extList);
            //
            //} else {
            //    String[] newStr = new String[] {scrnMsg.A.no(i).effFromDt_A1.getValue(), effThruDt};
            //    List<String[]> newList = new ArrayList<String[]>();
            //    newList.add(newStr);
            //    trxCondEffMap.put(sb.toString(), newList);
            //}
            // Del End 2017/08/03 QC#18240
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).effThruDt_B1)) {
                if (ZYPDateUtil.compare(scrnMsg.B.no(i).effFromDt_B1.getValue(), scrnMsg.B.no(i).effThruDt_B1.getValue()) > 0) {
                    scrnMsg.B.no(i).effFromDt_B1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.B.no(i).effFromDt_B1.getNameForMessage(), scrnMsg.B.no(i).effThruDt_B1.getNameForMessage() });
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
                }
            }

            sb.setLength(0);
            sb = sb.append(scrnMsg.B.no(i).prcRuleCondGrpCd_B1.getValue());
            sb = sb.append(COMMA).append(scrnMsg.B.no(i).prcRuleDtlChrgNm_B1.getValue());
            sb = sb.append(COMMA).append(scrnMsg.B.no(i).prcRuleModTpCd_B1.getValue());
            sb = sb.append(COMMA).append(scrnMsg.B.no(i).prcRuleAdjTpCd_B1.getValue());
            String effThruDt = nullToHighValue(scrnMsg.B.no(i).effThruDt_B1.getValue());

            if (adjDtlEffMap.containsKey(sb.toString())) {
                List<String[]> extList =  adjDtlEffMap.get(sb.toString());
                    for (String[] extStr : extList) {
                        if (ZYPDateUtil.compare(extStr[0], effThruDt) <= 0
                                && ZYPDateUtil.compare(scrnMsg.B.no(i).effFromDt_B1.getValue(), extStr[1]) <= 0) {
                            scrnMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NMAM8113E);
                            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B1);
                            break;
                        }
                    }
                    String[] newStr = new String[] {scrnMsg.B.no(i).effFromDt_B1.getValue(), effThruDt};
                    extList.add(newStr);
                    adjDtlEffMap.put(sb.toString(), extList);

            } else {
                String[] newStr = new String[] {scrnMsg.B.no(i).effFromDt_B1.getValue(), effThruDt};
                List<String[]> newList = new ArrayList<String[]>();
                newList.add(newStr);
                adjDtlEffMap.put(sb.toString(), newList);
            }
        }

        scrnMsg.putErrorScreen();
    }

    private static String nullToHighValue(String val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        }
        return HIGH_VAL_DT;
    }
    /**
     * getParamNMAL1130ForDefPrcd
     * @param scrnMsg NMAL7270BMsg
     * @return Object[]
     */
    public static Object[] getParamNMAL1130ForDefPrcd(NMAL7270BMsg scrnMsg){
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Default Precedence Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     PRH.GLBL_CMPY_CD ");
        sb.append("    ,PRH.EZCANCELFLAG ");
        sb.append("    ,PRH.PRC_RULE_HDR_PK ");
        sb.append("    ,PRH.PRC_RULE_NM ");
        sb.append("    ,PRH.PRC_RULE_COND_TP_CD ");
        sb.append("    ,PRCT.PRC_RULE_COND_TP_DESC_TXT ");
        sb.append("    ,LOB.LINE_BIZ_TP_DESC_TXT ");
        sb.append("    ,PRC.PRC_RULE_CATG_DESC_TXT ");
        sb.append("    ,PRH.DEF_RULE_PRCD_NUM ");
        sb.append("    ,PRP.PRC_RULE_PRCD_PK ");
        sb.append("    ,PRP.PRC_RULE_PRCD_GRP_NM ");
        sb.append("FROM PRC_RULE_HDR PRH ");
        sb.append("    ,PRC_RULE_COND_TP PRCT ");
        sb.append("    ,LINE_BIZ_TP LOB ");
        sb.append("    ,PRC_RULE_CATG PRC ");
        sb.append("    ,PRC_RULE_PRCD_DTL PRPD ");
        sb.append("    ,PRC_RULE_PRCD PRP ");
        sb.append("WHERE PRH.GLBL_CMPY_CD  = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND   PRH.EZCANCELFLAG  = '0' ");
        sb.append("AND   PRH.DEF_RULE_PRCD_NUM  = ' ").append(scrnMsg.defRulePrcdNum_H1.getValue()).append("' ");
        sb.append("AND   PRH.GLBL_CMPY_CD = PRCT.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = PRCT.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.PRC_RULE_COND_TP_CD = PRCT.PRC_RULE_COND_TP_CD(+) ");
        sb.append("AND   PRH.GLBL_CMPY_CD = LOB.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = LOB.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.LINE_BIZ_TP_CD = LOB.LINE_BIZ_TP_CD(+) ");
        sb.append("AND   PRH.GLBL_CMPY_CD = PRC.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = PRC.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.PRC_RULE_CATG_CD = PRC.PRC_RULE_CATG_CD(+) ");
        sb.append("AND   PRH.GLBL_CMPY_CD = PRPD.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRH.EZCANCELFLAG = PRPD.EZCANCELFLAG(+) ");
        sb.append("AND   PRH.PRC_RULE_HDR_PK = PRPD.PRC_RULE_HDR_PK(+) ");
        sb.append("AND   PRPD.GLBL_CMPY_CD = PRP.GLBL_CMPY_CD(+) ");
        sb.append("AND   PRPD.EZCANCELFLAG = PRP.EZCANCELFLAG(+) ");
        sb.append("AND   PRPD.PRC_RULE_PRCD_PK = PRP.PRC_RULE_PRCD_PK(+) ");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_4);
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Rule or Table Name";
        whereArray0[IDX_1] = "PRC_RULE_NM";
        whereArray0[IDX_2] = "%";
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Precedence Group#";
        whereArray1[IDX_1] = "PRC_RULE_PRCD_PK";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Prcd. Group Name";
        whereArray2[IDX_1] = "PRC_RULE_PRCD_GRP_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        
        List<Object[]> columnList = new ArrayList<Object[]>(IDX_8);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "ID";
        columnArray0[IDX_1] = "PRC_RULE_HDR_PK";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Rule or Table Name";
        columnArray1[IDX_1] = "PRC_RULE_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Price Adjust. Type";
        columnArray2[IDX_1] = "PRC_RULE_COND_TP_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Line of Business";
        columnArray3[IDX_1] = "LINE_BIZ_TP_DESC_TXT";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Price Adjust Category";
        columnArray4[IDX_1] = "PRC_RULE_CATG_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Default Rule Prcd.";
        columnArray5[IDX_1] = "DEF_RULE_PRCD_NUM";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_11);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[IDX_4];
        columnArray6[IDX_0] = "Precedence Group";
        columnArray6[IDX_1] = "PRC_RULE_PRCD_PK";
        columnArray6[IDX_2] = BigDecimal.valueOf(IDX_11);
        columnArray6[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[IDX_4];
        columnArray7[IDX_0] = "Prcd. Group Name";
        columnArray7[IDX_1] = "PRC_RULE_PRCD_GRP_NM";
        columnArray7[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray7[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray7);
        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_3);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PRC_RULE_COND_TP_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "DEF_RULE_PRCD_NUM";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "PRC_RULE_NM";
        sortConditionArray2[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray2);
        params[IDX_5] = sortConditionList;

        params[IDX_6] = new ArrayList<EZDBStringItem>();;

        return params;
    }
    // QC#9694 2016/06/28 Mod End

    // 2018/06/07 QC#26099 Add Start
    public static void checkNumeric(EZDBStringItem target) {

        if (ZYPCommonFunc.hasValue(target)) {
            //remove Comma
            ZYPEZDItemValueSetter.setValue(target, target.getValue().replace(",", ""));

            //Numeric Check
            if (!ZYPCommonFunc.isNumberCheck(target.getValue())) {
                target.setErrorInfo(1, ZZM9004E, new String[] {target.getNameForMessage()});
            }
        }
    }
    // 2018/06/07 QC#26099 Add End
}
