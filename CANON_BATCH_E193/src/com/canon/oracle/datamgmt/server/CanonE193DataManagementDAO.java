package com.canon.oracle.datamgmt.server;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE193DataManagementDAO {
   public static final String CREATE_TICKET= "{call CANON_E193_DATAMGMT_PKG.CREATE_TICKET(?)}";
   public static final String GET_MESSAGE_DETAILS= "{call CANON_E193_DATAMGMT_PKG.GET_MESSAGE_DETAILS(?,?)}";
   public static final String INSERT_DM_EMAIL_STG= "{call CANON_E193_DATAMGMT_PKG.INSERT_DM_EMAIL_STG(?,?,?,?,?,?,?)}";
   public static final String INSERT_E193_DM_ATTCHMENT= "{call CANON_E193_DATAMGMT_PKG.INSERT_E193_DM_ATTCHMENT(?,?,?,?,?)}";

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

   public CanonE193DataManagementDAO(){
   }
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

                    statement.execute();
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
   }
   
   private static String partial(String s){
	   if(s==null) return s;
	   return s.length()<=10? s: s.substring(0,10).replaceAll("[^a-zA-Z]+", " ")+"...";
   }
   
   public static Object[] insertDmEmailStg(String p_from_email,
    String p_subject,
    String p_body,
    String p_message_id,
    Timestamp p_received_date){
        clearException();
        System.out.println("in insertDmEmailStg "+p_from_email+"|"+p_subject+"|"+partial(p_body)+"|"+p_message_id+"|"+p_received_date);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(INSERT_DM_EMAIL_STG);
                if (statement != null) {
                    statement.setObject(1, p_from_email, OracleTypes.VARCHAR);
                    statement.setObject(2, p_subject, OracleTypes.VARCHAR);
                    statement.setObject(3, p_body, OracleTypes.VARCHAR);
                    statement.setObject(4, p_message_id, OracleTypes.VARCHAR);
                    statement.setObject(5, p_received_date, OracleTypes.TIMESTAMP);
                    statement.registerOutParameter(6, OracleTypes.NUMBER);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);

                    statement.execute();
                    return new Object[]{
                        statement.getObject(6)                   
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
   
   public static Object[] getMessageDetails(String p_message_id){
       clearException();
       System.out.println("in getMessageDetails "+p_message_id);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_MESSAGE_DETAILS);
               if (statement != null) {
                   statement.setObject(1, p_message_id, OracleTypes.VARCHAR);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);

                   statement.execute();
                   return new Object[]{
                       cursorToList((ResultSet)statement.getObject(2),MessageInfo.getRowMapper())};
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
   
   /*
   {call CANON_E193_DATAMGMT_PKG.GET_MESSAGE_DETAILS('<OFC2E9DEC3.F06F0794-ON85257F61.00572BB7@cusa.canon.com>',?)}
   (
   FROM_EMAIL VARCHAR2,
       SUBJECT VARCHAR2,
       RECEIVED_DATE DATE
   )
   */

   public static class MessageInfo {
      private String fromEmail;
          private String subject;
          private Timestamp receivedDate;
       
       public MessageInfo(){
       }
       public MessageInfo(String fromEmail, 
               String subject, 
               Timestamp receivedDate){
           this.fromEmail=fromEmail;
           this.subject=subject;
           this.receivedDate=receivedDate;

       }
       public String getFromEmail() {
           return fromEmail;
       }
       public void setFromEmail(String fromEmail) {
           this.fromEmail=fromEmail;
       }        public String getSubject() {
           return subject;
       }
       public void setSubject(String subject) {
           this.subject=subject;
       }        public Timestamp getReceivedDate() {
           return receivedDate;
       }
       public void setReceivedDate(Timestamp receivedDate) {
           this.receivedDate=receivedDate;
       }
       public static RowMapper getRowMapper(){
           return new RowMapper() {
               public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                   return new MessageInfo(
                       rs.getString("FROM_EMAIL"),
                       rs.getString("SUBJECT"),
                       rs.getTimestamp("RECEIVED_DATE")
                   );
               }
           };
       }
       public String toString() {
           return "MessageInfo{" + "fromEmail="+fromEmail+", subject="+subject+", receivedDate="+receivedDate+'}';
       }
   }

   public static Object[] insertE193DmAttchment(BigDecimal p_id,
		    String p_file_type,
		    String p_file_name,
		    InputStream p_attachment){
		        clearException();
		        System.out.println("in insertE193DmAttchment "+p_id+"|"+p_file_type+"|"+p_file_name+"|"+p_attachment);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
		            connection = TransactionScope.getConnection();
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(INSERT_E193_DM_ATTCHMENT);
		                if (statement != null) {
		                    statement.setObject(1, p_id, OracleTypes.NUMBER);
		                    statement.setObject(2, p_file_type, OracleTypes.VARCHAR);
		                    statement.setObject(3, p_file_name, OracleTypes.VARCHAR);
		                    statement.setBinaryStream(4, p_attachment);
		                    statement.registerOutParameter(5, OracleTypes.VARCHAR);

		                    statement.execute();
		                    return new Object[]{
		                        statement.getObject(5)};
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

   
    private static List cursorToList(ResultSet cursor,RowMapper rowMapper){
        List list = new ArrayList();
        try {
            while (cursor.next()) {
                list.add(rowMapper.mapRow(cursor, 0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                cursor.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }



    public interface RowMapper {
        Object mapRow(ResultSet rs,int rowNum)throws SQLException;
    }
}
