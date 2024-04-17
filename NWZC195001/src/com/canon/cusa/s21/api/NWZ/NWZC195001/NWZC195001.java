/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC195001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;

/**
 *<pre>
 * NWZC1950 Manual Price Hold API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/25   Hitachi         A.Kohinata      Create          QC#61337
 *</pre>
 */
public class NWZC195001 extends S21ApiCommonBase {

    /** Order Number is not entered. */
    private static final String NWZM0002E = "NWZM0002E";

    /** "Global Company Code" is not set. */
    private static final String NWZM0473E = "NWZM0473E";

    /**
     * Message ID : A system error occurred. Please contact your
     * system administrator.
     */
    private static final String NWZM0474E = "NWZM0474E";

    /** S21Logger */
    private S21Logger logger = S21LoggerFactory.getLogger(NWZC195001.class);

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /**
     * Constructor
     */
    public NWZC195001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * execute (This can be called method from external class.)
     * @param param NWXC005001 Validation Bean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param.getInputPMsg());

        try {
            doProcess(param, msgMap, onBatchType);
        } catch (S21NwfException e) {
            if ((e.getMessageInfo() != null) && (ZYPCommonFunc.hasValue(e.getMessageInfo().getCode()))) {
                msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                msgMap.addXxMsgId(NWZM0474E);
            }
            logger.error("Workflow Simulate Error", e);
        }

        msgMap.flush();
    }

    /**
     * One message in List is taken out,and executed.
     * @param params NWXC005001 Validation Bean List
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> params, final ONBATCH_TYPE onBatchType) {

        for (NWXC005001ValidationBean param : params) {
            execute(param, onBatchType);
        }
    }

    private void doProcess(NWXC005001ValidationBean param, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) throws S21NwfException {

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!checkInput(msgMap, inMsg)) {
            return;
        }

        if (!checkProcDfn(param, inMsg)) {
            return;
        }

        if (existManualPriceHold(inMsg)) {
            return;
        }

        if (!existManualPriceChange(inMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum_O, inMsg.cpoOrdNum_I);
        ZYPEZDItemValueSetter.setValue(inMsg.hldRsnCd, HLD_RSN.MANUAL_PRICE);
    }

    private boolean checkInput(S21ApiMessageMap msgMap, NWXC005001PMsg inMsg) {

        if (!ZYPCommonFunc.hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(inMsg.cpoOrdNum_I)) {
            msgMap.addXxMsgId(NWZM0002E);
            return false;
        }

        return true;
    }

    private boolean checkProcDfn(NWXC005001ValidationBean param, NWXC005001PMsg inMsg) {

        DS_ORD_TP_PROC_DFNTMsg procDfTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(procDfTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(procDfTMsg.dsOrdTpCd, param.getCTMsg().dsOrdTpCd.getValue());

        procDfTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(procDfTMsg);

        if (procDfTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(procDfTMsg.manPrcNodeUsgFlg.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean existManualPriceHold(NWXC005001PMsg inMsg) {

        final HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("016");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", inMsg.cpoOrdNum_I.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.MANUAL_PRICE);
        condition.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);

        return S21ApiTBLAccessor.count(condition) > 0;
    }

    private boolean existManualPriceChange(NWXC005001PMsg inMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());
        ssmParam.put("prcCondTpManualPrice", PRC_COND_TP.MANUAL_PRICE);
        List<String> prcDtlGrpCdList = new ArrayList<String>();
        prcDtlGrpCdList.add(PRC_DTL_GRP.BASE_PRICE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.FREIGHT);
        prcDtlGrpCdList.add(PRC_DTL_GRP.HANDLING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.FUEL_SURCHARGE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.SHIPPING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.RESTOCKING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_1);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_2);
        ssmParam.put("prcDtlGrpCdList", prcDtlGrpCdList);

        int count = (Integer) ssmClient.queryObject("countManualPriceChange", ssmParam);
        return count > 0;
    }
}
