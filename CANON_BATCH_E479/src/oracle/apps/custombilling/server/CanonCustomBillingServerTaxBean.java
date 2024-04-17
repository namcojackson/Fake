
/*
select sum(to_number(FIELD7)) stateTax,
          sum(to_number(FIELD8)) countyTax,
            sum(to_number(FIELD9)) cityTax           
           from CANON.CANON_E479_SMPL_INV_DTL_TBL
           where CONTROL_TYPE = 'DETAIL'
           and INV_ID =1
(
STATETAX NUMBER,
COUNTYTAX NUMBER,
CITYTAX NUMBER
)
*/
package oracle.apps.custombilling.server;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CanonCustomBillingServerTaxBean {
   private BigDecimal statetax;
   private BigDecimal countytax;
   private BigDecimal citytax;

    public CanonCustomBillingServerTaxBean(){
    }
    public CanonCustomBillingServerTaxBean(BigDecimal statetax, 
            BigDecimal countytax, 
            BigDecimal citytax){
        this.statetax=statetax;
        this.countytax=countytax;
        this.citytax=citytax;
    }
    public BigDecimal getStatetax() {
        return statetax;
    }
    public void setStatetax(BigDecimal statetax) {
        this.statetax=statetax;
    }
    public BigDecimal getCountytax() {
        return countytax;
    }
    public void setCountytax(BigDecimal countytax) {
        this.countytax=countytax;
    }
    public BigDecimal getCitytax() {
        return citytax;
    }
    public void setCitytax(BigDecimal citytax) {
        this.citytax=citytax;
    }
    public static CanonCustomBillingServerRowMapper getRowMapper(){
        return new CanonCustomBillingServerRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingServerTaxBean(
                    rs.getBigDecimal("STATETAX"),
                    rs.getBigDecimal("COUNTYTAX"),
                    rs.getBigDecimal("CITYTAX")
                );
            }
        };
    }
    public String toString() {
        return "CanonE479Tax{" + "statetax="+statetax+", countytax="+countytax+", citytax="+citytax+'}';
    }
}


