package oracle.apps.custombilling.server;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import oracle.apps.jtf.aom.transaction.TransactionScope;

public class CanonCustomBillingServerInvoiceDAO {

    public static final String GET_URN_INFO= "{call CANON_E479_INV_PKG.GET_URN_INFO(?,?,?,?,?,?,?,?)}";
    public static final String GET_INV_DETAILS = "{call CANON_E479_INV_PKG.GET_INV_DETAILS(?,?)}";
    public static final String GET_INVOICES_TO_CREATE = "{call CANON_E479_INV_PKG.GET_INVOICES_TO_CREATE(?)}";
    public static final String CREATE_INVOICE_RECORD= "{call CANON_E479_INV_PKG.CREATE_INVOICE_RECORD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String IS_PROD = "{call CANON_E479_TEMPLATE_UTIL_PKG.IS_PROD(?)}";
    public static final String GET_SALE_INFO = "{call CANON_E479_INV_PKG.GET_SALE_INFO(?,?)}";
    public static final String GET_INITIAL_PATH= "{call CANON_E479_INV_PKG.GET_INITIAL_PATH(?,?)}";
    public static final String GET_DB_NAME= "{call CANON_E479_TEMPLATE_UTIL_PKG.GET_DB_NAME(?)}";
    public static final String GET_BODY_SUBJECT= "{call CANON_E479_INV_PKG.GET_BODY_SUBJECT(?,?)}";

    public static final String GET_BILL_INV_UPD = "{call CANON_E479_INV_PKG.get_bill_invoices_for_update(?,?,?,?,?,?)}";
    
    public CanonCustomBillingServerInvoiceDAO() {
    }

    public static List getAllUrnNumber() {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement("select distinct INV_ID, urn_number from CANON_E479_INV_DTL_TBL order by INV_ID, to_number(urn_number)");
                if (statement != null) {
                    ResultSet cursor = statement.executeQuery();
                    return cursorToList(cursor, InvDetail.getRowMapper());
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

    public static Object[] getUrnInfo(String p_urn_number) {
        System.out.println("in getUrnInfo " + p_urn_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareCall(GET_URN_INFO);
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
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), CanonCustomBillingServerControlBean.getRowMapper()), 
                        cursorToList((ResultSet) statement.getObject(3), CanonCustomBillingServerRemittanceStubBean.getRowMapper()), 
                        cursorToList((ResultSet) statement.getObject(4), CanonCustomBillingServerDetailBean.getRowMapper()), 
                        cursorToList((ResultSet) statement.getObject(5), CanonCustomBillingServerSummaryBean.getRowMapper()), 
                        cursorToList((ResultSet) statement.getObject(6), CanonCustomBillingServerTaxBean.getRowMapper()),
                        statement.getObject(7),
                        statement.getObject(8)
                            };
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

    public static Object[] getBillInvforUpd(String p_source,String p_urn_number) {
        System.out.println("in getBillInvforUpd " + p_urn_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareCall(GET_BILL_INV_UPD);
                if (statement != null) {
                	statement.setObject(1, p_source, OracleTypes.VARCHAR);
                    statement.setObject(2, p_urn_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.registerOutParameter(4, OracleTypes.CURSOR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{ 
                        cursorToList((ResultSet) statement.getObject(3), billupdInfo.getRowMapper()),
                        cursorToList((ResultSet) statement.getObject(4), billinvoiceupdInfo.getRowMapper()),
                        statement.getObject(5),
                        statement.getObject(6)
                            };
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

    
    public static interface RowMapper {
        Object mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

    
    private static List cursorToList(ResultSet cursor, RowMapper rowMapper) {
        List list = new ArrayList();
        try {
            while (cursor.next()) {
                list.add(rowMapper.mapRow(cursor, 0));
            }
        } catch (SQLException ex) {
            //saveException(ex);
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
    
    public static Object[] getInvDetails(BigDecimal p_inv_number) {
        System.out.println("in getInvDetails " + p_inv_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareCall(GET_INV_DETAILS);
                if (statement != null) {
                    statement.setObject(1, p_inv_number, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2),
                                new CanonCustomBillingServerRowMapper() {

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

    public static Object[] getInvoicesToCreate() {
        System.out.println("in getInvoicesToCreate ");

        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareCall(GET_INVOICES_TO_CREATE);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(1),
                                InvoiceInfo.getRowMapper())};
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
    {call CANON_E479_INV_PKG.GET_INVOICES_TO_CREATE(?)}
    (
    INV_ID NUMBER,
    NEGATIVE_READ_URN VARCHAR2,
    HIGH_DOLLAR_URN VARCHAR2,
    RANDOM_URN VARCHAR2
    )
    */
    public static class InvoiceInfo {
       private BigDecimal invId;
       private String negativeReadUrn;
       private String highDollarUrn;
       private String randomUrn;

        public InvoiceInfo(){
        }
        public InvoiceInfo(BigDecimal invId, 
                String negativeReadUrn, 
                String highDollarUrn, 
                String randomUrn){
            this.invId=invId;
            this.negativeReadUrn=negativeReadUrn;
            this.highDollarUrn=highDollarUrn;
            this.randomUrn=randomUrn;
        }
        public BigDecimal getInvId() {
            return invId;
        }
        public void setInvId(BigDecimal invId) {
            this.invId=invId;
        }
        public String getNegativeReadUrn() {
            return negativeReadUrn;
        }
        public void setNegativeReadUrn(String negativeReadUrn) {
            this.negativeReadUrn=negativeReadUrn;
        }
        public String getHighDollarUrn() {
            return highDollarUrn;
        }
        public void setHighDollarUrn(String highDollarUrn) {
            this.highDollarUrn=highDollarUrn;
        }
        public String getRandomUrn() {
            return randomUrn;
        }
        public void setRandomUrn(String randomUrn) {
            this.randomUrn=randomUrn;
        }
        public static CanonCustomBillingServerRowMapper getRowMapper(){
            return new CanonCustomBillingServerRowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new InvoiceInfo(
                        rs.getBigDecimal("INV_ID"),
                        rs.getString("NEGATIVE_READ_URN"),
                        rs.getString("HIGH_DOLLAR_URN"),
                        rs.getString("RANDOM_URN")
                    );
                }
            };
        }
        public String toString() {
            return "InvoiceInfo{" + "invId="+invId+", negativeReadUrn="+negativeReadUrn+", highDollarUrn="+highDollarUrn+", randomUrn="+randomUrn+'}';
        }
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
    	    String p_parent_customer_name,
    	    String p_customer_group,
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
    	    String p_remarks){
    	        System.out.println("in createInvoiceRecord "+p_invoice_id+"|"+p_urn_number+"|"+p_invoice_type+"|"+p_invoice_region+"|"+p_biller_name+"|"+p_biller_email+"|"+p_customer_email+"|"+p_other_email+"|"+p_review_required+"|"+p_parent_customer_name+"|"+p_customer_group+"|"+p_customer_name+"|"+p_bill_location+"|"+p_bill_number+"|"+p_bill_date+"|"+p_invoice_break+"|"+p_bill_period+"|"+p_amount_due+"|"+p_invoice_path+"|"+p_pending_action+"|"+p_user_id+"|"+p_file_name+"|"+p_remarks);
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
    	                    statement.setObject(10, p_parent_customer_name, OracleTypes.VARCHAR);
    	                    statement.setObject(11, p_customer_group, OracleTypes.VARCHAR);
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
   
    public static Object[] getSaleInfo(String p_urn_number) {
        System.out.println("in getSaleInfo " + p_urn_number);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareCall(GET_SALE_INFO);
                if (statement != null) {
                    statement.setObject(1, p_urn_number, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet) statement.getObject(2), SaleInfo.getRowMapper())};
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

    private static List cursorToList(ResultSet cursor, CanonCustomBillingServerRowMapper rowMapper) {
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

    /*
    select distinct INV_ID, urn_number from CANON_E479_SMPL_INV_DTL_TBL order by INV_ID, to_number(urn_number)
    (
    INV_ID NUMBER,
    URN_NUMBER VARCHAR2
    )
     */
    public static class InvDetail {

        private BigDecimal invId;
        private String urnNumber;

        public InvDetail() {
        }

        public InvDetail(BigDecimal invId,
                String urnNumber) {
            this.invId = invId;
            this.urnNumber = urnNumber;
        }

        public BigDecimal getInvId() {
            return invId;
        }

        public void setInvId(BigDecimal invId) {
            this.invId = invId;
        }

        public String getUrnNumber() {
            return urnNumber;
        }

        public void setUrnNumber(String urnNumber) {
            this.urnNumber = urnNumber;
        }

        public static CanonCustomBillingServerRowMapper getRowMapper() {
            return new CanonCustomBillingServerRowMapper() {

                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new InvDetail(
                            rs.getBigDecimal("INV_ID"),
                            rs.getString("URN_NUMBER"));
                }
            };
        }

        public String toString() {
            return "InvDetail{" + "invId=" + invId + ", urnNumber=" + urnNumber + '}';
        }
    }

    public static class SaleInfo {

        private String ordertype;
        private String producttype;
        private String modelName;
        private BigDecimal totalCount;
        private BigDecimal totalCharge;

        public SaleInfo() {
        }

        public SaleInfo(String ordertype,
                String producttype,
                String modelName,
                BigDecimal totalCount,
                BigDecimal totalCharge) {
            this.ordertype = ordertype;
            this.producttype = producttype;
            this.modelName = modelName;
            this.totalCount = totalCount;
            this.totalCharge = totalCharge;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getProducttype() {
            return producttype;
        }

        public void setProducttype(String producttype) {
            this.producttype = producttype;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public BigDecimal getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(BigDecimal totalCount) {
            this.totalCount = totalCount;
        }

        public BigDecimal getTotalCharge() {
            return totalCharge;
        }

        public void setTotalCharge(BigDecimal totalCharge) {
            this.totalCharge = totalCharge;
        }

        public static CanonCustomBillingServerRowMapper getRowMapper() {
            return new CanonCustomBillingServerRowMapper() {

                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new SaleInfo(
                            rs.getString("ORDERTYPE"),
                            rs.getString("PRODUCTTYPE"),
                            rs.getString("MODEL_NAME"),
                            rs.getBigDecimal("TOTAL_COUNT"),
                            rs.getBigDecimal("TOTAL_CHARGE"));
                }
            };
        }

        public String toString() {
            return "SaleInfo{" + "ordertype=" + ordertype + ", producttype=" + producttype + ", modelName=" + modelName + ", totalCount=" + totalCount + ", totalCharge=" + totalCharge + '}';
        }
    }

    public static class billupdInfo {

        private String billNumber;
        private Timestamp billdate;

        public billupdInfo() {
        }

		public billupdInfo(String billNumber, Timestamp billdate) {
			super();
			this.billNumber = billNumber;
			this.billdate = billdate;
		}

		public String getBillNumber() {
			return billNumber;
		}



		public void setBillNumber(String billNumber) {
			this.billNumber = billNumber;
		}



		public Timestamp getBilldate() {
			return billdate;
		}



		public void setBilldate(Timestamp billdate) {
			this.billdate = billdate;
		}

		public static RowMapper getRowMapper() {
            return new RowMapper() {

                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new billupdInfo(
                            rs.getString("BILL_NUMBER"),
                            rs.getTimestamp("BILL_DATE"));
                }
            };
        }

    }    
    
    public static class billinvoiceupdInfo {

        private String urn;
        private String billNumber;
        private String invoiceNumber;
        private Timestamp billdate;

        public billinvoiceupdInfo() {
        }

        public billinvoiceupdInfo(String urn, String billNumber,
				String invoiceNumber, Timestamp billdate) {
			super();
			this.urn = urn;
			this.billNumber = billNumber;
			this.invoiceNumber = invoiceNumber;
			this.billdate = billdate;
		}

		public String getUrn() {
			return urn;
		}

		public void setUrn(String urn) {
			this.urn = urn;
		}

		public String getBillNumber() {
			return billNumber;
		}

		public void setBillNumber(String billNumber) {
			this.billNumber = billNumber;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public Timestamp getBilldate() {
			return billdate;
		}

		public void setBilldate(Timestamp billdate) {
			this.billdate = billdate;
		}

		public static RowMapper getRowMapper() {
            return new RowMapper() {

                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new billinvoiceupdInfo(
                            rs.getString("URN"),
                            rs.getString("BILL_NUMBER"),
                            rs.getString("INVOICE_NUMBER"),
                            rs.getTimestamp("BILL_DATE"));
                }
            };
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

   public static Object[] getDbName(){
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareCall(GET_DB_NAME);
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
   
   public static Object[] getBodySubject(String p_name){
        System.out.println("in getBodySubject "+p_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.prepareCall(GET_BODY_SUBJECT);
                if (statement != null) {
                    statement.setObject(1, p_name, OracleTypes.VARCHAR);
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
   
//    public static void main(String args[]) {
//        Object data[] = getUrnInfo("1002320");
//        System.out.println(data[0]);
//        System.out.println(data[1]);
//        System.out.println(data[2]);
//        System.out.println(data[3]);
//        System.out.println(data[4]);
//
//        data = getInvDetails(new BigDecimal(1));
//        System.out.println(data[0]);
//
//        System.out.println(getAllUrnNumber());
//        System.out.println(CanonCustomBillingServerUtil.first(getDbName()));
//
//    }
}
