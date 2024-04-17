/*
{call CANON_E479_TEMPLATE_PKG.GET_COLUMN_DETAILS(8,26,?)}
(
HEADER_ID NUMBER,
VIEW_ID NUMBER,
COLUMN_ID NUMBER,
COLUMN_TYPE VARCHAR2,
COLUMN_NAME VARCHAR2,
COLUMN_ALIAS VARCHAR2,
COLUMN_POSITION NUMBER,
AGGREGATE_BY VARCHAR2,
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
public class CanonCustomBillingColumnDetailBean {
   private BigDecimal headerId;
   private BigDecimal viewId;
   private BigDecimal columnId;
   private String columnType;
   private String columnName;
   private String columnAlias;
   private BigDecimal columnPosition;
   private String aggregateBy;
   private Timestamp creationDate;
   private String createdBy;
   private Timestamp lastUpdateDate;
   private String lastUpdatedBy;

    public CanonCustomBillingColumnDetailBean(){
    }
    public CanonCustomBillingColumnDetailBean(BigDecimal headerId, 
            BigDecimal viewId, 
            BigDecimal columnId, 
            String columnType, 
            String columnName, 
            String columnAlias, 
            BigDecimal columnPosition, 
            String aggregateBy, 
            Timestamp creationDate, 
            String createdBy, 
            Timestamp lastUpdateDate, 
            String lastUpdatedBy){
        this.headerId=headerId;
        this.viewId=viewId;
        this.columnId=columnId;
        this.columnType=columnType;
        this.columnName=columnName;
        this.columnAlias=columnAlias;
        this.columnPosition=columnPosition;
        this.aggregateBy=aggregateBy;
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
    public BigDecimal getColumnId() {
        return columnId;
    }
    public void setColumnId(BigDecimal columnId) {
        this.columnId=columnId;
    }
    public String getColumnType() {
        return columnType;
    }
    public void setColumnType(String columnType) {
        this.columnType=columnType;
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName=columnName;
    }
    public String getColumnAlias() {
        return columnAlias;
    }
    public void setColumnAlias(String columnAlias) {
        this.columnAlias=columnAlias;
    }
    public BigDecimal getColumnPosition() {
        return columnPosition;
    }
    public void setColumnPosition(BigDecimal columnPosition) {
        this.columnPosition=columnPosition;
    }
    public String getAggregateBy() {
        return aggregateBy;
    }
    public void setAggregateBy(String aggregateBy) {
        this.aggregateBy=aggregateBy;
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
                return new CanonCustomBillingColumnDetailBean(
                    rs.getBigDecimal("HEADER_ID"),
                    rs.getBigDecimal("VIEW_ID"),
                    rs.getBigDecimal("COLUMN_ID"),
                    rs.getString("COLUMN_TYPE"),
                    rs.getString("COLUMN_NAME"),
                    rs.getString("COLUMN_ALIAS"),
                    rs.getBigDecimal("COLUMN_POSITION"),
                    rs.getString("AGGREGATE_BY"),
                    rs.getTimestamp("CREATION_DATE"),
                    rs.getString("CREATED_BY"),
                    rs.getTimestamp("LAST_UPDATE_DATE"),
                    rs.getString("LAST_UPDATED_BY")
                );
            }
        };
    }
    public String toString() {
        return "CanonCustomBillingColumnDetailBean{" + "headerId="+headerId+", viewId="+viewId+", columnId="+columnId+", columnType="+columnType+", columnName="+columnName+", columnAlias="+columnAlias+", columnPosition="+columnPosition+", aggregateBy="+aggregateBy+", creationDate="+creationDate+", createdBy="+createdBy+", lastUpdateDate="+lastUpdateDate+", lastUpdatedBy="+lastUpdatedBy+'}';
    }
}

