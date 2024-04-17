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

    public class CanonE479InvoiceMasterDAO {

   public static final String CLEAR_EXCEL_REF= "{call CANON_E479_INVOICE_MASTER_PKG.CLEAR_EXCEL_REF(?)}";
   public static final String CLEAR_INVOICE_MASTER= "{call CANON_E479_INVOICE_MASTER_PKG.CLEAR_INVOICE_MASTER(?,?,?)}";
   public static final String CLEAR_STAGING_TABLE= "{call CANON_E479_INVOICE_MASTER_PKG.CLEAR_STAGING_TABLE(?,?,?)}";
   public static final String CREATE_EXCEL_FILE= "{call CANON_E479_INVOICE_MASTER_PKG.CREATE_EXCEL_FILE(?,?,?,?,?,?)}";
   public static final String CREATE_FILE= "{call CANON_E479_INVOICE_MASTER_PKG.CREATE_FILE(?)}";
   public static final String DEFAULT_VALUES= "{call CANON_E479_INVOICE_MASTER_PKG.DEFAULT_VALUES(?,?,?,?,?,?,?)}";
   public static final String EON= "{call CANON_E479_INVOICE_MASTER_PKG.EON(?,?,?)}";
   public static final String EVN= "{call CANON_E479_INVOICE_MASTER_PKG.EVN(?,?,?)}";
   public static final String GET_MESSAGE_LINES= "{call CANON_E479_INVOICE_MASTER_PKG.GET_MESSAGE_LINES()}";
   public static final String GET_ORDER_BY= "{?= call CANON_E479_INVOICE_MASTER_PKG.GET_ORDER_BY(?,?,?,?,?)}";
   public static final String GET_SCAN_LINE= "{call CANON_E479_INVOICE_MASTER_PKG.GET_SCAN_LINE(?,?,?,?,?)}";
   public static final String GET_TT= "{?= call CANON_E479_INVOICE_MASTER_PKG.GET_TT(?)}";
   public static final String MARK_PROCESSED= "{call CANON_E479_INVOICE_MASTER_PKG.MARK_PROCESSED(?,?,?)}";
   public static final String MARK_UNPROCESSED= "{call CANON_E479_INVOICE_MASTER_PKG.MARK_UNPROCESSED(?,?,?,?)}";
   public static final String OEY= "{call CANON_E479_INVOICE_MASTER_PKG.OEY(?,?,?)}";
   public static final String OVN= "{call CANON_E479_INVOICE_MASTER_PKG.OVN(?,?,?)}";
   public static final String POSTPROCESS_REF= "{call CANON_E479_INVOICE_MASTER_PKG.POSTPROCESS_REF(?)}";
   public static final String PREPROCESS_IM_RECS= "{call CANON_E479_INVOICE_MASTER_PKG.PREPROCESS_IM_RECS(?)}";
   public static final String PREPROCESS_REF= "{call CANON_E479_INVOICE_MASTER_PKG.PREPROCESS_REF(?)}";
   public static final String PREPROCESS_TMPLTE= "{?= call CANON_E479_INVOICE_MASTER_PKG.PREPROCESS_TMPLTE(?,?,?,?)}";
   public static final String PROCESS_BILL_TO= "{call CANON_E479_INVOICE_MASTER_PKG.PROCESS_BILL_TO(?)}";
   public static final String PROCESS_CUSTOMER= "{call CANON_E479_INVOICE_MASTER_PKG.PROCESS_CUSTOMER(?)}";
   public static final String PROCESS_CUSTOMER_GROUP= "{call CANON_E479_INVOICE_MASTER_PKG.PROCESS_CUSTOMER_GROUP(?)}";
   public static final String PROCESS_NEW= "{?= call CANON_E479_INVOICE_MASTER_PKG.PROCESS_NEW(?)}";
   public static final String PROCESS_PARENT_CUSTOMER= "{call CANON_E479_INVOICE_MASTER_PKG.PROCESS_PARENT_CUSTOMER(?)}";
   public static final String ROLL_BACK= "{call CANON_E479_INVOICE_MASTER_PKG.ROLL_BACK()}";
   public static final String SET_INV_ATTRIBUTES= "{call CANON_E479_INVOICE_MASTER_PKG.SET_INV_ATTRIBUTES(?)}";
   public static final String SET_VARIABLES= "{call CANON_E479_INVOICE_MASTER_PKG.SET_VARIABLES(?,?,?,?,?,?)}";
   public static final String UPDATE_CONTROL_REMITTANCE= "{call CANON_E479_INVOICE_MASTER_PKG.UPDATE_CONTROL_REMITTANCE(?,?)}";
   public static final String UPD_CHECK_ATTRIBUTES= "{call CANON_E479_INVOICE_MASTER_PKG.UPD_CHECK_ATTRIBUTES(?)}";
   public static final String VALIDATE_INVOICE_AMTS= "{?= call CANON_E479_INVOICE_MASTER_PKG.VALIDATE_INVOICE_AMTS(?,?,?)}";
   public static final String VEN= "{call CANON_E479_INVOICE_MASTER_PKG.VEN(?,?,?)}";

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

   public CanonE479InvoiceMasterDAO(){
   }

   public static Object[] clearExcelRef(BigDecimal p_ref_id){
        clearException();
        System.out.println("in clearExcelRef "+p_ref_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CLEAR_EXCEL_REF);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
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

   public static Object[] clearInvoiceMaster(BigDecimal p_ref_id,
    String p_ref_level,
    String p_ref_value){
        clearException();
        System.out.println("in clearInvoiceMaster "+p_ref_id+"|"+p_ref_level+"|"+p_ref_value);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CLEAR_INVOICE_MASTER);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_ref_level, OracleTypes.VARCHAR);
                    statement.setObject(3, p_ref_value, OracleTypes.VARCHAR);
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

   public static Object[] clearStagingTable(BigDecimal p_ref_id,
    String p_ref_level,
    String p_ref_value){
        clearException();
        System.out.println("in clearStagingTable "+p_ref_id+"|"+p_ref_level+"|"+p_ref_value);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CLEAR_STAGING_TABLE);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_ref_level, OracleTypes.VARCHAR);
                    statement.setObject(3, p_ref_value, OracleTypes.VARCHAR);
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

   public static Object[] createExcelFile(String p_customer_group,
    String p_parent_customer,
    String p_customer,
    String p_bill_to_location,
    Timestamp p_start_date,
    Timestamp p_end_date){
        clearException();
        System.out.println("in createExcelFile "+p_customer_group+"|"+p_parent_customer+"|"+p_customer+"|"+p_bill_to_location+"|"+p_start_date+"|"+p_end_date);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CREATE_EXCEL_FILE);
                if (statement != null) {
                    statement.setObject(1, p_customer_group, OracleTypes.VARCHAR);
                    statement.setObject(2, p_parent_customer, OracleTypes.VARCHAR);
                    statement.setObject(3, p_customer, OracleTypes.VARCHAR);
                    statement.setObject(4, p_bill_to_location, OracleTypes.VARCHAR);
                    statement.setObject(5, p_start_date, OracleTypes.TIMESTAMP);
                    statement.setObject(6, p_end_date, OracleTypes.TIMESTAMP);
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

   public static Object[] createFile(BigDecimal p_ref_id){
        clearException();
        System.out.println("in createFile "+p_ref_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CREATE_FILE);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
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

   public static Object[] defaultValues(BigDecimal p_ref_id,
    String p_procedure_name,
    String p_level,
    String p_level_value,
    String p_customer_name,
    String p_bill_number,
    BigDecimal g_total_amount_due){
        clearException();
        System.out.println("in defaultValues "+p_ref_id+"|"+p_procedure_name+"|"+p_level+"|"+p_level_value+"|"+p_customer_name+"|"+p_bill_number+"|"+g_total_amount_due);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(DEFAULT_VALUES);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_procedure_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_level, OracleTypes.VARCHAR);
                    statement.setObject(4, p_level_value, OracleTypes.VARCHAR);
                    statement.setObject(5, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(6, p_bill_number, OracleTypes.VARCHAR);
                    statement.setObject(7, g_total_amount_due, OracleTypes.NUMBER);
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

   public static Object[] eon(BigDecimal p_hdrid,
    BigDecimal p_ref_id,
    String p_mode){
        clearException();
        System.out.println("in eon "+p_hdrid+"|"+p_ref_id+"|"+p_mode);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(EON);
                if (statement != null) {
                    statement.setObject(1, p_hdrid, OracleTypes.NUMBER);
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_mode, OracleTypes.VARCHAR);
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

   public static Object[] evn(BigDecimal p_hdrid,
    BigDecimal p_ref_id,
    String p_mode){
        clearException();
        System.out.println("in evn "+p_hdrid+"|"+p_ref_id+"|"+p_mode);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(EVN);
                if (statement != null) {
                    statement.setObject(1, p_hdrid, OracleTypes.NUMBER);
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_mode, OracleTypes.VARCHAR);
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

   public static Object[] getMessageLines(){
        clearException();
        System.out.println("in getMessageLines ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_MESSAGE_LINES);
                if (statement != null) {
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

   public static Object[] getOrderBy(BigDecimal p_header_id,
    BigDecimal p_view_id,
    String p_sort_pref1,
    String p_sort_pref2,
    String p_sort_pref3){
        clearException();
        System.out.println("in getOrderBy "+p_header_id+"|"+p_view_id+"|"+p_sort_pref1+"|"+p_sort_pref2+"|"+p_sort_pref3);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_ORDER_BY);
                if (statement != null) {
                    statement.setObject(2, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_view_id, OracleTypes.NUMBER);
                    statement.setObject(4, p_sort_pref1, OracleTypes.VARCHAR);
                    statement.setObject(5, p_sort_pref2, OracleTypes.VARCHAR);
                    statement.setObject(6, p_sort_pref3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1)};
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

   public static Object[] getScanLine(String p_cons_inv_flag,
    String p_cust_num,
    String p_inv_num,
    BigDecimal p_inv_amt){
        clearException();
        System.out.println("in getScanLine "+p_cons_inv_flag+"|"+p_cust_num+"|"+p_inv_num+"|"+p_inv_amt);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SCAN_LINE);
                if (statement != null) {
                    statement.setObject(1, p_cons_inv_flag, OracleTypes.VARCHAR);
                    statement.setObject(2, p_cust_num, OracleTypes.VARCHAR);
                    statement.setObject(3, p_inv_num, OracleTypes.VARCHAR);
                    statement.setObject(4, p_inv_amt, OracleTypes.NUMBER);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(5)};
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

   public static Object[] getTt(BigDecimal p_hdrid){
        clearException();
        System.out.println("in getTt "+p_hdrid);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_TT);
                if (statement != null) {
                    statement.setObject(2, p_hdrid, OracleTypes.NUMBER);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1)};
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

   public static Object[] markProcessed(BigDecimal p_ref_id,
    String p_level,
    String p_level_value){
        clearException();
        System.out.println("in markProcessed "+p_ref_id+"|"+p_level+"|"+p_level_value);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(MARK_PROCESSED);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_level, OracleTypes.VARCHAR);
                    statement.setObject(3, p_level_value, OracleTypes.VARCHAR);
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

   public static Object[] markUnprocessed(BigDecimal p_ref_id,
    String p_level,
    String p_level_value,
    String p_mode){
        clearException();
        System.out.println("in markUnprocessed "+p_ref_id+"|"+p_level+"|"+p_level_value+"|"+p_mode);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(MARK_UNPROCESSED);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_level, OracleTypes.VARCHAR);
                    statement.setObject(3, p_level_value, OracleTypes.VARCHAR);
                    statement.setObject(4, p_mode, OracleTypes.VARCHAR);
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

   public static Object[] oey(BigDecimal p_hdrid,
    BigDecimal p_ref_id,
    String p_mode){
        clearException();
        System.out.println("in oey "+p_hdrid+"|"+p_ref_id+"|"+p_mode);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(OEY);
                if (statement != null) {
                    statement.setObject(1, p_hdrid, OracleTypes.NUMBER);
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_mode, OracleTypes.VARCHAR);
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

   public static Object[] ovn(BigDecimal p_hdrid,
    BigDecimal p_ref_id,
    String p_mode){
        clearException();
        System.out.println("in ovn "+p_hdrid+"|"+p_ref_id+"|"+p_mode);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(OVN);
                if (statement != null) {
                    statement.setObject(1, p_hdrid, OracleTypes.NUMBER);
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_mode, OracleTypes.VARCHAR);
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

   public static Object[] postprocessRef(BigDecimal p_ref_id){
        clearException();
        System.out.println("in postprocessRef "+p_ref_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(POSTPROCESS_REF);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
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

   public static Object[] preprocessImRecs(BigDecimal p_header_id){
        clearException();
        System.out.println("in preprocessImRecs "+p_header_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PREPROCESS_IM_RECS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
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

   public static Object[] preprocessRef(BigDecimal p_ref_id){
        clearException();
        System.out.println("in preprocessRef "+p_ref_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PREPROCESS_REF);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
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

   public static Object[] preprocessTmplte(BigDecimal p_ref_id,
    BigDecimal p_hdr_id,
    String p_level,
    String p_level_value){
        clearException();
        System.out.println("in preprocessTmplte "+p_ref_id+"|"+p_hdr_id+"|"+p_level+"|"+p_level_value);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PREPROCESS_TMPLTE);
                if (statement != null) {
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_hdr_id, OracleTypes.NUMBER);
                    statement.setObject(4, p_level, OracleTypes.VARCHAR);
                    statement.setObject(5, p_level_value, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.BOOLEAN);
                    statement.execute();
                    return new Object[]{statement.getObject(1)};
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

   public static Object[] processBillTo(String p_bill_to_location){
        clearException();
        System.out.println("in processBillTo "+p_bill_to_location);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROCESS_BILL_TO);
                if (statement != null) {
                    statement.setObject(1, p_bill_to_location, OracleTypes.VARCHAR);
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

   public static Object[] processCustomer(String p_customer){
        clearException();
        System.out.println("in processCustomer "+p_customer);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROCESS_CUSTOMER);
                if (statement != null) {
                    statement.setObject(1, p_customer, OracleTypes.VARCHAR);
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

   public static Object[] processCustomerGroup(String p_customer_group){
        clearException();
        System.out.println("in processCustomerGroup "+p_customer_group);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROCESS_CUSTOMER_GROUP);
                if (statement != null) {
                    statement.setObject(1, p_customer_group, OracleTypes.VARCHAR);
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

   public static Object[] processNew(BigDecimal p_header_id){
        clearException();
        System.out.println("in processNew "+p_header_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROCESS_NEW);
                if (statement != null) {
                    statement.setObject(2, p_header_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(1, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(1)};
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

   public static Object[] processParentCustomer(String p_parent_customer){
        clearException();
        System.out.println("in processParentCustomer "+p_parent_customer);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PROCESS_PARENT_CUSTOMER);
                if (statement != null) {
                    statement.setObject(1, p_parent_customer, OracleTypes.VARCHAR);
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

   public static Object[] rollBack(){
        clearException();
        System.out.println("in rollBack ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ROLL_BACK);
                if (statement != null) {
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

   public static Object[] setInvAttributes(String p_invoice_number){
        clearException();
        System.out.println("in setInvAttributes "+p_invoice_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SET_INV_ATTRIBUTES);
                if (statement != null) {
                    statement.setObject(1, p_invoice_number, OracleTypes.VARCHAR);
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

   public static Object[] setVariables(String p_bill_number,
    BigDecimal p_ref_id,
    BigDecimal p_seq_id,
    String p_invoice_number,
    BigDecimal p_inv_amt,
    String p_neg_rd){
        clearException();
        System.out.println("in setVariables "+p_bill_number+"|"+p_ref_id+"|"+p_seq_id+"|"+p_invoice_number+"|"+p_inv_amt+"|"+p_neg_rd);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SET_VARIABLES);
                if (statement != null) {
                    statement.setObject(1, p_bill_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_seq_id, OracleTypes.NUMBER);
                    statement.setObject(4, p_invoice_number, OracleTypes.VARCHAR);
                    statement.setObject(5, p_inv_amt, OracleTypes.NUMBER);
                    statement.setObject(6, p_neg_rd, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(6)};
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

   public static Object[] updateControlRemittance(BigDecimal p_ref_id,
    BigDecimal p_seq_id){
        clearException();
        System.out.println("in updateControlRemittance "+p_ref_id+"|"+p_seq_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPDATE_CONTROL_REMITTANCE);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_seq_id, OracleTypes.NUMBER);
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

   public static Object[] updCheckAttributes(BigDecimal p_ref_id){
        clearException();
        System.out.println("in updCheckAttributes "+p_ref_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPD_CHECK_ATTRIBUTES);
                if (statement != null) {
                    statement.setObject(1, p_ref_id, OracleTypes.NUMBER);
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

   public static Object[] validateInvoiceAmts(BigDecimal p_ref_id,
    String p_level,
    String p_level_value){
        clearException();
        System.out.println("in validateInvoiceAmts "+p_ref_id+"|"+p_level+"|"+p_level_value);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(VALIDATE_INVOICE_AMTS);
                if (statement != null) {
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_level, OracleTypes.VARCHAR);
                    statement.setObject(4, p_level_value, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(1)};
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

   public static Object[] ven(BigDecimal p_hdrid,
    BigDecimal p_ref_id,
    String p_mode){
        clearException();
        System.out.println("in ven "+p_hdrid+"|"+p_ref_id+"|"+p_mode);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(VEN);
                if (statement != null) {
                    statement.setObject(1, p_hdrid, OracleTypes.NUMBER);
                    statement.setObject(2, p_ref_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_mode, OracleTypes.VARCHAR);
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
