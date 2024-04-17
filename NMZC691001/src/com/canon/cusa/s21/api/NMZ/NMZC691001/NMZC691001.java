/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC691001;

import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.NMZM0001E;
import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.NMZM0353E;
import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.NMZM0354E;
import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.NMZM0355E;
import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.NMZM0356E;
import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.NMZM0357E;
import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.NMZM0358E;
import static com.canon.cusa.s21.api.NMZ.NMZC691001.constant.NMZC691001Constant.MAX_MSG_LEN;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ACCT_RELNTMsg;
import business.parts.NMZC691001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Customer Relation Create API for MyCSA
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/07   Fujitsu         H.Sugawara      Create          N/A
 * 2017/11/29   Fujitsu         N.Sugiura       Update          QC#22801
 * </pre>
 */
public class NMZC691001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatType = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Sales Date */
    private String slsDt = null;

    /**
     * Constructor
     */
    public NMZC691001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Customer Relation Create API for MyCSA
     * </pre>
     * @param pMsgList List<NMZC691001PMsg>
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(final List<NMZC691001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NMZC691001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * <pre>
     * Customer Relation Create API for MyCSA
     * </pre>
     * @param pMsg NMZC691001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(final NMZC691001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        init(pMsg, onBatTp);
        execute();
        msgMap.flush();
    }

    /**
     * <pre>
     * Execute API
     * </pre>
     */
    private void execute() {

        vldPMsg();
        if (hasErr()) {
            return;
        }

        NMZC691001PMsg pMsg = (NMZC691001PMsg) msgMap.getPmsg();
        createCustomerRelation(pMsg);
    }

    /**
     * <pre>
     * Initialization
     * </pre>
     * @param pMsg NMZC691001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    private void init(NMZC691001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        onBatType = onBatTp;

        slsDt = ZYPDateUtil.getSalesDate();
    }

    /**
     * <pre>
     * Validate PMsg
     * </pre>
     */
    private void vldPMsg() {

        // Checking mandatory value
        vldMandatoryCheck();
        if (hasErr()) {
            return;
        }

        // Checking input value
        vldInputValueCheck();
        if (hasErr()) {
            return;
        }

        // Checking master data
        vldMasterCheck();
        if (hasErr()) {
            return;
        }
    }

    /**
     * <pre>
     * Has error
     * </pre>
     * @return boolean
     */
    private boolean hasErr() {

        NMZC691001PMsg pMsg = (NMZC691001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Validate mandatory parameter
     * </pre>
     */
    private void vldMandatoryCheck() {

        NMZC691001PMsg pMsg = (NMZC691001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NMZM0001E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dsAcctNum)) {
            setErrMsg(pMsg, NMZM0353E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.relnDsAcctNum)) {
            setErrMsg(pMsg, NMZM0354E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dsAcctRelnTpCd)) {
            setErrMsg(pMsg, NMZM0355E);
        }

        return;
    }

    /**
     * <pre>
     * Validate input value
     * </pre>
     */
    private void vldInputValueCheck() {

        NMZC691001PMsg pMsg = (NMZC691001PMsg) msgMap.getPmsg();

        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.dsAcctRelnBillToFlg.getValue()) // 
                && !ZYPConstant.FLG_ON_Y.equals(pMsg.dsAcctRelnShipToFlg.getValue())) {
            setErrMsg(pMsg, NMZM0358E);
        }

        return;
    }

    /**
     * <pre>
     * Validate master data
     * </pre>
     */
    private void vldMasterCheck() {

        Integer cnt = null;
        NMZC691001PMsg pMsg = (NMZC691001PMsg) msgMap.getPmsg();

        // Count Related Account From(dsAcctNum)
        cnt = countSellToCustomer(pMsg.glblCmpyCd.getValue(), pMsg.dsAcctNum.getValue());

        if (cnt.intValue() < 1) {
            setErrMsg(pMsg, NMZM0356E);
        }

        // Count Related Account To(relnDsAcctNum)
        cnt = countSellToCustomer(pMsg.glblCmpyCd.getValue(), pMsg.relnDsAcctNum.getValue());

        if (cnt.intValue() < 1) {
            setErrMsg(pMsg, NMZM0357E);
        }

        return;
    }

    /**
     * <pre>
     * Set error message
     * </pre>
     * @param pMsg NMZC691001PMsg
     * @param msgId String
     */
    private void setErrMsg(NMZC691001PMsg pMsg, String msgId) {
        setErrMsg(pMsg, msgId, null);
    }

    /**
     * <pre>
     * Set error message
     * </pre>
     * @param pMsg NMZC691001PMsg
     * @param msgId String
     * @param msgPrms String[]
     */
    private void setErrMsg(NMZC691001PMsg pMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    /**
     * <pre>
     * Cut message
     * </pre>
     * @param msg String
     * @return String
     */
    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > MAX_MSG_LEN) {
            return msg.substring(0, MAX_MSG_LEN);
        }
        return msg;
    }

    /**
     * <pre>
     * createCustomerRelation
     * </pre>
     * @param param NMZC691001PMsg
     * @return No Error : true
     */
    private boolean createCustomerRelation(NMZC691001PMsg param) {

        if (!DS_ACCT_RELN_TP.MYCSA_ACCOUNT.equals(param.dsAcctRelnTpCd.getValue())) {
            return true;
        }

        // Insert or update normal data
        insertOrUpdateDsAcctReln(param, false);

        if (ZYPConstant.FLG_ON_Y.equals(param.dsAcctRelnRecipFlg.getValue())) {
            // Insert or update reciprocal data
            insertOrUpdateDsAcctReln(param, true);
        }

        return true;
    }

    /**
     * <pre>
     * Insert or update DS_ACCT_RELN table
     * </pre>
     * @param param NMZC691001PMsg
     * @param isRecip boolean(true:reciprocal data)
     */
    private void insertOrUpdateDsAcctReln(NMZC691001PMsg param, boolean isRecip) {

        List<Map<String, Object>> dsAcctRelnList = (List<Map<String, Object>>) getDsAcctRelnList(param, isRecip);

        if (dsAcctRelnList != null && dsAcctRelnList.size() > 0) {
            // Update
            Map<String, Object> dsAcctReln = dsAcctRelnList.get(0);
            BigDecimal dsAcctRelnPk = (BigDecimal) dsAcctReln.get("DS_ACCT_RELN_PK");

            DS_ACCT_RELNTMsg tMsg = new DS_ACCT_RELNTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnPk, dsAcctRelnPk);

            // Record lock for update
            tMsg = (DS_ACCT_RELNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);

            tMsg = getDsAcctRelnTMsgForUpdate(tMsg, param);
            S21ApiTBLAccessor.update(tMsg);
        } else {
            // Insert
            DS_ACCT_RELNTMsg tMsg = getDsAcctRelnTMsgForInsert(param, isRecip);
            S21ApiTBLAccessor.insert(tMsg);
        }
    }

    /**
     * <pre>
     * Get DS_ACCT_RELNTMsg for insert
     * </pre>
     * @param param NMZC691001PMsg
     * @param isRecip boolean(true:reciprocal data)
     * @return DS_ACCT_RELNTMsg
     */
    private DS_ACCT_RELNTMsg getDsAcctRelnTMsgForInsert(NMZC691001PMsg param, boolean isRecip) {

        DS_ACCT_RELNTMsg tMsg = new DS_ACCT_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ACCT_RELN_SQ));

        if (isRecip) {
            // For reciprocal data
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, param.relnDsAcctNum);
            ZYPEZDItemValueSetter.setValue(tMsg.relnDsAcctNum, param.dsAcctNum);
        } else {
            // For normal data
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, param.dsAcctNum);
            ZYPEZDItemValueSetter.setValue(tMsg.relnDsAcctNum, param.relnDsAcctNum);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnTpCd, param.dsAcctRelnTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, slsDt);
// 2017/11/29 QC#22801 Mod Start
//        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnBillToFlg, param.dsAcctRelnBillToFlg);
//        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnShipToFlg, param.dsAcctRelnShipToFlg);
//        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnRecipFlg, param.dsAcctRelnRecipFlg);
        if (ZYPConstant.FLG_ON_Y.equals(param.dsAcctRelnBillToFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnBillToFlg, param.dsAcctRelnBillToFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnBillToFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(param.dsAcctRelnShipToFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnShipToFlg, param.dsAcctRelnShipToFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnShipToFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(param.dsAcctRelnRecipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnRecipFlg, param.dsAcctRelnRecipFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnRecipFlg, ZYPConstant.FLG_OFF_N);
        }
// 2017/11/29 QC#22801 Mod End
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

        return tMsg;
    }

    /**
     * <pre>
     * Get DS_ACCT_RELNTMsg for update
     * </pre>
     * @param param NMZC691001PMsg
     * @return DS_ACCT_RELNTMsg
     */
    private DS_ACCT_RELNTMsg getDsAcctRelnTMsgForUpdate(DS_ACCT_RELNTMsg tMsg, NMZC691001PMsg param) {

        String effFromDt = tMsg.effFromDt.getValue();

        if (ZYPDateUtil.compare(slsDt, effFromDt) < 0) {
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, slsDt);
            ZYPEZDItemValueSetter.setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        }

        if (ZYPConstant.FLG_ON_Y.equals(param.dsAcctRelnBillToFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnBillToFlg, param.dsAcctRelnBillToFlg);
        }
        if (ZYPConstant.FLG_ON_Y.equals(param.dsAcctRelnShipToFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnShipToFlg, param.dsAcctRelnShipToFlg);
        }
        if (ZYPConstant.FLG_ON_Y.equals(param.dsAcctRelnRecipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRelnRecipFlg, param.dsAcctRelnRecipFlg);
        }

        return tMsg;
    }

    /**
     * Count Sell to Customer
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return Integer
     */
    private Integer countSellToCustomer(String glblCmpyCd, String sellToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("sellToCustCd", sellToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Integer) ssmBatchClient.queryObject("countSellToCustomer", ssmParam);
    }

    /**
     * Get Direct Sales Account Relation
     * @param param NMZC691001PMsg
     * @param isRecip boolean(true:reciprocal data)
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getDsAcctRelnList(NMZC691001PMsg param, boolean isRecip) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());

        if (isRecip) {
            // For reciprocal data
            ssmParam.put("relatedAccountFrom", param.relnDsAcctNum.getValue());
            ssmParam.put("relatedAccountTo", param.dsAcctNum.getValue());
        } else {
            // For normal data
            ssmParam.put("relatedAccountFrom", param.dsAcctNum.getValue());
            ssmParam.put("relatedAccountTo", param.relnDsAcctNum.getValue());
        }

        ssmParam.put("relationType", param.dsAcctRelnTpCd.getValue());
        ssmParam.put("rgtnStsCd01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("rgtnStsCd02", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsAcctRelnList", ssmParam);
    }

}
