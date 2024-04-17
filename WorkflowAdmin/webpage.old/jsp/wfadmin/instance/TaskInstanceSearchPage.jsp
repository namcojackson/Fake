<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--wfadmin-->

<!--Search Criteria section-->
<form method="get" action='<c:url value="/wfadmin/instance/TaskInstanceSearchPage/search.sweb"/>'>
    
    <legend><font size="3" style="font-weight:bold">Search Task Instance </font></legend>
	<table border="0" cellpadding="0" cellspacing="0" width="575"><tbody>
		<tr>
			<td class="stab" nowrap>Process Code:</td>
			<td class="stab" nowrap>
				<select name="searchParam.processCodeStrList" id="processCodeDropDown"  onchange='loadTaskCodesByProcessCode(this)' >
				       <option value=""></option>
				    <c:forEach var="code" items="${SwebPage.processCodes}">
 				       <option value='${code}' ${(SwebPage.searchParam.processCodeStrList == code)?'selected':''} >${code}</option>
				    </c:forEach>
		                </select>
			</td>
			<td class="stab" width="10px">&nbsp;</td>
			<td class="stab" nowrap>Task Code:</td>
			<td class="stab" nowrap>
				<select name="searchParam.taskCodeStrList" style="WIDTH:150px;">
				    <option value=""></option>
				    <c:forEach var="taskcode" items="${SwebPage.taskCodes}">
				       <option value='${taskcode}' ${(SwebPage.searchParam.taskCodeStrList == taskcode)?'selected':''} >${taskcode}</option>
				    </c:forEach>
		                </select>
			</td>
		</tr>
		<tr>
			<td class="stab" nowrap>Document ID:</td>
			<td class="stab" nowrap>
			        <input type="text" style="width: 250px" name="searchParam.documentId" value="${SwebPage.searchParam.documentId}"/>
			</td>
			<td class="stab" width="10px">&nbsp;</td>
			<td class="stab" nowrap>User Id:</td>
			<td class="stab" nowrap>
			   <input type="text" style="width: 250px" name="userIdSearchCriteria" value="${SwebPage.userIdSearchCriteria}"/>
			</td>
			<td class="stab" width="10px">&nbsp;</td>
		</tr>
		<!--Removed process due date for testing -->
		<tr>
			<td class="stab" nowrap>Create Date From:(mmddyyyy)</td>
			<td class="stab" nowrap>
				<input type="text" style="width: 250px" name="searchParam.taskStartDateAfterStr" value="${SwebPage.searchParam.taskStartDateAfterStr}"/>
			</td>
			<td class="stab" width="10px">&nbsp;</td>		
			<td class="stab" nowrap>Create Date To:(mmddyyyy)</td>
			<td class="stab" nowrap>
				<input type="text" style="width: 250px" name="searchParam.taskStartDateBeforeStr" value="${SwebPage.searchParam.taskStartDateBeforeStr}"/>
			</td>		
		</tr>
		<tr>
			<td class="stab" nowrap>Due Date From:(mmddyyyy)</td>
			<td class="stab" nowrap>
			    <input type="text" style="width: 250px" name="searchParam.taskDueDateAfterStr" value="${SwebPage.searchParam.taskDueDateAfterStr}"/>
			</td>
			<td class="stab" width="10px">&nbsp;</td>		
			<td class="stab" nowrap>Due Date To:(mmddyyyy)</td>
			<td class="stab" nowrap>
                            <input type="text" style="width: 250px" name="searchParam.taskDueDateBeforeStr" value="${SwebPage.searchParam.taskDueDateBeforeStr}"/>
			</td>		
		</tr>	
		<tr>
		    <td class="stab" nowrap>Find Work Items No user can work on:</td>
			<td class="stab" nowrap>
			   <input type="checkbox" name="workItemNoUserCanWork" onclick="disableSearchFields()" value="true"  ${(SwebPage.workItemNoUserCanWork eq "true")?'checked':''}>
			</td>
			<td class="stab" width="10px">&nbsp;</td>	
			<td class="stab" nowrap>Instance Status:</td>
			<td class="stab" nowrap>
				<select name="searchParam.taskInstanceStatus" >
					<option value="All">All</option>
					<option value="processing" ${(SwebPage.searchParam.taskInstanceStatus eq "processing")?'selected':''}>In Progress</option>
					<option value="completed" ${(SwebPage.searchParam.taskInstanceStatus eq "completed")?'selected':''}>Completed</option>
					<option value="suspended" ${(SwebPage.searchParam.taskInstanceStatus eq "suspended")?'selected':''}>Suspended</option>
	             		</select>
			</td>

				
		</tr>
		<tr>
			<td class="stab" nowrap>Max result count:</td>
			<td class="stab" nowrap colspan="4" align="left">
				<select name="searchParam.maxResultCount" style="WIDTH: 100px;">
					<c:forEach var="selection" items="${SwebPage.maxResultCountSelections}" varStatus="rowStatus">
						<option value="${selection}" ${(SwebPage.searchParam.maxResultCount == selection)?'selected':''}>${selection}</option>
					</c:forEach>
				</select>				
			</td>

		</tr>		
		<tr>
			<td class="stab" nowrap colspan="5" align="left">
				<input type="submit" class="pBtn7" value="Search"/>
				<input type="button" class="pBtn7" value="Back" onclick="backToProcInsPage();return false;"/>
			</td>
		</tr>
	</tbody></table>
</form>

<br/>

<!--Search Result section-->
<script type="text/javascript">

    function backToProcInsPage()
    {
        doPost($('resultForm'), '<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/showPage.sweb?backAction=true&needSearch=true"/>');
        return false;
    } 
    
    
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
// edit task
function editInstance() {
	doPost($('resultForm'), '<c:url value="/wfadmin/instance/TaskInstanceSearchPage/editTask.sweb"/>');
	return false;
}


function loadTaskCodesByProcessCode(elem) {
        doGet('<c:url value="/wfadmin/instance/TaskInstanceSearchPage/loadTaskCode.sweb?searchParam.processCodeStrList=' +   elem.value  +   '"/>');
	return true;
}


/**
*  Disble on search fields
*/
function disableSearchFields(){
	//class="pHsu" readonly="true"
	if(document.getElementById("workItemNoUserCanWork").checked){
		$('userIdSearchCriteria').className = 'pHsu';
		$('userIdSearchCriteria').disabled = true;
		$('userIdSearchCriteria').value = "";
		}else{
		$('userIdSearchCriteria').className = '';
		$('userIdSearchCriteria').disabled = false;
	}

}


disableSearchFields();


</script>


<form id="resultForm" method="get" action='<c:url value="/wfadmin/instance/TaskInstanceSearchPage/update.sweb"/>'>
<c:choose>
	<c:when test="${SwebPage.workItemListCount == 0}">
		<hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No task instance found...</font>
				</td>
			</tr>
		</tbody></table>
	</c:when>
	<c:when test="${SwebPage.workItemListCount > 0}">
		<hr/>
		<input type="button" class="pBtn7" value="Edit" onclick="editInstance();"/>
		result count: ${SwebPage.workItemListCount}
		<br/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<thead><tr>
				<th class="pClothBs">select</th>
				<th class="pClothBs">Users</th>				
				<th class="pClothBs">Document Id</th>
				<th class="pClothBs">Status</th>
				<th class="pClothBs">Task Code</th>
				<th class="pClothBs">Task Name</th>
				<th class="pClothBs">Task StartDate</th>
				<th class="pClothBs">Task End Date</th>
				<th class="pClothBs">Task Due Date</th>
				<th class="pClothBs">Task Inst Id</th>				
			</tr></thead>
			<tbody>
				<c:forEach var="workItem" items="${SwebPage.workItemList}" varStatus="rowStatus">
					<c:choose>
						<c:when test="${(rowStatus.index%2) == 1}">
							<tr class="worklistodd">
						</c:when>
						<c:otherwise>
							<tr class="worklisteven">
						</c:otherwise>
					</c:choose>
						<td class="stab" align="center">
						  <c:if test="${(workItem.taskStatus eq 'processing')}"> 
						   <input type="radio" name="selectedTaskId" value="${workItem.taskInstId}" ${(SwebPage.selectedTaskId eq workItem.taskInstId)?'checked':''}/>
						 </c:if>
                                                </td>
                                                <td class="stab"  align="center">
                                                  <c:if test="${(SwebPage.workItemNoUserCanWork  ne 'true')}"> 
                                                   <a href='<c:url value="/wfadmin/instance/TaskInstanceUserSearchPage/showPage.sweb?taskInstID=${workItem.taskInstId}&documentId=${workItem.documentId}&taskCode=${workItem.taskCode}&taskRole=${workItem.taskEditRoleId}"/>'>Users</a>
                                                  </c:if>
                                                </td>
						<td class="stab"  align="center">${workItem.documentId}</td>
			                        <td class="stab"  align="center">${workItem.taskStatus}</td>						
						<td class="stab"  align="center">${workItem.taskCode}</td>
						<td class="stab"  align="center">${workItem.taskName}</td>
						<td class="stab"  align="center"><fmt:formatDate value="${workItem.taskStartDate}" type="both" pattern="MM/dd/yyyy HH:mm"/></td>
						<td class="stab" align="center"><fmt:formatDate value="${workItem.taskEndDate}" type="both" pattern="MM/dd/yyyy HH:mm"/></td>
                        			<td class="stab" align="center"><fmt:formatDate value="${workItem.taskDueDate}" type="both" pattern="MM/dd/yyyy HH:mm"/></td>
                        			<td class="stab"  align="center">${workItem.taskInstId}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="button" class="pBtn7" value="Edit" onclick="editInstance();"/>
	</c:when>
	<c:otherwise>
		<!--<hr/>-->
		<!--Not a search result. Don't display anything-->
		<br/>
	</c:otherwise>
</c:choose>



</form>




