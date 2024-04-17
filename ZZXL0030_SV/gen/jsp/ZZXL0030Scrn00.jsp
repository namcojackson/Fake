<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100127000758 --%>
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
			<input type="hidden" name="pageID" value="ZZXL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Business Calendar Console">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
					<!-- Parts or S21 -->
					<table width="100%" height="2" border="0">
						<tr valign="middle">
							<td>&nbsp;</td>
						</tr>
						<tr valign="middle">
							<td>&nbsp;</td>
						</tr>
					</table>
					<table align="center" width="700" border="0" id="glblCmpyTable">
						<col width="50">
						<col width="130">
						<col width="40">
						<col width="50">
						<col width="50">
						<col width="50">
						<col width="300">
						<tr>
							<td></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
							<td><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="Find" value="Find" htmlClass="pBtn5" /></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<table align="center" width="700" border="0">
						<col width="87">
						<col width="192">
						<col width="40">
						<col width="50">
						<col width="50">
						<col width="50">
						<col width="300">
						<tr>
							<td></td>
							<td class="stab"><label>Calendar Type</label></td>
							<td colspan="2">
								<ezf:select name="calTpCd" ezfName="calTpCd" ezfCodeName="calTpCd_DP" ezfDispName="calTpNm_DP" />
							</td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
						</tr>
						<tr>
							<td></td>
							<td class="stab"><label>Month/Year</label></td>
							<td>
								<ezf:select name="xxMthDt" ezfName="xxMthDt" ezfCodeName="xxMthDt_D1" ezfDispName="xxMthDt_D2" />
							</td>
							<td><ezf:inputText name="xxYrDt" ezfName="xxYrDt" otherAttr=" size=\"4\""/></td>
							<td><ezf:inputButton name="Today" value="Today" htmlClass="pBtn5" /></td>
							<td><ezf:inputButton name="PrevMonth" value="Prev" htmlClass="pBtn5" /></td>
							<td><ezf:inputButton name="NextMonth" value="Next" htmlClass="pBtn5" /></td>
						</tr>
					</table>
					<hr size="1" width="55%" noshade>

					<table cellpadding="0" cellspacing="0" border="0">
						<col width="300">
						<col width="300">
						<col width="50">
						<col width="300">
						<col width="300">
						<tr>
						<td></td>
						<td align="center">
						<table width="100%" height="35" border="1" id="calTable">
							<tr valign="middle">
								<td class="stab" colspan="7" align="center"><ezf:label name="xxYrMth_01" ezfName="xxYrMth_01" /></td>
							</tr>
							<tr>
								<td class="stab" align="center"><font color="red">Sun</font></td>
								<td class="stab" align="center">Mon</td>
								<td class="stab" align="center">Tue</td>
								<td class="stab" align="center">Wed</td>
								<td class="stab" align="center">Thu</td>
								<td class="stab" align="center">Fri</td>
								<td class="stab" align="center"><font color="blue">Sat</font></td>
							</tr>
							
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="0" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="1" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="2" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="3" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="4" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="5" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="6" /></td>
							</tr>
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="1" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="2" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="3" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="4" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="5" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="6" /></td>
							</tr>

							<tr>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="7" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="8" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="9" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="10" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="11" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="12" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="13" /></td>
							</tr>
							<tr>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="7" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="8" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="9" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="10" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="11" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="12" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="13" /></td>
							</tr>
							
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="14" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="15" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="16" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="17" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="18" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="19" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="20" /></td>
							</tr>
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="14" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="15" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="16" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="17" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="18" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="19" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="20" /></td>
							</tr>
							
							<tr>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="21" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="22" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="23" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="24" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="25" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="26" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="27" /></td>
							</tr>
							<tr>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="21" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="22" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="23" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="24" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="25" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="26" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="27" /></td>
							</tr>
							
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="28" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="29" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="30" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="31" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="32" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="33" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="34" /></td>
							</tr>
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="28" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="29" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="30" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="31" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="32" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="33" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="34" /></td>
							</tr>
							
							<tr>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="35" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="36" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="37" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="38" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="39" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="40" /></td>
								<td align="center"><ezf:label name="xxDay_A" ezfName="xxDay_A" ezfHyo="A" ezfArrayNo="41" /></td>
							</tr>
							<tr>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="35" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="36" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="37" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="38" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="39" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="40" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="41" /></td>
							</tr>
						</table>
						</td>
						<td></td>
						<td align="center">
						<table width="100%" height="35" border="1" id="calTable">
							<tr valign="middle"><td class="stab" colspan="7" align="center"><ezf:label name="xxYrMth_02" ezfName="xxYrMth_02" /></td></tr>
							<tr>
								<td class="stab" align="center"><font color="red">Sun</font></td>
								<td class="stab" align="center">Mon</td>
								<td class="stab" align="center">Tue</td>
								<td class="stab" align="center">Wed</td>
								<td class="stab" align="center">Thu</td>
								<td class="stab" align="center">Fri</td>
								<td class="stab" align="center"><font color="blue">Sat</font></td>
							</tr>
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezfbrrayno=\"0\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="1" otherAttr=" ezfbrrayno=\"1\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="2" otherAttr=" ezfbrrayno=\"2\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="3" otherAttr=" ezfbrrayno=\"3\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="4" otherAttr=" ezfbrrayno=\"4\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="5" otherAttr=" ezfbrrayno=\"5\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="6" otherAttr=" ezfbrrayno=\"6\""/></td>
							</tr>
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezfbrrayno=\"0\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="1" otherAttr=" ezfbrrayno=\"1\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="2" otherAttr=" ezfbrrayno=\"2\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="3" otherAttr=" ezfbrrayno=\"3\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="4" otherAttr=" ezfbrrayno=\"4\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="5" otherAttr=" ezfbrrayno=\"5\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="6" otherAttr=" ezfbrrayno=\"6\""/></td>
							</tr>

							<tr>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="7" otherAttr=" ezfbrrayno=\"7\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="8" otherAttr=" ezfbrrayno=\"8\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="9" otherAttr=" ezfbrrayno=\"9\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="10" otherAttr=" ezfbrrayno=\"10\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="11" otherAttr=" ezfbrrayno=\"11\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="12" otherAttr=" ezfbrrayno=\"12\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="13" otherAttr=" ezfbrrayno=\"13\""/></td>
							</tr>
							<tr>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="7" otherAttr=" ezfbrrayno=\"7\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="8" otherAttr=" ezfbrrayno=\"8\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="9" otherAttr=" ezfbrrayno=\"9\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="10" otherAttr=" ezfbrrayno=\"10\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="11" otherAttr=" ezfbrrayno=\"11\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="12" otherAttr=" ezfbrrayno=\"12\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="13" otherAttr=" ezfbrrayno=\"13\""/></td>
							</tr>
							
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="14" otherAttr=" ezfbrrayno=\"14\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="15" otherAttr=" ezfbrrayno=\"15\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="16" otherAttr=" ezfbrrayno=\"16\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="17" otherAttr=" ezfbrrayno=\"17\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="18" otherAttr=" ezfbrrayno=\"18\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="19" otherAttr=" ezfbrrayno=\"19\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="20" otherAttr=" ezfbrrayno=\"20\""/></td>
							</tr>
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="14" otherAttr=" ezfbrrayno=\"14\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="15" otherAttr=" ezfbrrayno=\"15\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="16" otherAttr=" ezfbrrayno=\"16\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="17" otherAttr=" ezfbrrayno=\"17\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="18" otherAttr=" ezfbrrayno=\"18\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="19" otherAttr=" ezfbrrayno=\"19\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="20" otherAttr=" ezfbrrayno=\"20\""/></td>
							</tr>
							
							<tr>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="21" otherAttr=" ezfbrrayno=\"21\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="22" otherAttr=" ezfbrrayno=\"22\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="23" otherAttr=" ezfbrrayno=\"23\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="24" otherAttr=" ezfbrrayno=\"24\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="25" otherAttr=" ezfbrrayno=\"25\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="26" otherAttr=" ezfbrrayno=\"26\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="27" otherAttr=" ezfbrrayno=\"27\""/></td>
							</tr>
							<tr>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="21" otherAttr=" ezfbrrayno=\"21\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="22" otherAttr=" ezfbrrayno=\"22\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="23" otherAttr=" ezfbrrayno=\"23\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="24" otherAttr=" ezfbrrayno=\"24\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="25" otherAttr=" ezfbrrayno=\"25\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="26" otherAttr=" ezfbrrayno=\"26\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="27" otherAttr=" ezfbrrayno=\"27\""/></td>
							</tr>
							
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="28" otherAttr=" ezfbrrayno=\"28\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="29" otherAttr=" ezfbrrayno=\"29\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="30" otherAttr=" ezfbrrayno=\"30\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="31" otherAttr=" ezfbrrayno=\"31\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="32" otherAttr=" ezfbrrayno=\"32\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="33" otherAttr=" ezfbrrayno=\"33\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="34" otherAttr=" ezfbrrayno=\"34\""/></td>
							</tr>
							<tr bgcolor="#dfdfdf">
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="28" otherAttr=" ezfbrrayno=\"28\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="29" otherAttr=" ezfbrrayno=\"29\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="30" otherAttr=" ezfbrrayno=\"30\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="31" otherAttr=" ezfbrrayno=\"31\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="32" otherAttr=" ezfbrrayno=\"32\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="33" otherAttr=" ezfbrrayno=\"33\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="34" otherAttr=" ezfbrrayno=\"34\""/></td>
							</tr>
							
							<tr>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="35" otherAttr=" ezfbrrayno=\"35\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="36" otherAttr=" ezfbrrayno=\"36\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="37" otherAttr=" ezfbrrayno=\"37\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="38" otherAttr=" ezfbrrayno=\"38\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="39" otherAttr=" ezfbrrayno=\"39\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="40" otherAttr=" ezfbrrayno=\"40\""/></td>
								<td align="center"><ezf:label name="xxDay_B" ezfName="xxDay_B" ezfHyo="B" ezfArrayNo="41" otherAttr=" ezfbrrayno=\"41\""/></td>
							</tr>
							<tr>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="35" otherAttr=" ezfbrrayno=\"35\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="36" otherAttr=" ezfbrrayno=\"36\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="37" otherAttr=" ezfbrrayno=\"37\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="38" otherAttr=" ezfbrrayno=\"38\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="39" otherAttr=" ezfbrrayno=\"39\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="40" otherAttr=" ezfbrrayno=\"40\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="41" otherAttr=" ezfbrrayno=\"41\""/></td>
							</tr>
							
						</table>
						</td>
						<td></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td></td>
							<td align="center"><ezf:inputButton name="CheckWeekDaysA" value="Check Weekday" /></td>
							<td></td>
							<td align="center"><ezf:inputButton name="CheckWeekDaysB" value="Check Weekday" /></td>
							<td></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
