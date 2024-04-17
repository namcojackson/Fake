package com.canon.cusa.s21.api.NWZ.NWZC192001;

import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWAM0037E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM0036E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM0046E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM0346E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM0347E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM0942E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM1904E;
import static com.canon.cusa.s21.api.NWZ.NWZC192001.constant.NWZC192001Constant.NWZM1909E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC192001PMsg;
import business.parts.NWZC206001PMsg;
import business.parts.NWZC206001_APMsg;

import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.api.NWZ.NWZC206001.constant.NWZC206001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Supersede Common API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/18   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWZC192001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatType = null;

    /**
     * Constructor
     */
    public NWZC192001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute
     * 
     * @param param NWZC192001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC192001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.onBatType = onBatchType;

        try {

            doProcess(msgMap);

        } finally {
            msgMap.flush();
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

        NWZC192001PMsg pMsg = (NWZC192001PMsg) msgMap.getPmsg();
        if (ZYPCommonFunc.hasValue(pMsg.sbstMdseCd)) {
            return;
        }

        MDSETMsg mdseTMsg = getMdse(pMsg.glblCmpyCd.getValue(), pMsg.origMdseCd.getValue());

        if (mdseTMsg == null) {
            msgMap.addXxMsgId(NWZM0036E);
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.custOrdEnblFlg.getValue())) {
            msgMap.addXxMsgId(NWAM0037E);
            return;
        }

        String mdseCd = mdseTMsg.mdseCd.getValue();
        NWZC206001PMsg nwzc206001PMsg = new NWZC206001PMsg();

        if (!getDefWhCd(msgMap, nwzc206001PMsg, mdseTMsg)) {
            return;
        }

        if (!getSupersedeMdseCallAPI(msgMap, nwzc206001PMsg, mdseCd)) {
            return;
        }
        
        getSupersedeMdse(msgMap, nwzc206001PMsg);
    }

    /**
     * paramCheck
     * 
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean paramCheck(S21ApiMessageMap msgMap) {

        NWZC192001PMsg pMsg = (NWZC192001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0346E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dropShipFlg)) {
            msgMap.addXxMsgId(NWZM0347E);
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(pMsg.dropShipFlg.getValue())) {
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
     * getDefWhCd
     * 
     * @param msgMap S21ApiMessageMap
     * @param nwzc206001PMsg NWZC206001PMsg
     * @param mdseTMsg mdseTMsg
     * @return boolean
     */
    private boolean getDefWhCd(S21ApiMessageMap msgMap, NWZC206001PMsg nwzc206001PMsg, MDSETMsg mdseTMsg) {

        NWZC192001PMsg pMsg = (NWZC192001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.whCd, dsWhCd);
            return true;
        } else if (ZYPCommonFunc.hasValue(pMsg.rtlWhCd)) {
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.whCd, pMsg.rtlWhCd);
            return true;
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
            return true;
        }

        boolean result = true;
        NWZC180001PMsg apiMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdTpCd, pMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.mdseCd, mdseTMsg.mdseCd);
        if (ZYPCommonFunc.hasValue(pMsg.shipToPostCd)) {
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, pMsg.shipToPostCd.getValue());
        } else {
            List<Map<String, Object>> list = getShipToCustByLocNum(
                    pMsg.glblCmpyCd.getValue(), pMsg.shipToLocNum.getValue());
            if (list == null || list.size() == 0) {
                msgMap.addXxMsgId(NWZM1909E);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, (String) list.get(0).get("POST_CD"));
        }

        ZYPEZDItemValueSetter.setValue(apiMsg.ordQty, pMsg.ordQty.getValue());

        // call NWZC1800 Default WH API
        new NWZC180001().execute(apiMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                if (msgId.endsWith("E")) {
                    msgMap.addXxMsgId(msgId);
                    result = false;
                }
            }
        } else {

        	if (!ZYPCommonFunc.hasValue(apiMsg.rtlWhCd)) {
        		msgMap.addXxMsgId(NWZM0942E);
        		return false;
        	}
        	String whCd = S21StringUtil.concatStrings(apiMsg.rtlWhCd.getValue(), apiMsg.rtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.whCd, whCd);
        }

        return result;
    }

    /**
     * Get SupersedeMdse CallAPI
     * 
     * @param msgMap S21ApiMessageMap
     * @param nwzc206001PMsg NWZC206001PMsg
     * @return boolean
     */
    private boolean getSupersedeMdseCallAPI(S21ApiMessageMap msgMap, NWZC206001PMsg nwzc206001PMsg, String mdseCd) {

        NWZC192001PMsg pMsg = (NWZC192001PMsg) msgMap.getPmsg();

        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.stkStsCd, STK_STS.GOOD);

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxModeCd, NWZC206001Constant.SUPD_LATEST_MODE);
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxModeCd, NWZC206001Constant.SUPD_LIST_MODE);
            ZYPEZDItemValueSetter.setValue(nwzc206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
        }

        NWZC206001 superSedeAPI = new NWZC206001();
        superSedeAPI.execute(nwzc206001PMsg, this.onBatType);

        boolean result = true;
        if (!S21ApiUtil.getXxMsgIdList(nwzc206001PMsg).isEmpty()) {
            for (int j = 0; j < nwzc206001PMsg.xxMsgIdList.getValidCount(); j++) {
                String msgId = nwzc206001PMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                msgMap.addXxMsgId(msgId);
                result = false;
            }
        }
        return result;
    }

    /**
     * Get SupersedeMdse
     * 
     * @param msgMap S21ApiMessageMap
     * @param nwzc206001PMsg NWZC206001PMsg
     */
    private void getSupersedeMdse(S21ApiMessageMap msgMap, NWZC206001PMsg nwzc206001PMsg) {

        NWZC192001PMsg pMsg = (NWZC192001PMsg) msgMap.getPmsg();
        BigDecimal ordQty = pMsg.ordQty.getValue();

        String supdMdseCd = null;
        String supdMdseNm = null;
        if (NWZC206001Constant.SUPD_LIST_MODE.equals(nwzc206001PMsg.xxModeCd.getValue())) {

            for (int i = 0; i < nwzc206001PMsg.A.getValidCount(); i++) {
                NWZC206001_APMsg supd = nwzc206001PMsg.A.no(i);
                if (supd.invtyAvalQty.getValue().compareTo(ordQty) >= 0) {
                    // Stocked In Inventory
                    ZYPEZDItemValueSetter.setValue(pMsg.supdToMdseCd, supd.mdseCd.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseDescShortTxt_SP, supd.mdseNm.getValue());
                    return;
                }
                supdMdseCd = supd.mdseCd.getValue();
                supdMdseNm = supd.mdseNm.getValue();
            }

        } else if (NWZC206001Constant.SUPD_LATEST_MODE.equals(nwzc206001PMsg.xxModeCd.getValue())) {
            if (0 < nwzc206001PMsg.A.getValidCount()) {
            	supdMdseCd = nwzc206001PMsg.A.no(0).mdseCd.getValue();
            	supdMdseNm = nwzc206001PMsg.A.no(0).mdseNm.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.supdToMdseCd, supdMdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseDescShortTxt_SP, supdMdseNm);

        String origMdseCd = pMsg.origMdseCd.getValue();
        if (ZYPCommonFunc.hasValue(supdMdseCd) && !origMdseCd.equals(supdMdseCd)) {
            String ordTakeMdseCd = getOrdTakeMdseCd(pMsg.glblCmpyCd.getValue(), supdMdseCd);
            ZYPEZDItemValueSetter.setValue(pMsg.supdToMdseCd, ordTakeMdseCd);
        }
    }

    /**
     * <pre>
     * get order take mdse cd.
     * if this method coldn't get order take mdsecd, return parameter mdseCd.
     * (2015/12/04 S21_NA#1290 added)
     * @param glblCmpyCd Global Company code.
     * @param mdseCd Merchandise Code
     * @return Order Take Merchandise Code or parameter mdseCd
     */
    public static String getOrdTakeMdseCd(String glblCmpyCd, String mdseCd) {
        ORD_TAKE_MDSETMsg queryKeyOrdTakeMdse = new ORD_TAKE_MDSETMsg();
        queryKeyOrdTakeMdse.setSQLID("001");
        queryKeyOrdTakeMdse.glblCmpyCd.setValue(glblCmpyCd);
        queryKeyOrdTakeMdse.mdseCd.setValue(mdseCd);

        ORD_TAKE_MDSETMsgArray rsltArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(queryKeyOrdTakeMdse);
        if (0 == rsltArray.getValidCount()) {
            return mdseCd;
        } else {
            return rsltArray.no(0).ordTakeMdseCd.getValue();
        }
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

    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            MDSETMsg queryMdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, mdseCd);

            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
            if (mdseTMsg == null) {

                ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
                ordTakeMdseMsg.setSQLID("002");
                ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

                ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
                if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                    mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
                }
            }
        }
        return mdseTMsg;
    }
}
