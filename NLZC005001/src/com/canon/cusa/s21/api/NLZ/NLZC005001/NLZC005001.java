/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC005001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;
import business.db.MDSETMsg;
import business.parts.NLZC005001PMsg;

import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistoryBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HRCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 *<pre>
 * Inventory Adjustment Approval to WF API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/12/2018   CITS            T.Tokutomi      Create          QC#18380
 * 05/28/2018   CITS            K.Ogino         Update          QC#26350,QC#26261
 * 12/23/2019   CITS            K.Ogino         Update          QC#55187
 * 01/28/2020   Fujitsu         T.Ogura         Update          QC#55569
 * 05/21/2020   CITS            M.Furugoori     Update          QC#56549
 * 06/04/2020   CITS            M.Furugoori     Update          QC#56720
 *</pre>
 */
public class NLZC005001 extends S21ApiCommonBase {

    /** Work flow ID */
    public static final String WF_ID = "NLZC00500101";

    /** Date Format */
    private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** PSN_CD_SIZE */
    private static final int PSN_CD_SIZE = 8;

    // START 2020/05/19 [QC#56549,MOD]
    /** PSN_CD_SIZE */
    private static final String SYSTEM = "SYSTEM";
    // END 2020/05/19 [QC#56549,MOD]

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient;

    /** run user */
    private String runUserID;

    /**
     * Constructor
     */
    public NLZC005001() {
        super();
    }

    /**
     * execute Inventory Adjustment Order workflow.
     * @param pMsg NLZC005001PMsg
     * @param onBtchType ONBATCH_TYPE
     */
    public void execute(final NLZC005001PMsg pMsg, final ONBATCH_TYPE onBtchType) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onBatchType = onBtchType;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        if (checkMandatory(pMsg)) {
            // Error
            return;
        }

        // Create tokenBizObject
        NLZC005001TokenBizObject token = createToken(pMsg);

        // Update Pmsg
        this.msgMap.flush();
        if (S21ApiUtil.isXxMsgId(pMsg) //
                || token == null) {
            // Master Error.
            return;
        }

        startWorkflow(token);

        this.msgMap.flush();
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // Work flow Error
            return;
        }

        createApprovalHistory(pMsg);

        this.msgMap.flush();

        // End

    }

    private boolean checkMandatory(NLZC005001PMsg pMsg) {

        boolean hasError = false;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            // Global Company Code is not set.
            this.msgMap.addXxMsgId("NLZM2259E");
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            // Sales Date is not set.
            this.msgMap.addXxMsgId("NLZM2079E");
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.invtyOrdNum)) {
            // Inventory Order Number is not entered.
            this.msgMap.addXxMsgId("NLZM0048E");
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.apvlHistSrcTpCd)) {
            // Input Parameter "Approval history Source Type Code" is
            // a mandatory field.
            this.msgMap.addXxMsgId("NPZM0211E");

            hasError = true;
        }

        return hasError;
    }

    private NLZC005001TokenBizObject createToken(NLZC005001PMsg pMsg) {

        // START 2020/05/21 [QC#56549,MOD]
//        String rqstPsnNm = "";
//        if (ZYPCommonFunc.hasValue(pMsg.rqstUpdByPsnCd)) {
//            rqstPsnNm = getPersonName(pMsg.glblCmpyCd.getValue(), pMsg.rqstUpdByPsnCd.getValue());
//        } else {
//            this.runUserID = EZDDBCICarrier.getUserID();
//            if (runUserID.length() > PSN_CD_SIZE) {
//                this.runUserID = this.runUserID.substring(0, PSN_CD_SIZE);
//            }
//
//            rqstPsnNm = getPersonName(pMsg.glblCmpyCd.getValue(), this.runUserID);
//        }
        // END 2020/05/21 [QC#56549,MOD]

        Map<String, Object> invtyHedInfo = getInvtyOrdHeaderInfo(pMsg.glblCmpyCd.getValue(), pMsg.invtyOrdNum.getValue());

        List<Map<String, Object>> invtyDtlInfo = getInvtyOrdDtlInfo(pMsg.glblCmpyCd.getValue(), pMsg.invtyOrdNum.getValue());

        // Check Inventory Order
        if (invtyHedInfo == null //
                || (invtyDtlInfo == null || invtyDtlInfo.isEmpty())) {
            // Error: Entered "Inventory Order Number" does not exist
            // in INVTY_ORD.
            this.msgMap.addXxMsgId("NLCM0023E");
            return null;
        }

        BigDecimal amount = sum(pMsg.glblCmpyCd.getValue(), invtyDtlInfo, (String) invtyHedInfo.get("INVTY_ORD_TP_CD"));

        // Calculation Error.
        // Update Pmsg.
        this.msgMap.flush();
        if (S21ApiUtil.isXxMsgId(msgMap.getPmsg())) {
            return null;
        }

        NLZC005001TokenBizObject token = new NLZC005001TokenBizObject();

        token.setBizId(WF_ID);
        token.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());

        token.setAttribute1Lbl("Inventory Adjustment#");
        token.setAttribute1(pMsg.invtyOrdNum.getValue());

        // START 2020/05/21 [QC#56549,MOD]
//        token.setAttribute2Lbl("Requester");
//        if (ZYPCommonFunc.hasValue(pMsg.rqstUpdByPsnCd)) {
//            token.setAttribute2(ZYPCommonFunc.concatString(pMsg.rqstUpdByPsnCd.getValue(), " ", rqstPsnNm));
//        } else {
//            if (ZYPCommonFunc.hasValue(rqstPsnNm)) {
//                token.setAttribute2(ZYPCommonFunc.concatString(this.runUserID, " ", rqstPsnNm));
//            } else {
//                token.setAttribute2(this.runUserID);
//            }
//
//        }
        // END 2020/05/21 [QC#56549,MOD]

        token.setAttribute3Lbl("Total Amount");
        token.setAttribute3(amount.toPlainString());

        token.setAttribute4Lbl("Inventory Adjustmet Type");
        token.setAttribute4((String) invtyHedInfo.get("INVTY_ORD_TP_DESC_TXT"));

        token.setAttribute5Lbl("Transaction Source Type");
        token.setAttribute5((String) invtyHedInfo.get("TRX_SRC_TP_DESC_TXT"));

        // START 01/28/2020 T.Ogura [QC#55569,ADD]
        token.setCommentsLbl("Comments");
        token.setComments((String) invtyHedInfo.get("INVTY_ORD_LINE_CMNT_TXT"));
        // END   01/28/2020 T.Ogura [QC#55569,ADD]

        token.setBizScreenId("NLCL0290");
        token.setBizScreenParams(pMsg.invtyOrdNum.getValue());


        token.setCondStr1((String) invtyHedInfo.get("INVTY_LOC_CD"));

        // QC#26350
        // START 2020/05/21 [QC#56549,MOD]
//        if (ZYPCommonFunc.hasValue(pMsg.rqstUpdByPsnCd)) {
//            token.setCondStr2(pMsg.rqstUpdByPsnCd.getValue());
//        } else {
//            token.setCondStr2(runUserID);
//        }
        // END 2020/05/21 [QC#56549,MOD]

        token.setCondStr3(APVL_HRCH_TP.POSITIONAL);
        token.setCondNum1(amount);

        // QC#26261. Mod QC#55187 Start
        List<Map<String, Object>> tranMap = getApvlTeamTransactions(token);
        if (tranMap == null || tranMap.isEmpty()) {
            token.setCondStr4(pMsg.apvlHistSrcTpCd.getValue());
        } else if (tranMap.size() == 1) {
            token.setCondStr4((String) tranMap.get(0).get("APVL_HIST_SRC_TP_CD"));
        } else {
            // Multiple
            boolean isParts = false;
            boolean isOther = false;

            for (Map<String, Object> record : invtyDtlInfo) {
                MDSETMsg mdseTMsg = getMdse(token.getGlblCmpyCd(), (String) record.get("MDSE_CD"));
                if (mdseTMsg != null) {
                    if (MDSE_CATG.PARTS.equals(mdseTMsg.mdseCatgCd.getValue())) {
                        isParts = true;
                    } else {
                        isOther = true;
                    }
                }
            }

            if (isOther && isParts) {
                token.setCondStr4(APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_MERCHANDISE);
            } else if (!isOther && isParts){
                token.setCondStr4(APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_PARTS);
            } else {
                token.setCondStr4(APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_MERCHANDISE);
            }

//            if (isParts) {
//                token.setCondStr4(APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_PARTS);
//            } else {
//                token.setCondStr4(APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_MERCHANDISE);
//            }
        }
        // QC#55187 End

        // START 2020/05/21 [QC#56549,MOD]
        String rqstPsnNm = "";
        if (ZYPCommonFunc.hasValue(pMsg.rqstUpdByPsnCd)) {
            rqstPsnNm = getPersonName(pMsg.glblCmpyCd.getValue(), pMsg.rqstUpdByPsnCd.getValue());
        } else {
            this.runUserID = EZDDBCICarrier.getUserID();
            // START 2020/05/19 [QC#56549,ADD]
            String NLGB014001 = ZYPCodeDataUtil.getVarCharConstValue("NLZC0050_ADJ_WMS_BATCH_ID", pMsg.glblCmpyCd.getValue());
            if (NLGB014001.equals(this.runUserID)) {
                BigDecimal limitAmount = getLimitAmount(pMsg.glblCmpyCd.getValue(), SYSTEM, (String) invtyHedInfo.get("INVTY_LOC_CD"), token);
                // START 2020/06/04 [QC#56720,MOD]
//                if (amount.abs().compareTo(limitAmount) > 0) {
                if (ZYPCommonFunc.hasValue(limitAmount) && amount.abs().compareTo(limitAmount) > 0) {
                    this.runUserID = SYSTEM;
                }
                // END 2020/06/04 [QC#56720,MOD]
            }
            // END 2020/05/19 [QC#56549,ADD]
            if (runUserID.length() > PSN_CD_SIZE) {
                this.runUserID = this.runUserID.substring(0, PSN_CD_SIZE);
            }

            rqstPsnNm = getPersonName(pMsg.glblCmpyCd.getValue(), this.runUserID);
        }

        token.setAttribute2Lbl("Requester");
        if (ZYPCommonFunc.hasValue(pMsg.rqstUpdByPsnCd)) {
            token.setAttribute2(ZYPCommonFunc.concatString(pMsg.rqstUpdByPsnCd.getValue(), " ", rqstPsnNm));
        } else {
            if (ZYPCommonFunc.hasValue(rqstPsnNm)) {
                token.setAttribute2(ZYPCommonFunc.concatString(this.runUserID, " ", rqstPsnNm));
            } else {
                token.setAttribute2(this.runUserID);
            }

        }

        if (ZYPCommonFunc.hasValue(pMsg.rqstUpdByPsnCd)) {
            token.setCondStr2(pMsg.rqstUpdByPsnCd.getValue());
        } else {
            token.setCondStr2(runUserID);
        }
        // END 2020/05/21 [QC#56549,MOD]

        // START 2020/06/04 [QC#56720,ADD]
        token.setCondStr9(ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(pMsg.cycleCntRsnCd)) {
            List<String> autoApproveRsnList = new ArrayList<String>();
            String rtlWhAutoApproveRsnCd = token.getCondStr1() + "_AUTO_APPROVE_RSN_CD";
            String autoApproveRsnVal = ZYPCodeDataUtil.getVarCharConstValue(rtlWhAutoApproveRsnCd, pMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(autoApproveRsnVal)) {
                autoApproveRsnList = Arrays.asList(autoApproveRsnVal.split(","));
                if (autoApproveRsnList.contains(pMsg.cycleCntRsnCd.getValue())) {
                    token.setCondStr9(ZYPConstant.FLG_ON_Y);
                }
            }
        }
        // END 2020/06/04 [QC#56720,ADD]

        if (ZYPCommonFunc.hasValue(pMsg.prchGrpCd)) {
            token.setCondStr5(pMsg.prchGrpCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.poMdseTpCd)) {
            token.setCondStr6(pMsg.prchGrpCd.getValue());
        }

        token.setInvtyOrdNum(pMsg.invtyOrdNum.getValue());
        token.setApvlHistSrcTpCd(pMsg.apvlHistSrcTpCd.getValue());

        return token;
    }

    private void startWorkflow(NLZC005001TokenBizObject tokenBiz) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();

            List<S21NwfProcess> processes = context.getProcess(WF_ID, tokenBiz.getInvtyOrdNum());

            for (S21NwfProcess p : processes) {
                if (p.isActive()) {
                    p.getToken().cancel();
                }
            }

            process = context.newProcess(WF_ID);

            S21NwfToken token = process.getToken();
            process.setDocumentId(tokenBiz.getInvtyOrdNum());
            token.setTokenObj(tokenBiz);

            token.start();
        } catch (S21NwfSystemException e) {
            // An error occurred in Creation of Workflow Process.
            this.msgMap.addXxMsgId("NPZM0213E");
        } catch (S21NwfBizException e) {
            this.msgMap.addXxMsgId(e.getMessageInfo().getCode());
        } catch (S21NwfException e) {
            // System error has occurred.
            this.msgMap.addXxMsgId("NFDM0008E");
        }
    }

    private void createApprovalHistory(NLZC005001PMsg pMsg) {
        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();

        inParam.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inParam.setTrxRefNum(pMsg.invtyOrdNum.getValue());
        inParam.setApvlHistInfoTs(ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        inParam.setApvlHistActTpCd(APVL_HIST_ACT_TP.SUBMIT);
        if (ZYPCommonFunc.hasValue(pMsg.rqstUpdByPsnCd)) {
            inParam.setApvlByPsnCd(pMsg.rqstUpdByPsnCd.getValue());
        } else {
            inParam.setApvlByPsnCd(this.runUserID);
        }
        inParam.setApvlHistSrcTpCd(pMsg.apvlHistSrcTpCd.getValue());

        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);

        if (!NPXC001001CreateApprovalHistory.NORMAL.equals(rtrnCd)) {
            // An error occurred in NPXC001001CreateApprovalHistory.
            this.msgMap.addXxMsgId("NPZM0212E");
        }
    }

    private String getPersonName(String glblCmpyCd, String psnCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("psnCd", psnCd);

        return (String) ssmBatchClient.queryObject("getPersonName", param);
    }

    private Map<String, Object> getInvtyOrdHeaderInfo(String glblCmpyCd, String invtyOrdNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("invtyOrdNum", invtyOrdNum);

        return (Map<String, Object>) ssmBatchClient.queryObject("getInvtyOrdHeaderInfo", param);
    }

    private List<Map<String, Object>> getInvtyOrdDtlInfo(String glblCmpyCd, String invtyOrdNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("invtyOrdNum", invtyOrdNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvtyOrdDtlInfo", param);
    }

    private BigDecimal sum(String glblCmpyCd, List<Map<String, Object>> invtyDtlInfo, String invtyOrdTpCd) {

        String absFlg = ZYPCodeDataUtil.getVarCharConstValue("NLZC0050_ABS_CALC_CTRL", glblCmpyCd);

        boolean subWhChange = INVTY_ORD_TP.SUB_WH_CHANGE.equals(invtyOrdTpCd);

        BigDecimal sum = BigDecimal.ZERO;

        if (subWhChange) {
            BigDecimal fromAmt = BigDecimal.ZERO;
            BigDecimal toAmt = BigDecimal.ZERO;
            if (ZYPConstant.FLG_ON_Y.equals(absFlg)) {
                for (Map<String, Object> record : invtyDtlInfo) {
                    NLXC001001GetInventoryItemCostBean fromBean = new NLXC001001GetInventoryItemCostBean();

                    fromBean.setGlblCmpyCd(glblCmpyCd);
                    fromBean.setInvtyLocCd((String) record.get("INVTY_LOC_CD"));
                    fromBean.setMdseCd((String) record.get("MDSE_CD"));
                    fromBean.setQty(((BigDecimal) record.get("ORD_QTY")).abs());

                    NLXC001001GetInventoryItemCostBean toBean = new NLXC001001GetInventoryItemCostBean();

                    toBean.setGlblCmpyCd(glblCmpyCd);
                    toBean.setInvtyLocCd((String) record.get("TO_INVTY_LOC_CD"));
                    toBean.setMdseCd((String) record.get("MDSE_CD"));
                    toBean.setQty(((BigDecimal) record.get("ORD_QTY")).abs());

                    fromBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(fromBean);
                    toBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(toBean);

                    if (!fromBean.getErrList().isEmpty()) {
                        for (String msgId : fromBean.getErrList()) {
                            msgMap.addXxMsgId(msgId);
                        }

                        break;
                    }
                    if (!toBean.getErrList().isEmpty()) {
                        for (String msgId : toBean.getErrList()) {
                            msgMap.addXxMsgId(msgId);
                        }

                        break;
                    }

                    fromAmt = fromAmt.add(fromBean.getTotPrcAmt());
                    toAmt = toAmt.add(toBean.getTotPrcAmt());
                }
            } else {
                for (Map<String, Object> record : invtyDtlInfo) {
                    NLXC001001GetInventoryItemCostBean fromBean = new NLXC001001GetInventoryItemCostBean();

                    fromBean.setGlblCmpyCd(glblCmpyCd);
                    fromBean.setInvtyLocCd((String) record.get("INVTY_LOC_CD"));
                    fromBean.setMdseCd((String) record.get("MDSE_CD"));
                    fromBean.setQty(((BigDecimal) record.get("ORD_QTY")));

                    NLXC001001GetInventoryItemCostBean toBean = new NLXC001001GetInventoryItemCostBean();

                    toBean.setGlblCmpyCd(glblCmpyCd);
                    toBean.setInvtyLocCd((String) record.get("TO_INVTY_LOC_CD"));
                    toBean.setMdseCd((String) record.get("MDSE_CD"));
                    toBean.setQty(((BigDecimal) record.get("ORD_QTY")));

                    fromBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(fromBean);
                    toBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(toBean);

                    if (!fromBean.getErrList().isEmpty()) {
                        for (String msgId : fromBean.getErrList()) {
                            msgMap.addXxMsgId(msgId);
                        }

                        break;
                    }
                    if (!toBean.getErrList().isEmpty()) {
                        for (String msgId : toBean.getErrList()) {
                            msgMap.addXxMsgId(msgId);
                        }

                        break;
                    }

                    fromAmt = fromAmt.add(fromBean.getTotPrcAmt());
                    toAmt = toAmt.add(toBean.getTotPrcAmt());
                }
            }

            sum = toAmt.subtract(fromAmt);

        } else {
            if (ZYPConstant.FLG_ON_Y.equals(absFlg)) {
                for (Map<String, Object> record : invtyDtlInfo) {
                    NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();

                    bean.setGlblCmpyCd(glblCmpyCd);
                    bean.setInvtyLocCd((String) record.get("INVTY_LOC_CD"));
                    bean.setMdseCd((String) record.get("MDSE_CD"));
                    bean.setQty(((BigDecimal) record.get("ORD_QTY")).abs());

                    bean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);

                    if (!bean.getErrList().isEmpty()) {
                        for (String msgId : bean.getErrList()) {
                            msgMap.addXxMsgId(msgId);
                        }

                        break;
                    }

                    sum = sum.add(bean.getTotPrcAmt());
                }
            } else {
                for (Map<String, Object> record : invtyDtlInfo) {
                    NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();

                    bean.setGlblCmpyCd(glblCmpyCd);
                    bean.setInvtyLocCd((String) record.get("INVTY_LOC_CD"));
                    bean.setMdseCd((String) record.get("MDSE_CD"));
                    bean.setQty((BigDecimal) record.get("ORD_QTY"));

                    bean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);

                    if (!bean.getErrList().isEmpty()) {
                        for (String msgId : bean.getErrList()) {
                            msgMap.addXxMsgId(msgId);
                        }

                        break;
                    }

                    sum = sum.add(bean.getTotPrcAmt());
                }
            }
        }

        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private List<Map<String, Object>> getApvlTeamTransactions(NLZC005001TokenBizObject token) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", token.getGlblCmpyCd());
        param.put("apvlHrchTpCd", token.getCondStr3());
        param.put("rtlWhCd", token.getCondStr1());
        param.put("apvlLimitAmt", token.getCondNum1());
        param.put("adjMerchandise", APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_MERCHANDISE);
        param.put("adjParts", APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_PARTS);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getApvlTeamTransactions", param);
    }

    /**
     * Get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);

        return (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
    }

    // START 2020/05/19 [QC#56549,ADD]
    private BigDecimal getLimitAmount(String glblCmpyCd, String system, String invtyLocCd, NLZC005001TokenBizObject token) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("apvlHrchTpCd", APVL_HRCH_TP.POSITIONAL);
        param.put("system", system);
        param.put("rtlWhCd", invtyLocCd);
        param.put("apvlHistSrcTpCd", token.getCondStr4());

        return (BigDecimal) ssmBatchClient.queryObject("getLimitAmount", param);
    }
    // END 2020/05/19 [QC#56549,ADD]
}
