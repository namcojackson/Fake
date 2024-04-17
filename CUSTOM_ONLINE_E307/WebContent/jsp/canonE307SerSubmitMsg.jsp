<%@page import="com.canon.apps.servreq.beans.CanonE307ServMsgsBean"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServMsgDao"%>
<%@page language="java" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<%@page import="java.text.*" %>
<%@page import="java.sql.*" %>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.net.URLDecoder"%>
<%@page import ="java.io.PrintWriter"%>

<%
        CanonE307ServMsgDao objMsgDAO = new CanonE307ServMsgDao(); 
        String resArray;
        String userId="";
        PrintWriter pw = response.getWriter();
        StringBuffer buff = new StringBuffer();
    	String levelArr= request.getParameter("levelArr");
    	String levelValueArr= request.getParameter("levelValueArr");
       	String startDateArr= request.getParameter("startDateArr");
    	String startTimeArr= request.getParameter("startTimeArr");
       	String endDateArr= request.getParameter("endDateArr");
    	String endTimeArr= request.getParameter("endTimeArr");
       	String fullHourArr= request.getParameter("fullHourArr");
    	String msgArr= request.getParameter("msgArr");
    	 userId= request.getParameter("user");
    	System.out.println("levelArr:"+levelArr);
    	String[] level = levelArr.split(","); 
    	String[] levelValue = levelValueArr.split(",");
    	String[] startDate = startDateArr.split(","); 
    	String[] startTime = startTimeArr.split(",");
    	String[] endDate = endDateArr.split(","); 
    	String[] endTime= endTimeArr.split(",");
    	String[] fullHour= fullHourArr.split(","); 
    	String[] msg = msgArr.split(",");
    	
    	
    	
    	int len=level.length;
    	System.out.println("len:"+len);
    	System.out.println("level:"+level[0]);
    	java.util.ArrayList msgList = new java.util.ArrayList();
    	for(int i=0;i<len;i++)
    	{
  	  CanonE307ServMsgsBean  objMsgBean = new CanonE307ServMsgsBean();
  	  
  	String strlevel = level[i];
	String strlevelValue = levelValue[i];
	String strStartDate = startDate[i];
	String strEndDate = endDate[i];
	String strStartTime =startTime[i];
	String strEndTime = endTime[i];
	String strmsg = msg[i];
	String strfullhour=fullHour[i];
	System.out.println("strlevel:"+strlevel+"strlevelValue:"+strlevelValue+"strStartDate:"+strStartDate+"strEndDate:"+strEndDate+"strStartTime:"+strStartTime+"strEndTime:"+strEndTime+"strfullhour:"
			+strfullhour+"strmsg:"+strmsg);
  	  
	if(strlevel.equalsIgnoreCase("REGION"))
	{
		objMsgBean.setRegion(strlevelValue);
		
	}
	else if(strlevel.equalsIgnoreCase("BRANCH"))
	{
		objMsgBean.setBranch(strlevelValue);
		
	}
	else if(strlevel.equalsIgnoreCase("POSTAL"))
	{
		objMsgBean.setPostal(strlevelValue);
		
	}
	else if(strlevel.equalsIgnoreCase("PARTY_SITE_NUMBER"))
	{
		objMsgBean.setPartySiteNumber(strlevelValue);
		
	}
	else if(strlevel.equalsIgnoreCase("ACCOUNT_NUMBER"))
	{
		objMsgBean.setAccountNumber(strlevelValue);
		
	}
	else if(strlevel.equalsIgnoreCase("SERIAL_NUMBER"))
	{
		objMsgBean.setSerialNumber(strlevelValue);
		
	}
	else if(strlevel.equalsIgnoreCase("MODEL"))
	{
		objMsgBean.setModel(strlevelValue);
		
	}
	else if(strlevel.equalsIgnoreCase("PARTY_NUMBER"))
	{
		objMsgBean.setPartyNumber(strlevelValue);
		
	} 
	else if(strlevel.equalsIgnoreCase("SVCTEAM"))
	{
		objMsgBean.setSvcTeam(strlevelValue);
	} 
	
  	    objMsgBean.setLineNumber(i);
	 	objMsgBean.setFieldName(strlevel);
		objMsgBean.setFieldValue(strlevelValue);
		objMsgBean.setStartDate(strStartDate);
		objMsgBean.setEndDate(strEndDate);
		objMsgBean.setStartTime(strStartTime);
		objMsgBean.setEndTime(strEndTime);
		objMsgBean.setIsFullHour(strfullhour);;	
		objMsgBean.setServMsg(strmsg);	
  		msgList.add(objMsgBean);
    	}
 
    	resArray= objMsgDAO.createServMsgs(msgList, userId);
    	System.out.println("Return Message from DB:"+resArray);
        buff.append(resArray);        	
    	System.out.println("buff : " + buff.toString());
        pw.println(buff.toString());
        pw.flush(); 
    	
 %>
