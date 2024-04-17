/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL1110.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFBL1110.NFBL1110BMsg;
import business.servlet.NFBL1110.constant.NFBL1110Constant;
import business.servlet.NFBL2040.NFBL2040BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12690
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#13442
 * 2017/01/20   Hitachi         E.Kameishi      Update          QC#17156
 * 2017/03/17   Hitachi         E.Kameishi      Update          QC#14205
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2017/12/25   Hitachi         Y.Takeno        Update          QC#22831
 * </pre>
 */
public class NFBL1110CommonLogic implements NFBL1110Constant {

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1110BMsg
     */
    @SuppressWarnings("unchecked")
    public static void initControl(EZDCommonHandler handler, NFBL1110BMsg scrnMsg) {

        String userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        List funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);

        // TODO test start
//        funcList = new ArrayList();
//        funcList.add(FUNC_ID_NFBL1110T030);
        // TODO test end

        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Invoice Entry Screen(NFBL1110). UserID is -> " + userId);
        }

        boolean isRef = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.apMaintInvStsCd_IH.getValue())) {
            int intApMaintInvStsCd_IH = Integer.parseInt(scrnMsg.apMaintInvStsCd_IH.getValue());
            int intApMaintInvStsCd_12 = Integer.parseInt(AP_MAINT_INV_STS_CD_12);
            if (
                (
                    funcList.contains(FUNC_ID_NFBL1110T010) // Function ID NFBL1110T010 (Reference)
                && !funcList.contains(FUNC_ID_NFBL1110T020)
                && !funcList.contains(FUNC_ID_NFBL1110T030)
                )
            ||  scrnMsg.apMaintInvStsCd_IH.getValue().equals(AP_MAINT_INV_STS_CD_10)
            ||  (
                    scrnMsg.apMaintInvStsCd_IH.getValue().equals(AP_MAINT_INV_STS_CD_11)
                && !funcList.contains(FUNC_ID_NFBL1110T030)
                )
            ||  intApMaintInvStsCd_IH >= intApMaintInvStsCd_12
            ) {
                isRef = true;
            }
        }
        if (isRef) {
            setInputProtectedForRef(scrnMsg);
            setButtonForRef(handler, scrnMsg);
        } else {
            if (funcList.contains(FUNC_ID_NFBL1110T030)) {
                /** Function ID NFBL1110T030 (Registration/Update with Approve/Reject) */
                setInputProtectedForAdmin(scrnMsg);
                setButtonForAdmin(handler, scrnMsg);
            } else if (funcList.contains(FUNC_ID_NFBL1110T020)) {
                /** Function ID NFBL1110T020 (Registration/Update without Approve/Reject) */
                setInputProtectedForMod(scrnMsg);
                setButtonForMod(handler, scrnMsg);
            } else {
                /** Reference */
                setInputProtectedForRef(scrnMsg);
                setButtonForRef(handler, scrnMsg);
            }
        }
        initAppFracDigit(scrnMsg);
    }

    
    /**
     * setInputProtectedForRef
     * @param scrnMsg NFBL1110BMsg
     */
    public static void setInputProtectedForRef(NFBL1110BMsg scrnMsg) {

        // Batch Header
        scrnMsg.apBatNum_BA.setInputProtected(true);
        scrnMsg.apCtrlAmt_BA.setInputProtected(true);
        scrnMsg.apCtrlCnt_BA.setInputProtected(true);
        scrnMsg.apBatDt_BA.setInputProtected(true);
        scrnMsg.apRunTotAmt_BA.setInputProtected(true);
        scrnMsg.apRunTotCnt_BA.setInputProtected(true);
        // Invoice Header
        // START 2016/09/09 K.Kojima [QC#12725,MOD]
        // scrnMsg.apvrUsrNm_IH.setInputProtected(true);
        scrnMsg.varCharConstVal_IH.setInputProtected(true);
        // END 2016/09/09 K.Kojima [QC#12725,MOD]
        scrnMsg.apInvAmt_IH.setInputProtected(true);
        scrnMsg.apDsWfStsNm_IH.setInputProtected(true);
        scrnMsg.prntVndCd_L.setInputProtected(true);
        scrnMsg.prntVndCd_IH.setInputProtected(true);
        scrnMsg.prntVndNm_IH.setInputProtected(true);
        scrnMsg.apMiscAmt_IH.setInputProtected(true);
        scrnMsg.apInvNum_IH.setInputProtected(true);
        scrnMsg.vndSiteNm_L.setInputProtected(true);
        scrnMsg.vndSiteNm_IH.setInputProtected(true);
        scrnMsg.apTaxAmt_IH.setInputProtected(true);
        scrnMsg.invDt_IH.setInputProtected(true);
        scrnMsg.apMaintInvStsNm_IH.setInputProtected(true);
        scrnMsg.lateFeeAmt_IH.setInputProtected(true);
        // Field values for [Add Serial] button
        scrnMsg.xxChkBox_AD.setInputProtected(true);
        // START 2016/09/29 K.Kojima [QC#13442,ADD]
        scrnMsg.serNum_L.setInputProtected(true);
        // END 2016/09/29 K.Kojima [QC#13442,ADD]
        scrnMsg.serNum_AD.setInputProtected(true);
        scrnMsg.ovrdSerNum_AD.setInputProtected(true);
        scrnMsg.startDt_AD.setInputProtected(true);
        scrnMsg.endDt_AD.setInputProtected(true);
        scrnMsg.baseAmt_AD.setInputProtected(true);
        // START 2016/09/29 W.Honda [Unit Test#201,MOD]
        scrnMsg.xxLinkAncr_ID.setInputProtected(true);
        // START 2016/09/29 W.Honda [Unit Test#201,MOD]
        // Invoice Line
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).ovrdSerNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).startDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).endDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).baseAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cntrTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).startReadMtrCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).endReadMtrCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).readMtrCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).apTolAmt_A1.setInputProtected(true);
        }
        // Comment only for Administrator
        scrnMsg.invCmntTxt_CO.setInputProtected(true);
        scrnMsg.apAdjAmt_CO.setInputProtected(true);
        scrnMsg.apAdjRsnCd_CO.setInputProtected(true);
    }

    /**
     * setInputProtectedForMod
     * @param scrnMsg NFBL1110BMsg
     */
    public static void setInputProtectedForMod(NFBL1110BMsg scrnMsg) {

        // Batch Header
        scrnMsg.apBatNum_BA.setInputProtected(true);
        scrnMsg.apCtrlAmt_BA.setInputProtected(false);
        scrnMsg.apCtrlCnt_BA.setInputProtected(false);
        scrnMsg.apBatDt_BA.setInputProtected(false);
        scrnMsg.apRunTotAmt_BA.setInputProtected(true);
        scrnMsg.apRunTotCnt_BA.setInputProtected(true);
        // Invoice Header
        // START 2016/09/09 K.Kojima [QC#12725,MOD]
        // scrnMsg.apvrUsrNm_IH.setInputProtected(true);
        scrnMsg.varCharConstVal_IH.setInputProtected(true);
        // END 2016/09/09 K.Kojima [QC#12725,MOD]
        scrnMsg.apInvAmt_IH.setInputProtected(false);
        scrnMsg.apDsWfStsNm_IH.setInputProtected(true);
        scrnMsg.prntVndCd_L.setInputProtected(false);
        scrnMsg.prntVndCd_IH.setInputProtected(false);
        scrnMsg.prntVndNm_IH.setInputProtected(true);
        scrnMsg.apMiscAmt_IH.setInputProtected(false);
        scrnMsg.apInvNum_IH.setInputProtected(false);
        scrnMsg.vndSiteNm_L.setInputProtected(false);
        scrnMsg.vndSiteNm_IH.setInputProtected(false);
        scrnMsg.apTaxAmt_IH.setInputProtected(false);
        scrnMsg.invDt_IH.setInputProtected(false);
        scrnMsg.apMaintInvStsNm_IH.setInputProtected(true);
        scrnMsg.lateFeeAmt_IH.setInputProtected(false);
        // Field values for [Add Serial] button
        scrnMsg.xxChkBox_AD.setInputProtected(false);
        // START 2016/09/29 K.Kojima [QC#13442,ADD]
        scrnMsg.serNum_L.setInputProtected(false);
        // END 2016/09/29 K.Kojima [QC#13442,ADD]
        scrnMsg.serNum_AD.setInputProtected(false);
        scrnMsg.ovrdSerNum_AD.setInputProtected(false);
        scrnMsg.startDt_AD.setInputProtected(false);
        scrnMsg.endDt_AD.setInputProtected(false);
        scrnMsg.baseAmt_AD.setInputProtected(false);
        // START 2016/09/30 W.Honda [Unit Test#201,MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.apMaintInvStsCd_IH)
                && !AP_MAINT_INV_STS_CD_00.equals(scrnMsg.apMaintInvStsCd_IH.getValue())) {
            scrnMsg.xxLinkAncr_ID.setInputProtected(true);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxSrvUrlTxt)) {
                scrnMsg.xxSrvUrlTxt.setInputProtected(false);
            }
        } else {
            scrnMsg.xxLinkAncr_ID.setInputProtected(false);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxSrvUrlTxt)) {
                scrnMsg.xxSrvUrlTxt.setInputProtected(true);
            }
        }
        // START 2016/09/30 W.Honda [Unit Test#201,MOD]
        // Invoice Line
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Mod Start 2016/08/22 QC#12830
            //int remainder = i % INT_6;
            //if (remainder == 0) {
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(false);
                scrnMsg.A.no(i).ovrdSerNum_A1.setInputProtected(false);
                scrnMsg.A.no(i).startDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).endDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).baseAmt_A1.setInputProtected(false);
            } else {
                //scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).ovrdSerNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).startDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).endDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).baseAmt_A1.setInputProtected(true);
            }
            // Mod End 2016/08/22 QC#12830
            scrnMsg.A.no(i).cntrTpCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).startReadMtrCnt_A1.setInputProtected(false);
            scrnMsg.A.no(i).endReadMtrCnt_A1.setInputProtected(false);
            scrnMsg.A.no(i).readMtrCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).apTolAmt_A1.setInputProtected(false);
        }
        // Comment only for Administrator
        scrnMsg.invCmntTxt_CO.setInputProtected(true);
        scrnMsg.apAdjAmt_CO.setInputProtected(true);
        scrnMsg.apAdjRsnCd_CO.setInputProtected(true);
    }

    /**
     * setInputProtectedForAdmin
     * @param scrnMsg NFBL1110BMsg
     */
    public static void setInputProtectedForAdmin(NFBL1110BMsg scrnMsg) {

        if (scrnMsg.apMaintInvStsCd_IH.getValue().equals(AP_MAINT_INV_STS_CD_11)) {
            // Batch Header
            scrnMsg.apBatNum_BA.setInputProtected(true);
            scrnMsg.apCtrlAmt_BA.setInputProtected(true);
            scrnMsg.apCtrlCnt_BA.setInputProtected(true);
            scrnMsg.apBatDt_BA.setInputProtected(true);
            scrnMsg.apRunTotAmt_BA.setInputProtected(true);
            scrnMsg.apRunTotCnt_BA.setInputProtected(true);
            // Invoice Header
            // START 2016/09/09 K.Kojima [QC#12725,MOD]
            // scrnMsg.apvrUsrNm_IH.setInputProtected(true);
            scrnMsg.varCharConstVal_IH.setInputProtected(true);
            // END 2016/09/09 K.Kojima [QC#12725,MOD]
            scrnMsg.apInvAmt_IH.setInputProtected(true);
            scrnMsg.apDsWfStsNm_IH.setInputProtected(true);
            scrnMsg.prntVndCd_L.setInputProtected(true);
            scrnMsg.prntVndCd_IH.setInputProtected(true);
            scrnMsg.prntVndNm_IH.setInputProtected(true);
            scrnMsg.apMiscAmt_IH.setInputProtected(true);
            scrnMsg.apInvNum_IH.setInputProtected(true);
            scrnMsg.vndSiteNm_L.setInputProtected(true);
            scrnMsg.vndSiteNm_IH.setInputProtected(true);
            scrnMsg.apTaxAmt_IH.setInputProtected(true);
            scrnMsg.invDt_IH.setInputProtected(true);
            scrnMsg.apMaintInvStsNm_IH.setInputProtected(true);
            scrnMsg.lateFeeAmt_IH.setInputProtected(true);
            // Field values for [Add Serial] button
            scrnMsg.xxChkBox_AD.setInputProtected(true);
            // START 2016/09/29 K.Kojima [QC#13442,ADD]
            scrnMsg.serNum_L.setInputProtected(true);
            // END 2016/09/29 K.Kojima [QC#13442,ADD]
            scrnMsg.serNum_AD.setInputProtected(true);
            scrnMsg.ovrdSerNum_AD.setInputProtected(true);
            scrnMsg.startDt_AD.setInputProtected(true);
            scrnMsg.endDt_AD.setInputProtected(true);
            scrnMsg.baseAmt_AD.setInputProtected(true);
            // START 2016/09/29 W.Honda [Unit Test#201,MOD]
            if (ZYPCommonFunc.hasValue(scrnMsg.apMaintInvStsCd_IH)
                    && !AP_MAINT_INV_STS_CD_00.equals(scrnMsg.apMaintInvStsCd_IH.getValue())) {
                scrnMsg.xxLinkAncr_ID.setInputProtected(true);
                if (ZYPCommonFunc.hasValue(scrnMsg.xxSrvUrlTxt)) {
                    scrnMsg.xxSrvUrlTxt.setInputProtected(false);
                }
            } else {
                scrnMsg.xxLinkAncr_ID.setInputProtected(false);
                if (ZYPCommonFunc.hasValue(scrnMsg.xxSrvUrlTxt)) {
                    scrnMsg.xxSrvUrlTxt.setInputProtected(true);
                }
            }
            // START 2016/09/29 W.Honda [Unit Test#201,MOD]
            // Invoice Line
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).ovrdSerNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).startDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).endDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).baseAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).cntrTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).startReadMtrCnt_A1.setInputProtected(true);
                scrnMsg.A.no(i).endReadMtrCnt_A1.setInputProtected(true);
                scrnMsg.A.no(i).readMtrCnt_A1.setInputProtected(true);
                scrnMsg.A.no(i).apTolAmt_A1.setInputProtected(true);
            }
            // Comment only for Administrator
            scrnMsg.invCmntTxt_CO.setInputProtected(false);
            scrnMsg.apAdjAmt_CO.setInputProtected(false);
            scrnMsg.apAdjRsnCd_CO.setInputProtected(false);
        } else {
            // Batch Header
            scrnMsg.apBatNum_BA.setInputProtected(true);
            scrnMsg.apCtrlAmt_BA.setInputProtected(false);
            scrnMsg.apCtrlCnt_BA.setInputProtected(false);
            scrnMsg.apBatDt_BA.setInputProtected(false);
            scrnMsg.apRunTotAmt_BA.setInputProtected(true);
            scrnMsg.apRunTotCnt_BA.setInputProtected(true);
            // Invoice Header
            // START 2016/09/09 K.Kojima [QC#12725,MOD]
            // scrnMsg.apvrUsrNm_IH.setInputProtected(true);
            scrnMsg.varCharConstVal_IH.setInputProtected(true);
            // END 2016/09/09 K.Kojima [QC#12725,MOD]
            scrnMsg.apInvAmt_IH.setInputProtected(false);
            scrnMsg.apDsWfStsNm_IH.setInputProtected(true);
            scrnMsg.prntVndCd_L.setInputProtected(false);
            scrnMsg.prntVndCd_IH.setInputProtected(false);
            scrnMsg.prntVndNm_IH.setInputProtected(true);
            scrnMsg.apMiscAmt_IH.setInputProtected(false);
            scrnMsg.apInvNum_IH.setInputProtected(false);
            scrnMsg.vndSiteNm_L.setInputProtected(false);
            scrnMsg.vndSiteNm_IH.setInputProtected(false);
            scrnMsg.apTaxAmt_IH.setInputProtected(false);
            scrnMsg.invDt_IH.setInputProtected(false);
            scrnMsg.apMaintInvStsNm_IH.setInputProtected(true);
            scrnMsg.lateFeeAmt_IH.setInputProtected(false);
            // Field values for [Add Serial] button
            scrnMsg.xxChkBox_AD.setInputProtected(false);
            // START 2016/09/29 K.Kojima [QC#13442,ADD]
            scrnMsg.serNum_L.setInputProtected(false);
            // END 2016/09/29 K.Kojima [QC#13442,ADD]
            scrnMsg.serNum_AD.setInputProtected(false);
            scrnMsg.ovrdSerNum_AD.setInputProtected(false);
            scrnMsg.startDt_AD.setInputProtected(false);
            scrnMsg.endDt_AD.setInputProtected(false);
            scrnMsg.baseAmt_AD.setInputProtected(false);
            // Invoice Line
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // Mod Start 2016/08/22 QC#12830
                //int remainder = i % INT_6;
                //if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).serNum_A1.setInputProtected(false);
                    scrnMsg.A.no(i).ovrdSerNum_A1.setInputProtected(false);
                    scrnMsg.A.no(i).startDt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).endDt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).baseAmt_A1.setInputProtected(false);
                } else {
                    //scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).ovrdSerNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).startDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).endDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).baseAmt_A1.setInputProtected(true);
                }
                // Mod End 2016/08/22 QC#12830
                scrnMsg.A.no(i).cntrTpCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).startReadMtrCnt_A1.setInputProtected(false);
                scrnMsg.A.no(i).endReadMtrCnt_A1.setInputProtected(false);
                scrnMsg.A.no(i).readMtrCnt_A1.setInputProtected(true);
                scrnMsg.A.no(i).apTolAmt_A1.setInputProtected(false);
            }
            // Comment only for Administrator
            scrnMsg.invCmntTxt_CO.setInputProtected(true);
            scrnMsg.apAdjAmt_CO.setInputProtected(true);
            scrnMsg.apAdjRsnCd_CO.setInputProtected(true);
        }

    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1110BMsg
     */
    public static void setButtonForRef(EZDCommonHandler handler, NFBL1110BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH_SUPPLIER_NAME, false); // [Supplier] Button
        handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, false); // [Add Serial] Button
        handler.setButtonEnabled(BTN_NORMAL_SELECT_ALL, false); // [Select All] Button
        handler.setButtonEnabled(BTN_NORMAL_UNSELECT_ALL, false); // [Unselect All] Button
        handler.setButtonEnabled(BTN_NORMAL_DELETE_SERIAL, false); // [Delete Serial] Button
        //Add Start 2016/08/23 QC#12830
        handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, false); // [Add Counter] Button
        handler.setButtonEnabled(BTN_NORMAL_DELETE_COUNTER, false); // [Delete Counter] Button
        //Add End 2016/08/23 QC#12830
 
        if (ZYPCommonFunc.hasValue(scrnMsg.xxListNum_Y)
        &&  scrnMsg.xxListNum_Y.getValueInt() > 0) {
            handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, true); // [Prev] Button
        } else {
            handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, false); // [Prev] Button
        }
        handler.setButtonEnabled(BTN_NORMAL_NEXT_INVOICE, true); // [Next] Button
        handler.setButtonEnabled(BTN_NORMAL_DELETE_INVOICE, false); // [Delete Invoice] Button
        if (ZYPCommonFunc.hasValue(scrnMsg.apBatNum_BA)) {
            handler.setButtonEnabled(BTN_NORMAL_SUMMARY, true); // [Summary] Button
        } else {
            handler.setButtonEnabled(BTN_NORMAL_SUMMARY, false); // [Summary] Button
        }

        // Common Button
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        // START 2016/08/05 K.Kojima [QC#12690,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        // END 2016/08/05 K.Kojima [QC#12690,MOD]
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        // START 2016/08/05 K.Kojima [QC#12690,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        // END 2016/08/05 K.Kojima [QC#12690,MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        // START 2016/08/05 K.Kojima [QC#12690,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        // END 2016/08/05 K.Kojima [QC#12690,MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * setButtonForMod
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1110BMsg
     */
    public static void setButtonForMod(EZDCommonHandler handler, NFBL1110BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH_SUPPLIER_NAME, true); // [Supplier] Button
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, true); // [Add Serial] Button
            handler.setButtonEnabled(BTN_NORMAL_SELECT_ALL, false); // [Select All] Button
            handler.setButtonEnabled(BTN_NORMAL_UNSELECT_ALL, false); // [Unselect All] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_SERIAL, false); // [Delete Serial] Button
            // Add Start 2016/08/23 QC#12830
            handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, false); // [Add Counter] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_COUNTER, false); // [Delete Counter] Button
            // Add End 2016/08/23 QC#12830
        } else {
            // Mod Start 2016/08/23 QC#12830
            // int maxRowCnt = scrnMsg.A.length() / INT_6 * INT_6;
            int maxRowCnt = scrnMsg.A.length();
            if (scrnMsg.A.getValidCount() == maxRowCnt) {

                handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, false); // [Add Serial] Button
                // Add Start 2016/08/23 QC#12830
                handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, false); // [Add Counter] Button
                // Add End 2016/08/23 QC#12830
            } else {
                handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, true); // [Add Serial] Button
                // Add Start 2016/08/23 QC#12830
                handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, true); // [Add Counter] Button
                // Add End 2016/08/23 QC#12830
            }
            handler.setButtonEnabled(BTN_NORMAL_SELECT_ALL, true); // [Select All] Button
            handler.setButtonEnabled(BTN_NORMAL_UNSELECT_ALL, true); // [Unselect All] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_SERIAL, true); // [Delete Serial] Button
            // Add Start 2016/08/23 QC#12830
            handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, true); // [Add Counter] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_COUNTER, true); // [Delete Counter] Button
            // Add End 2016/08/23 QC#12830
        }
        if (scrnMsg.Y.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, false); // [Prev] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_INVOICE, false); // [Delete Invoice] Button
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxListNum_Y)
            &&  scrnMsg.xxListNum_Y.getValueInt() > 0) {
                handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, true); // [Prev] Button
            } else {
                handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, false); // [Prev] Button
            }
            handler.setButtonEnabled(BTN_NORMAL_DELETE_INVOICE, true); // [Delete Invoice] Button
        }
        handler.setButtonEnabled(BTN_NORMAL_NEXT_INVOICE, true); // [Next] Button
        if (ZYPCommonFunc.hasValue(scrnMsg.apBatNum_BA)) {
            handler.setButtonEnabled(BTN_NORMAL_SUMMARY, true); // [Summary] Button
        } else {
            handler.setButtonEnabled(BTN_NORMAL_SUMMARY, false); // [Summary] Button
        }

        // Common Button
        if (scrnMsg.A.getValidCount() == 0
        &&  scrnMsg.Y.getValidCount() == 0) {
            // For deleting invoice.
            if (scrnMsg.xxInvDelFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            }
        } else {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        }
        // START 2016/08/05 K.Kojima [QC#12690,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        // END 2016/08/05 K.Kojima [QC#12690,MOD]
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        // START 2016/08/05 K.Kojima [QC#12690,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        // END 2016/08/05 K.Kojima [QC#12690,MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        // START 2016/08/05 K.Kojima [QC#12690,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        // END 2016/08/05 K.Kojima [QC#12690,MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }
    
    /**
     * setButtonForAdmin
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1110BMsg
     */
    public static void setButtonForAdmin(EZDCommonHandler handler, NFBL1110BMsg scrnMsg) {

        if (scrnMsg.apMaintInvStsCd_IH.getValue().equals(AP_MAINT_INV_STS_CD_11)) {
            // Header
            handler.setButtonEnabled(BTN_NORMAL_SEARCH_SUPPLIER_NAME, false); // [Supplier] Button
            handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, false); // [Add Serial] Button
            handler.setButtonEnabled(BTN_NORMAL_SELECT_ALL, false); // [Select All] Button
            handler.setButtonEnabled(BTN_NORMAL_UNSELECT_ALL, false); // [Unselect All] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_SERIAL, false); // [Delete Serial] Button
            // Add Start 2016/08/23 QC#12830
            handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, false); // [Add Counter] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_COUNTER, false); // [Delete Counter] Button
            // Add End 2016/08/23 QC#12830
            if (ZYPCommonFunc.hasValue(scrnMsg.xxListNum_Y)
            &&  scrnMsg.xxListNum_Y.getValueInt() > 0) {
                handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, true); // [Prev] Button
            } else {
                handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, false); // [Prev] Button
            }
            handler.setButtonEnabled(BTN_NORMAL_NEXT_INVOICE, true); // [Next] Button
            handler.setButtonEnabled(BTN_NORMAL_DELETE_INVOICE, false); // [Delete Invoice] Button
            if (ZYPCommonFunc.hasValue(scrnMsg.apBatNum_BA)) {
                handler.setButtonEnabled(BTN_NORMAL_SUMMARY, true); // [Summary] Button
            } else {
                handler.setButtonEnabled(BTN_NORMAL_SUMMARY, false); // [Summary] Button
            }

            // Common Button
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            // START 2016/08/05 K.Kojima [QC#12690,MOD]
            // handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            // END 2016/08/05 K.Kojima [QC#12690,MOD]
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 1, null);
            handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 1, null);
            // START 2016/08/05 K.Kojima [QC#12690,MOD]
            // handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
            // handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            // END 2016/08/05 K.Kojima [QC#12690,MOD]
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            // START 2016/08/05 K.Kojima [QC#12690,MOD]
            // handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            // END 2016/08/05 K.Kojima [QC#12690,MOD]
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            // Header
            handler.setButtonEnabled(BTN_NORMAL_SEARCH_SUPPLIER_NAME, true); // [Supplier] Button
            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, true); // [Add Serial] Button
                handler.setButtonEnabled(BTN_NORMAL_SELECT_ALL, false); // [Select All] Button
                handler.setButtonEnabled(BTN_NORMAL_UNSELECT_ALL, false); // [Unselect All] Button
                handler.setButtonEnabled(BTN_NORMAL_DELETE_SERIAL, false); // [Delete Serial] Button
                // Add Start 2016/08/23 QC#12830
                handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, false); // [Add Counter] Button
                handler.setButtonEnabled(BTN_NORMAL_DELETE_COUNTER, false); // [Delete Counter] Button
                // Add End 2016/08/23 QC#12830
            } else {
                // Mod Start 2016/08/23 QC#12830
                // int maxRowCnt = scrnMsg.A.length() / INT_6 * INT_6;
                int maxRowCnt = scrnMsg.A.length();
                // Mod End 2016/08/23 QC#12830
                if (scrnMsg.A.getValidCount() == maxRowCnt) {
                    handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, false); // [Add Serial] Button
                    // Add Start 2016/08/23 QC#12830
                    handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, false); // [Add Counter] Button
                    // Add End 2016/08/23 QC#12830
                } else {
                    handler.setButtonEnabled(BTN_NORMAL_ADD_SERIAL, true); // [Add Serial] Button
                    // Add Start 2016/08/23 QC#12830
                    handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, true); // [Add Counter] Button
                    // Add End 2016/08/23 QC#12830
                }
                handler.setButtonEnabled(BTN_NORMAL_SELECT_ALL, true); // [Select All] Button
                handler.setButtonEnabled(BTN_NORMAL_UNSELECT_ALL, true); // [Unselect All] Button
                handler.setButtonEnabled(BTN_NORMAL_DELETE_SERIAL, true); // [Delete Serial] Button
                // Add Start 2016/08/23 QC#12830
                handler.setButtonEnabled(BTN_NORMAL_ADD_COUNTER, true); // [Add Counter] Button
                handler.setButtonEnabled(BTN_NORMAL_DELETE_COUNTER, true); // [Delete Counter] Button
                // Add End 2016/08/23 QC#12830
            }
            if (scrnMsg.Y.getValidCount() == 0) {
                handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, false); // [Prev] Button
                handler.setButtonEnabled(BTN_NORMAL_DELETE_INVOICE, false); // [Delete Invoice] Button
            } else {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxListNum_Y)
                &&  scrnMsg.xxListNum_Y.getValueInt() > 0) {
                    handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, true); // [Prev] Button
                } else {
                    handler.setButtonEnabled(BTN_NORMAL_PREV_INVOICE, false); // [Prev] Button
                }
                handler.setButtonEnabled(BTN_NORMAL_DELETE_INVOICE, true); // [Delete Invoice] Button
            }
            handler.setButtonEnabled(BTN_NORMAL_NEXT_INVOICE, true); // [Next] Button
            if (ZYPCommonFunc.hasValue(scrnMsg.apBatNum_BA)) {
                handler.setButtonEnabled(BTN_NORMAL_SUMMARY, true); // [Summary] Button
            } else {
                handler.setButtonEnabled(BTN_NORMAL_SUMMARY, false); // [Summary] Button
            }

            // Common Button
            if (scrnMsg.A.getValidCount() == 0
            &&  scrnMsg.Y.getValidCount() == 0) {
                // For deleting invoice.
                if (scrnMsg.xxInvDelFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
                    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
                } else {
                    handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
                    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
                }
            } else {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            }
            // START 2016/08/05 K.Kojima [QC#12690,MOD]
            // handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            // END 2016/08/05 K.Kojima [QC#12690,MOD]
            if (scrnMsg.A.getValidCount() == 0
            &&  scrnMsg.Y.getValidCount() == 0) {
                handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
                handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            } else {
                if (scrnMsg.apMaintInvStsCd_IH.getValue().equals(AP_MAINT_INV_STS_CD_11)) {
                    handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 1, null);
                    handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 1, null);
                } else {
                    handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
                    handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
                }
            }
            // START 2016/08/05 K.Kojima [QC#12690,MOD]
            // handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
            // handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            // END 2016/08/05 K.Kojima [QC#12690,MOD]
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            // START 2016/08/05 K.Kojima [QC#12690,MOD]
            // handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            // END 2016/08/05 K.Kojima [QC#12690,MOD]
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
    }

    /**
     * Method name: clearRowsBG_A
     * <dd>The method explanation: Clear alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void clearRowsBG_A(EZDBMsg bMsg) {
        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
    }

    /**
     * Method name: setAlternateRowsBG_A
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBG_A(EZDBMsg bMsg) {
        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
    }

    /**
     * Method name: setAlternateRowsBGCommon
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBGCommon(EZDBMsg bMsg) {
        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        clearRowsBG_A(scrnMsg);
        setAlternateRowsBG_A(scrnMsg);
    }

    /**
     * Method name: setButtonConfirmMsg
     * <dd>The method explanation: Set Button Confirm Message.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static void setButtonConfirmMsg(EZDCommonHandler handler) {
        // Button Confirm Message Settings
        handler.setButtonConfirmMsg(BTN_CMN_SAVE[1], ZZM8101I, new String[] {BTN_CMN_SAVE[2] }, 1);
        handler.setButtonConfirmMsg(BTN_CMN_SUBMIT[1], ZZM8101I, new String[] {BTN_CMN_SUBMIT[2] }, 1);
        handler.setButtonConfirmMsg(BTN_CMN_APPROVE[1], ZZM8101I, new String[] {BTN_CMN_APPROVE[2] }, 1);
        handler.setButtonConfirmMsg(BTN_CMN_REJECT[1], ZZM8101I, new String[] {BTN_CMN_REJECT[2] }, 1);
        handler.setButtonConfirmMsg(BTN_CMN_CLEAR[1], ZZM8101I, new String[] {BTN_CMN_CLEAR[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], ZZM8101I, new String[] {BTN_CMN_RETURN[2] }, 0);
    }

    /**
     * Method name: initAppFracDigit
     * <dd>The method explanation: Init App Frac Digit.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initAppFracDigit(EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        scrnMsg.apCtrlAmt_BA.setAppFracDigit(FRAC_DIGIT_2);
        scrnMsg.apRunTotAmt_BA.setAppFracDigit(FRAC_DIGIT_2);

        scrnMsg.apInvAmt_IH.setAppFracDigit(FRAC_DIGIT_2);
        scrnMsg.apMiscAmt_IH.setAppFracDigit(FRAC_DIGIT_2);
        scrnMsg.apTaxAmt_IH.setAppFracDigit(FRAC_DIGIT_2);
        scrnMsg.lateFeeAmt_IH.setAppFracDigit(FRAC_DIGIT_2);

        scrnMsg.baseAmt_AD.setAppFracDigit(FRAC_DIGIT_2);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).baseAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.A.no(i).apTolAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
        }

        scrnMsg.apAdjAmt_CO.setAppFracDigit(FRAC_DIGIT_2);

    }
    // START 2017/01/20 E.Kameishi [QC#17156,ADD]
    /**
     * Get Param NWAL1130 For Supplier
     * @param scrnMsg NFBL1110BMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @return Param NWAL1130 For Supplier
     */
    public static Object[] getParamNWAL1130ForSupplier(NFBL1110BMsg scrnMsg, String glblCmpyCd, String salesDate) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Supplier/Supplier Site Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("    V.EZCANCELFLAG,");
        sb.append("    V.GLBL_CMPY_CD,");
        sb.append("    V.VND_CD,");
        sb.append("    V.LOC_NM,");
        sb.append("    V.INAC_DT,");
        sb.append("    PV.PRNT_VND_CD,");
        sb.append("    PV.PRNT_VND_NM,");
        sb.append("    PV.PRNT_VND_TP_CD");
        sb.append(" FROM");
        sb.append("    PRNT_VND PV,");
        sb.append("    VND      V,");
        sb.append("    VND_TP_RELN VTR");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        sb.append("  AND V.RGTN_STS_CD  = '");
        sb.append(RGTN_STS.READY_FOR_ORDER_TAKING);
        sb.append("'");
        sb.append("  AND V.EFF_THRU_DT  >= '").append(salesDate).append("'");
        sb.append("  AND V.VND_CD        = VTR.VND_CD");
        sb.append("  AND V.GLBL_CMPY_CD  = VTR.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG  = VTR.EZCANCELFLAG");
        sb.append("  AND VTR.VND_TP_CD   = '");
        sb.append(VND_TP.SUPPLIER);
        sb.append("'");
        sb.append("  AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append("  AND V.PRNT_VND_PK  = PV.PRNT_VND_PK");
        sb.append("  AND (PV.INAC_DT IS NULL OR PV.INAC_DT > '").append(salesDate).append("')");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        // START 2017/12/25 [QC#22831, MOD]
        whereArray1[IDX_0] = "Supplier Number";
        // START 2017/12/25 [QC#22831, MOD]
        whereArray1[IDX_1] = "PRNT_VND_CD";
        whereArray1[IDX_2] = scrnMsg.prntVndCd_IH.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Supplier Name";
        whereArray2[IDX_1] = "PRNT_VND_NM";
        whereArray2[IDX_2] = scrnMsg.prntVndNm_IH.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray3 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        // whereArray3[IDX_0] = "Site Code";
        whereArray3[IDX_0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray3[IDX_1] = "VND_CD";
        whereArray3[IDX_2] = scrnMsg.apVndCd_HD.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray4 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        // whereArray4[IDX_0] = "Site Name";
        whereArray4[IDX_0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray4[IDX_1] = "LOC_NM";
        whereArray4[IDX_2] = scrnMsg.vndSiteNm_IH.getValue();
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        whereList.add(whereArray4);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        // START 2017/12/25 [QC#22831, MOD]
        columnArray1[IDX_0] = "Supplier Number";
        // END   2017/12/25 [QC#22831, MOD]
        columnArray1[IDX_1] = "PRNT_VND_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Supplier Name";
        columnArray2[IDX_1] = "PRNT_VND_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        // columnArray3[IDX_0] = "Site Code";
        columnArray3[IDX_0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray3[IDX_1] = "VND_CD";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray4 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        // columnArray4[IDX_0] = "Site Name";
        columnArray4[IDX_0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray4[IDX_1] = "LOC_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRNT_VND_CD";
        sortConditionArray1[IDX_1] = "ASC";
        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "VND_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        sortList.add(sortConditionArray2);
        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // END 2017/01/20 E.Kameishi [QC#17156,ADD]

}
