package oracle.apps.custombilling.beans;
import java.sql.ResultSet;
import java.sql.SQLException;

import canon.apps.common.CanonRowMapper;
public class CanonCustomBillingCustomerNameBean {

   private String customerName;
   private String customerNumber;
   

    public CanonCustomBillingCustomerNameBean(){
    }
    public CanonCustomBillingCustomerNameBean(String customerName, 
            String customerNumber){
        this.customerName=customerName;
        this.customerNumber=customerNumber;
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
   
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingCustomerNameBean(
                    rs.getString("CUSTOMER_NAME"),
                    rs.getString("CUSTOMER_NUMBER")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingCustomerNameBean{" + "customerName="+customerName+", customerNumber="+customerNumber+'}';
    }
}

