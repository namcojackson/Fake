/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB726001;

import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.CSMP_REC_TP_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.CSMP_TRD_PTNR_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.INV;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.INTFC_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.NWCM0059E;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.NWCM0110E;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.NWCM0144E;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.SEGMENT_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.UNIT_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB726001.constant.NWCB726001Constant.DEFAULT_SER_NUM;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.INVTMsg;
import business.db.NEAI0980_01TMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_CLM_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * CSMP Send Claim Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/21   Fujitsu         H.Ikeda         Create          N/A
 * 2017/02/14   Fujitsu         S.Fujita        Update          QC#17421
 * 2017/06/8    Fujitsu         A.Kosai         Update          QC#18589
 *</pre>
 */

public class NWCB726001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** interface Id */
    private String interfaceId = null;

    /** Transaction ID */
    private BigDecimal transactionId;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** SSM LowLevel Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor tblAccessor = null;

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NWCB726001().executeBatch(NWCB726001.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NWCM0059E, new String[] {GLBL_CMPY_CD});
        }

        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(NWCM0144E, new String[] {INTFC_ID});
        }

        tblAccessor = new S21TransactionTableAccessor();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        int workTotalCount = 0;
        BigDecimal seqNum = BigDecimal.ZERO;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // get Invoice Data
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("csmpInvClmStsCd", CSMP_INV_CLM_STS.UNPROCESSED);

            stmt = this.ssmLLClient.createPreparedStatement("getInvoiceData", ssmParam, execParam);
            rsSet = stmt.executeQuery();


            while (rsSet.next()) {
                if (workTotalCount == 0) {
                    // get TransactionId
                    this.transactionId = tblAccessor.createIntegrationRecord(this.interfaceId);
                }
                // workCount + 1
                workTotalCount += 1;
                // get SerNum
                String serNumStr = selectSerNumStr(ssmParam, rsSet);
                // 2017/06/08 QC#18589 Add Start
                if (!ZYPCommonFunc.hasValue(serNumStr)) {
                    serNumStr = DEFAULT_SER_NUM; 
                }
                // 2017/06/08 QC#18589 Add End
                // sequence
                seqNum = seqNum.add(BigDecimal.ONE);
                if (insertNEAI0980(rsSet, seqNum, serNumStr)) {
                    updateDsInv(rsSet.getString("INV_NUM_CUSA"));
                } else {
                    this.termCd = TERM_CD.ABNORMAL_END;
                }
            }
        } catch (SQLException e) {
            this.errorCount += 1;
            this.termCd = TERM_CD.ABNORMAL_END;
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            this.totalCount = this.totalCount + workTotalCount;
            this.successCount = this.totalCount - this.errorCount;
        }
    }

    private String selectSerNumStr(Map<String, Object> ssmParam, ResultSet rsSet) throws SQLException {
        ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("invNum", rsSet.getString("INV_NUM_CUSA"));
        ssmParam.put("invBolLineNum", rsSet.getString("INV_BOL_LINE_NUM"));
        ssmParam.put("invLineNum", rsSet.getString("INV_LINE_NUM"));
        ssmParam.put("invLineSubNum", rsSet.getString("INV_LINE_SUB_NUM"));
        ssmParam.put("invTrxLineSubNum", rsSet.getString("INV_LINE_SUB_TRX_NUM"));
        ssmParam.put("rowNum", 1);

        return (String) ssmBatchClient.queryObject("getSerNum", ssmParam);
    }

   private boolean insertNEAI0980(ResultSet rsSet, BigDecimal seqNum, String serNumStr) throws SQLException {

       NEAI0980_01TMsg tMsg = new NEAI0980_01TMsg();

       ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.interfaceId);
       ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.transactionId);
       ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID);
       ZYPEZDItemValueSetter.setValue(tMsg.unitId, UNIT_ID);
       ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNum);

       ZYPEZDItemValueSetter.setValue(tMsg.csmpRecTpCd, CSMP_REC_TP_CD);
       ZYPEZDItemValueSetter.setValue(tMsg.csmpTrdPtnrCd, CSMP_TRD_PTNR_CD);
       ZYPEZDItemValueSetter.setValue(tMsg.csmpDlrCd, cutData(rsSet.getString("RTL_DLR_CD"), 7));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpClmInvNum, rsSet.getString("INV_NUM_CUSA"));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpEndCustInvNum, rsSet.getString("INV_NUM_END_CUSTOMER"));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpPoNum, cutData(rsSet.getString("CUST_ISS_PO_NUM"), 30));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpContrIfNum, cutData(rsSet.getString("CSMP_CONTR_NUM"), 12));
       tMsg.csmpInstlDlrCd.clear();
       tMsg.csmpEndCustCd.clear();
       ZYPEZDItemValueSetter.setValue(tMsg.csmpEndCustNm, cutData(rsSet.getString("SELL_TO_LOC_NM"), 60));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpSerNum, serNumStr);
       ZYPEZDItemValueSetter.setValue(tMsg.csmpMdseCd, rsSet.getString("MDSE_CD"));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpShipDt, rsSet.getString("SHIP_DT"));
       // START 2017/02/14 S.Fujita [QC#17421,MOD]
//       ZYPEZDItemValueSetter.setValue(tMsg.csmpAmtCrTxt, cutData(rsSet.getString("INV_TOT_DEAL_NET_AMT"), 15));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpAmtCrTxt, cutData(rsSet.getString("INV_LINE_DEAL_NET_AMT"), 15));
       // END   2017/02/14 S.Fujita [QC#17421,MOD]
       tMsg.csmpCrCd.clear();
       ZYPEZDItemValueSetter.setValue(tMsg.csmpShipToAddr_01, cutData(rsSet.getString("SHIP_TO_FIRST_LINE_ADDR"), 35));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpShipToAddr_02, cutData(rsSet.getString("SHIP_TO_SCD_LINE_ADDR"), 35));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpShipToCtyAddr, rsSet.getString("SHIP_TO_CTY_ADDR"));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpShipToStCd, rsSet.getString("SHIP_TO_ST_CD"));
       ZYPEZDItemValueSetter.setValue(tMsg.csmpShipToPostCd, rsSet.getString("SHIP_TO_POST_CD"));

       S21FastTBLAccessor.insert(tMsg);

       if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
           this.errorCount++;
           super.rollback();
           return false;
       }

       return true;
   }

   private String cutData(String item, int cutCnt) {

       if (ZYPCommonFunc.hasValue(item)) {
           if (item.length() > cutCnt) {
               return item.substring(0, cutCnt);
           } else {
               return item;
           }
       } else {
           return item;
       }
   }

   private void updateDsInv(String invNum) {

       INVTMsg hdrTmsg = new INVTMsg();
       ZYPEZDItemValueSetter.setValue(hdrTmsg.glblCmpyCd, this.glblCmpyCd);
       ZYPEZDItemValueSetter.setValue(hdrTmsg.invNum, invNum);
       hdrTmsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateWait(hdrTmsg);

       ZYPEZDItemValueSetter.setValue(hdrTmsg.csmpInvClmStsCd, CSMP_INV_CLM_STS.WAITING_FOR_PAYMENT);

       EZDTBLAccessor.update(hdrTmsg);
       if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTmsg.getReturnCode())) {
           throw new S21AbendException(NWCM0110E, new String[] {INV});
       }

       commit();
       this.successCount += 1;
   }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);
    }
}
