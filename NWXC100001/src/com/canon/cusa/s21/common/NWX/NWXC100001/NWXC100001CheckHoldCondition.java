/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPO_DTLTMsg;
import business.db.MDSETMsg;
import business.db.SHPG_PLNTMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Billing Block check.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/12   Fujitsu         S.Tsunaki       Create          N/A
 * 2012/11/20   Fujitsu         S.Tsunaki       Update          Defect #58
 * 2013/07/16   Fujitsu         N.Nakazawa      Update          WDS R-OM028
 * 2014/04/01   Fujitsu         N.Nakazawa      Update          Defect#3695
 * 2020/04/07   Fujitsu         Y.Kanefusa      Update          S21_NA#56481
 *</pre>
 */
public class NWXC100001CheckHoldCondition {

    /** Result Message */
    private String messageId = null;

    /** Hold ShpgPlnNum Number Cache */
    private Map<String, Boolean> holdCpoOrdNumMap = new HashMap<String, Boolean>();

    /** SSM Batch Client */
    public static final S21SsmBatchClient SSM_BATCH_CLIENT = S21SsmBatchClient.getClient(NWXC100001CheckHoldCondition.class);

    /** Revenue Process */
    public static final String PROCESS_MODE_REVENUE = "01";

    /** Printed Process */
    public static final String PPROCESS_MODE_PRINTED = "02";

    /** Shipped Process */
    public static final String PPROCESS_MODE_SHIPPED = "03";

    /** Process Error */
    public static final String NWZM1175E = "NWZM1175E";

    /** Parent BOM_LVL_LINE_NUM */
    public static final String PARENT_BOM_LVL_LINE_NUM = "000000";
    /** Parent SET_CPO_DTL_SUB_LINE_NUM */
    public static final String PARENT_SET_CPO_SUB_LINE_NUM = "000";

    /**
     * Check Hold Condition.
     * @param shpgPlnTMsg SHPG_PLNTMsg
     * @param processMode mode
     * @return true:No Hold false:Exist Hold
     */
    public boolean checkHoldCondition(SHPG_PLNTMsg shpgPlnTMsg, String processMode) {

        if (shpgPlnTMsg == null || !ZYPCommonFunc.hasValue(processMode)) {
            messageId = NWZM1175E;
            return false;
        }

        return checkHoldProcess(
                new NWXC100001CheckHoldConditionBean(
                shpgPlnTMsg.glblCmpyCd.getValue(),
                shpgPlnTMsg.trxHdrNum.getValue(),
                shpgPlnTMsg.trxLineNum.getValue(),
                shpgPlnTMsg.trxLineSubNum.getValue(),
                shpgPlnTMsg.trxSrcTpCd.getValue(),
                shpgPlnTMsg.shpgPlnNum.getValue(),
                shpgPlnTMsg.poReqFlg.getValue(),
                shpgPlnTMsg.mdseCd.getValue(),
                shpgPlnTMsg.shipCpltCd.getValue(),
                shpgPlnTMsg.setShpgPlnNum.getValue()
                ),
                processMode
                );
    }

    /**
     * Check Hold Condition.
     * @param shpgPlnTMsgList SHPG_PLNTMsg List
     * @param processMode mode
     * @return true:No Hold false:Exist Hold
     */
    public boolean checkHoldCondition(List<SHPG_PLNTMsg> shpgPlnTMsgList, String processMode) {

        if (shpgPlnTMsgList == null || !ZYPCommonFunc.hasValue(processMode)) {
            messageId = NWZM1175E;
            return false;
        }

        for (SHPG_PLNTMsg shpgPlnTMsg : shpgPlnTMsgList) {
            if (!checkHoldCondition(shpgPlnTMsg, processMode)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check Hold Condition.
     * @param bean Prameter Bean
     * @param processMode Revenue:01 / SO Printed:02
     * @return true:No Hold false:Exist Hold
     */
    public boolean checkHoldCondition(NWXC100001CheckHoldConditionBean bean, String processMode) {

        if (!ZYPCommonFunc.hasValue(processMode)
                || !ZYPCommonFunc.hasValue(bean.getGlblCmpyCd())
                || !ZYPCommonFunc.hasValue(bean.getTrxHdrNum())
                || !ZYPCommonFunc.hasValue(bean.getTrxLineNum())
                || !ZYPCommonFunc.hasValue(bean.getTrxLineSubNum())
                || !ZYPCommonFunc.hasValue(bean.getMdseCd())) {
            messageId = NWZM1175E;
            return false;
        }

        return checkHoldProcess(bean, processMode);
    }

    /**
     * Check Hold Condition.
     * @param glblCmpyCd glblCmpyCd
     * @param order SHPG_PLN.TRX_HDR_NUM
     * @param processMode Revenue:01 / SO Printed:02
     * @return true:No Hold false:Exist Hold
     */
    public boolean checkHoldCondition(String glblCmpyCd, String order, String processMode) {

        if (!ZYPCommonFunc.hasValue(processMode)
                || !ZYPCommonFunc.hasValue(order)) {
            messageId = NWZM1175E;
            return false;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", order);
        ssmParam.put("shpgStsCd", new String[] {SHPG_STS.INVOICED, SHPG_STS.CANCELLED});

        List<SHPG_PLNTMsg> list = (List<SHPG_PLNTMsg>) SSM_BATCH_CLIENT.queryObjectList("shpgPlnNotMultiStsCd", ssmParam);

        if (list != null && !list.isEmpty()) {
            for (SHPG_PLNTMsg msg : list) {
                if (!checkHoldCondition(msg, processMode)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Check Hold Condition.
     * @param bean Prameter Bean
     * @param processMode Revenue:01 / SO Printed:02
     * @return true:No Hold false:Exist Hold
     */
    private boolean checkHoldProcess(NWXC100001CheckHoldConditionBean bean, String processMode) {

        // Reset error message
        messageId = null;

        // #3. CPO Level Hold Check
        if (!checkCpoLevelHold(bean.getGlblCmpyCd(), bean.getTrxHdrNum(), processMode)) {
            return false;
        }

        // Get Warehouse code
        String whCd = getWhCd(bean.getGlblCmpyCd(), bean.getPoReqFlg(), bean.getMdseCd());

        // #4. Line & Shipping Plan Hold Check
        return checkLineAndShpgPlnLevelHold(bean, processMode, whCd);
    }

    private boolean checkCpoLevelHold(String glblCmpyCd, String cpoOrdNum, String processMode) {

        // cpoOrdNum Cache check
        if (holdCpoOrdNumMap.containsKey(cpoOrdNum)) {
            return holdCpoOrdNumMap.get(cpoOrdNum);
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        // WDS Defect #58
        //ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
        ssmParam.put("hldLvlCd", HLD_LVL.CPO_LEVEL);
        ssmParam.put("whTpCd", WH_TP.COMMON);
        ssmParam.put("processMode", processMode);

        int count = (Integer) SSM_BATCH_CLIENT.queryObject("queryCpoLevelHold", ssmParam);

        if (count == 0) {
            holdCpoOrdNumMap.put(cpoOrdNum, Boolean.TRUE);
            return true;
        } else {
            holdCpoOrdNumMap.put(cpoOrdNum, Boolean.FALSE);
            return false;
        }
    }

    private boolean checkLineAndShpgPlnLevelHold(NWXC100001CheckHoldConditionBean bean, String processMode, String whCd) {

        
        List<Map<String, String>> ssmParamList = new ArrayList<Map<String, String>>();

        Map<String, String> masterParam = createSsmParam(bean, processMode, whCd);
        ssmParamList.add(masterParam);
        
        CPO_DTLTMsg cpoDtlTMsg  = new CPO_DTLTMsg();
        cpoDtlTMsg.cpoOrdNum.setValue(bean.getTrxHdrNum());
        cpoDtlTMsg.cpoDtlLineNum.setValue(bean.getTrxLineNum());
        cpoDtlTMsg.cpoDtlLineSubNum.setValue(bean.getTrxLineSubNum());
        cpoDtlTMsg.glblCmpyCd.setValue(bean.getGlblCmpyCd());
        cpoDtlTMsg = (CPO_DTLTMsg)S21ApiTBLAccessor.findByKey(cpoDtlTMsg);
        if (cpoDtlTMsg == null) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(bean.getShpgPlnNum())) {
            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, bean.getShpgPlnNum());
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, bean.getGlblCmpyCd());
            
            SHPG_PLNTMsg shpgPlnTMsgRes = (SHPG_PLNTMsg)S21CacheTBLAccessor.findByKey(shpgPlnTMsg);
            if (shpgPlnTMsgRes == null) {
                return true;
            }
            if (ZYPCommonFunc.hasValue(shpgPlnTMsgRes.setShpgPlnNum)) {
                masterParam.put("setShpgPlnNum", shpgPlnTMsgRes.setShpgPlnNum.getValue());
            } else if (PARENT_SET_CPO_SUB_LINE_NUM.equals(shpgPlnTMsgRes.trxLineSubNum.getValue())) {
                masterParam.put("setShpgPlnNum", shpgPlnTMsgRes.shpgPlnNum.getValue());
            }
            // Defect#3695 Start
            else {
                masterParam.put("shpgPlnNum", shpgPlnTMsgRes.shpgPlnNum.getValue());
            }
            // Defect#3695 End
        }
        // Defect#3695 Start
        // Defect#3695 End
        
        
        // Query get TargetData
        masterParam.put("parentCpoDtlSubLineNum", PARENT_SET_CPO_SUB_LINE_NUM);
        List<Map<String, String>> resultList = (List<Map<String, String>>) SSM_BATCH_CLIENT.queryObjectList("queryTagetData", masterParam);
        if (resultList != null && !resultList.isEmpty()) {
            for (Map<String, String> resultMap : resultList) {
                ssmParamList.add(createSsmParam(resultMap, bean.getGlblCmpyCd(), processMode, whCd));
            }
        }

        for (Map<String, String> ssmParam : ssmParamList) {
            if (!checkLineAndShpgPlnLevelHold(ssmParam)) {
                return false;
            }
        }

        return true;
    }

    private Map<String, String> createSsmParam(Map<String, String> ssmResult, String glblCmpyCd, String processMode, String whCd) {
        String shpgPlnNum = ssmResult.get("SHPG_PLN_NUM");
        String cpoOrdNum = ssmResult.get("TRX_HDR_NUM");
        String cpoDtlLineNum = ssmResult.get("TRX_LINE_NUM");
        String cpoDtlLineSubNum = ssmResult.get("TRX_LINE_SUB_NUM");
        String trxSrcTpCd = ssmResult.get("TRX_SRC_TP_CD");
        return createSsmParam(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, trxSrcTpCd, shpgPlnNum, null, null, processMode, whCd);
    }

    private Map<String, String> createSsmParam(NWXC100001CheckHoldConditionBean bean, String processMode, String whCd) {
        return createSsmParam(bean.getGlblCmpyCd(), bean.getTrxHdrNum(), bean.getTrxLineNum(), bean.getTrxLineSubNum(),
                bean.getTrxSrcTpCd(), bean.getShpgPlnNum(), bean.getShipCpltCd(), bean.getSetShpgPlnNum(),
                processMode, whCd);
    }

    private Map<String, String> createSsmParam(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String trxSrcTpCd, String shpgPlnNum, String shipCpltCd, String setShpgPlnNum, String processMode,
            String whCd) {

        Map<String, String> param = new HashMap<String, String>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("cpoDtlLineNum", cpoDtlLineNum);
        param.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        if (ZYPCommonFunc.hasValue(trxSrcTpCd)) {
            param.put("trxSrcTpCd", trxSrcTpCd);
        }
        if (ZYPCommonFunc.hasValue(shpgPlnNum)) {
            param.put("shpgPlnNum", shpgPlnNum);
        }
        if (ZYPCommonFunc.hasValue(shipCpltCd)) {
            param.put("shipCpltCd", shipCpltCd);
        }
        if (ZYPCommonFunc.hasValue(setShpgPlnNum)) {
            param.put("setShpgPlnNum", setShpgPlnNum);
        }
        param.put("processMode", processMode);
        param.put("whTpCd", whCd);
        // QC#56247 2020/04/07 Add Start
        param.put("trxSrcTpWholeSales", TRX_SRC_TP.WHOLE_SALES);
        // QC#56247 2020/04/07 Add End


        return param;
    }

    private boolean checkLineAndShpgPlnLevelHold(Map<String, String> ssmParam) {

        ssmParam.put("hldLvlCd", HLD_LVL.CPO_DETAIL_LEVEL);

        int count = (Integer) SSM_BATCH_CLIENT.queryObject("queryCpoDtlLevelHold", ssmParam);
        if (count != 0) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(ssmParam.get("shpgPlnNum"))) {
            ssmParam.put("hldLvlCd", HLD_LVL.SHIPPING_PLAN_LEVEL);

            count = (Integer) SSM_BATCH_CLIENT.queryObject("queryShpgPlnLevelHold", ssmParam);
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private String getWhCd(String glblCmpyCd, String poReqFlg, String mdseCd) {

        if (ZYPCommonFunc.hasValue(poReqFlg)
                && ZYPConstant.FLG_ON_Y.equals(poReqFlg)) {
            return WH_TP.VENDOR;
        } else {
            MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
            if (mdseMsg != null) {
                if (ZYPConstant.FLG_OFF_N.equals(mdseMsg.invtyCtrlFlg.getValue())) {
                    return WH_TP.NON_W_OR_H;
                }
            }
        }

        return WH_TP.COMMON;
    }

    /**
     * Get error message ID.
     * @return Error Message ID
     */
    public String getMessageId() {
        return messageId;
    }

}
