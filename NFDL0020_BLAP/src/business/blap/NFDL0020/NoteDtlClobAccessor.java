/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0020;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.canon.cusa.s21.framework.log.S21AbendException;

import business.db.CLT_NOTE_DTLTMsg;

import parts.dbcommon.EZDSQLAccessor;

/**
 *<pre>
 * NtfyEmlBodyClobAccessor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/12/12   Hitachi         S.Fujita        Create          QC#60406
 *</pre>
 */

 class NoteDtlClobAccessor extends EZDSQLAccessor {

     /** CLOB_COL_NM */
     private static final String CLOB_COL_NM = "DTL_NOTE_CLOD";

     /** AND */
     private static final String AND = "AND";

     /** ntfySendEmlInfoTMsg */
     final CLT_NOTE_DTLTMsg cltNoteDtlTMsg;

     /** keyColumnList */
     final List<String>[] keyColumnList;

     /**
      * Contractor
      * @param cltNoteDtlTMsg
      */
     NoteDtlClobAccessor(CLT_NOTE_DTLTMsg cltNoteDtlTMsg) {
         this.cltNoteDtlTMsg = cltNoteDtlTMsg;
         this.keyColumnList = cltNoteDtlTMsg.getKeyColumnList();
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
                 getJDBCUtil().setParam(ps, i + 1, cltNoteDtlTMsg.getValueData(keyColumnList[0].get(i), -1));
             }

             final int resCnt = ps.executeUpdate();

             if (resCnt == 1) {

                 // --------------------------------------------------
                 // SELECT
                 // --------------------------------------------------
                 final String selectSql = createClobSelectSQL();
                 ps = con.prepareStatement(selectSql);
                 for (int i = 0; i < keyColumnList[1].size(); i++) {
                     getJDBCUtil().setParam(ps, i + 1, cltNoteDtlTMsg.getValueData(keyColumnList[0].get(i), -1));
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
         sb.append("UPDATE ").append(cltNoteDtlTMsg.getTableName().toUpperCase());
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
         sb.append(" FROM ").append(cltNoteDtlTMsg.getTableName().toUpperCase());
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
