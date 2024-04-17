<!-- $Header: canon_E193_csEISelectAddress.jsp $ -->
<%--========================================================================
 |
 | FILE 
 | canon_E193_csEIAccountList.jsp - Select Accounts
 | 
 | AUTHOR
 | Q11607 
 |
 | CREATION DATE
 | 04/18/2019
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

<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objAcctArrayList" scope="request" />

<%@ include file="canon_E193_csTopInc.jsp" %>

<% 
   // Menu Prompt
   strPageName = "Enter & Inquiry";
   
   // Check page from to show the path
   strPageFrom = request.getParameter("hPageFrom");
   String hPath = request.getParameter("hPath");
   String strProbType = request.getParameter("probType");
   
   if (hPath == null)
      hPath = strSelectAccountT1;
   else
      if(hPath.indexOf(strSelectAccountT1) < 0)
         hPath = hPath + " -> " + strSelectAccountT1;   
%>
 
<%@ include file="canon_E193_csMenuInc.jsp" %> 

<script language="JavaScript">
   var orderAsc = "asc";
   var orderDesc = "desc";
   var tArray = new Array("acctName", "acctNum", "serialNum", "modelNum");
   
   function fnOrderBy(name) {
      var objForm = document.acctSelectForm;
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
      objForm.action = "canon_E193_csEIAccountListController.jsp";
      objForm.submit();
      return false;
   }

   function action_func1(iPageIndex) {
      document.acctSelectForm.action = "canon_E193_csEIAccountListController.jsp";
      document.acctSelectForm.iPageNo.value =  iPageIndex;
      document.acctSelectForm.submit();
   }

   function action_func2() {
	   resetErroMsg();
      if ((document.acctSelectForm.selAcctId.length == 0) || (document.acctSelectForm.selAcctId.value == null)
            || (document.acctSelectForm.selAcctId.value == "")) {
    	  displayErrorMsg("Please select one of the acount");
         document.acctSelectForm.selAcctId.focus();
         document.acctSelectForm.selAcctId.select();
      }else{
         document.acctSelectForm.action = "canon_E193_csEIAccountController.jsp";
         document.acctSelectForm.submit();
      }
   }
   
   function select_Func(acct_id, acct_name, acct_num, serial_num, model_num, po_num) {
      document.acctSelectForm.selAcctId.value = acct_id;
      document.acctSelectForm.selAcctName.value = acct_name;
      document.acctSelectForm.selAcctNum.value = acct_num;
      document.acctSelectForm.selSerialNum.value = serial_num;
      document.acctSelectForm.selModelNum.value = model_num;
      document.acctSelectForm.selPONum.value = po_num;
   }
   
   $(document).ready(
			function() {
				if(!($("input[name='rSlctAddr']").is(':checked')))
				{
					$('#rSlctAddr0').attr('checked', true);
					$('#rSlctAddr0').trigger( "click" );
				}
			});
   
</script>

<div id="main_content">
   	<div id="page_top">
   		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strSelectAccountT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>

   <form name="acctSelectForm" method="post">
   
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
   <input type="hidden" name="probType" value="<%=strProbType%>">
   <input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">

   <input type="hidden" name="hPageFrom" value="EIAccountList" >
   
   <input type="hidden" name="iPageNo" value="<%=request.getParameter("iPageNo")==null?"0":request.getParameter("iPageNo")%>">
   <input type="hidden" name="iTotPageNo" value="<%=request.getParameter("iTotPageNo")==null?"0":request.getParameter("iTotPageNo")%>">
   <input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
   <input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
   
   <input type="hidden" name="selAcctId">
   <input type="hidden" name="selAcctName">
   <input type="hidden" name="selAcctNum">
   <input type="hidden" name="selSerialNum">
   <input type="hidden" name="selModelNum">
   <input type="hidden" name="selPONum">
   <input type="hidden" name="hPath" value="<%=hPath%>">
   <table class="request-service" cellspacing="1" style="padding-right: 20%;">

			<tr>
				<td colspan="8" style="text-align: left; padding-left: 30px;"><font color="#FF0000"><b><%=strSelectAccountT1%></b></font></td>
			</tr>
			<tr>
			    <td colspan="8">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
			<tr>
               <th width="5%">&nbsp;</th> <!-- <a href="#" onClick="return fnOrderBy(tArray[0])"> -->
               <th width="35%">Account Name</th>
               <th width="20%">Account Number</th>
               <%
               		if (strProbType != null
               				&& strProbType.equalsIgnoreCase("sernum")) {
               %>
		               <th width="20%">Serial Number</th>
		               <th width="20%">Model</th>
               <%
               		} else if (strProbType != null
               				&& strProbType.equalsIgnoreCase("ponum")) {
               %>
               			<th width="20%">PO Number</th>
               <% 
               		}
                %>
            </tr>
<%
			Canon_E193_AccountDetails objAcctDetails = null;
            int iCountAddrList = 0;          
            ArrayList alAddrList = (ArrayList)objAcctArrayList.getArrayList();
            if(alAddrList != null && alAddrList.size() > 0) {
               String strAcctId = "", strAcctName = "", strAcctNum = "", strSerialNum = "", strModelNum= "", strPONum = "";
               
               String strAcctNameDisplay = "";
               iCountAddrList = alAddrList.size();
               for(int i = 0; i < iCountAddrList; i++) {
            	   objAcctDetails = (Canon_E193_AccountDetails)alAddrList.get(i);
                  
                  strAcctId = objAcctDetails.getAcctId();
                  if (strAcctId == null)
                     strAcctId = "";
                  strAcctName = objAcctDetails.getAcctName();
                  if (strAcctName == null)
                  {
                     strAcctName = "";
                  }
                  //Start - For Handling Single Quotes etc
                  else
                  {
                     strAcctNameDisplay = getHTMLString(strAcctName);
                  }
                  //End - For Handling Single Quotes etc
                  strAcctNum = objAcctDetails.getAcctNum();
                  if (strAcctNum == null)
                     strAcctNum = "";
                  
                  strSerialNum = objAcctDetails.getSerialNum();
                  if (strSerialNum == null)
                	  strSerialNum = "";
                  
                  strModelNum = objAcctDetails.getModel();
                  if (strModelNum == null)
                	  strModelNum = "";
                 
                  strPONum = objAcctDetails.getPoNum();
                  if (strPONum == null)
                	  strPONum = "";
%>             
                  <tr>
                     <td>
                        <input name="rSlctAddr" type="radio" id="rSlctAddr<%=i%>" value="<%=i%>" onClick='select_Func("<%=strAcctId%>","<%=strAcctName%>","<%=strAcctNum%>",
                        		"<%=strSerialNum%>","<%=strModelNum%>", "<%=strPONum%>")'>
                     </td>
                     <td><%=strAcctNameDisplay%>&nbsp;</td>
                     <td><%=strAcctNum%>&nbsp;</td>
                     <% if(strProbType != null
                				&& strProbType.equalsIgnoreCase("sernum")) 
                     	{
                     %>
                     <td><%=strSerialNum%>&nbsp;</td>
                     <td><%=strModelNum%>&nbsp;</td>
                     <%
               			} else if (strProbType != null
               				&& strProbType.equalsIgnoreCase("ponum")) {
               		 %>
               		 <td><%=strPONum%>&nbsp;</td>
               		 <% 
		               	}
		             %>
                    
                  </tr>
      <%       } // closed for loop
               /* int iTotPageNo = (int)(Integer.parseInt(request.getParameter("iTotPageNo")));
               int iPageNo = (int)(Integer.parseInt(request.getParameter("iPageNo")));
               if (iTotPageNo > 1) { */
      %>
                  <!-- <tr class="tdTableCellStyle">
                     <td colspan="8"> Pages:  &nbsp; &nbsp; -->
      <%
                    /*  for(int j=1;j<=iTotPageNo;j++) {
                        if(j == iPageNo) { */
      %>                         
                          <%--  &nbsp;<%=j%> --%>
      <%                            
                        /* }else{ */
      %>
                         <%--   &nbsp;<a href="javascript:action_func1(<%=j%>);"><%=j%></a> --%>
      <%
                     /*    }
                     } */
      %>
                 <!--  </td>
               </tr> -->
      <%                         
              /*  } */
            }else{
      %>
               <tr>
                  <td colspan="8">
                     <b>Account not found for this value. Please revise your search criteria.</b>
                  </td>
               </tr>
<%
            }
%>           
   </table>
   
      <div align="center" style="text-align: center; margin-bottom: 5px; padding-right: 20%;">

					<a  href="javascript:history.back();" class="btn_red" >Prev</a> 
					<%
   if(iCountAddrList == 0) {
%>                      
				<a class="btn_red disabled" href="#" >Next</a>                          
<%
                        }else{
%>
					<a class="btn_red" href="javascript:action_func2();" >Next</a>                              
<%
                        }
%>																
</div>
   <br>
   </form> 
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>