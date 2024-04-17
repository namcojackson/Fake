// Database name CANDEV
/*
{call CANON_E479_TEMPLATE_PKG.GET_TEMPLATE_INFO(7,?)}
(
HEADER_ID NUMBER,
PARENT_CUSTOMER_NAME VARCHAR2,
PARTY_NAME VARCHAR2,
BILL_TO_LOCATION VARCHAR2,
OTHER_EMAIL VARCHAR2,
CUSTOMER_EMAIL VARCHAR2,
INVOICE_BREAK VARCHAR2,
MULTI_TAB VARCHAR2,
WITHIN_TAB VARCHAR2,
STATUS_FLAG VARCHAR2,
CREATION_DATE DATE,
CREATED_BY VARCHAR2,
LAST_UPDATE_DATE DATE,
LAST_UPDATED_BY VARCHAR2,
INCLUDE_TXT_FORMAT VARCHAR2,
SUPPRESS_ZERO_INVOICE VARCHAR2
)
*/
package oracle.apps.custombilling.beans;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import canon.apps.common.CanonRowMapper;
public class CanonCustomBillingTemplateInfoBean {
   private BigDecimal headerId;
   private String parentCustomerName;
   private String customerName;
   private String billToLocation;
   private String otherEmail;
   private String customerEmail;
   private String invoiceBreak;
   private String multiTab;
   private String withinTab;
   private String statusFlag;
   private Timestamp creationDate;
   private String createdBy;
   private Timestamp lastUpdateDate;
   private String lastUpdatedBy;
   private String includeTxtFormat;
   private String suppressZeroInvoice;
   private String groupName;
   private String templateLevel;
   private String billerEmail;
   private String billerName;
   private String billerPhone;
   private String billerFax;
   

    public CanonCustomBillingTemplateInfoBean(){
    }
    public CanonCustomBillingTemplateInfoBean(BigDecimal headerId, 
            String parentCustomerName, 
            String customerName, 
            String billToLocation, 
            String otherEmail, 
            String customerEmail, 
            String invoiceBreak, 
            String multiTab, 
            String withinTab, 
            String statusFlag, 
            Timestamp creationDate, 
            String createdBy, 
            Timestamp lastUpdateDate, 
            String lastUpdatedBy, 
            String includeTxtFormat, 
            String suppressZeroInvoice,
            String templateLevel,
            String groupName){
        this.headerId=headerId;
        this.parentCustomerName=parentCustomerName;
        this.customerName=customerName;
        this.billToLocation=billToLocation;
        this.otherEmail=otherEmail;
        this.customerEmail=customerEmail;
        this.invoiceBreak=invoiceBreak;
        this.multiTab=multiTab;
        this.withinTab=withinTab;
        this.statusFlag=statusFlag;
        this.creationDate=creationDate;
        this.createdBy=createdBy;
        this.lastUpdateDate=lastUpdateDate;
        this.lastUpdatedBy=lastUpdatedBy;
        this.includeTxtFormat=includeTxtFormat;
        this.suppressZeroInvoice=suppressZeroInvoice;
        this.templateLevel=templateLevel;
        this.groupName=groupName;
    }
    public BigDecimal getHeaderId() {
        return headerId;
    }
    public void setHeaderId(BigDecimal headerId) {
        this.headerId=headerId;
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
    public String getBillToLocation() {
        return billToLocation;
    }
    public void setBillToLocation(String billToLocation) {
        this.billToLocation=billToLocation;
    }
    public String getOtherEmail() {
        return otherEmail;
    }
    public void setOtherEmail(String otherEmail) {
        this.otherEmail=otherEmail;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail=customerEmail;
    }
    public String getBillerEmail() {
        return billerEmail;
    }
    public void setBillerEmail(String billerEmail) {
        this.billerEmail=billerEmail;
    }
    public String getBillerName() {
        return billerName;
    }
    public void setBillerName(String billerName) {
        this.billerName=billerName;
    }
    public String getBillerPhone() {
        return billerPhone;
    }
    public void setBillerPhone(String billerPhone) {
        this.billerPhone=billerPhone;
    }
    public String getBillerFax() {
        return billerFax;
    }
    public void setBillerFax(String billerFax) {
        this.billerFax=billerFax;
    }
    public String getInvoiceBreak() {
        return invoiceBreak;
    }
    public void setInvoiceBreak(String invoiceBreak) {
        this.invoiceBreak=invoiceBreak;
    }
    public String getMultiTab() {
        return multiTab;
    }
    public void setMultiTab(String multiTab) {
        this.multiTab=multiTab;
    }
    public String getWithinTab() {
        return withinTab;
    }
    public void setWithinTab(String withinTab) {
        this.withinTab=withinTab;
    }
    public String getStatusFlag() {
        return statusFlag;
    }
    public void setStatusFlag(String statusFlag) {
        this.statusFlag=statusFlag;
    }
    public Timestamp getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate=creationDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy=createdBy;
    }
    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate=lastUpdateDate;
    }
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy=lastUpdatedBy;
    }
    public String getIncludeTxtFormat() {
        return includeTxtFormat;
    }
    public void setIncludeTxtFormat(String includeTxtFormat) {
        this.includeTxtFormat=includeTxtFormat;
    }
    public String getSuppressZeroInvoice() {
        return suppressZeroInvoice;
    }
    public void setSuppressZeroInvoice(String suppressZeroInvoice) {
        this.suppressZeroInvoice=suppressZeroInvoice;
    }
    public String getTemplateLevel() {
        return templateLevel;
    }
    public void setTemplateLevel(String templateLevel) {
        this.templateLevel=templateLevel;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName=groupName;
    }
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingTemplateInfoBean(
                    rs.getBigDecimal("HEADER_ID"),
                    rs.getString("PARENT_CUSTOMER_NAME"),
                    rs.getString("CUSTOMER_NAME"),
                    rs.getString("BILL_TO_LOCATION"),
                    rs.getString("OTHER_EMAIL"),
                    rs.getString("CUSTOMER_EMAIL"),
                    rs.getString("INVOICE_BREAK"),
                    rs.getString("MULTI_TAB"),
                    rs.getString("WITHIN_TAB"),
                    rs.getString("STATUS_FLAG"),
                    rs.getTimestamp("CREATION_DATE"),
                    rs.getString("CREATED_BY"),
                    rs.getTimestamp("LAST_UPDATE_DATE"),
                    rs.getString("LAST_UPDATED_BY"),
                    rs.getString("INCLUDE_TXT_FORMAT"),
                    rs.getString("SUPPRESS_ZERO_INVOICE"),
                    rs.getString("TEMPLATE_LEVEL"),
                    rs.getString("DS_ACCT_GRP_NM")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingTemplateInfoBean{" + "headerId="+headerId+", parentCustomer="+parentCustomerName+", customerName="+customerName+", billToLocation="+billToLocation+", otherEmail="+otherEmail+", customerEmail="+customerEmail+", invoiceBreak="+invoiceBreak+", multiTab="+multiTab+", withinTab="+withinTab+", statusFlag="+statusFlag+", creationDate="+creationDate+", createdBy="+createdBy+", lastUpdateDate="+lastUpdateDate+", lastUpdatedBy="+lastUpdatedBy+", includeTxtFormat="+includeTxtFormat+", suppressZeroInvoice="+suppressZeroInvoice+'}';
    }
}
