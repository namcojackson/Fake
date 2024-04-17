<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form id="resultForm" method="POST" action='<c:url value="/wfadmin/jobx/JobDispatcherManagePage/showPage.sweb"/>'>



<legend><font size="3" style="font-weight:bold">Job Dispatcher Administration</font></legend>

<c:choose>
	<c:when test="${SwebPage.heartBeatsSize == 0}">
		<hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No Heart Beat entries found.</font>
				</td>
			</tr>
		</tbody></table>
	</c:when>
	<c:otherwise>
		<table>
			<tr>
				<TD class="pClothBs">Select </td>
				<TD class="pClothBs">Dispatcher Instance Name</td>
				<TD class="pClothBs">Last Access timestamp</td>
				  <TD class="pClothBs">Status</td>  
			</tr>
            <tbody>
            	<c:forEach var="heartBeat" items="${SwebPage.heartBeats}" varStatus="rowStatus">
					<c:choose>
						<c:when test="${(rowStatus.index%2) == 1}">
							<tr class="worklistodd">
						</c:when>
						<c:otherwise>
							<tr class="worklisteven">
						</c:otherwise>
					</c:choose>
						<td class="stab" align="center"><input type="radio" name="radioName" value="${heartBeat.name}"/></td>
						<td class="stab"  align="center">${heartBeat.name}</td>
						<td class="stab"  align="center"><fmt:formatDate value="${heartBeat.lastExecutionDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
						  <td class="stab"  align="center">
							 <c:choose>
								<c:when test="${(SwebPage.currDate.time - heartBeat.lastExecutionDate.time ) > 60000}">
									<font color="red">Not Running</font>
								</c:when>
								<c:otherwise>
									Running
								</c:otherwise>
							</c:choose>
						</td>  
					 </tr>	            	
            	</c:forEach>
			<tbody>				
		</table>
				
		Note: If there is no activity on the record for more than 60secs then the status is shown as "Not Running".
		<br>
			</c:otherwise>
	</c:choose>
		
		<input type="button" value="Refresh"  class="pBtn7" onclick="refreshHeartBeats();" />
		<c:if test="${SwebPage.heartBeatsSize > 0}">
			<input type="button" value="Delete Heart Beat record"  class="pBtn15" onclick="deleteHeartBeats();"/>
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

// refresh
function refreshHeartBeats() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/JobDispatcherManagePage/showPage.sweb"/>');
	return false;
}

// delete
function deleteHeartBeats() {
	doPost($('resultForm'), '<c:url value="/wfadmin/jobx/JobDispatcherManagePage/deleteHeartBeats.sweb"/>');
	return false;
}



</script>