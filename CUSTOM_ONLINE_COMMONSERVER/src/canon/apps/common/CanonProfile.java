package canon.apps.common;

import java.math.BigDecimal;
import java.sql.Date;

/*
 * There are 2 type of function in this class, one type of function throws exception if profile not exits,
 * Another type of function returns default value if profile not exits.
 */
public final class CanonProfile {

	static void error(String profileName,String validation_status, String error_msg) throws Exception{
		if(validation_status!=null && validation_status.equals("E")){
			String exmsg="Error occured ("+error_msg+") while getting profile " + profileName;
			System.out.println(exmsg);
			throw new Exception(exmsg);
		}else{
			String exmsg="Invalid Validation Status ("+validation_status+") "+profileName;
			System.out.println(exmsg);
			throw new Exception(exmsg);
		}
	}
	
	public static String getStringValue(String profileName) throws Exception{
		Object objs[]=CanonCommonUtilDao.profileByChar(profileName);
		String profile_value=(String)first(objs);
		String validation_status=(String)second(objs);
		String error_msg=(String)third(objs);
		if(validation_status!=null && validation_status.equals("S")){
			return profile_value;
		}else{
			error(profileName,validation_status,error_msg);
			return null; // Never here
		}
	}
	
	public static String getStringValue(String profileName, String defaultValue){
		try {
			return getStringValue(profileName);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static int getIntValue(String profileName) throws Exception {
		Object objs[]=CanonCommonUtilDao.profileByNumber(profileName);
		BigDecimal profile_value=(BigDecimal)first(objs);
		String validation_status=(String)second(objs);
		String error_msg=(String)third(objs);
		if(validation_status!=null && validation_status.equals("S")){
			return profile_value.intValue();
		}else{
			error(profileName,validation_status,error_msg);
			return 0; // Never here
		}
	}

	public static int getIntValue(String profileName, int defaultValue) {
		try {
			return getIntValue(profileName);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static long getLongValue(String profileName) throws Exception {
		Object objs[]=CanonCommonUtilDao.profileByNumber(profileName);
		BigDecimal profile_value=(BigDecimal)first(objs);
		String validation_status=(String)second(objs);
		String error_msg=(String)third(objs);
		if(validation_status!=null && validation_status.equals("S")){
			return profile_value.longValue();
		}else{
			error(profileName,validation_status,error_msg);
			return 0; // Never here
		}
	}

	public static long getLongValue(String profileName, long defaultValue) {
		try {
			return getLongValue(profileName);
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Date getDateValue(String profileName) throws Exception {
		Object objs[]=CanonCommonUtilDao.profileByDate(profileName);
		Date profile_value=(Date)first(objs);
		String validation_status=(String)second(objs);
		String error_msg=(String)third(objs);
		if(validation_status!=null && validation_status.equals("S")){
			return profile_value;
		}else{
			error(profileName,validation_status,error_msg);
			return null; // Never here
		}
	}

	
	public static Date getDateValue(String profileName,Date defaultValue) {
		try {
			return getDateValue(profileName);
		} catch (Exception e) {
			return defaultValue;
		}
		
	}
	
    static Object nth(Object obj,int n) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < n ? null : objs[n-1];
        }
        return null;
    }

    static Object first(Object obj) {
    	return nth(obj,1);
    }

    static Object second(Object obj) {
    	return nth(obj,2);
    }

    static Object third(Object obj) {
    	return nth(obj,3);
    }
    
    static boolean isEmpty(String s){
    	return s==null || s.trim().length()==0;
    }
    
//	public static void main(String []args){
//		test();
//	}
	
	static void test(){
		//System.out.println(" CANON_E479_BILLER_EMAIL_ADDRESS = "+getStringValue("CANON_E479_BILLER_EMAIL_ADDRESS"));
		try {
			System.out.println(" IS_PROD_SYSTEM = "+getStringValue("IS_PROD_SYSTEM"));
			System.out.println(" CANON_E479_BILLER_EMAIL_ADDRESS = "+getStringValue("CANON_E479_BILLER_EMAIL_ADDRESS"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(" CANON_E479_HIGH_DOLLAR_CUTOFF = "+getIntValue("CANON_E479_HIGH_DOLLAR_CUTOFF"));
			System.out.println(" CANON_E479_NEGATIVE_READ_CUTOFF = "+getLongValue("CANON_E479_NEGATIVE_READ_CUTOFF"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(" CANON_TEST_DUMMY_DATE = "+getDateValue("CANON_TEST_DUMMY_DATE"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(" IS_TEST_SYSTEM = "+getStringValue("IS_TEST_SYSTEM","Y"));
		System.out.println(" Non_Existing = "+getIntValue("Non_Existing",0));
		System.out.println(" CANON_TEST_DUMMY_DATE = "+getDateValue("NON_EXISTING",new Date(115,8,10)));

	}

	
}
