<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<script type="text/javascript">

    function update()
    {
        doPost($('resultForm'), '<c:url value="/wfadmin/instance/ProcessInstanceEditPage/update.sweb"/>');
        return false;
    }
    
    function back()
    {
        doPost($('resultForm'), '<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/showPage.sweb?backAction=true&needSearch=true"/>');
        return false;
    }
</script>

<!--Edit Page-->
<form method="POST" id="resultForm" action='<c:url value="/wfadmin/instance/ProcessInstanceEditPage/executeAction.sweb"/>'>

<legend><font size="3" style="font-weight:bold">Edit Process Instance</font></legend>

<input type="hidden" name="procInstID" value="${SwebPage.procInstExtAttr.processInstanceId}"/>

<table border="0" cellpadding="0" cellspacing="0" width="850">
    <thead>
        <tr>
            <th class="pClothBs">Process Code</th>
            <th class="pClothBs">Document Id</th>
            <th class="pClothBs">Application Code</th>
            <th class="pClothBs">Requested By</th>
            <th class="pClothBs">Start Date</th>
            <th class="pClothBs">End Date</th>
            <th class="pClothBs">Process Def ID</th>
			<th class="pClothBs">Process Inst Id</th>            
        </tr>
    </thead>
    <tbody>
        <tr>
        	<td class="stab" align="center">${SwebPage.procInstExtAttr.processCode}</td>
            <td class="stab" align="center">${SwebPage.procInstExtAttr.documentId}</td>
            <td class="stab" align="center">${SwebPage.procInstExtAttr.applicationCode}</td>
            <td class="stab" align="center">${SwebPage.procInstExtAttr.requestedBy}</td>
            <td class="stab" align="center">${SwebPage.processInstance.start}</td>
            <td class="stab" align="center">${SwebPage.processInstance.end}</td>
            <td class="stab" align="center">${SwebPage.procInstExtAttr.processDefinitionId}</td>
            <td class="stab" align="center">${SwebPage.procInstExtAttr.processInstanceId}</td>
        </tr>
    </tbody>
</table>

<br/>
<hr/>
<legend><font size="3" style="font-weight:bold">Task Instances</font></legend>
<c:choose>
<c:when test="${SwebPage.activeNodesSize > 0 }">
	
	<table border="0" cellpadding="0" cellspacing="0" width="850">
	    <thead>
	        <tr>
	            <th class="pClothBs"></th>
	            <th class="pClothBs">Comp Id</th>
	            <th class="pClothBs">Task Code</th>
	            <th class="pClothBs">Task Name</th>
	            <th class="pClothBs">User</th>
	            <th class="pClothBs">Task Start Date</th>
	            <th class="pClothBs">Task End Date</th>
	            <th class="pClothBs">Task Inst Id</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="wi" items="${SwebPage.activeNodes}" varStatus="rowStatus">
	        <c:choose>
	        <c:when test="${(rowStatus.index%2) == 1}">
	        <tr class="worklistodd">
	            </c:when>
	            <c:otherwise>
	        <tr class="worklisteven">
	            </c:otherwise>
	            </c:choose>
	            <td align="center">
	            	<c:choose>
	            		<c:when test="${SwebPage.processInstance.end != null || wi.taskEndDate != null}">
							<a href='<c:url value="/wfadmin/instance/ProcessInstanceEditPage/executeAction.sweb?action=EditTask&selectedTaskId=${wi.taskInstId}"/>'>View</a>	            			
	            		</c:when>
	            		<c:otherwise>
	            			<a href='<c:url value="/wfadmin/instance/ProcessInstanceEditPage/executeAction.sweb?action=EditTask&selectedTaskId=${wi.taskInstId}"/>'>Edit</a>
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	            <td class="stab" align="center">${wi.taskComp}</td>
	            
	            <td class="stab" align="center">${wi.taskCode}</td>
	            <td class="stab" align="center">${wi.nodeName}</td>
	            <td class="stab" align="center">${wi.taskInstUserId}</td>
	            <td class="stab" align="center"><fmt:formatDate value="${wi.taskStartDate}" type="both" pattern="MM/dd/yyyy HH:mm"/></td>
	            <td class="stab" align="center"><fmt:formatDate value="${wi.taskEndDate}" type="both" pattern="MM/dd/yyyy HH:mm"/></td>
	            <td class="stab" align="center">${wi.taskInstId}</td>
	        </tr>
	        </c:forEach>
	        <tr>
	            <td><!-- input type="submit" class="cBtn" name="action" value="EditTask"/--></td>
	        </tr>
	</table>
</c:when>
<c:otherwise>
	<br/>
	No Task Instances found.
</c:otherwise>
</c:choose>

<br/>
<hr/>
<table>
    <tr>
        <td class="stab">Process Instance Due Date(mmddyyyy)</td>
        <td class="stab"><input type="text"  maxlength="8" name="processDeadline" value="<fmt:formatDate value="${SwebPage.procInstExtAttr.dueDate}" type="both" pattern="MMddyyyy"/>"></td>
    </tr>
</table>


<table>
    <c:if test="${not empty SwebPage.varList}">
        <tr>
            <td><b>Process Instance Variable</b></td>
            <td><b>Value</b></td>
        </tr>
        <c:forEach var="wfVar" items="${SwebPage.varList}" varStatus="rowStatus">
            <tr>
                <c:choose>
                    <c:when test="${wfVar.key == 'wf_processDeadline' or wfVar.key == 'wf_nextTaskUser'}">
                    	<input type="hidden" name="varList[${rowStatus.index}].key" value="${wfVar.key}"/>
                        <td class="stab">${wfVar.key}</td>
                        <td><input type="text" name="varList[${rowStatus.index}].value" value="${wfVar.value}" readonly="true"/></td>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="varList[${rowStatus.index}].key" value="${wfVar.key}"/>
                        <td class="stab">${wfVar.key}</td>
                        <td class="stab"><input type="text" name="varList[${rowStatus.index}].value" value="${wfVar.value}"/></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </c:if>
</table>

<hr/>
<jsp:include page="procInstExtAttrInclude.jsp"/>
<hr/>
<table>
    <tr>
        <td><input type="button" class="pBtn7" value="Update" onclick="update();return false;"  ${(SwebPage.processInstance.end != null)?'disabled':''} /></td>
        <td>
        <input type="button" value="Back" class="pBtn7" onclick="back();return false;"/>
        </td>
    </tr>
</table>





</form>
