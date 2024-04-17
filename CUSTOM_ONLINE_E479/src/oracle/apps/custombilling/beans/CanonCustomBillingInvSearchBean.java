package oracle.apps.custombilling.beans;

/*
{call CANON_E479_INV_PKG.GET_INV_DETAILS_FOR_APPROVAL('1424961',?)}
(
INVOICE_ID NUMBER,
    URN_NUMBER VARCHAR2,
    INVOICE_TYPE VARCHAR2,
    INV_REGION VARCHAR2,
    BILLER_NAME VARCHAR2,
    BILLER_EMAIL VARCHAR2,
    CUSTOMER_EMAIL VARCHAR2,
    OTHER_EMAIL VARCHAR2,
    REVIEW_REQUIRED VARCHAR2,
    PARENT_CUSTOMER_NAME VARCHAR2,
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
    DS_ACCT_GRP_NM VARCHAR2,
    EMAILTEXT VARCHAR2
)
*/

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import canon.apps.common.CanonRowMapper;

public class CanonCustomBillingInvSearchBean {
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
       private String dsAcctGrpNm;
       private String emailtext;
    
    public CanonCustomBillingInvSearchBean(){
    }
    public CanonCustomBillingInvSearchBean(BigDecimal invoiceId, 
            String urnNumber, 
            String invoiceType, 
            String invRegion, 
            String billerName, 
            String billerEmail, 
            String customerEmail, 
            String otherEmail, 
            String reviewRequired, 
            String parentCustomerName, 
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
            String dsAcctGrpNm, 
            String emailtext){
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
        this.dsAcctGrpNm=dsAcctGrpNm;
        this.emailtext=emailtext;
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
    public String getBillerName() {
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
    public String getDsAcctGrpNm() {
        return dsAcctGrpNm;
    }
    public void setDsAcctGrpNm(String dsAcctGrpNm) {
        this.dsAcctGrpNm=dsAcctGrpNm;
    }
    public String getEmailtext() {
        return emailtext;
    }
    public void setEmailtext(String emailtext) {
        this.emailtext=emailtext;
    }
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingInvSearchBean(
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
                    rs.getString("PENDING_ACTION"),
                    rs.getString("COMMENTS"),
                    rs.getTimestamp("CREATION_DATE"),
                    rs.getBigDecimal("CREATED_BY"),
                    rs.getBigDecimal("LAST_UPDATED_BY"),
                    rs.getTimestamp("LAST_UPDATE_DATE"),
                    rs.getString("REMARKS"),
                    rs.getString("DS_ACCT_GRP_NM"),
                    rs.getString("EMAILTEXT")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingInvSearchBean{" + "invoiceId="+invoiceId+", urnNumber="+urnNumber+", invoiceType="+invoiceType+", invRegion="+invRegion+", billerName="+billerName+", billerEmail="+billerEmail+", customerEmail="+customerEmail+", otherEmail="+otherEmail+", reviewRequired="+reviewRequired+", parentCustomerName="+parentCustomerName+", customerName="+customerName+", billLocation="+billLocation+", billNumber="+billNumber+", billDate="+billDate+", invoiceBreak="+invoiceBreak+", billPeriod="+billPeriod+", amountDue="+amountDue+", invoicePath="+invoicePath+", serverUrl="+serverUrl+", invoiceFileName="+invoiceFileName+", pendingAction="+pendingAction+", comments="+comments+", creationDate="+creationDate+", createdBy="+createdBy+", lastUpdatedBy="+lastUpdatedBy+", lastUpdateDate="+lastUpdateDate+", remarks="+remarks+", dsAcctGrpNm="+dsAcctGrpNm+", emailtext="+emailtext+'}';
    }
}
