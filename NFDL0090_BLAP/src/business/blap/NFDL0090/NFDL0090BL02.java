/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0090;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;


import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.blap.NFDL0090.common.NFDL0090CommonLogic;
import business.blap.NFDL0090.constant.NFDL0090Constant;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import static business.blap.NFDL0090.common.NFDL0090CommonLogic.*;
import static business.blap.NFDL0090.constant.NFDL0090Constant.NZZM0000E;
import static business.blap.NFDL0090.constant.NFDL0090Constant.NZZM0001W;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_NOTE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2016/05/11   Hitachi         K.Kojima        Update          QC#7638
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2017/09/13   Hitachi         T.Tsuchida      Update          QC#21113
 * 2018/07/17   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/19   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/24   Hitachi         Y.Takeno        Update          QC#26989-1
 * 2018/09/11   Hitachi         Y.Takeno        Update          QC#24884
 * 2019/02/05   Hitachi         H.Umeda         Update          QC#30162
 * 2022/11/24   Hitachi         S.Naya          Update          QC#57252
 * 2022/12/02   Hitachi         S.Fujita        Update          QC#60034
 *</pre>
 */
public class NFDL0090BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;
            NFDL0090SMsg glblMsg = (NFDL0090SMsg) sMsg;

            if ("NFDL0090_INIT".equals(screenAplID)) {
                doProcess_NFDL0090_INIT(bizMsg, glblMsg);
            } else if ("NFDL0090Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NFDL0090Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NFDL0090Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NFDL0090Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NFDL0090Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_CMN_Download(cMsg, sMsg);
            // START 2018/07/17 [QC#26989, ADD]
            } else if ("NFDL0090Scrn00_Click_Search".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_Click_Search(bizMsg, glblMsg);
            } else if ("NFDL0090Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_TBLColumnSort(cMsg, sMsg);
            // END   2018/07/17 [QC#26989, ADD]
            // START 2018/07/19 [QC#26989, ADD]
            } else if ("NFDL0090Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_CMN_Clear(bizMsg, glblMsg);
            // END   2018/07/19 [QC#26989, ADD]
            // START 2019/02/05 [QC#30162, ADD]
            } else if ("NFDL0090Scrn00_WrtOffTotalCalc".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_WrtOffTotalCalc(bizMsg, glblMsg);
            // END   2019/02/05 [QC#30162, ADD]
            // START 2022/11/24 S.Naya [QC#57252,ADD]
            } else if ("NFDL0090Scrn00_OpenWin_ChargeAccount".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_OpenWin_ChargeAccount(cMsg, sMsg);
            // END   2022/11/24 S.Naya [QC#57252,ADD]
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * INIT Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NFDL0090_INIT(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {

        setArAcctDt(cMsg);

        createPullDown(cMsg);
        setDefaultPullDown(cMsg);

        getDsAcctNm(cMsg);

        // START 201902/06 H.Umeda [QC#30162, ADD]
        cMsg.xxDealApplyAmtNum_H1.setValue(new BigDecimal(0));
        // END   201902/06 H.Umeda [QC#30162, ADD]

        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getArTrxBal(cMsg, sMsg);
        if (S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())
                && !"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())
                && !"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }
        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            if (!"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        }

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        NFDL0090CommonLogic.setCurrentPageOut(cMsg, sMsg, 0);
    }

    /**
     * <pre>
     * CMN_Submit Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NFDL0090Scrn00_CMN_Submit(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {
        // START 2018/09/11 [QC#24884, DEL]
        // doProcess_NFDL0090_INIT(cMsg, sMsg);
        // END   2018/09/11 [QC#24884, DEL]
    }

    /**
     * <pre>
     * PageJump Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NFDL0090Scrn00_PageJump(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {
        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int paginationFromOriginal = cMsg.xxPageShowFromNum_BK.getValueInt();
        NFDL0090CommonLogic.setCurrentPageIn(cMsg, sMsg, paginationFromOriginal);

        int i = paginationFrom;
        for (; i < paginationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - paginationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - paginationFrom);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(paginationFrom + 1);
        cMsg.xxPageShowToNum.setValue(paginationFrom + cMsg.A.getValidCount());

    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NFDL0090Scrn00_PageNext(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {

        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        NFDL0090CommonLogic.setCurrentPageIn(cMsg, sMsg, paginationFrom);

        paginationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length();

        NFDL0090CommonLogic.setCurrentPageOut(cMsg, sMsg, paginationFrom);

    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NFDL0090Scrn00_PagePrev(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {

        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        NFDL0090CommonLogic.setCurrentPageIn(cMsg, sMsg, paginationFrom);

        paginationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length();

        NFDL0090CommonLogic.setCurrentPageOut(cMsg, sMsg, paginationFrom);
    }

    private void doProcess_NFDL0090Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;
        NFDL0090Query.getInstance().createDetailCSV(bizMsg);
    }

    @SuppressWarnings("unchecked")
    private void createPullDown(NFDL0090CMsg cMsg) {
        Map<String, String> pMap = new HashMap<String, String>();
        // START 2022/12/02 S.Fujita [QC#60034, ADD]
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NFDL0090Constant.BIZ_ID);
        
        // get Pulldown List
        S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getArAdjTp(cMsg, funcList);
        if (ssmResult != null && ssmResult.isCodeNormal()){
            List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();
            for(int i = 0; i < result.size() ; i++){
                Map<String, Object> pullDownData = result.get(i);
                cMsg.arAdjTpCd_FC.no(i).setValue((String) pullDownData.get("AR_ADJ_TP_CD"));
                cMsg.arAdjTpDescTxt_FD.no(i).setValue((String) pullDownData.get("AR_ADJ_TP_DESC_TXT"));
            }
        } else{
            // END 2022/12/02 S.Fujita [QC#60034, ADD]
            pMap.put("GLBL_CMPY_CD", getGlobalCompanyCode());
            // START 2017/09/13 T.Tsuchida [QC#21113,MOD]
            pMap.put("AR_ADJ_CATG_CD", AR_ADJ_TRX_TP.ADJUSTMENT);
            pMap.put("CLT_TRGT_FLG", ZYPConstant.FLG_ON_Y);
            // END 2017/09/13 T.Tsuchida [QC#21113,MOD]
            AR_ADJ_TPTMsgArray outArAdjTpMsgList = (AR_ADJ_TPTMsgArray) ZYPCodeDataUtil.findByCondition("AR_ADJ_TP", pMap);
    
            if (outArAdjTpMsgList.length() > 0) {
                Map<String, String> tMsgKeys = new HashMap<String, String>();
                tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "arAdjTpCd");
                tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "arAdjTpDescTxt");
                ZYPPulldownValueSetter.set(outArAdjTpMsgList, tMsgKeys, cMsg.arAdjTpCd_FC, cMsg.arAdjTpDescTxt_FD);
            }
        }

        ZYPCodeDataUtil.createPulldownList(AR_WRT_OFF_NOTE.class, cMsg.arWrtOffNoteCd_FC, cMsg.arWrtOffNoteDescTxt_FD);

    }
    private void setDefaultPullDown(NFDL0090CMsg cMsg) {
        S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getDefWrtOffActv(cMsg);
        String defWrtOffActv = (String) ssmResult.getResultObject();
        if (ZYPCommonFunc.hasValue(defWrtOffActv)) {
            ZYPEZDItemValueSetter.setValue(cMsg.arAdjTpCd_FS, defWrtOffActv);
        }
    }

    private void setArAcctDt(NFDL0090CMsg cMsg) {

        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", getGlobalCompanyCode());

        AR_ACCT_DTTMsg inArAcctDtMsg = new AR_ACCT_DTTMsg();
        inArAcctDtMsg.subSysCd.setValue(subSysCd);
        AR_ACCT_DTTMsg outArAcctDtMsg = NFDL0090CommonLogic.findArAcctDtInfo(getGlobalCompanyCode(), inArAcctDtMsg);

        if (outArAcctDtMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.acctDt_H1, outArAcctDtMsg.acctDt.getValue());

        } else {
            EZDDebugOutput.println(1, "setArAcctDt() Err:None Data", this);
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0008E, null);
            return;
        }
    }

    private void getDsAcctNm(NFDL0090CMsg cMsg) {
        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("sellToCustCd01", cMsg.dsAcctNum_H1.getValue());

        SELL_TO_CUSTTMsgArray outMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        // START 2016/05/11 K.Kojima [QC#6870,MOD]
        // if (outMsgArray == null) {
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            // END 2016/05/11 K.Kojima [QC#6870,MOD]
            EZDDebugOutput.println(1, "getDsAcctNm() Err:None Data", this);
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0008E, null);
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, outMsgArray.no(0).dsAcctNm);
    }

    // START 2018/07/17 [QC#26989, ADD]
    private void doProcess_NFDL0090Scrn00_Click_Search(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {
        setArAcctDt(cMsg);

        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getArTrxBal(cMsg, sMsg);
        if (S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())
                && !"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())
                && !"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }
        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            if (!"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        }

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        NFDL0090CommonLogic.setCurrentPageOut(cMsg, sMsg, 0);
    }

    private void doProcess_NFDL0090Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;
        NFDL0090SMsg globalMsg = (NFDL0090SMsg) sMsg;

        int cnt = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(cnt + i), null);
        }

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

            if (bizMsg.A.getValidCount() > 0) {

                // Copy from SMsg to CMsg
                int i = 0;
                for (; i < globalMsg.A.getValidCount(); i++) {
                    if (i == bizMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                bizMsg.A.setValidCount(i);

                // Page break setting
                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            }
        }
    }
    // END   2018/07/17 [QC#26989, ADD]

    // START 2018/07/19 [QC#26989, ADD]
    private void doProcess_NFDL0090Scrn00_CMN_Clear(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {
        setArAcctDt(cMsg);

        // START 2018/07/24 [QC#26989-1, MOD]
        cMsg.xxQueryFltrTxt_H1.clear();
        cMsg.custIssPoNum_H1.clear();
        cMsg.arAdjTpCd_FS.clear();
        cMsg.arWrtOffNoteCd_FS.clear();
        cMsg.arWrtOffNoteTxt_FS.clear();
        // END   2018/07/24 [QC#26989-1, MOD]
        // START 2018/09/11 [QC#24884, ADD]
        cMsg.arWrtOffRqstPk_H1.clear();
        // END   2018/09/11 [QC#24884, ADD]
        // START 201902/06 H.Umeda [QC#30162, ADD]
        cMsg.xxDealApplyAmtNum_H1.setValue(new BigDecimal(0));
        // END   201902/06 H.Umeda [QC#30162, ADD]
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        cMsg.xxCmntTxt_FS.clear();
        // END   2022/11/24 S.Naya [QC#57252,ADD]

        createPullDown(cMsg);
        setDefaultPullDown(cMsg);

        getDsAcctNm(cMsg);

        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getArTrxBal(cMsg, sMsg);
        if (S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())
                && !"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())
                && !"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }
        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            if (!"NFDL0090Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        }

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        NFDL0090CommonLogic.setCurrentPageOut(cMsg, sMsg, 0);
    }
    // END   2018/07/19 [QC#26989, ADD]
    // START 2019/02/05 [QC#30162, ADD]
    private void doProcess_NFDL0090Scrn00_WrtOffTotalCalc(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {

        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;

        NFDL0090CommonLogic.setCurrentPageIn(cMsg, sMsg, paginationFrom);

        NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;
        NFDL0090SMsg glblMsg = (NFDL0090SMsg) sMsg;

        BigDecimal ttl = BigDecimal.ZERO;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if(ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())){
                ttl = ttl.add(glblMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
            }
        }
        bizMsg.xxDealApplyAmtNum_H1.setValue(ttl);
        
        if (ttl.equals(BigDecimal.ZERO)) {
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0024E);
        }
    }
    // END   2019/02/05 [QC#30162, ADD]
    // START 2022/11/24 S.Naya [QC#57252,ADD]
    /**
     * Method name: doProcess_NFDL0090Scrn00_OpenWin_ChargeAccount
     * <dd>The method explanation: Event when selecting Charge Account.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0090Scrn00_OpenWin_ChargeAccount(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.xxCmntTxt_FS)) {
            String xxCmntTxt = bizMsg.xxCmntTxt_FS.getValue();
            String[] strSplit = xxCmntTxt.split("\\.");
            if (!check9Seg(bizMsg, strSplit)) {
                return;
            }
        }
        get9SegDefault(bizMsg, getGlobalCompanyCode());
    }
    // END   2022/11/24 S.Naya [QC#57252,ADD]
}
