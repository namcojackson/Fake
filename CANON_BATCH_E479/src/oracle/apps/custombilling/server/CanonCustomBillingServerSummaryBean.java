
/*
select FIELD1 orderTYpe, FIELD2 productType, count(to_number(FIELD3)) baseCount,sum(to_number(FIELD3)) baseCharge,
           count(to_number(FIELD4)) serviceCount, sum(to_number(FIELD4)) serviceCharge,
           count(to_number(FIELD5)) attachCount, sum(to_number(FIELD5)) attachCharge, 
           count(to_number(FIELD6)) usageCount, sum(to_number(FIELD6))  usageCharge
           from CANON.CANON_E479_SMPL_INV_DTL_TBL
           where CONTROL_TYPE = 'DETAIL'
           and INV_ID =1
           group by   FIELD1, FIELD2
           order by 1,2
(
ORDERTYPE VARCHAR2,
PRODUCTTYPE VARCHAR2,
BASECOUNT NUMBER,
BASECHARGE NUMBER,
SERVICECOUNT NUMBER,
SERVICECHARGE NUMBER,
ATTACHCOUNT NUMBER,
ATTACHCHARGE NUMBER,
USAGECOUNT NUMBER,
USAGECHARGE NUMBER
)
*/
package oracle.apps.custombilling.server;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CanonCustomBillingServerSummaryBean {
   private String ordertype;
   private String producttype;
   private BigDecimal basecount;
   private BigDecimal basecharge;
   private BigDecimal servicecount;
   private BigDecimal servicecharge;
   private BigDecimal attachcount;
   private BigDecimal attachcharge;
   private BigDecimal usagecount;
   private BigDecimal usagecharge;

    public CanonCustomBillingServerSummaryBean(){
    }
    public CanonCustomBillingServerSummaryBean(String ordertype, 
            String producttype, 
            BigDecimal basecount, 
            BigDecimal basecharge, 
            BigDecimal servicecount, 
            BigDecimal servicecharge, 
            BigDecimal attachcount, 
            BigDecimal attachcharge, 
            BigDecimal usagecount, 
            BigDecimal usagecharge){
        this.ordertype=ordertype;
        this.producttype=producttype;
        this.basecount=basecount;
        this.basecharge=basecharge;
        this.servicecount=servicecount;
        this.servicecharge=servicecharge;
        this.attachcount=attachcount;
        this.attachcharge=attachcharge;
        this.usagecount=usagecount;
        this.usagecharge=usagecharge;
    }
    public String getOrdertype() {
        return ordertype;
    }
    public void setOrdertype(String ordertype) {
        this.ordertype=ordertype;
    }
    public String getProducttype() {
        return producttype;
    }
    public void setProducttype(String producttype) {
        this.producttype=producttype;
    }
    public BigDecimal getBasecount() {
        return basecount;
    }
    public void setBasecount(BigDecimal basecount) {
        this.basecount=basecount;
    }
    public BigDecimal getBasecharge() {
        return basecharge;
    }
    public void setBasecharge(BigDecimal basecharge) {
        this.basecharge=basecharge;
    }
    public BigDecimal getServicecount() {
        return servicecount;
    }
    public void setServicecount(BigDecimal servicecount) {
        this.servicecount=servicecount;
    }
    public BigDecimal getServicecharge() {
        return servicecharge;
    }
    public void setServicecharge(BigDecimal servicecharge) {
        this.servicecharge=servicecharge;
    }
    public BigDecimal getAttachcount() {
        return attachcount;
    }
    public void setAttachcount(BigDecimal attachcount) {
        this.attachcount=attachcount;
    }
    public BigDecimal getAttachcharge() {
        return attachcharge;
    }
    public void setAttachcharge(BigDecimal attachcharge) {
        this.attachcharge=attachcharge;
    }
    public BigDecimal getUsagecount() {
        return usagecount;
    }
    public void setUsagecount(BigDecimal usagecount) {
        this.usagecount=usagecount;
    }
    public BigDecimal getUsagecharge() {
        return usagecharge;
    }
    public void setUsagecharge(BigDecimal usagecharge) {
        this.usagecharge=usagecharge;
    }
    public static CanonCustomBillingServerRowMapper getRowMapper(){
        return new CanonCustomBillingServerRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingServerSummaryBean(
                    rs.getString("ORDERTYPE"),
                    rs.getString("PRODUCTTYPE"),
                    rs.getBigDecimal("BASECOUNT"),
                    rs.getBigDecimal("BASECHARGE"),
                    rs.getBigDecimal("SERVICECOUNT"),
                    rs.getBigDecimal("SERVICECHARGE"),
                    rs.getBigDecimal("ATTACHCOUNT"),
                    rs.getBigDecimal("ATTACHCHARGE"),
                    rs.getBigDecimal("USAGECOUNT"),
                    rs.getBigDecimal("USAGECHARGE")
                );
            }
        };
    }
    public String toString() {
        return "CanonE479Summary{" + "ordertype="+ordertype+", producttype="+producttype+", basecount="+basecount+", basecharge="+basecharge+", servicecount="+servicecount+", servicecharge="+servicecharge+", attachcount="+attachcount+", attachcharge="+attachcharge+", usagecount="+usagecount+", usagecharge="+usagecharge+'}';
    }
}


