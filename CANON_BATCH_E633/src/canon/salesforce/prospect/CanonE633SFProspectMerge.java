package canon.salesforce.prospect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import com.canon.usa.s21.integration.service.salesforce.Soap;
import com.canon.usa.s21.integration.service.salesforce.enterprise.*;
import com.canon.usa.s21.integration.service.salesforce.enterprise.Error;
import com.canon.usa.s21.integration.service.salesforce.sobject.Account;
import com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy;

import canon.salesforce.bulk.CanonE633SFReqRsltRec;
import canon.salesforce.common.*;

public class CanonE633SFProspectMerge {

	private static Connection oracleConnection = null;
	private static final String mergeData = "{call CANON_E633_SF_BULK_PKG.get_data(?,?)}";
	private static final String updateReqRsltData = "{call CANON_E633_SF_BULK_PKG.update_req_rslt_data(?)}";
	private static final String updateSFData = "{call CANON_E633_SF_BULK_PKG.update_sf_data(?,?,?)}";
	static String handlerName = null;
	private SalesforceProxy sfProxy = new SalesforceProxy();
	String[] loginCredentials = null;
	
  public static void main(String[] args) {
	  try {
			CanonSalesForceCommon commonObj = new CanonSalesForceCommon();
		    CanonE633SFProspectMerge prosObj = new CanonE633SFProspectMerge();
		    
		    oracleConnection = TransactionScope.getConnection();
		    handlerName=System.getenv("VAR_USER1");
		    //Scanner in = new Scanner(System.in);
		    //System.out.println("Please enter handler name:");
			 //handlerName = "ProspectMerge";
			 //in.close();
			 System.out.println("handler : " +handlerName);
			 prosObj.mergeProspects(commonObj);
	  } catch (Exception e){
		  System.out.println(e.getMessage());
		  System.out.println(e.getStackTrace());
		  System.exit(99);
	  } catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(99);
	} finally {
		  if (oracleConnection != null)
			  TransactionScope.releaseConnection(oracleConnection);
	  }
  }

  public CanonE633SFProspectMerge() {
  }


 /* private String getProspectsUpdateSql() {
	  
    StringBuffer updsql = new StringBuffer(300);

    updsql.append(" UPDATE ds_acct_rvw_pros darp ");
    updsql.append("    SET xtrnl_pros_rvw_sts_cd = ?");
    updsql.append("       ,xtrnl_pros_rvw_cmnt_txt = ?");
    updsql.append("       ,xtrnl_pros_rvw_dt = SYSDATE");
    updsql.append("  WHERE pros_rvw_id = ?");
    updsql.append("    AND NVL(PROS_RVW_STS_CD,'X') = 'D' ");
    updsql.append("    AND NVL(xtrnl_pros_rvw_sts_cd,'E') = 'E' ");

    System.out.println("\nupdate sql for prospects: " + updsql.toString());
    return updsql.toString();
  }*/

  private void mergeProspects(CanonSalesForceCommon commonObj) throws Throwable, Exception {
    CallableStatement mergeDataStmt = null;
    PreparedStatement preparedStatement = null;
    ResultSet rset = null;
    int count = 0;
    LoginResponse sfLogin = null;
    String mergePId = null;
    String masterPId = null;
    Soap binding = null;
    List<CanonE633SFReqRsltRec> resultList = new ArrayList<CanonE633SFReqRsltRec>();
    
   
    System.out.println("In mergeProspects......");
    try {
    	
		
     // binding = commonObj.getBinding();
      
      //MergeRequest mr = new MergeRequest();
      
      SessionHeader sessionHeader = new SessionHeader();
      Login lReq = new Login();
       loginCredentials = commonObj.getLoginCredentials(handlerName);
       lReq.setUsername(loginCredentials[0]);
	   lReq.setPassword(loginCredentials[1]);
		 System.out.println("Salesforce userName: " + lReq.getUsername());
		try {
			sfLogin = sfProxy.login(lReq);
			System.out.println("login user: " +sfLogin.getResult().getUserId());
			System.out.println("sessionId : " +sfLogin.getResult().getSessionId());
			System.out.println("serviceUrl : " +sfLogin.getResult().getServerUrl());
			System.out.println("login resp : " +sfLogin.getResult().getUserInfo().toString());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
      
		sessionHeader.setSessionId(sfLogin.getResult().getSessionId());
		sessionHeader.setSessionUrl(sfLogin.getResult().getServerUrl());
		System.out.println("logged In :" +sfLogin.getResult().getUserId());
      
      if (oracleConnection == null)
          oracleConnection = TransactionScope.getConnection();
      
      
     // preparedStatement = oracleConnection.prepareStatement(getProspectsUpdateSql());
      try {
      if (oracleConnection != null) {
    	  mergeDataStmt = (CallableStatement) oracleConnection.prepareCall(mergeData);
    	  if (mergeDataStmt != null) {
    		  mergeDataStmt.setObject(1, handlerName, OracleTypes.VARCHAR);
        	  mergeDataStmt.registerOutParameter(2, OracleTypes.CURSOR);
    		  mergeDataStmt.execute();
    	  }
      } else 
          System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
      } catch(Throwable eb){
    	  eb.printStackTrace();
    	  throw eb;
      }

      
      ResultSet setupCur = (ResultSet)mergeDataStmt.getObject(2);
      System.out.println("\nSTART PROSPECT MERGE " );
     
		while (setupCur.next()){
			count = count+1;
			mergePId = setupCur.getString("merge_pid");
			masterPId = setupCur.getString("master_pid");
			
			System.out.println("mergePId = " +mergePId);
			System.out.println("masterPId = " +masterPId);

	        Account masterAcct = new Account();
	        MergeRequest mr = new MergeRequest();
	        Merge merge = new Merge();
	        MergeResponse mergeResp = null;
	        
	        masterAcct.setId(masterPId);
	        System.out.println("Account created");
	        //set MergeRequest
	        mr.setMasterRecord(masterAcct);   
	        mr.getRecordToMergeIds().add(mergePId);
	        System.out.println("MergeRequest is created");
	        //set Merge
	        merge.getRequest().add(mr);
	     	System.out.println("MergeRequest added to Merge");
	       
		   try{
	        	//mergeResp = sfProxy.merge(merge);
	        	mergeResp = sfProxy.merge(merge,sessionHeader, null, null, null, null, null, null, null, null, null, null, null);
	        	System.out.println("merge resp: " +mergeResp.getResult().size());
	        } catch (Throwable e) {
	      		// TODO Auto-generated catch block
	      		e.printStackTrace();
	      		throw e;
	        }
	        System.out.println("MergeResp ?? : " +mergeResp);
	
	        String status = "";
	        String message = "";
        
	        if (mergeResp != null){
	        	System.out.println("mergeResp is not null");
	        	 CanonE633SFReqRsltRec result = new CanonE633SFReqRsltRec();
	        	for (MergeResult mRes : mergeResp.getResult()) {
	        		System.out.println("mRes success ? : " +mRes.isSuccess());
	        		if (mRes.isSuccess()){
	        			status = "P";
	        			message = "Merged Data in salesforce";
	        		} else {
	        			status = "E";
	        			Error err = mRes.getErrors().get(0);
	        			message = err.getMessage();
	        		}
	        	}
	        	result.setSfdcid(mergePId);
	        	result.setMessage(message);
	        	result.setStatus(status);
	        	resultList.add(result);
	        	
	        }

        	
		}
		System.out.println("updating results......");
		updateProspect(resultList);

      System.out.println("\nTotal Records Processed: " + count);
      //System.out.println("\nEND PROSPECT MERGE - uniqueDate: " + commonObj.getUniqueDate());
      
    }  catch(Exception exception) {
      System.err.println("\nException: " + exception.getMessage());
      try {
    	Login login = new Login();
	  		loginCredentials = commonObj.getLoginCredentials(handlerName);
	        login.setUsername(loginCredentials[0]);
	 	    login.setPassword(loginCredentials[1]);
			if (sfLogin == null)
    	  		//sfLogin = commonObj.getSOAPSalesForceLogin();
    	  		sfLogin = sfProxy.login(login);
		    //binding = commonObj.getBinding();
      } catch(Exception ee) {
        System.err.println("\nError in logging again: " + ee.getMessage());
        throw ee;
      } catch (Throwable e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		throw e;
      }
      throw exception;
  	} finally {
      try {
        if(rset != null)
          rset.close();

        if(preparedStatement != null)
          preparedStatement.close();

       if(sfLogin != null){
    	   Logout logout = new Logout();
    	   //commonObj.sfLogout(logout);
       }
          
      } catch(Exception exception2) {
        System.err.println("\nException: " + exception2.getMessage());
        throw exception2;
      }
  	}
  }


  private void updateProspect(List<CanonE633SFReqRsltRec> updList) throws Exception {
	  System.out.println("In updateProspects.......");
	  System.out.println("updList size : "+updList.size());
    try {
    	CallableStatement statement = null;
        try {
                if (oracleConnection == null)
                        oracleConnection = TransactionScope.getConnection();
            if (oracleConnection != null) {
                statement = (CallableStatement) oracleConnection.prepareCall(updateReqRsltData);
                if (statement != null) {
                        //log.info("statement is not null......................................");
                        statement.setObject(1, CanonE633SFReqRsltRec.canonE633SFReqRsltTblListToArray(updList, oracleConnection), OracleTypes.ARRAY);
                    statement.execute();
                    
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
                statement.close();
                //calling update sf data
                System.out.println("calling update_sf_data");
                statement = (CallableStatement) oracleConnection.prepareCall(updateSFData);
                if(statement != null) {
                	statement.setObject(1, "", OracleTypes.VARCHAR);
                    statement.setObject(2, handlerName, OracleTypes.VARCHAR );
                    statement.setObject(3, "merge", OracleTypes.VARCHAR);
                    statement.execute();
                } else 
                	System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        }
        
    }
    catch(Exception exception) {
      System.err.println("\nException: " + exception.getMessage());
      throw exception;
    }
  }

}