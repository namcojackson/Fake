<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100610052828 --%>
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
			<input type="hidden" name="pageID" value="ZZOL0130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Delete Table Maintenance">
<center>
			<!-- include S21BusinessProcessTAB -->
	<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
						<!-- 	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
							<tr>
								<td width="1100px" height="28px" valign="bottom">
										<div>
											<table border="0" cellpadding="0" cellspacing="0">
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
														<tr title='Order Entry'>
															<td width="107px" align="center" class="same">
																Archive View
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='Hold Release'>
															<td width="90px" align="center" class="disabled">
																Hld Rlse
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='Soft Allocation'>
															<td width="90px" align="center" class="disabled">
																Soft Alloc
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='Hard Allocation'>
															<td width="90px" align="center" class="disabled">
																Hard Alloc
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='Order Inquiry by Status'>
															<td width="90px" align="center" class="disabled">
																Ordr Inq
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='Sales Summary'>
															<td width="90px" align="center" class="disabled">
																Sales Sum
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='ATP Inquiry by Order'>
															<td width="90px" align="center" class="disabled">
																ATP Inq
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='iW Remote Upload'>
															<td width="90px" align="center" class="disabled">
																iW Rmt Upld
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='iW Remote Inquiry'>
															<td width="90px" align="center" class="disabled">
																iW Rmt Inq
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
														<tr title='Upload Screen for LAN Data from Canon Inc.'>
															<td width="90px" align="center" class="disabled">
																LAN Upld
															</td>
															<td width="17px" align="center">
																<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
															</td>
														</tr>
													</table>
												</td>
											</table>
										</div>
								</td>
								<td valign="bottom" align="center">
										<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>-->
						
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
					<table height="5" border="0" align="center">
						<tr valign="middle">
							<td>&nbsp;</td>
						</tr>
					</table>
					<table height="35" border="0" align="center">
						<col width="20">
						<col width="130">
						<col width="170">
						<col width="400">
						<col width="170">
						<tr valign="middle">
							<td>&nbsp;</td>
							<td class="stab" align="left"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
							<td ><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="ABR" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="stab" align="left">Delete Table ID(*)</td>
							<td><ezf:inputText name="delTblId_A0" ezfName="delTblId_A0" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"28\" ezftoupper=\"\""/></td>
							<td>&nbsp;</td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
						</tr>

					</table>
<!-- **************************************** HEADER **************************************** -->
					<hr>
<!-- **************************************** DETAIL **************************************** -->
					<table border="0"  align="center">
						<col width="20">
						<col width="996">
						<td>&nbsp;</td>
						<td>
							<!-- ********* EDIT ********* -->
							<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
								<col width="280" align="left">
								<col width="140" align="left">
								<col width="110" align="center">
								<col width="236" align="left">
								<col width="140" align="center">
								<col width="120" align="center">
								<tr height="24">
									<td class="pClothBs">Delete Table ID</td>
									<td class="pClothBs">Cancel Flag</td>
									<td class="pClothBs">Effective Months</td>
									<td class="pClothBs">Comment</td>
									<td class="pClothBs">Delete Execution Date</td>
									<td class="pClothBs">&nbsp;</td>
								</tr>
								<tr height="45">
									<td><ezf:inputText name="delTblId" ezfName="delTblId" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"28\" ezftoupper=\"\""/></td>
									<td align="center">
										<ezf:select name="specEffCancCd_L" ezfName="specEffCancCd_L" >
											<ezf:option value="1" >Cancel</ezf:option>
											<ezf:option value="0" >Not Cancel</ezf:option>
											<ezf:option value="9" >All</ezf:option>
										</ezf:select>
									</td>
									<td><ezf:inputText name="specEffMthAot" ezfName="specEffMthAot" value="XXXXX" otherAttr=" size=\"4\" maxlength=\"3\""/></td>
									<td><ezf:inputText name="delTblCmntTxt" ezfName="delTblCmntTxt" value="XXXXXXXXXXXXXXXXXX" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
									<td><ezf:inputText name="delExecDt" ezfName="delExecDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>&nbsp;<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('delExecDt', 4);" ></td>
									<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn4" />
									<ezf:inputButton name="Upd" value="Upd" htmlClass="pBtn4" /></td>
								</tr>
							</table>
							<!-- ********* EDIT ********* -->
							<table border="0" >
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table>
							<!-- ********* DATA ********* -->
							<table border="0" >
								<td width="100%" valign="top">
									<tr>
										<!-- ****** PAGENATION ****** -->
										<table border="0" cellpadding="1" width="98%">
										<!--
										<table border="0" cellpadding="0" width="100%">
										    <tr>
										        <td align="left">
										            <table border="0" cellpadding="0" align="left" cellspacing="0">
										                <col>
										                <tr>
										                    <td>Results 999 - 999 of 999</td>
										                </tr>
										            </table>
										        </td>
										        <td align="right">
										            <table border="0" cellpadding="0" cellspacing="0">
										                <col width="54"  align="center">
										                <col width="40"  align="center">
										                <col width="16"  align="center">
										                <col width="40"  align="center">
										                <col width="26"  align="center">
										                <col width="10">
										                <col>
										                <col width="20">
										                <col>
										                <col width="1">
										                <col>
										                <tr>
										                    <td class="stab">Showing</td>
										                    <td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
										                    <td class="stab">/</td>
										                    <td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
										                    <td class="stab">page</td>
										                    <td>&nbsp;</td>
										                    <td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
										                    <td></td>
										                    <td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
										                    <td></td>
										                    <td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
										                </tr>
										            </table>
										        </td>
										    </tr>
										</table>
										-->

										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"          value="A" />
											<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"         value="FULL" />
										</jsp:include>

										<!-- ****** PAGENATION ****** -->
										<!-- ******* TITLE ******* -->
										<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
											<col width="280" align="left">
											<col width="140" align="left">
											<col width="110" align="center">
											<col width="236" align="left">
											<col width="140" align="center">
											<col width="120" align="center">
											<tr height="24">
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'delTblId_A1'           )">Delete Table ID      <img id="sortIMG.delTblId_A1"         border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxEzCancFlgSpecName_A1')">Cancel Flag          <img id="sortIMG.specEzCancelflag_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'specEffMth_A1'         )">Effective Months     <img id="sortIMG.specEffMth_A1"       border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'delTblCmntTxt_A1'      )">Comment              <img id="sortIMG.delTblCmntTxt_A1"    border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'delExecDt_A1'          )">Delete Execution Date<img id="sortIMG.delExecDt_A1"        border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs">&nbsp;</td>
											</tr>
										</table>
										<!-- ******* TITLE ******* -->
										<!-- ******* ARRAY ******* -->
										<div style="overflow-y:scroll; height:316; width:1046; table-layout:fixed;">
											<table style="table-layout:fixed" id="A" border="1" cellpadding="1" cellspacing="0">
											<col width="30" align="left">
											<col width="250" align="left">
											<col width="140" align="left">
											<col width="110" align="center">
											<col width="236" align="left">
											<col width="140" align="center">
											<col width="120" align="center">
												<ezf:row ezfHyo="A">
												<tr height="38">
													<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="delTblId_A1" ezfName="delTblId_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="xxSpecEffCancNm_A1" ezfName="xxSpecEffCancNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="specEffMthAot_A1" ezfName="specEffMthAot_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<ezf:textArea name="delTblCmntTxt_A1" ezfName="delTblCmntTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"30\" style=\"background-color:transparent;border:none;overflow:hidden;\""/>
													</td>
													<td><ezf:label name="delExecDt_A1" ezfName="delExecDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" /></td>
												</tr>
												</ezf:row>
										
												<ezf:skip>
												</ezf:skip>
											</table>
										</div>
										<!-- ******* ARRAY ******* -->
									</tr>
								</td>
							</table>
							<!-- ********* DATA ********* -->
						</td>
					</table>
<!-- **************************************** DETAIL **************************************** -->
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
