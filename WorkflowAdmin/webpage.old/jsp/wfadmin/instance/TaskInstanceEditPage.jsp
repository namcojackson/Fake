<%@ page import="com.canon.cusa.s21.framework.workflow.common.sweb.core.WfSwebActionErrors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
    function backToProcessInstEditPage()
    {
        doGet('<c:url value="/wfadmin/instance/ProcessInstanceEditPage/showPage.sweb?procInstID=${SwebPage.procInstID}"/>');
        return false;
    }
    
    function backToTaskInstSearchPage()
    {
        doGet('<c:url value="/wfadmin/instance/TaskInstanceSearchPage/showPage.sweb?needSearch=true&backAction=true"/>');
        return false;
    }    
    
	// submit the form with get method
	function doGet(actionURL) {
		location.href  = actionURL;
		return true;
	}    
</script>

<!--Task Instance Edit Page-->
<form  id="resultForm" method="get" action='<c:url value="/wfadmin/instance/TaskInstanceEditPage/executeAction.sweb"/>'>
<legend><font size="3" style="font-weight:bold">Update Task Instance</font></legend>
<%--Message Area --%>
<span style="color:#ff0000"><c:out value="${SwebPage.currentErrorMessage}"/></span>
<% session.removeAttribute(WfSwebActionErrors.GLOBAL_ERROR);%>

    <input type="hidden" name="taskInstID" id="taskInstID" value="${SwebPage.taskInstID}"/>
	<input type="hidden" name="taskCode" value="${SwebPage.workItem.taskCode}" />
	<input type="hidden" name="taskSearchSourcePage" value="${SwebPage.taskSearchSourcePage}" />
	
	<table border="0" cellpadding="0" cellspacing="0" width="800">
			<thead>
				<tr>
					
					<th class="pClothBs">Task Code</th>
					<th class="pClothBs">Task Name</th>
					<th class="pClothBs">Comp Id</th>
					<th class="pClothBs">User</th>
					<th class="pClothBs">Start Date</th>
					<th class="pClothBs">End Date</th>
					<th class="pClothBs">Task Instance Id</th>
				</tr>
			</thead>
			<tbody>
			  <tr>
				<td class="stab" align="center">${SwebPage.workItem.taskCode}</td>
				<td class="stab" align="center">${SwebPage.workItem.nodeName}</td>
				<td class="stab" align="center">${SwebPage.workItem.taskComp}</td>
				<td class="stab" align="center">${SwebPage.workItem.taskInstUserId}</td>
				<td class="stab" align="center"><fmt:formatDate value="${SwebPage.workItem.taskStartDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
				<td class="stab" align="center"><fmt:formatDate value="${SwebPage.workItem.taskEndDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
				<td class="stab" align="center">${SwebPage.workItem.taskInstId}</td>
		      </tr>
		    </tbody>
	</table>
	
    <HR>
	<table>
		
			  <c:if test="${SwebPage.userInRole}">
			  	<tr>
		          <td class="stab">Task Assigned to:</td>
				  <td class="stab">
					<select name="taskInstUserId" style="WIDTH: 150px;">
						<c:forEach var="user" items="${SwebPage.userListForRole}">
							<option value='${user}' ${(user == SwebPage.workItem.taskInstUserId) ?'selected':''}>${user}</option>
						</c:forEach>
					</select>
				   </td>
				</tr>
				<tr>
			      <td class="stab">Delegator:</td>
				  <td class="stab">
					<select name="delegatorId" style="WIDTH: 150px;">
						<c:forEach var="user" items="${SwebPage.userListForRole}">
							<option value='${user}' ${(user == SwebPage.workItem.taskInstDelegatorId) ?'selected':''}>${user}</option>
						</c:forEach>
					</select>
				   </td>
				   </tr>	
			  </c:if>
			  <c:if test="${!SwebPage.userInRole}">
		  			<tr>
	                   <td class="stab">Task Assigned to:</td>
	                   <td class="stab">${(null == SwebPage.workItem.taskInstUserId) ?'NA':SwebPage.workItem.taskInstUserId}  </td>
                   </tr>
                   <tr>
	                   <td class="stab">Delegator:</td>
	                   <td class="stab">${(null == SwebPage.workItem.taskInstDelegatorId) ?'NA':SwebPage.workItem.taskInstDelegatorId}  </td>
                   </tr>
			  </c:if>			
		</tr>
		<tr>
			<td class="stab">Hierarchy Level:</td>
			<td class="stab">
				<c:choose>
					<c:when test="${SwebPage.hierarchyLevel == null}">
						NA
					</c:when>
					<c:otherwise>
						<input type="text" name="hierarchyLevel" value='${SwebPage.hierarchyLevel}'/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="stab">Task Due Date (MMddyyyy):</td>
			<td class="stab"><input type="text" maxlength="8" name="taskDueDate" value='${SwebPage.taskDueDate}'/></td>
		</tr>
		<tr>
			<td class="stab">Task DA Attr1:</td>
			<td class="stab"><input type="text" name="taskDataAccessAttribute1" id="taskDataAccessAttribute1" value="${SwebPage.taskDataAccessAttribute1}"/></td>
		</tr>
		<tr>
			<td class="stab">Task DA Attr2:</td>
			<td class="stab"><input type="text" name="taskDataAccessAttribute2" id="taskDataAccessAttribute2" value="${SwebPage.taskDataAccessAttribute2}"/></td>
		</tr>
		<tr>
			<td class="stab">Task DA Attr3:</td>
			<td class="stab"><input type="text" name="taskDataAccessAttribute3" id="taskDataAccessAttribute3" value="${SwebPage.taskDataAccessAttribute3}"/></td>
		</tr>
		<tr>
			<td class="stab">Task DA Attr4:</td>
			<td class="stab"><input type="text" name="taskDataAccessAttribute4" id="taskDataAccessAttribute4" value="${SwebPage.taskDataAccessAttribute4}"/></td>
		</tr>
                <tr>
			<td class="stab">Comments(if any):</td>
			<td class="stab"><input type="text" name="comments" id="comments" value="${SwebPage.comments}" maxlength="250" /></td>
		</tr>		
	</table>	

	<table>
		<tr>
			<td class="stab"><input type="submit" class="pBtn7" name="action" value="Update" class="cBtn" ${(SwebPage.workItem.processInstance.endDate != null ||  SwebPage.workItem.taskEndDate != null )?'disabled':''}></td>
			<td class="stab">
				<c:choose>
					<c:when test="${SwebPage.taskSearchSourcePage}">
						<input type="button" value="Back" class="pBtn7" onclick="backToTaskInstSearchPage();return false;"/>
					</c:when>
					<c:otherwise>
						<input type="button" value="Back" class="pBtn7" onclick="backToProcessInstEditPage();return false;"/>
					</c:otherwise>
				</c:choose>
			</td>
			
		</tr>
	</table>
	
	
</form>

