/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0020;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import parts.dbcommon.EZDSQLAccessor;
import business.db.NTFY_HDRTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * NtfySqlClodAccessor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NtfySqlClodAccessor extends EZDSQLAccessor {

    /** CLOB_COL_NM */
    private static final String CLOB_COL_NM = "NTFY_SQL_CLOD";

    /** AND */
    private static final String AND = "AND";

    /** Notification Header Message */
    final NTFY_HDRTMsg ntfyHdrTMsg;

    /** Notification Header Key Column List */
    final List<String>[] ntfyHdrKeyColumnList;

    /**
     * Constractor.
     * @param ntfyHdrTMsg NTFY_HDRTMsg
     */
    public NtfySqlClodAccessor(NTFY_HDRTMsg ntfyHdrTMsg) {
        this.ntfyHdrTMsg = ntfyHdrTMsg;
        this.ntfyHdrKeyColumnList = ntfyHdrTMsg.getKeyColumnList();
    }

    /**
     * get 'NTFY_SQL_CLOD'.
     * @return NTFY_SQL_CLOD
     */
    public String getNtfySqlTxt() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = getConnection();

            // --------------------------------------------------
            // SELECT
            // --------------------------------------------------
            final String selectSql = createClobSelectSQL();
            ps = con.prepareStatement(selectSql);
            for (int i = 0; i < ntfyHdrKeyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, ntfyHdrTMsg.getValueData(ntfyHdrKeyColumnList[0].get(i), -1));
            }

            rs = ps.executeQuery();

            String sqlTxt = "";
            if (rs.next()) {
                final Clob clob = rs.getClob(CLOB_COL_NM);
                if (clob != null) {
                    int length = (int) clob.length();
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
     * update 'NTFY_SQL_CLOD'.
     * @param ntfySqlTxt String (NTFY_SQL_CLOD)
     * @return true/success, false/failure
     */
    boolean updateNtfySqlTxt(String ntfySqlTxt) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = getConnection();

            // --------------------------------------------------
            // UPDATE (EMPTY_CLOB)
            // --------------------------------------------------
            final String updateSql = createClobEmptyUpdateSQL();
            ps = con.prepareStatement(updateSql);
            for (int i = 0; i < ntfyHdrKeyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, ntfyHdrTMsg.getValueData(ntfyHdrKeyColumnList[0].get(i), -1));
            }

            final int resCnt = ps.executeUpdate();

            if (resCnt == 1) {

                // --------------------------------------------------
                // SELECT
                // --------------------------------------------------
                final String selectSql = createClobSelectSQL();
                ps = con.prepareStatement(selectSql);
                for (int i = 0; i < ntfyHdrKeyColumnList[1].size(); i++) {
                    getJDBCUtil().setParam(ps, i + 1, ntfyHdrTMsg.getValueData(ntfyHdrKeyColumnList[0].get(i), -1));
                }
                rs = ps.executeQuery();
                if (rs.next()) {
                    // write CLOB.
                    final Clob clob = rs.getClob(CLOB_COL_NM);
                    if (clob != null) {
                        clob.setString(1, ntfySqlTxt);
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
        sb.append("UPDATE ").append(ntfyHdrTMsg.getTableName().toUpperCase());
        sb.append(" SET ").append(CLOB_COL_NM).append(" = EMPTY_CLOB()");
        sb.append(" WHERE ");
        for (int i = 0; i < ntfyHdrKeyColumnList[1].size(); i++) {
            sb.append(" ").append(ntfyHdrKeyColumnList[1].get(i)).append(" = ? ").append(AND);
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
        sb.append(" FROM ").append(ntfyHdrTMsg.getTableName().toUpperCase());
        sb.append(" WHERE ");
        for (int i = 0; i < ntfyHdrKeyColumnList[1].size(); i++) {
            sb.append(" ").append(ntfyHdrKeyColumnList[1].get(i)).append(" = ? ").append(AND);
        }

        // SQL
        String sql = sb.toString();
        if (sql.endsWith(AND)) {
            sql = sql.substring(0, sql.length() - AND.length());
        }

        return sql;
    }
}
