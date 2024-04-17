/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFZ.NFZC310001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.AR_BAT_RCPTTMsg;
import business.parts.NFZC310001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Contract Overdue Hold API 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/13   Fujitsu         Y.Matsui        Create          QC#26993
 * 2019/07/16   Fujitsu         S.Ohki          Update          QC#51515
 *</pre>
 */
public class NFZC310001 extends S21ApiCommonBase {

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Global Company Code is a mandatory field.. */
    public static final String NFZM0001E = "NFZM0001E";

    /** Failed to update [@]. */
    public static final String NFCM0615E = "NFCM0615E";

    /** The data does not exist in [@]. [@] */
    public static final String NFCM0845E = "NFCM0845E";

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /**
     * Constructor
     */
    public NFZC310001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * execute
     * @param param NFZC310001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NFZC310001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap);

        msgMap.flush();
    }

    /**
     * execute
     * @param params List of NFZC310001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NFZC310001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NFZC310001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap) {

        if (!checkInput(msgMap)) {
            return;
        }

        if (!updateArBatRcptSts(msgMap)) {
            return;
        }

    }

    private static boolean checkInput(S21ApiMessageMap msgMap) {
        NFZC310001PMsg pMsg = (NFZC310001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NFZM0001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.arBatRcptNm)) {
            msgMap.addXxMsgIdWithPrm(ZZZM9025E, new String[] {"Batch Receipt Name" });
            return false;
        }

        return true;
    }

    private boolean updateArBatRcptSts(S21ApiMessageMap msgMap) {
        NFZC310001PMsg pMsg = (NFZC310001PMsg) msgMap.getPmsg();

        Map<String, Object> arBatRcptInfo = getArBatRcptInfo(msgMap);
        if (arBatRcptInfo == null) {
            return false;
        }

        String arBatRcptStsCd = getArBatRcptStsCd(arBatRcptInfo);

        // No need to update if Batch Receipt Status is not changed.
        if (arBatRcptStsCd.equals(arBatRcptInfo.get("AR_BAT_RCPT_STS_CD"))) {
            return true;
        }

        AR_BAT_RCPTTMsg criteria = new AR_BAT_RCPTTMsg();
        criteria.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        criteria.arBatRcptNm.setValue(pMsg.arBatRcptNm.getValue());
        AR_BAT_RCPTTMsg arBatRcpt = (AR_BAT_RCPTTMsg) S21FastTBLAccessor.findByKeyForUpdate(criteria);
        if (arBatRcpt == null) {
            msgMap.addXxMsgIdWithPrm(NFCM0845E, new String[] {"Batch Receipt Name", pMsg.arBatRcptNm.getValue() });
            return false;
        }

        arBatRcpt.arBatRcptStsCd.setValue(arBatRcptStsCd);
        S21FastTBLAccessor.update(arBatRcpt);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arBatRcpt.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NFCM0615E, new String[] {arBatRcpt.getTableName() });

            return false;
        }

        return true;

    }

    private Map<String, Object> getArBatRcptInfo(S21ApiMessageMap msgMap) {
        NFZC310001PMsg pMsg = (NFZC310001PMsg) msgMap.getPmsg();

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arBatRcptNm", pMsg.arBatRcptNm.getValue());
        ssmParam.put("arCashApplyStsUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("arCashApplyStsUnidentified", AR_CASH_APPLY_STS.UNIDENTIFIED);
        ssmParam.put("arCashApplyStsPartial", AR_CASH_APPLY_STS.PARTIAL);
        // START 2019/07/16 [QC#51515, ADD]
        ssmParam.put("arCashApplyStsVoid", AR_CASH_APPLY_STS.VOID);
        // END 2019/07/16 [QC#51515, ADD]

        return (Map<String, Object>) ssmClient.queryObject("getArBatRcptInfo", ssmParam);
    }

    private String getArBatRcptStsCd(Map<String, Object> arBatRcptInfo) {
        BigDecimal arBatRcptAmt = getAsBigDecimal(arBatRcptInfo, "AR_BAT_RCPT_AMT");
        BigDecimal totRcptAmt = getAsBigDecimal(arBatRcptInfo, "TOT_DEAL_NET_RCPT_AMT");
        int rcptCnt = getAsInt(arBatRcptInfo, "RCPT_CNT");
        int unApplyRcptCnt = getAsInt(arBatRcptInfo, "UN_APPPLY_RCPT_CNT");

        // START 2019/07/16 [QC#51515, MOD]
//        if (rcptCnt == 0 || totRcptAmt.compareTo(arBatRcptAmt) != 0) {
        int batRcptCnt = getAsInt(arBatRcptInfo, "AR_BAT_RCPT_CNT");
        if (rcptCnt != batRcptCnt || totRcptAmt.compareTo(arBatRcptAmt) != 0) {
        // END 2019/07/16 [QC#51515, ADD]
            return AR_BAT_RCPT_STS.OUT_OF_BALANCE;
        }

        if (unApplyRcptCnt > 0) {
            return AR_BAT_RCPT_STS.OPEN;
        }

        return AR_BAT_RCPT_STS.CLOSED;
    }

    private BigDecimal getAsBigDecimal(Map<String, Object> map, String key) {
        return (BigDecimal) map.get(key);
    }

    private int getAsInt(Map<String, Object> map, String key) {
        return ((BigDecimal) map.get(key)).intValue();
    }

}
