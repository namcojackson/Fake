package canon.apps.common;

import com.canon.common.CanonCommonDao;

public class CanonConstants {
	public static final String CSA_COMPANY_CODE = "ADB";
	public static  String SCHEMA_NAME = "";
	  
	static{
		 SCHEMA_NAME = CanonCommonDao.getSchema();
	}
	  
}
