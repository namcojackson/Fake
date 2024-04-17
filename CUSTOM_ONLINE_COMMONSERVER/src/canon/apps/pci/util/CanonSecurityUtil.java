package canon.apps.pci.util;

import canon.apps.common.CanonS21SessionValidate;


public class CanonSecurityUtil {

	/*
	 * always return true, since we check at servlet filter level. 
	 */
	public static boolean isJSPAccessValid(String pageName, int userId,int respId) {
		return true;
	}
	
	/*
	 * shortcut for CanonS21SessionValidate.hasRole
	 */
	public static boolean hasRole(String role) {
		return CanonS21SessionValidate.hasRole(role);
	}

}
