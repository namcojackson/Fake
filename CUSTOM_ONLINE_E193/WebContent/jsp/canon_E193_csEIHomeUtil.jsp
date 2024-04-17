 
<%@page import="java.util.List"%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@ page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_EmailValidation" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_EmailValidation" id="objEmailDao" scope="page"/>

       
<%    Canon_E193_EmailValidation emailValidation = new Canon_E193_EmailValidation();

       
      //ArrayList al = obj.getEmanageUsername(strOrigEmailAddress);
	  String email = request.getParameter("email");
	  System.out.println("email: " + email);
	  ArrayList alEmanageUser = objEmailDao.getEmanageUser(email);
	//  List<String> listOfName = emailValidation.getEmanageUsername(email);
		  System.out.println("hello s is:  " + alEmanageUser);

	  response.setContentType("application/html");
	  response.setCharacterEncoding("UTF-8");
    //  if(listOfName!=null){
    	//  response.getWriter().write(listOfName.toString());
    	if(alEmanageUser!=null && alEmanageUser.size() > 0 ){
    		for(int i=0; i<alEmanageUser.size(); i++) {
    		response.getWriter().write(alEmanageUser.get(i).toString()+ "<br >");
    		//response.getWriter().println();
    		}
    	}
	   else{
		   response.getWriter().write(""); 
	   }

     %> 
	    	  
