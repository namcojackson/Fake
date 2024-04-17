package oracle.apps.e621.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE621SRUpdateDao {
	
	public static final String CREATE_RITE_AID_SR = "{call CANON_E621_RITE_AID_PKG.CREATE_RITE_AID_SR(?)}";
	public static final String GET_RITE_AID_EMAIL = "{call CANON_E621_RITE_AID_PKG.GET_RITE_AID_EMAIL_ADD(?)}";
	public static final String INSERT_DATA = "{call CANON_E621_RITE_AID_PKG.INSERT_DATA(?,?,?,?,?,?,?,?)}";
	public static final String SEND_STATUS = "{call CANON_E621_RITE_AID_PKG.SEND_STATUS(?)}";
	public static final String UPDATE_STATUS_DATA = "{call CANON_E621_RITE_AID_PKG.UPDATE_STATUS(?,?,?,?,?,?,?,?)}";
	public static final String PREPARE_FROM_EMAIL_REQUEST = "{call CANON_E621_RITE_AID_PKG.PREPARE_FROM_EMAIL_REQUEST(?,?)}";
	
	

	public ArrayList<CanonE621SRRequestData> createRiteAidSR() {
        CallableStatement statement = null;
        Connection connection = null;
        ArrayList<CanonE621SRRequestData> requests = new  ArrayList<CanonE621SRRequestData>();
        try {
        	 connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CREATE_RITE_AID_SR);
                if (statement != null) {
                	statement.registerOutParameter(1, OracleTypes.CURSOR);
					statement.execute();
					ResultSet newRequests = (ResultSet)statement.getObject(1);
					while (newRequests.next()){
						CanonE621SRRequestData requestData = new CanonE621SRRequestData();							
					
						requestData.setSerialNumber(newRequests.getString("SERIAL_NUMBER"));
						requestData.setIncidentNumber(newRequests.getString("INCIDENT_NUMBER"));
						requestData.setIncidentStatus(newRequests.getString("INCIDENT_STATUS"));
						requestData.setIncidentDate(newRequests.getTimestamp("INCIDENT_DATE"));
						requestData.setIncidentDate1(newRequests.getString("INCIDENT_DATE1"));
						requestData.setTaskNumber(newRequests.getString("TASK_NUMBER"));
						requestData.setTaskStatus(newRequests.getString("TASK_STATUS"));
						requestData.setProbDescription(newRequests.getString("PROB_DESCRIPTION"));
						requestData.setContactInfo(newRequests.getString("CONTACT_INFO"));
						requestData.setCreationDate(newRequests.getString("CREATION_DATE"));
						
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
	
	
	public ArrayList<String> getRiteAidEmail() {
        CallableStatement statement = null;
        Connection connection = null;
        ArrayList<String> riteAidEmails = new ArrayList<String>();
      
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_RITE_AID_EMAIL);
                if (statement != null) {
                	statement.registerOutParameter(1, OracleTypes.CURSOR);
					statement.execute();
					ResultSet rs = (ResultSet)statement.getObject(1);
					while (rs.next()){
						riteAidEmails.add(rs.getString("EMAIL"));	
					}              
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
		return riteAidEmails;
   }


	public void insertData(CanonE621SRRequestData requestData, String email_flag) {
		
		 CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(INSERT_DATA);
	                if (statement != null) {
	                    statement.setObject(1, requestData.getSerialNumber(), OracleTypes.VARCHAR);
	                    statement.setObject(2, email_flag, OracleTypes.VARCHAR);
	                    statement.setObject(3, requestData.getIncidentNumber(), OracleTypes.VARCHAR);
	                    statement.setObject(4, requestData.getIncidentStatus(), OracleTypes.VARCHAR);
	                    statement.setObject(5, requestData.getIncidentDate(), OracleTypes.TIMESTAMP);
	                    statement.setObject(6, requestData.getTaskNumber(), OracleTypes.VARCHAR);
	                    statement.setObject(7, requestData.getEmailSentTo(), OracleTypes.VARCHAR);
	                    statement.setObject(8, requestData.getTaskStatus(), OracleTypes.VARCHAR);
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


	public ArrayList<CanonE621SRRequestData> getUpdatedStatus() {
        CallableStatement statement = null;
        Connection connection = null;
        ArrayList<CanonE621SRRequestData> requests = new  ArrayList<CanonE621SRRequestData>();
        try {
        	 connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SEND_STATUS);
                if (statement != null) {
                	statement.registerOutParameter(1, OracleTypes.CURSOR);
					statement.execute();
					ResultSet newRequests = (ResultSet)statement.getObject(1);
					while (newRequests.next()){
						CanonE621SRRequestData requestData = new CanonE621SRRequestData();							
					
						requestData.setSerialNumber(newRequests.getString("SERIAL_NUMBER"));
						requestData.setIncidentNumber(newRequests.getString("INCIDENT_NUMBER"));
						requestData.setIncidentStatus(newRequests.getString("INCIDENT_STATUS"));
						requestData.setIncidentDate(newRequests.getTimestamp("INCIDENT_DATE"));
						requestData.setTaskNumber(newRequests.getString("TASK_NUMBER"));
						requestData.setTaskStatus(newRequests.getString("TASK_STATUS"));
						requestData.setContactInfo(newRequests.getString("CONTACT_INFO"));
						requestData.setHelpDeskNum(newRequests.getString("rite_aid_hdesk_num"));
						
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


	public void updateStatusData(CanonE621SRRequestData requestData,
			String email_flag) {

		 CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(UPDATE_STATUS_DATA);
	                if (statement != null) {
	                	System.out.println("flag is" + email_flag);
	                	System.out.println("task num is" +  requestData.getTaskNumber());
	                	System.out.println("getIncidentNumber is" +  requestData.getIncidentNumber());
	                	System.out.println("getIncidentDate is" +  requestData.getIncidentDate());
	                    statement.setObject(1, requestData.getSerialNumber(), OracleTypes.VARCHAR);
	                    statement.setObject(2, email_flag, OracleTypes.VARCHAR);
	                    statement.setObject(3, requestData.getIncidentNumber(), OracleTypes.VARCHAR);
	                    statement.setObject(4, requestData.getIncidentStatus(), OracleTypes.VARCHAR);
	                    statement.setObject(5, requestData.getIncidentDate(), OracleTypes.TIMESTAMP);
	                    statement.setObject(6, requestData.getTaskNumber(), OracleTypes.VARCHAR);
	                    statement.setObject(7, requestData.getEmailSentTo(), OracleTypes.VARCHAR);
	                    statement.setObject(8, requestData.getTaskStatus(), OracleTypes.VARCHAR);
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

	public String prepareEmailRequest(String subject) {

		 CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(PREPARE_FROM_EMAIL_REQUEST);
	                if (statement != null) {
	                    statement.setObject(1, subject, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
	                    statement.execute();
	                    String status = (String)statement.getObject(2);
	                    return status;
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
