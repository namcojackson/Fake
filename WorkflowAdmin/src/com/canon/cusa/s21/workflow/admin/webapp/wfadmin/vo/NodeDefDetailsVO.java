/*
 * Copyright (c) 2010 Canon USA Inc. All rights reserved.
 * Original Author: Rohit Chandramohan
 * Company: Fujitsu Limited
 * Date: Jan 22, 2010
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.graph.def.Node;

import com.canon.cusa.s21.framework.workflow.core.model.WfNodeExtendedAttribute;

public class NodeDefDetailsVO {

	public WfNodeExtendedAttribute nodeExtAttr;

	public List<EventActionVO> eventActions;
	
	
	public int eventActionCount;

	Node node;

	public WfNodeExtendedAttribute getNodeExtAttr() {
		return nodeExtAttr;
	}

	public void setNodeExtAttr(WfNodeExtendedAttribute nodeExtAttr) {
		this.nodeExtAttr = nodeExtAttr;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

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



}
