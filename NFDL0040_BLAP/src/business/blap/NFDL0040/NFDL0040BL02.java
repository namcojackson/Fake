package business.blap.NFDL0040;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0040.common.NFDL0040CommonLogic;
import business.blap.NFDL0040.constant.NFDL0040Constant;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRMS_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/04/25   Fujitsu         C.Naito         Update          QC#6269-2
 * 2016/04/25   Fujitsu         C.Naito         Update          QC#6269-6
 * 2016/08/08   Hitachi         K.Kojima        Update          QC#13005
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#10279
 * 2016/08/26   Hitachi         K.Kojima        Update          QC#10786
 * 2016/10/28   Hitachi         E.Kameishi      Update          QC#14370
 * 2017/01/17   Fujitsu         H.Ikeda         Update          QC#10786
 * 2023/05/26   Hitachi         S.Nakatani      Update          QC#61271
 *</pre>
 */
public class NFDL0040BL02 extends S21BusinessHandler implements NFDL0040Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0040_INIT".equals(screenAplID)) {
                doProcess_NFDL0040_INIT(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_OnClick_TabPromise".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_OnClick_TabPromise(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_OnClick_TabDispute".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_OnClick_TabDispute(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_OnClick_PromiseAdd".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_OnClick_PromiseAdd(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_OnClick_PromiseApply".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_OnClick_PromiseApply(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_OnClick_DisputeAdd".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_OnClick_DisputeAdd(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFDL0040Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_PagePrev(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040_INIT================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;
        // START 2016/08/08 K.Kojima [QC#13005,ADD]
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.arTrxNum_H)) {
//          ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H, (BigDecimal) getArTrxBalData(bizMsg, globalMsg).get("DEAL_RMNG_BAL_GRS_AMT"));
//        }
        boolean isMultiInv = false;
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
            BigDecimal dealRmngBalGrsAmt = 
                (BigDecimal) NFDL0040CommonLogic.getArTrxBalData(getGlobalCompanyCode(), bizMsg, bizMsg.xxTrxNumSrchTxt.getValue()).get("DEAL_RMNG_BAL_GRS_AMT");
            ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H, dealRmngBalGrsAmt);
            isMultiInv = bizMsg.xxTrxNumSrchTxt.getValue().contains(",");
            if (isMultiInv) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dealCltPrmsAmt_AH, dealRmngBalGrsAmt);
                ZYPEZDItemValueSetter.setValue(bizMsg.dealCltDsptAmt_BH, dealRmngBalGrsAmt);
            }
        }
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
        // END 2016/08/08 K.Kojima [QC#13005,ADD]
        ZYPCodeDataUtil.createPulldownList("CLT_DSPT_RSN", bizMsg.cltDsptRsnCd_LC, bizMsg.cltDsptRsnDescTxt_LD, ":");
        // START 2016/08/26 K.Kojima [QC#10786,ADD]
        ZYPCodeDataUtil.createPulldownList("CLT_DSPT_STS", bizMsg.cltDsptStsCd_LC, bizMsg.cltDsptStsDescTxt_LD, ":");
        // END 2016/08/26 K.Kojima [QC#10786,ADD]
        setAccountName(bizMsg, globalMsg);

        if (!ZYPCommonFunc.hasValue(bizMsg.xxDplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_PROMISE);
        }
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
        // START 2016/10/28 E.Kameishi [QC#14370,MOD]
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_AH, ZYPConstant.FLG_ON_Y);
        // END 2016/10/28 E.Kameishi [QC#14370,MOD]
        if (isMultiInv) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_AH, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_AH, ZYPConstant.FLG_ON_Y);
        }
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        getPromise(bizMsg, globalMsg);
//        getDispute(bizMsg, globalMsg);
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
            if (isMultiInv) {
                getPromiseForMultiInv(bizMsg, globalMsg);
                getDisputeForMultiInv(bizMsg, globalMsg);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.arTrxNum_H, bizMsg.xxTrxNumSrchTxt.getValue());
                getPromise(bizMsg, globalMsg);
                getDispute(bizMsg, globalMsg);
            }
        } else {
            getPromise(bizMsg, globalMsg);
            getDispute(bizMsg, globalMsg);
        }
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
        // START 2016/08/09 K.Kojima [QC#10279,ADD]
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId("EXTNE193");
        if (funcIdList == null || funcIdList.isEmpty() || !funcIdList.contains("EXTNE193T020")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2016/08/09 K.Kojima [QC#10279,ADD]
        EZDDebugOutput.println(1, "doProcess_NFDL0040_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_OnClick_PromiseApply(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_PromiseApply================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;
        NFDL0040CommonLogic.copyPage(bizMsg, globalMsg);

        NFDL0040SMsg tmpMsg = new NFDL0040SMsg();
        EZDMsg.copy(globalMsg, null, tmpMsg, null);
        ZYPTableUtil.clear(globalMsg.A);
        getPromise(bizMsg, globalMsg);
        for (int i = 0; i < tmpMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(tmpMsg.A.no(i).dealCltPrmsAmt_AB) || !tmpMsg.A.no(i).dealCltPrmsAmt_A.getValue().equals(tmpMsg.A.no(i).dealCltPrmsAmt_AB)) {
                if (ZYPCommonFunc.hasValue(tmpMsg.A.no(i).cltPrmsDtlPk_A)) {
                    for (int j = 0; j < globalMsg.A.getValidCount(); j++) {
                        if (globalMsg.A.no(j).cltPrmsDtlPk_A.getValue().equals(tmpMsg.A.no(i).cltPrmsDtlPk_A.getValue())) {
                            EZDMsg.copy(tmpMsg.A.no(i), null, globalMsg.A.no(j), null);
                        }
                    }
                } else {
                    int no = globalMsg.A.getValidCount();
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).dealCltPrmsAmt_A, bizMsg.dealCltPrmsAmt_AH);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).cltPrmsDt_A, bizMsg.cltPrmsDt_AH);
                    globalMsg.A.setValidCount(no + 1);
                }
            }
        }
        NFDL0040CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_PromiseApply================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_CMN_Clear================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;
        doProcess_NFDL0040_INIT(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_CMN_Clear================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_OnClick_TabPromise(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_TabPromise================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_PROMISE);
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_TabPromise================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_OnClick_TabDispute(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_TabDispute================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DISPUTE);
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_TabDispute================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_CMN_Submit================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;
        if (bizMsg.getMessageKind().equals("E")) {
            return;
        }
        doProcess_NFDL0040_INIT(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_CMN_Submit================================END", this);
    }

    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getPromise(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("onAccount", STR_ON_ACCOUNT);
        ssmMap.put("cltPrmsStsCd", CLT_PRMS_STS.COLLECTIBLE);
        ssmMap.put("cMsg", bizMsg);
        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getPromise(ssmMap, globalMsg);
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {

                bizMsg.setMessageInfo("NZZM0001W");

                queryResCnt = globalMsg.A.length();
            }

            int i;
            for (i = 0; i < globalMsg.A.getValidCount(); i++) {

                if (i < bizMsg.A.length()) {

                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);

                } else {
                    break;
                }
            }

            bizMsg.A.setValidCount(i);
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);

        } else {
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(0);
        }
    }

    private void setAccountName(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("sellToCustCd01", bizMsg.dsAcctNum_H.getValue());
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        SELL_TO_CUSTTMsgArray outMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, outMsgArray.no(0).dsAcctNm);
            return;
        }
    }

    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getDispute(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getDispute(ssmMap, globalMsg);
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.B.length()) {

                bizMsg.setMessageInfo("NZZM0001W");

                queryResCnt = globalMsg.B.length();
            }

            int i;
            for (i = 0; i < globalMsg.B.getValidCount(); i++) {

                if (i < bizMsg.B.length()) {

                    EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);

                } else {
                    break;
                }
            }

            bizMsg.B.setValidCount(i);

            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);

        } else {
            bizMsg.B.setValidCount(0);
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(0);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_OnClick_PromiseAdd(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_PromiseAdd================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;
        NFDL0040CommonLogic.copyPage(bizMsg, globalMsg);

        int no = globalMsg.A.getValidCount();

        if (no == globalMsg.A.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[]{String.valueOf(globalMsg.A.length())});
            return;
        }

        int cnt = NFDL0040CommonLogic.getCollectablePromiseCount(getGlobalCompanyCode(), bizMsg, globalMsg);

        if (cnt > 0) {
            bizMsg.setMessageInfo("NFCM0767E", new String[]{});
            return;
        }

        // START 2017/01/17 H.Ikeda [QC#10786,ADD]
        if (chkPromise(bizMsg, globalMsg)) {
            bizMsg.setMessageInfo("NFCM0767E");
            return;
        }
        // END   2017/01/17 H.Ikeda [QC#10786,ADD]

        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).dealCltPrmsAmt_A, bizMsg.dealCltPrmsAmt_AH);
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).cltPrmsDt_A, bizMsg.cltPrmsDt_AH);
        // [QC#6269-2] UPDATE start
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//      if (ZYPCommonFunc.hasValue(bizMsg.arTrxNum_H)) {
//      Map resMap = getArTrxBalData(bizMsg, globalMsg);
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
            Map resMap = NFDL0040CommonLogic.getArTrxBalData(getGlobalCompanyCode(), bizMsg, bizMsg.xxTrxNumSrchTxt.getValue());
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
            if (resMap.containsKey("DEAL_RMNG_BAL_GRS_AMT")) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).dealRmngBalGrsAmt_A2, (BigDecimal) resMap.get("DEAL_RMNG_BAL_GRS_AMT"));
            } else {
                globalMsg.A.no(no).dealRmngBalGrsAmt_A2.setValue(BigDecimal.ZERO);
            }
            if (resMap.containsKey("FUNC_RMNG_BAL_GRS_AMT")) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).funcRmngBalGrsAmt_A2, (BigDecimal) resMap.get("FUNC_RMNG_BAL_GRS_AMT"));
            } else {
                globalMsg.A.no(no).funcRmngBalGrsAmt_A2.setValue(BigDecimal.ZERO);
            }
        }
        // [QC#6269-2] UPDATE end
        globalMsg.A.setValidCount(no + 1);
        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
            boolean isMultiInv = bizMsg.xxTrxNumSrchTxt.getValue().contains(",");
            if (!isMultiInv) {
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]
                bizMsg.dealCltPrmsAmt_AH.clear();
        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
            }
        }
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]
        bizMsg.cltPrmsDt_AH.clear();
        NFDL0040CommonLogic.lastPage(bizMsg, globalMsg);
        NFDL0040CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
        // [QC#6269-6] DELETE start
        //bizMsg.setMessageInfo("NZZM0002I");
        // [QC#6269-6] DELETE end
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_PromiseAdd================================END", this);
    }


    // START 2023/05/26 S.Nakatani [QC#61271,DEL]
//    private Map getArTrxBalData(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
//        // search arTrxBal(Invoice#) AR_TRX_BAL data
//        Map<String, Object> ssmMap = new HashMap<String, Object>();
//        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
//        ssmMap.put("cMsg", bizMsg);
//        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getArTrxBalData(ssmMap);
//        
//        if (ssmResult.isCodeNormal()) {
//            Map<BigDecimal, Object> arTrxBalResultMap = (Map<BigDecimal, Object>) ssmResult.getResultObject();
//            if (arTrxBalResultMap == null) {
//                bizMsg.xxTrxNumSrchTxt.setErrorInfo(1, "NFCM0807E", new String[] {"Invoice#", bizMsg.xxTrxNumSrchTxt.getValue(), "AR_TRX_BAL" });
//                return null;
//            }
//
//            Map <String, BigDecimal> resMap = new HashMap<String, BigDecimal>();
//            resMap.put("DEAL_RMNG_BAL_GRS_AMT", (BigDecimal) arTrxBalResultMap.get("DEAL_RMNG_BAL_GRS_AMT"));
//            resMap.put("FUNC_RMNG_BAL_GRS_AMT", (BigDecimal) arTrxBalResultMap.get("FUNC_RMNG_BAL_GRS_AMT"));
//            return resMap;
//
//        } else {
//            bizMsg.xxTrxNumSrchTxt.setErrorInfo(1, "NFCM0807E", new String[] {"Invoice#", bizMsg.xxTrxNumSrchTxt.getValue(), "AR_TRX_BAL" });
//            return null;
//        }
//    }
    // END 2023/05/26 S.Nakatani [QC#61271,DEL]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_OnClick_DisputeAdd(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_DisputeAdd================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;
        NFDL0040CommonLogic.copyPage(bizMsg, globalMsg);

        int no = globalMsg.B.getValidCount();

        if (no == globalMsg.B.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[]{String.valueOf(globalMsg.B.length())});
            return;
        }

        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).dealCltDsptAmt_B, bizMsg.dealCltDsptAmt_BH);
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).cltDsptRsnCd_B, bizMsg.cltDsptRsnCd_BH);

        //[QC#6269-2] UPDATE start
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.arTrxNum_H)) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
//          Map resMap = getArTrxBalData(bizMsg, globalMsg);
            Map resMap = NFDL0040CommonLogic.getArTrxBalData(getGlobalCompanyCode(), bizMsg, bizMsg.xxTrxNumSrchTxt.getValue());
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
            if (resMap.containsKey("DEAL_RMNG_BAL_GRS_AMT")) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).dealRmngBalGrsAmt_B,(BigDecimal) resMap.get("DEAL_RMNG_BAL_GRS_AMT"));
            } else {
                globalMsg.B.no(no).dealRmngBalGrsAmt_B.setValue(BigDecimal.ZERO);
            }
            if (resMap.containsKey("FUNC_RMNG_BAL_GRS_AMT")) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).funcRmngBalGrsAmt_B,(BigDecimal) resMap.get("FUNC_RMNG_BAL_GRS_AMT"));
            } else {
                globalMsg.B.no(no).funcRmngBalGrsAmt_B.setValue(BigDecimal.ZERO);
            }
        }
        //[QC#6269-2] UPDATE end
        
        globalMsg.B.setValidCount(no + 1);
        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
            boolean isMultiInv = bizMsg.xxTrxNumSrchTxt.getValue().contains(",");
            if (!isMultiInv) {
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]
                bizMsg.dealCltDsptAmt_BH.clear();
        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
            }
        }
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]
        bizMsg.cltDsptRsnCd_BH.clear();
        NFDL0040CommonLogic.lastPage(bizMsg, globalMsg);
        NFDL0040CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
        //[QC#6269-6] DELETE start
        //bizMsg.setMessageInfo("NZZM0002I");
        //[QC#6269-6] DELETE end
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_OnClick_DisputeAdd================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_PageNext================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;

        if (NFDL0040CommonLogic.copyPage(bizMsg, globalMsg)) {
            NFDL0040CommonLogic.nextPage(bizMsg, globalMsg);
        }
        NFDL0040CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0040Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_PagePrev================================START", this);
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;

        if (NFDL0040CommonLogic.copyPage(bizMsg, globalMsg)) {
            NFDL0040CommonLogic.prevPage(bizMsg, globalMsg);
        }
        NFDL0040CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_PagePrev================================END", this);
    }

    // START 2017/01/17 H.Ikeda [QC#10786,ADD]
    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private boolean chkPromise(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.arTrxNum_H)) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
            return false;
        } else {
            Set<String> set =  new HashSet<String>();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                set.add(bizMsg.A.no(i).arTrxNum_A.getValue());
            }
            if (1 < set.size()) {
                return true;
            }
        }
        return false;
    }
    // END   2017/01/17 H.Ikeda [QC#10786,ADD]

    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getPromiseForMultiInv(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        if (!"NFDL0040Scrn00_CMN_Submit".equals(bizMsg.getScreenAplID())) {
            globalMsg.A.clear();
            bizMsg.A.clear();
            globalMsg.A.setValidCount(0);
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(0);
            return;
        }

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("onAccount", STR_ON_ACCOUNT);
        ssmMap.put("cltPrmsStsCd", CLT_PRMS_STS.COLLECTIBLE);
        ssmMap.put("cMsg", bizMsg);
        List<String> arTrxNumList = NFDL0040CommonLogic.splitAndToInvList(bizMsg.xxTrxNumSrchTxt.getValue());
        ssmMap.put("arTrxNumList", arTrxNumList);
        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getPromiseForMultiInv(ssmMap, globalMsg);

        if (ssmResult.isCodeNormal()) {
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > globalMsg.A.length()) {
                    bizMsg.setMessageInfo("NZZM0001W");
                    queryResCnt = globalMsg.A.length();
                }
                int i;
                for (i = 0; i < globalMsg.A.getValidCount(); i++) {
                    if (i < bizMsg.A.length()) {
                        EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                    } else {
                        break;
                    }
                }
                bizMsg.A.setValidCount(i);
                bizMsg.xxPageShowFromNum_A.setValue(1);
                bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
                bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);
        } else {
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(0);
        }
    }

    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getDisputeForMultiInv(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        if (!"NFDL0040Scrn00_CMN_Submit".equals(bizMsg.getScreenAplID())) {
            globalMsg.B.clear();
            bizMsg.B.clear();
            globalMsg.B.setValidCount(0);
            bizMsg.B.setValidCount(0);
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(0);
            return;
        }

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        List<String> arTrxNumList = NFDL0040CommonLogic.splitAndToInvList(bizMsg.xxTrxNumSrchTxt.getValue());
        ssmMap.put("arTrxNumList", arTrxNumList);
        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getDisputeForMultiInv(ssmMap, globalMsg);

        if (ssmResult.isCodeNormal()) {
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > globalMsg.B.length()) {
                    bizMsg.setMessageInfo("NZZM0001W");
                    queryResCnt = globalMsg.B.length();
                }
                int i;
                for (i = 0; i < globalMsg.B.getValidCount(); i++) {
                    if (i < bizMsg.B.length()) {
                        EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
                    } else {
                        break;
                    }
                }
                bizMsg.B.setValidCount(i);
                bizMsg.xxPageShowFromNum_B.setValue(1);
                bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
                bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);
        } else {
            bizMsg.B.setValidCount(0);
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(0);
        }
    }
    // END 2023/05/26 S.Nakatani [QC#61271,ADD]
}
