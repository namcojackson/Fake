/*
{call CANON_E479_TEMPLATE_PKG.GET_BILL_TO_SITES(Null,1,100,1,'PARTY_NAME',?,?)}
(
PARTY_NAME VARCHAR2,
SITE_USE_ID NUMBER,
ADDRESS1 VARCHAR2,
ADDRESS2 VARCHAR2,
CITY VARCHAR2,
STATE VARCHAR2,
POSTAL_CODE VARCHAR2,
LOCATION VARCHAR2
)
*/
package oracle.apps.custombilling.beans;
import java.sql.ResultSet;
import java.sql.SQLException;

import canon.apps.common.CanonRowMapper;
public class CanonCustomBillingCustomerGroupBean {
   private String customerGroup;

    public CanonCustomBillingCustomerGroupBean(){
    }
    public CanonCustomBillingCustomerGroupBean(String customerGroup 
     ){
        this.customerGroup=customerGroup;
     
    }
    public String getCustomerGroup() {
        return customerGroup;
    }
    public void setCustomerGroup(String customerGroup) {
        this.customerGroup=customerGroup;
    }

  
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingCustomerGroupBean(
                    rs.getString("GROUP_NAME")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingCustomerGroupBean{" + "customerGroup="+customerGroup+'}';
    }
}

