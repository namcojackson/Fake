/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC158001;

import java.util.List;

import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/14   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWZC158001 extends S21ApiCommonBase {

    /** Order Number is not entered. */
    private static final String NWZM0002E = "NWZM0002E";

    /** "Global Company Code" is not set. */
    private static final String NWZM0473E = "NWZM0473E";

    /**
     * Constructor
     */
    public NWZC158001() {
        super();
    }

    /**
     * execute (This can be called method from external class.)
     * @param param NWXC005001 Validation Bean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param.getInputPMsg());

        doProcess(param, msgMap, onBatchType);

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

    /**
     * Main process method.
     * @param param NWXC005001 Validation Bean
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(NWXC005001ValidationBean param, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!checkInput(msgMap, inMsg, onBatchType)) {
            return;
        }

        if (!checkProcDfn(param, inMsg)) {
            return;
        }

        if (existValidationHold(inMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum_O, inMsg.cpoOrdNum_I);
        ZYPEZDItemValueSetter.setValue(inMsg.hldRsnCd, HLD_RSN.VALIDATION_HOLD);
    }

    /**
     * Check input parameter.
     * @param msgMap S21ApiMessageMap
     * @param inMsg NWXC005001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return Check Result : normal is true
     */
    private boolean checkInput(S21ApiMessageMap msgMap, NWXC005001PMsg inMsg, ONBATCH_TYPE onBatchType) {

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

    /**
     * Check process definition.
     * @param param NWXC005001 Validation Bean
     * @param inMsg NWXC005001PMsg
     * @return If Validation Approval Node Usage Flag is 'Y', return true.
     */
    private boolean checkProcDfn(NWXC005001ValidationBean param, NWXC005001PMsg inMsg) {

        DS_ORD_TP_PROC_DFNTMsg procDfTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(procDfTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(procDfTMsg.dsOrdTpCd, param.getDscTMsg().dsOrdTpCd.getValue());

        procDfTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21ApiTBLAccessor.findByKey(procDfTMsg);

        if (procDfTMsg != null) {
            String vldApvlNodeUsgFlg = procDfTMsg.vldApvlNodeUsgFlg.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(vldApvlNodeUsgFlg)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check exist Validation Hold.
     * @param inMsg NWXC005001PMsg
     * @return If validation hold exist, return true.
     */
    private boolean existValidationHold(NWXC005001PMsg inMsg) {

        final HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("018");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", inMsg.cpoOrdNum_I.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.VALIDATION_HOLD);

        return S21ApiTBLAccessor.count(condition) > 0;
    }
}
