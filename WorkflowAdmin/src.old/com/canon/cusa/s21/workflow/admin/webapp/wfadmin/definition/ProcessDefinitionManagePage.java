package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.definition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LazyList;
import com.canon.cusa.s21.framework.common.collections.S21LazyListFactory;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstanceExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.WfProcessExtendedAttribute;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;

public class ProcessDefinitionManagePage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(ProcessDefinitionManagePage.class);

	//***************************************************
	// Process definition inquiry area
	//***************************************************
	private List processCodes;
	
	private int processCodesSize;
	private int procExtAttrsSize;
	
	private String processCode;
	
	private WfProcessExtendedAttribute selectedProcExtAttr;

	private List selectedProcDefList = new ArrayList();
	
	//***************************************************
	// Process initiation area
	//***************************************************
	/**
	 * Process definition detail info associated with the selected procDefinitionId.
	 */
	private List<WfProcessExtendedAttribute> procExtAttrs;

	private List<WfNodeExtendedAttribute> nodeExtAttrs;
	/**
	 * input fields for new process instance
	 */
	private S21WfProcessInstanceExtendedAttribute procInstExtAttr = new S21WfProcessInstanceExtendedAttribute();

	//---------------------------------------------
	// Inner class
	//---------------------------------------------
	public static class WfProcessExtendedAttributeDataFactory implements S21LazyListFactory {
		public Object create() {
			return new WfProcessExtendedAttribute();
		}
	}

	//---------------------------------------------
	// Constructor
	//---------------------------------------------
	public ProcessDefinitionManagePage() {
		procExtAttrs = new S21LazyList(new ArrayList(), new WfProcessExtendedAttributeDataFactory());
	}	

	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
//		super.prepareRequiredProperties(pageTransitionContext);
		Map prepareArg = pageTransitionContext.getPrepareArgs();

		if (processCodes == null) {
			processCodes = wfProcessDefinitionService.getDistinctProcessCodes();
			processCodesSize = processCodes.size();
		}		

		//Check if process code seslected
		if (S21StringUtil.isNotEmpty(getProcessCode())) {
				procExtAttrs = wfProcessDefinitionService.getAllProcessExtendedAttributesForProcessCode(processCode);
				procExtAttrsSize = procExtAttrs.size();
		} else {
				//procExtAttr = new WfProcessExtendedAttribute();
		}

		if (selectedProcExtAttr != null) {
			if (S21StringUtil.isNotEmpty(selectedProcExtAttr.getProcessCode())) {
				nodeExtAttrs = wfProcessDefinitionService.getNodeExtendedAttributesByProcessCode((selectedProcExtAttr.getProcessCode()));
			}
			
		}
	}
	public WfSwebDestinationPage showPage() throws Exception {
		return activateDestinationPage(getClass());
	}

	public WfSwebDestinationPage executeAction() throws Exception {
		if ("Show".equals(getAction())) {
			return showProcessDefinition();
		}else if("Show Tasks".equals(getAction())){
			return showTasks();
		}
		
		return showPage();
	}

	public WfSwebDestinationPage showProcessDefinition() throws Exception {
		//addPrepareArgForDestination(PARAMETER_PROC_DEF_ID, getProcessCode());
		return activateDestinationPage(getClass());
	}

	public WfSwebDestinationPage showTasks() throws Exception {
		logger.debug("showTasks...");
		// prepare selected id list
		ArrayList<Long> selectedIdList = new ArrayList();
		/*for (int i = 0; i < procExtAttrs.size(); i++) {
			WfProcessExtendedAttribute processExtAttr = (WfProcessExtendedAttribute) procExtAttrs.get(i);
			if (processExtAttr.isSelected()) {
				this.selectedProcExtAttr = processExtAttr;
				break;
			}
		}*/
		return activateDestinationPage(getClass());
	}
	
	public WfSwebDestinationPage activateDef() throws Exception {
		if(selectedProcDefList != null && selectedProcDefList.size() > 0){
			List<Long> procDefIdLongList = new ArrayList<Long>();
			for(String str : (List<String>)selectedProcDefList){
				procDefIdLongList.add(Long.parseLong(str));
			}
			wfProcessDefinitionService.activateProcessDefinitaions(procDefIdLongList);
		}
		return activateDestinationPage(getClass());
	}
	
	public WfSwebDestinationPage deActivateDef() throws Exception {
		if(selectedProcDefList != null && selectedProcDefList.size() > 0){
			List<Long> procDefIdLongList = new ArrayList<Long>();
			for(String str : (List<String>)selectedProcDefList){
				procDefIdLongList.add(Long.parseLong(str));
			}
			
			boolean hasErrors = false;
			//check no open instance is available before deactivating
			for(Long defId : procDefIdLongList){
				int count = wfProcessDefinitionService.getOpenInstanceCountForProcessDef(defId);
				if(count > 0){
					setGlobalActionError("open.instances.found", defId+"");
					hasErrors = true;
				}
			}
			if(!hasErrors){
				wfProcessDefinitionService.deActivateProcessDefinitaions(procDefIdLongList);
			}
		}
		
		return activateDestinationPage(getClass());
	}	

	//---------------------------------------------
	// properties
	//---------------------------------------------

	public List<WfProcessExtendedAttribute> getProcExtAttrs() {
		return procExtAttrs;
	}

	public void setProcExtAttrs(List<WfProcessExtendedAttribute> procExtAttrs) {
		this.procExtAttrs = procExtAttrs;
	}

	public S21WfProcessInstanceExtendedAttribute getProcInstExtAttr() {
		return procInstExtAttr;
	}

	public void setProcInstExtAttr(S21WfProcessInstanceExtendedAttribute procInstExtAttr) {
		this.procInstExtAttr = procInstExtAttr;
	}

	public List<WfNodeExtendedAttribute> getNodeExtAttrs() {
		return nodeExtAttrs;
	}

	public void setNodeExtAttrs(List<WfNodeExtendedAttribute> nodeExtAttrs) {
		this.nodeExtAttrs = nodeExtAttrs;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	@Override
	public String getPageId() {
		return "ZZWL8010";
	}

	@Override
	public String getPageName() {
		return "Process Definition Management";
	}

	public WfProcessExtendedAttribute getSelectedProcExtAttr() {
		return selectedProcExtAttr;
	}

	public void setSelectedProcExtAttr(
			WfProcessExtendedAttribute selectedProcExtAttr) {
		this.selectedProcExtAttr = selectedProcExtAttr;
	}

	public List getSelectedProcDefList() {
		return selectedProcDefList;
	}

    public void setSelectedProcDefList(int index, Object value) {
        this.selectedProcDefList.add(value);
    }

	public int getProcessCodesSize() {
		return processCodesSize;
	}

	public void setProcessCodesSize(int processCodesSize) {
		this.processCodesSize = processCodesSize;
	}

	public int getProcExtAttrsSize() {
		return procExtAttrsSize;
	}

	public void setProcExtAttrsSize(int procExtAttrsSize) {
		this.procExtAttrsSize = procExtAttrsSize;
	}

	public List getProcessCodes() {
		return processCodes;
	}

	public void setProcessCodes(List processCodes) {
		this.processCodes = processCodes;
	}


	
	


}
