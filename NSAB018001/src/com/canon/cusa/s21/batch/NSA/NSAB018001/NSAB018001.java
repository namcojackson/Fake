/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB018001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import business.db.FLD_ADCV_BY_SERTMsg;
import business.db.SVC_ADCV_BY_SERTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001MtrCntTwoPntEst;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
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
 * ADCV by Serial.
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2016/06/30   Hitachi         Y.Tsuchimoto    Update          QC#10692
 * 2017/09/07   Hitachi         T.Kanasaka      Update          QC#15134
 * 2017/09/26   Hitachi         T.Kanasaka      Update          QC#21420
 * 2020/01/28   Hitachi         A.Kohinata      Update          QC#55495
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2020/03/26   Hitachi         A.Kohinata      Update          QC#56363
 * 2020/07/15   Hitachi         K.Yamada        Update          QC#57375
 * 2023/10/26   CITS            T.Aizawa        Update          QC#61996
 * 2023/11/15   CITS            T.Aizawa        Update          QC#61996
 *</pre>
 */
public class NSAB018001 extends S21BatchMain {

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * Parameter "@" is not set.
     */
    public static final String NSAM0131E = "NSAM0131E";

    /**
     * System Error : @
     */
    private static final String NSAM0219E = "NSAM0219E";

    /**
     * Serial#[@] does not have any meter read information.
     */
    private static final String NSAM0350E = "NSAM0350E";

    /**
     * The meter label : @ of the serial# : @, the ADCV went negative
     * number.
     */
    private static final String NSAM0351E = "NSAM0351E";

    /**
     * Serial#[@] could not be inserted into the ADCV table.
     */
    private static final String NSAM0352E = "NSAM0352E";

    // add start 2020/03/26 QC#56363
    /** [@] is not found. */
    private static final String ZZZM9006E = "ZZZM9006E";

    // add end 2020/03/26 QC#56363

    // START 2017/09/07 T.Kanasaka [QC#15134,DEL]
    // /**
    // * Scale of AVG_MTR_READ_CNT
    // */
    // private static final int AVG_MTR_READ_CNT_SCL = 8;
    // END 2017/09/07 T.Kanasaka [QC#15134,DEL]

    // START 2023/11/15 t.aizawa [QC#61996,ADD]
    /** Dummy Service Task# for Latest Meter Read */
    private static final String DUMMY_TASK_LATEST = "DUMMYTASKLATEST";

    /** Dummy Service Task# for Previous Meter Read */
    private static final String DUMMY_TASK_PREV = "DUMMYTASKPREV";

    // END 2023/11/15 t.aizawa [QC#61996,ADD]

    /**
     * Low level coding client
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
     * Serial ADCV Selection Group Number
     */
    private BigDecimal serAdcvSelGrpNum = null;

    // add start 2020/03/26 QC#56363
    /** Interval date */
    private String intvlDate = null;

    // add end 2020/03/26 QC#56363

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NSAB018001().executeBatch(NSAB018001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        ssmLlcClnt = S21SsmLowLevelCodingClient.getClient(NSAB018001.class);
        ssmBatClnt = S21SsmBatchClient.getClient(NSAB018001.class);
        termCd = TERM_CD.NORMAL_END;
        glblCmpyCd = getGlobalCompanyCode();
        // START 2016/06/30 [QC#10692, ADD]
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSAM0131E, new String[] {" Global Company Code" });
        }
        // END 2016/06/30 [QC#10692, ADD]
        String usrVal1 = getUserVariable1();
        if (ZYPCommonFunc.hasValue(usrVal1)) {
            serAdcvSelGrpNum = new BigDecimal(usrVal1);
        } else {
            throw new S21AbendException(NSAM0131E, new String[] {"VAR_USER1" });
        }
        errMsgList = new ArrayList<String>();
        errTMsgList = new ArrayList<EZDTMsg>();
        sysTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        // add start 2020/03/26 QC#56363
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, NSAB018001.class.getSimpleName());
        BigDecimal intvlDays = ZYPCodeDataUtil.getNumConstValue("NSAB0180_INTVL_DAYS", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(intvlDays)) {
            throw new S21AbendException(ZZZM9006E, new String[] {"NUM_CONST" });
        }
        intvlDate = ZYPDateUtil.addDays(slsDt, intvlDays.negate().intValue());
        // add end 2020/03/26 QC#56363
    }

    @Override
    protected void mainRoutine() {

        delTbl();
        if (hasMsg()) {
            // START 2016/06/30 [QC#10692, MOD]
            termCd = TERM_CD.WARNING_END;
            // END 2016/06/30 [QC#10692, MOD]
            return;
        }

        commit();

        calcAdcvBySerNum();
        // START 2016/06/30 [QC#10692, ADD]
        if (hasMsg()) {
            termCd = TERM_CD.WARNING_END;
        }
        // END 2016/06/30 [QC#10692, ADD]
        // START 2020/03/03 [QC#56123, ADD]
        calcAdcvBySerNumForFld();
        // START 2016/06/30 [QC#10692, ADD]
        if (hasMsg()) {
            termCd = TERM_CD.WARNING_END;
        }
        // END 2020/03/03 [QC#56123, ADD]

        cratErrLog();
    }

    @Override
    protected void termRoutine() {
        sendEmail();
        setTermState(termCd, normRecCnt, errRecCnt);
    }

    private void delTbl() {
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        try {
            // mod start 2020/07/15 QC#57375
            prepStmt = getPrepStmt(false);
            // mod end 2020/07/15 QC#57375
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {
                BigDecimal svcMachMstrPk = rsltSet.getBigDecimal("SVC_MACH_MSTR_PK");
                SVC_ADCV_BY_SERTMsg tMsg = new SVC_ADCV_BY_SERTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd", "svcMachMstrPk" });
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !S21FastTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {
                    addMsg(null, null, null, null, null, NSAM0219E, "Work Table Deletion cannot be processed.");
                }
                // START 2020/03/03 [QC#56123, ADD]
                FLD_ADCV_BY_SERTMsg fldTMsg = new FLD_ADCV_BY_SERTMsg();
                ZYPEZDItemValueSetter.setValue(fldTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(fldTMsg.svcMachMstrPk, svcMachMstrPk);
                S21FastTBLAccessor.removeByPartialValue(fldTMsg, new String[] {"glblCmpyCd", "svcMachMstrPk" });
                rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !S21FastTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {
                    addMsg(null, null, null, null, null, NSAM0219E, "Work Table Deletion cannot be processed.");
                }
                // END 2020/03/03 [QC#56123, ADD]
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prepStmt, rsltSet);
        }
    }

    private void calcAdcvBySerNum() {
        int normRecCnt = 0;
        int errRecCnt = 0;
        List<SVC_ADCV_BY_SERTMsg> tMsgList = new ArrayList<SVC_ADCV_BY_SERTMsg>();
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        try {
            // mod start 2020/07/15 QC#57375
            prepStmt = getPrepStmt(true);
            // mod end 2020/07/15 QC#57375
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {

                BigDecimal svcMachMstrPk = rsltSet.getBigDecimal("SVC_MACH_MSTR_PK");
                String serNum = rsltSet.getString("SER_NUM");
                List<Map<String, Object>> svcPhysMtrList = getSvcPhysMtrInfo(svcMachMstrPk);
                if (svcPhysMtrList == null || svcPhysMtrList.isEmpty()) {
                    normRecCnt++;
                    continue;
                }

                tMsgList.clear();
                boolean skip = false;
                for (Map<String, Object> svcPhysMtrInfo : svcPhysMtrList) {

                    BigDecimal svcPhysMtrPk = (BigDecimal) svcPhysMtrInfo.get("SVC_PHYS_MTR_PK");
                    String mdlMtrLbCd = (String) svcPhysMtrInfo.get("MDL_MTR_LB_CD");
                    String mdlNm = (String) svcPhysMtrInfo.get("MDL_NM");
                    // START 2020/03/03 [QC#56123, MOD]
                    // String effFromDt = (String)
                    // svcPhysMtrInfo.get("EFF_FROM_DT");
                    // mod start 2020/03/26 QC#56363
                    // String effFromDt = (String)
                    // svcPhysMtrInfo.get("ISTL_DT");
                    // // START 2020/03/03 [QC#56123, MOD]
                    // String effThruDt = (String)
                    // svcPhysMtrInfo.get("EFF_THRU_DT");
                    //
                    // List<Map<String, Object>> mtrReadList =
                    // getSvcPhysMtrReadInfo(svcPhysMtrPk, effFromDt,
                    // effThruDt);
                    // if (mtrReadList == null || mtrReadList.size()
                    // != 2) {
                    // // mod start 2020/01/28 QC#55495
                    // //addMsg(svcMachMstrPk, serNum, svcPhysMtrPk,
                    // mdlMtrLbCd, NSAM0350E, serNum);
                    // //skip = true;
                    // //break;
                    // continue;
                    // // mod end 2020/01/28 QC#55495
                    // }
                    String istlDt = (String) svcPhysMtrInfo.get("ISTL_DT");
                    if (!ZYPCommonFunc.hasValue(istlDt)) {
                        continue;
                    }
                    List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
                    dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
                    dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
                    String effFromDt = null;
                    String effThruDt = null;
                    String rowNum = null;

                    if (existIstlDtInIntvlDate(istlDt)) {
                        effFromDt = istlDt;
                        rowNum = "1";
                    } else if (existMtrReadInIntvlDate(svcPhysMtrPk, dsMtrReadTpGrpCdList)) {
                        effFromDt = intvlDate;
                        rowNum = "1";
                    } else {
                        effFromDt = istlDt;
                    }
                    List<Map<String, Object>> mtrReadList = getSvcPhysMtrReadInfo(svcPhysMtrPk, effFromDt, effThruDt, rowNum, dsMtrReadTpGrpCdList);
                    if (mtrReadList == null || mtrReadList.size() != 2) {
                        continue;
                    }
                    // mod end 2020/03/26 QC#56363

                    // START 2017/09/04 T.Kanasaka [QC#15134,MOD]
                    String firstMtrReadDt = (String) mtrReadList.get(0).get("MTR_READ_DT");
                    BigDecimal firstReadMtrCnt = (BigDecimal) mtrReadList.get(0).get("READ_MTR_CNT");
                    BigDecimal firstSvcPhysMtrReadPk = (BigDecimal) mtrReadList.get(0).get("SVC_PHYS_MTR_READ_PK");
                    String lastMtrReadDt = (String) mtrReadList.get(1).get("MTR_READ_DT");
                    BigDecimal lastReadMtrCnt = (BigDecimal) mtrReadList.get(1).get("READ_MTR_CNT");
                    BigDecimal lastSvcPhysMtrReadPk = (BigDecimal) mtrReadList.get(1).get("SVC_PHYS_MTR_READ_PK");

                    if (!ZYPCommonFunc.hasValue(firstMtrReadDt) || !ZYPCommonFunc.hasValue(lastMtrReadDt)) {
                        addMsg(svcMachMstrPk, serNum, svcPhysMtrPk, mdlMtrLbCd, NSAM0351E, mdlMtrLbCd, serNum);
                        skip = true;
                        break;
                    }

                    // START 2017/09/26 T.Kanasaka [QC#21420,DEL]
                    // BigDecimal cnt =
                    // lastReadMtrCnt.subtract(firstReadMtrCnt);
                    // if (BigDecimal.ZERO.compareTo(cnt) > 0) {
                    // addMsg(svcMachMstrPk, serNum, svcPhysMtrPk,
                    // mdlMtrLbCd, NSAM0351E, mdlMtrLbCd, serNum);
                    // skip = true;
                    // break;
                    // }
                    // END 2017/09/26 T.Kanasaka [QC#21420,DEL]

                    int days = ZYPDateUtil.getDiffDays(lastMtrReadDt, firstMtrReadDt);
                    if (days == 0 || days < 0) {
                        // START 2020/03/03 [QC#56123, MOD]
                        // addMsg(svcMachMstrPk, serNum, svcPhysMtrPk,
                        // mdlMtrLbCd, NSAM0351E, mdlMtrLbCd, serNum);
                        // skip = true;
                        // break;
                        continue;
                        // END 2020/03/03 [QC#56123, MOD]
                    }

                    // BigDecimal avgMtrReadCnt =
                    // cnt.divide(BigDecimal.valueOf(days),
                    // AVG_MTR_READ_CNT_SCL, RoundingMode.HALF_UP);

                    SvcPhysMtrReadInfoBean firstMtrReadInfo = new SvcPhysMtrReadInfoBean();
                    firstMtrReadInfo.setSvcPhysMtrReadPk(firstSvcPhysMtrReadPk);
                    firstMtrReadInfo.setSvcPhysMtrPk(svcPhysMtrPk);
                    firstMtrReadInfo.setMtrReadDt(firstMtrReadDt);
                    firstMtrReadInfo.setReadMtrCnt(firstReadMtrCnt);

                    SvcPhysMtrReadInfoBean lastMtrReadInfo = new SvcPhysMtrReadInfoBean();
                    lastMtrReadInfo.setSvcPhysMtrReadPk(lastSvcPhysMtrReadPk);
                    lastMtrReadInfo.setSvcPhysMtrPk(svcPhysMtrPk);
                    lastMtrReadInfo.setMtrReadDt(lastMtrReadDt);
                    lastMtrReadInfo.setReadMtrCnt(lastReadMtrCnt);

                    List<SvcPhysMtrReadInfoBean> mtrReadInfoList = new ArrayList<SvcPhysMtrReadInfoBean>();
                    mtrReadInfoList.add(firstMtrReadInfo);
                    mtrReadInfoList.add(lastMtrReadInfo);

                    NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                    BigDecimal avgMtrReadCnt = nsxc003001MtrEst.calcAvgDlyCopyVol(glblCmpyCd, mtrReadInfoList);
                    // END 2017/09/07 T.Kanasaka [QC#15134,MOD]

                    // START 2017/09/26 T.Kanasaka [QC#21420,ADD]
                    if (BigDecimal.ZERO.compareTo(avgMtrReadCnt) > 0) {
                        // START 2020/03/03 [QC#56123, MOD]
                        // addMsg(svcMachMstrPk, serNum, svcPhysMtrPk,
                        // mdlMtrLbCd, NSAM0351E, mdlMtrLbCd, serNum);
                        // skip = true;
                        // break;
                        continue;
                        // END 2020/03/03 [QC#56123, MOD]
                    }
                    // END 2017/09/26 T.Kanasaka [QC#21420,ADD]

                    SVC_ADCV_BY_SERTMsg tMsg = new SVC_ADCV_BY_SERTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.serNum, serNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, mdlMtrLbCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.avgMtrReadCnt, avgMtrReadCnt);
                    ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, mdlNm);
                    tMsgList.add(tMsg);
                }
                if (skip) {
                    errRecCnt++;
                    rollback();
                } else {
                    // mod start 2020/01/28 QC#55495
                    if (tMsgList.size() > 0) {
                        int ins = S21FastTBLAccessor.insert(tMsgList.toArray(new SVC_ADCV_BY_SERTMsg[tMsgList.size()]));
                        if (ins == tMsgList.size()) {
                            normRecCnt++;
                            commit();
                        } else {
                            errRecCnt++;
                            addMsg(svcMachMstrPk, serNum, null, null, NSAM0352E, serNum);
                            rollback();
                        }
                    } else {
                        normRecCnt++;
                        rollback();
                    }
                    // mod end 2020/01/28 QC#55495
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

    // START 2020/03/03 [QC#56123, ADD]
    private void calcAdcvBySerNumForFld() {
        int normRecCnt = 0;
        int errRecCnt = 0;
        List<FLD_ADCV_BY_SERTMsg> tMsgList = new ArrayList<FLD_ADCV_BY_SERTMsg>();
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        try {
            // mod start 2020/07/15 QC#57375
            prepStmt = getPrepStmt(false);
            // mod end 2020/07/15 QC#57375
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {

                BigDecimal svcMachMstrPk = rsltSet.getBigDecimal("SVC_MACH_MSTR_PK");
                String serNum = rsltSet.getString("SER_NUM");
                List<Map<String, Object>> svcPhysMtrList = getSvcPhysMtrInfo(svcMachMstrPk);
                if (svcPhysMtrList == null || svcPhysMtrList.isEmpty()) {
                    normRecCnt++;
                    continue;
                }

                tMsgList.clear();
                boolean skip = false;
                for (Map<String, Object> svcPhysMtrInfo : svcPhysMtrList) {

                    BigDecimal svcPhysMtrPk = (BigDecimal) svcPhysMtrInfo.get("SVC_PHYS_MTR_PK");
                    String mdlMtrLbCd = (String) svcPhysMtrInfo.get("MDL_MTR_LB_CD");
                    String mdlNm = (String) svcPhysMtrInfo.get("MDL_NM");
                    // mod start 2020/03/26 QC#56363
                    // String effFromDt = (String)
                    // svcPhysMtrInfo.get("ISTL_DT");
                    // String effThruDt = (String)
                    // svcPhysMtrInfo.get("EFF_THRU_DT");
                    //
                    // List<Map<String, Object>> mtrReadList =
                    // getSvcPhysMtrReadInfoForFld(svcPhysMtrPk,
                    // effFromDt, effThruDt);
                    // if (mtrReadList == null || mtrReadList.size()
                    // != 2) {
                    // continue;
                    // }
                    String istlDt = (String) svcPhysMtrInfo.get("ISTL_DT");
                    if (!ZYPCommonFunc.hasValue(istlDt)) {
                        continue;
                    }
                    List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
                    dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
                    dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
                    String effFromDt = null;
                    String effThruDt = null;
                    String rowNum = null;
                    // START 2023/11/15 t.aizawa [QC#61996,DEL]
                    // START 2023/10/26 t.aizawa [QC#61996,ADD]
                    // List<Map<String, Object>> mtrReadList = null;
                    // END 2023/10/26 t.aizawa [QC#61996,ADD]
                    // END 2023/11/15 t.aizawa [QC#61996,DEL]

                    if (existIstlDtInIntvlDate(istlDt)) {
                        effFromDt = istlDt;
                        rowNum = "1";
                        // START 2023/11/15 t.aizawa [QC#61996,DEL]
                        // START 2023/10/26 t.aizawa [QC#61996,ADD]
                        // mtrReadList =
                        // getSvcPhysMtrReadInfoForFld(svcPhysMtrPk,
                        // effFromDt, effThruDt, rowNum,
                        // dsMtrReadTpGrpCdList);
                        // END 2023/10/26 t.aizawa [QC#61996,ADD]
                        // END 2023/11/15 t.aizawa [QC#61996,DEL]
                        // START 2023/10/26 t.aizawa [QC#61996,DEL]
                        // } else if
                        // (existMtrReadInIntvlDate(svcPhysMtrPk,
                        // dsMtrReadTpGrpCdList)) {
                        // END 2023/10/26 t.aizawa [QC#61996,DEL]
                        // START 2023/10/26 t.aizawa [QC#61996,ADD]
                    } else if (existMtrReadInIntvlDateForFld(svcPhysMtrPk, dsMtrReadTpGrpCdList)) {
                        // END 2023/10/26 t.aizawa [QC#61996,ADD]
                        effFromDt = intvlDate;
                        rowNum = "1";
                        // START 2023/11/15 t.aizawa [QC#61996,DEL]
                        // START 2023/10/26 t.aizawa [QC#61996,ADD]
                        // mtrReadList =
                        // getSvcPhysMtrReadInfo(svcPhysMtrPk,
                        // effFromDt, effThruDt, rowNum,
                        // dsMtrReadTpGrpCdList);
                        // END 2023/10/26 t.aizawa [QC#61996,ADD]
                        // END 2023/11/15 t.aizawa [QC#61996,DEL]
                    } else {
                        effFromDt = istlDt;
                        // START 2023/11/15 t.aizawa [QC#61996,DEL]
                        // START 2023/10/26 t.aizawa [QC#61996,ADD]
                        // mtrReadList =
                        // getSvcPhysMtrReadInfoForFld(svcPhysMtrPk,
                        // effFromDt, effThruDt, rowNum,
                        // dsMtrReadTpGrpCdList);
                        // END 2023/10/26 t.aizawa [QC#61996,ADD]
                        // END 2023/11/15 t.aizawa [QC#61996,DEL]
                    }
                    // START 2023/10/26 t.aizawa [QC#61996,DEL]
                    // List<Map<String, Object>> mtrReadList =
                    // getSvcPhysMtrReadInfo(svcPhysMtrPk, effFromDt,
                    // effThruDt, rowNum, dsMtrReadTpGrpCdList);
                    // END 2023/10/26 t.aizawa [QC#61996,DEL]
                    // START 2023/11/15 t.aizawa [QC#61996,ADD]
                    List<Map<String, Object>> mtrReadList = getSvcPhysMtrReadInfoForFld(svcPhysMtrPk, effFromDt, effThruDt, rowNum, dsMtrReadTpGrpCdList);
                    // END 2023/11/15 t.aizawa [QC#61996,ADD]
                    if (mtrReadList == null || mtrReadList.size() != 2) {
                        continue;
                    }
                    // mod end 2020/03/26 QC#56363

                    String firstMtrReadDt = (String) mtrReadList.get(0).get("MTR_READ_DT");
                    BigDecimal firstReadMtrCnt = (BigDecimal) mtrReadList.get(0).get("READ_MTR_CNT");
                    BigDecimal firstSvcPhysMtrReadPk = (BigDecimal) mtrReadList.get(0).get("SVC_PHYS_MTR_READ_PK");
                    String lastMtrReadDt = (String) mtrReadList.get(1).get("MTR_READ_DT");
                    BigDecimal lastReadMtrCnt = (BigDecimal) mtrReadList.get(1).get("READ_MTR_CNT");
                    BigDecimal lastSvcPhysMtrReadPk = (BigDecimal) mtrReadList.get(1).get("SVC_PHYS_MTR_READ_PK");

                    if (!ZYPCommonFunc.hasValue(firstMtrReadDt) || !ZYPCommonFunc.hasValue(lastMtrReadDt)) {
                        addMsg(svcMachMstrPk, serNum, svcPhysMtrPk, mdlMtrLbCd, NSAM0351E, mdlMtrLbCd, serNum);
                        skip = true;
                        break;
                    }
                    int days = ZYPDateUtil.getDiffDays(lastMtrReadDt, firstMtrReadDt);
                    if (days == 0 || days < 0) {
                        continue;
                    }

                    SvcPhysMtrReadInfoBean firstMtrReadInfo = new SvcPhysMtrReadInfoBean();
                    firstMtrReadInfo.setSvcPhysMtrReadPk(firstSvcPhysMtrReadPk);
                    firstMtrReadInfo.setSvcPhysMtrPk(svcPhysMtrPk);
                    firstMtrReadInfo.setMtrReadDt(firstMtrReadDt);
                    firstMtrReadInfo.setReadMtrCnt(firstReadMtrCnt);

                    SvcPhysMtrReadInfoBean lastMtrReadInfo = new SvcPhysMtrReadInfoBean();
                    lastMtrReadInfo.setSvcPhysMtrReadPk(lastSvcPhysMtrReadPk);
                    lastMtrReadInfo.setSvcPhysMtrPk(svcPhysMtrPk);
                    lastMtrReadInfo.setMtrReadDt(lastMtrReadDt);
                    lastMtrReadInfo.setReadMtrCnt(lastReadMtrCnt);

                    List<SvcPhysMtrReadInfoBean> mtrReadInfoList = new ArrayList<SvcPhysMtrReadInfoBean>();
                    mtrReadInfoList.add(firstMtrReadInfo);
                    mtrReadInfoList.add(lastMtrReadInfo);

                    NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                    BigDecimal avgMtrReadCnt = nsxc003001MtrEst.calcAvgDlyCopyVol(glblCmpyCd, mtrReadInfoList);

                    if (BigDecimal.ZERO.compareTo(avgMtrReadCnt) > 0) {
                        continue;
                    }

                    FLD_ADCV_BY_SERTMsg tMsg = new FLD_ADCV_BY_SERTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.serNum, serNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, mdlMtrLbCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.avgMtrReadCnt, avgMtrReadCnt);
                    ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, mdlNm);
                    tMsgList.add(tMsg);
                }
                if (skip) {
                    errRecCnt++;
                    rollback();
                } else {
                    if (tMsgList.size() > 0) {
                        int ins = S21FastTBLAccessor.insert(tMsgList.toArray(new FLD_ADCV_BY_SERTMsg[tMsgList.size()]));
                        if (ins == tMsgList.size()) {
                            normRecCnt++;
                            commit();
                        } else {
                            errRecCnt++;
                            addMsg(svcMachMstrPk, serNum, null, null, NSAM0352E, serNum);
                            rollback();
                        }
                    } else {
                        normRecCnt++;
                        rollback();
                    }
                }
            }
            this.normRecCnt = this.normRecCnt + normRecCnt;
            this.errRecCnt = this.errRecCnt + errRecCnt;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prepStmt, rsltSet);
        }
    }

    // del start 2020/03/26 QC#56363
    // // @SuppressWarnings("unchecked")
    // private List<Map<String, Object>>
    // getSvcPhysMtrReadInfoForFld(BigDecimal svcPhysMtrPk, String
    // effFromDt, String effThruDt) {
    // Map<String, Object> prm = new HashMap<String, Object>();
    // prm.put("glblCmpyCd", glblCmpyCd);
    // prm.put("svcPhysMtrPk", svcPhysMtrPk);
    // prm.put("effFromDt", effFromDt);
    // prm.put("effThruDt", effThruDt);
    // prm.put("dsMtrReadTpGrpCdList", new String[]
    // {DS_MTR_READ_TP_GRP.SERVICE_READS,
    // DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ });
    // return ssmBatClnt.queryObjectList("getSvcPhysMtrReadInfo",
    // prm);
    // }
    // del end 2020/03/26 QC#56363
    // END 2020/03/03 [QC#56123, ADD]

    // mod start 2020/07/15 QC#57375
    private PreparedStatement getPrepStmt(boolean contrExistsFlg) throws SQLException {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setFetchSize(1000);
        execPrm.setMaxRows(0);
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("serAdcvSelGrpNum", serAdcvSelGrpNum);
        if (contrExistsFlg) {
            prm.put("contrExists", ZYPConstant.FLG_ON_Y);
        }
        return ssmLlcClnt.createPreparedStatement("getSvcAdcvBySerSel", prm, execPrm);
    }

    // mod end 2020/07/15 QC#57375

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcPhysMtrInfo(BigDecimal svcMachMstrPk) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        return ssmBatClnt.queryObjectList("getSvcPhysMtrInfo", prm);
    }

    @SuppressWarnings("unchecked")
    // mod start 2020/03/26 QC#56363
    // private List<Map<String, Object>>
    // getSvcPhysMtrReadInfo(BigDecimal svcPhysMtrPk, String
    // effFromDt, String effThruDt) {
    private List<Map<String, Object>> getSvcPhysMtrReadInfo(BigDecimal svcPhysMtrPk, String effFromDt, String effThruDt, String rowNum, List<String> dsMtrReadTpGrpCdList) {
        // mod end 2020/03/26 QC#56363
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("effFromDt", effFromDt);
        prm.put("effThruDt", effThruDt);
        // START 2020/03/03 [QC#56123, ADD]
        // mod start 2020/03/26 QC#56363
        // prm.put("dsMtrReadTpGrpCdList", new String[]
        // {DS_MTR_READ_TP_GRP.BILLABLE_READS,
        // DS_MTR_READ_TP_GRP.SUPPLY_READS });
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // mod end 2020/03/26 QC#56363
        // END 2020/03/03 [QC#56123, ADD]
        // add start 2020/03/26 QC#56363
        prm.put("rowNum", rowNum);
        // add end 2020/03/26 QC#56363
        return ssmBatClnt.queryObjectList("getSvcPhysMtrReadInfo", prm);
    }

    /**
     * Get Service Physical Meter Read Info. (for Field)
     * @param svcPhysMtrPk
     * @param effFromDt
     * @param effThruDt
     * @param rowNum
     * @param dsMtrReadTpGrpCdList
     * @return
     */
    private List<Map<String, Object>> getSvcPhysMtrReadInfoForFld(BigDecimal svcPhysMtrPk, String effFromDt, String effThruDt, String rowNum, List<String> dsMtrReadTpGrpCdList) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("effFromDt", effFromDt);
        prm.put("effThruDt", effThruDt);
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        prm.put("rowNum", rowNum);
        return NWXC412001.autoCast(ssmBatClnt.queryObjectList("getSvcPhysMtrReadInfoForFld", prm));
    }

    private void addMsg(BigDecimal svcMachMstrPk, String serNum, BigDecimal svcPhysMtrPk, String mdlMtrLbCd, String errCd, String... prm) {
        // Message
        String errMsg = S21MessageFunc.clspGetMessage(errCd, prm);
        errMsgList.add(errMsg);

        // Log
        SVC_BAT_ERR_LOGTMsg tMsg = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, "NSAB0180");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogTs, sysTs);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_01, "SVC_MACH_MSTR_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_01, numToStr(svcMachMstrPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_02, "SER_NUM");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_02, serNum);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_03, "SVC_PHYS_MTR_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_03, numToStr(svcPhysMtrPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_04, "MDL_MTR_LB_CD");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_04, mdlMtrLbCd);
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
            String rtnCd = mailer.sendMail(glblCmpyCd, NSAB018001.class.getSimpleName());
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

    // add start 2020/03/26 QC#56363
    private boolean existIstlDtInIntvlDate(String istlDt) {
        return (ZYPDateUtil.compare(intvlDate, istlDt) <= 0);
    }

    private boolean existMtrReadInIntvlDate(BigDecimal svcPhysMtrPk, List<String> dsMtrReadTpGrpCdList) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("effFromDt", intvlDate);
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        BigDecimal count = (BigDecimal) ssmBatClnt.queryObject("countSvcPhysMtrRead", prm);
        return BigDecimal.valueOf(2).compareTo(count) <= 0;
    }

    // add end 2020/03/26 QC#56363

    // START 2023/10/26 t.aizawa [QC#61996,ADD]
    /**
     * Check if Meter Read exists within period. (for Field)
     * @param svcPhysMtrPk
     * @param dsMtrReadTpGrpCdList
     * @param isNeedTaskNum
     * @return
     */
    private boolean existMtrReadInIntvlDateForFld(BigDecimal svcPhysMtrPk, List<String> dsMtrReadTpGrpCdList) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("effFromDt", intvlDate);
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        prm.put("dummyTaskLatest", DUMMY_TASK_LATEST);
        prm.put("dummyTaskPrevious", DUMMY_TASK_PREV);
        BigDecimal count = (BigDecimal) ssmBatClnt.queryObject("countSvcPhysMtrReadForFld", prm);
        return BigDecimal.valueOf(2).compareTo(count) <= 0;
    }
    // END 2023/10/26 t.aizawa [QC#61996,ADD]

}
