
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307ServiceReqCustomerRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao"%>
<%@page import="java.util.ArrayList"%>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<div style="min-height: 400px;">
<table style="width: 100%;">
<tr>
<td>
<%
        CanonE307ServiceReqCreateDao dao =  new CanonE307ServiceReqCreateDao();
        CanonCommonUtil util = new CanonCommonUtil();
        String custName =util.checkNull(request.getParameter("custName"));
        String cId =util.checkNull(request.getParameter("cId"));
        String accNum=util.checkNull(request.getParameter("accNum"));
        
        String custId=cId+"CustName";
        String acctId=cId+"AcctNum";

        
        
		int pageNum =1; 		
		
		String strPageNum =request.getParameter("pageNum");
		
		if(strPageNum!=null){
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
		
		String sortBy =util.checkNull(request.getParameter("sortBy"));
		if(sortBy.length()<1)
			sortBy="LOC_NM";
		String sortOrder=util.checkNull(request.getParameter("sortOrder")); //ASC or DESC
		if(sortOrder.length()<1)
			sortOrder="DESC";
		
		if(custName.length()>0){
			 try{
		  		 custName=custName.replaceAll("&amp;","&");
			 }catch(Exception e){
				 System.out.println("Customer name special char error : "+e.getMessage());
			 }
		}
		
		Object[] obj = dao.getCustomer(custName,accNum, start, end, sortBy, sortOrder);
		
		int totalRecords = ((Integer )obj[0]).intValue();
		
 %>

<input type="hidden" name="cId" id="cId" value="<%=cId%>"/>
<input type="hidden" name="sortBy" id="sortBy" value="<%=sortBy%>"/>
<input type="hidden" name="sortOrder" id="sortOrder" value="<%=sortOrder%>"/>  

<table style="font-size: 12px;width: 100%;">
              <tr>
	                <td>Customer Name</td>
	                <td>
		                <input type="text" class="inTxt" placeholder="Customer Name" name="sCustName" size="20" id="sCustName" style="height: 19px;" value="<%=custName%>" tabindex="1"  />
	                </td>
	                <td>Account Number</td>
	                <td>
		                <input type="text" placeholder="Account Number" class="inTxt"  name="sAccNum" size="10" style="height: 19px;" id="sAccNum" value="<%=accNum %>"  />
	                </td>
	                
	                <td>
	                 <a id="aSrchCust" onclick="searchCustomer('1');" class="btn" href="#" style="float: right;">Search</a>
	                </td>
	                <td>
	                   <a id="aSrchCustClr" onclick="$('#sCustName').val('');$('#sAccNum').val('');" style="margin-left:5px;" class="btn" href="#" style="float: right;">Clear</a>
	                </td>
	                
	           </tr>     
	           
	         
 </table>
   <br/>
 <%           		
		
		if(totalRecords>0){ 
		
			 ArrayList<CanonE307ServiceReqCustomerRec> arList = (ArrayList<CanonE307ServiceReqCustomerRec>) obj[1];
			
			totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
			String fnGetModel="searchCustomer";
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
    	   
 %>
          <table   style="width: 100%; text-align: center;" >
           <tr><td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
          </table>
          <table class="lovTbl" align="center" style="text-align: center;width: 100%;">
           <thead>
             <tr class='canontableCell' >
              <th class="hd"> </th> <th class="hd"  onclick="sortCustSrch('LOC_NM');"  style="text-decoration: underline;">Customer Name</th>
              <th class="hd"  onclick="sortCustSrch('SELL_TO_CUST_CD');"  style="text-decoration: underline;">Account Number</th></thead>
           <tbody> 
            <%
	         for(CanonE307ServiceReqCustomerRec custBean : arList){
	 		%>
	   	      <tr>
	   	          <td width="5%">
	   	            <input type="radio" class="chk" name="rCust" />
	   	          </td>
	   	          <td width="45%"><%=util.checkNull( custBean.getCustName()) %></td>
	   	           <td width="40%"><%=util.checkNull(custBean.getAcctNum())%></td>
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
		</table>		   
		  <% 
		}else{
	     %>
	       <p style="font-size: 11px;font-weight: bold;">No records found for this search criteria.</p>
	     <%		
		  }
          %>
      </div>    
      <table   style="width: 100%;" >
         <tr><td style="float: right;margin-right: 10px;"> <a href="#"  class="btn" onclick="closeDlg();">Select</a></td></tr>
      </table>

<script type="text/javascript">

$(function (){

	$(".chk").click (function (){

		   var tr=$(this).closest("tr");
		   var custName= $(tr.children()[1]).html();
		   var acctNum= $(tr.children()[2]).html();
		   $("#<%=cId%>CustName").val(custName); 
		   $("#<%=cId%>sCustName").html(custName); 
		   $("#<%=cId%>AcctNum").val(acctNum);
	});

});
</script>
