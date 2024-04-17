package business.blap.NFCL3020.common;

import static business.blap.NFCL3020.constant.NFCL3020Constant.BIZ_ID;
import static business.blap.NFCL3020.constant.NFCL3020Constant.VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3020.NFCL3020CMsg;
import business.blap.NFCL3020.NFCL3020Query;
import business.blap.NFCL3020.NFCL3020SMsg;
import business.blap.NFCL3020.constant.NFCL3020Constant;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.DS_BANK_ACCTTMsg;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NFZC202001PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 03/16/2016   Fujitsu         T.Tanaka        Update          Def#3269
 * 09/05/2016   Fujitsu         C.Tanaka        Update          QC#5521
 * 01/10/2018   Fujitsu         T.Murai         Update          QC#21400
 * 2018/01/17   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/03/08   Fujitsu         H.Ikeda         Update          QC#24469
 * 2018/03/14   Fujitsu         H.Ikeda         Update          QC#24801
 * 2018/04/03   Fujitsu         H.Ikeda         Update          QC#21737-1
 * 2018/04/06   Fujitsu         H.Ikeda         Update          QC#25338
 * 2018/05/09   Fujitsu         Y.Matsui        Update          QC#25856
 * 2018/06/04   Fujitsu         Y.Matsui        Update          QC#25368
 * 2018/07/13   Fujitsu         Y.Matsui        Update          QC#26993
 * 2019/07/16   Fujitsu         H.Ikeda         Update          QC#51515
 * 2019/09/02   Fujitsu         M.Ishii         Update          QC#53097
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3020CommonLogic {
    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFCL3020CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_B.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_B.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_B.setValue(shareMsgAry.getValidCount());
    }

    // START 2018/01/16 H.Ikeda [QC#22759, ADD]
    /**
     * Search Bill To Customer Account Name
     * @param cMsg NFCL3020CMsg
     */
    public static void searchAddressForBillToCustomerAccount(NFCL3020CMsg cMsg) {

        SELL_TO_CUSTTMsgArray outSellToCustTMsg = NFCL3020Query.findBillToAcctCust(cMsg);

        if (outSellToCustTMsg.getValidCount() == 0) {
            cMsg.payerCustCd_BH.setErrorInfo(1, "NFCM0502E", new String[] {"Customer" });
            return;
        }
        cMsg.dsAcctNm_BH.setValue(outSellToCustTMsg.no(0).dsAcctNm.getValue());
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        cMsg.invNum_BH.clear();
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    }
    // END  2018/01/16 H.Ikeda [QC#22759, ADD]

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NFCL3020CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        int startIndex = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_B.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020SMsg
     */
    public static void prevPage(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        bizMsgAry = bizMsg.B;
        shareMsgAry = globalMsg.B;
        bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() - bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Next Page
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020SMsg
     */
    public static void nextPage(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.B;
        shareMsgAry = globalMsg.B;
        bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() + bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020SMsg
     */
    public static void lastPage(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.B;
        shareMsgAry = globalMsg.B;
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum_B.setValue(lastPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {
        int pageCnt = globalMsg.B.getValidCount() / bizMsg.B.length();
        int lastPageFromNum = bizMsg.B.length() * pageCnt + 1;
        if (globalMsg.B.getValidCount() % bizMsg.B.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.B.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

    // START 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param dataCount int
     */
    public static void setPageData(NFCL3020SMsg globalMsg, NFCL3020CMsg bizMsg, int dataCount) {

        if (0 == dataCount) {
            ZYPTableUtil.clear(bizMsg.B);
            bizMsg.xxPageShowFromNum_B.clear();
            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
            return;
        } else {
            // do nothing
        }

        int page = dataCount / bizMsg.B.length();
        int rest = dataCount % bizMsg.B.length();
        int fromNum = 0;

        if (0 == rest) {
            fromNum = ((page - 1) * bizMsg.B.length());
        } else {
            fromNum = (page * bizMsg.B.length());
        }

        setGlobalMsgToBizMsg(globalMsg, bizMsg, fromNum, fromNum);

        bizMsg.xxPageShowFromNum_B.setValue(fromNum + 1);
        bizMsg.xxPageShowToNum_B.setValue(fromNum + bizMsg.B.getValidCount());
        bizMsg.xxPageShowOfNum_B.setValue(globalMsg.B.getValidCount());

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setGlobalMsgToBizMsg(NFCL3020SMsg globalMsg, NFCL3020CMsg bizMsg, int index, int pagenationFrom) {

        ZYPTableUtil.clear(bizMsg.B);
        int i = index;
        for (; i < pagenationFrom + bizMsg.B.length(); i++) {
            if (i < globalMsg.B.getValidCount()) {
                EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.B.setValidCount(i - pagenationFrom);
        bizMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum_B.setValue(pagenationFrom + bizMsg.B.getValidCount());
        bizMsg.xxPageShowOfNum_B.setValue(globalMsg.B.getValidCount());
    }

    // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Recalculation
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020SMsg
     * @param calcAmt BigDecimal
     * @param calcCnt BigDecimal
     */
    // Mod Start 2018/03/07 S21_NA#24469
    //public static void reCalc(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {
    public static void reCalc(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg, String type, BigDecimal calcAmt, BigDecimal calcCnt) {
    // Mod End   2018/03/07 S21_NA#24469
        BigDecimal totAmt_Active = BigDecimal.ZERO;
        BigDecimal totAmt_Reversed = BigDecimal.ZERO;
        int totCnt_Active = 0;
        int totCnt_Reversed = 0;
        
        BigDecimal arBatRcptAmt = BigDecimal.ZERO;
        BigDecimal arBatRcptCnt = BigDecimal.ZERO;

        // Mod Start 2018/03/07 S21_NA#24469
        //for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
        //    if(globalMsg.B.no(i).arCashApplyStsCd_B.getValue().equals(AR_CASH_APPLY_STS.VOID)) {
        //        totAmt_Reversed = totAmt_Reversed.add(globalMsg.B.no(i).funcRcptAmt_B.getValue());
        //        totCnt_Reversed++;
        //    } else {
        //        totAmt_Active = totAmt_Active.add(globalMsg.B.no(i).funcRcptAmt_B.getValue());
        //        totCnt_Active++;
        //    }
        //}
        if(NFCL3020Constant.TYPE_INIT.equals(type)){
            Map<String, Object> ssmMap = new HashMap<String, Object>();
            ssmMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmMap.put("cMsg", bizMsg);
            ssmMap.put("Asterisk", NFCL3020Constant.ASTERISK);
            S21SsmEZDResult ssmResult = NFCL3020Query.getInstance().getRcptAmtList(bizMsg, ssmMap);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> ssmResultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (int i = 0; i < ssmResultList.size(); i++) {
                    Map<String, Object> ssmResultListData = ssmResultList.get(i);
                    BigDecimal funcRcptAmt = (BigDecimal) ssmResultListData.get("FUNC_RCPT_AMT");
                    if (AR_CASH_APPLY_STS.VOID.equals((String) ssmResultListData.get("AR_CASH_APPLY_STS_CD"))) {
                        totAmt_Reversed = totAmt_Reversed.add(funcRcptAmt);
                        totCnt_Reversed++;
                    } else {
                        totAmt_Active = totAmt_Active.add(funcRcptAmt);
                        totCnt_Active++;
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptAmt_H)) {
                arBatRcptAmt = bizMsg.arBatRcptAmt_H.getValue();
            }

            if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptCnt_H)) {
                arBatRcptCnt = bizMsg.arBatRcptCnt_H.getValue();
            }
            // Active Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, totAmt_Active);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H1, new BigDecimal(totCnt_Active));

            // Reversed Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H2, totAmt_Reversed);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H2, new BigDecimal(totCnt_Reversed));

            // Difference Amount
            // START 2019/07/16 H.Ikeda [QC#51515, MOD]
            //ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H3, arBatRcptAmt.subtract(totAmt_Active).subtract(totAmt_Reversed));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H3, arBatRcptAmt.subtract(totAmt_Active));
            // END   2019/07/16 H.Ikeda [QC#51515, MOD]
            // START 2017/07/06 E.Kameishi [QC#19740, MOD]
            if (AR_LOCK_BOX.CFS_LOCKBOX.equals(bizMsg.arLockBoxCd_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, BigDecimal.ZERO);
            } else {
                // START 2019/07/16 H.Ikeda [QC#51515, MOD]
                //ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, arBatRcptCnt.subtract(new BigDecimal(totCnt_Active)).subtract(new BigDecimal(totCnt_Reversed)));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, arBatRcptCnt.subtract(new BigDecimal(totCnt_Active)));
                // END   2019/07/16 H.Ikeda [QC#51515, MOD]
            }
            // END 2017/07/06 E.Kameishi [QC#19740, MOD]

            // START 2018/07/13 Y.Matsui [QC#26993, MOD]
            NFCL3020CommonLogic.reCalcArBatRcptStatus(bizMsg, globalMsg);
            // END   2018/07/13 Y.Matsui [QC#26993, MOD]

        } else if (NFCL3020Constant.TYPE_ADD.equals(type)) {
            // Active Amount
            // Add Start 2018/03/14 S21_NA#24801
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotAmt_H1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, totAmt_Active);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotCnt_H1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H1, new BigDecimal(totCnt_Active));
            }
            // Add End   2018/03/14 S21_NA#24801
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, bizMsg.xxTotAmt_H1.getValue().add(calcAmt));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H1, bizMsg.xxTotCnt_H1.getValue().add(calcCnt));

            if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptAmt_H)) {
                arBatRcptAmt = bizMsg.arBatRcptAmt_H.getValue();
            }
            if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptCnt_H)) {
                arBatRcptCnt = bizMsg.arBatRcptCnt_H.getValue();
            }
            // Difference Amount
            // Add Start 2018/03/14 S21_NA#24801
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotAmt_H2)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H2, totAmt_Reversed);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotCnt_H2)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H2, new BigDecimal(totCnt_Reversed));
            }
            // Add End   2018/03/14 S21_NA#24801
            // START 2019/07/16 H.Ikeda [QC#51515, MOD]
            //ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H3, arBatRcptAmt.subtract(bizMsg.xxTotAmt_H1.getValue()).subtract(bizMsg.xxTotAmt_H2.getValue()));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H3, arBatRcptAmt.subtract(bizMsg.xxTotAmt_H1.getValue()));
            // END   2019/07/16 H.Ikeda [QC#51515, MOD]
            if (AR_LOCK_BOX.CFS_LOCKBOX.equals(bizMsg.arLockBoxCd_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, BigDecimal.ZERO);
            } else {
                // START 2019/07/16 H.Ikeda [QC#51515, MOD]
                //ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, arBatRcptCnt.subtract(bizMsg.xxTotCnt_H1.getValue()).subtract(bizMsg.xxTotCnt_H2.getValue()));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, arBatRcptCnt.subtract(bizMsg.xxTotCnt_H1.getValue()));
                // END   2019/07/16 H.Ikeda [QC#51515, MOD]
            }
        } else{
            // Active Amount
            // Add Start 2018/03/14 S21_NA#24801
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotAmt_H1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, totAmt_Active);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotCnt_H1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H1, new BigDecimal(totCnt_Active));
            }
            // Add End   2018/03/14 S21_NA#24801
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, bizMsg.xxTotAmt_H1.getValue().subtract(calcAmt));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H1, bizMsg.xxTotCnt_H1.getValue().subtract(calcCnt));

            if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptAmt_H)) {
                arBatRcptAmt = bizMsg.arBatRcptAmt_H.getValue();
            }
            if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptCnt_H)) {
                arBatRcptCnt = bizMsg.arBatRcptCnt_H.getValue();
            }
            // Difference Amount
            // Add Start 2018/03/14 S21_NA#24801
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotAmt_H2)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H2, totAmt_Reversed);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTotCnt_H2)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H2, new BigDecimal(totCnt_Reversed));
            }
            // Add End   2018/03/14 S21_NA#24801
            // START 2019/07/16 H.Ikeda [QC#51515, MOD]
            //ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H3, arBatRcptAmt.subtract(bizMsg.xxTotAmt_H1.getValue()).subtract(bizMsg.xxTotAmt_H2.getValue()));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H3, arBatRcptAmt.subtract(bizMsg.xxTotAmt_H1.getValue()));
            // END   2019/07/16 H.Ikeda [QC#51515, MOD]
            if (AR_LOCK_BOX.CFS_LOCKBOX.equals(bizMsg.arLockBoxCd_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, BigDecimal.ZERO);
            } else {
                // START 2019/07/16 H.Ikeda [QC#51515, MOD]
                //ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, arBatRcptCnt.subtract(bizMsg.xxTotCnt_H1.getValue()).subtract(bizMsg.xxTotCnt_H2.getValue()));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotCnt_H3, arBatRcptCnt.subtract(bizMsg.xxTotCnt_H1.getValue()));
                // END   2019/07/16 H.Ikeda [QC#51515, MOD]
            }
        }
        // Mod End   2018/03/07 S21_NA#24469
    }

    /**
     * Create Pull Down List(Bank)
     * @param glblCmpyCd String
     * @param bizMsg NFCL3020CMsg
     */
    public static void createPulldownListBank(String glblCmpyCd, NFCL3020CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult dsBank = NFCL3020Query.getInstance().getBankPullDownList(bizMsg, ssmParam);

        if (dsBank.isCodeNormal()) {
            List<Map> dsBankList = (List<Map>) dsBank.getResultObject();
            initPullDownCreate(bizMsg.dsBankCd_LC, bizMsg.dsBankNm_LD, dsBankList, new String[] {"DS_BANK_CD", "DS_BANK_NM" });
        }
    }

    /**
     * Create Pull Down List(Bank Branch)
     * @param glblCmpyCd String
     * @param bizMsg NFCL3020CMsg
     */
    public static void createPulldownListBankBr(String glblCmpyCd, NFCL3020CMsg bizMsg) {

        List<Map> dsBankBrList = new ArrayList<Map>();
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankCd_H)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsBankCd", bizMsg.dsBankCd_H.getValue());
            S21SsmEZDResult dsBankBr = NFCL3020Query.getInstance().getBankBrPullDownList(bizMsg, ssmParam);

            if (dsBankBr.isCodeNormal()) {
                dsBankBrList = (List<Map>) dsBankBr.getResultObject();
                initPullDownCreate(bizMsg.bankRteNum_LC, bizMsg.dsBankBrNm_LD, dsBankBrList, new String[] {"BANK_RTE_NUM", "DS_BANK_BR_NM" });
                createPulldownListBankAccount(glblCmpyCd, bizMsg);
            }
        }
    }

    /**
     * Create Pull Down List(Bank Account)
     * @param glblCmpyCd String
     * @param bizMsg NFCL3020CMsg
     */
    public static void createPulldownListBankAccount(String glblCmpyCd, NFCL3020CMsg bizMsg) {
        List<Map> dsBankAcctList = new ArrayList<Map>();
        if (ZYPCommonFunc.hasValue(bizMsg.bankRteNum_H)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsBankCd", bizMsg.dsBankCd_H.getValue());
            ssmParam.put("bankRteNum", bizMsg.bankRteNum_H.getValue());
            ssmParam.put("dsBankAcctTpCd", DS_BANK_ACCT_TP.INTERNAL);
            S21SsmEZDResult dsBankAcct = NFCL3020Query.getInstance().getBankAccountPullDownList(bizMsg, ssmParam);

            if (dsBankAcct.isCodeNormal()) {
                dsBankAcctList = (List<Map>) dsBankAcct.getResultObject();
                initPullDownCreate(bizMsg.remDsBankAcctPk_LC, bizMsg.dsBankAcctNm_LD, dsBankAcctList, new String[] {"DS_BANK_ACCT_PK", "DS_BANK_ACCT_NUM", "DS_BANK_ACCT_NM" });
            }
        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListArRcptTrxTp(String glblCmpyCd, NFCL3020CMsg bizMsg) {
        List<Map> arRcptTrxTpList = new ArrayList<Map>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        String arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue("AR_NFCL3020_RCPT_TRX_TP", glblCmpyCd);
        ssmParam.put("arRcptTrxTpCd", arRcptTrxTpCdList);

        S21SsmEZDResult arRcptTrxTp = NFCL3020Query.getInstance().geArRcptTrxTpPullDownList(bizMsg, ssmParam);

        if (arRcptTrxTp.isCodeNormal()) {
            arRcptTrxTpList = (List<Map>) arRcptTrxTp.getResultObject();
            initPullDownCreate(bizMsg.arRcptTrxTpCd_LC, bizMsg.arRcptTrxTpNm_LD, arRcptTrxTpList, new String[] {"AR_RCPT_TRX_TP_CD", "AR_RCPT_TRX_TP_NM"});
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.arRcptTrxTpCd_BH, "00");
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListArBatRcptSts(String glblCmpyCd, NFCL3020CMsg bizMsg) {
        List<Map> arBatRcptStsList = new ArrayList<Map>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult arBatRcptSts = NFCL3020Query.getInstance().geArBatRcptStsPullDownList(bizMsg, ssmParam);
        if (arBatRcptSts.isCodeNormal()) {
            arBatRcptStsList = (List<Map>) arBatRcptSts.getResultObject();
            initPullDownCreate(bizMsg.arBatRcptStsCd_LC, bizMsg.arBatRcptStsNm_LD, arBatRcptStsList, new String[] {"AR_BAT_RCPT_STS_CD", "AR_BAT_RCPT_STS_NM"});
        }
    }

    /**
     * Initialize Pull Down Create
     * @param pulldownCd BigDecimal
     * @param pulldownName EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    private static void initPullDownCreate(EZDCStringItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));
//            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[0]), ":", (String) pullDownData.get(dbColumn[1])));
        }
    }

    /**
     * Initialize Pull Down Create
     * @param pulldownCd String
     * @param pulldownName EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    private static void initPullDownCreate(EZDCBigDecimalItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((BigDecimal) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));
//            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[1]), ":", (String) pullDownData.get(dbColumn[2])));
        }
    }

    /**
     * Create Pull Down List (Bank Account)
     * @param glblCmpyCd String
     * @param remDsBankAcctPk BigDecimal
     */
    public static void createPulldownListBankAccountByremDsBankAcctPk(String glblCmpyCd, BigDecimal remDsBankAcctPk, NFCL3020CMsg bizMsg) {
        bizMsg.dsBankCd_H.clear();
        bizMsg.bankRteNum_H.clear();
        bizMsg.remDsBankAcctPk_H.clear();

        DS_BANK_ACCTTMsg acctInMsg = new DS_BANK_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(acctInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(acctInMsg.dsBankAcctPk, remDsBankAcctPk);

        DS_BANK_ACCTTMsg acctOutMsg = (DS_BANK_ACCTTMsg) EZDTBLAccessor.findByKey(acctInMsg);
        if (acctOutMsg == null) {
            NFCL3020CommonLogic.createPulldownListBankBr(glblCmpyCd, bizMsg);
            NFCL3020CommonLogic.createPulldownListBankAccount(glblCmpyCd, bizMsg);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsBankCd_H, acctOutMsg.dsBankCd);
            NFCL3020CommonLogic.createPulldownListBankBr(glblCmpyCd, bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.bankRteNum_H, acctOutMsg.bankRteNum);
            NFCL3020CommonLogic.createPulldownListBankAccount(glblCmpyCd, bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.remDsBankAcctPk_H, acctOutMsg.dsBankAcctPk);
        }
    }

    /**
     * Create Pull Down List (AR Receipt Source)
     * @param glblCmpyCd String
     * @param bizMsg NFCL3020CMsg
     * @param arRcptManCrftFlg String
     */
    // Mod Start 2018/04/03 S21_NA#21737-1
    public static void createPulldownListArRcptSrc(String glblCmpyCd, NFCL3020CMsg bizMsg, String arRcptManCrftFlg) {
    // Mod End   2018/04/03 S21_NA#21737-1
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // Mod Start 2018/04/04 S21_NA#21737-1
        ssmParam.put("arRcptManCrftFlg", arRcptManCrftFlg);
        // Mod End   2018/04/04 S21_NA#21737-1
        S21SsmEZDResult arRcptSrc = NFCL3020Query.getInstance().getArRcptSrcPullDownList(bizMsg, ssmParam);
        if (arRcptSrc.isCodeNormal()) {
            List<Map> arRcptSrcList = (List<Map>) arRcptSrc.getResultObject();
            initPullDownCreate(bizMsg.arRcptSrcCd_LC, bizMsg.arRcptSrcNm_LD, arRcptSrcList, new String[] {"AR_RCPT_SRC_CD", "AR_RCPT_SRC_NM" });
        }
    }
    
    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    public static void clearScreen(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;

        bizMsg.arRcptSrcCd_H.clear();
        bizMsg.arBatRcptStsCd_H.clear();
        bizMsg.arBatRcptCnt_H.clear();
        bizMsg.arBatRcptAmt_H.clear();
        bizMsg.arBatRcptDt_H.clear();
        bizMsg.arLockBoxFileNm_H.clear();
        bizMsg.arLockBoxCd_H.clear();
        bizMsg.arLockBoxBatNum_H.clear();
        bizMsg.xxPageShowFromNum_B.clear();
        bizMsg.xxPageShowToNum_B.clear();
        bizMsg.xxPageShowOfNum_B.clear();

        bizMsg.xxTotAmt_H1.clear();
        bizMsg.xxTotAmt_H2.clear();
        bizMsg.xxTotAmt_H3.clear();
        bizMsg.xxTotAmt_H4.clear();
        bizMsg.xxTotAmt_H5.clear();
        bizMsg.xxTotAmt_H6.clear();
        bizMsg.xxTotAmt_H7.clear();
        bizMsg.xxTotAmt_H8.clear();
        bizMsg.xxTotAmt_H9.clear();
        bizMsg.xxTotCnt_H1.clear();
        bizMsg.xxTotCnt_H2.clear();
        bizMsg.xxTotCnt_H3.clear();
        bizMsg.xxTotCnt_H4.clear();
        bizMsg.xxTotCnt_H5.clear();
        bizMsg.xxTotCnt_H6.clear();
        bizMsg.xxTotCnt_H7.clear();
        bizMsg.xxTotCnt_H8.clear();
        bizMsg.xxTotCnt_H9.clear();

        bizMsg.rcptChkNum_BH.clear();
        bizMsg.arRcptTrxTpCd_BH.clear();
        bizMsg.rcptDt_BH.clear();
        bizMsg.funcRcptAmt_BH.clear();

        bizMsg.dsBankCd_H.clear();
        bizMsg.bankRteNum_H.clear();
        bizMsg.remDsBankAcctPk_H.clear();
        bizMsg.arBatRcptCmntTxt_H.clear();
        
        // QC#5521 ADD Start
        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();
        // QC#5521 ADD End

//        ZYPCodeDataUtil.createPulldownList(AR_LOCK_BOX.class, bizMsg.arLockBoxCd_LC, bizMsg.arLockBoxNm_LD);
//        NFCL3020CommonLogic.createPulldownListArRcptTrxTp(bizMsg.glblCmpyCd.getValue(), bizMsg);
//        NFCL3020CommonLogic.createPulldownListArRcptSrc(bizMsg.glblCmpyCd.getValue(), bizMsg);
//        NFCL3020CommonLogic.createPulldownListArBatRcptSts(bizMsg.glblCmpyCd.getValue(), bizMsg);

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(globalMsg.B);
    }

    //Def#3269
    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean checkReceiptDate(NFCL3020CMsg bizMsg) {
        
        String startDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 0, 0, -1);
        String endDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 0, 0, 1);
        
        if(startDt.compareTo(bizMsg.rcptDt_BH.getValue()) > 0 ) {
            bizMsg.rcptDt_BH.setErrorInfo(1, "NFCM0827E", new String[] {startDt,endDt});
            return false;
        }
        if(endDt.compareTo(bizMsg.rcptDt_BH.getValue()) < 0 ) {
            bizMsg.rcptDt_BH.setErrorInfo(1, "NFCM0827E", new String[] {startDt,endDt});
            return false;
        }
        return true;
    }

    // QC#5521 ADD Start
    /**
     * Create Save Option PullDown list
     * @param bizMsg NFCL3020CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createPulldownListSaveOpt(NFCL3020CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_LC.clear();
        bizMsg.srchOptNm_LD.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", BIZ_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_LC.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_LD.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }

    /**
     * Call NSZC0330 for Search Option
     * @param bizMsg NFCL3020CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callSrchOptApi(NFCL3020CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_H.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_H.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NFCL3020CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFCL3020CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_LD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_LD.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_H.getValue().equals(bizMsg.srchOptNm_LD.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H) && bizMsg.srchOptPk_H.getValue().compareTo(bizMsg.srchOptPk_LC.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NFCL3020CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NFCL3020CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_LD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_LD.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_H.getValue().compareTo(bizMsg.srchOptPk_LC.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_H.getValue().equals(bizMsg.srchOptNm_LD.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NFCL3020CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_LD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_LD.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_H.getValue().compareTo(bizMsg.srchOptPk_LC.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_LC.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_LD.no(i));
            }
        }
        return;
    }

    /**
     * First Page
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020SMsg
     */
    public static void firstPage(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.B;
        shareMsgAry = globalMsg.B;
        bizMsg.xxPageShowFromNum_B.setValue(1);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }
    // QC#5521 ADD End

    // START 2018/04/06 H.Ikeda [QC#25338, ADD]
    public static boolean callCrPrflUpdtAPI(NFCL3020CMsg bizMsg, AR_RCPTTMsg arRcptTMsg) {
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();
        
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("arTrxNum01", arRcptTMsg.rcptNum.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (1 > outMsg.length()) {
            return true;
        }
        BigDecimal arTrxBalPk = outMsg.no(0).arTrxBalPk.getValue();
        
        inMsg.clear();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.arTrxBalPk.setValue(arTrxBalPk);
        AR_TRX_BALTMsg arTrxBal = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (arTrxBal == null) {
            return true;
        }

        // START 2018/05/09 [QC#25856, MOD]
        if (ZYPCommonFunc.hasValue(arTrxBal.billToCustCd)) {
            ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
            ZYPEZDItemValueSetter.setValue(paramMsg.billToCustCd, arTrxBal.billToCustCd);
        } else {
            ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_CUST_ACCT);
            ZYPEZDItemValueSetter.setValue(paramMsg.sellToCustCd, arTrxBal.billToCustAcctCd);
        }
        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.procDt, ZYPDateUtil.getSalesDate());
        // END 2018/05/09 [QC#25856, MOD]

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.ONLINE);
        
        List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
        for (String msgId : msgList) {
            if (msgId.endsWith("E")) {
                bizMsg.setMessageInfo(msgId);
                return false;
            }
        }
        return true;
    }
    // END 2018/04/06 H.ikeda [QC#25338, ADD]

    // START 2018/05/09 [QC#25856, ADD]
    /**
     * get Customer Code/Name Location
     * @param bizMsg NFCL3020CMsg
     * @return boolean
     */
    public static boolean getCustomer(NFCL3020CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsAcctNum", bizMsg.payerCustCd_BH.getValue());
        ssmParam.put("locNum", bizMsg.locNum_BH.getValue());
        // 2019/09/02 QC#53097 Del Start
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2019/09/02 QC#53097 Del End

        S21SsmEZDResult ssmResult = NFCL3020Query.getInstance().getCustomer(bizMsg, ssmParam);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        if (resultList.size() == 0) {
            bizMsg.payerCustCd_BH.setErrorInfo(1, "NFCM0883E");
            bizMsg.dsAcctNm_BH.clear();
            bizMsg.locNum_BH.setErrorInfo(1, "NFCM0883E");
            return false;
        } else if (resultList.size() > 1) {
            if (!ZYPCommonFunc.hasValue(bizMsg.locNum_BH)) {
                Map<String, Object> resultMap = resultList.get(0);
                ZYPEZDItemValueSetter.setValue(bizMsg.payerCustCd_BH, (String) resultMap.get("DS_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_BH, (String) resultMap.get("DS_ACCT_NM"));
                return true;
            }
            bizMsg.payerCustCd_BH.setErrorInfo(1, "NFCM0857E", new String[] {"Customer information" });
            return false;
        } else {
            Map<String, Object> resultMap = resultList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.payerCustCd_BH, (String) resultMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_BH, (String) resultMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.locNum_BH, (String) resultMap.get("LOC_NUM"));
        }
        return true;
    }
    // END   2018/05/09 [QC#25856, ADD]

    // START 2018/06/04 Y.Matsui [QC#25368,ADD]
    /**
     * Get AR Account Date
     * @param bizMsg NFCL3020CMsg
     * @return String
     */
    public static String getArAcctDt(NFCL3020CMsg bizMsg) {

        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY, bizMsg.glblCmpyCd.getValue());
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.subSysCd.setValue(subSysCd);
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            bizMsg.setMessageInfo("NFZM0007E");
        }
        return outMsg.acctDt.getValue();
    }
    // END   2018/06/04 Y.Matsui [QC#25368,ADD]

    // START 2018/07/13 Y.Matsui [QC#26993, ADD]
    /**
     * ReCalc AR Batch Receipt Status
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020SMsg
     */
    public static void reCalcArBatRcptStatus(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {
        // START 2019/07/16 H.Ikeda [QC#51515, DEL]
//        if (globalMsg.B.getValidCount() == 0) {
//            bizMsg.arBatRcptStsCd_H.setValue(AR_BAT_RCPT_STS.OUT_OF_BALANCE);
//            return;
//        }
//        BigDecimal totCnt = new BigDecimal(globalMsg.B.getValidCount());
        // END   2019/07/16 H.Ikeda [QC#51515, DEL]
        BigDecimal totCnt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;
        boolean containsUnapplied = false;
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            // START 2019/07/16 H.Ikeda [QC#51515, MOD]
//            totAmt = totAmt.add(globalMsg.B.no(i).funcRcptAmt_B.getValue());
            if (isUnapplied(globalMsg.B.no(i).arCashApplyStsCd_B.getValue())) {
                containsUnapplied = true;
            }
            if (!AR_CASH_APPLY_STS.VOID.equals(globalMsg.B.no(i).arCashApplyStsCd_B.getValue())) {
                totAmt = totAmt.add(globalMsg.B.no(i).funcRcptAmt_B.getValue());
                totCnt = totCnt.add(BigDecimal.ONE);
            }
            // END   2019/07/16 H.Ikeda [QC#51515, MOD]
        }
        if ((totCnt.compareTo(bizMsg.arBatRcptCnt_H.getValue()) != 0) || (totAmt.compareTo(bizMsg.arBatRcptAmt_H.getValue()) != 0)) {
            bizMsg.arBatRcptStsCd_H.setValue(AR_BAT_RCPT_STS.OUT_OF_BALANCE);
            return;
        }

        if (containsUnapplied) {
            bizMsg.arBatRcptStsCd_H.setValue(AR_BAT_RCPT_STS.OPEN);
            return;
        }

        bizMsg.arBatRcptStsCd_H.setValue(AR_BAT_RCPT_STS.CLOSED);
    }

    private static boolean isUnapplied(String arCashApplyStsCd) {
        if (!ZYPCommonFunc.hasValue(arCashApplyStsCd)) {
            return true;
        }
        if (AR_CASH_APPLY_STS.UNIDENTIFIED.equals(arCashApplyStsCd)) {
            return true;
        }
        if (AR_CASH_APPLY_STS.UNAPPLIED.equals(arCashApplyStsCd)) {
            return true;
        }
        if (AR_CASH_APPLY_STS.PARTIAL.equals(arCashApplyStsCd)) {
            return true;
        }
        return false;
    }
    // END   2018/07/13 Y.Matsui [QC#26993, ADD]
}
