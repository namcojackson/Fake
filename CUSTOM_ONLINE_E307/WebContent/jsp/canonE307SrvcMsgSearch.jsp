<%@page import="com.canon.apps.servreq.dao.CanonE307ServMsgDao"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="com.canon.apps.servreq.util.CanonE307FileUploadDownloadAPIUtil"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqCallAvdAPIUtil"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqAPIUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307BillToCustCreditAuthBean"%>
<%@page import="com.canon.apps.servreq.beans.*"%>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao"%>
<%@ page import="com.canon.apps.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%> 
<%@page import="canon.apps.common.CanonCustomProfile"%>
<%!
	public String escape(String s){
		if(s!=null) return s.replace("\"","\\\"");
		return "";
	}
%>
<%   
String pageTitle="Create";
String pageHdr="Advanced Service Call Center";
ArrayList<String> menuList = new ArrayList<String>(); 
 
%>  
<div id="searchMsg">
<%@include file="canonE307ServReqMsgHeader.jsp"%>

<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canonE307ServiceReqCreate.css" type="text/css"> 


<div id="main_content">  
<div id="page_top">
	<h1>ASCC Service Messages</h1>
</div>	
<%
	String strfieldName = request.getParameter("level")==null?"":request.getParameter("level");
	String strfieldValue = request.getParameter("value")==null?"":request.getParameter("value");
	CanonE307ServMsgDao objMsgDAO = new CanonE307ServMsgDao();
	java.util.Date currentDate = new Date();
	String strCurrentDate="";
	if (currentDate != null)
	{
		strCurrentDate =  sdf.format(currentDate);
	}	
	String strAction = request.getParameter("Action");
	System.out.println("strAction : " + strAction);
	int servId = -1;
	int retVal = -1;
	String res[]; 
	java.util.ArrayList msgList = new java.util.ArrayList();
	if("updateMessage".equals(strAction)){
		String recSize = request.getParameter("msgSize")==null?"":request.getParameter("msgSize");
		int msgSize = 0;
		if("".equals(recSize)){
			//Do Nothing
		}else{
			msgSize = Integer.parseInt(recSize);
		}		
		for(int i=0;i<msgSize;i++){
			String cBox = request.getParameter("selectedItem"+i);
			if(cBox!=null && cBox!="null"){
				
				CanonE307ServMsgsBean msgObj = new CanonE307ServMsgsBean();
				String strServId = request.getParameter("servId"+i)==null?"":request.getParameter("servId"+i);
				System.out.println("strServId::"+strServId);
				String strStartDate = request.getParameter("startDate"+i);
				String strEndDate = request.getParameter("endDate"+i);
				String strStartTime = request.getParameter("strTime"+i);
				String strEndTime = request.getParameter("edTime"+i);
				String strMsg = request.getParameter("msg"+i);
				String strFullHour = request.getParameter("fullHour"+i)==null?"false":request.getParameter("fullHour"+i);
				System.out.println("strStartDate:"+strStartDate+":strEndDate:"+strEndDate+":strStartTime:"+strStartTime+":strEndTime:"+strEndTime+":strMsg:"+strMsg+":strFullHour:"+strFullHour);
			   if("".equals(strServId)){
					//Do Nothing
				}else{
					servId = Integer.parseInt(strServId);
				}
				//System.out.println("servId::"+servId);
			   msgObj.setEndDate(strEndDate);
			   msgObj.setStartDate(strStartDate);
			   msgObj.setStartTime(strStartTime);
			   msgObj.setEndTime(strEndTime);
			   msgObj.setServMsg(strMsg);
			   msgObj.setIsFullHour(strFullHour);
			   msgObj.setServMsgId(servId);
		
			   msgList.add(msgObj);
			}
		}
		
		res = objMsgDAO.updateServMsgs(msgList, loginUser);
		System.out.println("Res::"+res[0]);
		if(res[0].indexOf("successfully")!=-1){
			retVal=1;
			
		}
	}	
%>	
<div style="margin:0px 5px;"><h1>Service Message Search</h1></div>

<form id="messageSearchForm" name=messageSearchForm method="post" action="<%=ctxPath%>/e307/jsp/canonE307SrvcMsgSearch.jsp">
	<div class="" style="width:100%">
	<table style="align:center;width:80%;" >
		<tr>
			<td style="width:75%">
				<div class="serviceMsg" style="width:100%">
					<table cellspacing="5" style="float:right;">
						<tr>
							<th colspan=2>Service Message Search</th>
						</tr>
						
						<tr align="center">
						<td>Level</td>
		                      <td align="left" class="non-required_field" width="70%">
	                         <SELECT NAME="level" id="level" style="border: solid 1px #336699;width:145px">
							<option <%="ALL".equals(request.getParameter("level"))?"selected":""%> value="ALL" >ALL</option>
							<option <%="REGION".equals(request.getParameter("level"))?"selected":""%> value="REGION" >Region</option>
							<option <%="BRANCH".equals(request.getParameter("level"))?"selected":""%> value="BRANCH" >Branch</option>
							<option <%="POSTAL".equals(request.getParameter("level"))?"selected":""%> value="POSTAL" >Postal</option>
							<%-- <option <%="PARTY_NUMBER".equals(request.getParameter("level"))?"selected":""%> value="PARTY_NUMBER" >Party Number</option> --%>
							<option <%="PARTY_SITE_NUMBER".equals(request.getParameter("level"))?"selected":""%> value="PARTY_SITE_NUMBER" >Party Site Number</option>
							<option <%="ACCOUNT_NUMBER".equals(request.getParameter("level"))?"selected":""%> value="ACCOUNT_NUMBER" >Account Number</option>
							<option <%="SERIAL_NUMBER".equals(request.getParameter("level"))?"selected":""%> value="SERIAL_NUMBER" >Serial Number</option>
							<option <%="MODEL".equals(request.getParameter("level"))?"selected":""%> value="MODEL" >Model</option>
							<option <%="SVCTEAM".equals(request.getParameter("level"))?"selected":""%> value="SVCTEAM" >Team</option>
					         </select>
	                        </td>
	                   <tr align="center">
							<td>Value</td>
		                    <td align="left" width="70%">
	            			 <input type="text" id="value" name="value" style="margin: 0 0px !important" value="<%=request.getParameter("value")==null?"":request.getParameter("value")%>">
	           				</td>
						</tr>
	
							<tr valign="top" align="right">
							<td colspan="2" style="text-align: right;">
								<a href="javascript:fnSearchMessage()" class="btn">Search</a> 
								<a href="javascript:fnClear()" class="btn">Clear</a>
								<a href="javascript:createNewConfig()" class="btn">Create Message</a>
							</td>
						</tr>
					</table>
				</div>
			</td>
			<td></td>
		</tr>
	</table>
	</div>
	<%
	   if(retVal>0){
		%>
		<table width="98%" align="center">
			<tr align="left" style="color:green;font-size:15px">
				<td id="updateMsg">The record has been updated successfully.</td>
			</tr>
		</table>	
		<%	
		}
		%>
	<table width="98%" align="center">
	  <tr align="left" style="color:red;font-size:15px">
	  <td>
	    <div id="feedbackDiv">	
		</div>
		</td>
		</tr>
	</table>
	<div id='resultsDiv'>
		<jsp:include page="canonE307SrvcMsgSearchIncl.jsp">
		    <jsp:param value="<%=strfieldName %>" name="level"/>
		    <jsp:param value="<%=strfieldValue %>" name="solutionName"/>
			<jsp:param value="<%=strAction %>" name="Action"/>    
	    </jsp:include>	 
	
	</div>		
</form>
		<table>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
		</table>
  <div id="toolTip1">
    <table>
      <tr><td> <textarea  id="ta" rows="10" cols="60" onKeyDown="itmDescLimit(this);" onKeyUp="itmDescLimit(this);" ></textarea> </td></tr>
      <tr><td><p style="font-size:11px;" ><span id="taCnt">2000</span>  characters left.</p> </td></tr>
      <tr><td> 
        <input type="hidden" id="eleId" value=""/>
       <input type="button" onclick="hideTT1('set');" value="Set"/>
       <input type="button" onclick="hideTT1('close');" value="Close"/>
      </td></tr>
    </table>
  </div>
  </div>
<script type="text/javascript">
function fnRdOnly(){
	$('input[type="text"]').each(function (){
		 $(this).addClass("rdl").attr("readonly","readonly");
	});
}

function addtoolTip1(e){
	$(e).click(function() { 
	             showTT1($(this),$(this).val());
            }
      );
}

function showTT1(element, text) {
	
	$('#toolTip1 #eleId').val(element.attr("id"));
	
    var offset = element.offset();
    $('#toolTip1 #ta').val(text);
    
    $('#toolTip1')
    .css({
        'top'  : (offset.top-105) + 'px',
        'left' : (offset.left +element.outerWidth()-400)+ 'px' 
        
    }).show();
   // $('#toolTip1 #ta').focus();
    $("#toolTip1 textarea").focus();
    $("#taCnt").html(2000 - text.length);
}

function hideTT1(s) {
	var eleId=$('#toolTip1 #eleId').val();
    if(s=="set"){ 
	  $("#"+eleId).val($("#toolTip1 #ta").val());
    }
    $('#toolTip1 #eleId').val("");
    $('#toolTip1 #ta').val("");
	$('#toolTip1').hide();
    
}
/*function showTT(element, text) {
	   var offset = element.offset();
	   $('#toolTip1').html("");    
	   var str= '<br><a href="#" onclick="hideScTT();" style="float:right;margin-top:2px;" class="btn">Close</a><a href="#" onclick="clearTT();" style="float:right;margin:2px 5px 0px 5px;" class="btn">Clear</a>';
	    
	    $('#toolTip1')
	    .css({
	        'top'  : (offset.top+25) + 'px',
	        'left' : (offset.left-245)+ 'px' 
	   }).html(text  + "  " +str).show();
	}

function hideScTT() {
	var eleId=$('#toolTip1 #eleId').val();
		$("#"+eleId).val($('#toolTip1 textarea').val());
		$('#toolTip1').hide();
} */

function itmDescLimit(ele) 	{
	 var lt=2000;
	if (ele.value.length > lt) {
		ele.value = ele.value.substring(0, lt);
	} else {
		$("#taCnt").html(lt - ele.value.length);
	}
}

$(function (){
	$(".itemDescC").each(function () {
	  addtoolTip1($(this));	
	});
});
function  validateStartTime(x)
{	
//alert("starttime");
	$('#feedbackDiv').html("");
	 $("#resultDiv").html("");
	  $('#updateMsg').html("");
      var table = document.getElementById('itemupdate'),rows = table.getElementsByTagName('tr'), cells;
      var rowcount=x.parentNode.parentNode.rowIndex;   
	  var startTime= table.rows[rowcount].cells[4].children[0].value;
		table.rows[rowcount].cells[4].children[0].className="";
		 var numbers = /^[0-9]+$/;
	//alert("startTime:"+startTime);		  
	  var filter = /^([01]?[0-9]|2[0-3]):?[0-5][0-9]?$/;
	  	if(startTime.length!=4)
		{
			table.rows[rowcount].cells[4].children[0].value="";
			table.rows[rowcount].cells[4].children[0].className="error";
			$('#feedbackDiv').html("Please enter 4 digit in Start Time");
			return false;
		}
	  	else  if(!numbers.test(startTime))
	  		{
	  		table.rows[rowcount].cells[4].children[0].value="";
			table.rows[rowcount].cells[4].children[0].className="error";
			$('#feedbackDiv').html("Please enter only numbers in Start Time");
	  		return false;
	  		}

	else
	{		 
  		
	  if (filter.test(startTime)) {		 
	        return true;
	    }
	    else {
	    	 
	    	table.rows[rowcount].cells[4].children[0].value="";
	    	table.rows[rowcount].cells[4].children[0].className="error";
	    	  $('#feedbackDiv').html("Please enter correct start time format from 0000 to 2359 in Start Time");
	        return false;
	    }
	
  }
}
function  validateEndTime(x)
{	
	$('#feedbackDiv').html("");
	 $("#resultDiv").html("");
	  $('#updateMsg').html("");
      var table = document.getElementById('itemupdate'),rows = table.getElementsByTagName('tr'), cells;
      var rowcount=x.parentNode.parentNode.rowIndex;   
	  var endTime= table.rows[rowcount].cells[5].children[0].value; 
	  table.rows[rowcount].cells[5].children[0].className="";
	//  alert("startTime:"+startTime);	
	 var numbers = /^[0-9]+$/;
     var filter = /^([01]?[0-9]|2[0-3]):?[0-5][0-9]?$/;		
	if(endTime.length!=4)
		{
			table.rows[rowcount].cells[5].children[0].value="";
			table.rows[rowcount].cells[5].children[0].className="error";
			$('#feedbackDiv').html("Please enter 4 digit in End Time");
			return false;
		}
	else  if(!numbers.test(endTime))
		{
		table.rows[rowcount].cells[5].children[0].value="";
	table.rows[rowcount].cells[5].children[0].className="error";
	$('#feedbackDiv').html("Please enter only numbers in End Time");
		return false;
		}
	else
	{		 
  		  if (filter.test(endTime)) {
		 
	        return true;
	      }
	    else {
	    	 
	    	table.rows[rowcount].cells[5].children[0].value="";
	    	table.rows[rowcount].cells[5].children[0].className="error";
	    	  $('#feedbackDiv').html("Please enter end time format from 0000 to 2359 in End Time");
	        return false;
			}
		}
	}
function fnSearchMessage(){
	var value = $('#value').val();
    var levelDropdownValue=$('#level').find('option:selected').val();
    //console.log("Level:"+levelDropdownValue+":Value:"+value);
	var url = 'canonE307SrvcMsgSearch.jsp?Action=searchMessage';
	document.forms['messageSearchForm'].action = url;
	document.forms['messageSearchForm'].submit();			
}
function compareBaseUnitStartdate(iVal,x){
	//alert("compareBaseUnitStartdate");
    var tDate = "";
	var fDate = "";
		 $('#feedbackDiv').html("");
		 $("#resultDiv").html("");	
         $('#updateMsg').html("");		 
		 tDate = $('#endDate'+iVal).val();
		 fDate= $('#startDate'+iVal).val();
         var table = document.getElementById('itemupdate'),rows = table.getElementsByTagName('tr'), i, j, cells;		 
		 var rowcount=x.parentNode.parentNode.rowIndex;   		
		 var sd=table.rows[rowcount].cells[2].children[0].id.replace('Div','');		
		  var sd1="";
		  sd1="#"+sd;
	     $(sd1).removeClass("error");
		var res=validateDate(fDate);
		
	if(res!=false){	
		
		if(tDate!=null && tDate!=""){
			//var Months = new Array("Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")
			var yValid = "FALSE";
			var mValid = "FALSE";
			var dValid = "FALSE";
			var yyValid = "FALSE";
			var mmValid = "FALSE";
			var ddValid = "FALSE";
			var fParams = fDate.split("/");
			var tParams = tDate.split("/");
			var x=0;
			var fx=0;
			var tx=0;
			//alert("fparams:"+fParams+"tParams:"+tParams);  
			if((fParams[1] > 0) || (tParams[1] > 0))
			{
					if((tParams[2] - fParams[2]) > 0)
					{
						yValid = "TRUE";
					}
					else if((tParams[2] - fParams[2]) == 0)
					{
						yyValid = "TRUE";
					}
					fx = fParams[0];
					tx = tParams[0];
					
				  //alert("fx:"+fx);
				  //alert("tx:"+tx);      
					if(yValid == "TRUE")
					{
						dValid = "TRUE";
					}
					else if (((tx - fx) == 0) && ((yValid == "TRUE")|| (yyValid == "TRUE")) && ((tParams[1] - fParams[1]) >0))
					{
						mValid = "TRUE";
					}
					else
					{
						if((yValid == "TRUE")|| (yyValid == "TRUE"))
						{
							if(tx < fx)
							{
								mValid = "FALSE";
								dValid = "FALSE";
							}
							else if(((tParams[1] - fParams[1]) <0) && ((tx - fx) == 0))
							{
								mValid = "FALSE";
								dValid = "FALSE";          
							}
							else
							{
								mValid = "TRUE";
								dValid = "TRUE";
							}
						}
						else if((tx > fx) && ((yValid == "TRUE")|| (yyValid == "TRUE")))
						{
							mValid = "TRUE";
							dValid = "TRUE";
						}
					}
					if((mValid == "TRUE")&& (yyValid == "TRUE"))
					{
						dValid = "TRUE";
					}
					if(!(dValid == "TRUE"))
					{
						$(sd1).addClass("error");
						$('#feedbackDiv').html("Please select start date less than end date: " + tDate);
						$('#startDate'+iVal).val('');					
					
						return false;
					}
			}
		}
	}
	else if(res==false)
		{
		$(sd1).addClass("error");
		$('#feedbackDiv').html("Please enter valid start date with format mm//dd//yyyy");
		$('#startDate'+iVal).val('');	
		}
	}	
	function compareBaseUnitEnddate(iVal,x){	
	var tDate = "";
	var fDate = "";
	
		tDate = $('#endDate'+iVal).val();
		fDate= $('#startDate'+iVal).val();
		var res=validateDate(tDate);
		var rowcount=x.parentNode.parentNode.rowIndex;   
        var table = document.getElementById('itemupdate'),rows = table.getElementsByTagName('tr'), i, j, cells;		 		
		 var ed=table.rows[rowcount].cells[3].children[0].id.replace('Div','');
		 var ed1="";
		  ed1="#"+ed;
	     $(ed1).removeClass("error");
	    $('#feedbackDiv').html("");	
	    $("#resultDiv").html("");
		$('#updateMsg').html("");
if(res!=false)
	{
	if(tDate!=null && tDate!='null' && tDate!='' && fDate!=null && fDate!='null' && fDate!=''){
		var yValid = "FALSE";
		var mValid = "FALSE";
		var dValid = "FALSE";
		var yyValid = "FALSE";
		var mmValid = "FALSE";
		var ddValid = "FALSE";
		
		var fParams = fDate.split("/");
		var tParams = tDate.split("/");
		
		//alert("fParams :"+ fParams + "tParams :" + tParams);
		var x=0;
		var fx=0;
		var tx=0;
		if((fParams[1] > 0) || (tParams[1] > 0))
		{
			if((tParams[2] - fParams[2]) > 0)
			{
				yValid = "TRUE";
			}
			else if((tParams[2] - fParams[2]) == 0)
			{
				yyValid = "TRUE";
			}
			fx = fParams[0];
			tx = tParams[0];
	     //alert("fx:"+fx);
		// alert("tx:"+tx);      
			if(yValid == "TRUE")
			{
				dValid = "TRUE";
			}
			else if (((tx - fx) == 0) && ((yValid == "TRUE")|| (yyValid == "TRUE")) && ((tParams[1] - fParams[1]) >0))
			{
				mValid = "TRUE";
			}
			else
			{
				if((yValid == "TRUE")|| (yyValid == "TRUE"))
				{
					if(tx < fx)
					{
						mValid = "FALSE";
						dValid = "FALSE";
					}
					else if(((tParams[1] - fParams[1]) <0) && ((tx - fx) == 0))
					{
						mValid = "FALSE";
						dValid = "FALSE";          
					}
					else
					{
						mValid = "TRUE";
						dValid = "TRUE";
					}
				}
				else if((tx > fx) && ((yValid == "TRUE")|| (yyValid == "TRUE")))
				{
					mValid = "TRUE";
					dValid = "TRUE";
				}
			}
			if((mValid == "TRUE")&& (yyValid == "TRUE"))
			{
				dValid = "TRUE";
			}
			if(!(dValid == "TRUE"))
			{
				$(ed1).addClass("error");
			//	$('#feedbackDiv').html("Please select end date greater than Date: " + fDate);
			    $('#feedbackDiv').html("Please select end date greater than start Date");
				$('#endDate'+iVal).val('');
				$('#endDate'+iVal).val($('#endDt'+iVal).val());
				return false;
			}
		}	
	}	

  }
		else if(res==false)
		{
			$(ed1).addClass("error");
			$('#feedbackDiv').html("Please enter valid end date with format mm//dd//yyyy");
			$('#endDate'+iVal).val('');	
		}
}
	function validateDate(testDate)	
	{
	 var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/ ;
	 if(!(date_regex.test(testDate)))
	 {
	    return false;
	  }
	 else
		 return true;
 }
 
function checkNull(s){
	try{	
		if (s == null || s == undefined  ) {
		 s="";	
		}
		
	}catch (e) {
		 s="";
		console.log(e);
	} 
	return s;
}
function updateMessage(){
	var msgSize = $('#msgSize').val();		
	$('#feedbackDiv').html('');
	$('#updateMsg').html('');
	var retVal=true;
	var checkVal ="false";
	var strTime="";
	var edtTime="";
	if(msgSize!=null && msgSize!=""){
		for(j=0;j<msgSize;j++){
			if (document.getElementById('selectedItem'+j).checked) {
				checkVal ="true";
			}
		}
		if(checkVal=="true"){
			//alert("checkVal");
			for(i=0;i<msgSize;i++){
				if(document.getElementById("selectedItem"+i).checked == true){
					var startDate = $('#startDate'+i).val();
					//alert("startDate::"+startDate);						
					var endDate = $('#endDate'+i).val();
					//alert("endDate::"+endDate);
					var startTime = $('#startTime'+i).val();
					//alert("startTime::"+startTime);
					var endTime = $('#endTime'+i).val();
					//alert("endTime::"+endTime);
					var msg = $('#msg'+i).val();
					//alert("msg::"+msg);	
					//var fullhour = $('#fullHour'+i).is(':checked');
					//alert("fullhour::"+fullhour);
					 $('#startDate'+i).removeClass("error");
					 $('#startTime'+i).removeClass("error");
					 $('#endDate'+i).removeClass("error");	
					 $('#endTime'+i).removeClass("error");
					 $('#msg'+i).removeClass("error");	
					if($('#fullHour'+i).is(':checked'))
						{
							$('#fullHour'+i).val("true");
						}else{
							$('#fullHour'+i).val("false");
						}
					//alert("fullhoursdfsdf::"+fullhour);
					startTime=checkNull(startTime);
					if(0 == startTime.length)
					{
						 //do nothing   
					}
			       else{
					  // alert("startTime::"+startTime);
					        stTime =startTime.match(/.{1,2}(?=(.{2})+(?!.))|.{1,2}$/g);
					         if(typeof  stTime[1] ==="undefined")
								{
								stTime[1]="00";
								}
							strTime=stTime[0]+":"+stTime[1];
							  //alert("strTime:"+strTime);
							$('#strTime'+i).val(strTime);
							//////alert("strTime:"+$('#strTime'+i).val());
				      }
					 endTime=checkNull(endTime);
						if(0 === endTime.length)
						{
							 //do nothing     
						}
						else
						{
							 edTime =endTime.match(/.{1,2}(?=(.{2})+(?!.))|.{1,2}$/g);
							 if(typeof  edTime[1] ==="undefined")
								{
								edTime[1]="00";
								}
							edtTime=edTime[0]+":"+edTime[1];
							//alert("edtTime:"+edtTime);
							$('#edTime'+i).val(edtTime); //alert("strTime:"+strTime);
							//alert("edttimr:"+$('#edTime'+i).val());
						}						
					 // alert("checktest::");	
					 if((startDate=="" || endDate=="") ||(strTime=="" || edtTime=="") ||(msg=="")){
						submitFlag = "N";
						//alert("startDate:"+startDate+"endDate:"+endDate+"strTime:"+strTime+"edtTime:"+edtTime+"msg:"+msg);
						if(startDate=="")
							{
							 $('#startDate'+i).addClass("error");									
							}
						
						if(strTime=="")
						{
							 $('#startTime'+i).addClass("error");
						 
						}
						 if(endDate=="")	
						{
						  $('#endDate'+i).addClass("error");	
						
						}
						if(edtTime=="")
						{
							$('#endTime'+i).addClass("error");
						}
						if(msg=="")
							{
							 $('#msg'+i).addClass("error");						
							}
							$('#updateMsg').html('');
						$('#feedbackDiv').html("Please enter all fields before saving the record.");
                       // alert("feedback::"+$('#feedbackDiv').val());							
						return ;
		           }
					
				}
			}
		}else{
			$('#updateMsg').html('');
			$('#feedbackDiv').html("Please select check box to update details");
			return;
		}
	}
	var url = 'canonE307SrvcMsgSearch.jsp?Action=updateMessage';
	document.forms['messageSearchForm'].action = url;
	document.forms['messageSearchForm'].submit();		
}

function fnEnableConfigDet(iVal){
    $('#feedbackDiv').html('');
	$('#updateMsg').html('');
	if(document.getElementById("selectedItem"+iVal).checked == true){

		    document.getElementById("endDate"+iVal).readOnly = false;
		    document.getElementById("startDate"+iVal).readOnly = false;
		    $("#startDate"+iVal).attr("disabled", false);
		    $("#endDate"+iVal).attr("disabled", false);
		    $("#endDate"+iVal).css("background-color", "#FFFFFF");
		    $("#endDateTD"+iVal).css("background-color", "#FFFFFF");
			$("#endDate"+iVal).addClass("datePicker");
			$("#startDate"+iVal).css("background-color", "#FFFFFF");
			$("#startDateTD"+iVal).css("background-color", "#FFFFFF");
			$("#startDate"+iVal).addClass("datePicker");
			document.getElementById("msg"+iVal).readOnly = false;				
			$("#fullhourTD"+iVal).css("background-color", "#FFFFFF");
			$('#fullHour'+iVal).attr("disabled", false);
			$('#msg'+iVal).css("background-color", "#FFFFFF");	
			$('#msgTD'+iVal).css("background-color", "#FFFFFF");	
			 document.getElementById("endTime"+iVal).readOnly = false;
			 document.getElementById("startTime"+iVal).readOnly = false;
     		 document.getElementById("startTime"+iVal).maxLength=4;
			 document.getElementById("endTime"+iVal).maxLength=4;
			 $("#endTime"+iVal).css("background-color", "#FFFFFF");
			 $("#startTime"+iVal).css("background-color", "#FFFFFF");
			 $("#endtimeTD"+iVal).css("background-color", "#FFFFFF");
			 $("#starttimeTD"+iVal).css("background-color", "#FFFFFF");
			 $('#msg'+iVal).addClass("itemDescC");						
			/*	$(".itemDescC").each(function () {
				  addToolTip1($(this));	
				});
	*/
}
	else
		{
			document.getElementById("endDate"+iVal).readOnly = true;
		 	document.getElementById("startDate"+iVal).readOnly = true;
			$("#endDate"+iVal).css("background-color", "#F7F7E7");
			$("#endDate"+iVal).removeClass("datePicker");
			$("#startDate"+iVal).css("background-color", "#F7F7E7");
			$("#startDate"+iVal).removeClass("datePicker");
			$("#startDate"+iVal).attr("disabled", true);
			$("#endDate"+iVal).attr("disabled", true);
		 	document.getElementById("endTime"+iVal).readOnly = true;
		 	document.getElementById("startTime"+iVal).readOnly = true;
			$("#startTime"+iVal).css("background-color", "#F7F7E7");
			$("#endTime"+iVal).css("background-color", "#F7F7E7");
			$("#endtimeTD"+iVal).css("background-color", "#F7F7E7");
			$("#starttimeTD"+iVal).css("background-color", "#F7F7E7");	
			$("#startDateTD"+iVal).css("background-color", "#F7F7E7");
			$("#endDateTD"+iVal).css("background-color", "#F7F7E7");				
			//$("#endTime"+iVal).removeClass("allownumeric");
			 // $("#startTime"+iVal).removeClass("allownumeric");
			  document.getElementById("startTime"+iVal).maxLength=10;
			 document.getElementById("endTime"+iVal).maxLength=10;
		 	document.getElementById("msg"+iVal).readOnly = true;
			$("#msg"+iVal).css("background-color", "#F7F7E7");	
			$("#msg"+iVal).removeClass("itemDescC");
		    $("#fullhourTD"+iVal).css("background-color", "#F7F7E7");
			$('#fullHour'+iVal).attr("disabled", true);

		}
}
function show() {
    $.blockUI({ css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff' 
    }, baseZ: 9000
	}); 
}

function hide() {
	$.unblockUI();
} 
function searchMessageLink1(pageNum) {

	 $('#feedbackDiv').html('');
	$('#updateMsg').html('');
     var stract="searchMessage";	
     var levelDropdownValue=$('#level').find('option:selected').val();
	   var value=$('#value').val();
	  modalName = "#searchMsg";
		var qryStr = "modalName=" + modalName + "&pageNum=" + pageNum + "&Action=" + stract +"&level=" + levelDropdownValue +"&value=" + value;
		show();
		$.ajax( {
			url : "canonE307SrvcMsgSearch.jsp",
			data : qryStr,
			type : "POST",
			cache : false,
			success : function(data) {
				hide();
				$(modalName).html("");
				$(modalName).html(data);
				setRowStyles("#itemupdate");
		
			}
		});		
	}
	function searchMessageLink(pageNum){
		$('#feedbackDiv').html('');
		$('#updateMsg').html('');
	    var levelDropdownValue=$('#level').find('option:selected').val();
		var value=$('#value').val();
		 var stract="searchMessage";	
		var qryStr="pageNum="+pageNum;
		qryStr= qryStr+"&Action=" + stract +"&level=" + levelDropdownValue +"&value=" + value;
		show();
		 $.ajax({
				url:"canonE307SrvcMsgSearchIncl.jsp",
				data:qryStr,
				type:"POST",
		        cache: false,
				success: function(data){  
				hide();
				document.getElementById("resultsDiv").innerHTML = $.trim(data);
					$("#paging #a"+pageNum).css({"color":"white","background-color":"#A10304"});
					$(".itemDescC").each(function () {
						  addtoolTip1($(this));	
					});
					
					$(".datePicker").datepicker({
						 dateFormat: 'mm/dd/yy',
						 changeMonth: true,
						 changeYear: true
					});
			 	}             
		});
	}
	
	function setRowStyles(tbl) {
		$(tbl + " tbody tr:odd").each(function() {
			$(this).addClass("evenRow");
		});
    }
	function fnClear(){
		 $('#feedbackDiv').html("");		
	     $('#updateMsg').html("");	
		 $('#level').val('ALL');
         $('#value').val('');		 
		 $('.error').removeClass('error');	
		var url = 'canonE307SrvcMsgSearch.jsp';
		document.forms['messageSearchForm'].action = url;
		document.forms['messageSearchForm'].submit();		
	}
	function createNewConfig(){
		var url = 'canonE307ServMsgCreate.jsp';
		document.forms['messageSearchForm'].action = url;
		document.forms['messageSearchForm'].submit();		
	}
/*	$(function (){
		$(".itemDescC").each(function () {
		  addToolTip1($(this));	
		});
		
	});*/

	 $(function(){	
		 if (!$.support.placeholder) {
		    	$('[placeholder]').focus(function() {
		    		  var input = $(this);
		    		  if (input.val() == input.attr('placeholder')) {
		    		    input.val('');
		    		    input.removeClass('placeholder');
		    		  }
		    		}).blur(function() {
		    		  var input = $(this);
		    		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    		    input.addClass('placeholder');
		    		    input.val(input.attr('placeholder'));
		    		  }
		    		}).blur();
		        }		
	 });
	 $(".datePicker").datepicker({
		 dateFormat: 'mm/dd/yy',
		 changeMonth: true,
		 changeYear: true
	});
	 $(document).ready(function() {
			$("#a1").css({"color":"white","background-color":"#A10304"});
		//    $('.startDate').datepicker();
		//    $('.endDate').datepicker();
		//	fnRdOnly();
		});
	 function itmDescLimit(ele) 	{
		 var lt=2000;
		if (ele.value.length > lt) {
			ele.value = ele.value.substring(0, lt);
		} else {
			$("#taCnt").html(lt - ele.value.length);
		}
	}
/* 	 function isIE () {
		  var myNav = navigator.userAgent.toLowerCase();
		  return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
	}
	 
	 (function ($) {
		  $.fn.fixOverflow = function () {
		    if (isIE()) {
		      return this.each(function () {
		        if (this.scrollWidth > this.offsetWidth) {
		          $(this).css({ 'padding-bottom' : '20px', 'overflow-y' : 'hidden' });
		        }
		      });            
		    } else {
		      return this;
		    }
		  };
		})(jQuery); */

	// $('itemupdate').fixOverflow().doOtherPlugin();

/*	function addtoolTip1(e){
		$(e).click(function() { 
		             showTT1($(this),$(this).val());
	            }
	      );
	}

	function showTT1(element, text) {
		
		$('#toolTip1 #eleId').val(element.attr("id"));
		
	    var offset = element.offset();
	    $('#toolTip1 #ta').val(text);
	    
	    $('#toolTip1')
	    .css({
	        'top'  : (offset.top-105) + 'px',
	        'left' : (offset.left +element.outerWidth()-400)+ 'px' 
	        
	    }).show();
	   // $('#toolTip1 #ta').focus();
	    $("#toolTip1 textarea").focus();
	    $("#taCnt").html(2000 - text.length);
	}

	function hideTT1(s) {
		var eleId=$('#toolTip1 #eleId').val();
	    if(s=="set"){ 
		  $("#"+eleId).val($("#toolTip1 #ta").val());
	    }
	    $('#toolTip1 #eleId').val("");
	    $('#toolTip1 #ta').val("");
		$('#toolTip1').hide();
	    
	}	*/
</script>
<style type="text/css">

#toolTip1 {
	background-color: #fff;
	border: solid 3px #d6d6d6;
	color: green;
	font-weight: bold;
	display: none;
	font-family: Helvetica, Arial, sans-serif;
	font-size: 11px;
	line-height: 1.3;
	padding: 5px 7px;
	position: absolute;
}

/* .btn_red {
	font-size: 15px !important;
	color: #FFFFFF !important;
	background: -webkit-linear-gradient(#cc0000, #8e191c);
	background: -moz-linear-gradient(center top , #cc0000 5%, #8e191c 100%) repeat scroll 0 0 white;
	background: -ms-linear-gradient(#cc0000, #8e191c);
	background: linear-gradient(#cc0000, #8e191c);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#cc0000', endColorstr='#8e191c');
	background-color: #8e191c;
	border: none;
	cursor: pointer;
	display: inline-block;
	padding: 4px 18px 4px 18px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;	
	}
.btn_red:hover {
	background: -webkit-linear-gradient(#ff0000, #cc0000);
	background: -moz-linear-gradient(center top , #ff0000 5%, #cc0000 100%) repeat scroll 0 0 white;
	background: -ms-linear-gradient(#ff0000, #cc0000);
	background: linear-gradient(#ff0000, #cc0000);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#ff0000', endColorstr='#cc0000');
	background-color: #cc0000;
	text-decoration: none !important;
	}
	
a {
	color: #0000FF;
}
	 #paging a{
 margin: 20px 5px 0px 5px;
 font-weight: bold;
 font-size: 11px;
 float: left;
 border: 1px solid #F6F6F6;
 padding: 2px 5px;
 color: #666666;
 text-decoration: underline;
}	
 .pageLinkBg {
	color : #FFFFFF;
	background-color : black;
}

.top_bar{
background-color: #FFFFFF;
color: #336699;
font-size: 18;
font-weight: bolder;
height: 26px;
}

.error
{
border-color:red;
}

.search_hd {
	background-color: #FFFFFF;
	color: #336699;
	font-size: 14;
	font-weight: bolder;
}
.search_text {
	background-color: #FFFFFF;
	color: #336699;
	font-size: 12;
	font-weight: bolder;
}
 */
msg_td{
	padding: 0px;
	background-color:#F7F7E7;
	height:30px;
}	
</style>

<%@include file="canonE307ServReqFtr.jsp"%>