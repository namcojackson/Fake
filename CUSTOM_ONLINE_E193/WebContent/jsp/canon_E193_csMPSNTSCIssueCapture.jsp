<!-- $Header: S21 canon_E193_csMPSNTSCIssueCapture.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csMPSNTSCIssueCapture - MPS-NTSC issue capture page
 |   
 | DESCRIPTION
 |   Page to capture the details of the MPS-NTSC issue.
 |
 | AUTHOR
 |	Mangala Shenoy
 |
 | CREATION DATE
 |	04/08/2016
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_SystemDAO" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Severity" id="objSeverityDao" scope="page"/>
<%@ include file="canon_E193_csTopInc.jsp" %>

<%
	// Menu Prompt
	strPageName = "MPS-NTSC Issue Capture";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strMPSNTSCIssueCaptureT1;
	else
		if(hPath.indexOf(strMPSNTSCIssueCaptureT1) < 0)
			hPath = hPath + " -> " + strMPSNTSCIssueCaptureT1;	
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>




<%-- Obtain the CFS cat id and parent catId : Begin --%>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_IssueList" id="objIssueList" scope="page"/>
<%
String hParentCatCode = "NONBILL";
String hCatId         = "686";//null;// "600"; 
String hParentCatId   = "106";//null;//"500";
String hCatDesc       = "MPS - NTSC";//null;//"CFS Cost Per Copy";

 
//Get Org ID
int iOrgId = objCiSession.getOrgId();

String cfs_access_flag = objCiSession.getCFSAccessFlag(); 
String cfs_user_flag   = objCiSession.getCFSUserFlag();
if (cfs_user_flag == null || "".equalsIgnoreCase(cfs_user_flag)) cfs_user_flag="N";
String errMsgCFSCatId  = null;

/* ITG# 73987 : Begin */	
String strRegionCode = (String)objCiSession.getRegionCode();	
/* ITG# 73987 : End */

/* ITG# 149678 : Begin */	
//if((strRegionCode==null || "".equals(strRegionCode.trim()) || "null".equals(strRegionCode.trim())) && (cfs_user_flag!=null && "Y".equalsIgnoreCase(cfs_user_flag.trim())))
// all CFS tickets are created under EAST_REGION
if((strRegionCode==null || "".equals(strRegionCode.trim()) || "null".equals(strRegionCode.trim())))
  strRegionCode = "EAST_REGION";
/* ITG# 149678 : End */


ArrayList alIssueList = new ArrayList();

/* ITG# 73987 : Begin */	
//alIssueList = objIssueList.getIssueList(iOrgId, hParentCatCode, 0);
//Start changes for S21 by Mangala
//alIssueList = objIssueList.getIssueList(iOrgId, strRegionCode, hParentCatCode, 0);
//End changes for S21 by Mangala		
/* ITG# 73987 : End */	

/* Changed For ITG # 156613 By Chandra Sekhar - Start */
//Start changes for S21 by Mangala
//ArrayList alCategoryList = new ArrayList();
//alCategoryList = objIssueList.getCategories(strRegionCode, hParentCatCode, cfs_user_flag, cfs_access_flag);
//End changes for S21 by Mangala
/* Changed For ITG # 156613 By Chandra Sekhar - End */

%> 

<!-- Added For ITG # 156613 By Chandra Sekhar - Start -->
<script language="JavaScript">
function createMpsNtsTicket() {
	$("#createButton").attr("disabled", true);
	$("#createButton").removeClass("btn_red");
	$("#createButton").addClass("btn_disabled");
	var isCorrectlyEnterDigit = true;
    var reqSelector=".required, .requireds"; 
    var objForm = document.cfsBillingIssueForm;
	   if(!validateParams(reqSelector)){
		   
		   if(!validatePhNum(objForm.origPhCd)) {
		         return;
		      }else if(!validatePhNum(objForm.origPhNo)) {
		         return ;
		      }else if(!validatePhNum(objForm.origPhNo2)) {
		         return;  
//		       }else if(!validatePhNum(objForm.origPhNoExt)) {
//		          return false;
		      }else if(objForm.origPhCd.value != '' && objForm.origPhCd.value.length < 3) {
		         alert("Area code should be 3 digits");
		         objForm.origPhCd.focus();
		         objForm.origPhCd.select();
		         //return false;
		         isCorrectlyEnterDigit = false;
		      }else if(objForm.origPhNo.value != '' && objForm.origPhNo.value.length < 3) {
		         alert("Phone no should be 3 digits");
		         objForm.origPhNo.focus();
		         objForm.origPhNo.select();
		        // return false;
		         isCorrectlyEnterDigit = false;
		      }else if(objForm.origPhNo2.value != '' && objForm.origPhNo2.value.length < 4) {
		         alert("Phone no should be 4 digits");
		         objForm.origPhNo2.focus();
		         objForm.origPhNo2.select();
		        // return false;
		         isCorrectlyEnterDigit = false;
		      }
		   // Newly Added
		   var firstName=document.cfsBillingIssueForm.origFirstName.value;
		   var lastName=document.cfsBillingIssueForm.origLastName.value;
		   var origName = firstName + " " + lastName;
		   document.cfsBillingIssueForm.origName.value=origName;
		   // Newly Added END
		   if(objForm.iLineId.value != '' && objForm.iLineId.value != '0' && isCorrectlyEnterDigit === true) {
				var notes = objForm.notes.value;
				var existingNotes = objForm.notesDisplay.value;
				if((notes.length + existingNotes.length) > 32000){
					alert("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
					objForm.notes.focus();
				}else {
	               objForm.flag.value = "update";
	               objForm.readData.value = "yes";
	               objForm.submit();
				}
	           
	       }else if(isCorrectlyEnterDigit === true){
	    	   	var notes = objForm.notes.value;
				if (notes.length > 32000) {
					alert("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
					objForm.notes.focus();
				} else {
					objForm.submit();
				}
			}
		}
	   $("#createButton").attr("disabled", false);
	  	$("#createButton").removeClass("btn_disabled");
	  	$("#createButton").addClass("btn_red");
	}

	function validatePhNum(thisObj) {
		if (isNaN(thisObj.value)) {
			alert("Please enter numbers only.");
			thisObj.focus();
			thisObj.select();
			return false;
		}
		//Start Changes for S21 by Mangala to handle dot in phone number field
		else {
			for (var i = 0; i < thisObj.value.length; i++) {
				vChar = thisObj.value.charAt(i);
				if (vChar == ".") {
					alert("Please enter numbers only.");
					thisObj.focus();
					thisObj.select();
					return false;
				}
			}
		}
		//End Changes for S21 by Mangala
		return true;
	}

	function getReasonCode(code) {
		document.cfsBillingIssueForm.hParentCatId.value = code;
	}

	/*function openReasonCode() {
	 var val = document.cfsBillingIssueForm.hParentCatCode.value;
	 var vWin = window.open("canon_E193_csTicketReasonCodeP.jsp?reasonCode="+val, "newwin","height=300,width=940,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
	 vWin.focus();
	 }*/

	function action_func1ReasonCode() {
		resetErroMsg();
		var objForm = document.ReasonCodeForm;
		var isSelected = false;
		var val = '';
		var v = 0;
		if (objForm.thdetails != null) {
			v = objForm.thdetails.length;
			if (v > 0) {
				for (i = 0; i < v; i++) {
					if (objForm.thdetails[i].checked) {
						isSelected = true;
						val = objForm.thdetails[i].value;
						break;
					}
				}
			} else if (objForm.thdetails.checked) {
				val = objForm.thdetails.value;
				isSelected = true;
			}
		}
		if (!isSelected) {
			displayErrorMsg("Please select the reasonCode");
		} else {
			getReasonCode(objForm.reasonCode.value, val);
			closeDlg();
		}
	}

	function rsnCodeRefresh() {
		//document.ReasonCodeForm.submit();

		var qryStr = "reasonCode="
				+ encodeURIComponent($('select[name="reasonCode"]').val());
		var modelName = "#dlg";

		$.ajax({
			url : "canon_E193_csTicketReasonCodeP.jsp",
			data : qryStr,
			type : "POST",
			cache : false,
			cached : false,
			success : function(data) {
				hidePleaseWait();
				$(modelName).html("");
				$(modelName).html(data);
				hidePleaseWait();
			}
		});

	}
</script>
<!-- Added For ITG # 156613 By Chandra Sekhar - End -->
<script language="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csHomeVald.js"></script>

<%
	//Get Org ID
	/// int iOrgId = objCiSession.getOrgId();
	
	// getting severity codes for drop down list
	ArrayList alSeverityList = objSeverityDao.getSeverity();
%> 

	<div id="main_content">
	<div id="page_top">	   		
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strMPSNTSCIssueCaptureT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>   
     <!-- canon_E193_csCFSTicketController from canon_E193_csTicketSummaryNBController.jsp-->
     <form name="cfsBillingIssueForm" id="cfsBillingIssueForm" action="canon_E193_csCFSTicketController.jsp" method="post">

	<input type="hidden" name="hPageFrom" value="MPSNTSCIssueCapture">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="hReasonCd" value="<%=request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd")%>">
	
	<input type="hidden" name="hStrPageName" id="hStrPageName" value="MPS-NTSC Issue Capture">
        <%-- Kireet: Begin --%>
	<input type="hidden" name="hCatId" value="<%=hCatId%>">
	<input type="hidden" name="hParentCatId" value="<%=hParentCatId%>">
	<input type="hidden" name="hParentCatCode" value="<%=hParentCatCode%>">
	<input type="hidden" name="hCatDesc" value="<%=hCatDesc%>">
        <%-- Kireet: End --%>

	<input type="hidden" name="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">
	<input type="hidden" name="iLineId" id="iLineId" value="<%=request.getParameter("iLineId")==null?"":request.getParameter("iLineId")%>">
	<input type="hidden" name="flag" value="">
	<input type="hidden" name="readData" value="">
        
	<input type="hidden" name="origType" value="<%=("Y".equalsIgnoreCase(cfs_user_flag)?"thirdParty":"Internal")%>"> <!--"< %=request.getParameter("origType")% >" -->
		
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >	
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")%>" >
	<input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
	
	<input type="hidden" name="recurProb" value="No"> <!--- =request.getParameter("recurProb")% >" --->
	<input type="hidden" name="probType" value=""> <!--- %=request.getParameter("probType")% >" --->
	<input type="hidden" name="val1" value=""> <!--- %=request.getParameter("val1")% >" --->
	
	<input type="hidden" name="selAcctId" value="-99"> <!--- %=request.getParameter("selAcctId")% >" --->
	<input type="hidden" name="selLocId" value="-1"> <!--- %=request.getParameter("selLocId") %>" --->
	 <input type="hidden" name="selAcctName" value=""> <!--- %=request.getParameter("selAcctName") %>" --->
  <!-- 	<input type="hidden" name="selAcctName" value="<%=("Y".equalsIgnoreCase(cfs_user_flag)?"CBS INC":"CFS INC")%>"> -->
	<!--  <input type="hidden" name="selAcctNum" value=""> <!--- %=request.getParameter("selAcctNum") %>" --->
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<input type="hidden" name="category" value="MPS - NTSC"> 
	<!-- <div style="margin-left:40px; margin-top: 50px"><font size="5" color="blue"><b>MPS-NTSC Issue Capture:</b></font></div> -->
	<table class="request-servicehome" style="align:center;" cellspacing="0">	  
	<tr><td height="10" colspan="2"></td></tr>

        <!-- First Name, Last Name, Ph No. info... Begin -->
        <tr>
          <td colspan=2>
           <table>
			<tr>
				<td colspan="3">
					<div id="errorWidget"
						style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
					</div>
				</td>
			</tr>
		  <tr>
		      <td width="10"></td>
		      <td><b>First Name:</b></td>
		      <td> <!-- align="left"> -->
		      <input name="origFirstName" id="origFirstName" type="text" size="35" maxlength="60" class="inTxt required" style="background-color:#ffff00">			 
		      </td>
	      </tr>
	      <tr>
		      <td width="10"></td>
		      <td> <!-- align="left"> -->
			<b> Last Name: </b>
		      </td>
		      <td> <!-- align="left"> -->
		      <input name="origLastName" id="origLastName" type="text" size="35" maxlength="60" class="inTxt required" style="background-color:#ffff00">			 			
		      </td>
	      </tr>
	      <input type="hidden" name="origName" value="">
	      <tr>
		      <td width="10"></td>
		      <td> <b> Number: </b> 
		      </td>
		      <td> <!-- align="left"> -->
		       <input name="origPhCd" id="origPhCd" type="text" size="3" maxlength="3" class="inTxt required" onkeyup="fnNextTab(this)" style="background-color:#ffff00;width:140px;" >
			 <input name="origPhNo" id="origPhNo" type="text" size="3" maxlength="3" class="inTxt required" onkeyup="fnNextTab(this)" style="background-color:#ffff00;width:140px;" >
			 <input name="origPhNo2" id="origPhNo2" type="text" size="4" maxlength="4" class="inTxt required" onkeyup="fnNextTab(this)" style="background-color:#ffff00;width:140px;" >
			 <font class="promptReadOnly"><b>Ext</b></font>
			 <input name="origPhNoExt" type="text" size="5" maxlength="5" class="inTxt" style="width:90px;">
		      </td>
	      </tr>
	      <tr>
		      <td width="10"></td>
		      <td> <!-- align="left"> -->
			 	<b>Email Address:</b>
		      </td>
		      <td> <!-- align="left"> -->
			 <input name="origEmailAddress" id="origEmailAddress" type="text" size="35" maxlength="60" class="inTxt" onChange="validateEmail(this)">
		      </td>
	      </tr>

	      <tr>
		      <td width="10"></td>
		      <td> 
			 	<b>Serial Number:</b>
		      </td>
		      <td> <!-- align="left"> -->
			 <input name="origCFSSerialNumber" id="origCFSSerialNumber" type="text"  size="15" maxlength="30" class="inTxt">
		      </td>
	      </tr>


              <!-- NFirst Name, Last Name, Ph No. info... End -->
          </table>
        </td>
      </tr>

  		
	<tr>
	<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td class="tableQuestionCell" colspan="2">
			<font class="promptReadOnly" color="#FF0000">
				<b><%=strNBIssueCaptureN1%></b>
			</font>
		</td>
	</tr>
    	<tr>
    		<!-- <td colspan="2">&nbsp;</td> -->
  	</tr>
  	<tr>
		<td width="10">&nbsp;</td>
		<td>
			<table cellspacing="1" class="supplies-tablehome" style="width:800px;">
				<tr>
					<th><a href="javascript:void(0)" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','<%=hParentCatCode%>','Reason Code');">Reason Code</a><%=strReasonCodeN1%></th>
					<th>Urgency</th>
				</tr>
				<tr>
					<td><input type="text" name="reasonCdDesc" id="reasonCdDesc" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','<%=hParentCatCode%>','Reason Code');" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>" class="inTxt required" style="background-color:#ffff00" size="80" readonly=true></td>
					<td>
						<select name="severity" size="1" class="searchBarLink">
						<%
							if(alSeverityList != null && alSeverityList.size() > 0) {
								String reqSeverity = request.getParameter("severity")==null?"":request.getParameter("severity");
								String code = "";
								for(int i=0; i<alSeverityList.size(); i++) {
									code = alSeverityList.get(i).toString();
						%>
									<option value="<%=code%>" <%=code.equals(reqSeverity)?"selected":""%>><%=code%></option>
						<%		}
							}
						%>
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
    	<tr>
    		<!-- <td colspan="2">&nbsp;</td> -->
  		</tr>
   		<tr>
   			<td class="tableQuestionCell" colspan="2">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strNBIssueCaptureN2%></b>
   				</font>
   			</td>
   		</tr>
    	<tr>
    		<!-- <td colspan="2">&nbsp;</td> -->
  		</tr>
  		<%
  			String strNotesDisplay = request.getParameter("notes")==null?"":request.getParameter("notes");
  			if(!"".equalsIgnoreCase(strNotesDisplay)) {
  		%>
			<tr>
				<td width="10">&nbsp;</td>
				<td><textarea name="notesDisplay" id="notesDisplay" rows="10" style="height:auto; width:800px;background-color:#ffff00" wrap="OFF" class="inTxt required" readonly="readonly"><%=strNotesDisplay%></textarea></td>		
			</tr>
		<%	}  %>
		<tr>
			<td width="10">&nbsp;</td>
			<td><textarea name="notes" id="notes" rows="10" style="height:auto; width:800px;background-color:#ffff00" wrap="OFF" class="inTxt required" ></textarea></td>		
		</tr>		
	</table>
	</form>
	<div style="text-align: center; margin-bottom: 5px">
	  <a id="createButton" class="btn_red" href="javascript:createMpsNtsTicket();">Create Ticket</a>		
	</div>
		<div id="dlg"></div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
<!-- canon_E193_csCFSIssueCapture.jsp : End -->
