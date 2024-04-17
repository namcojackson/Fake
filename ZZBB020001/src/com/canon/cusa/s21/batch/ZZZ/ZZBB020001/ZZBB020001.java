package com.canon.cusa.s21.batch.ZZZ.ZZBB020001;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import parts.dbcommon.EZDConnectionMgr;
import business.db.BAT_TBL_CNT_LOGTMsg;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.common.S21SsmEZDOracleJDBCUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZZBB020001 extends S21BatchMain {

    private S21SsmBatchClient ssmBatchClient = null;

    private static final SimpleDateFormat TIME_FORMAT_YYMMDD = new SimpleDateFormat("yyyyMMdd");

    private TERM_CD termCd = TERM_CD.NORMAL_END;

    private static int recordCount = 0;
    
    private static Map<String, BigDecimal> targetTable;
    private static Map<String, BigDecimal> targetTableRowCnt;
    
    private static String sqlSelectCount = "SELECT COUNT(1) FROM %s";
        
    /** Global Company Code */
    private String glblCmpyCd;
    
    private String date;
        
    @Override
    protected void initRoutine() {
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.date = TIME_FORMAT_YYMMDD.format(new Date());
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        new ZZBB020001().executeBatch(ZZBB020001.class.getSimpleName());
    }
    
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, recordCount, 0, recordCount);
    }
    
    @Override
    protected void mainRoutine() {
        this.doProcess(); 
    }
    
    protected void doProcess() {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        ssmBatchClient.queryObject("getTableInfo", mapParam, new SearchTargetTableList());
        
               
        if (targetTable.size() > 0) {
            
            try {
                getTableCount();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new S21AbendException("ZZBM0065E");
            }
            
            BAT_TBL_CNT_LOGTMsg batTblCntLogTMsg = null;
            Iterator iterator = targetTable.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry tableList = (Entry) iterator.next();
                String tblName = tableList.getKey().toString();
                BigDecimal avrRowLength = (BigDecimal) tableList.getValue();
                BigDecimal rowCount = targetTableRowCnt.get(tblName);
                
                batTblCntLogTMsg = new BAT_TBL_CNT_LOGTMsg();
                batTblCntLogTMsg.glblCmpyCd.setValue(glblCmpyCd);
                batTblCntLogTMsg.batTblNm.setValue(tblName);
                batTblCntLogTMsg.batTblEstDt.setValue(date);
                batTblCntLogTMsg.batTblSizeNum.setValue(rowCount.multiply(avrRowLength));
                batTblCntLogTMsg.batTblDataCnt.setValue(rowCount);
                S21FastTBLAccessor.create(batTblCntLogTMsg);
                commit();
                recordCount++;
            }
        }
        return;
    }
    
    protected void getTableCount() throws SQLException {
        
        Connection con = EZDConnectionMgr.getInstance().getConnection();
        PreparedStatement pstmtSelectCount = null;
        ResultSet result = null;
        
        Iterator iterator = targetTableRowCnt.entrySet().iterator();

        try {
            
            while (iterator.hasNext()) {
                
                Entry tableList = (Entry) iterator.next();
                String tblName = tableList.getKey().toString();
                BigDecimal rowCount = BigDecimal.ZERO;
                
                try {
                    String sql = String.format(sqlSelectCount, tblName);                    
                    pstmtSelectCount = con.prepareStatement(sql);
                    result = pstmtSelectCount.executeQuery();  
                    
                    result.next();
                    rowCount = result.getBigDecimal(1);
                    targetTableRowCnt.put(tblName, rowCount);
                } catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        throw new S21AbendException("ZZBM0065E");
                    }
                    throw e;
                } finally {
                    try {
                        if(result != null){
                            result.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new S21AbendException("ZZBM0065E");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new S21AbendException("ZZBM0065E");
        } finally {
            if (pstmtSelectCount != null) {
                pstmtSelectCount.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return;
    }

    private class SearchTargetTableList extends S21SsmVoidResultSetHandlerSupport {

        /**
         * S21SsmEZDOracleJDBCUtil
         */
        private S21SsmEZDOracleJDBCUtil jdbcUtil = S21SsmEZDOracleJDBCUtil.getInstance();

        @Override
        protected void doProcessQueryResult(ResultSet result) throws SQLException {
            
            targetTable = new HashMap<String, BigDecimal>();
            targetTableRowCnt = new HashMap<String, BigDecimal>();
            while (result.next()) {
                targetTable.put(jdbcUtil.getString(result, "TBL_NM"), jdbcUtil.getBigDecimal(result, "AVG_ROW_LEN"));
                targetTableRowCnt.put(jdbcUtil.getString(result, "TBL_NM"), BigDecimal.ZERO);
            }
        }
    }
}
