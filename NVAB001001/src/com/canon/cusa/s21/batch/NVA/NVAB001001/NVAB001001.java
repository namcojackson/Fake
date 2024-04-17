/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NVA.NVAB001001;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21CSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Create Address Validation List(BODS)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/20   Fujitsu         M.Nakamura      Create          QC#21451
 *</pre>
 */
public class NVAB001001 extends S21BatchMain {

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Process Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NVAB001001().executeBatch(NVAB001001.class.getSimpleName());
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);

    }

    private void doProcess() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        // Set Temporary File Name.
        String fileName = ZYPCSVOutFile.createCSVOutFileNm("CleansingAddressList") + ".csv";

        try {
            // Get Address Info
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(1000);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("ctryUS", CTRY.UNITED_STATES_OF_AMERICA);

            stmt = ssmLLClient.createPreparedStatement("getAddressInfo", ssmParam, execParam);
            rs = stmt.executeQuery();

            String strLine = "";

            File f = new File(fileName);

            fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            while (rs.next()) {
                NMZC003001PMsg pMsg = new NMZC003001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, rs.getString("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.stCd, rs.getString("ST_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.postCd, rs.getString("POST_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, rs.getString("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, rs.getString("CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(pMsg.cntyPk, rs.getBigDecimal("CNTY_PK"));
                ZYPEZDItemValueSetter.setValue(pMsg.xxAddrVldFlg, ZYPConstant.FLG_ON_Y);

                NMZC003001 api = new NMZC003001();
                api.execute(pMsg, ONBATCH_TYPE.BATCH);

                if (!NMZC003001Constant.RTRN_CD_NO_ERR.equals(pMsg.xxVldStsCd_01.getValue())
                        || !NMZC003001Constant.RTRN_CD_NO_ERR.equals(pMsg.xxVldStsCd_02.getValue())
                        || !NMZC003001Constant.RTRN_CD_NO_ERR.equals(pMsg.xxVldStsCd_03.getValue())
                        || !NMZC003001Constant.RTRN_CD_NO_ERR.equals(pMsg.xxVldStsCd_04.getValue())
                        || !NMZC003001Constant.RTRN_CD_NO_ERR.equals(pMsg.xxVldStsCd_05.getValue())
                        || !NMZC003001Constant.RTRN_CD_NO_ERR.equals(pMsg.xxVldStsCd_06.getValue())
                        || !NMZC003001Constant.RTRN_CD_NO_ERR.equals(pMsg.xxVldStsCd_07.getValue())
                        ) {
                    strLine = "";
                    strLine = pMsg.xxVldStsCd_01.getValue() + ",\"" + nvl(rs.getString("FIRST_LINE_ADDR")) + "\",\"" + pMsg.firstLineAddr.getValue() + "\"";
                    strLine = strLine + "," + pMsg.xxVldStsCd_02.getValue() + ",\"" + nvl(rs.getString("SCD_LINE_ADDR")) + "\",\"" + pMsg.scdLineAddr.getValue() + "\"";
                    strLine = strLine + "," + pMsg.xxVldStsCd_03.getValue() + "," + rs.getString("CTY_ADDR") + "," + pMsg.ctyAddr.getValue();
                    strLine = strLine + "," + pMsg.xxVldStsCd_04.getValue() + "," + rs.getString("ST_CD") + "," + pMsg.stCd.getValue();
                    strLine = strLine + "," + pMsg.xxVldStsCd_05.getValue() + "," + rs.getString("POST_CD") + "," + pMsg.postCd.getValue();
                    strLine = strLine + "," + pMsg.xxVldStsCd_06.getValue() + "," + rs.getString("CTRY_CD") + "," + pMsg.ctryCd.getValue();
                    strLine = strLine + "," + pMsg.xxVldStsCd_07.getValue() + "," + rs.getString("CNTY_NM") + "," + pMsg.cntyNm.getValue();
                    strLine = strLine + "," + nvl(rs.getString("SELL_TO_CUST_CD")) + "," + nvl(rs.getString("BILL_TO_CUST_CD")) + "," +  nvl(rs.getString("SHIP_TO_CUST_CD")); 
                    strLine = strLine + "," + String.valueOf(rs.getBigDecimal("PTY_LOC_PK"));
                    strLine = strLine + S21CSVOutFile.LINE_END_CODE_CRLF;

                    pw.write(strLine);

                    this.totalErrCount = this.totalErrCount + 1;
                } else {
                    this.totalNmlCount = this.totalNmlCount + 1;
                }
            }

        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } catch (IOException e) {
            throw new S21AbendException("ZZZM9026E", new String[] {fileName});
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            pw.close();
        }

    }

    private String nvl(String val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        }
        return "";
    }
}
