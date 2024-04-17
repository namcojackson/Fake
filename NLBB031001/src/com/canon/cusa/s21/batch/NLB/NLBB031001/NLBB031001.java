/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB031001;

import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.CARR_TP_CD_UPS;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_CNSGN_ADDR;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_CNSGN_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_CNSGN_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_CNSGN_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_CNSGN_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_01;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_02;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_03;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_04;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_09;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_18;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_23;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_24;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_25;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_26;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_27;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_38;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_46;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_48;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_50;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_51;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_55;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_56;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_57;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_59;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_60;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_62;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_63;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_76;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_FLD_TXT_78;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_GS_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_LOAD_QTY;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_PICK_UP_DT;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_PICK_UP_TM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_PRO_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_SHPPR_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_STS_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_STS_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_STS_DT;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_STS_RSN_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_STS_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_STS_TM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_ST_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_TM_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_TP_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_TRX_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_WT;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_EDI_WT_UNIT_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.COL_TRX_LINE_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.EDI_FLD_TXT_51_QQ;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.EDI_FLD_TXT_57_AA;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.EDI_FLD_TXT_62_A3;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.EDI_FLD_TXT_76_O;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.EDI_TP_CD_UPS;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_CARR_TP_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_EDI_FLD_TXT_55;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_EDI_FLD_TXT_57;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_IS_POD_WRK;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_PRO_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.KEY_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.LG_EDI_ST_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.NLGM0045E;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.NLGM0049E;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.ONE;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.PRAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.PRAM_NM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.SQL_STMT_ID_GET_EDI_RCV_LIST;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.ST_APPL_ERR_END;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.ST_NORMAL_END;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.TBL_NLBI1320_01;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.TBL_POD_ADDR_WRK;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.TBL_POD_STS_WRK;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.TP_PROOF_OF_DELIVERY;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.TP_PROOF_OF_PICKUP;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.UPS_ETA_STS_CD_FLD_57;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.VAL_BLANK;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.VAL_CONST_EDI_TM_CD_LT;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.VAL_EDI_LN_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.VAL_EMPTY;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.VAL_SLASH;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.VAL_ZERO;
import static com.canon.cusa.s21.batch.NLB.NLBB031001.constant.NLBB031001Constant.ZERO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.POD_ADDR_WRKTMsg;
import business.db.POD_STS_WRKTMsg;
import business.parts.NLZC405001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC405001.NLZC405001;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Batch Program Class for Receive EDI240
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   CITS            N.Akaishi       Create          V1.0
 * 01/17/2018   CITS            S.Katsuma       Update          QC#23056
 * 02/02/2018   CITS            T.Hakodate      Update          QC#23858
 * 02/08/2018   CITS            Y.Iwasaki       Update          QC#30288
 * 02/13/2018   CITS            M.Naito         Update          QC#30342
 * 03/29/2019   Fujitsu         T.Ogura         Update          QC#30594
 * 05/10/2019   CITS            K.Ogino         Update          QC#31201
 *</pre>
 */
public class NLBB031001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************
    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Counter of Records: Successful */
    private int normalRecCount = 0;

    /** Counter of Records: Error */
    private int totalErrCount = 0;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** */
    private S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();

    // START 2018/01/17 S.Katsuma [QC#23056,ADD]
    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Sales Date */
    private String salesDate = null;
    // END 2018/01/17 S.Katsuma [QC#23056,ADD]

    /** ETA Date Status 57 */
    private List<String> etaStatusFld57 = null;

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arguments
     */
    public static void main(String[] args) {
        new NLBB031001().executeBatch(NLBB031001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        // START 2018/01/17 S.Katsuma [QC#23056,ADD]
        errMsgList = new ArrayList<Map<String, String>>();
        // START 2018/01/17 S.Katsuma [QC#23056,ADD]

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_INTERFACE_ID });
        }

        // START 2018/01/17 S.Katsuma [QC#23056,ADD]
        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        // END 2018/01/17 S.Katsuma [QC#23056,ADD]

        String constValue = ZYPCodeDataUtil.getVarCharConstValue(UPS_ETA_STS_CD_FLD_57, getGlobalCompanyCode());
        if (constValue != null) {
            this.etaStatusFld57 = Arrays.asList(constValue.split(","));
        } else {
            this.etaStatusFld57 = Arrays.asList(new String[]{POD_STS.ESTIMATED_DELIVERY_APPOINTMENT_DATE_AND_OR_OR_TIME, POD_STS.DELIVERY_APPOINTMENT_SECURED});
        }
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccessor.getIntegrationRecord(this.interfaceId);

        if (tranIds.length == 0) {
            return;
        }

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        int rtnSt = ZERO;
        // 2017/01/17 S.Katsuma [QC#23056,ADD]
        try {
            for (BigDecimal tranId : tranIds) {
                // POD (Proof of Delivery)
                List<Map<String, Object>> wrkProcList = createPodInterface(execParam, tranId);

                // POD (Proof of Delivery) WRK Data
                rtnSt = createEdiRcvInterface(execParam, tranId, wrkProcList);

                if (rtnSt == ST_APPL_ERR_END) {
                    rollback();
                    continue;
                }

                // ETA WRK Data
                rtnSt = createETAInterface(execParam, tranId);

                if (rtnSt == ST_APPL_ERR_END) {
                    rollback();
                    continue;
                }

                // Processed Flg of transaction ID update to the
                // Processed.
                trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
                commit();
            }
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
                commit();
            }
        }
        // 2017/01/17 S.Katsuma [QC#23056,ADD]

    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(termCd, this.normalRecCount, this.totalErrCount, this.normalRecCount + this.totalErrCount);
    }

    /**
     * create POD_ADDR_WRK, POD_STS_WRK from Receiving IF(NLBI1320_01)
     * @param execParam S21SsmExecutionParameter
     * @param tranId BigDecimal transactionID
     * @return return code
     */
    private int createEdiRcvInterface(S21SsmExecutionParameter execParam, BigDecimal tranId, List<Map<String, Object>> wrkProcList) {

        PreparedStatement prdStmtEdiRcvList = null;
        ResultSet rsEdiRcvList = null;

        try {

            Map<String, Object> queryParam = setPodDataParams(tranId);
            queryParam.put(KEY_IS_POD_WRK, "Y");
            prdStmtEdiRcvList = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_EDI_RCV_LIST, queryParam, execParam);
            rsEdiRcvList = prdStmtEdiRcvList.executeQuery();

            // START 2018/01/17 S.Katsuma [QC#23056,ADD]
            while (rsEdiRcvList.next()) {
                Map<String, Object> ediRcvInfo = new HashMap<String, Object>();
                setWmsPodData(ediRcvInfo, rsEdiRcvList, "0");
                wrkProcList.add(ediRcvInfo);
            }
            sortPodWrkList(wrkProcList);
            // START 2018/01/17 S.Katsuma [QC#23056,ADD]

            // BigDecimal ediTrxId =
            // trxAccessor.getNextTransactionId();
            BigDecimal ediTrxId = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_POD_CTRL_SQ");

            // Break Key : [TRACKING NUMBER]/[EDI DATE]/[EDI
            // TIME]/[EDI NUMBER]/[EDI SET NUMBER]
            String beforePodKey = VAL_EMPTY;
            String nowPodKey = VAL_EMPTY;

            // START 2018/01/17 S.Katsuma [QC#23056,DEL]
            Map<String, Object> ediRcvInfo = new HashMap<String, Object>();
            // END 2018/01/17 S.Katsuma [QC#23056,DEL]
            int ediStCtrlCdSeq = ONE;

            // START 2018/01/17 S.Katsuma [QC#23056,MOD]
//            while (rsEdiRcvList.next()) {
            for (Map<String, Object> wrkProc : wrkProcList) {
            // END 2018/01/17 S.Katsuma [QC#23056,MOD]

                StringBuilder podKeyBuff = new StringBuilder();
             // START 2018/02/02 T.Hakodate [QC#23858,MOD]
             // podKeyBuff.append(wrkProc.get(COL_EDI_PRO_NUM)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_01)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_TM_CD)).append(VAL_SLASH)
             // .append(wrkProc.get(COL_EDI_FLD_TXT_03)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_04));
                podKeyBuff.append(wrkProc.get(COL_EDI_PRO_NUM)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_01)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_02)).append(VAL_SLASH)
                        .append(wrkProc.get(COL_EDI_FLD_TXT_03)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_04));
             // END   2018/02/02 T.Hakodate [QC#23858,MOD]
                nowPodKey = podKeyBuff.toString();
                podKeyBuff.setLength(ZERO);

                if (!VAL_EMPTY.equals(beforePodKey) && !nowPodKey.equals(beforePodKey)) {

                    // insert POD_ADDR_WRK
                    createPodAddrWrk(ediRcvInfo);

                    // insert POD_STS_WRK
                    createPodStsWrk(ediRcvInfo);

                    ediRcvInfo.clear();

                    // increment Seq for EDI_ST_CTRL_CODE
                    ediStCtrlCdSeq++;

                    normalRecCount++;
                }

                String ediStCtrlCd = ediTrxId.toPlainString() + ZYPCommonFunc.leftPad(String.valueOf(ediStCtrlCdSeq), LG_EDI_ST_CTRL_CD, VAL_ZERO);
                if (ediStCtrlCd.length() > 10) {
                    ediStCtrlCd = ediStCtrlCd.substring(ediStCtrlCd.length() - 10);
                }
                ediRcvInfo.put(COL_EDI_ST_CTRL_CD, ediStCtrlCd);

                String ediTrxIdWk = ediTrxId.toPlainString();
                if (ediTrxIdWk.length() > 10) {
                    ediTrxIdWk = ediTrxIdWk.substring(ediTrxIdWk.length() - 10);
                }
                ediRcvInfo.put(COL_EDI_GS_CTRL_CD, ediTrxIdWk);

                setWmsPodData(ediRcvInfo, wrkProc);
                beforePodKey = nowPodKey;
            }

            if (ediRcvInfo.size() > ZERO) {
                // insert POD_ADDR_WRK
                createPodAddrWrk(ediRcvInfo);

                // insert POD_STS_WRK
                createPodStsWrk(ediRcvInfo);

                // increment Seq for EDI_ST_CTRL_CODE
                ediStCtrlCdSeq++;

                ediRcvInfo.clear();

                normalRecCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtEdiRcvList, rsEdiRcvList);
        }

        return ST_NORMAL_END;
    }

    /**
     * createPodAddrWrk
     * @param pEdiRcvInfo
     * @return return code
     */
    private int createPodAddrWrk(Map<String, Object> pEdiRcvInfo) {
        POD_ADDR_WRKTMsg podAddrWrk = new POD_ADDR_WRKTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTrxId, (String) pEdiRcvInfo.get(COL_EDI_TRX_ID));

        // EDI_SQ_ID
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_ADDR_SQ);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediSqId, ediSqId.toPlainString());

        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.procStsCd, (String) pEdiRcvInfo.get(COL_PROC_STS_CD));

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTpCd, (String) pEdiRcvInfo.get(COL_EDI_TP_CD));

        // EDI_GS_CTRL_CD (For each batch processing of once, Sql ID
        // numbering)
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd, (String) pEdiRcvInfo.get(COL_EDI_GS_CTRL_CD));

        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediStCtrlCd, (String) pEdiRcvInfo.get(COL_EDI_ST_CTRL_CD));

        // EDI_SHPPR_NM
        // START 2019/02/13 M.Naito [QC#30342,MOD]
//        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, (String) pEdiRcvInfo.get(COL_EDI_SHPPR_NM));
        String ediShpprNm = (String) pEdiRcvInfo.get(COL_EDI_SHPPR_NM);
        if (ZYPCommonFunc.hasValue(ediShpprNm) && ediShpprNm.length() > 35) {
            ediShpprNm = ediShpprNm.substring(0, 35);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, ediShpprNm);

        // EDI_CNSGN_NM
//        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnNm, (String) pEdiRcvInfo.get(COL_EDI_CNSGN_NM));
        String ediCnsgnNm = (String) pEdiRcvInfo.get(COL_EDI_CNSGN_NM);
        if (ZYPCommonFunc.hasValue(ediCnsgnNm) && ediCnsgnNm.length() > 35) {
            ediCnsgnNm = ediCnsgnNm.substring(0, 35);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnNm, ediCnsgnNm);

        // EDI_CNSGN_ADDR
//        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnAddr, (String) pEdiRcvInfo.get(COL_EDI_CNSGN_ADDR));
        String ediCnsgnAddr = (String) pEdiRcvInfo.get(COL_EDI_CNSGN_ADDR);
        if (ZYPCommonFunc.hasValue(ediCnsgnAddr) && ediCnsgnAddr.length() > 35) {
            ediCnsgnAddr = ediCnsgnAddr.substring(0, 35);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnAddr, ediCnsgnAddr);
        // END 2019/02/13 M.Naito [QC#30342,MOD]

        // EDI_CNSGN_CTY_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnCtyNm, (String) pEdiRcvInfo.get(COL_EDI_CNSGN_CTY_NM));

        // EDI_CNSGN_ST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnStCd, (String) pEdiRcvInfo.get(COL_EDI_CNSGN_ST_CD));

        // EDI_CNSGN_POST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnPostCd, (String) pEdiRcvInfo.get(COL_EDI_CNSGN_POST_CD));

        // PROC_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.podSrcTpCd, POD_SRC_TP._240);

        // insert POD_ADDR_WRK
        EZDTBLAccessor.insert(podAddrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podAddrWrk.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_ADDR_WRK, TBL_NLBI1320_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID),
                    NLXCMsgHelper.toListedString(this.glblCmpyCd, pEdiRcvInfo.get(COL_TRANSACTION_ID)) });
        }

        return ST_NORMAL_END;
    }

    /**
     * createPodStsWrk
     * @param pEdiRcvInfo
     * @return return code
     */
    private int createPodStsWrk(Map<String, Object> pEdiRcvInfo) {
        POD_STS_WRKTMsg podStsWrk = new POD_STS_WRKTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTrxId, (String) pEdiRcvInfo.get(COL_EDI_TRX_ID));

        // EDI_SQ_ID
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_STS_SQ);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediSqId, ediSqId.toPlainString());

        // ERR_MSG_CD

        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.procStsCd, (String) pEdiRcvInfo.get(COL_PROC_STS_CD));

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTpCd, (String) pEdiRcvInfo.get(COL_EDI_TP_CD));

        // EDI_GS_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediGsCtrlCd, (String) pEdiRcvInfo.get(COL_EDI_GS_CTRL_CD));

        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStCtrlCd, (String) pEdiRcvInfo.get(COL_EDI_ST_CTRL_CD));

        // EDI_LN_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLnCtrlCd, VAL_EDI_LN_CTRL_CD);

        // EDI_PRO_NUM(TRACKING_NUMBER)
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum, (String) pEdiRcvInfo.get(COL_EDI_PRO_NUM));

        // EDI_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCd, (String) pEdiRcvInfo.get(COL_EDI_STS_CD));

        // EDI_STS_DT
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsDt, (String) pEdiRcvInfo.get(COL_EDI_STS_DT));

        // EDI_STS_TM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsTm, (String) pEdiRcvInfo.get(COL_EDI_STS_TM));

        // EDI_TM_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTmCd, VAL_CONST_EDI_TM_CD_LT);

        // EDI_STS_RSN_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsRsnCd, (String) pEdiRcvInfo.get(COL_EDI_STS_RSN_CD));

        // EDI_STS_CTY_NM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCtyNm, (String) pEdiRcvInfo.get(COL_EDI_STS_CTY_NM));

        // EDI_STS_ST_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsStCd, (String) pEdiRcvInfo.get(COL_EDI_STS_ST_CD));

        // EDI_WT
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediWt, (BigDecimal) pEdiRcvInfo.get(COL_EDI_WT));

        // EDI_WT_UNIT_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediWtUnitCd, (String) pEdiRcvInfo.get(COL_EDI_WT_UNIT_CD));

        // EDI_LOAD_QTY
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLoadQty, (BigDecimal) pEdiRcvInfo.get(COL_EDI_LOAD_QTY));

        // EDI_PICK_UP_DT
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediPickUpDt, (String) pEdiRcvInfo.get(COL_EDI_PICK_UP_DT));

        // EDI_PICK_UP_TM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediPickUpTm, (String) pEdiRcvInfo.get(COL_EDI_PICK_UP_TM));

        // insert POD_STS_WRK
        EZDTBLAccessor.insert(podStsWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podStsWrk.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_STS_WRK, TBL_NLBI1320_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID),
                    NLXCMsgHelper.toListedString(this.glblCmpyCd, pEdiRcvInfo.get(COL_TRANSACTION_ID)) });
        }

        return ST_NORMAL_END;
    }

    // START 2018/01/17 S.Katsuma [QC#23056,ADD]
    /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * createPodInterface
     * @param execParam
     */
    private List<Map<String, Object>> createPodInterface(S21SsmExecutionParameter execParam, BigDecimal tranId) {
        PreparedStatement prdStmtPod = null;
        ResultSet rsPod = null;
        List<Map<String, Object>> wrkProcList = new ArrayList<Map<String, Object>>();

        try {

            Map<String, Object> queryParam = setPodDataParams(tranId);
            queryParam.put(KEY_IS_POD_WRK, "N");

            prdStmtPod = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_EDI_RCV_LIST, queryParam, execParam);
            rsPod = prdStmtPod.executeQuery();
            while (rsPod.next()) {

                // Get WMS Upload [POD] Header
                Map<String, Object> wmsPodData = new HashMap<String, Object>();
                wmsPodData.put(COL_TRANSACTION_ID, tranId.toPlainString());
                wmsPodData.put(COL_EDI_FLD_TXT_38, rsPod.getString(COL_EDI_FLD_TXT_38));
                String ediFldTxt55 = rsPod.getString(COL_EDI_FLD_TXT_55);
                wmsPodData.put(COL_EDI_FLD_TXT_55, ediFldTxt55);
                wmsPodData.put(COL_EDI_FLD_TXT_59, rsPod.getString(COL_EDI_FLD_TXT_59));
                wmsPodData.put(COL_EDI_FLD_TXT_60, rsPod.getString(COL_EDI_FLD_TXT_60));

                boolean flResult = false;
                String queryName = null;
                if (ZYPCommonFunc.hasValue(ediFldTxt55) && TP_PROOF_OF_PICKUP.equals(ediFldTxt55)) {
//                    queryName = "getRWSNumByTrackingNum";
                    // Inbound data is skipped because Can't get RWS# by Tracking# at 2018/01/22
                    setWmsPodData(wmsPodData, rsPod, "0");
                    wrkProcList.add(wmsPodData);
                    continue;
                } else if (ZYPCommonFunc.hasValue(ediFldTxt55) && TP_PROOF_OF_DELIVERY.equals(ediFldTxt55)) {
                    queryName = "getSoNumByTrackingNum";
                }
                flResult = getTrxNum(execParam, wmsPodData, queryName);

                if (!flResult) {
                    setWmsPodData(wmsPodData, rsPod, "0");
                    wrkProcList.add(wmsPodData);
                } else {
                    setWmsPodData(wmsPodData, rsPod, "1");
                    wrkProcList.add(wmsPodData);
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtPod, rsPod);
        }

        return wrkProcList;
    }

    private Map<String, Object> setPodDataParams(BigDecimal tranId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(KEY_INTERFACE_ID, this.interfaceId);
        queryParam.put(KEY_TRANSACTION_ID, tranId);
        queryParam.put(KEY_EDI_FLD_TXT_55, new String[] {TP_PROOF_OF_PICKUP, TP_PROOF_OF_DELIVERY });

        return queryParam;
    }

    private boolean getTrxNum(S21SsmExecutionParameter execParam, Map<String, Object> wmsPodData, String queryName) {
        PreparedStatement trxStmtPod = null;
        ResultSet rsTrx = null;
        boolean flResult = false;

        try {
            // Get SO Num or RWS Num
            Map<String, Object> trxQueryParam = new HashMap<String, Object>();
            trxQueryParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
            trxQueryParam.put(KEY_PRO_NUM, (String) wmsPodData.get(COL_EDI_FLD_TXT_38));
            trxQueryParam.put(KEY_CARR_TP_CD, CARR_TP_CD_UPS);

            trxStmtPod = ssmLLClient.createPreparedStatement(queryName, trxQueryParam, execParam);
            rsTrx = trxStmtPod.executeQuery();
            while (rsTrx.next()) {
                if (!flResult) {
                    flResult = true;
                }

                wmsPodData.put(COL_TRX_HDR_NUM, rsTrx.getString(COL_TRX_HDR_NUM));
                wmsPodData.put(COL_TRX_LINE_NUM, rsTrx.getString(COL_TRX_LINE_NUM));

                // API Call
                if (!callNLZC405001(wmsPodData)) {
//                    outputErr(NLGM0041E, new String[] {COL_EDI_TRX_ID, TBL_NLBI1320_01, this.interfaceId + "," + (String) wmsPodData.get(COL_TRANSACTION_ID) });    // 2019/03/29 T.Ogura [QC#30594,DEL]
                    continue;
                }
                normalRecCount++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(trxStmtPod, rsTrx);
        }

        return flResult;
    }

    private boolean callNLZC405001(Map<String, Object> wmsPodData) {
        NLZC405001PMsg nlzc405001PMsg = new NLZC405001PMsg();

        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.slsDt, this.salesDate);

        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxHdrNum, (String) wmsPodData.get(COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxLineNum, (String) wmsPodData.get(COL_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.proNum, (String) wmsPodData.get(COL_EDI_FLD_TXT_38));

        String ediFldTxt55 = (String) wmsPodData.get(COL_EDI_FLD_TXT_55);
        String carrRsnCd = null;
        String inbdOtbdCd = null;
        if (ZYPCommonFunc.hasValue(ediFldTxt55) && TP_PROOF_OF_PICKUP.equals(ediFldTxt55)) {
            carrRsnCd = CARR_RSN.PICKED_UP;
            inbdOtbdCd = "1";
        } else if (ZYPCommonFunc.hasValue(ediFldTxt55) && TP_PROOF_OF_DELIVERY.equals(ediFldTxt55)) {
            carrRsnCd = CARR_RSN.DELIVERED;
            inbdOtbdCd = "2";
        }
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.inbdOtbdCd, inbdOtbdCd);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, carrRsnCd);

        String ediFldTxt59 = (String) wmsPodData.get(COL_EDI_FLD_TXT_59);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.xxRqstDt, ediFldTxt59);
        String ediFldTxt60 = (String) wmsPodData.get(COL_EDI_FLD_TXT_60);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.actlDelyTm, ediFldTxt60);
        if (ZYPCommonFunc.hasValue(ediFldTxt59) && ZYPCommonFunc.hasValue(ediFldTxt60)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.updTs, ediFldTxt59 + ediFldTxt60);
        }
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.podSrcTpCd, POD_SRC_TP._240);

        NLZC405001 nlzc405001 = new NLZC405001();
        nlzc405001.execute(nlzc405001PMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc405001PMsg);

        if (msgList.size() > 0) {
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (msgId.endsWith("E")) {
                    outputApiCallErr(msgId, null, wmsPodData);    // 2019/03/29 T.Ogura [QC#30594,ADD]
                    return false;
                }
            }
        }
        return true;
    }

    private void setWmsPodData(Map<String, Object> ediRcvInfo, ResultSet rsEdiRcvList, String procStsCd) throws SQLException {
        ediRcvInfo.put(COL_INTERFACE_ID, rsEdiRcvList.getString(COL_INTERFACE_ID));
        ediRcvInfo.put(COL_EDI_TRX_ID, rsEdiRcvList.getBigDecimal(COL_TRANSACTION_ID).toPlainString());

        ediRcvInfo.put(COL_PROC_STS_CD, procStsCd);
        ediRcvInfo.put(COL_EDI_TP_CD, EDI_TP_CD_UPS);

        ediRcvInfo.put(COL_EDI_FLD_TXT_04, rsEdiRcvList.getString(COL_EDI_FLD_TXT_04));
        ediRcvInfo.put(COL_EDI_FLD_TXT_03, rsEdiRcvList.getString(COL_EDI_FLD_TXT_03));
        ediRcvInfo.put(COL_EDI_FLD_TXT_01, rsEdiRcvList.getString(COL_EDI_FLD_TXT_01));
        // START 2018/02/02 T.Hakodate [QC#23858,ADD]
        ediRcvInfo.put(COL_EDI_FLD_TXT_02, rsEdiRcvList.getString(COL_EDI_FLD_TXT_02));
        ediRcvInfo.put(COL_EDI_PRO_NUM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_38));
        // END   2018/02/02 T.Hakodate [QC#23858,ADD]

        if (ZYPCommonFunc.hasValue(rsEdiRcvList.getString(COL_EDI_FLD_TXT_55))) {
            // set base pod line info

            // set POD base info
            ediRcvInfo.put(COL_EDI_SHPPR_NM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_09));
            ediRcvInfo.put(COL_EDI_CNSGN_NM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_18));

            StringBuilder ediCnsgnAddrBuff = new StringBuilder();
            if (ZYPCommonFunc.hasValue(rsEdiRcvList.getString(COL_EDI_FLD_TXT_23))) {
                ediCnsgnAddrBuff.append(rsEdiRcvList.getString(COL_EDI_FLD_TXT_23));
            }
            if (ZYPCommonFunc.hasValue(rsEdiRcvList.getString(COL_EDI_FLD_TXT_24))) {
                ediCnsgnAddrBuff.append(rsEdiRcvList.getString(COL_EDI_FLD_TXT_24));
            }
            ediRcvInfo.put(COL_EDI_CNSGN_ADDR, ediCnsgnAddrBuff.toString());
            ediCnsgnAddrBuff.setLength(ZERO);

            ediRcvInfo.put(COL_EDI_CNSGN_CTY_NM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_25));
            ediRcvInfo.put(COL_EDI_CNSGN_ST_CD, rsEdiRcvList.getString(COL_EDI_FLD_TXT_26));
            ediRcvInfo.put(COL_EDI_CNSGN_POST_CD, rsEdiRcvList.getString(COL_EDI_FLD_TXT_27));

            // START 2018/02/02 T.Hakodate [QC#23858,MOD]
            // ediRcvInfo.put(COL_EDI_PRO_NUM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_38));
            // END 2018/02/02 T.Hakodate [QC#23858,MOD]
            ediRcvInfo.put(COL_EDI_STS_CD, rsEdiRcvList.getString(COL_EDI_FLD_TXT_55));

            ediRcvInfo.put(COL_EDI_STS_DT, rsEdiRcvList.getString(COL_EDI_FLD_TXT_59));
            ediRcvInfo.put(COL_EDI_STS_TM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_60));

            // START 2018/02/02 T.Hakodate [QC#23858,MOD]
            // ediRcvInfo.put(COL_EDI_TM_CD , rsEdiRcvList.getString(COL_EDI_FLD_TXT_02));
            // END 2018/02/02 T.Hakodate [QC#23858,MOD]
            ediRcvInfo.put(COL_EDI_STS_RSN_CD, rsEdiRcvList.getString(COL_EDI_FLD_TXT_56));
            ediRcvInfo.put(COL_EDI_STS_CTY_NM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_46));
            ediRcvInfo.put(COL_EDI_STS_ST_CD, rsEdiRcvList.getString(COL_EDI_FLD_TXT_48));

        }

        // set POD additional info

        if (ZYPCommonFunc.hasValue(rsEdiRcvList.getString(COL_EDI_FLD_TXT_62)) && EDI_FLD_TXT_62_A3.equals(rsEdiRcvList.getString(COL_EDI_FLD_TXT_62))) {
            ediRcvInfo.put(COL_EDI_WT, rsEdiRcvList.getBigDecimal(COL_EDI_FLD_TXT_63));
        }

        if (ZYPCommonFunc.hasValue(rsEdiRcvList.getString(COL_EDI_FLD_TXT_51)) && EDI_FLD_TXT_51_QQ.equals(rsEdiRcvList.getString(COL_EDI_FLD_TXT_51))) {
            ediRcvInfo.put(COL_EDI_WT_UNIT_CD, ZYPCommonFunc.subByteString(rsEdiRcvList.getString(COL_EDI_FLD_TXT_50), ZERO, ONE));
        }

        if (ZYPCommonFunc.hasValue(rsEdiRcvList.getString(COL_EDI_FLD_TXT_76)) && EDI_FLD_TXT_76_O.equals(rsEdiRcvList.getString(COL_EDI_FLD_TXT_76))) {
            ediRcvInfo.put(COL_EDI_LOAD_QTY, rsEdiRcvList.getBigDecimal(COL_EDI_FLD_TXT_78));
        }

        if (ZYPCommonFunc.hasValue(rsEdiRcvList.getString(COL_EDI_FLD_TXT_57)) && EDI_FLD_TXT_57_AA.equals(rsEdiRcvList.getString(COL_EDI_FLD_TXT_57))) {
            ediRcvInfo.put(COL_EDI_PICK_UP_DT, rsEdiRcvList.getString(COL_EDI_FLD_TXT_59));
            ediRcvInfo.put(COL_EDI_PICK_UP_TM, rsEdiRcvList.getString(COL_EDI_FLD_TXT_60));
        }
    }

    private void setWmsPodData(Map<String, Object> ediRcvInfo, Map<String, Object> wrkProc) throws SQLException {

        // START 2018/02/02 T.Hakodate [QC#23858,MOD]
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_INTERFACE_ID))) {
            ediRcvInfo.put(COL_INTERFACE_ID, wrkProc.get(COL_INTERFACE_ID));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_TRX_ID))) {
            ediRcvInfo.put(COL_EDI_TRX_ID, wrkProc.get(COL_EDI_TRX_ID));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_PROC_STS_CD))) {
            ediRcvInfo.put(COL_PROC_STS_CD, wrkProc.get(COL_PROC_STS_CD));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_TP_CD))) {
            ediRcvInfo.put(COL_EDI_TP_CD, wrkProc.get(COL_EDI_TP_CD));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_SHPPR_NM))) {
            ediRcvInfo.put(COL_EDI_SHPPR_NM, wrkProc.get(COL_EDI_SHPPR_NM));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_CNSGN_NM))) {
            ediRcvInfo.put(COL_EDI_CNSGN_NM, wrkProc.get(COL_EDI_CNSGN_NM));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_CNSGN_ADDR))) {
            ediRcvInfo.put(COL_EDI_CNSGN_ADDR, wrkProc.get(COL_EDI_CNSGN_ADDR));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_CNSGN_CTY_NM))) {
            ediRcvInfo.put(COL_EDI_CNSGN_CTY_NM, wrkProc.get(COL_EDI_CNSGN_CTY_NM));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_CNSGN_ST_CD))) {
            ediRcvInfo.put(COL_EDI_CNSGN_ST_CD, wrkProc.get(COL_EDI_CNSGN_ST_CD));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_CNSGN_POST_CD))) {
            ediRcvInfo.put(COL_EDI_CNSGN_POST_CD, wrkProc.get(COL_EDI_CNSGN_POST_CD));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_PRO_NUM))) {
            ediRcvInfo.put(COL_EDI_PRO_NUM, wrkProc.get(COL_EDI_PRO_NUM));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_STS_CD))) {
            ediRcvInfo.put(COL_EDI_STS_CD, wrkProc.get(COL_EDI_STS_CD));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_STS_DT))) {
            ediRcvInfo.put(COL_EDI_STS_DT, wrkProc.get(COL_EDI_STS_DT));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_STS_TM))) {
            ediRcvInfo.put(COL_EDI_STS_TM, wrkProc.get(COL_EDI_STS_TM));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_TM_CD))) {
            ediRcvInfo.put(COL_EDI_TM_CD, wrkProc.get(COL_EDI_TM_CD));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_STS_RSN_CD))) {
            ediRcvInfo.put(COL_EDI_STS_RSN_CD, wrkProc.get(COL_EDI_STS_RSN_CD));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_STS_CTY_NM))) {
            ediRcvInfo.put(COL_EDI_STS_CTY_NM, wrkProc.get(COL_EDI_STS_CTY_NM));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_STS_ST_CD))) {
            ediRcvInfo.put(COL_EDI_STS_ST_CD, wrkProc.get(COL_EDI_STS_ST_CD));
        }
        if (ZYPCommonFunc.hasValue((BigDecimal) wrkProc.get(COL_EDI_WT))) {
            ediRcvInfo.put(COL_EDI_WT, wrkProc.get(COL_EDI_WT));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_WT_UNIT_CD))) {
            ediRcvInfo.put(COL_EDI_WT_UNIT_CD, wrkProc.get(COL_EDI_WT_UNIT_CD));
        }
        if (ZYPCommonFunc.hasValue((BigDecimal) wrkProc.get(COL_EDI_LOAD_QTY))) {
            ediRcvInfo.put(COL_EDI_LOAD_QTY, wrkProc.get(COL_EDI_LOAD_QTY));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_PICK_UP_DT))) {
            ediRcvInfo.put(COL_EDI_PICK_UP_DT, wrkProc.get(COL_EDI_PICK_UP_DT));
        }
        if (ZYPCommonFunc.hasValue((String) wrkProc.get(COL_EDI_PICK_UP_TM))) {
            ediRcvInfo.put(COL_EDI_PICK_UP_TM, wrkProc.get(COL_EDI_PICK_UP_TM));
        }
        // END 2018/02/02 T.Hakodate [QC#23858,MOD]
    }

    private void sortPodWrkList(List<Map<String, Object>> wmsPodList) {
        Collections.sort(wmsPodList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> rec1, Map<String, Object> rec2) {
                String colName1 = (String) rec1.get(COL_EDI_FLD_TXT_04);
                String colName2 = (String) rec2.get(COL_EDI_FLD_TXT_04);

                if (colName1 == null) {
                    return -1;
                } else if (colName2 == null) {
                    return 1;
                } else {
                    return (colName1.compareTo(colName2));
                }
            }
        });
        Collections.sort(wmsPodList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> rec1, Map<String, Object> rec2) {
                String colName1 = (String) rec1.get(COL_EDI_FLD_TXT_03);
                String colName2 = (String) rec2.get(COL_EDI_FLD_TXT_03);

                if (colName1 == null) {
                    return -1;
                } else if (colName2 == null) {
                    return 1;
                } else {
                    return (colName1.compareTo(colName2));
                }
            }
        });
        Collections.sort(wmsPodList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> rec1, Map<String, Object> rec2) {
                String colName1 = (String) rec1.get(COL_EDI_TM_CD);
                String colName2 = (String) rec2.get(COL_EDI_TM_CD);

                if (colName1 == null) {
                    return -1;
                } else if (colName2 == null) {
                    return 1;
                } else {
                    return (colName1.compareTo(colName2));
                }
            }
        });
        Collections.sort(wmsPodList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> rec1, Map<String, Object> rec2) {
                String colName1 = (String) rec1.get(COL_EDI_FLD_TXT_01);
                String colName2 = (String) rec2.get(COL_EDI_FLD_TXT_01);

                if (colName1 == null) {
                    return -1;
                } else if (colName2 == null) {
                    return 1;
                } else {
                    return (colName1.compareTo(colName2));
                }
            }
        });
        Collections.sort(wmsPodList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> rec1, Map<String, Object> rec2) {
                String colName1 = (String) rec1.get(COL_EDI_PRO_NUM);
                String colName2 = (String) rec2.get(COL_EDI_PRO_NUM);

                if (colName1 == null) {
                    return -1;
                } else if (colName2 == null) {
                    return 1;
                } else {
                    return (colName1.compareTo(colName2));
                }
            }
        });
    }
    // END 2018/01/17 S.Katsuma [QC#23056,ADD]

    // START 2019/03/29 T.Ogura [QC#30594,ADD]
    private void outputApiCallErr(String msgId, String[] msgParam, Map<String, Object> wmsPodData) {
        StringBuilder builder = new StringBuilder();
        builder.append(S21MessageFunc.clspGetMessage(msgId, msgParam));
        builder.append(VAL_BLANK);
        builder.append(generateWmsPodKeyInfo(wmsPodData));

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }
    // END   2019/03/29 T.Ogura [QC#30594,ADD]

    // START 2019/03/29 T.Ogura [QC#30594,ADD]
    private String generateWmsPodKeyInfo(Map<String, Object> wmsPodData) {
        StringBuilder builder = new StringBuilder();
        builder.append("Table:[");
        builder.append(TBL_NLBI1320_01);
        builder.append("]");
        builder.append(VAL_BLANK);
        builder.append("Search Key:[");
        builder.append(this.interfaceId);
        builder.append(",");
        builder.append((String) wmsPodData.get(COL_TRANSACTION_ID));
        builder.append("]");
        return builder.toString();
    }
    // END   2019/03/29 T.Ogura [QC#30594,ADD]

    /**
     * create Complete POD_ADDR_WRK, POD_STS_WRK from Receiving IF(NLBI1320_01)
     * @param execParam S21SsmExecutionParameter
     * @param tranId BigDecimal transactionID
     * @return return code
     */
    private int createETAInterface(S21SsmExecutionParameter execParam, BigDecimal tranId) {

        PreparedStatement podEtaStsRs = null;
        ResultSet rsETARcvList = null;

        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_INTERFACE_ID, this.interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_EDI_FLD_TXT_57, this.etaStatusFld57);

            podEtaStsRs = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_EDI_RCV_LIST, queryParam, execParam);
            rsETARcvList = podEtaStsRs.executeQuery();

            List<Map<String, Object>> wrkProcList = new ArrayList<Map<String,Object>>();
            while (rsETARcvList.next()) {
                Map<String, Object> ediEtaInfo = new HashMap<String, Object>();
                setWmsPodData(ediEtaInfo, rsETARcvList, "0");
                wrkProcList.add(ediEtaInfo);
            }

            BigDecimal ediTrxId = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_POD_CTRL_SQ");

            // Break Key : [TRACKING NUMBER]/[EDI DATE]/[EDI
            // TIME]/[EDI NUMBER]/[EDI SET NUMBER]
            String beforePodKey = VAL_EMPTY;
            String nowPodKey = VAL_EMPTY;

            Map<String, Object> ediRcvInfo = new HashMap<String, Object>();
            int ediStCtrlCdSeq = ONE;

            for (Map<String, Object> wrkProc : wrkProcList) {

                StringBuilder podKeyBuff = new StringBuilder();
                podKeyBuff.append(wrkProc.get(COL_EDI_PRO_NUM)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_01)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_02)).append(VAL_SLASH)
                        .append(wrkProc.get(COL_EDI_FLD_TXT_03)).append(VAL_SLASH).append(wrkProc.get(COL_EDI_FLD_TXT_04));
                nowPodKey = podKeyBuff.toString();
                podKeyBuff.setLength(ZERO);

                if (!VAL_EMPTY.equals(beforePodKey) && !nowPodKey.equals(beforePodKey)) {

                    // insert POD_ADDR_WRK
                    createPodAddrWrk(ediRcvInfo);

                    // insert POD_STS_WRK
                    createPodStsWrk(ediRcvInfo);

                    ediRcvInfo.clear();

                    // increment Seq for EDI_ST_CTRL_CODE
                    ediStCtrlCdSeq++;

                    normalRecCount++;
                }

                String ediStCtrlCd = ediTrxId.toPlainString() + ZYPCommonFunc.leftPad(String.valueOf(ediStCtrlCdSeq), LG_EDI_ST_CTRL_CD, VAL_ZERO);
                if (ediStCtrlCd.length() > 10) {
                    ediStCtrlCd = ediStCtrlCd.substring(ediStCtrlCd.length() - 10);
                }
                ediRcvInfo.put(COL_EDI_ST_CTRL_CD, ediStCtrlCd);

                String ediTrxIdWk = ediTrxId.toPlainString();
                if (ediTrxIdWk.length() > 10) {
                    ediTrxIdWk = ediTrxIdWk.substring(ediTrxIdWk.length() - 10);
                }
                ediRcvInfo.put(COL_EDI_GS_CTRL_CD, ediTrxIdWk);

                setWmsPodData(ediRcvInfo, wrkProc);
                beforePodKey = nowPodKey;
            }

            if (ediRcvInfo.size() > ZERO) {
                // insert POD_ADDR_WRK
                createPodAddrWrk(ediRcvInfo);

                // insert POD_STS_WRK
                createPodStsWrk(ediRcvInfo);

                // increment Seq for EDI_ST_CTRL_CODE
                ediStCtrlCdSeq++;

                ediRcvInfo.clear();

                normalRecCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(podEtaStsRs, rsETARcvList);
        }

        return ST_NORMAL_END;
    }
}
