/**
 * <pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/27/2011   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB900001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.db.ARC_RQSTTMsg;
import business.db.ARC_RQST_RECTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21OracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * This is a class for to register ARC_RQST and ARC_RQST_REC.
 */
public class NWAB900001 extends S21BatchMain {
    /** YEAR_POS_START */
    private static final int YEAR_POS_START = 0;

    /** YEAR_POS_END */
    private static final int YEAR_POS_END = 4;

    /** MONTH_POS_START */
    private static final int MONTH_POS_START = 4;

    /** MONTH_POS_END */
    private static final int MONTH_POS_END = 6;

    /** LAST_MONTH */
    private static final int LAST_MONTH = 12;

    /** LAST_TWO_DIGIT_MONTH */
    private static final int LAST_TWO_DIGIT_MONTH = 10;

    /** REC_RETEN_START_YR_MTH_LENGTH */
    private static final int REC_RETEN_START_YR_MTH_LENGTH = 6;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** procCount */
    private int procCount = 0;

    /** normalCnt */
    private int normalCnt = 0;

    /** errCnt */
    private int errCnt = 0;
    
    /** Purge Target Name */
    private String purgeTrgtNm;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialization S21BatchMain */
        new NWAB900001().executeBatch(NWAB900001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = getGlobalCompanyCode();
        
        // User Variable - 1 : Purge Target Name
        this.purgeTrgtNm = getUserVariable1();
        if (!hasValue(this.purgeTrgtNm)) {
            S21InfoLogOutput.println("Purge Target Name is null, the purge never process.");
            return;
        }
    }

    @Override
    protected void mainRoutine() {
        try {
            execute();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errCnt, this.procCount);
    }

    private void execute() throws SQLException {
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        Map<String, Serializable> param = new HashMap<String, Serializable>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("pgId", "NWAB900001");
        param.put("purgeTrgtNm", this.purgeTrgtNm);

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = ssmLLClient.createPreparedStatement("getTargetList", param, ssmParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                this.registArcHeaderAndDetail(rs.getString("SRC_TABLE_NM"), rs.getString("SRC_SCHM_NM"), rs.getString("BASE_SQL"), rs.getString("SRC_RETEN_NUM_TP_CD"), rs.getBigDecimal("SRC_RETEN_NUM").intValue());
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void registArcHeaderAndDetail(String tableName, String schmNm, String baseSql, String srcRetenNumTpCd, int retenNum) {
        // Common
        String insYearMonth = getRecRetenStartYrMth();

        // get retentionInfo
        String notDelFrom = getNotDeleteStartCommon(glblCmpyCd, getSrcRetenNumTpCd(srcRetenNumTpCd), retenNum);

        // Header
        BigDecimal arcRqstPk = getArcRqstPk();
        ARC_RQSTTMsg arcRqst = createArcRqst(this.glblCmpyCd, tableName, schmNm, arcRqstPk, insYearMonth);

        S21FastTBLAccessor.insert(arcRqst);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arcRqst.getReturnCode())) {
            this.addCnt(1, 0);
            S21InfoLogOutput.println("Error[Header] : " + tableName);
            return;
        } else {
            this.addCnt(1, 1);
        }

        // Detail
        // Create SQL
        String execSql = baseSql;
        execSql = execSql.replace("%1", this.glblCmpyCd);
        execSql = execSql.replace("%2", notDelFrom);

        ARC_RQST_RECTMsg arcRqstRec = createArcRqstRec(arcRqst, execSql);

        S21FastTBLAccessor.insert(arcRqstRec);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arcRqstRec.getReturnCode())) {
            this.addCnt(1, 0);
            rollback();
            S21InfoLogOutput.println("Error[Detail] : " + tableName);
            return;
        } else {
            this.addCnt(1, 1);
            commit();
        }

    }

    private void addCnt(int procCnt, int insCnt) {
        this.procCount = this.procCount + procCnt;
        this.normalCnt = this.normalCnt + insCnt;
        this.errCnt = this.errCnt + (procCnt - insCnt);
    }

    // If you want to use below methods at another class, then please
    // create OM common class and copy below methods.
    /**
     * This method will return Year Month string for
     * recRetenStartYrMth column.
     * @return String
     */
    private static String getRecRetenStartYrMth() {
        return ZYPDateUtil.getBatProcDate().substring(YEAR_POS_START, MONTH_POS_END);
    }

    /**
     * @param glblCmpyCd String
     * @param tpCd SRC_RETEN_NUM_TP_CD
     * @param retenNum int
     * @return String
     */
    public static String getNotDeleteStartCommon(String glblCmpyCd, SRC_RETEN_NUM_TP_CD tpCd, int retenNum) {

        if (SRC_RETEN_NUM_TP_CD.YEAR == tpCd) {
            String ym = getBatchYearMonth(glblCmpyCd);

            int yInt = Integer.valueOf(ym.substring(YEAR_POS_START, YEAR_POS_END));

            return String.valueOf(yInt - retenNum);

        } else if (SRC_RETEN_NUM_TP_CD.MONTH == tpCd) {

            String ym = getBatchYearMonth(glblCmpyCd);

            int yInt = Integer.valueOf(ym.substring(YEAR_POS_START, YEAR_POS_END));
            int mInt = Integer.valueOf(ym.substring(MONTH_POS_START, MONTH_POS_END));

            int yOverCnt = 0;

            for (int i = 0; i < retenNum; i++) {
                if (1 == mInt) {
                    mInt = LAST_MONTH;
                    yOverCnt++;
                } else {
                    mInt--;
                }
            }

            yInt = yInt - yOverCnt;

            StringBuffer sb = new StringBuffer();
            sb.append(yInt);
            if (mInt < LAST_TWO_DIGIT_MONTH) {
                sb.append("0");
            }
            sb.append(mInt);

            return sb.toString();
        } else if (SRC_RETEN_NUM_TP_CD.DAY == tpCd) {
            String day = getBatchDay(glblCmpyCd);
            return S21CalendarUtil.addDay(day, (-1) * retenNum);
        } else {
            throw new IllegalArgumentException("Error! SRC_RETEN_NUM_TP_CD is Illegal input");
        }

    }

    private static String getBatchYearMonth(String glblCmpyCd) {
        return ZYPDateUtil.getBatProcDate().substring(YEAR_POS_START, MONTH_POS_END);
    }

    private static String getBatchDay(String glblCmpyCd) {
        return ZYPDateUtil.getBatProcDate();
    }

    /**
     * @param tpCd String
     * @return SRC_RETEN_NUM_TP_CD
     */
    private static SRC_RETEN_NUM_TP_CD getSrcRetenNumTpCd(String tpCd) {
        if (null == tpCd || 1 != tpCd.length()) {
            return null;
        } else {
            if (SRC_RETEN_NUM_TP_CD.YEAR.getValue().equals(tpCd)) {
                return SRC_RETEN_NUM_TP_CD.YEAR;
            } else if (SRC_RETEN_NUM_TP_CD.MONTH.getValue().equals(tpCd)) {
                return SRC_RETEN_NUM_TP_CD.MONTH;
            } else if (SRC_RETEN_NUM_TP_CD.DAY.getValue().equals(tpCd)) {
                return SRC_RETEN_NUM_TP_CD.DAY;
            } else {
                return null;
            }
        }
    }

    /**
     * This method will return ARC_RQST_SQ for ARC_RQST_PK.
     * @return BigDecimal
     */
    private static BigDecimal getArcRqstPk() {
        return S21OracleSeqAccessor.getSeqNumber("ARC_RQST_SQ");
    }

    /**
     * This method will return ARC_RQST_REC_SQ for ARC_RQST_REC_PK.
     * @return BigDecimal
     */
    private static BigDecimal getArcRqstRecPk() {
        return S21OracleSeqAccessor.getSeqNumber("ARC_RQST_REC_SQ");
    }

    /**
     * This method will return ARC_RQSTTMsg.</br> If null parameter
     * include, then will return null.
     * @param glblCmpyCd String
     * @param srcTblNm String
     * @param srcSchmNm String
     * @param arcRqstPk BigDecimal
     * @param recRetenStartYrMth String
     * @return ARC_RQSTTMsg
     */
    private static ARC_RQSTTMsg createArcRqst(String glblCmpyCd, String srcTblNm, String srcSchmNm, BigDecimal arcRqstPk, String recRetenStartYrMth) {
        try {
            if (null == glblCmpyCd) {
                throw new IllegalArgumentException("glblCmpyCd is null.");
            }
            if (null == srcTblNm) {
                throw new IllegalArgumentException("srcTblNm is null.");
            }
            if (null == arcRqstPk) {
                throw new IllegalArgumentException("arcRqstPk is null.");
            }

            if (null == srcSchmNm) {
                throw new IllegalArgumentException("srcSchmNm is null.");
            }

            // check recRetenStartYrMth
            if (null == recRetenStartYrMth) {
                throw new IllegalArgumentException("srcTblNm is null.");
            } else if (REC_RETEN_START_YR_MTH_LENGTH != recRetenStartYrMth.length()) {
                throw new IllegalArgumentException("srcTblNm length = " + recRetenStartYrMth.length());
            }
        } catch (Exception e) {
            throw new S21AbendException(e);
        }

        ARC_RQSTTMsg arcRqst = new ARC_RQSTTMsg();
        arcRqst.glblCmpyCd.setValue(glblCmpyCd);
        arcRqst.arcRqstPk.setValue(arcRqstPk);
        arcRqst.srcSchmNm.setValue(srcSchmNm);
        arcRqst.srcTblNm.setValue(srcTblNm);
        arcRqst.recRetenStartYrMth.setValue(recRetenStartYrMth);

        return arcRqst;
    }

    /**
     * This method will return ARC_RQST_RECTMsg.</br> If null
     * parameter include, then will return null.
     * @param arcRqst ARC_RQSTTMsg
     * @param condSqlTxt String
     * @return ARC_RQST_RECTMsg
     */
    private static ARC_RQST_RECTMsg createArcRqstRec(ARC_RQSTTMsg arcRqst, String condSqlTxt) {
        try {
            // check arcRqst
            if (null == arcRqst) {
                throw new IllegalArgumentException("arcRqst is null.");
            } else if (!ZYPCommonFunc.hasValue(arcRqst.glblCmpyCd) || 0 == arcRqst.glblCmpyCd.getValue().length()) {
                throw new IllegalArgumentException("arcRqst(glblCmpyCd) is invalid data.");
            } else if (!ZYPCommonFunc.hasValue(arcRqst.arcRqstPk) || arcRqst.arcRqstPk.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("arcRqst(arcRqstPk) is invalid data.");
            }

            // check condSqlTxt
            if (null == condSqlTxt || 0 == condSqlTxt.length()) {
                throw new IllegalArgumentException("condSqlTxt is null.");
            }
        } catch (Exception e) {
            throw new S21AbendException(e);
        }

        ARC_RQST_RECTMsg arcRqstRec = new ARC_RQST_RECTMsg();
        arcRqstRec.glblCmpyCd.setValue(arcRqst.glblCmpyCd.getValue());
        arcRqstRec.arcRqstRecPk.setValue(getArcRqstRecPk());
        arcRqstRec.arcRqstPk.setValue(arcRqst.arcRqstPk.getValue());
        arcRqstRec.condSqlTxt.setValue(condSqlTxt);

        return arcRqstRec;
    }

    /**
     * SRC_RETEN_NUM_TP_CD enum.
     */
    private enum SRC_RETEN_NUM_TP_CD {
        /** YEAR("Y") */
        YEAR("Y")
        /** MONTH("M") */
        , MONTH("M")
        /** DAY("D") */
        , DAY("D");

        /** tpCd */
        private String tpCd;

        private SRC_RETEN_NUM_TP_CD(String tpCd) {
            this.tpCd = tpCd;
        }

        /**
         * @return String
         */
        public String getValue() {
            return this.tpCd;
        }
    }
}
