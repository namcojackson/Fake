/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060.common;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import parts.common.EZDBMsgArray;
import business.servlet.NLEL0060.NLEL0060BMsg;
import business.servlet.NLEL0060.NLEL0060_ABMsg;
import business.servlet.NLEL0060.NLEL0060_ABMsgArray;
import business.servlet.NLEL0060.NLEL0060_BBMsg;
import business.servlet.NLEL0060.NLEL0060_CBMsg;
import business.servlet.NLEL0060.NLEL0060_DBMsg;
import business.servlet.NLEL0060.NLEL0060_EBMsg;
import business.servlet.NLEL0060.NLEL0060_FBMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLEL0060CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/11   Hitachi         J.Kim           Update          QC#6581
 * 2016/04/22   Hitachi         J.Kim           Update          QC#6770
 * 2016/04/21   Hitachi         J.Kim           Update          QC#7113
 * 2016/04/27   Hitachi         J.Kim           Update          QC#7461
 * 2016/06/14   Hitachi         T.Tsuchida      Update          QC#9757
 * 2016/06/20   Hitachi         J.Kim           Update          QC#10294
 * 2016/06/23   Hitachi         K.Kojima        Update          QC#10563
 * 2016/06/30   Hitachi         J.Kim           Update          QC#10877
 * 2016/07/21   Hitachi         Y.Tsuchimoto    Update          QC#11019
 * 2016/08/08   Fujitsu         C.Tanaka        Update          QC#11135
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#13360
 * 2016/09/26   Fujitsu         C.Tanaka        Update          QC#12697, QC#11899
 * 2016/09/27   Hitachi         J.Kim           Update          QC#13372
 * 2016/10/17   Hitachi         J.Kim           Update          QC#13453
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 2017/11/01   Hitachi         J.Kim           Update          QC#16345
 * 2018/02/26   Hitachi         J.Kim           Update          QC#21965
 * 2018/03/06   Hitachi         J.Kim           Update          QC#24601
 * 2018/05/17   Hitachi         J.Kim           Update          QC#25879
 * 2018/06/20   Hitachi         J.Kim           Update          QC#26044
 * 2018/08/09   CITS            S.Katsuma       Update          QC#22812
 * 2018/08/17   CITS            Y.Iwasaki       Update          QC#24426
 * 2018/08/28   Fujitsu         S.Ohki          Update          QC#28000
 *</pre>
 */
public class NLEL0060CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

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
     * @param scrnMsg NLEL0060BMsg
     * @param bMsgArray EZDBMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NLEL0060BMsg scrnMsg, EZDBMsgArray bMsgArray, String tblId) {
        setRowsBGWithClear(scrnMsg, bMsgArray, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NLEL0060BMsg
     * @param bMsgArray EZDBMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NLEL0060BMsg scrnMsg, EZDBMsgArray bMsgArray, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        // START 2016/10/07 J.Kim [QC#5521,ADD]
        // tblColor.clearRowsBG("A_Right", bMsgArray);
        // tblColor.clearRowsBG("B_Right", bMsgArray);
        // tblColor.clearRowsBG("C_Right", bMsgArray);
        // tblColor.clearRowsBG("D_Right", bMsgArray);
        // tblColor.clearRowsBG("E_Right", bMsgArray);
        // tblColor.clearRowsBG("F_Right", bMsgArray);
        // tblColor.clearRowsBG("A_Left", bMsgArray);
        // tblColor.clearRowsBG("B_Left", bMsgArray);
        // tblColor.clearRowsBG("C_Left", bMsgArray);
        // tblColor.clearRowsBG("D_Left", bMsgArray);
        // Color on
        // String right = tblId + "_Right";
        // String left = tblId + "_Left";
        // if (bMsgArray.getValidCount() > 0) {
        // tblColor.setAlternateRowsBG(right, bMsgArray, grpRows);
        // if (!tblId.equals("E") && !tblId.equals("F")) {
        // tblColor.setAlternateRowsBG(left, bMsgArray, grpRows);
        // }
        // }
        // END 2016/10/07 J.Kim [QC#5521,ADD]

        tblColor.clearRowsBG("A", bMsgArray);
        tblColor.clearRowsBG("B", bMsgArray);
        tblColor.clearRowsBG("C", bMsgArray);
        tblColor.clearRowsBG("D", bMsgArray);
        tblColor.clearRowsBG("E", bMsgArray);
        tblColor.clearRowsBG("F", bMsgArray);
        if (bMsgArray.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(tblId, bMsgArray, grpRows);
        }
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NLEL0060BMsg
     * @param scrnAMsgAry NLEL0060_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NLEL0060BMsg scrnMsg, NLEL0060_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * hasUpdateFuncId
     * @param scrnMsg NLEL0060BMsg
     * @return boolean
     */
    public static boolean hasUpdateFuncId(NLEL0060BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
            // START 2016/06/14 T.Tsuchida [QC#9757,MOD]
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxModeInd_P)) {
            return false;
            // END 2016/06/14 T.Tsuchida [QC#9757,MOD]
        } else if (funcList.contains(PRIV_UPD)) {
            return true;
        }

        return false;
    }

    /**
     * Set Table Row Color for each tab
     * @param scrnMsg NLEL0060BMsg
     */
    public static void setTableBGColor(NLEL0060BMsg scrnMsg) {

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            NLEL0060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        } else if (TAB_ASG.equals(tab)) {
            NLEL0060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        } else if (TAB_FIN.equals(tab)) {
            NLEL0060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        } else if (TAB_TRX.equals(tab)) {
            NLEL0060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.D, "D");
        } else if (TAB_DEP_SIM.equals(tab)) {
            NLEL0060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.E, "E");
            // START 2016/09/27 J.Kim [QC#13372,ADD]
        } else if (TAB_ASSET_HIST.equals(tab)) {
            NLEL0060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.F, "F");
        }
        // END 2016/09/27 J.Kim [QC#13372,ADD]
    }

    /**
     * Set initial input protected
     * @param handler S21CommonHandler
     * @param scrnMsg NLEL0060BMsg
     */
    public static void initFieldProp(S21CommonHandler handler, NLEL0060BMsg scrnMsg) {
        scrnMsg.rtlWhNm_H1.setInputProtected(true);
        scrnMsg.vndNm_H1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);

        NLEL0060_ABMsg aBMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            aBMsg = scrnMsg.A.no(i);
            aBMsg.dsAssetMstrPk_A1.setInputProtected(true);
            aBMsg.assetTpDescTxt_A1.setInputProtected(true);
            aBMsg.assetStsDescTxt_A1.setInputProtected(true);
            aBMsg.depcStartDt_A1.setInputProtected(true);
            aBMsg.svcConfigMstrPk_A1.setInputProtected(true);
            aBMsg.serNum_A1.setInputProtected(true);
            aBMsg.mdseCd_A1.setInputProtected(true);
            aBMsg.coaMdseTpCd_A1.setInputProtected(true);
            aBMsg.curValAmt_A1.setInputProtected(true);
            aBMsg.prntDsAssetMstrPk_A1.setInputProtected(true);
            aBMsg.dsAcctNm_A1.setInputProtected(true);
            aBMsg.cpoOrdNum_A1.setInputProtected(true);
            aBMsg.dplyLineNum_A1.setInputProtected(true);
            // START 2018/06/19 J.Kim [QC#24844,MOD]
            // aBMsg.poOrdNum_A1.setInputProtected(true);
            aBMsg.custIssPoNum_A1.setInputProtected(true);
            // END 2018/06/19 J.Kim [QC#24844,MOD]
            aBMsg.invNum_A1.setInputProtected(true);
            aBMsg.vndCd_A1.setInputProtected(true);
            aBMsg.prntVndNm_A1.setInputProtected(true);
            aBMsg.svcInvNum_A1.setInputProtected(true);
            aBMsg.invDt_A1.setInputProtected(true);
            aBMsg.lastDepcYrMth_A1.setInputProtected(true);
            aBMsg.dsContrNum_A1.setInputProtected(true);
            aBMsg.contrEffFromDt_A1.setInputProtected(true);
            aBMsg.contrEffThruDt_A1.setInputProtected(true);
            aBMsg.dsAssetDescTxt_A1.setInputProtected(true);
            aBMsg.depcMthNum_A1.setInputProtected(true);
            aBMsg.totAssetQty_A1.setInputProtected(true);
            aBMsg.assetTagNum_A1.setInputProtected(true);
            aBMsg.dsAssetGrpInitBookAmt_A1.setInputProtected(true);
            // START 2018/07/25 J.Kim [QC#24950,ADD]
            aBMsg.dtlCmntTxt_A1.setInputProtected(true);
            // END 2018/07/125 J.Kim [QC#24950,ADD]
            // START 2018/08/1 J.Kim [QC#26901,ADD]
            aBMsg.amtChngRsnTpCd_A1.setInputProtected(true);
            // END 2018/08/1 J.Kim [QC#26901,ADD]
            // START 2018/08/09 S.Katsuma [QC#22812,ADD]
            aBMsg.coaProdCd_A1.setInputProtected(true);
            // END 2018/08/09 S.Katsuma [QC#22812,ADD]
        }

        NLEL0060_BBMsg bBMsg;
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            bBMsg = scrnMsg.B.no(i);
            bBMsg.prntDsAssetMstrPk_B1.setInputProtected(true);
            bBMsg.assetTpDescTxt_B1.setInputProtected(true);
            // START 2016/11/01 J.Kim [QC#16345,MOD]
            // bBMsg.xxScrItem50Txt_B1.setInputProtected(true);
            bBMsg.coaAcctCd_B.setInputProtected(true);
            bBMsg.coaBrCd_B.setInputProtected(true);
            bBMsg.coaCcCd_B.setInputProtected(true);
            bBMsg.coaExtnCd_B.setInputProtected(true);
            // END 2016/11/01 J.Kim [QC#16345,MOD]
            bBMsg.xxScrItem50Txt_B2.setInputProtected(true);
            bBMsg.xxScrItem10Txt_B1.setInputProtected(true);
            bBMsg.xxAllLineAddr_B1.setInputProtected(true);
            bBMsg.ctyAddr_B1.setInputProtected(true);
            bBMsg.stCd_B1.setInputProtected(true);
            bBMsg.postCd_B1.setInputProtected(true);
            bBMsg.asgDtlCmntTxt_B1.setInputProtected(true);
            handler.setButtonEnabled(OPENWIN_ASSET_ACCT_GL, i, false);
            handler.setButtonEnabled(OPENWIN_EXP_ACCT_GL, i, false);
            // START 2017/05/16 M.Naito
            handler.setButtonEnabled(OPENWIN_RTRN_WH_CD, i, false);
            // END 2017/05/16 M.Naito
            // START 2018/05/17 J.Kim [QC#25879,ADD]
            bBMsg.psnNum_B.setInputProtected(true); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
            handler.setButtonEnabled(OPENWIN_SR, i, false);
            // END 2018/05/17 J.Kim [QC#25879,ADD]
        }

        NLEL0060_CBMsg cBMsg;
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            cBMsg = scrnMsg.C.no(i);
            cBMsg.dsAssetMstrPk_C1.setInputProtected(true);
            cBMsg.prntDsAssetMstrPk_C1.setInputProtected(true);
            cBMsg.assetTpDescTxt_C1.setInputProtected(true);
            cBMsg.assetStsDescTxt_C1.setInputProtected(true);
            cBMsg.curValAmt_C1.setInputProtected(true);
            // START 2016/04/11 J.Kim [QC#6581,MOD]
            // cBMsg.xxScrItem20Txt_C1.setInputProtected(true);
            cBMsg.xxRteActlFrtAmt_C1.setInputProtected(true);
            // END 2016/04/11 J.Kim [QC#6581,MOD]
            // START 2016/07/21 Y.Tsuchimoto [QC#11019,MOD]
            cBMsg.xxTotPrcAmt_C1.setInputProtected(true);
            // END 2016/07/21 Y.Tsuchimoto [QC#11019,MOD]
            cBMsg.lastDepcYrMth_C1.setInputProtected(true);
            cBMsg.dsAssetGrpInitBookAmt_C1.setInputProtected(true);
            cBMsg.curValAmt_C2.setInputProtected(true);
            cBMsg.finDtlCmntTxt_C1.setInputProtected(true);
        }

        NLEL0060_DBMsg dBMsg;
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            dBMsg = scrnMsg.D.no(i);
            dBMsg.prntDsAssetMstrPk_D1.setInputProtected(true);
            dBMsg.assetTpDescTxt_D1.setInputProtected(true);
            // START 2016/06/23 K.Kojima [QC#10563,MOD]
            // dBMsg.trxNm_D1.setInputProtected(true);
            dBMsg.trxRsnNm_D1.setInputProtected(true);
            // END 2016/06/23 K.Kojima [QC#10563,MOD]
            dBMsg.assetTrxDt_D1.setInputProtected(true);
            dBMsg.depcValAmt_D1.setInputProtected(true);
            dBMsg.coaAcctNm_D1.setInputProtected(true);
            dBMsg.xxScrItem50Txt_D1.setInputProtected(true);
            dBMsg.coaAcctNm_D2.setInputProtected(true);
            dBMsg.xxScrItem50Txt_D2.setInputProtected(true);
            dBMsg.finDtlCmntTxt_D1.setInputProtected(true);
            dBMsg.depcMthNum_D1.setInputProtected(true);
        }

        NLEL0060_EBMsg eBMsg;
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            eBMsg = scrnMsg.E.no(i);
            eBMsg.prntDsAssetMstrPk_E1.setInputProtected(true);
            eBMsg.assetTpNm_E1.setInputProtected(true);
            eBMsg.assetTrxDt_E1.setInputProtected(true);
            eBMsg.depcValAmt_E1.setInputProtected(true);
            eBMsg.drCoaAcctCd_E1.setInputProtected(true);
            eBMsg.depcValAmt_E2.setInputProtected(true);
            eBMsg.crCoaAcctCd_E1.setInputProtected(true);
        }

        // START 2016/09/27 J.Kim [QC#13372,ADD]
        NLEL0060_FBMsg fBMsg;
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            fBMsg = scrnMsg.F.no(i);
            fBMsg.dsAssetMstrPk_F1.setInputProtected(true);
            // START 2018/07/25 J.Kim [QC#24950,ADD]
            fBMsg.prntDsAssetMstrPk_F1.setInputProtected(true);
            fBMsg.procModeNm_F1.setInputProtected(true);
            // END 2018/07/125 J.Kim [QC#24950,ADD]
            fBMsg.xxDtTm_F1.setInputProtected(true);
            fBMsg.updFldTxt_F1.setInputProtected(true);
            fBMsg.oldValTxt_F1.setInputProtected(true);
            fBMsg.newValTxt_F1.setInputProtected(true);
            fBMsg.updUsrId_F1.setInputProtected(true);
        }
        // END 2016/09/27 J.Kim [QC#13372,ADD]
    }

    /**
     * Control input protected for tab
     * @param handler S21CommonHandler
     * @param scrnMsg NLEL0060BMsg
     */
    public static void ctrlFieldProp(S21CommonHandler handler, NLEL0060BMsg scrnMsg) {

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            NLEL0060CommonLogic.ctrlFieldPropForPending(handler, scrnMsg);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_AA.getValue())) {
                NLEL0060CommonLogic.ctrlFieldPropForAdjust(handler, scrnMsg);
            }
        } else if (TAB_ASG.equals(tab)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_BA.getValue())) {
                NLEL0060CommonLogic.ctrlFieldPropForAdjust(handler, scrnMsg);
            }
            // QC#11135 ADD Start
            NLEL0060_BBMsg bBMsg;
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                bBMsg = scrnMsg.B.no(i);
                if (ASSET_STS.PENDING.equals(bBMsg.assetStsCd_B1.getValue()) || ASSET_STS.ACTIVATE.equals(bBMsg.assetStsCd_B1.getValue())) {
                    bBMsg.xxChkBox_B1.setInputProtected(false);
                } else {
                    bBMsg.xxChkBox_B1.setInputProtected(true);
                }
            }
            // QC#11135 ADD End
        } else if (TAB_FIN.equals(tab)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_CA.getValue())) {
                NLEL0060CommonLogic.ctrlFieldPropForAdjust(handler, scrnMsg);
            } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_CM.getValue())) {
                NLEL0060CommonLogic.ctrlFieldPropForMerge(scrnMsg);
            }
            // QC#11135 ADD Start
            NLEL0060_CBMsg cBMsg;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                cBMsg = scrnMsg.C.no(i);
                if (ASSET_STS.PENDING.equals(cBMsg.assetStsCd_C1.getValue()) || ASSET_STS.ACTIVATE.equals(cBMsg.assetStsCd_C1.getValue())) {
                    cBMsg.xxChkBox_C1.setInputProtected(false);
                } else {
                    cBMsg.xxChkBox_C1.setInputProtected(true);
                }
            }
            // QC#11135 ADD End
            // START 2018/04/06 J.Kim [QC#24561,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.acctYrMth_T1)) {
                handler.setButtonEnabled("Retire", true);
                scrnMsg.assetRtireRsnCmntTxt_T1.setInputProtected(false);
            } else {
                handler.setButtonEnabled("Retire", false);
                scrnMsg.assetRtireRsnCmntTxt_T1.setInputProtected(true);
            }
            // END 2018/04/06 J.Kim [QC#24561,ADD]
        }
    }

    /**
     * Control input protected for pending Asset
     * @param handler S21CommonHandler
     * @param scrnMsg NLEL0060BMsg
     */
    public static void ctrlFieldPropForPending(S21CommonHandler handler, NLEL0060BMsg scrnMsg) {

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            // START 2016/04/21 J.Kim [QC#7113,DEL]
            // scrnMsg.xxBtnFlg_AA.clear();
            // END 2016/04/21 J.Kim [QC#7113,DEL]

            NLEL0060_ABMsg aBMsg;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                aBMsg = scrnMsg.A.no(i);
                if (ASSET_STS.PENDING.equals(aBMsg.assetStsCd_A1.getValue())) {
                    aBMsg.assetTagNum_A1.setInputProtected(false);
                    aBMsg.dsAssetDescTxt_A1.setInputProtected(false);
                    aBMsg.depcMthNum_A1.setInputProtected(false);
                    aBMsg.totAssetQty_A1.setInputProtected(false);
                    // START 2016/06/20 J.Kim [QC#10294,ADD]
                    aBMsg.dsAssetGrpInitBookAmt_A1.setInputProtected(false);
                    aBMsg.dsContrNum_A1.setInputProtected(false);
                    aBMsg.contrEffFromDt_A1.setInputProtected(false);
                    aBMsg.contrEffThruDt_A1.setInputProtected(false);
                    // END 2016/06/20 J.Kim [QC#10294,ADD]
                    aBMsg.depcStartDt_A1.setInputProtected(false);
                    aBMsg.coaMdseTpCd_A1.setInputProtected(false);
                    aBMsg.cpoOrdNum_A1.setInputProtected(false);
                    aBMsg.dplyLineNum_A1.setInputProtected(false);
                    // START 2018/06/19 J.Kim [QC#24844,MOD]
                    // aBMsg.poOrdNum_A1.setInputProtected(false);
                    aBMsg.custIssPoNum_A1.setInputProtected(false);
                    // END 2018/06/19 J.Kim [QC#24844,MOD]
                    aBMsg.invNum_A1.setInputProtected(false);
                    aBMsg.vndCd_A1.setInputProtected(false);
                    // START 2018/07/25 J.Kim [QC#24950,ADD]
                    aBMsg.dtlCmntTxt_A1.setInputProtected(false);
                    // END 2018/07/125 J.Kim [QC#24950,ADD]
                    // START 2018/08/1 J.Kim [QC#26901,ADD]
                    aBMsg.amtChngRsnTpCd_A1.setInputProtected(false);
                    // END 2018/08/1 J.Kim [QC#26901,ADD]
                    handler.setButtonEnabled(OPENWIN_VND_CODE, i, true);
                    handler.setButtonEnabled(OPENWIN_COA_MDSE_TP_CD, i, true);
                    // START 2018/06/20 J.Kim [QC#26044,ADD]
                    handler.setButtonEnabled(OPENWIN_DS_ACCT_NM, i, false);
                    // END 2018/06/20 J.Kim [QC#26044,ADD]
                    if (!ZYPCommonFunc.hasValue(aBMsg.svcMachMstrPk_A1)) {
                        aBMsg.serNum_A1.setInputProtected(false);
                        if (ZYPConstant.FLG_ON_Y.equals(aBMsg.manEntryFlg_A1.getValue())) {
                            aBMsg.dsAcctNm_A1.setInputProtected(false);
                            handler.setButtonEnabled(OPENWIN_DS_ACCT_NM, i, true);
                        } else {
                            aBMsg.dsAcctNm_A1.setInputProtected(true);
                            handler.setButtonEnabled(OPENWIN_DS_ACCT_NM, i, false);
                        }
                    } else {
                        aBMsg.serNum_A1.setInputProtected(true);
                    }
                } else {
                    aBMsg.assetTagNum_A1.setInputProtected(true);
                    aBMsg.dsAssetDescTxt_A1.setInputProtected(true);
                    aBMsg.depcMthNum_A1.setInputProtected(true);
                    aBMsg.totAssetQty_A1.setInputProtected(true);
                    // START 2016/06/20 J.Kim [QC#10294,ADD]
                    aBMsg.dsAssetGrpInitBookAmt_A1.setInputProtected(true);
                    aBMsg.dsContrNum_A1.setInputProtected(true);
                    aBMsg.contrEffFromDt_A1.setInputProtected(true);
                    aBMsg.contrEffThruDt_A1.setInputProtected(true);
                    // END 2016/06/20 J.Kim [QC#10294,ADD]
                    aBMsg.depcStartDt_A1.setInputProtected(true);
                    aBMsg.serNum_A1.setInputProtected(true);
                    aBMsg.coaMdseTpCd_A1.setInputProtected(true);
                    aBMsg.dsAcctNm_A1.setInputProtected(true);
                    aBMsg.cpoOrdNum_A1.setInputProtected(true);
                    aBMsg.dplyLineNum_A1.setInputProtected(true);
                    // START 2018/06/19 J.Kim [QC#24844,MOD]
                    // aBMsg.poOrdNum_A1.setInputProtected(true);
                    aBMsg.custIssPoNum_A1.setInputProtected(true);
                    // END 2018/06/19 J.Kim [QC#24844,MOD]
                    aBMsg.invNum_A1.setInputProtected(true);
                    aBMsg.vndCd_A1.setInputProtected(true);
                    // START 2018/07/25 J.Kim [QC#24950,ADD]
                    aBMsg.dtlCmntTxt_A1.setInputProtected(true);
                    // END 2018/07/125 J.Kim [QC#24950,ADD]
                    // START 2018/08/1 J.Kim [QC#26901,ADD]
                    aBMsg.amtChngRsnTpCd_A1.setInputProtected(true);
                    // END 2018/08/1 J.Kim [QC#26901,ADD]
                    handler.setButtonEnabled(OPENWIN_VND_CODE, i, false);
                    handler.setButtonEnabled(OPENWIN_COA_MDSE_TP_CD, i, false);
                    handler.setButtonEnabled(OPENWIN_DS_ACCT_NM, i, false);
                }
                // START 2016/06/20 J.Kim [QC#10294,DEL]
                // aBMsg.assetLeaseNum_A1.setInputProtected(true);
                // aBMsg.leaseStartDt_A1.setInputProtected(true);
                // aBMsg.leaseEndDt_A1.setInputProtected(true);
                // END 2016/06/20 J.Kim [QC#10294,DEL]

                // QC#11135 ADD Start
                if (ASSET_STS.PENDING.equals(aBMsg.assetStsCd_A1.getValue()) || ASSET_STS.ACTIVATE.equals(aBMsg.assetStsCd_A1.getValue())) {
                    aBMsg.xxChkBox_A1.setInputProtected(false);
                } else {
                    aBMsg.xxChkBox_A1.setInputProtected(true);
                }
                // QC#11135 ADD End
            }
        }
    }

    /**
     * Control input protected after Adjust button pressed
     * @param handler S21CommonHandler
     * @param scrnMsg NLEL0060BMsg
     */
    public static void ctrlFieldPropForAdjust(S21CommonHandler handler, NLEL0060BMsg scrnMsg) {

        boolean enableFlg = false;

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            // START 2016/05/11 K.Kojima [QC#7113,DEL]
            // scrnMsg.xxBtnFlg_AA.clear();
            // END 2016/05/11 K.Kojima [QC#7113,DEL]

            NLEL0060_ABMsg aBMsg;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                aBMsg = scrnMsg.A.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(aBMsg.xxChkBox_A1.getValue())) {
                    aBMsg.dsAssetDescTxt_A1.setInputProtected(false);
                    aBMsg.depcMthNum_A1.setInputProtected(false);
                    aBMsg.totAssetQty_A1.setInputProtected(false);
                    aBMsg.dsAssetGrpInitBookAmt_A1.setInputProtected(false);
                    aBMsg.assetTagNum_A1.setInputProtected(false);
                    // START 2018/06/20 J.Kim [QC#26044,ADD]
                    handler.setButtonEnabled(OPENWIN_DS_ACCT_NM, i, false);
                    // END 2018/06/20 J.Kim [QC#26044,ADD]
                    // START 2018/07/25 J.Kim [QC#24950,ADD]
                    aBMsg.dtlCmntTxt_A1.setInputProtected(false);
                    // END 2018/07/125 J.Kim [QC#24950,ADD]
                    // START 2018/08/1 J.Kim [QC#26901,ADD]
                    aBMsg.amtChngRsnTpCd_A1.setInputProtected(false);
                    // END 2018/08/1 J.Kim [QC#26901,ADD]
                    if (!ZYPCommonFunc.hasValue(aBMsg.svcMachMstrPk_A1)) {
                        aBMsg.serNum_A1.setInputProtected(false);
                        if (ZYPConstant.FLG_ON_Y.equals(aBMsg.manEntryFlg_A1.getValue())) {
                            aBMsg.dsAcctNm_A1.setInputProtected(false);
                            handler.setButtonEnabled(OPENWIN_DS_ACCT_NM, i, true);
                        }
                    }
                    enableFlg = true;
                } else {
                    if (!ASSET_STS.PENDING.equals(aBMsg.assetStsCd_A1.getValue())) {
                        aBMsg.dsAssetDescTxt_A1.setInputProtected(true);
                        aBMsg.depcMthNum_A1.setInputProtected(true);
                        aBMsg.totAssetQty_A1.setInputProtected(true);
                        aBMsg.dsAssetGrpInitBookAmt_A1.setInputProtected(true);
                        aBMsg.serNum_A1.setInputProtected(true);
                        aBMsg.assetTagNum_A1.setInputProtected(true);
                        aBMsg.dsAcctNm_A1.setInputProtected(true);
                        // START 2018/07/25 J.Kim [QC#24950,ADD]
                        aBMsg.dtlCmntTxt_A1.setInputProtected(true);
                        // END 2018/07/125 J.Kim [QC#24950,ADD]
                        // START 2018/08/1 J.Kim [QC#26901,ADD]
                        aBMsg.amtChngRsnTpCd_A1.setInputProtected(true);
                        // END 2018/08/1 J.Kim [QC#26901,ADD]
                        handler.setButtonEnabled(OPENWIN_DS_ACCT_NM, i, false);
                    }
                }
            }
            if (enableFlg) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_AA, ZYPConstant.FLG_ON_Y);
            }

        } else if (TAB_ASG.equals(tab)) {
            // START 2016/05/11 K.Kojima [QC#7113,DEL]
            // scrnMsg.xxBtnFlg_BA.clear();
            // END 2016/05/11 K.Kojima [QC#7113,DEL]

            NLEL0060_BBMsg bBMsg;
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                bBMsg = scrnMsg.B.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(bBMsg.xxChkBox_B1.getValue())) {
                    // START 2018/02/26 J.Kim [QC#21965,MOD]
                    if (!ZYPCommonFunc.hasValue(bBMsg.svcMachMstrPk_B1)) {
                        bBMsg.xxAllLineAddr_B1.setInputProtected(false);
                        bBMsg.ctyAddr_B1.setInputProtected(false);
                        bBMsg.stCd_B1.setInputProtected(false);
                        bBMsg.postCd_B1.setInputProtected(false);
                    } else {
                        bBMsg.xxAllLineAddr_B1.setInputProtected(true);
                        bBMsg.ctyAddr_B1.setInputProtected(true);
                        bBMsg.stCd_B1.setInputProtected(true);
                        bBMsg.postCd_B1.setInputProtected(true);
                    }
                    // END 2018/02/26 J.Kim [QC#21965,MOD]
                    if (PROC_MODE_51.equals(bBMsg.procModeCd_B1.getValue())) {
                        bBMsg.psnNum_B.setInputProtected(true); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
                        handler.setButtonEnabled(OPENWIN_SR, i, false);
                    } else {
                        bBMsg.psnNum_B.setInputProtected(false); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
                        handler.setButtonEnabled(OPENWIN_SR, i, true);
                    }
                    bBMsg.asgDtlCmntTxt_B1.setInputProtected(false);
                    handler.setButtonEnabled(OPENWIN_ASSET_ACCT_GL, i, true);
                    // START 2016/11/01 J.Kim [QC#16345,MOD]
                    // handler.setButtonEnabled(OPENWIN_EXP_ACCT_GL, i, true);
                    if (ZYPCommonFunc.hasValue(bBMsg.xxScrItem50Txt_B2) && !ZYPCommonFunc.hasValue(bBMsg.svcMachMstrPk_B1)) {
                        handler.setButtonEnabled(OPENWIN_EXP_ACCT_GL, i, true);
                        // START 2018/03/06 J.Kim [QC#24601,ADD]
                        bBMsg.xxScrItem50Txt_B2.setInputProtected(false);
                        // END 2018/03/06 J.Kim [QC#24601,ADD]
                    } else {
                        handler.setButtonEnabled(OPENWIN_EXP_ACCT_GL, i, false);
                        // START 2018/03/06 J.Kim [QC#24601,ADD]
                        bBMsg.xxScrItem50Txt_B2.setInputProtected(true);
                        // END 2018/03/06 J.Kim [QC#24601,ADD]
                    }
                    // END 2016/11/01 J.Kim [QC#16345,MOD]

                    // START 2017/05/15 M.Naito
                    if (!ZYPCommonFunc.hasValue(bBMsg.svcMachMstrPk_B1) && !ON_SITE.equals(bBMsg.xxScrItem10Txt_B1.getValue())) {
                        bBMsg.xxScrItem10Txt_B1.setInputProtected(false);
                        handler.setButtonEnabled(OPENWIN_RTRN_WH_CD, i, true);
                    }
                    // END 2017/05/15 M.Naito
                    enableFlg = true;
                } else {
                    bBMsg.xxAllLineAddr_B1.setInputProtected(true);
                    bBMsg.ctyAddr_B1.setInputProtected(true);
                    bBMsg.stCd_B1.setInputProtected(true);
                    bBMsg.postCd_B1.setInputProtected(true);
                    bBMsg.asgDtlCmntTxt_B1.setInputProtected(true);
                    bBMsg.xxScrItem10Txt_B1.setInputProtected(true);
                    // START 2018/03/06 J.Kim [QC#24601,ADD]
                    bBMsg.xxScrItem50Txt_B2.setInputProtected(true);
                    // END 2018/03/06 J.Kim [QC#24601,ADD]
                    handler.setButtonEnabled(OPENWIN_ASSET_ACCT_GL, i, false);
                    handler.setButtonEnabled(OPENWIN_EXP_ACCT_GL, i, false);
                    handler.setButtonEnabled(OPENWIN_RTRN_WH_CD, i, false);
                    // START 2018/05/17 J.Kim [QC#25879,ADD]
                    bBMsg.psnNum_B.setInputProtected(true); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
                    handler.setButtonEnabled(OPENWIN_SR, i, false);
                    // END 2018/05/17 J.Kim [QC#25879,ADD]
                }
            }
            if (enableFlg) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_BA, ZYPConstant.FLG_ON_Y);
            }

        } else if (TAB_FIN.equals(tab)) {
            // START 2016/05/11 K.Kojima [QC#7113,DEL]
            // scrnMsg.xxBtnFlg_CA.clear();
            // scrnMsg.xxBtnFlg_CM.clear();
            // END 2016/05/11 K.Kojima [QC#7113,DEL]

            NLEL0060_CBMsg cBMsg;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                cBMsg = scrnMsg.C.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(cBMsg.xxChkBox_C1.getValue())) {
                    // START 2016/04/27 J.Kim [QC#7461,MOD]
                    if (!ASSET_STS.ACTIVATE.equals(cBMsg.assetStsCd_C1.getValue())) {
                        cBMsg.curValAmt_C1.setInputProtected(true);
                        cBMsg.curValAmt_C2.setInputProtected(true);
                    } else {
                        cBMsg.curValAmt_C1.setInputProtected(false);
                        cBMsg.curValAmt_C2.setInputProtected(false);
                    }
                    // END 2016/04/27 J.Kim [QC#7461,MOD]
                    // START 2016/06/30 J.Kim [QC#10877,DEL]
                    // cBMsg.curValAmt_C2.setInputProtected(false);
                    // END 2016/06/30 J.Kim [QC#10877,DEL]
                    cBMsg.finDtlCmntTxt_C1.setInputProtected(false);
                    enableFlg = true;
                } else {
                    cBMsg.curValAmt_C1.setInputProtected(true);
                    cBMsg.curValAmt_C2.setInputProtected(true);
                    cBMsg.finDtlCmntTxt_C1.setInputProtected(true);
                }
                cBMsg.prntDsAssetMstrPk_C1.setInputProtected(true);
            }

            if (enableFlg) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_CA, ZYPConstant.FLG_ON_Y);
            }
        }
    }

    /**
     * Control input protected after Merge button pressed
     * @param scrnMsg NLEL0060BMsg
     */
    public static void ctrlFieldPropForMerge(NLEL0060BMsg scrnMsg) {
        // START 2016/05/11 K.Kojima [QC#7113,DEL]
        // scrnMsg.xxBtnFlg_CM.clear();
        // scrnMsg.xxBtnFlg_CA.clear();
        // END 2016/05/11 K.Kojima [QC#7113,DEL]

        boolean enableFlg = false;

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_FIN.equals(tab)) {
            NLEL0060_CBMsg cBMsg;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                cBMsg = scrnMsg.C.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(cBMsg.xxChkBox_C1.getValue())) {
                    cBMsg.prntDsAssetMstrPk_C1.setInputProtected(false);
                    cBMsg.finDtlCmntTxt_C1.setInputProtected(false);
                    enableFlg = true;
                } else {
                    cBMsg.prntDsAssetMstrPk_C1.setInputProtected(true);
                    cBMsg.finDtlCmntTxt_C1.setInputProtected(true);
                }
                cBMsg.curValAmt_C1.setInputProtected(true);
                cBMsg.curValAmt_C2.setInputProtected(true);
            }

            if (enableFlg) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_CM, ZYPConstant.FLG_ON_Y);
            }
        }
    }

    /**
     * Control common Submit button property
     * @param handler S21CommonHandler
     * @param scrnMsg NLEL0060BMsg
     */
    public static void ctrlCmnSubBtnProp(S21CommonHandler handler, NLEL0060BMsg scrnMsg) {
        initCmnBtnProp(handler);

        boolean enableFlg = false;
        String tab = scrnMsg.xxDplyTab.getValue();

        if (TAB_DTL.equals(tab)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxPgFlg_T1.getValue())) {
                enableFlg = true;
            }
        } else if (TAB_ASG.equals(tab)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_BA.getValue())) {
                enableFlg = true;
            }
        } else if (TAB_FIN.equals(tab)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_CA.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_CM.getValue())) {
                enableFlg = true;
            }
        }

        if (enableFlg) {
            if (hasUpdateFuncId(scrnMsg)) {
                setCmnBtnProp(handler, BTN_CMN_SUB, 1);
            }
        }

        // START 2016/04/21 J.Kim [QC#6770,ADD]
        ctrlFieldProp(handler, scrnMsg);
        // END 2016/04/21 J.Kim [QC#6770,ADD]
    }

    /**
     * getNMAL6050Param
     * @param scrnMsg NLEL0060BMsg
     * @param value String
     * @return Object[
     */
    public static Object[] getNMAL6050Param(NLEL0060BMsg scrnMsg, String value) {

        scrnMsg.P.clear();
        scrnMsg.P.no(0).xxPopPrm.setValue("COA_ACCT");
        scrnMsg.P.no(1).xxPopPrm.setValue("COA_ACCT_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("COA_ACCT_NM");
        scrnMsg.P.no(3).xxPopPrm.setValue("COA_ACCT_SORT_NUM");
        scrnMsg.P.no(4).xxPopPrm.setValue("Lookup COA Account Code");
        scrnMsg.P.no(5).xxPopPrm.setValue("COA Account Code");
        scrnMsg.P.no(6).xxPopPrm.setValue("COA Account Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("COA Account Code");
        scrnMsg.P.no(8).xxPopPrm.setValue("COA Account Name");
        scrnMsg.P.no(9).xxPopPrm.setValue(value);
        scrnMsg.P.no(10).xxPopPrm.clear();

        Object[] param = new Object[11];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;

        return param;
    }

    /**
     * addCheckItem for each tab
     * @param scrnMsg NLEL0060BMsg
     */
    public static void addCheckItemForTab(NLEL0060BMsg scrnMsg) {

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            NLEL0060_ABMsg aBMsg;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                aBMsg = scrnMsg.A.no(i);
                scrnMsg.addCheckItem(aBMsg.xxChkBox_A1);
                if (ASSET_STS.PENDING.equals(aBMsg.assetStsCd_A1.getValue())) {
                    scrnMsg.addCheckItem(aBMsg.depcStartDt_A1);
                    scrnMsg.addCheckItem(aBMsg.cpoOrdNum_A1);
                    scrnMsg.addCheckItem(aBMsg.dplyLineNum_A1);
                    scrnMsg.addCheckItem(aBMsg.dsContrNum_A1);
                    scrnMsg.addCheckItem(aBMsg.contrEffFromDt_A1);
                    scrnMsg.addCheckItem(aBMsg.contrEffThruDt_A1);
                    scrnMsg.addCheckItem(aBMsg.custIssPoNum_A1);
                    scrnMsg.addCheckItem(aBMsg.invNum_A1);
                    scrnMsg.addCheckItem(aBMsg.vndCd_A1);
                    // START 2018/07/25 J.Kim [QC#24950,ADD]
                    scrnMsg.addCheckItem(aBMsg.dtlCmntTxt_A1);
                    // END 2018/07/125 J.Kim [QC#24950,ADD]
                    // START 2018/08/1 J.Kim [QC#26901,ADD]
                    scrnMsg.addCheckItem(aBMsg.amtChngRsnTpCd_A1);
                    // END 2018/08/1 J.Kim [QC#26901,ADD]
                }

                scrnMsg.addCheckItem(aBMsg.dsAssetDescTxt_A1);
                scrnMsg.addCheckItem(aBMsg.depcMthNum_A1);
                scrnMsg.addCheckItem(aBMsg.totAssetQty_A1);
                scrnMsg.addCheckItem(aBMsg.coaMdseTpCd_A1);
                scrnMsg.addCheckItem(aBMsg.assetTagNum_A1);
                scrnMsg.addCheckItem(aBMsg.dsAssetGrpInitBookAmt_A1);
                // START 2018/08/1 J.Kim [QC#26901,ADD]
                scrnMsg.addCheckItem(aBMsg.amtChngRsnTpCd_A1);
                // END 2018/08/1 J.Kim [QC#26901,ADD]
                if (!ZYPCommonFunc.hasValue(aBMsg.svcMachMstrPk_A1)) {
                    scrnMsg.addCheckItem(aBMsg.serNum_A1);
                    if (ZYPConstant.FLG_ON_Y.equals(aBMsg.manEntryFlg_A1.getValue())) {
                        scrnMsg.addCheckItem(aBMsg.dsAcctNm_A1);
                    }
                }
            }

        } else if (TAB_ASG.equals(tab)) {
            NLEL0060_BBMsg bBMsg;
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                bBMsg = scrnMsg.B.no(i);
                scrnMsg.addCheckItem(bBMsg.xxChkBox_B1);
                scrnMsg.addCheckItem(bBMsg.xxAllLineAddr_B1);
                scrnMsg.addCheckItem(bBMsg.ctyAddr_B1);
                scrnMsg.addCheckItem(bBMsg.stCd_B1);
                scrnMsg.addCheckItem(bBMsg.postCd_B1);
                scrnMsg.addCheckItem(bBMsg.asgDtlCmntTxt_B1);
                // START 2016/11/01 J.Kim [QC#16345,MOD]
                // scrnMsg.addCheckItem(bBMsg.xxScrItem50Txt_B1);
                scrnMsg.addCheckItem(bBMsg.coaAcctCd_B);
                scrnMsg.addCheckItem(bBMsg.coaBrCd_B);
                scrnMsg.addCheckItem(bBMsg.coaCcCd_B);
                scrnMsg.addCheckItem(bBMsg.coaExtnCd_B);
                // START 2016/11/01 J.Kim [QC#16345,MOD]
                scrnMsg.addCheckItem(bBMsg.xxScrItem50Txt_B2);
                if (!ZYPCommonFunc.hasValue(bBMsg.svcMachMstrPk_B1)) {
                    scrnMsg.addCheckItem(bBMsg.xxScrItem10Txt_B1);
                }
                // START 2018/05/17 J.Kim [QC#25879,ADD]
                scrnMsg.addCheckItem(bBMsg.psnNum_B); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
                // END 2018/05/17 J.Kim [QC#25879,ADD]
            }

        } else if (TAB_FIN.equals(tab)) {
            NLEL0060_CBMsg cBMsg;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                cBMsg = scrnMsg.C.no(i);
                scrnMsg.addCheckItem(cBMsg.xxChkBox_C1);
                scrnMsg.addCheckItem(cBMsg.finDtlCmntTxt_C1);
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_CA.getValue())) {
                    scrnMsg.addCheckItem(cBMsg.dsAssetGrpInitBookAmt_C1);
                    scrnMsg.addCheckItem(cBMsg.curValAmt_C1);
                    scrnMsg.addCheckItem(cBMsg.curValAmt_C2);

                } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_CM.getValue())) {
                    scrnMsg.addCheckItem(cBMsg.prntDsAssetMstrPk_C1);
                }
            }
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * Clear error message
     * @param scrnMsg NLEL0060BMsg
     */
    public static void clearErrMsg(NLEL0060BMsg scrnMsg) {

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            NLEL0060_ABMsg aBMsg;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                aBMsg = scrnMsg.A.no(i);
                aBMsg.xxPgFlg_A1.clear();
                aBMsg.xxChkBox_A1.clearErrorInfo();
            }
        } else if (TAB_ASG.equals(tab)) {
            NLEL0060_BBMsg bBMsg;
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                bBMsg = scrnMsg.B.no(i);
                bBMsg.xxChkBox_B1.clearErrorInfo();
            }
        } else if (TAB_FIN.equals(tab)) {
            NLEL0060_CBMsg cBMsg;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                cBMsg = scrnMsg.C.no(i);
                cBMsg.xxChkBox_C1.clearErrorInfo();
            }
        }
    }

    // START 2016/10/17 J.Kim [QC#13453,ADD]
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0220BMsg
     * @param accountString String
     * @param lineNum int
     * @return Object[]
     */
    public static boolean checkSearchPopupScreen(NLEL0060BMsg scrnMsg, String accountString, int lineNum) {

        scrnMsg.P.clear();
        if (ZYPCommonFunc.hasValue(accountString)) {
            String[] accStr = accountString.split(Pattern.quote(STR_COMMA));
            if (accStr.length != VAL_9) {
                return false;
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_CMPY_CD).xxPopPrm, accStr[ARRAY_NUM_CMPY_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_BR_CD).xxPopPrm, accStr[ARRAY_NUM_BR_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_CC_CD).xxPopPrm, accStr[ARRAY_NUM_CC_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_ACCT_CD).xxPopPrm, accStr[ARRAY_NUM_ACCT_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_PROD_CD).xxPopPrm, accStr[ARRAY_NUM_PROD_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_CH_CD).xxPopPrm, accStr[ARRAY_NUM_CH_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_AFFL_CD).xxPopPrm, accStr[ARRAY_NUM_AFFL_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_PROJ_CD).xxPopPrm, accStr[ARRAY_NUM_PROJ_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_EXTN_CD).xxPopPrm, accStr[ARRAY_NUM_EXTN_CD]);
        }
        return true;
    }
    // END 2016/10/17 J.Kim [QC#13453,ADD]

    // START 2018/03/28 J.Kim [QC#22087,ADD]
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NLEL0060BMsg
     * @param accountString String
     * @param lineNum int
     * @return Object[]
     */
    public static Object[] setParamForCoaAcctPopup(NLEL0060BMsg scrnMsg, String expCoaAcctCd, String scrnEventNm) {

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, TBL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, TBL_CD_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, TBL_NM_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, TBL_SORT_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, SCR_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, HDR_CD_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, HDR_NM_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, DTL_CD_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, expCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, scrnEventNm);

        Object[] params = new Object[12];
        int index = 0;
        params[index++] = scrnMsg.P.no(0).xxPopPrm;
        params[index++] = scrnMsg.P.no(1).xxPopPrm;
        params[index++] = scrnMsg.P.no(2).xxPopPrm;
        params[index++] = scrnMsg.P.no(3).xxPopPrm;
        params[index++] = scrnMsg.P.no(4).xxPopPrm;
        params[index++] = scrnMsg.P.no(5).xxPopPrm;
        params[index++] = scrnMsg.P.no(6).xxPopPrm;
        params[index++] = scrnMsg.P.no(7).xxPopPrm;
        params[index++] = scrnMsg.P.no(8).xxPopPrm;
        params[index++] = scrnMsg.P.no(9).xxPopPrm;
        params[index++] = scrnMsg.P.no(10).xxPopPrm;
        params[index++] = scrnMsg.P.no(11).xxPopPrm;

        return params;
    }

    public static Object[] setParamForCoaBrPopup(NLEL0060BMsg scrnMsg, String expCoaBrCd, String scrnEventNm) {

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, TBL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, TBL_CD_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, TBL_NM_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, TBL_SORT_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, SCR_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, HDR_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, HDR_NM_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, DTL_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, DTL_NM_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, expCoaBrCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, scrnEventNm);

        Object[] params = new Object[12];
        int index = 0;
        params[index++] = scrnMsg.P.no(0).xxPopPrm;
        params[index++] = scrnMsg.P.no(1).xxPopPrm;
        params[index++] = scrnMsg.P.no(2).xxPopPrm;
        params[index++] = scrnMsg.P.no(3).xxPopPrm;
        params[index++] = scrnMsg.P.no(4).xxPopPrm;
        params[index++] = scrnMsg.P.no(5).xxPopPrm;
        params[index++] = scrnMsg.P.no(6).xxPopPrm;
        params[index++] = scrnMsg.P.no(7).xxPopPrm;
        params[index++] = scrnMsg.P.no(8).xxPopPrm;
        params[index++] = scrnMsg.P.no(9).xxPopPrm;
        params[index++] = scrnMsg.P.no(10).xxPopPrm;
        params[index++] = scrnMsg.P.no(11).xxPopPrm;

        return params;
    }

    public static Object[] setParamForCoaCcPopup(NLEL0060BMsg scrnMsg, String expCoaCcCd, String scrnEventNm) {

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, TBL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, TBL_CD_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, TBL_NM_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, TBL_SORT_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, SCR_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, HDR_CD_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, HDR_NM_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, DTL_CD_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, DTL_NM_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, expCoaCcCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, scrnEventNm);

        Object[] params = new Object[12];
        int index = 0;
        params[index++] = scrnMsg.P.no(0).xxPopPrm;
        params[index++] = scrnMsg.P.no(1).xxPopPrm;
        params[index++] = scrnMsg.P.no(2).xxPopPrm;
        params[index++] = scrnMsg.P.no(3).xxPopPrm;
        params[index++] = scrnMsg.P.no(4).xxPopPrm;
        params[index++] = scrnMsg.P.no(5).xxPopPrm;
        params[index++] = scrnMsg.P.no(6).xxPopPrm;
        params[index++] = scrnMsg.P.no(7).xxPopPrm;
        params[index++] = scrnMsg.P.no(8).xxPopPrm;
        params[index++] = scrnMsg.P.no(9).xxPopPrm;
        params[index++] = scrnMsg.P.no(10).xxPopPrm;
        params[index++] = scrnMsg.P.no(11).xxPopPrm;

        return params;
    }

    public static Object[] setParamForCoaExtnPopup(NLEL0060BMsg scrnMsg, String expCoaExtnCd, String scrnEventNm) {

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, TBL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, TBL_CD_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, TBL_NM_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, TBL_SORT_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, SCR_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, HDR_CD_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, HDR_NM_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, DTL_CD_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, DTL_NM_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, expCoaExtnCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, scrnEventNm);

        Object[] params = new Object[12];
        int index = 0;
        params[index++] = scrnMsg.P.no(0).xxPopPrm;
        params[index++] = scrnMsg.P.no(1).xxPopPrm;
        params[index++] = scrnMsg.P.no(2).xxPopPrm;
        params[index++] = scrnMsg.P.no(3).xxPopPrm;
        params[index++] = scrnMsg.P.no(4).xxPopPrm;
        params[index++] = scrnMsg.P.no(5).xxPopPrm;
        params[index++] = scrnMsg.P.no(6).xxPopPrm;
        params[index++] = scrnMsg.P.no(7).xxPopPrm;
        params[index++] = scrnMsg.P.no(8).xxPopPrm;
        params[index++] = scrnMsg.P.no(9).xxPopPrm;
        params[index++] = scrnMsg.P.no(10).xxPopPrm;
        params[index++] = scrnMsg.P.no(11).xxPopPrm;

        return params;
    }
    // END 2018/03/28 J.Kim [QC#22087,ADD]

    // START 2018/05/17 J.Kim [QC#25879,ADD]
    /**
     * Get Param NWAL1130 For Salesrep
     * @param scrnMsg NLEL0060BMsg
     * @param index int
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NLEL0060BMsg scrnMsg, String glblCmpyCd, int index) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sales Rep Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    T.GLBL_CMPY_CD");
        sb.append("   ,T.EZCANCELFLAG");
        sb.append("   ,SP.PSN_NUM");
        sb.append("   ,T.TOC_NM");
        sb.append("   ,SP.LINE_BIZ_TP_CD");
        sb.append("   ,CB.COA_BR_NM");
        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("   ,T.TOC_CD ");
        sb.append("FROM");
        sb.append("    TOC T");
        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("   ,BIZ_AREA_ORG BA");
        sb.append("   ,ORG_FUNC_ASG OFA");
        sb.append("   ,S21_PSN SP");
        sb.append("   ,COA_BR CB ");
        sb.append("   ,S21_ORG SO ");
        sb.append("WHERE");
        sb.append("        T.GLBL_CMPY_CD        =  '").append(glblCmpyCd).append("'");
        sb.append("    AND T.EZCANCELFLAG        = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        sb.append("    AND SP.EFF_FROM_DT        <= '").append(ZYPDateUtil.getSalesDate()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(ZYPDateUtil.getSalesDate()).append("' OR SP.EFF_THRU_DT IS NULL)");
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Sales Rep Number";
        whereArray0[IDX_1] = "PSN_NUM";
        if (hasValue(scrnMsg.B.no(index).psnNum_B)) { // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
            whereArray0[IDX_2] = scrnMsg.B.no(index).psnNum_B.getValue(); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Name";
        whereArray1[IDX_1] = "TOC_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Sales Rep";
        whereArray2[IDX_1] = "TOC_CD";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Sales Rep Number";
        columnArray0[IDX_1] = "PSN_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_12);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Name";
        columnArray1[IDX_1] = "TOC_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Line of Business";
        columnArray2[IDX_1] = "LINE_BIZ_TP_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Branch";
        columnArray3[IDX_1] = "COA_BR_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Role";
        columnArray4[IDX_1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "TOC_CD";
        columnArray5[IDX_1] = "TOC_CD";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_0);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PSN_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Q);
        params[IDX_6] = scrnMsg.Q;

        return params;
    }
    // END 2018/05/17 J.Kim [QC#25879,ADD]
}
