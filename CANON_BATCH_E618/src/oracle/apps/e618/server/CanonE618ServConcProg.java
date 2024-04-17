/***********************************************************************
*  FileName : CanonE618ServConcProg.java
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

public class CanonE618ServConcProg {

	public static final String SERV_REQ_EXTRACT= "{call CANON_E618_SERVICE_REQ_STG_PKG.MAIN(?,?,?,?)}";



    public  void servReqExtract(String p_date_from, String p_date_to){
        System.out.println("in servReqExtract extract "+p_date_from+"|"+p_date_to);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {

                statement = (CallableStatement) connection.prepareCall(SERV_REQ_EXTRACT);
                if (statement != null) {
                    statement.setObject(3, p_date_from, OracleTypes.VARCHAR);
                    statement.setObject(4, p_date_to, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1,OracleTypes.VARCHAR);
                    statement.registerOutParameter(2,OracleTypes.VARCHAR);
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
    	CanonE618ServConcProg CanonE618ServConcProg=new CanonE618ServConcProg();
    	CanonE618ServConcProg.servReqExtract(fromDate, toDate);

    	System.out.println("CanonE618ServConcProg called:");

    }


}
