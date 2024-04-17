<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240411204558 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

<!--  For TCEPPS Start-->
<script type="text/javascript" src="./js/common/tcepps.js" charset="UTF-8"></script>
<%@page import="com.canon.usa.app.security.APPSecurityManager"%>
<%@page import="com.canon.usa.tcepps.security.TCEPPSSecurityManager"%>
<%@page import="com.canon.usa.tcepps.property.TCEPPSPropertyManager"%>
<%@page import="business.servlet.NWAL2020.NWAL2020BMsg" %>
<%@page import="parts.common.EZDSystemEnv" %>
<%
	int index = 0;
	NWAL2020BMsg scrnMsg 	= (NWAL2020BMsg)databean.getEZDBMsg();
	long tceppsTokenExp = Long.parseLong(scrnMsg.xxSrTsTxt_T.getValue());
	String tceppsApp	= scrnMsg.appNm_T.getValue();
	String tceppsToken	= (tceppsTokenExp > 0) ? TCEPPSSecurityManager.getInstance().generateToken(tceppsTokenExp) : "";
	String tceppsSign	= (tceppsTokenExp > 0) ? TCEPPSSecurityManager.getInstance().generateSign(tceppsToken, tceppsApp) : "";
	String tceppsUrl	= TCEPPSPropertyManager.getInstance().getTceppsUrl();
	String tceppsEncryptedRequest		= scrnMsg.xxGenlFldAreaTxt_T1.getValue();
	String tceppsEncryptedRequestkey	= scrnMsg.xxGenlFldAreaTxt_T2.getValue();
	String appToken						= scrnMsg.xxGenlFldAreaTxt_T3.getValue();
	String bgColor                      = EZDSystemEnv.getString("S21.mode.color");
	bgColor                             = (null == bgColor || 0 == bgColor.length()) ? "#FAFAFA" : bgColor;
	String enableNewCrCardFlag                 = "true";
	String appTokenName = APPSecurityManager.APP_TOKEN;
	session.setAttribute(appTokenName, appToken);

%>

<script>
s21TceppsUtil = {
	orgSendServer : null, // original sendServer function
	command : "",     // command name for sendServer
	tceppsCardFormPage : "", // [tceppsUrl]/jsp/creditCardFormPage.jsp
	tceppsCardFromIframe : "creditCardFormIFrame",
	ParamMap : {"tceppsEncryptedResponse": "tceppsEncryptedResponse", "tceppsEncryptedResponseKey" : "tceppsEncryptedResponseKey"},

	// setting hook Function before sendServer
	// hookEvtNames : hash (hookEventName)
	hookSendServer : function(hookEvtNames){
		if(!hookEvtNames) return;
		s21TceppsUtil.orgSendServer = sendServer;
		sendServer = function(param, num){
			if(num) s21TceppsUtil.number = num;
			var commandName = param;
			if(param.name != undefined){
				commandName = param.name;
			}
			if(!(commandName in hookEvtNames)){
				s21TceppsUtil.orgSendServer(param, num);
			}else{
				//alert(commandName);
				hookEvtNames[commandName]();
				s21TceppsUtil.command = param;
			}
		}
	},
	// return original "sendServer" Function
	postSendServer : function(){
		if(s21TceppsUtil.orgSendServer){
			try{
				s21TceppsUtil.orgSendServer(s21TceppsUtil.command, s21TceppsUtil.number);
			}catch(e){
				alert(e);
			}
			sendServer = s21TceppsUtil.orgSendServer;
			s21TceppsUtil.orgSendServer = null;
		}
	},
	// add TCEPPS Response to Main Form
	addTceppsResponseToForm : function(parameters){
		var tceppsResponce = document.getElementById("tceppsResponceForm");
		if(tceppsResponce){
			tceppsResponce.parentNode.removeChild(tceppsResponce);
		}
		tceppsResponce = document.createElement('div');
		tceppsResponce.style.display = "none";
		tceppsResponce.id = "tceppsResponceForm";

		try{
			for(key in parameters){
				if(key in s21TceppsUtil.ParamMap){
					var elm = document.createElement("input");
					elm.type = "hidden";
					elm.id = s21TceppsUtil.ParamMap[key];
					elm.name = s21TceppsUtil.ParamMap[key];
					elm.value = parameters[key];
					tceppsResponce.appendChild(elm);

				}
			}
			document.getElementsByName("mainForm")[0].appendChild(tceppsResponce);
		}catch(e){
			alert(e);
		}
	},
	// set CreditCardFrom to Iframe
	getIframePage : function(){
		var tcepps  = document.forms["tcepps"];
		tcepps.action = s21TceppsUtil.tceppsCardFormPage;
		tcepps.target = s21TceppsUtil.tceppsCardFromIframe;
		getCreditCardForm(
				tcepps
		);
	},
	// create element for cross browser
	createNamedElement : function(tag, name) {
		var isIE =/*@cc_on!@*/false;
		var element;
		if (name) {
			element = (isIE)? document.createElement('<' + tag + ' name="' + name + '">'): document.createElement(tag);
			element.name = name;
		} else {
			element = document.createElement(tag);
		}
		return element;
	},
	// append hidden input for cross browser
	appendNamedHiddenInput : function(form, name, value) {
		var hiddenInput = s21TceppsUtil.createNamedElement("input", name);
		hiddenInput.type = "hidden";
		hiddenInput.value = value;
		form.appendChild(hiddenInput);
	}
}

var forceSubmitToTCEPPS = false; // TODO Add logic to separate "profileAdd" or "newOrder_reversal"
var authorizeFunc = function(){
	if(document.getElementsByName("sellToCustCd")[0].value == ""){
		return;
	}

	var msgCd  = document.getElementById("msgCode");
	var msgFld = document.getElementById("msgfield");
	msgCd.innerText = "";
	msgFld.innerText = "";

	var tceppsProxy = document.forms["tceppsProxy"];
	tceppsProxy.serviceOperation.value = "profileAdd";

	invokeTceppsProxy(tceppsProxy);
}

// clear iFrame form
function clearForm (){
	var tceppsProxy = document.forms["tceppsProxy"];
	tceppsProxy.serviceOperation.value = "clearCreditCard";
	invokeTceppsProxy(tceppsProxy);
}
// put balloonTip for addressValidate
function putAddressValidate(){
	var tceppsProxy = document.forms["tceppsProxy"];
	tceppsProxy.serviceOperation.value = "addressValidate";
	invokeTceppsProxy(tceppsProxy);
}


var recordTceppsMessage = function(message){
	var tceppsErr = document.getElementById("tceppsErrForm");
	if(tceppsErr){
		tceppsErr.parentNode.removeChild(tceppsErr);
	}
	tceppsErr = document.createElement('div');
	tceppsErr.style.display = "none";
	tceppsErr.id = "tceppsErrForm";
	try{
		var elm = document.createElement("input");
		elm.type = "hidden";
		elm.id = "xxSrErrDescTxt_F";
		elm.name = "xxSrErrDescTxt_F";
		elm.value = message.substring(0, 99);
		tceppsErr.appendChild(elm);
		document.getElementsByName("mainForm")[0].appendChild(tceppsErr);
		s21TceppsUtil.orgSendServer("PA_RecordLog");
	}catch(e){
		alert(e);
	}
}

// This function is called when TCEPPS response success
function tceppsResponseSuccess(parameters) {
	if (parameters["tcepps.serviceOperation"] == "cancel") return;
	s21TceppsUtil.addTceppsResponseToForm(parameters);
	if(s21TceppsUtil.orgSendServer){
		s21TceppsUtil.postSendServer();
	}
}

//This function is called when TCEPPS response error
function tceppsResponseError(errorMessage) {
	if(errorMessage != "Failed to invoke TCEPPS Service." && !document.getElementById("tceppsProxyIframe")){
		// called by tceppsResponse and tceppsProxyIframe is not defined.
		var proxyIframe = document.createElement("form");
		proxyIframe.id = "tceppsProxyIframe";
		document.body.appendChild(proxyIframe);
	}
	
	if(errorMessage == "PaymentechNewOrderExecutionFailureException : Given information for the credit card is not valid."){ // TODO Add logic to addressValidate
		//forceSubmitToTCEPPS = true; // TODO Add logic to separate "profileAdd" or "newOrder_reversal"
		//setTimeout(function(){putAddressValidate()}, 20);
		errorMessage = "Given information for the credit card is not valid.";
	}else if(errorMessage == "SOAPFaultException : 839 Error validating card/account number checksum."){
		errorMessage = "Credit Card Account Number is invalid.";
	}
	//var msgCd  = document.getElementById("msgCode");
	//var msgFld = document.getElementById("msgfield");
	//msgCd.className = "cMsgI";
	//msgFld.className = "cMsgI";
	//msgCd.innerText = ""; // set message code here, if necessary
	//msgFld.innerText = errorMessage;
	// recordMessageToLog(errorMessage); // if necessary, please mount function
	if(null != errorMessage) {
		document.getElementsByName("xxRqstNumSrchTxt_T")[0].value = errorMessage;
	}

	s21TceppsUtil.orgSendServer("OnBlur_CreateCrditCard");
}

// Generate tcepps form
function generateTceppsForm() {
	var tcepps  = document.forms["tcepps"];
	if(!tcepps){
		var form = s21TceppsUtil.createNamedElement("form", "tcepps");
		form.method = "post";

		s21TceppsUtil.appendNamedHiddenInput(form, "app", "<%= tceppsApp %>");
		s21TceppsUtil.appendNamedHiddenInput(form, "token", "<%= tceppsToken %>");
		s21TceppsUtil.appendNamedHiddenInput(form, "sign", "<%= tceppsSign %>");
		s21TceppsUtil.appendNamedHiddenInput(form, "serviceOperation", "");
		s21TceppsUtil.appendNamedHiddenInput(form, "encryptedRequest", "<%= tceppsEncryptedRequest %>");
		s21TceppsUtil.appendNamedHiddenInput(form, "encryptedRequestKey", "<%= tceppsEncryptedRequestkey %>");
		s21TceppsUtil.appendNamedHiddenInput(form, "background-color", "<%= bgColor%>");
		s21TceppsUtil.appendNamedHiddenInput(form, "enableNewCrCardFlag", <%= enableNewCrCardFlag%>);
		s21TceppsUtil.appendNamedHiddenInput(form, "<%= appTokenName %>", "<%= appToken %>");

		document.body.appendChild(form);
	}
}
//Generate tceppsProxy Form
function generateTceppsProxy() {
	var tceppsProxy = document.forms["tceppsProxy"];
	if(!tceppsProxy){
		var form = s21TceppsUtil.createNamedElement("form", "tceppsProxy");
		form.action = "<%= tceppsUrl %>/jsp/creditCardFormProxy.jsp?tceppsApp=<%= tceppsApp %>";
		s21TceppsUtil.appendNamedHiddenInput(form, "serviceOperation", "");
		s21TceppsUtil.appendNamedHiddenInput(form, "addProfile", "");
		document.body.appendChild(form);
	}
}

(function(){
	// For NWAL2020 Credit tab
	var isCreditEntryAllowed = "<%= tceppsTokenExp > 0 %>";
	if (isCreditEntryAllowed != "true") return;

	var orgOnload = window.onload;
	window.onload = function(){
		if(orgOnload){
			orgOnload();
		}
		generateTceppsForm();
		generateTceppsProxy();
		var hookEvtNames = {"InsertNewCard" : authorizeFunc}; // assosiate EventName : hookFunction
		if(hookEvtNames){
			s21TceppsUtil.hookSendServer(hookEvtNames);
		}
		s21TceppsUtil.tceppsCardFormPage = "<%= tceppsUrl %>/jsp/creditCardFormPage.jsp?tceppsApp=<%= tceppsApp %>";
		s21TceppsUtil.getIframePage();
	}
})();
</script>
<!--  For TCEPPS End-->
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NWAL2020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Credit Card Entry">

<center>
	<table width="90%">
		<tr>
			<td valign="top">
				<div style="word-break:break-all">
					<table border="0" cellpadding="0" cellspacing="0" style="margin-top:200px;">
						<tr>
							<td style="word-break:keep-all;">
								<fieldset>
									<table style="table-layout:fixed;" height="152">
										<tr>
											<td>Account Code:<ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="TRX_N5" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
										</tr>
										<tr>
											<td><ezf:inputHidden name="xxRqstNumSrchTxt_T" ezfName="xxRqstNumSrchTxt_T" /></td>
										</tr>
										<tr>
											<!--  credit card iframe -->
											<td rowspan="7">
												<div id="ccFrom">
													<c:set var="isCreditEntryAllowed" scope="page" value="<%= (0 < tceppsTokenExp) %>" />
													<c:if test="${isCreditEntryAllowed}" >
														<iframe name="creditCardFormIFrame" width="580px" height="115px" scrolling="no" frameBorder="0">
														</iframe>
													</c:if>
												</div>
											</td>
											<!--  credit card iframe -->
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td align="right">
								<ezf:inputButton name="InsertNewCard" value="Insert New Card" htmlClass="pBtn10" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
