<%@page import="java.util.HashMap"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.canon.usa.tcepps.security.TCEPPSSecurityManager"%>
<%@page import="java.util.Map"%>
<%@page import="canon.apps.common.CanonCustomProfile"%>
<%!
	com.canon.common.CanonCommonUtil util= new com.canon.common.CanonCommonUtil();
	String checkNull(String s){
		return util.checkNull(s);
	}
	
	String safe(String s){
		return URLEncoder.encode(util.checkNull(s));
	}
	
	public String escape(String s){
		if(s!=null) return s.replace("\"","\\\"");
		return "";
	}
	
%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setHeader("Cache-Control", "no-cache, private, no-store, max-stale=0");
	response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        String tceppsApp = "S21CSA_ASCC";
        String tceppsEncryptedResponse =  request.getParameter("tceppsEncryptedResponse");
        String tceppsEncryptedResponseKey =  request.getParameter("tceppsEncryptedResponseKey");		
//        out.println("tceppsEncryptedResponse   " + tceppsEncryptedResponse + "<BR>");
//        out.println("tceppsEncryptedResponseKey   " + tceppsEncryptedResponseKey);
        //out.println("Request Query String " + request.getQueryString() +"<BR>");
//        out.println("<BR><BR>");
	Map<String, String> tceppsResponse = null ;	
	try{
          tceppsResponse = TCEPPSSecurityManager.getInstance().decryptTceppsParameters(tceppsApp, tceppsEncryptedResponse, tceppsEncryptedResponseKey);
	}catch(Exception ex){
		out.println(ex);
	}
	String customerRefNum="";
	String cardBrand="";
	String ccExp="";
	String customerName="";
	String approvalStatus="";
	String procStatusMessage="";
	String txRefNum="";
	String ccAccountNum="";
	if(tceppsResponse ==null) {
		tceppsResponse=new HashMap<String, String>();	
	}
	System.out.println(" tceppsResponse "+tceppsResponse);
	tceppsResponse.put("status",request.getParameter("status"));
	tceppsResponse.put("exceptionClass",request.getParameter("exceptionClass"));
	tceppsResponse.put("errorMessage",request.getParameter("errorMessage"));
	tceppsResponse.put("tcepps.serviceOperation",request.getParameter("tcepps.serviceOperation"));
	
/* 	customerRefNum=tceppsResponse.get("ppa.customerRefNum");
	cardBrand=tceppsResponse.get("ppa.cardBrand");
	ccExp=tceppsResponse.get("ppa.ccExp");
	customerName=tceppsResponse.get("ppa.customerName");
	ccAccountNum=tceppsResponse.get("ppa.ccAccountNum");
	approvalStatus= tceppsResponse.get("pno.approvalStatus");
	procStatusMessage=tceppsResponse.get("pno.procStatusMessage");
	txRefNum=tceppsResponse.get("pno.txRefNum");
 */
 
if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) {
	out.println("<!-- debug:");
	out.println(tceppsResponse.toString().replaceAll("--",""));
	out.println("-->");
}			

%>

<script type="text/javascript">

var _response={
"x":"x"	
<%
for(String key : tceppsResponse.keySet()) {
    String val = tceppsResponse.get(key);
    String key_=(key.startsWith("ppa.") || key.startsWith("pno.")) ? key.substring(4) : key;
%>
	,"<%= key_ %>" : "<%= escape(val)%>" 
<%
}
%>
};

    


	function tceppsResponse(response) {
		if(top.tceppsInvocationError) {
			top.tceppsInvocationError = false;
		}
		
    	var status = response.status; 
    	if (status) {
    		if (status == "SUCCESS") {
    			if(top.tceppsResponseSuccess) {
    				top.tceppsResponseSuccess(response);
    			} else {
    				parent.tceppsResponseSuccess(response);
    			}

    		} else {
    			if(top.tceppsResponseError) {
    				top.tceppsResponseError(response);
    			} else {
    				parent.tceppsResponseError(response);
    			}
    		}
    	}
		//top.document.body.removeChild(top.document.getElementById("tceppsProxyIFrame"));
	}
	
    window.onload = function() {
       tceppsResponse(_response);
    }
</script>

