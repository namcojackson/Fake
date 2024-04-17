package oracle.apps.emsde618.sync;

import java.sql.Connection;
import java.util.Date;

import oracle.apps.emsde618.sync.CanonEMSDE618OracleDao;
import oracle.apps.emsde618.sync.CanonEMSDE618SQLDao;


public class CanonEMSDE618SqlServerSyncProgram {

	
	public static void main(String[] args) {
		System.out.println("INFO: Started  Program For loading Impact  Service Requests at: "+ new Date()+ " \n"); 
		CanonEMSDE618OracleDao oracleDao = new CanonEMSDE618OracleDao();
		String serverInfo = oracleDao.getSqlServerInfo();
		
		CanonEMSDE618SQLDao sqlDao = new CanonEMSDE618SQLDao(serverInfo);
		Connection conn = sqlDao.getSqlServerConnection();
		
		if (conn == null) {
			System.out.println("ERROR Details : Unable to connect to SQLServer");
		} else {
			if (oracleDao.truncateTable()){
				System.out.println("INFO: Truncated Table Successfully \n"); 
				 boolean isLoaded=false;
		            try {
						long startTime = System.currentTimeMillis();				
						isLoaded =	 loadData(oracleDao, sqlDao, conn);
						System.out.println("INFO:  Total processing time in Seconds  : [ "+ ((System.currentTimeMillis() - startTime) / 1000)+ " ] \n");		
						Date end = new Date();		    
						
						if(isLoaded){
						System.out.println("Successfully Inserted Data For Service Requests  finished at " + end); 
						}else{
							System.out.println("ERROR: Error occured while loading one or more views data");
						}		  			
					} catch (Exception e) {
						System.out.println("Unable Complete the request run the file");
						System.out.println("ERROR Details : " + e.getMessage() );
					}finally{
					    try{	 
						sqlDao.closeConn(conn);
					    }catch (Exception e1) {
							e1.printStackTrace();
						}
					}	
			}
           
	     }		
	}	
	
	
	public static boolean loadData(CanonEMSDE618OracleDao oracleDao,CanonEMSDE618SQLDao sqlDao, Connection conn) {
		boolean isLoaded = false;
		isLoaded = sqlDao.loadDataForServiceRequests(conn);
		return isLoaded;
	}
}
