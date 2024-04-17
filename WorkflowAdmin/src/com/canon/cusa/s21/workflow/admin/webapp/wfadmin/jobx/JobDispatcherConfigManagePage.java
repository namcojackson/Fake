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

public class JobDispatcherConfigManagePage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(JobDispatcherConfigManagePage.class);
	private List<Map> configs;
	private int configsSize;
	private List selectedList = new ArrayList();
	private String threadName;
	private boolean enabled;
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
		configs = wfJobDispatchService.getJobDispatcherConfigs();
		configsSize = configs.size();
	}

	//---------------------------------------------
	// Listeners
	//---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		//Get all the heart beats
		
		
		return activateDestinationPageWithoutLayout(getClass());
	}
	
	public WfSwebDestinationPage deleteConfigs() throws Exception{
		logger.debug("deleteConfigs...");
		if(selectedList != null ){
			for(String selectedItem : (List<String>)selectedList){
				if(S21StringUtil.isNotEmpty(selectedItem)){
					wfJobDispatchService.deleteJobDispatcherConfigByName(selectedItem);
				}
			}
		}			
		return activateDestinationPageWithoutLayout(getClass());
	}
	
	public WfSwebDestinationPage enableConfigs() throws Exception{
		logger.debug("enableConfigs...");
		if(selectedList != null ){
			for(String selectedItem : (List<String>)selectedList){
				if(S21StringUtil.isNotEmpty(selectedItem)){
					wfJobDispatchService.updateJobDispatcherConfig(selectedItem, true);
					
				}
			}
		}		
		return activateDestinationPageWithoutLayout(getClass());
	}	
	
	public WfSwebDestinationPage disableConfigs() throws Exception{
		logger.debug("disableConfigs...");
		if(selectedList != null ){
			for(String selectedItem : (List<String>)selectedList){
				if(S21StringUtil.isNotEmpty(selectedItem)){
					wfJobDispatchService.updateJobDispatcherConfig(selectedItem, false);
					
				}
			}
		}
		
		return activateDestinationPageWithoutLayout(getClass());
	}	
	
	public WfSwebDestinationPage createConfigs() throws Exception{
		logger.debug("createConfigs...");
		if(S21StringUtil.isNotEmpty(threadName)){
			wfJobDispatchService.createJobDispatcherConfig(threadName, enabled);
		}else{
			setGlobalActionError("empty.threadName");
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


	public List<Map> getConfigs() {
		return configs;
	}

	public void setConfigs(List<Map> configs) {
		this.configs = configs;
	}

	public int getConfigsSize() {
		return configsSize;
	}

	public void setConfigsSize(int configsSize) {
		this.configsSize = configsSize;
	}


	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public List getSelectedList() {
		return selectedList;
	}

    public void setSelectedList(int index, Object value) {
        this.selectedList.add(value);
    }	
}



