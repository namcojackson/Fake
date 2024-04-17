<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151126155624 --%>
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
<fmt:setBundle basename="I18N_NSBL0200Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSBL0200Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSBL0200Scrn00.title" bundle="${I18N_SCREEN_ID}">Technician Recommend Popup</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## FROM (COMMON)HEADER ######################################## --%>
				<table style="margin-top: 5px; margin-left: 5px" cellSpacing="0" cellPadding="1" border="0">
					<colgroup>
						<!-- Model Name -->
						<col align="left" width="80px">
						<col align="center" width="75px">
						<col width="5px">
						<!-- MDSE Code -->
						<col align="left" width="70px">
						<col align="center" width="75px">
						<col width="5px">
						<!-- Serial# -->
						<col align="left" width="50px">
						<col align="center" width="75px">
						<col width="5px">
						<!-- Branch -->
						<col align="left" width="50px">
						<col align="center" width="90px">
						<col width="5px">
						<!-- LOB -->
						<col align="left" width="25px">
						<col align="center" width="75px">
						<col width="5px">
					</colgroup>
					<tbody>
						<tr>
							<!-- Model Name -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></td>
							<td><ezf:inputText name="mdlNm_SC" ezfName="mdlNm_SC" value="ZZZZZZZZZ1" otherAttr=" maxlength=\"50\" size=\"10\" ezftoupper=\"\""/></td>
							<td />
							<!-- MDSE Code -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.2" bundle="${I18N_SCREEN_ID}">MDSE Code</fmt:message></td>
							<td><ezf:inputText name="mdseCd_SC" ezfName="mdseCd_SC" value="ZZZZZZZZZ1ZZZZZZ" otherAttr=" maxlength=\"16\" size=\"10\" ezftoupper=\"\""/></td>
							<td />
							<!-- Serial# -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
							<td><ezf:inputText name="serNum_SC" ezfName="serNum_SC" value="ZZZZZZZZZ1" otherAttr=" maxlength=\"30\" size=\"10\" ezftoupper=\"\""/></td>
							<td />
							<!-- Branch -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Branch</fmt:message></td>
							<td><ezf:inputText name="svcContrBrDescTxt_SC" ezfName="svcContrBrDescTxt_SC" value="ZZZZZZZZZ1ZZZZZZ" otherAttr=" maxlength=\"50\" size=\"20\" ezftoupper=\"\""/></td>
							<td />
							<!-- LOB -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.5" bundle="${I18N_SCREEN_ID}">LOB</fmt:message></td>
							<td><ezf:inputText name="lineBizTpDescTxt_SC" ezfName="lineBizTpDescTxt_SC" value="ZZZZZZZZZ1ZZZZZZ" otherAttr=" maxlength=\"50\" size=\"10\" ezftoupper=\"\""/></td>
							<td />
						</tr>
					</tbody>
				</table>
				<table style="margin-left: 5px" cellSpacing="0" cellPadding="1" border="0">
					<colgroup>
						<!-- Branch -->
						<col align="left" width="45px">
						<col align="center" width="30px">
						<col width="5px">
						<!-- LOB -->
						<col align="left" width="25px">
						<col align="center" width="100px">
						<col width="90px">
						<!-- Skill Number -->
						<col align="left" width="95px">
						<col align="center" width="45px">
						<col width="5px">
						<!-- Skill Name -->
						<col align="left" width="85px">
						<col align="center" width="160px">
						<col width="5px">
					</colgroup>
					<tbody>
						<tr>
							<!-- Branch -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Branch</fmt:message></td>
							<td><ezf:inputText name="fldSvcBrCd_SC" ezfName="fldSvcBrCd_SC" value="ZZZZ" otherAttr=" maxlength=\"3\" size=\"3\" ezftoupper=\"\""/></td>
							<td />
							<!-- LOB -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.5" bundle="${I18N_SCREEN_ID}">LOB</fmt:message></td>
							<td>
								<ezf:select name="lineBizTpNm_SV" ezfName="lineBizTpNm_SV" ezfBlank="1" ezfCodeName="lineBizTpNm_CD" ezfDispName="lineBizTpNm_NM" />
							</td>
							<td />
							<!-- Skill Number -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Skill Number</fmt:message></td>
							<td><ezf:inputText name="svcSkillNum_SC" ezfName="svcSkillNum_SC" value="ZZZZZ" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/></td>
							<td />
							<!-- Skill Name -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Skill Name</fmt:message></td>
							<td><ezf:inputText name="svcSkillNm_SC" ezfName="svcSkillNm_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" otherAttr=" maxlength=\"30\" size=\"30\" ezftoupper=\"\""/></td>
							<td />
						</tr>
					</tbody>
				</table>
				<table style="margin-left: 5px" cellSpacing="0" cellPadding="1" border="0">
					<colgroup>
						<!-- Tech Code -->
						<col align="left" width="60px">
						<col align="center" width="150px">
						<col width="5px">
						<!-- Tech Name -->
						<col align="left" width="100px">
						<col align="center" width="150px">
						<col width="5px">
						<!-- Training -->
						<col align="left" width="50px">
						<col align="center" width="80px">
						<col width="65px">
						<!-- Search -->
						<col align="center" width="60px">
					</colgroup>
					<tbody>
						<tr>
							<!-- Tech Code -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Tech (*)</fmt:message></td>
							<td><ezf:inputText name="techCd_SC" ezfName="techCd_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2" otherAttr=" maxlength=\"20\" size=\"20\" ezftoupper=\"\""/></td>
							<td />
							<!-- Tech Name -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Tech Name (*)</fmt:message></td>
							<td><ezf:inputText name="techNm_SC" ezfName="techNm_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZ" otherAttr=" maxlength=\"45\" size=\"20\" ezftoupper=\"\""/></td>
							<td />
							<!-- Training -->
							<td><fmt:message key="i18n.NSBL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Training</fmt:message></td>
							<td>
								<ezf:select name="xxScrItem10Txt_SV" ezfName="xxScrItem10Txt_SV" ezfBlank="1" ezfCodeName="xxScrItem10Txt_CD" ezfDispName="xxScrItem10Txt_NM" />
							</td>
							<td />
							<!-- Search -->
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</tbody>
				</table>
<%-- ######################################## TO   (COMMON)HEADER ######################################## --%>
				<hr />
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
<ezf:skip>
				<table width="771px">
					<tr>
						<td align="right">
							<TABLE cellSpacing="0" cellPadding="1" border="0">
								<COLGROUP>
									<COL align="left" width="80">
									<COL align="left" width="80">
									<COL align="left" width="700">
									<COL align="right" width="40">
									<COL align="middle" width="16">
									<COL align="right" width="40">
									<COL align="middle" width="16">
									<COL align="right" width="40">
									<COL width="10">
									<COL width="0">
									<COL width="1">
									<COL width="0">
								</COLGROUP>
								<TBODY>
									<TR>
										<TD></TD>
										<TD></TD>
										<TD>&nbsp;</TD>
										<TD class="stab">Showing</TD>
										<TD class="pOut">1</TD>
										<TD class="stab">to</TD>
										<TD class="pOut">50</TD>
										<TD class="stab">of</TD>
										<TD class="pOut">200</TD>
										<TD>&nbsp;</TD>
										<TD><INPUT class="pBtn3" disabled onclick="sendServer(this)" type="button" value="Prev" name="PagePrev"></TD>
										<TD></TD>
										<TD><INPUT class="pBtn3" onclick="sendServer(this)" type="button" value="Next" name="PageNext"></TD>
									</TR>
								</TBODY>
							</TABLE>
						</td>
					</tr>
				</table>
</ezf:skip>
				<table width="771px">
					<tr>
						<td align="right">
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							</jsp:include>
						</td>
					</tr>
				</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## FROM (COMMON)DETAIL ######################################## --%>
				<center>
					<table width="775px">
						<tr>
							<td align="left">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" ><!-- width="754px" -->
									<colgroup>
										<col align="center" width="25px">	<!-- Tech -->
										<col align="center" width="50px">	<!-- Tech -->
										<col align="center" width="185px">	<!-- Tech Name -->
										<col align="center" width="100px">	<!-- Skill Lv -->
										<col align="center" width="50px">	<!-- Branch Code -->
										<col align="center" width="170px">	<!-- Branch Name -->
										<col align="center" width="170px">	<!-- LOB (Description) -->
									</colgroup>
									<tbody>
										<tr height="19px">
											<td class="pClothBs" rowspan="2" colspan="2">
												<a onclick="columnSort('A', 'techCd_RS')" href="#" class="pSortCol" >
													<fmt:message key="i18n.NSBL0200Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Tech</fmt:message><img id="sortIMG.techCd_RS"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
											<td class="pClothBs" rowspan="2">
												<a onclick="columnSort('A', 'techNm_RS')" href="#" class="pSortCol" >
													<fmt:message key="i18n.NSBL0200Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Tech Name</fmt:message><img id="sortIMG.techNm_RS"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
											<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0200Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Skill Lv</fmt:message></td>
											<td class="pClothBs" colspan="2"><fmt:message key="i18n.NSBL0200Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Branch</fmt:message></td>
											<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0200Scrn00.label.14" bundle="${I18N_SCREEN_ID}">LOB (Description)</fmt:message></td>
										</tr>
										<tr height="18px">
											<td class="pClothBs"><fmt:message key="i18n.NSBL0200Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Code</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0200Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
										</tr>
									</tbody>
								</table>
								<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; height:394px;" >
									<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all">
										<colgroup>
										<col align="center" width="25px">	<!-- Tech -->
										<col align="center" width="50px">	<!-- Tech -->
										<col align="center" width="185px">	<!-- Tech Name -->
										<col align="center" width="100px">	<!-- Skill Lv -->
										<col align="center" width="50px">	<!-- Branch Code -->
										<col align="center" width="170px">	<!-- Branch Name -->
										<col align="center" width="170px">	<!-- LOB (Description) -->
										</colgroup>
										<tbody>
										<ezf:row ezfHyo="A">
											<tr height="28px">
												<td>
													<ezf:inputButton name="SelectTech" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" style=\"width:20px\""/>
												</td>
												<td>
													<ezf:inputText name="techCd_RS" ezfName="techCd_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"6\""/>
												</td>
												<td><ezf:inputText name="techNm_RS" ezfName="techNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"45\" size=\"30\""/></td>
												<td><ezf:inputText name="svcSkillLvlDescTxt_RS" ezfName="svcSkillLvlDescTxt_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"50\" size=\"10\""/></td>
												<td><ezf:label name="svcContrBrCd_RS" ezfName="svcContrBrCd_RS" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="svcContrBrDescTxt_RS" ezfName="svcContrBrDescTxt_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"50\" size=\"20\""/></td>
												<td><ezf:inputText name="lineBizTpDescTxt_RS" ezfName="lineBizTpDescTxt_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"50\" size=\"20\""/></td>
											</tr>
										</ezf:row>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</center>
<%-- ######################################## TO   (COMMON)DETAIL ######################################## --%>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
