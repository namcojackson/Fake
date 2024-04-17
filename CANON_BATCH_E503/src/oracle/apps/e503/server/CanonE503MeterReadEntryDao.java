package oracle.apps.e503.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import oracle.apps.e471.server.CanonE471SRRequestData;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
public class CanonE503MeterReadEntryDao {
	
	public static final String INSERT_DATA = "{call CANON_E503_EMAIL_PARSE_PKG.INSERT_DATA(?,?,?,?,?,?)}";
	public static final String GET_NEW_REQUESTS = "{call CANON_E503_EMAIL_PARSE_PKG.GET_NEW_REQUESTS(?)}";
	public static final String INSERT_EMAIL_ARCH= "{call CANON_E503_EMAIL_PARSE_PKG.EMAIL_ARCH(?,?,?,?,?,?,?)}";
	public static final String UPDATE_REQUEST= "{call CANON_E503_EMAIL_PARSE_PKG.UPDATE_REQUEST(?,?,?,?)}";
	public static final String VALIDATE_CALL= "{call CANON_E503_EMAIL_PARSE_PKG.mail_data_load_proc()}";

	
	public boolean insertData(String serial, String date, String model,
			String totalReading, String bwReading) {
		System.out.println("insertData serial: "+ serial+" date: "+date+" model: "+model+" totalReading: "+totalReading+" bwReading: "+bwReading);
			       boolean isInserted = true;
			        CallableStatement statement = null;
			        Connection connection = null;
			        try {
			        	 connection = TransactionScope.getConnection();
			            if (connection != null) {
			                statement = (CallableStatement) connection.prepareCall(INSERT_DATA);
			                if (statement != null) {
								statement.setObject(1, model, OracleTypes.VARCHAR);
								statement.setObject(2, serial, OracleTypes.VARCHAR);
								statement.setObject(3, date,OracleTypes.VARCHAR);
								statement.setObject(4, totalReading,OracleTypes.VARCHAR);						
								statement.setObject(5, bwReading, OracleTypes.VARCHAR);
								statement.setObject(6, "AUTOEMAIL", OracleTypes.VARCHAR);
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
		
		  public  Object[] insertDmEmailArch(String p_from_email,
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
				                statement = (CallableStatement) connection.prepareCall(INSERT_EMAIL_ARCH);
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


		public ArrayList<CanonE503MREntry> getNewRequests() {
	        CallableStatement statement = null;
	        Connection connection = null;
	        ArrayList<CanonE503MREntry> requests = new  ArrayList<CanonE503MREntry>();
	        try {
	        	 connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_NEW_REQUESTS);
	                if (statement != null) {
	                	statement.registerOutParameter(1, OracleTypes.CURSOR);
						statement.execute();
						ResultSet newRequests = (ResultSet)statement.getObject(1);
						while (newRequests.next()){
							CanonE503MREntry requestData = new CanonE503MREntry();				
							
							requestData.setSvcMachMasterPk(newRequests.getString("SVC_MACH_MSTR_PK"));
							requestData.setMtrReadDt(newRequests.getTimestamp("READING_DATE"));
							requestData.setNoOfMeters(newRequests.getInt("NO_OF_METERS"));

							requestData.setSerialNumber(newRequests.getString("SERIAL_NUMBER"));
							requestData.setTotalRead(newRequests.getString("TOTAL_READS"));
							requestData.setBwRead(newRequests.getString("BW_READS"));
							requestData.setSvcTtlPhysMtrPk(newRequests.getString("TOTAL_PK"));
							requestData.setSvcBwPhysMtrPk(newRequests.getString("BW_PK"));

							
							requests.add(requestData);
						}
						return requests;
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
	

		public void updateRecords(String flag, String errmsg,String serialNumber, Timestamp creationDate) {
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(UPDATE_REQUEST);
	                if (statement != null) {
	                    statement.setObject(1, flag, OracleTypes.VARCHAR);
	                    statement.setObject(2, errmsg, OracleTypes.VARCHAR);
	                    statement.setObject(3, serialNumber, OracleTypes.VARCHAR);
	                    statement.setObject(4, creationDate, OracleTypes.TIMESTAMP);
	                    statement.execute();
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
	   }


	

		public void validateCalls() {
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(VALIDATE_CALL);
	                if (statement != null) {
	                    statement.execute();
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
	   }




	


		






}
