/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC174001;

import java.util.List;

import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         T.Yoshida       Create          N/A
 * 2015/12/14   Fujitsu         T.ishii         Update          S21_NA#1880
 * 2016/08/10   SRAA            K.Aratani       Update          S21_NA#13323
 * 2016/10/06   Fujitsu         T.Yoshida       Update          S21_NA#14973
 * 2016/11/14   Fujitsu         H.Nagashima     Update          S21_NA#8538
 * 2017/05/16   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7319
 *</pre>
 */
public class NWZC174001 extends S21ApiCommonBase {

    /** Order Number is not entered. */
    private static final String NWZM0002E = "NWZM0002E";

    /** The data does not exist in CPO. */
    private static final String NWZM0073E = "NWZM0073E";

    /** Sales Date is not entered. */
    private static final String NWZM0445E = "NWZM0445E";

    /** "Global Company Code" is not set. */
    private static final String NWZM0473E = "NWZM0473E";

    /** The data does not exist in DS CPO. */
    private static final String NWZM1491E = "NWZM1491E";

    /** Set Parent Line Sub Number */
    public static final String SET_PARENT_LINE_SUB_NUM = "000";

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /**
     * Constructor
     */
    public NWZC174001() {
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

        if (!checkInput(param, msgMap, onBatchType)) {
            return;
        }

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (existFinalReviewHold(inMsg)) {
            return;
        }

        setOutParam(inMsg);
    }

    /**
     * Check input parameter.
     * @param param NWXC005001 Validation Bean
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     * @return No Error : true
     */
    private boolean checkInput(NWXC005001ValidationBean param, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
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

        if (param.getCTMsg() == null) {
            msgMap.addXxMsgId(NWZM0073E);
            return false;
        }

        if (param.getDscTMsg() == null) {
            msgMap.addXxMsgId(NWZM1491E);
            return false;
        }

        return true;
    }

    /**
     * Check exist Final review hold.
     * @param inMsg NWXC005001PMsg
     * @return If it has final review hold, return true.
     */
    private boolean existFinalReviewHold(NWXC005001PMsg inMsg) {

        final HLDTMsg condition = new HLDTMsg();

        condition.setSQLID("019");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", inMsg.cpoOrdNum_I.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.FINAL_REVIEW);
        return S21ApiTBLAccessor.count(condition) > 0;
    }

    /**
     * Set Output Parameter
     * @param inMsg NWXC005001PMsg
     */
    private void setOutParam(NWXC005001PMsg inMsg) {

        // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Mod Start
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
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.hldRsnCd, HLD_RSN.FINAL_REVIEW);
//        }
//        inMsg.shpgPlnList.setValidCount(index);
        inMsg.cpoOrdNum_O.setValue(inMsg.cpoOrdNum_I.getValue());
        inMsg.hldRsnCd.setValue(HLD_RSN.FINAL_REVIEW);
        // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Mod End
    }

    // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Del Start
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
//        ssmParam.put("setParentLineSubNum", SET_PARENT_LINE_SUB_NUM);
//        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
//
//        return (List<Map<String, Object>>) ssmClient.queryObjectList("getShpgPlnList", ssmParam);
//    }
    // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Del End
}
