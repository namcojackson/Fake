<!-- WF Admin Header starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageHeader.jsp"/>
<!-- WF Admin Header end -->


<%@ page import="com.canon.cusa.s21.framework.workflow.common.sweb.core.WfSwebActionErrors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
  function showDetails()
  {
      doPost($('resultForm'), '<c:url value="/wfadmin/definition/ProcessCodeManagePage/showPage.sweb"/>');
    return false;
  }

  function stopProcessCode()
  {
    doPost($('resultForm'), '<c:url value="/wfadmin/definition/ProcessCodeManagePage/stopProcessCode.sweb"/>');
    return false;
  }
  
  function resumeProcessCode()
  {
     doPost($('resultForm'), '<c:url value="/wfadmin/definition/ProcessCodeManagePage/resumeProcessCode.sweb"/>');
    return false;
  }  
</script> 

<span style="color:#ff0000"><c:out value="${SwebPage.currentErrorMessage}"/></span>
<% session.removeAttribute(WfSwebActionErrors.GLOBAL_ERROR);%>

<form id="resultForm" method="post" >
<br/>
<c:choose>
	<c:when test="${SwebPage.processCodesSize == 0}">
		<hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No Process Codes found.</font>
				</td>
			</tr>
		</tbody></table>
	</c:when>
	<c:otherwise>
		<table>
		    <tr>
		        <td class="stab">Process Code: &nbsp; </td>
		        <td>
		            <select name="processCode" >
		                <c:forEach var="code" items="${SwebPage.processCodes}">
		                    <option value='${code}' ${(SwebPage.processCode == code)?'selected':''}>${code}</option>
		                </c:forEach>
		            </select>
		        </td>
		    </tr>
		</table>

		<!--Search related buttons -->
		<table>
		    <tr>
		        <td>
		            <input type="button" class="pBtn7" value="Show Details" onclick="showDetails();return false;"/>
		            <input type="button" class="pBtn7" value="Suspend" onclick="stopProcessCode();return false;"/>
		            <input type="button" class="pBtn7" value="Resume" onclick="resumeProcessCode();return false;"/>
		        </td>
		    </tr>
		</table>
	</c:otherwise>
</c:choose>

<c:if test="${SwebPage.procExtAttrsSize > 0}">
	
	
	<!--Form related buttons -->
	<table  style="table-layout:auto;" border="0" cellpadding="0" cellspacing="0" width="600" >
	
	    <thead>
	        <tr>
	            <th class="pClothBs">Process Def Id</th>
	            <th class="pClothBs">Process Def Name</th>
	            <th class="pClothBs">Version</th>
	            <th class="pClothBs">Is Active</th>
	            <th class="pClothBs">In Service</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="procExtAttr" items="${SwebPage.procExtAttrs}" varStatus="rowStatus">
	            <c:choose>
	                <c:when test="${(rowStatus.index%2) == 1}">
	                    <tr class="worklistodd">
	                </c:when>
	                <c:otherwise>
	                    <tr class="worklisteven">
	                </c:otherwise>
	            </c:choose>
	            <td class="stab" align="center">${procExtAttr.processDefId}</td>
	            <td class="stab" align="center">${procExtAttr.processName}</td>
	            <td class="stab" align="center">${procExtAttr.processVersion}</td>
	            <td class="stab" align="center">${procExtAttr.active}</td>
	            <td class="stab" align="center"><b>${procExtAttr.inService}</b></td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<!--Form related buttons -->


</c:if>
</form>

<!-- WF Admin Footer starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageFooter.jsp"/>
<!-- WF Admin Footer end -->
