package oracle.apps.e008.server;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import canon.apps.common.CanonEmailUtil;
import canon.apps.common.CanonCustomProfile;
import org.apache.commons.mail.HtmlEmail;

public class CanonE008CheckandNotify {

	public static final String NOTIFY_PRICING_TEAM= "{call CANON_E008_ITEM_WORKBENCH_PKG.VALIDATE_ITEM_CREATION(?,?,?)}";
	public static final String GET_ROLE_EMAIL= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_ROLE_EMAIL(?,?,?)}"; 
	public static final String ERROR_PROJECT_DETAILS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROJ_ERRORS(?,?,?)}";
	
	
   
    
    public static Object first(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 1 ? null : objs[0];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 1 ? null : l.get(0);
        }
        return null;
    }

    public static Object second(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 2 ? null : objs[1];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 2 ? null : l.get(1);
        }
        return null;
    }

    
    public static void main(String args[]) throws Exception {
    	String orderNumber = ""; 
    	String toDate="";
    	String fromDate="";
    	String errproj ="";
    	String notifyRole="";
    	List emailList;
    	List emailListreq;
    	String inputParam1=System.getenv("VAR_INPUT_PARAM1");
    	if(inputParam1==null || inputParam1.trim().length()==0)
    	inputParam1="No Value Provided";
    	else
    		orderNumber=inputParam1;  

  	
    	System.out.println("OrderNumber:"+orderNumber);
    	//CanonE008CheckandNotify canonE008WB=new CanonE008CheckandNotify();
    	List approvaStatusList=(List)CanonE008CheckandNotify.first(getValidPricingEvent(orderNumber));
    	System.out.println("after Executed "+orderNumber);
    	System.out.println("CanonE008CheckandNotify Start.");
    	for(int i=0;i<approvaStatusList.size();i++){
    		CanonE008CheckandNotify.ApprovalStatusInfo status=(CanonE008CheckandNotify.ApprovalStatusInfo)approvaStatusList.get(i);

            HtmlEmail email = CanonEmailUtil.createHtmlEmail();
            String emailSubject="";
            String emailBody="";
            String emailSubInit ="" ;
			try
	        {
				emailSubInit = CanonCustomProfile.getSystemProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT");
				System.out.println("CanonE008ItemWorkbenchProcess: REJECT emailSubInit " + emailSubInit);
				if(emailSubInit.equals("null"))
					emailSubInit = "";
	        }
	        catch(NullPointerException e)
	        {
	            System.out.print("CanonE008ItemWorkbenchProcess: REJECT emailSubInit NullPointerException");
	            emailSubInit = "";
	        }
  	   	  
    		System.out.println("inside loop Executed projectid"+status.getProjectId());
    		System.out.println("inside loop Executed attribute4 "+status.processflag);

    		notifyRole=status.getNotifyrole();
    		
			emailList = (List) CanonE008CheckandNotify.first(getRoleEmailAddress(notifyRole));
			String[] TO_PRC_ADDRESS = new String[emailList.size()];
			for (int k = 0; k < emailList.size(); k++) {  
	
				TO_PRC_ADDRESS[k]=(String)emailList.get(k);
				System.out.println("Email Address "+TO_PRC_ADDRESS[k]);	
			}  
			
			Map <String,String[]> itemEmailMap=new HashMap<String,String[]>();
			itemEmailMap.put(notifyRole, TO_PRC_ADDRESS);
			/*******************************/	
			emailListreq = (List) CanonE008CheckandNotify.first(getRoleEmailAddress(""+status.getProjectId()));
			String[] TO_REQ_ADDRESS = new String[emailListreq.size()];
			for (int k = 0; k < emailListreq.size(); k++) {  
	
				TO_REQ_ADDRESS[k]=(String)emailListreq.get(k);
				System.out.println("Email req Address "+TO_REQ_ADDRESS[k]);	
			}  
			
			itemEmailMap.put("E008_REQ", TO_REQ_ADDRESS);
			/********************************/
			
            if(status.getProcessflag().equals("S"))
            {		
	         	//emailSubject=emailSubInit + "Pricing Update Required for Project - "+ status.getProjectId();
	         	emailSubject=emailSubInit + "Item(s) Have Created in S21 for Project - "+ status.getProjectId(); // Changed for incident#51764
	         	emailBody=""; //"The Project is Rejected with the following comments:"+p_comments;
            }
            errproj ="";
            if(status.getProcessflag().equals("F"))
            {
	         	emailSubject=emailSubInit + "Upload Item Failed for the Project - "+ status.getProjectId();
	         	emailBody=""; //"The Project is Rejected with the following comments:"+p_comments;
	         	
	         	errproj ="<table bgcolor=\"white\" width=\"75%\" border=\"1\" cellpadding=\"3\" cellspacing=\"1\">"
						+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
						+"<b>Item Number </b></td><td align=\"left\">"
						+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
						+"<b>Item Description </b></td><td align=\"left\">"
						+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
						+"<b>Error Details</b></td></tr>";
						
	         	List projectErrorList=(List)CanonE008CheckandNotify.first(getErrorProjectDetails(status.getProjectId().toString()));
	         	for(int k=0;k<projectErrorList.size();k++){
	        		CanonE008CheckandNotify.ProjectErrorInfo errorProj =(CanonE008CheckandNotify.ProjectErrorInfo)projectErrorList.get(k);
	        		
	        		errproj = errproj + "<tr><td>"+errorProj.getItemId()+"</td><td>"+errorProj.getItemDesc()+"</td><td>"+errorProj.getProcessmessage()+"</td></tr>";
	         	}
	         	
	         	errproj = errproj + "</table>";
            }
            
            
            System.out.println("inside loop Executed emailSubject "+emailSubject);
            
	        String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"1\" cellpadding=\"3\" cellspacing=\"1\">"
							+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Number </b></td><td align=\"left\">"
							+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Description </b></td><td align=\"left\">"
							+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Submitter</b></td><td align=\"left\">"
							+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Notes </b></td></tr>"
							+"<tr><td>"+status.getProjectId()+"</td><td>"+CanonE008CheckandNotify.nonNullify(status.getProjectDesc())+"</td><td>"+status.getProjectSubmitter()+"</td><td>"+CanonE008CheckandNotify.nonNullify(status.getProjectNotes())+"</td></tr></table>"
	     					+"Please review and take appropriate action on the Project: <a href=" + "http://s21csa.cusa.canon.com/s21extn"+"/e008/jsp/canonE008Project.jsp?project_number="+status.getProjectId()
	 						//+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e590/jsp/CanonE590ItemSetupSearch.jsp"
	     					+ ">Project Detail</a>";

          	email.setSubject(emailSubject);       
          	email.addTo(itemEmailMap.get(notifyRole));
          	email.addBcc(itemEmailMap.get("E008_REQ"));
          	email.setHtmlMsg(emailBody+"<html>" + cid + errproj + "</html>");
 			email.send();   
 			System.out.println("Email Sent "+status.getProjectId());
            
    	}
    	System.out.println("CanonE008CheckandNotify End.");
    	
    }  

    public static String nonNullify(String inpStr) {

        String opStr = "";

        if (inpStr != null) {
            opStr = inpStr.trim();
        }

        return opStr;


    }
    public static class ApprovalStatusInfo {
    	   private BigDecimal projectId;
    	   private String projectDesc;
    	   private String projectSubmitter;
    	   private String projectNotes;
    	   private String approvalstatus;
    	   private String processflag;
    	   private String notifyrole;

    	    public ApprovalStatusInfo(){
    	    }
    	    public ApprovalStatusInfo(BigDecimal projectId, 
    	            String projectDesc, 
    	            String projectSubmitter, 
    	            String projectNotes, 
    	            String approvalstatus, 
    	            String processflag,
    	            String notifyrole

    	            ){
    	        this.projectId=projectId;
    	        this.projectDesc=projectDesc;
    	        this.projectSubmitter=projectSubmitter;
    	        this.projectNotes=projectNotes;
    	        this.approvalstatus=approvalstatus;
    	        this.processflag=processflag;
    	        this.notifyrole=notifyrole;
    	    }
    	    public BigDecimal getProjectId() {
    	        return projectId;
    	    }
    	    public void setProjectId(BigDecimal projectId) {
    	        this.projectId=projectId;
    	    }
    	    public String getProjectDesc() {
    	        return projectDesc;
    	    }
    	    public void setProjectDesc(String projectDesc) {
    	        this.projectDesc=projectDesc;
    	    }
    	    public String getProjectSubmitter() {
    	        return projectSubmitter;
    	    }
    	    public void setProjectSubmitter(String projectSubmitter) {
    	        this.projectSubmitter=projectSubmitter;
    	    }
    	    public String getProjectNotes() {
    	        return projectNotes;
    	    }
    	    public void setProjectNotes(String projectNotes) {
    	        this.projectNotes=projectNotes;
    	    }

    	    public String getApprovalstatus() {
				return approvalstatus;
			}
			public void setApprovalstatus(String approvalstatus) {
				this.approvalstatus = approvalstatus;
			}
			public String getProcessflag() {
				return processflag;
			}
			public void setProcessflag(String processflag) {
				this.processflag = processflag;
			}
			
			public String getNotifyrole() {
				return notifyrole;
			}
			public void setNotifyrole(String notifyrole) {
				this.notifyrole = notifyrole;
			}
			public static RowMapper getRowMapper(){
    	        return new RowMapper() {
    	            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	                return new ApprovalStatusInfo(
    	                    rs.getBigDecimal("PROJECT_ID"),
    	                    rs.getString("PROJECT_DESC"),
    	                    rs.getString("PROJECT_REQUESTOR"),
    	                    rs.getString("PROJECT_NOTES"),
    	                    rs.getString("APPROVAL_STATUS"),
    	                    rs.getString("PROCESS_FLAG"),
    	                    rs.getString("NOTIFY_ROLE")
    	                );
    	            }
    	        };
    	    }
    	    
    	}

    
    public static class ProjectErrorInfo {
 	   private String itemId;
 	   private String itemDesc;
 	   private String processmessage;


 	    public ProjectErrorInfo(){
 	    }
 	    
 	    public ProjectErrorInfo(String itemId, String itemDesc,
				String processmessage) {
			super();
			this.itemId = itemId;
			this.itemDesc = itemDesc;
			this.processmessage = processmessage;
		}
 	    
 	    
 	    public String getItemId() {
			return itemId;
		}

		public void setItemId(String itemId) {
			this.itemId = itemId;
		}

		public String getItemDesc() {
			return itemDesc;
		}

		public void setItemDesc(String itemDesc) {
			this.itemDesc = itemDesc;
		}

		public String getProcessmessage() {
			return processmessage;
		}

		public void setProcessmessage(String processmessage) {
			this.processmessage = processmessage;
		}

		public static RowMapper getRowMapper(){
 	        return new RowMapper() {
 	            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
 	                return new ProjectErrorInfo(
 	                    rs.getString("ITEM_NUMBER"),
 	                    rs.getString("ITEM_DESCRIPTION"),
 	                    rs.getString("PROCESS_MESSAGE")
 	                );
 	            }
 	        };
 	    }
 	    
 	}
    
   public static interface RowMapper {
        Object mapRow(ResultSet rs, int rowNum) throws SQLException;
    }
    private static List cursorToList(ResultSet cursor, RowMapper rowMapper) {
        List list = new ArrayList();
        try {
            while (cursor.next()) {
                list.add(rowMapper.mapRow(cursor, 0)); 
            }
        } catch (SQLException ex) {
            //saveException(ex);
            ex.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    
    
    public static Object[] getValidPricingEvent(String p_project_no){
        //clearException();
        System.out.println("in getValidPricingEvent "+p_project_no);
        CallableStatement statement = null;
        System.out.println("in getValidPricingEvent1 "+p_project_no);
        Connection connection = null;
        System.out.println("in getValidPricingEvent2 "+p_project_no);
        try {
        	System.out.println("in getValidPricingEvent3 "+p_project_no);
        	connection = TransactionScope.getConnection();
        	System.out.println("in getValidPricingEvent4 "+p_project_no);
            if (connection != null) {
            	System.out.println("in getValidPricingEvent5 "+p_project_no);
            	statement = (CallableStatement) connection.prepareCall(NOTIFY_PRICING_TEAM);
            	System.out.println("in getValidPricingEvent6 "+p_project_no);
                if (statement != null) {
                	System.out.println("in Executed "+p_project_no);
                    statement.setObject(1, p_project_no, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("after Executed "+p_project_no);
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ApprovalStatusInfo.getRowMapper())
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            //saveException(ex);
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    TransactionScope.releaseConnection(connection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } 
        return null;
   }  

    public static Object[] getErrorProjectDetails(String p_project_no){
        //clearException();
        System.out.println("in getErrorProjectDetails "+p_project_no);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ERROR_PROJECT_DETAILS);
                if (statement != null) {
                	System.out.println("in Executed "+p_project_no);
                    statement.setObject(1, p_project_no, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("after Executed "+p_project_no);
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ProjectErrorInfo.getRowMapper())
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            //saveException(ex);
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    TransactionScope.releaseConnection(connection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } 
        return null;
   }  

    public static RowMapper stringRowMapper() {
        return new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        };
    }
    
    public static Object[] getRoleEmailAddress(String p_role_id){

    	System.out.println("in getRoleEmailAddress "+p_role_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_ROLE_EMAIL);
                if (statement != null) {
                    statement.setObject(1, p_role_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    //return new String[]{statement.getString(2)};
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())
                 		   				,statement.getObject(3)
                    						};
                    
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    TransactionScope.releaseConnection(connection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
        return null;
   }     // Added by Madhusudan Duna
    
    
}
