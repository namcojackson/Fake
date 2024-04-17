package oracle.apps.salesrevenue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.StringTokenizer;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;


public class CanonR569SalesRevenueForcast {
	public static final String FETCH_REVENUE_DATA= "{call CANON_R569_REV_FORECAST_PKG.FETCH_REVENUE_DATA(?,?,?)}";
	
	public  void fetchRevenueData(String p_date_from, String p_date_to){
        System.out.println("in fetchRevenueData "+"|"+p_date_from+"|"+p_date_to);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {
            	
                statement = (CallableStatement) connection.prepareCall(FETCH_REVENUE_DATA);
                if (statement != null) {                    
                    statement.setObject(1, p_date_from, OracleTypes.VARCHAR);
                    statement.setObject(2, p_date_to, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3,OracleTypes.VARCHAR);   
                    statement.execute();        
                    String getSalesOrderExtractmsg=statement.getString(3);
                    
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
    	String toDate="";
    	String fromDate="";
    	
    	
    	String inputParam1=System.getenv("VAR_INPUT_PARAM1");
    	if(inputParam1==null || inputParam1.trim().length()==0)
    	inputParam1="No Value Provided";
    	else
    		fromDate=inputParam1;
    	
    	String inputParam2=System.getenv("VAR_INPUT_PARAM2");
    	if(inputParam2==null || inputParam2.trim().length()==0)
    	inputParam2="No Value Provided";
    	else
    		toDate=inputParam2;
    	
    	System.out.println("OrderNumber:"+" FromDate:"+fromDate+" Todate:"+toDate);
    	CanonR569SalesRevenueForcast canonITTWorkBenchConcProg=new CanonR569SalesRevenueForcast();
    	canonITTWorkBenchConcProg.fetchRevenueData(fromDate, toDate);
    	System.out.println("CanonR569SalesRevenueForcast called:");
    	
	}
}
