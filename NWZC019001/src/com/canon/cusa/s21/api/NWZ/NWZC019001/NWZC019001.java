/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC019001;

import java.util.List;

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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * When asserted over due of each customer (Set it with Credit Risk Class) is exceeded, Hold is done. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/11/19   Fujitsu         S.Yamamoto      Create          540(All2)
 * 2015/09/10   Fujitsu         T.Yoshida       Update          For CSA
 * 2015/12/14   Fujitsu         T.ishii         Update          S21_NA#1880
 * 2016/08/10   SRAA            K.Aratani       Update          S21_NA#13323
 * 2016/10/06   Fujitsu         T.Yoshida       Update          S21_NA#14973
 * 2017/05/17   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7319
 * 2017/12/28   Fujitsu         M.Ohno          Update          S21_NA#22737
 *</pre>
 */
public class NWZC019001 extends S21ApiCommonBase {

    /** Data Company Code is not entered. */
    public static final String NWZM0001E = "NWZM0001E";

    /** Order Number is not entered. */
    public static final String NWZM0002E = "NWZM0002E";

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** Sales Date is not entered. */
    public static final String NWZM0445E = "NWZM0445E";

    /** Parent Number */
    public static final String SET_ITEM_PARENT_NUM = "000";

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    // 2017/12/27 QC#2273 add start
    /** CR_CHK_LVL_ACCT */
    private String CR_CHK_LVL_ACCT = "CR_CHK_LVL_ACCT";
    // 2017/12/27 QC#2273 add end


    /**
     * Constructor
     */
    public NWZC019001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * <pre>
     * Refer to the class comment.
     * </pre>
     * @param param Interface Msg of Over Due Hold API
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
        if (cpoTMsg == null) {
            msgMap.addXxMsgId(NWZM0073E);
            return;
        }

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();
        String glblCmpyCd = inMsg.glblCmpyCd.getValue();
        // 2017/12/27 QC#22737 add start
        String acctLvlFlg =  ZYPCodeDataUtil.getVarCharConstValue(CR_CHK_LVL_ACCT, glblCmpyCd);
        // 2017/12/27 QC#22737 add end

        if (existOverDueHold(glblCmpyCd, inMsg.cpoOrdNum_I.getValue())) {
            return;
        }

        // 2017/12/28 QC#22737 mod start
        String payerCustCd = cpoTMsg.payerCustCd.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(acctLvlFlg)) {
            // Account Level
            String accountOverDueFlg = getAccountOverDueFlg(glblCmpyCd, cpoTMsg.billToCustAcctCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(accountOverDueFlg)) {
                setOutParam(inMsg);
            }
        } else {
            // Location Level
            String locationOverDueFlg = getCrChkOverDueFlg(glblCmpyCd, payerCustCd);
            if (!ZYPCommonFunc.hasValue(locationOverDueFlg)) {
                // Account Level
                locationOverDueFlg = getAccountOverDueFlg(glblCmpyCd, cpoTMsg.billToCustAcctCd.getValue());
            }

            if (ZYPConstant.FLG_ON_Y.equals(locationOverDueFlg)) {
                setOutParam(inMsg);
            }
        }
        // 2017/12/28 QC#22737 mod end

    }

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

        if (!ZYPCommonFunc.hasValue(inMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0445E);
            return false;
        }

        return true;
    }

    private static boolean existOverDueHold(String glblCmpyCd, String cpoOrdNum) {

        final HLDTMsg hldTMsg = new HLDTMsg();

        hldTMsg.setSQLID("019");
        hldTMsg.setMaxCount(1);
        hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        hldTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        hldTMsg.setConditionValue("hldRsnCd01", HLD_RSN.OVER_DUE);

        return S21ApiTBLAccessor.count(hldTMsg) != 0;
    }

    private static String getCrChkOverDueFlg(String glblCmpyCd, String billToCustCd) {

        final CUST_CR_PRFLTMsgArray custCrPrflTMsgArray = NWXCustCrPrflTMsgThreadLocalCache.getInstance().get(glblCmpyCd, billToCustCd);
        // 2017/12/27 QC#22737 mod end
//        if (custCrPrflTMsgArray.getValidCount() > 0) {
        if (custCrPrflTMsgArray.getValidCount() > 0 && ZYPCommonFunc.hasValue(custCrPrflTMsgArray.no(0).crLimitAmt)) {
            return custCrPrflTMsgArray.no(0).ovdWsFlg.getValue();
        }
        // 2017/12/27 QC#22737 mod end

        return null;
    }

    /**
     * get Account OVD_WS_FLG.
     * @param glblCmpyCd Global Company Code
     * @param billToCustAcctCd Bill To Customer Account Code
     * @return Account OVD_WS_FLG
     */
    private String getAccountOverDueFlg(String glblCmpyCd, String billToCustAcctCd) {

        DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, billToCustAcctCd);
        tMsg = (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            return tMsg.ovdWsFlg.getValue();
        }

        return null;
    }

    /**
     * Set Output Parameter
     * @param inMsg NWXC005001PMsg
     */
    private void setOutParam(NWXC005001PMsg inMsg) {

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
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.hldRsnCd, HLD_RSN.OVER_DUE);
//        }
        inMsg.cpoOrdNum_O.setValue(inMsg.cpoOrdNum_I.getValue());
        inMsg.hldRsnCd.setValue(HLD_RSN.OVER_DUE);
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
}
