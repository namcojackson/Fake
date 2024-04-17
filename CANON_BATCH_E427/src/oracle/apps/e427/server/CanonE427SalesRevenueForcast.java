package oracle.apps.e427.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.StringTokenizer;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE427SalesRevenueForcast{
	public static final String FETCH_REVENUE_DATA= "{call CANON_E427_REV_FORECAST_PKG.FETCH_REVENUE_DATA(?)}";
	
	public  void fetchRevenueData(){
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {
            	
                statement = (CallableStatement) connection.prepareCall(FETCH_REVENUE_DATA);
                if (statement != null) {                    
                    statement.registerOutParameter(1,OracleTypes.VARCHAR);   
                    statement.execute();        
                    String getSalesOrderExtractmsg=statement.getString(1);
                    
                    StringTokenizer allMessages=new StringTokenizer(getSalesOrderExtractmsg, "~");
                    while(allMessages.hasMoreTokens())
                    {
                    	String salesMsg=allMessages.nextToken();
                    	System.out.println(salesMsg);
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
   }
	
	public static void main(String[] args) {		    	
    	    
		CanonE427SalesRevenueForcast canonE427SalesRevenueForcast=new CanonE427SalesRevenueForcast();
		canonE427SalesRevenueForcast.fetchRevenueData();
    	System.out.println("CanonR569SalesRevenueForcast called:");
    	
	}
}
