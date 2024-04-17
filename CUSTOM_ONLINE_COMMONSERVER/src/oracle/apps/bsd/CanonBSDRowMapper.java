package oracle.apps.bsd;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @deprecated use {@link canon.apps.common.CanonRowMapper} instead.   
 */
@Deprecated
public interface CanonBSDRowMapper {
        Object mapRow(ResultSet rs,int rowNum)throws SQLException;
}
