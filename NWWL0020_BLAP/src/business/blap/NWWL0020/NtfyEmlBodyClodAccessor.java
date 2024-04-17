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
import business.db.NTFY_ACT_DTLTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * NtfyEmlBodyClodAccessor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NtfyEmlBodyClodAccessor extends EZDSQLAccessor {

    /** CLOB_COL_NM */
    private static final String CLOB_COL_NM = "NTFY_EML_BODY_CLOD";

    /** AND */
    private static final String AND = "AND";

    /** Notification Action Detail Message */
    final NTFY_ACT_DTLTMsg ntfyActDtlTMsg;

    /** Notification Action Detail Key Column List */
    final List<String>[] ntfyActDtlKeyColumnList;

    /**
     * Constractor.
     * @param ntfyActDtlTMsg NTFY_ACT_DTLTMsg
     */
    public NtfyEmlBodyClodAccessor(NTFY_ACT_DTLTMsg ntfyActDtlTMsg) {
        this.ntfyActDtlTMsg = ntfyActDtlTMsg;
        this.ntfyActDtlKeyColumnList = ntfyActDtlTMsg.getKeyColumnList();
    }

    /**
     * get 'NTFY_EML_BODY_CLOD'.
     * @return NTFY_EML_BODY_CLOD
     */
    public String getNtfyMailTxt() {

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
            for (int i = 0; i < ntfyActDtlKeyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, ntfyActDtlTMsg.getValueData(ntfyActDtlKeyColumnList[0].get(i), -1));
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
     * update 'NTFY_EML_BODY_CLOD'.
     * @param ntfyMailTxt String (NTFY_EML_BODY_CLOD)
     * @return true/success, false/failure
     */
    boolean updateNtfyMailTxt(String ntfyMailTxt) {

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
            for (int i = 0; i < ntfyActDtlKeyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, ntfyActDtlTMsg.getValueData(ntfyActDtlKeyColumnList[0].get(i), -1));
            }

            final int resCnt = ps.executeUpdate();

            if (resCnt == 1) {

                // --------------------------------------------------
                // SELECT
                // --------------------------------------------------
                final String selectSql = createClobSelectSQL();
                ps = con.prepareStatement(selectSql);
                for (int i = 0; i < ntfyActDtlKeyColumnList[1].size(); i++) {
                    getJDBCUtil().setParam(ps, i + 1, ntfyActDtlTMsg.getValueData(ntfyActDtlKeyColumnList[0].get(i), -1));
                }
                rs = ps.executeQuery();
                if (rs.next()) {
                    // write CLOB.
                    final Clob clob = rs.getClob(CLOB_COL_NM);
                    if (clob != null) {
                        clob.setString(1, ntfyMailTxt);
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
        sb.append("UPDATE ").append(ntfyActDtlTMsg.getTableName().toUpperCase());
        sb.append(" SET ").append(CLOB_COL_NM).append(" = EMPTY_CLOB()");
        sb.append(" WHERE ");
        for (int i = 0; i < ntfyActDtlKeyColumnList[1].size(); i++) {
            sb.append(" ").append(ntfyActDtlKeyColumnList[1].get(i)).append(" = ? ").append(AND);
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
        sb.append(" FROM ").append(ntfyActDtlTMsg.getTableName().toUpperCase());
        sb.append(" WHERE ");
        for (int i = 0; i < ntfyActDtlKeyColumnList[1].size(); i++) {
            sb.append(" ").append(ntfyActDtlKeyColumnList[1].get(i)).append(" = ? ").append(AND);
        }

        // SQL
        String sql = sb.toString();
        if (sql.endsWith(AND)) {
            sql = sql.substring(0, sql.length() - AND.length());
        }

        return sql;
    }
}
