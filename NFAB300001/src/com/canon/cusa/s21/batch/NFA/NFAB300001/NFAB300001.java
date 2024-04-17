/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB300001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static com.canon.cusa.s21.batch.NFA.NFAB300001.constant.NFAB300001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.MAN_JRNL_ENTRY_DTLTMsg;
import business.db.MAN_JRNL_ENTRY_DTLTMsgArray;
import business.db.MAN_JRNL_ENTRY_HDRTMsg;
import business.parts.NFZC104001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC104001.NFZC104001;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Auto Reversal Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Hitachi         K.Kasai         Create          N/A
 * 2016/07/05   Hitachi         A.Kohinata      Update          QC#10692
 * 2016/07/14   Hitachi         J.Kim           Update          QC#10325
 * 2016/12/12   Fujitsu         H.Ikeda         Update          QC#15823
 *</pre>
 */
public class NFAB300001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Date */
    private String batchDt = null;

    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** Dc Contract success count */
    private int infoSccessCnt = 0;

    // 2016/07/05 QC#10692 Add Start
    /** Dc Contract error count */
    private int infoErrorCnt = 0;
    // 2016/07/05 QC#10692 Add End

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NFAB300001().executeBatch(NFAB300001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get Batch Date.
        // If an error occurs, throw Exception.
        this.batchDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(this.batchDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Batch Date" });
        }

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(FETCH_SIZE_MAX);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {

        doProcess();
    }

    @Override
    protected void termRoutine() {

        // Set term code and total count.
        // 2016/07/05 QC#10692 Mod Start
        if (infoErrorCnt > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, infoSccessCnt, infoErrorCnt, searchCnt);
        // 2016/07/05 QC#10692 Mod End
    }

    /**
     * doProcess
     */
    private void doProcess() {
        PreparedStatement manJrnlEntryHdrInfo = null;
        ResultSet manJrnlEntryLine = null;
        NFZC104001PMsg pMsg = new NFZC104001PMsg();

        // get Main Journal Entry
        try {
            manJrnlEntryHdrInfo = ssmLLClient.createPreparedStatement("getManJrnlEntryHdrList", setParamForGetTargetManJrnl(), excParam);
            manJrnlEntryLine = manJrnlEntryHdrInfo.executeQuery();

            while (manJrnlEntryLine.next()) {
                searchCnt++;
                BigDecimal manJrnlEntryHdrPk = manJrnlEntryLine.getBigDecimal(MAN_JRNL_ENTRY_HDR_PK);

                // get original MAN_JRNL_ENTRY_HDR Info
                MAN_JRNL_ENTRY_HDRTMsg orgMsg = new MAN_JRNL_ENTRY_HDRTMsg();
                setValue(orgMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(orgMsg.manJrnlEntryHdrPk, manJrnlEntryHdrPk);
                orgMsg = (MAN_JRNL_ENTRY_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(orgMsg);

                // create reversal data in MAN_JRNL_ENTRY_HDR
                MAN_JRNL_ENTRY_HDRTMsg rvsHdrMsg = new MAN_JRNL_ENTRY_HDRTMsg();
                insertRvsManJrnlEntryHdr(orgMsg, rvsHdrMsg);
                // create reversal data in MAN_JRNL_ENTRY_DTL
                MAN_JRNL_ENTRY_DTLTMsgArray dtlMsgArray = getManJrnlEntryDtl(orgMsg.manJrnlEntryHdrPk.getValue());
                insertRvsManJrnlEntryDtl(rvsHdrMsg.manJrnlEntryHdrPk.getValue(), dtlMsgArray);

                // set Journal Entry Creation API
                setParamForApi(pMsg, rvsHdrMsg.manJrnlEntryHdrPk.getValue(), this.batchDt);
                // call Journal Entry Creation API
                if (callApi(pMsg)) {
                    // update original MAN_JRNL_ENTRY_HDR info
                    setParamForUpdate(orgMsg);
                    S21FastTBLAccessor.update(orgMsg);
                    infoSccessCnt++;
                    // 2016/07/05 QC#10692 Del Start
                    //commit();
                    // 2016/07/05 QC#10692 Del End
                } else {
                    S21InfoLogOutput.printlnv(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    rollback();
                    // 2016/07/05 QC#10692 Mod Start
                    infoSccessCnt = 0;
                    infoErrorCnt = 1;
                    return;
                    //throw new S21AbendException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    // 2016/07/05 QC#10692 Mod End
                }
                // 2016/07/05 QC#10692 Add Start
                commit();
                // 2016/07/05 QC#10692 Add End
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(manJrnlEntryHdrInfo, manJrnlEntryLine);
        }
    }

    private void setParamForUpdate(MAN_JRNL_ENTRY_HDRTMsg orgMsg) {
        if (ZYPDateUtil.compare(orgMsg.rsvdRvslDt.getValue(), this.batchDt) > 0) {
            setValue(orgMsg.actlRvslDt, orgMsg.rsvdRvslDt);
        } else {
            setValue(orgMsg.actlRvslDt, this.batchDt);
        }
        setValue(orgMsg.rvslCpltFlg, ZYPConstant.FLG_ON_Y);
    }

    private void insertRvsManJrnlEntryDtl(BigDecimal manJrnlEntryHdrPk, MAN_JRNL_ENTRY_DTLTMsgArray dtlMsgArray) {
        // START 2016/12/12 H.Ikeda [QC#15823,MOD]
        int cnt = 0;
        if (dtlMsgArray != null) {
            MAN_JRNL_ENTRY_DTLTMsg rvcDtlMsgs[] = new MAN_JRNL_ENTRY_DTLTMsg[dtlMsgArray.getValidCount()];
            for (int i = 0; i < dtlMsgArray.getValidCount(); i++) {
                MAN_JRNL_ENTRY_DTLTMsg dtlMsg = dtlMsgArray.no(i);
                MAN_JRNL_ENTRY_DTLTMsg rvcDtlMsg = new MAN_JRNL_ENTRY_DTLTMsg();
                EZDMsg.copy(dtlMsg, null, rvcDtlMsg, null);
                setValue(rvcDtlMsg.manJrnlEntryDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MAN_JRNL_ENTRY_DTL_SQ));
                setValue(rvcDtlMsg.manJrnlEntryHdrPk, manJrnlEntryHdrPk);
                setValue(rvcDtlMsg.jrnlDealAmt, dtlMsg.jrnlDealAmt.getValue().negate());
                setValue(rvcDtlMsg.jrnlFuncAmt, dtlMsg.jrnlFuncAmt.getValue().negate());

                rvcDtlMsgs[cnt] = rvcDtlMsg;
                cnt++;
            }
            if (cnt > 0) {
                int rCnt = S21FastTBLAccessor.insert(rvcDtlMsgs);
                if (rCnt > 0) {
                    rollback();
                    throw new S21AbendException(NFZM0028E, new String[] {"MAN_JRNL_ENTRY_DTL"});
                }
            }
        }
        // END   2016/12/12 H.Ikeda [QC#15823,MOD]
    }

    private void insertRvsManJrnlEntryHdr(MAN_JRNL_ENTRY_HDRTMsg orgMsg, MAN_JRNL_ENTRY_HDRTMsg rvsHdrMsg) {
        setValue(rvsHdrMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(rvsHdrMsg.manJrnlEntryHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MAN_JRNL_ENTRY_HDR_SQ));
        setValue(rvsHdrMsg.glPerNm, createGLPeriod(orgMsg.rsvdRvslDt.getValue()));
        setValue(rvsHdrMsg.glDt, this.batchDt);
        setValue(rvsHdrMsg.jrnlCatgCd, orgMsg.jrnlCatgCd);
        // START 2016/07/14 J.Kim [QC#10325,ADD]
        // setValue(rvsHdrMsg.manJrnlNm, "Reversal Of " + orgMsg.manJrnlNm.getValue());
        int manJrnlNmLen = rvsHdrMsg.getAttr("manJrnlNm").getDigit();
        String manJrnlNm = "Reversal Of " + orgMsg.manJrnlNm.getValue();
        if (manJrnlNm.length() > manJrnlNmLen) {
            manJrnlNm = manJrnlNm.substring(0, manJrnlNmLen);
        }
        setValue(rvsHdrMsg.manJrnlNm, manJrnlNm);
        // END 2016/07/14 J.Kim [QC#10325,ADD]
        setValue(rvsHdrMsg.manJrnlDescTxt, orgMsg.manJrnlDescTxt);
        setValue(rvsHdrMsg.rvslCpltFlg, ZYPConstant.FLG_OFF_N);
        setValue(rvsHdrMsg.autoRvslFlg, ZYPConstant.FLG_OFF_N);
        setValue(rvsHdrMsg.manJrnlCpltFlg, ZYPConstant.FLG_ON_Y);
        setValue(rvsHdrMsg.glSendCpltFlg, ZYPConstant.FLG_OFF_N);
        // START 2016/12/12 H.Ikeda [QC#15823,MOD]
        S21FastTBLAccessor.insert(rvsHdrMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rvsHdrMsg.getReturnCode())) {
            rollback();
            throw new S21AbendException(NFZM0028E, new String[] {"MAN_JRNL_ENTRY_HDR"});
        }
        // END   2016/12/12 H.Ikeda [QC#15823,MOD]
    }

    private boolean callApi(NFZC104001PMsg pMsg) {
        new NFZC104001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            return false;
        }
        pMsg.clear();
        return true;
    }

    private void setParamForApi(NFZC104001PMsg pMsg, BigDecimal manJrnlEntryHdrPk, String procDt) {
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.manJrnlEntryHdrPk, manJrnlEntryHdrPk);
        setValue(pMsg.procDt, procDt);
    }

    private Map<String, Object> setParamForGetTargetManJrnl() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("startDt", this.batchDt.substring(0, LEN_YYYYMM).concat("01"));
        paramMap.put("rsvdRvslDt", ZYPDateUtil.addDays(this.batchDt, 1));
        return paramMap;
    }

    private String createGLPeriod(String dateStr) {

        if (!ZYPCommonFunc.hasValue(dateStr)) {
            return null;
        }

        String strYY = dateStr.substring(BIGIN_IDX_YY, END_IDX_YY);
        String strMM = dateStr.substring(BIGIN_IDX_MM, END_IDX_MM);
        String monthNm = "";
        String glPeriod = "";
        if ("01".equals(strMM)) {
            monthNm = "JAN";
        } else if ("02".equals(strMM)) {
            monthNm = "FEB";
        } else if ("03".equals(strMM)) {
            monthNm = "MAR";
        } else if ("04".equals(strMM)) {
            monthNm = "APR";
        } else if ("05".equals(strMM)) {
            monthNm = "MAY";
        } else if ("06".equals(strMM)) {
            monthNm = "JUN";
        } else if ("07".equals(strMM)) {
            monthNm = "JUL";
        } else if ("08".equals(strMM)) {
            monthNm = "AUG";
        } else if ("09".equals(strMM)) {
            monthNm = "SEP";
        } else if ("10".equals(strMM)) {
            monthNm = "OCT";
        } else if ("11".equals(strMM)) {
            monthNm = "NOV";
        } else if ("12".equals(strMM)) {
            monthNm = "DEC";
        }
        glPeriod = monthNm.concat("-").concat(strYY);
        return glPeriod;
    }

    private MAN_JRNL_ENTRY_DTLTMsgArray getManJrnlEntryDtl(BigDecimal manJrnlEntryHdrPk) {

        MAN_JRNL_ENTRY_DTLTMsg jrnlDtlTMsg = new MAN_JRNL_ENTRY_DTLTMsg();
        jrnlDtlTMsg.setSQLID("001");
        jrnlDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        jrnlDtlTMsg.setConditionValue("manJrnlEntryHdrPk01", manJrnlEntryHdrPk);
        return (MAN_JRNL_ENTRY_DTLTMsgArray) EZDTBLAccessor.findByCondition(jrnlDtlTMsg);
    }
}
