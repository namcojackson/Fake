package canon.apps.common;

import static canon.apps.common.CanonS21SessionValidate.isBusinessAppAuthorized;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CanonS21AuthorizationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		if(!isValid(request,response)){
			RequestDispatcher disp=request.getRequestDispatcher("/common/jsp/canonInvalidAccess.jsp");
			disp.forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private boolean isValid(HttpServletRequest request ,HttpServletResponse response) {
    	String path = request.getRequestURI().substring(request.getContextPath().length());
    	String appName=path.substring(1,path.indexOf("/", 1)).toUpperCase();
    	if("COMMON".equals(appName)) 
    		return true;
	//	boolean authorized = isBusinessAppAuthorized("S21EXTN_" + appName);
    //  return authorized;
    	//return true; // changed by Sachin for E008 issue  by pass the  security for all 
    	
    	if((isBusinessAppAuthorized("S21EXTN_" + appName)) || (isBusinessAppAuthorized("EXTN" + appName))){
    		return true;
    	}else{
    		return false;
    	}
	}
}
