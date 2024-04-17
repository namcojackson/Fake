/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0120.common;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NFAL0120.NFAL0120CMsg;
import business.blap.NFAL0120.NFAL0120Query;
import business.blap.NFAL0120.NFAL0120SMsg;
import business.blap.NFAL0120.constant.NFAL0120Constant;
import business.blap.NFCL3050.constant.NFCL3050Constant;
import business.db.AJE_RPT_JRNL_ENTRYTMsg;
import business.db.JRNL_CATGTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static parts.common.EZDCommonFunc.isNumberCheck;

/**
 * Class name: NFAL0120CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120CommonLogic implements NFAL0120Constant {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
    
    /** S21UserProfileService profileService */
    private S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

    /** String globalCompanyCode */
    private String globalCompanyCode = profileService.getGlobalCompanyCode();

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

    /** Array of TMsg */
    private EZDTMsg[] tMsgCreate;

    /** Size of Array */
    private int tMsgCnt = 0;

    /**
     * Method name: isArchived
     * <dd>The method explanation: Check if the requested query is within s21_apps or not.
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global Message
     */
    public boolean isArchived(NFAL0120CMsg bizMsg) {
        // if not yet archived
        if (bizMsg.xxExstFlg.getValue().equals(NO)) {
            return false;
        }
        
        String minGlDt = bizMsg.glDt_MN.getValue();
            
        if (minGlDt == null) {
            bizMsg.setMessageInfo("NFAM0032E");
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
        
        String thisMonth = ZYPDateUtil.DateFormatter(ZYPDateUtil.getSalesDate(globalCompanyCode), "yyyyMMdd", "yyyyMM");
        String monthFrom = ZYPDateUtil.DateFormatter(bizMsg.glDt_FR.getValue(), "yyyyMMdd", "yyyyMM");
        String lastMonth = getLastMonth();
        
        // if from date is this month, then from normal space
        if (thisMonth.equals(monthFrom)) {
            return false;
        // if from date is last month and last month data is existing in JRNL_ENTRY, then from normal space
        } else if (lastMonth.equals(monthFrom) && !minGlDt.equals("")){
            return false;
        // else, from Archive
        } else {
            return true;
        }
    }
    
    public String getLastMonth(){
        Calendar cal = Calendar.getInstance();
        String today = ZYPDateUtil.getSalesDate(globalCompanyCode);

        cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(today, "yyyyMMdd", "yyyy")),
                Integer.valueOf(ZYPDateUtil.DateFormatter(today, "yyyyMMdd", "MM")) - 1,
                Integer.valueOf(ZYPDateUtil.DateFormatter(today, "yyyyMMdd", "dd")));

        // minus one month (send previous month fixed data)
        cal.add(Calendar.MONTH, -1);

        // format
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month != null && month.length() == 1) {
            month = "0" + month;
        }
        
        return String.valueOf(cal.get(Calendar.YEAR)) + month;
    }
    
    /**
     * Method name: getLastMonth
     * <dd>The method explanation: minumum GL_DT in Journal Entry 
     * <dd>Remarks:
     */
    public void getLastMonth(NFAL0120CMsg bizMsg) {

        String minGlDt = parts.checkNull(NFAL0120Query.getInstance().getLastMonth());
        bizMsg.glDt_MN.setValue(minGlDt);
        
    }
    
    /**
     * Method name: checkArchiveDone
     * <dd>Remarks:
     */
    public void checkArchiveDone(NFAL0120CMsg bizMsg) {

        String yesOrNo = NFAL0120Query.getInstance().chkIsArhived();
        bizMsg.xxExstFlg.setValue(yesOrNo);
        
    }
    
    /**
     * Method name: getResultArReclass
     * <dd>The method explanation: Get result from JRNL_ENTRY table.
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global Message
     */
    public void getResultArReclass(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        bizMsg.xxTotCnt.setValue(0);
        globalMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().getResultArReclass(bizMsg, globalMsg, false);

        // ------- start CSA modification
        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo("NFAM0002E", new String[] {"result" });
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }
        
        if (ssmResult.isCodeNotFound()) {
            // try to search in Archive
            ssmResult = NFAL0120Query.getInstance().getResultArReclass(bizMsg, globalMsg, true);
        }
        // ------- end CSA modification
        
        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo("NFAM0002E", new String[] {"result" });
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Method name: getResultVer1
     * <dd>The method explanation: Get result from JRNL_ENTRY table.
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global Message
     */
    public void getResultVer1(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        bizMsg.xxTotCnt.setValue(0);
        globalMsg.A.setValidCount(0);

        // Debit or Credit result
        //-------- start CSA modification
        // First try in normal schema
        S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().getResult(bizMsg, globalMsg, false);
        
        // START    2019/12/19 [QC#53730, DEL]
        // if no search result, then try to search from Archive
        //if (ssmResult.isCodeNotFound()) {
        //    ssmResult = NFAL0120Query.getInstance().getResult(bizMsg, globalMsg, true);
        //}
        // END      2019/12/19 [QC#53730, DEL]
        //-------- end CSA modification
        
        int queryResCnt = 0;
        if (ssmResult.isCodeNormal()) {
            // has a result
            // in case of exceeding maximum limit of record
            queryResCnt = ssmResult.getQueryResultCount();
            bizMsg.xxTotCnt.setValue(queryResCnt);

            int i = 0;
            for (; i < globalMsg.A.getValidCount(); i++) {
                // START 2017/03/16 [QC#14205,MOD]
//                if (i == bizMsg.A.length()) {
//                    break;
//                }
//                setDrCrAmtCell(globalMsg, i);
//                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
//                globalMsg.A.no(i).xxRecHistCratByNm_A.setValue(ZYPRecHistUtil.getFullNameForRecHist(globalMsg.A.no(i).xxRecHistCratByNm_A.getValue()));
//                globalMsg.A.no(i).xxRecHistUpdByNm_A.setValue(ZYPRecHistUtil.getFullNameForRecHist(globalMsg.A.no(i).xxRecHistUpdByNm_A.getValue()));
                if (i < bizMsg.A.length()) {
                    setDrCrAmtCell(globalMsg, i);
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                    continue;
                }
                // END   2017/03/16 [QC#14205,MOD]
            }
            bizMsg.A.setValidCount(i);
        }
        // Set result to cMsg
        setSearchedResult(bizMsg, globalMsg, globalMsg.A.getValidCount());
    }

    /**
     * Method name: getResultVer2
     * <dd>The method explanation: Get result from JRNL_ENTRY table.
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global Message
     */
    /*
    public void getResultVer2(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        // TODO
        bizMsg.xxTotCnt.setValue(0);
        globalMsg.A.setValidCount(0);

        List<JRNL_ENTRYTMsg> resultList = new ArrayList<JRNL_ENTRYTMsg>();
        
        // Debit and Credit in the same result
        
        // ---------------------- start CSA modification
        S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().getResultDrCr(bizMsg, globalMsg, false);
        
        if (ssmResult.isCodeNotFound()) {
            // try to search from Archive
            ssmResult = NFAL0120Query.getInstance().getResultDrCr(bizMsg, globalMsg, true);
        }
        // ----------------- end
        
        int queryResCnt = 0;
        if (ssmResult.isCodeNormal()) {

            queryResCnt = ssmResult.getQueryResultCount();
            bizMsg.xxTotCnt.setValue(queryResCnt);
            // List for Result
            List resultListTmp = (List) ssmResult.getResultObject();
            // List for Search Criteria
            List<Map> srchCrtList = getSearchCriteriaList(bizMsg);

            for (int i = 0; i < queryResCnt; i++) {
                Map map = (Map) resultListTmp.get(i);
                // For Debit
                JRNL_ENTRYTMsg tMsgDr = getTMsg(map, srchCrtList, DEBIT_INT);
                if (tMsgDr != null) {
                    resultList.add(tMsgDr);
                }
                // For Credit
                JRNL_ENTRYTMsg tMsgCr = getTMsg(map, srchCrtList, CREDIT_INT);
                if (tMsgCr != null) {
                    resultList.add(tMsgCr);
                }
            }
        }
        // Create search result as tMsg array
        JRNL_ENTRYTMsg[] tMsgArr = createTMsgArr(resultList);
        // Sort
        Arrays.sort(tMsgArr, new TMsgComparator());
        // Set result to sMsg
        setSMsgFromTMsgArr(globalMsg, tMsgArr);
        // Set result to cMsg
        setSearchedResult(bizMsg, globalMsg, globalMsg.A.getValidCount());
    }
    */

    /**
     * Class TMsgComparator
     * <dd>Compare Sort T-Message
     */
    private class TMsgComparator implements Comparator<Object> {

        public int compare(Object obj1, Object obj2) {

            JRNL_ENTRYTMsg tMsg1 = (JRNL_ENTRYTMsg) obj1;
            JRNL_ENTRYTMsg tMsg2 = (JRNL_ENTRYTMsg) obj2;

            BigDecimal pk1 = tMsg1.jrnlEntryPk.getValue();
            BigDecimal pk2 = tMsg2.jrnlEntryPk.getValue();

            if (pk1.compareTo(pk2) == 0) {
                // If same primary key
                // then compare D or C
                String dr1 = tMsg1.ajeIntfcCmntTxt.getValue();
                String dr2 = tMsg2.ajeIntfcCmntTxt.getValue();
                if (dr1.compareTo(dr2) == 1) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (pk1.compareTo(pk2) == 1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private JRNL_ENTRYTMsg[] createTMsgArr(List resultList) {

        JRNL_ENTRYTMsg[] tMsgArr = new JRNL_ENTRYTMsg[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            tMsgArr[i] = (JRNL_ENTRYTMsg) resultList.get(i);
        }
        return tMsgArr;
    }

    private void setSearchedResult(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg, int queryResCnt) {

        if (queryResCnt > 0) {
            int i = 0;
            for (; i < globalMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            // has no result
            bizMsg.setMessageInfo("NFAM0002E", new String[] {"result" });
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    private void setSMsgFromTMsgArr(NFAL0120SMsg globalMsg, JRNL_ENTRYTMsg[] tMsgArr) {

        int count = 0;
        for (int i = 0; i < tMsgArr.length; i++) {

            // Reached at maximum length
            if (i == globalMsg.A.length()) {
                break;
            }

            JRNL_ENTRYTMsg tMsg = tMsgArr[i];

            globalMsg.A.no(i).jrnlEntryPk_A.setValue(tMsg.jrnlEntryPk.getValue());
            globalMsg.A.no(i).glDt_A.setValue(tMsg.glDt.getValue());
            //globalMsg.A.no(i).procDt_A.setValue(tMsg.procDt.getValue());
            globalMsg.A.no(i).ajeId_A.setValue(tMsg.ajeId.getValue());
            globalMsg.A.no(i).jrnlSrcNm_A.setValue(tMsg.jrnlSrcNm.getValue());
            globalMsg.A.no(i).jrnlCatgNm_A.setValue(tMsg.jrnlCatgNm.getValue());

            globalMsg.A.no(i).xxScrId_A.setValue(tMsg.ajeIntfcCmntTxt.getValue());
            globalMsg.A.no(i).drCoaCmpyCd_A.setValue(tMsg.drCoaCmpyCd.getValue());
            globalMsg.A.no(i).drCoaBrCd_A.setValue(tMsg.drCoaBrCd.getValue());
            globalMsg.A.no(i).drCoaCcCd_A.setValue(tMsg.drCoaCcCd.getValue());
            globalMsg.A.no(i).drCoaAcctCd_A.setValue(tMsg.drCoaAcctCd.getValue());

            globalMsg.A.no(i).drCoaProdCd_A.setValue(tMsg.drCoaProdCd.getValue());
            globalMsg.A.no(i).drCoaChCd_A.setValue(tMsg.drCoaChCd.getValue());
            globalMsg.A.no(i).drCoaAfflCd_A.setValue(tMsg.drCoaAfflCd.getValue());
            globalMsg.A.no(i).drCoaProjCd_A.setValue(tMsg.drCoaProjCd.getValue());
            globalMsg.A.no(i).drCoaExtnCd_A.setValue(tMsg.drCoaExtnCd.getValue());

            globalMsg.A.no(i).jrnlFuncDrAmt_A.setValue(tMsg.jrnlFuncDrAmt.getValue());
            globalMsg.A.no(i).jrnlFuncCrAmt_A.setValue(tMsg.jrnlFuncCrAmt.getValue());
            globalMsg.A.no(i).coaProdCd_A.setValue(tMsg.coaProdCd.getValue());
            globalMsg.A.no(i).tocCd_A.setValue(tMsg.tocCd.getValue());
            globalMsg.A.no(i).billToCustCd_A.setValue(tMsg.billToCustCd.getValue());

            globalMsg.A.no(i).vndCd_A.setValue(tMsg.vndCd.getValue());
            globalMsg.A.no(i).ajeInvNum_A.setValue(tMsg.ajeInvNum.getValue());
            globalMsg.A.no(i).prmoPk_A.setValue(tMsg.prmoPk.getValue());
            globalMsg.A.no(i).ajeItemCd_A.setValue(tMsg.ajeItemCd.getValue());
            globalMsg.A.no(i).soNum_A.setValue(tMsg.soNum.getValue());
            count++;
        }
        globalMsg.A.setValidCount(count);
    }

    private JRNL_ENTRYTMsg getTMsg(Map resultMap, List<Map> srchCrtList, int drOrCr) {

        if (!srchCrtMatchedWithResult(srchCrtList, resultMap, drOrCr)) {
            // No Result line (T-Msg) needed when
            // Search criteria does not match
            // the Debit or Credit result
            return null;
        } else {
            // Return Debit or Credit line (T-Msg)
            JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();

            tMsg.jrnlEntryPk.setValue(parts.checkNull((BigDecimal) resultMap.get(JRNL_ENTRY_PK)));
            tMsg.glDt.setValue(parts.checkNull((String) resultMap.get(GL_DT)));
            tMsg.procDt.setValue(parts.checkNull((String) resultMap.get(PROC_DT)));
            tMsg.ajeId.setValue(parts.checkNull((String) resultMap.get(AJE_ID)));
            tMsg.jrnlSrcNm.setValue(parts.checkNull((String) resultMap.get(JRNL_SRC_NM)));
            tMsg.jrnlCatgNm.setValue(parts.checkNull((String) resultMap.get(JRNL_CATG_NM)));

            if (drOrCr == DEBIT_INT) {
                // For Debit
                tMsg.ajeIntfcCmntTxt.setValue(DEBIT_STR);
                tMsg.jrnlFuncDrAmt.setValue(parts.checkNull((BigDecimal) resultMap.get(JRNL_FUNC_DR_AMT)));
                tMsg.jrnlFuncCrAmt.clear();

                tMsg.drCoaAcctCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_ACCT_CD)));
                tMsg.drCoaCmpyCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_CMPY_CD)));
                tMsg.drCoaBrCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_BR_CD)));
                tMsg.drCoaCcCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_CC_CD)));
                tMsg.drCoaProdCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_PROD_CD)));

                tMsg.drCoaChCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_CH_CD)));
                tMsg.drCoaAfflCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_AFFL_CD)));
                tMsg.drCoaProjCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_PROJ_CD)));
                tMsg.drCoaExtnCd.setValue(parts.checkNull((String) resultMap.get(DR_COA_EXTN_CD)));
            } else if (drOrCr == CREDIT_INT) {
                // For Credit
                tMsg.ajeIntfcCmntTxt.setValue(CREDIT_STR);
                tMsg.jrnlFuncDrAmt.clear();
                tMsg.jrnlFuncCrAmt.setValue(parts.checkNull((BigDecimal) resultMap.get(JRNL_FUNC_CR_AMT)));

                tMsg.drCoaAcctCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_ACCT_CD)));
                tMsg.drCoaCmpyCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_CMPY_CD)));
                tMsg.drCoaBrCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_BR_CD)));
                tMsg.drCoaCcCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_CC_CD)));
                tMsg.drCoaProdCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_PROD_CD)));

                tMsg.drCoaChCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_CH_CD)));
                tMsg.drCoaAfflCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_AFFL_CD)));
                tMsg.drCoaProjCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_PROJ_CD)));
                tMsg.drCoaExtnCd.setValue(parts.checkNull((String) resultMap.get(CR_COA_EXTN_CD)));
            }

            tMsg.coaProdCd.setValue(parts.checkNull((String) resultMap.get(COA_PROD_CD)));
            tMsg.tocCd.setValue(parts.checkNull((String) resultMap.get(TOC_CD)));

            tMsg.billToCustCd.setValue(parts.checkNull((String) resultMap.get(BILL_TO_CUST_CD)));
            tMsg.vndCd.setValue(parts.checkNull((String) resultMap.get(VND_CD)));
            tMsg.ajeInvNum.setValue(parts.checkNull((String) resultMap.get(AJE_INV_NUM)));

            if (ZYPCommonFunc.hasValue(((BigDecimal) resultMap.get(PRMO_PK)))) {
                tMsg.prmoPk.setValue((BigDecimal) resultMap.get(PRMO_PK));
            } else {
                // in order not to put "0"
                tMsg.prmoPk.clear();
            }
            tMsg.ajeItemCd.setValue(parts.checkNull((String) resultMap.get(AJE_ITEM_CD)));
            tMsg.soNum.setValue(parts.checkNull((String) resultMap.get(SO_NUM)));

            return tMsg;
        }
    }

    /**
     * Method name: srchCrtMatchedWithResult
     * <dd>The method explanation: This method check if ALL Search
     * criteria matched ALL part of Debit or Credit, if not matched
     * record will not be displied
     * <dd>Remarks:
     * @param srchCrtList List for Search Criteria
     * @param resultMap Searched result for both Debit and Credit
     * @param drOrCr Debit = 1, Credit = 2
     * @return When search criteria does not exist or match with ALL
     * Debit/Credit data then return true, otherwise return false.
     */
    private boolean srchCrtMatchedWithResult(List<Map> srchCrtList, Map resultMap, int drOrCr) {

        for (int i = 0; i < srchCrtList.size(); i++) {
            Map srchCrtMap = (Map) srchCrtList.get(i);

            // Search Criterias for Nine Segment
            String acctSrchCrt = (String) srchCrtMap.get(COA_ACCT);
            String prodSrchCrt = (String) srchCrtMap.get(COA_PROD);
            String brSrchCrt = (String) srchCrtMap.get(COA_BR);
            String ccSrchCrt = (String) srchCrtMap.get(COA_CC);
            String chSrchCrt = (String) srchCrtMap.get(COA_CH);
            String afflSrchCrt = (String) srchCrtMap.get(COA_AFFL);
            String projSrchCrt = (String) srchCrtMap.get(COA_PROJ);

            if (drOrCr == DEBIT_INT) {
                // Check a part of result for Debit
                // If ANY COA segment does not match
                // with a search criteria, return false
                if (ZYPCommonFunc.hasValue(acctSrchCrt) && !acctSrchCrt.startsWith(parts.checkNull((String) resultMap.get(DR_COA_ACCT_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(prodSrchCrt) && !prodSrchCrt.startsWith(parts.checkNull((String) resultMap.get(DR_COA_PROD_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(brSrchCrt) && !brSrchCrt.startsWith(parts.checkNull((String) resultMap.get(DR_COA_BR_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(ccSrchCrt) && !ccSrchCrt.startsWith(parts.checkNull((String) resultMap.get(DR_COA_CC_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(chSrchCrt) && !chSrchCrt.startsWith(parts.checkNull((String) resultMap.get(DR_COA_CH_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(afflSrchCrt) && !afflSrchCrt.startsWith(parts.checkNull((String) resultMap.get(DR_COA_AFFL_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(projSrchCrt) && !projSrchCrt.startsWith(parts.checkNull((String) resultMap.get(DR_COA_PROJ_CD)))) {
                    return false;
                }
            } else if (drOrCr == CREDIT_INT) {
                // Check a part of result for Credit,
                // If ANY COA segment does not match
                // with a search criteria, return false
                if (ZYPCommonFunc.hasValue(acctSrchCrt) && !acctSrchCrt.startsWith(parts.checkNull((String) resultMap.get(CR_COA_ACCT_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(prodSrchCrt) && !prodSrchCrt.startsWith(parts.checkNull((String) resultMap.get(CR_COA_PROD_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(brSrchCrt) && !brSrchCrt.startsWith(parts.checkNull((String) resultMap.get(CR_COA_BR_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(ccSrchCrt) && !ccSrchCrt.startsWith(parts.checkNull((String) resultMap.get(CR_COA_CC_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(chSrchCrt) && !chSrchCrt.startsWith(parts.checkNull((String) resultMap.get(CR_COA_CH_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(afflSrchCrt) && !afflSrchCrt.startsWith(parts.checkNull((String) resultMap.get(CR_COA_AFFL_CD)))) {
                    return false;
                }
                if (ZYPCommonFunc.hasValue(projSrchCrt) && !projSrchCrt.startsWith(parts.checkNull((String) resultMap.get(CR_COA_PROJ_CD)))) {
                    return false;
                }
            }
        }
        // When search criteria does not exist or match with
        // ALL Debit/Credit data then return true, otherwise
        // return false.
        return true;
    }

    /*
    private List<Map> getSearchCriteriaList(NFAL0120CMsg bizMsg) {

        String coaAcctCd = bizMsg.coaAcctCd.getValue();
        String coaProdCd = bizMsg.drCoaProdCd.getValue();
        String coaBrCd = bizMsg.coaBrCd_3.getValue();
        String coaCcCd = bizMsg.coaCcCd.getValue();
        String coaChCd = bizMsg.coaChCd_3.getValue();
        String coaAfflCd = bizMsg.coaAfflCd.getValue();
        String coaProjCd = bizMsg.coaProjCd.getValue();
        
        List<Map> srchCrtList = new ArrayList<Map>();

        // COA_ACCT_CD
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            addList(srchCrtList, COA_ACCT, coaAcctCd);
        }
        // COA_PROD_CD
        if (ZYPCommonFunc.hasValue(coaProdCd)) {
            addList(srchCrtList, COA_PROD, coaProdCd);
        }
        // COA_BR_CD
        if (ZYPCommonFunc.hasValue(coaBrCd)) {
            addList(srchCrtList, COA_BR, coaBrCd);
        }
        // COA_CC_CD
        if (ZYPCommonFunc.hasValue(coaCcCd)) {
            addList(srchCrtList, COA_CC, coaCcCd);
        }
        // COA_CH_CD
        if (ZYPCommonFunc.hasValue(coaChCd)) {
            addList(srchCrtList, COA_CH, coaChCd);
        }
        // COA_AFFL_CD
        if (ZYPCommonFunc.hasValue(coaAfflCd)) {
            addList(srchCrtList, COA_AFFL, coaAfflCd);
        }
        // COA_PROJ_CD
        if (ZYPCommonFunc.hasValue(coaProjCd)) {
            addList(srchCrtList, COA_PROJ, coaProjCd);
        }
        return srchCrtList;
    }
    */

    private void addList(List<Map> srchCrList, String mapKey, String mapVal) {
        Map<String, String> srchMap = new HashMap<String, String>();
        srchMap.put(mapKey, mapVal);
        srchCrList.add(srchMap);
    }

    /**
     * Method name: getResultDrForReport
     * <dd>The method explanation: Get result from JRNL_ENTRY table.
     * <dd>Remarks:
     * @param bizMsg NFAL0120CMsg
     * @return long
     */
    public long getResultDrForReport(NFAL0120CMsg bizMsg) {

        long queryResCnt = 0;
        try {
            // ------ start CSA modification
            S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().getResultDrForReport(bizMsg, false);
            
            if (ssmResult.isCodeNotFound()) {
                // try to search from Archive
                ssmResult = NFAL0120Query.getInstance().getResultDrForReport(bizMsg, true);
            }
            // --------- end
            
            if (ssmResult.isCodeNormal()) {

                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

                // has a result
                // in case of exceesing maximum limit of record
                queryResCnt = ssmResult.getQueryResultCount();
                EZDDebugOutput.println(5, "=========getResultDrForReport: Debit count = " + queryResCnt, this);

                if (queryResCnt > 0) {
                    // Clear work table
                    clearWorkTable();
                }

                List resultList = (List) ssmResult.getResultObject();
                for (int i = 0; i < queryResCnt; i++) {
                    Map map = (Map) resultList.get(i);
                    AJE_RPT_JRNL_ENTRYTMsg tMsgIn = createReportTMsg(map);
                    createRecord(tMsgIn);
                }
                EZDDebugOutput.println(5, "=========getResultDrForReport: Debit count(Remained after loop) = " + tMsgCnt, this);
                if (tMsgCnt != 0) {
                    createRecord(null);
                }
            }
        } catch (Exception e) {
            // NFAM0035E=0, @ couldn't be completed by unexpected
            // reason. The detailed error message: "@".
            bizMsg.setMessageInfo("NFAM0035E", new String[] {e.getMessage() });
        }
        return queryResCnt;
    }

    /**
     * Method name: getResultCrForReport
     * <dd>The method explanation: Get result from JRNL_ENTRY table.
     * <dd>Remarks:
     * @param bizMsg NFAL0120CMsg
     * @param drCount long
     * @return long
     */
    public long getResultCrForReport(NFAL0120CMsg bizMsg, long drCount) {

        long crCount = 0;
        try {
            // ---------- start CSA modification
            S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().getResultCrForReport(bizMsg, false);
            
            if (ssmResult.isCodeNotFound()) {
                // try to saerch from Archive
                ssmResult = NFAL0120Query.getInstance().getResultCrForReport(bizMsg, true);
            }
            // ------ end
            
            if (ssmResult.isCodeNormal()) {

                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

                // has a result
                // in case of exceesing maximum limit of record
                crCount = ssmResult.getQueryResultCount();
                EZDDebugOutput.println(5, "=========getResultCrForReport: Credit count = " + crCount, this);

                if (drCount == 0 && crCount > 0) {
                    // Clear work table
                    // to prepare new records
                    clearWorkTable();
                }

                List resultList = (List) ssmResult.getResultObject();
                for (int i = 0; i < crCount; i++) {
                    Map map = (Map) resultList.get(i);
                    AJE_RPT_JRNL_ENTRYTMsg tMsgIn = createReportTMsg(map);
                    createRecord(tMsgIn);
                }
                EZDDebugOutput.println(5, "=========getResultCrForReport: Credit count(Remained after loop) = " + tMsgCnt, this);
                if (tMsgCnt != 0) {
                    createRecord(null);
                }
            }
        } catch (Exception e) {
            // NFAM0035E=0, @ couldn't be completed by unexpected
            // reason. The detailed error message: "@".
            bizMsg.setMessageInfo("NFAM0035E", new String[] {e.getMessage() });
        }
        return crCount;
    }

    /**
     * Method name: clearWorkTable
     * <dd>The method explanation:
     * <dd>Remarks:
     */
    public void clearWorkTable() {
        // Clear work table
        AJE_RPT_JRNL_ENTRYTMsg tMsg = new AJE_RPT_JRNL_ENTRYTMsg();
        tMsg.glblCmpyCd.setValue(globalCompanyCode);
        S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
    }

    private AJE_RPT_JRNL_ENTRYTMsg createReportTMsg(Map map) {

        AJE_RPT_JRNL_ENTRYTMsg tMsg = new AJE_RPT_JRNL_ENTRYTMsg();

        String dOrC = parts.checkNull((String) map.get(DR_OR_CR));

        if (dOrC.equals(DEBIT_NUM_STR)) {
            tMsg.drCrTpCd.setValue(DEBIT_STR);
            tMsg.jrnlFuncDrAmt.setValue(parts.checkNull((BigDecimal) map.get(JRNL_FUNC_DR_AMT)));
            tMsg.jrnlFuncCrAmt.clear();
        } else {
            tMsg.drCrTpCd.setValue(CREDIT_STR);
            tMsg.jrnlFuncDrAmt.clear();
            tMsg.jrnlFuncCrAmt.setValue(parts.checkNull((BigDecimal) map.get(JRNL_FUNC_CR_AMT)));
        }

        tMsg.glblCmpyCd.setValue(globalCompanyCode);
        tMsg.jrnlEntryPk.setValue(parts.checkNull((BigDecimal) map.get(JRNL_ENTRY_PK)));
        tMsg.glSendCpltFlg.setValue(NO);

        tMsg.glDt.setValue(parts.checkNull((String) map.get(GL_DT)));
        tMsg.procDt.setValue(parts.checkNull((String) map.get(PROC_DT)));
        tMsg.ajeId.setValue(parts.checkNull((String) map.get(AJE_ID)));
        tMsg.jrnlSrcNm.setValue(parts.checkNull((String) map.get(JRNL_SRC_NM)));
        tMsg.jrnlCatgNm.setValue(parts.checkNull((String) map.get(JRNL_CATG_NM)));

        tMsg.drCoaAcctCd.setValue(parts.checkNull((String) map.get(COA_ACCT_CD)));
        tMsg.drCoaCmpyCd.setValue(parts.checkNull((String) map.get(COA_CMPY_CD)));
        tMsg.drCoaBrCd.setValue(parts.checkNull((String) map.get(COA_BR_CD)));
        tMsg.drCoaCcCd.setValue(parts.checkNull((String) map.get(COA_CC_CD)));
        tMsg.drCoaProdCd.setValue(parts.checkNull((String) map.get(COA_PROD_CD_DC)));

        tMsg.drCoaChCd.setValue(parts.checkNull((String) map.get(COA_CH_CD)));
        tMsg.drCoaAfflCd.setValue(parts.checkNull((String) map.get(COA_AFFL_CD)));
        tMsg.drCoaProjCd.setValue(parts.checkNull((String) map.get(COA_PROJ_CD)));
        tMsg.drCoaExtnCd.setValue(parts.checkNull((String) map.get(COA_EXTN_CD)));

        tMsg.coaProdCd.setValue(parts.checkNull((String) map.get(COA_PROD_CD)));
        tMsg.tocCd.setValue(parts.checkNull((String) map.get(TOC_CD)));

        tMsg.billToCustCd.setValue(parts.checkNull((String) map.get(BILL_TO_CUST_CD)));
        tMsg.vndCd.setValue(parts.checkNull((String) map.get(VND_CD)));
        tMsg.ajeInvNum.setValue(parts.checkNull((String) map.get(AJE_INV_NUM)));
        if (ZYPCommonFunc.hasValue(((BigDecimal) map.get(PRMO_PK)))) {
            tMsg.prmoPk.setValue((BigDecimal) map.get(PRMO_PK));
        } else {
            tMsg.prmoPk.clear();
        }
        tMsg.ajeItemCd.setValue(parts.checkNull((String) map.get(AJE_ITEM_CD)));

        return tMsg;
    }

    private void createRecord(EZDTMsg tMsg) {

        if (tMsg != null) {
            tMsgCreate[tMsgCnt] = tMsg;
            tMsgCnt += 1;

        } else { // array may be not full
            tMsgCreate = changeArraySize(tMsgCreate, tMsgCnt);
        }

        // per 10000 lines
        if (tMsgCnt >= BULK_INSERT_COUNT || tMsg == null) {

            int retCnt = S21FastTBLAccessor.insert(tMsgCreate);

            // if passed records' count and return count don't
            // match, error
            if (retCnt != tMsgCnt) {
                throw new S21AbendException(ZZBM0074E);
            }
            // initialize
            tMsgCnt = 0;
            tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    private EZDTMsg[] changeArraySize(EZDTMsg[] arrayRec, int newsize) {

        EZDTMsg[] copyMsgs = arrayRec.clone();
        arrayRec = new EZDTMsg[newsize];
        // copy data
        for (int i = 0; i < newsize; i++) {
            arrayRec[i] = copyMsgs[i];
        }
        return arrayRec;
    }

    // private String getTotalCountStr(NFAL0120CMsg bizMsg) {
    //
    // long countResultDr = countResultDr(bizMsg);
    // long countResultCr = countResultCr(bizMsg);
    // long totalCount = countResultDr + countResultCr;
    //
    // return Long.toString(totalCount);
    // }

    /**
     * Method name: countResultDr
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @return long
     */
    public long countResultDr(NFAL0120CMsg bizMsg) {

        long queryResCnt = 0;
        try {
            // ------------ start CSA modification 
            S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().countResultDr(bizMsg, false);
            
            if (ssmResult.isCodeNotFound()) {
                // try to saerch from Archive
                ssmResult = NFAL0120Query.getInstance().countResultDr(bizMsg, true);
            }
            
            // ----------- end
            
            if (ssmResult.isCodeNormal()) {

                List resultList = (List) ssmResult.getResultObject();
                if (resultList != null && resultList.size() > 0) {
                    Map map = (Map) resultList.get(0);
                    BigDecimal count = parts.checkNull((BigDecimal) map.get(DR_TOT_COUNT));
                    queryResCnt = count.longValue();
                }
            }
        } catch (Exception e) {
            // NFAM0035E=0, @ couldn't be completed by unexpected
            // reason. The detailed error message: "@".
            bizMsg.setMessageInfo("NFAM0035E", new String[] {e.getMessage() });
        }
        return queryResCnt;
    }

    /**
     * Method name: countResultCr
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @return long
     */
    public long countResultCr(NFAL0120CMsg bizMsg) {

        long queryResCnt = 0;
        try {
            // -------------- start CSA modification
            S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().countResultCr(bizMsg, false);
            
            if (ssmResult.isCodeNotFound()) {
                // try to search from Archive
                ssmResult = NFAL0120Query.getInstance().countResultCr(bizMsg, true);
            }
            // -------------- end
            
            if (ssmResult.isCodeNormal()) {

                List resultList = (List) ssmResult.getResultObject();
                if (resultList != null && resultList.size() > 0) {
                    Map map = (Map) resultList.get(0);
                    BigDecimal count = parts.checkNull((BigDecimal) map.get(CR_TOT_COUNT));
                    queryResCnt = count.longValue();
                }
            }
        } catch (Exception e) {
            // NFAM0035E=0, @ couldn't be completed by unexpected
            // reason. The detailed error message: "@".
            bizMsg.setMessageInfo("NFAM0035E", new String[] {e.getMessage() });
        }
        return queryResCnt;
    }

    /**
     * Method name: setDrCrAmtCell
     * <dd>The method Clear Credit amount for Debit, and Clear Debit
     * amount for Credit record
     * <dd>Remarks:
     * @param globalMsg S-Msg
     * @param i Array index
     */
    public static void setDrCrAmtCell(NFAL0120SMsg globalMsg, int i) {
        String dOrC = globalMsg.A.no(i).xxScrId_A.getValue();
        if (dOrC.equals(DEBIT_NUM_STR)) {
            // Clear Credit amount for Debit record
            globalMsg.A.no(i).jrnlFuncCrAmt_A.clear();
        } else if (dOrC.equals(CREDIT_NUM_STR)) {
            // Clear Debit amount for Credit record
            globalMsg.A.no(i).jrnlFuncDrAmt_A.clear();
        }
    }

    /**
     * Method name: clearSearchResult
     * <dd>Remarks:
     * @param bizMsg NFAL0120CMsg
     */
    public void clearSearchResult(NFAL0120CMsg bizMsg) {
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.xxTotPrcAmt_DR.clear();
        bizMsg.xxTotPrcAmt_CR.clear();
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
    }
    
    //---- start 2016/05/31 add
    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NFCL3050CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NFAL0120CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NFAL0120Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_H.clear();
            bizMsg.srchOptNm_H.clear();
            return;
        }

        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_H.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_H.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_H.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }
    
    /**
     * getYYYYMM
     * @param String period name. Should be XXX-99 format.
     * @return String
     */
    public static String getYYYYMM(String perNm) {
        
        if (!hasValue(perNm)) {
            return null;
        }
        
        String monthNm = perNm.substring(0, 3);
        // When 21XX, it won't work...
        String YYYY = "20".concat(perNm.substring(4, 6));
        String MM = "";
        
        if ("JAN".equals(monthNm)) {
            MM = "01";
        } else if ("FEB".equals(monthNm)) {
            MM = "02";
        } else if ("MAR".equals(monthNm)) {
            MM = "03";
        } else if ("APR".equals(monthNm)) {
            MM = "04";
        } else if ("MAY".equals(monthNm)) {
            MM = "05";
        } else if ("JUN".equals(monthNm)) {
            MM = "06";
        } else if ("JUL".equals(monthNm)) {
            MM = "07";
        } else if ("AUG".equals(monthNm)) {
            MM = "08";
        } else if ("SEP".equals(monthNm)) {
            MM = "09";
        } else if ("OCT".equals(monthNm)) {
            MM = "10";
        } else if ("NOV".equals(monthNm)) {
            MM = "11";
        } else if ("DEC".equals(monthNm)) {
            MM = "12";
        } 
        
        return YYYY.concat(MM);
    }
    
    /**
     * isExistSaveSearchName
     * @param bizMsg NFAL0120CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFAL0120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    private static boolean callNszc0330(NFAL0120CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NFAL0120CMsg
     * @param glblMsg NFAL0120SMsg
     * @param srchOptUsrId String
     * @praram glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NFAL0120CMsg bizMsg, NFAL0120SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFAL0120Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.glDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.glDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.sysSrcCd_3.getValue());
        // START 2017/12/04 [QC#12525, DEL]
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.jrnlCatgNm.getValue());
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.coaAcctCd_FM.getValue());
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.coaAcctCd_TO.getValue());
        // END   2017/12/04 [QC#12525, DEL]
        if (hasValue(bizMsg.jrnlDealDrAmt_FM)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.jrnlDealDrAmt_FM.getValue().toString());
        }
        if (hasValue(bizMsg.jrnlDealDrAmt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.jrnlDealDrAmt_TO.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.glPerNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.xxQueryFltrTxt.getValue());
        // START 2017/12/04 [QC#12525, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.jrnlCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.coaCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.coaCcCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.coaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.coaProdCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.coaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.coaAfflCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.coaProjCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.coaExtnCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.ajeTrxTpCd_3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.xxQueryFltrTxt_AT.getValue());
        // END   2017/12/04 [QC#12525, ADD]

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(NFCL3050Constant.SCRN_ID_00, "Save Search") });
        }

    }    
    
    private static boolean isSameSaveSearchName(NFAL0120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NFAL0120CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NFAL0120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H.no(i));
            }
        }
        return;
    }
    
    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NFAL0120CMsg
     * @param glblMsg NFAL0120SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NFAL0120CMsg bizMsg, NFAL0120SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFAL0120Constant.BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I"
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }
    
    /**
     * callNszc0330forSearchOption
     * @param bizMsg NFAL0120CMsg
     * @param glblMsg NFAL0120SMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchOption(NFAL0120CMsg bizMsg, NFAL0120SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFCL3050Constant.BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        if (hasValue(pMsg.srchOptTxt_01)) {
            if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_01.getValue(), "yyyyMMdd")) {
                // START 2016/09/14 [QC#13472, MOD]
                ZYPEZDItemValueSetter.setValue(bizMsg.glDt_FR, pMsg.srchOptTxt_01.getValue());
                // END 2016/09/14 [QC#13472, MOD]
            } else {
                bizMsg.glDt_FR.clear();
            }
        }
        if (hasValue(pMsg.srchOptTxt_02)) {
            if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_02.getValue(), "yyyyMMdd")) {
                // START 2016/09/14 [QC#13472, MOD]
                ZYPEZDItemValueSetter.setValue(bizMsg.glDt_TO, pMsg.srchOptTxt_02.getValue());
                // END 2016/09/14 [QC#13472, MOD]
            } else {
                bizMsg.glDt_TO.clear();
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcCd_3, pMsg.srchOptTxt_03);
        // START 2017/12/04 [QC#12525, DEL]
        // ZYPEZDItemValueSetter.setValue(bizMsg.jrnlCatgNm, pMsg.srchOptTxt_04);
        // ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_FM, pMsg.srchOptTxt_05);
        // ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_TO, pMsg.srchOptTxt_06);
        // END   2017/12/04 [QC#12525, DEL]

        if (isNumberCheck(pMsg.srchOptTxt_07.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.jrnlDealDrAmt_FM, new BigDecimal(pMsg.srchOptTxt_07.getValue()));
        } else {
            bizMsg.jrnlDealDrAmt_FM.clear();
        }
        if (isNumberCheck(pMsg.srchOptTxt_08.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.jrnlDealDrAmt_TO, new BigDecimal(pMsg.srchOptTxt_08.getValue()));
        } else {
            bizMsg.jrnlDealDrAmt_TO.clear();
        }
        
        ZYPEZDItemValueSetter.setValue(bizMsg.glPerNm, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxQueryFltrTxt, pMsg.srchOptTxt_10);

        // START 2017/12/04 [QC#12525, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.jrnlCatgCd, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.ajeTrxTpCd_3, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxQueryFltrTxt_AT, pMsg.srchOptTxt_22);
        // END   2017/12/04 [QC#12525, ADD]
    }
    
    /**
     * loadOnePageToCMsg
     * @param bizMsg NFAL0120CMsg
     * @param glblMsg NFAL0120SMsg
     */
    public static void loadOnePageToCMsg(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {
        int i = 0;
        for (; i < globalMsg.A.getValidCount(); i++) {
            if (i == bizMsg.A.length()) {
                break;
            }
            setDrCrAmtCell(globalMsg, i);
            EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
        }
        bizMsg.A.setValidCount(i);
        
    }
    
    //---- end 2016/05/31

    // START 2017/12/04 [QC#12525, ADD]
    /**
     * Journal Category Validation
     * @param bizMsg
     * @param glblCmpyCd
     * @return  boolean
     */
    public static boolean jrnlCatgSearch(NFAL0120CMsg bizMsg, String glblCmpyCd) {
        JRNL_CATGTMsg tMsg = new JRNL_CATGTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlCatgCd, bizMsg.jrnlCatgCd);

        JRNL_CATGTMsg rslt = (JRNL_CATGTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (rslt == null) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.jrnlCatgNm, rslt.jrnlCatgNm.getValue());
            return true;
        }
    }
    // END   2017/12/04 [QC#12525, ADD]

    // START 2018/10/25 S.Ohki [QC#28869,ADD]
    public static void outputSearchLog(NFAL0120CMsg cMsg) {

        StringBuffer sb = new StringBuffer();
        sb.append("NFAL0120 Search Condition : ");
        if (ZYPCommonFunc.hasValue(cMsg.glDt_FR) || ZYPCommonFunc.hasValue(cMsg.glDt_TO)) {
        	sb.append(" GL Date[").append(cMsg.glDt_FR.getValue()).append("-").append(cMsg.glDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.sysSrcCd_3)) {
            sb.append(" Journal Source[").append(cMsg.sysSrcCd_3.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.jrnlCatgCd)) {
            sb.append(" Journal Category[").append(cMsg.jrnlCatgCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaCmpyCd)) {
            sb.append(" Company Code[").append(cMsg.coaCmpyCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaBrCd)) {
            sb.append(" Branch (*)[").append(cMsg.coaBrCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaCcCd)) {
            sb.append(" Cost Center (*)[").append(cMsg.coaCcCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaAcctCd)) {
            sb.append(" Natural Account (*)[").append(cMsg.coaAcctCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaProdCd)) {
            sb.append(" Product Code (*)[").append(cMsg.coaProdCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaChCd)) {
            sb.append(" Sales Channel (*)[").append(cMsg.coaChCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaAfflCd)) {
            sb.append(" Intercompany Code (*) [").append(cMsg.coaAfflCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaProjCd)) {
            sb.append(" Merchandise Type (*)[").append(cMsg.coaProjCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaExtnCd)) {
            sb.append(" Business Unit (*)[").append(cMsg.coaExtnCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.ajeTrxTpCd_3)) {
            sb.append(" Transaction Type[").append(cMsg.ajeTrxTpCd_3.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_AT)) {
            sb.append(" Transaction Code[").append(cMsg.xxQueryFltrTxt_AT.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.jrnlDealDrAmt_FM) || ZYPCommonFunc.hasValue(cMsg.jrnlDealDrAmt_TO)) {
        	sb.append(" Amount[").append(cMsg.jrnlDealDrAmt_FM.getValue()).append("-").append(cMsg.jrnlDealDrAmt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.glPerNm)) {
            sb.append(" GL Period[").append(cMsg.glPerNm.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt)) {
            sb.append(" Reference Search (*)[").append(cMsg.xxQueryFltrTxt.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/10/25 S.Ohki [QC#28869,ADD]
}
