<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161025112126 --%>
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
			<input type="hidden" name="pageID" value="NMAL2590Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Geo code search popup">

			<center>
			<BR>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
<%-- ######################################## HEADER ######################################## --%>
							<table border="0" cellpadding="0" cellspacing="0">
								<col align="right" width="61">
								<col width="3">
								<col align="left" width="225">
								<col width="10">
								<col align="right" width="101">
								<col width="3">
								<col align="left" width="225">
								<tr>
									<td class="stab">City(*)</td>
									<td class="stab">&nbsp</td>
									<td><ezf:inputText name="ctyAddr" ezfName="ctyAddr" value="XXXXXXXX" otherAttr=" size=\"40\" maxlength=\"250\" ezftoupper=\"\""/></td>
									<td class="stab">&nbsp</td>
									<td class="stab">Postal Code(*)</td>
									<td class="stab">&nbsp</td>
									<td><ezf:inputText name="postCd" ezfName="postCd" value="XXXXXXXX" otherAttr=" size=\"40\" maxlength=\"250\" ezftoupper=\"\""/></td>
								</tr>
									<td class="stab">State(*)</td>
									<td class="stab">&nbsp</td>
									<td><ezf:inputText name="stCd" ezfName="stCd" value="XX" otherAttr=" size=\"40\" maxlength=\"250\" ezftoupper=\"\""/></td>
									<td class="stab">&nbsp</td>
									<td class="stab">County(*)</td>
									<td class="stab">&nbsp</td>
									<td><ezf:inputText name="cntyNm" ezfName="cntyNm" value="XXXXXXXX" otherAttr=" size=\"40\" maxlength=\"250\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab" colspan="7" align="right">
										<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<hr />
<%-- ######################################## BODY ######################################## --%>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="764" align="right">
							<%-- =============== PAGING =============== --%>
							<table width="100%">
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
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
								<col align="right">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
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
												<td class="pOut">20</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td> </td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</ezf:skip>
						</td>
					</tr>
					<tr>
						<td>
							<table cellpadding="0" border="0">
								<tr>
									<td valign="Top" width="778">
										<div id='rightTblHead' style="width:937px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="762">
												<col align="center" width="252">
												<col align="center" width="105">
												<col align="center" width="150">
												<col align="center" width="255">
												<tr height="24">
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctyAddr_A' )">City<img id="sortIMG.ctyAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'stCd_A' )">State<img id="sortIMG.stCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'postCd_A' )">Postal Code<img id="sortIMG.postCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cntyNm_A' )">County<img id="sortIMG.cntyNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:778px; height:425px; display:block;  overflow-y:scroll; margin:0px; padding:0px;">
											<table id="A_Top" border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="760">
												<col align="left"   width="250">
												<col align="left"   width="105">
												<col align="left"   width="150">
												<col align="left"   width="255">
												<ezf:row ezfHyo="A">
													<tr height="24">
														<td align="left">
															<ezf:anchor name="ctyAddr_A" ezfName="ctyAddr_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectItem" otherAttr=" id=\"ctyAddr_A#{EZF_ROW_NUMBER}\"">
																<ezf:label name="ctyAddr_A" ezfName="ctyAddr_A" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td>
														<td align="left">
															<ezf:anchor name="stCd_A" ezfName="stCd_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectItem" otherAttr=" id=\"stCd_A#{EZF_ROW_NUMBER}\"">
																<ezf:label name="stCd_A" ezfName="stCd_A" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td><td align="left">
															<ezf:anchor name="postCd_A" ezfName="postCd_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectItem" otherAttr=" id=\"postCd_A#{EZF_ROW_NUMBER}\"">
																<ezf:label name="postCd_A" ezfName="postCd_A" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td>
														<td align="left">
															<ezf:anchor name="cntyNm_A" ezfName="cntyNm_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectItem" otherAttr=" id=\"cntyNm_A#{EZF_ROW_NUMBER}\"">
																<ezf:label name="cntyNm_A" ezfName="cntyNm_A" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td>
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
			</center>


<%-- **** Task specific area ends here **** --%>
