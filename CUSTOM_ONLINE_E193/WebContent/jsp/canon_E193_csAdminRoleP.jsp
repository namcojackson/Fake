<!-- $Header: canon_E193_csAdminRoleP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csAdminRoleP.jsp - role codes.
 |   
 | DESCRIPTION
 |   Details of the role code and description.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/16/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 |
 |
 +=======================================================================--%>

 <% String ctxPath = request.getContextPath(); %>




<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_AdminAssign" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AdminCrmObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_AdminAssign" id="objAdminDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objResourceRole" scope="request" />
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<script language="javascript">
/* function fnRefreshRole() {
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
} */





</script>

<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	
	/* ITG# 73987 : Begin */	
	String strRegionCode = (String)objCiSession.getRegionCode();	
        /* ITG# 73987 : End */
	Canon_E193_AdminAssign adminAssignObj = new Canon_E193_AdminAssign();

	String strAdminGroup = request.getParameter("adminGroup");
	//
	String iGroupId = null;
	String reasonCode=request.getParameter("reasonCode")==null?"":request.getParameter("reasonCode");
	//System.out.println("reasonCode = "+ reasonCode);
	String strCodeDesc = request.getParameter("hCodeDesc")==null?"":request.getParameter("hCodeDesc");
	
	
	String updateFieldVal=request.getParameter("hUpdateField")==null?"":request.getParameter("hUpdateField");
	
	/* 
	 *  Field need to update 
	 */
	if(updateFieldVal!=""){
		int iCatIdVal = 0;
		int iGroupIdVal = 0;
		String catIdVal=request.getParameter("hCatId")==null?"":request.getParameter("hCatId");
		String roleIdVal = request.getParameter("hRoleId")==null?"":request.getParameter("hRoleId");
		String roleCodeVal=request.getParameter("hRoleCode")==null?"":request.getParameter("hRoleCode");
		String roleNameVal = request.getParameter("hRoleName")==null?"":request.getParameter("hRoleName");
		String roleTypeCodeVal=request.getParameter("hRoleTypeCode")==null?"":request.getParameter("hRoleTypeCode");
		String groupIdVal = request.getParameter("hGroupId")==null?"":request.getParameter("hGroupId");
		String groupDescVal=request.getParameter("hGroupDesc")==null?"":request.getParameter("hGroupDesc");
		String groupNameVal = request.getParameter("hGroupName")==null?"":request.getParameter("hGroupName");
		String resIdVal=request.getParameter("hResId")==null?"":request.getParameter("hResId");
		String resNameVal = request.getParameter("hResName")==null?"":request.getParameter("hResName");
		
		iCatIdVal = Integer.parseInt(catIdVal);
		iGroupIdVal = Integer.parseInt(groupIdVal);
		System.out.println("iCatIdVal = "+iCatIdVal +" , "+" ~IRoleId =  "+ roleIdVal +" , "+" strRoleCode = "+roleCodeVal 
				+" , "+" ~RoleName = " + roleNameVal+" , "+ " strRoleTypeCode = "+roleTypeCodeVal+" , "+" iGroupId = "
				+iGroupIdVal +" , "+" ~ strGroupDesc =  " + groupDescVal+" , "+" strGroupName = "+groupNameVal+" , "
				+" iResourceId = "+ resIdVal+" , "+"~ strResourceName= " +resNameVal);			
		int result = adminAssignObj.setCategories(iCatIdVal,roleIdVal,roleCodeVal,roleNameVal,roleTypeCodeVal,
				iGroupIdVal,groupNameVal,groupDescVal,resIdVal,resNameVal);
		
		/* if result is not one, redirect to error page*/	
		if (result != 1) {
			response.sendRedirect("canon_E193_csErrorPage.jsp?err=Error while updating admin roles");
	}
		
	}
	
	System.out.println("strCodeDesc:~ "+ strCodeDesc + " ~reasonCode~ "+reasonCode);
	ArrayList alAdminGroupList = null;
	Canon_E193_AdminCrmObj adminCrmObj = null;
	if("".equals(strCodeDesc)) {
		alAdminGroupList = objAdminDao.getGroup(iOrgId, strRegionCode); //ITG# 73987
		if(alAdminGroupList != null && alAdminGroupList.size() > 0) {
			String str1,str2;
 			for(int i=0; i<alAdminGroupList.size(); i++) {
   				adminCrmObj = (Canon_E193_AdminCrmObj)alAdminGroupList.get(i);
   				iGroupId = adminCrmObj.getIAttribute1();
   				str1 = adminCrmObj.getStrAttribute2();
   				str2 = adminCrmObj.getStrAttribute3();
   				//System.out.println("iGroupId = " + iGroupId + " - str1 = " + str1 + " - str2 = " + str2);
   				if(str1 == null || "".equals(str1)) str1 = " ";
   				if(str2 == null || "".equals(str2)) str2 = " ";
   				
   				//if(iGroupId == 0) iGroupId = adminCrmObj.getIAttribute1();
   				if(iGroupId != null) iGroupId = adminCrmObj.getIAttribute1();
   				if("".equals(strCodeDesc)) strCodeDesc = adminCrmObj.getIAttribute1() + "|" + str1 + "|" + str2 + "#" + str2;
   				else strCodeDesc = strCodeDesc + "#" + adminCrmObj.getIAttribute1() + "|" + str1 + "|" + str2 + "#" + str2;
   			}
   		}
	}
	System.out.println("iGroupId = " + iGroupId + " - strAdminGroup = " + strAdminGroup);
	if(reasonCode==null)
		reasonCode=iGroupId;
	//if(iGroupId == 0) iGroupId = Integer.parseInt(new StringTokenizer(strAdminGroup, "|").nextToken());
	//if(iGroupId != null && strAdminGroup != null) iGroupId = new StringTokenizer(strAdminGroup, "|").nextToken();
	if(iGroupId == null) iGroupId = new StringTokenizer(strAdminGroup, "|").nextToken();
	//System.out.println("iGroupId = " + iGroupId );
	//System.out.println("Before getRoleResource:");
	//System.out.println("iGroupId = " + iGroupId + " - iOrgId = " + iOrgId);
	ArrayList alAdminRoleResList = objAdminDao.getRoleResource(iOrgId, reasonCode);
	//System.out.println("After getRoleResource ");
%>

<form name="adminRoleForm" id="adminRoleForm" action="canon_E193_csAdminRoleP.jsp" method="post">
<input type="hidden" name="hCodeDesc" value="<%=strCodeDesc%>">

	<!-- return group values -->
	<input type="hidden" name="hRole1" value="">
	<input type="hidden" name="hRole2" value="">
	<input type="hidden" name="hRole3" value="">
	<input type="hidden" name="hRole4" value="">
	<input type="hidden" name="hResource1" value="">
	<input type="hidden" name="hResource2" id="hResource2"  value="">
	<input type="hidden" id="reasonCode" name="reasonCode"  value="<%=reasonCode%>">
	<input type="hidden" id="selectedGroup" name="selectedGroup" >
	<div>Admin Roles and Groups<br>
	<select name="adminGroup" id="adminGroup" size="1" class="searchBarLink" onChange="javascript:fnRefreshRole();">
	<%
		 				if(alAdminGroupList != null && alAdminGroupList.size() > 0) {
		 					for(int j=0; j<alAdminGroupList.size(); j++) {
		 						String code = "";
		 						String desc = "";		 					
		 						adminCrmObj = (Canon_E193_AdminCrmObj)alAdminGroupList.get(j);
		 						code=adminCrmObj.getIAttribute1();
		 						//System.out.println(" ~Code~ "+code + " ~ReasonCode~ "+ reasonCode);
		 						desc=adminCrmObj.getStrAttribute3();		 						
		 						 						
		 			%>
		 			<option value="<%=code%>" <%=code.equalsIgnoreCase(reasonCode)?"selected":""%>><%=desc%></option>
	
					<%} 
		 			   }   				
   					%>
   				</select>
	</div>
	
	    		<table class="supplies-table" cellspacing="1">
		 			<tr class="subhead">
		 				<th>&nbsp;</th>
						<th>Role</th>
						<th>Resource</th>
		 			</tr>
		 			<%  
		 			 ArrayList<String> resourceRoleNameList = new ArrayList<String>();
		 			 HashMap<Integer,ArrayList<String>> mHashMap = new HashMap<Integer,ArrayList<String>>();
		 				if(alAdminRoleResList != null && alAdminRoleResList.size() > 0) {
		 					
		 					for(int j=0; j<alAdminRoleResList.size(); j++) {
		 						adminCrmObj = (Canon_E193_AdminCrmObj)alAdminRoleResList.get(j);
		 						
		 						//if(resourceRoleNameList.size()==0){
		 							//resourceRoleNameList.add(adminCrmObj.getStrAttribute3());
		 							//resourceRoleNameList.add(adminCrmObj.getStrAttribute5());
		 							//System.out.println("Attribute3~~ "+ adminCrmObj.getStrAttribute3() + " ~~ Attribute5 ~~ " +adminCrmObj.getStrAttribute5());
		 					//	}
		 						//System.out.println(" Attribute3- " + adminCrmObj.getStrAttribute3() + " Attribute5- " + adminCrmObj.getStrAttribute5());
		 						%>
				   				<tr>
					   				<td><input name="thdetails" id="thdetails" type="radio" value="<%=j%>" onClick="javascript:fnSelectGroups('<%=""+adminCrmObj.getIAttribute1()%>','<%=adminCrmObj.getStrAttribute2()%>','<%=adminCrmObj.getStrAttribute3()%>','<%=adminCrmObj.getStrAttribute4()%>', '<%=""+adminCrmObj.getIAttribute11()%>', '<%=j%>')"></td>
					   				<td><%=adminCrmObj.getStrAttribute3()==null?"":adminCrmObj.getStrAttribute3()%></td>
					   				<td id="<%=j%>"><%=adminCrmObj.getStrAttribute5()==null?"":adminCrmObj.getStrAttribute5()%></td>
								</tr>
								
					<%		
					     
		 					}
		 					// mHashMap.put(1,resourceRoleNameList);
				 			//objResourceRole.setResourceRoleInfoMap(mHashMap);
				 			// Remove elements and assignd "Null" in ArrayList and HashMap because of multiple ArrayList and HashMap creation
				 			//resourceRoleNameList.clear();
				 		//	mHashMap.clear();
				 			//resourceRoleNameList = null;
				 			//mHashMap = null;
		 					
		 					}else{
					%>
							<tr>
								<td colspan="3">
									<font class="promptReadOnly"><b><%=strAdminRolePM1%></b></font>
								</td>
							</tr>
					<%	} 	
		 			
					%>
	     		</table>  
	     		<div>
	     		<a class="btn_red" href="javascript:selectAdminRole();">Select</a> 	
	     		</div> 	  		
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