package oracle.apps.custombilling.server;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CanonCustomBillingServerRowMapper {
    Object mapRow(ResultSet rs,int rowNum)throws SQLException;
    
}
