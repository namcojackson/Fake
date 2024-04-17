package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.jobx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.jobx.dispatcher.business.WfJobDispatchService;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;

public class ProcessDeployGroupManagePage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(ProcessDeployGroupManagePage.class);
	private List<Map> groups;
	private int groupsSize;
	private List selectedList = new ArrayList();
	private String groupName;
	private String processCode;
	private WfJobDispatchService wfJobDispatchService;


	public void setJobDispatcherService(WfJobDispatchService wfJobDispatchService) {
        this.wfJobDispatchService = wfJobDispatchService;
    }
    
	//---------------------------
	// init
	//---------------------------
	public void initApplicationSpecificProperty() throws Exception {
		super.initApplicationSpecificProperty();
		wfJobDispatchService = (WfJobDispatchService) getBean("wfJobDispatchService", WfJobDispatchService.class);
	}

	//---------------------------
	// preparation
	//---------------------------
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
		Map prepareArg = pageTransitionContext.getPrepareArgs();
		groups = wfJobDispatchService.getProcessDeployGroups();
		groupsSize = groups.size();
	}

	//---------------------------------------------
	// Listeners
	//---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		//Get all the heart beats
		
		
		return activateDestinationPageWithoutLayout(getClass());
	}
	
	public WfSwebDestinationPage deleteGroup() throws Exception{
		logger.debug("deleteGroup...");
		if(selectedList != null ){
			for(String selectedItem : (List<String>)selectedList){
				if(S21StringUtil.isNotEmpty(selectedItem)){
					String delGrp = selectedItem.substring(0, selectedItem.indexOf("~"));
					String delProcCode = selectedItem.substring(selectedItem.indexOf("~")+1);
					wfJobDispatchService.deleteProcessDeployGroup(delGrp, delProcCode);
				}
				
			}
			
		}
		return activateDestinationPageWithoutLayout(getClass());
	}
	
	public WfSwebDestinationPage createGroup() throws Exception{
		logger.debug("createGroup...");
		//throw error msg if either of them are empty
		if(S21StringUtil.isEmpty(groupName) || S21StringUtil.isEmpty(processCode)){
			setGlobalActionError("invalid.group.processcode");
		}else{
			wfJobDispatchService.createProcessDeployGroup(groupName, processCode);
		}
		return activateDestinationPageWithoutLayout(getClass());
	}	

	@Override
	public String getPageId() {
		// TODO Auto-generated method stub
		return "ZZWL8050";
	}

	@Override
	public String getPageName() {
		// TODO Auto-generated method stub
		return "Job Dispatcher Config Management";
	}


	public List<Map> getGroups() {
		return groups;
	}

	public void setGroups(List<Map> groups) {
		this.groups = groups;
	}

	public int getGroupsSize() {
		return groupsSize;
	}

	public void setGroupsSize(int groupsSize) {
		this.groupsSize = groupsSize;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}


	public List getSelectedList() {
		return selectedList;
	}

    public void setSelectedList(int index, Object value) {
        this.selectedList.add(value);
    }
	
}



