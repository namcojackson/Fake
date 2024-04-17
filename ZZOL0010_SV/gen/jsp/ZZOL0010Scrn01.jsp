<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100128014853 --%>
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

<center>
			<!-- include S21BusinessProcessTAB -->
						<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

						<!-- <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
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
						</table> -->
	<div class="pTab_BODY">
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="48">
			</td>
			<td>
			</td>
		</tr>
		<tr valign="top">
			<td width="32">
			</td>
			<td>
				<table align="center" height="72" width="930">
					<col valign="top">
					<tr>
						<td>
							<table align="center" border="0">
								<col width="160">
								<col width="88">
								<col width="100">
								<col width="88">
								<col width="32">
								<col width="16">
								<col width="32">
								<col width="350">
								<tr height="32">
									<td class="stab"><label>Business Application ID</label></td>
									<td class="stab"><ezf:inputText name="ezBusinessID_01" ezfName="ezBusinessID_01" value="XXXXXXXX" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
									<td class="stab"></td>
									<td class="stab"><label>Start Time:</label></td>
									<td class="stab">
										<ezf:select name="xxHrs_SV" ezfName="xxHrs_SV" ezfCodeName="xxHrs_SC" ezfDispName="xxHrs_SD" />
									</td>
									<td class="stab" align="center"><label>:<label></td>
									<td class="stab">
										<ezf:select name="xxMn_SV" ezfName="xxMn_SV" ezfCodeName="xxMn_SC" ezfDispName="xxMn_SD" />
									</td>
									<td class="stab"></td>
								</tr>
							</table>
							<table align="center" border="0">
								<col width="160">
								<col width="88">
								<col width="100">
								<col width="88">
								<col width="32">
								<col width="16">
								<col width="32">
								<col width="350">
								<tr height="32">
									<td class="stab"><ezf:anchor name="xxAncrCtrlFlg" ezfName="xxAncrCtrlFlg" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >User Company CD</ezf:anchor></td>
									<td class="stab"><ezf:inputText name="ezCompanyCode_01" ezfName="ezCompanyCode_01" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
									<td class="stab"></td>
									<td class="stab"><label>End Time:</label></td>
									<td class="stab">
										<ezf:select name="xxHrs_EV" ezfName="xxHrs_EV" ezfCodeName="xxHrs_EC" ezfDispName="xxHrs_ED" />
									</td>
									<td class="stab" align="center"><label>:<label></td>
									<td class="stab">
										<ezf:select name="xxMn_EV" ezfName="xxMn_EV" ezfCodeName="xxMn_EC" ezfDispName="xxMn_ED" />
									</td>
									<td class="stab"></td>
								</tr>
							</table>
							<table border="0">
								<col width="160">
								<col width="60">
								<col width="60">
								<tr>
									<td class="stab"><label>Online Stop Flag</label></td>
									<td class="stab"><ezf:inputRadio name="ezOnlStopFlag_01" ezfName="ezOnlStopFlag_01" value="1" /><label>ON</label></td>
									<td class="stab"><ezf:inputRadio name="ezOnlStopFlag_01" ezfName="ezOnlStopFlag_01" value="0" /><label>OFF</label></td>
								</tr>
							</table>
							<table border="0">
								<col width="160">
								<col width="60">
								<col width="60">
								<col width="682">
								<tr>
									<td class="stab"><label>Online Event Process Log</label></td>
									<td class="stab"><ezf:inputRadio name="ezAcctInfoMode_01" ezfName="ezAcctInfoMode_01" value="1" /><label>RUN</label></td>
									<td class="stab"><ezf:inputRadio name="ezAcctInfoMode_01" ezfName="ezAcctInfoMode_01" value="0" /><label>STOP</label></td>
								</tr>
							</table>
							<table border="0">
								<col width="160">
								<col width="60">
								<col width="60">
								<col width="682">
								<tr>
									<td class="stab"><label>Debug Log</label></td>
									<td class="stab"><ezf:inputRadio name="ezDebugLevel_01" ezfName="ezDebugLevel_01" value="1" /><label>RUN</label></td>
									<td class="stab"><ezf:inputRadio name="ezDebugLevel_01" ezfName="ezDebugLevel_01" value="0" /><label>STOP</label></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
			</td>
		</tr>
	</table>
	</div>
</center>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZOL0010Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Online Service Maintenance Edit">

<%-- **** Task specific area ends here **** --%>
