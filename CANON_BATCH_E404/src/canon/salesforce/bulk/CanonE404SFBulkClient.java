package canon.salesforce.bulk;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.xml.ws.soap.SOAPFaultException;

import java.util.Date;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import canon.salesforce.bulk.CanonE404SFBulkHandler;
import canon.salesforce.common.CanonSalesForceCommon;

import com.canon.usa.s21.integration.service.salesforce.asyncapi.dataload.*;
import com.canon.usa.s21.integration.service.salesforce.asyncapi.dataload.Query;
import com.canon.usa.s21.integration.service.salesforce.asyncapi.dataload.QueryResult;
import com.canon.usa.s21.integration.service.salesforce.enterprise.*;
import com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy;
import com.sun.mail.iap.ConnectionException;

public class CanonE404SFBulkClient{
	
	//private BulkConnection connection;
	private SalesforceProxy sfProxy = new SalesforceProxy();
	private static Connection oracleConnection = null;
	static String handlerName = null;
	static String process = null;
	static LoginResponse loginResp = null;
	String apiVersion = "36.0";
	String serviceUrl;
	CanonSalesForceCommon commonObj = new CanonSalesForceCommon();
	//String batchSvcUrl;
	String sessionId ;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	 public static void main(String[] args) throws ConnectionException, IOException, SQLException, Exception{
		 //CanonSalesForceCommon commonObj = new CanonSalesForceCommon();
		 //Scanner in = new Scanner(System.in);
		 handlerName=System.getenv("VAR_USER1");
		//String limit = System.getenv("VAR_INPUT_PARAM2");
		 //String batchSize = System.getenv("VAR_INPUT_PARAM3");
		 process = System.getenv("VAR_USER4");
		 String operation = null;
		 
		// System.out.println("Please enter handler name:");
		// handlerName = in.nextLine();
		 
		 //System.out.println("Process : ") ;
		// process = in.nextLine();
		 
		// in.close();
		 String sObject = null;
		 String externalIdFieldName = null;
		 String extractTableName = null;
		 CallableStatement setupStmt = null;
		 CanonE404SFBulkClient bulkClient = new CanonE404SFBulkClient();
		 
		 try {
			 
			 if ( handlerName != null) {
				 //get the related data - sObject, operation, externalIdField for the handler from Oracle
				 if (oracleConnection == null)
						oracleConnection = TransactionScope.getConnection();
					setupStmt = (CallableStatement)oracleConnection.prepareCall("{call CANON_E404_SF_BULK_PKG.get_handler_setup(?,?)}");
					if (setupStmt != null) {
						setupStmt.setObject(1, handlerName, OracleTypes.VARCHAR);
						setupStmt.registerOutParameter(2, OracleTypes.CURSOR);
						setupStmt.execute();
					}
					ResultSet setupCur = (ResultSet)setupStmt.getObject(2);
					while (setupCur.next()){
						operation = setupCur.getString("operation");
						sObject = setupCur.getString("object_name");
						externalIdFieldName = setupCur.getString("external_id");
						extractTableName = setupCur.getString("extract_table_name");
					}
					
					 System.out.println("handlerName : " +handlerName);
					 System.out.println("process : " +process);
				        System.out.println("operation : " +operation);
				        System.out.println("externalIDField : " +externalIdFieldName);
				        System.out.println("sObjectName : " +sObject);
				        System.out.println("extractTableName : " +extractTableName);
			 
				 if (process != null && process.equalsIgnoreCase("DEFAULT")) {
					 System.out.println("calling run....");
					 bulkClient.run(new CanonE404SFBulkHandler(handlerName, operation, sObject, externalIdFieldName));
				 }
				 
				 if (process != null && process.equalsIgnoreCase("REPROCESS")){
					 System.out.println("Reprocess...");
					 String reprocessJobId = System.getenv("VAR_INPUT_PARAM5");
					 System.out.println("ReprocessJobId: " +reprocessJobId);
					 if (reprocessJobId != null){
						 //bulkClient.run(new HandlerTest(handlerName, operation, sObject, externalIdFieldName));
						 JobBatchInfo jobBatchInfo = bulkClient.getJobBatch(reprocessJobId, handlerName);
						 addReprocessDetails(jobBatchInfo);
						 SyncHandler handler = new CanonE404SFBulkHandler(handlerName, operation, sObject, externalIdFieldName);
						 bulkClient.updateResults(handler,jobBatchInfo.getJobResponse().getJobInfo(), true);
						 handler.onLeave();
					 } else System.out.println("Job ID required to reprocess a job.");
				 }
			 } else {
				 System.out.println("Handler name is required");
			 }
				 
		 } catch(Exception ex) {
			 	ex.printStackTrace();
				System.exit(99);
		 } catch (Throwable e) {
			// TODO Auto-generated catch block
			 System.out.println("Error : " +e.getMessage());
			 System.out.println("Error cause : "+e.getCause());
			e.printStackTrace();
			System.exit(99);
		} finally{
			 try {
				 	if (setupStmt != null)
				 		setupStmt.close();
				 	if ( oracleConnection != null){
				 		TransactionScope.releaseConnection(oracleConnection);
				 	}
				 	if (loginResp != null){
				 		//logout --> which method to call 
				 	}
			 } catch (Exception ep){
				 ep.printStackTrace();
				 System.exit(99);
			 }
		 }
	 }
	 
	 private JobBatchInfo getJobBatch(String jobId, String handlerName) throws Throwable{
		 CreateJobResponse jobResp = new CreateJobResponse();
		 List<CreateBatchWithBinaryAttachmentsResponse> listBatchResp = new ArrayList<CreateBatchWithBinaryAttachmentsResponse>();
		 try {
			 if (loginResp == null){
				 loginResp = getLoginResult();
			 }

			 //set the service url with jobId
			 //TODO UNDO
			 String url = serviceUrl + "/job/" + jobId + "/batch";

			 //fetch the batches for this job
			 Query qry = new Query();
			 qry.setServiceURL(url);
			 qry.setSessionId(sessionId);
			 GetBatchInfoRequest batchReq = new GetBatchInfoRequest();
			 batchReq.setQuery(qry);
			 GetBatchInfoResponse batchRes = new GetBatchInfoResponse();
			 batchRes = sfProxy.getBatch(batchReq);
			 List<BatchInfo> batchInfoList = batchRes.getBatchInfoList().getBatchInfo();
			 for(BatchInfo b : batchInfoList){
				 CreateBatchWithBinaryAttachmentsResponse batch = new CreateBatchWithBinaryAttachmentsResponse();
				 batch.setBatchInfo(b);
				 listBatchResp.add(batch);
			 }

		 } catch(Throwable e){
			 throw e;
		 }
		 return new JobBatchInfo(listBatchResp,jobResp);
	 }
	 
	 private static void addReprocessDetails(JobBatchInfo jobBatchInfo) throws Throwable{
		 try {
			 List<CreateBatchWithBinaryAttachmentsResponse> batchList = jobBatchInfo.getBatchResponse();
			 for(CreateBatchWithBinaryAttachmentsResponse b : batchList){
				 addBatchDetails(b, jobBatchInfo.getJobId(), true);
			 }
		 } catch (Throwable e){
			 throw e;
		 }
		 
	 }
	 
	 
	 public void run(SyncHandler handler) throws Throwable{
		 System.out.println("begin run " + handler);
		 try {
			 	handler.onEnter();
			 	loginResp = getLoginResult();
			 	System.out.println("SessionId : " +loginResp.getResult().getSessionId());
			 	System.out.println("serviceUrl : " +loginResp.getResult().getServerUrl());
			 	System.out.println("login : " +loginResp.getResult().getUserId());
			 	//JobBatchInfo jobBatchInfo = createBatches(connection, handler, handlerName);
			 	JobBatchInfo jobBatchInfo = createBatches(handler);
			 	if (jobBatchInfo.getJobResponse() != null) {
			 		closeJob(jobBatchInfo.getJobId());
			 		awaitCompletion(jobBatchInfo.getJobResponse(), jobBatchInfo.getBatchResponse());
			 		//updateResults(connection, jobBatchInfo.getJobInfo(), jobBatchInfo.getBatchInfos(), handler);
			 		updateResults(handler,jobBatchInfo.getJobResponse().getJobInfo(), false);
			 				
			 	}else {
			 		System.out.println("No records found.");
		        }
			 	//closeJob("testJob1");
			 /*CreateJobResponse jobresp = new CreateJobResponse();
			 JobInfo jInfo = new JobInfo();
			 jInfo.setId("testJob1");
         	jInfo.setObject("User");
         	jInfo.setOperation(OperationEnum.valueOf("UPSERT"));
         	jInfo.setExternalIdFieldName("CBS_Salesrep_Number__c");
         	//jobInfo = new CreateJobResponse();
         	jobresp.setJobInfo(jInfo);
         	
         	List<CreateBatchWithBinaryAttachmentsResponse> listBatchResp = new ArrayList<CreateBatchWithBinaryAttachmentsResponse>();
         	for(int i=0; i<2; i++){
         		CreateBatchWithBinaryAttachmentsResponse bresp = new CreateBatchWithBinaryAttachmentsResponse();
         		
         		BatchInfo binfo = new BatchInfo();
         		binfo.setId("batch" + i);
         		binfo.setJobId("testJob1");
         		bresp.setBatchInfo(binfo);
         		listBatchResp.add(bresp);
         	}
         	
         	JobBatchInfo jobBatchInfo = new JobBatchInfo(listBatchResp, jobresp);
         	updateResults(handler,jobBatchInfo.getJobResponse().getJobInfo(), false);*/
         	
         	
         	//awaitCompletion(jobresp, listBatchResp);
			 
			 	
			 		
		 }catch (Exception ex){
			 ex.printStackTrace();
		 } catch (Throwable e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}finally {
			 //invalidateSessions
			 //logout
			handler.onLeave();
			System.out.println("finish run " + handler);
		 }
		 
	 }

	 public LoginResponse getLoginResult() throws Throwable{
		 LoginResponse lResp = null;
		 Login lReq = new Login();
		 String[] loginCredentials = null;
		 
		 try {
			// lReq.setUsername("cbsinterface@solutions.canon.com.sfdcdevs21");
			 //lReq.setPassword("welcome2lUoUbSkEp3wsfiPFxHtGFy1Yx");
			 loginCredentials = commonObj.getLoginCredentials(handlerName);
				 lReq.setUsername(loginCredentials[0]);
				 lReq.setPassword(loginCredentials[1]);
				 System.out.println("Salesforce userName: " + lReq.getUsername());
			lResp = sfProxy.login(lReq);
			sessionId = lResp.getResult().getSessionId();
			serviceUrl =  lResp.getResult().getServerUrl();
			//apiVersion = serviceUrl.substring(serviceUrl.indexOf("/u/") + 3,3);
			System.out.println("apiVersion : " +apiVersion);
			//sessionId = "00DM0000000FSBD!AQ4AQDRNdULxZ9Ce7TeXAwOaW_we2Cjkfg5n9g0TcX3KX5rjeWGBzmm2fl0Wjlht7SMuAWYdlm9ZU7kceWaqCFhN3YKG98Pb";
			//serviceUrl = "https://cbs--SFDCDEVS21.cs7.my.salesforce.com/services/Soap/u/34.0/00DM0000000FSBD";
			serviceUrl = serviceUrl.substring(0, serviceUrl.indexOf("Soap/"))
	                + "async/" + apiVersion ;
		} catch (Throwable e) {
			try {
				if ((e.getMessage()).contains("ConnectionException")){
					Thread.sleep(5000); //sleep for 5sec
					//lReq.setUsername("cbsinterface@solutions.canon.com.sfdcdevs21");
					 //lReq.setPassword("welcome2lUoUbSkEp3wsfiPFxHtGFy1Yx");
					 loginCredentials = commonObj.getLoginCredentials(handlerName);
					 lReq.setUsername(loginCredentials[0]);
					 lReq.setPassword(loginCredentials[1]);
					 System.out.println("Salesforce userName: " + lReq.getUsername());
					lResp = sfProxy.login(lReq);
					sessionId = lResp.getResult().getSessionId();
					serviceUrl =  lResp.getResult().getServerUrl();
					//apiVersion = serviceUrl.substring(serviceUrl.indexOf("/u/") + 3,3);
					System.out.println("apiVersion : " +apiVersion);
					serviceUrl = serviceUrl.substring(0, serviceUrl.indexOf("Soap/"))
			                + "async/" + apiVersion ;
				}else throw e;
			}catch(Exception exp){
				exp.printStackTrace();
				throw exp;
			}
		  // TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
		 
		 return lResp;
		 
	 }
	 /**
	     * Create and upload batches using a CSV file.
	     * The file into the appropriate size batch files.
	     * 
	     * @param connection
	     *            Connection to use for creating batches
	     * @param jobInfo
	     *            Job associated with new batches
	     * @param csvFileName
	     *            The source file for batch data
	     */
	 public JobBatchInfo createBatches(SyncHandler handler) throws Throwable{
		 CreateJobResponse jobInfo = null;
		 LineReader rdr = handler.getLineReader();
		 int maxRowsPerBatch = handler.getBatchSize();
		 List<CreateBatchWithBinaryAttachmentsResponse> batchResponse = new ArrayList<CreateBatchWithBinaryAttachmentsResponse>();
		 System.out.println("In createBatches....");
		// Split the CSV file into multiple batches
		 try {
			
			 byte[] headerBytes = (rdr.readLine() + "\n").getBytes("UTF-8"); 
			 int headerBytesLength = headerBytes.length;
			 ByteArrayOutputStream tmpOut = new ByteArrayOutputStream();
	            int maxBytesPerBatch = 10000000; // 10 million bytes per batch
	            int currentBytes = 0;
	            int currentLines = 0;
	            String nextLine; 
	            if (handler.getOperation().equalsIgnoreCase("query")){
	            	tmpOut.write(headerBytes);
	            	if (jobInfo == null){
	            		//JobInfo jInfo = new JobInfo();
		            	//jInfo.setId("testJob1");
		            	//jInfo.setObject("User");
		            	//jInfo.setOperation(OperationEnum.valueOf("UPSERT"));
		            	//jInfo.setExternalIdFieldName("CBS_Salesrep_Number__c");
		            	//jobInfo = new CreateJobResponse();
		            	//jobInfo.setJobInfo(jInfo); 
	            		jobInfo = createJob(handler);
	            	}
	            	           	
	            	
	            	createBatch(tmpOut, batchResponse, jobInfo);
	            }
	                       
	            if (handler.getOperation().equalsIgnoreCase("upsert") ||
	            	handler.getOperation().equalsIgnoreCase("insert") ||
	            	handler.getOperation().equalsIgnoreCase("update") ||
	            	handler.getOperation().equalsIgnoreCase("delete") ||
	            	//handler.getOperation().equalsIgnoreCase("merge") ||
	            	handler.getOperation().equalsIgnoreCase("hard_delete")){
	            	System.out.println("Inupsert");
		            while ((nextLine = rdr.readLine()) != null) {
		                byte[] bytes = (nextLine + "\n").getBytes("UTF-8");
		                // Create a new batch when our batch size limit is reached
		                if (currentBytes + bytes.length > maxBytesPerBatch
		                        || currentLines > maxRowsPerBatch) {
		                    if (jobInfo == null) {
		                        jobInfo = createJob(handler);
		                    }
		                    createBatch(tmpOut, batchResponse, jobInfo);
		                    currentBytes = 0;
		                    currentLines = 0;
		                }
		                if (currentBytes == 0) {
		                    tmpOut = new ByteArrayOutputStream();
		                    tmpOut.write(headerBytes);
		                    currentBytes = headerBytesLength;
		                    currentLines = 1;
		                }
		                tmpOut.write(bytes);
		                currentBytes += bytes.length;
		                currentLines++;
		            }
		            // Finished processing all rows
		            // Create a final batch for any remaining data
		            if (currentLines > 1) {
		                if (jobInfo == null) {
		                	/*JobInfo jInfo = new JobInfo();
			            	jInfo.setId("testJob1");
			            	jInfo.setObject("User");
			            	jInfo.setOperation(OperationEnum.valueOf("UPSERT"));
			            	jInfo.setExternalIdFieldName("CBS_Salesrep_Number__c");
			            	jobInfo = new CreateJobResponse();
			            	jobInfo.setJobInfo(jInfo); */
		                    jobInfo = createJob(handler);
		                }
		                createBatch(tmpOut, batchResponse, jobInfo);
		            }
	            }
		 } catch (SQLException sqlEx){
			 sqlEx.printStackTrace();
	        } catch (Exception ex){
	        	ex.printStackTrace();
	        } catch (Throwable e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw e;
			}finally {
//	            tmpFile.delete();
	            try {
	            	rdr.close();
	            }catch (Exception exp){
	            	exp.printStackTrace();
	            }
	        }
	        return new JobBatchInfo(batchResponse, jobInfo);
		 }
	 
	 /**
	     * Create a new job using the Bulk API.
	     * 
	     * @param sobjectType
	     *            The object type being loaded, such as "Account"
	     * @param connection
	     *            BulkConnection used to create the new job.
	     * @return The JobInfo for the new job.
	 * @throws Throwable 
	     * @throws AsyncApiException
	     */
	 private CreateJobResponse createJob(SyncHandler handler) throws SQLException, Exception, Throwable{
		 String sObjectType=handler.getSobjectType();
	        String operation=handler.getOperation();
	        JobInfo job = new JobInfo();
	        CreateJobResponse jobResp = new CreateJobResponse();
	        CreateJobRequest jobReq = new CreateJobRequest();
	        String jobSvcUrl = serviceUrl + "/job" ;
	        	        
	        System.out.println("In create Job....");
	        System.out.println("JOB Service URL : " +jobSvcUrl);
	        try {
		       // job.setBulkConnection("WHAT");
		        job.setObject(sObjectType);
		        //OperationEnum opEnum = OperationEnum.fromValue(operation.toLowerCase());
		        job.setOperation(OperationEnum.valueOf(operation.toUpperCase()));
		        job.setContentType(ContentType.fromValue("CSV"));
		       // System.out.println("handler:externalId: " +handler.getExternalIdFieldName());
		        if(operation.equalsIgnoreCase("upsert") )
		        	job.setExternalIdFieldName(handler.getExternalIdFieldName());
		        job.setServiceURL(jobSvcUrl);
		        job.setSessionId(sessionId);
		        jobReq.setJobInfo(job);
		        System.out.println("jobReq : operation" +job.getOperation() + ", ContentType : " +job.getContentType() + ", externalId: " +job.getExternalIdFieldName() + ",session : " +job.getSessionId() + ", serviceUrl : " +job.getServiceURL());
		        jobResp = sfProxy.createJob(jobReq);
		        
		      //add entry in job_tbl
		        if (jobResp != null){
		        	System.out.println("Job ID: " +jobResp.getJobInfo().getId());
		        	addJobDetails(jobResp,sObjectType, operation);
		        }
	        }catch (SQLException sqlEx){
	        	sqlEx.printStackTrace();
	        	throw sqlEx;
	        }catch (Exception ex){
	        	ex.printStackTrace();
	        	throw ex;
	        } catch (Throwable e) {
				throw e;
			}
			return jobResp;
		
	        
	 }
	 
	 private void addJobDetails(CreateJobResponse jobInfo, String sObjectType, String operation) throws SQLException, Throwable{
		 System.out.println("In addJobDetails........");
		 String dbCall = "{call CANON_E404_SF_BULK_PKG.add_job_details(?,?,?,?,?,?)}";
		 CallableStatement stmt = null;
		 
		 try {
	        	if (oracleConnection == null)
	        			oracleConnection = TransactionScope.getConnection();
	            if (oracleConnection != null) {
	            	stmt = (CallableStatement) oracleConnection.prepareCall(dbCall);
	                if (stmt != null) {
	                      	stmt.setObject(1, jobInfo.getJobInfo().getId(), OracleTypes.VARCHAR);
		                	stmt.setObject(2, sObjectType, OracleTypes.VARCHAR);
		                	stmt.setObject(3, operation, OracleTypes.VARCHAR );
		                	stmt.setObject(4, handlerName, OracleTypes.VARCHAR);
		                   	stmt.setObject(5,(jobInfo.getJobInfo().getState()).toString(), OracleTypes.VARCHAR);
		                	//stmt.setObject(6,dateFormat.format(jobInfo.getJobInfo().getCreatedDate()), OracleTypes.VARCHAR);
		                   	Calendar cal = jobInfo.getJobInfo().getCreatedDate().toGregorianCalendar();
		                   	//System.out.println("job created date :" +cal.getTime());
		                	stmt.setObject(6,new Timestamp(cal.getTime().getTime()), OracleTypes.DATE);
		                	stmt.execute();
		             } else {
	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (SQLException sqlEx) {
	        	sqlEx.printStackTrace();
	        	//throw sqlEx;
	        }catch (Exception ex) {
	            ex.printStackTrace();
	        }catch (Throwable e){
	        	throw e;
	        } finally {
	            if (stmt != null) {
	                try {
	                	stmt.close();
	                	stmt = null;
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }
	            }
	        }
		 //return retStatus;
		}
		 
	 
	 /**
	     * Create a batch by uploading the contents of the file.
	     * This closes the output stream.
	     * 
	     * @param tmpOut
	     *            The output stream used to write the CSV data for a single batch.
	     * @param tmpFile
	     *            The file associated with the above stream.
	     * @param batchInfos
	     *            The batch info for the newly created batch is added to this list.
	     * @param connection
	     *            The BulkConnection used to create the new batch.
	     * @param jobInfo
	     *            The JobInfo associated with the new batch.
	     */
	 private void createBatch(ByteArrayOutputStream tmpOut,
	            List<CreateBatchWithBinaryAttachmentsResponse> batchInfos, CreateJobResponse jobInfo)
	            throws Throwable {
	        tmpOut.flush();
	        tmpOut.close();
	        System.out.println("In createBatch...");
	        try {
	        	//create the service URL for the batch with jobId
	        	String batchSvcUrl = serviceUrl + "/job/" + jobInfo.getJobInfo().getId() + "/batch" ;
	        	System.out.println("batch Svc Url : " +batchSvcUrl);	        	
	        	//Data
	        	String tmpInputStream = new String(tmpOut.toString());
	        	System.out.println("data before sending to WMB: " +tmpInputStream);
	        	
		        CreateBatchWithBinaryAttachmentsRequest batchReq = new CreateBatchWithBinaryAttachmentsRequest();
		        batchReq.setCSVData(tmpInputStream.toString());
		        batchReq.setServiceURL(batchSvcUrl);
		        batchReq.setSessionId(loginResp.getResult().getSessionId());
		        //batchReq.setSessionId("00DM0000000FSBD!AQ4AQCWVjyJlKpz9jjXIUWUIcaRgjzzLDD1IomX8nF2uLuBhrljFYX6sQiKFd6vPzOFqtc1lprRWEtsvU39RA1dZHLPJF2e");
		        CreateBatchWithBinaryAttachmentsResponse batchInfo = sfProxy.createBatchWithBinaryAttachments(batchReq);
	            addBatchDetails(batchInfo, jobInfo.getJobInfo().getId(), false);
	            batchInfos.add(batchInfo);
	        } catch (Exception ex){
	        	ex.printStackTrace();
	        } catch (Throwable e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw e;
			}
	    }
	 
	 private static void addBatchDetails(CreateBatchWithBinaryAttachmentsResponse batchInfo, String jobId, boolean isReprocess) throws Throwable{
		System.out.println("In addBatchDetails...........");
		 String dbCall = null;
		 
		 if (isReprocess)
			 dbCall = "{call CANON_E404_SF_BULK_PKG.add_reprocess_details(?,?,?,?,?)}";
		 else dbCall = "{call CANON_E404_SF_BULK_PKG.add_batch_details(?,?,?,?,?,?)}";
		 
		 CallableStatement stmt = null;
		 
		 try {
	        	if (oracleConnection == null)
	        			oracleConnection = TransactionScope.getConnection();
	            if (oracleConnection != null) {
	            	stmt = (CallableStatement) oracleConnection.prepareCall(dbCall);
	                if (stmt != null) {
	                	stmt.setObject(1, batchInfo.getBatchInfo().getId(), OracleTypes.VARCHAR);
	                	stmt.setObject(2, jobId, OracleTypes.VARCHAR );
	                	stmt.setObject(3,"Y", OracleTypes.VARCHAR);
	                	stmt.setObject(4,batchInfo.getBatchInfo().getState().toString(), OracleTypes.VARCHAR);
	                	//String process = (isReprocess ? "REPROCESS" : "DEFAULT");
	                	if (isReprocess){
                            stmt.setObject(5, new Timestamp(new Date().getTime()), OracleTypes.DATE);
                            stmt.setObject(6,"REPROCESS", OracleTypes.VARCHAR);
	                	}
	                	else {
                            Calendar cal = batchInfo.getBatchInfo().getCreatedDate().toGregorianCalendar();
                            stmt.setObject(5,new Timestamp(cal.getTime().getTime()), OracleTypes.DATE);
                            stmt.setObject(6,"DEFAULT", OracleTypes.VARCHAR);
	                	}

	                	stmt.setObject(6, process, OracleTypes.VARCHAR);
	                	stmt.execute();
	                	//retStatus = true;
	                } else {
	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } catch (Throwable e){
	        	throw e;
	        } finally {
	            if (stmt != null) {
	                try {
	                	stmt.close();
	                	stmt = null;
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }
	            }
	        }
		 //return retStatus;
	 }
	 
	 private void closeJob(String jobId) throws Throwable {
		 System.out.println("in closeJob....");
		 try {
			 UpdateJobRequest updJobReq = new UpdateJobRequest();
			 String jobSvcUrl = serviceUrl + "/job/" + jobId;
			 JobInfo jobInfo = new JobInfo();
			 jobInfo.setId(jobId);
			 jobInfo.setState(JobStateEnum.CLOSED);
			 jobInfo.setSessionId(sessionId);
			 jobInfo.setServiceURL(jobSvcUrl);
	 		  updJobReq.setJobInfo(jobInfo);
	 		  sfProxy.updateJob(updJobReq);
	 		  
	 		  //update job details in Oracle
	 		 JobInfo jInfo = new JobInfo();
			 jInfo.setId(jobId);
			 jInfo.setSessionId(sessionId);
			 jInfo.setServiceURL(jobSvcUrl);
	 		  GetJobRequest jobReq = new GetJobRequest();
	 		  jobReq.setJobInfo(jInfo);
	 		  GetJobResponse jobRsp = sfProxy.getJob(jobReq);
	 		  System.out.println("JobResp : " + jobRsp.getJobInfo().getId());
	 		  updateJobDetails(jobId,jobRsp.getJobInfo().getState().toString(),jobRsp.getJobInfo().getNumberBatchesTotal());
		 } catch (Exception ex){
			 ex.printStackTrace();
		 } catch (Throwable e) {
			//e.printStackTrace();
			throw e;
		}
	  }
	 
	 private void updateJobDetails(String jobID, String status, int totalBatches) throws Throwable{
		 //boolean retStatus = false;
		 System.out.println("In updateJobDetails.............");
		 String dbCall = "{call CANON_E404_SF_BULK_PKG.update_job_details(?,?,?)}";
		 CallableStatement stmt = null;
		 try {
	        	if (oracleConnection == null)
	        			oracleConnection = TransactionScope.getConnection();
	            if (oracleConnection != null) {
	            	stmt = (CallableStatement) oracleConnection.prepareCall(dbCall);
	                if (stmt != null) {
	                	stmt.setObject(1, jobID, OracleTypes.VARCHAR );
	                	stmt.setObject(2, status, OracleTypes.VARCHAR);
	                	stmt.setObject(3, totalBatches, OracleTypes.NUMBER);
	                	stmt.execute();
	                	//retStatus = true;
	                } else {
	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } catch (Throwable e) {
	        	throw e;
	        } finally {
	            if (stmt != null) {
	                try {
	                	stmt.close();
	                	stmt = null;
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }
	            }
	        }
		 //return retStatus;
	 }
	 
	 /**
	     * Wait for a job to complete by polling the Bulk API.
	     * 
	     * @param connection
	     *            BulkConnection used to check results.
	     * @param job
	     *            The job awaiting completion.
	     * @param batchInfoList
	     *            List of batches for this job.
	     * @throws AsyncApiException
	     */
	 private void awaitCompletion(CreateJobResponse jobResp, List<CreateBatchWithBinaryAttachmentsResponse> batchRespList) throws Throwable{
		 System.out.println("In awaitCompletion.........");
		 long sleepTime = 0L;
		 Set<String> incompleteBatches = new HashSet<String>();
		 for (CreateBatchWithBinaryAttachmentsResponse b : batchRespList){
			 incompleteBatches.add(b.getBatchInfo().getId());
		 }
		 while (!incompleteBatches.isEmpty()){
			 try {
				 Thread.sleep(sleepTime);
			 
			 System.out.println("Awaiting results...." +incompleteBatches.size());
			 sleepTime = 10000L;
			 Query q = new Query();
			 String batchServiceUrl = serviceUrl + "/job/" + jobResp.getJobInfo().getId() + "/batch";
			 q.setServiceURL(batchServiceUrl);
			 q.setSessionId(sessionId);
			 //q.setSessionId("00DM0000000FSBD!AQ4AQCWVjyJlKpz9jjXIUWUIcaRgjzzLDD1IomX8nF2uLuBhrljFYX6sQiKFd6vPzOFqtc1lprRWEtsvU39RA1dZHLPJF2e");
			 GetBatchInfoRequest gbireq = new GetBatchInfoRequest();
			 gbireq.setQuery(q);
			 GetBatchInfoResponse gbirsp = new GetBatchInfoResponse();
			 gbirsp = sfProxy.getBatch(gbireq); 
			 List<BatchInfo> statusList = gbirsp.getBatchInfoList().getBatchInfo();
			 for(BatchInfo b : statusList) {
				 if (b.getState() == BatchStateEnum.COMPLETED ||
						 b.getState() == BatchStateEnum.FAILED) {
					 if (incompleteBatches.remove(b.getId())){
						 System.out.println("BATCH STATUS: " +b.getState());
					 }
				 }
			 }
			 }catch(InterruptedException e) { 
			 } catch (Throwable e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw e;
			}
		 }
		 
	 }
	  
	 
	 private void updateResults(SyncHandler handler, JobInfo job, boolean isReprocess
	            ) throws IOException, Throwable {
		 System.out.println("In updateResults...................");
		 try {
		 	GetBatchInfoRequest gbInfoReq = new GetBatchInfoRequest();
		 	Query qry = new Query();
		 	String batchServiceUrl = serviceUrl + "/job/" + job.getId() + "/batch";
		 	qry.setServiceURL(batchServiceUrl);
		 	qry.setSessionId(sessionId);
		 	//qry.setSessionId("00DM0000000FSBD!AQ4AQCWVjyJlKpz9jjXIUWUIcaRgjzzLDD1IomX8nF2uLuBhrljFYX6sQiKFd6vPzOFqtc1lprRWEtsvU39RA1dZHLPJF2e");
		 	QueryBatchResult qbReslt = new QueryBatchResult();
		 	ResultList rsList = new ResultList();
		 	//ResultList qryResultList = new ResultList();
		 	ResultList qryResultList = null;
		 	RequestList reqList = new RequestList();
		 	QueryBatchRequest qbRequest = new QueryBatchRequest();
		 	List<QueryResult> qrObj ;
		 	
		 	String batchIDServiceUrl = null;
		 	gbInfoReq.setQuery(qry);
		 	GetBatchInfoResponse gbInfoRsp = sfProxy.getBatch(gbInfoReq);
		 	BatchInfoList batchInfoList = gbInfoRsp.getBatchInfoList();
		 	
		 	for(BatchInfo b : batchInfoList.getBatchInfo()){
		 			updateBatchDetails(b, isReprocess);
		 		batchIDServiceUrl = batchServiceUrl + "/" + b.getId() + "/request";
		 		qry.setServiceURL(batchIDServiceUrl);
		 		qry.setSessionId(sessionId);
		 		//qry.setSessionId("00DM0000000FSBD!AQ4AQCWVjyJlKpz9jjXIUWUIcaRgjzzLDD1IomX8nF2uLuBhrljFYX6sQiKFd6vPzOFqtc1lprRWEtsvU39RA1dZHLPJF2e");
		 		//get the batch request
		 		qbRequest.setQuery(qry);
		 		reqList = sfProxy.queryBatchRequest(qbRequest);
		 		System.out.println("reqList : " +reqList.getCSVData());
		 		
		 		//get the batch result
		 		batchIDServiceUrl = batchServiceUrl + "/" + b.getId() + "/result";
		 		qry.setServiceURL(batchIDServiceUrl);
		 		qbReslt.setQuery(qry);
		 		
		 		rsList = sfProxy.queryBatchResult(qbReslt);	
		 				 		 		
		 		System.out.println("rsList.getCSVData() : " +rsList.getCSVData());
		 		System.out.println("rsList : " +rsList.getResult().size());
		 		
		 		if(handler.getOperation().equalsIgnoreCase("query") ) {
		 			
		 			List<QueryResult> qrObjList = rsList.getResult();
					 List<SObject> sObjList ;
		             CanonE404SFQryRsltRec q = new CanonE404SFQryRsltRec();
		            // System.out.println("resultReader......................." +resultList);
		             if (rsList.getResult().size() >= 0) {
			             for(QueryResult qr : qrObjList){
			            	 sObjList = qr.getRecords();
			            	 for (SObject sObj : sObjList){
			            		 System.out.println("sObj ID: " +sObj.getId());
			            		 System.out.println("sObj Type: " +sObj.getType());
			            		 System.out.println("sObj Any: " +sObj.getAny());
			            		 batchIDServiceUrl = batchServiceUrl + "/" + b.getId() + "/result" + "/" +sObj.getId();
			            		 qry.setServiceURL(batchIDServiceUrl);
			            		 qbReslt.setQuery(qry);
			            		 try {
			            			 qryResultList = sfProxy.queryBatchResult(qbReslt);
			            		 } catch(Exception sfe) {
			            			 if ((sfe.getMessage()).contains("Records not found for this query")){
			            				 qryResultList = null;
			            				 System.out.println("No query results");
			            			 }
			            		 }
			            	 }
			             }
		             } else
		            	 	System.out.println("No query results");
		 		}
		 		//System.out.println("qryResultList : " +qryResultList.getCSVData().length());
		 		//handler.update(reqList.getCSVData(), rsList.getCSVData(), b.getJobId());
		 		if(qryResultList != null && qryResultList.getCSVData() != null && qryResultList.getCSVData().length() > 0)
		 			handler.update(reqList,  qryResultList, b.getJobId());
		 		if (rsList.getCSVData() != null)
		 			handler.update(reqList,  rsList, b.getJobId());
		 		updateBatchDetailsE404Flag(b.getId(), b.getJobId(), isReprocess);
		 	}
		 }catch (Exception ex){
			 ex.printStackTrace();
		 } catch (Throwable e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
	    }	
	 
	 
	private void updateBatchDetails(BatchInfo b, boolean isReprocess) throws Throwable{
		 //boolean retStatus = false;
		System.out.println("In updateBatchDetails...........");
		String dbCall = null;
		if (!isReprocess)
		  dbCall = "{call CANON_E404_SF_BULK_PKG.update_batch_details(?,?,?,?,?,?,?,?,?)}";
		else dbCall = "{call CANON_E404_SF_BULK_PKG.update_reprocess_batch_details(?,?,?,?,?,?,?,?)}";
		 CallableStatement stmt = null;
		 
		 try {
	        	if (oracleConnection == null)
	        			oracleConnection = TransactionScope.getConnection();
	            if (oracleConnection != null) {
	            	stmt = (CallableStatement) oracleConnection.prepareCall(dbCall);
	                if (stmt != null) {
	                	stmt.setObject(1, b.getId(), OracleTypes.VARCHAR);
	                	stmt.setObject(2, b.getJobId(), OracleTypes.VARCHAR );
	                	stmt.setObject(3, (b.getNumberRecordsFailed() + b.getNumberRecordsProcessed()), OracleTypes.NUMBER);
	                	stmt.setObject(4, "Y", OracleTypes.VARCHAR);
	                	stmt.setObject(5, b.getNumberRecordsFailed(), OracleTypes.NUMBER);
	                	stmt.setObject(6, b.getNumberRecordsProcessed(), OracleTypes.NUMBER);
	                	stmt.setObject(7, b.getState().toString(), OracleTypes.VARCHAR);
	                	stmt.setObject(8, b.getStateMessage(), OracleTypes.VARCHAR);
	                	if (isReprocess)
	                		stmt.setObject(9,"REPROCESS", OracleTypes.VARCHAR);
	                	else stmt.setObject(9,"DEFAULT", OracleTypes.VARCHAR);
	                	stmt.execute();
	                	//retStatus = true;
	                } else {
	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }catch (Throwable e){
	        	throw e;
	        } finally {
	            if (stmt != null) {
	                try {
	                	stmt.close();
	                	stmt = null;
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }
	            }
	        }
		 //return retStatus;
		 
	 }
	 
	 private void updateBatchDetailsE404Flag(String batchID, String jobID, boolean isReprocess) throws Throwable{
		 //boolean retStatus = false;
		 System.out.println("In updateBatchDetailsE404Flag.............");
		 String dbCall = "{call CANON_E404_SF_BULK_PKG.update_batch_e404_flag(?,?,?)}";
		 CallableStatement stmt = null;
		 
		 try {
	        	if (oracleConnection == null)
	        			oracleConnection = TransactionScope.getConnection();
	            if (oracleConnection != null) {
	            	stmt = (CallableStatement) oracleConnection.prepareCall(dbCall);
	                if (stmt != null) {
	                	stmt.setObject(1, batchID, OracleTypes.VARCHAR);
	                	stmt.setObject(2, jobID, OracleTypes.VARCHAR );
	                	if (isReprocess)
	                		stmt.setObject(3,"REPROCESS", OracleTypes.VARCHAR);
	                	else stmt.setObject(3,"DEFAULT", OracleTypes.VARCHAR);
	                	stmt.execute();
	                	//retStatus = true;
	                } else {
	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } catch (Throwable e){
	        	throw e;
	        } finally {
	            if (stmt != null) {
	                try {
	                	stmt.close();
	                	stmt = null;
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }
	            }
	        }
		 //return retStatus;
	 }
	 
	 
	 public static interface SyncHandler {

		 	public void onEnter();
		 	
		 	public void onLeave();
		 	
	        public int getLimit();

	        public int getBatchSize();
	        
	        public String getOperation();

	        public String getSobjectType();

	        public LineReader getLineReader();
	        
	        public String getExternalIdFieldName();

	        //public void update(CSVReader request, CSVReader result, String operation);
	        //public void update(String request, String result, String jobId);
	        public void update(RequestList reqList, ResultList resList, String jobId);
	    }
	 
	public abstract static class AbstractSyncHandler implements SyncHandler {

	        public int getLimit() {
	           // String limit = System.getenv("VAR_INPUT_PARAM2");
	        	String limit = "20000000";
	            return limit == null ? 20 : Integer.parseInt(limit);
	        }

	        public int getBatchSize() {
	            //String batchSize = System.getProperty("VAR_INPUT_PARAM3");
	        	//String batchSize = "10000";
				String batchSize = "5000";
	            return batchSize == null ? 10 : Integer.parseInt(batchSize);
	        }
	               
	        public void onEnter() {
	        }
	        
	        public void onLeave() {
	        }
	        
	        public static String csvLine(String[] fields) {
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < fields.length; i++) {
	                sb.append(i == 0 ? "\"" + escapeCSV(fields[i]) + "\"" : ",\"" + escapeCSV(fields[i]) + "\"");
	            }
	            return sb.toString();
	        }
	        
	        public static String trim(String str) {
	            return str == null ? null : str.trim();
	        }

	        public static String trimToNull(String str) {
	            String ts = trim(str);
	            return isEmpty(ts) ? null : ts;
	        }

	        public static boolean isEmpty(String str) {
	            return str == null || str.length() == 0;
	        }

	        public static String escapeCSV(String s) {
	            return s == null ? "" : s.replace("\"", "\"\"");
	        }

	        public static String nullToNA(String str) {
	            return str == null ? "#N/A" : str;
	        }
	     
	        public static String csvBoolean(String s) {
	            return "Y".equals(s) ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
	        }
	        
	        static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        public static String csvDate(Date d) {
	            //return d==null ? null : sdf.format(d);
	            return d==null ? "#N/A" : sdf.format(d);
	        }
	        
	        public static String csvBigDecimal(BigDecimal d) {
	            return d==null ? null : d.toString();
	        }
	    }
	 
	 private class JobBatchInfo {
		 List<CreateBatchWithBinaryAttachmentsResponse> listBatchResp;
		 CreateJobResponse createJobResponse;
		 
		 public JobBatchInfo(List<CreateBatchWithBinaryAttachmentsResponse> bResp, CreateJobResponse jResp){
			 this.listBatchResp = bResp;
			 this.createJobResponse = jResp;
		 }
		 
		 public void setbatchInfo(List<CreateBatchWithBinaryAttachmentsResponse> batchResponse){
			 this.listBatchResp = batchResponse;
		 }
		 
		 public void setjobInfo(CreateJobResponse jobResponse){
			 this.createJobResponse = jobResponse;
		 }
		 
		 public List<CreateBatchWithBinaryAttachmentsResponse> getBatchResponse(){
			 return listBatchResp;
		 }
		 
		 public CreateJobResponse getJobResponse(){
			 return createJobResponse;
		 }
		 
		 public String getJobId(){
			 return (createJobResponse.getJobInfo()).getId();
		 }
		 
		 public int getTotalBatches(){
			 return (createJobResponse.getJobInfo()).getNumberBatchesTotal();
		 }
	 }
		 
	 
	 public static interface LineReader {

	        public String readLine() throws IOException, SQLException;

	        public void close() throws IOException;
	 }

	    public static class BufferedLineReader implements LineReader {

	        private BufferedReader bufferedReader;

	        public BufferedLineReader(BufferedReader bufferedReader) {
	            this.bufferedReader = bufferedReader;
	        }

	        public String readLine() throws IOException {
	            return bufferedReader.readLine();
	        }

	        public void close() throws IOException {
	            bufferedReader.close();
	        }
	    }
}