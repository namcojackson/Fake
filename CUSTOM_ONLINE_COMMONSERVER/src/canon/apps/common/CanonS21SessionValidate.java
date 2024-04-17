package canon.apps.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.apps.fnd.common.WebAppsContext;

import com.canon.cusa.s21.framework.security.S21Authentication;
import com.canon.cusa.s21.framework.security.S21AuthenticationException;
import com.canon.cusa.s21.framework.security.S21AuthorizationAction;
import com.canon.cusa.s21.framework.security.S21AuthorizationException;
import com.canon.cusa.s21.framework.security.context.S21SecurityContext;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class CanonS21SessionValidate {

	public static boolean isAuthenticated() {
		System.out
				.println("[Kim Debug]isAuthenticated: Used S21SecurityContextHolder.getContext() method, Not from session.");
		S21SecurityContext context = S21SecurityContextHolder.getContext();
		System.out.println("in isAuthenticated, context is  " + context);
		if (context != null) {
			System.out.println("in isAuthenticated, authentication is  "
					+ context.getAuthentication());
			return context.getAuthentication() != null;
		} else {
			return false;
		}
	}

	public static boolean isBusinessAppAuthorized(String bizAppId) {
		S21UserProfileService upService = S21UserProfileServiceFactory
				.getInstance().getService();
		List<String> funcCodes = upService
				.getAuthorizedFunctionCodeListForBizAppId(bizAppId);
		return funcCodes.size() > 0;
	}

	/**
	 * return the login user name, for example employee Q ID
	 */
	public static String getUserName() {
		S21SecurityContext context = S21SecurityContextHolder.getContext();
		S21Authentication s21Authentication = context.getAuthentication();
		String loginName = "";
		try {
			loginName = s21Authentication.getIdentityDetails().getUID();
			// getUID is same as getIdentityDN()
		} catch (S21AuthenticationException e) {
			e.printStackTrace();
		}
		return loginName;
	}

	/**
	 * return Full name of the login user.
	 */
	public static String getFullName() {
		S21SecurityContext context = S21SecurityContextHolder.getContext();
		S21Authentication s21Authentication = context.getAuthentication();
		String fullName = "";
		try {
			fullName = s21Authentication.getIdentityDetails().getUserName();
		} catch (S21AuthenticationException e) {
			e.printStackTrace();
		}
		return fullName;
	}

	/*
	 * Used by WebRequestUtil to minic OA Framework
	 * 
	 * @see WebRequestUtil
	 */
	public static WebAppsContext validateContext(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		System.out.println("session is new ? " + session.isNew());
		if (session == null || !isAuthenticated()) {
			throw new IOException("Not Authenticated.");
		}
		WebAppsContext wc = new WebAppsContext();
		if (session != null) {
			wc.setUserName(getUserName());
		}
		return wc;
	}

	/**
	 * Used for dynamic context. for example: <script
	 * src='<%=commonRoot(request)%>/jquery/jquery-1.5.1.min.js'
	 * type='text/javascript'></script>
	 */
	public static String commonRoot(HttpServletRequest req) {
		return req.getContextPath() + "/common";
	}

	/*
	 * Check if user has specific role
	 */
	public static boolean hasRole(String role) {
		S21SecurityContext context = S21SecurityContextHolder.getContext();
		if (context != null) {
			S21Authentication s21Authentication = context.getAuthentication();
			if (s21Authentication != null) {
				S21AuthorizationAction[] actions = null;
				try {
					actions = s21Authentication.getAuthorizationActions();
				} catch (S21AuthorizationException e) {
					e.printStackTrace();
				}
				if (actions !=null) {
					for (S21AuthorizationAction action : actions) {
						String resourceObjName = action.getName();
						if (role.equals(resourceObjName)) {
							return true;
						}
					}
				}
			}
		}
		return false;

	}
	
}
