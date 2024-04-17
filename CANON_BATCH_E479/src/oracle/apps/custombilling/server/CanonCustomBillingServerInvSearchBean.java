

/*
select * from CANON_E479_INV_SRCH_TBL
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
PROFILE_NAME VARCHAR2,
CUSTOMER_NAME VARCHAR2,
BILL_LOCATION VARCHAR2,
BILL_NUMBER VARCHAR2,
BILL_DATE DATE,
INVOICE_BREAK VARCHAR2,
BILL_PERIOD VARCHAR2,
AMOUNT_DUE VARCHAR2,
CREATION_DATE DATE,
CREATED_BY NUMBER,
LAST_UPDATED_BY NUMBER,
LAST_UPDATE_DATE DATE,
INVOICE_PATH VARCHAR2,
PENDING_ACTION VARCHAR2
)
*/
package oracle.apps.custombilling.server;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CanonCustomBillingServerInvSearchBean {
   private BigDecimal invoiceId;
   private String urnNumber;
   private String invoiceType;
   private String invRegion;
   private String billerName;
   private String billerEmail;
   private String customerEmail;
   private String otherEmail;
   private String reviewRequired;
   private String profileName;
   private String customerName;
   private String billLocation;
   private String billNumber;
   private Timestamp billDate;
   private String invoiceBreak;
   private String billPeriod;
   private String amountDue;
   private Timestamp creationDate;
   private BigDecimal createdBy;
   private BigDecimal lastUpdatedBy;
   private Timestamp lastUpdateDate;
   private String invoicePath;
   private String pendingAction;
   private String company1;
   private String company2;
   private String phone;
   private String fax;
   private String negativeRead;
   private String highDollar;
   private String emailTextData;
   private String parentCustomerName;
   private String customerGroup;

    public CanonCustomBillingServerInvSearchBean(){
    }
    public CanonCustomBillingServerInvSearchBean(BigDecimal invoiceId, 
            String urnNumber, 
            String invoiceType, 
            String invRegion, 
            String billerName, 
            String billerEmail, 
            String customerEmail, 
            String otherEmail, 
            String reviewRequired, 
            String profileName,
            String parentCustomerName,
            String customerGroup,
            String customerName, 
            String billLocation, 
            String billNumber, 
            Timestamp billDate, 
            String invoiceBreak, 
            String billPeriod, 
            String amountDue, 
            Timestamp creationDate, 
            BigDecimal createdBy, 
            BigDecimal lastUpdatedBy, 
            Timestamp lastUpdateDate, 
            String invoicePath, 
            String pendingAction){
        this.invoiceId=invoiceId;
        this.urnNumber=urnNumber;
        this.invoiceType=invoiceType;
        this.invRegion=invRegion;
        this.billerName=billerName;
        this.billerEmail=billerEmail;
        this.customerEmail=customerEmail;
        this.otherEmail=otherEmail;
        this.reviewRequired=reviewRequired;
        this.profileName=profileName;
        this.parentCustomerName=parentCustomerName;
        this.customerGroup=customerGroup;
        this.customerName=customerName;
        this.billLocation=billLocation;
        this.billNumber=billNumber;
        this.billDate=billDate;
        this.invoiceBreak=invoiceBreak;
        this.billPeriod=billPeriod;
        this.amountDue=amountDue;
        this.creationDate=creationDate;
        this.createdBy=createdBy;
        this.lastUpdatedBy=lastUpdatedBy;
        this.lastUpdateDate=lastUpdateDate;
        this.invoicePath=invoicePath;
        this.pendingAction=pendingAction;
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
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName=profileName;
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
    public String getInvoicePath() {
        return invoicePath;
    }
    public void setInvoicePath(String invoicePath) {
        this.invoicePath=invoicePath;
    }
    public String getPendingAction() {
        return pendingAction;
    }
    public void setPendingAction(String pendingAction) {
        this.pendingAction=pendingAction;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNegativeRead() {
        return negativeRead;
    }

    public void setNegativeRead(String negativeRead) {
        this.negativeRead = negativeRead;
    }

    public String getHighDollar() {
        return highDollar;
    }

    public void setHighDollar(String highDollar) {
        this.highDollar = highDollar;
    }

    public String getEmailTextData() {
        return emailTextData;
    }

    public void setEmailTextData(String emailTextData) {
        this.emailTextData = emailTextData;
    }
    
    public String toString() {
        return "CanonCustomBillingServerInvSearchBean{" + "invoiceId=" + invoiceId + ", urnNumber=" + urnNumber + ", invoiceType=" + invoiceType + ", invRegion=" + invRegion + ", billerName=" + billerName + ", billerEmail=" + billerEmail + ", customerEmail=" + customerEmail + ", otherEmail=" + otherEmail + ", reviewRequired=" + reviewRequired + ", profileName=" + profileName + ", parentCustomerName=" + parentCustomerName +", customerGroup=" + customerGroup +", customerName=" + customerName + ", billLocation=" + billLocation + ", billNumber=" + billNumber + ", billDate=" + billDate + ", invoiceBreak=" + invoiceBreak + ", billPeriod=" + billPeriod + ", amountDue=" + amountDue + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", invoicePath=" + invoicePath + ", pendingAction=" + pendingAction + ", company1=" + company1 + ", company2=" + company2 + ", phone=" + phone + ", fax=" + fax + '}';
    }
    
}

