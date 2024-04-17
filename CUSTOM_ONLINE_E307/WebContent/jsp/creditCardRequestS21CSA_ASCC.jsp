<%@page import="com.canon.usa.app.security.APPSecurityManager"%>
<%@page import="com.canon.usa.tcepps.security.TCEPPSSecurityManager"%>
<%@page import="com.canon.usa.tcepps.property.TCEPPSPropertyManager"%>
<%@page import="canon.apps.common.CanonCustomProfile"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil" %>
<%@page import="com.canon.common.CanonCommonUtil"  %>
<%@page import="com.canon.usa.tcepps.property.TCEPPSPropertyManager"%>

<%!
    com.canon.common.CanonCommonUtil util=new  com.canon.common.CanonCommonUtil();
	class LimitHashMap extends java.util.HashMap<String,String>{
	public String put(String key,String value, int lengthLimit){
		return super.put(key,value!=null && value.length()>lengthLimit?
				value.substring(0,lengthLimit):
				util.checkNull(value));
		}
	}
 %>
<%
	String tceppsApp = "S21CSA_ASCC";
	String profileId=request.getParameter("profileId");
	String tceppsAction = null;
	if(profileId==null || profileId.trim().length()==0){
		tceppsAction="profileAdd";
	}else{
		tceppsAction="newOrder";
	}
	
	String tceppsToken = TCEPPSSecurityManager.getInstance().generateToken(3600000);
	String tceppsSign = TCEPPSSecurityManager.getInstance().generateSign(tceppsToken, tceppsApp);
	String appTokenName = APPSecurityManager.APP_TOKEN;
	String appTokenValue = APPSecurityManager.getInstance().generateToken(session);
	String tceppsUrl = TCEPPSPropertyManager.getInstance().getTceppsUrl();
	
	session.setAttribute("tceppsApp", tceppsApp);

	String requestUrl = request.getRequestURL().toString();
	boolean secure="true".equals(request.getParameter("secure"));
	String proxyUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/") + 1)+ "tceppsResponseProxy.jsp";
	proxyUrl=secure? proxyUrl.replace("http:","https:"): proxyUrl;
	
 	String strSerialNum = request.getParameter("serialNumber");
	System.out.println("strSerialNum : " + strSerialNum+" requestUrl : "+requestUrl);
	
	LimitHashMap tceppsRequest = new LimitHashMap();
	tceppsRequest.put("tcepps.proxyUrl", proxyUrl);
	if("profileAdd".equals(tceppsAction)){
		tceppsRequest.put("ppa.customerName", request.getParameter("customerName"),30);
		tceppsRequest.put("ppa.customerAddress1", request.getParameter("customerAddress1"),30);
		tceppsRequest.put("ppa.customerAddress2", request.getParameter("customerAddress2"),30);
		tceppsRequest.put("ppa.customerCity", request.getParameter("customerCity"),20);
		tceppsRequest.put("ppa.customerState", request.getParameter("customerState"),2);
		tceppsRequest.put("ppa.customerZIP", request.getParameter("customerZIP"),10);
		tceppsRequest.put("ppa.customerEmail", request.getParameter("customerEmail"),50);
		tceppsRequest.put("ppa.customerPhone", request.getParameter("customerPhone"),14);
		tceppsRequest.put("ppa.customerCountryCode", request.getParameter("customerCountryCode"),2);
	}		
	if("newOrder".equals(tceppsAction)){
		tceppsRequest.put("pno.customerRefNum", profileId);
		/*
		Transaction Amount
			Implied decimal, including those currencies that are a zero exponent. For example, both $100.00 (an exponent of 2) and ¥100 (an exponent of 0) should be sent as amount = 10000.
		*/
		String amount=request.getParameter("amount")+"00";
		String orderID=request.getParameter("orderID");
		tceppsRequest.put("pno.amount",util.checkNull(amount));	
		tceppsRequest.put("pno.orderID",util.checkNull(orderID));
	}
	System.out.println("tceppsRequest : "+ tceppsRequest);

	java.util.Map<String, String> tceppsEncryptedRequestMap = TCEPPSSecurityManager.getInstance().encryptUrlEncodeTceppsParameters(tceppsRequest);
	String tceppsEncryptedRequest = tceppsEncryptedRequestMap.get(TCEPPSSecurityManager.TCEPPS_ENCRYPTED_REQUEST);
	String tceppsEncryptedRequestKey = tceppsEncryptedRequestMap.get(TCEPPSSecurityManager.TCEPPS_ENCRYPTED_REQUEST_KEY);
%>
	<form id="tcepps" name="tcepps" method="post" action="<%=tceppsUrl%>/jsp/creditCardFormPage.jsp" target="creditCardFormIFrame">
		<input id="app" name="app" type="hidden" value="<%=tceppsApp%>" /> 
		<input id="token" name="token" type="hidden" value="<%=CanonPCISecurityUtil.htmlAttrEncode(tceppsToken)%>" /> 
		<input id="sign" name="sign" type="hidden" value="<%=CanonPCISecurityUtil.htmlAttrEncode(tceppsSign)%>" /> 
		<input id="serviceOperation" name="serviceOperation" type="hidden" value="<%= tceppsAction %>" /> 
		<input id="encryptedRequest" name="encryptedRequest" type="hidden" value="<%=CanonPCISecurityUtil.htmlAttrEncode(tceppsEncryptedRequest)%>" /> 
		<input id="encryptedRequestKey" name="encryptedRequestKey" type="hidden" value="<%=CanonPCISecurityUtil.htmlAttrEncode(tceppsEncryptedRequestKey)%>" />
	</form>

	<form id="tceppsProxy" name="tceppsProxy" action="<%=tceppsUrl%>/jsp/creditCardFormProxy.jsp">
		<input id="app" name="app" type="hidden" value="<%=tceppsApp%>" /> 
		<input id="serviceOperation" name="serviceOperation" type="hidden" value="<%= tceppsAction %>" /> 
		<input id="avsName" name="avsName" type="hidden" value="Canon U.S.A, Inc." /> 
		<input id="address1" name="address1" type="hidden" value="1 Canon Park" /> 
		<input id="address2" name="address2" type="hidden" value="" /> 
		<input id="city" name="city" type="hidden" value="Melville" /> 
		<input id="state" name="state" type="hidden" value="NY" /> 
		<input id="zipCode" name="zipCode" type="hidden" value="11747" />
	</form>

	<form id="creditCard" name="creditCard" method="post" action="creditCardResponse.jsp">
		<input id="<%=appTokenName%>" name="<%=appTokenName%>" type="hidden" value="" /> 
		<input id="tceppsEncryptedResponse" name="tceppsEncryptedResponse" type="hidden" value="" /> 
		<input id="tceppsEncryptedResponseKey" name="tceppsEncryptedResponseKey" type="hidden" value="" />
		<iframe name="creditCardFormIFrame" width="350" height="200" scrolling="no" frameBorder="0"></iframe>
	</form>
