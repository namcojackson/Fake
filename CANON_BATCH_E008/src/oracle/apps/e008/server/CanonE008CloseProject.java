package oracle.apps.e008.server;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.apps.e008.server.CanonE008CheckandNotify.RowMapper;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE008CloseProject {

	public static final String CHECK_PRICING_ASSIGN= "{call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_PRICING_ASSIGN(?,?)}";
    
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
    	String projectNumber = ""; 
    	String inputParam1=System.getenv("VAR_INPUT_PARAM1");
    	if(inputParam1==null || inputParam1.trim().length()==0)
    	inputParam1="No Value Provided";
    	else
    		projectNumber=inputParam1;
    	
    	System.out.println("OrderNumber:"+projectNumber);
    	CanonE008CloseProject obj=new CanonE008CloseProject();
    	String errFlag = obj.CheckPricingAssign(projectNumber);
    	System.out.println("CanonE590ConcProg called:"+ errFlag);
    	
    }    
    
    
    public static String CheckPricingAssign(String p_project_no){
        //clearException();
        System.out.println("in CheckPricingAssign "+p_project_no);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_PRICING_ASSIGN);
                if (statement != null) {
                	System.out.println("in Executed "+p_project_no);
                    statement.setObject(1, p_project_no, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("after Executed "+p_project_no);
                    String ErrFlag= statement.getString(2);
                    System.out.println("ErrFlag "+ErrFlag);
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
