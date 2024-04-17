<!-- $Header: canonE193DffUtility.jsp $ -->
<%--========================================================================
 |
 | FILE
 | canonE193DffUtility.jsp - JSP DFF Utility.
 |
 | DESCRIPTION
 |   Details of the JSP DFF Screen.
 |
 | AUTHOR
 | Chandra Sekhar
 |
 | CREATION DATE
 | 06-Apr-2009
 |
 | HISTORY
 | DATE         WHO               WHY
 |
 | 06-Apr-2009  Chandra Sekhar    ITG # 209125 - JSP DFF Utility.
 |
 +=======================================================================--%>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@ page language="java" import="com.canon.oracle.custapp.custinq.beans.CanonE193DffDataBean" %>
<%@ page language="java" import="java.util.Vector" %>

<% String ctxPath = request.getContextPath(); %>	
	<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csHomeVald.js"></script> 
	<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
	<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">  
	<link href="<%=ctxPath%>/common/jquery/jquery-ui.css" rel='stylesheet' type='text/css' />
    <%-- <script src="<%=ctxPath%>/common/jquery/jquery-1.10.2.min.js" type='text/javascript'></script>   		
  <script src="<%=ctxPath%>/common/jquery/jquery-ui-1.10.3.custom.min.js" type='text/javascript'></script> --%>
   <script src="<%=ctxPath%>/common/jquery/jquery-3.5.1.min.js" type='text/javascript'></script>
  <script src="<%=ctxPath%>/common/jquery/jquery-ui.min.js" type='text/javascript'></script>
   <script src="<%=ctxPath%>/e193/js/canon_E193PrintPdf.js" type='text/javascript'></script>
    <script src='<%=ctxPath%>/common/jquery/jquery.blockUI2.js' type='text/javascript'></script>	
	<script src='<%=ctxPath%>/common/jquery/jquery.timepicker.js' type='text/javascript' ></script>
    <link href='<%=ctxPath%>/common/jquery/jquery.timepicker.css' rel="stylesheet" type="text/css">
    <script type="text/javascript" src='<%=ctxPath%>/common/jquery/autoNumeric-1.6.2.js'></script>	
    <script src='<%=ctxPath%>/common/jquery/jquery-ui-timepicker-addon.js' type='text/javascript'></script>
    <script src='<%=ctxPath%>/common/jquery/ui.core.js' type='text/javascript'></script>
	
<%
   try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
		<% //Start changes for S21 by Mangala%>
      <jsp:useBean class="com.canon.oracle.custapp.custinq.beans.CanonE193DffUtilityBean" scope="request" id="CanonE193DffUtilityBean" />
     <% //End changes for S21 by Mangala%>
      <jsp:setProperty name="CanonE193DffUtilityBean" property="*" />
      <%
         String sourceApplication = request.getParameter("sourceApplication")==null?"":request.getParameter("sourceApplication");
         String sourceContext     = request.getParameter("sourceContext")==null?"":request.getParameter("sourceContext");
         String contextValue      = request.getParameter("contextValue")==null?"":request.getParameter("contextValue");
         String userId            = request.getParameter("userId")==null?"":request.getParameter("userId");
      %>
      <%!
        String blankNull(String s) {
          return ((s == null) || s.equals("")) ? "" : s;
        }
      %>
      <script>
      $(document).ready(function() {
  		$(".date-picker").datepicker({
  			dateFormat : 'mm/dd/yy',
  			changeMonth : true,
  			changeYear : true
  		});
  	});
        NLSformat = 'DD-MON-RRRR';  var allowPrompt = true; window.onbeforeunload = WarnUser; function WarnUser(){}    
        function Prompt() { allowPrompt = true; }        
        function radioButtonClick(attrId, attrVal, finalValue)
        {
          document.forms['canonE193DffUtility'].elements[attrId].value = finalValue;
          document.forms['canonE193DffUtility'].elements[attrVal].value = finalValue;
        }
		
        function OpenLinkDetails(documentId) {
          var vLink = "canonE193ActualDocumentLink.jsp?DocumentId=" + documentId;
          var vWin  = window.open(vLink,"_blank","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,alwaysRaised=yes");
          vWin.focus();
        }
        
		function checkNum(evn){
			var eve = event || evt; 
			var charCode = eve.which || eve.keyCode;
			if (charCode > 31 && (charCode < 48 || charCode > 57))
				return false;
			return true;			
		}        
				
      </script>
<!-- Modified Form name = "canonE389DffUtility" to  "canonE193DffUtility" -->
      <form action="canonE193DffUtility.jsp" name="canonE193DffUtility" id="canonE193DffUtility" method="post">
      <p id="msg"></p>
        <%
          java.util.ArrayList alDffResults = CanonE193DffUtilityBean.getDffDetails(sourceContext);
          //System.out.println("alDffResults = "+alDffResults);
          CanonE193DffDataBean DBInsert    = new CanonE193DffDataBean();

          int cnt = alDffResults.size();
          System.out.println("cnt = "+cnt);
          String errMsg       = DBInsert.getErrMsg();
          String Mode         = request.getParameter("Mode");
          System.out.println("errMsg = "+errMsg);
          System.out.println("Mode = "+Mode);
          Vector vAttrIds     = new Vector();
          Vector vAttrValues  = new Vector();
          Vector vDisplayData = new Vector();

          if (Mode != null && Mode.equals("insert"))
          {
        	  System.out.println("if (Mode != null");
            for (int idx = 0; idx < cnt; idx++)
            {
              String AttrIds    = request.getParameter("attributes"+idx);
              String AttrValues = request.getParameter("attrvalue"+idx);
              System.out.println("E193 Values -------> : " +AttrIds +" : " +AttrValues);

              CanonE193DffDataBean obj1 = new CanonE193DffDataBean();
              CanonE193DffDataBean obj2 = new CanonE193DffDataBean();

              obj1.setattributeId(AttrIds);
              obj2.setattributeValue(AttrValues);

              vAttrIds.add(obj1);
              vAttrValues.add(obj2);
            }

            if (vAttrIds.size() > 0)
            {
              System.out.println("E193 context " +sourceApplication+" "+sourceContext+" "+contextValue+" "+userId);
			  String xErrorMsg = DBInsert.UploadData(vAttrIds, vAttrValues, userId, sourceApplication, sourceContext, contextValue);
			  
              System.out.println("xErrorMsg : " +xErrorMsg);
              if (xErrorMsg == null || xErrorMsg.equals(""))
              {
        %>
           <font color="red" size=2><b>Changes Saved Successfully.</b></font>
        <%
              } else {
        %>
           <font color="red" size=2><b>Error : <%=xErrorMsg%></b></font>
        <%
              }
            }
          }

          vDisplayData = CanonE193DffDataBean.displayData (sourceApplication, sourceContext, contextValue);
          System.out.println("Any Error : " +errMsg);
        %>

        <input type="hidden" name="sourceApplication" id="sourceApplication" value="<%=sourceApplication%>">
        <input type="hidden" name="sourceContext"     id="sourceContext"     value="<%=sourceContext%>">
        <input type="hidden" name="contextValue"      id="contextValue"      value="<%=contextValue%>">
        <input type="hidden" name="userId"            id="userId"            value="<%=userId%>">
        <input type="hidden" name="Cnt"               id="Cnt"               value="<%=cnt%>">

        <table class="request-service" width="100%" cellspacing="0">

        <%

          for (int i = 0; i < alDffResults.size(); i++)
          {

            String bclr;
            if(i % 2 == 0)
               bclr = "eventableDataCell";
            else
               bclr = "oddtableDataCell";

            CanonE193DffUtilityBean objdff = (CanonE193DffUtilityBean)alDffResults.get(i);
            CanonE193DffUtilityBean objData = new CanonE193DffUtilityBean();
            objData = (CanonE193DffUtilityBean)vDisplayData.elementAt(i);

            String vAttrId    = objData.getattributeId();
            String vAttrValue = objData.getAttributeValue();

            String seqNumber           = objdff.getseqNumber();
            String prompt              = objdff.getprompt();
            String attributeId         = objdff.getattributeId();
            String requiredFlag        = objdff.getrequiredFlag();
            String enabledFlag         = objdff.getenabledFlag();
            String displayFlag         = objdff.getdisplayFlag();
            String securityEnabledFlag = objdff.getsecurityEnabledFlag();
            String valueSetName        = objdff.getvalueSetName();
            String defaultType         = objdff.getdefaultType();
            String defaultValue        = objdff.getdefaultValue();
            String displaySize         = objdff.getdisplaySize();

            String attrid = request.getParameter(attributeId);
            Vector vData = new Vector();

        %>

          <input type="hidden" name="prompt<%=i%>"       id="prompt<%=i%>"       value="<%=prompt%>">
          <input type="hidden" name="attributes<%=i%>"   id="attributes<%=i%>"   value="<%=attributeId%>">
          <input type="hidden" name="reqFlag<%=i%>"      id="reqFlag<%=i%>"      value="<%=requiredFlag%>">
          <input type="hidden" name="enabledFlag<%=i%>"  id="enabledFlag<%=i%>"  value="<%=enabledFlag%>">
          <input type="hidden" name="displayFlag<%=i%>"  id="displayFlag<%=i%>"  value="<%=displayFlag%>">
          <input type="hidden" name="valueSetName<%=i%>" id="valueSetName<%=i%>" value="<%=valueSetName%>">
          <input type="hidden" name="defaultValue<%=i%>" id="defaultValue<%=i%>" value="<%=defaultValue%>">
          <input type="hidden" name="attrvalue<%=i%>"    id="attrvalue<%=i%>"    value="<%=blankNull(vAttrValue)%>">
			<tr>
			    <td colspan="8">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
          <%
          if (displayFlag.equals("Y")) 
          {
          %>

          <tr>
            <td width="50%" align="left" class="<%= bclr %>"><font size=2><b><%=prompt%>
               <%if("Y".equalsIgnoreCase(requiredFlag)){%><font size=2 color="red">*</font><%}%> :
               </b></font>
            </td>

            <td width="50%" align="left" class="<%= bclr %>">
              <% if(defaultValue.equals("CHAR")) { %>
                 <% if (securityEnabledFlag.equals("Y")) { %>
                   <input type="text" size=<%=displaySize%> DISABLED name="<%=attributeId%>" value="<%=blankNull(vAttrValue)%>">
                 <%} else { %>
                   <input type="text" size=<%=displaySize%> <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>" value="<%=blankNull(vAttrValue)%>">
                 <% } %>
			  <% } else if (defaultValue.equals("NUMBER")) { %>
                 <% if (securityEnabledFlag.equals("Y")) { %>
                   <input type="text" size=<%=displaySize%> DISABLED name="<%=attributeId%>" value="<%=blankNull(vAttrValue)%>" onkeypress="return checkNum();">
                 <%} else { %>
                   <input type="text" size=<%=displaySize%> <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>" value="<%=blankNull(vAttrValue)%>" onkeypress="return checkNum();"> 
				  <% } %>
			  <% } else if (defaultValue.equals("LINK")) { %>
                   <% if (securityEnabledFlag.equals("Y")) { %>
                      <a DISABLED ><b>Click Here...</b></a>
                   <%} else { %>
                     <% if (blankNull(vAttrValue) == null || blankNull(vAttrValue) == "") { %>
                        <a><b>Click Here...</b></a>
                     <% } else { %>
                        <% if (enabledFlag.equals("N")) { %> <a><b>Click Here...</b></a> 
                        <% } else { %> 
                          <a href="javascript:void(0);" onclick="NoPrompt();OpenLinkDetails('<%=blankNull(vAttrValue)%>');"><b>Click Here...</b></a>
                        <% } %>
                     <% } %>
                   <% } %>

              <% } else if (defaultValue.equals("DATE")) { %>
                   <% if (securityEnabledFlag.equals("Y")) { %>
                     <input type="text" size=<%=displaySize%> DISABLED name="<%=attributeId%>" value="<%=blankNull(vAttrValue)%>">                     
                   <% } else { %>
                     <input type="text" size=<%=displaySize%> readonly name="<%=attributeId%>" id="<%=attributeId%>" value="<%=blankNull(vAttrValue)%>" class="date-picker">                    
                   <% } %>

              <% } else if (defaultValue.equals("RADIOBUTTON")) { %>
                   <% if (securityEnabledFlag.equals("Y")) { %>
                     <% if (blankNull(vAttrValue).equalsIgnoreCase("Y")){ %>
                        <input type="radio" DISABLED name="<%=attributeId%>"  value="Y" checked  onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'Y')"/> Yes &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" DISABLED name="<%=attributeId%>"  value="N" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'N')"/> No
                     <% } else if (blankNull(vAttrValue).equalsIgnoreCase("N")){ %>
                        <input type="radio" DISABLED name="<%=attributeId%>"  value="Y" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'Y')" /> Yes &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" DISABLED name="<%=attributeId%>"  value="N" checked onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'N')"/> No
                     <% } else { %>
                        <input type="radio" DISABLED name="<%=attributeId%>"  value="Y" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'Y')" /> Yes  &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" DISABLED name="<%=attributeId%>"  value="N" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'N')"/> No
                     <% } %>

                   <% } else { %>
                     <% if (blankNull(vAttrValue).equalsIgnoreCase("Y")){ %>
                        <input type="radio" <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>"  value="Y" checked  onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'Y')"/> Yes &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>"  value="N" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'N')"/> No
                     <% } else if (blankNull(vAttrValue).equalsIgnoreCase("N")){ %>
                        <input type="radio" <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>"  value="Y" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'Y')" /> Yes &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>"  value="N" checked onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'N')"/> No
                     <% } else { %>
                        <input type="radio" <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>"  value="Y" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'Y')" /> Yes  &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" <% if (enabledFlag.equals("N")) { %> DISABLED <% } else { %> <% } %> name="<%=attributeId%>"  value="N" onclick="radioButtonClick('<%=attributeId%>', 'attrvalue<%=i%>', 'N')"/> No
                     <% } %>
                   <% } %>

              <% } else if (defaultValue.equals("LOV")) { %>
                   <% if (securityEnabledFlag.equals("Y")) { int dSize = (Integer.parseInt(displaySize)*8); %>
                     <%=CanonE193DffUtilityBean.valueSetLovHTML(attributeId, valueSetName, vAttrValue, securityEnabledFlag, enabledFlag, dSize) %>
                   <% } else { int dSize = (Integer.parseInt(displaySize)*8); %>
                     <%=CanonE193DffUtilityBean.valueSetLovHTML(attributeId, valueSetName, vAttrValue, securityEnabledFlag, enabledFlag, dSize) %>
                   <% } %>

              <% } else if (defaultValue.equals("MINUTES")) { %>
                   <% int dSize = (Integer.parseInt(displaySize)*8); 
				   int hr;
				   int min;
				   String h = "";
				   String m = "";
				   if (vAttrValue != null && !vAttrValue.equals("")) {
				   hr = (int)Integer.parseInt(vAttrValue)/60;
				   min = Integer.parseInt(vAttrValue)%60;
				   h = Integer.toString(hr);
				   
				   if (min < 10) {m = "0"+min;}
				   else {m=m+min;}
					}	
				   %>
				   <input type="hidden" name="<%=attributeId%>" id="<%=attributeId%>" value="<%=vAttrValue%>">
                     <%=CanonE193DffUtilityBean.valueSetLovHTML("hours"+i, "CANON_E193_HOURS",h , securityEnabledFlag, enabledFlag, dSize) %>
					 <b>&nbsp:&nbsp</b>
                     <%=CanonE193DffUtilityBean.valueSetLovHTML("minutes"+i, "CANON_E193_MINUTES", m, securityEnabledFlag, enabledFlag, dSize) %>
            
              <% } %>

            </td>
          </tr>
          <% } %>
        <%
          }
        %>
        </table >

        <%
          if (alDffResults.size() > 0)
          {
        %>
             <br><td width="100%" align="left" class="colbr"><font size=2 color="red"><b>* Required Fields</b></font></td>
        <%
          }
        %>
        <br>
        <table width="100%" border="0" cellspacing="6" cellpadding="3">
          <%
            if (alDffResults.size() == 0)
            {
          %>
            <tr><td width="100%" align="left"> <b><font color="red" size="2">
                  No Additional Attributes Defined. Please Close The Window. </font></b>
                </td>
            </tr><br><br>

            <tr><td width="70%" align="center"> 
            <!-- <input type="button" name="Close" id="Close" size="15" value="&nbsp;&nbsp;&nbsp;&nbsp;Close&nbsp;&nbsp;&nbsp;&nbsp;"  onClick="NoPrompt();javascript:window.close();" /> -->
            
            <a name="Close" id="Close" class="btn_red" href="javascript:closeDlg();" >Close</a>
                </td>
            </tr>

          <% } else { %>

            <td width="50%" align="right"> 
            <!-- <input type="button" name="Save" id="Save" size="15" value="&nbsp;&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;&nbsp;" onClick="NoPrompt();checkAllData();" /> -->
            
            <a class="btn_red" href="#" name="Save" id="Save" onclick="NoPrompt();checkAllData('canonE193DffUtility');">Save</a>
            
            </td>

            <td width="50%" align="left"> 
            <!-- <input type="button" name="Close" id="Close" size="15" value="&nbsp;&nbsp;&nbsp;&nbsp;Close&nbsp;&nbsp;&nbsp;&nbsp;"   onClick="javascript:window.close();" /> -->
                <a name="Close" id="Close" class="btn_red" href="javascript:$('.ui-dialog-titlebar-close').trigger('click');" >Close</a> 
            </td>
          <% } %>

          <input type="hidden" name="Mode" id= "Mode" value="">
        </table>

      


<%
   }
   catch (Exception eExp) {
%>
     <br><br><br>
     <b><font color="red" size=2 class="colbr">
            An unexpected error occurred. Please close this page and re-query the line attributes by clicking on the link.
        </font></b><br><br>
        <% out.println("Error Message : " +eExp.toString()); %>
     <table>
     <tr><td> <input type="button" name="Close" id="Close" size="15" value="&nbsp;&nbsp;&nbsp;&nbsp;Close&nbsp;&nbsp;&nbsp;&nbsp;"
            onClick="javascript:closeDlg();" />
         </td>
     </tr>
     </table>
<%
   }
%>


</form>