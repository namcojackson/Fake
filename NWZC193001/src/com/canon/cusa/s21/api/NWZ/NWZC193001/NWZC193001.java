/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC193001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPOTMsg;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Contract Overdue Hold API 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/06   Fujitsu         Y.Matsui        Create          QC#27079
 *</pre>
 */
public class NWZC193001 extends S21ApiCommonBase {

    /** Data Company Code is not entered. */
    public static final String NWZM0001E = "NWZM0001E";

    /** Order Number is not entered. */
    public static final String NWZM0002E = "NWZM0002E";

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** Sales Date is not entered. */
    public static final String NWZM0445E = "NWZM0445E";

    /** CR_CHK_LVL_ACCT */
    private static final String CR_CHK_LVL_ACCT = "CR_CHK_LVL_ACCT";

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /**
     * Constructor
     */
    public NWZC193001() {
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

        if (existOverDueHold(inMsg.glblCmpyCd.getValue(), inMsg.cpoOrdNum_I.getValue())) {
            return;
        }

        List<String> dsContrNumList = getContractNumberList(inMsg);
        if (dsContrNumList.isEmpty()) {
            return;
        }

        String acctLvlFlg = ZYPCodeDataUtil.getVarCharConstValue(CR_CHK_LVL_ACCT, inMsg.glblCmpyCd.getValue());

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", inMsg.slsDt.getValue());
        ssmParam.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());
        ssmParam.put("slsDt", inMsg.slsDt.getValue());
        ssmParam.put("arTrxTpInvoice", AR_TRX_TP.INVOICE);
        ssmParam.put("arCashApplyStsUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("arCashApplyStsPartial", AR_CASH_APPLY_STS.PARTIAL);
        ssmParam.put("dsContrNumList", dsContrNumList);
        ssmParam.put("billToCustAcctCd", cpoTMsg.billToCustAcctCd.getValue());
        ssmParam.put("billToCustCd", cpoTMsg.billToCustCd.getValue());
        ssmParam.put("acctLvlFlg", acctLvlFlg);

        BigDecimal count = (BigDecimal) ssmClient.queryObject("countOnverdueContractInvoice", ssmParam);

        if (count.compareTo(BigDecimal.ZERO) != 0) {
            setOutParam(inMsg);
        }
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

    private List<String> getContractNumberList(NWXC005001PMsg inMsg) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());

        return (List<String>) ssmClient.queryObjectList("getContract", ssmParam);
    }

    private void setOutParam(NWXC005001PMsg inMsg) {
        inMsg.cpoOrdNum_O.setValue(inMsg.cpoOrdNum_I.getValue());
        inMsg.hldRsnCd.setValue(HLD_RSN.OVER_DUE);
    }
}
