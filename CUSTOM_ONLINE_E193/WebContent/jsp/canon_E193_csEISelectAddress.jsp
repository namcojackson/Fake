<!-- $Header: canon_E193_csEISelectAddress.jsp $ -->
<%--========================================================================
 |
 | FILE 
 | canon_E193_csEISelectAddress.jsp - Select Addressess
 |   
 | DESCRIPTION
 |   Retrives Addresses for given Account name or Number, stores them in session
 |   and forwards to respective jsp page.
 |
 | AUTHOR
 | Dipti Shedji 
 |
 | CREATION DATE
 | 08/07/2005
 |
 | HISTORY
 | DATE        WHO               WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>

<%!

   //To take care of data containing special characters like Single Quote. If additional special characters need to be handled coding should be done
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
      return nameBuf.toString();
   }

%>

<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objAddrArrayList" scope="request" />

<%@ include file="canon_E193_csTopInc.jsp" %>

<% 
   // Menu Prompt
   strPageName = "Enter & Inquiry";
   
   // Check page from to show the path
   strPageFrom = request.getParameter("hPageFrom");
   String hPath = request.getParameter("hPath");
   if (hPath == null)
      hPath = strSelectAccountAddressT1;
   else
      if(hPath.indexOf(strSelectAccountAddressT1) < 0)
         hPath = hPath + " -> " + strSelectAccountAddressT1;   
%>
 
<%@ include file="canon_E193_csMenuInc.jsp" %> 

<script language="JavaScript">
   var orderAsc = "asc";
   var orderDesc = "desc";
   var tArray = new Array("acctName", "acctNo", "addr1", "addr2", "city", "state", "zipCode");
   
   function fnOrderBy(name) {
      var objForm = document.addrSelectForm;
      var vOrdName = objForm.orderName.value;
      if(name == tArray[0] && vOrdName == "null") {
         vOrdName = tArray[0];
         objForm.orderBy.value = orderAsc;
      }
      if(vOrdName != name) objForm.orderBy.value = 'null';
      objForm.orderName.value = name;
      var reqOrderBy = objForm.orderBy.value;
      
      for(i=0; i<tArray.length; i++) {
         if(name == tArray[i] && (reqOrderBy == 'null' || reqOrderBy == orderDesc)) {
            objForm.orderBy.value = orderAsc;
         }else if(name == tArray[i] && reqOrderBy == orderAsc) {
            objForm.orderBy.value = orderDesc;
         }
      }
      objForm.action = "canon_E193_csEIAddressController.jsp";
      objForm.submit();
      return false;
   }

   function action_func1(iPageIndex) {
      document.addrSelectForm.action = "canon_E193_csEIAddressController.jsp";
      document.addrSelectForm.iPageNo.value =  iPageIndex;
      document.addrSelectForm.submit();
   }

   function action_func2() {
	   resetErroMsg();
      if ((document.addrSelectForm.selAcctId.length == 0) || (document.addrSelectForm.selAcctId.value == null)
            || (document.addrSelectForm.selAcctId.value == "")) {
    	  displayErrorMsg("Please select one of the addresses");
         document.addrSelectForm.selAcctId.focus();
         document.addrSelectForm.selAcctId.select();
      }else{
         document.addrSelectForm.action = "canon_E193_csEIAccountController.jsp";
         document.addrSelectForm.submit();
      }
   }
   
   function select_Func(acct_id, loc_id, acct_name, acct_num) {
      document.addrSelectForm.selAcctId.value = acct_id;
      document.addrSelectForm.selLocId.value = loc_id;
      document.addrSelectForm.selAcctName.value = acct_name;
      document.addrSelectForm.selAcctNum.value = acct_num;
   }
   
</script>

<div id="main_content">
   	<div id="page_top">
   		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strSelectAccountAddressT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>

   <form name="addrSelectForm" method="post">
   
   <!-- hidden variables -->
   <input type="hidden" name="origName" value="<%=request.getParameter("origName")%>" >
   <input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")%>">
   <input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
   <input type="hidden" name="origCheckbox" value="<%=request.getParameter("origCheckbox")%>">
   <input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
   <input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >
   <input type="hidden" name="custName" value="<%=request.getParameter("custName")%>" >
   <input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
   <input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
   
   <input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")%>">
   <input type="hidden" name="probType" value="<%=request.getParameter("probType")%>">
   <input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">

   <input type="hidden" name="address1" value="<%=request.getParameter("address1")%>">
   <input type="hidden" name="address2" value="<%=request.getParameter("address2")%>">
   <input type="hidden" name="city" value="<%=request.getParameter("city")%>">
   <input type="hidden" name="state" value="<%=request.getParameter("state")%>">
   <input type="hidden" name="zipCode" value="<%=request.getParameter("zipCode")%>">
   
   <input type="hidden" name="hPageFrom" value="EISelectAddress" >
   
   <input type="hidden" name="iPageNo" value="<%=request.getParameter("iPageNo")==null?"0":request.getParameter("iPageNo")%>">
   <input type="hidden" name="iTotPageNo" value="<%=request.getParameter("iTotPageNo")==null?"0":request.getParameter("iTotPageNo")%>">
   <input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
   <input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
   
   <input type="hidden" name="selAcctId">
   <input type="hidden" name="selLocId">
   <input type="hidden" name="selAcctName">
   <input type="hidden" name="selAcctNum">
   <input type="hidden" name="hPath" value="<%=hPath%>">
   <table class="request-service" cellspacing="1">

			<tr>
				<td colspan="8" style="text-align: left; padding-left: 30px;"><font color="#FF0000"><b><%=strSelectAccountAddressN1%></b></font></td>
			</tr>
			<tr>
			    <td colspan="8">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
			<tr>
               <th width="20">&nbsp;</th>
               <th width="200"><a href="#" onClick="return fnOrderBy(tArray[0])">Account Name</a></th>
               <th width="50"><a href="#" onClick="return fnOrderBy(tArray[1])">Account Number</a></th>
               <th width="180"><a href="#" onClick="return fnOrderBy(tArray[2])">Address1</a></th>
               <th width="170"><a href="#" onClick="return fnOrderBy(tArray[3])">Address2</a></th>
               <th width="150"><a href="#" onClick="return fnOrderBy(tArray[4])">City</a></th>
               <th width="30"><a href="#" onClick="return fnOrderBy(tArray[5])">State</a></th>
               <th width="80"><a href="#" onClick="return fnOrderBy(tArray[6])">Zip Code</a></th>
            </tr>
<%
            Canon_E193_AcctAddressObj objAddrObj = null;
            int iCountAddrList = 0;          
            ArrayList alAddrList = (ArrayList)objAddrArrayList.getArrayList();
            if(alAddrList != null && alAddrList.size() > 0) {
               String strAcctId = "", strLocId = "", strAcctName = "", strAcctNum = "";
               String strAddress1 = "", strAddress2 = "", strCity = "", strState = "", strPostalCode = "";
               String strAcctNameDisplay = "";
               iCountAddrList = alAddrList.size();
               for(int i = 0; i < iCountAddrList; i++) {
                  objAddrObj = (Canon_E193_AcctAddressObj)alAddrList.get(i);
                  
                  strAcctId = objAddrObj.getAcctID();
                  if (strAcctId == null)
                     strAcctId = "";
                  strLocId = objAddrObj.getLocID();
                  if (strLocId == null)
                     strLocId = "";
                  strAcctName = objAddrObj.getAcctName();
                  if (strAcctName == null)
                  {
                     strAcctName = "";
                  }
                  //Start - For Handling Single Quotes etc
                  else
                  {
                     //strAcctName = getHTMLString(strAcctName); // Removed because of space is stored as 49824 (ascii code) character
                     strAcctNameDisplay = getHTMLString(strAcctName);
                  }
                  //End - For Handling Single Quotes etc
                  strAcctNum = objAddrObj.getAcctNum();
                  if (strAcctNum == null)
                     strAcctNum = "";
                  strAddress1 = objAddrObj.getAddress1();
                  if (strAddress1 == null)
                     strAddress1 = "";
                  strAddress2 = objAddrObj.getAddress2();
                  if (strAddress2 == null)
                     strAddress2 = "";
                  strCity = objAddrObj.getCity();
                  if (strCity == null)
                     strCity = "";
                  strState = objAddrObj.getState();
                  if (strState == null)
                     strState = "";
                  strPostalCode = objAddrObj.getPostalCode();
                  if (strPostalCode == null)
                     strPostalCode = "";
%>             
                  <tr>
                     <td>
                        <input name="rSlctAddr" type="radio" value="<%=i%>" onClick='select_Func("<%=strAcctId%>","<%=strLocId%>","<%=strAcctName%>","<%=strAcctNum%>")'>
                     </td>
                     <td><%=strAcctNameDisplay%>&nbsp;</td>
                     <td><%=strAcctNum%>&nbsp;</td>
                     <td><%=strAddress1%>&nbsp;</td>
                     <td><%=strAddress2%>&nbsp;</td>
                     <td><%=strCity%>&nbsp;</td>
                     <td><%=strState%>&nbsp;</td>      
                     <td><%=strPostalCode%>&nbsp;</td>
                  </tr>
      <%       } // closed for loop
               int iTotPageNo = (int)(Integer.parseInt(request.getParameter("iTotPageNo")));
               int iPageNo = (int)(Integer.parseInt(request.getParameter("iPageNo")));
               if (iTotPageNo > 1) {
      %>
                  <tr class="tdTableCellStyle">
                     <td colspan="8"> Pages:  &nbsp; &nbsp;
      <%
                     for(int j=1;j<=iTotPageNo;j++) {
                        if(j == iPageNo) {
      %>                         
                           &nbsp;<%=j%>
      <%                            
                        }else{
      %>
                           &nbsp;<a href="javascript:action_func1(<%=j%>);"><%=j%></a>
      <%
                        }
                     }
      %>
                  </td>
               </tr>
      <%                         
               }
            }else{
      %>
               <tr>
                  <td colspan="8">
                     <b>Address not found for this value. Please revise your search criteria.</b>
                  </td>
               </tr>
<%
            }
%>           
   </table>
   
      <div style="text-align: center;">

					<a  href="javascript:history.back();" class="btn_red">Prev</a> 
					<%
   if(iCountAddrList == 0) {
%>                      
				<a class="btn_red disabled" href="#" >Next</a>                          
<%
                        }else{
%>
					<a class="btn_red" href="javascript:action_func2();">Next</a>                              
<%
                        }
%>																
</div>
   <br>
   </form> 
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>