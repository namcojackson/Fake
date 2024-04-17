package canon.salesforce.common;

public interface CanonSalesForceConstants {
  public static final String SALESFORCE_PROP = "SALESFORCE_PROP.properties";

  public static final String CBS_SALESFORCE_USERNAME = "CBS_SALESFORCE_USERNAME";

  public static final String CBS_SALESFORCE_PASSWORD = "CBS_SALESFORCE_PASSWORD";

  public static final String CBS_SERVER_PROD = "CBS_SERVER_PROD";

  public static final String CBS_DB_SID = "CBS_DB_SID";

  public static final String CBS_PROXY_SERVER  =  "CBS_PROXY_SERVER";

  public static final String CBS_PROXY_PORT =  "CBS_PROXY_PORT";

  //public static final String CBS_SALESFORCE_PERIOD  =  "2007-12-31";

  public static final int CBS_BATCH_SIZE = 201;

 // public static final String CBS_SYS_ADMIN_SF_PROFILE = "System Administrator";

  public static final String CBS_ORG_NAME = "CBS";

 // public static final String CBS_RECORD_TYPE_ACCT = "Customer";

  //public static final String CBS_RECORD_TYPE_PROS = "Prospect";

  //public static final String CBS_SOBJECT_TYPE_ACCT = "Account";

  //public static final String CBS_RECORD_TYPE_CNCT = "Oracle Source";

 // public static final String CBS_SOBJECT_TYPE_CNCT = "Contact";

  //public static final String CBS_RECORD_TYPE      =  "CBS";

 // public static final String CBS_SOBJECT_TYPE_IB  =  "CBS_IB__c";
  
  //public static final String CBS_SOBJECT_TYPE_3RDPARTY  =  "THIRD_PARTY_LEASE__c"; //ITG#497373
  
  //public static final String CBS_SOBJECT_TYPE_OWNATCHANGE  =  "CSA_ACCOUNT_OWNER_AT_CHANGE__c"; 
  
  //public static final String CBS_SOBJECT_TYPE_CONTDTL  =  "CSA_CONTRACT_DETAIL__c"; 

//Modified by dipti. when checking for max count we are subtracting 1 from the number hence it is 2
  //public static final int CBS_SF_MAX_BATCHCOUNT = 5;
 public static final int CBS_SF_MAX_BATCHCOUNT = 2;

  public static final int CBS_BATCH_WAIT_TIME = 30000;

  //public static final String CBS_LEASE_EXP_BATCH_CLSS_NAME = "CBSLeaseExpiryBatch";

  //public static final String CBS_TASK_REASSGN_BATCH_CLSS_NAME = "CBSTaskOwnerReassignmentBatch";

  //public static final String CBS_OPPT_REASSGN_BATCH_CLSS_NAME = "CBSOpportunityOwnerReassignmentBatch";

  //public static final String CBS_EVNT_REASSGN_BATCH_CLSS_NAME = "CBSEventOwnerReassignmentBatch";

  //public static final String CBS_CNCT_REASSGN_BATCH_CLSS_NAME = "CBSContactOwnerReassignmentBatch";

 // public static final String CBS_ACCT_STS_BATCH_CLSS_NAME = "CBSAccountStatusBatch";

  //public static final String CBS_ACCT_LEAS_BATCH_CLSS_NAME = "CBSAccountLeaseBatch";

  //public static final String CBS_INACT_PRDCT_BATCH_CLSS_NAME = "CBSInactiveProductBatch";
  
  //public static final String CBS_ACCT_TEAM_VISIBILITY_BATCH_CLSS_NAME = "CBSAcctTeamVisibilityBatch"; //ITG#446855
  
  //public static final String CSA_LEAD_DEV_IDEN_BATCH_CLSS_NAME   = "CSAIdentifyRecipientsNewLeadBatch";
  
  //public static final String CSA_LEAD_NOACT_IDEN_BATCH_CLSS_NAME = "CSAIdentifyRecipientsNewLeadNoActionBatc";

  //public static final String CSA_LEAD_DEV_NOTI_BATCH_CLSS_NAME   = "CSANotifyRecipientsNewLeadBatch";
  
  //public static final String CSA_LEAD_NOACT_NOTI_BATCH_CLSS_NAME = "CSANotifyRecipientsNewLeadNoActionBatch";
  
  //public static final String CSA_IDEN_PROF_SVCS_OPP_SHARES_BATCH_CLSS_NAME = "CSAIdentifyProfSvcsOppSharesBatch";
  
  //public static final String CSA_NTFY_PROF_SVCS_OPP_SHARES_BATCH_CLSS_NAME = "CSANotifyProfSvcsOppSharesBatch";
  
  //public static final String CBS_SALESFORCE_ADMIN_USERNAME = "CBS_SALESFORCE_ADMIN_USERNAME";

  //public static final String CBS_SALESFORCE_ADMIN_PASSWORD = "CBS_SALESFORCE_ADMIN_PASSWORD";
  
  //ITG#536679
  //public static final String CSA_REDO_MASS_ASSGN_ACCT_SHARE_BATCH_CLASS_NAME = "CSARedoMassAssgnAcctShareBatch";
  
  /**
   * SFDC Conversion Changes
   */
 // public static final String OCE_SALESFORCE_USERNAME = "OCE_SALESFORCE_USERNAME";

  //public static final String OCE_SALESFORCE_PASSWORD = "OCE_SALESFORCE_PASSWORD";
  
 // public static final String LFSPPS_SALESFORCE_PROP = "LFSPPS_SALESFORCE.properties";
	
	public static final String LFS_SALESFORCE_USERNAME = "LFS_SALESFORCE_USERNAME";

	public static final String LFS_SALESFORCE_PASSWORD = "LFS_SALESFORCE_PASSWORD";

	public static final String LFS_SERVER_PROD = "LFS_SERVER_PROD";

	public static final String LFS_DB_SID = "LFS_DB_SID";

	public static final String LFS_PROXY_SERVER  =  "LFS_PROXY_SERVER";

	public static final String LFS_PROXY_PORT  =  "LFS_PROXY_PORT";
	
	public static final String PPS_SALESFORCE_USERNAME = "PPS_SALESFORCE_USERNAME";

	public static final String PPS_SALESFORCE_PASSWORD = "PPS_SALESFORCE_PASSWORD";

	public static final String PPS_SERVER_PROD = "PPS_SERVER_PROD";

	public static final String PPS_DB_SID = "PPS_DB_SID";

	public static final String PPS_PROXY_SERVER  =  "PPS_PROXY_SERVER";

	public static final String PPS_PROXY_PORT  =  "PPS_PROXY_PORT";
}
