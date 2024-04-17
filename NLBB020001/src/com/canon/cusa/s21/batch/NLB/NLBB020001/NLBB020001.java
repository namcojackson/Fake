package com.canon.cusa.s21.batch.NLB.NLBB020001;

import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.CARR_TP_CD_FEDEX;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_AFRT_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_CMTE_DT_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_CMTE_TS;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_RCPNT_ADDR;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_RCPNT_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_RCPNT_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_RCPNT_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_RCPNT_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SCAN_CTY_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SCAN_DT_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SCAN_STS_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SCAN_ST_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SCAN_TP_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SCAN_TS;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SHPPR_ADDR;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SHPPR_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SHPPR_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SHPPR_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_SHPPR_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_TRX_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_EDI_WT_UOM_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.COL_TRX_LINE_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.EDI_DUMMY_DATE;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.LG_EDI_ST_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.NLGM0045E;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.NLGM0049E;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.PRAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.PRAM_NM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.TBL_NLBI1310_01;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.TBL_POD_ADDR_WRK;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.TBL_POD_STS_WRK;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.TP_PROOF_OF_DELIVERY;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.TP_PROOF_OF_PICKUP;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.VAL_BLANK;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.VAL_EDI_LN_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.VAL_EDI_TM_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.VAL_EDI_TP_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB020001.constant.NLBB020001Constant.VAL_ZERO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * Batch Program Class for Receive EDI214.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2016   CITS            T.Wada          Create          
 * 10/11/2016   CITS            T.Hakodate      UPDATE          QC#15066
 * 01/16/2018   CITS            S.Katsuma       Update          QC#23056
 * 02/02/2018   CITS            T.Hakodate      Update          QC#23921
 * 02/23/2018   CITS            S.Katsuma       Update          QC#24011
 * 03/22/2019   Fujitsu         T.Ogura         Update          QC#30565
 * 03/29/2019   Fujitsu         T.Ogura         Update          QC#30594
 * 05/10/2019   CITS            K.Ogino         Update          QC#31201
 * 05/24/2019   CITS            T.Tokutomi      Update          QC#50528
 *</pre>
 */
public class NLBB020001 extends S21BatchMain {

    /** Transaction Id for POD IF Table */
    private BigDecimal podTrxId = null;

    /** Seq for EDI_ST_CTRL_CODE */
    private BigDecimal ediStCtrlCdSeq = BigDecimal.ONE;

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    // private String glblCmpyCd = null;
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId = null;

    /** Counter of Records: Successful */
    private int normalRecCount = 0;

    /** Counter of Records: Error */
    private int totalErrCount = 0;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess = null;

    // START 2018/01/16 S.Katsuma [QC#23056,ADD]
    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Sales Date */
    private String salesDate = null;

    // END 2018/01/16 S.Katsuma [QC#23056,ADD]

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {
        new NLBB020001().executeBatch(NLBB020001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        trxAccess = new S21TransactionTableAccessor();

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        // START 2018/01/16 S.Katsuma [QC#23056,ADD]
        errMsgList = new ArrayList<Map<String, String>>();
        // START 2018/01/16 S.Katsuma [QC#23056,ADD]

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

        // START 2018/01/16 S.Katsuma [QC#23056,ADD]
        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        // END 2018/01/16 S.Katsuma [QC#23056,ADD]
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIds.length == 0) {
            return;
        }

        // Loop Transaction Id Unit
        try {
            for (BigDecimal tranId : tranIds) {

                // POD (Proof of Delivery)
                List<Map<String, Object>> wrkProcList = createPodInterface(execParam, tranId);

                // START 2017/01/16 S.Katsuma [QC#23056,ADD]
                // POD (Proof of Delivery) WRK Data
                createPodWrkInterface(execParam, tranId, wrkProcList);
                // END 2017/01/16 S.Katsuma [QC#23056,ADD]

                // Processed Flg of transaction ID update to the
                // Processed
                trxAccess.endIntegrationProcess(this.interfaceId, tranId);
                commit();
            }
        } finally {
            // START 2018/01/17 S.Katsuma [QC#23056,ADD]
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
                commit();
            }
            // END 2018/01/17 S.Katsuma [QC#23056,ADD]
        }
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
     * createPodWrkInterface
     * @param execParam
     */
    private void createPodWrkInterface(S21SsmExecutionParameter execParam, BigDecimal tranId, List<Map<String, Object>> wrkProcList) {
        PreparedStatement prdStmtPod = null;
        ResultSet rsPod = null;

        try {

            Map<String, Object> queryParam = setPodDataParams(tranId);
            queryParam.put("isPodWrk", "Y");

            prdStmtPod = ssmLLClient.createPreparedStatement("getPodData", queryParam, execParam);
            rsPod = prdStmtPod.executeQuery();

            while (rsPod.next()) {

                // Get WMS Upload [POD] Header
                Map<String, Object> wmsPodData = new HashMap<String, Object>();
                // START 2018/01/17 S.Katsuma [QC#23056,ADD]
                setWmsPodData(wmsPodData, rsPod);
                // END 2018/01/17 S.Katsuma [QC#23056,ADD]

                // createPodData
                createPodData(wmsPodData);
                normalRecCount++;

                // increment Seq for EDI_ST_CTRL_CODE
                ediStCtrlCdSeq = ediStCtrlCdSeq.add(BigDecimal.ONE);

            }

            // START 2018/01/17 S.Katsuma [QC#23056,ADD]
            for (Map<String, Object> wmsPodData : wrkProcList) {
                // createPodData
                createPodData(wmsPodData);
                normalRecCount++;

                // increment Seq for EDI_ST_CTRL_CODE
                ediStCtrlCdSeq = ediStCtrlCdSeq.add(BigDecimal.ONE);
            }
            // END 2018/01/17 S.Katsuma [QC#23056,ADD]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtPod, rsPod);
        }

    }

    /**
     * createPodData
     * @param wmsPodData
     * @param ediLnCtrlCdSeq
     */
    private void createPodData(Map<String, Object> wmsPodData) {

        POD_ADDR_WRKTMsg podAddrWrk = new POD_ADDR_WRKTMsg();
        POD_STS_WRKTMsg podStsWrk = new POD_STS_WRKTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTrxId, (String) wmsPodData.get(COL_TRANSACTION_ID));

        // EDI_SQ_ID (SeqID（AutoSeq) EDI_POD_ADDR_SQ.nextval)
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_ADDR_SQ);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediSqId, ediSqId.toPlainString());

        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.procStsCd, VAL_ZERO);

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTpCd, VAL_EDI_TP_CD);

        // EDI_GS_CTRL_CD (For each batch processing of once, Sql ID
        // numbering)
        String ediGsCtrlCdWk = podTrxId.toPlainString();
        if (ediGsCtrlCdWk.length() > 10) {
            ediGsCtrlCdWk = ediGsCtrlCdWk.substring(ediGsCtrlCdWk.length() - 10);
        }
        // START 2019/03/22 T.Ogura [QC#30565,MOD]
        // ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd,
        // podTrxId.toPlainString());
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd, ediGsCtrlCdWk);
        // END 2019/03/22 T.Ogura [QC#30565,MOD]

        // EDI_ST_CTRL_CD
        String ediStCtrlCd = podTrxId.toPlainString() + ZYPCommonFunc.leftPad(ediStCtrlCdSeq.toString(), LG_EDI_ST_CTRL_CD, VAL_ZERO);
        if (ediStCtrlCd.length() > 10) {
            ediStCtrlCd = ediStCtrlCd.substring(ediStCtrlCd.length() - 10);
        }

        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediStCtrlCd, ediStCtrlCd);

        // EDI_SHPPR_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, (String) wmsPodData.get(COL_EDI_SHPPR_NM));
        // EDI_SHPPR_ADDR
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprAddr, (String) wmsPodData.get(COL_EDI_SHPPR_ADDR));
        // EDI_SHPPR_CTY_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprCtyNm, (String) wmsPodData.get(COL_EDI_SHPPR_CTY_NM));
        // EDI_SHPPR_ST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprStCd, (String) wmsPodData.get(COL_EDI_SHPPR_ST_CD));
        // EDI_SHPPR_POST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprPostCd, (String) wmsPodData.get(COL_EDI_SHPPR_POST_CD));

        // EDI_CNSGN_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnNm, (String) wmsPodData.get(COL_EDI_RCPNT_NM));
        // EDI_CNSGN_ADDR
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnAddr, (String) wmsPodData.get(COL_EDI_RCPNT_ADDR));
        // EDI_CNSGN_CTY_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnCtyNm, (String) wmsPodData.get(COL_EDI_RCPNT_CTY_NM));
        // EDI_CNSGN_ST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnStCd, (String) wmsPodData.get(COL_EDI_RCPNT_ST_CD));
        // EDI_CNSGN_POST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnPostCd, (String) wmsPodData.get(COL_EDI_RCPNT_POST_CD));
        // POD_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.podSrcTpCd, "01");

        // //////////////////////////////////////////////
        // insert POD_ADDR_WRK
        // //////////////////////////////////////////////
        EZDTBLAccessor.insert(podAddrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podAddrWrk.getReturnCode())) {
            outputErr(NLGM0045E, new String[] {TBL_POD_ADDR_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_ADDR_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
        }

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.glblCmpyCd, podAddrWrk.glblCmpyCd.getValue());
        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTrxId, podAddrWrk.ediTrxId.getValue());
        // EDI_SQ_ID
        BigDecimal ediSqIdSts = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_STS_SQ);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediSqId, ediSqIdSts.toPlainString());
        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.procStsCd, podAddrWrk.procStsCd.getValue());
        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTpCd, podAddrWrk.ediTpCd.getValue());
        // EDI_GS_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediGsCtrlCd, podAddrWrk.ediGsCtrlCd.getValue());
        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStCtrlCd, podAddrWrk.ediStCtrlCd.getValue());
        // EDI_LN_CTRL_CD
        String ediLnCtrlCd = VAL_EDI_LN_CTRL_CD;
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLnCtrlCd, ediLnCtrlCd);
        // EDI_PRO_NUM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum, wmsPodData.get(COL_EDI_AFRT_NUM).toString());

        // EDI_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCd, (String) wmsPodData.get(COL_EDI_SCAN_TP_TXT));
        // EDI_STS_DT
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsDt, (String) wmsPodData.get(COL_EDI_SCAN_DT_TXT));
        // EDI_STS_TM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsTm, (String) wmsPodData.get(COL_EDI_SCAN_TS));

        // EDI_STS_RSN_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsRsnCd, (String) wmsPodData.get(COL_EDI_SCAN_STS_TXT));
        // EDI_STS_CTY_NM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCtyNm, (String) wmsPodData.get(COL_EDI_SCAN_CTY_TXT));
        // EDI_STS_ST_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsStCd, (String) wmsPodData.get(COL_EDI_SCAN_ST_TXT));

        // EDI_WT_UNIT_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediWtUnitCd, (String) wmsPodData.get(COL_EDI_WT_UOM_TXT));

        // EDI_PICK_UP_DT
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediPickUpDt, (String) wmsPodData.get(COL_EDI_SCAN_DT_TXT));

        // START 2018/02/23 S.Katsuma [QC#24011,ADD]
        // EDI_TM_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTmCd, VAL_EDI_TM_CD);
        // END 2018/02/23 S.Katsuma [QC#24011,ADD]

        // //////////////////////////////////////////////
        // insert POD_STS_WRK
        EZDTBLAccessor.insert(podStsWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podStsWrk.getReturnCode())) {
            outputErr(NLGM0045E, new String[] {TBL_POD_STS_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_STS_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
        }
    }

    /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        // START 2018/01/17 S.Katsuma [QC#23056,ADD]
        errMsgList.add(mailParam);
        // END 2018/01/17 S.Katsuma [QC#23056,ADD]

        S21InfoLogOutput.println(msgId, msgParam);
    }

    // START 2017/01/16 S.Katsuma [QC#23056,ADD]
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
            queryParam.put("isPodWrk", "N");

            prdStmtPod = ssmLLClient.createPreparedStatement("getPodData", queryParam, execParam);
            rsPod = prdStmtPod.executeQuery();
            while (rsPod.next()) {

                // Get WMS Upload [POD] Header
                Map<String, Object> wmsPodData = new HashMap<String, Object>();
                wmsPodData.put(COL_TRANSACTION_ID, tranId.toPlainString());
                wmsPodData.put(COL_EDI_AFRT_NUM, rsPod.getString(COL_EDI_AFRT_NUM));
                String ediScanTpTxt = rsPod.getString(COL_EDI_SCAN_TP_TXT);
                wmsPodData.put(COL_EDI_SCAN_TP_TXT, ediScanTpTxt);
                wmsPodData.put(COL_EDI_SCAN_DT_TXT, rsPod.getString(COL_EDI_SCAN_DT_TXT));
                wmsPodData.put(COL_EDI_SCAN_TS, rsPod.getString(COL_EDI_SCAN_TS));

                boolean flResult = false;
                String queryName = null;
                if (ZYPCommonFunc.hasValue(ediScanTpTxt) && TP_PROOF_OF_PICKUP.equals(ediScanTpTxt)) {
                    // queryName = "getRWSNumByTrackingNum";
                    // Inbound data is skipped because Can't get RWS#
                    // by Tracking# at 2018/01/22
                    setWmsPodData(wmsPodData, rsPod);
                    wrkProcList.add(wmsPodData);
                    // QC#31201 ADD ETA WRK Map Deep copy
                    Map<String, Object> wmsEtaData = new HashMap<String, Object>();
                    Set<String> keySet = wmsPodData.keySet();
                    Iterator it = keySet.iterator();
                    while (it.hasNext()) {
                        String key = String.valueOf(it.next());
                        wmsEtaData.put(key, wmsPodData.get(key));
                    }
                    wmsEtaData.put(COL_EDI_SCAN_DT_TXT, rsPod.getString(COL_EDI_CMTE_DT_TXT));
                    wmsEtaData.put(COL_EDI_SCAN_TS, rsPod.getString(COL_EDI_CMTE_TS));
                    wmsEtaData.put(COL_EDI_SCAN_TP_TXT, POD_STS.ESTIMATED_DELIVERY_APPOINTMENT_DATE_AND_OR_OR_TIME);
                    wrkProcList.add(wmsEtaData);
                    continue;
                } else if (ZYPCommonFunc.hasValue(ediScanTpTxt) && TP_PROOF_OF_DELIVERY.equals(ediScanTpTxt)) {
                    queryName = "getSoNumByTrackingNum";
                }
                flResult = getTrxNum(execParam, wmsPodData, queryName);

                if (!flResult) {
                    setWmsPodData(wmsPodData, rsPod);
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

        if (!ZYPCommonFunc.hasValue(podTrxId)) {
            podTrxId = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_POD_CTRL_SQ");
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("interfaceId", this.interfaceId);
        queryParam.put("transactionId", tranId);
        queryParam.put("ediScanTpTxt", new String[] {TP_PROOF_OF_PICKUP, TP_PROOF_OF_DELIVERY });
        // QC#50528
        queryParam.put("dummyDt", EDI_DUMMY_DATE);
        queryParam.put("salesDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        return queryParam;
    }

    private boolean getTrxNum(S21SsmExecutionParameter execParam, Map<String, Object> wmsPodData, String queryName) {
        PreparedStatement trxStmtPod = null;
        ResultSet rsTrx = null;
        boolean flResult = false;

        try {
            // Get SO Num or RWS Num
            Map<String, Object> trxQueryParam = new HashMap<String, Object>();
            trxQueryParam.put("glblCmpyCd", this.glblCmpyCd);
            trxQueryParam.put("proNum", (String) wmsPodData.get(COL_EDI_AFRT_NUM));
            trxQueryParam.put("carrTpCd", CARR_TP_CD_FEDEX);

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
                    // outputErr(NLGM0041E, new String[]
                    // {COL_EDI_TRX_ID, TBL_NLBI1310_01,
                    // this.interfaceId + "," + (String)
                    // wmsPodData.get(COL_TRANSACTION_ID) }); //
                    // 2019/03/29 T.Ogura [QC#30594,DEL]
                    continue;
                }
                normalRecCount++;
            }
            // QC#31201
            if (flResult) {
                createPodDataComp(wmsPodData);
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
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.proNum, (String) wmsPodData.get(COL_EDI_AFRT_NUM));

        String ediScanTpTxt = (String) wmsPodData.get(COL_EDI_SCAN_TP_TXT);
        String carrRsnCd = null;
        String inbdOtbdCd = null;
        if (ZYPCommonFunc.hasValue(ediScanTpTxt) && TP_PROOF_OF_PICKUP.equals(ediScanTpTxt)) {
            carrRsnCd = CARR_RSN.PICKED_UP;
            inbdOtbdCd = "1";
        } else if (ZYPCommonFunc.hasValue(ediScanTpTxt) && TP_PROOF_OF_DELIVERY.equals(ediScanTpTxt)) {
            carrRsnCd = CARR_RSN.DELIVERED;
            inbdOtbdCd = "2";
        }
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, carrRsnCd);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.inbdOtbdCd, inbdOtbdCd);

        String ediScanDtTxt = (String) wmsPodData.get(COL_EDI_SCAN_DT_TXT);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.xxRqstDt, ediScanDtTxt);
        String ediScanTs = (String) wmsPodData.get(COL_EDI_SCAN_TS);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.actlDelyTm, ediScanTs);
        if (ZYPCommonFunc.hasValue(ediScanDtTxt) && ZYPCommonFunc.hasValue(ediScanTs)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.updTs, ediScanDtTxt + ediScanTs);
        }
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.podSrcTpCd, POD_SRC_TP._214);

        // START 2018/02/02 T.Hakodate [QC#23921,MOD]
        if (ZYPCommonFunc.hasValue(ediScanTpTxt)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.ediStsCd, ediScanTpTxt);
        }
        // END 2018/02/02 T.Hakodate [QC#23921,MOD]

        NLZC405001 nlzc405001 = new NLZC405001();
        nlzc405001.execute(nlzc405001PMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc405001PMsg);

        if (msgList.size() > 0) {
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (msgId.endsWith("E")) {
                    outputApiCallErr(msgId, null, wmsPodData); // 2019/03/29
                                                               // T.Ogura
                                                               // [QC#30594,ADD]
                    return false;
                }
            }
        }
        return true;
    }

    private void setWmsPodData(Map<String, Object> wmsPodData, ResultSet rsPod) throws SQLException {
        wmsPodData.put(COL_TRANSACTION_ID, rsPod.getString(COL_TRANSACTION_ID));
        wmsPodData.put(COL_EDI_SHPPR_NM, rsPod.getString(COL_EDI_SHPPR_NM));
        wmsPodData.put(COL_EDI_SHPPR_ADDR, rsPod.getString(COL_EDI_SHPPR_ADDR));
        wmsPodData.put(COL_EDI_SHPPR_CTY_NM, rsPod.getString(COL_EDI_SHPPR_CTY_NM));
        wmsPodData.put(COL_EDI_SHPPR_ST_CD, rsPod.getString(COL_EDI_SHPPR_ST_CD));
        wmsPodData.put(COL_EDI_SHPPR_POST_CD, rsPod.getString(COL_EDI_SHPPR_POST_CD));
        wmsPodData.put(COL_EDI_RCPNT_NM, rsPod.getString(COL_EDI_RCPNT_NM));
        wmsPodData.put(COL_EDI_RCPNT_ADDR, rsPod.getString(COL_EDI_RCPNT_ADDR));
        wmsPodData.put(COL_EDI_RCPNT_CTY_NM, rsPod.getString(COL_EDI_RCPNT_CTY_NM));
        wmsPodData.put(COL_EDI_RCPNT_ST_CD, rsPod.getString(COL_EDI_RCPNT_ST_CD));
        wmsPodData.put(COL_EDI_RCPNT_POST_CD, rsPod.getString(COL_EDI_RCPNT_POST_CD));
        wmsPodData.put(COL_EDI_AFRT_NUM, rsPod.getString(COL_EDI_AFRT_NUM));
        wmsPodData.put(COL_EDI_SCAN_TP_TXT, rsPod.getString(COL_EDI_SCAN_TP_TXT));
        wmsPodData.put(COL_EDI_SCAN_DT_TXT, rsPod.getString(COL_EDI_SCAN_DT_TXT));
        wmsPodData.put(COL_EDI_SCAN_TS, rsPod.getString(COL_EDI_SCAN_TS));
        wmsPodData.put(COL_EDI_SCAN_STS_TXT, rsPod.getString(COL_EDI_SCAN_STS_TXT));
        wmsPodData.put(COL_EDI_SCAN_CTY_TXT, rsPod.getString(COL_EDI_SCAN_CTY_TXT));
        wmsPodData.put(COL_EDI_SCAN_ST_TXT, rsPod.getString(COL_EDI_SCAN_ST_TXT));
        // START 2018/02/23 S.Katsuma [QC#24011,ADD]
        wmsPodData.put(COL_EDI_WT_UOM_TXT, rsPod.getString(COL_EDI_WT_UOM_TXT));
        // END 2018/02/23 S.Katsuma [QC#24011,ADD]
    }

    // END 2017/01/16 S.Katsuma [QC#23056,ADD]

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

    // END 2019/03/29 T.Ogura [QC#30594,ADD]

    // START 2019/03/29 T.Ogura [QC#30594,ADD]
    private String generateWmsPodKeyInfo(Map<String, Object> wmsPodData) {
        StringBuilder builder = new StringBuilder();
        builder.append("Table:[");
        builder.append(TBL_NLBI1310_01);
        builder.append("]");
        builder.append(VAL_BLANK);
        builder.append("Search Key:[");
        builder.append(this.interfaceId);
        builder.append(",");
        builder.append((String) wmsPodData.get(COL_TRANSACTION_ID));
        builder.append("]");
        return builder.toString();
    }

    // END 2019/03/29 T.Ogura [QC#30594,ADD]

    /**
     * createPodDataComp. ADD QC#31201
     * @param wmsPodData
     * @param ediLnCtrlCdSeq
     */
    private void createPodDataComp(Map<String, Object> wmsPodData) {

        POD_ADDR_WRKTMsg podAddrWrk = new POD_ADDR_WRKTMsg();
        POD_STS_WRKTMsg podStsWrk = new POD_STS_WRKTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTrxId, (String) wmsPodData.get(COL_TRANSACTION_ID));

        // EDI_SQ_ID (SeqID（AutoSeq) EDI_POD_ADDR_SQ.nextval)
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_ADDR_SQ);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediSqId, ediSqId.toPlainString());

        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.procStsCd, "1");

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTpCd, VAL_EDI_TP_CD);

        // EDI_GS_CTRL_CD (For each batch processing of once, Sql ID
        // numbering)
        String ediGsCtrlCdWk = podTrxId.toPlainString();
        if (ediGsCtrlCdWk.length() > 10) {
            ediGsCtrlCdWk = ediGsCtrlCdWk.substring(ediGsCtrlCdWk.length() - 10);
        }
        // START 2019/03/22 T.Ogura [QC#30565,MOD]
        // ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd,
        // podTrxId.toPlainString());
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd, ediGsCtrlCdWk);
        // END 2019/03/22 T.Ogura [QC#30565,MOD]

        // EDI_ST_CTRL_CD
        String ediStCtrlCd = podTrxId.toPlainString() + ZYPCommonFunc.leftPad(ediStCtrlCdSeq.toString(), LG_EDI_ST_CTRL_CD, VAL_ZERO);
        if (ediStCtrlCd.length() > 10) {
            ediStCtrlCd = ediStCtrlCd.substring(ediStCtrlCd.length() - 10);
        }

        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediStCtrlCd, ediStCtrlCd);

        // EDI_SHPPR_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, (String) wmsPodData.get(COL_EDI_SHPPR_NM));
        // EDI_SHPPR_ADDR
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprAddr, (String) wmsPodData.get(COL_EDI_SHPPR_ADDR));
        // EDI_SHPPR_CTY_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprCtyNm, (String) wmsPodData.get(COL_EDI_SHPPR_CTY_NM));
        // EDI_SHPPR_ST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprStCd, (String) wmsPodData.get(COL_EDI_SHPPR_ST_CD));
        // EDI_SHPPR_POST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprPostCd, (String) wmsPodData.get(COL_EDI_SHPPR_POST_CD));

        // EDI_CNSGN_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnNm, (String) wmsPodData.get(COL_EDI_RCPNT_NM));
        // EDI_CNSGN_ADDR
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnAddr, (String) wmsPodData.get(COL_EDI_RCPNT_ADDR));
        // EDI_CNSGN_CTY_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnCtyNm, (String) wmsPodData.get(COL_EDI_RCPNT_CTY_NM));
        // EDI_CNSGN_ST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnStCd, (String) wmsPodData.get(COL_EDI_RCPNT_ST_CD));
        // EDI_CNSGN_POST_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediCnsgnPostCd, (String) wmsPodData.get(COL_EDI_RCPNT_POST_CD));
        // POD_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.podSrcTpCd, "01");

        // //////////////////////////////////////////////
        // insert POD_ADDR_WRK
        // //////////////////////////////////////////////
        EZDTBLAccessor.insert(podAddrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podAddrWrk.getReturnCode())) {
            outputErr(NLGM0045E, new String[] {TBL_POD_ADDR_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_ADDR_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
        }

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.glblCmpyCd, podAddrWrk.glblCmpyCd.getValue());
        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTrxId, podAddrWrk.ediTrxId.getValue());
        // EDI_SQ_ID
        BigDecimal ediSqIdSts = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_STS_SQ);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediSqId, ediSqIdSts.toPlainString());
        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.procStsCd, podAddrWrk.procStsCd.getValue());
        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTpCd, podAddrWrk.ediTpCd.getValue());
        // EDI_GS_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediGsCtrlCd, podAddrWrk.ediGsCtrlCd.getValue());
        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStCtrlCd, podAddrWrk.ediStCtrlCd.getValue());
        // EDI_LN_CTRL_CD
        String ediLnCtrlCd = VAL_EDI_LN_CTRL_CD;
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLnCtrlCd, ediLnCtrlCd);
        // EDI_PRO_NUM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum, wmsPodData.get(COL_EDI_AFRT_NUM).toString());

        // EDI_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCd, (String) wmsPodData.get(COL_EDI_SCAN_TP_TXT));
        // EDI_STS_DT
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsDt, (String) wmsPodData.get(COL_EDI_SCAN_DT_TXT));
        // EDI_STS_TM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsTm, (String) wmsPodData.get(COL_EDI_SCAN_TS));

        // EDI_STS_RSN_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsRsnCd, (String) wmsPodData.get(COL_EDI_SCAN_STS_TXT));
        // EDI_STS_CTY_NM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCtyNm, (String) wmsPodData.get(COL_EDI_SCAN_CTY_TXT));
        // EDI_STS_ST_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsStCd, (String) wmsPodData.get(COL_EDI_SCAN_ST_TXT));

        // EDI_WT_UNIT_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediWtUnitCd, (String) wmsPodData.get(COL_EDI_WT_UOM_TXT));

        // EDI_PICK_UP_DT
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediPickUpDt, (String) wmsPodData.get(COL_EDI_SCAN_DT_TXT));

        // START 2018/02/23 S.Katsuma [QC#24011,ADD]
        // EDI_TM_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTmCd, VAL_EDI_TM_CD);
        // END 2018/02/23 S.Katsuma [QC#24011,ADD]

        // //////////////////////////////////////////////
        // insert POD_STS_WRK
        EZDTBLAccessor.insert(podStsWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podStsWrk.getReturnCode())) {
            outputErr(NLGM0045E, new String[] {TBL_POD_STS_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_STS_WRK, TBL_NLBI1310_01, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
        }
    }
}
