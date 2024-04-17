/*
 * Copyright (c) 2010 Canon USA Inc. All rights reserved.
 * Original Author: Rohit Chandramohan
 * Company: Fujitsu Limited
 * Date: Jan 29, 2010
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo;

import com.canon.cusa.s21.framework.workflow.core.model.WfProcessExtendedAttribute;

import org.jbpm.graph.def.ProcessDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object to define ProcessDefinition  Detail view
 * @author Q03117
 *
 */
public class ProcessDefDetailsVO {

	public WfProcessExtendedAttribute procExtAttr;

	public List<EventActionVO> eventActions;

	public int eventActionCount;

	ProcessDefinition procDef;

	public List<EventActionVO> getEventActions() {
		return eventActions;
	}

	public void setEventActions(List<EventActionVO> eventActions) {
		this.eventActions = eventActions;
	}

	public void addEventAction(EventActionVO eventActionVO) {
		if (eventActions == null) {
			eventActions = new ArrayList<EventActionVO>();
		}
		eventActions.add(eventActionVO);
	}

	public int getEventActionCount() {
		if (eventActions != null) {
			this.eventActionCount = eventActions.size();
		}
		return eventActionCount;
	}

	public WfProcessExtendedAttribute getProcExtAttr() {
		return procExtAttr;
	}

	public void setProcExtAttr(WfProcessExtendedAttribute procExtAttr) {
		this.procExtAttr = procExtAttr;
	}

	public ProcessDefinition getProcDef() {
		return procDef;
	}

	public void setProcDef(ProcessDefinition procDef) {
		this.procDef = procDef;
	}

}
