

<%@page import="com.canon.apps.servreq.dao.CanonE307ServMsgDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307SRMsgsBean"%>
<%
	CanonE307ServMsgDao objMessageDAO = new CanonE307ServMsgDao();
	int pageNum =1; 
	String strAction = request.getParameter("Action");
	System.out.println("strAction : " + strAction);
	int servId = -1;
	int retVal = -1;
	String res[];
	if("searchMessage".equals(strAction) || ("updateMessage".equals(strAction))){
		System.out.println("in Search Message");
		String strfieldName = request.getParameter("level")==null?"":request.getParameter("level");
		String strfieldValue = request.getParameter("value")==null?"":request.getParameter("value");
		//System.out.println("in Search Message strfieldName"+strfieldName);
		//System.out.println("in Search Message strfieldValue"+strfieldValue);
		ArrayList alMessage = new ArrayList();
		String reqCol = "Y";
	
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
			System.out.println("pageNum::"+pageNum);
			System.out.println("start::"+start+":end:"+end);
			alMessage = objMessageDAO.searchServMsgs(strfieldName,strfieldValue, start,end);
			int totalLinks =0;        		
			System.out.println("alMessage.size()::"+alMessage.size());
			if(alMessage.size()>1){ 
				int totalRecords=Integer.parseInt(""+alMessage.get(0));
				System.out.println("totalRecords::"+totalRecords);
				alMessage.remove(0); // removing total Records param from list
				System.out.println("alMessage.size after remove::"+alMessage.size());
				totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
				System.out.println("TotalLinks::"+totalLinks);
				String fnGetMessage="searchMessageLink";			
				String pageLinkMsg="";
	    	   
	    	    if(totalLinks>1){
	    	    	  pageLinkMsg= start+" to "+(end-(ct-alMessage.size())) +" of "+ totalRecords +" records found.";
	    	    		System.out.println("pageLinkMsg::"+pageLinkMsg);
	    	    }
%>
		<div id="historyTblDiv" >	
			 <table width="100%" style="font-size: 11px;" id="mdlLinks">
			      <tr>
<%-- 					  <td align="left">
					         <jsp:include page="canonE307MsgPaginationIncl.jsp">
							   <jsp:param value="<%=totalLinks %>" name="totalLinks"/>
							   <jsp:param value="<%=pageNum %>" name="pageNum"/>
							   <jsp:param value="<%=fnGetMessage %>" name="fn"/>
							   </jsp:include>
					  </td> --%>
					<td align="right" style="text-align: right;font-size: 13px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
            </table>
         </div>
     <!--    <div class="message-information">  -->
    <!--   	<div class="service"> -->
     <!--   	<div id="scrolltbl" style="overflow: auto; overflow-y: hidden; width:99%;"> -->
      		<div  style="width:99.8%; overflow-x:auto; overflow-y: hidden;"> 
	        <table  cellpadding="1" cellspacing="1" width="130%" align="center" id="itemupdate" class="model-table" style="display: block;white-space: nowrap;margin:2px 5px 2px 5px;">
	        <thead>
			     <tr><th align="center"> SELECT</th>
					<th align="center" colspan="3">Service Message</th>
					<th align="center">Start Date</th>
					<th align="center">End Date</th>
					<th align="center">Start Time</th>
					<th align="center">End Time</th>
					<th align="center">24 Hour</th>
					<th align="center">Region</th>
					<th align="center">Branch</th>
					<th align="center">Team</th>
					<th align="center">Postal</th>
					<th align="center">Party Name</th>
					<th align="center">Party Number</th>
					<th align="center">Party Site Number</th>
					<th align="center">Account Number</th>
					<th align="center">Model</th>
					<th align="center">Serial Number</th>			
					<th align="center">Last Updated By</th>
			      </tr>
</thead>
<tbody>

			     <%
			     if(alMessage!=null && alMessage.size()>0){
			    	 System.out.println("alMessage: "+ alMessage.size());
			     for(int i=0;i<alMessage.size();i++){
			    	 CanonE307SRMsgsBean searchBeanObj = (CanonE307SRMsgsBean) alMessage.get(i);
			    	 String strFullHour = searchBeanObj.getIsFullHour();
			    	 if(strFullHour==null)
		               {
		               strFullHour=" ";
	                 } 
			    	  String bclr="";
			    	  
						if((i%2) == 0)
						{
							bclr   = "background-color: #FFFFFF";
						}
						else
						{
							bclr   = "background-color: #ebebeb";
						}	
			     %>
			     	<tr style="<%=bclr%>">
 				      <td align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;"><input type="checkbox"  name="selectedItem<%=i%>" value="<%=i%>" id="selectedItem<%=i%>" style="padding: 0px;border:0px;background-color:#F7F7E7;" onclick="fnEnableConfigDet('<%=i%>')"/></td>
			   	      <td id="msgTD"  align="center" colspan="3" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text" class="itemDescC" id="msg<%=i%>" name="msg<%=i%>" value="<%=searchBeanObj.getServMsg() ==null?"":searchBeanObj.getServMsg()%>" readonly="true" style="background-color:#F7F7E7;width:500px; margin: 0px 0px !important;border: 0px solid #aac;">
					  </td> 
					  <input type="hidden" name="startDateFlag<%=i%>" id="startDateFlag<%=i%>" value="<%=searchBeanObj.getStartDate() %>">
					  <td nowrap id="startDateTD<%=i%>" align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
					  <input type="text" name="startDate<%=i%>" id="startDate<%=i%>" class="datePicker" disabled readonly="true" value="<%=searchBeanObj.getStartDate()==null?"":searchBeanObj.getStartDate()%>" size=9 onchange="compareBaseUnitStartdate('<%=i%>',this)" style="background-color:#F7F7E7;margin: 0px 0px !important;border: 0px solid #aac;">
					  </td>
					  <td id="endDateTD<%=i%>" align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">		
					  <input type="text" id="endDate<%=i%>" name="endDate<%= i%>" class="datePicker" disabled readonly="true" value="<%=searchBeanObj.getEndDate()==null?"":searchBeanObj.getEndDate() %>" size=9 onchange="compareBaseUnitEnddate('<%=i%>',this)" style="background-color:#F7F7E7;margin: 0px 0px !important;border: 0px solid #aac;"></td>
					  <input type="hidden" id="endDt<%=i%>" name="endDt<%=i%>" value="<%=searchBeanObj.getEndDate()%>">
					  <td id="starttimeTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text" name="startTime<%=i%>" id="startTime<%= i%>" value="<%=searchBeanObj.getStartTime()==null?"":searchBeanObj.getStartTime()%>" readonly="true" onchange="validateStartTime(this)" style="background-color:#F7F7E7;margin: 0px 0px !important;border: 0px solid #aac; width:55px;">
			          <input type="hidden" id="strTime<%=i%>" name="strTime<%=i%>" value="">	
			          </td>
						<td id="endtimeTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
						<input type="text"  id="endTime<%=i%>"  name="endTime<%= i%>" value="<%=searchBeanObj.getEndTime()==null?"":searchBeanObj.getEndTime()%>" readonly="true"  onchange="validateEndTime(this)" style="background-color:#F7F7E7;margin: 0px 0px !important;border: 0px solid #aac; width:55px;">
						<input type="hidden" id="edTime<%=i%>" name="edTime<%=i%>" value="">
						 </td>
					  <td id="fullhourTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
					  <input type="checkbox" id="fullHour<%=i%>" name="fullHour<%=i%>" class="chk"  value="<%=strFullHour%>" <%=strFullHour.equalsIgnoreCase("true")?"checked":" "%> disabled  style="width:30px;border:0px;"/>
					  </td>      
					  <td id="regionTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getRegion()==null?"":searchBeanObj.getRegion()%>" readonly="true" style="background-color:#F7F7E7; width:150px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			          <td id="branchTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getBranch()==null?" ":searchBeanObj.getBranch()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			          <td id="teamTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getSvcTeam()==null?" ":searchBeanObj.getSvcTeam()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>			          
			          <td id="postalTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getPostal()==null?"":searchBeanObj.getPostal()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			            <td id="partynameTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getPartyName()==null?"":searchBeanObj.getPartyName()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			            <td id="partynumTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getPartyNumber()==null?"":searchBeanObj.getPartyNumber()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			            <td id="partysitenumTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getPartySiteNumber()==null?"":searchBeanObj.getPartySiteNumber()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			            <td id="acctnumTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getAccountNumber()==null?"":searchBeanObj.getAccountNumber()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
					  <td id="modelTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getModel() ==null?"":searchBeanObj.getModel()%>" readonly="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			          <td id="serialnumTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getSerialNumber()==null?"":searchBeanObj.getSerialNumber()%>" readonly ="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>					  
			          <td id="lastUpdatedByTD<%=i%>" nowrap align="center" style="padding: 0px;background-color:#F7F7E7;height:30px;">
			          <input type="text"  value="<%=searchBeanObj.getLastUpdatedBy()==null?"":searchBeanObj.getLastUpdatedBy()%>" readonly ="true" style="background-color:#F7F7E7; width:100px;margin: 0px 0px !important;border: 0px solid #aac;">
			          </td>
			   	       <input type="hidden" id="servId<%=i%>" name="servId<%=i%>" value="<%=searchBeanObj.getServMsgId()%>">	 	      
			  </tr>
			      <%
			     	}
			     }
			      %>
       <input type="hidden" id="msgSize" name="msgSize" value="<%=alMessage.size()%>">
  <!--      <tr><td colspan=19>&nbsp;</td></tr> -->

  </tbody>
            </table>  
            </div>  
    		 <table width="100%" style="font-size: 11px;" id="mdlLinks">
			      <tr>
					  <td align="left">
					         <jsp:include page="canonE307MsgPaginationIncl.jsp">
							   <jsp:param value="<%=totalLinks %>" name="totalLinks"/>
							   <jsp:param value="<%=pageNum %>" name="pageNum"/>
							   <jsp:param value="<%=fnGetMessage %>" name="fn"/>
							   </jsp:include>
					  </td>
					  <td align="right" style="text-align: right;font-size: 13px;font-weight: bold;"><%=pageLinkMsg %></td>
				   </tr>
			 	  </table>
 <br>
	<table width="98%">
		<tr align="center">
			<td>
				<a href="javascript:updateMessage()" class="btn" style="font-size: 15px !important;">Save</a> 
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
	
	
	<%	
	}
	%>