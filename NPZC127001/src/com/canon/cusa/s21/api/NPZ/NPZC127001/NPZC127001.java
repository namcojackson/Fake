/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC127001;

import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.MODE_COMPLETION;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.MODE_CREATE;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.MODE_DELETE;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.MODE_SUBMIT;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.MODE_UPDATE;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPAM1371E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPAM1372E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPAM1373E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0179E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0219E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0220E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0221E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0222E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0223E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0225E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0226E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0227E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0228E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0229E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.NPZM0269E;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.RTRN_DSPL_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.RTRN_REQ_PRT_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant.TASK_NUM_LEN;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.RMNF_ORDTMsg;
import business.db.RMNF_TASKTMsg;
import business.db.RMNF_USGTMsg;
import business.db.RTL_SWHTMsg;
import business.parts.NPZC127001PMsg;

import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Reman Task Update API
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS            S.Tanikawa      Create          N/A
 * 08/02/2016   CSAI            Y.Imazu         Update          QC#12826
 * 09/29/2016   CITS            T.Wada          Update          QC#13282
 * 01/29/2018   CITS            T.Wada          Update          QC#23072
 * 02/01/2019   CITS            T.Hakodate      Update          QC#30019
 * </pre>
 */

public class NPZC127001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient instance.
     */
    private S21SsmBatchClient client = null;

    /**
     * Constructor
     */
    public NPZC127001() {
        super();
    }

    /**
     * Reman Task Update API
     * @param pMsg NPZC127001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(final NPZC127001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        // initializes message map.
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        this.client = S21SsmBatchClient.getClient(this.getClass());

        try {
            // mandatory check
            if (!mandatoryCheck(pMsg, msgMap)) {
                return;
            }

            // get reman order in process
            RMNF_ORDTMsg rmnfOrdTMsg = new RMNF_ORDTMsg();
            rmnfOrdTMsg = getActiveRmnfOrd(pMsg);

            if (rmnfOrdTMsg == null) {
                msgMap.addXxMsgId(NPAM1371E);
                return;
            }

            // check order start date is earlier than task start date
            if (ZYPCommonFunc.hasValue(rmnfOrdTMsg.rmnfStartDt) && ZYPCommonFunc.hasValue(pMsg.rmnfTaskStartDt)) {
                if (0 < ZYPDateUtil.compare(rmnfOrdTMsg.rmnfStartDt.getValue(), pMsg.rmnfTaskStartDt.getValue())) {
                    msgMap.addXxMsgId(NPAM1372E);
                    return;
                }
            }

            if (MODE_CREATE.equals(pMsg.xxModeCd.getValue())) {
                executeCreate(pMsg, rmnfOrdTMsg, msgMap);
            } else if (MODE_UPDATE.equals(pMsg.xxModeCd.getValue())) {
                executeUpdate(pMsg, rmnfOrdTMsg, msgMap);
            } else if (MODE_DELETE.equals(pMsg.xxModeCd.getValue())) {
                executeDelete(pMsg, rmnfOrdTMsg, msgMap);
            } else if (MODE_COMPLETION.equals(pMsg.xxModeCd.getValue())) {
                executeCompletion(pMsg, rmnfOrdTMsg, msgMap);
            } else if (MODE_SUBMIT.equals(pMsg.xxModeCd.getValue())) {
            	executeSubmit(pMsg, rmnfOrdTMsg, msgMap);
            }
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
    private boolean mandatoryCheck(NPZC127001PMsg pMsg, S21ApiMessageMap msgMap) {

        boolean returnVal = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NPZM0179E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {
            msgMap.addXxMsgId(NPZM0219E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.rmnfOrdNum)) {
            msgMap.addXxMsgId(NPZM0220E);
            returnVal = false;
        }

        if (MODE_CREATE.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.techTocCd)) {
                msgMap.addXxMsgId(NPZM0222E);
                returnVal = false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.rmnfTaskDescTxt)) {
                msgMap.addXxMsgId(NPZM0269E);
                returnVal = false;
            }
        } else if (MODE_UPDATE.equals(pMsg.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.rmnfTaskNum)) {
                msgMap.addXxMsgId(NPZM0221E);
                returnVal = false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.techTocCd)) {
                msgMap.addXxMsgId(NPZM0222E);
                returnVal = false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.rmnfTaskDescTxt)) {
                msgMap.addXxMsgId(NPZM0269E);
                returnVal = false;
            }
        } else if (MODE_DELETE.equals(pMsg.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.rmnfTaskNum)) {
                msgMap.addXxMsgId(NPZM0221E);
                returnVal = false;
            }
        } else if (MODE_COMPLETION.equals(pMsg.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.rmnfTaskNum)) {
                msgMap.addXxMsgId(NPZM0221E);
                returnVal = false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.techTocCd)) {
                msgMap.addXxMsgId(NPZM0222E);
                returnVal = false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.rmnfLborAot)) {
                msgMap.addXxMsgId(NPZM0223E);
                returnVal = false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.rmnfTaskEndDt)) {
                msgMap.addXxMsgId(NPZM0225E);
                returnVal = false;
            }
        }
        return returnVal;
    }

    /**
     * getActiveRmnfOrd
     * @param pMsg
     * @return
     */
    private RMNF_ORDTMsg getActiveRmnfOrd(NPZC127001PMsg pMsg) {
        RMNF_ORDTMsg tMsg = new RMNF_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, "0");
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, pMsg.rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdStsCd, RMNF_ORD_STS.IN_PROCESS);

        RMNF_ORDTMsg rmnfOrdTMsg = (RMNF_ORDTMsg) S21ApiTBLAccessor.findByKey(tMsg);

        return rmnfOrdTMsg;
    }

    /**
     * executeCreate
     * @param pMsg
     * @param ordTMsg
     * @param msgMap
     */
    private void executeCreate(NPZC127001PMsg pMsg, RMNF_ORDTMsg ordTMsg, S21ApiMessageMap msgMap) {

        String rmnfTaskNum = getRemanTaskNum(pMsg);
        BigDecimal rmnfTaskPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RMNF_TASK_SQ);

        RMNF_TASKTMsg taskTMsg = new RMNF_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(taskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskPk, rmnfTaskPk);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfOrdNum, pMsg.rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskNum, rmnfTaskNum);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskStsCd, RMNF_TASK_STS.OPEN);
        ZYPEZDItemValueSetter.setValue(taskTMsg.techTocCd, pMsg.techTocCd);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskStartDt, pMsg.rmnfTaskStartDt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborAot, pMsg.rmnfLborAot);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborCmntTxt, pMsg.rmnfLborCmntTxt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.spclInstnCmntTxt, pMsg.spclInstnCmntTxt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskDescTxt, pMsg.rmnfTaskDescTxt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskEndDt, pMsg.rmnfTaskEndDt);

        // QC#30019 add start
        // get Labor Cost per Hour
        BigDecimal rmnfCostPerHourAmt = getLaborCost(pMsg);

        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfCostPerHourAmt, rmnfCostPerHourAmt);
        if (ZYPCommonFunc.hasValue(rmnfCostPerHourAmt) && ZYPCommonFunc.hasValue(pMsg.rmnfLborAot)) {
            ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborCostAmt, rmnfCostPerHourAmt.multiply(pMsg.rmnfLborAot.getValue()));
        }
        // QC#30019 add end
                
        // insert RMNF_TASK
        S21ApiTBLAccessor.insert(taskTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(taskTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NPZM0226E);
            return;
        }

        // insert RMNF_USG
        insertRmnfUsg(pMsg, ordTMsg, taskTMsg, msgMap);
    }

    /**
     * executeUpdate
     * @param pMsg
     * @param ordTMsg
     * @param msgMap
     */
    private void executeUpdate(NPZC127001PMsg pMsg, RMNF_ORDTMsg ordTMsg, S21ApiMessageMap msgMap) {

        // get Reman Task PK
        RMNF_TASKTMsg taskTMsg = getRemanTaskPk(pMsg);
        if (taskTMsg == null) {
            msgMap.addXxMsgId(NPAM1373E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(taskTMsg.techTocCd, pMsg.techTocCd);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskStartDt, pMsg.rmnfTaskStartDt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborAot, pMsg.rmnfLborAot);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborCmntTxt, pMsg.rmnfLborCmntTxt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.spclInstnCmntTxt, pMsg.spclInstnCmntTxt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskDescTxt, pMsg.rmnfTaskDescTxt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskEndDt, pMsg.rmnfTaskEndDt);

        // QC#30019 add start
        // get Labor Cost per Hour
        BigDecimal rmnfCostPerHourAmt = getLaborCost(pMsg);

        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfCostPerHourAmt, rmnfCostPerHourAmt);
        if (ZYPCommonFunc.hasValue(rmnfCostPerHourAmt) && ZYPCommonFunc.hasValue(pMsg.rmnfLborAot)) {
            ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborCostAmt, rmnfCostPerHourAmt.multiply(pMsg.rmnfLborAot.getValue()));
        }
        // QC#30019 add end

        // update RMNF_TASK
        S21ApiTBLAccessor.update(taskTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(taskTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NPZM0227E);
            return;
        }

        // delete parts usage
        if (!deleteRemanTaskUsg(pMsg.glblCmpyCd.getValue(), pMsg.rmnfOrdNum.getValue(), taskTMsg.rmnfTaskNum.getValue(), msgMap)) {
            return;
        }

        // insert RMNF_USG
        insertRmnfUsg(pMsg, ordTMsg, taskTMsg, msgMap);
    }

    /**
     * deleteRemanTaskUsg
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @param rmnfTaskNum
     * @param msgMap
     */
    private boolean deleteRemanTaskUsg(String glblCmpyCd, String rmnfOrdNum, String rmnfTaskNum, S21ApiMessageMap msgMap) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", glblCmpyCd);
        queryParams.put("rmnfOrdNum", rmnfOrdNum);
        queryParams.put("rmnfTaskNum", rmnfTaskNum);

        List<Map> resultList = (List<Map>) this.client.queryObjectList("getRemanTaskUsgPk", queryParams);
        if ((resultList == null) || (resultList.size() == 0)) {
            return true;
        }
        for (int i = 0; i < resultList.size(); i++) {
            RMNF_USGTMsg item = new RMNF_USGTMsg();
            ZYPEZDItemValueSetter.setValue(item.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(item.rmnfUsgPk, (BigDecimal) resultList.get(i).get("RMNF_USG_PK"));
            S21ApiTBLAccessor.remove(item);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(item.getReturnCode())) {
                msgMap.addXxMsgId(NPZM0229E);
                return false;
            }
        }
        return true;
    }

    /**
     * removeRemanTaskUsg
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @param rmnfTaskNum
     * @param msgMap
     */
    private boolean removeRemanTaskUsg(String glblCmpyCd, String rmnfOrdNum, String rmnfTaskNum, S21ApiMessageMap msgMap) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", glblCmpyCd);
        queryParams.put("rmnfOrdNum", rmnfOrdNum);
        queryParams.put("rmnfTaskNum", rmnfTaskNum);

        List<Map> resultList = (List<Map>) this.client.queryObjectList("getRemanTaskUsgPk", queryParams);
        if ((resultList == null) || (resultList.size() == 0)) {
            return true;
        }
        for (int i = 0; i < resultList.size(); i++) {
            RMNF_USGTMsg item = new RMNF_USGTMsg();
            ZYPEZDItemValueSetter.setValue(item.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(item.rmnfUsgPk, (BigDecimal) resultList.get(i).get("RMNF_USG_PK"));
            S21ApiTBLAccessor.logicalRemove(item);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(item.getReturnCode())) {
                msgMap.addXxMsgId(NPZM0229E);
                return false;
            }
        }
        return true;
    }

    /**
     * executeDelete
     * @param pMsg
     * @param ordTMsg
     * @param msgMap
     */
    private void executeDelete(NPZC127001PMsg pMsg, RMNF_ORDTMsg ordTMsg, S21ApiMessageMap msgMap) {

        // get Reman Task PK
        RMNF_TASKTMsg taskTMsg = getRemanTaskPk(pMsg);
        if (taskTMsg == null) {
            msgMap.addXxMsgId(NPAM1373E);
            return;
        }

        // set update value
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskStsCd, RMNF_TASK_STS.CANCEL);

        // update RMNF_TASK
        S21ApiTBLAccessor.update(taskTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(taskTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NPZM0227E);
            return;
        }

        removeRemanTaskUsg(pMsg.glblCmpyCd.getValue(), pMsg.rmnfOrdNum.getValue(), taskTMsg.rmnfTaskNum.getValue(), msgMap);
    }

    /**
     * executeCompletion
     * @param pMsg
     * @param ordTMsg
     * @param msgMap
     */
    private void executeCompletion(NPZC127001PMsg pMsg, RMNF_ORDTMsg ordTMsg, S21ApiMessageMap msgMap) {

        // get Reman Task PK
        RMNF_TASKTMsg taskTMsg = getRemanTaskPk(pMsg);
        if (taskTMsg == null) {
            msgMap.addXxMsgId(NPAM1373E);
            return;
        }

        // set update value
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskStsCd, RMNF_TASK_STS.CLOSED);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskEndDt, pMsg.rmnfTaskEndDt);
        // update RMNF_TASK
        S21ApiTBLAccessor.update(taskTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(taskTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NPZM0227E);
            return;
        }
    }
    /**
     * executeSubmit
     * @param pMsg
     * @param ordTMsg
     * @param msgMap
     */
    private void executeSubmit(NPZC127001PMsg pMsg, RMNF_ORDTMsg ordTMsg, S21ApiMessageMap msgMap) {

        // get Reman Task PK
        RMNF_TASKTMsg taskTMsg = getRemanTaskPk(pMsg);
        if (taskTMsg == null) {
            msgMap.addXxMsgId(NPAM1373E);
            return;
        }

        // get Labor Cost per Hour
        BigDecimal rmnfCostPerHourAmt = getLaborCost(pMsg);

        // set update value
        if (ZYPCommonFunc.hasValue(pMsg.rmnfTaskEndDt)) {
            ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskStsCd, RMNF_TASK_STS.CLOSED);
            ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfTaskEndDt, pMsg.rmnfTaskEndDt);
        }
        ZYPEZDItemValueSetter.setValue(taskTMsg.techTocCd, pMsg.techTocCd);


        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborAot, pMsg.rmnfLborAot);
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfCostPerHourAmt, rmnfCostPerHourAmt);
        if (ZYPCommonFunc.hasValue(rmnfCostPerHourAmt) && ZYPCommonFunc.hasValue(pMsg.rmnfLborAot)) {
            ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborCostAmt, rmnfCostPerHourAmt.multiply(pMsg.rmnfLborAot.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(taskTMsg.rmnfLborCmntTxt, pMsg.rmnfLborCmntTxt);
        ZYPEZDItemValueSetter.setValue(taskTMsg.spclInstnCmntTxt, pMsg.spclInstnCmntTxt);

        // update RMNF_TASK
        S21ApiTBLAccessor.update(taskTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(taskTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NPZM0227E);
            return;
        }

        // delete parts usage
        if (!deleteRemanTaskUsg(pMsg.glblCmpyCd.getValue(), pMsg.rmnfOrdNum.getValue(), taskTMsg.rmnfTaskNum.getValue(), msgMap)) {
            return;
        }

        // insert usage
        for (int i = 0; i < pMsg.remanUsgList.getValidCount(); i++) {
            // get Item Master/ Inventory Info
            Map<String, Object> invtyInfo = getInvty(pMsg, ordTMsg, i);

            // when inventory available quantity is less than usage
            // parts quantity
//            if (((BigDecimal) invtyInfo.get(INVTY_AVAL_QTY)).compareTo(pMsg.remanUsgList.no(i).rmnfPrtQty.getValue()) < 0) {
//                msgMap.addXxMsgId(NLCM0081E);
//                return;
//            }

            NLXC001001GetInventoryItemCostBean beanUsgCost = new NLXC001001GetInventoryItemCostBean();
            NLXC001001GetInventoryItemCostBean beanRcvryCost = new NLXC001001GetInventoryItemCostBean();

            String invtyLocCd = "";

            // when DEL_FLG is not ON
            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.remanUsgList.no(i).delFlg.getValue())) {

                // get Parts Usage Cost
                beanUsgCost.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
                beanUsgCost.setInvtyLocCd(ordTMsg.rmnfUsgRtlWhCd.getValue() + ordTMsg.rmnfUsgRtlSwhCd.getValue());
                beanUsgCost.setMdseCd(pMsg.remanUsgList.no(i).mdseCd.getValue());
                beanUsgCost.setQty(pMsg.remanUsgList.no(i).rmnfPrtQty.getValue());

                NLXC001001GetInventoryItemCost.getInventoryItemCost(beanUsgCost);

                if (!beanUsgCost.getErrList().isEmpty()) {

                    for (String errId : beanUsgCost.getErrList()) {

                        msgMap.addXxMsgId(errId);
                    }

                    return;
                }

                // get Parts Asset Recovery Cost
                if (ZYPConstant.FLG_ON_Y.equals((String) invtyInfo.get(RTRN_REQ_PRT_FLG))) {

                    invtyLocCd = getInvtyLocCd(pMsg.glblCmpyCd.getValue(), ordTMsg.rmnfUsgRtlWhCd.getValue(), (String) invtyInfo.get(RTRN_DSPL_TP_CD));

                    beanRcvryCost.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
                    beanRcvryCost.setInvtyLocCd(invtyLocCd);
                    beanRcvryCost.setMdseCd(pMsg.remanUsgList.no(i).mdseCd.getValue());
                    beanRcvryCost.setQty(pMsg.remanUsgList.no(i).rmnfPrtQty.getValue());

                    NLXC001001GetInventoryItemCost.getInventoryItemCost(beanRcvryCost);

                    if (!beanRcvryCost.getErrList().isEmpty()) {

                        for (String errId : beanRcvryCost.getErrList()) {

                            msgMap.addXxMsgId(errId);
                        }

                        return;
                    }
                }
            }

//            if (ZYPCommonFunc.hasValue(pMsg.remanUsgList.no(i).rmnfPrtQty) && (0 < pMsg.remanUsgList.no(i).rmnfPrtQty.getValue().intValue())) {
	            RMNF_USGTMsg usgTMsg = new RMNF_USGTMsg();
	
	            BigDecimal rmnfUsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RMNF_USG_SQ);
	
	            // set value for create
	            ZYPEZDItemValueSetter.setValue(usgTMsg.glblCmpyCd, pMsg.glblCmpyCd);
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfUsgPk, rmnfUsgPk);
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfTaskPk, taskTMsg.rmnfTaskPk);
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfOrdNum, pMsg.rmnfOrdNum);
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfTaskNum, taskTMsg.rmnfTaskNum);
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfInvtyLocCd, ordTMsg.rmnfUsgRtlWhCd.getValue() + ordTMsg.rmnfUsgRtlSwhCd.getValue());
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfUsgRtrnInvtyLocCd, invtyLocCd);
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rtrnReqPrtFlg, (String) invtyInfo.get(RTRN_REQ_PRT_FLG));
	            ZYPEZDItemValueSetter.setValue(usgTMsg.mdseCd, pMsg.remanUsgList.no(i).mdseCd);

                if (ZYPCommonFunc.hasValue(pMsg.remanUsgList.no(i).rmnfPrtQty)) {
                    ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtQty, pMsg.remanUsgList.no(i).rmnfPrtQty);
                } else {
                	ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtQty, BigDecimal.ZERO);
                }
                if (ZYPCommonFunc.hasValue(pMsg.remanUsgList.no(i).rmnfPrtRelQty)) {
                    ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtRelQty, pMsg.remanUsgList.no(i).rmnfPrtRelQty);
                } else {
                	ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtRelQty, BigDecimal.ZERO);
                }

	            ZYPEZDItemValueSetter.setValue(usgTMsg.prtUnitCostAmt, beanUsgCost.getUnitPrcAmt());
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtUsgCostAmt, beanUsgCost.getTotPrcAmt());
	            ZYPEZDItemValueSetter.setValue(usgTMsg.assetRecovCostAmt, beanRcvryCost.getTotPrcAmt());
	            ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfUsgLineNum, ZYPCommonFunc.leftPad(String.valueOf(i + 1), TASK_NUM_LEN, "0"));
	
	            if (ZYPConstant.FLG_ON_Y.equals(pMsg.remanUsgList.no(i).rmnfPrtUsgComitFlg.getValue())) {
	                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtUsgComitFlg, ZYPConstant.FLG_ON_Y);
	            } else {
	            	ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtUsgComitFlg, ZYPConstant.FLG_OFF_N);
	            }
	
	            // insert RMNF_USG
	            S21ApiTBLAccessor.insert(usgTMsg);
	            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(usgTMsg.getReturnCode())) {
	                msgMap.addXxMsgId(NPZM0228E);
	                return;
	            }
        	
            }
        }

//    }






    /**
     * getRemanTaskNum
     * @param pMsg
     * @return
     */
    private String getRemanTaskNum(NPZC127001PMsg pMsg) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParams.put("rmnfOrdNum", pMsg.rmnfOrdNum);

        BigDecimal taskNumMax = (BigDecimal) this.client.queryObject("getRemanTaskNum", queryParams);
        if (taskNumMax == null) {
            return "001";
        }
        int taskNum = taskNumMax.intValue();
        return ZYPCommonFunc.leftPad(String.valueOf(taskNum + 1), TASK_NUM_LEN, "0");
    }

    /**
     * getRemanTaskPk
     * @param pMsg
     * @return
     */
    private RMNF_TASKTMsg getRemanTaskPk(NPZC127001PMsg pMsg) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParams.put("rmnfOrdNum", pMsg.rmnfOrdNum);
        queryParams.put("rmnfTaskNum", pMsg.rmnfTaskNum);

        Map<String, BigDecimal> map = (Map<String, BigDecimal>) this.client.queryObject("getRemanTaskPk", queryParams);
        if (map != null) {
            RMNF_TASKTMsg tMsg = new RMNF_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfTaskPk, map.get("RMNF_TASK_PK"));
            return (RMNF_TASKTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
        }
        return null;
    }

    /**
     * insertRmnfUsg
     * @param pMsg
     * @param ordTMsg
     * @param taskTMsg
     * @param msgMap
     */
    private void insertRmnfUsg(NPZC127001PMsg pMsg, RMNF_ORDTMsg ordTMsg, RMNF_TASKTMsg taskTMsg, S21ApiMessageMap msgMap) {

        for (int i = 0; i < pMsg.remanUsgList.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.remanUsgList.no(i).delFlg.getValue())) {
                BigDecimal rmnfUsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RMNF_USG_SQ);

                RMNF_USGTMsg usgTMsg = new RMNF_USGTMsg();
                ZYPEZDItemValueSetter.setValue(usgTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfUsgPk, rmnfUsgPk);
                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfTaskPk, taskTMsg.rmnfTaskPk);
                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfOrdNum, pMsg.rmnfOrdNum);
                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfTaskNum, taskTMsg.rmnfTaskNum);
                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfInvtyLocCd, ordTMsg.rmnfUsgRtlWhCd.getValue() + ordTMsg.rmnfUsgRtlSwhCd.getValue());
                ZYPEZDItemValueSetter.setValue(usgTMsg.mdseCd, pMsg.remanUsgList.no(i).mdseCd);

                if (ZYPCommonFunc.hasValue(pMsg.remanUsgList.no(i).rmnfPrtQty)) {
                    ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtQty, pMsg.remanUsgList.no(i).rmnfPrtQty);
                } else {
                	ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtQty, BigDecimal.ZERO);
                }
                if (ZYPCommonFunc.hasValue(pMsg.remanUsgList.no(i).rmnfPrtRelQty)) {
                    ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtRelQty, pMsg.remanUsgList.no(i).rmnfPrtRelQty);
                } else {
                	ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtRelQty, BigDecimal.ZERO);
                }

                ZYPEZDItemValueSetter.setValue(usgTMsg.rtrnReqPrtFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfUsgLineNum, ZYPCommonFunc.leftPad(String.valueOf(i + 1), TASK_NUM_LEN, "0"));

                if (ZYPConstant.FLG_ON_Y.equals(pMsg.remanUsgList.no(i).rmnfPrtUsgComitFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtUsgComitFlg, ZYPConstant.FLG_ON_Y);
                } else {
                	ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtUsgComitFlg, ZYPConstant.FLG_OFF_N);
                }

                // QC#30019 add start
                String invtyLocCd = "";
                NLXC001001GetInventoryItemCostBean beanUsgCost = new NLXC001001GetInventoryItemCostBean();
                NLXC001001GetInventoryItemCostBean beanRcvryCost = new NLXC001001GetInventoryItemCostBean();

                Map<String, Object> invtyInfo = getInvty(pMsg, ordTMsg, i);

                // get Parts Usage Cost
                beanUsgCost.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
                beanUsgCost.setInvtyLocCd(ordTMsg.rmnfUsgRtlWhCd.getValue() + ordTMsg.rmnfUsgRtlSwhCd.getValue());
                beanUsgCost.setMdseCd(pMsg.remanUsgList.no(i).mdseCd.getValue());
                beanUsgCost.setQty(pMsg.remanUsgList.no(i).rmnfPrtQty.getValue());

                NLXC001001GetInventoryItemCost.getInventoryItemCost(beanUsgCost);

                if (!beanUsgCost.getErrList().isEmpty()) {

                    for (String errId : beanUsgCost.getErrList()) {

                        msgMap.addXxMsgId(errId);
                    }

                    return;
                }

                // get Parts Asset Recovery Cost
                if (ZYPConstant.FLG_ON_Y.equals((String) invtyInfo.get(RTRN_REQ_PRT_FLG))) {

                    invtyLocCd = getInvtyLocCd(pMsg.glblCmpyCd.getValue(), ordTMsg.rmnfUsgRtlWhCd.getValue(), (String) invtyInfo.get(RTRN_DSPL_TP_CD));

                    beanRcvryCost.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
                    beanRcvryCost.setInvtyLocCd(invtyLocCd);
                    beanRcvryCost.setMdseCd(pMsg.remanUsgList.no(i).mdseCd.getValue());
                    beanRcvryCost.setQty(pMsg.remanUsgList.no(i).rmnfPrtQty.getValue());

                    NLXC001001GetInventoryItemCost.getInventoryItemCost(beanRcvryCost);

                    if (!beanRcvryCost.getErrList().isEmpty()) {

                        for (String errId : beanRcvryCost.getErrList()) {

                            msgMap.addXxMsgId(errId);
                        }

                        return;
                    }
                }
                ZYPEZDItemValueSetter.setValue(usgTMsg.prtUnitCostAmt, beanUsgCost.getUnitPrcAmt());
                ZYPEZDItemValueSetter.setValue(usgTMsg.rmnfPrtUsgCostAmt, beanUsgCost.getTotPrcAmt());
                ZYPEZDItemValueSetter.setValue(usgTMsg.assetRecovCostAmt, beanRcvryCost.getTotPrcAmt());

                // QC#30019 add end

                // insert RMNF_USG
                S21ApiTBLAccessor.insert(usgTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(usgTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NPZM0228E);
                    return;
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskNum, taskTMsg.rmnfTaskNum);
    }

    /**
     * getLaborCost
     * @param pMsg NPZC127001PMsg
     * @return BigDecimal
     */
    private BigDecimal getLaborCost(NPZC127001PMsg pMsg) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", pMsg.glblCmpyCd);

        return (BigDecimal) this.client.queryObject("getLaborCost", queryParams);
    }

    /**
     * getInvty
     * @param pMsg NPZC127001PMsg
     * @param ordTMsg RMNF_ORDTMsg
     * @param indx int
     * @return Map<String, Object>
     */
    private Map<String, Object> getInvty(NPZC127001PMsg pMsg, RMNF_ORDTMsg ordTMsg, int indx) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParams.put("mdseCd", pMsg.remanUsgList.no(indx).mdseCd);
        queryParams.put("invtyLocCd", ordTMsg.rmnfUsgRtlWhCd.getValue() + ordTMsg.rmnfUsgRtlSwhCd.getValue());
        queryParams.put("locStsCd", LOC_STS.WORK_IN_PROCESS_REMAN);
        queryParams.put("stkStsCd", STK_STS.GOOD);

        return (Map<String, Object>) this.client.queryObject("getInvty", queryParams);
    }

    /**
     * getInvtyLocCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @return rtlSwhCd
     */
    private String getInvtyLocCd(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, rtlSwhCd);
        rtlSwhTMsg = (RTL_SWHTMsg) S21ApiTBLAccessor.findByKey(rtlSwhTMsg);

        if (rtlSwhTMsg == null) {

            return null;
        }

        return rtlSwhTMsg.invtyLocCd.getValue();
    }
    /**
     * 
     * @param rmnfOrdNum
     * @param rtlWhCd
     * @param glblCmpyCd
     * @return
     */



}
