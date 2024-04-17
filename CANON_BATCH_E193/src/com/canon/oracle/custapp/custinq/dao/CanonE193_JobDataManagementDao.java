//package com.canon.cusa.custapp.batch.dao;
package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE193_JobDataManagementDao {

	   public static final String CREATE_TICKET= "{call CANON_E193_DATAMGMT_PKG.CREATE_TICKET(?)}";
	   public static final String CREATE_QUICK_TICKET= "{call CANON_E193_DATAMGMT_PKG.CREATE_QUICK_TICKET(?)}";
	   public static final String MAIN= "{call CANON_E193_SLM_CI_CR_BATCH_PKG.MAIN(?,?)}";
	   public static final String UPDATE_CLOSE_STUCK_TICKET= "{call CANON_E193_STK_TKT_UPDATE_PKG.UPDATE_CLOSE_STUCK_TICKET(?,?)}";
	   public static final String MAIN_ASSIGN= "{call CANON_E193_SLM_CI_CR_BATCH_PKG.MAIN_ASSIGN(?,?)}";
	  
	   
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
	   
	   /**
	    * 
	    * @return
	    */
	   
	   public static Object[] createTicket(){
	        clearException();
	        System.out.println("in createTicket ");
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(CREATE_TICKET);
	                if (statement != null) {
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    System.out.println(" Before Execute the Query!! ");
	                    statement.execute();
	                    System.out.println(" After Execute the Query!! ");
	                    return new Object[]{
	                        statement.getObject(1)};
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
	   } // End of Create ticket.
	   
	   
	   
	   /**
	    * 
	    * @return
	    */
	   public static Object[] createQuickTicket(){
	        clearException();
	        System.out.println("in createQuickTicket ");
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(CREATE_QUICK_TICKET);
	                if (statement != null) {
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    System.out.println(" Before Execute the Query!! ");
	                    statement.execute();
	                    System.out.println(" After Execute the Query!! ");
	                    return new Object[]{
	                        statement.getObject(1)};
	                    
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
	   } // End of Create quick ticket.
	   
	   
	   /**
	    * 
	    * @return
	    */
	   public static Object[] createMain(){
	        clearException();
	        System.out.println("in createMain ");
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(MAIN);
	                if (statement != null) {
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                	statement.registerOutParameter(2, OracleTypes.NUMBER);
	                	
	                	System.out.println(" Before Execute the Query!! ");
	                    statement.execute();
	                    /*return new Object[]{
	                        statement.getObject(1)};*/
	                    System.out.println(" After Execute the Query!! ");  
	                    return null;
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
	        /*return null;*/
	   } // End of main Job.
	   
	   
	   
	   /**
	    * 
	    * @return
	    */
	   public static Object[] updateCloseStuckTicket(){
	        clearException();
	        System.out.println("in updateCloseStuckTicket ");
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(UPDATE_CLOSE_STUCK_TICKET);
	                if (statement != null) {
	                	statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                	statement.registerOutParameter(2, OracleTypes.NUMBER);
	                	System.out.println(" Before Execute the Query!! ");
	                    statement.execute();
	                    /*return new Object[]{
	                        statement.getObject(1)};*/
	                    
	                    System.out.println(" After Execute the Query!! ");
	                    return null;
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
	   } // End of update Close Stuck Ticket
	   
	   
	   
	   /**
	    * 
	    * @return
	    */
	   public static Object[] mainAssign(){
	        clearException();
	        System.out.println("in mainAssign ");
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(MAIN_ASSIGN);
	                if (statement != null) {
	                	statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                	statement.registerOutParameter(2, OracleTypes.NUMBER);
	                	System.out.println(" Before Execute the Query!! ");
	                    statement.execute();
	                    /*return new Object[]{
	                        statement.getObject(1)};*/
	                    System.out.println(" After Execute the Query!! ");
	                    return null;
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
	   } // End Main Assign Batch proc
	   
	   
	   
	   
	   
	   
	   
	   /**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
}
