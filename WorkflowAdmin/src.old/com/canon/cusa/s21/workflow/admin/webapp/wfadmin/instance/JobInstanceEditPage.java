package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.instance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jbpm.job.Job;

import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.jobx.dispatcher.business.WfJobDispatchService;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfJobsQueryParameter;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo.JobInstanceEditPageView;

public class JobInstanceEditPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(JobInstanceEditPage.class);

	

	public static String JOB_ID_LIST = "jobIdList";
	
	private List dueDate  = new ArrayList();
	private Map jobTypes = new HashMap();
	
	private S21WfJobsQueryParameter queryParam = new S21WfJobsQueryParameter();
	
	
	private boolean jobHistorySearch;
	
	//***************************************************
	//Job detail area
	//***************************************************
	private List activeJobs;

	boolean hasErrors = false;
	
	//job id placeholder
	private List<String> jobIdList = new ArrayList();

	private List jobMappings = null;

	//---------------------------
	// DI
	//---------------------------
	private WfJobDispatchService service;

	public void setJobDispatcherService(WfJobDispatchService service) {
		this.service = service;
	}

	//---------------------------
	// init
	//---------------------------
	public void initApplicationSpecificProperty() throws Exception {
		super.initApplicationSpecificProperty();
		service = (WfJobDispatchService) getBean("wfJobDispatchService",WfJobDispatchService.class);
	}

	//---------------------------
	// preparation
	//---------------------------
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
		Map prepareArg = pageTransitionContext.getPrepareArgs();
		
		if(prepareArg.get(JOB_ID_LIST) != null){
			this.jobIdList  = (List<String>)prepareArg.get(JOB_ID_LIST);
		}
		if(jobIdList == null || jobIdList.size() == 0){
			setGlobalActionError("no.job.selected");
			return;
		}
		queryParam.setJobIdList(jobIdList);
		
		this.activeJobs = service.getActiveJobsList(queryParam);
		
		if(activeJobs.size() == 0){
			setGlobalActionError("no.job.found");
			return;
		}
		
        //calculate job types for display for each job id
        for (Iterator iterator = activeJobs.iterator(); iterator.hasNext();) {
            Map jobMap = (Map) iterator.next();
            String jobtype = (String) jobMap.get("jobType");
            jobTypes.put((Long) jobMap.get("id"), getJobTypeDesc(jobtype));
        }		
	}

	//---------------------------------------------
	// Listeners
	//---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		return activateDestinationPage(getClass());
	}

	public WfSwebDestinationPage save() throws Exception {
		logger.debug("saving jobs...");
		List<String> newjobIdList = new ArrayList();
		jobMappings = new ArrayList();
		
		for(int i =0; i< jobIdList.size() ; i++){
			String dueDate  =  (String)getDueDate().get(i);
			if(hasErrors){
				break;
			}
			validateGivenDate(dueDate);
		}			
		
		if(hasErrors){
			jobMappings = null;
		}else{
			
			for(int i =0; i< jobIdList.size() ; i++){
				
				String dueDate  =  (String)getDueDate().get(i);
				Job originalJob = service.loadJob(Long.parseLong(jobIdList.get(i)));
				Job newJob = service.saveJob(originalJob.getId(), parseDate(dueDate));
				newjobIdList.add(newJob.getId()+"");
				
				JobInstanceEditPageView jobView = new JobInstanceEditPageView();
				jobView.setOriginalJob(originalJob);
				jobView.setJob(newJob);
				jobMappings.add(jobView);
			}

			jobIdList = newjobIdList;
			addPrepareArgForDestination(JOB_ID_LIST, jobIdList);
		}
	
		return activateDestinationPage(getClass());
	}
	
	private void validateGivenDate(String dateStr){
		try{
			parseDate(dateStr);
		}catch(Exception e){
			setGlobalActionError("invalid.date", dateStr);
			hasErrors = true;
		}
	}
	
	private Date parseDate(String dateStr) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return dateFormat.parse(dateStr);
	}

	public List getActiveJobs() {
		return activeJobs;
	}

	public void setActiveJobs(List activeJobs) {
		this.activeJobs = activeJobs;
	}

	public List getJobIdList() {
		return jobIdList;
	}

	public void setJobIdList(int index, Object value) {
			this.jobIdList.add((String)value);
	}

	@Override
	public String getPageId() {
		return "ZZWL8040";
	}

	@Override
	public String getPageName() {
		return "Edit Job Instances";
	}

	public List getDueDate() {
		return dueDate;
	}

	public void setDueDate(int index, Object value) {
		this.dueDate.add(value);
	}

	public boolean isJobHistorySearch() {
		return jobHistorySearch;
	}

	public void setJobHistorySearch(boolean jobHistorySearch) {
		this.jobHistorySearch = jobHistorySearch;
	}

	public S21WfJobsQueryParameter getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(S21WfJobsQueryParameter queryParam) {
		this.queryParam = queryParam;
	}

	public List getJobMappings() {
		return jobMappings;
	}

	public void setJobMappings(List jobMappings) {
		this.jobMappings = jobMappings;
	}

	public Map getJobTypes() {
		return jobTypes;
	}

	public void setJobTypes(Map jobTypes) {
		this.jobTypes = jobTypes;
	}

    private String getJobTypeDesc(String jobType) {

        if (jobType.equals("V")) {
            return "V: Transition Notification";
        } else if (jobType.equals("W")) {
            return "W: Reassign Notification";
        } else if (jobType.equals("X")) {
            return "X: Process Deadline Notification";
        } else if (jobType.equals("Y")) {
            return "Y: Task Deadline Notification";
        } else if (jobType.equals("Z")) {
            return "Z: Work Item Notification";
        } else {
            return "J: Decision/System Task";
        }
    }	

}
