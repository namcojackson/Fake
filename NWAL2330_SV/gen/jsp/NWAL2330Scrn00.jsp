<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160411171011 --%>
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
			<input type="hidden" name="pageID" value="NWAL2330Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Original Order Search">
			<table width="1010" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div class="pTab_BODY">
							<table cellpadding="0" border="0">
								<col width="10">
								<col width="60"  align="left">
								<col width="120" align="left">
								<col width="90" align="left">
								<col width="160" align="left">
								<col width="60"  align="left">
								<col width="150" align="left">
								<col width="80"  align="left">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">Order#(*)</td>
									<td><ezf:inputText name="cpoOrdNum_H1" ezfName="cpoOrdNum_H1" otherAttr=" size=\"13\" maxlength=\"8\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" >Sold To Name(*)</ezf:anchor></td>
									<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" >Number(*)</ezf:anchor></td>
									<td><ezf:inputText name="sellToCustCd_H1" ezfName="sellToCustCd_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class="stab">Order Source</td>
									<td>
										<ezf:select name="cpoSrcTpCd_H1" ezfName="cpoSrcTpCd_H1" ezfBlank="1" ezfCodeName="cpoSrcTpCd_L1" ezfDispName="cpoSrcTpDescTxt_L1" />
									</td>
								</tr>
							</table>
							<table cellpadding="0" border="0">
								<col width="10">
								<col width="60"  align="left">
								<col width="120" align="left">
								<col width="90" align="left">
								<col width="160" align="left">
								<col width="60"  align="left">
								<col width="150" align="left">
								<col width="80"  align="left">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">SO#(*)</td>
									<td><ezf:inputText name="soNum_H1" ezfName="soNum_H1" otherAttr=" size=\"13\" maxlength=\"8\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" >Bill To Name(*)</ezf:anchor></td>
									<td><ezf:inputText name="dsAcctNm_H2" ezfName="dsAcctNm_H2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" >Number(*)</ezf:anchor></td>
									<td><ezf:inputText name="billToCustAcctCd_H1" ezfName="billToCustAcctCd_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class="stab">Order Catagory</td>
									<td>
										<ezf:select name="dsOrdCatgCd_H1" ezfName="dsOrdCatgCd_H1" ezfBlank="1" ezfCodeName="dsOrdCatgCd_L1" ezfDispName="dsOrdCatgDescTxt_L1" otherEvent1=" onchange=\"sendServer('Select_OrderCategory')\"" />
									</td>
								</tr>
							</table>
							<table cellpadding="0" border="0">
								<col width="10">
								<col width="60"  align="left">
								<col width="120" align="left">
								<col width="90" align="left">
								<col width="160" align="left">
								<col width="60"  align="left">
								<col width="150" align="left">
								<col width="80"  align="left">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">Invoice#(*)</td>
									<td><ezf:inputText name="invNum_H1" ezfName="invNum_H1" otherAttr=" size=\"13\" maxlength=\"8\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Ship To Name(*)</ezf:anchor></td>
									<td><ezf:inputText name="dsAcctNm_H3" ezfName="dsAcctNm_H3" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Number(*)</ezf:anchor></td>
									<td><ezf:inputText name="shipToCustAcctCd_H1" ezfName="shipToCustAcctCd_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class="stab">Order Reason</td>
									<td>
										<ezf:select name="dsOrdTpCd_H1" ezfName="dsOrdTpCd_H1" ezfBlank="1" ezfCodeName="dsOrdTpCd_L1" ezfDispName="dsOrdTpDescTxt_L1" />
									</td>
								</tr>
							</table>
							<table cellpadding="0" border="0">
								<col width="10">
								<col width="60" align="left">
								<col width="100" align="left">
								<col width="10" align="left">
								<col width="100" align="left">
								<col width="20" align="left">
								<col width="60" align="left">
								<col width="100" align="left">
								<col width="10">
								<col width="100" align="left">
								<col width="100" align="left">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">Order Date</td>
									<td>
										<ezf:inputText name="ordDt_H1" ezfName="ordDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('ordDt_H1', 4);" >
									</td>
									<td>-</td>
									<td>
										<ezf:inputText name="ordDt_H2" ezfName="ordDt_H2" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('ordDt_H2', 4);" >
									</td>
									<td>&nbsp;</td>
									<td class="stab">Invoice Date</td>
									<td>
										<ezf:inputText name="invDt_H1" ezfName="invDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_H1', 4);" >
									</td>
									<td>-</td>
									<td>
										<ezf:inputText name="invDt_H2" ezfName="invDt_H2" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_H2', 4);" >
									</td>
								</tr>
							</table>
							<table cellpadding="0" border="0">
								<col width="900" align="left">
								<col width="100" align="left">
								<tr>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn4" /></td>
								</tr>
							</table>
							<%-- Left Tbl Header--%>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr valign="top">
									<td width="10">&nbsp;</td>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col align="center" width="100">
											<col align="center" width="100">
											<col align="center" width="100">
											<tr height="36">
												<td class="pClothBs">Order#</td>
												<td class="pClothBs">Invoice#</td>
												<td class="pClothBs">SO#</td>
											</tr>
										</table>
										<div id="LeftTable" style="overflow-y:hidden; overflow-x:hidden; height:369" onScroll="synchroScrollTop(this.id, new Array('RightTable'));">
											<table border="1" cellpadding="1" cellspacing="0" id="A1" style="table-layout: fixed">
												<col align="center" width="100">
												<col align="center" width="100">
												<col align="center" width="100">
												<ezf:row ezfHyo="A">
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><ezf:anchor name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_OrderNum" ><ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:label name="invNum_A1" ezfName="invNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
													<tr id="id_row${EZF_ROW_NUMBER}" height="23">
														<td><a href="#" onclick="sendServer('Select_OrderNum')" ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A"></label></a></td>
														<td><label ezfout name="invNum_A1" ezfname="invNum_A1" ezfhyo="A">600101</label></td>
														<td><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SH0001</label></td>
													</tr>
												</ezf:skip>
											</table>
										</div>
									</td>
									<td>	
										<div id="RightTable_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:626" onScroll="synchroScrollLeft(this.id, new Array('RightTable'));">
											<table border="1" cellpadding="1" cellspacing="0" width="1500" style="table-layout: fixed;">
												<col align="center" width="100">
												<col align="center" width="100">
												<col align="center" width="212">
												<col align="center" width="212">
												<col align="center" width="212">
												<col align="center" width="100">
												<col align="center" width="200">
												<col align="center" width="200">
												<col align="center" width="200">
												<tr height="36">
													<td class="pClothBs">Order Date</td>
													<td class="pClothBs">Invoice Date</td>
													<td class="pClothBs">Sold To</td>
													<td class="pClothBs">Bill To</td>
													<td class="pClothBs">Ship To</td>
													<td class="pClothBs">Amount</td>
													<td class="pClothBs">Order Source</td>
													<td class="pClothBs">Order Category</td>
													<td class="pClothBs">Order Reason</td>
												</tr>
											</table>
										</div>
										<div id="RightTable" style="overflow-y:scroll; overflow-x:scroll; width:643; height:385" onScroll="synchroScrollTop(this.id, new Array('LeftTable')); synchroScrollLeft(this.id, new Array('RightTable_Top'));">
											<table border="1" cellpadding="1" cellspacing="0" id="A2" width="1500" style="table-layout: fixed;">
												<col align="center" width="100">
												<col align="center" width="100">
												<col align="center" width="212">
												<col align="center" width="212">
												<col align="center" width="212">
												<col align="right"  width="100">
												<col align="center" width="200">
												<col align="center" width="200">
												<col align="center" width="200">
												<ezf:row ezfHyo="A">
													<tr height="23">
														<td><ezf:label name="ordDt_A1" ezfName="ordDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="invDt_A1" ezfName="invDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"30\""/></td>
														<td><ezf:inputText name="dsAcctNm_A2" ezfName="dsAcctNm_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"30\""/></td>
														<td><ezf:inputText name="dsAcctNm_A3" ezfName="dsAcctNm_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"30\""/></td>
														<td><ezf:label name="invTotFuncNetAmt_A1" ezfName="invTotFuncNetAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="cpoSrcTpDescTxt_A1" ezfName="cpoSrcTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"30\""/></td>
														<td><ezf:inputText name="dsOrdCatgDescTxt_A1" ezfName="dsOrdCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"30\""/></td>
														<td><ezf:inputText name="dsOrdTpDescTxt_A1" ezfName="dsOrdTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"30\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
													<tr height="23">
														<td><label ezfout name="ordDt_A1" ezfname="ordDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A">12/31/2016</label></td>
														<td><input type="text" name="dsAcctNm_A1" ezftoupper="" ezfname="dsAcctNm_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A2" ezftoupper="" ezfname="dsAcctNm_A2" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsAcctNm_A3" ezftoupper="" ezfname="dsAcctNm_A3" ezfhyo="A" size="30"></td>
														<td><label ezfout name="invTotFuncNetAmt_A1" ezfname="invTotFuncNetAmt_A1" ezfhyo="A">200,000,00</label></td>
														<td><input type="text" name="cpoSrcTpDescTxt_A1" ezftoupper="" ezfname="cpoSrcTpDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdCatgDescTxt_A1" ezftoupper="" ezfname="dsOrdCatgDescTxt_A1" ezfhyo="A" size="30"></td>
														<td><input type="text" name="dsOrdTpDescTxt_A1" ezftoupper="" ezfname="dsOrdTpDescTxt_A1" ezfhyo="A" size="30"></td>
													</tr>
												</ezf:skip>
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
