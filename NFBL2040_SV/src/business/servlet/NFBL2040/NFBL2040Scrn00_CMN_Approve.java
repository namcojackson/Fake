/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2040.NFBL2040CMsg;
import business.parts.NFBCommonBusiness;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/27   Hitachi         Y.Takeno        Create          QC#22143
 * 2018/01/10   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/01/23   CITS            T.Gotoda        Update          QC#21694, 21696
 * 2018/03/07   Hitachi         J.Kim           Update          QC#24636
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/07/12   CITS            T.Tokutomi      Update          QC#27025 [Delete This Program]
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_CMN_Approve extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // Check Search Condition.
        String poNum = NFBCommonBusiness.chkNull(scrnMsg.poNum.getValue());
        String delyOrdNum_H = NFBCommonBusiness.chkNull(scrnMsg.delyOrdNum_H.getValue());
        String poNum_HD = NFBCommonBusiness.chkNull(scrnMsg.poNum_HD.getValue());
        String delyOrdNum_HD = NFBCommonBusiness.chkNull(scrnMsg.delyOrdNum_HD.getValue());
        // START QC#25902,QC#25190,QC#25141
        String vndRtrnNum = NFBCommonBusiness.chkNull(scrnMsg.vndRtrnNum.getValue());
        String vndRtrnNum_HD = NFBCommonBusiness.chkNull(scrnMsg.vndRtrnNum_HD.getValue());
        // END QC#25902,QC#25190,QC#25141

        if (!ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S)) {
            scrnMsg.cmApInvTpCd_S.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.cmApInvTpCd_S.getNameForMessage() });
        }

        String acctInvSts = scrnMsg.acctInvStsCd.getValue();
        if (!acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
            if (!poNum.equals(poNum_HD)) {
                scrnMsg.poNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.poNum.getNameForMessage() });
            }
            if (!delyOrdNum_H.equals(delyOrdNum_HD)) {
                scrnMsg.delyOrdNum_H.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.delyOrdNum_H.getNameForMessage() });
            }
            // START QC#25902,QC#25190,QC#25141
            if (!vndRtrnNum.equals(vndRtrnNum_HD)) {
                scrnMsg.vndRtrnNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.vndRtrnNum.getNameForMessage() });
            }
            // END QC#25902,QC#25190,QC#25141
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.apVndCd)) {
            scrnMsg.apVndCd.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.apVndCd.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum)) {
            scrnMsg.apVndInvNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.apVndInvNum.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.poNum_HT) && CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            scrnMsg.poNum_HT.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poNum_HT.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_DH)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.delyOrdNum_DH, NONE);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.invDt)) {
            scrnMsg.invDt.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.invDt.getNameForMessage() });
        }
        BigDecimal invAmt = NFBCommonBusiness.chkNull(scrnMsg.invAmt.getValue());
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())
                && BigDecimal.ZERO.compareTo(invAmt) <= 0) {
            scrnMsg.invAmt.setErrorInfo(1, NFBM0229E);
        }
        if (!CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())
                && BigDecimal.ZERO.compareTo(invAmt) >= 0) {
            scrnMsg.invAmt.setErrorInfo(1, NFBM0230E);
        }
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {
                    scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Hold Name" });
                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRsnCd_H1.getValue())) {
                    scrnMsg.H.no(i).pmtHldRsnCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Hold Reason" });
                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRsnCd_H1);
                }
            }
        }
        if (acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
            NFBL2040CommonLogic.checkHoldRelease(scrnMsg);
            scrnMsg.putErrorScreen();
        }
        scrnMsg.addCheckItem(scrnMsg.cmApInvTpCd_S);
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_H);
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.poNum_HT);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_DH);
        // Header Tab
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.invDt);
        scrnMsg.addCheckItem(scrnMsg.apPmtChkNum);
        scrnMsg.addCheckItem(scrnMsg.invAmt);
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermCd);
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt);
        scrnMsg.addCheckItem(scrnMsg.vndPmtMethCd);
        scrnMsg.addCheckItem(scrnMsg.vndPmtMethDescTxt);
        scrnMsg.addCheckItem(scrnMsg.poApvlDt);
        scrnMsg.addCheckItem(scrnMsg.entPoDtlDealNetAmt_TO);
        scrnMsg.addCheckItem(scrnMsg.invHdrDescTxt);
        scrnMsg.addCheckItem(scrnMsg.acctInvStsDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.pmtDt);
        scrnMsg.addCheckItem(scrnMsg.hdrPmtAmt);
        scrnMsg.addCheckItem(scrnMsg.termNetDueDt);
        scrnMsg.addCheckItem(scrnMsg.acOcTotDiscAmt);
        scrnMsg.addCheckItem(scrnMsg.apInvCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.acctDt);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum_HH);
        scrnMsg.addCheckItem(scrnMsg.invAmt_HH);
        // HOLDS Tab
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldCd_H1)
                    && PMT_HLD.THEREFORE.equals(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {
                scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0253E);
                scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
            }
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelRsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelCmntTxt_H1);
        }
        // LINES Tab
        boolean hasErrLinesTab = false;
        int noneMdseCdNum = 1;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1)) {
                scrnMsg.A.no(i).apLineTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Line Type" });
                hasErrLinesTab = true;
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxInvAmt_A1)) {
                scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Line Amount" });
                hasErrLinesTab = true;
            }

            if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxCntnrFlg_A1.getValue())
                    && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1)) {
                // Not Text Item required Item code.
                scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NFBM0255E, new String[] {"Line Type", "Item", "Item" });
                hasErrLinesTab = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && !AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                if (!AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).mdseCd_A1, NONE + String.format(NONE_MDSE_CD_FORMAT, noneMdseCdNum));
                    noneMdseCdNum++;
                }
            }
            if (!scrnMsg.A.no(i).xxCmntTxt_A1.isInputProtected() && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxCmntTxt_A1)) {
                scrnMsg.A.no(i).xxCmntTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Charge Account" });
                hasErrLinesTab = true;
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).apLineTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseDescShortTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxInvAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vndUomCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxCmntTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apAcctDescTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invRcvQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apRejQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apBillQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).custDlrCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).csmpInvNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxInstlFullAddr_A1);
        }

        // START 2018/01/23 [QC#21694, 21696 MOD]
        BigDecimal lineTotAmt = BigDecimal.ZERO;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            BigDecimal lineAmt = scrnMsg.A.no(i).xxInvAmt_A1.getValue();
            BigDecimal apBillQty = scrnMsg.A.no(i).apBillQty_A1.getValue();
            BigDecimal dealGrsUnitPrcAmt = scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue();

            if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {

                if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (apBillQty == null || BigDecimal.ZERO.compareTo(apBillQty) < 0) {
                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0231E);
                        hasErrLinesTab = true;
                    }

                    if (dealGrsUnitPrcAmt == null || BigDecimal.ZERO.compareTo(dealGrsUnitPrcAmt) > 0) {
                        scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setErrorInfo(1, NFBM0279E);
                        hasErrLinesTab = true;
                    }

                    if (lineAmt == null
                            || dealGrsUnitPrcAmt == null
                            || apBillQty == null
                            || lineAmt.compareTo(dealGrsUnitPrcAmt.multiply(apBillQty)) != 0) {

                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0225E);
                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0225E);
                        hasErrLinesTab = true;
                    }
                } else if (AP_LINE_TP.TAX.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                        || AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (lineAmt == null || BigDecimal.ZERO.compareTo(lineAmt) <= 0) {
                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0278E);
                        hasErrLinesTab = true;
                    }
                }
            } else if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {

                if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (apBillQty == null
                            || BigDecimal.ZERO.compareTo(apBillQty) > 0) {

                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0273E);
                        hasErrLinesTab = true;
                    }

                    if (dealGrsUnitPrcAmt == null || BigDecimal.ZERO.compareTo(dealGrsUnitPrcAmt) > 0) {
                        scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setErrorInfo(1, NFBM0279E);
                        hasErrLinesTab = true;
                    }

                    if (lineAmt == null
                            || dealGrsUnitPrcAmt == null
                            || apBillQty == null
                            || lineAmt.compareTo(dealGrsUnitPrcAmt.multiply(apBillQty)) != 0) {

                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0225E); 
                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0225E);
                        hasErrLinesTab = true;
                    }
                } else if (AP_LINE_TP.TAX.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                        || AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (lineAmt == null || BigDecimal.ZERO.compareTo(lineAmt) >= 0) {
                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0274E);
                        hasErrLinesTab = true;
                    }
                }
            }

            if (lineAmt != null) {
                lineTotAmt = lineTotAmt.add(lineAmt);
            }
        }
        // END  2018/01/23 [QC#21694, 21696 MOD]

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotPrcAmt_F, lineTotAmt);
        scrnMsg.addCheckItem(scrnMsg.xxTotPrcAmt_F);
        if (hasErrLinesTab) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_02, TAB_LINES);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
            NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        // DISTRIBUTIONS Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).invDt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).jrnlFuncDrAmt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).jrnlFuncCrAmt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).apAcctDescTxt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxCmntTxt_D1);
        }
        scrnMsg.putErrorScreen();

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NFBM0044E, new String[] {"Lines Data"});
            throw new EZDFieldErrorException();
        }
        if (invAmt.compareTo(lineTotAmt) != 0) {
            scrnMsg.setMessageInfo(NFBM0067E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NONE.equals(scrnMsg.delyOrdNum_DH.getValue())) {
            scrnMsg.delyOrdNum_DH.clear();
        }
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.poNum_HT);
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_DH);
        scrnMsg.addCheckItem(scrnMsg.invDt);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxCmntTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apBillQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apLineTpCd_A1);
            // START 2018/03/09 J.Kim [QC#24636,ADD]
            if (!AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                scrnMsg.A.no(i).mdseCd_A1.clear();
            }
            // END 2018/03/09 J.Kim [QC#24636,ADD]
        }
        scrnMsg.addCheckItem(scrnMsg.termNetDueDt);
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).xxChkBox_H1);
        }
        scrnMsg.putErrorScreen();

        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            // Common
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
            // START 2020/02/17 [QC#53413, MOD]
            //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, false);
            // END   2020/02/17 [QC#53413, MOD]
        } else {
            /** Initialize button control */ 
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        if (TAB_HOLDS.equals(scrnMsg.xxDplyTab_01.getValue())) {
            NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);
        }
        if (TAB_DISTRIBUTIONS.equals(scrnMsg.xxDplyTab_02.getValue())) {
            NFBL2040CommonLogic.clearRowsBG_D(scrnMsg);
        }
        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);

        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // START 2018/01/10 [QC#22143, MOD]
        // if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
        if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind()) 
                && scrnMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.AUTHORIZED)) {
        // END   2018/01/10 [QC#22143, MOD]
            NFBL2040CommonLogic.setButtonNotFromOthScrForAfterApprove(this, scrnMsg);
            NFBL2040CommonLogic.setInputProtectedForAfterApprove(scrnMsg);
            scrnMsg.setMessageInfo(ZZM8100I);
        }
        String acctInvSts = scrnMsg.acctInvStsCd.getValue();
        if (acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
            this.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
            this.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
        }
    }
}
