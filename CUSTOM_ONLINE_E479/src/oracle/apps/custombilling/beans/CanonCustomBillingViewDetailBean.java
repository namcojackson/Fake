/*
{call CANON_E479_TEMPLATE_PKG.GET_VIEW_DETAILS(8,?)}
(
HEADER_ID NUMBER,
VIEW_ID NUMBER,
VIEW_NAME VARCHAR2,
VIEW_ALIAS VARCHAR2,
VIEW_SEQUENCE NUMBER,
SORT_PREF_COL1 VARCHAR2,
SORT_PREF_COL2 VARCHAR2,
SORT_PREF_COL3 VARCHAR2,
CREATION_DATE DATE,
CREATED_BY VARCHAR2,
LAST_UPDATE_DATE DATE,
LAST_UPDATED_BY VARCHAR2
)
*/
package oracle.apps.custombilling.beans;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import canon.apps.common.CanonRowMapper;
public class CanonCustomBillingViewDetailBean {
   private BigDecimal headerId;
   private BigDecimal viewId;
   private String viewName;
   private String viewAlias;
   private BigDecimal viewSequence;
   private String sortPrefCol1;
   private String sortPrefCol2;
   private String sortPrefCol3;
   private Timestamp creationDate;
   private String createdBy;
   private Timestamp lastUpdateDate;
   private String lastUpdatedBy;

    public CanonCustomBillingViewDetailBean(){
    }
    public CanonCustomBillingViewDetailBean(BigDecimal headerId, 
            BigDecimal viewId, 
            String viewName, 
            String viewAlias, 
            BigDecimal viewSequence, 
            String sortPrefCol1, 
            String sortPrefCol2, 
            String sortPrefCol3, 
            Timestamp creationDate, 
            String createdBy, 
            Timestamp lastUpdateDate, 
            String lastUpdatedBy){
        this.headerId=headerId;
        this.viewId=viewId;
        this.viewName=viewName;
        this.viewAlias=viewAlias;
        this.viewSequence=viewSequence;
        this.sortPrefCol1=sortPrefCol1;
        this.sortPrefCol2=sortPrefCol2;
        this.sortPrefCol3=sortPrefCol3;
        this.creationDate=creationDate;
        this.createdBy=createdBy;
        this.lastUpdateDate=lastUpdateDate;
        this.lastUpdatedBy=lastUpdatedBy;
    }
    public BigDecimal getHeaderId() {
        return headerId;
    }
    public void setHeaderId(BigDecimal headerId) {
        this.headerId=headerId;
    }
    public BigDecimal getViewId() {
        return viewId;
    }
    public void setViewId(BigDecimal viewId) {
        this.viewId=viewId;
    }
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName=viewName;
    }
    public String getViewAlias() {
        return viewAlias;
    }
    public void setViewAlias(String viewAlias) {
        this.viewAlias=viewAlias;
    }
    public BigDecimal getViewSequence() {
        return viewSequence;
    }
    public void setViewSequence(BigDecimal viewSequence) {
        this.viewSequence=viewSequence;
    }
    public String getSortPrefCol1() {
        return sortPrefCol1;
    }
    public void setSortPrefCol1(String sortPrefCol1) {
        this.sortPrefCol1=sortPrefCol1;
    }
    public String getSortPrefCol2() {
        return sortPrefCol2;
    }
    public void setSortPrefCol2(String sortPrefCol2) {
        this.sortPrefCol2=sortPrefCol2;
    }
    public String getSortPrefCol3() {
        return sortPrefCol3;
    }
    public void setSortPrefCol3(String sortPrefCol3) {
        this.sortPrefCol3=sortPrefCol3;
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
    public static CanonRowMapper getRowMapper(){
        return new CanonRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingViewDetailBean(
                    rs.getBigDecimal("HEADER_ID"),
                    rs.getBigDecimal("VIEW_ID"),
                    rs.getString("VIEW_NAME"),
                    rs.getString("VIEW_ALIAS"),
                    rs.getBigDecimal("VIEW_SEQUENCE"),
                    rs.getString("SORT_PREF_COL1"),
                    rs.getString("SORT_PREF_COL2"),
                    rs.getString("SORT_PREF_COL3"),
                    rs.getTimestamp("CREATION_DATE"),
                    rs.getString("CREATED_BY"),
                    rs.getTimestamp("LAST_UPDATE_DATE"),
                    rs.getString("LAST_UPDATED_BY")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingViewDetailBean{" + "headerId="+headerId+", viewId="+viewId+", viewName="+viewName+", viewAlias="+viewAlias+", viewSequence="+viewSequence+", sortPrefCol1="+sortPrefCol1+", sortPrefCol2="+sortPrefCol2+", sortPrefCol3="+sortPrefCol3+", creationDate="+creationDate+", createdBy="+createdBy+", lastUpdateDate="+lastUpdateDate+", lastUpdatedBy="+lastUpdatedBy+'}';
    }
}

