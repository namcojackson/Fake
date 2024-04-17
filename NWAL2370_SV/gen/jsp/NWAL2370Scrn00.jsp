<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180425140457 --%>
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
			<input type="hidden" name="pageID" value="NWAL2370Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Rental Shell Detail Popup">
				<table>
					<tr>
						<td>
							<table cellpadding="2" border="1" cellspacing="0" style="margin-bottom:4px;">
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<tr height="">
									<td class="pClothBs">Maintenance Shell#</td>
									<td class="pOut"><ezf:inputText name="shellLineNum" ezfName="shellLineNum" value="999" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
									<td class="stab">Term From/To/Mon</td>
									<td>
										<ezf:inputText name="fromPerMthNum" ezfName="fromPerMthNum" value="1" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/><ezf:inputText name="toPerMthNum" ezfName="toPerMthNum" value="12" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
										<ezf:inputText name="termMthAot" ezfName="termMthAot" value="12" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
									</td>
									<td class="pClothBs">Config#</td>
									<td class="pOut"><ezf:inputText name="dsOrdPosnNum" ezfName="dsOrdPosnNum" value="1" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\" style=\"border:0;background-color:transparent;\""/></td>
									<td class="pClothBs">Model</td>
									<td class="pOut"><ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="OCEPW345/365" otherAttr=" size=\"15\" maxlength=\"90\" ezftoupper=\"\" style=\"border:0;background-color:transparent;\""/></td>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px;">
								<col align="left" valign="top">
								<tr>
									<td>
										<div>
											<table border="1" cellpadding="1" cellspacing="0" style="width:980; table-layout:fixed;">
												<col width="40"  align="center"><!--Line#-->
												<col width="70" align="center"><!--Item#-->
												<col width="160" align="center"><!--Description-->
												<col width="70"  align="center"><!--# of Payments-->
												<col width="110" align="center"><!--Frequency-->
												<col width="90" align="center"><!--Rental Payment<-->
												<col width="90" align="center"><!--Rental Equip-->
												<col width="90" align="center"><!--Rental Service-->
												<col width="90" align="center"><!--Addl Charge-->
												<col width="160" align="center"><!--Bundle Name-->
												<tr height="48">
													<td class="pClothBs">Line#</td>
													<td class="pClothBs">Item#</td>
													<td class="pClothBs">Description</td>
													<td class="pClothBs"># of Payments</td>
													<td class="pClothBs">Frequency</td>
													<td class="pClothBs">Rental Payment</td>
													<td class="pClothBs">Rental</td>
													<td class="pClothBs">Service</td>
													<td class="pClothBs">Addl Charge</td>
													<td class="pClothBs">Bundle Name</td>
												</tr>
											</table>
										</div>
										<div style="width:998; height:458px; overflow-y:auto; overflow-x:auto;">
											<table id="A" border="1" cellpadding="1" cellspacing="0" style="width:980; table-layout:fixed;">
												<col width="40"  align="left"><!--Line#-->
												<col width="70" align="left"><!--Item#-->
												<col width="160" align="left"><!--Description-->
												<col width="70"  align="right"><!--# of Payments-->
												<col width="110" align="left"><!--Frequency-->
												<col width="90" align="right"><!--Rental Payment<-->
												<col width="90" align="right"><!--Rental Equip-->
												<col width="90" align="right"><!--Rental Service-->
												<col width="90" align="right"><!--Addl Charge-->
												<col width="160" align="left"><!--Bundle Name-->
												<tbody>
												<ezf:row ezfHyo="A">
													<tr id="id_A_row{EZF_ROW_NUMBER}">
														<td><ezf:inputText name="xxLineNum_A1" ezfName="xxLineNum_A1" value="1.2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="2592B001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="PCL PRINTER KIT-AB1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"250\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxFreqCycleCnt_A1" ezfName="xxFreqCycleCnt_A1" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;padding:0px;\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="bllgCycleDescTxt_A1" ezfName="bllgCycleDescTxt_A1" value="SY - Semiannual" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxTotAmt_PY" ezfName="xxTotAmt_PY" value="22.33" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;padding:0px;\""/></td>
														<td><ezf:inputText name="xxTotAmt_EQ" ezfName="xxTotAmt_EQ" value="22.33" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;padding:0px;\""/></td>
														<td><ezf:inputText name="xxTotAmt_SV" ezfName="xxTotAmt_SV" value="22.33" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;padding:0px;\""/></td>
														<td><ezf:inputText name="xxTotAmt_AC" ezfName="xxTotAmt_AC" value="22.33" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;padding:0px;\""/></td>
														<td><ezf:inputText name="prcListEquipConfigNm_A1" ezfName="prcListEquipConfigNm_A1" value="WWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
												</ezf:row>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>


<%-- **** Task specific area ends here **** --%>
