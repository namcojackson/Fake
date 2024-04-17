package business.blap.NFDL0040.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsgArray;
import business.blap.NFDL0040.NFDL0040CMsg;
import business.blap.NFDL0040.NFDL0040Query;
import business.blap.NFDL0040.NFDL0040SMsg;
import business.blap.NFDL0040.constant.NFDL0040Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRMS_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2023/05/26   Hitachi         S.Nakatani      Update          QC#61271
 *</pre>
 */
public class NFDL0040CommonLogic implements NFDL0040Constant {

    public static boolean copyPage(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        if (globalMsg.A.getValidCount() > 0) {
            bizMsgAry = bizMsg.A;
            shareMsgAry = globalMsg.A;
            int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
                return true;
            }
            for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
                EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
            }
        }

        boolean hasError = false;

        if (globalMsg.B.getValidCount() > 0) {
            bizMsgAry = bizMsg.B;
            shareMsgAry = globalMsg.B;
            int startIndex = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_B.getValueInt() < 0) {
                return true;
            }
            for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
                // START 2018/04/23 [QC#20940, MOD]
                String cltDsptStsCd_BB = globalMsg.B.no(startIndex + dispRowNum).cltDsptStsCd_BB.getValue();
                EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
                globalMsg.B.no(startIndex + dispRowNum).cltDsptStsCd_BB.setValue(cltDsptStsCd_BB);
                // START 2018/04/23 [QC#20940, MOD]
            }
        }
        bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        return !hasError;
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(String glblCmpyCd, NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        if (globalMsg.A.getValidCount() > 0) {
            int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.A.length() && startIndex + dispRowNum < globalMsg.A.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.A.get(startIndex + dispRowNum), null, bizMsg.A.get(dispRowNum), null);
            }
            bizMsg.A.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_A.setValue(globalMsg.A.getValidCount());
        }
        if (globalMsg.B.getValidCount() > 0) {
            int startIndex = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_B.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.B.length() && startIndex + dispRowNum < globalMsg.B.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.B.get(startIndex + dispRowNum), null, bizMsg.B.get(dispRowNum), null);

            }
            bizMsg.B.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_B.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_B.setValue(globalMsg.B.getValidCount());
        }
    }

    /**
     * prev Page.(copy from SMsg to CMsg)
     * @param bizMsg .
     * @param bizMsgAry CMsg.
     * @param shareMsgAry SMsg.
     */
    public static void prevPage(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length());
        } else if (bizMsg.xxPageTblNm.getValue().equals(TABLE_B)) {
            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() - bizMsg.B.length());
        }
    }

    /**
     * next Page.(copy from SMsg to CMsg)
     * @param bizMsg .
     * @param bizMsgAry CMsg.
     * @param shareMsgAry SMsg.
     */
    public static void nextPage(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsg.A.length());
        } else if (bizMsg.xxPageTblNm.getValue().equals(TABLE_B)) {
            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() + bizMsg.B.length());
        }
    }

    /**
     * next Page.(copy from SMsg to CMsg)
     * @param bizMsg .
     * @param bizMsgAry CMsg.
     * @param shareMsgAry SMsg.
     */
    public static BigDecimal getPageShowFrom(int index, NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        int pageCnt = index / bizMsg.A.length();
        int pageShowFrom = bizMsg.A.length() * pageCnt + 1;
        if (index % bizMsg.A.length() == 0) {
            pageShowFrom = pageShowFrom - bizMsg.A.length();
        }
        return new BigDecimal(pageShowFrom);
    }

    /**
     * next Page.(copy from SMsg to CMsg)
     * @param bizMsg .
     * @param bizMsgAry CMsg.
     * @param shareMsgAry SMsg.
     */
    public static void lastPage(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {

        if (bizMsg.xxDplyTab.getValue().equals(TAB_PROMISE)) {
            BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
            bizMsg.xxPageShowFromNum_A.setValue(lastPageFromNum);

        } else if (bizMsg.xxDplyTab.getValue().equals(TAB_DISPUTE)) {
            BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
            bizMsg.xxPageShowFromNum_B.setValue(lastPageFromNum);
        }
    }

    /**
     * next Page.(copy from SMsg to CMsg)
     * @param bizMsg .
     * @param bizMsgAry CMsg.
     * @param shareMsgAry SMsg.
     */
    public static BigDecimal getLastPageFromNum(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        if (bizMsg.xxDplyTab.getValue().equals(TAB_PROMISE)) {
            // count last page number
            int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
            int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
            if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
                lastPageFromNum = lastPageFromNum - bizMsg.A.length();
            }
            return new BigDecimal(lastPageFromNum);

        } else {
            // count last page number
            int pageCnt = globalMsg.B.getValidCount() / bizMsg.B.length();
            int lastPageFromNum = bizMsg.B.length() * pageCnt + 1;
            if (globalMsg.B.getValidCount() % bizMsg.B.length() == 0) {
                lastPageFromNum = lastPageFromNum - bizMsg.B.length();
            }
            return new BigDecimal(lastPageFromNum);
        }
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    public static int getCollectablePromiseCount(String glblCmpyCd, NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("onAccount", STR_ON_ACCOUNT);
        ssmMap.put("cltPrmsStsCd", CLT_PRMS_STS.COLLECTIBLE);
        ssmMap.put("cMsg", bizMsg);
        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
        List<String> arTrxNumList = splitAndToInvList(bizMsg.xxTrxNumSrchTxt.getValue());
        ssmMap.put("arTrxNumList", arTrxNumList);
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]

        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getCollectablePromiseCount(ssmMap);
        if (ssmResult.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) ssmResult.getResultObject();
            return cnt.intValue();
        } else {
            return BigDecimal.ZERO.intValue();
        }
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    public static boolean isInvalidDisputeAmt(String glblCmpyCd, NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        BigDecimal sumAmt = BigDecimal.ZERO;
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            // START 2018/10/04 J.Kim [QC#28571,MOD]
            String cltDsptStsCd = globalMsg.B.no(i).cltDsptStsCd_B.getValue();
            if (!CLT_DSPT_STS.CLOSED.equals(cltDsptStsCd)) {
                sumAmt = sumAmt.add(globalMsg.B.no(i).dealCltDsptAmt_B.getValue());
            }
            // END 2018/10/04 J.Kim [QC#28571,MOD]
        }
        BigDecimal invBalAmt = getActualInvoiceBalance(glblCmpyCd, bizMsg, globalMsg);
        if (invBalAmt.compareTo(sumAmt) < 0) {
            return true;
        }
        return false;
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    public static BigDecimal getActualInvoiceBalance(String glblCmpyCd, NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cMsg", bizMsg);
        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
        List<String> arTrxNumList = splitAndToInvList(bizMsg.xxTrxNumSrchTxt.getValue());
        ssmMap.put("arTrxNumList", arTrxNumList);
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]

        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getActualInvoiceBalance(ssmMap);
        if (ssmResult.isCodeNormal()) {
            BigDecimal balAmt = (BigDecimal) ssmResult.getResultObject();
            return balAmt;
        } else {
            return BigDecimal.ZERO;
        }
    }

    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
    /**
     * @param glblCmpyCd Global Company Code
     * @param bizMsg Business Component Interface Message
     * @return Integer
     */
    public static int getOpenDisputeCount(String glblCmpyCd, NFDL0040CMsg bizMsg) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cltDsptStsCd", CLT_DSPT_STS.APPROVED);
        List<String> arTrxNumList = splitAndToInvList(bizMsg.xxTrxNumSrchTxt.getValue());
        ssmMap.put("arTrxNumList", arTrxNumList);
        ssmMap.put("cMsg", bizMsg);

        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getOpenDisputeCount(ssmMap);
        if (ssmResult.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) ssmResult.getResultObject();
            return cnt.intValue();
        } else {
            return BigDecimal.ZERO.intValue();
        }
    }

    /**
     * @param String multiInvTxt
     * @return List<String>
     */
    public static List<String> splitAndToInvList(String multiInvTxt) {
        List<String> arTrxNumList = new ArrayList<String>();
        String[] multiInv = multiInvTxt.split(",");

        arTrxNumList.addAll(Arrays.asList(multiInv));
        return arTrxNumList;
    }

    /**
     * @param glblCmpyCd Global Company Code
     * @param bizMsg Business Component Interface Message
     * @param arTrxNum Accounts Receivable Transaction Number
     * @return Integer
     */
    public static Map getArTrxBalData(String glblCmpyCd, NFDL0040CMsg bizMsg, String arTrxNum) {
        // search arTrxBal(Invoice#) AR_TRX_BAL data
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cMsg", bizMsg);
        List<String> arTrxNumList = NFDL0040CommonLogic.splitAndToInvList(arTrxNum);
        ssmMap.put("arTrxNumList", arTrxNumList);
        S21SsmEZDResult ssmResult = NFDL0040Query.getInstance().getArTrxBalData(ssmMap);

        if (ssmResult.isCodeNormal()) {
            Map<BigDecimal, Object> arTrxBalResultMap = (Map<BigDecimal, Object>) ssmResult.getResultObject();
            if (arTrxBalResultMap == null) {
                bizMsg.xxTrxNumSrchTxt.setErrorInfo(1, "NFCM0807E", new String[] {"Invoice#", arTrxNum, "AR_TRX_BAL" });
                return null;
            }

            Map<String, BigDecimal> resMap = new HashMap<String, BigDecimal>();
            resMap.put("DEAL_RMNG_BAL_GRS_AMT", (BigDecimal) arTrxBalResultMap.get("DEAL_RMNG_BAL_GRS_AMT"));
            resMap.put("FUNC_RMNG_BAL_GRS_AMT", (BigDecimal) arTrxBalResultMap.get("FUNC_RMNG_BAL_GRS_AMT"));
            return resMap;

        } else {
            bizMsg.xxTrxNumSrchTxt.setErrorInfo(1, "NFCM0807E", new String[] {"Invoice#", arTrxNum, "AR_TRX_BAL" });
            return null;
        }
    }
    // END 2023/05/26 S.Nakatani [QC#61271,ADD]
}