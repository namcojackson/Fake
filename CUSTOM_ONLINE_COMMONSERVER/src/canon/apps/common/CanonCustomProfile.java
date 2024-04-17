package canon.apps.common;

/**
 * Utility class to retrieve Custom Profile values 
 * @author Q05058
 *
 */
public class CanonCustomProfile {
	
	private static void checkResult(Object [] result) throws Exception{
	    System.out.println("result is " +result);
	    if(result==null) {
	        Exception cause=CanonCommonUtilDao.getException();
	        throw new Exception("Database exception.",cause);
	    }
	}

	private static void checkErrors(Object [] result,int error_flag_index,int error_message_index) throws Exception{
	    checkResult(result);
	    if(error_flag_index>result.length-1 || error_message_index>result.length-1){
	        throw new Exception("Database exception, invalid values.");
	    }
	    String error_flag=(String)result[error_flag_index];
	    String error_message=(String)result[error_message_index];
	    System.out.println("error_flag is " +error_flag);
	    System.out.println("error_message is " +error_message);
	    if( "E".equals(error_flag)){
	        throw new Exception("Database error.", new Exception(error_message));
	    }
	}
	
	/**
	 * the profile name, for example CANON_E479_BILLER_EMAIL
	 * @param profileName
	 * @return
	 * @throws Exception 
	 */
	public static String getUserProfileValue(String profileName){
		System.out.println("in getUserProfileValue profileName is "+profileName);
		if(profileName==null){
			return null;
		}
		Object [] result=CanonCommonUtilDao.retrieveProfileValues(CanonS21SessionValidate.getUserName(), null, profileName);
        try {
			checkErrors(result, 1,2);
	        String val=(String)result[0];
	        return val;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
