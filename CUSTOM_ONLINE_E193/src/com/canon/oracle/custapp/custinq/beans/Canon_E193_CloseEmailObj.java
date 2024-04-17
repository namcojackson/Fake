package com.canon.oracle.custapp.custinq.beans;

public class Canon_E193_CloseEmailObj {
	
		
	private String skipNotificationFlag;
	
	private String emailAddress;
	
	private String subject;
	
	private String comments;

	
	public String getSkipNotificationFlag() {
		return skipNotificationFlag;
	}

	public void setSkipNotificationFlag(String skipNotificationFlag) {
		this.skipNotificationFlag = skipNotificationFlag;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Canon_E193_CloseEmailObj [skipNotificationFlag=" + skipNotificationFlag
				+ ", emailAddress=" + emailAddress + ", subject=" + subject
				+ ", comments=" + comments + "]";
	}
	
	

}
