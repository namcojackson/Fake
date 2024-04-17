package canon.salesforce.batch;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.xml.ws.Holder;

import com.canon.usa.s21.integration.service.salesforce.enterprise.LimitInfoHeader;
import com.canon.usa.s21.integration.service.salesforce.enterprise.Login;
import com.canon.usa.s21.integration.service.salesforce.enterprise.LoginResponse;
import com.canon.usa.s21.integration.service.salesforce.enterprise.MruHeader;
import com.canon.usa.s21.integration.service.salesforce.enterprise.PackageVersionHeader;
import com.canon.usa.s21.integration.service.salesforce.enterprise.Query;
import com.canon.usa.s21.integration.service.salesforce.enterprise.QueryOptions;
import com.canon.usa.s21.integration.service.salesforce.enterprise.SessionHeader;
import com.canon.usa.s21.integration.service.salesforce.lfs.batch.type.LFSCompetitiveAssetCounts;
import com.canon.usa.s21.integration.service.salesforce.lfs.batch.type.LFSUpdateMarketGroup;
import com.canon.usa.s21.integration.service.salesforce.pps.batch.type.PPSContactCounts;
import com.canon.usa.s21.integration.service.salesforce.pps.batch.type.PPSUpdateCounty;
import com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy;
import com.sun.mail.iap.ConnectionException;

import canon.salesforce.common.CanonSalesForceCommon;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE633SFBatchClient{
	
	public static final int CBS_SF_MAX_BATCHCOUNT = 2;
	public static final int CBS_BATCH_WAIT_TIME = 30000;
	static String serviceUrl = null;
	static String sessionId = null;
	
	private static Connection oracleConnection = null;
	
	 public static void main(String[] args) throws ConnectionException, IOException, SQLException, Exception{
		 
		 CallableStatement setupStmt = null;
	     String batchProgram = null;
		 String sourceName = null;
		 String apexClassName = null;
		 String soql = null;
		 String useLastFinishedDate = null;
		 String lastFinishedDate = null;
   	     String jobId = null;
		Boolean isFinished = false;	

   	     // To read from script
 	      batchProgram=System.getenv("VAR_INPUT_PARAM1");
   	     
   	     // Local testing  
 	      //batchProgram = "PPSContactCounts";

		 try {		 
			 if ( batchProgram != null) {
				 
				 if (oracleConnection == null){
					 oracleConnection = TransactionScope.getConnection();
					 System.out.println("got Oracle Connection..");
				 }
					
				   System.out.println("Fetching meta data for batch program: "+batchProgram);
					setupStmt = (CallableStatement)oracleConnection.prepareCall("{call CANON_E633_SF_APXJOB_PKG.get_apxjob_setup(?,?)}");
					
					if (setupStmt != null) {
						setupStmt.setObject(1, batchProgram, OracleTypes.VARCHAR);
						setupStmt.registerOutParameter(2, OracleTypes.CURSOR);
						System.out.println("Executing the query");
						setupStmt.execute();
						System.out.println("query executed");
					}

					ResultSet setupCur = (ResultSet)setupStmt.getObject(2);	
					System.out.println("setupCur size: " +setupCur.getFetchSize());
					//System.out.println("setupCur size: " +setupCur.getStatement());
					
				if (setupCur.next()) {
					System.out.println("processing setupCur ");
					sourceName = setupCur.getString("source_name");
					apexClassName = setupCur.getString("apex_class_name");
					soql = setupCur.getString("soql");
					useLastFinishedDate = setupCur.getString("use_last_finished_date");
					lastFinishedDate = setupCur.getString("last_finished_date");

					System.out.println("Batch Program : " + batchProgram);
					System.out.println("Source Name : " + sourceName);
					System.out.println("Apex Class Name : " + apexClassName);
					System.out.println("SOQL : " + soql);
					System.out.println("Use Last Finished Date : " + useLastFinishedDate);
					System.out.println("Last Finished Date : " + lastFinishedDate);
					
					System.out.println("trying to log in ......");
					 login(batchProgram);
					
					if (isReady(apexClassName)){
						System.out.println("Calling callS21SalesforceProxy for "+ batchProgram);
						jobId = callS21SalesforceProxy(batchProgram, soql,sourceName,lastFinishedDate);
						System.out.println("Job ID >>> " + jobId);
									
						while(!isFinished && (jobId != null)){
							try{
								Thread.sleep(CBS_BATCH_WAIT_TIME);
							}catch(InterruptedException ee){
								System.out.println(ee);
							}
							isFinished = isBatchFinished(jobId);						
						}					
						
						//if(jobId != null){
							System.out.println("Updating finished timestamp on batch program");
							CallableStatement callableStatement = (CallableStatement)oracleConnection.prepareCall("{call CANON_E633_SF_APXJOB_PKG.update_finished_date(?)}");
							if (callableStatement != null) {
								callableStatement.setObject(1, batchProgram, OracleTypes.VARCHAR);
								callableStatement.execute();
							}
					//	}			
					} else {
						try {
							Thread.sleep(CBS_BATCH_WAIT_TIME);
						} catch (InterruptedException ee) {
							System.out.println(ee);
						}
					}


				} else{
					 System.out.println("Invalid Batch Program name");
					}		 
			 } else {
				 System.out.println("Batch Program name is required");
			 }
				 
		 } catch(Exception ex) {
			 	ex.printStackTrace();
		 } catch (Throwable e) {
			 System.out.println("Error : " +e.getMessage());
			 System.out.println("Error cause : "+e.getCause());
			e.printStackTrace();
		} finally{
			 try {
				 	if (setupStmt != null)
				 		setupStmt.close();
				 	if ( oracleConnection != null){
				 		TransactionScope.releaseConnection(oracleConnection);
				 	}
			 } catch (Exception ep){
				 ep.printStackTrace();
			 }
		 }
	 }






	/**
	  * 
	  * Based on batch program calls appropriate method from s21 wrapper
	  * 
	  * @param batchProgram
	 * @param soql 
	 * @param lastFinishedDate 
	 * @param sourceName 
	  * @return
	  * @throws Throwable
	  */
	private static String callS21SalesforceProxy(String batchProgram, String soql, String sourceName, String lastFinishedDate) throws Throwable {
		
			com.canon.usa.s21.integration.service.salesforce.pps.batch.wrapper.SalesforceProxy ppsSFProxy = new com.canon.usa.s21.integration.service.salesforce.pps.batch.wrapper.SalesforceProxy();
			com.canon.usa.s21.integration.service.salesforce.lfs.batch.wrapper.SalesforceProxy lfsSFProxy = new com.canon.usa.s21.integration.service.salesforce.lfs.batch.wrapper.SalesforceProxy();
			String jobId = null;
			
			if (batchProgram.contains("PPS")){
				if (batchProgram.equalsIgnoreCase("PPSContactCounts")){
					PPSContactCounts ppsContactCounts = new PPSContactCounts();
					jobId = (ppsSFProxy.ppsContactCounts(ppsContactCounts)).getResult();
				}
				
				if (batchProgram.equalsIgnoreCase("PPSUpdateCounty")){
					PPSUpdateCounty ppsUpdateCounty = new PPSUpdateCounty();
					jobId = (ppsSFProxy.ppsUpdateCounty(ppsUpdateCounty)).getResult();
				}
				
			}
			
			if (batchProgram.contains("LFS")){
				if (batchProgram.equalsIgnoreCase("LFSUpdateMarketGroup")){
					LFSUpdateMarketGroup lfsUpdateMarketGroup = new LFSUpdateMarketGroup();
					jobId = (lfsSFProxy.lfsUpdateMarketGroup(lfsUpdateMarketGroup)).getResult();
				}
				
				if (batchProgram.equalsIgnoreCase("LFSCompetitiveAssetCounts")){
					LFSCompetitiveAssetCounts lfsCompAssetCount = new LFSCompetitiveAssetCounts();
					jobId = (lfsSFProxy.lfsCompetitiveAssetCounts(lfsCompAssetCount)).getResult();
				}
			}
			
			return jobId;
		}
	 
	 
	/**
	 * 
	 * Returns true  if same batch is not running &
	 * if there are no more than allowed number of jobs that are currently running
	 * 
	 * @param apexClassName
	 * @return
	 * @throws Throwable
	 */
	private static boolean isReady(String apexClassName) throws Throwable {
		com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy sfProxy = new com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy();

		Query request = new Query();
		String isReadyForSubmitBatchSoql = "Select jobType from AsyncApexJob a where jobType = 'BatchApex' and  status in ('Queued','Processing')";
		String isAnotherSameBatchSoql  = "Select  jobType from AsyncApexJob a where jobType = 'BatchApex' and  status in ('Queued','Processing') and a.ApexClass.Name = ";
		
		SessionHeader sessionHeader = new SessionHeader();
		sessionHeader.setSessionId(sessionId);
		sessionHeader.setSessionUrl(serviceUrl);
		
		System.out.println("Checking for current running batch count..."); 
		request.setQueryString(isReadyForSubmitBatchSoql);
		// int batchCount = salesforceProxy.query(request).getResult().getSize();
		int batchCount = sfProxy.query(request, sessionHeader,  new QueryOptions(), new MruHeader(),
				new PackageVersionHeader(), new Holder<LimitInfoHeader>()).getResult().getSize();
		System.out.println("batchCount:"+batchCount); 
		if (batchCount >= CBS_SF_MAX_BATCHCOUNT-1){
			// if more than allowed jobs are running then wait and check again
			return false;
		}
		
		System.out.println("Checking if  Another Same Batch is running ... "); 
		request.setQueryString(isAnotherSameBatchSoql +" '"+ apexClassName+"' ");
		//int sameBatchCount = salesforceProxy.query(request).getResult().getSize();
		int sameBatchCount = sfProxy.query(request, sessionHeader,  new QueryOptions(), new MruHeader(),
				new PackageVersionHeader(), new Holder<LimitInfoHeader>()).getResult().getSize();
		System.out.println("sameBatchCount:"+sameBatchCount); 
		if (sameBatchCount > 0){
			// If Same batch is already running then wait for it to finish
			return false;
		}
		
		return true;
	}

	 
	
	
	/**
	 * 
	 * Checks if batch is finished and returns true if it is
	 * 
	 * @param jobId
	 * @return
	 * @throws Throwable
	 */
	private static Boolean isBatchFinished(String jobId) throws Throwable {
	    System.out.println("Checking for is Batch Finishedt..."); 
	    
		SessionHeader sessionHeader = new SessionHeader();
		sessionHeader.setSessionId(sessionId);
		sessionHeader.setSessionUrl(serviceUrl);
		
		Query request = new Query();
		com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy salesforceProxy = new com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy();
	    String isBatchFinishedSoql = "Select  Status from AsyncApexJob a where status in ('Aborted','Completed','Failed') and a.Id = ";    
	    request.setQueryString(isBatchFinishedSoql +" '" +  jobId + "' ");

		//int batchCount = salesforceProxy.query(request).getResult().getSize();
		int batchCount = salesforceProxy.query(request, sessionHeader,  new QueryOptions(), new MruHeader(),
				new PackageVersionHeader(), new Holder<LimitInfoHeader>()).getResult().getSize();
		System.out.println("isBatchFinishedSoql Result "+batchCount);
		
		if (batchCount > 0){
			System.out.println("Job Finished....");
			return true;
		} else{
			System.out.println("Job Still running....");
			return false;
		}
		
	}
	
	
	
	public static LoginResponse login(String batchProgram) throws Throwable{
		 LoginResponse lResp = null;
		 Login lReq = new Login();
		 SalesforceProxy sfProxy = new SalesforceProxy();
		 String[] loginCredentials = null;
		 CanonSalesForceCommon commonObj = new CanonSalesForceCommon();
		 String handlerName = "ESS";
		 
		 try {
			 if(batchProgram.contains("PPS")) 
			 	handlerName = "PPS";
			 if(batchProgram.contains("LFS"))
			 	handlerName = "LFS";
			 loginCredentials = commonObj.getLoginCredentials(handlerName);
			 lReq.setUsername(loginCredentials[0]);
			 lReq.setPassword(loginCredentials[1]);
			 //lReq.setUsername("cbsinterface@solutions.canon.com.sfdcdevs21");
			 //lReq.setPassword("welcome2lUoUbSkEp3wsfiPFxHtGFy1Yx");
			lResp = sfProxy.login(lReq);
			sessionId = lResp.getResult().getSessionId();
			serviceUrl = lResp.getResult().getServerUrl();
			System.out.println("sessionId:"+sessionId);

		} catch (Throwable e) {
			try {
				if ((e.getMessage()).contains("ConnectionException")){
					Thread.sleep(5000); //sleep for 5sec
					if(batchProgram.contains("PPS")) 
					 	handlerName = "PPS";
					 if(batchProgram.contains("LFS"))
					 	handlerName = "LFS";
					 loginCredentials = commonObj.getLoginCredentials(handlerName);
					 lReq.setUsername(loginCredentials[0]);
					 lReq.setPassword(loginCredentials[1]);
					//lReq.setUsername("cbsinterface@solutions.canon.com.sfdcdevs21");
					 //lReq.setPassword("welcome2lUoUbSkEp3wsfiPFxHtGFy1Yx");
					lResp = sfProxy.login(lReq);
				}else throw e;
			}catch(Exception exp){
				exp.printStackTrace();
				throw exp;
			}
			throw e;
		}	 
		 return lResp;
		 
	 }
	
	
	
	public static String StringToDateTime (String str_date) {

        String pattern = "yyyy-MM-dd HH:mm:ss";
		Calendar targCal = Calendar.getInstance(TimeZone.getTimeZone("zulu")); //UTC time stamp
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //UTC foramt
		sdf.setCalendar(targCal);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
		
        try {
                if (str_date.length() > 0){
						str_date = sdf.format(df.parse(str_date));
				}		
                                
        }
        catch(Exception e) {
                        System.out.println("StringToDate():Exception: " +e.getMessage());
                e.printStackTrace();
        }
		
        return str_date ;
}


}