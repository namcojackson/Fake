package oracle.apps.jtf.aom.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * This is WAS version that uses managed data source. 
 * the nativeConnection method need to be compiled with /WebSphere/AppServer/plugins/com.ibm.ws.runtime.jar
 */
public abstract class TransactionScope {

	public static Connection nativeConnection(java.sql.Connection conn) {
		try {
			return com.ibm.websphere.rsadapter.WSCallHelper
					.getNativeConnection(conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return conn;
		}
	}	 

	public static Connection getConnection() throws Exception {
		// return useS21Connection?
		// parts.dbcommon.EZDConnectionMgr.getInstance().getConnection() : conn;
		try {
			Context ctx = new InitialContext();
			String name = "java:comp/env/jdbc/S21_EXTN"; // Indirect JNDI
															// lookup,
															// use resource
															// reference in
															// web.xml
			// String name = "jdbc/S21_CSA_EXTN5"; // Direct JNDI lookup
			DataSource dataSource = (DataSource) ctx.lookup(name);
			return dataSource.getConnection();
		} catch (Exception ex) {
			// Logger.getLogger(TransactionScope.class.getName()).log(Level.SEVERE,
			// null, ex);
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}

	public static Connection getNewConnection() throws TxnException, Exception {
		return getConnection();
	}

	public static void releaseConnection(java.sql.Connection conn) {
		System.out.println("in releaseConnection connection=" + conn);
		try {
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// public static void setRollbackOnly(){
	// }
	//
	// public static void begin(Thread t){
	// }
	//
	// public static void end(Thread t){
	// }

}