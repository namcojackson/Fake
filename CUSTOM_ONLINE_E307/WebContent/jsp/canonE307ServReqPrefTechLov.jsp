
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307ServiceReqAssigneeRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="java.util.ArrayList"%>

<table style="width: 100%;">
<tr>
<td>
<%
		CanonE307ServiceRequestDetailsDao dao =  new CanonE307ServiceRequestDetailsDao();
        CanonCommonUtil util = new CanonCommonUtil();
        String strTechName =util.checkNull(request.getParameter("prefTechNm"));
        String strBrnchNm =util.checkNull(request.getParameter("brnchNm"));
        

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
		//System.out.println("Before method call"+start+"end "+end+" strTechName: "+strTechName);
		Object[] obj = dao.getAssigneeDet(strTechName, start, end, sortBy, sortOrder,"ASSIGNEE", strBrnchNm);
		int totalRecords = ((Integer )obj[0]).intValue();
 %>
 <div class="service" style="border:0px;">
	<table style="width: 100%;border:0px;">
	     <tr>
	        <td nowrap>Technician Name </td>
	        <td width="33%">
	         <input type="text" name="prefTechNm" id="prefTechNm" value="<%=strTechName%>" tabindex="1"  />
	        </td>
	        <td >Branch </td>
	        <td width="33%">
	         <input type="text" name="brnchNm" id="brnchNm" value="<%=strBrnchNm%>" tabindex="1"  />
	        </td>
	        <td style="width: 33%">	 </td>       
	        <td>
		        <a href="javascript:searchPrefTech('1')" class="btn" style="white-space: nowrap;">Search</a>
	        </td>
	     </tr>
	 </table>
</div>
   <br/>
 <%           		
		if(totalRecords>0){ 
		
			 ArrayList<CanonE307ServiceReqAssigneeRec> arList = (ArrayList<CanonE307ServiceReqAssigneeRec>) obj[1];
			
			totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
			String fnGetSearch="searchPrefTech";
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
    	   
 %>
          <table   style="width: 100%;" style="width: 100%;align:right;" cellpadding="1" cellspacing="1" style='background-color:#cccc99'>
           <tr><td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
          </table>
          <table class="supplies-table" style="width: 100%;"  cellpadding="1" cellspacing="1" style="background-color:#cccc99">
           <thead>
             <tr>
              <th> Select </th> <th>Technician Name</th><th> Employee Id</th><th>Branch</th>
           </thead>
           <tbody> 
            <%
	         for(CanonE307ServiceReqAssigneeRec assBean : arList){
	 		%>
	   	      <tr>
	   	          <td width="5%">
	   	            <input type="radio" class="chk" name="rAssign" id="rAssign" onClick="fnSelTech('<%=util.checkNull(assBean.getStrAssignee()).replaceAll("'", "\\\\'")%>','<%=util.checkNull(assBean.getStrAssigneeCode())%>')" />
	   	          </td>
	   	           <td width="30%"><%=util.checkNull(assBean.getStrAssignee())%></td>
	   	           <td width="20%"><%=util.checkNull(assBean.getStrAssigneeCode()) %></td>
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
					  <td align="left">
					         <jsp:include page="canonE307ServiceReqPgIncl.jsp">
							   <jsp:param value="<%=totalLinks %>" name="totalLinks"/>
							   <jsp:param value="<%=pageNum %>" name="pageNum"/>
							   <jsp:param value="<%=fnGetSearch %>" name="fn"/>
							   </jsp:include>
					  </td>
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
