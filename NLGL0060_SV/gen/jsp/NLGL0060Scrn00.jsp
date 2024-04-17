<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200117135836 --%>
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
			<input type="hidden" name="pageID" value="NLGL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="WMS Maintenance">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table height="50" width="100%">
						<col valign="top">
						<tr>
							<td>
								<table height="30">
									<col width="80">
									<col width="80" align="left">
									<col width="120">
									<col width="10">

									<col width="80" align="left">
									<col width="80">
									<col width="10">
									<tr>
										<td></td>
										<td class="stab">WH Code</td>
										<td><ezf:inputText name="whCd_H" ezfName="whCd_H" value="3Z" otherAttr=" size=\"4\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Task Code</td>
										<td align="left">
											<ezf:select name="wmsTaskCd_H" ezfName="wmsTaskCd_H" ezfBlank="1" ezfCodeName="wmsTaskCd_P" ezfDispName="wmsTaskNm_P" otherAttr=" style=\"width:80px;\""/>
										</td>
									</tr>
								</table>

								<table height="30">
									<col width="80">
									<col width="80" align="left">
									<col width="120">
									<col width="10">

									<col width="80" align="left">
									<col width="80">
									<col width="10">
									
									<col width="60" align="left">
									<col width="100">
									<col width="230">
									
									<col width="50" align="right">
									<tr>
										<td></td>
										<td class="stab">Process Status</td>
										<td align="left">
											<ezf:select name="procStsCd_H" ezfName="procStsCd_H" ezfBlank="1" ezfCodeName="procStsCd_P" ezfDispName="procStsDescTxt_P" otherAttr=" style=\"width:120px;\""/>
										</td>
										<td></td>
										<td class="stab">Transaction ID</td>
										<td><ezf:inputText name="wmsInbdTrxPk_H" ezfName="wmsInbdTrxPk_H" value="YYYYYY" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Item Number</td>
										<td><ezf:inputText name="wmsMdseCd_H" ezfName="wmsMdseCd_H" value="XXXXXX" otherAttr=" size=\"17\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

								<hr>
<%-- ######################################## FROM (COMMON)PAGENATION ######################################## --%>
					<table style="MARGIN-LEFT: 5px; width: 1108px;">
						<colgroup>
							<col align="left" width="">
							<col align="right" width="">
						</colgroup>
						<tbody>
							<tr>
								<td>
									<div align="right">
										<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col >
												<col width="40" align="right">
												<col width="16" align="center">
												<col width="40" align="right">
												<col width="16" align="center">
												<col width="40" align="right">
												<col width="10">
												<col width="0">
												<col width="1">
												<col width="30">
												<tr>
													<td class="stab">Showing</td>
													<td><input type="text" name="xxPageShowFromNum" value="9999" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowFromNum"></td>
													<td class="stab">to</td>
													<td><input type="text" name="xxPageShowToNum" value="9999" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowToNum"></td>
													<td class="stab">of</td>
													<td><input type="text" name="xxPageShowOfNum" value="9999" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowOfNum"></td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
													<td></td>
												</tr>
											</table>
										</ezf:skip>
										<table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
											<tbody>
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
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
<%--  ####################################### TO (COMMON)PAGENATION ######################### --%>

<%-- ######################################## DETAIL ######################################## --%>

					<%-- ###TAB - BODY --%>
					<table height="357" width="100%">
						<tr valign="top">
							<td>
<div id="parentBoxA">
								<table border="1" cellpadding="0" cellspacing="0" style="margin-left:5px;">
									<tr>

										<td valign="top">
											<%-- id:topTBL --%>
										<div id="topTBL" style="width:1083px; overflow-y:hidden; overflow-x:hidden;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD"width="688">
													<col width="48"  align="center">
													<col width="100"  align="center">
													<col width="150" align="center">
													<col width="150" align="center">
													<col width="55" align="center">
													<col width="70"  align="center">
													<col width="130" align="center">
													<col width="100" align="center">
													<col width="120" align="center">
													<col width="98" align="center">
													<col width="60" align="center">
													<col width="100" align="center">
													<col width="100" align="center">

													<tr height="37">
														<td class="pClothBs">Delete Flag</td>
														<td class="pClothBs">Transaction ID</td>
														<td class="pClothBs">Register Date</td>
														<td class="pClothBs">Updated Date</td>
														<td class="pClothBs">WH Code</td>
														<td class="pClothBs">Task Code</td>
														<td class="pClothBs">Process Status</td>
														<td class="pClothBs">Error Message CD</td>
														<td class="pClothBs">Item Number</td>
														<td class="pClothBs">Serial#</td>
														<td class="pClothBs">Qty</td>
														<td class="pClothBs">Otbd Ord #</td>
														<td class="pClothBs">Inbd Ord #</td>
													</tr>
												</table>
											</div>

											<%-- id:rightTBL --%>
										<div id="rightTBL" style="overflow-y:scroll; height:410; overflow-x:scroll; width:1100; margin:0px; padding:0px;" onScroll="synchroScrollLeft( this.id, new Array( 'topTBL' ) );">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="688" id="A">
													<col width="48"  align="center">
													<col width="100"  align="left">
													<col width="150" align="left">
													<col width="150" align="left">
													<col width="55" align="left">
													<col width="70"  align="left">
													<col width="130" align="center">
													<col width="100" align="left">
													<col width="120" align="left">
													<col width="98" align="center">
													<col width="60" align="right">
													<col width="100" align="left">
													<col width="100" align="left">
														
													<ezf:row ezfHyo="A">
														<tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"OnChange_ChkBoxNum{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:label name="wmsInbdTrxPk_A1" ezfName="wmsInbdTrxPk_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="xxTsDsp19Txt_A1" ezfName="xxTsDsp19Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="xxTsDsp19Txt_A2" ezfName="xxTsDsp19Txt_A2" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="whCd_A1" ezfName="whCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="wmsTaskCd_A1" ezfName="wmsTaskCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td>
																<ezf:select name="procStsCd_A1" ezfName="procStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="procStsCd_P" ezfDispName="procStsDescTxt_P" otherAttr=" style=\"width:120px\""/>
															</td>
															<td><ezf:label name="errMsgCd_A1" ezfName="errMsgCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="wmsMdseCd_A1" ezfName="wmsMdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="SER000001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
															<td><ezf:label name="wmsOrdQty_A1" ezfName="wmsOrdQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="otbdOrdNum_A1" ezfName="otbdOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="inbdOrdNum_A1" ezfName="inbdOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
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
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
