<%@page	import="com.canon.cusa.s21.framework.security.authentication.details.S21IdentityDetails"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Date"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page	import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%@page errorPage="canonE008ErrorPage.jsp"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="oracle.apps.jtf.base.session.ServletSessionManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext"%>
<%@page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder"%>
<%@page language="java" import="com.canon.cusa.s21.framework.security.S21Authentication"%>
<%@page import="canon.apps.common.CanonEmailUtil"%>
<%@page import="org.apache.commons.mail.HtmlEmail"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="oracle.apps.e008.item.process.CanonE008TemplateDAO"%>

<%@page import="java.net.URLEncoder"%>

<%!    
    final static int ALL_ROWS = Integer.MAX_VALUE;
    final BigDecimal UPLOAD_ITEMS=new BigDecimal(-1);
    static final boolean IS_DEV=CanonE008ItemProcessHelper.isDevEnviornment();
    boolean canModifyProject=false;
    boolean canDeleteProject=false;
    boolean canSubmitForApproval=false;
    boolean canViewProject=false;
    boolean canCreateProject=false;
    boolean canValidateRejectProject=false;
    boolean canValidateCloseProject=false; 
    boolean canViewStatusMonitor=false;
    List requiredFields=null;
    List ownershipFields=null;
    static final String REQUIRED="Required";
    static final String VALIDATED="Validated";
    static final String PENDING_APPROVAL="PENDING APPROVAL";
    static final String OWNERSHIP="Y";
    static final String REQUIRED_CLASS="required";
    static final String OWNER_CLASS="owner";
    static final int COL_ITEM_TYPE=0;
    static final int COL_TEMPLATE=1;
    static final int COL_MERCH_TYPE=2;
    static final int COL_PROD_CODE=3;
    static final int COL_THIRD_PARTY=4;
    
    String p_approvers_comments;
    List canonTemplateList;
    
    List emailList;

 	String newstr="";
 	String recordstr="";
    
    static final List INITIAL_REQUIRED=Arrays.asList(new String[]{
        "ITEM_CODE",
        "ITEM_DESCRIPTION",
        "CANON_TEMPLATE",
        "ITEM_TYPE",
        "TEMPLATE",
        "MERCH_TYPE",
        "PROD_CODE",
        "COST",
        "MSRP"
    });
    
    Hashtable lovs=new Hashtable();
    
    static String [] parameterValues(HttpServletRequest request,String name){
        String param_prefix=CanonE008ItemProcessUtil.nonNullify(request.getParameter("param_prefix"));
        return request.getParameterValues(param_prefix+name);
    }

    static String nonNullify(Object o){
        if(o==null){
            return "";
        }else if(o instanceof String){
            return ((String)o).trim();
        }else {
            return o.toString();
        }
    }
    
    static String planValue(String[] plan,int idx){
        return CanonPCISecurityUtil.htmlEncode(nonNullify(plan[idx]));
    }
    
    static boolean isEmpty(String s){
        return CanonE008ItemProcessUtil.isEmpty(s);
    }
    
    static class ItemProcessException extends Exception{
        public ItemProcessException(String message, Throwable cause){
            super(message,cause);
        }
    }

    static void checkResult(Object [] result) throws ItemProcessException{
        if(result==null) {
            Exception cause=CanonE008ItemProcessDAO.getException();
            System.out.println("cause "+cause);
            throw new ItemProcessException("Database exception.",cause);
        }
    }
    
 
   //String required(BigDecimal itemId,String fieldName){
   String required(BigDecimal templateId,String fieldName){ 
	   //if(canModifyProject && canValidateRejectProject){ Commented by Madhusudan
        if(canModifyProject){
        	//System.out.println("templateId "+templateId);
        	//System.out.println("fieldName "+fieldName);
        	
        	Object result[] = CanonE008ItemProcessDAO.validateRequiredFields(new BigDecimal("0"), templateId);
            //checkErrors(result, 1,2);
            requiredFields=CanonE008ItemProcessUtil.first(result)!=null ? (List)CanonE008ItemProcessUtil.first(result) : Collections.EMPTY_LIST;
            //System.out.println("requiredFields.size() "+requiredFields.size());
        	for(int i=0;requiredFields!=null && i<requiredFields.size();i++){
                CanonE008ItemProcessDAO.ProjectReqValidateInfo rule=(CanonE008ItemProcessDAO.ProjectReqValidateInfo)requiredFields.get(i);
                
                if(templateId.equals(rule.getTemplateId()) && fieldName.equals(rule.getFieldName()))
                {
                	//System.out.println("getFieldName "+rule.getFieldName());
                	//System.out.println("getMessage "+rule.getMessage());
                	//return REQUIRED.equals(rule.getMessage()) || VALIDATED.equals(rule.getMessage()) ? "required" :"required";
                	return "REQUIRED".equals(rule.getMessage().trim()) ? "required" :"";
                }
            }
        }
        return "";
    }

    static void checkErrors(Object [] result,int error_flag_index,int error_message_index) throws ItemProcessException{
        checkResult(result);
        String error_flag=(String)result[error_flag_index];
        String error_message=(String)result[error_message_index];
        System.out.println("error_flag - "+ error_flag);
        System.out.println("error_message - "+ error_message);
        
        if( "E".equals(error_flag)){
            throw new ItemProcessException("Database error.", new Exception(error_message));
        }
    }
    

   /*  String owner(BigDecimal itemId,String fieldName){
        for(int i=0;ownershipFields!=null && i<ownershipFields.size();i++){
            CanonE008ItemProcessDAO.ProjectValidateInfo rule=(CanonE008ItemProcessDAO.ProjectValidateInfo)ownershipFields.get(i);
            if(itemId.equals(rule.getItemId()) && fieldName.equals(rule.getFieldName()))
            {
                return OWNERSHIP.equals(rule.getMessage()) ? OWNER_CLASS :"";
            }
        }
        return "";
    } */

    /* String required_and_owner(BigDecimal itemId,String fieldName){
        String r=required(itemId,fieldName);
        return CanonE008ItemProcessUtil.isEmpty(r)? owner(itemId,fieldName) : r+" "+owner(itemId,fieldName);
    } */
    
    String initial_required(String fieldName){
        return INITIAL_REQUIRED.indexOf(fieldName)>=0 ? "initial_required required " : "";
    }
    
    static boolean canModifyProject(BigDecimal projectID,String roleId,String userID) throws ItemProcessException{
        Object result[]=CanonE008ItemProcessDAO.checkModifyProject(projectID,roleId,userID);
        checkResult(result);
        return "Y".equals(CanonE008ItemProcessUtil.first(result));
    }
    static boolean canDeleteProject(BigDecimal projectID,String roleId,String userID) throws ItemProcessException{
        Object result[]=CanonE008ItemProcessDAO.checkDeleteProject(projectID,roleId,userID);
        checkResult(result);
        return "Y".equals(CanonE008ItemProcessUtil.first(result));
    }
    static boolean canViewProject(BigDecimal projectID,String roleId,String userID) throws ItemProcessException{
        Object result[]=CanonE008ItemProcessDAO.checkViewProject(projectID,roleId,userID);
        checkResult(result);
        return "Y".equals(CanonE008ItemProcessUtil.first(result));
    }
    static boolean canCreateProject(BigDecimal projectID,String roleId,String userID) throws ItemProcessException{
        Object result[]=CanonE008ItemProcessDAO.checkCreateProject(projectID,roleId,userID);
        checkResult(result);
        return "Y".equals(CanonE008ItemProcessUtil.first(result));
    }
    static boolean[] checkSubmitApprovalProject(BigDecimal projectID,String roleId,String userID) throws ItemProcessException{
        Object result[]=CanonE008ItemProcessDAO.checkSubmitApprovalProject(projectID,roleId,userID);
        checkResult(result);
        String str=(String)CanonE008ItemProcessUtil.first(result);
        return new boolean[]{"Y".equals(str)||"B".equals(str),"N".equals(str)||"B".equals(str)};
    }
    static boolean checkValidateRejectProject(BigDecimal projectID,String roleId,String userID)throws ItemProcessException{
        Object result[]=CanonE008ItemProcessDAO.checkValidateRejectProject(projectID,roleId,userID);
        checkResult(result);
        return "Y".equals(CanonE008ItemProcessUtil.first(result));
    }

    public  Object first(Object obj) {
            if (obj instanceof Object[]) {
                Object[] objs = (Object[]) obj;
                return objs.length < 1 ? null : objs[0];
            } else if (obj instanceof List) {
                List l = (List) obj;
                return l.size() < 1 ? null : l.get(0);
            }
            return null;
    }
   
    void addLOV(String name,Object result){
        lovs.put(name,CanonE008ItemProcessUtil.first(result)!=null ? (List) CanonE008ItemProcessUtil.first(result) : Collections.EMPTY_LIST);
    }    

    HashMap<String,ArrayList<String>> attlovs=new HashMap<String,ArrayList<String>>();
    
    void addattributeLOV(String name, Object result) {

		List attributeValueList = (List) CanonE008ItemProcessUtil.first(result);
		ArrayList<String> attval=new ArrayList<String>();
		//System.out.println(" addattributeLOV : " );
		for (int i = 0; i < attributeValueList.size(); i++) {
			CanonE008TemplateDAO.CanonE008lovRec attributeRec = (CanonE008TemplateDAO.CanonE008lovRec) attributeValueList.get(i);			
			//System.out.println(" addattributeLOV attributeRec : " + attributeRec.getListcode()+ " : " + attributeRec.getListname());
			attval.add(attributeRec.getListcode());
			attval.add(attributeRec.getListname());			
		}	
		attlovs.put(name,attval); 
	}    

	static String returndefaultText(Object result){
		List attributeValueList = (List) CanonE008ItemProcessUtil.first(result);
		String attval="";
		System.out.println(" returndefaultText : " );
		System.out.println(" returndefaultText : " );
		for (int i = 0; i < attributeValueList.size(); i++) {
			CanonE008TemplateDAO.CanonE008lovRec attributeRec = (CanonE008TemplateDAO.CanonE008lovRec) attributeValueList.get(i);			
			System.out.println(" returndefaultText attributeRec : " + attributeRec.getListcode()+ " : " + attributeRec.getListname());
			//attval.add(attributeRec.getListcode());
			attval = attributeRec.getListname();			
		}	
        return attval;
    }    
 	

%>


<%
       	 Object result[]= null;//CanonE008ItemProcessUtil.getLoginUserInfo(request, response);
       	 Object attributeresult[]= null;
         S21SecurityContext context1 = S21SecurityContextHolder.getContext();
      	 S21Authentication s21Authentication1=context1.getAuthentication();
         String loginUserID=s21Authentication1.getIdentityDetails().getUID();//(BigDecimal)CanonE008ItemProcessUtil.second(result);
         String getEmailID=s21Authentication1.getIdentityDetails().getEmailAddress();
         String assignedUserRole="";
         
         Integer addtionalAttList=0;
         /* String[] roleNames={
        		 "E008_ACCT",
         		 "E008_MERCH",
        		 "E008_MKT",
        		 "E008_MSTR",
        		 "E008_PART",
        		 "E008_PRC",
        		 "E008_SALES",
        		 "E008_SOL",
        		 "E008_SUP",
        		 "E008_SVC"
         };

         
         for(String role:roleNames)
         {
        	//System.out.println("Role Names:"+role+" hasRole:"+CanonS21SessionValidate.hasRole(role));
        	if(CanonS21SessionValidate.hasRole(role))
        	{
        		assignedUserRole=role;
        		System.out.println("Mapped Role Names:"+role);
        	}
         } */
 		
         System.out.println("loginUserID is "+loginUserID);
         String loginUserFullName=CanonS21SessionValidate.getFullName();//(String)CanonE008ItemProcessUtil.third(result);
         String roleId=assignedUserRole;//"E590_MSTR";//(BigDecimal)CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("roleId"),false);
         System.out.println("roleId is "+roleId);
         String action = request.getParameter("action");
         BigDecimal p_project_number = CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("project_number"),false);
         String p_project_description = request.getParameter("project_description");
         Timestamp p_creation_date = CanonE008ItemProcessUtil.toTimestamp2(request.getParameter("creation_date"));
         Timestamp p_last_update_date = CanonE008ItemProcessUtil.toTimestamp2(request.getParameter("last_update_date"));

         String p_project_name = request.getParameter("project_name");
         String p_project_type = request.getParameter("project_type");
         String p_master_project = request.getParameter("master_project");
         String p_project_status = request.getParameter("project_status");
         String p_project_notes = request.getParameter("project_notes");
         String p_project_processor_id =request.getParameter("project_processor_id");
         String p_requested_date = request.getParameter("requested_date");
         
         String p_notes = request.getParameter("note_comments");
         
         boolean isNewProject=p_project_number==null;
         if (p_project_number==null)
        	 isNewProject=true;
         else
        	 isNewProject=false;
         
         String p_project_processor_full_name=null;
         String resultMsg = "";
         System.out.println("action "+action);
         System.out.println("p_project_number "+p_project_number);

         if("upload".equals(action) 
	         || "save".equals(action) 
	         //|| "save_and_validate_project".equals(action) 
	         || "save_and_reject_project".equals(action)
	         || "submit_project".equals(action) )
         {

        	 CanonE008ItemProcessDAO.CanonE008ProjectRec p=null;
             p=new CanonE008ItemProcessDAO.CanonE008ProjectRec();
             if("upload".equals(action)){
                 p.setProjectNumber(UPLOAD_ITEMS);
                 p.setProjectId(UPLOAD_ITEMS);
             }else{    
                 p.setProjectName(p_project_name);
                 p.setProjectNumber(p_project_number);
                 p.setProjectId(p_project_number);
                 p.setProjectDesc(p_project_description);
                 p.setApprovalStatus(p_project_status);
     //          p.setApprovalComments(p_approvers_comments);
                 p.setLastUpdateBy(loginUserID);
                 p.setProjectNotes(p_project_notes);
                // p.setProjectSubmitter(isNewProject? loginUserID : p_project_processor_id.toString());
                 if(isNewProject){
                     p.setProjectRequester(loginUserID);
                     p.setCreatedBy(loginUserID);
                 }else{
                     p.setProjectRequester(p_project_processor_id.toString());
                 }
             }
                     
             List itemTable=new ArrayList();
             result=CanonE008ItemProcessDAO.saveProjectItems(p, itemTable);
             checkErrors(result, 1,2);
             p_project_number=(BigDecimal)CanonE008ItemProcessUtil.first(result);
             isNewProject=false;

         }
         if("upload".equals(action)){
             pageContext.setAttribute("resultMsg", resultMsg,PageContext.REQUEST_SCOPE);
             resultMsg = "forward to CanonE008ExcelProcess.jsp";
             pageContext.forward("CanonE008ExcelProcess.jsp");

         }else if("delete_project".equals(action)){
               result=CanonE008ItemProcessDAO.deleteProject(p_project_number);
               checkErrors(result, 0,1);
         }else if("add_notes".equals(action)){
             System.out.println("add Notes");
             System.out.println("p_notes"+p_notes);
        	 result=CanonE008ItemProcessDAO.addNotes(p_project_number,loginUserID,p_notes);
             checkErrors(result, 0,1);
         }else if("close_project".equals(action)){ // Added by Madhusudan Duna
             result=CanonE008ItemProcessDAO.closeProject(p_project_number);
             checkErrors(result, 0,1);// Added by Madhusudan Duna
             checkResult(result);
             String error_flag=(String)result[0];
            if("S".equals(error_flag))
            {	
				    // Added By Madhusudan Duna                    
		  			emailList = (List) CanonE008ItemProcessUtil
		  					.first(CanonE008ItemProcessDAO.getRoleEmailAddress(p_project_number.toString()));
		  			//List l = new ArrayList();
		  			String[] TO_REQUEST_ADDRESS = new String[emailList.size()];
		  			for (int i = 0; i < emailList.size(); i++) {
		  					
		  				TO_REQUEST_ADDRESS[i]=(String)emailList.get(i);
		  				System.out.println("validate_project result "+TO_REQUEST_ADDRESS[i]);	
		  			}  // Added By MAdhusudan Duna
		             
		   		    // Added By Madhusudan Duna                    
		  			emailList = (List) CanonE008ItemProcessUtil
		  					.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E590_MSTR"));
		  			//List l = new ArrayList();
		  			String[] TO_ADDRESS = new String[emailList.size()];
		  			for (int i = 0; i < emailList.size(); i++) {
		  					
		  				TO_ADDRESS[i]=(String)emailList.get(i);
		  				System.out.println("validate_project result "+TO_ADDRESS[i]);	
		  			}  // Added By MAdhusudan Duna
		            
		           String emailSubject="";
		          //if ("E590_MSTR".equalsIgnoreCase(roleId))
						emailSubject = "Project Approved - "
								+ p_project_number;     			
					HtmlEmail email = CanonEmailUtil.createHtmlEmail();
					email.setSubject(emailSubject)
							.setMsg("Project has been submitted successfully")
					
							.addTo(TO_ADDRESS) // Added By MAdhusudan Duna
							.addCc(TO_REQUEST_ADDRESS);
					
							//.addBcc("mduna_consultant@cusa.canon.com;salu_consultant@cusa.canon.com");
					        //.addTo("salu_consultant@cusa.canon.com","Subhadeep")
							//.addTo("mduna_consultant@cusa.canon.com","Madhusudan");
		
					String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\">"
							+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Number </b></td><td align=\"left\">"
							+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Description </b></td><td align=\"left\">"
							+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Submitter</b></td><td align=\"left\">"
							+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
							+"<b>Project Notes </b></td></tr>"
							+"<tr><td>"+p_project_number+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_description)+"</td><td>"+p_project_processor_id+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_notes)+"</td></tr></table>"
							+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e590/jsp/canonE590Project.jsp?project_number="+p_project_number+"&roleId="+roleId
							+ ">Project Detail</a>";
					email.setHtmlMsg("<html>" + cid +"</html>");
					email.send();
            }
             
             
         }else if("validate_project".equals(action)||"save_and_validate_project".equals(action)){
        	 //System.out.println("inside validate_project result " + action);
        	 result=CanonE008ItemProcessDAO.validateProject(p_project_number, roleId, loginUserID);
             //System.out.println("validate_project result "+result);
             checkErrors(result, 1,2);
             //System.out.println("validate_project result 11");
             List errors=(List)CanonE008ItemProcessUtil.first(result);
             System.out.println("validate_project result 22 errors - " + errors.size());
             pageContext.setAttribute("validate_project_errors", errors,PageContext.REQUEST_SCOPE);
             //System.out.println("validate_project result 33");
             pageContext.forward("CanonE008Service.jsp");
             //System.out.println("validate_project result 44");
         }else if("validate_required_fields".equals(action)){
             //result=CanonE008ItemProcessDAO.validateRequiredFields(p_project_number, roleId, loginUserID);
             //checkErrors(result, 1,2);
             // List errors=(List)CanonE008ItemProcessUtil.first(result);
             //pageContext.setAttribute("validate_required_fields", errors,PageContext.REQUEST_SCOPE);
             pageContext.forward("canonE590Service.jsp"); 
         }else if("check_status_monitor".equals(action)){
             result=CanonE008ItemProcessDAO.checkStatusMonitor(p_project_number);
             System.out.println("in checkStatusMonitor 11");
             //checkResult(result);
             System.out.println("in checkStatusMonitor 22");
             //pageContext.setAttribute("check_status_monitor", CanonE008ItemProcessUtil.first(result),PageContext.REQUEST_SCOPE);
             System.out.println("in checkStatusMonitor 33");
         }else if("get_ccid_desc".equals(action)){
             String p_segments=request.getParameter("segments");
             result=CanonE008ItemProcessDAO.getCcidDesc(p_segments);
             checkResult(result);
             pageContext.setAttribute("get_ccid_desc", CanonE008ItemProcessUtil.first(result),PageContext.REQUEST_SCOPE);
             pageContext.forward("canonE590Service.jsp");
         }else{

             if("delete_item".equals(action)){
                 String select_items[]=parameterValues(request,"select_item");
                 for(int i=0;select_items!=null && i<select_items.length;i++){
                	 System.out.println("Delete Item - "+select_items[i]);
                	 BigDecimal p_item_id=CanonE008ItemProcessUtil.toBigDecimal(select_items[i],false);
                     result=CanonE008ItemProcessDAO.deleteProjectItems(p_project_number, p_item_id);
                     System.out.println("Deleted Successful "+select_items[i]);
                     if(result!=null){
                       checkErrors(result, 0,1);
                     }
                 }
             }else if("submit_project".equals(action)){
                   result=CanonE008ItemProcessDAO.startapprovalprocess(p_project_number);
                   checkErrors(result, 0,1);
					// Added By Madhusudan Duna                    
	     			emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_MSTR"));
	     			//List l = new ArrayList();
	     			String[] TO_ADDRESS = new String[emailList.size()];
	     			for (int i = 0; i < emailList.size(); i++) {
	     					
	     				TO_ADDRESS[i]=(String)emailList.get(i);
	     				System.out.println("validate_project result "+TO_ADDRESS[i]);	
	     			}  // Added By MAdhusudan Duna
                   
                  String emailSubject="";
                 //if ("E590_MSTR".equalsIgnoreCase(roleId))
     				emailSubject = "Item Master - Approval Required for Project " + p_project_number;     			
     				HtmlEmail email = CanonEmailUtil.createHtmlEmail();
     				email.setSubject(emailSubject)
     					 .setMsg("Project has been submitted successfully")
     					 .addTo(TO_ADDRESS); // Added By MAdhusudan Duna
     			
     					//.addBcc("mduna_consultant@cusa.canon.com;salu_consultant@cusa.canon.com");
     			        //.addTo("salu_consultant@cusa.canon.com","Subhadeep")
     					//.addTo("mduna_consultant@cusa.canon.com","Madhusudan");

     			String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\">"
 						+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
 						+"<b>Project Number </b></td><td align=\"left\">"
 						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
 						+"<b>Project Description </b></td><td align=\"left\">"
 						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
 						+"<b>Project Submitter</b></td><td align=\"left\">"
 						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
 						+"<b>Project Notes </b></td></tr>"
 						+"<tr><td>"+p_project_number+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_description)+"</td><td>"+p_project_processor_id+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_notes)+"</td></tr></table>"
     					+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e590/jsp/canonE590Project.jsp?project_number="+p_project_number+"&roleId="+roleId
     					+ ">Project Detail</a>";
     			email.setHtmlMsg("<html>" + cid +"</html>");
     			email.send();
     		} else if ("approve_project".equals(action)) {
     			String p_comments = request
     					.getParameter("approve_comments");
     			result = CanonE008ItemProcessDAO.notificationAction(
     					p_project_number == null ? "" : p_project_number
     							.toString(), loginUserID, p_comments,
     					roleId, "APPROVED");
     			checkErrors(result, 0, 1);

				// Added By Madhusudan Duna                    
     			emailList = (List) CanonE008ItemProcessUtil
     					.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E590_SVC"));
     			String[] TO_SVC_ADDRESS = new String[emailList.size()];
     			for (int i = 0; i < emailList.size(); i++) {
     					
     				TO_SVC_ADDRESS[i]=(String)emailList.get(i);
     				System.out.println("validate_project result "+TO_SVC_ADDRESS[i]);	
     			}  // Added By MAdhusudan Duna
     			
				// Added By Madhusudan Duna                    
     			emailList = (List) CanonE008ItemProcessUtil
     					.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E590_SALES"));
     			String[] TO_SALES_ADDRESS = new String[emailList.size()];
     			for (int i = 0; i < emailList.size(); i++) {
     					
     				TO_SALES_ADDRESS[i]=(String)emailList.get(i);
     				System.out.println("validate_project result "+TO_SALES_ADDRESS[i]);	
     			}  // Added By MAdhusudan Duna
     			
				// Added By Madhusudan Duna                    
     			emailList = (List) CanonE008ItemProcessUtil
     					.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E590_MKT"));
     			String[] TO_MKT_ADDRESS = new String[emailList.size()];
     			for (int i = 0; i < emailList.size(); i++) {
     					
     				TO_MKT_ADDRESS[i]=(String)emailList.get(i);
     				System.out.println("validate_project result "+TO_MKT_ADDRESS[i]);	
     			}  // Added By MAdhusudan Duna

				// Added By Madhusudan Duna                    
     			emailList = (List) CanonE008ItemProcessUtil
     					.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E590_ACCT"));
     			String[] TO_ACCT_ADDRESS = new String[emailList.size()];
     			for (int i = 0; i < emailList.size(); i++) {
     					
     				TO_ACCT_ADDRESS[i]=(String)emailList.get(i);
     				System.out.println("validate_project result "+TO_ACCT_ADDRESS[i]);	
     			}  // Added By MAdhusudan Duna
     			
     			//String[] TO_SVC_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
     			//String[] TO_SALES_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
     			//String[] TO_MKT_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
     			//String[] TO_ACCT_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
     			
     			List approvaStatusList=(List)CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getcurrentapprovals(p_project_number));
     			Map <String,String[]> itemEmailMap=new HashMap<String,String[]>();
     			itemEmailMap.put("E590_SVC", TO_SVC_ADDRESS);
     			itemEmailMap.put("E590_SALES", TO_SALES_ADDRESS);
     			//itemEmailMap.put("E590_MSTR", "salu_consultant@cusa.canon.com");
     			itemEmailMap.put("E590_MKT", TO_MKT_ADDRESS);
     			itemEmailMap.put("E590_ACCT", TO_ACCT_ADDRESS);
     			
     			
     			for(int i=0;approvaStatusList!=null && i<approvaStatusList.size();i++){
                    CanonE008ItemProcessDAO.ApprovalStatusInfo status=(CanonE008ItemProcessDAO.ApprovalStatusInfo)approvaStatusList.get(i);
                    if("PENDING".equalsIgnoreCase(status.getApprovalStatus()) && "E590_MSTR".equalsIgnoreCase(roleId))
                    {
                    	HtmlEmail email = CanonEmailUtil.createHtmlEmail();
                    	 String emailSubject="";
                         if ("E590_MSTR".equalsIgnoreCase(status.getApproverRole()))
             				emailSubject = "Item Master - Approval Required for Project "
             						+ p_project_number;
             			else if ("E590_ACCT".equalsIgnoreCase(status.getApproverRole()))
             				emailSubject = "Accounting - Approval Required for Project "
             						+ p_project_number;
             			else if ("E590_MKT".equalsIgnoreCase(status.getApproverRole()))
             				emailSubject = "Marketing - Approval Required for Project "
             						+ p_project_number;
             			else if ("E590_SALES".equalsIgnoreCase(status.getApproverRole()))
             				emailSubject = "Incentive Comp - Approval Required for Project "
             						+ p_project_number;
             			else if ("E590_SVC".equalsIgnoreCase(status.getApproverRole()))
             				emailSubject = "Service - Approval Required for Project "
             						+ p_project_number;
             				email.setSubject(emailSubject)
         					.setMsg("Project has been submitted successfully")
         					.addTo(itemEmailMap.get(status.getApproverRole()));    
          				String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\">"
          						+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
          						+"<b>Project Number </b></td><td align=\"left\">"
          						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
          						+"<b>Project Description </b></td><td align=\"left\">"
          						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
          						+"<b>Project Submitter</b></td><td align=\"left\">"
          						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
          						+"<b>Project Notes </b></td></tr>"
          						+"<tr><td>"+p_project_number+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_description)+"</td><td>"+p_project_processor_id+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_notes)+"</td></tr></table>"
              					+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e590/jsp/canonE590Project.jsp?project_number="+p_project_number+"&roleId="+roleId
              					+ ">Project Detail</a>";
              			email.setHtmlMsg("<html>" + cid +"</html>");
              			email.send();             						
                    
                    }
     			}
     			 
     		} else if ("reject_project".equals(action)
     				|| "save_and_reject_project".equals(action)) {
     			String p_comments = request.getParameter("reject_comments");
     			result = CanonE008ItemProcessDAO.notificationAction(
     					p_project_number == null ? "" : p_project_number
     							.toString(), loginUserID, p_comments,
     					roleId, "REJECTED");
     			checkErrors(result, 0, 1);

				// Added By Madhusudan Duna                    
     			emailList = (List) CanonE008ItemProcessUtil
     					.first(CanonE008ItemProcessDAO.getRoleEmailAddress(p_project_number.toString()));
     			//List l = new ArrayList();
     			String[] TO_REJECT_ADDRESS = new String[emailList.size()];
     			for (int i = 0; i < emailList.size(); i++) {
     					
     				TO_REJECT_ADDRESS[i]=(String)emailList.get(i);
     				System.out.println("validate_project result "+TO_REJECT_ADDRESS[i]);	
     			}  // Added By MAdhusudan Duna
     			
     			
     			Object []projectDetails = CanonE008ItemProcessDAO
     					.getProjectItems(p_project_number);
     			if (projectDetails != null && projectDetails.length > 0) {
     				CanonE008ItemProcessDAO.CanonE008ProjectRec p = (CanonE008ItemProcessDAO.CanonE008ProjectRec) CanonE008ItemProcessUtil
     						.first(projectDetails);
     				String p_project_created_by = p.getCreatedBy();
     				HtmlEmail email = CanonEmailUtil.createHtmlEmail();
               	 String emailSubject="Project Rejected - "+ p_project_number;
               	 String emailBody="The Project is Rejected with the following comments:"+p_comments;
                 String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\">"
  						+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
  						+"<b>Project Number </b></td><td align=\"left\">"
  						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
  						+"<b>Project Description </b></td><td align=\"left\">"
  						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
  						+"<b>Project Submitter</b></td><td align=\"left\">"
  						+"<font color=\"#336699\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
  						+"<b>Project Notes </b></td></tr>"
  						+"<tr><td>"+p_project_number+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_description)+"</td><td>"+p_project_processor_id+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(p_project_notes)+"</td></tr></table>"
      					+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e590/jsp/canonE590Project.jsp?project_number="+p_project_number+"&roleId="+roleId
      					+ ">Project Detail</a>";
               	
               	email.setSubject(emailSubject)               	             
				//.addTo("salu_consultant@cusa.canon.com");
               	.addTo(TO_REJECT_ADDRESS);
               	email.setHtmlMsg(emailBody+"<html>" + cid +"</html>");
      			email.send();   	
      			projectDetails=null;
     			}
     			
     		}
             
            CanonE008ItemProcessDAO obj0 = new CanonE008ItemProcessDAO();
            Map<String,List<String>> temaplateHeaderMap=new HashMap<String,List<String>>();
             
            Object[] templateList = obj0.templateList();
            ArrayList templateNameList = (ArrayList)templateList[0];
            Object[] attributeHeaders = obj0.getItemMainAttributeHeaders(p_project_number,loginUserID);
         	ArrayList templateHeaderList = (ArrayList)attributeHeaders[0];
            int cnttemplate = 0; //templateNameList.size();
            int templateHeaderListCount=templateHeaderList.size();
            System.out.println("111");
      		List itemList = null;
      		List attributeList = null;
      		List bomItemList = null;
 
      		attributeresult = obj0.getProjItemAdditionalAttrValues(new BigDecimal("1"),new BigDecimal("1"));
			attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
			addtionalAttList = attributeList.size();
			
 /*   		List ColumnheaderitemList=null;
 			Object Headerresult[] = CanonE008ItemProcessDAO.getColumnHeaderDetails();
 			ColumnheaderitemList = (List) CanonE008ItemProcessUtil.first(Headerresult);
 */ 			//System.out.println("ColumnheaderitemList:" + ColumnheaderitemList.size());
    		if (isNewProject) {
     			
    			System.out.println("NewProject");
    			p_project_processor_id = loginUserID;
     			p_project_processor_full_name = loginUserFullName;
     			p_creation_date = new Timestamp(new Date().getTime());
     			p_last_update_date = p_creation_date;
     			canCreateProject = canCreateProject(null, roleId,loginUserID);
     			System.out.println("canCreateProject:" + canCreateProject);
     			if (!canCreateProject) {
     				throw new ItemProcessException(
     						"No permission to create project.", null);
     			}
     			canModifyProject = canCreateProject;
     		} else {
     			
     			System.out.println("OldProject");
     			canViewProject = canViewProject(p_project_number, roleId,loginUserID);
     			if (!canViewProject) {
     				throw new ItemProcessException(
     						"No permission to view the project.", null);
     			}
     			System.out.println("OldProject 1" +p_project_number);
     			result = CanonE008ItemProcessDAO.getProjectItems(p_project_number);
     			System.out.println("OldProject 2");
     			if (result != null && result.length > 0) {
     				System.out.println("11244");
     				CanonE008ItemProcessDAO.CanonE008ProjectRec p = (CanonE008ItemProcessDAO.CanonE008ProjectRec) CanonE008ItemProcessUtil.first(result);
     				
     				p_project_name = p.getProjectName();
     				p_master_project = p.getMasterproject();
      			    /* p_last_update_date = p.getLastUpdateDate();
     				p_creation_date = p.getCreationDate();  */
     				System.out.println("p_requested_date is "	+ p.getCreationDate());
     				//p_requested_date = p.getCreationDate().toString();
     				
     				
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
					p_requested_date = format.format(p.getCreationDate()); 
     				
     				System.out.println("p_requested_date is "	+ p_requested_date);
     				
     				p_project_description = p.getProjectDesc();
     				p_project_type = p.getProjectType();
     				p_project_status = p.getApprovalStatus();
     				System.out.println("p_project_status is " + p_project_status);
     				p_project_notes = p.getProjectNotes();
     				p_project_processor_id = p.getProjectRequester(); //  p.getProjectSubmitter();
     				p_project_processor_full_name = p.getCreatedBy();
     						
     				p_approvers_comments = p.getApprovalComments();
  
     				System.out.println("p_approvers_comments is "	+ p_approvers_comments);
     				itemList = (List) CanonE008ItemProcessUtil.second(result);
     				cnttemplate=itemList.size();
     				canCreateProject = canCreateProject(p_project_number,roleId,loginUserID);
     				canModifyProject = canModifyProject(p_project_number,roleId, loginUserID);
     				canDeleteProject = canDeleteProject(p_project_number,roleId, loginUserID);
     				boolean b[] = checkSubmitApprovalProject(p_project_number, roleId, loginUserID);
     				canSubmitForApproval = b[0];
     				canViewStatusMonitor = b[1];
     				
     				canValidateRejectProject = checkValidateRejectProject(p_project_number, roleId, loginUserID);
     				
     				canValidateCloseProject = false;
     				
     				/*if (("AWAITING PRICING".equals(p_project_status)) && ("E590_PRC".equalsIgnoreCase(roleId)))// Added by Madhusudan Duna
     				{
     					canValidateCloseProject = true;
     				}
     				else
     					canValidateCloseProject = false; // Added by Madhusudan Duna
     					
     			  */		
     					
     			}
     			
     			itemList = itemList == null ? Collections.EMPTY_LIST: itemList;
     			/* if (canModifyProject) {
     				
     				System.out.println("canModifyProject test:" + canModifyProject);
     				result = CanonE008ItemProcessDAO.validateRequiredFields(p_project_number,new BigDecimal("10"));
     				
     				System.out.println("canModifyProject test1:" + canModifyProject);
     				checkErrors(result, 1, 2);
     				
     				System.out.println("canModifyProject test2:" + canModifyProject);
     				
     				requiredFields = CanonE008ItemProcessUtil.first(result) != null ? (List) CanonE008ItemProcessUtil.first(result) : Collections.EMPTY_LIST;
     				System.out.println("canModifyProject test3:" + canModifyProject);		
     			}
     			
     			System.out.println("canModifyProject test4:" + canModifyProject);
     			if (PENDING_APPROVAL.equals(p_project_status)) {
     				System.out.println("canModifyProject test5:" + canModifyProject);
     				/////////result = CanonE008ItemProcessDAO.validateOwnershipFields(p_project_number,roleId, loginUserID);
     				///////checkErrors(result, 1, 2);
     				//ownershipFields = CanonE008ItemProcessUtil.first(result) != null ? (List) CanonE008ItemProcessUtil.first(result) : Collections.EMPTY_LIST;
     			} */
     			System.out.println("canModifyProject test6:" + canModifyProject);
     			}
     		
     		   System.out.println("canModifyProject test7:" + p_project_type);

               Object[] CattemplateList = obj0.CattemplateList(p_project_type);
     		   
     		   addLOV("template", CanonE008ItemProcessDAO.CattemplateList(p_project_type));
     		   addLOV("masterProject", CanonE008ItemProcessDAO.masterProjectList());
     		   addLOV("projectType", CanonE008ItemProcessDAO.projectTypeList());
     		   addLOV("componentList", CanonE008ItemProcessDAO.getComponentList("1"));
     		  
     		
%>
     
<style>

 div.dataTables_wrapper {
        width: 100%;  ##1240px;
        margin-left: 0px
        /*Height: 400px;*/
        margin: 0 auto;
    }  


.ui-validation-error {
	border: 2px solid #cd0a0a;
	}
 
 .custom-combobox {
		position: relative;
		display: inline-block;
		width: 150px;
	}
	
	/* .custom-combobox-toggle {
		position: absolute;
		top: 0;
		bottom: 0;
		margin-left: -1px;
		padding: 0;
	} */
/* 	.custom-combobox-input {
		margin: 0;
		padding: 3px 10px;
		background: #FFFFFF;
		border:1px #878787 solid;
		font-family;"Raleway", sans-serif;
		font-size-11px;
		color;#000000;
	} */

	.custom-combobox-input {
		margin: 0;
		/* padding: 0px 10px; */
		background: #FFFFFF;
		border:1px #878787 solid;
		font-family:"Raleway", sans-serif;
		font-size:11px;
		font-weight: normal;
		color:#000000;
		width:150px;
		height:20px;
		
	}
	    
</style>
 <% String ctxPath = request.getContextPath() ; %>
<script language="javascript">



/* document.createElement('datalist'); // Recognise datalist in old IE.

window.onload = function() {
    // Get language options.
    var languages = document.getElementById('masterProject');
    var options = languages.getElementsByTagName('optionX');
    
    // Append options count.
    languages.parentNode.appendChild(
        document.createElement('strong').appendChild(
            document.createTextNode(options.length + ' options')
        )
    );
} */
/* 	var datalist, listAttribute, options = [];
	
	if(!Modernizr.input.list)
	{
	    $('input[type="text"][list]').each(function() {
	        listAttribute = $(this).attr('list');
	        datalist = $('#' + listAttribute);
	        if (datalist.length > 0) {
	            options = [];
	            datalist.find('option').each(function() {
	                options.push({ label: this.innerHTML, value: this.value });
	            });
	            $(this).autocomplete({
	                source: options
	            });
	        }
	    });
	    $('datalist').remove();
	} */
	var NLSformat = 'DD-Mon-RRRR';
	var nooftemplate=0;
	var noofAttributes=<%=templateHeaderListCount%>;

	if (top !== self) {
		$.ui.dialog.prototype._focusTabbable = $.noop;
	}
	
 	function format ( d ) {
	    // `d` is the original data object for the row
	    //alert("1212121");
	    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	        '<tr>'+
	            '<td>Full name:</td>'+
	            '<td>'+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Extension number:</td>'+
	            '<td>'+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Extra info:</td>'+
	            '<td>And any further details here (images etc)...</td>'+
	        '</tr>'+
	    '</table>';
	} 
	
	
	$(document).ready(function() 
	{
	 		var table = $('#templateTable').DataTable( {
	        scrollY:        "300px",
	        scrollX:        true,
	        scrollCollapse: true,
	        searching: false,
	        autoWidth: false,
	        "bFilter" : false,
	        "bJQueryUI":false,
	        //"bSort":false,
	        "bPaginate":true, // Pagination True 
	        "sPaginationType":"full_numbers", // And its type.
	         "iDisplayLength": 15,
	         "bDeferRender" : false,
	         "bLengthChange":false,
	         //"processing": true,
	         "bStateSave" : false,
	         fixedColumns:   {
	            leftColumns: 0,
	            rightColumns: 1
	        }, 
	         "columnDefs": [                         
	                          {
	                              // The `data` parameter refers to the data for the cell (defined by the
	                              // `data` option, which defaults to the column being worked with, in
	                              // this case `data: 0`.
	                              "render": function ( data, type, row, meta ) {
	                            	  //console.log(meta);
	                            	  //console.log(meta["row"]);	                            	  
	                            	  var res = meta["row"];
	                                  /* return '<a id="'+(nooftemplate++)+'"  onclick="assignId('+nooftemplate+');" data-toggle="modal" data-target="#myModal" class="dt-button"><span>Additional</span></a>'; */
	                                  return '<a id="'+(nooftemplate++)+'"  onclick="assignId('+res+');" data-toggle="modal" data-target="#myModal" class="dt-button"><span>Additional</span></a>';
	                              },
	                              "targets": (noofAttributes+2+2)
	                          }
	                      ]        
	    } );
	    
	                              
	                              
	    var table = $('#templateTable').DataTable();
	    var pageno=getIdVal("pageNo");
	    table.page(parseInt(pageno)).draw('page');
	    
	    $('#templateTable').on( 'page.dt', function () {
	        var info = table.page.info();
	       console.log( 'Showing page: '+info.start+' of '+info.end );
	      	$("#pageFrom").val(info.start);
	       	$("#pageTo").val(info.end);
	       	$("#pageNo").val(info.page);
	    } );
	    
    	/*
	    $('#next').on( 'click', function () {
	        table.page( 'next' ).draw( 'page' );
	    } );
	     
	    $('#previous').on( 'click', function () {
	        table.page( 'previous' ).draw( 'page' );
	    } ); */
	    
	    
	   $('select.canonTemplateClass').on('change', function () {                
	             //alert("test");
		   		 var mainval=$(this).val(); 
	             var dropDownId=$(this).attr("id");              
	    		 var dropDownNumber = dropDownId.substring(dropDownId.indexOf("_")+1, dropDownId.length);             
	             var url= encodeURI('<%=ctxPath%>/e008/jsp/CanonE008GetSelectedTemplateAttributes.jsp?action=GetTemplate&templateName='+escape(mainval));   
	             //alert(mainval);         
	            	 $.ajax({  
							type: "POST",
							async:false,						
							url: url, 						
							complete: function(data)
								{  
									x =((data.responseText).replace(/^\s+|\s+$/g,''));
									res=x.split("@@");
									//alert("result " + res);
									 templateAttrVal=res[0].split(",");								
									 for(var i=0;i<templateAttrVal.length;i++)
									 {								 
									 var templateAttr=templateAttrVal[i];								 
									 //if(templateAttr.trim()=="null"||templateAttr.trim()=="[null"||templateAttr.trim()=="null]")
								     if($.trim(templateAttr)=="null"||$.trim(templateAttr)=="[null"||$.trim(templateAttr)=="null]")	 
									 	templateAttr="";									 								 
									 //$("#templateRow"+dropDownNumber+i).val(templateAttr);
									 $("#templateRowName"+dropDownNumber+"-"+i).val(templateAttr);
									 }															
								}  
						});  //ajax end 
	    });
	    
	    $(document).on("click", ".dt-button", function () {
	     
	             var additionalButtonId=$(this).attr("id");  
	   			 $(".modal-footer #addButtonPopupId").val(additionalButtonId );
	    
	   
	     
		});
	    
	      
	    $('#validate_project_button').click(function()
	      {
			       var x ;
				   //alert("1");
	                //saveProject();
	               validateProject();
	                //alert("2");
	            }).attr("title","click to validate the project");
				
	    $('#save_project_button_2,#save_project_button').click(function()
	    	      {
	   	                saveProject();
	 	            }).attr("title","click to save the project");
				
	    $('#add_note_button').click(function()
	    	      {
	    	 		$("#add-note-dialog").dialog( "open" );
	    	      }).attr("title","click to add the Note.");
				
	   /*  $("#add_item").click(function(){
	    	//addNewRow("templateTable");
	    	addRow();
	    });
	 */
	    $('#submit_project_button').click(function(e){
	        submitProject();
	    });
	    
	    $('#delete_item').click(function(e){
	        deleteItems();
	    });
	
	    $('#delete_project_button').click(function(e){
	        deleteProject();
	    });
	
	    $('#approval_status_button').click(function()
	    {
	         <%-- blockUsrInterface();
	         checkStatusMonitor(function(){
	             unBlockUsrInterface();
	             var projectNumber=<%=p_project_number%>;
	             var url = "<%=ctxPath%>/e008/jsp/canonE008Approval.jsp?project_number="+projectNumber;
	             var win = window.open(url,"NewItemSetupApproval","height=650px,width=900px,status=yes,scrollbars=yes,resizable=yes");
	             win.focus();
	         }); --%>
	         blockUsrInterface();
	         //checkStatusMonitor(function(){
	             unBlockUsrInterface();
	             var projectNumber=<%=p_project_number%>;
	             var url = "<%=ctxPath%>/e008/jsp/CanonE008Approval.jsp?project_number="+projectNumber;
	             var win = window.open(url,"NewItemSetupApproval","height=650px,width=900px,status=yes,scrollbars=yes,resizable=yes");
	             win.focus();
	         //}); 
	    });    
	
	    $("#add-note-dialog").dialog({
	        autoOpen: false,
	        height: "auto !important",
	        width: 400,
	        height:300,
	        modal: true,
	        buttons: {
	          "Close": function() {
	            $( this ).dialog( "close" );
	          },
	          "Add": function() {
	              $( this ).dialog( "close" );
	              addNotes();
	          }
	        },
	        close: function() {
	//          allFields.val( "" ).removeClass( "ui-validation-error" );
	        }
	      });
	
	    
	    $("#validate-comment-dialog").dialog({
	        autoOpen: false,
	        height: "auto !important",
	        width: 400,
	        modal: true,
	        buttons: {
	          "Close": function() {
	            $( this ).dialog( "close" );
	          },
	          "Approve": function() {
	              $( this ).dialog( "close" );
	              approveProject();
	          }
	        },
	        close: function() {
	//          allFields.val( "" ).removeClass( "ui-validation-error" );
	        }
	      });
	
	      $("#reject-comment-dialog").dialog({
	        autoOpen: false,
	        height: "auto !important",
	        width: 400,
	        height: 300,
	        modal: true,
	        buttons: {
	          "Close": function() {
	            $( this ).dialog( "close" );
	          },
	          "Reject": function() {
	              $( this ).dialog( "close" );
	              rejectProject();
	          }
	        },
	        close: function() {
	//          allFields.val( "" ).removeClass( "ui-validation-error" );
	        }
	      });
	
	      $("#approve-comment-dialog").dialog({
	        autoOpen: false,
	        height: "auto !important",
	        width: 400,
	        height: 300,
	        modal: true,
	        buttons: {
	          "Close": function() {
	            $( this ).dialog( "close" );
	          },
	          "Approve": function() {
	              $( this ).dialog( "close" );
	              approveProject();
	          }
	        },
	        close: function() {
	//          allFields.val( "" ).removeClass( "ui-validation-error" );
	        }
	      });
	      
	      $("#myBOMModal11").dialog({
		        autoOpen: false,
		        height: "auto !important",
		        width: 400,
		        height: 300,
		        modal: true,
		        buttons: {
		          "Close": function() {
		            $( this ).dialog( "close" );
		          },
		          "Approve": function() {
		              $( this ).dialog( "close" );
		              //approveProject();
		          }
		        },
		        close: function() {
		//          allFields.val( "" ).removeClass( "ui-validation-error" );
		        }
		      });
	      
	      function update_status( t) {
	          var project_status = $("#project_status" );
	          project_status
	            .text( t )
	            .addClass( "ui-state-highlight" );
	          setTimeout(function() {
	            project_status.removeClass( "ui-state-highlight", 1500 );
	          }, 500 );
	      }      
	      
	      function checkRequired( o, n, handler) {
	          if ( empty(o.val())) {
	            show_alert( n + " is required", handler, o);
	            return false;
	          } else {
	            return true;
	          }
	        }
	      
	      $('.bom_name_lov_icon').click(function()
	              {
	               	  //alert("sdsds");	
	    	  		  $.supplier_name_container=$(this).parents(".bom_name_container");
	                  var ele=$.supplier_name_container.find(".bom_name");
	                  if($.trim(ele.val()).length<3){
	                      default_alert_handler("To limit the search, please input at least 3 characters.");
	                  }else {
	                      openBOMLOV("bomNameLOV-DataDiv",{"bom_name": ele.val().toUpperCase()});
	                  }
	              });  	
	      
	        $(".bom_name_container").bind("selected", function(event, object) {
	            $("#bomNameLOV-DataDiv").html("");
	            $("#bomNameLOV-DataDiv").dialog("close");
	            $("#bomNameLOV-DataDiv").dialog("destroy");
	            var ele=$(this).find(".bom_name");
	            ele.val(object.supplier_name);
	            ele.attr("title",object.supplier_name);
	        });
	      

	        ////////////////////////////////////////////////////
	        
	        $.widget( "custom.combobox", {
				_create: function() {
					this.wrapper = $( "<span>" )
						.addClass( "custom-combobox" )
						.insertAfter( this.element );

					this.element.hide();
					this._createAutocomplete();
					//this._createShowAllButton();
				},

				_createAutocomplete: function() {
					var selected = this.element.children( ":selected" ),
						value = selected.val() ? selected.text() : "";

					this.input = $( "<input>" )
						.appendTo( this.wrapper )
						.val( value )
						//.attr( "title", "" )
						.attr( "name","masterProject")
  					    .attr( "id","masterProject")
						.addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
						.autocomplete({
							delay: 0,
							minLength: 0,
							source: $.proxy( this, "_source" )
						})
						.tooltip({
							classes: {
								"ui-tooltip": "ui-state-highlight"
							}
						});

					this._on( this.input, {
						autocompleteselect: function( event, ui ) {
							ui.item.option.selected = true;
							this._trigger( "select", event, {
								item: ui.item.option
							});
						}

						//autocompletechange: "_removeIfInvalid"
					});
				},

				_destroy: function() {
					this.wrapper.remove();
					this.element.show();
				}
			});

	        
	        function checkMaxLength( o, n, max, handler ) {
	            if ( o.val().length > max ) {
	              show_alert( n+ " - Maximum length (" + max + ") exceeded.", handler, o );
	              return false;
	            } else {
	              return true;
	            }
	          }

	          function checkRequired( o, n, handler) {
	            if ( empty(o.val())) {
	              show_alert( n + " is required", handler, o);
	              return false;
	            } else {
	              return true;
	            }
	          }

	          function checkIfTrue( o, n, valid, handler) {
	            if (!valid) {
	              show_alert(n, handler, o);
	              return false;
	            } else {
	              return true;
	            }
	          }


	          function checkRegexp( o, regexp, n, handler ) {
	            if ( !( regexp.test( o.val() ) ) ) {
	              show_alert( n, handler, o );
	              return false;
	            } else {
	              return true;
	            }
	          }

	          function checkDateTimeAfter( o, n, after, after_name, handler) {
	              var afterDateTime=new Date(after);
	              var d=new Date(o.val());
	              if (d.getTime()<=afterDateTime.getTime()){
	                show_alert( n + " must be after "+after_name +"("+ after+")", handler, o);
	                return false;
	              } else {
	                return true;
	              }
	          }

	          function checkCheckedAtLeast(checkboxes, atLeastNum, errormsg, handler) {
	            var len=checkboxes.filter(function(index){return this.checked}).length;
	            if ( len < atLeastNum) {
	              checkboxes.addClass( "ui-validation-error" );
	              show_alert(errormsg, handler);
	              return false;
	            } else {
	              return true;
	            }
	          }

	          function empty(str){
	              return !str || !/[^\s]+/.test(str);
	          }	        
	        
	        function checkInitialItems (prefix, handler){
	        	
        	    //alert("url - checkItems"  );

	            $.ajax({
	              type: "POST",
	              url: 'CanonE008ItemWorkbenchProcess.jsp', 
	              async:false,
				  data: "action="+"VALIDATEUPLOAD" +"&projectNumber="+getIdVal("projectNumber")+"&userId="+getIdVal("userId")+$("#submitForm").serialize() ,
	              success: function(data){
							unBlockUsrInterface();
	                        if(error_message(data)){
	                            show_error("Failed to save the project.",data);
	                        }else{
								//alert("1122" + data);
	                        	//x =((data.responseText).replace(/^\s+|\s+$/g,''));
								//alert("resp" + x);
								var res =data.split("@@@");
								//alert("res[1] "+res[1]);
								if (res[1]=='S')
								{
				                	 templateAttrVal=res[0].split("##");
				                	 //alert("Errors- "+templateAttrVal);
									 for(var i=0;i<templateAttrVal.length;i++)
									 {	
										var errdesc=templateAttrVal[i];
										//alert("errdesc " + errdesc);
										show_alert( errdesc, handler, "111");
									 }	 							                
									
	      					    }      		                        	
	                        	
	                        }
	                 } , 
	              complete: function(){
	                unBlockUsrInterface();
	              },
	              error:function(request){
	                    unBlockUsrInterface();
	                    var response=request.responseText;
	                    handler=makeSingleErrorHandler();
	                    //show_error("Failed to validate project.",response);
	              }
	            }); 
	            
	        }
	        	        
	        /*  function checkInitialItems(prefix, handler){
	        	alert("url11 - checkItems"  );
	            if(handler.valid()) $("."+prefix+"item_code").each(function(){
	                handler.valid() && checkRequired($(this),"Canon Item #", handler);
	            });

	            if(handler.valid()) $("."+prefix+"item_description").each(function(){
	                handler.valid() && checkRequired($(this),"Item Description", handler);
	            });
	        
	           
	            if(handler.valid()) $("."+prefix+"merch_type").each(function(){
	                handler.valid() && checkRequired($(this),"Merch Type", handler) && checkMaxLength( $(this), "Merch Type", 2, handler);
	            });
	            
	            if(handler.valid()) $("."+prefix+"item_type").each(function(){
	                handler.valid() && checkRequired($(this),"Item Type", handler);
	            });
	            //Added by DUNA RAO
	            if(handler.valid()) $("."+prefix+"canon_template").each(function(){
	                handler.valid() && checkRequired($(this),"Canon Template", handler);
	            });
	            
	            if(handler.valid()) $("."+prefix+"template").each(function(){
	                handler.valid() && checkRequired($(this),"Item Template", handler);
	            });
	            
	            if(handler.valid()) $("."+prefix+"prod_code").each(function(){
	                handler.valid() && checkRequired($(this),"Product Code", handler) && checkMaxLength( $(this), "Product Code", 2, handler);
	            });
	            
	            if(handler.valid()) $("."+prefix+"cost").each(function(){
	                handler.valid() && checkRequired($(this),"Std Cost", handler);
	            });
	            
	            if(handler.valid()) $("."+prefix+"msrp").each(function(){
	                handler.valid() && checkRequired($(this),"MSRP", handler);
	            });
	            
	        } */ 
	        
	        $.checkItems = checkInitialItems;
	        
	        $('#upload_item').click(function(e){
	            modelName = "#uploadExcelDiv";
	            var url = "<%=ctxPath%>/e008/jsp/CanonE008ExcelProcess.jsp?action=select&project_number=<%=p_project_number%>&userid=<%=loginUserID%>";
	            //alert(url);
	            $(modelName).dialog({
	                          height: 300,
	                          title: "Select file",
	                          modal: true ,
	                 		  autoOpen :false,
	                          width: 600,
	                 		  resizable: false,      
	                          buttons: { "Close": function() {  
	                                         var needrefresh=$("#submitButton").data("need-refresh");
	                                         $(modelName).html("");
	                                         $(this).dialog("close");
	                                         $(this).dialog("destroy");
	                                         if(needrefresh){
	                                             // TODO
	                                             refreshPage();
	                                         }
	                                     }

	                        }					
	                     });
				
	              // alert("111");
	            
	              $.ajax({
	                         url: url,
	                 		 cache: false,
	                         success: function(data){     
	                             //hidePleaseWait(); 
	                            //alert("22 "+data); 
	                           $(modelName).html(data);
	                           $("#readFrm").children("input").clone(true).appendTo("#submitForm");
	                         }             
	                     });
	           
	           $(modelName).dialog("open");
	           e.preventDefault();
	         });        	        
	        
	        
			$( "#masterProjectM" ).combobox();
			
	      
	} );

	 		
    function blockcsaUsrInterface() {
    	$.blockUI({	css : {
						border : 'none',
						padding : '15px',
						backgroundColor : '#000',
						'-webkit-border-radius' : '10px',
						'-moz-border-radius' : '10px',
						opacity : .5,
						color : '#fff',
						cursor : 'default'
					},
					message : '<h1> Please Wait. Checking CUSA..</h1>'
				}); 

	}
 

	
	function unBlockcsaUsrInterface() {
		$.unblockUI();
	}
	 		
	function arraycolumns(arraycount){
	
		var array = [];
  		
		 for (var i = 0; i < arraycount; i++) {
  		    array.push(37+i);
  		  }
  		  return (array);
    }

	function openBOMLOV(mName,options){
   		openE008LOV(mName,"BOM Name LOV",'CanonE008ItemsLOV.jsp',300,480, options);
    }
	
	function addNote() 
	      {
	 		$("#add-note-dialog").dialog( "open" );
	      };
			

	function approve() 
	      {
			validateandApproveProject();
	 		//$("#approve-comment-dialog").dialog( "open" );
	      };	      

  	function reject() 
	      {
  			//validateandRejectProject();	
	 		$("#reject-comment-dialog").dialog( "open" );
	      };	      
	      
	      
    function openBOM() 
     {
		//validateandApproveProject();
		//$("#myBOMModal11").dialog( "open" );
		document.getElementById('myBOMModal').style.display = "block";
     };	      

	function approvalHistory()  
    	    {
    	         blockUsrInterface();
    	         //checkStatusMonitor(function(){
    	             unBlockUsrInterface();
    	             var projectNumber=<%=p_project_number%>;
    	             var url = "<%=ctxPath%>/e008/jsp/CanonE008Approval.jsp?project_number="+projectNumber;
    	             var win = window.open(url,"NewItemSetupApproval","height=700px,width=1100px,status=yes,scrollbars=yes,resizable=yes");
    	             win.focus();
    	         //}); 
    	    };    	
	

    function callComponentChange(Id,row) {                
    	        //alert("test Id " + Id);
    	/*   	var mainval=$(this).val();
    	  		alert("mainval"+mainval);
    	        var dropDownId=$(this).attr("id");
    	        alert("dropDownId"+dropDownId);
    			var dropDownNumber = dropDownId.substring(dropDownId.indexOf("_")+1, dropDownId.length);  */ 
    	 		var selectBox = document.getElementById(Id);
    			//alert(selectBox.value);
   	            var selectedValue = selectBox.value;
    		    
    			 
    	        var url= encodeURI('<%=ctxPath%>/e008/jsp/CanonE008GetSelectedComponentDetails.jsp?component='+selectedValue);   
    	        //alert(mainval);         
    	       	 $.ajax({  
    						type: "POST",
    						async:false,						
    						url: url, 						
    						complete: function(data)
    							{  
    								 x =((data.responseText).replace(/^\s+|\s+$/g,''));
    								 //alert("X " + x);
    								 res=x.split("@@");
    								 templateAttrVal=res[0].split(",");			
    								 //alert("templateAttrVal " + templateAttrVal);
									 $("#selectCompId"+row).val(selectedValue);	
    								 for(var i=0;i<7;i++)
    								 {								 
    									 var templateAttr=templateAttrVal[i];
    									 //alert(" templateAttr-" +templateAttr);
    									 templateAttr= templateAttr.replace('[','').replace(']','');
     									//if(templateAttr.trim()=="null"||templateAttr.trim()=="[null"||templateAttr.trim()=="null]")
     								    if($.trim(templateAttr)=="null"||$.trim(templateAttr)=="[null"||$.trim(templateAttr)=="null]")	 
    									 	 templateAttr="";									 								 
    									if (i==1)
     										$("#bomCompDesc"+row).val(templateAttr);
    									if (i==2)
 											$("#bomManugacturer"+row).val(templateAttr);
    									if (i==3)
     										$("#bomMerchType"+row).val(templateAttr);
    									if (i==4)
     										$("#bomProdCode"+row).val(templateAttr);
    									if (i==5)
     										$("#bomItemType"+row).val(templateAttr);
    									if (i==6)
     										$("#bomCost"+row).val(templateAttr);
    									
    									
    								 }	
   								 }
    		 							 
    							  
    					});  //ajax end 
    		}; 
	    
    	    
    var counter = 1;
    var inrStr;
    var inraddStr = "";
    
    function callTemplateChange(Id,row) {                
       // alert("test Id " + Id + " row " + row);
/*   	var mainval=$(this).val();
  		alert("mainval"+mainval);
        var dropDownId=$(this).attr("id");
        alert("dropDownId"+dropDownId);
		var dropDownNumber = dropDownId.substring(dropDownId.indexOf("_")+1, dropDownId.length);  */ 
 		var selectBox = document.getElementById(Id);
		//alert(selectBox.selectedIndex);
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    //alert(selectedValue);

	    var templateBox = document.getElementById("htemplate_id_"+row);
	    var templateval = templateBox.value;
	    //alert("htemplateval "+templateval);
	    
	    
	    if ((selectedValue != templateval) && (templateval!=""))
	    {
	    	alert("You cannot change the Template. Please delete the row and add the new row." );
	    	$("#"+Id).val(templateval);
	    }
	    else
	    {	
        var url= encodeURI('<%=ctxPath%>/e008/jsp/CanonE008GetSelectedTemplateAttributes.jsp?action='+"GetTemplate"+"&templateName="+escape(selectedValue));   
        //alert(url);         
       	 $.ajax({  
					type: "POST",
					async:false,						
					url: url, 						
					complete: function(data)
						{  
							x =((data.responseText).replace(/^\s+|\s+$/g,''));
							//alert(" x- " +x);
							res=x.split("@@");
							 templateAttrVal=res[0].split(",");			
							 //alert("templateAttrVal Lenght" + templateAttrVal.length);
							 var k=0;
							 var n=0;
							  for(var i=0;i<templateAttrVal.length;i++)
							 {								 
								 var templateAttr=templateAttrVal[i];
								 //alert(" templateAttr-" +templateAttr);
								 templateAttr=templateAttr.replace('[','').replace(']','');
								 //if(templateAttr.trim()=="null"||templateAttr.trim()=="[null"||templateAttr.trim()=="null]")
								 if($.trim(templateAttr)=="null"||$.trim(templateAttr)=="[null"||$.trim(templateAttr)=="null]")	 
								 	 templateAttr="";									 								 
								
								 if (i==0) {
									 $("#template_id_"+row).val(templateAttr.substr(1,templateAttr.length) );
									 }
								 else {
									 n=i-1; 
									 var listcode="";
									 var listname = "";
									 var lovname = "";
									 if (templateAttr.substr(1, 1)=="M") { 
											 
										 	 if (templateAttr.substr(3, 1)!="Y")
										  	 {	
										 		//alert("Row " + row+n);
										  		//alert("Value " + $("#csatemplateRowName"+row+n).val());
	
										  		if ($("#csatemplateRowName"+row+"-"+n).val() != "Y")
											 		 $("#templateRowName"+row+"-"+n).val(templateAttr.substr(4,templateAttr.length));
										  		
										  		if ((templateAttr.substr(2, 1)=="Y") && (templateAttr.substr(4,templateAttr.length)!="")) //(templateAttr.substr(4,templateAttr.length)!=""))
											 	{
												 $("#templateRowName"+row+"-"+n).removeClass("required");
												 $("#templateRowName"+row+"-"+n).removeClass("notrequired");
       		 									 $("#templateRowName"+row+"-"+n).addClass("noneditrequired");
       		 									 $("#templateRowName"+row+"-"+n).prop("disabled", true); 
											 	}
											 else if ((templateAttr.substr(2, 1)=="Y") && (templateAttr.substr(4,templateAttr.length)=="")) //(templateAttr.substr(4,templateAttr.length)==""))
											 	{
													$("#templateRowName"+row+"-"+n).removeClass("noneditrequired");
													$("#templateRowName"+row+"-"+n).prop("disabled", false);
													$("#templateRowName"+row+"-"+n).removeClass("notrequired");
											 		$("#templateRowName"+row+"-"+n).addClass("required");
											 	}	
											 else
												{	 
												 $("#templateRowName"+row+"-"+n).removeClass("required");
												 $("#templateRowName"+row+"-"+n).removeClass("noneditrequired");
												 $("#templateRowName"+row+"-"+n).prop("disabled", false);
												 $("#templateRowName"+row+"-"+n).addClass("notrequired");
												}
											  		
										  		
										  	 }	
										 	 else
										 	 {
											  	var listdetails = templateAttr.substr(4,templateAttr.length);
											  	//alert("listdetails "+listdetails);
											  	var reslist=listdetails.split("**");
											  	if (reslist!="")
											  	{	listcode=reslist[0];
											  		listname = reslist[1];
											  		lovname = reslist[2];
											  	}
											 	
											  	if ((lovname=="ITEM_TYPE") && (listname!="PARTS"))
											  	{
											  		//document.getElementById("mercuryind"+row).checked=true;
											  		
											  		$("#mercuryind"+row).attr("checked", true);
											  		$("#mercuryind"+row).attr("value", "on");
											  		
											  		//var elm = document.getElementById("mercuryind"+row);
											  		//elm.click();
											  		
											  		//alert("lovname "+ lovname);
											  		//alert("listname "+ listname);
											  		//var lfckv = document.getElementById("mercuryind"+row).checked
											  		//alert("lfckv "+ lfckv);
											  		//alert("Check "+ "#mercuryind"+row);
											  		
											  	}
											  	
											  	if ((lovname!="SUPPLIER") && 
														(lovname!="PRODUCT_LEVEL5") &&
														(lovname!="SERVICE_MODEL") &&
														(lovname!="MARKETING_MODEL") && 
														(lovname!="MATERIAL_GROUP1") && 
														(lovname!="DEFAULT_SRC_WH") &&
														(lovname!="TARIFF_CODE"))
											  	{	
											  		
											  		//alert("Row " + row+n);
											  		//alert("Value " + $("#csatemplateRowName"+row+n).val());
		
											  		if ($("#csatemplateRowName"+row+"-"+n).val() != "Y")
											  		{
												  		$("#templateRowName"+row+"-"+n).empty();
											 			var	mainoption = $('<option selected ="" value="'+ listcode +'">'+listname+'</option>');
	 					  					        	$("#templateRowName"+row+"-"+n).append(mainoption);
	 					  					        	
											  		}
											  		
											  		if (lovname=="MARKETING_MODEL")
														$("#modtemplateRowName"+row+"-"+n).val(listname);
											  	}
											  	else
											  	{
											  		
									  				$("#templateRowName"+row+"-"+n).val(listcode);
									  				$("#default_text"+row+"-"+n).val(listname);
									  		
											  		if ((templateAttr.substr(2, 1)=="Y") && (listname!=""))
											  		{
											  			$("#default_text"+row+"-"+n).addClass("noneditrequired");
											    	 	$("#default_text"+row+"-"+n).prop("disabled", true);
											  		}
											  		else if ((templateAttr.substr(2, 1)=="Y") && (listname==""))
											  			$("#default_text"+row+"-"+n).addClass("required");
											  		else
											  			$("#default_text"+row+"-"+n).addClass("notrequired"); 
											  	}	
											  	
										 		//$("#templateRowName"+row+n).val(templateAttr.substr(4,templateAttr.length)); 
											 if ((templateAttr.substr(2, 1)=="Y") && (listname!="")) //(templateAttr.substr(4,templateAttr.length)!=""))
											 	{
												 $("#templateRowName"+row+"-"+n).removeClass("required");
												 $("#templateRowName"+row+"-"+n).removeClass("notrequired");
       		 									 $("#templateRowName"+row+"-"+n).addClass("noneditrequired");
       		 									 $("#templateRowName"+row+"-"+n).prop("disabled", true); 
  											 	 if (lovname=="MARKETING_MODEL")
														$("#modtemplateRowName"+row+"-"+n).addClass("noneditrequired");

											 	}
											 else if ((templateAttr.substr(2, 1)=="Y") && (listname=="")) //(templateAttr.substr(4,templateAttr.length)==""))
											 	{
													$("#templateRowName"+row+"-"+n).removeClass("noneditrequired");
													$("#templateRowName"+row+"-"+n).prop("disabled", false);
													$("#templateRowName"+row+"-"+n).removeClass("notrequired");
											 		$("#templateRowName"+row+"-"+n).addClass("required");
												 	if (lovname=="MARKETING_MODEL")
														$("#modtemplateRowName"+row+"-"+n).addClass("required");
											 	}	
											 else
												{	 
												 $("#templateRowName"+row+"-"+n).removeClass("required");
												 $("#templateRowName"+row+"-"+n).removeClass("noneditrequired");
												 $("#templateRowName"+row+"-"+n).prop("disabled", false);
												 $("#templateRowName"+row+"-"+n).addClass("notrequired");
											 	if (lovname=="MARKETING_MODEL")
											 		{
											 				$("#modtemplateRowName"+row+"-"+n).removeClass("required");
											 				$("#modtemplateRowName"+row+"-"+n).addClass("notrequired");
											 		}		
												}
	
										 	 }		
										 	}
										 else {
											  // alert("K "+k); 
											  //alert("value "+templateAttr.substr(3,templateAttr.length));
											  var additionstr = "";
										      if(templateAttr.substr(4,templateAttr.length)=="NULL]")
												  additionstr="";
											  else 
												  additionstr = templateAttr.substr(4,templateAttr.length);
												  
/* 											  $("#templateaddRow"+row+k).val(templateAttr.substr(3,templateAttr.length));
											  $("#templatedefaddRow"+row+k).val(templateAttr.substr(3,templateAttr.length));
*/											  
											 if ($("#csatemplateaddRow"+row+"-"+k).val() != "Y")
												{ 
												 $("#templateaddRow"+row+"-"+k).val(additionstr);
											     $("#templatedefaddRow"+row+"-"+k).val(additionstr);
												}

											  $("#templateReqaddRow"+row+"-"+k).val(templateAttr.substr(2, 1));
											  K=k++;	
									 	  }
								 	}
							 } 
	 							 
						}  
				});  //ajax end 
	    }	
	}; 

	function addRow() {
		
		var row = parseInt($("#countRow").val());
		//alert("row "+ row);
		inrStr="";
		inrStr = '<input type="hidden" id="template_id_'+row+'" name="template_name_'+row+'" value="">'
		inrStr = '<input type="hidden" id="htemplate_id_'+row+'" name="htemplate_name_'+row+'" value="">'
		inrStr = inrStr + '<input type="hidden" id="item_id_'+row+'" name="item_name_'+row+'" value="">'
		inrStr = inrStr + '<input type="hidden" id="item_type'+row+'" name="item_type_'+row+'" value="">'
		inrStr = inrStr + '<select Style="width:240px;" class="required" name="canonTemplateNames_'+row+'" onchange=callTemplateChange("canonTemplateNames_'+row+'",'+row+') id="canonTemplateNames_'+row+'"  class="canonTemplateClassNew" >'
		//alert("row 1" );
		inrStr = inrStr + '<option value="-1"></option>'
		//alert("row 2" );
     	<% 	//templateNameList = (ArrayList)templateList[0];
     		templateNameList = (ArrayList)CattemplateList[0];
      	for(int i=0;i<templateNameList.size();i++) {  	
			String templateName =(String)templateNameList.get(i); 
		%>	
			 inrStr = inrStr + '<option value="<%=templateName%>"><%=templateName%></option>'
		<%
			} 
		%> 
		//alert("row 3" );
		inrStr = inrStr +'</select>'
        //alert("inrStr " +inrStr);
		chkStr = '<input type="checkbox" id="mercuryind'+row+'" value="" class="select_item" name="mercuryind'+row+'">'
		inraddStr="";
		
		<%
		
		for(int k=0;k<addtionalAttList;k++){
			CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(k);
		%>
    	
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="templateaddRow'+row+'-'+'<%=k%>" name="templateaddRowName'+row+'-'+'<%=k%>">'
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="templateReqaddRow'+row+'-'+'<%=k%>" name="templateReqaddRowName'+row+'-'+'<%=k%>">'
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="templatedefaddRow'+row+'-'+'<%=k%>" name="templatedefaddRowName'+row+'-'+'<%=k%>">'
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="csatemplateaddRow'+row+'-'+'<%=k%>" name="csatemplateaddRowName'+row+'-'+'<%=k%>" value="">'
    	<%
		}  
		%>
		
		//alert("row inraddStr " +inraddStr);
        //return recordstr;
		$('#templateTable').dataTable().fnAddData( [
			'<input type="checkbox" class="select_item" name="select_item">',
			inrStr,
			chkStr,
	     	<% 	 for(int i=0;i<templateHeaderList.size();i++) {  	
		     		CanonE008ItemProcessDAO.attributeheaderInfo tempHeader = (CanonE008ItemProcessDAO.attributeheaderInfo)templateHeaderList.get(i);
		     		
					if(tempHeader.getLovFlag().equals("Y"))
					{	
							//String strvalidfeildname = "templateRowName"+i;
					
						if ((!tempHeader.getE008WBtblMap().equals("SUPPLIER")) && 
								(!tempHeader.getE008WBtblMap().equals("PRODUCT_LEVEL5")) &&
								(!tempHeader.getE008WBtblMap().equals("SERVICE_MODEL")) && 
								(!tempHeader.getE008WBtblMap().equals("MATERIAL_GROUP1")) && 
								(!tempHeader.getE008WBtblMap().equals("DEFAULT_SRC_WH")) &&
								(!tempHeader.getE008WBtblMap().equals("MARKETING_MODEL")) &&
								(!tempHeader.getE008WBtblMap().equals("TARIFF_CODE")))  
						{
							if ((tempHeader.getE008WBtblMap().equals("MARKETING_MODEL")))
							{
								  
			%>
								'<div class=\"select-editable\"><select name=\"modtemplateRowName'+row+'-'+'<%=i%>\"id=\"modtemplateRowName'+row+'-'+'<%=i%>\" onchange = \"this.nextElementSibling.value=this.value\" onfocus=\"changeFunc(\'templateRowName'+row+'-'+<%=i%>+'\',\'<%=tempHeader.getE008WBtblMap()%>\',\'\','+row+');\" class=\"canonTemplateClassNew\" >'+
								'<option value="-1"></option>'+
								'<input type="text" id="templateRowName'+row+'-'+'<%=i%>" name="templateRowName'+row+'-'+'<%=i%>" value=""></div>'+
								
							<% } else { if (tempHeader.getE008WBtblMap().equals("SUPPLIER_SITE")) 
											{
							%>
			
							'<select name=\"templateRowName'+row+'-'+'<%=i%>\"id=\"templateRowName'+row+'-'+'<%=i%>\" onfocus=\"changeFuncSupplier(\'templateRowName'+row+'-'+<%=i%>+'\',\'<%=tempHeader.getE008WBtblMap()%>\',\'\','+row+');\" class=\"canonTemplateClassNew\" >'+
							'<option value="-1"></option>'+
							'<input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value="">'+
			            	
			            	
						<% } else { %>
							
					'<select name=\"templateRowName'+row+'-'+'<%=i%>\"id=\"templateRowName'+row+'-'+'<%=i%>\" onfocus=\"changeFunc(\'templateRowName'+row+'-'+<%=i%>+'\',\'<%=tempHeader.getE008WBtblMap()%>\',\'\','+row+');\" class=\"canonTemplateClassNew\" >'+
					'<option value="-1"></option>'+
					'<input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value="">'+
					
					<% if (tempHeader.getE008WBtblMap().equals("PRODUCT_LEVEL3"))
	            	{	
	            	%>
            		'<input type=\"hidden\" id=\"product_level3_'+row+'\" name=\"product_level3_'+row+'\" value=\"'+'templateRowName'+row+'-'+'<%=i%>\" >'+
	            	<% } %>
					
			<%
					  			}	
						} } else {
			%>		'<div id=\"searchBox'+row+'<%=i%>\">'+
					'<table>'+
					'<td style=\"padding:2px;\">'+
			    		'<input style=\" display:none;\" class=\"inTxt\" type=\"text\" id=\"templateRowName'+row+'-'+'<%=i%>\"  name=\"templateRowName'+row+'-'+'<%=i%>\">'+
		    			'<input class=\"inTxt\" type=\"text\"  onchange=\"checkemptyChange('+row+',<%=i%>,\'<%=tempHeader.getE008WBtblMap()%>\')\" id=\"default_text'+row+'-'+'<%=i%>\"  name=\"default_text'+row+'-'+'<%=i%>\" VALUE=\"\">'+
		    		'</td>'+	 
            	    '<td style=\"padding:2px;\">'+
						'<button type=\"button\" id=\"find'+row+'<%=i%>\" onclick=\"callChange('+row+',<%=i%>,\'<%=tempHeader.getE008WBtblMap()%>\',\'\')\"><img id=\"myImg\" src=\"<%=ctxPath%>/e008/images/canonSearch.png\"></button>' +           	    	
					'</td>'+ 
	            	'</table>'+  
	            	'</div>'+
	            	'<div id=\"searchList'+row+'<%=i%>\" style=\"display:none;\" >'+
	            	'<select name=\"'+'<%=i%>templateRowName'+row+'-'+'<%=i%>\"id=\"'+'<%=i%>templateRowName'+row+'-'+'<%=i%>\" onchange=\"callListChange(\''+'<%=i%>templateRowName'+row+'-'+<%=i%>+'\',<%=i%>,'+row+')\" class=\"canonTemplateClassNew\" >'+
					'<option value="-1"></option>'+
            		'</div>'+
					<% if (tempHeader.getE008WBtblMap().equals("SUPPLIER"))
	            	{	
	            	%>
            		'<input type=\"hidden\" id=\"item_supplier'+row+'\" name=\"item_supplier_'+row+'\" value=\"'+'<%=i%>templateRowName'+row+'-'+'<%=i%>\" >'+
	            	<% } %>
            	    
			<% } %>			
					//alert("row 3" ); 
					'</select>',
			 <%
				}
				else
				{
			%>	
			
				<% if (tempHeader.getE008WBtblMap().equals("ITEM_NUMBER")) { 
					%>	
						'<table><td style="padding:2px;"><input type="text" id="templateRowName'+row+'-'+'<%=i%>" name="templateRowName'+row+'-'+'<%=i%>"><input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value=""></td><td style="padding:2px;"><button type="button" id="checkcusa'+row+'<%=i%>" onclick="callcusa(<%=i%>,'+row+')"><img id="myImg" src="<%=ctxPath%>/e008/images/get.png"></button></td></table>',
				<%
					}
				else
				{
				%> 
			
		        '<input type="text" id="templateRowName'+row+'-'+'<%=i%>" name="templateRowName'+row+'-'+'<%=i%>"><input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value="">',
	    	<%  } 
				
			    } %> 

			<%
    		} 
   			%> 
   			inraddStr,
   			'<a id="'+row+'" onclick="assignId('+row+');" data-toggle="modal" data-target="#myModal" class="dt-button"><span>Additional444</span></a>',
   			<%-- '<input type="text" id="templateaddRow'+row+'<%=templateHeaderList.size()%>" name="templateaddRowName'+row+'<%=templateHeaderList.size()%>">' --%>
	       ] );
		//alert("row 5" );   style="width: 10px;display:none;"
	    counter++;  
	    //alert("row 6" );
	    row = row +1  ;
	    //alert("newrow" + row)
	    setIdVal("countRow",row)
	    
	} 


	function addcusaRow(itemNo) {
		
		var row = parseInt($("#countRow").val());
		//alert("row "+ row);
		inrStr="";
		inrStr = '<input type="hidden" id="template_id_'+row+'" name="template_name_'+row+'" value="">'
		inrStr = '<input type="hidden" id="htemplate_id_'+row+'" name="htemplate_name_'+row+'" value="">'
		inrStr = inrStr + '<input type="hidden" id="item_id_'+row+'" name="item_name_'+row+'" value="">'
		inrStr = inrStr + '<input type="hidden" id="item_type'+row+'" name="item_type_'+row+'" value="">'
		inrStr = inrStr + '<select Style="width:240px;"  class="required" name="canonTemplateNames_'+row+'" onchange=callTemplateChange("canonTemplateNames_'+row+'",'+row+') id="canonTemplateNames_'+row+'"  class="canonTemplateClassNew" >'
		//alert("row 1" );
		inrStr = inrStr + '<option value="-1"></option>'
		//alert("row 2" );
     	<% 	//templateNameList = (ArrayList)templateList[0];
     		templateNameList = (ArrayList)CattemplateList[0];
      	for(int i=0;i<templateNameList.size();i++) {  	
			String templateName =(String)templateNameList.get(i); 
		%>	
			 inrStr = inrStr + '<option value="<%=templateName%>"><%=templateName%></option>'
		<%
			} 
		%> 
		//alert("row 3" );
		inrStr = inrStr +'</select>'
        //alert("inrStr " +inrStr);
		chkStr = '<input type="checkbox" id="mercuryind'+row+'" value="" class="select_item" name="mercuryind'+row+'">'
		inraddStr="";
		
		<%
		
		for(int k=0;k<addtionalAttList;k++){
			CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(k);
		%>
    	
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="templateaddRow'+row+'-'+'<%=k%>" name="templateaddRowName'+row+'-'+'<%=k%>">'
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="templateReqaddRow'+row+'-'+'<%=k%>" name="templateReqaddRowName'+row+'-'+'<%=k%>">'
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="templatedefaddRow'+row+'-'+'<%=k%>" name="templatedefaddRowName'+row+'-'+'<%=k%>">'
		inraddStr = inraddStr + '<input style="width: 20px; display:none;" type="text" id="csatemplateaddRow'+row+'-'+'<%=k%>" name="csatemplateaddRowName'+row+'-'+'<%=k%>" value="">'

    	<%
		}  
		%>
		
		//alert("row inraddStr " +inraddStr);
        //return recordstr;
		$('#templateTable').dataTable().fnAddData( [
			'<input type="checkbox" class="select_item" name="select_item">',
			inrStr,	
			chkStr,
	     	<% 	 for(int i=0;i<templateHeaderList.size();i++) {  	
		     		CanonE008ItemProcessDAO.attributeheaderInfo tempHeader = (CanonE008ItemProcessDAO.attributeheaderInfo)templateHeaderList.get(i);
		     		
					if(tempHeader.getLovFlag().equals("Y"))
					{	
							//String strvalidfeildname = "templateRowName"+i;
					
						if ((!tempHeader.getE008WBtblMap().equals("SUPPLIER")) && 
								(!tempHeader.getE008WBtblMap().equals("PRODUCT_LEVEL5")) &&
								(!tempHeader.getE008WBtblMap().equals("SERVICE_MODEL")) && 
								(!tempHeader.getE008WBtblMap().equals("MATERIAL_GROUP1")) && 
								(!tempHeader.getE008WBtblMap().equals("DEFAULT_SRC_WH")) &&
								(!tempHeader.getE008WBtblMap().equals("MARKETING_MODEL")) &&
								(!tempHeader.getE008WBtblMap().equals("TARIFF_CODE")))  
						{
					  

							if ((tempHeader.getE008WBtblMap().equals("MARKETING_MODEL")))
							{
								  
			%>
								'<div class=\"select-editable\"><select name=\"modtemplateRowName'+row+'-'+'<%=i%>\"id=\"modtemplateRowName'+row+'-'+'<%=i%>\" onchange = \"this.nextElementSibling.value=this.value\" onfocus=\"changeFunc(\'templateRowName'+row+'-'+''+<%=i%>+'\',\'<%=tempHeader.getE008WBtblMap()%>\',\'\','+row+');\" class=\"canonTemplateClassNew\" >'+
								'<option value="-1"></option>'+
								'<input type="text" id="templateRowName'+row+'-'+'<%=i%>" name="templateRowName'+row+'-'+'<%=i%>" value=""></div>'+
								
							<% } else { if (tempHeader.getE008WBtblMap().equals("SUPPLIER_SITE")) 
											{
							%>
			
							'<select name=\"templateRowName'+row+'-'+'<%=i%>\"id=\"templateRowName'+row+'-'+'<%=i%>\" onfocus=\"changeFuncSupplier(\'templateRowName'+row+'-'+<%=i%>+'\',\'<%=tempHeader.getE008WBtblMap()%>\',\'\','+row+');\" class=\"canonTemplateClassNew\" >'+
							'<option value="-1"></option>'+
							'<input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value="">'+
			            	
			            	
						<% } else { %>
							
					'<select name=\"templateRowName'+row+'-'+'<%=i%>\"id=\"templateRowName'+row+'-'+'<%=i%>\" onfocus=\"changeFunc(\'templateRowName'+row+'-'+<%=i%>+'\',\'<%=tempHeader.getE008WBtblMap()%>\',\'\','+row+');\" class=\"canonTemplateClassNew\" >'+
					'<option value="-1"></option>'+
					'<input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value="">'+
					
					<% if (tempHeader.getE008WBtblMap().equals("PRODUCT_LEVEL3"))
	            	{	
	            	%>
            		'<input type=\"hidden\" id=\"product_level3_'+row+'\" name=\"product_level3_'+row+'\" value=\"'+'templateRowName'+row+'-'+'<%=i%>\" >'+
	            	<% } %>					
					
			<%
					  			}	
					}
				} else {
			%>		'<div id=\"searchBox'+row+'<%=i%>\">'+
					'<table>'+
					'<td style=\"padding:2px;\">'+
			    		'<input style=\" display:none;\" class=\"inTxt\" type=\"text\" id=\"templateRowName'+row+'-'+'<%=i%>\"  name=\"templateRowName'+row+'-'+'<%=i%>\">'+
		    			'<input class=\"inTxt\" type=\"text\"  onchange=\"checkemptyChange('+row+',<%=i%>,\'<%=tempHeader.getE008WBtblMap()%>\')\" id=\"default_text'+row+'-'+'<%=i%>\"  name=\"default_text'+row+'-'+'<%=i%>\" VALUE=\"\">'+
		    		'</td>'+	
            	    '<td style=\"padding:2px;\">'+
						'<button type=\"button\" id=\"find'+row+'<%=i%>\" onclick=\"callChange('+row+',<%=i%>,\'<%=tempHeader.getE008WBtblMap()%>\',\'\')\"><img id=\"myImg\" src=\"<%=ctxPath%>/e008/images/canonSearch.png\"></button>' +           	    	
					'</td>'+ 
	            	'</table>'+  
	            	'</div>'+
	            	'<div id=\"searchList'+row+'<%=i%>\" style=\"display:none;\" >'+
	            	'<select name=\"'+'<%=i%>templateRowName'+row+'-'+'<%=i%>\"id=\"'+'<%=i%>templateRowName'+row+'-'+'<%=i%>\" onchange=\"callListChange(\''+'<%=i%>templateRowName'+row+'-'+<%=i%>+'\',<%=i%>,'+row+')\" class=\"canonTemplateClassNew\" >'+
					'<option value="-1"></option>'+
            		'</div>'+
            		<% if (tempHeader.getE008WBtblMap().equals("SUPPLIER"))
	            	{	
	            	%>
            		'<input type=\"hidden\" id=\"item_supplier'+row+'\" name=\"item_supplier_'+row+'\" value=\"'+'<%=i%>templateRowName'+row+'-'+'<%=i%>\" >'+
	            	<% } %>            	    
			<% } %>			
					//alert("row 3" ); 
					'</select>',
				
			 <%
				}
				else
				{
			%>	
			
				<% if (tempHeader.getE008WBtblMap().equals("ITEM_NUMBER")) { 
					%>	
						'<table><td style="padding:2px;"><input type="text" id="templateRowName'+row+'-'+'<%=i%>" name="templateRowName'+row+'-'+'<%=i%>" value='+ itemNo + ' ><input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value="Y"></td><td style="padding:2px;"><button type="button" id="checkcusa'+row+'<%=i%>" onclick="callcusa(<%=i%>,'+row+')"><img id="myImg" src="<%=ctxPath%>/e008/images/get.png"></button></td></table>',
				<%
					}
				else
				{
				%> 
			
		        '<input type="text" id="templateRowName'+row+'-'+'<%=i%>" name="templateRowName'+row+'-'+'<%=i%>"><input style=\"display:none;\" type="text" id="csatemplateRowName'+row+'-'+'<%=i%>" name="csatemplateRowName'+row+'-'+'<%=i%>" value="">',
	    	<%  } 
				
			    } %> 

			
			<%
    		} 
   			%> 
   			inraddStr,
   			'<a id="'+row+'" onclick="assignId('+row+');" data-toggle="modal" data-target="#myModal" class="dt-button"><span>Additional444</span></a>',
   			<%-- '<input type="text" id="templateaddRow'+row+'<%=templateHeaderList.size()%>" name="templateaddRowName'+row+'<%=templateHeaderList.size()%>">' --%>
	       ] );
		//alert("row 5" );   style="width: 10px;display:none;"
	    counter++;  
	    //alert("row 6" );
	    row = row +1  ;
	    
	    setIdVal("countRow",row);
	    
	    callcusacompFunc(row-1,itemNo);
	    
	} 
	
	
	function callcusacompFunc(row,defaultvalue) {                
		        
				//alert("defaultvalue "+defaultvalue);    	
				var url= '<%=ctxPath%>/e008/jsp/CanonE008GetSelectedTemplateAttributes.jsp?';
		        //alert("new row "+row);         
		       	 $.ajax({  
							type: "POST",
							async:false,						
							url: url,
							data: 'action=GETCUSAITEM&cusaItem='+defaultvalue.trim() +'&projectNumber=' ,
							complete: function(data)
								{  
									x =((data.responseText).replace(/^\s+|\s+$/g,''));
									//alert(" x- " +x);
									res=x.split("@@");
									templateAttrVal=res[0].split(",");			
									 //alert("templateAttrVal Lenght" + templateAttrVal);
									 var k=0;
									 var n=0;
									  for(var i=0;i<templateAttrVal.length;i++)
									 {								 
										 var templateAttr=templateAttrVal[i];
										 //alert(" templateAttr-" +templateAttr);
										 templateAttr=templateAttr.replace('[','').replace(']','');

										 if($.trim(templateAttr)=="null"||$.trim(templateAttr)=="[null"||$.trim(templateAttr)=="null]")	 
										 	 templateAttr="";									 								 
										
										 //alert ("templateAttr "+templateAttr);
										
										 if (i==0) {
											 $("#template_id_"+row).val(templateAttr.substr(1,templateAttr.length) );
											 }
										 else {
											 n=i-1; 
											 var listcode="";
											 var listname = "";
											 var lovname = "";
											 if (templateAttr.substr(1, 1)=="M") { 
													 
												 	 if (templateAttr.substr(3, 1)!="Y")
												 	 {	
												 		 if (templateAttr.substr(4,templateAttr.length)!="") 
												 	 		{
												 			  $("#templateRowName"+row+"-"+n).val(templateAttr.substr(4,templateAttr.length));
												 		      $("#csatemplateRowName"+row+'-'+n).val("Y");
												 	 		} 
												 	 }
												 	 else
												 	 {
													  	//alert ("templateAttr11 "+templateAttr);
												 		var listdetails = templateAttr.substr(4,templateAttr.length);
													  	//alert("listdetails "+listdetails);
													  	var reslist=listdetails.split("**");
													  	if (reslist!="")
													  	{	listcode=reslist[0];
													  		listname = reslist[1];
													  		lovname = reslist[2];
													  	}
													 	
													  	if (listcode!="")
													  	{	
	 													  	if ((lovname!="SUPPLIER") && 
	 																(lovname!="PRODUCT_LEVEL5") &&
	 																(lovname!="SERVICE_MODEL") &&
	 																(lovname!="MARKETING_MODEL") &&
	 																(lovname!="MATERIAL_GROUP1") && 
	 																(lovname!="DEFAULT_SRC_WH") &&
	 																(lovname!="TARIFF_CODE"))
	 													  	{	
	 													  		$("#templateRowName"+row+"-"+n).empty();
	 												 			var	mainoption = $('<option selected ="" value="'+ listcode +'">'+listname+'</option>');
	 		 					  					        	$("#templateRowName"+row+"-"+n).append(mainoption);
	 													  	}
	 													  	else
	 													  	{
	 													  		$("#templateRowName"+row+"-"+n).val(listcode);
	 													  		$("#default_text"+row+'-'+n).val(listname);
	 													  		
	 													  		/*if ((templateAttr.substr(2, 1)=="Y") && (listname!=""))
	 													  		{
	 													  			$("#default_text"+row+n).addClass("noneditrequired");
	 													    	 	$("#default_text"+row+n).prop("disabled", true);
	 													  		}
	 													  		else if ((templateAttr.substr(2, 1)=="Y") && (listname==""))
	 													  			$("#default_text"+row+n).addClass("required");
	 													  		else
	 													  			$("#default_text"+row+n).addClass("notrequired");
	 													  		*/
	 													  	}
	 													  	$("#csatemplateRowName"+row+'-'+n).val("Y");	
													  	}	 													  	
												 	 }
												 	 
													 /*if ((templateAttr.substr(2, 1)=="Y") && (listname!="")) //(templateAttr.substr(4,templateAttr.length)!=""))
													 	{
														 $("#templateRowName"+row+n).addClass("noneditrequired");
													     $("#templateRowName"+row+n).prop("disabled", true); 
													    }
													 else if ((templateAttr.substr(2, 1)=="Y") && (listname=="")) //(templateAttr.substr(4,templateAttr.length)==""))
													 		$("#templateRowName"+row+n).addClass("required");
													 		
													 else
															 
														$("#templateRowName"+row+n).addClass("notrequired");
													 */
												 	}
												 else {
													  // alert("K "+k); 
													  //alert("value "+templateAttr.substr(3,templateAttr.length));
													  var additionstr = "";
													  var additionlistcode ="";
												      if(templateAttr.substr(4,templateAttr.length)=="NULL]")
														  additionstr="";
													  else 
														  additionstr = templateAttr.substr(4,templateAttr.length);
												      
												      //alert("additionstr " + additionstr);
												      
												      if (additionstr!="")
												      {
												    	  var resaddlist=additionstr.split("**");
	 													  if (resaddlist!="")
	 													  {	
	 														  additionlistcode=resaddlist[0];
	 													  }
	 													  	  
												    	  
	 													  if (additionlistcode!="")
	 													  { 
															  $("#templateaddRow"+row+'-'+k).val(additionstr);
															  $("#csatemplateaddRow"+row+'-'+k).val("Y");
		 													  $("#templatedefaddRow"+row+'-'+k).val(additionstr);
		 													  $("#templateReqaddRow"+row+'-'+k).val(templateAttr.substr(2, 1));
	 													  }
												      }	  
													  K=k++;	
											 	  }
										 	}
										
										
									 } 
								}  
						});  //ajax end 
			}; 

	
    function deleteItems() {
        var item_selection = $( [] ).add($(".select_item"));
        //alert("item_selection "+ item_selection);
        var handler=makeSingleErrorHandler();
        checkCheckedAtLeast( item_selection, 1, "Please select at least one item.", handler );
        if(!handler.valid()){
            handler.showError();
        }else{
            var html='<div id="dialog-confirm" class="model-table" title="Delete item?">'+
            '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Selected item(s) will be deleted. Are you sure?</p></div>';

            $( html ).dialog({
                  resizable: false,
                  height:150,
                  modal: true,
                  open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
                  buttons: {
                    Cancel: function() {
                      $( this ).dialog( "close" );
                    },
                    "Yes": function() {
                        $("#action").val('delete_item');
                        var url = $("#projectForm").attr('action');
                        $( this ).dialog( "close" );
                        blockUsrInterface();
    
                        $.ajax({
                          type: "POST",
                          url: url,
                          data: getFormData(),
                          success: function(data){
                            unBlockUsrInterface();
                            if(error_message(data)){
                                show_error("Failed to delete selected item(s).",data);
                            }else{
                                $("#divCanonE008Main").html("");
                                $("#divCanonE008Main").html(data);
                                //update_status("Item(s) has been deleted.");
                            }
                          },
                          error:function(request){
                                unBlockUsrInterface();
                                var response=request.responseText;
                                show_error("Failed to delete selected item(s).",response);
                          }
                        });
                    }
                  }
            });
        }
    
    }
    
    function uploadItems()
    {
        modelName = "#uploadExcelDiv";
        //alert(modelName);
        var url = "<%=ctxPath%>/e008/jsp/CanonE008ExcelProcess.jsp?action=select&project_number=<%=p_project_number%>&userid=<%=loginUserID%>";
        //alert(url);
        $(modelName).dialog({
                      height: 300,
                      title: "Select file",
                      modal: true ,
             		  autoOpen :false,
                      width: 600,
             		  resizable: false,  
             		  open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
                      buttons: { "Close": function() {  
                                     var needrefresh=$("#submitButton").data("need-refresh");
                                     $(modelName).html("");
                                     $(this).dialog("close");
                                     $(this).dialog("destroy");
                                     if(needrefresh){
                                         // TODO
                                         newrefreshPage(<%=p_project_number%>);
                                     }
                                 }

                    }					
                 });
        //alert("22");
          $.ajax({
                     url: url,
             		 cache: false,
                     success: function(data){     
                         //hidePleaseWait(); 
                        //alert("22 "+data); 
                       $(modelName).html(data);
                       $("#readFrm").children("input").clone(true).appendTo("#submitForm");
                     }             
                 });
       //alert("333");
       $(modelName).dialog("open");
    }

    function deleteProject()
    {
        var projectNumber=<%=p_project_number%>;
        var html='<div id="dialog-confirm" title="Delete project?">'+
        '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Project '+projectNumber+' will be deleted. Are you sure?</p></div>';
        
        $( html ).dialog({		
              resizable: false,
              height:140,
              modal: true,
              open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
              buttons: {
                Cancel: function() {
                  $( this ).dialog( "close" );
                },
                "Yes": function() {
                    $("#action").val('delete_project');
                    var url = $("#projectForm").attr('action');
                    blockUsrInterface();
                    $( this ).dialog( "close" );
                    $.ajax({
                      type: "POST",
                      url: url,
                      data: getFormData(),
                      success: function(msg){
                        unBlockUsrInterface();
                        //$( this ).dialog( "close" );
                        if(error_message(msg)){
                            show_error("Failed to delete the project.",msg);
                        }else{
                        var status="<div>Project "+projectNumber+" has been deleted.</div>";
                            $(status).dialog({
                              resizable: false,
                              height:140,
                              modal: true,
                              buttons: {
                                "OK": function() {
                                  $( this ).dialog( "close" );
                                  window.location.replace("CanonE008ItemSetupSearch.jsp?");
                                }
                              }
                            });
                        }
                      },
                      error:function(request){
                            unBlockUsrInterface();
                            $( this ).dialog( "close" );
                            var response=request.responseText;
                            show_error("Failed to delete the project.",response);
                      }
                    });                        
                }
              }
            });
    }

    function checkStatusMonitor(continueHandler){
        var url = $("#projectForm").attr('action');

        $.ajax({
          type: "GET",
          url: url,
          data: $("#project_number").serialize()+"&action=check_status_monitor",
          dataType: "json",
          success: function(result){
             if(error_message(result)){
                var handler=makeSingleErrorHandler();
                show_error("Failed to check status monitor.",result);
            }else{ 
                if("Y"===result.check_status_monitor){
                    continueHandler();
                }else if("N"==result.check_status_monitor){
                    unBlockUsrInterface();
                    show_error("Workflow is in progress.");
                }else{
                    unBlockUsrInterface();
                    show_error("Invalid result",result);
                }
             } 
          },
          error:function(request){
                unBlockUsrInterface();
                var response=request.responseText;
                var handler=makeSingleErrorHandler();
                //alert("response "+response);
                show_error("Failed to check status monitor1.",response);
          }
        });
    }
    
    
    function error_message(data){
        var re=new RegExp("^<!--ERROR_MESSAGE(.*)ERROR_MESSAGE-->","m");
        var match=re.exec(data);
        if (match){
            return match[1];
        }else{
            return "";
        }
    }    
    
    function makeSingleErrorHandler(){
        var error;
        var bValid=true;
        return {
            handle:function(t,o){
                error={"component":o,"message":t};
                bValid=false;
            },
            showError:function(){
                default_alert_handler(error.message,error.component);
            },
            done:function(){
            },
            valid:function(){
                return bValid;
            }
        }
    }

    function makeMultiErrorHandler(errs){
        var errors=[];
        if(errs){
            errors = errors.concat(errs);
        }
        var bValid=true;
        var obj = {
            handle:function(t,o){
                errors.push({"component":o,"message":t});
            },
            done:function(){
                bValid=errors.length==0;
            },
            valid:function(){
                return bValid;
            },
            showError:function(){
                this.showErrors_(errors);
            }
        };
        
        obj.showErrors_= function (errs) {
            clear_status();
            
            var messages='<ul class="message">';
            $(errs).each(function(i,error){
                //alert("error.component "+ error.component);
            	if (error.component) error.component.addClass( "ui-validation-error");
                messages+="<li>";
                console.log("Testing err - "+($(error.component).data("item-code")))
                if($(error.component).data("item-code")){
                    messages+="Canon Item#->"+$(error.component).data("item-code")+" ";
                }
                messages+= error.message+"</li>";
            });
            messages+="</ul>";
            var html=
                '<div title="Alert">'
                    + messages 
                    +'<span style="font-weight: normal; color: black">Validation failed. Please fix the data.</span>'
                '</div>';
            $("#validatation-DataDiv").html("");
            $("#validatation-DataDiv").html(html);
            $("#validatation-DataDiv").dialog({
              modal: true,
              width:600,
              height:320,
              buttons: {
                Ok: function() {
                  $( this ).dialog( "close" );
                }
              }
            });
        }
        return obj;
    }
    
    $.makeMultiErrorHandler=makeMultiErrorHandler;    
    var defaultSingleErrorHandler=makeSingleErrorHandler();
    function show_error(errmsg,response){
        defaultSingleErrorHandler.handle(errmsg);
        defaultSingleErrorHandler.showError();
        <% if(IS_DEV){ %>
            if(typeof response != "undefined"){
              $("#project_status" ).append('&nbsp;<a class="jsp_error" href="#">View error detail (visible only in dev environment)</a>');
              $("#project_status .jsp_error").click(function (){
                 var newWindow = window.open("","Error","width=400,height=500,scrollbars=1,resizable=1")
                 newWindow .document.open()
                 newWindow .document.write(response)
                 newWindow .document.close()
              });
            }
        <%} %>    
    }

    function checkCheckedAtLeast(checkboxes, atLeastNum, errormsg, handler) {
        var len=checkboxes.filter(function(index){return this.checked}).length;
        if ( len < atLeastNum) {
          checkboxes.addClass( "ui-validation-error" );
          show_alert(errormsg, handler);
          return false;
        } else {
          return true;
        }
      }

      function empty(str){
          return !str || !/[^\s]+/.test(str);
      }

      function show_alert(t, handler, o) {
          handler.handle(t,o);
      }    

      function default_alert_handler(t,o) {
          clear_status();
          if(o){
              o.addClass( "ui-validation-error" );
          }
          var html=
              '<div title="Alert">'
                  +'<p>'
                  +    '<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0;"></span>'
                  + t 
                  + '</p>'+
              '</div>';
          $( html ).dialog({
            modal: true,
            buttons: {
              Ok: function() {
                $( this ).dialog( "close" );
              }
            }
          });
      }

      function clear_status() {
          var project_status = $("#project_status" );
          project_status
            .text("")
            .removeClass( "ui-state-highlight" );
          $(".ui-validation-error").removeClass("ui-validation-error");
        }      
      
    
    function validateInput(handler){
        $(".item_code").each(function(){
            var newItemCode=$(this).val();
            if(newItemCode!=$(this).data("item-code")){
                var oldItemCode=$(this).data("item-code");
                $(this).data("item-code",newItemCode);
                $("input[data-item-code='"+oldItemCode+"']").each(function(){
                    $(this).data("item-code",newItemCode);
                });
            }
            
        });
        //checkRequired($("#project_name"),"Project Name", handler) && checkMaxLength( $("#project_name"), "Project Name", 40, handler);
        //handler.valid() && checkRequired($("#project_description"),"Project Description", handler) && checkMaxLength( $("#project_description"), "Project Description", 40, handler);
        //handler.valid() && checkItems("", handler);
        return handler;
    }	
	
    function showValidationError(errs){
        clear_status();
        var messages=' <ol class="message">';
        //console.log("aaa");
        $(errs).each(function(i,error){
        	
        	//$('#templateRow01').addClass("required ui-validation-error"); border: 2px solid #cd0a0a;
        	//$(error.component).css({'borderColor':'#cd0a0a','borderStyle':'solid','borderWidth':'3px'});
        	if (error.component) 
        		{
        		//alert("error.component "+ (error.field_name).substr(0,17));
        		//alert("error.component " + error.component.val())
        		error.component.css({'borderColor':'#cd0a0a','borderStyle':'solid','borderWidth':'3px'});
        		if ((error.field_name).substr(0,17)=="templateReqaddRow")
        			error.component.val(error.component.val()+'E');
        		}
        	//if (error.component) error.component.addClass( "required ui-validation-error");
            messages+=" <li>";
            if(!error.field_name && $(error.component).data("item-code")){
                messages+="Canon Item#->"+$(error.component).data("item-code")+" ";
            }
            messages+= error.message+"</li>";
        });
        //console.log("bbb");
        messages+=" </ol>";
        var html=
            '<div title="Failure Alert">'
                +'<span style="font-weight: normal; color: black">Project validation failed.</span>'
                + messages 
            '</div>';
           console.log("html " + html);     
        $("#validatation-DataDiv").html("");
        $("#validatation-DataDiv").html(html);
        $("#validatation-DataDiv").dialog({
          modal: true,
          width:640,
          height:500,
          open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
          buttons: {
            "Close": function() {
              $( this ).dialog( "close" );
            }
          }
        });
        
    }
	
    function saveProject()
    {
         clear_status();
        var handler=makeMultiErrorHandler();
        validateInput(handler);
        handler.done();
        if(!handler.valid()){
            handler.showError();
            return;
        } 
        $('input, select').prop('disabled', false);
        
        var project_no=getIdVal("projectNumber");
        //alert("project_no "+project_no);
        
        var project_name=getIdVal("projectName");
        if (project_name=='')
      	{
				show_error("Please Enter the Project Name.");
				return;
      	}        
        
        var project_Typebox = document.getElementById("projectType");
        var project_Type = project_Typebox.options[project_Typebox.selectedIndex].value;
        //alert(project_Typebox.value);
        //alert(project_Type);
        if (project_Type=='00')
      	{
				show_error("Please Select the Project Type.");
				return;
      	}        
        
        var html='<div id="dialog-confirm" title="Submit For Save">'+
        '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Are you sure to Save the project?</p></div>';
        $( html ).dialog({
              resizable: false,
              height:150,
              modal: true,
              open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
              buttons: {
                Cancel: function() {
                  $( this ).dialog( "close" );
                },
                "Yes": function() {
                     /* $("#action").val('submit_project');
                    var url = $("#projectForm").attr('action');  */
                    $( this ).dialog( "close" );
                   // alert("project_no 11 "+project_no);
                    blockUsrInterface();
                    //alert("project_no 222 "+project_no);
                    var x ;
                    $("#action").val('SAVE');
                	$.ajax({  
      						type: "POST",
      						async:false,						
      						url: 'CanonE008ItemWorkbenchProcess.jsp', 
      						data: "action="+"SAVE"+"&cntRow="+getIdVal("countRow")+"&userId=999"+getFormData() ,
      						//data: getFormData() ,
      						complete: function(data)
      							{  
      								unBlockUsrInterface();
      		                        if(error_message(data)){
      		                            show_error("Failed to save the project.",data);
      		                        }else{

      		                        	x =((data.responseText).replace(/^\s+|\s+$/g,''));
      									//alert("resp" + x);
      									var res =x.split("@@@");
      									if (res[1]=='S')
      									{
      										//alert ("Project " + res[0])
      										var strproNo = res[0];
      										newrefreshPage(strproNo);
      										/* if (res[4]!='new')
      										{
      											validateProject();
      										} */
/*       										if (res[4]=='new')
      										{
      										refreshPage(strproNo);
      										}
 */      									}
      									
      		                        	
      		                        	//$("#divCanonE008Main").html("");
      		                            //$("#divCanonE008Main").html(data);
      		                            //update_status("The project has been saved.");
      		                        }
      		                      },
      		                      error:function(request){
      		                            unBlockUsrInterface();
      		                            var response=request.responseText;
      		                            //show_error("Failed to save the project.",response);
      		                      }
      					}); 
                	
                	
                }
              }
        });
    
    }	

    function saveAlertValidateProject(){
    	
        var html='<div id="dialog-confirm" title="Submit For Save">'+
        '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Is the Project changes Saved?</p></div>';
        $( html ).dialog({
              resizable: false,
              height:150,
              modal: true,
              open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
              buttons: {
                Cancel: function() {
                  $( this ).dialog( "close" );
                },
                "Yes": function() {
                			    $( this ).dialog( "close" );
						        blockUsrInterface();
						        //checkStatusMonitor(function(){
						            <%-- $("#action").val('<%=canModifyProject?"save_and_validate_project" : "validate_project" %>'); --%>
						            $("#action").val('save_and_validate_project');
						            var url = $("#projectForm").attr('action');
						            //alert("url-" + url);
						            $.ajax({
						              type: "POST",
						              url: url,
						              data: getFormData(),
						              dataType: "json",
						              success: function(errors){
						                unBlockUsrInterface();
						                console.log(errors);
						                if(error_message(errors)){
						                    var handler=makeSingleErrorHandler();
						                    show_error("Failed to validate project.",data);
						                }else{
						                    $(errors).each(function(i,error){
						                        var itemtr=$("#item_"+error.item_id);
						                        //console.log("error.item_id " + error.item_id);
						                        //console.log("itemtr "+ itemtr);
						                        //var error_field=$("#attributeRow2");  //templateRow02
						                        var error_field=$("#"+error.field_name);
						                        //var error_field=$("."+error.field_name.toLowerCase(),itemtr);
						                        //console.log("error_field "+ error_field);
						                        if (itemtr.length>0)
						                        {
						                        	console.log("22222");
						                        }
						                        if(error_field.length>0){
						                        	//console.log("11111");
						                        	error.component=error_field;
						                        }
						                    });
						                    var handler=makeMultiErrorHandler(errors);
						                    //console.log("wwww");
						                    handler.showErrors_= showValidationError;
						                    //console.log("qqqq");
						                     <%-- <%if (canModifyProject){ %> --%>
						                    validateInput(handler);
						                    <%-- <%} %> --%>
						                    handler.done();
						                    //console.log("yyyyy");
						                    if(!handler.valid()){
						                    	//console.log("eee");
						                    	handler.showError();
						                    	//console.log("rrr");
						                    }else{
						                    	console.log("ttt");
						                    	unBlockUsrInterface();
						                    	alert("Project validate successfully. ");
						                    	//null;
						                    	/* $("#validate-comment-dialog").dialog( "open" ); */
						                    } 
						                }
						              },
						              complete: function(){
						                unBlockUsrInterface();
						              },
						              error:function(request){
						                    unBlockUsrInterface();
						                    var response=request.responseText;
						                    var handler=makeSingleErrorHandler();
						                    show_error("Failed to validate project.",response);
						              }
						            });
						  }					            
              }
        });
    }

    function validateandApproveProject(){
        blockUsrInterface();
        //checkStatusMonitor(function(){
            $("#action").val('save_and_validate_project');
            var url = $("#projectForm").attr('action');
            //alert("url-" + url);
            $.ajax({
              type: "POST",
              url: url,
              data: getFormData(),
              dataType: "json",
              success: function(errors){
                unBlockUsrInterface();
                //console.log(errors);
                if(error_message(errors)){
                    var handler=makeSingleErrorHandler();
                    show_error("Failed to validate project.",data);
                }else{
                    $(errors).each(function(i,error){
                        var itemtr=$("#item_"+error.item_id);
                        //console.log("error.item_id " + error.item_id);
                        //console.log("itemtr "+ itemtr);
                        //var error_field=$("#attributeRow2");  //templateRow02
                        var error_field=$("#"+error.field_name);
                        //var error_field=$("."+error.field_name.toLowerCase(),itemtr);
                        //console.log("error_field "+ error_field);
                        if (itemtr.length>0)
                        {
                        	console.log("22222");
                        }
                        if(error_field.length>0){
                        	//console.log("11111");
                        	error.component=error_field;
                        }
                    });
                    var handler=makeMultiErrorHandler(errors);
                    //console.log("wwww");
                    handler.showErrors_= showValidationError;
                    //console.log("qqqq");
                     <%-- <%if (canModifyProject){ %> --%>
                    validateInput(handler);
                    <%-- <%} %> --%>
                    handler.done();
                    //console.log("yyyyy");
                    if(!handler.valid()){
                    	//console.log("eee");
                    	handler.showError();
                    	//console.log("rrr");
                    }else{
                    	//console.log("ttt");
                    	$("#approve-comment-dialog").dialog( "open" );
                    	//null;
                    	/* $("#validate-comment-dialog").dialog( "open" ); */
                    } 
                }
              },
              complete: function(){
            	  unBlockUsrInterface();
              },
              error:function(request){
                    unBlockUsrInterface();
                    var response=request.responseText;
                    var handler=makeSingleErrorHandler();
                    show_error("Failed to validate project.",response);
              }
            });
       // });
    }
      

    function validateandRejectProject(){
        blockUsrInterface();
        //checkStatusMonitor(function(){
            <%-- $("#action").val('<%=canModifyProject?"save_and_validate_project" : "validate_project" %>'); --%>
            $("#action").val('save_and_validate_project');
            var url = $("#projectForm").attr('action');
            //alert("url-" + url);
            $.ajax({
              type: "POST",
              url: url,
              data: getFormData(),
              dataType: "json",
              success: function(errors){
                unBlockUsrInterface();
                $("#reject-comment-dialog").dialog( "open" );
                //console.log(errors);
              },
              complete: function(){
            	  unBlockUsrInterface();
              },
              error:function(request){
                    unBlockUsrInterface();
                    var response=request.responseText;
                    var handler=makeSingleErrorHandler();
                    show_error("Failed to validate project.",response);
              }
            });
       // });
    }
      
    
    function validateProject(){
        blockUsrInterface();
        //checkStatusMonitor(function(){
            <%-- $("#action").val('<%=canModifyProject?"save_and_validate_project" : "validate_project" %>'); --%>
            $("#action").val('save_and_validate_project');
            var url = $("#projectForm").attr('action');
            //alert("url-" + url);
            $.ajax({
              type: "POST",
              url: url,
              data: getFormData(),
              dataType: "json",
              success: function(errors){
                unBlockUsrInterface();
                console.log(errors);
                if(error_message(errors)){
                    var handler=makeSingleErrorHandler();
                    show_error("Failed to validate project.",data);
                }else{
                    $(errors).each(function(i,error){
                        var itemtr=$("#item_"+error.item_id);
                        //console.log("error.item_id " + error.item_id);
                        //console.log("itemtr "+ itemtr);
                        //var error_field=$("#attributeRow2");  //templateRow02
                        var error_field=$("#"+error.field_name);
                        //var error_field=$("."+error.field_name.toLowerCase(),itemtr);
                        //console.log("error_field "+ error_field);
                        if (itemtr.length>0)
                        {
                        	console.log("22222");
                        }
                        if(error_field.length>0){
                        	console.log("11111");
                        	error.component=error_field;
                        }
                    });
                    var handler=makeMultiErrorHandler(errors);
                    //console.log("wwww");
                    handler.showErrors_= showValidationError;
                    //console.log("qqqq");
                     <%-- <%if (canModifyProject){ %> --%>
                    validateInput(handler);
                    <%-- <%} %> --%>
                    handler.done();
                   // console.log("yyyyy");
                    if(!handler.valid()){
                    	console.log("eee");
                    	handler.showError();
                    	//console.log("rrr");
                    }else{
                    	console.log("ttt");
                    	//null;
                    	/* $("#validate-comment-dialog").dialog( "open" ); */
                    } 
                }
              },
              complete: function(){
            	  unBlockUsrInterface();
              },
              error:function(request){
                    unBlockUsrInterface();
                    var response=request.responseText;
                    var handler=makeSingleErrorHandler();
                    show_error("Failed to validate project.",response);
              }
            });
       // });
    }
	
    function submitProject()
    {
        /* clear_status();
        var handler=makeMultiErrorHandler();
        validateInput(handler);
        handler.done();
        if(!handler.valid()){
            handler.showError();
            return;
        }  */
        $('input, select').prop('disabled', false);
        
        var html='<div id="dialog-confirm" title="Submit For Approval">'+
        '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Are you sure to submit the project?</p></div>';
        $( html ).dialog({
              resizable: false,
              height:150,
              modal: true,
              open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
              buttons: {
                Cancel: function() {
                  $( this ).dialog( "close" );
                },
                "Yes": function() {
                     /* $("#action").val('submit_project');
                    var url = $("#projectForm").attr('action');  */
                    $( this ).dialog( "close" );
                    blockUsrInterface();

                    var x ;
                	$.ajax({  
      						type: "POST",
      						async:false,						
      						url: 'CanonE008ItemWorkbenchProcess.jsp', 
      						data: "action="+"SUBMIT_PROJECT" +"&cntRow="+getIdVal("countRow")+"&reqName="+getIdVal("requester_name")+"&projectNote="+getIdVal("projectNote")+getFormData() ,
      						complete: function(data)
      							{  
      								unBlockUsrInterface();
      		                        if(error_message(data)){
      		                            show_error("Failed to submit the project.",data);
      		                        }else{

      		                        	x =((data.responseText).replace(/^\s+|\s+$/g,''));
      									//alert("resp" + x);
      									var res =x.split("@@@");
      									if (res[1]=='S')
      									{
      										//alert ("Project " + res[0])
      										var strproNo = res[0]; 
      										//refreshPage(strproNo);
      										newrefreshPage(strproNo);	
      									}      		                        	
      		                        	
      		                        	
      		                        	//$("#divCanonE008Main").html("");
      		                            //$("#divCanonE008Main").html(data);
      		                            //update_status("The project has been saved.");
      		                        }
      		                      },
      		                      error:function(request){
      		                            unBlockUsrInterface();
      		                            var response=request.responseText;
      		                            //show_error("Failed to submit the project.",response);
      		                      }
      					}); 
                	
                	
                }
              }
        });
    
    } 	
   
    function approveProject()
    {
    	blockUsrInterface();
    	$('input, select').prop('disabled', false);
    	$.ajax({
 		  type: "POST",
		  async:false,						
      	  url: "CanonE008ItemWorkbenchProcess.jsp?action="+"APPROVE_PROJECT" +"&reqName="+getIdVal("requester_name") +"&projectNote="+getIdVal("projectNote") +"&userId="+ getIdVal("userId"), 
      	  data: $("#approve_comments").serialize()+"&"+getFormData(),
      	  complete: function(data)          
		  {  
			unBlockUsrInterface();
                    if(error_message(data)){
                        show_error("Failed to approve the project.",data);
                    }else{

                    	x =((data.responseText).replace(/^\s+|\s+$/g,''));
			//alert("resp" + x);
			var res =x.split("@@@");
			if (res[1]=='S')
			{
				//alert ("Project " + res[0])
				var strproNo = res[0]; 
				//refreshPage(strproNo);	
				newrefreshPage(strproNo);	
			}      		                        	
                    	//$("#divCanonE008Main").html("");
                        //$("#divCanonE008Main").html(data);
                        //update_status("The project has been saved.");
                    }
                  },
          error:function(request){
                unBlockUsrInterface();
                var response=request.responseText;
                show_error("Failed to approve project.",response);
          }
        });
        
    }

    function rejectProject()
    {
    	blockUsrInterface();
    	$('input, select').prop('disabled', false);
    	$.ajax({
 		  type: "POST",
		  async:false,						
      	  url: "CanonE008ItemWorkbenchProcess.jsp?action="+"REJECT_PROJECT" +"&reqName="+getIdVal("requester_name") +"&projectNote="+getIdVal("projectNote") +"&userId="+ getIdVal("userId"), 
      	  data: $("#reject_comments").serialize()+"&"+getFormData(),
      	  complete: function(data)          
		{  
			unBlockUsrInterface();
                    if(error_message(data)){
                        show_error("Failed to approve the project.",data);
                    }else{

                    	x =((data.responseText).replace(/^\s+|\s+$/g,''));
			//alert("resp" + x);
			var res =x.split("@@@");
			if (res[1]=='S')
			{
				//alert ("Project " + res[0])
				var strproNo = res[0]; 
				//refreshPage(strproNo);
				newrefreshPage(strproNo);	
			}      		                        	
                    	//$("#divCanonE008Main").html("");
                        //$("#divCanonE008Main").html(data);
                        //update_status("The project has been saved.");
                    }
                  },
          error:function(request){
                unBlockUsrInterface();
                var response=request.responseText;
                show_error("Failed to approve project.",response);
          }
        });
        
    }    
	
	function refreshPage(project_number)
    {
    	
    	location.reload(true);
    	//alert ("project_number "+ project_number);
    	if (project_number == null)
    		project_number = getIdVal("projectNumber");
    		
    	<%-- var url = $("#projectForm").attr('action')+"?project_number="+project_number+"&roleId=<%=roleId%>"; --%>
    	var url = $("#projectForm").attr('action')+"?project_number="+project_number;
    	var data = getFormData();
        //alert("url "+url);
        blockUsrInterface();
        $.get(url,function(data) {
           // alert("data " + data);
        	$("#divCanonE008Main").html("");
            $("#divCanonE008Main").html(data);
            unBlockUsrInterface();
        });
        
        //alert("Done ");
    }
	
	
    function newrefreshPage(project_number)
    {
    	if (project_number == null)
    		project_number = getIdVal("projectNumber");
    	
        $("#action").val('');
        var url = $("#projectForm").attr('action')+"?project_number="+project_number;
        //alert ("url "+url);
        blockUsrInterface();
        $.ajax({
          type: "POST",
          url: url,
          data: getFormData(),
          success: function(data){
            unBlockUsrInterface();
            if(error_message(data)){
                show_error("Failed to refresh the project.",data);
            }else{
                $("#divCanonE008Main").html("");
                $("#divCanonE008Main").html(data);
                //update_status("Project approved!");
            }
          },
          error:function(request){
                unBlockUsrInterface();
                var response=request.responseText;
                show_error("Failed to refresh project.",response);
          }
        });
    }
    

    function getFormData() {
    	//var myform = $('#projectForm');
		//var disabled = myform.find(':input:disabled').removeAttr('disabled');
		//var ser = $('#projectForm').serialize();
		var ser ='';
		var firstrow = parseInt($("#pageFrom").val());
		var row = parseInt($("#pageTo").val()); //parseInt($("#countRow").val());
		var colmain = parseInt($("#countmainCol").val());
		var coladd = parseInt($("#countaddCol").val());
		
		//alert('firstrow '+ firstrow);
		//alert('row '+ row);
		
		if(firstrow==row)
		{	
			firstrow=0;
			if(parseInt($("#countRow").val())<15)
				row = parseInt($("#countRow").val());
			else
				row = 15;
		}

		//alert('Newfirstrow '+ firstrow);
		//alert('Newrow '+ row);
		
		
		//var table = $("#templateTable");
		var strmain = 'templateRowName';
		var strattr = 'templateaddRow';
		var strattrName = 'templateaddRowName';

		
		var mainser = '&project_number='+document.getElementById('project_number').value+
					'&action='+document.getElementById('action').value+
					'&projectNumber='+document.getElementById('project_number').value+
					'&userId='+getIdVal("userId")+'&cntRow='+document.getElementById('countRow').value+
					'&startRow='+firstrow+
					'&endRow='+row+
					'&projectName='+document.getElementById('projectName').value+
					//'&projectNote='+document.getElementById('projectNote').value+
					'&approval_Satus='+document.getElementById('approval_Satus').value+
					'&projectType='+document.getElementById('projectType').value+
					'&projectRequester='+document.getElementById('projectRequester').value+
					'&masterProjectM='+document.getElementById('masterProjectM').value+
					'&masterProject='+document.getElementById('masterProject').value+
					'&projectDesc='+document.getElementById('projectDesc').value;
		//alert('mainser '+ mainser);
		
		if((document.getElementById('action').value=='SAVE') || (document.getElementById('action').value=='delete_item') )
		{
			//alert('SAVE');
			var strmainltr ='';
			var strserialize = mainser;
			
			//for(k=0; k<row;k++)
			for(k=firstrow; k<row;k++)	
			{	
				var strmercury = $("#mercuryind"+k).prop("checked") ? "on" : "N";
				
				//alert("mercury "+ $("#mercuryind"+k).prop("checked"));
				
				var stritemid = document.getElementById('item_id_'+k).value;
				var strselectItem = $("#cb_"+stritemid).prop("checked") ? "Y" : "N";
				
				//alert("strmercury "+ strselectItem);
				var strnewselectItem="";
				if(strselectItem=='Y')
				{	
					strnewselectItem = '&select_item='+stritemid;
				}
					
				
				//alert("strselectItem "+ strselectItem + " : "+stritemid);
				
				var strcom =// '&template_name_'+k+'='+document.getElementById('template_id_'+k).value+ 
				 '&item_name_'+k+'='+document.getElementById('item_id_'+k).value+
				 '&mercuryind'+k+'='+  strmercury+
				 '&htemplate_name_'+k+'='+document.getElementById('htemplate_id_'+k).value+
				 '&canonTemplateNames_'+k+'='+document.getElementById('canonTemplateNames_'+k).value+ strnewselectItem;
				 
				
				strserialize = strserialize + strcom;
				
				for(i=0; i<colmain;i++)
				{
					
					var strmainltr = strmain+k+'-'+i;
					var strvalue = document.getElementById(strmainltr).value;
					strserialize = strserialize + '&' +strmainltr +'='+strvalue;
				}
	
				for(i=0; i<coladd;i++)
				{
					var strattltr = strattr+k+'-'+i;
					var strattltrname = strattrName+k+'-'+i;
					var strvalue = document.getElementById(strattltr).value;
					strserialize = strserialize + '&' +strattltrname +'='+strvalue;
				}
				//alert('strserialize '+strserialize);
			}
			
			ser = strserialize.replace(' ','+');
	        
		}
		else if (document.getElementById('action').value=='SAVEBOM')
		{
			//alert('SAVEBOM');
			var strserialize = mainser;
			
			for(k=0; k<15;k++)
			{	
				var strcompid = document.getElementById('selectCompId'+k).value;
				var strbomid = document.getElementById('bomItemId').value;
				var strinstr = document.getElementById('bomInstructions').value;
				var strQty   = document.getElementById('bomQty'+k).value;
				
				
				var strcom = '&selectCompId'+k+'='+strcompid+ 
					 '&bomItemid'+'='+strbomid+
					 '&bomInstructions'+'='+  strinstr+
					 '&bomQty'+k+'='+strQty;
				 
				//alert('SAVEBOM - ' + strcom);
				
				strserialize = strserialize + strcom;
				
				ser = strserialize.replace(' ','+');
				
			}
		}
		else
		{	
			ser = mainser;
		}
		
		return ser;
	 }
	
	function getIdVal(x)
	{
	try
		{
		var m = document.getElementById(x).value;
		//alert(m);
		return m;
		} catch(err)
			{ alert(err);
				return false;}
	}
	//set value for given id
	function setIdVal(x,m)
	{
	try
		{
		 document.getElementById(x).value = m;
		// $("#countRow").val(m);
		// alert($("#countRow").val());
		//alert(m);
		//return m;
		} catch(err)
			{ alert(err);
				return false;}
	}

    function addNotes()
    {
        var handler=makeSingleErrorHandler();
        $("#action").val('add_notes');
        var url = $("#projectForm").attr('action');
        blockUsrInterface();
        $.ajax({
          type: "POST",
          url: url,
          data: $("#note_comments").serialize()+"&"+getFormData(),
          success: function(data){
            unBlockUsrInterface();
            if(error_message(data)){
                show_error("Failed to add Note.",data);
            }else{
                //alert("22222");
                newrefreshPage(null);
            	//$("#divCanonE008Main").html("");
                //$("#divCanonE008Main").html(data);
                //update_status("Project approved!");
            }
          },
          error:function(request){
                unBlockUsrInterface();
                var response=request.responseText;
                show_error("Failed to Add Notes.",response);
          }
        });
        
    }

  

	 function cancelTermsAndConditions(){
			if($("#DataDiv").dialog( "isOpen" )){
				$("#DataDiv").html('');																						
				$("#DataDiv").dialog("close");																																
				$("#DataDiv").dialog("destroy");
			}
		}
		
	function assignAdditinalAttr()
		{
		
		//alert("tttt - " + getIdVal("rowIndex"));
		//var strrrr = getIdVal("");
		var addIndex= getIdVal("rowIndex");  ///$("#addButtonPopupId").val()
		////alert("addIndex "+addIndex);

		for(i=0;i<<%=addtionalAttList%>;i++) {
			//if(i=1)
			//{	
			//alert("addIndex "+addIndex);
				//alert("attributeRow " + $("#attributeRowName"+i).val());
				//alert("templateaddRow " + $('#templateaddRow'+addIndex+i).val());
			//}
			//alert( "reqlov " + $("#reqlovName"+i).val());
			
			
			
			if ($("#reqlov"+i).val()!="Y")
				$("#templateaddRow"+addIndex+'-'+i).val($("#attributeRowName"+i).val());
			else
		    {   
				
				var lovname = $("#attributeRow22"+i).val();

				if ((lovname!="Supplier") && 
						(lovname!="Supplier Site") &&
						(lovname!="Service Model") && 
						(lovname!="Material Group1") && 
						(lovname!="Default Source Warehouse") &&
						(lovname!="Tariff Code"))
			  	{	
	
				var selectedValue = $("#attributeRowName"+i).val();
				var selectedtext = $("#attributeRowName"+i+" option:selected").text();
				
				////alert("finalval "+finalval);
				
			  	}	
				else
				{
					//alert("lovname "+lovname);
					var selectedValue = $("#attributeRowName"+i).val();
					var selectedtext = $("#default_text"+i).val();
					//alert("selectedValue "+selectedValue);
					//alert("selectedtext "+selectedtext);
						
				}
				var finalval ="";
				if ((selectedValue!="") && (selectedtext!=""))
					 finalval = selectedValue + "**"+selectedtext;
				$("#templateaddRow"+addIndex+'-'+i).val(finalval);
		    }
	
			//alert("templateaddRow " + $('#templateaddRow'+addIndex+i).val());
			
			//$("#templateaddRow"+addIndex+i).val($("#attributeRow"+i).val());
			//document.getElementById('templateaddRowName'+addIndex+i).value = document.getElementById('attributeRow'+addIndex).value;
		}
		document.getElementById("myModal").style.display = "block";
		//alert("#ItemNumber");				
		}

	 $(".rdl").each(function (){
		 
		  var ele=$(this);
		  var tp = $(ele).attr("type");
		  if(tp=="text")
			  $(ele).addClass("rdl").attr("readonly","readonly"); 
		  else   
			  $(ele).addClass("rdl").attr("disabled","true");	  
		  
	 });
	 
	 $('input[type="text"]').each(function (){
		 $(this).addClass("inTxt");	  
	 });
		
	 $("#divCanonE008Main").show();

	 
	 function fnExcelReport()
	 {
	     var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
	     var textRange; var j=0;
	     tab = document.getElementById('templateTable'); // id of table
		
	     tab_text=tab_text+"<font size=10><th>Template</th></font>"
	     //tab_text=tab_text+"<th>Mercury IND</th>"
	     <%
		 for(int i=0;i<templateHeaderList.size();i++){	
			 CanonE008ItemProcessDAO.attributeheaderInfo tempHeader = (CanonE008ItemProcessDAO.attributeheaderInfo)templateHeaderList.get(i);
			 //String templateHeader=(String)templateHeaderList.get(i);	
			 String templateHeader=(String)tempHeader.getAttributeName();
		 %>
		 tab_text=tab_text+"<font size=10><th><%=templateHeader%></th></font>";
		 <% } %>
		 <%	
				attributeresult = obj0.getProjItemAdditionalAttrValues(new BigDecimal("1"),new BigDecimal("1"));
				attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
				
				for(int i=0;i<attributeList.size();i++) {
					CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(i);
		%>		 	
		tab_text=tab_text+"<font size=10><th><%=itemrec.getAttribute()%></th></font>";
		
		<% } %>
		
		 <% if (itemList != null)
    		{
    		for(int j=0;j<itemList.size();j++) {
				CanonE008ItemProcessDAO.ItemInfoRec item=(CanonE008ItemProcessDAO.ItemInfoRec) itemList.get(j);
				String tempidstr = "canonTemplateNames_"+j;
				String strmercheck ="";
				String rowval = ""+j;
				
		%>  
		tab_text=tab_text+"<tr>";
		tab_text=tab_text+"<td><%=item.getTemplate()%></td>";
		 <%
			attributeresult = obj0.getProjItemAttrValues(item.getProjectid(),item.getItemid(),loginUserID);
			attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
			String strrequired="";
			String strkitItem="";
			String strattributename="";
			for(int i=0;i<attributeList.size();i++){		 
				CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(i);
				
				if(itemrec.getAttributelovflag().equals("Y"))	
				{	
					String strvalidfeildname = "templateRowName"+j+"-"+i;
					String itemrow = ""+j;
					String defaulttext ="";
					if ((!itemrec.getAttributevalue().equals("")) && (!itemrec.getAttributelovname().equals("MARKETING_MODEL")))
					{	
						defaulttext = returndefaultText(CanonE008TemplateDAO.getitemattdefvalueList(itemrec.getAttributelovname(),itemrec.getAttributevalue()));
					}
					else
					{	
						defaulttext = itemrec.getAttributevalue();
					}
		 %>
		 		tab_text=tab_text+"<td><%=defaulttext%></td>";
		 <% } else {%>
	 			tab_text=tab_text+"<td><%=CanonE008ItemProcessUtil.protectSpecialCharacters(itemrec.getAttributevalue())%></td>";	
		 <%
			} }
		 %>
		 
		 <%
			attributeresult = obj0.getProjItemAdditionalAttrValues(item.getProjectid(),item.getItemid());
			attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
			String alltext="";
			for(int k=0;k<attributeList.size();k++){
				CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(k);
				//System.out.println("before AttributeLink:lovflag " + itemrec.getAttributelovflag());
				//System.out.println("before AttributeLink:lovname " + itemrec.getAttributelovname());
				//System.out.println("before AttributeLink:lovvalue " + itemrec.getAttributevalue());
				if (itemrec.getAttributelovflag().equals("Y") && !itemrec.getAttributevalue().equals(""))
				{	String defaulttext = returndefaultText(CanonE008TemplateDAO.getitemattdefvalueList(itemrec.getAttributelovname(),itemrec.getAttributevalue()));
					alltext = defaulttext;
					//alltext = itemrec.getAttributevalue() +"**"+defaulttext;
					
					//System.out.println("before AttributeLink:alltext " + alltext);
				}	
			   else
				   alltext = itemrec.getAttributevalue();	
		%>
				
         	tab_text=tab_text+"<td><%=alltext%></td>";
     <% } %>
		 
		 
		 
		 
		 
		 tab_text=tab_text+"</tr>";
		 <%
    		} }
		 %>
		 
	     /* for(j = 0 ; j < tab.rows.length ; j++) 
	     {     
	         tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
	         //tab_text=tab_text+"</tr>";
	     } */

	     tab_text=tab_text+"</table>";
	     tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
	     tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
	     tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

	     var ua = window.navigator.userAgent;
	     var msie = ua.indexOf("MSIE "); 

	     if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
	     {
	         txtArea1.document.open("txt/html","replace");
	         txtArea1.document.write(tab_text);
	         txtArea1.document.close();
	         txtArea1.focus();
	         sa=txtArea1.document.execCommand("SaveAs",true,"*.xls");
	     }  
	     else                 //other browser not tested on IE 11
	         sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

	     if (sa)
			   alert("Project Items download successfully. ");	
	    // return (sa);
	 }	 
	 
</script>			

<!-- <script src="<%=ctxPath%>/common/jquery/jquery.scrolltable.js" type="text/javascript"></script> -->
<div id="main_content">
	<div id="page_top">	<h1>Item Workbench <%=p_project_number == null ? "" : "- Project Number " + p_project_number.toString()%></h1>
	</div>
	<iframe id="txtArea1" style="display:none"></iframe>
	<!-- <div class="table-inner"> -->
	<form name="projectForm" id="projectForm" method="post" action="canonE008ProjectInclude.jsp">
	
		<input type="hidden" name="project_number" id="project_number"	value=" <%=p_project_number == null ? "" : p_project_number.toString()%>">
		<input type="hidden" name="action" id="action" value="" /> 
		<input type="hidden" id="userId" name="userId" value="<%=loginUserID == null ? "" : loginUserID%>">
		<input type="hidden" name="roleId" value="<%=roleId == null ? "" : roleId.toString()%>">
		<input type="hidden" name="countRow" id="countRow" value="<%=cnttemplate %>">
		<input type="hidden" name="countmainCol" id="countmainCol" value="<%=templateHeaderListCount%>">
		<input type="hidden" name="countaddCol" id="countaddCol" value="<%=addtionalAttList%>">
		
		
		
		<div class="prj_main">
				<table class="whdr" width=100% style="border-spacing: 5px;" cellspacing="5">
				 <tbody>
					<tr>
					    <th colspan="4">Project Info</th>
						<th colspan="2">Notes Details</th>
						<th colspan="2">Status Info</th>
					</tr>
					<tr>
						<td>Project	Number</td>
						<td><input class="rdl inTxt" readonly="readonly" type="text" id="projectNumber"
							name="projectNumber" value=" <%=p_project_number==null? "":p_project_number.toString()%>"></td>
						<td>Project	Name</td>
						<td><input class="rdl required inTxt" type="text" id="projectName" name="projectName"
							value="<%=CanonE008ItemProcessUtil.nonNullify(p_project_name)%>"></td>
						<td>Notes</td>
						<td rowspan="7"><textarea class="inTxt rdl" readonly="readonly" style="width: 380px; height: 170px; background-color: #CCCCCC;" name ="projectNote" id="projectNote" ><%=CanonE008ItemProcessUtil.nonNullify(p_project_notes)%></textarea></td> 
						<td>Status</td>
						<td><input class="rdl inTxt" type="text" readonly="readonly" name="approval_Satus" id="approval_Satus" value="<%=CanonE008ItemProcessUtil.nonNullify(p_project_status)%>"></td>
					</tr>
					 <tr>
						<td>Project	Type</td>
						<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"projectType",p_project_type,"required","")%></td>
						<td>Project	Requestor</td>
						<td><input  class="inTxt" type="hidden" id="projectRequester"	name="projectRequester"
							value="<%=p_project_processor_id == null ? "": p_project_processor_id.toString()%>"><input type="text" class="rdl inTxt" readonly="readonly" id="requester_name" value="<%=CanonE008ItemProcessUtil.nonNullify(p_project_processor_full_name)%>"></td>
						<td>
						    <%
							if (p_project_number != null) {
							%>
						    <a class="btnPrj" href="javascript:addNote()">Add Note</a>
						    <% } %>
						</td>
						<td></td>
						<td align="right">
						    <%
							if (p_project_number != null) {
							%>
						    <a class="btnPrj" href="javascript:approvalHistory()">Approval History</a></td>
							<% } %>
					</tr>
					<tr>
						<td>Master Project</td>
						<%-- <td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"masterProject",p_master_project,null,"")%></td> --%>
						<td><div class="ui-widget">
							<%=CanonE008ItemProcessHelper.genSelectdataLMItemHtml(lovs,"masterProject", p_master_project, "", "Select Master Project" )%>
							</div></td>
						<td>Requested Date</td>
	              		<td><input class="rdl inTxt" readonly="readonly" type="text" id="requested_date" value="<%=p_requested_date%>"> </td>
					</tr> 
					<tr>
						<td>Project	Description</td>
						<td><textarea rows="6" cols="30" class="nonedittextarea"
								name="projectDesc" id="projectDesc"><%=CanonE008ItemProcessUtil.nonNullify(p_project_description)%></textarea></td>
					</tr>
				</tbody>					
				</table>
		</div>
		<div class="service_btn">
			<table class="whdr" cellspacing="5">
				 <tbody>
				 	<tr><td colspan="5" align="left">
						<%
						if (p_project_number == null) {
						%>
					    <a class="btnPrj" href="javascript:saveProject()">Save Project</a>
						<%
						}
						%>
         
						<%
						if ((p_project_number != null) && (canModifyProject)) {
						%>
					    <a class="btnPrj" href="javascript:saveAlertValidateProject()">Validate</a>
						<%
						}
					
						if ((canDeleteProject) && (p_project_number != null)) {
						//if ((p_project_number != null) ) {
						%>
					    <a class="btnPrj" href="javascript:deleteProject()">Delete Project</a>
					    <a class="btnPrj" href="javascript:uploadItems()">Upload Items</a>
						<%
						}
						%>

						<%
						if (canValidateCloseProject) {
						%>
					    <a class="btnPrj" href="javascript:closeProject()">Close</a>
						<%
						}
							if ((canValidateRejectProject) && (p_project_number != null)) {
						%>
					    <a class="btnPrj" href="javascript:approve()">Approve</a>
						<%
						}
							if ((canValidateRejectProject) && (p_project_number != null)) {
						%>
					    <a class="btnPrj" href="javascript:reject()">Reject</a>
						<%
						}
							if ((canSubmitForApproval) && (p_project_number != null)) {
						%>
					    <a class="btnPrj" href="javascript:submitProject()">Submit Project</a> 
		    			 <%
						}
							if ((p_project_number != null)) {
						%>
					    <a class="btnPrj" href="javascript:fnExcelReport()">Export Project</a> 
		    			 <%
						}
			    		  %>
			    		</td>  
		                <!-- <td align="center"> 
		                    <button class="ui-state-default ui-corner-all" type="button" id="upload_item" title="Upload Items">
		                        <span class="ui-button-text">Upload Item</span>
		                    </button>
		                </td> --> 			    				
					</tr>
 				</tbody>		 
			</table>		 
		</div>		 		
		<div>
			<span id="project_status"></span>
	    </div>

 			<%-- <div class="select_all_div">
				<%
					if (canModifyProject) {
				%>
				Select: <a class="select_all" href="#" id="select_all_button">All</a>
				Deselect: <a class="select_all" href="#" id="deselect_all_button">All</a>
				<%
					}
				%>
			</div> --%>
 			   
 			<%
				if (p_project_number != null) {
			%>
 			<!-- <table id="templateTable" class="stripe row-border order-column" cellspacing="0" width="100%"> -->
  			<div class="proj-main-inner">
 			<table id="templateTable"  class="projItem-table hover row-border display compact" cellspacing="1">
			        <thead>
			            <!-- <tr id="report_tbl_first" style="color:#ffffff;font-size:12px;"> -->
			            
			             <th></th>
			             <th>Template</th>
			             <th>Mercury IND</th>
			             <%
						 for(int i=0;i<templateHeaderList.size();i++){	
							 CanonE008ItemProcessDAO.attributeheaderInfo tempHeader = (CanonE008ItemProcessDAO.attributeheaderInfo)templateHeaderList.get(i);
							 //String templateHeader=(String)templateHeaderList.get(i);	
							 String templateHeader=(String)tempHeader.getAttributeName();
						 %>
						 <th><%=templateHeader%></th>
						 <% } %>
						 
						 <th></th>
						 <th>Additional Attributes</th>
						                 
			         
			       </thead> 
			        <tbody> 
			               <% if (itemList != null)
			               		{
			               		for(int j=0;j<itemList.size();j++) {
                    				CanonE008ItemProcessDAO.ItemInfoRec item=(CanonE008ItemProcessDAO.ItemInfoRec) itemList.get(j);
                    				String tempidstr = "canonTemplateNames_"+j;
                    				String strmercheck ="";
                    				String rowval = ""+j;
                    				
                    				String strtemplreq="";
                    				if (p_project_status.equals("PENDING APPROVAL"))
                    					strtemplreq="noneditrequired readonly";
                    				else
                    					strtemplreq="required";
                    				
							%>  
							
								<tr id="item_<%=item.getItemid()%>" style="color:#000000;">
								
			         			<%
			         			if (item.getAttributeDefaultValue().equals("S")) 
			         			{
			         			%>
				            	<td align="center" style="color:#000000;background-color:#00ff00;"><input type="checkbox" id="cb_<%=item.getItemid()%>" class="select_item" name="cb_<%=item.getItemid()%>"></td>
					            <% } 
			         			if (item.getAttributeDefaultValue().equals("F")) 
			         			{
			         			%>
			         			<td align="center" style="color:#000000;background-color:#FF0000;"><input type="checkbox" id="cb_<%=item.getItemid()%>" class="select_item" name="cb_<%=item.getItemid()%>"></td>
					            <% }
			         			if (item.getAttributeDefaultValue().equals("")) 
			         			{
			         			%>
			         			<td align="center"><input type="checkbox" id="cb_<%=item.getItemid()%>" class="select_item" name="cb_<%=item.getItemid()%>"></td>
			         			<%} %>	

					            <input type="hidden" id="template_id_<%=j%>" name="template_name_<%=j%>" value="<%=item.getTemplateid()%>">
					            <input type="hidden" id="item_id_<%=j%>" name="item_name_<%=j%>" value="<%=item.getItemid()%>">
					            <input type="hidden" id="item_type<%=j%>" name="item_type_<%=j%>" value="">
					            <input type="hidden" id="htemplate_id_<%=j%>" name="htemplate_name_<%=j%>" value="<%=item.getTemplate()%>">
					            
					            <td align="center"><%=CanonE008ItemProcessHelper.genSelectHtmlProjTemplate(lovs,"template",item.getTemplate(),tempidstr,rowval,strtemplreq)%></td>
					            
								<%
					            	if (item.getMercuryinclude().equals("Y"))
					         			{
					         				strmercheck="checked";
					         	%>
					         				<td align="center"><input type="checkbox" id="mercuryind<%=j%>" checked="<%=strmercheck%>"  class="select_item" name="mercuryind<%=j%>"></td>
					         	
					         	<%		}
					         			else
					         			{
					         				strmercheck="";
					         	%>
					         				<td align="center"><input type="checkbox" id="mercuryind<%=j%>" class="select_item" name="mercuryind<%=j%>"></td>
					         	<%			
					         			}	
					            %>					            
					            
					            

								<%
									attributeresult = obj0.getProjItemAttrValues(item.getProjectid(),item.getItemid(),loginUserID);
									attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
									String strrequired="";
									String strkitItem="";
									String strattributename="";
									for(int i=0;i<attributeList.size();i++){
										CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(i);
										
										strrequired="";

										if ((itemrec.getAttributereq().equals("YES")) && (!itemrec.getAttributeDefaultValue().equals("NULL")))
										{
											strrequired="noneditrequired readonly";
										}
										if ((itemrec.getAttributereq().equals("YES")) && (itemrec.getAttributeDefaultValue().equals("NULL") ))
										{
											strrequired="required";
										}

										if (itemrec.getAttributereq().equals("NO"))
										{
											strrequired="";
										}

										if (itemrec.getAttributevalid().equals("NO"))
										{	
											strrequired="noneditrequired readonly";
											//strrequired="noneditrequired disabled";
										}
										
									    /*if (!canCreateProject)
										{
											strrequired="noneditrequired disabled";
										}*/
										System.out.println("before SUBSTR ITEMMASTER:");
										strattributename = itemrec.getAttributelovname().substring(0,itemrec.getAttributelovname().length()-2) ;
										System.out.println("before strattributename:" + strattributename);
										System.out.println("before getAttributelovname():" + itemrec.getAttributelovname());
										strkitItem = itemrec.getAttributelovname().substring(itemrec.getAttributelovname().length()-2);
										//strkitItem = "11";
										System.out.println("before strkitItem:" + strkitItem);
										if (!strattributename.equals("ITEM_NUMBER"))
											strattributename = itemrec.getAttributelovname();

										
										System.out.println("before strattributename:" + strattributename);
										
										//if((itemrec.getAttributelovflag().equals("Y")) && (canCreateProject)) 
										if(itemrec.getAttributelovflag().equals("Y"))	
										{	
											String strvalidfeildname = "templateRowName"+j+"-"+i;
											String itemrow = ""+j;
											
											if ((!itemrec.getAttributelovname().equals("SUPPLIER")) && 
													(!itemrec.getAttributelovname().equals("PRODUCT_LEVEL5")) &&
													(!itemrec.getAttributelovname().equals("SERVICE_MODEL")) && 
													(!itemrec.getAttributelovname().equals("MATERIAL_GROUP1")) && 
													(!itemrec.getAttributelovname().equals("DEFAULT_SRC_WH")) &&
													(!itemrec.getAttributelovname().equals("MARKETING_MODEL")) &&
													(!itemrec.getAttributelovname().equals("TARIFF_CODE")))  
											{	
												//addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattvalueList(itemrec.getAttributelovname()));
												addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattdefvalueList(itemrec.getAttributelovname(),itemrec.getAttributevalue()));
												
												//if ((!itemrec.getAttributelovname().equals("MARKETING_MODEL")) && (CanonE008ItemProcessUtil.nonNullify(p_project_status).equals("ENTERED")))
												if ((itemrec.getAttributelovname().equals("MARKETING_MODEL")))
												{

	 							%>
 									<td align="center"><%=CanonE008ItemProcessHelper.genSelectItemHtmlModal(attlovs,strvalidfeildname, itemrec.getAttributevalue(),strrequired,itemrec.getAttributelovname(),itemrow)%></td>
	 							
	 							<% } else { %> 
									
									<td align="center"><%=CanonE008ItemProcessHelper.genSelectItemHtmlmainattribute(attlovs,strvalidfeildname, itemrec.getAttributevalue(),strrequired,itemrec.getAttributelovname(),itemrow)%></td>
									
					            	<% if (itemrec.getAttributelovname().equals("PRODUCT_LEVEL3"))
					            	{	
					            	%>
					            		<input type="hidden" id="product_level3_<%=j%>" name="product_level3_<%=j%>" value="<%=strvalidfeildname%>">
					            		
					            	<% } %>									
									
																					
								<% } } else {  
 										String defaulttext = returndefaultText(CanonE008TemplateDAO.getitemattdefvalueList(itemrec.getAttributelovname(),itemrec.getAttributevalue()));
 								%>
		 						
		 							<td>
					                	<div id="searchBox<%=j%><%=i%>">
					                	    <table>
					                	    <td style="padding:2px;">
					                	    	<input style=" display:none;"  class="inTxt" type="text" id="<%=strvalidfeildname%>"  name="<%=strvalidfeildname%>" VALUE="<%=CanonE008ItemProcessUtil.nonNullify(itemrec.getAttributevalue())%>">
					                	    	<input class="<%=strrequired%>" class="inTxt" type="text"  onchange="checkemptyChange(<%=j%>,<%=i%>,'<%=itemrec.getAttributelovname()%>')" id="default_text<%=j%>-<%=i%>"  name="default_text<%=j%>-<%=i%>" VALUE="<%=defaulttext%>">
					                	    	
					                	    </td>
					                	    <td style="padding:2px;">
					                	    	<button type='button' id="find<%=j%><%=i%>" onclick="callChange(<%=j%>,<%=i%>,'<%=itemrec.getAttributelovname()%>','<%=itemrec.getAttributevalue()%>')"><img id="myImg" src="<%=ctxPath%>/e008/images/canonSearch.png"></button>
					                	    	<%-- <input type="image" id="find<%=j%><%=i%>" onclick="callChange(<%=j%>,<%=i%>,'<%=itemrec.getAttributelovname()%>','<%=itemrec.getAttributevalue()%>')" src="<%=ctxPath%>/e008/images/canonSearch.png"> --%>
					                	    </td> 
					                	    </table>  
					                	</div>
					                	    
					                	<div id="searchList<%=j%><%=i%>" style="display:none;" >
					                		<%=CanonE008ItemProcessHelper.genSelecttemplatedataListHtml(lovs, strvalidfeildname, "",i+strvalidfeildname, ""+i, ""+j )%>
					                	</div>
					            	</td>
					            	<% if (itemrec.getAttributelovname().equals("SUPPLIER"))
					            	{	
					            	%>
					            		<input type="hidden" id="item_supplier<%=j%>" name="item_supplier_<%=j%>" value="<%=strvalidfeildname%>">
					            		
					            	<% } %>
								<%  }
										}
										else
										{
											
										if (!strrequired.equals(""))
										{
								
											if(strkitItem.equals("11")||strkitItem.equals("10"))  //SET and KIT ITEM	 
											{
								%>
								  
										    <%
						         			if (item.getAttributeDefaultValue().equals("S")) 
						         			{
						         			%>
							            	<td style="color:#000000;background-color:#00ff00;">
								            <% } 
						         			else if (item.getAttributeDefaultValue().equals("F")) 
						         			{
						         			%>
						         			<td style="color:#000000;background-color:#FF0000;">
								            <% }
						         			else
						         			{
						         			%>
						         			<td>
						         			<%} %>	
								  
								    		<a id="templateSetRowName<%=j%><%=i%>"  onclick="assignBOMId(<%=j%>)" data-toggle="modal" data-target="#myBOMModal" class="dt-button"><span style="color:black"><%=itemrec.getAttributevalue()%></span></a></td>
								    		<input style="display:none;" class=<%=strrequired%> type="text" id="templateRowName<%=j%>-<%=i%>" name="templateRowName<%=j%>-<%=i%>"  VALUE="<%=itemrec.getAttributevalue()%>">



										<%		
												//CanonE008ItemProcessDAO.ItemInfoRec item=(CanonE008ItemProcessDAO.ItemInfoRec) itemList.get(0);
		 										result = obj0.getbomItems(item.getItemid());
		 										bomItemList = (List) CanonE008ItemProcessUtil.first(result);
												for(int ii=0;ii<bomItemList.size();ii++) {
													CanonE008ItemProcessDAO.getCompItems bomitemrec=(CanonE008ItemProcessDAO.getCompItems) bomItemList.get(ii);
			 							%>
											<input style="display:none;" id="<%=item.getItemid()%>ComponentId<%=ii%>"  name="<%=item.getItemid()%>ComponentId<%=ii%>" VALUE="<%=bomitemrec.getComponentItem() %>">
						                	<input style="display:none;" id="<%=item.getItemid()%>ComponentId<%=ii%>"  name="<%=item.getItemid()%>ComponentId<%=ii%>" VALUE="<%=bomitemrec.getComponentItem() %>">
						                	<input style="display:none;" id="<%=item.getItemid()%>bomCompDesc<%=ii%>"  name="<%=item.getItemid()%>bomCompDesc<%=ii%>" VALUE="<%=bomitemrec.getCompdesc()%>">
						                	<input style="display:none;" id="<%=item.getItemid()%>bomManugacturer<%=ii%>"  name="<%=item.getItemid()%>bomManugacturer<%=ii%>" VALUE="<%=bomitemrec.getManufacturer()%>">
						                	<input style="display:none;" id="<%=item.getItemid()%>bomQty<%=ii%>"  name="<%=item.getItemid()%>bomQty<%=ii%>" VALUE="<%=bomitemrec.getQty()%>">
						                	<input style="display:none;" id="<%=item.getItemid()%>bomMerchType<%=ii%>"  name="<%=item.getItemid()%>bomMerchType<%=ii%>" VALUE="<%=bomitemrec.getMerchType()%>">
						                	<input style="display:none;" id="<%=item.getItemid()%>bomProdCode<%=ii%>"  name="<%=item.getItemid()%>bomProdCode<%=ii%>" VALUE="<%=bomitemrec.getProdCode()%>">
						                	<input style="display:none;" id="<%=item.getItemid()%>bomItemType<%=ii%>"  name="<%=item.getItemid()%>bomItemType<%=ii%>" VALUE="<%=bomitemrec.getItemtype()%>">
						                	<input style="display:none;" id="<%=item.getItemid()%>bomCost<%=ii%>"  name="<%=item.getItemid()%>bomCost<%=ii%>" VALUE="<%=bomitemrec.getCost()%>">
			 								
										 <% } %>
											<input  style="display:none;" id="<%=item.getItemid()%>bomCompcount"  name="bomCompcount" VALUE="<%=bomItemList.size()%>">
	
										<% } else { %>
								    <%-- <td><input class=<%=strrequired%> type="text" id="templateRowName<%=j%><%=i%>"  onclick="openBOM();" name="templateRowName<%=j%><%=i%>"  VALUE="<%=itemrec.getAttributevalue()%>"></td> --%>
									
									<%
				         			if (item.getAttributeDefaultValue().equals("S") && itemrec.getAttributelovname().equals("ITEM_NUMBER")) 
				         			{
				         			%>
					            	<td style="color:#000000;background-color:#00ff00;">
						            <% } 
				         			else if (item.getAttributeDefaultValue().equals("F") && itemrec.getAttributelovname().equals("ITEM_NUMBER")) 
				         			{
				         			%>
				         			<td style="color:#000000;background-color:#FF0000;">
						            <% }
				         			else
				         			{
				         			%>
				         			<td>
				         			<%} %>	
				         			
									
									<div id="sBox<%=j%><%=i%>">
									<table><td style="padding:2px;">
									<input class=<%=strrequired%> type="text" id="templateRowName<%=j%>-<%=i%>" name="templateRowName<%=j%>-<%=i%>"  VALUE="<%=CanonE008ItemProcessUtil.protectSpecialCharacters(itemrec.getAttributevalue())%>">

									<% if (itemrec.getAttributelovname().equals("ITEM_NUMBER")) { 
									%>	
									</td>
									<td style="padding:2px;">
									<button type='button' id="checkcusa<%=j%><%=i%>" onclick="callcusa(<%=i%>,<%=j%>,'<%=itemrec.getAttributelovname()%>','<%=itemrec.getAttributevalue()%>')"><img id="myImg" src="<%=ctxPath%>/e008/images/get.png"></button>
									<% } %>
									</td></table>
									</div>
									</td> 
									
								
								 <%   }	
										}
										else
										{ 
								%>	
										
									<td><input type="text" id="templateRowName<%=j%>-<%=i%>" name="templateRowName<%=j%>-<%=i%>"  VALUE="<%=CanonE008ItemProcessUtil.protectSpecialCharacters(itemrec.getAttributevalue())%>"></td>
								<%		}									 
										} %>  
								
 					                <%-- <td><input type="text" id="templateRow<%=j%><%=i%>" name="templateRowName<%=j%><%=i%>"  <%=required(item.getTemplateid() ,itemrec.getTemplate())%> VALUE="<%=itemrec.getAttributevalue()%>"></td> --%>
					            <%    
					                if (itemrec.getTemplate().equals("Item Type")) {
					            %>
					                
					                <input style="display:none;"  id="item_type<%=j%>" name="item_type_<%=j%>" value="<%=itemrec.getAttributevalue() %>">
					                
					            <% }  %>     
					            	<input style="display:none;"  id="csatemplateRowName<%=j%>-<%=i%>" name="csatemplateRowName<%=j%>-<%=i%>" value="">
					            <% } %>				           
					            <!-- <td style="text-align:right;"><button>Addition123</button></td> -->     
								<td style="display:none;"> 
 								<%
									attributeresult = obj0.getProjItemAdditionalAttrValues(item.getProjectid(),item.getItemid());
									attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
									String alltext="";
									for(int k=0;k<attributeList.size();k++){
										CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(k);
										//System.out.println("before AttributeLink:lovflag " + itemrec.getAttributelovflag());
										//System.out.println("before AttributeLink:lovname " + itemrec.getAttributelovname());
										//System.out.println("before AttributeLink:lovvalue " + itemrec.getAttributevalue());
										if (itemrec.getAttributelovflag().equals("Y") && !itemrec.getAttributevalue().equals(""))
										{	String defaulttext = returndefaultText(CanonE008TemplateDAO.getitemattdefvalueList(itemrec.getAttributelovname(),itemrec.getAttributevalue()));
											alltext = itemrec.getAttributevalue() +"**"+defaulttext;
											
											//System.out.println("before AttributeLink:alltext " + alltext);
										}	
									   else
										   alltext = itemrec.getAttributevalue();	
								%>
  										<input style="width: 10px;" type="text"  id="templateaddRow<%=j%>-<%=k%>" name="templateaddRowName<%=j%>-<%=k%>" VALUE="<%=alltext%>">
						            	<input style="width: 10px;" type="text"  id="templatedefaddRow<%=j%>-<%=k%>" name="templatedefaddRowName<%=j%>-<%=k%>" VALUE="<%=itemrec.getAttributeDefaultValue() %>">
						            	<input style="width: 10px;" type="text"  id="templateReqaddRow<%=j%>-<%=k%>" name="templateReqaddRowName<%=j%>-<%=k%>" VALUE="<%=itemrec.getAttributereq().substring(0,1)%>">

					            <% } %>
 								</td>					            
				             	 <td style="text-align:right;"></td>
				            	</tr>
					            <% } } %>
					                     
		             </tbody> 
	        </table>
	        </div>
			
 			<script>
 			
	 			function callcusa(Id,row,attributename,defaultval) {    
				    //alert("Id " + Id);
				    //alert("row " + row);
				    //alert("attributename " + attributename);
				    //alert("defaultval " + defaultval);
				    var strapprovalStatus = $('#'+'approval_Satus').val();
				    var defaultval1 = $('#'+'templateRowName'+row+'-'+Id).val(); 
				    
				    //alert("defaultval1 " + defaultval1);
				    
					var length = defaultval1.length;
					if ((strapprovalStatus=="ENTERED") || (strapprovalStatus=="ITEM MASTER REVIEW"))
					{	
						if (length>=8)  
							{	 
								 //alert(" roWid" + row+Id+'default_value'+row);
								 blockUsrInterface()		
								 blockcsaUsrInterface()
								 callcusaFunc(Id,row,defaultval1);
								 unBlockcsaUsrInterface()		
							}
						else
							{
							alert("Enter atleast 8 letters ");
							}
					}		 
				}
 			
	 		function callcusaFunc(Id,row,defaultvalue) {                
	 		       // alert("test Id " + Id + " row " + row);
	 		 		var selectBox = document.getElementById("canonTemplateNames_"+Id);
	 				//alert(selectBox.selectedIndex);
	 		        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	 			    //alert(selectedValue);
	 			    //blockUsrInterface();
	 		        var url= encodeURI('<%=ctxPath%>/e008/jsp/CanonE008GetSelectedTemplateAttributes.jsp?action='+"GETCUSAITEM"+"&templateName="+escape(selectedValue)+"&cusaItem="+ defaultvalue+"&projectNumber=");   
	 		        //alert(url);         
	 		       	 $.ajax({  
	 							type: "POST",
	 							async:false,						
	 							url: url, 						
	 							complete: function(data)
	 								{  
	 									x =((data.responseText).replace(/^\s+|\s+$/g,''));
	 									//alert(" x- " +x);
	 									res=x.split("@@");
	 									//if (res[0].split(",").length==2)
	 									//alert("res[0]"+res[0].split(",").length);	
	 									if (res[0].split(",").length==1)	
	 									{
	 										show_error(defaultvalue + " - Item not available in CUSA. ");
	 									}
	 									else 
	 									{	
	 									templateAttrVal=res[0].split(",");			
	 									 //alert("templateAttrVal Lenght" + templateAttrVal);
	 									 var k=0;
	 									 var n=0;
	 									  for(var i=0;i<templateAttrVal.length;i++)
	 									 {								 
	 										 var templateAttr=templateAttrVal[i];
	 										 //alert(" templateAttr-" +templateAttr);
	 										 templateAttr=templateAttr.replace('[','').replace(']','');

 	 										 if($.trim(templateAttr)=="null"||$.trim(templateAttr)=="[null"||$.trim(templateAttr)=="null]")	 
	 										 	 templateAttr="";									 								 
 	 										
 	 										 //alert ("templateAttr "+templateAttr);
 	 										
	 										 if (i==0) {
	 											 $("#template_id_"+row).val(templateAttr.substr(1,templateAttr.length) );
	 											 }
	 										 else {
	 											 n=i-1; 
	 											 var listcode="";
	 											 var listname = "";
	 											 var lovname = "";
	 											 if (templateAttr.substr(1, 1)=="M") { 
	 													 
	 												 	 if (templateAttr.substr(3, 1)!="Y")
	 												 	 {	
	 												 		 if (templateAttr.substr(4,templateAttr.length)!="") 
	 												 			{ 
	 												 	 			$("#templateRowName"+row+'-'+n).val(templateAttr.substr(4,templateAttr.length));
	 												 				$("#csatemplateRowName"+row+'-'+n).val("Y");
	 												 			}
	 												 	 }
	 												 	 else
	 												 	 {
	 													  	//alert ("templateAttr11 "+templateAttr);
	 												 		var listdetails = templateAttr.substr(4,templateAttr.length);
	 													  	//alert("listdetails "+listdetails);
	 													  	var reslist=listdetails.split("**");
	 													  	if (reslist!="")
	 													  	{	listcode=reslist[0];
	 													  		listname = reslist[1];
	 													  		lovname = reslist[2];
	 													  	}
	 													 	
	 													  	//alert ("listcode "+listcode);
	 													  	//alert ("listname "+listname);
	 													  	//alert ("lovname "+lovname);
	 													  	
	 													  	if (listcode!="")
	 													  	{	
		 													  	if ((lovname!="SUPPLIER") && 
		 																(lovname!="PRODUCT_LEVEL5") &&
		 																(lovname!="SERVICE_MODEL") && 
		 																(lovname!="MARKETING_MODEL") &&
		 																(lovname!="MATERIAL_GROUP1") && 
		 																(lovname!="DEFAULT_SRC_WH") &&
		 																(lovname!="TARIFF_CODE"))
		 													  	{	
		 													  		$("#templateRowName"+row+'-'+n).empty();
		 												 			var	mainoption = $('<option selected ="" value="'+ listcode +'">'+listname+'</option>');
		 		 					  					        	$("#templateRowName"+row+'-'+n).append(mainoption);
		 													  	}
		 													  	else
		 													  	{
		 													  		$("#templateRowName"+row+'-'+n).val(listcode);
		 													  		$("#default_text"+row+'-'+n).val(listname);
		 													  		
		 													  		
		 													  		/*if ((templateAttr.substr(2, 1)=="Y") && (listname!=""))
		 													  		{
		 													  			$("#default_text"+row+n).addClass("noneditrequired");
		 													    	 	$("#default_text"+row+n).prop("disabled", true);
		 													  		}
		 													  		else if ((templateAttr.substr(2, 1)=="Y") && (listname==""))
		 													  			$("#default_text"+row+n).addClass("required");
		 													  		else
		 													  			$("#default_text"+row+n).addClass("notrequired");
		 													  		*/
		 													  	}
		 													  	$("#csatemplateRowName"+row+'-'+n).val("Y");
	 													  	}	 													  	
	 												 	 }
	 												 	 
	 													 /*if ((templateAttr.substr(2, 1)=="Y") && (listname!="")) //(templateAttr.substr(4,templateAttr.length)!=""))
	 													 	{
	 														 $("#templateRowName"+row+n).addClass("noneditrequired");
	 													     $("#templateRowName"+row+n).prop("disabled", true); 
	 													    }
	 													 else if ((templateAttr.substr(2, 1)=="Y") && (listname=="")) //(templateAttr.substr(4,templateAttr.length)==""))
	 													 		$("#templateRowName"+row+n).addClass("required");
	 													 		
	 													 else
	 															 
	 														$("#templateRowName"+row+n).addClass("notrequired");
	 													 */
	 												 	}
	 												 else {
	 													  // alert("K "+k); 
	 													  //alert("value "+templateAttr.substr(3,templateAttr.length));
	 													  var additionstr = "";
	 													  var additionlistcode ="";
	 												      if(templateAttr.substr(4,templateAttr.length)=="NULL]")
	 														  additionstr="";
	 													  else 
	 														  additionstr = templateAttr.substr(4,templateAttr.length);
	 												      
	 												      //alert("additionstr " + additionstr);
	 												      
	 												      if (additionstr!="")
	 												      {
	 												    	  var resaddlist=additionstr.split("**");
		 													  if (resaddlist!="")
		 													  {	
		 														  additionlistcode=resaddlist[0];
		 													  }
		 													  	  
	 												    	  
		 													  if (additionlistcode!="")
		 													  { 
																  $("#templateaddRow"+row+'-'+k).val(additionstr);
																  $("#csatemplateaddRow"+row+'-'+k).val("Y");
			 													  $("#templatedefaddRow"+row+'-'+k).val(additionstr);
			 													  $("#templateReqaddRow"+row+'-'+k).val(templateAttr.substr(2, 1));
		 													  }
	 												      }	  
	 													  K=k++;	
	 											 	  }
	 										 	}
	 										
	 										
	 									 } 
	 									//alert(" res[1] - " +res[1]);  
	 									bomitems=res[1].split(","); 
 										for(var i=0;i<bomitems.length;i++)
	 									{
 											var compItem=bomitems[i]
 											compItem=compItem.replace('[','').replace(']','');
 											
 											if (compItem!="")
 													addcusaRow(compItem);
 										}
	 								   }
	 								}  
	 						});  //ajax end 
	 		    	//unBlockUsrInterface();	
	 			}; 
	 			
 				function checkemptyChange(Id,row,lovName) {  
 					
 					//alert("Id " + Id);
 					var mainval=$('#'+'default_text'+Id+'-'+row).val();
 					//alert("mainval " + mainval);
 					if(mainval=="") 
 						$('#'+'templateRowName'+Id+'-'+row).val("");
 					
 					if ((lovName== "SERVICE_MODEL") || (lovName== "MARKETING_MODEL")) 
 						$('#'+'templateRowName'+Id+'-'+row).val(mainval);
 					
				}
 			
 				function callChange(Id,row,attributename,defaultval) {    
									//$('#'+Id+'searchList'+row).hide();
									//$('#'+'find'+Id+row).click(function () {
										    //alert("Id " + Id);
										    //alert("row " + row);
					  						var mainval=$('#'+'default_text'+Id+'-'+row).val();
					  						var length = $('#'+'default_text'+Id+'-'+row).val().length;
					  						//alert("length "+length);
					  						if (length>=3)
					  						{	 
					  							 //alert(" roWid" + row+Id+'default_value'+row);
					  							 changefilterFunc(''+row+'templateRowName'+Id+'-'+row,mainval,attributename,defaultval);
					  							 //callCompassign("ComponentId0",mainval);
						  						$('#'+'searchBox'+Id+row).hide();
				  						        $('#'+'searchList'+Id+row).show();
					  						}
				  						 	else
				  							{
				  								alert("Enter atleast 3 letters ");
				  							}
					  						 
								    //});
									
 				}	
 				
 				
 				function changeFunc(selectboxName,attributeName,defaultvalue,row) { 
 					
 					var selectBox = document.getElementById(selectboxName);
 				    
 					var selectedValue = selectBox.options[selectBox.selectedIndex].value;
 				    //document.getElementById(selectboxName).innerHTML = "";
 					//alert("TEST");
 					//alert("selectboxName "+selectboxName);
 					//alert("attributeName "+attributeName);
 					//alert("defaultvalue "+defaultvalue);
 					//alert("row "+row);
 					
 					var productLevel3Value ="";
 					
 					var productLevel3Box = document.getElementById('product_level3_'+row); 
 					//alert("productLevel3Box "+productLevel3Box);
 					var productLevel3Code = document.getElementById(productLevel3Box.value);
 					
 					var productLevel3SelectValue = productLevel3Code.value;
 					
 					//alert("productLevel3SelectValue "+productLevel3SelectValue);
 					//var productLevel3Select = document.getElementById(productLevel3Code.value);
 					//var productLevel3SelectValue = productLevel3Select.options[productLevel3Select.selectedIndex].value;
 					//alert("productLevel3SelectValue "+productLevel3SelectValue.value);
 					
 					//alert("productLevel3Code " + productLevel3Code);
 					
 			 		if (selectedValue!=defaultvalue)
 			 			defaultvalue = selectedValue;
 			 		
 					var x ;
 					blockUsrInterface();
 					$.ajax({  
 								type: "POST",
 								async:false,						
 								url: 'CanonE008TemplateProcess.jsp', 
 								data: "action="+"GETLOV"+"&attributename="+ attributeName+"&selectvalue="+"" 
 								+"&productlevel3="+ productLevel3SelectValue+"&userId="+getIdVal("userId"),
 								complete: function(data)
 									{  
 										x =((data.responseText).replace(/^\s+|\s+$/g,''));
 										//alert(x);
 										res=x.split("@@")
 										lovVal=res[0].split("|,");
 										 var defvalue = "";
 										 //alert("lovVal.length "+ lovVal.length)
 										 clearSelectList(selectBox);
 										 for(var i=0;i<lovVal.length;i++)
 										 {
 											 var lovValue=lovVal[i];
 											 lovValue=lovValue.replace('[','').replace('|]','');
 											 defvalue = $.trim(lovValue);
 											 var option = document.createElement("option");
 											    main = lovValue.split("*")
 											    //alert(main);
 											    option.value = $.trim(main[0]);
 											    option.text = $.trim(main[1]);
 											    selectBox.options[i+1] = option;
 											    if (option.value==defaultvalue)
 											    	selectBox.options[i+1].selected = true;
 										 }
 									}  
 							});  //ajax end
 					
 					//return x;  
 					unBlockUsrInterface();		  
 					
 					
 				}

 				function changeFuncSupplier(selectboxName,attributeName,defaultvalue,row) { 
 					
 					var selectBox = document.getElementById(selectboxName);
 				    
 					var selectedValue = selectBox.options[selectBox.selectedIndex].value;
 				    //document.getElementById(selectboxName).innerHTML = "";
 					//alert("TEST");
 					//alert("selectboxName "+selectboxName);
 					//alert("attributeName "+attributeName);
 					//alert("selectedValue "+selectedValue);
 					//alert("defaultvalue "+defaultvalue);
 					var supplierValue ="";
 					
 					var supplierBox = document.getElementById('item_supplier'+row); 
 					var supplierCode = document.getElementById(supplierBox.value);
 					
 					//alert("supplierCode " + supplierCode.value);
 					
	 				if (attributeName="SUPPLIER_SITE") 
	 						supplierValue = supplierCode.value;

	 				if (supplierValue!="")
 					{	
	 			 		if (selectedValue!=defaultvalue)
	 			 			defaultvalue = selectedValue;
	 			 		
	 			 		
	 					var x ;
	 					blockUsrInterface();
	 					$.ajax({  
	 								type: "POST",
	 								async:false,						
	 								url: 'CanonE008TemplateProcess.jsp', 
	 								data: "action="+"GETLOV"+"&attributename="+ attributeName+"&selectvalue="+""+"&suppliercode="+supplierValue 
	 									  +"&userId="+getIdVal("userId"),
	 								complete: function(data)
	 									{  
	 										x =((data.responseText).replace(/^\s+|\s+$/g,''));
	 										//alert(x);
	 										res=x.split("@@")
	 										lovVal=res[0].split("|,");
	 										 var defvalue = "";
	 										 //alert("lovVal.length "+ lovVal.length)
	 										 clearSelectList(selectBox);
	 										 for(var i=0;i<lovVal.length;i++)
	 										 {
	 											 var lovValue=lovVal[i];
	 											 lovValue=lovValue.replace('[','').replace('|]','');
	 											 defvalue = $.trim(lovValue);
	 											 var option = document.createElement("option");
	 											    main = lovValue.split("*")
	 											    option.value = $.trim(main[0]);
	 											    option.text = $.trim(main[1]);
	 											    selectBox.options[i+1] = option;
	 											    if (option.value==defaultvalue)
	 											    	selectBox.options[i+1].selected = true;
	 										 }
	 									}  
	 							});  //ajax end
	 					
	 					//return x;  
	 					unBlockUsrInterface();		  
 					}
 					else
 					{
 						alert("Please enter the Supplier.");	
 					}
 					
 				}
 				
 				function clearSelectList(list) {
 				    // when length is 0, the evaluation will return false.
 				    while (list.options.length) {
 				        // continue to remove the first option until no options remain.
 				        list.remove(0);
 				    }

 				}
 				
 				function callListChange(Id,row,templateId) {           
 					//alert("Id " + Id + " " + row);
 					
 			        $('#'+'searchList'+templateId+row).hide();
 			        $('#'+'searchBox'+templateId+row).show();
 				    
 			  		var selectBox = document.getElementById(Id);
 					//alert(selectBox.value);
 					//alert($("#"+Id+" option:selected").text());
 			        var selectedValue = selectBox.value;
 			        var selectedtext = $("#"+Id+" option:selected").text();  //selectBox.value;
 			        
 					 $("#"+"templateRowName"+templateId+'-'+row).val(selectedValue);
 					 $("#"+"default_text"+templateId+'-'+row).val(selectedtext); 
 				}; 	
 				
 				function changefilterFunc(compid,compval,attributename,defaultvalue) {
 						
 			            //alert("compid "+ compid);
 			            //alert("compval "+ compval);
 			            //alert("attributename "+ attributename);
 			            //alert("defaultvalue "+ defaultvalue);
 			            blockUsrInterface();
 			         	 $.ajax({  
 									type: "POST",
 									async:false,						
 									url: 'CanonE008TemplateProcess.jsp', 
 									data: "action="+"GETLOV"+"&attributename="+ attributename+"&selectvalue="+compval
 										  +"&userId="+getIdVal("userId"),
 									complete: function(data)
 										{  
 											
 										x =((data.responseText).replace(/^\s+|\s+$/g,''));
 										//alert(x);
 										res=x.split("@@")
 										lovVal=res[0].split("|,");
 										 var defvalue = "";
 										 var mainoption ="";
 										 $("#"+compid).empty();
 										 //alert("lovVal.length "+ lovVal.length)
 										 //clearSelectList(selectBox);
 										 var blankoption = $('<option value="">'+'</option>');
 				  				  		 $("#"+compid).append(blankoption);
 										 for(var i=0;i<lovVal.length;i++)
 										 {
 											 var lovValue=lovVal[i];
 											 lovValue=lovValue.replace('[','').replace('|]','');
 											 defvalue = $.trim(lovValue);
 											 var option = document.createElement("option");
 											    main = lovValue.split("*")
 											    option.value = $.trim(main[0]);
 											    option.text = $.trim(main[1]);
 											    
 											    if (option.value==defaultvalue)
 											    	mainoption = $('<option selected ="" value="'+ option.value +'">'+option.text+'</option>');
 											    else
 											    	mainoption = $('<option value="'+ option.value +'">'+option.text+'</option>');
 											    
 					  					        $("#"+compid).append(mainoption);
 					  					        
 										 }
 																							
 										}  
 								});
 			         	unBlockUsrInterface();
 						}
 				   
 				
 				
 			</script>
		<div class="service_btn">
		<table border="0" align="right">
			<tr>
				<td>
				<%
					if ((p_project_number != null) && (canModifyProject)) {
				%>				
				<a class="btnPrj" href="javascript:saveProject()">Save</a>
				<%
					}
				%>

				<%
					if ((p_project_number != null) && (canModifyProject) && (canCreateProject)) {
				%>				
				<a class="btnPrj" href="javascript:addRow()">Add Line</a>
				<a class="btnPrj" href="javascript:deleteItems()">Delete Line</a>
				<%
					}
				%>
				</td>
			</tr>
		</table>
		</div>
	<%
		}
	%>

		<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel" >
		<div class="proj_additional">
 		  <div class="modal-dialog" role="document" style="width: 60%;">
		     <div class="modal-content" style="width: 100%;"> 
 		      <div class="modal-header" style="width: 100%;">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="assignAdditinalAttr()"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">ADDITIONAL ITEM INFORMATION</h4>
		      </div>
		      <div class="modal-body">
				<div class="addtional-model-table" id="canonAddtInfoPrintServices">
 					<div class="panel panel-primary">
					<!-- <div class="panel-body" > -->
						<!-- <table  style="width: 100%; margin: 0px 20px 20px 20px;"> -->
						<div class="additional_header"> 
					    <table> 
							<tbody>
								<tr><th colspan="2">Item Details</th></tr>
	 							<!-- <tr><td>Item Type</td><td><input class="rdl inTxt" readonly type="text" id="ItemType" value=""></td></tr> -->
				    			<tr><td>Item Number</td><td><input class="rdl inTxt" readonly type="text" id="ItemNumber" name="ItemNumber"></td></tr>
				    			<tr><td>Item Description</td><td><input class="rdl inTxt" readonly type="text" id="ItemDescription"></td></tr>
				    			<tr><td><input type="checkbox" id="checkbox1" value=""/> Check to see all Required Attributes</td></tr>  
				    			<tr><td><input type="hidden" class="rdl inTxt" readonly type="text" id="rowIndex" value=""></td></tr>
			    			</tbody>							
			    		</table>    
			    		</div>
			    	<!-- </div> -->
			    		<div class="proj-main-inner">	
						<!-- <table id="addtionalTable" class="Addorder-column" cellspacing="0" width="100%"> -->
						<div class="panel-body">
						<table id="addtionalTable">
				        <thead>
			    	        <tr>
			    	        	 <!-- <th>Required</th> -->
			            		 <th>Category</th>
	    						 <th>Attribute</th>
	    						 <th>Value</th>     
  		            	    </tr>
			        	</thead>
   			        	<tbody>
								<%	/* if (itemList != null)
									{ */	
										//var item_number = document.getElementById('ItemNumber').value;
										
										//CanonE008ItemProcessDAO.ItemInfoRec item=(CanonE008ItemProcessDAO.ItemInfoRec) itemList.get(0);
 										attributeresult = obj0.getProjItemAdditionalAttrValues(new BigDecimal("1"),new BigDecimal("1"));
										attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
 										String strrequired="";
								%>
 									<input  style="display:none;" class="rdl inTxt" type="text" id="attrCount"  name="attrCount" VALUE="<%=attributeList.size()%>">	
 								<%											
										
										for(int i=0;i<attributeList.size();i++) {
											CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(i);
											strrequired="";

 											 if ((itemrec.getAttributereq().equals("YES")) && (!itemrec.getAttributeDefaultValue().equals(""))) 
											{	strrequired="noneditrequired readonly"; //"required";  //"noneditrequired disabled";
											}
											
											if ((itemrec.getAttributereq().equals("YES")) && (itemrec.getAttributeDefaultValue().equals("")))
												strrequired="required"; 
											
											if (itemrec.getAttributereq().equals("NO"))
											{	strrequired="notreq";
											}
														
											 if (!canCreateProject)
											{
												strrequired="noneditrequired readonly";
												//strrequired="noneditrequired disabled";
											} 
 											
											if(itemrec.getAttributelovflag().equals("Y"))
											{	
											String strvalidfeildname = "attributeRowName"+i;
											//addLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattvalueList(attributeRec.getCreatedByName()));
											//addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattvalueList(itemrec.getAttributelovname() ));
	 						

										if ((!itemrec.getAttributelovname().equals("SUPPLIER")) && 
															(!itemrec.getAttributelovname().equals("PRODUCT_LEVEL5")) &&
															(!itemrec.getAttributelovname().equals("SERVICE_MODEL")) &&
															(!itemrec.getAttributelovname().equals("MARKETING_MODEL")) &&
															(!itemrec.getAttributelovname().equals("MATERIAL_GROUP1")) && 
															(!itemrec.getAttributelovname().equals("DEFAULT_SRC_WH")) &&
															(!itemrec.getAttributelovname().equals("TARIFF_CODE")))  
													{	
														//addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattvalueList(itemrec.getAttributelovname()));
														addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattdefvalueList(itemrec.getAttributelovname(),"X"));
		
			 							%>

			 								<tr id="additionalAtt<%=i%>">
											<input  style="display:none;"  class="rdl inTxt" type="text" id="reqlov<%=i%>"  name="reqlovName<%=i%>" VALUE="<%=itemrec.getAttributelovflag() %>">
						                	<input  style="display:none;" class="rdl inTxt" type="text" id="reqRow<%=i%>"  name="reqRowName<%=i%>" VALUE="">
			 								<input  style="display:none;"  class="rdl inTxt" type="text" id="attrName<%=i%>"  name="attrName<%=i%>" VALUE="<%=itemrec.getAttributelovname() %>">
						                	<td><input class="rdl inTxt" readonly type="text" id="attributeRow11<%=i%>"  name="attributeRowName<%=i%>" VALUE="<%=itemrec.getTemplate()%>"></td>
						                	<td><input class="rdl inTxt" readonly type="text" id="attributeRow22<%=i%>"  name="attributeRowName<%=i%>" VALUE="<%=itemrec.getAttribute()%>"></td>
				 							<td align="center"><%=CanonE008ItemProcessHelper.genSelectItemHtmlattribute(attlovs,strvalidfeildname, itemrec.getAttributevalue(),strrequired,itemrec.getAttributelovname(),"0")%></td>
				 							<%-- <td align="center"><%=CanonE008ItemProcessHelper.genSelectHtmlattribute(attlovs,strvalidfeildname, itemrec.getAttributelovname(),strrequired)%></td> --%>
			 								</tr>
			 											
			 							<% } else {  
		 										String defaulttext = ""; //returndefaultText(CanonE008TemplateDAO.getitemattdefvalueList(itemrec.getAttributelovname(),itemrec.getAttributevalue()));
		 								%>

			 								<tr id="additionalAtt<%=i%>">
			 								<input  style="display:none;"  class="rdl inTxt" type="text" id="reqlov<%=i%>"  name="reqlovName<%=i%>" VALUE="<%=itemrec.getAttributelovflag() %>">
			 								<input  style="display:none;"  class="rdl inTxt" type="text" id="reqRow<%=i%>"  name="reqRowName<%=i%>" VALUE="">
			 								<input  style="display:none;"  class="rdl inTxt" type="text" id="attrName<%=i%>"  name="attrName<%=i%>" VALUE="<%=itemrec.getAttributelovname() %>">
						                	<td><input class="rdl inTxt" readonly type="text" id="attributeRow11<%=i%>"  name="attributeRowName<%=i%>" VALUE="<%=itemrec.getTemplate()%>"></td>
						                	<td><input class="rdl inTxt" readonly type="text" id="attributeRow22<%=i%>"  name="attributeRowName<%=i%>" VALUE="<%=itemrec.getAttribute()%>"></td>
				 							<%-- <td align="center"><%=CanonE008ItemProcessHelper.genSelectItemHtmlattribute(attlovs,strvalidfeildname, itemrec.getAttributevalue(),strrequired,itemrec.getAttributelovname())%></td> --%>
											<td align="center">
							                	<div id="searchBox<%=i%>">
							                	    <table  id="filterTable" style="padding:0px;">
							                	    <td style="padding:2px;">
							                	    	<input style=" display:none;"  class="inTxt" type="text" id="<%=strvalidfeildname%>"  name="<%=strvalidfeildname%>" VALUE="">
							                	    	<input class=<%=strrequired%> class="inTxt" type="text"  onchange="checkemptyChangeAttribute(<%=i%>)" id="default_text<%=i%>"  name="default_text<%=i%>" VALUE="<%=defaulttext%>">
							                	    	
							                	    </td>
							                	    <td style="padding:2px;">
							                	    	<button type='button' id="find<%=i%>" onclick="callChangeAttribute(<%=i%>,'<%=itemrec.getAttributelovname()%>','')"><img id="myImg" src="<%=ctxPath%>/e008/images/canonSearch.png"></button>
							                	    	<%-- <input type="image" id="find<%=j%><%=i%>" onclick="callChange(<%=j%>,<%=i%>,'<%=itemrec.getAttributelovname()%>','<%=itemrec.getAttributevalue()%>')" src="<%=ctxPath%>/e008/images/canonSearch.png"> --%>
							                	    </td> 
							                	    </table>  
							                	</div>
							                	    
							                	<div id="searchList<%=i%>" style="display:none;" >
							                		<%=CanonE008ItemProcessHelper.genSelectitemattributedataListHtml(lovs, strvalidfeildname, "",i+strvalidfeildname, ""+i, "" )%>
							                	</div>
											</td>
			 								</tr>
				 						
										<%  }

										%>	


								<%
									}
									else
									{
								%>	
									<tr id="additionalAtt<%=i%>">
									<input  style="display:none;"  class="rdl inTxt" type="text" id="reqlov<%=i%>"  name="reqlovName<%=i%>" VALUE="<%=itemrec.getAttributelovflag() %>">
	 								<input style="display:none;" class="rdl inTxt" type="text" id="reqRow<%=i%>"  name="reqRowName<%=i%>" VALUE="">
	 								<input  style="display:none;"  class="rdl inTxt" type="text" id="attrName<%=i%>"  name="attrName<%=i%>" VALUE="<%=itemrec.getAttributelovname() %>">
				                	<td><input class="rdl inTxt" type="text" id="attributeRow11<%=i%>"  name="attributeRowName<%=i%>" VALUE="<%=itemrec.getTemplate()%>"></td>
				                	<td><input class="rdl inTxt" type="text" id="attributeRow22<%=i%>"  name="attributeRowName<%=i%>" VALUE="<%=itemrec.getAttribute()%>"></td>
				                	<td><input class=<%=strrequired%> type="text" id="attributeRowName<%=i%>"  name="attributeRowName<%=i%>" VALUE="<%=itemrec.getAttributevalue()%>"></td>
									</tr>
								 <% } %>
								 
					            <%  } %>
					            
 		                 	</tbody>
  	            		</table>
  	            		</div>
						</div>
						
  	            		<style>
						     .panel-body
							{
							    max-height: 450px;
							    overflow-y: scroll;
							} 
							.Addorder-column td
							{
							    padding:0 15px 0 15px;
							}
/* 
							.modal_fade { height: 100px; }

							.modal_fade { width: 150px; } */
							
						</style>

  					    <script>
 
	  					  $(document).ready(function () {
	  						
		  					  $('#checkbox1').change(function () {
		  					        if (!this.checked) 
		  					        	showAll();
		  					        else 
		  					        	showReq();
		  						    });
		  					});  					        
  					  
  	  					  function showReq() {
			  						//alert("test");
			  						var oTable = document.getElementById("addtionalTable");
			  						//var oRows = oTable.getElementsByTagName("tr");
			  						var oRowscount = document.getElementById("attrCount").value;
								    //alert("oRowscount " + oRowscount);
								    //alert("oRows.length " + oRows.length);
								    var z;
								    var k;
			  						for( var i=0; i < oRowscount; i++ ) {
			  							var z = "";
			  							//k=i-1;
			  							var z=document.getElementById('reqRow'+i).value;
			  							//alert("z "+ z);
			  							//var cells = oRows[i].getElementsByTagName('td');
			  							//var textfound = cells[1].innerHTML.indexOf("attributeRow22");
			  							//alert("cells "+ textfound);
										var list = document.getElementById("additionalAtt"+i);
										//alert("list " + list);
										
			  							if(z.substr(0,1).trim() =="Y")
											list.style.display = "";
										else
											list.style.display = "none";
												
										
/* 			  							if (textfound > -1)
			  							{	
				  							if(z.substr(0,1).trim() =="Y") 
				  								oRows[i].style.display = ""
				  							else
				  								oRows[i].style.display = "none"
			  							} */		  								
			  						} 
			  					}


   	  					  function showAll() {
		  						//alert("test");
		  						var oTable = document.getElementById("addtionalTable");
		  						var oRows = oTable.getElementsByTagName("tr");
							    //alert("length " + oRows.length);		
							    var z;
		  						for( var i=0; i < oRows.length; i++ ) {
		  								oRows[i].style.display = ""
		  						}			
		  					}
   	  					    
 		  				   function assignId(rowId){
 		  						
 		  						//alert('rowId'+rowId);
				    			//attRowId = rowId;
				    			//var table = $('#example').DataTable();
				    			
				    			//alert( 'Row index: '+ table.row( this ).index() );
				    			
				    			$( "#checkbox1" ).prop( "checked", false );
				    			showAll();
 			    			    var reqval;
 			    			    var defaultvalue;
 			    			    var countrecord = document.getElementById('countRow').value;
 			    			    //alert('Count Row:: '+countrecord);
 			    			   
 			    			    var oTable = document.getElementById("addtionalTable");
			  					//var oRows = oTable.getElementsByTagName("tr");
			  					var oRows = document.getElementById("attrCount").value;
			  					document.getElementById('rowIndex').value=rowId;
		  					
 			    			    //alert('New update rowId'+rowId);
			    				//document.getElementById('ItemType').value=document.getElementById('item_type'+rowId).value;
			    				document.getElementById('ItemNumber').value=document.getElementById('templateRowName'+rowId+'-'+'0').value;
			    				document.getElementById('ItemDescription').value= document.getElementById('templateRowName'+rowId+'-'+'1').value;  //rowId
			    				//var template_id = document.getElementById('template_id_'+rowId).value;
			    				//var item_id = document.getElementById('item_id_'+rowId).value;
			    				//alert('new oRows'+oRows);
                             
		    					for(i=0;i<oRows;i++)  {
		    						//alert("value " + document.getElementById('templateaddRow'+rowId+i).value );
		    						var lovname = document.getElementById('attributeRow22'+i).value;
						 			var listcode="";
						 			var listname="";
						 			var	mainoption="";
									var lovflag = document.getElementById('reqlov'+i).value;
									
									//if ((lovflag=="Y") && (document.getElementById('templateaddRow'+rowId+i).value!="") && (document.getElementById('templateaddRow'+rowId+i).value!="NULL"))
									
									/* if ((i>=60) && (i<=65))
									{	
										alert("Row "+rowId+i);
										alert("value "+document.getElementById('csatemplateaddRow'+rowId+i).value);
										alert("value "+document.getElementById('templateaddRow'+rowId+i).value);
										alert("value "+document.getElementById('templatedefaddRow'+rowId+i).value);
									} */

									if (lovflag=="Y") 
										{
										 		  
										  if ((document.getElementById('templateaddRow'+rowId+'-'+i).value!="") && (document.getElementById('templateaddRow'+rowId+'-'+i).value!="NULL"))
											{
												//alert("lovname " + lovname + " : "+i );
										  		var reslist=document.getElementById('templateaddRow'+rowId+'-'+i).value.split("**");
											  	if (reslist!="")
											  	{	listcode=reslist[0];
											  		listname = reslist[1];
											  	}
											}
										  
										  	//alert("reslist " + reslist );
										  	
											if ((lovname!="Supplier") && 
													(lovname!="Supplier Site") &&
													(lovname!="Service Model") && 
													(lovname!="Material Group1") && 
													(lovname!="Default Source Warehouse") &&
													(lovname!="Tariff Code"))
										  	{	
										  		$("#attributeRowName"+i).empty();
									 			
										  		mainoption = $('<option selected ="" value="'+ listcode +'">'+listname+'</option>');
					  					       	$("#attributeRowName"+i).append(mainoption);
										  	}
										  	else
										  	{
										  		//alert("test");
										  		document.getElementById('attributeRowName'+i).value = listcode;
										  		document.getElementById('default_text'+i).value = listname;
										  		//$("#attributeRowName"+i).val(listcode);
										  		//$("#default_text"+i).val(listname);
										  	}
									
										}
									else
										{
				    						if ((document.getElementById('templateaddRow'+rowId+'-'+i).value != null) && (document.getElementById('templateaddRow'+rowId+'-'+i).value!="NULL"))			    					 
				    							document.getElementById('attributeRowName'+i).value=document.getElementById('templateaddRow'+rowId+'-'+i).value;
				    						else
				    							document.getElementById('attributeRowName'+i).value="";
			    							//$("#attributeRowName"+i).val(document.getElementById('templateaddRow'+rowId+i).value);
			    						 	//document.getElementById('attributeRow'+i).className = document.getElementById('templateReqaddRow'+rowId+i).value;
										}
									
									
									reqval="";
									defaultvalue = "";
 		    						if ((document.getElementById('templateReqaddRow'+rowId+'-'+i).value != null) && (document.getElementById('templateReqaddRow'+rowId+'-'+i).value!="")) 	
	    							{
 		    							reqval = document.getElementById('templateReqaddRow'+rowId+'-'+i).value;
 		    							defaultvalue = document.getElementById('templatedefaddRow'+rowId+'-'+i).value;
	    							}
		    						
	    		        			
		    						document.getElementById('reqRow'+i).value=reqval;
		    						
		    						if ((reqval=="Y") && (defaultvalue != "NULL"))
		    							{
	    		        				$("#attributeRowName"+i).css({'backgroundColor':'#cccccc'});
		    						    $("#attributeRowName"+i).addClass("noneditrequired disabled");

		    						    if(document.getElementById('reqlov'+i).value=="Y")
		    						    	$("#attributeRowName"+i).removeAttr("onfocus");
		    						    
		    							}
	    		        			
	    		        			if ((reqval=="Y") && (defaultvalue == "NULL"))
	    		        				{
	    		        				$("#attributeRowName"+i).css({'backgroundColor':'#ffff00'});
	    		        				$("#attributeRowName"+i).removeClass("noneditrequired disabled");

	    		        				//alert('Req rowId'+rowId);
	    		        				if(document.getElementById('reqlov'+i).value=="Y")
	    		        					$("#attributeRowName"+i).attr('onfocus', 'changeFunc("'+'attributeRowName'+i+'","'+document.getElementById('attrName'+i).value+'","",'+rowId+');');
	    		        				}

	    		        			if ((reqval=="N") && (defaultvalue == "NULL"))
    		        				{
	    		        				$("#attributeRowName"+i).css({'backgroundColor':'#FFFFFF'});

    		        				//alert('Req rowId'+rowId);
    		        				if(document.getElementById('reqlov'+i).value=="Y")
    		        					$("#attributeRowName"+i).attr('onfocus', 'changeFunc("'+'attributeRowName'+i+'","'+document.getElementById('attrName'+i).value+'","",'+rowId+');');
    		        				}
	    		        			
		    						if ((reqval=="YE") && (defaultvalue != "NULL"))
	    		        				{
		    							$("#attributeRowName"+i).css({'backgroundColor':'#cccccc'});
		    							$("#attributeRowName"+i).addClass("noneditrequired disabled");
	    		        				
	    		        				}
	    		        			
	    		        			if ((reqval=="YE") && (defaultvalue == "NULL"))
	    		        				{ 
	    		        				$("#attributeRowName"+i).css({'backgroundColor':'#ffff00'});
	    		        			    $("#attributeRowName"+i).removeClass("noneditrequired disabled");
	    		        				
	    		        				}

	    		        			if (reqval=="N")
	    		        				$("#attributeRowName"+i).css({'backgroundColor':'#FFFFFF'});
	    		        			
	    		        			//if (reqval=="Y")
	    		        			//if (reqval=="YE")
	    		        			if (reqval.substring(1,2)=="E")	
	    		        				$("#attributeRowName"+i).css({'borderColor':'#cd0a0a','borderStyle':'solid','borderWidth':'3px'});
	    		        			else
	    		        				$("#attributeRowName"+i).css({'borderColor':'#cccccc','borderStyle':'solid','borderWidth':'1px'});
 		    					
		    					} 
			    				
			    				//alert('done::');
			    			
				    			}
							
		    					
		    					
		    					
				 				function checkemptyChangeAttribute(row) {  
				 					
				 					//alert("Id " + Id);
				 					var mainval=$('#'+'default_text'+row).val();
				 					//alert("mainval " + mainval);
				 					if(mainval=="") 
				 						$('#'+'attributeRowName'+row).val("");
				 				
								}
				 					
				 				function callChangeAttribute(row,attributename,defaultval) {    
													//$('#'+Id+'searchList'+row).hide();
													//$('#'+'find'+row).click(function () {
														    //alert("Id " + Id);
														    //alert("row " + row);
									  						 var mainval=$('#'+'default_text'+row).val();
									  						 var length = $('#'+'default_text'+row).val().length;
									  						 //alert("length "+length);
									  						 if (length>=3)
									  						{	 
									  							 //alert(" roWid" + row+Id+'default_value'+row);
									  							 changefilterFunc(''+row+'attributeRowName'+row,mainval,attributename,defaultval);
									  							 //callCompassign("ComponentId0",mainval);
										  						$('#'+'searchBox'+row).hide();
								  						        $('#'+'searchList'+row).show();
									  						}
								  						 	else
								  							{
								  								alert("Enter atleast 3 letters ");
								  							}
									  						 
												    //});
													
				 				}	
				 				
				 				function clearSelectListAttribute(list) {
				 				    // when length is 0, the evaluation will return false.
				 				    while (list.options.length) {
				 				        // continue to remove the first option until no options remain.
				 				        list.remove(0);
				 				    }

				 				}
				 				
				 				function callListChangeAttribute(Id,row,templateId) {           
				 					//alert("Id " + Id + " " + row);
				 					
				 			        $('#'+'searchList'+row).hide();
				 			        $('#'+'searchBox'+row).show();
				 				    
				 			  		var selectBox = document.getElementById(Id);
				 					//alert(selectBox.value);
				 					//alert($("#"+Id+" option:selected").text());
				 			        var selectedValue = selectBox.value;
				 			        var selectedtext = $("#"+Id+" option:selected").text();  //selectBox.value;
				 			        
				 					 $("#"+"attributeRowName"+row).val(selectedValue);
				 					 $("#"+"default_text"+row).val(selectedtext); 
				 				}; 	
				 				
			      	    </script>		
		
						<div class="row EditForm" style="display: none;">				
							<div class="col-sm-12 col-md-12 col-lg-12">
							</div>							
						</div>
					
 					</div>
<!--					</div> -->
				</div>      
		      
		      </div>
		      <div class="modal-footer">
		      		<input type="hidden" name="addButtonPopupId" id="addButtonPopupId" value=""/>
		      		<button type="button" class="btnPrj" data-dismiss="modal" aria-label="Close" onclick="assignAdditinalAttr()"><span aria-hidden="true">OK</span></button>
					<!-- <a class="btnPrj" id="addId" tabindex="0" aria-controls="" onclick="assignAdditinalAttr()"><span>OK</span></a> -->
					<!-- <a style="display: none;" class="dt-button buttons-create pull-right " tabindex="0" aria-controls=""><span>ADD</span></a> -->
		      </div>
 		    </div> 
		  </div> 
		  </div>
		</div>


	<div style="display: none;"  id="validate-comment-dialog" title="Approve Comment" style="width: 300px; height: 500px">
		<p class="message">Project validate successfully!</P>
		<p>Approve comments:</P>
		<div>
			<textarea name="validate_comments" id="validate_comments"	style="width: 380px; height: 130px"></textarea>
			<div></div>
		</div>
	</div>		

	<div style="display: none;"  id="approve-comment-dialog" title="Approve Comment" style="width: 300px; height: 500px">
		<p>Please input approve comments below.</P>
		<p>Approve comments:</P>
		<div>
			<textarea name="approve_comments" id="approve_comments"	style="width: 380px; height: 130px" maxlength="240"></textarea>
		</div>
	</div>

	<div style="display: none;"  id="reject-comment-dialog" title="Reject Comment"	style="width: 300px; height: 500px">
		<p>Please input reject comments below.</P>
		<p>Reject comments:</P>
		<div>
			<textarea name="reject_comments" id="reject_comments" style="width: 380px; height: 130px" maxlength="240"></textarea>
			<div></div>
		</div>
	</div>		


	<div style="display: none;"  id="add-note-dialog" title="Note Comment"	style="width: 300px; height: 200px">
		<p>Please input Notes below.</P>
		<p>Notes:</P>
		<div>
			<textarea name="note_comments" id="note_comments"
				style="width: 380px; height: 130px"></textarea>
			<div></div>

		</div>
	</div>
		
	<div class="modal fade" id="myBOMModal"  role="dialog" aria-labelledby="myModalLabel" >
 		<div class="proj_component">
		<div>
 		  <div class="modal-dialog" role="document" style="width: 100%;">
		     <div class="modal-content" style="width: 90%;"> 
 		      <div class="modal-header" style="width: 100%;">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="submitBOM()"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">BOM ASSIGNMENTS</h4>
		      </div>
		      <div class="modal-body">
				<div class="addtional-model-table" id="canonAddtInfoPrintServices">
 					<div class="panel panel-primary">
						<input type="hidden" name="countCompRow" id="countCompRow" value="20">
						<div class="additional_header"> 
					    <table> 
							<tbody>
								<tr><th colspan="2">SET Item Details</th></tr>
								<tr>
								<td>
							    <table border="0" class="additional_header1" > 
									<tr type="hidden"><td></td><td><input type="hidden" style="width: 200px;" class="rdl inTxt" readonly type="text" id="bomItemId" name="bomItemid"></td></tr>
		 							<!-- <tr><td>BOM Item Type</td><td><input  style="width: 200px;" class="rdl inTxt" readonly type="text" id="bomItemType" value=""></td></tr> -->
					    			<tr><td>Item Number</td><td><input style="width: 200px;" class="rdl inTxt" readonly type="text" id="bomItemNumber" name="bomItemNumber"></td></tr>
					    			<tr><td>Item Description</td><td><input style="width: 200px;" class="rdl inTxt" readonly type="text" id="bomItemDescription"></td></tr>
								</table>
								</td> 
								<td>
							    <table border="0"  class="additional_header1"> 
									<tr><td>BOM Instructions</td><td><textarea  style="width: 500px; height: 107px;"  class="bottom" rows="9" cols="30" name="bomInstructions" id="bomInstructions"></textarea></td></tr>
								</table>
								</td>
								</tr>
			    			</tbody>							
			    		</table>    
			    		</div>
		    		 <div class="proj-component-inner"> 
						<div class="panel-body">
						<table id="BOMaddtionalTable">
				        <thead> 
			    	        <tr>
			            		 <th >Component Number</th>
	    						 <th >Component Description</th>
	    						 <th >Manufacturer</th>
	    						 <th >QTY</th>
	    						 <th >Merch Type</th>
	    						 <th >Prod Code</th>
	    						 <th >Item Type</th>
	    						 <th >Cost</th>     
  		            	    </tr>
			        	 </thead>
    			        	 <tbody> 
								<%	/* if (itemList != null)
									{ */	
										//String item_number = request.getParameter ("templateSetRowName00"); //document.getElementById('ItemNumber').value;
										
										//CanonE008ItemProcessDAO.ItemInfoRec item=(CanonE008ItemProcessDAO.ItemInfoRec) itemList.get(0);
 										//result = obj0.getbomItems(new BigDecimal("1101"));
 										//bomItemList = (List) CanonE008ItemProcessUtil.first(result);
 										//String strrequired="";
										//addtionalAttList = bomItemList.size();
										for(int i=0;i<15;i++) {
											//CanonE008ItemProcessDAO.getCompItems bomitemrec=(CanonE008ItemProcessDAO.getCompItems) bomItemList.get(i);
											String strcomp ="ComponentId"+i;
											String strcomplist = "componentList"+i;
											String strrow = ""+ i;
	 							%>
	 								<tr class='<%= i%2==0? "even" : "odd" %>'>
					                <%-- <td align="center"><input type="checkbox" id="select_comp<%=i%>" class="select_comp" name="select_comp<%=i%>"></td> --%>
					                <%-- <td><%=CanonE008ItemProcessHelper.genSelectItemdataListHtml(lovs,strcomplist, "",strcomp, strrow, "Select Component" )%></td> --%> 
				                	<td>
				                	<div id="searchBox<%=i%>"><input style="width: 120px;" class="inTxt" type="text" id="selectCompId<%=i%>"  name="selectCompId<%=i%>" VALUE="">
				                		<button type='button' id="cancel<%=i%>"><img id="myImg" src="<%=ctxPath%>/e008/images/canonSearch.png"></button></div>
				                	<div id="searchList<%=i%>"><%=CanonE008ItemProcessHelper.genSelectItemdataListHtml(lovs,strcomplist, "",strcomp, strrow, "Select Component" )%></div>
				                	</td>
				                	<td><div><input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomCompDesc<%=i%>"  name="bomCompDesc<%=i%>" VALUE=""></div></td>
				                	<td><input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomManugacturer<%=i%>"  name="bomManugacturer<%=i%>" VALUE=""></td>
				                	<td><input style="width: 30px; text-align: center;" class="inTxt" type="text" id="bomQty<%=i%>"  name="bomQty<%=i%>" VALUE=""></td>
				                	<td><input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomMerchType<%=i%>"  name="bomMerchType<%=i%>" VALUE=""></td>
				                	<td><input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomProdCode<%=i%>"  name="bomProdCode<%=i%>" VALUE=""></td>
				                	<td><input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomItemType<%=i%>"  name="bomItemType<%=i%>" VALUE=""></td>
				                	<td><input style="width: 70px; text-align: right;" class="rdl inTxt" readonly type="text" id="bomCost<%=i%>"  name="bomCost<%=i%>" VALUE=""></td>
	 								</tr>
								 <% } %>
								 
					            
 		                 	</tbody> 
   	            		</table>
  	            		</div>
						</div>
						
  	            		<style>
						    /*  .panel-body
							{
							    max-height: 450px;
							    overflow-y: scroll;
							} 
							.Addorder-column td
							{
							    padding:0 15px 0 15px;
							}
							td.bottom {
										    vertical-align: top;
										    border: 1px solid green;
										} */
/* 
							.modal_fade { height: 100px; }

							.modal_fade { width: 150px; } */
							
						</style>
						
  					    <script>

  						/*   $(document).ready( function() {

  							  var table = $('#BOMaddtionalTable').DataTable( {
		  				        scrollY:        "600px",
		  				        scrollX:        true,
		  				        scrollCollapse: true,
		  				        searching: false,
		  				        paging:         false,
		  				    	} );
  						    
  						  }); */  					    


  							$('#searchList0').hide();
  							$('#searchList1').hide();
  							$('#searchList2').hide();
  							$('#searchList3').hide();
  							$('#searchList4').hide();
  							$('#searchList5').hide();
  							$('#searchList6').hide();
  							$('#searchList7').hide();
  							$('#searchList8').hide();
  							$('#searchList9').hide();
  							$('#searchList10').hide();
  							$('#searchList11').hide();
  							$('#searchList12').hide();
  							$('#searchList13').hide();
  							$('#searchList14').hide();

  							$('#selectCompId0').keyup('input', function() { 
  								if ($(this).val()=="")
  							    	{
 										$("#bomCompDesc0").val("");
										$("#bomManugacturer0").val("");
 										$("#bomMerchType0").val("");
 										$("#bomProdCode0").val("");
 										$("#bomItemType0").val("");
 										$("#bomCost0").val("");
  							    	
  							    	}
  							});
  							$('#selectCompId1').keyup('input', function() { 
  								if ($(this).val()=="")
  							    	{
 										$("#bomCompDesc1").val("");
										$("#bomManugacturer1").val("");
 										$("#bomMerchType1").val("");
 										$("#bomProdCode1").val("");
 										$("#bomItemType1").val("");
 										$("#bomCost1").val("");
  							    	
  							    	}
  							});$('#selectCompId2').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc2").val("");
										$("#bomManugacturer2").val("");
										$("#bomMerchType2").val("");
										$("#bomProdCode2").val("");
										$("#bomItemType2").val("");
										$("#bomCost2").val("");
							    	
							    	}
							});$('#selectCompId3').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc3").val("");
										$("#bomManugacturer3").val("");
										$("#bomMerchType3").val("");
										$("#bomProdCode3").val("");
										$("#bomItemType3").val("");
										$("#bomCost3").val("");
							    	
							    	}
							});$('#selectCompId4').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc4").val("");
										$("#bomMerchType4").val("");
										$("#bomProdCode4").val("");
										$("#bomItemType4").val("");
										$("#bomCost4").val("");
							    	
							    	}
							});
  							$('#selectCompId5').keyup('input', function() { 
  								if ($(this).val()=="")
  							    	{
 										$("#bomCompDesc5").val("");
										$("#bomManugacturer5").val("");
 										$("#bomMerchType5").val("");
 										$("#bomProdCode5").val("");
 										$("#bomItemType5").val("");
 										$("#bomCost5").val("");
  							    	
  							    	}
  							});
  							$('#selectCompId6').keyup('input', function() { 
  								if ($(this).val()=="")
  							    	{
 										$("#bomCompDesc6").val("");
										$("#bomManugacturer6").val("");
 										$("#bomMerchType6").val("");
 										$("#bomProdCode6").val("");
 										$("#bomItemType6").val("");
 										$("#bomCost6").val("");
  							    	
  							    	}
  							});$('#selectCompId7').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc7").val("");
										$("#bomManugacturer7").val("");
										$("#bomMerchType7").val("");
										$("#bomProdCode7").val("");
										$("#bomItemType7").val("");
										$("#bomCost7").val("");
							    	
							    	}
							});$('#selectCompId8').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc8").val("");
										$("#bomManugacturer8").val("");
										$("#bomMerchType8").val("");
										$("#bomProdCode8").val("");
										$("#bomItemType8").val("");
										$("#bomCost8").val("");
							    	
							    	}
							});$('#selectCompId9').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc9").val("");
										$("#bomMerchType9").val("");
										$("#bomProdCode9").val("");
										$("#bomItemType9").val("");
										$("#bomCost9").val("");
							    	
							    	}
							});  	
  							$('#selectCompId10').keyup('input', function() { 
  								if ($(this).val()=="")
  							    	{
 										$("#bomCompDesc10").val("");
										$("#bomManugacturer10").val("");
 										$("#bomMerchType10").val("");
 										$("#bomProdCode10").val("");
 										$("#bomItemType10").val("");
 										$("#bomCost10").val("");
  							    	
  							    	}
  							});
  							$('#selectCompId11').keyup('input', function() { 
  								if ($(this).val()=="")
  							    	{
 										$("#bomCompDesc11").val("");
										$("#bomManugacturer11").val("");
 										$("#bomMerchType11").val("");
 										$("#bomProdCode11").val("");
 										$("#bomItemType11").val("");
 										$("#bomCost11").val("");
  							    	
  							    	}
  							});$('#selectCompId12').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc12").val("");
										$("#bomManugacturer12").val("");
										$("#bomMerchType12").val("");
										$("#bomProdCode12").val("");
										$("#bomItemType12").val("");
										$("#bomCost12").val("");
							    	
							    	}
							});$('#selectCompId13').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc13").val("");
										$("#bomManugacturer13").val("");
										$("#bomMerchType13").val("");
										$("#bomProdCode13").val("");
										$("#bomItemType13").val("");
										$("#bomCost13").val("");
							    	
							    	}
							});$('#selectCompId14').keyup('input', function() { 
  								if ($(this).val()=="")
							    	{
										$("#bomCompDesc14").val("");
										$("#bomMerchType14").val("");
										$("#bomProdCode14").val("");
										$("#bomItemType14").val("");
										$("#bomCost14").val("");
							    	
							    	}
							});
							
							
  							$('#searchList0').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList0').hide();
  						            $('#searchBox0').show();
  						        //}
  						    });
  							$('#searchList1').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList1').hide();
  						            $('#searchBox1').show();
  						        //}
  						    });
  							$('#searchList2').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList2').hide();
  						            $('#searchBox2').show();
  						        //}
  						    });
  							$('#searchList3').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList3').hide();
  						            $('#searchBox3').show();
  						        //}
  						    });
  							$('#searchList4').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList4').hide();
  						            $('#searchBox4').show();
  						        //}
  						    });
  							$('#searchList5').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList5').hide();
  						            $('#searchBox5').show();
  						        //}
  						    });
  							$('#searchList6').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList6').hide();
  						            $('#searchBox6').show();
  						        //}
  						    });
  							$('#searchList7').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList7').hide();
  						            $('#searchBox7').show();
  						        //}
  						    });
  							$('#searchList8').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList8').hide();
  						            $('#searchBox8').show();
  						        //}
  						    });
  							$('#searchList9').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList9').hide();
  						            $('#searchBox9').show();
  						        //}
  						    });
  							$('#searchList10').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList10').hide();
  						            $('#searchBox10').show();
  						        //}
  						    });
  							$('#searchList11').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList11').hide();
  						            $('#searchBox11').show();
  						        //}
  						    });
  							$('#searchList12').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList12').hide();
  						            $('#searchBox12').show();
  						        //}
  						    });
  							$('#searchList13').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList13').hide();
  						            $('#searchBox13').show();
  						        //}
  						    });
  							$('#searchList14').change(function() {
  						        //if ($(this).val() === 'Create your own question') {
  						            $('#searchList14').hide();
  						            $('#searchBox14').show();
  						        //}
  						    });

  							
  							$('#cancel0').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId0').val();
			  						 var length = $('#selectCompId0').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						{	 
			  						 	callCompassign("ComponentId0",mainval);
				  						 $('#searchBox0').hide();
		  						        $('#searchList0').show();
			  						}
		  						 else
		  							{
		  							alert("Enter atleast 3 letters ");
		  							}
			  						 
						    });
  							$('#cancel1').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId1').val();
			  						 var length = $('#selectCompId1').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						{	 
			  							 callCompassign("ComponentId1",mainval);
				  						 $('#searchBox1').hide();
		  						        $('#searchList1').show(); 
			  						}
				  					else
			  							{
			  							alert("Enter atleast 3 letters ");
			  							} 
						    });
  							$('#cancel2').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId2').val();
			  						 var length = $('#selectCompId2').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {		 
			  						 	callCompassign("ComponentId2",mainval);
				  						 $('#searchBox2').hide();
		  						        $('#searchList2').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
  							$('#cancel3').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId3').val();
			  						 var length = $('#selectCompId3').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						{			 
			  						 	callCompassign("ComponentId3",mainval);
				  						 $('#searchBox3').hide();
		  						        $('#searchList3').show(); 
			  						}
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
  							$('#cancel4').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId4').val();
			  						 var length = $('#selectCompId4').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  						 	callCompassign("ComponentId4",mainval);
				  						 $('#searchBox4').hide();
		  						        $('#searchList4').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
  							$('#cancel5').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId5').val();
			  						 var length = $('#selectCompId5').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						{	 
			  						 	callCompassign("ComponentId5",mainval);
			  						 	$('#searchBox5').hide();
	  						        	$('#searchList5').show();
			  						}
			  						 else
			  							{
			  							alert("Enter atleast 3 letters ");
			  							}
			  						 
						    });
 							$('#cancel6').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId6').val();
			  						 var length = $('#selectCompId6').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  							 callCompassign("ComponentId6",mainval);
				  						 $('#searchBox6').hide();
		    						     $('#searchList6').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
 							$('#cancel7').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId7').val();
			  						 var length = $('#selectCompId7').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  						 	callCompassign("ComponentId7",mainval);
				  						 $('#searchBox7').hide();
		  						        $('#searchList7').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
 							$('#cancel8').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId8').val();
			  						 var length = $('#selectCompId8').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  						 	callCompassign("ComponentId8",mainval);
				  						 $('#searchBox8').hide();
		  						        $('#searchList8').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
 							$('#cancel9').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId9').val();
			  						 var length = $('#selectCompId9').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  						 	callCompassign("ComponentId9",mainval);
				  						 $('#searchBox9').hide();
		  						        $('#searchList9').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
  							$('#cancel10').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId10').val();
			  						 var length = $('#selectCompId10').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  						 	callCompassign("ComponentId10",mainval);
				  						 $('#searchBox10').hide();
		  						        $('#searchList10').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
 							$('#cancel11').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId11').val();
			  						 var length = $('#selectCompId11').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						{ 
			  							 callCompassign("ComponentId11",mainval);
				  						 $('#searchBox11').hide();
		  						        $('#searchList11').show(); 
			  						}
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
 							$('#cancel12').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId12').val();
			  						 var length = $('#selectCompId12').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						{	 
			  						 	callCompassign("ComponentId12",mainval);
				  						 $('#searchBox12').hide();
		  						        $('#searchList12').show(); 
			  						}
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
 							$('#cancel13').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId13').val();
			  						 var length = $('#selectCompId13').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  						 	callCompassign("ComponentId13",mainval);
				  						 $('#searchBox13').hide();
		  						        $('#searchList13').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });
 							$('#cancel14').click(function () {
								 //alert("11");
			  						 var mainval=$('#selectCompId14').val();
			  						 var length = $('#selectCompId14').val().length;
			  						//alert("length "+length);
			  						 if (length>=3)
			  						 {	 
			  						 	callCompassign("ComponentId14",mainval);
				  						 $('#searchBox14').hide();
		  						        $('#searchList14').show(); 
			  						 }
				  					else
		  							{
			  							alert("Enter atleast 3 letters ");
		  							} 
			  						 
						    });

		  					 function callCompassign(compid,compval) {
	 		  						
		  						 var url = encodeURI('<%=ctxPath%>/e008/jsp/CanonE008ItemWorkbenchProcess.jsp?action='+'GETCOMPLIST'+'&compList='+compval);   
		  			             //alert(compid);         
		  			            	 $.ajax({  
		  									type: "POST",
		  									async:false,						
		  									url: url, 		
		  									data: getFormData(),
		  									complete: function(data)
		  										{  
		  											x =((data.responseText).replace(/^\s+|\s+$/g,''));
		  											res=x.split("@@");
		  											//alert("result " + res);
		  											 templateAttrVal=res[0].split(",");
		  											 $("#"+compid).empty();
		  											 var blankoption = $('<option value="">'+'</option>');
	  				  					        	 $("#"+compid).append(blankoption);
		  											 for(var i=0;i<templateAttrVal.length;i++)
		  											 {	
		  												var lovValue=templateAttrVal[i];
		  												//alert("lovValue " + lovValue.replace('[','').replace(']',''));
		  												lovValue= lovValue.replace('[','').replace(']','');
		  												var option = $('<option value="'+ lovValue +'">'+lovValue+'</option>');
		  				  					        	$("#"+compid).append(option);
		  				  					        	
		  											 }	 														
		  										}  
		  								});
				    			
					    			}   
		  					   
		  					   
	  					    function assignBOMId(rowId) {
	 		  						
	 		  						//alert('rowId '+rowId);
					    			//attRowId = rowId;
					    			var reqval;
	 			    			    var defaultvalue;
	 			    			    
	 			    			    //alert('Count Row:: '+countrecord);
	 			    			   
				    			    var oTable = document.getElementById("BOMaddtionalTable");
				  					var oRows = oTable.getElementsByTagName("tr");
				  					document.getElementById('bomItemId').value=document.getElementById('item_id_'+rowId).value;
				  					
				  					var strbomItemId = document.getElementById('item_id_'+rowId).value;
				  					
				  					//alert('bomItemId '+strbomItemId );
				  					
				    				document.getElementById('bomItemNumber').value=document.getElementById('templateRowName'+rowId+'-'+'0').value;
				    				document.getElementById('bomItemDescription').value= document.getElementById('templateRowName'+rowId+'-'+'1').value;  //rowId
									
				    				var countrecord = document.getElementById(strbomItemId+'bomCompcount').value;
				    				
									//result = obj0.getbomItems(new BigDecimal("1101"));
									//bomItemList = (List) CanonE008ItemProcessUtil.first(result);
									//String strrequired="";
									//addtionalAttList = bomItemList.size();

									for(i=0;i<15;i++) {
										
										if (i<countrecord)
										{	
											if ((document.getElementById(strbomItemId+'ComponentId'+i).value != null) && (document.getElementById(strbomItemId+'ComponentId'+i).value.toUpperCase()!="NULL"))
												//document.getElementById('ComponentId'+i).value=document.getElementById(strbomItemId+'ComponentId'+i).value;
												document.getElementById('selectCompId'+i).value=document.getElementById(strbomItemId+'ComponentId'+i).value;
											else
												//document.getElementById('ComponentId'+i).value="";
											    document.getElementById('selectCompId'+i).value="";
											
											if ((document.getElementById(strbomItemId+'bomCompDesc'+i).value != null) && (document.getElementById(strbomItemId+'bomCompDesc'+i).value.toUpperCase()!="NULL"))
												document.getElementById('bomCompDesc'+i).value=document.getElementById(strbomItemId+'bomCompDesc'+i).value;
											else
												document.getElementById('bomCompDesc'+i).value="";
											
											if ((document.getElementById(strbomItemId+'bomManugacturer'+i).value != null) && (document.getElementById(strbomItemId+'bomManugacturer'+i).value.toUpperCase()!="NULL"))
												document.getElementById('bomManugacturer'+i).value=document.getElementById(strbomItemId+'bomManugacturer'+i).value;
											else
												document.getElementById('bomManugacturer'+i).value="";
											
											if ((document.getElementById(strbomItemId+'bomQty'+i).value != null) && (document.getElementById(strbomItemId+'bomQty'+i).value.toUpperCase()!="NULL"))
												document.getElementById('bomQty'+i).value=document.getElementById(strbomItemId+'bomQty'+i).value;
											else
												document.getElementById('bomQty'+i).value="";
											
											if ((document.getElementById(strbomItemId+'bomMerchType'+i).value != null) && (document.getElementById(strbomItemId+'bomMerchType'+i).value.toUpperCase()!="NULL"))	
												document.getElementById('bomMerchType'+i).value=document.getElementById(strbomItemId+'bomMerchType'+i).value;
											else
												document.getElementById('bomMerchType'+i).value="";
											
											if ((document.getElementById(strbomItemId+'bomProdCode'+i).value != null) && (document.getElementById(strbomItemId+'bomProdCode'+i).value.toUpperCase()!="NULL"))	
												document.getElementById('bomProdCode'+i).value=document.getElementById(strbomItemId+'bomProdCode'+i).value;
											else
												document.getElementById('bomProdCode'+i).value="";
	
											if ((document.getElementById(strbomItemId+'bomItemType'+i).value != null) && (document.getElementById(strbomItemId+'bomItemType'+i).value.toUpperCase()!="NULL"))	
												document.getElementById('bomItemType'+i).value=document.getElementById(strbomItemId+'bomItemType'+i).value;
											else
												document.getElementById('bomItemType'+i).value="";
	
											if ((document.getElementById(strbomItemId+'bomCost'+i).value != null) && (document.getElementById(strbomItemId+'bomCost'+i).value.toUpperCase()!="NULL"))	
												document.getElementById('bomCost'+i).value=document.getElementById(strbomItemId+'bomCost'+i).value;
											else
												document.getElementById('bomCost'+i).value="";
										}
										else
										{	
											document.getElementById('selectCompId'+i).value=""								
											document.getElementById('ComponentId'+i).value="";
											document.getElementById('bomCompDesc'+i).value="";	
											document.getElementById('bomManugacturer'+i).value="";
											document.getElementById('bomQty'+i).value="";
											document.getElementById('bomMerchType'+i).value="";
											document.getElementById('bomProdCode'+i).value="";
											document.getElementById('bomItemType'+i).value="";
											document.getElementById('bomCost'+i).value="";
										}
											
										}

			    			}
	  					    
	  				
	  					/*     function addComp() {
	  						
	  					    	var row = parseInt(getIdVal("countCompRow"));
	  							//alert("row " + row);
   		  						inrStr = '<input id=ComponentId  autocomplete=off type=text placeholder="Select Component" list="componentList" onchange="callComponentChange(ComponentId)" value="" style="width: 120px;">'
	  							inrStr = inrStr + '<datalist id="componentList">'
	  							//alert("row 1" );
	  							inrStr = inrStr + '<option value="-1"></option>'
	  							inrStr = inrStr + '</datalist>'
	  							//alert("row 2" );
	  							$('#BOMaddtionalTable').dataTable().fnAddData( [
	  								'<input type="checkbox" class="select_item" name="select_item">',
	  								inrStr,
	  								'<input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomCompDesc'+ row +'" name="bomCompDesc'+ row + '" VALUE="">',
	  								'<input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomManugacturer'+row +'" name="bomManugacturer' +row + '" VALUE="">',
	  								'<input style="width: 30px;" class="inTxt" readonly type="text" id="bomQty'+row + '" name="bomQty' +row + '" VALUE="">',
	  								'<input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomMerchType' +row +'" name="bomMerchType' +row + '"  VALUE="">',
	  								'<input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomProdCode' +row + '" name="bomProdCode' +row + '" VALUE="">',
	  								'<input style="width: 120px;" class="rdl inTxt" readonly type="text" id="bomItemType' +row + '" name="bomItemType' +row + '" VALUE="">',
	  								'<input style="width: 70px;" class="rdl inTxt" readonly type="text" id="bomCost' +row + '" name="bomCost' +row + '"  VALUE="">'
	  						       ] );
	  							//alert("row 5" );
	  						    counter++;  
	  						    //alert("row 6" );
	  						    row = row +1  ;
	  						    //alert("newrow" + row)
	  						    setIdVal("countCompRow",row)

	  					 	} 
	  					     */
	  					    
	  					    
	  					    function submitBOM()
	  					    {
	  					    	 var approvalstr = document.getElementById('approval_Satus').value;
	  					    	 if ((approvalstr == 'ENTERED') || (approvalstr == 'ITEM MASTER REVIEW')) 
	  					    	 {
			  					    	 $('input, select').prop('disabled', false);
			  					        
			  					         var html='<div id="dialog-confirm" title="Submit For Approval">'+
			  					        '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Do you want to save the BOM Assignment?</p></div>';
			  					        $( html ).dialog({
			  					              resizable: false,
			  					              height:150,
			  					              modal: true,
			  					              buttons: {
			  					                "No": function() {
			  					                  $( this ).dialog( "close" );
			  					                },
			  					                "Yes": function() {
			  					                	$( this ).dialog( "close" );
			  					                    blockUsrInterface(); 
		
			  					                    var x ;
			  					                    $("#action").val('SAVEBOM');
			  					                	$.ajax({    
			  					      						type: "POST",
			  					      						async:false,						
			  					      						url: 'CanonE008ItemWorkbenchProcess.jsp', 
			  					      						data: "action="+"SAVEBOM" +"&reqName="+getIdVal("requester_name")+getFormData() ,
			  					      						complete: function(data)
			  					      							{  
			  					      								unBlockUsrInterface();
			  					      		                        if(error_message(data)){
			  					      		                            show_error("Failed to submit the project.",data);
			  					      		                        }else{
		
			  					      		                        	x =((data.responseText).replace(/^\s+|\s+$/g,''));
			  					      									//alert("resp" + x);
			  					      									var res =x.split("@@@");
			  					      									if (res[1]=='S')
			  					      									{
			  					      										//alert ("Project " + res[0])
			  					      										var strproNo = res[0]; 
			  					      										newrefreshPage(strproNo);	
			  					      									}      		                        	
			  					      		                        }
			  					      		                      },
			  					      		                      error:function(request){
			  					      		                            unBlockUsrInterface();
			  					      		                            var response=request.responseText;
			  					      		                            //show_error("Failed to submit the project.",response);
			  					      		                      }
			  					      					}); 
			  					                	
			  		 			                }
			  					              }
			  					        }); 
	  					    	 }	
	  					    }   	  					    

	  					   	function submitBOMClose()
	  					    	{
	  						 		document.getElementById("myBOMModal").style.display = "block";
	  					    	}
	  					     
		  					function submitBOMSave()
		  					    {
		  					    	$('input, select').prop('disabled', false); 
		  					    	var x ;
		  					    	$("#action").val('SAVEBOM');
					                	$.ajax({    
					      						type: "POST",
					      						async:false,						
					      						url: 'CanonE008ItemWorkbenchProcess.jsp', 
					      						data: "action="+"SAVEBOM" +"&reqName="+getIdVal("requester_name")+getFormData() ,
					      						complete: function(data)
					      							{  
					      								//unBlockUsrInterface();
					      		                        if(error_message(data)){
					      		                            show_error("Failed to submit the project.",data);
					      		                        }else{

					      		                        	x =((data.responseText).replace(/^\s+|\s+$/g,''));
					      									//alert("resp" + x);
					      									var res =x.split("@@@");
					      									if (res[1]=='S')
					      									{
					      										//alert ("Project " + res[0])
					      										var strproNo = res[0]; 
					      										newrefreshPage(strproNo);	
					      									}      		                        	
					      		                        }
					      		                      },
					      		                      error:function(request){
					      		                            //unBlockUsrInterface();
					      		                            var response=request.responseText;
					      		                            //show_error("Failed to submit the project.",response);
					      		                      }
					      					});
		  					    	
					                document.getElementById("myBOMModal").style.display = "block";
					                	
		  					    }   	  					    
	  					     
			      	    </script>		
						
						<div class="row EditForm" style="display: none;">				
							<div class="col-sm-12 col-md-12 col-lg-12">
							</div>							
						</div>
					
 					</div>
				</div>      
		      
		      </div>
		      <div class="modal-footer">
		      		<input type="hidden" name="addButtonPopupId" id="addButtonPopupId" value=""/>
		      		<button type="button" class="btnPrj" data-dismiss="modal" aria-label="Close" onclick="submitBOMSave()"><span aria-hidden="true">Save</span></button>
		      		<button type="button" class="btnPrj" data-dismiss="modal" aria-label="Close" onclick="submitBOMClose()"><span aria-hidden="true">Close</span></button>
<!-- 					<a class="btnPrj" id="addComp" tabindex="0" aria-controls="" onclick="addComp()"><span>Add Component</span></a>
					<a class="btnPrj" id="deleteComp" tabindex="0" aria-controls=""><span>Delele Component</span></a>
 -->		      </div>
 		    </div> 
		  </div> 
		  </div>
	</div>
	
	<div style="display: none;" id="bomNameLOV-DataDiv"></div>		
	<!-- <div id="dynamicLOV-DataDiv"></div>		 -->							 
	</form>

</div>	
<%
	}
%>
