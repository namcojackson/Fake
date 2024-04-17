<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqAPIUtil"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="java.util.*" %>
<%
	String machPk = request.getParameter("machPk");	
	String serialNumber = request.getParameter("serialNumber");
	String model = request.getParameter("model");
	String loginUser =  request.getParameter("userId");
	String selDate = "";
	String modeCode = "0";
	
	String result = "N";
	//System.out.println("machPk: "+machPk+" serialNumber: "+serialNumber+" model: "+model+" loginUser: "+loginUser);

	CanonCommonUtil util = new CanonCommonUtil();
	//PrintWriter pw = response.getWriter();
   // StringBuffer buff = new StringBuffer("");
    
    CanonE307ServiceReqAPIUtil apiUti= new CanonE307ServiceReqAPIUtil();
    String[] arrAch = apiUti.getBillCode(machPk, selDate, modeCode, serialNumber, model, "", loginUser,"N");  //UC		
    if("Y".equals(util.checkNull(arrAch[1]).trim()) && "Y".equals(util.checkNull(arrAch[10]).trim())){
    	result="Y";
    }
		//buff.append(result);
		response.setContentType("application/text");
		//System.out.println("After Hours Flag: "+ buff.toString());
        //pw.println(buff.toString());
        //pw.write(result);
        response.getWriter().write(result);
        
%>
