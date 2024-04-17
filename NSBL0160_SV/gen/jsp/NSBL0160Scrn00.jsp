<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530175834 --%>
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
			<input type="hidden" name="pageID" value="NSBL0160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Memo Entry">
<center>
	<table cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1070px" height="28px" valign="bottom">
							<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
								<tr title="Memo Entry Screen">
									<td width="107px" align="center" class="same">Memo Entry</td>
								</tr>
							</table>
						</td>
						<td width="10px" valign="bottom" align="center">
							<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0" onclick="prevTabPage()"></a>
						</td>
						<td width="10px" valign="bottom" align="center">
							<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0" onclick="nextTabPage()"></a>
						</td>
					</tr>
				</table>
				</ezf:skip>
				<div class="pTab_BODY">
<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table style="margin-top: 0px; margin-left: 5px" border="0">
						<tr>
							<td>
								<table cellSpacing="0" cellPadding="2" border="1">
									<colgroup>
										<ezf:row ezfHyo="A">
										<col align="middle">
										</ezf:row>
										<ezf:skip>
										<col align="middle" width="215px">
										<col align="middle" width="215px">
										<col align="middle" width="215px">
										<col align="middle" width="215px">
										</ezf:skip>
									</colgroup>
									<tbody>
										<tr height="28">
											<ezf:row ezfHyo="A">
											<td class="pClothBs"><ezf:label name="xxComnScrColLbTxt_HD" ezfName="xxComnScrColLbTxt_HD" ezfHyo="A" ezfArrayNo="0" /></td>
											</ezf:row>
											<ezf:skip>
											<td class="pClothBs"><label name="xxComnScrColLbTxt_HD" ezfname="xxComnScrColLbTxt_HD" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											<td class="pClothBs"><label name="xxComnScrColLbTxt_HD" ezfname="xxComnScrColLbTxt_HD" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											<td class="pClothBs"><label name="xxComnScrColLbTxt_HD" ezfname="xxComnScrColLbTxt_HD" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											<td class="pClothBs"><label name="xxComnScrColLbTxt_HD" ezfname="xxComnScrColLbTxt_HD" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											</ezf:skip>
										</tr>
										<tr height="28">
											<ezf:row ezfHyo="A">
											<td><ezf:label name="xxComnScrColLbTxt_LB" ezfName="xxComnScrColLbTxt_LB" ezfHyo="A" ezfArrayNo="0" /></td>
											</ezf:row>
											<ezf:skip>
											<td><label name="xxComnScrColLbTxt_LB" ezfname="xxComnScrColLbTxt_LB" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											<td><label name="xxComnScrColLbTxt_LB" ezfname="xxComnScrColLbTxt_LB" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											<td><label name="xxComnScrColLbTxt_LB" ezfname="xxComnScrColLbTxt_LB" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											<td><label name="xxComnScrColLbTxt_LB" ezfname="xxComnScrColLbTxt_LB" ezfhyo="A" ezfout>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3</label></td>
											</ezf:skip>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellSpacing="0" cellPadding="1" border="0">
									<colgroup>
										<col width="105px">
										<col width="245px">
										<col width="33px">
										<col width="65px">
										<col width="100px">
										<col width="120px">
										<col width="20px">
										<col width="120px">
										<col width="5px">
										<col width="80px">
										<col width="175px">
										<col width="5px">
									</colgroup>
									<tbody>
										<tr>
											<td>Reason Code</td>
											<td>
												<ezf:select name="svcMemoRsnCd_SC" ezfName="svcMemoRsnCd_SC" ezfBlank="1" ezfCodeName="svcMemoRsnCd_SP" ezfDispName="svcMemoRsnDescTxt_SP" otherAttr=" style=\"width:240px;\""/>
											</td>
											<td></td>
											<td><ezf:inputButton name="Add_Cmnt" value="Add" htmlClass="pBtn6" otherAttr=" id=\"Add_Cmnt\""/></td>
											<td></td>
											<td>
											</td>
											<td></td>
											<td>
											</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->
<!-- ################################################## Search Result   - [START] ################################################## -->
					<table style="margin-top: 0px; margin-left: 5px" border="0" width="1095px">
						<tr>
							<td>
								<table border="0" width="100%">
									<colgroup>
										<col width="100px">
										<col width="120px">
										<col width="20px">
										<col width="120px">
										<col width="5px">
										<col width="65px">
										<col width="270px">
										<col>
									</colgroup>
									<tr>
										<td>Date Updated</td>
										<td>
											<ezf:inputText name="xxFromDt_SC" ezfName="xxFromDt_SC" value="09/15/2014" otherAttr=" size=\"12\" maxlength=\"12\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_SC', 4);">
										</td>
										<td>-</td>
										<td>
											<ezf:inputText name="xxThruDt_SC" ezfName="xxThruDt_SC" value="09/15/2014" otherAttr=" size=\"12\" maxlength=\"12\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_SC', 4);">
										</td>
										<td>&nbsp;</td>
										<td>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/>
										</td>
										<td></td>
										<td  align="right">
											<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col >
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col width="0">
														<col width="1">
														<col width="0">
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">3</td>
															<td class="stab">of</td>
															<td class="pOut">1000</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
														</tr>
													</table>
											</ezf:skip>

											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="B" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="1" cellpadding="1" cellspacing="0" width="1096px" style="table-layout: fixed">
									<colgroup>
										<col align="center" width="140px">
										<col align="center" width="148px">
										<col align="center" width="90px">
										<col align="center" width="250px">
										<col align="center" width="360px">
									</colgroup>
									<tbody>
										<tr>
											<td class="pClothBs">Date Updated</td>
											<td class="pClothBs">Who Created</td>
											<td class="pClothBs">Manual</td>
											<td class="pClothBs">Reason Code</td>
											<td class="pClothBs">Notes</td>
										</tr>
									</tbody>
								</table>
								<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; width:1113px; height:380px;">
									<table border="1" cellpadding="1" cellspacing="0" id="B" width="1096px" style="table-layout: fixed">
										<colgroup>
											<col align="center" width="140px">
											<col align="center" width="148px">
											<col align="center" width="90px">
											<col align="center" width="250px">
											<col align="center" width="360px">
										</colgroup>
										<tbody>
											<ezf:row ezfHyo="B">
												<tr>
													<td><ezf:label name="xxTsDsp19Txt_B" ezfName="xxTsDsp19Txt_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="fullPsnNm_B" ezfName="fullPsnNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="xxScrItem40Txt_B" ezfName="xxScrItem40Txt_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="svcMemoRsnDescTxt_B" ezfName="svcMemoRsnDescTxt_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:textArea name="svcCmntTxt_B" ezfName="svcCmntTxt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" cols=\"52\" rows=\"3\""/></td>
													<td class="pAuditInfo">
														<ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
											</ezf:skip>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</table>
<!-- ################################################## Search Result   - [E N D] ################################################## -->
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
