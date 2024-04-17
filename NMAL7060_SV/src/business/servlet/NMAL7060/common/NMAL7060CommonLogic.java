/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060.common;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_APL;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_APR;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_RST;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW_BLLG;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_FILTER_SEARCH;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_INSERT_ROW_BLLG;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_OPENWIN_BLLGNM;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_OPENWIN_MDL;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.FUNC_ID_UPDATE;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7060.NMAL7060BMsg;
import business.servlet.NMAL7060.NMAL7060_ABMsgArray;
import business.servlet.NMAL7060.NMAL7060_BBMsgArray;
import business.servlet.NMAL7060.NMAL7060_CBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7060CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         q09947          Create          N/A
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#6738
 * 2017/02/10   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7060CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7060BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL7060BMsg scrnMsg) {
        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        initCmnBtnProp(handler);
        if (ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgPk)) {
            controlScreenFieldsForHeader(handler, scrnMsg, true);
        } else {
            controlScreenFieldsForHeader(handler, scrnMsg, false);
        }

        controlButton(userProfileService, handler, scrnMsg);

        controlScreenFieldsForDetail(handler, scrnMsg);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
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
     * controlScreenFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3070BMsg
     * @param activeFlg Boolean
     */
    public static final void controlScreenFieldsForHeader(EZDCommonHandler handler, NMAL7060BMsg scrnMsg, boolean activeFlg) {
        scrnMsg.prcMtrPkgNm.setInputProtected(false);
        scrnMsg.prcMtrPkgDescTxt.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(activeFlg);
        scrnMsg.effThruDt.setInputProtected(false);
        scrnMsg.corpAdvPrcFlg.setInputProtected(false);
        // Add Start 2017/02/17 QC#17529
        scrnMsg.mdlNm_F1.setInputProtected((scrnMsg.xxDtlCnt.getValue().compareTo(BigDecimal.ZERO) <= 0));
        // Add End 2017/02/17 QC#17529
    }

    /**
     * controlScreenFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3070BMsg
     */
    public static final void controlScreenFieldsForDetail(EZDCommonHandler handler, NMAL7060BMsg scrnMsg) {

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.xxRadioBtn.setInputProtected(false);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlNm_A1)) {
                scrnMsg.A.no(i).xxLinkAncr_AM.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkAncr_AM.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.A.no(i).xxLinkAncr_AM.clear();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgPk)
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcMtrPkgMdlPk_A1.getValue())) {
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
            }
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(false);
            scrnMsg.A.no(i).xxDt10Dt_AI.setInputProtected(true);
            scrnMsg.A.no(i).xxFullNm_AI.setInputProtected(true);
            scrnMsg.A.no(i).xxDt10Dt_AU.setInputProtected(true);
            scrnMsg.A.no(i).xxFullNm_AU.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).bllgMtrLvlNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).mtrLbDescTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).mtrLbNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).mdseCd_B1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).mtrLbDescTxt_C1.setInputProtected(true);
            // QC#6738 2016/04/26 Del start
//            scrnMsg.C.no(i).bllblFlg_C1.setInputProtected(true);
//            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.C.no(i).mtrEntryMndFlg_C1.getValue())) {
//                scrnMsg.C.no(i).bllblFlg_C1.setInputProtected(false);
//            } else {
//                scrnMsg.C.no(i).bllblFlg_C1.setInputProtected(true);
//            }
//            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).bllblFlg_C1.getValue())
//                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).actvFlg_C1.getValue())) {
//                scrnMsg.C.no(i).actvFlg_C1.setInputProtected(true);
//            } else {
//                scrnMsg.C.no(i).actvFlg_C1.setInputProtected(false);
//            }
            scrnMsg.C.no(i).mtrMultRate_C1.setInputProtected(false);
//            scrnMsg.C.no(i).xxDt10Dt_CI.setInputProtected(true);
//            scrnMsg.C.no(i).xxFullNm_CI.setInputProtected(true);
//            scrnMsg.C.no(i).xxDt10Dt_CU.setInputProtected(true);
//            scrnMsg.C.no(i).xxFullNm_CU.setInputProtected(true);
            // QC#6738 2016/04/26 Del end
        }
    }

    /**
     * controlButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg 7060BMsg
     */
    public static final void controlButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL7060BMsg scrnMsg) {

        if (isUpdateUser(userProfileService)) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            // Mod Start 2017/02/10 QC#17529
//            if (scrnMsg.A.getValidCount() < scrnMsg.A.length()) {
//                handler.setButtonProperties(BTN_INSERT_ROW[0], BTN_INSERT_ROW[1], BTN_INSERT_ROW[2], 1, null);
//            } else {
//                handler.setButtonProperties(BTN_INSERT_ROW[0], BTN_INSERT_ROW[1], BTN_INSERT_ROW[2], 0, null);
//            }
            handler.setButtonProperties(BTN_INSERT_ROW[0], BTN_INSERT_ROW[1], BTN_INSERT_ROW[2], 1, null);
            // Mod End 2017/02/10 QC#17529

            handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 0, null);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn.getValue())) {
                int index = scrnMsg.xxRadioBtn.getValueInt();
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).ezInTime_AK.getValue())) {
                    handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 1, null);
                }
            }

            handler.setButtonProperties(BTN_INSERT_ROW_BLLG[0], BTN_INSERT_ROW_BLLG[1], BTN_INSERT_ROW_BLLG[2], 0, null);
            handler.setButtonProperties(BTN_DELETE_ROW_BLLG[0], BTN_DELETE_ROW_BLLG[1], BTN_DELETE_ROW_BLLG[2], 0, null);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlNm_A1)) {
                    continue;
                }

                if (scrnMsg.B.getValidCount() < scrnMsg.B.length()) {
                    handler.setButtonProperties(BTN_INSERT_ROW_BLLG[0], BTN_INSERT_ROW_BLLG[1], BTN_INSERT_ROW_BLLG[2], 1, null);
                }

                if (scrnMsg.B.getValidCount() > 0) {
                    handler.setButtonProperties(BTN_DELETE_ROW_BLLG[0], BTN_DELETE_ROW_BLLG[1], BTN_DELETE_ROW_BLLG[2], 1, null);
                }
                break;
            }
            // Add Start 2017/02/17 QC#17529
            if (scrnMsg.xxDtlCnt.getValue().compareTo(BigDecimal.ZERO) > 0) {
                if (scrnMsg.B.getValidCount() < scrnMsg.B.length()) {
                    handler.setButtonProperties(BTN_INSERT_ROW_BLLG[0], BTN_INSERT_ROW_BLLG[1], BTN_INSERT_ROW_BLLG[2], 1, null);
                }

                if (scrnMsg.B.getValidCount() > 0) {
                    handler.setButtonProperties(BTN_DELETE_ROW_BLLG[0], BTN_DELETE_ROW_BLLG[1], BTN_DELETE_ROW_BLLG[2], 1, null);
                }
                handler.setButtonProperties(BTN_FILTER_SEARCH[0], BTN_FILTER_SEARCH[1], BTN_FILTER_SEARCH[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_FILTER_SEARCH[0], BTN_FILTER_SEARCH[1], BTN_FILTER_SEARCH[2], 0, null);
            }
            // Add End 2017/02/17 QC#17529
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            handler.setButtonProperties(BTN_INSERT_ROW[0], BTN_INSERT_ROW[1], BTN_INSERT_ROW[2], 0, null);
            handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 0, null);
            handler.setButtonProperties(BTN_INSERT_ROW_BLLG[0], BTN_INSERT_ROW_BLLG[1], BTN_INSERT_ROW_BLLG[2], 0, null);
            handler.setButtonProperties(BTN_DELETE_ROW_BLLG[0], BTN_DELETE_ROW_BLLG[1], BTN_DELETE_ROW_BLLG[2], 0, null);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (isUpdateUser(userProfileService)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcMtrPkgMdlPk_A1)) {
                    handler.setButtonEnabled(BTN_OPENWIN_MDL[0], i, false);
                } else {
                    handler.setButtonEnabled(BTN_OPENWIN_MDL[0], i, true);
                }
            } else {
                handler.setButtonEnabled(BTN_OPENWIN_MDL[0], i, false);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (isUpdateUser(userProfileService)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcMtrPkgBllgMtrPk_B1)) {
                    handler.setButtonEnabled(BTN_OPENWIN_BLLGNM[0], i, false);
                } else {
                    handler.setButtonEnabled(BTN_OPENWIN_BLLGNM[0], i, true);
                }
            } else {
                handler.setButtonEnabled(BTN_OPENWIN_BLLGNM[0], i, false);
            }
        }
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNC_ID_UPDATE);
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
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnAMsgAry NMAL7060_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7060BMsg scrnMsg, NMAL7060_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnAMsgAry NMAL7060_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7060BMsg scrnMsg, NMAL7060_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnAMsgAry NMAL7060_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7060BMsg scrnMsg, NMAL7060_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnBMsgAry NMAL7060_BBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7060BMsg scrnMsg, NMAL7060_BBMsgArray scrnBMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnBMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnBMsgAry NMAL7060_BBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7060BMsg scrnMsg, NMAL7060_BBMsgArray scrnBMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnBMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnBMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnBMsgAry NMAL7060_BBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7060BMsg scrnMsg, NMAL7060_BBMsgArray scrnBMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnBMsgAry);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnCMsgAry NMAL7060_CBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7060BMsg scrnMsg, NMAL7060_CBMsgArray scrnCMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnCMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnCMsgAry NMAL7060_CBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7060BMsg scrnMsg, NMAL7060_CBMsgArray scrnCMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnCMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnCMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7060BMsg
     * @param scrnCMsgAry NMAL7060_CBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7060BMsg scrnMsg, NMAL7060_CBMsgArray scrnCMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnCMsgAry);
    }
}
