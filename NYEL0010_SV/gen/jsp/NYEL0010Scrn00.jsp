<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161117151452 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NYEL0010Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

<%@ page import="business.servlet.NYEL0010.NYEL0010BMsg" %>
<%@ page import="business.servlet.NYEL0010.NYEL0010Bean" %>
<% NYEL0010BMsg bMsg = (NYEL0010BMsg)databean.getEZDBMsg(); %>
<% String customAppUrl_Ascc = EZDSystemEnv.getString("S21.extn.url.assc"); %>
<style type="text/css">
<!--
td.head1{font-size:10.0pt;font-family:'Arial',sans-serif;color:#929292;font-weight:bold;language:en-US;}
td.head2{font-size:9.0pt;font-family:'Arial',sans-serif;color:#929292;language:en-US;}
td.body1{font-size:11.0pt;font-family:'Arial',sans-serif;color:#AC0001;text-decoration:none;font-weight:bold;language:en-US;}
td.body2{font-size:8.0pt;font-family:'Arial',sans-serif;color:#929292;font-weight:bold;}
td.body3{font-size:8.0pt;font-family:'Arial',sans-serif;color:#929292;}
a.linkhead:link{color:#fff;text-decoration:none;}
a.linkhead:visited{color:#fff;text-decoration:none;}
a.linkhead:hover{color:#fc2202;}
a.linkhead:active{color:#F00;}a.linkbody:link{color:#000;text-decoration:none;}
a.linkbody:visited{color:#000;text-decoration:none;}
a.linkbody:hover{color:#fc2202;}a.linkbody:active{color:#F00;}
#allocation,#edi,#odercapture,#loantrial,#invoice,#creditdevit,#procurement,#reciving,#shipping,#inventry,#serial,#cashapplication,#collection,#merchandise,#organization,#partner,#salescondition,#codemaintenance,#orderfulfill,#exportdoc,#serviceparts,#cppd,#servicerepair,#return,#aje,#cost,#promotion,#sysmenu,#dispatch,#machine_asset,#contract_billing,#ascc{background:url('img/menu/spritedmenuicons.png');width:32px;height:34px;text-indent:-9999px;display:block;border-style:none;}
#retail_master{background:url('img/menu/retail_master.png');width:32px;height:34px;text-indent:-9999px;display:block;border-style:none;}
#order_capture{background:url('img/menu/order_capture.png');width:32px;height:34px;text-indent:-9999px;display:block;border-style:none;}
#install_billing{background:url('img/menu/install_billing.png');width:32px;height:34px;text-indent:-9999px;display:block;border-style:none;}
#return_asset{background:url('img/menu/return_asset.png');width:32px;height:34px;text-indent:-9999px;display:block;border-style:none;}
#setupmyprocess,#reporting,#oninq,#myprocess,#admin,#dwh,#workflow{background:url('img/menu/spritedheadicons.png?ts=20151209');text-indent:-9999px;display:block;}
#mimgbody,#mimgshadow,#mimgshadow,#mimgfooter,#mimginfoback,#mimglogo{background:url('img/menu/spritedmenuimages.png');text-indent:-9999px;display:block;}
#admin{background-position:0 0;width:27px;height:29px;}
#dwh{background-position:-29px 0;width:26px;height:29px;}
#myprocess{background-position:-56px 0;width:26px;height:29px;}
#oninq{background-position:-83px 0;width:26px;height:29px;}
#reporting{background-position:-111px 0;width:27px;height:29px;}
#setupmyprocess{background-position:-140px 0;width:24px;height:29px;}
#mimgbody{background-position:-1px -1px;width:1133px;height:2px;}
#mimgshadow{background-position:-1px -4px;width:1133px;height:13px;}
#mimgfooter{background-position:-1px -18px;width:1133px;height:5px;}
#mimginfoback{background-position:-669px -24px;width:445px;height:109px;}
#mimglogo{background-position:-12px -24px;width:75px;height:109px;}
#allocation{background-position:0 0;}
#edi{background-position:-32px 0;}
#odercapture{background-position:-704px 0;}
#loantrial{background-position:-800px 0;}
#sysmenu{background-position:-864px 0;}
#invoice{background-position:-640px 0;}
#creditdevit{background-position:-160px 0;}
#procurement{background-position:-320px 0;}
#reciving{background-position:-352px 0;}
#shipping{background-position:-512px 0;}
#inventry{background-position:-384px 0;}
#serial{background-position:-416px 0;}
#cashapplication{background-position:-576px 0;}
#collection{background-position:-544px 0;}
#merchandise{background-position:-736px 0;}
#organization{background-position:-672px 0;}
#partner{background-position:-96px 0;}
#salescondition{background-position:-768px 0;}
#codemaintenance{background-position:-608px 0;}
#orderfulfill{background-position:-192px 0;}
#exportdoc{background-position:-224px 0;}
#serviceparts{background-position:-448px 0;}
#cppd{background-position:-128px 0;}
#servicerepair{background-position:-480px 0;}
#return{background-position:-288px 0;}
#aje{background-position:-256px 0;}
#cost{background-position:-64px 0;}
#promotion{background-position:-832px 0;}
#dispatch{background-position:-960px 0;}
#machine_asset{background-position:-992px 0;}
#contract_billing{background-position:-224px 0;}
#workflow{background-position:-167px 0;width:26px;height:29px;}
#ascc{background-position:-1216px 0;}
#workflowBlue{background-position:-1152px 0;}
#allocation.gray{background-position:0 34px !important;}
#edi.gray{background-position:-32px 34px !important;}
#odercapture.gray{background-position:-704px 34px !important;}
#loantrial.gray{background-position:-800px 34px !important;}
#sysmenu.gray{background-position:-864px 34px !important;}
#invoice.gray{background-position:-640px 34px !important;}
#creditdevit.gray{background-position:-160px 34px !important;}
#procurement.gray{background-position:-320px 34px !important;}
#reciving.gray{background-position:-352px 34px !important;}
#shipping.gray{background-position:-512px 34px !important;}
#inventry.gray{background-position:-384px 34px !important;}
#serial.gray{background-position:-416px 34px !important;}
#cashapplication.gray{background-position:-576px 34px !important;}
#collection.gray{background-position:-544px 34px !important;}
#merchandise.gray{background-position:-736px 34px !important;}
#organization.gray{background-position:-672px 34px !important;}
#partner.gray{background-position:-96px 34px !important;}
#salescondition.gray{background-position:-768px 34px !important;}
#codemaintenance.gray{background-position:-608px 34px !important;}
#orderfulfill.gray{background-position:-192px 34px !important;}
#exportdoc.gray{background-position:-224px 34px !important;}
#serviceparts.gray{background-position:-448px 34px !important;}
#cppd.gray{background-position:-128px 34 !important;}
#servicerepair.gray{background-position:-480px 34px !important;}
#return.gray{background-position:-288px 34px !important;}
#aje.gray{background-position:-256px 34px !important;}
#cost.gray{background-position:-64px 34px !important;}
#promotion.gray{background-position:-832px 34px !important;}
#dispatch.gray{background-position:-960px 34px !important;}
#machine_asset.gray{background-position:-992px 34px !important;}
#contract_billing.gray{background-position:-224px 34px !important;}
#workflowBlue.gray{background-position:-1152px 34px !important;}
-->
</style>


<!-- Set Page ID  -->
<input type="hidden" name="pageID" value="NYEL0010Scrn00">
<!-- Set Page Name -->
<input type="hidden" name="pageName" value="<fmt:message key="i18n.NYEL0010Scrn00.title" bundle="${I18N_SCREEN_ID}">S21 Main Menu</fmt:message>">
<div style="position:absolute; left:0px; top:0px; width:1133px; height:121px;"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr class="mainHeader"><td width="10" height="109"><img src="img/spacer.gif" width="11" height="109"></td><td width="75" height="109" align="left"><span id="mimglogo">logo</span></td><td width="582" height="109" align="left" valign="top" bgcolor="#0f0f0f"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" width="8" height="15"></td><td><img src="img/spacer.gif" width="180" height="15"></td><td><img src="img/spacer.gif" width="180" height="15"></td><td><img src="img/spacer.gif" width="180" height="15"></td><td><img src="img/spacer.gif" width="26" height="15"></td></tr><tr><td><img src="img/spacer.gif" width="8" height="40"></td><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="24%" rowspan="2" align="center" valign="middle"><span id="myprocess"><fmt:message key="i18n.NYEL0010Scrn00.label.2" bundle="${I18N_SCREEN_ID}">myprocess</fmt:message></span></td>
<%
if (bMsg.bizAppId_K1.getValue().equals("")) {
%>
<td class="head1"><fmt:message key="i18n.NYEL0010Scrn00.label.3" bundle="${I18N_SCREEN_ID}">My Process</fmt:message></td>
<% }else{ %>
<td class="head1"><a href="#" onClick="JumpToOtherBusinessApp('K1')" class="linkhead"><fmt:message key="i18n.NYEL0010Scrn00.label.3" bundle="${I18N_SCREEN_ID}">My Process</fmt:message></a></td>
<% } %>
</tr><tr><td class="head2"><fmt:message key="i18n.NYEL0010Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Custom Menu Process</fmt:message></td></tr></table></td><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="24%" rowspan="2" align="center" valign="middle"><span id="reporting">reporting</span>
<%
if (bMsg.bizAppId_M2.getValue().equals("")) {
%>
<td class="head1" width="76%"><fmt:message key="i18n.NYEL0010Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Reporting</fmt:message></td>
<% }else{ %>
<td class="head1" width="76%"><a href="#" onClick="JumpToOtherBusinessApp('M2')" class="linkhead"><fmt:message key="i18n.NYEL0010Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Reporting</fmt:message></a></td>
<% } %>
</tr><tr><td class="head2"><fmt:message key="i18n.NYEL0010Scrn00.label.7" bundle="${I18N_SCREEN_ID}">S21 Archive Viewer</fmt:message></td></tr></table></td><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="24%" rowspan="2" align="center" valign="middle"><span id="oninq">oninq</span>
<%
if (bMsg.bizAppId_L1.getValue().equals("")) {
%>
<td class="head1" width="76%"><fmt:message key="i18n.NYEL0010Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Online Inquiry</fmt:message></td>
<% }else{ %>
<td class="head1" width="76%"><a href="#" onClick="JumpToOtherBusinessApp('L1')" class="linkhead"><fmt:message key="i18n.NYEL0010Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Online Inquiry</fmt:message></a></td>
<% } %>
</tr><tr><td class="head2"><fmt:message key="i18n.NYEL0010Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Sales/Order/Inventry</fmt:message></td></tr></table></td><td><img src="img/spacer.gif" width="26" height="40"></td></tr><tr><td><img src="img/spacer.gif" width="8" height="40"></td><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="24%" rowspan="2" align="center" valign="middle"><span id="setupmyprocess">setupmyprocess</span></td>
<%
if (bMsg.bizAppId_K2.getValue().equals("")) {
%>
<td class="head1"><fmt:message key="i18n.NYEL0010Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Set Up My Process</fmt:message></td>
<% }else{ %>
<td class="head1"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MyProcessMaintenance" htmlClass="linkhead" ><fmt:message key="i18n.NYEL0010Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Set Up My Process</fmt:message></ezf:anchor></td>
<% } %>
</tr><tr><td class="head2"><fmt:message key="i18n.NYEL0010Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Setup Custom Process</fmt:message></td></tr></table></td>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="24%" rowspan="2" align="center" valign="middle"><span id="workflow"><fmt:message key="i18n.NYEL0010Scrn00.label.14" bundle="${I18N_SCREEN_ID}">workflow</fmt:message></span>
<%
if (bMsg.bizAppId_Z1.getValue().equals("")) {
%>
<td class="head1" width="76%"><fmt:message key="i18n.NYEL0010Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Workflow</fmt:message></td>
<% }else{ %>
<jsp:include page="../nwfcomp/S21NwfWorkItemCounterHolder.jsp" />
<td class="head1" width="76%" id="nwfCount"><a href="#" onClick="JumpToOtherBusinessApp('Z1')" class="linkhead"><fmt:message key="i18n.NYEL0010Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Workflow</fmt:message></a></td>
<% } %>
</tr><tr><td class="head2"><fmt:message key="i18n.NYEL0010Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Workflow</fmt:message></td></tr></table></td>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="24%" rowspan="2" align="center" valign="middle"><span id="admin"><fmt:message key="i18n.NYEL0010Scrn00.label.16" bundle="${I18N_SCREEN_ID}">ids</fmt:message></span>

<td class="head1" width="76%"><a href="${_ezddatabean.othSysUrl_P1}" target="_blank" class="linkhead"><fmt:message key="i18n.NYEL0010Scrn00.label.17" bundle="${I18N_SCREEN_ID}">IDS</fmt:message></a></td>
</tr><tr><td class="head2"><fmt:message key="i18n.NYEL0010Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Info. Delivery System</fmt:message></td></tr></table></td>
<td><img src="img/spacer.gif" width="26" height="40"></td></tr></table></td>

<td width="445px" height="109" id="mimginfoback"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" width="45" height="15"></td><td><img src="img/spacer.gif" width="400" height="15"></td></tr><tr><td width="10%" height="94" align="center" valign="top"></td><td width="76%" height="94" align="left" valign="top"><ezf:textArea name="menuInfoTxt" ezfName="menuInfoTxt" otherAttr=" rows=\"5\" cols=\"70\" style=\"width:370px;font-family:Arial;background-color:transparent;scrollbar-base-color:#939393;scrollbar-face-color:#939393;scrollbar-arrow-color:#2A2A2A;scrollbar-shadow-color:#2A2A2A;scrollbar-darkshadow-color:#939393;scrollbar-highlight-color:#2A2A2A;scrollbar-3dlight-color:#939393;scrollbar-track-color:#939393;border:1px solid #737373;display:block;\""/></td></tr></table></td><td width="20" height="109"><img src="img/spacer.gif" width="20" height="109"></td></tr><tr><td height="13" colspan="5" align="left"><img src="img/spacer.gif" width="1133" height="13" id="mimgshadow" /></td></tr></table></div><table border="0" cellpadding="0" cellspacing="0"><tr><td height="600" background="img/menu/contents_background.png"><div style="position:absolute; left:40px; top:134px; width:200px; height:200px;" ><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="17" align="left" valign="top"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="17" height="1"></td></tr><tr><td width="17"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" height="5"></td></tr><tr><td align="center" valign="middle">
<% if (!bMsg.xxWfProcCd_A0.getValue().equals("")) { %>

<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="A" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_A0}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_A0}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr></table></td></tr></table></td><td><table width="180" border="0" cellspacing="0" cellpadding="0"><tr><td width="5" bgcolor="#757575"><img src="img/spacer.gif" width="5"></td><td width="8" align="left"><img src="img/spacer.gif" width="8"></td><td width="170"><table width="170" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="2" class="body1"><fmt:message key="i18n.NYEL0010Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Order Process</fmt:message></td></tr><tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr><tr>
<%
if (bMsg.bizAppId_A1.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="allocation" style="filter:gray();" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.20" bundle="${I18N_SCREEN_ID}">allocation</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top">
<a href="#" onClick="JumpToOtherBusinessApp('A1')"><span id="allocation"><fmt:message key="i18n.NYEL0010Scrn00.label.20" bundle="${I18N_SCREEN_ID}">allocation</fmt:message></span></a>
</td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_A1.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Allocation Planning</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('A1')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Allocation Planning</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
<%-- QC29577 START C.KIM 12/10/2018
<tr>
<%
if (bMsg.bizAppId_A2.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="edi" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.22" bundle="${I18N_SCREEN_ID}">edi</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('A2')"><span id="edi"><fmt:message key="i18n.NYEL0010Scrn00.label.22" bundle="${I18N_SCREEN_ID}">edi</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_A2.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.23" bundle="${I18N_SCREEN_ID}">EDI</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('A2')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.23" bundle="${I18N_SCREEN_ID}">EDI</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
QC29577 END C.KIM 12/10/2018 --%>
<tr>
<%
if (bMsg.bizAppId_A3.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="odercapture" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.24" bundle="${I18N_SCREEN_ID}">ordercapture</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('A3')"><span id="odercapture"><fmt:message key="i18n.NYEL0010Scrn00.label.24" bundle="${I18N_SCREEN_ID}">ordercapture</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_A3.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Order Capture</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('A3')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Order Capture</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
</table></td></tr></table></td></tr></table></div><div style="position:absolute; left:255px; top:134px; width:200px; height:200px;" ><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="17" align="left" valign="top"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="17" height="1"></td></tr><tr><td width="17"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" height="5"></td></tr><tr><td align="center" valign="middle">
<%
if (!bMsg.xxWfProcCd_B0.getValue().equals("")) {
%>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="B" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_B0}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_B0}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr></table></td></tr></table></td><td><table width="180" border="0" cellspacing="0" cellpadding="0"><tr><td width="5" bgcolor="#757575"><img src="img/spacer.gif" width="5"></td><td width="8" align="left"><img src="img/spacer.gif" width="8"></td><td width="170"><table width="170" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="2" class="body1"><fmt:message key="i18n.NYEL0010Scrn00.label.26" bundle="${I18N_SCREEN_ID}">SCE</fmt:message></td></tr><tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr><tr>
<%
if (bMsg.bizAppId_B1.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="procurement" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.27" bundle="${I18N_SCREEN_ID}">procurement</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('B1')"><span id="procurement"><fmt:message key="i18n.NYEL0010Scrn00.label.27" bundle="${I18N_SCREEN_ID}">procurement</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_B1.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Procurement</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('B1')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Procurement</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_B2.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="reciving" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.29" bundle="${I18N_SCREEN_ID}">reciving</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('B2')"><span id="reciving"><fmt:message key="i18n.NYEL0010Scrn00.label.29" bundle="${I18N_SCREEN_ID}">reciving</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_B2.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.30" bundle="${I18N_SCREEN_ID}">Receiving Mgmt</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('B2')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.30" bundle="${I18N_SCREEN_ID}">Receiving Mgmt</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_B3.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="shipping" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.31" bundle="${I18N_SCREEN_ID}">shipping</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('B3')"><span id="shipping"><fmt:message key="i18n.NYEL0010Scrn00.label.31" bundle="${I18N_SCREEN_ID}">shipping</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_B3.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Shipping Mgmt</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('B3')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Shipping Mgmt</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_B4.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="inventry" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.33" bundle="${I18N_SCREEN_ID}">inventory</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('B4')"><span id="inventry"><fmt:message key="i18n.NYEL0010Scrn00.label.33" bundle="${I18N_SCREEN_ID}">inventory</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_B4.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.34" bundle="${I18N_SCREEN_ID}">Inventory Mgmt</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('B4')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.34" bundle="${I18N_SCREEN_ID}">Inventory Mgmt</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_B5.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="serial" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.35" bundle="${I18N_SCREEN_ID}">serial</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('B5')"><span id="serial"><fmt:message key="i18n.NYEL0010Scrn00.label.35" bundle="${I18N_SCREEN_ID}">serial</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_B5.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.36" bundle="${I18N_SCREEN_ID}">Serial# Mgmt</fmt:message></td>
<% }else{ %>
<td class="body2" width="130">
<a href="#" onClick="JumpToOtherBusinessApp('B5')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.36" bundle="${I18N_SCREEN_ID}">Serial# Mgmt</fmt:message></a>
</td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr></table></td></tr></table></td></tr></table></div><div style="position:absolute; left:475px; top:134px; width:200px; height:200px;" ><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="17" align="left" valign="top"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="17" height="1"></td></tr><tr><td width="17"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" height="5"></td></tr><tr><td align="center" valign="middle">
<%
if (!bMsg.xxWfProcCd_N0.getValue().equals("")) {
%>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="N" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_N0}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_N0}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<%  } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr></table></td></tr></table></td><td><table width="180" border="0" cellspacing="0" cellpadding="0"><tr><td width="5" bgcolor="#757575"><img src="img/spacer.gif" width="5"></td><td width="8" align="left"><img src="img/spacer.gif" width="8"></td><td width="170"><table width="170" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="2" class="body1"><fmt:message key="i18n.NYEL0010Scrn00.label.37" bundle="${I18N_SCREEN_ID}">Master</fmt:message></td></tr><tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr><tr>
<%
if (bMsg.bizAppId_N1.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="merchandise" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.38" bundle="${I18N_SCREEN_ID}">merchandise</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('N1')"><span id="merchandise"><fmt:message key="i18n.NYEL0010Scrn00.label.38" bundle="${I18N_SCREEN_ID}">merchandise</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_N1.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.39" bundle="${I18N_SCREEN_ID}">Merchandise</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('N1')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.39" bundle="${I18N_SCREEN_ID}">Merchandise</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_N2.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="organization" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.40" bundle="${I18N_SCREEN_ID}">organization</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('N2')"><span id="organization"><fmt:message key="i18n.NYEL0010Scrn00.label.40" bundle="${I18N_SCREEN_ID}">organization</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_N2.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.41" bundle="${I18N_SCREEN_ID}">Organization</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('N2')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.41" bundle="${I18N_SCREEN_ID}">Organization</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_N3.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="partner" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.42" bundle="${I18N_SCREEN_ID}">partner</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('N3')"><span id="partner"><fmt:message key="i18n.NYEL0010Scrn00.label.42" bundle="${I18N_SCREEN_ID}">partner</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_N3.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.43" bundle="${I18N_SCREEN_ID}">Partner</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('N3')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.43" bundle="${I18N_SCREEN_ID}">Partner</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_N4.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="salescondition" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.44" bundle="${I18N_SCREEN_ID}">salescondition</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('N4')"><span id="salescondition"><fmt:message key="i18n.NYEL0010Scrn00.label.44" bundle="${I18N_SCREEN_ID}">salescondition</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_N4.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.45" bundle="${I18N_SCREEN_ID}">Sales Condition</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('N4')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.45" bundle="${I18N_SCREEN_ID}">Sales Condition</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_N5.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="codemaintenance" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.46" bundle="${I18N_SCREEN_ID}">codemaintenance</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('N5')"><span id="codemaintenance"><fmt:message key="i18n.NYEL0010Scrn00.label.46" bundle="${I18N_SCREEN_ID}">codemaintenance</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_N5.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.47" bundle="${I18N_SCREEN_ID}">Code Maintenance</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('N5')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.47" bundle="${I18N_SCREEN_ID}">Code Maintenance</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
</table></td></tr></table></td></tr></table></div>
<div style="position:absolute; left:700px; top:134px; width:200px; height:200px;" ><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="17" align="left" valign="top"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="17" height="1"></td></tr><tr><td width="17"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" height="5"></td></tr><tr><td align="center" valign="middle">
<%
if (!bMsg.xxWfProcCd_H1.getValue().equals("")) {
%>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="H1" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_H1}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_H1}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle">
<%
if (!bMsg.xxWfProcCd_H0.getValue().equals("")) {
%>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="H0" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_H0}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_H0}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle">
<%
if (!bMsg.xxWfProcCd_H2.getValue().equals("")) {
%>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="H2" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_H2}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_H2}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr></table></td></tr></table></td><td><table width="180" border="0" cellspacing="0" cellpadding="0"><tr><td width="5" bgcolor="#757575"><img src="img/spacer.gif" width="5"></td><td width="8" align="left"><img src="img/spacer.gif" width="8"></td><td width="170"><table width="170" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="2" class="body1"><fmt:message key="i18n.NYEL0010Scrn00.label.52" bundle="${I18N_SCREEN_ID}">Post-Sales</fmt:message></td></tr><tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr>
<!--
<tr>
<%
if (bMsg.bizAppId_H1.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="servicerepair" style="filter:gray()" class="gray">servicerepair</span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('H1')"><span id="servicerepair">servicerepair</span></a>
</td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_H1.getValue().equals("")) {
%>
<td class="body2" width="130">Service & Repair</td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('H1')" class="linkbody">Service & Repair</a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr>
-->
<tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
<tr><%if (bMsg.bizAppId_H3.getValue().equals("")){%><td width="40" align="left" valign="top"><span id="dispatch" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.53" bundle="${I18N_SCREEN_ID}">dispatch</fmt:message></span></td><% }else{ %><td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('H3')"><span id="dispatch"><fmt:message key="i18n.NYEL0010Scrn00.label.53" bundle="${I18N_SCREEN_ID}">dispatch</fmt:message></span></a></td><% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr><%if (bMsg.bizAppId_H3.getValue().equals("")) {%><td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.54" bundle="${I18N_SCREEN_ID}">Dispatch</fmt:message></td><% }else{ %><td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('H3')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.54" bundle="${I18N_SCREEN_ID}">Dispatch</fmt:message></a></td><% } %></tr><tr><td class="body3" width="130"></td></tr></table></td></tr>
<tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
<tr><%if (bMsg.bizAppId_H4.getValue().equals("")) {%><td width="40" align="left" valign="top"><span id="machine_asset" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.55" bundle="${I18N_SCREEN_ID}">Machine/Asset</fmt:message></span></td><% }else{ %><td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('H4')"><span id="machine_asset"><fmt:message key="i18n.NYEL0010Scrn00.label.55" bundle="${I18N_SCREEN_ID}">Machine/Asset</fmt:message></span></a></td><% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr><%if (bMsg.bizAppId_H4.getValue().equals("")) {%><td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.55" bundle="${I18N_SCREEN_ID}">Machine/Asset</fmt:message></td><% }else{ %><td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('H4')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.55" bundle="${I18N_SCREEN_ID}">Machine/Asset</fmt:message></a></td><% } %></tr><tr><td class="body3" width="130"></td></tr></table></td></tr>
<tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
<tr><%if (bMsg.bizAppId_H5.getValue().equals("")) {%><td width="40" align="left" valign="top"><span id="contract_billing" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.56" bundle="${I18N_SCREEN_ID}">Contract/Billing</fmt:message></span></td><% }else{ %><td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('H5')"><span id="contract_billing"><fmt:message key="i18n.NYEL0010Scrn00.label.56" bundle="${I18N_SCREEN_ID}">Contract/Billing</fmt:message></span></a></td><% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr><%if (bMsg.bizAppId_H5.getValue().equals("")) {%><td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.56" bundle="${I18N_SCREEN_ID}">Contract/Billing</fmt:message></td><% }else{ %><td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('H5')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.56" bundle="${I18N_SCREEN_ID}">Contract/Billing</fmt:message></a></td><% } %></tr><tr><td class="body3" width="130"></td></tr></table></td></tr>
<tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
<%-- QC29577 START C.KIM 12/10/2018
<tr>
<%
if (bMsg.bizAppId_H2.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="return" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.57" bundle="${I18N_SCREEN_ID}">return</fmt:message></span></td><% }else{ %><td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('H2')"><span id="return"><fmt:message key="i18n.NYEL0010Scrn00.label.57" bundle="${I18N_SCREEN_ID}">return</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_H2.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.58" bundle="${I18N_SCREEN_ID}">Return</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('H2')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.58" bundle="${I18N_SCREEN_ID}">Return</fmt:message></a></td>
<% } %>
</tr>
<tr><td class="body3" width="130"></td></tr></table></td></tr>
<tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr>
QC29577 END C.KIM 12/10/2018 --%>
</table></td></tr></table></td></tr></table></div><div style="position:absolute; left:40px; top:400px; width:200px; height:200px;" ><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="17" align="left" valign="top"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="17" height="1"></td></tr><tr><td width="17"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" height="5"></td></tr><tr><td align="center" valign="middle">
<%
if (!bMsg.xxWfProcCd_C0.getValue().equals("")) {
%>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="C" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_C0}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_C0}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr></table></td></tr></table></td><td><table width="180" border="0" cellspacing="0" cellpadding="0"><tr><td width="5" bgcolor="#757575"><img src="img/spacer.gif" width="5"></td><td width="8" align="left"><img src="img/spacer.gif" width="8"></td><td width="170"><table width="170" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="2" class="body1"><fmt:message key="i18n.NYEL0010Scrn00.label.59" bundle="${I18N_SCREEN_ID}">Invoicing</fmt:message></td></tr><tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr><tr>
<%
if (bMsg.bizAppId_C1.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="invoice" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.60" bundle="${I18N_SCREEN_ID}">invoice</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('C1')"><span id="invoice"><fmt:message key="i18n.NYEL0010Scrn00.label.60" bundle="${I18N_SCREEN_ID}">invoice</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_C1.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.61" bundle="${I18N_SCREEN_ID}">Invoice</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('C1')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.61" bundle="${I18N_SCREEN_ID}">Invoice</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_C2.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="creditdevit" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.62" bundle="${I18N_SCREEN_ID}">creditdevit</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('C2')"><span id="creditdevit"><fmt:message key="i18n.NYEL0010Scrn00.label.62" bundle="${I18N_SCREEN_ID}">creditdevit</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_C2.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.63" bundle="${I18N_SCREEN_ID}">Credit/Debit</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('C2')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.63" bundle="${I18N_SCREEN_ID}">Credit/Debit</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr></table></td></tr></table></td></tr></table></div>
<div style="position:absolute; left:251px; top:400px; width:200px; height:100px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="21" align="left" valign="top"><table width="21" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="21" height="1"></td>
					</tr>
					<tr>
						<td width="21">
							<table width="21" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="img/spacer.gif" height="5"></td>
								</tr>
								<tr>
<td align="center" valign="middle">
<% if (!bMsg.xxWfProcCd_D0.getValue().equals("")) { %>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="D" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_D0}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_D0}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td>
								</tr>
								<tr>
									<td><img src="img/spacer.gif" height="8"></td>
								</tr>
								<tr>
									<td align="center" valign="middle"><img src="img/spacer.gif"></td>
								</tr>
								<tr>
									<td><img src="img/spacer.gif" height="8"></td>
								</tr>
								<tr>
									<td align="center" valign="middle"><img src="img/spacer.gif"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		<td>
	<table width="180" border="0" cellspacing="0" cellpadding="0">
	<tr><td width="5" bgcolor="#757575"><img src="img/spacer.gif" width="5"></td><td width="8" align="left"><img src="img/spacer.gif" width="8"></td><td width="170"><table width="170" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="2" class="body1"><fmt:message key="i18n.NYEL0010Scrn00.label.64" bundle="${I18N_SCREEN_ID}">AR</fmt:message></td></tr><tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr><tr>
<%
if (bMsg.bizAppId_D1.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="cashapplication" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.65" bundle="${I18N_SCREEN_ID}">cashapplication</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top">
<a href="#" onClick="JumpToOtherBusinessApp('D1')"><span id="cashapplication"><fmt:message key="i18n.NYEL0010Scrn00.label.65" bundle="${I18N_SCREEN_ID}">cashapplication</fmt:message></span></a>
</td>
<% } %>
<td>
<table border="0" cellspacing="0" cellpadding="0">
<tr>
<%
if (bMsg.bizAppId_D1.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.66" bundle="${I18N_SCREEN_ID}">Cash Application</fmt:message></td>
<% }else{ %>
<td class="body2" width="130">
<a href="#" onClick="JumpToOtherBusinessApp('D1')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.66" bundle="${I18N_SCREEN_ID}">Cash Application</fmt:message></a>
</td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_D2.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="collection" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.67" bundle="${I18N_SCREEN_ID}">collection</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('D2')"><span id="collection"><fmt:message key="i18n.NYEL0010Scrn00.label.67" bundle="${I18N_SCREEN_ID}">collection</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_D2.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.68" bundle="${I18N_SCREEN_ID}">Collection</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('D2')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.68" bundle="${I18N_SCREEN_ID}">Collection</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr></table></td></tr></table></td></tr></table></div>

<div style="position:absolute; left:255px; top:520px; width:200px; height:100px;" >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="21" align="left" valign="top">
				<table width="17" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="17" height="1"></td>
					</tr>
					<tr>
						<td width="17">
							<table width="17" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="img/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td align="center" valign="middle"></td>
								</tr>
								<tr>
									<td><img src="img/spacer.gif" height="8"></td>
								</tr>
								<tr>
									<td align="center" valign="middle"><img src="img/spacer.gif"></td>
								</tr>
								<tr>
									<td><img src="img/spacer.gif" height="8"></td>
								</tr>
								<tr>
									<td align="center" valign="middle"><img src="img/spacer.gif"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table width="180" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="5" bgcolor="#757575">
							<img src="img/spacer.gif" width="5">
						</td>
						<td width="8" align="left">
							<img src="img/spacer.gif" width="8">
						</td>
						<td width="170">
							<table width="170" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="2" class="body1">
										<fmt:message key="i18n.NYEL0010Scrn00.label.69" bundle="${I18N_SCREEN_ID}">System Control</fmt:message>
									</td>
								</tr>
								<tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr>
								<tr>
								<% if (bMsg.bizAppId_O1.getValue().equals("")) { %>
									<td width="40" align="left" valign="top">
										<span id="sysmenu" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.70" bundle="${I18N_SCREEN_ID}">sysmenu</fmt:message></span>
									</td>
								<% }else{ %>
									<td width="40" align="left" valign="top">
										<a href="#" onClick="JumpToOtherBusinessApp('O1')"><span id="sysmenu"><fmt:message key="i18n.NYEL0010Scrn00.label.70" bundle="${I18N_SCREEN_ID}">sysmenu</fmt:message></span></a>
									</td>
								<% } %>
									<td>
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<% if (bMsg.bizAppId_O1.getValue().equals("")) { %>
													<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.71" bundle="${I18N_SCREEN_ID}">System Menu</fmt:message></td>
												<% }else{ %>
													<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('O1')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.71" bundle="${I18N_SCREEN_ID}">System Menu</fmt:message></a></td>
												<% } %>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2" height="4">
										<img src="img/spacer.gif" height="4">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

<%-- CustomApp(ASCC) ADD START J.Kim 10/06/2015
<div style="position:absolute; left:700px; top:400px; width:200px; height:200px;" >
--%>
<div style="position:absolute; left:700px; top:440px; width:200px; height:200px;" >
<%-- CustomApp(ASCC) ADD END J.Kim 10/06/2015--%>

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="17" align="left" valign="top"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td height="1" align="left" valign="top" bgcolor="#757575"><img src="img/spacer.gif" width="17" height="1"></td></tr><tr><td width="17"><table width="17" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="img/spacer.gif" height="5"></td></tr><tr><td align="center" valign="middle">

<%
if (!bMsg.xxWfProcCd_J0.getValue().equals("")) {
%>
<jsp:include page="../wfcomp/S21WfWorkItemCounterHolder.jsp">
<jsp:param name="nameSpace" value="J" />
<jsp:param name="processCodes" value="${_ezddatabean.xxWfProcCd_J0}" />
<jsp:param name="transitionName" value="WorkList" />
<jsp:param name="s21ProcessGroupId" value="${_ezddatabean.menuProcGrpCd_J0}" />
<jsp:param name="hiddenName" value="menuProcGrpCd" />
</jsp:include>
<% } %>
</td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr><tr><td><img src="img/spacer.gif" height="8"></td></tr><tr><td align="center" valign="middle"><img src="img/spacer.gif"></td></tr></table></td></tr></table></td><td><table width="180" border="0" cellspacing="0" cellpadding="0"><tr><td width="5" bgcolor="#757575"><img src="img/spacer.gif" width="5"></td><td width="8" align="left"><img src="img/spacer.gif" width="8"></td><td width="170"><table width="170" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="2" class="body1"><fmt:message key="i18n.NYEL0010Scrn00.label.72" bundle="${I18N_SCREEN_ID}">Financial Link</fmt:message></td></tr><tr><td colspan="2" height="4"><img src="img/spacer.gif" height="4"></td></tr><tr>
<%
if (bMsg.bizAppId_J1.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="aje" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.73" bundle="${I18N_SCREEN_ID}">aje</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('J1')"><span id="aje"><fmt:message key="i18n.NYEL0010Scrn00.label.73" bundle="${I18N_SCREEN_ID}">aje</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_J1.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.74" bundle="${I18N_SCREEN_ID}">AJE</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('J1')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.74" bundle="${I18N_SCREEN_ID}">AJE</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr><tr>
<%
if (bMsg.bizAppId_J2.getValue().equals("")) {
%>
<td width="40" align="left" valign="top"><span id="cost" style="filter:gray()" class="gray"><fmt:message key="i18n.NYEL0010Scrn00.label.75" bundle="${I18N_SCREEN_ID}">cost</fmt:message></span></td>
<% }else{ %>
<td width="40" align="left" valign="top"><a href="#" onClick="JumpToOtherBusinessApp('J2')"><span id="cost"><fmt:message key="i18n.NYEL0010Scrn00.label.75" bundle="${I18N_SCREEN_ID}">cost</fmt:message></span></a></td>
<% } %>
<td><table border="0" cellspacing="0" cellpadding="0"><tr>
<%
if (bMsg.bizAppId_J2.getValue().equals("")) {
%>
<td class="body2" width="130"><fmt:message key="i18n.NYEL0010Scrn00.label.76" bundle="${I18N_SCREEN_ID}">Cost</fmt:message></td>
<% }else{ %>
<td class="body2" width="130"><a href="#" onClick="JumpToOtherBusinessApp('J2')" class="linkbody"><fmt:message key="i18n.NYEL0010Scrn00.label.76" bundle="${I18N_SCREEN_ID}">Cost</fmt:message></a></td>
<% } %>
</tr><tr><td class="body3" width="130"></td></tr></table></td></tr><tr><td colspan="2" height="6"><img src="img/spacer.gif" height="6"></td></tr></table></td></tr></table></td></tr></table></div>
</td></tr><tr><td width="1133" height="5"><img src="img/spacer.gif" width="1133" height="5" id="mimgfooter" /></td></tr></table>

<script type="text/javascript">
<!--
function goOtherLink(b){
	if(typeof(ajaxS21AbortWfRequests)=="function"){
		ajaxS21AbortWfRequests()
	}
	if(b.length>=4){
		if(b.substring(0,4)=="http"){
			window.open(b,"_blank","")
		}else{
			window.open(b,"_self")
		}
	}
}
function goOtherLink_parts(b){
	if(typeof(ajaxS21AbortWfRequests)=="function"){
		ajaxS21AbortWfRequests()
	}
	window.open(b,"_blank","");
	(open("","_top").opener=top).close()
}
function JumpToOtherBusinessApp(b){
	if(typeof(ajaxS21AbortWfRequests)=="function"){
		ajaxS21AbortWfRequests()
	}
	document.getElementById("selectedProcess").value=b;
	sendServer("JumpToOtherBusinessApp")
}
// START -09/16/2011 Sumida for cross browser
document.write("<",'input type="hidden" id="selectedProcess" name="selectedProcess"'," >");
document.write("<",'input type="hidden" id="menuProcGrpCd" name="menuProcGrpCd" value=""'," >");
// END   -09/16/2011 Sumida for cross browser
// -->
</script>
<%-- CustomApp(ASCC) ADD START J.Kim 10/06/2015--%>
<script>
function f_open_window_max( aURL, aWinName )
{
   var wOpen;
   var sOptions;

   sOptions = 'status=yes,menubar=no,scrollbars=yes,resizable=yes,toolbar=no';
   sOptions = sOptions + ',width=' + (screen.availWidth - 17).toString();
   sOptions = sOptions + ',height=' + (screen.availHeight - 65).toString();
   sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

   wOpen = window.open( '', aWinName, sOptions );
   wOpen.location = aURL;
   wOpen.focus();
   wOpen.moveTo( 0, 0 );
   //wOpen.resizeTo( screen.availWidth, screen.availHeight  );
   return wOpen;
}
</script>
<%-- CustomApp(ASCC) ADD END J.Kim 10/06/2015--%>

<%-- **** Task specific area ends here **** --%>
