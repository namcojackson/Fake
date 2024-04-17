<!-- $Header: ITG# 74988 canon_E193_csTicketAssignmentP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketAssignmentP.jsp - reason codes
 |   
 | DESCRIPTION
 |   Details of the assignment department, roles and resource names.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/14/2005
 |
 | HISTORY
 | DATE        WHO               WHY
 | 30-Nov-06   Vikas Basal       ITG# 74988 CFS Changes
 | 16-Sep-13   Hema Doniparthi	 ITG#475337
 | 29-Jan-16   Mangala Shenoy	 Modified for S21 changes
 +=======================================================================--%>
 
<%@page language="java"%> 
<%@page import="java.lang.*"%>
<%@page import ="java.util.*"%>
<%@page import="com.canon.oracle.custapp.custinq.dao.*"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.*"%>


<% String ctxPath = request.getContextPath(); %>
  
<%
try 
{
%>
	<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
	//Get org ID
	int iOrgId = objCiSession.getOrgId();
	
	Canon_E193_Assignment objAssignDao = new Canon_E193_Assignment();
	ArrayList alDeptList = (ArrayList)objAssignDao.getDept();
	
	String hDeptVal = request.getParameter("assignDept");
	String hRoleVal = request.getParameter("hRoleVal")==null?"":request.getParameter("hRoleVal");
	
	String assignFlag = request.getParameter("assignFlag");
	String strselectedRoleVal=request.getParameter("selectedRoleVal")==null?"":request.getParameter("selectedRoleVal");

%>


<script type="text/javascript">

	function dept_select()
	{	   
	   	var selectedRoleVal=$('#assignRole :selected').val();	
	   	var hRoleVal=$('#hRoleVal').val();
	   	var qryStr="assignDept="+encodeURIComponent( $('select[name="assignDept"]').val() )+"&hDeptSel=yes&hRoleSel=yes&hChgDeptSel=yes";
	   	//qryStr+="&assignRole="+encodeURIComponent( $('#assignRole :selected').val() );	
	   	qryStr+="&assignRole="+encodeURIComponent($('#hRoleVal').val() )+"&hRoleVal="+hRoleVal+"&selectedRoleVal="+selectedRoleVal;
	   	$("#hDeptSel").val("yes");
	   	$("#hRoleSel").val("yes");
	   	$("#hChgDeptSel").val("yes");
	
	    var modelName ="#dlg";
		//alert(qryStr);

	    $.ajax({
			url: "canon_E193_csTicketAssignmentP.jsp",
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

	function role_select()
	{		
		var selectedRoleVal=$('#assignRole :selected').val();   	
		var qryStr="assignRole="+encodeURIComponent( $('select[name="assignRole"]').val() );
		qryStr+="&assignDept="+encodeURIComponent( $('select[name="assignDept"]').val() )+"&hDeptSel=yes&hRoleSel=yes&hChgDeptSel=yes"+"&selectedRoleVal="+selectedRoleVal;		
	   	var modelName ="#dlg";
	   	//alert(qryStr);

	    $.ajax({
			url: "canon_E193_csTicketAssignmentP.jsp",
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

	function resource_select(selectField)
	{
	   	var selectedResourceName=$(selectField).closest('tr').find('td[class="assignResource"]').text();	  
	   	document.AssignmentForm.hResourceId.value = selectField.value;
	   	document.AssignmentForm.hResourceIdText.value=selectedResourceName;
	}

	function select_assignee() 
	{
		resetErroMsg();
	   	var resourceVal = document.AssignmentForm.hResourceId.value;
	   	var reAssignVal = document.AssignmentForm.reAssign.value;
	   	//reAssign
	   	var objForm = document.AssignmentForm;
	   	var isSelected = false;
	   	var v = 0;    
	   	if(objForm.assignResource != null) {
	   		v = objForm.assignResource.length;
	   		
	   		if(v > 0) {   		
	   			for(i=0; i<objForm.assignResource.length; i++) {
	   				if(objForm.assignResource[i].checked) {   		
	   					isSelected = true;
	   					break;
	   				}
	   			}
	   		}else if(objForm.assignResource.checked) {
	   			isSelected = true;
	   		}
	   	}
	   	
	   	if (!isSelected) {
	   		displayErrorMsg("Please select one of the above resources.");
	   	}else{	   		
	   		 	var f = document.AssignmentForm;
	   		 	var deptVal = $('#assignDept :selected').val();//f.assignDept.options[f.assignDept.selectedIndex].value;
	   		 	var roleVal = $('#assignRole :selected').val();//f.assignRole.options[f.assignRole.selectedIndex].value;
	   		 	var deptDesc = $('#assignDept :selected').text();//f.assignDept.options[f.assignDept.selectedIndex].text;
	   		 	var assignDesc = $('#assignRole :selected').text();//f.assignRole.options[f.assignRole.selectedIndex].text;
	   		 	var resourceDesc = '';   		 	
	   		 	if(resourceVal != null)
	   		 		resourceDesc = document.AssignmentForm.hResourceIdText.value;
	   		// alert('deptVal : ' + deptVal + ' roleVal : '+ roleVal + ' resourceVal : '+resourceVal + ' deptDesc : ' + 
	   				// ' assignDesc : ' + assignDesc + ' resourceDesc : ' + resourceDesc + ' reAssignVal ' + reAssignVal);
	   		 getAssignment(deptVal, roleVal, resourceVal, deptDesc, assignDesc, resourceDesc,reAssignVal);
	   	}
	}

</script>

<form name="AssignmentForm" id="AssignmentForm" method="post">

	<!-- hidden variables -->
	<input type="hidden" name="hPageFrom" id="hPageFrom" value="csTicketAssignment" >
	
	<input type="hidden" name="hDeptSel" id="hDeptSel" value="<%=request.getParameter("hDeptSel")%>">
	<input type="hidden" name="hRoleSel" id="hRoleSel" value="<%=request.getParameter("hRoleSel")%>">
	<input type="hidden" name="hResourceId" id="hResourceId" value="<%=request.getParameter("hResourceId")==null?"":request.getParameter("hResourceId")%>">
	<input type="hidden" name="hChgDeptSel" id="hChgDeptSel" value="">
	<input type="hidden" name="hRoleVal" id="hRoleVal" value="<%=hRoleVal%>">
	<input type="hidden" name="hResourceIdText" id="hResourceIdText" value="">
	<input type="hidden" name="reAssign" value="reAssigned">
	<input type="hidden" name="selectedRoleVal" id="selectedRoleVal" value="<%=strselectedRoleVal%>">
      <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
		<p id="eMsg"></p>
      </div>
	<div style="margin-left:30px;"> Ticket Assignments: </div>
	<div style="margin-left:30px;"> Select Department: 
	<select name="assignDept"  id="assignDept" size="1" class="searchBarLink" onChange="javascript:dept_select(this)" style="margin-left:20px;">
			   			<% 
							for(int i=0;i<alDeptList.size();i++)
							{
								Canon_E193_AssignmentObj objAssignDept = (Canon_E193_AssignmentObj)alDeptList.get(i);
								
								String strDeptCode = objAssignDept.getStrAttribute2();
								if (strDeptCode == null)
									strDeptCode = "";
								String strDeptName = objAssignDept.getStrAttribute3();
								if (strDeptName == null)
									strDeptName = "";
									
								String strSelected = "";
								if (hDeptVal == null && i == 0){
									strSelected = "selected";
								}else if (strDeptCode.equals(hDeptVal)){ 
									strSelected = "selected";
								}else{ 
									strSelected = "";
								}
								
									if("yes".equals(assignFlag) && i == 0) hDeptVal = strDeptCode;
						/* ITG# 74988 - Begin */

		     						if( ("Y".equals(objCiSession.getCFSAccessFlag())) && ("Y".equals(objCiSession.getCFSUserFlag())) )
								{
									if ( "CSR_E193_CFS".equals(strDeptCode) || "CSR_E193_CONTRACT".equals(strDeptCode) || "CSR_E193_CUSTOMER_SERVICE".equals(strDeptCode) )
									{
			   			%>
			   						<option value="<%=strDeptCode%>" <%=strSelected%>><%=strDeptName%></option>
			   			<%		
			   						}
			   					}
			   					else
			   					{
			   			%>
			   						<option value="<%=strDeptCode%>" <%=strSelected%>><%=strDeptName%></option>
			   			<%
			   					}
			   			/* ITG# 74988 - End */
			   				}
			   			%>
		   				</select>
		   				<a class="btn_red" href="javascript:select_assignee();">Select</a>		
	</div>
	<div style="margin-left:30px;">
	<%
					String hDeptSel = request.getParameter("hDeptSel");
					if("yes".equalsIgnoreCase(hDeptSel))
					{
%>	
	Select Role: 
	<select name="assignRole" id="assignRole" style="width:120px;margin-left:75px;" size="1"  onChange="javascript:role_select(this)">
<%
								System.out.println(" hDeptVal: "+hDeptVal);
								ArrayList alRoleList = (ArrayList)objAssignDao.getRole(hDeptVal);
								String strChgDeptSel = request.getParameter("hChgDeptSel")==null?"":request.getParameter("hChgDeptSel");
								
								boolean isRoleMatched = false;
								String tempRoleId = "";
								
								for(int i=0;i<alRoleList.size();i++)
								{
									Canon_E193_AssignmentObj objAssignRole = (Canon_E193_AssignmentObj)alRoleList.get(i);
									//Start Changes for S21 by Mangala
									//int iRoleId = objAssignRole.getIAttribute1();
									String iRoleId = objAssignRole.getIAttribute1();
											//System.out.println("########iRoleId = "+iRoleId);
									//End Changes for S21 by Mangala	
									
									
									String strRoleName = objAssignRole.getStrAttribute3();
									System.out.println("strRoleName:"+strRoleName+" iRoleId:"+iRoleId+" strselectedRoleVal:"+strselectedRoleVal);
									if (strRoleName == null)
										strRoleName = "";																			
									String strRoleSelected = "";
									if (iRoleId.equalsIgnoreCase(strselectedRoleVal))
									{
										strRoleSelected = "selected";
										isRoleMatched = true;
									}
									else
									{
										strRoleSelected = "";
										if(i==0)
										{
											tempRoleId = iRoleId;
										}
									}
									if("yes".equals(strChgDeptSel) && i == 0) hRoleVal = ""+iRoleId;
									
									if("yes".equals(assignFlag) && i == 0) hRoleVal = ""+iRoleId;
									
%>
									<option value="<%=iRoleId%>" <%=strRoleSelected%>><%=strRoleName%></option>
<%
								}
								if(!isRoleMatched)
								{
									strselectedRoleVal = tempRoleId;
								}
%>
<%			
						if(alRoleList.size() == 0)
						{
%>
							<%=strTicketAssignmentPM1%>
							
<%
						}
					} 
%>
								</select>
									</div>
	
<table width="800" class="request-service" cellspacing="0">
<%
		String hRoleSel = request.getParameter("hRoleSel");
		//System.out.println("hRoleSel : " + hRoleSel);
		if("yes".equalsIgnoreCase(hRoleSel))
		{
%>
		
   		<tr>
   			<td colspan="2">
   			<div style="margin-left:30px;">Select Resource:</div>
   			</td>
   		</tr>				
   		<tr>
			<td width="10"></td>
    		<td>
	    		<table cellspacing="1" class="request-service">
		 			<tr >
		 				<th width="10">&nbsp;</th>
						<th>Resource Name</th>
		 			</tr>
<%

				String hIRoleId = "-1";
				if(("null".equals(hRoleVal)) || ("".equals(hRoleVal))){
				// DO Nothing
				}else{
					hIRoleId = hRoleVal;				
				}
				System.out.println("Prior to call getResource:iOrgId"+iOrgId+" strselectedRoleVal:"+strselectedRoleVal);
				ArrayList alResourceList = (ArrayList)objAssignDao.getResource(iOrgId, strselectedRoleVal);
				
				for(int i=0;i<alResourceList.size();i++)
				{
					Canon_E193_AssignmentObj objAssignResource = (Canon_E193_AssignmentObj)alResourceList.get(i);
					
					//Start changes for S21 by Mangala
					//int iResourceId = objAssignResource.getIAttribute1();
					String iResourceId = objAssignResource.getIAttribute1();		
					//End changes for S21 by Mangala		
					String strResourceName = objAssignResource.getStrAttribute2();
					if (strResourceName == null)
						strResourceName = "";				
					String strResourceChecked = "";
					String rId = request.getParameter("hResourceId");

					int id = -1; 
					if(rId != null && (!("".equalsIgnoreCase(rId))) && (!("null".equalsIgnoreCase(rId))) )
						//Start changes for S21 by Mangala
						//id = Integer.parseInt(rId);
					//if(iResourceId == id )
						if(iResourceId == rId )
						//End changes for S21 by Mangala
						strResourceChecked = "checked";
%>
					<tr>
						<td width="10">
							<input name="assignResource" type="radio" value="<%=iResourceId%>" <%=strResourceChecked%> onChange="resource_select(this)"></td>
						<td class="assignResource" id="<%=iResourceId%>"><%=strResourceName%></td>
					</tr>
<%			
				}
				if(alResourceList.size() == 0)
				{
%>
					<tr class="tdTableCellStyle">
						<td colspan="2">
							<font class="promptReadOnly"><b><%=strTicketAssignmentPM2%></b></font>
						</td>
					</tr>
<%
				}
%>					
	     		</table>   
	  		</td>
 		</tr>
<%
	}
%>		
		<tr>
			<td align="left" colspan="2"> 
			   <a class="btn_red" style="margin-left:30px;" href="javascript:select_assignee();">Select</a>				
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