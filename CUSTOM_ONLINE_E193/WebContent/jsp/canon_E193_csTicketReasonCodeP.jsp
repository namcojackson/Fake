<!-- $Header: ITG# 74988 canon_E193_csTicketReasonCodeP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketReasonCodeP.jsp - reason codes.
 |   
 | DESCRIPTION
 |   Details of the reason code and reason description.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/14/2005
 |
 | HISTORY
 | DATE        WHO               WHY
 |
 | 30-Nov-06   Vikas Basal       ITG# 74988 CFS Changes 
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<% String ctxPath = request.getContextPath(); %>
<link rel=stylesheet href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">
<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_ReasonCodeObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_ReasonCode" id="objReasonDao" scope="page"/>
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<script language="javascript">
function rsnCodeRefresh() {
	//document.ReasonCodeForm.submit();
	
	var qryStr="reasonCode="+encodeURIComponent( $('select[name="reasonCode"]').val() );
	   var modelName ="#dlg";

	       $.ajax({
				url: "canon_E193_csTicketReasonCodeP.jsp",
				data:qryStr,
				type:"POST",
			    cache: false,
			    cached: false,
				success: function(data){     
						  hidePleaseWait();     
	   					  $(modelName).html("");					
	   				      $(modelName).html(data);  				       
	   				       hidePleaseWait();
	 	        }             
	       });
	
}



</script>

<%
	//Get Application ID
	boolean isFirstTime = true;
	int iApplId = objCiSession.getApplId();
	String strReasonCode = request.getParameter("reasonCode");
	System.out.println("~~ strReasonCode ~~~"+ strReasonCode);
	if(strReasonCode != null && !strReasonCode.startsWith("CSR_E193_")) strReasonCode = "CSR_E193_"+strReasonCode;
	String strCodeDesc = request.getParameter("hCodeDesc")==null?"":request.getParameter("hCodeDesc");
	if(!"".equals(strCodeDesc)) isFirstTime = false;
	ArrayList alReasonList = new ArrayList();
	Canon_E193_ReasonCodeObj objReason = null;	
	System.out.println("inputs in reason code are " + iApplId  + strReasonCode  + isFirstTime);
	alReasonList = objReasonDao.getReasonCodes(iApplId, strReasonCode, isFirstTime);

	if(alReasonList != null && alReasonList.size() > 1) {
   		ArrayList list = (ArrayList)alReasonList.get(0);
   		if(list != null && list.size() > 0) {
   			for(int i=0; i<list.size(); i++) {
   				objReason = (Canon_E193_ReasonCodeObj)list.get(i);
   				if("".equals(strCodeDesc)) strCodeDesc = objReason.getReasonCode() + "|" + objReason.getMeaning();
   				else strCodeDesc = strCodeDesc + "|" + objReason.getReasonCode() + "|" + objReason.getMeaning();
   			}
   			alReasonList.remove(0);
   		}
   	}

/* ITG# 74988 - Begin */
	int iCFS = strReasonCode.indexOf("CFS");
	System.out.println("~~ iCFS ~~~"+ iCFS);
/* ITG# 74988 - End */  
%>

	<form name="ReasonCodeForm" action="canon_E193_csTicketReasonCodeP.jsp" method="post">
	<input type="hidden" name="hCodeDesc" value="<%=strCodeDesc%>">
	<div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
	 <p id="eMsg"></p>
	</div>
	<div style="margin-left:30px;">Customer Inquiry Reasons</div>
	<div style="margin-left:30px;"><select name="reasonCode" id="reasonCode" size="1" class="searchBarLink" onChange="javascript:rsnCodeRefresh();">
					   			<% 	strReasonCode = strReasonCode==null?"":strReasonCode;
					   				if(strCodeDesc != null) {
					   					String code = "", desc = "";
					   					StringTokenizer st = new StringTokenizer(strCodeDesc, "|");
					   					while(st.hasMoreTokens()) {
					   						code = st.nextToken();
					   						desc = st.nextToken();
								/* ITG# 74988 - Begin */  					   						
					   						if (iCFS > 0)
					   						{
					   							if ( "CSR_E193_CFS".equals(code) )
												{
					   			%>
					   							<option value="<%=code%>" <%=strReasonCode.equals(code)?"selected":""%>><%=desc%></option>
					   			<%		
					   							}
					   						} else if ( !("CSR_E193_CFS".equals(code)) )
					   						{
					   			%>			
					   							<option value="<%=code%>" <%=strReasonCode.equals(code)?"selected":""%>><%=desc%></option>
					   			<%
					   						}
					   					}
					   				}
								/* ITG# 74988  - End */  					   				
					   			%>
		   					</select><a class="btn_red" href="javascript:action_func1ReasonCode()" style="float:right;">Select</a></div>
		   					<table cellspacing="1" class="supplies-table" style="margin-left:30px;">
		 					 <tr>
				 				<th>&nbsp;</th>
								<th>CI Reason</th>
								<th>CI Description</th>
		 					 </tr>
		 			<%
		 				if(alReasonList != null && alReasonList.size() > 0) {
		 					ArrayList dList = (ArrayList)alReasonList.get(0);
		 					if(dList != null && dList.size() > 0) {
		 						for(int j=0; j<dList.size(); j++) {
		 							objReason = (Canon_E193_ReasonCodeObj)dList.get(j);
		 			%>
				   				<tr>
					   				<td><input name="thdetails" type="radio" value="<%=objReason.getReason()==null?"":objReason.getReason()%>"></td>
		               				<td style="text-align:left"><%=objReason.getReason()==null?"":objReason.getReason()%></td>
					   				<td style="text-align:left"><%=objReason.getDescription()==null?"":objReason.getDescription()%></td>
								</tr>
					<%			}
							}else{
					%>
							<tr>
								<td colspan="3">
									<b><%=strTicketReasonCodePM1%></b>
								</td>
							</tr>
					<%		
							}
						} 
					%>
					<tr>
				<td align="left" colspan="3"> 
				<a class="btn_red" href="javascript:action_func1ReasonCode();" style="float:right;">Select</a>				
				</td>
				</tr>
	     		</table> 	
	</form>	
<%
} 
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
       
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>