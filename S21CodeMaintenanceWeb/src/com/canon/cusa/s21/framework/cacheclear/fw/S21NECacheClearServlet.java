package com.canon.cusa.s21.framework.cacheclear.fw;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.security.as.common.icache.flash.S21AuthorizationCacheAccessor;

/**
 * Servlet Calss for S21AuthorizationCache Clear<br>
 * @author Administrator
 */
public class S21NECacheClearServlet extends HttpServlet {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * GET Request Process<br>
     * @param req request
     * @param resp response
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProc(req, resp);
    }

    /**
     * POST Request Process<br>
     * @param req request
     * @param resp response
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProc(req, resp);
    }

    /**
     * Cache Clear Process<br>
     * @param req request
     * @param resp response
     */
    protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applnName = (String) req.getParameter("APP");
        applnName = URLDecoder.decode(applnName,"UTF-8");

        S21InfoLogOutput.println("S21NECacheClearServlet:Received APP Name ="+applnName);
        S21AuthorizationCacheAccessor.getInstance().invalidateCache(applnName);
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(applnName+" cache was cleared.");
        out.close();
        S21InfoLogOutput.println("S21NECacheClearServlet:"+applnName+" cache was cleared.");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
