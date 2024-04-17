<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--wfadmin-->

<!--Search Criteria section-->
<form method="get" action='<c:url value="/wfadmin/instance/TaskInstanceUserSearchPage/showPage.sweb"/>'>
    
    <legend><font size="3" style="font-weight:bold">Users for Task </font></legend>
<br/>

<!--Search Result section-->
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


</script>
        
	<table border="0" cellpadding="0" cellspacing="0" width="600">
			<thead>
				<tr>
					
					<th class="pClothBs">Task Code</th>
					<th class="pClothBs">Task Role</th>
					<th class="pClothBs">Doc Id</th>
					<th class="pClothBs">Task Instance Id</th>
				</tr>
			</thead>
			<tbody>
			  <tr>
				<td class="stab" align="center">${SwebPage.taskCode}</td>
				<td class="stab" align="center">${SwebPage.taskRole}</td>
				<td class="stab" align="center">${SwebPage.documentId}</td>
				<td class="stab" align="center">${SwebPage.taskInstID}</td>
		      </tr>
		    </tbody>
	</table>        
	
         <hr>	
	<!--For User In Role  -->	
	
		<c:if test="${SwebPage.userInRole}">
        <table border="0" cellpadding="0" cellspacing="0"><tbody>		
		<tr>
			<td class="stab" nowrap>ToDo User:</td>
			<td class="stab" width="10px">&nbsp;</td>	
			<td class="stab" align="left" nowrap>
			   ${SwebPage.actorId} 
			</td>
		</tr>
	</tbody></table>
	 <hr>
	<table border="0" cellpadding="0" cellspacing="0" ><tbody>
		<tr>
			<td class="stab" nowrap>Delegator:</td>
			<td class="stab" width="10px">&nbsp;</td>	
			<td class="stab" align="left" nowrap>
			 ${SwebPage.delegatorId}
			</td>
		</tr>
	</tbody></table>
	 <hr>
	<table border="0" cellpadding="0" cellspacing="0"><tbody>
		<tr>
			<td class="stab" nowrap>CanDo Users:</td>
			<td class="stab" width="10px">&nbsp;</td>	
			<td class="stab"  align="left" nowrap>
			        <c:forEach var="canDoUser" items="${SwebPage.canDoUserList}">
			                     ${canDoUser} ,
			        </c:forEach>

			</td>
		</tr>	
	</tbody></table>	
		</c:if>
		<!--For Assign Mode Role  -->
	
		<c:if test="${!SwebPage.userInRole}">
	
	<table border="0" cellpadding="0" cellspacing="0"><tbody>	
		<tr>
			<td class="stab" nowrap>To Do Users:</td>
			<td class="stab" width="10px">&nbsp;</td>	
			<td class="stab" nowrap>
				  <c:forEach var="toDoUserListForRole" items="${SwebPage.toDoUserListForRole}">
					     ${toDoUserListForRole} ,
			        </c:forEach>
			</td>
		</tr>	
         </tbody></table>		
		</c:if>
	
	
	<table border="0" cellpadding="0" cellspacing="0"><tbody>
		<tr>
					<td class="stab" nowrap colspan="5" align="left">
						<input type="button" class="pBtn7" value="Back" onclick="javascript:window.history.back();return false;"/>
					</td>
				</tr>
	</tbody></table>
	
</form>




