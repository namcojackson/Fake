package oracle.apps.jtf.aom.transaction;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.apps.jtf.aom.transaction.TransactionScope;

public class TransactionScopeTest {

	public static void main(String[] args) throws Exception {
		PrintStream out = System.out;
		Connection conn = null;
		conn = TransactionScope.getConnection();
		out.println(" use TransactionScope conn =" + conn);
		if (conn != null) {
			DatabaseMetaData md = conn.getMetaData();
			out.println(" Username:" + md.getUserName());
			ResultSet schemas = md.getSchemas();
			out.println("schemas:");
			while (schemas.next()) {
				out.println(schemas.getString(1));
			}
		} else {
			out.println("conn is null. ");
		}

		Statement statement = null;
		try {
			conn.setAutoCommit(true);
			statement = conn.createStatement();
			String updateTableSQL = "UPDATE CANON.CANON_E390_BID_FUNDS_TRK_TBL SET BID_NUMBER = '1146' WHERE BID_ID=852";
			out.println(updateTableSQL);
			boolean isSet = statement.execute(updateTableSQL);
			out.println(isSet ? "??" : statement.getUpdateCount());
		} catch (Exception e) {
			e.printStackTrace(new java.io.PrintWriter(out));
		} finally {
			if (statement != null) {
				statement.close();
			}
		}

		try {
			statement = conn.createStatement();
			ResultSet rs = statement
					.executeQuery("select BID_NUMBER from CANON.CANON_E390_BID_FUNDS_TRK_TBL  WHERE BID_ID=852");
			while (rs.next()) {
				String bid_number = rs.getString("BID_NUMBER");
				out.println("bid_number \t" + bid_number);
			}
			out.println("done.");

		} catch (Exception e) {
			e.printStackTrace(new java.io.PrintWriter(out));
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

}
