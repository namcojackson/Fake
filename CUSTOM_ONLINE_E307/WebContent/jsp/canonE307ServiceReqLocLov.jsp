
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307ServiceReqLocRec"%>
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
        CanonCommonUtil  util = new CanonCommonUtil();
        String addr =util.checkNull(request.getParameter("addr"));
        String locType =util.checkNull(request.getParameter("locType"));
        String custAccNum =util.checkNull(request.getParameter("accNum"));
       
        String sId =util.checkNull(request.getParameter("sId"));
        
        String locLbl = "Installed At Location";
         if(sId.equalsIgnoreCase("b"))
        	   locLbl = "Bill To Location";
        
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
		
		String system =null;
		
		String sortBy =util.checkNull(request.getParameter("sortBy"));
		if(sortBy.length()<1)
			sortBy="FIRST_LINE_ADDR";
		String sortOrder=util.checkNull(request.getParameter("sortOrder")); //ASC or DESC
		if(sortOrder.length()<1)
			sortOrder="DESC";
		
		Object[] obj = dao.getCustLocation(locType, custAccNum, addr, start, end, sortBy, sortOrder);
				
		
		int totalRecords = ((Integer )obj[0]).intValue();
		
 %>

<input type="hidden" name="sId" id="sId" value="<%=sId%>"/>  

<input type="hidden" name="sortBy" id="sortBy" value="<%=sortBy%>"/>
<input type="hidden" name="sortOrder" id="sortOrder" value="<%=sortOrder%>"/>

<table style="font-size: 11px;width: 100%;">
              <tr>
	                <td class="lbl" width="25%"><%=locLbl %></td>
	                <td width="45%">
		                <input type="text" name="sAddr" id="sAddr" value="<%=addr%>" tabindex="1" size="35"  />
		                <input type="hidden" name="sCustAccNum" id="sCustAccNum" value="<%=custAccNum%>"/>
		                <input type="hidden" name="sLocType" id="sLocType" value="<%=locType%>"/>
		            </td>
	                <td  width="30%">
	                   <a id="aSrchLoc" onclick="searchCustomerLoc('1');" class="btn" href="#">Search</a>
	                   <a id="aSrchLocClr" onclick="$('#sAddr').val('');" style="margin-left:5px;" class="btn" href="#">Clear</a>
	                </td>
              </tr>
 </table>
 <br/>
 <%           		
		
		if(totalRecords>0){ 
		
			 ArrayList<CanonE307ServiceReqLocRec> arList = (ArrayList<CanonE307ServiceReqLocRec>) obj[1];
			
			totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
			String fnGetModel="searchCustomerLoc";
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
    	   
 %>
          <table   style="width: 100%;" >
           <tr><td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
          </table>
          <table class="lovTbl" style="text-align: center;width: 100%;">
           <thead>
             <tr >
              <th class="hd"> </th> 
              <th class="hd" onclick="sortLocSrch('FIRST_LINE_ADDR');"  style="text-decoration: underline;">Location</th>
              <th class="hd" onclick="sortLocSrch('CTY_ADDR');"  style="text-decoration: underline;">City</th>
              <th class="hd" onclick="sortLocSrch('ST_CD');"  style="text-decoration: underline;">State</th>
              <th class="hd" onclick="sortLocSrch('POST_CD');"  style="text-decoration: underline;">Postal Code</th>
              <th class="hd" onclick="sortLocSrch('CTRY_CD');"  style="text-decoration: underline;">Country</th>
            </thead>
           <tbody> 
            <%
	         for(CanonE307ServiceReqLocRec locBean : arList){
	 		%>
	   	      <tr>
	   	          <td width="5%">
	   	            <input type="radio" class="chk" name="rCust" />
	   	            <input type="hidden" name="custCode" id="custCode" value="<%=util.checkNull(locBean.getCustCode())%>"/> 
	   	            <input type="hidden" name="custPk" id="custPk" value="<%=util.checkNull(locBean.getCustPk())%>"/>
	   	          </td>
	   	          <td nowrap="nowrap"><%=util.checkNull(locBean.getAddr()) %></td>
	   	          <td nowrap="nowrap"><%=util.checkNull(locBean.getCity())%></td>
	   	          <td><%=util.checkNull(locBean.getState()) %></td>
	   	          <td><%=util.checkNull(locBean.getPostalCode()) %></td>
	   	          <td><%=util.checkNull(locBean.getCountry()) %></td>
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
				 <table style="font-size: 11px;width: 100%;" id="pgLinks">
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
		   var address= $(tr.children()[1]).html();
		   var city= $(tr.children()[2]).html();
		   var state= $(tr.children()[3]).html();
		   var postalCode= $(tr.children()[4]).html();
		   var country= $(tr.children()[5]).html();
		   var custCode= $(tr.children()[0]).find("input#custCode").val();
		   var custPk  = $(tr.children()[0]).find("input#custPk").val();
		   $("#<%=sId%>Addr").val(address);
		   $("#<%=sId%>sAddr").html(address);
		   $("#<%=sId%>sCity").html(city);
		   $("#<%=sId%>sState").html(state);
		   $("#<%=sId%>sPostalCode").html(postalCode);
		   $("#<%=sId%>CustCode").val(custCode);
		   var extPkVal = $("#<%=sId%>CustPk").val();
		   
		   if(extPkVal!=custPk){
			   $("#sInstBase").show();			   
		   }else{
			   $("#sInstBase").hide();	
		   }

		   
	});

});
</script>
