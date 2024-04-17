/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC186001;
import java.util.List;

import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HDD_RMV;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 *<pre>
 * HDD Removal Hold API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/05   Fujitsu         W.Honda         Create          QC#14612
 *</pre>
 */

public class NWZC186001 extends S21ApiCommonBase {

    /** Data Company Code is not entered. */
    public static final String NWZM0001E = "NWZM0001E";

    /** Order Number is not entered. */
    public static final String NWZM0002E = "NWZM0002E";

    /** Order Line Number is not entered. */
    public static final String NWZM0003E = "NWZM0003E";

    /** Order Line Sub Number is not entered. */
    public static final String NWZM0004E = "NWZM0004E";

    /** Sales Date is not entered. */
    public static final String NWZM0445E = "NWZM0445E";

    /**
     * Constructor
     */
    public NWZC186001() {
        super();
    }

    /**
     * <pre>
     * Refer to the class comment.
     * One Msg in List is taken out, and the execute(NWXC005001ValidationBean, ONBATCH_TYPE) is executed.
     * </pre>
     * @see #execute(NWXC005001ValidationBean,
     * com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE)
     * @param params Interface Msg list of HDD Removal Hold API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * <pre>
     * Refer to the class comment.
     * </pre>
     * @param param Interface Msg of HDD Removal Hold API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param.getInputPMsg());

        doProcess(param, msgMap);

        msgMap.flush();
    }

    private void doProcess(NWXC005001ValidationBean param, S21ApiMessageMap msgMap) {

        if (!checkInput(msgMap)) {
            return;
        }

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();
        String glblCmpyCd       = inMsg.glblCmpyCd.getValue();
        String cpoOrdNum        = inMsg.cpoOrdNum_I.getValue();
        String cpoDtlLineNum    = inMsg.cpoDtlLineNum_I.getValue();
        String cpoDtlLineSubNum = inMsg.cpoDtlLineSubNum_I.getValue();

        if (!isHddRemoval(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum)) {
            return;
        }

        if (existHddRemovalHold(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum)) {
            return;
        }

        setHldRsn(inMsg, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
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

        if (!ZYPCommonFunc.hasValue(inMsg.cpoDtlLineNum_I)) {
            msgMap.addXxMsgId(NWZM0003E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(inMsg.cpoDtlLineSubNum_I)) {
            msgMap.addXxMsgId(NWZM0004E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(inMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0445E);
            return false;
        }

        return true;
    }

    private static boolean isHddRemoval(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, cpoDtlLineSubNum);

        dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKey(dsCpoRtrnDtlTMsg);
        if (dsCpoRtrnDtlTMsg != null) {
            if (HDD_RMV.HDD_REMOVE.equals(dsCpoRtrnDtlTMsg.hddRmvCd.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static boolean existHddRemovalHold(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        final HLDTMsg hldTMsg = new HLDTMsg();
        hldTMsg.setSQLID("024");
        hldTMsg.setMaxCount(1);
        hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        hldTMsg.setConditionValue("hldRsnCd01", HLD_RSN.HDD_REMOVAL);
        hldTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        hldTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
        hldTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);

        return S21ApiTBLAccessor.count(hldTMsg) != 0;
    }

    /**
     * Set hold reason.
     * @param inMsg NWXC005001PMsg
     * @param cpoTMsg CPOTMsg
     * @param shpgPlnTMsg SHPG_PLNTMsg
     */
    private void setHldRsn(NWXC005001PMsg inMsg, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum_O, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum_O, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum_O, cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.hldRsnCd, HLD_RSN.HDD_REMOVAL);
    }
}
