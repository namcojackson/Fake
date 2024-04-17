
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="canon.apps.common.CanonCustomProfile"%>

<%
//	final int _pc = pageContext.REQUEST_SCOPE;
//	pageContext.setAttribute("_pageTitle", "Search", _pc);

	String pageTitle="Search";
	
	ArrayList<String> menuList = new ArrayList<String>();
//	 menuList.add("navSearch:menuToggleSearch:Search:N");
	 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
%>

<%@ include file="canonE307ServReqHeader.jsp" %> 

<div id ="main_content">
   		<div id="page_top">
			<h1>Advanced Service Call Center</h1>
		</div>
		<div style="margin:10px 13px;"><h1>Waste Toner Container Order Entry</h1></div>				
<div class="table-inner">
<%
	CanonE307WasteTonerContainerDao srchDao =  new CanonE307WasteTonerContainerDao();
	String strLocation = util.checkNull(request.getParameter("location"));
	String strSerialNumber = util.checkNull(request.getParameter("srlTagNmbr"));
	String strCustName = util.checkNull(request.getParameter("custName"));
	strLocation = strLocation == null?"":strLocation.trim();
	strSerialNumber = strSerialNumber==null?"":strSerialNumber;
	strCustName = strCustName==null?"":strCustName.trim();
	String customerName = (strCustName != null && strCustName.length()>0) ? strCustName.toUpperCase()+"%":null;
	String locationAddress = (strLocation != null && strLocation.length()>0 )? "%"+strLocation.toUpperCase()+"%":null;
	String strAction = util.checkNull(request.getParameter("Action"));
	String rspMsg="Please enter Search Criteria.";
	String retVal="Y";
	int pageNumber = (request.getParameter("pageNumber")==null)?1:Integer.parseInt(request.getParameter("pageNumber"));

	int count=10; 
	int start = ((pageNumber-1)*count) + 1 ;
	int end= pageNumber*count;
	String contextPath =request.getContextPath();
	int totalLks=0;
	String fnGetRes="fnGetMachSrchRes";
	String pageLinkMsg="";
	ArrayList<CanonE307WasteTonerRec> lstSearchRes = new ArrayList<CanonE307WasteTonerRec>();
	
	if("search".equals(strAction)){
		Object[] obj = srchDao.getMachineSearchResults(strSerialNumber, customerName, locationAddress, start, end);
		
		int totalRecords = 0;
		if(obj[0]!=null){
			totalRecords = ((Integer)obj[0]).intValue();
		}

		if(obj[1]!=null){
			lstSearchRes = (ArrayList<CanonE307WasteTonerRec>) obj[1];
		}
		totalLks = (totalRecords%count>0)?((totalRecords/count)+1):totalRecords/count;		
		
		   
	    if(totalLks>1){
	    	  pageLinkMsg= start+" to "+(end-(count-lstSearchRes.size())) +" of "+ totalRecords +" records found.";
	    }
	    rspMsg = "No data found for this criteria.";
			
	}
	if("submit".equals(strAction)){
		System.out.println("Before Submit..");
		String[] res = srchDao.submitOrders(request);
		System.out.println("After Submit..");
		if(res[1]!=null && res[1].length()>0){
			rspMsg = res[1];
			retVal = res[0];
		}
	}


    System.out.println("pageLinkMsg : " + pageLinkMsg);
    String strErrStyle="align:center;";
%>
	<style>
		.required{
			background-color: #FFFF00;
		}
	</style> 
	<form name="wstCntnrFrm" id="wstCntnrFrm" action="canonE307WasteContainerSearch.jsp" method="post">			
			<div class="toner-information">
				<table width="60%" cellspacing="5" id="machSrchTbl">
					<tr>
						<th colspan="4">Machine/Customer Search</th>
					</tr>
					<tr>
						<td width="30%"><input type="text" id="srlTagNmbr" name="srlTagNmbr" placeholder="Serial/Tag#" style='width: 250px;' value="<%=util.checkNull(request.getParameter("srlTagNmbr"))%>">
					<!-- 	<td width="10%" border=0 align="left"> -->
							<img src="<%=imgSrc1%>"  height='18' onClick="fnGetSrlNum();"/>
						</td>
						<input type="hidden" name="srlNumber" id="srlNumber" value="">
						<td width="30%"><input type="text" id="custName" name="custName" placeholder="Customer Name" style='width: 350px;' value="<%=util.checkNull(request.getParameter("custName"))%>" onchange="javascript:fnDsblEnblLctn()"/>
					<!-- 	<td width="10%" border=0 align="left"> -->
							<img src="<%=imgSrc1%>"  height='18' onClick="fnGetCustNm();"/>
						</td>
						<input type="hidden" id='acctNum' name='acctNum' value=''>
					</tr>
					<tr>
						<td colspan=4 align="left"><input type="text" id="location" name="location" placeholder="Location" style='width: 500px;' value="<%=strLocation%>" /></td>
						
					</tr>
					<tr>
						<td style="text-align: right;" colspan=4>
							<a href="javascript:fnWstTnrSearch()" class="btn">Search</a>
							<a href="javascript:fnClearAll()" class="btn">Clear</a>
						</td>
						
					</tr>					
				</table>
			</div>
			
	
	<div id="errorWidget"  style="display: none;color:red;padding-top: 130px;font-size: 15">
 		<p id="eMsg"></p>
	</div>
	<%	
	if(lstSearchRes.size()>10){
	    	strErrStyle="height: 400px;  overflow-y:auto;align:center;";
	    }
	%>
		<div class="table-inner">
			<table width="100%" id="pgLinks" cellspacing="0">
				<tr>
					  <td width="50%" id="pagination">
				           <jsp:include page="canonE307ServiceReqPgIncl.jsp">
						   <jsp:param value="<%=totalLks %>" name="totalLinks"/>
						   <jsp:param value="<%=pageNumber %>" name="pageNum"/>
						   <jsp:param value="<%=fnGetRes %>" name="fn"/>
						   </jsp:include>
					  </td>
					  <%
					  if(pageLinkMsg!="" && pageLinkMsg!=null){
					  %>
						 <td width="50%" align="right" id="showing"><b>Showing</b>&nbsp;<%=pageLinkMsg %></td>  
					  <%
					  }
					  %>
			   </tr>
		  </table>
		<table class="supplies-table" cellspacing="1">
			<tr >
				<th style="width:1%;">Select ALL<br/><input type="checkbox" name="selectAllCB" id="selectAllCB" value="selectAll" onclick="javascript:selectAllRows()"/></th>
				<th style="width:7%;">Serial#</th>
				<th style="width:8%;">Model</th>
				<th style="width:8%;">Customer</th>
				<th style="width:11%;">Location</th>
				<th style="width:8%;">Contact Name</th>
				<th style="width:5%;">Contact Phone</th>	
				<th style="width:5%;">Phone Ext</th>				
				<th style="width:5%;">Email Address</th>
				<th style="width:2%;">Shelf Stock</th>
				<th style="width:2%;">Quantity</th>
			</tr>
			<%
			if(lstSearchRes!=null && lstSearchRes.size()>0){
				
				for(int i=0;i<lstSearchRes.size();i++){
					CanonE307WasteTonerRec resultObj = (CanonE307WasteTonerRec)lstSearchRes.get(i);
			%>
			<tr>
				<td align="center" style="width:1%;"><input type="checkbox" name="wtCheckBox" value="<%=i%>" id="wtCheckBox<%=i%>" class="chkGrp" value="Y" onclick="javascript:selectRow('<%=i%>')"></td>
				<td style="width:7%;" nowrap><%=util.checkNull(resultObj.getSerialNumber())%>
					<input type="hidden" name="serialNumber<%=i%>" id="serialNumber<%=i%>" value="<%=util.checkNull(resultObj.getSerialNumber())%>"></td>
				<td style="width:8%;"><%=util.checkNull(resultObj.getModel()) %>
					<input type="hidden" name="model<%=i%>" id="model<%=i%>" value="<%=util.checkNull(resultObj.getModel())%>">
				<td style="width:8%;"><%=util.checkNull(resultObj.getCustomer()) %>
					<input type="hidden" name="customer<%=i%>" id="customer<%=i%>" value="<%=util.checkNull(resultObj.getCustomer())%>">		
				</td>					
				<td style="width:11%;"><%=util.checkNull(resultObj.getLocation()) %>
					<input type="hidden" name="location<%=i%>" id="location<%=i%>" value="<%=util.checkNull(resultObj.getLocation()) %>">	
				</td>
				<td style="width:8%;">
					<span id="contactNmStatic<%=i%>" style="width:10px;"><%=util.checkNull(resultObj.getContactName()) %> 
	      				<input type="hidden" name="contactNmHidden<%=i%>" id="contactNmHidden<%=i%>" value="<%=util.checkNull(resultObj.getContactName())%>" >
	      		    </span>
	      			<span id="contactNmEdit<%=i%>"  style="display: none;">
	      				<input type="text" maxlength="100" name="contactNm<%=i%>" id="contactNm<%=i%>" value="<%=util.checkNull(resultObj.getContactName()) %>"  class="required" size="15" style="width: 100px;background-color: #FFFF00;"/>
	      			</span>							
					
				</td>
				<td style="width:5%;">
					<span id="contactPhnStatic<%=i%>"><%=util.checkNull(resultObj.getContactPhone()) %>
	      				<input type="hidden" name="contactPhnHidden<%=i%>" id="contactPhnHidden<%=i%>" value="<%=util.checkNull(resultObj.getContactPhone())%>" >
	      		    </span>
	      			<span id="contactPhnEdit<%=i%>"  style="display: none;">
	      				<input type="text" maxlength="30" name="contactPhn<%=i%>" id="contactPhn<%=i%>" value="<%=util.checkNull(resultObj.getContactPhone()) %>"  class="required" size="15" style="width: 90px;background-color: #FFFF00;"/>
	      			</span>		
				</td>
				<td style="width:3%;">
					<span id="phnExtnStatic<%=i%>"><%=util.checkNull(resultObj.getContactPhone()) %>
	      				<input type="hidden" name="phnExtnHidden<%=i%>" id="phnExtnHidden<%=i%>" value="<%=util.checkNull(resultObj.getContactPhnExt())%>" >
	      		    </span>
	      			<span id="phnExtnEdit<%=i%>"  style="display: none;">
	      				<input type="text" maxlength="30" name="phnExtn<%=i%>" id="phnExtn<%=i%>" value="<%=util.checkNull(resultObj.getContactPhnExt()) %>"  size="15" style="width: 40px;"/>
	      			</span>		
				</td>				
				<td style="width:5%;">
 				<span id="emailStatic<%=i%>"><%=util.checkNull(resultObj.getEmailAddress()) %>
      				<input type="hidden" name="emailHidden<%=i%>" id="emailHidden<%=i%>" value="<%=util.checkNull(resultObj.getEmailAddress())%>" >
      		    </span>
      			<span id="emailEdit<%=i%>"  style="display: none;">
      				<input type="text" maxlength="150" name="email<%=i%>" id="email<%=i%>" value="<%=util.checkNull(resultObj.getEmailAddress()) %>" onchange="validateEmailaddress('<%=i%>')"  class="required" size="15" style="width: 100px;background-color: #FFFF00;"/>
      			</span>				
				
				</td>
				<td style="width:2%;">
					<input type="hidden" name="selshelfStock<%=i%>" id="selshelfStock<%=i%>" value="<%=util.checkNull(resultObj.getShelfStock())%>">
					<%	
						if("Yes".equalsIgnoreCase(resultObj.getShelfStock()))
				      	  {
				      	%>
				      	<select name="shelfStock<%=i%>" id="shelfStock<%=i%>" style="width: 70px;">
					      	<option value="">SELECT</option>
					      	<option value="Yes">Yes</option>
					      	<option value="No">No</option>
				      	</select>
				      	<%
				      	 }
				      	%>
				</td>
				<td style="width:2%;">
					<select name="qty<%=i%>" id="qty<%=i%>" style="width: 50px;">
						      	<option value="0">0</option>
						      	<option value="1">1</option>
						      	<option value="2">2</option>
						      	<option value="3">3</option>
						      	<option value="4">4</option>
						      	<option value="5">5</option>
						      	<option value="6">6</option>
	      			</select>
				</td>
			</tr>	
			<%		
				}
			}else{
			%>
				<tr class='eventableDataCell'><td colspan=11  align="center"><%=rspMsg %></td></tr>
			<%
			}
			%>
		</table>
		
  <br/>
  <br/>
  <%
  	if(lstSearchRes!=null && lstSearchRes.size()>0){
  %>
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
	        <tr>
	          <td width="30%">
	            &nbsp;
	          </td>
	          <td nowrap align="center" width="40%">
	          <a href="javascript:fnCreateOrder()" class="btn">Create Order</a>
	          </td> 
	          <td width="30%">
	            &nbsp;
	          </td>              
	        </tr>  
	      </table>	
	      <br/>
	  	  <br/>	
  	  <%} %>
  </div>
				
	</form>
</div>
</div>

<script>
$(function (){
	fnDsblEnblLctn();
	
/*	$('#selectAllCB').click(function(){
        if($(this).is(':checked')) {
            $(".chkGrp").prop("checked", true);
        } else {
            $(".chkGrp").prop("checked", false);
        }               
    });	*/
});	
$(document).ready(function() {
	 $("#a"+<%=pageNumber%>).css({"color":"white","background-color":"#A10304"});
	});
function selectAllRows()
{
	var allCheckBox = document.getElementById("selectAllCB");
    var checkBoxes = document.getElementsByName("wtCheckBox");
    for (i =0; i < checkBoxes.length; i++) {
  	  var value = checkBoxes[i].value;				  
        if (allCheckBox.checked == false) {
        	
        	 document.getElementById("wtCheckBox"+value).checked = false;
            
            document.getElementById('qty'+value).value = '0';
			  
      	  document.getElementById("contactNmStatic"+value).style.display = "block";
		  document.getElementById("contactNmEdit"+value).style.display  = "none";
			
		  document.getElementById("emailStatic"+value).style.display = "block";
		  document.getElementById("emailEdit"+value).style.display  = "none";
		  
		  document.getElementById("contactPhnStatic"+value).style.display = "block";
		  document.getElementById("contactPhnEdit"+value).style.display  = "none";	
		  
		  
		  document.getElementById("phnExtnStatic"+value).style.display = "block";
		  document.getElementById("phnExtnEdit"+value).style.display  = "none";	

        } else {
        	 document.getElementById("wtCheckBox"+value).checked = true;
      	  
      	  	document.getElementById('qty'+value).value = '1';
			  
  		  document.getElementById("contactNmStatic"+value).style.display = "none";
		  document.getElementById("contactNmEdit"+value).style.display  = "block";
	
		  document.getElementById("emailStatic"+value).style.display = "none";
		  document.getElementById("emailEdit"+value).style.display  = "block";
		  
		  document.getElementById("contactPhnStatic"+value).style.display = "none";
		  document.getElementById("contactPhnEdit"+value).style.display  = "block";
		  
		  document.getElementById("phnExtnStatic"+value).style.display = "none";
		  document.getElementById("phnExtnEdit"+value).style.display  = "block";	
		
        }
    }
}
function selectRow(rid)
{
	  var allCheckBox = document.getElementById("selectAllCB");
	  allCheckBox.checked = false;
	  
	 // var value = document.getElementById("wtCheckBox"+rid).value;
	  
	  if (document.getElementById("wtCheckBox"+rid).checked == false) {
        document.getElementById("wtCheckBox"+rid).checked = false;
        
        document.getElementById('qty'+rid).value = '0';
		  
		  document.getElementById("contactNmStatic"+rid).style.display = "block";
		  document.getElementById("contactNmEdit"+rid).style.display  = "none";
			
		  document.getElementById("emailStatic"+rid).style.display = "block";
		  document.getElementById("emailEdit"+rid).style.display  = "none";
		  
		  document.getElementById("contactPhnStatic"+rid).style.display = "block";
		  document.getElementById("contactPhnEdit"+rid).style.display  = "none";	
		  
		  
		  document.getElementById("phnExtnStatic"+rid).style.display = "block";
		  document.getElementById("phnExtnEdit"+rid).style.display  = "none";	
		  
		
		//  document.getElementById("contactInfo"+rid).value = document.getElementById("contactInfoHidden"+rid).value;
		//  document.getElementById("email"+rid).value = document.getElementById("emailHidden"+rid).value;

    } else {
  	  document.getElementById("wtCheckBox"+rid).checked = true;
  	  
  	  document.getElementById('qty'+rid).value = '1';
  	  
		  
		  document.getElementById("contactNmStatic"+rid).style.display = "none";
		  document.getElementById("contactNmEdit"+rid).style.display  = "block";
	
		  document.getElementById("emailStatic"+rid).style.display = "none";
		  document.getElementById("emailEdit"+rid).style.display  = "block";
		  
		  document.getElementById("contactPhnStatic"+rid).style.display = "none";
		  document.getElementById("contactPhnEdit"+rid).style.display  = "block";
		  
		  document.getElementById("phnExtnStatic"+rid).style.display = "none";
		  document.getElementById("phnExtnEdit"+rid).style.display  = "block";	
	
    }
}
function fnWstTnrSearch(){
	$('#eMsg').html('');
 	$("#errorWidget").hide();
	$("#machSrchTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
	var srlTagNmbr = $.trim($('#srlTagNmbr').val());
	var customerName = $.trim($('#custName').val());
	var location = $.trim($('#location').val());
	  if(srlTagNmbr.length == 0 && customerName.length == 0)
	  {
			$('#eMsg').html('Please enter search criteria.');
			$("#errorWidget").show();
		  return;
	  }
	  
	  if(customerName.length >0)
	  {
		  if(location.length == 0)
		  {
			$('#eMsg').html('Please enter location address.');
			$("#errorWidget").show();			  
			 return;
		  }
	  }
		showPleaseWait();
		var urlDetail = "canonE307WasteContainerSearch.jsp?Action=search";
		document.wstCntnrFrm.action = urlDetail;
		document.wstCntnrFrm.submit();		
}
function fnGetMachSrchRes(pageNum){
	$("#machSrchTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});

	showPleaseWait();
	var urlDetail = "canonE307WasteContainerSearch.jsp?Action=search&pageNumber="+pageNum;
	document.wstCntnrFrm.action = urlDetail;
	document.wstCntnrFrm.submit();	

}
function fnGetSrlNum(){
	$("#machSrchTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
    var modelName ="#srlDiv";
    $(modelName).html("");	
    var srlTagNmbr = $.trim($("#srlTagNmbr").val());
	   $(modelName).dialog({
					height: 680,
					title: "Serial / Tag Number Search", 
					modal: true ,
					zIndex:1005,
					width: 750,		
					resizable: false    
				});
	   showPleaseWait();
	   var qryStr="srlTagNmbr="+encodeURIComponent(srlTagNmbr);
	       $.ajax({
			url: "canonE307ServReqSerialLov.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
     				   hidePleaseWait();
		 	        }             
		 });
		  
	     $( modelName ).dialog("open");
	   //  $(".ui-dialog").css({"top":"990px"}); 
	     $(".ui-dialog-titlebar").addClass("hd");
 }
 function fnGetCustNm(){
		$("#machSrchTbl input").each(function() {
		    var input = $(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		});
	    var modelName ="#srlDiv";
	    $(modelName).html("");	
	    var custName = $.trim($("#custName").val());
		   $(modelName).dialog({
						height: 680,
						title: "Customer Name Search", 
						modal: true ,
						zIndex:1005,
						width: 750,		
						resizable: false    
					});
		   showPleaseWait();
		   var qryStr="customerName="+encodeURIComponent(custName);
		       $.ajax({
				url: "canonE307ServReqCustomerLov.jsp",
				data:qryStr,
				type:"POST",
		        cache: false,
				success: function(data){     
						  hidePleaseWait();     
						  $(modelName).html("");					
					      $(modelName).html(data);
	     				   hidePleaseWait();
			 	        }             
			 });
			  
		     $( modelName ).dialog("open");
		   //  $(".ui-dialog").css({"top":"990px"}); 
		     $(".ui-dialog-titlebar").addClass("hd");
 }
 function fnSelAssign(serialNumber){
	 var modelName ="#srlDiv";
	 $('#srlTagNmbr').val(serialNumber);
	 $('#srlNumber').val(serialNumber);

	 $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");
 }
 function fnSelCustmr(custName, acctNum){
	 var modelName ="#srlDiv";
	 $('#acctNum').val(acctNum);
	 $('#custName').val(custName);
	 
	 fnDsblEnblLctn();
	 
	 $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");
 }
 function fnSearchSerNum() {
	 
		var modelName = "#srlDiv";
		var srlTagNmbr = $.trim($("#srlTagNumber").val());

		srlTagNmbr = encodeURIComponent(srlTagNmbr);
	    showPleaseWait();
		var qryStr="srlTagNmbr="+srlTagNmbr;
		 $.ajax({
				url:"canonE307ServReqSerialLov.jsp",
				data:qryStr,
				type:"POST",
		        cache: false,
				success: function(data){     
						  hidePleaseWait();     
						  $(modelName).html("");					
					      $(modelName).html(data);
					       hidePleaseWait();
			 	        }             
		});
	}
 function fnSearchCustomer() {
		var modelName = "#srlDiv";
		var custName = $.trim($('#customerName').val());

		custName = encodeURIComponent(custName);
	    showPleaseWait();
		var qryStr="customerName="+custName;
		 $.ajax({
				url:"canonE307ServReqCustomerLov.jsp",
				data:qryStr,
				type:"POST",
		        cache: false,
				success: function(data){     
						  hidePleaseWait();     
						  $(modelName).html("");					
					      $(modelName).html(data);
					       hidePleaseWait();
			 	        }             
		});
	} 
 function fnClose(divName){
	 var modelName = "#"+divName;
	 $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");
 }
 function fnDsblEnblLctn(){
	var custName = $('#custName').val();
	if(custName!=null && custName!='null' && custName!=''){
		$('#location').removeClass("rdl").removeAttr("readonly");
		$('#location').removeClass("rdl").removeAttr("disabled");
	}else{
		 $('#location').addClass("rdl").attr("readonly","readonly");
		 $('#location').addClass("rdl").attr("disabled","true");
	}

 }

 function fnCreateOrder()
 {
		$('#eMsg').html('');
	 	$("#errorWidget").hide();
	  var checkBoxes = document.getElementsByName("wtCheckBox");
	  var selectedFlag = 0;
	  for (i =0; i < checkBoxes.length; i++) {
   	  var snId = checkBoxes[i].value;

         if(document.getElementById("wtCheckBox"+i).checked)
         {
       	  var model = $.trim($('#model'+i).val());
       	  var customer = $.trim($('#customer'+i).val());
       	  var location = $.trim($('#location'+i).val());
       	  var contactInfo = $.trim($('#contactNm'+i).val());
       	  var email = $.trim($('#email'+i).val());
       	  var qty = $.trim($('#qty'+i).val());
       	  var contactPhn =  $.trim($('#contactPhn'+i).val());
       	  
       	  
     	  var shelfStock='';

			 var e = document.getElementById('shelfStock'+i);
			 if(e != null)
			 {
			 	 shelfStock = e.options[e.selectedIndex].value;
			 	
			 	 if($.trim(shelfStock) == '')
         		 {
         			$('#eMsg').html('Please select an option for Shelf Stock.');
   				 	$("#errorWidget").show();
         			 return;
         		 }
			 }
     		 if(contactPhn == '' || contactPhn == 'null' )
     		 {
       				$('#eMsg').html('Contact Phone should be entered for all selected Serial Numbers.');
				 	$("#errorWidget").show();
     			 return;
     		 }
			 
     		 if(contactInfo == '' || contactInfo == 'null' )
     		 {
       				$('#eMsg').html('Contact Name should be entered for all selected Serial Numbers.');
				 	$("#errorWidget").show();
     			 return;
     		 }
     		 if(email == '' || email == 'null')
     		 {
   				$('#eMsg').html('Email address should be entered for all selected Serial Numbers.');
			 	$("#errorWidget").show();     			 
     			 return;
     		 }else{
     			var retVal = validateEmailaddress(i);
     			if(!retVal){
     				return;
     			}
     		 }
     		 
     		 if(qty == '0')
     		 {
    				$('#eMsg').html('Order Quantity should be greater than 0 for all selected Serial Numbers.');
    			 	$("#errorWidget").show();  
      			 return;
     		 }
     		 
     		selectedFlag++;
         }
     }
	    
	  if(selectedFlag > 0)
	  {
		var r = confirm("The selected rows will be submitted for supply order creation. \nClick OK to proceed or Cancel to make any modifications.");
	    if (r == true) {
	    	var urlDetail = "canonE307WasteContainerSearch.jsp?Action=submit";
			document.wstCntnrFrm.action = urlDetail;
	    	document.forms['wstCntnrFrm'].submit();
	    	showPleaseWait();
	    } else {
	        return;
	    }
	  } else{
			$('#eMsg').html('No rows are selected');
		 	$("#errorWidget").show();
		  return;
	  }
 }
 function validateEmailaddress(iVal)
 {
	$('#eMsg').html('');
 	$("#errorWidget").hide();
   var emailaddressValue = "";
   emailaddressValue    = document.getElementById("email"+iVal).value;
	var v_len = emailaddressValue.length;

	if(v_len >0 && (emailaddressValue.toUpperCase()=='NA' || emailaddressValue.toUpperCase()=='N/A' || emailaddressValue.toUpperCase()=='PACARNES@FF.FED.US'||emailaddressValue.toUpperCase().indexOf("REFUSE")==0)){
		return true;
	}else{
		if(v_len>0){
			if (v_len < 5)
			{
				$('#eMsg').html('Please enter atleast 5 characters in Email Address.');
			 	$("#errorWidget").show();
			  //document.getElementById("email"+rid).className = "required_field";
			  return false;
			}
			else 
			{
				var parts = emailaddressValue.split("@");
				var firstPart = parts[0];
				var alpha=false;
				if(firstPart.length>0){
					alpha = true;
				}else{
					$('#eMsg').html('Email address should contain at least one character before @');
				 	$("#errorWidget").show();
					return false;
				}
				if(parts.length>1){
					var nextPart1 = parts[1];
					var nextPart2 = nextPart1.split(".");
					if(nextPart2.length>1){
						var backpart = nextPart2[0];
						var backpart1 = nextPart2[1];
						var al=false;
						if(backpart.length>0){
							alpha=true;
						}else{
							$('#eMsg').html('Email address should contain at least one character after @');
						 	$("#errorWidget").show();							
							return false;
						}
						
						if(backpart1.length>0){
							alpha=true;
						}else{
							$('#eMsg').html('Email address should contain at least one character after .');
						 	$("#errorWidget").show();							
							return false;
						}
						
					}else{
						$('#eMsg').html('Email address should contain at least one character before .');
					 	$("#errorWidget").show();						
						return false;
					}
				}else{
					$('#eMsg').html('Email address should contain at least one character after @.');
				 	$("#errorWidget").show();					
					return false;
				}
				return true;
			}
			return true;
		}
	}
 }

</script>
	<div id="srlDiv"></div>	
	</body>
</html>
