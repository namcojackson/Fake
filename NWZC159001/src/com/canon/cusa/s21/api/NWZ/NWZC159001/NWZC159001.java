/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC159001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;
import business.parts.NWZC156001PMsg;
import business.parts.NWZC156001_svcConfigRefPMsg;
import business.parts.NWZC156002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC156001.NWZC156001;
import com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC164001.NWZC164001TokenObject;
import com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 *<pre>
 * NWZC1590 Profitability Hold API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/09   Fujitsu         T.Yoshida       Create          N/A
 * 2016/07/19   Fujitsu         S.Iidaka        Update          S21_NA#11920
 * 2016/09/21   Fujitsu         M.Ohno          Update          S21_NA#12194
 * 2016/10/06   Fujitsu         N.Aoyama        Update          S21_NA#14974
 * 2016/10/13   Fujitsu         Mz.Takahashi    Update          S21_NA#14974
 * 2017/04/13   Fujitsu         T.Aoi           Update          S21_NA#18207
 *</pre>
 */
public class NWZC159001 extends S21ApiCommonBase {

    /** Order Number is not entered. */
    private static final String NWZM0002E = "NWZM0002E";

    /** "Global Company Code" is not set. */
    private static final String NWZM0473E = "NWZM0473E";

    // 2016/10/13 S21_NA#14974 ADD START
    /** Message ID : A system error occurred. Please contact your system administrator. */
    private static final String NWZM0474E = "NWZM0474E";

    /** Profitability Apvl Simulate Workflow ID */
    private static final String PROFIT_SIMULATE_WFID = "NWWP0009";

    /** Auto Approve Parameter */
    private static final String WF_AUTO_APVL_PARM_NEXT2 = "Next2";

    /** S21Logger */
    private S21Logger logger = S21LoggerFactory.getLogger(NWZC159001.class);
    // 2016/10/13 S21_NA#14974 ADD END

    // S21_NA#11920 START
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;
    // S21_NA#11920 END

    /**
     * Constructor
     */
    public NWZC159001() {
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

        // 2016/10/13 S21_NA#14974 MOD START
        // doProcess(param, msgMap, onBatchType);
        try {
            doProcess(param, msgMap, onBatchType);
        } catch (S21NwfException e) {
            if ((e.getMessageInfo() != null) && (ZYPCommonFunc.hasValue(e.getMessageInfo().getCode()))) {
                msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                msgMap.addXxMsgId(NWZM0474E);
            }
            logger.error("Workflow Simulate Error", e);
        }
        // 2016/10/13 S21_NA#14974 MOD END

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
     * @throws S21NwfException 
     */
    private void doProcess(NWXC005001ValidationBean param, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) throws S21NwfException {

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!checkInput(msgMap, inMsg, onBatchType)) {
            return;
        }

        // S21_NA#11920 START
        if (isAllLineCredit(inMsg)) {
            return;
        }
        // S21_NA#11920 END

        if (!checkProcDfn(param, inMsg)) {
            return;
        }

        if (existProfitabilityHold(inMsg)) {
            return;
        }

        // 2016/10/06 S21_NA#14974 UPDATE START
        // // 2016/09/21 S21_NA#12194 ADD START
        // if (isAllLineNotPrft(inMsg)) {
        // return;
        // }
        // // 2016/09/21 S21_NA#12194 ADD END
        List<Map<String, Object>> mapResList = getAllLineNotPrft(inMsg);
        if (mapResList.isEmpty()) {
            return;
        }
        // 2016/10/06 S21_NA#14974 UPDATE E N D

        // 2016/10/06 S21_NA#14974 ADD START
        if (chkPrftHoldApprove(inMsg, mapResList)) {
            return;
        }
        // 2016/10/06 S21_NA#14974 ADD E N D

        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum_O, inMsg.cpoOrdNum_I);
        ZYPEZDItemValueSetter.setValue(inMsg.hldRsnCd, HLD_RSN.PROFITABILITY_HOLD);
    }

    /**
     * Check input parameter.
     * @param msgMap S21ApiMessageMap
     * @param inMsg NWXC005001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return Check Result : normal is true
     */
    private boolean checkInput(S21ApiMessageMap msgMap, NWXC005001PMsg inMsg, ONBATCH_TYPE onBatchType) {

        if (!ZYPCommonFunc.hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(inMsg.cpoOrdNum_I)) {
            msgMap.addXxMsgId(NWZM0002E);
            return false;
        }

        return true;
    }

    /**
     * Check process definition.
     * @param param NWXC005001 Validation Bean
     * @param inMsg NWXC005001PMsg
     * @return If Profitability Approval Node Usage Flag is 'Y', return true.
     */
    private boolean checkProcDfn(NWXC005001ValidationBean param, NWXC005001PMsg inMsg) {

        DS_ORD_TP_PROC_DFNTMsg procDfTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(procDfTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(procDfTMsg.dsOrdTpCd, param.getCTMsg().dsOrdTpCd.getValue());

        procDfTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(procDfTMsg);

        if (procDfTMsg != null) {
            String prftApvlNodeUsgFlg = procDfTMsg.prftApvlNodeUsgFlg.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(prftApvlNodeUsgFlg)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check exist Profitability Hold.
     * @param inMsg NWXC005001PMsg
     * @return If profitability hold exist, return true.
     */
    private boolean existProfitabilityHold(NWXC005001PMsg inMsg) {

        final HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("018");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", inMsg.cpoOrdNum_I.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.PROFITABILITY_HOLD);

        return S21ApiTBLAccessor.count(condition) > 0;
    }

    // S21_NA#11920 START
    /**
     * Check credit of all line.
     * @param inMsg NWXC005001PMsg
     * @return If all line is credit, return true.
     */
    @SuppressWarnings("unchecked")
    private boolean isAllLineCredit(NWXC005001PMsg inMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());
        ssmParam.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        ssmParam.put("rtrnLineStsCancel", RTRN_LINE_STS.CANCELLED);
        List<Map<String, String>> getDsCpoDtlDsRtrnDtlList = ssmClient.queryObjectList("getDsCpoDtlDsRtrnDtlList", ssmParam);

        if (getDsCpoDtlDsRtrnDtlList == null || getDsCpoDtlDsRtrnDtlList.size() == 0) {
            return false;
        }

        boolean allLineCreditFlg = true;
        for (Map<String, String> dsCpoDtlDsRtrnDtl : getDsCpoDtlDsRtrnDtlList) {
            if (!CR_REBIL.CREDIT.equals(dsCpoDtlDsRtrnDtl.get("CR_REBIL_CD"))) {
                allLineCreditFlg = false;
                break;
            }
        }

        if (allLineCreditFlg) {
            return true;
        }

        return false;
    }
    // S21_NA#11920 END

    // 2016/10/06 S21_NA#14974 DEL START
    // // 2016/09/21 S21_NA#12194 ADD START
    // /**
    // * Check profitability of all line.
    // * @param inMsg NWXC005001PMsg
    // * @return If all line is not profitability, return true.
    // */
    // private boolean isAllLineNotPrft(NWXC005001PMsg inMsg) {
    // Map<String, String> ssmParam = new HashMap<String, String>();
    // ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
    // ssmParam.put("trxHdrNum", inMsg.cpoOrdNum_I.getValue());
    // ssmParam.put("outBound", CONFIG_CATG.OUTBOUND);
    // ssmParam.put("inBound", CONFIG_CATG.INBOUND);
    // ssmParam.put("ordPrftTrxCatgOutBound",
    // ORD_PRFT_TRX_CATG.OUTBOUND);
    // ssmParam.put("ordPrftTrxCatgInBound",
    // ORD_PRFT_TRX_CATG.INBOUND);
    // ssmParam.put("slsDt",
    // ZYPDateUtil.getSalesDate(inMsg.glblCmpyCd.getValue()));
    // List<String> getPrftApvlLineDataList =
    // ssmClient.queryObjectList("getPrftApvlLineData", ssmParam);
    //
    // if (getPrftApvlLineDataList == null ||
    // getPrftApvlLineDataList.size() <= 0) {
    // return true;
    // }
    //
    // return false;
    // }
    // // 2016/09/21 S21_NA#12194 ADD END
    // 2016/10/06 S21_NA#14974 DEL E N D

    // 2016/10/06 S21_NA#14974 ADD START
    /**
     * get profitability of all line.
     * @param inMsg NWXC005001PMsg
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getAllLineNotPrft(NWXC005001PMsg inMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", inMsg.cpoOrdNum_I.getValue());
        ssmParam.put("outBound", CONFIG_CATG.OUTBOUND);
        ssmParam.put("inBound", CONFIG_CATG.INBOUND);
        ssmParam.put("ordPrftTrxCatgOutBound", ORD_PRFT_TRX_CATG.OUTBOUND);
        ssmParam.put("ordPrftTrxCatgInBound", ORD_PRFT_TRX_CATG.INBOUND);
        ssmParam.put("slsDt", inMsg.slsDt.getValue());

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getPrftApvlLineData", ssmParam);
    }

    /**
     * check profitability Hold reject or approve
     * @param inMsg NWXC005001PMsg
     * @param mapResList List<Map<String, Object>>
     * @return boolean approve return true, reject return false
     * @throws S21NwfException
     */
    private boolean chkPrftHoldApprove(NWXC005001PMsg inMsg, List<Map<String, Object>> mapResList) throws S21NwfException {

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        paramMap.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());

        Map<String, Object> mapResHdr = getSsmQueryObject("getPrftApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        // call profitability Calculation API
        NWZC156001PMsg prftApiPMsg = new NWZC156001PMsg();
        if (!callPrftCalcApi(inMsg, prftApiPMsg, mapResHdr, mapResList)) {
            return false;
        }

        // Order Header Workflow Rejection And Approval
        if (!isAutoApvl(inMsg, prftApiPMsg, mapResHdr)) { // 2017/04/13 S21_#18207 Mod
            return false;
        }

        return true;
    }

    /**
     * Call profitability Calculation API
     * @param inMsg
     * @param prftApiPMsg
     * @param mapResHdr
     * @param mapResList
     * @return
     */
    private boolean callPrftCalcApi(NWXC005001PMsg inMsg, NWZC156001PMsg prftApiPMsg, Map<String, Object> mapResHdr, List<Map<String, Object>> mapResList) {

        ZYPEZDItemValueSetter.setValue(prftApiPMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.slsDt, inMsg.slsDt.getValue());
        // 2017/04/13 S21_NA#18207 Mod Start
        //ZYPEZDItemValueSetter.setValue(prftApiPMsg.xxModeCd, NWZC156001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.xxModeCd, NWZC156001Constant.MODE_ONLINE);
        // 2017/04/13 S21_NA#18207 Mod End
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.trxHdrNum, inMsg.cpoOrdNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsOrdCatgCd, (String) mapResHdr.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsOrdTpCd, (String) mapResHdr.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsOrdRsnCd, (String) mapResHdr.get("DS_ORD_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.prcBaseDt, (String) mapResHdr.get("PRC_BASE_DT"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.prcCalcDt, (String) mapResHdr.get("PRC_CALC_DT"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.funcNegoDealAmt, (BigDecimal) mapResHdr.get("NEGO_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.csmpContrNum, (String) mapResHdr.get("CSMP_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dlrRefNum, (String) mapResHdr.get("DLR_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsAcctNum, (String) mapResHdr.get("SELL_TO_CUST_CD"));

        NWZC156001_svcConfigRefPMsg svcConfigRefPMsg = null;
        int cnt = 0;
        for (Map<String, Object> mapResLine : mapResList) {
            svcConfigRefPMsg = prftApiPMsg.svcConfigRef.no(cnt++);
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordPrftTrxCatgCd, (String) mapResLine.get("CONFIG_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxLineNum, (String) mapResLine.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxLineSubNum, (String) mapResLine.get("TRX_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsOrdPosnNum, (String) mapResLine.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsCpoLineNum, (BigDecimal) mapResLine.get("DS_CPO_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsCpoLineSubNum, (BigDecimal) mapResLine.get("DS_CPO_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.mdseCd, (String) mapResLine.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.rtlWhCd, (String) mapResLine.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.rtlSwhCd, (String) mapResLine.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsOrdLineCatgCd, (String) mapResLine.get("DS_ORD_LINE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordQty, (BigDecimal) mapResLine.get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.custUomCd, (String) mapResLine.get("CUST_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxRefLineNum, (String) mapResLine.get("REF_CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxRefLineSubNum, (String) mapResLine.get("REF_CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.flPrcListCd, (String) mapResLine.get("FL_PRC_LIST_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.csmpPrcListCd, (String) mapResLine.get("CSMP_PRC_LIST_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.prcCatgCd, (String) mapResLine.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcManFlAdjAmt, (BigDecimal) mapResLine.get("FUNC_MAN_FL_ADJ_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcUnitListPrcAmt, (BigDecimal) mapResLine.get("FUNC_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcNetUnitPrcAmt, (BigDecimal) mapResLine.get("FUNC_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcNetSellPrcAmt, (BigDecimal) mapResLine.get("FUNC_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcSvcCostTrnsfAmt, (BigDecimal) mapResLine.get("FUNC_SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcManRepRevAdjAmt, (BigDecimal) mapResLine.get("FUNC_MAN_REP_REV_ADJ_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcSvcRevTrnsfAmt, (BigDecimal) mapResLine.get("FUNC_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.cpoDtlFuncSlsAmt, (BigDecimal) mapResLine.get("CPO_DTL_FUNC_SLS_AMT"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordCustUomQty, (BigDecimal) mapResLine.get("ORD_CUST_UOM_QTY"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.csmpContrNum, (String) mapResLine.get("DTL_CSMP_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dlrRefNum, (String) mapResLine.get("DTL_DLR_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordLineStsCd, (String) mapResLine.get("ORD_LINE_STS_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.billToCustCd, (String) mapResLine.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.shipToCustCd, (String) mapResLine.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcUnitStdCostAmt, (BigDecimal) mapResLine.get("THIS_MTH_TOT_STD_COST_AMT"));
        }
        prftApiPMsg.svcConfigRef.setValidCount(cnt);

        List<NWZC156002PMsg> prftApiPMsg2List = new ArrayList<NWZC156002PMsg>();
        NWZC156001 prftCalcApi = new NWZC156001();
        prftCalcApi.execute(prftApiPMsg, prftApiPMsg2List, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prftApiPMsg)) {
            return false;
        }

        for (NWZC156002PMsg prftApiPMsg2 : prftApiPMsg2List) {
            if (S21ApiUtil.isXxMsgId(prftApiPMsg2)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSsmQueryObject(String sqlId, Map<String, Object> paramMap) {

        Map<String, Object> mapRes = (Map<String, Object>) ssmClient.queryObject(sqlId, paramMap);

        if (mapRes == null) {
            // System Error
            throw new S21AbendException(NWZC164001Constant.NWZM0474E);
        }

        return mapRes;
    }
    // 2016/10/06 S21_NA#14974 ADD E N D

    // 2016/10/13 S21_NA#14974 ADD START
    /**
     * 
     * @param inMsg
     * @param mapResHdr
     * @return
     * @throws S21NwfException
     */
    private boolean isAutoApvl(NWXC005001PMsg inMsg, NWZC156001PMsg prftApiPMsg, Map<String, Object> mapResHdr) throws S21NwfException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        paramMap.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());

        Map<String, Object> mapOrdProcNodePrfl = getSsmQueryObject("getOrdProcNodePrfl", paramMap);

        if (mapOrdProcNodePrfl.isEmpty()) {
            return false;
        }

        String ordProcNodePrfl = (String) mapOrdProcNodePrfl.get("ORD_PROC_NODE_PRFL_NM_PA");

        if (!ZYPCommonFunc.hasValue(ordProcNodePrfl)) {
            return false;
        }

        // 2017/04/13 S21_NA#18207 Mod Start
        //paramMap = new HashMap<String, Object>();
        //paramMap.put("glblCmpyCd", inMsg.glblCmpyCd);
        //paramMap.put("trxHdrNum", inMsg.cpoOrdNum_I);
        //Map<String, Object> mapOrdPrft = getSsmQueryObject("getOrdPrft", paramMap);

        if (prftApiPMsg == null) {
            return false;
        }

        if (prftApiPMsg.altGrsPrftPct == null) {
            return false;
        }

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();

        if (BigDecimal.ZERO.compareTo(prftApiPMsg.altGrsPrftPct.getValue()) != 0) {
            tokenBiz.setCondNum1(prftApiPMsg.altGrsPrftPct.getValue());
        } else {
            tokenBiz.setCondNum1(prftApiPMsg.grsPrftPct.getValue());
        }

        tokenBiz.setBizId(PROFIT_SIMULATE_WFID);
        tokenBiz.setCondStr1(ordProcNodePrfl);
        tokenBiz.setCondStr2((String) mapResHdr.get("DS_ORD_CATG_NM"));
        tokenBiz.setCondStr3((String) mapResHdr.get("DS_ORD_TP_NM"));
        tokenBiz.setCondStr4((String) mapResHdr.get("COA_EXTN_CD"));
        tokenBiz.setCondStr5((String) mapResHdr.get("COA_BR_CD"));
        // 2017/04/13 S21_#18207 Mod End

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        context = factory.getContex();
        process = context.newSimulateProcess(PROFIT_SIMULATE_WFID);
        process.setDocumentId(inMsg.cpoOrdNum_I.getValue());

        S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();
        token.setTokenObj(tokenBiz);
        tokenBiz.setBizScreenId(NWZC164001Constant.CONST_ORDER_ENTRY_SCREEN_ID);
        tokenBiz.setBizScreenParams(inMsg.cpoOrdNum_I);

        NWZC164001TokenObject result = (NWZC164001TokenObject) token.simulate();

        if (WF_AUTO_APVL_PARM_NEXT2.equals(result.getCondStr9())) {
            return true;
        }

        return false;
    }
    // 2016/10/13 S21_NA#14974 ADD END
}
