package oracle.apps.e580.itt.workbench;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import oracle.jdbc.OracleTypes; 
import oracle.sql.ARRAY;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import java.math.BigDecimal;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.canon.common.CanonCommonUtil;

import canon.apps.common.CanonConstants;
import business.parts.NPZC104001PMsg;
import oracle.apps.jtf.aom.transaction.TransactionScope;

public class CanonE580ITTWorkbenchDAO {

    public static final String ITT_LINE_DETAILS = "{call CANON_E580_ITT_UTIL_PKG.ITT_LINE_DETAILS(?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_HEADER_DETAILS= "{call CANON_E580_ITT_UTIL_PKG.ITT_HEADER_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_CUST_ACCOUNT_LOV = "{call CANON_E580_ITT_UTIL_PKG.ITT_CUST_ACCOUNT_LOV(?,?,?)}";
    public static final String ITT_STATUS_LOV = "{call CANON_E580_ITT_UTIL_PKG.ITT_STATUS_LOV(?)}";
    public static final String GET_SERVER_INITIAL_PATH = "{call CANON_E479_INV_PKG.GET_SERVER_INITIAL_PATH(?)}";
    public static final String ITT_CMAP_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_CMAP_LOV(?)}";
    public static final String ITT_PRODUCT_SOURCE_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_PRODUCT_SOURCE_LOV(?)}";
    public static final String ITT_ORDER_PROCESSOR_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_ORDER_PROCESSOR_LOV(?,?)}";
    public static final String ITT_DEALER_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_LOV(?,?)}";
	public static final String ITT_DEALER_CODE_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_CODE_LOV(?,?)}";
    public static final String ITT_HEADER_UPDATE= "{call CANON_E580_ITT_UTIL_PKG.ITT_HEADER_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_GOODS_LINE_UPDATE= "{call CANON_E580_ITT_UTIL_PKG.ITT_GOODS_LINE_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_EXPENSE_LINE_UPDATE= "{call CANON_E580_ITT_UTIL_PKG.ITT_EXPENSE_LINE_UPDATE(?,?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_EXPENSE_LINE_INSERT= "{call CANON_E580_ITT_UTIL_PKG.ITT_EXPENSE_LINE_INSERT(?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_CONTRACT_TYPE_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_CONTRACT_TYPE_LOV(?)}";
    public static final String ITT_PLAN_TYPE_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_PLAN_TYPE_LOV(?)}";
    public static final String ITT_BILL_CYCLE_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_BILL_CYCLE_LOV(?)}";
    public static final String ITT_PRICING_DETAILS= "{call CANON_E580_ITT_UTIL_PKG.ITT_PRICING_DETAILS(?,?,?)}";
    public static final String ITT_UPDATE_PRICING= "{call CANON_E580_ITT_UTIL_PKG.ITT_UPDATE_PRICING(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_NOTES_INSERT= "{call CANON_E580_ITT_UTIL_PKG.ITT_NOTES_INSERT(?,?,?,?,?)}";
    public static final String ITT_NOTES_DETAILS= "{call CANON_E580_ITT_UTIL_PKG.ITT_NOTES_DETAILS(?,?,?,?,?,?,?)}";
    public static final String ITT_LINE_TYPES= "{call CANON_E580_ITT_UTIL_PKG.ITT_LINE_TYPES(?)}";
    public static final String ITT_TIMESTAMP_UPDATE= "{call CANON_E580_ITT_UTIL_PKG.ITT_TIMESTAMP_UPDATE(?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_TIMESTAMP_DETAILS= "{call CANON_E580_ITT_UTIL_PKG.ITT_TIMESTAMP_DETAILS(?,?)}";    
    public static final String ITT_SALES_ZONE_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_SALES_ZONE_LOV(?)}";
    public static final String ITT_SALES_BRANCH_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_SALES_BRANCH_LOV(?,?)}";
    public static final String ITT_SALESREP_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_SALESREP_LOV(?,?)}";    
    public static final String ITT_CREATE_PO_PDF= "{call CANON_E580_ITT_UTIL_PKG.ITT_CREATE_PO_PDF(?,?,?,?,?,?,?,?,?,?)}";
    public static final String ITT_MARKVIEW_ATTACHED_DOCS= "{call CANON_E580_ITT_UTIL_PKG.ITT_MARKVIEW_ATTACHED_DOCS(?,?,?)}";
    public static final String CREATE_PO_PRC= "{call CANON_E580_AUTOCREATE_PO_PKG.ELIGIBLE_CREATE_PO_LINES(?,?,?,?,?,?)}";
    public static final String ITT_MAIL_INVOICES_TO_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_MAIL_INVOICES_TO_LOV(?)}";
    public static final String ITT_EDIT_MODE_CHECK= "{call CANON_E580_ITT_UTIL_PKG.ITT_EDIT_MODE_CHECK(?,?)}";
    public static final String ITT_NOTES_UPDATE= "{call CANON_E580_ITT_UTIL_PKG.ITT_NOTES_UPDATE(?,?,?,?,?)}";
    public static final String ELIGIBLE_PO_LINES= "{call CANON_E580_PO_RECEIPTS_PKG.ELIGIBLE_PO_LINES(?,?,?)}";
	//Added by Madhusudan Duna
	public static final String ELIGIBLE_EXP_PO_LINES= "{call CANON_E580_PO_RECEIPTS_PKG.ELIGIBLE_EXPENSE_PO_LINES(?,?,?)}";
	public static final String INSERT_EXP_RECEIPT_PRC= "{call CANON_E580_PO_RECEIPTS_PKG.INSERT_EXPENSE_RECEIPT_PRC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	//Added by Madhusudan Duna
    public static final String INSERT_RECEIPT_PRC= "{call CANON_E580_PO_RECEIPTS_PKG.INSERT_RECEIPT_PRC(?,?,?,?)}";
    
    public static final String CANON_CREATE_RECEIPT_PRC= "{call CANON_E580_PO_RECEIPTS_PKG.CANON_CREATE_RECEIPT_PRC(?,?,?,?,?)}";
    public static final String ITT_SUB_INV_LOV= "{call CANON_E580_ITT_UTIL_PKG.ITT_SUB_INV_LOV(?,?)}";
    public static final String ITT_LINES_ON_HOLD= "{call CANON_E580_ITT_UTIL_PKG.ITT_LINES_ON_HOLD(?,?,?)}";
    public static final String RELEASE_HOLD= "{call CANON_E580_ITT_PROCESS_PKG.RELEASE_HOLD(?,?,?,?,?)}";
    public static final String ITT_DEALER_SHIP_TO_CNA_CODE= "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_SHIP_TO_CNA_CODE(?,?)}";
	public static final String ITT_DEALER_SHIP_TO_CNA_DNAME= "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_SHIP_TO_CNA_DNAME(?,?)}";
    public static final String ITT_SCHDL_DELIV_DT_REASON_CODE= "{call CANON_E580_ITT_UTIL_PKG.ITT_SCHDL_DELIV_DT_REASON_CODE(?)}";
    public static final String ITT_DELIV_DT_RESCHDL_HISTORY= "{call CANON_E580_ITT_UTIL_PKG.ITT_DELIV_DT_RESCHDL_HISTORY(?,?,?)}";
    public static final String RECEIVE_PO_CHECK= "{call CANON_E580_PO_RECEIPTS_PKG.RECEIVE_PO_CHECK(?,?,?)}";
    public static final String SERIAL_NUMBER_CHECK= "{call CANON_E580_PO_RECEIPTS_PKG.SERIAL_NUMBER_CHECK(?,?,?,?)}";
    public static final String UPDATE_PO_ITT="{call CANON_E580_AUTOCREATE_PO_PKG.UPDATE_PO_ITT(?,?,?,?)}";
    public static final String DEALER_INSTALL_CONFIRM= "{call CANON_E580_ITT_PROCESS_PKG.DEALER_INSTALL_CONFIRM(?,?,?,?,?,?,?)}";
    public static final String UPDATE_POR_ERROR="{call CANON_E580_PO_RECEIPTS_PKG.UPDATE_POR_ERROR(?,?,?,?,?)}";
    public static final String GET_EXPENSE_ITEM= "{call CANON_E580_ITT_UTIL_PKG.GET_EXPENSE_ITEM(?)}";
    public static final String GET_APPEND_PO_NUMBERS= "{call CANON_E580_AUTOCREATE_PO_PKG.GET_APPEND_PO_NUMBERS(?,?,?,?,?,?)}";
    public static final String DERIVE_APPEND_PO_LINES= "{call CANON_E580_AUTOCREATE_PO_PKG.DERIVE_APPEND_PO_LINES(?,?,?,?,?,?,?)}";
	//Added ITG#690711
	public static final String ITT_DEALER_COMP_INSERT= "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_COMP_INSERT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String ITT_DEALER_COMP_DETAILS= "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_COMP_DETAILS(?,?,?,?,?,?,?)}";
    public static final String ITT_DEALER_TOTAL_COMP = "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_TOTAL_COMP(?,?)}";
    public static final String ITT_DLRCOMP_EXTRACT = "{call CANON_E580_ITT_UTIL_PKG.ITT_DLRCOMP_EXTRACT(?,?)}";
	public static final String DEALER_CONFIRM= "{call CANON_E580_ITT_PROCESS_PKG.RELEASE_GSD_HOLD(?,?)}";
	public static final String DEALER_COMP_REFRESH= "{call CANON_E580_ITT_PROCESS_PKG.REFRESH_COMPS(?,?,?,?)}";	
	public static final String COMP_OVERRIDE_WF= "{call CANON_W46_GSDCOMP_WF_APPRV_PKG.START_APPROVAL_WORKFLOW(?,?,?)}";	
	public static final String ITT_GSD_ORDERTYPE_CHECK= "{call CANON_E580_ITT_UTIL_PKG.ITT_GSD_ORDERTYPE_CHECK(?,?)}";
	public static final String ITT_GSD_LOCAL_SHIP_CHECK= "{call CANON_E580_ITT_UTIL_PKG.ITT_GSD_LOCAL_SHIP_CHECK(?,?)}";
	public static final String UPDATE_DECLINE_MAINT= "{call CANON_E580_ITT_UTIL_PKG.UPDATE_DECLINE_MAINT(?,?,?,?)}";
    public static final String ITT_DEALER_COMP_CHK = "{call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_COMP_CHK(?,?,?)}"; 
    public static final String ITT_CREATE_GSD_PDF= "{call CANON_E580_ITT_UTIL_PKG.ITT_CREATE_GSD_PDF(?,?,?,?,?)}";	
    
    public CanonE580ITTWorkbenchDAO() {
    }

   public static Object[] ittLineDetails(String p_itt_number,
    String p_so_number,
    BigDecimal p_from_record,
    BigDecimal p_to_record,
    String p_order_by,
    String p_asc_desc){
        System.out.println("in ittLineDetails "+p_itt_number+"|"+p_so_number+"|"+p_from_record+"|"+p_to_record+"|"+p_order_by+"|"+p_asc_desc);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_LINE_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_from_record, OracleTypes.NUMBER);
                    statement.setObject(4, p_to_record, OracleTypes.NUMBER);
                    statement.setObject(5, p_order_by, OracleTypes.VARCHAR);
                    statement.setObject(6, p_asc_desc, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.CURSOR);
                    statement.registerOutParameter(8, OracleTypes.CURSOR);
                    statement.registerOutParameter(9, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(7),LineDetailSummary.getRowMapper())
                        ,cursorToList((ResultSet)statement.getObject(8),LineDetailItem.getRowMapper())
                        ,statement.getObject(9)};
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
        return null;
   }

   public static Object[] ittHeaderDetails(String p_itt_number,
    String p_so_number,
    String p_customer_name,
    String p_ship_to_account,
    String p_itt_status,
    String p_itt_processor,
    String p_sales_rep,
    String p_sales_zone,
    String p_sales_branch,
    String p_cusa_po_number,
    String p_transaction_type,
    String p_dealer_name,
    Timestamp p_delivery_date_from,
    Timestamp p_delivery_date_to,
    String p_include_closed_itt,
    BigDecimal p_from_record,
    BigDecimal p_to_record,
    String p_order_by,
    String p_asc_desc){
        System.out.println("in ittHeaderDetails "+p_itt_number+"|"+p_so_number+"|"+p_customer_name+"|"+p_ship_to_account+"|"+p_itt_status+"|"+p_itt_processor+"|"+p_sales_rep+"|"+p_sales_zone+"|"+p_sales_branch+"|"+p_cusa_po_number+"|"+p_transaction_type+"|"+p_dealer_name+"|"+p_delivery_date_from+"|"+p_delivery_date_to+"|"+p_include_closed_itt+"|"+p_from_record+"|"+p_to_record+"|"+p_order_by+"|"+p_asc_desc);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {
            	
                statement = (CallableStatement) connection.prepareCall(ITT_HEADER_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_ship_to_account, OracleTypes.VARCHAR);
                    statement.setObject(5, p_itt_status, OracleTypes.VARCHAR);
                    statement.setObject(6, p_itt_processor, OracleTypes.VARCHAR);
                    statement.setObject(7, p_sales_rep, OracleTypes.VARCHAR);
                    statement.setObject(8, p_sales_zone, OracleTypes.VARCHAR);
                    statement.setObject(9, p_sales_branch, OracleTypes.VARCHAR);
                    statement.setObject(10, p_cusa_po_number, OracleTypes.VARCHAR);
                    statement.setObject(11, p_transaction_type, OracleTypes.VARCHAR);
                    statement.setObject(12, p_dealer_name, OracleTypes.VARCHAR);
                    statement.setObject(13, p_delivery_date_from, OracleTypes.TIMESTAMP);
                    statement.setObject(14, p_delivery_date_to, OracleTypes.TIMESTAMP);
                    statement.setObject(15, p_include_closed_itt, OracleTypes.VARCHAR);
                    statement.setObject(16, p_from_record, OracleTypes.NUMBER);
                    statement.setObject(17, p_to_record, OracleTypes.NUMBER);
                    statement.setObject(18, p_order_by, OracleTypes.VARCHAR);
                    statement.setObject(19, p_asc_desc, OracleTypes.VARCHAR);
                    statement.registerOutParameter(20, OracleTypes.CURSOR);
                    statement.registerOutParameter(21, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(20),HeaderDetail.getRowMapper())
                        ,statement.getObject(21)};
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
        return null;
   }
   
    public static Object[] ittCustAccountLov(String p_cust_name,
            String p_cust_number) {
        System.out.println("in ittCustAccountLov " + p_cust_name + "|" + p_cust_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_CUST_ACCOUNT_LOV);
                if (statement != null) {
                    statement.setObject(1, p_cust_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_cust_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(3), CustomerAccountInfo.getRowMapper())};
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
        return null;
    }

    public static Object[] ittStatusLov() {
        System.out.println("in ittStatusLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_STATUS_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(1),
                        new CanonE580RowMapper() {
                            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                return rs.getString(1);
                            }
                        })};
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
        return null;
    }

    public static String getFullName(String userName) throws Exception {
        String fullName = null;
        StringBuffer qry = new StringBuffer(20);
        Statement statement = null;
        ResultSet resultSet = null;
        Connection oracleconnection = null;


        qry.append(" SELECT UPPER(papf.first_name || ' ' || papf.last_name) full_name \n");
        qry.append("   FROM per_all_people_f papf \n");
        qry.append("  WHERE 1=1\n");

        if (userName != null && !("".equals(userName))) {
            qry.append(" AND upper(papf.employee_number) = '");
            qry.append(userName.toUpperCase());
            qry.append("'\n");
        }


        //logMessage(qry.toString());
        try {
            oracleconnection = (Connection) TransactionScope.getConnection();
            if (oracleconnection != null) {
                statement = oracleconnection.createStatement();
                if (statement != null) {
                    resultSet = statement.executeQuery(qry.toString());


                    if (resultSet.next()) {
                        fullName = resultSet.getString(1);

                    } else {

                        logErrorMessage("dbStatus: WHILE RETRIEVING THE RESULTSET");
                    }

                } else {
                    logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");
                }
            } else {
                logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");
            }
        } catch (SQLException sqlExp) {
            logErrorMessage("SQLexception: " + sqlExp);
            throw sqlExp;
        } catch (Exception exception) {
            logErrorMessage("exception: " + exception);
        } finally {
            closeResources(resultSet);
            closeResources(statement);
            try {
                if (oracleconnection != null) {
                    TransactionScope.releaseConnection(oracleconnection);
                }
            } catch (Exception eExp) {
            }
        }
        return fullName;
    }
   public static Object[] ittProductSourceLov(){
        System.out.println("in ittProductSourceLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_PRODUCT_SOURCE_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }

   public static Object[] ittOrderProcessorLov(String p_processor_name){
        System.out.println("in ittOrderProcessorLov "+p_processor_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_ORDER_PROCESSOR_LOV);
                if (statement != null) {
                    statement.setObject(1, p_processor_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ITTOrderProcessorInfo.getRowMapper())};
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
        return null;
   }
   
    private static List cursorToList(ResultSet cursor, CanonE580RowMapper rowMapper) {
        List list = new ArrayList();
        if(cursor!=null){
	        try {
	            while (cursor.next()) {
	                list.add(rowMapper.mapRow(cursor, 0));
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                cursor.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
        }
        return list;
    }

    // Database name CANDEV
    /*
     {call CANON_E580_ITT_UTIL_PKG.ITT_CUST_ACCOUNT_LOV('CARLETON',NULL,?)}
     (
     SHIP_TO_CUSTOMER VARCHAR2,
     ACCOUNT_NUMBER VARCHAR2
     )
     */
    public static class CustomerAccountInfo {

        private String shipToCustomer;
        private String accountNumber;

        public CustomerAccountInfo() {
        }

        public CustomerAccountInfo(String shipToCustomer,
                String accountNumber) {
            this.shipToCustomer = shipToCustomer;
            this.accountNumber = accountNumber;
        }

        public String getShipToCustomer() {
            return shipToCustomer;
        }

        public void setShipToCustomer(String shipToCustomer) {
            this.shipToCustomer = shipToCustomer;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public static CanonE580RowMapper getRowMapper() {
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new CustomerAccountInfo(
                            rs.getString("SHIP_TO_CUSTOMER"),
                            rs.getString("ACCOUNT_NUMBER"));
                }
            };
        }

        public String toString() {
            return "CustomerAccountInfo{" + "shipToCustomer=" + shipToCustomer + ", accountNumber=" + accountNumber + '}';
        }
    }

    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_HEADER_DETAILS(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,?,?)}
    (
    ROW_NUM NUMBER,
    ITT_STATUS VARCHAR2,
    ITT_ADMIN_OWNER VARCHAR2,
    ORDER_NUMBER VARCHAR2,
    ITT_NUMBER VARCHAR2,
    PARTY_NAME VARCHAR2,
    CITY VARCHAR2,
    STATE VARCHAR2,
    POSTAL_CODE VARCHAR2,
    CUST_SHIP_TO_ADDRESS VARCHAR2,
    NAME VARCHAR2,
    SALES_ZONE VARCHAR2,
    CSA_SALES_BRANCH VARCHAR2,
    DEALER VARCHAR2,
    DEALER_WHSE_CODE VARCHAR2,
    DEALER_ADDRESS_LINE1 VARCHAR2,
    DEALER_ADDRESS_LINE2 VARCHAR2,
    DEALER_ADDRESS_LINE3 VARCHAR2,
    DEALER_CITY VARCHAR2,
    DEALER_STATE VARCHAR2,
    DEALER_ZIP VARCHAR2,
    DEALER_COUNTRY VARCHAR2,
    DEALER_CONTACT VARCHAR2,
    DEALER_CONTACT_NUMBER VARCHAR2,
    DEALER_EMAIL VARCHAR2,
    CMAP_YES_NO VARCHAR2,
    PRODUCT_SOURCED_BY VARCHAR2,
    CUSA_PO_NUMBER VARCHAR2,
    SCHEDULED_DELIVERY_DATE DATE,
    LAST_UPDATE_DATE DATE,
    MAIL_INVOICES_TO VARCHAR2
    )
    */
    public static class HeaderDetail {
       private BigDecimal rowNum;
       private String ittStatus;
       private String ittAdminOwner;
       private String orderNumber;
       private String ittNumber;
       private String partyName;
       private String city;
       private String state;
       private String postalCode;
       private String custShipToAddress;
       private String name;
       private String salesZone;
       private String csaSalesBranch;
       private String dealer;
       private String dealerWhseCode;
       private String dealerAddressLine1;
       private String dealerAddressLine2;
       private String dealerAddressLine3;
       private String dealerCity;
       private String dealerState;
       private String dealerZip;
       private String dealerCountry;
       private String dealerContact;
       private String dealerContactNumber;
       private String dealerEmail;
       private String cmapYesNo;
       private String productSourcedBy;
       private String cusaPoNumber;
       private Timestamp scheduledDeliveryDate;
       private Timestamp lastUpdateDate;
       private String mailInvoicesTo;

        public HeaderDetail(){
        }
        public HeaderDetail(BigDecimal rowNum, 
                String ittStatus, 
                String ittAdminOwner, 
                String orderNumber, 
                String ittNumber, 
                String partyName, 
                String city, 
                String state, 
                String postalCode, 
                String custShipToAddress, 
                String name, 
                String salesZone, 
                String csaSalesBranch, 
                String dealer, 
                String dealerWhseCode, 
                String dealerAddressLine1, 
                String dealerAddressLine2, 
                String dealerAddressLine3, 
                String dealerCity, 
                String dealerState, 
                String dealerZip, 
                String dealerCountry, 
                String dealerContact, 
                String dealerContactNumber, 
                String dealerEmail, 
                String cmapYesNo, 
                String productSourcedBy, 
                String cusaPoNumber, 
                Timestamp scheduledDeliveryDate, 
                Timestamp lastUpdateDate, 
                String mailInvoicesTo){
            this.rowNum=rowNum;
            this.ittStatus=ittStatus;
            this.ittAdminOwner=ittAdminOwner;
            this.orderNumber=orderNumber;
            this.ittNumber=ittNumber;
            this.partyName=partyName;
            this.city=city;
            this.state=state;
            this.postalCode=postalCode;
            this.custShipToAddress=custShipToAddress;
            this.name=name;
            this.salesZone=salesZone;
            this.csaSalesBranch=csaSalesBranch;
            this.dealer=dealer;
            this.dealerWhseCode=dealerWhseCode;
            this.dealerAddressLine1=dealerAddressLine1;
            this.dealerAddressLine2=dealerAddressLine2;
            this.dealerAddressLine3=dealerAddressLine3;
            this.dealerCity=dealerCity;
            this.dealerState=dealerState;
            this.dealerZip=dealerZip;
            this.dealerCountry=dealerCountry;
            this.dealerContact=dealerContact;
            this.dealerContactNumber=dealerContactNumber;
            this.dealerEmail=dealerEmail;
            this.cmapYesNo=cmapYesNo;
            this.productSourcedBy=productSourcedBy;
            this.cusaPoNumber=cusaPoNumber;
            this.scheduledDeliveryDate=scheduledDeliveryDate;
            this.lastUpdateDate=lastUpdateDate;
            this.mailInvoicesTo=mailInvoicesTo;
        }
        public BigDecimal getRowNum() {
            return rowNum;
        }
        public void setRowNum(BigDecimal rowNum) {
            this.rowNum=rowNum;
        }
        public String getIttStatus() {
            return ittStatus;
        }
        public void setIttStatus(String ittStatus) {
            this.ittStatus=ittStatus;
        }
        public String getIttAdminOwner() {
            return ittAdminOwner;
        }
        public void setIttAdminOwner(String ittAdminOwner) {
            this.ittAdminOwner=ittAdminOwner;
        }
        public String getOrderNumber() {
            return orderNumber;
        }
        public void setOrderNumber(String orderNumber) {
            this.orderNumber=orderNumber;
        }
        public String getIttNumber() {
            return ittNumber;
        }
        public void setIttNumber(String ittNumber) {
            this.ittNumber=ittNumber;
        }
        public String getPartyName() {
            return partyName;
        }
        public void setPartyName(String partyName) {
            this.partyName=partyName;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city=city;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state=state;
        }
        public String getPostalCode() {
            return postalCode;
        }
        public void setPostalCode(String postalCode) {
            this.postalCode=postalCode;
        }
        public String getCustShipToAddress() {
            return custShipToAddress;
        }
        public void setCustShipToAddress(String custShipToAddress) {
            this.custShipToAddress=custShipToAddress;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name=name;
        }
        public String getSalesZone() {
            return salesZone;
        }
        public void setSalesZone(String salesZone) {
            this.salesZone=salesZone;
        }
        public String getCsaSalesBranch() {
            return csaSalesBranch;
        }
        public void setCsaSalesBranch(String csaSalesBranch) {
            this.csaSalesBranch=csaSalesBranch;
        }
        public String getDealer() {
            return dealer;
        }
        public void setDealer(String dealer) {
            this.dealer=dealer;
        }
        public String getDealerWhseCode() {
            return dealerWhseCode;
        }
        public void setDealerWhseCode(String dealerWhseCode) {
            this.dealerWhseCode=dealerWhseCode;
        }
        public String getDealerAddressLine1() {
            return dealerAddressLine1;
        }
        public void setDealerAddressLine1(String dealerAddressLine1) {
            this.dealerAddressLine1=dealerAddressLine1;
        }
        public String getDealerAddressLine2() {
            return dealerAddressLine2;
        }
        public void setDealerAddressLine2(String dealerAddressLine2) {
            this.dealerAddressLine2=dealerAddressLine2;
        }
        public String getDealerAddressLine3() {
            return dealerAddressLine3;
        }
        public void setDealerAddressLine3(String dealerAddressLine3) {
            this.dealerAddressLine3=dealerAddressLine3;
        }
        public String getDealerCity() {
            return dealerCity;
        }
        public void setDealerCity(String dealerCity) {
            this.dealerCity=dealerCity;
        }
        public String getDealerState() {
            return dealerState;
        }
        public void setDealerState(String dealerState) {
            this.dealerState=dealerState;
        }
        public String getDealerZip() {
            return dealerZip;
        }
        public void setDealerZip(String dealerZip) {
            this.dealerZip=dealerZip;
        }
        public String getDealerCountry() {
            return dealerCountry;
        }
        public void setDealerCountry(String dealerCountry) {
            this.dealerCountry=dealerCountry;
        }
        public String getDealerContact() {
            return dealerContact;
        }
        public void setDealerContact(String dealerContact) {
            this.dealerContact=dealerContact;
        }
        public String getDealerContactNumber() {
            return dealerContactNumber;
        }
        public void setDealerContactNumber(String dealerContactNumber) {
            this.dealerContactNumber=dealerContactNumber;
        }
        public String getDealerEmail() {
            return dealerEmail;
        }
        public void setDealerEmail(String dealerEmail) {
            this.dealerEmail=dealerEmail;
        }
        public String getCmapYesNo() {
            return cmapYesNo;
        }
        public void setCmapYesNo(String cmapYesNo) {
            this.cmapYesNo=cmapYesNo;
        }
        public String getProductSourcedBy() {
            return productSourcedBy;
        }
        public void setProductSourcedBy(String productSourcedBy) {
            this.productSourcedBy=productSourcedBy;
        }
        public String getCusaPoNumber() {
            return cusaPoNumber;
        }
        public void setCusaPoNumber(String cusaPoNumber) {
            this.cusaPoNumber=cusaPoNumber;
        }
        public Timestamp getScheduledDeliveryDate() {
            return scheduledDeliveryDate;
        }
        public void setScheduledDeliveryDate(Timestamp scheduledDeliveryDate) {
            this.scheduledDeliveryDate=scheduledDeliveryDate;
        }
        public Timestamp getLastUpdateDate() {
            return lastUpdateDate;
        }
        public void setLastUpdateDate(Timestamp lastUpdateDate) {
            this.lastUpdateDate=lastUpdateDate;
        }
        public String getMailInvoicesTo() {
            return mailInvoicesTo;
        }
        public void setMailInvoicesTo(String mailInvoicesTo) {
            this.mailInvoicesTo=mailInvoicesTo;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new HeaderDetail(
                        rs.getBigDecimal("ROW_NUM"),
                        rs.getString("ITT_STATUS"),
                        rs.getString("ITT_ADMIN_OWNER"),
                        rs.getString("ORDER_NUMBER"),
                        rs.getString("ITT_NUMBER"),
                        rs.getString("PARTY_NAME"),
                        rs.getString("CITY"),
                        rs.getString("STATE"),
                        rs.getString("POSTAL_CODE"),
                        rs.getString("CUST_SHIP_TO_ADDRESS"),
                        rs.getString("NAME"),
                        rs.getString("SALES_ZONE"),
                        rs.getString("CSA_SALES_BRANCH"),
                        rs.getString("DEALER"),
                        rs.getString("DEALER_WHSE_CODE"),
                        rs.getString("DEALER_ADDRESS_LINE1"),
                        rs.getString("DEALER_ADDRESS_LINE2"),
                        rs.getString("DEALER_ADDRESS_LINE3"),
                        rs.getString("DEALER_CITY"),
                        rs.getString("DEALER_STATE"),
                        rs.getString("DEALER_ZIP"),
                        rs.getString("DEALER_COUNTRY"),
                        rs.getString("DEALER_CONTACT"),
                        rs.getString("DEALER_CONTACT_NUMBER"),
                        rs.getString("DEALER_EMAIL"),
                        rs.getString("CMAP_YES_NO"),
                        rs.getString("PRODUCT_SOURCED_BY"),
                        rs.getString("CUSA_PO_NUMBER"),
                        rs.getTimestamp("SCHEDULED_DELIVERY_DATE"),
                        rs.getTimestamp("LAST_UPDATE_DATE"),
                        rs.getString("MAIL_INVOICES_TO")
                    );
                }
            };
        }
        public String toString() {
            return "HeaderDetail{" + "rowNum="+rowNum+", ittStatus="+ittStatus+", ittAdminOwner="+ittAdminOwner+", orderNumber="+orderNumber+", ittNumber="+ittNumber+", partyName="+partyName+", city="+city+", state="+state+", postalCode="+postalCode+", custShipToAddress="+custShipToAddress+", name="+name+", salesZone="+salesZone+", csaSalesBranch="+csaSalesBranch+", dealer="+dealer+", dealerWhseCode="+dealerWhseCode+", dealerAddressLine1="+dealerAddressLine1+", dealerAddressLine2="+dealerAddressLine2+", dealerAddressLine3="+dealerAddressLine3+", dealerCity="+dealerCity+", dealerState="+dealerState+", dealerZip="+dealerZip+", dealerCountry="+dealerCountry+", dealerContact="+dealerContact+", dealerContactNumber="+dealerContactNumber+", dealerEmail="+dealerEmail+", cmapYesNo="+cmapYesNo+", productSourcedBy="+productSourcedBy+", cusaPoNumber="+cusaPoNumber+", scheduledDeliveryDate="+scheduledDeliveryDate+", lastUpdateDate="+lastUpdateDate+", mailInvoicesTo="+mailInvoicesTo+'}';
        }
    }
    
   public static Object[] ittCmapLov(){
        System.out.println("in ittCmapLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_CMAP_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }

    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_LINE_DETAILS( '51255-76-ITT-001',NULL,NULL,NULL,NULL,NULL,?,?,?)}
    (
    ITT_NUMBER VARCHAR2,
    ORDER_NUMBER VARCHAR2,
    ORDER_STATUS VARCHAR2,
    ORDER_BOOKED_DATE DATE,
    SHIP_TO_CUSTOMER VARCHAR2,
    ITT_ADMIN_OWNER VARCHAR2,
    ITT_ADMIN_OWNER_NAME VARCHAR2,
    ITT_STATUS VARCHAR2,
    SALES_REP VARCHAR2,
    SALES_ZONE VARCHAR2,
    SALES_BRANCH VARCHAR2,
    SHOP_TO_ADDRESS VARCHAR2,
    CITY_STATE VARCHAR2,
    POSTAL_CODE VARCHAR2,
    COUNTRY VARCHAR2,
    DEALER VARCHAR2,
    DEALER_WHSE_CODE VARCHAR2,
    DEALER_ADDRESS_LINE1 VARCHAR2,
    DEALER_ADDRESS_LINE2 VARCHAR2,
    DEALER_ADDRESS_LINE3 VARCHAR2,
    DEALER_CITY VARCHAR2,
    DEALER_STATE VARCHAR2,
    DEALER_ZIP VARCHAR2,
    DEALER_COUNTRY VARCHAR2,
    DEALER_CONTACT VARCHAR2,
    DEALER_CONTACT_NUMBER VARCHAR2,
    DEALER_EMAIL VARCHAR2,
    DEALER_SHIP_TO_CNA_CODE VARCHAR2,
    CMAP_YES_NO VARCHAR2,
    PRODUCT_SOURCED_BY VARCHAR2,
    MAIL_INVOICES_TO VARCHAR2,
    CUST_CONTACT_NAME CHAR,
    CUST_CONTACT_PHONE CHAR,
    CUST_CONTACT_EMAIL CHAR,
    CUST_CONTACT_FAX CHAR,
    DEALER_PO_NUMBER VARCHAR2,
    VENDOR_ID NUMBER,
    VENDOR_SITE_ID NUMBER,
    )
    */
    public static class LineDetailSummary {
       private String ittNumber;
       private String orderNumber;
       private String orderStatus;
       private Timestamp orderBookedDate;
       private String shipToCustomer;
       private String ittAdminOwner;
       private String ittAdminOwnerName;
       private String ittStatus;
       private String salesRep;
       private String salesZone;
       private String salesBranch;
       private String shopToAddress;
       private String cityState;
       private String postalCode;
       private String country;
       private String dealer;
       private String dealerWhseCode;
       private String dealerAddressLine1;
       private String dealerAddressLine2;
       private String dealerAddressLine3;
       private String dealerCity;
       private String dealerState;
       private String dealerZip;
       private String dealerCountry;
       private String dealerContact;
       private String dealerContactNumber;
       private String dealerEmail;
       private String dealerShipToCnaCode;
       private String cmapYesNo;
       private String productSourcedBy;
       private String mailInvoicesTo;
       private String custContactName;
       private String custContactPhone;
       private String custContactEmail;
       private String custContactFax;
       private String dealerPoNumber;
       private BigDecimal vendorId;
       private BigDecimal vendorSiteId;
       private String prntVndCd;
	   private String dealerShiptoName;
	   private String dealerShiptoContact;
	   private String dealerShiptoZip;
	   private String dealerShiptoContactNo;
	   private String dealerShiptoEmail;
	   private String dealerShiptoAddLn1;
       private String dealerShiptoAddLn2;
       private String dealerShiptoAddLn3;
       private String dealerShiptoCity;
       private String dealerShiptoState;
	   private Timestamp reqdeliverydate;
	   private String compaprvstatus;

        public LineDetailSummary(){
        }
        public LineDetailSummary(String ittNumber, 
                String orderNumber, 
                String orderStatus, 
                Timestamp orderBookedDate, 
                String shipToCustomer, 
                String ittAdminOwner, 
                String ittAdminOwnerName, 
                String ittStatus, 
                String salesRep, 
                String salesZone, 
                String salesBranch, 
                String shopToAddress, 
                String cityState, 
                String postalCode, 
                String country, 
                String dealer, 
                String dealerWhseCode, 
                String dealerAddressLine1, 
                String dealerAddressLine2, 
                String dealerAddressLine3, 
                String dealerCity, 
                String dealerState, 
                String dealerZip, 
                String dealerCountry, 
                String dealerContact, 
                String dealerContactNumber, 
                String dealerEmail, 
                String dealerShipToCnaCode, 
                String cmapYesNo, 
                String productSourcedBy, 
                String mailInvoicesTo, 
                String custContactName, 
                String custContactPhone, 
                String custContactEmail, 
                String custContactFax, 
                String dealerPoNumber, 
                BigDecimal vendorId, 
                BigDecimal vendorSiteId,
                String prntVndCd,
				String dealerShiptoName,
				String dealerShiptoContact,
				String dealerShiptoZip,
				String dealerShiptoContactNo,
				String dealerShiptoEmail,
				String dealerShiptoAddLn1,
				String dealerShiptoAddLn2,
				String dealerShiptoAddLn3,
				String dealerShiptoCity,
				String dealerShiptoState,
				Timestamp reqdeliverydate,
				String compaprvstatus){
            this.ittNumber=ittNumber;
            this.orderNumber=orderNumber;
            this.orderStatus=orderStatus;
            this.orderBookedDate=orderBookedDate;
            this.shipToCustomer=shipToCustomer;
            this.ittAdminOwner=ittAdminOwner;
            this.ittAdminOwnerName=ittAdminOwnerName;
            this.ittStatus=ittStatus;
            this.salesRep=salesRep;
            this.salesZone=salesZone;
            this.salesBranch=salesBranch;
            this.shopToAddress=shopToAddress;
            this.cityState=cityState;
            this.postalCode=postalCode;
            this.country=country;
            this.dealer=dealer;
            this.dealerWhseCode=dealerWhseCode;
            this.dealerAddressLine1=dealerAddressLine1;
            this.dealerAddressLine2=dealerAddressLine2;
            this.dealerAddressLine3=dealerAddressLine3;
            this.dealerCity=dealerCity;
            this.dealerState=dealerState;
            this.dealerZip=dealerZip;
            this.dealerCountry=dealerCountry;
            this.dealerContact=dealerContact;
            this.dealerContactNumber=dealerContactNumber;
            this.dealerEmail=dealerEmail;
            this.dealerShipToCnaCode=dealerShipToCnaCode;
            this.cmapYesNo=cmapYesNo;
            this.productSourcedBy=productSourcedBy;
            this.mailInvoicesTo=mailInvoicesTo;
            this.custContactName=custContactName;
            this.custContactPhone=custContactPhone;
            this.custContactEmail=custContactEmail;
            this.custContactFax=custContactFax;
            this.dealerPoNumber=dealerPoNumber;
            this.vendorId=vendorId;
            this.vendorSiteId=vendorSiteId;
            this.prntVndCd=prntVndCd;
			this.dealerShiptoName=dealerShiptoName;
			this.dealerShiptoContact=dealerShiptoContact;
			this.dealerShiptoZip=dealerShiptoZip;
			this.dealerShiptoContactNo=dealerShiptoContactNo;
			this.dealerShiptoEmail=dealerShiptoEmail;
			this.dealerShiptoAddLn1=dealerShiptoAddLn1;
			this.dealerShiptoAddLn2=dealerShiptoAddLn2;
			this.dealerShiptoAddLn3=dealerShiptoAddLn3;
			this.dealerShiptoCity=dealerShiptoCity;
			this.dealerShiptoState=dealerShiptoState;
			this.reqdeliverydate=reqdeliverydate;
			this.compaprvstatus=compaprvstatus;
        }
        public String getIttNumber() {
            return ittNumber;
        }
        public void setIttNumber(String ittNumber) {
            this.ittNumber=ittNumber;
        }
        public String getOrderNumber() {
            return orderNumber;
        }
        public void setOrderNumber(String orderNumber) {
            this.orderNumber=orderNumber;
        }
        public String getOrderStatus() {
            return orderStatus;
        }
        public void setOrderStatus(String orderStatus) {
            this.orderStatus=orderStatus;
        }
        public Timestamp getOrderBookedDate() {
            return orderBookedDate;
        }
        public void setOrderBookedDate(Timestamp orderBookedDate) {
            this.orderBookedDate=orderBookedDate;
        }
        public String getShipToCustomer() {
            return shipToCustomer;
        }
        public void setShipToCustomer(String shipToCustomer) {
            this.shipToCustomer=shipToCustomer;
        }
        public String getIttAdminOwner() {
            return ittAdminOwner;
        }
        public void setIttAdminOwner(String ittAdminOwner) {
            this.ittAdminOwner=ittAdminOwner;
        }
        public String getIttAdminOwnerName() {
            return ittAdminOwnerName;
        }
        public void setIttAdminOwnerName(String ittAdminOwnerName) {
            this.ittAdminOwnerName=ittAdminOwnerName;
        }
        public String getIttStatus() {
            return ittStatus;
        }
        public void setIttStatus(String ittStatus) {
            this.ittStatus=ittStatus;
        }
        public String getSalesRep() {
            return salesRep;
        }
        public void setSalesRep(String salesRep) {
            this.salesRep=salesRep;
        }
        public String getSalesZone() {
            return salesZone;
        }
        public void setSalesZone(String salesZone) {
            this.salesZone=salesZone;
        }
        public String getSalesBranch() {
            return salesBranch;
        }
        public void setSalesBranch(String salesBranch) {
            this.salesBranch=salesBranch;
        }
        public String getShopToAddress() {
            return shopToAddress;
        }
        public void setShopToAddress(String shopToAddress) {
            this.shopToAddress=shopToAddress;
        }
        public String getCityState() {
            return cityState;
        }
        public void setCityState(String cityState) {
            this.cityState=cityState;
        }
        public String getPostalCode() {
            return postalCode;
        }
        public void setPostalCode(String postalCode) {
            this.postalCode=postalCode;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country=country;
        }
        public String getDealer() {
            return dealer;
        }
        public void setDealer(String dealer) {
            this.dealer=dealer;
        }
        public String getDealerWhseCode() {
            return dealerWhseCode;
        }
        public void setDealerWhseCode(String dealerWhseCode) {
            this.dealerWhseCode=dealerWhseCode;
        }
        public String getDealerAddressLine1() {
            return dealerAddressLine1;
        }
        public void setDealerAddressLine1(String dealerAddressLine1) {
            this.dealerAddressLine1=dealerAddressLine1;
        }
        public String getDealerAddressLine2() {
            return dealerAddressLine2;
        }
        public void setDealerAddressLine2(String dealerAddressLine2) {
            this.dealerAddressLine2=dealerAddressLine2;
        }
        public String getDealerAddressLine3() {
            return dealerAddressLine3;
        }
        public void setDealerAddressLine3(String dealerAddressLine3) {
            this.dealerAddressLine3=dealerAddressLine3;
        }
        public String getDealerCity() {
            return dealerCity;
        }
        public void setDealerCity(String dealerCity) {
            this.dealerCity=dealerCity;
        }
        public String getDealerState() {
            return dealerState;
        }
        public void setDealerState(String dealerState) {
            this.dealerState=dealerState;
        }
        public String getDealerZip() {
            return dealerZip;
        }
        public void setDealerZip(String dealerZip) {
            this.dealerZip=dealerZip;
        }
        public String getDealerCountry() {
            return dealerCountry;
        }
        public void setDealerCountry(String dealerCountry) {
            this.dealerCountry=dealerCountry;
        }
        public String getDealerContact() {
            return dealerContact;
        }
        public void setDealerContact(String dealerContact) {
            this.dealerContact=dealerContact;
        }
        public String getDealerContactNumber() {
            return dealerContactNumber;
        }
        public void setDealerContactNumber(String dealerContactNumber) {
            this.dealerContactNumber=dealerContactNumber;
        }
        public String getDealerEmail() {
            return dealerEmail;
        }
        public void setDealerEmail(String dealerEmail) {
            this.dealerEmail=dealerEmail;
        }
        public String getDealerShipToCnaCode() {
            return dealerShipToCnaCode;
        }
        public void setDealerShipToCnaCode(String dealerShipToCnaCode) {
            this.dealerShipToCnaCode=dealerShipToCnaCode;
        }
        public String getCmapYesNo() {
            return cmapYesNo;
        }
        public void setCmapYesNo(String cmapYesNo) {
            this.cmapYesNo=cmapYesNo;
        }
        public String getProductSourcedBy() {
            return productSourcedBy;
        }
        public void setProductSourcedBy(String productSourcedBy) {
            this.productSourcedBy=productSourcedBy;
        }
        public String getMailInvoicesTo() {
            return mailInvoicesTo;
        }
        public void setMailInvoicesTo(String mailInvoicesTo) {
            this.mailInvoicesTo=mailInvoicesTo;
        }
        public String getCustContactName() {
            return custContactName;
        }
        public void setCustContactName(String custContactName) {
            this.custContactName=custContactName;
        }
        public String getCustContactPhone() {
            return custContactPhone;
        }
        public void setCustContactPhone(String custContactPhone) {
            this.custContactPhone=custContactPhone;
        }
        public String getCustContactEmail() {
            return custContactEmail;
        }
        public void setCustContactEmail(String custContactEmail) {
            this.custContactEmail=custContactEmail;
        }
        public String getCustContactFax() {
            return custContactFax;
        }
        public void setCustContactFax(String custContactFax) {
            this.custContactFax=custContactFax;
        }
        public String getDealerPoNumber() {
            return dealerPoNumber;
        }
        public void setDealerPoNumber(String dealerPoNumber) {
            this.dealerPoNumber=dealerPoNumber;
        }
        public BigDecimal getVendorId() {
            return vendorId;
        }
        public void setVendorId(BigDecimal vendorId) {
            this.vendorId=vendorId;
        }
        public BigDecimal getVendorSiteId() {
            return vendorSiteId;
        }
        public void setVendorSiteId(BigDecimal vendorSiteId) {
            this.vendorSiteId=vendorSiteId;
        }        
        public String getPrntVndCd() {
			return prntVndCd;
		}
		public void setPrntVndCd(String prntVndCd) {
			this.prntVndCd = prntVndCd;
		}
		
		public String getDealerShiptoName() {
            return dealerShiptoName;
        }
        public void setDealerShiptoName(String dealerShiptoName) {
            this.dealerShiptoName=dealerShiptoName;
        }
		public String getDealerShiptoContact() {
            return dealerShiptoContact;
        }
        public void setDealerShiptoContact(String dealerShiptoContact) {
            this.dealerShiptoContact=dealerShiptoContact;
        }
		public String getDealerShiptoZip() {
            return dealerShiptoZip;
        }
        public void setDealerShiptoZip(String dealerShiptoZip) {
            this.dealerShiptoZip=dealerShiptoZip;
        }
		public String getDealerShiptoContactNo() {
            return dealerShiptoContactNo;
        }
        public void setDealerShiptoContactNo(String dealerShiptoContactNo) {
            this.dealerShiptoContactNo=dealerShiptoContactNo;
        }
		public String getDealerShiptoEmail() {
            return dealerShiptoEmail;
        }
        public void setDealerShiptoEmail(String dealerShiptoEmail) {
            this.dealerShiptoEmail=dealerShiptoEmail;
        }
		public String getDealerShiptoAddLn1() {
            return dealerShiptoAddLn1;
        }
        public void setDealerShiptoAddLn1(String dealerShiptoAddLn1) {
            this.dealerShiptoAddLn1=dealerShiptoAddLn1;
        }
		public String getDealerShiptoAddLn2() {
            return dealerShiptoAddLn2;
        }
        public void setDealerShiptoAddLn2(String dealerShiptoAddLn2) {
            this.dealerShiptoAddLn2=dealerShiptoAddLn2;
        }
		public String getDealerShiptoAddLn3() {
            return dealerShiptoAddLn3;
        }
        public void setDealerShiptoAddLn3(String dealerShiptoAddLn3) {
            this.dealerShiptoAddLn3=dealerShiptoAddLn3;
        }
		public String getDealerShiptoCity() {
            return dealerShiptoCity;
        }
        public void setDealerShiptoCity(String dealerShiptoCity) {
            this.dealerShiptoCity=dealerShiptoCity;
        }
		public String getDealerShiptoState() {
            return dealerShiptoState;
        }
        public void setDealerShiptoState(String dealerShiptoState) {
            this.dealerShiptoState=dealerShiptoState;
        }
        public Timestamp getreqdeliverydate() {
            return reqdeliverydate;
        }
        public void setreqdeliverydate(Timestamp reqdeliverydate) {
            this.reqdeliverydate=reqdeliverydate;
        }
		public String getCompAprvStatus() {
            return compaprvstatus;
        }
        public void setCompAprvStatus(String compaprvstatus) {
            this.compaprvstatus=compaprvstatus;
        }		
		
		public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new LineDetailSummary(
                        rs.getString("ITT_NUMBER"),
                        rs.getString("ORDER_NUMBER"),
                        rs.getString("ORDER_STATUS"),
                        rs.getTimestamp("ORDER_BOOKED_DATE"),
                        rs.getString("SHIP_TO_CUSTOMER"),
                        rs.getString("ITT_ADMIN_OWNER"),
                        rs.getString("ITT_ADMIN_OWNER_NAME"),
                        rs.getString("ITT_STATUS"),
                        rs.getString("SALES_REP"),
                        rs.getString("SALES_ZONE"),
                        rs.getString("SALES_BRANCH"),
                        rs.getString("SHOP_TO_ADDRESS"),
                        rs.getString("CITY_STATE"),
                        rs.getString("POSTAL_CODE"),
                        rs.getString("COUNTRY"),
                        rs.getString("DEALER"),
                        rs.getString("DEALER_WHSE_CODE"),
                        rs.getString("DEALER_ADDRESS_LINE1"),
                        rs.getString("DEALER_ADDRESS_LINE2"),
                        rs.getString("DEALER_ADDRESS_LINE3"),
                        rs.getString("DEALER_CITY"),
                        rs.getString("DEALER_STATE"),
                        rs.getString("DEALER_ZIP"),
                        rs.getString("DEALER_COUNTRY"),
                        rs.getString("DEALER_CONTACT"),
                        rs.getString("DEALER_CONTACT_NUMBER"),
                        rs.getString("DEALER_EMAIL"),
                        rs.getString("DEALER_SHIP_TO_CNA_CODE"),
                        rs.getString("CMAP_YES_NO"),
                        rs.getString("PRODUCT_SOURCED_BY"),
                        rs.getString("MAIL_INVOICES_TO"),
                        rs.getString("CUST_CONTACT_NAME"),
                        rs.getString("CUST_CONTACT_PHONE"),
                        rs.getString("CUST_CONTACT_EMAIL"),
                        rs.getString("CUST_CONTACT_FAX"),
                        rs.getString("DEALER_PO_NUMBER"),
                        rs.getBigDecimal("VENDOR_ID"),
                        rs.getBigDecimal("VENDOR_SITE_ID"),
                        rs.getString("PRNT_VND_CD"),
						rs.getString("DEALER_SHIPTO_NAME"),
						rs.getString("DEALER_SHIPTO_CONTACT"),
						rs.getString("DEALER_SHIPTO_ZIP"),
						rs.getString("DEALER_SHIPTO_CONTACT_NUMBER"),
						rs.getString("DEALER_SHIPTO_EMAIL"),
						rs.getString("DEALER_SHIPTO_ADDRESS_LINE1"),
						rs.getString("DEALER_SHIPTO_ADDRESS_LINE2"),
						rs.getString("DEALER_SHIPTO_ADDRESS_LINE3"),
						rs.getString("DEALER_SHIPTO_CITY"),
						rs.getString("DEALER_SHIPTO_STATE"),
						rs.getTimestamp("REQUESTED_DELIVERY_DATE"),
						rs.getString("COMP_APPRV_STATUS")
                    );
                }
            };
        }
        public String toString() {
            return "LineDetailSummary{" + "ittNumber="+ittNumber+", orderNumber="+orderNumber+", orderStatus="+orderStatus+", orderBookedDate="+orderBookedDate+", shipToCustomer="+shipToCustomer+", ittAdminOwner="+ittAdminOwner+", ittAdminOwnerName="+ittAdminOwnerName+", ittStatus="+ittStatus+", salesRep="+salesRep+", salesZone="+salesZone+", salesBranch="+salesBranch+", shopToAddress="+shopToAddress+", cityState="+cityState+", postalCode="+postalCode+", country="+country+", dealer="+dealer+", dealerWhseCode="+dealerWhseCode+", dealerAddressLine1="+dealerAddressLine1+", dealerAddressLine2="+dealerAddressLine2+", dealerAddressLine3="+dealerAddressLine3+", dealerCity="+dealerCity+", dealerState="+dealerState+", dealerZip="+dealerZip+", dealerCountry="+dealerCountry+", dealerContact="+dealerContact+", dealerContactNumber="+dealerContactNumber+", dealerEmail="+dealerEmail+", dealerShipToCnaCode="+dealerShipToCnaCode+", cmapYesNo="+cmapYesNo+", productSourcedBy="+productSourcedBy+", mailInvoicesTo="+mailInvoicesTo+", custContactName="+custContactName+", custContactPhone="+custContactPhone+", custContactEmail="+custContactEmail+", custContactFax="+custContactFax+", dealerPoNumber="+dealerPoNumber+", vendorId="+vendorId+", vendorSiteId="+vendorSiteId+", prntVndCd="+prntVndCd+",reqdeliverydate="+reqdeliverydate+",compaprvstatus="+compaprvstatus+'}';
        }
    }

    /* output cursor position 2 */
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_LINE_DETAILS( '51255-76-ITT-001',NULL,NULL,NULL,NULL,NULL,?,?,?)}
    (
    ROW_NUM NUMBER,
    LINE_ID NUMBER,
    MERCHANDISE VARCHAR2,
    ITT_NUMBER VARCHAR2,
    LINE_NUMBER VARCHAR2,
    SO_LINE_TYPE VARCHAR2,
    ITT_STATUS VARCHAR2,
    ITEM VARCHAR2,
    ITEM_DESCRIPTION VARCHAR2,
    ORDERED_QUANTITY NUMBER,
    ITEM_PURCHASE_PRICE NUMBER,
    FINDER_FEE NUMBER,
    INSTALL_CREDIT NUMBER,
    LINE_STATUS_CODE VARCHAR2,
    DELIVERY_SCHEDULED_DATE DATE,
    REASON_CODE VARCHAR2,
    SERIAL_NUMBER VARCHAR2,
    LOT_NUMBER VARCHAR2,
    CUSA_PO_NUMBER VARCHAR2,
    ITT_LINE_TYPE VARCHAR2,
    PRODUCT_SOURCED_BY VARCHAR2
    )
    */
    public static class LineDetailItem {
       private BigDecimal rowNum;
       private BigDecimal headerId;	   
       private String lineId;
       private String merchandise;
       private String ittNumber;
       private String lineNumber;
       private String soLineType;
       private String ittStatus;
       private String item;
       private String itemDescription;
       private BigDecimal orderedQuantity;
       private BigDecimal itemPurchasePrice;
       private BigDecimal finderFee;
       private BigDecimal installCredit;
       private String lineStatusCode;
       private Timestamp deliveryScheduledDate;
       private String reasonCode;
       private String serialNumber;
       private String lotNumber;
       private String cusaPoNumber;
       private String ittLineType;
       private String productSourcedBy;
	   private String gsdDeliveryNumber;
	   private String gsdDeliveryStatus;
	   private String trackingnumber;
	   private String shipmethod;	
	   private String shipset;		
       private BigDecimal complinetotal;
	   private String priceListName;
	   private String poAckStatus;
       private Timestamp shippedDate;	   
	   private String shipmentNumber;	     	   

        public LineDetailItem(){
        }
        public LineDetailItem(BigDecimal rowNum, 
				BigDecimal headerId,	
        		String lineId, 
                String merchandise, 
                String ittNumber, 
                String lineNumber, 
                String soLineType, 
                String ittStatus, 
                String item, 
                String itemDescription, 
                BigDecimal orderedQuantity, 
                BigDecimal itemPurchasePrice, 
                BigDecimal finderFee, 
                BigDecimal installCredit, 
                String lineStatusCode, 
                Timestamp deliveryScheduledDate, 
                String reasonCode, 
                String serialNumber, 
                String lotNumber, 
                String cusaPoNumber, 
                String ittLineType, 
                String productSourcedBy,
				String gsdDeliveryNumber,
				String gsdDeliveryStatus,
			    String trackingnumber,
				String shipmethod,
				String shipset,
				BigDecimal complinetotal,
				String priceListName,
				String poAckStatus,
				Timestamp shippedDate,
				String shipmentNumber				
				){
            this.rowNum=rowNum;
            this.lineId=lineId;
            this.merchandise=merchandise;
            this.ittNumber=ittNumber;
            this.lineNumber=lineNumber;
            this.soLineType=soLineType;
            this.ittStatus=ittStatus;
            this.item=item;
            this.itemDescription=itemDescription;
            this.orderedQuantity=orderedQuantity;
            this.itemPurchasePrice=itemPurchasePrice;
            this.finderFee=finderFee;
            this.installCredit=installCredit;
            this.lineStatusCode=lineStatusCode;
            this.deliveryScheduledDate=deliveryScheduledDate;
            this.reasonCode=reasonCode;
            this.serialNumber=serialNumber;
            this.lotNumber=lotNumber;
            this.cusaPoNumber=cusaPoNumber;
            this.ittLineType=ittLineType;
            this.productSourcedBy=productSourcedBy;
			this.gsdDeliveryNumber=gsdDeliveryNumber;
			this.gsdDeliveryStatus=gsdDeliveryStatus;
			this.trackingnumber=trackingnumber;
			this.shipmethod=shipmethod;
			this.shipset=shipset;
			this.complinetotal=complinetotal;
			this.priceListName=priceListName;
			this.poAckStatus=poAckStatus;
			this.shippedDate=shippedDate;
			this.shipmentNumber=shipmentNumber;			
        }
        public BigDecimal getRowNum() {
            return rowNum;
        }
        public void setRowNum(BigDecimal rowNum) {
            this.rowNum=rowNum;
        }
        public BigDecimal getheaderId() {
            return headerId;
        }
        public void setheaderId(BigDecimal headerId) {
            this.headerId=headerId;
        }		
        public String getLineId() {
            return lineId;
        }
        public void setLineId(String lineId) {
            this.lineId=lineId;
        }
        public String getMerchandise() {
            return merchandise;
        }
        public void setMerchandise(String merchandise) {
            this.merchandise=merchandise;
        }
        public String getIttNumber() {
            return ittNumber;
        }
        public void setIttNumber(String ittNumber) {
            this.ittNumber=ittNumber;
        }
        public String getLineNumber() {
            return lineNumber;
        }
        public void setLineNumber(String lineNumber) {
            this.lineNumber=lineNumber;
        }
        public String getSoLineType() {
            return soLineType;
        }
        public void setSoLineType(String soLineType) {
            this.soLineType=soLineType;
        }
        public String getIttStatus() {
            return ittStatus;
        }
        public void setIttStatus(String ittStatus) {
            this.ittStatus=ittStatus;
        }
        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item=item;
        }
        public String getItemDescription() {
            return itemDescription;
        }
        public void setItemDescription(String itemDescription) {
            this.itemDescription=itemDescription;
        }
        public BigDecimal getOrderedQuantity() {
            return orderedQuantity;
        }
        public void setOrderedQuantity(BigDecimal orderedQuantity) {
            this.orderedQuantity=orderedQuantity;
        }
        public BigDecimal getItemPurchasePrice() {
            return itemPurchasePrice;
        }
        public void setItemPurchasePrice(BigDecimal itemPurchasePrice) {
            this.itemPurchasePrice=itemPurchasePrice;
        }
        public BigDecimal getFinderFee() {
            return finderFee;
        }
        public void setFinderFee(BigDecimal finderFee) {
            this.finderFee=finderFee;
        }
        public BigDecimal getInstallCredit() {
            return installCredit;
        }
        public void setInstallCredit(BigDecimal installCredit) {
            this.installCredit=installCredit;
        }
        public String getLineStatusCode() {
            return lineStatusCode;
        }
        public void setLineStatusCode(String lineStatusCode) {
            this.lineStatusCode=lineStatusCode;
        }
        public Timestamp getDeliveryScheduledDate() {
            return deliveryScheduledDate;
        }
        public void setDeliveryScheduledDate(Timestamp deliveryScheduledDate) {
            this.deliveryScheduledDate=deliveryScheduledDate;
        }
        public String getReasonCode() {
            return reasonCode;
        }
        public void setReasonCode(String reasonCode) {
            this.reasonCode=reasonCode;
        }
        public String getSerialNumber() {
            return serialNumber;
        }
        public void setSerialNumber(String serialNumber) {
            this.serialNumber=serialNumber;
        }
        public String getLotNumber() {
            return lotNumber;
        }
        public void setLotNumber(String lotNumber) {
            this.lotNumber=lotNumber;
        }
        public String getCusaPoNumber() {
            return cusaPoNumber;
        }
        public void setCusaPoNumber(String cusaPoNumber) {
            this.cusaPoNumber=cusaPoNumber;
        }
        public String getIttLineType() {
            return ittLineType;
        }
        public void setIttLineType(String ittLineType) {
            this.ittLineType=ittLineType;
        }
        public String getProductSourcedBy() {
            return productSourcedBy;
        }
        public void setProductSourcedBy(String productSourcedBy) {
            this.productSourcedBy=productSourcedBy;
        }
		public String getGSDDeliveryNumber() {
            return gsdDeliveryNumber;
        }
        public void setGSDDeliveryNumber(String gsdDeliveryNumber) {
            this.gsdDeliveryNumber=gsdDeliveryNumber;
        }
		public String getGSDDeliveryStatus() {
            return gsdDeliveryStatus;
        }
        public void setGSDDeliveryStatus(String gsdDeliveryStatus) {
            this.gsdDeliveryStatus=gsdDeliveryStatus;
        }
		public String gettrackingnumber() {
            return trackingnumber;
        }
        public void settrackingnumber(String trackingnumber) {
            this.trackingnumber=trackingnumber;
        }
		public String getshipmethod() {
            return shipmethod;
        }
        public void setshipmethod(String shipmethod) {
            this.shipmethod=shipmethod;
        }	
		public String getshipset() {
            return shipset;
        }
        public void setshipset(String shipset) {
            this.shipset=shipset;
        }		
        public BigDecimal getcomplinetotal() {
            return complinetotal;
        }
        public void setcomplinetotal(BigDecimal complinetotal) {
            this.complinetotal=complinetotal;
        }		
		public String getPriceListName() {
            return priceListName;
        }
        public void setPriceListName(String priceListName) {
            this.priceListName=priceListName;
        }	
		
		public String getPoAckStatus() {
            return poAckStatus;
        }
        public void setPoAckStatus(String poAckStatus) {
            this.poAckStatus=poAckStatus;
        }

		public Timestamp getShippedDate() {
            return shippedDate;
        }
        public void setShippedDate(Timestamp shippedDate) {
            this.shippedDate=shippedDate;
        }
		
		public String getShipmentNumber() {
            return shipmentNumber;
        }
        public void setShipmentNumber(String shipmentNumber) {
            this.shipmentNumber=shipmentNumber;
        }		
        
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new LineDetailItem(
                        rs.getBigDecimal("ROW_NUM"),
						null,//rs.getBigDecimal("HEADER_ID"),
                        rs.getString("LINE_ID"),
                        rs.getString("MERCHANDISE"),
                        rs.getString("ITT_NUMBER"),
                        rs.getString("LINE_NUMBER"),
                        rs.getString("SO_LINE_TYPE"),
                        rs.getString("ITT_STATUS"),
                        rs.getString("ITEM"),
                        rs.getString("ITEM_DESCRIPTION"),
                        rs.getBigDecimal("ORDERED_QUANTITY"),
                        rs.getBigDecimal("ITEM_PURCHASE_PRICE"),
                        rs.getBigDecimal("FINDER_FEE"),
                        rs.getBigDecimal("INSTALL_CREDIT"),
                        rs.getString("LINE_STATUS_CODE"),
                        rs.getTimestamp("DELIVERY_SCHEDULED_DATE"),
                        rs.getString("REASON_CODE"),
                        rs.getString("SERIAL_NUMBER"),
                        rs.getString("LOT_NUMBER"),
                        rs.getString("CUSA_PO_NUMBER"),
                        rs.getString("ITT_LINE_TYPE"),
                        rs.getString("PRODUCT_SOURCED_BY"),
						rs.getString("DELIVERY_NUMBER"),
						rs.getString("DELIVERY_STATUS"),
						rs.getString("TRACKING_NUMBER"),
						rs.getString("SHIPPING_METHOD"),
                        rs.getString("SHIP_SET"),
						rs.getBigDecimal("COMP_LINE_TOTAL"),
						rs.getString("PRICE_LIST_NAME"),
						//ITG#732264
						rs.getString("POACK_STATUS"), 
					    rs.getTimestamp("SHIPPED_DATE"),
					    rs.getString("SHIPMENT_NUMBER")						
                    );
                }
            };
        }
        public String toString() {
            return "LineDetailItem{" + "rowNum="+rowNum+", headerId="+headerId+", lineId="+lineId+", merchandise="+merchandise+", ittNumber="+ittNumber+", lineNumber="+lineNumber+", soLineType="+soLineType+", ittStatus="+ittStatus+", item="+item+", itemDescription="+itemDescription+", orderedQuantity="+orderedQuantity+", itemPurchasePrice="+itemPurchasePrice+", finderFee="+finderFee+", installCredit="+installCredit+", lineStatusCode="+lineStatusCode+", deliveryScheduledDate="+deliveryScheduledDate+", reasonCode="+reasonCode+", serialNumber="+serialNumber+", lotNumber="+lotNumber+", cusaPoNumber="+cusaPoNumber+", ittLineType="+ittLineType+", productSourcedBy="+productSourcedBy+", gsdDeliveryNumber="+gsdDeliveryNumber+", gsdDeliveryStatus="+gsdDeliveryStatus+", trackingnumber="+trackingnumber+", shipmethod="+shipmethod+", shipset"+shipset+", complinetotal="+complinetotal+", priceListName="+priceListName+", poAckStatus="+poAckStatus+", shippedDate="+shippedDate+", shipmentNumber="+shipmentNumber+'}';
        }
    }
    
    
    public static interface CanonE580RowMapper {

        Object mapRow(ResultSet rs, int rowNum) throws SQLException;
    }
    
    public static CanonE580RowMapper stringRowMapper(){
         return new CanonE580RowMapper() {
                            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                return rs.getString(1);
                            }
                        };
    }
    
    private static void logErrorMessage(String message) {
        System.err.println(message);
    }

    private static void logMessage(String message) {
        System.out.println(message);
    }

    private static void closeResources(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception exception) {
                logErrorMessage("Unable to close result set:  " + exception);
            }
        }
    }

    public static void closeResources(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception exception) {
                logErrorMessage("Unable to close statement:  " + exception);
            }
        }
    }
    
    public static Object[] getServerInitialPath() {

        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SERVER_INITIAL_PATH);
                if (statement != null) {
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
    
    public static class ITTOrderProcessorInfo {
       private String sourceName;
       private String sourceNumber;
       private String roleName;

        public ITTOrderProcessorInfo(){
        }
        public ITTOrderProcessorInfo(String sourceName, 
                String sourceNumber, 
                String roleName){
            this.sourceName=sourceName;
            this.sourceNumber=sourceNumber;
            this.roleName=roleName;
        }
        public String getSourceName() {
            return sourceName;
        }
        public void setSourceName(String sourceName) {
            this.sourceName=sourceName;
        }
        public String getSourceNumber() {
            return sourceNumber;
        }
        public void setSourceNumber(String sourceNumber) {
            this.sourceNumber=sourceNumber;
        }
        public String getRoleName() {
            return roleName;
        }
        public void setRoleName(String roleName) {
            this.roleName=roleName;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ITTOrderProcessorInfo(
                        rs.getString("SOURCE_NAME"),
                        rs.getString("SOURCE_NUMBER"),
                        rs.getString("ROLE_NAME")
                    );
                }
            };
        }
        public String toString() {
            return "ITTOrderProcessorInfo{" + "sourceName="+sourceName+", sourceNumber="+sourceNumber+", roleName="+roleName+'}';
        }
    }


   public static Object[] ittDealerLov(String p_dealer_name){
        System.out.println("in ittDealerLov "+p_dealer_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_DEALER_LOV);
                if (statement != null) {
                    statement.setObject(1, p_dealer_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),DealerInfo.getRowMapper())};
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
        return null;
   }

    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_LOV(NULL,?)}
    (
    VENDOR_ID NUMBER,
    DEALER_NAME VARCHAR2,
    SUPPLIER_CODE VARCHAR2,
    VENDOR_SITE_ID NUMBER,
    DEALER_CODE VARCHAR2,
    ADDRESS_LINE1 VARCHAR2,
    ADDRESS_LINE2 VARCHAR2,
    ADDRESS_LINE3 VARCHAR2,
    CITY VARCHAR2,
    STATE VARCHAR2,
    ZIP VARCHAR2,
    COUNTRY VARCHAR2,
    FIRST_NAME VARCHAR2,
    MIDDLE_NAME VARCHAR2,
    LAST_NAME VARCHAR2,
    AREA_CODE VARCHAR2,
    PHONE VARCHAR2,
    EMAIL_ADDRESS VARCHAR2
    )
    */
    public static class DealerInfo {
       private BigDecimal vendorId;
       private String dealerName;
       private String supplierCode;
       private BigDecimal vendorSiteId;
       private String dealerCode;
       private String addressLine1;
       private String addressLine2;
       private String addressLine3;
       private String city;
       private String state;
       private String zip;
       private String country;
       private String firstName;
       private String middleName;
       private String lastName;
       private String areaCode;
       private String phone;
       private String emailAddress;

        public DealerInfo(){
        }
        public DealerInfo(BigDecimal vendorId, 
                String dealerName, 
                String supplierCode, 
                BigDecimal vendorSiteId, 
                String dealerCode, 
                String addressLine1, 
                String addressLine2, 
                String addressLine3, 
                String city, 
                String state, 
                String zip, 
                String country, 
                String firstName, 
                String middleName, 
                String lastName, 
                String areaCode, 
                String phone, 
                String emailAddress){
            this.vendorId=vendorId;
            this.dealerName=dealerName;
            this.supplierCode=supplierCode;
            this.vendorSiteId=vendorSiteId;
            this.dealerCode=dealerCode;
            this.addressLine1=addressLine1;
            this.addressLine2=addressLine2;
            this.addressLine3=addressLine3;
            this.city=city;
            this.state=state;
            this.zip=zip;
            this.country=country;
            this.firstName=firstName;
            this.middleName=middleName;
            this.lastName=lastName;
            this.areaCode=areaCode;
            this.phone=phone;
            this.emailAddress=emailAddress;
        }
        public BigDecimal getVendorId() {
            return vendorId;
        }
        public void setVendorId(BigDecimal vendorId) {
            this.vendorId=vendorId;
        }
        public String getDealerName() {
            return dealerName;
        }
        public void setDealerName(String dealerName) {
            this.dealerName=dealerName;
        }
        public String getSupplierCode() {
            return supplierCode;
        }
        public void setSupplierCode(String supplierCode) {
            this.supplierCode=supplierCode;
        }
        public BigDecimal getVendorSiteId() {
            return vendorSiteId;
        }
        public void setVendorSiteId(BigDecimal vendorSiteId) {
            this.vendorSiteId=vendorSiteId;
        }
        public String getDealerCode() {
            return dealerCode;
        }
        public void setDealerCode(String dealerCode) {
            this.dealerCode=dealerCode;
        }
        public String getAddressLine1() {
            return addressLine1;
        }
        public void setAddressLine1(String addressLine1) {
            this.addressLine1=addressLine1;
        }
        public String getAddressLine2() {
            return addressLine2;
        }
        public void setAddressLine2(String addressLine2) {
            this.addressLine2=addressLine2;
        }
        public String getAddressLine3() {
            return addressLine3;
        }
        public void setAddressLine3(String addressLine3) {
            this.addressLine3=addressLine3;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city=city;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state=state;
        }
        public String getZip() {
            return zip;
        }
        public void setZip(String zip) {
            this.zip=zip;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country=country;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName=firstName;
        }
        public String getMiddleName() {
            return middleName;
        }
        public void setMiddleName(String middleName) {
            this.middleName=middleName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName=lastName;
        }
        public String getAreaCode() {
            return areaCode;
        }
        public void setAreaCode(String areaCode) {
            this.areaCode=areaCode;
        }
        public String getPhone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone=phone;
        }
        public String getEmailAddress() {
            return emailAddress;
        }
        public void setEmailAddress(String emailAddress) {
            this.emailAddress=emailAddress;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new DealerInfo(
                        rs.getBigDecimal("VENDOR_ID"),
                        rs.getString("DEALER_NAME"),
                        rs.getString("SUPPLIER_CODE"),
                        rs.getBigDecimal("VENDOR_SITE_ID"),
                        rs.getString("DEALER_CODE"),
                        rs.getString("ADDRESS_LINE1"),
                        rs.getString("ADDRESS_LINE2"),
                        rs.getString("ADDRESS_LINE3"),
                        rs.getString("CITY"),
                        rs.getString("STATE"),
                        rs.getString("ZIP"),
                        rs.getString("COUNTRY"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("MIDDLE_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("AREA_CODE"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL_ADDRESS")
                    );
                }
            };
        }
        public String toString() {
            return "DealerInfo{" + "vendorId="+vendorId+", dealerName="+dealerName+", supplierCode="+supplierCode+", vendorSiteId="+vendorSiteId+", dealerCode="+dealerCode+", addressLine1="+addressLine1+", addressLine2="+addressLine2+", addressLine3="+addressLine3+", city="+city+", state="+state+", zip="+zip+", country="+country+", firstName="+firstName+", middleName="+middleName+", lastName="+lastName+", areaCode="+areaCode+", phone="+phone+", emailAddress="+emailAddress+'}';
        }
    }

    public static Object[] ittDealerCodeLov(String p_dealer_code){
        System.out.println("in ittDealerCodeLov "+p_dealer_code);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_DEALER_CODE_LOV);
                if (statement != null) {
                    statement.setObject(1, p_dealer_code, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),DealerCodeInfo.getRowMapper())};
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
        return null;
   }

    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_LOV(NULL,?)}
    (
    VENDOR_ID NUMBER,
    DEALER_NAME VARCHAR2,
    SUPPLIER_CODE VARCHAR2,
    VENDOR_SITE_ID NUMBER,
    DEALER_CODE VARCHAR2,
    ADDRESS_LINE1 VARCHAR2,
    ADDRESS_LINE2 VARCHAR2,
    ADDRESS_LINE3 VARCHAR2,
    CITY VARCHAR2,
    STATE VARCHAR2,
    ZIP VARCHAR2,
    COUNTRY VARCHAR2,
    FIRST_NAME VARCHAR2,
    MIDDLE_NAME VARCHAR2,
    LAST_NAME VARCHAR2,
    AREA_CODE VARCHAR2,
    PHONE VARCHAR2,
    EMAIL_ADDRESS VARCHAR2
    )
    */
    public static class DealerCodeInfo {
       private BigDecimal vendorId;
       private String dealerName;
       private String supplierCode;
       private BigDecimal vendorSiteId;
       private String dealerCode;
       private String addressLine1;
       private String addressLine2;
       private String addressLine3;
       private String city;
       private String state;
       private String zip;
       private String country;
       private String firstName;
       private String middleName;
       private String lastName;
       private String areaCode;
       private String phone;
       private String emailAddress;

        public DealerCodeInfo(){
        }
        public DealerCodeInfo(BigDecimal vendorId, 
                String dealerName, 
                String supplierCode, 
                BigDecimal vendorSiteId, 
                String dealerCode, 
                String addressLine1, 
                String addressLine2, 
                String addressLine3, 
                String city, 
                String state, 
                String zip, 
                String country, 
                String firstName, 
                String middleName, 
                String lastName, 
                String areaCode, 
                String phone, 
                String emailAddress){
            this.vendorId=vendorId;
            this.dealerName=dealerName;
            this.supplierCode=supplierCode;
            this.vendorSiteId=vendorSiteId;
            this.dealerCode=dealerCode;
            this.addressLine1=addressLine1;
            this.addressLine2=addressLine2;
            this.addressLine3=addressLine3;
            this.city=city;
            this.state=state;
            this.zip=zip;
            this.country=country;
            this.firstName=firstName;
            this.middleName=middleName;
            this.lastName=lastName;
            this.areaCode=areaCode;
            this.phone=phone;
            this.emailAddress=emailAddress;
        }
        public BigDecimal getVendorId() {
            return vendorId;
        }
        public void setVendorId(BigDecimal vendorId) {
            this.vendorId=vendorId;
        }
        public String getDealerName() {
            return dealerName;
        }
        public void setDealerName(String dealerName) {
            this.dealerName=dealerName;
        }
        public String getSupplierCode() {
            return supplierCode;
        }
        public void setSupplierCode(String supplierCode) {
            this.supplierCode=supplierCode;
        }
        public BigDecimal getVendorSiteId() {
            return vendorSiteId;
        }
        public void setVendorSiteId(BigDecimal vendorSiteId) {
            this.vendorSiteId=vendorSiteId;
        }
        public String getDealerCode() {
            return dealerCode;
        }
        public void setDealerCode(String dealerCode) {
            this.dealerCode=dealerCode;
        }
        public String getAddressLine1() {
            return addressLine1;
        }
        public void setAddressLine1(String addressLine1) {
            this.addressLine1=addressLine1;
        }
        public String getAddressLine2() {
            return addressLine2;
        }
        public void setAddressLine2(String addressLine2) {
            this.addressLine2=addressLine2;
        }
        public String getAddressLine3() {
            return addressLine3;
        }
        public void setAddressLine3(String addressLine3) {
            this.addressLine3=addressLine3;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city=city;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state=state;
        }
        public String getZip() {
            return zip;
        }
        public void setZip(String zip) {
            this.zip=zip;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country=country;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName=firstName;
        }
        public String getMiddleName() {
            return middleName;
        }
        public void setMiddleName(String middleName) {
            this.middleName=middleName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName=lastName;
        }
        public String getAreaCode() {
            return areaCode;
        }
        public void setAreaCode(String areaCode) {
            this.areaCode=areaCode;
        }
        public String getPhone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone=phone;
        }
        public String getEmailAddress() {
            return emailAddress;
        }
        public void setEmailAddress(String emailAddress) {
            this.emailAddress=emailAddress;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new DealerCodeInfo(
                        rs.getBigDecimal("VENDOR_ID"),
                        rs.getString("DEALER_NAME"),
                        rs.getString("SUPPLIER_CODE"),
                        rs.getBigDecimal("VENDOR_SITE_ID"),
                        rs.getString("DEALER_CODE"),
                        rs.getString("ADDRESS_LINE1"),
                        rs.getString("ADDRESS_LINE2"),
                        rs.getString("ADDRESS_LINE3"),
                        rs.getString("CITY"),
                        rs.getString("STATE"),
                        rs.getString("ZIP"),
                        rs.getString("COUNTRY"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("MIDDLE_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("AREA_CODE"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL_ADDRESS")
                    );
                }
            };
        }
        public String toString() {
            return "DealerCodeInfo{" + "vendorId="+vendorId+", dealerName="+dealerName+", supplierCode="+supplierCode+", vendorSiteId="+vendorSiteId+", dealerCode="+dealerCode+", addressLine1="+addressLine1+", addressLine2="+addressLine2+", addressLine3="+addressLine3+", city="+city+", state="+state+", zip="+zip+", country="+country+", firstName="+firstName+", middleName="+middleName+", lastName="+lastName+", areaCode="+areaCode+", phone="+phone+", emailAddress="+emailAddress+'}';
        }
    }	
	
    
   public static Object[] ittHeaderUpdate(String p_so_number,
    String p_itt_number,
    String p_user_name,
    String p_itt_status,
    String p_itt_admin_owner,
    String p_dealer,
    String p_dealer_whse_code,
    String p_dealer_address_line1,
    String p_dealer_address_line2,
    String p_dealer_address_line3,
    String p_dealer_city,
    String p_dealer_state,
    String p_dealer_zip,
    String p_dealer_country,
    String p_dealer_contact_number,
    String p_dealer_email,
    String p_cmap_yes_no,
    String p_product_sourced_by,
    String p_dealer_contact,
    String p_mail_invoices_to,
    String p_cust_contact_name,
    String p_cust_contact_phone,
    String p_cust_contact_email,
    String p_cust_contact_fax,
    String p_dealer_ship_to_cna_code,
    BigDecimal p_vendor_id,
    BigDecimal p_vendor_site_id,
    BigDecimal p_vendor_contact_id,
    String p_prnt_vnd_cd,
	String p_dealer_shipto_name,        //Added Raghavendra Uppala ITG#690711 Start
	String p_dealer_shipto_contact_name,     
	String p_dealer_shipto_phone,
    String p_dealer_shipto_email,
    String p_dealer_shipto_add_ln1,
    String p_dealer_shipto_add_ln2,
    String p_dealer_shipto_add_ln3,
    String p_dealer_shipto_city,
    String p_dealer_shipto_state,    
    String p_dealer_shipto_zip,
	String p_dealer_shipto_country,
    Timestamp p_req_delivery_date	//Added Raghavendra Uppala ITG#690711 End
    ){
        System.out.println("in ittHeaderUpdate "+p_so_number+"|"+p_itt_number+"|"+p_user_name+"|"+p_itt_status+"|"+p_itt_admin_owner+"|"+p_dealer+"|"+p_dealer_whse_code+"|"+p_dealer_address_line1+"|"+p_dealer_address_line2+"|"+p_dealer_address_line3+"|"+p_dealer_city+"|"+p_dealer_state+"|"+p_dealer_zip+"|"+p_dealer_country+"|"+p_dealer_contact_number+"|"+p_dealer_email+"|"+p_cmap_yes_no+"|"+p_product_sourced_by+"|"+p_dealer_contact+"|"+p_mail_invoices_to+"|"+p_cust_contact_name+"|"+p_cust_contact_phone+"|"+p_cust_contact_email+"|"+p_cust_contact_fax+"|"+p_dealer_ship_to_cna_code+"|"+p_vendor_id+"|"+p_vendor_site_id+"|"+p_vendor_contact_id+"|"+p_prnt_vnd_cd);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_HEADER_UPDATE);
                if (statement != null) {
                    statement.setObject(1, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_itt_status, OracleTypes.VARCHAR);
                    statement.setObject(5, p_itt_admin_owner, OracleTypes.VARCHAR);
                    statement.setObject(6, p_dealer, OracleTypes.VARCHAR);
                    statement.setObject(7, p_dealer_whse_code, OracleTypes.VARCHAR);
                    statement.setObject(8, p_dealer_address_line1, OracleTypes.VARCHAR);
                    statement.setObject(9, p_dealer_address_line2, OracleTypes.VARCHAR);
                    statement.setObject(10, p_dealer_address_line3, OracleTypes.VARCHAR);
                    statement.setObject(11, p_dealer_city, OracleTypes.VARCHAR);
                    statement.setObject(12, p_dealer_state, OracleTypes.VARCHAR);
                    statement.setObject(13, p_dealer_zip, OracleTypes.VARCHAR);
                    statement.setObject(14, p_dealer_country, OracleTypes.VARCHAR);
                    statement.setObject(15, p_dealer_contact_number, OracleTypes.VARCHAR);
                    statement.setObject(16, p_dealer_email, OracleTypes.VARCHAR);
                    statement.setObject(17, p_cmap_yes_no, OracleTypes.VARCHAR);
                    statement.setObject(18, null, OracleTypes.VARCHAR);
                    statement.setObject(19, p_dealer_contact, OracleTypes.VARCHAR);
                    statement.setObject(20, p_mail_invoices_to, OracleTypes.VARCHAR);
                    statement.setObject(21, p_cust_contact_name, OracleTypes.VARCHAR);
                    statement.setObject(22, p_cust_contact_phone, OracleTypes.VARCHAR);
                    statement.setObject(23, p_cust_contact_email, OracleTypes.VARCHAR);
                    statement.setObject(24, p_cust_contact_fax, OracleTypes.VARCHAR);
                    statement.setObject(25, p_dealer_ship_to_cna_code, OracleTypes.VARCHAR);
                    statement.setObject(26, p_vendor_id, OracleTypes.NUMBER);
                    statement.setObject(27, p_vendor_site_id, OracleTypes.NUMBER);
                    statement.setObject(28, null, OracleTypes.NUMBER); 
                    statement.setObject(29, p_prnt_vnd_cd, OracleTypes.VARCHAR); 
					statement.setObject(30, p_dealer_shipto_name, OracleTypes.VARCHAR);             //Added Raghavendra Uppala ITG#690711 Start
					statement.setObject(31, p_dealer_shipto_contact_name, OracleTypes.VARCHAR);      
                    statement.setObject(32, p_dealer_shipto_phone, OracleTypes.VARCHAR);
                    statement.setObject(33, p_dealer_shipto_email, OracleTypes.VARCHAR);
                    statement.setObject(34, p_dealer_shipto_add_ln1, OracleTypes.VARCHAR);
                    statement.setObject(35, p_dealer_shipto_add_ln2, OracleTypes.VARCHAR);
                    statement.setObject(36, p_dealer_shipto_add_ln3, OracleTypes.VARCHAR);
                    statement.setObject(37, p_dealer_shipto_city, OracleTypes.VARCHAR);
                    statement.setObject(38, p_dealer_shipto_state, OracleTypes.VARCHAR);
					statement.setObject(39, p_dealer_shipto_zip, OracleTypes.VARCHAR);
                    statement.setObject(40, p_dealer_shipto_country, OracleTypes.VARCHAR); 
                    statement.setObject(41, p_req_delivery_date, OracleTypes.TIMESTAMP);					//Added Raghavendra Uppala ITG#690711 End
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }
   
   public static Object[] ittGoodsLineUpdate(String p_itt_number,
    String p_line_number,
    String p_user_name,
    String p_itt_status,
    Timestamp p_delivery_scheduled_date,
    BigDecimal p_install_credit,
    BigDecimal p_finder_fee,
    String p_serial_number,
    String p_reason_code,
    String p_po_number,
    String p_product_sourced_by,
    BigDecimal p_item_purchase_price){
        System.out.println("in ittGoodsLineUpdate "+p_itt_number+"|"+p_line_number+"|"+p_user_name+"|"+p_itt_status+"|"+p_delivery_scheduled_date+"|"+p_install_credit+"|"+p_finder_fee+"|"+p_serial_number+"|"+p_reason_code+"|"+p_po_number+"|"+p_product_sourced_by+"|"+p_item_purchase_price);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_GOODS_LINE_UPDATE);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_line_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_itt_status, OracleTypes.VARCHAR);
                    statement.setObject(5, p_delivery_scheduled_date, OracleTypes.TIMESTAMP);
                    statement.setObject(6, p_install_credit, OracleTypes.NUMBER);
                    statement.setObject(7, p_finder_fee, OracleTypes.NUMBER);
                    statement.setObject(8, p_serial_number, OracleTypes.VARCHAR);
                    statement.setObject(9, p_reason_code, OracleTypes.VARCHAR);
                    statement.setObject(10, p_po_number, OracleTypes.VARCHAR);
                    statement.setObject(11, p_product_sourced_by, OracleTypes.VARCHAR);
                    statement.setObject(12, p_item_purchase_price, OracleTypes.NUMBER);
                    statement.registerOutParameter(13, OracleTypes.VARCHAR);
                    statement.registerOutParameter(14, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("ITT_GOODS_LINE_UPDATE execution successful:");
                    return new Object[]{statement.getObject(13)
                        ,statement.getObject(14)};
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
        return null;
   }
   
   public static Object[] ittExpenseLineUpdate(String p_itt_number,
    String p_line_number,
    String p_user_name,
    String p_item,
    String p_item_description,
    BigDecimal p_ordered_quantity,
    BigDecimal p_item_purchase_price,
    BigDecimal p_install_credit,
    BigDecimal p_finder_fee,
    String p_po_number){
        System.out.println("in ittExpenseLineUpdate "+p_itt_number+"|"+p_line_number+"|"+p_user_name+"|"+p_item+"|"+p_item_description+"|"+p_ordered_quantity+"|"+p_item_purchase_price+"|"+p_install_credit+"|"+p_finder_fee+"|"+p_po_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_EXPENSE_LINE_UPDATE);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_line_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_item, OracleTypes.VARCHAR);
                    statement.setObject(5, p_item_description, OracleTypes.VARCHAR);
                    statement.setObject(6, p_ordered_quantity, OracleTypes.NUMBER);
                    statement.setObject(7, p_item_purchase_price, OracleTypes.NUMBER);
                    statement.setObject(8, p_install_credit, OracleTypes.NUMBER);
                    statement.setObject(9, p_finder_fee, OracleTypes.NUMBER);
                    statement.setObject(10, p_po_number, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }
   
   public static Object[] ittExpenseLineInsert(String p_so_number,
    String p_itt_number,
    String p_user_name,
    String p_item,
    String p_item_description,
    BigDecimal p_ordered_quantity,
    BigDecimal p_item_purchase_price,
    BigDecimal p_install_credit,
    BigDecimal p_finder_fee){
        System.out.println("in ittExpenseLineInsert "+p_so_number+"|"+p_itt_number+"|"+p_user_name+"|"+p_item+"|"+p_item_description+"|"+p_ordered_quantity+"|"+p_item_purchase_price+"|"+p_install_credit+"|"+p_finder_fee);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_EXPENSE_LINE_INSERT);
                if (statement != null) {
                    statement.setObject(1, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_item, OracleTypes.VARCHAR);
                    statement.setObject(5, p_item_description, OracleTypes.VARCHAR);
                    statement.setObject(6, p_ordered_quantity, OracleTypes.NUMBER);
                    statement.setObject(7, p_item_purchase_price, OracleTypes.NUMBER);
                    statement.setObject(8, p_install_credit, OracleTypes.NUMBER);
                    statement.setObject(9, p_finder_fee, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }
   
   public static Object[] ittContractTypeLov(){
        System.out.println("in ittContractTypeLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_CONTRACT_TYPE_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }   
   
   public static Object[] ittPlanTypeLov(){
        System.out.println("in ittPlanTypeLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_PLAN_TYPE_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }   
   
   public static Object[] ittBillCycleLov(){
        System.out.println("in ittBillCycleLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_BILL_CYCLE_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }

   public static Object[] ittPricingDetails(String p_itt_number){
        System.out.println("in ittPricingDetails "+p_itt_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_PRICING_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),PricingDetail.getRowMapper())
                        ,statement.getObject(3)};
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
        return null;
   }
   

    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_PRICING_DETAILS( 'TEST1456-ITT-01',?,?)}
    (
    ROW_NUM NUMBER,
    EQUIP_MODEL VARCHAR2,
    QUANTITY NUMBER,
    CONTRACT_TYPE VARCHAR2,
    METER_TYPE VARCHAR2,
    OVERAGE_RATE NUMBER,
    PLAN_TYPE VARCHAR2,
    TERM NUMBER,
    BASE_PRICE NUMBER,
    BASE_BILL_CYCLE VARCHAR2,
    OVERAGE_BILL_CYCLE VARCHAR2,
    COPY_INCLUSION NUMBER,
    MULTIPLIER NUMBER
    )
    */
    public static class PricingDetail {
       private BigDecimal rowNum;
       private String equipModel;
       private BigDecimal quantity;
       private String contractType;
       private String meterType;
       private BigDecimal overageRate;
       private String planType;
       private BigDecimal term;
       private BigDecimal basePrice;
       private String baseBillCycle;
       private String overageBillCycle;
       private BigDecimal copyInclusion;
       private BigDecimal multiplier;
	   private BigDecimal monthlyAddComp;    //Added Raghavendra Uppala ITG#690711
	   private BigDecimal monthlyAdminComp;  //Added Raghavendra Uppala ITG#690711 
	   private String declineMaint;  //ITG#690711 
	   private String configBundle;  //ITG#728624

        public PricingDetail(){
        }
        public PricingDetail(BigDecimal rowNum, 
                String equipModel, 
                BigDecimal quantity, 
                String contractType, 
                String meterType, 
                BigDecimal overageRate, 
                String planType, 
                BigDecimal term, 
                BigDecimal basePrice, 
                String baseBillCycle, 
                String overageBillCycle, 
                BigDecimal copyInclusion, 
                BigDecimal multiplier,
				BigDecimal monthlyAddComp,    //Added Raghavendra Uppala ITG#690711
				BigDecimal monthlyAdminComp,   //Added Raghavendra Uppala ITG#690711
				String declineMaint, //ITG#690711 
				String configBundle //ITG#728624
                ){
            this.rowNum=rowNum;
            this.equipModel=equipModel;
            this.quantity=quantity;
            this.contractType=contractType;
            this.meterType=meterType;
            this.overageRate=overageRate;
            this.planType=planType;
            this.term=term;
            this.basePrice=basePrice;
            this.baseBillCycle=baseBillCycle;
            this.overageBillCycle=overageBillCycle;
            this.copyInclusion=copyInclusion;
            this.multiplier=multiplier;
			this.monthlyAddComp=monthlyAddComp;        //Added Raghavendra Uppala ITG#690711
			this.monthlyAdminComp=monthlyAdminComp;    //Added Raghavendra Uppala ITG#690711 
			this.declineMaint=declineMaint; //ITG#690711 
			this.configBundle=configBundle; //ITG#728624
        }
        public BigDecimal getRowNum() {
            return rowNum;
        }
        public void setRowNum(BigDecimal rowNum) {
            this.rowNum=rowNum;
        }
        public String getEquipModel() {
            return equipModel;
        }
        public void setEquipModel(String equipModel) {
            this.equipModel=equipModel;
        }
        public BigDecimal getQuantity() {
            return quantity;
        }
        public void setQuantity(BigDecimal quantity) {
            this.quantity=quantity;
        }
        public String getContractType() {
            return contractType;
        }
        public void setContractType(String contractType) {
            this.contractType=contractType;
        }
        public String getMeterType() {
            return meterType;
        }
        public void setMeterType(String meterType) {
            this.meterType=meterType;
        }
        public BigDecimal getOverageRate() {
            return overageRate;
        }
        public void setOverageRate(BigDecimal overageRate) {
            this.overageRate=overageRate;
        }
        public String getPlanType() {
            return planType;
        }
        public void setPlanType(String planType) {
            this.planType=planType;
        }
        public BigDecimal getTerm() {
            return term;
        }
        public void setTerm(BigDecimal term) {
            this.term=term;
        }
        public BigDecimal getBasePrice() {
            return basePrice;
        }
        public void setBasePrice(BigDecimal basePrice) {
            this.basePrice=basePrice;
        }
        public String getBaseBillCycle() {
            return baseBillCycle;
        }
        public void setBaseBillCycle(String baseBillCycle) {
            this.baseBillCycle=baseBillCycle;
        }
        public String getOverageBillCycle() {
            return overageBillCycle;
        }
        public void setOverageBillCycle(String overageBillCycle) {
            this.overageBillCycle=overageBillCycle;
        }
        public BigDecimal getCopyInclusion() {
            return copyInclusion;
        }
        public void setCopyInclusion(BigDecimal copyInclusion) {
            this.copyInclusion=copyInclusion;
        }
        public BigDecimal getMultiplier() {
            return multiplier;
        }
        public void setMultiplier(BigDecimal multiplier) {
            this.multiplier=multiplier;
        }
		public BigDecimal getMonthlyAddComp() {         //Added Raghavendra Uppala ITG#690711 Start
            return monthlyAddComp;
        }
        public void setMonthlyAddComp(BigDecimal monthlyAddComp) {
            this.monthlyAddComp=monthlyAddComp;
        }
		public BigDecimal getMonthlyAdminComp() {
            return monthlyAdminComp;
        }
        public void setMonthlyAdminComp(BigDecimal monthlyAdminComp) {
            this.monthlyAdminComp=monthlyAdminComp;
        }                 
		public String getDeclineMaint() {
            return declineMaint;
        }
        public void setDeclineMaint(String declineMaint) {
            this.declineMaint=declineMaint;
        }		//Added Raghavendra Uppala ITG#690711 End
		public String getConfigBundle() {
            return configBundle;
        }		
        public void setConfigBundle(String configBundle) {
            this.configBundle=configBundle;
        }		//ITG#728624
        
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new PricingDetail(
                        rs.getBigDecimal("ROW_NUM"),
                        rs.getString("EQUIP_MODEL"),
                        rs.getBigDecimal("QUANTITY"),
                        rs.getString("CONTRACT_TYPE"),
                        rs.getString("METER_TYPE"),
                        rs.getBigDecimal("OVERAGE_RATE"),
                        rs.getString("PLAN_TYPE"),
                        rs.getBigDecimal("TERM"),
                        rs.getBigDecimal("BASE_PRICE"),
                        rs.getString("BASE_BILL_CYCLE"),
                        rs.getString("OVERAGE_BILL_CYCLE"),
                        rs.getBigDecimal("COPY_INCLUSION"),
                        rs.getBigDecimal("MULTIPLIER"),
                        new BigDecimal(0),new BigDecimal(0),"",""//TODO
//						rs.getBigDecimal("MONTHLY_ADDITIONAL_COMP"),          //Added Raghavendra Uppala ITG#690711 
//						rs.getBigDecimal("MONTHLY_ADMIN_COMP") ,               //Added Raghavendra Uppala ITG#690711
//						rs.getString("DECLINE_MAINTENANCE") ,				//ITG#690711
//						rs.getString("CONFIGURATION_BUNDLE") 			//ITG#728624			
                    );
                }
            };
        }
        public String toString() {
            return "PricingDetail{" + "rowNum="+rowNum+", equipModel="+equipModel+", quantity="+quantity+", contractType="+contractType+", meterType="+meterType+", overageRate="+overageRate+", planType="+planType+", term="+term+", basePrice="+basePrice+", baseBillCycle="+baseBillCycle+", overageBillCycle="+overageBillCycle+", copyInclusion="+copyInclusion+", multiplier="+multiplier+",declineMaint="+declineMaint+",configBundle="+configBundle+'}';
        }
    }


    // ITG#690711	 start
   
    public static Object[] ittDealerCompDetails(String p_header_id, String p_line_id,String p_itt_number,
  											  String p_item,String p_line_number)
  	{
          System.out.println("in ittDealerCompDetails "+p_itt_number+"|"+p_item+"|"+p_line_number);
          CallableStatement statement = null;
          Connection connection = null;
          try {
              connection = TransactionScope.getConnection();
              if (connection != null) {
                  statement = (CallableStatement) connection.prepareCall(ITT_DEALER_COMP_DETAILS);
                  if (statement != null) {
                      statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
  					statement.setObject(2, p_item, OracleTypes.VARCHAR);
  					statement.setObject(3, p_line_number, OracleTypes.VARCHAR);
  					statement.setObject(4, p_header_id, OracleTypes.VARCHAR);
  					statement.setObject(5, p_line_id, OracleTypes.VARCHAR);					
                      statement.registerOutParameter(6, OracleTypes.CURSOR);
                      statement.registerOutParameter(7, OracleTypes.NUMBER);
                      statement.execute();
                      return new Object[]{cursorToList((ResultSet)statement.getObject(6),DealerCompDetail.getRowMapper())
                          ,statement.getObject(7)};
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
          return null;
     }
     

      public static class DealerCompDetail {
         private BigDecimal rowNum;
         private BigDecimal serviceComp;
         private BigDecimal findingComp;
         private BigDecimal originalComp;
         private BigDecimal orderTakeComp;
         private BigDecimal installComp;
         private BigDecimal renewalComp;
         private BigDecimal removalComp;
         private BigDecimal extWarrantyComp;
  	   private String serviceDlrCode;
         private String serviceDlr;
  	   private String findingDlrCode;
         private String findingDlr;
  	   private String originalDlrCode;
         private String originalDlr;
  	   private String orderTakeDlrCode;
         private String orderTakeDlr;
  	   private String installCompDlrCode;
         private String installCompDlr;
  	   private String renewalCompDlrCode;
         private String renewalCompDlr;
  	   private String removalCompDlrCode;
         private String removalCompDlr;
  	   private String extWarrantyCompDlrCode;
         private String extWarrantyCompDlr;	   
  	   

          public DealerCompDetail(){
          }
          public DealerCompDetail(BigDecimal rowNum, 
                  BigDecimal serviceComp, 
                  BigDecimal findingComp, 
                  BigDecimal originalComp, 
                  BigDecimal orderTakeComp, 
                  BigDecimal installComp, 
                  BigDecimal renewalComp, 
                  BigDecimal removalComp, 
                  BigDecimal extWarrantyComp,
  				String serviceDlrCode,
  				String serviceDlr,
  				String findingDlrCode,
                  String findingDlr,
  	            String originalDlrCode,
                  String originalDlr,
  	            String orderTakeDlrCode,
                  String orderTakeDlr,
  	            String installCompDlrCode,
                  String installCompDlr,
  	            String renewalCompDlrCode,
                  String renewalCompDlr,
  	            String removalCompDlrCode,
                  String removalCompDlr,
  	            String extWarrantyCompDlrCode,
                  String extWarrantyCompDlr
  				){
              this.rowNum=rowNum;
              this.serviceComp=serviceComp;
              this.findingComp=findingComp;
              this.originalComp=originalComp;
              this.orderTakeComp=orderTakeComp;
              this.installComp=installComp;
              this.renewalComp=renewalComp;
              this.removalComp=removalComp;
              this.extWarrantyComp=extWarrantyComp;
  			this.serviceDlrCode=serviceDlrCode;
              this.serviceDlr=serviceDlr;
              this.findingDlrCode=findingDlrCode;
              this.findingDlr=findingDlr;
              this.originalDlrCode=originalDlrCode;
              this.originalDlr=originalDlr;
              this.orderTakeDlrCode=orderTakeDlrCode;
              this.orderTakeDlr=orderTakeDlr;
              this.installCompDlrCode=installCompDlrCode;
  			this.installCompDlr=installCompDlr;
  			this.renewalCompDlrCode=renewalCompDlrCode;
              this.renewalCompDlr=renewalCompDlr;
              this.removalCompDlrCode=removalCompDlrCode;
              this.removalCompDlr=removalCompDlr;
              this.extWarrantyCompDlrCode=extWarrantyCompDlrCode;
  			this.extWarrantyCompDlr=extWarrantyCompDlr;
          }
          public BigDecimal getRowNum() {
              return rowNum;
          }
          public void setRowNum(BigDecimal rowNum) {
              this.rowNum=rowNum;
          }
          public BigDecimal getServiceComp() {
              return serviceComp;
          }
          public void setServiceComp(BigDecimal serviceComp) {
              this.serviceComp=serviceComp;
          }        
          public BigDecimal getFindingComp() {
              return findingComp;
          }
          public void setFindingComp(BigDecimal findingComp) {
              this.findingComp=findingComp;
          }
          public BigDecimal getOriginalComp() {
              return originalComp;
          }
          public void setOriginalComp(BigDecimal originalComp) {
              this.originalComp=originalComp;
          }        
          public BigDecimal getOrderTakeComp() {
              return orderTakeComp;
          }
          public void setOrderTakeComp(BigDecimal orderTakeComp) {
              this.orderTakeComp=orderTakeComp;
          }
          public BigDecimal getInstallComp() {
              return installComp;
          }
          public void setInstallComp(BigDecimal installComp) {
              this.installComp=installComp;
          }
          public BigDecimal getRenewalComp() {
              return renewalComp;
          }
          public void setRenewalComp(BigDecimal renewalComp) {
              this.renewalComp=renewalComp;
          }
          public BigDecimal getRemovalComp() {
              return removalComp;
          }
          public void setRemovalComp(BigDecimal removalComp) {
              this.removalComp=removalComp;
          }
          public BigDecimal getExtWarrantyComp() {
              return extWarrantyComp;
          }
          public void setExtWarrantyComp(BigDecimal extWarrantyComp) {
              this.extWarrantyComp=extWarrantyComp;
          }
  		public String getServiceDlrCode() {
              return serviceDlrCode;
          }
          public void setServiceDlrCode(String serviceDlrCode) {
              this.serviceDlrCode=serviceDlrCode;
          }
          public String getServiceDlr() {
              return serviceDlr;
          }
          public void setServiceDlr(String serviceDlr) {
              this.serviceDlr=serviceDlr;
          }
  		public String getFindingDlrCode() {
              return findingDlrCode;
          }
          public void setFindingDlrCode(String findingDlrCode) {
              this.findingDlrCode=findingDlrCode;
          }
          public String getFindingDlr() {
              return findingDlr;
          }
          public void setFindingDlr(String findingDlr) {
              this.findingDlr=findingDlr;
          }
  		public String getOriginalDlrCode() {
              return originalDlrCode;
          }
          public void setOriginalDlrCode(String originalDlrCode) {
              this.originalDlrCode=originalDlrCode;
          }
  		public String getOriginalDlr() {
              return originalDlr;
          }
          public void setOriginalDlr(String originalDlr) {
              this.originalDlr=originalDlr;
          }
  		public String getOrderTakeDlrCode() {
              return orderTakeDlrCode;
          }
          public void setOrderTakeDlrCode(String orderTakeDlrCode) {
              this.orderTakeDlrCode=orderTakeDlrCode;
          }
  		public String getOrderTakeDlr() {
              return orderTakeDlr;
          }
          public void setOrderTakeDlr(String orderTakeDlr) {
              this.orderTakeDlr=orderTakeDlr;
          }
  		public String getInstallCompDlrCode() {
              return installCompDlrCode;
          }
          public void setInstallCompDlrCode(String installCompDlrCode) {
              this.installCompDlrCode=installCompDlrCode;
          }
  		public String getInstallCompDlr() {
              return installCompDlr;
          }
          public void setInstallCompDlr(String installCompDlr) {
              this.installCompDlr=installCompDlr;
          }
  		public String getRenewalCompDlrCode() {
              return renewalCompDlrCode;
          }
          public void setRenewalCompDlrCode(String renewalCompDlrCode) {
              this.renewalCompDlrCode=renewalCompDlrCode;
          }
  		public String getRenewalCompDlr() {
              return renewalCompDlr;
          }
          public void setRenewalCompDlr(String renewalCompDlr) {
              this.renewalCompDlr=renewalCompDlr;
          }
  		public String getRemovalCompDlrCode() {
              return removalCompDlrCode;
          }
          public void setRemovalCompDlrCode(String removalCompDlrCode) {
              this.removalCompDlrCode=removalCompDlrCode;
          }
  		public String getRemovalCompDlr() {
              return removalCompDlr;
          }
          public void setRemovalCompDlr(String removalCompDlr) {
              this.removalCompDlr=removalCompDlr;
          }
  		public String getExtWarrantyCompDlrCode() {
              return extWarrantyCompDlrCode;
          }
          public void setExtWarrantyCompDlrCode(String extWarrantyCompDlrCode) {
              this.extWarrantyCompDlrCode=extWarrantyCompDlrCode;
          }
  		public String getExtWarrantyCompDlr() {
              return extWarrantyCompDlr;
          }
          public void setExtWarrantyCompDlr(String extWarrantyCompDlr) {
              this.extWarrantyCompDlr=extWarrantyCompDlr;
          }
  		
          public static CanonE580RowMapper getRowMapper(){
              return new CanonE580RowMapper() {
                  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                      return new DealerCompDetail(
                          rs.getBigDecimal("ROWNUM"),
                          rs.getBigDecimal("SERVICE_DEALER_COMP"),
                          rs.getBigDecimal("FINDING_DEALER_COMP"),
                          rs.getBigDecimal("ORIGINAL_DEALER_COMP"),
                          rs.getBigDecimal("ORDER_TAKE_DEALER_COMP"),
                          rs.getBigDecimal("INSTALL_COMP"),
                          rs.getBigDecimal("RENEWAL_COMP"),
                          rs.getBigDecimal("REMOVAL_COMP"),
                          rs.getBigDecimal("EXT_WARRANTY_COMP"),
  						rs.getString("SERVICE_DLR_CODE"),
                          rs.getString("SERVICE_DLR"),
  						rs.getString("FINDING_DLR_CODE"),
                          rs.getString("FINDING_DLR"),
  						rs.getString("ORIGINAL_DLR_CODE"),
                          rs.getString("ORIGINAL_DLR"),
  						rs.getString("ORDER_TAKE_DLR_CODE"),
                          rs.getString("ORDER_TAKE_DLR"),
  						rs.getString("INSTALL_COMP_DLR_CODE"),
                          rs.getString("INSTALL_COMP_DLR"),
  						rs.getString("RENEWAL_COMP_DLR_CODE"),
                          rs.getString("RENEWAL_COMP_DLR"),
  						rs.getString("REMOVAL_COMP_DLR_CODE"),
                          rs.getString("REMOVAL_COMP_DLR"),
  						rs.getString("EXT_WARRANTY_COMP_DLR_CODE"),
                          rs.getString("EXT_WARRANTY_COMP_DLR")
                      );
                  }
              };
          }
          public String toString() {
              return "DealerCompDetail{" + "rowNum="+rowNum+", serviceComp="+serviceComp+", findingComp="+findingComp+", originalComp="+originalComp+", orderTakeComp="+orderTakeComp+", installComp="+installComp+", renewalComp="+renewalComp+", removalComp="+removalComp+", extWarrantyComp="+extWarrantyComp+'}';
          }
      }

    public static Object[] ittDealerTotalComp(String p_itt_number)
  	{
          System.out.println("in ittDealerTotalComp "+p_itt_number);
          CallableStatement statement = null;
          Connection connection = null;
          try {
              connection = TransactionScope.getConnection();
              if (connection != null) {
                  statement = (CallableStatement) connection.prepareCall(ITT_DEALER_TOTAL_COMP);
                  if (statement != null) {
                      statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);				
                      statement.registerOutParameter(2, OracleTypes.CURSOR);
                      statement.execute();
                      return new Object[]{cursorToList((ResultSet)statement.getObject(2),DealerCompTotal.getRowMapper())};
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
          return null;
     }   

      public static class DealerCompTotal {
  	   private String dealerCode;
         private String dealer;
  	   private BigDecimal totalcomp;  
  	   private BigDecimal totalmaintcomp;   	   
  	   

          public DealerCompTotal(){
          }
          public DealerCompTotal(
  		        String dealerCode,
  		        String dealer,
  		        BigDecimal totalcomp,
  				BigDecimal totalmaintcomp
  				){
              this.dealerCode=dealerCode;
              this.dealer=dealer;
              this.totalcomp=totalcomp;
  			this.totalmaintcomp=totalmaintcomp;
          }
  		
  		public String getDealerCode() {
              return dealerCode;
          }
          public void setDealerCode(String dealerCode) {
              this.dealerCode=dealerCode;
          }
  		
          public String getDealer() {
              return dealer;
          }
          public void setDealer(String dealer) {
              this.dealer=dealer;
          }
  		
          public BigDecimal getTotalComp() {
              return totalcomp;
          }
          public void setTotalComp(BigDecimal totalcomp) {
              this.totalcomp=totalcomp;
          }
  		
          public BigDecimal getTotalMaintComp() {
              return totalmaintcomp;
          }
          public void setTotalMaintComp(BigDecimal totalmaintcomp) {
              this.totalmaintcomp=totalmaintcomp;
          }				
  		
          public static CanonE580RowMapper getRowMapper(){
              return new CanonE580RowMapper() {
                  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                      return new DealerCompTotal(
  					    rs.getString("DEALER_CODE"),
  						rs.getString("DEALER"),
                          rs.getBigDecimal("TOTAL_COMP"),
  						rs.getBigDecimal("TOTAL_MAINTENANCE_COMP")
                      );
                  }
              };
          }
          public String toString() {
              return "DealerCompTotal{" + "dealerCode="+dealerCode+", dealer="+dealer+", totalcomp="+totalcomp+", totalmaintcomp="+totalmaintcomp+'}';
          }
      }

  	public static Object[] ittDealerCompChk(String p_itt_number,String p_mode)
  	{
          System.out.println("in ittDealerCompChk "+p_itt_number);
          CallableStatement statement = null;
          Connection connection = null;
          try {
              connection = TransactionScope.getConnection();
              if (connection != null) {
                  statement = (CallableStatement) connection.prepareCall(ITT_DEALER_COMP_CHK);
                  if (statement != null) {
                      statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);	
                      statement.setObject(2, p_mode, OracleTypes.VARCHAR);						
                      statement.registerOutParameter(3, OracleTypes.CURSOR);
                      statement.execute();
                      return new Object[]{cursorToList((ResultSet)statement.getObject(3),DlrCompChk.getRowMapper())};					
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
          return null;
     }
     
      public static class DlrCompChk {
  	   private String linenum;
         private String itemcode;   
  	   

          public DlrCompChk(){
          }
          public DlrCompChk(
  		        String linenum,
  		        String itemcode
  				){
              this.linenum=linenum;
              this.itemcode=itemcode;
          }
  		public String getLineNum() {
              return linenum;
          }
          public void setLineNum(String linenum) {
              this.linenum=linenum;
          }
          public String getItemCode() {
              return itemcode;
          }
          public void setItemCode(String itemcode) {
              this.itemcode=itemcode;
          }		
  		
          public static CanonE580RowMapper getRowMapper(){
              return new CanonE580RowMapper() {
                  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                      return new DlrCompChk(
  					    rs.getString("LINE_NUMBER"),
  						rs.getString("ITEM")
                      );
                  }
              };
          }
          public String toString() {
              return "DlrCompChk{" + "linenum="+linenum+", itemcode="+itemcode+'}';
          }
      }
  	
    public static Object[] ittDealerCompExtract(String p_itt_number)
  	{
          System.out.println("in ittDealerCompExtract "+p_itt_number);
          CallableStatement statement = null;
          Connection connection = null;
          try {
              connection = TransactionScope.getConnection();
              if (connection != null) {
                  statement = (CallableStatement) connection.prepareCall(ITT_DLRCOMP_EXTRACT);
                  if (statement != null) {
                      statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);				
                      statement.registerOutParameter(2, OracleTypes.CURSOR);
                      statement.execute();
                      return new Object[]{cursorToList((ResultSet)statement.getObject(2),DealerCompExtract.getRowMapper())};
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
          return null;
     }
     

      public static class DealerCompExtract {
  	   private BigDecimal linenumber;	
  	   private String itemcode;
  	   private String dealerCode;
         private String dealer;
         private BigDecimal serviceComp;
         private BigDecimal findingComp;
         private BigDecimal originalComp;
         private BigDecimal orderTakeComp;
         private BigDecimal installComp;
         private BigDecimal renewalComp;
         private BigDecimal removalComp;
         private BigDecimal extWarrantyComp;	   
  	   

          public DealerCompExtract(){
          }
          public DealerCompExtract(
  		        BigDecimal linenumber,
  				String itemcode,
  		        String dealerCode,
  		        String dealer,
  		        BigDecimal serviceComp,
  				BigDecimal findingComp,
  				BigDecimal originalComp,
  				BigDecimal orderTakeComp,
  				BigDecimal installComp,
  				BigDecimal renewalComp,
  				BigDecimal removalComp,
  				BigDecimal extWarrantyComp
  				){
  			this.linenumber=linenumber;
  			this.itemcode=itemcode;
              this.dealerCode=dealerCode;
              this.dealer=dealer;
              this.serviceComp=serviceComp;
  			this.findingComp=findingComp;
  			this.originalComp=originalComp;
  			this.orderTakeComp=orderTakeComp;
  			this.installComp=installComp;
  			this.renewalComp=renewalComp;
  			this.removalComp=removalComp;
  			this.extWarrantyComp=extWarrantyComp;	
          }
          public BigDecimal getLineNumber() {
              return linenumber;
          }
          public void setLineNumber(BigDecimal linenumber) {
              this.linenumber=linenumber;
          }
  		public String getItemCode() {
              return itemcode;
          }
          public void setItemCode(String itemcode) {
              this.itemcode=itemcode;
          }		
  		public String getDealerCode() {
              return dealerCode;
          }
          public void setDealerCode(String dealerCode) {
              this.dealerCode=dealerCode;
          }
          public String getDealer() {
              return dealer;
          }
          public void setDealer(String dealer) {
              this.dealer=dealer;
          }	
  		public BigDecimal getServiceComp() {
              return serviceComp;
          }
          public void setServiceComp(BigDecimal serviceComp) {
              this.serviceComp=serviceComp;
          }        
          public BigDecimal getFindingComp() {
              return findingComp;
          }
          public void setFindingComp(BigDecimal findingComp) {
              this.findingComp=findingComp;
          }
          public BigDecimal getOriginalComp() {
              return originalComp;
          }
          public void setOriginalComp(BigDecimal originalComp) {
              this.originalComp=originalComp;
          }        
          public BigDecimal getOrderTakeComp() {
              return orderTakeComp;
          }
          public void setOrderTakeComp(BigDecimal orderTakeComp) {
              this.orderTakeComp=orderTakeComp;
          }
          public BigDecimal getInstallComp() {
              return installComp;
          }
          public void setInstallComp(BigDecimal installComp) {
              this.installComp=installComp;
          }
          public BigDecimal getRenewalComp() {
              return renewalComp;
          }
          public void setRenewalComp(BigDecimal renewalComp) {
              this.renewalComp=renewalComp;
          }
          public BigDecimal getRemovalComp() {
              return removalComp;
          }
          public void setRemovalComp(BigDecimal removalComp) {
              this.removalComp=removalComp;
          }
          public BigDecimal getExtWarrantyComp() {
              return extWarrantyComp;
          }
          public void setExtWarrantyComp(BigDecimal extWarrantyComp) {
              this.extWarrantyComp=extWarrantyComp;
          }		
  		
          public static CanonE580RowMapper getRowMapper(){
              return new CanonE580RowMapper() {
                  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                      return new DealerCompExtract(
  					    rs.getBigDecimal("LINE_NUMBER"),
  					    rs.getString("ITEM_CODE"),
  					    rs.getString("DEALER_CODE"),
  						rs.getString("DEALER"),
                          rs.getBigDecimal("SERVICE_DEALER_COMP"),
                          rs.getBigDecimal("FINDING_DEALER_COMP"),
                          rs.getBigDecimal("ORIGINAL_DEALER_COMP"),
                          rs.getBigDecimal("ORDER_TAKE_DEALER_COMP"),
                          rs.getBigDecimal("INSTALL_COMP"),
                          rs.getBigDecimal("RENEWAL_COMP"),
                          rs.getBigDecimal("REMOVAL_COMP"),
                          rs.getBigDecimal("EXT_WARRANTY_COMP")
                      );
                  }
              };
          }
          public String toString() {
              return "DealerCompExtract{" + "linenumber="+linenumber+", itemcode="+itemcode+", dealerCode="+dealerCode+", dealer="+dealer+", serviceComp="+serviceComp+", findingComp="+findingComp+", originalComp="+originalComp+", orderTakeComp="+orderTakeComp+", installComp="+installComp+", renewalComp="+renewalComp+", removalComp="+removalComp+", extWarrantyComp="+extWarrantyComp+'}';
          }
      }
  	
   // ITG#690711	 end
       

   public static Object[] ittUpdatePricing(String p_itt_number,
    String p_user_name,
    String p_equip_model,
    String p_meter_type,
    String p_contract_type,
    BigDecimal p_overage_rate,
    String p_plan_type,
    BigDecimal p_term,
    BigDecimal p_base_price,
    String p_base_bill_cycle,
    String p_overage_bill_cycle,
    BigDecimal p_copy_inclusion,
    BigDecimal p_multiplier,
	BigDecimal p_mon_add_comp,                   //Added Raghavendra Uppala ITG#690711
	BigDecimal p_mon_admin_comp                  //Added Raghavendra Uppala ITG#690711
	  ){
        System.out.println("in ittUpdatePricing "+p_itt_number+"|"+p_user_name+"|"+p_equip_model+"|"+p_meter_type+"|"+p_contract_type+"|"+p_overage_rate+"|"+p_plan_type+"|"+p_term+"|"+p_base_price+"|"+p_base_bill_cycle+"|"+p_overage_bill_cycle+"|"+p_copy_inclusion+"|"+p_multiplier+"|"+p_mon_add_comp+"|"+p_mon_admin_comp);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_UPDATE_PRICING);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_equip_model, OracleTypes.VARCHAR);
                    statement.setObject(4, p_meter_type, OracleTypes.VARCHAR);
                    statement.setObject(5, p_contract_type, OracleTypes.VARCHAR);
                    statement.setObject(6, p_overage_rate, OracleTypes.NUMBER);
                    statement.setObject(7, p_plan_type, OracleTypes.VARCHAR);
                    statement.setObject(8, p_term, OracleTypes.NUMBER);
                    statement.setObject(9, p_base_price, OracleTypes.NUMBER);
                    statement.setObject(10, p_base_bill_cycle, OracleTypes.VARCHAR);
                    statement.setObject(11, p_overage_bill_cycle, OracleTypes.VARCHAR);
                    statement.setObject(12, p_copy_inclusion, OracleTypes.NUMBER);
                    statement.setObject(13, p_multiplier, OracleTypes.NUMBER);
					statement.setObject(14, p_mon_add_comp, OracleTypes.NUMBER);        //Added Raghavendra Uppala ITG#690711
					statement.setObject(15, p_mon_admin_comp, OracleTypes.NUMBER);      //Added Raghavendra Uppala ITG#690711
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }
   
   public static Object[] ittNotesInsert(String p_so_number,
    String p_itt_number,
    String p_user_name,
    String p_notes,
    String p_add_to_popdf){
        System.out.println("in ittNotesInsert "+p_so_number+"|"+p_itt_number+"|"+p_user_name+"|"+p_notes+"|"+p_add_to_popdf);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_NOTES_INSERT);
                if (statement != null) {
                    statement.setObject(1, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_notes, OracleTypes.VARCHAR);
                    statement.setObject(5, p_add_to_popdf, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }
   
   //Added Raghavendra Uppala ITG#690711 Start
   public static Object[] ittDealerCompInsert(String p_header_id,
											  String p_line_id,
											  String p_itt_number,          
											  String p_user_name,
											  String p_so_number,
											  String p_line_number,
											  String p_item,
											  BigDecimal p_Service_Comp,
											  BigDecimal p_Finding_Comp,
											  BigDecimal p_Original_Comp,
											  BigDecimal p_OrderTake_Comp,
											  BigDecimal p_Install_Comp,
											  BigDecimal p_Renewal_Comp,
											  BigDecimal p_Removal_Comp,
											  BigDecimal p_ExtWarranty_Comp,
											  String p_service_dlr_code,
											  String p_service_dlr, 
											  String p_finding_dlr_code, 
											  String p_finding_dlr, 
											  String p_original_dlr_code, 
											  String p_original_dlr, 
											  String p_order_take_dlr_code, 
											  String p_order_take_dlr, 
											  String p_install_comp_dlr_code,
											  String p_install_comp_dlr, 
											  String p_renewal_comp_dlr_code, 
											  String p_renewal_comp_dlr, 
											  String p_removal_comp_dlr_code,
											  String p_removal_comp_dlr, 
											  String p_ext_warranty_comp_dlr_code, 
											  String p_ext_warranty_comp_dlr,
											  String p_comp_override)
	{
        System.out.println("in ittDealerCompInsert "+p_header_id+"|"+p_line_id+"|"+p_itt_number+"|"+p_user_name+"|"+p_Service_Comp+"|"+p_Finding_Comp+"|"+p_Original_Comp+"|"+p_OrderTake_Comp+"|"+p_Install_Comp+"|"+p_Renewal_Comp+"|"+p_Removal_Comp+"|"+p_ExtWarranty_Comp);		
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_DEALER_COMP_INSERT);
                if (statement != null) {
					
                    statement.setObject(1, p_header_id, OracleTypes.VARCHAR);
                    statement.setObject(2, p_line_id, OracleTypes.VARCHAR);
					statement.setObject(3, p_itt_number, OracleTypes.VARCHAR);
					statement.setObject(4, p_user_name, OracleTypes.VARCHAR);
					statement.setObject(5, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(6, p_line_number, OracleTypes.VARCHAR);
                    statement.setObject(7, p_item, OracleTypes.VARCHAR);
                    statement.setObject(8, p_Service_Comp, OracleTypes.NUMBER);
					statement.setObject(9, p_Finding_Comp, OracleTypes.NUMBER);
					statement.setObject(10,p_Original_Comp, OracleTypes.NUMBER);
                    statement.setObject(11,p_OrderTake_Comp, OracleTypes.NUMBER);
                    statement.setObject(12, p_Install_Comp, OracleTypes.NUMBER);
					statement.setObject(13, p_Renewal_Comp, OracleTypes.NUMBER);
					statement.setObject(14, p_Removal_Comp, OracleTypes.NUMBER);
                    statement.setObject(15, p_ExtWarranty_Comp, OracleTypes.NUMBER);
					statement.setObject(16, p_service_dlr_code, OracleTypes.VARCHAR);
					statement.setObject(17, p_service_dlr, OracleTypes.VARCHAR);
					statement.setObject(18, p_finding_dlr_code, OracleTypes.VARCHAR);
                    statement.setObject(19, p_finding_dlr, OracleTypes.VARCHAR);
					statement.setObject(20, p_original_dlr_code, OracleTypes.VARCHAR);
					statement.setObject(21, p_original_dlr, OracleTypes.VARCHAR);
					statement.setObject(22, p_order_take_dlr_code, OracleTypes.VARCHAR);
                    statement.setObject(23, p_order_take_dlr, OracleTypes.VARCHAR);
					statement.setObject(24, p_install_comp_dlr_code, OracleTypes.VARCHAR);
					statement.setObject(25, p_install_comp_dlr, OracleTypes.VARCHAR);
					statement.setObject(26, p_renewal_comp_dlr_code, OracleTypes.VARCHAR);
                    statement.setObject(27, p_renewal_comp_dlr, OracleTypes.VARCHAR);
					statement.setObject(28, p_removal_comp_dlr_code, OracleTypes.VARCHAR);
					statement.setObject(29, p_removal_comp_dlr, OracleTypes.VARCHAR);
                    statement.setObject(30, p_ext_warranty_comp_dlr_code, OracleTypes.VARCHAR); 
                    statement.setObject(31, p_ext_warranty_comp_dlr, OracleTypes.VARCHAR); 	
                    statement.setObject(32, p_comp_override, OracleTypes.VARCHAR); 					
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }                                                                        
   //Added Raghavendra Uppala ITG#690711 End
   
   
   public static Object[] ittNotesDetails(String p_itt_number,
    BigDecimal p_from_record,
    BigDecimal p_to_record,
    String p_order_by,
    String p_asc_desc){
        System.out.println("in ittNotesDetails "+p_itt_number+"|"+p_from_record+"|"+p_to_record+"|"+p_order_by+"|"+p_asc_desc);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_NOTES_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_from_record, OracleTypes.NUMBER);
                    statement.setObject(3, p_to_record, OracleTypes.NUMBER);
                    statement.setObject(4, p_order_by, OracleTypes.VARCHAR);
                    statement.setObject(5, p_asc_desc, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.CURSOR);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(6),NotesDetail.getRowMapper())
                        ,statement.getObject(7)};
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
        return null;
   }










    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_NOTES_DETAILS('S0160830-ITT-001',NULL,NULL,'SEQ_NUMBER','ASC',?,?)}
    (
    ROW_NUM NUMBER,
    ITT_NUMBER VARCHAR2,
    ORDER_NUMBER VARCHAR2,
    SEQ_NUMBER NUMBER,
    NOTES VARCHAR2,
    CREATED_BY_NAME VARCHAR2,
    CREATION_DATE DATE,
    ADD_TO_PO_PDF VARCHAR2
    )
    */
    public static class NotesDetail {
       private BigDecimal rowNum;
       private String ittNumber;
       private String orderNumber;
       private BigDecimal seqNumber;
       private String notes;
       private String createdByName;
       private Timestamp creationDate;
       private String addToPoPdf;

        public NotesDetail(){
        }
        public NotesDetail(BigDecimal rowNum, 
                String ittNumber, 
                String orderNumber, 
                BigDecimal seqNumber, 
                String notes, 
                String createdByName, 
                Timestamp creationDate, 
                String addToPoPdf){
            this.rowNum=rowNum;
            this.ittNumber=ittNumber;
            this.orderNumber=orderNumber;
            this.seqNumber=seqNumber;
            this.notes=notes;
            this.createdByName=createdByName;
            this.creationDate=creationDate;
            this.addToPoPdf=addToPoPdf;
        }
        public BigDecimal getRowNum() {
            return rowNum;
        }
        public void setRowNum(BigDecimal rowNum) {
            this.rowNum=rowNum;
        }
        public String getIttNumber() {
            return ittNumber;
        }
        public void setIttNumber(String ittNumber) {
            this.ittNumber=ittNumber;
        }
        public String getOrderNumber() {
            return orderNumber;
        }
        public void setOrderNumber(String orderNumber) {
            this.orderNumber=orderNumber;
        }
        public BigDecimal getSeqNumber() {
            return seqNumber;
        }
        public void setSeqNumber(BigDecimal seqNumber) {
            this.seqNumber=seqNumber;
        }
        public String getNotes() {
            return notes;
        }
        public void setNotes(String notes) {
            this.notes=notes;
        }
        public String getCreatedByName() {
            return createdByName;
        }
        public void setCreatedByName(String createdByName) {
            this.createdByName=createdByName;
        }
        public Timestamp getCreationDate() {
            return creationDate;
        }
        public void setCreationDate(Timestamp creationDate) {
            this.creationDate=creationDate;
        }
        public String getAddToPoPdf() {
            return addToPoPdf;
        }
        public void setAddToPoPdf(String addToPoPdf) {
            this.addToPoPdf=addToPoPdf;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new NotesDetail(
                        rs.getBigDecimal("ROW_NUM"),
                        rs.getString("ITT_NUMBER"),
                        rs.getString("ORDER_NUMBER"),
                        rs.getBigDecimal("SEQ_NUMBER"),
                        rs.getString("NOTES"),
                        rs.getString("CREATED_BY_NAME"),
                        rs.getTimestamp("CREATION_DATE"),
                        rs.getString("ADD_TO_PO_PDF")
                    );
                }
            };
        }
        public String toString() {
            return "NotesDetail{" + "rowNum="+rowNum+", ittNumber="+ittNumber+", orderNumber="+orderNumber+", seqNumber="+seqNumber+", notes="+notes+", createdByName="+createdByName+", creationDate="+creationDate+", addToPoPdf="+addToPoPdf+'}';
        }
    }

   public static Object[] ittLineTypes(){
        System.out.println("in ittLineTypes ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_LINE_TYPES);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }    
   

   public static Object[] ittTimestampUpdate(String p_itt_number,
    String p_user_name,
    Timestamp p_po_date,
    Timestamp p_po_accepted_date_by_dlr,
    Timestamp p_po_sent_date_to_cusa,
    Timestamp p_shipped_date_from_cusa,
    Timestamp p_equip_arrive_at_dlr_date,
    Timestamp p_pod_rcvd_from_dlr_date,
    Timestamp p_cna_po_aprvd_by_dlr_date){
        System.out.println("in ittTimestampUpdate "+p_itt_number+"|"+p_user_name+"|"+p_po_date+"|"+p_po_accepted_date_by_dlr+"|"+p_po_sent_date_to_cusa+"|"+p_shipped_date_from_cusa+"|"+p_equip_arrive_at_dlr_date+"|"+p_pod_rcvd_from_dlr_date+"|"+p_cna_po_aprvd_by_dlr_date);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_TIMESTAMP_UPDATE);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_po_date, OracleTypes.TIMESTAMP);
                    statement.setObject(4, p_po_accepted_date_by_dlr, OracleTypes.TIMESTAMP);
                    statement.setObject(5, p_po_sent_date_to_cusa, OracleTypes.TIMESTAMP);
                    statement.setObject(6, p_shipped_date_from_cusa, OracleTypes.TIMESTAMP);
                    statement.setObject(7, p_equip_arrive_at_dlr_date, OracleTypes.TIMESTAMP);
                    statement.setObject(8, p_pod_rcvd_from_dlr_date, OracleTypes.TIMESTAMP);
                    statement.setObject(9, p_cna_po_aprvd_by_dlr_date, OracleTypes.TIMESTAMP);
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }

   public static Object[] ittTimestampDetails(String p_itt_number){
        System.out.println("in ittTimestampDetails "+p_itt_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_TIMESTAMP_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),TimestampDetail.getRowMapper())};
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
        return null;
   }

    // Database name CANDEV
	/*
     {call CANON_E580_ITT_UTIL_PKG.ITT_TIMESTAMP_DETAILS('S0127243T-ITT-01',?)}
     (
     ITT_NUMBER VARCHAR2,
     ORDER_CREATION_DATE DATE,
     BOOKED_DATE DATE,
     PO_DATE DATE,
     PO_ACCEPTED_DATE_BY_DLR DATE,
     PO_SENT_DATE_TO_CUSA DATE,
     SHIPPED_DATE_FROM_CUSA DATE,
     EQUIP_ARRIVE_AT_DLR_DATE DATE,
     POD_RCVD_FROM_DLR_DATE DATE,
     CNA_PO_APRVD_BY_DLR_DATE DATE,
     PDF_CREATION_DATE DATE,
     PDF_LAST_UPDATE_DATE DATE
     )
     */
    public static class TimestampDetail {

        private String ittNumber;
        private Timestamp orderCreationDate;
        private Timestamp bookedDate;
        private Timestamp poDate;
        private Timestamp poAcceptedDateByDlr;
        private Timestamp poSentDateToCusa;
        private Timestamp shippedDateFromCusa;
        private Timestamp equipArriveAtDlrDate;
        private Timestamp podRcvdFromDlrDate;
        private Timestamp cnaPoAprvdByDlrDate;
        private Timestamp pdfCreationDate;
        private Timestamp pdfLastUpdateDate;

        public TimestampDetail() {
        }

        public TimestampDetail(String ittNumber,
                Timestamp orderCreationDate,
                Timestamp bookedDate,
                Timestamp poDate,
                Timestamp poAcceptedDateByDlr,
                Timestamp poSentDateToCusa,
                Timestamp shippedDateFromCusa,
                Timestamp equipArriveAtDlrDate,
                Timestamp podRcvdFromDlrDate,
                Timestamp cnaPoAprvdByDlrDate,
                Timestamp pdfCreationDate,
                Timestamp pdfLastUpdateDate) {
            this.ittNumber = ittNumber;
            this.orderCreationDate = orderCreationDate;
            this.bookedDate = bookedDate;
            this.poDate = poDate;
            this.poAcceptedDateByDlr = poAcceptedDateByDlr;
            this.poSentDateToCusa = poSentDateToCusa;
            this.shippedDateFromCusa = shippedDateFromCusa;
            this.equipArriveAtDlrDate = equipArriveAtDlrDate;
            this.podRcvdFromDlrDate = podRcvdFromDlrDate;
            this.cnaPoAprvdByDlrDate = cnaPoAprvdByDlrDate;
            this.pdfCreationDate = pdfCreationDate;
            this.pdfLastUpdateDate = pdfLastUpdateDate;
        }

        public String getIttNumber() {
            return ittNumber;
        }

        public void setIttNumber(String ittNumber) {
            this.ittNumber = ittNumber;
        }

        public Timestamp getOrderCreationDate() {
            return orderCreationDate;
        }

        public void setOrderCreationDate(Timestamp orderCreationDate) {
            this.orderCreationDate = orderCreationDate;
        }

        public Timestamp getBookedDate() {
            return bookedDate;
        }

        public void setBookedDate(Timestamp bookedDate) {
            this.bookedDate = bookedDate;
        }

        public Timestamp getPoDate() {
            return poDate;
        }

        public void setPoDate(Timestamp poDate) {
            this.poDate = poDate;
        }

        public Timestamp getPoAcceptedDateByDlr() {
            return poAcceptedDateByDlr;
        }

        public void setPoAcceptedDateByDlr(Timestamp poAcceptedDateByDlr) {
            this.poAcceptedDateByDlr = poAcceptedDateByDlr;
        }

        public Timestamp getPoSentDateToCusa() {
            return poSentDateToCusa;
        }

        public void setPoSentDateToCusa(Timestamp poSentDateToCusa) {
            this.poSentDateToCusa = poSentDateToCusa;
        }

        public Timestamp getShippedDateFromCusa() {
            return shippedDateFromCusa;
        }

        public void setShippedDateFromCusa(Timestamp shippedDateFromCusa) {
            this.shippedDateFromCusa = shippedDateFromCusa;
        }

        public Timestamp getEquipArriveAtDlrDate() {
            return equipArriveAtDlrDate;
        }

        public void setEquipArriveAtDlrDate(Timestamp equipArriveAtDlrDate) {
            this.equipArriveAtDlrDate = equipArriveAtDlrDate;
        }

        public Timestamp getPodRcvdFromDlrDate() {
            return podRcvdFromDlrDate;
        }

        public void setPodRcvdFromDlrDate(Timestamp podRcvdFromDlrDate) {
            this.podRcvdFromDlrDate = podRcvdFromDlrDate;
        }

        public Timestamp getCnaPoAprvdByDlrDate() {
            return cnaPoAprvdByDlrDate;
        }

        public void setCnaPoAprvdByDlrDate(Timestamp cnaPoAprvdByDlrDate) {
            this.cnaPoAprvdByDlrDate = cnaPoAprvdByDlrDate;
        }

        public Timestamp getPdfCreationDate() {
            return pdfCreationDate;
        }

        public void setPdfCreationDate(Timestamp pdfCreationDate) {
            this.pdfCreationDate = pdfCreationDate;
        }

        public Timestamp getPdfLastUpdateDate() {
            return pdfLastUpdateDate;
        }

        public void setPdfLastUpdateDate(Timestamp pdfLastUpdateDate) {
            this.pdfLastUpdateDate = pdfLastUpdateDate;
        }

        public static CanonE580RowMapper getRowMapper() {
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new TimestampDetail(
                            rs.getString("ITT_NUMBER"),
                            rs.getTimestamp("ORDER_CREATION_DATE"),
                            rs.getTimestamp("BOOKED_DATE"),
                            rs.getTimestamp("PO_DATE"),
                            rs.getTimestamp("PO_ACCEPTED_DATE_BY_DLR"),
                            rs.getTimestamp("PO_SENT_DATE_TO_CUSA"),
                            rs.getTimestamp("SHIPPED_DATE_FROM_CUSA"),
                            rs.getTimestamp("EQUIP_ARRIVE_AT_DLR_DATE"),
                            rs.getTimestamp("POD_RCVD_FROM_DLR_DATE"),
                            rs.getTimestamp("CNA_PO_APRVD_BY_DLR_DATE"),
                            rs.getTimestamp("PDF_CREATION_DATE"),
                            rs.getTimestamp("PDF_LAST_UPDATE_DATE"));
                }
            };
        }

        public String toString() {
            return "TimestampDetail{" + "ittNumber=" + ittNumber + ", orderCreationDate=" + orderCreationDate + ", bookedDate=" + bookedDate + ", poDate=" + poDate + ", poAcceptedDateByDlr=" + poAcceptedDateByDlr + ", poSentDateToCusa=" + poSentDateToCusa + ", shippedDateFromCusa=" + shippedDateFromCusa + ", equipArriveAtDlrDate=" + equipArriveAtDlrDate + ", podRcvdFromDlrDate=" + podRcvdFromDlrDate + ", cnaPoAprvdByDlrDate=" + cnaPoAprvdByDlrDate + ", pdfCreationDate=" + pdfCreationDate + ", pdfLastUpdateDate=" + pdfLastUpdateDate + '}';
        }
    }


   public static Object[] ittSalesZoneLov(){
        System.out.println("in ittSalesZoneLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_SALES_ZONE_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }    

   public static Object[] ittSalesBranchLov(String p_sales_branch){
        System.out.println("in ittSalesBranchLov "+p_sales_branch);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_SALES_BRANCH_LOV);
                if (statement != null) {
                    statement.setObject(1, p_sales_branch, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())};
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
        return null;
   }   
   
   public static Object[] ittSalesrepLov(String p_sales_rep){
        System.out.println("in ittSalesrepLov "+p_sales_rep);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_SALESREP_LOV);
                if (statement != null) {
                    statement.setObject(1, p_sales_rep, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())};
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
        return null;
   }

   public static Object[] ittCreatePoPdf(String p_itt_number,
	String p_line_number,
    String p_user_name){
        System.out.println("in ittCreatePoPdf "+p_itt_number+"|"+p_line_number+"|"+p_user_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_CREATE_PO_PDF);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_line_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.CURSOR);
                    statement.registerOutParameter(5, OracleTypes.CURSOR);
                    statement.registerOutParameter(6, OracleTypes.CURSOR);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.registerOutParameter(8, OracleTypes.NUMBER);
                    statement.registerOutParameter(9, OracleTypes.NUMBER);
                    statement.registerOutParameter(10, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(4),PDFHeaderInfo.getRowMapper())
                        ,cursorToList((ResultSet)statement.getObject(5),PDFLineInfo.getRowMapper())
                        ,cursorToList((ResultSet)statement.getObject(6),PDFMaintInfo.getRowMapper())
                        ,statement.getObject(7)
                        ,statement.getObject(8)
                        ,statement.getObject(9)
                        ,statement.getObject(10)};
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
        return null;
   }

    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_CREATE_PO_PDF('BPC-623252-ITT-11','08857',?,?,?,?,?,?,?)}
    (
    ITT_NUMBER VARCHAR2,
    ORDER_NUMBER VARCHAR2,
    DEALER VARCHAR2,
    DEALER_WHSE_CODE VARCHAR2,
    LOCATION CHAR,
    DEALER_CONTACT VARCHAR2,
    DEALER_CONTACT_NUMBER VARCHAR2,
    DEALER_EMAIL VARCHAR2,
    DLR_FAX_AREA_CODE CHAR,
    DLR_FAX CHAR,
    DEALER_SHIP_TO_CNA_CODE VARCHAR2,
    SHIP_TO_CUSTOMER VARCHAR2,
    ADDRESS1 VARCHAR2,
    ADDRESS2 VARCHAR2,
    CITY VARCHAR2,
    STATE VARCHAR2,
    POSTAL_CODE VARCHAR2,
    COUNTY VARCHAR2,
    CUSTOMER_CONTACT CHAR,
    CUSTOMER_CONTACT_PHONE CHAR,
    CUSTOMER_CONTACT_EMAIL CHAR,
    CUSTOMER_CONTACT_FAX CHAR,
    ITT_ADMIN_NAME CHAR,
    ITT_ADMIN_PHONE CHAR,
    ITT_ADMIN_FAX CHAR,
    ITT_ADMIN_EMAIL CHAR,
    SCHEDULED_DELIVERY_DATE DATE,
    MAIL_INVOICES_TO VARCHAR2,
    REQUEST_DATE DATE,
    SALES_BRANCH VARCHAR2
    )
    */
    public static class PDFHeaderInfo {
       private String ittNumber;
       private String orderNumber;
       private String dealer;
       private String dealerWhseCode;
       private String location;
       private String dealerContact;
       private String dealerContactNumber;
       private String dealerEmail;
       private String dlrFaxAreaCode;
       private String dlrFax;
       private String dealerShipToCnaCode;
       private String shipToCustomer;
       private String address1;
       private String address2;
       private String city;
       private String state;
       private String postalCode;
       private String county;
       private String customerContact;
       private String customerContactPhone;
       private String customerContactEmail;
       private String customerContactFax;
       private String ittAdminName;
       private String ittAdminPhone;
       private String ittAdminFax;
       private String ittAdminEmail;
       private Timestamp scheduledDeliveryDate;
       private String mailInvoicesTo;
       private Timestamp requestDate;
       private String salesBranch;

        public PDFHeaderInfo(){
        }
        public PDFHeaderInfo(String ittNumber, 
                String orderNumber, 
                String dealer, 
                String dealerWhseCode, 
                String location, 
                String dealerContact, 
                String dealerContactNumber, 
                String dealerEmail, 
                String dlrFaxAreaCode, 
                String dlrFax, 
                String dealerShipToCnaCode, 
                String shipToCustomer, 
                String address1, 
                String address2, 
                String city, 
                String state, 
                String postalCode, 
                String county, 
                String customerContact, 
                String customerContactPhone, 
                String customerContactEmail, 
                String customerContactFax, 
                String ittAdminName, 
                String ittAdminPhone, 
                String ittAdminFax, 
                String ittAdminEmail, 
                Timestamp scheduledDeliveryDate, 
                String mailInvoicesTo, 
                Timestamp requestDate, 
                String salesBranch){
            this.ittNumber=ittNumber;
            this.orderNumber=orderNumber;
            this.dealer=dealer;
            this.dealerWhseCode=dealerWhseCode;
            this.location=location;
            this.dealerContact=dealerContact;
            this.dealerContactNumber=dealerContactNumber;
            this.dealerEmail=dealerEmail;
            this.dlrFaxAreaCode=dlrFaxAreaCode;
            this.dlrFax=dlrFax;
            this.dealerShipToCnaCode=dealerShipToCnaCode;
            this.shipToCustomer=shipToCustomer;
            this.address1=address1;
            this.address2=address2;
            this.city=city;
            this.state=state;
            this.postalCode=postalCode;
            this.county=county;
            this.customerContact=customerContact;
            this.customerContactPhone=customerContactPhone;
            this.customerContactEmail=customerContactEmail;
            this.customerContactFax=customerContactFax;
            this.ittAdminName=ittAdminName;
            this.ittAdminPhone=ittAdminPhone;
            this.ittAdminFax=ittAdminFax;
            this.ittAdminEmail=ittAdminEmail;
            this.scheduledDeliveryDate=scheduledDeliveryDate;
            this.mailInvoicesTo=mailInvoicesTo;
            this.requestDate=requestDate;
            this.salesBranch=salesBranch;
        }
        public String getIttNumber() {
            return ittNumber;
        }
        public void setIttNumber(String ittNumber) {
            this.ittNumber=ittNumber;
        }
        public String getOrderNumber() {
            return orderNumber;
        }
        public void setOrderNumber(String orderNumber) {
            this.orderNumber=orderNumber;
        }
        public String getDealer() {
            return dealer;
        }
        public void setDealer(String dealer) {
            this.dealer=dealer;
        }
        public String getDealerWhseCode() {
            return dealerWhseCode;
        }
        public void setDealerWhseCode(String dealerWhseCode) {
            this.dealerWhseCode=dealerWhseCode;
        }
        public String getLocation() {
            return location;
        }
        public void setLocation(String location) {
            this.location=location;
        }
        public String getDealerContact() {
            return dealerContact;
        }
        public void setDealerContact(String dealerContact) {
            this.dealerContact=dealerContact;
        }
        public String getDealerContactNumber() {
            return dealerContactNumber;
        }
        public void setDealerContactNumber(String dealerContactNumber) {
            this.dealerContactNumber=dealerContactNumber;
        }
        public String getDealerEmail() {
            return dealerEmail;
        }
        public void setDealerEmail(String dealerEmail) {
            this.dealerEmail=dealerEmail;
        }
        public String getDlrFaxAreaCode() {
            return dlrFaxAreaCode;
        }
        public void setDlrFaxAreaCode(String dlrFaxAreaCode) {
            this.dlrFaxAreaCode=dlrFaxAreaCode;
        }
        public String getDlrFax() {
            return dlrFax;
        }
        public void setDlrFax(String dlrFax) {
            this.dlrFax=dlrFax;
        }
        public String getDealerShipToCnaCode() {
            return dealerShipToCnaCode;
        }
        public void setDealerShipToCnaCode(String dealerShipToCnaCode) {
            this.dealerShipToCnaCode=dealerShipToCnaCode;
        }
        public String getShipToCustomer() {
            return shipToCustomer;
        }
        public void setShipToCustomer(String shipToCustomer) {
            this.shipToCustomer=shipToCustomer;
        }
        public String getAddress1() {
            return address1;
        }
        public void setAddress1(String address1) {
            this.address1=address1;
        }
        public String getAddress2() {
            return address2;
        }
        public void setAddress2(String address2) {
            this.address2=address2;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city=city;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state=state;
        }
        public String getPostalCode() {
            return postalCode;
        }
        public void setPostalCode(String postalCode) {
            this.postalCode=postalCode;
        }
        public String getCounty() {
            return county;
        }
        public void setCounty(String county) {
            this.county=county;
        }
        public String getCustomerContact() {
            return customerContact;
        }
        public void setCustomerContact(String customerContact) {
            this.customerContact=customerContact;
        }
        public String getCustomerContactPhone() {
            return customerContactPhone;
        }
        public void setCustomerContactPhone(String customerContactPhone) {
            this.customerContactPhone=customerContactPhone;
        }
        public String getCustomerContactEmail() {
            return customerContactEmail;
        }
        public void setCustomerContactEmail(String customerContactEmail) {
            this.customerContactEmail=customerContactEmail;
        }
        public String getCustomerContactFax() {
            return customerContactFax;
        }
        public void setCustomerContactFax(String customerContactFax) {
            this.customerContactFax=customerContactFax;
        }
        public String getIttAdminName() {
            return ittAdminName;
        }
        public void setIttAdminName(String ittAdminName) {
            this.ittAdminName=ittAdminName;
        }
        public String getIttAdminPhone() {
            return ittAdminPhone;
        }
        public void setIttAdminPhone(String ittAdminPhone) {
            this.ittAdminPhone=ittAdminPhone;
        }
        public String getIttAdminFax() {
            return ittAdminFax;
        }
        public void setIttAdminFax(String ittAdminFax) {
            this.ittAdminFax=ittAdminFax;
        }
        public String getIttAdminEmail() {
            return ittAdminEmail;
        }
        public void setIttAdminEmail(String ittAdminEmail) {
            this.ittAdminEmail=ittAdminEmail;
        }
        public Timestamp getScheduledDeliveryDate() {
            return scheduledDeliveryDate;
        }
        public void setScheduledDeliveryDate(Timestamp scheduledDeliveryDate) {
            this.scheduledDeliveryDate=scheduledDeliveryDate;
        }
        public String getMailInvoicesTo() {
            return mailInvoicesTo;
        }
        public void setMailInvoicesTo(String mailInvoicesTo) {
            this.mailInvoicesTo=mailInvoicesTo;
        }
        public Timestamp getRequestDate() {
            return requestDate;
        }
        public void setRequestDate(Timestamp requestDate) {
            this.requestDate=requestDate;
        }
        public String getSalesBranch() {
            return salesBranch;
        }
        public void setSalesBranch(String salesBranch) {
            this.salesBranch=salesBranch;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                	String strittNumber=                         rs.getString("ITT_NUMBER")==null?"":rs.getString("ITT_NUMBER");
                    String strorderNumber=                rs.getString("ORDER_NUMBER")==null?"":rs.getString("ORDER_NUMBER");
                    String strdealer=                     rs.getString("DEALER")==null?"":rs.getString("DEALER");
                    String strdealerWhseCode=             rs.getString("DEALER_WHSE_CODE")==null?"":rs.getString("DEALER_WHSE_CODE");
                    String strlocation=                   rs.getString("LOCATION")==null?"":rs.getString("LOCATION");
                    String strdealerContact=              rs.getString("DEALER_CONTACT")==null?"":rs.getString("DEALER_CONTACT");
                    String strdealerContactNumber=        rs.getString("DEALER_CONTACT_NUMBER")==null?"":rs.getString("DEALER_CONTACT_NUMBER");
                    String strdealerEmail=                rs.getString("DEALER_EMAIL")==null?"":rs.getString("DEALER_EMAIL");
                    String strdlrFaxAreaCode=             rs.getString("DLR_FAX_AREA_CODE")==null?"":rs.getString("DLR_FAX_AREA_CODE");
                    String strdlrFax=                     rs.getString("DLR_FAX")==null?"":rs.getString("DLR_FAX");
                    String strdealerShipToCnaCode=        rs.getString("DEALER_SHIP_TO_CNA_CODE")==null?"":rs.getString("DEALER_SHIP_TO_CNA_CODE");
                    String strshipToCustomer=             rs.getString("SHIP_TO_CUSTOMER")==null?"":rs.getString("SHIP_TO_CUSTOMER");
                    String straddress1=                   rs.getString("ADDRESS1")==null?"":rs.getString("ADDRESS1");
                    String straddress2=                   rs.getString("ADDRESS2")==null?"":rs.getString("ADDRESS2");
                    String strcity=                       rs.getString("CITY")==null?"":rs.getString("CITY");
                    String strstate=                      rs.getString("STATE")==null?"":rs.getString("STATE");
                    String strpostalCode=                 rs.getString("POSTAL_CODE")==null?"":rs.getString("POSTAL_CODE");
                    String strcounty=                     rs.getString("COUNTY")==null?"":rs.getString("COUNTY");    
                    String strcustomerContact=            rs.getString("CUSTOMER_CONTACT")==null?"":rs.getString("CUSTOMER_CONTACT");
                    String strcustomerContactPhone=       rs.getString("CUSTOMER_CONTACT_PHONE")==null?"":rs.getString("CUSTOMER_CONTACT_PHONE");
                    String strcustomerContactEmail=       rs.getString("CUSTOMER_CONTACT_EMAIL")==null?"":rs.getString("CUSTOMER_CONTACT_EMAIL");
                    String strcustomerContactFax=         rs.getString("CUSTOMER_CONTACT_FAX")==null?"":rs.getString("CUSTOMER_CONTACT_FAX");
                    String strittAdminName=               rs.getString("ITT_ADMIN_NAME")==null?"":rs.getString("ITT_ADMIN_NAME");
                    String strittAdminPhone=              rs.getString("ITT_ADMIN_PHONE")==null?"":rs.getString("ITT_ADMIN_PHONE");
                    String strittAdminFax=                rs.getString("ITT_ADMIN_FAX")==null?"":rs.getString("ITT_ADMIN_FAX");
                    String strittAdminEmail=              rs.getString("ITT_ADMIN_EMAIL")==null?"":rs.getString("ITT_ADMIN_EMAIL");
                    Timestamp strscheduledDeliveryDate=   rs.getTimestamp("SCHEDULED_DELIVERY_DATE")==null?new Timestamp(System.currentTimeMillis()):rs.getTimestamp("SCHEDULED_DELIVERY_DATE");
                    String strmailInvoicesTo=             rs.getString("MAIL_INVOICES_TO")==null?"":rs.getString("MAIL_INVOICES_TO");
                    Timestamp strrequestDate=             rs.getTimestamp("REQUEST_DATE")==null?new Timestamp(System.currentTimeMillis()):rs.getTimestamp("REQUEST_DATE");
                    String strsalesBranch= 				  rs.getString("SALES_BRANCH")==null?"":rs.getString("SALES_BRANCH");
                    System.out.println("getData 4.3");
                	
                    return new PDFHeaderInfo(strittNumber,                 
                            strorderNumber,               
                            strdealer,                    
                            strdealerWhseCode,            
                            strlocation,                  
                            strdealerContact,             
                            strdealerContactNumber,       
                            strdealerEmail,               
                            strdlrFaxAreaCode,            
                            strdlrFax,                    
                            strdealerShipToCnaCode,       
                            strshipToCustomer,            
                            straddress1,                  
                            straddress2,                  
                            strcity,                      
                            strstate,                     
                            strpostalCode,                
                            strcounty,                    
                            strcustomerContact,           
                            strcustomerContactPhone,      
                            strcustomerContactEmail,      
                            strcustomerContactFax,        
                            strittAdminName,              
                            strittAdminPhone,             
                            strittAdminFax,               
    						strittAdminEmail,             
    						strscheduledDeliveryDate,  
    						strmailInvoicesTo,            
                            strrequestDate,            
                            strsalesBranch);
                    /*return new PDFHeaderInfo(
                        rs.getString("ITT_NUMBER"),
                        rs.getString("ORDER_NUMBER"),
                        rs.getString("DEALER"),
                        rs.getString("DEALER_WHSE_CODE"),
                        rs.getString("LOCATION"),
                        rs.getString("DEALER_CONTACT"),
                        rs.getString("DEALER_CONTACT_NUMBER"),
                        rs.getString("DEALER_EMAIL"),
                        rs.getString("DLR_FAX_AREA_CODE"),
                        rs.getString("DLR_FAX"),
                        rs.getString("DEALER_SHIP_TO_CNA_CODE"),
                        rs.getString("SHIP_TO_CUSTOMER"),
                        rs.getString("ADDRESS1"),
                        rs.getString("ADDRESS2"),
                        rs.getString("CITY"),
                        rs.getString("STATE"),
                        rs.getString("POSTAL_CODE"),
                        rs.getString("COUNTY"),
                        rs.getString("CUSTOMER_CONTACT"),
                        rs.getString("CUSTOMER_CONTACT_PHONE"),
                        rs.getString("CUSTOMER_CONTACT_EMAIL"),
                        rs.getString("CUSTOMER_CONTACT_FAX"),
                        rs.getString("ITT_ADMIN_NAME"),
                        rs.getString("ITT_ADMIN_PHONE"),
                        rs.getString("ITT_ADMIN_FAX"),
                        rs.getString("ITT_ADMIN_EMAIL"),
                        rs.getTimestamp("SCHEDULED_DELIVERY_DATE"),
                        rs.getString("MAIL_INVOICES_TO"),
                        rs.getTimestamp("REQUEST_DATE"),
                        rs.getString("SALES_BRANCH")
                    );*/
                }
            };
        }
        public String toString() {
            return "PDFHeaderInfo{" + "ittNumber="+ittNumber+", orderNumber="+orderNumber+", dealer="+dealer+", dealerWhseCode="+dealerWhseCode+", location="+location+", dealerContact="+dealerContact+", dealerContactNumber="+dealerContactNumber+", dealerEmail="+dealerEmail+", dlrFaxAreaCode="+dlrFaxAreaCode+", dlrFax="+dlrFax+", dealerShipToCnaCode="+dealerShipToCnaCode+", shipToCustomer="+shipToCustomer+", address1="+address1+", address2="+address2+", city="+city+", state="+state+", postalCode="+postalCode+", county="+county+", customerContact="+customerContact+", customerContactPhone="+customerContactPhone+", customerContactEmail="+customerContactEmail+", customerContactFax="+customerContactFax+", ittAdminName="+ittAdminName+", ittAdminPhone="+ittAdminPhone+", ittAdminFax="+ittAdminFax+", ittAdminEmail="+ittAdminEmail+", scheduledDeliveryDate="+scheduledDeliveryDate+", mailInvoicesTo="+mailInvoicesTo+", requestDate="+requestDate+", salesBranch="+salesBranch+'}';
        }
    }    
    
    /* output cursor position 2 */
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_CREATE_PO_PDF('BPR-TEST1001-ITT-01','Q05058',?,?,?,?,?,?,?)}
    (
    ITEM VARCHAR2,
    ITEM_DESCRIPTION VARCHAR2,
    ORDERED_QUANTITY NUMBER,
    ITT_LINE_TYPE VARCHAR2,
    ITEM_PURCHASE_PRICE VARCHAR2,
    EXTENDED_COST VARCHAR2,
    INSTALL_CREDIT NUMBER,
    FINDER_FEE VARCHAR2,
    EQUIP_MODEL VARCHAR2,
    MERCHANDISE VARCHAR2,
    COUNT_FLAG CHAR,
    CANCELLED_FLAG VARCHAR2
    )
    */
    public static class PDFLineInfo {
       private String item;
       private String itemDescription;
       private BigDecimal orderedQuantity;
       private String ittLineType;
       private String itemPurchasePrice;
       private String extendedCost;
       private BigDecimal installCredit;
       private String finderFee;
       private String equipModel;
       private String merchandise;
       private String countFlag;
       private String cancelledFlag;

        public PDFLineInfo(){
        }
        public PDFLineInfo(String item, 
                String itemDescription, 
                BigDecimal orderedQuantity, 
                String ittLineType, 
                String itemPurchasePrice, 
                String extendedCost, 
                BigDecimal installCredit, 
                String finderFee, 
                String equipModel, 
                String merchandise, 
                String countFlag, 
                String cancelledFlag){
            this.item=item;
            this.itemDescription=itemDescription;
            this.orderedQuantity=orderedQuantity;
            this.ittLineType=ittLineType;
            this.itemPurchasePrice=itemPurchasePrice;
            this.extendedCost=extendedCost;
            this.installCredit=installCredit;
            this.finderFee=finderFee;
            this.equipModel=equipModel;
            this.merchandise=merchandise;
            this.countFlag=countFlag;
            this.cancelledFlag=cancelledFlag;
        }
        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item=item;
        }
        public String getItemDescription() {
            return itemDescription;
        }
        public void setItemDescription(String itemDescription) {
            this.itemDescription=itemDescription;
        }
        public BigDecimal getOrderedQuantity() {
            return orderedQuantity;
        }
        public void setOrderedQuantity(BigDecimal orderedQuantity) {
            this.orderedQuantity=orderedQuantity;
        }
        public String getIttLineType() {
            return ittLineType;
        }
        public void setIttLineType(String ittLineType) {
            this.ittLineType=ittLineType;
        }
        public String getItemPurchasePrice() {
            return itemPurchasePrice;
        }
        public void setItemPurchasePrice(String itemPurchasePrice) {
            this.itemPurchasePrice=itemPurchasePrice;
        }
        public String getExtendedCost() {
            return extendedCost;
        }
        public void setExtendedCost(String extendedCost) {
            this.extendedCost=extendedCost;
        }
        public BigDecimal getInstallCredit() {
            return installCredit;
        }
        public void setInstallCredit(BigDecimal installCredit) {
            this.installCredit=installCredit;
        }
        public String getFinderFee() {
            return finderFee;
        }
        public void setFinderFee(String finderFee) {
            this.finderFee=finderFee;
        }
        public String getEquipModel() {
            return equipModel;
        }
        public void setEquipModel(String equipModel) {
            this.equipModel=equipModel;
        }
        public String getMerchandise() {
            return merchandise;
        }
        public void setMerchandise(String merchandise) {
            this.merchandise=merchandise;
        }
        public String getCountFlag() {
            return countFlag;
        }
        public void setCountFlag(String countFlag) {
            this.countFlag=countFlag;
        }
        public String getCancelledFlag() {
            return cancelledFlag;
        }
        public void setCancelledFlag(String cancelledFlag) {
            this.cancelledFlag=cancelledFlag;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new PDFLineInfo(
                        rs.getString("ITEM"),
                        rs.getString("ITEM_DESCRIPTION"),
                        rs.getBigDecimal("ORDERED_QUANTITY"),
                        rs.getString("ITT_LINE_TYPE"),
                        rs.getString("ITEM_PURCHASE_PRICE"),
                        rs.getString("EXTENDED_COST"),
                        rs.getBigDecimal("INSTALL_CREDIT"),
                        rs.getString("FINDER_FEE"),
                        rs.getString("EQUIP_MODEL"),
                        rs.getString("MERCHANDISE"),
                        rs.getString("COUNT_FLAG"),
                        rs.getString("CANCELLED_FLAG")
                    );
                }
            };
        }
        public String toString() {
            return "PDFLineInfo{" + "item="+item+", itemDescription="+itemDescription+", orderedQuantity="+orderedQuantity+", ittLineType="+ittLineType+", itemPurchasePrice="+itemPurchasePrice+", extendedCost="+extendedCost+", installCredit="+installCredit+", finderFee="+finderFee+", equipModel="+equipModel+", merchandise="+merchandise+", countFlag="+countFlag+", cancelledFlag="+cancelledFlag+'}';
        }
    }

    /* output cursor position 3 */
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_CREATE_PO_PDF('BPR-TEST1001-ITT-01','Q05058',?,?,?,?,?,?)}
    (
    EQUIP_MODEL VARCHAR2,
    METER_TYPE VARCHAR2,
    QUANTITY NUMBER,
    BASE_PRICE NUMBER,
    COPY_INCLUSION NUMBER,
    OVERAGE_RATE NUMBER,
    TERM NUMBER,
    BASE_BILL_CYCLE VARCHAR2,
    OVERAGE_BILL_CYCLE VARCHAR2,
    CONTRACT_TYPE VARCHAR2
    )
    */
    public static class PDFMaintInfo {
       private String equipModel;
       private String meterType;
       private BigDecimal quantity;
       private BigDecimal basePrice;
       private BigDecimal copyInclusion;
       private BigDecimal overageRate;
       private BigDecimal term;
       private String baseBillCycle;
       private String overageBillCycle;
       private String contractType;

        public PDFMaintInfo(){
        }
        public PDFMaintInfo(String equipModel, 
                String meterType, 
                BigDecimal quantity, 
                BigDecimal basePrice, 
                BigDecimal copyInclusion, 
                BigDecimal overageRate, 
                BigDecimal term, 
                String baseBillCycle, 
                String overageBillCycle, 
                String contractType){
            this.equipModel=equipModel;
            this.meterType=meterType;
            this.quantity=quantity;
            this.basePrice=basePrice;
            this.copyInclusion=copyInclusion;
            this.overageRate=overageRate;
            this.term=term;
            this.baseBillCycle=baseBillCycle;
            this.overageBillCycle=overageBillCycle;
            this.contractType=contractType;
        }
        public String getEquipModel() {
            return equipModel;
        }
        public void setEquipModel(String equipModel) {
            this.equipModel=equipModel;
        }
        public String getMeterType() {
            return meterType;
        }
        public void setMeterType(String meterType) {
            this.meterType=meterType;
        }
        public BigDecimal getQuantity() {
            return quantity;
        }
        public void setQuantity(BigDecimal quantity) {
            this.quantity=quantity;
        }
        public BigDecimal getBasePrice() {
            return basePrice;
        }
        public void setBasePrice(BigDecimal basePrice) {
            this.basePrice=basePrice;
        }
        public BigDecimal getCopyInclusion() {
            return copyInclusion;
        }
        public void setCopyInclusion(BigDecimal copyInclusion) {
            this.copyInclusion=copyInclusion;
        }
        public BigDecimal getOverageRate() {
            return overageRate;
        }
        public void setOverageRate(BigDecimal overageRate) {
            this.overageRate=overageRate;
        }
        public BigDecimal getTerm() {
            return term;
        }
        public void setTerm(BigDecimal term) {
            this.term=term;
        }
        public String getBaseBillCycle() {
            return baseBillCycle;
        }
        public void setBaseBillCycle(String baseBillCycle) {
            this.baseBillCycle=baseBillCycle;
        }
        public String getOverageBillCycle() {
            return overageBillCycle;
        }
        public void setOverageBillCycle(String overageBillCycle) {
            this.overageBillCycle=overageBillCycle;
        }
        public String getContractType() {
            return contractType;
        }
        public void setContractType(String contractType) {
            this.contractType=contractType;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new PDFMaintInfo(
                        rs.getString("EQUIP_MODEL"),
                        rs.getString("METER_TYPE"),
                        rs.getBigDecimal("QUANTITY"),
                        rs.getBigDecimal("BASE_PRICE"),
                        rs.getBigDecimal("COPY_INCLUSION"),
                        rs.getBigDecimal("OVERAGE_RATE"),
                        rs.getBigDecimal("TERM"),
                        rs.getString("BASE_BILL_CYCLE"),
                        rs.getString("OVERAGE_BILL_CYCLE"),
                        rs.getString("CONTRACT_TYPE")
                    );
                }
            };
        }
        public String toString() {
            return "PDFMaintInfo{" + "equipModel="+equipModel+", meterType="+meterType+", quantity="+quantity+", basePrice="+basePrice+", copyInclusion="+copyInclusion+", overageRate="+overageRate+", term="+term+", baseBillCycle="+baseBillCycle+", overageBillCycle="+overageBillCycle+", contractType="+contractType+'}';
        }
    }

   public static Object[] ittMarkviewAttachedDocs(String p_itt_number,
    String p_user_name){
        System.out.println("in ittMarkviewAttachedDocs "+p_itt_number+"|"+p_user_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_MARKVIEW_ATTACHED_DOCS);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(3),MarkviewAttatchedDocInfo.getRowMapper())};
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
        return null;
   }


    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_MARKVIEW_ATTACHED_DOCS('S0180760-ITT-01','Q05058',?)}
    (
    ATTACHMENT_DATE DATE,
    DESCRIPTION VARCHAR2,
    DOCUMENT_ID VARCHAR2,
    MARKVIEW_DOC_LINK VARCHAR2
    )
    */
    public static class MarkviewAttatchedDocInfo {
       private Timestamp attachmentDate;
       private String description;
       private String documentId;
       private String markviewDocLink;

        public MarkviewAttatchedDocInfo(){
        }
        public MarkviewAttatchedDocInfo(Timestamp attachmentDate, 
                String description, 
                String documentId, 
                String markviewDocLink){
            this.attachmentDate=attachmentDate;
            this.description=description;
            this.documentId=documentId;
            this.markviewDocLink=markviewDocLink;
        }
        public Timestamp getAttachmentDate() {
            return attachmentDate;
        }
        public void setAttachmentDate(Timestamp attachmentDate) {
            this.attachmentDate=attachmentDate;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description=description;
        }
        public String getDocumentId() {
            return documentId;
        }
        public void setDocumentId(String documentId) {
            this.documentId=documentId;
        }
        public String getMarkviewDocLink() {
            return markviewDocLink;
        }
        public void setMarkviewDocLink(String markviewDocLink) {
            this.markviewDocLink=markviewDocLink;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new MarkviewAttatchedDocInfo(
                        rs.getTimestamp("ATTACHMENT_DATE"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("DOCUMENT_ID"),
                        rs.getString("MARKVIEW_DOC_LINK")
                    );
                }
            };
        }
        public String toString() {
            return "MarkviewAttatchedDocInfo{" + "attachmentDate="+attachmentDate+", description="+description+", documentId="+documentId+", markviewDocLink="+markviewDocLink+'}';
        }
    }


    public static Object[] createPoPrc(String p_itt_number,String p_user_name,String p_called_from, String p_cusa_po_num, String p_dealer_po_num){
    	
		   NPZC104001PMsg  pmsgValuesFromTables = new NPZC104001PMsg();
		   List<NPZC104001PMsg>  pmsgValuesFromTablesList = new ArrayList<NPZC104001PMsg>();
		  
	        System.out.println("in createPoPrc "+p_itt_number+"|"+p_user_name+"|"+p_called_from+"|"+p_cusa_po_num+"|"+p_dealer_po_num);
	        CallableStatement statement = null;
	        Connection connection = null;
	        Object[] result=new Object[3];
	        String status="";
	        String status_msg="";
	        Array canonE580CraetePoTblTypArray =null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	            	boolean createPOPrc=CanonE580ITTWorkbenchUtil.isEmpty(p_cusa_po_num) && CanonE580ITTWorkbenchUtil.isEmpty(p_dealer_po_num);
	                statement = createPOPrc? (CallableStatement) connection.prepareCall(CREATE_PO_PRC) :  (CallableStatement) connection.prepareCall(DERIVE_APPEND_PO_LINES);
	                if (statement != null) {
	                	if(createPOPrc) {
		                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
		                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
		                    statement.setObject(3, p_called_from, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(4, OracleTypes.ARRAY, "CANON_E580_CREATE_PO_TBL_TYP");
		                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
		                    statement.execute();
		                    canonE580CraetePoTblTypArray = statement.getArray(4);
		                    status=statement.getString(5);
		                    status_msg=statement.getString(6);
	                	}else {
	                        statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
	                        statement.setObject(2, p_cusa_po_num, OracleTypes.VARCHAR);
	                        statement.setObject(3, p_dealer_po_num, OracleTypes.VARCHAR);
	                        statement.setObject(4, p_user_name, OracleTypes.VARCHAR);
	                        statement.registerOutParameter(5, OracleTypes.ARRAY ,"CANON_E580_CREATE_PO_TBL_TYP");
	                        statement.registerOutParameter(6, OracleTypes.VARCHAR);
	                        statement.registerOutParameter(7, OracleTypes.VARCHAR);
	                        statement.execute();
		                    canonE580CraetePoTblTypArray = statement.getArray(5);
		                    status=statement.getString(6);
		                    status_msg=statement.getString(7);
	                	}
	                    
	                    String[] pmsgArrayElements={"xxModeCd",
	                    	    "eventId",
	                    	    "procDt",
	                    	    "xxRqstTs",
	                    	    "poOrdNum",
	                    	    "dsPoTpCd",
	                    	    "dsPoTpNm",
	                    	    "poQlfyCd",
	                    	    "poSubmtPsnCd",
	                    	    "poSubmtTs",
	                    	    "poApvlStsCd",
	                    	    "poApvlPsnCd",
	                    	    "poApvlDt",
	                    	    "poApvlTs",
	                    	    "destRtlWhCd",
	                    	    "srcRtlWhCd",
	                    	    "poOrdSrcCd",
	                    	    "prntVndCd",
	                    	    "prntVndNm",
	                    	    "vndCd",
	                    	    "vndNm",
	                    	    "dealCcyCd",
	                    	    "vndDropShipFlg",
	                    	    "prchGrpCd",
	                    	    "vndPmtTermCd",
	                    	    "vndPmtTermDescTxt",
	                    	    "rqstTechTocCd",
	                    	    "rqstRcvDt",
	                    	    "rqstRcvTm",
	                    	    "shipToCustCd",
	                    	    "shipToLocNm",
	                    	    "shipToAcctNm",
	                    	    "shipToAddlLocNm",
	                    	    "shipToFirstLineAddr",
	                    	    "shipToScdLineAddr",
	                    	    "shipToThirdLineAddr",
	                    	    "shipToFrthLineAddr",
	                    	    "shipToFirstRefCmntTxt",
	                    	    "shipToScdRefCmntTxt",
	                    	    "shipToCtyAddr",
	                    	    "shipToStCd",
	                    	    "shipToProvNm",
	                    	    "shipToCntyNm",
	                    	    "shipToPostCd",
	                    	    "shipToCtryCd",
	                    	    "ctacPsnNm",
	                    	    "rtrnShipToRtlWhCd",
	                    	    "shipFromSoNumListTxt",
	                    	    "carrCd",
	                    	    "carrAcctNum",
	                    	    "shpgSvcLvlCd",
	                    	    "frtChrgToCd",
	                    	    "frtChrgMethCd",
	                    	    "lineBizTpCd",
	                    	    "poOrdCmntTxt",
	                    	    "trsmtMethTpCd",
	                    	    "sendPoFaxNum",
	                    	    "sendPoEmlAddr",
	                    	    "poSendFlg",
	                    	    "poSendTs",
	                    	    "poPrintFlg",
	                    	    "dsctnInd",
	                    	    "wfFlg",
	                    	    "vndIssOrdNum",
	                    	    "eipRptRqstPk"};	                   
	                    Object[] objcanonE580CraetePoTblTypArray = (Object[]) canonE580CraetePoTblTypArray.getArray();
	                    int attrCountcanonE580CraetePoObj=objcanonE580CraetePoTblTypArray.length;
	                    for(int i=0; i<attrCountcanonE580CraetePoObj;i++){
	                    	pmsgValuesFromTables = new NPZC104001PMsg();
	                    	 pmsgValuesFromTables.glblCmpyCd.setValue("ADB");
	        	            STRUCT structCanonE580CraetePoTblTypArray = (STRUCT)objcanonE580CraetePoTblTypArray[i];
	        	            
	        	            Object[] obj = structCanonE580CraetePoTblTypArray.getAttributes();
	        	            int attributePriorToFirstTblType=66;
	        	            int getAttributesTillFirstTableType=0;
	        	            
	        	            for(int pmsgArrayElementsCount=0;pmsgArrayElementsCount<pmsgArrayElements.length;pmsgArrayElementsCount++)
	        	            {
	        	            	 
	        	            	//pmsgValuesFromTables.xxModeCd.setValue((String) obj[pmsgArrayElementsCount++]);
		        	            //pmsgValuesFromTables.eventId.setValue((String) obj[pmsgArrayElementsCount++]);
	        	            	getAttributesTillFirstTableType=0;
	        	            	String valueFromDb="";
	        	            	valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	        	            	 pmsgValuesFromTables.xxModeCd.setValue(valueFromDb==null?"":valueFromDb);
	        	            	 valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.eventId.setValue(valueFromDb==null?"":valueFromDb);
	             	           valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.procDt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.xxRqstTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	           pmsgValuesFromTables.poOrdNum.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];	             	            
	             	            //getAttributesTillFirstTableType++;
	             	          	
	             	          	//valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.dsPoTpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];	             	            
	             	            pmsgValuesFromTables.dsPoTpNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            
	             	            pmsgValuesFromTables.poQlfyCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSubmtPsnCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSubmtTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlStsCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlPsnCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlDt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.destRtlWhCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.srcRtlWhCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poOrdSrcCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.prntVndCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.prntVndNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.dealCcyCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndDropShipFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.prchGrpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndPmtTermCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndPmtTermDescTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rqstTechTocCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rqstRcvDt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rqstRcvTm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCustCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToLocNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToAcctNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToAddlLocNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToFirstLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToScdLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToThirdLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToFrthLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToFirstRefCmntTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToScdRefCmntTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCtyAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToStCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToProvNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCntyNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToPostCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCtryCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.ctacPsnNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rtrnShipToRtlWhCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipFromSoNumListTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.carrCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.carrAcctNum.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shpgSvcLvlCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.frtChrgToCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.frtChrgMethCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.lineBizTpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poOrdCmntTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.trsmtMethTpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.sendPoFaxNum.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.sendPoEmlAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSendFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSendTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poPrintFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.dsctnInd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.wfFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndIssOrdNum.setValue(valueFromDb==null?"":valueFromDb);
	             	            valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            if(valueFromDb!=null)
	             	            pmsgValuesFromTables.eipRptRqstPk.setValue(new BigDecimal(valueFromDb));
	             	            int arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	           if(obj[arrayIndexToProcess] instanceof ARRAY)
	       	            	{
		       	            		 ARRAY canonE580PohMsgTypArray=(ARRAY)obj[arrayIndexToProcess];
		       	            		 Datum[] datumCanonE580PohMsgTyp=canonE580PohMsgTypArray.getOracleArray();
		       	            		pmsgValuesFromTables.poInfo.setValidCount(datumCanonE580PohMsgTyp.length);
		       	            		 for(int canonE580PohMsgTypArrayElementCount=0;canonE580PohMsgTypArrayElementCount<datumCanonE580PohMsgTyp.length;canonE580PohMsgTypArrayElementCount++)
		       	            		 {
		       	     	            STRUCT structcanonE580PohMsgTlbTypArray = (STRUCT)datumCanonE580PohMsgTyp[canonE580PohMsgTypArrayElementCount];
		       	     	           Object[] objcanonE580PohMsgTlbTyp = structcanonE580PohMsgTlbTypArray.getAttributes();
		       	     	           
		       	     	       String poHdrMsgLineValueFromDb="";
		       	     	       BigDecimal poHdrMsgBigDecimalValueFromDb=null;
		       	     	           for(int canonE580PohMsgTlbCount=0;canonE580PohMsgTlbCount<objcanonE580PohMsgTlbTyp.length;canonE580PohMsgTlbCount++)
		       	     	           {
		       	     	        	poHdrMsgBigDecimalValueFromDb=(BigDecimal) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        //pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).poMsgPk.setValue(poHdrMsgBigDecimalValueFromDb);
		       	     	        poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).poMsgTpCd.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).poMsgSubmtPsnCd.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).xxDsMultMsgDplyTxt.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb); poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).prchReqNum.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).prchReqLineNum.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);
		       	     	        poHdrMsgBigDecimalValueFromDb=(BigDecimal) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).prchReqLineSubNum.setValue(poHdrMsgBigDecimalValueFromDb);
		       	     	        		       	     	        	 
		       	     	           }
		       	            		 }
		       	            	}
	             	          arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	         if(obj[arrayIndexToProcess] instanceof ARRAY)
		       	            	{	             	        	
		       	            		 ARRAY canonE580PolTypArray=(ARRAY)obj[arrayIndexToProcess];
		       	            		 Datum[] datumcanonE580PolTyp=canonE580PolTypArray.getOracleArray();
		       	            		pmsgValuesFromTables.poLineInfo.setValidCount(datumcanonE580PolTyp.length);
		       	            		 for(int canonE580PohMsgTypArrayElementCount=0;canonE580PohMsgTypArrayElementCount<datumcanonE580PolTyp.length;canonE580PohMsgTypArrayElementCount++)
		       	            		 {
		       	     	            STRUCT structcanonE580PohMsgTlbTypArray = (STRUCT)datumcanonE580PolTyp[canonE580PohMsgTypArrayElementCount];
		       	     	           Object[] objcanonE580PolTlbTyp = structcanonE580PohMsgTlbTypArray.getAttributes();
		       	     	       String poLineValueFromDb="";
		       	     	       BigDecimal poLineBigDecimalValueFromDb=null;
		       	     	       
		       	     	   
		       	     	           for(int canonE580PohMsgTlbCount=0;canonE580PohMsgTlbCount<objcanonE580PolTlbTyp.length;canonE580PohMsgTlbCount++)
		       	     	           {
		       	     	        	poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poOrdDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).dispPoDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poLineTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poMdseCmpsnTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).setPoOrdDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).mdseCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).mdseNm.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).mdseDescShortTxt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poQty.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];				       	     	
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poDispQty.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poInvQty.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poDispUomCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).thisMthFobCostAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entDealNetUnitPrcAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entPoDtlDealNetAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entFuncNetUnitPrcAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entPoDtlFuncNetAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).exchRate.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).custUomCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).destRtlSwhCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).srcRtlSwhCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).invtyLocCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). rqstRcvDt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).rqstRcvTm.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).frtCondCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origMdseCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).fromStkStsCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).toStkStsCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).adminPsnCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poMatchTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). ordQty.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoOrdNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoDtlLineSubNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).custIssPoNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). custIssPoDt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoOrdTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).billToCustCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).sellToCustCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).shpgPlnNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).prchReqNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).prchReqLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). prchReqLineSubNum.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).trxRefNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).trxRefLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).trxRefLineSubNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).aslDtlPk.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).aslMdseCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).aslUnitPrcAmt.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).shipFromSoNumListTxt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).vndInvtyLocCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).vndIssPoOrdNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).proNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).vndPoAckStsCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origPoOrdNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origPoOrdDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origDispPoDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). svcConfigMstrPk.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poSendTs.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poOrdDtlCmntTxt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).xxMsgId.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	    //poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];		       	     	        	  
		       	     	           }
		       	            		 }
		       	            	}
	             	         
	             	        arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	           if(obj[arrayIndexToProcess] instanceof ARRAY)
	       	            	{
	       	            		 ARRAY canonE580PosTblTypArray=(ARRAY)obj[arrayIndexToProcess];
	       	            		 Datum[] datumCanonE580PosTblTyp=canonE580PosTblTypArray.getOracleArray();
	       	            		pmsgValuesFromTables.serNumList.setValidCount(datumCanonE580PosTblTyp.length);
	       	            		 for(int canonE580PohMsgTypArrayElementCount=0;canonE580PohMsgTypArrayElementCount<datumCanonE580PosTblTyp.length;canonE580PohMsgTypArrayElementCount++)
	       	            		 {
	       	     	            STRUCT structCanonE580PosTblTypArray = (STRUCT)datumCanonE580PosTblTyp[canonE580PohMsgTypArrayElementCount];
	       	     	           Object[] objCanonE580PosTblTyp = structCanonE580PosTblTypArray.getAttributes();
	       	     	       String posLineValueFromDb="";
	       	     	       BigDecimal posLineBigDecimalValueFromDb=null;
	       	     	           for(int canonE580PosTlbCount=0;canonE580PosTlbCount<objCanonE580PosTblTyp.length;canonE580PosTlbCount++)
	       	     	           {	       	     	           
	       	     	        	posLineValueFromDb=(String) objCanonE580PosTblTyp[canonE580PosTlbCount++];
			       	     	    pmsgValuesFromTables.serNumList.no(canonE580PohMsgTypArrayElementCount).poOrdDtlLineNum.setValue(posLineValueFromDb==null?"":posLineValueFromDb);
			       	     	posLineBigDecimalValueFromDb=(BigDecimal) objCanonE580PosTblTyp[canonE580PosTlbCount++];
			       	     	    pmsgValuesFromTables.serNumList.no(canonE580PohMsgTypArrayElementCount).poSerNumPk.setValue(posLineBigDecimalValueFromDb);posLineValueFromDb=(String) objCanonE580PosTblTyp[canonE580PosTlbCount++];
			       	     	    pmsgValuesFromTables.serNumList.no(canonE580PohMsgTypArrayElementCount).serNum.setValue(posLineValueFromDb==null?"":posLineValueFromDb);			       	     	    
	       	     	           }	       	     	        	   
	       	            		 }
	       	            	}
	             	           
	             	          arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	           if(obj[arrayIndexToProcess] instanceof ARRAY)
	       	            	{
	       	            		 ARRAY canoNE580PoaTypArray=(ARRAY)obj[arrayIndexToProcess];
	       	            		 Datum[] datumCanonE580PoaTyp=canoNE580PoaTypArray.getOracleArray();
	       	            		pmsgValuesFromTables.poAcctInfo.setValidCount(datumCanonE580PoaTyp.length);
	       	            		 for(int canonE580PoaTypArrayElementCount=0;canonE580PoaTypArrayElementCount<datumCanonE580PoaTyp.length;canonE580PoaTypArrayElementCount++)
	       	            		 {
	       	     	            STRUCT structCanonE580PoaTypArray = (STRUCT)datumCanonE580PoaTyp[canonE580PoaTypArrayElementCount];
	       	     	           Object[] objCanonE580PoaTblTyp = structCanonE580PoaTypArray.getAttributes();
	       	     	       String posLineValueFromDb="";
	       	     	           for(int canonE580PoaTlbCount=0;canonE580PoaTlbCount<objCanonE580PoaTblTyp.length;canonE580PoaTlbCount++)
	       	     	           {	       	     	           
	       	     	        	posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).poOrdDtlLineNum.setValue(posLineValueFromDb==null?"":posLineValueFromDb);
			       	     	    posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).poAcctTpCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaCmpyCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaAfflCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaBrCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaChCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaCcCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaAcctCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaProdCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaProjCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaExtnCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);
	       	     	           }	       	     	        	   
	       	            		 }
	       	            	}
	             	           
	             	         //  System.out.println("pmsgValuesFromTables "+pmsgValuesFromTables);
	        	            }
	        	            
	        	            pmsgValuesFromTablesList.add(pmsgValuesFromTables);
	                    }
	                    		
	                    
	                    	result[0]=pmsgValuesFromTablesList;
	                    	result[1]=status;
	                    	result[2]=status_msg;
	                    	
	                    
	                    
	                    System.out.println("pmsgValuesFromTablesList size in DAO:"+pmsgValuesFromTablesList.size());   
	                    
	                    
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
	        return result;
	   }
    
    public static Object[] ittMailInvoicesToLov(){
        System.out.println("in ittMailInvoicesToLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_MAIL_INVOICES_TO_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())};
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
        return null;
   }
   
   public static Object[] ittEditModeCheck(String p_user_name){
        System.out.println("in ittEditModeCheck "+p_user_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_EDIT_MODE_CHECK);
                if (statement != null) {
                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)};
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
        return null;
   }
   
   //Added Raghavendra Uppala ITG#690711 Start
   public static Object[] ittGSDOrdertypeCheck(String p_itt_number){              
        System.out.println("in ittGSDOrdertypeCheck "+p_itt_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_GSD_ORDERTYPE_CHECK);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)};
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
        return null;
   }     

      public static Object[] ittGSDLocalShipCheck(String p_itt_number){              
        System.out.println("in ittGSDLocalShipCheck "+p_itt_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_GSD_LOCAL_SHIP_CHECK);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)};
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
        return null;
   } 
   
   //Added Raghavendra Uppala ITG#690711 End
 
   
   public static Object[] ittNotesUpdate(String p_so_number,
    String p_itt_number,
    String p_user_name,
    BigDecimal p_seq_number,
    String p_add_to_popdf){
        System.out.println("in ittNotesUpdate "+p_so_number+"|"+p_itt_number+"|"+p_user_name+"|"+p_seq_number+"|"+p_add_to_popdf);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_NOTES_UPDATE);
                if (statement != null) {
                    statement.setObject(1, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_seq_number, OracleTypes.NUMBER);
                    statement.setObject(5, p_add_to_popdf, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }
   
   public static Object[] eligiblePoLines(String p_itt_number,
    String p_user_name){
        System.out.println("in eligiblePoLines "+p_itt_number+"|"+p_user_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ELIGIBLE_PO_LINES);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(3),ReceivePOInfo.getRowMapper())};
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
        return null;
   }
	// Added by Madhusudan Duna
   public static Object[] eligibleExpensePoLines(String p_itt_number,
    String p_user_name){
        System.out.println("in eligibleExpPoLines "+p_itt_number+"|"+p_user_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ELIGIBLE_EXP_PO_LINES);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(3),ReceiveExpPOInfo.getRowMapper())};
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
        return null;
   }  // Added by Madhusudan Duna

   /*
   {call CANON_E580_PO_RECEIPTS_PKG.ELIGIBLE_PO_LINES('IT-20001462-02','Q05058',?)}
   (
   ITT_NUMBER VARCHAR2,
       SO_NUMBER VARCHAR2,
       SO_LINE_NUMBER VARCHAR2,
       PO_NUMBER VARCHAR2,
       PO_LINE_NUMBER VARCHAR2,
       ORDERED_ITEM VARCHAR2,
       DESCRIPTION VARCHAR2,
       LINE_QTY NUMBER,
       SERIAL_NUMBER_FLAG VARCHAR2,
       LOT_CONTROL_FLAG VARCHAR2,
       LICENSE_NUMBER_FLAG CHAR,
       LICENSE_NUMBER_REQUIRED_FLAG CHAR,
       UNIT_MEAS_LOOKUP_CODE VARCHAR2,
       ORGANIZATION_CODE VARCHAR2,
       LOT_NUMBER VARCHAR2,
       QTY_AVBL NUMBER,
       CLOSED_CODE VARCHAR2,
       QUANTITY_RECEIVED NUMBER,
       DESTINATION_SUBINVENTORY VARCHAR2,
       ITT_LINE_TYPE VARCHAR2,
       ETA_DT VARCHAR2,
       STK_STS_CD VARCHAR2,
       RWS_REF_NUM VARCHAR2,
       DISP_LINE_NUMBER VARCHAR2,
       DISP_ITEM VARCHAR2
   )
   */

   public static class ReceivePOInfo {
      private String ittNumber;
          private String soNumber;
          private String soLineNumber;
          private String poNumber;
          private String poLineNumber;
          private String orderedItem;
          private String description;
          private BigDecimal lineQty;
          private String serialNumberFlag;
          private String lotControlFlag;
          private String licenseNumberFlag;
          private String licenseNumberRequiredFlag;
          private String unitMeasLookupCode;
          private String organizationCode;
          private String lotNumber;
          private BigDecimal qtyAvbl;
          private String closedCode;
          private BigDecimal quantityReceived;
          private String destinationSubinventory;
          private String ittLineType;
          private String etaDt;
          private String stkStsCd;
          private String rwsRefNum;
          private String dispLineNumber;
          private String dispItem;
       
       public ReceivePOInfo(){
       }
       public ReceivePOInfo(String ittNumber, 
               String soNumber, 
               String soLineNumber, 
               String poNumber, 
               String poLineNumber, 
               String orderedItem, 
               String description, 
               BigDecimal lineQty, 
               String serialNumberFlag, 
               String lotControlFlag, 
               String licenseNumberFlag, 
               String licenseNumberRequiredFlag, 
               String unitMeasLookupCode, 
               String organizationCode, 
               String lotNumber, 
               BigDecimal qtyAvbl, 
               String closedCode, 
               BigDecimal quantityReceived, 
               String destinationSubinventory, 
               String ittLineType, 
               String etaDt, 
               String stkStsCd, 
               String rwsRefNum, 
               String dispLineNumber, 
               String dispItem){
           this.ittNumber=ittNumber;
           this.soNumber=soNumber;
           this.soLineNumber=soLineNumber;
           this.poNumber=poNumber;
           this.poLineNumber=poLineNumber;
           this.orderedItem=orderedItem;
           this.description=description;
           this.lineQty=lineQty;
           this.serialNumberFlag=serialNumberFlag;
           this.lotControlFlag=lotControlFlag;
           this.licenseNumberFlag=licenseNumberFlag;
           this.licenseNumberRequiredFlag=licenseNumberRequiredFlag;
           this.unitMeasLookupCode=unitMeasLookupCode;
           this.organizationCode=organizationCode;
           this.lotNumber=lotNumber;
           this.qtyAvbl=qtyAvbl;
           this.closedCode=closedCode;
           this.quantityReceived=quantityReceived;
           this.destinationSubinventory=destinationSubinventory;
           this.ittLineType=ittLineType;
           this.etaDt=etaDt;
           this.stkStsCd=stkStsCd;
           this.rwsRefNum=rwsRefNum;
           this.dispLineNumber=dispLineNumber;
           this.dispItem=dispItem;

       }

       public String getIttNumber() {
           return ittNumber;
       }

       public void setIttNumber(String ittNumber) {
           this.ittNumber=ittNumber;
       }
       public String getSoNumber() {
           return soNumber;
       }

       public void setSoNumber(String soNumber) {
           this.soNumber=soNumber;
       }
       public String getSoLineNumber() {
           return soLineNumber;
       }

       public void setSoLineNumber(String soLineNumber) {
           this.soLineNumber=soLineNumber;
       }
       public String getPoNumber() {
           return poNumber;
       }

       public void setPoNumber(String poNumber) {
           this.poNumber=poNumber;
       }
       public String getPoLineNumber() {
           return poLineNumber;
       }

       public void setPoLineNumber(String poLineNumber) {
           this.poLineNumber=poLineNumber;
       }
       public String getOrderedItem() {
           return orderedItem;
       }

       public void setOrderedItem(String orderedItem) {
           this.orderedItem=orderedItem;
       }
       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description=description;
       }
       public BigDecimal getLineQty() {
           return lineQty;
       }

       public void setLineQty(BigDecimal lineQty) {
           this.lineQty=lineQty;
       }
       public String getSerialNumberFlag() {
           return serialNumberFlag;
       }

       public void setSerialNumberFlag(String serialNumberFlag) {
           this.serialNumberFlag=serialNumberFlag;
       }
       public String getLotControlFlag() {
           return lotControlFlag;
       }

       public void setLotControlFlag(String lotControlFlag) {
           this.lotControlFlag=lotControlFlag;
       }
       public String getLicenseNumberFlag() {
           return licenseNumberFlag;
       }

       public void setLicenseNumberFlag(String licenseNumberFlag) {
           this.licenseNumberFlag=licenseNumberFlag;
       }
       public String getLicenseNumberRequiredFlag() {
           return licenseNumberRequiredFlag;
       }

       public void setLicenseNumberRequiredFlag(String licenseNumberRequiredFlag) {
           this.licenseNumberRequiredFlag=licenseNumberRequiredFlag;
       }
       public String getUnitMeasLookupCode() {
           return unitMeasLookupCode;
       }

       public void setUnitMeasLookupCode(String unitMeasLookupCode) {
           this.unitMeasLookupCode=unitMeasLookupCode;
       }
       public String getOrganizationCode() {
           return organizationCode;
       }

       public void setOrganizationCode(String organizationCode) {
           this.organizationCode=organizationCode;
       }
       public String getLotNumber() {
           return lotNumber;
       }

       public void setLotNumber(String lotNumber) {
           this.lotNumber=lotNumber;
       }
       public BigDecimal getQtyAvbl() {
           return qtyAvbl;
       }

       public void setQtyAvbl(BigDecimal qtyAvbl) {
           this.qtyAvbl=qtyAvbl;
       }
       public String getClosedCode() {
           return closedCode;
       }

       public void setClosedCode(String closedCode) {
           this.closedCode=closedCode;
       }
       public BigDecimal getQuantityReceived() {
           return quantityReceived;
       }

       public void setQuantityReceived(BigDecimal quantityReceived) {
           this.quantityReceived=quantityReceived;
       }
       public String getDestinationSubinventory() {
           return destinationSubinventory;
       }

       public void setDestinationSubinventory(String destinationSubinventory) {
           this.destinationSubinventory=destinationSubinventory;
       }
       public String getIttLineType() {
           return ittLineType;
       }

       public void setIttLineType(String ittLineType) {
           this.ittLineType=ittLineType;
       }
       public String getEtaDt() {
           return etaDt;
       }

       public void setEtaDt(String etaDt) {
           this.etaDt=etaDt;
       }
       public String getStkStsCd() {
           return stkStsCd;
       }

       public void setStkStsCd(String stkStsCd) {
           this.stkStsCd=stkStsCd;
       }
       public String getRwsRefNum() {
           return rwsRefNum;
       }

       public void setRwsRefNum(String rwsRefNum) {
           this.rwsRefNum=rwsRefNum;
       }
       public String getDispLineNumber() {
           return dispLineNumber;
       }

       public void setDispLineNumber(String dispLineNumber) {
           this.dispLineNumber=dispLineNumber;
       }
       public String getDispItem() {
           return dispItem;
       }

       public void setDispItem(String dispItem) {
           this.dispItem=dispItem;
       }
       public static CanonE580RowMapper getRowMapper(){
           return new CanonE580RowMapper() {
               public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                   return new ReceivePOInfo(
                       rs.getString("ITT_NUMBER"),
                       rs.getString("SO_NUMBER"),
                       rs.getString("SO_LINE_NUMBER"),
                       rs.getString("PO_NUMBER"),
                       rs.getString("PO_LINE_NUMBER"),
                       rs.getString("ORDERED_ITEM"),
                       rs.getString("DESCRIPTION"),
                       rs.getBigDecimal("LINE_QTY"),
                       rs.getString("SERIAL_NUMBER_FLAG"),
                       rs.getString("LOT_CONTROL_FLAG"),
                       rs.getString("LICENSE_NUMBER_FLAG"),
                       rs.getString("LICENSE_NUMBER_REQUIRED_FLAG"),
                       rs.getString("UNIT_MEAS_LOOKUP_CODE"),
                       rs.getString("ORGANIZATION_CODE"),
                       rs.getString("LOT_NUMBER"),
                       rs.getBigDecimal("QTY_AVBL"),
                       rs.getString("CLOSED_CODE"),
                       rs.getBigDecimal("QUANTITY_RECEIVED"),
                       rs.getString("DESTINATION_SUBINVENTORY"),
                       rs.getString("ITT_LINE_TYPE"),
                       rs.getString("ETA_DT"),
                       rs.getString("STK_STS_CD"),
                       rs.getString("RWS_REF_NUM"),
                       rs.getString("DISP_LINE_NUMBER"),
                       rs.getString("DISP_ITEM")
                   );
               }
           };
       }
       public String toString() {
           return "ReceivePOInfo{" + "ittNumber="+ittNumber+", soNumber="+soNumber+", soLineNumber="+soLineNumber+", poNumber="+poNumber+", poLineNumber="+poLineNumber+", orderedItem="+orderedItem+", description="+description+", lineQty="+lineQty+", serialNumberFlag="+serialNumberFlag+", lotControlFlag="+lotControlFlag+", licenseNumberFlag="+licenseNumberFlag+", licenseNumberRequiredFlag="+licenseNumberRequiredFlag+", unitMeasLookupCode="+unitMeasLookupCode+", organizationCode="+organizationCode+", lotNumber="+lotNumber+", qtyAvbl="+qtyAvbl+", closedCode="+closedCode+", quantityReceived="+quantityReceived+", destinationSubinventory="+destinationSubinventory+", ittLineType="+ittLineType+", etaDt="+etaDt+", stkStsCd="+stkStsCd+", rwsRefNum="+rwsRefNum+", dispLineNumber="+dispLineNumber+", dispItem="+dispItem+'}';
       }
   }

   /*
   {call CANON_E580_PO_RECEIPTS_PKG.ELIGIBLE_EXPENSE_PO_LINES('IT-20001991-01','Q05058',?)}
   (
   ITT_NUMBER VARCHAR2,
       SO_NUMBER VARCHAR2,
       SO_LINE_NUMBER VARCHAR2,
       PO_NUMBER VARCHAR2,
       PO_LINE_NUMBER VARCHAR2,
       ORDERED_ITEM VARCHAR2,
       DESCRIPTION VARCHAR2,
       LINE_QTY NUMBER,
       SERIAL_NUMBER_FLAG CHAR,
       LOT_CONTROL_FLAG CHAR,
       LICENSE_NUMBER_FLAG CHAR,
       LICENSE_NUMBER_REQUIRED_FLAG CHAR,
       UNIT_MEAS_LOOKUP_CODE VARCHAR2,
       ORGANIZATION_CODE VARCHAR2,
       LOT_NUMBER VARCHAR2,
       QTY_AVBL NUMBER,
       CLOSED_CODE VARCHAR2,
       QUANTITY_RECEIVED NUMBER,
       DESTINATION_SUBINVENTORY VARCHAR2,
       ITT_LINE_TYPE VARCHAR2,
       ETA_DT VARCHAR2,
       STK_STS_CD VARCHAR2,
       RWS_REF_NUM VARCHAR2
   )
   */

   public static class ReceiveExpPOInfo {
      private String ittNumber;
          private String soNumber;
          private String soLineNumber;
          private String poNumber;
          private String poLineNumber;
          private String orderedItem;
          private String description;
          private BigDecimal lineQty;
          private String serialNumberFlag;
          private String lotControlFlag;
          private String licenseNumberFlag;
          private String licenseNumberRequiredFlag;
          private String unitMeasLookupCode;
          private String organizationCode;
          private String lotNumber;
          private BigDecimal qtyAvbl;
          private String closedCode;
          private BigDecimal quantityReceived;
          private String destinationSubinventory;
          private String ittLineType;
          private String etaDt;
          private String stkStsCd;
          private String rwsRefNum;
       
       public ReceiveExpPOInfo(){
       }
       public ReceiveExpPOInfo(String ittNumber, 
               String soNumber, 
               String soLineNumber, 
               String poNumber, 
               String poLineNumber, 
               String orderedItem, 
               String description, 
               BigDecimal lineQty, 
               String serialNumberFlag, 
               String lotControlFlag, 
               String licenseNumberFlag, 
               String licenseNumberRequiredFlag, 
               String unitMeasLookupCode, 
               String organizationCode, 
               String lotNumber, 
               BigDecimal qtyAvbl, 
               String closedCode, 
               BigDecimal quantityReceived, 
               String destinationSubinventory, 
               String ittLineType, 
               String etaDt, 
               String stkStsCd, 
               String rwsRefNum){
           this.ittNumber=ittNumber;
           this.soNumber=soNumber;
           this.soLineNumber=soLineNumber;
           this.poNumber=poNumber;
           this.poLineNumber=poLineNumber;
           this.orderedItem=orderedItem;
           this.description=description;
           this.lineQty=lineQty;
           this.serialNumberFlag=serialNumberFlag;
           this.lotControlFlag=lotControlFlag;
           this.licenseNumberFlag=licenseNumberFlag;
           this.licenseNumberRequiredFlag=licenseNumberRequiredFlag;
           this.unitMeasLookupCode=unitMeasLookupCode;
           this.organizationCode=organizationCode;
           this.lotNumber=lotNumber;
           this.qtyAvbl=qtyAvbl;
           this.closedCode=closedCode;
           this.quantityReceived=quantityReceived;
           this.destinationSubinventory=destinationSubinventory;
           this.ittLineType=ittLineType;
           this.etaDt=etaDt;
           this.stkStsCd=stkStsCd;
           this.rwsRefNum=rwsRefNum;

       }

       public String getIttNumber() {
           return ittNumber;
       }

       public void setIttNumber(String ittNumber) {
           this.ittNumber=ittNumber;
       }
       public String getSoNumber() {
           return soNumber;
       }

       public void setSoNumber(String soNumber) {
           this.soNumber=soNumber;
       }
       public String getSoLineNumber() {
           return soLineNumber;
       }

       public void setSoLineNumber(String soLineNumber) {
           this.soLineNumber=soLineNumber;
       }
       public String getPoNumber() {
           return poNumber;
       }

       public void setPoNumber(String poNumber) {
           this.poNumber=poNumber;
       }
       public String getPoLineNumber() {
           return poLineNumber;
       }

       public void setPoLineNumber(String poLineNumber) {
           this.poLineNumber=poLineNumber;
       }
       public String getOrderedItem() {
           return orderedItem;
       }

       public void setOrderedItem(String orderedItem) {
           this.orderedItem=orderedItem;
       }
       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description=description;
       }
       public BigDecimal getLineQty() {
           return lineQty;
       }

       public void setLineQty(BigDecimal lineQty) {
           this.lineQty=lineQty;
       }
       public String getSerialNumberFlag() {
           return serialNumberFlag;
       }

       public void setSerialNumberFlag(String serialNumberFlag) {
           this.serialNumberFlag=serialNumberFlag;
       }
       public String getLotControlFlag() {
           return lotControlFlag;
       }

       public void setLotControlFlag(String lotControlFlag) {
           this.lotControlFlag=lotControlFlag;
       }
       public String getLicenseNumberFlag() {
           return licenseNumberFlag;
       }

       public void setLicenseNumberFlag(String licenseNumberFlag) {
           this.licenseNumberFlag=licenseNumberFlag;
       }
       public String getLicenseNumberRequiredFlag() {
           return licenseNumberRequiredFlag;
       }

       public void setLicenseNumberRequiredFlag(String licenseNumberRequiredFlag) {
           this.licenseNumberRequiredFlag=licenseNumberRequiredFlag;
       }
       public String getUnitMeasLookupCode() {
           return unitMeasLookupCode;
       }

       public void setUnitMeasLookupCode(String unitMeasLookupCode) {
           this.unitMeasLookupCode=unitMeasLookupCode;
       }
       public String getOrganizationCode() {
           return organizationCode;
       }

       public void setOrganizationCode(String organizationCode) {
           this.organizationCode=organizationCode;
       }
       public String getLotNumber() {
           return lotNumber;
       }

       public void setLotNumber(String lotNumber) {
           this.lotNumber=lotNumber;
       }
       public BigDecimal getQtyAvbl() {
           return qtyAvbl;
       }

       public void setQtyAvbl(BigDecimal qtyAvbl) {
           this.qtyAvbl=qtyAvbl;
       }
       public String getClosedCode() {
           return closedCode;
       }

       public void setClosedCode(String closedCode) {
           this.closedCode=closedCode;
       }
       public BigDecimal getQuantityReceived() {
           return quantityReceived;
       }

       public void setQuantityReceived(BigDecimal quantityReceived) {
           this.quantityReceived=quantityReceived;
       }
       public String getDestinationSubinventory() {
           return destinationSubinventory;
       }

       public void setDestinationSubinventory(String destinationSubinventory) {
           this.destinationSubinventory=destinationSubinventory;
       }
       public String getIttLineType() {
           return ittLineType;
       }

       public void setIttLineType(String ittLineType) {
           this.ittLineType=ittLineType;
       }
       public String getEtaDt() {
           return etaDt;
       }

       public void setEtaDt(String etaDt) {
           this.etaDt=etaDt;
       }
       public String getStkStsCd() {
           return stkStsCd;
       }

       public void setStkStsCd(String stkStsCd) {
           this.stkStsCd=stkStsCd;
       }
       public String getRwsRefNum() {
           return rwsRefNum;
       }

       public void setRwsRefNum(String rwsRefNum) {
           this.rwsRefNum=rwsRefNum;
       }
       public static CanonE580RowMapper getRowMapper(){
           return new CanonE580RowMapper() {
               public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                   return new ReceiveExpPOInfo(
                       rs.getString("ITT_NUMBER"),
                       rs.getString("SO_NUMBER"),
                       rs.getString("SO_LINE_NUMBER"),
                       rs.getString("PO_NUMBER"),
                       rs.getString("PO_LINE_NUMBER"),
                       rs.getString("ORDERED_ITEM"),
                       rs.getString("DESCRIPTION"),
                       rs.getBigDecimal("LINE_QTY"),
                       rs.getString("SERIAL_NUMBER_FLAG"),
                       rs.getString("LOT_CONTROL_FLAG"),
                       rs.getString("LICENSE_NUMBER_FLAG"),
                       rs.getString("LICENSE_NUMBER_REQUIRED_FLAG"),
                       rs.getString("UNIT_MEAS_LOOKUP_CODE"),
                       rs.getString("ORGANIZATION_CODE"),
                       rs.getString("LOT_NUMBER"),
                       rs.getBigDecimal("QTY_AVBL"),
                       rs.getString("CLOSED_CODE"),
                       rs.getBigDecimal("QUANTITY_RECEIVED"),
                       rs.getString("DESTINATION_SUBINVENTORY"),
                       rs.getString("ITT_LINE_TYPE"),
                       rs.getString("ETA_DT"),
                       rs.getString("STK_STS_CD"),
                       rs.getString("RWS_REF_NUM")
                   );
               }
           };
       }
       public String toString() {
           return "ReceiveExpPOInfo{" + "ittNumber="+ittNumber+", soNumber="+soNumber+", soLineNumber="+soLineNumber+", poNumber="+poNumber+", poLineNumber="+poLineNumber+", orderedItem="+orderedItem+", description="+description+", lineQty="+lineQty+", serialNumberFlag="+serialNumberFlag+", lotControlFlag="+lotControlFlag+", licenseNumberFlag="+licenseNumberFlag+", licenseNumberRequiredFlag="+licenseNumberRequiredFlag+", unitMeasLookupCode="+unitMeasLookupCode+", organizationCode="+organizationCode+", lotNumber="+lotNumber+", qtyAvbl="+qtyAvbl+", closedCode="+closedCode+", quantityReceived="+quantityReceived+", destinationSubinventory="+destinationSubinventory+", ittLineType="+ittLineType+", etaDt="+etaDt+", stkStsCd="+stkStsCd+", rwsRefNum="+rwsRefNum+'}';
       }
   }
   

   public static Object[] insertReceiptPrc(String p_itt_number,
    String p_user_name,
    String p_itt_line_num,
    String p_po_number,
    String p_po_line_num,
    String p_item_name,
    String p_item_desc,
    BigDecimal p_rcvd_qty,
    String p_inv_org,
    String p_subinv,
    String p_lot_num,
    String p_serial_num,
    String p_license_number){
        System.out.println("in insertReceiptPrc "+p_itt_number+"|"+p_user_name+"|"+p_itt_line_num+"|"+p_po_number+"|"+p_po_line_num+"|"+p_item_name+"|"+p_item_desc+"|"+p_rcvd_qty+"|"+p_inv_org+"|"+p_subinv+"|"+p_lot_num+"|"+p_serial_num+"|"+p_license_number);
        
        
        CanonE580ITTWorkbenchDAO.ReceivePoType reveicePo=null;       
        reveicePo=new CanonE580ITTWorkbenchDAO.ReceivePoType(p_itt_number,p_itt_line_num,p_po_number,p_po_line_num,p_item_name,p_item_desc,p_rcvd_qty,p_inv_org,p_subinv,p_lot_num,p_serial_num,p_license_number);
                
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(INSERT_RECEIPT_PRC);
                if (statement != null) {
                	
                	connection.getTypeMap().put("CANON_E580_POR_TYP_REC", ReceivePoType.class);
                    statement.setObject(1, reveicePo);                    
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);                   
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(3)
                        ,statement.getObject(4)};
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
        return null;
   }
   //Added by Madhusudan Duna
   public static Object[] insertExpReceiptPrc(String p_itt_number,
    String p_user_name,
    String p_itt_line_num,
    BigDecimal p_po_number,
    BigDecimal p_po_line_num,
    String p_item_name,
    String p_item_desc,
    BigDecimal p_rcvd_qty,
    String p_inv_org,
    String p_subinv,
    String p_lot_num,
    String p_serial_num,
    String p_license_number){
        System.out.println("in insertExpReceiptPrc "+p_itt_number+"|"+p_user_name+"|"+p_itt_line_num+"|"+p_po_number+"|"+p_po_line_num+"|"+p_item_name+"|"+p_item_desc+"|"+p_rcvd_qty+"|"+p_inv_org+"|"+p_subinv+"|"+p_lot_num+"|"+p_serial_num+"|"+p_license_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(INSERT_EXP_RECEIPT_PRC);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_itt_line_num, OracleTypes.VARCHAR);
                    statement.setObject(4, p_po_number, OracleTypes.NUMBER);
                    statement.setObject(5, p_po_line_num, OracleTypes.NUMBER);
                    statement.setObject(6, p_item_name, OracleTypes.VARCHAR);
                    statement.setObject(7, p_item_desc, OracleTypes.VARCHAR);
                    statement.setObject(8, p_rcvd_qty, OracleTypes.NUMBER);
                    statement.setObject(9, p_inv_org, OracleTypes.VARCHAR);
                    statement.setObject(10, p_subinv, OracleTypes.VARCHAR);
                    statement.setObject(11, p_lot_num, OracleTypes.VARCHAR);
                    statement.setObject(12, p_serial_num, OracleTypes.VARCHAR);
                    statement.setObject(13, p_license_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(14, OracleTypes.VARCHAR);
                    statement.registerOutParameter(15, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(14)
                        ,statement.getObject(15)};
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
        return null;
   }
   //Added by Madhusudan Duna
   
   public static Object[] canonCreateReceiptPrc(String p_itt_number,
    String p_user_name,
    BigDecimal p_po_number){
        System.out.println("in canonCreateReceiptPrc "+p_itt_number+"|"+p_user_name+"|"+p_po_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CANON_CREATE_RECEIPT_PRC);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_po_number, OracleTypes.NUMBER);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(4)
                        ,statement.getObject(5)};
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
        return null;
   }

   public static Object[] ittSubInvLov(String p_inv_org_code){
        System.out.println("in ittSubInvLov "+p_inv_org_code);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_SUB_INV_LOV);
                if (statement != null) {
                    statement.setObject(1, p_inv_org_code, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())};
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
        return null;
   }

   public static Object[] ittLinesOnHold(String p_itt_number){
        System.out.println("in ittLinesOnHold "+p_itt_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_LINES_ON_HOLD);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),LinesOnHoldInfo.getRowMapper()),
                    		cursorToList((ResultSet)statement.getObject(3),MachConfInfo.getRowMapper())};
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
        return null;
   }

   /*
   {call CANON_E580_ITT_UTIL_PKG.ITT_LINES_ON_HOLD('IT-20001462-02',?,?)}
   (
   ITT_NUMBER VARCHAR2,
       SO_NUMBER VARCHAR2,
       SO_LINE_NUMBER VARCHAR2,
       PO_NUMBER VARCHAR2,
       PO_LINE_NUMBER VARCHAR2,
       ITEM VARCHAR2,
       ITEM_DESCRIPTION VARCHAR2,
       RELEASED_FLAG VARCHAR2,
       HOLD_FLAG VARCHAR2,
       HOLD_PK NUMBER,
       HOLD_RSN_CD VARCHAR2,
       SO_SUB_LINE_NUMBER VARCHAR2,
       DISP_LINE_NUMBER VARCHAR2,
       DISP_ITEM VARCHAR2,
       DELIV_CONF_FLAG VARCHAR2
   )
   */

   public static class LinesOnHoldInfo {
      private String ittNumber;
          private String soNumber;
          private String soLineNumber;
          private String poNumber;
          private String poLineNumber;
          private String item;
          private String itemDescription;
          private String releasedFlag;
          private String holdFlag;
          private BigDecimal holdPk;
          private String holdRsnCd;
          private String soSubLineNumber;
          private String dispLineNumber;
          private String dispItem;
          private String delivConfFlag;
       
       public LinesOnHoldInfo(){
       }
       public LinesOnHoldInfo(String ittNumber, 
               String soNumber, 
               String soLineNumber, 
               String poNumber, 
               String poLineNumber, 
               String item, 
               String itemDescription, 
               String releasedFlag, 
               String holdFlag, 
               BigDecimal holdPk, 
               String holdRsnCd, 
               String soSubLineNumber, 
               String dispLineNumber, 
               String dispItem, 
               String delivConfFlag){
           this.ittNumber=ittNumber;
           this.soNumber=soNumber;
           this.soLineNumber=soLineNumber;
           this.poNumber=poNumber;
           this.poLineNumber=poLineNumber;
           this.item=item;
           this.itemDescription=itemDescription;
           this.releasedFlag=releasedFlag;
           this.holdFlag=holdFlag;
           this.holdPk=holdPk;
           this.holdRsnCd=holdRsnCd;
           this.soSubLineNumber=soSubLineNumber;
           this.dispLineNumber=dispLineNumber;
           this.dispItem=dispItem;
           this.delivConfFlag=delivConfFlag;

       }

       public String getIttNumber() {
           return ittNumber;
       }

       public void setIttNumber(String ittNumber) {
           this.ittNumber=ittNumber;
       }
       public String getSoNumber() {
           return soNumber;
       }

       public void setSoNumber(String soNumber) {
           this.soNumber=soNumber;
       }
       public String getSoLineNumber() {
           return soLineNumber;
       }

       public void setSoLineNumber(String soLineNumber) {
           this.soLineNumber=soLineNumber;
       }
       public String getPoNumber() {
           return poNumber;
       }

       public void setPoNumber(String poNumber) {
           this.poNumber=poNumber;
       }
       public String getPoLineNumber() {
           return poLineNumber;
       }

       public void setPoLineNumber(String poLineNumber) {
           this.poLineNumber=poLineNumber;
       }
       public String getItem() {
           return item;
       }

       public void setItem(String item) {
           this.item=item;
       }
       public String getItemDescription() {
           return itemDescription;
       }

       public void setItemDescription(String itemDescription) {
           this.itemDescription=itemDescription;
       }
       public String getReleasedFlag() {
           return releasedFlag;
       }

       public void setReleasedFlag(String releasedFlag) {
           this.releasedFlag=releasedFlag;
       }
       public String getHoldFlag() {
           return holdFlag;
       }

       public void setHoldFlag(String holdFlag) {
           this.holdFlag=holdFlag;
       }
       public BigDecimal getHoldPk() {
           return holdPk;
       }

       public void setHoldPk(BigDecimal holdPk) {
           this.holdPk=holdPk;
       }
       public String getHoldRsnCd() {
           return holdRsnCd;
       }

       public void setHoldRsnCd(String holdRsnCd) {
           this.holdRsnCd=holdRsnCd;
       }
       public String getSoSubLineNumber() {
           return soSubLineNumber;
       }

       public void setSoSubLineNumber(String soSubLineNumber) {
           this.soSubLineNumber=soSubLineNumber;
       }
       public String getDispLineNumber() {
           return dispLineNumber;
       }

       public void setDispLineNumber(String dispLineNumber) {
           this.dispLineNumber=dispLineNumber;
       }
       public String getDispItem() {
           return dispItem;
       }

       public void setDispItem(String dispItem) {
           this.dispItem=dispItem;
       }
       public String getDelivConfFlag() {
           return delivConfFlag;
       }

       public void setDelivConfFlag(String delivConfFlag) {
           this.delivConfFlag=delivConfFlag;
       }
       public static CanonE580RowMapper getRowMapper(){
           return new CanonE580RowMapper() {
               public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                   return new LinesOnHoldInfo(
                       rs.getString("ITT_NUMBER"),
                       rs.getString("SO_NUMBER"),
                       rs.getString("SO_LINE_NUMBER"),
                       rs.getString("PO_NUMBER"),
                       rs.getString("PO_LINE_NUMBER"),
                       rs.getString("ITEM"),
                       rs.getString("ITEM_DESCRIPTION"),
                       rs.getString("RELEASED_FLAG"),
                       rs.getString("HOLD_FLAG"),
                       rs.getBigDecimal("HOLD_PK"),
                       rs.getString("HOLD_RSN_CD"),
                       rs.getString("SO_SUB_LINE_NUMBER"),
                       rs.getString("DISP_LINE_NUMBER"),
                       rs.getString("DISP_ITEM"),
                       rs.getString("DELIV_CONF_FLAG")
                   );
               }
           };
       }
       public String toString() {
           return "LinesOnHoldInfo{" + "ittNumber="+ittNumber+", soNumber="+soNumber+", soLineNumber="+soLineNumber+", poNumber="+poNumber+", poLineNumber="+poLineNumber+", item="+item+", itemDescription="+itemDescription+", releasedFlag="+releasedFlag+", holdFlag="+holdFlag+", holdPk="+holdPk+", holdRsnCd="+holdRsnCd+", soSubLineNumber="+soSubLineNumber+", dispLineNumber="+dispLineNumber+", dispItem="+dispItem+", delivConfFlag="+delivConfFlag+'}';
       }
   }

    
   public static Object[] releaseHold(String p_so_number,
    String p_itt_number,
    String p_itt_line_num,
    String p_comments,
    String p_user_name){
        System.out.println("in releaseHold "+p_so_number+"|"+p_itt_number+"|"+p_itt_line_num+"|"+p_comments+"|"+p_user_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(RELEASE_HOLD);
                if (statement != null) {
                    statement.setObject(1, p_so_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_itt_line_num, OracleTypes.VARCHAR);
                    statement.setObject(4, p_comments, OracleTypes.VARCHAR);
                    statement.setObject(5, p_user_name, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{};
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
        return null;
   }    
 
   public static Object[] ittDealerShipToCnaCode(String p_dealer_ship_to_cna_code){
        System.out.println("in ittDealerShipToCnaCode "+p_dealer_ship_to_cna_code);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_DEALER_SHIP_TO_CNA_CODE);
                if (statement != null) {
                    statement.setObject(1, p_dealer_ship_to_cna_code, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ShipToCNACodeInfo.getRowMapper())};
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
        return null;
   }
   
    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_SHIP_TO_CNA_CODE(?)}
    (
    SHIP_TO_CNA_CODE VARCHAR2,
    SHIP_TO_CNA_ADDRESS VARCHAR2,
    SHIP_TO_CNA_ADDRESS2 VARCHAR2,
    SHIP_TO_CNA_CITY VARCHAR2,
    SHIP_TO_CNA_STATE VARCHAR2,
    SHIP_TO_CNA_ZIP_CODE VARCHAR2
    )
    */
    public static class ShipToCNACodeInfo {
       private String shipToCnaCode;
       private String shipToCnaAddress;
       private String shipToCnaAddress2;
       private String shipToCnaCity;
       private String shipToCnaState;
       private String shipToCnaZipCode;

        public ShipToCNACodeInfo(){
        }
        public ShipToCNACodeInfo(String shipToCnaCode, 
                String shipToCnaAddress, 
                String shipToCnaAddress2, 
                String shipToCnaCity, 
                String shipToCnaState, 
                String shipToCnaZipCode){
            this.shipToCnaCode=shipToCnaCode;
            this.shipToCnaAddress=shipToCnaAddress;
            this.shipToCnaAddress2=shipToCnaAddress2;
            this.shipToCnaCity=shipToCnaCity;
            this.shipToCnaState=shipToCnaState;
            this.shipToCnaZipCode=shipToCnaZipCode;
        }
        public String getShipToCnaCode() {
            return shipToCnaCode;
        }
        public void setShipToCnaCode(String shipToCnaCode) {
            this.shipToCnaCode=shipToCnaCode;
        }
        public String getShipToCnaAddress() {
            return shipToCnaAddress;
        }
        public void setShipToCnaAddress(String shipToCnaAddress) {
            this.shipToCnaAddress=shipToCnaAddress;
        }
        public String getShipToCnaAddress2() {
            return shipToCnaAddress2;
        }
        public void setShipToCnaAddress2(String shipToCnaAddress2) {
            this.shipToCnaAddress2=shipToCnaAddress2;
        }
        public String getShipToCnaCity() {
            return shipToCnaCity;
        }
        public void setShipToCnaCity(String shipToCnaCity) {
            this.shipToCnaCity=shipToCnaCity;
        }
        public String getShipToCnaState() {
            return shipToCnaState;
        }
        public void setShipToCnaState(String shipToCnaState) {
            this.shipToCnaState=shipToCnaState;
        }
        public String getShipToCnaZipCode() {
            return shipToCnaZipCode;
        }
        public void setShipToCnaZipCode(String shipToCnaZipCode) {
            this.shipToCnaZipCode=shipToCnaZipCode;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ShipToCNACodeInfo(
                        rs.getString("SHIP_TO_CNA_CODE"),
                        rs.getString("SHIP_TO_CNA_ADDRESS"),
                        rs.getString("SHIP_TO_CNA_ADDRESS2"),
                        rs.getString("SHIP_TO_CNA_CITY"),
                        rs.getString("SHIP_TO_CNA_STATE"),
                        rs.getString("SHIP_TO_CNA_ZIP_CODE")
                    );
                }
            };
        }
        public String toString() {
            return "ShipToCNACodeInfo{" + "shipToCnaCode="+shipToCnaCode+", shipToCnaAddress="+shipToCnaAddress+", shipToCnaAddress2="+shipToCnaAddress2+", shipToCnaCity="+shipToCnaCity+", shipToCnaState="+shipToCnaState+", shipToCnaZipCode="+shipToCnaZipCode+'}';
        }
    }

    public static Object[] ittDealerShipToCnaDname(String p_dealer_ship_to_cna_dname){
        System.out.println("in ittDealerShipToCnaDname "+p_dealer_ship_to_cna_dname);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_DEALER_SHIP_TO_CNA_DNAME);
                if (statement != null) {
                    statement.setObject(1, p_dealer_ship_to_cna_dname, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ShipToCNADnameInfo.getRowMapper())};
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
        return null;
   }
   
    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_DEALER_SHIP_TO_CNA_DNAME(?)}
    (
    SHIP_TO_CNA_CODE VARCHAR2,
    SHIP_TO_CNA_ADDRESS VARCHAR2,
    SHIP_TO_CNA_ADDRESS2 VARCHAR2,
    SHIP_TO_CNA_CITY VARCHAR2,
    SHIP_TO_CNA_STATE VARCHAR2,
    SHIP_TO_CNA_ZIP_CODE VARCHAR2
    )
    */
    public static class ShipToCNADnameInfo {
       private String shipToCnaCode;
       private String shipToCnaAddress;
       private String shipToCnaAddress2;
       private String shipToCnaCity;
       private String shipToCnaState;
       private String shipToCnaZipCode;

        public ShipToCNADnameInfo(){
        }
        public ShipToCNADnameInfo(String shipToCnaCode, 
                String shipToCnaAddress, 
                String shipToCnaAddress2, 
                String shipToCnaCity, 
                String shipToCnaState, 
                String shipToCnaZipCode){
            this.shipToCnaCode=shipToCnaCode;
            this.shipToCnaAddress=shipToCnaAddress;
            this.shipToCnaAddress2=shipToCnaAddress2;
            this.shipToCnaCity=shipToCnaCity;
            this.shipToCnaState=shipToCnaState;
            this.shipToCnaZipCode=shipToCnaZipCode;
        }
        public String getShipToCnaCode() {
            return shipToCnaCode;
        }
        public void setShipToCnaCode(String shipToCnaCode) {
            this.shipToCnaCode=shipToCnaCode;
        }
        public String getShipToCnaAddress() {
            return shipToCnaAddress;
        }
        public void setShipToCnaAddress(String shipToCnaAddress) {
            this.shipToCnaAddress=shipToCnaAddress;
        }
        public String getShipToCnaAddress2() {
            return shipToCnaAddress2;
        }
        public void setShipToCnaAddress2(String shipToCnaAddress2) {
            this.shipToCnaAddress2=shipToCnaAddress2;
        }
        public String getShipToCnaCity() {
            return shipToCnaCity;
        }
        public void setShipToCnaCity(String shipToCnaCity) {
            this.shipToCnaCity=shipToCnaCity;
        }
        public String getShipToCnaState() {
            return shipToCnaState;
        }
        public void setShipToCnaState(String shipToCnaState) {
            this.shipToCnaState=shipToCnaState;
        }
        public String getShipToCnaZipCode() {
            return shipToCnaZipCode;
        }
        public void setShipToCnaZipCode(String shipToCnaZipCode) {
            this.shipToCnaZipCode=shipToCnaZipCode;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ShipToCNADnameInfo(
                        rs.getString("SHIP_TO_CNA_CODE"),
                        rs.getString("SHIP_TO_CNA_ADDRESS"),
                        rs.getString("SHIP_TO_CNA_ADDRESS2"),
                        rs.getString("SHIP_TO_CNA_CITY"),
                        rs.getString("SHIP_TO_CNA_STATE"),
                        rs.getString("SHIP_TO_CNA_ZIP_CODE")
                    );
                }
            };
        }
        public String toString() {
            return "ShipToCNADnameInfo{" + "shipToCnaCode="+shipToCnaCode+", shipToCnaAddress="+shipToCnaAddress+", shipToCnaAddress2="+shipToCnaAddress2+", shipToCnaCity="+shipToCnaCity+", shipToCnaState="+shipToCnaState+", shipToCnaZipCode="+shipToCnaZipCode+'}';
        }
    }  
    
   public static Object[] ittSchdlDelivDtReasonCode(){
        System.out.println("in ittSchdlDelivDtReasonCode ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_SCHDL_DELIV_DT_REASON_CODE);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),ReasonCodeInfo.getRowMapper())};
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
        return null;
   }    

    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_SCHDL_DELIV_DT_REASON_CODE(?)}
    (
    FLEX_VALUE_MEANING VARCHAR2
    )
    */
    public static class ReasonCodeInfo {
       private String flexValueMeaning;
       private String description;

        public ReasonCodeInfo(){
        }
        public ReasonCodeInfo(String flexValueMeaning, 
                String description){
            this.flexValueMeaning=flexValueMeaning;
            this.description=description;
        }
        public String getFlexValueMeaning() {
            return flexValueMeaning;
        }
        public void setFlexValueMeaning(String flexValueMeaning) {
            this.flexValueMeaning=flexValueMeaning;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description=description;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ReasonCodeInfo(
                        rs.getString("FLEX_VALUE_MEANING"),
                        rs.getString("DESCRIPTION")
                    );
                }
            };
        }
        public String toString() {
            return "ReasonCodeInfo{" + "flexValueMeaning="+flexValueMeaning+", description="+description+'}';
        }
    }

   public static Object[] ittDelivDtReschdlHistory(String p_itt_number,
    String p_line_number){
        System.out.println("in ittDelivDtReschdlHistory "+p_itt_number+"|"+p_line_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITT_DELIV_DT_RESCHDL_HISTORY);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_line_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(3),ScheduledDTHistoryInfo.getRowMapper())};
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
        return null;
   }

    // Database name CANDEV
    /*
    {call CANON_E580_ITT_UTIL_PKG.ITT_DELIV_DT_RESCHDL_HISTORY('','',?)}
    (
    VERSION_NUMBER NUMBER,
    LAST_UPDATE_BY VARCHAR2,
    SCHEDULED_DELIVERY_DATE DATE,
    REASON_CODE VARCHAR2,
    SCHEDULE_STATUS VARCHAR2,
    LAST_UPDATE_DATE DATE
    )
    */
    public static class ScheduledDTHistoryInfo {
       private BigDecimal versionNumber;
       private String lastUpdateBy;
       private Timestamp scheduledDeliveryDate;
       private String reasonCode;
       private String scheduleStatus;
       private Timestamp lastUpdateDate;

        public ScheduledDTHistoryInfo(){
        }
        public ScheduledDTHistoryInfo(BigDecimal versionNumber, 
                String lastUpdateBy, 
                Timestamp scheduledDeliveryDate, 
                String reasonCode, 
                String scheduleStatus, 
                Timestamp lastUpdateDate){
            this.versionNumber=versionNumber;
            this.lastUpdateBy=lastUpdateBy;
            this.scheduledDeliveryDate=scheduledDeliveryDate;
            this.reasonCode=reasonCode;
            this.scheduleStatus=scheduleStatus;
            this.lastUpdateDate=lastUpdateDate;
        }
        public BigDecimal getVersionNumber() {
            return versionNumber;
        }
        public void setVersionNumber(BigDecimal versionNumber) {
            this.versionNumber=versionNumber;
        }
        public String getLastUpdateBy() {
            return lastUpdateBy;
        }
        public void setLastUpdateBy(String lastUpdateBy) {
            this.lastUpdateBy=lastUpdateBy;
        }
        public Timestamp getScheduledDeliveryDate() {
            return scheduledDeliveryDate;
        }
        public void setScheduledDeliveryDate(Timestamp scheduledDeliveryDate) {
            this.scheduledDeliveryDate=scheduledDeliveryDate;
        }
        public String getReasonCode() {
            return reasonCode;
        }
        public void setReasonCode(String reasonCode) {
            this.reasonCode=reasonCode;
        }
        public String getScheduleStatus() {
            return scheduleStatus;
        }
        public void setScheduleStatus(String scheduleStatus) {
            this.scheduleStatus=scheduleStatus;
        }
        public Timestamp getLastUpdateDate() {
            return lastUpdateDate;
        }
        public void setLastUpdateDate(Timestamp lastUpdateDate) {
            this.lastUpdateDate=lastUpdateDate;
        }
        public static CanonE580RowMapper getRowMapper(){
            return new CanonE580RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ScheduledDTHistoryInfo(
                        rs.getBigDecimal("VERSION_NUMBER"),
                        rs.getString("LAST_UPDATE_BY"),
                        rs.getTimestamp("SCHEDULED_DELIVERY_DATE"),
                        rs.getString("REASON_CODE"),
                        rs.getString("SCHEDULE_STATUS"),
                        rs.getTimestamp("LAST_UPDATE_DATE")
                    );
                }
            };
        }
        public String toString() {
            return "ScheduledDTHistoryInfo{" + "versionNumber="+versionNumber+", lastUpdateBy="+lastUpdateBy+", scheduledDeliveryDate="+scheduledDeliveryDate+", reasonCode="+reasonCode+", scheduleStatus="+scheduleStatus+", lastUpdateDate="+lastUpdateDate+'}';
        }
    }

   

   public static Object[] receivePoCheck(String p_itt_number){
        System.out.println("in receivePoCheck "+p_itt_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(RECEIVE_PO_CHECK);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)
                        ,statement.getObject(3)};
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
        return null;
   }
   
   public static Object[] serialNumberCheck(String p_itt_number,
    String p_serial_number){
        System.out.println("in serialNumberCheck "+p_itt_number+"|"+p_serial_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SERIAL_NUMBER_CHECK);
                if (statement != null) {
                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_serial_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(3)
                        ,statement.getObject(4)};
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
        return null;
   }
   
   
   public static class ReceivePoType implements java.sql.SQLData, java.io.Serializable{
       private String ittNumber;
       private String ittLineNum;
       private String ponumber;   
       private String polinenum;    
       private String itemname;   
       private String itemdesc;     
       private BigDecimal rcvdqty;      
       private String invorg;       
       private String subinv;       
       private String lotnum;       
       private String serialnum;    
       private String licensenumber;

        public ReceivePoType(){
        }
        public ReceivePoType(
        		String ittNumber,
        		String ittLineNum,
        		String ponumber,   
                String polinenum,    
                String itemname,     
                String itemdesc,     
                BigDecimal rcvdqty,      
                String invorg,       
                String subinv,       
                String lotnum,       
                String serialnum,    
                String licensenumber){
            this.ittNumber=ittNumber;
            this.ittLineNum=ittLineNum;
            this.ponumber=ponumber;
            this.polinenum=polinenum;
            this.itemname=itemname;
            this.itemdesc=itemdesc;
            this.rcvdqty=rcvdqty;
            this.invorg=invorg;
            this.subinv=subinv;
            this.lotnum=lotnum;
            this.serialnum=serialnum;
            this.licensenumber=licensenumber;
        }
        
        public String getIttNumber() {
			return ittNumber;
		}
		public void setIttNumber(String ittNumber) {
			this.ittNumber = ittNumber;
		}
		public String getIttLineNum() {
			return ittLineNum;
		}
		public void setIttLineNum(String ittLineNum) {
			this.ittLineNum = ittLineNum;
		}
		public String getPonumber() {
			return ponumber;
		}
		public void setPonumber(String ponumber) {
			this.ponumber = ponumber;
		}
		public String getPolinenum() {
			return polinenum;
		}
		public void setPolinenum(String polinenum) {
			this.polinenum = polinenum;
		}
		public String getItemname() {
			return itemname;
		}
		public void setItemname(String itemname) {
			this.itemname = itemname;
		}
		public String getItemdesc() {
			return itemdesc;
		}
		public void setItemdesc(String itemdesc) {
			this.itemdesc = itemdesc;
		}
		
		
		public BigDecimal getRcvdqty() {
			return rcvdqty;
		}
		public void setRcvdqty(BigDecimal rcvdqty) {
			this.rcvdqty = rcvdqty;
		}
		public String getInvorg() {
			return invorg;
		}
		public void setInvorg(String invorg) {
			this.invorg = invorg;
		}
		public String getSubinv() {
			return subinv;
		}
		public void setSubinv(String subinv) {
			this.subinv = subinv;
		}
		public String getLotnum() {
			return lotnum;
		}
		public void setLotnum(String lotnum) {
			this.lotnum = lotnum;
		}
		public String getSerialnum() {
			return serialnum;
		}
		public void setSerialnum(String serialnum) {
			this.serialnum = serialnum;
		}
		public String getLicensenumber() {
			return licensenumber;
		}
		public void setLicensenumber(String licensenumber) {
			this.licensenumber = licensenumber;
		}
		public String getSQLTypeName() throws SQLException {
            return "CANON_E580_POR_TYP_REC";
        }

        public void readSQL(SQLInput stream, String typeName) throws SQLException {
        	   ittNumber = stream.readString();
        	   ittLineNum = stream.readString();
               ponumber = stream.readString();
               polinenum = stream.readString();
               itemname = stream.readString();
               itemdesc = stream.readString();                   
               rcvdqty = stream.readBigDecimal();
               invorg = stream.readString();
               subinv = stream.readString();                   
               lotnum = stream.readString();
               serialnum = stream.readString();
               licensenumber = stream.readString();               
        }

        public void writeSQL(SQLOutput stream) throws SQLException {
               stream.writeString(ittNumber);
               stream.writeString(ittLineNum);
               stream.writeString(ponumber);
               stream.writeString(polinenum);
               stream.writeString(itemname);
               stream.writeString(itemdesc);
               stream.writeBigDecimal(rcvdqty);
               stream.writeString(invorg);
               stream.writeString(subinv);
               stream.writeString(lotnum);
               stream.writeString(serialnum);
               stream.writeString(licensenumber);               

        }
        public String toString() {
            return "ReceivePoType{" + "ittNumber="+ittNumber+", ittLineNum="+ittLineNum+", ponumber="+ponumber+", polinenum="+polinenum+", itemname="+itemname+", itemdesc="+itemdesc+", rcvdqty="+rcvdqty+", invorg="+invorg+", subinv="+subinv+", lotnum="+lotnum+", serialnum="+serialnum+", licensenumber="+licensenumber+'}';
        }
    }
   public static void updatePoItt(String p_itt_number,String p_po_number,String p_err_msg,String p_user_name){
       System.out.println("in updatePoItt "+p_itt_number);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(UPDATE_PO_ITT);
               if (statement != null) {
                   statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                   statement.setObject(2, p_po_number, OracleTypes.VARCHAR);
                   statement.setObject(3, p_err_msg, OracleTypes.VARCHAR);
                   statement.setObject(4, p_user_name, OracleTypes.VARCHAR);
                   
                   statement.execute();                   
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
   
   public static Object[] dealerInstallConfirm(String p_itt_number,
		    String p_so_number,
		    String p_line_number,
		    String p_username,
		    String p_err_msg,
		    CanonE580ITTWorkbenchCallback<Object> callback){
	   			logMsg("in dealerInstallConfirm "+p_itt_number+"|"+p_so_number+"|"+p_line_number+"|"+p_username+"|"+p_err_msg);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
	        		connection =((ConnectionAware)callback).getConnection();
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(DEALER_INSTALL_CONFIRM);
		                if (statement != null) {
		                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
		                    statement.setObject(2, p_so_number, OracleTypes.VARCHAR);
		                    statement.setObject(3, p_line_number, OracleTypes.VARCHAR);
		                    statement.setObject(4, p_username, OracleTypes.VARCHAR);
		                    statement.setObject(5, p_err_msg, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
		                    statement.execute();
		                    String flag=(String)statement.getObject(6);
		                    String errmsg=(String)statement.getObject(7);
		                    logMsg("in dealerInstallConfirm returns:"+flag+"|"+errmsg);
		                    if("S".equals(flag)) {
			                    callback.onSuccess();
		                    }else {
		                    	logMsg(errmsg);
					            callback.onError("Database Error.");
		                    }
		                    return new Object[]{flag,errmsg};
		                } else {
		                	String err="DBStatus.UNABLE_TO_CREATE_STATEMENT ";
		                	logMsg(err);
				            callback.onError(err);
		                }
		            } else {
		                String err="DBStatus.UNABLE_TO_GET_CONNECTION";
		                logMsg(err);
			            callback.onError(err);
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            callback.onError(ex.getMessage());
		        } finally {
					if (statement != null) {
					    try {
					        statement.close();
					        statement = null;
					    } catch (Exception exp) {
					        exp.printStackTrace();
					    }
					}
		     	   callback.onFinally();
		        }
		        return null;
		   }
   
   public static void updatePorError(String p_itt_number,String itt_line_num,String p_po_number,String p_err_msg,String p_user_name){
       System.out.println("in updatePoItt "+p_itt_number+" itt_line_num:"+itt_line_num+" p_po_number:"+p_po_number+" p_err_msg:"+p_err_msg+" p_user_name:"+p_user_name);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(UPDATE_POR_ERROR);
               if (statement != null) {
                   statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
                   statement.setObject(2, itt_line_num, OracleTypes.VARCHAR);
                   statement.setObject(3, p_po_number, OracleTypes.VARCHAR);
                   statement.setObject(4, p_err_msg, OracleTypes.VARCHAR);
                   statement.setObject(5, p_user_name, OracleTypes.VARCHAR);
                   
                   statement.execute();                   
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
   
   public static Object[] getExpenseItem(){
       System.out.println("in getExpenseItem ");
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_EXPENSE_ITEM);
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
   
   /* output cursor position 2 */
   /*
   {call CANON_E580_ITT_UTIL_PKG.ITT_LINES_ON_HOLD('IT-20001553-01',?,?)}
   (
   SVC_CONFIG_MSTR_PK NUMBER,
       SER_NUM VARCHAR2,
       SVC_MACH_MSTR_PK NUMBER,
       STK_STS_CD VARCHAR2,
       SVC_MACH_USG_STS_CD VARCHAR2,
       SVC_MACH_MSTR_LOC_STS_CD VARCHAR2
   )
   */

   public static class MachConfInfo {
      private BigDecimal svcConfigMstrPk;
          private String serNum;
          private BigDecimal svcMachMstrPk;
          private String stkStsCd;
          private String svcMachUsgStsCd;
          private String svcMachMstrLocStsCd;
       
       public MachConfInfo(){
       }
       public MachConfInfo(BigDecimal svcConfigMstrPk, 
               String serNum, 
               BigDecimal svcMachMstrPk, 
               String stkStsCd, 
               String svcMachUsgStsCd, 
               String svcMachMstrLocStsCd){
           this.svcConfigMstrPk=svcConfigMstrPk;
           this.serNum=serNum;
           this.svcMachMstrPk=svcMachMstrPk;
           this.stkStsCd=stkStsCd;
           this.svcMachUsgStsCd=svcMachUsgStsCd;
           this.svcMachMstrLocStsCd=svcMachMstrLocStsCd;

       }

       public BigDecimal getSvcConfigMstrPk() {
           return svcConfigMstrPk;
       }

       public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
           this.svcConfigMstrPk=svcConfigMstrPk;
       }
       public String getSerNum() {
           return serNum;
       }

       public void setSerNum(String serNum) {
           this.serNum=serNum;
       }
       public BigDecimal getSvcMachMstrPk() {
           return svcMachMstrPk;
       }

       public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
           this.svcMachMstrPk=svcMachMstrPk;
       }
       public String getStkStsCd() {
           return stkStsCd;
       }

       public void setStkStsCd(String stkStsCd) {
           this.stkStsCd=stkStsCd;
       }
       public String getSvcMachUsgStsCd() {
           return svcMachUsgStsCd;
       }

       public void setSvcMachUsgStsCd(String svcMachUsgStsCd) {
           this.svcMachUsgStsCd=svcMachUsgStsCd;
       }
       public String getSvcMachMstrLocStsCd() {
           return svcMachMstrLocStsCd;
       }

       public void setSvcMachMstrLocStsCd(String svcMachMstrLocStsCd) {
           this.svcMachMstrLocStsCd=svcMachMstrLocStsCd;
       }
       public static CanonE580RowMapper getRowMapper(){
           return new CanonE580RowMapper() {
               public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                   return new MachConfInfo(
                       rs.getBigDecimal("SVC_CONFIG_MSTR_PK"),
                       rs.getString("SER_NUM"),
                       rs.getBigDecimal("SVC_MACH_MSTR_PK"),
                       rs.getString("STK_STS_CD"),
                       rs.getString("SVC_MACH_USG_STS_CD"),
                       rs.getString("SVC_MACH_MSTR_LOC_STS_CD")
                   );
               }
           };
       }
       public String toString() {
           return "MachConfInfo{" + "svcConfigMstrPk="+svcConfigMstrPk+", serNum="+serNum+", svcMachMstrPk="+svcMachMstrPk+", stkStsCd="+stkStsCd+", svcMachUsgStsCd="+svcMachUsgStsCd+", svcMachMstrLocStsCd="+svcMachMstrLocStsCd+'}';
       }
   }
   
   
   public static Object[] getAppendPoNumbers(String p_itt_num,boolean expand){
       System.out.println("in getAppendPoNumbers "+p_itt_num);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_APPEND_PO_NUMBERS);
               if (statement != null) {
                   statement.setObject(1, p_itt_num, OracleTypes.VARCHAR);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   statement.registerOutParameter(3, OracleTypes.CURSOR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.registerOutParameter(5, OracleTypes.VARCHAR);
                   statement.registerOutParameter(6, OracleTypes.VARCHAR);

                   statement.execute();
                   
                   return new Object[]{
               		   expand? cursorToList((ResultSet)statement.getObject(2),AppendPOInfo.getRowMapper()) : statement.getObject(2),                   
                	   expand? cursorToList((ResultSet)statement.getObject(3),AppendPOInfo.getRowMapper()) : statement.getObject(3),                   
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
   {call CANON_E580_AUTOCREATE_PO_PKG.GET_APPEND_PO_NUMBERS('IT-20002150-01',?,?,?,?,?)}
   (
   PO_NUMBER VARCHAR2,
       PO_DATE DATE,
       PO_STS VARCHAR2,
       SUPPLIER VARCHAR2
   )
   */

   public static class AppendPOInfo {
      private String poNumber;
          private Timestamp poDate;
          private String poSts;
          private String supplier;
       
       public AppendPOInfo(){
       }
       public AppendPOInfo(String poNumber, 
               Timestamp poDate, 
               String poSts, 
               String supplier){
           this.poNumber=poNumber;
           this.poDate=poDate;
           this.poSts=poSts;
           this.supplier=supplier;

       }

       public String getPoNumber() {
           return poNumber;
       }

       public void setPoNumber(String poNumber) {
           this.poNumber=poNumber;
       }
       public Timestamp getPoDate() {
           return poDate;
       }

       public void setPoDate(Timestamp poDate) {
           this.poDate=poDate;
       }
       public String getPoSts() {
           return poSts;
       }

       public void setPoSts(String poSts) {
           this.poSts=poSts;
       }
       public String getSupplier() {
           return supplier;
       }

       public void setSupplier(String supplier) {
           this.supplier=supplier;
       }
       public static CanonE580RowMapper getRowMapper(){
           return new CanonE580RowMapper() {
               public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                   return new AppendPOInfo(
                       rs.getString("PO_NUMBER"),
                       rs.getTimestamp("PO_DATE"),
                       rs.getString("PO_STS"),
                       rs.getString("SUPPLIER")
                   );
               }
           };
       }
       public String toString() {
           return "AppendPOInfo{" + "poNumber="+poNumber+", poDate="+poDate+", poSts="+poSts+", supplier="+supplier+'}';
       }
   }

   public interface ConnectionAware{
   	Connection getConnection();
   }

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void logMsg(String str) {
		System.out.println("[e580] [" + sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}
   
	   
	   public static Object[] dealerconfirm(String itt_number){
	        System.out.println("in dealerconfirm "+itt_number);
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(DEALER_CONFIRM);
	                if (statement != null) {
	                    statement.setObject(1, itt_number, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
	                    statement.execute();
	                    return new Object[]{statement.getObject(2)};
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
	        return null;
	   }   
	   
	   public static Object[] compoverride(String itt_number,String user_id){
	        System.out.println("in compoverride "+itt_number+user_id);
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(COMP_OVERRIDE_WF);
	                if (statement != null) {
	                    statement.setObject(1, itt_number, OracleTypes.VARCHAR);
	                    statement.setObject(2, user_id, OracleTypes.VARCHAR);					
	                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
	                    statement.execute();
	                    return new Object[]{statement.getObject(3)};
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
	        return null;
	   }   

	   public static Object[] comprefresh(String itt_number){
	        System.out.println("in comprefresh "+itt_number);
			String line_id =null;
			String  var="JSP";		
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(DEALER_COMP_REFRESH);
	                if (statement != null) {
	                    statement.setObject(1, itt_number, OracleTypes.VARCHAR);	
						statement.setObject(2, line_id, OracleTypes.VARCHAR);	
						statement.setObject(3, var, OracleTypes.VARCHAR);					
	                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
	                    statement.execute();
	                    return new Object[]{statement.getObject(4)};
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
	        return null;
	   }       

	   public static Object[] updateDeclineMaintFlag(String ittnum,String model, String flag){
	        System.out.println("in updateDeclineMaintFlag "+ittnum+"|"+model+"|"+flag);
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(UPDATE_DECLINE_MAINT);
	                if (statement != null) {
	                    statement.setObject(1, ittnum, OracleTypes.VARCHAR);
	                    statement.setObject(2, model, OracleTypes.VARCHAR);
	                    statement.setObject(3, flag, OracleTypes.VARCHAR);
						statement.registerOutParameter(4, OracleTypes.VARCHAR);
	                    statement.execute();
	                    return new Object[]{statement.getObject(4)};
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
	        return null;
	   }
		
		public String getUserId(String userName) throws Exception {
			StringBuffer qry = new StringBuffer(20);
			Statement statement = null;
			ResultSet resultSet = null;
			Connection oracleconnection = null;
			String list="";


			qry.append(" SELECT user_id  \n");
			qry.append("     FROM fnd_user \n");
			qry.append("    WHERE end_date IS NULL \n");
			qry.append("    AND UPPER(user_name)='");
			if(userName != null && !("".equals(userName))) {
				qry.append((userName));
				qry.append("'\n");
			} else {
				qry.append(("ABC"));
				qry.append("'\n");
			}
			logMessage(qry.toString());

			try {
				oracleconnection = TransactionScope.getConnection();
				if(oracleconnection != null) {
					statement = oracleconnection.createStatement();
					if(statement != null) {
						resultSet = statement.executeQuery(qry.toString());
						while(resultSet.next()) {
							list = resultSet.getString(1);

						}//while ends
					}
					else {
						logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");
					}
				}
				else {
					logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");
				}
			}
			catch(SQLException sqlExp) {
				logErrorMessage("SQLexception: " + sqlExp );
				throw sqlExp;
			}
			catch(Exception exception) {
				logErrorMessage("exception: " + exception);
			}
			finally {
				closeResources(resultSet);
				closeResources(statement);
				try {
					if(oracleconnection != null)
						TransactionScope.releaseConnection(oracleconnection);
				}
				catch (Exception eExp) {
				}
			}
			return list;
		}  

	   public static Object[] ittCreateGSDPdf(String p_itt_number,
	    String p_user_name){
	        System.out.println("in ittCreateGSDPdf "+p_itt_number+"|"+p_user_name);
	        CallableStatement statement = null;
	        Connection connection = null;
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(ITT_CREATE_GSD_PDF);
	                if (statement != null) {
	                    statement.setObject(1, p_itt_number, OracleTypes.VARCHAR);
	                    statement.setObject(2, p_user_name, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(3, OracleTypes.CURSOR);
	                    statement.registerOutParameter(4, OracleTypes.CURSOR);
	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
	                    statement.execute();
	                    return new Object[]{cursorToList((ResultSet)statement.getObject(3),GSDPDFHeaderInfo.getRowMapper())
	                        ,cursorToList((ResultSet)statement.getObject(4),GSDPDFLineInfo.getRowMapper())
	                        ,statement.getObject(5)};
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
	        return null;
	   }

	    public static class GSDPDFHeaderInfo {
	       private String ittNumber;
		   private Timestamp requestedDeliveryDate;
	       private String orderNumber;
	       private String dealer;
		   private String dealerLocation;
		   private String dealerCSZ;	   
	       private String dealerCode;
	       private String dealerEmail;	   
	       private String dealerContact;
	       private String dealerContactNumber;
	       private String dealerFax;	   
	       private String dealerShipToName;	   	   
	       private String dealerShipToCnaCode;
	       private String dealerShipToLocation;	   
	       private String dealerShipToCSZ;	
	      // private String dealerShipToContact;	
	       private String dealerShipToNumber;	
	       private String dealerShipToFax;		   
	       private String dealerShipToEmail;		   
	       private String shipToCustomer;
	       private String custLocation;
	       private String custCSP;
	       private String custCounty;
	       private String custCountry;
	       private String customerContactName;
	       private String customerContactPhone;
	       private String customerContactEmail;
	       private String customerContactFax;
	       private String shippingOrderNumber;	   
	       private String shippingDate;		   
	       private String ittAdminOwner;
	       private String salesRep;
	       private String contractNumber;
	       private String businessType;
	       private String poNumber;

	        public GSDPDFHeaderInfo(){
	        }
	        public GSDPDFHeaderInfo(
				         String ittNumber,
						 Timestamp requestedDeliveryDate,
			             String orderNumber,
			             String dealer,
			             String dealerLocation,
			             String dealerCSZ,	   
			             String dealerCode,
			             String dealerEmail,	   
			             String dealerContact,
			             String dealerContactNumber,
			             String dealerFax,   
			             String dealerShipToName,
			             String dealerShipToCnaCode,
			             String dealerShipToLocation,	   
			             String dealerShipToCSZ,
			            // String dealerShipToContact,	
			             String dealerShipToNumber,	
						 String dealerShipToFax,
			             String dealerShipToEmail,		   
			             String shipToCustomer,
			             String custLocation,
			             String custCSP,
			             String custCounty,
			             String custCountry,
			             String customerContactName,
			             String customerContactPhone,
			             String customerContactEmail,
			             String customerContactFax,
			             String shippingOrderNumber,	   
			             String shippingDate,	   
			             String ittAdminOwner,
			             String salesRep,
			             String contractNumber,
			             String businessType,
			             String poNumber){
	                this.ittNumber=ittNumber;
	                this.requestedDeliveryDate= requestedDeliveryDate; 
	                this.orderNumber= orderNumber; 
	                this.dealer= dealer; 
	                this.dealerLocation= dealerLocation; 
	                this.dealerCSZ=dealerCSZ;	    
	                this.dealerCode= dealerCode; 
	                this.dealerEmail=dealerEmail;	    
	                this.dealerContact= dealerContact; 
	                this.dealerContactNumber= dealerContactNumber; 
	                this.dealerFax=dealerFax;	    
	                this.dealerShipToName=dealerShipToName;	   	    
	                this.dealerShipToCnaCode=dealerShipToCnaCode; 
	                this.dealerShipToLocation=dealerShipToLocation;	    
	                this.dealerShipToCSZ=dealerShipToCSZ;	 
	               // this.dealerShipToContact=dealerShipToContact;	 
	                this.dealerShipToNumber=dealerShipToNumber;	 
					this.dealerShipToFax=dealerShipToFax;
	                this.dealerShipToEmail=dealerShipToEmail;		    
	                this.shipToCustomer= shipToCustomer; 
	                this.custLocation=custLocation; 
	                this.custCSP= custCSP; 
	                this.custCounty= custCounty; 
	                this.custCountry=  custCountry; 
	                this.customerContactName= customerContactName; 
	                this.customerContactPhone= customerContactPhone; 
	                this.customerContactEmail= customerContactEmail; 
	                this.customerContactFax= customerContactFax; 
	                this.shippingOrderNumber= shippingOrderNumber;	    
	                this.shippingDate=shippingDate;		    
	                this.ittAdminOwner= ittAdminOwner; 
	                this.salesRep= salesRep; 
	                this.contractNumber= contractNumber; 
	                this.businessType= businessType; 
	                this.poNumber= poNumber; 
	        }
			
	        public String getIttNumber() {
	            return ittNumber;
	        }
	        public void setIttNumber(String ittNumber) {
	            this.ittNumber=ittNumber;
	        }
			
	        public Timestamp getRequestedDeliveryDate() {
	            return requestedDeliveryDate;
	        }
	        public void setRequestedDeliveryDate(Timestamp requestedDeliveryDate) {
	            this.requestedDeliveryDate= requestedDeliveryDate;
	        }		

	        public String getOrderNumber() {
	            return orderNumber;
	        }
	        public void setOrderNumber(String orderNumber) {
	            this.orderNumber= orderNumber;
	        }	

			public String getDealer() {
	            return dealer;
	        }
	        public void setDealer(String dealer) {
	            this.dealer= dealer;
	        }	

			public String getDealerLocation() {
	            return dealerLocation;
	        }
	        public void setDealerLocation(String dealerLocation) {
	            this.dealerLocation= dealerLocation;
	        }	

			public String getDealerCSZ() {
	            return dealerCSZ;
	        }
	        public void setDealerCSZ(String dealerCSZ) {
	            this.dealerCSZ=dealerCSZ;
	        }	

			public String getDealerCode() {
	            return dealerCode;
	        }
	        public void setDealerCode(String dealerCode) {
	            this.dealerCode= dealerCode;
	        }	   

			public String getDealerEmail() {
	            return dealerEmail;
	        }
	        public void setDealerEmail(String dealerEmail) {
	            this.dealerEmail=dealerEmail;
	        }	

			public String getDealerContact() {
	            return dealerContact;
	        }
	        public void setDealerContact(String dealerContact) {
	            this.dealerContact= dealerContact;
	        }	
	 
			public String getDealerContactNumber() {
	            return dealerContactNumber;
	        }
	        public void setDealerContactNumber(String dealerContactNumber) {
	            this.dealerContactNumber= dealerContactNumber;
	        }	

			public String getDealerFax() {
	            return dealerFax;
	        }
	        public void setDealerFax(String dealerFax) {
	            this.dealerFax=dealerFax;
	        }

			public String getDealerShipToName() {
	            return dealerShipToName;
	        }
	        public void setDealerShipToName(String dealerShipToName) {
	            this.dealerShipToName=dealerShipToName;
	        }	 
	  
			public String getDealerShipToCnaCode() {
	            return dealerShipToCnaCode;
	        }
	        public void setDealerShipToCnaCode(String dealerShipToCnaCode) {
	            this.dealerShipToCnaCode=dealerShipToCnaCode;
	        }	 
	  	   
			public String getDealerShipToLocation() {
	            return dealerShipToLocation;
	        }
	        public void setDealerShipToLocation(String dealerShipToLocation) {
	            this.dealerShipToLocation=dealerShipToLocation;	
	        }

			public String getDealerShipToCSZ() {
	            return dealerShipToCSZ;
	        }
	        public void setDealerShipToCSZ(String dealerShipToCSZ) {
	            this.dealerShipToCSZ=dealerShipToCSZ;
	        }
	   
			//public String getDealerShipToContact() {
	        //    return dealerShipToContact;
	       // }
	       // public void setDealerShipToContact(String dealerShipToContact) {
	       //     this.dealerShipToContact=dealerShipToContact;
	       // }	
			
			public String getDealerShipToNumber() {
	            return dealerShipToNumber;
	        }
	        public void setDealerShipToNumber(String dealerShipToNumber) {
	            this.dealerShipToNumber=dealerShipToNumber;
	        }	
			
			public String getDealerShipToFax() {
	            return dealerShipToFax;
	        }
	        public void setDealerShipToFax(String dealerShipToFax) {
	            this.dealerShipToFax=dealerShipToFax;
	        }	
			
			public String getDealerShipToEmail() {
	            return dealerShipToEmail;
	        }
	        public void setDealerShipToEmail(String dealerShipToEmail) {
	            this.dealerShipToEmail=dealerShipToEmail;	
	        }			

			public String getShipToCustomer() {
	            return shipToCustomer;
	        }
	        public void setShipToCustomer(String shipToCustomer) {
	            this.shipToCustomer= shipToCustomer;
	        }	

			public String getCustLocation() {
	            return custLocation;
	        }
	        public void setCustLocation(String custLocation) {
	            this.custLocation=custLocation;	
	        }	
		
			public String getCustCSP() {
	            return custCSP;
	        }
	        public void setCustCSP(String custCSP) {
	            this.custCSP= custCSP;
	        }	

			public String getCustCounty() {
	            return custCounty;
	        }
	        public void setCustCounty(String custCounty) {
	            this.custCounty= custCounty;
	        }	

			public String getCustCountry() {
	            return custCountry;
	        }
	        public void setCustCountry(String custCountry) {
	            this.custCountry=  custCountry;
	        }	

			public String getCustomerContactName() {
	            return customerContactName;
	        }
	        public void setCustomerContactName(String customerContactName) {
	            this.customerContactName= customerContactName;
	        }
		   
			public String getCustomerContactPhone() {
	            return customerContactPhone;
	        }
	        public void setCustomerContactPhone(String customerContactPhone) {
	            this.customerContactPhone= customerContactPhone;
	        }	

			public String getCustomerContactEmail() {
	            return customerContactEmail;
	        }
	        public void setCustomerContactEmail(String customerContactEmail) {
	            this.customerContactEmail= customerContactEmail;
	        }

			public String getCustomerContactFax() {
	            return customerContactFax;
	        }
	        public void setCustomerContactFax(String customerContactFax) {
	            this.customerContactFax= customerContactFax;
	        }	

			public String getShippingOrderNumber() {
	            return shippingOrderNumber;
	        }
	        public void setShippingOrderNumber(String shippingOrderNumber) {
	            this.shippingOrderNumber= shippingOrderNumber;
	        }

			public String getShippingDate() {
	            return shippingDate;
	        }
	        public void setShippingDate(String shippingDate) {
	            this.shippingDate=shippingDate;	
	        }	

			public String getIttAdminOwner() {
	            return ittAdminOwner;
	        }
	        public void setIttAdminOwner(String ittAdminOwner) {
	            this.ittAdminOwner= ittAdminOwner;
	        }
		   
			public String getSalesRep() {
	            return salesRep;
	        }
	        public void setSalesRep(String salesRep) {
	            this.salesRep= salesRep;
	        }	

			public String getContractNumber() {
	            return contractNumber;
	        }
	        public void setContractNumber(String contractNumber) {
	            this.contractNumber= contractNumber;
	        }	
	   
			public String getBusinessType() {
	            return businessType;
	        }
	        public void setBusinessType(String businessType) {
	            this.businessType= businessType;
	        }	

			public String getPoNumber() {
	            return poNumber;
	        }
	        public void setPoNumber(String poNumber) {
	            this.poNumber= poNumber;
	        }	

	        public static CanonE580RowMapper getRowMapper(){
	            return new CanonE580RowMapper() {
	                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	                    return new GSDPDFHeaderInfo(
	                       rs.getString("ITT_NUMBER"),
	                       rs.getTimestamp("REQUESTED_DELIVERY_DATE"),
	                       rs.getString("ORDER_NUMBER"),
	                       rs.getString("DEALER"),
	                       rs.getString("DEALER_LOCATION"),
	                       rs.getString("DEALER_CSZ"),
	                       rs.getString("DEALER_CODE"),
	                       rs.getString("DEALER_EMAIL"),
	                       rs.getString("DEALER_CONTACT"),
	                       rs.getString("DEALER_CONTACT_NUMBER"),
	                       rs.getString("DEALER_CONTACT_FAX"),
	                       rs.getString("DEALER_SHIPTO_NAME"),
	                       rs.getString("DEALER_SHIP_TO_CNA_CODE"),
	                       rs.getString("DEALER_SHIPTO_LOCATION"),
	                       rs.getString("DEALER_SHIP_CSZ"),
	                       rs.getString("DEALER_SHIPTO_CONTACT_NUMBER"),
	                       rs.getString("DEALER_SHIPTO_CONTACT_FAX"),
	                       rs.getString("DEALER_SHIPTO_EMAIL"),
	                       rs.getString("SHIP_TO_CUSTOMER"),
	                       rs.getString("CUST_LOCATION"),
	                       rs.getString("CUST_CSP"),
	                       rs.getString("COUNTY"),
	                       rs.getString("COUNTRY"),
	                       rs.getString("CUSTOMER_CONTACT_NAME"),
	                       rs.getString("CUSTOMER_CONTACT_PHONE"),
	                       rs.getString("CUSTOMER_CONTACT_EMAIL"),
	                       rs.getString("CUSTOMER_CONTACT_FAX"),
	                       rs.getString("SHIPPING_ORDER_NUMBER"),
	                       rs.getString("SHIPPING_DATE"),
	                       rs.getString("ITT_ADMIN_OWNER"),
	                       rs.getString("CANON_SALES_REP"),
	                       rs.getString("CONTRACT_NUMBER"),
	                       rs.getString("BUSINESS_TYPE"),
	                       rs.getString("PURCHASE_ORDER_NUMBER")
	                    );
	                }
	            };
	        }
	        public String toString() {
		          return "GSDPDFHeaderInfo{" + "ittNumber="+ittNumber+", requestedDeliveryDate="+requestedDeliveryDate+", orderNumber="+orderNumber+", dealer="+dealer+", dealerLocation="+dealerLocation+", dealerCSZ="+dealerCSZ+", dealerCode="+dealerCode+", dealerEmail="+dealerEmail+", dealerContact="+dealerContact+", dealerContactNumber="+dealerContactNumber+", dealerFax="+dealerFax+", dealerShipToName="+dealerShipToName+", dealerShipToCnaCode="+dealerShipToCnaCode+", dealerShipToLocation="+dealerShipToLocation+", dealerShipToCSZ="+dealerShipToCSZ+", dealerShipToNumber="+dealerShipToNumber+", dealerShipToFax="+dealerShipToFax+", dealerShipToEmail="+dealerShipToEmail+" , shipToCustomer="+shipToCustomer+", custLocation="+custLocation+", custCSP="+custCSP+", custCounty="+custCounty+", custCountry="+custCountry+", customerContactName="+customerContactName+", customerContactPhone="+customerContactPhone+", customerContactEmail="+customerContactEmail+", customerContactFax="+customerContactFax+", shippingOrderNumber="+shippingOrderNumber+", shippingDate="+shippingDate+", ittAdminOwner="+ittAdminOwner+", salesRep="+salesRep+", contractNumber="+contractNumber+", businessType="+businessType+", poNumber="+poNumber+'}';	    
	        }
	    }    

	    public static class GSDPDFLineInfo {
		   private String lineNumber;
	       private String equipModel;			  
	       private String item;
	       private String itemDescription;
	       private BigDecimal orderedQuantity;

	        public GSDPDFLineInfo(){
	        }
	        public GSDPDFLineInfo( 
					String lineNumber,
					String equipModel, 
				    String item, 
	                String itemDescription, 
	                BigDecimal orderedQuantity){
				this.lineNumber=lineNumber;
				this.equipModel=equipModel;
	            this.item=item;
	            this.itemDescription=itemDescription;
	            this.orderedQuantity=orderedQuantity;
	        }
	        public String getLineNumber() {
	            return lineNumber;
	        }
	        public void setLineNumber(String lineNumber) {
	            this.lineNumber=lineNumber;
	        }		
	        public String getEquipModel() {
	            return equipModel;
	        }
	        public void setEquipModel(String equipModel) {
	            this.equipModel=equipModel;
	        }		
	        public String getItem() {
	            return item;
	        }
	        public void setItem(String item) {
	            this.item=item;
	        }
	        public String getItemDescription() {
	            return itemDescription;
	        }
	        public void setItemDescription(String itemDescription) {
	            this.itemDescription=itemDescription;
	        }
	        public BigDecimal getOrderedQuantity() {
	            return orderedQuantity;
	        }
	        public void setOrderedQuantity(BigDecimal orderedQuantity) {
	            this.orderedQuantity=orderedQuantity;
	        }
			
	        public static CanonE580RowMapper getRowMapper(){
	            return new CanonE580RowMapper() {
	                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	                    return new GSDPDFLineInfo(
						    rs.getString("LINE_NUMBER"),
							rs.getString("EQUIP_MODEL"),
	                        rs.getString("ITEM"),
	                        rs.getString("ITEM_DESCRIPTION"),
	                        rs.getBigDecimal("ORDERED_QUANTITY")
	                    );
	                }
	            };
	        }
	        public String toString() {
	            return "GSDPDFLineInfo{" + "lineNumber="+lineNumber+", item="+item+", itemDescription="+itemDescription+", orderedQuantity="+orderedQuantity+'}';
	        }
	    }
	
}
