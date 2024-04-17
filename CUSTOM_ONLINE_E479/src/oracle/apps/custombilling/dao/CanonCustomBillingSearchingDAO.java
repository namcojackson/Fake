package oracle.apps.custombilling.dao;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import oracle.apps.custombilling.beans.CanonCustomBillingSearchingBean;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.CallableStatement;


import canon.apps.common.CanonRowMapper;
import oracle.apps.custombilling.beans.CanonCustomBillingColumnDetailBean;
import oracle.apps.custombilling.beans.CanonCustomBillingInvSearchBean;
import oracle.apps.custombilling.beans.CanonCustomBillingProfileAccountBean;
import oracle.apps.custombilling.beans.CanonCustomBillingTemplateInfoBean;
import oracle.apps.custombilling.beans.CanonCustomBillingViewDetailBean;

public class CanonCustomBillingSearchingDAO {

    public static final String VALIDATE_TEMPLATE_RECORD  = "{call CANON_E479_TEMPLATE_PKG.VALIDATE_TEMPLATE_RECORD(?,?,?,?,?,?)}";
    public static final String UPDATE_VIEW_DETAILS = "{call CANON_E479_TEMPLATE_PKG.UPDATE_VIEW_DETAILS(?,?,?,?,?,?,?,?,?,?)}";
    public static final String UPDATE_TEMPLATE_INFO= "{call CANON_E479_TEMPLATE_PKG.UPDATE_TEMPLATE_INFO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String UPDATE_COLUMN_DETAILS = "{call CANON_E479_TEMPLATE_PKG.UPDATE_COLUMN_DETAILS(?,?,?,?,?,?,?,?,?,?)}";
    public static final String IS_PARTY_EXIST_IN_TEMPLATE = "{call CANON_E479_TEMPLATE_PKG.IS_PARTY_EXIST_IN_TEMPLATE(?,?,?)}";
    public static final String INSERT_VIEW_DETAILS = "{call CANON_E479_TEMPLATE_PKG.INSERT_VIEW_DETAILS(?,?,?,?,?,?,?,?,?,?)}";
    public static final String INSERT_COLUMN_DETAILS = "{call CANON_E479_TEMPLATE_PKG.INSERT_COLUMN_DETAILS(?,?,?,?,?,?,?,?,?,?)}";
    public static final String GET_VIEW_DETAILS = "{call CANON_E479_TEMPLATE_PKG.GET_VIEW_DETAILS(?,?)}";
    public static final String GET_VALUESET_DATA = "{call CANON_E479_TEMPLATE_PKG.GET_VALUESET_DATA(?,?)}";
    public static final String GET_TEMPLATE_INFO = "{call CANON_E479_TEMPLATE_PKG.GET_TEMPLATE_INFO(?,?)}";
    public static final String GET_TEMPLATES = "{call CANON_E479_TEMPLATE_PKG.GET_TEMPLATES(?,?,?)}";
    public static final String GET_SORTING_SEQ3 = "{call CANON_E479_TEMPLATE_PKG.GET_SORTING_SEQ3(?,?,?,?)}";
    public static final String GET_SORTING_SEQ2 = "{call CANON_E479_TEMPLATE_PKG.GET_SORTING_SEQ2(?,?,?)}";
    public static final String GET_SORTING_SEQ1 = "{call CANON_E479_TEMPLATE_PKG.GET_SORTING_SEQ1(?,?)}";
    public static final String GET_PROFILE_ACCOUNTS = "{call CANON_E479_TEMPLATE_PKG.GET_PROFILE_ACCOUNTS(?,?)}";
    public static final String GET_CUSTOMER_NAMES = "{call CANON_E479_TEMPLATE_PKG.GET_CUSTOMER_NAMES(?,?,?,?,?,?,?)}";
    public static final String GET_INITIAL_COLUMN_LIST = "{call CANON_E479_TEMPLATE_PKG.GET_INITIAL_COLUMN_LIST(?,?)}";
    public static final String GET_ELIGIBLE_SUM_COLS = "{call CANON_E479_TEMPLATE_PKG.GET_ELIGIBLE_SUM_COLS(?,?)}";
    public static final String GET_ELGBLE_NON_NUM_COLS = "{call CANON_E479_TEMPLATE_PKG.GET_ELGBLE_NON_NUM_COLS(?,?)}";
    public static final String GET_COLUMN_DETAILS = "{call CANON_E479_TEMPLATE_PKG.GET_COLUMN_DETAILS(?,?,?)}";
    public static final String GET_BILL_TO_SITES= "{call CANON_E479_TEMPLATE_PKG.GET_BILL_TO_SITES(?,?,?,?,?,?,?,?)}";
    public static final String GET_ACCOUNT_PROFILE_DATA = "{call CANON_E479_TEMPLATE_PKG.GET_ACCOUNT_PROFILE_DATA(?,?,?,?,?,?,?)}";
    public static final String DELETE_VIEW_DETAILS = "{call CANON_E479_TEMPLATE_PKG.DELETE_VIEW_DETAILS(?,?,?)}";
    public static final String CREATE_TEMPLATE = "{call CANON_E479_TEMPLATE_PKG.CREATE_TEMPLATE(?,?,?,?,?,?,?,?)}";
    public static final String APPEND_MESSAGE = "{call CANON_E479_TEMPLATE_PKG.APPEND_MESSAGE(?,?,?)}";
    public static final String DELETE_VIEW_COLUMNS= "{call CANON_E479_TEMPLATE_PKG.DELETE_VIEW_COLUMNS(?,?,?,?)}";
    public static final String GET_BILLER_DETAIL= "{call CANON_E479_TEMPLATE_UTIL_PKG.GET_BILLER_DETAIL(?,?,?,?,?,?,?)}";
    public static final String COPY_TEMPLATE= "{call CANON_E479_TEMPLATE_PKG.COPY_TEMPLATE(?,?,?,?,?,?,?,?,?)}";
    public static final String UPDATE_FOR_COMP_INCOMP= "{call CANON_E479_TEMPLATE_PKG.UPDATE_FOR_COMP_INCOMP(?,?,?,?)}";
    public static final String SEARCH_CUSTOM_INVOICES= "{call CANON_E479_INV_PKG.SEARCH_CUSTOM_INVOICES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String IS_USER_BILLER= "{call CANON_E479_INV_PKG.IS_USER_BILLER(?,?)}";
    public static final String GET_INV_DETAILS = "{call CANON_E479_INV_PKG.GET_INV_DETAILS(?,?)}";
    public static final String APPROVE_REJECT_INVOICES= "{call CANON_E479_INV_PKG.APPROVE_REJECT_INVOICES(?,?,?,?,?)}";
    public static final String GET_INV_DETAILS_FOR_APPROVAL= "{call CANON_E479_INV_PKG.GET_INV_DETAILS_FOR_APPROVAL(?,?)}";
    public static final String IS_PROD = "{call CANON_E479_TEMPLATE_UTIL_PKG.IS_PROD(?)}";
    public static final String IS_CONCURRENT_PROGRAM_RUNNING= "{call CANON_E479_TEMPLATE_PKG.IS_CONCURRENT_PROGRAM_RUNNING(?)}";
    public static final String GET_DB_NAME= "{call CANON_E479_TEMPLATE_UTIL_PKG.GET_DB_NAME(?)}";
    public static final String GET_URN_INFO= "{call CANON_E479_INV_PKG.GET_URN_INFO(?,?,?,?,?,?,?,?)}";
    public static final String DELETE_TEMPLATE= "{call CANON_E479_TEMPLATE_PKG.DELETE_TEMPLATE(?,?)}";
    public static final String GET_GROUP_NAMES = "{call CANON_E479_TEMPLATE_PKG.GET_CUSTOMER_GROUP(?,?,?,?,?)}";
    public static final String GET_PARENT_CUST_NAMES = "{call CANON_E479_TEMPLATE_PKG.GET_PARENT_CUSTOMER(?,?,?,?,?,?,?)}";
    public static final String GET_INITIAL_PATH= "{call CANON_E479_INV_PKG.GET_INITIAL_PATH(?,?)}";
    
    public static final CanonRowMapper STRING_ROW_MAPPER = new CanonRowMapper() {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString(1);
        }
    };

    public static Object[] validateTemplateRecord(String p_profile_name,
            String p_party_name,
            BigDecimal p_site_use_id,
            BigDecimal p_template_id) {
        System.out.println("in validateTemplateRecord " + p_profile_name + "|" + p_party_name + "|" + p_site_use_id + "|" + p_template_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(VALIDATE_TEMPLATE_RECORD);
                if (statement != null) {
                    statement.setObject(1, p_profile_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_party_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_site_use_id, OracleTypes.NUMBER);
                    statement.setObject(4, p_template_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(5), statement.getObject(6)};
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

    public static ArrayList getCustomBillSearchDtls(String searchVal, String searchType) {
        System.out.println("in getCustomBillSearchDtls " + searchVal + "|" + searchType);
        Connection conn;
        conn = null;
        CallableStatement cstmt;
        cstmt = null;
        ArrayList arrlist = new ArrayList();

        ResultSet rs = null;
        try {

            conn = TransactionScope.getConnection();
            cstmt = (CallableStatement) conn.prepareCall(GET_TEMPLATES);
            cstmt.setString(1, searchVal);
            cstmt.setString(2, searchType);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.execute();
            rs =(ResultSet) cstmt.getObject(3);

            while (rs.next()) {
                CanonCustomBillingSearchingBean searchBean = new CanonCustomBillingSearchingBean();
                searchBean.setHeaderId(rs.getString("header_id"));
                searchBean.setParentCustomerName(rs.getString("parent_acct"));
                searchBean.setGroupName(rs.getString("group_name"));
                searchBean.setPartyName(rs.getString("customer_name"));
                searchBean.setBillToUse(rs.getString("bill_to_use"));
                searchBean.setOtherEmail(rs.getString("other_email"));
                searchBean.setCustomerEmail(rs.getString("customer_email"));
                searchBean.setStatusFlag(rs.getString("status_flag"));
                searchBean.setTemplateLevel(rs.getString("template_level"));
                arrlist.add(searchBean);
            }

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    try {
                        rs.close();
                        rs = null;
                       
                    }catch (java.sql.SQLException sqlExp) {
                        sqlExp.printStackTrace();
                    }
                }

                if (cstmt != null) {
                    try {
                        cstmt.close();
                        cstmt = null;
                    } catch (java.sql.SQLException sqlExp) {
                        sqlExp.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        TransactionScope.releaseConnection(conn);
                        conn = null;
                    } catch (Exception sqlExp) {
                        sqlExp.printStackTrace();
                    }
                }
            } catch (Exception exception3) {
                exception3.printStackTrace();
            }
        }
        return arrlist;
    }

    public static Object[] getCustomerNames(String p_customer_name,
            BigDecimal p_start_range,
            BigDecimal p_end_range,
            String p_sort_by,
            String p_sort_type) {
        System.out.println("in getCustomerNames " + p_customer_name + "|" + p_start_range + "|" + p_end_range + "|" + p_sort_by + "|" + p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_CUSTOMER_NAMES);
                if (statement != null) {
                    statement.setObject(1, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(3, p_end_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(5, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.CURSOR);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(6), oracle.apps.custombilling.beans.CanonCustomBillingCustomerNameBean.getRowMapper()), statement.getObject(7)};
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
    
    public static Object[] getParentCustomer(String p_customer_name,
            BigDecimal p_start_range,
            BigDecimal p_end_range,
            String p_sort_by,
            String p_sort_type) {
        System.out.println("in getParentCustomer " + p_customer_name + "|" + p_start_range + "|" + p_end_range + "|" + p_sort_by + "|" + p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_PARENT_CUST_NAMES);
                if (statement != null) {
                    statement.setObject(1, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(3, p_end_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(5, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.CURSOR);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(6), oracle.apps.custombilling.beans.CanonCustomBillingCustomerNameBean.getRowMapper()), statement.getObject(7)};
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
    
    public static Object[] getGroupNames(String p_group_name,
            BigDecimal p_start_range,
            BigDecimal p_end_range
            ) {
        System.out.println("in getGroupNames " + p_group_name + "|" + p_start_range + "|" + p_end_range );
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_GROUP_NAMES);
                if (statement != null) {
                    statement.setObject(1, p_group_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(3, p_end_range, OracleTypes.NUMBER);
                    statement.registerOutParameter(4, OracleTypes.CURSOR);
                    statement.registerOutParameter(5, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(4), oracle.apps.custombilling.beans.CanonCustomBillingCustomerGroupBean.getRowMapper()), statement.getObject(5)};
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


   public static Object[] getBillToSites(String p_bill_location,
    String p_party_name,
    BigDecimal p_start_range,
    BigDecimal p_end_range,
    String p_sort_by,
    String p_sort_type){
        System.out.println("in getBillToSites "+p_bill_location+"|"+p_party_name+"|"+p_start_range+"|"+p_end_range+"|"+p_sort_by+"|"+p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_BILL_TO_SITES);
                if (statement != null) {
                    statement.setObject(1, p_bill_location, OracleTypes.VARCHAR);
                    statement.setObject(2, p_party_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_end_range, OracleTypes.NUMBER);
                    statement.setObject(5, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(6, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.CURSOR);
                    statement.registerOutParameter(8, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(7),oracle.apps.custombilling.beans.CanonCustomBillingBillToSiteBean.getRowMapper())
                        ,statement.getObject(8)};
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

    public static Object[] getAccountProfileData(String p_profile_name,
            BigDecimal p_start_range,
            BigDecimal p_end_range,
            String p_sort_by,
            String p_sort_type) {
        System.out.println("in getAccountProfileData " + p_profile_name + "|" + p_start_range + "|" + p_end_range + "|" + p_sort_by + "|" + p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_ACCOUNT_PROFILE_DATA);
                if (statement != null) {
                    statement.setObject(1, p_profile_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(3, p_end_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(5, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.CURSOR);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(6), oracle.apps.custombilling.beans.CanonCustomBillingAccountProfileDataBean.getRowMapper()), statement.getObject(7)};
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

    public static Object[] getProfileAccounts(BigDecimal p_profile_id) {
        System.out.println("in getProfileAccounts " + p_profile_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_PROFILE_ACCOUNTS);
                if (statement != null) {
                    statement.setObject(1, p_profile_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), CanonCustomBillingProfileAccountBean.getRowMapper())};
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

    private static List cursorToList(ResultSet cursor, CanonRowMapper rowMapper) {
        List list = new ArrayList();
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
        return list;
    }

    public static Object[] getTemplateInfo(String p_template_id) {
        System.out.println("in getTemplateInfo " + p_template_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_TEMPLATE_INFO);
                if (statement != null) {
                    statement.setObject(1, p_template_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), CanonCustomBillingTemplateInfoBean.getRowMapper())};
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

    public static Object[] createTemplate(String p_customer_name,
    		String p_group_name,
            String p_parent_customer_name,
            String p_bill_to_use_id,
            String p_template_level,
            String p_user_id) {
        System.out.println("$$$: in createTemplate " + p_customer_name + "|" + p_group_name + "|" + p_parent_customer_name + "|" + p_bill_to_use_id + "|" + p_template_level +"|" + p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CREATE_TEMPLATE);
                if (statement != null) {
                    statement.setObject(1, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_group_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_parent_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_bill_to_use_id, OracleTypes.VARCHAR);
                    statement.setObject(5, p_template_level, OracleTypes.VARCHAR);
                    statement.setObject(6, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.registerOutParameter(8, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(7), statement.getObject(8)};
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

   public static Object[] updateTemplateInfo(String p_template_id,
    String p_biller_first_name,
    String p_biller_last_name,
    String p_biller_contact_no,
    String p_biller_fax_no,
    String p_biller_email_id,
    String p_customer_email,
    String p_other_email,
    String p_invoice_break,
    String p_multi_tab,
    String p_within_tab,
    String p_user_id,
    String p_text_format,
    String p_suppress_zero){
        System.out.println("in updateTemplateInfo ");
        System.out.println(" parameter values: "+p_template_id+"|"+p_biller_first_name+"|"+p_biller_last_name+"|"+p_biller_contact_no+"|"+p_biller_fax_no+"|"+p_biller_email_id
        		);
        System.out.println(" --> "+p_customer_email+"|"+p_other_email+"|"+p_invoice_break+"|"+p_multi_tab+"|"+p_within_tab+"|"+p_user_id+"|"+p_text_format+"|"+p_suppress_zero);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPDATE_TEMPLATE_INFO);
                if (statement != null) {
                    statement.setObject(1, p_template_id, OracleTypes.VARCHAR);
                    statement.setObject(2, p_biller_first_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_biller_last_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_biller_contact_no, OracleTypes.VARCHAR);
                    statement.setObject(5, p_biller_fax_no, OracleTypes.VARCHAR);
                    statement.setObject(6, p_biller_email_id, OracleTypes.VARCHAR);
                    statement.setObject(7, p_customer_email, OracleTypes.VARCHAR);
                    statement.setObject(8, p_other_email, OracleTypes.VARCHAR);
                    statement.setObject(9, p_invoice_break, OracleTypes.VARCHAR);
                    statement.setObject(10, p_multi_tab, OracleTypes.VARCHAR);
                    statement.setObject(11, p_within_tab, OracleTypes.VARCHAR);
                    statement.setObject(12, p_user_id, OracleTypes.VARCHAR);
                    statement.setObject(13, p_text_format, OracleTypes.VARCHAR);
                    statement.setObject(14, p_suppress_zero, OracleTypes.VARCHAR);
                    statement.registerOutParameter(15, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(15)};
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
   
    public static Object[] getValuesetData(String p_valueset_name) {
        System.out.println("in getValuesetData " + p_valueset_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_VALUESET_DATA);
                if (statement != null) {
                    statement.setObject(1, p_valueset_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2),
                                new CanonRowMapper() {

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

    public static Object[] getViewDetails(BigDecimal p_header_id) {
        System.out.println("in getViewDetails " + p_header_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_VIEW_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), CanonCustomBillingViewDetailBean.getRowMapper())};
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

    public static Object[] getSortingSeq1(String p_view_name) {
        System.out.println("in getSortingSeq1 " + p_view_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SORTING_SEQ1);
                if (statement != null) {
                    statement.setObject(1, p_view_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2),
                                new CanonRowMapper() {

                                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                        return rs.getString("COLUMN_NAME");
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

    public static Object[] getSortingSeq2(String p_view_name,
            String p_sort_seq1) {
        System.out.println("in getSortingSeq2 " + p_view_name + "|" + p_sort_seq1);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SORTING_SEQ2);
                if (statement != null) {
                    statement.setObject(1, p_view_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_sort_seq1, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(3),
                                new CanonRowMapper() {

                                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                        return rs.getString("COLUMN_NAME");
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

    public static Object[] getSortingSeq3(String p_view_name,
            String p_sort_seq1,
            String p_sort_seq2) {
        System.out.println("in getSortingSeq3 " + p_view_name + "|" + p_sort_seq1 + "|" + p_sort_seq2);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SORTING_SEQ3);
                if (statement != null) {
                    statement.setObject(1, p_view_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_sort_seq1, OracleTypes.VARCHAR);
                    statement.setObject(3, p_sort_seq2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(4),
                                new CanonRowMapper() {

                                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                        return rs.getString("COLUMN_NAME");
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

    public static Object[] insertViewDetails(BigDecimal p_header_id,
            String p_view_name,
            String p_view_alias,
            BigDecimal p_view_seq,
            String p_sort_pref1,
            String p_sort_pref2,
            String p_sort_pref3,
            String p_created_by,
            String p_last_updated_by) {
        System.out.println("in insertViewDetails " + p_header_id + "|" + p_view_name + "|" + p_view_alias + "|" + p_view_seq + "|" + p_sort_pref1 + "|" + p_sort_pref2 + "|" + p_sort_pref3 + "|" + p_created_by + "|" + p_last_updated_by);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(INSERT_VIEW_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_view_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_view_alias, OracleTypes.VARCHAR);
                    statement.setObject(4, p_view_seq, OracleTypes.NUMBER);
                    statement.setObject(5, p_sort_pref1, OracleTypes.VARCHAR);
                    statement.setObject(6, p_sort_pref2, OracleTypes.VARCHAR);
                    statement.setObject(7, p_sort_pref3, OracleTypes.VARCHAR);
                    statement.setObject(8, p_created_by, OracleTypes.VARCHAR);
                    statement.setObject(9, p_last_updated_by, OracleTypes.VARCHAR);
                    statement.registerOutParameter(10, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(10)};
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

    public static Object[] updateViewDetails(BigDecimal p_header_id,
            BigDecimal p_view_id,
            String p_view_name,
            String p_view_alias,
            BigDecimal p_view_seq,
            String p_sort_pref1,
            String p_sort_pref2,
            String p_sort_pref3,
            String p_last_updated_by) {
        System.out.println("in updateViewDetails " + p_header_id + "|" + p_view_id + "|" + p_view_name + "|" + p_view_alias + "|" + p_view_seq + "|" + p_sort_pref1 + "|" + p_sort_pref2 + "|" + p_sort_pref3 + "|" + p_last_updated_by);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPDATE_VIEW_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_view_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_view_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_view_alias, OracleTypes.VARCHAR);
                    statement.setObject(5, p_view_seq, OracleTypes.NUMBER);
                    statement.setObject(6, p_sort_pref1, OracleTypes.VARCHAR);
                    statement.setObject(7, p_sort_pref2, OracleTypes.VARCHAR);
                    statement.setObject(8, p_sort_pref3, OracleTypes.VARCHAR);
                    statement.setObject(9, p_last_updated_by, OracleTypes.VARCHAR);
                    statement.registerOutParameter(10, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(10)};
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

    public static Object[] deleteViewDetails(BigDecimal p_header_id,
            BigDecimal p_view_id) {
        System.out.println("in deleteViewDetails " + p_header_id + "|" + p_view_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(DELETE_VIEW_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_view_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(3, OracleTypes.NUMBER);
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

    public static Object[] getInitialColumnList(String p_view_name) {
        System.out.println("in getInitialColumnList " + p_view_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_INITIAL_COLUMN_LIST);
                if (statement != null) {
                    statement.setObject(1, p_view_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), STRING_ROW_MAPPER)};
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

    public static Object[] getEligibleSumCols(String p_view_name) {
        System.out.println("in getEligibleSumCols " + p_view_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_ELIGIBLE_SUM_COLS);
                if (statement != null) {
                    statement.setObject(1, p_view_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), STRING_ROW_MAPPER)};
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

    public static Object[] getEligibleNonNumericCols(String p_view_name) {
        System.out.println("in getEligibleNonNumericCols " + p_view_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_ELGBLE_NON_NUM_COLS);
                if (statement != null) {
                    statement.setObject(1, p_view_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), STRING_ROW_MAPPER)};
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

    public static Object[] getColumnDetails(BigDecimal p_header_id,
            BigDecimal p_view_id) {
        System.out.println("in getColumnDetails " + p_header_id + "|" + p_view_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_COLUMN_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_view_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(3), CanonCustomBillingColumnDetailBean.getRowMapper())};
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

    public static Object[] insertColumnDetails(BigDecimal p_header_id,
            BigDecimal p_view_id,
            String p_column_type,
            String p_column_name,
            String p_column_alias,
            String p_column_position,
            String p_aggregate_by,
            String p_created_by,
            String p_last_updated_by) {
        System.out.println("in insertColumnDetails " + p_header_id + "|" + p_view_id + "|" + p_column_type + "|" + p_column_name + "|" + p_column_alias + "|" + p_column_position + "|" + p_aggregate_by + "|" + p_created_by + "|" + p_last_updated_by);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(INSERT_COLUMN_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_view_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_column_type, OracleTypes.VARCHAR);
                    statement.setObject(4, p_column_name, OracleTypes.VARCHAR);
                    statement.setObject(5, p_column_alias, OracleTypes.VARCHAR);
                    statement.setObject(6, p_column_position, OracleTypes.VARCHAR);
                    statement.setObject(7, p_aggregate_by, OracleTypes.VARCHAR);
                    statement.setObject(8, p_created_by, OracleTypes.VARCHAR);
                    statement.setObject(9, p_last_updated_by, OracleTypes.VARCHAR);
                    statement.registerOutParameter(10, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(10)};
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

    public static Object[] updateColumnDetails(BigDecimal p_header_id,
            BigDecimal p_view_id,
            BigDecimal p_column_id,
            String p_column_type,
            String p_column_name,
            String p_column_alias,
            String p_column_position,
            String p_aggregate_by,
            String p_last_updated_by) {
        System.out.println("in updateColumnDetails " + p_header_id + "|" + p_view_id + "|" + p_column_id + "|" + p_column_type + "|" + p_column_name + "|" + p_column_alias + "|" + p_column_position + "|" + p_aggregate_by + "|" + p_last_updated_by);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPDATE_COLUMN_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_view_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_column_id, OracleTypes.NUMBER);
                    statement.setObject(4, p_column_type, OracleTypes.VARCHAR);
                    statement.setObject(5, p_column_name, OracleTypes.VARCHAR);
                    statement.setObject(6, p_column_alias, OracleTypes.VARCHAR);
                    statement.setObject(7, p_column_position, OracleTypes.VARCHAR);
                    statement.setObject(8, p_aggregate_by, OracleTypes.VARCHAR);
                    statement.setObject(9, p_last_updated_by, OracleTypes.VARCHAR);
                    statement.registerOutParameter(10, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(10)};
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

   public static Object[] deleteViewColumns(BigDecimal p_header_id,
    BigDecimal p_view_id,
    BigDecimal p_column_id){
        System.out.println("in deleteViewColumns "+p_header_id+"|"+p_view_id+"|"+p_column_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(DELETE_VIEW_COLUMNS);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_view_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_column_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(4, OracleTypes.NUMBER);
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
   
   public static Object[] getBillerDetail(BigDecimal p_header_id){
        System.out.println("in getBillerDetail "+p_header_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_BILLER_DETAIL);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)
                        ,statement.getObject(3)
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

   public static Object[] copyTemplate(BigDecimal p_header_id,
    String p_customer_name,
    String p_group_name,
    String p_parent_customer,
    String p_bill_location,
    String p_template_level,
    String p_user_id){
        System.out.println("in copyTemplate "+p_header_id+"|"+p_customer_name+"|"+p_group_name+"|"+p_parent_customer+"|"+p_bill_location+"|"+p_template_level+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(COPY_TEMPLATE);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_group_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_parent_customer, OracleTypes.VARCHAR);
                    statement.setObject(5, p_bill_location, OracleTypes.VARCHAR);
                    statement.setObject(6, p_template_level, OracleTypes.VARCHAR);
                    statement.setObject(7, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
                    statement.registerOutParameter(9, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(6)
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
   
   public static Object[] updateForCompIncomp(BigDecimal p_header_id,
    String p_status){
        System.out.println("in updateForCompIncomp "+p_header_id+"|"+p_status);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPDATE_FOR_COMP_INCOMP);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_status, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.NUMBER);
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
  public static Object[] searchCustomInvoices(BigDecimal p_user_id,
    String p_user_role,
    String p_bill_number,
    String p_pending_action,
    String p_period,
    String p_urn_number,
    String p_biller_name,
    String p_parent_customer_name,
    String p_customer_group,
    String p_customer_name,
    String p_location,
    Timestamp p_bill_from_date,
    Timestamp p_bill_to_date,
    BigDecimal p_start_range,
    BigDecimal p_end_range,
    String p_sort_by,
    String p_sort_type){
        System.out.println("in searchCustomInvoices "+p_user_id+"|"+p_bill_number+"|"+p_pending_action+"|"+p_period+"|"+p_urn_number+"|"+p_biller_name+"|"+p_parent_customer_name+"|"+p_customer_group+"|"+p_customer_name+"|"+p_location+"|"+p_bill_from_date+"|"+p_bill_to_date+"|"+p_start_range+"|"+p_end_range+"|"+p_sort_by+"|"+p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SEARCH_CUSTOM_INVOICES);
                if (statement != null) {
                    statement.setObject(1, p_user_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_user_role, OracleTypes.VARCHAR);
                    statement.setObject(3, p_bill_number, OracleTypes.VARCHAR);
                    statement.setObject(4, p_pending_action, OracleTypes.VARCHAR);
                    statement.setObject(5, p_period, OracleTypes.VARCHAR);
                    statement.setObject(6, p_urn_number, OracleTypes.VARCHAR);
                    statement.setObject(7, p_biller_name, OracleTypes.VARCHAR);
                    statement.setObject(8, p_parent_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(9, p_customer_group, OracleTypes.VARCHAR);
                    statement.setObject(10, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(11, p_location, OracleTypes.VARCHAR);
                    statement.setObject(12, p_bill_from_date, OracleTypes.TIMESTAMP);
                    statement.setObject(13, p_bill_to_date, OracleTypes.TIMESTAMP);
                    statement.setObject(14, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(15, p_end_range, OracleTypes.NUMBER);
                    statement.setObject(16, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(17, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(18, OracleTypes.CURSOR);
                    statement.registerOutParameter(19, OracleTypes.NUMBER);
                    statement.registerOutParameter(20, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(18),CustomInvoiceInfo.getRowMapper())
                        ,statement.getObject(19)
                        ,statement.getObject(20)};
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
   public static Object[] isUserBiller(BigDecimal p_user_id){
        System.out.println("in isUserBiller "+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(IS_USER_BILLER);
                if (statement != null) {
                    statement.setObject(1, p_user_id, OracleTypes.NUMBER);
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
   
    public static Object[] getInvDetails(BigDecimal p_inv_number) {
        System.out.println("in getInvDetails " + p_inv_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_INV_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_inv_number, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2),
                                new CanonRowMapper() {
                                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                        return new String(
                                                rs.getString("URN_NUMBER"));
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
    
   public static Object[] approveRejectInvoices(BigDecimal p_invoice_id,
    String p_status,
    BigDecimal p_user_id,
    String p_comments){
        System.out.println("in approveRejectInvoices "+p_invoice_id+"|"+p_status+"|"+p_user_id+"|"+p_comments);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(APPROVE_REJECT_INVOICES);
                if (statement != null) {
                    statement.setObject(1, p_invoice_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_status, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_id, OracleTypes.NUMBER);
                    statement.setObject(4, p_comments, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(5)};
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
   
   public static Object[] getInvDetailsForApproval(BigDecimal p_inv_number){
        System.out.println("in getInvDetailsForApproval "+p_inv_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_INV_DETAILS_FOR_APPROVAL);
                if (statement != null) {
                    statement.setObject(1, p_inv_number, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),CanonCustomBillingInvSearchBean.getRowMapper())};
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
   
    public static Object[] isProd() {
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(IS_PROD);
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
    
   public static Object[] isConcurrentProgramRunning(){
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(IS_CONCURRENT_PROGRAM_RUNNING);
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
   
   public static Object[] getDbName(){
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_DB_NAME);
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
   
    public static Object[] getControlInfo(String p_urn_number) {
        System.out.println("in getUrnInfo " + p_urn_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_URN_INFO);
                if (statement != null) {
                    statement.setObject(1, p_urn_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.registerOutParameter(4, OracleTypes.CURSOR);
                    statement.registerOutParameter(5, OracleTypes.CURSOR);
                    statement.registerOutParameter(6, OracleTypes.CURSOR);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), ControlBean.getRowMapper())};
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

   public static Object[] deleteTemplate(BigDecimal p_header_id){
        System.out.println("in deleteTemplate "+p_header_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(DELETE_TEMPLATE);
                if (statement != null) {
                    statement.setObject(1, p_header_id, OracleTypes.NUMBER);
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
   
    public static class ControlBean {
       private String urnNmuber;
       private BigDecimal invId;
       private BigDecimal seqNo;
       private String controlType;
       private String tabName;
       private String field1;
       private String field2;
       private String field3;
       private String field4;
       private String field5;
       private String field6;
       private String field7;
       private String field8;
       private String field9;
       private String field10;
       private String field11;
       private String field12;
       private String field13;
       private String field14;
       private String field15;
       private String field16;
       private String field17;
       private String field18;
       private String field19;
       private String field20;
       private String field21;
       private String field22;
       private String field23;
       private String field24;
       private String field25;
       private String field26;
       private String field27;
       private String field28;
       private String field29;
       private String field30;
       private String field31;
       private String field32;
       private String field33;
       private String field34;
       private String field35;
       private String field36;
       private String field37;
       private String field38;
       private String field39;
       private String field40;
       private String field41;
       private String field42;
       private String field43;
       private String field44;
       private String field45;
       private String field46;
       private String field47;
       private String field48;
       private String field49;
       private String field50;
       private String field51;
       private String field52;
       private String field53;
       private String field54;
       private String field55;
       private String field56;
       private String field57;
       private String field58;
       private String field59;
       private String field60;
       private String field61;
       private String field62;
       private String field63;
       private String field64;
       private String field65;
       private String field66;
       private String field67;
       private String field68;
       private String field69;
       private String field70;
       private String field71;
       private String field72;
       private String field73;
       private String field74;
       private String field75;
       private String field76;
       private String field77;
       private String field78;
       private String field79;
       private String field80;
       private String field81;
       private String field82;
       private String field83;
       private String field84;
       private String field85;
       private String field86;
       private String field87;
       private String field88;
       private String field89;
       private String field90;
       private String field91;
       private String field92;
       private String field93;
       private String field94;
       private String field95;
       private String field96;
       private String field97;
       private String field98;
       private String field99;
       private String field100;
       private BigDecimal createdBy;
       private Timestamp creationDate;
       private BigDecimal lastUpdatedBy;
       private Timestamp lastUpdateDate;

        public ControlBean(){
        }
        public ControlBean(String urnNmuber,
                BigDecimal invId, 
                BigDecimal seqNo, 
                String controlType, 
                String tabName, 
                String field1, 
                String field2, 
                String field3, 
                String field4, 
                String field5, 
                String field6, 
                String field7, 
                String field8, 
                String field9, 
                String field10, 
                String field11, 
                String field12, 
                String field13, 
                String field14, 
                String field15, 
                String field16, 
                String field17, 
                String field18, 
                String field19, 
                String field20, 
                String field21, 
                String field22, 
                String field23, 
                String field24, 
                String field25, 
                String field26, 
                String field27, 
                String field28, 
                String field29, 
                String field30, 
                String field31, 
                String field32, 
                String field33, 
                String field34, 
                String field35, 
                String field36, 
                String field37, 
                String field38, 
                String field39, 
                String field40, 
                String field41, 
                String field42, 
                String field43, 
                String field44, 
                String field45, 
                String field46, 
                String field47, 
                String field48, 
                String field49, 
                String field50, 
                String field51, 
                String field52, 
                String field53, 
                String field54, 
                String field55, 
                String field56, 
                String field57, 
                String field58, 
                String field59, 
                String field60, 
                String field61, 
                String field62, 
                String field63, 
                String field64, 
                String field65, 
                String field66, 
                String field67, 
                String field68, 
                String field69, 
                String field70, 
                String field71, 
                String field72, 
                String field73, 
                String field74, 
                String field75, 
                String field76, 
                String field77, 
                String field78, 
                String field79, 
                String field80, 
                String field81, 
                String field82, 
                String field83, 
                String field84, 
                String field85, 
                String field86, 
                String field87, 
                String field88, 
                String field89, 
                String field90, 
                String field91, 
                String field92, 
                String field93, 
                String field94, 
                String field95, 
                String field96, 
                String field97, 
                String field98, 
                String field99, 
                String field100, 
                BigDecimal createdBy, 
                Timestamp creationDate, 
                BigDecimal lastUpdatedBy, 
                Timestamp lastUpdateDate){
            this.urnNmuber=urnNmuber;
            this.invId=invId;
            this.seqNo=seqNo;
            this.controlType=controlType;
            this.tabName=tabName;
            this.field1=field1;
            this.field2=field2;
            this.field3=field3;
            this.field4=field4;
            this.field5=field5;
            this.field6=field6;
            this.field7=field7;
            this.field8=field8;
            this.field9=field9;
            this.field10=field10;
            this.field11=field11;
            this.field12=field12;
            this.field13=field13;
            this.field14=field14;
            this.field15=field15;
            this.field16=field16;
            this.field17=field17;
            this.field18=field18;
            this.field19=field19;
            this.field20=field20;
            this.field21=field21;
            this.field22=field22;
            this.field23=field23;
            this.field24=field24;
            this.field25=field25;
            this.field26=field26;
            this.field27=field27;
            this.field28=field28;
            this.field29=field29;
            this.field30=field30;
            this.field31=field31;
            this.field32=field32;
            this.field33=field33;
            this.field34=field34;
            this.field35=field35;
            this.field36=field36;
            this.field37=field37;
            this.field38=field38;
            this.field39=field39;
            this.field40=field40;
            this.field41=field41;
            this.field42=field42;
            this.field43=field43;
            this.field44=field44;
            this.field45=field45;
            this.field46=field46;
            this.field47=field47;
            this.field48=field48;
            this.field49=field49;
            this.field50=field50;
            this.field51=field51;
            this.field52=field52;
            this.field53=field53;
            this.field54=field54;
            this.field55=field55;
            this.field56=field56;
            this.field57=field57;
            this.field58=field58;
            this.field59=field59;
            this.field60=field60;
            this.field61=field61;
            this.field62=field62;
            this.field63=field63;
            this.field64=field64;
            this.field65=field65;
            this.field66=field66;
            this.field67=field67;
            this.field68=field68;
            this.field69=field69;
            this.field70=field70;
            this.field71=field71;
            this.field72=field72;
            this.field73=field73;
            this.field74=field74;
            this.field75=field75;
            this.field76=field76;
            this.field77=field77;
            this.field78=field78;
            this.field79=field79;
            this.field80=field80;
            this.field81=field81;
            this.field82=field82;
            this.field83=field83;
            this.field84=field84;
            this.field85=field85;
            this.field86=field86;
            this.field87=field87;
            this.field88=field88;
            this.field89=field89;
            this.field90=field90;
            this.field91=field91;
            this.field92=field92;
            this.field93=field93;
            this.field94=field94;
            this.field95=field95;
            this.field96=field96;
            this.field97=field97;
            this.field98=field98;
            this.field99=field99;
            this.field100=field100;
            this.createdBy=createdBy;
            this.creationDate=creationDate;
            this.lastUpdatedBy=lastUpdatedBy;
            this.lastUpdateDate=lastUpdateDate;
        }

        public String getUrnNmuber() {
            return urnNmuber;
        }

        public void setUrnNmuber(String urnNmuber) {
            this.urnNmuber = urnNmuber;
        }

        public BigDecimal getInvId() {
            return invId;
        }
        public void setInvId(BigDecimal invId) {
            this.invId=invId;
        }
        public BigDecimal getSeqNo() {
            return seqNo;
        }
        public void setSeqNo(BigDecimal seqNo) {
            this.seqNo=seqNo;
        }
        public String getControlType() {
            return controlType;
        }
        public void setControlType(String controlType) {
            this.controlType=controlType;
        }
        public String getTabName() {
            return tabName;
        }
        public void setTabName(String tabName) {
            this.tabName=tabName;
        }
        public String getField1() {
            return field1;
        }
        public void setField1(String field1) {
            this.field1=field1;
        }
        public String getField2() {
            return field2;
        }
        public void setField2(String field2) {
            this.field2=field2;
        }
        public String getField3() {
            return field3;
        }
        public void setField3(String field3) {
            this.field3=field3;
        }
        public String getField4() {
            return field4;
        }
        public void setField4(String field4) {
            this.field4=field4;
        }
        public String getField5() {
            return field5;
        }
        public void setField5(String field5) {
            this.field5=field5;
        }
        public String getField6() {
            return field6;
        }
        public void setField6(String field6) {
            this.field6=field6;
        }
        public String getField7() {
            return field7;
        }
        public void setField7(String field7) {
            this.field7=field7;
        }
        public String getField8() {
            return field8;
        }
        public void setField8(String field8) {
            this.field8=field8;
        }
        public String getField9() {
            return field9;
        }
        public void setField9(String field9) {
            this.field9=field9;
        }
        public String getField10() {
            return field10;
        }
        public void setField10(String field10) {
            this.field10=field10;
        }
        public String getField11() {
            return field11;
        }
        public void setField11(String field11) {
            this.field11=field11;
        }
        public String getField12() {
            return field12;
        }
        public void setField12(String field12) {
            this.field12=field12;
        }
        public String getField13() {
            return field13;
        }
        public void setField13(String field13) {
            this.field13=field13;
        }
        public String getField14() {
            return field14;
        }
        public void setField14(String field14) {
            this.field14=field14;
        }
        public String getField15() {
            return field15;
        }
        public void setField15(String field15) {
            this.field15=field15;
        }
        public String getField16() {
            return field16;
        }
        public void setField16(String field16) {
            this.field16=field16;
        }
        public String getField17() {
            return field17;
        }
        public void setField17(String field17) {
            this.field17=field17;
        }
        public String getField18() {
            return field18;
        }
        public void setField18(String field18) {
            this.field18=field18;
        }
        public String getField19() {
            return field19;
        }
        public void setField19(String field19) {
            this.field19=field19;
        }
        public String getField20() {
            return field20;
        }
        public void setField20(String field20) {
            this.field20=field20;
        }
        public String getField21() {
            return field21;
        }
        public void setField21(String field21) {
            this.field21=field21;
        }
        public String getField22() {
            return field22;
        }
        public void setField22(String field22) {
            this.field22=field22;
        }
        public String getField23() {
            return field23;
        }
        public void setField23(String field23) {
            this.field23=field23;
        }
        public String getField24() {
            return field24;
        }
        public void setField24(String field24) {
            this.field24=field24;
        }
        public String getField25() {
            return field25;
        }
        public void setField25(String field25) {
            this.field25=field25;
        }
        public String getField26() {
            return field26;
        }
        public void setField26(String field26) {
            this.field26=field26;
        }
        public String getField27() {
            return field27;
        }
        public void setField27(String field27) {
            this.field27=field27;
        }
        public String getField28() {
            return field28;
        }
        public void setField28(String field28) {
            this.field28=field28;
        }
        public String getField29() {
            return field29;
        }
        public void setField29(String field29) {
            this.field29=field29;
        }
        public String getField30() {
            return field30;
        }
        public void setField30(String field30) {
            this.field30=field30;
        }
        public String getField31() {
            return field31;
        }
        public void setField31(String field31) {
            this.field31=field31;
        }
        public String getField32() {
            return field32;
        }
        public void setField32(String field32) {
            this.field32=field32;
        }
        public String getField33() {
            return field33;
        }
        public void setField33(String field33) {
            this.field33=field33;
        }
        public String getField34() {
            return field34;
        }
        public void setField34(String field34) {
            this.field34=field34;
        }
        public String getField35() {
            return field35;
        }
        public void setField35(String field35) {
            this.field35=field35;
        }
        public String getField36() {
            return field36;
        }
        public void setField36(String field36) {
            this.field36=field36;
        }
        public String getField37() {
            return field37;
        }
        public void setField37(String field37) {
            this.field37=field37;
        }
        public String getField38() {
            return field38;
        }
        public void setField38(String field38) {
            this.field38=field38;
        }
        public String getField39() {
            return field39;
        }
        public void setField39(String field39) {
            this.field39=field39;
        }
        public String getField40() {
            return field40;
        }
        public void setField40(String field40) {
            this.field40=field40;
        }
        public String getField41() {
            return field41;
        }
        public void setField41(String field41) {
            this.field41=field41;
        }
        public String getField42() {
            return field42;
        }
        public void setField42(String field42) {
            this.field42=field42;
        }
        public String getField43() {
            return field43;
        }
        public void setField43(String field43) {
            this.field43=field43;
        }
        public String getField44() {
            return field44;
        }
        public void setField44(String field44) {
            this.field44=field44;
        }
        public String getField45() {
            return field45;
        }
        public void setField45(String field45) {
            this.field45=field45;
        }
        public String getField46() {
            return field46;
        }
        public void setField46(String field46) {
            this.field46=field46;
        }
        public String getField47() {
            return field47;
        }
        public void setField47(String field47) {
            this.field47=field47;
        }
        public String getField48() {
            return field48;
        }
        public void setField48(String field48) {
            this.field48=field48;
        }
        public String getField49() {
            return field49;
        }
        public void setField49(String field49) {
            this.field49=field49;
        }
        public String getField50() {
            return field50;
        }
        public void setField50(String field50) {
            this.field50=field50;
        }
        public String getField51() {
            return field51;
        }
        public void setField51(String field51) {
            this.field51=field51;
        }
        public String getField52() {
            return field52;
        }
        public void setField52(String field52) {
            this.field52=field52;
        }
        public String getField53() {
            return field53;
        }
        public void setField53(String field53) {
            this.field53=field53;
        }
        public String getField54() {
            return field54;
        }
        public void setField54(String field54) {
            this.field54=field54;
        }
        public String getField55() {
            return field55;
        }
        public void setField55(String field55) {
            this.field55=field55;
        }
        public String getField56() {
            return field56;
        }
        public void setField56(String field56) {
            this.field56=field56;
        }
        public String getField57() {
            return field57;
        }
        public void setField57(String field57) {
            this.field57=field57;
        }
        public String getField58() {
            return field58;
        }
        public void setField58(String field58) {
            this.field58=field58;
        }
        public String getField59() {
            return field59;
        }
        public void setField59(String field59) {
            this.field59=field59;
        }
        public String getField60() {
            return field60;
        }
        public void setField60(String field60) {
            this.field60=field60;
        }
        public String getField61() {
            return field61;
        }
        public void setField61(String field61) {
            this.field61=field61;
        }
        public String getField62() {
            return field62;
        }
        public void setField62(String field62) {
            this.field62=field62;
        }
        public String getField63() {
            return field63;
        }
        public void setField63(String field63) {
            this.field63=field63;
        }
        public String getField64() {
            return field64;
        }
        public void setField64(String field64) {
            this.field64=field64;
        }
        public String getField65() {
            return field65;
        }
        public void setField65(String field65) {
            this.field65=field65;
        }
        public String getField66() {
            return field66;
        }
        public void setField66(String field66) {
            this.field66=field66;
        }
        public String getField67() {
            return field67;
        }
        public void setField67(String field67) {
            this.field67=field67;
        }
        public String getField68() {
            return field68;
        }
        public void setField68(String field68) {
            this.field68=field68;
        }
        public String getField69() {
            return field69;
        }
        public void setField69(String field69) {
            this.field69=field69;
        }
        public String getField70() {
            return field70;
        }
        public void setField70(String field70) {
            this.field70=field70;
        }
        public String getField71() {
            return field71;
        }
        public void setField71(String field71) {
            this.field71=field71;
        }
        public String getField72() {
            return field72;
        }
        public void setField72(String field72) {
            this.field72=field72;
        }
        public String getField73() {
            return field73;
        }
        public void setField73(String field73) {
            this.field73=field73;
        }
        public String getField74() {
            return field74;
        }
        public void setField74(String field74) {
            this.field74=field74;
        }
        public String getField75() {
            return field75;
        }
        public void setField75(String field75) {
            this.field75=field75;
        }
        public String getField76() {
            return field76;
        }
        public void setField76(String field76) {
            this.field76=field76;
        }
        public String getField77() {
            return field77;
        }
        public void setField77(String field77) {
            this.field77=field77;
        }
        public String getField78() {
            return field78;
        }
        public void setField78(String field78) {
            this.field78=field78;
        }
        public String getField79() {
            return field79;
        }
        public void setField79(String field79) {
            this.field79=field79;
        }
        public String getField80() {
            return field80;
        }
        public void setField80(String field80) {
            this.field80=field80;
        }
        public String getField81() {
            return field81;
        }
        public void setField81(String field81) {
            this.field81=field81;
        }
        public String getField82() {
            return field82;
        }
        public void setField82(String field82) {
            this.field82=field82;
        }
        public String getField83() {
            return field83;
        }
        public void setField83(String field83) {
            this.field83=field83;
        }
        public String getField84() {
            return field84;
        }
        public void setField84(String field84) {
            this.field84=field84;
        }
        public String getField85() {
            return field85;
        }
        public void setField85(String field85) {
            this.field85=field85;
        }
        public String getField86() {
            return field86;
        }
        public void setField86(String field86) {
            this.field86=field86;
        }
        public String getField87() {
            return field87;
        }
        public void setField87(String field87) {
            this.field87=field87;
        }
        public String getField88() {
            return field88;
        }
        public void setField88(String field88) {
            this.field88=field88;
        }
        public String getField89() {
            return field89;
        }
        public void setField89(String field89) {
            this.field89=field89;
        }
        public String getField90() {
            return field90;
        }
        public void setField90(String field90) {
            this.field90=field90;
        }
        public String getField91() {
            return field91;
        }
        public void setField91(String field91) {
            this.field91=field91;
        }
        public String getField92() {
            return field92;
        }
        public void setField92(String field92) {
            this.field92=field92;
        }
        public String getField93() {
            return field93;
        }
        public void setField93(String field93) {
            this.field93=field93;
        }
        public String getField94() {
            return field94;
        }
        public void setField94(String field94) {
            this.field94=field94;
        }
        public String getField95() {
            return field95;
        }
        public void setField95(String field95) {
            this.field95=field95;
        }
        public String getField96() {
            return field96;
        }
        public void setField96(String field96) {
            this.field96=field96;
        }
        public String getField97() {
            return field97;
        }
        public void setField97(String field97) {
            this.field97=field97;
        }
        public String getField98() {
            return field98;
        }
        public void setField98(String field98) {
            this.field98=field98;
        }
        public String getField99() {
            return field99;
        }
        public void setField99(String field99) {
            this.field99=field99;
        }
        public String getField100() {
            return field100;
        }
        public void setField100(String field100) {
            this.field100=field100;
        }
        public BigDecimal getCreatedBy() {
            return createdBy;
        }
        public void setCreatedBy(BigDecimal createdBy) {
            this.createdBy=createdBy;
        }
        public Timestamp getCreationDate() {
            return creationDate;
        }
        public void setCreationDate(Timestamp creationDate) {
            this.creationDate=creationDate;
        }
        public BigDecimal getLastUpdatedBy() {
            return lastUpdatedBy;
        }
        public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
            this.lastUpdatedBy=lastUpdatedBy;
        }
        public Timestamp getLastUpdateDate() {
            return lastUpdateDate;
        }
        public void setLastUpdateDate(Timestamp lastUpdateDate) {
            this.lastUpdateDate=lastUpdateDate;
        }
        public static CanonRowMapper getRowMapper(){
            return new CanonRowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ControlBean(
                        rs.getString("URN_NUMBER"),
                        rs.getBigDecimal("INV_ID"),
                        rs.getBigDecimal("SEQ_NO"),
                        rs.getString("CONTROL_TYPE"),
                        rs.getString("TAB_NAME"),
                        rs.getString("FIELD1"),
                        rs.getString("FIELD2"),
                        rs.getString("FIELD3"),
                        rs.getString("FIELD4"),
                        rs.getString("FIELD5"),
                        rs.getString("FIELD6"),
                        rs.getString("FIELD7"),
                        rs.getString("FIELD8"),
                        rs.getString("FIELD9"),
                        rs.getString("FIELD10"),
                        rs.getString("FIELD11"),
                        rs.getString("FIELD12"),
                        rs.getString("FIELD13"),
                        rs.getString("FIELD14"),
                        rs.getString("FIELD15"),
                        rs.getString("FIELD16"),
                        rs.getString("FIELD17"),
                        rs.getString("FIELD18"),
                        rs.getString("FIELD19"),
                        rs.getString("FIELD20"),
                        rs.getString("FIELD21"),
                        rs.getString("FIELD22"),
                        rs.getString("FIELD23"),
                        rs.getString("FIELD24"),
                        rs.getString("FIELD25"),
                        rs.getString("FIELD26"),
                        rs.getString("FIELD27"),
                        rs.getString("FIELD28"),
                        rs.getString("FIELD29"),
                        rs.getString("FIELD30"),
                        rs.getString("FIELD31"),
                        rs.getString("FIELD32"),
                        rs.getString("FIELD33"),
                        rs.getString("FIELD34"),
                        rs.getString("FIELD35"),
                        rs.getString("FIELD36"),
                        rs.getString("FIELD37"),
                        rs.getString("FIELD38"),
                        rs.getString("FIELD39"),
                        rs.getString("FIELD40"),
                        rs.getString("FIELD41"),
                        rs.getString("FIELD42"),
                        rs.getString("FIELD43"),
                        rs.getString("FIELD44"),
                        rs.getString("FIELD45"),
                        rs.getString("FIELD46"),
                        rs.getString("FIELD47"),
                        rs.getString("FIELD48"),
                        rs.getString("FIELD49"),
                        rs.getString("FIELD50"),
                        rs.getString("FIELD51"),
                        rs.getString("FIELD52"),
                        rs.getString("FIELD53"),
                        rs.getString("FIELD54"),
                        rs.getString("FIELD55"),
                        rs.getString("FIELD56"),
                        rs.getString("FIELD57"),
                        rs.getString("FIELD58"),
                        rs.getString("FIELD59"),
                        rs.getString("FIELD60"),
                        rs.getString("FIELD61"),
                        rs.getString("FIELD62"),
                        rs.getString("FIELD63"),
                        rs.getString("FIELD64"),
                        rs.getString("FIELD65"),
                        rs.getString("FIELD66"),
                        rs.getString("FIELD67"),
                        rs.getString("FIELD68"),
                        rs.getString("FIELD69"),
                        rs.getString("FIELD70"),
                        rs.getString("FIELD71"),
                        rs.getString("FIELD72"),
                        rs.getString("FIELD73"),
                        rs.getString("FIELD74"),
                        rs.getString("FIELD75"),
                        rs.getString("FIELD76"),
                        rs.getString("FIELD77"),
                        rs.getString("FIELD78"),
                        rs.getString("FIELD79"),
                        rs.getString("FIELD80"),
                        rs.getString("FIELD81"),
                        rs.getString("FIELD82"),
                        rs.getString("FIELD83"),
                        rs.getString("FIELD84"),
                        rs.getString("FIELD85"),
                        rs.getString("FIELD86"),
                        rs.getString("FIELD87"),
                        rs.getString("FIELD88"),
                        rs.getString("FIELD89"),
                        rs.getString("FIELD90"),
                        rs.getString("FIELD91"),
                        rs.getString("FIELD92"),
                        rs.getString("FIELD93"),
                        rs.getString("FIELD94"),
                        rs.getString("FIELD95"),
                        rs.getString("FIELD96"),
                        rs.getString("FIELD97"),
                        rs.getString("FIELD98"),
                        rs.getString("FIELD99"),
                        rs.getString("FIELD100"),
                        rs.getBigDecimal("CREATED_BY"),
                        rs.getTimestamp("CREATION_DATE"),
                        rs.getBigDecimal("LAST_UPDATED_BY"),
                        rs.getTimestamp("LAST_UPDATE_DATE")
                    );
                }
            };
        }
        public String toString() {
            return "CanonE479Control{" + "invId="+invId+", seqNo="+seqNo+", controlType="+controlType+", tabName="+tabName+", field1="+field1+", field2="+field2+", field3="+field3+", field4="+field4+", field5="+field5+", field6="+field6+", field7="+field7+", field8="+field8+", field9="+field9+", field10="+field10+", field11="+field11+", field12="+field12+", field13="+field13+", field14="+field14+", field15="+field15+", field16="+field16+", field17="+field17+", field18="+field18+", field19="+field19+", field20="+field20+", field21="+field21+", field22="+field22+", field23="+field23+", field24="+field24+", field25="+field25+", field26="+field26+", field27="+field27+", field28="+field28+", field29="+field29+", field30="+field30+", field31="+field31+", field32="+field32+", field33="+field33+", field34="+field34+", field35="+field35+", field36="+field36+", field37="+field37+", field38="+field38+", field39="+field39+", field40="+field40+", field41="+field41+", field42="+field42+", field43="+field43+", field44="+field44+", field45="+field45+", field46="+field46+", field47="+field47+", field48="+field48+", field49="+field49+", field50="+field50+", field51="+field51+", field52="+field52+", field53="+field53+", field54="+field54+", field55="+field55+", field56="+field56+", field57="+field57+", field58="+field58+", field59="+field59+", field60="+field60+", field61="+field61+", field62="+field62+", field63="+field63+", field64="+field64+", field65="+field65+", field66="+field66+", field67="+field67+", field68="+field68+", field69="+field69+", field70="+field70+", field71="+field71+", field72="+field72+", field73="+field73+", field74="+field74+", field75="+field75+", field76="+field76+", field77="+field77+", field78="+field78+", field79="+field79+", field80="+field80+", field81="+field81+", field82="+field82+", field83="+field83+", field84="+field84+", field85="+field85+", field86="+field86+", field87="+field87+", field88="+field88+", field89="+field89+", field90="+field90+", field91="+field91+", field92="+field92+", field93="+field93+", field94="+field94+", field95="+field95+", field96="+field96+", field97="+field97+", field98="+field98+", field99="+field99+", field100="+field100+", createdBy="+createdBy+", creationDate="+creationDate+", lastUpdatedBy="+lastUpdatedBy+", lastUpdateDate="+lastUpdateDate+'}';
        }
    }    

    // Database name CANDEV
    /*
    {call CANON_E479_INV_PKG.SEARCH_CUSTOM_INVOICES(3210,'','Y','','','','','','',NULL,NULL,1,10,'CREATION_DATE','desc',?,?,?)}
    (
    ROW_NUMBER NUMBER,
    INVOICE_ID NUMBER,
    URN_NUMBER VARCHAR2,
    INVOICE_TYPE VARCHAR2,
    INV_REGION VARCHAR2,
    BILLER_NAME VARCHAR2,
    BILLER_EMAIL VARCHAR2,
    CUSTOMER_EMAIL VARCHAR2,
    OTHER_EMAIL VARCHAR2,
    REVIEW_REQUIRED VARCHAR2,
    PROFILE_NAME VARCHAR2,
    CUSTOMER_NAME VARCHAR2,
    BILL_LOCATION VARCHAR2,
    BILL_NUMBER VARCHAR2,
    BILL_DATE DATE,
    INVOICE_BREAK VARCHAR2,
    BILL_PERIOD VARCHAR2,
    AMOUNT_DUE VARCHAR2,
    INVOICE_PATH VARCHAR2,
    SERVER_URL VARCHAR2,
    INVOICE_FILE_NAME VARCHAR2,
    PENDING_ACTION VARCHAR2,
    COMMENTS VARCHAR2,
    CREATION_DATE DATE,
    CREATED_BY NUMBER,
    LAST_UPDATED_BY NUMBER,
    LAST_UPDATE_DATE DATE,
    REMARKS VARCHAR2,
    PENDING_INV_FLAG VARCHAR2
    )
    */
    public static class CustomInvoiceInfo {
       private BigDecimal rowNumber;
       private BigDecimal invoiceId;
       private String urnNumber;
       private String invoiceType;
       private String invRegion;
       private String billerName;
       private String billerEmail;
       private String customerEmail;
       private String otherEmail;
       private String reviewRequired;
       private String parentCustomerName;
       private String customerName;
       private String billLocation;
       private String billNumber;
       private Timestamp billDate;
       private String invoiceBreak;
       private String billPeriod;
       private String amountDue;
       private String invoicePath;
       private String serverUrl;
       private String invoiceFileName;
       private String pendingAction;
       private String comments;
       private Timestamp creationDate;
       private BigDecimal createdBy;
       private BigDecimal lastUpdatedBy;
       private Timestamp lastUpdateDate;
       private String remarks;
       private String pendingInvFlag;
       private String customerGroup;

        public CustomInvoiceInfo(){
        }
        public CustomInvoiceInfo(BigDecimal rowNumber, 
                BigDecimal invoiceId, 
                String urnNumber, 
                String invoiceType, 
                String invRegion, 
                String billerName, 
                String billerEmail, 
                String customerEmail, 
                String otherEmail, 
                String reviewRequired, 
                String parentCustomerName,
                String customerGroup,
                String customerName, 
                String billLocation, 
                String billNumber, 
                Timestamp billDate, 
                String invoiceBreak, 
                String billPeriod, 
                String amountDue, 
                String invoicePath, 
                String serverUrl, 
                String invoiceFileName, 
                String pendingAction, 
                String comments, 
                Timestamp creationDate, 
                BigDecimal createdBy, 
                BigDecimal lastUpdatedBy, 
                Timestamp lastUpdateDate, 
                String remarks, 
                String pendingInvFlag){
            this.rowNumber=rowNumber;
            this.invoiceId=invoiceId;
            this.urnNumber=urnNumber;
            this.invoiceType=invoiceType;
            this.invRegion=invRegion;
            this.billerName=billerName;
            this.billerEmail=billerEmail;
            this.customerEmail=customerEmail;
            this.otherEmail=otherEmail;
            this.reviewRequired=reviewRequired;
            this.parentCustomerName=parentCustomerName;
            this.customerGroup=customerGroup;
            this.customerName=customerName;
            this.billLocation=billLocation;
            this.billNumber=billNumber;
            this.billDate=billDate;
            this.invoiceBreak=invoiceBreak;
            this.billPeriod=billPeriod;
            this.amountDue=amountDue;
            this.invoicePath=invoicePath;
            this.serverUrl=serverUrl;
            this.invoiceFileName=invoiceFileName;
            this.pendingAction=pendingAction;
            this.comments=comments;
            this.creationDate=creationDate;
            this.createdBy=createdBy;
            this.lastUpdatedBy=lastUpdatedBy;
            this.lastUpdateDate=lastUpdateDate;
            this.remarks=remarks;
            this.pendingInvFlag=pendingInvFlag;
        }
        public BigDecimal getRowNumber() {
            return rowNumber;
        }
        public void setRowNumber(BigDecimal rowNumber) {
            this.rowNumber=rowNumber;
        }
        public BigDecimal getInvoiceId() {
            return invoiceId;
        }
        public void setInvoiceId(BigDecimal invoiceId) {
            this.invoiceId=invoiceId;
        }
        public String getUrnNumber() {
            return urnNumber;
        }
        public void setUrnNumber(String urnNumber) {
            this.urnNumber=urnNumber;
        }
        public String getInvoiceType() {
            return invoiceType;
        }
        public void setInvoiceType(String invoiceType) {
            this.invoiceType=invoiceType;
        }
        public String getInvRegion() {
            return invRegion;
        }
        public void setInvRegion(String invRegion) {
            this.invRegion=invRegion;
        }
        public String getBillerName()  {
            return billerName;
        }
        public void setBillerName(String billerName) {
            this.billerName=billerName;
        }
        public String getBillerEmail() {
            return billerEmail;
        }
        public void setBillerEmail(String billerEmail) {
            this.billerEmail=billerEmail;
        }
        public String getCustomerEmail() {
            return customerEmail;
        }
        public void setCustomerEmail(String customerEmail) {
            this.customerEmail=customerEmail;
        }
        public String getOtherEmail() {
            return otherEmail;
        }
        public void setOtherEmail(String otherEmail) {
            this.otherEmail=otherEmail;
        }
        public String getReviewRequired() {
            return reviewRequired;
        }
        public void setReviewRequired(String reviewRequired) {
            this.reviewRequired=reviewRequired;
        }
        public String getParentCustomerName() {
            return parentCustomerName;
        }
        public void setParentCustomerName(String parentCustomerName) {
            this.parentCustomerName=parentCustomerName;
        }
        public String getCustomerGroup() {
            return customerGroup;
        }
        public void setCustomerGroup(String customerGroup) {
            this.customerGroup=customerGroup;
        }
        public String getCustomerName() {
            return customerName;
        }
        public void setCustomerName(String customerName) {
            this.customerName=customerName;
        }
        public String getBillLocation() {
            return billLocation;
        }
        public void setBillLocation(String billLocation) {
            this.billLocation=billLocation;
        }
        public String getBillNumber() {
            return billNumber;
        }
        public void setBillNumber(String billNumber) {
            this.billNumber=billNumber;
        }
        public Timestamp getBillDate() {
            return billDate;
        }
        public void setBillDate(Timestamp billDate) {
            this.billDate=billDate;
        }
        public String getInvoiceBreak() {
            return invoiceBreak;
        }
        public void setInvoiceBreak(String invoiceBreak) {
            this.invoiceBreak=invoiceBreak;
        }
        public String getBillPeriod() {
            return billPeriod;
        }
        public void setBillPeriod(String billPeriod) {
            this.billPeriod=billPeriod;
        }
        public String getAmountDue() {
            return amountDue;
        }
        public void setAmountDue(String amountDue) {
            this.amountDue=amountDue;
        }
        public String getInvoicePath() {
            return invoicePath;
        }
        public void setInvoicePath(String invoicePath) {
            this.invoicePath=invoicePath;
        }
        public String getServerUrl() {
            return serverUrl;
        }
        public void setServerUrl(String serverUrl) {
            this.serverUrl=serverUrl;
        }
        public String getInvoiceFileName() {
            return invoiceFileName;
        }
        public void setInvoiceFileName(String invoiceFileName) {
            this.invoiceFileName=invoiceFileName;
        }
        public String getPendingAction() {
            return pendingAction;
        }
        public void setPendingAction(String pendingAction) {
            this.pendingAction=pendingAction;
        }
        public String getComments() {
            return comments;
        }
        public void setComments(String comments) {
            this.comments=comments;
        }
        public Timestamp getCreationDate() {
            return creationDate;
        }
        public void setCreationDate(Timestamp creationDate) {
            this.creationDate=creationDate;
        }
        public BigDecimal getCreatedBy() {
            return createdBy;
        }
        public void setCreatedBy(BigDecimal createdBy) {
            this.createdBy=createdBy;
        }
        public BigDecimal getLastUpdatedBy() {
            return lastUpdatedBy;
        }
        public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
            this.lastUpdatedBy=lastUpdatedBy;
        }
        public Timestamp getLastUpdateDate() {
            return lastUpdateDate;
        }
        public void setLastUpdateDate(Timestamp lastUpdateDate) {
            this.lastUpdateDate=lastUpdateDate;
        }
        public String getRemarks() {
            return remarks;
        }
        public void setRemarks(String remarks) {
            this.remarks=remarks;
        }
        public String getPendingInvFlag() {
            return pendingInvFlag;
        }
        public void setPendingInvFlag(String pendingInvFlag) {
            this.pendingInvFlag=pendingInvFlag;
        }
        public static CanonRowMapper getRowMapper() {
            return new CanonRowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new CustomInvoiceInfo(
                        rs.getBigDecimal("ROW_NUMBER"),
                        rs.getBigDecimal("INVOICE_ID"),
                        rs.getString("URN_NUMBER"),
                        rs.getString("INVOICE_TYPE"),
                        rs.getString("INV_REGION"),
                        rs.getString("BILLER_NAME"),
                        rs.getString("BILLER_EMAIL"),
                        rs.getString("CUSTOMER_EMAIL"),
                        rs.getString("OTHER_EMAIL"),
                        rs.getString("REVIEW_REQUIRED"),
                        rs.getString("PARENT_CUSTOMER_NAME"),
                        rs.getString("DS_ACCT_GRP_NM"),
                        rs.getString("CUSTOMER_NAME"),
                        rs.getString("BILL_LOCATION"),
                        rs.getString("BILL_NUMBER"),
                        rs.getTimestamp("BILL_DATE"),
                        rs.getString("INVOICE_BREAK"),
                        rs.getString("BILL_PERIOD"),
                        rs.getString("AMOUNT_DUE"),
                        rs.getString("INVOICE_PATH"),
                        rs.getString("SERVER_URL"),
                        rs.getString("INVOICE_FILE_NAME"),
                        rs.getString("PENDING_ACTION" ),
                        rs.getString("COMMENTS"),
                        rs.getTimestamp("CREATION_DATE"),
                        rs.getBigDecimal("CREATED_BY"),
                        rs.getBigDecimal("LAST_UPDATED_BY"),
                        rs.getTimestamp("LAST_UPDATE_DATE"),
                        rs.getString("REMARKS"),
                        rs.getString("PENDING_INV_FLAG")
                    );
                }
            };
        }
        public String toString() {
            return "CustomInvoiceInfo{" + "rowNumber="+rowNumber+", invoiceId="+invoiceId+", urnNumber="+urnNumber+", invoiceType="+invoiceType+", invRegion="+invRegion+", billerName="+billerName+", billerEmail="+billerEmail+", customerEmail="+customerEmail+", otherEmail="+otherEmail+", reviewRequired="+reviewRequired+", parentCustomerName="+parentCustomerName+"customerGroup"+customerGroup+", customerName="+customerName+", billLocation="+billLocation+", billNumber="+billNumber+", billDate="+billDate+", invoiceBreak="+invoiceBreak+", billPeriod="+billPeriod+", amountDue="+amountDue+", invoicePath="+invoicePath+", serverUrl="+serverUrl+", invoiceFileName="+invoiceFileName+", pendingAction="+pendingAction+", comments="+comments+", creationDate="+creationDate+", createdBy="+createdBy+", lastUpdatedBy="+lastUpdatedBy+", lastUpdateDate="+lastUpdateDate+", remarks="+remarks+", pendingInvFlag="+pendingInvFlag+'}';
        }
    }

    public static Object[] getInitialPath(String p_source){
        System.out.println("in getInitialPath "+p_source);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_INITIAL_PATH);
                if (statement != null) {
                    statement.setObject(1, p_source, OracleTypes.VARCHAR);
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
    
}
