<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.canon.oracle.custapp.util.CanonUploadUtil"%>
<%@page import="com.canon.oracle.custapp.util.CanonCustCareUtil"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AccountDetails"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="java.util.*" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_QuickTicketDAO" id="objQuickAcctInfo" scope="page"/>




<%!//To take care of data containing special characters like Single Quote. If additional special characters need to be handled coding should be done
   //here
   public String getHTMLString(String param) {
      if (param == null)
         return "";
      StringBuffer nameBuf = new StringBuffer(param.length() + 100);
      int length = param.length();
      for (int i = 0; i < length; i++) {
         char ch = param.charAt(i);
         switch (ch) {
            case '<':
               nameBuf.append("&lt;");
               break;
            case '>':
               nameBuf.append("&gt;");
               break;
            case ' ':
               nameBuf.append("&nbsp;");
               break;
            case '&':
               nameBuf.append("&amp;");
               break;
            case '"':
               nameBuf.append("&quot;");
               break;
            case '\'':
               nameBuf.append("&#039;");
               break;
            default :
               nameBuf.append(ch);
         }
      }
      return param;
   }

	public String stringWithDoubleQuotes(String strValue, boolean isNext) {
		StringBuilder builder = new StringBuilder();
		builder.append("\"");
		builder.append(strValue);
		builder.append("\"");
		if (isNext) {
			builder.append(",");
		}
		return builder.toString();
	}
	
	public void prepareSerialNumberHeaderJson(StringBuffer retJsonBuilder){
		
		retJsonBuilder.append("[");
		//retJsonBuilder.append(stringWithDoubleQuotes("Acct Id", true));
		retJsonBuilder.append(stringWithDoubleQuotes("Accont Name", true));
		retJsonBuilder.append(stringWithDoubleQuotes("Accout Number ", true));
		retJsonBuilder.append(stringWithDoubleQuotes("Serial Number", true));
		retJsonBuilder.append(stringWithDoubleQuotes("Model", false));
		retJsonBuilder.append("]");
	}
	
	public void prepareSerialNumberJson(Canon_E193_AccountDetails objAcctDetails, StringBuffer retJsonBuilder){
		retJsonBuilder.append("[");
		//retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getAcctId(), true));
		retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getAcctName(), true));
		retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getAcctNum(), true));
		retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getSerialNum(), true));
		retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getModel(), false));
		retJsonBuilder.append("]");
	}
	
	public void preparePONumberHeaderJson(StringBuffer retJsonBuilder){
		
		retJsonBuilder.append("[");
		//retJsonBuilder.append(stringWithDoubleQuotes("Acct Id", true));
		retJsonBuilder.append(stringWithDoubleQuotes("Accont Name", true));
		retJsonBuilder.append(stringWithDoubleQuotes("Accout Number ", true));
		retJsonBuilder.append(stringWithDoubleQuotes("PO Number", false));
		retJsonBuilder.append("]");
	}
	
	public void preparePONumberJson(Canon_E193_AccountDetails objAcctDetails, StringBuffer retJsonBuilder){
		retJsonBuilder.append("[");
		//retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getAcctId(), true));
		retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getAcctName(), true));
		retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getAcctNum(), true));
		retJsonBuilder.append(stringWithDoubleQuotes(objAcctDetails.getPoNum(), false));
		retJsonBuilder.append("]");
	}
	
	public void prepareOneRecordJson(StringBuffer retJsonBuilder,
			String partyName, String acctNum, String custAcctId, String trxType) {
		retJsonBuilder.append("[");
		retJsonBuilder.append(stringWithDoubleQuotes(partyName, true));
		retJsonBuilder.append(stringWithDoubleQuotes(acctNum, true));
		retJsonBuilder.append(stringWithDoubleQuotes(custAcctId, true));
		retJsonBuilder.append(stringWithDoubleQuotes(trxType, false));
		retJsonBuilder.append("]");
	}

%>
<%
	String strAcctVal = request.getParameter("acctVal").trim();
	String strOrgTypVal = request.getParameter("originalType").trim();
	int iOrgId = Integer.parseInt(request.getParameter("orgId").trim());
	System.out.println("strAcctVal : " + strAcctVal  + "strOrgTypVal : " + strOrgTypVal + "iOrgId : " + iOrgId);
	int currentRead =0;
	String retVal="";
	
	StringBuffer retJsonBuilder = new StringBuffer();
	CanonCustCareUtil custCareApi = new CanonCustCareUtil();
		
	retJsonBuilder.append("[") ;
	
	if(strOrgTypVal != null && strOrgTypVal.equalsIgnoreCase("serialNum"))
	{
		String strOrderName = "";
		String strOrderBy = "";		
		int iPageNo = 0;
		int iTotPageNo = 0;
		//Get Account Details
		ArrayList alAcctList = objQuickAcctInfo.getQuickTicketSNoAcctDetails(strOrgTypVal, strAcctVal, strOrderName, strOrderBy, iPageNo, iTotPageNo);
		Canon_E193_AccountDetails objAcctDetails = null;
		if(alAcctList != null)
		{
			if(alAcctList.size() == 1)
			{
				objAcctDetails = (Canon_E193_AccountDetails)alAcctList.get(0);
				prepareOneRecordJson(retJsonBuilder, 
						getHTMLString(objAcctDetails.getAcctName()), objAcctDetails.getAcctNum(), 
						objAcctDetails.getAcctId(), "");
			}else if(alAcctList.size() > 1)
			{
				prepareSerialNumberHeaderJson(retJsonBuilder);
				retJsonBuilder.append(",");
				for(int i = 0; i < alAcctList.size(); i++)
				{
					objAcctDetails = (Canon_E193_AccountDetails)alAcctList.get(i);
					if(objAcctDetails != null)
					{
						prepareSerialNumberJson(objAcctDetails, retJsonBuilder);
					}
					if(i < alAcctList.size() - 1)
					{
						retJsonBuilder.append(",");
					}
				}
			}
		}
		
	}else if(strOrgTypVal != null && strOrgTypVal.equalsIgnoreCase("poNum"))
	{
		ArrayList alAcctList = objQuickAcctInfo.getMultipleAccountDetails(iOrgId, strOrgTypVal, strAcctVal);
		Canon_E193_AccountDetails objAcctDetails = null;
		if(alAcctList != null)
		{
			if(alAcctList.size() == 1)
			{
				objAcctDetails = (Canon_E193_AccountDetails)alAcctList.get(0);
				prepareOneRecordJson(retJsonBuilder, 
						getHTMLString(objAcctDetails.getAcctName()), objAcctDetails.getAcctNum(), 
						objAcctDetails.getAcctId(), "");
			}else if(alAcctList.size() > 1)
			{
				preparePONumberHeaderJson(retJsonBuilder);
				retJsonBuilder.append(",");
				for(int i = 0; i < alAcctList.size(); i++)
				{
					objAcctDetails = (Canon_E193_AccountDetails)alAcctList.get(i);
					if(objAcctDetails != null)
					{
						preparePONumberJson(objAcctDetails, retJsonBuilder);
					}
					if(i < alAcctList.size() - 1)
					{
						retJsonBuilder.append(",");
					}
				}
			}
		}
	}
	else
	{
		if(!("".equals(strAcctVal))){
			String[] retMessage = objQuickAcctInfo.getAccountDetails(strAcctVal, strOrgTypVal, iOrgId);
			if(retMessage[1] != null){
				prepareOneRecordJson(retJsonBuilder, retMessage[0], retMessage[1], retMessage[2], custCareApi.checkNull(retMessage[3]));
			}
		}
		  
	}
	
	retJsonBuilder.append("] ") ;
	System.out.println("End Quick Ticket details : ");
	
	
	response.setContentType("application/text");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(retJsonBuilder.toString());
	
%>