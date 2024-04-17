
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
}

function clearTT(){
	
	$("textarea").each(function (){
         var nm=$(this).attr("name");
         if(nm="scratchPad"){
        		$(this).html("");
        		$(this).val("");    	 
         }
	});
}
function closeDlg(){
	var dlgId="#dlg";
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
	
}