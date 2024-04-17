<%@page import="java.sql.Timestamp"%>
<%@page import="oracle.apps.jtf.base.session.ServletSessionManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.S21Authentication" %>
<%!    final static int ALL_ROWS = Integer.MAX_VALUE;
    String [] parameterValues(HttpServletRequest request,String name){
        String param_prefix=CanonE008ItemProcessUtil.nonNullify(request.getParameter("param_prefix"));
        return request.getParameterValues(param_prefix+name);
    }
    String nonNullify(Object o){
        if(o==null){
            return "";
        }else if(o instanceof String){
            return ((String)o).trim();
        }else {
            return o.toString();
        }
        
    }   
%>     
<%
    
    Object result[]= null;//CanonE008ItemProcessUtil.getLoginUserInfo(request, response);
    S21SecurityContext context = S21SecurityContextHolder.getContext();
	S21Authentication s21Authentication=context.getAuthentication();
    String loginUserID=s21Authentication.getIdentityDetails().getUID();  
    String loginUserFullName=CanonS21SessionValidate.getFullName();
    BigDecimal p_project_number = CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("project_number"),false);
    String p_project_description = request.getParameter("project_description");
    String p_project_name = request.getParameter("project_name");
    String p_project_notes = request.getParameter("project_notes");
    String p_project_processor_full_name=null;
    BigDecimal p_request_id=null;
    
    result=CanonE008ItemProcessDAO.getProjectItems(p_project_number);
    if(result!=null && result.length>0){
        CanonE008ItemProcessDAO.CanonE008ProjectRec p=(CanonE008ItemProcessDAO.CanonE008ProjectRec)CanonE008ItemProcessUtil.first(result);
        System.out.println("getProjectItems 1 "+p);
        System.out.println("getProjectItems 2 "+CanonE008ItemProcessUtil.second(result));
        p_project_name=p.getProjectName();
        p_project_description=p.getProjectDesc();
        p_project_processor_full_name=""; //p.getProjectSubmitterName();
        p_project_notes=p.getProjectNotes();
        p_request_id=new BigDecimal("0"); //p.getRequestId();
    }
    result = null;
    
%>    

<style>
	
/* 	span { text-align:center; display:block; }

    .top_bar {
    	font-size: 20px;
    }
		
        .search_text {
        font-size: 10px;
    }
    
    #report_tbl_first .hd {
        font-size: 10px;
    }
    
    input{
        font-size: 10px;
    }
    
    td {
        font-size: 10px;
    }

    tr {
        font-size: 17px;
    } */
 
fieldset {
    font-family: "Raleway",sans-serif;
    ##border: 5px solid #1F497D;
    border: 5px solid #003b4e 
    background: #ddd;
    border-radius: 5px;
    padding: 15px;
}

fieldset legend {
    background: #003b4e;
    color: #fff;
    padding: 5px 10px ;
    font-size: 13px;
    border-radius: 5px;
    box-shadow: 0 0 0 5px #ddd;
    margin-left: 20px;
    margin-right: 20px;
}
     
</style>

<script>
/*     $( document ).ready(function() {
        $('#close_window_button').click(function()
       {
           window.close();
       });
    });
 */    
    function close();
    {
    	window.close();
    }
</script>

<div id="main_content" style="text-align: center;">
	<div>	<h1>Project History <%=p_project_number == null ? "" : "- Project Number " + p_project_number.toString()%></h1>
</div>	
<form name="approvalForm" id="approvalForm" method="post">
    <input type="hidden" name="save_flag" id="save_flag"/>
    <input type="hidden" name="project_number" id="project_number" value=" <%=p_project_number==null? "":p_project_number.toString()%>"/>
    <input type="hidden" name="action"  id="action"/>
	<br>
        <%
            List approvalcurrentList=(List)CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getcurrentapprovals(p_project_number));
        %>    
 		<section style="margin: 10px;">
		<fieldset style="min-height:100px;"> 
		<legend><b> Current Approvals </b> </legend>
        <!-- <table border="0" width="100%" cellspacing="2" cellpadding="2" > -->
        <table class="apprval-table">
            <tr>
                <th>Approval Group</th>
                <th align="center">Approval Status</th>
                <th align="center">Approver</th>
                <th align="center">Date</th>
                <th align="center">Age(Days)</th>
                <th align="center">Comments</th>
            </tr>
            <%  System.out.println("approvalcurrentList.size() "+approvalcurrentList.size());          
                for(int i=0;i<approvalcurrentList.size();i++){
                    CanonE008ItemProcessDAO.ApprovalStatusInfo status=(CanonE008ItemProcessDAO.ApprovalStatusInfo)approvalcurrentList.get(i);
            %>   
            <tr class='<%= i%2==0? "even" : "odd" %>' >
                <%-- <td><input width="100px" class="rdl inTxt" type="text" id="approval_group" value="<%=status.getApprovingDepartment()%>"></td> --%>
                <td><%=status.getApprovingDepartment()%></td>
                <td align="center"><%=nonNullify(status.getApprovalStatus())%></td>
                <td align="center"><%=nonNullify(status.getApproverName())%></td>
                <td align="center"><%=CanonE008ItemProcessUtil.formatDate4(status.getLastUpdateDate())%></td>
                <td align="center"><%=nonNullify(status.getAge())%></td>
                <td align="center"><%=nonNullify(status.getApproverComments())%></td>
            </tr>
            <%
                }
            %>
       </table> 
 <br>
       </fieldset>
            <%  
               List approvalhistoryList=(List)CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.gethistapprovals(p_project_number));
                if(approvalhistoryList!=null && approvalhistoryList.size()>0){
            %>
<!--        <section style="margin: 10px;">
       <fieldset style="border-radius: 5px; padding: 5px; min-height:150px;"><legend><b>Historical Approvals</b></legend>     
 -->	
 		<section style="margin: 10px;">
		<fieldset style="min-height:100px;">
		<legend><b> Historical Approvals </b> </legend>
       
       <!-- <table border="0" width="100%" cellspacing="2" cellpadding="2"> -->
       <table class="apprval-table">
            <tr>
                <th>Approval Group</th>
                <th align="center">Approval Status</th>
                <th align="center">Approver</th>
                <th align="center">Date</th>
                <th align="center">Age(Days)</th>
                <th align="center">Comments</th>
            </tr>
            <%    System.out.println("approvalhistoryList.size() "+approvalhistoryList.size());             
                for(int i=0;i<approvalhistoryList.size();i++){
                    CanonE008ItemProcessDAO.ApprHistInfo status=(CanonE008ItemProcessDAO.ApprHistInfo)approvalhistoryList.get(i);
            %>
            <tr class='<%= i%2==0? "even" : "odd" %>'>
                <td><%=nonNullify(status.getApprovingDepartment())%></td>
                <td align="center"><%=nonNullify(status.getApprovalStatus())%></td>
                <td align="center"><%=nonNullify(status.getApproverName())%></td>
                <td align="center"><%=CanonE008ItemProcessUtil.formatDate4(status.getLastUpdateDate())%></td>
                <td align="center"></td>
                <td align="center"><%=nonNullify(status.getApproverComments())%></td>
            </tr>
            <%
                }
            }else{
            %>  
            <tr>
                <td colspan="5">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="5" >No Rejected History</td>
            </tr>
            <%
                }
            %>
            
        </table>
        </fieldset>  
            <br>
			<div class="service_btn">
			<table>  
            <tr>
                <td align="center">
                	<a class="btnPrj" href="javascript:close()">Close</a>  
                    <!-- <button class="ui-state-default ui-corner-all" type="button" id="close_window_button" title="Close Window">
                        <span class="ui-button-text">Close</span>
                    </button> -->
                </td>  						
            </tr>
        	</table>	
        	</div>
        </form>
