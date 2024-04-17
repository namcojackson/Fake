<!-- WF Admin Header starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageHeader.jsp"/>
<!-- WF Admin Header end -->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

  function deleteInstance()
  {
  	if(checkkSelection()){
	    if (confirm("Are you sure to delete the Jobs?"+ getConfirmationMsg())) {
	      doPost($('resultForm'), '<c:url value="/wfadmin/instance/JonInstanceManagePage/deleteJob.sweb"/>');
	    }
	}
	return false;
  }
  
  function executeInstance()
  {
  	if(checkkSelection()){
	    if (confirm("Are you sure to execute the Jobs?" + getConfirmationMsg() +"\nNote: Rerun will archive the existing job and create a new job in executable state. Job dispatcher will pick the jobs and execute.")) {
	      doPost($('resultForm'), '<c:url value="/wfadmin/instance/JonInstanceManagePage/resendMessage.sweb"/>');
	    }
	}
    return false;
  }
  
  function editInstance()
  {
	if(checkkSelection()){  
	    doPost($('resultForm'), '<c:url value="/wfadmin/instance/JonInstanceManagePage/editJobs.sweb"/>');
	}    
    return false;
  }
  
  function searchJobs()
  {
    doPost($('resultForm'), '<c:url value="/wfadmin/instance/JonInstanceManagePage/searchJobs.sweb"/>');
    return false;
  }
  

  /**
   *  Disble on search fields
   */
  function disableJobSearchFields(){
    //class="pHsu" readonly="true"
    
    if(document.getElementById("jobHistorySearch").checked){

     $('queryParam.archiveDateAfterStr').className = '';
     $('queryParam.archiveDateAfterStr').disabled = false;
     $('queryParam.archiveDateBeforeStr').className = '';
     $('queryParam.archiveDateBeforeStr').disabled = false;
     
    }else{
     $('queryParam.archiveDateAfterStr').className = 'pHsu';
     $('queryParam.archiveDateAfterStr').disabled = true;
     $('queryParam.archiveDateBeforeStr').className = 'pHsu';
     $('queryParam.archiveDateBeforeStr').disabled = true;
     
    }
    
  }
  

	function checkkSelection(){
    	//atleast one checkbox should be selected

	    var flag = false;
	    var length = 1;
	    for(var i =0; i < length ; i++){
	        var element = "selectedItemList["+i+"]";
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
	        alert("Please select a Job.");
	    }
	    return flag;    	
    }
    
    function getConfirmationMsg(){
    	var msg = "";
	    var length = 1;
	    for(var i =0; i < length ; i++){
	        var element = "selectedItemList["+i+"]";
	        var checkBoxElement = document.getElementById(element);
	        if(checkBoxElement == null){
	         	break;
	        }else if(checkBoxElement.checked){
	        	msg = msg + '\n' + checkBoxElement.value;
			}else{
				//not checked. loop for more checkboxes.
			}
			length++;
	    }
	    
	    return msg;    
    
    }    

</script>
<body onload="disableJobSearchFields();">
<form id="resultForm" method="GET"  action='<c:url value="/wfadmin/instance/JonInstanceManagePage/searchJobs.sweb"/>' >

<input type="hidden" name="jobListSize"/>

<legend><font size="3" style="font-weight:bold">Job Search</font></legend>

<table>
	<tr>
		<td>
			<table  style="table-layout:absolute" border=0" width="800px">
				<tr >
					<td class="stab" width="140px">Process Code</td>
					<td class="stab" width="180px">	
						<select name="queryParam.processCode" >
						         <option value=""></option>
					                <c:forEach var="code" items="${SwebPage.processCodes}">
					                    <option value='${code}' ${(SwebPage.queryParam.processCode == code)?'selected':''}>${code}</option>
					                </c:forEach>			   
				        </select>
					</td>
					<td class="stab">Document Id</td>
					<td class="stab"><input type="text"  name="queryParam.documentId"  value="${SwebPage.queryParam.documentId}" ></td>
				</tr>
				<tr>
					<td class="stab">Job Type</td>
					<td class="stab">
					         <select name="queryParam.jobClass" >
					               <option value="">All</option>
								   <c:forEach var="jobType" items="${SwebPage.jobTypeStaticList}">
									<option value='${jobType.key}' ${(SwebPage.queryParam.jobClass == jobType.key)?'selected':''}>${jobType.value}</option>
								   </c:forEach>
				             </select>
			        </td>
					<td class="stab">Locked Jobs</td>
					<td class="stab"><input type="checkbox" name="queryParam.locked"  value="true"  ${(SwebPage.queryParam.locked eq "true")?'checked':''}></td>
				</tr>
				<tr>
					<td class="stab">Due Date (MMddyyyy)</td>
					<td class="stab">
						<input type="text" maxlength="8" size="8" name="queryParam.dueDateAfterStr" value="${SwebPage.queryParam.dueDateAfterStr}">
						to <input type="text"  maxlength="8" size="8" name="queryParam.dueDateBeforeStr" value="${SwebPage.queryParam.dueDateBeforeStr}">
					</td>
					<td class="stab">Failed Jobs</td>
					<td class="stab"><input type="checkbox" name="queryParam.failed"  value="true"  ${(SwebPage.queryParam.failed eq "true")?'checked':''}></td>
				</tr>
				<tr>
					<td class="stab">Create Date (MMddyyyy)</td>
					<td class="stab">
						<input type="text" maxlength="8" size="8" name="queryParam.createDateAfterStr" value="${SwebPage.queryParam.createDateAfterStr}">
						to <input type="text"  maxlength="8" size="8" name="queryParam.createDateBeforeStr" value="${SwebPage.queryParam.createDateBeforeStr}">
					</td>	
							
			
					<td class="stab">Search Archived Jobs </td>
					<td class="stab"><input type="checkbox" name="jobHistorySearch" id="jobHistorySearch"  value="true"  ${(SwebPage.jobHistorySearch)?'checked':''}  onclick="disableJobSearchFields()"></td>
				</tr>
			    <tr>
			    	<td class="stab"></td>
			    	<td class="stab"></td>
					<td class="stab">Execution Date (MMddyyyy)</td>
					<td class="stab"><input type="text" maxlength="8" size="8" name="queryParam.archiveDateAfterStr" value="${SwebPage.queryParam.archiveDateAfterStr}">
						to <input type="text"  maxlength="8" size="8" name="queryParam.archiveDateBeforeStr" value="${SwebPage.queryParam.archiveDateBeforeStr}">
					</td>		
				</tr>
			</table>
		</td>
		  <!--
		<td align="right">
				<table border="1" align="left" style="vertical-align: bottom">
					<tr>
						<td>
							<b>Job Types:</b><br/>
							N: Decision/System Task<br/>
							V: Transition Notification <br/>
							W: Reassign Notification<br/>
							X: Process Deadline Notification<br/>
							Y: Task Deadline Notification<br/>
							Z: Work Item Notification
						</td>
					</tr>
				</table>
	  </td>
	    -->
  	</tr>
  </table>
  
   <!--Search related buttons -->	
  
	    <table>
		  <tr>
		      <td> 
			   <input type="submit" value="Search Jobs"  class="pBtn7" value="Search" onclick="searchJobs();return false;" />
			   <input type="reset" value="Reset"  class="pBtn7" value="Reset" />
		       </td>
		   </tr>
	    </table>
  
<c:choose>
	<c:when test="${SwebPage.jobsCount == 0}">
	         <hr/>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>
			<tr>
				<td class="stab" align="left">
					<font class="fontPRM">No jobs found.</font>
				</td>
			</tr>
		</tbody></table>
	</c:when>
	<c:when test="${SwebPage.jobsCount > 0}">
	             <hr/>
	    <table>	
	    	<tr>
				 <c:if test="${!SwebPage.jobHistorySearch}">
			      <td> 
			      	<input type="button" class="pBtn7" value="Delete" onclick="deleteInstance();return false;"/>
					<input type="button" class="pBtn7" value="Rerun" onclick="executeInstance();return false;"/>
					<input type="button" class="pBtn12" value="Change Due Date" onclick="editInstance();return false;"/>
			       </td>
				 </c:if>
				 <td>
				 	Results : ${SwebPage.jobsCount}
				 </td>
		 	</tr>
		</table>	             
    <div > 
      <table style="table-layout:auto;" border="1" cellpadding="0" cellspacing="0" width="990">       
		<thead>
		  <tr>
		  <c:if test="${!SwebPage.jobHistorySearch}">
		    <td class="pClothBs" width="50" align="center">Select</td>
		   </c:if>
		    <td class="pClothBs" width="60" align="center">JobId</td> 
		    <td class="pClothBs" width="30" nowrap="nowrap" align="center">Type</td>
		    <td class="pClothBs" width="120" align="center">Document Id</td>
		    <td class="pClothBs" width="80" align="center">Status</td>
		    <c:if test="${SwebPage.jobHistorySearch}">
                     <td class="pClothBs" width="140" align="center">Execution Date</td>
			</c:if>                               
		    <td class="pClothBs" width="140" align="center">Due Date</td>
		    <td class="pClothBs" width="50" align="center">Suspend</td>
		    <td class="pClothBs" width="50" align="center">Retries</td>
		    <td class="pClothBs" width="150" align="center">Lock Owner</td>
		    <td class="pClothBs" width="140" align="center">Lock Time</td>
		    <td class="pClothBs" width="100" align="center">Exception</td>
		  <c:if test="${SwebPage.jobHistorySearch}">
		    <td class="pClothBs" width="100" align="center">Admin Action</td>
		   </c:if>
		    <td class="pClothBs" width="60" align="center">ProcInstId</td>
		    <td class="pClothBs" width="140"  align="center">Create Date</td> 
					    
		  </tr>
		</thead>  
         <tbody>
			<c:forEach var="t" items="${SwebPage.jobs}" varStatus="rowStatus">
			  <c:choose>
			    <c:when test="${(rowStatus.index%2) == 1}">
			      <tr class="worklistodd">
			    </c:when>
			    <c:otherwise>
			      <tr class="worklisteven">
			    </c:otherwise>
			  </c:choose>
			  <c:if test="${!SwebPage.jobHistorySearch}">
			     <td class="stab" align="center"><input type="checkbox" name="selectedItemList[${rowStatus.index}]" value="${t.id}"/></td>
			  </c:if>
			  <td class="stab">${t.id}</td>
			  <td class="stab">${t.jobType}</td>
			  <td class="stab">${t.documentId}</td>
			  <td class="stab">
				  <c:choose>
				    <c:when test="${t.exception != null }">
				      Failed
				    </c:when>
				    <c:when test="${SwebPage.jobHistorySearch}">
				    	<c:choose>
						    <c:when test="${t.lockTime != null && t.adminAction == null  && t.exception == null}">
						      Successful
						    </c:when>
						    <c:when test="${t.lockTime != null }">
				      			Locked
				    		</c:when>	
						    <c:otherwise>
						    	Aborted
						    </c:otherwise>
					 	</c:choose>
				    </c:when>
				    <c:when test="${t.lockTime != null }">
					      Locked
				    </c:when>
				    <c:when test="${t.suspended == 1}">
				    	Suspended
				    </c:when>			    
				    <c:otherwise>
				    	Pending
				    </c:otherwise>
				  </c:choose>			  	
			  
			  </td>			  
			  
			  <c:if test="${SwebPage.jobHistorySearch}">
			       <td><fmt:formatDate value="${t.archiveDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
			  </c:if>			  
			  <td class="stab"><fmt:formatDate value="${t.dueDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>

			  <td class="stab" align="center">${t.suspended}&nbsp;</td>
			  <td class="stab" align="center">${t.retries}</td>
			  
			  <td class="stab">${t.lockOwner}&nbsp;</td>
			  <td class="stab"><fmt:formatDate value="${t.lockTime}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/>&nbsp;</td>
			  <td class="stab">
			  	  <c:choose>
				  	<c:when test="${t.exception != null}">
				     	<textarea id="message_${rowStatus.index}" rows="2" cols="20" readonly="true" >${t.exception}</textarea>
				     </c:when>
				     <c:otherwise>&nbsp;</c:otherwise>
				  </c:choose>
			  </td>
			  <c:if test="${SwebPage.jobHistorySearch}">
			  		<td class="stab"><c:if test="${t.adminAction != null}">${t.adminAction}</c:if>&nbsp;</td>
			  </c:if>
			  
				    <td class="stab">${t.procInstId}</td>
			  	 	<td class="stab"><fmt:formatDate value="${t.createDate}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>		  
			  </tr>
			</c:forEach>
		 </tbody>
		</table>
	</div>  
		 <c:if test="${!SwebPage.jobHistorySearch}">
			 <table>
			  <tr>
			      <td> 
			      	<input type="button" class="pBtn7" value="Delete" onclick="deleteInstance();return false;"/>
					<input type="button" class="pBtn7" value="Rerun" onclick="executeInstance();return false;"/>
					<input type="button" class="pBtn12" value="Change Due Date" onclick="editInstance();return false;"/>
			       </td>
			   </tr>
			 </table>	
		 </c:if>
			 
			 <hr/>

			</c:when>
		<c:otherwise>
			<!--<hr/>-->
			<!--Not a search result. Don't display anything-->
			<br/>
		</c:otherwise>
	</c:choose>

</form>
</body>

<!-- WF Admin Footer starts -->
<jsp:include page="../../wfadmin/_layout/WfAdminPageFooter.jsp"/>
<!-- WF Admin Footer end -->
