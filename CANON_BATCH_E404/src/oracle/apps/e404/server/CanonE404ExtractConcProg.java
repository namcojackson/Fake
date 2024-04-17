/***********************************************************************
*  FileName : CanonE404ExtractConcProg.java
*	
*	Modification History :
*	Date		ITG Ticket #	Author			Description
*	---------------------------------------------------------------------
*	
*
************************************************************************/
package oracle.apps.e404.server;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.CallableStatement;
import java.sql.Connection;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE404ExtractConcProg {

	public static final String EXTRACT_USERS = "{call CANON_E404_SF_USER_PKG.extract_users(?,?)}";
	public static final String EXTRACT_ACCOUNTS = "{call CANON_E404_SF_ACCOUNT_PKG.extract_accounts(?,?)}";
	public static final String EXTRACT_I98	= "{call CANON_I98_ATRS_PKG.terr_tbl_populate(?,?)}";
	public static final String EXTRACT_RD159 = "{call CANON_RD159_GEO_TERR_PKG.populate_geo_terr(?,?)}";
	public static final String EXTRACT_CONTDTL = "{call CANON_E404_RD143_CONTDTL_PKG.extract_contdtl(?,?)}";
	public static final String EXTRACT_CFS = "{call CANON_E404_SF_CFS_LEASE_PKG.load_cfs_leases(?,?)}";
	public static final String EXTRACT_IB = "{call CANON_E404_SF_INSTALL_BASE_PKG.submit_program(?,?)}";
	public static final String EXTRACT_RD143 = "{call CANON_RD143_CONT_MACH_PKG.main(?,?)}";
	public static final String EXTRACT_ACCT_TEAM = "{call CANON_E404_SF_ACCT_TEAM_PKG.process_account_team(?,?)}";
	public static final String EXTRACT_ACCT_OWNER_AT = "{call canon_e404_owner_at_change_pkg.extract_owner_at_changes(?,?,?,?,?)}"; //changed for defect#21245
	public static final String EXTRACT_E215 = "{call canon_e215_cfs_lease_upg_pkg.lease_data_incr_refresh(?,?)}";
	public static final String EXTRACT_PRODUCT = "{call canon_e404_sf_product_pkg.extract_products(?,?)}";
	public static final String EXTRACT_STACK_RANK = "{call canon_e404_sf_stack_rank_pkg.extract_stack_rank(?,?)}";
	public static final String EXTRACT_PROS_MERGE = "{call canon_e404_sf_pros_merge_pkg.extract_prospects(?,?)}";
	public static final String EXTRACT_IBLEASECONNECTOR = "{call canon_e404_ibleaseconnect_pkg.extract(?,?)}";
	
	
	 public static void main(String args[]) throws Exception {
    	//String action = ""; 
    	int action = 0;
    	String param="";
    	String inputParam1=System.getenv("VAR_USER1");
		
		try {
    	if(inputParam1==null || inputParam1.trim().length()==0) {
			inputParam1="No Value Provided";
			System.out.println("Please provide value for extract, valid values are USER, ACCOUNT, I98, RD159, CONTDTL, CFS");
		}
    	else {
    			if ((inputParam1.toUpperCase()).equals("USER"))
    				executeSQLPKG(EXTRACT_USERS);
    				 //action = 1;
    			else if ((inputParam1.toUpperCase()).equals("ACCOUNT"))
    				executeSQLPKG(EXTRACT_ACCOUNTS);
    				//action = 2;
    			else if ((inputParam1.toUpperCase()).equals("I98"))
    				executeSQLPKG(EXTRACT_I98);
    				//action = 3;
    			else if ((inputParam1.toUpperCase()).equals("RD159"))
    				executeSQLPKG(EXTRACT_RD159);
    				//action = 4;
    			else if ((inputParam1.toUpperCase()).equals("CONTDTL"))
    				executeSQLPKG(EXTRACT_CONTDTL);
    				//action = 5;
    			else if ((inputParam1.toUpperCase()).equals("CFS"))
    				executeSQLPKG(EXTRACT_CFS);
    				//action = 6;
    			else if((inputParam1.toUpperCase()).equals("ACCT_TEAM"))
    				executeSQLPKG(EXTRACT_ACCT_TEAM);
    			else if((inputParam1.toUpperCase()).equals("IB"))
    				executeSQLPKG(EXTRACT_IB);
    			else if((inputParam1.toUpperCase()).equals("ACCT_OWNER_AT"))
    				executeSQLPKG(EXTRACT_ACCT_OWNER_AT);
    			else if((inputParam1.toUpperCase()).equals("E215"))
    				executeSQLPKG(EXTRACT_E215);
    			else if((inputParam1.toUpperCase()).equals("RD143"))
    				executeSQLPKG(EXTRACT_RD143);
				else if((inputParam1.toUpperCase()).equals("PRDOUCT"))
    				executeSQLPKG(EXTRACT_PRODUCT);
    			else if((inputParam1.toUpperCase()).equals("STACK_RANK"))
    				executeSQLPKG(EXTRACT_STACK_RANK);
				else if((inputParam1.toUpperCase()).equals("PROSPECT_MERGE"))
    				executeSQLPKG(EXTRACT_PROS_MERGE);
				else if((inputParam1.toUpperCase()).equals("IBLEASECONNECTOREXT"))
    				executeSQLPKG(EXTRACT_IBLEASECONNECTOR);
    	
				/*String inputParam2=System.getenv("VAR_USER2");
				if(inputParam2==null || inputParam2.trim().length()==0)
					inputParam2="No Value Provided";
				else
					param=inputParam2;*/
				
				//System.out.println("Action:"+inputParam1+"| param:"+fromDate);
				//System.out.println("Action:"+inputParam1+"| param:"+param);
				//CanonE404ExtractConcProg e404ExtractCP=new CanonE404ExtractConcProg();
				//e404ExtractCP.extract(action, param);
				//System.out.println("CanonE404ExtractConcProg called:");
		}
		} catch (Exception ex) {
            ex.printStackTrace();
            System.exit(99);
            throw ex;
        }
    }
	
	
	/*public void extract(int action, String param) {
        
		switch (action) {
			case 1 :
				executeSQLPKG(EXTRACT_USERS);
				break;
			case 2 :
				executeSQLPKG(EXTRACT_ACCOUNTS);
				break;
			case 3 :
				executeSQLPKG(EXTRACT_I98);
				break;
			case 4 :
				executeSQLPKG(EXTRACT_RD159);
				break;
			case 5 :
				executeSQLPKG(EXTRACT_CONTDTL);
				break;
			case 6 :
				executeSQLPKG(EXTRACT_CFS);
				break;
			default :
				break;
		}
        	
	}*/
	
	
	public static void executeSQLPKG(String dbCallStr) throws Exception{
		System.out.println("in executeSQLPKG ");
		System.out.println("Extract call: " +dbCallStr);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {
            	
                statement = (CallableStatement) connection.prepareCall(dbCallStr);
                if (statement != null) {
					if(dbCallStr.contains("owner_at_change")){ //added for defect#21245
    				    statement.setNull(3,  OracleTypes.VARCHAR);
    				    statement.setNull(4,  OracleTypes.VARCHAR);
    				    statement.setObject(5, "N", OracleTypes.VARCHAR);
                    }
                    statement.registerOutParameter(1,OracleTypes.VARCHAR);  
				    statement.registerOutParameter(2,OracleTypes.VARCHAR);
                    statement.execute();        
					//QC21578
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
