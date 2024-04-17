/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC011001;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPOTMsg;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXCustCrPrflTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Payer of the order checks whether it is Payer that is sure to need the approval of
 * CreditDepartment regardless of the amount of money of credit facility.
 * Hold information is returned for Payer for which approval is necessary.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/16/2009   Fujitsu         S.Sugino        Create          N/A
 * 10/27/2009   Fujitsu         S.Sugino        Update          N/A
 * 11/19/2009   Fujitsu         T.Kaneda        Update          N/A
 * 12/08/2009   Fujitsu         S.Sugino        Update          N/A Cache of CUST_CR_PRFL.
 * 02/08/2010   Fujitsu         T.Nagase        Update          N/A (Dealer profile)
 * 09/20/2010   Fujitsu         S.Yamamoto      Update          25(All2)
 * 11/30/2010   Fujitsu         K.Tajima        Update          Def# 645 [Performance] NWAL0010 Order Entry (case of Auto Allocation)
 * 12/02/2010   Fujitsu         S.Yamamoto      Update          540(All2)
 * 2015/09/10   Fujitsu         T.Yoshida       Update          For CSA
 * 2015/12/14   Fujitsu         T.ishii         Update          S21_NA#1880
 * 2016/04/06   Fujitsu         T.ishii         Update          S21_NA#6329
 * 2016/08/10   SRAA            K.Aratani       Update          S21_NA#13323
 * 2016/10/06   Fujitsu         T.Yoshida       Update          S21_NA#14973
 * 2017/05/17   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7319
 * 2017/12/27   Fujitsu         M.Ohno          Update          S21_NA#22737
 * 2018/01/18   Fujitsu         M.Ohno          Update          S21_NA#22737-2
 *</pre>
 */
public class NWZC011001 extends S21ApiCommonBase {

    /** Data Company Code is not entered. */
    public static final String NWZM0001E = "NWZM0001E";

    /** Order Number is not entered. */
    public static final String NWZM0002E = "NWZM0002E";

    // S21_NA#14973 Del Start
    // /** Order Line Number is not entered. */
    // private static final String NWZM0003E = "NWZM0003E";
    //
    // /** Order Line Sub Number is not entered. */
    // private static final String NWZM0004E = "NWZM0004E";
    // // S21_NA#14973 Del End
    //
    // /** "Shipping Plan Number" is not entered. */
    // public static final String NWZM0250E = "NWZM0250E";
    // S21_NA#14973 Del End

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** The data does not exist in CUST_CR_PRFL. */
    public static final String NWZM0241E = "NWZM0241E";

    /** Sales Date is not entered. */
    public static final String NWZM0445E = "NWZM0445E";

    /** The data does not exist in DS CPO Configuration. */
    public static final String NWZM1399E = "NWZM1399E";

    /** The data does not exist in DS CPO. */
    private static final String NWZM1491E = "NWZM1491E";

    /** Parent Number */
    private static final String SET_ITEM_PARENT_NUM = "000";

    /** Local Cache */
    private final LocalCache localCache = new LocalCache();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    // 2017/12/27 QC#22273 add start
    /** CR_CHK_LVL_ACCT */
    private String CR_CHK_LVL_ACCT = "CR_CHK_LVL_ACCT";
    // 2017/12/27 QC#22273 add end

    // 2018/01/18 QC#22273-2 add start
    /** Credit Profile type */
    private String HARD_HOLD = "01";

    /** Credit Profile type */
    private String ACCT_STATUS = "02";

    /** Credit Profile type */
    private String CHK_REQ = "03";
    // 2018/01/18 QC#22273-2 add end
    

    /**
     * Constructor
     */
    public NWZC011001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * <pre>
     * Refer to the class comment.
     * </pre>
     * @param param Interface Msg of Credit Profile API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param.getInputPMsg());

        doProcess(param, msgMap);

        msgMap.flush();
    }

    /**
     * <pre>
     * Refer to the class comment.
     * One Msg in List is taken out, and the execute(NWXC005001ValidationBean, ONBATCH_TYPE) is executed.
     * </pre>
     * @see #execute(NWXC005001ValidationBean,
     * com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE)
     * @param params Interface Msg list of Credit Profile API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean param : params) {
            execute(param, onBatchType);
        }
    }

    private void doProcess(NWXC005001ValidationBean param, S21ApiMessageMap msgMap) {

        if (!checkInput(msgMap)) {
            return;
        }

        CPOTMsg cpoTMsg = param.getCTMsg();
        // SHPG_PLNTMsg shpgPlnTMsg = param.getSpTMsg(); //
        // S21_NA#14973 Del
        if (cpoTMsg == null) { // S21_NA#14973 Mod
            msgMap.addXxMsgId(NWZM0073E);
            return;
        }

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();
        String glblCmpyCd = inMsg.glblCmpyCd.getValue();
        // 2017/12/27 QC#22737 add start
        String acctLvlFlg =  ZYPCodeDataUtil.getVarCharConstValue(CR_CHK_LVL_ACCT, glblCmpyCd);
        // 2017/12/27 QC#22737 add end
        // String shpgPlnNum = inMsg.shpgPlnNum_I.getValue(); //
        // S21_NA#14973 Del

        // S21_NA#14973 Del Start
        // if
        // (SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue()))
        // {
        // return;
        // }
        // S21_NA#14973 Del End

        // S21_NA#14973 Del Start
        // if (existExemption(inMsg, dsCpoTMsg)) {
        // return;
        // }
        // S21_NA#14973 Del End

        if (existCreditProfileHold(glblCmpyCd, inMsg.cpoOrdNum_I.getValue())) {
            return;
        }

        // S21_NA#13323 Del Start
        // if (isAllLineCredit(inMsg)) {
        // return;
        // }
        // S21_NA#13323 Del End

        // 2017/12/27 QC#22737 mod start

        String payerCustCd = cpoTMsg.payerCustCd.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(acctLvlFlg)) {
            // Account Level Check
            // 2018/01/18 QC#22737-2 add start
            DS_ACCT_CR_PRFLTMsg dsAcctCrPrfl = new DS_ACCT_CR_PRFLTMsg();
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.dsAcctNum, cpoTMsg.billToCustAcctCd);
            dsAcctCrPrfl = (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(dsAcctCrPrfl);
            if (dsAcctCrPrfl != null) {

                if (S21StringUtil.isEquals(dsAcctCrPrfl.custHardHldFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

                    setOutParam(inMsg, payerCustCd, HARD_HOLD);
                }
            }
            // 2018/01/18 QC#22737-2 add end

            String dsCrChkReqTpCd = getDsCrChkReqTpCd(glblCmpyCd, cpoTMsg);

            if (!ZYPCommonFunc.hasValue(dsCrChkReqTpCd)) {
                // Mod
                msgMap.addXxMsgId(NWZM0241E);
                return;
            }

            if (CR_CHK_REQ_TP.HOLD.equals(dsCrChkReqTpCd)) {
                // S21_NA#14973 Mod Start
                // setHldRsn(glblCmpyCd, payerCustCd, inMsg, cpoTMsg,
                // shpgPlnTMsg);
                setOutParam(inMsg, payerCustCd, CHK_REQ);
                // S21_NA#14973 Mod End
                // 2018/01/18 QC#22273-2 del start
                //return;
                // 2018/01/18 QC#22273-2 del end
            }

            String accountCrHldFlg = getAccountCrHldFlg(glblCmpyCd, cpoTMsg);
            if (ZYPConstant.FLG_ON_Y.equals(accountCrHldFlg)) {
                // S21_NA#14973 Mod Start
                // setHldRsn(glblCmpyCd, payerCustCd, inMsg, cpoTMsg,
                // shpgPlnTMsg);
                setOutParam(inMsg, payerCustCd, ACCT_STATUS);
                // S21_NA#14973 Mod End
            }

        } else {

            // Location Level Check
            String crChkReqTpCd = getCrChkReqTpCd(glblCmpyCd, payerCustCd);

             if (!ZYPCommonFunc.hasValue(crChkReqTpCd)) {
                 // Account Level
                crChkReqTpCd = getDsCrChkReqTpCd(glblCmpyCd, cpoTMsg);
            }

            if (CR_CHK_REQ_TP.HOLD.equals(crChkReqTpCd)) {
                // S21_NA#14973 Mod Start
                // setHldRsn(glblCmpyCd, payerCustCd, inMsg, cpoTMsg,
                // shpgPlnTMsg);
                setOutParam(inMsg, payerCustCd, CHK_REQ);
                // S21_NA#14973 Mod End
                // 2018/01/18 QC#22273-2 del start
                //return;
                // 2018/01/18 QC#22273-2 del end
            }

            String locationCrHldFlg = getLocationCrHldFlg(glblCmpyCd, cpoTMsg);
            if (ZYPConstant.FLG_ON_Y.equals(locationCrHldFlg)) {
                // S21_NA#14973 Mod Start
                // setHldRsn(glblCmpyCd, payerCustCd, inMsg, cpoTMsg,
                // shpgPlnTMsg);
                setOutParam(inMsg, payerCustCd, ACCT_STATUS);
                // S21_NA#14973 Mod End
                // 2018/01/18 QC#22273-2 del start
                //return;
                // 2018/01/18 QC#22273-2 del end
            }
        }
        // 2017/12/27 QC#22737 mod end

    }

//    private boolean existCustExToOrdRst(String glblCmpyCd, String payerCustCd) {
//
//        // cache
//        final StringBuilder cacheKeySb = new StringBuilder();
//        cacheKeySb.append(glblCmpyCd).append(",");
//        cacheKeySb.append(payerCustCd);
//
//        final String cacheKey = cacheKeySb.toString();
//
//        Boolean contractInfo = this.localCache.contractInfoCache.get(cacheKey);
//        if (contractInfo == null) {
//            contractInfo = chkContractInfo(glblCmpyCd, null, null, null, null, HLD_RSN.CREDIT_PROFILE, payerCustCd);
//            this.localCache.contractInfoCache.put(cacheKey, contractInfo);
//        }
//
//        return contractInfo;
//    }

    private static boolean checkInput(S21ApiMessageMap msgMap) {

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(inMsg.cpoOrdNum_I)) {
            msgMap.addXxMsgId(NWZM0002E);
            return false;
        }

        // S21_NA#14973 Del Start
        // if (!ZYPCommonFunc.hasValue(inMsg.cpoDtlLineNum_I)) {
        // msgMap.addXxMsgId(NWZM0003E);
        // return false;
        // }
        //
        // if (!ZYPCommonFunc.hasValue(inMsg.cpoDtlLineSubNum_I)) {
        // msgMap.addXxMsgId(NWZM0004E);
        // return false;
        // }
        //
        // if (!ZYPCommonFunc.hasValue(inMsg.shpgPlnNum_I)) {
        // msgMap.addXxMsgId(NWZM0250E);
        // return false;
        // }
        // S21_NA#14973 Del End

        if (!ZYPCommonFunc.hasValue(inMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0445E);
            return false;
        }

        return true;
    }

    private static boolean existCreditProfileHold(String glblCmpyCd, String cpoOrdNum) {

        final HLDTMsg condition = new HLDTMsg();

        // S21_NA#14973 Mod Start
        // condition.setSQLID("020");
        condition.setSQLID("019");
        // S21_NA#14973 Mod End

        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);

        // S21_NA#14973 Mod Start
        // condition.setConditionValue("shpgPlnNum01",
        // inMsg.shpgPlnNum_I.getValue());
        condition.setConditionValue("cpoOrdNum01", cpoOrdNum);
        // S21_NA#14973 Mod End

        condition.setConditionValue("hldRsnCd01", HLD_RSN.CREDIT_PROFILE);

        return S21ApiTBLAccessor.count(condition) != 0;
    }

    private static String getCrChkReqTpCd(String glblCmpyCd, String billToCustCd) {

        final CUST_CR_PRFLTMsgArray custCrPrflTMsgArray = NWXCustCrPrflTMsgThreadLocalCache.getInstance().get(glblCmpyCd, billToCustCd);
        // 2017/12/27 QC#22737 mod end
//        if (custCrPrflTMsgArray.length() > 0) {
        if (custCrPrflTMsgArray.length() > 0 && ZYPCommonFunc.hasValue(custCrPrflTMsgArray.no(0).crLimitAmt)) {
            return custCrPrflTMsgArray.no(0).crChkReqTpCd.getValue();
        }
       // 2017/12/27 QC#22737 mod end
       
        return null;
    }

    // S21_NA#14973 Del Start
    // /**
    // * Check exist exemption.
    // * @param inMsg NWXC005001PMsg
    // * @param dsCpoTMsg DS_CPOTMsg
    // * @return If customer is exemption, return true.
    // */
    // private boolean existExemption(NWXC005001PMsg inMsg, DS_CPOTMsg
    // dsCpoTMsg) {
    //
    // final CUST_CR_CHK_EXEMTMsg condition = new
    // CUST_CR_CHK_EXEMTMsg();
    // condition.setSQLID("001");
    // condition.setMaxCount(1);
    // condition.setConditionValue("glblCmpyCd01",
    // inMsg.glblCmpyCd.getValue());
    // condition.setConditionValue("dsAcctNum01",
    // dsCpoTMsg.billToCustAcctCd.getValue());
    // condition.setConditionValue("effFromDt01",
    // inMsg.slsDt.getValue());
    // condition.setConditionValue("effToDt01",
    // inMsg.slsDt.getValue());
    //
    // return S21ApiTBLAccessor.count(condition) > 0;
    // }
    // S21_NA#14973 Del End

    // S21_NA#13323 Del Start
    // /**
    // * Check credit of all line.
    // * @param inMsg NWXC005001PMsg
    // * @return If all line is credit, return true.
    // */
    // @SuppressWarnings("unchecked")
    // private boolean isAllLineCredit(NWXC005001PMsg inMsg) {
    //
    // Map<String, String> ssmParam = new HashMap<String, String>();
    // ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
    // ssmParam.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());
    // ssmParam.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
    // List<Map<String, String>> getDsCpoDtlList =
    // ssmClient.queryObjectList("getDsCpoDtlList",
    // ssmParam);
    //
    // if (getDsCpoDtlList == null || getDsCpoDtlList.size() == 0) {
    // return false;
    // }
    //
    // boolean allLineCreditFlg = true;
    // for (Map<String, String> dsCpoDtl : getDsCpoDtlList) {
    // if (!CR_REBIL.CREDIT.equals(dsCpoDtl.get("CR_REBIL_CD"))) {
    // allLineCreditFlg = false;
    // break;
    // }
    // }
    //
    // if (allLineCreditFlg) {
    // return true;
    // }
    //
    // return false;
    // }
    // S21_NA#13323 Del End

    /**
     * get CR_CHK_REQ_TP_CD.
     * @param glblCmpyCd Global Company Code
     * @param cpoTMsg CPOTMsg
     * @return CR_CHK_REQ_TP_CD
     */
    private String getDsCrChkReqTpCd(String glblCmpyCd, CPOTMsg cpoTMsg) {

        DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, cpoTMsg.billToCustAcctCd.getValue());
        tMsg = (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            return tMsg.crChkReqTpCd.getValue();
        }

        return null;
    }

    /**
     * get location CR_HLD_FLG.
     * @param glblCmpyCd Global Company Code
     * @param cpoTMsg CPOTMsg
     * @return Location CR_HLD_FLG
     */
    @SuppressWarnings("unchecked")
    private String getLocationCrHldFlg(String glblCmpyCd, CPOTMsg cpoTMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", cpoTMsg.billToCustCd.getValue());
        List<Map<String, String>> getLocationCrHldFlgList = ssmClient.queryObjectList("getLocationCrHldFlg", ssmParam);

        if (getLocationCrHldFlgList == null || getLocationCrHldFlgList.size() == 0) {
            return ZYPConstant.FLG_OFF_N;
        }

        return getLocationCrHldFlgList.get(0).get("CR_HLD_FLG");
    }

    /**
     * get Account CR_HLD_FLG.
     * @param glblCmpyCd Global Company Code
     * @param cpoTMsg CPOTMsg
     * @return Account CR_HLD_FLG
     */
    @SuppressWarnings("unchecked")
    private String getAccountCrHldFlg(String glblCmpyCd, CPOTMsg cpoTMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustAcctCd", cpoTMsg.billToCustAcctCd.getValue());
        List<Map<String, String>> getAccountCrHldFlgList = ssmClient.queryObjectList("getAccountCrHldFlg", ssmParam);

        if (getAccountCrHldFlgList == null || getAccountCrHldFlgList.size() == 0) {
            return ZYPConstant.FLG_OFF_N;
        }

        return getAccountCrHldFlgList.get(0).get("CR_HLD_FLG");
    }

    // S21_NA#14973 Del Start
    // /**
    // * Set hold reason.
    // * @param glblCmpyCd Global Company Code
    // * @param payerCustCd payer Customer Code
    // * @param inMsg NWXC005001PMsg
    // * @param cpoTMsg CPOTMsg
    // * @param shpgPlnTMsg SHPG_PLNTMsg
    // */
    // private void setHldRsn(String glblCmpyCd, String payerCustCd,
    // NWXC005001PMsg inMsg, CPOTMsg
    // cpoTMsg, SHPG_PLNTMsg shpgPlnTMsg) {
    //
    // final HLD_RSNTMsg hldRsnTMsg =
    // NWXHldRsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd,
    // HLD_RSN.CREDIT_PROFILE);
    // if
    // (ZYPConstant.FLG_ON_Y.equals(hldRsnTMsg.exReqFlg.getValue())) {
    // // Hold is executed when there is no Hold data of Credit
    // // Profile in the exception table.
    // if (existCustExToOrdRst(glblCmpyCd, payerCustCd)) {
    // return;
    // }
    // }
    //
    // ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum_O,
    // cpoTMsg.cpoOrdNum);
    // ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum_O,
    // shpgPlnTMsg.trxLineNum);
    // ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum_O,
    // shpgPlnTMsg.trxLineSubNum);
    // ZYPEZDItemValueSetter.setValue(inMsg.shpgPlnNum_O,
    // shpgPlnTMsg.shpgPlnNum);
    // ZYPEZDItemValueSetter.setValue(inMsg.hldRsnCd,
    // HLD_RSN.CREDIT_PROFILE);
    // }
    // S21_NA#14973 Del End

    /**
     * Local Data Cache.
     * @author K.Tajima
     */
    private static final class LocalCache {

        /** S21 LRU Map */
        final S21LRUMap<String, Boolean> contractInfoCache = new S21LRUMap<String, Boolean>();
    }

    // S21_NA#14973 Add Start
    /**
     * Set Output Parameter
     * @param inMsg NWXC005001PMsg
     */
    private void setOutParam(NWXC005001PMsg inMsg, String payerCustCd, String creditProfileType) {

//        final HLD_RSNTMsg hldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(inMsg.glblCmpyCd.getValue(), HLD_RSN.CREDIT_PROFILE);
//        if (ZYPConstant.FLG_ON_Y.equals(hldRsnTMsg.exReqFlg.getValue())) {
//            // Hold is executed when there is no Hold data of Credit
//            // Profile in the exception table.
//            if (existCustExToOrdRst(inMsg.glblCmpyCd.getValue(), payerCustCd)) {
//                return;
//            }
//        }

        // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Mod Start
//        List<Map<String, Object>> shpgPlnList = getShpgPlnList(inMsg);
//        if (shpgPlnList == null || shpgPlnList.size() == 0) {
//            return;
//        }
//
//        int index = 0;
//        for (; index < shpgPlnList.size(); index++) {
//            NWXC005001_shpgPlnListPMsg shpgPlnListPMsg = inMsg.shpgPlnList.no(index);
//            Map<String, Object> shpgPlnInfo = shpgPlnList.get(index);
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoOrdNum, (String) shpgPlnInfo.get("TRX_HDR_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoDtlLineNum, (String) shpgPlnInfo.get("TRX_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoDtlLineSubNum, (String) shpgPlnInfo.get("TRX_LINE_SUB_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.shpgPlnNum, (String) shpgPlnInfo.get("SHPG_PLN_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.ordQty, (BigDecimal) shpgPlnInfo.get("ORD_QTY"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.hldRsnCd, HLD_RSN.CREDIT_PROFILE);
//        }
//        inMsg.shpgPlnList.setValidCount(index);
        inMsg.cpoOrdNum_O.setValue(inMsg.cpoOrdNum_I.getValue());
        inMsg.hldRsnCd.setValue(HLD_RSN.CREDIT_PROFILE);
        // 2018/01/18 QC#22273-2 add start
        inMsg.modeCd.no(inMsg.modeCd.getValidCount()).xxModeCd.setValue(creditProfileType);
        inMsg.modeCd.setValidCount(inMsg.modeCd.getValidCount() + 1);
        // 2018/01/18 QC#22273-2 add end
        // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Mod End
    }

    // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Mod Start
//    /**
//     * Get Shipping Plan List
//     * @param inMsg NWXC005001PMsg
//     * @return Shipping Plan List
//     */
//    @SuppressWarnings("unchecked")
//    private List<Map<String, Object>> getShpgPlnList(NWXC005001PMsg inMsg) {
//
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());
//        ssmParam.put("setParentLineSubNum", SET_ITEM_PARENT_NUM);
//        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
//
//        return (List<Map<String, Object>>) ssmClient.queryObjectList("getShpgPlnList", ssmParam);
//    }
    // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Mod End
    // S21_NA#14973 Add End
}
