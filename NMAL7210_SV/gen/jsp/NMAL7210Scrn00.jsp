<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160125132916 --%>
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

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NMAL7210Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Formula Search">
			<table width="1133" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<%-- ###TAB - HEAD --%>
						<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
						<ezf:skip>
						<div class="pTab_HEAD">
							<ul>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="96%">
											<div>
												<li title="Price Adjustment Table" class="pTab_ON"><a href="#">Prc Fmla Search</a></li>
											</div>
										</td>
									</tr>
								</table>
							</ul>
						</div>
						</ezf:skip>
						<%------------------------------------%>
						<%-- Header							--%>
						<%------------------------------------%>
						<div class="pTab_BODY">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top">
											<table cellpadding="0" border="0">
											<col width="120" align="left">
											<col width="120" align="left">
											<col width="200">
											<col width="100"  align="left">
											<col width="120"   align="left">
											<tr>
												<td class="stab">Formula Name(*)</td>
												<td><ezf:inputText name="prcFmlaNm_H1" ezfName="prcFmlaNm_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
												<td>&nbsp;</td>
												<td class="stab">Effective Date From</td>
												<td>
													<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td valign="top">
										<table cellpadding="0" border="0">
											<col width="120" align="left">
											<col width="120" align="left">
											<col width="200">
											<col width="100" align="left">
											<col width="120" align="left">
											<tr>
												<td class="stab">Formula Description(*)</td>
												<td><ezf:inputText name="prcFmlaDescTxt_H1" ezfName="prcFmlaDescTxt_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
												<td>&nbsp;</td>
												<td class="stab">Effective Date To</td>
												<td>
													<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td valign="top">
										<table cellpadding="0" border="0">
											<col width="120" align="left">
											<col width="120" align="left">
											<col width="440" align="left">
											<col width="100" align="left">
											<col width="100" align="left">
											<tr>
												<td class="stab">Formula Type</td>
												<td>
													<ezf:select name="prcFmlaTpCd_H1" ezfName="prcFmlaTpCd_H1" ezfBlank="1" ezfCodeName="prcFmlaTpCd_L1" ezfDispName="prcFmlaTpNm_L1" />
												</td>
												<td>&nbsp;</td>
												<td class="stab">Active</td>
												<td><ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" /></td>
												<td>&nbsp;</td>
												<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
							<hr style="height: 0px;" cellpadding="0">
							<%------------------------------------%>
							<%-- Detail							--%>
							<%------------------------------------%>
							<table border="0">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>&nbsp;</td>
												<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:hidden; float:fixed;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed; style="margin-right:20px">
														<col align="center" width="30">
														<col align="center" width="350">
														<col align="center" width="350">
														<col align="center" width="160">
														<col align="center" width="100">
														<col align="center" width="100">
														<tr height="18">
															<td class="pClothBs">&nbsp;</td>
															<td class="pClothBs">Formula Name</td>
															<td class="pClothBs">Formula Description</td>
															<td class="pClothBs">Formula Type</td>
															<td class="pClothBs">Date From</td>
															<td class="pClothBs">Date To</td>
														</tr>
													</table>
												</div>
												<div id="LeftTable_A" style="overflow-y:scroll; overflow-x:none; height:400; width:1100;">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<col align="center" width="30">
														<col align="center" width="350">
														<col align="center" width="350">
														<col align="center" width="160">
														<col align="center" width="100">
														<col align="center" width="100">
														<ezf:row ezfHyo="A">
															<tr id="id_leftA_row{EZF_ROW_NUMBER}">
																<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="N" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
																<td><ezf:inputText name="prcFmlaNm_A1" ezfName="prcFmlaNm_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="prcFmlaDescTxt_A1" ezfName="prcFmlaDescTxt_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
																<td><ezf:label name="prcFmlaTpNm_A1" ezfName="prcFmlaTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															</tr>
														</ezf:row>
													</table>
												</div>
											</tr>
										</table>
										<table border="0">
											<col align="left" width="900">
											<tr>
												<td>&nbsp;</td>
												<td>
													<ezf:inputButton name="ViewFormula" value="View Formula" htmlClass="pBtn7" />
													<ezf:inputButton name="NewFormula" value="New Formula" htmlClass="pBtn7" />
												</td>
											</tr>
										</table>
										
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
