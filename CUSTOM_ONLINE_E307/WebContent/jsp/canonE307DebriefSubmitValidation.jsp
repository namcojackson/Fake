<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO" %>  
<%@page import="java.util.*" %>
<%

	CanonE307DebriefDetailsDAO durObj = new CanonE307DebriefDetailsDAO();
	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
	String retFlg = durObj.getOnlineValidFlg();
	buff.append(retFlg);
	//System.out.println("Submit validation buff : " + buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>
