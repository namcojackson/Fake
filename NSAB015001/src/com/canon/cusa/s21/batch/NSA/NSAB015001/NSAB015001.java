/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB015001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import business.db.MTR_EST_SELTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * Auto Estimation Selection.
 * <p>
 * Divide auto estimation target machines into chunks for parallel
 * processing.
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2017/06/02   Hitachi         N.Arai          Update          QC#18297
 * 2018/05/28   CITS            T.Wada          Update          QC#15410(Sol#246)
 * 2019/10/08   Hitachi         K.Kitachi       Update          QC#53964
 * 2022/08/12   Hitachi         N.Takatsu       Update          QC#60186
 * 2023/02/03   CITS            E.Sanchez       Update          QC#61138
 * </pre>
 */
public class NSAB015001 extends S21BatchMain {

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * System Error : @
     */
    private static final String NSAM0219E = "NSAM0219E";

    /**
     * Service Machine Master PK [@] cannot be inserted.
     */
    private static final String NSAM0349E = "NSAM0349E";

    /**
     * Client for prepared statement
     */
    private S21SsmLowLevelCodingClient ssmLlcClnt = null;

    /**
     * Batch client
     */
    private S21SsmBatchClient ssmBatClnt = null;

    /**
     * Termination code
     */
    private TERM_CD termCd = null;

    /**
     * Global company code
     */
    private String glblCmpyCd = null;

    /**
     * Parallel process total task count
     */
    private BigDecimal parProcTotTaskCnt = null;

    /**
     * Normal record count
     */
    private int normRecCnt = 0;

    /**
     * Error record count
     */
    private int errRecCnt = 0;

    /**
     * Error message list
     */
    private List<String> errMsgList;

    /**
     * Error TMsg list
     */
    private List<EZDTMsg> errTMsgList;

    /**
     * System time stamp
     */
    private String sysTs = null;

    /**
     * Sales date
     */
    private String slsDt = null;

    // START 2017/06/02 N.Arai [QC#18297, MOD]
    /**
     * Error Msg : Sales date cannot be obtained.
     */
    private static final String NSAM0178E = "NSAM0178E";

    /**
     * Meter Estimation Type
     */
    private String mtrEstTp = null;

    /**
     * 10th of Following Month
     */
    private final int day10th = 10;

    /**
     * 20th of Following Month
     */
    private final int day20th = 20;

    // END 2017/06/02 N.Arai [QC#18297, MOD]

    // START 2019/10/08 K.Kitachi [QC#53964, ADD]
    /**
     * Mode : Daily
     */
    private static final String MODE_DAILY = "01";

    /**
     * INT_4 : 4
     */
    private static final int INT_4 = 4;

    /**
     * INT_6 : 6
     */
    private static final int INT_6 = 6;

    /**
     * INT_8 : 8
     */
    private static final int INT_8 = 8;

    /**
     * START_DAY : 01
     */
    private static final String START_DAY = "01";
    // END 2019/10/08 K.Kitachi [QC#53964, ADD]

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        new NSAB015001().executeBatch(NSAB015001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        ssmLlcClnt = S21SsmLowLevelCodingClient.getClient(NSAB015001.class);
        ssmBatClnt = S21SsmBatchClient.getClient(NSAB015001.class);
        termCd = TERM_CD.NORMAL_END;
        glblCmpyCd = getGlobalCompanyCode();
        parProcTotTaskCnt = ZYPCodeDataUtil.getNumConstValue("NSAB0180_PAR_PROC_TOT_TASK_CNT", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(parProcTotTaskCnt)) {
            parProcTotTaskCnt = BigDecimal.ONE;
        }
        errMsgList = new ArrayList<String>();
        errTMsgList = new ArrayList<EZDTMsg>();
        sysTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        slsDt = ZYPDateUtil.getSalesDate();
    }

    @Override
    protected void mainRoutine() {

        delTempTbl();
        if (hasMsg()) {
            return;
        }

        commit();

        // START 2017/06/02 N.Arai [QC#18297, MOD]
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E);
        }
        // START 2019/10/08 K.Kitachi [QC#53964, DEL]
//        int slsDay = Integer.parseInt(slsDt.substring(6, 8));
//        Calendar parsedCalendar = Calendar.getInstance();
//        parsedCalendar.clear();
//        parsedCalendar.set(Integer.parseInt(slsDt.substring(0, 4)), Integer.parseInt(slsDt.substring(4, 6)) - 1, 1);
//        int lastDay = parsedCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//        if (day10th == slsDay) {
//            mtrEstTp = MTR_EST_TP._10TH_OF_FOLLOWING_MONTH;
//        } else if (day20th == slsDay) {
//            mtrEstTp = MTR_EST_TP._20TH_OF_FOLLOWING_MONTH;
//        } else if (slsDay == lastDay) {
//            mtrEstTp = MTR_EST_TP.END_OF_CURRENT_MONTH;
//        } else {
//            return;
//        }
        // END 2019/10/08 K.Kitachi [QC#53964, DEL]
        // END 2017/06/02 N.Arai [QC#18297, MOD]

        // START 2019/10/08 K.Kitachi [QC#53964, ADD]
        setMtrEstTp();
        if (!ZYPCommonFunc.hasValue(mtrEstTp)) {
            return;
        }
        // END 2019/10/08 K.Kitachi [QC#53964, ADD]

        divEstCalcTrgt();

        cratErrLog();
    }

    @Override
    protected void termRoutine() {
        sendEmail();
        setTermState(termCd, normRecCnt, errRecCnt);
    }

    private void delTempTbl() {
        MTR_EST_SELTMsg tMsg = new MTR_EST_SELTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
        String rtnCd = tMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !S21FastTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {
            addMsg(null, null, NSAM0219E, "Work Table Deletion cannot be processed.");
        }
    }

    private void divEstCalcTrgt() {
        int normRecCnt = 0;
        int errRecCnt = 0;
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        try {
            prepStmt = getPrepStmt();
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {
                BigDecimal svcMachMstrPk = rsltSet.getBigDecimal("SVC_MACH_MSTR_PK");
                String serNum = rsltSet.getString("SER_NUM");
                BigDecimal dsContrPk = rsltSet.getBigDecimal("DS_CONTR_PK");
                String dsContrNum = rsltSet.getString("DS_CONTR_NUM");
                BigDecimal dsContrDtlPk = rsltSet.getBigDecimal("DS_CONTR_DTL_PK");
                BigDecimal parProcTaskNum = rsltSet.getBigDecimal("PAR_PROC_TASK_NUM");

                // QC#15410(Sol#246) Add Start
                BigDecimal cnt = countMtrEstSelRec(svcMachMstrPk);
                if (ZYPCommonFunc.hasValue(cnt) && cnt.intValue() > 0) {
                    continue;
                }
                // QC#15410(Sol#246) Add End

                MTR_EST_SELTMsg tMsg = new MTR_EST_SELTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrEstSelPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MTR_EST_SEL_SQ"));
                ZYPEZDItemValueSetter.setValue(tMsg.mtrEstSelGrpNum, parProcTaskNum);
                ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.serNum, serNum);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, dsContrNum);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrEstStsCd, MTR_EST_STS.ACTIVE);
                tMsg.selPassNum.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.mtrEstProcDt, slsDt);
                S21FastTBLAccessor.insert(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    normRecCnt++;
                    commit();
                } else {
                    addMsg(svcMachMstrPk, NSAM0349E, svcMachMstrPk.toPlainString());
                    errRecCnt++;
                    rollback();
                }
            }
            this.normRecCnt = normRecCnt;
            this.errRecCnt = errRecCnt;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prepStmt, rsltSet);
        }
    }

    private PreparedStatement getPrepStmt() throws SQLException {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setFetchSize(1000);
        execPrm.setMaxRows(0);
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("parProcTotTaskCnt", parProcTotTaskCnt);
        // prm.put("dsContrStsCd", DS_CONTR_STS.EFFECTIVE);
        prm.put("slsDt", slsDt);
        // START 2019/10/08 K.Kitachi [QC#53964, ADD]
        if (MTR_EST_TP.END_OF_CURRENT_MONTH.equals(mtrEstTp)) {
            prm.put("slsDt", getMonthEndDate(slsDt));
            // START 2022/08/12 N.Takatsu [QC#60186, ADD]
            prm.put("endOfCurrentMonth", mtrEstTp);
            // END 2022/08/12 N.Takatsu [QC#60186, ADD]
        }
        // END 2019/10/08 K.Kitachi [QC#53964, ADD]
        // START 2017/06/02 N.Arai [QC#18297, MOD]
        prm.put("mtrEstTpCd", mtrEstTp);
        // END 2017/06/02 N.Arai [QC#18297, MOD]
        // START 2023/02/03 E.Sanchez [QC#61138, ADD]
        prm.put("skipRecovTpCd", SKIP_RECOV_TP.SKIP);
        // END 2023/02/03 E.Sanchez [QC#61138, ADD]
        return ssmLlcClnt.createPreparedStatement("divEstCalcTrgt", prm, execPrm);
    }

    private void addMsg(BigDecimal svcMachMstrPk, String errCd, String... prm) {
        // Message
        String errMsg = S21MessageFunc.clspGetMessage(errCd, prm);
        errMsgList.add(errMsg);

        // Log
        SVC_BAT_ERR_LOGTMsg tMsg = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, "NSAB0150");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogTs, sysTs);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_01, "SVC_MACH_MSTR_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_01, numToStr(svcMachMstrPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrMsgTxt, errMsg);
        errTMsgList.add(tMsg);
    }

    private boolean hasMsg() {
        return errMsgList.size() > 0;
    }

    private void sendEmail() {
        if (hasMsg()) {
            NSXC001001SendMailForErrorInfo mailer = new NSXC001001SendMailForErrorInfo();
            mailer.addErrMsgList(errMsgList);
            String rtnCd = mailer.sendMail(glblCmpyCd, NSAB015001.class.getSimpleName());
            if (ZYPCommonFunc.hasValue(rtnCd)) {
                S21InfoLogOutput.println(rtnCd);
            }
        }
    }

    private void cratErrLog() {
        if (hasMsg()) {
            int cnt = S21FastTBLAccessor.insert(errTMsgList.toArray(new SVC_BAT_ERR_LOGTMsg[errTMsgList.size()]));
            if (errTMsgList.size() != cnt) {
                S21InfoLogOutput.println(NSAM0032E, new String[] {"SVC_BAT_ERR_LOG" });
            }
        }
    }

    private static String numToStr(BigDecimal num) {
        if (ZYPCommonFunc.hasValue(num)) {
            return num.toPlainString();
        } else {
            return null;
        }
    }

    // QC#15410(Sol#246) Add Start
    private BigDecimal countMtrEstSelRec(BigDecimal svcMachMstrPk) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        prm.put("slsDt", slsDt);
        return (BigDecimal) ssmBatClnt.queryObject("countMtrEstSelRec", prm);
    }
    // QC#15410(Sol#246) Add End

    // START 2019/10/08 K.Kitachi [QC#53964, ADD]
    private void setMtrEstTp() {
        if (isDailyMode()) {
            String monthEndBizDate = ZYPDateUtil.getEndOfMonthBusinessDayEx(glblCmpyCd, CAL_TP.CSA_GENERAL, slsDt.substring(0, INT_4), slsDt.substring(INT_4, INT_6));
            if (ZYPDateUtil.compare(slsDt, monthEndBizDate) == 0) {
                mtrEstTp = MTR_EST_TP.END_OF_CURRENT_MONTH;
            }
        } else {
            int slsDay = Integer.parseInt(slsDt.substring(INT_6, INT_8));
            if (slsDay == day10th) {
                mtrEstTp = MTR_EST_TP._10TH_OF_FOLLOWING_MONTH;
            }
            if (slsDay == day20th) {
                mtrEstTp = MTR_EST_TP._20TH_OF_FOLLOWING_MONTH;
            }
        }
    }

    private boolean isDailyMode() {
        String mode = getUserVariable1();
        if (ZYPCommonFunc.hasValue(mode) && mode.equals(MODE_DAILY)) {
            return true;
        }
        return false;
    }

    private String getMonthEndDate(String date) {
        String nextMonthStartDate = getNextMonthStartDate(date);
        return getPrevDay(nextMonthStartDate);
    }

    private String getNextMonthStartDate(String date) {
        String startDate = date.substring(0, INT_6) + START_DAY;
        return addMonths(startDate, 1);
    }

    private String addMonths(String date, int numberOfMonths) {
        SimpleDateFormat format = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, numberOfMonths);
        String returnDate = format.format(cal.getTime());
        return returnDate;
    }

    private String getPrevDay(String date) {
        return ZYPDateUtil.addDays(date, -1);
    }
    // END 2019/10/08 K.Kitachi [QC#53964, ADD]
}
