/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB049001;

import static com.canon.cusa.s21.batch.NSA.NSAB049001.constant.NSAB049001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_MSGTMsg;
import business.db.DS_MTR_INTFCTMsg;
import business.db.ROSS_INTFC_MTR_READTMsg;
import business.parts.NSZC051001PMsg;
import business.parts.NSZC051001_rsltPrmListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC051001.NSZC051001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Batch Program Class for CUSA Retail Meter Reads Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * -----------------------------------------------------------------------
 * 06/10/2016   CITS            T.Wada          Create          None
 * 02/08/2017   Hitachi         K.Kojima        Update          QC#16600
 * 04/11/2017   Hitachi         T.Tomita        Update          QC#18242
 * 2017/05/26   Hitachi         K.Kojima        Update          QC#18710
 * 2017/06/06   Hitachi         K.Kitachi       Update          QC#18342
 *</pre>
 */
public class NSAB049001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // START 2017/06/06 K.Kitachi [QC#18342, DEL]
//    /** commit count */
//    private int commitCount = 0;
    // END 2017/06/06 K.Kitachi [QC#18342, DEL]

    /** total correct count */
    private int totalCorrectCount = 0;

    /** Counter of Records: Error */
    private int totalErrorCount = 0;

    /** fetchSize */
    private String fetchSize = "1000";

    /** error message code list */
    private List<String> errMsgList;

    /** error message txt for ROSS_INTFC_MTR_PROC_TXT */
    private String rossIfMtrProcTxt = "";

    // START 2017/06/06 K.Kitachi [QC#18342, ADD]
    /** Process Time Stamp */
    private String procTs = null;
    // END 2017/06/06 K.Kitachi [QC#18342, ADD]

    /**
     * Called for batch shell.
     * @param args execution parameter
     */
    public static void main(String[] args) {
        new NSAB049001().executeBatch(NSAB049001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales date
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // START 2017/06/06 K.Kitachi [QC#18342, ADD]
        this.procTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS);
        // END 2017/06/06 K.Kitachi [QC#18342, ADD]

        // Get FetchSize
        fetchSize = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(fetchSize)) {
            fetchSize = DEFAULT_FETCH_SIZE;
        }

        // START 2017/06/06 K.Kitachi [QC#18342, DEL]
//        // Get Commit Count
//        commitCount = getCommitCount();
        // END 2017/06/06 K.Kitachi [QC#18342, DEL]

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.errMsgList = new ArrayList<String>();
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
        execParam.setFetchSize(Integer.parseInt(fetchSize));

        createPhysMtrRead(execParam);
        // Send Mail
        if (errMsgList.size() > 0) {
            sendErrMail();
            // commit for mail
            commit();
        }

    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(this.termCd, this.totalCommitCount, this.totalErrorCount, this.totalCorrectCount);
    }

    /**
     * createPhysMtrRead
     * @param execParam
     */
    private void createPhysMtrRead(S21SsmExecutionParameter execParam) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            // Get SER_NUM, MTR_READ_DT
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
            queryParam.put("ROSS_INTFC_MTR_PROC_CD", ROSS_INTFC_MTR_PROC_CD_NORMAL);

            stmt = ssmLLClient.createPreparedStatement("getTargetSers", queryParam, execParam);
            rs = stmt.executeQuery();

            // START 2017/06/06 K.Kitachi [QC#18342, MOD]
//            int insertCount = 0;

            while (rs.next()) {

                totalCorrectCount++;

                String serNum = rs.getString(SER_NUM);
                // START 2017/02/08 K.Kojima [QC#16600,ADD]
                String mdseCd = rs.getString(MDSE_CD);
                // END 2017/02/08 K.Kojima [QC#16600,ADD]
                String mtrReadDt = rs.getString(MTR_READ_DT);

                // Get Ross Meter Read Info
                // START 2017/02/08 K.Kojima [QC#16600,MOD]
                // List<Map<String, Object>> rossMtrReadList = getSerialMtrReadList(serNum, mtrReadDt);
                List<Map<String, Object>> rossMtrReadList = getSerialMtrReadList(serNum, mdseCd, mtrReadDt);
                // END 2017/02/08 K.Kojima [QC#16600,MOD]

                // Validation Check & Get SVC_PHYS_MTR INFO
                // START 2017/02/08 K.Kojima [QC#16600,MOD]
                // List<Map<String, Object>> physMtrList = getPhysMtrInfoList(rossMtrReadList, serNum, mtrReadDt);
                List<Map<String, Object>> physMtrList = getPhysMtrInfoList(rossMtrReadList, serNum, mdseCd, mtrReadDt);
                // END 2017/02/08 K.Kojima [QC#16600,MOD]
                if (physMtrList == null) {
                    updateErrorStatus(rossMtrReadList);
                    commit();
                    totalErrorCount++;
                    continue;
                }

                // API Call
//                // START 2017/02/08 K.Kojima [QC#16600,MOD]
//                // int rslt = execApi(rossMtrReadList, physMtrList, serNum, mtrReadDt);
//                int rslt = execApi(rossMtrReadList, physMtrList, serNum, mdseCd, mtrReadDt);
//                // END 2017/02/08 K.Kojima [QC#16600,MOD]
//                if (rslt == VAL_CHECK_ERR) {
//                    // START 2017/02/08 K.Kojima [QC#16600,MOD]
//                    // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0531E, new String[] {serNum }));
//                    bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0531E, new String[] {serNum, mdseCd }));
//                    // END 2017/02/08 K.Kojima [QC#16600,MOD]
//                    updateErrorStatus(rossMtrReadList);
//                    totalErrorCount++;
//                    continue;
//                } else if (rslt == API_EXEC_ERR) {
//                    rollback();
//                    totalErrorCount++;
//                    continue;
//                }
//
//                insertCount++;
//
//                if (insertCount >= commitCount) {
//                    commit();
//                    this.totalCommitCount += insertCount;
//                    insertCount = 0;
//                }
                NSZC051001PMsg pMsg = new NSZC051001PMsg();
                List<Map<String, Object>> updInfoList = new ArrayList<Map<String, Object>>();
                List<DS_MTR_INTFCTMsg> dsMtrIntfcTMsgList = new ArrayList<DS_MTR_INTFCTMsg>();
                List<DS_MSGTMsg> dsMsgTMsgList = new ArrayList<DS_MSGTMsg>();
                int rslt = execApi(rossMtrReadList, physMtrList, serNum, mdseCd, mtrReadDt, pMsg, updInfoList, dsMtrIntfcTMsgList, dsMsgTMsgList);
                if (rslt == API_EXEC_ERR) {
                    rollback();
                }
                if (dsMtrIntfcTMsgList.size() > 0) {
                    insertDsMtrIntfc(dsMtrIntfcTMsgList);
                }
                if (dsMsgTMsgList.size() > 0) {
                    insertDsMsg(dsMsgTMsgList);
                }
                if (rslt == VAL_CHECK_ERR) {
                    bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0531E, new String[] {serNum, mdseCd }));
                    updateErrorStatus(rossMtrReadList);
                    this.totalErrorCount++;
                } else if (rslt == API_EXEC_ERR) {
                    List<String> errIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                    String procCd = null;
                    String procTxt = null;
                    // API Fault
                    for (Map<String, Object> rossUpdInfo : updInfoList) {
                        String msg = S21MessageFunc.clspGetMessage(NSAM0003E, new String[] {NSZC051001, errIdList.get(0), "serNum=" + serNum + ",mdseCd=" + mdseCd });
                        bizErrorProcess(msg);
                        Map<String, Object> rsltMap = getDsMsgTxt(serNum, mdseCd, (String) rossUpdInfo.get(MTR_LB_CD), mtrReadDt);
                        if (rsltMap != null) {
                            procCd = (String) rsltMap.get(DS_MTR_PROC_STS_CD);
                            procTxt = (String) rsltMap.get(DS_MSG_TXT);
                        } else {
                            procCd = DS_MTR_PROC_STS_CD_ERR;
                            procTxt = msg;
                        }
                        updateApiErrStatus((BigDecimal) rossUpdInfo.get(ROSS_INTFC_MTR_READ_PK), (String) rossUpdInfo.get(MTR_LB_CD), procCd, procTxt);
                    }
                    this.totalErrorCount++;
                } else {
                    this.totalCommitCount++;
                }
                commit();
            }

//            commit();
//            this.totalCommitCount += insertCount;
            // END 2017/06/06 K.Kitachi [QC#18342, MOD]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * getPhysMtrInfoList
     * @param rossMtrReadList
     * @param serNum
     * @param mdseCd
     * @param mtrReadDt
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private List<Map<String, Object>> getPhysMtrInfoList(List<Map<String, Object>> rossMtrReadList, String serNum, String mtrReadDt) {
    private List<Map<String, Object>> getPhysMtrInfoList(List<Map<String, Object>> rossMtrReadList, String serNum, String mdseCd, String mtrReadDt) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]

        // START 2017/02/08 K.Kojima [QC#16600,MOD]
        // Map<String, Object> svcMachMstr = getSvcMachMstr(serNum);
        Map<String, Object> svcMachMstr = getSvcMachMstr(serNum, mdseCd);
        // END 2017/02/08 K.Kojima [QC#16600,MOD]
        // Check 01
        if (svcMachMstr == null) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0524E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0524E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return null;
        }
        // Check 02
        if (rossMtrReadList.size() > 2) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0525E, new String[] {serNum, String.valueOf(rossMtrReadList.size()) }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0525E, new String[] {serNum, mdseCd, String.valueOf(rossMtrReadList.size()) }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return null;

        }
        BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstr.get(SVC_MACH_MSTR_PK);
        List<Map<String, Object>> physMtrList = getPhysMtrList(svcMachMstrPk);

        // Check 03
        if (physMtrList == null || physMtrList.size() == 0) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0526E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0526E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return null;
        }

        // Validation Check
        // START 2017/02/08 K.Kojima [QC#16600,MOD]
        // if (!bizErrCheck(rossMtrReadList, physMtrList, serNum)) {
        if (!bizErrCheck(rossMtrReadList, physMtrList, serNum, mdseCd)) {
        // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return null;
        }

        // Check 10
        // START 2017/02/08 K.Kojima [QC#16600,MOD]
        // if (!checkDuplicate(physMtrList, serNum, mtrReadDt)) {
        if (!checkDuplicate(physMtrList, serNum, mdseCd, mtrReadDt)) {
        // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return null;
        }

        return physMtrList;
    }

    /**
     * bizErrCheck
     * @param rossMtrReadList
     * @param physMtrList
     * @param curSerNum
     * @param mdseCd
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private boolean bizErrCheck(List<Map<String, Object>> rossMtrReadList, List<Map<String, Object>> physMtrList, String curSerNum) {
    private boolean bizErrCheck(List<Map<String, Object>> rossMtrReadList, List<Map<String, Object>> physMtrList, String curSerNum, String mdseCd) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]
        if (rossMtrReadList.size() == 2) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // if (!bizErrCheck1(physMtrList, curSerNum)) {
            if (!bizErrCheck1(physMtrList, curSerNum, mdseCd)) {
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
                return false;
            }
        } else {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // if (!bizErrCheck2(physMtrList, curSerNum)) {
            if (!bizErrCheck2(physMtrList, curSerNum, mdseCd)) {
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
                return false;
            }
        }

        return true;
    }

    /**
     * bizErrCheck1
     * @param physMtrList
     * @param serNum
     * @param mdseCd
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private boolean bizErrCheck1(List<Map<String, Object>> physMtrList, String serNum) {
    private boolean bizErrCheck1(List<Map<String, Object>> physMtrList, String serNum, String mdseCd) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]
        int bwMtrCnt = 0;
        int totMtrCnt = 0;
        int clMtrCnt = 0;
        for (Map<String, Object> physMtr : physMtrList) {
            if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(BW_MTR_FLG))) {
                bwMtrCnt++;
            } else if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(TOT_MTR_FLG))) {
                totMtrCnt++;
            } else if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(COLOR_MTR_FLG))) {
                clMtrCnt++;
            }
        }
        // Check 04
        if ((bwMtrCnt > 1) || (totMtrCnt > 1) || (clMtrCnt > 1)) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0527E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0527E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return false;
        }
        // Check 05
        if (bwMtrCnt == 0) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0528E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0528E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return false;
        }
        // Check 06
        if (totMtrCnt == 0) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0529E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0529E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return false;
        }

        return true;
    }

    /**
     * @param physMtrList
     * @param serNum
     * @param mdseCd
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private boolean bizErrCheck2(List<Map<String, Object>> physMtrList, String serNum) {
    private boolean bizErrCheck2(List<Map<String, Object>> physMtrList, String serNum, String mdseCd) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]
        int bwMtrCnt = 0;
        int totMtrCnt = 0;
        int clMtrCnt = 0;
        for (Map<String, Object> physMtr : physMtrList) {
            if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(BW_MTR_FLG))) {
                bwMtrCnt++;
            } else if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(TOT_MTR_FLG))) {
                totMtrCnt++;
            } else if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(COLOR_MTR_FLG))) {
                clMtrCnt++;
            }
        }
        // Check 04
        if ((bwMtrCnt > 1) || (totMtrCnt > 1) || (clMtrCnt > 1)) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0527E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0527E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return false;
        }

        // Check 07
        if (bwMtrCnt == 0 && totMtrCnt == 0) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0526E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0526E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return false;
        }
        // Check 08
        if (bwMtrCnt > 0 && totMtrCnt > 0) {
            // START 2017/02/08 K.Kojima [QC#16600,MOD]
            // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0527E, new String[] {serNum }));
            bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0527E, new String[] {serNum, mdseCd }));
            // END 2017/02/08 K.Kojima [QC#16600,MOD]
            return false;
        }
        // Check 09
        if (bwMtrCnt > 0 && clMtrCnt > 0) {
            if (totMtrCnt == 0) {
                // START 2017/02/08 K.Kojima [QC#16600,MOD]
                // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0529E, new String[] {serNum }));
                bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0529E, new String[] {serNum, mdseCd }));
                // END 2017/02/08 K.Kojima [QC#16600,MOD]
                return false;
            }
        }
        return true;
    }

    /**
     * checkDuplicate
     * @param physMtrList
     * @param serNum
     * @param mdseCd
     * @param mtrReadDt
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private boolean checkDuplicate(List<Map<String, Object>> physMtrList, String serNum, String mtrReadDt) {
    private boolean checkDuplicate(List<Map<String, Object>> physMtrList, String serNum, String mdseCd, String mtrReadDt) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]
        BigDecimal svcPhysMtrPk = null;
        for (Map<String, Object> physMtr : physMtrList) {
            svcPhysMtrPk = (BigDecimal) physMtr.get(SVC_PHYS_MTR_PK);
            if (!getMtrReadForDupCheck(svcPhysMtrPk, mtrReadDt)) {
                // START 2017/02/08 K.Kojima [QC#16600,MOD]
                // bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0530E, new String[] {serNum }));
                bizErrorProcess(S21MessageFunc.clspGetMessage(NSAM0530E, new String[] {serNum, mdseCd }));
                // END 2017/02/08 K.Kojima [QC#16600,MOD]
                return false;

            }
        }
        return true;
    }

    /**
     * execApi
     * @param rossMtrReadList
     * @param physMtrList
     * @param serNum
     * @param mdseCd
     * @param mtrReadDt
     * @param pMsg
     * @param updInfoList
     * @param dsMtrIntfcTMsgList
     * @param dsMsgTMsgList
     * @return
     */
    // START 2017/06/06 K.Kitachi [QC#18342, MOD]
//    // START 2017/02/08 K.Kojima [QC#16600,MOD]
//    // private int execApi(List<Map<String, Object>> rossMtrReadList, List<Map<String, Object>> physMtrList, String serNum, String mtrReadDt) {
//    private int execApi(List<Map<String, Object>> rossMtrReadList, List<Map<String, Object>> physMtrList, String serNum, String mdseCd, String mtrReadDt) {
//    // END 2017/02/08 K.Kojima [QC#16600,MOD]
    private int execApi(List<Map<String, Object>> rossMtrReadList, List<Map<String, Object>> physMtrList, String serNum, String mdseCd, String mtrReadDt, NSZC051001PMsg pMsg, List<Map<String, Object>> updInfoList, List<DS_MTR_INTFCTMsg> dsMtrIntfcTMsgList, List<DS_MSGTMsg> dsMsgTMsgList) {
    // END 2017/06/06 K.Kitachi [QC#18342, MOD]
        int rossMtrReadCnt = rossMtrReadList.size();
        int physMtrCnt = physMtrList.size();

        Map<String, Object> updInfo1 = new HashMap<String, Object>();
        Map<String, Object> updInfo2 = new HashMap<String, Object>();
        // START 2017/06/06 K.Kitachi [QC#18342, DEL]
//        List<Map<String, Object>> updInfoList = new ArrayList<Map<String, Object>>();
        // END 2017/06/06 K.Kitachi [QC#18342, DEL]

        BigDecimal mtrTot = BigDecimal.ZERO;
        BigDecimal mtrBw = BigDecimal.ZERO;
        BigDecimal mtrCl = BigDecimal.ZERO;
        BigDecimal mtrTstTot = BigDecimal.ZERO;
        BigDecimal mtrTstBw = BigDecimal.ZERO;
        BigDecimal mtrTstCl = BigDecimal.ZERO;

        BigDecimal totPhysMtrPk = null;
        String totMtrLbCd = null;
        BigDecimal bwPhysMtrPk = null;
        String bwMtrLbCd = null;
        BigDecimal clPhysMtrPk = null;
        String clMtrLbCd = null;

        // START 2017/06/06 K.Kitachi [QC#18342, DEL]
//        NSZC051001PMsg pMsg = new NSZC051001PMsg();
        // END 2017/06/06 K.Kitachi [QC#18342, DEL]

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, DS_MTR_READ_TP.ROSS);
        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ROSS);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.rgtnUsrId, BATCH_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
        // START 2017/02/08 K.Kojima [QC#16600,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
        // END 2017/02/08 K.Kojima [QC#16600,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.mtrReadDt, mtrReadDt);
        ZYPEZDItemValueSetter.setValue(pMsg.rgtnMtrDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);

        // Get MtrLbCd
        for (Map<String, Object> physMtr : physMtrList) {
            if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(TOT_MTR_FLG))) {
                totPhysMtrPk = (BigDecimal) physMtr.get(SVC_PHYS_MTR_PK);
                totMtrLbCd = (String) physMtr.get(MTR_LB_CD);
            } else if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(BW_MTR_FLG))) {
                bwPhysMtrPk = (BigDecimal) physMtr.get(SVC_PHYS_MTR_PK);
                bwMtrLbCd = (String) physMtr.get(MTR_LB_CD);
            } else if (ZYPConstant.FLG_ON_Y.equals((String) physMtr.get(COLOR_MTR_FLG))) {
                clPhysMtrPk = (BigDecimal) physMtr.get(SVC_PHYS_MTR_PK);
                clMtrLbCd = (String) physMtr.get(MTR_LB_CD);
            }
        }

        if (rossMtrReadCnt == 2) {

            mtrTot = (BigDecimal) rossMtrReadList.get(1).get(READ_MTR_CNT);
            mtrTstTot = (BigDecimal) rossMtrReadList.get(1).get(TEST_MTR_CNT);

            mtrBw = (BigDecimal) rossMtrReadList.get(0).get(READ_MTR_CNT);
            mtrTstBw = (BigDecimal) rossMtrReadList.get(0).get(TEST_MTR_CNT);

            // Total
            ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).mdlMtrLbCd, totMtrLbCd);
            ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).readMtrCnt, mtrTot);
            ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).testMtrCnt, mtrTstTot);

            // Check 11
            if (!checkLastMtrCnt(totPhysMtrPk, mtrTot, mtrReadDt)) {
                return VAL_CHECK_ERR;
            }

            // BW
            ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(1).mdlMtrLbCd, bwMtrLbCd);
            ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(1).readMtrCnt, mtrBw);
            ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(1).testMtrCnt, mtrTstBw);

            // Check 11
            if (!checkLastMtrCnt(bwPhysMtrPk, mtrBw, mtrReadDt)) {
                return VAL_CHECK_ERR;
            }

            pMsg.meterList.setValidCount(2);

            updInfo1.put(ROSS_INTFC_MTR_READ_PK, (BigDecimal) rossMtrReadList.get(0).get(ROSS_INTFC_MTR_READ_PK));
            updInfo1.put(MTR_LB_CD, totMtrLbCd);
            updInfoList.add(updInfo1);
            updInfo2.put(ROSS_INTFC_MTR_READ_PK, (BigDecimal) rossMtrReadList.get(1).get(ROSS_INTFC_MTR_READ_PK));
            updInfo2.put(MTR_LB_CD, bwMtrLbCd);
            updInfoList.add(updInfo2);

            if (physMtrCnt == MAX_PHYS_MTR_CNT) {
                // Color
                mtrCl = mtrTot.subtract(mtrBw);
                mtrTstCl = mtrTstTot.subtract(mtrTstBw);

                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(2).mdlMtrLbCd, clMtrLbCd);
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(2).readMtrCnt, mtrCl);
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(2).testMtrCnt, mtrTstCl);

                // Check 11
                if (!checkLastMtrCnt(clPhysMtrPk, mtrCl, mtrReadDt)) {
                    return VAL_CHECK_ERR;
                }

                pMsg.meterList.setValidCount(MAX_PHYS_MTR_CNT);

            }

        } else if (rossMtrReadCnt == 1) {

            mtrTot = (BigDecimal) rossMtrReadList.get(0).get(READ_MTR_CNT);
            mtrBw = (BigDecimal) rossMtrReadList.get(0).get(READ_MTR_CNT);
            mtrCl = (BigDecimal) rossMtrReadList.get(0).get(READ_MTR_CNT);
            mtrTstTot = (BigDecimal) rossMtrReadList.get(0).get(TEST_MTR_CNT);
            mtrTstBw = (BigDecimal) rossMtrReadList.get(0).get(TEST_MTR_CNT);
            mtrTstCl = (BigDecimal) rossMtrReadList.get(0).get(TEST_MTR_CNT);

            if (physMtrCnt == 2) {
                // Total
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).mdlMtrLbCd, totMtrLbCd);
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).readMtrCnt, mtrTot);
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).testMtrCnt, mtrTstTot);

                // Check 11
                if (!checkLastMtrCnt(totPhysMtrPk, mtrTot, mtrReadDt)) {
                    return VAL_CHECK_ERR;
                }

                // Color
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(1).mdlMtrLbCd, clMtrLbCd);
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(1).readMtrCnt, mtrCl);
                ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(1).testMtrCnt, mtrTstCl);

                // Check 11
                if (!checkLastMtrCnt(clPhysMtrPk, mtrCl, mtrReadDt)) {
                    return VAL_CHECK_ERR;
                }

                pMsg.meterList.setValidCount(2);

                updInfo1.put(ROSS_INTFC_MTR_READ_PK, (BigDecimal) rossMtrReadList.get(0).get(ROSS_INTFC_MTR_READ_PK));
                updInfo1.put(MTR_LB_CD, totMtrLbCd);
                updInfoList.add(updInfo1);

                pMsg.meterList.setValidCount(2);
            } else if (physMtrCnt == 1) {
                if (ZYPConstant.FLG_ON_Y.equals((String) physMtrList.get(0).get(TOT_MTR_FLG))) {
                    // Total
                    ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).mdlMtrLbCd, totMtrLbCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).readMtrCnt, mtrTot);
                    ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).testMtrCnt, mtrTstTot);
                    // Check 11
                    if (!checkLastMtrCnt(totPhysMtrPk, mtrTot, mtrReadDt)) {
                        return VAL_CHECK_ERR;
                    }
                    pMsg.meterList.setValidCount(1);

                    updInfo1.put(ROSS_INTFC_MTR_READ_PK, (BigDecimal) rossMtrReadList.get(0).get(ROSS_INTFC_MTR_READ_PK));
                    updInfo1.put(MTR_LB_CD, totMtrLbCd);
                    updInfoList.add(updInfo1);

                } else if (ZYPConstant.FLG_ON_Y.equals((String) physMtrList.get(0).get(BW_MTR_FLG))) {
                    // BW
                    ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).mdlMtrLbCd, bwMtrLbCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).readMtrCnt, mtrBw);
                    ZYPEZDItemValueSetter.setValue(pMsg.meterList.no(0).testMtrCnt, mtrTstBw);
                    // Check 11
                    if (!checkLastMtrCnt(bwPhysMtrPk, mtrBw, mtrReadDt)) {
                        return VAL_CHECK_ERR;
                    }
                    pMsg.meterList.setValidCount(1);

                    updInfo1.put(ROSS_INTFC_MTR_READ_PK, (BigDecimal) rossMtrReadList.get(0).get(ROSS_INTFC_MTR_READ_PK));
                    updInfo1.put(MTR_LB_CD, bwMtrLbCd);
                    updInfoList.add(updInfo1);

                }
            }
        }

        NSZC051001 api = new NSZC051001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        // START 2017/06/06 K.Kitachi [QC#18342, ADD]
        BigDecimal dsMtrIntfcPk;
        BigDecimal errGrpSq = null;
        DS_MTR_INTFCTMsg dsMtrIntfcTMsg;
        for (int i = 0; i < pMsg.rsltPrmList.getValidCount(); i++) {
            dsMtrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MTR_INTFC_SQ);
            if (DS_MTR_PROC_STS.ERROR.equals(pMsg.dsMtrProcStsCd.getValue()) && !ZYPCommonFunc.hasValue(errGrpSq)) {
                errGrpSq = dsMtrIntfcPk.negate();
            }
            dsMtrIntfcTMsg = createDsMtrIntfcTMsg(pMsg, dsMtrIntfcPk, errGrpSq, i);
            dsMtrIntfcTMsgList.add(dsMtrIntfcTMsg);
            if (ZYPCommonFunc.hasValue(pMsg.dsMsgTxt)) {
                dsMsgTMsgList.add(createDsMsgTMsg(pMsg, dsMtrIntfcPk));
            }
        }
        // END 2017/06/06 K.Kitachi [QC#18342, ADD]

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // START 2017/06/06 K.Kitachi [QC#18342, DEL]
//            List<String> errIdList = S21ApiUtil.getXxMsgIdList(pMsg);
//            String procCd = null;
//            String procTxt = null;
//            // API Fault
//            for (Map<String, Object> rossUpdInfo : updInfoList) {
//                // START 2017/02/08 K.Kojima [QC#16600,MOD]
//                // String msg = S21MessageFunc.clspGetMessage(NSAM0003E, new String[] {NSZC051001, errIdList.get(0), "serNum=" + serNum });
//                String msg = S21MessageFunc.clspGetMessage(NSAM0003E, new String[] {NSZC051001, errIdList.get(0), "serNum=" + serNum + ",mdseCd=" + mdseCd });
//                // END 2017/02/08 K.Kojima [QC#16600,MOD]
//                bizErrorProcess(msg);
//
//                // START 2017/02/08 K.Kojima [QC#16600,MOD]
//                // Map<String, Object> rslt = getDsMsgTxt(serNum, (String) rossUpdInfo.get(MTR_LB_CD), mtrReadDt);
//                Map<String, Object> rslt = getDsMsgTxt(serNum, mdseCd, (String) rossUpdInfo.get(MTR_LB_CD), mtrReadDt);
//                // END 2017/02/08 K.Kojima [QC#16600,MOD]
//                if (rslt != null) {
//                    procCd = (String) rslt.get(DS_MTR_PROC_STS_CD);
//                    procTxt = (String) rslt.get(DS_MSG_TXT);
//                } else {
//                    procCd = DS_MTR_PROC_STS_CD_ERR;
//                    procTxt = msg;
//                }
//                updateApiErrStatus((BigDecimal) rossUpdInfo.get(ROSS_INTFC_MTR_READ_PK), (String) rossUpdInfo.get(MTR_LB_CD), procCd, procTxt);
//            }
            // END 2017/06/06 K.Kitachi [QC#18342, DEL]
            return API_EXEC_ERR;
        }

        // API Success
        for (Map<String, Object> rossUpdInfo : updInfoList) {
            updateNormalStatus((BigDecimal) rossUpdInfo.get(ROSS_INTFC_MTR_READ_PK), (String) rossUpdInfo.get(MTR_LB_CD));
        }
        return 0;

    }

    /**
     * updateApiErrStatus
     * @param rossIntfcMtrReadPk
     * @param mtrLbCd
     * @param rslt
     */
    private void updateApiErrStatus(BigDecimal rossIntfcMtrReadPk, String mtrLbCd, String procCd, String procTxt) {

        ROSS_INTFC_MTR_READTMsg tMsg = new ROSS_INTFC_MTR_READTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrReadPk, rossIntfcMtrReadPk);

        tMsg = (ROSS_INTFC_MTR_READTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
        }

        if (DS_MTR_PROC_STS_CD_WARN.equals(procCd)) {
            // WARN
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrProcCd, ROSS_INTFC_MTR_PROC_CD_WARN);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrProcCd, ROSS_INTFC_MTR_PROC_CD_ERR);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, mtrLbCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrProcTxt, procTxt);
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
        }

        termCd = TERM_CD.WARNING_END;

    }

    /**
     * updateNormalStatus
     * @param rossIntfcMtrReadPk
     * @param mtrLbCd
     */
    private void updateNormalStatus(BigDecimal rossIntfcMtrReadPk, String mtrLbCd) {
        ROSS_INTFC_MTR_READTMsg tMsg = new ROSS_INTFC_MTR_READTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrReadPk, rossIntfcMtrReadPk);

        tMsg = (ROSS_INTFC_MTR_READTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
        }

        ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrProcCd, ROSS_INTFC_MTR_PROC_CD_PROCESSED);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, mtrLbCd);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
        }
    }

    /**
     * updateErrorStatus
     * @param rossMtrReadList
     */
    private void updateErrorStatus(List<Map<String, Object>> rossMtrReadList) {
        for (Map<String, Object> rossMtrRead : rossMtrReadList) {
            BigDecimal rossIntfcMtrReadPk = (BigDecimal) rossMtrRead.get(ROSS_INTFC_MTR_READ_PK);
            ROSS_INTFC_MTR_READTMsg tMsg = new ROSS_INTFC_MTR_READTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrReadPk, rossIntfcMtrReadPk);
            tMsg = (ROSS_INTFC_MTR_READTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
            }

            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrProcCd, ROSS_INTFC_MTR_PROC_CD_ERR);
            // START 2017/05/26 K.Kojima [QC#18710,ADD]
            if (this.rossIfMtrProcTxt != null && this.rossIfMtrProcTxt.length() > ROSS_INTFC_MTR_PROC_TXT_LENGTH) {
                this.rossIfMtrProcTxt = this.rossIfMtrProcTxt.substring(0, ROSS_INTFC_MTR_PROC_TXT_LENGTH);
            }
            // END 2017/05/26 K.Kojima [QC#18710,ADD]
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrProcTxt, rossIfMtrProcTxt);
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
            }
        }
        termCd = TERM_CD.WARNING_END;
    }

    /**
     * getSerialMtrReadList
     * @param serNum
     * @param mdseCd
     * @param mtrReadDt
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private List<Map<String, Object>> getSerialMtrReadList(String serNum, String mtrReadDt) {
    private List<Map<String, Object>> getSerialMtrReadList(String serNum, String mdseCd, String mtrReadDt) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SER_NUM", serNum);
        // START 2017/02/08 K.Kojima [QC#16600,ADD]
        queryParam.put("MDSE_CD", mdseCd);
        // END 2017/02/08 K.Kojima [QC#16600,ADD]
        queryParam.put("MTR_READ_DT", mtrReadDt);
        queryParam.put("ROSS_INTFC_MTR_PROC_CD", ROSS_INTFC_MTR_PROC_CD_NORMAL);
        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSerialMtrReadList", queryParam);
        return ret;
    }

    /**
     * getSvcMachMstr
     * @param serNum
     * @param mdseCd
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private Map<String, Object> getSvcMachMstr(String serNum) {
    private Map<String, Object> getSvcMachMstr(String serNum, String mdseCd) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SER_NUM", serNum);
        // START 2017/02/08 K.Kojima [QC#16600,ADD]
        queryParam.put("MDSE_CD", mdseCd);
        // END 2017/02/08 K.Kojima [QC#16600,ADD]
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachMstr", queryParam);
        return ret;
    }

    /**
     * getPhysMtrList
     * @param svcMachMstrPk
     * @return
     */
    private List<Map<String, Object>> getPhysMtrList(BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SVC_MACH_MSTR_PK", svcMachMstrPk);
        queryParam.put("ACTV_FLG", ZYPConstant.FLG_ON_Y);
        queryParam.put("SALES_DATE", this.salesDate);
        queryParam.put("BW_MTR_FLG", ZYPConstant.FLG_ON_Y);
        queryParam.put("COLOR_MTR_FLG", ZYPConstant.FLG_ON_Y);
        queryParam.put("TOT_MTR_FLG", ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPhysMtrList", queryParam);
        return ret;
    }

    /**
     * getMtrReadForDupCheck
     * @param svcPhysMtrPk
     * @param mtrReadDt
     * @return
     */
    private boolean getMtrReadForDupCheck(BigDecimal svcPhysMtrPk, String mtrReadDt) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SVC_PHYS_MTR_PK", svcPhysMtrPk);
        queryParam.put("DS_MTR_READ_TP_GRP_CD", "B");
        queryParam.put("MTR_READ_DT", mtrReadDt);
        queryParam.put("EST_FLG", ZYPConstant.FLG_OFF_N);
        // mod start 2017/04/11 CSA Defect#18242
//        queryParam.put("VLD_MTR_FLG", ZYPConstant.FLG_OFF_N);
        queryParam.put("VLD_MTR_FLG", ZYPConstant.FLG_ON_Y);
        // mod end 2017/04/11 CSA Defect#18242
        BigDecimal ret = (BigDecimal) this.ssmBatchClient.queryObject("getMtrReadForDupCheck", queryParam);
        if (ret.intValue() > 0) {
            return false;
        }
        return true;
    }

    /**
     * getDsMsgTxt
     * @param serNum
     * @param mdseCd
     * @param mtrLbCd
     * @param mtrReadDt
     * @return
     */
    // START 2017/02/08 K.Kojima [QC#16600,MOD]
    // private Map<String, Object> getDsMsgTxt(String serNum, String mtrLbCd, String mtrReadDt) {
    private Map<String, Object> getDsMsgTxt(String serNum, String mdseCd, String mtrLbCd, String mtrReadDt) {
    // END 2017/02/08 K.Kojima [QC#16600,MOD]
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SER_NUM", serNum);
        // START 2017/02/08 K.Kojima [QC#16600,ADD]
        queryParam.put("MDSE_CD", mdseCd);
        // END 2017/02/08 K.Kojima [QC#16600,ADD]
        queryParam.put("MDL_MTR_LB_CD", mtrLbCd);
        queryParam.put("MTR_READ_DT", mtrReadDt);
        queryParam.put("ST_WARN", DS_MTR_PROC_STS_CD_WARN);
        queryParam.put("ST_ERR", DS_MTR_PROC_STS_CD_ERR);
        queryParam.put("DS_MSG_TRX_NM", DS_MTR_INTFC_PK);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsMsgTxt", queryParam);
        return ret;
    }

    /**
     * checkLastMtrCnt
     * @param svcPhysMtrPk
     * @param readMtrCnt
     * @param mtrReadDt
     * @return
     */
    private boolean checkLastMtrCnt(BigDecimal svcPhysMtrPk, BigDecimal readMtrCnt, String mtrReadDt) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SVC_PHYS_MTR_PK", svcPhysMtrPk);
        // mod start 2017/04/11 CSA Defect#18242
//        queryParam.put("DS_MTR_READ_TP_GRP_CD", "B");
        queryParam.put("DS_MTR_READ_TP_GRP_CD", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        queryParam.put("EST_FLG", ZYPConstant.FLG_OFF_N);
//        queryParam.put("VLD_MTR_FLG", ZYPConstant.FLG_OFF_N);
        queryParam.put("VLD_MTR_FLG", ZYPConstant.FLG_ON_Y);
        // mod end 2017/04/11 CSA Defect#18242
        BigDecimal ret = (BigDecimal) this.ssmBatchClient.queryObject("getLastMtrCnt", queryParam);
        if (!ZYPCommonFunc.hasValue(ret)) {
            return true;
        }
        if (ret.intValue() > readMtrCnt.intValue()) {
            return false;
        }
        return true;
    }

    /**
     * bizErrorProcess
     * @param message
     */
    private void bizErrorProcess(String message) {
        S21InfoLogOutput.println(message);
        this.errMsgList.add(message);
        rossIfMtrProcTxt = message;
    }

    /**
     * sendApplErrMail
     */
    private void sendErrMail() {

        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress addrFrom = null;
        if (!addrFromList.isEmpty()) {
            addrFrom = addrFromList.get(0);
        }
        // Get Mail Address To
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_TO_GROUP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_TO_GROUP_ID });
        }

        // 10.3. Create message for Body
        StringBuilder msgBuf = new StringBuilder();
        msgBuf.append(LINE_FEED_CODE);
        for (int i = 0; i < this.errMsgList.size(); i++) {
            msgBuf.append(errMsgList.get(i));
            msgBuf.append(LINE_FEED_CODE);
        }
        String message = msgBuf.toString();
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_TEMPLATE_ID });
        }
        template.setTemplateParameter(EMAIL_PARAM_BATCH_ID, BATCH_ID);
        String currentTime = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT_MM_DD_YYYY_HH_MM);
        template.setTemplateParameter(EMAIL_PARAM_DATE, currentTime);
        template.setTemplateParameter(EMAIL_PARAM_MSG, message);

        // Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(addrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    // START 2017/06/06 K.Kitachi [QC#18342, ADD]
    private DS_MTR_INTFCTMsg createDsMtrIntfcTMsg(NSZC051001PMsg pMsg, BigDecimal dsMtrIntfcPk, BigDecimal errGrpSq, int idx) {
        NSZC051001_rsltPrmListPMsg rsltPrm = pMsg.rsltPrmList.no(idx);
        DS_MTR_INTFCTMsg tMsg = new DS_MTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMtrIntfcPk, dsMtrIntfcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadSrcTpCd, pMsg.mtrReadSrcTpCd);
        BigDecimal svcPhysMtrReadGrpSq = rsltPrm.svcPhysMtrReadGrpSq.getValue();
        if (DS_MTR_PROC_STS.ERROR.equals(pMsg.dsMtrProcStsCd.getValue())) {
            svcPhysMtrReadGrpSq = errGrpSq;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, pMsg.serNum);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.mdseCd_O);
        ZYPEZDItemValueSetter.setValue(tMsg.svcPhysMtrPk, rsltPrm.svcPhysMtrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlMtrLbCd, rsltPrm.mdlMtrLbCd);
        ZYPEZDItemValueSetter.setValue(tMsg.readMtrCnt, rsltPrm.readMtrCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadDt, pMsg.mtrReadDt);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMtrProcStsCd, pMsg.dsMtrProcStsCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnUsrId, pMsg.rgtnUsrId);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMtrProcTs, this.procTs);
        return tMsg;
    }

    private DS_MSGTMsg createDsMsgTMsg(NSZC051001PMsg pMsg, BigDecimal dsMtrIntfcPk) {
        DS_MSGTMsg tMsg = new DS_MSGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MSG_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsMsgTrxNum, String.valueOf(dsMtrIntfcPk));
        ZYPEZDItemValueSetter.setValue(tMsg.dsMsgTrxNm, DS_MTR_INTFC_PK);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMsgTxt, pMsg.dsMsgTxt);
        return tMsg;
    }

    private void insertDsMtrIntfc(List<DS_MTR_INTFCTMsg> createTMsg) {
        DS_MTR_INTFCTMsg[] createList = new DS_MTR_INTFCTMsg[createTMsg.size()];
        S21FastTBLAccessor.insert(createTMsg.toArray(createList));
    }

    private void insertDsMsg(List<DS_MSGTMsg> createDsMsgTMsg) {
        DS_MSGTMsg[] createList = new DS_MSGTMsg[createDsMsgTMsg.size()];
        S21FastTBLAccessor.insert(createDsMsgTMsg.toArray(createList));
    }
    // END 2017/06/06 K.Kitachi [QC#18342, ADD]
}
