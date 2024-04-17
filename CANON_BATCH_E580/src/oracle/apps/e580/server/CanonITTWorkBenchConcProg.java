package oracle.apps.e580.server;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonITTWorkBenchConcProg {

	public static final String SALES_ORDERS_EXTRACT= "{call CANON_E580_ITT_PROCESS_PKG.SALES_ORDERS_EXTRACT(?,?,?,?)}";
   
    
    
    public  void salesOrderExtract(String p_order_number, String p_date_from, String p_date_to){
        System.out.println("in ittHeaderDetails "+p_order_number+"|"+p_date_from+"|"+p_date_to);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {
            	
                statement = (CallableStatement) connection.prepareCall(SALES_ORDERS_EXTRACT);
                if (statement != null) {
                    statement.setObject(1, p_order_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_date_from, OracleTypes.VARCHAR);
                    statement.setObject(3, p_date_to, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4,OracleTypes.VARCHAR);   
                    statement.execute();        
                    String getSalesOrderExtractmsg=statement.getString(4);
                    
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
    
       
    public static Object first(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 1 ? null : objs[0];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 1 ? null : l.get(0);
        }
        return null;
    }

    public static Object second(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 2 ? null : objs[1];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 2 ? null : l.get(1);
        }
        return null;
    }

    
    public static void main(String args[]) throws Exception {
    	String orderNumber = ""; 
    	String toDate="";
    	String fromDate="";
    	String inputParam1=System.getenv("VAR_INPUT_PARAM1");
    	if(inputParam1==null || inputParam1.trim().length()==0)
    	inputParam1="No Value Provided";
    	else
    		orderNumber=inputParam1;
    	
    	String inputParam2=System.getenv("VAR_INPUT_PARAM2");
    	if(inputParam2==null || inputParam2.trim().length()==0)
    	inputParam2="No Value Provided";
    	else
    		fromDate=inputParam2;
    	
    	String inputParam3=System.getenv("VAR_INPUT_PARAM3");
    	if(inputParam3==null || inputParam3.trim().length()==0)
    	inputParam3="No Value Provided";
    	else
    		toDate=inputParam3;
    	
    	System.out.println("OrderNumber:"+orderNumber+" FromDate:"+fromDate+" Todate:"+toDate);
    	CanonITTWorkBenchConcProg canonITTWorkBenchConcProg=new CanonITTWorkBenchConcProg();
    	canonITTWorkBenchConcProg.salesOrderExtract(orderNumber, fromDate, toDate);
    	
    	CanonITTWorkBenchS21API s21api=new CanonITTWorkBenchS21API();
    	String[] msgs=s21api.cancelLines(orderNumber);
    	if(msgs!=null) {
    		System.out.println("return msgs:"+Arrays.asList(msgs));
    	}
    	System.out.println("CanonITTWorkBenchConcProg called:");
    	
    }

    
}
