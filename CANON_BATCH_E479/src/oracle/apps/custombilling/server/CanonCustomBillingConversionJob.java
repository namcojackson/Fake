package oracle.apps.custombilling.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonCustomBillingConversionJob {
	
	public static final String STAGING_EXTRACT= "{call CANON_E479_CONVERSION_PKG.CANON_E479_CONVERSION_MAIN()}";
	static CanonCustomBillingServerUtil util = new CanonCustomBillingServerUtil();
	
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
    	String p_source = ""; 
    	String inputParam1=System.getenv("VAR_INPUT_PARAM1");
    	String inputParam2=System.getenv("VAR_INPUT_PARAM2");
    	String inputParam3=System.getenv("VAR_INPUT_PARAM3");
    	String inputParam4=System.getenv("VAR_INPUT_PARAM4");
    	String inputParam5=System.getenv("VAR_INPUT_PARAM5");
    	String inputParam6=System.getenv("VAR_INPUT_PARAM6");
    	String inputParam7=System.getenv("VAR_INPUT_PARAM7");
    	
    	if(inputParam1==null || inputParam1.trim().length()==0)
    	inputParam1="No Value Provided";
    	else
    		p_source=inputParam1;
    	
    	CanonCustomBillingConversionJob obj=new CanonCustomBillingConversionJob();
    	String errFlag = obj.createCustomBillingStagingConversion();
    	System.out.println("createCustomBillingStagingConversion called:"+ errFlag);
    	
    }    
    

    
    public static String createCustomBillingStagingConversion() throws ParseException{
        //clearException();
        System.out.println("in createCustomBillingStagingConversion ");
        CallableStatement statement = null;
        Connection connection = null;
        //String dt = "2017-11-08";  // Start date
        //String dt = checkdate();
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        Calendar cc = Calendar.getInstance();
        cc.setTime(currentDate);
        cc.add(Calendar.DATE, 1);  // number of days to add
        String today = sdf.format(cc.getTime());
        
        
        
        try {
        	//Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dt);
        	//Date nextday = new SimpleDateFormat("MM/dd/yyyy").parse(today);
        	
        	//System.out.println(" From Date.."+ date);
        	//System.out.println(" To Date.."+ nextday);
        	
        	connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(STAGING_EXTRACT);
                if (statement != null) {
                	System.out.println("in Executed ");
                    statement.execute();
                    System.out.println("after Executed ");
                    return "S";
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            //saveException(ex);
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
        return null;
   }  

    
}
