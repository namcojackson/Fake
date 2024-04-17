/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB239001;

import static com.canon.cusa.s21.batch.NWA.NWAB239001.constant.NWAB239001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.NWAI2410_01TMsg;

import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Received Customer Order (IF Data Creation)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/04/2013   Hitachi         K.Kasai         Create          N/A
 * 06/03/2016   CITS            N.Akaishi       Update          V1.0
 *</pre>
 */
public class NWAB239001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Grobal Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Process Date */
    private String procDate = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** success count */
    private int successCount;

    /** error count */
    private int errorCount;

    /** Commit Number */
    private int commitCount;

    @Override
    protected void initRoutine() {

        this.successCount = 0;

        this.errorCount = 0;

        this.glblCmpyCd = getGlobalCompanyCode();

        this.interfaceId = getInterfaceID();

        checkParam();

        this.procDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // get the Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= MAX_COMMIT_NUMBER) {
            this.commitCount = MAX_COMMIT_NUMBER;
        }
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = trxAccess.getNextTransactionId();
    }

    @Override
    protected void mainRoutine() {

         //insert parts CPO Data To NWAI2420_01
         insertPartsCpoData();

        trxAccess.createIntegrationRecordForBatch(this.interfaceId, this.trxId);
    }

    @Override
    protected void termRoutine() {
        setTermState(TERM_CD.NORMAL_END, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB239001().executeBatch(NWAB239001.class.getSimpleName());
    }

    private void checkParam() {
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_ITEM_INTERFACE_ID });
        }
    }

    private void insertPartsCpoData() {
        //get INTFC_CPO_WRK Data
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("intfcCratDt", this.procDate);
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitCount);
        execParam.setMaxRows(0);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPartsCpoData", queryParam);
            rs = stmt.executeQuery();
            int inputCount = 0;
            String preCpoOrdNum = null;
            String currentCpoOrdNum = null;
            NWAI2410_01TMsg inParam = null;
            List<String> msgList = new ArrayList<String>();
            boolean isError = false;
            while (rs.next()) {
                inputCount++;
                currentCpoOrdNum = rs.getString("INTFC_CPO_ORD_NUM");
                if (preCpoOrdNum != null && !currentCpoOrdNum.equals(preCpoOrdNum)) {
                    if (isError) {
                        rollback();
                        this.errorCount++;
                    } else {
                        commit();
                        this.successCount++;
                    }
                    isError = false;
                }
                if (!isError) {
                    inParam = setInterfaceData(rs, inputCount);
                    EZDTBLAccessor.insert(inParam);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
                        rollback();
                        //get error message
                        msgList.add(setErrorMessage(NWAM0546E, inParam));
                        isError = true;
                    }
                }
                preCpoOrdNum = rs.getString("INTFC_CPO_ORD_NUM");
            }
            if (isError) {
                rollback();
                this.errorCount++;
            } else {
                commit();
                this.successCount++;
            }
            for (String msg : msgList) {
                //send error mail
                callSendMailForErrorInfo(msg);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private NWAI2410_01TMsg setInterfaceData(ResultSet rs, int seqNum) {
        NWAI2410_01TMsg dwai241001TMsg = new NWAI2410_01TMsg();

        try {
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.transactionId, this.trxId);
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.unitId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.seqNumber, new BigDecimal(seqNum));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.glblCmpy3Cd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcCpoOrdDt, rs.getString("INTFC_CPO_ORD_DT"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcCpoOrdNum, rs.getString("INTFC_CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.cincShipToGlblCmpyCd, rs.getString("CINC_SHIP_TO_GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.cincRcvGlblWhCd, rs.getString("CINC_RCV_GLBL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.cincBillToGlblCmpyCd, rs.getString("CINC_BILL_TO_GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.cincGlblCmpyCatgCd, rs.getString("CINC_GLBL_CMPY_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcSellToCustCd, rs.getString("INTFC_SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcOrdQtySgnTxt, rs.getString("INTFC_ORD_QTY_SGN_TXT"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcOrdQty, rs.getBigDecimal("INTFC_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcRsdDt, rs.getString("INTFC_RSD_DT"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcExpdShipDt, rs.getString("INTFC_EXPD_SHIP_DT"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcRddDt, rs.getString("INTFC_RDD_DT"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.cincGlblShpgMethCd, rs.getString("CINC_GLBL_SHPG_METH_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.cincGlblOrdCatgCd, rs.getString("CINC_GLBL_ORD_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.prtChrgInd, rs.getString("PRT_CHRG_IND"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.prtEmerOrdInd, rs.getString("PRT_EMER_ORD_IND"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.intfcCratDt, rs.getString("INTFC_CRAT_DT"));
            ZYPEZDItemValueSetter.setValue(dwai241001TMsg.cincGlblWhCd, rs.getString("CINC_GLBL_WH_CD"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return dwai241001TMsg;
    }

    private void callSendMailForErrorInfo(String msg) {
        NWXC100001SendMailForErrorInfo sendMail = new NWXC100001SendMailForErrorInfo();
        sendMail.addErrMsg(msg);
        sendMail.sendMail(this.glblCmpyCd, FUNC_ID);
    }

    private String setErrorMessage(String msg, NWAI2410_01TMsg inParam) {
        StringBuilder sb = new StringBuilder();
        sb.append("NWAI2410_01 Key:INTFC_CPO_ORD_NUM:").append(inParam.intfcCpoOrdNum.getValue());
        sb.append(" INTFC_CPO_ORD_DT:").append(inParam.intfcCpoOrdDt.getValue());
        return S21MessageFunc.clspGetMessage(msg, new String[] {sb.toString()});
    }
}
