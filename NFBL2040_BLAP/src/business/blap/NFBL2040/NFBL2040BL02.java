/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2040;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL2040.common.NFBL2040CommonLogic;
import business.blap.NFBL2040.constant.NFBL2040Constant;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#11331
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12971
 * 2016/08/25   Hitachi         Y.Tsuchimoto    Update          QC#12979
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Update          QC#12043
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13550
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2016/10/07   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/07   Hitachi         Y.Tsuchimoto    Update          QC#15091
 * 2016/11/11   Hitachi         K.Kasai         Update          QC#15445
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15904
 * 2017/11/14   CITS            T.Wada          Update          QC#21727
 * 2018/01/04   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/02/23   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/13   Hitachi         Y.Takeno        Update          QC#24274
 * 2018/03/22   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/06/26   CITS            S.Katsuma       Update          QC#24617
 * 2018/07/30   CITS            T.Tokutomi      Update          QC#27029
 * 2018/08/23   Hitachi         Y.Takeno        Update          QC#27247-1
 * 2018/10/12   Hitachi         Y.Takeno        Update          QC#28226
 * 2018/11/29   Hitachi         Y.Takeno        Update          QC#28904
 * 2019/02/21   Hitachi         Y.Takeno        Update          QC#30420
 * 2019/09/02   Hitachi         Y.Takeno        Update          QC#52876
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 * 2020/07/02   CITS            R.Kurahashi     Update          QC#56696
 * 2020/04/22   CITS            R.Azucena       Update          QC#56829
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2023/01/17   Hitachi         S.Nakatani      Update          QC#60812
 * </pre>
 */
public class NFBL2040BL02 extends S21BusinessHandler implements NFBL2040Constant {
    /** User ID */
    private final String USER_ID = getContextUserInfo().getUserId();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            // +++++ [START] : Scrn00
            if ("NFBL2040_INIT".equals(screenAplID)) {
                doProcess_NFBL2040_INIT(cMsg, sMsg);
                // START 2018/08/23 [QC#27247-1, ADD]
                ZYPGUITableColumn.getColData(cMsg, sMsg, "AHEAD");
                // END   2018/08/23 [QC#27247-1, ADD]
                // START 2020/02/17 [QC#53413, ADD]
                // mod start 2022/02/15 QC#57090
                //NFBL2040CommonLogic.delMultiPoData(cMsg);
                NFBL2040CommonLogic.clearMultiPoOrMultiVndRtrnData(cMsg);
                // mod end 2022/02/15 QC#57090
                // END   2020/02/17 [QC#53413, ADD]
            } else if ("NFBL2040Scrn00_Search".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Search(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_Refresh".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Refresh(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_InsertRow(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_DeleteRow(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_ItemDescription".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_ItemDescription(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_OnChange_XX_ALLOC_TP_CD".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_OnChange_XX_ALLOC_TP_CD(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_HoldTabDownloadButton".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_HoldTabDownloadButton(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_LinesTabDownloadButton".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_LinesTabDownloadButton(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_DistributionTabDownloadButton".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_DistributionTabDownloadButton(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_TAB_Distributions".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_TAB_Distributions(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_TAB_Hold".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_TAB_Hold(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Clear(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Reset(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_InvoiceCancel".equals(screenAplID)) {
                doProcess_NFBL2040_INIT(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_CMN_Submit".equals(screenAplID)) {
                // No Operation
                return;
            } else if ("NFBL2040Scrn00_Release".equals(screenAplID)) {
                // No Operation
                return;
            } else if ("NFBL2040Scrn00_OpenWin_ChargeAccount".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_OpenWin_ChargeAccount(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_InsertRowHold".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_InsertRowHold(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_DeleteRowHold".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_DeleteRowHold(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_Hold".equals(screenAplID)) {
                    // No Operation
                    return;
            } else if ("NFBL2040_NWAL1130".equals(screenAplID)) {
                // START QC#25902,QC#25190,QC#25141
                if ("Correction".equals(((NFBL2040CMsg)cMsg).xxScrEventNm.getValue())) {
                    doProcess_NFBL2040Scrn00_Correction(cMsg, sMsg);
                    return;
                } else {
                    doProcess_NFBL2040Scrn00_NFBL2040_NWAL1130(cMsg, sMsg);
                }
                // END QC#25902,QC#25190,QC#25141
            } else if ("NFBL2040Scrn00_OnChange_Location".equals(screenAplID)) {
                        doProcess_NFBL2040Scrn00_OnChange_Location(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_OnChange_InvDt".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_OnChange_PmtTerm(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_OnChange_PmtTerm".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_OnChange_PmtTerm(cMsg, sMsg);
            // QC#21727
            } else if ("NFBL2040Scrn00_OnChange_ApLineTpCd".equals(screenAplID)) {
            	doProcess_NFBL2040Scrn00_OnChange_ApLineTpCd(cMsg, sMsg);
            // START 2018/01/04 [QC#22143, MOD]
            } else if ("NFBL2040Scrn00_CMN_Approve".equals(screenAplID)) {
                // No Operation
                return;
            // END   2018/01/04 [QC#22143, MOD]
            // START 2018/02/23 [QC#23505, MOD]
            } else if ("NFBL2040Scrn00_Correction".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Correction(cMsg, sMsg);
                return;
            // END   2018/23/23 [QC#23505, MOD]
            // START 2018/03/13 [QC#24274, MOD]
            } else if ("NFBL2040Scrn00_OnChange_UnitPrc".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_OnChange_UnitPrc(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_OnChange_BillQty".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_OnChange_BillQty(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_OnChange_InvAmt".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_OnChange_InvAmt(cMsg, sMsg);
            // END   2018/03/13 [QC#24274, MOD]
            // START QC#25902,QC#25190,QC#25141
            } else if ("NFBL2040Scrn00_PO_Line_Correction".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_POLineCorrection(cMsg, sMsg);
            // END QC#25902,QC#25190,QC#25141
            // START 2018/08/23 [QC#27247-1. ADD]
            } else if ("NFBL2040Scrn00_CMN_ColSave".equals(screenAplID)) {
                // No Operation
                return;
            } else if ("NFBL2040Scrn00_CMN_ColClear".equals(screenAplID)){
                // No Operation
                return;
            // END   2018/08/23 [QC#27247-1. ADD]
            // START 2018/11/29 [QC#28904, ADD]
            } else if ("NFBL2040Scrn00_Regenerate_Invoice".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Regenerate_Invoice(cMsg, sMsg);
            // END   2018/11/29 [QC#28904, ADD]
            // START 2020/02/17 [QC#53413, ADD]
            } else if ("NFBL2040Scrn00_Line_Search".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Line_Search(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_Correction".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Correction(cMsg, sMsg);
            // END   2020/02/17 [QC#53413, ADD]
            // START 2020/07/02 R.Kurahashi [QC#56696,ADD]
            } else if ("NFBL2040Scrn00_Amount_Calc".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Amount_Calc(cMsg, sMsg);
            // END 2020/07/02 R.Kurahashi [QC#56696,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Scrn01
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    
    /**
     * Method name: doProcess_NFBL2040_INIT
     * <dd>The method explanation: Init
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040_INIT================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // Create [Invoice Type] pulldown.
        NFBL2040CommonLogic.createCmApInvTpPulldownList(bizMsg);
        // Create [Invoice Matching] pulldown.
        NFBL2040CommonLogic.createCmInvMatchPulldownList(bizMsg);

        // Create [Hold Name] pulldown.
        NFBL2040CommonLogic.createPmtHldPulldownList(bizMsg);
        // Create [Hold Reason] pulldown.
        NFBL2040CommonLogic.createPmtHldRsnPulldownList(bizMsg);

        // From Other Screen
        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT)) {
            if (!NFBL2040CommonLogic.getInvRecordFromOtherScreen(bizMsg)) {
                // Error
                return;
            }
        }

        EZDDebugOutput.println(5, "doProcess_NFBL2040_INIT================================END", this);
    }

    // START 2020/02/17 [QC#53413, ADD]
    /**
     * Method name: doProcess_NFBL2040Scrn00_Line_Search
     * <dd>The method explanation: [Search] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Line_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Line_Search================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // Get screen information.
        // mod start 2022/02/15 QC#57090
        //NFBL2040CommonLogic.getRecordPoSearch(bizMsg);
        NFBL2040CommonLogic.getRecordLineSearch(bizMsg);
        // mod end 2022/02/15 QC#57090
        // Keep search condition
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_HD, bizMsg.cmApInvTpCd_S.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_HD, bizMsg.delyOrdNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_HD, bizMsg.rwsNum_H.getValue());
        // mod start 2022/02/15 QC#57090
        //ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum.getValue());
        // mod end 2022/02/15 QC#57090

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Line_Search================================END", this);
    }
    // END   2020/02/17 [QC#53413, ADD]

    /**
     * Method name: doProcess_NFBL2040Scrn00_Search
     * <dd>The method explanation: [Search] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Search================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // Clear information on HEADER tab
        NFBL2040CommonLogic.clearHeader(bizMsg, ZYPConstant.FLG_OFF_N);
        // Clear information on HOLDS tab
        NFBL2040CommonLogic.clearHolds(bizMsg);
        // Clear information on LINES tab
        NFBL2040CommonLogic.clearLines(bizMsg);
        // Clear information on DISTRIBUTIONS tab
        NFBL2040CommonLogic.clearDistributions(bizMsg);
        // Get screen information.
        NFBL2040CommonLogic.getRecord(bizMsg);
        // Keep search condition
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_HD, bizMsg.cmApInvTpCd_S.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_HD, bizMsg.delyOrdNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_HD, bizMsg.rwsNum_H.getValue());
        // START QC#25902,QC#25190,QC#25141
        // mod start 2022/02/15 QC#57090
        //ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum.getValue());
        // mod end 2022/02/15 QC#57090
        // END QC#25902,QC#25190,QC#25141

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Search================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_Refresh
     * <dd>The method explanation: [Refresh] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Refresh(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Refresh================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        //NFBL2040CommonLogic.commonRefreshHoldsTab(bizMsg, USER_ID);
        // QC#27029 Update.
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            NFBL2040CommonLogic.commonRefreshHoldsTab(bizMsg, USER_ID);
        } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) //
                && ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            NFBL2040CommonLogic.commonRefreshHoldsTab(bizMsg, USER_ID);
        }
        NFBL2040CommonLogic.commonRefreshDistributionsTab(bizMsg, USER_ID);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Refresh================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_InsertRow
     * <dd>The method explanation: [Insert Row] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NFBL2040Scrn00_InsertRow(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_InsertRow================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        int validCountA = bizMsg.A.getValidCount();
        // Get records for [Line Type] pulldown values.
        List apLineTpPulldownList = null;
        // START 2019/09/02 [QC#52876, MOD]
        // if (ZYPCommonFunc.hasValue(bizMsg.cmApInvTpCd_S) && CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
        if (ZYPCommonFunc.hasValue(bizMsg.cmApInvTpCd_S) && (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())
                || CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()))) {
        // END   2019/09/02 [QC#52876, MOD]
            apLineTpPulldownList = NFBL2040CommonLogic.getApLineTpPulldownListForPoMatch();
        } else {
            apLineTpPulldownList = NFBL2040CommonLogic.getApLineTpPulldownList();
        }
        BigDecimal lineTotAmt = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, lineTotAmt);
        // START 2019/02/21 [QC#30420, ADD]
        String newDtlLineNum = String.format(AP_VND_INV_LINE_NUM_FORMAT,
                NFBL2040CommonLogic.getLastInvoiceDtlLineNumber(bizMsg) + 1);
        // END   2019/02/21 [QC#30420, ADD]
        // Line Number
        // START 2018/03/22 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(validCountA).xxDtlLineNum_A1, String.format(LINE_NUM_FORMAT, validCountA + 1));                                // Line Number
        // START 2019/02/21 [QC#30420, ADD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(validCountA).xxDtlLineNum_A1, String.format(AP_VND_INV_LINE_NUM_FORMAT, validCountA + 1));                                // Line Number
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(validCountA).xxDtlLineNum_A1, newDtlLineNum);
        // END   2019/02/21 [QC#30420, ADD]
        // END   2018/03/22 [QC#20316, MOD]
        // Create [Line Type] pulldown for each rows.
        NFBL2040CommonLogic.createApLineTpPulldownList(apLineTpPulldownList, validCountA, bizMsg);
        bizMsg.A.no(validCountA).apLineTpCd_A1.clear();                                                           // Line Type
        bizMsg.A.no(validCountA).mdseCd_A1.clear();                // Item
        bizMsg.A.no(validCountA).mdseDescShortTxt_A1.clear();      // Line Description
        bizMsg.A.no(validCountA).xxInvAmt_A1.clear();               // Line Amount
        bizMsg.A.no(validCountA).acInvJrnlCostAmt_A1.clear();
        bizMsg.A.no(validCountA).vndUomCd_A1.clear();              // Unit Of Measure
        bizMsg.A.no(validCountA).xxCmntTxt_A1.clear();             // Charge Account
        bizMsg.A.no(validCountA).apAcctDescTxt_A1.clear();           // Account Description
        bizMsg.A.no(validCountA).dealGrsUnitPrcAmt_A1.clear();     // Unit Price
        bizMsg.A.no(validCountA).invQty_A1.clear();                // Invoice Quantity
        // START 2021/04/22 R.Azucena [QC#56829, ADD]
        bizMsg.A.no(validCountA).slsHldQty_A1.clear();             // Invoice Qty on HOLD
        // END 2021/04/22 R.Azucena [QC#56829, ADD]
        bizMsg.A.no(validCountA).poQty_A1.clear();                 // Ordered Quantity
        bizMsg.A.no(validCountA).invRcvQty_A1.clear();             // Received Qunatity
        bizMsg.A.no(validCountA).apRejQty_A1.clear();              // Rejected Quantity
        bizMsg.A.no(validCountA).apBillQty_A1.clear();             // Billed Quantity
        bizMsg.A.no(validCountA).dsContrNum_A1.clear();            // Contract
        bizMsg.A.no(validCountA).custDlrCd_A1.clear();             // Dealer Code
        bizMsg.A.no(validCountA).serNum_A1.clear();                // Serial Number
        bizMsg.A.no(validCountA).csmpInvNum_A1.clear();            // CSMP Invoice
        bizMsg.A.no(validCountA).xxInstlFullAddr_A1.clear();       // Install Location
        bizMsg.A.no(validCountA).cmStkInPk_A1.clear();
        bizMsg.A.no(validCountA).invAsgFlg_A1.clear();
        bizMsg.A.no(validCountA).origVndInvNum_A1.clear();         // Original Vendor Invoice Number
        bizMsg.A.no(validCountA).origVndInvSqNum_A1.clear();       // Original Vendor Invoice Sequence Number
        bizMsg.A.no(validCountA).origDelyOrdNum_A1.clear();        // Original Delivery Order Number
        bizMsg.A.no(validCountA).poOrdDtlLineNum_A1.clear();       // PO Order Detail Line Number
        // START 2018/02/27 [QC#23505, ADD]
        bizMsg.A.no(validCountA).poNum_A1.clear();                 // PO Order Number
        // START 2020/02/17 [QC#53413, ADD]
        bizMsg.A.no(validCountA).poApvlDt_A1.clear();              // PO Date
        bizMsg.A.no(validCountA).entPoDtlDealNetAmt_A3.clear();    // PO Amount
        bizMsg.A.no(validCountA).delyOrdNum_A2.clear();            // Receipt
        bizMsg.A.no(validCountA).rwsNum_A1.clear();                // RWS Number
        // END   2020/02/17 [QC#53413, ADD]
        bizMsg.A.no(validCountA).invCrctDt_A1.clear();             // Correction Date
        // START 2018/06/26 S.Katsuma [QC#24617,ADD]
        bizMsg.A.no(validCountA).locNm_A1.clear();               // Received WH
        // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(validCountA).delyOrdNum_A1,
                bizMsg.delyOrdNum_DH);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(validCountA).apVndInvSqNum_A1, 
                bizMsg.apVndInvSqNum);                             // 
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(validCountA).xxLineTpCd_A1,
                XX_LINE_TP_CD_VALID);                              // 
        // END   2018/02/27 [QC#23505, ADD]
        validCountA++;
        bizMsg.A.setValidCount(validCountA);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_InsertRow================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_DeleteRow
     * <dd>The method explanation: [Delete Row] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_DeleteRow(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_DeleteRow================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;


        // Check selected Row exists
        List<Integer> checkedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (checkedRows.isEmpty()) {
            bizMsg.setMessageInfo(NFBM0017E, new String[] {EMPTY_STRING });
            return;
        }

        // Check AP_LINE_TP is not 'ITEM'
        boolean isErr = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2018/02/27 [QC#20505, ADD]
            if (!NFBL2040CommonLogic.isValidLine(bizMsg, bizMsg.A.no(i)) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NFBM0280E, new String[]{});
                isErr = true;
            }
            if(NFBL2040CommonLogic.isCorrectableInvoice(bizMsg) && NFBL2040CommonLogic.isCorrectedInvoice(bizMsg)
                    && NFBL2040CommonLogic.isUnsubmittedCorrection(bizMsg)) {
                continue;
            }
            // END   2018/02/27 [QC#20505, ADD]

            // START 2019/09/02 [QC#52876, MOD]
            // if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
            if ((CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) 
                    || CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()))
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
            // END   2019/09/02 [QC#52876, MOD]
                if (AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NFBM0269E, new String[]{});
                    isErr = true;
                }
            }
        }
        if (isErr) {
            return;
        }

        List<Integer> unCheckedRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (bizMsg.A.no(i).xxChkBox_A1.isClear()) {
                unCheckedRows.add(i);
            }
        }

        List<NFBL2040_ACMsg> unCheckedRecords = new ArrayList<NFBL2040_ACMsg>();
        Iterator<Integer> it = unCheckedRows.iterator();
        while (it.hasNext()) {
            unCheckedRecords.add(bizMsg.A.no(it.next()));
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (i < unCheckedRecords.size()) {
                EZDMsg.copy(unCheckedRecords.get(i), null, bizMsg.A.no(i), null);

            } else {
                bizMsg.A.no(i).clear();
            }
        }
        bizMsg.A.setValidCount(unCheckedRecords.size());

        BigDecimal lineTotAmt = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).xxChkBox_A1.clear();
            lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));

            // Reset Line Number
            // START 2018/03/22 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxDtlLineNum_A1, String.format(LINE_NUM_FORMAT, i + 1));
            // START 2019/02/21 [QC#30420, DEL]
            // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxDtlLineNum_A1, String.format(AP_VND_INV_LINE_NUM_FORMAT, i + 1));
            // END   2019/02/21 [QC#30420, DEL]
            // END   2018/03/22 [QC#20316, MOD]
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, lineTotAmt);

        // START 2019/02/21 [QC#30420, ADD]
        NFBL2040CommonLogic.renumberInvoiceDtlLineNumber(bizMsg);
        // END   2019/02/21 [QC#30420, ADD]

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_DeleteRow================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_Clear
     * <dd>The method explanation: Clear values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Clear================================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // Clear search condition
        NFBL2040CommonLogic.clearHiddenValue(bizMsg);
        // Clear search condition
        NFBL2040CommonLogic.clearSearchCondition(bizMsg);
        // Clear information on HEADER tab
        NFBL2040CommonLogic.clearHeader(bizMsg, ZYPConstant.FLG_OFF_N);
        // Clear information on HOLDS tab
        NFBL2040CommonLogic.clearHolds(bizMsg);
        // Clear information on LINES tab
        NFBL2040CommonLogic.clearLines(bizMsg);
        // Clear information on DISTRIBUTIONS tab
        NFBL2040CommonLogic.clearDistributions(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Clear================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_Reset
     * <dd>The method explanation: Reset values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Reset================================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // Clear search condition
        NFBL2040CommonLogic.clearHiddenValue(bizMsg);
        // Clear search condition
        NFBL2040CommonLogic.clearSearchCondition(bizMsg);
        // Clear information on HEADER tab
        NFBL2040CommonLogic.clearHeader(bizMsg, ZYPConstant.FLG_OFF_N);
        // Clear information on HOLDS tab
        NFBL2040CommonLogic.clearHolds(bizMsg);
        // Clear information on LINES tab
        NFBL2040CommonLogic.clearLines(bizMsg);
        // Clear information on DISTRIBUTIONS tab
        NFBL2040CommonLogic.clearDistributions(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Reset================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_ItemDescription
     * <dd>The method explanation: [>>] button event on the [LINES] tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_ItemDescription(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_ItemDescription================================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        NFBL2040CommonLogic.getMdseNm(bizMsg);
        // QC#21727
        NFBL2040CommonLogic.getAslInfo(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_ItemDescription================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_XX_ALLOC_TP_CD
     * <dd>The method explanation: [Distribution] radio button event.
     * <dd>Remarks:s
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OnChange_XX_ALLOC_TP_CD(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_XX_ALLOC_TP_CD================================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        NFBL2040CommonLogic.commonRefreshDistributionsTab(bizMsg, USER_ID);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_XX_ALLOC_TP_CD================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_HoldTabDownloadButton
     * <dd>The method explanation: CSV Download
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_HoldTabDownloadButton(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_HoldTabDownloadButton================================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        /** Create CSV data */
        NFBL2040CommonLogic.csvDownLoadHoldTab(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_HoldTabDownloadButton================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_LinesTabDownloadButton
     * <dd>The method explanation: CSV Download
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_LinesTabDownloadButton(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_LinesTabDownloadButton================================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        /** Create CSV data */
        NFBL2040CommonLogic.csvDownLoadLinesTab(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_LinesTabDownloadButton================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_DistributionTabDownloadButton
     * <dd>The method explanation: CSV Download
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_DistributionTabDownloadButton(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_DistributionTabDownloadButton================================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        /** Create CSV data */
        NFBL2040CommonLogic.csvDownLoadDistributionTab(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_DistributionTabDownloadButton================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_TAB_Distributions
     * <dd>The method explanation: Event when selecting [DISTRIBUTIONS] tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_TAB_Distributions(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_DistributionTabDownloadButton================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        NFBL2040CommonLogic.commonRefreshDistributionsTab(bizMsg, USER_ID);
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_DistributionTabDownloadButton================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_TAB_Hold
     * <dd>The method explanation: Event when selecting [HOLDS] tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_TAB_Hold(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_TAB_Hold================================START", this);
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_TAB_Hold================================END", this);

        return;
    }

    // START 2016/08/02 T.Tsuchida [QC#12040,ADD]
    /**
     * Method name: doProcess_NFBL2040Scrn00_OpenWin_ChargeAccount
     * <dd>The method explanation: Event when selecting Charge Account.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OpenWin_ChargeAccount(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OpenWin_ChargeAccount================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        int idx = bizMsg.xxCellIdx.getValueInt();
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).xxCmntTxt_A1)) {
            String xxCmntTxt = bizMsg.A.no(idx).xxCmntTxt_A1.getValue();
            String[] strSplit = xxCmntTxt.split("\\.");
            if (!NFBL2040CommonLogic.check9Seg(bizMsg, idx, strSplit)) {
                return;
            }
        }

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OpenWin_ChargeAccount================================END", this);
    }
    /**
     * doProcess_NFBL2040Scrn00_OnChange_ApLineTpCd (QC#21727)
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2040Scrn00_OnChange_ApLineTpCd(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_ApLineTpCd================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        int idx = bizMsg.xxCellIdx.getValueInt();
        boolean ret = NFBL2040CommonLogic.getAccountInfo(bizMsg, idx);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OpenWin_ChargeAccount================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_InsertRowHold
     * <dd>The method explanation: [Insert Row(Hold)] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_InsertRowHold(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        int validCountH = bizMsg.H.getValidCount();

        bizMsg.H.no(validCountH).xxChkBox_H2.clear();         // Check Box for delete
        bizMsg.H.no(validCountH).pmtHldCd_H1.clear();         // Hold Name
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCountH).pmtHldDt_H1, ZYPDateUtil.getSalesDate());  // Hold Date
        bizMsg.H.no(validCountH).pmtHldRsnCd_H1.clear();      // Hold Reason
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCountH).pmtHldPsnCd_H1, USER_ID);  // Hold By
        bizMsg.H.no(validCountH).xxChkBox_H1.clear();         // Hold Release
        bizMsg.H.no(validCountH).pmtHldRelPsnCd_H1.clear();   // Released By
        bizMsg.H.no(validCountH).pmtHldRelDt_H1.clear();      // Released Date
        NFBL2040CommonLogic.createPmtHldRelRsnPulldownList(NFBL2040CommonLogic.getPmtHldRelRsnPulldownList(), validCountH, bizMsg);
        bizMsg.H.no(validCountH).pmtHldRelRsnCd_H1.clear();   // Release Reason
        bizMsg.H.no(validCountH).pmtHldRelCmntTxt_H1.clear(); // Release Note
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCountH).xxInsUpdDelFlg_H1, ZYPConstant.FLG_ON_Y); // Manual Hold Flag
        validCountH++;
        bizMsg.H.setValidCount(validCountH);

    }
    /**
     * Method name: doProcess_NFBL2040Scrn00_DeleteRowHold
     * <dd>The method explanation: [Delete Row(Hold)] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_DeleteRowHold(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        List<Integer> checkedRows = new ArrayList<Integer>();

        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxChkBox_H2.getValue())) {
                checkedRows.add(i);
                break;
            }
        }

        if (checkedRows.isEmpty()) {
            bizMsg.setMessageInfo(NFBM0017E, new String[] {EMPTY_STRING });
        }

        List<Integer> unCheckedRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (bizMsg.H.no(i).xxChkBox_H2.isClear()) {
                unCheckedRows.add(i);
            }
        }

        List<NFBL2040_HCMsg> unCheckedRecords = new ArrayList<NFBL2040_HCMsg>();
        Iterator<Integer> it = unCheckedRows.iterator();
        while (it.hasNext()) {
            unCheckedRecords.add(bizMsg.H.no(it.next()));
        }

        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (i < unCheckedRecords.size()) {
                EZDMsg.copy(unCheckedRecords.get(i), null, bizMsg.H.no(i), null);

            } else {
                bizMsg.H.no(i).clear();
            }
        }
        bizMsg.H.setValidCount(unCheckedRecords.size());
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_Location
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OnChange_Location(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_Location================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVndPmt(bizMsg);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            Map<String, Object> rtn = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtMethCd, (String) rtn.get(VND_PMT_METH_CD));
            ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtMethDescTxt, (String) rtn.get(VND_PMT_METH_DESC_TXT));
            // START 2018/10/12 [QC#28226, DEL]
            // ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermCd, (String) rtn.get(VND_PMT_TERM_CD));
            // ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt, (String) rtn.get(VND_PMT_TERM_DESC_TXT));
            // END   2018/10/12 [QC#28226, DEL]
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PA, (String) rtn.get(PAY_ALONE_FLG));
            // START 2018/10/12 [QC#28226, DEL]
            // doProcess_NFBL2040Scrn00_OnChange_PmtTerm(bizMsg, sMsg);
            // END   2018/10/12 [QC#28226, DEL]
        } else if (ssmResult != null && ssmResult.isCodeNotFound()) {
            bizMsg.vndPmtMethCd.clear();
            bizMsg.vndPmtMethDescTxt.clear();
            bizMsg.vndPmtTermCd.clear();
            bizMsg.vndPmtTermDescTxt.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PA, ZYPConstant.FLG_OFF_N);
            bizMsg.termNetDueDt.clear();
        }
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_Location================================END", this);
    }    /**
     * Method name: doProcess_NFBL2040Scrn00_NFBL2040_NWAL1130
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_NFBL2040_NWAL1130(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_NFBL2040_NWAL1130================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        if ("OnClick_TermLink".equals(bizMsg.xxScrEventNm.getValue())) {
            doProcess_NFBL2040Scrn00_OnChange_PmtTerm(cMsg, sMsg);
        // START 2018/02/28 [QC#23505, ADD]
        } else if ("OpenWin_PurchaseOrder2".equals(bizMsg.xxScrEventNm.getValue())) {
            doProcess_NFBL2040Scrn00_OpenWin_PurchaseOrder2(cMsg, sMsg);
        // END   2018/02/28 [QC#23505, ADD]
        } else {
            doProcess_NFBL2040Scrn00_OnChange_Location(cMsg, sMsg);
        }
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_NFBL2040_NWAL1130================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_PmtTermDt
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OnChange_PmtTerm(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_PmtTerm================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        if (ZYPCommonFunc.hasValue(bizMsg.invDt)
                && ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt)) {
            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPmtTermDt(bizMsg);
            if (ssmResult != null && ssmResult.isCodeNormal()) {
                Map<String, Object> rtn = (Map<String, Object>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.termNetDueDt, (String) rtn.get(TERM_NET_DUE_DT));
            } else {
                bizMsg.termNetDueDt.clear();
            }
        } else {
            bizMsg.termNetDueDt.clear();
        }
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_PmtTerm================================END", this);
    }

    // START 2018/02/23 [QC#23505, ADD]

    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_PmtTermDt
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OpenWin_PurchaseOrder2(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OpenWin_PurchaseOrder2====================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // modify Header Tab & add records to Lines tab
        // START QC#25902,QC#25190,QC#25141
        boolean result = NFBL2040CommonLogic.addLineRecord(bizMsg, false);
        // END QC#25902,QC#25190,QC#25141
        if (!result) {
            return;
        }

        // set search condition items
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_HD, bizMsg.cmApInvTpCd_S);    // Invoice
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum_HT);               // PO#
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_HD, bizMsg.delyOrdNum_H);      // Receipt#
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_DH, bizMsg.rwsNum_H);              // RWS#

        // refresh Distribution Tab
        NFBL2040CommonLogic.commonRefreshDistributionsTab(bizMsg, USER_ID);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OpenWin_PurchaseOrder2====================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_PmtTermDt
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Correction(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Correction================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // set search condition items
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_HD, bizMsg.cmApInvTpCd_S);    // Invoice
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum_HT);               // PO#
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_HD, bizMsg.delyOrdNum_H);      // Receipt#
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_DH, bizMsg.rwsNum_H);              // RWS#
        // START QC#25902,QC#25190,QC#25141
        // mod start 2022/02/15 QC#57090
        //ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum_H);      // Vnedor Return#
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum);      // Vnedor Return#
        // mod end 2022/02/15 QC#57090
        // Start 2023/1/17 S.Nakatani [QC#60812, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && !bizMsg.apVndInvNum_OT.getValue().equals(bizMsg.apVndInvNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
        }
        // End 2023/1/17 S.Nakatani [QC#60812, ADD]
        createInitialCorrectionInvoice(bizMsg, false);
        // END QC#25902,QC#25190,QC#25141

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Correction================================END", this);
    }

    // MOD QC#25902,QC#25190,QC#25141
    private boolean createInitialCorrectionInvoice(NFBL2040CMsg bizMsg, boolean isLine) {
        boolean rtnVal = true;

        // backup ApInvSqNum
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvSqNum_BK, bizMsg.apVndInvSqNum);

        if (isLine) {

            // Add Credit Invoice Line (minus line)
            NFBL2040CommonLogic.addCreditLines(bizMsg, true);

            // Copy Original Invoice Line (modify Line)
            NFBL2040CommonLogic.addCopiedOriginalLines(bizMsg, true);

        } else {
            // START 2020/02/17 [QC#53413, MOD]
            //// Check PO status
//            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT) && !bizMsg.poNum_HT.getValue().equals(bizMsg.poNum.getValue())) {
//                if (!NFBL2040CommonLogic.checkPoSts(bizMsg)) {
//                    return false;
//                }
//            }
            if (!checkPoSts(bizMsg)) {
                return false;
            }
            // END   2020/02/17 [QC#53413, MOD]

            // Add Credit Invoice Line (minus line)
            NFBL2040CommonLogic.addCreditLines(bizMsg, false);

            // Copy Original Invoice Line (modify Line)
            NFBL2040CommonLogic.addCopiedOriginalLines(bizMsg, false);
        }

        // set Checkbox
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CR, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_CR, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum, bizMsg.poNum_HD);

        return rtnVal;
    }

    // END   2018/02/23 [QC#23505, ADD]

    // START 2020/02/17 [QC#53413, ADD]
    /**
     * checkPoSts
     * @param bizMsg NFBL2040CMsg
     * @return boolean
     */
    private boolean checkPoSts(NFBL2040CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
            if (!bizMsg.poNum_HT.getValue().equals(bizMsg.poNum.getValue())) {
                if (!NFBL2040CommonLogic.checkPoSts(bizMsg)) {
                    return false;
                }
            }
        } else {
            List<String> poNumList = new ArrayList<String>();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    String poNum = bizMsg.A.no(i).poNum_A1.getValue();
                    if (!poNumList.contains(poNum)) {
                        poNumList.add(poNum);
                    }
                }
            }
            boolean stsFlg = true;
            for (int j = 0; j < poNumList.size(); j++) {
                String poNum = poNumList.get(j);
                if (poNum.equals(bizMsg.poNum.getValue())) {
                    stsFlg = false;
                    break;
                }
            }
            if (stsFlg) {
                for (int k = 0; k < poNumList.size(); k++) {
                    if (!NFBL2040CommonLogic.checkPoSts(bizMsg, poNumList.get(k))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    // END   2020/02/17 [QC#53413, ADD]
    
    // START 2018/03/13 [QC#24274, ADD]
    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_UnitPrc
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OnChange_UnitPrc(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_UnitPrc==========================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        NFBL2040CommonLogic.recalcLineAmount(bizMsg);
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_UnitPrc==========================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_BillQty
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OnChange_BillQty(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_BillQty==========================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        NFBL2040CommonLogic.recalcLineAmount(bizMsg);
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_BillQty==========================END", this);
    }
    
    /**
     * Method name: doProcess_NFBL2040Scrn00_OnChange_InvAmt
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_OnChange_InvAmt(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_InvAmt===========================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        NFBL2040CommonLogic.recalcLineAmount(bizMsg);
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_OnChange_InvAmt===========================END", this);
    }
    // END   2018/03/13 [QC#24274, ADD]

    /**
     * ADD QC#25902,QC#25190,QC#25141
     * Method name: doProcess_NFBL2040Scrn00_POLineCorrection
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_POLineCorrection(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Correction================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // set search condition items
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_HD, bizMsg.cmApInvTpCd_S);    // Invoice
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum_HT);               // PO#
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_HD, bizMsg.delyOrdNum_H);      // Receipt#
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_DH, bizMsg.rwsNum_H);              // RWS#
        // mod start 2022/02/15 QC#57090
        //ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum_H);      // Vnedor Return#
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum);      // Vnedor Return#
        // mod end 2022/02/15 QC#57090

        // Start 2023/1/17 S.Nakatani [QC#60812, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && !bizMsg.apVndInvNum_OT.getValue().equals(bizMsg.apVndInvNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
        }
        // End 2023/1/17 S.Nakatani [QC#60812, ADD]
        createInitialCorrectionInvoice(bizMsg, true);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Correction================================END", this);
    }

    // START 2018/11/29 [QC#28904, ADD]
    /**
     * Method name: doProcess_NFBL2040Scrn00_Regenerate_Invoice
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Regenerate_Invoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Recreate_Invoice==========================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2020/02/17 [QC#53413, ADD]
        String poNumH = null;
        List<String> poNumList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            poNumH = bizMsg.poNum.getValue();
        }
        if (ZYPCommonFunc.hasValue(poNumH)) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    String poNumLine = bizMsg.A.no(i).poNum_A1.getValue();
                    if (!poNumH.equals(poNumLine)) {
                        if (!poNumList.contains(poNumLine)) {
                            poNumList.add(poNumLine);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    String poNumLine = bizMsg.A.no(i).poNum_A1.getValue();
                    if (!poNumList.contains(poNumLine)) {
                        poNumList.add(poNumLine);
                    }
                }
            }
        }
        // END   2020/02/17 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        List<String> vndRtrnNumList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndRtrnNum_A1)) {
                String vndRtrnNum = bizMsg.A.no(i).vndRtrnNum_A1.getValue();
                if (!vndRtrnNumList.contains(vndRtrnNum)) {
                    vndRtrnNumList.add(vndRtrnNum);
                }
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
            String vndRtrnNum = bizMsg.vndRtrnNum.getValue();
            if (vndRtrnNumList.contains(vndRtrnNum)) {
                vndRtrnNumList.remove(vndRtrnNumList.indexOf(vndRtrnNum));
            }
        }
        // add end 2022/02/15 QC#57090
        
        // backup header values.
        String cmApInvTpCd = bizMsg.cmApInvTpCd_S.getValue();
        String poNum = null;
        String delyOrdNum = null;
        String rwsNum = null;
        String vndRtrnNum = null;

        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            poNum = bizMsg.poNum.getValue();
            delyOrdNum = bizMsg.delyOrdNum_H.getValue();
            rwsNum = bizMsg.rwsNum_H.getValue();

        } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())
                && ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            poNum = bizMsg.poNum.getValue();
            delyOrdNum = bizMsg.delyOrdNum_H.getValue();
            rwsNum = bizMsg.rwsNum_H.getValue();

        } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())
                && ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
            vndRtrnNum = bizMsg.vndRtrnNum.getValue();
            delyOrdNum = bizMsg.delyOrdNum_H.getValue();
        }

        // clear parmeter
        bizMsg.apVndCd_OT.clear();
        bizMsg.apVndInvNum_OT.clear();
        bizMsg.apVndInvSqNum_OT.clear();

        // Clear search condition
        NFBL2040CommonLogic.clearHiddenValue(bizMsg);
        // Clear search condition
        NFBL2040CommonLogic.clearSearchCondition(bizMsg);
        // Clear information on HEADER tab
        NFBL2040CommonLogic.clearHeader(bizMsg, ZYPConstant.FLG_OFF_N);
        // Clear information on HOLDS tab
        NFBL2040CommonLogic.clearHolds(bizMsg);
        // Clear information on LINES tab
        NFBL2040CommonLogic.clearLines(bizMsg);
        // Clear information on DISTRIBUTIONS tab
        NFBL2040CommonLogic.clearDistributions(bizMsg);

        // restore header values.
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_S, cmApInvTpCd);
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum, poNum);

        } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())
                && ZYPCommonFunc.hasValue(poNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum, poNum);

        } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())
                && ZYPCommonFunc.hasValue(vndRtrnNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum, vndRtrnNum);
        }

        // Get screen information.
        NFBL2040CommonLogic.getRecord(bizMsg);
        // START 2020/02/17 [QC#53413, ADD]
        for (int j = 0; j < poNumList.size(); j++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum_L, poNumList.get(j));
            // mod start 2022/02/15 QC#57090
            //NFBL2040CommonLogic.getRecordPoSearch(bizMsg);
            NFBL2040CommonLogic.getRecordLineSearch(bizMsg);
            // mod end 2022/02/15 QC#57090
            bizMsg.poNum_L.clear();
        }
        // END   2020/02/17 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        for (int j = 0; j < vndRtrnNumList.size(); j++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_L, vndRtrnNumList.get(j));
            NFBL2040CommonLogic.getRecordLineSearch(bizMsg);
            bizMsg.vndRtrnNum_L.clear();
        }
        // add end 2022/02/15 QC#57090
        
        // Keep search condition
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_HD, bizMsg.cmApInvTpCd_S.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_HD, bizMsg.delyOrdNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_HD, bizMsg.rwsNum_H.getValue());
        // mod start 2022/02/15 QC#57090
        //ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum.getValue());
        // mod end 2022/02/15 QC#57090

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Recreate_Invoice============================END", this);
    }
    // END   2018/11/29 [QC#28904, ADD]
    // START 2020/07/02 R.Kurahashi [QC#56696,ADD]
    /**
     * Method name: doProcess_NFBL2040Scrn00_Amount_Calc
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Amount_Calc(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Amount_Calc================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        
        doProcess_NFBL2040Scrn00_OnChange_PmtTerm(cMsg, sMsg);
        
        if (bizMsg.A.getValidCount() > 0) {
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NFBL2040CommonLogic.recalcLineAmount(bizMsg, i);
            }
        }


        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Amount_Calc================================END", this);
    }
    // END 2020/07/02 R.Kurahashi [QC#56696,ADD]
}
