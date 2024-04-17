package oracle.apps.e471.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import oracle.apps.e471.server.CanonE471SRRequestData;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
public class CanonE471SRCreationDao {
	
	public static final String INSERT_DATA = "{call CANON_E471_SR_WEB_PKG.INSERT_DATA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_NEW_REQUESTS = "{call CANON_E471_SR_WEB_PKG.GET_NEW_REQUESTS(?)}";
	public static final String GET_REQUESTS_FOR_NOTIFICATION = "{call CANON_E471_SR_WEB_PKG.GET_REQUESTS_FOR_NOTIFICATION(?)}";
	public static final String INSERT_EMAIL_ARCH= "{call CANON_E471_SR_WEB_PKG.EMAIL_ARCH(?,?,?,?,?,?,?)}";
	public static final String UPDATE_REQUEST= "{call CANON_E471_SR_WEB_PKG.UPDATE_REQUEST(?,?,?,?,?)}";
	public static final String UPDATE_EMAIL_FLAG= "{call CANON_E471_SR_WEB_PKG.UPDATE_EMAIL_FLAG(?,?)}";
	public static final String VALIDATE_CALL= "{call CANON_E471_SR_WEB_PKG.VALIDATE_CALL()}";
	public static final String VALIDATE_IS_BILLABLE= "{call CANON_E471_SR_WEB_PKG.VALIDATE_IS_BILLABLE(?,?,?,?)}";
	public static final String SR_STATE_EMAIL= "{call CANON_E471_SR_WEB_PKG.SR_STATE_EMAIL(?,?,?,?)}";

	
		public  boolean  insertData(String serial, String incident,
				String company, String address1, String address2, String city,
				String state, String postalCode, String spInstruct,
				String requestedBy, String phone, String emailAddress,
				String srIssue, String serviceType, String statusCd, String statusMsg,
				String contactName, String contactPhone, String contactEmail, String source){
			
			       boolean isInserted = true;
			        CallableStatement statement = null;
			        Connection connection = null;
			        try {
			        	 connection = TransactionScope.getConnection();
			            if (connection != null) {
			                statement = (CallableStatement) connection.prepareCall(INSERT_DATA);
			                if (statement != null) {
								statement.setObject(1, company, OracleTypes.VARCHAR);
								statement.setObject(2, requestedBy, OracleTypes.VARCHAR);
								statement.setObject(3, address1,OracleTypes.VARCHAR);
								statement.setObject(4, address2,OracleTypes.VARCHAR);
								
								statement.setObject(5, city, OracleTypes.VARCHAR);
								statement.setObject(6, state, OracleTypes.VARCHAR);
								statement.setObject(7, postalCode,OracleTypes.VARCHAR);
								statement.setObject(8, emailAddress,OracleTypes.VARCHAR);
								
								statement.setObject(9, phone, OracleTypes.VARCHAR);
								statement.setObject(10, serial, OracleTypes.VARCHAR);
								statement.setObject(11, srIssue,OracleTypes.VARCHAR);
								statement.setObject(12, serviceType,OracleTypes.VARCHAR);
								
								statement.setObject(13, spInstruct, OracleTypes.VARCHAR);
								statement.setObject(14, incident, OracleTypes.VARCHAR);
								statement.setObject(15, statusCd,OracleTypes.VARCHAR);
								statement.setObject(16, statusMsg,OracleTypes.VARCHAR);
								
								statement.setObject(17, contactName, OracleTypes.VARCHAR);
								statement.setObject(18, contactPhone, OracleTypes.VARCHAR);
								statement.setObject(19, contactEmail,OracleTypes.VARCHAR);
								statement.setObject(20, source,OracleTypes.VARCHAR);
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



		public ArrayList<CanonE471SRRequestData> getNewRequests() {
	        CallableStatement statement = null;
	        Connection connection = null;
	        ArrayList<CanonE471SRRequestData> requests = new  ArrayList<CanonE471SRRequestData>();
	        try {
	        	 connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_NEW_REQUESTS);
	                if (statement != null) {
	                	statement.registerOutParameter(1, OracleTypes.CURSOR);
						statement.execute();
						ResultSet newRequests = (ResultSet)statement.getObject(1);
						while (newRequests.next()){
							CanonE471SRRequestData requestData = new CanonE471SRRequestData();							
							requestData.setCompany(newRequests.getString("COMPANY"));
							requestData.setRequestedBy(newRequests.getString("REQUESTED_BY"));
							requestData.setAddress(newRequests.getString("ADDRESS"));
							requestData.setAddress2(newRequests.getString("ADDRESS2"));
							requestData.setCity(newRequests.getString("CITY"));
							requestData.setState(newRequests.getString("STATE"));
							requestData.setZipCode(newRequests.getString("ZIP_CODE"));
							requestData.setEmailAddress(newRequests.getString("EMAIL_ADDRESS"));
							requestData.setPhoneNumber(newRequests.getString("PHONE_NUMBER"));
							requestData.setFaxNumber(newRequests.getString("FAX_NUMBER"));
							requestData.setSerialNumber(newRequests.getString("SERIAL_NUMBER"));
							requestData.setMeterRead(newRequests.getString("METER_READ"));
							requestData.setProblem(newRequests.getString("PROBLEM"));
							requestData.setCreationDate(newRequests.getTimestamp("CREATION_DATE"));
							requestData.setProcessedFlag(newRequests.getString("PROCESSED_FLAG"));
							requestData.setErrorMessage(newRequests.getString("ERROR_MESSAGE"));
							requestData.setSource(newRequests.getString("SOURCE"));
							requestData.setSourceEmail(newRequests.getString("SOURCE_EMAIL"));
							requestData.setServiceType(newRequests.getString("SERVICE_TYPE"));
							requestData.setServiceTypeCd(newRequests.getString("DS_SVC_CALL_TP_CD"));
							requestData.setSpecialInstructions(newRequests.getString("SPECIAL_INSTRUCTIONS"));
							requestData.setCaseNumber(newRequests.getString("CASE_NUMBER"));
							requestData.setSvcMachMasterPk(newRequests.getString("SVC_MACH_MSTR_PK"));
							requestData.setContactName(newRequests.getString("CONTACT_NAME"));
							requestData.setContactPhone(newRequests.getString("CONTACT_PHONE"));
							requestData.setContactEmail(newRequests.getString("CONTACT_EMAIL"));
							
							requests.add(requestData);
						}
						return requests;
						//System.out.println("After  GET_NEW_REQUESTS");
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

		
		
		
		
		
		public ArrayList<CanonE471SRRequestData> getRequestsForNotifications() {
	        CallableStatement statement = null;
	        Connection connection = null;
	        ArrayList<CanonE471SRRequestData> request = new  ArrayList<CanonE471SRRequestData>();
	        try {
	        	 connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_REQUESTS_FOR_NOTIFICATION);
	                if (statement != null) {
	                	statement.registerOutParameter(1, OracleTypes.CURSOR);
						statement.execute();
						ResultSet requests = (ResultSet)statement.getObject(1);
						while (requests.next()){
							CanonE471SRRequestData requestData = new CanonE471SRRequestData();							
							requestData.setCompany(requests.getString("COMPANY"));
							requestData.setRequestedBy(requests.getString("REQUESTED_BY"));
							requestData.setAddress(requests.getString("ADDRESS"));
							requestData.setAddress2(requests.getString("ADDRESS2"));
							requestData.setCity(requests.getString("CITY"));
							requestData.setState(requests.getString("STATE"));
							requestData.setZipCode(requests.getString("ZIP_CODE"));
							requestData.setEmailAddress(requests.getString("EMAIL_ADDRESS"));
							requestData.setPhoneNumber(requests.getString("PHONE_NUMBER"));
							requestData.setFaxNumber(requests.getString("FAX_NUMBER"));
							requestData.setSerialNumber(requests.getString("SERIAL_NUMBER"));
							requestData.setMeterRead(requests.getString("METER_READ"));
							requestData.setProblem(requests.getString("PROBLEM"));
							requestData.setCreationDate(requests.getTimestamp("CREATION_DATE"));
							requestData.setProcessedFlag(requests.getString("PROCESSED_FLAG"));
							requestData.setErrorMessage(requests.getString("ERROR_MESSAGE"));
							requestData.setSource(requests.getString("SOURCE"));
							requestData.setSourceEmail(requests.getString("SOURCE_EMAIL"));
							requestData.setServiceType(requests.getString("SERVICE_TYPE"));
							requestData.setSpecialInstructions(requests.getString("SPECIAL_INSTRUCTIONS"));
							requestData.setCaseNumber(requests.getString("CASE_NUMBER"));
							requestData.setIncidentId(requests.getString("INCIDENT_ID"));
							
							request.add(requestData);
						}
						return request;
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



		public void updateRecords(String flag, String errmsg,String incidentId, String serialNumber, Timestamp creationDate) {
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(UPDATE_REQUEST);
	                if (statement != null) {
	                    statement.setObject(1, incidentId, OracleTypes.VARCHAR);
	                    statement.setObject(2, flag, OracleTypes.VARCHAR);
	                    statement.setObject(3, errmsg, OracleTypes.VARCHAR);
	                    statement.setObject(4, serialNumber, OracleTypes.VARCHAR);
	                    statement.setObject(5, creationDate, OracleTypes.TIMESTAMP);
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




		public void updateEmailSentFlag(String serialNumber,Timestamp creationDate) {
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(UPDATE_EMAIL_FLAG);
	                if (statement != null) {
	                    statement.setObject(1, serialNumber, OracleTypes.VARCHAR);
	                    statement.setObject(2, creationDate, OracleTypes.TIMESTAMP);
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




		public Boolean isBillable(String billCd, String serialNumber,Timestamp creationDate) {
	        CallableStatement statement = null;
	        Connection connection = null;
	        Boolean isBillable = false;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(VALIDATE_IS_BILLABLE);
	                if (statement != null) {
	                    statement.setObject(1, serialNumber, OracleTypes.VARCHAR);
	                    statement.setObject(2, billCd, OracleTypes.VARCHAR);
	                    statement.setObject(3, creationDate, OracleTypes.TIMESTAMP);
	                    statement.registerOutParameter(4,OracleTypes.VARCHAR);
	                    statement.execute();
	                    String billable = (String)statement.getObject(4);
	                    if(billable == "Y"){
	                    	isBillable = true;
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
			return isBillable;
	   }




		public ArrayList<String> getEmailFromState(String state, String source) {
	        CallableStatement statement = null;
	        Connection connection = null;
	        ArrayList<String> contactInfo = new ArrayList<String>();
	        String  emailId = null;
	        String  contactPhone = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(SR_STATE_EMAIL);
	                if (statement != null) {
	                    statement.setObject(1, state, OracleTypes.VARCHAR);
	                    statement.setObject(2, source, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(3,OracleTypes.VARCHAR);
	                    statement.registerOutParameter(4,OracleTypes.VARCHAR);
	                    statement.execute();
	                    emailId = (String)statement.getObject(3);
	                    contactPhone = (String)statement.getObject(4);
	                    
	                    contactInfo.add(emailId);
	                    contactInfo.add(contactPhone);
	                    
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
			return contactInfo;
	   }


}
