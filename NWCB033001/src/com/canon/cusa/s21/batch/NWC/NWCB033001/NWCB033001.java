/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB033001;

import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_CFS_FUND_APP_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_CFS_FUND_CR_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_CFS_FUND_CUST_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_CFS_FUND_PO_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_CFS_FUND_PO_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_CFS_PO_CRAT_TS;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_EZINTIME;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_LEASE_CMPY_USR_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_LEASE_CMPY_USR_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_LTST_BKNG_CMNT_TS;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.COL_LTST_BKNG_CMNT_TXT;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.INTFC_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.NWCM0109E;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.NWCM0110E;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.NWCM0145E;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NWC.NWCB033001.constant.NWCB033001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CFS_LEASE_PO_INFOTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INTFC_TS_MGTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * CFS PO# Inbound Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   Fujitsu         W.Honda         Create          N/A
 * 2016/11/04   Fujitsu         W.Honda         Update          QC#15698
 * 2018/03/14   Fujitsu         A.Kosai         Update          S21_NA#24446
 *</pre>
 */

public class NWCB033001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** interface Id */
    private String interfaceId = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** SSM Low Level Coding Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor tblAccessor = null;

    // 2016/11/04 QC#15698 Add Start
    /** Max Request Update Time Stamp */
    private String maxRqstUpdTs = null;
    // 2016/11/04 QC#15698 Add End

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NWCB033001().executeBatch(NWCB033001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD});
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {GLBL_CMPY_CD});
        }

        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {INTFC_ID});
        }

        this.tblAccessor = new S21TransactionTableAccessor();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        BigDecimal[] transactionIdList = tblAccessor.getIntegrationRecord(this.interfaceId);
        if (transactionIdList.length == 0) {
            this.termCd = TERM_CD.NORMAL_END;
            return;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        int worktotalCnt = 0;
        int workerrCnt = 0;

        try {
            for (BigDecimal intfcTrxId : transactionIdList) {

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                execParam.setFetchSize(DEFAULT_FETCH_SIZE);
                execParam.setMaxRows(0);
                execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
                execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("interfaceId", this.interfaceId);
                ssmParam.put("transactionId", intfcTrxId);
                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ps = this.ssmLLClient.createPreparedStatement("getPoInfoFromNWCI0120", ssmParam, execParam);
                rs = ps.executeQuery();

                while (rs.next()) {
                    CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();

                    if (!checkTargetData(rs, cfsLeasePoInfoTMsg)) {
                        workerrCnt = workerrCnt + 1;
                        continue;
                    }

                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
                    BigDecimal cfsLeasePoInfoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_PO_INFO_SQ);
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsLeasePoInfoPk);
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsAppNum, rs.getString(COL_CFS_FUND_APP_NUM));
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.dsAcctNm, rs.getString(COL_CFS_FUND_CUST_NM));
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsPoNum, rs.getString(COL_CFS_FUND_PO_NUM));
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsPoAmt, rs.getBigDecimal(COL_CFS_FUND_PO_AMT));
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsCrAmt, rs.getBigDecimal(COL_CFS_FUND_CR_AMT));
                    String ltstBkngCmntDt = rs.getString(COL_LTST_BKNG_CMNT_TS);
                    if (ZYPCommonFunc.hasValue(ltstBkngCmntDt)) {
                        ltstBkngCmntDt = ltstBkngCmntDt.replace("/", "");
                        if (cfsLeasePoInfoTMsg.getAttr("ltstBkngCmntDt").getDigit() < ltstBkngCmntDt.length()) {
                            ltstBkngCmntDt = ltstBkngCmntDt.substring(0, cfsLeasePoInfoTMsg.getAttr("ltstBkngCmntDt").getDigit());
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.ltstBkngCmntDt, ltstBkngCmntDt);
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.ltstBkngCmntTxt, rs.getString(COL_LTST_BKNG_CMNT_TXT));
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.leaseCmpyUsrId, rs.getString(COL_LEASE_CMPY_USR_ID));
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.leaseCmpyUsrNm, rs.getString(COL_LEASE_CMPY_USR_NM));
                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_OFF_N);

                    EZDTBLAccessor.insert(cfsLeasePoInfoTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
                        workerrCnt = workerrCnt + 1;
                        String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("PO_NUM = %s / %s", rs.getString(COL_CFS_FUND_APP_NUM), rs.getString(COL_CFS_FUND_PO_NUM))};
                        String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
                        writeLogLn(errMsgText);
                    }

                    // 2016/11/04 QC#15698 Add Start
                    // 2018/03/14 S21_NA#24446 Mod Start
//                    if (!ZYPCommonFunc.hasValue(this.maxRqstUpdTs)
//                            || ZYPDateUtil.compare(rs.getString(COL_EZINTIME),this.maxRqstUpdTs) > 0) {
//                        this.maxRqstUpdTs = rs.getString(COL_EZINTIME);
//                    }
                    if (!ZYPCommonFunc.hasValue(this.maxRqstUpdTs)
                            || ZYPDateUtil.compare(rs.getString(COL_CFS_PO_CRAT_TS), this.maxRqstUpdTs) > 0) {
                        this.maxRqstUpdTs = rs.getString(COL_CFS_PO_CRAT_TS);
                    }
                    // 2018/03/14 S21_NA#24446 Mod End
                    // 2016/11/04 QC#15698 Add End
                    worktotalCnt = worktotalCnt + 1;
                }

                this.errorCount = this.errorCount + workerrCnt;
                this.totalCount = this.totalCount + worktotalCnt + workerrCnt;
                workerrCnt = 0;
                worktotalCnt = 0;
                tblAccessor.endIntegrationProcess(this.interfaceId, intfcTrxId);
                commit();
            }

            // 2016/11/04 QC#15698 Add Start
            if (this.totalCount - this.errorCount > 0) {
                updateIntfcTsMgt();
            }
            commit();
            // 2016/11/04 QC#15698 Add End
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
            this.successCount = this.totalCount - this.errorCount;
        }

    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);
    }

    private static boolean checkTargetData(ResultSet rs, CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg) throws SQLException {

        // Mandatory Check
        if (!ZYPCommonFunc.hasValue(rs.getString(COL_CFS_FUND_APP_NUM))) {
            String[] paramArray = new String[] {COL_CFS_FUND_APP_NUM};
            String errMsgText = S21MessageFunc.clspGetMessage(ZZZM9025E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(rs.getString(COL_CFS_FUND_PO_NUM))) {
            String[] paramArray = new String[] {COL_CFS_FUND_PO_NUM};
            String errMsgText = S21MessageFunc.clspGetMessage(ZZZM9025E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(rs.getString(COL_CFS_FUND_PO_AMT))) {
            String[] paramArray = new String[] {COL_CFS_FUND_PO_AMT};
            String errMsgText = S21MessageFunc.clspGetMessage(ZZZM9025E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        // Overflow Check
        if (ZYPCommonFunc.hasValue(rs.getString(COL_CFS_FUND_PO_NUM))) {
            if (cfsLeasePoInfoTMsg.getAttr("cfsPoNum").getDigit() < rs.getString(COL_CFS_FUND_PO_NUM).length()) {
                String[] paramArray = new String[] {COL_CFS_FUND_PO_NUM};
                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0145E, paramArray);
                writeLogLn(errMsgText);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(rs.getString(COL_CFS_FUND_PO_AMT))) {
            if (cfsLeasePoInfoTMsg.getAttr("cfsPoAmt").getDigit() < rs.getString(COL_CFS_FUND_PO_AMT).length()) {
                String[] paramArray = new String[] {COL_CFS_FUND_PO_AMT};
                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0145E, paramArray);
                writeLogLn(errMsgText);
                return false;
            }
        }

        return true;

    }

    private static void writeLogLn(String format, Object... param) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", BIZ_APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    // 2016/11/04 QC#15698 Add Start
    private void updateIntfcTsMgt() {
        INTFC_TS_MGTTMsg keyTMsg = new INTFC_TS_MGTTMsg();
        ZYPEZDItemValueSetter.setValue(keyTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(keyTMsg.mngIntfcId, this.interfaceId);

        INTFC_TS_MGTTMsg tMsg = (INTFC_TS_MGTTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyTMsg);

        if (tMsg == null) {
            ZYPEZDItemValueSetter.setValue(keyTMsg.mngIntfcTs, this.maxRqstUpdTs);
            S21FastTBLAccessor.insert(keyTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(keyTMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NWCM0109E, new String[] {"INTFC_TS_MGT", this.interfaceId});
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.mngIntfcTs, this.maxRqstUpdTs);
            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NWCM0110E, new String[] {"INTFC_TS_MGT", this.interfaceId});
            }
        }
    }
    // 2016/11/04 QC#15698 Add End
}
