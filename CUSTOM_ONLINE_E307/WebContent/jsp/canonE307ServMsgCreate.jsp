
<%@page import="com.canon.apps.servreq.beans.*"%>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
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
<%
	String imgSource=ctxPath+"/common/images/cross.jpg"; 
    imgSrc=ctxPath+"/common/images/download.png";
%>

<div id="main_content">  
<div id="page_top">
	<h1>ASCC Service Messages</h1>
</div>	
<div style="margin:0px 5px;"><h1>Service Message Creation</h1></div>
	<form name="messageCreationForm" id="messageCreationForm" method="post" action="canonE307SerMessageCreation.jsp" >
	<input type="hidden" id="imgVal" name="imgVal" value="">
	<div class="service">
	 <table  cellpadding="1" cellspacing="1" style="background-color:#FFFFFF;align:center;margin:20px 5px 20px 5px;"  id="itemAdd">
	 	<input type="hidden" id="itemCount" name="itemCount" value="">
		<input type="hidden" id="userId" name="userId" value="<%=loginUser%>">
		<tr>			
			<th class="ascctablecell" align="center" style="width: 10%;">Level</th>
			<th class="ascctablecell" align="center" style="width: 8%;">Value</th>
			<th class="ascctablecell" align="center" style="width: 8%;">Start Date</th>
			<th class="ascctablecell" align="center" style="width: 7%;">Start Time</th>
			<th class="ascctablecell" align="center" style="width: 7%;">End Date</th>
			<th class="ascctablecell" align="center" style="width: 7%;">End Time</th>
            <th class="ascctablecell" align="center" style="width: 5%;">24Hour</th>				
            <th class="ascctablecell" align="center" style="width: 25%;">Service Message</th>		
            <th class="ascctablecell" align="center" style="width: 4%;">Delete</th>				
					
		</tr>
	
		<tr id="msgRow0">
			  <td >
				<SELECT NAME="level0" id="level0" style="width: 135px;">
				<option value="0" selected>Please Select Level</option>
				<option value="REGION">Region</option>
				<option value="BRANCH">Branch</option>
				<option value="SVCTEAM">Team</option>
				<option value="POSTAL">Postal</option>
			<!-- 	<option value="PARTY_NUMBER">Party Number</option> -->
				<option value="PARTY_SITE_NUMBER">Party Site Number</option>
				<option value="ACCOUNT_NUMBER">Account Number</option>
				<option value="SERIAL_NUMBER">Serial Number</option>
				<option value="MODEL">Model</option>
				
				</select>
		    </td>	
			<td nowrap><input type="text" id="levelValue0" name="levelValue0" value="" style="width:145px;">&nbsp;<img src="<%=imgSrc %>" alt="" height="21" onClick="openLevelLov('levelSearchLovDiv','1', '10','0','True',this)"></td>           
			<td><input type="text" id="startDate0" name="startDate0" class="datePicker startDate"  size=9  onChange="compareBaseUnitStartdate(0,this)" style="width:80px;"></td>
			<td style="padding-left:10px;"><input type="text" id="startTime0" name="startTime0"  onChange="validateStartTime(this)" value="" maxlength=4 style="width:55px;"></td>					
			<td><input type="text" id="endDate0" name="endDate0" class="datePicker endDate" value="" size=9 onChange="compareBaseUnitEnddate(0,this)" style="width:80px;"></td>		
			<td style="padding-left:10px;"><input type="text" id="endTime0" name="endTime0" value=""maxlength=4 onChange="validateEndTime(this)" style="width:55px;"></td>		
			<td style="padding-left:15px;"><input type="checkbox" name="fullhour0" id="fullhour0" onclick="$(this).attr('value', this.checked ? true : false)" style="margin-left:15px;border:0px;"/></td>
			<td><input type="text" id="msg0" name="msg0" value="" class="itemDescC" style="width: 380px;"></td>	
            <td  style="text-align:center">
            <img src="<%=imgSource%>" alt="" height="18" class="del" onClick="delRow(0)"></td>
 		</tr>
			<tr id="msgRow1">
			  <td >
				<SELECT NAME="level1" id="level1" style="width: 135px;">
				<option value="0" selected>Please Select Level</option>
				<option value="REGION">Region</option>
				<option value="BRANCH">Branch</option>
				<option value="SVCTEAM">Team</option>
				<option value="POSTAL">Postal</option>
				<!-- <option value="PARTY_NUMBER">Party Number</option> -->
				<option value="PARTY_SITE_NUMBER">Party Site Number</option>
				<option value="ACCOUNT_NUMBER">Account Number</option>
				<option value="SERIAL_NUMBER">Serial Number</option>
				<option value="MODEL">Model</option>
				</select>
		    </td>	
			<td nowrap><input type="text" id="levelValue1" name="levelValue1" value="" style="width:145px;">&nbsp;<img src="<%=imgSrc %>" alt="" height="21" onClick="openLevelLov('levelSearchLovDiv','1', '10','1','True',this)"></td>            					
			<td><input type="text" id="startDate1" name="startDate1" class="datePicker startDate" value="" size=9 onChange="compareBaseUnitStartdate(1,this)" style="width: 80px;"></td>
			<td style="padding-left:10px;"><input type="text" id="startTime1" name="startTime1" onChange="validateStartTime(this)" value="" maxlength=4 style="width: 55px;"></td>					
			<td><input type="text" id="endDate1" name="endDate1" class="datePicker endDate" value=""  size=9 onChange="compareBaseUnitEnddate(1,this)" style="width: 80px"></td>		
			<td style="padding-left:10px;"><input type="text" id="endTime0" name="endTime0" value=""maxlength=4 onChange="validateEndTime(this)" style="width: 55px;"></td>		
			<td style="padding-left:15px;"><input type="checkbox" name="fullhour1" id="fullhour1"  onclick="$(this).attr('value', this.checked ? true : false)" style="margin-left:15px;border:0px;"/></td>
			<td><input type="text" id="msg1" name="msg1" value="" class="itemDescC" style="width: 380px;"></td>	
			<td style="text-align:center"><img src="<%=imgSource%>" alt="" height="18" class="del" onClick="delRow(1)"></td>				   
		</tr>
 		<tr id="msgRow2">
			  <td>
				<SELECT NAME="level2" id="level2" style="width:135px;">
				<option value="0" selected>Please Select Level</option>
				<option value="REGION">Region</option>
				<option value="BRANCH">Branch</option>
				<option value="SVCTEAM">Team</option>
				<option value="POSTAL">Postal</option>
				<!-- <option value="PARTY_NUMBER">Party Number</option> -->
				<option value="PARTY_SITE_NUMBER">Party Site Number</option>
				<option value="ACCOUNT_NUMBER">Account Number</option>
				<option value="SERIAL_NUMBER">Serial Number</option>
				<option value="MODEL">Model</option>
				</select>
		    </td>	
			<td nowrap><input type="text" id="levelValue2" name="levelValue2" style="width:145px;">&nbsp;<img src="<%=imgSrc %>" alt="" height="21" onClick="openLevelLov('levelSearchLovDiv','1', '10','2','True',this)"></td>						
			<td><input type="text" id="startDate2" name="startDate2" class="datePicker startDate" value=""  size=9 onChange="compareBaseUnitStartdate(2,this)" style="width: 80px"></td>
			<td style="padding-left:10px;"> <input type="text" id="startTime2" name="startTime2" value=""  maxlength=4 onChange="validateStartTime(this)" style="width: 55px;"></td>					
			<td><input type="text" id="endDate2" name="endDate2" class="datePicker endDate" value=""  size=9 onChange="compareBaseUnitEnddate(2,this)" style="width: 80px"></td>		
			<td style="padding-left:10px;"><input type="text" id="endTime2" name="endTime2" value="" maxlength=4 onChange="validateEndTime(this)" style="width: 55px;"></td>								
			<td style="padding-left:15px;"><input type="checkbox" name="fullhour2"  id="fullhour2"  onclick="$(this).attr('value', this.checked ? true : false)" style="margin-left:15px;border:0px;"/></td>
			<td><input type="text" id="msg2" name="msg2" value="" class="itemDescC" style="width:380px;"></td>		
			<td style="text-align:center"><img src="<%=imgSource%>" alt="" height="18" class="del" onClick="delRow(2)"></td>			    
		</tr>
		<tr id="msgRow3">
			  <td >
				<SELECT NAME="level3" id="level3" style="width:135px;">
				<option value="0" selected>Please Select Level</option>
				<option value="REGION">Region</option>
				<option value="BRANCH">Branch</option>
				<option value="SVCTEAM">Team</option>
				<option value="POSTAL">Postal</option>
				<!-- <option value="PARTY_NUMBER">Party Number</option> -->
				<option value="PARTY_SITE_NUMBER">Party Site Number</option>
				<option value="ACCOUNT_NUMBER">Account Number</option>
				<option value="SERIAL_NUMBER">Serial Number</option>
				<option value="MODEL">Model</option>
				</select>
		    </td>	
			<td nowrap><input type="text" id="levelValue3" name="levelValue3" value="" style="width:145px;">&nbsp;<img src="<%=imgSrc %>" alt="" height="21" onClick="openLevelLov('levelSearchLovDiv','1','10','3','True',this)"></td>				
			<td><input type="text" id="startDate3" name="startDate3" class="datePicker startDate" value=""  size=9 onChange="compareBaseUnitStartdate(3,this)" style="width: 80px;"></td>
			<td style="padding-left:10px;"><input type="text" id="startTime3" name="startTime3" value=""  maxlength=4 onChange="validateStartTime(this)" style="width: 55px;"></td>					
			<td><input type="text" id="endDate3" name="endDate3" class="datePicker endDate" value=""  size=9 onChange="compareBaseUnitEnddate(3,this)" style="width: 80px;"></td>		
			<td style="padding-left:10px;"><input type="text" id="endTime3" name="endTime3" value="" maxlength=4 onChange="validateEndTime(this)" style="width: 55px;"></td>					
			<td style="padding-left:15px;"><input type="checkbox" name="fullhour3"  id="fullhour3"  onclick="$(this).attr('value', this.checked ? true : false)" style="margin-left:15px;border:0px;"/></td>
			<td><input type="text" id="msg3" name="msg3" value="" class="itemDescC" style="width: 380px;"></td>		
			<td style="text-align:center"><img src="<%=imgSource%>" alt="" height="18" class="del" onClick="delRow('3')"></td>				
		</tr> 
		
	</table>
	</div>
	<table width="98%" align="center">
	<tr align="left" style="color:red;font-size:15px">
	<td>
	<div id="feedbackDiv">	
	</div>
	</td>
	</tr>
	<tr align="left" style="color:red;font-size:15px">
	<td>
	<div id="errorDiv" style="width:200px">	
	</div>
	</td>
	</tr>
	<tr style="color:green;font-size:15px:">
	<td>
	<div id="resultDiv">
	</div>
	</td>
	</tr>
	</table>	
<br>
<div align="center">
	<table width="200px;" align="center">
		<tr align="center">			
			<td><a href="javascript:addMessage()" class="btn">Add&nbsp;Row</a></td>
			<td><a href="javascript:saveMessage()" class="btn">Save</a></td>
			<td><a href="javascript:clickClear()" class="btn">Clear</a></td>
			<td><a href="javascript:clickBack()" class="btn">Back</a></td>
		</tr>
	</table>	
</div>
	</form>
</div>
  <div id="levelSearchLovDiv"> 
  </div>	
  
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

<%@include file="canonE307ServReqFtr.jsp"%>

<script type="text/javascript">

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
        'top'  : (offset.top+30) + 'px',
        'left' : (offset.left +element.outerWidth()-500)+ 'px' 
        
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

$(document).ready(function() {
	//alert("st12");
    $('.startDate').datepicker();
    $('.endDate').datepicker();
    $('.startDate').datepicker('setDate', 'today');
});
/*$(".datePicker").datepicker({
	 dateFormat: 'M dd yy',
	 changeMonth: true,
	 changeYear: true
}); */
/*	$(".allownumeric").live("keypress keyup blur",function (event) {    
         $(this).val($(this).val().replace(/[^\d].+/, ""));
        	  if((event.which < 48) || ( event.which > 57)) {
              event.preventDefault();
          }
      }); */
	
	 function softHandler(){ 
   	  $(".datePicker").datepicker({dateFormat: 'mm/dd/yy',changeMonth: true, changeYear: true});
   	}
 
 /* 	$(function(){
   	 // softHandler();
   	  $('.datePicker').live('click', softHandler);
   	});
    $('.datePicker').datepicker({
		   onClose: function() { this.focus(); }
	});
   	$('input.datePicker:not(.endLive)').live('focus',function() {
   		$(this).addClass('endLive').datepicker({dateFormat: 'mm/dd/yy', changeMonth: true, changeYear: true}).focus();
   		return false; 
   	}); */
	function  validateStartTime(x)
	{	
	//alert("starttime");
		$('#feedbackDiv').html("");
		 $("#resultDiv").html("");
		  $("#errorDiv").html("");
	      var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), cells;
	      var rowcount=x.parentNode.parentNode.rowIndex;   
		  var startTime= table.rows[rowcount].cells[3].children[0].value;
			table.rows[rowcount].cells[3].children[0].className="";
			 var numbers = /^[0-9]+$/;
		//alert("startTime:"+startTime);		  
		  var filter = /^([01]?[0-9]|2[0-3]):?[0-5][0-9]?$/;
		  	if(startTime.length!=4)
			{
				table.rows[rowcount].cells[3].children[0].value="";
				table.rows[rowcount].cells[3].children[0].className="error";
				$('#feedbackDiv').html("Please enter 4 digit in Start Time");
				return false;
			}
		  	else  if(!numbers.test(startTime))
		  		{
		  		table.rows[rowcount].cells[3].children[0].value="";
				table.rows[rowcount].cells[3].children[0].className="error";
				$('#feedbackDiv').html("Please enter only numbers in Start Time");
		  		return false;
		  		}

		else
		{		 
	  		
		  if (filter.test(startTime)) {		 
		        return true;
		    }
		    else {
		    	 
		    	table.rows[rowcount].cells[3].children[0].value="";
		    	table.rows[rowcount].cells[3].children[0].className="error";
		    	  $('#feedbackDiv').html("Please enter correct start time format from 0000 to 2359 in Start Time");
		        return false;
		    }
		
	  }
	}
	function  validateEndTime(x)
	{	
		$('#feedbackDiv').html("");
		 $("#resultDiv").html("");
		 $("#errorDiv").html("");
	      var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), cells;
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
/*$('.del').live('click',function(){
	    //$('#feedbackDiv').html("");
		//$('#resultDiv').html("");
	//$(this).parent().parent().remove();
	 
	 $(this).closest('tr').remove()
	var count = $('#itemAdd tr').length;
	count=count-1;
	$('#itemCount').val(count);
});*/
function delRow(rowNo){
	$("#itemAdd tr[id='msgRow" + rowNo + "']").remove();
	var count = $('#itemAdd tr').length;
	count=count-1;
	$('#itemCount').val(count);
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
    function openLevelLov(mName, pageNum, iVal,itemCount,Flag,x){
		$('#feedbackDiv').html("");
		$('#resultDiv').html("");
		 $("#errorDiv").html("");
		 modalName = "#"+mName;
		 var Error="NErr";
		 var errMsg="";
		  var title;
		  var urlDetail;
		  var flag=Flag;
		  var count;
		   $(modalName).html("");    
		  if(flag=="True")
			  {
			  count=itemCount;			  
			  }
		  else
			  {
			  count=$("#itemCount").val();
			  count=count-1;
			  }
		    //console.log("itemCount:"+count);
		    var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), cells;
		    cells = rows[count].getElementsByTagName('td');
		    var rowcount=x.parentNode.parentNode.rowIndex;  
		    //console.log("rowcount : "+rowcount);
		    var levelDropdownValue= table.rows[rowcount].cells[0].children[0].value; 
		    var level=table.rows[rowcount].cells[1].children[0].value;
		    table.rows[rowcount].cells[1].children[0].className="";
		    table.rows[rowcount].cells[0].children[0].className="";
			levelDropdownValue=$.trim(levelDropdownValue);
			var data="";
  		    var qryStr ="&level=" +level+"&pageNum=" + pageNum + "&itemCount=" + count+"&levelDropdownValue="+levelDropdownValue+"&rowIndex="+rowcount;	    
		   switch(levelDropdownValue) {
		    case "REGION":
		    	urlDetail = 'canonE307SerRegionLov.jsp';
		    	title='Region Search';
		        break;
		    case "BRANCH":
		    	urlDetail = 'canonE307SerBranchLov.jsp';
		    	title='Branch Search';
		        break;
		    case "POSTAL":
		    	urlDetail = 'POSTAL';
		    	if(level.length!=5)
		    		{
		    		Error="E";
		    		data=table.rows[rowcount].cells[1].children[0];
		    		errMsg="Please enter 5 digit for Postal";
		    		}
		    	title='Postal Search';
		        break;
		    case "PARTY_NUMBER":
		    	if(level!="")
		    		{
		    	urlDetail = 'canonE307SerPartyLov.jsp';
		    	title='Party Number Search';
		           }
		    	else{
		    		data=table.rows[rowcount].cells[1].children[0];
		    		errMsg="Please enter value to search";
		    		Error="E";
		    	   }
		        break;
		    case "PARTY_SITE_NUMBER":
		    	if(level!="")
	    		{
		    	urlDetail = 'canonE307SerPartySiteNumberLov.jsp';
		    	title='Party Site Number Search';
	    		}
		    	else{
		    		data=table.rows[rowcount].cells[1].children[0];
		    		errMsg="Please enter value to search";
		    		Error="E";
		    	   }
		        break;
		    case "ACCOUNT_NUMBER":
		    	if(level!="")
	    		{
		    	urlDetail = 'canonE307SerAccountNumberLov.jsp';
		    	title='Account Number Search';
	    		}
		    	else{
		    		data=table.rows[rowcount].cells[1].children[0];
		    		errMsg="Please enter value to search";
		    		Error="E";
		    	   }
		        break;
		    case "SERIAL_NUMBER":
		    	if(level!="")
	    		{
		    	urlDetail = 'canonE307SerSerialNumberLov.jsp';
		    	title='Serial Number Search';
	    		}
		    	else{
		    		data=table.rows[rowcount].cells[1].children[0];
		    		errMsg="Please enter value to search";
		    		Error="E";
		    	   }
		        break;
		    case "MODEL":
		    	urlDetail = 'canonE307SerModelLov.jsp';
		    	title='Model Search';
		        break;
		    case "SVCTEAM":
		    	urlDetail = 'canonE307SvcTeamLov.jsp';
		    	title='Team Search';
		    	 break;
		    default:
		    	Error = "E";
		         data=table.rows[rowcount].cells[0].children[0];
		        errMsg="Please select level and search";
		}	
		//console.log("urlDetail:"+urlDetail);  
		//console.log("title:"+title);   
	
		if(Error!="E")
			{
			   if(urlDetail!='POSTAL')
				   {
				   
			show();
			
		  $.ajax({
				url: urlDetail,
				data : qryStr,
				type : "POST",
				cache: false,
				success: function(data){   
				hide();
			  $(modalName).html("");          
			  $(modalName).html(data);
			  $("#mdlLinks #paging  #a1").addClass("pageLinkBg").css( {
					"color" : "#FFFFFF"
				});
				 
			}
		});
			  $(modalName).dialog({
					height: 550,
					title: title,
					modal: true ,
			autoOpen :false,
					 width: 650,		
			 resizable: false,      
			/*		 buttons: { "close": function() {  
											$(modalName).html("");
											$(this).dialog("close");
											$(this).dialog("destroy");
											}
				   }	*/				
				});
		$(modalName).dialog("open");
		$(".ui-dialog").css( {
			"top" : "100px"
		});
	}	    
			}else
				{
			$('#feedbackDiv').html(errMsg);
			data.className="error";
				}
   }
    function fnCloseSearchDlg(){
    	$('#levelSearchLovDiv').html("");
		$('#levelSearchLovDiv').dialog("close");
		$('#levelSearchLovDiv').dialog("destroy");
    }
    
function compareBaseUnitEnddate(iVal,x){	
	var tDate = "";
	var fDate = "";
	     var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), i, j, cells;		 
		 var rowcount=x.parentNode.parentNode.rowIndex;   		
		tDate = table.rows[rowcount].cells[4].children[0].value;
		fDate= table.rows[rowcount].cells[2].children[0].value;
		var res=validateDate(tDate);		
	    $('#feedbackDiv').html("");	
	    $("#resultDiv").html(""); 
		$("#errorDiv").html("");
		 var ed= table.rows[rowcount].cells[4].children[0].value;
		table.rows[rowcount].cells[4].children[0].className="";
if(res!=false)
	{
	if(tDate!=null && tDate!='null' && tDate!='' && fDate!=null && fDate!='null' && fDate!=''){
		///var Months = new Array("Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")
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
				table.rows[rowcount].cells[4].children[0].value="";
				table.rows[rowcount].cells[4].children[0].className="error";
				$('#feedbackDiv').html("Please select end date greater than Date: " + fDate);
				return false;
			}
		}	
	}	

  }
		else if(res==false)
		{
			table.rows[rowcount].cells[4].children[0].value="";
			table.rows[rowcount].cells[4].children[0].className="error";
			$('#feedbackDiv').html("Please enter valid end date with format mm//dd//yyyy");
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
 
function submitMessage(levelArr,levelValueArr,startDateArr,startTimeArr,endDateArr,endTimeArr,fullHourArr,msgArr){
       var user=$('#userId').val();
       //console.log("user"+user);
       $('#feedbackDiv').html("");
       $("#resultDiv").html("");
	    $("#errorDiv").html("");
       var  qryString="&levelArr="+levelArr+"&levelValueArr="+levelValueArr+"&startDateArr="+startDateArr+"&startTimeArr="+startTimeArr+"&endDateArr="+endDateArr+"&endTimeArr="+endTimeArr+"&fullHourArr="+fullHourArr+"&msgArr="+msgArr+"&user="+user;
		var sRes="";
		var resu=[];
		 var resmsg;
		 var scmsg="";
		var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), i, j, cells;
	      var urlDetail="canonE307SerSubmitMsg.jsp";
	  	show();
				  	$.ajax({
						url: urlDetail,
						cache: false,
						type:"POST",
						data:qryString,
						 async: false,
						success: function(data){ 
							hide();
							var resarr=[];
							   resarr=data;
							   var count=1;
							  // console.log("data"+data);
							   res = resarr.split(",");								     
							       for (var i in res) {								        	 
							    	   if(res[i].indexOf("successfully")!=-1)
								     	{
                                         $("#resultDiv").html("The record has been saved successfully.");
										   $('.error').removeClass('error');
										 document.forms['messageCreationForm'].reset();
										 $('.startDate').datepicker();
                                         $('.startDate').datepicker('setDate', 'today');
										  $('.newstartDate').datepicker();
                                         $('.newstartDate').datepicker('setDate', 'today');
								   		   } 								        	 
							    	   else{									        	 
								    	    var result=[];								    	    
										   result = res[i].split("-");;
										   if(result[2].indexOf("Invalid")>-1)
											   {  
											      if(count!=1)
											    	  {
											           resmsg=resmsg+"\n"+res[i];
											    	  }
											      else
											    	  {
											    	  resmsg=res[i];
											    	  }
											      ++count;
										         var itCount=parseInt(result[1]);										
										         table.rows[itCount].cells[1].children[0].className="error";
								               }										 
								        }
								         $("#errorDiv").html(resmsg);
                                        									 
								   }								   
						}
							
					});
				  	
		} 
 function checkNull(s){
	try{	
		if (s == null || s == undefined) {
		 s="";	
		}
		
	}catch (e) {
		 s="";
		//console.log(e);
	} 
	return s;
}
 function customEncodeURIComponent(str) {
	    return encodeURIComponent(str).replace(/'/g, '%60');
	}
	function saveMessage(){
		$('#feedbackDiv').html("");
		$('#resultDiv').html("");
		 $("#errorDiv").html("");
        $('.error').removeClass('error');		
		var submitFlag = "";
		var itemCount = $('#itemCount').val();
		//console.log("itemCount in Save message::"+itemCount);		
		var count = $('#itemAdd tr').length;
		count=count-1;
		var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), i, j, cells, customerId;
		var rCount = table.rows.length;
		var stTime=[];
		var edTime=[];
		var levelArr=[];
		var levelValueArr=[];
		var startDateArr=[];
		var endDateArr=[];
		var startTimeArr=[];
		var endTimeArr=[];
		var fullHourArr=[];
		var msgArr=[];
		
	    var rowvalidateresponse="";
	    
	  for (i = 1, j = rows.length; i < j; ++i) {
		    var sd;
			var ed;
		    var strTime="";
		    var edtTime="";
	    cells = rows[i].getElementsByTagName('td');
	    if (!cells.length) {
	        continue;
	    }
	    var selectlevel=table.rows[i].cells[0].children[0].value;
         if(selectlevel!="0")
	    	 levelArr.push(checkNull(table.rows[i].cells[0].children[0].value));	 
	    	
	    	else
	    		levelArr.push("")
	    levelValueArr.push(checkNull(table.rows[i].cells[1].children[0].value));
	     sd=table.rows[i].cells[2].children[0].value;
	    
	    // alert("date::"+sd);	    
		startDateArr.push(checkNull(sd));	 
        var startTime=table.rows[i].cells[3].children[0].value;
		 startTime=checkNull(startTime);
		if(0 == startTime.length)
		{
			startTimeArr.push(strTime);	    
		}
       else{
		        stTime =startTime.match(/.{1,2}(?=(.{2})+(?!.))|.{1,2}$/g);
		         if(typeof  stTime[1] ==="undefined")
					{
					stTime[1]="00";
					}
				strTime=stTime[0]+":"+stTime[1];
				  //alert("strTime:"+strTime);
				 startTimeArr.push(strTime);
	      }
	    ed=table.rows[i].cells[4].children[0].value;
	    endDateArr.push(checkNull(ed));
		var endTime=table.rows[i].cells[5].children[0].value;		
		 endTime=checkNull(endTime);
		if(0 === endTime.length)
		{
			endTimeArr.push(edtTime);	  
		}
		else
		{
			 edTime =endTime.match(/.{1,2}(?=(.{2})+(?!.))|.{1,2}$/g);
			 if(typeof  edTime[1] ==="undefined")
				{
				edTime[1]="00";
				}
			edtTime=edTime[0]+":"+edTime[1];
			  //alert("strTime:"+strTime);
			endTimeArr.push(edtTime);
		}
	    if(table.rows[i].cells[6].children[0].value=="on")
	    	{
	    	var fh="false";
	    	}
	    else
			  fh="true";
	    fullHourArr.push(checkNull(fh));
	    msgArr.push(customEncodeURIComponent(checkNull(table.rows[i].cells[7].children[0].value)));
	      }
       
	  rowvalidateresponse=validateRow(levelArr);
	  
	  if(rowvalidateresponse=="true")
		 {
		  for (i=0; i<levelArr.length; i++)
		    {	
	
		    var level=levelArr[i];
			var levelValue = levelValueArr[i];
			var startDate=startDateArr[i];
			var endDate =endDateArr[i];
	        var startTime =startTimeArr[i];
			var endTime =endTimeArr[i];			
		    var msg =msgArr[i];		
			var rowindex=i+1;
		    //var sd=table.rows[rowindex].cells[2].children[0].id.replace('Div','');
		    //var ed=table.rows[rowindex].cells[4].children[0].id.replace('Div','');
			   table.rows[rowindex].cells[1].children[0].className="";
		       table.rows[rowindex].cells[3].children[0].className="";
			   table.rows[rowindex].cells[5].children[0].className="";
			   table.rows[rowindex].cells[7].children[0].className="";
			   //table.rows[rowindex].cells[2].children[0].className="";
			   //table.rows[rowindex].cells[4].children[0].className="";
			    		
			  	if(level!=""){
					  if((startDate=="" || endDate=="") ||(startTime=="" || endTime=="") ||(msg=="")||(levelValue=="")){
							submitFlag = "N";
                             if(levelValue=="")
								{
								 table.rows[rowindex].cells[1].children[0].className="error";					
								}							
							if(startDate=="")
								{
								table.rows[rowindex].cells[2].children[0].className="datePicker error";	
								}
							
							if(startTime=="")
							{
							 table.rows[rowindex].cells[3].children[0].value;
							 table.rows[rowindex].cells[3].children[0].className="error";
							}
							 if(endDate=="")	
							{
					       table.rows[rowindex].cells[4].children[0].className="datePicker error";
							}
					      if(endTime=="")
						   {
						     table.rows[rowindex].cells[5].children[0].className="error";						   
						    }
							if(msg=="")
								{
								 table.rows[rowindex].cells[7].children[0].className="error";						
								}
							$('#feedbackDiv').html("Please enter all fields before saving the record.");	
							return ;
			           }
				}
			  if(level=="POSTAL")
				{
				  var numbers = /^[0-9]+$/;
				   if(levelValue.length!=5)
					   {
					   submitFlag = "N";
					   table.rows[rowindex].cells[1].children[0].className="error";
					   $('#feedbackDiv').html("Please enter 5 digit for Postal ");
						return ;
					   }
				   else if(!numbers.test(levelValue))
					   {
					   submitFlag = "N";
					   table.rows[rowindex].cells[1].children[0].className="error";
					   $('#feedbackDiv').html("Please enter only numbers for PostalCode ");
						return ;					   
					   }
					   
				}
			submitFlag="Y";
		    }
		 } 
	    else
		    {
		     submitFlag="N";	
		     $('#feedbackDiv').html("Please enter atleast one row values before saving the record.");
		     }
	  
		if(submitFlag == 'Y'){
			   submitMessage(levelArr,levelValueArr,startDateArr,startTimeArr,endDateArr,endTimeArr,fullHourArr,msgArr);
		
		}
	}
	function validateRow(levelArr)
	{
		var rowvalidate="false";
		 for (var i in levelArr)
		{
			 if(levelArr[i]!="")
				 {
				    rowvalidate="true";				    
				    break;
				 }	 
			 
		}
		 return rowvalidate;
		
	}
	function itemDescClick(){
		$(".itemDescC").each(function () {
		  addtoolTip1($(this));	
		});
	}
	function addMessage(){
			//alert("add accesssory");
        $('.newstartDate').toggleClass("startDate");
        $('.newstartDate').removeClass('newstartDate');			
        var tbcount = $('#itemAdd tr').length;
		tbcount=tbcount-1;
		$('#itemCount').val(tbcount);  			
		var count = $('#itemCount').val();
		var itemCount = count++;	
		var newRow = "<tr id='msgRow"+itemCount+"'>"+
	 " <td><SELECT id='level"+itemCount+"' name='level"+itemCount+"' style='width:135px;'><option value='0' selected>Please Select Level</option><option value='REGION' >Region</option><option value='BRANCH'>Branch</option>	<option value='SVCTEAM'>Team</option> <option value='POSTAL' >Postal</option><option value='PARTY_SITE_NUMBER'>Party Site Number</option><option value='ACCOUNT_NUMBER' >Account Number</option><option value='SERIAL_NUMBER'>Serial Number</option><option value='MODEL'>Model</option>			</select></td>	"
	 +"<td nowrap><input type='text' style='width:145px;' name='levelValue"+itemCount+"' id='levelValue"+itemCount+"' value='' >&nbsp;<img src='<%=imgSrc%>' alt='' height='21' " + 
	 " onClick=openLevelLov('levelSearchLovDiv','1','10',"+itemCount+",'F',this)></td>"+	"<td><input type='text'  class='datePicker newstartDate' value=''  size=9 onChange='compareBaseUnitStartdate("+itemCount+",this)' style='width:80px;'></td>"+
	 "<td  style='padding-left:10px;'><input type='text' name='startTime"+itemCount+"' id='startTime"+itemCount+"' value=''  maxlength=4 onChange='validateStartTime(this)' style='width:55px;'></td>"+
	 "<td><input type='text'  class='datePicker newEndDate' value=''  size=9 onChange='compareBaseUnitEnddate("+itemCount+",this)' style='width:80px;'></td>"+
	 "<td  style='padding-left:10px;'><input type='text' name='endTime"+itemCount+"' id='endTime"+itemCount+"' value=''  maxlength=4 onChange='validateEndTime(this)' style='width:55px;'></td>"+
				"<td style='padding-left:15px;'><input type='checkbox' id='fullhour"+itemCount+"' name='fullhour"+itemCount+"' onclick='$(this).attr('value', this.checked ? true : false)' style='margin-left:15px;border:0px;'></td>"+
				"<td><input type='text' id='msg"+itemCount+"' name='msg"+itemCount+"' value='' class='itemDescC' style='width:380px'></td>"+
				"<td style='text-align:center' id='delete"+itemCount+"' name='delete"+itemCount+"' class='del'><img src='<%=imgSource%>' alt='' height=18 onClick='delRow("+itemCount+")'></td>"+
				"<\/tr>";				
				$('#itemCount').val(count);
				$('#itemAdd').append(newRow); 
				$('.newstartDate').datepicker();
                $('.newstartDate').datepicker('setDate', 'today');
                $('.newEndDate').datepicker();
				itemDescClick();
	}

		
function msieversion() {

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer, return version number
        return parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
    else                 // If another browser, return 0
    	return 'otherbrowser';

    return false;
  }

	
	
function clickClear(){
	$('#feedbackDiv').html("");		
	$('#resultDiv').html("");
    $("#errorDiv").html("");	
    $('.error').removeClass('error');	
		document.forms['messageCreationForm'].reset();
		//document.forms['catalogCreationForm'].submit();	
	}

function clickBack(){
	var url = 'canonE307SrvcMsgSearch.jsp';
		document.forms['messageCreationForm'].action = url;
		document.forms['messageCreationForm'].submit();	
	}
	
function compareBaseUnitStartdate(iVal,x){
	//alert("compareBaseUnitStartdate");
    var tDate = "";
	var fDate = "";
		 $('#feedbackDiv').html("");
		 $("#resultDiv").html("");	
		 $("#errorDiv").html("");
         var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), i, j, cells;		 
		 var rowcount=x.parentNode.parentNode.rowIndex;   				 
		 tDate = table.rows[rowcount].cells[4].children[0].value;
		 fDate= table.rows[rowcount].cells[2].children[0].value;
        
		 var sd= table.rows[rowcount].cells[2].children[0].value;
		 table.rows[rowcount].cells[2].children[0].className="";
		 
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
							table.rows[rowcount].cells[2].children[0].value="";
				            table.rows[rowcount].cells[2].children[0].className="error";
						$('#feedbackDiv').html("Please select start date less than end date: " + tDate);									
						return false;
					}
			}
		}
	}
	else if(res==false)
		{
		table.rows[rowcount].cells[2].children[0].value="";
		table.rows[rowcount].cells[2].children[0].className="error";
		$('#feedbackDiv').html("Please enter valid start date with format mm//dd//yyyy");
		}
	}

</script>
<style type="text/css">

/* #confirmBox
{
    display: none;
    background-color: #eee;
    border-radius: 5px;
    border: 1px solid #aaa;
    position: absolute;
    width: 300px;
    left: 50%;
    margin-left: -150px;
    padding: 6px 8px 8px;
    box-sizing: border-box;
    text-align: center;
}
#confirmBox .button {
    background-color: #ccc;
    display: inline-block;
    border-radius: 3px;
    border: 1px solid #aaa;
    padding: 2px;
    text-align: center;
    width: 80px;
    cursor: pointer;
}
#confirmBox .button:hover
{
    background-color: #ddd;
}
#confirmBox .message
{
    text-align: left;
    margin-bottom: 8px;
} */

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
	text-decoration:none;
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
.close {
  position: absolute;
  right: 32px;
	top: 80px;
  width: 32px;
  height: 32px;

   
}
.close:hover {
  opacity: 1;
}
.close:before, .close:after {
  position: absolute;
  left: -100px;
  content: ' ';
  height: 13px;
  width: 2px;
  background-color:red;
}
.close:before {
  transform: rotate(45deg);
}
.close:after {
  transform: rotate(-45deg);
}
.error
{
border-color:red;
}
.top_bar{
background-color: #FFFFFF;
color: #336699;
font-size: 18;
font-weight: bolder;
height: 26px;
}
.ascctablecell
{
    background-color: #aaaaaa;
	font-size: 14px;
	text-align: center;
	color: white;
	height: 25px;
	font-weight: bold

}
a {
	color: #0000FF;
}
#itemAdd
{
  width: 100%;
} */
 
</style>