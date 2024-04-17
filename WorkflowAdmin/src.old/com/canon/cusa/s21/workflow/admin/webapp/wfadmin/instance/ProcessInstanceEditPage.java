package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.instance;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.jbpm.graph.exe.ProcessInstance;

import com.canon.cusa.s21.framework.common.S21Pair;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LazyList;
import com.canon.cusa.s21.framework.common.collections.S21LazyListFactory;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.context.WfHumanTaskExecutionContextImpl;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfAlreadyProcessedException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfConcurrentUpdateException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfDuplicateInstanceCreationException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfSecurityViolationException;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfUnexpectedDataSetupException;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstanceExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfVariables;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItem;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItemQueryParameter;
import com.canon.cusa.s21.framework.workflow.core.model.WfProcessExtendedAttribute;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;


public class ProcessInstanceEditPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(ProcessInstanceEditPage.class);
	
	private static final String PARAMETER_PROC_INST_ID = "procInstID";
	
    //***************************************************
    // Process inquiry area
    //***************************************************
    /**
     * Process instance detail info associated with the selected procInstID.
     */
    private S21WfProcessInstanceExtendedAttribute procInstExtAttr = new S21WfProcessInstanceExtendedAttribute();
    
    private ProcessInstance processInstance;
    
    /**
     * Nodes taht currently tokens stay.
     * List contains processCode, taskInstId, tokenId, taskCode, taskName...
     */
    private List<S21WfWorkItem> activeNodes;
    
    private int activeNodesSize;
    
    
    /**
     * to provide process dead line from application
     */
    private String processDeadline;
    
    private String selectedTaskId;
    
    /**
     * WF variable data holder
     */
    private List<S21Pair> varList;
    
    private String procInstID;
    
    private boolean hasErrors;
    
    
    /**
     * Process definition detail info associated with the selected procInstID.
     */
    private WfProcessExtendedAttribute procExtAttr = new WfProcessExtendedAttribute();;

	//---------------------------------------------
	// Inner class
	//---------------------------------------------
	public static class WfVariableDataFactory implements S21LazyListFactory {
		public Object create() {
			return new S21Pair();
		}
	}     

	//---------------------------------------------
	// Constructor
	//---------------------------------------------
	public ProcessInstanceEditPage() {
		varList = new S21LazyList(new ArrayList(), new WfVariableDataFactory());
	}
	
    
	@Override
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {

			procInstExtAttr = wfProcessService.getProcessInstanceExtendedAttributeForProcInst(Long.valueOf(procInstID));
			
			processInstance = wfProcessService.getProcessInstance(Long.valueOf(procInstID));
	        
			// variable
	        S21WfVariables wfVar = wfProcessService.getProcessVariables(Long.valueOf(procInstID));
	        Iterator it = wfVar.keySet().iterator();
	        List varList = getVarList();
	        int idx = 0;
	        while (it.hasNext()) {
	            String key = (String) it.next();
	            S21Pair elem = (S21Pair) varList.get(idx);
	            elem.setKey(key);
	            elem.setValue((String) wfVar.get(key));
	            idx++;
	        }
	        
	        if (procExtAttr == null) {
	            if (S21StringUtil.isNotEmpty(procInstID)) {
	                try {
	                    procExtAttr = wfProcessService.getProcessExtendedAttributeForProcessInst(Long.valueOf(procInstID));
	                } catch (RuntimeException e) { // eg. null pointer(not found)
	                    procExtAttr = new WfProcessExtendedAttribute();
	                }
	            } else {
	                procExtAttr = new WfProcessExtendedAttribute();
	            }
	        }
	        
	        
	        S21WfWorkItemQueryParameter option = new S21WfWorkItemQueryParameter();
	        option.setIncludeSystemTask(false);
	        option.setAdminQuery(true);
//	        option.setTaskInstanceStatus(S21WfWorkItemQueryParameter.INST_STATUS_OPEN);
	        option.setProcessInstanceId(procInstID);
	        String userId = null;
	        activeNodes = wfWorkItemQueryService.getWorkItemListForTaskStatus(option, userId);
	        activeNodesSize = activeNodes.size();
			
			logger.debug("prepareRequiredProperties...");
	}
	
	
	
	//---------------------------------------------
	// Listeners
	//---------------------------------------------
    public WfSwebDestinationPage executeAction() throws Exception {
        if ("EditTask".equals(getAction())) {
            return editTask();
        }else if ("Update".equals(getAction())) {
            return update();
        } else {
            return showPage();
        }
    }
	
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		return activateDestinationPage(getClass());
	}
	
	public WfSwebDestinationPage editTask() throws Exception {
		   
			logger.debug("cancelProcess...");
		
			String taskinstId = getSelectedTaskId();
			addPrepareArgForDestination("taskInstID",taskinstId);
			addPrepareArgForDestination("taskSearchSourcePage", false);
			logger.debug("edit task instance id" + taskinstId);
			return redirectDestinationPage(TaskInstanceEditPage.class);
	 }
	
	public WfSwebDestinationPage update() throws Exception {
		
		if(S21StringUtil.isNotEmpty(processDeadline)){
			if(!S21StringUtil.isValidDateFormat(processDeadline, S21WfVariables.PROCESS_DEADLINE_VARIABLE_FORMAT)){
				hasErrors = true;
				setGlobalActionError("invalid.date", processDeadline);
			}
		}
		
		if(!hasErrors){
			
	        try {
	            S21WfProcessInstanceExtendedAttribute persistedProcInstExtAttr = wfProcessService.getProcessInstanceExtendedAttributeForProcInst(Long.valueOf(procInstID));
	            // copy DA attr
	            persistedProcInstExtAttr.setProductGroupTreePath(
	                    procInstExtAttr.getProductGroupTreeId(),
	                    procInstExtAttr.getProductGroupNodeValueLevel1(),
	                    procInstExtAttr.getProductGroupNodeValueLevel2(),
	                    procInstExtAttr.getProductGroupNodeValueLevel3(),
	                    procInstExtAttr.getProductGroupNodeValueLevel4(),
	                    procInstExtAttr.getProductGroupNodeValueLevel5(),
	                    procInstExtAttr.getProductGroupNodeValueLevel6(),
	                    procInstExtAttr.getProductGroupNodeValueLevel7(),
	                    procInstExtAttr.getProductGroupNodeValueLevel8()
	            );
	            persistedProcInstExtAttr.setPartnerGroupTreePath(
	                    procInstExtAttr.getPartnerGroupTreeId(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel1(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel2(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel3(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel4(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel5(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel6(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel7(),
	                    procInstExtAttr.getPartnerGroupNodeValueLevel8()
	            );
	            persistedProcInstExtAttr.setOrgUnitTreePath(
	                    procInstExtAttr.getOrgUnitTreeId(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel1(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel2(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel3(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel4(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel5(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel6(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel7(),
	                    procInstExtAttr.getOrgUnitNodeValueLevel8()
	            );
	            persistedProcInstExtAttr.setMerchandiseCode(procInstExtAttr.getMerchandiseCode());
	            persistedProcInstExtAttr.setTransactionOrgCode(procInstExtAttr.getTransactionOrgCode());
	            persistedProcInstExtAttr.setBillTo(procInstExtAttr.getBillTo());
	            persistedProcInstExtAttr.setSellTo(procInstExtAttr.getSellTo());
	            persistedProcInstExtAttr.setShipTo(procInstExtAttr.getShipTo());
	            persistedProcInstExtAttr.setWarehouse(procInstExtAttr.getWarehouse());
	            persistedProcInstExtAttr.setBranch(procInstExtAttr.getBranch());
	
	            //Set task attributes - 1 to 8
	            persistedProcInstExtAttr.setAttr1(procInstExtAttr.getAttr1());
	            persistedProcInstExtAttr.setAttr2(procInstExtAttr.getAttr2());
	            persistedProcInstExtAttr.setAttr3(procInstExtAttr.getAttr3());
	            persistedProcInstExtAttr.setAttr4(procInstExtAttr.getAttr4());
	            persistedProcInstExtAttr.setAttr5(procInstExtAttr.getAttr5());
	            persistedProcInstExtAttr.setAttr6(procInstExtAttr.getAttr6());
	            persistedProcInstExtAttr.setAttr7(procInstExtAttr.getAttr7());
	            persistedProcInstExtAttr.setAttr8(procInstExtAttr.getAttr8());
	
	//            persistedProcInstExtAttr.setRegion(procInstExtAttr.getRegion());
	//            persistedProcInstExtAttr.setTerritory(procInstExtAttr.getTerritory());
	            
	            if (S21StringUtil.isValidDateFormat(processDeadline, S21WfVariables.PROCESS_DEADLINE_VARIABLE_FORMAT)) {
	                Date processDeadlinDueDate = S21WfVariables.PROCESS_DEADLINE_VARIABLE_FORMAT.parse(processDeadline);
	               // procInstExtAttr.setDueDate(processDeadlinDueDate);
	                persistedProcInstExtAttr.setDueDate(processDeadlinDueDate);
	            }
	            
	            //also check if  due date was previously set and admin is trying to remove due date
	            S21WfVariables vars = getWfVariables();
	            if(persistedProcInstExtAttr.getDueDate() != null && S21StringUtil.isEmpty(processDeadline)){
	               	persistedProcInstExtAttr.setDueDate(null);
	            }
	            
//	            WfHumanTaskExecutionContextImpl wfExecCtx = new WfHumanTaskExecutionContextImpl(
//	                    null, null, null, null, null, persistedProcInstExtAttr, null, null);
	
//	            wfExecCtx.setWfVariables(vars);
//	            wfTaskService.saveAdminOperations(wfExecCtx);
	            wfTaskService.updateProcessInstanceExtAttrByAdmin(persistedProcInstExtAttr, vars, getUserId(),"");
	
	        }
	        catch (Exception e) {
	          if (e.getCause() instanceof ServletException) {
	            Throwable t = e.getCause();
	
	            if (t != null) {
	              t = t.getCause();
	            }
	
	            if (t instanceof S21WfSecurityViolationException) {
	              setGlobalActionError("error.security", t.getMessage());
	              return activateDestinationPage(getClass());
	
	            } else if (t instanceof S21WfUnexpectedDataSetupException) {
	              setGlobalActionError("error.data", t.getMessage());
	              return activateDestinationPage(getClass());
	
	            } else if (t instanceof S21WfAlreadyProcessedException) {
	              setGlobalActionError("error.already", t.getMessage());
	              return activateDestinationPage(getClass());
	
	            } else if (t instanceof S21WfConcurrentUpdateException) {
	              setGlobalActionError("error.concurrent", t.getMessage());
	              return activateDestinationPage(getClass());
	
	            } else if (t instanceof S21WfDuplicateInstanceCreationException) {
	              setGlobalActionError("error.duplicate", t.getMessage());
	              return activateDestinationPage(getClass());
	
	            } else {
	              setGlobalActionError("error.unrecoverable", e.getClass().getSimpleName());
	              e.printStackTrace();
	              return activateDestinationPage(getClass());
	            }
	
	          } else {
	            setGlobalActionError("error.unrecoverable", e.getClass().getSimpleName());
	            e.printStackTrace();
	            return activateDestinationPage(getClass());
	          }
	        }
		}

        addPrepareArgForDestination(PARAMETER_PROC_INST_ID, getProcInstID());
        return activateDestinationPage(getClass());
    }	
		
	protected S21WfVariables getWfVariables() {
		S21WfVariables vars = new S21WfVariables();
		List<S21Pair> varList = getVarList();
		for (S21Pair elem : varList) {
			if (S21StringUtil.isNotEmpty(elem.getKey()) && S21StringUtil.isNotEmpty(elem.getValue())){
				vars.put(elem.getKey(), elem.getValue());
			}
		}
		return vars;
	}			
			
	public S21WfProcessInstanceExtendedAttribute getProcInstExtAttr() {
		return procInstExtAttr;
	}


	public void setProcInstExtAttr(S21WfProcessInstanceExtendedAttribute procInstExtAttr) {
		this.procInstExtAttr = procInstExtAttr;
	}


	public WfProcessExtendedAttribute getProcExtAttr() {
		return procExtAttr;
	}


	public void setProcExtAttr(WfProcessExtendedAttribute procExtAttr) {
		this.procExtAttr = procExtAttr;
	}


	public List<S21WfWorkItem> getActiveNodes() {
		return activeNodes;
	}


	public void setActiveNodes(List<S21WfWorkItem> activeNodes) {
		this.activeNodes = activeNodes;
	}


	public String getProcessDeadline() {
		return processDeadline;
	}


	public void setProcessDeadline(String processDeadline) {
		this.processDeadline = processDeadline;
	}
	

	public String getSelectedTaskId() {
		return selectedTaskId;
	}


	public void setSelectedTaskId(String selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}
	
	public List getVarList() {
		return varList;
	}

	public void setVarList(List<S21Pair> varList) {
		this.varList = varList;
	}
	
	@Override
	public String getPageId() {

		return "ZZWL8020";
	}


	@Override
	public String getPageName() {
		return "Process Instance Management";
	}


	public String getProcInstID() {
		return procInstID;
	}


	public void setProcInstID(String procInstID) {
		this.procInstID = procInstID;
	}


	public ProcessInstance getProcessInstance() {
		return processInstance;
	}


	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}


	public int getActiveNodesSize() {
		return activeNodesSize;
	}


	public void setActiveNodesSize(int activeNodesSize) {
		this.activeNodesSize = activeNodesSize;
	}


	
	
}
