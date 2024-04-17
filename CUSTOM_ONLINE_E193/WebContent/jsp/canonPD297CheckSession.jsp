<%@ page language="java" %>
<%@ page import="com.canon.oracle.custapp.custprof.beans.canonPD297SecurityBean" %>

<jsp:useBean class="com.canon.oracle.custapp.custprof.beans.canonPD297SecurityBean" id="objSecurity" scope="session"/>

                    
<%
 String iUserID = objSecurity.getUserId();
 
	if(iUserID == "" )
	{
%>
	<jsp:forward page="AppsLocalLogin.jsp"/>
<%
	}
%>