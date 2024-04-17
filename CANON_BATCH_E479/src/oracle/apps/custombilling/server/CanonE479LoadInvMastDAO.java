package oracle.apps.custombilling.server;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;
import java.math.BigDecimal;
import java.sql.Timestamp;
import canon.apps.common.CanonRowMapper;
import oracle.apps.jtf.aom.transaction.TransactionScope;

    public class CanonE479LoadInvMastDAO {

   public static final String LOAD_INV_MAST= "{call CANON_E479_LOAD_INV_MAST_PKG.LOAD_INV_MAST(?,?,?,?,?,?)}";

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

   public CanonE479LoadInvMastDAO(){
   }

   public static Object[] loadInvMast(String p_customer_group,
    String p_parent_customer,
    String p_customer,
    String p_bill_to_location,
    Timestamp p_from_date,
    Timestamp p_to_date){
        clearException();
        System.out.println("in loadInvMast "+p_customer_group+"|"+p_parent_customer+"|"+p_customer+"|"+p_bill_to_location+"|"+p_from_date+"|"+p_to_date);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(LOAD_INV_MAST);
                if (statement != null) {
                    statement.setObject(1, p_customer_group, OracleTypes.VARCHAR);
                    statement.setObject(2, p_parent_customer, OracleTypes.VARCHAR);
                    statement.setObject(3, p_customer, OracleTypes.VARCHAR);
                    statement.setObject(4, p_bill_to_location, OracleTypes.VARCHAR);
                    statement.setObject(5, p_from_date, OracleTypes.TIMESTAMP);
                    statement.setObject(6, p_to_date, OracleTypes.TIMESTAMP);
                    statement.execute();
                    return new Object[]{};
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


    private static List cursorToList(ResultSet cursor,CanonRowMapper rowMapper){
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


}
