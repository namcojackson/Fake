package oracle.apps.e165.server;

import java.text.ParseException;
import java.sql.CallableStatement;
import java.sql.Connection;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE165CallCntrTrnstnProg {
	
	public static final String LOAD_DATA = "{call CANON_I165_SR_REP_PKG.LOAD_SEED_AND_LAUNCH(?,?)}";
	
	 public static void main(String args[]) throws Exception {
	      CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            System.out.println("connection:"+connection);
	            if (connection != null) {
	            	
	                statement = (CallableStatement) connection.prepareCall(LOAD_DATA);
	                if (statement != null) {
	                    statement.registerOutParameter(1,OracleTypes.VARCHAR);  
					    statement.registerOutParameter(2,OracleTypes.VARCHAR);
	                    statement.execute();        
						String statusCode = statement.getString(2);
	                    System.out.println("statusCode: " +statusCode);
	                    if (statusCode != null &&
	                    	!statusCode.isEmpty() &&
	                    	("E".equalsIgnoreCase(statusCode) ||
							 "2".equalsIgnoreCase(statusCode))){
	                    	System.err.println("SQL PKG error: statusCode :" +statusCode + " message:" +statement.getString(1));
	                    	throw new Exception(statement.getString(1));
	                    }
	                } else {
	                    System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            //ex.printStackTrace();
				throw ex;
	        } finally {
	            if (statement != null) {
	                try {
	                    statement.close();
	                    statement = null;
	                } catch (Exception exp) {
	                    //exp.printStackTrace();
						throw exp;
	                }
	            }
	            if (connection != null) {
	                try {
	                    TransactionScope.releaseConnection(connection);
	                } catch (Exception ex) {
	                    //ex.printStackTrace();
						throw ex;
	                }
	            }

	        }
	 }

}
