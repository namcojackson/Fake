<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--wfadmin-->

<!--Search Criteria section-->
<form id="resultForm" method="GET" action='<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/search.sweb"/>'>
    
    <legend><font size="3" style="font-weight:bold">Search Process Instance </font></legend>
	<table border="0" cellpadding="0" cellspacing="0" width="575"><tbody>
		<tr>
			<td class="stab" nowrap>Process Code</td>
			<td class="stab" nowrap>
				<select name="searchParam.processCodeStrList" >
					<option value=""></option>
	                <c:forEach var="code" items="${SwebPage.processCodes}">
	                    <option value='${code}' ${(SwebPage.searchParam.processCodeStrList == code)?'selected':''}>${code}</option>
		            </c:forEach>
		        </select>	
				
			</td>
			<td class="stab" width="10px">&nbsp;</td>
			<td class="stab" nowrap>Document Id</td>
				<td class="stab" nowrap>
  			        <input type="text" style="width: 250px" name="searchParam.documentId" value="${SwebPage.searchParam.documentId}"/>
			</td>
		</tr>
		<tr>
			<td class="stab" nowrap>Process Definition Id</td>
			<td class="stab" nowrap>
                    <input type="text" style="width: 250px" name="searchParam.definitionId" value="${SwebPage.searchParam.definitionId}"/>
			</td>		
			<td class="stab" width="10px">&nbsp;</td>
			<td class="stab" nowrap>Status</td>
			<td class="stab" nowrap>
		         <select name="searchParam.instanceStatus" >
		               	<option value="All">All</option>
 						<option value="processing" ${(SwebPage.searchParam.instanceStatus eq "processing")?'selected':''}>processing</option>
 						<option value="completed" ${(SwebPage.searchParam.instanceStatus eq "completed")?'selected':''}>completed</option>
 						<option value="suspended" ${(SwebPage.searchParam.instanceStatus eq "suspended")?'selected':''}>suspended</option>
	             </select>				
			</td>
		</tr>
		<!--Removed process due date for testing -->
		<tr>
			<td class="stab" nowrap>Create Date From (MMddyyyy)</td>
			<td class="stab" nowrap>
				<input type="text" maxlength="8" style="width: 250px" name="searchParam.startDateAfterStr" value="${SwebPage.searchParam.startDateAfterStr}"/>
			</td>
			<td class="stab" width="10px">&nbsp;</td>		
			<td class="stab" nowrap>Create Date To (MMddyyyy)</td>
			<td class="stab" nowrap>
				<input type="text" maxlength="8"  style="width: 250px" name="searchParam.startDateBeforeStr" value="${SwebPage.searchParam.startDateBeforeStr}"/>
			</td>		
		</tr>
		<tr>
			<td class="stab" nowrap>Due Date From (MMddyyyy)</td>
			<td class="stab" nowrap>
		             <input type="text" maxlength="8"  style="width: 250px" name="searchParam.dueDateAfterStr" value="${SwebPage.searchParam.dueDateAfterStr}"/>
			</td>
			<td class="stab" width="10px">&nbsp;</td>		
			<td class="stab" nowrap>Due Date To (MMddyyyy)</td>
			<td class="stab" nowrap>
                    <input type="text" maxlength="8"  style="width: 250px" name="searchParam.dueDateBeforeStr" value="${SwebPage.searchParam.dueDateBeforeStr}"/>
			</td>		
		</tr>	
		<tr>
			<td class="stab" nowrap>Max result count</td>
			<td class="stab" nowrap >
				<select name="searchParam.maxResultCount" style="WIDTH: 100px;">
					<c:forEach var="selection" items="${SwebPage.maxResultCountSelections}" varStatus="rowStatus">
						<option value="${selection}" ${(SwebPage.searchParam.maxResultCount == selection)?'selected':''}>${selection}</option>
					</c:forEach>
				</select>
			</td>
			<td class="stab" width="10px">&nbsp;</td>		
			<td class="stab" nowrap>Requested By</td>
			<td class="stab" nowrap>
                    <input type="text" style="width: 250px" name="searchParam.requestedBy" value="${SwebPage.searchParam.requestedBy}"/>
			</td>								
		</tr>
		<tr>
			<td class="stab" nowrap colspan="5" align="left">
				<input type="submit" class="pBtn7" value="Search"/>
				<input type="button" class="pBtn7" value="Back" onclick="back();return false;"/>
			</td>
		</tr>
	</tbody></table>


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
	
	// edit
	function back() {
		doPost($('resultForm'), '<c:url value="/wfadmin/menu/MenuPage/showPage.sweb"/>');
		return false;
	}
	
		
	// edit
	function editInstance() {
		if(checkOneItemSelection()){
			doPost($('resultForm'), '<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/editProcess.sweb"/>');
		}
		return false;
	}
	
	// cancel process
	function abortInstance() {
	    if(checkkSelection()){
			if (confirm("Are you sure to Abort the following Process Instances: "+ getConfirmationMsg())) {
				doPost($('resultForm'), '<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/abortProcess.sweb"/>');
			}
		}
		return false;
	}
	
	// suspend process
	function suspendInstance() {
		if(checkkSelection()){
			if (confirm("Are you sure to Suspend the following Process Instances: "+ getConfirmationMsg())) {
				doPost($('resultForm'), '<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/suspendProcess.sweb"/>');
			}
		}
		return false;
	}
	
	// resume process
	function resumeInstance() {
		if(checkkSelection()){
			if (confirm("Are you sure to Resume the following Process Instances: "+ getConfirmationMsg())) {
				doPost($('resultForm'), '<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/resumeProcess.sweb"/>');
			}
		}
		return false;
	}


	function checkkSelection(){
    	//atleast one checkbox should be selected

	    var flag = false;
	    var length = 1;
	    for(var i =0; i < length ; i++){
	        var element = "processInstanceId["+i+"]";
	        var checkBoxElement = document.getElementById(element);
	        if(checkBoxElement == null){
	         	flag = false;
	         	break;
	        }else if(checkBoxElement.checked){
			    flag = true;
			    break;
			}else{
				//not checked. loop for more checkboxes.
			}
			length++;
	    }

	    if(!flag){
	        alert("Please select a Process Instance.");
	    }
	    return flag;    	
    }
    
	function checkOneItemSelection(){
    	//Only one checkbox should be selected

	    var oneItemSelected = false;
	    var count = 0;
	    var length = 1;
	    for(var i =0; i < length ; i++){
	        var element = "processInstanceId["+i+"]";
	        var checkBoxElement = document.getElementById(element);
	        if(checkBoxElement == null){
	        	//no chkbox found
	         	break;
	        }else if(checkBoxElement.checked){
			    count++;
			    oneItemSelected = true;
			    if(count > 1){
			    	oneItemSelected = false;
			    	break;
			    }
			}else{
				//not checked. loop for more checkboxes.
			}
			length++;
	    }

	    if(!oneItemSelected){
	        alert("Please select only one Process Instance.");
	    }
	    return oneItemSelected;    	
    }    
    
    function getConfirmationMsg(){
    	var msg = "";
	    var length = 1;
	    for(var i =0; i < length ; i++){
	        var element = "processInstanceId["+i+"]";
	        var checkBoxElement = document.getElementById(element);
	        if(checkBoxElement == null){
	         	break;
	        }else if(checkBoxElement.checked){
	        	var docIdElement = "documentId["+i+"]";
	        	msg = msg + '\n' + document.getElementById(docIdElement).value;
			}else{
				//not checked. loop for more checkboxes.
			}
			length++;
	    }
	    
	    return msg;    
    
    }

</script>

<c:choose>
	<c:when test="${SwebPage.processListCount == 0}">
		<hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No Process Instances found.</font>
				</td>
			</tr>
		</tbody></table>
	</c:when>
	<c:when test="${SwebPage.processListCount > 0}">
		<hr/>
		<input type="button" class="pBtn7" value="Suspend" onclick="suspendInstance();return false;"/>
		<input type="button" class="pBtn7" value="Resume" onclick="resumeInstance();return false;"/>
		<input type="button" class="pBtn7" value="Abort" onclick="abortInstance();return false;"/>
		<input type="button" class="pBtn7" value="Edit" onclick="editInstance();return false;"/>
		Results : ${SwebPage.processListCount}
		<br/>
		
   <div>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th class="pClothBs">Select</th>
					<th class="pClothBs">History</th>
					<th class="pClothBs">Tasks</th>
					<th class="pClothBs">Jobs</th>
					<th class="pClothBs">Process Code</th>
					<th class="pClothBs">Document ID</th>
					<th class="pClothBs">Status</th>					
					<th class="pClothBs">Start Date</th>
					<th class="pClothBs">End Date</th>
					<th class="pClothBs">Due Date</th>
					<th class="pClothBs">Requested By</th>				
					<th class="pClothBs">Proc Def Id</th>
					<th class="pClothBs">Proc Inst ID</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="process" items="${SwebPage.processList}" varStatus="rowStatus">
					<c:choose>
						<c:when test="${(rowStatus.index%2) == 1}">
							<tr class="worklistodd">
						</c:when>
						<c:otherwise>
							<tr class="worklisteven">
						</c:otherwise>
					</c:choose>
						<input type="hidden" name="processList[${rowStatus.index}].instanceId" value="${process.instanceId}"/>
						<input type="hidden" name="documentId[${rowStatus.index}]" value="${process.documentId}"/>
						<td class="stab" align="center">
							<input type="checkbox" id="processInstanceId[${rowStatus.index}]" name="processList[${rowStatus.index}].selected" value="true" />
						</td>
						<td class="stab"  align="center"><a href="#" onclick="s21WfOpenTaskHistoryWindowByProcInstId(${process.instanceId})">History</a></td>
						<td class="stab"  align="center"><a href='<c:url value="/wfadmin/instance/TaskInstanceSearchPage/showPage.sweb?searchParam.processInstanceId=${process.instanceId}&needSearch=true&searchParam.documentId=${process.documentId}&searchParam.processCodeStrList=${process.code}"/>'>Tasks</a></td>
						<td class="stab"  align="center"><a href='<c:url value="/wfadmin/instance/JonInstanceManagePage/showPage.sweb?queryParam.procInstId=${process.instanceId}&needSearch=true&queryParam.documentId=${process.documentId}&queryParam.processCode=${process.code}"/>'>Jobs</a></td>
						<td class="stab"  align="center">${process.code}</td>
						<td class="stab" align="center">${process.documentId}</td>
						<td class="stab"  align="center">${process.processStatus}</td>
						<td class="stab"  align="center"><fmt:formatDate value="${process.startDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
						<td class="stab" align="center"><fmt:formatDate value="${process.endDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
						<td class="stab" align="center"><fmt:formatDate value="${process.dueDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
						<td class="stab"  align="center">${process.requestedBy}</td>
						<td class="stab"  align="center">${process.definitionId}</td>
						<td class="stab"  align="center">${process.instanceId}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		<input type="button" class="pBtn7" value="Suspend" onclick="suspendInstance();return false;"/>
		<input type="button" class="pBtn7" value="Resume" onclick="resumeInstance();return false;"/>
		<input type="button" class="pBtn7" value="Abort" onclick="abortInstance();return false;"/>
		<input type="button" class="pBtn7" value="Edit" onclick="editInstance();return false;"/>
	</c:when>
	<c:otherwise>
		<!--<hr/>-->
		<%--Else processListCount: <c:out value="${SwebPage.processListCount}"/>--%>
		<!--Not a search result. Don't display anything-->
		<br/>
	</c:otherwise>
</c:choose>


<jsp:include page="../../wfcomp/S21WfTaskHistoryPageInvoker.jsp">
	<jsp:param name="displayMode" value="static" />
	<jsp:param name="windowWidth" value="1000px" />
	<jsp:param name="windowHeight" value="480px" />
</jsp:include>

</form>
