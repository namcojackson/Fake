/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2460;

import static business.blap.NMAL2460.constant.NMAL2460Constant.SCREEN_ID;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM0176E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8333I;
import static business.blap.NMAL2460.constant.NMAL2460Constant.ZZM9037E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.ZZM9000E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM0182E;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2460.common.NMAL2460CommonLogic;
import business.db.ACCT_TRTY_RESRC_RQST_DTLTMsg;
import business.db.ACCT_TRTY_RESRC_RQST_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Account Owner Lookup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         O.Okuma         Create          N/A
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 2017/08/29   Fujitsu         H.Sugawara      Update          QC#20826
 * 2017/09/13   Fujitsu         W.Honda         Update          S21_NA#21044
 * 2017/11/27   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 * 2017/11/28   Fujitsu         Hd.Sugawara     Update          QC#21044
 * 2018/03/20   Fujitsu         K.Ishizuka      Update          QC#22744
 *</pre>
 */
public class NMAL2460BL06 extends S21BusinessHandler {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NMAL2460CMsg cMsg = (NMAL2460CMsg) arg0;
        NMAL2460SMsg sMsg = (NMAL2460SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NMAL2460Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SaveSearch(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcessNMAL2460Scrn00_DeleteSearch(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NMAL2460Scrn00_CMN_Submit
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_CMN_Submit(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        // copy cMsg to sMsg;
        NMAL2460CommonLogic.setPagenation(cMsg, sMsg);

        String glblCmpyCd = getGlobalCompanyCode();
        String usrId = getContextUserInfo().getUserId();
        boolean isSuccess = true;
        boolean isChange = false;
        List<ACCT_TRTY_RESRC_RQST_HDRTMsg> insHdrTMsgList = new ArrayList<ACCT_TRTY_RESRC_RQST_HDRTMsg>();
        List<ACCT_TRTY_RESRC_RQST_DTLTMsg> insDtlTMsgList = new ArrayList<ACCT_TRTY_RESRC_RQST_DTLTMsg>();
        BigDecimal hdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_RESRC_RQST_HDR_SQ);
        // Add Start 2017/08/29 QC#20826
        int frstErrNum = -1;
        // Add End 2017/08/29 QC#20826
        // 2017/11/27 CSA-QC#20597 Del Start
        // Add Start 2017/09/13 QC#21044
        // List<TRTY_RULETMsg> delTrtyRuleList = new ArrayList<TRTY_RULETMsg>();
        // List<TRTY_RULETMsg> addTrtyRuleList = new ArrayList<TRTY_RULETMsg>();
        // Map<String, NMZC270001PMsg> ruleValidationPMsgList = new HashMap<String, NMZC270001PMsg>();
        // Add End 2017/03/13 QC#21044
        // 2017/11/27 CSA-QC#20597 Del End
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NMAL2460_BSMsg bsMsg = sMsg.B.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(bsMsg.xxValUpdFlg_B.getValue())) {
                continue;
            }

            if (NMAL2460CommonLogic.hasChangeTeritoryAttribute(bsMsg)) {
                // Set delete to Territory
                NMAL2460CommonLogic.setTerritoryDelete(bsMsg);

                // Check Master : Changed Territory Attributes
                // Mod Start 2017/09/13 QC#21044
//                if (!NMAL2460CommonLogic.checkChangeTeritoryAttribute(bsMsg, getGlobalCompanyCode(), isSuccess)) {
                // 2017/11/27 CSA-QC#20597 Mod Start
                // if (!NMAL2460CommonLogic.checkChangeTeritoryAttribute(bsMsg, getGlobalCompanyCode(), isSuccess, delTrtyRuleList, addTrtyRuleList, ruleValidationPMsgList)) {
                // Mod Start 2017/11/28 QC#21044
                //if (!NMAL2460CommonLogic.checkChangeTeritoryAttribute(bsMsg, getGlobalCompanyCode(), isSuccess)) {
                if (!NMAL2460CommonLogic.checkChangeTeritoryAttribute(cMsg, bsMsg, getGlobalCompanyCode(), isSuccess)) {
                    // Mod End 2017/11/28 QC#21044
                // 2017/11/27 CSA-QC#20597 Mod End
                // Mod End 2017/09/13 QC#21044
                    isSuccess = false;

                    // Add Start 2017/08/29 QC#20826
                    if(frstErrNum < 0) {
                        frstErrNum = i;
                    }
                    // Add End 2017/08/29 QC#20826

                    continue;
                }

            } else if (!NMAL2460CommonLogic.isChanged(bsMsg.manEntryFlg_BO.getValue(), bsMsg.manEntryFlg_B.getValue(), true)) {
                // Skip if there is no change in record
                continue;
            }

            // return Error
            if (!isSuccess) {
                if (i == sMsg.B.getValidCount() || (i != 0 && i % cMsg.B.length() == 0)) {
                    clearUpldFlg(sMsg); // 2018/03/20 S21_NA#22744 Add
                    // Mod Start 2017/08/29 QC#20826
                    //cMsg.xxPageShowCurNum_B.setValue(i / cMsg.B.length());
                    int pageNum = getFirstErrorPageNumber(cMsg, frstErrNum);
                    cMsg.xxPageShowCurNum_B.setValue(pageNum);
                    // Mod End 2017/08/29 QC#20826
                    cMsg.setMessageInfo(ZZM9037E);
                    NMAL2460CommonLogic.pagenationForJump(cMsg, sMsg);
                    return;
                } else {
                    continue;
                }
            }

            // 2017/11/27 CSA-QC#20597 Del Start
            // Add Start 2017/09/13 QC#21044
            // Update TRTY_RULE
//            if (!delTrtyRuleList.isEmpty()) {
//                int updListCnt = delTrtyRuleList.size();
//                int updCnt = S21FastTBLAccessor.update(delTrtyRuleList.toArray(new TRTY_RULETMsg[0]));
//                if (updCnt != updListCnt) {
//                    cMsg.setMessageInfo(NMAM0176E, new String[] {"ACCT_TRTY_RESRC_RQST_HDR" });
//                    return;
//                }
//            }
//            // Insert TRTY_RULE
//            if (!addTrtyRuleList.isEmpty()) {
//                int insListCnt = addTrtyRuleList.size();
//                int insCnt = S21FastTBLAccessor.insert(addTrtyRuleList.toArray(new TRTY_RULETMsg[0]));
//                if (insCnt != insListCnt) {
//                    cMsg.setMessageInfo(NMAM0176E, new String[] {"ACCT_TRTY_RESRC_RQST_HDR" });
//                    return;
//                }
//            }
//
//            if (!NMAL2460CommonLogic.callNMZC270001TrtyRuleValidationAPI(ruleValidationPMsgList, bsMsg)) {
//                EZDConnectionMgr.getInstance().rollback();
//                isSuccess = false;
//
//                if(frstErrNum < 0) {
//                    frstErrNum = i;
//                }
//                continue;
//            }
//            EZDConnectionMgr.getInstance().rollback();
            // Add End 2017/09/13 QC#21044
            // 2017/11/27 CSA-QC#20597 Del End

            // set TMsg for insert
            insDtlTMsgList = NMAL2460CommonLogic.setInsertAcctTrtyResrcRqstDtl(insDtlTMsgList, bsMsg, hdrSq, cMsg.rqstRsltCmntTxt.getValue(), glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bsMsg.xxValUpdFlg_B, ZYPConstant.FLG_ON_Y);
            isChange = true;
        }

        // return if has error
        if (!isSuccess) {
            clearUpldFlg(sMsg); // 2018/03/20 S21_NA#22744 Add
            // Mod Start 2017/08/29 QC#20826
            //cMsg.xxPageShowCurNum_B.setValue(cMsg.B.length() / sMsg.B.getValidCount());
            int pageNum = getFirstErrorPageNumber(cMsg, frstErrNum);
            cMsg.xxPageShowCurNum_B.setValue(pageNum);
            // Mod End 2017/08/29 QC#20826

            cMsg.setMessageInfo(ZZM9037E);
            NMAL2460CommonLogic.pagenationForJump(cMsg, sMsg);
            return;
        }

        // return if has no change
        if (!isChange) {
            cMsg.setMessageInfo(NMAM8333I);
            return;
        }

        insHdrTMsgList = NMAL2460CommonLogic.setInsertAcctTrtyResrcRqstHdr(insHdrTMsgList, glblCmpyCd, hdrSq, usrId);

        // insert ACCT_TRTY_RESRC_RQST_HDR
        if (!insHdrTMsgList.isEmpty()) {
            int insListCnt = insHdrTMsgList.size();
            int insCnt = S21FastTBLAccessor.insert(insHdrTMsgList.toArray(new ACCT_TRTY_RESRC_RQST_HDRTMsg[0]));
            if (insCnt != insListCnt) {
                clearUpldFlg(sMsg); // 2018/03/20 S21_NA#22744 Add
                cMsg.setMessageInfo(NMAM0176E, new String[] {"ACCT_TRTY_RESRC_RQST_HDR" });
                return;
            }
        }

        // insert ACCT_TRTY_RESRC_RQST_DTL
        if (!insDtlTMsgList.isEmpty()) {
            int insListCnt = insDtlTMsgList.size();
            int insCnt = S21FastTBLAccessor.insert(insDtlTMsgList.toArray(new ACCT_TRTY_RESRC_RQST_DTLTMsg[0]));
            if (insCnt != insListCnt) {
                clearUpldFlg(sMsg); // 2018/03/20 S21_NA#22744 Add
                cMsg.setMessageInfo(NMAM0176E, new String[] {"ACCT_TRTY_RESRC_RQST_DTL" });
            }
        }
    }
    
    // Mod Start 2017/08/29 QC#20826
    /**
     * getFirstErrorPageNumber
     * @param cMsg NMAL2460CMsg
     * @param frstErrNum int
     * @return int
     */
    private int getFirstErrorPageNumber(NMAL2460CMsg cMsg, int frstErrNum) {
        int lineNum = frstErrNum + 1;
        int pageNum = lineNum / cMsg.B.length();

        if((lineNum % cMsg.B.length()) > 0){
            pageNum++;
        }

        return pageNum;
    }
    // Mod End 2017/08/29 QC#20826
    
    // 2018/03/20 S21_NA#22744 Add Start
    private void clearUpldFlg(NMAL2460SMsg sMsg){
        for(int i = 0; i < sMsg.B.getValidCount(); i++){
            NMAL2460_BSMsg bsMsg = sMsg.B.no(i);
            bsMsg.xxValUpdFlg_B.clear();
        }
    }
    // 2018/03/20 S21_NA#22744 Add End

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2460Scrn00_SaveSearch(NMAL2460CMsg bizMsg, NMAL2460SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Search Option Name") });
            return;
        }
        if (NMAL2460CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM0182E // You can not [@] this record Because of [@] already exists.
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCREEN_ID, "Save") //
                            , converter.convLabel2i18nLabel(SCREEN_ID, "Search Option Name") });
            return;
        }

        NMAL2460CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

    }

    /**
     * doProcessNMAL2460Scrn00_DeleteSearch
     * @param bizMsg
     * @param sMsg
     */
    private void doProcessNMAL2460Scrn00_DeleteSearch(NMAL2460CMsg bizMsg, NMAL2460SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, ZZM9000E // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Saved Search Options") });
            return;
        }

        NMAL2460CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
    
    
}
