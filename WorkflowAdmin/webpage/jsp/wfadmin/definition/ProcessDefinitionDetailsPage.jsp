<!-- WF Admin Header starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageHeader.jsp"/>
<!-- WF Admin Header end -->


<%@ page import="com.canon.cusa.s21.framework.workflow.common.sweb.core.WfSwebActionErrors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="post" name="resultForm">
<script type="text/javascript">

  function show()
  {
    doPost($('resultForm'), '<c:url value="/wfadmin/definition/ProcessDefinitionManagePage/executeAction.sweb"/>');
    return false;
  }
  

      
</script> 


<span style="color:#ff0000"><c:out value="${SwebPage.currentErrorMessage}"/></span>
<% session.removeAttribute(WfSwebActionErrors.GLOBAL_ERROR);%>


<br/>


		<table border="0.5" cellpadding="0.5" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th class="pClothBs">Process Def Id</th>
				    <th class="pClothBs">Name</th>
					<th class="pClothBs">Process Code</th>
					<th class="pClothBs">Definition Details</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="pClothYs" align="left">${SwebPage.proceDefDetailsVO.procDef.id}</td>
					<td class="pClothYs" align="center">${SwebPage.proceDefDetailsVO.procDef.name}</td>
					<td class="pClothYs" align="center">${SwebPage.proceDefDetailsVO.procExtAttr.processCode}</td>
					<td class="pClothYs" align="center">${SwebPage.proceDefDetailsVO.procDef.description}</td>
				</tr>
               <c:if test="${SwebPage.proceDefDetailsVO.eventActionCount > 0}">
					<tr>
						<td>&nbsp;&nbsp;</td>
						<td colspan="3">
						<table border="1" cellpadding="1" cellspacing="1" width="100%">
							<tr>
								<th class="pClothBs">Event Type</th>
								<th class="pClothBs">Handler Class</th>
								<th class="pClothBs">Configuration</th>
							</tr>
							<c:forEach var="eventAction" items="${SwebPage.proceDefDetailsVO.eventActions}"  varStatus="subRowStatus">
								<c:choose>
									<c:when test="${(subRowStatus.index%2) == 1}">
										<tr class="worklistodd">
									</c:when>
									<c:otherwise>
										<tr class="worklisteven">
									</c:otherwise>
								</c:choose>
								<td class="fontPBM" align="center">${eventAction.eventType}</td>
								<td class="fontPBM" align="left">${eventAction.action.actionDelegation.className}</td>
								<td class="fontPBM" align="left">${eventAction.actionConfiguration}</td>
								</tr>
							</c:forEach>
						</table>
						</td>
					</tr>
				</c:if>	
				<tr>
				    <td class="stab" nowrap colspan="4" align="left">
					   <input type="button" class="pBtn7" value="Back" onclick="javascript:window.history.back();return false;"/>
				    </td>
		        </tr>			
			</tbody>
		</table>	


         <br/> <hr>
        <legend><font size="2" style="font-weight:bold">Node Definition Details</font></legend>
		<table border="0.5" cellpadding="0.5" cellspacing="0" width="100%">
			<c:forEach var="nodeDefDetail" items="${SwebPage.nodeDefDetailsVOs}" varStatus="rowStatus">
			   <thead>
				<tr>
					<th class="pClothBs">Node Id</th>
					<th class="pClothBs">Name</th>
					<th class="pClothBs">Task Code</th>
					<th class="pClothBs">Definition Details</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="pClothYs" align="left">${nodeDefDetail.node.id}</td>
					<td class="pClothYs" align="center">${nodeDefDetail.node.name}</td>
					<td class="pClothYs" align="center">${nodeDefDetail.nodeExtAttr.taskCode}</td>
					<td class="pClothYs" align="center">${nodeDefDetail.node.description}</td>
				</tr>
				<c:if test="${nodeDefDetail.eventActionCount > 0}">
					<tr>
						<td>&nbsp;&nbsp;</td>
						<td colspan="3">
						<table border="1" cellpadding="1" cellspacing="1" width="100%">
							<tr>
								<th class="pClothBs">Event Type</th>
								<th class="pClothBs">Handler Class</th>
								<th class="pClothBs">Configuration</th>
							</tr>
							<c:forEach var="eventAction" items="${nodeDefDetail.eventActions}"
								varStatus="subRowStatus">
								<c:choose>
									<c:when test="${(subRowStatus.index%2) == 1}">
										<tr class="worklistodd">
									</c:when>
									<c:otherwise>
										<tr class="worklisteven">
									</c:otherwise>
								</c:choose>
								<td class="fontPBM" align="center">${eventAction.eventType}</td>
								<td class="fontPBM" align="left">${eventAction.action.actionDelegation.className}</td>
								<td class="fontPBM" align="left">${eventAction.actionConfiguration}</td>
								</tr>
							</c:forEach>
						</table>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="4">&nbsp;&nbsp;</td>
				</tr>
			</c:forEach>
			<tr>
				<td class="stab" nowrap colspan="4" align="left">
					<input type="button" class="pBtn7" value="Back" onclick="javascript:window.history.back();return false;"/>
				</td>
		   </tr>
		</tbody>
	</table>



</form>

<!-- WF Admin Footer starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageFooter.jsp"/>
<!-- WF Admin Footer end -->

