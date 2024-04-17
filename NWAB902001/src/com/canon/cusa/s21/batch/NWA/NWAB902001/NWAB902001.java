package com.canon.cusa.s21.batch.NWA.NWAB902001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;

import business.db.OM_PURGE_INV_TRGT_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * <pre>
 * Creating Purge Invoice Target
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 05/31/2011   SRA America     Y.Takada        Create          2269 
 *</pre>
 */
public class NWAB902001 extends S21BatchMain {

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Term cd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Processing Count */
    private int procCount = 0;

    /** Normal Count */
    private int normalCnt = 0;

    /** Error Count */
    private int errCnt = 0;

    /** Source Retention Number Type Code */
    private enum SRC_RETEN_NUM_TP_CD {
        /** YEAR("Y") */
        YEAR("Y")
        /** MONTH("M") */
        , MONTH("M")
        /** DAY("D") */
        , DAY("D");

        /** Type Code */
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

    /** SQL List */
    private List<String> sqlList = null;

    /** Insert data list OM_PURGE_INV_TRGT_WRK */
    private List<OM_PURGE_INV_TRGT_WRKTMsg> insWrkList = null;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NWAB902001().executeBatch(NWAB902001.class.getSimpleName());
    }

    /**
     * 
     */
    public NWAB902001() {
        super();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.sqlList = new ArrayList<String>();
        this.insWrkList = new ArrayList<OM_PURGE_INV_TRGT_WRKTMsg>();
    }

    @Override
    protected void initRoutine() {

        if (!isGlobalCompanyCode()) {
            S21InfoLogOutput.println("NWCM0008E", new String[] {"Global Company Code" });
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            return;
        }

//        // User Variable - 1 : Transaction Source Type Code
//        this.trxSrcTpCd = getUserVariable2();
//        if (!hasValue(this.trxSrcTpCd)) {
//            S21InfoLogOutput.println("Transaction Source Type Code is null, Target all Transaction Source Type Code.");
//        }
    }

    @Override
    protected void mainRoutine() {

        try {

            // ----------------------------------------
            // Search SQL from Archive Condition Table.
            // ----------------------------------------
            getBaseSQL();

            // ----------------------------------------
            // Purge data acquisition.
            // Purge Data is written in Work Table.
            // ----------------------------------------
            insertPurgeData();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCnt, this.errCnt, this.procCount);
    }

    private boolean isGlobalCompanyCode() {

        this.glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(this.glblCmpyCd)) {
            return false;
        }

        return ZYPCodeDataUtil.hasCodeValue(GLBL_CMPY.class, this.glblCmpyCd, this.glblCmpyCd);
    }

    private void getBaseSQL() throws SQLException {

        Map<String, Serializable> param = new HashMap<String, Serializable>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("pgId", NWAB902001.class.getSimpleName());

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = ssmLLClient.createPreparedStatement("getBaseSQLList", param, ssmParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                createSQL(rs.getString("SRC_TABLE_NM"), rs.getString("SRC_SCHM_NM"), rs.getString("BASE_SQL"), rs.getString("SRC_RETEN_NUM_TP_CD"), rs.getBigDecimal("SRC_RETEN_NUM").intValue());
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void createSQL(String tableName, String schmNm, String baseSql, String srcRetenNumTpCd, int retenNum) {

        String notDelFrom = getNotDeleteStartCommon(this.glblCmpyCd, getSrcRetenNumTpCd(srcRetenNumTpCd), retenNum);

        String execSql = baseSql;
        execSql = execSql.replace("%1", this.glblCmpyCd);
        execSql = execSql.replace("%2", schmNm);
        execSql = execSql.replace("%3", notDelFrom);

        this.sqlList.add(execSql);
    }

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

    private static String getBatchYearMonth(String glblCmpyCd) {
        return ZYPDateUtil.getBatProcDate().substring(YEAR_POS_START, MONTH_POS_END);
    }

    private static String getBatchDay(String glblCmpyCd) {
        return ZYPDateUtil.getBatProcDate();
    }

    private void insertPurgeData() throws SQLException {

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (String execSql : sqlList) {

            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {

                stmt = ssmLLClient.createPreparedStatement("searchPurgeData", execSql, ssmParam);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    insertWrkTbl(rs.getString("INV_NUM"), rs.getString("ORIG_INV_NUM"), rs.getString("GRP_INV_NUM"), rs.getString("CPO_ORD_NUM"), rs.getString("ORIG_CPO_ORD_NUM"), rs.getString("CUST_ISS_PO_NUM"));
                }

            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
        }
    }
    
    private void insertWrkTbl(String invNum, String origInvNum, String grpInvNum, String cpoOrdNum, String origCpoOrdNum, String custIssPoNum) throws SQLException {

        PreparedStatement statement = null;

//        try {
//            ssmLLClient.createPreparedStatement("deleteWrkTbl", new HashMap<String, String>());
//            statement.executeUpdate();
//        } finally {
//            normalCnt = procCount;
//            S21SsmLowLevelCodingClient.closeResource(statement, null);
//        }

        final Map<String, String> ssmParam = new HashMap<String, String>();

        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        ssmParam.put("ezInTime", dateTime);
        ssmParam.put("ezInTimeZone", upTimeZone);
        ssmParam.put("ezInCompanyCode", upCmpyCd);
        ssmParam.put("ezInUserID", userId);
        ssmParam.put("ezInAplID", upPgId);

        ssmParam.put("ezUpTime", dateTime);
        ssmParam.put("ezUpTimeZone", upTimeZone);
        ssmParam.put("ezUpCompanyCode", upCmpyCd);
        ssmParam.put("ezUpUserID", userId);
        ssmParam.put("ezUpAplID", upPgId);

        ssmParam.put("glblCmpyCd", defaultStr(this.glblCmpyCd));
        ssmParam.put("invNum", defaultStr(invNum));
        ssmParam.put("origInvNum", defaultStr(origInvNum));
        ssmParam.put("grpInvNum", defaultStr(grpInvNum));
        ssmParam.put("cpoOrdNum", defaultStr(cpoOrdNum));
        ssmParam.put("origCpoOrdNum", defaultStr(origCpoOrdNum));
        ssmParam.put("custIssPoNum", defaultStr(custIssPoNum));
        
        try {
        	statement = ssmLLClient.createPreparedStatement("insertWrkTbl", ssmParam);
        	procCount += statement.executeUpdate();
        } finally {
            normalCnt = procCount;
            S21SsmLowLevelCodingClient.closeResource(statement, null);
        }
    }

    private String defaultStr(String str) {

        if (str == null) {
            return "";
        }
        return str;
    }

}
