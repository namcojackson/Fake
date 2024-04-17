
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307ServiceReqAssigneeRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="java.util.ArrayList"%>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<table style="width: 100%;">
<tr>
<td>
<%
		CanonE307ServiceRequestDetailsDao dao =  new CanonE307ServiceRequestDetailsDao();
        CanonCommonUtil util = new CanonCommonUtil();
        String strAssignName =util.checkNull(request.getParameter("assignName"));
        String strBranch = util.checkNull(request.getParameter("branchNm"));
		String strIval = util.checkNull(request.getParameter("iVal"));
  //      String custId=cId+"CustName";
   //     String acctId=cId+"AcctNum";
        
		int pageNum =1; 		
		
		String strPageNum =request.getParameter("pageNum");
		
		if(strPageNum!=null && strPageNum!="null" && strPageNum!=""){
			 try{	
			   pageNum = Integer.parseInt(strPageNum);
			 }catch(Exception e){
			   System.out.println("Page Num. parse error : "+e.getMessage());
			   pageNum =1;
			 }
		}	
		
		int ct=10; // number of Records per page 
		int start = ((pageNum-1)*ct) + 1 ;
		int end= pageNum*ct;
		int totalLinks =0;
		
		String sortBy =""; 
		String sortOrder=""; 
		System.out.println("Before method call"+start+"end "+end+" strAssignName: "+strAssignName);
		Object[] obj = dao.getAssigneeDet(strAssignName, start, end, sortBy, sortOrder,"ASSIGNEE", strBranch);
		int totalRecords = ((Integer )obj[0]).intValue();
 %>
 <div class="service">
	<table style="width: 100%;border:0px;">
	     <tr>
	        <td width="5%" nowrap>Technician Name</td>
	        <td width="35%">
	         <input type="text" name="assignName" id="assignName" value="<%=strAssignName%>" tabindex="1"  />
	        </td>
	        <td width="5%" nowrap>Branch</td>
	        <td width="35%">
	         <input type="text" name="branchNm" id="branchNm" value="<%=strBranch%>" tabindex="1"  />
	        </td>	        
	        <td width="33%" style="align:left">
	            <a href="javascript:searchAssignee('1','<%=strIval%>')" class="btn" style="white-space: nowrap;">Search</a>
	        </td>
	     </tr>
	 </table>
   <br/>
   </div>
 <%           		
		if(totalRecords>0){ 
		
			 ArrayList<CanonE307ServiceReqAssigneeRec> arList = (ArrayList<CanonE307ServiceReqAssigneeRec>) obj[1];
			
			totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
			String fnGetSearch="searchAssignee";
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
    	   
 %>
          <table   style="width: 100%;" >
           <tr><td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
          </table>
          <table class="supplies-table" style="width: 98%;"  cellpadding="1" cellspacing="1" style="background-color:#cccc99">
           <thead>
             <tr >
              <th> Select </th><th>Technician Name</th><th>Employee Id</th><th>Branch</th>
           </thead>
           <tbody> 
            <%
	         for(CanonE307ServiceReqAssigneeRec assBean : arList){
	 		%>
	   	      <tr >
	   	          <td width="5%" align="center">
	   	            <input type="radio" class="chk" name="rAssign" id="rAssign" style="border:0px;" onClick="fnSelAssign('<%=util.checkNull(assBean.getStrAssignee()).replaceAll("'", "\\\\'")%>','<%=util.checkNull(assBean.getStrAssigneeCode())%>','<%=strIval%>')" />
	   	          </td>
	   	           <td width="30%"><%=util.checkNull(assBean.getStrAssignee())%></td>
	   	           <td width="20%"><%=util.checkNull(assBean.getStrAssigneeCode())%></td>
	   	           <td width="30%"><%=util.checkNull(assBean.getStrBranch())%></td>
	   	      </tr>
	   		<%
	         }
	        %>
            </tbody>
          </table>	   
        </td>
        </tr>
		   <tr>
		     <td>
				 <table  style="font-size: 11px;width:100%;" id="pgLinks">
			      <tr>
					  <td align="left" nowrap>
							<div id="pagination">
							<%
						//	String sTotalLinks =request.getParameter("totalLinks");
						//	String sPageNum =request.getParameter("pageNum");
						//	String fn = request.getParameter("fn");
						//	int totalLinks = (sTotalLinks!=null)?Integer.parseInt(sTotalLinks):0; 
						//	int pageNum = (sPageNum!=null)?Integer.parseInt(sPageNum):1; 
							
							int nop=totalLinks;
							 
							 if(nop==0){ // no rows
								 
							 }else if(0<nop  && nop<=1){
								 
								 
							 }else if(nop>1){
							
								 if(nop>10 ){
								  
									 if(pageNum>2){
										 
											%>
											 <a href="javascript:<%=fnGetSearch%>('1','<%=strIval%>');">First</a>
											<% 
										 }
									 if(pageNum>1){
										%>
										 <a href="javascript:<%=fnGetSearch%>('<%=(pageNum-1) %>','<%=strIval%>');"> Prev</a>
										<% 
									 }
									 int temp=pageNum;
									 if(temp>5)
									  pageNum-=5;
									
									 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
									  %>
									   <a  id="a<%=k %>" href="javascript:<%=fnGetSearch%>('<%=k %>','<%=strIval%>');"><%=k %></a>
							 		  <% 
								     }
									 if(temp>5)
										  pageNum+=5;
									 
									 if( (pageNum+1) <= nop){
										 %>
										  <a href="javascript:<%=fnGetSearch%>('<%=(pageNum+1)%>','<%=strIval%>');">Next</a>
										 <%
									 }
									 %>
									  <a href="javascript:<%=fnGetSearch%>('<%= nop %>','<%=strIval%>');">Last</a>
									 <%
								 }
								 
								 if(nop<=10){
									 for(int k=1;k<=nop;k++){
										%>
										 <a id="a<%=k %>" href="javascript:<%=fnGetSearch%>('<%= k %>','<%=strIval%>');"><%= k %></a>
										<% 
										 
									 }
								 }
							 }else{
								 
							 }
							 
							 %>
							</div>
					  </td>
				   </tr>
			 	  </table>
		     </td>
		   </tr>
		   <tr class="trEmpty"><td>&nbsp;</td></tr>
		   <tr align="right">
		   <td>
			   <table style="width: 98%;align:right;">
				  <tr align="right">
				 	  <td><a href="javascript:fnCloseSearchDlg('techDiv')" class="btn" style="white-space: nowrap;">Close</a></td>
				 </tr>
			   </table>
		   </td>
		   </tr>		   
		</table>		   
		  <% 
		}else{
	     %>
	       <p style="font-size: 11px;font-weight: bold;">No records found for this search criteria.</p>
	     <%		
		  }
          %>

