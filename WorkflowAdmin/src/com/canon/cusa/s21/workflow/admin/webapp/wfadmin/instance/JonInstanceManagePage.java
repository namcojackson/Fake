package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.instance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.common.S21Pair;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.jobx.dispatcher.business.WfJobDispatchService;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfJobsQueryParameter;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;



public class JonInstanceManagePage extends WfAdminBasePage {
    private static final S21Logger logger = S21LoggerFactory.getLogger(JonInstanceManagePage.class);

    private final String JOB_INSTANCE_SEARCH_PARAM = "JOB_INSTANCE_SEARCH_PARAM";
    
    private List processCodes;
    
    private List jobs;
    private Map jobTypes = new HashMap();


    private List<S21Pair> jobTypeStaticList;

    private int jobId;

    private List selectedItemList = new ArrayList();

    private S21WfJobsQueryParameter queryParam = new S21WfJobsQueryParameter();

    private boolean needSearch;
    private boolean backAction;

    private int jobsCount = -1;

    /**
     * Flag to represent types of search Jobs and Job History Search
     */
    private boolean jobSearch;

    private boolean jobHistorySearch;

    //---------------------------
    // DI
    //---------------------------
    private WfJobDispatchService service;

    public void setJobDispatcherService(WfJobDispatchService service) {
        this.service = service;
    }
    
    private boolean hasErrors;


    //---------------------------
    // init
    //---------------------------
    public void initApplicationSpecificProperty() throws Exception {
        super.initApplicationSpecificProperty();
        service = (WfJobDispatchService) getBean("wfJobDispatchService", WfJobDispatchService.class);
    }

    //---------------------------
    // preparation
    //---------------------------
    public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
        //Map prepareArg = pageTransitionContext.getPrepareArgs();
		if (processCodes == null) {
			processCodes = wfProcessDefinitionService.getDistinctProcessCodes();
		}

        Map prepareArg = pageTransitionContext.getPrepareArgs();
        if(prepareArg.get("needSearch") != null){
        	needSearch = Boolean.parseBoolean((String)prepareArg.get("needSearch"));
        }
        if (needSearch) {
        	
        	if(backAction){
        		queryParam = (S21WfJobsQueryParameter) request.getSession().getAttribute(JOB_INSTANCE_SEARCH_PARAM);
        	}
        	//validate search params
        	validateQueryParam(queryParam);
        	if(!hasErrors){
	            //Check to search job history
	            if (isJobHistorySearch()) {
	                jobs = service.getArchivedJobsList(queryParam);
	            } else {
	                jobs = service.getActiveJobsList(queryParam);
	            }
	            request.getSession().setAttribute(JOB_INSTANCE_SEARCH_PARAM, queryParam);
	            this.jobsCount = jobs.size();
	            //calculate job types for display for each job id
	            for (Iterator iterator = jobs.iterator(); iterator.hasNext();) {
	                Map jobMap = (Map) iterator.next();
	                String jobtype = (String) jobMap.get("jobType");
	                jobTypes.put((Long) jobMap.get("id"), getJobTypeDesc(jobtype));
	            }
        	}

        }
    }
    
    private void validateQueryParam(S21WfJobsQueryParameter queryParam){
    	
    	validateDate(queryParam.getCreateDateAfterStr(), queryParam.getCreateDateAfter());
    	validateDate(queryParam.getCreateDateBeforeStr(), queryParam.getCreateDateBefore());
    	validateDate(queryParam.getArchiveDateAfterStr(), queryParam.getArchiveDateAfter());
    	validateDate(queryParam.getArchiveDateBeforeStr(), queryParam.getArchiveDateBefore());
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

    //---------------------------
    // pre Listeners
    //---------------------------
    public void preListener() throws Exception {
        super.preListener();
    }

    //---------------------------------------------
    // Listeners
    //---------------------------------------------
    public WfSwebDestinationPage showPage() throws Exception {
        logger.debug("showPage...");
        return activateDestinationPageWithoutLayout(getClass());
    }

    public WfSwebDestinationPage searchJobs() throws Exception {
        logger.debug("showPage...");
        this.needSearch = true;
        return activateDestinationPageWithoutLayout(getClass());
    }


    public WfSwebDestinationPage editJobs() throws Exception {
        logger.debug("editJobs...");
        this.needSearch = true;
        
        if(selectedItemList.size() == 0){
        	setGlobalActionError("no.job.selected");
        	return activateDestinationPageWithoutLayout(getClass());
        }
        
        List<String> jobIdList = new ArrayList<String>();
        for (Iterator iterator = selectedItemList.iterator(); iterator.hasNext();) {
            String jobId = (String) iterator.next();
            jobIdList.add(jobId);
        }
        addPrepareArgForDestination(JobInstanceEditPage.JOB_ID_LIST, jobIdList);
        return activateDestinationPageWithoutLayout(JobInstanceEditPage.class);
    }

  
    public WfSwebDestinationPage deleteJob() throws Exception {
        logger.debug("deleteJob...");
        this.needSearch = true;
        if(selectedItemList.size() == 0){
        	setGlobalActionError("no.job.selected");
        	return activateDestinationPageWithoutLayout(getClass());
        }        
        for (Iterator iterator = selectedItemList.iterator(); iterator.hasNext();) {
            long id = new Long((String) iterator.next());
            service.logAdminOperationAndDeleteJob(id);
        }

        return activateDestinationPageWithoutLayout(getClass());
    }    

    public WfSwebDestinationPage resendMessage() throws Exception {
        logger.debug("resendMessage...");
        this.needSearch = true;
        if(selectedItemList.size() == 0){
        	setGlobalActionError("no.job.selected");
        	return activateDestinationPageWithoutLayout(getClass());
        }        
        for (Iterator iterator = selectedItemList.iterator(); iterator.hasNext();) {
            long id = new Long((String) iterator.next());
            //job is put in queue...so that the JobWatcher will pick it up
            service.putJobInQueue(id);
        }
        return activateDestinationPageWithoutLayout(getClass());
    }

    //Private Methods
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

    public List getJobs() {
        return jobs;
    }

    public void setJobs(List jobs) {
        this.jobs = jobs;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public List getSelectedItemList() {
        return selectedItemList;
    }

    public void setSelectedItemList(int index, Object value) {
        this.selectedItemList.add(value);
    }

    public Map getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(Map jobTypes) {
        this.jobTypes = jobTypes;
    }

    public boolean isNeedSearch() {
        return needSearch;
    }

    public void setNeedSearch(boolean needSearch) {
        this.needSearch = needSearch;
    }


    public int getJobsCount() {
        return jobsCount;
    }


    public void setJobsCount(int jobsCount) {
        this.jobsCount = jobsCount;
    }

    public S21WfJobsQueryParameter getQueryParam() {
        return queryParam;
    }


    public void setQueryParam(S21WfJobsQueryParameter queryParam) {
        this.queryParam = queryParam;
    }

    /**
     * Static maps for Job class drop downs
     *
     * @return
     */
    public List<S21Pair> getJobTypeStaticList() {
        if (jobTypeStaticList == null) {
            jobTypeStaticList = new ArrayList<S21Pair>();
            jobTypeStaticList.add(new S21Pair("N", "N: Decision/System Task"));
            jobTypeStaticList.add(new S21Pair("V", "V: Transition Notification"));
            jobTypeStaticList.add(new S21Pair("W", "W: Reassign Notification"));
            jobTypeStaticList.add(new S21Pair("X", "X: Process Deadline Notification"));
            jobTypeStaticList.add(new S21Pair("Y", "Y: Task Deadline Notification"));
            jobTypeStaticList.add(new S21Pair("Z", "Z: Work Item Notification"));
            
        }
        return jobTypeStaticList;
    }

    public boolean isJobHistorySearch() {
        return jobHistorySearch;
    }


    public void setJobHistorySearch(boolean jobHistorySearch) {
        this.jobHistorySearch = jobHistorySearch;
    }


    @Override
    public String getPageId() {
        return "ZZWL8040";
    }

    @Override
    public String getPageName() {
        return "Job Management";
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
