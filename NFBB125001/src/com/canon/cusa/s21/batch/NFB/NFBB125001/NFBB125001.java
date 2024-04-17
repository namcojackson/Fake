package com.canon.cusa.s21.batch.NFB.NFBB125001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.parts.NFBCommonBusiness;
import business.parts.NLZC309001PMsg;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

import com.canon.cusa.s21.api.NLZ.NLZC309001.NLZC309001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * 3rd Party FM (PO) 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CUSA            Y.Aikawa        Create          N/A
 * 11/07/2017   Hitachi         Y.Takeno        Update          QC#21849(Sol#218)
 * 2018/01/22   Hitachi         J.Kim           Update          QC#22056
 * 2018/04/10   Hitachi         J.Kim           Update          QC#25381
 * </pre>
 */
public class NFBB125001 extends S21BatchMain implements NFBB125001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** CM_PROC_DT */
    private String cmProcDt;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Total Commit Count */
    private int totalCommitCount;

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB125001().executeBatch(NFBB125001.class.getSimpleName());
    }
    @Override
    protected void initRoutine() {
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Get CM_PROC_DT */
        this.cmProcDt = NFBCommonBusiness.getCmProcDt(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cmProcDt)) {
            throw new S21AbendException(NFBM0028E);
        }
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
    }

    @Override
    protected void mainRoutine() {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("apInvCatgCd_06", AP_INV_CATG_CD_06);
        queryParam.put("acctInvStsCd_20", ACCT_INV_STS_CD_20);
        // START 2018/01/22 J.Kim [QC#22056,MOD]
        // queryParam.put("cmDefAcctCd", CM_DEF_ACCT.ASSET_COA);
        queryParam.put("cmDefAcctCdAsset", CM_DEF_ACCT.ASSET_COA);
        queryParam.put("cmDefAcctCdFreight", CM_DEF_ACCT.FREIGHT_COA);
        queryParam.put("cmDefAcctCdTax", CM_DEF_ACCT.TAX_COA);
        queryParam.put("apLineTp", AP_LINE_TP.ITEM);
        // END 2018/01/22 J.Kim [QC#22056,MOD]
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_RECORD, queryParam, new SelectRecordHandler());
        if (bRet == Boolean.TRUE) {
            commit();
        } else {
            rollback();
        }
    }
    @Override
    protected void termRoutine() {
        /** Normal End this process */
        setTermState(TERM_CD.NORMAL_END, totalCommitCount, 0, 0);
    }

    /**
     * Private class: SelectRecordHandler
     */
    private class SelectRecordHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                // Link expense assets registered today to Assets System.
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(MDSE_CD, rs.getString(MDSE_CD));
                paramMap.put(SHIP_TO_CUST_ACCT_CD, rs.getString(SHIP_TO_CUST_ACCT_CD));
                paramMap.put(AP_VND_INV_NUM, rs.getString(AP_VND_INV_NUM));
                paramMap.put(INV_DT, rs.getString(INV_DT));
                paramMap.put(AP_VND_INV_SQ_NUM, rs.getString(AP_VND_INV_SQ_NUM));
                // START 2018/04/10 J.Kim [QC#25381,ADD]
                paramMap.put(AP_VND_INV_LINE_NUM, rs.getString(AP_VND_INV_LINE_NUM));
                // END 2018/04/10 J.Kim [QC#25381,ADD]
                paramMap.put(STD_COST_AMT, rs.getBigDecimal(STD_COST_AMT));
                paramMap.put(TOT_ASSET_QTY, rs.getBigDecimal(TOT_ASSET_QTY));

                String msgId = callAssetStagingEntryApi(paramMap);
                if (msgId != null) {
                    throw new S21AbendException(msgId);
                }
                totalCommitCount++;
            }
            return Boolean.TRUE;
        }
    }

    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.totalCommitCount = 0;
    }

    private String callAssetStagingEntryApi(Map<String, Object> paramMap) {

        NLZC309001PMsg pMsg = new NLZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cmProcDt);
        ZYPEZDItemValueSetter.setValue(pMsg.procModeCd, PROC_MODE_CD_51);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String)paramMap.get(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, (String)paramMap.get(SHIP_TO_CUST_ACCT_CD));
        // START 11/07/2017 [QC#21849, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, (String)paramMap.get(SHIP_TO_CUST_ACCT_CD));
        // END   11/07/2017 [QC#21849, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.invNum, (String)paramMap.get(AP_VND_INV_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.invDt, (String)paramMap.get(INV_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.apVndInvSqNum, (String)paramMap.get(AP_VND_INV_SQ_NUM));
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.apVndInvLineNum, (String) paramMap.get(AP_VND_INV_LINE_NUM));
        // END 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.stdCostAmt, (BigDecimal)paramMap.get(STD_COST_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.totAssetQty, (BigDecimal)paramMap.get(TOT_ASSET_QTY));

        NLZC309001 apiAssetStagingEntry = new NLZC309001();
        apiAssetStagingEntry.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            StringBuilder sbMsg = new StringBuilder();

            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (!pMsg.xxMsgIdList.no(i).xxMsgId.isClear()) {
//                    sbMsg.append(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                }
            }
        }
        return null;
    }
}