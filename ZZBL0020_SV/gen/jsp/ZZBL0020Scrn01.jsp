<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20140619032955 --%>
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
<input type="hidden" name="pageID" value="ZZBL0020Scrn01"> 
<!-- Set Page Name -->
<input type="hidden" name="pageName" value="Request Control Master Console">

<div id="main_BODY" >
<table border="0" width="100%" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td>
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
			<table align="center" align="center" border="0" width="1000">
				<tbody>
					<tr><td>&nbsp;</td></tr>
					<tr height="40">
						<td width="300"></td>
						<td width="60"></td>
						<td width="60"></td>
						<td width="60"></td>
						<td width="60"></td>
					</tr>
					<tr>
						<td align="right" class="stab">JOB NET ID&nbsp;</td>
						<td colspan="2">
							<ezf:inputText name="ezReqBusinessID_B" ezfName="ezReqBusinessID_B" otherAttr=" size=\"40\" ezftoupper=\"\""/>
						</td>
						<td align="center">
							
						</td>
						<td align="left">
							
						</td>
					</tr>
										
					<tr>
						<td align="right" class="stab">JOB NET Name&nbsp;</td>
						<td height="20" colspan="4">
							<ezf:inputText name="ezReqBusinessName_B" ezfName="ezReqBusinessName_B" value="XXX" otherAttr=" size=\"53\""/>
						</td>
					</tr>
					<tr>
						<td align="right" class="stab">Sceduled Control JOB NET ID&nbsp;</td>
						<td height="20" colspan="4">
							<ezf:inputText name="ezReqJobCtlNetID_B" ezfName="ezReqJobCtlNetID_B" otherAttr=" size=\"16\" ezftoupper=\"\""/>
						</td>
					</tr>
					
					<tr>
						<td align="right" class="stab">Concurrency&nbsp;</td>
						<td height="20">
							<ezf:select name="ezReqJobConcurrency_B" ezfName="ezReqJobConcurrency_B" ezfCodeName="ezReqJobConcurrency_P1" ezfDispName="xxJobCncrCd_P1" />
						</td>
						<td align="left">Valid value : 1 to 9, A to Z </td>
					</tr>
					<tr>
						<td align="right" class="stab">Job Blocking Flag&nbsp;</td>
						<td height="20">
							<ezf:select name="ezReqJobStopFlag_B" ezfName="ezReqJobStopFlag_B" ezfCodeName="ezReqJobStopFlag_P2" ezfDispName="xxJobBlockFlgNm_P2" />
						</td>
						<td align="left">Yes : Stop Running from Sceduled Control JOB NET <br> No: Run from Sceduled Control JOB NET</td>
					</tr>
					
					<tr>
						<td align="right" class="stab">Job Error Flag&nbsp;</td>
						<td height="20" >
							<ezf:select name="ezReqJobErrorCtlFlag_B" ezfName="ezReqJobErrorCtlFlag_B" ezfCodeName="ezReqJobErrorCtlFlag_P3" ezfDispName="xxJobErrCtrlFlgNm_P3" />
							
						</td>
						<td align="left">Continue or Abort in ABEND.</td>	
					</tr>
					
					<tr>
						<td align="right" class="stab">Max Counter&nbsp;</td>
						<td height="20" colspan="3">
							<ezf:inputText name="ezReqCountPerJob_B" ezfName="ezReqCountPerJob_B" value="100" otherAttr=" size=\"10\""/>
						</td>
					</tr>
					<tr height="200"><td colspan="5"></td></tr>
				</tbody>
			</table>
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>

<%-- **** Task specific area ends here **** --%>
