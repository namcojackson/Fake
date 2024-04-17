/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB132001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.NPAI2050_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * NPAB1320 Outstanding PO IF Data Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/10/21   Fujitsu         M.Nakamura      Create          N/A
 * 2013/10/25   Fujitsu         C.Naito         Update          N/A
 * 2013/11/21   Fujitsu         S.Yoshida       Update          Def.3154
 * 2016/04/20   CITS            R.Shimamoto     Update          V2.0
 *</pre>
 */
public class NPAB132001 extends S21BatchMain {

    // -- Error Message Code --------------------
    /** [@] is mandatory. */
    private static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Data insert failure. */
    private static final String MSG_ID_NPAM1341E = "NPAM1341E";

    // -- Constant --------------
    /** Insert Count */
    private static final int INS_UNI_CNT = 1000;

    /** NPAI2050_01 TableName */
    public static final String TABLE_NPAI2050_01 = "Table: NPAI2050_01";

    // -- Variable --------------
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Date. */
    private String batchExecDate = null;

    /** Interface ID */
    private String ifId = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** trxTblAccessor */
    private S21TransactionTableAccessor trxTblAccessor = null;

    /** Term code */
    private TERM_CD termCd = null;

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
        new NPAB132001().executeBatch(NPAB132001.class.getSimpleName());
    }

    /**
     * <pre>
     * Initialization routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
     */
    @Override
    protected void initRoutine() {
        try {
            // Initialization
            profileService = S21UserProfileServiceFactory.getInstance().getService();
            ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            termCd = TERM_CD.NORMAL_END;
            trxTblAccessor = new S21TransactionTableAccessor();
            trxId = trxTblAccessor.getNextTransactionId();
            batchExecDate = ZYPDateUtil.getBatProcDate(getGlobalCompanyCode());

            S21InfoLogOutput.println("Transaction ID = " + trxId.toString());

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
    @Override
    protected void mainRoutine() {
        try {
            // Search MDSE and Insert InterfaceTBL
            executeSearchWorkAndInsert();

            if (normalRecCnt > 0) {
                // Insert TransactionTBL
                executeInsertTrxTBL();

                // Commit
                commit();
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
    @Override
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

        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Global Company Code" });
        }

        // Get Interface ID.
        ifId = getInterfaceID();

        S21InfoLogOutput.println("Interface ID = " + ifId);

        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(ifId)) {
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Interface ID" });
        }
    }

    /**
     * <pre>
     * Execute Search Work and Insert.
     * </pre>
     */
    private void executeSearchWorkAndInsert() {

        // Work Search Condition
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("intfcCratDt", this.batchExecDate);

        PreparedStatement stmt = null;
        ResultSet resSet = null;

        try {
            // Execute Search Work.
            stmt = ssmLLClient.createPreparedStatement("getWork", params);
            resSet = stmt.executeQuery();

            // Insert
            List<NPAI2050_01TMsg> insList = new ArrayList<NPAI2050_01TMsg>();

            int seqNum = 1;

            while (resSet.next()) {
                NPAI2050_01TMsg dpai2050TMsg = new NPAI2050_01TMsg();

                S21ResultSetMapper.map(resSet, dpai2050TMsg);

                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.interfaceId, ifId);
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.transactionId, trxId);
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.segmentId, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.unitId, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.seqNumber, BigDecimal.valueOf(seqNum++));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.glblCmpy3Cd, resSet.getString("GLBL_CMPY_CD"));
                // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
//                dpai2050TMsg.cincGlblWhCd.clear();
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.cincGlblWhCd, resSet.getString("CINC_GLBL_WH_CD"));
                // END 05/09/2016 R.Shimamoto [V2.0 MOD]
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcPoOrdDt, resSet.getString("INTFC_PO_ORD_DT"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcPoOrdNum, resSet.getString("INTFC_PO_ORD_NUM"));
                dpai2050TMsg.cincCoNum.clear();
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.cincSplyGlblCmpyCd, resSet.getString("CINC_SPLY_GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.cincSplyGlblWhCd, resSet.getString("CINC_SPLY_GLBL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.cincVndGlblCmpyCd, resSet.getString("CINC_VND_GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcPrtVndCd, resSet.getString("INTFC_PRT_VND_CD"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcPrtMdseCd, resSet.getString("INTFC_PRT_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.prtSizeTxt, resSet.getString("PRT_SIZE_TXT"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcPoRmnSgnTxt, resSet.getString("INTFC_PO_RMN_SGN_TXT"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcPoRmnQty, resSet.getBigDecimal("INTFC_PO_RMN_QTY"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcEstStkInDt, resSet.getString("INTFC_EST_STK_IN_DT"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.cincGlblShpgMethCd, resSet.getString("CINC_GLBL_SHPG_METH_CD"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.cincPoGlblOrdCatgCd, resSet.getString("CINC_PO_GLBL_ORD_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.prtChrgInd, resSet.getString("PRT_CHRG_IND"));
                ZYPEZDItemValueSetter.setValue(dpai2050TMsg.intfcCratDt, resSet.getString("INTFC_CRAT_DT"));

                insList.add(dpai2050TMsg);
                totalCount++;

                if (insList.size() == INS_UNI_CNT) {
                    int insListCnt = insList.size();
                    int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new NPAI2050_01TMsg[0]));
                    insList.clear();
                    if (insListCnt != rsltCnt) {
                        throw new S21AbendException(MSG_ID_NPAM1341E, new String[] {TABLE_NPAI2050_01 });
                    }
                }

            }
            if (insList.size() > 0) {
                int insListCnt = insList.size();
                int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new NPAI2050_01TMsg[0]));
                insList.clear();
                if (insListCnt != rsltCnt) {
                    throw new S21AbendException(MSG_ID_NPAM1341E, new String[] {TABLE_NPAI2050_01 });
                }
            }
            // Insert NomalEnd
            normalRecCnt = totalCount;

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
     * Execute InsertTrxTBL
     * </pre>
     */
    private void executeInsertTrxTBL() {
        // Create interface transaction record.
        trxTblAccessor.createIntegrationRecordForBatch(getInterfaceID(), trxId);
    }

}
