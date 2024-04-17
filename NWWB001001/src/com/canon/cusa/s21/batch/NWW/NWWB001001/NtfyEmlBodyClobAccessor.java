/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWW.NWWB001001;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import parts.dbcommon.EZDSQLAccessor;
import business.db.NTFY_SEND_EML_INFOTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * NtfyEmlBodyClobAccessor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
class NtfyEmlBodyClobAccessor extends EZDSQLAccessor {

    /** CLOB_COL_NM */
    private static final String CLOB_COL_NM = "NTFY_EML_BODY_CLOD";

    /** AND */
    private static final String AND = "AND";

    /** ntfySendEmlInfoTMsg */
    final NTFY_SEND_EML_INFOTMsg ntfySendEmlInfoTMsg;

    /** keyColumnList */
    final List<String>[] keyColumnList;

    /**
     * Constractor.
     * @param ntfySendEmlInfoTMsg
     */
    NtfyEmlBodyClobAccessor(NTFY_SEND_EML_INFOTMsg ntfySendEmlInfoTMsg) {
        this.ntfySendEmlInfoTMsg = ntfySendEmlInfoTMsg;
        this.keyColumnList = ntfySendEmlInfoTMsg.getKeyColumnList();
    }

    /**
     * get 'NTFY_EML_BODY_CLOD'.
     * @return NTFY_EML_BODY_CLOD
     */
    String getSql() {

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
            for (int i = 0; i < keyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, ntfySendEmlInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
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
     * @param mlTxt NTFY_EML_BODY_CLOD
     * @return true/success, false/failure
     */
    boolean updateSql(String mlTxt) {

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
            for (int i = 0; i < keyColumnList[1].size(); i++) {
                getJDBCUtil().setParam(ps, i + 1, ntfySendEmlInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
            }

            final int resCnt = ps.executeUpdate();

            if (resCnt == 1) {

                // --------------------------------------------------
                // SELECT
                // --------------------------------------------------
                final String selectSql = createClobSelectSQL();
                ps = con.prepareStatement(selectSql);
                for (int i = 0; i < keyColumnList[1].size(); i++) {
                    getJDBCUtil().setParam(ps, i + 1, ntfySendEmlInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
                }
                rs = ps.executeQuery();
                if (rs.next()) {
                    // write CLOB.
                    final Clob clob = rs.getClob(CLOB_COL_NM);
                    if (clob != null) {
                        clob.setString(1, mlTxt);
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
        sb.append("UPDATE ").append(ntfySendEmlInfoTMsg.getTableName().toUpperCase());
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
        sb.append(" FROM ").append(ntfySendEmlInfoTMsg.getTableName().toUpperCase());
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
