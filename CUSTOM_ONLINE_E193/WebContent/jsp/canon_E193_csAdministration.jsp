<!-- $Header: canon_E193_csAdministration.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csAdministration.jsp - Assign Resource Groups to CRM
 |  Categories.
 | 
 | DESCRIPTION
 |  
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes.
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_AdminAssign" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="sessObjVar" scope="request" />
<%@ include file="canon_E193_csTopInc.jsp" %>

<% 	
 	// Menu Prompt
	strPageName = "Administration";
   // Canon_E193_SessionObj sessObjVar = new Canon_E193_SessionObj();
	// Check page from to show the path
	
	
	strPageFrom = request.getParameter("hPageFrom");
    ArrayList<String> resourceDetailsList = new ArrayList<String>();
    boolean isSelected = false;
    int idx = 0;
    int iCatId = 0;
    String iRoleId = "";
    String strRoleCode = "";
    String strRoleName = "";
    String strRoleTypeCode = "";
    int iGroupId = 0;
    String strGroupDesc = "";
    String strGroupName = "";
    String iResourceId = "";
    String strResourceName = "";
    boolean isPageUpdated = true;
	//Get Org Name
	String strOrgName = (String)objCiSession.getOrgName();
	int iOrgId = -1;
	if(strOrgName != null && (!(("").equalsIgnoreCase(strOrgName))))
		iOrgId = (int)objCiSession.getOrgId();
	//System.out.println("iCatId = (int)Integer.parseInt(request.getParameter(hCatId)) = " + request.getParameter("hCatId"));
	Canon_E193_AdminAssign objAdminAssign = new Canon_E193_AdminAssign();
	
	//Check if Update button is pressed.
	String hUpdated = request.getParameter("hUpdated");

	if("yes".equalsIgnoreCase(hUpdated)) 
	{
		String hIndex = request.getParameter("hIndex");
		//System.out.println(" hIndex~ "+hIndex);
		if(hIndex != null && (!("".equalsIgnoreCase(hIndex))) && (!("null".equalsIgnoreCase(hIndex))))
		{
			idx = (int)Integer.parseInt(hIndex);
			//System.out.println("idx-- " + idx);
			iCatId = (int)Integer.parseInt(request.getParameter("hCatId"));
			iRoleId = request.getParameter("hRoleId");
			strRoleCode = request.getParameter("hRoleCode");
			strRoleName = request.getParameter("tbRole"+ idx);
			strRoleTypeCode = request.getParameter("hRoleTypeCode");
			iGroupId = (int)Integer.parseInt(request.getParameter("hGroupId"));
			//String strGroupName = request.getParameter("tbGroup"+ idx);
			//String strGroupDesc = request.getParameter("hGroupDesc");
			strGroupDesc = request.getParameter("tbGroup"+ idx);
			strGroupName = request.getParameter("hGroupDesc");
			//int iResourceId = (int)Integer.parseInt(request.getParameter("hResourceId"));
			iResourceId = request.getParameter("hResourceId");
			strResourceName = request.getParameter("tbResource"+ idx);
			//System.out.println(" Before call setCategories:= "+" iCatId = "+iCatId +" , "+" ~IRoleId~ "+ iRoleId +" , "+" strRoleCode = "+strRoleCode +" , "+" ~RoleName~ " + strRoleName+" , "+ " strRoleTypeCode = "+strRoleTypeCode+" , "+" iGroupId = "+iGroupId +" , "+" ~ strGroupDesc~ " + strGroupDesc+" , "+" strGroupName = "+strGroupName+" , "+" iResourceId = "+ iResourceId+" , "+"~ strResourceName~ " +strResourceName);			
			int result = objAdminAssign.setCategories(iCatId,iRoleId,strRoleCode,strRoleName,strRoleTypeCode,iGroupId,strGroupName,strGroupDesc,iResourceId,strResourceName);
			isSelected = false;
			isPageUpdated = true;
			/* if result is not one, redirect to error page*/	
			if (result != 1) {
				response.sendRedirect("canon_E193_csErrorPage.jsp?err=Error while updating admin roles");
		}
		}
	}
	else if("Y".equalsIgnoreCase(hUpdated)){
		String hIndex = request.getParameter("hIndex");
		//System.out.println(" hIndex~ "+hIndex);
		if(hIndex != null && (!("".equalsIgnoreCase(hIndex))) && (!("null".equalsIgnoreCase(hIndex))))
		{
			idx = (int)Integer.parseInt(hIndex);
			iCatId = (int)Integer.parseInt(request.getParameter("hCatId"));
			iRoleId = request.getParameter("hRoleId");
			strRoleCode = request.getParameter("hRoleCode");
			strRoleName = request.getParameter("tbRole"+ idx);
			strRoleTypeCode = request.getParameter("hRoleTypeCode");
			iGroupId = (int)Integer.parseInt(request.getParameter("hGroupId"));
			//String strGroupName = request.getParameter("tbGroup"+ idx);
			//String strGroupDesc = request.getParameter("hGroupDesc");
			strGroupDesc = request.getParameter("tbGroup"+ idx);
			strGroupName = request.getParameter("hGroupDesc");
			
			//int iResourceId = (int)Integer.parseInt(request.getParameter("hResourceId"));
			iResourceId = request.getParameter("hResourceId");
			strResourceName = request.getParameter("tbResource"+ idx);
		    System.out.println(" strRoleName~~ "+strRoleName + " ~~strGroupName~~ "+ strGroupDesc + " ~~ strResourceName~~ "+ strResourceName);
		   // isPageUpdated = false;
		   resourceDetailsList.add(strGroupDesc);
		   resourceDetailsList.add(strRoleName);
		   resourceDetailsList.add(strResourceName);
		   isSelected = true;
		}
	}

    /* ITG# 73987 : Begin */
    /*
    String strRegionCode = (String)objCiSession.getRegionCode();	
    */
    
    String strRegionCode = request.getParameter("regionCodeSel");	
    if(strRegionCode==null || "".equals(strRegionCode)) strRegionCode=Canon_E193_RegionCodeDAO.EASTERNREGCODE;
    //update session
    objCiSession.setRegionCode(strRegionCode);
    session.setAttribute("objCiSession",objCiSession);
    ArrayList alCatList = (ArrayList)objAdminAssign.getCategories(iOrgId,strRegionCode);
    /* ITG# 73987 : End */
%>
 
<%@ include file="canon_E193_csMenuInc.jsp" %> 
  
<script type="text/javascript">
	var index  = -1;
	var prevRoleVal = "";
	var prevGroupVal = "";
	var prevResVal = "";
	var isCategorySelected = false;
		
	function action_func1(val1, val2, val3, val4, val5, val6)
	{
			resetErroMsg();
			//alert(document.csResourceAssignment.hIndex.value + " @@@ hCatId= " + document.csResourceAssignment.hCatId.value + " @@@ hIndex2 " +document.csResourceAssignment.hIndex.length);
		//console.log("~=" + val1 + " ~= "+ val2 + " ~= " + val3 +  " ~= " + val4 + " ~= "+ val5+ " ~= "+val6  );
		/* if((document.csResourceAssignment.hIndex.value === null) || (document.csResourceAssignment.hIndex.value === "")
			|| (document.csResourceAssignment.hIndex.length === 0))
		{
			displayErrorMsg("Please select one of the categories");
		} */
		if(isCategorySelected === false){
			displayErrorMsg("Please select one of the categories");
			//isCategorySelected = false;
		}
		else
		{
			document.csResourceAssignment.hIndex.value = index;
			if(val1!="" && val2!="" && val3!="") {
			document.csResourceAssignment.hRoleId.value = val1;
			document.csResourceAssignment.hRoleCode.value = val2;
			//document.getElementById(vRoleName).value = roleName;			
			document.csResourceAssignment.hRoleTypeCode.value = val3;
			}
			document.csResourceAssignment.hGroupId.value = val4;
			if(val5!="" && val6!="") {
			document.csResourceAssignment.hGroupDesc.value = val5;
			document.csResourceAssignment.hResourceId.value = val6;
			}
			document.csResourceAssignment.hUpdated.value = "yes";
			document.csResourceAssignment.action = "canon_E193_csAdministration.jsp";
			document.csResourceAssignment.submit();
		}
	}
	
	function action_func2()
	{
				//if(index==null || "null".equals(index)) index="";
				document.csResourceAssignment.hIndex.value = index;
				document.csResourceAssignment.hUpdated.value = "no";
				document.csResourceAssignment.action = "canon_E193_csAdministration.jsp";
				document.csResourceAssignment.submit();
	}
	
	function action_func3(catId,roleId,roleCode,roleName,roleTypeCode,groupId,groupDesc,groupName,resId,resName)
	{
		//console.log(encodeURIComponent(catId));
		//console.log(encodeURIComponent(roleId));
		//var selectValue = "Y";
				/* document.csResourceAssignment.hIndex.value = index;
				document.csResourceAssignment.hUpdated.value = "Y";  
				document.csResourceAssignment.action = "canon_E193_csAdministration.jsp";
				document.csResourceAssignment.submit(); */  
		 var qryStr="hCatId="+encodeURIComponent(catId)+"&hRoleId="+encodeURIComponent(roleId)+"&hRoleCode="+encodeURIComponent(roleCode)+"&hRoleName="+encodeURIComponent(roleName)+"&hRoleTypeCode="+encodeURIComponent(roleTypeCode)+"&hGroupId="+encodeURIComponent(groupId)+"&hGroupDesc="+encodeURIComponent(groupDesc)+"&hGroupName="+encodeURIComponent(groupName)+"&hResId="+encodeURIComponent(resId)+"&hResName="+encodeURIComponent(resName)+"&hUpdateField="+1;
		
	       $.ajax({
			url: "canon_E193_csAdminRoleP.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			 success: function(data){     
					 //alert("Success");
		 	        }             
		 }); 
	}
	function getAdminRole(groupId, groupName, groupDesc, roleId, roleCode, roleName, roleTypeCode, resId, resName)
	{
		var vRoleName = "tbRole" + index;
		var vGroupName = "tbGroup" + index;
		var vResName = "tbResource" + index;		
		prevRoleVal = document.getElementById(vRoleName).value;
		prevGroupVal = document.getElementById(vGroupName).value;
		prevResVal = document.getElementById(vResName).value;
		//var catId = document.getElementById(hCatId).value;
		//var catId = $("#hCatId").val();
		var catId = $("input[name=hCatId]").val();
		//console.log("groupId = "+groupId+ ", GroupName- "+groupName+ ", groupDesc- "+groupDesc+", roleId- "+roleId+ +", roleCode = "+roleCode+ ", roleName- "+ roleName +", roleTypeCode = "+roleTypeCode + ", resId = "+resId+   ", resName- " + resName);
		document.csResourceAssignment.hGroupId.value = groupId;		
		//document.getElementById(vGroupName).value = groupName;		
		//document.csResourceAssignment.hGroupDesc.value = groupDesc;
		document.getElementById(vGroupName).value = groupDesc;		
		document.csResourceAssignment.hGroupDesc.value = groupName;
		document.csResourceAssignment.hRoleId.value = roleId;
		document.csResourceAssignment.hRoleCode.value = roleCode;
		document.getElementById(vRoleName).value = roleName;			
		document.csResourceAssignment.hRoleTypeCode.value = roleTypeCode;
		document.csResourceAssignment.hResourceId.value = resId;		
		document.getElementById(vResName).value = resName;
		
		closeDlg();
		// Update data base for new role selection.
		action_func3(catId,roleId,roleCode,roleName,roleTypeCode,groupId,groupDesc,groupName,resId,resName);
	}
	
	function roleChange(val)
	{
		resetErroMsg();
		if(index == val)
			{
		 showPleaseWait();			    
		  var modelName ="#dlg";
		  	   $(modelName).dialog({
		   					height: 500,
		   					title: "Admin Roles", 
		   					modal: true ,
		   					zIndex:1005,
		   					width: 650,		
		   	                resizable: false      
		   				});
		    	   
		    	   var qryStr="reasonCode="+encodeURIComponent(val);
		    	       $.ajax({
		   			url: "canon_E193_csAdminRoleP.jsp",
		   			data:qryStr,
		   			type:"POST",
		   	        cache: false,
		   			 success: function(data){     
		   					  //hidePleaseWait();     
		   					  $(modelName).html("");					
		   				      $(modelName).html(data);
		   				      fnRefreshRole();
		   				       hidePleaseWait();
		   		 	        }             
		   		 });
		   		  
		   	     $( modelName ).dialog("open");
		   	     $(".ui-dialog-titlebar").addClass("hd");
		   	     $(".ui-dialog").css({"top":"200px","position":"fixed"}); 		   	  
		   	     $('.ui-widget-overlay').remove();
			 		
		
			}
		else
		{			
			displayErrorMsg("Please select the category first");
		}		
	}
	
	function selectRole(val, catId)
	{
		var vRName = "tbRole" + index;
		var vGName = "tbGroup" + index;
		var vResName = "tbResource" + index;
		
		// Here index is previously selected row and val is new selected row.
		/*  if(index != -1 && index != val)
		{
			document.getElementById(vRName).value = document.getElementById(vRName).defaultValue;
			document.getElementById(vGName).value = document.getElementById(vGName).defaultValue;
			document.getElementById(vResName).value = document.getElementById(vResName).defaultValue;
		}  */	
		index = val;
		//alert(" Index Value: "+ index + " CatID: "+ catId);
		document.csResourceAssignment.hCatId.value = catId;
		isCategorySelected = true;
	}
	
	function select_reasonCode() {
		resetErroMsg();
		var objForm = document.reasonCodeForm;
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
			setReasonCode(val);				
			closeDlg();
		}
	}
	
	function selectAdminRole() {
		resetErroMsg();
		var objForm = document.adminRoleForm;
		var isSelected = false;
		var v = 0;
		if(objForm.thdetails != null) v = objForm.thdetails.length;
		if(v > 0) {
			for(i=0; i<v; i++) {
				if(objForm.thdetails[i].checked) {
					isSelected = true;
					break;
				}
			}
		}else if(objForm.thdetails != null && objForm.thdetails.checked) {
			isSelected = true;
		}

		if(!isSelected) {
			displayErrorMsg("Please select the adminRole");
		}else {
			var val = objForm.adminGroup.value;
			var groupArray = val.split("|");
			var v1 = objForm.hRole1.value;
			var v2 = objForm.hRole2.value;
			var v3 = objForm.hRole3.value;
			var v4 = objForm.hRole4.value;
			var v5 = objForm.hResource1.value;
			var v6 = objForm.hResource2.value;
			
			if ((groupArray[1] == null) || (groupArray[1] == "null"))
				groupArray[1] = "$";
			
				groupArray[2] = objForm.selectedGroup.value;
			if ((groupArray[3] == null) || (groupArray[3] == "null"))
				groupArray[3] = "$";
			if ((v2 == null) || (v2 == "null"))
				v2 = "$";
			if ((v3 == null) || (v3 == "null"))
				v3 = "$";
			if ((v6 == null) || (v6 == "null"))
				v6 = "$";
			
			getAdminRole(groupArray[0], groupArray[1], groupArray[2], v1, v2, v3, v4, v5, v6);
			
			// Process Start For Locally Store user Activity 
			 //  action_func3();
			
		}
	}
	
	function fnRefreshRole() {
	   var qryStr="reasonCode="+encodeURIComponent($('#adminGroup :selected').val());
	   var modelName ="#dlg";
	       $.ajax({
				url: "canon_E193_csAdminRoleP.jsp",
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
	       $( modelName ).dialog("open");
	   	     $(".ui-dialog-titlebar").addClass("hd");
	   	     $(".ui-dialog").css({"top":"200px","position":"fixed"}); 		   	  
	   	     $('.ui-widget-overlay').remove();
	}
	
	function fnSelectGroups(val1, val2, val3, val4, val5, val6) {	
		//console.log("fnSelectGroups "+ val6," ~~ "+ val1+" ~~ "+ val2+" ~~ "+ val3+" ~~ "+ val4 + +" ~~ "+ val5);
		var selectedGroup=$('#adminGroup :selected').text();	
//		$("#selectedGroup").val(selectedGroup.substring(0,selectedGroup.indexOf("-")));
		$("#selectedGroup").val(selectedGroup);	
		var objForm = document.adminRoleForm;
		objForm.hRole1.value = val1;
		objForm.hRole2.value = val2;
		objForm.hRole3.value = val3;
		objForm.hRole4.value = val4;
		objForm.hResource1.value = val5; 
		objForm.hResource2.value = "";				
		if(document.getElementById(val6)!=null)
			objForm.hResource2.value=$('input#thdetails[value="'+val6+'"]').closest('tr').find('td[id="'+val6+'"]').text();//document.getElementById(val6).innerHTML;
				
	}
	
	
</script>

<div id="main_content">
   		<div id="page_top">
			<h1>Customer Care - <%=strAdministrationT1%></h1>
		</div>
		<div class="table-inner">
<form name="csResourceAssignment" id="csResourceAssignment" method="post">
<!-- hidden variables -->
	<input type="hidden" name="hPageFrom" value="csAdminResourceAssignment" >
	<input type="hidden" name="hIndex" id="hIndex" value="<%=request.getParameter("hIndex")%>" >
	<input type="hidden" name="hUpdated" value="<%=request.getParameter("hUpdated")%>" >
	
	<input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")%>">	
	<input type="hidden" name="hRoleId" id="hRoleId">
	<input type="hidden" name="hRoleCode" id="hRoleCode">
	<input type="hidden" name="hRoleTypeCode" id="hRoleTypeCode">
	<input type="hidden" name="hGroupId" id="hGroupId">
	<input type="hidden" name="hGroupDesc" id="hGroupDesc">	
	<input type="hidden" name="hResourceId" id="hResourceId">
	<div style="margin-top: 20px;">
	<b style="margin-left: 5px;"><%=strAdministrationM1%> <%=strOrgName%>&nbsp;-&nbsp;</b>
	<%
		//ITG#: 125696: Begin
		String[] regionCodes = {Canon_E193_RegionCodeDAO.EASTERNREGCODE,"CENTRAL_REGION","WEST_REGION"};
		%>
		<select name="regionCodeSel" onChange="javascript:action_func2();">
		<%
		for(int ind=0; ind<regionCodes.length; ind++){
		  boolean selected=regionCodes[ind].equalsIgnoreCase(strRegionCode);
		  %>
		  <option name="<%=regionCodes[ind]%>" <%=selected?"selected":""%>><%=regionCodes[ind]%>
		  </option>
		  <%
		}
		%>
		</select>
	</div>
 	 <div style="text-align: right">
		<a class="btn_red" style="margin-right: 5px;" href="javascript:action_func1('<%=iRoleId%>','<%=strRoleCode%>','<%=strRoleTypeCode%>','<%=iGroupId%>', '<%=strGroupName%>', '<%=iResourceId%>')")">Update</a>
	</div>  
	 <div style="margin-top: 15px;"></div>
	<table class="supplies-table" cellspacing="1" style="margin-top: 5px">
			<tr>
			    <td colspan="8">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
				<tr>
					<th>&nbsp;</th>
					<th>Parent Category</th>
					<th>Category Code</th>
					<th>Category Description</th>
					<th>Group</th>
					<th>Role</th>
					<th>Resource</th>
					<th>&nbsp;</th>		
				</tr>
<%			

             String strCatCode = "";
             String strCatDesc = "";
             String strParentCatCode = "";
             String strCrmRoleName = "";
             String iCatCrmRoleId = "";
             String strCrmRoleCode = "";
             String iCrmResourceId = "";
             String strCrmRoleTypeCode = "";
             String strCrmResourceName = "";
             String strCrmGroupDesc = "";
             int iCatCrmGroupId = 0;
             String strCrmGroupName = "";
             /* if(isPageUpdated) { */
				for(int i=0;i<alCatList.size();i++)
				{
					Canon_E193_AdminAssignObj objAminAssign = (Canon_E193_AdminAssignObj)alCatList.get(i);
					iCatId = objAminAssign.getICatId();
					//System.out.println("~iCatId~ "+ iCatId);
					strCatCode = objAminAssign.getStrCatCode();
					//System.out.println("~strCatCode~ "+ strCatCode);
					if (strCatCode == null)
						strCatCode = "";
					strCatDesc = objAminAssign.getStrCatDesc();
					//System.out.println("~CatDesc~ "+ strCatDesc);
					if (strCatDesc == null)
						strCatDesc = "";
					strParentCatCode = objAminAssign.getStrParentCatCode();
					//System.out.println("~strParentCatCode~ "+ strCatCode);
					if (strParentCatCode == null)
						strParentCatCode = "";
					iCatCrmRoleId = String.valueOf(objAminAssign.getICrmRoleId());
					strCrmRoleCode = objAminAssign.getStrCrmRoleCode();
					if (strCrmRoleCode == null)
						strCrmRoleCode = "";
					
					
					//int iCrmResourceId = objAminAssign.getICrmResourceId();
					iCrmResourceId = objAminAssign.getICrmResourceId();
					strCrmRoleTypeCode = objAminAssign.getStrCrmRoleTypeCode();
					if (strCrmRoleTypeCode == null)
						strCrmRoleTypeCode = "";											
					iCatCrmGroupId = objAminAssign.getICrmResourceGroupId();
					strCrmGroupName = objAminAssign.getStrCrmGroupName();
					//System.out.println("~GroupName~ "+ strCrmGroupName);
					if (strCrmGroupName == null)
						strCrmGroupName = "";
					
					if(isSelected && idx==i){
						strCrmGroupDesc = resourceDetailsList.get(0);
						strCrmRoleName = resourceDetailsList.get(1);
						strCrmResourceName = resourceDetailsList.get(2);
						isSelected = false;
					}else{
						
						strCrmGroupDesc = objAminAssign.getStrCrmGroupDesc();
						//System.out.println("~GroupDesc~ "+ strCrmGroupDesc);
						if (strCrmGroupDesc == null)
							strCrmGroupDesc = "";
						strCrmRoleName = objAminAssign.getStrCrmRoleName();
						//System.out.println("~RoleName~ "+ strCrmRoleName);
						if (strCrmRoleName == null)
							strCrmRoleName = "";
						strCrmResourceName = objAminAssign.getStrCrmResourceName();
						//System.out.println("~ResourceName~ "+ strCrmResourceName);
						if (strCrmResourceName == null)
							strCrmResourceName = "";
					}
					
%>				
					<tr>
<%
						if(!(strParentCatCode.equals("")))
						{	
%>					
							
            				<td><input type="radio" name="rbCat" onclick="selectRole(<%=i%>,<%=iCatId%>)" value="<%=iCatId%>"></td>
<%
						}
						else
						{
%>
							<td>&nbsp;</td>
<%
						}
%>						
						<td><%=strParentCatCode%>&nbsp;</td>
						<td><%=strCatCode%>&nbsp;</td>
						<td><%=strCatDesc%>&nbsp;</td>						
<%
						if (!(strParentCatCode.equals("")))
						{					
%>
							<td>
								<input type="text" style="width:120px;" name="tbGroup<%=i%>" id="tbGroup<%=i%>"  readonly value="<%=strCrmGroupDesc%>">
							</td>
							<td>
								<input type="text" style="width:120px;" name="tbRole<%=i%>" id="tbRole<%=i%>" readonly value="<%=strCrmRoleName%>">
							</td>   	
							<td>
								<input type="text" style="width:120px;" name="tbResource<%=i%>" id="tbResource<%=i%>" readonly value="<%=strCrmResourceName%>">
							</td>
							<td>
								<a href="javascript:void(0)" onClick="javascript:roleChange(<%=i%>)">Select Role</a>
							</td>							
<%
						}
						else
						{
%>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>	
							<td>&nbsp;</td>	
<%
						}
%>
					</tr> 
<%
				}
				/* isPageUpdated = false;
             } */
%>
	
			</table> 
			<div style="text-align: center; margin-bottom: 5px;">
			 <a class="btn_red" href="javascript:action_func1('<%=iRoleId%>','<%=strRoleCode%>','<%=strRoleTypeCode%>','<%=iGroupId%>', '<%=strGroupName%>', '<%=iResourceId%>')")">Update</a>	
			</div>			
</form>
<div id="dlg"></div>   
</div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>  
