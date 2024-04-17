/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB061001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttrDefines;
import business.db.NSBI2010_01TMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Web OMAL F2 Closed Job Visit Travel Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         O.Okuma         Create          N/A
 * 2016/10/28   Hitachi         T.Mizuki        Update          QC#15568
 * </pre>
 */
public class NSBB061001 extends S21BatchMain {

    /** [@] field is mandatory. */
    private static final String ZZM9000E = "ZZM9000E";

    /** Failed to insert "@". */
    private static final String INS_ERR_MSD_ID = "NSBM0164E";

    /** message Item: Global Company Code */
    private static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Interface Id */
    private static final String MSG_ITEM_INTERFACE_ID = "Interface Id";

    /** MAX_COMMIT_NUMBER: 1000 */
    private static final int MAX_COMMIT_NUMBER = 1000;

    /** Time Stamp Format */
    private static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Variable item start position */
    private static final int VAR_ITEM_START_POSN = 17;

    /** OMAL DATA TYPE: A */
    private static final String OMAL_DATA_TYPE = "A";

    /** Number: 2 */
    private static final int NUM_2 = 2;

    /** Number: 3 */
    private static final int NUM_3 = 3;

    /** Number: 24 */
    private static final int NUM_24 = 24;

    /** Number: 60 */
    private static final int NUM_60 = 60;

    // mod start 2016/10/28 CSA QC#15568
    /** Number: 99 */
    private static final int NUM_99 = 99;
    // mod end 2016/10/28 CSA QC#15568

    /** WEB_OMAL_MONTH : -36 */
    private static final int WEB_OMAL_MONTH = -36;

    /** DS_CONTR_CTRL_STS_LIST */
    private static final String[] DS_CONTR_CTRL_STS_LIST = {DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.CANCELLED };

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Unit ID */
    private int unitId;

    /** Commit Number */
    private int commitNumber;

    /** Total Commit Count */
    private int totalCommitCount;

    /** Process Time Stamp */
    private String procTs;

    /** Batch Process Date */
    private String batchProcDate;

    /** Sales Date */
    private String salesDate;

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!hasValue(this.intfcId)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get Batch Process Date
        this.batchProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = this.trxAccess.getNextTransactionId();
        this.unitId = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
    }

    @Override
    protected void mainRoutine() {

        insertInterfaceData("getInputData", setSearchCondition());

        // Insert Transaction data
        this.trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB061001().executeBatch(NSBB061001.class.getSimpleName());
    }

    private void insertInterfaceData(String sqlId, Map<String, Object> paramMap) {
        List<NSBI2010_01TMsg> inTMsgList = new ArrayList<NSBI2010_01TMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, paramMap, execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;

            // Insert Interface Data
            while (rs.next()) {
                this.unitId++;
                inTMsgList.add(setCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList);
                    inTMsgList = new ArrayList<NSBI2010_01TMsg>();
                    this.totalCommitCount += commitCount;
                    commitCount = 0;
                }
            }

            if (this.unitId != this.totalCommitCount) {
                commitCount = insertInterfaceData(inTMsgList);
                this.totalCommitCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertInterfaceData(List<NSBI2010_01TMsg> inMsgLst) {
        NSBI2010_01TMsg[] inMsgArray;
        inMsgArray = new NSBI2010_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(INS_ERR_MSD_ID, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }

    private Map<String, Object> setSearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("fsrStsCd_closed", SVC_TASK_STS.CLOSED);
        inParam.put("dsContrCtrlStsCd_cancelled", DS_CONTR_CTRL_STS.CANCELLED);
        inParam.put("dsContrCtrlStsCdList", DS_CONTR_CTRL_STS_LIST);
        inParam.put("dsCntrCatgCd_warranty", DS_CONTR_CATG.WARRANTY);
        inParam.put("strLen_2", NUM_2);
        inParam.put("strFrom_3", NUM_3);
        inParam.put("hourPerDay_24", NUM_24);
        inParam.put("minPerHour_60", NUM_60);
        // mod start 2016/10/28 CSA QC#15568
        inParam.put("maxHour_99", NUM_99);
        // mod end 2016/10/28 CSA QC#15568
        inParam.put("omalDataType_A", OMAL_DATA_TYPE);
        inParam.put("bizAreaOrgCd_service", BIZ_AREA_ORG.SERVICE);
        inParam.put("slsDt", this.salesDate);
        inParam.put("addMonth", WEB_OMAL_MONTH);
        inParam.put("orgTierCd_0", ORG_TIER._0);
        inParam.put("orgTierCd_1", ORG_TIER._1);
        inParam.put("orgTierCd_2", ORG_TIER._2);
        inParam.put("orgTierCd_3", ORG_TIER._3);
        inParam.put("orgTierCd_4", ORG_TIER._4);
        inParam.put("orgTierCd_5", ORG_TIER._5);
        inParam.put("orgTierCd_6", ORG_TIER._6);
        inParam.put("orgTierCd_7", ORG_TIER._7);
        inParam.put("orgTierCd_8", ORG_TIER._8);
        inParam.put("orgTierCd_9", ORG_TIER._9);
        inParam.put("orgTierCd_10", ORG_TIER._10);

        return inParam;
    }

    private NSBI2010_01TMsg setCreateValue(ResultSet rs) {

        NSBI2010_01TMsg inParam = new NSBI2010_01TMsg();

        ArrayList[] columnList = inParam.getSelectColumnList();

        List<String> rsColNames = new ArrayList<String>();
        ResultSetMetaData rsm;
        String dbNm;
        String rsNm;

        try {
            rsm = rs.getMetaData();
            for (int i = 0; i < rsm.getColumnCount(); i++) {
                rsColNames.add(rsm.getColumnName(i + 1));
            }
            inParam.setDBValue("interfaceId", this.intfcId);
            inParam.setDBValue("transactionId", this.trxId);
            inParam.setDBValue("segmentId", BigDecimal.ONE);
            inParam.setDBValue("unitId", BigDecimal.valueOf(this.unitId));
            inParam.setDBValue("seqNumber", BigDecimal.ONE);
            for (int idx = VAR_ITEM_START_POSN; idx < columnList[0].size(); idx++) {
                dbNm = columnList[0].get(idx).toString();
                rsNm = columnList[1].get(idx).toString();
                if (!rsColNames.contains(rsNm)) {
                    continue;
                }
                if (inParam.getAttr(dbNm).getType() == EZDItemAttrDefines.TYPE_SEISU_SYOSU) {
                    inParam.setDBValue(dbNm, rs.getBigDecimal(rsNm));
                } else {
                    inParam.setDBValue(dbNm, rs.getString(rsNm));
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }
}