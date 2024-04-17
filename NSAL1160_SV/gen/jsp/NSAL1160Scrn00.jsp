<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170712112224 --%>
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
			<input type="hidden" name="pageID" value="NSAL1160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Watch Notes Action">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<div class="pTab_BODY">
<!-- ################################################## HEADER - [START] ################################################## -->
				<table border="0" width="100%">
					<tr>
						<td>
							<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
							<colgroup>
								<col width="80px"><!-- Label -->
								<col width="100px">
								<col width="80px"><!-- Label -->
								<col width="100px">
								<col width="110px"><!-- Label -->
								<col width="">
							</colgroup>
							<tbody>
								<tr>
								<td class="stab" align="center">Contract#</td>
								<td class="stab" align="left"><ezf:label name="dsContrNum" ezfName="dsContrNum" /></td>
								<td class="stab" align="center">Customer#</td>
								<td class="stab" align="left"><ezf:label name="shipToCustAcctCd" ezfName="shipToCustAcctCd" /></td>
								<td class="stab" align="center">Customer Name</td>
								<td class="stab" align="left"><ezf:label name="dsAcctNm" ezfName="dsAcctNm" /></td>
								</tr>
							</tbody>
							</table>
						</td>
					</tr>
				</table>
<!-- ################################################## HEADER - [END  ] ################################################## -->
<!-- ################################################## List1 - [START] ################################################### -->
				<table border="0" width="100%">
					<col width="100%">
						<tr>
							<td valign="top">
								<fieldset style="height:120;">
									<legend>Search Workflow Actions</legend>
									<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<col width="409px">
									<col >
								    <col width="4px">
										<tr>
										  <td>
										  	<table border="0" cellpadding="0" cellspacing="0">
										  		<col width="10px">
										  		<col width="80px">	<!-- Label -->
												<col width="112px">
												<col width="5px">	<!-- Label -->
												<col width="112px">
												<col width="100px">
												<tr>
													<td></td>
													<td class="stab" align="left">View From</td>
													<td class="stab" align="center"><ezf:inputText name="procDt_AF" ezfName="procDt_AF" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('procDt_AF', 4);" id="procDt_AF"></td>
													<td class="stab" align="center"> - </td>
													<td class="stab" align="center"><ezf:inputText name="procDt_AT" ezfName="procDt_AT" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('procDt_AT', 4);" id="procDt_AT"></td>
													<td><ezf:inputButton name="SearchA" ezfName="SearchA" value="Search" htmlClass="pBtn6" /></td>
												</tr>
										  	</table>
										  </td>
										  <td align="right">
											<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
											<table border="0" cellpadding="0" width="100%">
												<tr align="right">
													<td>
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
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
														</jsp:include>
													</td>
												</tr>
									 		</table>
									 		<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
										  </td>
										  <td></td>
										</tr>
										<tr>
											<td colspan="3">
												<div id="topTBL_A" style="overflow-x:hidden; overflow-y:none;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="1098px">
														<colgroup>
														<col align="middle" width="120px">	<!-- Note Creation Date -->
														<col align="middle" width="100px">	<!-- Process Date -->
														<col align="middle" width="200px">	<!-- Created by -->
														<col align="middle" width="200px">	<!-- Action -->
														<col align="middle">				<!-- Note -->
														</colgroup>
														<tbody>
															<tr height="25">
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'cratDt_DA')">Note Creation Date</a><img id="sortIMG.cratDt_DA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'procDt_DA')">Process Date</a><img id="sortIMG.procDt_DA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem61Txt_A')">Created by</a><img id="sortIMG.xxScrItem61Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'abuseActDescTxt')">Action</a><img id="sortIMG.abuseActDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'abuseActCmntTxt')">Note</a><img id="sortIMG.abuseActCmntTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															</tr>
														</tbody>
													</table>
												</div>
												<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; height:110px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL_A'));">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all" width="100%">
														<colgroup>
														<col align="middle" width="120px">	<!-- Note Creation Date -->
														<col align="middle" width="100px">	<!-- Process Date -->
														<col align="middle" width="200px">	<!-- Created by -->
														<col align="middle" width="200px">	<!-- Action -->
														<col>								<!-- Note -->
														</colgroup>
														<tbody>
														<ezf:row ezfHyo="A">
															<tr height="25">
																<td><ezf:label name="cratDt_DA" ezfName="cratDt_DA" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="procDt_DA" ezfName="procDt_DA" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="xxScrItem61Txt_A" ezfName="xxScrItem61Txt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3 XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" style=\"border:none; background-color:transparent;\" id=\"xxScrItem61Txt_A#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputText name="abuseActDescTxt" ezfName="abuseActDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" style=\"border:none; background-color:transparent;\" id=\"abuseActDescTxt#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputText name="abuseActCmntTxt" ezfName="abuseActCmntTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"60\" style=\"border:none; background-color:transparent;\" id=\"abuseActCmntTxt#{EZF_ROW_NUMBER}\""/></td>
															</tr>
															<ezf:skip>
															<tr>
																<td><label ezfout name="cratDt_DA" ezfname="cratDt_DA" ezfhyo="A">2016/01/01</label></td>
																<td><label ezfout name="procDt_DA" ezfname="procDt_DA" ezfhyo="A">2016/01/01</label></td>
																<td><input type="text" class="pPro" size="25" style="border:none; background-color:transparent;" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3 XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="xxScrItem61Txt_A" ezfname="xxScrItem61Txt_A" id="xxScrItem61Txt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
																<td><input type="text" class="pPro" size="25" style="border:none; background-color:transparent;" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="abuseActDescTxt" ezfname="abuseActDescTxt" id="abuseActDescTxt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
																<td><input type="text" class="pPro" size="60" style="border:none; background-color:transparent;" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="abuseActCmntTxt" ezfname="abuseActCmntTxt" id="abuseActCmntTxt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															</tr>
															</ezf:skip>
														</ezf:row>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
				</table><!-- Table_A END -->
<!-- ################################################## List1 - [END  ] ################################################## -->
<!-- ################################################## List2 - [START] ################################################## -->
				<table border="0" width="100%">
				<col width="100%">
					<tr>
						<td valign="top">
							<fieldset style="height:120;">
								<legend>Search Notes</legend>
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
								<col width="409px">
								<col >
								<col width="4px">
									<tr>
									  <td>
									  	<table border="0" cellpadding="0" cellspacing="0">
									  		<col width="10px">
									  		<col width="80px">	<!-- Label -->
											<col width="112px">
											<col width="5px">	<!-- Label -->
											<col width="112px">
											<col width="100px">
											<tr>
												<td></td>
												<td class="stab" align="left">View From</td>
												<td class="stab" align="center"><ezf:inputText name="procDt_BF" ezfName="procDt_BF" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
												<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('procDt_BF', 4);" id="procDt_BF"></td>
												<td class="stab" align="center"> - </td>
												<td class="stab" align="center"><ezf:inputText name="procDt_BT" ezfName="procDt_BT" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
												<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('procDt_BT', 4);" id="procDt_BT"></td>
												<td><ezf:inputButton name="SearchB" ezfName="SearchB" value="Search" htmlClass="pBtn6" /></td>
											</tr>
									  	</table>
									  </td>
									  <td align="right">
										<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
										<table border="0" cellpadding="0" width="100%">
											<tr align="right">
												<td>
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
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
													</jsp:include>
												</td>
											</tr>
								 		</table>
								 		<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
									  </td>
									  <td></td>
									</tr>
									<tr>
										<td colspan="3">
											<div id="topTBL_B" style="overflow-x:hidden; overflow-y:none;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="1098px">
													<colgroup>
													<col align="middle" width="120px">	<!-- Order Date -->
													<col align="middle" width="100px">	<!-- Process Date -->
													<col align="middle" width="200px">	<!-- Action -->
													<col align="middle">	            <!-- Note -->
													</colgroup>
													<tbody>
														<tr height="25">
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'procDt_D1')">Order Date</a><img id="sortIMG.procDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'procDt_D2')">Process Date</a><img id="sortIMG.procDt_D2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'svcMemoTpDescTxt')">Action</a><img id="sortIMG.svcMemoTpDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'svcCmntTxt')">Note</a><img id="sortIMG.svcCmntTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														</tr>
													</tbody>
												</table>
											</div>
											<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; height:110px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL_B'));">
												<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout:fixed;word-break:break-all" width="100%">
													<colgroup>
													<col align="middle" width="120px">	<!-- Order Date -->
													<col align="middle" width="100px">	<!-- Process Date -->
													<col align="middle" width="200px">	<!-- Action -->
													<col>								<!-- Note -->
													</colgroup>
													<tbody>
													<ezf:row ezfHyo="B">
														<tr height="25">
															<td><ezf:label name="procDt_D1" ezfName="procDt_D1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="procDt_D2" ezfName="procDt_D2" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="svcMemoTpDescTxt" ezfName="svcMemoTpDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" style=\"border:none; background-color:transparent;\" id=\"svcMemoTpDescTxt#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="svcCmntTxt" ezfName="svcCmntTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"70\" style=\"border:none; background-color:transparent;\" id=\"svcCmntTxt#{EZF_ROW_NUMBER}\""/></td>
														</tr>
														<ezf:skip>
														<tr>
															<td><label ezfout name="procDt_D1" ezfname="procDt_D1" ezfhyo="B">2016/01/01</label></td>
															<td><label ezfout name="procDt_D2" ezfname="procDt_D2" ezfhyo="B">2016/01/01</label></td>
															<td><input type="text" class="pPro" size="25"  style="border:none; background-color:transparent;" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="svcMemoTpDescTxt" ezfname="svcMemoTpDescTxt" id="svcMemoTpDescTxt#{EZF_ROW_NUMBER}" ezfhyo="B"></td>
															<td><input type="text" class="pPro" size="70" style="border:none; background-color:transparent;" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" name="svcCmntTxt" ezfname="svcCmntTxt" id="svcCmntTxt#{EZF_ROW_NUMBER}" ezfhyo="B"></td>
														</tr>
														</ezf:skip>
													</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table><!-- Table_B END -->
<!-- ################################################## List2 - [END  ] ################################################## -->
<!-- ################################################## List3 - [START] ################################################## -->
			<table border="0" width="100%">
				<col width="100%">
					<tr>
						<td valign="top">
							<fieldset style="height:120;">
								<legend>Search Enforcement Actions</legend>
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
								<col width="409px">
								<col >
								<col width="4px">
									<tr>
									  <td>
									  	<table border="0" cellpadding="0" cellspacing="0">
									  		<col width="10px">
									  		<col width="80px">	<!-- Label -->
											<col width="112px">
											<col width="5px">	<!-- Label -->
											<col width="112px">
											<col width="100px">
											<tr>
												<td></td>
												<td class="stab" align="left">View From</td>
												<td class="stab" align="center"><ezf:inputText name="cratDt_CF" ezfName="cratDt_CF" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
												<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('cratDt_CF', 4);" id="cratDt_CF"></td>
												<td class="stab" align="center"> - </td>
												<td class="stab" align="center"><ezf:inputText name="cratDt_CT" ezfName="cratDt_CT" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
												<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('cratDt_CT', 4);" id="cratDt_CT"></td>
												<td><ezf:inputButton name="SearchC" ezfName="SearchC" value="Search" htmlClass="pBtn6" /></td>
											</tr>
									  	</table>
									  </td>
									  <td align="right">
										<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
										<table border="0" cellpadding="0" width="100%">
											<tr align="right">
												<td>
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
														<jsp:param name="TableNm"     value="C" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_C" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_C" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_C" />
													</jsp:include>
												</td>
											</tr>
								 		</table>
								 		<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
									  </td>
									  <td></td>
									</tr>
									<tr>
										<td colspan="3">
											<div id="topTBL_C" style="overflow-x:hidden; overflow-y:none;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="1098px">
													<colgroup>
													<col align="middle" width="220px">	<!-- Override Reason -->
													<col align="middle" width="100px">	<!-- Override Date -->
													<col align="middle" width="250px">	<!-- Approver -->
													<col align="middle" width="100px">	<!-- Expiration Date -->
													<col>
													</colgroup>
													<tbody>
														<tr height="25">
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('C', 'abuseOvwrtRsnDescTxt')">Override Reason</a><img id="sortIMG.abuseOvwrtRsnDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('C', 'cratDt')">Override Date</a><img id="sortIMG.cratDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('C', 'xxScrItem61Txt_C')">Approver</a><img id="sortIMG.xxScrItem61Txt_C" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('C', 'svcSplyExprDt')">Expiration Date</a><img id="sortIMG.svcSplyExprDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td class="pClothBs" >&nbsp;</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; height:110px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL_C'));">
												<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout:fixed;word-break:break-all" width="100%">
													<colgroup>
													<col align="middle" width="220px">	<!-- Override Reason -->
													<col align="middle" width="100px">	<!-- Override Date -->
													<col align="middle" width="250px">	<!-- Approver -->
													<col align="middle" width="100px">	<!-- Expiration Date -->
													<col>
													</colgroup>
													<tbody>
													<ezf:row ezfHyo="C">
														<tr height="25">
															<td><ezf:inputText name="abuseOvwrtRsnDescTxt" ezfName="abuseOvwrtRsnDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\" id=\"abuseOvwrtRsnDescTxt#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:label name="cratDt" ezfName="cratDt" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="xxScrItem61Txt_C" ezfName="xxScrItem61Txt_C" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3 XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"35\" style=\"border:none; background-color:transparent;\" id=\"xxScrItem61Txt_C#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:label name="svcSplyExprDt" ezfName="svcSplyExprDt" ezfHyo="C" ezfArrayNo="0" /></td>
															<td>&nbsp;</td>
														</tr>
														<ezf:skip>
														<tr>
															<td><input type="text" class="pPro" size="30" style="border:none; background-color:transparent;" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="abuseOvwrtRsnDescTxt" ezfname="abuseOvwrtRsnDescTxt" id="abuseOvwrtRsnDescTxt#{EZF_ROW_NUMBER}" ezfhyo="C"></td>
															<td><label ezfout name="cratDt" ezfname="cratDt" ezfhyo="C">2016/01/01</label></td>
															<td><input type="text" class="pPro" size="35" style="border:none; background-color:transparent;" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3 XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="xxScrItem61Txt_C" ezfname="xxScrItem61Txt_C" id="xxScrItem61Txt_C#{EZF_ROW_NUMBER}" ezfhyo="C"></td>
															<td><label ezfout name="svcSplyExprDt" ezfname="svcSplyExprDt" ezfhyo="C">2016/01/01</label></td>
															<td>&nbsp;</td>
														</tr>
														</ezf:skip>
													</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table><!-- Table_C END -->
<!-- ################################################## List3 - [END  ] ################################################## -->
			</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
