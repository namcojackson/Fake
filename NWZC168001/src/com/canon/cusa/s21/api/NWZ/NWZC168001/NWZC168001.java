/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC168001;

import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM0789E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1782E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1790E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1793E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1841E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1842E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1843E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1844E;
import static com.canon.cusa.s21.api.NWZ.NWZC168001.constant.NWZC168001Constant.NWZM1870E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPOTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SPLY_TRK_RPT_WRKTMsg;
import business.parts.NWZC168001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * NWZC168001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   CUSA            T.Murai         Create          N/A
 *</pre>
 */
public class NWZC168001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NWZC168001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * NWZC168001.execute </pre>
     * @param param NWZC168001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC168001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap);

        msgMap.flush();
    }

    /**
     * NWZC168001.execute(List)
     * @param params List<NWZC168001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC168001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC168001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * doProcess
     * @param msgMap S21ApiMessageMap
     */
    private void doProcess(S21ApiMessageMap msgMap) {

        if (!paramCheck(msgMap)) {
            return;
        }

        NWZC168001PMsg param = (NWZC168001PMsg) msgMap.getPmsg();
        if (ZYPConstant.FLG_ON_Y.equals(param.delFlg.getValue())) {
            deleteSupplyTrackingWork(msgMap);
        }

        List<Map<String, String>> trackingList = getTracking(msgMap);
        if (trackingList == null || trackingList.size() <= 0) {
            msgMap.addXxMsgId(NWZM1844E);
            return;
        }

        insertSupplyTrackingWork(trackingList, msgMap);
    }

    /**
     * paramCheck
     * @param msgMap S21ApiMessageMap
     */
    private boolean paramCheck(S21ApiMessageMap msgMap) {

        NWZC168001PMsg param = (NWZC168001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0789E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM1790E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.bizAppId)) {
            msgMap.addXxMsgId(NWZM1793E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.otptOpCd)) {
            msgMap.addXxMsgId(NWZM1782E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.sellToCustCd)) {
            msgMap.addXxMsgId(NWZM1870E);
            return false;
        }

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setSQLID("046");
        inTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inTMsg.setConditionValue("sellToCustCd01", param.sellToCustCd.getValue());
        SELL_TO_CUSTTMsgArray outTMsg = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        if (outTMsg.getValidCount() <= 0) {
            msgMap.addXxMsgId(NWZM1841E);
            return false;
        }

        if (ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            CPOTMsg inCpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(inCpoTMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inCpoTMsg.cpoOrdNum, param.cpoOrdNum);
            CPOTMsg outCpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(inCpoTMsg);

            if (outCpoTMsg == null) {
                msgMap.addXxMsgId(NWZM1842E);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.delFlg)) {
            ZYPEZDItemValueSetter.setValue(param.delFlg, ZYPConstant.FLG_ON_Y);
        }

        return true;
    }

    /**
     * deleteSupplyTrackingWork
     * @param msgMap S21ApiMessageMap
     */
    @SuppressWarnings("unchecked")
    private void deleteSupplyTrackingWork(S21ApiMessageMap msgMap) {

        NWZC168001PMsg param = (NWZC168001PMsg) msgMap.getPmsg();

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("bizAppId", param.bizAppId.getValue());
        mapParam.put("otptOpCd", param.otptOpCd.getValue());
        mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());

        List<Map<String, BigDecimal>> delList = (List<Map<String, BigDecimal>>) ssmBatchClient.queryObjectList("getDeleteWrk", mapParam);

        if (delList != null) {
            for (Map<String, BigDecimal> map : delList) {
                BigDecimal wrkPk = map.get("SPLY_TRK_RPT_WRK_PK");
                if (ZYPCommonFunc.hasValue(wrkPk)) {
                    SPLY_TRK_RPT_WRKTMsg inTMsg = new SPLY_TRK_RPT_WRKTMsg();
                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inTMsg.splyTrkRptWrkPk, wrkPk);
                    SPLY_TRK_RPT_WRKTMsg outTMsg = (SPLY_TRK_RPT_WRKTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

                    if (outTMsg != null) {
                        S21ApiTBLAccessor.remove(outTMsg);
                    }
                }
            }
        }
    }

    /**
     * getTracking
     * @param msgMap S21ApiMessageMap
     * @return List<Map<String, String>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, String>> getTracking(S21ApiMessageMap msgMap) {

        NWZC168001PMsg param = (NWZC168001PMsg) msgMap.getPmsg();

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("sellToCustCd", param.sellToCustCd.getValue());
        mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
        mapParam.put("inbdOtbtCd", INBD_OTBD.INBOUND);

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getTracking", mapParam);
    }

    /**
     * insertSupplyTrackingWork
     * @param trackinglList List<Map< String, String>>
     * @param msgMap S21ApiMessageMap
     */
    private void insertSupplyTrackingWork(List<Map<String, String>> trackinglList, S21ApiMessageMap msgMap) {

        NWZC168001PMsg param = (NWZC168001PMsg) msgMap.getPmsg();

        for (Map<String, String> tracking : trackinglList) {
            SPLY_TRK_RPT_WRKTMsg inTMsg = new SPLY_TRK_RPT_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.splyTrkRptWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_TRK_RPT_WRK_SQ));
            ZYPEZDItemValueSetter.setValue(inTMsg.bizAppId, param.bizAppId);
            ZYPEZDItemValueSetter.setValue(inTMsg.otptOpCd, param.otptOpCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.custIssPoNum, tracking.get("CUST_ISS_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, tracking.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoWrkLineNum, tracking.get("CPO_WRK_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.sellToCustNm, tracking.get("SELL_TO_CUST_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.shipToInfoTxt, tracking.get("SHIP_TO_INFO_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, tracking.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseDescShortTxt, tracking.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.ordQtyTxt, tracking.get("ORD_QTY_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.shipQtyTxt, tracking.get("SHIP_QTY_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.actlShipDtTxt, tracking.get("ACTL_SHIP_DT_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.carrNm, tracking.get("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.proNum, tracking.get("PRO_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.refValTxt, tracking.get("REF_VAL_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.hdrLineInfoTxt, tracking.get("HDR_LINE_INFO_TXT"));

            S21ApiTBLAccessor.insert(inTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1843E);
                return;
            }
        }
    }
}
