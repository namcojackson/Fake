/*
 * Copyright (c) 2010 Canon USA Inc. All rights reserved.
 * Original Author: Rohit Chandramohan
 * Company: Fujitsu Limited
 * Date: Jan 26, 2010
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jbpm.graph.def.Action;

public class EventActionVO {

	//public static final Map<String, String> TYPE_MAP = new HashMap<String, String>();
	
	/*
	 * Patterns for splitting XML data
	 */
	private static final String XML_START_ELEMENT_PATTERN= "<([A-Za-z0-9]+?)>";
	private static final String XML_DATA_PATTERN = "([A-Za-z0-9.]*)";
	private static final String XML_END_ELEMENT_PATTERN= "</([A-Za-z0-9]+?)>";
	
	

	private String actionConfiguration;

/*	static {
		TYPE_MAP.put("A", "Task");
		TYPE_MAP.put("C", "ProcessState");
		TYPE_MAP.put("D", "Decision");
		TYPE_MAP.put("E", "EndState");
		TYPE_MAP.put("F", "Fork");
		TYPE_MAP.put("J", "Fork");
		TYPE_MAP.put("K", "TaskNode");
		TYPE_MAP.put("N", "Node");
		TYPE_MAP.put("P", "ProcessDefinition");
		TYPE_MAP.put("R", "StartState");
		TYPE_MAP.put("S", "State");
		TYPE_MAP.put("T", "Transition");
		TYPE_MAP.put("U", "SuperState");
	}

	
*/
	
	String eventType;
	
	/**
	   As defined by JBPM 
	  "A" class="org.jbpm.taskmgmt.def.Task"
	  "C" class="org.jbpm.graph.node.ProcessState"
	  "D" class="org.jbpm.graph.node.Decision"
	  "E" class="org.jbpm.graph.node.EndState"
	  "F" class="org.jbpm.graph.node.Fork"
	  "J" class="org.jbpm.graph.node.Join"
	  "K" class="org.jbpm.graph.node.TaskNode"
	  "N" class="org.jbpm.graph.def.Node"
	  "P" class="org.jbpm.graph.def.ProcessDefinition"
	  "R" class="org.jbpm.graph.node.StartState"
	  "S" class="org.jbpm.graph.node.State"
	  "T" class="org.jbpm.graph.def.Transition"
	  "U" class="org.jbpm.graph.def.SuperState"
	 */
	String type;

	Action action;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Essential logic to split String of XML data for display purpose
	 * Converts following data: 
	 * <element0>data0</element0> <element1>data1</element1><element2>data2</element2>
	 * to
	 * element0 = data0
	 * element1 = data1
	 * element2 = data3
	 * 
	 * @return
	 */
	public String getActionConfiguration() {
		this.actionConfiguration = action.getActionDelegation() != null ? action.getActionDelegation().getConfiguration() : null;
		if (actionConfiguration == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		CharSequence inputStr = actionConfiguration;
		// Split and group by end element ignores end elements
		Pattern pattern = Pattern.compile(XML_END_ELEMENT_PATTERN);// </([A-Za-z0-9]+?)>

		String inputs[] = pattern.split(inputStr);
		for (String input : inputs) {
			// new pattern is Start element and text
			String patternStr = XML_START_ELEMENT_PATTERN + XML_DATA_PATTERN;// "<([A-Za-z0-9]+?)>([A-Za-z0-9.]*)";
			pattern = Pattern.compile(patternStr);
			Matcher matcher = pattern.matcher(input);
			boolean matchFound = matcher.find();
			if (matchFound) {
				// 3 matches returned our value will always be at position 1 and
				// 2
				sb.append(matcher.group(1));
				sb.append(" = ");
				sb.append(matcher.group(2));
				sb.append("<br />");
			}
		}
		return sb.toString();
	}

/*
 * public void setActionConfiguration(String actionConfiguration) {
 * this.actionConfiguration = actionConfiguration; }
 */

}
