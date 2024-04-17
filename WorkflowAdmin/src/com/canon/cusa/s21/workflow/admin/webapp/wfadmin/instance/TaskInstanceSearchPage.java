package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.instance;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfJobsQueryParameter;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstance;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItem;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItemQueryParameter;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeExtendedAttribute;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;

public class TaskInstanceSearchPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(TaskInstanceSearchPage.class);

	private boolean needSearch;
	private final String TASK_INSTANCE_SEARCH_PARAM = "TASK_INSTANCE_SEARCH_PARAM";
	private S21WfWorkItemQueryParameter queryParam = new S21WfWorkItemQueryParameter();
	private List<S21WfProcessInstance> processList;
	private List<S21WfWorkItem> workItemList;
	private int workItemListCount = -1;
	private String userIdSearchCriteria;
	private String workItemNoUserCanWork;
	private List<String> processCodes;
	private List<String> taskCodes;
	 private boolean backAction;

	private String[] maxResultCountSelections = new String[]{"20", "50", "100", "300", "500"};

	
	/**
	 * Radio selection
	 */
	private String selectedTaskId;

	public boolean isNeedSearch() {
		return needSearch;
	}

	public void setNeedSearch(boolean needSearch) {
		this.needSearch = needSearch;
	}

	//---------------------------------------------
	// Inner class
	//---------------------------------------------
/*	public static class WfTaskSearchProcessDataFactory implements S21LazyListFactory {
		public Object create() {
			return new S21WfWorkItem();
		}
	}

	//---------------------------------------------
	// Constructor
	//---------------------------------------------
	public TaskInstanceSearchPage() {
		workItemList = new S21LazyList(new ArrayList(), new WfTaskSearchProcessDataFactory());
	}
*/
	//---------------------------
	// preparation
	//---------------------------
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
		
		if (processCodes == null) {
			processCodes = wfProcessDefinitionService.getDistinctProcessCodes();
			//wfProcessDefinitionService.getNodeExtendedAttributesByProcessCode(processCode)
			loadTaskCodes();

		}
		
		if (needSearch) {
        	
        	if(backAction){
        		queryParam = (S21WfWorkItemQueryParameter) request.getSession().getAttribute(TASK_INSTANCE_SEARCH_PARAM);
        	}			
			queryParam.setAdminQuery(true);
			if (!checkValidProcessCode()) {
				return;
			}
			if (S21StringUtil.isNotEmpty(userIdSearchCriteria)) {
				// spl search: Perform search on given user id.
					workItemList = wfWorkItemQueryService.getWorkItemUserCanWork(queryParam, userIdSearchCriteria);
					workItemListCount = workItemList.size();
			} else if (S21StringUtil.isEquals(workItemNoUserCanWork, "true")) {
				// spl search: Perform search no user can work on based on a
				// processcode
					workItemList = wfWorkItemQueryService.getWorkItemNoUserCanWork(queryParam);
					//reset query param to clear any values set for query
					queryParam.setTaskCodeStrList(null);
					workItemListCount = workItemList.size();
			} else {
				// this will perform regular search based on all other criteria
				workItemList = wfWorkItemQueryService.getWorkItemListForTaskStatus(queryParam, null);
				workItemListCount = workItemList.size();
			}
			
			request.getSession().setAttribute(TASK_INSTANCE_SEARCH_PARAM, queryParam);
		}
	}

	private boolean checkValidProcessCode() {
		boolean isValid = true;
		if (S21StringUtil.isEmpty(queryParam.getProcessCodeStrList())) {
			isValid = false;
			setGlobalActionError("error.taskInstanceSearchPage.processcode");
			workItemListCount = -1;
		}
		return isValid;
	}

	//---------------------------------------------
	// Listeners	
	//---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		workItemListCount = -1;
		return activateDestinationPageWithoutLayout(getClass());
	}

	public WfSwebDestinationPage search() throws Exception {
		logger.debug("search...");
		needSearch = true;
		return activateDestinationPageWithoutLayout(getClass());
	}

	public WfSwebDestinationPage editTask() throws Exception {
		logger.debug("edit task instance...");
		String taskinstId = getSelectedTaskId();
		addPrepareArgForDestination("taskInstID", taskinstId);
		logger.debug("edit task instance id" + taskinstId);
		/*
		 * for (int i = 0; i < workItemList.size(); i++) { S21WfWorkItem
		 * workItem = (S21WfWorkItem) workItemList.get(i); if
		 * (workItem.isSelected()) { Long taskInstanceId =
		 * workItem.getTaskInstId();
		 * 
		 * break; } }
		 */
		return redirectDestinationPage(TaskInstanceEditPage.class);
	}
	
	
	public WfSwebDestinationPage loadTaskCode() throws Exception {
		// only one since selected from a drop down
		loadTaskCodes();
		return activateDestinationPageWithoutLayout(TaskInstanceSearchPage.class);
	}

	private void loadTaskCodes() {
		String processCode = queryParam.getProcessCodeStrList();
		//no processing without process codes
		if (S21StringUtil.isNotEmpty(processCode)) {
			List<WfNodeExtendedAttribute> nodeExtAttrList = wfProcessDefinitionService.getNodeExtendedAttributesByProcessCode(processCode);

			if (taskCodes == null) {
				taskCodes = new ArrayList<String>();
			}
			taskCodes.clear();
			for (WfNodeExtendedAttribute nodeExtAttr : nodeExtAttrList) {
				if(nodeExtAttr.getTaskCode() != null){
				taskCodes.add(nodeExtAttr.getTaskCode());
				}
			}
		}
	}

	// ---------------------------------------------
	// Properties
	//---------------------------------------------
	public S21WfWorkItemQueryParameter getSearchParam() {
		return queryParam;
	}

	public void setSearchParam(S21WfWorkItemQueryParameter queryParam) {
		this.queryParam = queryParam;
	}


	public String[] getMaxResultCountSelections() {
		return maxResultCountSelections;
	}	

	public int getWorkItemListCount() {
		return workItemListCount;
	}

	public void setWorkItemListCount(int workItemListCount) {
		this.workItemListCount = workItemListCount;
	}

	public List<S21WfWorkItem> getWorkItemList() {
		return workItemList;
	}

	public void setWorkItemList(List<S21WfWorkItem> workItemList) {
		this.workItemList = workItemList;
	}

	public String getSelectedTaskId() {
		return selectedTaskId;
	}

	public void setSelectedTaskId(String selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

	@Override
	public String getPageId() {
		return "ZZWL3030";
	}

	@Override
	public String getPageName() {
		return "Task Instance Management ";
	}

	public String getUserIdSearchCriteria() {
		return userIdSearchCriteria;
	}

	public void setUserIdSearchCriteria(String userIdSearchCriteria) {
		this.userIdSearchCriteria = userIdSearchCriteria;
	}

	public String getWorkItemNoUserCanWork() {
		return workItemNoUserCanWork;
	}

	public void setWorkItemNoUserCanWork(String workItemNoUserCanWork) {
		this.workItemNoUserCanWork = workItemNoUserCanWork;
	}

	public List getProcessCodes() {
		return processCodes;
	}

	public void setProcessCodes(List processCodes) {
		this.processCodes = processCodes;
	}

	public List<String> getTaskCodes() {
		return taskCodes;
	}

	public void setTaskCodes(List<String> taskCodes) {
		this.taskCodes = taskCodes;
	}

	public boolean isBackAction() {
		return backAction;
	}

	public void setBackAction(boolean backAction) {
		this.backAction = backAction;
	}

	
}

