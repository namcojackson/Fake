<!-- $Header: canon_E193_csTicketStatusP.jsp $ -->
<%--=========================================================================
 |
 | FILE 
 |  canon_E193_csTicketStatusP.jsp
 |
 | DESCRIPTION
 |  Ticket Status and Resolution Popup Page.
 |   
 | AUTHOR
 |	Venkat Velagala
 |
 | CREATION DATE
 |	23-Apr-2008
 |
 | HISTORY
 |  DATE				WHO						WHY
 | 21-Apr-2009    Chandra Sekhar       ITG # 176718 Change
 | 21-Apr-2009    Chandra Sekhar       ITG # 208045 - Canon Ball Changes
 | 29-Jan-2016    Mangala Shenoy	   Modified for S21 changes
 +=======================================================================--%>

<!-- ITG # 208045 Changes -->
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillInfo" scope="page" />
<jsp:setProperty name="objNonBillInfo" property="*" />
<%@page import="com.canon.oracle.custapp.custinq.dao.*"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.*"%>
<%@page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_Ticket"%>
<!-- ITG # 208045 Changes -->

  <% String ctxPath = request.getContextPath(); %>
  <link rel=stylesheet href="<%=ctxPath%>/e193/css/styles.css">
  <link rel=stylesheet href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css">
  <SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>


<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="java.lang.*" %>


<%@page import="com.canon.oracle.custapp.custinq.beans.*"%>
<%@page import="com.canon.oracle.custapp.util.*" %>

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>


<% 	
	String strTicketId = request.getParameter("ticketId");
	int iTktId = -1;
	if(strTicketId != null && !("".equals(strTicketId)) && !("null".equalsIgnoreCase(strTicketId)) )
		iTktId = Integer.parseInt(strTicketId);
	
	int iOrgId = objCiSession.getOrgId();
	
	//Start changes for S21 by Mangala
	//int iResId = objCiSession.getResourceId();
	String iResId = objCiSession.getResourceId();
	//End changes for S21 by Mangala
	Canon_E193_Ticket objTkt = new Canon_E193_Ticket();

	ArrayList alHeaderLineDtls = (ArrayList)objTkt.getTicketDetails(iOrgId,iTktId,iResId);
    
	String strCatIdP = "";
    String strLineIdConCat = "";

    //Get Header Object
    Canon_E193_TicketHeaderObj objHeader = (Canon_E193_TicketHeaderObj) alHeaderLineDtls.get(0);

%>
  

<div id="main_content_DialogBox">
<form name="tktStatusForm" method="post">
   <input type="hidden" name="hPageFrom" value="EISelectAcct" >
   <input type="hidden" name="hLineSel" value="" >

   <input type="hidden" name="ticketId" value="">
   <input type="hidden" name="hHdrStatus" value="">
   <input type="hidden" name="hLineId" id="hLineId" value="">   
   <input type="hidden" name="hLineStatus" value="">
   <input type="hidden" name="hLineSeverity" value="">
   <input type="hidden" name="hLineDept" value="">
   <input type="hidden" name="hLineRole" value="">
   <input type="hidden" name="hLineResource" value="">
   <input type="hidden" name="hLineNotes" value="">
   <input type="hidden" name="hSaveChanges" value="">
   <input type="hidden" name="hLineIdConCat" value="">
   <input type="hidden" name="strCatIdP" value="" >
   
   <input type="hidden" name="hCatId" value="<%=objHeader.getCatId()%>">
   <input type="hidden" name="hParentCatId" value="">
   <input type="hidden" name="hCatDesc" value="<%=objHeader.getCatIdDesc()%>">
   <input type="hidden" name="iTicketId" value="<%=objHeader.getTicketId()%>">

   <input type="hidden" name="origName" value="<%=objHeader.getOrigName()%>" >
   <input type="hidden" name="origPhNo" value="<%=objHeader.getOrigPhNo()%>">
   <input type="hidden" name="origEmailAddress" value="<%=objHeader.getOrigEmail()%>">
    <input type="hidden" name="origCheckbox" value="<%=objHeader.getsendEmailFlag()%>">
   <input type="hidden" name="origType" value="<%=objHeader.getOrigType()%>" >
   <input type="hidden" name="sourceType" value="<%=objHeader.getAttribute9()%>" >
         
   <input type="hidden" name="custName" value="<%=objHeader.getCustContact()%>" >
   <input type="hidden" name="custPhNo" value="<%=objHeader.getCustPhNo()%>">
   <input type="hidden" name="custEmailAddress" value="<%=objHeader.getCustEmail()%>">
   
   <input type="hidden" name="recurProb" value="<%=objHeader.getRecurring()%>">
   <input type="hidden" name="probType" value="">
   <input type="hidden" name="val1" value="">
   
   <input type="hidden" name="selAcctId" value="<%=objHeader.getCustAcctId()%>" >
   <input type="hidden" name="selLocId" value="" >
   <input type="hidden" name="selAcctName" value="<%=objHeader.getCustomerName()%>" >
   <input type="hidden" name="selAcctNum" value="<%=objHeader.getCustomerNo()%>" >
   
   <input type="hidden" name="tktId" value="<%=objHeader.getTicketId()%>">
   <input type="hidden" name="hIsBilling" value="<%=objHeader.getBillingIssue()%>">
   <input type="hidden" name="invNum" value="<%=objHeader.getInvoiceNo()%>">
   <input type="hidden" name="invSource" value="<%=objHeader.getCategory()%>">
  <table class="request-service" style="align:center;width:900px;" cellspacing="0" >
<!-- ITG # 176718 Change -->
   <% objCiSession.setRegionCode(objHeader.getAttribute6()); %>
<!-- ITG # 176718 Change -->

   <tr>
      <td colspan="2">
         <font class="promptReadOnly"><b>Ticket Number: &nbsp;<u><%=objHeader.getTicketNo()==null?"":objHeader.getTicketNo()%></u></b></font>
      </td>
   <tr>     
   <tr><td height="10" colspan="2"></td></tr>
   <tr> 
      <td colspan="2">Ticket Details:</td>
   </tr>
   <tr>
			    <td colspan="2">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
   <tr> 
      <td width="10"></td>
      <td>
         <table cellspacing="1" class="request-service">
            <tr>
<% 
         if(objHeader.getInvoiceNo() != null && !"".equals(objHeader.getInvoiceNo()) && !"null".equalsIgnoreCase(objHeader.getInvoiceNo()) ) {  
%>         
               <th>Invoice Number</th>
<%
            }
%>          
               <th>Date</th>
               <th>Type</th>
               <th>Created By</th>
               <th>Owner</th>
               <th>Status</th>
            </tr>
<%       
         String strHdrAccess =  objHeader.getAttribute5();
         String strCFSSerialNo = objHeader.getAttribute4();
%>          
            <tr>
<% 
            if( objHeader.getInvoiceNo() != null && !"".equals(objHeader.getInvoiceNo()) && !"null".equalsIgnoreCase(objHeader.getInvoiceNo()) ) {    
%> 
               <td><%=objHeader.getInvoiceNo()%></td> 
<%
            }
%>             
               <td><%=objHeader.getCreationDate()==null?"":objHeader.getCreationDate()%></td>
               <td><%=objHeader.getCategory()==null?"":objHeader.getCategory()%></td>
               <td><%=objHeader.getCreatedByResName()%></td>
               <td><%=objHeader.getOwnerResName()%></td>
               <td><%=objHeader.getStatus()%></td>
            </tr>
         </table>   
      </td>
   </tr>
   <tr><td height="10" colspan="2"></td></tr>
   <tr>
      <td colspan="2">Customer Details:</td>
   </tr>
   <tr> 
      <td width="10"></td>
      <td>
         <table cellspacing="1" class="request-service">
            <tr>
               <th>Account Name</th>
               <th>Account Number</th>
               <th>Contact Name</th>
               <th>Contact Number</th>
               <th>E-Mail</th>
<%
	if ( "CFS".equals( objHeader.getCategory()) )
	{ 
%>
               <td>CFS Serial Number</td>
<%
	}
%>
               
            </tr>
            <tr >
               <td><%=objHeader.getCustomerName()==null?"":objHeader.getCustomerName()%></td>
               <td><%=objHeader.getCustomerNo()==null?"":objHeader.getCustomerNo()%></td>
               <td><%=objHeader.getCustContact()==null?"":objHeader.getCustContact()%></td>
               <td><%=objHeader.getCustPhNo()==null?"":objHeader.getCustPhNo()%></td>
	       <td>
	         <a href="mailto:<%=objHeader.getCustEmail()==null?"":objHeader.getCustEmail()%>">
	           <%=objHeader.getCustEmail()==null?"":objHeader.getCustEmail()%>
	         </a>
	       </td>
<%
	if ( "CFS".equals( objHeader.getCategory()) )
	{ 
%>
               <td><%=strCFSSerialNo==null?"":strCFSSerialNo%></td>
<%
	}
%>
             </tr>
           </table>   
      </td>
   </tr>
   <tr><td height="10" colspan="2"></td></tr>
   <tr> 
      <td class="tdTableCellStyle" colspan="2"><font class="promptReadOnly">Ticket Line Details:</font></td>
   </tr>
   <tr>
      <td width="10"></td>
      <td>   
         <table cellspacing="1"  class="request-service">
            <tr>
               <th valign="top">&nbsp;</th>
               <th valign="top">Category</th>
               <th valign="top">Updated By</th>
               <th valign="top" nowrap>Update Date</th>
               <th valign="top">Resolution Time<br>(in days)</th>
               <th valign="top" align="center">Status</th>
               <th valign="top" align="center">Urgency</th>
               <th valign="top" align="center">Assignee Department</th>
               <th valign="top" align="center">Assignee Role</th>
               <th valign="top" align="center">Assignee Resource</th>
               <th valign="top">Reason</th>     
               <!-- ITG # 208045 Changes  -->
               <th>Line Attributes</th>
               <!-- ITG # 208045 Changes  -->
            </tr>
<%
            
            Canon_E193_Ticket objTktCatCount = new Canon_E193_Ticket();
            int iIssueCount = objTktCatCount.getIssueCount(objHeader.getCatId());
            int iTktIssueCount = 0;
            int iChildCatCount = -1;
            String strAttribute4 = "", strSummaryFlag = "N";
            boolean isFlagSummary = false;
            for(int i=1; i<alHeaderLineDtls.size(); i++) {
               Canon_E193_TicketLinesObj objLine = (Canon_E193_TicketLinesObj)alHeaderLineDtls.get(i);
               String strLineAccess =  objLine.getAttribute5();
               strLineIdConCat = strLineIdConCat + objLine.getLineId() + '|';
               String strLineStatus = objLine.getLineStatus();
               String strLineSeverity = objLine.getSeverity();

               /* ITG # 208045 Changes */
               String sourceApplication = "CUSTOMER_SERVICE";
               String sourceContext = "CS_E193_" +objHeader.getCategory() +"_" +objLine.getCatCode();
               int DffCount = (int)objNonBillInfo.getDffCount(sourceContext);
               /* ITG # 208045 Changes */
               
               strAttribute4 = objLine.getAttribute4()==null?"":objLine.getAttribute4();
               if("USAGE".equalsIgnoreCase(strAttribute4)) {
                  strSummaryFlag = ""+objLine.getLineId()+"|"+objLine.getLineNo();
                  if(!isFlagSummary) isFlagSummary = true;
               }
               
               // get total number of non_close issues added to this ticket
               if(!("CLOSE".equalsIgnoreCase(strLineStatus)))
               {
                  iTktIssueCount++;
                  
                  // get all non_close issues added to this ticket   
                  if(i == 1) 
                     strCatIdP = ""+objLine.getCatId();
                  else 
                     strCatIdP = strCatIdP + "|" + ""+objLine.getCatId();
               }
               
               iChildCatCount = objTktCatCount.getIssueCount(objLine.getCatId());               
%>   
            
            <tr >
               <input type="hidden" name="lineId<%=i%>" id="lineId<%=i%>" value="<%=objLine.getLineId()%>" >
               <input type="hidden" name="summaryLineId" id="summaryLineId" value="<%=strSummaryFlag%>" >
               <td><input name="csdetails" id="csdetails" type="radio" value="<%=i%>" onClick="select_Line(this,'note<%=i%>', <%=alHeaderLineDtls.size() %> )"></td>    
                  <td>
<%
                  if(iChildCatCount > 0) {
%>
                  <a href="javascript:void get_sublines('<%=objLine.getLineId()%>', '<%=strAttribute4%>');"><%=objLine.getCatIdDesc()==null?"":objLine.getCatIdDesc()%></a>
<%
                  } else {
%>
                  <%=objLine.getCatIdDesc()==null?"":objLine.getCatIdDesc()%>
<%
                  } 
%>
               </td>
               <td><%=objLine.getLastUpdatedByName()==null?"":objLine.getLastUpdatedByName()%></td> 
               <td nowrap><%=objLine.getLastUpdatedDate()==null?"":objLine.getLastUpdatedDate()%></td>                     
               <td><%=objLine.getAttribute3()==null?"":objLine.getAttribute3()%></td>
               <td nowrap><%=objLine.getLineStatus()==null?"":objLine.getLineStatus()%></td>
               <td nowrap><%=objLine.getSeverity()==null?"":objLine.getSeverity()%></td>
               <td><%=objLine.getDeptName()==null?"":objLine.getDeptName()%></td>
               <td><%=objLine.getRoleName()==null?"":objLine.getRoleName()%></td>
               <td><%=objLine.getResourceName()==null?"":objLine.getResourceName()%></td>
               <td><%=objLine.getReason()==null?"":objLine.getReason()%></td>
               <!-- ITG # 208045 Changes -->
               <td><% if (DffCount == 0) { %> <font size=4><b>...</b></font>
                   <% } else { %> <a href="#" onClick="javascript:OpenDff('<%=sourceApplication%>',  '<%=sourceContext%>', '<%=objHeader.getTicketNo()%>', '<%=objCiSession.getUserId()%>');"><font size=4><b>...</b></font></a>
                   <% } %>
               </td>
               <!-- ITG # 208045 Changes -->
            </tr>
<%
            }
            if(alHeaderLineDtls.size() < 2) {
%>          
               <tr class="tdTableCellStyle">
                  <td colspan="14">
                     <font class="promptReadOnly"><b><%=strTicketStatusM1%></b></font>
                  </td>
               </tr>
<%
            }
%>
         </table>   
         </div>
      </td>
   </tr>
   <tr><td height="10" colspan="2"></td>  
   <tr>
      <td class="tableQuestionCell" colspan="2">
         <font class="promptReadOnly" color="#FF0000">
            <b><%=strTicketStatusN2%></b>
         </font>
      </td>
   </tr>
   <tr><td colspan="2" height="10"></td></tr>
   <tr>
      <td class="tableQuestionCell" colspan="2"><font class="promptReadOnly">Ticket Comments:</font></td>
   </tr> 
    <tr><td height="10" colspan="2"></td></tr>
   <tr id="note0" style="position: absolute; visibility: visible;">
      
      <td colspan="2"> <textarea name="textarea" rows="10" style="height:auto; width:800px;" class="inTxt" readonly></textarea></td>
    </tr>      
    <% 
      for(int i=1; i<alHeaderLineDtls.size(); i++) {
         Canon_E193_TicketLinesObj objLine = (Canon_E193_TicketLinesObj)alHeaderLineDtls.get(i);
    %>
      <tr id="note<%=i%>" style="position: absolute; visibility: hidden;">
         <td colspan="2">
            <textarea name="existingnotes" rows="10" style="height:auto; width:800px;" class="inTxt" readonly><%=objLine.getNotes()==null?"":objLine.getNotes()%></textarea>
            <input type=hidden name="enlen" value="<%=objLine.getNotes()==null?0:objLine.getNotes().length()%>" >
         </td>
      </tr>
   <%  }  %>   
</table>
</form>
</div>
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
