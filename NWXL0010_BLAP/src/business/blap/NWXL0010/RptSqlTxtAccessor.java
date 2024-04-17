/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NWXL0010;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import parts.dbcommon.EZDSQLAccessor;
import business.db.RPT_SQLTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * 'RPT_SQL_TXT' Accessor. (CLOB Accessor)
 * 
 * @author K.Tajima
 */
class RptSqlTxtAccessor extends EZDSQLAccessor {

    private static final String CLOB_COL_NM = "RPT_SQL_TXT";
    private static final String AND = "AND";

    final RPT_SQLTMsg rptSqlTMsg;
    final List<String>[] keyColumnList;

    /**
     * Constractor.
     * 
     * @param rptSqlTMsg
     */
    @SuppressWarnings("unchecked")
    RptSqlTxtAccessor(RPT_SQLTMsg rptSqlTMsg) {
        this.rptSqlTMsg    = rptSqlTMsg;
        this.keyColumnList = rptSqlTMsg.getKeyColumnList();
    }

    /**
     * get 'RPT_SQL_TXT'.
     * 
     * @return  RPT_SQL_TXT
     */
    String getRptSqlTxt() {

        Connection        con = null;
        PreparedStatement ps  = null;
        ResultSet         rs  = null;

        try {

            con = getConnection();

            // --------------------------------------------------
            // SELECT
            // --------------------------------------------------
            final String SELECT_SQL = createClobSelectSQL();
            ps = con.prepareStatement(SELECT_SQL);
            for (int i = 0; i < keyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, rptSqlTMsg.getValueData(keyColumnList[0].get(i), -1));
            }
            
            rs = ps.executeQuery();

            String sqlTxt = "";
            if (rs.next()) {
                final Clob clob = rs.getClob(CLOB_COL_NM);
                if (clob != null) {
                    int length = (int)clob.length();
                    sqlTxt = clob.getSubString(1, length);
                }
            }
            
            return sqlTxt;

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            super.closeResource(con, ps, rs);
        }
    }
    
    /**
     * update 'RPT_SQL_TXT'.
     * 
     * @param   rptSqlTxt   RPT_SQL_TXT
     * @return  true/success,   false/failure
     */
    boolean updateRptSqlTxt(String rptSqlTxt) {

        Connection        con = null;
        PreparedStatement ps  = null;
        ResultSet         rs  = null;

        try {

            con = getConnection();

            // --------------------------------------------------
            // UPDATE (EMPTY_CLOB)
            // --------------------------------------------------
            final String UPDATE_SQL = createClobEmptyUpdateSQL();
            ps = con.prepareStatement(UPDATE_SQL);
            for (int i = 0; i < keyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, rptSqlTMsg.getValueData(keyColumnList[0].get(i), -1));
            }
            
            final int resCnt = ps.executeUpdate();
            
            if (resCnt == 1) {
                
                // --------------------------------------------------
                // SELECT
                // --------------------------------------------------
                final String SELECT_SQL = createClobSelectSQL();
                ps = con.prepareStatement(SELECT_SQL);
                for (int i = 0; i < keyColumnList[1].size(); i++) {
                    getJDBCUtil().setParam(ps, i + 1, rptSqlTMsg.getValueData(keyColumnList[0].get(i), -1));
                }
                rs = ps.executeQuery();
                if (rs.next()) {
                    // write CLOB.
                    final Clob clob = rs.getClob(CLOB_COL_NM);
                    if (clob != null) {
                        clob.setString(1, rptSqlTxt);
                    }
                }
            }
            
            return true;

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            super.closeResource(con, ps, rs);
        }
    }
    
    private String createClobEmptyUpdateSQL() {
        
        final StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(rptSqlTMsg.getTableName().toUpperCase());
        sb.append(" SET ").append(CLOB_COL_NM).append(" = EMPTY_CLOB()");
        sb.append(" WHERE ");
        for (int i = 0; i < keyColumnList[1].size(); i++) {
            sb.append(" ").append(keyColumnList[1].get(i)).append(" = ? ").append(AND);
        }
        
        String sql = sb.toString();
        if (sql.endsWith(AND)) {
            sql = sql.substring(0, sql.length() - AND.length());
        }
        
        return sql;
    }

    private String createClobSelectSQL() {
        
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").append(CLOB_COL_NM);
        sb.append(" FROM ").append(rptSqlTMsg.getTableName().toUpperCase());
        sb.append(" WHERE ");
        for (int i = 0; i < keyColumnList[1].size(); i++) {
            sb.append(" ").append(keyColumnList[1].get(i)).append(" = ? ").append(AND);
        }

        // SQL
        String sql = sb.toString();
        if (sql.endsWith(AND)) {
            sql = sql.substring(0, sql.length() - AND.length());
        }
        
        return sql;
    }

}
