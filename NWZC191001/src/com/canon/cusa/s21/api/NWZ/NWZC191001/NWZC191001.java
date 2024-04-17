package com.canon.cusa.s21.api.NWZ.NWZC191001;

import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.MAX_MSG_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM0046E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM0347E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM0983E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM0984E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM1008E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM1286E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM1904E;
import static com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant.NWZM1906E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM1909E;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ORD_TPTMsg;
import business.parts.NWZC191001PMsg;
import business.parts.NWZC192001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC191001.constant.NWZC191001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC192001.NWZC192001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Supersede Common API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/18   Fujitsu         S.Ohki          Create          N/A
 * 2019/11/08   Fujitsu         Y.Kanefusa      Update          S21_NA#54235
 *</pre>
 */
public class NWZC191001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatType = null;

    /**
     * Constructor
     */
    public NWZC191001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute
     * 
     * @param param NWZC191001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC191001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.onBatType = onBatchType;

        try {

            doProcess(msgMap);

        } finally {
            msgMap.flush();
            setErrMsg(msgMap); // QC#54235 2019/11/08 Add
        }
    }

    /**
     * doProcess
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void doProcess(S21ApiMessageMap msgMap) {

        if (!paramCheck(msgMap)) {
            return;
        }

        NWZC192001PMsg nwzc192001PMsg = new NWZC192001PMsg();

        if (!getOrdCatgAndOrdTp(msgMap, nwzc192001PMsg)) {
            return;
        }

        if (!callSupersedeCommonAPI(msgMap, nwzc192001PMsg)) {
            return;
        }
    }

    /**
     * paramCheck
     * 
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean paramCheck(S21ApiMessageMap msgMap) {

        NWZC191001PMsg pMsg = (NWZC191001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.lgcyOrdTpCd)) {
            msgMap.addXxMsgId(NWZM0983E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.lgcyOrdRsnCd)) {
            msgMap.addXxMsgId(NWZM0984E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dropShipFlg)) {
            msgMap.addXxMsgId(NWZM0347E);
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.shipToPostCd)) {
                msgMap.addXxMsgId(NWZM1008E);
                return false;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(pMsg.shipToLocNum)) {
                msgMap.addXxMsgId(NWZM1904E);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.origMdseCd)) {
            msgMap.addXxMsgId(NWZM0021E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.ordQty)) {
            msgMap.addXxMsgId(NWZM0046E);
            return false;
        }

        return true;
    }

    /**
     * getOrdCatgAndOrdTp
     * 
     * @param msgMap  S21ApiMessageMap
     * @param nwzc192001PMsg NWZC192001PMsg
     * @return boolean
     */
    private boolean getOrdCatgAndOrdTp(S21ApiMessageMap msgMap,
            NWZC192001PMsg nwzc192001PMsg) {

        NWZC191001PMsg pMsg = (NWZC191001PMsg) msgMap.getPmsg();

        List<Map<String, Object>> list = getXtrnlIntfcXref(pMsg.glblCmpyCd.getValue(), pMsg.lgcyOrdTpCd.getValue(), pMsg.lgcyOrdRsnCd.getValue());
        if (list.size() == 0) {
            msgMap.addXxMsgId(NWZM1906E);
            return false;
        }
        Map<String, Object> xtrnlIntfcXref = list.get(0);

        ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.dsOrdCatgCd, (String) xtrnlIntfcXref.get("TRGT_ATTRB_TXT_02"));
        ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.dsOrdTpCd, (String) xtrnlIntfcXref.get("TRGT_ATTRB_TXT_03"));

        DS_ORD_TPTMsg dsOrdTpTMsg = getDsOrdTp(pMsg.glblCmpyCd.getValue(), nwzc192001PMsg.dsOrdTpCd.getValue());
        if (dsOrdTpTMsg == null) {
            msgMap.addXxMsgId(NWZM1286E);
            return false;
        }
        return true;
    }

    /**
     * getXtrnlIntfcXref
     * 
     * @param glblCmpyCd String
     * @param lgcyOrdTpCd String
     * @param lgcyOrdRsnCd String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getXtrnlIntfcXref(String glblCmpyCd, String lgcyOrdTpCd, String lgcyOrdRsnCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", CPO_SRC_TP.IS_WEB);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.ORDER_TYPE_MAPPING);
        ssmParam.put("srcAttrbTxt02", lgcyOrdTpCd);
        ssmParam.put("srcAttrbTxt03", lgcyOrdRsnCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getXtrnlIntfcXref", ssmParam);
    }

    /**
     * getDsOrdTp
     * 
     * @param glblCmpyCd String
     * @param dsOrdTpCd String
     * @return DS_ORD_TPTMsg
     */
    private DS_ORD_TPTMsg getDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TPTMsg prmTMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsOrdTpCd, dsOrdTpCd);
        return (DS_ORD_TPTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * Get SupersedeMdse
     * 
     * @param msgMap S21ApiMessageMap
     * @param nwzc192001PMsg nwzc192001PMsg
     */
    private boolean callSupersedeCommonAPI(S21ApiMessageMap msgMap, NWZC192001PMsg nwzc192001PMsg) {

        NWZC191001PMsg pMsg = (NWZC191001PMsg) msgMap.getPmsg();

        ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.dropShipFlg, pMsg.dropShipFlg.getValue());
        ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.ordQty, pMsg.ordQty.getValue());
        ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.origMdseCd, pMsg.origMdseCd.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.shipToPostCd, pMsg.shipToPostCd.getValue());
        } else {
            List<Map<String, Object>> list = getShipToCustByLocNum(pMsg.glblCmpyCd.getValue(), pMsg.shipToLocNum.getValue());
            if (list == null || list.size() == 0) {
                msgMap.addXxMsgId(NWZM1909E);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.shipToPostCd, (String) list.get(0).get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(nwzc192001PMsg.shipToLocNum, pMsg.shipToLocNum.getValue());
        }

        NWZC192001 supdCmnAPI = new NWZC192001();
        supdCmnAPI.execute(nwzc192001PMsg, this.onBatType);

        boolean result = true;
        if (!S21ApiUtil.getXxMsgIdList(nwzc192001PMsg).isEmpty()) {
            for (int j = 0; j < nwzc192001PMsg.xxMsgIdList.getValidCount(); j++) {
                String msgId = nwzc192001PMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                msgMap.addXxMsgId(msgId);
                result = false;
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.supdToMdseCd, nwzc192001PMsg.supdToMdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseDescShortTxt_SP, nwzc192001PMsg.mdseDescShortTxt_SP);
        return result;
    }

    /**
     * getShipToCustByLocNum
     * 
     * @param glblCmpyCd String
     * @param locNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getShipToCustByLocNum(String glblCmpyCd, String locNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShipToCustByLocNum", ssmParam);
    }
    // QC#54235 2019/11/08 Add Start
    private void setErrMsg(S21ApiMessageMap msgMap) {
        NWZC191001PMsg pMsg = (NWZC191001PMsg) msgMap.getPmsg();
        int i = 0;
        for (; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(i).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, null)));
        }
        pMsg.xxMsgIdList.setValidCount(i);
    }
    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > MAX_MSG_LEN) {
            return msg.substring(0, MAX_MSG_LEN);
        }
        return msg;
    }
    // QC#54235 2019/11/08 Add End

}
