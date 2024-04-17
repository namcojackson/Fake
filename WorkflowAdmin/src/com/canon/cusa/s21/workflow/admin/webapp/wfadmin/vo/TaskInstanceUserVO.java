/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Rohit Chandramohan
 * Company: Fujitsu Limited
 * Date: Dec 14, 2009
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.vo;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.workflow.core.model.S21WfWorkItem;

/**
 * Value object used TaskInstanceUserSearchPage
 * 
 * @author Q03117
 * 
 */
public class TaskInstanceUserVO {

	S21WfWorkItem wfWorkItem;

	List<String> userList;
	
	String userAsStr  = "";

	/**
	 * Build a userList are assigned to the task instance
	 * 
	 * @param user
	 */
	public void addUsers(List<String> users) {
		if (userList == null) {
			userList = new ArrayList<String>();
		}
		if (users != null) {
			userList.addAll(users);
		}
	}
	
	/**
	 * Build a userList are assigned to the task instance
	 * 
	 * @param user
	 */
	public void addUser(String user) {
		if (userList == null) {
			userList = new ArrayList<String>();
		}
		userList.add(user);
	}	

	/**
	 * Return comma separated users associated with a taskInstance
	 * 
	 * @return
	 */
	public String getUserAsStr() {
		userAsStr =  S21StringUtil.arrayToStrList(userList);
		return userAsStr;
	}

	public S21WfWorkItem getWfWorkItem() {
		return wfWorkItem;
	}

	public void setWfWorkItem(S21WfWorkItem wfWorkItem) {
		this.wfWorkItem = wfWorkItem;
	}

}
