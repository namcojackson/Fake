/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB230001;

import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ASL_DTL_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_MRP_INFO_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_MRP_PLN_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_PARTS;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_RTL_WH_CATG_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.DB_PARAM_WH_OWNR_MNX;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.EMAIL_PARAM_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.EMAIL_PARAM_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.IF_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.IF_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.MAX_INVTY_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.MIN_INVTY_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ML_GRP_CD_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ML_GRP_CD_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ML_LANG;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ML_LANG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ML_TMPL_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.MRP_INFO_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.MRP_PLN_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.NPAB2300_MNX_MRP_PLN_NM_PARTS;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.NPAM1171E;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.NPAM1172E;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.NPZM0161E;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.PARAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.PARAM_NM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RPLSH_DLY_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RPLSH_FRI_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RPLSH_MON_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RPLSH_THU_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RPLSH_TUE_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RPLSH_WED_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.WH_RG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ZZBM0009I;
import static com.canon.cusa.s21.batch.NPA.NPAB230001.constant.NPAB230001Constant.ZZM9000E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MRP_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Baxter IF for MINMAX
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/11/2021   CITS            K.Ogino         Create          QC#58990
 * 12/15/2021   CITS            K.Ogino         Update          QC#58990-2
 * 12/23/2021   CITS            K.Ogino         Update          QC#58990-3
 * 02/03/2022   CITS            K.Ogino         Update          QC#59649
 * 07/11/2022   CITS            K.Ogino         Update          QC#60261
 *</pre>
 */
public class NPAB230001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<String> errMsgList = new ArrayList<String>();

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** Sales Date */
    private String salesDate = null;

    // *********************************************************
    // Instance Variables: Constant values on load
    // *********************************************************
    /** var_char_const_val */
    private String varVal = "";

    /**
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NPAB230001().executeBatch(NPAB230001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of variable
        successCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();

        // Global Company Code
        this.glblCmpyCd = prof.getGlobalCompanyCode();

        // Interface ID
        this.interfaceId = getInterfaceID();

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // VAR_CHAR_CONST
        this.varVal = ZYPCodeDataUtil.getVarCharConstValue(NPAB2300_MNX_MRP_PLN_NM_PARTS, this.glblCmpyCd);

        // Check on input parameter
        checkParameter();
    }

    @Override
    protected void mainRoutine() {

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIds.length <= 0) {
            return;
        }

        try {
            for (BigDecimal trxId : tranIds) {
                // Mod QC#60261
                cratOrUpdMrpInfo(trxId, false);
                cratOrUpdMrpInfo(trxId, true);

                // Update the flag to processed in any case - success/fail -
                trxAccess.endIntegrationProcess(this.interfaceId, trxId);

                commit();
            }

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                // ERROR MAIL
                sendErrMail();
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {

        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount) });

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_GLBL_CMPY_CD });
        }

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_INTERFACE_ID });
        }
    }

    // Mod QC#60261
    private void cratOrUpdMrpInfo(BigDecimal trxId, boolean isTech) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(DB_PARAM_INTERFACE_ID, this.interfaceId);
        bindParam.put(DB_PARAM_TRANSACTION_ID, trxId);
        bindParam.put(DB_PARAM_WH_OWNR_MNX, WH_OWNR.MNX);
        // QC#59649 Mod
        // bindParam.put(DB_PARAM_DS_COND_CONST_GRP_ID, NPAB2300_MINMAX_SUP);
        bindParam.put(DB_PARAM_PARTS, this.varVal);
        bindParam.put(DB_PARAM_SALES_DATE, this.salesDate);
        bindParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        // Mod QC#60261
        String execSql = "";
        if (isTech) {
            execSql = "findIFDataForTech";
            bindParam.put(DB_PARAM_RTL_WH_CATG_LIST, Arrays.asList(new String[]{RTL_WH_CATG.TECH_CAR_STOCK, RTL_WH_CATG.CUSTOMER}));
        } else {
            bindParam.put("partsDepo", RTL_WH_CATG.PARTS_DEPO);
            bindParam.put(DB_PARAM_RTL_WH_CATG_LIST, Arrays.asList(new String[]{RTL_WH_CATG.TECH_CAR_STOCK, RTL_WH_CATG.CUSTOMER}));
            execSql = "findIFData";
        }
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            // Mod QC#60261
            stmt = ssmLLClient.createPreparedStatement(execSql, bindParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                // IF Data Validation Check. Mod QC#60261
                boolean isErr = isValidIfData(rs, isTech);
                if (isErr) {
                    continue;
                }

                BigDecimal mrpInfoPk = rs.getBigDecimal(MRP_INFO_PK);

                // Check existing MRP_INFO. Mod QC#60261
                if (!ZYPCommonFunc.hasValue(mrpInfoPk)) {
                    checkMrpInfoAndTerminate(rs, isTech);
                }

                if (ZYPCommonFunc.hasValue(mrpInfoPk)) {
                    // update
                    updateMrpInfo(rs, mrpInfoPk);
                } else {
                    // insert
                    insertMrpInfo(rs, isTech);
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertMrpInfo(ResultSet rs, boolean isTech) throws SQLException {

        // QC#58990-3. find Existsing MRP_INFO
        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(DB_PARAM_MRP_PLN_NM, rs.getString(MRP_PLN_NM));
        queryParam.put(DB_PARAM_MRP_INFO_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);

        Map<String, Object> existsMrpInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("findMrpPlan", queryParam);

        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();

        String whOwnrCd = rs.getString(WH_OWNR_CD);

        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MRP_INFO_SQ"));
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mrpPlnNm, rs.getString(MRP_PLN_NM));
        if (!isTech) {
            ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, rs.getString(RTL_WH_CD) + rs.getString(RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rs.getString(RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rs.getString(RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, rs.getString("DEST_RTL_WH_CD") + "G");
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rs.getString("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, "G");
        }
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, rs.getString(RTL_WH_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoRgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        ZYPEZDItemValueSetter.setValue(tMsg.ropQty, rs.getBigDecimal(MIN_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, rs.getBigDecimal(MAX_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.ovrdMaxInvtyQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.minOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.incrOrdQty, BigDecimal.ZERO);
        if (WH_OWNR.DBS.equals(whOwnrCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.SUPPLIER);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.WAREHOUSE);
            ZYPEZDItemValueSetter.setValue(tMsg.srcLocCd, "2P1NEW");
            ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, "2P1");
            ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, "NEW");
        }
        // QC#58990-3
        if (existsMrpInfoMap == null || existsMrpInfoMap.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(tMsg.rplshDlyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rplshMonFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rplshTueFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rplshWedFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rplshThuFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rplshFriFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.rplshDlyFlg, (String) existsMrpInfoMap.get(RPLSH_DLY_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.rplshMonFlg, (String) existsMrpInfoMap.get(RPLSH_MON_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.rplshTueFlg, (String) existsMrpInfoMap.get(RPLSH_TUE_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.rplshWedFlg, (String) existsMrpInfoMap.get(RPLSH_WED_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.rplshThuFlg, (String) existsMrpInfoMap.get(RPLSH_THU_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.rplshFriFlg, (String) existsMrpInfoMap.get(RPLSH_FRI_FLG));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.supdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.calcOrdProcCd, ZYPConstant.FLG_OFF_0);

        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println("Insert Error:" + tMsg.getReturnCode());
            StringBuilder sb = new StringBuilder();
            sb.append("MRP_INFO");
            sb.append(", MDSE_CD:" + rs.getString(MDSE_CD));
            sb.append(", SRC_WH_CD:" + rs.getString(RTL_WH_CD));
            sb.append(", MIN_INVTY_QTY:" + rs.getBigDecimal(MIN_INVTY_QTY));
            sb.append(", MAX_INVTY_QTY:" + rs.getBigDecimal(MAX_INVTY_QTY));
            setErrMsg(sb.toString(), NPAM1172E);
            errorCount++;
            return;
        }
        successCount++;
    }

    private void updateMrpInfo(ResultSet rs, BigDecimal mrpInfoPk) throws SQLException {

        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoPk, mrpInfoPk);
        tMsg = (MRP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        if (tMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.ropQty, rs.getBigDecimal(MIN_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, rs.getBigDecimal(MAX_INVTY_QTY));
        // QC#58990-2
        ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println("Update Error:" + tMsg.getReturnCode());
            StringBuilder sb = new StringBuilder();
            sb.append("MRP_INFO_PK:" + mrpInfoPk);
            sb.append(", MDSE_CD:" + rs.getString(MDSE_CD));
            sb.append(", SRC_WH_CD:" + rs.getString(RTL_WH_CD));
            sb.append(", MIN_INVTY_QTY:" + rs.getBigDecimal(MIN_INVTY_QTY));
            sb.append(", MAX_INVTY_QTY:" + rs.getBigDecimal(MAX_INVTY_QTY));
            setErrMsg(sb.toString(), NPAM1171E);
            errorCount++;
            return;
        }
        successCount++;
    }

    // Mod QC#60261
    private void checkMrpInfoAndTerminate(ResultSet rs, boolean isTech) throws SQLException {

        // Get MRP INFO
        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(DB_PARAM_MDSE_CD, rs.getString(MDSE_CD));
        queryParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put(DB_PARAM_MRP_PLN_NM, rs.getString(MRP_PLN_NM));
        // Mod QC#60261
        String sql = "";
        if (isTech) {
            sql = "checkMrpInfoForTech";
            queryParam.put(DB_PARAM_SRC_RTL_WH_CD, rs.getString(RTL_WH_CD));
        } else {
            sql = "checkMrpInfo";
            queryParam.put(DB_PARAM_RTL_WH_CD, rs.getString(RTL_WH_CD));
            queryParam.put(DB_PARAM_RTL_SWH_CD, rs.getString(RTL_SWH_CD));
        }
        BigDecimal mrpInfoPk = (BigDecimal) ssmBatchClient.queryObject(sql, queryParam);

        if (!ZYPCommonFunc.hasValue(mrpInfoPk)) {
            return;
        }

        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoPk, mrpInfoPk);
        tMsg = (MRP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        if (tMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoRgtnStsCd, RGTN_STS.TERMINATED);
        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println("Update Error:" + tMsg.getReturnCode());
            StringBuilder sb = new StringBuilder();
            sb.append("MRP_INFO_PK:" + mrpInfoPk);
            sb.append(", MDSE_CD:" + rs.getString(MDSE_CD));
            sb.append(", SRC_WH_CD:" + rs.getString(RTL_WH_CD));
            setErrMsg(sb.toString(), NPAM1171E);
            errorCount++;
            return;
        }
    }

    private void setErrMsg(String errMsg, String errMsgId) {

        String[] errParam = new String[] {errMsg };

        S21InfoLogOutput.println(errMsgId, errParam);
        errMsgList.add(S21MessageFunc.clspGetMessage(errMsgId, errParam));

        errorCount++;
        rollback();
    }

    // Mod QC#60261
    private boolean isValidIfData(ResultSet rs, boolean isTech) throws SQLException {

        boolean isErr = false;

        String mdseCd = rs.getString(MDSE_CD);
        String rtlWhCd = rs.getString(RTL_WH_CD);
        // Mod QC#60261
        String rtlSwhCd = rs.getString(RTL_SWH_CD);
        String mrpPlnNm = rs.getString(MRP_PLN_NM);
        BigDecimal aslDtlPk = null;
        String ifRtlWhCd = "";
        if (!isTech) {
            aslDtlPk = rs.getBigDecimal(ASL_DTL_PK);
        } else {
            ifRtlWhCd = rs.getString(IF_RTL_WH_CD);
        }
        String whRgCd = rs.getString(WH_RG_CD);
        String ifMdseCd = rs.getString(IF_MDSE_CD);
        BigDecimal minInvtyQty = rs.getBigDecimal(MIN_INVTY_QTY);
        BigDecimal maxInvtyQty = rs.getBigDecimal(MAX_INVTY_QTY);

        // MDSE_CD
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            isErr = true;
        }
        // RTL_WH_CD(Region Mapping)
        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            isErr = true;
        }
         // RTL_SWH_CD(Region Mapping)
        if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
            isErr = true;
        }
        // MRP_PLN_NM
        if (!ZYPCommonFunc.hasValue(mrpPlnNm)) {
            isErr = true;
        }
        // ASL_DTL_PK(ASL_DTL). // Mod QC#60261
        if (!isTech && ZYPCommonFunc.hasValue(mdseCd) && !ZYPCommonFunc.hasValue(aslDtlPk)) {
            isErr = true;
        }

        if (isErr) {
            StringBuilder sb = new StringBuilder();
            if (ZYPCommonFunc.hasValue(mdseCd)) {
                sb.append("\tMDSE_CD:[" + mdseCd + "]");
                // Mod QC#60261
                if (!isTech && !ZYPCommonFunc.hasValue(aslDtlPk)) {
                    sb.append(" / ASL:There is no Primary Vendor.");
                }
            } else {
                sb.append("\tIF_MDSE_CD:[" + ifMdseCd + "] There is no S21 MDSE Master.");
            }
            if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                sb.append(" / WH_CD:" + rtlWhCd);
            } else {
                sb.append(" / TRD_PTNR_WH_RG_XREF:[" + whRgCd + "] There is no Region Mapping.");
            }
            if (isTech && !ZYPCommonFunc.hasValue(rtlWhCd) && ZYPCommonFunc.hasValue(ifRtlWhCd)) {
                sb.append(" / IF_RTL_WH_CD:[" + ifRtlWhCd + "]");
            }
            // Mod QC#60261
            if (!isTech && ZYPCommonFunc.hasValue(mdseCd) && !ZYPCommonFunc.hasValue(mrpPlnNm)) {
                // QC#59649 Mod
                sb.append(" / MRP_PLN_PROD_COND:There is no combination of MRP plan name and Vendor Code.");
            }
            sb.append(" / MIN_INVTY_QTY:" + minInvtyQty);
            sb.append(" / MAX_INVTY_QTY:" + maxInvtyQty);
            errMsgList.add(sb.toString());
        }

        return isErr;
    }

    private void sendErrMail() {
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, ML_GRP_CD_FROM);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, ML_GRP_CD_TO);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList.isEmpty()) {
            return;
        }
        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, ML_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NPZM0161E);
        }

        String crlf = System.getProperty("line.separator");

        // Message
        StringBuffer msg = new StringBuffer();
        for (String errMsg : this.errMsgList) {
            // line end.
            msg.append(errMsg);
            msg.append(crlf);
        }

        template.setTemplateParameter(EMAIL_PARAM_BATCH_ID, BUSINESS_ID);
        template.setTemplateParameter(EMAIL_PARAM_MESSAGE, msg.toString());

        // Set mail subject
        mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }
}
