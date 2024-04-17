
<%@page import="com.canon.apps.servreq.beans.CanonE307SerialNumLov"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307WasteTonerContainerDao"%>
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
		CanonE307WasteTonerContainerDao dao =  new CanonE307WasteTonerContainerDao();
        CanonCommonUtil util = new CanonCommonUtil();
        String strSrlTagNmbr =util.checkNull(request.getParameter("srlTagNmbr"));
        ArrayList<CanonE307SerialNumLov> arList = new ArrayList<CanonE307SerialNumLov>();
        
		System.out.println("strSrlTagNmbr "+strSrlTagNmbr);
		if(strSrlTagNmbr!=null && strSrlTagNmbr.length()>0){
			arList = dao.getSerialNumbers(strSrlTagNmbr);
		}

 %>
 <div class="service">
	<table style="width: 100%;border:0px;">
	     <tr>
	        <td width="5%" nowrap>Serial/Tag number</td>
	        <td width="35%">
	        	<input type="text" name="srlTagNumber" id="srlTagNumber" value="<%=strSrlTagNmbr%>" tabindex="1"  />
	        </td>
        	<td width="3%" style="align:left">
	            <a href="javascript:fnSearchSerNum()" class="btn" style="white-space: nowrap;">Search</a>
	        </td>	
	        <td width="3%" style="align:left">
	            <a href="javascript:fnClose('srlDiv')" class="btn" style="white-space: nowrap;">Close</a>
	        </td>	   
	     </tr>
	 </table>
   <br/>
   </div>

 <div class="service">
       <table  class="lov-table" style="width: 98%;"  cellpadding="1" cellspacing="1">
           <thead>
             <tr>
              <th> Select </th> 
              <th>Serial Number</th>
              <th>Tag Number</th>
              <th>Customer Name</th>
              <th>Address</th>
             </tr>
           </thead>
           <tbody> 
            <%
            if(arList!=null && arList.size()>0){
	         for(CanonE307SerialNumLov srlBean : arList){
	 		%>
	   	      <tr >
	   	          <td width="5%" align="center">
	   	            <input type="radio" class="chk" name="rAssign" id="rAssign" style="border:0px;" onClick="fnSelAssign('<%=util.checkNull(srlBean.getStrSerialNumber() )%>')" />
	   	          </td>
	   	           <td width="11%"><%=util.checkNull(srlBean.getStrSerialNumber() )%></td>
	   	           <td width="11%"><%=util.checkNull(srlBean.getStrEquipNumber())%></td>
	   	           <td width="35%"><%=util.checkNull(srlBean.getStrCustName())%></td>
	   	           <td width="35%"><%=util.checkNull(srlBean.getStrAddress())%></td>
	   	      </tr>
	   		<%
	         }
            }else{
            	if(("".equals(strSrlTagNmbr)) || ("null".equals(strSrlTagNmbr))){
            		%>
					<tr><td colspan=5>Please enter Search Criteria.</td></tr>
            		<%
            	}else{
            		%>
            		<tr><td colspan=5>No Serial Number found for the search criteria.  </td></tr>
            		<%
            	}
            }
	        %>
            </tbody>
          </table>	   
 </div>
   