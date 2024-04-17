<%@ page import="org.jbpm.job.Timer" %>
<%@ page import="org.jbpm.job.ExecuteNodeJob" %>
<%@ page import="org.jbpm.job.ExecuteActionJob" %>
<%@ page import="com.canon.cusa.s21.framework.workflow.core.jbpmx.util.HibernateProxyUtil" %>
<%@ page import="com.canon.cusa.s21.framework.common.S21StringUtil" %>
<%@ page import="org.jbpm.job.Job" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    function saveInstance()
    {
        doPost($('resultForm'), '<c:url value="/wfadmin/instance/JobInstanceEditPage/save.sweb"/>');
        return false;
    }

    function back()
    {
        doPost($('resultForm'), '<c:url value="/wfadmin/instance/JonInstanceManagePage/showPage.sweb?backAction=true&needSearch=true"/>');
        return false;
    }
</script>

<form id="resultForm" method="POST" action='<c:url value="/wfadmin/instance/JobInstanceEditPage/showPage.sweb"/>'>

    <legend><font size="3" style="font-weight:bold">Edit Job </font></legend>

    <table style="table-layout:absolute" border="1" cellpadding="0" cellspacing="0" width="600px">

        <thead>
            <tr>
                <th class="pClothBs" width="100px" align="center">Type</th>
                <th class="pClothBs" width="20px" align="center">JobId</th>
                <th class="pClothBs" width="50px" align="center">ProcInst</th>
                <th class="pClothBs" width="80px" align="center">Due Date</th>
            </tr>
        </thead>

        <c:forEach var="t" items="${SwebPage.activeJobs}" varStatus="rowStatus">


            <input type="hidden" name="jobIdList[${rowStatus.index}]" value="${t.id}"/>
            <c:choose>
                <c:when test="${(rowStatus.index%2) == 1}">
                    <tr class="worklistodd">
                </c:when>
                <c:otherwise>
                    <tr class="worklisteven">
                </c:otherwise>
            </c:choose>
            <td class="stab">${SwebPage.jobTypes[t.id]}</td>
            <td class="stab">${t.id}</td>
            <td class="stab">${t.procInstId}</td>
            <%--td class="stab">
                ${t.processInstance.processDefinition.name}
            </td--%>
            <td class="stab">
                <input type="text" name="dueDate[${rowStatus.index}]" value='<fmt:formatDate value="${t.dueDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/>'/>
            </td>
            </tr>
        </c:forEach>
    </table>
    
    <input type="button" value="Save" class="pBtn7" onclick="saveInstance();return false;"/>
    <input type="button" value="Back" class="pBtn7" onclick="back();return false;"/>
    <br>    <br>
    
   
    
    <c:if test="${SwebPage.jobMappings != null}">
    	The jobs are updated successfully. <br>
    	Please note that new Job is created for each modified Job. 
    	And the modified Job is archived.  <br>
    
		<table style="table-layout:absolute" border="1" cellpadding="0" cellspacing="0" width="600px">
		
		        <thead>
		            <tr>
		                <th class="pClothBs" width="100px" align="center">Original Job Id</th>
		                <th class="pClothBs" width="120px" align="center">Original Due Date</th>
		                <th class="pClothBs" width="100px" align="center">New Job Id</th>
		                <th class="pClothBs" width="120px" align="center">New Due Date</th>
		            </tr>
		        </thead>
		
		        <c:forEach var="jobMapping" items="${SwebPage.jobMappings}" varStatus="rowStatus">
		
			
		            <c:choose>
		                <c:when test="${(rowStatus.index%2) == 1}">
		                    <tr class="worklistodd">
		                </c:when>
		                <c:otherwise>
		                    <tr class="worklisteven">
		                </c:otherwise>
		            </c:choose>
		            <td class="stab">
		                    ${jobMapping.originalJob.id}
		            </td>
		            <td class="stab">
		                <fmt:formatDate value="${jobMapping.originalJob.dueDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/>
		            </td>
		            <td class="stab">
		                    ${jobMapping.job.id}
		            </td>
		            <td class="stab">
		                <fmt:formatDate value="${jobMapping.originalJob.dueDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/>
		            </td>
		            </tr>
		        </c:forEach>
    </table>    
    	
    </c:if>


</form>