package com.canon.apps.servreq.beans;

public class CanonE307SRAuditLogRec {

	private String strFSR;
	private String strTask;
	private String strTime;
	private String strUserId;
	private String strUpdate;
	private String strOldValue;
	private String strNewValue;
	
	
	
	public CanonE307SRAuditLogRec(){
	}
	
	
	public CanonE307SRAuditLogRec(String strFSR, String strTask,
			String strTime, String strUserId, String strUpdate,
			String strOldValue, String strNewValue) {
		super();
		this.strFSR = strFSR;
		this.strTask = strTask;
		this.strTime = strTime;
		this.strUserId = strUserId;
		this.strUpdate = strUpdate;
		this.strOldValue = strOldValue;
		this.strNewValue = strNewValue;
	}


	public String getStrFSR() {
		return strFSR;
	}


	public void setStrFSR(String strFSR) {
		this.strFSR = strFSR;
	}


	public String getStrTask() {
		return strTask;
	}


	public void setStrTask(String strTask) {
		this.strTask = strTask;
	}


	public String getStrTime() {
		return strTime;
	}


	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}


	public String getStrUserId() {
		return strUserId;
	}


	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}


	public String getStrUpdate() {
		return strUpdate;
	}


	public void setStrUpdate(String strUpdate) {
		this.strUpdate = strUpdate;
	}


	public String getStrOldValue() {
		return strOldValue;
	}


	public void setStrOldValue(String strOldValue) {
		this.strOldValue = strOldValue;
	}


	public String getStrNewValue() {
		return strNewValue;
	}


	public void setStrNewValue(String strNewValue) {
		this.strNewValue = strNewValue;
	}


	@Override
	public String toString() {
		return "CanonE307SRAuditLogRec [strFSR=" + strFSR + ", strTask="
				+ strTask + ", strTime=" + strTime + ", strUserId=" + strUserId
				+ ", strUpdate=" + strUpdate + ", strOldValue=" + strOldValue
				+ ", strNewValue=" + strNewValue + "]";
	}


}
