/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2840.common;

import static business.servlet.NMAL2840.constant.NMAL2840Constant.BIZ_ID;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_APL;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_APR;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_DEL;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_DWL;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_RJT;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_RST;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_RTN;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_SAV;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_CMN_SUB;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_EXP_FOR_REVIEW;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_EXTRACT_RESET;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_EXTRACT_SEND;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_PAGE_NEXT;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_PAGE_PREV;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_REVIEW_RESET;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.BTN_UPDATE_S21;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.SCRN_ID_00;
import static business.servlet.NMAL2840.constant.NMAL2840Constant.UPDATE_AUTHORITY;

import java.util.List;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2840.NMAL2840BMsg;
import business.servlet.NMAL2840.NMAL2840_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_CRIT;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL2840CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura       Create          N/A
 * 2016/06/15   Fujitsu         R.Nakamura       Update          QC#10073
 * 2016/06/15   Fujitsu         R.Nakamura       Update          QC#9960
 * 2016/06/17   Fujitsu         R.Nakamura       Update          QC#10340
 * 2016/06/20   Fujitsu         R.Nakamura       Update          QC#10340
 * 2016/07/01   Fujitsu         R.Nakamura       Update          QC#11316
 * 2016/07/12   Fujitsu         R.Nakamura       Update          QC#9536
 *</pre>
 */
public class NMAL2840CommonLogic {

    /**
     * updateAuthority
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean updateAuthority(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(UPDATE_AUTHORITY);
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param updateAuthorityFlg boolean
     */
    public static void initCmnBtnProp(EZDCommonHandler handler, boolean updateAuthorityFlg) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        // Mod Start 2016/06/17 QC#10340
        if (updateAuthorityFlg) {
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        }
        // Mod Start 2016/06/17 QC#10340
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL3000BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL2840BMsg scrnMsg) {
        boolean updateAuthorityFlg = updateAuthority(userProfileService);

        initCmnBtnProp(handler, updateAuthorityFlg);

        int pageNum = scrnMsg.xxPageShowToNum.getValueInt();
        int allPageNum = scrnMsg.xxPageShowOfNum.getValueInt();

        // Mod Start 2016/06/15 QC#9960
        // setProtectButton(!updateAuthorityFlg, handler, pageNum,
        // allPageNum);
        // scrnProtectHeader(updateAuthorityFlg, scrnMsg);
        setProtectButton(updateAuthorityFlg, handler, pageNum, allPageNum);
        scrnProtectHeader(!updateAuthorityFlg, scrnMsg);
        // Mod End 2016/06/15 QC#9960
        scrnProtectDetail(scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param updateAuthorityFlg boolean
     * @param handler EZDCommonHandler
     * @param pageNum int
     * @param allPageNum int
     */
    public static void setProtectButton(boolean updateAuthorityFlg, EZDCommonHandler handler, int pageNum, int allPageNum) {

        handler.setButtonEnabled(BTN_EXTRACT_SEND, updateAuthorityFlg);
        handler.setButtonEnabled(BTN_EXTRACT_RESET, updateAuthorityFlg);
        handler.setButtonEnabled(BTN_REVIEW_RESET, updateAuthorityFlg);
        handler.setButtonEnabled(BTN_EXP_FOR_REVIEW, updateAuthorityFlg);
        handler.setButtonEnabled(BTN_UPDATE_S21, updateAuthorityFlg);
        if (pageNum > 1) {
            handler.setButtonEnabled(BTN_PAGE_PREV, true);
        } else {
            handler.setButtonEnabled(BTN_PAGE_PREV, false);
        }
        if (pageNum < allPageNum) {
            handler.setButtonEnabled(BTN_PAGE_NEXT, true);
        } else {
            handler.setButtonEnabled(BTN_PAGE_NEXT, false);
        }
    }

    /**
     * scrnProtect
     * @param updateAuthorityFlg boolean
     * @param scrnMsg NMAL3000BMsg
     */
    public static void scrnProtectHeader(boolean updateAuthorityFlg, NMAL2840BMsg scrnMsg) {

        scrnMsg.xxDtTm_1.setInputProtected(updateAuthorityFlg);

        // Add Start 2016/06/15 QC#10073
        scrnMsg.dunsCritCd_PS.setInputProtected(updateAuthorityFlg);
        // Add End 2016/06/15 QC#10073
        scrnMsg.dunsCritDefValFlg_11.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_12.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_21.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_31.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_32.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_33.setInputProtected(updateAuthorityFlg);
        scrnMsg.effFromDt.setInputProtected(updateAuthorityFlg);

        scrnMsg.xxDtTm_2.setInputProtected(updateAuthorityFlg);

        // Add Start 2016/06/15 QC#10073
        scrnMsg.xxDtTm_PS.setInputProtected(updateAuthorityFlg);
        // Add End 2016/06/15 QC#10073
        scrnMsg.dunsCritDescTxt_51.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDescTxt_52.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDescTxt_53.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDescTxt_54.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDescTxt_55.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDescTxt_56.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDescTxt_57.setInputProtected(updateAuthorityFlg);

        scrnMsg.dunsCritDefValFlg_51.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_52.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_53.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_54.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_55.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_56.setInputProtected(updateAuthorityFlg);
        scrnMsg.dunsCritDefValFlg_57.setInputProtected(updateAuthorityFlg);

        if (!updateAuthorityFlg) {
            scrnProtectExtract(scrnMsg);
            scrnProtectReview(scrnMsg);
        }
    }

    /**
     * scrnProtect
     * @param scrnMsg NMAL3000BMsg
     */
    public static void scrnProtectExtract(NMAL2840BMsg scrnMsg) {

        boolean checkFlg = false;

        // Mod Start 2016/07/12 QC#9536
        if (DUNS_CRIT.YEARLY.equals(scrnMsg.dunsCritCd_PS.getValue())) {
            checkFlg = true;
            ZYPEZDItemValueSetter.setValue(scrnMsg.dunsCritDefValFlg_11, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dunsCritDefValFlg_12, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dunsCritDefValFlg_21, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dunsCritDefValFlg_31, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dunsCritDefValFlg_32, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dunsCritDefValFlg_33, ZYPConstant.CHKBOX_ON_Y);
            scrnMsg.effFromDt.clear();
        } else {
            checkFlg = false;
        }
        // Mod End 2016/07/12 QC#9536

        scrnMsg.dunsCritDefValFlg_11.setInputProtected(checkFlg);
        scrnMsg.dunsCritDefValFlg_12.setInputProtected(checkFlg);
        scrnMsg.dunsCritDefValFlg_21.setInputProtected(checkFlg);
        scrnMsg.dunsCritDefValFlg_31.setInputProtected(checkFlg);
        scrnMsg.dunsCritDefValFlg_32.setInputProtected(checkFlg);
        scrnMsg.dunsCritDefValFlg_33.setInputProtected(checkFlg);
        scrnMsg.effFromDt.setInputProtected(checkFlg);

    }

    /**
     * scrnProtectReview
     * @param scrnMsg NMAL3000BMsg
     */
    public static void scrnProtectReview(NMAL2840BMsg scrnMsg) {

        scrnProtectReviewItem(scrnMsg.dunsCritDefValFlg_51, scrnMsg.dunsCritDescTxt_51);
        scrnProtectReviewItem(scrnMsg.dunsCritDefValFlg_52, scrnMsg.dunsCritDescTxt_52);
        scrnProtectReviewItem(scrnMsg.dunsCritDefValFlg_53, scrnMsg.dunsCritDescTxt_53);
        scrnProtectReviewItem(scrnMsg.dunsCritDefValFlg_54, scrnMsg.dunsCritDescTxt_54);
        scrnProtectReviewItem(scrnMsg.dunsCritDefValFlg_55, scrnMsg.dunsCritDescTxt_55);
        scrnProtectReviewItem(scrnMsg.dunsCritDefValFlg_56, scrnMsg.dunsCritDescTxt_56);
        scrnProtectReviewItem(scrnMsg.dunsCritDefValFlg_57, scrnMsg.dunsCritDescTxt_57);
    }

    /**
     * scrnProtect
     * @param itemCheckBox EZDBStringItem
     * @param itemTxt EZDBStringItem
     */
    public static void scrnProtectReviewItem(EZDBStringItem itemCheckBox, EZDBStringItem itemTxt) {

        // Mod Start 2016/07/12 QC#9536
        if (ZYPConstant.CHKBOX_ON_Y.equals(itemCheckBox.getValue())) {
            itemTxt.clear();
            itemTxt.setInputProtected(true);
        } else {
            itemTxt.setInputProtected(false);
        }
        // Mod End 2016/07/12 QC#9536
    }

    /**
     * scrnProtect
     * @param scrnMsg NMAL3000BMsg
     */
    public static void scrnProtectDetail(NMAL2840BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Mod Start 2016/06/15 QC#9960
            // scrnMsg.A.no(i).dunsProcTpDescTxt.setInputProtected(false);
            // scrnMsg.A.no(i).dunsFileNm_2.setInputProtected(false);
            // scrnMsg.A.no(i).dunsFileLineNum.setInputProtected(false);
            // scrnMsg.A.no(i).xxDtTm_4.setInputProtected(false);
            // scrnMsg.A.no(i).dunsProcById.setInputProtected(false);
            // scrnMsg.A.no(i).dunsCritValTxt.setInputProtected(false);
            scrnMsg.A.no(i).dunsProcTpDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).dunsFileNm_2.setInputProtected(true);
            scrnMsg.A.no(i).dunsFileLineNum.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_4.setInputProtected(true);
            // Mod Start 2016/07/01 QC#11316
            // scrnMsg.A.no(i).dunsProcById.setInputProtected(true);
            scrnMsg.A.no(i).fill103Txt.setInputProtected(true);
            // Mod Start 2016/06/20 QC#10340
            // scrnMsg.A.no(i).dunsCritValTxt.setInputProtected(true);
            // scrnMsg.A.no(i).fill212Txt.setInputProtected(true);
            scrnMsg.A.no(i).xxDunsProcCmntTxt.setInputProtected(true);
            // Mod End 2016/06/20 QC#10340
            // Mod End 2016/06/15 QC#9960
            // Mod End 2016/07/01 QC#11316
        }
    }

    /**
     * Set Common Button properties.
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
     * @param scrnMsg NMAL2840BMsg
     * @param scrnAMsgAry NMAL2840_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2840BMsg scrnMsg, NMAL2840_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2840BMsg
     * @param scrnAMsgAry NMAL2840_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2840BMsg scrnMsg, NMAL2840_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL2840BMsg
     * @param scrnAMsgAry NMAL2840_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL2840BMsg scrnMsg, NMAL2840_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * commonAddCheckItemExtract
     * @param scrnMsg NMAL2840BMsg
     */
    public static void commonAddCheckItemExtract(NMAL2840BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.dunsCritCd_PS);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_11);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_12);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_21);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_31);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_32);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_33);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
    }

    /**
     * commonAddCheckItemExtract
     * @param scrnMsg NMAL2840BMsg
     */
    public static void commonAddCheckItemReview(NMAL2840BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.xxDtTm_PS);

        scrnMsg.addCheckItem(scrnMsg.dunsCritDescTxt_51);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDescTxt_52);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDescTxt_53);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDescTxt_54);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDescTxt_55);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDescTxt_56);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDescTxt_57);

        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_51);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_52);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_53);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_54);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_55);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_56);
        scrnMsg.addCheckItem(scrnMsg.dunsCritDefValFlg_57);
    }
}
