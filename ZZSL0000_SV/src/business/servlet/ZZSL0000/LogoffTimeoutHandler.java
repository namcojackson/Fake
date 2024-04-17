/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q01487
 * Company: Fujitsu Limited
 * Date: Jul 6, 2009
 */
package business.servlet.ZZSL0000;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.fujitsu.uji.DispatchContext;
import com.fujitsu.uji.GenericHandler;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 * The Class LogoffTimeoutHandler.
 * @author $Author: Piotr Cebula $
 * @version $Revision: 1.2 $ $Date: 2009/07/06 22:24:22 $
 */
public class LogoffTimeoutHandler extends GenericHandler {
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -4213132293279403640L;

    /**
     * The Constant TIMEOUT_FLG.
     */
    public static final String TIMEOUT_FLG = "AMA_TIMEOUT_FLG";
    
	/**
     * Handler action.
     * @param context the dispatch context
     */
	public final void action(DispatchContext context) {

		//redirect to login screen
		HttpDispatchContext hdc = (HttpDispatchContext) context;

		ServletContext sc = hdc.getServletContext();
//		String logoffLink = sc.getInitParameter("logoff_link");
       
        //Set timeout flag
        hdc.getServletRequest().getSession().setAttribute(TIMEOUT_FLG, "true");
        
        RequestDispatcher rd = sc.getRequestDispatcher("/jsp/common/login.jsp");
		try {
			rd.forward(hdc.getServletRequest(), hdc.getServletResponse());
		} catch (IOException e) {
		} catch (ServletException e) {
		}
	}
}
