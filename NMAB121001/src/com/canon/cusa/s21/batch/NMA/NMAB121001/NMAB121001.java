/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB121001;

import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ACCT_TRTY_ROLE_ASG_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ASG_TRTY_ATTRB_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ASG_TRTY_ATTRB_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.CNTY_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DS_ACCT_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DS_ACCT_CLS_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DS_ACCT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DS_ACCT_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.DS_CUST_SIC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FIFTH_DS_ACCT_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FIFTH_DS_ACCT_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FIRST_DS_ACCT_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FIRST_DS_ACCT_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FRTH_DS_ACCT_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FRTH_DS_ACCT_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.LINE_BIZ_ROLE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.LINE_BIZ_ROLE_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.MAN_ENTRY_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.NON_SLS_REP_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ORG_RANK_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.PSN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.SCD_DS_ACCT_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.SCD_DS_ACCT_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.THIRD_DS_ACCT_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.THIRD_DS_ACCT_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.TOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.TRTY_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.TRTY_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.TRTY_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB121001.constant.NMAB121001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_ACCT_TRTYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Account Territory Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB121001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

    /** Commit Count */
    private int commitCount;

    /** Insert Count */
    private int normalRecCnt;

    /** Fetch Count */
    private int totalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NMAB121001().executeBatch(NMAB121001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialize
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.totalRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE});
        }

        // Get Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= DEFAULT_COMMIT_COUNT) {
            this.commitCount = DEFAULT_COMMIT_COUNT;
        }

        // Get Target date
        this.trgtDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.trgtDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_PROC_DATE });
        }

    }

    @Override
    protected void mainRoutine() {

        // Check Target Date Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            DMN_ACCT_TRTYTMsg tMsg = new DMN_ACCT_TRTYTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For DMN_ACCT_TRTY Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", param, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To DMN_ACCT_TRTY
                DMN_ACCT_TRTYTMsg tMsg = mapData(rs);

                // Insert DMN_ACCT_TRTY
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", LOC_NUM, "=", rs.getString(LOC_NUM),
                            ", ", ORG_CD, "=", rs.getString(ORG_CD), ", ", PSN_CD, "=", rs.getString(PSN_CD));
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg});
                    this.errRecCnt++;
                }

                // Commit By Commit Point
                wkInsertCount++;
                if (this.errRecCnt == 0 && wkInsertCount == this.commitCount) {
                    commit();
                    this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                    wkInsertCount = 0;
                }

            } // End Loop

            // Commit By Last Data
            if (this.errRecCnt == 0 && wkInsertCount > 0) {
                this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                commit();
            } else {
                rollback();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    /**
     * Map Result Set To DMN_ACCT_TRTYTMsg
     */
    private DMN_ACCT_TRTYTMsg mapData(ResultSet rs) throws SQLException {

        DMN_ACCT_TRTYTMsg tMsg = new DMN_ACCT_TRTYTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt,  this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.acctTrtyRoleAsgPk, rs.getBigDecimal(ACCT_TRTY_ROLE_ASG_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString(LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, rs.getString(ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.orgNm, rs.getString(ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpCd, rs.getString(LINE_BIZ_ROLE_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpDescTxt, rs.getString(LINE_BIZ_ROLE_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, rs.getString(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctTpCd, rs.getString(DS_ACCT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctTpDescTxt, rs.getString(DS_ACCT_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctClsCd, rs.getString(DS_ACCT_CLS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctClsDescTxt, rs.getString(DS_ACCT_CLS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.firstDsAcctGrpTpCd, rs.getString(FIRST_DS_ACCT_GRP_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.firstDsAcctGrpDescTxt, rs.getString(FIRST_DS_ACCT_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.scdDsAcctGrpTpCd, rs.getString(SCD_DS_ACCT_GRP_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.scdDsAcctGrpDescTxt, rs.getString(SCD_DS_ACCT_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdDsAcctGrpTpCd, rs.getString(THIRD_DS_ACCT_GRP_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdDsAcctGrpDescTxt, rs.getString(THIRD_DS_ACCT_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.frthDsAcctGrpTpCd, rs.getString(FRTH_DS_ACCT_GRP_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frthDsAcctGrpDescTxt, rs.getString(FRTH_DS_ACCT_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthDsAcctGrpTpCd, rs.getString(FIFTH_DS_ACCT_GRP_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthDsAcctGrpDescTxt, rs.getString(FIFTH_DS_ACCT_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString(CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString(ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.provNm, rs.getString(PROV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString(CNTY_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString(POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyGrpTpCd, rs.getString(TRTY_GRP_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.orgRankNum, rs.getString(ORG_RANK_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, rs.getString(TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.tocNm, rs.getString(TOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, rs.getString(PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.psnNum, rs.getString(PSN_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnFirstNm, rs.getString(PSN_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnLastNm, rs.getString(PSN_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.manEntryFlg, rs.getString(MAN_ENTRY_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTpCd, rs.getString(TRTY_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTpDescTxt, rs.getString(TRTY_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.asgTrtyAttrbCd, rs.getString(ASG_TRTY_ATTRB_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.asgTrtyAttrbDescTxt, rs.getString(ASG_TRTY_ATTRB_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.nonSlsRepFlg, rs.getString(NON_SLS_REP_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, rs.getString(LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString(DS_CUST_SIC_CD));

        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }
}
