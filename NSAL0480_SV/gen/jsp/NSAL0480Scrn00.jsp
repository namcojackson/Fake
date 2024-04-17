<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161202151234 --%>
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
<fmt:setBundle basename="I18N_NSAL0480Scrn00" var="I18N_SCREEN_ID" scope="request" />
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

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSAL0480Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0480Scrn00.title" bundle="${I18N_SCREEN_ID}">Model Search</fmt:message>">

	<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>

<!--
		<ezf:skip>
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<li title="<fmt:message key="i18n.NSAL0480Scrn00.title" bundle="${I18N_SCREEN_ID}">Model Search</fmt:message>" class="pTab_ON"><a href="#">Mdl Search</a></li>
								</div>
							</td>
							<td valign="bottom" align="center">
								<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
							</td>
							<td valign="bottom" align="center">
								<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
							</td>
						</tr>
					</table>
				</ul>
			</div>
		</ezf:skip>
-->

			<%-- include S21BusinessProcessTAB --%>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<div class="pTab_BODY" style="width:100%; text-align:center;" >
				<table border="0" cellspacing="0" cellpadding="0" style="width:98%; text-align:left;margin-top:3">
					<col width="120">
					<col width="340">
					<col width="110">
					<col width="300">
					<col width="">
					<tr>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Saved Search Options</fmt:message></td>
						<td>
							<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width: 320px\" id=\"srchOptPk\""/>
						</td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Search Option Name</fmt:message></td>
						<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td>
							<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
							<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
						</td>
					</tr>
				</table>
				<hr style="width:98%;" />

				<table border="0" cellspacing="0" cellpadding="0" style="width:98%; text-align:left;">
					<col width="">
					<col width="">
					<col width="">
					<col width="">
					<col width="">
					<col width="">
					<tr height="22">
						<td class="stab"><span><fmt:message key="i18n.NSAL0480Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></sapn>(*)</td>
						<td class="stab"><ezf:inputText name="t_MdlNm_H" ezfName="t_MdlNm_H" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Meter Group</fmt:message></td>
						<td>
							<ezf:select name="mtrGrpPk_H" ezfName="mtrGrpPk_H" ezfBlank="1" ezfCodeName="mtrGrpPk_L" ezfDispName="mtrGrpNm_L" otherAttr=" style=\"width: 200px\" id=\"mtrGrpPk\""/>
						</td>
						<td class="stab">
							<ezf:anchor name="t_ItemCd_LK" ezfName="t_ItemCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdse" otherAttr=" ezfanchortext=\"\"">
							<fmt:message key="i18n.NSAL0480Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Item Code(*)</fmt:message>
							</ezf:anchor>
						</td>
						<td class="stab"><ezf:inputText name="t_ItemCd_H" ezfName="t_ItemCd_H" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/></td>
					</tr>

					<tr height="22">
						<td class="stab"><span><fmt:message key="i18n.NSAL0480Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Model Description</fmt:message></span>(*)</td>
						<td class="stab"><ezf:inputText name="mdlDescTxt_H" ezfName="mdlDescTxt_H" otherAttr=" size=\"30\" maxlength=\"90\""/></td>
						<td class="stab">
							<ezf:anchor name="svcSkillNum_LK" ezfName="svcSkillNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SvcSkill" otherAttr=" ezfanchortext=\"\""><fmt:message key="i18n.NSAL0480Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Service Skills</fmt:message></ezf:anchor>
						</td>
						<td><ezf:inputText name="svcSkillNum_H" ezfName="svcSkillNum_H" value="XXXX5" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/><ezf:inputText name="svcSkillDescTxt_H" ezfName="svcSkillDescTxt_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"21\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td class="stab">
							<ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SplyMdse" otherAttr=" ezfanchortext=\"\"">
							<fmt:message key="i18n.NSAL0480Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Supply Item Code(*)</fmt:message>
							</ezf:anchor>
						</td>
						<td class="stab"><ezf:inputText name="mdseCd_H" ezfName="mdseCd_H" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/></td>
					</tr>

					<tr height="22">
						<td class="stab">
							<ezf:anchor name="mdlGrp_LK" ezfName="mdlGrp_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_MdlGrp" otherAttr=" ezfanchortext=\"\"">
							<span><fmt:message key="i18n.NSAL0480Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Model Group</fmt:message></span>(*)
							</ezf:anchor>
						</td>
						<td class="stab"><ezf:inputText name="mdlGrpNm_H" ezfName="mdlGrpNm_H" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Installation Rules</fmt:message></td>
						<td>
							<ezf:select name="svcIstlRuleNum_HY" ezfName="svcIstlRuleNum_HY" ezfBlank="1" ezfCodeName="svcIstlRuleNum_LY" ezfDispName="svcIstlRuleNm_LY" otherAttr=" style=\"width: 200px\" id=\"svcIstlRuleNum\""/>
						</td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Supply OEM Code(*)</fmt:message></td>
						<td class="stab"><ezf:inputText name="imgSplyOemCd_H" ezfName="imgSplyOemCd_H" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
					</tr>

					<tr height="22">
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Service Segment</fmt:message></td>
						<td>
							<ezf:select name="svcSegCd_H" ezfName="svcSegCd_H" ezfBlank="1" ezfCodeName="svcSegCd_L" ezfDispName="xxScrItem54Txt_L" otherAttr=" style=\"width: 216px\" id=\"svcSegCd\""/>
						</td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.13" bundle="${I18N_SCREEN_ID}">De-Installation Rules</fmt:message></td>
						<td>
							<ezf:select name="svcIstlRuleNum_HN" ezfName="svcIstlRuleNum_HN" ezfBlank="1" ezfCodeName="svcIstlRuleNum_LN" ezfDispName="svcIstlRuleNm_LN" otherAttr=" style=\"width: 200px\" id=\"svcDeinsRuleNum\""/>
						</td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Supply Class</fmt:message></td>
						<td>
							<ezf:select name="mdseItemClsTpCd_H" ezfName="mdseItemClsTpCd_H" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_L" ezfDispName="mdseItemClsTpDescTxt_L" otherAttr=" style=\"width: 216px\" id=\"mdseItemClsTpCd\""/>
						</td>
					</tr>

					<tr height="22">
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Active Status</fmt:message></td>
						<td>
							<ezf:select name="mdlActvFlg_H" ezfName="mdlActvFlg_H" ezfBlank="1" ezfCodeName="mdlActvFlg_L" ezfDispName="xxScrItem10Txt_L" otherAttr=" style=\"width: 216px\" id=\"mdlActvFlg\""/>
						</td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Customer Installable</fmt:message></td>
						<td align="left">
							<ezf:inputCheckBox name="custIstlFlg_HY" ezfName="custIstlFlg_HY" value="Y" otherAttr=" id=\"custIstlFlg_HY\""/><fmt:message key="i18n.NSAL0480Scrn00.label.17" bundle="${I18N_SCREEN_ID}">ON&nbsp;</fmt:message>
							<ezf:inputCheckBox name="custIstlFlg_HN" ezfName="custIstlFlg_HN" value="N" otherAttr=" id=\"custIstlFlg_HN\""/><fmt:message key="i18n.NSAL0480Scrn00.label.18" bundle="${I18N_SCREEN_ID}">OFF</fmt:message>
						</td>
					</tr>

					<tr height="22">
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Create Date</fmt:message></td>
						<td>
							<ezf:inputText name="xxCratDt_H" ezfName="xxCratDt_H" otherAttr=" size=\"10\" maxlength=\"10\""/>
							&nbsp;<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_H', 4);">
						</td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Installation Required for Revenue</fmt:message></td>
						<td align="left">
							<ezf:inputCheckBox name="svcIstlReqFlg_HY" ezfName="svcIstlReqFlg_HY" value="Y" otherAttr=" id=\"svcIstlReqFlg_HY\""/><fmt:message key="i18n.NSAL0480Scrn00.label.17" bundle="${I18N_SCREEN_ID}">ON&nbsp;</fmt:message>
							<ezf:inputCheckBox name="svcIstlReqFlg_HN" ezfName="svcIstlReqFlg_HN" value="N" otherAttr=" id=\"svcIstlReqFlg_HN\""/><fmt:message key="i18n.NSAL0480Scrn00.label.18" bundle="${I18N_SCREEN_ID}">OFF</fmt:message>
						</td>
					</tr>

					<tr height="22">
						<td></td>
						<td></td>
						<td class="stab"><fmt:message key="i18n.NSAL0480Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Site Survey Required</fmt:message></td>
						<td align="left">
							<ezf:inputCheckBox name="siteSrvyReqFlg_HY" ezfName="siteSrvyReqFlg_HY" value="Y" otherAttr=" id=\"siteSrvyReqFlg_HY\""/><fmt:message key="i18n.NSAL0480Scrn00.label.17" bundle="${I18N_SCREEN_ID}">ON&nbsp;</fmt:message>
							<ezf:inputCheckBox name="siteSrvyReqFlg_HN" ezfName="siteSrvyReqFlg_HN" value="N" otherAttr=" id=\"siteSrvyReqFlg_HN\""/><fmt:message key="i18n.NSAL0480Scrn00.label.18" bundle="${I18N_SCREEN_ID}">OFF</fmt:message>
						</td>
						<td></td>
						<td align="right">
							<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
						</td>
					</tr>
				</table>
				<hr style="width:98%;" />

				<table border="0" style="table-layout:fixed;width=100%;margin-left:20;">
					<col width="180">
					<col width="530">
					<col width="">
					<tr>
						<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
						<td></td>
					<!-- Pagination & Navigation--START-->
						<td>
							<ezf:skip>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="10">
									<col>
									<col width="1">
									<col>
								<tr>
									<td class="stab">Showing</td>
									<td class="pOut">1</td>
									<td class="stab">to</td>
									<td class="pOut">20</td>
									<td class="stab">of</td>
									<td class="pOut">200</td>
									<td>&nbsp;</td>
									<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
									<td></td>
									<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
								</tr>
								</table>
							</ezf:skip>
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
						</td>
					<!-- Pagination & Navigation--END-->
					</tr>
				</table>
				<%-- ############ DETAIL ############ --%>
				<table>
					<tr>
						<td>
							<div id="parentBoxA">
					<!-- <table>
  						<tr>
							<td width="10"></td>
							<td> -->
								<div style="float:left; display:block"> <!-- left table -->
									<div id='leftTblHead' style="display:block;">
									</div>
									<div id='leftTbl' style="float:left; display:block; height:254px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
									</div>
								</div>  <!-- left table -->
								<div style="float:left"> <!-- right table -->
									<div id='rightTblHead' style="width:1064px; display:block; overflow:hidden; margin:0px;padding:0px;">
										<!-- <div id="topTBL" style="overflow-x: hidden; width: 1062; overflow-y: none; height: 35;" > -->
										<!-- <div style="width:100%; text-align:left;" > -->
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1732 px" style="margin-right:20px" >
										<!-- <table style="table-layout:fixed;width=1064;margin-left:20;" border="1" cellpadding="1" cellspacing="0">-->
											<col width="22" align="center">	<!-- radio -->
											<col width="197" align="center">	<!-- Model Name -->
											<col width="205" align="center">	<!-- Model Description -->
											<col width="96" align="center">	<!-- Model Group -->
											<col width="60" align="center">	<!-- Service Segment -->
											<col width="80" align="center">	<!-- Status -->
											<col width="82" align="center">	<!-- Create Date -->
											<col width="160" align="center">	<!-- Meter Group -->
											<col width="160" align="center">	<!-- Service Skills -->
											<col width="110" align="center">	<!-- Installation Rules -->
											<col width="130" align="center">	<!-- De-installation Rules -->
											<col width="130" align="center">	<!-- Customer Installable -->
											<col width="170" align="center">	<!-- Install Required for Review -->
											<col width="130" align="center">	<!-- Site Survey Required -->
											<tr>
												<td id="AH0" class="pClothBs">&nbsp;</td>
												<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></a><img id="sortIMG.t_MdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlDescTxt_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Model Description</fmt:message></a><img id="sortIMG.mdlDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlGrpNm_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Model Group</fmt:message></a><img id="sortIMG.mdlGrpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSegCd_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Svc Seg</fmt:message></a><img id="sortIMG.svcSegCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem10Txt_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Status</fmt:message></a><img id="sortIMG.xxScrItem10Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxCratDt_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Create Date</fmt:message></a><img id="sortIMG.xxCratDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrGrpNm_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Meter Group</fmt:message></a><img id="sortIMG.mtrGrpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSkillNm_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Service Skills</fmt:message></a><img id="sortIMG.svcSkillNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcIstlRuleNum_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Installation Rules</fmt:message></a><img id="sortIMG.svcIstlRuleNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcDeinsRuleNum_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.27" bundle="${I18N_SCREEN_ID}">De-installation Rules</fmt:message></a><img id="sortIMG.svcDeinsRuleNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'custIstlFlg_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Customer Installable</fmt:message></a><img id="sortIMG.custIstlFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcIstlReqFlg_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Install Required for Review</fmt:message></a><img id="sortIMG.svcIstlReqFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'siteSrvyReqFlg_A')"><fmt:message key="i18n.NSAL0480Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Site Survey Required</fmt:message></a><img id="sortIMG.siteSrvyReqFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											</tr>
										</table>
										<!-- </div> topTBL -->
									</div><!-- 'rightTblHead' -->
									<div id="rightTbl" style="width:1081px; height:271px; display:block; overflow:scroll; margin:0px; padding:0px;" >
									<!-- <div style="overflow-y:scroll; height:312; overflow-x:none; width:1100;" id="A" width="1100px" > -->
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1732px" >
										<!-- <table id="A" style="table-layout:fixed;margin-left:20;" border="1" cellpadding="1" cellspacing="0"> -->
											<col width="22" align="center">	<!-- radio -->
											<col width="197" align="left">		<!-- Model Name -->
											<col width="205" align="center">	<!-- Model Description -->
											<col width="96" align="center">	<!-- Model Group -->
											<col width="60" align="center">	<!-- Service Segment -->
											<col width="80" align="center">	<!-- Status -->
											<col width="82" align="center">	<!-- Create Date -->
											<col width="160" align="center">	<!-- Meter Group -->
											<col width="160" align="center">	<!-- Service Skills -->
											<col width="110" align="center">	<!-- Installation Rules -->
											<col width="130" align="center">	<!-- De-installation Rules -->
											<col width="130" align="center">	<!-- Customer Installable -->
											<col width="170" align="center">	<!-- Install Required for Review -->
											<col width="130" align="center">	<!-- Site Survey Required -->
											<!--  <tbody>  -->
											<ezf:row ezfHyo="A">
											<tr id="A_rightTBLRow#{EZF_ROW_NUMBER}">
												<td style="height:25px; text-align:center"><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn#{EZF_ROW_NUMBER}\""/></td>
												<td align="center"><ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="IPC001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
												<td align="center"><ezf:inputText name="mdlDescTxt_A" ezfName="mdlDescTxt_A" value="Image Press C800" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"90\""/></td>
												<td align="center"><ezf:inputText name="mdlGrpNm_A" ezfName="mdlGrpNm_A" value="IPC123456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
												<td align="center"><ezf:inputText name="svcSegCd_A" ezfName="svcSegCd_A" value="XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
												<td align="center"><ezf:inputText name="xxScrItem10Txt_A" ezfName="xxScrItem10Txt_A" value="Active" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"09\" maxlength=\"10\""/></td>
												<td align="center"><ezf:inputText name="xxCratDt_A" ezfName="xxCratDt_A" value="03/04/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												<td align="center"><ezf:inputText name="mtrGrpNm_A" ezfName="mtrGrpNm_A" value="CNSL-1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
												<td align="center"><ezf:inputText name="svcSkillNm_A" ezfName="svcSkillNm_A" value="Skill 1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
												<td align="center"><ezf:inputText name="svcIstlRuleNum_A" ezfName="svcIstlRuleNum_A" value="FF" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
												<td align="center"><ezf:inputText name="svcDeinsRuleNum_A" ezfName="svcDeinsRuleNum_A" value="DD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
												<td align="center"><ezf:inputText name="custIstlFlg_A" ezfName="custIstlFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\""/></td>
												<td align="center"><ezf:inputText name="svcIstlReqFlg_A" ezfName="svcIstlReqFlg_A" value="N" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\""/></td>
												<td align="center"><ezf:inputText name="siteSrvyReqFlg_A" ezfName="siteSrvyReqFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\""/></td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												</td>
											</tr>
											<ezf:skip>
											<tr>
												<td style="height:25px; text-align:center"><input type="radio" id="xxRadioBtn" name="xxRadioBtn" ezfname="xxRadioBtn" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" ></td>
												<td align="center"><input type="text" name="t_MdlNm_A" ezfname="t_MdlNm_A" value="IPC001" size="25" maxlength="50" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="mdlDescTxt_A" ezfname="mdlDescTxt_A" value="Image Press C800" size="28" maxlength="90" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="mdlGrpNm_A" ezfname="mdlGrpNm_A" value="IPC123456" size="12" maxlength="20" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="svcSegCd_A" ezfname="svcSegCd_A" value="XX" size="2" maxlength="2" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="xxScrItem10Txt_A" ezfname="xxScrItem10Txt_A" value="Active" size="09" maxlength="10" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="xxCratDt_A" ezfname="xxCratDt_A" value="03/04/2015" size="10" maxlength="10" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="mtrGrpNm_A" ezfname="mtrGrpNm_A" value="CNSL-1" size="20" maxlength="30" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="svcSkillNm_A" ezfname="svcSkillNm_A" value="Skill 1" size="20" maxlength="30" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="svcIstlRuleNum_A" ezfname="svcIstlRuleNum_A" value="FF" size="2" maxlength="2" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="svcDeinsRuleNum_A" ezfname="svcDeinsRuleNum_A" value="DD" size="2" maxlength="2" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="custIstlFlg_A" ezfname="custIstlFlg_A" value="Y" size="1" maxlength="1" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="svcIstlReqFlg_A" ezfname="svcIstlReqFlg_A" value="N" size="1" maxlength="1" ezfhyo="A" ></td>
												<td align="center"><input type="text" name="siteSrvyReqFlg_A" ezfname="siteSrvyReqFlg_A" value="Y" size="1" maxlength="1" ezfhyo="A" ></td>
											</tr>
											</ezf:skip>
											</ezf:row>
										<!--  </tbody>  -->
										</table>
									</div><!-- rightTbl -->
								</div><!-- right table -->
							</div><!-- parent box -->
							<table border="0" style="table-layout:fixed;width=100%;margin-left:20;">
								<col width="">
								<tr>
									<td>
										<ezf:inputButton name="ViewItem" value="View Item" htmlClass="pBtn8" />
									</td>
								</tr>
							</table>
							
									<!--</div>--><!-- rightTblHead -->
								<!--</div>--> <!-- right table -->
						</td>
					</tr>
				</table>
			</div><!-- pTab_BODY -->
		</td>
		</tr>
	</table>
	</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false);
	</script>

<%-- **** Task specific area ends here **** --%>
