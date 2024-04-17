<!-- WF Admin Header starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageHeader.jsp"/>
<!-- WF Admin Header end -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form id="resultForm" method="POST" action='<c:url value="/wfadmin/jobx/JobDispatcherConfigManagePage/showPage.sweb"/>'>



<legend><font size="3" style="font-weight:bold">Job Dispatcher Config Administration</font></legend>


<table>
	<tr>
		<td>	Thread name
		</td>
		<td>	
			<input type="text" name="threadName" >
		</td>
	</tr>
	<tr>
		<td>	Enable
		</td>
		<td>	
			<input type="checkbox" name="enabled"  value="true">
		</td>
	</tr>
	<tr>
		<td><input type="button" value="Create"  class="pBtn11" onclick="createConfigs();"/>
		</td>
	</tr>
	
</table>


<c:choose>
	<c:when test="${SwebPage.configsSize == 0}">
		<hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No Config entries found.</font>
				</td>
			</tr>
		</tbody></table>
	</c:when>
	<c:otherwise>
		<table border="0" cellpadding="0" cellspacing="0" width="30%">
			<tr>
				<TD class="pClothBs">Select </td>
				<TD class="pClothBs">Thread Name</td>
				  <TD class="pClothBs">Is Enabled</td>  
			</tr>
            <tbody>
            	<c:forEach var="config" items="${SwebPage.configs}" varStatus="rowStatus">
					<c:choose>
						<c:when test="${(rowStatus.index%2) == 1}">
							<tr class="worklistodd">
						</c:when>
						<c:otherwise>
							<tr class="worklisteven">
						</c:otherwise>
					</c:choose>
						<td class="stab" align="left">
							<input type="checkbox" name="selectedList[${rowStatus.index}]" value="${config.name}"/>
						</td>
						<td class="stab"  align="left">${config.name}</td>
						<td class="stab"  align="left">${config.enabled}</td>
					 </tr>	            	
            	</c:forEach>
			<tbody>				
		</table>
				
			</c:otherwise>
	</c:choose>
		
		<c:if test="${SwebPage.configsSize > 0}">
			<input type="button" value="Delete"  class="pBtn11" onclick="deleteConfigs();"/>
			<input type="button" value="Enable"  class="pBtn11" onclick="enableConfigs();"/>
			<input type="button" value="Disable"  class="pBtn11" onclick="disableConfigs();"/>
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
function deleteConfigs() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/JobDispatcherConfigManagePage/deleteConfigs.sweb"/>');
	return false;
}

// enable
function enableConfigs() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/JobDispatcherConfigManagePage/enableConfigs.sweb"/>');
	return false;
}

// disable
function disableConfigs() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/JobDispatcherConfigManagePage/disableConfigs.sweb"/>');
	return false;
}

// create
function createConfigs() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/JobDispatcherConfigManagePage/createConfigs.sweb"/>');
	return false;
}

</script>


<!-- WF Admin Footer starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageFooter.jsp"/>
<!-- WF Admin Footer end -->