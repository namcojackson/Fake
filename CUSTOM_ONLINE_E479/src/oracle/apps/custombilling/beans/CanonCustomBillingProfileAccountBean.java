/*
{call CANON_E479_TEMPLATE_PKG.GET_PROFILE_ACCOUNTS(21,?)}
(
PARTY_NAME VARCHAR2,
PARTY_SITE_NUMBER VARCHAR2,
SITE_USE_ID NUMBER,
LOCATION VARCHAR2,
ADDRESS1 VARCHAR2,
ADDRESS2 VARCHAR2,
CITY VARCHAR2,
STATE VARCHAR2,
POSTAL_CODE VARCHAR2
)
*/
package oracle.apps.custombilling.beans;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import canon.apps.common.CanonRowMapper;
public class CanonCustomBillingProfileAccountBean {
   private String partyName;
   private String partySiteNumber;
   private BigDecimal siteUseId;
   private String location;
   private String address1;
   private String address2;
   private String city;
   private String state;
   private String postalCode;

    public CanonCustomBillingProfileAccountBean(){
    }
    public CanonCustomBillingProfileAccountBean(String partyName, 
            String partySiteNumber, 
            BigDecimal siteUseId, 
            String location, 
            String address1, 
            String address2, 
            String city, 
            String state, 
            String postalCode){
        this.partyName=partyName;
        this.partySiteNumber=partySiteNumber;
        this.siteUseId=siteUseId;
        this.location=location;
        this.address1=address1;
        this.address2=address2;
        this.city=city;
        this.state=state;
        this.postalCode=postalCode;
    }
    public String getPartyName() {
        return partyName;
    }
    public void setPartyName(String partyName) {
        this.partyName=partyName;
    }
    public String getPartySiteNumber() {
        return partySiteNumber;
    }
    public void setPartySiteNumber(String partySiteNumber) {
        this.partySiteNumber=partySiteNumber;
    }
    public BigDecimal getSiteUseId() {
        return siteUseId;
    }
    public void setSiteUseId(BigDecimal siteUseId) {
        this.siteUseId=siteUseId;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location=location;
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
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingProfileAccountBean(
                    rs.getString("PARTY_NAME"),
                    rs.getString("PARTY_SITE_NUMBER"),
                    rs.getBigDecimal("SITE_USE_ID"),
                    rs.getString("LOCATION"),
                    rs.getString("ADDRESS1"),
                    rs.getString("ADDRESS2"),
                    rs.getString("CITY"),
                    rs.getString("STATE"),
                    rs.getString("POSTAL_CODE")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingProfileAccountBean{" + "partyName="+partyName+", partySiteNumber="+partySiteNumber+", siteUseId="+siteUseId+", location="+location+", address1="+address1+", address2="+address2+", city="+city+", state="+state+", postalCode="+postalCode+'}';
    }
}

