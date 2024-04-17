/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC126001;

import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.MODE_CANCEL;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPAM1371E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPAM1545E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPZM0028E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPZM0033E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPZM0179E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPZM0215E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPZM0219E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.NPZM0220E;
import static com.canon.cusa.s21.api.NPZ.NPZC126001.constant.NPZC126001Constant.VAR_RMNF_ORD_STS_W4C;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.RMNF_ORDTMsg;
import business.db.RMNF_PRT_REQTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.WMS_OTBD_SO_WRKTMsg;
import business.parts.NPZC126001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Reman Order Update API
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CITS            S.Tanikawa      Create          N/A
 * 10/07/2016   CITS            T.Wada          Update          QC#13807
 * 01/29/2018   CITS            T.Wada          Update          QC#23072
 * </pre>
 */
public class NPZC126001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient instance.
     */
    private S21SsmBatchClient client = null;

    /**
     * Constructor
     */
    public NPZC126001() {
        super();
    }

    /**
     * Reman Order Update API
     * @param pMsg NPZC126001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(final NPZC126001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        // initializes message map.
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        this.client = S21SsmBatchClient.getClient(this.getClass());

        try {

            if (!mandatoryCheck(pMsg, msgMap)) {
                msgMap.flush();
                return;
            }

            RMNF_ORDTMsg rmnfOrdTMsg = getActiveRmnfOrder(msgMap, pMsg);

            // no record
            if (rmnfOrdTMsg == null) {
                return;
            }

            updateRmnfOrd(msgMap, pMsg, rmnfOrdTMsg);

        } finally {

            // send the messages to PMsg
            msgMap.flush();

        }
    }

    /**
     * mandatoryCheck
     * @param pMsg
     * @param msgMap
     * @return
     */
    private boolean mandatoryCheck(NPZC126001PMsg pMsg, S21ApiMessageMap msgMap) {

        boolean returnVal = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NPZM0179E);
            returnVal = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {
            msgMap.addXxMsgId(NPZM0219E);
            returnVal = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NPZM0033E);
            returnVal = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.rmnfOrdNum)) {
            msgMap.addXxMsgId(NPZM0220E);
            returnVal = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.soNum)) {
            msgMap.addXxMsgId(NPZM0028E);
            returnVal = false;
        }

        return returnVal;
    }

    /**
     * getActiveRmnfOrder
     * @param msgMap S21ApiMessageMap
     * @param pMsg NPZC126001PMsg
     * @return RMNF_ORDTMsg
     */
    private RMNF_ORDTMsg getActiveRmnfOrder(S21ApiMessageMap msgMap, NPZC126001PMsg pMsg) {

        // get VarCharConst
        String rmnfOrdStsW4c = ZYPCodeDataUtil.getVarCharConstValue(VAR_RMNF_ORD_STS_W4C, pMsg.glblCmpyCd.getValue());

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParams.put("rmnfOrdNum", pMsg.rmnfOrdNum);
// QC#13807
        List<String> rmnfOrdSts = new ArrayList<String>();
        rmnfOrdSts.add(RMNF_ORD_STS.IN_PROCESS);
        rmnfOrdSts.add(RMNF_ORD_STS.SO_CANCELLED);
        queryParams.put("rmnfOrdSts", rmnfOrdSts);
        Map resultMap = (Map) this.client.queryObject("getRemanOrder", queryParams);

        if (resultMap == null) {
            msgMap.addXxMsgId(NPAM1371E);
            return null;
        }

        RMNF_ORDTMsg tMsg = new RMNF_ORDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, pMsg.rmnfOrdNum);

        tMsg = (RMNF_ORDTMsg) S21ApiTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            msgMap.addXxMsgId(NPAM1371E);
            return null;
        }
        

        return tMsg;
    }

    /**
     * getSO
     * @param pMsg
     * @param List<Map<String, Object>>
     * @return
     */
    private List<Map<String, Object>> getSO(NPZC126001PMsg pMsg) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParams.put("soNum", pMsg.soNum);
        queryParams.put("soSlpNum", pMsg.soSlpNum);

        return (List<Map<String, Object>>) this.client.queryObjectList("getSO", queryParams);
    }

    /**
     * updateRmnfOrd
     * @param msgMap
     * @param pMsg
     * @param rmnfOrd
     * @return
     */
    private boolean updateRmnfOrd(S21ApiMessageMap msgMap, NPZC126001PMsg pMsg, RMNF_ORDTMsg rmnfOrd) {

        if (pMsg.xxModeCd.getValue().equals(MODE_CANCEL)) {

            // get SO
            List<Map<String, Object>> soList = getSO(pMsg);

            if ((soList == null) || (soList.size() == 0)) {
                msgMap.addXxMsgId(NPAM1545E);
                return false;

            }

            // cancel Rmn Parts Req QC#13807
            if (SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals((String) soList.get(0).get("SCE_ORD_TP_CD"))) {
                // cancel Rmn Parts Req
                cancelPrtReqForSoCancel(pMsg);
            }

            // cancel allocation
            if (!allocationNWZC1070(msgMap, pMsg, soList)) {

                return false;

            }

            if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals((String) soList.get(0).get("SCE_ORD_TP_CD"))) {

                // update reman order QC#13807
                ZYPEZDItemValueSetter.setValue(rmnfOrd.rmnfOrdStsCd, RMNF_ORD_STS.SO_CANCELLED);
                S21ApiTBLAccessor.update(rmnfOrd);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmnfOrd.getReturnCode())) {

                    // add error message
                    msgMap.addXxMsgId(NPZM0215E);
                    return false;
                }

            }

            return true;
        }
        // QC#23072
        else if (pMsg.xxModeCd.getValue().equals(RMNF_ORD_STS.COMPLETED)) {
            if (!updateRemanOrderSts(msgMap, pMsg, RMNF_ORD_STS.COMPLETED)) {

                return false;
            }

            return true;

        } else {

            return false;

        }
    }
    /**
     * updateRemanOrderSts
     * @param msgMap
     * @param pMsg
     * @param rmnfOrdNum
     * @param rmnfOrdStsCd
     * @param glblCmpyCd
     * @return
     */
    private boolean updateRemanOrderSts(S21ApiMessageMap msgMap, NPZC126001PMsg pMsg, String rmnfOrdStsCd) {

    	String glblCmpyCd = pMsg.glblCmpyCd.getValue();
    	String rmnfOrdNum = pMsg.rmnfOrdNum.getValue();
    	
        RMNF_ORDTMsg oldMsg = new RMNF_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(oldMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(oldMsg.rmnfOrdNum, rmnfOrdNum);
        RMNF_ORDTMsg newMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(oldMsg);

        // status Completed.
        ZYPEZDItemValueSetter.setValue(newMsg.rmnfOrdStsCd, rmnfOrdStsCd);

        if (RMNF_ORD_STS.COMPLETED.equals(rmnfOrdStsCd)) {

            // RMNF_END_DT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfEndDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

            // RMNF_PRT_USG_COST_AMT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfPrtUsgCostAmt, getRmnfPrtUsgCostAmt(glblCmpyCd, rmnfOrdNum, null));

            // RMNF_TOT_LBOR_COST_AMT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfTotLborCostAmt, getRmnTotLborCostAmt(glblCmpyCd, rmnfOrdNum, null));

            // RMNF_MACH_COST_AMT
            NLXC001001GetInventoryItemCostBean parm = new NLXC001001GetInventoryItemCostBean();
            parm.setGlblCmpyCd(glblCmpyCd);
            parm.setInvtyLocCd(newMsg.rmnfInvtyLocCd.getValue());
            parm.setMdseCd(getRmnfToCmptMdseCd(newMsg));
            parm.setQty(BigDecimal.ONE);
            parm = NLXC001001GetInventoryItemCost.getInventoryItemCost(parm);
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfMachCostAmt, parm.getTotPrcAmt());

            // RMNF_TOT_COST_AMT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfTotCostAmt, getRmnfTotCostAmt(newMsg));
        }

        EZDTBLAccessor.update(newMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newMsg.getReturnCode())) {
            msgMap.addXxMsgId(NPZM0215E);
            return false;
        }

        return true;
    }
    /**
     * call Allocation For non CPO API
     * @param msgMap
     * @param pMsg
     * @param soList
     * @return
     */
    private boolean allocationNWZC1070(S21ApiMessageMap msgMap, NPZC126001PMsg pMsg, List<Map<String, Object>> soList) {
        List<NWZC107001PMsg> pMsgList = new ArrayList<NWZC107001PMsg>();
        for (Map<String, Object> m : soList) {
            NWZC107001PMsg apiMsg = new NWZC107001PMsg();
            ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC107001.REQ_TP_CANCEL);
            ZYPEZDItemValueSetter.setValue(apiMsg.trxSrcTpCd, (String) m.get("TRX_SRC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(apiMsg.trxHdrNum, (String) m.get("TRX_HDR_NUM"));
            ZYPEZDItemValueSetter.setValue(apiMsg.trxLineNum, (String) m.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(apiMsg.trxLineSubNum, (String) m.get("TRX_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, pMsg.slsDt);
            pMsgList.add(apiMsg);
        }

        Map<String, String> mMap = new HashMap<String, String>();
        // exec API
        new NWZC107001().execute(pMsgList, ONBATCH_TYPE.ONLINE);
        for (NWZC107001PMsg apiMsg : pMsgList) {
            for (int i = 0; i < apiMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = apiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (!mMap.containsKey(msgId)) {
                    mMap.put(msgId, msgId);
                }
            }
        }
        if (0 < mMap.size()) {
            for (Map.Entry<String, String> e : mMap.entrySet()) {
                msgMap.addXxMsgId(e.getKey());
            }
            return false;
        }
        return true;
    }
    /**
     * cancelRmnPrtReq
     * @param msgMap
     * @param pMsg
     * @param soList
     * @return
     */
    private boolean cancelRmnPrtReq(S21ApiMessageMap msgMap, NPZC126001PMsg pMsg, List<Map<String, Object>> soList) {
        List<NWZC107001PMsg> pMsgList = new ArrayList<NWZC107001PMsg>();
        for (Map<String, Object> m : soList) {
            NWZC107001PMsg apiMsg = new NWZC107001PMsg();
            ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC107001.REQ_TP_CANCEL);
            ZYPEZDItemValueSetter.setValue(apiMsg.trxSrcTpCd, (String) m.get("TRX_SRC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(apiMsg.trxHdrNum, (String) m.get("TRX_HDR_NUM"));
            ZYPEZDItemValueSetter.setValue(apiMsg.trxLineNum, (String) m.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(apiMsg.trxLineSubNum, (String) m.get("TRX_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, pMsg.slsDt);
            pMsgList.add(apiMsg);
        }

        Map<String, String> mMap = new HashMap<String, String>();
        // exec API
        new NWZC107001().execute(pMsgList, ONBATCH_TYPE.ONLINE);
        for (NWZC107001PMsg apiMsg : pMsgList) {
            for (int i = 0; i < apiMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = apiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (!mMap.containsKey(msgId)) {
                    mMap.put(msgId, msgId);
                }
            }
        }
        if (0 < mMap.size()) {
            for (Map.Entry<String, String> e : mMap.entrySet()) {
                msgMap.addXxMsgId(e.getKey());
            }
            return false;
        }
        return true;
    }
    /**
     * getPrtReqForCancel
     * @param pMsg
     * @return
     */
    private List<Map<String, Object>> getPrtReqForCancel(NPZC126001PMsg pMsg) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParams.put("rmnfOrdNum", pMsg.rmnfOrdNum);
        queryParams.put("soNum", pMsg.soNum);

        return (List<Map<String, Object>>) this.client.queryObjectList("getPrtReqForCancel", queryParams);
    }

    /**
     * getRmnfToCmptMdseCd
     * @param tMsg
     * @return
     */
    private String getRmnfToCmptMdseCd(RMNF_ORDTMsg tMsg) {

        Map<String, Object> queryParams = new HashMap<String, Object>();

        queryParams.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        queryParams.put("rmnfOrdNum", tMsg.rmnfOrdNum.getValue());

        return  (String)this.client.queryObject("getRmnfToCmptMdseCd", queryParams);

    }

    /**
     * getRmnfTotCostAmt
     * @param tMsg
     * @return
     */
    private BigDecimal getRmnfTotCostAmt(RMNF_ORDTMsg tMsg) {

        BigDecimal rmnfTotCostAmt = BigDecimal.ZERO;
        BigDecimal assetRecovCostAmt = BigDecimal.ZERO;

        Map<String, Object> queryParams = new HashMap<String, Object>();

        queryParams.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        queryParams.put("rmnfOrdNum", tMsg.rmnfOrdNum.getValue());

        assetRecovCostAmt = (BigDecimal)this.client.queryObject("getAssetRecovCostAmt", queryParams);

        BigDecimal rmnfPrtUsgCostAmt = BigDecimal.ZERO;
        if (tMsg.rmnfPrtUsgCostAmt != null) {
        	rmnfPrtUsgCostAmt = tMsg.rmnfPrtUsgCostAmt.getValue();
        }
        BigDecimal rmnfTotLborCostAmt = BigDecimal.ZERO;
        if (tMsg.rmnfTotLborCostAmt != null) {
            rmnfTotLborCostAmt = tMsg.rmnfTotLborCostAmt.getValue();
        }

        BigDecimal rmnfMachCostAmt = BigDecimal.ZERO;
        if (tMsg.rmnfMachCostAmt != null) {
        	rmnfMachCostAmt = tMsg.rmnfMachCostAmt.getValue();
        }

        rmnfTotCostAmt = rmnfPrtUsgCostAmt.add(rmnfTotLborCostAmt).add(rmnfMachCostAmt).subtract(assetRecovCostAmt);

        return rmnfTotCostAmt;

    }

    /**
     * getRmnfPrtUsgCostAmt
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @param lineNum
     * @return
     */
    private BigDecimal getRmnfPrtUsgCostAmt(String glblCmpyCd, String rmnfOrdNum, String lineNum) {

        BigDecimal rmnfPrtUsgCostAmt = BigDecimal.ZERO;

        Map<String, Object> queryParams = new HashMap<String, Object>();

        queryParams.put("glblCmpyCd", glblCmpyCd);
        queryParams.put("rmnfOrdNum", rmnfOrdNum);

        rmnfPrtUsgCostAmt = (BigDecimal)this.client.queryObject("getRmnfPrtUsgCostAmt", queryParams);


        return rmnfPrtUsgCostAmt;

    }

    /**
     * getRmnTotLborCostAmt
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @param lineNum
     * @return
     */
    private BigDecimal getRmnTotLborCostAmt(String glblCmpyCd, String rmnfOrdNum, String lineNum) {

        BigDecimal rmnfLborCostAmt = BigDecimal.ZERO;

        Map<String, Object> queryParams = new HashMap<String, Object>();

        queryParams.put("glblCmpyCd", glblCmpyCd);
        queryParams.put("rmnfOrdNum", rmnfOrdNum);
        //queryParams.put("rmnfTaskNum", lineNum);
        queryParams.put("cancel", RMNF_TASK_STS.CANCEL);

        rmnfLborCostAmt = (BigDecimal)this.client.queryObject("getRmnTotLborCostAmt", queryParams);

        return rmnfLborCostAmt;

    }

    /**
     * cancelPrtReqForCancel
     * @param pMsg
     * @return
     */
    private boolean cancelPrtReqForSoCancel(NPZC126001PMsg pMsg) {
        List<Map<String, Object>> pkList = getPrtReqForCancel(pMsg); 

        // Update PROC_STS_CD of WMS_OTBD_SO_WRK table.
        for (Map<String, Object> pk : pkList) {
            RMNF_PRT_REQTMsg rmnfPrtReqTMsg = new RMNF_PRT_REQTMsg();
            ZYPEZDItemValueSetter.setValue(rmnfPrtReqTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rmnfPrtReqTMsg.rmnfPrtReqPk, (BigDecimal)pk.get("RMNF_PRT_REQ_PK"));

            rmnfPrtReqTMsg = (RMNF_PRT_REQTMsg) EZDTBLAccessor.findByKeyForUpdate(rmnfPrtReqTMsg);
            rmnfPrtReqTMsg.soNum.clear();
            rmnfPrtReqTMsg.rmnfPrtRqstProcCd.setValue(ZYPConstant.FLG_OFF_0);

            // Set error message code.
            EZDTBLAccessor.update(rmnfPrtReqTMsg);
        }
        return true;
    }

}
