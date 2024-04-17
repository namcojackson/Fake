<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160907135656 --%>
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
			<input type="hidden" name="pageID" value="NWAL1800Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Check Contract Popup">


			<center>
			<table border="0" cellpadding="0" width="710">
				<div class="pTab_BODY">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" >
								<col align="center">
								<tr>
									<td>
										<br>
										Customer has the following on a current supplies included contract.
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
						<table border="0" cellpadding="0" width="710">
							<tr align="right">
								<td>
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
						<ezf:skip>
						<table border="0" cellpadding="0" width="710">
							<tr>
								<td align="left">
									<table border="0" cellpadding="0" align="left" cellspacing="0">
										<col>
											<tr>
												<td><br></td>
											</tr>
									</table>
								</td>
								<td align="right">
									<table border="0" cellpadding="0" cellspacing="0">
										<col width="54" align="center">
										<col width="40" align="center">
										<col width="16" align="center">
										<col width="40" align="center">
										<col width="26" align="center">
										<col width="10">
										<col width="20">
										<col>
										<col width="1">
										<col>
										<tr>
											<td class="stab">Showing</td>
											<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
											<td class="stab">to</td>
											<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
											<td class="stab">of</td>
											<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
											
											<td></td>
											<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						</ezf:skip>
						</td>
					</tr>
<%-- ######################################## DETAIL ######################################## --%>
					<tr>
                        <td>
						<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
							<col align="center" width="220">
							<col align="center" width="120">
							<col align="center" width="220">
							<col align="center" width="150">
							<tr height="22">
								<td class="pClothBs">Contract#</td>
								<td class="pClothBs" align="center">Item#</td>
								<td class="pClothBs" align="center">Description</td>
								<td class="pClothBs" align="center">Schedule</td>
							</tr>
						</table>
					
						<div style="width:727;overflow-y:scroll; height:474">
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
								<col align="left" width="220">
								<col align="left" width="120">
								<col align="left" width="220">
								<col align="left" width="150">
								<ezf:row ezfHyo="A">
								<tr height="22">
									<td><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="schdAgmtNum_A" ezfName="schdAgmtNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
								</tr>
								</ezf:row>
								<ezf:skip>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>123456789012345678901234567890</td>
									<td>1234567890123456</td>
									<td>123456789012345678901234567890</td>
									<td>12345678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>AAA456789012345678901234567890</td>
									<td>BBB4567890123456</td>
									<td>CCC456789012345678901234567890</td>
									<td>DDD45678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>AAA456789012345678901234567890</td>
									<td>BBB4567890123456</td>
									<td>CCC456789012345678901234567890</td>
									<td>DDD45678901234567890</td>
								</tr>
								<tr style="height:25">
									<td>AAA456789012345678901234567890</td>
									<td>BBB4567890123456</td>
									<td>CCC456789012345678901234567890</td>
									<td>DDD45678901234567890</td>
								</tr>
								</ezf:skip>
							</table>
						</div>
						</td>
					</tr>
				</div>
			</table>
			</center>



<%-- **** Task specific area ends here **** --%>
