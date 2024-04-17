package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.definition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.Event;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.instantiation.Delegation;

import com.canon.cusa.s21.framework.common.collections.S21LazyListFactory;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfProcessInstanceExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.WfNodeExtendedAttribute;
import com.canon.cusa.s21.framework.workflow.core.model.WfProcessExtendedAttribute;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo.EventActionVO;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo.NodeDefDetailsVO;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo.ProcessDefDetailsVO;

public class ProcessDefinitionDetailsPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(ProcessDefinitionDetailsPage.class);

	//***************************************************
	// Process definition inquiry area
	//***************************************************
	private Long procDefId;
	
	private List<NodeDefDetailsVO> nodeDefDetailsVOs;
	
	private ProcessDefDetailsVO proceDefDetailsVO;

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
	public ProcessDefinitionDetailsPage() {
		//procExtAttrs = new S21LazyList(new ArrayList(), new WfProcessExtendedAttributeDataFactory());
	}	

	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
		// super.prepareRequiredProperties(pageTransitionContext);
		Map prepareArg = pageTransitionContext.getPrepareArgs();

		ProcessDefinition procDef = wfProcessDefinitionService.getEagerLoadProcessDefinitionById(procDefId);
		//ProcessDefinition procDef = wfProcessDefinitionService.getProcessDefinitionById(procDefId);
		WfProcessExtendedAttribute procExtAttr = wfProcessDefinitionService.getProcessExtendedAttributeForProcessDefId(procDefId);
		List<Node> nodes = procDef.getNodes();
		
		nodeDefDetailsVOs = new ArrayList<NodeDefDetailsVO>();
		EventActionVO eventActionVO = null;
		
    	ProcessDefDetailsVO proceDefDetailsVO = new ProcessDefDetailsVO();
    	proceDefDetailsVO.setProcDef(procDef);
    	proceDefDetailsVO.setProcExtAttr(procExtAttr);
    	setProceDefDetailsVO(proceDefDetailsVO);

        Map processEventMap = procDef.getEvents();
        Iterator processEventMapIterator = processEventMap.entrySet().iterator();
        while(processEventMapIterator.hasNext()){
        	Entry e = (Entry)processEventMapIterator.next();
        	// System.out.println(e.getKey());
            Event event = (Event)e.getValue();
			List actionList = event.getActions();
			Iterator listIterator = actionList.iterator();

			while (listIterator.hasNext()) {
				Action action = (Action) listIterator.next();
				if (action != null) {
					eventActionVO = new EventActionVO();
					eventActionVO.setEventType(event.getEventType());
					eventActionVO.setAction(action);
					proceDefDetailsVO.addEventAction(eventActionVO);
					
				}

			}
			;
        	
        }
		
		
		for (Node node : nodes) {

			if (node != null) {
				NodeDefDetailsVO nodeDefDetailsVO = new NodeDefDetailsVO();
				WfNodeExtendedAttribute nodeExtAttr = wfProcessDefinitionService.getNodeExtendedAttributeByNodeId(node.getId());

				nodeDefDetailsVO.setNode(node);
				nodeDefDetailsVO.setNodeExtAttr(nodeExtAttr);

				// Flatten all actions and set nodeDefDetailsVO
				// Eager fetch all the nodes
				Map eventMap = null;

				// Get all leaving transitions
				Iterator leavingTransitionIterator = node.getLeavingTransitions().iterator();
				while (leavingTransitionIterator.hasNext()) {
					Transition transition = (Transition) leavingTransitionIterator.next();
					// Get all Transition Events
					eventMap = transition.getEvents();
					Iterator eventMapiterator = eventMap.entrySet().iterator();
					while (eventMapiterator.hasNext()) {
						Entry e = (Entry) eventMapiterator.next();
						logger.error(e.getKey());
						Event event = (Event) e.getValue();
						List actionList = event.getActions();
						Iterator listIterator = actionList.iterator();

						while (listIterator.hasNext()) {
							Action action = (Action) listIterator.next();
							if (action != null) {
								eventActionVO = new EventActionVO();
								eventActionVO.setEventType(event.getEventType());
								eventActionVO.setAction(action);
								nodeDefDetailsVO.addEventAction(eventActionVO);
								//logger.error("Leaving Transition" + action.getActionDelegation().getClassName());
								//logger.error(action.getActionDelegation().getConfiguration() + "/n/n");
							}

						}
						;
					}
				}

				// Get all Node events
				eventMap = node.getEvents();
				Iterator eventMapiterator = eventMap.entrySet().iterator();
				while (eventMapiterator.hasNext()) {
					Entry e = (Entry) eventMapiterator.next();
					logger.error(e.getKey());
					Event event = (Event) e.getValue();
					List actionList = event.getActions();
					Iterator listIterator = actionList.iterator();

					while (listIterator.hasNext()) {
						Action action = (Action) listIterator.next();
						if (action != null) {
							eventActionVO = new EventActionVO();
							eventActionVO.setEventType(event.getEventType());
							eventActionVO.setAction(action);
							nodeDefDetailsVO.addEventAction(eventActionVO);
							//logger.error("Node Transition" + action.getActionDelegation().getClassName());
							//logger.error(action.getActionDelegation().getConfiguration() + "/n/n");
							

						}

					}
					;
				}

				nodeDefDetailsVOs.add(nodeDefDetailsVO);
			}

		}
		
		// List procDefId = new ArrayList<Long>();
		// procDefId.add(procDefId);
		// List procDefinitions =
		// JbpmUtil.getCurrentJbpmContext().getGraphSession().findProcessDefinitions(procDefId);

		// WfNodeExtendedAttribute nodeExtAttr =
		// wfProcessDefinitionService.getNodeEx();

		// ProcessDefinition testProcDef =
		// wfProcessDefinitionService.getProcessDefinitionById(procDefId);
		// wfProcessDefinitionService.getFirstTaskCodeForProcessCode(processCode)
		// procDef.get
		/*
		 * Map nodeMap = procDef.getNodesMap(); Iterator i =
		 * nodeMap.entrySet().iterator(); //Populate the VO while(i.hasNext()){
		 * Entry e = (Entry)i.next(); Node node = (Node)e.getValue();
		 * NodeDefDetailsVO nodeDefDetailsVO = new NodeDefDetailsVO();
		 * nodeDefDetailsVO.setNode(node); WfNodeExtendedAttribute nodeExtAttr =
		 * wfProcessDefinitionService.getNodeExtendedAttributeByNodeId(node.getId());
		 * nodeDefDetailsVO.setNodeExtAttr(nodeExtAttr); //String[]
		 * supportedEvents = List nodeEventList = new ArrayList(); for(String
		 * supportedEvent :node.getSupportedEventTypes()){ Event event =
		 * node.getEvent(supportedEvent); };
		 * 
		 * Map eventMap = node.getEvents(); Iterator inode =
		 * eventMap.entrySet().iterator(); while(inode.hasNext()){
		 * 
		 * Entry enode = (Entry)inode.next();
		 * 
		 * Event event = (Event)enode.getValue(); List actionList =
		 * event.getActions(); Iterator j = actionList.iterator();
		 * while(j.hasNext()){
		 * 
		 * Action action = (Action)j.next(); Delegation delegation =
		 * action.getActionDelegation();
		 * 
		 * //action.getActionDelegation().getConfigType() } }
		 */

	}
	
	public WfSwebDestinationPage showPage() throws Exception {
		return activateDestinationPage(getClass());
	}

	
	public WfSwebDestinationPage executeAction() throws Exception {
		/*if ("Show".equals(getAction())) {
			return showProcessDefinition();
		}else if("Show Tasks".equals(getAction())){
			return showTasks();
		}*/
		return showPage();
	}


    @Override
	public String getPageId() {
		return "ZZWL8090";
	}

	@Override
	public String getPageName() {
		return "Process Definition Details";
	}

	public Long getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(Long procDefId) {
		this.procDefId = procDefId;
	}

	public List<NodeDefDetailsVO> getNodeDefDetailsVOs() {
		return nodeDefDetailsVOs;
	}

	public void setNodeDefDetailsVOs(List<NodeDefDetailsVO> nodeDefDetailsVOs) {
		this.nodeDefDetailsVOs = nodeDefDetailsVOs;
	}

	public ProcessDefDetailsVO getProceDefDetailsVO() {
		return proceDefDetailsVO;
	}

	public void setProceDefDetailsVO(ProcessDefDetailsVO proceDefDetailsVO) {
		this.proceDefDetailsVO = proceDefDetailsVO;
	}
	
}
