/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: Jan 8, 2009
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.instance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.jbpm.graph.exe.ProcessInstance;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LazyList;
import com.canon.cusa.s21.framework.common.collections.S21LazyListFactory;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.jobx.dispatcher.business.WfJobDispatchService;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstance;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstanceExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstanceQueryParameter;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;


/**
 * <p>
 * </p>
 *
 * @author $Author: MasahitoNakahara $
 * @version $Revision: 1.12 $ $Date: 2009/04/28 16:06:36 $
 */
public class ProcessInstanceSearchPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(ProcessInstanceSearchPage.class);

	private final String PROCESS_INSTANCE_SEARCH_PARAM = "PROCESS_INSTANCE_SEARCH_PARAM";
	private boolean needSearch;
	private S21WfProcessInstanceQueryParameter queryParam = new S21WfProcessInstanceQueryParameter();
	private List<S21WfProcessInstance> processList;
	private List processCodes;
	private int processListCount = -1;
	private boolean hasErrors;
	private boolean backAction;
	private String[] maxResultCountSelections = new String[]{"20", "50", "100", "300", "500"};

	public boolean isNeedSearch() {
		return needSearch;
	}

	public void setNeedSearch(boolean needSearch) {
		this.needSearch = needSearch;
	}

	//---------------------------------------------
	// Inner class
	//---------------------------------------------
	public static class WfAdminProcessDataFactory implements S21LazyListFactory {
		public Object create() {
			return new S21WfProcessInstance();
		}
	}

	//---------------------------------------------
	// Constructor
	//---------------------------------------------
	public ProcessInstanceSearchPage() {
		processList = new S21LazyList(new ArrayList(), new WfAdminProcessDataFactory());
	}

	//---------------------------
	// preparation
	//---------------------------
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
		
		if (processCodes == null) {
			processCodes = wfProcessDefinitionService.getDistinctProcessCodes();
		}
		
		if (needSearch) {
			if(isBackAction()){
				queryParam = (S21WfProcessInstanceQueryParameter)request.getSession().getAttribute(PROCESS_INSTANCE_SEARCH_PARAM);
			}
        	validateQueryParam(queryParam);
        	if(!hasErrors){
//        		queryParam.setSkipRowIndex(1);
        		queryParam.setMaxResultCount(queryParam.getMaxResultCount() - 1);
				processList = wfProcessService.getWfProcessInstanceList(queryParam);
				queryParam.setMaxResultCount(queryParam.getMaxResultCount() + 1);
				processListCount = processList.size();
				request.getSession().setAttribute(PROCESS_INSTANCE_SEARCH_PARAM, queryParam);				
        	}
		}
	}
	
    private void validateQueryParam(S21WfProcessInstanceQueryParameter queryParam){
    	
    	validateDate(queryParam.getStartDateAfterStr(), queryParam.getStartDateAfter());
    	validateDate(queryParam.getStartDateBeforeStr(), queryParam.getStartDateBefore());
    	validateDate(queryParam.getDueDateAfterStr(), queryParam.getDueDateAfter());
    	validateDate(queryParam.getDueDateBeforeStr(), queryParam.getDueDateBefore());
    }
    
    private void validateDate(String dateStr, Date date){
    	if(S21StringUtil.isNotEmpty(dateStr)){
    		if(date == null){
    			//invalid datestr
    			setGlobalActionError("invalid.date", dateStr);
    			hasErrors = true;
    		}
    	}    	
    }	

	//---------------------------------------------
	// Listeners
	//---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		processListCount = -1;
		return activateDestinationPage(getClass());
	}

	public WfSwebDestinationPage search() throws Exception {
		logger.debug("search...");
		needSearch = true;
		return activateDestinationPage(getClass());
	}

	public WfSwebDestinationPage abortProcess() throws Exception {
		logger.debug("abortProcess...");
		needSearch = true;
		boolean canAbort = true;
		List<Long> selectedIdList = getSelectedProcessInstances();
		// abort only suspended process instances
		for(Long selectedProcInstId : selectedIdList){
			ProcessInstance selectedProcInst = wfProcessService.getProcessInstance(selectedProcInstId);
			if(!selectedProcInst.isSuspended()){
				S21WfProcessInstanceExtendedAttribute selectedWfProcInstExtAttr = wfProcessService.getProcessInstanceExtendedAttributeForProcInst(selectedProcInstId);
				setGlobalActionError("process.instance.not.suspended", selectedWfProcInstExtAttr.getDocumentId());
				canAbort = false;
				break;
			}
		}
		if(canAbort){
			// do abort
			wfProcessService.cancelProcessInstanceByIdList(selectedIdList);
		}
		return activateDestinationPage(getClass());
	}
	
	public WfSwebDestinationPage suspendProcess() throws Exception {
		logger.debug("suspendProcess...");
		needSearch = true;
		boolean canSuspend = true;
		List<Long> selectedIdList = getSelectedProcessInstances();
		// suspend only pending process instances
		for(Long selectedProcInstId : selectedIdList){
			ProcessInstance selectedProcInst = wfProcessService.getProcessInstance(selectedProcInstId);
			if(selectedProcInst.getEnd() != null){
				S21WfProcessInstanceExtendedAttribute selectedWfProcInstExtAttr = wfProcessService.getProcessInstanceExtendedAttributeForProcInst(selectedProcInstId);
				setGlobalActionError("process.instance.already.ended", selectedWfProcInstExtAttr.getDocumentId());
				canSuspend = false;
				break;
			}
		}		
		if(canSuspend){
			// do suspend
			wfProcessService.suspendProcessInstanceByIdList(selectedIdList);
		}
		return activateDestinationPage(getClass());
	}	
	
	public WfSwebDestinationPage resumeProcess() throws Exception {
		logger.debug("resumeProcess...");
		needSearch = true;
		boolean canResume = true;
		List<Long> selectedIdList = getSelectedProcessInstances();
		// resume only suspended process instances
		for(Long selectedProcInstId : selectedIdList){
			ProcessInstance selectedProcInst = wfProcessService.getProcessInstance(selectedProcInstId);
			if(selectedProcInst.getEnd() != null ||  !selectedProcInst.isSuspended()){
				S21WfProcessInstanceExtendedAttribute selectedWfProcInstExtAttr = wfProcessService.getProcessInstanceExtendedAttributeForProcInst(selectedProcInstId);
				setGlobalActionError("process.instance.cannot.resume", selectedWfProcInstExtAttr.getDocumentId());
				canResume = false;
				break;
			}
		}		
		if(canResume){
			//do resume
			wfProcessService.resumeProcessInstanceByIdList(selectedIdList);
		}
		return activateDestinationPage(getClass());
	}		
	
	private List<Long> getSelectedProcessInstances(){
		// prepare selected id list
		List<Long> selectedIdList = new ArrayList();
		for (int i = 0; i < processList.size(); i++) {
			S21WfProcessInstance processData = (S21WfProcessInstance) processList.get(i);
			if (processData.isSelected()) {
				selectedIdList.add(new Long(processData.getInstanceId()));
			}
		}		
		return selectedIdList;
	}
	
	public WfSwebDestinationPage editProcess() throws Exception {
		logger.debug("cancelProcess...");
		// prepare selected id list
		ArrayList<Long> selectedIdList = new ArrayList();
		for (int i = 0; i < processList.size(); i++) {
			S21WfProcessInstance processData = (S21WfProcessInstance) processList.get(i);
			if (processData.isSelected()) {
				Long processInstanceId  = processData.getInstanceId();
				addPrepareArgForDestination("procInstID", processInstanceId);
				logger.debug("Selected process instance id =" + processInstanceId);
				break;
			}
		}
		
		return redirectDestinationPage(ProcessInstanceEditPage.class);
	}

	//---------------------------------------------
	// Properties
	//---------------------------------------------
	public S21WfProcessInstanceQueryParameter getSearchParam() {
		return queryParam;
	}

	public void setSearchParam(S21WfProcessInstanceQueryParameter queryParam) {
		this.queryParam = queryParam;
	}

	public List<S21WfProcessInstance> getProcessList() {
		return processList;
	}

	public void setProcessList(List<S21WfProcessInstance> processList) {
		this.processList = processList;
	}

	public int getProcessListCount() {
		return processListCount;
	}

	public void setProcessListCount(int processListCount) {
		this.processListCount = processListCount;
	}

	public String[] getMaxResultCountSelections() {
		return maxResultCountSelections;
	}

	@Override
	public String getPageId() {
		return "ZZWL8020";
	}

	@Override
	public String getPageName() {
		return "Process Instance Management";
	}

	public boolean isBackAction() {
		return backAction;
	}

	public void setBackAction(boolean backAction) {
		this.backAction = backAction;
	}

	public List getProcessCodes() {
		return processCodes;
	}

	public void setProcessCodes(List processCodes) {
		this.processCodes = processCodes;
	}
	
	


}