<!-- WF Admin Header starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageHeader.jsp"/>
<!-- WF Admin Header end -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form id="resultForm" method="POST" action='<c:url value="/wfadmin/jobx/JobDispatcherConfigManagePage/showPage.sweb"/>'>



<legend><font size="3" style="font-weight:bold">Process Deploy Group Management</font></legend>


<table>
	<tr>
		<td>	Group Name
		</td>
		<td>	
			<input type="text" name="groupName" >
		</td>
	</tr>
	<tr>
		<td>	Process Code
		</td>
		<td>	
			<input type="text" name="processCode" >
		</td>
	</tr>
	<tr>
		<td><input type="button" value="Create"  class="pBtn11" onclick="createGroup();"/>
		</td>
	</tr>
	
</table>


<c:choose>
	<c:when test="${SwebPage.groupsSize == 0}">
		<hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No Deploy Group entries found.</font>
				</td>
			</tr>
		</tbody></table>
	</c:when>
	<c:otherwise>
		<table border="0" cellpadding="0" cellspacing="0" width="30%">
			<tr>
				<TD class="pClothBs">Select </td>
				<TD class="pClothBs">Group Name</td>
				  <TD class="pClothBs">Process Code</td>  
			</tr>
            <tbody>
            	<c:forEach var="group" items="${SwebPage.groups}" varStatus="rowStatus">
					<c:choose>
						<c:when test="${(rowStatus.index%2) == 1}">
							<tr class="worklistodd">
						</c:when>
						<c:otherwise>
							<tr class="worklisteven">
						</c:otherwise>
					</c:choose>
						<td class="stab" align="left">
							<input type="checkbox" name="selectedList[${rowStatus.index}]" value="${group.groupName}~${group.processCode}"/>
						</td>
						<td class="stab"  align="left">${group.groupName}</td>
						<td class="stab"  align="left">${group.processCode}</td>
					 </tr>	            	
            	</c:forEach>
			<tbody>				
		</table>
				
			</c:otherwise>
	</c:choose>
		
		<c:if test="${SwebPage.groupsSize > 0}">
			<input type="button" value="Delete"  class="pBtn11" onclick="deleteGroup();"/>
		</c:if>

</form>


<script type="text/javascript">
// submit the form with post method
function doPost(formObj, actionURL) {
	formObj.method = "post";
	formObj.action = actionURL;
	return formObj.submit();
}

// submit the form with get method
function doGet(actionURL) {
	location.href  = actionURL;
	return true;
}


// delete
function deleteGroup() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/ProcessDeployGroupManagePage/deleteGroup.sweb"/>');
	return false;
}


// create
function createGroup() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/ProcessDeployGroupManagePage/createGroup.sweb"/>');
	return false;
}

</script>


<!-- WF Admin Footer starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageFooter.jsp"/>
<!-- WF Admin Footer end -->