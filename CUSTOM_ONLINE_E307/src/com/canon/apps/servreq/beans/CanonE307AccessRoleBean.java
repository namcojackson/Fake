package com.canon.apps.servreq.beans;

public class CanonE307AccessRoleBean {

	private String hasTechRl;
	private String hasSrvcMngrRl;
	private String hasReadOnly;
	
	public CanonE307AccessRoleBean() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307AccessRoleBean(String hasTechRl, String hasSrvcMngrRl,
			String hasReadOnly) {
		super();
		this.hasTechRl = hasTechRl;
		this.hasSrvcMngrRl = hasSrvcMngrRl;
		this.hasReadOnly = hasReadOnly;
	}

	public String getHasTechRl() {
		return hasTechRl;
	}

	public void setHasTechRl(String hasTechRl) {
		this.hasTechRl = hasTechRl;
	}

	public String getHasSrvcMngrRl() {
		return hasSrvcMngrRl;
	}

	public void setHasSrvcMngrRl(String hasSrvcMngrRl) {
		this.hasSrvcMngrRl = hasSrvcMngrRl;
	}

	public String getHasReadOnly() {
		return hasReadOnly;
	}

	public void setHasReadOnly(String hasReadOnly) {
		this.hasReadOnly = hasReadOnly;
	}


	
}
