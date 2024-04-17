package canon.salesforce.bulk;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.canon.usa.s21.integration.service.salesforce.asyncapi.dataload.QueryResult;
import com.canon.usa.s21.integration.service.salesforce.asyncapi.dataload.RequestList;
import com.canon.usa.s21.integration.service.salesforce.asyncapi.dataload.ResultList;
import com.canon.usa.s21.integration.service.salesforce.asyncapi.dataload.SObject;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import canon.salesforce.bulk.CanonE633SFBulkClient.AbstractSyncHandler;
import canon.salesforce.bulk.CanonE633SFBulkClient.LineReader;
//import oracle.jdbc.OracleConnection;
//import edu.emory.mathcs.backport.java.util.Arrays;
//import canon.salesforce.bulk.CanonE404SfQryRsltRecTST;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;

public class CanonE633SFBulkHandler extends AbstractSyncHandler {
	
	public static final String getColumnMapping = "{call CANON_E633_SF_BULK_PKG.GET_COLUMN_MAPPING(?,?)}";
    private static String getLoadData = null;
    private static final String updateData = "{call CANON_E633_SF_BULK_PKG.update_sf_data(?,?,?)}";
    private static final String updateReqRsltData = "{call CANON_E633_SF_BULK_PKG.update_req_rslt_data(?)}";
    private static final String updateQryRsltData = "{call CANON_E633_SF_BULK_PKG.update_qry_rslt_data(?)}";
    private static final String getWhereClause = "{call CANON_E633_SF_BULK_PKG.get_where_clause(?,?)}";
    private static Connection oracleConnection = null;
    
    String sObjectName;
    String operation ;
    String externalIDField;
    static String handlerName;
    String jobId;
    
    public CanonE633SFBulkHandler(String handlerName, String operation, String sObject, String externalIdFieldName) {
        this.handlerName = handlerName;
        this.operation = operation;
        this.sObjectName = sObject;
        this.externalIDField = externalIdFieldName;
        
        getLoadData = "{call CANON_E633_SF_BULK_PKG.get_data(?,?)}";
        
              
        System.out.println("handlerName : " +handlerName);
        System.out.println("operation : " +operation);
        System.out.println("externalIDField : " +externalIDField);
        System.out.println("sObjectName : " +sObjectName);
    }
    
	/*public static void main(String args[]) throws Exception {
	
		HandlerTest testHndlr = new HandlerTest();
		
		//String handlerName=System.getenv("VAR_INPUT_PARAM1");
		
		if (handlerName.equals("AccountUpload") ){
    		get_load_data = "{call CANON_E633_SF_ACCOUNT_PKG.get_load_data(?)}";
    		update_data = "{call CANON_E633_SF_ACCOUNT_PKG.update_data(?)}";
		}
    	else if (handlerName.equals("IBUpload") )
    		get_load_data = "{call CANON_E633_INSTALL_BASE_PKG.get_load_data(?)}";
    	
    	System.out.println("handlerName : " +handlerName);
    	LineReader lr = testHndlr.getLineReader(handlerName);
    	
    }*/
	
	@Override
	public String getOperation() {
            //return com.sforce.async.OperationEnum.insert; ITG#523770
			return operation; 
    }

	@Override
    public String getSobjectType() {
		return sObjectName;
    }

    @Override
    public String getExternalIdFieldName() {
    	//System.out.println("handler::externalIDFIeld : " +externalIDField);
		return externalIDField;
    }
    
    @Override
    public void onEnter() {
    	if(handlerName.contains("Roles")){
    		purgeRolesData();
    	}
    	
    	if(handlerName.contains("Profiles")){
    		purgeProfilesData();
    	}
    }

    @Override
    public void onLeave() {
		updateAllData(jobId, handlerName, operation);     	
        if (oracleConnection != null) {
            TransactionScope.releaseConnection(oracleConnection);
        }
    }
    
	public LineReader getLineReader() {
		
		//final LinkedHashMap<String, String> lhm = null;
		final CallableStatement statement ;
		final CallableStatement dataStmt ;
		final CallableStatement filterStmt;
		Connection connection = null;
		
		//System.out.println("In getLineReader....");
        try {
        	connection = TransactionScope.getConnection();
            statement = (CallableStatement) connection.prepareCall(getColumnMapping);
            filterStmt = (CallableStatement) connection.prepareCall(getWhereClause);
			
            if (statement != null) { //for header row
            	statement.setObject(1, handlerName, OracleTypes.VARCHAR);
                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.execute();
                final ResultSet cursor = (ResultSet) statement.getObject(2);
                if (operation.equalsIgnoreCase("query")){
                	LineReader lineReader = new LineReader(){
                		@Override 
                		public String readLine() throws IOException, SQLException
                		{
                			List<String> header = new ArrayList<String>();
                			while (cursor.next()){
                				header.add(cursor.getString("SF_OBJECT_FIELD_NAME"));
                			}
                			//System.out.println("header ...................." +header.toString());
                			String[] fields = header.toArray(new String[header.size()]);
                			
                			//System.out.println("fields ...................." +fields.toString());
                			StringBuilder sb = new StringBuilder(); 
                			sb.append("SELECT ");
                			for (int x=0; x<fields.length; x++){
                				if (x==0)
                					sb.append(fields[x]);
                				else {
                					sb.append(",");
                					sb.append(fields[x]);
                				}
                			}
                			//sb.append(header.toString());
                			sb.append( " FROM " + sObjectName);
                			//System.out.println("sb ...................." +sb.toString());
                			
                			//get the filter conditions(where clause) if any
                			if(filterStmt != null) {
                				filterStmt.setObject(1, handlerName, OracleTypes.VARCHAR);
                            	filterStmt.registerOutParameter(2, OracleTypes.CURSOR);
                				filterStmt.execute();
                				final ResultSet filterCur = (ResultSet)filterStmt.getObject(2);
                			
	                			while (filterCur.next()) {
	                				sb.append("  ");
	                				if (filterCur.getString("where_clause") != null){
	                					sb.append(" WHERE  ");
	                					sb.append(filterCur.getString("where_clause"));
	                				}
	                					
	                			}
                			}
                			
                			//if (handlerName.equalsIgnoreCase("CFSAuditDownload")){
                				//sb.append(" WHERE isdeleted = false  AND  lastModifiedDate = LAST_N_DAYS:7 ");
                			//	sb.append(" WHERE isdeleted = false  AND  lastModifiedDate = today ");
                			//}
                			return sb.toString();	
                			//return csvLine((String[]) header.toArray());
                		}
                		
                		@Override
    					public void close(){
    						try {
    							 if (cursor != null)
    								 cursor.close();
    							 if (statement != null)
    								 statement.close();
    						} catch (Exception exp){
    							exp.printStackTrace();
    						}
    					}
                	};
					return lineReader;
				}
                
                if (operation.equalsIgnoreCase("upsert") ||
						operation.equalsIgnoreCase("insert") ||
						operation.equalsIgnoreCase("update") ||
						operation.equalsIgnoreCase("delete") ||
						operation.equalsIgnoreCase("merge") ||
						operation.equalsIgnoreCase("hard_delete")){
                	//Final CallableStatement dataStmt = (CallableStatement) connection.prepareCall(getLoadData);
                	
                dataStmt = (CallableStatement) connection.prepareCall(getLoadData);
                if (dataStmt != null){ //for data
                	dataStmt.setObject(1, handlerName, OracleTypes.VARCHAR);
                	dataStmt.registerOutParameter(2, OracleTypes.CURSOR);
    				dataStmt.execute();
    				final ResultSet dataCur = (ResultSet)dataStmt.getObject(2);   				    				
    				final LinkedHashMap<String, String> lhm = new LinkedHashMap<String,String>();
				LineReader lineReader = new LineReader() { 
					int lineCnt = 0;
					
					
					@Override
                    public String readLine() throws IOException, SQLException {
						List<String> header = new ArrayList<String>();	
						System.out.println("in readLine");
						//final LinkedHashMap<String, String> lhm = new LinkedHashMap<String,String>();	
						
						 if (lineCnt == 0) {
	                            lineCnt++;
	                            while(cursor.next()) {
	            					header.add(cursor.getString("SF_OBJECT_FIELD_NAME"));
	            					
	            					lhm.put(cursor.getString("ORACLE_COLUMN_NAME"),cursor.getString("TYPE"));
	            				}
	                            //System.out.println("before sending 1 ........" +header);
	                            //System.out.println(lhm.toString());
	                            return csvLine(header.toArray(new String[header.size()]));
	                            
						 }
						 try {
							 //System.out.println("dataCur size: ..........." +dataCur.getFetchSize());
							 
							 if (dataCur.next()) {
								 lineCnt++;
								 //System.out.println("before sending 3 .......dataCur.next()." );
								 String s = null;
								 List<String> dataStr = new ArrayList<String>();
								 for(String k: lhm.keySet()){
										s = lhm.get(k);
										if (s.equalsIgnoreCase("String") ) 
											dataStr.add(nullToNA(dataCur.getString(k)));
										else if (s.equalsIgnoreCase("Date") )
											dataStr.add(csvDate(dataCur.getDate(k)));
										else if (s.equalsIgnoreCase("DateTime") )
											dataStr.add(csvDateTime(dataCur.getString(k)));
										else if(s.equalsIgnoreCase("Number") )
											dataStr.add(nullToNA(csvBigDecimal(dataCur.getBigDecimal(k))));
										//else if(s.equalsIgnoreCase("Boolean"))
											//dataStr.add("TRUE".equals(dataCur.getBoolean(k))?"true":"false");
										//System.out.println("before returning ........" +dataStr);
								}
								 //System.out.println("before sending 2 ........" +dataStr);
								 return csvLine(dataStr.toArray(new String[dataStr.size()]));
								 
								 
							 }else return null;
							 
						 }catch (Exception ex){
							 ex.printStackTrace();
						 }
						
						 return ",,";
					}
					
					
					@Override
					public void close(){
						try {
							if(dataCur != null)
								dataCur.close();
							 if (cursor != null)
								 cursor.close();
							 if (statement != null)
								 statement.close();
							 if (dataStmt != null)
								 dataStmt.close();
						} catch (Exception exp){
							exp.printStackTrace();
						}
					}
					
				};
				return lineReader;
                }
            }
            }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
				
	    }

	
	@Override
	//public void update(String requestReader, String resultReader, String jobId){
	public void update(RequestList requestList, ResultList resultList1, String jobId){	
		List<CanonE633SFReqRsltRec> resultList = new ArrayList<CanonE633SFReqRsltRec>(); 
		 List<CanonE633SFQryRsltRec> qryRsltList = new ArrayList<CanonE633SFQryRsltRec>(); 
		this.jobId = jobId;
		//String requestReader = null;
		//String resultReader = null;
		List<QueryResult> qrObjList ;
				
		try {	
			
			BufferedReader requestReader = new BufferedReader(new StringReader(requestList.getCSVData()));
			BufferedReader resultReader = new BufferedReader(new StringReader(resultList1.getCSVData()));
			
			//requestReader = requestList.getCSVData();
			//resultReader = resultList1.getCSVData();
			//System.out.println("requestReader : " +requestReader);
			//System.out.println("resultReader : " +resultReader);
			
			
				//String[] req = requestReader.split("\\r?\\n");
				//String[] res = resultReader.split("\\r?\\n");
				
				//System.out.println("req length : " +req.length);
				//System.out.println(" res length : " +res.length);
				
			 //List<String> reqList = Arrays.asList(requestReader.split("\\r?\\n"));
	        // List<String> resList = Arrays.asList(resultReader.split("\\r?\\n"));
	         List<String> reqList = new ArrayList<String>();
	         List<String> resList = new ArrayList<String>();
	         for (String reqLine = requestReader.readLine(); reqLine != null; reqLine = requestReader.readLine()) {
	 		    reqList.add(reqLine);
	 		}
	         
	         for (String resLine = resultReader.readLine(); resLine != null; resLine = resultReader.readLine()) {
		 		    resList.add(resLine);
		 		    //System.out.println("resLine : " +resLine);
		 		}
	         requestReader.close();
	         resultReader.close();
	         
	         int itr = 1;
	         
	         //System.out.println("reqList : " +reqList.size());
	         //System.out.println("resList : " +resList.size());

        // while ((result = resultReader.nextRecord()) != null && (request = requestReader.nextRecord()) != null) {
             //while ((result = resultReader.nextRecord()) != null ) {
		if (!operation.equalsIgnoreCase("query")) {
			
            for (int a=1; a<resList.size(); a++){
            		String result = resList.get(a);
            		//System.out.println("result : " +result);
            	
            	String[] resultData = result.split(",");
            	String[] requestData = (reqList.get(itr)).split(",");
            	
            	//System.out.println("resultData length : " +resultData.length);
            	
            	//System.out.println("requestData length : " +requestData.length);
            	
             if (!operation.equalsIgnoreCase("query") &&
                  !operation.equalsIgnoreCase("hard_delete") &&
                  !operation.equalsIgnoreCase("delete")){
                     CanonE633SFReqRsltRec b = new CanonE633SFReqRsltRec();
                     CanonE633SFQryRsltRec q = new CanonE633SFQryRsltRec();
                     String id = resultData[0].replace("\"","");
                    // System.out.println("success: " +resultData[1]);
                     //System.out.println("created: " +resultData[2]);
                     boolean success = "true".equalsIgnoreCase(resultData[1].replace("\"", ""))?true:false;
                     boolean created = "true".equalsIgnoreCase(resultData[2].replace("\"",""))?true:false;
                     //System.out.println("success : " +success);
                     //System.out.println("created : " +created);
                     String error = resultData[3];
                     String instanceID = requestData[0]; //change the index number according to the request index
                    /// System.out.println("instanceID : " +instanceID);
                    // System.out.println("jobId : " +jobId);
                     String message = null;
                     String status = null;
                    
                     if (success) { 
                         status = "P";
                         if (created) 
                              message = "Created Data in salesforce";
                         if(!created) 
                              message = "Updated Data in salesforce";
                     } else {
                         status = "E";
                         message = trimToNull(error.replace("\"", ""));
                     }
                     
                     
                     if (handlerName.contains("ATMAcctShare")){
                    	 q.setSfdcid(id);
                    	 q.setJobId(jobId);
                    	 q.setAttribute1(instanceID.replace("\"", "")); //site_sfdc_id
                    	 q.setAttribute2(requestData[1].replace("\"", "")); //user_sfdc_id
                    	 q.setAttribute3(status);
                         q.setAttribute4(message);
                         qryRsltList.add(q);
                         //System.out.println("resultToOracle............" +q);
                     } 
                     else if (handlerName.contains("ATMUpload")){
                    	 q.setSfdcid(id);;
                    	 q.setJobId(jobId);
                    	 q.setAttribute1(instanceID.replace("\"", "")); //site_sfdc_id
                    	 q.setAttribute2(requestData[1].replace("\"", "")); //user_sfdc_id
                    	 q.setAttribute3(requestData[2].replace("\"","")); //role
                    	 q.setAttribute4(status);
                    	 q.setAttribute5(message);
                    	 qryRsltList.add(q);
                         //System.out.println("resultToOracle............" +q);
                     }else {
                    	 b.setSfdcid(id);
                    	 b.setInstanceid(instanceID.replace("\"", ""));
                         b.setJobId(jobId); //new for S21 - instead of requestId
                         b.setStatus(status);
                         b.setMessage(message);
                    	 resultList.add(b);
                    	 //System.out.println("resultToOracle............" +b);
                     }
                     
                     
                     
             }
             
             
           /*  if (operation.equalsIgnoreCase("query")){
                 CanonE633SFQryRsltRec q = new CanonE633SFQryRsltRec();
                 //log.info("resultReader......................." +result);
                 

                 //for (String s : result) {
                 for (int cnt=0; cnt<resultData.length; cnt++){
                         if (cnt == 0){
                                 q.setSfdcid(resultData[cnt]);
                                 //log.info("sfdcid.............." +result.get(cnt));
                         }
                         if (cnt > 0){
                                 q.setAttribute(resultData[cnt], cnt);
                                 //log.info("setting attribute " + cnt + "    value......" +result.get(cnt));
                         }

                 }
                 //cnt = 0;
                 q.setJobId(jobId);
                 System.out.println("jobId..............." +jobId);
                 System.out.println("result ..............." +q);
                 qryRsltList.add(q);
         }*/
            
             
         if (operation.equalsIgnoreCase("hard_delete") ||
        		 operation.equalsIgnoreCase("delete")){
                 CanonE633SFReqRsltRec b = new CanonE633SFReqRsltRec();
                 //String id = result.get(0);
                // request = requestReader.nextRecord();
                 String id = requestData[0].replace("\"", "");
                 boolean success = "true".equalsIgnoreCase(resultData[1].replace("\"", ""))?true:false;
                 boolean created = "true".equalsIgnoreCase(resultData[2].replace("\"", ""))?true:false;
                 String error = resultData[3].replace("\"", "");
                 String message = null;
                 String status = null;
                 if (success) {
                         status = "P";
                         message = "Deleted Data in Salesforce";
                 } else {
                         String[] msgs = error.split(":");
                         if (msgs[0].contains("ENTITY") ||
                                 msgs[0].contains("INVALID_CROSS")){
                                 status = "P";
                                 message = "Deleted Data in Salesforce";
                         } else {
                                 status = "E";
                                 message = trimToNull(error.replace("\"", ""));
                         }
                 }
                 b.setSfdcid(id);
                 b.setStatus(status);
                 b.setMessage(message);
                 b.setJobId(jobId);
                 resultList.add(b);
                 //System.out.println("resultToOracle............" +b);
         }
         itr++;

     }
		}
		
		 if (operation.equalsIgnoreCase("query")){
			 qrObjList = resultList1.getResult();
			 List<SObject> sObjList ;
            
             //System.out.println("resultReader......................." +resultList);
             
            /* for(QueryResult qr : qrObjList){
            	 sObjList = qr.getRecords();
            	 /*for (SObject sObj : sObjList){
            		 System.out.println("sObj ID: " +sObj.getId());
            		 System.out.println("sObj Type: " +sObj.getType());
            		 System.out.println("sObj Any: " +sObj.getAny());
            	 }
             }*/

             //for (String s : result) {
             for (int cnt=1; cnt<resList.size(); cnt++){
            	 String[] csvData = resList.get(cnt).split("\",\"");
            	 //System.out.println("csvData : " +Arrays.toString(csvData));
            	 CanonE633SFQryRsltRec q = new CanonE633SFQryRsltRec();
            	 for (int i=0; i<csvData.length; i++){
                     if (i == 0){
                             q.setSfdcid(csvData[i].replace("\"", ""));
                             //System.out.println("sfdcid.............." +csvData[i]);
                     }
                     if (i > 0){
                             q.setAttribute(csvData[i].replace("\"", ""), i);
                             //System.out.println("setting attribute " + i + "    value......" +csvData[i]);
                     }
                    
            	 }
            	 q.setJobId(jobId);
                 qryRsltList.add(q);
                 //System.out.println("jobId..............." +jobId);
                 //System.out.println("result ..............." +q);
             }
             //cnt = 0;
             
             
             
     }

           //updateIb(getConnection(), resultList);
             if (resultList != null && resultList.size() > 0) {
                 //System.out.println("resultList.size...................." +resultList.size());
                 updateReqRsltData(resultList);
             }

             if (qryRsltList != null && qryRsltList.size() > 0) {
                 //System.out.println("qryRsltList.size.................." +qryRsltList.size());
                 updateQryRsltData(qryRsltList);

             }

         } catch (Exception ex) {
             ex.printStackTrace();
                         //ITG#510411 - START
             if ((ex.getMessage()).contains("SocketException") ||
                 (ex.getMessage()).contains("Connection reset") ) {
                                 //log.info("reprocessing.....");
                 if ( resultList != null && resultList.size() > 0)
                     updateReqRsltData(resultList);
                 if ( qryRsltList != null && qryRsltList.size() > 0)
                         updateQryRsltData(qryRsltList);
             }
         }
     }


public static Object[] updateReqRsltData(List<CanonE633SFReqRsltRec> p_result_tbl) {
    System.out.println("in updateReqRsltData....................... ");
   // log.info("p_result_tbl size : " +p_result_tbl.size());
   // for(CanonE633SfIbReqRsltRecTST a: p_result_tbl){
    //      log.info("ResultList : " +a);
    //}
    CallableStatement statement = null;
    try {
            if (oracleConnection == null)
                    oracleConnection = TransactionScope.getConnection();
        if (oracleConnection != null) {
            statement = (CallableStatement) oracleConnection.prepareCall(updateReqRsltData);
            if (statement != null) {
                    //log.info("statement is not null......................................");
                    statement.setObject(1, CanonE633SFReqRsltRec.canonE633SFReqRsltTblListToArray(p_result_tbl, oracleConnection), OracleTypes.ARRAY);
                statement.execute();
                return new Object[]{};
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
    }
    return null;
}

public static Object[] updateQryRsltData(List<CanonE633SFQryRsltRec> p_result_tbl) {
   System.out.println("in updateQryRsltData....................... ");
    //System.out.println("p_result_tbl size : " +p_result_tbl.size());
   // for(CanonE633SfIbReqRsltRecTST a: p_result_tbl){
    //      log.info("ResultList : " +a);
    //}
    CallableStatement statement = null;
    try {
            if (oracleConnection == null)
                    oracleConnection = TransactionScope.getConnection();
        if (oracleConnection != null) {
            statement = (CallableStatement) oracleConnection.prepareCall(updateQryRsltData);
            if (statement != null) {
                    //log.info("statement is not null......................................");
                    statement.setObject(1, CanonE633SFQryRsltRec.canonE633SFQryRsltTblListToArray(p_result_tbl, oracleConnection), OracleTypes.ARRAY);
                    statement.execute();
                return new Object[]{};
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
    }
    return null;
}

	
	public static void updateAllData(String jobId, String handlerName, String operation) { 
        CallableStatement statement = null;
        try {
        	if (oracleConnection == null)
        			oracleConnection = TransactionScope.getConnection();
            if (oracleConnection != null) {
                statement = (CallableStatement) oracleConnection.prepareCall(updateData);
                if (statement != null) {
                    statement.setObject(1, jobId, OracleTypes.VARCHAR);
                    statement.setObject(2, handlerName, OracleTypes.VARCHAR );
                    statement.setObject(3, operation, OracleTypes.VARCHAR);
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
        }
        
    }
	
	public static void purgeRolesData(){
		System.out.println("In purgeRolesData...............................");
		CallableStatement statement = null;
		String dbCall = "{call CANON_E633_SF_PURGE_PKG.purge_roles(?)}";
		try {
        	if (oracleConnection == null)
        			oracleConnection = TransactionScope.getConnection();
            if (oracleConnection != null) {
                statement = (CallableStatement) oracleConnection.prepareCall(dbCall);
                if (statement != null) {
                	statement.setObject(1, handlerName, OracleTypes.VARCHAR );
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
        }
	}
	
	public static void purgeProfilesData(){
		System.out.println("In purgeProfilesData...............................");
		CallableStatement statement = null;
		String dbCall = "{call CANON_E633_SF_PURGE_PKG.purge_profiles(?)}";
		try {
        	if (oracleConnection == null)
        			oracleConnection = TransactionScope.getConnection();
            if (oracleConnection != null) {
                statement = (CallableStatement) oracleConnection.prepareCall(dbCall);
                if (statement != null) {
                	statement.setObject(1, handlerName, OracleTypes.VARCHAR );
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
        }
	}
	
	
}
