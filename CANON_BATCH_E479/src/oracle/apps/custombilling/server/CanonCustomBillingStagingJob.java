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

public class CanonCustomBillingStagingJob {
	
	public static final String STAGING_EXTRACT= "{call CANON_E479_BILL_EXTRACT_PKG.staging_extract(?,?,?,?,?,?,?,?,?)}";
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
    	
    	System.out.println("Source :"+inputParam1);
    	System.out.println("customer_group :"+inputParam2);
    	System.out.println("parent_customer :"+inputParam3);
    	System.out.println("Customer :"+inputParam4);
    	System.out.println("bill_to_location :"+inputParam5);
    	System.out.println("from_date :"+inputParam6);
    	System.out.println("to_date :"+inputParam7);
    	
    	CanonCustomBillingStagingJob obj=new CanonCustomBillingStagingJob();
    	String errFlag = obj.createBillingStaging(p_source,inputParam2,inputParam3,inputParam4,inputParam5,inputParam6,inputParam7);
    	System.out.println("createBillingStaging called:"+ errFlag);
    	
    }    
    
    public static String checkdate() throws ParseException
    {
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	
	    Calendar cal = Calendar.getInstance();
	    Integer year = cal.get(Calendar.YEAR);
	    Integer month = cal.get(Calendar.MONTH)+1;
	    Integer nextmonth = cal.get(Calendar.MONTH)+1;

	    String oeStartDate1Str = month.toString()+"/13/";
	    String oeEndDate1Str = month.toString()+"/16/";
	    
	    String oeStartDate2Str = month.toString()+"/28/";
	    String oeEndDate2Str = nextmonth.toString()+"/01/";

	    oeStartDate1Str = oeStartDate1Str.concat(year.toString());
	    oeEndDate1Str = oeEndDate1Str.concat(year.toString());
	    
	    
	    oeStartDate2Str = oeStartDate2Str.concat(year.toString());
	    if (month==12)
	    	oeEndDate2Str = oeEndDate2Str.concat(year.toString()+1);
	    else
	    	oeEndDate2Str = oeEndDate2Str.concat(year.toString());

	    
	    
	    System.out.println("oeStartDate1Str..." + oeStartDate1Str);
	    System.out.println("oeEndDate1Str..."+oeEndDate1Str);
	    System.out.println("oeStartDate2Str..." + oeStartDate2Str);
	    System.out.println("oeEndDate2Str..."+oeEndDate2Str);
	    Integer dayminus=0;
	    //System.out.println("CanonCustomProfile .CANON_E479_BILLING_EXTRACT_FROM_DATE .."+CanonCustomProfile.getSystemProfileValue("CANON_E479_BILLING_EXTRACT_FROM_DATE"));
	    try {
			
			dayminus =Integer.parseInt(util.getProfile(null,"","","CANON_E479_BILLING_EXTRACT_FROM_DATE"));
			System.out.println("CanonCustomProfile .CANON_E479_BILLING_EXTRACT_FROM_DATE .."+ dayminus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    	    
	    Date startDate1 = sdf.parse(oeStartDate1Str);
	    Date endDate1 = sdf.parse(oeEndDate1Str);
	    Date startDate2 = sdf.parse(oeStartDate1Str);
	    Date endDate2 = sdf.parse(oeEndDate1Str);

	    
	    Date d = new Date();
	    String currDt = sdf.format(d);
	
	
	    if((d.after(startDate1) && (d.before(endDate1))) || (currDt.equals(sdf.format(startDate1)) ||currDt.equals(sdf.format(endDate1)))){
	        System.out.println("Date is between "+oeStartDate1Str+" and " + oeEndDate1Str);
	        return ""+cal.get(Calendar.MONTH) +"/16/"+year.toString();
	    }
	    else if((d.after(startDate2) && (d.before(endDate2))) || (currDt.equals(sdf.format(startDate2)) ||currDt.equals(sdf.format(endDate2)))){
	    	System.out.println("Date is between "+oeStartDate2Str+" and " + oeEndDate2Str);
	    	return ""+cal.get(Calendar.MONTH)+1+"/01/"+year.toString();
	    }
	    else{
	        System.out.println("Date is not between the check dates...");
	        cal.add(Calendar.DATE, - dayminus);
	        return ""+ sdf.format(cal.getTime());
	    }
    }

    
    public static String createBillingStaging(String p_source,String p_customer_group,String p_parent_customer,String p_customer,String p_bill_to_location,String p_from_date,String p_to_date ) throws ParseException{
        //clearException();
        System.out.println("in createBillingStaging "+p_source);
        CallableStatement statement = null;
        Connection connection = null;
        //String dt = "2017-11-08";  // Start date
        String dt = checkdate();
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        Calendar cc = Calendar.getInstance();
        cc.setTime(currentDate);
        cc.add(Calendar.DATE, 1);  // number of days to add
        String today = sdf.format(cc.getTime());
        
        
        
        try {
        	Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dt);
        	Date nextday = new SimpleDateFormat("MM/dd/yyyy").parse(today);
        	
        	System.out.println(" From Date.."+ date);
        	System.out.println(" To Date.."+ nextday);
        	
        	connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(STAGING_EXTRACT);
                if (statement != null) {
                	System.out.println("in Executed "+p_source);
                    statement.setObject(1, p_source, OracleTypes.VARCHAR);
                    statement.setObject(2, p_customer_group, OracleTypes.VARCHAR);
                    statement.setObject(3, p_parent_customer, OracleTypes.VARCHAR);
                    statement.setObject(4, p_customer, OracleTypes.VARCHAR);
                    statement.setObject(5, p_bill_to_location, OracleTypes.VARCHAR);
					statement.setObject(6, new java.sql.Date(date.getTime()), OracleTypes.DATE);
                    statement.setObject(7, new java.sql.Date(nextday.getTime()), OracleTypes.DATE);
                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("after Executed "+p_source);
                    String ErrFlag= statement.getString(8);
                    String ErrMess= statement.getString(9);
                    System.out.println("ErrFlag "+ErrFlag);
                    System.out.println("ErrMessage "+ErrMess);
                    return ErrFlag;
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
