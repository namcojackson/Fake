<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160311115818 --%>
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
			<input type="hidden" name="pageID" value="NMAL7220Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Formula Entry">
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
						<div class="pTab_BODY">
							<table cellpadding="0" border="0">
								<col width="30">
								<col width="120" align="left">
								<col width="230" align="left">
								<col width="30">
								<col width="10" align="left">
								<col width="25" align="left">
								<col width="100" align="left">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">Formula ID</td>
									<td><ezf:inputText name="prcFmlaPk_H1" ezfName="prcFmlaPk_H1" otherAttr=" size=\"28\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
									<td>&nbsp;</td>
									<td class="stab">Formula Name</td>
									<td><ezf:inputText name="prcFmlaNm_H1" ezfName="prcFmlaNm_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
								</tr>
							</table>
							<table cellpadding="0" border="0">
								<col width="30">
								<col width="120" align="left">
								<col width="180" align="left">
								<col width="23" align="left">
								<col width="100" align="left">
								<col width="100" align="left">
								<col width="5" align="left">
								<col width="90" align="left">
								<col width="100" align="left">
								<col width="5" align="left">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">Formula Description</td>
									<td><ezf:inputText name="prcFmlaDescTxt_H1" ezfName="prcFmlaDescTxt_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Effective Date From</td>
									<td>
										<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
									</td>
									<td>&nbsp;</td>
									<td class="stab">Effective Date To</td>
									<td>
										<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
									</td>
									<td>&nbsp;</td>
									<td class="stab">Active</td>
									<td><ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" /></td>
								</tr>
							</table>
							<hr>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="30">&nbsp;</td>
									<td>
										<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; float:fixed;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed; style="margin-right:20px">
												<col align="center" width="150">
												<col align="center" width="420">
												<col align="center" width="120">
												<col align="center" width="100">
												<col align="center" width="180">
												<tr height="36">
													<td class="pClothBs">Formula Type</td>
													<td class="pClothBs">Source Price List</td>
													<td class="pClothBs">Operator</td>
													<td class="pClothBs">Factor Value</td>
													<td class="pClothBs">Custom Price<br>Function Name</td>
												</tr>
											</table>
										</div>
										<div id="LeftTable_A" style="overflow-y:none; overflow-x:none; height:400; width:1100;">
											<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
												<col align="center" width="150">
												<col align="center" width="420">
												<col align="center" width="120">
												<col align="center" width="100">
												<col align="center" width="180">
												<ezf:row ezfHyo="A">
													<tr id="id_leftA_row{EZF_ROW_NUMBER}">
														<td>
															<ezf:select name="prcFmlaTpCd_A1" ezfName="prcFmlaTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcFmlaTpCd_L1" ezfDispName="prcFmlaTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_Formula_Type')\"" />
														</td>
														<td>
															<ezf:inputButton name="OpenWin_Price_List" value="List" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"OpenWin_Price_List\""/>
															<ezf:inputText name="prcCatgNm_A1" ezfName="prcCatgNm_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"75\" ezftoupper=\"\""/>
														</td>
														<td>
															<ezf:select name="prcFmlaOpCd_A1" ezfName="prcFmlaOpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcFmlaOpCd_L1" ezfDispName="prcFmlaOpNm_L1" />
														</td>
														<td><ezf:inputText name="prcFmlaNum_A1" ezfName="prcFmlaNum_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
														<td>
															<ezf:select name="prcFuncTpCd_A1" ezfName="prcFuncTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcFuncTpCd_L1" ezfDispName="prcFuncTpNm_L1" />
														</td>
													</tr>
												</ezf:row>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>

<%-- **** Task specific area ends here **** --%>
