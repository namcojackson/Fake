package oracle.apps.custombilling.beans;

public class CanonCustomBillingSearchingBean {
	private String headerId;
	private String profileId;
	private String profileName;
	private String partyName;
	private String billToUse;
	private String otherEmail;
	private String customerEmail;
    private String statusFlag;
    private String parentCustomerName;
    private String templateLevel;
    private String groupName;

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }
	
	public String getHeaderId(){
		return headerId;
	}
	public void setHeaderId(String headerId){
		this.headerId =headerId;
	}
	public String getProfileId(){
		return profileId;
	}
	public void setProfileId(String profileId){
		this.profileId = profileId;
	}
	public String getProfileName(){
		return profileName;
	}
	public void setProfileName(String profileName){
		this.profileName = profileName;
	}
	public String getCustomerName(){
		return partyName;
	}
	public void setPartyName(String partyName){
		this.partyName = partyName;
	}
	public String getBillToUse(){
		return billToUse;
	}
	public void setBillToUse(String billToUse){
		this.billToUse = billToUse;
	}
	public String getOtherEmail(){
		return otherEmail;
	}
	public void setOtherEmail(String otherEmail){
		this.otherEmail = otherEmail;
	}
	public String getCustomerEmail(){
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail){
		this.customerEmail = customerEmail;
	}
	public String getParentCustomerName() {
        return parentCustomerName;
    }
    public void setParentCustomerName(String parentCustomerName) {
        this.parentCustomerName=parentCustomerName;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName=groupName;
    }
    public String getTemplateLevel() {
        return templateLevel;
    }
    public void setTemplateLevel(String templateLevel) {
        this.templateLevel=templateLevel;
    }
	
}
