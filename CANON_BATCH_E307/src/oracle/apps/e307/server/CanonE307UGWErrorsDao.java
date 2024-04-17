package oracle.apps.e307.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
public class CanonE307UGWErrorsDao {
	
	public static final String INSERT_DATA = "{call CANON_E307_PRCMAIL_PKG.INSERT_DATA(?,?,?,?)}";
	public static final String LOAD_UGW_ERRORS = "{call CANON_E307_PRCMAIL_PKG.load_ugwerr_dtls()}";
	   public static final String INSERT_UGW_EMAIL_ARCH= "{call CANON_E307_PRCMAIL_PKG.INSERT_UGW_EMAIL_ARCH(?,?,?,?,?,?,?)}";
	
		public  boolean  insertData(String p_device_id, String p_error_code,
				Timestamp p_occurred_date, String p_error_description) {
			
		         	//System.out.println("in insertData " + p_device_id + "|" + p_error_code+ "|" + p_occurred_date + "|" + p_error_description);
			        //clearException();
			       boolean isInserted = true;
			        CallableStatement statement = null;
			        Connection connection = null;
			        try {
			        	 connection = TransactionScope.getConnection();
			            if (connection != null) {
			                statement = (CallableStatement) connection.prepareCall(INSERT_DATA);
			                if (statement != null) {
								statement.setObject(1, p_device_id, OracleTypes.VARCHAR);
								statement.setObject(2, p_error_code, OracleTypes.VARCHAR);
								statement.setObject(3, p_occurred_date,OracleTypes.TIMESTAMP);
								statement.setObject(4, p_error_description,OracleTypes.VARCHAR);
								statement.execute();
								System.out.println("After insertData ");
								return isInserted ;
			                } else {
			                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
			                }
			            } else {
			                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
			            }
			        } catch (Exception ex) {
			           // saveException(ex);
			        	isInserted = false;
			            ex.printStackTrace();
			        } finally {
			            if (statement != null) {
			                try {
			                    statement.close();
			                } catch (Exception exp) {
			                    exp.printStackTrace();
			                }
			            }
			            if (connection != null) {
			                try {
			                    TransactionScope.releaseConnection(connection);
			                } catch (Exception ex) {
			                    ex.printStackTrace();
			                }
			            }

			        }
			        return isInserted;
			   }
		
		
		
		public  Object[] loadUGWErrors() {
			        CallableStatement statement = null;
			        Connection connection = null;
			        try {
			        	 connection = TransactionScope.getConnection();
			            if (connection != null) {
			                statement = (CallableStatement) connection.prepareCall(LOAD_UGW_ERRORS);
			                if (statement != null) {
								statement.execute();
								System.out.println("After  loadUGWErrors");
								return new Object[] {};
			                } else {
			                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
			                }
			            } else {
			                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        } finally {
			            if (statement != null) {
			                try {
			                    statement.close();
			                } catch (Exception exp) {
			                    exp.printStackTrace();
			                }
			            }
			            if (connection != null) {
			                try {
			                    TransactionScope.releaseConnection(connection);
			                } catch (Exception ex) {
			                    ex.printStackTrace();
			                }
			            }

			        }
			        return null;
			   }
		
		
		
		  public static Object[] insertDmEmailArch(String p_from_email,
				    String p_subject,
				    String p_body,
				    String p_message_id,
				    Timestamp p_received_date){
				        //System.out.println("in insertDmEmailArch "+p_from_email+"|"+p_subject+"|"+partial(p_body)+"|"+p_message_id+"|"+p_received_date);
				        CallableStatement statement = null;
				        Connection connection = null;
				        try {
				            connection = TransactionScope.getConnection();
				            if (connection != null) {
				                statement = (CallableStatement) connection.prepareCall(INSERT_UGW_EMAIL_ARCH);
				                if (statement != null) {
				                    statement.setObject(1, p_from_email, OracleTypes.VARCHAR);
				                    statement.setObject(2, p_subject, OracleTypes.VARCHAR);
				                    statement.setObject(3, p_body, OracleTypes.VARCHAR);
				                    statement.setObject(4, p_message_id, OracleTypes.VARCHAR);
				                    statement.setObject(5, p_received_date, OracleTypes.TIMESTAMP);
				                    statement.registerOutParameter(6, OracleTypes.NUMBER);
				                    statement.registerOutParameter(7, OracleTypes.VARCHAR);

				                    statement.execute();
				                    return new Object[]{
				                        statement.getObject(6)                   
				                        ,statement.getObject(7)};
				                } else {
				                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
				                }
				            } else {
				                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
				            }
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        } finally {
				            if (statement != null) {
				                try {
				                    statement.close();
				                    statement = null;
				                } catch (Exception exp) {
				                    exp.printStackTrace();
				                }
				            }
				            if (connection != null) {
				                try {
				                    TransactionScope.releaseConnection(connection);
				                } catch (Exception ex) {
				                    ex.printStackTrace();
				                }
				            }

				        }
				        return null;
				   }

}
