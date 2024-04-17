/*
{call CANON_E479_TEMPLATE_PKG.GET_PARTY_NAMES(Null,1,100,1,'PARTY_NAME',?,?)}
(
PARTY_NAME VARCHAR2,
PARTY_NUMBER VARCHAR2,
ACCOUNT_NUMBER VARCHAR2
)
*/
package oracle.apps.custombilling.beans;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CanonCustomBillingPartyNameBean {

   private String customerName;
   private String customerNumber;
   

    public CanonCustomBillingPartyNameBean(){
    }
    public CanonCustomBillingPartyNameBean(String customerName, 
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
   
    public static oracle.apps.bsd.CanonBSDRowMapper getRowMapper(){
        return new oracle.apps.bsd.CanonBSDRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingPartyNameBean(
                    rs.getString("CUSTOMER_NAME"),
                    rs.getString("CUSTOMER_NUMBER")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingPartyNameBean{" + "customerName="+customerName+", customerNumber="+customerNumber+'}';
    }
}

