package canon.apps.common;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CanonRowMapper {
	Object mapRow(ResultSet rs, int rowNum) throws SQLException;

}
