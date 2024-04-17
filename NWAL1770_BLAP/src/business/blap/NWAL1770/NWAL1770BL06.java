/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0973E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0753W;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM8472W;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1770.common.NWAL1770CommonLogic;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForAttachment;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForItemLine;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForReport;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForSaveSubmit;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         T.Yoshida       Create          N/A
 * 2016/09/21   Fujitsu         H.Ikeda         Update          S21_NA#14578
 * 2016/09/27   Fujitsu         H.Ikeda         Update          S21_NA#13914
 * 2017/08/17   Fujitsu         S.Takami        Update          S21_NA#20659
 * 2017/09/28   Fujitsu         T.Murai         Update          S21_NA#21121
 * 2017/10/19   Hitachi         J.Kim           Update          QC#21157
 * 2018/07/19   Fujitsu         M.Ishii         Update          S21_NA#26153
 * 2018/07/24   Fujitsu         T.Aoi           Update          S21_NA#26274
 * 2019/10/03   Fujitsu         A.Kazuki        Update          QC#53595
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2024/04/03   CITS            A.Shimada       Update          CSA-QC#63691
 * </pre>
 */
public class NWAL1770BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
            NWAL1770SMsg glblMsg = (NWAL1770SMsg) sMsg;

            if ("NWAL1770Scrn00_CMN_Save".equals(screenAplID)) {
                // 2018/07/19 QC#26153 Mod Start
//                doProcess_NWAL1770Scrn00_CMN_Save(bizMsg, glblMsg);
                doProcess_NWAL1770Scrn00_CMN_Save(bizMsg, glblMsg, false);
                // 2018/07/19 QC#26153 Mod End
            } else if ("NWAL1770Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_CMN_Submit(bizMsg, glblMsg, false, false);
            } else if ("NWAL1770_NWAL1790".equals(screenAplID)) {
                doProcess_NWAL1770_NWAL1790(bizMsg);
            } else if ("NWAL1770Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NWAL1770Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            // Add Start 2017/09/28 S21_NA#21121
            } else if ("NWAL1770Scrn00_MoveWin_CreditCard".equals(screenAplID)) {
                // 2018/07/19 QC#26153 Mod Start
//                doProcess_NWAL1770Scrn00_CMN_Save(bizMsg, glblMsg);
                doProcess_NWAL1770Scrn00_CMN_Save(bizMsg, glblMsg, false);
                // 2018/07/19 QC#26153 Mod End
            // Add End 2017/09/28 S21_NA#21121
            // 2018/07/19 QC#26153 Add Start
            } else if ("NWAL1770Scrn00_OpenWin_Profitability".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_Profitability(bizMsg, glblMsg);
            // 2018/07/19 QC#26153 Add End
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * do Process (CMN_Save)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    // 2018/07/19 QC#26153 Mod Start
//    private void doProcess_NWAL1770Scrn00_CMN_Save(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {
//        doProcess_NWAL1770Scrn00_CMN_Submit(bizMsg, glblMsg, true);
    private void doProcess_NWAL1770Scrn00_CMN_Save(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallProfitability) {
        doProcess_NWAL1770Scrn00_CMN_Submit(bizMsg, glblMsg, true, isCallProfitability);
    // 2018/07/19 QC#26153 Mod End
    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @param isCallSave Called Save Button
     */
    // 2018/07/19 QC#26153 Mod Start
//    private void doProcess_NWAL1770Scrn00_CMN_Submit(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallSave) {
    private void doProcess_NWAL1770Scrn00_CMN_Submit(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallSave, boolean isCallProfitability) {
    // 2018/07/19 QC#26153 Mod End

        // 2018/07/24 S21_NA#26274 Del Start
        //NWAL1770CommonLogic.numberingQuoteLineNumber(bizMsg);
        // 2018/07/24 S21_NA#26274 Del End

        if (!NWAL1770CommonLogicForSaveSubmit.checkAndClearCode(bizMsg)) {
            return;
        }

        NWAL1770CommonLogicForSaveSubmit.prepareSetToConponent(bizMsg);

        // 2017/08/17 S21_NA#20659 Add Start
        if (isCallSave) {
            isHeaderSave(bizMsg);
        } else {
            if (bizMsg.B.getValidCount() == 0) {
                //START 2024/04/03 [CSA-QC#63691,DEL]
//                NWAL1770CommonLogic.createEmptyDetaiLine(bizMsg);
                //END 2024/04/03 [CSA-QC#63691,DEL]
                //START 2024/04/03 [CSA-QC#63691,ADD]
                NWAL1770CommonLogic.createBlankDetailLine(bizMsg);
                //END 2024/04/03 [CSA-QC#63691,ADD]
            }
        }
        // 2017/08/17 S21_NA#20659 Add End

        // Check Mandatory
        // 2016/09/27 S21_NA#13914 Mod Start
        if (!NWAL1770CommonLogicForSaveSubmit.checkMandatoryField(bizMsg, isCallSave)) {
        //if (!NWAL1770CommonLogicForSaveSubmit.checkMandatoryField(bizMsg)) {
        // 2016/09/27 S21_NA#13914 Mod End
            return;
        }

        // Check Rerun Price $ Event
        if (!isCallSave && NWAL1770CommonLogicForSaveSubmit.needRePricing(bizMsg, glblMsg)) {
            return;
        }

        // Check Duplication PO Number
        String custIssPoNum = bizMsg.custIssPoNum.getValue();
        if (ZYPCommonFunc.hasValue(custIssPoNum) && ZYPCommonFunc.hasValue(bizMsg.custIssPoNum_BK)) {
            if (!custIssPoNum.equals(bizMsg.custIssPoNum_BK.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_PO, ZYPConstant.FLG_OFF_N);
            }
        }
        // Add Start 2016/09/21 S21_NA#14578
        // Check Sales Credit Info
        if (!NWAL1770CommonLogicForSaveSubmit.checkSlsCrInfo(bizMsg)) {
            return;
        }
        // Add End 2016/09/21 S21_NA#14578
        if (ZYPCommonFunc.hasValue(custIssPoNum) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_PO.getValue())) {
            if (NWAL1770CommonLogicForSaveSubmit.isDuplicationPoNum(bizMsg, isCallSave)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_BK, custIssPoNum);
        }

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        if (!isCallSave && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_SC.getValue())) {
            boolean checkResult = NWAL1770CommonLogic.checkSupplyCoveredContractInfo(bizMsg);
            if (checkResult) {
                bizMsg.setMessageInfo(NWAM8472W);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_SC, ZYPConstant.FLG_ON_Y);
                return;
            }
        }
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        // Confirm Sales Order Create
        if (!isCallSave && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_SM.getValue())) {
            bizMsg.setMessageInfo(NWAM0753W);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_SM, ZYPConstant.FLG_ON_Y);
            return;
        }

        // START 2017/10/17 J.Kim [QC#21157,DEL]
        //// Call Default WH API
        //if (!isCallSave && !NWAL1770CommonLogicForSaveSubmit.deriveDefaultWh(bizMsg)) {
        //    return;
        //}
        // END 2017/10/17 J.Kim [QC#21157,DEL]

        // 2019/10/03 QC#53595 Add Start
        if (!NWAL1770CommonLogicForSaveSubmit.checkISOrderBindToISGroup(bizMsg)) {
            bizMsg.setMessageInfo(NWAM0973E);
            bizMsg.psnNum.setErrorInfo(1, NWAM0973E);
            return;
        }
        // 2019/10/03 QC#53595 Add End

        // ADD START 2022/08/19 H.Watanabe [QC#60255]
        if (!NWAL1770CommonLogicForSaveSubmit.checkCarrAcctNumValidation(bizMsg)) {
            return;
        }
        // ADD END   2022/08/19 H.Watanabe [QC#60255]

        // Check Exclusion
        if (NWAL1770CommonLogicForSaveSubmit.isModifiedByOtherUser(bizMsg, glblMsg)) {
            return;
        }

        // Record Lock
        if (!NWAL1770CommonLogicForSaveSubmit.executeRecordLock(bizMsg)) {
            return;
        }

        // 2023/11/06 QC#61924 Add Start
        if (!NWAL1770CommonLogicForSaveSubmit.hasDeactivateAccountOrLocation(bizMsg, glblMsg)) {
            return;
        }
        // 2023/11/06 QC#61924 Add End

        // Call Supply Quote Update API
        // 2018/07/19 QC#26153 Mod Start
//        NWAL1770CommonLogicForSaveSubmit.callSplyQuoteUpdateApi(bizMsg, glblMsg, isCallSave);
        // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
        boolean result = NWAL1770CommonLogicForSaveSubmit.callSplyQuoteUpdateApi(bizMsg, glblMsg, isCallSave, isCallProfitability);
        if (result && !isCallSave && !isCallProfitability) {
            S21UserInfo userInfo = this.getContextUserInfo();
            String usrId = userInfo.getUserId();
            NWAL1770CommonLogicForAttachment.copyAttachmentRefToOrder(bizMsg, usrId);
        }
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
        // 2018/07/19 QC#26153 Mod End
    }

    /**
     * do Process (NWAL1770_NWAL1790)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NWAL1790(NWAL1770CMsg bizMsg) {

        // Call Report Create API
        if ("OpenWin_Confirmation".equals(bizMsg.xxScrEventNm.getValue())) {
            if (!NWAL1770CommonLogicForReport.callSplyQuoteReportCratApi(bizMsg)) {
                return;
            }
        } else {
            if (!NWAL1770CommonLogicForReport.callSplyTrackReportCratApi(bizMsg)) {
                return;
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg_ML.getValue())) {
            // Execute EIP Process (Send Email)
            NWAL1770CommonLogicForReport.executeEipProcessForSendMail(bizMsg);

            // Create Confirmation Output Pulldown
            if ("OpenWin_Confirmation".equals(bizMsg.xxScrEventNm.getValue())) {
                if (!NWAL1770CommonLogicForReport.insertReportOutputLog(bizMsg)) {
                    return;
                }

                NWAL1770CommonLogicForReport.createConfOutputPulldown(bizMsg);
            }
        }
    }
    
    // 2018/07/19 QC#26153 Add Start
    /**
     * do Process (NWAL1770_OpenWin_Profitability)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_Profitability(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        if (bizMsg.cpoOrdNum.getValue().equals("")) {
            doProcess_NWAL1770Scrn00_CMN_Save(bizMsg, glblMsg, true);
        }
        NWAL1770CommonLogicForItemLine.quote_search(bizMsg, glblMsg, false, glblCmpyCd);
        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkSupplyCoveredContractInfo(bizMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    }
    // 2018/07/19 QC#26153 Add End

    // 2017/08/17 S21_NA#20659 Add Start
    /**
     * <pre>
     * Detail Check, there is no Item Code Set up Line, clear all details, and Header Mode.
     * @param bizMsg Business Message
     * @return true: header mode, clear detail data. false: there are detail lines.
     * </pre>
     */
    private boolean isHeaderSave(NWAL1770CMsg bizMsg) {

        boolean isHeaderSave = false;
        // Detail Check, there is no Item Code Set up Line, clear all details, and Header Mode.
        boolean isItemExists = false;
        for (int i = 0; i < bizMsg.B.getValidCount(); i ++) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).mdseCd_B)) {
                isItemExists = true;
                break;
            }
        }
        if (!isItemExists) {
            isHeaderSave = true;
            bizMsg.B.clear();
            bizMsg.B.setValidCount(0);
        }
        return isHeaderSave;
    }
    // 2017/08/17 S21_NA#20659 Add End
}
