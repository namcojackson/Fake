/***********************************************************************
*  FileName : CanonE618SoRmaConcProg.java
*
*	Modification History :
*	Date		ITG Ticket #	Author			Description
*	---------------------------------------------------------------------
*
*
************************************************************************/
package oracle.apps.e618.server;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.List;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE618SoRmaConcProg {

	public static final String SALES_ORDERS_EXTRACT= "{call CANON_E618_ORDERS_RMA_STG_PKG.MAIN(?,?,?,?)}";



    public  void salesOrderExtract(String p_date_from, String p_date_to){
        System.out.println("in SoRma extract "+p_date_from+"|"+p_date_to);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {

                statement = (CallableStatement) connection.prepareCall(SALES_ORDERS_EXTRACT);
                if (statement != null) {
                    statement.setObject(1, p_date_from, OracleTypes.VARCHAR);
                    statement.setObject(2, p_date_to, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3,OracleTypes.VARCHAR);
                    statement.registerOutParameter(4,OracleTypes.VARCHAR);
                    statement.execute();
                   	String statusCode = statement.getString(4);
					   System.out.println("statusCode: " +statusCode);
					   if (statusCode != null &&
						!statusCode.isEmpty() &&
						("E".equalsIgnoreCase(statusCode) ||
						 "4".equalsIgnoreCase(statusCode))){
						System.err.println("SQL PKG error: statusCode :" +statusCode + " message:" +statement.getString(3));
						throw new Exception(statement.getString(3));
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

    	System.out.println("FromDate:"+fromDate+" Todate:"+toDate);
    	CanonE618SoRmaConcProg CanonE618SoRmaConcProg=new CanonE618SoRmaConcProg();
    	CanonE618SoRmaConcProg.salesOrderExtract(fromDate, toDate);

    	System.out.println("CanonE618SoRmaConcProg called:");

    }


}
