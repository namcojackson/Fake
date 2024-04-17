package oracle.apps.e633.server;

/***********************************************************************
*  FileName : CanonE633ExtractConcProg.java
*	
*	Modification History :
*	Date		ITG Ticket #	Author			Description
*	---------------------------------------------------------------------
*	
*
************************************************************************/


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.CallableStatement;
import java.sql.Connection;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE633ExtractConcProg {

	public static final String EXTRACT_DB = "{call CANON_E633_SF_LFSPPS_PKG.extract(?,?,?)}";
	public static final String PPS_USER_DB = "{call CANON_E633_PPS_SF_USER_PKG.extract_users(?,?)}";
	public static final String LFS_USER_DB = "{call CANON_E633_LFS_SF_USER_PKG.extract_users(?,?)}";
	public static final String PROSPECT_MERGE_DB = "{call CANON_E633_SF_PROS_MERGE_PKG.extract_prospects(?,?)}";
	public static final String ATM_DB = "{call CANON_E633_SF_ACCT_TEAM_PKG.process_account_team(?,?)}";
	public static final String LFS_CONTACTS_DB ="{call CANON_E633_SUPLY_CONTACTS_PKG.EXTRACT_LFS_SUPPLY_CONTACTS(?,?)}";
	
	 
	
	 public static void main(String args[]) throws Exception {
		// System.out.println("CanonE633ExtractConcProg called:");
    	String param="";
    	String inputParam1=System.getenv("VAR_USER1");
    	try {
	    	if(inputParam1==null || inputParam1.trim().length()==0) {
				inputParam1="No Value Provided";
				System.out.println("Please provide value for extract, valid values are PPS_USER, LFS_USER, PROSPECT_MERGE,...");
				throw new Exception("Please provide value for extract");
			}
	    	else {
	    		//System.out.println("Action:"+inputParam1+"| param:"+param);
	    		//CanonE633ExtractConcProg e633ExtractCP=new CanonE633ExtractConcProg();
	    		
	    			if("PPS_USER".equalsIgnoreCase(inputParam1)){
	    				executeSQLPKG(PPS_USER_DB,null);
	    			}else if ("LFS_USER".equalsIgnoreCase(inputParam1)){
	    				executeSQLPKG(LFS_USER_DB,null);
	    			}else if("PROSPECT_MERGE".equalsIgnoreCase(inputParam1)){
	    				executeSQLPKG(PROSPECT_MERGE_DB,null);
					}else if("ACCOUNT_TEAM".equalsIgnoreCase(inputParam1)){
						executeSQLPKG(ATM_DB,null);
					}else if("LFS_SUPLY_CONTACTS".equalsIgnoreCase(inputParam1)){
						executeSQLPKG(LFS_CONTACTS_DB,null);
	    			}else 
	    				executeSQLPKG(EXTRACT_DB,inputParam1);
					
			}
    	}catch (Exception ex) {
            ex.printStackTrace();
            System.exit(99);
            throw ex;
        }
    }
	
	public static void executeSQLPKG(String dbCallStr,String handler) throws Exception {
		System.out.println("in executeSQLPKG ");
		System.out.println("Extract for : " +handler);
		System.out.println("DB Call : " +dbCallStr);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {
            		statement = (CallableStatement) connection.prepareCall(dbCallStr);
                    if (statement != null) {
                    	statement.registerOutParameter(1,OracleTypes.VARCHAR);  
    				    statement.registerOutParameter(2,OracleTypes.VARCHAR);
                    	if(handler != null)
                    		statement.setObject(3, handler);
                        
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
                        throw new Exception("executeSQLPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                    }
            } else {
                System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
                throw new Exception("executeSQLPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
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
                    throw exp;
                }
            }
            if (connection != null) {
                try {
                    TransactionScope.releaseConnection(connection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw ex;
                }
            }

        }
   }
	
    static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    public static Timestamp toTimestamp(String tsStr) {
        try {
            return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(sdf.parse(tsStr).getTime());
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }


}

