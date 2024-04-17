/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.custombilling.dao;

import oracle.apps.custombilling.beans.CanonCustomBillingCustomerGroupBean;
import canon.apps.common.CanonRowMapper;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

import oracle.jdbc.OracleTypes;

import java.math.BigDecimal;
import java.sql.Timestamp;

import oracle.apps.jtf.aom.transaction.TransactionScope;

public class CanonCustomBillingManualDAO {

    public static final String GET_BILL_PERIOD_LOV = "{call CANON_E479_MANUAL_BILLING_PKG.GET_BILL_PERIOD_LOV(?,?,?)}";
    public static final String GET_BILL_TO_LOCATION_LOV = "{call CANON_E479_MANUAL_BILLING_PKG.GET_BILL_TO_LOCATION_LOV(?,?,?,?,?,?,?,?,?,?)}";
    public static final String GET_CUSTOMER_NAME_LOV = "{call CANON_E479_MANUAL_BILLING_PKG.GET_CUSTOMER_NAME_LOV(?,?,?,?,?,?,?,?,?)}";
    public static final String GET_CUSTOMER_GROUP_LOV = "{call CANON_E479_MANUAL_BILLING_PKG.get_customer_group_LOV(?,?,?,?,?,?,?,?)}";
    public static final String GET_PARENT_CUSTOMER_LOV = "{call CANON_E479_MANUAL_BILLING_PKG.get_parent_customer_LOV(?,?,?,?,?,?,?,?,?,?)}";
    //public static final String GET_CUSTOMER_NAME_LOV = "{call CANON_E479_INV_SRCH_UTIL_PKG.get_customer_name_LOV(?,?,?,?,?,?,?,?,?)}";
    //public static final String GET_BILL_TO_LOCATION_LOV = "{call CANON_E479_INV_SRCH_UTIL_PKG.GET_BILL_TO_LOCATION_LOV(?,?,?,?,?,?,?,?,?,?)}"
    public static final String GET_SEQUENCE_VALUES = "{call CANON_E479_MANUAL_BILLING_PKG.GET_SEQUENCE_VALUES(?,?,?,?)}";
    public static final String URN_UPDATE_STATUS = "{call CANON_E479_MANUAL_BILLING_PKG.URN_UPDATE_STATUS(?,?,?,?,?,?,?)}";
    public static final String CREATE_INVOICE_RECORD = "{call CANON_E479_INV_PKG.CREATE_INVOICE_RECORD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String GET_SERVER_INITIAL_PATH = "{call CANON_E479_INV_PKG.GET_INITIAL_PATH(?,?)}";
    //public static final String GET_SERVER_INITIAL_PATH = "{call CANON_E479_INV_PKG.GET_SERVER_INITIAL_PATH(?)}";
    public static final String GET_BILLER_NAME_LOV = "{call CANON_E479_MANUAL_BILLING_PKG.GET_BILLER_NAME_LOV(?,?,?)}";
    public static final String CHECK_VALIDATE_URN = "{call CANON_E479_MANUAL_BILLING_PKG.check_valid_urn(?,?,?,?,?,?,?,?)}";
         
  
    public static Object[] getBillPeriodLov() {
        System.out.println("in getBillPeriodLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_BILL_PERIOD_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(1), BillPeriodInfo.getRowMapper()), statement.getObject(2), statement.getObject(3)};
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

    public static Object[] getSequenceValues() {
        System.out.println("in getSequenceValues ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SEQUENCE_VALUES);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1), statement.getObject(2), statement.getObject(3), statement.getObject(4)};
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
    // Added by DUNA RAO om NOV-16-2016 for ITG Request 645933
    public static Object[] checkvalidateurn(String p_cust_name,String p_profile_name,String p_bill_location,String p_urn_number,String p_bill_period,Timestamp p_bill_date) {
        System.out.println("in checkvalidateurn ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_VALIDATE_URN);
                if (statement != null) {
                	statement.setObject(1, p_cust_name, OracleTypes.VARCHAR);
                	statement.setObject(2, p_profile_name, OracleTypes.VARCHAR);
                	statement.setObject(3, p_bill_location, OracleTypes.VARCHAR);
                	statement.setObject(4, p_urn_number, OracleTypes.VARCHAR);
                    statement.setObject(5, p_bill_period, OracleTypes.VARCHAR);
                    statement.setObject(6, p_bill_date, OracleTypes.TIMESTAMP);
                	statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
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
   
    public static Object[] createInvoiceRecord(BigDecimal p_invoice_id,
            String p_urn_number,
            String p_invoice_type,
            String p_invoice_region,
            String p_biller_name,
            String p_biller_email,
            String p_customer_email,
            String p_other_email,
            String p_review_required,
            String p_parent_cust_name,
            String p_cust_group,
            String p_customer_name,
            String p_bill_location,
            String p_bill_number,
            Timestamp p_bill_date,
            String p_invoice_break,
            String p_bill_period,
            BigDecimal p_amount_due,
            String p_invoice_path,
            String p_pending_action,
            BigDecimal p_user_id,
            String p_file_name,
            String p_remarks) {
        System.out.println("in createInvoiceRecord " + p_invoice_id + "|" + p_urn_number + "|" + p_invoice_type + "|" + p_invoice_region + "|" + p_biller_name + "|" + p_biller_email + "|" + p_customer_email + "|" + p_other_email + "|" + p_review_required + "|" + "|" + p_customer_name + "|" + p_bill_location + "|" + p_bill_number + "|" + p_bill_date + "|" + p_invoice_break + "|" + p_bill_period + "|" + p_amount_due + "|" + p_invoice_path + "|" + p_pending_action + "|" + p_user_id + "|" + p_file_name + "|" + p_remarks);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CREATE_INVOICE_RECORD);
                if (statement != null) {
                    statement.setObject(1, p_invoice_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_urn_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_invoice_type, OracleTypes.VARCHAR);
                    statement.setObject(4, p_invoice_region, OracleTypes.VARCHAR);
                    statement.setObject(5, p_biller_name, OracleTypes.VARCHAR);
                    statement.setObject(6, p_biller_email, OracleTypes.VARCHAR);
                    statement.setObject(7, p_customer_email, OracleTypes.VARCHAR);
                    statement.setObject(8, p_other_email, OracleTypes.VARCHAR);
                    statement.setObject(9, p_review_required, OracleTypes.VARCHAR);
                    statement.setObject(10, p_parent_cust_name, OracleTypes.VARCHAR);
                    statement.setObject(11, p_cust_group, OracleTypes.VARCHAR);
                    statement.setObject(12, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(13, p_bill_location, OracleTypes.VARCHAR);
                    statement.setObject(14, p_bill_number, OracleTypes.VARCHAR);
                    statement.setObject(15, p_bill_date, OracleTypes.TIMESTAMP);
                    statement.setObject(16, p_invoice_break, OracleTypes.VARCHAR);
                    statement.setObject(17, p_bill_period, OracleTypes.VARCHAR);
                    statement.setObject(18, p_amount_due, OracleTypes.NUMBER);
                    statement.setObject(19, p_invoice_path, OracleTypes.VARCHAR);
                    statement.setObject(20, p_pending_action, OracleTypes.VARCHAR);
                    statement.setObject(21, p_user_id, OracleTypes.NUMBER);
                    statement.setObject(22, p_file_name, OracleTypes.VARCHAR);
                    statement.setObject(23, p_remarks, OracleTypes.VARCHAR);
                    statement.registerOutParameter(24, OracleTypes.NUMBER);
                    statement.execute();
                    return new Object[]{statement.getObject(24)};
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

    public static Object[] getServerInitialPath(String p_source) {

        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SERVER_INITIAL_PATH);
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

    public static Object[] getBillerNameLov() {
        System.out.println("in getBillerNameLov ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_BILLER_NAME_LOV);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(1), BillerNameInfo.getRowMapper()), statement.getObject(2), statement.getObject(3)};
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

    public static Object[] getCustomerNameLov(String p_customer_name,
            BigDecimal p_start_range,
            BigDecimal p_end_range,
            String p_sort_by,
            String p_sort_type) {
        System.out.println("in getCustomerNameLov " + p_customer_name + "|" + p_start_range + "|" + p_end_range + "|" + p_sort_by + "|" + p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_CUSTOMER_NAME_LOV);
                if (statement != null) {
                    statement.setObject(1, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(2, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(3, p_end_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(5, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.CURSOR);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(6), CustNameInfo.getRowMapper()), statement.getObject(7), statement.getObject(8), statement.getObject(9)};
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
    
    
    public static Object[] getManualParentCustomerNameLov(String p_customer_id,
    		String parent_customer_name,
            BigDecimal p_start_range,
            BigDecimal p_end_range,
            String p_sort_by,
            String p_sort_type) {
        System.out.println("in getManualParentCustomerNameLov: "+p_customer_id + "|" +  parent_customer_name + "|" + p_start_range + "|" + p_end_range + "|" + p_sort_by + "|" + p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_PARENT_CUSTOMER_LOV);
                if (statement != null) {
                	statement.setObject(1, p_customer_id, OracleTypes.VARCHAR);
                    statement.setObject(2, parent_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(3, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_end_range, OracleTypes.NUMBER);
                    statement.setObject(5, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(6, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.CURSOR);
                    statement.registerOutParameter(8, OracleTypes.NUMBER);
                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
                    statement.registerOutParameter(10, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("getManualParentCustomerNameLov number of records:"+ ((BigDecimal) statement.getObject(8)).intValue()); 
                    return new Object[]{cursorToList((ResultSet) statement.getObject(7), CustNameInfo.getRowMapper()), statement.getObject(8), statement.getObject(9), statement.getObject(10)};
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

    public static Object[] getManualCustomGroupLOV(String p_customer_id,
            String customerGroup,
            BigDecimal p_start_range,
            BigDecimal p_end_range) {
        System.out.println("in getManualCustomGroupLOV " + p_customer_id + "|" + customerGroup + "|" + p_start_range + "|" + p_end_range);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_CUSTOMER_GROUP_LOV);
                if (statement != null) {
                    statement.setObject(1, p_customer_id, OracleTypes.VARCHAR);
                    statement.setObject(2, customerGroup, OracleTypes.VARCHAR);
                    statement.setObject(3, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_end_range, OracleTypes.NUMBER);
                    statement.registerOutParameter(5, OracleTypes.CURSOR);
                    statement.registerOutParameter(6, OracleTypes.NUMBER);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("getManualCustomGroupLOV number of records:"+ ((BigDecimal) statement.getObject(6)).intValue());
                    return new Object[]{cursorToList((ResultSet) statement.getObject(5), CustGroupInfo.getRowMapper()), statement.getObject(6), statement.getObject(7), statement.getObject(8)};
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
    
    public static Object[] getBillToLocationLov(String p_customer_id,
            String p_bill_location,
            BigDecimal p_start_range,
            BigDecimal p_end_range,
            String p_sort_by,
            String p_sort_type) {
        System.out.println("in getBillToLocationLov " + p_customer_id + "|" + p_bill_location + "|" + p_sort_by + "|" + p_sort_type);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_BILL_TO_LOCATION_LOV);
                if (statement != null) {
                    statement.setObject(1, p_customer_id, OracleTypes.VARCHAR);
                    statement.setObject(2, p_bill_location, OracleTypes.VARCHAR);
                    statement.setObject(3, p_start_range, OracleTypes.NUMBER);
                    statement.setObject(4, p_end_range, OracleTypes.NUMBER);                    
                    statement.setObject(5, p_sort_by, OracleTypes.VARCHAR);
                    statement.setObject(6, p_sort_type, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.CURSOR);
                    statement.registerOutParameter(8, OracleTypes.NUMBER);
                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
                    statement.registerOutParameter(10, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println(" number of records:"+ ((BigDecimal) statement.getObject(8)).intValue());
                    return new Object[]{cursorToList((ResultSet) statement.getObject(7), BillLocationInfo.getRowMapper()), statement.getObject(8), statement.getObject(9), statement.getObject(10)};
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

    public static Object[] urnUpdateStatus(BigDecimal p_invoice_id,
            BigDecimal p_urn_number,
            String p_customer_name,
            String p_bill_period,
            String p_bill_location) {
        System.out.println("in urnUpdateStatus " + p_invoice_id + "|" + p_urn_number + "|" + p_customer_name + "|" + p_bill_period + "|" + p_bill_location);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(URN_UPDATE_STATUS);
                if (statement != null) {
                    statement.setObject(1, p_invoice_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_urn_number, OracleTypes.NUMBER);
                    statement.setObject(3, p_customer_name, OracleTypes.VARCHAR);
                    statement.setObject(4, p_bill_period, OracleTypes.VARCHAR);
                    statement.setObject(5, p_bill_location, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(6), statement.getObject(7)};
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
     {call CANON_E479_MANUAL_BILLING_PKG.GET_BILL_TO_LOCATION_LOV(590099,NULL,NULL,NULL,?,?,?,?)}
     (
     CUSTOMER_NAME VARCHAR2,
     SITE_USE_ID NUMBER,
     ADDRESS1 VARCHAR2,
     ADDRESS2 VARCHAR2,
     CITY VARCHAR2,
     STATE VARCHAR2,
     POSTAL_CODE VARCHAR2,
     LOCATION VARCHAR2
     )
     */
    public static class BillLocationInfo {

        private String customerName;
        private String siteUseId;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String postalCode;
        private String location;

        public BillLocationInfo() {
        }

        public BillLocationInfo(String customerName,
                String siteUseId,
                String address1,
                String address2,
                String city,
                String state,
                String postalCode,
                String location) {
            this.customerName = customerName;
            this.siteUseId = siteUseId;
            this.address1 = address1;
            this.address2 = address2;
            this.city = city;
            this.state = state;
            this.postalCode = postalCode;
            this.location = location;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSiteUseId() {
            return siteUseId;
        }

        public void setSiteUseId(String siteUseId) {
            this.siteUseId = siteUseId;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public static RowMapper getRowMapper() {
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new BillLocationInfo(rs.getString("CUSTOMER_NAME"),
                            rs.getString("LOCATION"),
                            //rs.getBigDecimal("SITE_USE_ID"),
                            rs.getString("ADDRESS1"),
                            rs.getString("ADDRESS2"),
                            rs.getString("CITY"),
                            rs.getString("STATE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("LOCATION")
                    );
                }
            };
        }

        public String toString() {
            return "BillLocationInfo{" + "customerName=" + customerName + ", siteUseId=" + siteUseId + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + ", location=" + location + '}';
        }
    }

    /*
     {call CANON_E479_MANUAL_BILLING_PKG.GET_BILL_PERIOD_LOV(?,?,?)}
     (
     ACCOUNTING_PERIOD VARCHAR2
     )
     */
    public static class BillPeriodInfo {

        private String accountingPeriod;

        public BillPeriodInfo() {
        }

        public BillPeriodInfo(String accountingPeriod) {
            this.accountingPeriod = accountingPeriod;
        }

        public String getAccountingPeriod() {
            return accountingPeriod;
        }

        public void setAccountingPeriod(String accountingPeriod) {
            this.accountingPeriod = accountingPeriod;
        }

        public static RowMapper getRowMapper() {
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new BillPeriodInfo(rs.getString("ACCOUNTING_PERIOD")
                    );
                }
            };
        }

        public String toString() {
            return "BillPeriodInfo{" + "accountingPeriod=" + accountingPeriod + '}';
        }
    }

    /*
     {call CANON_E479_MANUAL_BILLING_PKG.GET_BILLER_NAME_LOV(?,?,?)}
     (
     USER_ID NUMBER,
     BILLER_NAME VARCHAR2
     )
     */
    public static class BillerNameInfo {

        private BigDecimal userId;
        private String billerName;

        public BillerNameInfo() {
        }

        public BillerNameInfo(BigDecimal userId,
                String billerName) {
            this.userId = userId;
            this.billerName = billerName;
        }

        public BigDecimal getUserId() {
            return userId;
        }

        public void setUserId(BigDecimal userId) {
            this.userId = userId;
        }

        public String getBillerName() {
            return billerName;
        }

        public void setBillerName(String billerName) {
            this.billerName = billerName;
        }

        public static RowMapper getRowMapper() {
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new BillerNameInfo(rs.getBigDecimal("USER_ID"),
                            rs.getString("BILLER_NAME")
                    );
                }
            };
        }

        public String toString() {
            return "BillerNameInfo{" + "userId=" + userId + ", billerName=" + billerName + '}';
        }
    }

    /*
     {call CANON_E479_MANUAL_BILLING_PKG.GET_CUSTOMER_NAME_LOV(NULL,1,1,NULL,NULL,?,?,?,?)}
     (
     CUSTOMER_NAME VARCHAR2,
     CUSTOMER_NUMBER VARCHAR2,
     CUSTOMER_ID NUMBER
     )
     */
    public static class CustNameInfo {

        private String customerName;
        private String customerNumber;
        private BigDecimal customerId;

        public CustNameInfo() {
        }

        public CustNameInfo(String customerName,
                String customerNumber,
                BigDecimal customerId) {
            this.customerName = customerName;
            this.customerNumber = customerNumber;
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerNumber() {
            return customerNumber;
        }

        public void setCustomerNumber(String customerNumber) {
            this.customerNumber = customerNumber;
        }

        public BigDecimal getCustomerId() {
            return customerId;
        }

        public void setCustomerId(BigDecimal customerId) {
            this.customerId = customerId;
        }

        public static RowMapper getRowMapper() {
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new CustNameInfo(rs.getString("CUSTOMER_NAME"),
                            rs.getString("CUSTOMER_NUMBER"),
                            rs.getBigDecimal("CUSTOMER_ID")
                    );
                }
            };
        }

        public String toString() {
            return "CustNameInfo{" + "customerName=" + customerName + ", customerNumber=" + customerNumber + ", customerId=" + customerId + '}';
        }
    }
    
    
    public static class CustGroupInfo {
    	
    	 private String customerGroup;

    	    public CustGroupInfo(){
    	    }
    	    public CustGroupInfo(String customerGroup 
    	     ){
    	        this.customerGroup=customerGroup;
    	     
    	    }
    	    public String getCustomerGroup() {
    	        return customerGroup;
    	    }
    	    public void setCustomerGroup(String customerGroup) {
    	        this.customerGroup=customerGroup;
    	    }

    	  
    	    public static RowMapper getRowMapper(){
    	        return new RowMapper() {
    	            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	                return new CustGroupInfo(
    	                    rs.getString("GROUP_NAME")
    	                );
    	            }
    	        };
    	    }
    	    public String toString() {
    	        return "CustGroupInfo{" + "customerGroup="+customerGroup+'}';
    	    }   	    
    	   
    }

    private static List cursorToList(ResultSet cursor, RowMapper rowMapper) {
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

    public interface RowMapper {

        Object mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

}
