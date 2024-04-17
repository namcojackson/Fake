/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB240001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.db.NWAI2420_01TMsg;
import business.db.VAR_CHAR_CONSTTMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.internal.calendar.S21DateManagement;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * NWAB240001 Customer Back Order Batch IF Data Creation
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 2013/10/07   Fujitsu         A.Suda          Create          MEX-IF013
 * 2013/10/22   Fujitsu         A.Suda          Update          Defect#126
 *</pre>
 */
public class NWAB240001 extends S21BatchMain {

    // -- Error Message Code --------------------
    /** [@] is mandatory. */
    private static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Failed to register data. Table: [@]. */
    private static final String MSG_ID_NWAM0546E = "NWAM0546E";

    // -- Variavle --------------
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Interface ID */
    private String ifId = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** trxTblAccessor */
    private S21TransactionTableAccessor trxTblAccessor = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Transaction ID */
    private BigDecimal trxId = null;

    /** Total count */
    private int totalCount = 0;

    /** Normal count */
    int normalRecCnt = 0;

    /** Error count */
    int errRecCnt = 0;

    /** Variable Character Const Key (Cinc Global WH Code) */
    private static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD = "SCUBE_IF_CINC_GLBL_WH_CD";

    /**
     * <pre>
     * Initialization routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
     */
    protected void initRoutine() {

        try {
            // Initialization
            termCd = TERM_CD.NORMAL_END;
            trxTblAccessor = new S21TransactionTableAccessor();
            trxId = trxTblAccessor.getNextTransactionId();
            ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            if (!isGlobalCompanyCode()) {
                throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Global Company Code" });
            }
            if (!getBatProcDate()) {
                throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Sales Date" });
            }
            if (!getIfID()) {
                throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Interface ID" });
            }

        } catch (S21AbendException e) {
            S21InfoLogOutput.println(e.getMessage());
            throw e;
        }
    }

    /**
     * <pre>
     * Main routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#mainRoutine()
     */
    protected void mainRoutine() {

        try {
            // Search Work and Insert InterfaceTBL
            executeSearchWorkAndInsert();

            if (normalRecCnt > 0) {
                // Commit(InterfaceTBL)
                commit();
                // Insert TransactionTBL
                executeInsertTrxTBL();
                // Commit(TransactionTBL)
                commit();

                S21InfoLogOutput.println("------------------------------------------");
                S21InfoLogOutput.println("Transaction ID = " + trxId.toString());
                S21InfoLogOutput.println("Interface ID = " + ifId);
                S21InfoLogOutput.println("------------------------------------------");

            }

        } catch (S21AbendException e) {
            S21InfoLogOutput.println(e.getMessage());
            throw e;
        }
    }

    /**
     * <pre>
     * Term routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#termRoutine()
     */
    protected void termRoutine() {

        termCd = TERM_CD.NORMAL_END;
        // Set term code and total commit count.
        setTermState(termCd, normalRecCnt, errRecCnt, totalCount);
    }

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NWAB240001().executeBatch(NWAB240001.class.getSimpleName());

    }

    /**
     * <pre>
     * Execute Search Work and Insert
     * </pre>
     */
    private void executeSearchWorkAndInsert() {

        PreparedStatement stmt = null;
        ResultSet resSet = null;

        String notUseCdForCincGlblWhCd = getVarCharConstValue(VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD);
        try {

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("slsDt", slsDt);

            // Execute Search BackOrder.
            stmt = ssmLLClient.createPreparedStatement("getIntfcBoWrk", param);
            resSet = stmt.executeQuery();

            List<NWAI2420_01TMsg> insList = new ArrayList<NWAI2420_01TMsg>();
            int seqNum = 1;

            while (resSet.next()) {
                NWAI2420_01TMsg dwai2420TMsg = new NWAI2420_01TMsg();

                S21ResultSetMapper.map(resSet, dwai2420TMsg);
                dwai2420TMsg.glblCmpy3Cd.setValue(glblCmpyCd);
                dwai2420TMsg.interfaceId.setValue(ifId);
                dwai2420TMsg.transactionId.setValue(trxId);
                dwai2420TMsg.segmentId.setValue(BigDecimal.ONE);
                dwai2420TMsg.unitId.setValue(BigDecimal.ONE);
                dwai2420TMsg.seqNumber.setValue(seqNum++);
                // Defect#126 Add Start
                String cincGlblWhCd = (String) resSet.getString("CINC_GLBL_WH_CD");
                if (ZYPCommonFunc.hasValue(notUseCdForCincGlblWhCd)) {
                    if (notUseCdForCincGlblWhCd.equals(cincGlblWhCd)) {
                        dwai2420TMsg.cincGlblWhCd.clear();
                    }
                }
                // Defect#126 Add End

                insList.add(dwai2420TMsg);
                totalCount++;

            }

            if (insList.size() > 0) {

                int insListCnt = insList.size();
                int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new NWAI2420_01TMsg[0]));
                if (insListCnt == rsltCnt) {
                    normalRecCnt = totalCount;
                } else {
                    throw new S21AbendException(MSG_ID_NWAM0546E, new String[] {"NWAI2420_01, Key IntfcCratDt :" + slsDt });
                }
            }

        } catch (Exception e) {
            // Insert AbnomalEnd
            // RoleBack
            rollback();
            termCd = TERM_CD.ABNORMAL_END;
            errRecCnt = totalCount;

            if (e instanceof SQLException) {
                sqlExceptionHandler((SQLException) e);
            } else {
                throw (S21AbendException) e;
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, resSet);
        }

    }

    /**
     * <pre>
     * Perform the check that is GLBL_CMPY_CD.
     * </pre>
     * @return true/normal. false/Abnormality.
     */
    private boolean isGlobalCompanyCode() {

        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Acquire an operative day than a global company cord.
     * </pre>
     * @return true/normal. false/Abnormality.
     */
    private boolean getBatProcDate() {

        slsDt = S21DateManagement.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            return false;
        }

        return true;
    }

    private boolean getIfID() {

        ifId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(ifId)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Execute InsertTrxTBL
     * </pre>
     */
    private void executeInsertTrxTBL() {
        // Create interface transaction record.
        trxTblAccessor.createIntegrationRecordForBatch(ifId, trxId);
    }

    /**
     * <pre>
     * Get VarCharConstValue
     * </pre>
     */
    private String getVarCharConstValue(String varCharConstNm) {

        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(varCharConstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(varCharConstTMsg.varCharConstNm, varCharConstNm);

        varCharConstTMsg = (VAR_CHAR_CONSTTMsg) S21CacheTBLAccessor.findByKey(varCharConstTMsg);

        if (null == varCharConstTMsg) {
            return null;
        } else {
            return varCharConstTMsg.varCharConstVal.getValue();
        }
    }
}
