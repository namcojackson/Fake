<!-- $Header: ITG# 74988 canon_E193_csCFSIssueCapture.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csCFSIssueCapture - CFS issue capture page
 |   
 | DESCRIPTION
 |   Page to capture the details of the CFS issue.
 |
 | AUTHOR
 |	Kireet K Bollam 
 |
 | CREATION DATE
 |	05/12/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 | 7-Mar-2007		Kireet K Bollam			ITG: 73987 Passing region_code
 |							while retriving Issue List .
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
	strPageName = "CFS Cost Per Copy - Care";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strCFSIssueCaptureT1;
	else
		if(hPath.indexOf(strCFSIssueCaptureT1) < 0)
			hPath = hPath + " -> " + strCFSIssueCaptureT1;	
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>




<%-- Obtain the CFS cat id and parent catId : Begin --%>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_IssueList" id="objIssueList" scope="page"/>
<%
String hParentCatCode = "CFS";
String hCatId         = null;// "600"; 
String hParentCatId   = null;//"500";
String hCatDesc       = null;//"CFS Cost Per Copy";


//Get Org ID
int iOrgId = objCiSession.getOrgId();

String cfs_access_flag = objCiSession.getCFSAccessFlag(); 
String cfs_user_flag   = objCiSession.getCFSUserFlag();
if (cfs_user_flag == null || "".equalsIgnoreCase(cfs_user_flag)) cfs_user_flag="N";
final String TO_CFS    = "TO CFS";
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
//System.out.println("iOrgId:"+iOrgId+"strRegionCode: "+strRegionCode+" hParentCatCode:"+hParentCatCode);
alIssueList = objIssueList.getIssueList(iOrgId, strRegionCode, hParentCatCode, 0);
/* ITG# 73987 : End */	

/* Changed For ITG # 156613 By Chandra Sekhar - Start */
ArrayList alCategoryList = new ArrayList();
System.out.println("strRegionCode:"+strRegionCode+"hParentCatCode: "+hParentCatCode+" cfs_user_flag:"+cfs_user_flag+" cfs_access_flag:"+cfs_access_flag);
alCategoryList = objIssueList.getCategories(strRegionCode, hParentCatCode, cfs_user_flag, cfs_access_flag);
/* Changed For ITG # 156613 By Chandra Sekhar - End */

%> 

<!-- Added For ITG # 156613 By Chandra Sekhar - Start -->
<script language="JavaScript">
   var isCategorySelected = false;
   function category_func(){
       var cnt = <%=alIssueList.size()%>;
       
	   var cfsUserFlag = "<%=cfs_user_flag%>";
	   var cfsAccessFlag = "<%=cfs_access_flag%>";
       var vCtgArray = new Array(cnt);
      /*  alert(" In category_func()= cnt= " +cnt); */
       <%
          Canon_E193_IssueListObj objIssue = null;
          int j = alIssueList.size();
          
          for(int i=0; i<j; i++) {      // for loop 1
       %>
       var z = <%=i%>;
      /*  alert(" In category_func()= z=  " + z);  */
       vCtgArray[z] = new Array(4);
       <%
          objIssue = (Canon_E193_IssueListObj)alIssueList.get(i);
       %>
       var ctgInd =0;
       for(;ctgInd<4;){                 // for loop 2
           if (ctgInd == 0)
           {
              vCtgArray[z][ctgInd] = "<%=objIssue.getCatId()%>";
              <%-- alert(" In category_func()= getCatId= " + <%=objIssue.getCatId()%>);  --%>
           }
           else if (ctgInd == 1)
           {
              vCtgArray[z][ctgInd] = "<%=objIssue.getParentCatId()%>";
              <%-- alert(" In category_func()= getParentCatId= " + <%=objIssue.getParentCatId()%>); --%>
           }
           else if (ctgInd == 2)
           {
              vCtgArray[z][ctgInd] = "<%=objIssue.getCatCode()%>";
           <%--  //  alert(" In category_func()= getCatCode" + <%=objIssue.getCatCode()%>); --%>
           }
           else if (ctgInd == 3)
           {
              vCtgArray[z][ctgInd] = "<%=objIssue.getCatDesc()%>";
              <%-- alert(" In category_func()= getCatDesc" + <%=objIssue.getCatDesc()%>); --%>
           }
           ctgInd=ctgInd+1;
       }    // for loop 2
<%
       }    // for loop 1
%>
       var iCategory = document.cfsBillingIssueForm.category.value;
       if ("Select One" == iCategory)
       {
    	   displayErrorMsg("Please Select Category Other Than  'Select One'.");
       }
       else
       {
          for (var i=0; i<vCtgArray.length; i++)
          {		/* alert(" Inside For Loop: vCtgArray[i][3]= " + vCtgArray[i][3].toString()); */
        	  // for loop 3
        	  var iCategoryValue = vCtgArray[i][3].toString();
             if (cfsAccessFlag == "Y" && cfsUserFlag == "N" && iCategoryValue === iCategory /*&& 
                   vCtgArray[i][2].toUpperCase().indexOf("TO CFS") >= 0 */)
             {
			    document.cfsBillingIssueForm.hCatId.value       = vCtgArray[i][0];
                document.cfsBillingIssueForm.hParentCatId.value = vCtgArray[i][1];
                
                document.cfsBillingIssueForm.hCatDesc.value     = vCtgArray[i][3];
                /* alert(" hParentCatId.value= " + document.cfsBillingIssueForm.hParentCatId.value + " hCatId.value= "+ document.cfsBillingIssueForm.hCatId.value); */
				break;
             }
             else if (cfsAccessFlag == "Y" && cfsUserFlag == "Y" && iCategoryValue === iCategory /*&& 
                       vCtgArray[i][2].toUpperCase().indexOf("TO CBS") >= 0 */)
             {
			    document.cfsBillingIssueForm.hCatId.value       = vCtgArray[i][0];
                document.cfsBillingIssueForm.hParentCatId.value = vCtgArray[i][1];
                document.cfsBillingIssueForm.hCatDesc.value     = vCtgArray[i][3];
               /*  alert(" hParentCatId.value1= " + document.cfsBillingIssueForm.hParentCatId.value + " hCatId.value1= "+ document.cfsBillingIssueForm.hCatId.value); */
				break;
             }
			 else if (cfsAccessFlag == "N" && cfsUserFlag == "N")
			 {
			    document.cfsBillingIssueForm.hCatId.value       = null;
                document.cfsBillingIssueForm.hParentCatId.value = null;
                document.cfsBillingIssueForm.hCatDesc.value     = null;
                /* alert(" hParentCatId.value2= " + document.cfsBillingIssueForm.hParentCatId.value + " hCatId.value2= "+ document.cfsBillingIssueForm.hCatId.value); */
			 }
          
              else var errMsgCFSCatId = "Error! Multiple valid categories identified for To CBS category";
            i 
          }	// for loop 3
       }
       isCategorySelected = true;
       if(document.cfsBillingIssueForm.hCatId.value == null)
       {
          var errMsgCFSCatId = "Error! No valid CFS category found.";
          throw new Exception(errMsgCFSCatId);
       }
   }
   
   function createQuickTicketFromCapture() {
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
		         isCorrectlyEnterDigit = false;
		        // return false;
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
		         //return false;
		         isCorrectlyEnterDigit = false;
		      }
		   if(isCategorySelected===false) {
			   alert("Please Select Category Other Than  'Select One'.");
		   }
		   if(objForm.iLineId.value != '' && objForm.iLineId.value != '0' && isCorrectlyEnterDigit===true && isCategorySelected===true) {
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
	           
	       }else if(isCorrectlyEnterDigit===true && isCategorySelected===true){
	    	   var notes = objForm.notes.value;
	    	   if(notes.length > 32000){
	    		   alert("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
					objForm.notes.focus();
				}else {
					objForm.submit();
				}
	       }
	   }
	   $("#createButton").attr("disabled", false);
	  	$("#createButton").removeClass("btn_disabled");
	  	$("#createButton").addClass("btn_red");
   }
   
   function validatePhNum(thisObj) {
	    if(isNaN(thisObj.value)) {
	       alert("Please enter numbers only.");
	       thisObj.focus();
	       thisObj.select();
	       return false;
	    }
	    //Start Changes for S21 by Mangala to handle dot in phone number field
	    else
	  	  { for (var i=0; i < thisObj.value.length; i++) {
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
   } */
   
   function action_func1ReasonCode() {
		resetErroMsg();
		var objForm = document.ReasonCodeForm;
		var isSelected = false;
		var val = '';
		var v = 0;
		if(objForm.thdetails != null) {
			v = objForm.thdetails.length;
			if(v > 0) {
				for(i=0; i<v; i++) {
					if(objForm.thdetails[i].checked) {
						isSelected = true;
						val = objForm.thdetails[i].value;
						break;
					}
				}
			}else if(objForm.thdetails.checked) {
				val = objForm.thdetails.value;
				isSelected = true;
			}
		}
		if(!isSelected) {
			displayErrorMsg("Please select the reasonCode");
		}else {		
			getReasonCode(objForm.reasonCode.value, val);
			closeDlg();
		}
	}
   
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
			<h1>Customer Care - <%=strCFSIssueCaptureT1%></h1>
			<div class="breadcrumb"><%=hPath%></div>
		</div>
		
		 <form name="cfsBillingIssueForm" id="cfsBillingIssueForm" action="canon_E193_csCFSTicketController.jsp" method="post">
		 <!-- canon_E193_csCFSTicketController from canon_E193_csTicketSummaryNBController.jsp-->
     

			<input type="hidden" name="hPageFrom" value="CFSIssueCapture">
			<input type="hidden" name="nextPage" value="">
			<input type="hidden" name="hReasonCd" id="hReasonCd" value="<%=request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd")%>">
			
		        <%-- Kireet: Begin --%>
			<input type="hidden" name="hCatId" value="<%=hCatId%>">
			<input type="hidden" name="hParentCatId" id="hParentCatId" value="<%=hParentCatId%>">
			<input type="hidden" name="hParentCatCode" id="hParentCatCode" value="<%=hParentCatCode%>">
			<input type="hidden" name="hCatDesc" id="hCatDesc" value="<%=hCatDesc%>">
		        <%-- Kireet: End --%>
		
			<input type="hidden" name="iTicketId" id="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">
			<input type="hidden" name="iLineId"   id="iLineId" value="<%=request.getParameter("iLineId")==null?"":request.getParameter("iLineId")%>">
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
			<!--  <input type="hidden" name="selAcctName" value="<%=("Y".equalsIgnoreCase(cfs_user_flag)?"CBS INC":"CFS INC")%>"> --> <!--- %=request.getParameter("selAcctName") %>" --->
			<input type="hidden" name="selAcctName" value="">
			<input type="hidden" name="selAcctNum" value=""> <!--- %=request.getParameter("selAcctNum") %>" --->
			<input type="hidden" name="hPath" value="<%=hPath%>">
			<input type="hidden" name="origName" value="">
			
			<!-- <div style="margin-left:40px; margin-top: 50px"><font size="5" color="blue"><b><%=strCFSIssueCaptureT1%></b></font></div> -->
   <table class="request-servicehome" style="align:center;bgcolor:#FFFFFF" cellspacing="0" >    	
	      <!-- First Name, Last Name, Ph No. info... Begin -->
		  <tr>
				<td colspan="3">
					<div id="errorWidget" style="display: none; padding-bottom: 5px;color: red;">
						<p id="eMsg"></p>
					</div>
				</td>
		  </tr>
		  <tr>
           <td colspan=2>
            <table>
	      <tr>
		      <td width="10"></td>
		      <td> <!-- align="left"> -->
			 <b> First Name: </b>
		      </td>
		      <td> <!-- align="left"> -->
			 <input name="origFirstName" id="origFirstName" type="text" size="35" maxlength="60" class="inTxt required" style="background-color:#ffff00">&nbsp;&nbsp;			 
		      </td>
	      </tr>
	      <tr>
		      <td width="10"></td>
		      <td> <!-- align="left"> -->
			<b> Last Name: </b>
		      </td>
		      <td> <!-- align="left"> -->
			 <input name="origLastName" id="origLastName" type="text" size="35" maxlength="60" style="background-color:#ffff00" class="inTxt required">&nbsp;&nbsp;
			 <!--font class="promptReadOnly" color="#FF0000">Enter the Last Name</font-->
		      </td>
	      </tr>
	      
	      <tr>
		      <td width="10"></td>
		      <td> <!-- align="left"> -->
			<b> Number: </b>
		      </td>
		      <td> <!-- align="left"> -->
			 <input name="origPhCd" id="origPhCd" type="text" size="3" maxlength="3" class="inTxt required" onkeyup="fnNextTab(this)" style="background-color:#ffff00;width:140px;" >  <!-- onChange="javascript:fnTestDele();fnTestDele1();"> -->
			 <input name="origPhNo" id="origPhNo" type="text" size="3" maxlength="3" class="inTxt required" onkeyup="fnNextTab(this)" style="background-color:#ffff00;width:140px;" >
			 <input name="origPhNo2" id="origPhNo2" type="text" size="4" maxlength="4" class="inTxt required" onkeyup="fnNextTab(this)" style="background-color:#ffff00;width:140px;" >
			 <font class="promptReadOnly"><b>Ext</b></font>
			 <input name="origPhNoExt" type="text" size="5" maxlength="5" class="inTxt" style="width:90px;"> 
		      </td>
	      </tr>
	      <tr>
		      <td width="10"></td>
		      <td> <!-- align="left"> -->
			<b> Email Address: </b>
		      </td>
		      <td> <!-- align="left"> -->
			 <input name="origEmailAddress" id="origEmailAddress" type="text" size="35" maxlength="60" class="inTxt" onChange="validateEmail(this)">
		      </td>
	      </tr>

	      <tr>
		      <td width="10"></td>
		      <td> <!-- align="left"> -->
			<b> CFS Serial Number: </b>
		      </td>
		      <td> <!-- align="left"> -->
			 <input name="origCFSSerialNumber" type="text" size="15" maxlength="30" class="inTxt">
		      </td>
	      </tr>          
<!-- Changed For ITG # 156613 By Chandra Sekhar - End -->

              <!-- NFirst Name, Last Name, Ph No. info... End -->
          </table>
        </td>
      </tr>
<!-- Changed For ITG # 156613 By Chandra Sekhar - Start -->
          <tr>
              <td width="10"></td>
              <td>
                <b> Select Category: </b>             
                 <select name="category"  id="category" size="1" onChange="javascript:category_func();" style="margin-left:40px;background-color:#ffff00;">
                    <%
                       if(alCategoryList != null && alCategoryList.size() > 0) {
                          String reqcategory = request.getParameter("category")==null?"":request.getParameter("category");
                          String code = "";
                          for(int i=0; i<alCategoryList.size(); i++) {
                             code = alCategoryList.get(i).toString();
                            /*  System.out.println( " Code: " + code); */
                    %>
                             <option value="<%=code%>" <%=code.equals(reqcategory)?"selected":""%>><%=code%></option>
                    <%    }
                        }
                    %>
                 </select>
              </td>
          </tr>
  		
	<tr>
	 <td colspan="2">&nbsp;</td>
	</tr>
	<tr>
	<td width="20">&nbsp;</td>
		<td>			
				<b><font color="#FF0000"><%=strNBIssueCaptureN1%></font></b>
		</td>		
	</tr>
    	<tr>
    		<!-- <td colspan="2">&nbsp;</td> -->
  	</tr>
  	<tr>
		<td width="10">&nbsp;</td>
		<td>
			<table cellspacing="1" class="supplies-tablehome" style="width:800px">			
				<tr>
					<th><a href="javascript:void(0)" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','CFS','Reason Code');">Reason Code</a><%=strReasonCodeN1%></th>
					<!-- getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','document.cfsBillingIssueForm.hParentCatCode.value','Reason Code'); -->
					<th>Urgency</th>
				</tr>
				<tr>
					<td><input type="text" name="reasonCdDesc" id="reasonCdDesc" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','CFS','Reason Code');" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>" class="inTxt required" style="background-color:#ffff00" size="80" readonly=true></td>
					<td>
						<select name="severity" id="severity" size="1" class="inTxt">
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
   		<td width="20">&nbsp;</td>
		<td>			
				<b><font color="#FF0000"><%=strNBIssueCaptureN2%></font></b>
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
				<td><textarea name="notesDisplay" id="notesDisplay" rows="10" wrap="OFF" class="inTxt" readonly="readonly" style="height:auto; width:800px;"><%=strNotesDisplay%></textarea></td>		
			</tr>
		<%	}  %>
		<tr>
			<td width="10">&nbsp;</td>
			<td><textarea name="notes" id="notes" rows="10" wrap="OFF" class="inTxt required" style="background-color:#ffff00;height:auto; width:800px;"></textarea></td>		
		</tr>
		<tr valign="top" style="text-align: center">
						<td style="text-align: center;" colspan="2">
						 
							<a id="createButton" class="btn_red" href="javascript:createQuickTicketFromCapture()">Create Ticket</a> 													
						</td>
					</tr>   		
	
	</table>
	</form>	
	<div id="dlg"></div>
	<%@ include file="canon_E193_csBottomInc.jsp" %>
	</div>
<!-- canon_E193_csCFSIssueCapture.jsp : End -->
