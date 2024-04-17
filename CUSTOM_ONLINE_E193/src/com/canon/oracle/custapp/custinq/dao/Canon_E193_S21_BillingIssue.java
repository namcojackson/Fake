package com.canon.oracle.custapp.custinq.dao;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;
import canon.apps.common.CanonRowMapper;

    public class Canon_E193_S21_BillingIssue {

   public static final String UPDATE_TICKET= "{call CANON_E193_CS_EVOLUTION_PKG_V1.UPDATE_TICKET(?,?,?,?,?,?,?,?)}";
   public static final String CREATE_BILL_TICKET= "{call CANON_E193_CS_EVOLUTION_PKG_V1.CREATE_BILL_TICKET(?,?,?,?,?,?,?,?,?)}";

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

   public Canon_E193_S21_BillingIssue(){
   }

   public static Object[] updateTicket(String p_ticket_number,
    String p_line_number,
    String p_status,
    String p_jtf_notes_detail,
    String p_updated_by){
        System.out.println("in updateTicket "+p_ticket_number+"|"+p_line_number+"|"+p_status+"|"+p_updated_by);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPDATE_TICKET);
                if (statement != null) {
                    statement.setObject(1, p_ticket_number, OracleTypes.VARCHAR);
                    statement.setObject(2, p_line_number, OracleTypes.VARCHAR);
                    statement.setObject(3, p_status, OracleTypes.VARCHAR);
                    statement.setObject(4, p_jtf_notes_detail, OracleTypes.VARCHAR);
                    statement.setObject(5, p_updated_by, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.NUMBER);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(6)
                        ,statement.getObject(7)
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

   public static Object[] createBillTicket(List p_hdr_tbl,
    List p_line_tbl,
    List p_subline_tbl,
    String p_jtf_notes_detail){
        System.out.println("in createBillTicket "+p_hdr_tbl+"|"+p_line_tbl+"|"+p_subline_tbl);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CREATE_BILL_TICKET);
                if (statement != null) {
                    statement.setObject(1, canonE193S21HdrTblListToArray(p_hdr_tbl, connection), OracleTypes.ARRAY);
                    statement.setObject(2, canonE193S21LineTblListToArray(p_line_tbl, connection), OracleTypes.ARRAY);
                    statement.setObject(3, canonE193S21SublineTblListToArray(p_subline_tbl, connection), OracleTypes.ARRAY);
                    statement.setObject(4, p_jtf_notes_detail, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.NUMBER);
                    statement.registerOutParameter(6, OracleTypes.NUMBER);
                    statement.registerOutParameter(7, OracleTypes.NUMBER);
                    statement.registerOutParameter(8, OracleTypes.NUMBER);
                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(5)
                        ,statement.getObject(6)
                        ,statement.getObject(7)
                        ,statement.getObject(8)
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


    static oracle.sql.ARRAY canonE193S21SublineTblListToArray(List list, Connection connection) throws SQLException{
        ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("APPS.CANON_E193_S21_SUBLINE_TBL", connection);
        CanonE193S21SublineRec[] elements = (CanonE193S21SublineRec[]) list.toArray(new CanonE193S21SublineRec[0]);
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection, elements);
        return array;
    }

    static List arrayToCanonE193S21SublineTblList(Array a, Connection connection) throws SQLException{
        List list=new ArrayList();
        connection.getTypeMap().put("CANON.CANON_E193_S21_SUBLINE_REC", CanonE193S21SublineRec.class);
        Object[] oo=(Object[])a.getArray();
        for (int i=0;i<oo.length;i++){
            list.add(oo[i]);
        }
        return list;
    }

    static oracle.sql.ARRAY canonE193S21HdrTblListToArray(List list, Connection connection) throws SQLException{
        ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("APPS.CANON_E193_S21_HDR_TBL", connection);
        CanonE193S21HdrRec[] elements = (CanonE193S21HdrRec[]) list.toArray(new CanonE193S21HdrRec[0]);
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection, elements);
        return array;
    }

    static List arrayToCanonE193S21HdrTblList(Array a, Connection connection) throws SQLException{
        List list=new ArrayList();
        connection.getTypeMap().put("CANON.CANON_E193_S21_HDR_REC", CanonE193S21HdrRec.class);
        Object[] oo=(Object[])a.getArray();
        for (int i=0;i<oo.length;i++){
            list.add(oo[i]);
        }
        return list;
    }

    static oracle.sql.ARRAY canonE193S21LineTblListToArray(List list, Connection connection) throws SQLException{
        ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("APPS.CANON_E193_S21_LINE_TBL", connection);
        CanonE193S21LineRec[] elements = (CanonE193S21LineRec[]) list.toArray(new CanonE193S21LineRec[0]);
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection, elements);
        return array;
    }

    static List arrayToCanonE193S21LineTblList(Array a, Connection connection) throws SQLException{
        List list=new ArrayList();
        connection.getTypeMap().put("CANON.CANON_E193_S21_LINE_REC", CanonE193S21LineRec.class);
        Object[] oo=(Object[])a.getArray();
        for (int i=0;i<oo.length;i++){
            list.add(oo[i]);
        }
        return list;
    }


    public static class CanonE193S21SublineRec implements java.sql.SQLData, java.io.Serializable  {
       private String crFlag;
       private String creditReason;
       private String serialNum;
       private String objectType;
       private String objectValue;
       private String currentValue;
       private String newValue;
       private String attribute1;
       private String attribute2;
       private String attribute3;
       private String invoiceNumber;
       private String instanceNumber;
       private String catDesc;

        public CanonE193S21SublineRec(){
        }
        public CanonE193S21SublineRec(String crFlag, 
                String creditReason, 
                String serialNum, 
                String objectType, 
                String objectValue, 
                String currentValue, 
                String newValue, 
                String attribute1, 
                String attribute2, 
                String attribute3, 
                String invoiceNumber, 
                String instanceNumber, 
                String catDesc){
            this.crFlag=crFlag;
            this.creditReason=creditReason;
            this.serialNum=serialNum;
            this.objectType=objectType;
            this.objectValue=objectValue;
            this.currentValue=currentValue;
            this.newValue=newValue;
            this.attribute1=attribute1;
            this.attribute2=attribute2;
            this.attribute3=attribute3;
            this.invoiceNumber=invoiceNumber;
            this.instanceNumber=instanceNumber;
            this.catDesc=catDesc;
        }
        public String getCrFlag() {
            return crFlag;
        }
        public void setCrFlag(String crFlag) {
            this.crFlag=crFlag;
        }
        public String getCreditReason() {
            return creditReason;
        }
        public void setCreditReason(String creditReason) {
            this.creditReason=creditReason;
        }
        public String getSerialNum() {
            return serialNum;
        }
        public void setSerialNum(String serialNum) {
            this.serialNum=serialNum;
        }
        public String getObjectType() {
            return objectType;
        }
        public void setObjectType(String objectType) {
            this.objectType=objectType;
        }
        public String getObjectValue() {
            return objectValue;
        }
        public void setObjectValue(String objectValue) {
            this.objectValue=objectValue;
        }
        public String getCurrentValue() {
            return currentValue;
        }
        public void setCurrentValue(String currentValue) {
            this.currentValue=currentValue;
        }
        public String getNewValue() {
            return newValue;
        }
        public void setNewValue(String newValue) {
            this.newValue=newValue;
        }
        public String getAttribute1() {
            return attribute1;
        }
        public void setAttribute1(String attribute1) {
            this.attribute1=attribute1;
        }
        public String getAttribute2() {
            return attribute2;
        }
        public void setAttribute2(String attribute2) {
            this.attribute2=attribute2;
        }
        public String getAttribute3() {
            return attribute3;
        }
        public void setAttribute3(String attribute3) {
            this.attribute3=attribute3;
        }
        public String getInvoiceNumber() {
            return invoiceNumber;
        }
        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber=invoiceNumber;
        }
        public String getInstanceNumber() {
            return instanceNumber;
        }
        public void setInstanceNumber(String instanceNumber) {
            this.instanceNumber=instanceNumber;
        }
        public String getCatDesc() {
            return catDesc;
        }
        public void setCatDesc(String catDesc) {
            this.catDesc=catDesc;
        }

        public String getSQLTypeName() throws SQLException {
            return "APPS.CANON_E193_S21_SUBLINE_REC";
        }

        public void readSQL(SQLInput stream, String typeName) 
        		throws SQLException {
               crFlag = stream.readString();
               creditReason = stream.readString();
               serialNum = stream.readString();
               objectType = stream.readString();
               objectValue = stream.readString();
               currentValue = stream.readString();
               newValue = stream.readString();
               attribute1 = stream.readString();
               attribute2 = stream.readString();
               attribute3 = stream.readString();
               invoiceNumber = stream.readString();
               instanceNumber = stream.readString();
               catDesc = stream.readString();

        }

        public void writeSQL(SQLOutput stream) throws SQLException {
               stream.writeString(crFlag);
               stream.writeString(creditReason);
               stream.writeString(serialNum);
               stream.writeString(objectType);
               stream.writeString(objectValue);
               stream.writeString(currentValue);
               stream.writeString(newValue);
               stream.writeString(attribute1);
               stream.writeString(attribute2);
               stream.writeString(attribute3);
               stream.writeString(invoiceNumber);
               stream.writeString(instanceNumber);
               stream.writeString(catDesc);

        }

        public String toString() {
            return "CanonE193S21SublineRec{" + "crFlag="+crFlag+", creditReason="+creditReason+", serialNum="+serialNum+", objectType="+objectType+", objectValue="+objectValue+", currentValue="+currentValue+", newValue="+newValue+", attribute1="+attribute1+", attribute2="+attribute2+", attribute3="+attribute3+", invoiceNumber="+invoiceNumber+", instanceNumber="+instanceNumber+", catDesc="+catDesc+'}';
        }
    }
     public static class CanonE193S21LineRec implements java.sql.SQLData, java.io.Serializable  {
       private String severity;
       private String reasonCode;
       private String reason;
       private String createdBy;
       private String catDesc;

        public CanonE193S21LineRec(){
        }
        public CanonE193S21LineRec(String severity, 
                String reasonCode, 
                String reason, 
                String createdBy, 
                String catDesc){
            this.severity=severity;
            this.reasonCode=reasonCode;
            this.reason=reason;
            this.createdBy=createdBy;
            this.catDesc=catDesc;
        }
        public String getSeverity() {
            return severity;
        }
        public void setSeverity(String severity) {
            this.severity=severity;
        }
        public String getReasonCode() {
            return reasonCode;
        }
        public void setReasonCode(String reasonCode) {
            this.reasonCode=reasonCode;
        }
        public String getReason() {
            return reason;
        }
        public void setReason(String reason) {
            this.reason=reason;
        }
        public String getCreatedBy() {
            return createdBy;
        }
        public void setCreatedBy(String createdBy) {
            this.createdBy=createdBy;
        }
        public String getCatDesc() {
            return catDesc;
        }
        public void setCatDesc(String catDesc) {
            this.catDesc=catDesc;
        }

        public String getSQLTypeName() throws SQLException {
            return "APPS.CANON_E193_S21_LINE_REC";
        }

        public void readSQL(SQLInput stream, String typeName) throws SQLException {
               severity = stream.readString();
               reasonCode = stream.readString();
               reason = stream.readString();
               createdBy = stream.readString();
               catDesc = stream.readString();

        }

        public void writeSQL(SQLOutput stream) throws SQLException {
               stream.writeString(severity);
               stream.writeString(reasonCode);
               stream.writeString(reason);
               stream.writeString(createdBy);
               stream.writeString(catDesc);

        }

        public String toString() {
            return "CanonE193S21LineRec{" + "severity="+severity+", reasonCode="+reasonCode+", reason="+reason+", createdBy="+createdBy+", catDesc="+catDesc+'}';
        }
    }
     public static class CanonE193S21HdrRec implements java.sql.SQLData, java.io.Serializable  {
       private String billingIssue;
       private String customerName;
       private String customerNumber;
       private String invoiceNumber;
       private String contractNumber;
       private BigDecimal orderNumber;
       private String orderType;
       private String origName;
       private String origPhNumber;
       private String origeMail;
       private String origType;
       private String custContact;
       private String custPhNumber;
       private String custeMail;
       private String region;
       private String source;
       private String createdBy;
       private String catDesc;

        public CanonE193S21HdrRec(){
        }
        public CanonE193S21HdrRec(String billingIssue, 
                String customerName, 
                String customerNumber, 
                String invoiceNumber, 
                String contractNumber, 
                BigDecimal orderNumber, 
                String orderType, 
                String origName, 
                String origPhNumber, 
                String origeMail, 
                String origType, 
                String custContact, 
                String custPhNumber, 
                String custeMail, 
                String region, 
                String source, 
                String createdBy, 
                String catDesc){
            this.billingIssue=billingIssue;
            this.customerName=customerName;
            this.customerNumber=customerNumber;
            this.invoiceNumber=invoiceNumber;
            this.contractNumber=contractNumber;
            this.orderNumber=orderNumber;
            this.orderType=orderType;
            this.origName=origName;
            this.origPhNumber=origPhNumber;
            this.origeMail=origeMail;
            this.origType=origType;
            this.custContact=custContact;
            this.custPhNumber=custPhNumber;
            this.custeMail=custeMail;
            this.region=region;
            this.source=source;
            this.createdBy=createdBy;
            this.catDesc=catDesc;
        }
        public String getBillingIssue() {
            return billingIssue;
        }
        public void setBillingIssue(String billingIssue) {
            this.billingIssue=billingIssue;
        }
        public String getCustomerName() {
            return customerName;
        }
        public void setCustomerName(String customerName) {
            this.customerName=customerName;
        }
        public String getCustomerNumber() {
            return customerNumber;
        }
        public void setCustomerNumber(String customerNumber) {
            this.customerNumber=customerNumber;
        }
        public String getInvoiceNumber() {
            return invoiceNumber;
        }
        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber=invoiceNumber;
        }
        public String getContractNumber() {
            return contractNumber;
        }
        public void setContractNumber(String contractNumber) {
            this.contractNumber=contractNumber;
        }
        public BigDecimal getOrderNumber() {
            return orderNumber;
        }
        public void setOrderNumber(BigDecimal orderNumber) {
            this.orderNumber=orderNumber;
        }
        public String getOrderType() {
            return orderType;
        }
        public void setOrderType(String orderType) {
            this.orderType=orderType;
        }
        public String getOrigName() {
            return origName;
        }
        public void setOrigName(String origName) {
            this.origName=origName;
        }
        public String getOrigPhNumber() {
            return origPhNumber;
        }
        public void setOrigPhNumber(String origPhNumber) {
            this.origPhNumber=origPhNumber;
        }
        public String getOrigeMail() {
            return origeMail;
        }
        public void setOrigeMail(String origeMail) {
            this.origeMail=origeMail;
        }
        public String getOrigType() {
            return origType;
        }
        public void setOrigType(String origType) {
            this.origType=origType;
        }
        public String getCustContact() {
            return custContact;
        }
        public void setCustContact(String custContact) {
            this.custContact=custContact;
        }
        public String getCustPhNumber() {
            return custPhNumber;
        }
        public void setCustPhNumber(String custPhNumber) {
            this.custPhNumber=custPhNumber;
        }
        public String getCusteMail() {
            return custeMail;
        }
        public void setCusteMail(String custeMail) {
            this.custeMail=custeMail;
        }
        public String getRegion() {
            return region;
        }
        public void setRegion(String region) {
            this.region=region;
        }
        public String getSource() {
            return source;
        }
        public void setSource(String source) {
            this.source=source;
        }
        public String getCreatedBy() {
            return createdBy;
        }
        public void setCreatedBy(String createdBy) {
            this.createdBy=createdBy;
        }
        public String getCatDesc() {
            return catDesc;
        }
        public void setCatDesc(String catDesc) {
            this.catDesc=catDesc;
        }

        public String getSQLTypeName() throws SQLException {
            return "APPS.CANON_E193_S21_HDR_REC";
        }

        public void readSQL(SQLInput stream, String typeName) throws SQLException {
               billingIssue = stream.readString();
               customerName = stream.readString();
               customerNumber = stream.readString();
               invoiceNumber = stream.readString();
               contractNumber = stream.readString();
               orderNumber = stream.readBigDecimal();
               orderType = stream.readString();
               origName = stream.readString();
               origPhNumber = stream.readString();
               origeMail = stream.readString();
               origType = stream.readString();
               custContact = stream.readString();
               custPhNumber = stream.readString();
               custeMail = stream.readString();
               region = stream.readString();
               source = stream.readString();
               createdBy = stream.readString();
               catDesc = stream.readString();

        }

        public void writeSQL(SQLOutput stream) throws SQLException {
               stream.writeString(billingIssue);
               stream.writeString(customerName);
               stream.writeString(customerNumber);
               stream.writeString(invoiceNumber);
               stream.writeString(contractNumber);
               stream.writeBigDecimal(orderNumber);
               stream.writeString(orderType);
               stream.writeString(origName);
               stream.writeString(origPhNumber);
               stream.writeString(origeMail);
               stream.writeString(origType);
               stream.writeString(custContact);
               stream.writeString(custPhNumber);
               stream.writeString(custeMail);
               stream.writeString(region);
               stream.writeString(source);
               stream.writeString(createdBy);
               stream.writeString(catDesc);

        }

        public String toString() {
            return "CanonE193S21HdrRec{" + "billingIssue="+billingIssue+", customerName="+customerName+", customerNumber="+customerNumber+", invoiceNumber="+invoiceNumber+", contractNumber="+contractNumber+", orderNumber="+orderNumber+", orderType="+orderType+", origName="+origName+", origPhNumber="+origPhNumber+", origeMail="+origeMail+", origType="+origType+", custContact="+custContact+", custPhNumber="+custPhNumber+", custeMail="+custeMail+", region="+region+", source="+source+", createdBy="+createdBy+", catDesc="+catDesc+'}';
        }
    }
 
}

