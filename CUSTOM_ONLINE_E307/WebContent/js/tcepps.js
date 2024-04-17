var tceppsInvocationError = false;
var _ccDeferred;
var _ccModelName="#divEnterCC";

function getCreditCardForm(
	tcepps
) {
	addTceppsActionApp(tcepps);

	var form = 
		generateTceppsProxyForm(
			tcepps.action
		,	tcepps.target
		,	tcepps.method
		)
	;
	appendHiddenInputs(form, tcepps);
	
	submitTceppsProxyForm(form, false);	
}


function invokeTceppsProxy(
	tceppsProxy
) {
	
	addTceppsActionApp(tceppsProxy);
	
	var form = 
		generateTceppsProxyForm(
			tceppsProxy.action
		,	"tceppsProxyIFrame"
		,	"post"
		)
	;

	appendHiddenInputs(form, tceppsProxy);
	
	submitTceppsProxyForm(form, false);
}

function addTceppsActionApp(tcepps) {
	if (tcepps.app) {
		addTceppsActionParameter(tcepps, "tceppsApp", tcepps.app.value);
	}
}

function addTceppsActionParameter(tcepps, parameterName, parameterValue) {
	if (tcepps.action.indexOf(parameterName + "=") < 0) {
		if (tcepps.action.indexOf("?") < 0) {
			tcepps.action += "?";
		} else {
			tcepps.action += "&";
		}
		tcepps.action += (parameterName + "=" + parameterValue);
	}	
}

function submitCreditCardForm(queryString) {	
	this.tceppsInvocationError = false;
	if (queryString.length > 0) {
    	var parameters = parseQueryString(queryString);
    	if (top.creditCardFormIFrame.invokeTceppsServiceOperation) {
    		top.creditCardFormIFrame.invokeTceppsServiceOperation(parameters);
    	}
	}
}


function invokeTceppsResponseError(tcepps, errorMessage) {
	var form = 
		generateTceppsProxyForm(
			tcepps.proxyUrl.value + "?status=ERROR&errorMessage=" + errorMessage
		,	"tceppsProxyIFrame"
		,	"post"
		)
	;					
	submitTceppsProxyForm(form, false);	
}


function getTceppsProxyForm(
	tcepps
) {
	
	addTceppsActionApp(tcepps);
	
	var form = generateTceppsProxyForm(
		tcepps.action
	, 	"tceppsProxyIFrame"
	,	"post"
	);

	appendHiddenInput(form, "tcepps.app"					, tcepps.app.value);
	appendHiddenInput(form, "tcepps.token"					, tcepps.token.value);
	appendHiddenInput(form, "tcepps.sign"					, tcepps.sign.value);
	appendHiddenInput(form, "tcepps.serviceOperation"		, tcepps.serviceOperation.value);
	
	return form;
}

function generateTceppsProxyForm(
	action
,	target
,	method
){
	generateTceppsProxyIFrame();

	var form = document.createElement("form");
	form.action = action;
	form.target = target;
	form.method = method;
	
	return form;
}

function generateTceppsProxyIFrame() {
	if(document.getElementById("tceppsProxyIFrame")) return;
	
	var tceppsProxyIFrame = document.createElement("iframe");
	document.body.appendChild(tceppsProxyIFrame);
	tceppsProxyIFrame.id 					= "tceppsProxyIFrame";
	tceppsProxyIFrame.name 					= "tceppsProxyIFrame";
	tceppsProxyIFrame.width					= 10;
	tceppsProxyIFrame.height				= 10;	
	tceppsProxyIFrame.contentWindow.name 	= "tceppsProxyIFrame";
	tceppsProxyIFrame.style.display 		= "none";
	addOnLoadEventListener(tceppsProxyIFrame);
	return tceppsProxyIFrame;
}

function addOnLoadEventListener(tceppsProxyIFrame) {
	if (tceppsProxyIFrame.addEventListener) {
		tceppsProxyIFrame.addEventListener("load", tceppsProxyIFrameOnLoad(), true);
	} else {
		tceppsProxyIFrame.attachEvent("onload", tceppsProxyIFrameOnLoad);
	}	
}

function tceppsProxyIFrameOnLoad() {
	//document.body.removeChild(document.getElementById("tceppsProxyIFrame"));
	setTimeout(checkTceppsInvocationError, 500);
}

function checkTceppsInvocationError() {
	if (this.tceppsInvocationError) {
		if (this.tceppsResponseError) {
			this.tceppsResponseError("Failed to invoke TCEPPS Service.");
		} else {
			alert("Failed to invoke TCEPPS Service.");
		}
	}
}

function appendHiddenInputs(targetForm, sourceForm) {
	for (var i = 0; i < sourceForm.elements.length; i++) {
		var sourceFormElement = sourceForm.elements[i];
		appendHiddenInput(targetForm, sourceForm.name + "." + sourceFormElement.name, sourceFormElement.value);
	}
}

function appendHiddenInput(form, name, value) {
	var hiddenInput = document.createElement("input");
	hiddenInput.type = "hidden";
	hiddenInput.name = name;
	hiddenInput.value = value;
	form.appendChild(hiddenInput);	
}


function submitTceppsProxyForm(tceppsProxyForm, checkInvocationError) {
	this.tceppsInvocationError = checkInvocationError;
	document.body.appendChild(tceppsProxyForm);	
	tceppsProxyForm.submit();
	document.body.removeChild(tceppsProxyForm);
}

function getParentUrl() {
	var url = window.location.href;
	return url.substr(0, url.lastIndexOf("/") + 1);			
}

function tceppsResponseSuccess(parameters) {
	var operation=parameters["tcepps.serviceOperation"];
	if (operation != "cancel") {
			if (parameters["appToken"]) {
				creditCard.appToken.value = parameters["appToken"];
			}
		
			if (parameters["tceppsEncryptedResponse"]) {
				creditCard.tceppsEncryptedResponse.value = parameters["tceppsEncryptedResponse"];
			}
			if (parameters["tceppsEncryptedResponseKey"]) {
				creditCard.tceppsEncryptedResponseKey.value = parameters["tceppsEncryptedResponseKey"];
			}
			if(operation=="profileAdd"){
				//{ppa.procStatusMessage=Profile Request Processed, ppa.cardBrand=VI, ppa.ccAccountNum=1111, ppa.ccExp=201612, ppa.procStatus=0, ppa.customerRefNum=77891956}
				$.creditCard($.extend({}, {"action":"close"}, parameters));
			}else if( operation=="newOrder"){
				// {pno.respDateTime=20160921152243, pno.customerRefNum=77893072, pno.respCode=00, pno.txRefNum=57E2DE0234DD2145C9EEDD04CBA0B94F0A25541E, pno.cardBrand=VI, pno.procStatus=0, pno.procStatusMessage=Approved, pno.profileProcStatus=0, pno.avsRespCode=3 , pno.approvalStatus=1}
				$(_ccModelName)[0].innerHTML="";
				if((parameters["procStatus"]!=="0" || parameters["approvalStatus"]!=="1" ) && parameters["txRefNum"]){
					delete parameters["txRefNum"];
				}
				delete parameters["x"];
				$.creditCard($.extend({}, {"action":"received"}, parameters));
			}
		}
}

function tceppsResponseError(parameters) {
	var operation=parameters["tcepps.serviceOperation"];
	if(operation=="profileAdd"){
		$.creditCard($.extend({}, {"action":"profie_add_error"}, parameters));
	}else if( operation=="newOrder"){
		$(_ccModelName)[0].innerHTML="";
		delete parameters["x"];
		$.creditCard($.extend({}, {"action":"new_order_error"}, parameters));
	}else{
		alert("Invalid operation "+operation);
		ccDeferred.reject(parameters);
	}
	
}

function cleanUpTceppsProxyIFrame(){
	var iframe=document.getElementById("tceppsProxyIFrame");
	while(iframe){
		document.body.removeChild(iframe);
		iframe=document.getElementById("tceppsProxyIFrame");
	}
}

function openCCDialog(title,url,height,width, options)
{
	$(_ccModelName).dialog({
		cache: false,
		height: height,
		title: title, 
		modal: true ,
		zIndex:1005,
	    resizable: false,
		autoOpen: false,
		width: width,
		closeOnEscape: true
		,buttons: {
		"Submit": function() {
			var tceppsProxy	= document.forms["tceppsProxy"];
		    invokeTceppsProxy(tceppsProxy);
		},
		"Close": function() {
			revertCCTitle();
			$(this).dialog("close");
			$(this).dialog("destroy");
			$(_ccModelName).html("");
			$(_ccModelName).trigger("closed");
		    cleanUpTceppsProxyIFrame();
		}}
	});
	
	delete options["title"];
	delete options["height"];
	delete options["width"];

	$.ajax({
		url: url,
		data:options,
		cache: false,
		success: function(data) {
			$(_ccModelName).html("");
			$(_ccModelName).html(data);
			var tcepps= document.forms["tcepps"];
			getCreditCardForm(tcepps);
		    cleanUpTceppsProxyIFrame();
	        }             
	});
    $( _ccModelName ).dialog("open")
    	.dialog("widget")
    	.find(".ui-dialog-buttonset .ui-button-text").addClass("btn").end()
    	.find(".ui-dialog-title").css({"float":"none"}).end()
    	.find(".ui-dialog-content").css({"overflow":"hidden","border":"none"});
	$(".ui-dialog-titlebar").addClass("hd");
	$(".ui-dialog").css({"top":"330px","float":"none"});
	$('.ui-dialog-titlebar-close').hide();
	setTimeout(function (){
		 $( ".ui-dialog-buttonset button").addClass("btn");
		},1000);
}

function postCCAuth(url,options)
{
	
	$.ajax({
		url: url,
		data:options,
		cache: false,
		success: function(data) {
			$(_ccModelName).html(data).hide();
			var tcepps= document.forms["tcepps"];
			getCreditCardForm(tcepps);
		    cleanUpTceppsProxyIFrame();
		    
		    setTimeout(function(){
		    	
		    	var tcepps		= document.forms["tcepps"];
		    	var tceppsProxy	= document.forms["tceppsProxy"];
		    	
		    	tcepps.serviceOperation.value = 'newOrder';
		    	tceppsProxy.serviceOperation.value = 'newOrder';
		    	invokeTceppsProxy(tceppsProxy);

		    }, 1000);
		    
	        }             
	});
	
}


$.creditCard = function(url,options) {
	// If url is an object, simulate pre-1.5 signature
	if ( typeof url === "object" ) {
		options = url;
		url = undefined;
	}

	// Force options to be an object
	options = options || {};
	
	options= $.extend( {}, $.creditCard.defaults, options );
	 
	if(options.action=="open"){
		_ccDeferred=jQuery.Deferred();
		
		$.extend(options,{"secure":window.location.protocol == "https:"});
		openCCDialog(options.title,url,options.height,options.width, options);
		return _ccDeferred.promise();
		
	}else if(options.action=="close"){
		
		$(_ccModelName).dialog("close");
		$(_ccModelName).dialog("destroy");
		$(_ccModelName).html("");
		$(_ccModelName).trigger("closed");
		cleanUpTceppsProxyIFrame();
		
		_ccDeferred.resolve(options);
		
	}else if(options.action=="auth"){
		_ccDeferred=jQuery.Deferred();
		if(options.profileId){
			$.extend(options,{"secure":window.location.protocol == "https:"});
			postCCAuth(url, options);
		}else{
			_ccDeferred.resolve({"error":"missing profileId"});
		}
		return _ccDeferred.promise();
	}else if(options.action=="received"){
		_ccDeferred.resolve(options);
	}else if(options.action=="profie_add_error"){
		$(_ccModelName).dialog("close");
		$(_ccModelName).dialog("destroy");
		$(_ccModelName).html("");
		$(_ccModelName).trigger("closed");
		cleanUpTceppsProxyIFrame();
		
		if(options.show_tcepps_error){
			var msg= "TCEPPS communication error occurred";
			msg=options.errorMessage? msg+ ": "+options.errorMessage: msg;
			alert(msg);
		}
		_ccDeferred.reject(options);
	}else if(options.action=="new_order_error"){
		cleanUpTceppsProxyIFrame();
		
		if(options.show_tcepps_error){
			var msg= "TCEPPS communication error occurred";
			msg=options.errorMessage? msg+": "+options.errorMessage: msg;
			alert(msg);
		}
		_ccDeferred.reject(options);
	}
	
}

$.creditCard.defaults = {
	title:"Enter Credit Card",
	width:400,
	height:250,
	show_tcepps_error:true
};

