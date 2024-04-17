<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20090916032709 --%>
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
<fmt:setBundle basename="I18N_NLCL0230Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NLCL0230Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NLCL0230Scrn00.title" bundle="${I18N_SCREEN_ID}">Location Search Popup</fmt:message>">
			
<center>
	<table>
		<tr>
			<td>
				
<%-- ######################################## HEADER ######################################## --%>
					<table>
						<col width="50">
						<col width="96">
						<col width="240">
						<tr>
							<td class="stab"><fmt:message key="i18n.NLCL0230Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Item Num</fmt:message></td>
							<td class="pOut"><ezf:label name="mdseCd" ezfName="mdseCd" /></td>
							<td class="pOut"><ezf:label name="mdseNm" ezfName="mdseNm" /></td>
						</tr>
					</table>
					<table>
						<col width="49">
						<col width="24">
						<col width="5">
						<col width="55">
						<col width="72">
						<col width="5">
						<col width="59">
						<col width="248">
						<col width="">
						<tr>
							<td class="stab"><fmt:message key="i18n.NLCL0230Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Loc TP</fmt:message></td>
							<td>
								<ezf:select name="invtyLocTpCd_P1" ezfName="invtyLocTpCd_P1" ezfCodeName="invtyLocTpCd" ezfDispName="xxEdtCdNm" otherAttr=" style=\"width:150px;\""/>
							</td>
							<td></td>
							<td class="stab"><fmt:message key="i18n.NLCL0230Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Loc Cd (*)</fmt:message></td>
							<td><ezf:inputText name="invtyLocCd" ezfName="invtyLocCd" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td></td>
							<td class="stab"><fmt:message key="i18n.NLCL0230Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Loc Nm (*)</fmt:message></td>
							<td><ezf:inputText name="invtyLocNm" ezfName="invtyLocNm" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
							<td><ezf:inputButton name="Search_Inventory" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
				<table width="534" align="center">
					<tr><td>
						<table border="1" cellpadding="1" cellspacing="0" width="534">
							<col width="144" align="center">
							<col width="108" align="center">
							<col width="240" align="center">
							<tr>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invtyLocTpNm_A1')"><fmt:message key="i18n.NLCL0230Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Location Type Name</fmt:message><img id="sortIMG.invtyLocTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invtyLocCd_A1')"><fmt:message key="i18n.NLCL0230Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Location Code</fmt:message><img id="sortIMG.invtyLocCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invtyLocNm_A1')"><fmt:message key="i18n.NLCL0230Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Location Name</fmt:message><img id="sortIMG.invtyLocNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							</tr>
						</table>
						
						<div style="overflow:auto; height:402; width:551;">
							<table border="1" cellpadding="1" cellspacing="0" id="A" width="534">
								<col width="144">
								<col width="108">
								<col width="240">
								<tbody>
									<ezf:row ezfHyo="A">
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><ezf:label name="invtyLocTpNm_A1" ezfName="invtyLocTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Location_Code" ><ezf:label name="invtyLocCd_A1" ezfName="invtyLocCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
											<td><ezf:label name="invtyLocNm_A1" ezfName="invtyLocNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="locTpNm" ezfname="locTpNm" ezfhyo="A">WWWWWWWWW1WWWWW</label></td>
											<td><a href="#"  ezfhyo="A" onclick="sendServer('Select_ConditionCode')"><label ezfout name="invtyLocCd_A1" ezfname="invtyLocCd_A1" ezfhyo="A">WWWWWWWW</label></a></td>
											<td><label ezfout name="invtyLocNm_A1" ezfname="invtyLocNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
										</tr>
										
										<tr>
											<td><label ezfout name="locTpNm" ezfname="locTpNm" ezfhyo="A">WWWWWWWWW1WWWWW</label></td>
											<td><a href="#"  ezfhyo="A" onclick="sendServer('Select_ConditionCode')"><label ezfout name="invtyLocCd_A1" ezfname="invtyLocCd_A1" ezfhyo="A">WWWWWWWW</label></a></td>
											<td><label ezfout name="invtyLocNm_A1" ezfname="invtyLocNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="locTpNm" ezfname="locTpNm" ezfhyo="A">WWWWWWWWW1WWWWW</label></td>
											<td><a href="#"  ezfhyo="A" onclick="sendServer('Select_ConditionCode')"><label ezfout name="invtyLocCd_A1" ezfname="invtyLocCd_A1" ezfhyo="A">WWWWWWWW</label></a></td>
											<td><label ezfout name="invtyLocNm_A1" ezfname="invtyLocNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
										</tr>
										
										<tr>
											<td><label ezfout name="locTpNm" ezfname="locTpNm" ezfhyo="A">WWWWWWWWW1WWWWW</label></td>
											<td><a href="#"  ezfhyo="A" onclick="sendServer('Select_ConditionCode')"><label ezfout name="invtyLocCd_A1" ezfname="invtyLocCd_A1" ezfhyo="A">WWWWWWWW</label></a></td>
											<td><label ezfout name="invtyLocNm_A1" ezfname="invtyLocNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="locTpNm" ezfname="locTpNm" ezfhyo="A">WWWWWWWWW1WWWWW</label></td>
											<td><a href="#"  ezfhyo="A" onclick="sendServer('Select_ConditionCode')"><label ezfout name="invtyLocCd_A1" ezfname="invtyLocCd_A1" ezfhyo="A">WWWWWWWW</label></a></td>
											<td><label ezfout name="invtyLocNm_A1" ezfname="invtyLocNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
										</tr>
										
										<tr>
											<td><label ezfout name="locTpNm" ezfname="locTpNm" ezfhyo="A">WWWWWWWWW1WWWWW</label></td>
											<td><a href="#"  ezfhyo="A" onclick="sendServer('Select_ConditionCode')"><label ezfout name="invtyLocCd_A1" ezfname="invtyLocCd_A1" ezfhyo="A">WWWWWWWW</label></a></td>
											<td><label ezfout name="invtyLocNm_A1" ezfname="invtyLocNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
										</tr>

										<tr class="pEvenNumberBGcolor">
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>

										<tr class="pEvenNumberBGcolor">
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>

										<tr class="pEvenNumberBGcolor">
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>

										<tr class="pEvenNumberBGcolor">
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
										
										<tr>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
											<td><label ezfout>&nbsp;</label></td>
										</tr>
									</ezf:skip>
								</tbody>
							</table>
						</div>
					<tr><td>
				</table>
				
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
