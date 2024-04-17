package business.blap.NFCL0730;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL0730.common.NFCL0730CommonLogic;
import business.blap.NFCL0730.constant.NFCL0730Constant;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJ_TPTMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Fujitsu         T.Tanaka        Create          Initial
 * 04/13/2016   CSAI            K.Uramori       Update          QC#7012
 * 2016/06/21   Hitachi         K.Kojima        Update          QC#10214
 * 2016/09/07   Hitachi         K.Kojima        Update          QC#13509
 * 2016/09/13   Hitachi         K.Kasai         Update          QC#13898
 * 2018/01/17   Fujitsu         T.Murai         Update          QC#20563
 * 2022/11/10   Hitachi         S.Naya          Update          QC#57252
 * 2023/01/27   Hitachi         T.Kuroda        Update          QC#61089
 *</pre>
 */
public class NFCL0730BL02 extends S21BusinessHandler implements NFCL0730Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL0730_INIT".equals(screenAplID)) {
                doProcess_NFCL0730_INIT(cMsg, sMsg);

            } else if ("NFCL0730Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_CMN_Clear(cMsg, sMsg);

            } else if ("NFCL0730Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_AddLine(cMsg, sMsg);

            } else if ("NFCL0730Scrn00_DeleteLines".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_DeleteLines(cMsg, sMsg);

            } else if ("NFCL0730Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NFCL0730Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_CMN_Return(cMsg, sMsg);

            } else if ("NFCL0730Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_CMN_Download(cMsg, sMsg);

            // START 2022/11/10 S.Naya [QC#57252,ADD]
            } else if ("NFCL0730Scrn00_OpenWin_ChargeAccount".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_OpenWin_ChargeAccount(cMsg, sMsg);
            // END   2022/11/10 S.Naya [QC#57252,ADD]
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
    private void doProcess_NFCL0730_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;

        if(!ZYPCommonFunc.hasValue(bizMsg.arTrxNum_H1.getValue())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return;
        }

        // Global Company Code
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // Standard Currency Code
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        glblCmpyTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);
        bizMsg.stdCcyCd.setValue(glblCmpyTMsg.stdCcyCd.getValue());

        //---- start 2016/03/10 move to common function
        // Target AR_TRX_BAL
        /*
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("arTrxNum01", bizMsg.arTrxNum_H1.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        
        AR_TRX_BALTMsgArray arTrxBalTMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(arTrxBalTMsg == null) {
            bizMsg.setMessageInfo("NFCM0041E");
            return;
        }
        if(arTrxBalTMsg.getValidCount() < 1) {
            bizMsg.setMessageInfo("NFCM0041E");
            return;
        }
        
        ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H1, arTrxBalTMsg.no(0).dealRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H2, arTrxBalTMsg.no(0).dealRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.arCashApplyStsCd_H2, arTrxBalTMsg.no(0).arCashApplyStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H2, arTrxBalTMsg.no(0).ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H2, arTrxBalTMsg.no(0).ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_H2, arTrxBalTMsg.no(0).billToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_H2, arTrxBalTMsg.no(0).billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxTpCd_H2, arTrxBalTMsg.no(0).arTrxTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.tocCd_H2, arTrxBalTMsg.no(0).tocCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H2, arTrxBalTMsg.no(0).coaProdCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxBalPk_H1, arTrxBalTMsg.no(0).arTrxBalPk.getValue());
        */
        //---- end 2016/03/10
        
        // Sales Date
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt,ZYPDateUtil.getSalesDate());
        
        // AR Account Date
        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", bizMsg.glblCmpyCd.getValue());
        if (subSysCd == null) {
            // START 2016/06/21 K.Kojima [QC#10214,ADD]
            // bizMsg.setMessageInfo("NFCM0041E");
            bizMsg.setMessageInfo(NFCM0845E, new String[] {"VAR_CHAR_CONST", "AR_SUB_SYS_ID" });
            // END 2016/06/21 K.Kojima [QC#10214,ADD]
            return;
        }
        AR_ACCT_DTTMsg arAcctDtTMsg = new AR_ACCT_DTTMsg();
        arAcctDtTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        arAcctDtTMsg.subSysCd.setValue(subSysCd);
        arAcctDtTMsg.onlBatTpCd.setValue("1");
        arAcctDtTMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(arAcctDtTMsg);
        if(arAcctDtTMsg == null) {
            // START 2016/06/21 K.Kojima [QC#10214,ADD]
            // bizMsg.setMessageInfo("NFCM0041E");
            bizMsg.setMessageInfo(NFCM0845E, new String[] {"AR_ACCT_DT", subSysCd });
            // END 2016/06/21 K.Kojima [QC#10214,ADD]
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.acctDt_H2, arAcctDtTMsg.acctDt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.acctYrMth_H2, arAcctDtTMsg.acctYrMth.getValue());
        
        // Activity (AR_ADJ_TP) Pull down
//        ZYPCodeDataUtil.createPulldownList(AR_ADJ_TP.class, bizMsg.arAdjTpCd_LC, bizMsg.arAdjTpNm_LD, ":");
        //---- start 2016/04/13 QC#7012 AR_ADJ_TP needs to be conditioned by category 
        //ZYPCodeDataUtil.createPulldownList(AR_ADJ_TP.class, bizMsg.arAdjTpCd_LC, bizMsg.arAdjTpDescTxt_LD);
        setADJType(bizMsg);
        
        //---- end 2016/04/13
        
        

        //---- start 2016/03/10 move to common function
        // AR_CASH_APP
        /*Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("arTrxNum", bizMsg.arTrxNum_H1.getValue());
        ssmParam.put("arApplytpCd", AR_APPLY_TP.ADJUSTMENT);
        
        S21SsmEZDResult ssmResult = NFCL0730Query.getInstance().getDetails(bizMsg, ssmParam);
        
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > bizMsg.B.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.B.length();
            }
            int i;
            for (i = 0; i < bizMsg.B.getValidCount(); i++) {
                if(i < bizMsg.A.length()) {
                    EZDMsg.copy(bizMsg.B.no(i), "B1", bizMsg.A.no(i), "A1");
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(i);
        } else {
            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.A.setValidCount(0);
        }
        */
        //---- end 2016/03/10
        
        if (!NFCL0730CommonLogic.searchHeader(bizMsg)) {
            return;
        }
        NFCL0730CommonLogic.searchDetail(bizMsg);
        
    }

    //---- start add 2016/04/13 QC#7012
    @SuppressWarnings("unchecked")
    private void setADJType(NFCL0730CMsg bizMsg) {

        try {

            // START 2023/01/27 T.Kuroda [QC#61089, MOD]
            S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
            List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NFCL0730Constant.BIZ_ID);

            // S21SsmEZDResult arAdjTpTMsgArray = NFCL0730CommonLogic.findArAdjTpList(this.getGlobalCompanyCode());
            S21SsmEZDResult arAdjTpTMsgArray = NFCL0730CommonLogic.findArAdjTpList(this.getGlobalCompanyCode(), funcList);
            // END   2023/01/27 T.Kuroda [QC#61089, MOD]

            if (arAdjTpTMsgArray != null) {
                List<Map> arAdjTpList = (List<Map>) arAdjTpTMsgArray.getResultObject();
                this.createAdjTpCdPullDown(bizMsg, arAdjTpList);
            }
            // START 2023/01/27 T.Kuroda [QC#61089, ADD]
            else {
                Map<String, String> pMap = new HashMap<String, String>();
                pMap.put("GLBL_CMPY_CD", getGlobalCompanyCode());
                pMap.put("AR_ADJ_CATG_CD", AR_ADJ_TRX_TP.ADJUSTMENT);
                AR_ADJ_TPTMsgArray outArAdjTpMsgList = (AR_ADJ_TPTMsgArray) ZYPCodeDataUtil.findByCondition("AR_ADJ_TP", pMap);

                if (outArAdjTpMsgList.length() > 0) {
                    Map<String, String> tMsgKeys = new HashMap<String, String>();
                    tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "arAdjTpCd");
                    tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "arAdjTpDescTxt");
                    ZYPPulldownValueSetter.set(outArAdjTpMsgList, tMsgKeys, bizMsg.arAdjTpCd_LC, bizMsg.arAdjTpDescTxt_LD);
                }

            }
            // END   2023/01/27 T.Kuroda [QC#61089, ADD]

        } catch (IllegalArgumentException e) {
            bizMsg.setMessageInfo("NFCM0106E", null);
        }
    }

    @SuppressWarnings("unchecked")
    private void createAdjTpCdPullDown(NFCL0730CMsg bizMsg, List<Map> arAdjTpTMsgArray) {
        if ( arAdjTpTMsgArray != null && arAdjTpTMsgArray.size() > 0) {
            
            if (arAdjTpTMsgArray.size() > bizMsg.arAdjTpCd_LC.length()) {
                bizMsg.setMessageInfo("NFCM0106E", null);
            } else {
            
                for (int i = 0; i < arAdjTpTMsgArray.size(); i++) {
    
                    Map pullDownData = arAdjTpTMsgArray.get(i);
    
                    bizMsg.arAdjTpCd_LC.no(i).setValue((String) pullDownData.get("AR_ADJ_TP_CD"));
                    bizMsg.arAdjTpDescTxt_LD.no(i).setValue((String) pullDownData.get("AR_ADJ_TP_DESC_TXT"));
                }
            }
        } else {
            bizMsg.setMessageInfo("NFCM0106E", null);
        }

    }
    //---- end 2016/04/13
    
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0730Scrn00_AddLine(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;
        
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        // check 9segment
        if (OTHER_CODE.equals(bizMsg.arAdjTpCd_H1.getValue())) {
            String[] coa = bizMsg.xxCmntTxt_H1.getValue().split("\\.");
            if(!NFCL0730CommonLogic.checkGlCodeCombination(bizMsg, coa)){
                return;
            }
        }
        // END   2022/11/10 S.Naya [QC#57252,ADD]
        int lineNo = bizMsg.A.getValidCount();
        if((lineNo + 1) > bizMsg.A.length()) {
            bizMsg.setMessageInfo("NFCM0108E");
        }
        
        AR_ADJ_TPTMsg arAdjtpTMsg = (AR_ADJ_TPTMsg) ZYPCodeDataUtil.findByCode(AR_ADJ_TP.class, getGlobalCompanyCode(), bizMsg.arAdjTpCd_H1.getValue());

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lineNo).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lineNo).arAdjTpCd_A1, bizMsg.arAdjTpCd_H1.getValue());
        // START 2016/09/13 [QC#13898, MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lineNo).arAdjTpDescTxt_A1, arAdjtpTMsg.arAdjTpDescTxt.getValue());
        // END 2016/09/13 [QC#13898, MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lineNo).glDt_A1, bizMsg.adjDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lineNo).dealApplyAmt_A1, bizMsg.dealArAdjAmt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lineNo).adjCmntTxt_A1, bizMsg.adjCmntTxt_H1.getValue());
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        if (OTHER_CODE.equals(bizMsg.arAdjTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lineNo).xxCmntTxt_A1, bizMsg.xxCmntTxt_H1.getValue());
        }
        // END   2022/11/10 S.Naya [QC#57252,ADD]
        lineNo++;
        bizMsg.A.setValidCount(lineNo);

        // balance
        BigDecimal dealRmngGrsAmt = new BigDecimal(0);
        dealRmngGrsAmt = bizMsg.dealRmngBalGrsAmt_H2.getValue();
        for (int i  = 0; i < bizMsg.A.getValidCount(); i++) {
            if (hasValue(bizMsg.A.no(i).arAdjNum_A1)) {
                continue;
            }
            dealRmngGrsAmt = dealRmngGrsAmt.add(bizMsg.A.no(i).dealApplyAmt_A1.getValue());
        }
        bizMsg.dealRmngBalGrsAmt_H1.setValue(dealRmngGrsAmt);
        
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0730Scrn00_DeleteLines(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;
        NFCL0730SMsg globalMsg = (NFCL0730SMsg) sMsg;

        int chkCnt = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (bizMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                chkCnt++;
            }
        }
        if(chkCnt==0) {
            bizMsg.setMessageInfo("NFDM0002E", new String[] {"Line(s)" });
            return;
        }
        
        globalMsg.A.clear();
        int cnt = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(!bizMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(cnt), null);
                cnt++;
            }
        }
        globalMsg.A.setValidCount(cnt);
        
        bizMsg.A.clear();
        cnt = 0;
        for (; cnt < globalMsg.A.getValidCount(); cnt++) {
            EZDMsg.copy(globalMsg.A.no(cnt), null, bizMsg.A.no(cnt), null);
            bizMsg.A.no(cnt).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
        }
        bizMsg.A.setValidCount(cnt);
        
        // balance
        BigDecimal dealRmngGrsAmt = new BigDecimal(0);
        dealRmngGrsAmt = bizMsg.dealRmngBalGrsAmt_H2.getValue();
        for (int i  = 0; i < bizMsg.A.getValidCount(); i++) {
            if (hasValue(bizMsg.A.no(i).arAdjNum_A1)) {
                continue;
            }
            dealRmngGrsAmt = dealRmngGrsAmt.add(bizMsg.A.no(i).dealApplyAmt_A1.getValue());
        }
        bizMsg.dealRmngBalGrsAmt_H1.setValue(dealRmngGrsAmt);
        
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0730Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;
        NFCL0730SMsg globalMsg = (NFCL0730SMsg) sMsg;
        

        // Target AR_TRX_BAL
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("arTrxNum01", bizMsg.arTrxNum_H1.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        
        AR_TRX_BALTMsgArray arTrxBalTMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(arTrxBalTMsg == null) {
            bizMsg.setMessageInfo("NFCM0041E");
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H1, arTrxBalTMsg.no(0).dealRmngBalGrsAmt.getValue());
        // START 2016/09/07 K.Kojima [QC#13509,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dealApplyAdjAmt_H2, arTrxBalTMsg.no(0).dealApplyAdjAmt.getValue());
        // END 2016/09/07 K.Kojima [QC#13509,ADD]

        // START 2018/01/17 T.Murai [QC#20563,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dealApplyAdjRsvdAmt_H2, arTrxBalTMsg.no(0).dealApplyAdjRsvdAmt.getValue());
        // END 2018/01/17 T.Murai [QC#20563,ADD]

        bizMsg.A.clear();
        bizMsg.B.clear();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rcptNum", bizMsg.rcptNum_H2.getValue());
        ssmParam.put("arApplytpCd", AR_APPLY_TP.ADJUSTMENT);
        
        S21SsmEZDResult ssmResult = NFCL0730Query.getInstance().getResultDetails(bizMsg, ssmParam);
        
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > bizMsg.B.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.B.length();
            }
            int i;
            for (i = 0; i < bizMsg.B.getValidCount(); i++) {
                if(i < bizMsg.A.length()) {
                    EZDMsg.copy(bizMsg.B.no(i), "B1", bizMsg.A.no(i), "A1");
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(i);
        } else {
            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.A.setValidCount(0);
        }
        
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0730Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        
    }
    
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0730Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;
        NFCL0730SMsg globalMsg = (NFCL0730SMsg) sMsg;

        // Header
        bizMsg.arAdjTpCd_H1.clear();
        bizMsg.adjDt_H1.clear();
        bizMsg.dealArAdjAmt_H1.clear();
        bizMsg.adjCmntTxt_H1.clear();
        
        
        //---- start mod 2016/03/10  
        /*bizMsg.dealRmngBalGrsAmt_H1.setValue(bizMsg.dealRmngBalGrsAmt_H2.getValue());

        // Detail
        bizMsg.A.clear();
        globalMsg.A.clear();
        globalMsg.B.clear();
        */
        
        if (!NFCL0730CommonLogic.searchHeader(bizMsg)) {
            return;
        }
        NFCL0730CommonLogic.searchDetail(bizMsg);
        
        //---- end 2016/03/10
        
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0730Scrn00_CMN_Return(EZDCMsg cMsg, EZDSMsg sMsg) {
        
    }

    // START 2022/11/10 S.Naya [QC#57252,ADD]
    /**
     * Method name: doProcess_NFCL0730Scrn00_OpenWin_ChargeAccount
     * <dd>The method explanation: Event when selecting Charge Account.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0730Scrn00_OpenWin_ChargeAccount(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.xxCmntTxt_H1)) {
            String xxCmntTxt = bizMsg.xxCmntTxt_H1.getValue();
            String[] strSplit = xxCmntTxt.split("\\.");
            if (!NFCL0730CommonLogic.check9Seg(bizMsg, strSplit)) {
                return;
            }
        }
        NFCL0730CommonLogic.get9SegDefault(bizMsg);
    }
    // END   2022/11/10 S.Naya [QC#57252,ADD]
}
