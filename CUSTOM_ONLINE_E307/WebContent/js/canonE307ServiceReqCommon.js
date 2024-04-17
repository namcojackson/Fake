function opnSCPad(){
	var scDivId="#sPadDiv";
	var scId="#sp";
	showTT($(scId),$(scDivId).html());
}

function showTT(element, text) {
	   var offset = element.offset();
	   $('#toolTip').html("");    
	   var str= '<br><a href="#" onclick="hideScTT();" style="float:right;margin-top:2px;" class="btn">Close</a><a href="#" onclick="clearTT();" style="float:right;margin:2px 5px 0px 5px;" class="btn">Clear</a>';
	    
	    $('#toolTip')
	    .css({
	        'top'  : (offset.top+25) + 'px',
	        'left' : (offset.left-245)+ 'px' 
	   }).html(text  + "  " +str).show();
	}

function hideScTT() {
		$('#sPadDiv textarea').html($('#toolTip textarea').val());
		$('#toolTip').hide();
		saveSCPad("add");
}

function clearTT(){
	
	$("textarea").each(function (){
         var nm=$(this).attr("name");
         if(nm="scratchPad"){
        		$(this).html("");
        		$(this).val("");    	 
         }
	});
	
	saveSCPad("clear");
	
}
	
function saveSCPad(act){
	
	$("#spAction").val(act);
	var qryStr=$("#spForm").serialize();
	
	
	$.ajax( {
		url : "canonE307ServiceReqSpUtil.jsp",
		cache : false,
		type : "POST",
		data : qryStr,
		success : function(data) {
 	         }
	});
}

function setCCTitle(){
	try {
		
		$(document).prop("title","Enter Credit Card");
		
	} catch (e) {

	}
}

function revertCCTitle(){
	try {
		$(document).prop("title",getPageTitle());		
	} catch (e) {

	}	
}
