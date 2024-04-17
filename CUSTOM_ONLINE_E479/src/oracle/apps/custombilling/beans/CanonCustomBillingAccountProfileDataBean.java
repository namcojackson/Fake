/*
{call CANON_E479_TEMPLATE_PKG.GET_ACCOUNT_PROFILE_DATA('',1,100,1,'',?,?)}
(
PROFILE_ID NUMBER,
PROFILE_NUM VARCHAR2,
PROFILE_NAME VARCHAR2,
REPORTING_NAME VARCHAR2
)
*/
package oracle.apps.custombilling.beans;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import canon.apps.common.CanonRowMapper;
public class CanonCustomBillingAccountProfileDataBean {
   private BigDecimal profileId;
   private String profileNum;
   private String profileName;
   private String reportingName;

    public CanonCustomBillingAccountProfileDataBean(){
    }
    public CanonCustomBillingAccountProfileDataBean(BigDecimal profileId, 
            String profileNum, 
            String profileName, 
            String reportingName){
        this.profileId=profileId;
        this.profileNum=profileNum;
        this.profileName=profileName;
        this.reportingName=reportingName; 
    }
    public BigDecimal getProfileId() {
        return profileId;
    }
    public void setProfileId(BigDecimal profileId) { 
        this.profileId=profileId;
    }
    public String getProfileNum() {
        return profileNum;
    }
    public void setProfileNum(String profileNum) {
        this.profileNum=profileNum;
    }
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName=profileName;
    }
    public String getReportingName() {
        return reportingName;
    }
    public void setReportingName(String reportingName) {
        this.reportingName=reportingName;
    }
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingAccountProfileDataBean(
                    rs.getBigDecimal("PROFILE_ID"),
                    rs.getString("PROFILE_NUM"),
                    rs.getString("PROFILE_NAME"),
                    rs.getString("REPORTING_NAME")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingAccountProfileDataBean{" + "profileId="+profileId+", profileNum="+profileNum+", profileName="+profileName+", reportingName="+reportingName+'}';
    }
}

