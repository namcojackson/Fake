package canon.apps.common;
import java.sql.CallableStatement;
import java.sql.Connection;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonCommonUtilDao {

   public static final String GET_PROFILE_VALUE= "{call CANON_COMMON_UTIL_PKG.GET_PROFILE_VALUE(?,?,?,?,?,?,?)}";
   public static final String PROFILE_BY_CHAR= "{?= call CANON_COMMON_UTIL_PKG.PROFILE_BY_CHAR(?,?,?)}";
   public static final String PROFILE_BY_DATE= "{?= call CANON_COMMON_UTIL_PKG.PROFILE_BY_DATE(?,?,?)}";
   public static final String PROFILE_BY_NUMBER= "{?= call CANON_COMMON_UTIL_PKG.PROFILE_BY_NUMBER(?,?,?)}";
   public static final String RETRIEVE_PROFILE_VALUES= "{call CANON_E001_CODETABLE_UI_PKG.RETRIEVE_PROFILE_VALUES(?,?,?,?,?,?)}";

   private static ThreadLocal exception = new ThreadLocal();
   private static void saveException(Exception e){
       exception.set(e);
   }
   private static void clearException(){
       exception.set(null);
   }
   public static Exception getException(){
       return (Exception)exception.get();
   }

   public static Object[] getProfileValue(String p_profile_name,
    String p_profile_type){
        clearException();
        System.out.println("in getProfileValue "+p_profile_name+"|"+p_profile_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_PROFILE_VALUE);
                if (statement != null) {
                    statement.setObject(1, p_profile_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_profile_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.NUMBER);
                    statement.registerOutParameter(5, OracleTypes.TIMESTAMP);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(3)
                        ,statement.getObject(4)
                        ,statement.getObject(5)
                        ,statement.getObject(6)
                        ,statement.getObject(7)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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

   public static Object[] profileByChar(String p_profile_name){
        clearException();
        System.out.println("in profileByChar "+p_profile_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROFILE_BY_CHAR);
                if (statement != null) {
                    statement.setObject(2, p_profile_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1)
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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

   public static Object[] profileByDate(String p_profile_name){
        clearException();
        System.out.println("in profileByDate "+p_profile_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROFILE_BY_DATE);
                if (statement != null) {
                    statement.setObject(2, p_profile_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.DATE);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1)
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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

   public static Object[] profileByNumber(String p_profile_name){
        clearException();
        System.out.println("in profileByNumber "+p_profile_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROFILE_BY_NUMBER);
                if (statement != null) {
                    statement.setObject(2, p_profile_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.NUMBER);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1)
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
   
   public static Object[] retrieveProfileValues(String p_user_name,
		    String p_role_name,
		    String p_profile_name){
		        clearException();
		        System.out.println("in retrieveProfileValues "+p_user_name+"|"+p_role_name+"|"+p_profile_name);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
		            connection = TransactionScope.getConnection();
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(RETRIEVE_PROFILE_VALUES);
		                if (statement != null) {
		                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
		                    statement.setObject(2, p_role_name, OracleTypes.VARCHAR);
		                    statement.setObject(3, p_profile_name, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(6, OracleTypes.VARCHAR);

		                    statement.execute();
		                    return new Object[]{
		                        statement.getObject(4)                   
		                        ,statement.getObject(5)                   
		                        ,statement.getObject(6)};
		                } else {
		                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
		                }
		            } else {
		                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		            }
		        } catch (Exception ex) {
		            saveException(ex);
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
