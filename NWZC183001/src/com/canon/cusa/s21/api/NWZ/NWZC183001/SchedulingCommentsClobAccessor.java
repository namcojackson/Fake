/*
 * <pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC183001;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import business.db.DS_CPO_DELY_INFOTMsg;
import parts.dbcommon.EZDSQLAccessor;

import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * SchedulingCommentsClobAccessor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/26   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class SchedulingCommentsClobAccessor extends EZDSQLAccessor {
    /** CLOB_COL_NM */
    private static final String CLOB_COL_NM = "DELY_SCHD_CMNT_CLOD";

    /** AND */
    private static final String AND = "AND";

    /** dsCpoDelyInfoTMsg */
    final DS_CPO_DELY_INFOTMsg dsCpoDelyInfoTMsg;

    /** keyColumnList */
    final List<String>[] keyColumnList;

    /**
     * Constractor.
     * @param dsCpoDelyInfoTMsg
     */
    SchedulingCommentsClobAccessor(DS_CPO_DELY_INFOTMsg dsCpoDelyInfoTMsg) {
        this.dsCpoDelyInfoTMsg = dsCpoDelyInfoTMsg;
        this.keyColumnList = (List<String>[]) dsCpoDelyInfoTMsg.getKeyColumnList();
    }

    /**
     * get 'DELY_SCHD_CMNT_CLOD'.
     * @return DELY_SCHD_CMNT_CLOD
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
                getJDBCUtil().setParam(ps, i + 1, dsCpoDelyInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
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
     * update 'DELY_SCHD_CMNT_CLOD'.
     * @param cmmtTxt DELY_SCHD_CMNT_CLOD
     * @return true/success, false/failure
     */
    boolean updateSql(String cmmtTxt) {

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
                getJDBCUtil().setParam(ps, i + 1, dsCpoDelyInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
            }

            final int resCnt = ps.executeUpdate();

            if (resCnt == 1) {

                // --------------------------------------------------
                // SELECT
                // --------------------------------------------------
                final String selectSql = createClobSelectSQL();
                ps = con.prepareStatement(selectSql);
                for (int i = 0; i < keyColumnList[1].size(); i++) {
                    getJDBCUtil().setParam(ps, i + 1, dsCpoDelyInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
                }
                rs = ps.executeQuery();
                if (rs.next()) {
                    // write CLOB.
                    final Clob clob = rs.getClob(CLOB_COL_NM);
                    if (clob != null) {
                        clob.setString(1, cmmtTxt);
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
        sb.append("UPDATE ").append(dsCpoDelyInfoTMsg.getTableName().toUpperCase());
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
        sb.append(" FROM ").append(dsCpoDelyInfoTMsg.getTableName().toUpperCase());
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
