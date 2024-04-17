/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC173001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPOTMsg;
import business.db.DS_CUST_TRX_RULETMsg;
import business.db.DS_CUST_TRX_RULETMsgArray;
import business.db.HLDTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
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
 * 2016/10/19   Fujitsu         M.Ohno          Update          S21_NA#14637
 * 2017/05/17   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7319
 *</pre>
 */
public class NWZC173001 extends S21ApiCommonBase {

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
    public NWZC173001() {
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
        CPOTMsg cpoTMsg = param.getCTMsg();

        if (existPoReviewHold(inMsg)) {
            return;
        }

        if (!checkPoMandatory(inMsg, cpoTMsg)) {
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
     * Check exist PO review hold.
     * @param inMsg NWXC005001PMsg
     * @return If it has PO review hold, return true.
     */
    private boolean existPoReviewHold(NWXC005001PMsg inMsg) {

        HLDTMsg condition = new HLDTMsg();

        condition.setSQLID("019");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", inMsg.cpoOrdNum_I.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.PO_REVIEW);

        return S21ApiTBLAccessor.count(condition) > 0;
    }

    /**
     * Check exist PO review hold.
     * @param inMsg NWXC005001PMsg
     * @param cpoTMsg CPOTMsg
     * @return If PO Mandatory, return false.
     */
    private boolean checkPoMandatory(NWXC005001PMsg inMsg, CPOTMsg cpoTMsg) {

        String dsTrxRuleTpCd = getDsTrxRuleTpCd(inMsg, cpoTMsg);
        if (!ZYPCommonFunc.hasValue(dsTrxRuleTpCd)) {
            return false;
        }

        if (!checkCustPoNum(inMsg, cpoTMsg, dsTrxRuleTpCd)) {
            return false;
        }

        return true;
    }

    /**
     * Get DS Trx Rule Type Code
     * @param inMsg NWXC005001PMsg
     * @param cpoTMsg CPOTMsg
     * @return DS Trx Rule Type Code
     */
    private String getDsTrxRuleTpCd(NWXC005001PMsg inMsg, CPOTMsg cpoTMsg) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", cpoTMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", cpoTMsg.dsOrdTpCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", cpoTMsg.dsOrdCatgCd.getValue());

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return null;
    }

    /**
     * Check customer PO number.
     * @param inMsg NWXC005001PMsg
     * @param cpoTMsg CPOTMsg
     * @param dsCpoConfigTMsg DS_CPO_CONFIGTMsg
     * @param dsTrxRuleTpCd DS Trx Rule Type Code
     * @return Check OK : true
     */
    private boolean checkCustPoNum(NWXC005001PMsg inMsg, CPOTMsg cpoTMsg, String dsTrxRuleTpCd) {

        String localPoReqFlg = getLocalPoReqFlg(inMsg, cpoTMsg, dsTrxRuleTpCd);
        if (!ZYPCommonFunc.hasValue(localPoReqFlg) || ZYPConstant.FLG_OFF_N.equals(localPoReqFlg)) {
            String accountPoReqFlg = getAccountPoReqFlg(inMsg, cpoTMsg, dsTrxRuleTpCd);
            if (!ZYPCommonFunc.hasValue(accountPoReqFlg) || ZYPConstant.FLG_OFF_N.equals(accountPoReqFlg)) {
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(cpoTMsg.custIssPoNum)) {
            return false;
        }

        return true;
    }

    /**
     * Get Location DS PO Required Flag
     * @param inMsg NWXC005001PMsg
     * @param cpoTMsg CPOTMsg
     * @param dsCpoConfigTMsg DS_CPO_CONFIGTMsg
     * @param dsTrxRuleTpCd DS Trx Rule Type Code
     * @return Location DS PO Required Flag
     */
    private String getLocalPoReqFlg(NWXC005001PMsg inMsg, CPOTMsg cpoTMsg, String dsTrxRuleTpCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", cpoTMsg.billToCustCd.getValue());
        ssmParam.put("dsTrxRuleTpCd", dsTrxRuleTpCd);
        return (String) ssmClient.queryObject("getLocalPoReqFlg", ssmParam);
    }

    /**
     * Get Account DS PO Required Flag
     * @param inMsg NWXC005001PMsg
     * @param cpoTMsg CPOTMsg
     * @param dsTrxRuleTpCd DS Trx Rule Type Code
     * @return Account DS PO Required Flag
     */
    private String getAccountPoReqFlg(NWXC005001PMsg inMsg, CPOTMsg cpoTMsg, String dsTrxRuleTpCd) {

        DS_CUST_TRX_RULETMsg condition = new DS_CUST_TRX_RULETMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("dsAcctNum01", cpoTMsg.billToCustAcctCd.getValue());
        condition.setConditionValue("dsTrxRuleTpCd01", dsTrxRuleTpCd);

        DS_CUST_TRX_RULETMsgArray tmsgArray = (DS_CUST_TRX_RULETMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).dsPoReqFlg.getValue();
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
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.hldRsnCd, HLD_RSN.PO_REVIEW);
//        }
//        inMsg.shpgPlnList.setValidCount(index);
        inMsg.cpoOrdNum_O.setValue(inMsg.cpoOrdNum_I.getValue());
        inMsg.hldRsnCd.setValue(HLD_RSN.PO_REVIEW);
        // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Mod End
    }

    // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Del Start
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
    // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Del End
}
