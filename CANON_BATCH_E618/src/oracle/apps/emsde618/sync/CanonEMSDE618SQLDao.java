package oracle.apps.emsde618.sync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.apps.emsde618.sync.CanonEMSDE618ServImpactRec;

public class CanonEMSDE618SQLDao {
	String quoteId="";
	String quote_Id="";
	String quoteIdSubQry="";
	String countQtSubQry="";
	private  String GET_SERVICE_REQUESTS="SELECT  *  FROM v_EMSD_Service_Request WHERE 1=1 ";


	
	private String sqlServerInfo;
	private CanonEMSDE618OracleDao oracleDao;
	private int fetchSize=10;
	
	
	public CanonEMSDE618SQLDao (String sqlSrvrInfo){
		this.sqlServerInfo=sqlSrvrInfo;
		oracleDao = new CanonEMSDE618OracleDao();
		fetchSize = 500;
	}
	
	public  Connection getSqlServerConnection() {
		System.out.println("INFO : Connecting to SQLServer...");
        try {
            String[] info = sqlServerInfo.split(":");
            String serverHost = info[0];
            String serverUserName = info[1];
            String serverUserPassword = info[2];
            String serverDB = info[3];
            String serverPort = info[4];

            String connectionUrl = "jdbc:jtds:sqlserver://" + serverHost + ":" + serverPort + "/" + serverDB;
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            System.out.println("connectionUrl: "+connectionUrl);
            return DriverManager.getConnection(connectionUrl, serverUserName, serverUserPassword);

        } catch (Exception ex) {
        	System.out.println("Unable to get the SQL Server Connection.");
    		System.out.println("**ERROR :  " + ex.getMessage());
    		ex.printStackTrace();
        }

        return null;
    }

 
 
  public  boolean loadDataForServiceRequests(Connection connection){
		
	    List<CanonEMSDE618ServImpactRec> list = new ArrayList<CanonEMSDE618ServImpactRec>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res=true;
		int i= 1;
		System.out.println( "INFO: Loading Data For Service Requests ...");
		try {		 
			ps = connection.prepareStatement(GET_SERVICE_REQUESTS);
			rs = ps.executeQuery();
			rs.setFetchSize(fetchSize);  
			while (rs.next()) {
				CanonEMSDE618ServImpactRec canRec = new CanonEMSDE618ServImpactRec(
						rs.getString("IncidentNumber"),
						rs.getString("SerialNumber"),
						rs.getString("IncidentType"), 
						rs.getString("Status"),
						rs.getString("Severity"),
						rs.getTimestamp("IncidentCreateDate"),
						rs.getString("CreatedBy"), 
						rs.getString("Assigned"),
						rs.getString("ClosedBy"),
						rs.getTimestamp("IncidentClosedDate"),
						rs.getString("ProblemSummary"),
						rs.getString("CustomerContact"),
						rs.getString("Address"),
						rs.getString("City"),
						rs.getString("State"),
						rs.getString("ZIP"),
						rs.getString("TaskNumber"),
						rs.getString("TaskPriority"),
						rs.getTimestamp("TechArrivedDate"),
						rs.getTimestamp("TechCompletedDate"),
						rs.getString("TaskStatus"),
						rs.getString("TaskProblemDescription"),
						rs.getString("SRConfirmationNumber"),
						rs.getTimestamp("IncidentResolvedDate"),
				       rs.getString("Resolution"),
				       rs.getString("TaskType"),
				       rs.getString("Source"));
				// list.add(canRec);
				if (list.size() == 1000) {
					System.out.println("Batch "+ i );
					i++;
					boolean b = oracleDao.insertDataForServImpact(list);
					if (!b) {
						System.out.println("ERROR in Inserting Data For  ServiceRequests");
						res = false;
					}
					list = new ArrayList<CanonEMSDE618ServImpactRec>();
					list.add(canRec);
				} else {
					list.add(canRec);
				}
			}
			
			if (list.size() < 1000) {
                boolean b = oracleDao.insertDataForServImpact(list);
                if (!b) {
                	System.out.println("ERROR in Inserting Data For  ServiceRequests");
                    res = false;
                }
            }
			//boolean b=	oracleDao.insertDataForServImpact(list);
/*			if(!b){
			     System.out.println("ERROR in Inserting Data For  ServiceRequests");
				 res=false;
			} else{
				System.out.println("INFO: Inserted/Updated "+ list.size() + " Service Requests in Oracle");
			}*/
		} catch (Exception exception) {
			System.out.println("ERROR in Loading ServiceRequests");
			System.out.println("exception :  " + exception.getMessage());
			res =false;
		} finally {
			try {
            close(ps, rs);			
			 } catch (Exception exception2) {
					System.out.println("**ERROR :  " + exception2.getMessage());
			}
		}	
		return res;
	}

 
    private static void close(PreparedStatement ps , ResultSet rs){
    	try{
	    	if (ps != null)
				ps.close();
    	}catch (Exception e) {
    		System.out.println("**ERROR :  " + e.getMessage());
		}	
    	try{
			if (rs != null)
				rs.close();
	    }catch (Exception e) {
			System.out.println("**ERROR :  " + e.getMessage());
		}	
	}

	public void closeConn(Connection connection) {
		try{	
	        if (connection != null)
	        	connection.close();
    	}catch (Exception e) {
    		System.out.println("**ERROR :  " + e.getMessage());
		}
	}
	
	


   
}
