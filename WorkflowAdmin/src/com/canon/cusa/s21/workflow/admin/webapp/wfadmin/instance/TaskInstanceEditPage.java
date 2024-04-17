package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.instance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.context.WfHumanTaskExecutionContextImpl;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfAlreadyProcessedException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfConcurrentUpdateException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfDuplicateInstanceCreationException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfSecurityViolationException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfUnexpectedDataSetupException;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstanceExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfVariables;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItem;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItemQueryParameter;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeInstanceExtendedAttribute;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;

public class TaskInstanceEditPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(TaskInstanceEditPage.class);
	private static final String PARAMETER_TASK_INST_ID = "taskInstID";

	private S21WfWorkItem workItem;
	
	private String taskInstUserId;
	
	private String taskDataAccessAttribute1;
	
	private String taskDataAccessAttribute2;
	
	private String taskDataAccessAttribute3;
	
	private String taskDataAccessAttribute4;	
	
	private Integer hierarchyLevel;
	
	private String taskInstID;
	
	private String taskCode;
	
	private String delegatorId;
	
	private String taskDueDate;
	
	private boolean taskSearchSourcePage = true; 
	
	private boolean userInRole;
	
	private long procInstID;
	
	private List userListForRole = new ArrayList();

	private String comments;
	
	
	@Override
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
		 if (S21StringUtil.isNotEmpty(taskInstID)) {
			S21WfWorkItemQueryParameter option = new S21WfWorkItemQueryParameter();
//			option.setTaskInstanceStatus(S21WfWorkItemQueryParameter.INST_STATUS_OPEN);
			option.setAdminQuery(true);
			option.setTaskInstanceId(taskInstID);
			String userId = null;

			List<S21WfWorkItem> editableWorkItemList = wfWorkItemQueryService.getWorkItemListForTaskStatus(option, userId);
			if (!editableWorkItemList.isEmpty()) {
				workItem = editableWorkItemList.get(0);
				//setTaskDAAttributeForTaskInstance(taskInstance)
				//also get task da attributes from node ext attributes
				long taskInstId  = new Long(taskInstID);
				procInstID = workItem.getProcessInstId();
				setTaskDAAttributeForTaskInstance(taskInstId);
				setUserIdListList(taskInstId);
				//
				
			}
		}
        

	}

	private void setUserIdListList(long taskInstId) {
		WfNodeExtendedAttribute wfNodeExtAttr  = wfTaskService.getNodeExtendedAttributeForTaskInstId(taskInstId);
		String roleId= wfNodeExtAttr.getTaskRole();
		if(S21StringUtil.isNotEmpty(roleId)  && !wfNodeExtAttr.isRoleAssignmentMode()){
			this.userListForRole = wfUserProfileService.getUserIdListForRole(wfNodeExtAttr.getTaskRole());
			this.userInRole = wfNodeExtAttr.isUserInRoleAssignmentMode();
		}
	}

	private void setTaskDAAttributeForTaskInstance(long taskInstID) throws Exception{
		WfNodeInstanceExtendedAttribute nodeInstExtAttr = wfTaskService.getNodeInstanceExtendedAttributeForTaskInstanceId(taskInstID);
		setTaskDataAccessAttribute1(nodeInstExtAttr.getAttr1());
		setTaskDataAccessAttribute2(nodeInstExtAttr.getAttr2());
		setTaskDataAccessAttribute3(nodeInstExtAttr.getAttr3());
		setTaskDataAccessAttribute4(nodeInstExtAttr.getAttr4());
		setHierarchyLevel(nodeInstExtAttr.getHierarchyLevel());
		if(nodeInstExtAttr.getDueDate() != null){
			setTaskDueDate(formatDate(nodeInstExtAttr.getDueDate()));
		}
		
	}

	// ---------------------------------------------
	// Listeners
	//---------------------------------------------
    public WfSwebDestinationPage executeAction() throws Exception {
        if ("Update".equals(getAction())) {
            return update();
        } else if("Cancel".equals(getAction())){
        	return searchPage();
        }else {
            return showPage();
        }
    }
	
	public WfSwebDestinationPage searchPage() throws Exception {
		logger.debug("showPage...");
		return activateDestinationPageWithoutLayout(TaskInstanceSearchPage.class);
	}

	
    public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		return activateDestinationPageWithoutLayout(getClass());
	}
	
    private boolean validateGivenDate(String dateStr){
		try{
			parseDate(dateStr);
			return true;
		}catch(Exception e){
			setGlobalActionError("invalid.date", dateStr);
			return false;
		}
	}	
    
	private Date parseDate(String dateStr) throws ParseException{
		DateFormat dateFormat = S21WfVariables.PROCESS_DEADLINE_VARIABLE_FORMAT;
		return dateFormat.parse(dateStr);
	}
	
	private String formatDate(Date date) throws ParseException{
		DateFormat dateFormat = S21WfVariables.PROCESS_DEADLINE_VARIABLE_FORMAT;
		return dateFormat.format(date);
	}	
	
	public  WfSwebDestinationPage update()  throws Exception{

		boolean hasErrors = false;
		Date newTaskDueDate = null;
		if(S21StringUtil.isNotEmpty(taskDueDate)){
			if(validateGivenDate(taskDueDate)){
				newTaskDueDate = parseDate(taskDueDate);
			}else{
				hasErrors = true;
			}
		}
		if(!hasErrors){
	        try {
	        	String taskInstIdStr =  getTaskInstID();
	        	long taskInstId =  Long.valueOf(taskInstIdStr);
	        	
	        	WfNodeInstanceExtendedAttribute wfNodeInstExtAttr = wfTaskService.getNodeInstanceExtendedAttributeForTaskInstanceId(taskInstId);
	            String taskUserId = getTaskInstUserId();
	            String delegatorId = getDelegatorId();
	           
	            if(S21StringUtil.isNotEmpty(taskUserId)){
	            	taskUserId = taskUserId.toUpperCase();
	            	if(!taskUserId.equals(wfNodeInstExtAttr.getUser())){
	            		wfNodeInstExtAttr.setUser(taskUserId);
	            	}
	            }
	            if(S21StringUtil.isNotEmpty(delegatorId)){
	            	delegatorId = delegatorId.toUpperCase();
	            	if(!delegatorId.equals(wfNodeInstExtAttr.getDelegator())){
	            		wfNodeInstExtAttr.setDelegator(delegatorId);
	            	}
	            }
	            
	            wfNodeInstExtAttr.setAttr1(getTaskDataAccessAttribute1());
	            wfNodeInstExtAttr.setAttr2(getTaskDataAccessAttribute2());
	            wfNodeInstExtAttr.setAttr3(getTaskDataAccessAttribute3());
	            wfNodeInstExtAttr.setAttr4(getTaskDataAccessAttribute4());
	            if(hierarchyLevel != null){
	            	wfNodeInstExtAttr.setHierarchyLevel(hierarchyLevel);
	            }
	            
	            wfNodeInstExtAttr.setDueDate(newTaskDueDate);		 		                          wfTaskService.updateNodeInstanceExtAttrByAdmin(wfNodeInstExtAttr,getUserId(),comments);
	        }
	        catch (Exception e) {
	          if (e.getCause() instanceof ServletException) {
	            Throwable t = e.getCause();
	
	            if (t != null) {
	              t = t.getCause();
	            }
	
	            if (t instanceof S21WfSecurityViolationException) {
	              setGlobalActionError("error.security", t.getMessage());
	              return activateDestinationPageWithoutLayout(getClass());
	
	            } else if (t instanceof S21WfUnexpectedDataSetupException) {
	              setGlobalActionError("error.data", t.getMessage());
	              return activateDestinationPageWithoutLayout(getClass());
	
	            } else if (t instanceof S21WfAlreadyProcessedException) {
	              setGlobalActionError("error.already", t.getMessage());
	              return activateDestinationPageWithoutLayout(getClass());
	
	            } else if (t instanceof S21WfConcurrentUpdateException) {
	              setGlobalActionError("error.concurrent", t.getMessage());
	              return activateDestinationPageWithoutLayout(getClass());
	
	            } else if (t instanceof S21WfDuplicateInstanceCreationException) {
	              setGlobalActionError("error.duplicate", t.getMessage());
	              return activateDestinationPageWithoutLayout(getClass());
	
	            } else {
	              setGlobalActionError("error.unrecoverable", e.getClass().getSimpleName());
	              e.printStackTrace();
	              return activateDestinationPageWithoutLayout(getClass());
	            }
	
	          } else {
	            setGlobalActionError("error.unrecoverable", e.getClass().getSimpleName());
	            e.printStackTrace();
	            return activateDestinationPageWithoutLayout(getClass());
	          }
	        }
		}
        
        
       addPrepareArgForDestination(PARAMETER_TASK_INST_ID, getTaskInstID());
       addPrepareArgForDestination("taskSearchSourcePage", taskSearchSourcePage);
        return redirectDestinationPage(getClass());
    
		
		
	}
	
	private void setUpTaskDataAccessAttribute(WfHumanTaskExecutionContextImpl wfExecCtx,long taskInstid){
		WfNodeInstanceExtendedAttribute nodeInstExtAttr = wfTaskService.getNodeInstanceExtendedAttributeForTaskInstanceId(taskInstid);
		

		
	}
	
	public S21WfWorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(S21WfWorkItem workItem) {
		this.workItem = workItem;
	}

	public String getTaskInstUserId() {
		return taskInstUserId;
	}

	public void setTaskInstUserId(String taskInstUserId) {
		this.taskInstUserId = taskInstUserId;
	}

	public String getTaskDataAccessAttribute1() {
		return taskDataAccessAttribute1;
	}

	public void setTaskDataAccessAttribute1(String taskDataAccessAttribute1) {
		this.taskDataAccessAttribute1 = taskDataAccessAttribute1;
	}

	public String getTaskDataAccessAttribute2() {
		return taskDataAccessAttribute2;
	}

	public void setTaskDataAccessAttribute2(String taskDataAccessAttribute2) {
		this.taskDataAccessAttribute2 = taskDataAccessAttribute2;
	}

	public String getTaskDataAccessAttribute3() {
		return taskDataAccessAttribute3;
	}

	public void setTaskDataAccessAttribute3(String taskDataAccessAttribute3) {
		this.taskDataAccessAttribute3 = taskDataAccessAttribute3;
	}

	public String getTaskDataAccessAttribute4() {
		return taskDataAccessAttribute4;
	}

	public void setTaskDataAccessAttribute4(String taskDataAccessAttribute4) {
		this.taskDataAccessAttribute4 = taskDataAccessAttribute4;
	}

	public List getUserListForRole() {
		return userListForRole;
	}

	public void setUserListForRole(List userListForRole) {
		this.userListForRole = userListForRole;
	}

	public boolean isUserInRole() {
		return userInRole;
	}

	public void setUserInRole(boolean userInRole) {
		this.userInRole = userInRole;
	}

	@Override
	public String getPageId() {
		return "ZZWL3030";
	}

	@Override
	public String getPageName() {
		return "Task Instance Management ";
	}

	public String getTaskInstID() {
		return taskInstID;
	}

	public void setTaskInstID(String taskInstID) {
		this.taskInstID = taskInstID;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public Integer getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(Integer hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

	public String getDelegatorId() {
		return delegatorId;
	}

	public void setDelegatorId(String delegatorId) {
		this.delegatorId = delegatorId;
	}

	public String getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(String taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public long getProcInstID() {
		return procInstID;
	}

	public void setProcInstID(long procInstID) {
		this.procInstID = procInstID;
	}

	public boolean isTaskSearchSourcePage() {
		return taskSearchSourcePage;
	}

	public void setTaskSearchSourcePage(boolean taskSearchSourcePage) {
		this.taskSearchSourcePage = taskSearchSourcePage;
	}


	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
}

