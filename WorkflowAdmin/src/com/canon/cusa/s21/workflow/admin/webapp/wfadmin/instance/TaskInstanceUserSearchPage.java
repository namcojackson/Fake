/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Rohit Chandramohan
 * Company: Fujitsu Limited
 * Date: Dec 14, 2009
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.canon.cusa.s21.framework.common.S21BeanPropertyUtil;
import com.canon.cusa.s21.framework.common.S21Pair;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21HierarchyMatchType;
import com.canon.cusa.s21.framework.userprofile.S21LogicalOperator;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstanceExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItem;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeInstanceExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeLevelDataAccessRule;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;


public class TaskInstanceUserSearchPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(TaskInstanceSearchPage.class);

	// String taskCode;

	// String processCode;

	// List<TaskInstanceUserVO> taskInstUserVOList;

	// private int taskInstUserVOCount = -1;

	private String taskInstID;
	private String documentId;
	private String taskCode;
	private String taskRole;

	/**
	 * Role specific implementation
	 */
	private String actorId = "";

	private String delegatorId = "";

	private List<String> canDoUserList;

	private List<String> toDoUserListForRole;

	private boolean userInRole;

	private WfNodeInstanceExtendedAttribute nodeInstExtAttr;

	@Override
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {

		long taskInstId = Long.valueOf(taskInstID);

		nodeInstExtAttr = wfTaskService.getNodeInstanceExtendedAttributeForTaskInstanceId(taskInstId);

		WfNodeExtendedAttribute nodeAttr = wfTaskService.getNodeExtendedAttributeForTaskInstId(taskInstId);

		setUserInRole(nodeAttr.isUserInRoleAssignmentMode());

		this.actorId = nodeInstExtAttr.getUser();

		if (S21StringUtil.isNotEmpty(actorId)) {

			this.delegatorId = nodeInstExtAttr.getDelegator();
			// also get Can do userList

			canDoUserList = wfUserProfileService.getUserIdListForRole(nodeInstExtAttr.getRole());
			if (canDoUserList != null && canDoUserList.size() > 0) {
				// dont show actor in cando
				if (S21StringUtil.isNotEmpty(delegatorId) && canDoUserList.contains(delegatorId)) {
					canDoUserList.remove(delegatorId);
				}
				// dont show actor in cando
				if (S21StringUtil.isNotEmpty(actorId) && canDoUserList.contains(actorId)) {
					canDoUserList.remove(actorId);
				}
			}

		} else {
			if (nodeAttr.isUserInRoleAssignmentMode()) {
				logger.warn("No particular user is assined to this task in userInRole mode, so framework doesn't send email.");
				return;
			}
			// email to group
			this.toDoUserListForRole = calculateUserIdListForRoleAssign(nodeInstExtAttr, nodeAttr);
		}

		/*
		 * if (S21StringUtil.isNotEmpty(taskCode)) {
		 * 
		 * 
		 * taskInstUserVOList = new ArrayList<TaskInstanceUserVO>();
		 * S21WfWorkItemQueryParameter queryParam = new
		 * S21WfWorkItemQueryParameter();
		 * queryParam.setTaskCodeStrList(taskCode);
		 * queryParam.setTaskInstanceStatus(S21WfWorkItemQueryParameter.TASK_INST_STATUS_PROCESSING);
		 * //queryParam.setProcessInstanceIsSuspended(S21WfWorkItemQueryParameter.NOT_SUSPENDED);
		 * List<S21WfWorkItem> workItemList =
		 * wfWorkItemQueryService.getWorkItemListForTaskStatus(queryParam,
		 * null);
		 * 
		 * //intialise references WfNodeExtendedAttribute nodeAttr = null;
		 * WfNodeLevelDataAccessRule daRule = null;
		 * 
		 * //taskCode level computation List<String> userList = new ArrayList<String>(); //
		 * check if assignMode = role and DA security is not required boolean
		 * daSecurityFlag = false; S21WfWorkItem wi = (workItemList != null &&
		 * workItemList.size() > 0) ? workItemList.get(0) : null;
		 * 
		 * if (wi != null) { nodeAttr = getNodeExtendedAttr(wi.getProcessCode(),
		 * wi.getTaskCode()); daRule = getDARuleForNodeExtAttr(nodeAttr); if
		 * (daRule.isUserProfileBasedDataAccessSecurity()) { userList =
		 * wfUserProfileService.getUserIdListForRole(wi.getTaskEditRoleId());
		 * daSecurityFlag = true; } }
		 * 
		 * //taskInstance level computation for (S21WfWorkItem workItem :
		 * workItemList) { TaskInstanceUserVO taskInstanceUserVO = new
		 * TaskInstanceUserVO(); taskInstanceUserVO.setWfWorkItem(workItem);
		 * String processCode = workItem.getProcessCode(); String taskCode =
		 * workItem.getTaskCode(); // Now calculate users for the taskInst //
		 * WfNodeExtendedAttribute nodeAttr = //
		 * getNodeExtendedAttr(processCode, taskCode); if
		 * (nodeAttr.isUserInRoleAssignmentMode()) { String actorId =
		 * workItem.getTaskInstUserId(); taskInstanceUserVO.addUser(actorId); }
		 * else { // WfNodeLevelDataAccessRule daRule = //
		 * getDARuleForNodeExtAttr(nodeAttr); if (daSecurityFlag) { // also
		 * check if task DA security is defined userList =
		 * doCalculateUserIdListWithDA(nodeAttr, daRule, workItem); } if
		 * (userList != null && userList.size() > 0) {
		 * taskInstanceUserVO.addUsers(userList); } }
		 * 
		 * taskInstUserVOList.add(taskInstanceUserVO); }
		 * 
		 * taskInstUserVOCount = taskInstUserVOList.size(); }
		 */
	}

	protected List<String> calculateUserIdListForRoleAssign(WfNodeInstanceExtendedAttribute nodeInstAttr, WfNodeExtendedAttribute nodeAttr) throws Exception {
		// filter user based on the DataAccessPermission and ProcessInstance's
		// DataAccessAttribute
		// WfProcessAnnotations procAnno =
		// wfProcessDefinitionService.getProcessAnnotationsForProcessDef(executionContext.getProcessDefinition());
		// WfProcessLevelDataAccessRule daRule =
		// procAnno.newWfProcessLevelDataAccessRule();
		WfNodeLevelDataAccessRule daRule = nodeAttr.getDataAccessRule();

		List<String> userIdList = null;
		if (!daRule.isUserProfileBasedDataAccessSecurity()) {
			userIdList = wfUserProfileService.getUserIdListForRole(nodeInstAttr.getRole());
		} else {
			userIdList = doCalculateUserIdListWithDA(nodeInstAttr, nodeAttr);
		}
		return userIdList;
	}

	private List<String> doCalculateUserIdListWithDA(WfNodeInstanceExtendedAttribute nodeInstAttr, WfNodeExtendedAttribute nodeAttr) throws Exception {
		// params
		String bizAppId = nodeAttr.getCompId();
		String[] functionIds = { nodeInstAttr.getRole() };

		WfNodeLevelDataAccessRule daRule = nodeAttr.getDataAccessRule();
		List<S21DataSecurityAttributeData> adList = new ArrayList<S21DataSecurityAttributeData>();
		S21DataSecurityAttributeData attributeData;

		S21WfProcessInstanceExtendedAttribute processInstanceExtAttr = wfTaskService.getProcessInstanceExtendedAttributeForTaskInst(nodeInstAttr.getTaskInstanceId());

		// ---------------------------------
		// process instance attribute
		// ---------------------------------
		long procInstId = processInstanceExtAttr.getProcessInstanceId();
		S21WfProcessInstanceExtendedAttribute procInstAttr = wfProcessService.getProcessInstanceExtendedAttributeForProcInst(procInstId);
		// Org Unit Hierarchy
		String treeId = daRule.getOrgUnitTreeId();
		if (S21StringUtil.isNotEmpty(treeId)) {
			attributeData = S21DataSecurityAttributeData.newOrgUnitHierarchyAttributeData();
			attributeData.setAttributeName(treeId);
			S21Pair valLayer = procInstAttr.getOrgUnitValueAndLayer();
			attributeData.setValue(valLayer.getValue());
			attributeData.setLayer(Integer.parseInt(valLayer.getKey()));
			adList.add(attributeData);
		}

		// MDSE Hierarchy
		treeId = daRule.getProductGroupTreeId();
		if (S21StringUtil.isNotEmpty(treeId)) {
			attributeData = S21DataSecurityAttributeData.newMdseHierarchyAttributeData();
			attributeData.setAttributeName(treeId);
			S21Pair valLayer = procInstAttr.getProductGroupValueAndLayer();
			attributeData.setValue(valLayer.getValue());
			attributeData.setLayer(Integer.parseInt(valLayer.getKey()));
			adList.add(attributeData);
		}

		// Partner Hierarchy
		treeId = daRule.getPartnerGroupTreeId();
		if (S21StringUtil.isNotEmpty(treeId)) {
			attributeData = S21DataSecurityAttributeData.newPartnerHierarchyAttributeData();
			attributeData.setAttributeName(treeId);
			S21Pair valLayer = procInstAttr.getPartnerGroupValueAndLayer();
			attributeData.setValue(valLayer.getValue());
			attributeData.setLayer(Integer.parseInt(valLayer.getKey()));
			adList.add(attributeData);
		}

		// process instance level code attributes: TOC, MDSE, BILL_TO, SELL_TO,
		// SHIP_TO, BR, WH, ATTR1 ... ATTR8
		Set<String> codeTypeAttrNameSet = daRule.getProcInstCodeTypeDaAttrNames();
		for (String codeAttrName : codeTypeAttrNameSet) {
			attributeData = S21DataSecurityAttributeData.newCodeAttributeData();
			attributeData.setAttributeName(codeAttrName);
			String propertyName = daRule.getProcInstCodeTypeDaAttrPropertyName(codeAttrName);
			if (S21StringUtil.isNotEmpty(propertyName)) {
				String value = (String) S21BeanPropertyUtil.getProperty(procInstAttr, propertyName);
				attributeData.setValue(value);
				attributeData.setLayer(1);
				adList.add(attributeData);
			}
		}

		// ---------------------------------
		// task instance attribute
		// ---------------------------------

		WfNodeInstanceExtendedAttribute taskInstAttr = wfTaskService.getNodeInstanceExtendedAttributeForTaskInstanceId(nodeInstAttr.getTaskInstanceId());
		// task instance level code attributes: ATTR1...4
		codeTypeAttrNameSet = daRule.getTaskInstCodeTypeDaAttrNames();
		for (String codeAttrName : codeTypeAttrNameSet) {
			attributeData = S21DataSecurityAttributeData.newCodeAttributeData();
			attributeData.setAttributeName(codeAttrName);
			String propertyName = daRule.getTaskInstCodeTypeDaAttrPropertyName(codeAttrName);
			if (S21StringUtil.isNotEmpty(propertyName)) {
				String value = (String) S21BeanPropertyUtil.getProperty(taskInstAttr, propertyName);
				attributeData.setValue(value);
				attributeData.setLayer(1);
				adList.add(attributeData);
			}
		}

		// ---------------------------------
		// do query users
		// ---------------------------------
		List<String> userIdList = wfUserProfileService.findUserIdListWithDataSecurity(bizAppId, functionIds, adList, S21HierarchyMatchType.ALL_LAYER, S21LogicalOperator.AND, processInstanceExtAttr.getGlobalCompanyCode());
		return userIdList;
	}

	// ---------------------------------------------
	// Listeners
	// ---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		// taskInstUserVOCount = -1;
		return activateDestinationPageWithoutLayout(getClass());
	}

	private List<String> doCalculateUserIdListWithDA(WfNodeExtendedAttribute nodeAttr, WfNodeLevelDataAccessRule daRule, S21WfWorkItem workItem) throws Exception {

		// params
		String bizAppId = nodeAttr.getCompId();
		logger.debug("RC: Error Checking" + workItem.getProcessCode());
		logger.debug("RC: Error Checking" + workItem.getTaskCode());
		String[] functionIds = { workItem.getTaskEditRoleId() };

		List<String> userIdList = null;
		if (!daRule.isUserProfileBasedDataAccessSecurity()) {
			userIdList = wfUserProfileService.getUserIdListForRole(workItem.getTaskEditRoleId());
			return userIdList;
		}
		List<S21DataSecurityAttributeData> adList = new ArrayList<S21DataSecurityAttributeData>();
		S21DataSecurityAttributeData attributeData = null;

		// ---------------------------------
		// process instance attribute
		// ---------------------------------
		long procInstId = workItem.getProcessInstId();
		S21WfProcessInstanceExtendedAttribute procInstAttr = wfProcessService.getProcessInstanceExtendedAttributeForProcInst(procInstId);
		// Org Unit Hierarchy
		String treeId = daRule.getOrgUnitTreeId();
		if (S21StringUtil.isNotEmpty(treeId)) {
			attributeData = S21DataSecurityAttributeData.newOrgUnitHierarchyAttributeData();
			attributeData.setAttributeName(treeId);
			S21Pair valLayer = procInstAttr.getOrgUnitValueAndLayer();
			attributeData.setValue(valLayer.getValue());
			attributeData.setLayer(Integer.parseInt(valLayer.getKey()));
			adList.add(attributeData);
		}

		// MDSE Hierarchy
		treeId = daRule.getProductGroupTreeId();
		if (S21StringUtil.isNotEmpty(treeId)) {
			attributeData = S21DataSecurityAttributeData.newMdseHierarchyAttributeData();
			attributeData.setAttributeName(treeId);
			S21Pair valLayer = procInstAttr.getProductGroupValueAndLayer();
			attributeData.setValue(valLayer.getValue());
			attributeData.setLayer(Integer.parseInt(valLayer.getKey()));
			adList.add(attributeData);
		}

		// Partner Hierarchy
		treeId = daRule.getPartnerGroupTreeId();
		if (S21StringUtil.isNotEmpty(treeId)) {
			attributeData = S21DataSecurityAttributeData.newPartnerHierarchyAttributeData();
			attributeData.setAttributeName(treeId);
			S21Pair valLayer = procInstAttr.getPartnerGroupValueAndLayer();
			attributeData.setValue(valLayer.getValue());
			attributeData.setLayer(Integer.parseInt(valLayer.getKey()));
			adList.add(attributeData);
		}

		// process instance level code attributes: TOC, MDSE, BILL_TO, SELL_TO,
		// SHIP_TO, BR, WH, ATTR1 ... ATTR8
		Set<String> codeTypeAttrNameSet = daRule.getProcInstCodeTypeDaAttrNames();
		for (String codeAttrName : codeTypeAttrNameSet) {
			attributeData = S21DataSecurityAttributeData.newCodeAttributeData();
			attributeData.setAttributeName(codeAttrName);
			String propertyName = daRule.getProcInstCodeTypeDaAttrPropertyName(codeAttrName);
			if (S21StringUtil.isNotEmpty(propertyName)) {
				String value = (String) S21BeanPropertyUtil.getProperty(procInstAttr, propertyName);
				attributeData.setValue(value);
				attributeData.setLayer(1);
				adList.add(attributeData);
			}
		}

		// ---------------------------------
		// task instance attribute
		// ---------------------------------
		// if (taskInst != null){
		WfNodeInstanceExtendedAttribute taskInstAttr = wfTaskService.getNodeInstanceExtendedAttributeForTaskInstanceId(workItem.getTaskInstId());
		// task instance level code attributes: ATTR1...4
		codeTypeAttrNameSet = daRule.getTaskInstCodeTypeDaAttrNames();
		/*
		 * for (String codeTypeAttrName : codeTypeAttrNameSet) { String
		 * columnName =
		 * daRule.getTaskInstColumnNameForCodeTypeDaAttr(codeTypeAttrName); List<String>
		 * values =
		 * wfUserProfileService.getCodeTypeDaAttributeValueListForTask(daRule.getTaskCode(),
		 * codeTypeAttrName, null); if (values != null && values.size() > 0) {
		 * attributeData.setValue(values.get(0)); attributeData.setLayer(1);
		 * adList.add(attributeData); } // WfColumnNameValuesInfo columnValInfo =
		 * param.addColumnValues(columnName, values); //if
		 * (values.contains(S21DataSecurityProfile.ATTRIBUTE_VALUE_ACCESS_ALL)) { //
		 * columnValInfo.setAllDataAccessGranted(true); //} //} else { //
		 * logger.warn("Task[" + daRule.getTaskCode() + "] requires [" +
		 * codeTypeAttrName + "] attribute, but user[" + userId + "] doesn't
		 * have data in profile."); //} }
		 */

		for (String codeAttrName : codeTypeAttrNameSet) {
			attributeData = S21DataSecurityAttributeData.newCodeAttributeData();
			attributeData.setAttributeName(codeAttrName);
			String propertyName = daRule.getTaskInstCodeTypeDaAttrPropertyName(codeAttrName);
			if (S21StringUtil.isNotEmpty(propertyName)) {
				String value = (String) S21BeanPropertyUtil.getProperty(taskInstAttr, propertyName);
				attributeData.setValue(value);
				attributeData.setLayer(1);
				adList.add(attributeData);
			}
		}
		// }

		// ---------------------------------
		// do query users
		// ---------------------------------
		userIdList = wfUserProfileService.findUserIdListWithDataSecurity(bizAppId, functionIds, adList, S21HierarchyMatchType.ALL_LAYER, S21LogicalOperator.AND, procInstAttr.getGlobalCompanyCode());
		return userIdList;
	}

	private WfNodeLevelDataAccessRule getDARuleForNodeExtAttr(WfNodeExtendedAttribute nodeAttr) {
		WfNodeLevelDataAccessRule daRule = nodeAttr.getDataAccessRule();
		return daRule;
	}

	private WfNodeExtendedAttribute getNodeExtendedAttr(String processCode, String taskCode) {
		WfNodeExtendedAttribute nodeAttr = wfProcessDefinitionService.getTaskExtendedAttributeByCode(processCode, taskCode);
		return nodeAttr;
	}

	@Override
	public String getPageId() {
		return "ZZWL3090";
	}

	@Override
	public String getPageName() {
		return "Task Instance User Search";
	}

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getDelegatorId() {
		return delegatorId;
	}

	public void setDelegatorId(String delegatorId) {
		this.delegatorId = delegatorId;
	}

	public List<String> getCanDoUserList() {
		return canDoUserList;
	}

	public void setCanDoUserList(List<String> canDoUserList) {
		this.canDoUserList = canDoUserList;
	}

	public List<String> getToDoUserListForRole() {
		return toDoUserListForRole;
	}

	public void setToDoUserListForRole(List<String> toDoUserListForRole) {
		this.toDoUserListForRole = toDoUserListForRole;
	}

	public boolean isUserInRole() {
		return userInRole;
	}

	public void setUserInRole(boolean userInRole) {
		this.userInRole = userInRole;
	}

	public String getTaskInstID() {
		return taskInstID;
	}

	public void setTaskInstID(String taskInstID) {
		this.taskInstID = taskInstID;
	}

	public WfNodeInstanceExtendedAttribute getNodeInstExtAttr() {
		return nodeInstExtAttr;
	}

	public void setNodeInstExtAttr(WfNodeInstanceExtendedAttribute nodeInstExtAttr) {
		this.nodeInstExtAttr = nodeInstExtAttr;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskRole() {
		return taskRole;
	}

	public void setTaskRole(String taskRole) {
		this.taskRole = taskRole;
	}

}
