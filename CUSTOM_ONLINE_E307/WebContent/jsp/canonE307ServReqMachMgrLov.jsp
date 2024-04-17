
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
        String strMachManager =util.checkNull(request.getParameter("machManager"));
        
        
		int pageNum =1; 		
		
		String strPageNum =request.getParameter("pageNum");
		
		if(strPageNum!=null && strPageNum!="null"){
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
		
		Object[] obj = dao.getAssigneeDet(strMachManager, start, end, sortBy, sortOrder,"MANAGER");
		
		int totalRecords = ((Integer )obj[0]).intValue();
		
 %>

<table style="font-size: 13px;width: 100%;">
     <tr>
        <td class="lbl" width="5%" nowrap>Customer Name </td>
        <td width="20%">
         <input type="text" name="machManagerLov" id="machManagerLov" value="<%=strMachManager%>" tabindex="1"  />
        </td>
        <td width="40%">
           <a href="javascript:searchMachMgr('1')" class="btn" style="white-space: nowrap;">Search</a>
        </td>
     </tr>
 </table>
   <br/>
 <%           		
		
		if(totalRecords>0){ 
		
			 ArrayList<CanonE307ServiceReqAssigneeRec> arList = (ArrayList<CanonE307ServiceReqAssigneeRec>) obj[1];
			
			totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
			String fnGetModel="searchMachMgr";
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
 %>
          <table style="width: 100%;align:right;" cellpadding="1" cellspacing="1" style='background-color:#cccc99'>
           <tr><td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
          </table>
          <table class="lov-table" style="width: 100%;">
           <thead>
             <tr>
              <th>Select</th> <th>Manager Name</th></thead>
           <tbody> 
            <%
	         for(CanonE307ServiceReqAssigneeRec mgrBean : arList){
	 		%>
	   	      <tr>
	   	          <td width="5%" align="center">
	   	            <input type="radio" class="chk" name="rAssign" onClick="fnSelManager('<%=util.checkNull(mgrBean.getStrAssignee())%>','<%=util.checkNull(mgrBean.getStrAssigneeCode())%>')" />
	   	          </td>
	   	          <td width="45%"><%=util.checkNull(mgrBean.getStrAssignee()) %></td>
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
							   <jsp:param value="<%=fnGetModel %>" name="fn"/>
							   </jsp:include>
					  </td>
				   </tr>
			 	  </table>
		     </td>
		   </tr>
		   <tr class="trEmpty"><td>&nbsp;</td></tr>
		   <tr align="right">
		   <td>
			   <table style="width: 100%;align:right;">
				  <tr align="right">
				 	  <td><a href="javascript:fnCloseSearchDlg('servInfoDiv')" class="btn" style="white-space: nowrap;">Close</a></td>
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

