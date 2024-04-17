/***********************************************************************
*  FileName : CanonE618FlashConcProg.java
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

public class CanonE618FlashConcProg {

	public static final String FLASH_PROG = "{call CANON_E618_S21_FLASH_PKG.MAIN(?,?)}";



    public void stageExtract()
    {
        System.out.println("in Stage extract ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {

                statement = (CallableStatement) connection.prepareCall(FLASH_PROG);
                if (statement != null) {
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

    	CanonE618FlashConcProg CanonE618FlashConcProg=new CanonE618FlashConcProg();
    	CanonE618FlashConcProg.stageExtract();

    	System.out.println("stageExtract called:");

    }


}
