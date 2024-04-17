/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC176001;

import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.COLUMN_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.COLUMN_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.COLUMN_CPO_ORD_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.NWZM1584E;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.NWZM1585E;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.NWZM1586E;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.NWZM1587E;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.NWZM1588E;
import static com.canon.cusa.s21.api.NWZ.NWZC176001.constant.NWZC176001Constant.NWZM1589E;

import java.util.List;
import java.util.Map;

import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Buyout Billing Hold API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/22/2015   Fujitsu         E.Yoshitake     Create          N/A
 * 2015/12/14   Fujitsu         T.ishii         Update          S21_NA#1880
 * 08/03/2016   Fujitsu         S.Yamamoto      Update          S21_NA#12889
 *</pre>
 */
public class NWZC176001 extends S21ApiCommonBase {

    /**
     * Constructor.
     */
    public NWZC176001() {
        super();
    }

    /**
     * Buyout Approval Hold API
     * @param params List<NWXC005001ValidationBean>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWXC005001ValidationBean> params, ONBATCH_TYPE onBatchType) {

        for (NWXC005001ValidationBean bean : params) {
            execute(bean, onBatchType);
        }
    }

    /**
     * Execute
     * @param bean NWXC005001ValidationBean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean bean, final ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg pMsg = bean.getInputPMsg();

        if (pMsg == null) {
            throw new S21AbendException("NWZM0382E");
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        try {
            // Checking Input value
            if (!checkInputParam(msgMap, bean)) {
                msgMap.flush();
                return;
            }

            // main
            doProcess(msgMap, bean);

        } finally {
            msgMap.flush();
        }
    }

    /**
     * Check the input parameters. If an error occurs, add a message
     * to the Message Map.
     * @param msgMap Message Map
     * @param bean NWXC005001ValidationBean
     * @return Results of the check.(false:error)
     */
    private boolean checkInputParam(S21ApiMessageMap msgMap, NWXC005001ValidationBean bean) {
        NWXC005001PMsg param = (NWXC005001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return false;
        }

        // Order Number
        if (!ZYPCommonFunc.hasValue(param.cpoOrdNum_I)) {
            msgMap.addXxMsgId(NWZM1584E);
            return false;
        }

        // Order Line Number
        if (!ZYPCommonFunc.hasValue(param.cpoDtlLineNum_I)) {
            msgMap.addXxMsgId(NWZM1585E);
            return false;
        }

        // Order Line Sub Number
        if (!ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum_I)) {
            msgMap.addXxMsgId(NWZM1586E);
            return false;
        }

        // Salse Date
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM1587E);
            return false;
        }

        // CPOTMsg
        if (bean.getCTMsg() == null) {
            msgMap.addXxMsgId(NWZM1588E);
            return false;
        }

        // CPO_DTLTMsg
        if (bean.getCdTMsg() == null) {
            msgMap.addXxMsgId(NWZM1589E);
            return false;
        }

        return true;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     * @param bean NWXC005001ValidationBean
     */
    protected void doProcess(S21ApiMessageMap msgMap, NWXC005001ValidationBean bean) {

        // Exists Hold Data
        if (0 < NWZC176001Query.getInstance().cntBuyoutBillingHold(bean)) {
            return;
        }

        // chkCreditOrder
        if (0 < NWZC176001Query.getInstance().cntCreditOrder(bean)) {
            return;
        }
        List<Map<String, String>> resultList = NWZC176001Query.getInstance().getDsCpoDtlInfo(bean);

        if (!resultList.isEmpty()) {
            for (Map<String, String> resultMap :  resultList) {
              if (bean.getDscdTMsg().cpoOrdNum.getValue().equals(resultMap.get(COLUMN_CPO_ORD_NUM))//
                      && bean.getDscdTMsg().cpoDtlLineNum.getValue().equals(resultMap.get(COLUMN_CPO_DTL_LINE_NUM))//
                      && bean.getDscdTMsg().cpoDtlLineSubNum.getValue().equals(resultMap.get(COLUMN_CPO_DTL_LINE_SUB_NUM))) {
                  return;
              }
            }

            // Result Set
            ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().cpoOrdNum_O, bean.getDscdTMsg().cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().cpoDtlLineNum_O, bean.getDscdTMsg().cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().cpoDtlLineSubNum_O, bean.getDscdTMsg().cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().hldRsnCd, HLD_RSN.BUYOUT_BILLING);
        } else {
            return;
        }

    }
}
