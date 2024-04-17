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
public class CanonCustomBillingBillToSiteBean {
   private String customerName;
   private String address1;
   private String address2;
   private String city;
   private String state;
   private String postalCode;
   private String location;

    public CanonCustomBillingBillToSiteBean(){
    }
    public CanonCustomBillingBillToSiteBean(String customerName, 
            String address1, 
            String address2, 
            String city, 
            String state, 
            String postalCode, 
            String location){
        this.customerName=customerName;
        this.address1=address1;
        this.address2=address2;
        this.city=city;
        this.state=state;
        this.postalCode=postalCode;
        this.location=location;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName=customerName;
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1=address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2=address2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city=city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state=state;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode=postalCode;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location=location;
    }
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingBillToSiteBean(
                    rs.getString("CUSTOMER_NAME"),
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
        return "CanonCustomBillingBillToSiteBean{" + "customerName="+customerName+",  address1="+address1+", address2="+address2+", city="+city+", state="+state+", postalCode="+postalCode+", location="+location+'}';
    }
}

