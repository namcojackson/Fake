package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.jobx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.jobx.dispatcher.business.WfJobDispatchService;
import com.canon.cusa.s21.framework.workflow.core.model.WfJobDispatcherHeartBeat;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;

public class JobDispatcherManagePage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(JobDispatcherManagePage.class);
	private List<WfJobDispatcherHeartBeat> heartBeats;
	private int heartBeatsSize;
	private String radioName;
	private WfJobDispatchService wfJobDispatchService;
	private Date currDate = new Date();
	
    public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

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
		heartBeats = wfJobDispatchService.getJobDispatcherHeartBeats();
		heartBeatsSize = heartBeats.size();
	}

	//---------------------------------------------
	// Listeners
	//---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		//Get all the heart beats
		
		
		return activateDestinationPageWithoutLayout(getClass());
	}
	
	public WfSwebDestinationPage deleteHeartBeats() throws Exception{
		logger.debug("deleteHeartBeats...");
		if(S21StringUtil.isNotEmpty(radioName)){
			wfJobDispatchService.deleteJobDispatcherHeartBeatByName(radioName);
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
		return "Job Dispatcher Management";
	}

	public List<WfJobDispatcherHeartBeat> getHeartBeats() {
		return heartBeats;
	}

	public void setHeartBeats(List<WfJobDispatcherHeartBeat> heartBeats) {
		this.heartBeats = heartBeats;
	}

	public String getRadioName() {
		return radioName;
	}

	public void setRadioName(String radioName) {
		this.radioName = radioName;
	}

	public int getHeartBeatsSize() {
		return heartBeatsSize;
	}

	public void setHeartBeatsSize(int heartBeatsSize) {
		this.heartBeatsSize = heartBeatsSize;
	}
	
	
	
	

}



