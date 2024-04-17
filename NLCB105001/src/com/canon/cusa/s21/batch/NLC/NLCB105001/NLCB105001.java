/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB105001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.NLCI1040_01TMsg;
import business.db.PRT_STK_IN_RSLT_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLCB105001 Stock In Data of the Day IF Data Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/10/17   Fujitsu         K.Fujita         Create          N/A
 *</pre>
 */

public class NLCB105001 extends S21BatchMain {

    // -- Error Message Code --------------------
    /** [@] is mandatory. */
    private static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Data insert failure.@ */
    private static final String MSG_ID_NLCM0125E = "NLCM0125E";

    /** @ could not be obtained from the @ Table.[@] */
    private static final String MSG_ID_NLCM0128E = "NLCM0128E";

    /** The value you entered is incorrect.*/
    private static final String MSG_ID_NLCM0129E = "NLCM0129E";

    /** The process has been successfully completed. (InterfaceID[@], TransactionID[@]) */
    private static final String MSG_ID_NLCM0130I = "NLCM0130I";

    // -- Const --------------

    /** Process Code : WWABF303 */
    private static final String PROCESS_CD_WWABF303 = "WWABF303";

    /** Interface Id : NLCI1040 */
    private static final String INTERFACE_ID_NLCI1040 = "NLCI1040";

    /** Stock In Sign Plus */
    public static final String STOCK_IN_SIGN_PLUS     = "+";

    /** Stock In Sign Minus */
    public static final String STOCK_IN_SIGN_MINUS    = "-";

    /** Table Column Name : DB_CLMN_INTFC_STK_IN_QTY */
    public static final String DB_CLMN_INVTY_TRX_QTY  = "INTFC_STK_IN_QTY";

    // -- Variavle --------------
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String ifId = null;

    /** process Code */
    private String processCd = "";

    /** commit count */
    private int commitCount = 0;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** trxTblAccessor */
    private S21TransactionTableAccessor trxTblAccessor = null;

    /** Transaction ID */
    private BigDecimal trxId = null;

    /** Total count */
    private int totalCount = 0;

    /** Normal count */
    int normalRecCnt = 0;

    /** Error count */
    int errRecCnt = 0;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NLCB105001().executeBatch(NLCB105001.class.getSimpleName());
    }

    /**
     * <pre>
     * Initialization routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
     */
    protected void initRoutine() {

        try {
            // Initialization
            profileService = S21UserProfileServiceFactory.getInstance().getService();
            ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            termCd = TERM_CD.NORMAL_END;
            trxTblAccessor = new S21TransactionTableAccessor();

            // Get and check the input parameters.
            getInParams();

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
            // Search Service Machine Master and Insert InterfaceTBL
            executeSearchServiceMachineMasterAndInsert();

            if (normalRecCnt > 0) {

                // Insert TransactionTBL
                executeInsertTrxTBL();

                // Update Interface Processed Flag
                updateInterfaceProcessedFlag();

                S21InfoLogOutput.println(MSG_ID_NLCM0130I, new String[] {this.ifId, trxId.toString() });
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
        // Set term code and total commit count.
        setTermState(termCd, normalRecCnt, errRecCnt, totalCount);
    }

    /**
     * <pre>
     *  Check the input parameters.
     *  oIf an error occurs, throws Exception.
     * </pre>
     */
    private void getInParams() {

        // Get the Global Company Code.
        glblCmpyCd = profileService.getGlobalCompanyCode();

        // Get Commit Count
        commitCount = getCommitCount();

        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Global Company Code" });
        }

        // Get Interface ID.
        ifId = getInterfaceID();

        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(ifId)) {
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Interface ID" });
        }

        // Get Process Code
        processCd = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(processCd)) {
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Process Code" });
        }

        if (!PROCESS_CD_WWABF303.equals(processCd) || !INTERFACE_ID_NLCI1040.equals(ifId)) {
            throw new S21AbendException(MSG_ID_NLCM0129E, null);
        }
    }

    /**
     * <pre>
     * Execute Search Service Machine Master and Insert
     * </pre>
     */
    private void executeSearchServiceMachineMasterAndInsert() {

        // Service Machine Master Search Condition
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("intfcProcFlg", ZYPConstant.FLG_OFF_N);
        params.put("stockInSignPlus"  , STOCK_IN_SIGN_PLUS);
        params.put("stockInSignMinus" , STOCK_IN_SIGN_MINUS);

        PreparedStatement stmt = null;
        ResultSet resSet = null;

        try {
            // Execute Search Parts Stock In Result Work.
            stmt = ssmLLClient.createPreparedStatement("getPartsStockInResultWork", params);
            resSet = stmt.executeQuery();

            // Insert
            List<NLCI1040_01TMsg> insList = new ArrayList<NLCI1040_01TMsg>();

            int seqNum = 1;

            boolean trxIdSetFlag = true;

            while (resSet.next()) {

                if (trxIdSetFlag) {
                    trxId = trxTblAccessor.getNextTransactionId();
                    trxIdSetFlag = false;
                }

                NLCI1040_01TMsg dlci1040TMsg = new NLCI1040_01TMsg();

                S21ResultSetMapper.map(resSet, dlci1040TMsg);

                BigDecimal invtyTrxQty = resSet.getBigDecimal(DB_CLMN_INVTY_TRX_QTY);
                if (ZYPCommonFunc.hasValue(invtyTrxQty) && invtyTrxQty.compareTo(BigDecimal.ZERO) >= 0) {
                    dlci1040TMsg.intfcStkInSgnTxt.setValue(STOCK_IN_SIGN_PLUS);
                } else {
                    dlci1040TMsg.intfcStkInSgnTxt.setValue(STOCK_IN_SIGN_MINUS);
                }

                dlci1040TMsg.intfcStkInQty.setValue(dlci1040TMsg.intfcStkInQty.getValue().abs());

                dlci1040TMsg.interfaceId.setValue(ifId);
                dlci1040TMsg.transactionId.setValue(trxId);
                dlci1040TMsg.segmentId.setValue(BigDecimal.ONE);
                dlci1040TMsg.unitId.setValue(BigDecimal.ONE);
                dlci1040TMsg.seqNumber.setValue(seqNum);

                seqNum++;

                insList.add(dlci1040TMsg);
                totalCount++;

                if (insList.size() == commitCount) {
                    int insListCnt = insList.size();
                    int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new NLCI1040_01TMsg[0]));

                    insList.clear();
                    if (insListCnt != rsltCnt) {
                        rollback();
                        termCd = TERM_CD.ABNORMAL_END;
                        errRecCnt = totalCount;
                        throw new S21AbendException(MSG_ID_NLCM0125E, new String[] {INTERFACE_ID_NLCI1040});
                    }

                    // Commit(InterfaceTBL)
                    commit();
                }
            }
            if (insList.size() > 0) {
                int insListCnt = insList.size();
                int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new NLCI1040_01TMsg[0]));

                insList.clear();
                if (insListCnt != rsltCnt) {
                    rollback();
                    termCd = TERM_CD.ABNORMAL_END;
                    errRecCnt = totalCount;
                    throw new S21AbendException(MSG_ID_NLCM0125E, new String[] {INTERFACE_ID_NLCI1040});
                }

                // Commit(InterfaceTBL)
                commit();
            }
            // Insert Nomal End
            normalRecCnt = totalCount;

        } catch (SQLException e) {
            // Insert AbnomalEnd
            // RoleBack
            rollback();
            termCd = TERM_CD.ABNORMAL_END;
            errRecCnt = totalCount;

            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, resSet);
        }
    }

    /**
     * <pre>
     * Execute InsertTrxTBL
     * </pre>
     */
    private void executeInsertTrxTBL() {
        // Create interface transaction record.
        trxTblAccessor.createIntegrationRecordForBatch(getInterfaceID(), trxId);
    }

    /**
     * <pre>
     * Update Interface Processed Flag
     * </pre>
     */
    private void updateInterfaceProcessedFlag() {
        // Update Interface Processed Flag
        PRT_STK_IN_RSLT_WRKTMsg prtStkInRsltWrktmsgSheach = new PRT_STK_IN_RSLT_WRKTMsg();
        prtStkInRsltWrktmsgSheach.glblCmpyCd.setValue(glblCmpyCd);
        prtStkInRsltWrktmsgSheach.intfcProcFlg.setValue(ZYPConstant.FLG_OFF_N);

        PRT_STK_IN_RSLT_WRKTMsg prtStkInRsltWrktmsgSet = new PRT_STK_IN_RSLT_WRKTMsg();
        prtStkInRsltWrktmsgSet.intfcProcFlg.setValue(ZYPConstant.FLG_ON_Y);

        S21FastTBLAccessor.updateByPartialValue(prtStkInRsltWrktmsgSheach, new String[] {"glblCmpyCd", "intfcProcFlg" }, prtStkInRsltWrktmsgSet, new String[] {"intfcProcFlg" });
    }
}
