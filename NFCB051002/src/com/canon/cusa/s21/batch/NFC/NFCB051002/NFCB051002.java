/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB051002;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_RCPT_INTFC_BAKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * NFCB051002.
 * Create Backup For AR Receive Receipt Interface File For eCheck
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NFCB051002 extends S21BatchMain {

    /** Common component for Database accessing */
    private S21SsmBatchClient ssmBatchClient;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient;

    /** Common component for Integration management record creation */
    private S21TransactionTableAccessor s21TranTblAccs;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String intfcId;

    /** Batch Process Date */
    private String batchProcDt;

    /** Parameter File Name */
    private String prmFlNm;

    /** Lock Box File Name */
    private String batRcptNm;

    /** Normal Record Count */
    private int normRecCnt = 0;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** First Number */
    private static final String FIRST_NUM = "_01";

    /** Underscore */
    private static final String STR_UNDERSCORE = "_";

    /** Percent */
    private static final String STR_PERCENT = "%";

    /** Digit Check Pattern */
    private static final String CHK_PTTERN = "^[0-9]*$";

    /** message of [@] is mandatory. */
    private static final String ZZZM9025E = "ZZZM9025E";

    /**
     * It is the main method that is called from the batch shell.
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB051002().executeBatch(NFCB051002.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(NFCB051002.class);

        this.s21TranTblAccs = new S21TransactionTableAccessor();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.glblCmpyCd = this.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.intfcId = super.getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Interface ID" });
        }

        this.batchProcDt = ZYPDateUtil.getBatProcDate();
        if (!ZYPCommonFunc.hasValue(batchProcDt)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Batch Process Date" });
        }

        this.prmFlNm = S21BatchMain.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.prmFlNm)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Batch Receipt Name" });
        }
    }

    @Override
    protected void mainRoutine() {

        final BigDecimal[] tranIdAry = this.s21TranTblAccs.getIntegrationRecord(this.intfcId);

        for (BigDecimal tranId : tranIdAry) {

            // Create Batch Receipt Name
            this.batRcptNm = createBatReceiptName();

            insertArRcptIntfcBak(tranId);

            this.s21TranTblAccs.endIntegrationProcess(this.intfcId, tranId);

            super.commit();
        }
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.normRecCnt, 0);
    }

    private String createBatReceiptName() {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("batRcptNm", this.prmFlNm + this.batchProcDt + STR_PERCENT);

        @SuppressWarnings("unchecked")
        List<String> queryObjectList = (List<String>) ssmBatchClient.queryObjectList("getBatReceiptName", ssmParam);
        return createBatReceiptName(queryObjectList);
    }

    private String createBatReceiptName(List<String> valList) {
        if (valList.isEmpty()) {
            return this.prmFlNm + this.batchProcDt + FIRST_NUM;
        }

        int maxIndex = 0;
        String[] arBatReceiptNameArray;
        String index = "";
        String existFileNm = "";
        Pattern p = Pattern.compile(CHK_PTTERN);
        for (String arBatReceiptName : valList) {
            arBatReceiptNameArray = arBatReceiptName.split(STR_UNDERSCORE);
            index = arBatReceiptNameArray[arBatReceiptNameArray.length - 1];
            if (p.matcher(index).find()) {
                if (maxIndex < Integer.valueOf(index)) {
                    maxIndex = Integer.valueOf(index);
                }
                if (!ZYPCommonFunc.hasValue(existFileNm)) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < arBatReceiptNameArray.length - 1; i++) {
                        if (i != 0) {
                            str.append(STR_UNDERSCORE);
                        }
                        str.append(arBatReceiptNameArray[i]);
                    }
                    existFileNm = str.toString();
                }
            } else {
                existFileNm = arBatReceiptName;
            }
        }

        if (maxIndex == 0) {
            return existFileNm + FIRST_NUM;
        } else {
            maxIndex++;
            return existFileNm + STR_UNDERSCORE + ZYPCommonFunc.leftPad(String.valueOf(maxIndex), 2, BigDecimal.ZERO.toString());
        }

    }

    private void insertArRcptIntfcBak(BigDecimal tranId) {
        PreparedStatement nfci1120Stmt = null;
        ResultSet nfci1120Rs = null;
        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("intfcId", this.intfcId);
            ssmParam.put("tranId", tranId);

            nfci1120Stmt = this.ssmLLClient.createPreparedStatement("getInterface", ssmParam);
            nfci1120Rs = nfci1120Stmt.executeQuery();
            while (nfci1120Rs.next()) {
                AR_RCPT_INTFC_BAKTMsg tMsg = new AR_RCPT_INTFC_BAKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.arRcptIntfcBakPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_INTFC_BAK_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, nfci1120Rs.getString("INTERFACE_ID"));
                ZYPEZDItemValueSetter.setValue(tMsg.transactionId, nfci1120Rs.getBigDecimal("TRANSACTION_ID"));
                ZYPEZDItemValueSetter.setValue(tMsg.segmentId, nfci1120Rs.getBigDecimal("SEGMENT_ID"));
                ZYPEZDItemValueSetter.setValue(tMsg.unitId, nfci1120Rs.getBigDecimal("UNIT_ID"));
                ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, nfci1120Rs.getBigDecimal("SEQ_NUMBER"));
                ZYPEZDItemValueSetter.setValue(tMsg.arRcptIntfcRecTxt, nfci1120Rs.getString("AR_RCPT_INTFC_REC_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.arBatRcptNm, this.batRcptNm);

                EZDTBLAccessor.insert(tMsg);
                normRecCnt++;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(nfci1120Stmt, nfci1120Rs);
        }

    }

}
