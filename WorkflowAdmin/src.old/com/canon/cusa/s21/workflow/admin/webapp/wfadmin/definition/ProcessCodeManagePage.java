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
import com.canon.cusa.s21.framework.workflow.core.model.WfProcessExtendedAttribute;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;

public class ProcessCodeManagePage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(ProcessCodeManagePage.class);

	//***************************************************
	// selected process code
	//***************************************************
	private String processCode;
	
	private List processCodes;
	
	private int processCodesSize;
	
	private int procExtAttrsSize;
	//***************************************************
	// Process initiation area
	//***************************************************
	/**
	 * Process definition detail info associated with the selected procDefinitionId.
	 */
	private List<WfProcessExtendedAttribute> procExtAttrs = new ArrayList<WfProcessExtendedAttribute>();

	//---------------------------------------------
	// Constructor
	//---------------------------------------------
	public ProcessCodeManagePage() {
	}	
	
/*	*//**
	 * to provide process dead line from application
	 *//*
	private String processDeadline;

	*//**
	 * to specify application specific next task user
	 *//*
	private String nextTaskUser;*/

	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
//		super.prepareRequiredProperties(pageTransitionContext);
		if (processCodes == null) {
			processCodes = wfProcessDefinitionService.getDistinctProcessCodes();
			processCodesSize = processCodes.size();
		}

		//Check if process code seslected
		if (S21StringUtil.isNotEmpty(processCode)) {
				procExtAttrs = wfProcessDefinitionService.getAllProcessExtendedAttributesForProcessCode(processCode);
				procExtAttrsSize =procExtAttrs.size();
		} 
	}

	public WfSwebDestinationPage showPage() throws Exception {
		return activateDestinationPage(getClass());
	}

	public WfSwebDestinationPage stopProcessCode() throws Exception {
		logger.debug("stopProcessCode...");
		if (S21StringUtil.isNotEmpty(processCode)) {
			wfProcessDefinitionService.stopServiceForProcessCode(processCode);
		}		
		return activateDestinationPage(getClass());
	}

	public WfSwebDestinationPage resumeProcessCode() throws Exception {
		logger.debug("resumeProcessCode...");
		if (S21StringUtil.isNotEmpty(processCode)) {
			wfProcessDefinitionService.resumeServiceForProcessCode(processCode);
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

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	
	

	public List getProcessCodes() {
		return processCodes;
	}

	public void setProcessCodes(List processCodes) {
		this.processCodes = processCodes;
	}

	@Override
	public String getPageId() {
		return "ZZWL8010";
	}

	@Override
	public String getPageName() {
		return "Process Code Management";
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


	
}
