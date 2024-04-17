/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/19   Hitachi         T.Tsuchida      Update          QC#12015
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/08/03   Hitachi         K.Kojima        Update          QC#12589
 * 2016/09/15   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13550
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16949
 * 2017/10/20   CITS            T.Tokutomi      Update          QC#21653
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2018/03/07   Hitachi         J.Kim           Update          QC#24636
 * 2018/03/13   Hitachi         Y.Takeno        Update          QC#24274
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040_INIT extends S21INITCommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        // NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
            for (int i = 0; i < param.length; i++) {
                // START 2016/08/03 K.Kojima [QC#12589,ADD]
                // if (i == 0) {
                // // Invoice Type
                // ZYPEZDItemValueSetter.setValue(scrnMsg.apVndInvNum_OT,
                // ((EZDBStringItem) param[i]).getValue());
                // }
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd_OT, ((EZDBStringItem) param[i]).getValue());
                } else if (i == 1) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.apVndInvNum_OT, ((EZDBStringItem) param[i]).getValue());
                } else if (i == 2) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.apVndInvSqNum_OT, ((EZDBStringItem) param[i]).getValue());
                }
                // END 2016/08/03 K.Kojima [QC#12589,MOD]
            }
        }

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Set confirm message when clicking button */
        NFBL2040CommonLogic.setButtonConfirmMsg(this);

        // START 2016/09/15 Y.Tsuchimoto [QC#13156,ADD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            // Common
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
            // START 2020/03/23 [QC#53413, MOD]
            //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, true);
            // END   2020/03/23 [QC#53413, MOD]

            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForInit(scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditForInit(this, scrnMsg);
        } else {
            /** Initialize button control */
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        // END   2016/09/15 Y.Tsuchimoto [QC#13156,ADD]

        // START 2018/03/09 J.Kim [QC#24636,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFBL2040_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(abMsg.apLineTpCd_A1) && !AP_LINE_TP.ITEM.equals(abMsg.apLineTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(abMsg.xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
                scrnMsg.A.no(i).mdseCd_A1.clear();
            }
        }
        // END 2018/03/09 J.Kim [QC#24636,ADD]

        // START 2017/01/13 E.Kameishi [QC#16948,ADD]
        String acctInvSts = scrnMsg.acctInvStsCd.getValue();
        if (acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
            this.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
            this.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
        }
        // END 2017/01/13 E.Kameishi [QC#16948,ADD]
        /** Set alternate rows background color */
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum_OT)) {
            // From other screen
            NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        }
        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);
        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2017/01/13 E.Kameishi [QC#16949,ADD]
        NFBL2040CommonLogic.setFocusRule(scrnMsg);
        // END 2017/01/13 E.Kameishi [QC#16949,ADD]
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) arg0;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        scrnMsg.cmApInvTpCd_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Type"));
        scrnMsg.poNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Number"));
        scrnMsg.delyOrdNum_H.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Number"));

        // HEADER tab
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.dplyVndNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Name"));
        scrnMsg.apVndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Site Code"));
        // END   2017/12/22 [QC#22831, MOD]
        scrnMsg.apVndInvNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Number"));
        scrnMsg.invDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Date"));
        scrnMsg.xxChkBox_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Pay Alone"));
        scrnMsg.apPmtChkNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Number"));
        scrnMsg.vndPmtMethCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Method Code"));
        scrnMsg.vndPmtMethDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Method"));
        scrnMsg.acctInvStsDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Status"));
        scrnMsg.prntVndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Number"));
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Matching"));
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Number"));
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.invAmt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Amount"));
        scrnMsg.vndPmtTermCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Term Code"));
        scrnMsg.vndPmtTermDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Terms"));
        scrnMsg.xxChkBox_HO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Holds"));
        scrnMsg.pmtDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Date"));
        scrnMsg.hdrPmtAmt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Amount Paid"));
        scrnMsg.termNetDueDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Terms Date"));
        scrnMsg.poApvlDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Date"));
        scrnMsg.entPoDtlDealNetAmt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Amount"));
        scrnMsg.delyOrdNum_DH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Number"));
        scrnMsg.invHdrDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Description"));
        scrnMsg.xxChkBox_PO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Variance"));
        scrnMsg.acOcTotDiscAmt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Discount"));
        scrnMsg.apInvCatgDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Source"));
        scrnMsg.acctDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Accounting Date"));
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Vendor Return Number"));
        scrnMsg.vndRtrnNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Vendor Return Number"));
        // END QC#25902,QC#25190,QC#25141

        // Detail
        // add start 2022/02/15 QC#57090
        scrnMsg.poNum_L.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Number"));
        scrnMsg.delyOrdNum_L.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Number"));
        scrnMsg.vndRtrnNum_L.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Vendor Return Number"));
        // add end 2022/02/15 QC#57090
        // START 2016/07/19 T.Tsuchida [QC#12015,ADD]
        // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFBL2040_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.apLineTpCd_A1.setNameForMessage("Line Type");
            abMsg.mdseCd_A1.setNameForMessage("Item");
            abMsg.mdseDescShortTxt_A1.setNameForMessage("Line Description");
            abMsg.xxInvAmt_A1.setNameForMessage("Line Amount");
            abMsg.vndUomCd_A1.setNameForMessage("Unit Of Measure");
            abMsg.xxCmntTxt_A1.setNameForMessage("Charge Account");
            abMsg.apAcctDescTxt_A1.setNameForMessage("Account Description");
            abMsg.dealGrsUnitPrcAmt_A1.setNameForMessage("Unit Price");
            abMsg.poQty_A1.setNameForMessage("Ordered Quantity");
            abMsg.invRcvQty_A1.setNameForMessage("Received Quantity");
            abMsg.apRejQty_A1.setNameForMessage("Rejected Quantity");
            // START 2018/03/13 [QC#24274, MOD]
            // abMsg.apBillQty_A1.setNameForMessage("Billed Quantity");
            abMsg.apBillQty_A1.setNameForMessage("Invoiced Qty");
            // END   2018/03/13 [QC#24274, MOD]
            abMsg.dsContrNum_A1.setNameForMessage("Contract");
            abMsg.custDlrCd_A1.setNameForMessage("Dealer Code");
            abMsg.serNum_A1.setNameForMessage("Serial Number");
            abMsg.csmpInvNum_A1.setNameForMessage("CSMP Invoice");
            abMsg.xxInstlFullAddr_A1.setNameForMessage("Install Location");
        }
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            NFBL2040_DBMsg dbMsg = scrnMsg.D.no(i);
            dbMsg.xxDtlLineNum_D1.setNameForMessage("Line Number");
            dbMsg.xxDtlLineNum_D2.setNameForMessage("Distribution Line Number");
            dbMsg.invDt_D1.setNameForMessage("Date");
            dbMsg.jrnlFuncDrAmt_D1.setNameForMessage("Debit");
            dbMsg.jrnlFuncCrAmt_D1.setNameForMessage("Credit");
            dbMsg.apAcctDescTxt_D1.setNameForMessage("Account Description");
            dbMsg.xxCmntTxt_D1.setNameForMessage("Account Code");
        }
        // END 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
        // END 2016/07/19 T.Tsuchida [QC#12015,ADD]
    }
}
