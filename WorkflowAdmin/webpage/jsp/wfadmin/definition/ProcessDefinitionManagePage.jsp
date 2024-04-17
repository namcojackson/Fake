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
  
  function activateDef()
  {
    if(checkkSelection()){
    	doPost($('resultForm'), '<c:url value="/wfadmin/definition/ProcessDefinitionManagePage/activateDef.sweb"/>');
    }
    return false;
  }
  
  function deActivateDef()
  {
  	if(checkkSelection()){
    	 doPost($('resultForm'), '<c:url value="/wfadmin/definition/ProcessDefinitionManagePage/deActivateDef.sweb"/>');
   	}
    return false;
  }  
  
	function checkkSelection(){
    	//atleast one checkbox should be selected

	    var flag = false;
	    var length = 1;
	    for(var i =0; i < length ; i++){
	        var element = "selectedProcDefList["+i+"]"
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
	        alert("Please select a Process Definition.");
	    }
	    return flag;    	
    }
      
</script> 


<span style="color:#ff0000"><c:out value="${SwebPage.currentErrorMessage}"/></span>
<% session.removeAttribute(WfSwebActionErrors.GLOBAL_ERROR);%>


<br/>


<c:choose>
	<c:when test="${SwebPage.processCodesSize == 0}">
		<hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No Process Definitions found.</font>
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
			            <input type="button" class="pBtn7" value="Show" onclick="show();return false;"/>
			        </td>
			    </tr>
			</table>
	</c:otherwise>
</c:choose>
<hr/>

<c:if test="${SwebPage.procExtAttrsSize > 0}">

<!--Form related buttons -->
<table>
    <tr>
        <td colspan="6">
            <input type="button" class="pBtn7"  value="Deactivate" onclick="deActivateDef();return false;"/>
            <input type="button" class="pBtn7"  value="Activate" onclick="activateDef();return false;"/>
        </td>
    </tr>
</table>
	<table  style="table-layout:auto;" border="0" cellpadding="0" cellspacing="0" width="800" >
	    <thead>
	        <tr>
	            <th class="pClothBs">Select</th>
	            <th class="pClothBs">Process Def Id</th>
	            <th class="pClothBs">Process Def Name</th>
	            <th class="pClothBs">Create Date</th>
	            <th class="pClothBs">Version</th>
	            <th class="pClothBs">Is Active</th>
	            <th class="pClothBs">In Service</th>
	            <th class="pClothBs">Open Instances</th>
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
	            <td align="center">
	                <input type="checkbox" name="selectedProcDefList[${rowStatus.index}]" value="${procExtAttr.processDefId}"/>
	            </td>
	            
	            <td class="stab" align="center">
			    <a href='<c:url value="/wfadmin/definition/ProcessDefinitionDetailsPage/showPage.sweb?procDefId=${procExtAttr.processDefId}"/>'>
			     ${procExtAttr.processDefId}
			    </a>
	            </td>
	            
	            <td class="stab" align="center">${procExtAttr.processName}</td>
	            <td class="stab" align="center"><fmt:formatDate value="${procExtAttr.createDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
	            <td class="stab" align="center">${procExtAttr.processVersion}</td>
	            <td class="stab" align="center"><b>${procExtAttr.active}</b></td>
	            <td class="stab" align="center">${procExtAttr.inService}</td>
	            <td class="stab" align="center">${procExtAttr.openInstanceCount}</td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
<!--Form related buttons -->
<table>
    <tr>
        <td colspan="6">
            <input type="button" class="pBtn7"  value="Deactivate" onclick="deActivateDef();return false;"/>
            <input type="button" class="pBtn7"  value="Activate" onclick="activateDef();return false;"/>
        </td>
    </tr>
</table>



<%--c:if test="${not empty SwebPage.nodeExtAttrs}">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <thead><tr>
                    <th class="pClothBs">nodeDefId</th>
                    <th class="pClothBs">nodeName</th>
                    <th class="pClothBs">taskCode</th>
                    <th class="pClothBs">compId</th>
                </tr></thead>
                <tbody>
                <c:forEach var="node" items="${SwebPage.nodeExtAttrs}" varStatus="rowStatus">
                    <c:choose>
                        <c:when test="${(rowStatus.index%2) == 1}">
                            <tr class="worklistodd">
                        </c:when>
                        <c:otherwise>
                            <tr class="worklisteven">
                        </c:otherwise>
                    </c:choose>
                        <td class="stab" align="left">${node.nodeDefId}</td>
                        <td class="stab" align="left">${node.nodeName}</td>
                        <td class="stab" align="left">${node.taskCode}</td>
                        <td class="stab" align="left">${node.compId}</td>
                    </tr>
                </c:forEach>
                </tbody>
        </table>
        <hr/>
    </c:if--%>

<%--input type="hidden" name="procInstExtAttr.processInstanceId" value="${SwebPage.procInstExtAttr.processInstanceId}"/>
    <c:choose>
        <c:when test="${not empty SwebPage.procInstID}">
            <div style="width:100%;height:100%; overflow:visible;background:#eeeeff" >
                <jsp:include page="../wfcomp/S21WfProcessDiagramHolder.jsp">
                    <jsp:param name="taskInstId" value='${(param.taskInstID) == null ? "null" : param.taskInstID}' />
                    <jsp:param name="procInstId" value='${(param.procInstID) == null ? "null" : param.procInstID}' />
                    <jsp:param name="displayMode" value='${(param.displayMode) == null ? "collapsed" : param.displayMode}' />
                </jsp:include>
            </div>
        </c:when>
    </c:choose--%>
</c:if>
</form>

<!-- WF Admin Footer starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageFooter.jsp"/>
<!-- WF Admin Footer end -->
